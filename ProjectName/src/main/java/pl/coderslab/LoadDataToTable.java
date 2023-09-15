package pl.coderslab;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class LoadDataToTable {
    //static String[][] tasks;
    //static String[] OPTIONS = {"add", "remove", "list", "exit"};
    static String FILE_NAME = "tasks.csv";
    public static void main(String[] args) {
        Path directory = Paths.get(FILE_NAME);
        File file = new File(FILE_NAME);
        if (!Files.exists(directory)) {
            System.out.println("File does not exists");
            System.exit(0);
        }
        StringBuilder printDataFromFile = new StringBuilder();
        int counter = 0;
        String[][] table = null;
        try {
            Scanner scan = new Scanner(file);
            while (scan.hasNextLine()) {
                String line = scan.nextLine();
                printDataFromFile.append(line).append(System.lineSeparator());
                counter++;
            }
            System.out.println(counter);
            String newText = printDataFromFile.toString();
            System.out.println(newText);
            String[] textArray = new String[counter];
            for (int i = 0; i < textArray.length; i++) {
                String[] rows = newText.split(",");
                textArray[i] = rows[i];
            }
            System.out.println(Arrays.toString(textArray));
            table = new String[textArray.length][];
            String[] joinedTable = new String[textArray.length];
            for (int i = 0; i < joinedTable.length; i++) {
               joinedTable[i] = textArray[i].join(System.lineSeparator());
            }
            System.out.println(Arrays.toString(joinedTable));
            System.out.println(Arrays.deepToString(table));

            for (int i = 0; i < textArray.length; i++) {
                String[] split = textArray[i].split(",");
                for (int j = 0; j < split.length; j++) {
                    table[i][j] = split[j];
                }
            }
            System.out.println(Arrays.deepToString(table));
//            for (int i = 0; i <) {
//
//            }
//
//            System.out.println();
//            table = new String[splittedText.length][splittedText.length];
//            for (int i = 0; i < splittedText.length; i++) {
//                String[] split = splittedText[i].split(",");
//                for (int j = 0; j < split.length; j++) {
//                    table[i][j] = split[j];
//                }
//            }
//            System.out.println(Arrays.deepToString(table));
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }
}
