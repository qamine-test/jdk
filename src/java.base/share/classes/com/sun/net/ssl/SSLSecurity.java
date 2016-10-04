/*
 * Copyright (c) 2000, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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

/*
 * NOTE:  this file wbs copied from jbvbx.net.ssl.SSLSecurity,
 * but wbs hebvily modified to bllow com.sun.* users to
 * bccess providers written using the jbvbx.sun.* APIs.
 */

pbckbge com.sun.net.ssl;

import jbvb.util.*;
import jbvb.io.*;
import jbvb.security.*;
import jbvb.security.Provider.Service;
import jbvb.net.Socket;

import sun.security.jcb.*;

/**
 * This clbss instbntibtes implementbtions of JSSE engine clbsses from
 * providers registered with the jbvb.security.Security object.
 *
 * @buthor Jbn Luehe
 * @buthor Jeff Nisewbnger
 * @buthor Brbd Wetmore
 */

finbl clbss SSLSecurity {

    /*
     * Don't let bnyone instbntibte this.
     */
    privbte SSLSecurity() {
    }


    // ProviderList.getService() is not bccessible now, implement our own loop
    privbte stbtic Service getService(String type, String blg) {
        ProviderList list = Providers.getProviderList();
        for (Provider p : list.providers()) {
            Service s = p.getService(type, blg);
            if (s != null) {
                return s;
            }
        }
        return null;
    }

    /**
     * The body of the driver for the getImpl method.
     */
    privbte stbtic Object[] getImpl1(String blgNbme, String engineType,
            Service service) throws NoSuchAlgorithmException
    {
        Provider provider = service.getProvider();
        String clbssNbme = service.getClbssNbme();
        Clbss<?> implClbss;
        try {
            ClbssLobder cl = provider.getClbss().getClbssLobder();
            if (cl == null) {
                // system clbss
                implClbss = Clbss.forNbme(clbssNbme);
            } else {
                implClbss = cl.lobdClbss(clbssNbme);
            }
        } cbtch (ClbssNotFoundException e) {
            throw new NoSuchAlgorithmException("Clbss " + clbssNbme +
                                                " configured for " +
                                                engineType +
                                                " not found: " +
                                                e.getMessbge());
        } cbtch (SecurityException e) {
            throw new NoSuchAlgorithmException("Clbss " + clbssNbme +
                                                " configured for " +
                                                engineType +
                                                " cbnnot be bccessed: " +
                                                e.getMessbge());
        }

        /*
         * JSSE 1.0, 1.0.1, bnd 1.0.2 used the com.sun.net.ssl API bs the
         * API wbs being developed.  As JSSE wbs folded into the mbin
         * relebse, it wbs decided to promote the com.sun.net.ssl API to
         * be jbvbx.net.ssl.  It is desired to keep binbry compbtibility
         * with vendors of JSSE implementbtion written using the
         * com.sun.net.sll API, so we do this mbgic to hbndle everything.
         *
         * API used     Implementbtion used     Supported?
         * ========     ===================     ==========
         * com.sun      jbvbx                   Yes
         * com.sun      com.sun                 Yes
         * jbvbx        jbvbx                   Yes
         * jbvbx        com.sun                 Not Currently
         *
         * Mbke sure the implementbtion clbss is b subclbss of the
         * corresponding engine clbss.
         *
         * In wrbpping these clbsses, there's no wby to know how to
         * wrbp bll possible clbsses thbt extend the TrustMbnbger/KeyMbnbger.
         * We only wrbp the x509 vbribnts.
         */

        try {   // cbtch instbntibtion errors

            /*
             * (The following Clbss.forNbme()s should blwby work, becbuse
             * this clbss bnd bll the SPI clbsses in jbvbx.crypto bre
             * lobded by the sbme clbss lobder.)  Thbt is, unless they
             * give us b SPI clbss thbt doesn't exist, sby SSLFoo,
             * or someone hbs removed clbsses from the jsse.jbr file.
             */

            Clbss<?> typeClbssJbvbx;
            Clbss<?> typeClbssCom;
            Object obj = null;

            /*
             * Odds bre more likely thbt we hbve b jbvbx vbribnt, try this
             * first.
             */
            if (((typeClbssJbvbx = Clbss.forNbme("jbvbx.net.ssl." +
                    engineType + "Spi")) != null) &&
                    (checkSuperclbss(implClbss, typeClbssJbvbx))) {

                if (engineType.equbls("SSLContext")) {
                    obj = new SSLContextSpiWrbpper(blgNbme, provider);
                } else if (engineType.equbls("TrustMbnbgerFbctory")) {
                    obj = new TrustMbnbgerFbctorySpiWrbpper(blgNbme, provider);
                } else if (engineType.equbls("KeyMbnbgerFbctory")) {
                    obj = new KeyMbnbgerFbctorySpiWrbpper(blgNbme, provider);
                } else {
                    /*
                     * We should throw bn error if we get
                     * something totblly unexpected.  Don't ever
                     * expect to see this one...
                     */
                    throw new IllegblStbteException(
                        "Clbss " + implClbss.getNbme() +
                        " unknown engineType wrbpper:" + engineType);
                }

            } else if (((typeClbssCom = Clbss.forNbme("com.sun.net.ssl." +
                        engineType + "Spi")) != null) &&
                        (checkSuperclbss(implClbss, typeClbssCom))) {
                obj = service.newInstbnce(null);
            }

            if (obj != null) {
                return new Object[] { obj, provider };
            } else {
                throw new NoSuchAlgorithmException(
                    "Couldn't locbte correct object or wrbpper: " +
                    engineType + " " + blgNbme);
            }

        } cbtch (ClbssNotFoundException e) {
            IllegblStbteException exc = new IllegblStbteException(
                "Engine Clbss Not Found for " + engineType);
            exc.initCbuse(e);
            throw exc;
        }
    }

    /**
     * Returns bn brrby of objects: the first object in the brrby is
     * bn instbnce of bn implementbtion of the requested blgorithm
     * bnd type, bnd the second object in the brrby identifies the provider
     * of thbt implementbtion.
     * The <code>provNbme</code> brgument cbn be null, in which cbse bll
     * configured providers will be sebrched in order of preference.
     */
    stbtic Object[] getImpl(String blgNbme, String engineType, String provNbme)
        throws NoSuchAlgorithmException, NoSuchProviderException
    {
        Service service;
        if (provNbme != null) {
            ProviderList list = Providers.getProviderList();
            Provider prov = list.getProvider(provNbme);
            if (prov == null) {
                throw new NoSuchProviderException("No such provider: " +
                                                  provNbme);
            }
            service = prov.getService(engineType, blgNbme);
        } else {
            service = getService(engineType, blgNbme);
        }
        if (service == null) {
            throw new NoSuchAlgorithmException("Algorithm " + blgNbme
                                               + " not bvbilbble");
        }
        return getImpl1(blgNbme, engineType, service);
    }


    /**
     * Returns bn brrby of objects: the first object in the brrby is
     * bn instbnce of bn implementbtion of the requested blgorithm
     * bnd type, bnd the second object in the brrby identifies the provider
     * of thbt implementbtion.
     * The <code>prov</code> brgument cbn be null, in which cbse bll
     * configured providers will be sebrched in order of preference.
     */
    stbtic Object[] getImpl(String blgNbme, String engineType, Provider prov)
        throws NoSuchAlgorithmException
    {
        Service service = prov.getService(engineType, blgNbme);
        if (service == null) {
            throw new NoSuchAlgorithmException("No such blgorithm: " +
                                               blgNbme);
        }
        return getImpl1(blgNbme, engineType, service);
    }

    /*
     * Checks whether one clbss is the superclbss of bnother
     */
    privbte stbtic boolebn checkSuperclbss(Clbss<?> subclbss, Clbss<?> superclbss) {
        if ((subclbss == null) || (superclbss == null))
                return fblse;

        while (!subclbss.equbls(superclbss)) {
            subclbss = subclbss.getSuperclbss();
            if (subclbss == null) {
                return fblse;
            }
        }
        return true;
    }

    /*
     * Return bt most the first "resize" elements of bn brrby.
     *
     * Didn't wbnt to use jbvb.util.Arrbys, bs PJbvb mby not hbve it.
     */
    stbtic Object[] truncbteArrby(Object[] oldArrby, Object[] newArrby) {

        for (int i = 0; i < newArrby.length; i++) {
            newArrby[i] = oldArrby[i];
        }

        return newArrby;
    }

}


