package org.vkomlev.transliterator.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.vkomlev.transliterator.dto.LangEnum;
import org.vkomlev.transliterator.service.TransliterationRule;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

@Configuration
@Slf4j
public class TransliteratorConfig {

  @Autowired
  private ObjectMapper objectMapper;

  @Value("classpath*:/*.json")
  private Resource[] resourceList;

  @Bean
  public Map<LangEnum, TransliterationRule> getRules() throws IOException {

    Map<LangEnum, TransliterationRule> transliterationRuleMap = Arrays.asList(resourceList).stream()
        .collect(Collectors.toMap(resource -> LangEnum
            .valueOf(StringUtils.substringBefore(resource.getFilename(), ".json")), resource -> {
          TransliterationRule transliterationRule = null;
          try {
            String resourceJson = IOUtils.toString(resource.getInputStream());
            transliterationRule = objectMapper.readValue(resourceJson, TransliterationRule.class);
          } catch (IOException e) {
            log.error("Error reading resource file {}", resource.getFilename(), e);
          }
          return transliterationRule;
        }));

    return transliterationRuleMap;
  }
}
