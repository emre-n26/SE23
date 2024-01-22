package de.htwberlin.web.api;

import de.htwberlin.service.QuotesService;
import de.htwberlin.service.VotableQuote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class QuoteController {

    @Autowired
    QuotesService quotesService;

    public QuoteController() {}

    @GetMapping("/api/v1/quote")
    public QuoteResponse getQuote(@RequestParam int index) {
        VotableQuote vq = quotesService.getQuote(index);
        return new QuoteResponse(vq.getQuote(), index, vq.getVotes());
    }

    @GetMapping("/api/v1/quotes")
    public List<QuoteResponse> getQuotes() {
        List<QuoteResponse> result = new ArrayList<>();
        List<VotableQuote> quotes = quotesService.getAllQuotes();
        for (int i = 0; i < quotes.size(); i++) {
            VotableQuote vq = quotes.get(i);
            result.add(new QuoteResponse(vq.getQuote(), i, vq.getVotes()));
        }
        return result;
    }

    // TODO hier eine Route zum Speichern von neuen Sprüchen hinzufügen

    @PostMapping("/api/v1/quote/{index}")
    public void saveQuote(@PathVariable int index, @RequestBody QuoteResponse newQuote) {
        VotableQuote votableQuote = new VotableQuote(newQuote.getQuote(), newQuote.getVotes());

        quotesService.addQuote(index, votableQuote);
    }

    // TODO hier eine Route zum Bewerten von neuen Sprüchen hinzufügen

    @PutMapping("/api/v1/vote")
    public void voteQuote(@RequestParam("id") int index, @RequestParam("vote") int vote) {
        // Verwenden Sie die Service-Methode zum Bewerten des Spruchs
        quotesService.voteQuote(index, vote);
    }


}
