package lessons2;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/*
Є текстовий файл file.txt. Необхідно прочитати файл, перетворити його в список об'єктів типу User, і записати їх у
новий файл user.json.
Результат виводу в консоль повинен бути відсортований за частотою (спочатку йдуть слова, що зустрічаються найчастіше)
Формат файлу:

перший рядок - заголовок
кожний наступний рядок - окремий об'єкт, кожна колонка розділена пробілом
Приклад:

Для файлу file.txt із вмістом:

name age
alice 21
ryan 30

необхідно створити наступний файл user.json:

[
    {
        "name": "alice",
        "age":21
    },
    {
        "name": "ryan",
        "age":30
    }
]
*/


public class MyIOGson {

    private String name;
    private int age;
    private static String inputFileName;
    private static String outputFileName;

    public MyIOGson(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public MyIOGson(String inputFileName, String outputFileName) {
        MyIOGson.inputFileName = inputFileName;
        MyIOGson.outputFileName = outputFileName;
    }

    public void creadGson() {
        List<MyIOGson> userList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFileName))) {
            String line;
            boolean firstLine = true;
            while ((line = reader.readLine()) != null) {
                if (firstLine) {
                    firstLine = false;
                    continue; // Пропустимо перший рядок із заголовками
                }
                reading(line, userList);
            }
            System.out.println("Зчитування файлу за шляхом " + inputFileName + " пройшло успішно!");
        } catch (IOException e) {
            System.out.println("Файл за шляхом " + inputFileName + ", не було зайдено або не існує " + e.getMessage());
        }
        // Записуємо список об'єктів User у JSON файл
        try (FileWriter writer = new FileWriter(outputFileName)) {
            Gson gson = new Gson();
            writer.write(gson.toJson(userList));
            System.out.println("Створення файлу за шляхом " + outputFileName + " пройшло успішно!");
        } catch (IOException e) {
            System.out.println("За шляхом " + outputFileName + " не вдалось записати, зверніться за допомогою " +
                    e.getMessage());
        }
    }

    private static void reading(String line, List<MyIOGson> userList) {
        String[] parts = line.split(" ");
        if (parts.length >= 2) {
            String name = parts[0];
            int age = Integer.parseInt(parts[1]);
            userList.add(new MyIOGson(name, age));
        }
    }
}