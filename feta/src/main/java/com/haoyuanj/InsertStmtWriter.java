package com.haoyuanj;

import java.io.BufferedWriter;
import java.io.IOException;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class InsertStmtWriter {

  private static final DateTimeFormatter DT_FORMATTER =
      DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

  public static void write(BufferedWriter br, List<IntervalMeteringData> data) throws IOException {
    for (IntervalMeteringData d : data) {
      br.write(createInsertStmt(d));
      br.newLine();
    }
  }

  private static String createInsertStmt(IntervalMeteringData data) {
    StringBuilder sb = new StringBuilder();
    sb.append("INSERT INTO meter_readings (nmi, timestamp, consumption) VALUES ");
    // TODO: Expect timezone from data, use UTC for this take home.
    ZonedDateTime dt = LocalDateTime.of(data.date(), LocalTime.MIN).atZone(ZoneOffset.UTC);
    for (int i = 0; i < data.consumptionsWh().length; i++) {
      sb.append("(")
          .append("'")
          .append(data.nmi())
          .append("'")
          .append(", ")
          .append("'")
          .append(dt.format(DT_FORMATTER))
          .append("'")
          .append(", ")
          .append(data.consumptionsWh()[i])
          .append(")");
      if (i < data.consumptionsWh().length - 1) {
        sb.append(", ");
      }
      dt = dt.plusMinutes(data.intervalMinutes());
    }
    sb.append(";");
    return sb.toString();
  }
}
