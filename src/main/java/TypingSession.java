package com.tehwalris.walrusTypingTest;

import java.time.Duration;

public class TypingSession {
  private String targetWord = "walrus";
  private LimitedStatsTracker limitedTrackerLow, limitedTrackerHigh;

  public TypingSession(String targetWord, int lowTrackingLimit, int highTrackingLimit) {
    this.targetWord = targetWord;
    limitedTrackerLow = new LimitedStatsTracker(lowTrackingLimit);
    limitedTrackerHigh = new LimitedStatsTracker(highTrackingLimit);
  }

  public void acceptInput(String input, Duration durationTaken) {
    boolean isCorrect = input.equals(targetWord);
    TypingResult entry = new TypingResult(isCorrect, durationTaken);
    limitedTrackerLow.addEntry(entry);
    limitedTrackerHigh.addEntry(entry);
  }

  public void printStats() {
    System.out.printf("Last %d: %s\n", limitedTrackerLow.getLimit(), getStatsString(limitedTrackerLow.getStats()));
    System.out.printf("Last %d: %s\n", limitedTrackerHigh.getLimit(), getStatsString(limitedTrackerHigh.getStats()));
  }

  private String getStatsString(Stats stats) {
    return String.format("correct/total: %d/%d (%.1f%%), average seconds taken: %.3f/%.3f/%.3f (all/correct/incorrect)",
        stats.correctCount, stats.totalCount, (float) stats.correctCount / stats.totalCount * 100.0,
        stats.averageSeconds, stats.averageSecondsCorrect, stats.averageSecondsIncorrect);
  }
}
