package com.revature.project0Test;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import com.revature.model.Account;
import com.revature.model.User;
import com.revature.service.Servicer;

public class ServicerTest {

  private static Servicer service;
  private static String input;
  private static String output;
  private static Boolean outputBol;
  private static User user;


  @Before // before it Test runs
  public void setUp() {
    service = new Servicer();
  }

  @Test
  public void loginValidate2Test() {
    boolean out = service.loginValidate2("d", "d");
    assertTrue(out);
  }

  @Test
  public void loginValidate2Test2() {
    boolean out = service.loginValidate2("d", "danny");
    assertFalse(out);
  }

  @Test
  public void loginValidate2Test3() {
    boolean out = service.loginValidate2("da", "da");
    assertFalse(out);
  }

  @Test
  public void loginValidate2Test4() {
    service.loginValidate2("d", "d");
    assertNotNull(service.account);
  }

  @Test
  public void loginValidate2Test5() {
    service.loginValidate2("d", "d");
    assertNotNull(service.user);
  }

  @Test
  public void withdraw2Test() {
    // (int id, int userId, String type, double balance)
    service.account = new Account(0, 0, "CHECKINGS", 100);
    service.withdraw2(service.account, "80.00");
    assertEquals(service.account.getBalance(), 20, 0);
  }

  @Test
  public void withdraw2Test2() {
    // (int id, int userId, String type, double balance)
    service.account = new Account(0, 0, "CHECKINGS", 100);
    service.withdraw2(service.account, "-100.00");
    assertEquals(service.account.getBalance(), 100, 0);
  }

  @Test
  public void withdraw2Test3() {
    // (int id, int userId, String type, double balance)
    service.account = new Account(0, 0, "CHECKINGS", 100);
    service.withdraw2(service.account, "100000.00");
    assertEquals(service.account.getBalance(), 100, 0);
  }
  
  @Test
  public void withdraw2Test4() {
    // (int id, int userId, String type, double balance)
    service.account = new Account(0, 0, "CHECKINGS", 100);
    service.withdraw2(service.account, "-100000.00");
    assertEquals(service.account.getBalance(), 100, 0);
  }

  @Test
  public void deposit2Test() {
    service.account = new Account(0, 0, "CHECKINGS", 100);
    service.deposit2(service.account, "100.00");
    assertEquals(service.account.getBalance(), 200, 0);
  }
  
  @Test
  public void deposit2Test2() {
    service.account = new Account(0, 0, "CHECKINGS", 100);
    service.deposit2(service.account, "-100.00");
    assertEquals(service.account.getBalance(), 100, 0);
  }
  
  @Test
  public void deposit2Test3() {
    service.account = new Account(0, 0, "CHECKINGS", 100);
    service.deposit2(service.account, "100000.00");
    assertEquals(service.account.getBalance(), 100, 0);
  }
  
  @Test
  public void deposit2Test4() {
    service.account = new Account(0, 0, "CHECKINGS", 100);
    service.deposit2(service.account, "-100.00");
    assertEquals(service.account.getBalance(), 100, 0);
  }
  
  @Test
  public void register1Test() {
    input = "jack";
    output = service.register1(input);
    assertEquals(output, null);
  }
  
  @Test
  public void register1Test2() {
    input = "daaaaaa";
    output = service.register1(input);
    assertEquals(output, "daaaaaa");
  }

  @Test
  public void register2Test() {
    //register2(String userName, String password, String firstName, String lastName)
    service.register2("day", "day1", "day2", "day3");
    assertEquals(service.user.getFirstName(), "day2");
    assertEquals(service.user.getLastName(), "day3");
    assertEquals(service.user.getUsername(), "day");
    assertEquals(service.user.getPassword(), "day1");
  }
  
  @Test
  public void accountRegistration2Test()
  {
    user = new User(0, "day", "day1", "day2", "day3");
    outputBol = service.accountRegistration2("Checking", user);
    assertFalse(outputBol);
  }
  
  @Test
  public void accountRegistration2Test2()
  {
    user = new User(0, "dannyr3", "dannyr3", "dannyr3", "dannyr3");
    outputBol = service.accountRegistration2("Checking", user);
    assertTrue(outputBol);
  }
  
  @After // After everything Test runs
  public void tearDown() {
    service = null;
  }

}
