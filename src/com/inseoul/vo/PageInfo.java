package com.inseoul.vo;

public class PageInfo {
  private int id, group, page, limit, maxPage, startPage, endPage, listCount;
  private String category;
  public int getStartRow() {
    return ((page - 1) * limit);
  }
  public int getId() {
    return id;
  }
  public void setId(int id) {
    this.id = id;
  }
  public int getGroup() {
    return group;
  }
  public void setGroup(int group) {
    this.group = group;
  }
  public int getPage() {
    return page;
  }
  public void setPage(int page) {
    this.page = page;
  }
  public int getLimit() {
    return limit;
  }
  public void setLimit(int limit) {
    this.limit = limit;
  }
  public int getMaxPage() {
    return maxPage;
  }
  public void setMaxPage(int maxPage) {
    this.maxPage = maxPage;
  }
  public int getStartPage() {
    return startPage;
  }
  public void setStartPage(int startPage) {
    this.startPage = startPage;
  }
  public int getEndPage() {
    return endPage;
  }
  public void setEndPage(int endPage) {
    this.endPage = endPage;
  }
  public int getListCount() {
    return listCount;
  }
  public void setListCount(int listCount) {
    this.listCount = listCount;
  }
  public String getCategory() {
    return category;
  }
  public void setCategory(String category) {
    this.category = category;
  }
  
  
}
