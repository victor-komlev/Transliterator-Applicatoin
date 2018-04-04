package org.vkomlev.transliterator;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@Slf4j
public class TransliterationApplication {


  public static void main(final String[] args) {
    log.info("====APPLICATION STARTED====");
    SpringApplication.run(TransliterationApplication.class, args);
  }

}
