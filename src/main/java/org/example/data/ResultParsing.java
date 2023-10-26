package org.example.data;

public class ResultParsing {

  private boolean end;
  private boolean unclear;
  private boolean print;
  private String resultStr;
  private ContactEntity contact;

  public ResultParsing() {
  }

  public ResultParsing( String resultStr ) {
    this( resultStr, false );
  }

  public ResultParsing( String resultStr, boolean end ) {
    this.setResultStr( resultStr );
    this.setEnd( end );
  }

  public boolean isEnd() {
    return end;
  }

  public void setEnd(boolean end) {
    this.end = end;
  }

  public String getResultStr() {
    return resultStr;
  }

  public void setResultStr(String resultStr) {
    this.resultStr = resultStr;
  }

  public ContactEntity getContact() {
    return contact;
  }

  public void setContact(ContactEntity contact) {
    this.contact = contact;
  }

  public boolean isUnclear() {
    return unclear;
  }

  public void setUnclear(boolean unclear) {
    this.unclear = unclear;
  }

  public boolean isPrint() {
    return print;
  }

  public void setPrint(boolean print) {
    this.print = print;
  }
}
