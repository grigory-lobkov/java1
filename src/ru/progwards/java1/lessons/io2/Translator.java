package ru.progwards.java1.lessons.io2;

//Создать класс Translator - переводчик, который будет переводить предложения с одного языка на другой

import java.util.Hashtable;

public class Translator {

    private class Translation {
        String wordFrom;
        String wordTo;
    }

/*
Конструктор Translator(String[] inLang, String[] outLang),
где inLang и outLang - массивы слов на разных языках, например русском и английском.
Сохранить значения параметров в приватных свойствах класса
*/

    //String[] inLang; // "с какого языка переводим", в нижнем регистре
    //String[] outLang; // "на какой язык переводим", в нижнем регистре
    Hashtable<String, String> wordTable; // атры слов в хэш-таблице

    Translator(String[] inLang, String[] outLang) {
        //this.inLang = inLang;
        //this.outLang = outLang;
        if (outLang.length != inLang.length) throw new ArrayIndexOutOfBoundsException("Длины массивов должны быть одинаковы");

        int count = inLang.length;
        wordTable = new Hashtable<String, String>(count);
        for (int i = 0; i < count; i++) {
            wordTable.put(inLang[i].toLowerCase(), outLang[i].toLowerCase());
        }
    }

/*Метод public String translate(String sentence),
в котором найти слова, содержащиеся в sentence и в inLang
и заменить их на соответствующие в outLang. Пунктуация
должна быть соблюдена (скопирована из оригинальной строки).
При этом надо соблюсти заглавные буквы, если они были в
оригинальном тексте. В inLang и outLang все слова хранятся в нижнем регистре.
    Например, фраза
"Hello World!" будет переведена как "Привет Мир!"
    при наличии слов "hello", "world" в inLang и "привет", "мир" в outLang
*/

    public String translateWord(String word) {
        String result = wordTable.get(word.toLowerCase());
        if (Character.isUpperCase(word.charAt(0))) {
            return Character.toUpperCase(word.charAt(0)) + (word.length() > 1 ? word.substring(1) : "");
        }
        return result;
    }

    public String translate(String sentence) {
        StringBuilder result = new StringBuilder(128);
        int wordBeginIndex = -1;

        for(int i = 0, len = sentence.length(); i < len; i++) {
            char c = sentence.charAt(i);
            if (Character.isLetter(c)) {
                if (wordBeginIndex < 0) wordBeginIndex = i;
            } else {
                if (wordBeginIndex >= 0) {
                    result.append(translateWord(sentence.substring(wordBeginIndex, i)));
                    wordBeginIndex = -1;
                }
                result.append(c);
            }
        }
        if (wordBeginIndex >= 0) {
            result.append(translateWord(sentence.substring(wordBeginIndex)));
        }
        return result.toString();
    }

    public static void main(String[] args) {
        String[] inLang = {"привет", "мир", "как", "твои", "дела"};
        String[] outLang = {"hello", "world", "how", "your", "deals"};
        Translator t = new Translator(inLang, outLang);
        System.out.println(t.translate("Привет, Мир!"));
    }
}
