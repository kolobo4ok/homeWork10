package lessons1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MyStringInputStream {
    private final String fileNames;

    public MyStringInputStream(String fileNames) {
        this.fileNames = fileNames;
    }

    public void numberValidation() {
        Pattern template = Pattern.compile("\\(\\d{3}\\) \\d{3}-\\d{4}|\\d{3}-\\d{3}-\\d{4}");
        try (BufferedReader reader = new BufferedReader(new FileReader(fileNames))) {
            boolean isEmpty = true;
            String numberPhone;
            while ((numberPhone = reader.readLine()) != null) {
                Matcher res = template.matcher(numberPhone);
                isEmpty = false;
                while (res.find()) {
                    System.out.println(res.group());
                }
            }
            if (isEmpty) {
                System.out.println("File empty ");
            }
        } catch (IOException e) {
            System.out.println("Error read file " + e.getMessage());
        }
    }
}