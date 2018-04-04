package org.vkomlev.transliterator.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.vkomlev.transliterator.dto.TransliterateDto;
import org.vkomlev.transliterator.service.TransliterationServiceImpl;

@RestController
@RequestMapping("transliterator")
public class TransliteratorController {

  @Autowired
  private TransliterationServiceImpl transliterationServiceImpl;


  @ApiOperation(value = "Transliterate", notes = "Accepts only RU or UA locales.")
  @PostMapping("/")
  public TransliterateDto transliterate(
      @ApiParam(value = "Transliteration Request", required = true) @RequestBody
          TransliterateDto transliterateRequest) {

    return transliterationServiceImpl.transliterate(transliterateRequest);
  }

}
