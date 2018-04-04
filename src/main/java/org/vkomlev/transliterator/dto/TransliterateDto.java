package org.vkomlev.transliterator.dto;

import lombok.Data;

@Data
public class TransliterateDto {
  private LangEnum language;
  private String text;
}
