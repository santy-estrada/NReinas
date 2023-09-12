package interfaz;

import Solucion.NReinasVector;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NReinasInterfaz extends JFrame {
	private int[][] soluciones;
	private int indiceActual;

	private JTextArea solucionTextArea;
	private JButton anteriorButton;
	private JButton siguienteButton;
	private JLabel JlabelsolucionNum;

	public NReinasInterfaz(int[][] soluciones) {
		this.soluciones = soluciones;
		this.indiceActual = 0;

		setTitle("Soluciones del Problema de las N Reinas");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(400, 300);

		solucionTextArea = new JTextArea(10, 30);
		solucionTextArea.setEditable(false);

		anteriorButton = new JButton("Anterior");
		siguienteButton = new JButton("Siguiente");

		anteriorButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mostrarSolucionAnterior();
			}
		});

		siguienteButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mostrarSiguienteSolucion();
			}
		});

		JPanel buttonPanel = new JPanel();
		buttonPanel.add(anteriorButton);
		buttonPanel.add(siguienteButton);

		getContentPane().add(solucionTextArea, BorderLayout.CENTER);
		getContentPane().add(buttonPanel, BorderLayout.SOUTH);

		JlabelsolucionNum = new JLabel("Solucion " + (indiceActual+1));
		JlabelsolucionNum.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(JlabelsolucionNum, BorderLayout.NORTH);

		mostrarSolucionActual();
	}

	private void mostrarSolucionActual() {
		if (soluciones.length == 0) {
			solucionTextArea.setText("No hay soluciones disponibles.");
		} else {
			int[] solucionActual = soluciones[indiceActual];
			StringBuilder textoSolucion = new StringBuilder();
			for (int i = 0; i < solucionActual.length; i++) {
				for (int j = 0; j < solucionActual.length; j++) {
					textoSolucion.append(solucionActual[i] == j ? "X " : "O ");
				}
				textoSolucion.append("\n");
			}
			solucionTextArea.setText(textoSolucion.toString());
		}
	}

	private void mostrarSolucionAnterior() {
        if (soluciones.length > 0) {
            indiceActual = (indiceActual - 1 + soluciones.length) % soluciones.length;
            mostrarSolucionActual();
            actualizarNumeroSolucion();
        }
    }

    private void mostrarSiguienteSolucion() {
        if (soluciones.length > 0) {
            indiceActual = (indiceActual + 1) % soluciones.length;
            mostrarSolucionActual();
            actualizarNumeroSolucion();
        }
    }

    private void actualizarNumeroSolucion() {
        JlabelsolucionNum.setText("Solucion " + (indiceActual+1));
    }
	public static void main(String[] args) {
		// Supongamos que tienes una matriz de soluciones previamente generadas
		NReinasVector nr = new NReinasVector();

		int[][] soluciones = nr.NReinasVect(8);

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				NReinasInterfaz gui = new NReinasInterfaz(soluciones);
				gui.setVisible(true);
			}
		});
	}

	// El código de NReinasVect debe estar disponible aquí
	// para que funcione correctamente.
}
