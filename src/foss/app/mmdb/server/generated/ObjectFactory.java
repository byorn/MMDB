//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2012.08.15 at 02:54:02 PM WST 
//


package foss.app.mmdb.server.generated;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the foss.app.mmdb.generated package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: foss.app.mmdb.generated
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Movies }
     * 
     */
    public Movies createMovies() {
        return new Movies();
    }

    /**
     * Create an instance of {@link Movies.Movie }
     * 
     */
    public Movies.Movie createMoviesMovie() {
        return new Movies.Movie();
    }

}