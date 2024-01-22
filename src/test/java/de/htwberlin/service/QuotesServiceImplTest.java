package de.htwberlin.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class QuotesServiceImplTest {

    @Test
    @DisplayName("should throw Exception when index is out of range")
    void throwQuote() {
        var sut = new QuotesServiceImpl();
        assertThrows(IllegalArgumentException.class, () -> sut.getQuote(-42));
    }

    @Test
    @DisplayName("should return first quote")
    void getQuote() {
        var sut = new QuotesServiceImpl();
        String expected = "Chuck Norris dreht nicht die Dusche auf. Er starrt sie an, bis sie zu weinen anfängt.";
        assertEquals(expected, sut.getQuote(0).getQuote());
    }

    @Test
    @DisplayName("should upvote a quote so that the index of following quotes is decreased by one")
    void upvoteQuote() {
        var sut = new QuotesServiceImpl();
        sut.voteQuote(42, 1);
        int expected = 1;
        assertEquals(expected, sut.getQuote(42).getVotes());
    }

    //TODO hier zwei Testfälle für die Methode addQuote(index, quote) hinzufügen

    @Test
    @DisplayName("should add a quote at the specified index and increase the total number of quotes")
    void addQuoteAtIndex() {
        var sut = new QuotesServiceImpl();
        int initialSize = sut.getAllQuotes().size();
        String newQuote = "This is a new quote.";
        int indexToAdd = 2;

        sut.addQuote(indexToAdd, newQuote);

        List<VotableQuote> quotes = sut.getAllQuotes();
        assertEquals(initialSize + 1, quotes.size());
        assertEquals(newQuote, quotes.get(indexToAdd).getQuote());
    }

    @Test
    @DisplayName("should throw Exception when trying to add a quote at an invalid index")
    void throwExceptionWhenAddingQuoteAtInvalidIndex() {
        var sut = new QuotesServiceImpl();
        String newQuote = "This is a new quote.";
        int invalidIndex = -1;

        assertThrows(IllegalArgumentException.class, () -> sut.addQuote(invalidIndex, newQuote));
    }


}
