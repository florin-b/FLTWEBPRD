package model;

import java.io.Serializable;

public class NavigationDetails implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String link;
	private String text;

	public NavigationDetails() {

	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	@Override
	public String toString() {
		return "NavigationDetails [link=" + link + ", text=" + text + "]";
	}

}
