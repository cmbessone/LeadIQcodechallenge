package com.stocktracker.stocktracker.adapter.output;

import com.stocktracker.stocktracker.adapter.output.dto.PolygonResponse;
import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class PolygonClient {

  private final RestTemplate restTemplate;
  private final String apiKey;

  public PolygonClient() {
    this.restTemplate = new RestTemplate();
    Dotenv dotenv = Dotenv.configure().directory(System.getProperty("user.dir")).load();
    this.apiKey = dotenv.get("POLYGON_API_KEY");
    if (this.apiKey == null || this.apiKey.isEmpty()) {
      throw new IllegalStateException("API key 'POLYGON_API_KEY' not set in .env'");
    }
  }

  public PolygonResponse fetchStockData(String symbol, String from, String to) {
    String url =
        UriComponentsBuilder.fromHttpUrl(
                "https://api.polygon.io/v2/aggs/ticker/"
                    + symbol
                    + "/range/1/day/"
                    + from
                    + "/"
                    + to)
            .queryParam("adjusted", "true")
            .queryParam("sort", "asc")
            .queryParam("limit", "120")
            .queryParam("apiKey", apiKey)
            .toUriString();

    return restTemplate.getForObject(url, PolygonResponse.class);
  }
}
