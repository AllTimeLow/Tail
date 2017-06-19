import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

/**
 * Created by LaneBoy on 05.06.17.
 */

public class Tail {

    private Params params;
    //читает файл построчно
    private ArrayList<String> ReadFile(String name, String folder) throws Exception {
        ArrayList<String> lines = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(folder + name));
        String line = "";
        while ((line = reader.readLine()) != null)
            lines.add(line);
        return lines;
    }
    //берутся последние n строк из массива
    private ArrayList<String> GetLines(ArrayList<String> lines, int count) {
        if (count < lines.size() && count > -1) {
            ArrayList<String> result = new ArrayList<>();
            for (int i = lines.size() - count; i < lines.size(); i++)
                result.add(lines.get(i));
            return result;
        }
        return lines;
    }
    //берутся последние n символов из массива
    private String GetSymbols(ArrayList<String> lines, int count) {
        int charCount = 0;
        for (String line : lines)
            charCount += line.length();
        String resLine = "";
        for (String line : lines) {
            if (charCount - line.length() > count)
                charCount -= line.length();
            else
                resLine += line;
        }
        if (resLine.length() > count)
            return resLine.substring(resLine.length() - count);
        else
            return resLine;
    }
    //запись файла
    private void WriteToFile(String name, ArrayList<String> lines) throws Exception {
        PrintWriter out = new PrintWriter(name);
        for (String item : lines)
            out.println(item);
        out.close();
    }
    //основная логика взятия n строк или символов из взодных файлов
    public void GetLines() {
        try {
            ArrayList<String> list = new ArrayList<>();
            String folder = "/Users/LaneBoy/Desktop/Kotlin/WhatTheF**k/tile/src/";
            boolean lines = true;
            int count = 0;
            //проверяются флаги
            if (params.c > 0) {
                count = params.c;
                lines = false;
            } else if (params.n > 0)
                count = params.n;
            else
                count = 10;
            //в массив сохраняется содержимое всех входных фалов
            if (params.inFileNames.size() > 0) {
                for (String name : params.inFileNames) {
                    list.add(name);
                    if (lines)
                        list.addAll(GetLines(ReadFile(name, folder), count));
                    else
                        list.add(GetSymbols(ReadFile(name, folder), count));
                }
            }
            //ввод с консоли
            else {
                Scanner sc = new Scanner(System.in);
                System.out.println("Введите количество строк");
                int n = sc.nextInt();
                sc.nextLine();
                ArrayList<String> linesList = new ArrayList<String> ();
                for (int i = 1; i <= n; i++) {
                    System.out.println("Введите " + i + " строку:\n");
                    linesList.add(sc.nextLine());
                }
                if (lines)
                    list.addAll(GetLines(linesList, count));
                else
                    list.add(GetSymbols(linesList, count));
            }
            //если имя выходного файла не указано, то пишется в консоли
            if (Objects.equals(params.outFile, "")) {
                WriteToFile(params.outFile, list);
            }
            //запись файла
            else {
                PrintWriter out = new PrintWriter(params.outFile);
                for (String item : list)
                    out.println(item);
                out.close();
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    //конструктор, который заполняет params
    public Tail(Params _params) throws Exception {
        params = _params;
    }
}
