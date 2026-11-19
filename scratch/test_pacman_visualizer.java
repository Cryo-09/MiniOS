import arcadeos.QuizPanel;
import arcadeos.QuizManager;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import javax.imageio.ImageIO;

public class test_pacman_visualizer {
    public static void main(String[] args) throws Exception {
        QuizPanel.soundEnabled = false;

        JFrame frame = new JFrame();
        frame.setUndecorated(true);
        QuizPanel panel = new QuizPanel(null);
        frame.getContentPane().add(panel);
        frame.setSize(1280, 800);
        frame.addNotify();
        panel.setBounds(0, 0, 1280, 800);

        Method mLoad = QuizPanel.class.getDeclaredMethod("loadQuestion", int.class, QuizManager.Difficulty.class, QuizManager.Mode.class, boolean.class);
        mLoad.setAccessible(true);

        Field fTable = QuizPanel.class.getDeclaredField("cipherTable");
        fTable.setAccessible(true);

        // 1. FCFS
        QuizManager.activeDiskPolicy = "FCFS";
        panel.selectModule(6);
        panel.setDifficulty(QuizManager.Difficulty.AVERAGE);
        mLoad.invoke(panel, 6, QuizManager.Difficulty.AVERAGE, QuizManager.Mode.FILL_MISSING, true);
        frame.validate();
        panel.validate();

        JTable table = (JTable) fTable.get(panel);
        // Select row 0, col 4 ("SEEK DIRECTION")
        table.changeSelection(0, 4, false, false);
        frame.validate();
        panel.validate();

        BufferedImage imgFcfs = new BufferedImage(1280, 800, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g1 = imgFcfs.createGraphics();
        panel.printAll(g1);
        g1.dispose();
        ImageIO.write(imgFcfs, "png", new File("C:/Users/User/.gemini/antigravity/brain/37bfbad8-9b12-4f39-9955-ef11582d3942/pacman_fcfs_preview.png"));
        System.out.println("Rendered pacman_fcfs_preview.png");

        // 2. SSTF
        QuizManager.activeDiskPolicy = "SSTF";
        mLoad.invoke(panel, 6, QuizManager.Difficulty.AVERAGE, QuizManager.Mode.FILL_MISSING, true);
        table = (JTable) fTable.get(panel);
        table.changeSelection(1, 3, false, false); // Col 3: TRACKS TRAVERSED
        frame.validate();
        panel.validate();

        BufferedImage imgSstf = new BufferedImage(1280, 800, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = imgSstf.createGraphics();
        panel.printAll(g2);
        g2.dispose();
        ImageIO.write(imgSstf, "png", new File("C:/Users/User/.gemini/antigravity/brain/37bfbad8-9b12-4f39-9955-ef11582d3942/pacman_sstf_preview.png"));
        System.out.println("Rendered pacman_sstf_preview.png");

        // 3. SCAN
        QuizManager.activeDiskPolicy = "SCAN";
        mLoad.invoke(panel, 6, QuizManager.Difficulty.AVERAGE, QuizManager.Mode.FILL_MISSING, true);
        table = (JTable) fTable.get(panel);
        table.changeSelection(2, 4, false, false);
        frame.validate();
        panel.validate();

        BufferedImage imgScan = new BufferedImage(1280, 800, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g3 = imgScan.createGraphics();
        panel.printAll(g3);
        g3.dispose();
        ImageIO.write(imgScan, "png", new File("C:/Users/User/.gemini/antigravity/brain/37bfbad8-9b12-4f39-9955-ef11582d3942/pacman_scan_preview.png"));
        System.out.println("Rendered pacman_scan_preview.png");

        // 4. C-SCAN
        QuizManager.activeDiskPolicy = "C-SCAN";
        mLoad.invoke(panel, 6, QuizManager.Difficulty.AVERAGE, QuizManager.Mode.FILL_MISSING, true);
        table = (JTable) fTable.get(panel);
        table.changeSelection(2, 5, false, false); // Col 5: CUMULATIVE SEEK
        frame.validate();
        panel.validate();

        BufferedImage imgCScan = new BufferedImage(1280, 800, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g4 = imgCScan.createGraphics();
        panel.printAll(g4);
        g4.dispose();
        ImageIO.write(imgCScan, "png", new File("C:/Users/User/.gemini/antigravity/brain/37bfbad8-9b12-4f39-9955-ef11582d3942/pacman_cscan_preview.png"));
        System.out.println("Rendered pacman_cscan_preview.png");

        // 5. LOOK
        QuizManager.activeDiskPolicy = "LOOK";
        mLoad.invoke(panel, 6, QuizManager.Difficulty.AVERAGE, QuizManager.Mode.FILL_MISSING, true);
        table = (JTable) fTable.get(panel);
        table.changeSelection(0, 3, false, false);
        frame.validate();
        panel.validate();

        BufferedImage imgLook = new BufferedImage(1280, 800, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g5 = imgLook.createGraphics();
        panel.printAll(g5);
        g5.dispose();
        ImageIO.write(imgLook, "png", new File("C:/Users/User/.gemini/antigravity/brain/37bfbad8-9b12-4f39-9955-ef11582d3942/pacman_look_preview.png"));
        System.out.println("Rendered pacman_look_preview.png");

        // 6. C-LOOK
        QuizManager.activeDiskPolicy = "C-LOOK";
        mLoad.invoke(panel, 6, QuizManager.Difficulty.AVERAGE, QuizManager.Mode.FILL_MISSING, true);
        table = (JTable) fTable.get(panel);
        table.changeSelection(3, 4, false, false);
        frame.validate();
        panel.validate();

        BufferedImage imgCLook = new BufferedImage(1280, 800, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g6 = imgCLook.createGraphics();
        panel.printAll(g6);
        g6.dispose();
        ImageIO.write(imgCLook, "png", new File("C:/Users/User/.gemini/antigravity/brain/37bfbad8-9b12-4f39-9955-ef11582d3942/pacman_clook_preview.png"));
        System.out.println("Rendered pacman_clook_preview.png");

        System.exit(0);
    }
}
