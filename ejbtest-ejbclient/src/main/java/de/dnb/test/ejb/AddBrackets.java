package de.dnb.test.ejb;

import javax.ejb.Remote;

@Remote
public interface AddBrackets {

	String processText(String text);

}
