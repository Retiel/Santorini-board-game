package it.polimi.ingsw.PSP33.view.gui.components;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

public class ImagePanel extends JPanel {

    private Image img;

    public ImagePanel(InputStream in) throws IOException {
        this(new ImageIcon(ImageIO.read(in)).getImage());
    }

    public ImagePanel(Image img) {
        this.img = img;
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
}