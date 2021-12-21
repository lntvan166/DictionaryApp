package com.app;

import com.app.menu.Menu;
import com.app.util.AppUtil;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * com.app
 * Create by Le Nguyen Tu Van
 * Date 12/21/2021 - 6:08 PM
 * Description: ...
 */
public class App {
    private Menu menu;

    public static HashMap slangWordList;

    public App() throws IOException {
        menu = new Menu();

        slangWordList = AppUtil.readRootDataFromTextFile("slang.txt");



    }

    public void start() {
        Set<Map.Entry<String, List<String>>> setSlangWordList = slangWordList.entrySet();

        for (Map.Entry<String, List<String>> slangWord : setSlangWordList) {
            System.out.println(slangWord.getKey() + ": " + slangWord.getValue());
        }
    }
}
