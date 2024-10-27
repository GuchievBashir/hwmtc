package org.example.enrichments;

import java.util.Map;

public interface EnrichmentInterface {
   Map<String, String> enrich(Map<String, String> input);
}
