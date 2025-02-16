import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;

class MyArrayTest {

  private MyArray testingArray;

  @BeforeEach
  void onTestsStart() {
    testingArray = new MyArray();
  }

  @NullAndEmptySource
  @ParameterizedTest
  void shouldCheckNullOrEmptyArray(Integer[] parameters) {
    assertFalse(testingArray.checkArray(parameters));
  }

  @Test
  void shouldCheckIncorrectArray() {
    Integer[] integers = {5, 3, -8, 17, 22};
    assertFalse(testingArray.checkArray(integers));
  }

  @ParameterizedTest
  @MethodSource("correctArrayProvider")
  void shouldCheckCorrectArray(List<Integer> parameters) {
    Integer[] array = parameters.toArray(new Integer[0]);
    Assertions.assertTrue(testingArray.checkArray(array));
  }

  static Stream<List<Integer>> correctArrayProvider() {
    return Stream.of(
        Arrays.asList(5, 3, -8, 17, 4),
        Arrays.asList(1, 3, -8, 17, 22),
        Arrays.asList(4, 3, -8, 17, 1)
    );
  }
}