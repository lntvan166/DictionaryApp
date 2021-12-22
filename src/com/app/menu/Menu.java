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
    private JButton button1;
    private JButton button2;
    private JButton button3;
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
                String type = (String) typeComboBox.getSelectedItem();
                if (Objects.equals(type, "Slang Word")) {
                    createAndShowSlangTable(SlangWordDictionary.findBySlangWord(searchField.getText()));
                } else {
                    createAndShowSlangTable(SlangWordDictionary.findBuDefinition(searchField.getText()));
                }
            }
        });
        historyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

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
                if(JOptionPane.showConfirmDialog(cancelFrame, "Confirm if you want to exit", "EXIT",
                        JOptionPane.YES_NO_OPTION)==JOptionPane.YES_NO_OPTION) {
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
            for(String definition: definitions) {
                row[1] = definition;
                model.addRow(row);
            }
        }

        TableColumnModel columns = slangTable.getColumnModel();
        columns.getColumn(0).setWidth(50);
        columns.getColumn(1).setMinWidth(150);
    }
}
