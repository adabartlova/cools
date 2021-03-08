package com.ada;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.sql.Time;
import java.text.AttributedCharacterIterator;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


public class Main {

    static BufferedImage bi;
    static BufferedImage bi2;
    static BufferedImage bi3;

    static boolean IsRunning;
    static Rectangle rectangle1;
    static Rectangle rectangle2;
    static Rectangle rectangle3;



   private static Window w = new Window(null)
        {
        @Override
        public void paint(Graphics g)
            {
                    Graphics2D g2 = (Graphics2D) g;
                    float opacity = 0.4f;
                    //g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacity));
                    g2.drawImage(bi, 915, 500, this);
                    g2.drawImage(bi2, 1330, 500, this);
                    g2.drawImage(bi3, 950, 455, this);
            }
        };



 public static void main(String[] args) throws AWTException, InterruptedException {

     Robot robot = null;
     try {
         robot = new Robot();
     } catch (AWTException awtException) {
         awtException.printStackTrace();
     }
     Rectangle rectangle1 = new Rectangle(915,1300,305,70);
     Rectangle rectangle2 = new Rectangle(1330,1300,305,70);
     Rectangle rectangle3 = new Rectangle(950,1255,250,45);

     IsRunning = true;

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
         TrayIcon trayIcon = new TrayIcon(image, "Cooldowns", popup);
         // set the TrayIcon properties
         //trayIcon.addActionListener(exitlistener);
         trayIcon.setPopupMenu(popup);
         // ...
         // add the tray image
         try {
             tray.add(trayIcon);
         } catch (AWTException e) {
             System.err.println(e);
         }
         // ...
     }

     //ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();

/*
     int delay = 100;
     Timer timer = new Timer(delay,drawlistener);
     timer.start();
*/



         for (;;) {
             //executorService.scheduleAtFixedRate(Main::drawCooldowns, 0, 100, TimeUnit.MILLISECONDS);
             Date date = new Date();
             DateFormat dateFormat = new SimpleDateFormat("SSS");
             String dateString = dateFormat.format(date);
             float f = Float.parseFloat(dateString);
             //System.out.println(i);

             //if ( ((f/500) == Math.round(f/500)) && (IsRunning) ) {
             if (IsRunning) {

                 //System.out.println(f/100);
                 //drawCooldowns();

                 bi = robot.createScreenCapture(rectangle1);
                 bi2 = robot.createScreenCapture(rectangle2);
                 bi3 = robot.createScreenCapture(rectangle3);

                 w.setAlwaysOnTop(true);
                 w.setBounds(w.getGraphicsConfiguration().getBounds());
                 w.setBackground(new Color(0, true));
                 w.setVisible(true);
                 w.setFocusable(false);

                 Thread.sleep(500);

             } else {
             }
         }



 }

}




