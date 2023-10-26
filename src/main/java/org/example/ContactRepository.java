package org.example;

import org.example.data.ContactEntity;
import org.springframework.beans.factory.annotation.Value;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


public class ContactRepository {

  @Value("${save.file.path}")
  String uploadPath;

  private final List<ContactEntity> contacts;

  public ContactRepository() {
    contacts = new ArrayList<>();
  }

  public void addContact( ContactEntity contact ) {
    contacts.add( contact );
  }

  public void prinatAllContacts() {
    if ( contacts.isEmpty() ) {
      System.out.println( Constant.INFO_PRINT );
    }

    for ( ContactEntity contact : contacts ) {
      contact.printInfo();
    }
  }

  public void saveContactsFile() throws IOException {
    if ( ! new File( uploadPath ).exists() ) {
      Files.createDirectories( Paths.get( uploadPath ) );
    }
    String fileName = "allContacts.txt";
    Path path = Paths.get( uploadPath, fileName );

    try ( var fstream = new OutputStreamWriter( new FileOutputStream( path.toFile()  ) ) ) {
      for ( ContactEntity contact : contacts ) {
        fstream.write( contact.getInfoContact() + "\n" );
      }

    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

}
