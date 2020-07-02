package it.polimi.ingsw.PSP33.view.gui.components;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

/**
 * JPanel used to set a background image to a panel
 */
public class ImagePanel extends JPanel {

    /**
     * Image used as background
     */
    private Image img;

    public ImagePanel(String path) {
        InputStream imageStream = getImageInputStream(path);

        try {
            this.img = new ImageIcon(ImageIO.read(imageStream)).getImage();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Dimension size = new Dimension(img.getWidth(null), img.getHeight(null));
        setLayout(new GridBagLayout());
        setPreferredSize(size);
        setMinimumSize(size);
        setMaximumSize(size);
        setSize(size);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(img != null) {
            g.drawImage(img, 0, 0, null);
        }
    }

    /**
     * Method used to get input stream for an image
     * @param path path of the image in resources
     * @return input stream of the image
     */
    private InputStream getImageInputStream(String path) {
        return getClass().getResourceAsStream(path);
    }
}