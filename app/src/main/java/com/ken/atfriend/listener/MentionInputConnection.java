package com.ken.atfriend.listener;

import android.view.KeyEvent;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputConnectionWrapper;

import com.ken.atfriend.MentionEditText;
import com.ken.atfriend.model.Range;
import com.ken.atfriend.util.RangeManager;


/**
 * Resume:
 *
 * @author ken
 * @e-mail gr201655@163.com
 * @time 2017/4/2 ken first commit
 *///handle the deletion action for mention string, such as '@test'
public class MentionInputConnection extends InputConnectionWrapper {
  private final MentionEditText mEditText;
  private final RangeManager mRangeManager;

  public MentionInputConnection(InputConnection target, boolean mutable, MentionEditText editText) {
    super(target, mutable);
    this.mEditText = editText;
    this.mRangeManager = editText.getRangeManager();
  }

  @Override
  public boolean sendKeyEvent(KeyEvent event) {
    if (event.getAction() == KeyEvent.ACTION_DOWN && event.getKeyCode() == KeyEvent.KEYCODE_DEL) {
      if (null != mRangeManager) {
        int selectionStart = mEditText.getSelectionStart();
        int selectionEnd = mEditText.getSelectionEnd();
        Range closestRange =
            mRangeManager.getRangeOfClosestMentionString(selectionStart, selectionEnd);
        if (closestRange == null) {
          mEditText.setSelected(false);
          return super.sendKeyEvent(event);
        }
        //if mention string has been selected or the cursor is at the beginning of mention string, just use default action(delete)
        if (mEditText.isSelected() || selectionStart == closestRange.getFrom()) {
          mEditText.setSelected(false);
          return super.sendKeyEvent(event);
        } else {
          //select the mention string
          mEditText.setSelected(true);
          mRangeManager.setLastSelectedRange(closestRange);
          setSelection(closestRange.getTo(), closestRange.getFrom());
        }
        return true;
      }
    }
    return super.sendKeyEvent(event);
  }

  @Override
  public boolean deleteSurroundingText(int beforeLength, int afterLength) {
    if (beforeLength == 1 && afterLength == 0) {
      return sendKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_DEL)) && sendKeyEvent(
          new KeyEvent(KeyEvent.ACTION_UP, KeyEvent.KEYCODE_DEL));
    }
    return super.deleteSurroundingText(beforeLength, afterLength);
  }
}
