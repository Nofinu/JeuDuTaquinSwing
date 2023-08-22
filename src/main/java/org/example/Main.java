package org.example;

import org.example.Layout.TaquinLayout;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame jFrame = new JFrame("DemoLayout");
                jFrame.setSize(1080,900);
                jFrame.setContentPane(new TaquinLayout().getJPanel());
                jFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                jFrame.setVisible(true);
            }
        });
    }
}