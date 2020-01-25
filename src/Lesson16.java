import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Lesson16 {

    // Создайте метод с сигнатурой String createFolder(String name),
    // который создает каталог name (один уровень) в текущей папке и возвращает полный родителя текущего каталога
    String createFolder(String name) {
        File f = new File(name);
        f.mkdir();
        Path p = Paths.get("..");
        return p.toAbsolutePath().normalize().toString();
    }

    // Реализовать метод с сигнатурой boolean replaceF(String name) который заменяет в файле все F на f,
    // в случае ошибки вернуть false. Для реализации пользоваться методами java.nio.file.Files.

    public static void main(String[] args) {
        System.out.println(new Lesson16().createFolder("qwe"));
    }

    boolean replaceF(String name) {
        String content, newContent;
        try {
            Path f = Paths.get(name);
            content = Files.readString(f);
            newContent = content.replaceAll("F", "f");
            Files.writeString(f, newContent);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
