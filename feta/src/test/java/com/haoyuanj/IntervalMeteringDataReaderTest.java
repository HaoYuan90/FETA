package com.haoyuanj;

import junit.framework.*;

import java.io.InputStream;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class IntervalMeteringDataReaderTest extends TestCase {

  private static final double EQUALITY_EPSILON = 0.000001d;

  public void testFileIsParsedCorrectly() throws Exception {
    try (InputStream s = getTestInputSteam("test_1.csv")) {
      assertNotNull(s);
      List<IntervalMeteringData> got = IntervalMeteringDataReader.read(s);
      List<IntervalMeteringData> want =
          Arrays.asList(
              new IntervalMeteringData[] {
                new IntervalMeteringData(
                    "NEM1201009",
                    LocalDate.of(2005, 3, 1),
                    30,
                    new double[] {
                      0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
                      461.0, 810.0, 568.0, 1234.0, 1353.0, 1507.0, 1344.0, 1773.0, 848.0, 1271.0,
                          895.0, 1327.0,
                      1012.9999999999999, 1793.0, 988.0, 985.0, 876.0, 555.0, 760.0, 938.0, 566.0,
                          512.0, 970.0, 760.0,
                      731.0, 615.0, 886.0, 531.0, 774.0, 712.0, 598.0, 670.0, 587.0, 657.0, 345.0,
                          231.0
                    }),
                new IntervalMeteringData(
                    "NEM1201009",
                    LocalDate.of(2005, 3, 2),
                    30,
                    new double[] {
                      0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 235.0, 567.0,
                      890.0, 1123.0, 1345.0, 1567.0, 1543.0, 1234.0, 987.0, 1123.0, 876.0, 1345.0,
                      1145.0, 1173.0, 1265.0, 987.0, 678.0, 998.0, 768.0, 954.0, 876.0, 845.0,
                      932.0, 786.0, 999.0, 879.0, 777.0, 578.0, 709.0, 772.0, 625.0, 653.0, 543.0,
                      599.0, 432.0, 432.0
                    }),
                new IntervalMeteringData(
                    "NEM1201009",
                    LocalDate.of(2005, 3, 3),
                    30,
                    new double[] {
                      0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 261.0, 310.0,
                      678.0, 934.0, 1211.0, 1134.0, 1423.0, 1370.0, 988.0, 1207.0, 890.0, 1320.0,
                      1130.0, 1913.0, 1180.0, 950.0, 746.0, 635.0, 956.0, 887.0, 560.0, 700.0,
                      788.0, 668.0, 543.0, 738.0, 802.0, 490.0, 598.0, 809.0, 520.0, 670.0, 570.0,
                      600.0, 289.0, 321.0
                    }),
                new IntervalMeteringData(
                    "NEM1201009",
                    LocalDate.of(2005, 3, 4),
                    30,
                    new double[] {
                      0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
                      335.0, 667.0, 790.0, 1022.9999999999999, 1145.0, 1777.0, 1563.0, 1344.0,
                          1087.0, 1453.0, 996.0, 1125.0,
                      1435.0, 1263.0, 1085.0, 1487.0, 1278.0, 768.0, 878.0, 754.0, 476.0, 1045.0,
                          1132.0, 896.0,
                      879.0, 679.0, 887.0, 784.0, 954.0, 712.0, 599.0, 593.0, 674.0, 799.0, 232.0,
                          612.0
                    }),
                new IntervalMeteringData(
                    "NEM1201010",
                    LocalDate.of(2005, 3, 1),
                    30,
                    new double[] {
                      0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
                      154.0, 460.0, 770.0, 1002.9999999999999, 1059.0, 1750.0, 1423.0, 1200.0,
                          980.0, 1111.0, 800.0, 1403.0,
                      1145.0, 1173.0, 1065.0, 1187.0, 900.0, 998.0, 768.0, 1432.0, 899.0, 1211.0,
                          873.0, 786.0,
                      1504.0, 719.0, 817.0, 780.0, 709.0, 700.0, 565.0, 655.0, 543.0, 786.0, 430.0,
                          432.0
                    }),
                new IntervalMeteringData(
                    "NEM1201011",
                    LocalDate.of(2005, 3, 1),
                    30,
                    new double[] {
                      0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.154, 0.46, 0.77,
                      1.003, 1.059, 1.75, 1.423, 1.2, 0.98, 1.111, 0.8, 1.403, 1.145, 1.173, 1.065,
                      1.187, 0.9, 0.998, 0.768, 1.432, 0.899, 1.211, 0.873, 0.786, 1.504, 0.719,
                      0.817, 0.78, 0.709, 0.7, 0.565, 0.655, 0.543, 0.786, 0.43, 0.432
                    }),
                new IntervalMeteringData(
                    "NEM1201012",
                    LocalDate.of(2005, 3, 1),
                    30,
                    new double[] {
                      0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
                      154000.0, 460000.0, 770000.0, 1002999.9999999999, 1059000.0, 1750000.0,
                          1423000.0, 1200000.0, 980000.0, 1111000.0, 800000.0, 1403000.0,
                      1145000.0, 1173000.0, 1065000.0, 1187000.0, 900000.0, 998000.0, 768000.0,
                          1432000.0, 899000.0, 1211000.0, 873000.0, 786000.0,
                      1504000.0, 719000.0, 817000.0, 780000.0, 709000.0, 700000.0, 565000.0,
                          655000.0, 543000.0, 786000.0, 430000.0, 432000.0
                    }),
              });
      assertEquals(got.size(), want.size());
      for (int i = 0; i < got.size(); i++) {
        assertTrue(
            "\ngot: " + got.get(i) + "\n" + "want: " + want.get(i),
            got.get(i).equalsApprox(want.get(i), EQUALITY_EPSILON));
      }
    }
  }

  public void testFileWithOneRecordIsParsedCorrectly() throws Exception {
    try (InputStream s = getTestInputSteam("test_2.csv")) {
      assertNotNull(s);
      List<IntervalMeteringData> got = IntervalMeteringDataReader.read(s);
      List<IntervalMeteringData> want =
          Arrays.asList(
              new IntervalMeteringData[] {
                new IntervalMeteringData(
                    "NEM1201009",
                    LocalDate.of(2005, 3, 1),
                    30,
                    new double[] {
                      0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
                      461.0, 810.0, 568.0, 1234.0, 1353.0, 1507.0, 1344.0, 1773.0, 848.0, 1271.0,
                          895.0, 1327.0,
                      1012.9999999999999, 1793.0, 988.0, 985.0, 876.0, 555.0, 760.0, 938.0, 566.0,
                          512.0, 970.0, 760.0,
                      731.0, 615.0, 886.0, 531.0, 774.0, 712.0, 598.0, 670.0, 587.0, 657.0, 345.0,
                          231.0
                    })
              });
      assertEquals(got.size(), want.size());
      for (int i = 0; i < got.size(); i++) {
        assertTrue(
            "\ngot: " + got.get(i) + "\n" + "want: " + want.get(i),
            got.get(i).equalsApprox(want.get(i), EQUALITY_EPSILON));
      }
    }
  }

  public void testFileWithoutIntervalDataIsParsedCorrectly() throws Exception {
    try (InputStream s = getTestInputSteam("test_3.csv")) {
      assertNotNull(s);
      List<IntervalMeteringData> data = IntervalMeteringDataReader.read(s);
      assertTrue(data.isEmpty());
    }
  }

  private InputStream getTestInputSteam(String filePath) {
    return getClass().getClassLoader().getResourceAsStream(filePath);
  }
}
