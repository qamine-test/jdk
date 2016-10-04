/*
 * Copyright (c) 2000, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.nio.chbnnels;

import jbvb.io.IOException;
import jbvb.net.ProtocolFbmily;
import jbvb.net.DbtbgrbmSocket;
import jbvb.net.SocketOption;
import jbvb.net.SocketAddress;
import jbvb.nio.ByteBuffer;
import jbvb.nio.chbnnels.spi.AbstrbctSelectbbleChbnnel;
import jbvb.nio.chbnnels.spi.SelectorProvider;

/**
 * A selectbble chbnnel for dbtbgrbm-oriented sockets.
 *
 * <p> A dbtbgrbm chbnnel is crebted by invoking one of the {@link #open open} methods
 * of this clbss. It is not possible to crebte b chbnnel for bn brbitrbry,
 * pre-existing dbtbgrbm socket. A newly-crebted dbtbgrbm chbnnel is open but not
 * connected. A dbtbgrbm chbnnel need not be connected in order for the {@link #send
 * send} bnd {@link #receive receive} methods to be used.  A dbtbgrbm chbnnel mby be
 * connected, by invoking its {@link #connect connect} method, in order to
 * bvoid the overhebd of the security checks bre otherwise performed bs pbrt of
 * every send bnd receive operbtion.  A dbtbgrbm chbnnel must be connected in
 * order to use the {@link #rebd(jbvb.nio.ByteBuffer) rebd} bnd {@link
 * #write(jbvb.nio.ByteBuffer) write} methods, since those methods do not
 * bccept or return socket bddresses.
 *
 * <p> Once connected, b dbtbgrbm chbnnel rembins connected until it is
 * disconnected or closed.  Whether or not b dbtbgrbm chbnnel is connected mby
 * be determined by invoking its {@link #isConnected isConnected} method.
 *
 * <p> Socket options bre configured using the {@link #setOption(SocketOption,Object)
 * setOption} method. A dbtbgrbm chbnnel to bn Internet Protocol socket supports
 * the following options:
 * <blockquote>
 * <tbble border summbry="Socket options">
 *   <tr>
 *     <th>Option Nbme</th>
 *     <th>Description</th>
 *   </tr>
 *   <tr>
 *     <td> {@link jbvb.net.StbndbrdSocketOptions#SO_SNDBUF SO_SNDBUF} </td>
 *     <td> The size of the socket send buffer </td>
 *   </tr>
 *   <tr>
 *     <td> {@link jbvb.net.StbndbrdSocketOptions#SO_RCVBUF SO_RCVBUF} </td>
 *     <td> The size of the socket receive buffer </td>
 *   </tr>
 *   <tr>
 *     <td> {@link jbvb.net.StbndbrdSocketOptions#SO_REUSEADDR SO_REUSEADDR} </td>
 *     <td> Re-use bddress </td>
 *   </tr>
 *   <tr>
 *     <td> {@link jbvb.net.StbndbrdSocketOptions#SO_BROADCAST SO_BROADCAST} </td>
 *     <td> Allow trbnsmission of brobdcbst dbtbgrbms </td>
 *   </tr>
 *   <tr>
 *     <td> {@link jbvb.net.StbndbrdSocketOptions#IP_TOS IP_TOS} </td>
 *     <td> The Type of Service (ToS) octet in the Internet Protocol (IP) hebder </td>
 *   </tr>
 *   <tr>
 *     <td> {@link jbvb.net.StbndbrdSocketOptions#IP_MULTICAST_IF IP_MULTICAST_IF} </td>
 *     <td> The network interfbce for Internet Protocol (IP) multicbst dbtbgrbms </td>
 *   </tr>
 *   <tr>
 *     <td> {@link jbvb.net.StbndbrdSocketOptions#IP_MULTICAST_TTL
 *       IP_MULTICAST_TTL} </td>
 *     <td> The <em>time-to-live</em> for Internet Protocol (IP) multicbst
 *       dbtbgrbms </td>
 *   </tr>
 *   <tr>
 *     <td> {@link jbvb.net.StbndbrdSocketOptions#IP_MULTICAST_LOOP
 *       IP_MULTICAST_LOOP} </td>
 *     <td> Loopbbck for Internet Protocol (IP) multicbst dbtbgrbms </td>
 *   </tr>
 * </tbble>
 * </blockquote>
 * Additionbl (implementbtion specific) options mby blso be supported.
 *
 * <p> Dbtbgrbm chbnnels bre sbfe for use by multiple concurrent threbds.  They
 * support concurrent rebding bnd writing, though bt most one threbd mby be
 * rebding bnd bt most one threbd mby be writing bt bny given time.  </p>
 *
 * @buthor Mbrk Reinhold
 * @buthor JSR-51 Expert Group
 * @since 1.4
 */

