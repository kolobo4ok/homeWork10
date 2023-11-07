package lessons3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/*
* Напишіть метод, який буде рахувати частоту кожного слова у файлі words.txt.
Результат виводу в консоль повинен бути відсортований за частотою (спочатку йдуть слова, що зустрічаються найчастіше)
Вважаємо що:

words.txt містить лише слова в нижньому регістрі, розділені пробілом
Кожне слово містить лише літери в нижньому регістрі
Слова розділені одним або декількома пробілами, або переносом рядка
Приклад:

Для файлу words.txt із вмістом:

the day is sunny the the
the sunny is is

Метод повинен повернути результат на кшталт:

the 4
is 3
sunny 2
day 1
* */
public class WordFrequencyCounter {
    private String inputFileName;

    public WordFrequencyCounter(String inputFileName) {
        this.inputFileName = inputFileName;
    }

    public void run() {
        Map<String, Integer> wordFrequencyMap = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] words = line.split("\\s+"); // Розділити рядок на слова
                for (String word : words) {
                    String cleanedWord = word.toLowerCase().replaceAll("[^a-z]", ""); // Перетворити на нижній регістр і видалити символи пунктуації
                    if (!cleanedWord.isEmpty()) {
                        wordFrequencyMap.put(cleanedWord, wordFrequencyMap.getOrDefault(cleanedWord, 0) + 1);
                    }
                }
            }
            System.out.println("Зчитування файлу за шляхом " + inputFileName + " пройшло успішно!");
        } catch (IOException e) {
            System.out.println("Файл за шляхом " + inputFileName + ", не було зайдено або не існує " + e.getMessage());
        }
        frequency(wordFrequencyMap);
    }

    private static void frequency(Map<String, Integer> wordFrequencyMap) {
        wordFrequencyMap.entrySet()
                .stream()
                .sorted((entry1, entry2) -> entry2.getValue().compareTo(entry1.getValue())) // Відсортувати за частотою
                .forEach(entry -> System.out.println(entry.getKey() + " " + entry.getValue()));
    }
}