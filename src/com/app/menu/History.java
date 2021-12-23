package com.app.menu;

import com.app.SlangWordDictionary;

import javax.swing.*;

/**
 * com.app.menu
 * Create by Le Nguyen Tu Van
 * Date 12/22/2021 - 11:19 PM
 * Description: ...
 */
public class History {

    private JFrame frameMain;
    private JPanel mainPanel;
    private JTextArea definitionArea;
    private JTextArea slangArea;

    public History() {
        slangArea.setText(SlangWordDictionary.historySlangSearch);
        definitionArea.setText(SlangWordDictionary.historyDefinitionSearch);
    }

    public void createAndShowGUI() {
        frameMain = new JFrame("App chat login");
        frameMain.setContentPane(mainPanel);
        frameMain.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frameMain.setLocationRelativeTo(null);
        frameMain.pack();
        frameMain.setVisible(true);
    }
}
