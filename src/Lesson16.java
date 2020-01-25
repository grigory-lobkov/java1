import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Lesson16 {

    //Создайте метод с сигнатурой String createFolder(String name),
    // который создает каталог name (один уровень) в текущей папке и возвращает полный родителя текущего каталога
    String createFolder(String name) {
        File f = new File(name);
        f.mkdir();
        Path p = Paths.get("..");
        return p.toAbsolutePath().normalize().toString();
    }

    public static void main(String[] args) {
        System.out.println(new Lesson16().createFolder("qwe"));
    }
}
