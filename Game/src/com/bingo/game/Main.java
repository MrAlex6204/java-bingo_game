/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bingo.game;

import com.bingo.game.controllers.Carta;
import com.bingo.game.controllers.PanelPrincipal;
import com.bingo.game.views.FramePanelPrincipal;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author 
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        //Abrir la ventana de Panel Principal
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                //Abrir
                PanelPrincipal pnlPrincipal  = new PanelPrincipal();
                pnlPrincipal.setLocationRelativeTo(null);
                pnlPrincipal.setVisible(true);
            }
        });
        
    }
       
    
}
