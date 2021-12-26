package com.app.menu;

import com.app.DictionaryUtil;
import com.app.util.AppUtil;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.*;
import java.util.List;

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
    private JButton answerAButton;
    private JButton answerBButton;
    private JButton answerCButton;
    private JButton answerDButton;
    private JLabel randomSlangField;
    private JLabel randomDefinitionField;
    private JComboBox typeGame;
    private JLabel questionField;
    private JPanel quizPanel;

    private String slangAnswer;
    private String definitionAnswer;

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
                        DictionaryUtil.removeSlangWord(slangWord, definition);
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
                    DictionaryUtil.resetDict();
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
        answerAButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkAnswer(answerAButton.getText());
            }
        });
        answerBButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkAnswer(answerBButton.getText());
            }
        });
        answerCButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkAnswer(answerCButton.getText());
            }
        });
        answerDButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkAnswer(answerDButton.getText());
            }
        });
        typeGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                renderQuizGame();
            }
        });
    }

    public void renderQuizGame() {
        String type = (String) typeGame.getSelectedItem();

        String newSlang = DictionaryUtil.getRandomSlang();

        while (Objects.equals(newSlang, slangAnswer)) {
            newSlang = DictionaryUtil.getRandomSlang();
        }

        slangAnswer = newSlang;
        definitionAnswer = DictionaryUtil.getRandomDefinitionBySlang(slangAnswer);

        Random r = new Random();
        int answer = r.nextInt(4);

        if (Objects.equals(type, "Slang Word")) {
            questionField.setText("     Find definition for '" + slangAnswer + "'");
            List<String> definition = DictionaryUtil.get4RandomDefinition(slangAnswer);
            definition.set(answer, definitionAnswer);
            answerAButton.setText(definition.get(0));
            answerBButton.setText(definition.get(1));
            answerCButton.setText(definition.get(2));
            answerDButton.setText(definition.get(3));
        } else {
            questionField.setText("     Find slang word for '" + definitionAnswer + "'");
            List<String> slangWord = DictionaryUtil.get4RandomSlang(slangAnswer);
            slangWord.set(answer, slangAnswer);
            answerAButton.setText(slangWord.get(0));
            answerBButton.setText(slangWord.get(1));
            answerCButton.setText(slangWord.get(2));
            answerDButton.setText(slangWord.get(3));
        }

    }

    public void checkAnswer(String answer) {
        String type = (String) typeGame.getSelectedItem();
        if (Objects.equals(type, "Slang Word")) {
            if (Objects.equals(answer, definitionAnswer)) {
                JOptionPane.showMessageDialog(quizPanel, "Correct answer!");
            } else {
                JOptionPane.showMessageDialog(quizPanel, "Wrong answer!");
            }
        } else {
            if (Objects.equals(answer, slangAnswer)) {
                JOptionPane.showMessageDialog(quizPanel, "Correct answer!");
            } else {
                JOptionPane.showMessageDialog(quizPanel, "Wrong answer!");
            }
        }
    }

    public void createAndShowGUI() {
        createAndShowSlangTable(DictionaryUtil.slangDict);
        randomButtonAction();
        renderQuizGame();
        frameMain = new JFrame("Slang word dictionary");
        frameMain.setContentPane(panelMain);
        frameMain.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frameMain.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                JFrame cancelFrame = new JFrame("EXIT");
                if (JOptionPane.showConfirmDialog(cancelFrame, "Confirm if you want to exit", "EXIT",
                        JOptionPane.YES_NO_OPTION) == JOptionPane.YES_NO_OPTION) {
                    try {
                        AppUtil.serializeDictionary();
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(null, ex);
                    }
                    System.exit(0);
                }
            }
        });
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int height = screenSize.height;
        int width = screenSize.width;
        frameMain.setSize(width/2, height/2);

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
            createAndShowSlangTable(DictionaryUtil.findBySlangWord(keyword));
        } else {
            createAndShowSlangTable(DictionaryUtil.findBuDefinition(keyword));
        }
    }

    public void randomButtonAction() {
        String randomSlangWord = DictionaryUtil.getRandomSlang();
        String randomDefinition = DictionaryUtil.getRandomDefinitionBySlang(randomSlangWord);
        randomSlangField.setText(randomSlangWord);
        randomDefinitionField.setText(randomDefinition);
    }

    public void addHistory() {
        String type = (String) typeComboBox.getSelectedItem();
        String keyword = searchField.getText();

        if (!Objects.equals(keyword, "")) {
            if (Objects.equals(type, "Slang Word")) {
                DictionaryUtil.historySlangSearch += keyword + "\n";
            } else {
                DictionaryUtil.historyDefinitionSearch += keyword + "\n";
            }
        }
    }
}
