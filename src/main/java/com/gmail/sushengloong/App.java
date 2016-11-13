package com.gmail.sushengloong;

import java.util.Date;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        new PersonalDocumentGenerator("Sheng Loong", new Date(), true).generateDocument();
    }
}
