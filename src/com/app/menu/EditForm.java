package com.app.menu;

import com.app.App;
import com.app.DictionaryUtil;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

/**
 * com.app.menu
 * Create by Le Nguyen Tu Van
 * Date 12/23/2021 - 8:32 PM
 * Description: ...
 */
public class EditForm {
    private JFrame frameMain;
    private JTextField textField1;
    private JTextField textField2;
    private JButton editButton;
    private JButton cancelButton;
    private JPanel panelMain;

    private final String oldSlangWord;
    private final String oldDefinition;

    public EditForm(String slangWord, String definition) {
        textField1.setText(slangWord);
        textField2.setText(definition);

        oldSlangWord = slangWord;
        oldDefinition = definition;

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frameMain.dispose();
            }
        });
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String slangWord = textField1.getText().toUpperCase();
                String definition = textField2.getText();
                if(!Objects.equals(slangWord, oldSlangWord) || !Objects.equals(definition, oldDefinition)) {
                    DictionaryUtil.removeSlangWord(oldSlangWord, oldDefinition);
                    DictionaryUtil.addSlangWord(slangWord, definition);
                    App.menu.searchButtonAction();
                }
                frameMain.dispose();
            }
        });
    }

    public void createAndShowGUI() {
        frameMain = new JFrame("Edit slang word");
        frameMain.setContentPane(panelMain);
        frameMain.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frameMain.setLocationRelativeTo(null);
        frameMain.pack();
        frameMain.setVisible(true);
    }
}
