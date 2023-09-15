package pl.coderslab;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.math.NumberUtils;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class TaskManager {

    static String[][] tasks;
    static String[] OPTIONS = {"add", "remove", "list", "exit"};
    static String FILE_NAME = "tasks.csv";


    public static void printOptions() {
        System.out.println(ConsoleColors.BLUE + "Please select an option" + ConsoleColors.RESET);
        for (int i = 0; i < OPTIONS.length; i++) {
            System.out.println(OPTIONS[i]);
        }
    }

    public static String[][] loadDataToTab(String fileName) {
        Path directory = Paths.get(fileName);
        if (!Files.exists(directory)) {
            System.out.println("File does not exists");
            System.exit(0);
        }
        String[][] table = null;
        try {
            List<String> strings = Files.readAllLines(directory);
            table = new String[strings.size()][strings.get(0).split(",").length];
            for (int i = 0; i < table.length; i++) {
                String[] split = strings.get(i).split(",");
                for (int j = 0; j < split.length; j++) {
                    table[i][j] = split[j];
                }
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return table;
    }
    public static void addTask() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Please add task description");
        String description = scan.nextLine().toLowerCase();
        System.out.println("Please add task due date");
        String date = scan.nextLine().toLowerCase();
        System.out.println("Is your task important: true/false");
        String importance = scan.nextLine().toLowerCase();
        tasks = Arrays.copyOf(tasks, tasks.length + 1);
        tasks[tasks.length - 1] = new String[3];
        tasks[tasks.length - 1][0] = description;
        tasks[tasks.length - 1][1] = date;
        tasks[tasks.length - 1][2] = importance;
    }
    public static void listedTab(String[][] tab){
        for (int i = 0; i < tab.length; i++) {
            System.out.print(i + " : ");
            for (int j = 0; j < tab[i].length; j++) {
                System.out.print(tab[i][j] + " ");
            }
            System.out.println();
        }
    }
    public static void removeTask(String[][]tab, int elementToDelete) {
        try {
            tasks = ArrayUtils.remove(tab,elementToDelete);
            System.out.println("Value was sucessfully deleted");
        }
        catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Element does not exists in table");
        }
        catch (IndexOutOfBoundsException e) {
            System.out.println("Element does not exists in table");
        }
    }
    public static int getTheNumber() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Please select number to delete");
        String number = scan.nextLine();
        while (!checkInputValue(number)) {
            System.out.println("Incorrect value, please try again");
            number = scan.nextLine();
        }
        return Integer.parseInt(number);
    }
    public static boolean checkInputValue(String valueIn) {
        if (NumberUtils.isParsable(valueIn)) {
            return Integer.parseInt(valueIn) >= 0;
        }
        return false;
    }
    public static void saveTableToFilee(String fileName, String[][] tab) {
        Path directory = Paths.get(fileName);
        String[] lines = new String[tasks.length];
        for (int i = 0; i < tab.length; i++) {
            lines[i] = String.join(",", tab[i]);
        }
        try {
            Files.write(directory,Arrays.asList(lines));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        tasks = loadDataToTab(FILE_NAME);
        printOptions();
        Scanner scan = new Scanner(System.in);
        while (scan.hasNextLine()) {
            String selection = scan.nextLine();
            String correctedSelection = selection.toLowerCase();
            switch (correctedSelection) {
                case "exit":
                    saveTableToFilee(FILE_NAME,tasks);
                    System.out.println(ConsoleColors.RED + "Bye, bye");
                    System.exit(0);
                    break;
                case "add":
                    addTask();
                    break;
                case "remove":
                    removeTask(tasks,getTheNumber());
                    break;
                case "list":
                    listedTab(tasks);
                    break;
                default:
                    System.out.println("Please select a correct option");
            }
            printOptions();
            }
        }
    }
