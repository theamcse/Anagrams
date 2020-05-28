

package com.google.engedu.anagrams;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

public class AnagramDictionary {

    private static final int MIN_NUM_ANAGRAMS = 5;
    private static final int DEFAULT_WORD_LENGTH = 3;
    private static final int MAX_WORD_LENGTH = 7;
    private Random random = new Random();
    int wordlength=DEFAULT_WORD_LENGTH;
    ArrayList<String> wordList;
    HashSet<String> wordset;
    HashMap<String , ArrayList<String>> letterstoword;


    public AnagramDictionary(Reader reader) throws IOException {
        BufferedReader in = new BufferedReader(reader);
        String line;
        wordList = new ArrayList<>();
        wordset = new HashSet<>();
        letterstoword = new HashMap<>();

        while((line = in.readLine()) != null) {
            String word = line.trim();
            wordList.add(word);
            wordset.add(word);
            String sortedword=sortLetters(word);
            if(letterstoword.containsKey(sortLetters(word))){
                ArrayList<String>listword=letterstoword.get(sortedword);
                listword.add(word);
                letterstoword.put(sortedword,listword);
            }
            else{
                ArrayList<String> listword=new ArrayList<>();
                listword.add(word);
                letterstoword.put(sortedword,listword);
            }

        }
    }
    public String sortLetters(String inputWord){
        char[] chars = inputWord.toCharArray();
        Arrays.sort(chars);
        String sortedWord = new String(chars);

        return  sortedWord;
    }


    public boolean isGoodWord(String word, String base) {
        if(wordset.contains(word) && !word.contains(base)){
            return true;
        }else{
            return false;
        }

    }



    public List<String> getAnagramsWithOneMoreLetter(String word) {
        ArrayList<String> result = new ArrayList<String>();
        char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();
        for(char letter:alphabet){
            if(letterstoword.containsKey(sortLetters(word+letter))){
                ArrayList<String> listAnagrams=letterstoword.get(sortLetters(word+letter));
                for(int i=0;i<listAnagrams.size();i++){
                    if(isGoodWord(listAnagrams.get(i),word)){
                        result.add(listAnagrams.get(i));
                    }

                }
            }
        }

        return result;
    }

    public String pickGoodStarterWord() {

        int length=0;
       while(true){
            String word=wordList.get(new Random().nextInt(wordList.size()));
            length=letterstoword.get(sortLetters(word)).size();

           if (length>=MIN_NUM_ANAGRAMS){
                return word;
            }
        }


    }
}
