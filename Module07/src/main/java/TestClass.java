package main.java;

import main.java.annotations.AfterSuite;
import main.java.annotations.BeforeSuite;
import main.java.annotations.Test;

public class TestClass {

  @BeforeSuite
  public void beforeSuite() {
    System.out.println("BeforeSuite method is running");
  }

  @Test(priority = 1)
  public void test1() {
    System.out.println("Test 1 is running");
  }

  @Test(priority = 3)
  public void test2() {
    System.out.println("Test 2 is running");
  }

  @Test(priority = 2)
  public void test3() {
    System.out.println("Test 3 is running");
  }

  @AfterSuite
  public void afterSuite() {
    System.out.println("AfterSuite method is running");
  }
}