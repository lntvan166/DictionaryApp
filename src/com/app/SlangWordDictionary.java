package com.app;

import java.util.*;

/**
 * com.app
 * Create by Le Nguyen Tu Van
 * Date 12/22/2021 - 7:22 PM
 * Description: ...
 */
public class SlangWordDictionary {

    public static HashMap<String, List<String>> slangDict = new HashMap<>();
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
            List<String> values = slangWord.getValue();
            for (String value : values) {
                if(value.toLowerCase().contains(definitionToFind.toLowerCase())){
                    slangMatch.put(key, List.of(new String[]{value}));
                }
            }
        }

        return slangMatch;
    }

    public static void resetDict() {
        slangDict = rootSlangDict;
    }

    public static void addSlangWord(String slangWord, String definition) {
        List<String> values = new ArrayList<>();
        if (slangDict.containsKey(slangWord)) {
            List<String> currentValues = slangDict.get(slangWord);
            values.addAll(currentValues);
            values.add(definition);
            slangDict.replace(slangWord, values);
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
}
