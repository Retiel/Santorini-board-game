package it.polimi.ingsw.PSP33.view.gui;

import it.polimi.ingsw.PSP33.utils.enums.Color;

import javax.swing.*;
import java.util.List;

/**
 * Frame used in the setup phase before the game starts
 */
public class SetupFrame extends JFrame {

    public SetupFrame() {
        super("Santorini_Setup");
        setSize(300, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        setVisible(true);
    }

    /**
     * Method used to show a dialog to make the client select the type of connection
     * @return selection (1. Create Lobby 2. Join Lobby)
     */
    public int selectConnection() {
        String[] options = {"Create Lobby", "Join Lobby"};

        int k = JOptionPane.showOptionDialog(this, "How do you want to connect?",
                "Select connection", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,
                null, options, options[0]);
        k++;
        return k;
    }

    /**
     * Method used to show a dialog to make the client select the number of players for the new lobby
     * @return selection (1. 2 players 2. 3 players)
     */
    public int selectNumberOfPlayers() {
        String[] options = {"2 players", "3 players"};

        int k = JOptionPane.showOptionDialog(this, "How many players?",
                "Select number of players", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,
                null, options, options[0]);
        k++;
        return k;
    }

    /**
     * Method used to show a dialog to make the client select the lobby
     * @param lobbyList list of available lobbies
     * @return selection (lobby's index of the list of available lobbies)
     */
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

    /**
     * Method used to show a dialog to make the client select his name
     * @return selected name
     */
    public String selectName() {
        String name = JOptionPane.showInputDialog(this, "Type your name",
                "");

        if(name == null) {
            name = "";
        }

        return name;
    }

    /**
     * Method used to show a dialog to make the client select his color
     * @param colorList list of available colors
     * @return selection (color's index of list of available colors)
     */
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

    /**
     * Method used to show a dialog telling the client to wait for players
     */
    public void requestWait() {
        JOptionPane.showMessageDialog(this, "Waiting for players..");
    }
}