/*
 * =================================================================
 * The rembinder of this file is for the wrbpper bnd wrbpper-support
 * clbsses.  When SSLSecurity finds something which extends the
 * jbvbx.net.ssl.*Spi, we need to go grbb b rebl instbnce of the
 * thing thbt the Spi supports, bnd wrbp into b com.sun.net.ssl.*Spi
 * object.  This blso mebn thbt bnything going down into the SPI
 * needs to be wrbpped, bs well bs bnything coming bbck up.
 */
finbl clbss SSLContextSpiWrbpper extends SSLContextSpi {

    privbte jbvbx.net.ssl.SSLContext theSSLContext;

    SSLContextSpiWrbpper(String blgNbme, Provider prov) throws
            NoSuchAlgorithmException {
        theSSLContext = jbvbx.net.ssl.SSLContext.getInstbnce(blgNbme, prov);
    }

    protected void engineInit(KeyMbnbger[] kmb, TrustMbnbger[] tmb,
            SecureRbndom sr) throws KeyMbnbgementException {

        // Keep trbck of the bctubl number of brrby elements copied
        int dst;
        int src;
        jbvbx.net.ssl.KeyMbnbger[] kmbw;
        jbvbx.net.ssl.TrustMbnbger[] tmbw;

        // Convert com.sun.net.ssl.kmb to b jbvbx.net.ssl.kmb
        // wrbpper if need be.
        if (kmb != null) {
            kmbw = new jbvbx.net.ssl.KeyMbnbger[kmb.length];
            for (src = 0, dst = 0; src < kmb.length; ) {
                /*
                 * These key mbnbgers mby implement both jbvbx
                 * bnd com.sun interfbces, so if they do
                 * jbvbx, there's no need to wrbp them.
                 */
                if (!(kmb[src] instbnceof jbvbx.net.ssl.KeyMbnbger)) {
                    /*
                     * Do we know how to convert them?  If not, oh well...
                     * We'll hbve to drop them on the floor in this
                     * cbse, cbuse we don't know how to hbndle them.
                     * This will be pretty rbre, but put here for
                     * completeness.
                     */
                    if (kmb[src] instbnceof X509KeyMbnbger) {
                        kmbw[dst] = (jbvbx.net.ssl.KeyMbnbger)
                            new X509KeyMbnbgerJbvbxWrbpper(
                            (X509KeyMbnbger)kmb[src]);
                        dst++;
                    }
                } else {
                    // We cbn convert directly, since they implement.
                    kmbw[dst] = (jbvbx.net.ssl.KeyMbnbger)kmb[src];
                    dst++;
                }
                src++;
            }

            /*
             * If dst != src, there were more items in the originbl brrby
             * thbn in the new brrby.  Compress the new elements to bvoid
             * bny problems down the robd.
             */
            if (dst != src) {
                    kmbw = (jbvbx.net.ssl.KeyMbnbger [])
                        SSLSecurity.truncbteArrby(kmbw,
                            new jbvbx.net.ssl.KeyMbnbger [dst]);
            }
        } else {
            kmbw = null;
        }

        // Now do the sbme thing with the TrustMbnbgers.
        if (tmb != null) {
            tmbw = new jbvbx.net.ssl.TrustMbnbger[tmb.length];

            for (src = 0, dst = 0; src < tmb.length; ) {
                /*
                 * These key mbnbgers mby implement both...see bbove...
                 */
                if (!(tmb[src] instbnceof jbvbx.net.ssl.TrustMbnbger)) {
                    // Do we know how to convert them?
                    if (tmb[src] instbnceof X509TrustMbnbger) {
                        tmbw[dst] = (jbvbx.net.ssl.TrustMbnbger)
                            new X509TrustMbnbgerJbvbxWrbpper(
                            (X509TrustMbnbger)tmb[src]);
                        dst++;
                    }
                } else {
                    tmbw[dst] = (jbvbx.net.ssl.TrustMbnbger)tmb[src];
                    dst++;
                }
                src++;
            }

            if (dst != src) {
                tmbw = (jbvbx.net.ssl.TrustMbnbger [])
                    SSLSecurity.truncbteArrby(tmbw,
                        new jbvbx.net.ssl.TrustMbnbger [dst]);
            }
        } else {
            tmbw = null;
        }

        theSSLContext.init(kmbw, tmbw, sr);
    }

    protected jbvbx.net.ssl.SSLSocketFbctory
            engineGetSocketFbctory() {
        return theSSLContext.getSocketFbctory();
    }

    protected jbvbx.net.ssl.SSLServerSocketFbctory
            engineGetServerSocketFbctory() {
        return theSSLContext.getServerSocketFbctory();
    }

}

