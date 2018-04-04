package org.vkomlev.transliterator.service;

import org.vkomlev.transliterator.dto.TransliterateDto;

public interface TransliterationService {
  TransliterateDto transliterate(TransliterateDto source);
}
