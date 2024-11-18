/**
 * 
 */
package com.asurion.spc.http;

import java.net.URI;

import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;

/**
 * Since apache HttpDelete does not have a set entity on it
 * 
 * @author djpichay
 *
 */
public class HttpDelete extends HttpEntityEnclosingRequestBase {

	public final static String METHOD_NAME = "DELETE";


    public HttpDelete() {
        super();
    }

    public HttpDelete(final URI uri) {
        super();
        setURI(uri);
    }

    /**
     * @throws IllegalArgumentException if the uri is invalid.
     */
    public HttpDelete(final String uri) {
        super();
        setURI(URI.create(uri));
    }

    @Override
    public String getMethod() {
        return METHOD_NAME;
    }
	
}
