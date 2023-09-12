package Solucion;

import java.util.Arrays;

public class NReinas {
	//Vector que contendr� las soluciones
	//Es tridimensional porq el tablero es 2D
	private static int [][][] soluciones = new int [0][0][0];

	//M�todo que s�lo toma la dimensi�n a usar
	public static int[][][] solucionesReina(int dimension) {
		//Se crea el tablero de dimensiones estipuladas
		int[][] tablero = new int [dimension][dimension];
		//Se llama el m�todo de backtracking
		solucion(0, tablero);
		//Se obtienen las soluciones
		return getSoluciones();
	}
	
	private static void solucion(int fila, int[][] tablero){
		/*
		 * Condici�n de parada: Se acaba de poner la �ltima fina (tablero.length-1) y se ha 
		 * vuelto a hacer la recursi�n. Por eso ya la fila es tablero.length
		 */
		if(fila == tablero.length) {
			//Guarda el tablero actual
			saveSoluciones(tablero);
			return;	//Sale del void
		}
		
		//Backtracking
		for(int col = 0; col < tablero.length; col++) {
			
			//Si la siguiente posici�n es v�lida
			if(valido(tablero, col, fila)) {
				//Poner una reina en la posici�n actual
				tablero[fila][col] = 1;
				//Recursivamente haga esto con todas las filas de la columna actual
				solucion(fila +1, tablero);
				//Si sale de la recursi�n, deshaga lo hecho
				tablero[fila][col] = 0;
			}
			/*
			 * A tener en cuenta: El c�digo funciona porque se guardan en una variable
			 * est�tica los tableros soluci�n, pues todos se deshacen igualmente al final.
			 * 
			 */
		}
		
		
	}
	
	//M�todo para obtener las soluciones
	private static int[][][] getSoluciones(){
		return soluciones;
	}
	
	//Guardar las soluciones (recibe el tablero soluci�n)
	private static void saveSoluciones(int[][] tablero){
		//Dimensi�n del tablero
		int dim = tablero.length;
		//Se le a�ade una soluci�n al arreglo de soluciones
		soluciones = Arrays.copyOf(soluciones, soluciones.length+1);
		/*
		 * A la posici�n a�adida se le debe llenar con un arreglo bidimensional vac�o con las
		 * dimensiones con las que se llenar�.
		 */
		soluciones[soluciones.length-1] = new int[dim][dim];
		/*
		 * Para guardar la informaci�n de un arreglo bidimensional:
		 * 	- Usar arraycopy() porque pasa las variables por valor y no por referencia
		 * 	- Usar for porque se deben guardar los datos por casillas (arreglos 1D)
		 * 	- Los datos se copiar�n por fila del tablero de 0 a tablero.length
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
		int dimension = 4;	//Dimensi�n a usar
		
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
