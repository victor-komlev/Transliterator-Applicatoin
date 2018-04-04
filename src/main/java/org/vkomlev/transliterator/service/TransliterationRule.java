package org.vkomlev.transliterator.service;

import lombok.Data;

import java.util.Map;

@Data
public class TransliterationRule {
  private String source;
  private Map<String, String> singleLetters;
  private Map<String, String> doubleLetters;
}
