import arcadeos.QuizPanel;
import arcadeos.QuizManager;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import javax.imageio.ImageIO;

public class test_overcooked_trace {
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

        // 1. AVERAGE TIER - Select EVENT (Col 1) -> Word selector
        panel.selectModule(7);
        panel.setDifficulty(QuizManager.Difficulty.AVERAGE);
        mLoad.invoke(panel, 7, QuizManager.Difficulty.AVERAGE, QuizManager.Mode.FILL_MISSING, true);
        frame.validate();
        panel.validate();

        JTable table = (JTable) fTable.get(panel);
        // Find a blank cell in EVENT (col 1) or select row 1, col 1
        table.changeSelection(1, 1, false, false);
        frame.validate();
        panel.validate();

        BufferedImage imgEvent = new BufferedImage(1280, 800, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g1 = imgEvent.createGraphics();
        panel.printAll(g1);
        g1.dispose();
        ImageIO.write(imgEvent, "png", new File("C:/Users/User/.gemini/antigravity/brain/37bfbad8-9b12-4f39-9955-ef11582d3942/overcooked_trace_event_preview.png"));
        System.out.println("Rendered overcooked_trace_event_preview.png");

        // 2. Select %CPU / JOB (Col 3) -> Math Register
        table.changeSelection(1, 3, false, false);
        frame.validate();
        panel.validate();

        BufferedImage imgMath = new BufferedImage(1280, 800, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = imgMath.createGraphics();
        panel.printAll(g2);
        g2.dispose();
        ImageIO.write(imgMath, "png", new File("C:/Users/User/.gemini/antigravity/brain/37bfbad8-9b12-4f39-9955-ef11582d3942/overcooked_trace_math_preview.png"));
        System.out.println("Rendered overcooked_trace_math_preview.png");

        // 3. EXPERT TIER
        panel.setDifficulty(QuizManager.Difficulty.EXPERT);
        mLoad.invoke(panel, 7, QuizManager.Difficulty.EXPERT, QuizManager.Mode.FILL_MISSING, true);
        table = (JTable) fTable.get(panel);
        table.changeSelection(2, 5, false, false); // Col 5: hw/job
        frame.validate();
        panel.validate();

        BufferedImage imgExpert = new BufferedImage(1280, 800, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g3 = imgExpert.createGraphics();
        panel.printAll(g3);
        g3.dispose();
        ImageIO.write(imgExpert, "png", new File("C:/Users/User/.gemini/antigravity/brain/37bfbad8-9b12-4f39-9955-ef11582d3942/overcooked_trace_expert_preview.png"));
        System.out.println("Rendered overcooked_trace_expert_preview.png");
    }
}
