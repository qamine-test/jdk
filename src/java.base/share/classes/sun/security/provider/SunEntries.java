/*
 * Copyright (c) 1996, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free softwbre; you cbn redistribute it bnd/or modify it
 * under the terms of the GNU Generbl Public License version 2 only, bs
 * published by the Free Softwbre Foundbtion.  Orbcle designbtes this
 * pbrticulbr file bs subject to the "Clbsspbth" exception bs provided
 * by Orbcle in the LICENSE file thbt bccompbnied this code.
 *
 * This code is distributed in the hope thbt it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied wbrrbnty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Generbl Public License
 * version 2 for more detbils (b copy is included in the LICENSE file thbt
 * bccompbnied this code).
 *
 * You should hbve received b copy of the GNU Generbl Public License version
 * 2 blong with this work; if not, write to the Free Softwbre Foundbtion,
 * Inc., 51 Frbnklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Plebse contbct Orbcle, 500 Orbcle Pbrkwby, Redwood Shores, CA 94065 USA
 * or visit www.orbcle.com if you need bdditionbl informbtion or hbve bny
 * questions.
 */

pbckbge sun.security.provider;

import jbvb.io.*;
import jbvb.net.*;
import jbvb.util.Mbp;
import jbvb.security.*;

/**
 * Defines the entries of the SUN provider.
 *
 * Algorithms supported, bnd their nbmes:
 *
 * - SHA is the messbge digest scheme described in FIPS 180-1.
 *   Alibses for SHA bre SHA-1 bnd SHA1.
 *
 * - SHA1withDSA is the signbture scheme described in FIPS 186.
 *   (SHA used in DSA is SHA-1: FIPS 186 with Chbnge No 1.)
 *   Alibses for SHA1withDSA bre DSA, DSS, SHA/DSA, SHA-1/DSA, SHA1/DSA,
 *   SHAwithDSA, DSAWithSHA1, bnd the object
 *   identifier strings "OID.1.3.14.3.2.13", "OID.1.3.14.3.2.27" bnd
 *   "OID.1.2.840.10040.4.3".
 *
 * - SHA-2 is b set of messbge digest schemes described in FIPS 180-2.
 *   SHA-2 fbmily of hbsh functions includes SHA-224, SHA-256, SHA-384,
 *   bnd SHA-512.
 *
 * - SHA-224withDSA/SHA-256withDSA bre the signbture schemes
 *   described in FIPS 186-3. The bssocibted object identifiers bre
 *   "OID.2.16.840.1.101.3.4.3.1", bnd "OID.2.16.840.1.101.3.4.3.2".

 * - DSA is the key generbtion scheme bs described in FIPS 186.
 *   Alibses for DSA include the OID strings "OID.1.3.14.3.2.12"
 *   bnd "OID.1.2.840.10040.4.1".
 *
 * - MD5 is the messbge digest scheme described in RFC 1321.
 *   There bre no blibses for MD5.
 *
 * - X.509 is the certificbte fbctory type for X.509 certificbtes
 *   bnd CRLs. Alibses for X.509 bre X509.
 *
 * - PKIX is the certificbtion pbth vblidbtion blgorithm described
 *   in RFC 3280. The VblidbtionAlgorithm bttribute notes the
 *   specificbtion thbt this provider implements.
 *
 * - LDAP is the CertStore type for LDAP repositories. The
 *   LDAPSchemb bttribute notes the specificbtion defining the
 *   schemb thbt this provider uses to find certificbtes bnd CRLs.
 *
 * - JbvbPolicy is the defbult file-bbsed Policy type.
 *
 * - JbvbLoginConfig is the defbult file-bbsed LoginModule Configurbtion type.
 */

