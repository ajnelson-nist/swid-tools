/**
 * Portions of this software was developed by employees of the National Institute
 * of Standards and Technology (NIST), an agency of the Federal Government and is
 * being made available as a public service. Pursuant to title 17 United States
 * Code Section 105, works of NIST employees are not subject to copyright
 * protection in the United States. This software may be subject to foreign
 * copyright. Permission in the United States and in foreign countries, to the
 * extent that NIST may hold copyright, to use, copy, modify, create derivative
 * works, and distribute this software and its documentation without fee is hereby
 * granted on a non-exclusive basis, provided that this notice and disclaimer
 * of warranty appears in all copies.
 *
 * THE SOFTWARE IS PROVIDED 'AS IS' WITHOUT ANY WARRANTY OF ANY KIND, EITHER
 * EXPRESSED, IMPLIED, OR STATUTORY, INCLUDING, BUT NOT LIMITED TO, ANY WARRANTY
 * THAT THE SOFTWARE WILL CONFORM TO SPECIFICATIONS, ANY IMPLIED WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE, AND FREEDOM FROM
 * INFRINGEMENT, AND ANY WARRANTY THAT THE DOCUMENTATION WILL CONFORM TO THE
 * SOFTWARE, OR ANY WARRANTY THAT THE SOFTWARE WILL BE ERROR FREE.  IN NO EVENT
 * SHALL NIST BE LIABLE FOR ANY DAMAGES, INCLUDING, BUT NOT LIMITED TO, DIRECT,
 * INDIRECT, SPECIAL OR CONSEQUENTIAL DAMAGES, ARISING OUT OF, RESULTING FROM,
 * OR IN ANY WAY CONNECTED WITH THIS SOFTWARE, WHETHER OR NOT BASED UPON WARRANTY,
 * CONTRACT, TORT, OR OTHERWISE, WHETHER OR NOT INJURY WAS SUSTAINED BY PERSONS OR
 * PROPERTY OR OTHERWISE, AND WHETHER OR NOT LOSS WAS SUSTAINED FROM, OR AROSE OUT
 * OF THE RESULTS OF, OR USE OF, THE SOFTWARE OR SERVICES PROVIDED HEREUNDER.
 */

package gov.nist.secauto.swid.client.service;

import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContexts;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

import javax.net.ssl.SSLContext;

public class HttpClientFactory {

  private static final Logger LOG = LogManager.getLogger(HttpClientFactory.class);

  /**
   * Construct a HTTP client
   * 
   * @param identityKeyStore
   *          the client keystore
   * @param clientCertificatePassword
   *          the password for the client certificate the password of the client certificate
   * @return the Http client
   * @throws KeyStoreException
   *           if there are errors with loading client certificate
   * @throws NoSuchAlgorithmException
   *           if there are errors
   * @throws CertificateException
   *           if there are errors with loading client certificate
   * @throws IOException
   *           if there are errors with read or write
   * @throws KeyManagementException
   *           if there are errors with certificate
   * @throws UnrecoverableKeyException
   *           if there are errors with loading client certificate
   */
  public CloseableHttpClient build(KeyStore identityKeyStore, String clientCertificatePassword)
      throws KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException, KeyManagementException,
      UnrecoverableKeyException {
    LOG.info("Building Http Client");
    SSLContext sslContext = SSLContexts.custom()
        // load identity keystore
        .loadKeyMaterial(identityKeyStore, clientCertificatePassword.toCharArray(), null).build();

    SSLConnectionSocketFactory sslConnectionSocketFactory = new SSLConnectionSocketFactory(sslContext,
        new String[] { "TLSv1.2" }, null, SSLConnectionSocketFactory.getDefaultHostnameVerifier());

    CloseableHttpClient client = HttpClients.custom().setSSLSocketFactory(sslConnectionSocketFactory).build();

    return client;

  }

  /**
   * Instantiate Java Keystore for the client certificate
   * 
   * @param clientCertificatePath
   *          path to client certificate
   * @param clientCertificatePassword
   *          the password of the client certificate
   * @return the keystore instance
   * @throws KeyStoreException
   *           if there are errors with loading client certificate
   * @throws NoSuchAlgorithmException
   *           if there are errors
   * @throws CertificateException
   *           if there are errors with loading client certificate
   * @throws IOException
   *           if there are errors with read or write
   */
  public KeyStore loadKeyStore(String clientCertificatePath, String clientCertificatePassword)
      throws NoSuchAlgorithmException, CertificateException, IOException, KeyStoreException {

    KeyStore identityKeyStore = KeyStore.getInstance("PKCS12");

    FileInputStream identityKeyStoreFile = new FileInputStream(clientCertificatePath);
    identityKeyStore.load(identityKeyStoreFile, clientCertificatePassword.toCharArray());

    return identityKeyStore;
  }

}
