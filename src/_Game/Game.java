package _Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Game extends JFrame
{
    // ** Game Components **
    private static Game game;
    private static Battlefield battlefield;
    private static GUI graphics;

    private static int whosTurn = 1; // PlayerID determines turn

    // ** Players **
    private static Player[] players;

    public static void main(String[] args)
    {
        System.out.println("Welcome to Hearthstone!\n");

        // ** Creates the players
        players = new Player[]
                {
                        new BotPlayer(),  // opposing player ... id = 0
                        new InputPlayer() // 'you' player    ... id = 1
                };

        // ** Creates the game
        game = new Game();

        // ** Kick-starts the game
        players[whosTurn].playTurn();


    }

    public Game()
    {
        battlefield = new Battlefield();

        setBackground(new Color(28, 28, 29));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setResizable(true);

        graphics = new GUI();
        setContentPane(graphics);

        // End turn button
        JButton endTurn = new JButton("End Turn");
        endTurn.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                battlefield.getRow(0).endTurnsForAllMinions();
                battlefield.getRow(1).endTurnsForAllMinions();
                players[1].endTurn();
            }
        });

        graphics.add(endTurn);

        setVisible(true);

    }

    public static void outputMessage(String m)
    {
        JOptionPane.showMessageDialog(game, m);
    }

    public static void nextTurn() // Triggers the next turn
    {
        // Changes who's turn
        whosTurn = whosTurn == 1 ? 0 : 1;

        // Plays that player's turn
        players[whosTurn].playTurn();
    }

    public static Battlefield getBattlefield()
    {
        return battlefield;
    }

    public static GUI getGUI()
    {
        return graphics;
    }

    public static Player getPlayer(int id)
    {
        for (Player p : players)
        {
            if (p.getPlayerID() == id)
            {
                return p;
            }
        }

        return null;
    }
}
