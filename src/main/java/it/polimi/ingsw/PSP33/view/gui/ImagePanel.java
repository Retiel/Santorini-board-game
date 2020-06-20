package it.polimi.ingsw.PSP33.view.gui;

import javax.swing.*;
import java.awt.*;

class ImagePanel extends JPanel {

    private Image img;

    public ImagePanel(String img) {
        this(new ImageIcon(img).getImage());
    }

    public ImagePanel(Image img) {
        this.img = img;
        Dimension size = new Dimension(img.getWidth(null), img.getHeight(null));
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