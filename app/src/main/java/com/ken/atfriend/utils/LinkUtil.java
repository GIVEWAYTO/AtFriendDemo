package com.ken.atfriend.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Project ID：400YF17051<br/>
 * Resume:
 *
 * @author ken
 * @e-mail gr201655@163.com
 * @see
 * @time 2017/4/15 ken first commit
 */
public class LinkUtil {

  private static final Pattern URL_PATTERN = Pattern.compile(
      "((http|https|ftp|ftps):\\/\\/)?([a-zA-Z0-9-]+\\.){1,5}(com|cn|net|org|hk|tw)((\\/(\\w|-)+(\\.([a-zA-Z]+))?)+)?(\\/)?(\\??([\\.%:a-zA-Z0-9_-]+=[#\\.%:a-zA-Z0-9_-]+(&amp;)?)+)?");

  public static String replaceUrl(String source) {
    Matcher matcher = URL_PATTERN.matcher(source);
    if (matcher.find()) {
      String url = matcher.group();

      source = source.replace(url, "<a href=" + "\'" + url + "\'" + ">🔗网页链接</a>");
    }
    return source;
  }
}