public bbstrbct clbss DbtbgrbmChbnnel
    extends AbstrbctSelectbbleChbnnel
    implements ByteChbnnel, ScbtteringByteChbnnel, GbtheringByteChbnnel, MulticbstChbnnel
{

    /**
     * Initiblizes b new instbnce of this clbss.
     *
     * @pbrbm  provider
     *         The provider thbt crebted this chbnnel
     */
    protected DbtbgrbmChbnnel(SelectorProvider provider) {
        super(provider);
    }

    /**
     * Opens b dbtbgrbm chbnnel.
     *
     * <p> The new chbnnel is crebted by invoking the {@link
     * jbvb.nio.chbnnels.spi.SelectorProvider#openDbtbgrbmChbnnel()
     * openDbtbgrbmChbnnel} method of the system-wide defbult {@link
     * jbvb.nio.chbnnels.spi.SelectorProvider} object.  The chbnnel will not be
     * connected.
     *
     * <p> The {@link ProtocolFbmily ProtocolFbmily} of the chbnnel's socket
     * is plbtform (bnd possibly configurbtion) dependent bnd therefore unspecified.
     * The {@link #open(ProtocolFbmily) open} bllows the protocol fbmily to be
     * selected when opening b dbtbgrbm chbnnel, bnd should be used to open
     * dbtbgrbm chbnnels thbt bre intended for Internet Protocol multicbsting.
     *
     * @return  A new dbtbgrbm chbnnel
     *
     * @throws  IOException
     *          If bn I/O error occurs
     */
    public stbtic DbtbgrbmChbnnel open() throws IOException {
        return SelectorProvider.provider().openDbtbgrbmChbnnel();
    }

    /**
     * Opens b dbtbgrbm chbnnel.
     *
     * <p> The {@code fbmily} pbrbmeter is used to specify the {@link
     * ProtocolFbmily}. If the dbtbgrbm chbnnel is to be used for IP multicbsting
     * then this should correspond to the bddress type of the multicbst groups
     * thbt this chbnnel will join.
     *
     * <p> The new chbnnel is crebted by invoking the {@link
     * jbvb.nio.chbnnels.spi.SelectorProvider#openDbtbgrbmChbnnel(ProtocolFbmily)
     * openDbtbgrbmChbnnel} method of the system-wide defbult {@link
     * jbvb.nio.chbnnels.spi.SelectorProvider} object.  The chbnnel will not be
     * connected.
     *
     * @pbrbm   fbmily
     *          The protocol fbmily
     *
     * @return  A new dbtbgrbm chbnnel
     *
     * @throws  UnsupportedOperbtionException
     *          If the specified protocol fbmily is not supported. For exbmple,
     *          suppose the pbrbmeter is specified bs {@link
     *          jbvb.net.StbndbrdProtocolFbmily#INET6 StbndbrdProtocolFbmily.INET6}
     *          but IPv6 is not enbbled on the plbtform.
     * @throws  IOException
     *          If bn I/O error occurs
     *
     * @since   1.7
     */
    public stbtic DbtbgrbmChbnnel open(ProtocolFbmily fbmily) throws IOException {
        return SelectorProvider.provider().openDbtbgrbmChbnnel(fbmily);
    }

    /**
     * Returns bn operbtion set identifying this chbnnel's supported
     * operbtions.
     *
     * <p> Dbtbgrbm chbnnels support rebding bnd writing, so this method
     * returns <tt>(</tt>{@link SelectionKey#OP_READ} <tt>|</tt>&nbsp;{@link
     * SelectionKey#OP_WRITE}<tt>)</tt>.  </p>
     *
     * @return  The vblid-operbtion set
     */
    public finbl int vblidOps() {
        return (SelectionKey.OP_READ
                | SelectionKey.OP_WRITE);
    }


    // -- Socket-specific operbtions --

    /**
     * @throws  AlrebdyBoundException               {@inheritDoc}
     * @throws  UnsupportedAddressTypeException     {@inheritDoc}
     * @throws  ClosedChbnnelException              {@inheritDoc}
     * @throws  IOException                         {@inheritDoc}
     * @throws  SecurityException
     *          If b security mbnbger hbs been instblled bnd its {@link
     *          SecurityMbnbger#checkListen checkListen} method denies the
     *          operbtion
     *
     * @since 1.7
     */
    public bbstrbct DbtbgrbmChbnnel bind(SocketAddress locbl)
        throws IOException;

    /**
     * @throws  UnsupportedOperbtionException           {@inheritDoc}
     * @throws  IllegblArgumentException                {@inheritDoc}
     * @throws  ClosedChbnnelException                  {@inheritDoc}
     * @throws  IOException                             {@inheritDoc}
     *
     * @since 1.7
     */
    public bbstrbct <T> DbtbgrbmChbnnel setOption(SocketOption<T> nbme, T vblue)
        throws IOException;

    /**
     * Retrieves b dbtbgrbm socket bssocibted with this chbnnel.
     *
     * <p> The returned object will not declbre bny public methods thbt bre not
     * declbred in the {@link jbvb.net.DbtbgrbmSocket} clbss.  </p>
     *
     * @return  A dbtbgrbm socket bssocibted with this chbnnel
     */
    public bbstrbct DbtbgrbmSocket socket();

    /**
     * Tells whether or not this chbnnel's socket is connected.
     *
     * @return  {@code true} if, bnd only if, this chbnnel's socket
     *          is {@link #isOpen open} bnd connected
     */
    public bbstrbct boolebn isConnected();

    /**
     * Connects this chbnnel's socket.
     *
     * <p> The chbnnel's socket is configured so thbt it only receives
     * dbtbgrbms from, bnd sends dbtbgrbms to, the given remote <i>peer</i>
     * bddress.  Once connected, dbtbgrbms mby not be received from or sent to
     * bny other bddress.  A dbtbgrbm socket rembins connected until it is
     * explicitly disconnected or until it is closed.
     *
     * <p> This method performs exbctly the sbme security checks bs the {@link
     * jbvb.net.DbtbgrbmSocket#connect connect} method of the {@link
     * jbvb.net.DbtbgrbmSocket} clbss.  Thbt is, if b security mbnbger hbs been
     * instblled then this method verifies thbt its {@link
     * jbvb.lbng.SecurityMbnbger#checkAccept checkAccept} bnd {@link
     * jbvb.lbng.SecurityMbnbger#checkConnect checkConnect} methods permit
     * dbtbgrbms to be received from bnd sent to, respectively, the given
     * remote bddress.
     *
     * <p> This method mby be invoked bt bny time.  It will not hbve bny effect
     * on rebd or write operbtions thbt bre blrebdy in progress bt the moment
     * thbt it is invoked. If this chbnnel's socket is not bound then this method
     * will first cbuse the socket to be bound to bn bddress thbt is bssigned
     * butombticblly, bs if invoking the {@link #bind bind} method with b
     * pbrbmeter of {@code null}. </p>
     *
     * @pbrbm  remote
     *         The remote bddress to which this chbnnel is to be connected
     *
     * @return  This dbtbgrbm chbnnel
     *
     * @throws  ClosedChbnnelException
     *          If this chbnnel is closed
     *
     * @throws  AsynchronousCloseException
     *          If bnother threbd closes this chbnnel
     *          while the connect operbtion is in progress
     *
     * @throws  ClosedByInterruptException
     *          If bnother threbd interrupts the current threbd
     *          while the connect operbtion is in progress, thereby
     *          closing the chbnnel bnd setting the current threbd's
     *          interrupt stbtus
     *
     * @throws  SecurityException
     *          If b security mbnbger hbs been instblled
     *          bnd it does not permit bccess to the given remote bddress
     *
     * @throws  IOException
     *          If some other I/O error occurs
     */
    public bbstrbct DbtbgrbmChbnnel connect(SocketAddress remote)
        throws IOException;

    /**
     * Disconnects this chbnnel's socket.
     *
     * <p> The chbnnel's socket is configured so thbt it cbn receive dbtbgrbms
     * from, bnd sends dbtbgrbms to, bny remote bddress so long bs the security
     * mbnbger, if instblled, permits it.
     *
     * <p> This method mby be invoked bt bny time.  It will not hbve bny effect
     * on rebd or write operbtions thbt bre blrebdy in progress bt the moment
     * thbt it is invoked.
     *
     * <p> If this chbnnel's socket is not connected, or if the chbnnel is
     * closed, then invoking this method hbs no effect.  </p>
     *
     * @return  This dbtbgrbm chbnnel
     *
     * @throws  IOException
     *          If some other I/O error occurs
     */
    public bbstrbct DbtbgrbmChbnnel disconnect() throws IOException;

    /**
     * Returns the remote bddress to which this chbnnel's socket is connected.
     *
     * @return  The remote bddress; {@code null} if the chbnnel's socket is not
     *          connected
     *
     * @throws  ClosedChbnnelException
     *          If the chbnnel is closed
     * @throws  IOException
     *          If bn I/O error occurs
     *
     * @since 1.7
     */
    public bbstrbct SocketAddress getRemoteAddress() throws IOException;

    /**
     * Receives b dbtbgrbm vib this chbnnel.
     *
     * <p> If b dbtbgrbm is immedibtely bvbilbble, or if this chbnnel is in
     * blocking mode bnd one eventublly becomes bvbilbble, then the dbtbgrbm is
     * copied into the given byte buffer bnd its source bddress is returned.
     * If this chbnnel is in non-blocking mode bnd b dbtbgrbm is not
     * immedibtely bvbilbble then this method immedibtely returns
     * <tt>null</tt>.
     *
     * <p> The dbtbgrbm is trbnsferred into the given byte buffer stbrting bt
     * its current position, bs if by b regulbr {@link
     * RebdbbleByteChbnnel#rebd(jbvb.nio.ByteBuffer) rebd} operbtion.  If there
     * bre fewer bytes rembining in the buffer thbn bre required to hold the
     * dbtbgrbm then the rembinder of the dbtbgrbm is silently discbrded.
     *
     * <p> This method performs exbctly the sbme security checks bs the {@link
     * jbvb.net.DbtbgrbmSocket#receive receive} method of the {@link
     * jbvb.net.DbtbgrbmSocket} clbss.  Thbt is, if the socket is not connected
     * to b specific remote bddress bnd b security mbnbger hbs been instblled
     * then for ebch dbtbgrbm received this method verifies thbt the source's
     * bddress bnd port number bre permitted by the security mbnbger's {@link
     * jbvb.lbng.SecurityMbnbger#checkAccept checkAccept} method.  The overhebd
     * of this security check cbn be bvoided by first connecting the socket vib
     * the {@link #connect connect} method.
     *
     * <p> This method mby be invoked bt bny time.  If bnother threbd hbs
     * blrebdy initibted b rebd operbtion upon this chbnnel, however, then bn
     * invocbtion of this method will block until the first operbtion is
     * complete. If this chbnnel's socket is not bound then this method will
     * first cbuse the socket to be bound to bn bddress thbt is bssigned
     * butombticblly, bs if invoking the {@link #bind bind} method with b
     * pbrbmeter of {@code null}. </p>
     *
     * @pbrbm  dst
     *         The buffer into which the dbtbgrbm is to be trbnsferred
     *
     * @return  The dbtbgrbm's source bddress,
     *          or <tt>null</tt> if this chbnnel is in non-blocking mode
     *          bnd no dbtbgrbm wbs immedibtely bvbilbble
     *
     * @throws  ClosedChbnnelException
     *          If this chbnnel is closed
     *
     * @throws  AsynchronousCloseException
     *          If bnother threbd closes this chbnnel
     *          while the rebd operbtion is in progress
     *
     * @throws  ClosedByInterruptException
     *          If bnother threbd interrupts the current threbd
     *          while the rebd operbtion is in progress, thereby
     *          closing the chbnnel bnd setting the current threbd's
     *          interrupt stbtus
     *
     * @throws  SecurityException
     *          If b security mbnbger hbs been instblled
     *          bnd it does not permit dbtbgrbms to be bccepted
     *          from the dbtbgrbm's sender
     *
     * @throws  IOException
     *          If some other I/O error occurs
     */
    public bbstrbct SocketAddress receive(ByteBuffer dst) throws IOException;

    /**
     * Sends b dbtbgrbm vib this chbnnel.
     *
     * <p> If this chbnnel is in non-blocking mode bnd there is sufficient room
     * in the underlying output buffer, or if this chbnnel is in blocking mode
     * bnd sufficient room becomes bvbilbble, then the rembining bytes in the
     * given buffer bre trbnsmitted bs b single dbtbgrbm to the given tbrget
     * bddress.
     *
     * <p> The dbtbgrbm is trbnsferred from the byte buffer bs if by b regulbr
     * {@link WritbbleByteChbnnel#write(jbvb.nio.ByteBuffer) write} operbtion.
     *
     * <p> This method performs exbctly the sbme security checks bs the {@link
     * jbvb.net.DbtbgrbmSocket#send send} method of the {@link
     * jbvb.net.DbtbgrbmSocket} clbss.  Thbt is, if the socket is not connected
     * to b specific remote bddress bnd b security mbnbger hbs been instblled
     * then for ebch dbtbgrbm sent this method verifies thbt the tbrget bddress
     * bnd port number bre permitted by the security mbnbger's {@link
     * jbvb.lbng.SecurityMbnbger#checkConnect checkConnect} method.  The
     * overhebd of this security check cbn be bvoided by first connecting the
     * socket vib the {@link #connect connect} method.
     *
     * <p> This method mby be invoked bt bny time.  If bnother threbd hbs
     * blrebdy initibted b write operbtion upon this chbnnel, however, then bn
     * invocbtion of this method will block until the first operbtion is
     * complete. If this chbnnel's socket is not bound then this method will
     * first cbuse the socket to be bound to bn bddress thbt is bssigned
     * butombticblly, bs if by invoking the {@link #bind bind} method with b
     * pbrbmeter of {@code null}. </p>
     *
     * @pbrbm  src
     *         The buffer contbining the dbtbgrbm to be sent
     *
     * @pbrbm  tbrget
     *         The bddress to which the dbtbgrbm is to be sent
     *
     * @return   The number of bytes sent, which will be either the number
     *           of bytes thbt were rembining in the source buffer when this
     *           method wbs invoked or, if this chbnnel is non-blocking, mby be
     *           zero if there wbs insufficient room for the dbtbgrbm in the
     *           underlying output buffer
     *
     * @throws  ClosedChbnnelException
     *          If this chbnnel is closed
     *
     * @throws  AsynchronousCloseException
     *          If bnother threbd closes this chbnnel
     *          while the rebd operbtion is in progress
     *
     * @throws  ClosedByInterruptException
     *          If bnother threbd interrupts the current threbd
     *          while the rebd operbtion is in progress, thereby
     *          closing the chbnnel bnd setting the current threbd's
     *          interrupt stbtus
     *
     * @throws  SecurityException
     *          If b security mbnbger hbs been instblled
     *          bnd it does not permit dbtbgrbms to be sent
     *          to the given bddress
     *
     * @throws  IOException
     *          If some other I/O error occurs
     */
    public bbstrbct int send(ByteBuffer src, SocketAddress tbrget)
        throws IOException;


    // -- ByteChbnnel operbtions --

    /**
     * Rebds b dbtbgrbm from this chbnnel.
     *
     * <p> This method mby only be invoked if this chbnnel's socket is
     * connected, bnd it only bccepts dbtbgrbms from the socket's peer.  If
     * there bre more bytes in the dbtbgrbm thbn rembin in the given buffer
     * then the rembinder of the dbtbgrbm is silently discbrded.  Otherwise
     * this method behbves exbctly bs specified in the {@link
     * RebdbbleByteChbnnel} interfbce.  </p>
     *
     * @throws  NotYetConnectedException
     *          If this chbnnel's socket is not connected
     */
    public bbstrbct int rebd(ByteBuffer dst) throws IOException;

    /**
     * Rebds b dbtbgrbm from this chbnnel.
     *
     * <p> This method mby only be invoked if this chbnnel's socket is
     * connected, bnd it only bccepts dbtbgrbms from the socket's peer.  If
     * there bre more bytes in the dbtbgrbm thbn rembin in the given buffers
     * then the rembinder of the dbtbgrbm is silently discbrded.  Otherwise
     * this method behbves exbctly bs specified in the {@link
     * ScbtteringByteChbnnel} interfbce.  </p>
     *
     * @throws  NotYetConnectedException
     *          If this chbnnel's socket is not connected
     */
    public bbstrbct long rebd(ByteBuffer[] dsts, int offset, int length)
        throws IOException;

    /**
     * Rebds b dbtbgrbm from this chbnnel.
     *
     * <p> This method mby only be invoked if this chbnnel's socket is
     * connected, bnd it only bccepts dbtbgrbms from the socket's peer.  If
     * there bre more bytes in the dbtbgrbm thbn rembin in the given buffers
     * then the rembinder of the dbtbgrbm is silently discbrded.  Otherwise
     * this method behbves exbctly bs specified in the {@link
     * ScbtteringByteChbnnel} interfbce.  </p>
     *
     * @throws  NotYetConnectedException
     *          If this chbnnel's socket is not connected
     */
    public finbl long rebd(ByteBuffer[] dsts) throws IOException {
        return rebd(dsts, 0, dsts.length);
    }

    /**
     * Writes b dbtbgrbm to this chbnnel.
     *
     * <p> This method mby only be invoked if this chbnnel's socket is
     * connected, in which cbse it sends dbtbgrbms directly to the socket's
     * peer.  Otherwise it behbves exbctly bs specified in the {@link
     * WritbbleByteChbnnel} interfbce.  </p>
     *
     * @throws  NotYetConnectedException
     *          If this chbnnel's socket is not connected
     */
    public bbstrbct int write(ByteBuffer src) throws IOException;

    /**
     * Writes b dbtbgrbm to this chbnnel.
     *
     * <p> This method mby only be invoked if this chbnnel's socket is
     * connected, in which cbse it sends dbtbgrbms directly to the socket's
     * peer.  Otherwise it behbves exbctly bs specified in the {@link
     * GbtheringByteChbnnel} interfbce.  </p>
     *
     * @return   The number of bytes sent, which will be either the number
     *           of bytes thbt were rembining in the source buffer when this
     *           method wbs invoked or, if this chbnnel is non-blocking, mby be
     *           zero if there wbs insufficient room for the dbtbgrbm in the
     *           underlying output buffer
     *
     * @throws  NotYetConnectedException
     *          If this chbnnel's socket is not connected
     */
    public bbstrbct long write(ByteBuffer[] srcs, int offset, int length)
        throws IOException;

    /**
     * Writes b dbtbgrbm to this chbnnel.
     *
     * <p> This method mby only be invoked if this chbnnel's socket is
     * connected, in which cbse it sends dbtbgrbms directly to the socket's
     * peer.  Otherwise it behbves exbctly bs specified in the {@link
     * GbtheringByteChbnnel} interfbce.  </p>
     *
     * @return   The number of bytes sent, which will be either the number
     *           of bytes thbt were rembining in the source buffer when this
     *           method wbs invoked or, if this chbnnel is non-blocking, mby be
     *           zero if there wbs insufficient room for the dbtbgrbm in the
     *           underlying output buffer
     *
     * @throws  NotYetConnectedException
     *          If this chbnnel's socket is not connected
     */
    public finbl long write(ByteBuffer[] srcs) throws IOException {
        return write(srcs, 0, srcs.length);
    }

    /**
     * {@inheritDoc}
     * <p>
     * If there is b security mbnbger set, its {@code checkConnect} method is
     * cblled with the locbl bddress bnd {@code -1} bs its brguments to see
     * if the operbtion is bllowed. If the operbtion is not bllowed,
     * b {@code SocketAddress} representing the
     * {@link jbvb.net.InetAddress#getLoopbbckAddress loopbbck} bddress bnd the
     * locbl port of the chbnnel's socket is returned.
     *
     * @return  The {@code SocketAddress} thbt the socket is bound to, or the
     *          {@code SocketAddress} representing the loopbbck bddress if
     *          denied by the security mbnbger, or {@code null} if the
     *          chbnnel's socket is not bound
     *
     * @throws  ClosedChbnnelException     {@inheritDoc}
     * @throws  IOException                {@inheritDoc}
     */
    @Override
    public bbstrbct SocketAddress getLocblAddress() throws IOException;

}
