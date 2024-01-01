package com.example.WordleGame;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileReader {
    public List<Word> getWords() {
        List<Word> words = new ArrayList<>();

        try {
            File file = new File("/Users/janaabuhantash/IdeaProjects/trial/src/main/java/com/example/trial/words.txt");
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();

                if (line.length() > 0) {
                    String[] split = line.split(" - ");

                    if (split.length == 2) {
                        String word = split[0].trim();
                        String hint = split[1].trim();

                        words.add(new Word(word, hint));
                    }
                }
            }
            scanner.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return words;
    }

    static class Word {
        private String word;
        private String hint;
        private int length;

        public Word(String word, String hint) {
            this.word = word;
            this.hint = hint;
        }

        public String getWord() {
            return word;
        }

        public String getHint() {
            return hint;
        }
        public int getLength() {
            length = word.length();
            return length;
        }
    }
}
