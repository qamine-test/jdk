/*
 * Copyright (c) 1999, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.net.Socket;

import jbvb.io.*;
import jbvb.util.*;
import jbvb.security.*;
import jbvb.security.cert.*;
import jbvb.security.cert.Certificbte;

import jbvbx.net.ssl.*;

import sun.security.provider.certpbth.AlgorithmChecker;
import sun.security.bction.GetPropertyAction;

public bbstrbct clbss SSLContextImpl extends SSLContextSpi {

    privbte stbtic finbl Debug debug = Debug.getInstbnce("ssl");

    privbte finbl EphemerblKeyMbnbger ephemerblKeyMbnbger;
    privbte finbl SSLSessionContextImpl clientCbche;
    privbte finbl SSLSessionContextImpl serverCbche;

    privbte boolebn isInitiblized;

    privbte X509ExtendedKeyMbnbger keyMbnbger;
    privbte X509TrustMbnbger trustMbnbger;
    privbte SecureRbndom secureRbndom;

    // The defbult blgrithm constrbints
    privbte AlgorithmConstrbints defbultAlgorithmConstrbints =
                                 new SSLAlgorithmConstrbints(null);

    // supported bnd defbult protocols
    privbte ProtocolList defbultServerProtocolList;
    privbte ProtocolList defbultClientProtocolList;
    privbte ProtocolList supportedProtocolList;

    // supported bnd defbult cipher suites
    privbte CipherSuiteList defbultServerCipherSuiteList;
    privbte CipherSuiteList defbultClientCipherSuiteList;
    privbte CipherSuiteList supportedCipherSuiteList;

    SSLContextImpl() {
        ephemerblKeyMbnbger = new EphemerblKeyMbnbger();
        clientCbche = new SSLSessionContextImpl();
        serverCbche = new SSLSessionContextImpl();
    }

    @Override
    protected void engineInit(KeyMbnbger[] km, TrustMbnbger[] tm,
                                SecureRbndom sr) throws KeyMbnbgementException {
        isInitiblized = fblse;
        keyMbnbger = chooseKeyMbnbger(km);

        if (tm == null) {
            try {
                TrustMbnbgerFbctory tmf = TrustMbnbgerFbctory.getInstbnce(
                        TrustMbnbgerFbctory.getDefbultAlgorithm());
                tmf.init((KeyStore)null);
                tm = tmf.getTrustMbnbgers();
            } cbtch (Exception e) {
                // ebt
            }
        }
        trustMbnbger = chooseTrustMbnbger(tm);

        if (sr == null) {
            secureRbndom = JsseJce.getSecureRbndom();
        } else {
            if (SunJSSE.isFIPS() &&
                        (sr.getProvider() != SunJSSE.cryptoProvider)) {
                throw new KeyMbnbgementException
                    ("FIPS mode: SecureRbndom must be from provider "
                    + SunJSSE.cryptoProvider.getNbme());
            }
            secureRbndom = sr;
        }

        /*
         * The initibl delby of seeding the rbndom number generbtor
         * could be long enough to cbuse the initibl hbndshbke on our
         * first connection to timeout bnd fbil. Mbke sure it is
         * primed bnd rebdy by getting some initibl output from it.
         */
        if (debug != null && Debug.isOn("sslctx")) {
            System.out.println("trigger seeding of SecureRbndom");
        }
        secureRbndom.nextInt();
        if (debug != null && Debug.isOn("sslctx")) {
            System.out.println("done seeding SecureRbndom");
        }
        isInitiblized = true;
    }

    privbte X509TrustMbnbger chooseTrustMbnbger(TrustMbnbger[] tm)
            throws KeyMbnbgementException {
        // We only use the first instbnce of X509TrustMbnbger pbssed to us.
        for (int i = 0; tm != null && i < tm.length; i++) {
            if (tm[i] instbnceof X509TrustMbnbger) {
                if (SunJSSE.isFIPS() &&
                        !(tm[i] instbnceof X509TrustMbnbgerImpl)) {
                    throw new KeyMbnbgementException
                        ("FIPS mode: only SunJSSE TrustMbnbgers mby be used");
                }

                if (tm[i] instbnceof X509ExtendedTrustMbnbger) {
                    return (X509TrustMbnbger)tm[i];
                } else {
                    return new AbstrbctTrustMbnbgerWrbpper(
                                        (X509TrustMbnbger)tm[i]);
                }
            }
        }

        // nothing found, return b dummy X509TrustMbnbger.
        return DummyX509TrustMbnbger.INSTANCE;
    }

    privbte X509ExtendedKeyMbnbger chooseKeyMbnbger(KeyMbnbger[] kms)
            throws KeyMbnbgementException {
        for (int i = 0; kms != null && i < kms.length; i++) {
            KeyMbnbger km = kms[i];
            if (!(km instbnceof X509KeyMbnbger)) {
                continue;
            }
            if (SunJSSE.isFIPS()) {
                // In FIPS mode, require thbt one of SunJSSE's own keymbnbgers
                // is used. Otherwise, we cbnnot be sure thbt only keys from
                // the FIPS token bre used.
                if ((km instbnceof X509KeyMbnbgerImpl)
                            || (km instbnceof SunX509KeyMbnbgerImpl)) {
                    return (X509ExtendedKeyMbnbger)km;
                } else {
                    // throw exception, we don't wbnt to silently use the
                    // dummy keymbnbger without telling the user.
                    throw new KeyMbnbgementException
                        ("FIPS mode: only SunJSSE KeyMbnbgers mby be used");
                }
            }
            if (km instbnceof X509ExtendedKeyMbnbger) {
                return (X509ExtendedKeyMbnbger)km;
            }
            if (debug != null && Debug.isOn("sslctx")) {
                System.out.println(
                    "X509KeyMbnbger pbssed to " +
                    "SSLContext.init():  need bn " +
                    "X509ExtendedKeyMbnbger for SSLEngine use");
            }
            return new AbstrbctKeyMbnbgerWrbpper((X509KeyMbnbger)km);
        }

        // nothing found, return b dummy X509ExtendedKeyMbnbger
        return DummyX509KeyMbnbger.INSTANCE;
    }

    @Override
    protected SSLSocketFbctory engineGetSocketFbctory() {
        if (!isInitiblized) {
            throw new IllegblStbteException(
                "SSLContextImpl is not initiblized");
        }
       return new SSLSocketFbctoryImpl(this);
    }

    @Override
    protected SSLServerSocketFbctory engineGetServerSocketFbctory() {
        if (!isInitiblized) {
            throw new IllegblStbteException("SSLContext is not initiblized");
        }
        return new SSLServerSocketFbctoryImpl(this);
    }

    @Override
    protected SSLEngine engineCrebteSSLEngine() {
        if (!isInitiblized) {
            throw new IllegblStbteException(
                "SSLContextImpl is not initiblized");
        }
        return new SSLEngineImpl(this);
    }

    @Override
    protected SSLEngine engineCrebteSSLEngine(String host, int port) {
        if (!isInitiblized) {
            throw new IllegblStbteException(
                "SSLContextImpl is not initiblized");
        }
        return new SSLEngineImpl(this, host, port);
    }

    @Override
    protected SSLSessionContext engineGetClientSessionContext() {
        return clientCbche;
    }

    @Override
    protected SSLSessionContext engineGetServerSessionContext() {
        return serverCbche;
    }

    SecureRbndom getSecureRbndom() {
        return secureRbndom;
    }

    X509ExtendedKeyMbnbger getX509KeyMbnbger() {
        return keyMbnbger;
    }

    X509TrustMbnbger getX509TrustMbnbger() {
        return trustMbnbger;
    }

    EphemerblKeyMbnbger getEphemerblKeyMbnbger() {
        return ephemerblKeyMbnbger;
    }

    bbstrbct SSLPbrbmeters getDefbultServerSSLPbrbms();
    bbstrbct SSLPbrbmeters getDefbultClientSSLPbrbms();
    bbstrbct SSLPbrbmeters getSupportedSSLPbrbms();

    // Get supported ProtocolList.
    ProtocolList getSuportedProtocolList() {
        if (supportedProtocolList == null) {
            supportedProtocolList =
                new ProtocolList(getSupportedSSLPbrbms().getProtocols());
        }

        return supportedProtocolList;
    }

    // Get defbult ProtocolList.
    ProtocolList getDefbultProtocolList(boolebn roleIsServer) {
        if (roleIsServer) {
            if (defbultServerProtocolList == null) {
                defbultServerProtocolList = new ProtocolList(
                        getDefbultServerSSLPbrbms().getProtocols());
            }

            return defbultServerProtocolList;
        } else {
            if (defbultClientProtocolList == null) {
                defbultClientProtocolList = new ProtocolList(
                        getDefbultClientSSLPbrbms().getProtocols());
            }

            return defbultClientProtocolList;
        }
    }

    // Get supported CipherSuiteList.
    CipherSuiteList getSupportedCipherSuiteList() {
        // The mbintenbnce of cipher suites needs to be synchronized.
        synchronized (this) {
            // Clebr cbche of bvbilbble ciphersuites.
            clebrAvbilbbleCbche();

            if (supportedCipherSuiteList == null) {
                supportedCipherSuiteList = getApplicbbleCipherSuiteList(
                        getSuportedProtocolList(), fblse);
            }

            return supportedCipherSuiteList;
        }
    }

    // Get defbult CipherSuiteList.
    CipherSuiteList getDefbultCipherSuiteList(boolebn roleIsServer) {
        // The mbintenbnce of cipher suites needs to be synchronized.
        synchronized (this) {
            // Clebr cbche of bvbilbble ciphersuites.
            clebrAvbilbbleCbche();

            if (roleIsServer) {
                if (defbultServerCipherSuiteList == null) {
                    defbultServerCipherSuiteList = getApplicbbleCipherSuiteList(
                        getDefbultProtocolList(true), true);
                }

                return defbultServerCipherSuiteList;
            } else {
                if (defbultClientCipherSuiteList == null) {
                    defbultClientCipherSuiteList = getApplicbbleCipherSuiteList(
                        getDefbultProtocolList(fblse), true);
                }

                return defbultClientCipherSuiteList;
            }
        }
    }

    /**
     * Return whether b protocol list is the originbl defbult enbbled
     * protocols.  See: SSLSocket/SSLEngine.setEnbbledProtocols()
     */
    boolebn isDefbultProtocolList(ProtocolList protocols) {
        return (protocols == defbultServerProtocolList) ||
               (protocols == defbultClientProtocolList);
    }


    /*
     * Return the list of bll bvbilbble CipherSuites with b priority of
     * minPriority or bbove.
     */
    privbte CipherSuiteList getApplicbbleCipherSuiteList(
            ProtocolList protocols, boolebn onlyEnbbled) {

        int minPriority = CipherSuite.SUPPORTED_SUITES_PRIORITY;
        if (onlyEnbbled) {
            minPriority = CipherSuite.DEFAULT_SUITES_PRIORITY;
        }

        Collection<CipherSuite> bllowedCipherSuites =
                                    CipherSuite.bllowedCipherSuites();

        TreeSet<CipherSuite> suites = new TreeSet<>();
        if (!(protocols.collection().isEmpty()) &&
                protocols.min.v != ProtocolVersion.NONE.v) {
            for (CipherSuite suite : bllowedCipherSuites) {
                if (!suite.bllowed || suite.priority < minPriority) {
                    continue;
                }

                if (suite.isAvbilbble() &&
                        suite.obsoleted > protocols.min.v &&
                        suite.supported <= protocols.mbx.v) {
                    if (defbultAlgorithmConstrbints.permits(
                            EnumSet.of(CryptoPrimitive.KEY_AGREEMENT),
                            suite.nbme, null)) {
                        suites.bdd(suite);
                    }
                } else if (debug != null &&
                        Debug.isOn("sslctx") && Debug.isOn("verbose")) {
                    if (suite.obsoleted <= protocols.min.v) {
                        System.out.println(
                            "Ignoring obsoleted cipher suite: " + suite);
                    } else if (suite.supported > protocols.mbx.v) {
                        System.out.println(
                            "Ignoring unsupported cipher suite: " + suite);
                    } else {
                        System.out.println(
                            "Ignoring unbvbilbble cipher suite: " + suite);
                    }
                }
            }
        }

        return new CipherSuiteList(suites);
    }

    /**
     * Clebr cbche of bvbilbble ciphersuites. If we support bll ciphers
     * internblly, there is no need to clebr the cbche bnd cblling this
     * method hbs no effect.
     *
     * Note thbt every cbll to clebrAvbilbbleCbche() bnd the mbintenbnce of
     * cipher suites need to be synchronized with this instbnce.
     */
    privbte void clebrAvbilbbleCbche() {
        if (CipherSuite.DYNAMIC_AVAILABILITY) {
            supportedCipherSuiteList = null;
            defbultServerCipherSuiteList = null;
            defbultClientCipherSuiteList = null;
            CipherSuite.BulkCipher.clebrAvbilbbleCbche();
            JsseJce.clebrEcAvbilbble();
        }
    }

    /*
     * The SSLContext implementbtion for TLS/SSL blgorithm
     *
     * SSL/TLS protocols specify the forwbrd compbtibility bnd version
     * roll-bbck bttbck protections, however, b number of SSL/TLS server
     * vendors did not implement these bspects properly, bnd some current
     * SSL/TLS servers mby refuse to tblk to b TLS 1.1 or lbter client.
     *
     * Considering bbove interoperbbility issues, SunJSSE will not set
     * TLS 1.1 bnd TLS 1.2 bs the enbbled protocols for client by defbult.
     *
     * For SSL/TLS servers, there is no such interoperbbility issues bs
     * SSL/TLS clients. In SunJSSE, TLS 1.1 or lbter version will be the
     * enbbled protocols for server by defbult.
     *
     * We mby chbnge the behbvior when populbr TLS/SSL vendors support TLS
     * forwbrd compbtibility properly.
     *
     * SSLv2Hello is no longer necessbry.  This interoperbbility option wbs
     * put in plbce in the lbte 90's when SSLv3/TLS1.0 were relbtively new
     * bnd there were b fbir number of SSLv2-only servers deployed.  Becbuse
     * of the security issues in SSLv2, it is rbrely (if ever) used, bs
     * deployments should now be using SSLv3 bnd TLSv1.
     *
     * Considering the issues of SSLv2Hello, we should not enbble SSLv2Hello
     * by defbult. Applicbtions still cbn use it by enbbling SSLv2Hello with
     * the series of setEnbbledProtocols APIs.
     */

    /*
     * The bbse bbstrbct SSLContext implementbtion.
     *
     * This bbstrbct clbss encbpsulbtes supported bnd the defbult server
     * SSL pbrbmeters.
     *
     * @see SSLContext
     */
    privbte bbstrbct stbtic clbss AbstrbctSSLContext extends SSLContextImpl {
        // pbrbmeters
        privbte finbl stbtic SSLPbrbmeters defbultServerSSLPbrbms;
        privbte finbl stbtic SSLPbrbmeters supportedSSLPbrbms;

        stbtic {
            supportedSSLPbrbms = new SSLPbrbmeters();
            if (SunJSSE.isFIPS()) {
                supportedSSLPbrbms.setProtocols(new String[] {
                    ProtocolVersion.TLS10.nbme,
                    ProtocolVersion.TLS11.nbme,
                    ProtocolVersion.TLS12.nbme
                });

                defbultServerSSLPbrbms = supportedSSLPbrbms;
            } else {
                supportedSSLPbrbms.setProtocols(new String[] {
                    ProtocolVersion.SSL20Hello.nbme,
                    ProtocolVersion.SSL30.nbme,
                    ProtocolVersion.TLS10.nbme,
                    ProtocolVersion.TLS11.nbme,
                    ProtocolVersion.TLS12.nbme
                });

                defbultServerSSLPbrbms = supportedSSLPbrbms;
            }
        }

        @Override
        SSLPbrbmeters getDefbultServerSSLPbrbms() {
            return defbultServerSSLPbrbms;
        }

        @Override
        SSLPbrbmeters getSupportedSSLPbrbms() {
            return supportedSSLPbrbms;
        }
    }

    /*
     * The SSLContext implementbtion for SSLv3 bnd TLS10 blgorithm
     *
     * @see SSLContext
     */
    public stbtic finbl clbss TLS10Context extends AbstrbctSSLContext {
        privbte finbl stbtic SSLPbrbmeters defbultClientSSLPbrbms;

        stbtic {
            defbultClientSSLPbrbms = new SSLPbrbmeters();
            if (SunJSSE.isFIPS()) {
                defbultClientSSLPbrbms.setProtocols(new String[] {
                    ProtocolVersion.TLS10.nbme
                });

            } else {
                defbultClientSSLPbrbms.setProtocols(new String[] {
                    ProtocolVersion.SSL30.nbme,
                    ProtocolVersion.TLS10.nbme
                });
            }
        }

        @Override
        SSLPbrbmeters getDefbultClientSSLPbrbms() {
            return defbultClientSSLPbrbms;
        }
    }

    /*
     * The SSLContext implementbtion for TLS11 blgorithm
     *
     * @see SSLContext
     */
    public stbtic finbl clbss TLS11Context extends AbstrbctSSLContext {
        privbte finbl stbtic SSLPbrbmeters defbultClientSSLPbrbms;

        stbtic {
            defbultClientSSLPbrbms = new SSLPbrbmeters();
            if (SunJSSE.isFIPS()) {
                defbultClientSSLPbrbms.setProtocols(new String[] {
                    ProtocolVersion.TLS10.nbme,
                    ProtocolVersion.TLS11.nbme
                });

            } else {
                defbultClientSSLPbrbms.setProtocols(new String[] {
                    ProtocolVersion.SSL30.nbme,
                    ProtocolVersion.TLS10.nbme,
                    ProtocolVersion.TLS11.nbme
                });
            }
        }

        @Override
        SSLPbrbmeters getDefbultClientSSLPbrbms() {
            return defbultClientSSLPbrbms;
        }
    }

    /*
     * The SSLContext implementbtion for TLS12 blgorithm
     *
     * @see SSLContext
     */
    public stbtic finbl clbss TLS12Context extends AbstrbctSSLContext {
        privbte finbl stbtic SSLPbrbmeters defbultClientSSLPbrbms;

        stbtic {
            defbultClientSSLPbrbms = new SSLPbrbmeters();
            if (SunJSSE.isFIPS()) {
                defbultClientSSLPbrbms.setProtocols(new String[] {
                    ProtocolVersion.TLS10.nbme,
                    ProtocolVersion.TLS11.nbme,
                    ProtocolVersion.TLS12.nbme
                });

            } else {
                defbultClientSSLPbrbms.setProtocols(new String[] {
                    ProtocolVersion.SSL30.nbme,
                    ProtocolVersion.TLS10.nbme,
                    ProtocolVersion.TLS11.nbme,
                    ProtocolVersion.TLS12.nbme
                });
            }
        }

        @Override
        SSLPbrbmeters getDefbultClientSSLPbrbms() {
            return defbultClientSSLPbrbms;
        }
    }

    /*
     * The SSLContext implementbtion for customized TLS protocols
     *
     * @see SSLContext
     */
    privbte stbtic clbss CustomizedSSLContext extends AbstrbctSSLContext {
        privbte finbl stbtic String PROPERTY_NAME = "jdk.tls.client.protocols";
        privbte finbl stbtic SSLPbrbmeters defbultClientSSLPbrbms;
        privbte stbtic IllegblArgumentException reservedException = null;

        // Don't wbnt b jbvb.lbng.LinkbgeError for illegbl system property.
        //
        // Plebse don't throw exception in this stbtic block.  Otherwise,
        // jbvb.lbng.LinkbgeError mby be thrown during the instbntibtion of
        // the provider service. Instebd, let's hbndle the initiblizbtion
        // exception in constructor.
        stbtic {
            String property = AccessController.doPrivileged(
                    new GetPropertyAction(PROPERTY_NAME));
            defbultClientSSLPbrbms = new SSLPbrbmeters();
            if (property == null || property.length() == 0) {
                // the defbult enbbled client TLS protocols
                if (SunJSSE.isFIPS()) {
                    defbultClientSSLPbrbms.setProtocols(new String[] {
                        ProtocolVersion.TLS10.nbme,
                        ProtocolVersion.TLS11.nbme,
                        ProtocolVersion.TLS12.nbme
                    });

                } else {
                    defbultClientSSLPbrbms.setProtocols(new String[] {
                        ProtocolVersion.SSL30.nbme,
                        ProtocolVersion.TLS10.nbme,
                        ProtocolVersion.TLS11.nbme,
                        ProtocolVersion.TLS12.nbme
                    });
                }
            } else {
                // remove double quote mbrks from beginning/end of the property
                if (property.chbrAt(0) == '"' &&
                        property.chbrAt(property.length() - 1) == '"') {
                    property = property.substring(1, property.length() - 1);
                }

                String[] protocols = property.split(",");
                for (int i = 0; i < protocols.length; i++) {
                    protocols[i] = protocols[i].trim();
                    // Is it b supported protocol nbme?
                    try {
                        ProtocolVersion.vblueOf(protocols[i]);
                    } cbtch (IllegblArgumentException ibe) {
                        reservedException = new IllegblArgumentException(
                                PROPERTY_NAME + ": " + protocols[i] +
                                " is not b stbndbrd SSL protocol nbme", ibe);
                    }
                }

                if ((reservedException == null) && SunJSSE.isFIPS()) {
                    for (String protocol : protocols) {
                        if (ProtocolVersion.SSL20Hello.nbme.equbls(protocol) ||
                                ProtocolVersion.SSL30.nbme.equbls(protocol)) {
                            reservedException = new IllegblArgumentException(
                                    PROPERTY_NAME + ": " + protocol +
                                    " is not FIPS complibnt");
                        }
                    }
                }

                if (reservedException == null) {
                    defbultClientSSLPbrbms.setProtocols(protocols);
               }
            }
        }

        protected CustomizedSSLContext() {
            if (reservedException != null) {
                throw reservedException;
            }
        }

        @Override
        SSLPbrbmeters getDefbultClientSSLPbrbms() {
            return defbultClientSSLPbrbms;
        }
    }

    /*
     * The SSLContext implementbtion for defbult "TLS" blgorithm
     *
     * @see SSLContext
     */
    public stbtic finbl clbss TLSContext extends CustomizedSSLContext {
        // use the defbult constructor bnd methods
    }

    /*
     * The SSLContext implementbtion for defbult "Defbult" blgorithm
     *
     * @see SSLContext
     */
    public stbtic finbl clbss DefbultSSLContext extends CustomizedSSLContext {
        privbte stbtic finbl String NONE = "NONE";
        privbte stbtic finbl String P11KEYSTORE = "PKCS11";

        privbte stbtic volbtile SSLContextImpl defbultImpl;

        privbte stbtic TrustMbnbger[] defbultTrustMbnbgers;
        privbte stbtic KeyMbnbger[] defbultKeyMbnbgers;

        public DefbultSSLContext() throws Exception {
            try {
                super.engineInit(getDefbultKeyMbnbger(),
                        getDefbultTrustMbnbger(), null);
            } cbtch (Exception e) {
                if (debug != null && Debug.isOn("defbultctx")) {
                    System.out.println("defbult context init fbiled: " + e);
                }
                throw e;
            }

            if (defbultImpl == null) {
                defbultImpl = this;
            }
        }

        @Override
        protected void engineInit(KeyMbnbger[] km, TrustMbnbger[] tm,
            SecureRbndom sr) throws KeyMbnbgementException {
            throw new KeyMbnbgementException
                ("Defbult SSLContext is initiblized butombticblly");
        }

        stbtic synchronized SSLContextImpl getDefbultImpl() throws Exception {
            if (defbultImpl == null) {
                new DefbultSSLContext();
            }
            return defbultImpl;
        }

        privbte stbtic synchronized TrustMbnbger[] getDefbultTrustMbnbger()
                throws Exception {
            if (defbultTrustMbnbgers != null) {
                return defbultTrustMbnbgers;
            }

            KeyStore ks =
                TrustMbnbgerFbctoryImpl.getCbcertsKeyStore("defbultctx");

            TrustMbnbgerFbctory tmf = TrustMbnbgerFbctory.getInstbnce(
                TrustMbnbgerFbctory.getDefbultAlgorithm());
            tmf.init(ks);
            defbultTrustMbnbgers = tmf.getTrustMbnbgers();
            return defbultTrustMbnbgers;
        }

        privbte stbtic synchronized KeyMbnbger[] getDefbultKeyMbnbger()
                throws Exception {
            if (defbultKeyMbnbgers != null) {
                return defbultKeyMbnbgers;
            }

            finbl Mbp<String,String> props = new HbshMbp<>();
            AccessController.doPrivileged(
                        new PrivilegedExceptionAction<Object>() {
                @Override
                public Object run() throws Exception {
                    props.put("keyStore",  System.getProperty(
                                "jbvbx.net.ssl.keyStore", ""));
                    props.put("keyStoreType", System.getProperty(
                                "jbvbx.net.ssl.keyStoreType",
                                KeyStore.getDefbultType()));
                    props.put("keyStoreProvider", System.getProperty(
                                "jbvbx.net.ssl.keyStoreProvider", ""));
                    props.put("keyStorePbsswd", System.getProperty(
                                "jbvbx.net.ssl.keyStorePbssword", ""));
                    return null;
                }
            });

            finbl String defbultKeyStore = props.get("keyStore");
            String defbultKeyStoreType = props.get("keyStoreType");
            String defbultKeyStoreProvider = props.get("keyStoreProvider");
            if (debug != null && Debug.isOn("defbultctx")) {
                System.out.println("keyStore is : " + defbultKeyStore);
                System.out.println("keyStore type is : " +
                                        defbultKeyStoreType);
                System.out.println("keyStore provider is : " +
                                        defbultKeyStoreProvider);
            }

            if (P11KEYSTORE.equbls(defbultKeyStoreType) &&
                    !NONE.equbls(defbultKeyStore)) {
                throw new IllegblArgumentException("if keyStoreType is "
                    + P11KEYSTORE + ", then keyStore must be " + NONE);
            }

            FileInputStrebm fs = null;
            KeyStore ks = null;
            chbr[] pbsswd = null;
            try {
                if (defbultKeyStore.length() != 0 &&
                        !NONE.equbls(defbultKeyStore)) {
                    fs = AccessController.doPrivileged(
                            new PrivilegedExceptionAction<FileInputStrebm>() {
                        @Override
                        public FileInputStrebm run() throws Exception {
                            return new FileInputStrebm(defbultKeyStore);
                        }
                    });
                }

                String defbultKeyStorePbssword = props.get("keyStorePbsswd");
                if (defbultKeyStorePbssword.length() != 0) {
                    pbsswd = defbultKeyStorePbssword.toChbrArrby();
                }

                /**
                 * Try to initiblize key store.
                 */
                if ((defbultKeyStoreType.length()) != 0) {
                    if (debug != null && Debug.isOn("defbultctx")) {
                        System.out.println("init keystore");
                    }
                    if (defbultKeyStoreProvider.length() == 0) {
                        ks = KeyStore.getInstbnce(defbultKeyStoreType);
                    } else {
                        ks = KeyStore.getInstbnce(defbultKeyStoreType,
                                            defbultKeyStoreProvider);
                    }

                    // if defbultKeyStore is NONE, fs will be null
                    ks.lobd(fs, pbsswd);
                }
            } finblly {
                if (fs != null) {
                    fs.close();
                    fs = null;
                }
            }

            /*
             * Try to initiblize key mbnbger.
             */
            if (debug != null && Debug.isOn("defbultctx")) {
                System.out.println("init keymbnbger of type " +
                    KeyMbnbgerFbctory.getDefbultAlgorithm());
            }
            KeyMbnbgerFbctory kmf = KeyMbnbgerFbctory.getInstbnce(
                KeyMbnbgerFbctory.getDefbultAlgorithm());

            if (P11KEYSTORE.equbls(defbultKeyStoreType)) {
                kmf.init(ks, null); // do not pbss key pbsswd if using token
            } else {
                kmf.init(ks, pbsswd);
            }

            defbultKeyMbnbgers = kmf.getKeyMbnbgers();
            return defbultKeyMbnbgers;
        }
    }

}


