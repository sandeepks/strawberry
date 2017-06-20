package com.dataworks.hive;


/**
 * <p>
 * Contains constant definitions for the standard {@link Format} instances
 * supported by the library. {@link #AVRO} is the default format.
 * </p>
 *
 * @since 0.2.0
 */
public class Formats {

  private Formats() {
  }

  /**
   * AVRO: the
   * <a href="http://avro.apache.org/docs/current/spec.html#Object+Container+Files">
   * Avro row-oriented format</a>
   */
  public static final Format AVRO = new Format("avro", CompressionType.Snappy,
      new CompressionType[] { CompressionType.Uncompressed, CompressionType.Snappy, CompressionType.Deflate, CompressionType.Bzip2 });

  /**
   * CSV: comma-separated values (read-only).
   *
   * @since 0.9.0
   */
  public static final Format CSV = new Format("csv", CompressionType.Uncompressed,
      new CompressionType[] { CompressionType.Uncompressed });

  /**
   * INPUTFORMAT: a mapreduce InputFormat (read-only).
   *
   * @since 0.18.0
   */
  public static final Format INPUTFORMAT = new Format("inputformat", CompressionType.Uncompressed,
      new CompressionType[] { CompressionType.Uncompressed });

  /**
   * Return a {@link Format} for the format name specified. If {@code formatName}
   * is not a valid name, an IllegalArgumentException is thrown. Currently the
   * formats <q>avro</q>, <q>csv</q>, and <q>parquet</q> are supported. Format names are
   * case sensitive.
   *
   * @since 0.9.0
   * @return an appropriate instance of Format
   * @throws IllegalArgumentException if {@code formatName} is not a valid format.
   */
  public static Format fromString(String formatName) {
    if (formatName.equals("avro")) {
      return AVRO;
    } else if (formatName.equals("csv")) {
      return CSV;
    } else if (formatName.equals("inputformat")) {
      return INPUTFORMAT;
    } else {
      throw new IllegalArgumentException("Unknown format type: " + formatName);
    }
  }

}
