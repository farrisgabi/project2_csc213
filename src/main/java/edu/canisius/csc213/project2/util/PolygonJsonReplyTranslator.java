package edu.canisius.csc213.project2.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.canisius.csc213.project2.quotes.StockQuote;

import java.io.IOException;

public class PolygonJsonReplyTranslator {

    public StockQuote translateJsonToFinancialInstrument(String json) throws IOException {
        // Create an ObjectMapper to parse JSON
        ObjectMapper objectMapper = new ObjectMapper();

        // Parse JSON string into a JsonNode
        JsonNode rootNode = objectMapper.readTree(json);

        // Extract data from JSON
        String symbol = rootNode.path("ticker").asText();
        JsonNode resultsNode = rootNode.path("results").path(0);
        double closePrice = resultsNode.path("c").asDouble();
        double highestPrice = resultsNode.path("h").asDouble();
        double lowestPrice = resultsNode.path("l").asDouble();
        int numberOfTransactions = resultsNode.path("n").asInt();
        double openPrice = resultsNode.path("o").asDouble();
        long timestamp = resultsNode.path("t").asLong();
        double tradingVolume = resultsNode.path("v").asDouble();

        // Create and return StockQuote object
        return new StockQuote(symbol, closePrice, highestPrice, lowestPrice, numberOfTransactions, openPrice, timestamp, tradingVolume);
    }
}
