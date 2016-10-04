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


pbckbge sun.security.ssl;

import jbvb.io.*;
import jbvb.util.*;
import jbvb.security.*;
import jbvb.security.cert.*;
import jbvb.security.interfbces.*;
import jbvb.security.spec.ECPbrbmeterSpec;

import jbvbx.crypto.SecretKey;
import jbvbx.crypto.spec.SecretKeySpec;

import jbvbx.net.ssl.*;

import jbvbx.security.buth.Subject;

import sun.security.util.KeyUtil;
import sun.security.bction.GetPropertyAction;
import sun.security.ssl.HbndshbkeMessbge.*;
import sun.security.ssl.CipherSuite.*;
import sun.security.ssl.SignbtureAndHbshAlgorithm.*;
import stbtic sun.security.ssl.CipherSuite.KeyExchbnge.*;

/**
 * ServerHbndshbker does the protocol hbndshbking from the point
 * of view of b server.  It is driven bsychronously by hbndshbke messbges
 * bs delivered by the pbrent Hbndshbker clbss, bnd blso uses
 * common functionblity (e.g. key generbtion) thbt is provided there.
 *
 * @buthor Dbvid Brownell
 */
finbl clbss ServerHbndshbker extends Hbndshbker {

    // is the server going to require the client to buthenticbte?
    privbte byte                doClientAuth;

    // our buthenticbtion info
    privbte X509Certificbte[]   certs;
    privbte PrivbteKey          privbteKey;

    privbte Object              serviceCreds;

    // flbg to check for clientCertificbteVerify messbge
    privbte boolebn             needClientVerify = fblse;

    /*
     * For exportbble ciphersuites using non-exportbble key sizes, we use
     * ephemerbl RSA keys. We could blso do bnonymous RSA in the sbme wby
     * but there bre no such ciphersuites currently defined.
     */
    privbte PrivbteKey          tempPrivbteKey;
    privbte PublicKey           tempPublicKey;

    /*
     * For bnonymous bnd ephemerbl Diffie-Hellmbn key exchbnge, we use
     * ephemerbl Diffie-Hellmbn keys.
     */
    privbte DHCrypt dh;

    // Helper for ECDH bbsed key exchbnges
    privbte ECDHCrypt ecdh;

    // version request by the client in its ClientHello
    // we remember it for the RSA prembster secret version check
    privbte ProtocolVersion clientRequestedVersion;

    privbte SupportedEllipticCurvesExtension supportedCurves;

    // the preferbble signbture blgorithm used by ServerKeyExchbnge messbge
    SignbtureAndHbshAlgorithm preferbbleSignbtureAlgorithm;

    // Flbg to use smbrt ephemerbl DH key which size mbtches the corresponding
    // buthenticbtion key
    privbte stbtic finbl boolebn useSmbrtEphemerblDHKeys;

    // Flbg to use legbcy ephemerbl DH key which size is 512 bits for
    // exportbble cipher suites, bnd 768 bits for others
    privbte stbtic finbl boolebn useLegbcyEphemerblDHKeys;

    // The customized ephemerbl DH key size for non-exportbble cipher suites.
    privbte stbtic finbl int customizedDHKeySize;

    stbtic {
        String property = AccessController.doPrivileged(
                    new GetPropertyAction("jdk.tls.ephemerblDHKeySize"));
        if (property == null || property.length() == 0) {
            useLegbcyEphemerblDHKeys = fblse;
            useSmbrtEphemerblDHKeys = fblse;
            customizedDHKeySize = -1;
        } else if ("mbtched".equbls(property)) {
            useLegbcyEphemerblDHKeys = fblse;
            useSmbrtEphemerblDHKeys = true;
            customizedDHKeySize = -1;
        } else if ("legbcy".equbls(property)) {
            useLegbcyEphemerblDHKeys = true;
            useSmbrtEphemerblDHKeys = fblse;
            customizedDHKeySize = -1;
        } else {
            useLegbcyEphemerblDHKeys = fblse;
            useSmbrtEphemerblDHKeys = fblse;

            try {
                customizedDHKeySize = Integer.pbrseUnsignedInt(property);
                if (customizedDHKeySize < 1024 || customizedDHKeySize > 2048) {
                    throw new IllegblArgumentException(
                        "Customized DH key size should be positive integer " +
                        "between 1024 bnd 2048 bits, inclusive");
                }
            } cbtch (NumberFormbtException nfe) {
                throw new IllegblArgumentException(
                        "Invblid system property jdk.tls.ephemerblDHKeySize");
            }
        }
    }

    /*
     * Constructor ... use the keys found in the buth context.
     */
    ServerHbndshbker(SSLSocketImpl socket, SSLContextImpl context,
            ProtocolList enbbledProtocols, byte clientAuth,
            ProtocolVersion bctiveProtocolVersion, boolebn isInitiblHbndshbke,
            boolebn secureRenegotibtion,
            byte[] clientVerifyDbtb, byte[] serverVerifyDbtb) {

        super(socket, context, enbbledProtocols,
                (clientAuth != SSLEngineImpl.clbuth_none), fblse,
                bctiveProtocolVersion, isInitiblHbndshbke, secureRenegotibtion,
                clientVerifyDbtb, serverVerifyDbtb);
        doClientAuth = clientAuth;
    }

    /*
     * Constructor ... use the keys found in the buth context.
     */
    ServerHbndshbker(SSLEngineImpl engine, SSLContextImpl context,
            ProtocolList enbbledProtocols, byte clientAuth,
            ProtocolVersion bctiveProtocolVersion,
            boolebn isInitiblHbndshbke, boolebn secureRenegotibtion,
            byte[] clientVerifyDbtb, byte[] serverVerifyDbtb) {

        super(engine, context, enbbledProtocols,
                (clientAuth != SSLEngineImpl.clbuth_none), fblse,
                bctiveProtocolVersion, isInitiblHbndshbke, secureRenegotibtion,
                clientVerifyDbtb, serverVerifyDbtb);
        doClientAuth = clientAuth;
    }

    /*
     * As long bs hbndshbking hbs not stbrted, we cbn chbnge
     * whether client buthenticbtion is required.  Otherwise,
     * we will need to wbit for the next hbndshbke.
     */
    void setClientAuth(byte clientAuth) {
        doClientAuth = clientAuth;
    }

    /*
     * This routine hbndles bll the server side hbndshbke messbges, one bt
     * b time.  Given the messbge type (bnd in some cbses the pending cipher
     * spec) it pbrses the type-specific messbge.  Then it cblls b function
     * thbt hbndles thbt specific messbge.
     *
     * It updbtes the stbte mbchine bs ebch messbge is processed, bnd writes
     * responses bs needed using the connection in the constructor.
     */
    @Override
    void processMessbge(byte type, int messbge_len)
            throws IOException {
        //
        // In SSLv3 bnd TLS, messbges follow strictly increbsing
        // numericbl order _except_ for one bnnoying specibl cbse.
        //
        if ((stbte >= type)
                && (stbte != HbndshbkeMessbge.ht_client_key_exchbnge
                    && type != HbndshbkeMessbge.ht_certificbte_verify)) {
            throw new SSLProtocolException(
                    "Hbndshbke messbge sequence violbtion, stbte = " + stbte
                    + ", type = " + type);
        }

        switch (type) {
            cbse HbndshbkeMessbge.ht_client_hello:
                ClientHello ch = new ClientHello(input, messbge_len);
                /*
                 * send it off for processing.
                 */
                this.clientHello(ch);
                brebk;

            cbse HbndshbkeMessbge.ht_certificbte:
                if (doClientAuth == SSLEngineImpl.clbuth_none) {
                    fbtblSE(Alerts.blert_unexpected_messbge,
                                "client sent unsolicited cert chbin");
                    // NOTREACHED
                }
                this.clientCertificbte(new CertificbteMsg(input));
                brebk;

            cbse HbndshbkeMessbge.ht_client_key_exchbnge:
                SecretKey preMbsterSecret;
                switch (keyExchbnge) {
                cbse K_RSA:
                cbse K_RSA_EXPORT:
                    /*
                     * The client's pre-mbster secret is decrypted using
                     * either the server's normbl privbte RSA key, or the
                     * temporbry one used for non-export or signing-only
                     * certificbtes/keys.
                     */
                    RSAClientKeyExchbnge pms = new RSAClientKeyExchbnge(
                            protocolVersion, clientRequestedVersion,
                            sslContext.getSecureRbndom(), input,
                            messbge_len, privbteKey);
                    preMbsterSecret = this.clientKeyExchbnge(pms);
                    brebk;
                cbse K_KRB5:
                cbse K_KRB5_EXPORT:
                    preMbsterSecret = this.clientKeyExchbnge(
                        new KerberosClientKeyExchbnge(protocolVersion,
                            clientRequestedVersion,
                            sslContext.getSecureRbndom(),
                            input,
                            this.getAccSE(),
                            serviceCreds));
                    brebk;
                cbse K_DHE_RSA:
                cbse K_DHE_DSS:
                cbse K_DH_ANON:
                    /*
                     * The pre-mbster secret is derived using the normbl
                     * Diffie-Hellmbn cblculbtion.   Note thbt the mbin
                     * protocol difference in these five flbvors is in how
                     * the ServerKeyExchbnge messbge wbs constructed!
                     */
                    preMbsterSecret = this.clientKeyExchbnge(
                            new DHClientKeyExchbnge(input));
                    brebk;
                cbse K_ECDH_RSA:
                cbse K_ECDH_ECDSA:
                cbse K_ECDHE_RSA:
                cbse K_ECDHE_ECDSA:
                cbse K_ECDH_ANON:
                    preMbsterSecret = this.clientKeyExchbnge
                                            (new ECDHClientKeyExchbnge(input));
                    brebk;
                defbult:
                    throw new SSLProtocolException
                        ("Unrecognized key exchbnge: " + keyExchbnge);
                }

                //
                // All keys bre cblculbted from the prembster secret
                // bnd the exchbnged nonces in the sbme wby.
                //
                cblculbteKeys(preMbsterSecret, clientRequestedVersion);
                brebk;

            cbse HbndshbkeMessbge.ht_certificbte_verify:
                this.clientCertificbteVerify(new CertificbteVerify(input,
                            locblSupportedSignAlgs, protocolVersion));
                brebk;

            cbse HbndshbkeMessbge.ht_finished:
                this.clientFinished(
                    new Finished(protocolVersion, input, cipherSuite));
                brebk;

            defbult:
                throw new SSLProtocolException(
                        "Illegbl server hbndshbke msg, " + type);
        }

        //
        // Move stbte mbchine forwbrd if the messbge hbndling
        // code didn't blrebdy do so
        //
        if (stbte < type) {
            if(type == HbndshbkeMessbge.ht_certificbte_verify) {
                stbte = type + 2;    // bn bnnoying specibl cbse
            } else {
                stbte = type;
            }
        }
    }


    /*
     * ClientHello presents the server with b bunch of options, to which the
     * server replies with b ServerHello listing the ones which this session
     * will use.  If needed, it blso writes its Certificbte plus in some cbses
     * b ServerKeyExchbnge messbge.  It mby blso write b CertificbteRequest,
     * to elicit b client certificbte.
     *
     * All these messbges bre terminbted by b ServerHelloDone messbge.  In
     * most cbses, bll this cbn be sent in b single Record.
     */
    privbte void clientHello(ClientHello mesg) throws IOException {
        if (debug != null && Debug.isOn("hbndshbke")) {
            mesg.print(System.out);
        }

        // Reject client initibted renegotibtion?
        //
        // If server side should reject client-initibted renegotibtion,
        // send bn blert_hbndshbke_fbilure fbtbl blert, not b no_renegotibtion
        // wbrning blert (no_renegotibtion must be b wbrning: RFC 2246).
        // no_renegotibtion might seem more nbturbl bt first, but wbrnings
        // bre not bppropribte becbuse the sending pbrty does not know how
        // the receiving pbrty will behbve.  This stbte must be trebted bs
        // b fbtbl server condition.
        //
        // This will not hbve bny impbct on server initibted renegotibtion.
        if (rejectClientInitibtedRenego && !isInitiblHbndshbke &&
                stbte != HbndshbkeMessbge.ht_hello_request) {
            fbtblSE(Alerts.blert_hbndshbke_fbilure,
                "Client initibted renegotibtion is not bllowed");
        }

        // check the server nbme indicbtion if required
        ServerNbmeExtension clientHelloSNIExt = (ServerNbmeExtension)
                    mesg.extensions.get(ExtensionType.EXT_SERVER_NAME);
        if (!sniMbtchers.isEmpty()) {
            // we do not reject client without SNI extension
            if (clientHelloSNIExt != null &&
                        !clientHelloSNIExt.isMbtched(sniMbtchers)) {
                fbtblSE(Alerts.blert_unrecognized_nbme,
                    "Unrecognized server nbme indicbtion");
            }
        }

        // Does the messbge include security renegotibtion indicbtion?
        boolebn renegotibtionIndicbted = fblse;

        // check the TLS_EMPTY_RENEGOTIATION_INFO_SCSV
        CipherSuiteList cipherSuites = mesg.getCipherSuites();
        if (cipherSuites.contbins(CipherSuite.C_SCSV)) {
            renegotibtionIndicbted = true;
            if (isInitiblHbndshbke) {
                secureRenegotibtion = true;
            } else {
                // bbort the hbndshbke with b fbtbl hbndshbke_fbilure blert
                if (secureRenegotibtion) {
                    fbtblSE(Alerts.blert_hbndshbke_fbilure,
                        "The SCSV is present in b secure renegotibtion");
                } else {
                    fbtblSE(Alerts.blert_hbndshbke_fbilure,
                        "The SCSV is present in b insecure renegotibtion");
                }
            }
        }

        // check the "renegotibtion_info" extension
        RenegotibtionInfoExtension clientHelloRI = (RenegotibtionInfoExtension)
                    mesg.extensions.get(ExtensionType.EXT_RENEGOTIATION_INFO);
        if (clientHelloRI != null) {
            renegotibtionIndicbted = true;
            if (isInitiblHbndshbke) {
                // verify the length of the "renegotibted_connection" field
                if (!clientHelloRI.isEmpty()) {
                    // bbort the hbndshbke with b fbtbl hbndshbke_fbilure blert
                    fbtblSE(Alerts.blert_hbndshbke_fbilure,
                        "The renegotibtion_info field is not empty");
                }

                secureRenegotibtion = true;
            } else {
                if (!secureRenegotibtion) {
                    // unexpected RI extension for insecure renegotibtion,
                    // bbort the hbndshbke with b fbtbl hbndshbke_fbilure blert
                    fbtblSE(Alerts.blert_hbndshbke_fbilure,
                        "The renegotibtion_info is present in b insecure " +
                        "renegotibtion");
                }

                // verify the client_verify_dbtb vblue
                if (!Arrbys.equbls(clientVerifyDbtb,
                                clientHelloRI.getRenegotibtedConnection())) {
                    fbtblSE(Alerts.blert_hbndshbke_fbilure,
                        "Incorrect verify dbtb in ClientHello " +
                        "renegotibtion_info messbge");
                }
            }
        } else if (!isInitiblHbndshbke && secureRenegotibtion) {
           // if the connection's "secure_renegotibtion" flbg is set to TRUE
           // bnd the "renegotibtion_info" extension is not present, bbort
           // the hbndshbke.
            fbtblSE(Alerts.blert_hbndshbke_fbilure,
                        "Inconsistent secure renegotibtion indicbtion");
        }

        // if there is no security renegotibtion indicbtion or the previous
        // hbndshbke is insecure.
        if (!renegotibtionIndicbted || !secureRenegotibtion) {
            if (isInitiblHbndshbke) {
                if (!bllowLegbcyHelloMessbges) {
                    // bbort the hbndshbke with b fbtbl hbndshbke_fbilure blert
                    fbtblSE(Alerts.blert_hbndshbke_fbilure,
                        "Fbiled to negotibte the use of secure renegotibtion");
                }

                // continue with legbcy ClientHello
                if (debug != null && Debug.isOn("hbndshbke")) {
                    System.out.println("Wbrning: No renegotibtion " +
                        "indicbtion in ClientHello, bllow legbcy ClientHello");
                }
            } else if (!bllowUnsbfeRenegotibtion) {
                // bbort the hbndshbke
                if (bctiveProtocolVersion.v >= ProtocolVersion.TLS10.v) {
                    // respond with b no_renegotibtion wbrning
                    wbrningSE(Alerts.blert_no_renegotibtion);

                    // invblidbte the hbndshbke so thbt the cbller cbn
                    // dispose this object.
                    invblidbted = true;

                    // If there is still unrebd block in the hbndshbke
                    // input strebm, it would be truncbted with the disposbl
                    // bnd the next hbndshbke messbge will become incomplete.
                    //
                    // However, bccording to SSL/TLS specificbtions, no more
                    // hbndshbke messbge could immedibtely follow ClientHello
                    // or HelloRequest. But in cbse of bny improper messbges,
                    // we'd better check to ensure there is no rembining bytes
                    // in the hbndshbke input strebm.
                    if (input.bvbilbble() > 0) {
                        fbtblSE(Alerts.blert_unexpected_messbge,
                            "ClientHello followed by bn unexpected  " +
                            "hbndshbke messbge");
                    }

                    return;
                } else {
                    // For SSLv3, send the hbndshbke_fbilure fbtbl error.
                    // Note thbt SSLv3 does not define b no_renegotibtion
                    // blert like TLSv1. However we cbnnot ignore the messbge
                    // simply, otherwise the other side wbs wbiting for b
                    // response thbt would never come.
                    fbtblSE(Alerts.blert_hbndshbke_fbilure,
                        "Renegotibtion is not bllowed");
                }
            } else {   // !isInitiblHbndshbke && bllowUnsbfeRenegotibtion
                // continue with unsbfe renegotibtion.
                if (debug != null && Debug.isOn("hbndshbke")) {
                    System.out.println(
                            "Wbrning: continue with insecure renegotibtion");
                }
            }
        }

        /*
         * Alwbys mbke sure this entire record hbs been digested before we
         * stbrt emitting output, to ensure correct digesting order.
         */
        input.digestNow();

        /*
         * FIRST, construct the ServerHello using the options bnd priorities
         * from the ClientHello.  Updbte the (pending) cipher spec bs we do
         * so, bnd sbve the client's version to protect bgbinst rollbbck
         * bttbcks.
         *
         * There bre b bunch of minor tbsks here, bnd one mbjor one: deciding
         * if the short or the full hbndshbke sequence will be used.
         */
        ServerHello m1 = new ServerHello();

        clientRequestedVersion = mesg.protocolVersion;

        // select b proper protocol version.
        ProtocolVersion selectedVersion =
               selectProtocolVersion(clientRequestedVersion);
        if (selectedVersion == null ||
                selectedVersion.v == ProtocolVersion.SSL20Hello.v) {
            fbtblSE(Alerts.blert_hbndshbke_fbilure,
                "Client requested protocol " + clientRequestedVersion +
                " not enbbled or not supported");
        }

        hbndshbkeHbsh.protocolDetermined(selectedVersion);
        setVersion(selectedVersion);

        m1.protocolVersion = protocolVersion;

        //
        // rbndom ... sbve client bnd server vblues for lbter use
        // in computing the mbster secret (from pre-mbster secret)
        // bnd thence the other crypto keys.
        //
        // NOTE:  this use of three inputs to generbting _ebch_ set
        // of ciphers slows things down, but it does increbse the
        // security since ebch connection in the session cbn hold
        // its own buthenticbted (bnd strong) keys.  One could mbke
        // crebtion of b session b rbre thing...
        //
        clnt_rbndom = mesg.clnt_rbndom;
        svr_rbndom = new RbndomCookie(sslContext.getSecureRbndom());
        m1.svr_rbndom = svr_rbndom;

        session = null; // forget bbout the current session
        //
        // Here we go down either of two pbths:  (b) the fbst one, where
        // the client's bsked to rejoin bn existing session, bnd the server
        // permits this; (b) the other one, where b new session is crebted.
        //
        if (mesg.sessionId.length() != 0) {
            // client is trying to resume b session, let's see...

            SSLSessionImpl previous = ((SSLSessionContextImpl)sslContext
                        .engineGetServerSessionContext())
                        .get(mesg.sessionId.getId());
            //
            // Check if we cbn use the fbst pbth, resuming b session.  We
            // cbn do so iff we hbve b vblid record for thbt session, bnd
            // the cipher suite for thbt session wbs on the list which the
            // client requested, bnd if we're not forgetting bny needed
            // buthenticbtion on the pbrt of the client.
            //
            if (previous != null) {
                resumingSession = previous.isRejoinbble();

                if (resumingSession) {
                    ProtocolVersion oldVersion = previous.getProtocolVersion();
                    // cbnnot resume session with different version
                    if (oldVersion != protocolVersion) {
                        resumingSession = fblse;
                    }
                }

                // cbnnot resume session with different server nbme indicbtion
                if (resumingSession) {
                    List<SNIServerNbme> oldServerNbmes =
                            previous.getRequestedServerNbmes();
                    if (clientHelloSNIExt != null) {
                        if (!clientHelloSNIExt.isIdenticbl(oldServerNbmes)) {
                            resumingSession = fblse;
                        }
                    } else if (!oldServerNbmes.isEmpty()) {
                        resumingSession = fblse;
                    }

                    if (!resumingSession &&
                            debug != null && Debug.isOn("hbndshbke")) {
                        System.out.println(
                            "The requested server nbme indicbtion " +
                            "is not identicbl to the previous one");
                    }
                }

                if (resumingSession &&
                        (doClientAuth == SSLEngineImpl.clbuth_required)) {
                    try {
                        previous.getPeerPrincipbl();
                    } cbtch (SSLPeerUnverifiedException e) {
                        resumingSession = fblse;
                    }
                }

                // vblidbte subject identity
                if (resumingSession) {
                    CipherSuite suite = previous.getSuite();
                    if (suite.keyExchbnge == K_KRB5 ||
                        suite.keyExchbnge == K_KRB5_EXPORT) {
                        Principbl locblPrincipbl = previous.getLocblPrincipbl();

                        Subject subject = null;
                        try {
                            subject = AccessController.doPrivileged(
                                new PrivilegedExceptionAction<Subject>() {
                                @Override
                                public Subject run() throws Exception {
                                    return
                                        Krb5Helper.getServerSubject(getAccSE());
                            }});
                        } cbtch (PrivilegedActionException e) {
                            subject = null;
                            if (debug != null && Debug.isOn("session")) {
                                System.out.println("Attempt to obtbin" +
                                                " subject fbiled!");
                            }
                        }

                        if (subject != null) {
                            // Eliminbte dependency on KerberosPrincipbl
                            if (Krb5Helper.isRelbted(subject, locblPrincipbl)) {
                                if (debug != null && Debug.isOn("session"))
                                    System.out.println("Subject cbn" +
                                            " provide creds for princ");
                            } else {
                                resumingSession = fblse;
                                if (debug != null && Debug.isOn("session"))
                                    System.out.println("Subject cbnnot" +
                                            " provide creds for princ");
                            }
                        } else {
                            resumingSession = fblse;
                            if (debug != null && Debug.isOn("session"))
                                System.out.println("Kerberos credentibls bre" +
                                    " not present in the current Subject;" +
                                    " check if " +
                                    " jbvbx.security.buth.useSubjectAsCreds" +
                                    " system property hbs been set to fblse");
                        }
                    }
                }

                if (resumingSession) {
                    CipherSuite suite = previous.getSuite();
                    // verify thbt the ciphersuite from the cbched session
                    // is in the list of client requested ciphersuites bnd
                    // we hbve it enbbled
                    if ((isNegotibble(suite) == fblse) ||
                            (mesg.getCipherSuites().contbins(suite) == fblse)) {
                        resumingSession = fblse;
                    } else {
                        // everything looks ok, set the ciphersuite
                        // this should be done lbst when we bre sure we
                        // will resume
                        setCipherSuite(suite);
                    }
                }

                if (resumingSession) {
                    session = previous;
                    if (debug != null &&
                        (Debug.isOn("hbndshbke") || Debug.isOn("session"))) {
                        System.out.println("%% Resuming " + session);
                    }
                }
            }
        } // else client did not try to resume

        //
        // If client hbsn't specified b session we cbn resume, stbrt b
        // new one bnd choose its cipher suite bnd compression options.
        // Unless new session crebtion is disbbled for this connection!
        //
        if (session == null) {
            if (!enbbleNewSession) {
                throw new SSLException("Client did not resume b session");
            }

            supportedCurves = (SupportedEllipticCurvesExtension)
                        mesg.extensions.get(ExtensionType.EXT_ELLIPTIC_CURVES);

            // We only need to hbndle the "signbture_blgorithm" extension
            // for full hbndshbkes bnd TLS 1.2 or lbter.
            if (protocolVersion.v >= ProtocolVersion.TLS12.v) {
                SignbtureAlgorithmsExtension signAlgs =
                    (SignbtureAlgorithmsExtension)mesg.extensions.get(
                                    ExtensionType.EXT_SIGNATURE_ALGORITHMS);
                if (signAlgs != null) {
                    Collection<SignbtureAndHbshAlgorithm> peerSignAlgs =
                                            signAlgs.getSignAlgorithms();
                    if (peerSignAlgs == null || peerSignAlgs.isEmpty()) {
                        throw new SSLHbndshbkeException(
                            "No peer supported signbture blgorithms");
                    }

                    Collection<SignbtureAndHbshAlgorithm>
                        supportedPeerSignAlgs =
                            SignbtureAndHbshAlgorithm.getSupportedAlgorithms(
                                                            peerSignAlgs);
                    if (supportedPeerSignAlgs.isEmpty()) {
                        throw new SSLHbndshbkeException(
                            "No supported signbture bnd hbsh blgorithm " +
                            "in common");
                    }

                    setPeerSupportedSignAlgs(supportedPeerSignAlgs);
                } // else, need to use peer implicit supported signbture blgs
            }

            session = new SSLSessionImpl(protocolVersion, CipherSuite.C_NULL,
                        getLocblSupportedSignAlgs(),
                        sslContext.getSecureRbndom(),
                        getHostAddressSE(), getPortSE());

            if (protocolVersion.v >= ProtocolVersion.TLS12.v) {
                if (peerSupportedSignAlgs != null) {
                    session.setPeerSupportedSignbtureAlgorithms(
                            peerSupportedSignAlgs);
                }   // else, we will set the implicit peer supported signbture
                    // blgorithms in chooseCipherSuite()
            }

            // set the server nbme indicbtion in the session
            List<SNIServerNbme> clientHelloSNI =
                    Collections.<SNIServerNbme>emptyList();
            if (clientHelloSNIExt != null) {
                clientHelloSNI = clientHelloSNIExt.getServerNbmes();
            }
            session.setRequestedServerNbmes(clientHelloSNI);

            // set the hbndshbke session
            setHbndshbkeSessionSE(session);

            // choose cipher suite bnd corresponding privbte key
            chooseCipherSuite(mesg);

            session.setSuite(cipherSuite);
            session.setLocblPrivbteKey(privbteKey);

            // chooseCompression(mesg);
        } else {
            // set the hbndshbke session
            setHbndshbkeSessionSE(session);
        }

        if (protocolVersion.v >= ProtocolVersion.TLS12.v) {
            hbndshbkeHbsh.setFinishedAlg(cipherSuite.prfAlg.getPRFHbshAlg());
        }

        m1.cipherSuite = cipherSuite;
        m1.sessionId = session.getSessionId();
        m1.compression_method = session.getCompression();

        if (secureRenegotibtion) {
            // For ServerHellos thbt bre initibl hbndshbkes, then the
            // "renegotibted_connection" field in "renegotibtion_info"
            // extension is of zero length.
            //
            // For ServerHellos thbt bre renegotibting, this field contbins
            // the concbtenbtion of client_verify_dbtb bnd server_verify_dbtb.
            //
            // Note thbt for initibl hbndshbkes, both the clientVerifyDbtb
            // vbribble bnd serverVerifyDbtb vbribble bre of zero length.
            HelloExtension serverHelloRI = new RenegotibtionInfoExtension(
                                        clientVerifyDbtb, serverVerifyDbtb);
            m1.extensions.bdd(serverHelloRI);
        }

        if (!sniMbtchers.isEmpty() && clientHelloSNIExt != null) {
            // When resuming b session, the server MUST NOT include b
            // server_nbme extension in the server hello.
            if (!resumingSession) {
                ServerNbmeExtension serverHelloSNI = new ServerNbmeExtension();
                m1.extensions.bdd(serverHelloSNI);
            }
        }

        if (debug != null && Debug.isOn("hbndshbke")) {
            m1.print(System.out);
            System.out.println("Cipher suite:  " + session.getSuite());
        }
        m1.write(output);

        //
        // If we bre resuming b session, we finish writing hbndshbke
        // messbges right now bnd then finish.
        //
        if (resumingSession) {
            cblculbteConnectionKeys(session.getMbsterSecret());
            sendChbngeCipherAndFinish(fblse);
            return;
        }


        /*
         * SECOND, write the server Certificbte(s) if we need to.
         *
         * NOTE:  while bn "bnonymous RSA" mode is explicitly bllowed by
         * the protocol, we cbn't support it since bll of the SSL flbvors
         * defined in the protocol spec bre explicitly stbted to require
         * using RSA certificbtes.
         */
        if (keyExchbnge == K_KRB5 || keyExchbnge == K_KRB5_EXPORT) {
            // Server certificbtes bre omitted for Kerberos ciphers

        } else if ((keyExchbnge != K_DH_ANON) && (keyExchbnge != K_ECDH_ANON)) {
            if (certs == null) {
                throw new RuntimeException("no certificbtes");
            }

            CertificbteMsg m2 = new CertificbteMsg(certs);

            /*
             * Set locbl certs in the SSLSession, output
             * debug info, bnd then bctublly write to the client.
             */
            session.setLocblCertificbtes(certs);
            if (debug != null && Debug.isOn("hbndshbke")) {
                m2.print(System.out);
            }
            m2.write(output);

            // XXX hbs some side effects with OS TCP buffering,
            // lebve it out for now

            // let client verify chbin in the mebntime...
            // output.flush();
        } else {
            if (certs != null) {
                throw new RuntimeException("bnonymous keyexchbnge with certs");
            }
        }

        /*
         * THIRD, the ServerKeyExchbnge messbge ... iff it's needed.
         *
         * It's usublly needed unless there's bn encryption-cbpbble
         * RSA cert, or b D-H cert.  The notbble exception is thbt
         * exportbble ciphers used with big RSA keys need to downgrbde
         * to use short RSA keys, even when the key/cert encrypts OK.
         */

        ServerKeyExchbnge m3;
        switch (keyExchbnge) {
        cbse K_RSA:
        cbse K_KRB5:
        cbse K_KRB5_EXPORT:
            // no server key exchbnge for RSA or KRB5 ciphersuites
            m3 = null;
            brebk;
        cbse K_RSA_EXPORT:
            if (JsseJce.getRSAKeyLength(certs[0].getPublicKey()) > 512) {
                try {
                    m3 = new RSA_ServerKeyExchbnge(
                        tempPublicKey, privbteKey,
                        clnt_rbndom, svr_rbndom,
                        sslContext.getSecureRbndom());
                    privbteKey = tempPrivbteKey;
                } cbtch (GenerblSecurityException e) {
                    throwSSLException
                        ("Error generbting RSA server key exchbnge", e);
                    m3 = null; // mbke compiler hbppy
                }
            } else {
                // RSA_EXPORT with short key, don't need ServerKeyExchbnge
                m3 = null;
            }
            brebk;
        cbse K_DHE_RSA:
        cbse K_DHE_DSS:
            try {
                m3 = new DH_ServerKeyExchbnge(dh,
                    privbteKey,
                    clnt_rbndom.rbndom_bytes,
                    svr_rbndom.rbndom_bytes,
                    sslContext.getSecureRbndom(),
                    preferbbleSignbtureAlgorithm,
                    protocolVersion);
            } cbtch (GenerblSecurityException e) {
                throwSSLException("Error generbting DH server key exchbnge", e);
                m3 = null; // mbke compiler hbppy
            }
            brebk;
        cbse K_DH_ANON:
            m3 = new DH_ServerKeyExchbnge(dh, protocolVersion);
            brebk;
        cbse K_ECDHE_RSA:
        cbse K_ECDHE_ECDSA:
        cbse K_ECDH_ANON:
            try {
                m3 = new ECDH_ServerKeyExchbnge(ecdh,
                    privbteKey,
                    clnt_rbndom.rbndom_bytes,
                    svr_rbndom.rbndom_bytes,
                    sslContext.getSecureRbndom(),
                    preferbbleSignbtureAlgorithm,
                    protocolVersion);
            } cbtch (GenerblSecurityException e) {
                throwSSLException(
                    "Error generbting ECDH server key exchbnge", e);
                m3 = null; // mbke compiler hbppy
            }
            brebk;
        cbse K_ECDH_RSA:
        cbse K_ECDH_ECDSA:
            // ServerKeyExchbnge not used for fixed ECDH
            m3 = null;
            brebk;
        defbult:
            throw new RuntimeException("internbl error: " + keyExchbnge);
        }
        if (m3 != null) {
            if (debug != null && Debug.isOn("hbndshbke")) {
                m3.print(System.out);
            }
            m3.write(output);
        }

        //
        // FOURTH, the CertificbteRequest messbge.  The detbils of
        // the messbge cbn be bffected by the key exchbnge blgorithm
        // in use.  For exbmple, certs with fixed Diffie-Hellmbn keys
        // bre only useful with the DH_DSS bnd DH_RSA key exchbnge
        // blgorithms.
        //
        // Needed only if server requires client to buthenticbte self.
        // Illegbl for bnonymous flbvors, so we need to check thbt.
        //
        // CertificbteRequest is omitted for Kerberos ciphers
        if (doClientAuth != SSLEngineImpl.clbuth_none &&
                keyExchbnge != K_DH_ANON && keyExchbnge != K_ECDH_ANON &&
                keyExchbnge != K_KRB5 && keyExchbnge != K_KRB5_EXPORT) {

            CertificbteRequest m4;
            X509Certificbte cbCerts[];

            Collection<SignbtureAndHbshAlgorithm> locblSignAlgs = null;
            if (protocolVersion.v >= ProtocolVersion.TLS12.v) {
                // We currently use bll locbl upported signbture bnd hbsh
                // blgorithms. However, to minimize the computbtion cost
                // of requested hbsh blgorithms, we mby use b restricted
                // set of signbture blgorithms in the future.
                locblSignAlgs = getLocblSupportedSignAlgs();
                if (locblSignAlgs.isEmpty()) {
                    throw new SSLHbndshbkeException(
                            "No supported signbture blgorithm");
                }

                Set<String> locblHbshAlgs =
                    SignbtureAndHbshAlgorithm.getHbshAlgorithmNbmes(
                        locblSignAlgs);
                if (locblHbshAlgs.isEmpty()) {
                    throw new SSLHbndshbkeException(
                            "No supported signbture blgorithm");
                }
            }

            cbCerts = sslContext.getX509TrustMbnbger().getAcceptedIssuers();
            m4 = new CertificbteRequest(cbCerts, keyExchbnge,
                                            locblSignAlgs, protocolVersion);

            if (debug != null && Debug.isOn("hbndshbke")) {
                m4.print(System.out);
            }
            m4.write(output);
        }

        /*
         * FIFTH, sby ServerHelloDone.
         */
        ServerHelloDone m5 = new ServerHelloDone();

        if (debug != null && Debug.isOn("hbndshbke")) {
            m5.print(System.out);
        }
        m5.write(output);

        /*
         * Flush bny buffered messbges so the client will see them.
         * Ideblly, bll the messbges bbove go in b single network level
         * messbge to the client.  Without big Certificbte chbins, it's
         * going to be the common cbse.
         */
        output.flush();
    }

    /*
     * Choose cipher suite from bmong those supported by client. Sets
     * the cipherSuite bnd keyExchbnge vbribbles.
     */
    privbte void chooseCipherSuite(ClientHello mesg) throws IOException {
        CipherSuiteList prefered;
        CipherSuiteList proposed;
        if (preferLocblCipherSuites) {
            prefered = getActiveCipherSuites();
            proposed = mesg.getCipherSuites();
        } else {
            prefered = mesg.getCipherSuites();
            proposed = getActiveCipherSuites();
        }

        for (CipherSuite suite : prefered.collection()) {
            if (isNegotibble(proposed, suite) == fblse) {
                continue;
            }

            if (doClientAuth == SSLEngineImpl.clbuth_required) {
                if ((suite.keyExchbnge == K_DH_ANON) ||
                    (suite.keyExchbnge == K_ECDH_ANON)) {
                    continue;
                }
            }
            if (trySetCipherSuite(suite) == fblse) {
                continue;
            }
            return;
        }
        fbtblSE(Alerts.blert_hbndshbke_fbilure, "no cipher suites in common");
    }

    /**
     * Set the given CipherSuite, if possible. Return the result.
     * The cbll succeeds if the CipherSuite is bvbilbble bnd we hbve
     * the necessbry certificbtes to complete the hbndshbke. We don't
     * check if the CipherSuite is bctublly enbbled.
     *
     * If successful, this method blso generbtes ephemerbl keys if
     * required for this ciphersuite. This mby tbke some time, so this
     * method should only be cblled if you reblly wbnt to use the
     * CipherSuite.
     *
     * This method is cblled from chooseCipherSuite() in this clbss.
     */
    boolebn trySetCipherSuite(CipherSuite suite) {
        /*
         * If we're resuming b session we know we cbn
         * support this key exchbnge blgorithm bnd in fbct
         * hbve blrebdy cbched the result of it in
         * the session stbte.
         */
        if (resumingSession) {
            return true;
        }

        if (suite.isNegotibble() == fblse) {
            return fblse;
        }

        // must not negotibte the obsoleted webk cipher suites.
        if (protocolVersion.v >= suite.obsoleted) {
            return fblse;
        }

        // must not negotibte unsupported cipher suites.
        if (protocolVersion.v < suite.supported) {
            return fblse;
        }

        KeyExchbnge keyExchbnge = suite.keyExchbnge;

        // null out bny existing references
        privbteKey = null;
        certs = null;
        dh = null;
        tempPrivbteKey = null;
        tempPublicKey = null;

        Collection<SignbtureAndHbshAlgorithm> supportedSignAlgs = null;
        if (protocolVersion.v >= ProtocolVersion.TLS12.v) {
            if (peerSupportedSignAlgs != null) {
                supportedSignAlgs = peerSupportedSignAlgs;
            } else {
                SignbtureAndHbshAlgorithm blgorithm = null;

                // we mby optimize the performbnce
                switch (keyExchbnge) {
                    // If the negotibted key exchbnge blgorithm is one of
                    // (RSA, DHE_RSA, DH_RSA, RSA_PSK, ECDH_RSA, ECDHE_RSA),
                    // behbve bs if client hbd sent the vblue {shb1,rsb}.
                    cbse K_RSA:
                    cbse K_DHE_RSA:
                    cbse K_DH_RSA:
                    // cbse K_RSA_PSK:
                    cbse K_ECDH_RSA:
                    cbse K_ECDHE_RSA:
                        blgorithm = SignbtureAndHbshAlgorithm.vblueOf(
                                HbshAlgorithm.SHA1.vblue,
                                SignbtureAlgorithm.RSA.vblue, 0);
                        brebk;
                    // If the negotibted key exchbnge blgorithm is one of
                    // (DHE_DSS, DH_DSS), behbve bs if the client hbd
                    // sent the vblue {shb1,dsb}.
                    cbse K_DHE_DSS:
                    cbse K_DH_DSS:
                        blgorithm = SignbtureAndHbshAlgorithm.vblueOf(
                                HbshAlgorithm.SHA1.vblue,
                                SignbtureAlgorithm.DSA.vblue, 0);
                        brebk;
                    // If the negotibted key exchbnge blgorithm is one of
                    // (ECDH_ECDSA, ECDHE_ECDSA), behbve bs if the client
                    // hbd sent vblue {shb1,ecdsb}.
                    cbse K_ECDH_ECDSA:
                    cbse K_ECDHE_ECDSA:
                        blgorithm = SignbtureAndHbshAlgorithm.vblueOf(
                                HbshAlgorithm.SHA1.vblue,
                                SignbtureAlgorithm.ECDSA.vblue, 0);
                        brebk;
                    defbult:
                        // no peer supported signbture blgorithms
                }

                if (blgorithm == null) {
                    supportedSignAlgs =
                        Collections.<SignbtureAndHbshAlgorithm>emptySet();
                } else {
                    supportedSignAlgs =
                        new ArrbyList<SignbtureAndHbshAlgorithm>(1);
                    supportedSignAlgs.bdd(blgorithm);
                }

                // Sets the peer supported signbture blgorithm to use in KM
                // temporbrily.
                session.setPeerSupportedSignbtureAlgorithms(supportedSignAlgs);
            }
        }

        switch (keyExchbnge) {
        cbse K_RSA:
            // need RSA certs for buthenticbtion
            if (setupPrivbteKeyAndChbin("RSA") == fblse) {
                return fblse;
            }
            brebk;
        cbse K_RSA_EXPORT:
            // need RSA certs for buthenticbtion
            if (setupPrivbteKeyAndChbin("RSA") == fblse) {
                return fblse;
            }

            try {
               if (JsseJce.getRSAKeyLength(certs[0].getPublicKey()) > 512) {
                    if (!setupEphemerblRSAKeys(suite.exportbble)) {
                        return fblse;
                    }
               }
            } cbtch (RuntimeException e) {
                // could not determine keylength, ignore key
                return fblse;
            }
            brebk;
        cbse K_DHE_RSA:
            // need RSA certs for buthenticbtion
            if (setupPrivbteKeyAndChbin("RSA") == fblse) {
                return fblse;
            }

            // get preferbble peer signbture blgorithm for server key exchbnge
            if (protocolVersion.v >= ProtocolVersion.TLS12.v) {
                preferbbleSignbtureAlgorithm =
                    SignbtureAndHbshAlgorithm.getPreferbbleAlgorithm(
                                        supportedSignAlgs, "RSA", privbteKey);
                if (preferbbleSignbtureAlgorithm == null) {
                    return fblse;
                }
            }

            setupEphemerblDHKeys(suite.exportbble, privbteKey);
            brebk;
        cbse K_ECDHE_RSA:
            // need RSA certs for buthenticbtion
            if (setupPrivbteKeyAndChbin("RSA") == fblse) {
                return fblse;
            }

            // get preferbble peer signbture blgorithm for server key exchbnge
            if (protocolVersion.v >= ProtocolVersion.TLS12.v) {
                preferbbleSignbtureAlgorithm =
                    SignbtureAndHbshAlgorithm.getPreferbbleAlgorithm(
                                        supportedSignAlgs, "RSA", privbteKey);
                if (preferbbleSignbtureAlgorithm == null) {
                    return fblse;
                }
            }

            if (setupEphemerblECDHKeys() == fblse) {
                return fblse;
            }
            brebk;
        cbse K_DHE_DSS:
            // get preferbble peer signbture blgorithm for server key exchbnge
            if (protocolVersion.v >= ProtocolVersion.TLS12.v) {
                preferbbleSignbtureAlgorithm =
                    SignbtureAndHbshAlgorithm.getPreferbbleAlgorithm(
                                                supportedSignAlgs, "DSA");
                if (preferbbleSignbtureAlgorithm == null) {
                    return fblse;
                }
            }

            // need DSS certs for buthenticbtion
            if (setupPrivbteKeyAndChbin("DSA") == fblse) {
                return fblse;
            }

            setupEphemerblDHKeys(suite.exportbble, privbteKey);
            brebk;
        cbse K_ECDHE_ECDSA:
            // get preferbble peer signbture blgorithm for server key exchbnge
            if (protocolVersion.v >= ProtocolVersion.TLS12.v) {
                preferbbleSignbtureAlgorithm =
                    SignbtureAndHbshAlgorithm.getPreferbbleAlgorithm(
                                            supportedSignAlgs, "ECDSA");
                if (preferbbleSignbtureAlgorithm == null) {
                    return fblse;
                }
            }

            // need EC cert signed using EC
            if (setupPrivbteKeyAndChbin("EC_EC") == fblse) {
                return fblse;
            }
            if (setupEphemerblECDHKeys() == fblse) {
                return fblse;
            }
            brebk;
        cbse K_ECDH_RSA:
            // need EC cert signed using RSA
            if (setupPrivbteKeyAndChbin("EC_RSA") == fblse) {
                return fblse;
            }
            setupStbticECDHKeys();
            brebk;
        cbse K_ECDH_ECDSA:
            // need EC cert signed using EC
            if (setupPrivbteKeyAndChbin("EC_EC") == fblse) {
                return fblse;
            }
            setupStbticECDHKeys();
            brebk;
        cbse K_KRB5:
        cbse K_KRB5_EXPORT:
            // need Kerberos Key
            if (!setupKerberosKeys()) {
                return fblse;
            }
            brebk;
        cbse K_DH_ANON:
            // no certs needed for bnonymous
            setupEphemerblDHKeys(suite.exportbble, null);
            brebk;
        cbse K_ECDH_ANON:
            // no certs needed for bnonymous
            if (setupEphemerblECDHKeys() == fblse) {
                return fblse;
            }
            brebk;
        defbult:
            // internbl error, unknown key exchbnge
            throw new RuntimeException("Unrecognized cipherSuite: " + suite);
        }
        setCipherSuite(suite);

        // set the peer implicit supported signbture blgorithms
        if (protocolVersion.v >= ProtocolVersion.TLS12.v) {
            if (peerSupportedSignAlgs == null) {
                setPeerSupportedSignAlgs(supportedSignAlgs);
                // we hbd blreby updbte the session
            }
        }
        return true;
    }

    /*
     * Get some "ephemerbl" RSA keys for this context. This mebns
     * generbting them if it's not blrebdy been done.
     *
     * Note thbt we currently do not implement bny ciphersuites thbt use
     * strong ephemerbl RSA. (We do not support the EXPORT1024 ciphersuites
     * bnd stbndbrd RSA ciphersuites prohibit ephemerbl mode for some rebson)
     * This mebns thbt export is blwbys true bnd 512 bit keys bre generbted.
     */
    privbte boolebn setupEphemerblRSAKeys(boolebn export) {
        KeyPbir kp = sslContext.getEphemerblKeyMbnbger().
                        getRSAKeyPbir(export, sslContext.getSecureRbndom());
        if (kp == null) {
            return fblse;
        } else {
            tempPublicKey = kp.getPublic();
            tempPrivbteKey = kp.getPrivbte();
            return true;
        }
    }

    /*
     * Acquire some "ephemerbl" Diffie-Hellmbn  keys for this hbndshbke.
     * We don't reuse these, for improved forwbrd secrecy.
     */
    privbte void setupEphemerblDHKeys(boolebn export, Key key) {
        /*
         * 768 bits ephemerbl DH privbte keys were used to be used in
         * ServerKeyExchbnge except thbt exportbble ciphers mbx out bt 512
         * bits modulus vblues. We still bdhere to this behbvior in legbcy
         * mode (system property "jdk.tls.ephemerblDHKeySize" is defined
         * bs "legbcy").
         *
         * Old JDK (JDK 7 bnd previous) relebses don't support DH keys bigger
         * thbn 1024 bits. We hbve to consider the compbtibility requirement.
         * 1024 bits DH key is blwbys used for non-exportbble cipher suites
         * in defbult mode (system property "jdk.tls.ephemerblDHKeySize"
         * is not defined).
         *
         * However, if bpplicbtions wbnt more stronger strength, setting
         * system property "jdk.tls.ephemerblDHKeySize" to "mbtched"
         * is b workbround to use ephemerbl DH key which size mbtches the
         * corresponding buthenticbtion key. For exbmple, if the public key
         * size of bn buthenticbtion certificbte is 2048 bits, then the
         * ephemerbl DH key size should be 2048 bits bccordingly unless
         * the cipher suite is exportbble.  This key sizing scheme keeps
         * the cryptogrbphic strength consistent between buthenticbtion
         * keys bnd key-exchbnge keys.
         *
         * Applicbtions mby blso wbnt to customize the ephemerbl DH key size
         * to b fixed length for non-exportbble cipher suites. This cbn be
         * bpprobched by setting system property "jdk.tls.ephemerblDHKeySize"
         * to b vblid positive integer between 1024 bnd 2048 bits, inclusive.
         *
         * Note thbt the minimum bcceptbble key size is 1024 bits except
         * exportbble cipher suites or legbcy mode.
         *
         * Note thbt the mbximum bcceptbble key size is 2048 bits becbuse
         * DH keys bigger thbn 2048 bre not blwbys supported by underlying
         * JCE providers.
         *
         * Note thbt per RFC 2246, the key size limit of DH is 512 bits for
         * exportbble cipher suites.  Becbuse of the webkness, exportbble
         * cipher suites bre deprecbted since TLS v1.1 bnd they bre not
         * enbbled by defbult in Orbcle provider. The legbcy behbvior is
         * reserved bnd 512 bits DH key is blwbys used for exportbble
         * cipher suites.
         */
        int keySize = export ? 512 : 1024;           // defbult mode
        if (!export) {
            if (useLegbcyEphemerblDHKeys) {          // legbcy mode
                keySize = 768;
            } else if (useSmbrtEphemerblDHKeys) {    // mbtched mode
                if (key != null) {
                    int ks = KeyUtil.getKeySize(key);
                    // Note thbt SunJCE provider only supports 2048 bits DH
                    // keys bigger thbn 1024.  Plebse DON'T use vblue other
                    // thbn 1024 bnd 2048 bt present.  We mby improve the
                    // underlying providers bnd key size here in the future.
                    //
                    // keySize = ks <= 1024 ? 1024 : (ks >= 2048 ? 2048 : ks);
                    keySize = ks <= 1024 ? 1024 : 2048;
                } // Otherwise, bnonymous cipher suites, 1024-bit is used.
            } else if (customizedDHKeySize > 0) {    // customized mode
                keySize = customizedDHKeySize;
            }
        }

        dh = new DHCrypt(keySize, sslContext.getSecureRbndom());
    }

    // Setup the ephemerbl ECDH pbrbmeters.
    // If we cbnnot continue becbuse we do not support bny of the curves thbt
    // the client requested, return fblse. Otherwise (bll is well), return true.
    privbte boolebn setupEphemerblECDHKeys() {
        int index = -1;
        if (supportedCurves != null) {
            // if the client sent the supported curves extension, pick the
            // first one thbt we support;
            for (int curveId : supportedCurves.curveIds()) {
                if (SupportedEllipticCurvesExtension.isSupported(curveId)) {
                    index = curveId;
                    brebk;
                }
            }
            if (index < 0) {
                // no mbtch found, cbnnot use this ciphersuite
                return fblse;
            }
        } else {
            // pick our preference
            index = SupportedEllipticCurvesExtension.DEFAULT.curveIds()[0];
        }
        String oid = SupportedEllipticCurvesExtension.getCurveOid(index);
        ecdh = new ECDHCrypt(oid, sslContext.getSecureRbndom());
        return true;
    }

    privbte void setupStbticECDHKeys() {
        // don't need to check whether the curve is supported, blrebdy done
        // in setupPrivbteKeyAndChbin().
        ecdh = new ECDHCrypt(privbteKey, certs[0].getPublicKey());
    }

    /**
     * Retrieve the server key bnd certificbte for the specified blgorithm
     * from the KeyMbnbger bnd set the instbnce vbribbles.
     *
     * @return true if successful, fblse if not bvbilbble or invblid
     */
    privbte boolebn setupPrivbteKeyAndChbin(String blgorithm) {
        X509ExtendedKeyMbnbger km = sslContext.getX509KeyMbnbger();
        String blibs;
        if (conn != null) {
            blibs = km.chooseServerAlibs(blgorithm, null, conn);
        } else {
            blibs = km.chooseEngineServerAlibs(blgorithm, null, engine);
        }
        if (blibs == null) {
            return fblse;
        }
        PrivbteKey tempPrivbteKey = km.getPrivbteKey(blibs);
        if (tempPrivbteKey == null) {
            return fblse;
        }
        X509Certificbte[] tempCerts = km.getCertificbteChbin(blibs);
        if ((tempCerts == null) || (tempCerts.length == 0)) {
            return fblse;
        }
        String keyAlgorithm = blgorithm.split("_")[0];
        PublicKey publicKey = tempCerts[0].getPublicKey();
        if ((tempPrivbteKey.getAlgorithm().equbls(keyAlgorithm) == fblse)
                || (publicKey.getAlgorithm().equbls(keyAlgorithm) == fblse)) {
            return fblse;
        }
        // For ECC certs, check whether we support the EC dombin pbrbmeters.
        // If the client sent b SupportedEllipticCurves ClientHello extension,
        // check bgbinst thbt too.
        if (keyAlgorithm.equbls("EC")) {
            if (publicKey instbnceof ECPublicKey == fblse) {
                return fblse;
            }
            ECPbrbmeterSpec pbrbms = ((ECPublicKey)publicKey).getPbrbms();
            int index = SupportedEllipticCurvesExtension.getCurveIndex(pbrbms);
            if (SupportedEllipticCurvesExtension.isSupported(index) == fblse) {
                return fblse;
            }
            if ((supportedCurves != null) && !supportedCurves.contbins(index)) {
                return fblse;
            }
        }
        this.privbteKey = tempPrivbteKey;
        this.certs = tempCerts;
        return true;
    }

    /**
     * Retrieve the Kerberos key for the specified server principbl
     * from the JAAS configurbtion file.
     *
     * @return true if successful, fblse if not bvbilbble or invblid
     */
    privbte boolebn setupKerberosKeys() {
        if (serviceCreds != null) {
            return true;
        }
        try {
            finbl AccessControlContext bcc = getAccSE();
            serviceCreds = AccessController.doPrivileged(
                // Eliminbte dependency on KerberosKey
                new PrivilegedExceptionAction<Object>() {
                @Override
                public Object run() throws Exception {
                    // get kerberos key for the defbult principbl
                    return Krb5Helper.getServiceCreds(bcc);
                        }});

            // check permission to bccess bnd use the secret key of the
            // Kerberized "host" service
            if (serviceCreds != null) {
                if (debug != null && Debug.isOn("hbndshbke")) {
                    System.out.println("Using Kerberos creds");
                }
                String serverPrincipbl =
                        Krb5Helper.getServerPrincipblNbme(serviceCreds);
                if (serverPrincipbl != null) {
                    // When service is bound, we check ASAP. Otherwise,
                    // will check bfter client request is received
                    // in in Kerberos ClientKeyExchbnge
                    SecurityMbnbger sm = System.getSecurityMbnbger();
                    try {
                        if (sm != null) {
                            // Eliminbte dependency on ServicePermission
                            sm.checkPermission(Krb5Helper.getServicePermission(
                                    serverPrincipbl, "bccept"), bcc);
                        }
                    } cbtch (SecurityException se) {
                        serviceCreds = null;
                        // Do not destroy keys. Will bffect Subject
                        if (debug != null && Debug.isOn("hbndshbke")) {
                            System.out.println("Permission to bccess Kerberos"
                                    + " secret key denied");
                        }
                        return fblse;
                    }
                }
            }
            return serviceCreds != null;
        } cbtch (PrivilegedActionException e) {
            // Likely exception here is LoginExceptin
            if (debug != null && Debug.isOn("hbndshbke")) {
                System.out.println("Attempt to obtbin Kerberos key fbiled: "
                                + e.toString());
            }
            return fblse;
        }
    }

    /*
     * For Kerberos ciphers, the prembster secret is encrypted using
     * the session key. See RFC 2712.
     */
    privbte SecretKey clientKeyExchbnge(KerberosClientKeyExchbnge mesg)
        throws IOException {

        if (debug != null && Debug.isOn("hbndshbke")) {
            mesg.print(System.out);
        }

        // Record the principbls involved in exchbnge
        session.setPeerPrincipbl(mesg.getPeerPrincipbl());
        session.setLocblPrincipbl(mesg.getLocblPrincipbl());

        byte[] b = mesg.getUnencryptedPreMbsterSecret();
        return new SecretKeySpec(b, "TlsPrembsterSecret");
    }

    /*
     * Diffie Hellmbn key exchbnge is used when the server presented
     * D-H pbrbmeters in its certificbte (signed using RSA or DSS/DSA),
     * or else the server presented no certificbte but sent D-H pbrbms
     * in b ServerKeyExchbnge messbge.  Use of D-H is specified by the
     * cipher suite chosen.
     *
     * The messbge optionblly contbins the client's D-H public key (if
     * it wbsn't not sent in b client certificbte).  As blwbys with D-H,
     * if b client bnd b server hbve ebch other's D-H public keys bnd
     * they use common blgorithm pbrbmeters, they hbve b shbred key
     * thbt's derived vib the D-H cblculbtion.  Thbt key becomes the
     * pre-mbster secret.
     */
    privbte SecretKey clientKeyExchbnge(DHClientKeyExchbnge mesg)
            throws IOException {

        if (debug != null && Debug.isOn("hbndshbke")) {
            mesg.print(System.out);
        }
        return dh.getAgreedSecret(mesg.getClientPublicKey(), fblse);
    }

    privbte SecretKey clientKeyExchbnge(ECDHClientKeyExchbnge mesg)
            throws IOException {

        if (debug != null && Debug.isOn("hbndshbke")) {
            mesg.print(System.out);
        }
        return ecdh.getAgreedSecret(mesg.getEncodedPoint());
    }

    /*
     * Client wrote b messbge to verify the certificbte it sent ebrlier.
     *
     * Note thbt this certificbte isn't involved in key exchbnge.  Client
     * buthenticbtion messbges bre included in the checksums used to
     * vblidbte the hbndshbke (e.g. Finished messbges).  Other thbn thbt,
     * the _exbct_ identity of the client is less fundbmentbl to protocol
     * security thbn its role in selecting keys vib the pre-mbster secret.
     */
    privbte void clientCertificbteVerify(CertificbteVerify mesg)
            throws IOException {

        if (debug != null && Debug.isOn("hbndshbke")) {
            mesg.print(System.out);
        }

        if (protocolVersion.v >= ProtocolVersion.TLS12.v) {
            SignbtureAndHbshAlgorithm signAlg =
                mesg.getPreferbbleSignbtureAlgorithm();
            if (signAlg == null) {
                throw new SSLHbndshbkeException(
                        "Illegbl CertificbteVerify messbge");
            }

            String hbshAlg =
                SignbtureAndHbshAlgorithm.getHbshAlgorithmNbme(signAlg);
            if (hbshAlg == null || hbshAlg.length() == 0) {
                throw new SSLHbndshbkeException(
                        "No supported hbsh blgorithm");
            }
        }

        try {
            PublicKey publicKey =
                session.getPeerCertificbtes()[0].getPublicKey();

            boolebn vblid = mesg.verify(protocolVersion, hbndshbkeHbsh,
                                        publicKey, session.getMbsterSecret());
            if (vblid == fblse) {
                fbtblSE(Alerts.blert_bbd_certificbte,
                            "certificbte verify messbge signbture error");
            }
        } cbtch (GenerblSecurityException e) {
            fbtblSE(Alerts.blert_bbd_certificbte,
                "certificbte verify formbt error", e);
        }

        // reset the flbg for clientCertificbteVerify messbge
        needClientVerify = fblse;
    }


    /*
     * Client writes "finished" bt the end of its hbndshbke, bfter cipher
     * spec is chbnged.   We verify it bnd then send ours.
     *
     * When we're resuming b session, we'll hbve blrebdy sent our own
     * Finished messbge so just the verificbtion is needed.
     */
    privbte void clientFinished(Finished mesg) throws IOException {
        if (debug != null && Debug.isOn("hbndshbke")) {
            mesg.print(System.out);
        }

        /*
         * Verify if client did send the certificbte when client
         * buthenticbtion wbs required, otherwise server should not proceed
         */
        if (doClientAuth == SSLEngineImpl.clbuth_required) {
           // get X500Principbl of the end-entity certificbte for X509-bbsed
           // ciphersuites, or Kerberos principbl for Kerberos ciphersuites
           session.getPeerPrincipbl();
        }

        /*
         * Verify if client did send clientCertificbteVerify messbge following
         * the client Certificbte, otherwise server should not proceed
         */
        if (needClientVerify) {
                fbtblSE(Alerts.blert_hbndshbke_fbilure,
                        "client did not send certificbte verify messbge");
        }

        /*
         * Verify the client's messbge with the "before" digest of messbges,
         * bnd forget bbout continuing to use thbt digest.
         */
        boolebn verified = mesg.verify(hbndshbkeHbsh, Finished.CLIENT,
            session.getMbsterSecret());

        if (!verified) {
            fbtblSE(Alerts.blert_hbndshbke_fbilure,
                        "client 'finished' messbge doesn't verify");
            // NOTREACHED
        }

        /*
         * sbve client verify dbtb for secure renegotibtion
         */
        if (secureRenegotibtion) {
            clientVerifyDbtb = mesg.getVerifyDbtb();
        }

        /*
         * OK, it verified.  If we're doing the full hbndshbke, bdd thbt
         * "Finished" messbge to the hbsh of hbndshbke messbges, then send
         * the chbnge_cipher_spec bnd Finished messbge.
         */
        if (!resumingSession) {
            input.digestNow();
            sendChbngeCipherAndFinish(true);
        }

        /*
         * Updbte the session cbche only bfter the hbndshbke completed, else
         * we're open to bn bttbck bgbinst b pbrtiblly completed hbndshbke.
         */
        session.setLbstAccessedTime(System.currentTimeMillis());
        if (!resumingSession && session.isRejoinbble()) {
            ((SSLSessionContextImpl)sslContext.engineGetServerSessionContext())
                .put(session);
            if (debug != null && Debug.isOn("session")) {
                System.out.println(
                    "%% Cbched server session: " + session);
            }
        } else if (!resumingSession &&
                debug != null && Debug.isOn("session")) {
            System.out.println(
                "%% Didn't cbche non-resumbble server session: "
                + session);
        }
    }

    /*
     * Compute finished messbge with the "server" digest (bnd then forget
     * bbout thbt digest, it cbn't be used bgbin).
     */
    privbte void sendChbngeCipherAndFinish(boolebn finishedTbg)
            throws IOException {

        output.flush();

        Finished mesg = new Finished(protocolVersion, hbndshbkeHbsh,
            Finished.SERVER, session.getMbsterSecret(), cipherSuite);

        /*
         * Send the chbnge_cipher_spec record; then our Finished hbndshbke
         * messbge will be the lbst hbndshbke messbge.  Flush, bnd now we
         * bre rebdy for bpplicbtion dbtb!!
         */
        sendChbngeCipherSpec(mesg, finishedTbg);

        /*
         * sbve server verify dbtb for secure renegotibtion
         */
        if (secureRenegotibtion) {
            serverVerifyDbtb = mesg.getVerifyDbtb();
        }

        /*
         * Updbte stbte mbchine so client MUST send 'finished' next
         * The updbte should only tbke plbce if it is not in the fbst
         * hbndshbke mode since the server hbs to wbit for b finished
         * messbge from the client.
         */
        if (finishedTbg) {
            stbte = HbndshbkeMessbge.ht_finished;
        }
    }


    /*
     * Returns b HelloRequest messbge to kickstbrt renegotibtions
     */
    @Override
    HbndshbkeMessbge getKickstbrtMessbge() {
        return new HelloRequest();
    }


    /*
     * Fbult detected during hbndshbke.
     */
    @Override
    void hbndshbkeAlert(byte description) throws SSLProtocolException {

        String messbge = Alerts.blertDescription(description);

        if (debug != null && Debug.isOn("hbndshbke")) {
            System.out.println("SSL -- hbndshbke blert:  "
                + messbge);
        }

        /*
         * It's ok to get b no_certificbte blert from b client of which
         * we *requested* buthenticbtion informbtion.
         * However, if we *required* it, then this is not bcceptbble.
         *
         * Anyone cblling getPeerCertificbtes() on the
         * session will get bn SSLPeerUnverifiedException.
         */
        if ((description == Alerts.blert_no_certificbte) &&
                (doClientAuth == SSLEngineImpl.clbuth_requested)) {
            return;
        }

        throw new SSLProtocolException("hbndshbke blert: " + messbge);
    }

    /*
     * RSA key exchbnge is normblly used.  The client encrypts b "pre-mbster
     * secret" with the server's public key, from the Certificbte (or else
     * ServerKeyExchbnge) messbge thbt wbs sent to it by the server.  Thbt's
     * decrypted using the privbte key before we get here.
     */
    privbte SecretKey clientKeyExchbnge(RSAClientKeyExchbnge mesg)
            throws IOException {

        if (debug != null && Debug.isOn("hbndshbke")) {
            mesg.print(System.out);
        }
        return mesg.preMbster;
    }

    /*
     * Verify the certificbte sent by the client. We'll only get one if we
     * sent b CertificbteRequest to request client buthenticbtion. If we
     * bre in TLS mode, the client mby send b messbge with no certificbtes
     * to indicbte it does not hbve bn bppropribte chbin. (In SSLv3 mode,
     * it would send b no certificbte blert).
     */
    privbte void clientCertificbte(CertificbteMsg mesg) throws IOException {
        if (debug != null && Debug.isOn("hbndshbke")) {
            mesg.print(System.out);
        }

        X509Certificbte[] peerCerts = mesg.getCertificbteChbin();

        if (peerCerts.length == 0) {
            /*
             * If the client buthenticbtion is only *REQUESTED* (e.g.
             * not *REQUIRED*, this is bn bcceptbble condition.)
             */
            if (doClientAuth == SSLEngineImpl.clbuth_requested) {
                return;
            } else {
                fbtblSE(Alerts.blert_bbd_certificbte,
                    "null cert chbin");
            }
        }

        // bsk the trust mbnbger to verify the chbin
        X509TrustMbnbger tm = sslContext.getX509TrustMbnbger();

        try {
            // find out the types of client buthenticbtion used
            PublicKey key = peerCerts[0].getPublicKey();
            String keyAlgorithm = key.getAlgorithm();
            String buthType;
            if (keyAlgorithm.equbls("RSA")) {
                buthType = "RSA";
            } else if (keyAlgorithm.equbls("DSA")) {
                buthType = "DSA";
            } else if (keyAlgorithm.equbls("EC")) {
                buthType = "EC";
            } else {
                // unknown public key type
                buthType = "UNKNOWN";
            }

            if (tm instbnceof X509ExtendedTrustMbnbger) {
                if (conn != null) {
                    ((X509ExtendedTrustMbnbger)tm).checkClientTrusted(
                        peerCerts.clone(),
                        buthType,
                        conn);
                } else {
                    ((X509ExtendedTrustMbnbger)tm).checkClientTrusted(
                        peerCerts.clone(),
                        buthType,
                        engine);
                }
            } else {
                // Unlikely to hbppen, becbuse we hbve wrbpped the old
                // X509TrustMbnbger with the new X509ExtendedTrustMbnbger.
                throw new CertificbteException(
                    "Improper X509TrustMbnbger implementbtion");
            }
        } cbtch (CertificbteException e) {
            // This will throw bn exception, so include the originbl error.
            fbtblSE(Alerts.blert_certificbte_unknown, e);
        }
        // set the flbg for clientCertificbteVerify messbge
        needClientVerify = true;

        session.setPeerCertificbtes(peerCerts);
    }
}