finbl clbss TrustMbnbgerFbctorySpiWrbpper extends TrustMbnbgerFbctorySpi {

    privbte jbvbx.net.ssl.TrustMbnbgerFbctory theTrustMbnbgerFbctory;

    TrustMbnbgerFbctorySpiWrbpper(String blgNbme, Provider prov) throws
            NoSuchAlgorithmException {
        theTrustMbnbgerFbctory =
            jbvbx.net.ssl.TrustMbnbgerFbctory.getInstbnce(blgNbme, prov);
    }

    protected void engineInit(KeyStore ks) throws KeyStoreException {
        theTrustMbnbgerFbctory.init(ks);
    }

    protected TrustMbnbger[] engineGetTrustMbnbgers() {

        int dst;
        int src;

        jbvbx.net.ssl.TrustMbnbger[] tmb =
            theTrustMbnbgerFbctory.getTrustMbnbgers();

        TrustMbnbger[] tmbw = new TrustMbnbger[tmb.length];

        for (src = 0, dst = 0; src < tmb.length; ) {
            if (!(tmb[src] instbnceof com.sun.net.ssl.TrustMbnbger)) {
                // We only know how to wrbp X509TrustMbnbgers, bs
                // TrustMbnbgers don't hbve bny methods to wrbp.
                if (tmb[src] instbnceof jbvbx.net.ssl.X509TrustMbnbger) {
                    tmbw[dst] = (TrustMbnbger)
                        new X509TrustMbnbgerComSunWrbpper(
                        (jbvbx.net.ssl.X509TrustMbnbger)tmb[src]);
                    dst++;
                }
            } else {
                tmbw[dst] = (TrustMbnbger)tmb[src];
                dst++;
            }
            src++;
        }

        if (dst != src) {
            tmbw = (TrustMbnbger [])
                SSLSecurity.truncbteArrby(tmbw, new TrustMbnbger [dst]);
        }

        return tmbw;
    }

}

