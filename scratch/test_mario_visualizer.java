import arcadeos.QuizPanel;
import arcadeos.QuizManager;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import javax.imageio.ImageIO;

public class test_mario_visualizer {
    public static void main(String[] args) throws Exception {
        QuizPanel.soundEnabled = false;

        JFrame frame = new JFrame();
        frame.setUndecorated(true);
        QuizPanel panel = new QuizPanel(null);
        frame.getContentPane().add(panel);
        frame.setSize(1280, 800);
        frame.addNotify();
        panel.setBounds(0, 0, 1280, 800);

        // 1. Select Module 3 (Super Mario) - FIFO Policy, Average Tier
        QuizManager.activeCachePolicy = "FIFO";
        panel.selectModule(3);
        panel.setDifficulty(QuizManager.Difficulty.AVERAGE);
        frame.validate();
        panel.validate();

        Field fTable = QuizPanel.class.getDeclaredField("cipherTable");
        fTable.setAccessible(true);
        JTable table = (JTable) fTable.get(panel);

        // Select row 1, col 5 ("STATUS (H/F)") -> Word option: HIT / FAULT
        table.changeSelection(1, 5, false, false);
        frame.validate();
        panel.validate();

        BufferedImage imgFifo = new BufferedImage(1280, 800, BufferedImage.TYPE_INT_ARGB);
        Graphics2D gFifo = imgFifo.createGraphics();
        panel.printAll(gFifo);
        gFifo.dispose();
        ImageIO.write(imgFifo, "png", new File("C:/Users/User/.gemini/antigravity/brain/37bfbad8-9b12-4f39-9955-ef11582d3942/mario_fifo_preview.png"));
        System.out.println("Rendered mario_fifo_preview.png");

        // 2. Switch to LRU Policy
        QuizManager.activeCachePolicy = "LRU";
        Method mLoad = QuizPanel.class.getDeclaredMethod("loadQuestion", int.class, QuizManager.Difficulty.class, QuizManager.Mode.class, boolean.class);
        mLoad.setAccessible(true);
        mLoad.invoke(panel, 3, QuizManager.Difficulty.AVERAGE, QuizManager.Mode.FILL_MISSING, true);
        table.changeSelection(2, 5, false, false);
        frame.validate();
        panel.validate();

        BufferedImage imgLru = new BufferedImage(1280, 800, BufferedImage.TYPE_INT_ARGB);
        Graphics2D gLru = imgLru.createGraphics();
        panel.printAll(gLru);
        gLru.dispose();
        ImageIO.write(imgLru, "png", new File("C:/Users/User/.gemini/antigravity/brain/37bfbad8-9b12-4f39-9955-ef11582d3942/mario_lru_preview.png"));
        System.out.println("Rendered mario_lru_preview.png");

        // 3. Switch to OPTIMAL Policy
        QuizManager.activeCachePolicy = "OPTIMAL";
        mLoad.invoke(panel, 3, QuizManager.Difficulty.AVERAGE, QuizManager.Mode.FILL_MISSING, true);
        table.changeSelection(2, 5, false, false);
        frame.validate();
        panel.validate();

        BufferedImage imgOpt = new BufferedImage(1280, 800, BufferedImage.TYPE_INT_ARGB);
        Graphics2D gOpt = imgOpt.createGraphics();
        panel.printAll(gOpt);
        gOpt.dispose();
        ImageIO.write(imgOpt, "png", new File("C:/Users/User/.gemini/antigravity/brain/37bfbad8-9b12-4f39-9955-ef11582d3942/mario_optimal_preview.png"));
        System.out.println("Rendered mario_optimal_preview.png");

        frame.dispose();
        System.exit(0);
    }
}
