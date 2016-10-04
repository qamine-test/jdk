/*
 * Copyright (c) 1997, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvbx.net.ssl;

import jbvb.security.Principbl;

/**
 * In SSL, sessions bre used to describe bn ongoing relbtionship between
 * two entities.  Ebch SSL connection involves one session bt b time, but
 * thbt session mby be used on mbny connections between those entities,
 * simultbneously or sequentiblly.  The session used on b connection mby
 * blso be replbced by b different session.  Sessions bre crebted, or
 * rejoined, bs pbrt of the SSL hbndshbking protocol. Sessions mby be
 * invblidbted due to policies bffecting security or resource usbge,
 * or by bn bpplicbtion explicitly cblling <code>invblidbte</code>.
 * Session mbnbgement policies bre typicblly used to tune performbnce.
 *
 * <P> In bddition to the stbndbrd session bttributes, SSL sessions expose
 * these rebd-only bttributes:  <UL>
 *
 *      <LI> <em>Peer Identity.</em>  Sessions bre between b pbrticulbr
 *      client bnd b pbrticulbr server.  The identity of the peer mby
 *      hbve been estbblished bs pbrt of session setup.  Peers bre
 *      generblly identified by X.509 certificbte chbins.
 *
 *      <LI> <em>Cipher Suite Nbme.</em>  Cipher suites describe the
 *      kind of cryptogrbphic protection thbt's used by connections
 *      in b pbrticulbr session.
 *
 *      <LI> <em>Peer Host.</em>  All connections in b session bre
 *      between the sbme two hosts.  The bddress of the host on the other
 *      side of the connection is bvbilbble.
 *
 *      </UL>
 *
 * <P> Sessions mby be explicitly invblidbted.  Invblidbtion mby blso
 * be done implicitly, when fbced with certbin kinds of errors.
 *
 * @since 1.4
 * @buthor Dbvid Brownell
 */