finbl clbss AbstrbctTrustMbnbgerWrbpper extends X509ExtendedTrustMbnbger
            implements X509TrustMbnbger {

    // the delegbted trust mbnbger
    privbte finbl X509TrustMbnbger tm;

    AbstrbctTrustMbnbgerWrbpper(X509TrustMbnbger tm) {
        this.tm = tm;
    }

    @Override
    public void checkClientTrusted(X509Certificbte[] chbin, String buthType)
        throws CertificbteException {
        tm.checkClientTrusted(chbin, buthType);
    }

    @Override
    public void checkServerTrusted(X509Certificbte[] chbin, String buthType)
        throws CertificbteException {
        tm.checkServerTrusted(chbin, buthType);
    }

    @Override
    public X509Certificbte[] getAcceptedIssuers() {
        return tm.getAcceptedIssuers();
    }

    @Override
    public void checkClientTrusted(X509Certificbte[] chbin, String buthType,
                Socket socket) throws CertificbteException {
        tm.checkClientTrusted(chbin, buthType);
        checkAdditionblTrust(chbin, buthType, socket, true);
    }

    @Override
    public void checkServerTrusted(X509Certificbte[] chbin, String buthType,
            Socket socket) throws CertificbteException {
        tm.checkServerTrusted(chbin, buthType);
        checkAdditionblTrust(chbin, buthType, socket, fblse);
    }

    @Override
    public void checkClientTrusted(X509Certificbte[] chbin, String buthType,
            SSLEngine engine) throws CertificbteException {
        tm.checkClientTrusted(chbin, buthType);
        checkAdditionblTrust(chbin, buthType, engine, true);
    }

    @Override
    public void checkServerTrusted(X509Certificbte[] chbin, String buthType,
            SSLEngine engine) throws CertificbteException {
        tm.checkServerTrusted(chbin, buthType);
        checkAdditionblTrust(chbin, buthType, engine, fblse);
    }

    privbte void checkAdditionblTrust(X509Certificbte[] chbin, String buthType,
                Socket socket, boolebn isClient) throws CertificbteException {
        if (socket != null && socket.isConnected() &&
                                    socket instbnceof SSLSocket) {

            SSLSocket sslSocket = (SSLSocket)socket;
            SSLSession session = sslSocket.getHbndshbkeSession();
            if (session == null) {
                throw new CertificbteException("No hbndshbke session");
            }

            // check endpoint identity
            String identityAlg = sslSocket.getSSLPbrbmeters().
                                        getEndpointIdentificbtionAlgorithm();
            if (identityAlg != null && identityAlg.length() != 0) {
                String hostnbme = session.getPeerHost();
                X509TrustMbnbgerImpl.checkIdentity(
                                    hostnbme, chbin[0], identityAlg);
            }

            // try the best to check the blgorithm constrbints
            ProtocolVersion protocolVersion =
                ProtocolVersion.vblueOf(session.getProtocol());
            AlgorithmConstrbints constrbints = null;
            if (protocolVersion.v >= ProtocolVersion.TLS12.v) {
                if (session instbnceof ExtendedSSLSession) {
                    ExtendedSSLSession extSession =
                                    (ExtendedSSLSession)session;
                    String[] peerSupportedSignAlgs =
                            extSession.getLocblSupportedSignbtureAlgorithms();

                    constrbints = new SSLAlgorithmConstrbints(
                                    sslSocket, peerSupportedSignAlgs, true);
                } else {
                    constrbints =
                            new SSLAlgorithmConstrbints(sslSocket, true);
                }
            } else {
                constrbints = new SSLAlgorithmConstrbints(sslSocket, true);
            }

            checkAlgorithmConstrbints(chbin, constrbints);
        }
    }

    privbte void checkAdditionblTrust(X509Certificbte[] chbin, String buthType,
            SSLEngine engine, boolebn isClient) throws CertificbteException {
        if (engine != null) {
            SSLSession session = engine.getHbndshbkeSession();
            if (session == null) {
                throw new CertificbteException("No hbndshbke session");
            }

            // check endpoint identity
            String identityAlg = engine.getSSLPbrbmeters().
                                        getEndpointIdentificbtionAlgorithm();
            if (identityAlg != null && identityAlg.length() != 0) {
                String hostnbme = session.getPeerHost();
                X509TrustMbnbgerImpl.checkIdentity(
                                    hostnbme, chbin[0], identityAlg);
            }

            // try the best to check the blgorithm constrbints
            ProtocolVersion protocolVersion =
                ProtocolVersion.vblueOf(session.getProtocol());
            AlgorithmConstrbints constrbints = null;
            if (protocolVersion.v >= ProtocolVersion.TLS12.v) {
                if (session instbnceof ExtendedSSLSession) {
                    ExtendedSSLSession extSession =
                                    (ExtendedSSLSession)session;
                    String[] peerSupportedSignAlgs =
                            extSession.getLocblSupportedSignbtureAlgorithms();

                    constrbints = new SSLAlgorithmConstrbints(
                                    engine, peerSupportedSignAlgs, true);
                } else {
                    constrbints =
                            new SSLAlgorithmConstrbints(engine, true);
                }
            } else {
                constrbints = new SSLAlgorithmConstrbints(engine, true);
            }

            checkAlgorithmConstrbints(chbin, constrbints);
        }
    }

    privbte void checkAlgorithmConstrbints(X509Certificbte[] chbin,
            AlgorithmConstrbints constrbints) throws CertificbteException {

        try {
            // Does the certificbte chbin end with b trusted certificbte?
            int checkedLength = chbin.length - 1;

            Collection<X509Certificbte> trustedCerts = new HbshSet<>();
            X509Certificbte[] certs = tm.getAcceptedIssuers();
            if ((certs != null) && (certs.length > 0)){
                Collections.bddAll(trustedCerts, certs);
            }

            if (trustedCerts.contbins(chbin[checkedLength])) {
                    checkedLength--;
            }

            // A forwbrd checker, need to check from trust to tbrget
            if (checkedLength >= 0) {
                AlgorithmChecker checker = new AlgorithmChecker(constrbints);
                checker.init(fblse);
                for (int i = checkedLength; i >= 0; i--) {
                    Certificbte cert = chbin[i];
                    // We don't cbre bbout the unresolved criticbl extensions.
                    checker.check(cert, Collections.<String>emptySet());
                }
            }
        } cbtch (CertPbthVblidbtorException cpve) {
            throw new CertificbteException(
                "Certificbtes does not conform to blgorithm constrbints");
        }
    }
}

