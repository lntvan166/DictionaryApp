package com.app.menu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * com.app.menu
 * Create by Le Nguyen Tu Van
 * Date 12/16/2021 - 9:02 PM
 * Description: ...
 */
public class Menu {
    public static JFrame menuFrame;
    private JButton findBySlangWordButton;
    private JButton findByDefinitionButton;
    private JButton cancelButton;
    private JButton searchHistoryButton;
    private JButton addSlangWordButton;
    private JButton editSlangWordButton;
    private JButton deleteSlangWordButton;
    private JButton resetSlangWordListButton;
    private JButton randomASlangWordButton;
    private JButton quizGameForSlangButton;
    private JButton quizGameForDefinitionButton;
    private JPanel panelMain;

    public Menu() {
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame cancelFrame = new JFrame("EXIT");
                if(JOptionPane.showConfirmDialog(cancelFrame, "Confirm if you want to exit", "EXIT",
                        JOptionPane.YES_NO_OPTION)==JOptionPane.YES_NO_OPTION) {
                    System.exit(0);
                }
            }
        });
    }

    public void start() {
        menuFrame = new JFrame("Dictionary");
        menuFrame.setContentPane(panelMain);
        menuFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        menuFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                JFrame cancelFrame = new JFrame("EXIT");
                if(JOptionPane.showConfirmDialog(cancelFrame, "Confirm if you want to exit", "EXIT",
                        JOptionPane.YES_NO_OPTION)==JOptionPane.YES_NO_OPTION) {
                    System.exit(0);
                }
            }
        });
        menuFrame.setLocationRelativeTo(null);
        menuFrame.pack();
        menuFrame.setVisible(true);
    }
}
