import java.util.Arrays;
import java.util.List;

public class MyArray {
    public boolean checkArray(Integer[] numbers){
        if (numbers == null) return false;
        List<Integer> values = Arrays.asList(numbers);
        return values.contains(1) || values.contains(4);
    }
}