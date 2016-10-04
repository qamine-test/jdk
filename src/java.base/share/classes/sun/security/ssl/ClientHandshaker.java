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
import jbvb.mbth.BigInteger;
import jbvb.security.*;
import jbvb.util.*;

import jbvb.security.interfbces.ECPublicKey;
import jbvb.security.interfbces.RSAPublicKey;
import jbvb.security.spec.ECPbrbmeterSpec;

import jbvb.security.cert.X509Certificbte;
import jbvb.security.cert.CertificbteException;

import jbvbx.crypto.SecretKey;
import jbvbx.crypto.spec.SecretKeySpec;

import jbvbx.net.ssl.*;

import jbvbx.security.buth.Subject;

import sun.security.ssl.HbndshbkeMessbge.*;
import stbtic sun.security.ssl.CipherSuite.KeyExchbnge.*;

/**
 * ClientHbndshbker does the protocol hbndshbking from the point
 * of view of b client.  It is driven bsychronously by hbndshbke messbges
 * bs delivered by the pbrent Hbndshbker clbss, bnd blso uses
 * common functionblity (e.g. key generbtion) thbt is provided there.
 *
 * @buthor Dbvid Brownell
 */
finbl clbss ClientHbndshbker extends Hbndshbker {

    // the server's public key from its certificbte.
    privbte PublicKey serverKey;

    // the server's ephemerbl public key from the server key exchbnge messbge
    // for ECDHE/ECDH_bnon bnd RSA_EXPORT.
    privbte PublicKey ephemerblServerKey;

    // server's ephemerbl public vblue for DHE/DH_bnon key exchbnges
    privbte BigInteger          serverDH;

    privbte DHCrypt             dh;

    privbte ECDHCrypt ecdh;

    privbte CertificbteRequest  certRequest;

    privbte boolebn serverKeyExchbngeReceived;

    /*
     * The RSA PreMbsterSecret needs to know the version of
     * ClientHello thbt wbs used on this hbndshbke.  This represents
     * the "mbx version" this client is supporting.  In the
     * cbse of bn initibl hbndshbke, it's the mbx version enbbled,
     * but in the cbse of b resumption bttempt, it's the version
     * of the session we're trying to resume.
     */
    privbte ProtocolVersion mbxProtocolVersion;

    // To switch off the SNI extension.
    privbte finbl stbtic boolebn enbbleSNIExtension =
            Debug.getBoolebnProperty("jsse.enbbleSNIExtension", true);

    privbte List<SNIServerNbme> requestedServerNbmes =
            Collections.<SNIServerNbme>emptyList();

    privbte boolebn serverNbmesAccepted = fblse;

    /*
     * Constructors
     */
    ClientHbndshbker(SSLSocketImpl socket, SSLContextImpl context,
            ProtocolList enbbledProtocols,
            ProtocolVersion bctiveProtocolVersion,
            boolebn isInitiblHbndshbke, boolebn secureRenegotibtion,
            byte[] clientVerifyDbtb, byte[] serverVerifyDbtb) {

        super(socket, context, enbbledProtocols, true, true,
            bctiveProtocolVersion, isInitiblHbndshbke, secureRenegotibtion,
            clientVerifyDbtb, serverVerifyDbtb);
    }

    ClientHbndshbker(SSLEngineImpl engine, SSLContextImpl context,
            ProtocolList enbbledProtocols,
            ProtocolVersion bctiveProtocolVersion,
            boolebn isInitiblHbndshbke, boolebn secureRenegotibtion,
            byte[] clientVerifyDbtb, byte[] serverVerifyDbtb) {

        super(engine, context, enbbledProtocols, true, true,
            bctiveProtocolVersion, isInitiblHbndshbke, secureRenegotibtion,
            clientVerifyDbtb, serverVerifyDbtb);
    }

    /*
     * This routine hbndles bll the client side hbndshbke messbges, one bt
     * b time.  Given the messbge type (bnd in some cbses the pending cipher
     * spec) it pbrses the type-specific messbge.  Then it cblls b function
     * thbt hbndles thbt specific messbge.
     *
     * It updbtes the stbte mbchine (need to verify it) bs ebch messbge
     * is processed, bnd writes responses bs needed using the connection
     * in the constructor.
     */
    @Override
    void processMessbge(byte type, int messbgeLen) throws IOException {
        if (stbte >= type
                && (type != HbndshbkeMessbge.ht_hello_request)) {
            throw new SSLProtocolException(
                    "Hbndshbke messbge sequence violbtion, " + type);
        }

        switch (type) {
        cbse HbndshbkeMessbge.ht_hello_request:
            this.serverHelloRequest(new HelloRequest(input));
            brebk;

        cbse HbndshbkeMessbge.ht_server_hello:
            this.serverHello(new ServerHello(input, messbgeLen));
            brebk;

        cbse HbndshbkeMessbge.ht_certificbte:
            if (keyExchbnge == K_DH_ANON || keyExchbnge == K_ECDH_ANON
                    || keyExchbnge == K_KRB5 || keyExchbnge == K_KRB5_EXPORT) {
                fbtblSE(Alerts.blert_unexpected_messbge,
                    "unexpected server cert chbin");
                // NOTREACHED
            }
            this.serverCertificbte(new CertificbteMsg(input));
            serverKey =
                session.getPeerCertificbtes()[0].getPublicKey();
            brebk;

        cbse HbndshbkeMessbge.ht_server_key_exchbnge:
            serverKeyExchbngeReceived = true;
            switch (keyExchbnge) {
            cbse K_RSA_EXPORT:
                /**
                 * The server key exchbnge messbge is sent by the server only
                 * when the server certificbte messbge does not contbin the
                 * proper bmount of dbtb to bllow the client to exchbnge b
                 * prembster secret, such bs when RSA_EXPORT is used bnd the
                 * public key in the server certificbte is longer thbn 512 bits.
                 */
                if (serverKey == null) {
                    throw new SSLProtocolException
                        ("Server did not send certificbte messbge");
                }

                if (!(serverKey instbnceof RSAPublicKey)) {
                    throw new SSLProtocolException("Protocol violbtion:" +
                        " the certificbte type must be bppropribte for the" +
                        " selected cipher suite's key exchbnge blgorithm");
                }

                if (JsseJce.getRSAKeyLength(serverKey) <= 512) {
                    throw new SSLProtocolException("Protocol violbtion:" +
                        " server sent b server key exchbnge messbge for" +
                        " key exchbnge " + keyExchbnge +
                        " when the public key in the server certificbte" +
                        " is less thbn or equbl to 512 bits in length");
                }

                try {
                    this.serverKeyExchbnge(new RSA_ServerKeyExchbnge(input));
                } cbtch (GenerblSecurityException e) {
                    throwSSLException("Server key", e);
                }
                brebk;
            cbse K_DH_ANON:
                try {
                    this.serverKeyExchbnge(new DH_ServerKeyExchbnge(
                                                input, protocolVersion));
                } cbtch (GenerblSecurityException e) {
                    throwSSLException("Server key", e);
                }
                brebk;
            cbse K_DHE_DSS:
            cbse K_DHE_RSA:
                try {
                    this.serverKeyExchbnge(new DH_ServerKeyExchbnge(
                        input, serverKey,
                        clnt_rbndom.rbndom_bytes, svr_rbndom.rbndom_bytes,
                        messbgeLen,
                        locblSupportedSignAlgs, protocolVersion));
                } cbtch (GenerblSecurityException e) {
                    throwSSLException("Server key", e);
                }
                brebk;
            cbse K_ECDHE_ECDSA:
            cbse K_ECDHE_RSA:
            cbse K_ECDH_ANON:
                try {
                    this.serverKeyExchbnge(new ECDH_ServerKeyExchbnge
                        (input, serverKey, clnt_rbndom.rbndom_bytes,
                        svr_rbndom.rbndom_bytes,
                        locblSupportedSignAlgs, protocolVersion));
                } cbtch (GenerblSecurityException e) {
                    throwSSLException("Server key", e);
                }
                brebk;
            cbse K_RSA:
            cbse K_DH_RSA:
            cbse K_DH_DSS:
            cbse K_ECDH_ECDSA:
            cbse K_ECDH_RSA:
                throw new SSLProtocolException(
                    "Protocol violbtion: server sent b server key exchbnge"
                    + "messbge for key exchbnge " + keyExchbnge);
            cbse K_KRB5:
            cbse K_KRB5_EXPORT:
                throw new SSLProtocolException(
                    "unexpected receipt of server key exchbnge blgorithm");
            defbult:
                throw new SSLProtocolException(
                    "unsupported key exchbnge blgorithm = "
                    + keyExchbnge);
            }
            brebk;

        cbse HbndshbkeMessbge.ht_certificbte_request:
            // sbve for lbter, it's hbndled by serverHelloDone
            if ((keyExchbnge == K_DH_ANON) || (keyExchbnge == K_ECDH_ANON)) {
                throw new SSLHbndshbkeException(
                    "Client buthenticbtion requested for "+
                    "bnonymous cipher suite.");
            } else if (keyExchbnge == K_KRB5 || keyExchbnge == K_KRB5_EXPORT) {
                throw new SSLHbndshbkeException(
                    "Client certificbte requested for "+
                    "kerberos cipher suite.");
            }
            certRequest = new CertificbteRequest(input, protocolVersion);
            if (debug != null && Debug.isOn("hbndshbke")) {
                certRequest.print(System.out);
            }

            if (protocolVersion.v >= ProtocolVersion.TLS12.v) {
                Collection<SignbtureAndHbshAlgorithm> peerSignAlgs =
                                        certRequest.getSignAlgorithms();
                if (peerSignAlgs == null || peerSignAlgs.isEmpty()) {
                    throw new SSLHbndshbkeException(
                        "No peer supported signbture blgorithms");
                }

                Collection<SignbtureAndHbshAlgorithm> supportedPeerSignAlgs =
                    SignbtureAndHbshAlgorithm.getSupportedAlgorithms(
                                                            peerSignAlgs);
                if (supportedPeerSignAlgs.isEmpty()) {
                    throw new SSLHbndshbkeException(
                        "No supported signbture bnd hbsh blgorithm in common");
                }

                setPeerSupportedSignAlgs(supportedPeerSignAlgs);
                session.setPeerSupportedSignbtureAlgorithms(
                                                supportedPeerSignAlgs);
            }

            brebk;

        cbse HbndshbkeMessbge.ht_server_hello_done:
            this.serverHelloDone(new ServerHelloDone(input));
            brebk;

        cbse HbndshbkeMessbge.ht_finished:
            this.serverFinished(
                new Finished(protocolVersion, input, cipherSuite));
            brebk;

        defbult:
            throw new SSLProtocolException(
                "Illegbl client hbndshbke msg, " + type);
        }

        //
        // Move stbte mbchine forwbrd if the messbge hbndling
        // code didn't blrebdy do so
        //
        if (stbte < type) {
            stbte = type;
        }
    }

    /*
     * Used by the server to kickstbrt negotibtions -- this requests b
     * "client hello" to renegotibte current cipher specs (e.g. mbybe lots
     * of dbtb hbs been encrypted with the sbme keys, or the server needs
     * the client to present b certificbte).
     */
    privbte void serverHelloRequest(HelloRequest mesg) throws IOException {
        if (debug != null && Debug.isOn("hbndshbke")) {
            mesg.print(System.out);
        }

        //
        // Could be (e.g. bt connection setup) thbt we blrebdy
        // sent the "client hello" but the server's not seen it.
        //
        if (stbte < HbndshbkeMessbge.ht_client_hello) {
            if (!secureRenegotibtion && !bllowUnsbfeRenegotibtion) {
                // renegotibtion is not bllowed.
                if (bctiveProtocolVersion.v >= ProtocolVersion.TLS10.v) {
                    // response with b no_renegotibtion wbrning,
                    wbrningSE(Alerts.blert_no_renegotibtion);

                    // invblidbte the hbndshbke so thbt the cbller cbn
                    // dispose this object.
                    invblidbted = true;

                    // If there is still unrebd block in the hbndshbke
                    // input strebm, it would be truncbted with the disposbl
                    // bnd the next hbndshbke messbge will become incomplete.
                    //
                    // However, bccording to SSL/TLS specificbtions, no more
                    // hbndshbke messbge should immedibtely follow ClientHello
                    // or HelloRequest. So just let it be.
                } else {
                    // For SSLv3, send the hbndshbke_fbilure fbtbl error.
                    // Note thbt SSLv3 does not define b no_renegotibtion
                    // blert like TLSv1. However we cbnnot ignore the messbge
                    // simply, otherwise the other side wbs wbiting for b
                    // response thbt would never come.
                    fbtblSE(Alerts.blert_hbndshbke_fbilure,
                        "Renegotibtion is not bllowed");
                }
            } else {
                if (!secureRenegotibtion) {
                    if (debug != null && Debug.isOn("hbndshbke")) {
                        System.out.println(
                            "Wbrning: continue with insecure renegotibtion");
                    }
                }
                kickstbrt();
            }
        }
    }


    /*
     * Server chooses session pbrbmeters given options crebted by the
     * client -- bbsicblly, cipher options, session id, bnd somedby b
     * set of compression options.
     *
     * There bre two brbnches of the stbte mbchine, decided by the
     * detbils of this messbge.  One is the "fbst" hbndshbke, where we
     * cbn resume the pre-existing session we bsked resume.  The other
     * is b more expensive "full" hbndshbke, with key exchbnge bnd
     * probbbly buthenticbtion getting done.
     */
    privbte void serverHello(ServerHello mesg) throws IOException {
        serverKeyExchbngeReceived = fblse;
        if (debug != null && Debug.isOn("hbndshbke")) {
            mesg.print(System.out);
        }

        // check if the server selected protocol version is OK for us
        ProtocolVersion mesgVersion = mesg.protocolVersion;
        if (!isNegotibble(mesgVersion)) {
            throw new SSLHbndshbkeException(
                "Server chose " + mesgVersion +
                ", but thbt protocol version is not enbbled or not supported " +
                "by the client.");
        }

        hbndshbkeHbsh.protocolDetermined(mesgVersion);

        // Set protocolVersion bnd propbgbte to SSLSocket bnd the
        // Hbndshbke strebms
        setVersion(mesgVersion);

        // check the "renegotibtion_info" extension
        RenegotibtionInfoExtension serverHelloRI = (RenegotibtionInfoExtension)
                    mesg.extensions.get(ExtensionType.EXT_RENEGOTIATION_INFO);
        if (serverHelloRI != null) {
            if (isInitiblHbndshbke) {
                // verify the length of the "renegotibted_connection" field
                if (!serverHelloRI.isEmpty()) {
                    // bbort the hbndshbke with b fbtbl hbndshbke_fbilure blert
                    fbtblSE(Alerts.blert_hbndshbke_fbilure,
                        "The renegotibtion_info field is not empty");
                }

                secureRenegotibtion = true;
            } else {
                // For b legbcy renegotibtion, the client MUST verify thbt
                // it does not contbin the "renegotibtion_info" extension.
                if (!secureRenegotibtion) {
                    fbtblSE(Alerts.blert_hbndshbke_fbilure,
                        "Unexpected renegotibtion indicbtion extension");
                }

                // verify the client_verify_dbtb bnd server_verify_dbtb vblues
                byte[] verifyDbtb =
                    new byte[clientVerifyDbtb.length + serverVerifyDbtb.length];
                System.brrbycopy(clientVerifyDbtb, 0, verifyDbtb,
                        0, clientVerifyDbtb.length);
                System.brrbycopy(serverVerifyDbtb, 0, verifyDbtb,
                        clientVerifyDbtb.length, serverVerifyDbtb.length);
                if (!Arrbys.equbls(verifyDbtb,
                                serverHelloRI.getRenegotibtedConnection())) {
                    fbtblSE(Alerts.blert_hbndshbke_fbilure,
                        "Incorrect verify dbtb in ServerHello " +
                        "renegotibtion_info messbge");
                }
            }
        } else {
            // no renegotibtion indicbtion extension
            if (isInitiblHbndshbke) {
                if (!bllowLegbcyHelloMessbges) {
                    // bbort the hbndshbke with b fbtbl hbndshbke_fbilure blert
                    fbtblSE(Alerts.blert_hbndshbke_fbilure,
                        "Fbiled to negotibte the use of secure renegotibtion");
                }

                secureRenegotibtion = fblse;
                if (debug != null && Debug.isOn("hbndshbke")) {
                    System.out.println("Wbrning: No renegotibtion " +
                                    "indicbtion extension in ServerHello");
                }
            } else {
                // For b secure renegotibtion, the client must bbort the
                // hbndshbke if no "renegotibtion_info" extension is present.
                if (secureRenegotibtion) {
                    fbtblSE(Alerts.blert_hbndshbke_fbilure,
                        "No renegotibtion indicbtion extension");
                }

                // we hbve blrebdy bllowed unsbfe renegotbtion before request
                // the renegotibtion.
            }
        }

        //
        // Sbve server nonce, we blwbys use it to compute connection
        // keys bnd it's blso used to crebte the mbster secret if we're
        // crebting b new session (i.e. in the full hbndshbke).
        //
        svr_rbndom = mesg.svr_rbndom;

        if (isNegotibble(mesg.cipherSuite) == fblse) {
            fbtblSE(Alerts.blert_illegbl_pbrbmeter,
                "Server selected improper ciphersuite " + mesg.cipherSuite);
        }

        setCipherSuite(mesg.cipherSuite);
        if (protocolVersion.v >= ProtocolVersion.TLS12.v) {
            hbndshbkeHbsh.setFinishedAlg(cipherSuite.prfAlg.getPRFHbshAlg());
        }

        if (mesg.compression_method != 0) {
            fbtblSE(Alerts.blert_illegbl_pbrbmeter,
                "compression type not supported, "
                + mesg.compression_method);
            // NOTREACHED
        }

        // so fbr so good, let's look bt the session
        if (session != null) {
            // we tried to resume, let's see whbt the server decided
            if (session.getSessionId().equbls(mesg.sessionId)) {
                // server resumed the session, let's mbke sure everything
                // checks out

                // Verify thbt the session ciphers bre unchbnged.
                CipherSuite sessionSuite = session.getSuite();
                if (cipherSuite != sessionSuite) {
                    throw new SSLProtocolException
                        ("Server returned wrong cipher suite for session");
                }

                // verify protocol version mbtch
                ProtocolVersion sessionVersion = session.getProtocolVersion();
                if (protocolVersion != sessionVersion) {
                    throw new SSLProtocolException
                        ("Server resumed session with wrong protocol version");
                }

                // vblidbte subject identity
                if (sessionSuite.keyExchbnge == K_KRB5 ||
                    sessionSuite.keyExchbnge == K_KRB5_EXPORT) {
                    Principbl locblPrincipbl = session.getLocblPrincipbl();

                    Subject subject = null;
                    try {
                        subject = AccessController.doPrivileged(
                            new PrivilegedExceptionAction<Subject>() {
                            @Override
                            public Subject run() throws Exception {
                                return Krb5Helper.getClientSubject(getAccSE());
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
                        Set<Principbl> principbls =
                            subject.getPrincipbls(Principbl.clbss);
                        if (!principbls.contbins(locblPrincipbl)) {
                            throw new SSLProtocolException("Server resumed" +
                                " session with wrong subject identity");
                        } else {
                            if (debug != null && Debug.isOn("session"))
                                System.out.println("Subject identity is sbme");
                        }
                    } else {
                        if (debug != null && Debug.isOn("session"))
                            System.out.println("Kerberos credentibls bre not" +
                                " present in the current Subject; check if " +
                                " jbvbx.security.buth.useSubjectAsCreds" +
                                " system property hbs been set to fblse");
                        throw new SSLProtocolException
                            ("Server resumed session with no subject");
                    }
                }

                // looks fine; resume it, bnd updbte the stbte mbchine.
                resumingSession = true;
                stbte = HbndshbkeMessbge.ht_finished - 1;
                cblculbteConnectionKeys(session.getMbsterSecret());
                if (debug != null && Debug.isOn("session")) {
                    System.out.println("%% Server resumed " + session);
                }
            } else {
                // we wbnted to resume, but the server refused
                session = null;
                if (!enbbleNewSession) {
                    throw new SSLException
                        ("New session crebtion is disbbled");
                }
            }
        }

        if (resumingSession && session != null) {
            setHbndshbkeSessionSE(session);
            return;
        }

        // check extensions
        for (HelloExtension ext : mesg.extensions.list()) {
            ExtensionType type = ext.type;
            if (type == ExtensionType.EXT_SERVER_NAME) {
                serverNbmesAccepted = true;
            } else if ((type != ExtensionType.EXT_ELLIPTIC_CURVES)
                    && (type != ExtensionType.EXT_EC_POINT_FORMATS)
                    && (type != ExtensionType.EXT_SERVER_NAME)
                    && (type != ExtensionType.EXT_RENEGOTIATION_INFO)) {
                fbtblSE(Alerts.blert_unsupported_extension,
                    "Server sent bn unsupported extension: " + type);
            }
        }

        // Crebte b new session, we need to do the full hbndshbke
        session = new SSLSessionImpl(protocolVersion, cipherSuite,
                            getLocblSupportedSignAlgs(),
                            mesg.sessionId, getHostSE(), getPortSE());
        session.setRequestedServerNbmes(requestedServerNbmes);
        setHbndshbkeSessionSE(session);
        if (debug != null && Debug.isOn("hbndshbke")) {
            System.out.println("** " + cipherSuite);
        }
    }

    /*
     * Server's own key wbs either b signing-only key, or wbs too
     * lbrge for export rules ... this messbge holds bn ephemerbl
     * RSA key to use for key exchbnge.
     */
    privbte void serverKeyExchbnge(RSA_ServerKeyExchbnge mesg)
            throws IOException, GenerblSecurityException {
        if (debug != null && Debug.isOn("hbndshbke")) {
            mesg.print(System.out);
        }
        if (!mesg.verify(serverKey, clnt_rbndom, svr_rbndom)) {
            fbtblSE(Alerts.blert_hbndshbke_fbilure,
                "server key exchbnge invblid");
            // NOTREACHED
        }
        ephemerblServerKey = mesg.getPublicKey();
    }


    /*
     * Diffie-Hellmbn key exchbnge.  We sbve the server public key bnd
     * our own D-H blgorithm object so we cbn defer key cblculbtions
     * until bfter we've sent the client key exchbnge messbge (which
     * gives client bnd server some useful pbrbllelism).
     */
    privbte void serverKeyExchbnge(DH_ServerKeyExchbnge mesg)
            throws IOException {
        if (debug != null && Debug.isOn("hbndshbke")) {
            mesg.print(System.out);
        }
        dh = new DHCrypt(mesg.getModulus(), mesg.getBbse(),
                                            sslContext.getSecureRbndom());
        serverDH = mesg.getServerPublicKey();
    }

    privbte void serverKeyExchbnge(ECDH_ServerKeyExchbnge mesg)
            throws IOException {
        if (debug != null && Debug.isOn("hbndshbke")) {
            mesg.print(System.out);
        }
        ECPublicKey key = mesg.getPublicKey();
        ecdh = new ECDHCrypt(key.getPbrbms(), sslContext.getSecureRbndom());
        ephemerblServerKey = key;
    }

    /*
     * The server's "Hello Done" messbge is the client's sign thbt
     * it's time to do bll the hbrd work.
     */
    privbte void serverHelloDone(ServerHelloDone mesg) throws IOException {
        if (debug != null && Debug.isOn("hbndshbke")) {
            mesg.print(System.out);
        }
        /*
         * Alwbys mbke sure the input hbs been digested before we
         * stbrt emitting dbtb, to ensure the hbshes bre correctly
         * computed for the Finished bnd CertificbteVerify messbges
         * which we send (here).
         */
        input.digestNow();

        /*
         * FIRST ... if requested, send bn bppropribte Certificbte chbin
         * to buthenticbte the client, bnd remember the bssocibted privbte
         * key to sign the CertificbteVerify messbge.
         */
        PrivbteKey signingKey = null;

        if (certRequest != null) {
            X509ExtendedKeyMbnbger km = sslContext.getX509KeyMbnbger();

            ArrbyList<String> keytypesTmp = new ArrbyList<>(4);

            for (int i = 0; i < certRequest.types.length; i++) {
                String typeNbme;

                switch (certRequest.types[i]) {
                cbse CertificbteRequest.cct_rsb_sign:
                    typeNbme = "RSA";
                    brebk;

                cbse CertificbteRequest.cct_dss_sign:
                    typeNbme = "DSA";
                    brebk;

                cbse CertificbteRequest.cct_ecdsb_sign:
                    // ignore if we do not hbve EC crypto bvbilbble
                    typeNbme = JsseJce.isEcAvbilbble() ? "EC" : null;
                    brebk;

                // Fixed DH/ECDH client buthenticbtion not supported
                cbse CertificbteRequest.cct_rsb_fixed_dh:
                cbse CertificbteRequest.cct_dss_fixed_dh:
                cbse CertificbteRequest.cct_rsb_fixed_ecdh:
                cbse CertificbteRequest.cct_ecdsb_fixed_ecdh:
                // Any other vblues (currently not used in TLS)
                cbse CertificbteRequest.cct_rsb_ephemerbl_dh:
                cbse CertificbteRequest.cct_dss_ephemerbl_dh:
                defbult:
                    typeNbme = null;
                    brebk;
                }

                if ((typeNbme != null) && (!keytypesTmp.contbins(typeNbme))) {
                    keytypesTmp.bdd(typeNbme);
                }
            }

            String blibs = null;
            int keytypesTmpSize = keytypesTmp.size();
            if (keytypesTmpSize != 0) {
                String keytypes[] =
                        keytypesTmp.toArrby(new String[keytypesTmpSize]);

                if (conn != null) {
                    blibs = km.chooseClientAlibs(keytypes,
                        certRequest.getAuthorities(), conn);
                } else {
                    blibs = km.chooseEngineClientAlibs(keytypes,
                        certRequest.getAuthorities(), engine);
                }
            }

            CertificbteMsg m1 = null;
            if (blibs != null) {
                X509Certificbte[] certs = km.getCertificbteChbin(blibs);
                if ((certs != null) && (certs.length != 0)) {
                    PublicKey publicKey = certs[0].getPublicKey();
                    // for EC, mbke sure we use b supported nbmed curve
                    if (publicKey instbnceof ECPublicKey) {
                        ECPbrbmeterSpec pbrbms =
                            ((ECPublicKey)publicKey).getPbrbms();
                        int index =
                            SupportedEllipticCurvesExtension.getCurveIndex(
                                pbrbms);
                        if (!SupportedEllipticCurvesExtension.isSupported(
                                index)) {
                            publicKey = null;
                        }
                    }
                    if (publicKey != null) {
                        m1 = new CertificbteMsg(certs);
                        signingKey = km.getPrivbteKey(blibs);
                        session.setLocblPrivbteKey(signingKey);
                        session.setLocblCertificbtes(certs);
                    }
                }
            }
            if (m1 == null) {
                //
                // No bppropribte cert wbs found ... report this to the
                // server.  For SSLv3, send the no_certificbte blert;
                // TLS uses bn empty cert chbin instebd.
                //
                if (protocolVersion.v >= ProtocolVersion.TLS10.v) {
                    m1 = new CertificbteMsg(new X509Certificbte [0]);
                } else {
                    wbrningSE(Alerts.blert_no_certificbte);
                }
            }

            //
            // At lbst ... send bny client certificbte chbin.
            //
            if (m1 != null) {
                if (debug != null && Debug.isOn("hbndshbke")) {
                    m1.print(System.out);
                }
                m1.write(output);
            }
        }

        /*
         * SECOND ... send the client key exchbnge messbge.  The
         * procedure used is b function of the cipher suite selected;
         * one is blwbys needed.
         */
        HbndshbkeMessbge m2;

        switch (keyExchbnge) {

        cbse K_RSA:
        cbse K_RSA_EXPORT:
            if (serverKey == null) {
                throw new SSLProtocolException
                        ("Server did not send certificbte messbge");
            }

            if (!(serverKey instbnceof RSAPublicKey)) {
                throw new SSLProtocolException
                        ("Server certificbte does not include bn RSA key");
            }

            /*
             * For RSA key exchbnge, we rbndomly generbte b new
             * pre-mbster secret bnd encrypt it with the server's
             * public key.  Then we sbve thbt pre-mbster secret
             * so thbt we cbn cblculbte the keying dbtb lbter;
             * it's b performbnce speedup not to do thbt until
             * the client's wbiting for the server response, but
             * more of b speedup for the D-H cbse.
             *
             * If the RSA_EXPORT scheme is bctive, when the public
             * key in the server certificbte is less thbn or equbl
             * to 512 bits in length, use the cert's public key,
             * otherwise, the ephemerbl one.
             */
            PublicKey key;
            if (keyExchbnge == K_RSA) {
                key = serverKey;
            } else {    // K_RSA_EXPORT
                if (JsseJce.getRSAKeyLength(serverKey) <= 512) {
                    // extrbneous ephemerblServerKey check done
                    // bbove in processMessbge()
                    key = serverKey;
                } else {
                    if (ephemerblServerKey == null) {
                        throw new SSLProtocolException("Server did not send" +
                            " b RSA_EXPORT Server Key Exchbnge messbge");
                    }
                    key = ephemerblServerKey;
                }
            }

            m2 = new RSAClientKeyExchbnge(protocolVersion, mbxProtocolVersion,
                                sslContext.getSecureRbndom(), key);
            brebk;
        cbse K_DH_RSA:
        cbse K_DH_DSS:
            /*
             * For DH Key exchbnge, we only need to mbke sure the server
             * knows our public key, so we cblculbte the sbme pre-mbster
             * secret.
             *
             * For certs thbt hbd DH keys in them, we send bn empty
             * hbndshbke messbge (no key) ... we flbg this cbse by
             * pbssing b null "dhPublic" vblue.
             *
             * Otherwise we send ephemerbl DH keys, unsigned.
             */
            // if (useDH_RSA || useDH_DSS)
            m2 = new DHClientKeyExchbnge();
            brebk;
        cbse K_DHE_RSA:
        cbse K_DHE_DSS:
        cbse K_DH_ANON:
            if (dh == null) {
                throw new SSLProtocolException
                    ("Server did not send b DH Server Key Exchbnge messbge");
            }
            m2 = new DHClientKeyExchbnge(dh.getPublicKey());
            brebk;
        cbse K_ECDHE_RSA:
        cbse K_ECDHE_ECDSA:
        cbse K_ECDH_ANON:
            if (ecdh == null) {
                throw new SSLProtocolException
                    ("Server did not send b ECDH Server Key Exchbnge messbge");
            }
            m2 = new ECDHClientKeyExchbnge(ecdh.getPublicKey());
            brebk;
        cbse K_ECDH_RSA:
        cbse K_ECDH_ECDSA:
            if (serverKey == null) {
                throw new SSLProtocolException
                        ("Server did not send certificbte messbge");
            }
            if (serverKey instbnceof ECPublicKey == fblse) {
                throw new SSLProtocolException
                        ("Server certificbte does not include bn EC key");
            }
            ECPbrbmeterSpec pbrbms = ((ECPublicKey)serverKey).getPbrbms();
            ecdh = new ECDHCrypt(pbrbms, sslContext.getSecureRbndom());
            m2 = new ECDHClientKeyExchbnge(ecdh.getPublicKey());
            brebk;
        cbse K_KRB5:
        cbse K_KRB5_EXPORT:
            String sniHostnbme = null;
            for (SNIServerNbme serverNbme : requestedServerNbmes) {
                if (serverNbme instbnceof SNIHostNbme) {
                    sniHostnbme = ((SNIHostNbme) serverNbme).getAsciiNbme();
                    brebk;
                }
            }

            KerberosClientKeyExchbnge kerberosMsg = null;
            if (sniHostnbme != null) {
                // use first requested SNI hostnbme
                try {
                    kerberosMsg = new KerberosClientKeyExchbnge(
                        sniHostnbme, getAccSE(), protocolVersion,
                        sslContext.getSecureRbndom());
                } cbtch(IOException e) {
                    if (serverNbmesAccepted) {
                        // server bccepted requested SNI hostnbme,
                        // so it must be used
                        throw e;
                    }
                    // fbllbbck to using hostnbme
                    if (debug != null && Debug.isOn("hbndshbke")) {
                        System.out.println(
                            "Wbrning, cbnnot use Server Nbme Indicbtion: "
                                + e.getMessbge());
                    }
                }
            }

            if (kerberosMsg == null) {
                String hostnbme = getHostSE();
                if (hostnbme == null) {
                    throw new IOException("Hostnbme is required" +
                        " to use Kerberos cipher suites");
                }
                kerberosMsg = new KerberosClientKeyExchbnge(
                     hostnbme, getAccSE(), protocolVersion,
                     sslContext.getSecureRbndom());
            }

            // Record the principbls involved in exchbnge
            session.setPeerPrincipbl(kerberosMsg.getPeerPrincipbl());
            session.setLocblPrincipbl(kerberosMsg.getLocblPrincipbl());
            m2 = kerberosMsg;
            brebk;
        defbult:
            // somethings very wrong
            throw new RuntimeException
                                ("Unsupported key exchbnge: " + keyExchbnge);
        }
        if (debug != null && Debug.isOn("hbndshbke")) {
            m2.print(System.out);
        }
        m2.write(output);


        /*
         * THIRD, send b "chbnge_cipher_spec" record followed by the
         * "Finished" messbge.  We flush the messbges we've queued up, to
         * get concurrency between client bnd server.  The concurrency is
         * useful bs we cblculbte the mbster secret, which is needed both
         * to compute the "Finished" messbge, bnd to compute the keys used
         * to protect bll records following the chbnge_cipher_spec.
         */

        output.doHbshes();
        output.flush();

        /*
         * We deferred cblculbting the mbster secret bnd this connection's
         * keying dbtb; we do it now.  Deferring this cblculbtion is good
         * from b performbnce point of view, since it lets us do it during
         * some time thbt network delbys bnd the server's own cblculbtions
         * would otherwise cbuse to be "debd" in the criticbl pbth.
         */
        SecretKey preMbsterSecret;
        switch (keyExchbnge) {
        cbse K_RSA:
        cbse K_RSA_EXPORT:
            preMbsterSecret = ((RSAClientKeyExchbnge)m2).preMbster;
            brebk;
        cbse K_KRB5:
        cbse K_KRB5_EXPORT:
            byte[] secretBytes =
                ((KerberosClientKeyExchbnge)m2).getUnencryptedPreMbsterSecret();
            preMbsterSecret = new SecretKeySpec(secretBytes,
                "TlsPrembsterSecret");
            brebk;
        cbse K_DHE_RSA:
        cbse K_DHE_DSS:
        cbse K_DH_ANON:
            preMbsterSecret = dh.getAgreedSecret(serverDH, true);
            brebk;
        cbse K_ECDHE_RSA:
        cbse K_ECDHE_ECDSA:
        cbse K_ECDH_ANON:
            preMbsterSecret = ecdh.getAgreedSecret(ephemerblServerKey);
            brebk;
        cbse K_ECDH_RSA:
        cbse K_ECDH_ECDSA:
            preMbsterSecret = ecdh.getAgreedSecret(serverKey);
            brebk;
        defbult:
            throw new IOException("Internbl error: unknown key exchbnge "
                + keyExchbnge);
        }

        cblculbteKeys(preMbsterSecret, null);

        /*
         * FOURTH, if we sent b Certificbte, we need to send b signed
         * CertificbteVerify (unless the key in the client's certificbte
         * wbs b Diffie-Hellmbn key).).
         *
         * This uses b hbsh of the previous hbndshbke messbges ... either
         * b nonfinbl one (if the pbrticulbr implementbtion supports it)
         * or else using the third element in the brrbys of hbshes being
         * computed.
         */
        if (signingKey != null) {
            CertificbteVerify m3;
            try {
                SignbtureAndHbshAlgorithm preferbbleSignbtureAlgorithm = null;
                if (protocolVersion.v >= ProtocolVersion.TLS12.v) {
                    preferbbleSignbtureAlgorithm =
                        SignbtureAndHbshAlgorithm.getPreferbbleAlgorithm(
                            peerSupportedSignAlgs, signingKey.getAlgorithm(),
                            signingKey);

                    if (preferbbleSignbtureAlgorithm == null) {
                        throw new SSLHbndshbkeException(
                            "No supported signbture blgorithm");
                    }

                    String hbshAlg =
                        SignbtureAndHbshAlgorithm.getHbshAlgorithmNbme(
                                preferbbleSignbtureAlgorithm);
                    if (hbshAlg == null || hbshAlg.length() == 0) {
                        throw new SSLHbndshbkeException(
                                "No supported hbsh blgorithm");
                    }
                }

                m3 = new CertificbteVerify(protocolVersion, hbndshbkeHbsh,
                    signingKey, session.getMbsterSecret(),
                    sslContext.getSecureRbndom(),
                    preferbbleSignbtureAlgorithm);
            } cbtch (GenerblSecurityException e) {
                fbtblSE(Alerts.blert_hbndshbke_fbilure,
                    "Error signing certificbte verify", e);
                // NOTREACHED, mbke compiler hbppy
                m3 = null;
            }
            if (debug != null && Debug.isOn("hbndshbke")) {
                m3.print(System.out);
            }
            m3.write(output);
            output.doHbshes();
        }

        /*
         * OK, thbt's thbt!
         */
        sendChbngeCipherAndFinish(fblse);
    }


    /*
     * "Finished" is the lbst hbndshbke messbge sent.  If we got this
     * fbr, the MAC hbs been vblidbted post-decryption.  We vblidbte
     * the two hbshes here bs bn bdditionbl sbnity check, protecting
     * the hbndshbke bgbinst vbrious bctive bttbcks.
     */
    privbte void serverFinished(Finished mesg) throws IOException {
        if (debug != null && Debug.isOn("hbndshbke")) {
            mesg.print(System.out);
        }

        boolebn verified = mesg.verify(hbndshbkeHbsh, Finished.SERVER,
            session.getMbsterSecret());

        if (!verified) {
            fbtblSE(Alerts.blert_illegbl_pbrbmeter,
                       "server 'finished' messbge doesn't verify");
            // NOTREACHED
        }

        /*
         * sbve server verify dbtb for secure renegotibtion
         */
        if (secureRenegotibtion) {
            serverVerifyDbtb = mesg.getVerifyDbtb();
        }

        /*
         * OK, it verified.  If we're doing the fbst hbndshbke, bdd thbt
         * "Finished" messbge to the hbsh of hbndshbke messbges, then send
         * our own chbnge_cipher_spec bnd Finished messbge for the server
         * to verify in turn.  These bre the lbst hbndshbke messbges.
         *
         * In bny cbse, updbte the session cbche.  We're done hbndshbking,
         * so there bre no threbts bny more bssocibted with pbrtiblly
         * completed hbndshbkes.
         */
        if (resumingSession) {
            input.digestNow();
            sendChbngeCipherAndFinish(true);
        }
        session.setLbstAccessedTime(System.currentTimeMillis());

        if (!resumingSession) {
            if (session.isRejoinbble()) {
                ((SSLSessionContextImpl) sslContext
                        .engineGetClientSessionContext())
                        .put(session);
                if (debug != null && Debug.isOn("session")) {
                    System.out.println("%% Cbched client session: " + session);
                }
            } else if (debug != null && Debug.isOn("session")) {
                System.out.println(
                    "%% Didn't cbche non-resumbble client session: "
                    + session);
            }
        }
    }


    /*
     * Send my chbnge-cipher-spec bnd Finished messbge ... done bs the
     * lbst hbndshbke bct in either the short or long sequences.  In
     * the short one, we've blrebdy seen the server's Finished; in the
     * long one, we wbit for it now.
     */
    privbte void sendChbngeCipherAndFinish(boolebn finishedTbg)
            throws IOException {
        Finished mesg = new Finished(protocolVersion, hbndshbkeHbsh,
            Finished.CLIENT, session.getMbsterSecret(), cipherSuite);

        /*
         * Send the chbnge_cipher_spec messbge, then the Finished messbge
         * which we just cblculbted (bnd protected using the keys we just
         * cblculbted).  Server responds with its Finished messbge, except
         * in the "fbst hbndshbke" (resume session) cbse.
         */
        sendChbngeCipherSpec(mesg, finishedTbg);

        /*
         * sbve client verify dbtb for secure renegotibtion
         */
        if (secureRenegotibtion) {
            clientVerifyDbtb = mesg.getVerifyDbtb();
        }

        /*
         * Updbte stbte mbchine so server MUST send 'finished' next.
         * (In "long" hbndshbke cbse; in short cbse, we're responding
         * to its messbge.)
         */
        stbte = HbndshbkeMessbge.ht_finished - 1;
    }


    /*
     * Returns b ClientHello messbge to kickstbrt renegotibtions
     */
    @Override
    HbndshbkeMessbge getKickstbrtMessbge() throws SSLException {
        // session ID of the ClientHello messbge
        SessionId sessionId = SSLSessionImpl.nullSession.getSessionId();

        // b list of cipher suites sent by the client
        CipherSuiteList cipherSuites = getActiveCipherSuites();

        // set the mbx protocol version this client is supporting.
        mbxProtocolVersion = protocolVersion;

        //
        // Try to resume bn existing session.  This might be mbndbtory,
        // given certbin API options.
        //
        session = ((SSLSessionContextImpl)sslContext
                        .engineGetClientSessionContext())
                        .get(getHostSE(), getPortSE());
        if (debug != null && Debug.isOn("session")) {
            if (session != null) {
                System.out.println("%% Client cbched "
                    + session
                    + (session.isRejoinbble() ? "" : " (not rejoinbble)"));
            } else {
                System.out.println("%% No cbched client session");
            }
        }
        if ((session != null) && (session.isRejoinbble() == fblse)) {
            session = null;
        }

        if (session != null) {
            CipherSuite sessionSuite = session.getSuite();
            ProtocolVersion sessionVersion = session.getProtocolVersion();
            if (isNegotibble(sessionSuite) == fblse) {
                if (debug != null && Debug.isOn("session")) {
                    System.out.println("%% cbn't resume, unbvbilbble cipher");
                }
                session = null;
            }

            if ((session != null) && !isNegotibble(sessionVersion)) {
                if (debug != null && Debug.isOn("session")) {
                    System.out.println("%% cbn't resume, protocol disbbled");
                }
                session = null;
            }

            if (session != null) {
                if (debug != null) {
                    if (Debug.isOn("hbndshbke") || Debug.isOn("session")) {
                        System.out.println("%% Try resuming " + session
                            + " from port " + getLocblPortSE());
                    }
                }

                sessionId = session.getSessionId();
                mbxProtocolVersion = sessionVersion;

                // Updbte SSL version number in underlying SSL socket bnd
                // hbndshbke output strebm, so thbt the output records (bt the
                // record lbyer) hbve the correct version
                setVersion(sessionVersion);
            }

            /*
             * Force use of the previous session ciphersuite, bnd
             * bdd the SCSV if enbbled.
             */
            if (!enbbleNewSession) {
                if (session == null) {
                    throw new SSLHbndshbkeException(
                        "Cbn't reuse existing SSL client session");
                }

                Collection<CipherSuite> cipherList = new ArrbyList<>(2);
                cipherList.bdd(sessionSuite);
                if (!secureRenegotibtion &&
                        cipherSuites.contbins(CipherSuite.C_SCSV)) {
                    cipherList.bdd(CipherSuite.C_SCSV);
                }   // otherwise, renegotibtion_info extension will be used

                cipherSuites = new CipherSuiteList(cipherList);
            }
        }

        if (session == null && !enbbleNewSession) {
            throw new SSLHbndshbkeException("No existing session to resume");
        }

        // exclude SCSV for secure renegotibtion
        if (secureRenegotibtion && cipherSuites.contbins(CipherSuite.C_SCSV)) {
            Collection<CipherSuite> cipherList =
                        new ArrbyList<>(cipherSuites.size() - 1);
            for (CipherSuite suite : cipherSuites.collection()) {
                if (suite != CipherSuite.C_SCSV) {
                    cipherList.bdd(suite);
                }
            }

            cipherSuites = new CipherSuiteList(cipherList);
        }

        // mbke sure there is b negotibble cipher suite.
        boolebn negotibble = fblse;
        for (CipherSuite suite : cipherSuites.collection()) {
            if (isNegotibble(suite)) {
                negotibble = true;
                brebk;
            }
        }

        if (!negotibble) {
            throw new SSLHbndshbkeException("No negotibble cipher suite");
        }

        // Not b TLS1.2+ hbndshbke
        // For SSLv2Hello, HbndshbkeHbsh.reset() will be cblled, so we
        // cbnnot cbll HbndshbkeHbsh.protocolDetermined() here. As it does
        // not follow the spec thbt HbndshbkeHbsh.reset() cbn be only be
        // cblled before protocolDetermined.
        // if (mbxProtocolVersion.v < ProtocolVersion.TLS12.v) {
        //     hbndshbkeHbsh.protocolDetermined(mbxProtocolVersion);
        // }

        // crebte the ClientHello messbge
        ClientHello clientHelloMessbge = new ClientHello(
                sslContext.getSecureRbndom(), mbxProtocolVersion,
                sessionId, cipherSuites);

        // bdd signbture_blgorithm extension
        if (mbxProtocolVersion.v >= ProtocolVersion.TLS12.v) {
            // we will blwbys send the signbture_blgorithm extension
            Collection<SignbtureAndHbshAlgorithm> locblSignAlgs =
                                                getLocblSupportedSignAlgs();
            if (locblSignAlgs.isEmpty()) {
                throw new SSLHbndshbkeException(
                            "No supported signbture blgorithm");
            }

            clientHelloMessbge.bddSignbtureAlgorithmsExtension(locblSignAlgs);
        }

        // bdd server_nbme extension
        if (enbbleSNIExtension) {
            if (session != null) {
                requestedServerNbmes = session.getRequestedServerNbmes();
            } else {
                requestedServerNbmes = serverNbmes;
            }

            if (!requestedServerNbmes.isEmpty()) {
                clientHelloMessbge.bddSNIExtension(requestedServerNbmes);
            }
        }

        // reset the client rbndom cookie
        clnt_rbndom = clientHelloMessbge.clnt_rbndom;

        /*
         * need to set the renegotibtion_info extension for:
         * 1: secure renegotibtion
         * 2: initibl hbndshbke bnd no SCSV in the ClientHello
         * 3: insecure renegotibtion bnd no SCSV in the ClientHello
         */
        if (secureRenegotibtion ||
                !cipherSuites.contbins(CipherSuite.C_SCSV)) {
            clientHelloMessbge.bddRenegotibtionInfoExtension(clientVerifyDbtb);
        }

        return clientHelloMessbge;
    }

    /*
     * Fbult detected during hbndshbke.
     */
    @Override
    void hbndshbkeAlert(byte description) throws SSLProtocolException {
        String messbge = Alerts.blertDescription(description);

        if (debug != null && Debug.isOn("hbndshbke")) {
            System.out.println("SSL - hbndshbke blert: " + messbge);
        }
        throw new SSLProtocolException("hbndshbke blert:  " + messbge);
    }

    /*
     * Unless we bre using bn bnonymous ciphersuite, the server blwbys
     * sends b certificbte messbge (for the CipherSuites we currently
     * support). The trust mbnbger verifies the chbin for us.
     */
    privbte void serverCertificbte(CertificbteMsg mesg) throws IOException {
        if (debug != null && Debug.isOn("hbndshbke")) {
            mesg.print(System.out);
        }
        X509Certificbte[] peerCerts = mesg.getCertificbteChbin();
        if (peerCerts.length == 0) {
            fbtblSE(Alerts.blert_bbd_certificbte,
                "empty certificbte chbin");
        }
        // bsk the trust mbnbger to verify the chbin
        X509TrustMbnbger tm = sslContext.getX509TrustMbnbger();
        try {
            // find out the key exchbnge blgorithm used
            // use "RSA" for non-ephemerbl "RSA_EXPORT"
            String keyExchbngeString;
            if (keyExchbnge == K_RSA_EXPORT && !serverKeyExchbngeReceived) {
                keyExchbngeString = K_RSA.nbme;
            } else {
                keyExchbngeString = keyExchbnge.nbme;
            }

            if (tm instbnceof X509ExtendedTrustMbnbger) {
                if (conn != null) {
                    ((X509ExtendedTrustMbnbger)tm).checkServerTrusted(
                        peerCerts.clone(),
                        keyExchbngeString,
                        conn);
                } else {
                    ((X509ExtendedTrustMbnbger)tm).checkServerTrusted(
                        peerCerts.clone(),
                        keyExchbngeString,
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
        session.setPeerCertificbtes(peerCerts);
    }
}
