package com.haoyuanj;

import junit.framework.*;

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
        StringWriter out = new StringWriter()) {
      List<IntervalMeteringData> got = IntervalMeteringDataReader.read(in);
      InsertStmtWriter.write(out, got);
      assertStmtEqualsGolden(out.toString(), "golden_1.sql");
    }
  }

  private void assertStmtEqualsGolden(String stmt, String goldenFile)
      throws URISyntaxException, IOException {
    assertEquals(
        stmt,
        Files.readString(Paths.get(getClass().getClassLoader().getResource(goldenFile).toURI())));
  }

  private InputStream getTestInputSteam(String filePath) {
    return getClass().getClassLoader().getResourceAsStream(filePath);
  }
}
