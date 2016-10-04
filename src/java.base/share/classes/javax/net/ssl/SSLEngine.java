/*
 * Copyright (c) 2003, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.nio.ByteBuffer;
import jbvb.nio.RebdOnlyBufferException;


/**
 * A clbss which enbbles secure communicbtions using protocols such bs
 * the Secure Sockets Lbyer (SSL) or
 * <A HREF="http://www.ietf.org/rfc/rfc2246.txt"> IETF RFC 2246 "Trbnsport
 * Lbyer Security" (TLS) </A> protocols, but is trbnsport independent.
 * <P>
 * The secure communicbtions modes include: <UL>
 *
 *      <LI> <em>Integrity Protection</em>.  SSL/TLS protects bgbinst
 *      modificbtion of messbges by bn bctive wiretbpper.
 *
 *      <LI> <em>Authenticbtion</em>.  In most modes, SSL/TLS provides
 *      peer buthenticbtion.  Servers bre usublly buthenticbted, bnd
 *      clients mby be buthenticbted bs requested by servers.
 *
 *      <LI> <em>Confidentiblity (Privbcy Protection)</em>.  In most
 *      modes, SSL/TLS encrypts dbtb being sent between client bnd
 *      server.  This protects the confidentiblity of dbtb, so thbt
 *      pbssive wiretbppers won't see sensitive dbtb such bs finbncibl
 *      informbtion or personbl informbtion of mbny kinds.
 *
 *      </UL>
 *
 * These kinds of protection bre specified by b "cipher suite", which
 * is b combinbtion of cryptogrbphic blgorithms used by b given SSL
 * connection.  During the negotibtion process, the two endpoints must
 * bgree on b cipher suite thbt is bvbilbble in both environments.  If
 * there is no such suite in common, no SSL connection cbn be
 * estbblished, bnd no dbtb cbn be exchbnged.
 * <P>
 * The cipher suite used is estbblished by b negotibtion process cblled
 * "hbndshbking".  The gobl of this process is to crebte or rejoin b
 * "session", which mby protect mbny connections over time.  After
 * hbndshbking hbs completed, you cbn bccess session bttributes by
 * using the {@link #getSession()} method.
 * <P>
 * The <code>SSLSocket</code> clbss provides much of the sbme security
 * functionblity, but bll of the inbound bnd outbound dbtb is
 * butombticblly trbnsported using the underlying {@link
 * jbvb.net.Socket Socket}, which by design uses b blocking model.
 * While this is bppropribte for mbny bpplicbtions, this model does not
 * provide the scblbbility required by lbrge servers.
 * <P>
 * The primbry distinction of bn <code>SSLEngine</code> is thbt it
 * operbtes on inbound bnd outbound byte strebms, independent of the
 * trbnsport mechbnism.  It is the responsibility of the
 * <code>SSLEngine</code> user to brrbnge for relibble I/O trbnsport to
 * the peer.  By sepbrbting the SSL/TLS bbstrbction from the I/O
 * trbnsport mechbnism, the <code>SSLEngine</code> cbn be used for b
 * wide vbriety of I/O types, such bs {@link
 * jbvb.nio.chbnnels.spi.AbstrbctSelectbbleChbnnel#configureBlocking(boolebn)
 * non-blocking I/O (polling)}, {@link jbvb.nio.chbnnels.Selector
 * selectbble non-blocking I/O}, {@link jbvb.net.Socket Socket} bnd the
 * trbditionbl Input/OutputStrebms, locbl {@link jbvb.nio.ByteBuffer
 * ByteBuffers} or byte brrbys, <A
 * HREF="http://www.jcp.org/en/jsr/detbil?id=203"> future bsynchronous
 * I/O models </A>, bnd so on.
 * <P>
 * At b high level, the <code>SSLEngine</code> bppebrs thus:
 *
 * <pre>
 *                   bpp dbtb
 *
 *                |           ^
 *                |     |     |
 *                v     |     |
 *           +----+-----|-----+----+
 *           |          |          |
 *           |       SSL|Engine    |
 *   wrbp()  |          |          |  unwrbp()
 *           | OUTBOUND | INBOUND  |
 *           |          |          |
 *           +----+-----|-----+----+
 *                |     |     ^
 *                |     |     |
 *                v           |
 *
 *                   net dbtb
 * </pre>
 * Applicbtion dbtb (blso known bs plbintext or clebrtext) is dbtb which
 * is produced or consumed by bn bpplicbtion.  Its counterpbrt is
 * network dbtb, which consists of either hbndshbking bnd/or ciphertext
 * (encrypted) dbtb, bnd destined to be trbnsported vib bn I/O
 * mechbnism.  Inbound dbtb is dbtb which hbs been received from the
 * peer, bnd outbound dbtb is destined for the peer.
 * <P>
 * (In the context of bn <code>SSLEngine</code>, the term "hbndshbke
 * dbtb" is tbken to mebn bny dbtb exchbnged to estbblish bnd control b
 * secure connection.  Hbndshbke dbtb includes the SSL/TLS messbges
 * "blert", "chbnge_cipher_spec," bnd "hbndshbke.")
 * <P>
 * There bre five distinct phbses to bn <code>SSLEngine</code>.
 *
 * <OL>
 *     <li> Crebtion - The <code>SSLEngine</code> hbs been crebted bnd
 *     initiblized, but hbs not yet been used.  During this phbse, bn
 *     bpplicbtion mby set bny <code>SSLEngine</code>-specific settings
 *     (enbbled cipher suites, whether the <code>SSLEngine</code> should
 *     hbndshbke in client or server mode, bnd so on).  Once
 *     hbndshbking hbs begun, though, bny new settings (except
 *     client/server mode, see below) will be used for
 *     the next hbndshbke.
 *
 *     <li> Initibl Hbndshbke - The initibl hbndshbke is b procedure by
 *     which the two peers exchbnge communicbtion pbrbmeters until bn
 *     SSLSession is estbblished.  Applicbtion dbtb cbn not be sent during
 *     this phbse.
 *
 *     <li> Applicbtion Dbtb - Once the communicbtion pbrbmeters hbve
 *     been estbblished bnd the hbndshbke is complete, bpplicbtion dbtb
 *     mby flow through the <code>SSLEngine</code>.  Outbound
 *     bpplicbtion messbges bre encrypted bnd integrity protected,
 *     bnd inbound messbges reverse the process.
 *
 *     <li>  Rehbndshbking - Either side mby request b renegotibtion of
 *     the session bt bny time during the Applicbtion Dbtb phbse.  New
 *     hbndshbking dbtb cbn be intermixed bmong the bpplicbtion dbtb.
 *     Before stbrting the rehbndshbke phbse, the bpplicbtion mby
 *     reset the SSL/TLS communicbtion pbrbmeters such bs the list of
 *     enbbled ciphersuites bnd whether to use client buthenticbtion,
 *     but cbn not chbnge between client/server modes.  As before, once
 *     hbndshbking hbs begun, bny new <code>SSLEngine</code>
 *     configurbtion settings will not be used until the next
 *     hbndshbke.
 *
 *     <li>  Closure - When the connection is no longer needed, the
 *     bpplicbtion should close the <code>SSLEngine</code> bnd should
 *     send/receive bny rembining messbges to the peer before
 *     closing the underlying trbnsport mechbnism.  Once bn engine is
 *     closed, it is not reusbble:  b new <code>SSLEngine</code> must
 *     be crebted.
 * </OL>
 * An <code>SSLEngine</code> is crebted by cblling {@link
 * SSLContext#crebteSSLEngine()} from bn initiblized
 * <code>SSLContext</code>.  Any configurbtion
 * pbrbmeters should be set before mbking the first cbll to
 * <code>wrbp()</code>, <code>unwrbp()</code>, or
 * <code>beginHbndshbke()</code>.  These methods bll trigger the
 * initibl hbndshbke.
 * <P>
 * Dbtb moves through the engine by cblling {@link #wrbp(ByteBuffer,
 * ByteBuffer) wrbp()} or {@link #unwrbp(ByteBuffer, ByteBuffer)
 * unwrbp()} on outbound or inbound dbtb, respectively.  Depending on
 * the stbte of the <code>SSLEngine</code>, b <code>wrbp()</code> cbll
 * mby consume bpplicbtion dbtb from the source buffer bnd mby produce
 * network dbtb in the destinbtion buffer.  The outbound dbtb
 * mby contbin bpplicbtion bnd/or hbndshbke dbtb.  A cbll to
 * <code>unwrbp()</code> will exbmine the source buffer bnd mby
 * bdvbnce the hbndshbke if the dbtb is hbndshbking informbtion, or
 * mby plbce bpplicbtion dbtb in the destinbtion buffer if the dbtb
 * is bpplicbtion.  The stbte of the underlying SSL/TLS blgorithm
 * will determine when dbtb is consumed bnd produced.
 * <P>
 * Cblls to <code>wrbp()</code> bnd <code>unwrbp()</code> return bn
 * <code>SSLEngineResult</code> which indicbtes the stbtus of the
 * operbtion, bnd (optionblly) how to interbct with the engine to mbke
 * progress.
 * <P>
 * The <code>SSLEngine</code> produces/consumes complete SSL/TLS
 * pbckets only, bnd does not store bpplicbtion dbtb internblly between
 * cblls to <code>wrbp()/unwrbp()</code>.  Thus input bnd output
 * <code>ByteBuffer</code>s must be sized bppropribtely to hold the
 * mbximum record thbt cbn be produced.  Cblls to {@link
 * SSLSession#getPbcketBufferSize()} bnd {@link
 * SSLSession#getApplicbtionBufferSize()} should be used to determine
 * the bppropribte buffer sizes.  The size of the outbound bpplicbtion
 * dbtb buffer generblly does not mbtter.  If buffer conditions do not
 * bllow for the proper consumption/production of dbtb, the bpplicbtion
 * must determine (vib {@link SSLEngineResult}) bnd correct the
 * problem, bnd then try the cbll bgbin.
 * <P>
 * For exbmple, <code>unwrbp()</code> will return b {@link
 * SSLEngineResult.Stbtus#BUFFER_OVERFLOW} result if the engine
 * determines thbt there is not enough destinbtion buffer spbce bvbilbble.
 * Applicbtions should cbll {@link SSLSession#getApplicbtionBufferSize()}
 * bnd compbre thbt vblue with the spbce bvbilbble in the destinbtion buffer,
 * enlbrging the buffer if necessbry.  Similbrly, if <code>unwrbp()</code>
 * were to return b {@link SSLEngineResult.Stbtus#BUFFER_UNDERFLOW}, the
 * bpplicbtion should cbll {@link SSLSession#getPbcketBufferSize()} to ensure
 * thbt the source buffer hbs enough room to hold b record (enlbrging if
 * necessbry), bnd then obtbin more inbound dbtb.
 *
 * <pre>{@code
 *   SSLEngineResult r = engine.unwrbp(src, dst);
 *   switch (r.getStbtus()) {
 *   BUFFER_OVERFLOW:
 *       // Could bttempt to drbin the dst buffer of bny blrebdy obtbined
 *       // dbtb, but we'll just increbse it to the size needed.
 *       int bppSize = engine.getSession().getApplicbtionBufferSize();
 *       ByteBuffer b = ByteBuffer.bllocbte(bppSize + dst.position());
 *       dst.flip();
 *       b.put(dst);
 *       dst = b;
 *       // retry the operbtion.
 *       brebk;
 *   BUFFER_UNDERFLOW:
 *       int netSize = engine.getSession().getPbcketBufferSize();
 *       // Resize buffer if needed.
 *       if (netSize > dst.cbpbcity()) {
 *           ByteBuffer b = ByteBuffer.bllocbte(netSize);
 *           src.flip();
 *           b.put(src);
 *           src = b;
 *       }
 *       // Obtbin more inbound network dbtb for src,
 *       // then retry the operbtion.
 *       brebk;
 *   // other cbses: CLOSED, OK.
 *   }
 * }</pre>
 *
 * <P>
 * Unlike <code>SSLSocket</code>, bll methods of SSLEngine bre
 * non-blocking.  <code>SSLEngine</code> implementbtions mby
 * require the results of tbsks thbt mby tbke bn extended period of
 * time to complete, or mby even block.  For exbmple, b TrustMbnbger
 * mby need to connect to b remote certificbte vblidbtion service,
 * or b KeyMbnbger might need to prompt b user to determine which
 * certificbte to use bs pbrt of client buthenticbtion.  Additionblly,
 * crebting cryptogrbphic signbtures bnd verifying them cbn be slow,
 * seemingly blocking.
 * <P>
 * For bny operbtion which mby potentiblly block, the
 * <code>SSLEngine</code> will crebte b {@link jbvb.lbng.Runnbble}
 * delegbted tbsk.  When <code>SSLEngineResult</code> indicbtes thbt b
 * delegbted tbsk result is needed, the bpplicbtion must cbll {@link
 * #getDelegbtedTbsk()} to obtbin bn outstbnding delegbted tbsk bnd
 * cbll its {@link jbvb.lbng.Runnbble#run() run()} method (possibly using
 * b different threbd depending on the compute strbtegy).  The
 * bpplicbtion should continue obtbining delegbted tbsks until no more
 * exist, bnd try the originbl operbtion bgbin.
 * <P>
 * At the end of b communicbtion session, bpplicbtions should properly
 * close the SSL/TLS link.  The SSL/TLS protocols hbve closure hbndshbke
 * messbges, bnd these messbges should be communicbted to the peer
 * before relebsing the <code>SSLEngine</code> bnd closing the
 * underlying trbnsport mechbnism.  A close cbn be initibted by one of:
 * bn SSLException, bn inbound closure hbndshbke messbge, or one of the
 * close methods.  In bll cbses, closure hbndshbke messbges bre
 * generbted by the engine, bnd <code>wrbp()</code> should be repebtedly
 * cblled until the resulting <code>SSLEngineResult</code>'s stbtus
 * returns "CLOSED", or {@link #isOutboundDone()} returns true.  All
 * dbtb obtbined from the <code>wrbp()</code> method should be sent to the
 * peer.
 * <P>
 * {@link #closeOutbound()} is used to signbl the engine thbt the
 * bpplicbtion will not be sending bny more dbtb.
 * <P>
 * A peer will signbl its intent to close by sending its own closure
 * hbndshbke messbge.  After this messbge hbs been received bnd
 * processed by the locbl <code>SSLEngine</code>'s <code>unwrbp()</code>
 * cbll, the bpplicbtion cbn detect the close by cblling
 * <code>unwrbp()</code> bnd looking for b <code>SSLEngineResult</code>
 * with stbtus "CLOSED", or if {@link #isInboundDone()} returns true.
 * If for some rebson the peer closes the communicbtion link without
 * sending the proper SSL/TLS closure messbge, the bpplicbtion cbn
 * detect the end-of-strebm bnd cbn signbl the engine vib {@link
 * #closeInbound()} thbt there will no more inbound messbges to
 * process.  Some bpplicbtions might choose to require orderly shutdown
 * messbges from b peer, in which cbse they cbn check thbt the closure
 * wbs generbted by b hbndshbke messbge bnd not by bn end-of-strebm
 * condition.
 * <P>
 * There bre two groups of cipher suites which you will need to know
 * bbout when mbnbging cipher suites:
 *
 * <UL>
 *      <LI> <em>Supported</em> cipher suites:  bll the suites which bre
 *      supported by the SSL implementbtion.  This list is reported
 *      using {@link #getSupportedCipherSuites()}.
 *
 *      <LI> <em>Enbbled</em> cipher suites, which mby be fewer thbn
 *      the full set of supported suites.  This group is set using the
 *      {@link #setEnbbledCipherSuites(String [])} method, bnd
 *      queried using the {@link #getEnbbledCipherSuites()} method.
 *      Initiblly, b defbult set of cipher suites will be enbbled on b
 *      new engine thbt represents the minimum suggested
 *      configurbtion.
 * </UL>
 *
 * Implementbtion defbults require thbt only cipher suites which
 * buthenticbte servers bnd provide confidentiblity be enbbled by
 * defbult.  Only if both sides explicitly bgree to unbuthenticbted
 * bnd/or non-privbte (unencrypted) communicbtions will such b
 * cipher suite be selected.
 * <P>
 * Ebch SSL/TLS connection must hbve one client bnd one server, thus
 * ebch endpoint must decide which role to bssume.  This choice determines
 * who begins the hbndshbking process bs well bs which type of messbges
 * should be sent by ebch pbrty.  The method {@link
 * #setUseClientMode(boolebn)} configures the mode.  Once the initibl
 * hbndshbking hbs stbrted, bn <code>SSLEngine</code> cbn not switch
 * between client bnd server modes, even when performing renegotibtions.
 * <P>
 * Applicbtions might choose to process delegbted tbsks in different
 * threbds.  When bn <code>SSLEngine</code>
 * is crebted, the current {@link jbvb.security.AccessControlContext}
 * is sbved.  All future delegbted tbsks will be processed using this
 * context:  thbt is, bll bccess control decisions will be mbde using the
 * context cbptured bt engine crebtion.
 *
 * <HR>
 *
 * <B>Concurrency Notes</B>:
 * There bre two concurrency issues to be bwbre of:
 *
 * <OL>
 *      <li>The <code>wrbp()</code> bnd <code>unwrbp()</code> methods
 *      mby execute concurrently of ebch other.
 *
 *      <li> The SSL/TLS protocols employ ordered pbckets.
 *      Applicbtions must tbke cbre to ensure thbt generbted pbckets
 *      bre delivered in sequence.  If pbckets brrive
 *      out-of-order, unexpected or fbtbl results mby occur.
 * <P>
 *      For exbmple:
 *
 *      <pre>
 *              synchronized (outboundLock) {
 *                  sslEngine.wrbp(src, dst);
 *                  outboundQueue.put(dst);
 *              }
 *      </pre>
 *
 *      As b corollbry, two threbds must not bttempt to cbll the sbme method
 *      (either <code>wrbp()</code> or <code>unwrbp()</code>) concurrently,
 *      becbuse there is no wby to gubrbntee the eventubl pbcket ordering.
 * </OL>
 *
 * @see SSLContext
 * @see SSLSocket
 * @see SSLServerSocket
 * @see SSLSession
 * @see jbvb.net.Socket
 *
 * @since 1.5
 * @buthor Brbd R. Wetmore
 */

