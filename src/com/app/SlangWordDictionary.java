package com.app;

import javax.swing.*;
import java.io.IOException;
import java.util.*;

/**
 * com.app
 * Create by Le Nguyen Tu Van
 * Date 12/22/2021 - 7:22 PM
 * Description: ...
 */
public class SlangWordDictionary {

    public static HashMap<String, List<String>> slangDict;
    public static HashMap<String, List<String>> rootSlangDict = new HashMap<>();
    public static String historySlangSearch = "";
    public static String historyDefinitionSearch = "";

    public static HashMap<String, List<String>> findBySlangWord(String slangWordToFind) {
        HashMap<String, List<String>> slangMatch = new HashMap<>();

        Set<Map.Entry<String, List<String>>> setSlangWordList = slangDict.entrySet();

        for (Map.Entry<String, List<String>> slangWord : setSlangWordList) {
            String key = slangWord.getKey();
            List<String> value = slangWord.getValue();
            if (key.toLowerCase().contains(slangWordToFind.toLowerCase())) {
                slangMatch.put(key, value);
            }
        }

        return slangMatch;
    }

    public static HashMap<String, List<String>> findBuDefinition(String definitionToFind) {
        HashMap<String, List<String>> slangMatch = new HashMap<>();

        Set<Map.Entry<String, List<String>>> setSlangWordList = SlangWordDictionary.slangDict.entrySet();


        for (Map.Entry<String, List<String>> slangWord : setSlangWordList) {
            String key = slangWord.getKey();
            List<String> newValues = new ArrayList<>();
            List<String> values = slangWord.getValue();
            for (String value : values) {

                if (value.toLowerCase().contains(definitionToFind.toLowerCase())) {
                    newValues.add(value);
                }
            }
            slangMatch.put(key, newValues);
        }


        return slangMatch;
    }

    public static void resetDict() throws IOException {
        slangDict.clear();
        slangDict.putAll(rootSlangDict);
    }

    public static void addSlangWord(String slangWord, String definition) {
        List<String> values = new ArrayList<>();
        if (slangDict.containsKey(slangWord)) {
            List<String> currentValues = slangDict.get(slangWord);
            values.addAll(currentValues);
            if (values.contains(definition)) {
                String[] options = {"Duplicate", "Overwrite"};
                JFrame confirmFrame = new JFrame();
                int result = JOptionPane.showOptionDialog(
                        confirmFrame,
                        "Slang word is already exist!\n" +
                                "Do you want to duplicate or overwrite it?",
                        "Slang word already exist",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        options,
                        options[0]
                );
                if (result == JOptionPane.YES_OPTION) {
                    values.add(definition);
                    slangDict.replace(slangWord, values);
                }
            } else {
                values.add(definition);
                slangDict.replace(slangWord, values);
            }
        } else {
            values.add(definition);
            slangDict.putIfAbsent(slangWord, values);
        }
    }

    public static void removeSlangWord(String slangWord, String definition) {
        List<String> currentValues = slangDict.get(slangWord);
        List<String> values = new ArrayList<>(currentValues);
        values.remove(definition);
        slangDict.replace(slangWord, values);
    }

    public static String getRandomSlang() {
        List<String> keysArray = new ArrayList<>(slangDict.keySet());
        Random r = new Random();

        return keysArray.get(r.nextInt(keysArray.size()));
    }

    public static String getRandomDefinitionBySlang(String slangWord) {
        Random r = new Random();

        List<String> values = slangDict.get(slangWord);

        return values.get(r.nextInt(values.size()));
    }

    public static List<String> get4RandomSlang(String answer) {
        List<String> randomSlang = new ArrayList<>();
        while (randomSlang.size() < 4) {
            String newSlang = getRandomSlang();
            if (!randomSlang.contains(newSlang) && !Objects.equals(newSlang, answer)) {
                randomSlang.add(newSlang);
            }
        }
        return randomSlang;
    }

    public static List<String> get4RandomDefinition(String answer) {
        List<String> randomDefinition = new ArrayList<>();
        List<String> randomSlang = new ArrayList<>();
        while (randomDefinition.size() < 4) {
            String newSlang = getRandomSlang();
            if (!randomSlang.contains(newSlang) && !Objects.equals(newSlang, answer)) {
                randomSlang.add(newSlang);
                String newDefinition = getRandomDefinitionBySlang(newSlang);
                if (!randomDefinition.contains(newDefinition)) {
                    randomDefinition.add(newDefinition);
                }
            }
        }
        return randomDefinition;
    }
}
