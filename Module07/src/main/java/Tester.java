package main.java;

import java.lang.reflect.Method;
import java.util.Comparator;
import java.util.stream.Stream;
import main.java.annotations.AfterSuite;
import main.java.annotations.BeforeSuite;
import main.java.annotations.Test;

public class Tester {

  public static void start(Class<?> testClass) {
    try {
      Object testInstance = testClass.getDeclaredConstructor().newInstance();

      Stream.of(testClass.getDeclaredMethods())
          .filter(method -> method.isAnnotationPresent(BeforeSuite.class))
          .reduce((method1, method2) -> {
            throw new RuntimeException("More than one @BeforeSuite method found");
          })
          .ifPresent(method -> invokeSafely(method, testInstance));

      Stream.of(testClass.getDeclaredMethods())
          .filter(method -> method.isAnnotationPresent(Test.class))
          .sorted(Comparator.comparingInt(m -> m.getAnnotation(Test.class).priority()))
          .toList()
          .forEach(method -> invokeSafely(method, testInstance));

      Stream.of(testClass.getDeclaredMethods())
          .filter(method -> method.isAnnotationPresent(AfterSuite.class))
          .reduce((method1, method2) -> {
            throw new RuntimeException("More than one @AfterSuite method found");
          })
          .ifPresent(method -> invokeSafely(method, testInstance));

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static void start(String className) {
    try {
      start(Class.forName(className));
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }
  }

  private static void invokeSafely(Method method, Object instance) {
    try {
      method.invoke(instance);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}