package com.haoyuanj;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class IntervalMeteringDataReader {

  private static final DateTimeFormatter YYYYMMDD = DateTimeFormatter.ofPattern("yyyyMMdd");
  private static final int MINUTES_IN_DAY = 1440;

  private record NmiContext(String nmi, int intervalMinutes, String uom) {}

  public static List<IntervalMeteringData> read(InputStream in) throws IOException {
    List<IntervalMeteringData> data = new ArrayList<>();
    try (CSVReader r = new CSVReader(new BufferedReader(new InputStreamReader(in)))) {
      String[] cells = r.readNext();
      NmiContext context = null;
      while (cells != null) {
        int indicator = Integer.parseInt(cells[0]);
        switch (indicator) {
          case 100:
            // Ignore header
            break;
          case 200:
            context = new NmiContext(cells[1], Integer.parseInt(cells[8]), cells[7].toLowerCase());
            break;
          case 300:
            // Interval data
            if (context == null) {
              throw new IllegalArgumentException("NMI data record not found before interval data.");
            }
            data.add(getIntervalMeteringData(context, cells));
            break;
          case 400:
            // Ignore interval event
            break;
          case 500:
            // Ignore b2b details
            break;
          case 900:
            // Ignore end of data
            break;
          default:
        }
        cells = r.readNext();
      }
    } catch (CsvValidationException e) {
      throw new IllegalArgumentException(e);
    }
    return data;
  }

  // Returns an instance of IntervalMeteringData from input NMI and cells from a record of type
  // "300"
  private static IntervalMeteringData getIntervalMeteringData(NmiContext context, String[] cells) {
    LocalDate intervalDate = LocalDate.parse(cells[1], YYYYMMDD);
    double[] consumptions = new double[MINUTES_IN_DAY / context.intervalMinutes()];
    for (int i = 0; i < consumptions.length; i++) {
      consumptions[i] = consumptionToWh(context.uom(), Double.parseDouble(cells[i + 2]));
    }
    return new IntervalMeteringData(
        context.nmi(), intervalDate, context.intervalMinutes, consumptions);
  }

  private static double consumptionToWh(String uom, double consumption) {
    switch (uom) {
      case "mwh":
        return consumption * 1_000_000;
      case "kwh":
        return consumption * 1_000;
      case "wh":
        return consumption;
      default:
        throw new IllegalArgumentException("UOM " + uom + " is not supported.");
    }
  }
}