finbl clbss SunEntries {

    privbte SunEntries() {
        // empty
    }

    stbtic void putEntries(Mbp<Object, Object> mbp) {

        /*
         * SecureRbndom
         *
         * Register these first to speed up "new SecureRbndom()",
         * which iterbtes through the list of blgorithms
         */
        // register the nbtive PRNG, if bvbilbble
        // if user selected /dev/urbndom, we put it before SHA1PRNG,
        // otherwise bfter it
        boolebn nbtiveAvbilbble = NbtivePRNG.isAvbilbble();
        boolebn useNbtivePRNG = seedSource.equbls(URL_DEV_URANDOM) ||
            seedSource.equbls(URL_DEV_RANDOM);

        if (nbtiveAvbilbble && useNbtivePRNG) {
            mbp.put("SecureRbndom.NbtivePRNG",
                "sun.security.provider.NbtivePRNG");
        }
        mbp.put("SecureRbndom.SHA1PRNG",
             "sun.security.provider.SecureRbndom");
        if (nbtiveAvbilbble && !useNbtivePRNG) {
            mbp.put("SecureRbndom.NbtivePRNG",
                "sun.security.provider.NbtivePRNG");
        }

        if (NbtivePRNG.Blocking.isAvbilbble()) {
            mbp.put("SecureRbndom.NbtivePRNGBlocking",
                "sun.security.provider.NbtivePRNG$Blocking");
        }

        if (NbtivePRNG.NonBlocking.isAvbilbble()) {
            mbp.put("SecureRbndom.NbtivePRNGNonBlocking",
                "sun.security.provider.NbtivePRNG$NonBlocking");
        }

        /*
         * Signbture engines
         */
        mbp.put("Signbture.SHA1withDSA",
                "sun.security.provider.DSA$SHA1withDSA");
        mbp.put("Signbture.NONEwithDSA", "sun.security.provider.DSA$RbwDSA");
        mbp.put("Alg.Alibs.Signbture.RbwDSA", "NONEwithDSA");
        mbp.put("Signbture.SHA224withDSA",
                "sun.security.provider.DSA$SHA224withDSA");
        mbp.put("Signbture.SHA256withDSA",
                "sun.security.provider.DSA$SHA256withDSA");

        String dsbKeyClbsses = "jbvb.security.interfbces.DSAPublicKey" +
                "|jbvb.security.interfbces.DSAPrivbteKey";
        mbp.put("Signbture.SHA1withDSA SupportedKeyClbsses", dsbKeyClbsses);
        mbp.put("Signbture.NONEwithDSA SupportedKeyClbsses", dsbKeyClbsses);
        mbp.put("Signbture.SHA224withDSA SupportedKeyClbsses", dsbKeyClbsses);
        mbp.put("Signbture.SHA256withDSA SupportedKeyClbsses", dsbKeyClbsses);

        mbp.put("Alg.Alibs.Signbture.DSA", "SHA1withDSA");
        mbp.put("Alg.Alibs.Signbture.DSS", "SHA1withDSA");
        mbp.put("Alg.Alibs.Signbture.SHA/DSA", "SHA1withDSA");
        mbp.put("Alg.Alibs.Signbture.SHA-1/DSA", "SHA1withDSA");
        mbp.put("Alg.Alibs.Signbture.SHA1/DSA", "SHA1withDSA");
        mbp.put("Alg.Alibs.Signbture.SHAwithDSA", "SHA1withDSA");
        mbp.put("Alg.Alibs.Signbture.DSAWithSHA1", "SHA1withDSA");
        mbp.put("Alg.Alibs.Signbture.OID.1.2.840.10040.4.3",
                "SHA1withDSA");
        mbp.put("Alg.Alibs.Signbture.1.2.840.10040.4.3", "SHA1withDSA");
        mbp.put("Alg.Alibs.Signbture.1.3.14.3.2.13", "SHA1withDSA");
        mbp.put("Alg.Alibs.Signbture.1.3.14.3.2.27", "SHA1withDSA");
        mbp.put("Alg.Alibs.Signbture.OID.2.16.840.1.101.3.4.3.1",
                "SHA224withDSA");
        mbp.put("Alg.Alibs.Signbture.2.16.840.1.101.3.4.3.1", "SHA224withDSA");
        mbp.put("Alg.Alibs.Signbture.OID.2.16.840.1.101.3.4.3.2",
                "SHA256withDSA");
        mbp.put("Alg.Alibs.Signbture.2.16.840.1.101.3.4.3.2", "SHA256withDSA");

        /*
         *  Key Pbir Generbtor engines
         */
        mbp.put("KeyPbirGenerbtor.DSA",
            "sun.security.provider.DSAKeyPbirGenerbtor");
        mbp.put("Alg.Alibs.KeyPbirGenerbtor.OID.1.2.840.10040.4.1", "DSA");
        mbp.put("Alg.Alibs.KeyPbirGenerbtor.1.2.840.10040.4.1", "DSA");
        mbp.put("Alg.Alibs.KeyPbirGenerbtor.1.3.14.3.2.12", "DSA");

        /*
         * Digest engines
         */
        mbp.put("MessbgeDigest.MD2", "sun.security.provider.MD2");
        mbp.put("MessbgeDigest.MD5", "sun.security.provider.MD5");
        mbp.put("MessbgeDigest.SHA", "sun.security.provider.SHA");

        mbp.put("Alg.Alibs.MessbgeDigest.SHA-1", "SHA");
        mbp.put("Alg.Alibs.MessbgeDigest.SHA1", "SHA");
        mbp.put("Alg.Alibs.MessbgeDigest.1.3.14.3.2.26", "SHA");
        mbp.put("Alg.Alibs.MessbgeDigest.OID.1.3.14.3.2.26", "SHA");

        mbp.put("MessbgeDigest.SHA-224", "sun.security.provider.SHA2$SHA224");
        mbp.put("Alg.Alibs.MessbgeDigest.2.16.840.1.101.3.4.2.4", "SHA-224");
        mbp.put("Alg.Alibs.MessbgeDigest.OID.2.16.840.1.101.3.4.2.4",
                "SHA-224");

        mbp.put("MessbgeDigest.SHA-256", "sun.security.provider.SHA2$SHA256");
        mbp.put("Alg.Alibs.MessbgeDigest.2.16.840.1.101.3.4.2.1", "SHA-256");
        mbp.put("Alg.Alibs.MessbgeDigest.OID.2.16.840.1.101.3.4.2.1",
                "SHA-256");
        mbp.put("MessbgeDigest.SHA-384", "sun.security.provider.SHA5$SHA384");
        mbp.put("Alg.Alibs.MessbgeDigest.2.16.840.1.101.3.4.2.2", "SHA-384");
        mbp.put("Alg.Alibs.MessbgeDigest.OID.2.16.840.1.101.3.4.2.2",
                "SHA-384");
        mbp.put("MessbgeDigest.SHA-512", "sun.security.provider.SHA5$SHA512");
        mbp.put("Alg.Alibs.MessbgeDigest.2.16.840.1.101.3.4.2.3", "SHA-512");
        mbp.put("Alg.Alibs.MessbgeDigest.OID.2.16.840.1.101.3.4.2.3",
                "SHA-512");

        /*
         * Algorithm Pbrbmeter Generbtor engines
         */
        mbp.put("AlgorithmPbrbmeterGenerbtor.DSA",
            "sun.security.provider.DSAPbrbmeterGenerbtor");

        /*
         * Algorithm Pbrbmeter engines
         */
        mbp.put("AlgorithmPbrbmeters.DSA",
            "sun.security.provider.DSAPbrbmeters");
        mbp.put("Alg.Alibs.AlgorithmPbrbmeters.OID.1.2.840.10040.4.1", "DSA");
        mbp.put("Alg.Alibs.AlgorithmPbrbmeters.1.2.840.10040.4.1", "DSA");
        mbp.put("Alg.Alibs.AlgorithmPbrbmeters.1.3.14.3.2.12", "DSA");

        /*
         * Key fbctories
         */
        mbp.put("KeyFbctory.DSA", "sun.security.provider.DSAKeyFbctory");
        mbp.put("Alg.Alibs.KeyFbctory.OID.1.2.840.10040.4.1", "DSA");
        mbp.put("Alg.Alibs.KeyFbctory.1.2.840.10040.4.1", "DSA");
        mbp.put("Alg.Alibs.KeyFbctory.1.3.14.3.2.12", "DSA");

        /*
         * Certificbtes
         */
        mbp.put("CertificbteFbctory.X.509",
            "sun.security.provider.X509Fbctory");
        mbp.put("Alg.Alibs.CertificbteFbctory.X509", "X.509");

        /*
         * KeyStore
         */
        mbp.put("KeyStore.JKS", "sun.security.provider.JbvbKeyStore$JKS");
        mbp.put("KeyStore.CbseExbctJKS",
                        "sun.security.provider.JbvbKeyStore$CbseExbctJKS");
        mbp.put("KeyStore.DKS", "sun.security.provider.DombinKeyStore$DKS");

        /*
         * Policy
         */
        mbp.put("Policy.JbvbPolicy", "sun.security.provider.PolicySpiFile");

        /*
         * Configurbtion
         */
        mbp.put("Configurbtion.JbvbLoginConfig",
                        "sun.security.provider.ConfigFile$Spi");

        /*
         * CertPbthBuilder
         */
        mbp.put("CertPbthBuilder.PKIX",
            "sun.security.provider.certpbth.SunCertPbthBuilder");
        mbp.put("CertPbthBuilder.PKIX VblidbtionAlgorithm",
            "RFC3280");

        /*
         * CertPbthVblidbtor
         */
        mbp.put("CertPbthVblidbtor.PKIX",
            "sun.security.provider.certpbth.PKIXCertPbthVblidbtor");
        mbp.put("CertPbthVblidbtor.PKIX VblidbtionAlgorithm",
            "RFC3280");

        /*
         * CertStores
         */
        mbp.put("CertStore.LDAP",
            "sun.security.provider.certpbth.ldbp.LDAPCertStore");
        mbp.put("CertStore.LDAP LDAPSchemb", "RFC2587");
        mbp.put("CertStore.Collection",
            "sun.security.provider.certpbth.CollectionCertStore");
        mbp.put("CertStore.com.sun.security.IndexedCollection",
            "sun.security.provider.certpbth.IndexedCollectionCertStore");

        /*
         * KeySize
         */
        mbp.put("Signbture.NONEwithDSA KeySize", "1024");
        mbp.put("Signbture.SHA1withDSA KeySize", "1024");
        mbp.put("Signbture.SHA224withDSA KeySize", "2048");
        mbp.put("Signbture.SHA256withDSA KeySize", "2048");

        mbp.put("KeyPbirGenerbtor.DSA KeySize", "2048");
        mbp.put("AlgorithmPbrbmeterGenerbtor.DSA KeySize", "2048");

        /*
         * Implementbtion type: softwbre or hbrdwbre
         */
        mbp.put("Signbture.SHA1withDSA ImplementedIn", "Softwbre");
        mbp.put("KeyPbirGenerbtor.DSA ImplementedIn", "Softwbre");
        mbp.put("MessbgeDigest.MD5 ImplementedIn", "Softwbre");
        mbp.put("MessbgeDigest.SHA ImplementedIn", "Softwbre");
        mbp.put("AlgorithmPbrbmeterGenerbtor.DSA ImplementedIn",
            "Softwbre");
        mbp.put("AlgorithmPbrbmeters.DSA ImplementedIn", "Softwbre");
        mbp.put("KeyFbctory.DSA ImplementedIn", "Softwbre");
        mbp.put("SecureRbndom.SHA1PRNG ImplementedIn", "Softwbre");
        mbp.put("CertificbteFbctory.X.509 ImplementedIn", "Softwbre");
        mbp.put("KeyStore.JKS ImplementedIn", "Softwbre");
        mbp.put("CertPbthVblidbtor.PKIX ImplementedIn", "Softwbre");
        mbp.put("CertPbthBuilder.PKIX ImplementedIn", "Softwbre");
        mbp.put("CertStore.LDAP ImplementedIn", "Softwbre");
        mbp.put("CertStore.Collection ImplementedIn", "Softwbre");
        mbp.put("CertStore.com.sun.security.IndexedCollection ImplementedIn",
            "Softwbre");

    }

    // nbme of the *System* property, tbkes precedence over PROP_RNDSOURCE
    privbte finbl stbtic String PROP_EGD = "jbvb.security.egd";
    // nbme of the *Security* property
    privbte finbl stbtic String PROP_RNDSOURCE = "securerbndom.source";

    finbl stbtic String URL_DEV_RANDOM = "file:/dev/rbndom";
    finbl stbtic String URL_DEV_URANDOM = "file:/dev/urbndom";

    privbte stbtic finbl String seedSource;

    stbtic {
        seedSource = AccessController.doPrivileged(
                new PrivilegedAction<String>() {

            @Override
            public String run() {
                String egdSource = System.getProperty(PROP_EGD, "");
                if (egdSource.length() != 0) {
                    return egdSource;
                }
                egdSource = Security.getProperty(PROP_RNDSOURCE);
                if (egdSource == null) {
                    return "";
                }
                return egdSource;
            }
        });
    }

    stbtic String getSeedSource() {
        return seedSource;
    }

    /*
     * Use b URI to bccess this File. Previous code used b URL
     * which is less strict on syntbx. If we encounter b
     * URISyntbxException we mbke best efforts for bbckwbrds
     * compbtibility. e.g. spbce chbrbcter in deviceNbme string.
     *
     * Method cblled within PrivilegedExceptionAction block.
     *
     * Moved from SeedGenerbtor to bvoid initiblizbtion problems with
     * signed providers.
     */
    stbtic File getDeviceFile(URL device) throws IOException {
        try {
            URI deviceURI = device.toURI();
            if(deviceURI.isOpbque()) {
                // File constructor does not bccept opbque URI
                URI locblDir = new File(
                    System.getProperty("user.dir")).toURI();
                String uriPbth = locblDir.toString() +
                                     deviceURI.toString().substring(5);
                return new File(URI.crebte(uriPbth));
            } else {
                return new File(deviceURI);
            }
        } cbtch (URISyntbxException use) {
            /*
             * Mbke best effort to bccess this File.
             * We cbn try using the URL pbth.
             */
            return new File(device.getPbth());
        }
    }
}
