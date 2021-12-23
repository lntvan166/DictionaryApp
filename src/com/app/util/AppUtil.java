package com.app.util;

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

    public static HashMap<String, List<String>> getData() {


        return null;
    }
}
