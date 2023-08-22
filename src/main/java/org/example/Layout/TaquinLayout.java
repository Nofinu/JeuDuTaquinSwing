package org.example.Layout;

import lombok.Data;

import javax.accessibility.AccessibleContext;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Data
public class TaquinLayout {

    private GridBagLayout gridBagLayout;
    private JPanel jPanel;
    private JButton jButton;

    private final List<String> list = Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8","");
    private List<String> listShuffle = new ArrayList<>();


    private Double firstValue;
    private Double secondValue;
    private String methode;

    public TaquinLayout() {
        jPanel = new JPanel();
        gridBagLayout = new GridBagLayout();
        jPanel.setLayout(gridBagLayout);
        affichage(list);
        AccessibleContext accessibleContext = jPanel.getAccessibleContext();
        jPanel.getComponent(0);
    }

    private void shuffle() {
        List<String> listtmp = new ArrayList<>(list.stream().toList());
        listShuffle = new ArrayList<>();
        while (!listtmp.isEmpty()) {
            int rdmValue = (int) (Math.random() * ((listtmp.size())));
            listShuffle.add(listtmp.get(rdmValue));
            listtmp.remove(rdmValue);
        }

        for (int i = 0; i < listShuffle.size(); i++) {
            ((JButton) jPanel.getComponent(i + 1)).setText(listShuffle.get(i));
        }
        affichage(listShuffle);
        ((JButton)jPanel.getComponent(0)).setText("Shuffle");
        jPanel.getComponent(0).setBackground(Color.BLACK);
        jPanel.getComponent(0).setForeground(Color.RED);
    }

