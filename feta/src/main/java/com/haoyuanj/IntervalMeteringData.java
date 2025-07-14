package com.haoyuanj;

import java.time.LocalDate;
import java.util.Arrays;

public record IntervalMeteringData(
    String nmi, LocalDate date, int intervalMinutes, double[] consumptionsWh) {

  // Establishes equality with some tolerance for consumptionsWh
  public boolean equalsApprox(IntervalMeteringData other, double epsilon) {
    if (!this.nmi.equals(other.nmi())) {
      return false;
    }
    if (!this.date.equals(other.date())) {
      return false;
    }
    if (this.intervalMinutes != other.intervalMinutes()) {
      return false;
    }
    if (this.consumptionsWh.length != other.consumptionsWh().length) {
      return false;
    }
    for (int i = 0; i < this.consumptionsWh.length; i++) {
      if (Math.abs(this.consumptionsWh[i] - other.consumptionsWh()[i]) > epsilon) {
        return false;
      }
    }
    return true;
  }

  @Override
  public String toString() {
    return "IntervalMeteringData[nmi="
        + this.nmi
        + ", date="
        + this.date
        + ", intervalMinutes="
        + this.intervalMinutes
        + ", consumptionSize="
        + this.consumptionsWh.length
        + ", consumptionsWh="
        + Arrays.toString(this.consumptionsWh);
  }
}
