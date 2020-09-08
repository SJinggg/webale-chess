package me.samoa.chess.view;

import me.samoa.chess.model.*;
import java.awt.event.*;
import javax.swing.*;

public class ButtonListener implements MouseListener {
    public void mousePressed(MouseEvent evt) {
        JButton clicked = (JButton)evt.getSource();

        System.out.println(clicked.getName());
        //perform movement here...? // listener not inside control
         
        
    }

    public void mouseClicked(MouseEvent evt){}
    public void mouseEntered(MouseEvent evt){}
    public void mouseExited(MouseEvent evt){}
    public void mouseReleased(MouseEvent evt){}
}