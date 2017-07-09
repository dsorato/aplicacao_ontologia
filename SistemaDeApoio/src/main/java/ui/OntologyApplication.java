package ui;

import java.io.IOException;
import javax.swing.JOptionPane;
import model.Preferences;
import org.semanticweb.owlapi.model.OWLOntologyStorageException;

public class OntologyApplication {
    public static void main(String args[]) throws OWLOntologyStorageException, IOException, Exception {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form 
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainFrame().setVisible(true);
            }
        });*/
        MainFrame mf = new MainFrame();
        mf.setVisible(true);
        while (true) {      
            try {
                Thread.currentThread().sleep(1);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            if(mf.definido){
                mf.definido = false;
                //"false", "bancos", "17", "10000", "casa", "sim");
                String path = "./ontologia_aplicacao.owl";
                if(mf.path.isEmpty()){
                 //   JOptionPane.showMessageDialog(null, "VOCÊ NÃO SELECIONOU A ONTOLOGIA.");
                }
                Preferences pf = new Preferences(mf.tolerancia, mf.area, mf.idade, mf.salario, mf.opcao, mf.risco, path);
                pf.createProperties();
                
                
            }
        }
        
    }
}
