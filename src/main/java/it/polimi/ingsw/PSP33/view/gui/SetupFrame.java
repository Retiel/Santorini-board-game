package it.polimi.ingsw.PSP33.view.gui;

import it.polimi.ingsw.PSP33.utils.enums.Color;

import javax.swing.*;
import java.util.List;

public class SetupFrame extends JFrame {

    public SetupFrame() {
        super("Santorini_Setup");
        setSize(300, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        setVisible(true);
    }

    public int selectConnection() {
        String[] options = {"Create Lobby", "Join Lobby"};

        int k = JOptionPane.showOptionDialog(this, "How do you want to connect?",
                "Select connection", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,
                null, options, options[0]);
        k++;
        return k;
    }

    public int selectNumberOfPlayers() {
        String[] options = {"2 players", "3 players"};

        int k = JOptionPane.showOptionDialog(this, "How many players?",
                "Select number of players", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,
                null, options, options[0]);
        k++;
        return k;
    }

    public int selectLobby(List<String> lobbyList) {
        String[] lobbies;
        String[] options = {"OK"};

        lobbies = new String[lobbyList.size()];
        for(String s : lobbyList) {
            lobbies[lobbyList.indexOf(s)] = s;
        }

        JComboBox<String> comboBox = new JComboBox<>(lobbies);
        JOptionPane.showOptionDialog(this, comboBox, "Lobby selection",
                JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);

        return comboBox.getSelectedIndex();
    }

    public String selectName() {
        String name = JOptionPane.showInputDialog(this, "Type your name",
                "");

        if(name == null) {
            name = "";
        }

        return name;
    }

    public int selectColor(List<Color> colorList) {
        String[] colors;
        String[] options = {"OK"};

        colors = new String[colorList.size()];
        for(Color color : colorList) {
            colors[colorList.indexOf(color)] = color.name();
        }

        JComboBox<String> comboBox = new JComboBox<>(colors);
        JOptionPane.showOptionDialog(this, comboBox, "Color selection",
                JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);

        return comboBox.getSelectedIndex();
    }

    public void requestWait() {
        JOptionPane.showMessageDialog(this, "Waiting for players..");
    }
}
