import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Lesson04 {
    public static void main(String[] args) throws Exception {
        Letters letter = new Letters();

        List<Callable<String>> callables = new ArrayList<>();
        callables.add(() -> {
            letter.printA();
            return "Done A";
        });
        callables.add(() -> {
            letter.printB();
            return "Done B";
        });
        callables.add(() -> {
            letter.printC();
            return "Done C";
        });

        ExecutorService ex = Executors.newFixedThreadPool(3);
        List<Future<String>> futures = ex.invokeAll(callables);
        for (Future<String> f  : futures) {
            System.out.println(f.get());
        }
        ex.shutdown();
    }
}
