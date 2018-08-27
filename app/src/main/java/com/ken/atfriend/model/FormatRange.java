package com.ken.atfriend.model;

/**
 * Project ID：400YF17051
 * Resume:
 *
 * @author ken
 * @e-mail gr201655@163.com
 * @time 2018/8/25 11:12
 */
public class FormatRange extends Range {

  private FormatData convert;

  public FormatRange(int from, int to) {
    super(from, to);
  }

  public FormatData getConvert() {
    return convert;
  }

  public interface FormatData {

    CharSequence formatCharSequence();
  }

  private CharSequence rangeCharSequence;

  public void setConvert(FormatData convert) {
    this.convert = convert;
  }

  public CharSequence getRangeCharSequence() {
    return rangeCharSequence;
  }

  public void setRangeCharSequence(CharSequence rangeCharSequence) {
    this.rangeCharSequence = rangeCharSequence;
  }
}
