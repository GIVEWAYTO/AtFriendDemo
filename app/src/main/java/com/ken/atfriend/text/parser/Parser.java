package com.ken.atfriend.text.parser;


import android.text.Html;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;

import com.ken.atfriend.text.listener.ParserConverter;
import com.ken.atfriend.utils.LinkUtil;

/**
 * Project ID：
 * Resume:
 *
 * @author ken
 * @e-mail gr201655@163.com
 * @see
 * @time 2017/4/9 ken first commit
 */
public class Parser implements ParserConverter {

  public Parser() {
  }

  @Override
  public Spanned convert(CharSequence source) {
    if (TextUtils.isEmpty(source)) return new SpannableString("");
    String sourceString = source.toString();
    sourceString = LinkUtil.replaceUrl(sourceString);

    return Html.fromHtml(sourceString, null, new HtmlTagHandler());
  }
}
