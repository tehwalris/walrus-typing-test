package com.tehwalris.walrusTypingTest;

import java.lang.Iterable;
import java.util.Iterator;

public class LimitedStack<T> implements Iterable<T> {
  private int limit;
  private int size = 0;
  private int startIndex = 0;
  private Object[] items;

  public class LimitedStackIterator<T> implements Iterator<T> {
    private LimitedStack<T> stack;
    private int i = 0;

    public LimitedStackIterator(LimitedStack<T> stack) {
      this.stack = stack;
    }

    public boolean hasNext() {
      return i < stack.getSize();
    }

    public T next() {
      return stack.getByIndex(i++);
    }
  }

  public LimitedStack(int limit) {
    this.limit = limit;
    this.items = new Object[limit];
  }

  public void push(T item) {
    items[getInternalIndex(size)] = item;
    if (size == limit)
      startIndex = getInternalIndex(1);
    else
      size++;
  }

  public T getByIndex(int i) {
    @SuppressWarnings("unchecked")
    final T item = (T) items[getInternalIndex(i)];
    return item;
  }

  public int getSize() {
    return this.size;
  }

  public Iterator<T> iterator() {
    return new LimitedStackIterator<T>(this);
  }

  private int getInternalIndex(int index) {
    return (startIndex + index) % (limit - 1);
  }
}
