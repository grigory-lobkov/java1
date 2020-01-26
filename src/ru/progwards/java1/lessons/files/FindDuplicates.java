package ru.progwards.java1.lessons.files;

import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.ListIterator;
import java.util.stream.Collectors;

interface IGetPathValue<T> {
    T get(Path p);
}

public class FindDuplicates {
// В заданном каталоге и его подкаталогах найти файлы, точно совпадающие по названию (и расширению),
// дате-времени последнего изменения, размеру и по содержимому. Сигнатура метода
// public List<List<String>> findDuplicates(String startPath)
// , результат - список, содержащий списки строк с именами и полными путями совпадающих файлов.

    // В заданном каталоге и его подкаталогах найти файлы, точно совпадающие
    public static List<List<Path>> findDuplicates(String startPath) {
        List<Path> path = readAllPathsNio(startPath);
        if (path == null) return null;
        List<List<Path>> paths = new ArrayList<List<Path>>();
        paths.add(path);
        //System.out.println(paths);

        // с одинаковыми именами
        paths = getEquals(paths, p -> p.getFileName().toString());
        //System.out.println(paths);

        // с одинаковой датой-временем последнего изменения
        paths = getEquals(paths, p -> {
            try {
                //return Files.getLastModifiedTime(p);
                return Files.getAttribute(p, "basic:lastModifiedTime");
            } catch (IOException e) {
                return null;
            }
        });
        //System.out.println(paths);

        // с одинаковым размером
        paths = getEquals(paths, p -> {
            try {
                return Files.size(p);
            } catch (IOException e) {
                return null;
            }
        });
        //System.out.println(paths);

        // с одинаковым содержимым
        paths = getEquals(paths, p -> {
            try {
                return Files.size(p);
            } catch (IOException e) {
                return null;
            }
        });

        return paths;
    }

    public static boolean isEqualPaths(Path path1, Path path2) {
        try (
                InputStream f1 = Files.newInputStream(path1);
                InputStream f2 = Files.newInputStream(path2);
        ) {
            long fSize = Files.size(path1);
            int bufSize = fSize / 10;
            final int minBufSize = 10 * 1024;
            final int maxBufSize = 1 * 1024 * 1024;
            if (bufSize > maxBufSize) bufSize = maxBufSize;
            else if (bufSize < minBufSize) bufSize = minBufSize;
            try (
                    BufferedInputStream s1 = new BufferedInputStream(f1);
                    BufferedInputStream s2 = new BufferedInputStream(f2);
            ) {
                int i1, i2;
                while ((i1 = s1.read()) != -1) {
                    i2 = s2.read();
                    if (i1 != i2) return false;
                }
                return true;
            }
        } catch (Throwable e) {
            return false;
        }
    }

    // в заданных списках файлов оставит только с одинаковым размером
    public static <T> List<List<Path>> getEquals(List<List<Path>> paths, IGetPathValue<T> comparatorFunc) {
        ListIterator<List<Path>> pathsIterator = paths.listIterator();
        List<List<Path>> result = new ArrayList<List<Path>>();
        while (pathsIterator.hasNext()) {
            List<Path> p1 = pathsIterator.next();
            Hashtable<T, List<Path>> ht = new Hashtable<T, List<Path>>();
            for (Path p : p1) {
                T cmp = comparatorFunc.get(p);
                if(cmp!=null) {
                    List<Path> found = ht.get(cmp);
                    if (found == null) {
                        found = new ArrayList<Path>();
                        found.add(p);
                        ht.put(cmp, found);
                    } else {
                        found.add(p);
                    }
                }
            }
            ht.forEach((k, v) -> {
                if (v.size() > 1) result.add(v);
            });
        }
        return result;
    }

    // в заданных списках файлов оставит только с одинаковым размером
    public static List<List<Path>> getEqualSizes(List<List<Path>> paths) throws IOException {
        ListIterator<List<Path>> pathsIterator = paths.listIterator();
        List<List<Path>> result = new ArrayList<List<Path>>();
        while (pathsIterator.hasNext()) {
            List<Path> p1 = pathsIterator.next();
            Hashtable<Long, List<Path>> ht = new Hashtable<Long, List<Path>>();
            for (Path p : p1) {
                Long fsize = Files.size(p);
                List<Path> found = ht.get(fsize);
                if (found == null) {
                    found = new ArrayList<Path>();
                    found.add(p);
                    ht.put(fsize, found);
                } else {
                    found.add(p);
                }
            }
            ht.forEach((k, v) -> {
                if (v.size() > 1) result.add(v);
            });
        }
        return result;
    }

    // заданный список файлов сгруппирует по одинаковым именам
    public static List<List<Path>> getEqualNames(List<Path> paths) {
        Hashtable<String, List<Path>> names = new Hashtable<String, List<Path>>();
        for (Path p : paths) {
            String fname = p.getFileName().toString();
            List<Path> found = names.get(fname);
            if (found == null) {
                found = new ArrayList<Path>();
                found.add(p);
                names.put(fname, found);
            } else {
                found.add(p);
            }
        }
        List<List<Path>> result = new ArrayList<List<Path>>();
        names.forEach((k, v) -> {
            if (v.size() > 1) result.add(v);
        });
        return result;
    }

    // Считываем все файлы в данном каталоге в поток
    public static List<Path> readAllPathsLambda(String startPath) throws IOException {
        return Files.walk(Paths.get(startPath))
                .filter(Files::isRegularFile)
                //.map(Path::toString)
                .collect(Collectors.toList());
    }

    // Считываем все файлы в данном каталоге в массив
    public static List<Path> readAllPathsNio(String startPath) {
        List<Path> result = new ArrayList<Path>();
        Path root = Paths.get(startPath);
        SimpleFileVisitor fv = new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                result.add(file);
                return FileVisitResult.CONTINUE;
            }
            @Override
            public FileVisitResult visitFileFailed(Path file, IOException e)
                    throws IOException {
                return FileVisitResult.SKIP_SUBTREE; // нет прав, и не надо
            }
        };
        try {
            Files.walkFileTree(root, fv);
            return result;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
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

    public static void main(String[] args) throws IOException {
        //System.out.println(readAllFilesIo("src"));
        //System.out.println(readAllFilesNio("src"));
        //System.out.println(readAllFilesLambda("src"));
        System.out.println(findDuplicates("."));
    }
}