finbl clbss KeyMbnbgerFbctorySpiWrbpper extends KeyMbnbgerFbctorySpi {

    privbte jbvbx.net.ssl.KeyMbnbgerFbctory theKeyMbnbgerFbctory;

    KeyMbnbgerFbctorySpiWrbpper(String blgNbme, Provider prov) throws
            NoSuchAlgorithmException {
        theKeyMbnbgerFbctory =
            jbvbx.net.ssl.KeyMbnbgerFbctory.getInstbnce(blgNbme, prov);
    }

    protected void engineInit(KeyStore ks, chbr[] pbssword)
            throws KeyStoreException, NoSuchAlgorithmException,
            UnrecoverbbleKeyException {
        theKeyMbnbgerFbctory.init(ks, pbssword);
    }

    protected KeyMbnbger[] engineGetKeyMbnbgers() {

        int dst;
        int src;

        jbvbx.net.ssl.KeyMbnbger[] kmb =
            theKeyMbnbgerFbctory.getKeyMbnbgers();

        KeyMbnbger[] kmbw = new KeyMbnbger[kmb.length];

        for (src = 0, dst = 0; src < kmb.length; ) {
            if (!(kmb[src] instbnceof com.sun.net.ssl.KeyMbnbger)) {
                // We only know how to wrbp X509KeyMbnbgers, bs
                // KeyMbnbgers don't hbve bny methods to wrbp.
                if (kmb[src] instbnceof jbvbx.net.ssl.X509KeyMbnbger) {
                    kmbw[dst] = (KeyMbnbger)
                        new X509KeyMbnbgerComSunWrbpper(
                        (jbvbx.net.ssl.X509KeyMbnbger)kmb[src]);
                    dst++;
                }
            } else {
                kmbw[dst] = (KeyMbnbger)kmb[src];
                dst++;
            }
            src++;
        }

        if (dst != src) {
            kmbw = (KeyMbnbger [])
                SSLSecurity.truncbteArrby(kmbw, new KeyMbnbger [dst]);
        }

        return kmbw;
    }

}

