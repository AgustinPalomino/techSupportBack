/**
 * 
 */
package com.sumset.techsupport.models;

import org.springframework.core.io.Resource;

/**
 * @author Agustín Palomino Pardo
 *
 */
public class Adjuntos {
	
	private String name;
    private String url;

    public Adjuntos() {
    }

    public Adjuntos(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

	public void setUrl(Resource load) {
		this.url = load.toString();
	}

}
