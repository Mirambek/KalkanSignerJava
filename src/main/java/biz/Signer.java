/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biz;

import java.io.FileInputStream;
import java.io.InputStream;
import java.security.AccessController;
import java.security.KeyStore;
import java.security.MessageDigest;
import java.security.PrivateKey;
import java.security.PrivilegedExceptionAction;
import java.security.Provider;
import java.security.Security;
import java.security.cert.X509Certificate;
import java.util.Enumeration;

import kz.gov.pki.kalkan.asn1.pkcs.PKCSObjectIdentifiers;
import kz.gov.pki.kalkan.jce.provider.KalkanProvider;
import kz.gov.pki.kalkan.jce.provider.cms.CMSSignedData;
//import kz.gov.pki.kalkan.xmldsig.KncaXS;
import kz.gov.pki.provider.utils.KeyStoreUtil;
import kz.gov.pki.provider.utils.model.TSAProfile;
import kz.gov.pki.reference.KNCAServiceRequestMethod;
import kz.gov.pki.reference.KalkanHashAlgorithm;
import kz.gov.pki.reference.TSAPolicy;
import org.apache.xml.security.encryption.XMLCipherParameters;

import java.security.cert.Certificate;
import java.util.Arrays;

import kz.gov.pki.provider.utils.CMSUtil;
import kz.gov.pki.provider.utils.model.SigningEntity;
import org.apache.tomcat.util.codec.binary.Base64;

import org.apache.xml.security.utils.Constants;

/**
 *
 * @author miram
 */
public class Signer {
    public String Sign(String data) {
        String signed = sign("C://Certificates//gost.p12", "123456", data);
        return signed;
    }
    public String sign(final String container, String password, String data) {

        String result = null;
        try {
        Provider kalkanProvider = new KalkanProvider();
        boolean exists = false;
        Provider[] providers = Security.getProviders();
        for (Provider p : providers) {
            if (p.getName().equals(kalkanProvider.getName())) {
                exists = true;
            }
        }
        if (!exists) {
            Security.addProvider(kalkanProvider);
        }

         String providerName = kalkanProvider.getName();
        //2 или
        providerName = KalkanProvider.PROVIDER_NAME;


            final String signMethod;
            final String digestMethod;
            KeyStore store = KeyStore.getInstance("PKCS12", providerName);
            InputStream inputStream;
            inputStream = AccessController.doPrivileged(new PrivilegedExceptionAction<FileInputStream>() {
                @Override
                public FileInputStream run() throws Exception {
                    FileInputStream fis = new FileInputStream(container);
                    return fis;
                }
            });
            store.load(inputStream, password.toCharArray());
            Enumeration<String> als = store.aliases();
            String alias = null;
            while (als.hasMoreElements()) {
                alias = als.nextElement();
            }

        Base64 codec = new Base64();
        KeyStore keyStore =store;
            SigningEntity signingEntity = KeyStoreUtil.getSigningEntity(keyStore, alias, password.toCharArray());
            CMSSignedData cmsSignedData =
                   CMSUtil.createCAdES(signingEntity,codec.decode(data),false,
                           false, kalkanProvider);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }


}
