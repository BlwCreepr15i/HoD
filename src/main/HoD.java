package main;

import javax.swing.*;

public class HoD {
    public static void main(String[] args) {

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setTitle("Heart of Darkness");

        GamePanel game = new GamePanel();
        frame.add(game);
        frame.pack();

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        game.setupGame();
        game.startGameThread();
    }
}