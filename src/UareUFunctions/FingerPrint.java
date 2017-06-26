/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UareUFunctions;

import com.digitalpersona.uareu.Fmd;
import java.awt.Image;
import javax.swing.ImageIcon;

/**
 *
 * @author L R E
 */
public class FingerPrint {
    private static Fmd fingerPrint;
    //private static Image fingerPrintImage;
    private static byte[] fingerPrintImage;
    
//    public FingerPrint(Fmd fingerPrint){
//        this.fingerPrint = fingerPrint;
//    }
//    
//     
//    public Fmd getFingerPrint() {
//        return fingerPrint;
//    }
//
//    public void setFingerPrint(Fmd fingerPrint) {
//        this.fingerPrint = fingerPrint;
//    }
        
//    public static Image getFingerPrintImage() {
//        return fingerPrintImage;
//    }
//
//    public static void setFingerPrintImage(Image fingerPrintImage) {
//        FingerPrint.fingerPrintImage = fingerPrintImage;
//    }

    public static byte[] getFingerPrintImage() {
        return fingerPrintImage;
    }

    public static void setFingerPrintImage(byte[] fingerPrintImage) {
        FingerPrint.fingerPrintImage = fingerPrintImage;
    }
   
}
