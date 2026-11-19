import arcadeos.QuizPanel;
import arcadeos.QuizManager;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.lang.reflect.Field;
import javax.imageio.ImageIO;

public class test_banker_visualizer {
    public static void main(String[] args) throws Exception {
        QuizPanel.soundEnabled = false;

        JFrame frame = new JFrame();
        frame.setUndecorated(true);
        QuizPanel panel = new QuizPanel(null);
        frame.getContentPane().add(panel);
        frame.setSize(1280, 800);
        frame.addNotify();
        panel.setBounds(0, 0, 1280, 800);

        // Test 1: Module 1 (Banker's Algorithm) Average Tier (5 clients)
        panel.selectModule(1);
        panel.setDifficulty(QuizManager.Difficulty.AVERAGE);
        frame.validate();
        panel.validate();

        Field fTable = QuizPanel.class.getDeclaredField("cipherTable");
        fTable.setAccessible(true);
        JTable table = (JTable) fTable.get(panel);

        // Select row 0, col 5 ("CAN SATISFY?") -> Word option
        table.changeSelection(0, 5, false, false);
        frame.validate();
        panel.validate();

        BufferedImage imgBankerWord = new BufferedImage(1280, 800, BufferedImage.TYPE_INT_ARGB);
        Graphics2D gWord = imgBankerWord.createGraphics();
        panel.printAll(gWord);
        gWord.dispose();
        ImageIO.write(imgBankerWord, "png", new File("C:/Users/User/.gemini/antigravity/brain/37bfbad8-9b12-4f39-9955-ef11582d3942/banker_word_selection_preview.png"));
        System.out.println("Rendered banker_word_selection_preview.png");

        // Select row 0, col 3 ("NEED") -> Number cell
        table.changeSelection(0, 3, false, false);
        frame.validate();
        panel.validate();

        BufferedImage imgBankerMath = new BufferedImage(1280, 800, BufferedImage.TYPE_INT_ARGB);
        Graphics2D gMath = imgBankerMath.createGraphics();
        panel.printAll(gMath);
        gMath.dispose();
        ImageIO.write(imgBankerMath, "png", new File("C:/Users/User/.gemini/antigravity/brain/37bfbad8-9b12-4f39-9955-ef11582d3942/banker_math_register_preview.png"));
        System.out.println("Rendered banker_math_register_preview.png");

        // Test 2: Easy Tier (3 clients)
        panel.setDifficulty(QuizManager.Difficulty.EASY);
        table.changeSelection(0, 5, false, false);
        frame.validate();
        panel.validate();

        BufferedImage imgBankerEasy = new BufferedImage(1280, 800, BufferedImage.TYPE_INT_ARGB);
        Graphics2D gEasy = imgBankerEasy.createGraphics();
        panel.printAll(gEasy);
        gEasy.dispose();
        ImageIO.write(imgBankerEasy, "png", new File("C:/Users/User/.gemini/antigravity/brain/37bfbad8-9b12-4f39-9955-ef11582d3942/banker_easy_vault_preview.png"));
        System.out.println("Rendered banker_easy_vault_preview.png");

        frame.dispose();
        System.exit(0);
    }
}
