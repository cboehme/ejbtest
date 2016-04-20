package de.dnb.test.ejb;

import javax.ejb.Stateless;

@Stateless
public class AddBracketsBean implements AddBrackets {

	public String processText(final String text) {
		return "[[[" + text + "]]]";
	}

}
