package com.ada;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.text.AttributedCharacterIterator;
import java.util.concurrent.TimeUnit;


public class Main {

    static BufferedImage bi;
    static BufferedImage bi2;
    static BufferedImage bi3;

 static Window w = new Window(null)
        {
        @Override
        public void paint(Graphics g)
            {
                g.drawImage(bi, 915, 720,this);
                g.drawImage(bi2, 1330, 720,this);
                g.drawImage(bi3,950,675,this);

            }
        };

 public static void main(String[] args) throws AWTException, InterruptedException {

     Robot robot = new Robot();
     Rectangle rectangle1 = new Rectangle(915,1300,305,70);
     Rectangle rectangle2 = new Rectangle(1330,1300,305,70);
     Rectangle rectangle3 = new Rectangle(950,1255,250,45);

     for (;;) {
         bi = robot.createScreenCapture(rectangle1);
         bi2 = robot.createScreenCapture(rectangle2);
         bi3 = robot.createScreenCapture(rectangle3);

         w.setAlwaysOnTop(true);
         w.setBounds(w.getGraphicsConfiguration().getBounds());
         w.setBackground(new Color(0, true));
         w.setVisible(true);

         //TimeUnit.MILLISECONDS.sleep(200);

     }
 }

}