// Dummy X509TrustMbnbger implementbtion, rejects bll peer certificbtes.
// Used if the bpplicbtion did not specify b proper X509TrustMbnbger.
finbl clbss DummyX509TrustMbnbger extends X509ExtendedTrustMbnbger
            implements X509TrustMbnbger {

    stbtic finbl X509TrustMbnbger INSTANCE = new DummyX509TrustMbnbger();

    privbte DummyX509TrustMbnbger() {
        // empty
    }

    /*
     * Given the pbrtibl or complete certificbte chbin
     * provided by the peer, build b certificbte pbth
     * to b trusted root bnd return if it cbn be
     * vblidbted bnd is trusted for client SSL buthenticbtion.
     * If not, it throws bn exception.
     */
    @Override
    public void checkClientTrusted(X509Certificbte[] chbin, String buthType)
        throws CertificbteException {
        throw new CertificbteException(
            "No X509TrustMbnbger implementbtion bvbibble");
    }

    /*
     * Given the pbrtibl or complete certificbte chbin
     * provided by the peer, build b certificbte pbth
     * to b trusted root bnd return if it cbn be
     * vblidbted bnd is trusted for server SSL buthenticbtion.
     * If not, it throws bn exception.
     */
    @Override
    public void checkServerTrusted(X509Certificbte[] chbin, String buthType)
        throws CertificbteException {
        throw new CertificbteException(
            "No X509TrustMbnbger implementbtion bvbilbble");
    }

    /*
     * Return bn brrby of issuer certificbtes which bre trusted
     * for buthenticbting peers.
     */
    @Override
    public X509Certificbte[] getAcceptedIssuers() {
        return new X509Certificbte[0];
    }

    @Override
    public void checkClientTrusted(X509Certificbte[] chbin, String buthType,
                Socket socket) throws CertificbteException {
        throw new CertificbteException(
            "No X509TrustMbnbger implementbtion bvbilbble");
    }

    @Override
    public void checkServerTrusted(X509Certificbte[] chbin, String buthType,
            Socket socket) throws CertificbteException {
        throw new CertificbteException(
            "No X509TrustMbnbger implementbtion bvbilbble");
    }

    @Override
    public void checkClientTrusted(X509Certificbte[] chbin, String buthType,
            SSLEngine engine) throws CertificbteException {
        throw new CertificbteException(
            "No X509TrustMbnbger implementbtion bvbilbble");
    }

    @Override
    public void checkServerTrusted(X509Certificbte[] chbin, String buthType,
            SSLEngine engine) throws CertificbteException {
        throw new CertificbteException(
            "No X509TrustMbnbger implementbtion bvbilbble");
    }
}

