package com.haoyuanj;

import org.apache.commons.cli.*;

import java.io.*;
import java.util.List;

public class Main {
  public static void main(String[] args) {
    Options options = new Options();
    options.addOption(
        Option.builder("f")
            .longOpt("file")
            .hasArg(true)
            .numberOfArgs(1)
            .required(true)
            .desc("Path of input NEM12 file.")
            .build());
    options.addOption(
        Option.builder("o")
            .longOpt("output")
            .hasArg(true)
            .numberOfArgs(1)
            .required(true)
            .desc("Path of output file containing SQL insert queries.")
            .build());

    CommandLineParser parser = new DefaultParser();
    CommandLine cmd = null;
    try {
      cmd = parser.parse(options, args);
    } catch (ParseException pe) {
      System.out.println("Error parsing command-line arguments: " + pe.getMessage());
      System.exit(1);
    }

    File inputFile = new File(cmd.getOptionValue("f"));
    List<IntervalMeteringData> data = null;
    try (InputStream s = new FileInputStream(inputFile)) {
      data = IntervalMeteringDataReader.read(s);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
      System.exit(1);
    } catch (IOException e) {
      e.printStackTrace();
      System.exit(1);
    }

    File outputFile = new File(cmd.getOptionValue("o"));
    try (Writer w = new FileWriter(outputFile)) {
      InsertStmtWriter.write(w, data);
    } catch (IOException e) {
      e.printStackTrace();
      System.exit(1);
    }

    System.out.println("Insert statements have been written to output file " + outputFile.getPath());
  }
}
