package com.ken.atfriend.utils;

import android.text.Editable;

import org.xml.sax.XMLReader;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * Project ID：400YF17051<br/>
 * Resume:
 *
 * @author ken
 * @e-mail gr201655@163.com
 * @see
 * @time 2017/4/15 ken first commit
 */
public class HtmlParserUtil {

  public static Map<String, String> parseStart(String tag, Editable output, XMLReader xmlReader) {
    Map<String, String> attibute = new HashMap<>();
    try {
      Field elementField = xmlReader.getClass().getDeclaredField("theNewElement");
      elementField.setAccessible(true);
      Object element = elementField.get(xmlReader);
      Field attsField = element.getClass().getDeclaredField("theAtts");
      attsField.setAccessible(true);
      Object atts = attsField.get(element);
      Field dataField = atts.getClass().getDeclaredField("data");
      dataField.setAccessible(true);
      String[] data = (String[]) dataField.get(atts);
      Field lengthField = atts.getClass().getDeclaredField("length");
      lengthField.setAccessible(true);
      int len = (Integer) lengthField.get(atts);
      for (int i = 0; i < len; i++) {
        attibute.put(data[i * 5 + 1], data[i * 5 + 4]);
      }
    } catch (NoSuchFieldException e) {
      e.printStackTrace();
    } catch (IllegalAccessException e) {
      e.printStackTrace();
    }

    return attibute;
  }
}
