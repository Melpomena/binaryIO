package com.nix.binaryio.processor.decoder;

/**
 * Service in charge of reading and writing from a history, as required by {@link
 * DecoderByteProcessor}. History is represented by a byte array with max size of maxIndex. When the
 * end of the array is reached, values are written again starting from the beginning, overriding
 * already existing values at those indices, ie the array shifts and wraps to the left.
 */
public class DecoderHistoryService {

  private final int maxIndex;

  private byte[] history;
  private int currentIndex = 0;
  private int repetitions = 0;

  public DecoderHistoryService(int maxIndex) {
    this.maxIndex = maxIndex;
    this.history = new byte[maxIndex];
  }

  /**
   * Write to the history
   *
   * @param newValues byte array of new values to be written to the history
   */
  public void write(byte[] newValues) {
    for (int i = 0; i < newValues.length; ++i) {
      this.history[currentIndex] = newValues[i];
      increaseCurrentIndex();
    }
  }

  /**
   * Read from the history.
   *
   * @param pi From where in the history to start reading
   * @param qi How many elements to read from the starting position
   * @return byte array containing the read elements
   */
  public byte[] read(int pi, int qi) {
    int startIndex = (currentIndex - pi) % maxIndex;
    int endIndex = Math.min(maxIndex, startIndex + qi);

    byte[] decodedValues = new byte[qi];
    int decodedValuesIndex = 0;

    for (int i = startIndex; i < endIndex; ++i) {
      decodedValues[decodedValuesIndex++] = this.history[i];
    }

    if (decodedValuesIndex < qi) {
      for (int i = 0; i < (qi - decodedValuesIndex); ++i) {
        decodedValues[decodedValuesIndex++] = this.history[i];
      }
    }
    return decodedValues;
  }

  /**
   * For the first iteration, before left wrapping, make sure that pi is not greater than the
   * current index, as it would try to read elements from the history before they were stored.
   *
   * @param pi From where in the memory to start reading
   * @return true if pi is valid and reading operation can safely be called
   */
  public boolean isValid(int pi) {
    if (repetitions == 0) {
      return pi < currentIndex;
    }
    return true;
  }

  /**
   * Increase the current index. Current index always represents where in the history array are we
   * currently storing values. This method deals with the shifting and wrapping to the left.
   */
  private void increaseCurrentIndex() {
    currentIndex++;
    if (currentIndex >= maxIndex) {
      currentIndex %= maxIndex;
      this.repetitions += 1;
    }
  }

  public byte[] getHistory() {
    return history;
  }

  public int getCurrentIndex() {
    return currentIndex;
  }
}
