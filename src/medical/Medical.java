/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package medical;

import Citas.FrameCita;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author Yanir
 */
public class Medical {

    /**
     * @param args the command line arguments
     */
    

    
    
    public static void main(String[] args) {
        // TODO code application logic here
         
             try {
                 UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
             } catch (UnsupportedLookAndFeelException ex) {
                 Logger.getLogger(Medical.class.getName()).log(Level.SEVERE, null, ex);
             } catch (ClassNotFoundException ex) {
            Logger.getLogger(Medical.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(Medical.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Medical.class.getName()).log(Level.SEVERE, null, ex);
        }
        FrameCita cita = new FrameCita();
        cita.setVisible(true);
    }
    
}
