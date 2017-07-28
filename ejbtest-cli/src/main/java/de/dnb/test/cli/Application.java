/*
 * Copyright 2017 Christoph Böhme
 *
 * Licensed under the Apache License, Version 2.0 the "License";
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package de.dnb.test.cli;

import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import de.dnb.test.ejb.AddBrackets;

public final class Application {

  public static void main(String[] args) throws NamingException {
    final Properties jndiProperties = initJndiProperties();
    final AddBrackets addBrackets = getEjb(jndiProperties);

    System.out.println(addBrackets.processText("Hello World"));
  }

  private static Properties initJndiProperties() {
    final Properties jndiProperties = new Properties();
    jndiProperties.put(Context.INITIAL_CONTEXT_FACTORY,
        "org.jboss.naming.remote.client.InitialContextFactory");
    jndiProperties.put("jboss.naming.client.ejb.context", true);
    jndiProperties.put(Context.PROVIDER_URL, "http-remoting://localhost:8080/");
    //jndiProperties.put(Context.SECURITY_PRINCIPAL, "test");
    //jndiProperties.put(Context.SECURITY_CREDENTIALS, "test");
    return jndiProperties;
  }

  private static AddBrackets getEjb(Properties jndiProps)
      throws NamingException {
    final Context jndiContext = new InitialContext(jndiProps);
    final String interfaceName = AddBrackets.class.getName();
    return (AddBrackets) jndiContext.lookup(
        "ejbtest-app-1.0-SNAPSHOT/ejbtest-ejb-1.0-SNAPSHOT/AddBracketsBean!"
            + interfaceName);
  }

}
