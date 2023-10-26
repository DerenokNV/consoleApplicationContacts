package org.example;

import org.example.config.AppConfig;
import org.example.data.ResultParsing;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Scanner;

public class Main {
  public static void main( String[] args ) {
    ApplicationContext context = new AnnotationConfigApplicationContext( AppConfig.class );

    Scanner scanner = new Scanner( System.in );
    info();

    AnalizScanner analizScanner = context.getBean( AnalizScanner.class );
    String readString;
    while( ( readString = scanner.nextLine() ) != null ) {

      ResultParsing res = analizScanner.parsingStringScanner( readString );
      if ( res.isEnd() ) {
        break;
      }
      if ( res.isUnclear() ) {
        info();
      } else if ( res.isPrint() ) {
        context.getBean( ContactRepository.class ).prinatAllContacts();
      } else {
        System.out.println( res.getResultStr() );
      }
    }
  }

  private static void info() {
    System.out.println("Для ввода Контакта в систему, ведите " + Constant.SCANN_ADD + "Контакт(в формате фио;телефон;емаил)");
    System.out.println("Для просмотра всех имеющихся контактов, введите " + Constant.SCANN_PRINT );
    System.out.println("Для сохранения всех имеющихся контактов в файл, введите " + Constant.SCANN_SAVEFILE );
    System.out.println("Для завершения работы, введите " + Constant.SCANN_END );
  }
}