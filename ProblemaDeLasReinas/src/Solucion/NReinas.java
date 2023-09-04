package Solucion;

import java.util.Arrays;

public class NReinas {
	//Vector que contendrá las soluciones
	//Es tridimensional porq el tablero es 2D
	private static int [][][] soluciones = new int [0][0][0];

	//Método que sólo toma la dimensión a usar
	public static int[][][] solucionesReina(int dimension) {
		//Se crea el tablero de dimensiones estipuladas
		int[][] tablero = new int [dimension][dimension];
		//Se llama el método de backtracking
		solucion(0, tablero);
		//Se obtienen las soluciones
		return getSoluciones();
	}
	
	private static void solucion(int fila, int[][] tablero){
		/*
		 * Condición de parada: Se acaba de poner la última fina (tablero.length-1) y se ha 
		 * vuelto a hacer la recursión. Por eso ya la fila es tablero.length
		 */
		if(fila == tablero.length) {
			//Guarda el tablero actual
			saveSoluciones(tablero);
			return;	//Sale del void
		}
		
		//Backtracking
		for(int col = 0; col < tablero.length; col++) {
			
			//Si la siguiente posición es válida
			if(valido(tablero, col, fila)) {
				//Poner una reina en la posición actual
				tablero[fila][col] = 1;
				//Recursivamente haga esto con todas las filas de la columna actual
				solucion(fila +1, tablero);
				//Si sale de la recursión, deshaga lo hecho
				tablero[fila][col] = 0;
			}
			/*
			 * A tener en cuenta: El código funciona porque se guardan en una variable
			 * estática los tableros solución, pues todos se deshacen igualmente al final.
			 * 
			 */
		}
		
		
	}
	
	//Método para obtener las soluciones
	private static int[][][] getSoluciones(){
		return soluciones;
	}
	
	//Guardar las soluciones (recibe el tablero solución)
	private static void saveSoluciones(int[][] tablero){
		//Dimensión del tablero
		int dim = tablero.length;
		//Se le añade una solución al arreglo de soluciones
		soluciones = Arrays.copyOf(soluciones, soluciones.length+1);
		/*
		 * A la posición añadida se le debe llenar con un arreglo bidimensional vacío con las
		 * dimensiones con las que se llenará.
		 */
		soluciones[soluciones.length-1] = new int[dim][dim];
		/*
		 * Para guardar la información de un arreglo bidimensional:
		 * 	- Usar arraycopy() porque pasa las variables por valor y no por referencia
		 * 	- Usar for porque se deben guardar los datos por casillas (arreglos 1D)
		 * 	- Los datos se copiarán por fila del tablero de 0 a tablero.length
		 */
		for(int i = 0; i < tablero.length; i++) {
			System.arraycopy(tablero[i], 0, soluciones[soluciones.length-1][i], 0, tablero.length);
		}
	}
	
	//Validar un movimiento
	private static boolean valido(int[][] tablero, int columna, int fila) {
		//Me revisa las posiciones hasta la fila actual
		for(int i = 0; i < fila; i++) {
			//Revisa columna recorriendo filas de arriba a abajo
			if(tablero[i][columna] == 1) {
				return false;
			}
			/*
			 * Revisa diagonal izquierda:
			 *	- Como se utiliza el mismo for, las filas se recorren de arriba a abajo
			 *	- Primero se verifica si no se sale del tablero
			 *		columa - fila + 0 es la primera casilla diagonal
			 *		columa - fila + 1 es la segunda casilla diagonal
			 *		...
			 *	- 
			 */
			if (columna - fila + i >= 0 && tablero[i][columna - fila + i] == 1) {
                return false;
            }
			
			/*
			 * Revisa diagonal derecha:
			 *	- Como se utiliza el mismo for, las filas se recorren de arriba a abajo
			 *	- Primero se verifica si no se sale del tablero
			 *		columa + fila - 0 es la primera casilla diagonal
			 *		columa + fila - 1 es la segunda casilla diagonal
			 *		...
			 *	- 
			 */
			if((columna + fila - i) < tablero.length && (tablero[i][columna + fila - i] == 1)) {
				return false;
			}
			
		}
		
		return true;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stu
		int dimension = 4;	//Dimensión a usar
		
		int[][][] soluciones = solucionesReina(dimension);
		
		//Imprimmir soluciones
		for(int i = 0; i < soluciones.length; i++) {
			for(int j = 0; j < soluciones[i].length; j++) {
				for(int k = 0; k < soluciones[i][j].length; k++) {
					System.out.print(soluciones[i][j][k] + " ");
				}
				System.out.println();
			}
			System.out.println("------------------------");
		}
		
		System.out.println("Soluciones encontradas: " + soluciones.length);
		
	}

}
