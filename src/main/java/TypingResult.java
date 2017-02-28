package com.tehwalris.walrusTypingTest;

import java.time.Duration;

public class TypingResult {
  public boolean isCorrect;
  public double secondsTaken;

  public TypingResult(boolean isCorrect, Duration durationTaken) {
    this.isCorrect = isCorrect;
    this.secondsTaken = (double) durationTaken.toNanos() * 1e-9;
  }
}
