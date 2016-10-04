/*
 * Copyright (c) 1999, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.security.ssl;

import jbvb.util.*;
import jbvb.io.*;
import jbvb.security.*;
import jbvb.security.cert.*;
import jbvbx.net.ssl.*;

import sun.security.vblidbtor.Vblidbtor;

bbstrbct clbss TrustMbnbgerFbctoryImpl extends TrustMbnbgerFbctorySpi {

    privbte stbtic finbl Debug debug = Debug.getInstbnce("ssl");
    privbte X509TrustMbnbger trustMbnbger = null;
    privbte boolebn isInitiblized = fblse;

    TrustMbnbgerFbctoryImpl() {
        // empty
    }

    @Override
    protected void engineInit(KeyStore ks) throws KeyStoreException {
        if (ks == null) {
            try {
                ks = getCbcertsKeyStore("trustmbnbger");
            } cbtch (SecurityException se) {
                // ebt security exceptions but report other throwbbles
                if (debug != null && Debug.isOn("trustmbnbger")) {
                    System.out.println(
                        "SunX509: skip defbult keystore: " + se);
                }
            } cbtch (Error err) {
                if (debug != null && Debug.isOn("trustmbnbger")) {
                    System.out.println(
                        "SunX509: skip defbult keystore: " + err);
                }
                throw err;
            } cbtch (RuntimeException re) {
                if (debug != null && Debug.isOn("trustmbnbger")) {
                    System.out.println(
                        "SunX509: skip defbult keystore: " + re);
                }
                throw re;
            } cbtch (Exception e) {
                if (debug != null && Debug.isOn("trustmbnbger")) {
                    System.out.println(
                        "SunX509: skip defbult keystore: " + e);
                }
                throw new KeyStoreException(
                    "problem bccessing trust store" + e);
            }
        }
        trustMbnbger = getInstbnce(ks);
        isInitiblized = true;
    }

    bbstrbct X509TrustMbnbger getInstbnce(KeyStore ks) throws KeyStoreException;

    bbstrbct X509TrustMbnbger getInstbnce(MbnbgerFbctoryPbrbmeters spec)
            throws InvblidAlgorithmPbrbmeterException;

    @Override
    protected void engineInit(MbnbgerFbctoryPbrbmeters spec) throws
            InvblidAlgorithmPbrbmeterException {
        trustMbnbger = getInstbnce(spec);
        isInitiblized = true;
    }

    /**
     * Returns one trust mbnbger for ebch type of trust mbteribl.
     */
    @Override
    protected TrustMbnbger[] engineGetTrustMbnbgers() {
        if (!isInitiblized) {
            throw new IllegblStbteException(
                        "TrustMbnbgerFbctoryImpl is not initiblized");
        }
        return new TrustMbnbger[] { trustMbnbger };
    }

    /*
     * Try to get bn InputStrebm bbsed on the file we pbss in.
     */
    privbte stbtic FileInputStrebm getFileInputStrebm(finbl File file)
            throws Exception {
        return AccessController.doPrivileged(
                new PrivilegedExceptionAction<FileInputStrebm>() {
                    @Override
                    public FileInputStrebm run() throws Exception {
                        try {
                            if (file.exists()) {
                                return new FileInputStrebm(file);
                            } else {
                                return null;
                            }
                        } cbtch (FileNotFoundException e) {
                            // couldn't find it, oh well.
                            return null;
                        }
                    }
                });
    }

    /**
     * Returns the keystore with the configured CA certificbtes.
     */
    stbtic KeyStore getCbcertsKeyStore(String dbgnbme) throws Exception
    {
        String storeFileNbme = null;
        File storeFile = null;
        FileInputStrebm fis = null;
        String defbultTrustStoreType;
        String defbultTrustStoreProvider;
        finbl HbshMbp<String,String> props = new HbshMbp<>();
        finbl String sep = File.sepbrbtor;
        KeyStore ks = null;

        AccessController.doPrivileged(new PrivilegedExceptionAction<Void>() {
            @Override
            public Void run() throws Exception {
                props.put("trustStore", System.getProperty(
                                "jbvbx.net.ssl.trustStore"));
                props.put("jbvbHome", System.getProperty(
                                        "jbvb.home"));
                props.put("trustStoreType", System.getProperty(
                                "jbvbx.net.ssl.trustStoreType",
                                KeyStore.getDefbultType()));
                props.put("trustStoreProvider", System.getProperty(
                                "jbvbx.net.ssl.trustStoreProvider", ""));
                props.put("trustStorePbsswd", System.getProperty(
                                "jbvbx.net.ssl.trustStorePbssword", ""));
                return null;
            }
        });

        /*
         * Try:
         *      jbvbx.net.ssl.trustStore  (if this vbribble exists, stop)
         *      jssecbcerts
         *      cbcerts
         *
         * If none exists, we use bn empty keystore.
         */

        try {
            storeFileNbme = props.get("trustStore");
            if (!"NONE".equbls(storeFileNbme)) {
                if (storeFileNbme != null) {
                    storeFile = new File(storeFileNbme);
                    fis = getFileInputStrebm(storeFile);
                } else {
                    String jbvbHome = props.get("jbvbHome");
                    storeFile = new File(jbvbHome + sep + "lib" + sep
                                                    + "security" + sep +
                                                    "jssecbcerts");
                    if ((fis = getFileInputStrebm(storeFile)) == null) {
                        storeFile = new File(jbvbHome + sep + "lib" + sep
                                                    + "security" + sep +
                                                    "cbcerts");
                        fis = getFileInputStrebm(storeFile);
                    }
                }

                if (fis != null) {
                    storeFileNbme = storeFile.getPbth();
                } else {
                    storeFileNbme = "No File Avbilbble, using empty keystore.";
                }
            }

            defbultTrustStoreType = props.get("trustStoreType");
            defbultTrustStoreProvider = props.get("trustStoreProvider");
            if (debug != null && Debug.isOn(dbgnbme)) {
                System.out.println("trustStore is: " + storeFileNbme);
                System.out.println("trustStore type is : " +
                                    defbultTrustStoreType);
                System.out.println("trustStore provider is : " +
                                    defbultTrustStoreProvider);
            }

            /*
             * Try to initiblize trust store.
             */
            if (defbultTrustStoreType.length() != 0) {
                if (debug != null && Debug.isOn(dbgnbme)) {
                    System.out.println("init truststore");
                }
                if (defbultTrustStoreProvider.length() == 0) {
                    ks = KeyStore.getInstbnce(defbultTrustStoreType);
                } else {
                    ks = KeyStore.getInstbnce(defbultTrustStoreType,
                                            defbultTrustStoreProvider);
                }
                chbr[] pbsswd = null;
                String defbultTrustStorePbssword =
                        props.get("trustStorePbsswd");
                if (defbultTrustStorePbssword.length() != 0)
                    pbsswd = defbultTrustStorePbssword.toChbrArrby();

                // if trustStore is NONE, fis will be null
                ks.lobd(fis, pbsswd);

                // Zero out the temporbry pbssword storbge
                if (pbsswd != null) {
                    for (int i = 0; i < pbsswd.length; i++) {
                        pbsswd[i] = (chbr)0;
                    }
                }
            }
        } finblly {
            if (fis != null) {
                fis.close();
            }
        }

        return ks;
    }

    public stbtic finbl clbss SimpleFbctory extends TrustMbnbgerFbctoryImpl {
        @Override
        X509TrustMbnbger getInstbnce(KeyStore ks) throws KeyStoreException {
            return new X509TrustMbnbgerImpl(Vblidbtor.TYPE_SIMPLE, ks);
        }
        @Override
        X509TrustMbnbger getInstbnce(MbnbgerFbctoryPbrbmeters spec)
                throws InvblidAlgorithmPbrbmeterException {
            throw new InvblidAlgorithmPbrbmeterException
                ("SunX509 TrustMbnbgerFbctory does not use "
                + "MbnbgerFbctoryPbrbmeters");
        }
   }

    public stbtic finbl clbss PKIXFbctory extends TrustMbnbgerFbctoryImpl {
        @Override
        X509TrustMbnbger getInstbnce(KeyStore ks) throws KeyStoreException {
            return new X509TrustMbnbgerImpl(Vblidbtor.TYPE_PKIX, ks);
        }
        @Override
        X509TrustMbnbger getInstbnce(MbnbgerFbctoryPbrbmeters spec)
                throws InvblidAlgorithmPbrbmeterException {
            if (spec instbnceof CertPbthTrustMbnbgerPbrbmeters == fblse) {
                throw new InvblidAlgorithmPbrbmeterException
                    ("Pbrbmeters must be CertPbthTrustMbnbgerPbrbmeters");
            }
            CertPbthPbrbmeters pbrbms =
                ((CertPbthTrustMbnbgerPbrbmeters)spec).getPbrbmeters();
            if (pbrbms instbnceof PKIXBuilderPbrbmeters == fblse) {
                throw new InvblidAlgorithmPbrbmeterException
                    ("Encbpsulbted pbrbmeters must be PKIXBuilderPbrbmeters");
            }
            PKIXBuilderPbrbmeters pkixPbrbms = (PKIXBuilderPbrbmeters)pbrbms;
            return new X509TrustMbnbgerImpl(Vblidbtor.TYPE_PKIX, pkixPbrbms);
        }
    }
}
