package com.app.util;

import com.app.DictionaryUtil;

import javax.swing.*;
import java.io.*;
import java.util.HashMap;
import java.util.List;

/**
 * com.app.util
 * Create by Le Nguyen Tu Van
 * Date 12/20/2021 - 10:57 PM
 * Description: ...
 */
public class AppUtil {
    public static HashMap<String, List<String>> readRootDataFromTextFile(String filename) throws IOException {
        HashMap<String, List<String>> slangWordList = new HashMap<>();

        BufferedReader bufferedReader = new BufferedReader(new FileReader(filename));

        String line = bufferedReader.readLine();

        do {
            String[] buffer = line.split("`");
            String slang = buffer[0];
            List<String> definition = List.of(buffer[1].split("\\| "));
            slangWordList.put(slang, definition);


            line = bufferedReader.readLine();
        } while (line != null);

        return slangWordList;
    }

    public static void serializeDictionary() throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream("slang.ser");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(DictionaryUtil.slangDict);
        objectOutputStream.close();
        fileOutputStream.close();
    }

    public static HashMap<String, List<String>> deserializeDictionary() throws IOException, ClassNotFoundException {
        FileInputStream fileInputStream = new FileInputStream("slang.ser");
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        HashMap<String, List<String>> result = (HashMap<String, List<String>>) objectInputStream.readObject();
        objectInputStream.close();
        fileInputStream.close();

        return result;
    }

    public static HashMap<String, List<String>> getData() throws IOException {
        HashMap<String, List<String>> slangWordList = new HashMap<>();

        try {
            slangWordList = deserializeDictionary();
        } catch (IOException | ClassNotFoundException e) {
            slangWordList = readRootDataFromTextFile("slang.txt");
        }

        return slangWordList;
    }
}
