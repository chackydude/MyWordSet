import javax.swing.*;
import java.awt.*;

class DrawingComponent extends JPanel {
    int xg[] =  TimeMeasurements.x;
    int yg[] =  TimeMeasurements.y;
    int ng = TimeMeasurements.n;

    @Override
    protected void paintComponent(Graphics gh) {
        Graphics2D drp = (Graphics2D)gh;
        drp.drawLine(20, 640, 20, 20);
        drp.drawLine(20, 640, 460, 640);
        drp.drawPolyline(xg, yg, ng);
    }
}

public class TimeMeasurements extends JFrame {
    public static int x[] = new int[400];
    public static int y[] = new int[400];
    public static int n = 400;

    public TimeMeasurements() {
        super("arrWordSet test");
        WordSet ws = new WordSet();
        WordSet wws = new WordSet();
        WordSet wsw;

        int counter = 0;
        for (int i = 1;i<401;i++){
            x[i-1] = i + 20;
        }
        StringBuilder str = new StringBuilder("aaa");
        StringBuilder str1 = new StringBuilder("bbbbb");
        int amount = 100;

        for (int i=0;i<400;i++){

                String[] strs = new String[amount];
                String[] strs1 = new String[amount];

                for (int j = 0; j < amount; j++) {
                    strs[j] = "" + Math.random()*1000000;
                }

                long start = System.currentTimeMillis();

                WordSet test = new WordSet(strs);

                long finish = System.currentTimeMillis();

                amount += 10;

            long  timeConsumedMillis = finish - start;
            y[i] = 490 - (int) (timeConsumedMillis * 5);

            System.out.println(i);
        }
        JPanel jcp = new JPanel(new BorderLayout());
        setContentPane(jcp);
        jcp.add(new DrawingComponent(), BorderLayout.CENTER);
        jcp.setBackground(Color.gray);
        setSize(500, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        new TimeMeasurements().setVisible(true);
    }
}