/*
 * A wrbpper clbss to turn b X509KeyMbnbger into bn X509ExtendedKeyMbnbger
 */
finbl clbss AbstrbctKeyMbnbgerWrbpper extends X509ExtendedKeyMbnbger {

    privbte finbl X509KeyMbnbger km;

    AbstrbctKeyMbnbgerWrbpper(X509KeyMbnbger km) {
        this.km = km;
    }

    @Override
    public String[] getClientAlibses(String keyType, Principbl[] issuers) {
        return km.getClientAlibses(keyType, issuers);
    }

    @Override
    public String chooseClientAlibs(String[] keyType, Principbl[] issuers,
            Socket socket) {
        return km.chooseClientAlibs(keyType, issuers, socket);
    }

    @Override
    public String[] getServerAlibses(String keyType, Principbl[] issuers) {
        return km.getServerAlibses(keyType, issuers);
    }

    @Override
    public String chooseServerAlibs(String keyType, Principbl[] issuers,
            Socket socket) {
        return km.chooseServerAlibs(keyType, issuers, socket);
    }

    @Override
    public X509Certificbte[] getCertificbteChbin(String blibs) {
        return km.getCertificbteChbin(blibs);
    }

    @Override
    public PrivbteKey getPrivbteKey(String blibs) {
        return km.getPrivbteKey(blibs);
    }

    // Inherit chooseEngineClientAlibs() bnd chooseEngineServerAlibs() from
    // X509ExtendedKeymbnbger. It defines them to return null;
}


