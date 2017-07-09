/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import com.ctc.wstx.util.StringUtil;
import javax.swing.InputVerifier;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author rr
 */
public class StringAgeInputVerifier extends InputVerifier{

    @Override
    public boolean verify(JComponent input) {
        JTextField text = (JTextField) input;
        if(text.getText().trim().isEmpty()){
             JOptionPane.showMessageDialog(input, "Por favor, você DEVE inserir um valor!", "Error", JOptionPane.ERROR_MESSAGE); 
             return false;
        }
        try {
            Integer.parseInt(text.getText());
            return true;
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(input, "POR FAVOR, INSIRA UMA IDADE VÁLIDA!");
            return false;
        }
        
        
    }
    
    
}


