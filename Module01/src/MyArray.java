import java.lang.reflect.Array;
import java.util.Arrays;

public class MyArray<E> {
    private E[] obj_array;
    public final int length;

    public MyArray(Class<E> clazz, int capacity) {
        try {
            obj_array = (E[]) Array.newInstance(clazz, capacity);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        this.length = capacity;
    }

    E get(int i) {
        return obj_array[i];
    }

    void set(int i, E e) {
        obj_array[i] = e;
    }

    @Override
    public String toString() {
        return Arrays.toString(obj_array);
    }
}