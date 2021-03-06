package com.app.menu;

import com.app.App;
import com.app.DictionaryUtil;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * com.app.menu
 * Create by Le Nguyen Tu Van
 * Date 12/23/2021 - 12:10 AM
 * Description: ...
 */
public class AddForm {
    private JFrame frameMain;
    private JTextField textField1;
    private JTextField textField2;
    private JButton addButton;
    private JButton cancelButton;
    private JPanel panelMain;

    public AddForm() {
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String slangWord = textField1.getText().toUpperCase();
                String definition = textField2.getText();
                DictionaryUtil.addSlangWord(slangWord, definition);
                App.menu.searchButtonAction();
                frameMain.dispose();
            }
        });
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frameMain.dispose();
            }
        });
    }

    public void createAndShowGUI() {
        frameMain = new JFrame("Add slang word");
        frameMain.setContentPane(panelMain);
        frameMain.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frameMain.setLocationRelativeTo(null);
        frameMain.pack();
        frameMain.setVisible(true);
    }
}