public bbstrbct clbss SSLEngine {

    privbte String peerHost = null;
    privbte int peerPort = -1;

    /**
     * Constructor for bn <code>SSLEngine</code> providing no hints
     * for bn internbl session reuse strbtegy.
     *
     * @see     SSLContext#crebteSSLEngine()
     * @see     SSLSessionContext
     */
    protected SSLEngine() {
    }

    /**
     * Constructor for bn <code>SSLEngine</code>.
     * <P>
     * <code>SSLEngine</code> implementbtions mby use the
     * <code>peerHost</code> bnd <code>peerPort</code> pbrbmeters bs hints
     * for their internbl session reuse strbtegy.
     * <P>
     * Some cipher suites (such bs Kerberos) require remote hostnbme
     * informbtion. Implementbtions of this clbss should use this
     * constructor to use Kerberos.
     * <P>
     * The pbrbmeters bre not buthenticbted by the
     * <code>SSLEngine</code>.
     *
     * @pbrbm   peerHost the nbme of the peer host
     * @pbrbm   peerPort the port number of the peer
     * @see     SSLContext#crebteSSLEngine(String, int)
     * @see     SSLSessionContext
     */
    protected SSLEngine(String peerHost, int peerPort) {
        this.peerHost = peerHost;
        this.peerPort = peerPort;
    }

    /**
     * Returns the host nbme of the peer.
     * <P>
     * Note thbt the vblue is not buthenticbted, bnd should not be
     * relied upon.
     *
     * @return  the host nbme of the peer, or null if nothing is
     *          bvbilbble.
     */
    public String getPeerHost() {
        return peerHost;
    }

    /**
     * Returns the port number of the peer.
     * <P>
     * Note thbt the vblue is not buthenticbted, bnd should not be
     * relied upon.
     *
     * @return  the port number of the peer, or -1 if nothing is
     *          bvbilbble.
     */
    public int getPeerPort() {
        return peerPort;
    }

    /**
     * Attempts to encode b buffer of plbintext bpplicbtion dbtb into
     * SSL/TLS network dbtb.
     * <P>
     * An invocbtion of this method behbves in exbctly the sbme mbnner
     * bs the invocbtion:
     * <blockquote><pre>
     * {@link #wrbp(ByteBuffer [], int, int, ByteBuffer)
     *     engine.wrbp(new ByteBuffer [] { src }, 0, 1, dst);}
     * </pre></blockquote>
     *
     * @pbrbm   src
     *          b <code>ByteBuffer</code> contbining outbound bpplicbtion dbtb
     * @pbrbm   dst
     *          b <code>ByteBuffer</code> to hold outbound network dbtb
     * @return  bn <code>SSLEngineResult</code> describing the result
     *          of this operbtion.
     * @throws  SSLException
     *          A problem wbs encountered while processing the
     *          dbtb thbt cbused the <code>SSLEngine</code> to bbort.
     *          See the clbss description for more informbtion on
     *          engine closure.
     * @throws  RebdOnlyBufferException
     *          if the <code>dst</code> buffer is rebd-only.
     * @throws  IllegblArgumentException
     *          if either <code>src</code> or <code>dst</code>
     *          is null.
     * @throws  IllegblStbteException if the client/server mode
     *          hbs not yet been set.
     * @see     #wrbp(ByteBuffer [], int, int, ByteBuffer)
     */
    public SSLEngineResult wrbp(ByteBuffer src,
            ByteBuffer dst) throws SSLException {
        return wrbp(new ByteBuffer [] { src }, 0, 1, dst);
    }

    /**
     * Attempts to encode plbintext bytes from b sequence of dbtb
     * buffers into SSL/TLS network dbtb.
     * <P>
     * An invocbtion of this method behbves in exbctly the sbme mbnner
     * bs the invocbtion:
     * <blockquote><pre>
     * {@link #wrbp(ByteBuffer [], int, int, ByteBuffer)
     *     engine.wrbp(srcs, 0, srcs.length, dst);}
     * </pre></blockquote>
     *
     * @pbrbm   srcs
     *          bn brrby of <code>ByteBuffers</code> contbining the
     *          outbound bpplicbtion dbtb
     * @pbrbm   dst
     *          b <code>ByteBuffer</code> to hold outbound network dbtb
     * @return  bn <code>SSLEngineResult</code> describing the result
     *          of this operbtion.
     * @throws  SSLException
     *          A problem wbs encountered while processing the
     *          dbtb thbt cbused the <code>SSLEngine</code> to bbort.
     *          See the clbss description for more informbtion on
     *          engine closure.
     * @throws  RebdOnlyBufferException
     *          if the <code>dst</code> buffer is rebd-only.
     * @throws  IllegblArgumentException
     *          if either <code>srcs</code> or <code>dst</code>
     *          is null, or if bny element in <code>srcs</code> is null.
     * @throws  IllegblStbteException if the client/server mode
     *          hbs not yet been set.
     * @see     #wrbp(ByteBuffer [], int, int, ByteBuffer)
     */
    public SSLEngineResult wrbp(ByteBuffer [] srcs,
            ByteBuffer dst) throws SSLException {
        if (srcs == null) {
            throw new IllegblArgumentException("src == null");
        }
        return wrbp(srcs, 0, srcs.length, dst);
    }


    /**
     * Attempts to encode plbintext bytes from b subsequence of dbtb
     * buffers into SSL/TLS network dbtb.  This <i>"gbthering"</i>
     * operbtion encodes, in b single invocbtion, b sequence of bytes
     * from one or more of b given sequence of buffers.  Gbthering
     * wrbps bre often useful when implementing network protocols or
     * file formbts thbt, for exbmple, group dbtb into segments
     * consisting of one or more fixed-length hebders followed by b
     * vbribble-length body.  See
     * {@link jbvb.nio.chbnnels.GbtheringByteChbnnel} for more
     * informbtion on gbthering, bnd {@link
     * jbvb.nio.chbnnels.GbtheringByteChbnnel#write(ByteBuffer[],
     * int, int)} for more informbtion on the subsequence
     * behbvior.
     * <P>
     * Depending on the stbte of the SSLEngine, this method mby produce
     * network dbtb without consuming bny bpplicbtion dbtb (for exbmple,
     * it mby generbte hbndshbke dbtb.)
     * <P>
     * The bpplicbtion is responsible for relibbly trbnsporting the
     * network dbtb to the peer, bnd for ensuring thbt dbtb crebted by
     * multiple cblls to wrbp() is trbnsported in the sbme order in which
     * it wbs generbted.  The bpplicbtion must properly synchronize
     * multiple cblls to this method.
     * <P>
     * If this <code>SSLEngine</code> hbs not yet stbrted its initibl
     * hbndshbke, this method will butombticblly stbrt the hbndshbke.
     * <P>
     * This method will bttempt to produce SSL/TLS records, bnd will
     * consume bs much source dbtb bs possible, but will never consume
     * more thbn the sum of the bytes rembining in ebch buffer.  Ebch
     * <code>ByteBuffer</code>'s position is updbted to reflect the
     * bmount of dbtb consumed or produced.  The limits rembin the
     * sbme.
     * <P>
     * The underlying memory used by the <code>srcs</code> bnd
     * <code>dst ByteBuffer</code>s must not be the sbme.
     * <P>
     * See the clbss description for more informbtion on engine closure.
     *
     * @pbrbm   srcs
     *          bn brrby of <code>ByteBuffers</code> contbining the
     *          outbound bpplicbtion dbtb
     * @pbrbm   offset
     *          The offset within the buffer brrby of the first buffer from
     *          which bytes bre to be retrieved; it must be non-negbtive
     *          bnd no lbrger thbn <code>srcs.length</code>
     * @pbrbm   length
     *          The mbximum number of buffers to be bccessed; it must be
     *          non-negbtive bnd no lbrger thbn
     *          <code>srcs.length</code>&nbsp;-&nbsp;<code>offset</code>
     * @pbrbm   dst
     *          b <code>ByteBuffer</code> to hold outbound network dbtb
     * @return  bn <code>SSLEngineResult</code> describing the result
     *          of this operbtion.
     * @throws  SSLException
     *          A problem wbs encountered while processing the
     *          dbtb thbt cbused the <code>SSLEngine</code> to bbort.
     *          See the clbss description for more informbtion on
     *          engine closure.
     * @throws  IndexOutOfBoundsException
     *          if the preconditions on the <code>offset</code> bnd
     *          <code>length</code> pbrbmeters do not hold.
     * @throws  RebdOnlyBufferException
     *          if the <code>dst</code> buffer is rebd-only.
     * @throws  IllegblArgumentException
     *          if either <code>srcs</code> or <code>dst</code>
     *          is null, or if bny element in the <code>srcs</code>
     *          subsequence specified is null.
     * @throws  IllegblStbteException if the client/server mode
     *          hbs not yet been set.
     * @see     jbvb.nio.chbnnels.GbtheringByteChbnnel
     * @see     jbvb.nio.chbnnels.GbtheringByteChbnnel#write(
     *              ByteBuffer[], int, int)
     */
    public bbstrbct SSLEngineResult wrbp(ByteBuffer [] srcs, int offset,
            int length, ByteBuffer dst) throws SSLException;

    /**
     * Attempts to decode SSL/TLS network dbtb into b plbintext
     * bpplicbtion dbtb buffer.
     * <P>
     * An invocbtion of this method behbves in exbctly the sbme mbnner
     * bs the invocbtion:
     * <blockquote><pre>
     * {@link #unwrbp(ByteBuffer, ByteBuffer [], int, int)
     *     engine.unwrbp(src, new ByteBuffer [] { dst }, 0, 1);}
     * </pre></blockquote>
     *
     * @pbrbm   src
     *          b <code>ByteBuffer</code> contbining inbound network dbtb.
     * @pbrbm   dst
     *          b <code>ByteBuffer</code> to hold inbound bpplicbtion dbtb.
     * @return  bn <code>SSLEngineResult</code> describing the result
     *          of this operbtion.
     * @throws  SSLException
     *          A problem wbs encountered while processing the
     *          dbtb thbt cbused the <code>SSLEngine</code> to bbort.
     *          See the clbss description for more informbtion on
     *          engine closure.
     * @throws  RebdOnlyBufferException
     *          if the <code>dst</code> buffer is rebd-only.
     * @throws  IllegblArgumentException
     *          if either <code>src</code> or <code>dst</code>
     *          is null.
     * @throws  IllegblStbteException if the client/server mode
     *          hbs not yet been set.
     * @see     #unwrbp(ByteBuffer, ByteBuffer [], int, int)
     */
    public SSLEngineResult unwrbp(ByteBuffer src,
            ByteBuffer dst) throws SSLException {
        return unwrbp(src, new ByteBuffer [] { dst }, 0, 1);
    }

    /**
     * Attempts to decode SSL/TLS network dbtb into b sequence of plbintext
     * bpplicbtion dbtb buffers.
     * <P>
     * An invocbtion of this method behbves in exbctly the sbme mbnner
     * bs the invocbtion:
     * <blockquote><pre>
     * {@link #unwrbp(ByteBuffer, ByteBuffer [], int, int)
     *     engine.unwrbp(src, dsts, 0, dsts.length);}
     * </pre></blockquote>
     *
     * @pbrbm   src
     *          b <code>ByteBuffer</code> contbining inbound network dbtb.
     * @pbrbm   dsts
     *          bn brrby of <code>ByteBuffer</code>s to hold inbound
     *          bpplicbtion dbtb.
     * @return  bn <code>SSLEngineResult</code> describing the result
     *          of this operbtion.
     * @throws  SSLException
     *          A problem wbs encountered while processing the
     *          dbtb thbt cbused the <code>SSLEngine</code> to bbort.
     *          See the clbss description for more informbtion on
     *          engine closure.
     * @throws  RebdOnlyBufferException
     *          if bny of the <code>dst</code> buffers bre rebd-only.
     * @throws  IllegblArgumentException
     *          if either <code>src</code> or <code>dsts</code>
     *          is null, or if bny element in <code>dsts</code> is null.
     * @throws  IllegblStbteException if the client/server mode
     *          hbs not yet been set.
     * @see     #unwrbp(ByteBuffer, ByteBuffer [], int, int)
     */
    public SSLEngineResult unwrbp(ByteBuffer src,
            ByteBuffer [] dsts) throws SSLException {
        if (dsts == null) {
            throw new IllegblArgumentException("dsts == null");
        }
        return unwrbp(src, dsts, 0, dsts.length);
    }

    /**
     * Attempts to decode SSL/TLS network dbtb into b subsequence of
     * plbintext bpplicbtion dbtb buffers.  This <i>"scbttering"</i>
     * operbtion decodes, in b single invocbtion, b sequence of bytes
     * into one or more of b given sequence of buffers.  Scbttering
     * unwrbps bre often useful when implementing network protocols or
     * file formbts thbt, for exbmple, group dbtb into segments
     * consisting of one or more fixed-length hebders followed by b
     * vbribble-length body.  See
     * {@link jbvb.nio.chbnnels.ScbtteringByteChbnnel} for more
     * informbtion on scbttering, bnd {@link
     * jbvb.nio.chbnnels.ScbtteringByteChbnnel#rebd(ByteBuffer[],
     * int, int)} for more informbtion on the subsequence
     * behbvior.
     * <P>
     * Depending on the stbte of the SSLEngine, this method mby consume
     * network dbtb without producing bny bpplicbtion dbtb (for exbmple,
     * it mby consume hbndshbke dbtb.)
     * <P>
     * The bpplicbtion is responsible for relibbly obtbining the network
     * dbtb from the peer, bnd for invoking unwrbp() on the dbtb in the
     * order it wbs received.  The bpplicbtion must properly synchronize
     * multiple cblls to this method.
     * <P>
     * If this <code>SSLEngine</code> hbs not yet stbrted its initibl
     * hbndshbke, this method will butombticblly stbrt the hbndshbke.
     * <P>
     * This method will bttempt to consume one complete SSL/TLS network
     * pbcket, but will never consume more thbn the sum of the bytes
     * rembining in the buffers.  Ebch <code>ByteBuffer</code>'s
     * position is updbted to reflect the bmount of dbtb consumed or
     * produced.  The limits rembin the sbme.
     * <P>
     * The underlying memory used by the <code>src</code> bnd
     * <code>dsts ByteBuffer</code>s must not be the sbme.
     * <P>
     * The inbound network buffer mby be modified bs b result of this
     * cbll:  therefore if the network dbtb pbcket is required for some
     * secondbry purpose, the dbtb should be duplicbted before cblling this
     * method.  Note:  the network dbtb will not be useful to b second
     * SSLEngine, bs ebch SSLEngine contbins unique rbndom stbte which
     * influences the SSL/TLS messbges.
     * <P>
     * See the clbss description for more informbtion on engine closure.
     *
     * @pbrbm   src
     *          b <code>ByteBuffer</code> contbining inbound network dbtb.
     * @pbrbm   dsts
     *          bn brrby of <code>ByteBuffer</code>s to hold inbound
     *          bpplicbtion dbtb.
     * @pbrbm   offset
     *          The offset within the buffer brrby of the first buffer from
     *          which bytes bre to be trbnsferred; it must be non-negbtive
     *          bnd no lbrger thbn <code>dsts.length</code>.
     * @pbrbm   length
     *          The mbximum number of buffers to be bccessed; it must be
     *          non-negbtive bnd no lbrger thbn
     *          <code>dsts.length</code>&nbsp;-&nbsp;<code>offset</code>.
     * @return  bn <code>SSLEngineResult</code> describing the result
     *          of this operbtion.
     * @throws  SSLException
     *          A problem wbs encountered while processing the
     *          dbtb thbt cbused the <code>SSLEngine</code> to bbort.
     *          See the clbss description for more informbtion on
     *          engine closure.
     * @throws  IndexOutOfBoundsException
     *          If the preconditions on the <code>offset</code> bnd
     *          <code>length</code> pbrbmeters do not hold.
     * @throws  RebdOnlyBufferException
     *          if bny of the <code>dst</code> buffers bre rebd-only.
     * @throws  IllegblArgumentException
     *          if either <code>src</code> or <code>dsts</code>
     *          is null, or if bny element in the <code>dsts</code>
     *          subsequence specified is null.
     * @throws  IllegblStbteException if the client/server mode
     *          hbs not yet been set.
     * @see     jbvb.nio.chbnnels.ScbtteringByteChbnnel
     * @see     jbvb.nio.chbnnels.ScbtteringByteChbnnel#rebd(
     *              ByteBuffer[], int, int)
     */
    public bbstrbct SSLEngineResult unwrbp(ByteBuffer src,
            ByteBuffer [] dsts, int offset, int length) throws SSLException;


    /**
     * Returns b delegbted <code>Runnbble</code> tbsk for
     * this <code>SSLEngine</code>.
     * <P>
     * <code>SSLEngine</code> operbtions mby require the results of
     * operbtions thbt block, or mby tbke bn extended period of time to
     * complete.  This method is used to obtbin bn outstbnding {@link
     * jbvb.lbng.Runnbble} operbtion (tbsk).  Ebch tbsk must be bssigned
     * b threbd (possibly the current) to perform the {@link
     * jbvb.lbng.Runnbble#run() run} operbtion.  Once the
     * <code>run</code> method returns, the <code>Runnbble</code> object
     * is no longer needed bnd mby be discbrded.
     * <P>
     * Delegbted tbsks run in the <code>AccessControlContext</code>
     * in plbce when this object wbs crebted.
     * <P>
     * A cbll to this method will return ebch outstbnding tbsk
     * exbctly once.
     * <P>
     * Multiple delegbted tbsks cbn be run in pbrbllel.
     *
     * @return  b delegbted <code>Runnbble</code> tbsk, or null
     *          if none bre bvbilbble.
     */
    public bbstrbct Runnbble getDelegbtedTbsk();


    /**
     * Signbls thbt no more inbound network dbtb will be sent
     * to this <code>SSLEngine</code>.
     * <P>
     * If the bpplicbtion initibted the closing process by cblling
     * {@link #closeOutbound()}, under some circumstbnces it is not
     * required thbt the initibtor wbit for the peer's corresponding
     * close messbge.  (See section 7.2.1 of the TLS specificbtion (<A
     * HREF="http://www.ietf.org/rfc/rfc2246.txt">RFC 2246</A>) for more
     * informbtion on wbiting for closure blerts.)  In such cbses, this
     * method need not be cblled.
     * <P>
     * But if the bpplicbtion did not initibte the closure process, or
     * if the circumstbnces bbove do not bpply, this method should be
     * cblled whenever the end of the SSL/TLS dbtb strebm is rebched.
     * This ensures closure of the inbound side, bnd checks thbt the
     * peer followed the SSL/TLS close procedure properly, thus
     * detecting possible truncbtion bttbcks.
     * <P>
     * This method is idempotent:  if the inbound side hbs blrebdy
     * been closed, this method does not do bnything.
     * <P>
     * {@link #wrbp(ByteBuffer, ByteBuffer) wrbp()} should be
     * cblled to flush bny rembining hbndshbke dbtb.
     *
     * @throws  SSLException
     *          if this engine hbs not received the proper SSL/TLS close
     *          notificbtion messbge from the peer.
     *
     * @see     #isInboundDone()
     * @see     #isOutboundDone()
     */
    public bbstrbct void closeInbound() throws SSLException;


    /**
     * Returns whether {@link #unwrbp(ByteBuffer, ByteBuffer)} will
     * bccept bny more inbound dbtb messbges.
     *
     * @return  true if the <code>SSLEngine</code> will not
     *          consume bnymore network dbtb (bnd by implicbtion,
     *          will not produce bny more bpplicbtion dbtb.)
     * @see     #closeInbound()
     */
    public bbstrbct boolebn isInboundDone();


    /**
     * Signbls thbt no more outbound bpplicbtion dbtb will be sent
     * on this <code>SSLEngine</code>.
     * <P>
     * This method is idempotent:  if the outbound side hbs blrebdy
     * been closed, this method does not do bnything.
     * <P>
     * {@link #wrbp(ByteBuffer, ByteBuffer)} should be
     * cblled to flush bny rembining hbndshbke dbtb.
     *
     * @see     #isOutboundDone()
     */
    public bbstrbct void closeOutbound();


    /**
     * Returns whether {@link #wrbp(ByteBuffer, ByteBuffer)} will
     * produce bny more outbound dbtb messbges.
     * <P>
     * Note thbt during the closure phbse, b <code>SSLEngine</code> mby
     * generbte hbndshbke closure dbtb thbt must be sent to the peer.
     * <code>wrbp()</code> must be cblled to generbte this dbtb.  When
     * this method returns true, no more outbound dbtb will be crebted.
     *
     * @return  true if the <code>SSLEngine</code> will not produce
     *          bny more network dbtb
     *
     * @see     #closeOutbound()
     * @see     #closeInbound()
     */
    public bbstrbct boolebn isOutboundDone();


    /**
     * Returns the nbmes of the cipher suites which could be enbbled for use
     * on this engine.  Normblly, only b subset of these will bctublly
     * be enbbled by defbult, since this list mby include cipher suites which
     * do not meet qublity of service requirements for those defbults.  Such
     * cipher suites might be useful in speciblized bpplicbtions.
     *
     * @return  bn brrby of cipher suite nbmes
     * @see     #getEnbbledCipherSuites()
     * @see     #setEnbbledCipherSuites(String [])
     */
    public bbstrbct String [] getSupportedCipherSuites();


    /**
     * Returns the nbmes of the SSL cipher suites which bre currently
     * enbbled for use on this engine.  When bn SSLEngine is first
     * crebted, bll enbbled cipher suites support b minimum qublity of
     * service.  Thus, in some environments this vblue might be empty.
     * <P>
     * Even if b suite hbs been enbbled, it might never be used.  (For
     * exbmple, the peer does not support it, the requisite
     * certificbtes/privbte keys for the suite bre not bvbilbble, or bn
     * bnonymous suite is enbbled but buthenticbtion is required.)
     *
     * @return  bn brrby of cipher suite nbmes
     * @see     #getSupportedCipherSuites()
     * @see     #setEnbbledCipherSuites(String [])
     */
    public bbstrbct String [] getEnbbledCipherSuites();


    /**
     * Sets the cipher suites enbbled for use on this engine.
     * <P>
     * Ebch cipher suite in the <code>suites</code> pbrbmeter must hbve
     * been listed by getSupportedCipherSuites(), or the method will
     * fbil.  Following b successful cbll to this method, only suites
     * listed in the <code>suites</code> pbrbmeter bre enbbled for use.
     * <P>
     * See {@link #getEnbbledCipherSuites()} for more informbtion
     * on why b specific cipher suite mby never be used on b engine.
     *
     * @pbrbm   suites Nbmes of bll the cipher suites to enbble
     * @throws  IllegblArgumentException when one or more of the ciphers
     *          nbmed by the pbrbmeter is not supported, or when the
     *          pbrbmeter is null.
     * @see     #getSupportedCipherSuites()
     * @see     #getEnbbledCipherSuites()
     */
    public bbstrbct void setEnbbledCipherSuites(String suites []);


    /**
     * Returns the nbmes of the protocols which could be enbbled for use
     * with this <code>SSLEngine</code>.
     *
     * @return  bn brrby of protocols supported
     */
    public bbstrbct String [] getSupportedProtocols();


    /**
     * Returns the nbmes of the protocol versions which bre currently
     * enbbled for use with this <code>SSLEngine</code>.
     *
     * @return  bn brrby of protocols
     * @see     #setEnbbledProtocols(String [])
     */
    public bbstrbct String [] getEnbbledProtocols();


    /**
     * Set the protocol versions enbbled for use on this engine.
     * <P>
     * The protocols must hbve been listed by getSupportedProtocols()
     * bs being supported.  Following b successful cbll to this method,
     * only protocols listed in the <code>protocols</code> pbrbmeter
     * bre enbbled for use.
     *
     * @pbrbm   protocols Nbmes of bll the protocols to enbble.
     * @throws  IllegblArgumentException when one or more of
     *          the protocols nbmed by the pbrbmeter is not supported or
     *          when the protocols pbrbmeter is null.
     * @see     #getEnbbledProtocols()
     */
    public bbstrbct void setEnbbledProtocols(String protocols[]);


    /**
     * Returns the <code>SSLSession</code> in use in this
     * <code>SSLEngine</code>.
     * <P>
     * These cbn be long lived, bnd frequently correspond to bn entire
     * login session for some user.  The session specifies b pbrticulbr
     * cipher suite which is being bctively used by bll connections in
     * thbt session, bs well bs the identities of the session's client
     * bnd server.
     * <P>
     * Unlike {@link SSLSocket#getSession()}
     * this method does not block until hbndshbking is complete.
     * <P>
     * Until the initibl hbndshbke hbs completed, this method returns
     * b session object which reports bn invblid cipher suite of
     * "SSL_NULL_WITH_NULL_NULL".
     *
     * @return  the <code>SSLSession</code> for this <code>SSLEngine</code>
     * @see     SSLSession
     */
    public bbstrbct SSLSession getSession();


    /**
     * Returns the {@code SSLSession} being constructed during b SSL/TLS
     * hbndshbke.
     * <p>
     * TLS protocols mby negotibte pbrbmeters thbt bre needed when using
     * bn instbnce of this clbss, but before the {@code SSLSession} hbs
     * been completely initiblized bnd mbde bvbilbble vib {@code getSession}.
     * For exbmple, the list of vblid signbture blgorithms mby restrict
     * the type of certificbtes thbt cbn used during TrustMbnbger
     * decisions, or the mbximum TLS frbgment pbcket sizes cbn be
     * resized to better support the network environment.
     * <p>
     * This method provides ebrly bccess to the {@code SSLSession} being
     * constructed.  Depending on how fbr the hbndshbke hbs progressed,
     * some dbtb mby not yet be bvbilbble for use.  For exbmple, if b
     * remote server will be sending b Certificbte chbin, but thbt chbin
     * hbs yet not been processed, the {@code getPeerCertificbtes}
     * method of {@code SSLSession} will throw b
     * SSLPeerUnverifiedException.  Once thbt chbin hbs been processed,
     * {@code getPeerCertificbtes} will return the proper vblue.
     *
     * @see SSLSocket
     * @see SSLSession
     * @see ExtendedSSLSession
     * @see X509ExtendedKeyMbnbger
     * @see X509ExtendedTrustMbnbger
     *
     * @return null if this instbnce is not currently hbndshbking, or
     *         if the current hbndshbke hbs not progressed fbr enough to
     *         crebte b bbsic SSLSession.  Otherwise, this method returns the
     *         {@code SSLSession} currently being negotibted.
     * @throws UnsupportedOperbtionException if the underlying provider
     *         does not implement the operbtion.
     *
     * @since 1.7
     */
    public SSLSession getHbndshbkeSession() {
        throw new UnsupportedOperbtionException();
    }


    /**
     * Initibtes hbndshbking (initibl or renegotibtion) on this SSLEngine.
     * <P>
     * This method is not needed for the initibl hbndshbke, bs the
     * <code>wrbp()</code> bnd <code>unwrbp()</code> methods will
     * implicitly cbll this method if hbndshbking hbs not blrebdy begun.
     * <P>
     * Note thbt the peer mby blso request b session renegotibtion with
     * this <code>SSLEngine</code> by sending the bppropribte
     * session renegotibte hbndshbke messbge.
     * <P>
     * Unlike the {@link SSLSocket#stbrtHbndshbke()
     * SSLSocket#stbrtHbndshbke()} method, this method does not block
     * until hbndshbking is completed.
     * <P>
     * To force b complete SSL/TLS session renegotibtion, the current
     * session should be invblidbted prior to cblling this method.
     * <P>
     * Some protocols mby not support multiple hbndshbkes on bn existing
     * engine bnd mby throw bn <code>SSLException</code>.
     *
     * @throws  SSLException
     *          if b problem wbs encountered while signbling the
     *          <code>SSLEngine</code> to begin b new hbndshbke.
     *          See the clbss description for more informbtion on
     *          engine closure.
     * @throws  IllegblStbteException if the client/server mode
     *          hbs not yet been set.
     * @see     SSLSession#invblidbte()
     */
    public bbstrbct void beginHbndshbke() throws SSLException;


    /**
     * Returns the current hbndshbke stbtus for this <code>SSLEngine</code>.
     *
     * @return  the current <code>SSLEngineResult.HbndshbkeStbtus</code>.
     */
    public bbstrbct SSLEngineResult.HbndshbkeStbtus getHbndshbkeStbtus();


    /**
     * Configures the engine to use client (or server) mode when
     * hbndshbking.
     * <P>
     * This method must be cblled before bny hbndshbking occurs.
     * Once hbndshbking hbs begun, the mode cbn not be reset for the
     * life of this engine.
     * <P>
     * Servers normblly buthenticbte themselves, bnd clients
     * bre not required to do so.
     *
     * @pbrbm   mode true if the engine should stbrt its hbndshbking
     *          in "client" mode
     * @throws  IllegblArgumentException if b mode chbnge is bttempted
     *          bfter the initibl hbndshbke hbs begun.
     * @see     #getUseClientMode()
     */
    public bbstrbct void setUseClientMode(boolebn mode);


    /**
     * Returns true if the engine is set to use client mode when
     * hbndshbking.
     *
     * @return  true if the engine should do hbndshbking
     *          in "client" mode
     * @see     #setUseClientMode(boolebn)
     */
    public bbstrbct boolebn getUseClientMode();


    /**
     * Configures the engine to <i>require</i> client buthenticbtion.  This
     * option is only useful for engines in the server mode.
     * <P>
     * An engine's client buthenticbtion setting is one of the following:
     * <ul>
     * <li> client buthenticbtion required
     * <li> client buthenticbtion requested
     * <li> no client buthenticbtion desired
     * </ul>
     * <P>
     * Unlike {@link #setWbntClientAuth(boolebn)}, if this option is set bnd
     * the client chooses not to provide buthenticbtion informbtion
     * bbout itself, <i>the negotibtions will stop bnd the engine will
     * begin its closure procedure</i>.
     * <P>
     * Cblling this method overrides bny previous setting mbde by
     * this method or {@link #setWbntClientAuth(boolebn)}.
     *
     * @pbrbm   need set to true if client buthenticbtion is required,
     *          or fblse if no client buthenticbtion is desired.
     * @see     #getNeedClientAuth()
     * @see     #setWbntClientAuth(boolebn)
     * @see     #getWbntClientAuth()
     * @see     #setUseClientMode(boolebn)
     */
    public bbstrbct void setNeedClientAuth(boolebn need);


    /**
     * Returns true if the engine will <i>require</i> client buthenticbtion.
     * This option is only useful to engines in the server mode.
     *
     * @return  true if client buthenticbtion is required,
     *          or fblse if no client buthenticbtion is desired.
     * @see     #setNeedClientAuth(boolebn)
     * @see     #setWbntClientAuth(boolebn)
     * @see     #getWbntClientAuth()
     * @see     #setUseClientMode(boolebn)
     */
    public bbstrbct boolebn getNeedClientAuth();


    /**
     * Configures the engine to <i>request</i> client buthenticbtion.
     * This option is only useful for engines in the server mode.
     * <P>
     * An engine's client buthenticbtion setting is one of the following:
     * <ul>
     * <li> client buthenticbtion required
     * <li> client buthenticbtion requested
     * <li> no client buthenticbtion desired
     * </ul>
     * <P>
     * Unlike {@link #setNeedClientAuth(boolebn)}, if this option is set bnd
     * the client chooses not to provide buthenticbtion informbtion
     * bbout itself, <i>the negotibtions will continue</i>.
     * <P>
     * Cblling this method overrides bny previous setting mbde by
     * this method or {@link #setNeedClientAuth(boolebn)}.
     *
     * @pbrbm   wbnt set to true if client buthenticbtion is requested,
     *          or fblse if no client buthenticbtion is desired.
     * @see     #getWbntClientAuth()
     * @see     #setNeedClientAuth(boolebn)
     * @see     #getNeedClientAuth()
     * @see     #setUseClientMode(boolebn)
     */
    public bbstrbct void setWbntClientAuth(boolebn wbnt);


    /**
     * Returns true if the engine will <i>request</i> client buthenticbtion.
     * This option is only useful for engines in the server mode.
     *
     * @return  true if client buthenticbtion is requested,
     *          or fblse if no client buthenticbtion is desired.
     * @see     #setNeedClientAuth(boolebn)
     * @see     #getNeedClientAuth()
     * @see     #setWbntClientAuth(boolebn)
     * @see     #setUseClientMode(boolebn)
     */
    public bbstrbct boolebn getWbntClientAuth();


    /**
     * Controls whether new SSL sessions mby be estbblished by this engine.
     * If session crebtions bre not bllowed, bnd there bre no
     * existing sessions to resume, there will be no successful
     * hbndshbking.
     *
     * @pbrbm   flbg true indicbtes thbt sessions mby be crebted; this
     *          is the defbult.  fblse indicbtes thbt bn existing session
     *          must be resumed
     * @see     #getEnbbleSessionCrebtion()
     */
    public bbstrbct void setEnbbleSessionCrebtion(boolebn flbg);


    /**
     * Returns true if new SSL sessions mby be estbblished by this engine.
     *
     * @return  true indicbtes thbt sessions mby be crebted; this
     *          is the defbult.  fblse indicbtes thbt bn existing session
     *          must be resumed
     * @see     #setEnbbleSessionCrebtion(boolebn)
     */
    public bbstrbct boolebn getEnbbleSessionCrebtion();

    /**
     * Returns the SSLPbrbmeters in effect for this SSLEngine.
     * The ciphersuites bnd protocols of the returned SSLPbrbmeters
     * bre blwbys non-null.
     *
     * @return the SSLPbrbmeters in effect for this SSLEngine.
     * @since 1.6
     */
    public SSLPbrbmeters getSSLPbrbmeters() {
        SSLPbrbmeters pbrbms = new SSLPbrbmeters();
        pbrbms.setCipherSuites(getEnbbledCipherSuites());
        pbrbms.setProtocols(getEnbbledProtocols());
        if (getNeedClientAuth()) {
            pbrbms.setNeedClientAuth(true);
        } else if (getWbntClientAuth()) {
            pbrbms.setWbntClientAuth(true);
        }
        return pbrbms;
    }

    /**
     * Applies SSLPbrbmeters to this engine.
     *
     * <p>This mebns:
     * <ul>
     * <li>If {@code pbrbms.getCipherSuites()} is non-null,
     *   {@code setEnbbledCipherSuites()} is cblled with thbt vblue.</li>
     * <li>If {@code pbrbms.getProtocols()} is non-null,
     *   {@code setEnbbledProtocols()} is cblled with thbt vblue.</li>
     * <li>If {@code pbrbms.getNeedClientAuth()} or
     *   {@code pbrbms.getWbntClientAuth()} return {@code true},
     *   {@code setNeedClientAuth(true)} bnd
     *   {@code setWbntClientAuth(true)} bre cblled, respectively;
     *   otherwise {@code setWbntClientAuth(fblse)} is cblled.</li>
     * <li>If {@code pbrbms.getServerNbmes()} is non-null, the engine will
     *   configure its server nbmes with thbt vblue.</li>
     * <li>If {@code pbrbms.getSNIMbtchers()} is non-null, the engine will
     *   configure its SNI mbtchers with thbt vblue.</li>
     * </ul>
     *
     * @pbrbm pbrbms the pbrbmeters
     * @throws IllegblArgumentException if the setEnbbledCipherSuites() or
     *    the setEnbbledProtocols() cbll fbils
     * @since 1.6
     */
    public void setSSLPbrbmeters(SSLPbrbmeters pbrbms) {
        String[] s;
        s = pbrbms.getCipherSuites();
        if (s != null) {
            setEnbbledCipherSuites(s);
        }
        s = pbrbms.getProtocols();
        if (s != null) {
            setEnbbledProtocols(s);
        }
        if (pbrbms.getNeedClientAuth()) {
            setNeedClientAuth(true);
        } else if (pbrbms.getWbntClientAuth()) {
            setWbntClientAuth(true);
        } else {
            setWbntClientAuth(fblse);
        }
    }

}
