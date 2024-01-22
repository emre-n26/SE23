package de.htwberlin.service;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuotesServiceImpl implements QuotesService {

    List<VotableQuote> quotes = QUOTES.stream().map(q -> new VotableQuote(q, 0)).collect(Collectors.toList());

    @Override
    public VotableQuote getQuote(int index) {
        if(index >= quotes.size() || index < 0) throw new IllegalArgumentException("invalid index");
        return quotes.get(index);
    }

    @Override
    public void addQuote(int index, String quote) {
        if(index >= quotes.size() || index < 0) throw new IllegalArgumentException("invalid index");
        quotes.add(index, new VotableQuote(quote, 0));
    }

    @Override
    public List<VotableQuote> getAllQuotes() {
        return quotes;
    }

    @Override
    public void voteQuote(int index, int upOrDown) {
        // TODO hier implementieren

            if (index < 0 || index >= quotes.size()) {
                throw new IllegalArgumentException("Invalid index");
            }

            VotableQuote quote = quotes.get(index);
            int currentVotes = quote.getVotes();

            if (upOrDown > 0) {
                quote.setVotes(currentVotes + 1);
            } else if (upOrDown < 0) {
                if (currentVotes > 0) {
                    quote.setVotes(currentVotes - 1);
                }
            }
    }
}
