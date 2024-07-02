package paint;
import component.TitleBar;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;

public class Paint extends JPanel {
    private BufferedImage canvas;
    private Point startPoint = null;
    private boolean isEraserMode = false;

    public Paint() {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(500, 500));
        setBackground(Color.WHITE);

        TitleBar titleBar = new TitleBar("Paint", this::closePaint);
        add(titleBar, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton penButton = new JButton("Pen");
        JButton eraserButton = new JButton("Eraser");
        buttonPanel.add(penButton);
        buttonPanel.add(eraserButton);
        add(buttonPanel, BorderLayout.SOUTH);

        penButton.addActionListener(e -> isEraserMode = false);
        eraserButton.addActionListener(e -> isEraserMode = true);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                startPoint = e.getPoint();
            }
            @Override
            public void mouseReleased(MouseEvent e) {
                if (startPoint != null) {
                    if (isEraserMode) {
                        drawLine(startPoint, e.getPoint(), Color.WHITE, 10);
                    }
                    else {
                        drawLine(startPoint, e.getPoint(), Color.BLACK, 2);
                    }
                    startPoint = null;
                    repaint();
                }
            }
        });

        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (startPoint != null) {
                    if (isEraserMode) {
                        drawLine(startPoint, e.getPoint(), Color.WHITE, 10);
                    }
                    else {
                        drawLine(startPoint, e.getPoint(), Color.BLACK, 2);
                    }
                    startPoint = e.getPoint();
                    repaint();
                }
            }
        });
        loadCanvas();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (canvas == null) {
            canvas = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2d = canvas.createGraphics();
            g2d.setColor(Color.WHITE);
            g2d.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
            g2d.dispose();
        }
        g.drawImage(canvas, 0, 0, null);
    }

    private void drawLine(Point start, Point end, Color color, int size) {
        if (canvas == null) {
            canvas = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
        }
        Graphics2D g = canvas.createGraphics();
        g.setColor(color);
        g.setStroke(new BasicStroke(size));
        g.drawLine(start.x, start.y, end.x, end.y);
        g.dispose();
    }

    private void closePaint() {
        saveCanvas();
        this.setVisible(false);
    }

    private void saveCanvas() {
        try {
            File file = new File("paint_data.png");
            ImageIO.write(canvas, "png", file);
        } catch (IOException e) { }
    }

    private void loadCanvas() {
        try {
            File file = new File("paint_data.png");
            if (file.exists()) {
                canvas = ImageIO.read(file);
                repaint();
            }
        } catch (IOException e) { }
    }
}
