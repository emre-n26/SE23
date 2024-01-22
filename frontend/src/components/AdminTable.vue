<template>
  <h3>{{ title }}</h3>
  <div>
    <input id="new-quote-text" v-model="newQuoteText" placeholder="Neuer Spruch" type="text" ref="nameInput" />
    <input v-model="newQuoteIndex" placeholder="Position" @keyup.enter="save()" />
    <button id="save-button" type="button" @click="save()">Save</button>
    <!--TODO hier Suchfeld einfügen-->
  </div>
  <div>
    <table>
      <thead>
        <tr>
          <th>Quote</th>
          <th>Index</th>
          <th>Votes</th>
        </tr>
      </thead>
      <tbody>
        <tr v-if="quotes.length === 0">
          <td colspan="2">No quotes yet</td>
        </tr>
        <tr v-for="quote in quotes" :key="quote.index">
          <td>{{ quote.quote }}</td>
          <td>{{ quote.index }}</td>
          <td>{{ quote.votes }}</td>
          <!--TODO hier Up- und Downvote-Buttons einfügen-->
            <td>
                <button :id="'upvote-button-' + quote.index" @click="voteQuote(quote.index, 'up')">+</button>
                <button :id="'downvote-button-' + quote.index" @click="voteQuote(quote.index, 'down')">-</button>
            </td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script>
export default {
  name: "AdminTable",
  props: ["title"],
  data() {
    return {
      quotes: [],
      newQuoteText: "",
      newQuoteIndex: ""
    };
  },
  methods: {
    loadQuotes() {
      const endpoint = "http://localhost:8080/api/v1/quotes";
      const requestOptions = {
        method: "GET",
        redirect: "follow",
      };
      fetch(endpoint, requestOptions)
        .then((response) => response.json())
        .then((result) => {
          this.quotes = [];
          result.forEach((thing) => {
            this.quotes.push(thing);
          })
        })
        .catch((error) => console.log("error", error));
    },
    save() {
      const endpoint = "http://localhost:8080/api/v1/quote/" + this.newQuoteIndex;
      const data = {
        quote: this.newQuoteText,
        index: this.newQuoteIndex,
        votes: 0,
      };
      const requestOptions = {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(data),
      };
      fetch(endpoint, requestOptions)
        .then((response) => response.json())
        .then((data) => {
          console.log("Success:", data);
          this.loadQuotes();
        })
        .catch((error) => console.log("error", error));
    },
    // TODO hier voteQuote Methode einfügen
      voteQuote(index, action) {
          const quote = this.quotes.find(q => q.index === index);
          if (quote) {
              if (action === "up") {
                  quote.votes++;
              } else if (action === "down") {
                  quote.votes--;
              }

              const endpoint = `http://localhost:8080/api/v1/quote/${quote.index}`;
              const data = {
                  votes: quote.votes
              };
              const requestOptions = {
                  method: "PUT",
                  headers: {
                      "Content-Type": "application/json",
                  },
                  body: JSON.stringify(data),
              };
              fetch(endpoint, requestOptions)
                  .then((response) => response.json())
                  .then((data) => {
                      console.log("Success:", data);
                  })
                  .catch((error) => console.log("error", error));
          }
    // TODO hier sortQuotesBy Methode einfügen
      sortQuotesBy(criteria); {
          this.quotes.sort((a, b) => {
              if (criteria === 'index') {
                  return a.index - b.index;
              } else if (criteria === 'votes') {
                  return b.votes - a.votes;
              }
          });
      }

  },
  created() {
    this.loadQuotes();
  },
},
}
</script>

<style scoped></style>
