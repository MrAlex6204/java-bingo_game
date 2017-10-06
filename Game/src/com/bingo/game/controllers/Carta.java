/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bingo.game.controllers;

/**
 *
 * @author
 */

import com.bingo.game.views.FrameCarta;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JToggleButton;

//Crear una herencia de la view (Clase) FrameCarta
public class Carta extends FrameCarta implements ActionListener {

    //Valor asignado para identificar si el boton no tiene asignado un valor numerico
    private final String EMPTY = "[VACIO]";
    private String _nombreDelJugador; //Nombre del jugador
    private boolean _esJuegoDeCartaLlena = false;

    //Lista de valores especificos para esta letra
    private int B_Values[] = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};

    //Crear arrays de JToggleButtons para poder asignarle aleatoriamente el numero    
    private final JToggleButton[] B = {
        this.btn_B1,
        this.btn_B2,
        this.btn_B3,
        this.btn_B4,
        this.btn_B5
    };
    //--------------------------------------------------------------------

    private int I_Values[] = {16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30};
    private final JToggleButton[] I = {
        this.btn_I1,
        this.btn_I2,
        this.btn_I3,
        this.btn_I4,
        this.btn_I5
    };
    //--------------------------------------------------------------------

    private int N_Values[] = {31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45};
    private final JToggleButton[] N = {
        this.btn_N1,
        this.btn_N2,
        this.btn_N3,
        this.btn_N4,
        this.btn_N5
    };
    //--------------------------------------------------------------------

    private int G_Values[] = {46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60};
    private final JToggleButton[] G = {
        this.btn_G1,
        this.btn_G2,
        this.btn_G3,
        this.btn_G4,
        this.btn_G5
    };
    //--------------------------------------------------------------------

    private int O_Values[] = {61, 62, 63, 64, 65, 66, 67, 68, 69, 70};
    private final JToggleButton[] O = {
        this.btn_O1,
        this.btn_O2,
        this.btn_O3,
        this.btn_O4,
        this.btn_O5
    };
    //--------------------------------------------------------------------
    //Array bidimensional
    private final JToggleButton[][] btnArray = {
        B, I, N, G, O
    };

    public Carta(String nombreDeljugador, boolean cartaLlena) {//Constructor de clase
        super();

        //Indicar el nombre del jugador
        lblJugadorNom.setText("Jugador : " + nombreDeljugador);
        _nombreDelJugador = nombreDeljugador;
        _esJuegoDeCartaLlena = cartaLlena;

        this.btnLimpiar.addActionListener((ActionEvent e) -> {
            limpiar();
        });

    }

    //Generar carta de bingo
    public void generarCartaDeBingo() {

        //Borra los valores de cada columna
        clearArray(B);
        clearArray(I);
        clearArray(N);
        clearArray(G);
        clearArray(O);

        //Asignarle valores a cada columna
        asignarValores("B", B, B_Values);
        asignarValores("I", I, I_Values);
        asignarValores("N", N, N_Values);
        asignarValores("G", G, G_Values);
        asignarValores("O", O, O_Values);

    }

    //Asignar valores a cada elememto de la lista de botones JToggleButton de la lista de una valores 
    private void asignarValores(String colName, JToggleButton btnLst[], int valLst[]) {

        //Recorer la lista de botones
        for (int idx = 0; idx < btnLst.length; idx++) {
            JToggleButton iButton = btnLst[idx]; //Obtener un elemento de la lista de la posicion actual de indx
            int iValor = 0;//Valor numerico que va tener el boton

            do {//Repetir hasta encontrar valor que no haiga sido asignado

                //Obtener un valor numerico de la lista aleatoriamente 
                iValor = obtenerValorDelIndex(getRndNum(valLst.length), valLst);

                //Verificar si el numero elegido ya fue asignado   
            } while (fueAsignado(iValor, btnLst));

            //Asignar el nombre y la posicion al boton actual
            iButton.setName(colName + "_" + idx);
            iButton.setText(String.valueOf(iValor));//Asignar el valor numerico al boton

            //Indicarle que el evento Click del boton se encuentra en esta clase
            iButton.addActionListener(this);

        }

    }

    //Comprueba si valor no fue asignado anteriormente en la lista
    private boolean fueAsignado(int valor, JToggleButton btnLst[]) {
        boolean asignado = false;

        for (JToggleButton btn : btnLst) {

            if (btn.getText() == String.valueOf(valor)) {
                asignado = true;
            }

        }

        return asignado;
    }

    //Borrar el texto de cada boton del array
    private void clearArray(JToggleButton arr[]) {

        for (JToggleButton iBtn : arr) {
            iBtn.setText(EMPTY);//Asignar un valor vacio al boton
        }

    }

    //Obtener un numero aleatoriamente entre 0 y el numero maximo especificado
    private int getRndNum(int max) {
        Random rnd = new Random();
        return rnd.nextInt(max);

    }

    //Retornar un objeto JToggleButton de la lista
    private JToggleButton obtenerButtonDelIndex(int index, JToggleButton btnLst[]) {
        return btnLst[index];
    }

    //Retornar un valor entero de la lista
    private int obtenerValorDelIndex(int index, int valLst[]) {
        return valLst[index];
    }

    //Obtener el texto formateado de cada celda
    private String obtenerTexto(int index, JToggleButton btnLst[]) {
        return String.format("[  %1$02d   ]", Integer.parseInt(btnLst[index].getText()));
    }

    //Retornar un texto con los valores de la carta que se genero
    @Override
    public String toString() {
        String strCarta = String.format("[  %1$2s   ][  %2$2s   ][  %3$2s   ][  %4$2s   ][  %5$2s   ]\n",
                "B", "I", "N", "G", "O");

        for (int idx = 0; idx < B.length; idx++) {

            strCarta
                    += obtenerTexto(idx, B)
                    + obtenerTexto(idx, I)
                    + obtenerTexto(idx, N)
                    + obtenerTexto(idx, G)
                    + obtenerTexto(idx, O) + "\n";

        }

        return strCarta;
    }

    //Limpiar tablero
    private void limpiar() {

        for (JToggleButton btnLst[] : btnArray) {

            for (JToggleButton iBtn : btnLst) {

                iBtn.setSelected(false);
                iBtn.setForeground(Color.BLACK);

            }

        }

    }

    //Verifica si gano en vertical
    private boolean ganoEnVertical(JToggleButton btnLst[]) {
        boolean gano = true;

        for (JToggleButton btn : btnLst) {//Recorre la lista de manera vertical            

            if (!btn.isSelected()) {
                return false;//Si uno de los items esta Desmarcados retornar false
            }

        }

        return gano;
    }

    //Verifica si gano en horizontal
    private boolean ganoEnHorizontal(int idx) {
        boolean gano = false;
        //Verificar si gano en cada columna horizontal en el que fue seleccionado 
        gano = (obtenerButtonDelIndex(idx, B).isSelected())
                & (obtenerButtonDelIndex(idx, I).isSelected())
                & (obtenerButtonDelIndex(idx, N).isSelected())
                & (obtenerButtonDelIndex(idx, G).isSelected())
                & (obtenerButtonDelIndex(idx, O).isSelected());

        return gano;
    }

    //Verifica si gano en diagonal
    private boolean ganoEnDiagonal() {
        //Verifica si gano de manera acostado "\"
        boolean gano = true;

        for (int idx = 0; idx < 5; idx++) {
            JToggleButton btn = btnArray[idx][idx];

            if (!btn.isSelected()) {
                gano = false;
                break;
            }

        }

        return gano;
    }

    //Verifica si gano en diagonal invertido
    private boolean ganoEnDiagonalInvertido() {
        //Verifica si gano de manera acostado invertido "/"
        boolean gano = true;

        //Array bidimensional
        for (int end = 4, top = 0; end >= 0; end--, top++) {
            JToggleButton btn = btnArray[end][top];

            if (!btn.isSelected()) {
                gano = false;
                break;
            }

        }

        return gano;
    }

    private boolean ganoEnTablaLlena() {
        boolean gano = true;

        for (JToggleButton btnLst[] : btnArray) {

            for (JToggleButton btn : btnLst) {

                if (!btn.isSelected()) {
                    gano = false;
                    break;
                }

            }

        }

        return gano;
    }

    //Verifica si gano
    private void verificarsiGano(JToggleButton btnPresionado) {

        if (btnPresionado.isSelected()) {//Si fue seleccionado
            boolean gano = false;
            String colNom = btnPresionado.getName().split("_")[0];
            int colIdx = Integer.parseInt(btnPresionado.getName().split("_")[1]);//Index del boton 

            //System.out.println("Button clicked : " + btnPresionado.getName());
            btnPresionado.setForeground(Color.red);
            btnPresionado.updateUI();

            switch (colNom) {//Verificar que columna fue seleccionada

                case "B":
                    //Verifica que no sea carta llena y que haiga ganado
                    gano = !_esJuegoDeCartaLlena & (ganoEnVertical(B) | ganoEnHorizontal(colIdx) | ganoEnDiagonal() | ganoEnDiagonalInvertido());
                    break;
                case "I":
                    gano = !_esJuegoDeCartaLlena & (ganoEnVertical(I) | ganoEnHorizontal(colIdx) | ganoEnDiagonal() | ganoEnDiagonalInvertido());
                    break;
                case "N":
                    gano = !_esJuegoDeCartaLlena & (ganoEnVertical(N) | ganoEnHorizontal(colIdx) | ganoEnDiagonal() | ganoEnDiagonalInvertido());
                    break;
                case "G":
                    gano = !_esJuegoDeCartaLlena & (ganoEnVertical(G) | ganoEnHorizontal(colIdx) | ganoEnDiagonal() | ganoEnDiagonalInvertido());
                    break;
                case "O":
                    gano = !_esJuegoDeCartaLlena & (ganoEnVertical(O) | ganoEnHorizontal(colIdx) | ganoEnDiagonal() | ganoEnDiagonalInvertido());
                    break;

            }

            //Mostrar si gano o si cuando es carta llena gano
            if (gano | (_esJuegoDeCartaLlena & ganoEnTablaLlena())) {
                System.out.println("El jugador " + _nombreDelJugador + " ha ganado!");
                new Runnable() {
                    @Override
                    public void run() {
                        JOptionPane.showMessageDialog(getContentPane(), "Felicidades " + _nombreDelJugador + " has ganado!", "Ganastes!", JOptionPane.INFORMATION_MESSAGE);
                    }
                }.run();
            }

        } else {

            btnPresionado.setForeground(Color.BLACK);
        }
    }

    //Evento que se ejecuta al  hacer click sobre el boton
    @Override
    public void actionPerformed(ActionEvent e) {

        verificarsiGano((JToggleButton) e.getSource());

    }

}
