package de.htwberlin.web.api;

public class QuoteResponse {

  private String quote;
  private int index;
  private int votes;

  public QuoteResponse(String quote, int index, int votes) {
    this.quote = quote;
    this.index = index;
    this.votes = votes;
  }

  public QuoteResponse() {
  }

  public String getQuote() {
    return quote;
  }

  public void setQuote(String quote) {
    this.quote = quote;
  }

  public int getIndex() {
    return index;
  }

  public void setIndex(int index) {
    this.index = index;
  }

  public int getVotes() {
    return votes;
  }

  public void setVotes(int votes) {
    this.votes = votes;
  }
}