    private void affichage(List<String> list) {
        GridBagConstraints c = new GridBagConstraints();

        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1;
        c.weighty = 0.5;
        c.gridy = 3;
        c.gridx = 0;
        c.gridwidth = 3;

        jButton = new JButton("shuffle");
        jButton.setFont(new Font("name", 1, 25));
        jButton.setForeground(Color.red);
        jButton.setBackground(Color.BLACK);
        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                shuffle();
            }
        });

        jPanel.add(jButton, c);
        gridBagLayout.columnWidths = new int[3];
        gridBagLayout.rowHeights = new int[4];


        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1;
        c.weighty = 1;
        c.gridwidth = 1;

        buttonListCreation(list, c);
    }

    private void buttonListCreation(List<String> list, GridBagConstraints c) {
        int cpt = 0;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                c.gridy = i;
                c.gridx = j;
                if (cpt < list.size()) {
                    jButton = new JButton(list.get(cpt));
                    jButton.setFont(new Font("name", 1, 25));
                    jButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                           actionButton(e);
                        }
                    });
                    cpt++;
                    jPanel.add(jButton, c);
                }

            }
        }
    }

    private void echangeBtn (int btnActeurId, int btnEmptyId){
        JButton btnActeur = (JButton) jPanel.getComponent(btnActeurId);
        JButton btnEmpty = (JButton) jPanel.getComponent(btnEmptyId);

        btnEmpty.setText(btnActeur.getText());
        btnActeur.setText("");
    }

    private boolean isSucces (){
        List<String> listSucces = new ArrayList<>();
        for (int i =1; i<=list.size();i++){
            listSucces.add(((JButton)jPanel.getComponent(i)).getText());
        }
        return list.equals(listSucces);
    }


    private void actionButton (ActionEvent e){

        int keyPush = 0;

        for (int i = 1; i <= 9; i++) {
            if (((JButton) e.getSource()).equals(jPanel.getComponent(i))) {
                keyPush = i;
                break;
            }
        }

        if(keyPush<=3){
            switch(keyPush){
                case 1:
                    cornerTopLeft(keyPush);
                    break;
                case 3:
                    cornerTopRight (keyPush);
                    break;
                default:
                    topMid(keyPush);
                    break;
            }
        }
        else if(keyPush<=6){
            switch(keyPush){
                case 4:
                    leftMid(keyPush);
                    break;
                case 6:
                    rightMid(keyPush);
                    break;
                default:
                    midButton(keyPush);
                    break;
            }
        }
        else if (keyPush<=9){
            switch(keyPush){
                case 7:
                    cornerBottomLeft (keyPush);
                    break;
                case 9:
                    cornerBottomRight(keyPush);
                    break;
                default:
                    bottomMid (keyPush);
                    break;
            }
        }
        if(isSucces()){
            ((JButton)jPanel.getComponent(0)).setText("Restart");
            jPanel.getComponent(0).setBackground(Color.GREEN);
            jPanel.getComponent(0).setForeground(Color.BLACK);

        }
    }

    private void cornerTopLeft (int btnActeur){
        if(((JButton)jPanel.getComponent(btnActeur+1)).getText().equals("") ){
            echangeBtn(btnActeur,btnActeur+1);
        }
        else if (((JButton)jPanel.getComponent(btnActeur+3)).getText().equals("")){
            echangeBtn(btnActeur,btnActeur+3);
        }
    }
    private void cornerBottomLeft (int btnActeur){
        if(((JButton)jPanel.getComponent(btnActeur+1)).getText().equals("")){
            echangeBtn(btnActeur,btnActeur+1);
        }
        else if (((JButton)jPanel.getComponent(btnActeur-3)).getText().equals("")){
            echangeBtn(btnActeur,btnActeur-3);
        }
    }
    private void cornerTopRight (int btnActeur){
        if(((JButton)jPanel.getComponent(btnActeur-1)).getText().equals("")){
            echangeBtn(btnActeur,btnActeur-1);
        }
        else if (((JButton)jPanel.getComponent(btnActeur+3)).getText().equals("")){
            echangeBtn(btnActeur,btnActeur+3);
        }
    }
    private void cornerBottomRight (int btnActeur){
        if(((JButton)jPanel.getComponent(btnActeur-1)).getText().equals("")){
            echangeBtn(btnActeur,btnActeur-1);
        }
        else if (((JButton)jPanel.getComponent(btnActeur-3)).getText().equals("")){
            echangeBtn(btnActeur,btnActeur-3);
        }
    }
    private void topMid (int btnActeur){
        if(((JButton)jPanel.getComponent(btnActeur-1)).getText().equals("")){
            echangeBtn(btnActeur,btnActeur-1);
        }
        else if (((JButton)jPanel.getComponent(btnActeur+1)).getText().equals("")){
            echangeBtn(btnActeur,btnActeur+1);
        }
        else if (((JButton)jPanel.getComponent(btnActeur+3)).getText().equals("")){
            echangeBtn(btnActeur,btnActeur+3);
        }
    }
    private void leftMid(int btnActeur){
        if(((JButton)jPanel.getComponent(btnActeur+1)).getText().equals("")){
            echangeBtn(btnActeur,btnActeur+1);
        }
        else if (((JButton)jPanel.getComponent(btnActeur+3)).getText().equals("")){
            echangeBtn(btnActeur,btnActeur+3);
        }
        else if (((JButton)jPanel.getComponent(btnActeur-3)).getText().equals("")){
            echangeBtn(btnActeur,btnActeur-3);
        }
    }
    private void midButton(int btnActeur){
        if(((JButton)jPanel.getComponent(btnActeur+1)).getText().equals("")){
            echangeBtn(btnActeur,btnActeur+1);
        }
        else if(((JButton)jPanel.getComponent(btnActeur-1)).getText().equals("")){
            echangeBtn(btnActeur,btnActeur-1);
        }
        else if (((JButton)jPanel.getComponent(btnActeur+3)).getText().equals("")){
            echangeBtn(btnActeur,btnActeur+3);
        }
        else if (((JButton)jPanel.getComponent(btnActeur-3)).getText().equals("")){
            echangeBtn(btnActeur,btnActeur-3);
        }
    }
    private void rightMid(int btnActeur){
        if(((JButton)jPanel.getComponent(btnActeur-1)).getText().equals("")){
            echangeBtn(btnActeur,btnActeur-1);
        }
        else if (((JButton)jPanel.getComponent(btnActeur+3)).getText().equals("")){
            echangeBtn(btnActeur,btnActeur+3);
        }
        else if (((JButton)jPanel.getComponent(btnActeur-3)).getText().equals("")){
            echangeBtn(btnActeur,btnActeur-3);
        }
    }
    private void bottomMid (int btnActeur){
        if(((JButton)jPanel.getComponent(btnActeur-1)).getText().equals("")){
            echangeBtn(btnActeur,btnActeur-1);
        }
        else if (((JButton)jPanel.getComponent(btnActeur+1)).getText().equals("")){
            echangeBtn(btnActeur,btnActeur+1);
        }
        else if (((JButton)jPanel.getComponent(btnActeur-3)).getText().equals("")){
            echangeBtn(btnActeur,btnActeur-3);
        }

    }
}
