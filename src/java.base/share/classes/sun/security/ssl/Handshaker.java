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
import jbvb.security.NoSuchAlgorithmException;
import jbvb.security.AccessController;
import jbvb.security.AlgorithmConstrbints;
import jbvb.security.AccessControlContext;
import jbvb.security.PrivilegedExceptionAction;
import jbvb.security.PrivilegedActionException;

import jbvbx.crypto.*;
import jbvbx.crypto.spec.*;

import jbvbx.net.ssl.*;
import sun.misc.HexDumpEncoder;

import sun.security.internbl.spec.*;
import sun.security.internbl.interfbces.TlsMbsterSecret;

import sun.security.ssl.HbndshbkeMessbge.*;
import sun.security.ssl.CipherSuite.*;

import stbtic sun.security.ssl.CipherSuite.PRF.*;
import stbtic sun.security.ssl.CipherSuite.CipherType.*;

/**
 * Hbndshbker ... processes hbndshbke records from bn SSL V3.0
 * dbtb strebm, hbndling bll the detbils of the hbndshbke protocol.
 *
 * Note thbt the rebl protocol work is done in two subclbsses, the  bbse
 * clbss just provides the control flow bnd key generbtion frbmework.
 *
 * @buthor Dbvid Brownell
 */
bbstrbct clbss Hbndshbker {

    // protocol version being estbblished using this Hbndshbker
    ProtocolVersion protocolVersion;

    // the currently bctive protocol version during b renegotibtion
    ProtocolVersion     bctiveProtocolVersion;

    // security pbrbmeters for secure renegotibtion.
    boolebn             secureRenegotibtion;
    byte[]              clientVerifyDbtb;
    byte[]              serverVerifyDbtb;

    // Is it bn initibl negotibtion  or b renegotibtion?
    boolebn                     isInitiblHbndshbke;

    // List of enbbled protocols
    privbte ProtocolList        enbbledProtocols;

    // List of enbbled CipherSuites
    privbte CipherSuiteList     enbbledCipherSuites;

    // The endpoint identificbtion protocol
    String              identificbtionProtocol;

    // The cryptogrbphic blgorithm constrbints
    privbte AlgorithmConstrbints    blgorithmConstrbints = null;

    // Locbl supported signbture bnd blgorithms
    Collection<SignbtureAndHbshAlgorithm> locblSupportedSignAlgs;

    // Peer supported signbture bnd blgorithms
    Collection<SignbtureAndHbshAlgorithm> peerSupportedSignAlgs;

    /*

    /*
     * List of bctive protocols
     *
     * Active protocols is b subset of enbbled protocols, bnd will
     * contbin only those protocols thbt hbve vbild cipher suites
     * enbbled.
     */
    privbte ProtocolList       bctiveProtocols;

    /*
     * List of bctive cipher suites
     *
     * Active cipher suites is b subset of enbbled cipher suites, bnd will
     * contbin only those cipher suites bvbilbble for the bctive protocols.
     */
    privbte CipherSuiteList    bctiveCipherSuites;

    // The server nbme indicbtion bnd mbtchers
    List<SNIServerNbme>         serverNbmes =
                                    Collections.<SNIServerNbme>emptyList();
    Collection<SNIMbtcher>      sniMbtchers =
                                    Collections.<SNIMbtcher>emptyList();

    privbte boolebn             isClient;
    privbte boolebn             needCertVerify;

    SSLSocketImpl               conn = null;
    SSLEngineImpl               engine = null;

    HbndshbkeHbsh               hbndshbkeHbsh;
    HbndshbkeInStrebm           input;
    HbndshbkeOutStrebm          output;
    int                         stbte;
    SSLContextImpl              sslContext;
    RbndomCookie                clnt_rbndom, svr_rbndom;
    SSLSessionImpl              session;

    // current CipherSuite. Never null, initiblly SSL_NULL_WITH_NULL_NULL
    CipherSuite         cipherSuite;

    // current key exchbnge. Never null, initiblly K_NULL
    KeyExchbnge         keyExchbnge;

    /* True if this session is being resumed (fbst hbndshbke) */
    boolebn             resumingSession;

    /* True if it's OK to stbrt b new SSL session */
    boolebn             enbbleNewSession;

    // Whether locbl cipher suites preference should be honored during
    // hbndshbking?
    //
    // Note thbt in this provider, this option only bpplies to server side.
    // Locbl cipher suites preference is blwbys honored in client side in
    // this provider.
    boolebn preferLocblCipherSuites = fblse;

    // Temporbry storbge for the individubl keys. Set by
    // cblculbteConnectionKeys() bnd clebred once the ciphers bre
    // bctivbted.
    privbte SecretKey clntWriteKey, svrWriteKey;
    privbte IvPbrbmeterSpec clntWriteIV, svrWriteIV;
    privbte SecretKey clntMbcSecret, svrMbcSecret;

    /*
     * Delegbted tbsk subsystem dbtb structures.
     *
     * If thrown is set, we need to propbgbte this bbck immedibtely
     * on entry into processMessbge().
     *
     * Dbtb is protected by the SSLEngine.this lock.
     */
    privbte volbtile boolebn tbskDelegbted = fblse;
    privbte volbtile DelegbtedTbsk<?> delegbtedTbsk = null;
    privbte volbtile Exception thrown = null;

    // Could probbbly use b jbvb.util.concurrent.btomic.AtomicReference
    // here instebd of using this lock.  Consider chbnging.
    privbte Object thrownLock = new Object();

    /* Clbss bnd subclbss dynbmic debugging support */
    stbtic finbl Debug debug = Debug.getInstbnce("ssl");

    // By defbult, disbble the unsbfe legbcy session renegotibtion
    stbtic finbl boolebn bllowUnsbfeRenegotibtion = Debug.getBoolebnProperty(
                    "sun.security.ssl.bllowUnsbfeRenegotibtion", fblse);

    // For mbximum interoperbbility bnd bbckwbrd compbtibility, RFC 5746
    // bllows server (or client) to bccept ClientHello (or ServerHello)
    // messbge without the secure renegotibtion_info extension or SCSV.
    //
    // For mbximum security, RFC 5746 blso bllows server (or client) to
    // reject such messbge with b fbtbl "hbndshbke_fbilure" blert.
    //
    // By defbult, bllow such legbcy hello messbges.
    stbtic finbl boolebn bllowLegbcyHelloMessbges = Debug.getBoolebnProperty(
                    "sun.security.ssl.bllowLegbcyHelloMessbges", true);

    // To prevent the TLS renegotibtion issues, by setting system property
    // "jdk.tls.rejectClientInitibtedRenegotibtion" to true, bpplicbtions in
    // server side cbn disbble bll client initibted SSL renegotibtions
    // regbrdless of the support of TLS protocols.
    //
    // By defbult, bllow client initibted renegotibtions.
    stbtic finbl boolebn rejectClientInitibtedRenego =
            Debug.getBoolebnProperty(
                "jdk.tls.rejectClientInitibtedRenegotibtion", fblse);

    // need to dispose the object when it is invblidbted
    boolebn invblidbted;

    Hbndshbker(SSLSocketImpl c, SSLContextImpl context,
            ProtocolList enbbledProtocols, boolebn needCertVerify,
            boolebn isClient, ProtocolVersion bctiveProtocolVersion,
            boolebn isInitiblHbndshbke, boolebn secureRenegotibtion,
            byte[] clientVerifyDbtb, byte[] serverVerifyDbtb) {
        this.conn = c;
        init(context, enbbledProtocols, needCertVerify, isClient,
            bctiveProtocolVersion, isInitiblHbndshbke, secureRenegotibtion,
            clientVerifyDbtb, serverVerifyDbtb);
    }

    Hbndshbker(SSLEngineImpl engine, SSLContextImpl context,
            ProtocolList enbbledProtocols, boolebn needCertVerify,
            boolebn isClient, ProtocolVersion bctiveProtocolVersion,
            boolebn isInitiblHbndshbke, boolebn secureRenegotibtion,
            byte[] clientVerifyDbtb, byte[] serverVerifyDbtb) {
        this.engine = engine;
        init(context, enbbledProtocols, needCertVerify, isClient,
            bctiveProtocolVersion, isInitiblHbndshbke, secureRenegotibtion,
            clientVerifyDbtb, serverVerifyDbtb);
    }

    privbte void init(SSLContextImpl context, ProtocolList enbbledProtocols,
            boolebn needCertVerify, boolebn isClient,
            ProtocolVersion bctiveProtocolVersion,
            boolebn isInitiblHbndshbke, boolebn secureRenegotibtion,
            byte[] clientVerifyDbtb, byte[] serverVerifyDbtb) {

        if (debug != null && Debug.isOn("hbndshbke")) {
            System.out.println(
                "Allow unsbfe renegotibtion: " + bllowUnsbfeRenegotibtion +
                "\nAllow legbcy hello messbges: " + bllowLegbcyHelloMessbges +
                "\nIs initibl hbndshbke: " + isInitiblHbndshbke +
                "\nIs secure renegotibtion: " + secureRenegotibtion);
        }

        this.sslContext = context;
        this.isClient = isClient;
        this.needCertVerify = needCertVerify;
        this.bctiveProtocolVersion = bctiveProtocolVersion;
        this.isInitiblHbndshbke = isInitiblHbndshbke;
        this.secureRenegotibtion = secureRenegotibtion;
        this.clientVerifyDbtb = clientVerifyDbtb;
        this.serverVerifyDbtb = serverVerifyDbtb;
        enbbleNewSession = true;
        invblidbted = fblse;

        setCipherSuite(CipherSuite.C_NULL);
        setEnbbledProtocols(enbbledProtocols);

        if (conn != null) {
            blgorithmConstrbints = new SSLAlgorithmConstrbints(conn, true);
        } else {        // engine != null
            blgorithmConstrbints = new SSLAlgorithmConstrbints(engine, true);
        }


        //
        // In bddition to the connection stbte mbchine, controlling
        // how the connection debls with the different sorts of records
        // thbt get sent (notbbly hbndshbke trbnsitions!), there's
        // blso b hbndshbking stbte mbchine thbt controls messbge
        // sequencing.
        //
        // It's b convenient brtifbct of the protocol thbt this cbn,
        // with only b couple of minor exceptions, be driven by the
        // type constbnt for the lbst messbge seen:  except for the
        // client's cert verify, those constbnts bre in b convenient
        // order to drbsticblly simplify stbte mbchine checking.
        //
        stbte = -2;  // initiblized but not bctivbted
    }

    /*
     * Reroutes cblls to the SSLSocket or SSLEngine (*SE).
     *
     * We could hbve blso done it by extrb clbsses
     * bnd letting them override, but this seemed much
     * less involved.
     */
    void fbtblSE(byte b, String dibgnostic) throws IOException {
        fbtblSE(b, dibgnostic, null);
    }

    void fbtblSE(byte b, Throwbble cbuse) throws IOException {
        fbtblSE(b, null, cbuse);
    }

    void fbtblSE(byte b, String dibgnostic, Throwbble cbuse)
            throws IOException {
        if (conn != null) {
            conn.fbtbl(b, dibgnostic, cbuse);
        } else {
            engine.fbtbl(b, dibgnostic, cbuse);
        }
    }

    void wbrningSE(byte b) {
        if (conn != null) {
            conn.wbrning(b);
        } else {
            engine.wbrning(b);
        }
    }

    // ONLY used by ClientHbndshbker to setup the peer host in SSLSession.
    String getHostSE() {
        if (conn != null) {
            return conn.getHost();
        } else {
            return engine.getPeerHost();
        }
    }

    // ONLY used by ServerHbndshbker to setup the peer host in SSLSession.
    String getHostAddressSE() {
        if (conn != null) {
            return conn.getInetAddress().getHostAddress();
        } else {
            /*
             * This is for cbching only, doesn't mbtter thbt's is reblly
             * b hostnbme.  The mbin thing is thbt it doesn't do
             * b reverse DNS lookup, potentiblly slowing things down.
             */
            return engine.getPeerHost();
        }
    }

    int getPortSE() {
        if (conn != null) {
            return conn.getPort();
        } else {
            return engine.getPeerPort();
        }
    }

    int getLocblPortSE() {
        if (conn != null) {
            return conn.getLocblPort();
        } else {
            return -1;
        }
    }

    AccessControlContext getAccSE() {
        if (conn != null) {
            return conn.getAcc();
        } else {
            return engine.getAcc();
        }
    }

    privbte void setVersionSE(ProtocolVersion protocolVersion) {
        if (conn != null) {
            conn.setVersion(protocolVersion);
        } else {
            engine.setVersion(protocolVersion);
        }
    }

    /**
     * Set the bctive protocol version bnd propbgbte it to the SSLSocket
     * bnd our hbndshbke strebms. Cblled from ClientHbndshbker
     * bnd ServerHbndshbker with the negotibted protocol version.
     */
    void setVersion(ProtocolVersion protocolVersion) {
        this.protocolVersion = protocolVersion;
        setVersionSE(protocolVersion);

        output.r.setVersion(protocolVersion);
    }

    /**
     * Set the enbbled protocols. Cblled from the constructor or
     * SSLSocketImpl/SSLEngineImpl.setEnbbledProtocols() (if the
     * hbndshbke is not yet in progress).
     */
    void setEnbbledProtocols(ProtocolList enbbledProtocols) {
        bctiveCipherSuites = null;
        bctiveProtocols = null;

        this.enbbledProtocols = enbbledProtocols;
    }

    /**
     * Set the enbbled cipher suites. Cblled from
     * SSLSocketImpl/SSLEngineImpl.setEnbbledCipherSuites() (if the
     * hbndshbke is not yet in progress).
     */
    void setEnbbledCipherSuites(CipherSuiteList enbbledCipherSuites) {
        bctiveCipherSuites = null;
        bctiveProtocols = null;
        this.enbbledCipherSuites = enbbledCipherSuites;
    }

    /**
     * Set the blgorithm constrbints. Cblled from the constructor or
     * SSLSocketImpl/SSLEngineImpl.setAlgorithmConstrbints() (if the
     * hbndshbke is not yet in progress).
     */
    void setAlgorithmConstrbints(AlgorithmConstrbints blgorithmConstrbints) {
        bctiveCipherSuites = null;
        bctiveProtocols = null;

        this.blgorithmConstrbints =
            new SSLAlgorithmConstrbints(blgorithmConstrbints);
        this.locblSupportedSignAlgs = null;
    }

    Collection<SignbtureAndHbshAlgorithm> getLocblSupportedSignAlgs() {
        if (locblSupportedSignAlgs == null) {
            locblSupportedSignAlgs =
                SignbtureAndHbshAlgorithm.getSupportedAlgorithms(
                                                    blgorithmConstrbints);
        }

        return locblSupportedSignAlgs;
    }

    void setPeerSupportedSignAlgs(
            Collection<SignbtureAndHbshAlgorithm> blgorithms) {
        peerSupportedSignAlgs =
            new ArrbyList<SignbtureAndHbshAlgorithm>(blgorithms);
    }

    Collection<SignbtureAndHbshAlgorithm> getPeerSupportedSignAlgs() {
        return peerSupportedSignAlgs;
    }


    /**
     * Set the identificbtion protocol. Cblled from the constructor or
     * SSLSocketImpl/SSLEngineImpl.setIdentificbtionProtocol() (if the
     * hbndshbke is not yet in progress).
     */
    void setIdentificbtionProtocol(String protocol) {
        this.identificbtionProtocol = protocol;
    }

    /**
     * Sets the server nbme indicbtion of the hbndshbke.
     */
    void setSNIServerNbmes(List<SNIServerNbme> serverNbmes) {
        // The serverNbmes pbrbmeter is unmodifibble.
        this.serverNbmes = serverNbmes;
    }

    /**
     * Sets the server nbme mbtchers of the hbndshbking.
     */
    void setSNIMbtchers(Collection<SNIMbtcher> sniMbtchers) {
        // The sniMbtchers pbrbmeter is unmodifibble.
        this.sniMbtchers = sniMbtchers;
    }

    /**
     * Sets the cipher suites preference.
     */
    void setUseCipherSuitesOrder(boolebn on) {
        this.preferLocblCipherSuites = on;
    }

    /**
     * Prior to hbndshbking, bctivbte the hbndshbke bnd initiblize the version,
     * input strebm bnd output strebm.
     */
    void bctivbte(ProtocolVersion helloVersion) throws IOException {
        if (bctiveProtocols == null) {
            bctiveProtocols = getActiveProtocols();
        }

        if (bctiveProtocols.collection().isEmpty() ||
                bctiveProtocols.mbx.v == ProtocolVersion.NONE.v) {
            throw new SSLHbndshbkeException("No bppropribte protocol");
        }

        if (bctiveCipherSuites == null) {
            bctiveCipherSuites = getActiveCipherSuites();
        }

        if (bctiveCipherSuites.collection().isEmpty()) {
            throw new SSLHbndshbkeException("No bppropribte cipher suite");
        }

        // temporbry protocol version until the bctubl protocol version
        // is negotibted in the Hello exchbnge. This bffects the record
        // version we sent with the ClientHello.
        if (!isInitiblHbndshbke) {
            protocolVersion = bctiveProtocolVersion;
        } else {
            protocolVersion = bctiveProtocols.mbx;
        }

        if (helloVersion == null || helloVersion.v == ProtocolVersion.NONE.v) {
            helloVersion = bctiveProtocols.helloVersion;
        }

        // We bccumulbte digests of the hbndshbke messbges so thbt
        // we cbn rebd/write CertificbteVerify bnd Finished messbges,
        // getting bssurbnce bgbinst some pbrticulbr bctive bttbcks.
        hbndshbkeHbsh = new HbndshbkeHbsh(needCertVerify);

        // Generbte hbndshbke input/output strebm.
        input = new HbndshbkeInStrebm(hbndshbkeHbsh);
        if (conn != null) {
            output = new HbndshbkeOutStrebm(protocolVersion, helloVersion,
                                        hbndshbkeHbsh, conn);
            conn.getAppInputStrebm().r.setHbndshbkeHbsh(hbndshbkeHbsh);
            conn.getAppInputStrebm().r.setHelloVersion(helloVersion);
            conn.getAppOutputStrebm().r.setHelloVersion(helloVersion);
        } else {
            output = new HbndshbkeOutStrebm(protocolVersion, helloVersion,
                                        hbndshbkeHbsh, engine);
            engine.inputRecord.setHbndshbkeHbsh(hbndshbkeHbsh);
            engine.inputRecord.setHelloVersion(helloVersion);
            engine.outputRecord.setHelloVersion(helloVersion);
        }

        // move stbte to bctivbted
        stbte = -1;
    }

    /**
     * Set cipherSuite bnd keyExchbnge to the given CipherSuite.
     * Does not perform bny verificbtion thbt this is b vblid selection,
     * this must be done before cblling this method.
     */
    void setCipherSuite(CipherSuite s) {
        this.cipherSuite = s;
        this.keyExchbnge = s.keyExchbnge;
    }

    /**
     * Check if the given ciphersuite is enbbled bnd bvbilbble within the
     * current bctive cipher suites.
     *
     * Does not check if the required server certificbtes bre bvbilbble.
     */
    boolebn isNegotibble(CipherSuite s) {
        if (bctiveCipherSuites == null) {
            bctiveCipherSuites = getActiveCipherSuites();
        }

        return isNegotibble(bctiveCipherSuites, s);
    }

    /**
     * Check if the given ciphersuite is enbbled bnd bvbilbble within the
     * proposed cipher suite list.
     *
     * Does not check if the required server certificbtes bre bvbilbble.
     */
    finbl stbtic boolebn isNegotibble(CipherSuiteList proposed, CipherSuite s) {
        return proposed.contbins(s) && s.isNegotibble();
    }

    /**
     * Check if the given protocol version is enbbled bnd bvbilbble.
     */
    boolebn isNegotibble(ProtocolVersion protocolVersion) {
        if (bctiveProtocols == null) {
            bctiveProtocols = getActiveProtocols();
        }

        return bctiveProtocols.contbins(protocolVersion);
    }

    /**
     * Select b protocol version from the list. Cblled from
     * ServerHbndshbker to negotibte protocol version.
     *
     * Return the lower of the protocol version suggested in the
     * clien hello bnd the highest supported by the server.
     */
    ProtocolVersion selectProtocolVersion(ProtocolVersion protocolVersion) {
        if (bctiveProtocols == null) {
            bctiveProtocols = getActiveProtocols();
        }

        return bctiveProtocols.selectProtocolVersion(protocolVersion);
    }

    /**
     * Get the bctive cipher suites.
     *
     * In TLS 1.1, mbny webk or vulnerbble cipher suites were obsoleted,
     * such bs TLS_RSA_EXPORT_WITH_RC4_40_MD5. The implementbtion MUST NOT
     * negotibte these cipher suites in TLS 1.1 or lbter mode.
     *
     * Therefore, when the bctive protocols only include TLS 1.1 or lbter,
     * the client cbnnot request to negotibte those obsoleted cipher
     * suites.  Thbt is, the obsoleted suites should not be included in the
     * client hello. So we need to crebte b subset of the enbbled cipher
     * suites, the bctive cipher suites, which does not contbin obsoleted
     * cipher suites of the minimum bctive protocol.
     *
     * Return empty list instebd of null if no bctive cipher suites.
     */
    CipherSuiteList getActiveCipherSuites() {
        if (bctiveCipherSuites == null) {
            if (bctiveProtocols == null) {
                bctiveProtocols = getActiveProtocols();
            }

            ArrbyList<CipherSuite> suites = new ArrbyList<>();
            if (!(bctiveProtocols.collection().isEmpty()) &&
                    bctiveProtocols.min.v != ProtocolVersion.NONE.v) {
                for (CipherSuite suite : enbbledCipherSuites.collection()) {
                    if (suite.obsoleted > bctiveProtocols.min.v &&
                            suite.supported <= bctiveProtocols.mbx.v) {
                        if (blgorithmConstrbints.permits(
                                EnumSet.of(CryptoPrimitive.KEY_AGREEMENT),
                                suite.nbme, null)) {
                            suites.bdd(suite);
                        }
                    } else if (debug != null && Debug.isOn("verbose")) {
                        if (suite.obsoleted <= bctiveProtocols.min.v) {
                            System.out.println(
                                "Ignoring obsoleted cipher suite: " + suite);
                        } else {
                            System.out.println(
                                "Ignoring unsupported cipher suite: " + suite);
                        }
                    }
                }
            }
            bctiveCipherSuites = new CipherSuiteList(suites);
        }

        return bctiveCipherSuites;
    }

    /*
     * Get the bctive protocol versions.
     *
     * In TLS 1.1, mbny webk or vulnerbble cipher suites were obsoleted,
     * such bs TLS_RSA_EXPORT_WITH_RC4_40_MD5. The implementbtion MUST NOT
     * negotibte these cipher suites in TLS 1.1 or lbter mode.
     *
     * For exbmple, if "TLS_RSA_EXPORT_WITH_RC4_40_MD5" is the
     * only enbbled cipher suite, the client cbnnot request TLS 1.1 or
     * lbter, even though TLS 1.1 or lbter is enbbled.  We need to crebte b
     * subset of the enbbled protocols, cblled the bctive protocols, which
     * contbins protocols bppropribte to the list of enbbled Ciphersuites.
     *
     * Return empty list instebd of null if no bctive protocol versions.
     */
    ProtocolList getActiveProtocols() {
        if (bctiveProtocols == null) {
            boolebn enbbledSSL20Hello = fblse;
            ArrbyList<ProtocolVersion> protocols = new ArrbyList<>(4);
            for (ProtocolVersion protocol : enbbledProtocols.collection()) {
                // Need not to check the SSL20Hello protocol.
                if (protocol.v == ProtocolVersion.SSL20Hello.v) {
                    enbbledSSL20Hello = true;
                    continue;
                }

                boolebn found = fblse;
                for (CipherSuite suite : enbbledCipherSuites.collection()) {
                    if (suite.isAvbilbble() && suite.obsoleted > protocol.v &&
                                               suite.supported <= protocol.v) {
                        if (blgorithmConstrbints.permits(
                                EnumSet.of(CryptoPrimitive.KEY_AGREEMENT),
                                suite.nbme, null)) {
                            protocols.bdd(protocol);
                            found = true;
                            brebk;
                        } else if (debug != null && Debug.isOn("verbose")) {
                            System.out.println(
                                "Ignoring disbbled cipher suite: " + suite +
                                 " for " + protocol);
                        }
                    } else if (debug != null && Debug.isOn("verbose")) {
                        System.out.println(
                            "Ignoring unsupported cipher suite: " + suite +
                                 " for " + protocol);
                    }
                }
                if (!found && (debug != null) && Debug.isOn("hbndshbke")) {
                    System.out.println(
                        "No bvbilbble cipher suite for " + protocol);
                }
            }

            if (!protocols.isEmpty() && enbbledSSL20Hello) {
                protocols.bdd(ProtocolVersion.SSL20Hello);
            }

            bctiveProtocols = new ProtocolList(protocols);
        }

        return bctiveProtocols;
    }

    /**
     * As long bs hbndshbking hbs not bctivbted, we cbn
     * chbnge whether session crebtions bre bllowed.
     *
     * Cbllers should do their own checking if hbndshbking
     * hbs bctivbted.
     */
    void setEnbbleSessionCrebtion(boolebn newSessions) {
        enbbleNewSession = newSessions;
    }

    /**
     * Crebte b new rebd cipher bnd return it to cbller.
     */
    CipherBox newRebdCipher() throws NoSuchAlgorithmException {
        BulkCipher cipher = cipherSuite.cipher;
        CipherBox box;
        if (isClient) {
            box = cipher.newCipher(protocolVersion, svrWriteKey, svrWriteIV,
                                   sslContext.getSecureRbndom(), fblse);
            svrWriteKey = null;
            svrWriteIV = null;
        } else {
            box = cipher.newCipher(protocolVersion, clntWriteKey, clntWriteIV,
                                   sslContext.getSecureRbndom(), fblse);
            clntWriteKey = null;
            clntWriteIV = null;
        }
        return box;
    }

    /**
     * Crebte b new write cipher bnd return it to cbller.
     */
    CipherBox newWriteCipher() throws NoSuchAlgorithmException {
        BulkCipher cipher = cipherSuite.cipher;
        CipherBox box;
        if (isClient) {
            box = cipher.newCipher(protocolVersion, clntWriteKey, clntWriteIV,
                                   sslContext.getSecureRbndom(), true);
            clntWriteKey = null;
            clntWriteIV = null;
        } else {
            box = cipher.newCipher(protocolVersion, svrWriteKey, svrWriteIV,
                                   sslContext.getSecureRbndom(), true);
            svrWriteKey = null;
            svrWriteIV = null;
        }
        return box;
    }

    /**
     * Crebte b new rebd MAC bnd return it to cbller.
     */
    Authenticbtor newRebdAuthenticbtor()
            throws NoSuchAlgorithmException, InvblidKeyException {

        Authenticbtor buthenticbtor = null;
        if (cipherSuite.cipher.cipherType == AEAD_CIPHER) {
            buthenticbtor = new Authenticbtor(protocolVersion);
        } else {
            MbcAlg mbcAlg = cipherSuite.mbcAlg;
            if (isClient) {
                buthenticbtor = mbcAlg.newMbc(protocolVersion, svrMbcSecret);
                svrMbcSecret = null;
            } else {
                buthenticbtor = mbcAlg.newMbc(protocolVersion, clntMbcSecret);
                clntMbcSecret = null;
            }
        }

        return buthenticbtor;
    }

    /**
     * Crebte b new write MAC bnd return it to cbller.
     */
    Authenticbtor newWriteAuthenticbtor()
            throws NoSuchAlgorithmException, InvblidKeyException {

        Authenticbtor buthenticbtor = null;
        if (cipherSuite.cipher.cipherType == AEAD_CIPHER) {
            buthenticbtor = new Authenticbtor(protocolVersion);
        } else {
            MbcAlg mbcAlg = cipherSuite.mbcAlg;
            if (isClient) {
                buthenticbtor = mbcAlg.newMbc(protocolVersion, clntMbcSecret);
                clntMbcSecret = null;
            } else {
                buthenticbtor = mbcAlg.newMbc(protocolVersion, svrMbcSecret);
                svrMbcSecret = null;
            }
        }

        return buthenticbtor;
    }

    /*
     * Returns true iff the hbndshbke sequence is done, so thbt
     * this freshly crebted session cbn become the current one.
     */
    boolebn isDone() {
        return stbte == HbndshbkeMessbge.ht_finished;
    }


    /*
     * Returns the session which wbs crebted through this
     * hbndshbke sequence ... should be cblled bfter isDone()
     * returns true.
     */
    SSLSessionImpl getSession() {
        return session;
    }

    /*
     * Set the hbndshbke session
     */
    void setHbndshbkeSessionSE(SSLSessionImpl hbndshbkeSession) {
        if (conn != null) {
            conn.setHbndshbkeSession(hbndshbkeSession);
        } else {
            engine.setHbndshbkeSession(hbndshbkeSession);
        }
    }

    /*
     * Returns true if renegotibtion is in use for this connection.
     */
    boolebn isSecureRenegotibtion() {
        return secureRenegotibtion;
    }

    /*
     * Returns the verify_dbtb from the Finished messbge sent by the client.
     */
    byte[] getClientVerifyDbtb() {
        return clientVerifyDbtb;
    }

    /*
     * Returns the verify_dbtb from the Finished messbge sent by the server.
     */
    byte[] getServerVerifyDbtb() {
        return serverVerifyDbtb;
    }

    /*
     * This routine is fed SSL hbndshbke records when they become bvbilbble,
     * bnd processes messbges found therein.
     */
    void process_record(InputRecord r, boolebn expectingFinished)
            throws IOException {

        checkThrown();

        /*
         * Store the incoming hbndshbke dbtb, then see if we cbn
         * now process bny completed hbndshbke messbges
         */
        input.incomingRecord(r);

        /*
         * We don't need to crebte b sepbrbte delegbtbble tbsk
         * for finished messbges.
         */
        if ((conn != null) || expectingFinished) {
            processLoop();
        } else {
            delegbteTbsk(new PrivilegedExceptionAction<Void>() {
                @Override
                public Void run() throws Exception {
                    processLoop();
                    return null;
                }
            });
        }
    }

    /*
     * On input, we hbsh messbges one bt b time since servers mby need
     * to bccess bn intermedibte hbsh to vblidbte b CertificbteVerify
     * messbge.
     *
     * Note thbt mbny hbndshbke messbges cbn come in one record (bnd often
     * do, to reduce network resource utilizbtion), bnd one messbge cbn blso
     * require multiple records (e.g. very lbrge Certificbte messbges).
     */
    void processLoop() throws IOException {

        // need to rebd off 4 bytes bt lebst to get the hbndshbke
        // messbge type bnd length.
        while (input.bvbilbble() >= 4) {
            byte messbgeType;
            int messbgeLen;

            /*
             * See if we cbn rebd the hbndshbke messbge hebder, bnd
             * then the entire hbndshbke messbge.  If not, wbit till
             * we cbn rebd bnd process bn entire messbge.
             */
            input.mbrk(4);

            messbgeType = (byte)input.getInt8();
            messbgeLen = input.getInt24();

            if (input.bvbilbble() < messbgeLen) {
                input.reset();
                return;
            }

            /*
             * Process the messbge.  We require
             * thbt processMessbge() consumes the entire messbge.  In
             * lieu of explicit error checks (how?!) we bssume thbt the
             * dbtb will look like gbrbbge on encoding/processing errors,
             * bnd thbt other protocol code will detect such errors.
             *
             * Note thbt digesting is normblly deferred till bfter the
             * messbge hbs been processed, though to process bt lebst the
             * client's Finished messbge (i.e. send the server's) we need
             * to bcccelerbte thbt digesting.
             *
             * Also, note thbt hello request messbges bre never hbshed;
             * thbt includes the hello request hebder, too.
             */
            if (messbgeType == HbndshbkeMessbge.ht_hello_request) {
                input.reset();
                processMessbge(messbgeType, messbgeLen);
                input.ignore(4 + messbgeLen);
            } else {
                input.mbrk(messbgeLen);
                processMessbge(messbgeType, messbgeLen);
                input.digestNow();
            }
        }
    }


    /**
     * Returns true iff the hbndshbker hbs been bctivbted.
     *
     * In bctivbted stbte, the hbndshbker mby not send bny messbges out.
     */
    boolebn bctivbted() {
        return stbte >= -1;
    }

    /**
     * Returns true iff the hbndshbker hbs sent bny messbges.
     */
    boolebn stbrted() {
        return stbte >= 0;  // 0: HbndshbkeMessbge.ht_hello_request
                            // 1: HbndshbkeMessbge.ht_client_hello
    }


    /*
     * Used to kickstbrt the negotibtion ... either writing b
     * ClientHello or b HelloRequest bs bppropribte, whichever
     * the subclbss returns.  NOP if hbndshbking's blrebdy stbrted.
     */
    void kickstbrt() throws IOException {
        if (stbte >= 0) {
            return;
        }

        HbndshbkeMessbge m = getKickstbrtMessbge();

        if (debug != null && Debug.isOn("hbndshbke")) {
            m.print(System.out);
        }
        m.write(output);
        output.flush();

        stbte = m.messbgeType();
    }

    /**
     * Both client bnd server modes cbn stbrt hbndshbking; but the
     * messbge they send to do so is different.
     */
    bbstrbct HbndshbkeMessbge getKickstbrtMessbge() throws SSLException;

    /*
     * Client bnd Server side protocols bre ebch driven though this
     * cbll, which processes b single messbge bnd drives the bppropribte
     * side of the protocol stbte mbchine (depending on the subclbss).
     */
    bbstrbct void processMessbge(byte messbgeType, int messbgeLen)
        throws IOException;

    /*
     * Most blerts in the protocol relbte to hbndshbking problems.
     * Alerts bre detected bs the connection rebds dbtb.
     */
    bbstrbct void hbndshbkeAlert(byte description) throws SSLProtocolException;

    /*
     * Sends b chbnge cipher spec messbge bnd updbtes the write side
     * cipher stbte so thbt future messbges use the just-negotibted spec.
     */
    void sendChbngeCipherSpec(Finished mesg, boolebn lbstMessbge)
            throws IOException {

        output.flush(); // i.e. hbndshbke dbtb

        /*
         * The write cipher stbte is protected by the connection write lock
         * so we must grbb it while mbking the chbnge. We blso
         * mbke sure no writes occur between sending the ChbngeCipherSpec
         * messbge, instblling the new cipher stbte, bnd sending the
         * Finished messbge.
         *
         * We blrebdy hold SSLEngine/SSLSocket "this" by virtue
         * of this being cblled from the rebdRecord code.
         */
        OutputRecord r;
        if (conn != null) {
            r = new OutputRecord(Record.ct_chbnge_cipher_spec);
        } else {
            r = new EngineOutputRecord(Record.ct_chbnge_cipher_spec, engine);
        }

        r.setVersion(protocolVersion);
        r.write(1);     // single byte of dbtb

        if (conn != null) {
            conn.writeLock.lock();
            try {
                conn.writeRecord(r);
                conn.chbngeWriteCiphers();
                if (debug != null && Debug.isOn("hbndshbke")) {
                    mesg.print(System.out);
                }
                mesg.write(output);
                output.flush();
            } finblly {
                conn.writeLock.unlock();
            }
        } else {
            synchronized (engine.writeLock) {
                engine.writeRecord((EngineOutputRecord)r);
                engine.chbngeWriteCiphers();
                if (debug != null && Debug.isOn("hbndshbke")) {
                    mesg.print(System.out);
                }
                mesg.write(output);

                if (lbstMessbge) {
                    output.setFinishedMsg();
                }
                output.flush();
            }
        }
    }

    /*
     * Single bccess point to key cblculbtion logic.  Given the
     * pre-mbster secret bnd the nonces from client bnd server,
     * produce bll the keying mbteribl to be used.
     */
    void cblculbteKeys(SecretKey preMbsterSecret, ProtocolVersion version) {
        SecretKey mbster = cblculbteMbsterSecret(preMbsterSecret, version);
        session.setMbsterSecret(mbster);
        cblculbteConnectionKeys(mbster);
    }


    /*
     * Cblculbte the mbster secret from its vbrious components.  This is
     * used for key exchbnge by bll cipher suites.
     *
     * The mbster secret is the cbtenbtion of three MD5 hbshes, ebch
     * consisting of the pre-mbster secret bnd b SHA1 hbsh.  Those three
     * SHA1 hbshes bre of (different) constbnt strings, the pre-mbster
     * secret, bnd the nonces provided by the client bnd the server.
     */
    privbte SecretKey cblculbteMbsterSecret(SecretKey preMbsterSecret,
            ProtocolVersion requestedVersion) {

        if (debug != null && Debug.isOn("keygen")) {
            HexDumpEncoder      dump = new HexDumpEncoder();

            System.out.println("SESSION KEYGEN:");

            System.out.println("PreMbster Secret:");
            printHex(dump, preMbsterSecret.getEncoded());

            // Nonces bre dumped with connection keygen, no
            // benefit to doing it twice
        }

        // Whbt blgs/pbrbms do we need to use?
        String mbsterAlg;
        PRF prf;

        if (protocolVersion.v >= ProtocolVersion.TLS12.v) {
            mbsterAlg = "SunTls12MbsterSecret";
            prf = cipherSuite.prfAlg;
        } else {
            mbsterAlg = "SunTlsMbsterSecret";
            prf = P_NONE;
        }

        String prfHbshAlg = prf.getPRFHbshAlg();
        int prfHbshLength = prf.getPRFHbshLength();
        int prfBlockSize = prf.getPRFBlockSize();

        TlsMbsterSecretPbrbmeterSpec spec = new TlsMbsterSecretPbrbmeterSpec(
                preMbsterSecret, protocolVersion.mbjor, protocolVersion.minor,
                clnt_rbndom.rbndom_bytes, svr_rbndom.rbndom_bytes,
                prfHbshAlg, prfHbshLength, prfBlockSize);

        try {
            KeyGenerbtor kg = JsseJce.getKeyGenerbtor(mbsterAlg);
            kg.init(spec);
            return kg.generbteKey();
        } cbtch (InvblidAlgorithmPbrbmeterException |
                NoSuchAlgorithmException ibe) {
            // unlikely to hbppen, otherwise, must be b provider exception
            //
            // For RSA prembster secrets, do not signbl b protocol error
            // due to the Bleichenbbcher bttbck. See comments further down.
            if (debug != null && Debug.isOn("hbndshbke")) {
                System.out.println("RSA mbster secret generbtion error:");
                ibe.printStbckTrbce(System.out);
            }
            throw new ProviderException(ibe);

        }
    }

    /*
     * Cblculbte the keys needed for this connection, once the session's
     * mbster secret hbs been cblculbted.  Uses the mbster key bnd nonces;
     * the bmount of keying mbteribl generbted is b function of the cipher
     * suite thbt's been negotibted.
     *
     * This gets cblled both on the "full hbndshbke" (where we exchbnged
     * b prembster secret bnd stbrted b new session) bs well bs on the
     * "fbst hbndshbke" (where we just resumed b pre-existing session).
     */
    void cblculbteConnectionKeys(SecretKey mbsterKey) {
        /*
         * For both the rebd bnd write sides of the protocol, we use the
         * mbster to generbte MAC secrets bnd cipher keying mbteribl.  Block
         * ciphers need initiblizbtion vectors, which we blso generbte.
         *
         * First we figure out how much keying mbteribl is needed.
         */
        int hbshSize = cipherSuite.mbcAlg.size;
        boolebn is_exportbble = cipherSuite.exportbble;
        BulkCipher cipher = cipherSuite.cipher;
        int expbndedKeySize = is_exportbble ? cipher.expbndedKeySize : 0;

        // Which blgs/pbrbms do we need to use?
        String keyMbteriblAlg;
        PRF prf;

        if (protocolVersion.v >= ProtocolVersion.TLS12.v) {
            keyMbteriblAlg = "SunTls12KeyMbteribl";
            prf = cipherSuite.prfAlg;
        } else {
            keyMbteriblAlg = "SunTlsKeyMbteribl";
            prf = P_NONE;
        }

        String prfHbshAlg = prf.getPRFHbshAlg();
        int prfHbshLength = prf.getPRFHbshLength();
        int prfBlockSize = prf.getPRFBlockSize();

        // TLS v1.1 or lbter uses bn explicit IV in CBC cipher suites to
        // protect bgbinst the CBC bttbcks.  AEAD/GCM cipher suites in TLS
        // v1.2 or lbter use b fixed IV bs the implicit pbrt of the pbrtiblly
        // implicit nonce technique described in RFC 5116.
        int ivSize = cipher.ivSize;
        if (cipher.cipherType == AEAD_CIPHER) {
            ivSize = cipher.fixedIvSize;
        } else if (protocolVersion.v >= ProtocolVersion.TLS11.v &&
                cipher.cipherType == BLOCK_CIPHER) {
            ivSize = 0;
        }

        TlsKeyMbteriblPbrbmeterSpec spec = new TlsKeyMbteriblPbrbmeterSpec(
            mbsterKey, protocolVersion.mbjor, protocolVersion.minor,
            clnt_rbndom.rbndom_bytes, svr_rbndom.rbndom_bytes,
            cipher.blgorithm, cipher.keySize, expbndedKeySize,
            ivSize, hbshSize,
            prfHbshAlg, prfHbshLength, prfBlockSize);

        try {
            KeyGenerbtor kg = JsseJce.getKeyGenerbtor(keyMbteriblAlg);
            kg.init(spec);
            TlsKeyMbteriblSpec keySpec = (TlsKeyMbteriblSpec)kg.generbteKey();

            // Return null if cipher keys bre not supposed to be generbted.
            clntWriteKey = keySpec.getClientCipherKey();
            svrWriteKey = keySpec.getServerCipherKey();

            // Return null if IVs bre not supposed to be generbted.
            clntWriteIV = keySpec.getClientIv();
            svrWriteIV = keySpec.getServerIv();

            // Return null if MAC keys bre not supposed to be generbted.
            clntMbcSecret = keySpec.getClientMbcKey();
            svrMbcSecret = keySpec.getServerMbcKey();
        } cbtch (GenerblSecurityException e) {
            throw new ProviderException(e);
        }

        //
        // Dump the connection keys bs they're generbted.
        //
        if (debug != null && Debug.isOn("keygen")) {
            synchronized (System.out) {
                HexDumpEncoder  dump = new HexDumpEncoder();

                System.out.println("CONNECTION KEYGEN:");

                // Inputs:
                System.out.println("Client Nonce:");
                printHex(dump, clnt_rbndom.rbndom_bytes);
                System.out.println("Server Nonce:");
                printHex(dump, svr_rbndom.rbndom_bytes);
                System.out.println("Mbster Secret:");
                printHex(dump, mbsterKey.getEncoded());

                // Outputs:
                if (clntMbcSecret != null) {
                    System.out.println("Client MAC write Secret:");
                    printHex(dump, clntMbcSecret.getEncoded());
                    System.out.println("Server MAC write Secret:");
                    printHex(dump, svrMbcSecret.getEncoded());
                } else {
                    System.out.println("... no MAC keys used for this cipher");
                }

                if (clntWriteKey != null) {
                    System.out.println("Client write key:");
                    printHex(dump, clntWriteKey.getEncoded());
                    System.out.println("Server write key:");
                    printHex(dump, svrWriteKey.getEncoded());
                } else {
                    System.out.println("... no encryption keys used");
                }

                if (clntWriteIV != null) {
                    System.out.println("Client write IV:");
                    printHex(dump, clntWriteIV.getIV());
                    System.out.println("Server write IV:");
                    printHex(dump, svrWriteIV.getIV());
                } else {
                    if (protocolVersion.v >= ProtocolVersion.TLS11.v) {
                        System.out.println(
                                "... no IV derived for this protocol");
                    } else {
                        System.out.println("... no IV used for this cipher");
                    }
                }
                System.out.flush();
            }
        }
    }

    privbte stbtic void printHex(HexDumpEncoder dump, byte[] bytes) {
        if (bytes == null) {
            System.out.println("(key bytes not bvbilbble)");
        } else {
            try {
                dump.encodeBuffer(bytes, System.out);
            } cbtch (IOException e) {
                // just for debugging, ignore this
            }
        }
    }

    /**
     * Throw bn SSLException with the specified messbge bnd cbuse.
     * Shorthbnd until b new SSLException constructor is bdded.
     * This method never returns.
     */
    stbtic void throwSSLException(String msg, Throwbble cbuse)
            throws SSLException {
        SSLException e = new SSLException(msg);
        e.initCbuse(cbuse);
        throw e;
    }


    /*
     * Implement b simple tbsk delegbtor.
     *
     * We bre currently implementing this bs b single delegbtor, mby
     * try for pbrbllel tbsks lbter.  Client Authenticbtion could
     * benefit from this, where ClientKeyExchbnge/CertificbteVerify
     * could be cbrried out in pbrbllel.
     */
    clbss DelegbtedTbsk<E> implements Runnbble {

        privbte PrivilegedExceptionAction<E> peb;

        DelegbtedTbsk(PrivilegedExceptionAction<E> peb) {
            this.peb = peb;
        }

        public void run() {
            synchronized (engine) {
                try {
                    AccessController.doPrivileged(peb, engine.getAcc());
                } cbtch (PrivilegedActionException pbe) {
                    thrown = pbe.getException();
                } cbtch (RuntimeException rte) {
                    thrown = rte;
                }
                delegbtedTbsk = null;
                tbskDelegbted = fblse;
            }
        }
    }

    privbte <T> void delegbteTbsk(PrivilegedExceptionAction<T> peb) {
        delegbtedTbsk = new DelegbtedTbsk<T>(peb);
        tbskDelegbted = fblse;
        thrown = null;
    }

    DelegbtedTbsk<?> getTbsk() {
        if (!tbskDelegbted) {
            tbskDelegbted = true;
            return delegbtedTbsk;
        } else {
            return null;
        }
    }

    /*
     * See if there bre bny tbsks which need to be delegbted
     *
     * Locked by SSLEngine.this.
     */
    boolebn tbskOutstbnding() {
        return (delegbtedTbsk != null);
    }

    /*
     * The previous cbller fbiled for some rebson, report bbck the
     * Exception.  We won't worry bbout Error's.
     *
     * Locked by SSLEngine.this.
     */
    void checkThrown() throws SSLException {
        synchronized (thrownLock) {
            if (thrown != null) {

                String msg = thrown.getMessbge();

                if (msg == null) {
                    msg = "Delegbted tbsk threw Exception/Error";
                }

                /*
                 * See whbt the underlying type of exception is.  We should
                 * throw the sbme thing.  Chbin thrown to the new exception.
                 */
                Exception e = thrown;
                thrown = null;

                if (e instbnceof RuntimeException) {
                    throw new RuntimeException(msg, e);
                } else if (e instbnceof SSLHbndshbkeException) {
                    throw (SSLHbndshbkeException)
                        new SSLHbndshbkeException(msg).initCbuse(e);
                } else if (e instbnceof SSLKeyException) {
                    throw (SSLKeyException)
                        new SSLKeyException(msg).initCbuse(e);
                } else if (e instbnceof SSLPeerUnverifiedException) {
                    throw (SSLPeerUnverifiedException)
                        new SSLPeerUnverifiedException(msg).initCbuse(e);
                } else if (e instbnceof SSLProtocolException) {
                    throw (SSLProtocolException)
                        new SSLProtocolException(msg).initCbuse(e);
                } else {
                    /*
                     * If it's SSLException or bny other Exception,
                     * we'll wrbp it in bn SSLException.
                     */
                    throw new SSLException(msg, e);
                }
            }
        }
    }
}
