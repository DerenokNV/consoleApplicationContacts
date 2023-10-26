package org.example;

import org.example.data.ResultParsing;
import org.springframework.context.annotation.Profile;

import java.io.*;

@Profile("init")
public class ContactInit {

  private String initFilePath;
  private final AnalizScanner analizScanner;
  private final ContactRepository contactRepository;

  public ContactInit( AnalizScanner analizScanner, ContactRepository contactRepository, String initFilePath ) {
    this.analizScanner = analizScanner;
    this.contactRepository = contactRepository;
    this.initFilePath = initFilePath;
    initFile();
  }

  private void initFile() {
    if ( initFilePath == null ) {
      return;
    }

    File file = new File( initFilePath );
    try ( FileReader fr = new FileReader( file );
          BufferedReader reader = new BufferedReader( fr ); ) {

      String line = reader.readLine();
      while( line != null ) {
        ResultParsing contact = analizScanner.createContactEntity( line );
        if ( contact.getContact() != null ) {
          contactRepository.addContact( contact.getContact() );
        }
        line = reader.readLine();
      }
    } catch ( IOException ex ) {
      throw new RuntimeException( ex );
    }

  }

}
