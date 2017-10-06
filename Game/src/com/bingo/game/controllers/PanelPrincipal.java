/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bingo.game.controllers;

import com.bingo.game.views.FramePanelPrincipal;
import java.awt.AWTEventMulticaster;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.*;

/**
 *
 * @author
 */
public class PanelPrincipal extends FramePanelPrincipal implements ActionListener {

    //Lista de jugadores 
    List<Carta> jugadoresLst = new ArrayList<>();

    public PanelPrincipal() {
        super();

        //Indicarle que el evento click del boton esta en esta clase
        btnJugar.addActionListener(this);

    }
    
    
    

    private void agregarJugador(String nombreDelJugador) {
        
        final Carta crtaBingo = new Carta(nombreDelJugador,this.chkTablaLlena.isSelected());
        
        Iterator<Carta> iCarta = jugadoresLst.iterator() ;
        
        do{//Repetir mientras hasta que se encuentre una carta de Bingo que no exista
            
            crtaBingo.generarCartaDeBingo();//Generar la carta del bingo
                    
        }while(crtaBingo.toString().equals(iCarta.toString()));
            
        jugadoresLst.add(crtaBingo);//Agregar carta a la lista
        
        //Mostrar en la consola la carta generada
        System.out.println(
                            "Jugador : "+nombreDelJugador + "\n"+
                            "Carta de bingo generada : \n"+
                             crtaBingo.toString()
        );
        crtaBingo.setLocationRelativeTo(this);
        crtaBingo.setVisible(true);//Mostrar carta de Bingo

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
           String nombre = null;                
                nombre  = JOptionPane.showInputDialog(this, "Nombre","Teclee su nombre para jugar",JOptionPane.INFORMATION_MESSAGE);
                
                if(nombre==null ||nombre.equals("")){
                    JOptionPane.showMessageDialog(this,"Nombre invalido!" , "Teclee el nombre",JOptionPane.ERROR_MESSAGE);
                }else{
                    agregarJugador(nombre);//Agregar jugador
                }
        
        
    }

}
