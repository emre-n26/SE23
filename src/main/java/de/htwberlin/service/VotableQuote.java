package de.htwberlin.service;

public class VotableQuote {

    private String quote;
    private int votes;

    public VotableQuote(String quote, int votes) {
        this.quote = quote;
        this.votes = votes;
    }

    public VotableQuote() { }

    public String getQuote() {
        return quote;
    }

    public void setQuote(String quote) {
        this.quote = quote;
    }

    public int getVotes() {
        return votes;
    }

    public void setVotes(int votes) {
        this.votes = votes;
    }
}
