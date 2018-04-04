package org.vkomlev.transliterator.util;

import org.apache.commons.lang3.StringUtils;
import org.vkomlev.transliterator.service.TransliterationRule;

public class TransliterationUtil {

  public static String transliterate(String text, TransliterationRule transliterationRule) {
    StringBuffer stringBuffer = new StringBuffer(text);

    if (transliterationRule.getDoubleLetters() != null) {
      transliterationRule.getDoubleLetters().forEach((original, transliterated) -> {
        replaceAll(stringBuffer, original, transliterated, 0);
      });
    }

    if (transliterationRule.getSingleLetters() != null) {
      transliterationRule.getSingleLetters().forEach((original, transliterated) -> {
        replaceAll(stringBuffer, original, transliterated, 0);
      });
    }

    return stringBuffer.toString();
  }

  private static void replaceAll(StringBuffer text, String source, String target,
      int currentPosition) {
    String[] targetOptions = target.split(",");
    int indexOfPattern = StringUtils.indexOfIgnoreCase(text, source, currentPosition);
    String targetString;
    if (indexOfPattern == 0) {
      targetString = targetOptions.length > 1 ? targetOptions[1] : targetOptions[0];
    } else if (indexOfPattern >= 1) {
      targetString = targetOptions[0];
    } else {
      return;
    }
    text.replace(indexOfPattern, indexOfPattern + source.length(), targetString);
    replaceAll(text, source, target,
        StringUtils.lastIndexOf(text, targetString) + targetString.length());
  }
}

