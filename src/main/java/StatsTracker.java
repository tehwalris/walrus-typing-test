package com.tehwalris.walrusTypingTest;

public interface StatsTracker {
  public void addEntry(TypingResult entry);
  public Stats getStats();
}

