package org.improving.utility;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;

public class FileUtil
{

   /**
    * Please add valid API Keys to client_secret.txt under resources. Each Key should be placed on a
    * new line.
    */
   public static LinkedList< String > getKeys()
   {
      URL res = FileUtil.class.getClassLoader()
                              .getResource( "client_secret.txt" );
      try
      {
         return new LinkedList<>( Files.readAllLines( Paths.get( res.toURI() )
                                                           .toFile()
                                                           .toPath(),
                                                      Charset.defaultCharset() ) );
      }
      catch( IOException | URISyntaxException e )
      {
         e.printStackTrace();
      }
      return null;
   }

   @Override
   public String toString()
   {
      return super.toString();
   }
}
