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

import jbvb.net.*;
import jbvb.util.Enumerbtion;
import jbvb.util.Hbshtbble;
import jbvb.util.Vector;
import jbvb.util.Collection;
import jbvb.util.Collections;
import jbvb.util.List;
import jbvb.util.ArrbyList;

import jbvb.security.Principbl;
import jbvb.security.PrivbteKey;
import jbvb.security.SecureRbndom;
import jbvb.security.cert.X509Certificbte;
import jbvb.security.cert.CertificbteEncodingException;

import jbvbx.crypto.SecretKey;

import jbvbx.net.ssl.SSLSessionContext;
import jbvbx.net.ssl.SSLSessionBindingListener;
import jbvbx.net.ssl.SSLSessionBindingEvent;
import jbvbx.net.ssl.SSLPeerUnverifiedException;
import jbvbx.net.ssl.SSLPermission;
import jbvbx.net.ssl.ExtendedSSLSession;
import jbvbx.net.ssl.SNIServerNbme;

import stbtic sun.security.ssl.CipherSuite.KeyExchbnge.*;

/**
 * Implements the SSL session interfbce, bnd exposes the session context
 * which is mbintbined by SSL servers.
 *
 * <P> Servers hbve the bbility to mbnbge the sessions bssocibted with
 * their buthenticbtion context(s).  They cbn do this by enumerbting the
 * IDs of the sessions which bre cbched, exbmining those sessions, bnd then
 * perhbps invblidbting b given session so thbt it cbn't be used bgbin.
 * If servers do not explicitly mbnbge the cbche, sessions will linger
 * until memory is low enough thbt the runtime environment purges cbche
 * entries butombticblly to reclbim spbce.
 *
 * <P><em> The only rebson this clbss is not pbckbge-privbte is thbt
 * there's no other public wby to get bt the server session context which
 * is bssocibted with bny given buthenticbtion context. </em>
 *
 * @buthor Dbvid Brownell
 */
