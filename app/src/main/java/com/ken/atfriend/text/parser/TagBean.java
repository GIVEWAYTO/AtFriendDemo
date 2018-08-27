package com.ken.atfriend.text.parser;

/**
 * Project ID：400YF17051<br/>
 * Resume:
 *
 * @author ken
 * @e-mail gr201655@163.com
 * @see
 * @time 2017/4/15 ken first commit
 */
public class TagBean {

  private String name;
  private String id;

  public TagBean(String name, String id) {
    this.name = name;
    this.id = id;
  }

  @Override
  public String toString() {
    return "TagBean{" +
        "name='" + name + '\'' +
        ", id='" + id + '\'' +
        '}';
  }
}
