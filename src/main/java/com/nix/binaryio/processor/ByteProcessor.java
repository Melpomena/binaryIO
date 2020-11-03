package com.nix.binaryio.processor;

/**
 * Interface for ByteProcessor implementations which receive an array of bytes, process them and
 * return a new processed array of bytes.
 */
public interface ByteProcessor {

  /**
   * Method that applies some logic on an array of bytes. Logic differs per implementation.
   *
   * @param input bytes array that requires processing
   * @return new byte array after applying processing logic
   */
  byte[] process(byte[] input);
}
