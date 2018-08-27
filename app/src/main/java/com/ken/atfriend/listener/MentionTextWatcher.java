package com.ken.atfriend.listener;

import android.text.Editable;
import android.text.TextWatcher;
import android.text.style.ForegroundColorSpan;

import com.ken.atfriend.MentionEditText;
import com.ken.atfriend.model.Range;
import com.ken.atfriend.util.RangeManager;

import java.util.Iterator;

/**
 * Resume:
 *
 * @author ken
 * @e-mail gr201655@163.com
 * @time 2017/4/2 ken first commit
 */
public class MentionTextWatcher implements TextWatcher {
  private final MentionEditText mEditText;
  private final RangeManager mRangeManager;

  public MentionTextWatcher(MentionEditText editText) {
    this.mEditText = editText;
    this.mRangeManager = mEditText.getRangeManager();
  }

  //若从整串string中间插入字符，需要将插入位置后面的range相应地挪位
  @Override
  public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    Editable editable = mEditText.getText();
    //在末尾增加就不需要处理了
    if (start < editable.length()) {
      int end = start + count;
      int offset = after - count;

      //清理start 到 start + count之间的span
      //如果range.from = 0，也会被getSpans(0,0,ForegroundColorSpan.class)获取到
      if (start != end && !mRangeManager.isEmpty()) {
        ForegroundColorSpan[] spans = editable.getSpans(start, end, ForegroundColorSpan.class);
        for (ForegroundColorSpan span : spans) {
          editable.removeSpan(span);
        }
      }

      //清理arraylist中上面已经清理掉的range
      //将end之后的span往后挪offset个位置
      Iterator iterator = mRangeManager.iterator();
      while (iterator.hasNext()) {
        Range range = (Range) iterator.next();
        if (range.isWrapped(start, end)) {
          iterator.remove();
          continue;
        }

        if (range.getFrom() >= end) {
          range.setOffset(offset);
        }
      }
    }
  }

  @Override
  public void onTextChanged(CharSequence charSequence, int index, int i1, int count) {
  }

  @Override
  public void afterTextChanged(Editable editable) {
  }
}
