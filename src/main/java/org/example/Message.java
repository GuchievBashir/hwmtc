package org.example;

import org.example.enrichments.*;
import java.util.Map;

public class Message {
  private Map<String, String> content;
  private final EnrichmentType enrichmentType;

  public Message(Map<String, String> content, final EnrichmentType enrichmentType) {
    this.content = content;
    this.enrichmentType = enrichmentType;
  }

  public Map<String, String> getContent() {
    return content;
  }

  public EnrichmentType getEnrichmentType() {
    return enrichmentType;
  }

  public void setContent(Map<String, String> content) {
    this.content = content;
  }
}
