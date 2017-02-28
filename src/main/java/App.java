package com.tehwalris.walrusTypingTest;

import java.util.Scanner;
import java.time.Instant;
import java.time.Duration;

public class App {
  public static void main(String[] args) {
    Scanner scanner = configureScanner();
    TypingSession session = new TypingSession("walrus", 20, 1000);
    Instant startInstant = Instant.now(), endInstant;
    System.out.println("Documentation: Type walrus lots.\nThat's it.\nGo!\n");
    printPrompt();
    while (scanner.hasNext()) {
      endInstant = Instant.now();
      Duration duration = Duration.between(startInstant, endInstant);
      startInstant = Instant.now();
      if (duration.toMillis() > 2000) {
        scanner.next();
        System.out.println("Input rejected - took too long");
        printPrompt();
      } else {
        session.acceptInput(scanner.next(), duration);
        session.printStats();
        printPrompt();
      }
    }
  }

  public static Scanner configureScanner() {
    Scanner scanner = new Scanner(System.in);
    scanner.useDelimiter("\\n");
    return scanner;
  }

  public static void printPrompt() {
    System.out.print("=> ");
  }
}
