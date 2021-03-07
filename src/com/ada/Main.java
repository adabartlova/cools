package com.ada;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.text.AttributedCharacterIterator;
import java.util.concurrent.TimeUnit;


public class Main {

    static BufferedImage bi;
    static BufferedImage bi2;
    static BufferedImage bi3;

    static boolean IsRunning;

 static Window w = new Window(null)
        {
        @Override
        public void paint(Graphics g)
            {
                if (IsRunning) {

                    Graphics2D g2 = (Graphics2D) g;
                    float opacity = 0.55f;
                    g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacity));
                    g2.drawImage(bi, 915, 750, this);
                    g2.drawImage(bi2, 1330, 750, this);
                    g2.drawImage(bi3, 950, 705, this);

                }

            }
        };

 public static void main(String[] args) throws AWTException, InterruptedException {

     IsRunning = true;

     TrayIcon trayIcon = null;
     if (SystemTray.isSupported()) {
         // get the SystemTray instance
         SystemTray tray = SystemTray.getSystemTray();
         // load an image
         Image image = Toolkit.getDefaultToolkit().getImage("src/icon.gif");
         // create a action listener to listen for default action executed on the tray icon
         ActionListener stoplistener = new ActionListener() {
             public void actionPerformed(ActionEvent e) {
                 IsRunning = false;
                 System.out.println("is not running");
             }
         };

         ActionListener startlistener = new ActionListener() {
             public void actionPerformed(ActionEvent e) {
                 IsRunning = true;
                 System.out.println("is running");
             }
         };

         ActionListener exitlistener = new ActionListener() {
             public void actionPerformed(ActionEvent e) {
                 Runtime.getRuntime().exit(0);
             }
         };

         // create a popup menu
         PopupMenu popup = new PopupMenu();
         // create menu item for the default action
         MenuItem stopItem = new MenuItem("Stop");
         MenuItem startItem = new MenuItem("Start");
         MenuItem exitItem = new MenuItem("Exit");
         stopItem.addActionListener(stoplistener);
         startItem.addActionListener(startlistener);
         exitItem.addActionListener(exitlistener);
         popup.add(startItem);
         popup.add(stopItem);
         popup.add(exitItem);
         /// ... add other items
         // construct a TrayIcon
         trayIcon = new TrayIcon(image, "Tray Demo", popup);
         // set the TrayIcon properties
         trayIcon.addActionListener(exitlistener);
         // ...
         // add the tray image
         try {
             tray.add(trayIcon);
         } catch (AWTException e) {
             System.err.println(e);
         }
         // ...
     } else {
         // disable tray option in your application or
         // perform other actions

     }


     if (IsRunning==true) {

         Robot robot = new Robot();
         Rectangle rectangle1 = new Rectangle(915,1300,305,70);
         Rectangle rectangle2 = new Rectangle(1330,1300,305,70);
         Rectangle rectangle3 = new Rectangle(950,1255,250,45);

         for (; ; ) {
             bi = robot.createScreenCapture(rectangle1);
             bi2 = robot.createScreenCapture(rectangle2);
             bi3 = robot.createScreenCapture(rectangle3);

             w.setAlwaysOnTop(true);
             w.setBounds(w.getGraphicsConfiguration().getBounds());
             w.setBackground(new Color(0, true));
             w.setVisible(true);
             w.setFocusable(false);

         }

     } else {

         w.dispose();
         w.setVisible(false);


     }
 }

}