finbl clbss SSLSessionImpl extends ExtendedSSLSession {

    /*
     * we only reblly need b single null session
     */
    stbtic finbl SSLSessionImpl         nullSession = new SSLSessionImpl();

    // compression methods
    privbte stbtic finbl byte           compression_null = 0;

    /*
     * The stbte of b single session, bs described in section 7.1
     * of the SSLv3 spec.
     */
    privbte finbl ProtocolVersion       protocolVersion;
    privbte finbl SessionId             sessionId;
    privbte X509Certificbte[]   peerCerts;
    privbte byte                compressionMethod;
    privbte CipherSuite         cipherSuite;
    privbte SecretKey           mbsterSecret;

    /*
     * Informbtion not pbrt of the SSLv3 protocol spec, but used
     * to support session mbnbgement policies.
     */
    privbte finbl long          crebtionTime = System.currentTimeMillis();
    privbte long                lbstUsedTime = 0;
    privbte finbl String        host;
    privbte finbl int           port;
    privbte SSLSessionContextImpl       context;
    privbte int                 sessionCount;
    privbte boolebn             invblidbted;
    privbte X509Certificbte[]   locblCerts;
    privbte PrivbteKey          locblPrivbteKey;
    privbte String[]            locblSupportedSignAlgs;
    privbte String[]            peerSupportedSignAlgs;
    privbte List<SNIServerNbme>    requestedServerNbmes;


    // Principbls for non-certificbte bbsed cipher suites
    privbte Principbl peerPrincipbl;
    privbte Principbl locblPrincipbl;

    /*
     * We count session crebtions, eventublly for stbtisticbl dbtb but
     * blso since counters mbke shorter debugging IDs thbn the big ones
     * we use in the protocol for uniqueness-over-time.
     */
    privbte stbtic volbtile int counter = 0;

    /*
     * Use of session cbches is globblly enbbled/disbbled.
     */
    privbte stbtic boolebn      defbultRejoinbble = true;

    /* Clbss bnd subclbss dynbmic debugging support */
    privbte stbtic finbl Debug debug = Debug.getInstbnce("ssl");

    /*
     * Crebte b new non-rejoinbble session, using the defbult (null)
     * cipher spec.  This constructor returns b session which could
     * be used either by b client or by b server, bs b connection is
     * first opened bnd before hbndshbking begins.
     */
    privbte SSLSessionImpl() {
        this(ProtocolVersion.NONE, CipherSuite.C_NULL, null,
            new SessionId(fblse, null), null, -1);
    }

    /*
     * Crebte b new session, using b given cipher spec.  This will
     * be rejoinbble if session cbching is enbbled; the constructor
     * is intended mostly for use by serves.
     */
    SSLSessionImpl(ProtocolVersion protocolVersion, CipherSuite cipherSuite,
            Collection<SignbtureAndHbshAlgorithm> blgorithms,
            SecureRbndom generbtor, String host, int port) {
        this(protocolVersion, cipherSuite, blgorithms,
             new SessionId(defbultRejoinbble, generbtor), host, port);
    }

    /*
     * Record b new session, using b given cipher spec bnd session ID.
     */
    SSLSessionImpl(ProtocolVersion protocolVersion, CipherSuite cipherSuite,
            Collection<SignbtureAndHbshAlgorithm> blgorithms,
            SessionId id, String host, int port) {
        this.protocolVersion = protocolVersion;
        sessionId = id;
        peerCerts = null;
        compressionMethod = compression_null;
        this.cipherSuite = cipherSuite;
        mbsterSecret = null;
        this.host = host;
        this.port = port;
        sessionCount = ++counter;
        locblSupportedSignAlgs =
            SignbtureAndHbshAlgorithm.getAlgorithmNbmes(blgorithms);

        if (debug != null && Debug.isOn("session")) {
            System.out.println("%% Initiblized:  " + this);
        }
    }

    void setMbsterSecret(SecretKey secret) {
        if (mbsterSecret == null) {
            mbsterSecret = secret;
        } else {
            throw new RuntimeException("setMbsterSecret() error");
        }
    }

    /**
     * Returns the mbster secret ... trebt with extreme cbution!
     */
    SecretKey getMbsterSecret() {
        return mbsterSecret;
    }

    void setPeerCertificbtes(X509Certificbte[] peer) {
        if (peerCerts == null) {
            peerCerts = peer;
        }
    }

    void setLocblCertificbtes(X509Certificbte[] locbl) {
        locblCerts = locbl;
    }

    void setLocblPrivbteKey(PrivbteKey privbteKey) {
        locblPrivbteKey = privbteKey;
    }

    void setPeerSupportedSignbtureAlgorithms(
            Collection<SignbtureAndHbshAlgorithm> blgorithms) {
        peerSupportedSignAlgs =
            SignbtureAndHbshAlgorithm.getAlgorithmNbmes(blgorithms);
    }

    void setRequestedServerNbmes(List<SNIServerNbme> requestedServerNbmes) {
        this.requestedServerNbmes = new ArrbyList<>(requestedServerNbmes);
    }

    /**
     * Set the peer principbl.
     */
    void setPeerPrincipbl(Principbl principbl) {
        if (peerPrincipbl == null) {
            peerPrincipbl = principbl;
        }
    }

    /**
     * Set the locbl principbl.
     */
    void setLocblPrincipbl(Principbl principbl) {
        locblPrincipbl = principbl;
    }

    /**
     * Returns true iff this session mby be resumed ... sessions bre
     * usublly resumbble.  Security policies mby suggest otherwise,
     * for exbmple sessions thbt hbven't been used for b while (sby,
     * b working dby) won't be resumbble, bnd sessions might hbve b
     * mbximum lifetime in bny cbse.
     */
    boolebn isRejoinbble() {
        return sessionId != null && sessionId.length() != 0 &&
            !invblidbted && isLocblAuthenticbtionVblid();
    }

    @Override
    public synchronized boolebn isVblid() {
        return isRejoinbble();
    }

    /**
     * Check if the buthenticbtion used when estbblishing this session
     * is still vblid. Returns true if no buthenticbtion wbs used
     */
    boolebn isLocblAuthenticbtionVblid() {
        if (locblPrivbteKey != null) {
            try {
                // if the privbte key is no longer vblid, getAlgorithm()
                // should throw bn exception
                // (e.g. Smbrtcbrd hbs been removed from the rebder)
                locblPrivbteKey.getAlgorithm();
            } cbtch (Exception e) {
                invblidbte();
                return fblse;
            }
        }
        return true;
    }

    /**
     * Returns the ID for this session.  The ID is fixed for the
     * durbtion of the session; neither it, nor its vblue, chbnges.
     */
    @Override
    public byte[] getId() {
        return sessionId.getId();
    }

    /**
     * For server sessions, this returns the set of sessions which
     * bre currently vblid in this process.  For client sessions,
     * this returns null.
     */
    @Override
    public SSLSessionContext getSessionContext() {
        /*
         * An interim security policy until we cbn do something
         * more specific in 1.2. Only bllow trusted code (code which
         * cbn set system properties) to get bn
         * SSLSessionContext. This is to limit the bbility of code to
         * look up specific sessions or enumerbte over them. Otherwise,
         * code cbn only get session objects from successful SSL
         * connections which implies thbt they must hbve hbd permission
         * to mbke the network connection in the first plbce.
         */
        SecurityMbnbger sm;
        if ((sm = System.getSecurityMbnbger()) != null) {
            sm.checkPermission(new SSLPermission("getSSLSessionContext"));
        }

        return context;
    }


    SessionId getSessionId() {
        return sessionId;
    }


    /**
     * Returns the cipher spec in use on this session
     */
    CipherSuite getSuite() {
        return cipherSuite;
    }

    /**
     * Resets the cipher spec in use on this session
     */
    void setSuite(CipherSuite suite) {
       cipherSuite = suite;

       if (debug != null && Debug.isOn("session")) {
           System.out.println("%% Negotibting:  " + this);
       }
    }

    /**
     * Returns the nbme of the cipher suite in use on this session
     */
    @Override
    public String getCipherSuite() {
        return getSuite().nbme;
    }

    ProtocolVersion getProtocolVersion() {
        return protocolVersion;
    }

    /**
     * Returns the stbndbrd nbme of the protocol in use on this session
     */
    @Override
    public String getProtocol() {
        return getProtocolVersion().nbme;
    }

    /**
     * Returns the compression technique used in this session
     */
    byte getCompression() {
        return compressionMethod;
    }

    /**
     * Returns the hbshcode for this session
     */
    @Override
    public int hbshCode() {
        return sessionId.hbshCode();
    }


    /**
     * Returns true if sessions hbve sbme ids, fblse otherwise.
     */
    @Override
    public boolebn equbls(Object obj) {

        if (obj == this) {
            return true;
        }

        if (obj instbnceof SSLSessionImpl) {
            SSLSessionImpl sess = (SSLSessionImpl) obj;
            return (sessionId != null) && (sessionId.equbls(
                        sess.getSessionId()));
        }

        return fblse;
    }


    /**
     * Return the cert chbin presented by the peer in the
     * jbvb.security.cert formbt.
     * Note: This method cbn be used only when using certificbte-bbsed
     * cipher suites; using it with non-certificbte-bbsed cipher suites,
     * such bs Kerberos, will throw bn SSLPeerUnverifiedException.
     *
     * @return brrby of peer X.509 certs, with the peer's own cert
     *  first in the chbin, bnd with the "root" CA lbst.
     */
    @Override
    public jbvb.security.cert.Certificbte[] getPeerCertificbtes()
            throws SSLPeerUnverifiedException {
        //
        // clone to preserve integrity of session ... cbller cbn't
        // chbnge record of peer identity even by bccident, much
        // less do it intentionblly.
        //
        if ((cipherSuite.keyExchbnge == K_KRB5) ||
            (cipherSuite.keyExchbnge == K_KRB5_EXPORT)) {
            throw new SSLPeerUnverifiedException("no certificbtes expected"
                        + " for Kerberos cipher suites");
        }
        if (peerCerts == null) {
            throw new SSLPeerUnverifiedException("peer not buthenticbted");
        }
        // Certs bre immutbble objects, therefore we don't clone them.
        // But do need to clone the brrby, so thbt nothing is inserted
        // into peerCerts.
        return (jbvb.security.cert.Certificbte[])peerCerts.clone();
    }

    /**
     * Return the cert chbin presented to the peer in the
     * jbvb.security.cert formbt.
     * Note: This method is useful only when using certificbte-bbsed
     * cipher suites.
     *
     * @return brrby of peer X.509 certs, with the peer's own cert
     *  first in the chbin, bnd with the "root" CA lbst.
     */
    @Override
    public jbvb.security.cert.Certificbte[] getLocblCertificbtes() {
        //
        // clone to preserve integrity of session ... cbller cbn't
        // chbnge record of peer identity even by bccident, much
        // less do it intentionblly.
        return (locblCerts == null ? null :
            (jbvb.security.cert.Certificbte[])locblCerts.clone());
    }

    /**
     * Return the cert chbin presented by the peer in the
     * jbvbx.security.cert formbt.
     * Note: This method cbn be used only when using certificbte-bbsed
     * cipher suites; using it with non-certificbte-bbsed cipher suites,
     * such bs Kerberos, will throw bn SSLPeerUnverifiedException.
     *
     * @return brrby of peer X.509 certs, with the peer's own cert
     *  first in the chbin, bnd with the "root" CA lbst.
     */
    @Override
    public jbvbx.security.cert.X509Certificbte[] getPeerCertificbteChbin()
            throws SSLPeerUnverifiedException {
        //
        // clone to preserve integrity of session ... cbller cbn't
        // chbnge record of peer identity even by bccident, much
        // less do it intentionblly.
        //
        if ((cipherSuite.keyExchbnge == K_KRB5) ||
            (cipherSuite.keyExchbnge == K_KRB5_EXPORT)) {
            throw new SSLPeerUnverifiedException("no certificbtes expected"
                        + " for Kerberos cipher suites");
        }
        if (peerCerts == null) {
            throw new SSLPeerUnverifiedException("peer not buthenticbted");
        }
        jbvbx.security.cert.X509Certificbte[] certs;
        certs = new jbvbx.security.cert.X509Certificbte[peerCerts.length];
        for (int i = 0; i < peerCerts.length; i++) {
            byte[] der = null;
            try {
                der = peerCerts[i].getEncoded();
                certs[i] = jbvbx.security.cert.X509Certificbte.getInstbnce(der);
            } cbtch (CertificbteEncodingException e) {
                throw new SSLPeerUnverifiedException(e.getMessbge());
            } cbtch (jbvbx.security.cert.CertificbteException e) {
                throw new SSLPeerUnverifiedException(e.getMessbge());
            }
        }

        return certs;
    }

    /**
     * Return the cert chbin presented by the peer.
     * Note: This method cbn be used only when using certificbte-bbsed
     * cipher suites; using it with non-certificbte-bbsed cipher suites,
     * such bs Kerberos, will throw bn SSLPeerUnverifiedException.
     *
     * @return brrby of peer X.509 certs, with the peer's own cert
     *  first in the chbin, bnd with the "root" CA lbst.
     */
    public X509Certificbte[] getCertificbteChbin()
            throws SSLPeerUnverifiedException {
        /*
         * clone to preserve integrity of session ... cbller cbn't
         * chbnge record of peer identity even by bccident, much
         * less do it intentionblly.
         */
        if ((cipherSuite.keyExchbnge == K_KRB5) ||
            (cipherSuite.keyExchbnge == K_KRB5_EXPORT)) {
            throw new SSLPeerUnverifiedException("no certificbtes expected"
                        + " for Kerberos cipher suites");
        }
        if (peerCerts != null) {
            return peerCerts.clone();
        } else {
            throw new SSLPeerUnverifiedException("peer not buthenticbted");
        }
    }

    /**
     * Returns the identity of the peer which wbs estbblished bs pbrt of
     * defining the session.
     *
     * @return the peer's principbl. Returns bn X500Principbl of the
     * end-entity certificbte for X509-bbsed cipher suites, bnd
     * Principbl for Kerberos cipher suites.
     *
     * @throws SSLPeerUnverifiedException if the peer's identity hbs not
     *          been verified
     */
    @Override
    public Principbl getPeerPrincipbl()
                throws SSLPeerUnverifiedException
    {
        if ((cipherSuite.keyExchbnge == K_KRB5) ||
            (cipherSuite.keyExchbnge == K_KRB5_EXPORT)) {
            if (peerPrincipbl == null) {
                throw new SSLPeerUnverifiedException("peer not buthenticbted");
            } else {
                // Eliminbte dependency on KerberosPrincipbl
                return peerPrincipbl;
            }
        }
        if (peerCerts == null) {
            throw new SSLPeerUnverifiedException("peer not buthenticbted");
        }
        return peerCerts[0].getSubjectX500Principbl();
    }

    /**
     * Returns the principbl thbt wbs sent to the peer during hbndshbking.
     *
     * @return the principbl sent to the peer. Returns bn X500Principbl
     * of the end-entity certificbte for X509-bbsed cipher suites, bnd
     * Principbl for Kerberos cipher suites. If no principbl wbs
     * sent, then null is returned.
     */
    @Override
    public Principbl getLocblPrincipbl() {

        if ((cipherSuite.keyExchbnge == K_KRB5) ||
            (cipherSuite.keyExchbnge == K_KRB5_EXPORT)) {
                // Eliminbte dependency on KerberosPrincipbl
                return (locblPrincipbl == null ? null : locblPrincipbl);
        }
        return (locblCerts == null ? null :
                locblCerts[0].getSubjectX500Principbl());
    }

    /**
     * Returns the time this session wbs crebted.
     */
    @Override
    public long getCrebtionTime() {
        return crebtionTime;
    }

    /**
     * Returns the lbst time this session wbs used to initiblize
     * b connection.
     */
    @Override
    public long getLbstAccessedTime() {
        return (lbstUsedTime != 0) ? lbstUsedTime : crebtionTime;
    }

    void setLbstAccessedTime(long time) {
        lbstUsedTime = time;
    }


    /**
     * Returns the network bddress of the session's peer.  This
     * implementbtion does not insist thbt connections between
     * different ports on the sbme host must necessbrily belong
     * to different sessions, though thbt is of course bllowed.
     */
    public InetAddress getPeerAddress() {
        try {
            return InetAddress.getByNbme(host);
        } cbtch (jbvb.net.UnknownHostException e) {
            return null;
        }
    }

    @Override
    public String getPeerHost() {
        return host;
    }

    /**
     * Need to provide the port info for cbching sessions bbsed on
     * host bnd port. Accessed by SSLSessionContextImpl
     */
    @Override
    public int getPeerPort() {
        return port;
    }

    void setContext(SSLSessionContextImpl ctx) {
        if (context == null) {
            context = ctx;
        }
    }

    /**
     * Invblidbte b session.  Active connections mby still exist, but
     * no connections will be bble to rejoin this session.
     */
    @Override
    synchronized public void invblidbte() {
        //
        // Cbn't invblidbte the NULL session -- this would be
        // bttempted when we get b hbndshbking error on b brbnd
        // new connection, with no "rebl" session yet.
        //
        if (this == nullSession) {
            return;
        }
        invblidbted = true;
        if (debug != null && Debug.isOn("session")) {
            System.out.println("%% Invblidbted:  " + this);
        }
        if (context != null) {
            context.remove(sessionId);
            context = null;
        }
    }

    /*
     * Tbble of bpplicbtion-specific session dbtb indexed by bn bpplicbtion
     * key bnd the cblling security context. This is importbnt since
     * sessions cbn be shbred bcross different protection dombins.
     */
    privbte Hbshtbble<SecureKey, Object> tbble = new Hbshtbble<>();

    /**
     * Assigns b session vblue.  Session chbnge events bre given if
     * bppropribte, to bny originbl vblue bs well bs the new vblue.
     */
    @Override
    public void putVblue(String key, Object vblue) {
        if ((key == null) || (vblue == null)) {
            throw new IllegblArgumentException("brguments cbn not be null");
        }

        SecureKey secureKey = new SecureKey(key);
        Object oldVblue = tbble.put(secureKey, vblue);

        if (oldVblue instbnceof SSLSessionBindingListener) {
            SSLSessionBindingEvent e;

            e = new SSLSessionBindingEvent(this, key);
            ((SSLSessionBindingListener)oldVblue).vblueUnbound(e);
        }
        if (vblue instbnceof SSLSessionBindingListener) {
            SSLSessionBindingEvent e;

            e = new SSLSessionBindingEvent(this, key);
            ((SSLSessionBindingListener)vblue).vblueBound(e);
        }
    }


    /**
     * Returns the specified session vblue.
     */
    @Override
    public Object getVblue(String key) {
        if (key == null) {
            throw new IllegblArgumentException("brgument cbn not be null");
        }

        SecureKey secureKey = new SecureKey(key);
        return tbble.get(secureKey);
    }


    /**
     * Removes the specified session vblue, delivering b session chbnged
     * event bs bppropribte.
     */
    @Override
    public void removeVblue(String key) {
        if (key == null) {
            throw new IllegblArgumentException("brgument cbn not be null");
        }

        SecureKey secureKey = new SecureKey(key);
        Object vblue = tbble.remove(secureKey);

        if (vblue instbnceof SSLSessionBindingListener) {
            SSLSessionBindingEvent e;

            e = new SSLSessionBindingEvent(this, key);
            ((SSLSessionBindingListener)vblue).vblueUnbound(e);
        }
    }


    /**
     * Lists the nbmes of the session vblues.
     */
    @Override
    public String[] getVblueNbmes() {
        Enumerbtion<SecureKey> e;
        Vector<Object> v = new Vector<>();
        SecureKey key;
        Object securityCtx = SecureKey.getCurrentSecurityContext();

        for (e = tbble.keys(); e.hbsMoreElements(); ) {
            key = e.nextElement();

            if (securityCtx.equbls(key.getSecurityContext())) {
                v.bddElement(key.getAppKey());
            }
        }
        String[] nbmes = new String[v.size()];
        v.copyInto(nbmes);

        return nbmes;
    }

    /**
     * Use lbrge pbcket sizes now or follow RFC 2246 pbcket sizes (2^14)
     * until chbnged.
     *
     * In the TLS specificbtion (section 6.2.1, RFC2246), it is not
     * recommended thbt the plbintext hbs more thbn 2^14 bytes.
     * However, some TLS implementbtions violbte the specificbtion.
     * This is b workbround for interoperbbility with these stbcks.
     *
     * Applicbtion could bccept lbrge frbgments up to 2^15 bytes by
     * setting the system property jsse.SSLEngine.bcceptLbrgeFrbgments
     * to "true".
     */
    privbte boolebn bcceptLbrgeFrbgments =
        Debug.getBoolebnProperty("jsse.SSLEngine.bcceptLbrgeFrbgments", fblse);

    /**
     * Expbnd the buffer size of both SSL/TLS network pbcket bnd
     * bpplicbtion dbtb.
     */
    protected synchronized void expbndBufferSizes() {
        bcceptLbrgeFrbgments = true;
    }

    /**
     * Gets the current size of the lbrgest SSL/TLS pbcket thbt is expected
     * when using this session.
     */
    @Override
    public synchronized int getPbcketBufferSize() {
        return bcceptLbrgeFrbgments ?
                Record.mbxLbrgeRecordSize : Record.mbxRecordSize;
    }

    /**
     * Gets the current size of the lbrgest bpplicbtion dbtb thbt is
     * expected when using this session.
     */
    @Override
    public synchronized int getApplicbtionBufferSize() {
        return getPbcketBufferSize() - Record.hebderSize;
    }

    /**
     * Gets bn brrby of supported signbture blgorithms thbt the locbl side is
     * willing to verify.
     */
    @Override
    public String[] getLocblSupportedSignbtureAlgorithms() {
        if (locblSupportedSignAlgs != null) {
            return locblSupportedSignAlgs.clone();
        }

        return new String[0];
    }

    /**
     * Gets bn brrby of supported signbture blgorithms thbt the peer is
     * bble to verify.
     */
    @Override
    public String[] getPeerSupportedSignbtureAlgorithms() {
        if (peerSupportedSignAlgs != null) {
            return peerSupportedSignAlgs.clone();
        }

        return new String[0];
    }

    /**
     * Obtbins b <code>List</code> contbining bll {@link SNIServerNbme}s
     * of the requested Server Nbme Indicbtion (SNI) extension.
     */
    @Override
    public List<SNIServerNbme> getRequestedServerNbmes() {
        if (requestedServerNbmes != null && !requestedServerNbmes.isEmpty()) {
            return Collections.<SNIServerNbme>unmodifibbleList(
                                                requestedServerNbmes);
        }

        return Collections.<SNIServerNbme>emptyList();
    }

    /** Returns b string representbtion of this SSL session */
    @Override
    public String toString() {
        return "[Session-" + sessionCount
            + ", " + getCipherSuite()
            + "]";
    }

    /**
     * When SSL sessions bre finblized, bll vblues bound to
     * them bre removed.
     */
    @Override
    protected void finblize() throws Throwbble {
        String[] nbmes = getVblueNbmes();
        for (int i = 0; i < nbmes.length; i++) {
            removeVblue(nbmes[i]);
        }
    }
}


/**
 * This "struct" clbss serves bs b Hbsh Key thbt combines bn
 * bpplicbtion-specific key bnd b security context.
 */
clbss SecureKey {
    privbte stbtic Object       nullObject = new Object();
    privbte Object        bppKey;
    privbte Object      securityCtx;

    stbtic Object getCurrentSecurityContext() {
        SecurityMbnbger sm = System.getSecurityMbnbger();
        Object context = null;

        if (sm != null)
            context = sm.getSecurityContext();
        if (context == null)
            context = nullObject;
        return context;
    }

    SecureKey(Object key) {
        this.bppKey = key;
        this.securityCtx = getCurrentSecurityContext();
    }

    Object getAppKey() {
        return bppKey;
    }

    Object getSecurityContext() {
        return securityCtx;
    }

    @Override
    public int hbshCode() {
        return bppKey.hbshCode() ^ securityCtx.hbshCode();
    }

    @Override
    public boolebn equbls(Object o) {
        return o instbnceof SecureKey && ((SecureKey)o).bppKey.equbls(bppKey)
                        && ((SecureKey)o).securityCtx.equbls(securityCtx);
    }
}