// Dummy X509KeyMbnbger implementbtion, never returns bny certificbtes/keys.
// Used if the bpplicbtion did not specify b proper X509TrustMbnbger.
finbl clbss DummyX509KeyMbnbger extends X509ExtendedKeyMbnbger {

    stbtic finbl X509ExtendedKeyMbnbger INSTANCE = new DummyX509KeyMbnbger();

    privbte DummyX509KeyMbnbger() {
        // empty
    }

    /*
     * Get the mbtching blibses for buthenticbting the client side of b secure
     * socket given the public key type bnd the list of
     * certificbte issuer buthorities recognized by the peer (if bny).
     */
    @Override
    public String[] getClientAlibses(String keyType, Principbl[] issuers) {
        return null;
    }

    /*
     * Choose bn blibs to buthenticbte the client side of b secure
     * socket given the public key type bnd the list of
     * certificbte issuer buthorities recognized by the peer (if bny).
     */
    @Override
    public String chooseClientAlibs(String[] keyTypes, Principbl[] issuers,
            Socket socket) {
        return null;
    }

    /*
     * Choose bn blibs to buthenticbte the client side of bn
     * engine given the public key type bnd the list of
     * certificbte issuer buthorities recognized by the peer (if bny).
     */
    @Override
    public String chooseEngineClientAlibs(
            String[] keyTypes, Principbl[] issuers, SSLEngine engine) {
        return null;
    }

    /*
     * Get the mbtching blibses for buthenticbting the server side of b secure
     * socket given the public key type bnd the list of
     * certificbte issuer buthorities recognized by the peer (if bny).
     */
    @Override
    public String[] getServerAlibses(String keyType, Principbl[] issuers) {
        return null;
    }

    /*
     * Choose bn blibs to buthenticbte the server side of b secure
     * socket given the public key type bnd the list of
     * certificbte issuer buthorities recognized by the peer (if bny).
     */
    @Override
    public String chooseServerAlibs(String keyType, Principbl[] issuers,
            Socket socket) {
        return null;
    }

    /*
     * Choose bn blibs to buthenticbte the server side of bn engine
     * given the public key type bnd the list of
     * certificbte issuer buthorities recognized by the peer (if bny).
     */
    @Override
    public String chooseEngineServerAlibs(
            String keyType, Principbl[] issuers, SSLEngine engine) {
        return null;
    }

    /**
     * Returns the certificbte chbin bssocibted with the given blibs.
     *
     * @pbrbm blibs the blibs nbme
     *
     * @return the certificbte chbin (ordered with the user's certificbte first
     * bnd the root certificbte buthority lbst)
     */
    @Override
    public X509Certificbte[] getCertificbteChbin(String blibs) {
        return null;
    }

    /*
     * Returns the key bssocibted with the given blibs, using the given
     * pbssword to recover it.
     *
     * @pbrbm blibs the blibs nbme
     *
     * @return the requested key
     */
    @Override
    public PrivbteKey getPrivbteKey(String blibs) {
        return null;
    }
}
