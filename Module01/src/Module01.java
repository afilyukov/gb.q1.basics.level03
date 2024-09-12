import fruits.Apple;
import fruits.Orange;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Module01 {

  public static void main(String[] args) {
    MyArray<Object> array = new MyArray<>(Object.class, 3);
    array.set(0, new Object());
    array.set(1, new Object());
    array.set(2, new Object());

    //Task1 and 2
    task1(array);

    task2(array);

    //Task 3
    task3();
  }

  private static void task3() {
    Box<Orange> boxOfOranges1 = new Box<>();
    Box<Apple> boxOfApples1 = new Box<>();
    Box<Apple> boxOfApples2 = new Box<>();
    boxOfApples1.add(new Apple());
    boxOfApples1.add(new Apple());
    boxOfApples1.add(new Apple());

    boxOfOranges1.add(new Orange());
    boxOfOranges1.add(new Orange());

    System.out.println("Вес коробки апельсинов-1: " + boxOfOranges1.getWeight());
    System.out.println("Вес коробки яблок-1: " + boxOfApples1.getWeight());
    System.out.println("Вес коробки яблок-2: " + boxOfApples2.getWeight());

    System.out.println("Коробка яблок-1 равна по весу коробке апельсинов-1: " + boxOfApples1.compare(boxOfOranges1));
    System.out.println("Коробка апельсинов-1 равна по весу коробке яблок-2: " + boxOfOranges1.compare(boxOfApples2));

    boxOfApples1.transferTo(boxOfApples2);
    System.out.println("Вес коробки яблок1: " + boxOfApples1.getWeight());
    System.out.println("Вес коробки яблок2: " + boxOfApples2.getWeight());
  }

  private static void task1(MyArray<Object> array) {
    IntStream.range(0, array.length).mapToObj(array::get).forEachOrdered(System.out::println);
    doSwipe(array, 0, 2);
    IntStream.range(0, array.length).mapToObj(array::get).forEachOrdered(System.out::println);
  }


  private static <E> void task2(MyArray<E> array) {
    List<E> list = IntStream.range(0, array.length).mapToObj(array::get)
        .collect(Collectors.toList());
    System.out.println("Печатаем список");
    list.forEach(System.out::println);
  }

  private static <E> void doSwipe(MyArray<E> array, int i, int j){
    E tmp = array.get(i);
    array.set(i, array.get(j));
    array.set(j, tmp);
  }
}