package org.vkomlev.transliterator.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vkomlev.transliterator.dto.LangEnum;
import org.vkomlev.transliterator.dto.TransliterateDto;
import org.vkomlev.transliterator.util.TransliterationUtil;

import java.util.Map;

@Service
public class TransliterationServiceImpl implements TransliterationService {


  @Autowired
  private Map<LangEnum, TransliterationRule> ruleMap;

  @Override
  public TransliterateDto transliterate(TransliterateDto source) {

    TransliterationRule transliterationRule = ruleMap.get(source.getLanguage());
    if (transliterationRule == null) {
      throw new IllegalArgumentException("No rule for " + source.getLanguage());
    }

    String transliterated =
        TransliterationUtil.transliterate(source.getText(), transliterationRule);

    TransliterateDto result = new TransliterateDto();
    result.setText(transliterated);
    result.setLanguage(LangEnum.EN);
    return result;
  }
}
