package org.example.enrichments;

import org.example.Message;

import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.*;

public class EnrichmentService {
  private ConcurrentHashMap<EnrichmentType, EnrichmentInterface> enrichments = new ConcurrentHashMap<>();
  private CopyOnWriteArrayList<KeyAndValue> copyofEnrichments = new CopyOnWriteArrayList<>();

  public void addEnrichment(EnrichmentType type, EnrichmentInterface enrichment) {
    KeyAndValue keyAndValue = new KeyAndValue(type, enrichment);
    copyofEnrichments.add(keyAndValue);
  }

  public record KeyAndValue(EnrichmentType type, EnrichmentInterface enrichment) {}

  public void updateEnrichment() {
    for (KeyAndValue keyAndValue : copyofEnrichments) {
      EnrichmentInterface enrichment = keyAndValue.enrichment;
      EnrichmentType type = keyAndValue.type;
      enrichments.put(type, enrichment);
    }
    copyofEnrichments.clear();
  }

  public Message enrich(Message message) {
    if (message == null) {return null;}
    Map<String, String> messageContent = message.getContent();
    EnrichmentType enrichmentType = message.getEnrichmentType();
    updateEnrichment();
    EnrichmentInterface enrichment = enrichments.get(enrichmentType);
    if (enrichment == null) {
      return message;
    }
    Map<String, String> enrichmentContent = enrichment.enrich(messageContent);
    message.setContent(enrichmentContent);
    return message;
  }

  public ArrayList<EnrichmentType> printEnrichmentType() {
    updateEnrichment();
    return new ArrayList<>(this.enrichments.keySet());
  }
}
