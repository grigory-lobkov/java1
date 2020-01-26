package ru.progwards.java1.lessons.files;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FindDuplicates {
// В заданном каталоге и его подкаталогах найти файлы, точно совпадающие по названию (и расширению),
// дате-времени последнего изменения, размеру и по содержимому. Сигнатура метода
// public List<List<String>> findDuplicates(String startPath)
// , результат - список, содержащий списки строк с именами и полными путями совпадающих файлов.

    // В заданном каталоге и его подкаталогах найти файлы, точно совпадающие
    public static List<List<String>> findDuplicates(String startPath) {
        List<String> readAll = readAllFilesNio(startPath);
        return null;
    }

    // Считываем все файлы в данном каталоге в массив
    public static List<String> readAllFilesLambda(String startPath) {
        try {
            return Files.walk(Paths.get(startPath))
                    .filter(Files::isRegularFile)
                    .map(Path::toString)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            return null;
        }
    }

    // Считываем все файлы в данном каталоге в массив
    public static List<String> readAllFilesNio(String startPath) {
        List<String> result = new ArrayList<String>();
        Path root = Paths.get(startPath);
        SimpleFileVisitor fv = new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                result.add(file.toString());
                return FileVisitResult.CONTINUE;
            }
        };
        try {
            Files.walkFileTree(root, fv);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    // Считываем все файлы в данном каталоге в массив
    public static List<String> readAllFilesIo(String startPath) {
        return readAllFilesIo(new File(startPath));
    }
    public static List<String> readAllFilesIo(File folder)
    {
        List<String> result = new ArrayList<String>();
        File[] folderEntries = folder.listFiles();
        for (File entry : folderEntries) {
            if (entry.isDirectory()) {
                result.addAll(readAllFilesIo(entry));
            } else {
                result.add(entry.getPath());
            }
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(readAllFilesIo("src"));
        System.out.println(readAllFilesNio("src"));
        System.out.println(readAllFilesLambda("src"));
    }
}
