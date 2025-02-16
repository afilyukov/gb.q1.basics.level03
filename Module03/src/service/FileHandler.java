import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Collections;
import java.util.List;

public class FileHandler {
    final String dir = System.getProperty("user.dir");
    private Path path;

    public FileHandler(String clientName){
       path = Paths.get(dir + "/" + clientName + ".log");
    }
    private void createFile(Path path){
        try {
            Files.createFile(path);
        }
        catch (IOException thorables){
            System.out.println("Cannot create logfile");
        }
    }

    private boolean checkFile(Path path){
        if (Files.exists(path)) {
            if (Files.isReadable(path)) {return true;}
        }
        return false;
    }

    public void appendToFile(String message) throws Exception{
        if (!checkFile(path)) { createFile(path);}
        Files.write(path, message.getBytes(), StandardOpenOption.APPEND);
    }

    public List<String> readFromFile(String clientName) throws Exception {
        List<String> lines = Collections.emptyList();
        Path path = Paths.get(dir + "/" + clientName + ".log");
        if (checkFile(path)){
            try {
                lines = Files.readAllLines(path, StandardCharsets.UTF_8);
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Файл существует, но не прочитан");
            }
        }
        return lines;
    }
}