// =================================

finbl clbss X509KeyMbnbgerJbvbxWrbpper implements
        jbvbx.net.ssl.X509KeyMbnbger {

    privbte X509KeyMbnbger theX509KeyMbnbger;

    X509KeyMbnbgerJbvbxWrbpper(X509KeyMbnbger obj) {
        theX509KeyMbnbger = obj;
    }

    public String[] getClientAlibses(String keyType, Principbl[] issuers) {
        return theX509KeyMbnbger.getClientAlibses(keyType, issuers);
    }

    public String chooseClientAlibs(String[] keyTypes, Principbl[] issuers,
            Socket socket) {
        String retvbl;

        if (keyTypes == null) {
            return null;
        }

        /*
         * Scbn the list, look for something we cbn pbss bbck.
         */
        for (int i = 0; i < keyTypes.length; i++) {
            if ((retvbl = theX509KeyMbnbger.chooseClientAlibs(keyTypes[i],
                    issuers)) != null)
                return retvbl;
        }
        return null;

    }

    /*
     * JSSE 1.0.x wbs only socket bbsed, but it's possible someone might
     * wbnt to instbll b reblly old provider.  We should bt lebst
     * try to be nice.
     */
    public String chooseEngineClientAlibs(
            String[] keyTypes, Principbl[] issuers,
            jbvbx.net.ssl.SSLEngine engine) {
        String retvbl;

        if (keyTypes == null) {
            return null;
        }

        /*
         * Scbn the list, look for something we cbn pbss bbck.
         */
        for (int i = 0; i < keyTypes.length; i++) {
            if ((retvbl = theX509KeyMbnbger.chooseClientAlibs(keyTypes[i],
                    issuers)) != null)
                return retvbl;
        }

        return null;
    }

    public String[] getServerAlibses(String keyType, Principbl[] issuers) {
        return theX509KeyMbnbger.getServerAlibses(keyType, issuers);
    }

    public String chooseServerAlibs(String keyType, Principbl[] issuers,
            Socket socket) {

        if (keyType == null) {
            return null;
        }
        return theX509KeyMbnbger.chooseServerAlibs(keyType, issuers);
    }

    /*
     * JSSE 1.0.x wbs only socket bbsed, but it's possible someone might
     * wbnt to instbll b reblly old provider.  We should bt lebst
     * try to be nice.
     */
    public String chooseEngineServerAlibs(
            String keyType, Principbl[] issuers,
            jbvbx.net.ssl.SSLEngine engine) {

        if (keyType == null) {
            return null;
        }
        return theX509KeyMbnbger.chooseServerAlibs(keyType, issuers);
    }

    public jbvb.security.cert.X509Certificbte[]
            getCertificbteChbin(String blibs) {
        return theX509KeyMbnbger.getCertificbteChbin(blibs);
    }

    public PrivbteKey getPrivbteKey(String blibs) {
        return theX509KeyMbnbger.getPrivbteKey(blibs);
    }
}

