package interfaz;

import Solucion.NReinasVector;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NReinasInterfaz extends JFrame {
    private int[][] soluciones;
    private int indiceActual;
    private JPanel tableroPanel;

    public NReinasInterfaz(int[][] soluciones) {
        this.soluciones = soluciones;
        this.indiceActual = 0;

        setTitle("Soluciones del Problema de las N Reinas");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 400);

        tableroPanel = new JPanel(new GridLayout(soluciones[0].length, soluciones[0].length));
        add(tableroPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        JButton anteriorButton = new JButton("Anterior");
        JButton siguienteButton = new JButton("Siguiente");

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

        buttonPanel.add(anteriorButton);
        buttonPanel.add(siguienteButton);

        add(buttonPanel, BorderLayout.SOUTH);

        mostrarSolucionActual();
    }

    private void mostrarSolucionActual() {
        if (soluciones.length == 0) {
            JOptionPane.showMessageDialog(this, "No hay soluciones disponibles.");
            return;
        }

        int[] solucionActual = soluciones[indiceActual];
        tableroPanel.removeAll();

        for (int i = 0; i < solucionActual.length; i++) {
            for (int j = 0; j < solucionActual.length; j++) {
                JPanel casilla = new JPanel();
                casilla.setPreferredSize(new Dimension(40, 40)); // Tamaño de cada casilla
                casilla.setBackground((i + j) % 2 == 0 ? Color.WHITE : Color.GRAY);

                if (solucionActual[i] == j) {
                    casilla.add(new JLabel("Q", SwingConstants.CENTER)); // "Q" representa una reina
                }

                tableroPanel.add(casilla);
            }
        }

        tableroPanel.revalidate();
        tableroPanel.repaint();
        actualizarNumeroSolucion();
    }

    private void mostrarSolucionAnterior() {
        if (soluciones.length > 0) {
        	//Esto evita índices negativos y permite dar la vuelta
            indiceActual = (indiceActual - 1 + soluciones.length) % soluciones.length;
            mostrarSolucionActual();
        }
    }

    private void mostrarSiguienteSolucion() {
        if (soluciones.length > 0) {
            indiceActual = (indiceActual + 1) % soluciones.length;
            mostrarSolucionActual();
        }
    }

    private void actualizarNumeroSolucion() {
        setTitle("Solución " + (indiceActual + 1));
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
}
