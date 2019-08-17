package com.demo.basis.base;

import com.demo.basis.utils.GameClock;

/** 限速器 */
public class Governor {
  private final int maxCount;
  private final int interval;
  private long lastTime = 0;
  private int count = 0;

  public Governor(int maxCount, int interval) {
    this.maxCount = maxCount;
    this.interval = interval;
  }

  public boolean overSpeed() {
    long now = GameClock.millis();
    if (now - lastTime > interval) {
      lastTime = now;
      count = 0;
    }

    ++count;
    if (count > maxCount) {
      return true;
    }

    return false;
  }

  public static class ConcurrentGovernor extends Governor {

    public ConcurrentGovernor(int maxCount, int interval) {
      super(maxCount, interval);
    }

    @Override
    public boolean overSpeed() {
      synchronized (this) {
        return super.overSpeed();
      }
    }
  }
}