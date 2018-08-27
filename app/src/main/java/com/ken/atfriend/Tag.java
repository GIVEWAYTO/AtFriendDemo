package com.ken.atfriend;

import android.graphics.Color;

import com.ken.atfriend.listener.InsertData;
import com.ken.atfriend.model.FormatRange;

import java.io.Serializable;

/**
 * Resume:
 *
 * @author ken
 * @e-mail gr201655@163.com
 * @see
 * @time 2017/4/3 ken first commit
 */
public class Tag implements Serializable, InsertData {

  private final CharSequence tagLable;
  private final CharSequence tagId;

  private CharSequence tagUrl;

  public Tag(CharSequence tagLable, CharSequence tagId) {
    this.tagLable = tagLable;
    this.tagId = tagId;
  }

  public CharSequence getTagLable() {
    return tagLable;
  }

  public CharSequence getTagId() {
    return tagId;
  }

  public CharSequence getTagUrl() {
    return tagUrl;
  }

  public void setTagUrl(CharSequence tagUrl) {
    this.tagUrl = tagUrl;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Tag tag = (Tag) o;

    if (tagLable != null ? !tagLable.equals(tag.tagLable) : tag.tagLable != null) return false;
    if (tagId != null ? !tagId.equals(tag.tagId) : tag.tagId != null) return false;
    return tagUrl != null ? tagUrl.equals(tag.tagUrl) : tag.tagUrl == null;
  }

  @Override
  public int hashCode() {
    int result = tagLable != null ? tagLable.hashCode() : 0;
    result = 31 * result + (tagId != null ? tagId.hashCode() : 0);
    result = 31 * result + (tagUrl != null ? tagUrl.hashCode() : 0);
    return result;
  }

  @Override
  public CharSequence charSequence() {
    return "#" + tagLable + "#";
  }

  @Override
  public FormatRange.FormatData formatData() {
    return new TagConvert(this);
  }

  @Override
  public int color() {
    return Color.GREEN;
  }

  private class TagConvert implements FormatRange.FormatData {
    public static final String TAG_FORMAT = "^#%s&&%s#^";
    private final Tag tag;

    public TagConvert(Tag tag) {
      this.tag = tag;
    }

    @Override
    public CharSequence formatCharSequence() {
      return String.format(TAG_FORMAT, tag.getTagId(),tag.tagLable);
    }
  }
}
