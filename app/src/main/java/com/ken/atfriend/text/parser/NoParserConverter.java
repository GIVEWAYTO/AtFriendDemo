package com.ken.atfriend.text.parser;

import android.text.SpannableString;
import android.text.Spanned;

import com.ken.atfriend.text.listener.ParserConverter;


/**
 * Project ID：
 * Resume:
 *
 * @author ken
 * @e-mail gr201655@163.com
 * @see
 * @time 2017/4/9 ken first commit
 */
public class NoParserConverter implements ParserConverter {

  @Override
  public Spanned convert(CharSequence source) {
    return new SpannableString(source);
  }
}
