package org.example;

import org.example.data.ContactEntity;
import org.example.data.ResultParsing;


public class AnalizScanner {

  private final ContactRepository contactRepository;

  public AnalizScanner( ContactRepository contactRepository ) {
    this.contactRepository = contactRepository;
  }

  public ResultParsing parsingStringScanner( String scannerStr ) {
    if ( scannerStr.contains( Constant.SCANN_ADD ) ) {
      if ( Constant.SCANN_ADD.equals( scannerStr ) ) {
        return new ResultParsing( Constant.ERROR_ADD_ALLEMPLY );
      }

      ResultParsing contact = createContactEntity( scannerStr.substring(Constant.SCANN_ADD.length() ) );
      if ( contact.getContact() == null ) {
        return contact;
      }

      contactRepository.addContact( contact.getContact() );
      return contact;

    } else if ( scannerStr.contains( Constant.SCANN_END ) ) {
      return new ResultParsing(null, true);

    } else if ( scannerStr.contains( Constant.SCANN_PRINT ) ) {
      ResultParsing result = new ResultParsing();
      result.setPrint(true);
      return result;

    } else if ( scannerStr.contains( Constant.SCANN_SAVEFILE ) ) {
      try {
        contactRepository.saveContactsFile();
        return new ResultParsing( Constant.INFO_SAVE );
      } catch ( Exception e ) {
        return new ResultParsing( Constant.ERROR_SAVE + ", " + e.getMessage() );
      }
    } else {
      ResultParsing result = new ResultParsing();
      result.setUnclear( true );
      return result;
    }
  }

  public ResultParsing createContactEntity( String params ) {
    String[] values = params.split( ";" );

    if ( values.length < 3 ) {
      return new ResultParsing( Constant.ERROR_ADD_EMPLY );
    }
    ContactEntity contact = new ContactEntity();
    for( String value : values ) {
      if ( value.contains( "@" ) ) {
        contact.setEmail( value );
      } else if ( value.contains( "+" ) ) {
        contact.setPhoneNumber( value );
      } else {
        contact.setFullName( value );
      }
    }

    if ( ! contact.isValidData() ) {
      return new ResultParsing( Constant.ERROR_ADD_INFO );
    }

    ResultParsing result = new ResultParsing( Constant.INFO_ADD_CONTACT );
    result.setContact( contact );
    return result;
  }

}
