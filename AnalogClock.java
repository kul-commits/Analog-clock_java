import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AnalogClock extends JFrame {
    private ClockPanel clockPanel;

    public AnalogClock() {
        setTitle("Analog Clock");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 300);
        setLocationRelativeTo(null);

        clockPanel = new ClockPanel();
        add(clockPanel);

        Timer timer = new Timer(1000, e -> {
            clockPanel.repaint();
        });
        timer.start();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            AnalogClock clock = new AnalogClock();
            clock.setVisible(true);
        });
    }
}

class ClockPanel extends JPanel {
    private int hour;
    private int minute;
    private int second;

    public ClockPanel() {
        setBackground(Color.BLACK);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Get current time
        getTime();

        // Set clock center and radius
        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;
        int radius = Math.min(getWidth(), getHeight()) / 2 - 10;

        // Draw clock face
        g.setColor(Color.RED);
        g.drawOval(centerX - radius, centerY - radius, 2 * radius, 2 * radius);

         // Draw numbers
         Font font = new Font("Arial", Font.PLAIN, 12);
         g.setFont(font);
         FontMetrics fm = g.getFontMetrics();
          for (int i = 1; i <= 12; i++) {
        double angle = Math.toRadians(30 * i);
        int x = (int) (centerX + (radius - 20) * Math.sin(angle));
        int y = (int) (centerY - (radius - 20) * Math.cos(angle));
        String number = Integer.toString(i);
        int stringWidth = fm.stringWidth(number);
        int stringHeight = fm.getAscent();
        g.drawString(number, x - stringWidth / 2, y + stringHeight / 2);
    }

        // Draw hour hand
        int hourHandLength = (int) (radius * 0.5);
        int hourX = (int) (centerX + hourHandLength * Math.sin(Math.toRadians(30 * hour + 0.5 * minute)));
        int hourY = (int) (centerY - hourHandLength * Math.cos(Math.toRadians(30 * hour + 0.5 * minute)));
        g.drawLine(centerX, centerY, hourX, hourY);

        // Draw minute hand
        int minuteHandLength = (int) (radius * 0.8);
        int minuteX = (int) (centerX + minuteHandLength * Math.sin(Math.toRadians(6 * minute + 0.1 * second)));
        int minuteY = (int) (centerY - minuteHandLength * Math.cos(Math.toRadians(6 * minute + 0.1 * second)));
        g.drawLine(centerX, centerY, minuteX, minuteY);

        // Draw second hand
        int secondHandLength = (int) (radius * 0.9);
        int secondX = (int) (centerX + secondHandLength * Math.sin(Math.toRadians(6 * second)));
        int secondY = (int) (centerY - secondHandLength * Math.cos(Math.toRadians(6 * second)));
        g.setColor(Color.RED);
        g.drawLine(centerX, centerY, secondX, secondY);
    }

    private void getTime() {
        Date now = new Date();
        SimpleDateFormat sdfHour = new SimpleDateFormat("hh");
        SimpleDateFormat sdfMinute = new SimpleDateFormat("mm");
        SimpleDateFormat sdfSecond = new SimpleDateFormat("ss");
        hour = Integer.parseInt(sdfHour.format(now));
        minute = Integer.parseInt(sdfMinute.format(now));
        second = Integer.parseInt(sdfSecond.format(now));
    }
}
