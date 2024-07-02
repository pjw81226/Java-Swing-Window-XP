package gallery;

import component.TitleBar;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Gallery extends JPanel {

    private final CardLayout cardLayout;
    private final JPanel photoPanel;
    private final List<String> imagePaths;
    private int currentIndex;

    public Gallery() {
        setLayout(new BorderLayout());
        TitleBar titleBar = new TitleBar("My Pictures", this::closePhoto);
        add(titleBar, BorderLayout.NORTH);

        cardLayout = new CardLayout();
        photoPanel = new JPanel(cardLayout);

        // List of image paths
        imagePaths = new ArrayList<>();
        imagePaths.add("/resources/image1.jpg");
        imagePaths.add("/resources/image2.jpeg");
        imagePaths.add("/resources/image3.png");
        imagePaths.add("/resources/image4.jpg");
        imagePaths.add("/resources/image5.jpg");
        imagePaths.add("/resources/image7.png");

        // Add images to the panel with custom ImagePanel for resizing
        for (String path : imagePaths) {
            ImageIcon originalIcon = new ImageIcon(getClass().getResource(path));
            ImagePanel imagePanel = new ImagePanel(originalIcon.getImage());
            photoPanel.add(imagePanel, path); // Add imagePanel with path as the identifier
        }
        add(photoPanel, BorderLayout.CENTER);

        JButton leftButton = new JButton("<");
        JButton rightButton = new JButton(">");

        leftButton.addActionListener(e -> {
            currentIndex = (currentIndex - 1 + imagePaths.size()) % imagePaths.size();
            cardLayout.show(photoPanel, imagePaths.get(currentIndex));
        });

        rightButton.addActionListener(e -> {
            currentIndex = (currentIndex + 1) % imagePaths.size();
            cardLayout.show(photoPanel, imagePaths.get(currentIndex));
        });

        JPanel navigationPanel = new JPanel(new BorderLayout());
        navigationPanel.add(leftButton, BorderLayout.WEST);
        navigationPanel.add(rightButton, BorderLayout.EAST);

        add(navigationPanel, BorderLayout.SOUTH);
    }

    private void closePhoto() {
        setVisible(false);
    }

    private class ImagePanel extends JPanel {
        private final Image image;

        public ImagePanel(Image image) {
            this.image = image;
            setPreferredSize(new Dimension(400, 500));
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D graphics2D = (Graphics2D) g.create();
            int x = (getWidth() - image.getWidth(null)) / 2;
            int y = (getHeight() - image.getHeight(null)) / 2;
            graphics2D.drawImage(image, x, y, this);
        }
    }

}
