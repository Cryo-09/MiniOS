import arcadeos.QuizPanel;
import arcadeos.QuizManager;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import javax.imageio.ImageIO;

public class test_crane_visualizer {
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

        // 1. Module 5 (Cyber Crane) - FIRST FIT, Average Tier
        QuizManager.activeAllocationPolicy = "FIRST FIT";
        panel.selectModule(5);
        panel.setDifficulty(QuizManager.Difficulty.AVERAGE);
        mLoad.invoke(panel, 5, QuizManager.Difficulty.AVERAGE, QuizManager.Mode.FILL_MISSING, true);
        frame.validate();
        panel.validate();

        JTable table = (JTable) fTable.get(panel);
        // Select row 0, col 4 ("SOCKET ASSIGNED") -> Word options: Socket 1..5, NONE
        table.changeSelection(0, 4, false, false);
        frame.validate();
        panel.validate();

        BufferedImage imgFirstFit = new BufferedImage(1280, 800, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g1 = imgFirstFit.createGraphics();
        panel.printAll(g1);
        g1.dispose();
        ImageIO.write(imgFirstFit, "png", new File("C:/Users/User/.gemini/antigravity/brain/37bfbad8-9b12-4f39-9955-ef11582d3942/crane_first_fit_preview.png"));
        System.out.println("Rendered crane_first_fit_preview.png");

        // 2. BEST FIT
        QuizManager.activeAllocationPolicy = "BEST FIT";
        mLoad.invoke(panel, 5, QuizManager.Difficulty.AVERAGE, QuizManager.Mode.FILL_MISSING, true);
        table = (JTable) fTable.get(panel);
        table.changeSelection(1, 4, false, false);
        frame.validate();
        panel.validate();

        BufferedImage imgBestFit = new BufferedImage(1280, 800, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = imgBestFit.createGraphics();
        panel.printAll(g2);
        g2.dispose();
        ImageIO.write(imgBestFit, "png", new File("C:/Users/User/.gemini/antigravity/brain/37bfbad8-9b12-4f39-9955-ef11582d3942/crane_best_fit_preview.png"));
        System.out.println("Rendered crane_best_fit_preview.png");

        // 3. NEXT FIT
        QuizManager.activeAllocationPolicy = "NEXT FIT";
        mLoad.invoke(panel, 5, QuizManager.Difficulty.AVERAGE, QuizManager.Mode.FILL_MISSING, true);
        table = (JTable) fTable.get(panel);
        table.changeSelection(2, 6, false, false); // Col 6: ALLOCATION STATUS
        frame.validate();
        panel.validate();

        BufferedImage imgNextFit = new BufferedImage(1280, 800, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g3 = imgNextFit.createGraphics();
        panel.printAll(g3);
        g3.dispose();
        ImageIO.write(imgNextFit, "png", new File("C:/Users/User/.gemini/antigravity/brain/37bfbad8-9b12-4f39-9955-ef11582d3942/crane_next_fit_preview.png"));
        System.out.println("Rendered crane_next_fit_preview.png");

        System.exit(0);
    }
}
