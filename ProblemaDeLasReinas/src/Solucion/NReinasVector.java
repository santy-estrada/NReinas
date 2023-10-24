package Solucion;

import java.util.Arrays;

public class NReinasVector {
	//Vector con soluciones
	private static int[][] soluciones = new int[0][0];

	//Revisar si el movimiento es válido
	private static boolean valido(int sol[], int etapa) {
		//Revisa para todas las columnas hasta en la q vamos
		for (int i = 0; i < etapa; i++) {
			/*
			 * Si sol[i] = sol[etapa] significa que hay una reina en esa fila
			 * Si Math.abs(sol[i] - sol[etapa]) == Math.abs(etapa-i)) significa que hay una
			 * 	en la diagonal
			 */
			if(sol[i] == sol[etapa] || (Math.abs(sol[i] - sol[etapa]) == Math.abs(etapa-i))) {
				return false;
			}
		}
		
		return true;
	}
	
	//Método para encontrar soluciones
	private static void reinas(int[] sol, int etapa) {
		//Si la etapa(columna) es mayor o igual al número de columnas, guarda y sale
		if(etapa >= sol.length) {
			saveSol(sol);
			return;
		}
		//Bandera para indicar que se debe salir del cliclo
		boolean flag = true;
		
		do {
			/*Se debe primero sumar uno porque todo es -1 inicialmente
			 * También se debe sumar 1 antes de verificar si la posición es válida
			 * 	No tiene sentido no hacerlo porque se estaría analizando la misma posición infinitas veces
			 */
			sol[etapa]++;	//Se pasa de columna
			//Si el valor ya es igual al largo del tablero, hace backtracking 
			if(sol[etapa] == sol.length) {
				sol[etapa] = -1;
				flag = false;	//Se debe salir del ciclo
				
			}else if(valido(sol, etapa)) {//Si la posición es válida, pasa a la siguiente columna
				reinas(sol, etapa+1);	//Se pasa de fila
			}
				
			
		//Hace el ciclo mientras no se salga del tablero (flag)
		}while(flag);
		
	}
	
	/*Método para guardar soluciones: 
	 * 	- Usar arraycopy() porque pasa las variables por valor y no por referencia
	 */
	private static void saveSol(int[] sol) {
		soluciones = Arrays.copyOf(soluciones, soluciones.length+1);
		int[] temp = new int[sol.length];
		System.arraycopy(sol, 0, temp, 0, temp.length);
		soluciones[soluciones.length-1] = temp;
	}
	
	//Obtener las soluciones
	private static int[][] getSoluciones(){
		return soluciones;
	}
	
	//Método a llamar con la dimensión del tablero
	public static int[][] NReinasVect(int dim){
		int aux []= new int[dim];	//Vector con -1 inicial
		Arrays.fill(aux, -1);
		
		reinas(aux, 0);	//Llamado para guardar las soluciones

		
		return getSoluciones();
	}
	
	//Método para obtener los tableros en consola
	public static void printSolutions(int[][] soluciones) {
		//Primer for para recorrer todas las soluciones
		for(int i = 0; i < soluciones.length; i++) {
			//Segundo for para recorrer las columas
			for(int j = 0; j < soluciones[i].length; j++) {
				//Tercer for para imprimir las filas
				for(int k = 0; k < soluciones[i].length; k++) {
					/*
					 * Se pone X cuando la j sea igual al valor guardado de la columna
					 * Esto indica que allí hay una reina. De lo contrario es vacío (O)
					 * Se logra usando la k. Si el vector solución en la columna k es igual a la 
					 * 	columna actual, hay una reina.
					 */
					if(soluciones[i][k] != j) {
						System.out.print("O ");
					}else {
						System.out.print("X ");
					}
				}
				System.out.println();
			}
			System.out.println();
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int[][] soluciones = NReinasVect(4);
		printSolutions(soluciones);
		
		/*
		int cont = 0;
		for(int i = 0; i < soluciones.length; i++) {
			for(int j = 0; j < soluciones[i].length; j++) {
				System.out.print(soluciones[i][j] + " ");
			}
			cont++;
			System.out.println();
		}
		System.out.println("Cantidad de soluciones encontradas: " + cont);*/
	}

}
