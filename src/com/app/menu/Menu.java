package com.app.menu;

import com.app.SlangWordDictionary;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
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
    private JButton randomSlangButton;
    private JPanel panelMain;
    private JScrollPane slangScroll;
    private JButton historyButton;

    public Menu() {
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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
                if (row==-1) {
                    JOptionPane.showMessageDialog(null, "Select a slang word first!");
                } else {

                    String slangWord = (String) slangTable.getModel().getValueAt(row, 0);
                    String definition = (String) slangTable.getModel().getValueAt(row, 1);
                    SlangWordDictionary.removeSlangWord(slangWord, definition);
                    searchButtonAction();
                }
            }
        });
    }

    public void createAndShowGUI() {
        createAndShowSlangTable(SlangWordDictionary.slangDict);
        frameMain = new JFrame("App chat login");
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

    void searchButtonAction() {
        String type = (String) typeComboBox.getSelectedItem();
        String keyword = searchField.getText();

        if (Objects.equals(type, "Slang Word")) {
            SlangWordDictionary.historySlangSearch += keyword + "\n";
            createAndShowSlangTable(SlangWordDictionary.findBySlangWord(keyword));
        } else {
            SlangWordDictionary.historyDefinitionSearch += keyword + "\n";
            createAndShowSlangTable(SlangWordDictionary.findBuDefinition(keyword));
        }
    }
}
