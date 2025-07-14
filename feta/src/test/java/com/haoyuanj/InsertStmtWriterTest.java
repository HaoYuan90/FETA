package com.haoyuanj;

import junit.framework.*;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class InsertStmtWriterTest extends TestCase {

  public void testDataSet1IsConvertedIntoInsertStatementsMatchingGolden() throws Exception {
    try (InputStream in = getTestInputSteam("test_1.csv");
        IntervalMeteringDataReader r = new IntervalMeteringDataReader(in);
        StringWriter sr = new StringWriter();
        BufferedWriter out = new BufferedWriter(sr)) {
      List<IntervalMeteringData> got = r.readAll();
      InsertStmtWriter.write(out, got);
      out.flush();
      assertStmtEqualsGolden(sr.toString(), "golden_1.sql");
    }
  }

  private void assertStmtEqualsGolden(String stmt, String goldenFile)
      throws URISyntaxException, IOException {
    assertEquals(
        Files.readString(Paths.get(getClass().getClassLoader().getResource(goldenFile).toURI())),
        stmt);
  }

  private InputStream getTestInputSteam(String filePath) {
    return getClass().getClassLoader().getResourceAsStream(filePath);
  }
}
