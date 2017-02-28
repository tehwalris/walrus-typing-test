package com.tehwalris.walrusTypingTest;

public class LimitedStatsTracker implements StatsTracker {
  private LimitedStack<TypingResult> results;
  private int limit;

  public LimitedStatsTracker(int limit) {
    this.limit = limit;
    results = new LimitedStack<TypingResult>(limit);
  }

  public void addEntry(TypingResult entry) {
    results.push(entry);
  }

  public Stats getStats() {
    Stats stats = new Stats();
    for (TypingResult entry : results) {
      stats.totalCount++;
      stats.averageSeconds += entry.secondsTaken;
      if (entry.isCorrect) {
        stats.correctCount++;
        stats.averageSecondsCorrect += entry.secondsTaken;
      } else {
        stats.incorrectCount++;
        stats.averageSecondsIncorrect += entry.secondsTaken;
      }
    }
    stats.averageSeconds /= stats.totalCount;
    stats.averageSecondsCorrect /= stats.correctCount;
    stats.averageSecondsIncorrect /= stats.incorrectCount;
    return stats;
  }

  public int getLimit() {
    return limit;
  }
}