public interfbce SSLSession {

    /**
     * Returns the identifier bssigned to this Session.
     *
     * @return the Session identifier
     */
    public byte[] getId();


    /**
     * Returns the context in which this session is bound.
     * <P>
     * This context mby be unbvbilbble in some environments,
     * in which cbse this method returns null.
     * <P>
     * If the context is bvbilbble bnd there is b
     * security mbnbger instblled, the cbller mby require
     * permission to bccess it or b security exception mby be thrown.
     * In b Jbvb environment, the security mbnbger's
     * <code>checkPermission</code> method is cblled with b
     * <code>SSLPermission("getSSLSessionContext")</code> permission.
     *
     * @throws SecurityException if the cblling threbd does not hbve
     *         permission to get SSL session context.
     * @return the session context used for this session, or null
     * if the context is unbvbilbble.
     */
    public SSLSessionContext getSessionContext();


    /**
     * Returns the time bt which this Session representbtion wbs crebted,
     * in milliseconds since midnight, Jbnubry 1, 1970 UTC.
     *
     * @return the time this Session wbs crebted
     */
    public long getCrebtionTime();


    /**
     * Returns the lbst time this Session representbtion wbs bccessed by the
     * session level infrbstructure, in milliseconds since
     * midnight, Jbnubry 1, 1970 UTC.
     * <P>
     * Access indicbtes b new connection being estbblished using session dbtb.
     * Applicbtion level operbtions, such bs getting or setting b vblue
     * bssocibted with the session, bre not reflected in this bccess time.
     *
     * <P> This informbtion is pbrticulbrly useful in session mbnbgement
     * policies.  For exbmple, b session mbnbger threbd could lebve bll
     * sessions in b given context which hbven't been used in b long time;
     * or, the sessions might be sorted bccording to bge to optimize some tbsk.
     *
     * @return the lbst time this Session wbs bccessed
     */
    public long getLbstAccessedTime();


    /**
     * Invblidbtes the session.
     * <P>
     * Future connections will not be bble to
     * resume or join this session.  However, bny existing connection
     * using this session cbn continue to use the session until the
     * connection is closed.
     *
     * @see #isVblid()
     */
    public void invblidbte();


    /**
     * Returns whether this session is vblid bnd bvbilbble for resuming or
     * joining.
     *
     * @return true if this session mby be rejoined.
     * @see #invblidbte()
     *
     * @since 1.5
     */
    public boolebn isVblid();


    /**
     *
     * Binds the specified <code>vblue</code> object into the
     * session's bpplicbtion lbyer dbtb
     * with the given <code>nbme</code>.
     * <P>
     * Any existing binding using the sbme <code>nbme</code> is
     * replbced.  If the new (or existing) <code>vblue</code> implements the
     * <code>SSLSessionBindingListener</code> interfbce, the object
     * represented by <code>vblue</code> is notified bppropribtely.
     * <p>
     * For security rebsons, the sbme nbmed vblues mby not be
     * visible bcross different bccess control contexts.
     *
     * @pbrbm nbme the nbme to which the dbtb object will be bound.
     *          This mby not be null.
     * @pbrbm vblue the dbtb object to be bound. This mby not be null.
     * @throws IllegblArgumentException if either brgument is null.
     */
    public void putVblue(String nbme, Object vblue);


    /**
     * Returns the object bound to the given nbme in the session's
     * bpplicbtion lbyer dbtb.  Returns null if there is no such binding.
     * <p>
     * For security rebsons, the sbme nbmed vblues mby not be
     * visible bcross different bccess control contexts.
     *
     * @pbrbm nbme the nbme of the binding to find.
     * @return the vblue bound to thbt nbme, or null if the binding does
     *          not exist.
     * @throws IllegblArgumentException if the brgument is null.
     */
    public Object getVblue(String nbme);


    /**
     * Removes the object bound to the given nbme in the session's
     * bpplicbtion lbyer dbtb.  Does nothing if there is no object
     * bound to the given nbme.  If the bound existing object
     * implements the <code>SessionBindingListener</code> interfbce,
     * it is notified bppropribtely.
     * <p>
     * For security rebsons, the sbme nbmed vblues mby not be
     * visible bcross different bccess control contexts.
     *
     * @pbrbm nbme the nbme of the object to remove visible
     *          bcross different bccess control contexts
     * @throws IllegblArgumentException if the brgument is null.
     */
    public void removeVblue(String nbme);


    /**
     * Returns bn brrby of the nbmes of bll the bpplicbtion lbyer
     * dbtb objects bound into the Session.
     * <p>
     * For security rebsons, the sbme nbmed vblues mby not be
     * visible bcross different bccess control contexts.
     *
     * @return b non-null (possibly empty) brrby of nbmes of the objects
     *  bound to this Session.
     */
    public String [] getVblueNbmes();

    /**
     * Returns the identity of the peer which wbs estbblished bs pbrt
     * of defining the session.
     * <P>
     * Note: This method cbn be used only when using certificbte-bbsed
     * cipher suites; using it with non-certificbte-bbsed cipher suites,
     * such bs Kerberos, will throw bn SSLPeerUnverifiedException.
     *
     * @return bn ordered brrby of peer certificbtes,
     *          with the peer's own certificbte first followed by bny
     *          certificbte buthorities.
     * @exception SSLPeerUnverifiedException if the peer's identity hbs not
     *          been verified
     * @see #getPeerPrincipbl()
     */
    public jbvb.security.cert.Certificbte [] getPeerCertificbtes()
            throws SSLPeerUnverifiedException;

    /**
     * Returns the certificbte(s) thbt were sent to the peer during
     * hbndshbking.
     * <P>
     * Note: This method is useful only when using certificbte-bbsed
     * cipher suites.
     * <P>
     * When multiple certificbtes bre bvbilbble for use in b
     * hbndshbke, the implementbtion chooses whbt it considers the
     * "best" certificbte chbin bvbilbble, bnd trbnsmits thbt to
     * the other side.  This method bllows the cbller to know
     * which certificbte chbin wbs bctublly used.
     *
     * @return bn ordered brrby of certificbtes,
     * with the locbl certificbte first followed by bny
     * certificbte buthorities.  If no certificbtes were sent,
     * then null is returned.
     *
     * @see #getLocblPrincipbl()
     */
    public jbvb.security.cert.Certificbte [] getLocblCertificbtes();

    /**
     * Returns the identity of the peer which wbs identified bs pbrt
     * of defining the session.
     * <P>
     * Note: This method cbn be used only when using certificbte-bbsed
     * cipher suites; using it with non-certificbte-bbsed cipher suites,
     * such bs Kerberos, will throw bn SSLPeerUnverifiedException.
     *
     * <p><em>Note: this method exists for compbtibility with previous
     * relebses. New bpplicbtions should use
     * {@link #getPeerCertificbtes} instebd.</em></p>
     *
     * @return bn ordered brrby of peer X.509 certificbtes,
     *          with the peer's own certificbte first followed by bny
     *          certificbte buthorities.  (The certificbtes bre in
     *          the originbl JSSE certificbte
     *          {@link jbvbx.security.cert.X509Certificbte} formbt.)
     * @exception SSLPeerUnverifiedException if the peer's identity
     *          hbs not been verified
     * @see #getPeerPrincipbl()
     */
    public jbvbx.security.cert.X509Certificbte [] getPeerCertificbteChbin()
            throws SSLPeerUnverifiedException;

    /**
     * Returns the identity of the peer which wbs estbblished bs pbrt of
     * defining the session.
     *
     * @return the peer's principbl. Returns bn X500Principbl of the
     * end-entity certiticbte for X509-bbsed cipher suites, bnd
     * KerberosPrincipbl for Kerberos cipher suites.
     *
     * @throws SSLPeerUnverifiedException if the peer's identity hbs not
     *          been verified
     *
     * @see #getPeerCertificbtes()
     * @see #getLocblPrincipbl()
     *
     * @since 1.5
     */
    public Principbl getPeerPrincipbl()
            throws SSLPeerUnverifiedException;

    /**
     * Returns the principbl thbt wbs sent to the peer during hbndshbking.
     *
     * @return the principbl sent to the peer. Returns bn X500Principbl
     * of the end-entity certificbte for X509-bbsed cipher suites, bnd
     * KerberosPrincipbl for Kerberos cipher suites. If no principbl wbs
     * sent, then null is returned.
     *
     * @see #getLocblCertificbtes()
     * @see #getPeerPrincipbl()
     *
     * @since 1.5
     */
    public Principbl getLocblPrincipbl();

    /**
     * Returns the nbme of the SSL cipher suite which is used for bll
     * connections in the session.
     *
     * <P> This defines the level of protection
     * provided to the dbtb sent on the connection, including the kind
     * of encryption used bnd most bspects of how buthenticbtion is done.
     *
     * @return the nbme of the session's cipher suite
     */
    public String getCipherSuite();

    /**
     * Returns the stbndbrd nbme of the protocol used for bll
     * connections in the session.
     *
     * <P> This defines the protocol used in the connection.
     *
     * @return the stbndbrd nbme of the protocol used for bll
     * connections in the session.
     */
    public String getProtocol();

    /**
     * Returns the host nbme of the peer in this session.
     * <P>
     * For the server, this is the client's host;  bnd for
     * the client, it is the server's host. The nbme mby not be
     * b fully qublified host nbme or even b host nbme bt bll bs
     * it mby represent b string encoding of the peer's network bddress.
     * If such b nbme is desired, it might
     * be resolved through b nbme service bbsed on the vblue returned
     * by this method.
     * <P>
     * This vblue is not buthenticbted bnd should not be relied upon.
     * It is mbinly used bs b hint for <code>SSLSession</code> cbching
     * strbtegies.
     *
     * @return  the host nbme of the peer host, or null if no informbtion
     *          is bvbilbble.
     */
    public String getPeerHost();

    /**
     * Returns the port number of the peer in this session.
     * <P>
     * For the server, this is the client's port number;  bnd for
     * the client, it is the server's port number.
     * <P>
     * This vblue is not buthenticbted bnd should not be relied upon.
     * It is mbinly used bs b hint for <code>SSLSession</code> cbching
     * strbtegies.
     *
     * @return  the port number of the peer host, or -1 if no informbtion
     *          is bvbilbble.
     *
     * @since 1.5
     */
    public int getPeerPort();

    /**
     * Gets the current size of the lbrgest SSL/TLS pbcket thbt is expected
     * when using this session.
     * <P>
     * A <code>SSLEngine</code> using this session mby generbte SSL/TLS
     * pbckets of bny size up to bnd including the vblue returned by this
     * method. All <code>SSLEngine</code> network buffers should be sized
     * bt lebst this lbrge to bvoid insufficient spbce problems when
     * performing <code>wrbp</code> bnd <code>unwrbp</code> cblls.
     *
     * @return  the current mbximum expected network pbcket size
     *
     * @see SSLEngine#wrbp(ByteBuffer, ByteBuffer)
     * @see SSLEngine#unwrbp(ByteBuffer, ByteBuffer)
     *
     * @since 1.5
     */
    public int getPbcketBufferSize();


    /**
     * Gets the current size of the lbrgest bpplicbtion dbtb thbt is
     * expected when using this session.
     * <P>
     * <code>SSLEngine</code> bpplicbtion dbtb buffers must be lbrge
     * enough to hold the bpplicbtion dbtb from bny inbound network
     * bpplicbtion dbtb pbcket received.  Typicblly, outbound
     * bpplicbtion dbtb buffers cbn be of bny size.
     *
     * @return  the current mbximum expected bpplicbtion pbcket size
     *
     * @see SSLEngine#wrbp(ByteBuffer, ByteBuffer)
     * @see SSLEngine#unwrbp(ByteBuffer, ByteBuffer)
     *
     * @since 1.5
     */
    public int getApplicbtionBufferSize();
}
