package com.app.menu;

import com.app.SlangWordDictionary;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.*;

/**
 * com.app.menu
 * Create by Le Nguyen Tu Van
 * Date 12/22/2021 - 8:28 PM
 * Description: ...
 */
public class Menu {
    private JFrame frameMain;

    private JPanel panelCoMain;
    private JTable slangTable;
    private JButton addButton;
    private JButton editButton;
    private JButton deleteButton;
    private JTextField searchField;
    private JComboBox typeComboBox;
    private JButton searchButton;
    private JPanel panelMain;
    private JScrollPane slangScroll;
    private JButton historyButton;
    private JButton resetButton;
    private JButton randomButton;
    private JButton nextQuestionButton;
    private JButton button4;
    private JButton button5;
    private JButton button6;
    private JButton button7;
    private JLabel randomSlangField;
    private JLabel randomDefinitionField;
    private JComboBox typeGame;

    private String answer;

    public Menu() {
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addHistory();
                searchButtonAction();
            }
        });
        historyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                History history = new History();
                history.createAndShowGUI();
            }
        });
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddForm addForm = new AddForm();
                addForm.createAndShowGUI();
            }
        });
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = slangTable.getSelectedRow();
                if (row == -1) {
                    JOptionPane.showMessageDialog(null, "Select a slang word first!");
                } else {

                    String slangWord = (String) slangTable.getModel().getValueAt(row, 0);
                    String definition = (String) slangTable.getModel().getValueAt(row, 1);
                    JFrame confirmFrame = new JFrame("EXIT");
                    if (JOptionPane.showConfirmDialog(confirmFrame,
                            "Confirm if you want to delete slang word:\n" + slangWord + ": " + definition, "EXIT",
                            JOptionPane.YES_NO_OPTION) == JOptionPane.YES_NO_OPTION) {
                        SlangWordDictionary.removeSlangWord(slangWord, definition);
                        searchButtonAction();
                    }
                }
            }
        });
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = slangTable.getSelectedRow();
                if (row == -1) {
                    JOptionPane.showMessageDialog(null, "Select a slang word first!");
                } else {

                    String slangWord = (String) slangTable.getModel().getValueAt(row, 0);
                    String definition = (String) slangTable.getModel().getValueAt(row, 1);
                    EditForm editForm = new EditForm(slangWord, definition);
                    editForm.createAndShowGUI();
                }
            }
        });
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    SlangWordDictionary.resetDict();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                searchButtonAction();
            }
        });
        searchField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addHistory();
                searchButtonAction();
            }
        });
        randomButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                randomButtonAction();
            }
        });
        nextQuestionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                renderQuizGame();
            }
        });
    }

    private void renderQuizGame() {
        String type = (String) typeGame.getSelectedItem();

    }

    public void createAndShowGUI() {
        createAndShowSlangTable(SlangWordDictionary.slangDict);
        randomButtonAction();
        frameMain = new JFrame("Slang word dictionary");
        frameMain.setContentPane(panelMain);
        frameMain.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frameMain.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                JFrame cancelFrame = new JFrame("EXIT");
                if (JOptionPane.showConfirmDialog(cancelFrame, "Confirm if you want to exit", "EXIT",
                        JOptionPane.YES_NO_OPTION) == JOptionPane.YES_NO_OPTION) {
                    System.exit(0);
                }
            }
        });
        frameMain.setLocationRelativeTo(null);
        frameMain.pack();
        frameMain.setVisible(true);
    }

    public void createAndShowSlangTable(HashMap<String, List<String>> filteredSlangDict) {
        slangTable.setModel(new DefaultTableModel(null, new String[]{"Slang word", "Definition"}));

        DefaultTableModel model = (DefaultTableModel) slangTable.getModel();

        Set<Map.Entry<String, List<String>>> setSlangDict = filteredSlangDict.entrySet();

        Object[] row = new Object[2];
        for (Map.Entry<String, List<String>> slangDict : setSlangDict) {
            String slangWord = slangDict.getKey();
            List<String> definitions = slangDict.getValue();

            row[0] = slangWord;
            for (String definition : definitions) {
                row[1] = definition;
                model.addRow(row);
            }
        }

        TableColumnModel columns = slangTable.getColumnModel();
        columns.getColumn(0).setWidth(50);
        columns.getColumn(1).setMinWidth(150);
    }

    public void searchButtonAction() {
        String type = (String) typeComboBox.getSelectedItem();
        String keyword = searchField.getText();

        if (Objects.equals(type, "Slang Word")) {
            createAndShowSlangTable(SlangWordDictionary.findBySlangWord(keyword));
        } else {
            createAndShowSlangTable(SlangWordDictionary.findBuDefinition(keyword));
        }
    }

    public void randomButtonAction() {
        String randomSlangWord = SlangWordDictionary.getRandomSlang();
        String randomDefinition = SlangWordDictionary.getRandomDefinitionBySlang(randomSlangWord);
        randomSlangField.setText(randomSlangWord);
        randomDefinitionField.setText(randomDefinition);
    }

    public void addHistory() {
        String type = (String) typeComboBox.getSelectedItem();
        String keyword = searchField.getText();

        if (!Objects.equals(keyword, "")) {
            if (Objects.equals(type, "Slang Word")) {
                SlangWordDictionary.historySlangSearch += keyword + "\n";
            } else {
                SlangWordDictionary.historyDefinitionSearch += keyword + "\n";
            }
        }
    }
}
