package it.polimi.ingsw.PSP33.view.gui;

import it.polimi.ingsw.PSP33.utils.enums.Color;
import it.polimi.ingsw.PSP33.view.gui.components.ImagePanel;
import it.polimi.ingsw.PSP33.view.gui.components.TextPanel;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

/**
 * Frame used in the setup phase before the game starts
 */
public class SetupFrame extends JFrame {

    private TextPanel textPanel;

    public SetupFrame() throws IOException {
        super("Santorini_Setup");
        setSize(610, 635);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        ImagePanel background = new ImagePanel("/setup_background.png");
        background.setLayout(new GridBagLayout());

        textPanel = new TextPanel("Welcome to Santorini",250,100,20);

        GridBagConstraints con = new GridBagConstraints();
        con.insets = new Insets(450,50,0,0);
        con.anchor = GridBagConstraints.PAGE_END;
        background.add(textPanel, con);

        add(background);

        setVisible(true);
    }

    /**
     * Method used to show a dialog to make the client select the type of connection
     * @return selection (1. Create Lobby 2. Join Lobby)
     */
    public int selectConnection() {
        String[] options = {"Create Lobby", "Join Lobby"};

        JOptionPane optionPane = new JOptionPane("How do you want to connect?", JOptionPane.QUESTION_MESSAGE, JOptionPane.YES_NO_CANCEL_OPTION,
                null, options, options[0]);
        lockClosure(this, optionPane);
        String res = (String) optionPane.getValue();
        int k = Arrays.asList(options).indexOf(res);
        k++;
        return k;

    }

    /**
     * Method used to show a dialog to make the client select the number of players for the new lobby
     * @return selection (1. 2 players 2. 3 players)
     */
    public int selectNumberOfPlayers() {
        String[] options = {"2 player", "3 player"};
        JOptionPane optionPane = new JOptionPane("How many players?",
                JOptionPane.QUESTION_MESSAGE, JOptionPane.YES_NO_OPTION,
                null, options, options[0]);
        lockClosure(this, optionPane);
        String res = (String) optionPane.getValue();
        int k = Arrays.asList(options).indexOf(res);
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

        colors = new String[colorList.size()];
        for(Color color : colorList) {
            colors[colorList.indexOf(color)] = color.name();
        }

        JOptionPane optionPane = new JOptionPane("Color selection",
                JOptionPane.PLAIN_MESSAGE, JOptionPane.DEFAULT_OPTION,null, colors);
        lockClosure(this, optionPane);
        String res = (String) optionPane.getValue();
        return Arrays.asList(colors).indexOf(res);
    }

    /**
     * Method used to show a dialog telling the client to wait for players
     */
    public void requestWait() {
        textPanel.setLabelText("Waiting for player...");
    }

    /**
     * Method used to remove the option to close a JOptionPane by clicking "X"
     * @param owner parent frame
     * @param optionPane option pane
     */
    public static void lockClosure(JFrame owner, JOptionPane optionPane){

        JDialog dialog = new JDialog(owner,
                "Click a button",
                true);
        dialog.setContentPane(optionPane);
        dialog.setDefaultCloseOperation(
                JDialog.DO_NOTHING_ON_CLOSE);
        optionPane.addPropertyChangeListener(e ->
        {
            String prop = e.getPropertyName();
            if (dialog.isVisible()
                    && (e.getSource() == optionPane)
                    && (JOptionPane.VALUE_PROPERTY.equals(prop))) {
                dialog.setVisible(false);
            }
        });
        dialog.pack();
        dialog.setLocationRelativeTo(owner);
        dialog.setResizable(false);
        dialog.setVisible(true);
    }
}