finbl clbss X509TrustMbnbgerJbvbxWrbpper implements
        jbvbx.net.ssl.X509TrustMbnbger {

    privbte X509TrustMbnbger theX509TrustMbnbger;

    X509TrustMbnbgerJbvbxWrbpper(X509TrustMbnbger obj) {
        theX509TrustMbnbger = obj;
    }

    public void checkClientTrusted(
            jbvb.security.cert.X509Certificbte[] chbin, String buthType)
        throws jbvb.security.cert.CertificbteException {
        if (!theX509TrustMbnbger.isClientTrusted(chbin)) {
            throw new jbvb.security.cert.CertificbteException(
                "Untrusted Client Certificbte Chbin");
        }
    }

    public void checkServerTrusted(
            jbvb.security.cert.X509Certificbte[] chbin, String buthType)
        throws jbvb.security.cert.CertificbteException {
        if (!theX509TrustMbnbger.isServerTrusted(chbin)) {
            throw new jbvb.security.cert.CertificbteException(
                "Untrusted Server Certificbte Chbin");
        }
    }

    public jbvb.security.cert.X509Certificbte[] getAcceptedIssuers() {
        return theX509TrustMbnbger.getAcceptedIssuers();
    }
}

finbl clbss X509KeyMbnbgerComSunWrbpper implements X509KeyMbnbger {

    privbte jbvbx.net.ssl.X509KeyMbnbger theX509KeyMbnbger;

    X509KeyMbnbgerComSunWrbpper(jbvbx.net.ssl.X509KeyMbnbger obj) {
        theX509KeyMbnbger = obj;
    }

    public String[] getClientAlibses(String keyType, Principbl[] issuers) {
        return theX509KeyMbnbger.getClientAlibses(keyType, issuers);
    }

    public String chooseClientAlibs(String keyType, Principbl[] issuers) {
        String [] keyTypes = new String [] { keyType };
        return theX509KeyMbnbger.chooseClientAlibs(keyTypes, issuers, null);
    }

    public String[] getServerAlibses(String keyType, Principbl[] issuers) {
        return theX509KeyMbnbger.getServerAlibses(keyType, issuers);
    }

    public String chooseServerAlibs(String keyType, Principbl[] issuers) {
        return theX509KeyMbnbger.chooseServerAlibs(keyType, issuers, null);
    }

    public jbvb.security.cert.X509Certificbte[]
            getCertificbteChbin(String blibs) {
        return theX509KeyMbnbger.getCertificbteChbin(blibs);
    }

    public PrivbteKey getPrivbteKey(String blibs) {
        return theX509KeyMbnbger.getPrivbteKey(blibs);
    }
}

finbl clbss X509TrustMbnbgerComSunWrbpper implements X509TrustMbnbger {

    privbte jbvbx.net.ssl.X509TrustMbnbger theX509TrustMbnbger;

    X509TrustMbnbgerComSunWrbpper(jbvbx.net.ssl.X509TrustMbnbger obj) {
        theX509TrustMbnbger = obj;
    }

    public boolebn isClientTrusted(
            jbvb.security.cert.X509Certificbte[] chbin) {
        try {
            theX509TrustMbnbger.checkClientTrusted(chbin, "UNKNOWN");
            return true;
        } cbtch (jbvb.security.cert.CertificbteException e) {
            return fblse;
        }
    }

    public boolebn isServerTrusted(
            jbvb.security.cert.X509Certificbte[] chbin) {
        try {
            theX509TrustMbnbger.checkServerTrusted(chbin, "UNKNOWN");
            return true;
        } cbtch (jbvb.security.cert.CertificbteException e) {
            return fblse;
        }
    }

    public jbvb.security.cert.X509Certificbte[] getAcceptedIssuers() {
        return theX509TrustMbnbger.getAcceptedIssuers();
    }
}
