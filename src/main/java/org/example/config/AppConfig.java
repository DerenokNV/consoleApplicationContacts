package org.example.config;

import org.example.AnalizScanner;
import org.example.ContactInit;
import org.example.ContactRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;


@ComponentScan("org.example")
@Configuration
@PropertySource(value = "classpath:application.yaml", factory = YamlPropertySourceFactory.class)
public class AppConfig {

  @Value("${init.file.path}")
  String initFilePath;

  @Bean
  public ContactRepository beanContactRepository() {
    return new ContactRepository();
  }

  @Bean
  public AnalizScanner beanAnalizScanner() {
    return new AnalizScanner( beanContactRepository() );
  }

  @Bean
  @Profile("init")
  public ContactInit beanContactInit() {
    return new ContactInit( beanAnalizScanner(), beanContactRepository(), initFilePath );
  }


}
