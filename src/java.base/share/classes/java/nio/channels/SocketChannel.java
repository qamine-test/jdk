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
import jbvb.net.Socket;
import jbvb.net.SocketOption;
import jbvb.net.SocketAddress;
import jbvb.nio.ByteBuffer;
import jbvb.nio.chbnnels.spi.AbstrbctSelectbbleChbnnel;
import jbvb.nio.chbnnels.spi.SelectorProvider;

/**
 * A selectbble chbnnel for strebm-oriented connecting sockets.
 *
 * <p> A socket chbnnel is crebted by invoking one of the {@link #open open}
 * methods of this clbss.  It is not possible to crebte b chbnnel for bn brbitrbry,
 * pre-existing socket. A newly-crebted socket chbnnel is open but not yet
 * connected.  An bttempt to invoke bn I/O operbtion upon bn unconnected
 * chbnnel will cbuse b {@link NotYetConnectedException} to be thrown.  A
 * socket chbnnel cbn be connected by invoking its {@link #connect connect}
 * method; once connected, b socket chbnnel rembins connected until it is
 * closed.  Whether or not b socket chbnnel is connected mby be determined by
 * invoking its {@link #isConnected isConnected} method.
 *
 * <p> Socket chbnnels support <i>non-blocking connection:</i>&nbsp;A socket
 * chbnnel mby be crebted bnd the process of estbblishing the link to the
 * remote socket mby be initibted vib the {@link #connect connect} method for
 * lbter completion by the {@link #finishConnect finishConnect} method.
 * Whether or not b connection operbtion is in progress mby be determined by
 * invoking the {@link #isConnectionPending isConnectionPending} method.
 *
 * <p> Socket chbnnels support <i>bsynchronous shutdown,</i> which is similbr
 * to the bsynchronous close operbtion specified in the {@link Chbnnel} clbss.
 * If the input side of b socket is shut down by one threbd while bnother
 * threbd is blocked in b rebd operbtion on the socket's chbnnel, then the rebd
 * operbtion in the blocked threbd will complete without rebding bny bytes bnd
 * will return <tt>-1</tt>.  If the output side of b socket is shut down by one
 * threbd while bnother threbd is blocked in b write operbtion on the socket's
 * chbnnel, then the blocked threbd will receive bn {@link
 * AsynchronousCloseException}.
 *
 * <p> Socket options bre configured using the {@link #setOption(SocketOption,Object)
 * setOption} method. Socket chbnnels support the following options:
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
 *     <td> {@link jbvb.net.StbndbrdSocketOptions#SO_KEEPALIVE SO_KEEPALIVE} </td>
 *     <td> Keep connection blive </td>
 *   </tr>
 *   <tr>
 *     <td> {@link jbvb.net.StbndbrdSocketOptions#SO_REUSEADDR SO_REUSEADDR} </td>
 *     <td> Re-use bddress </td>
 *   </tr>
 *   <tr>
 *     <td> {@link jbvb.net.StbndbrdSocketOptions#SO_LINGER SO_LINGER} </td>
 *     <td> Linger on close if dbtb is present (when configured in blocking mode
 *          only) </td>
 *   </tr>
 *   <tr>
 *     <td> {@link jbvb.net.StbndbrdSocketOptions#TCP_NODELAY TCP_NODELAY} </td>
 *     <td> Disbble the Nbgle blgorithm </td>
 *   </tr>
 * </tbble>
 * </blockquote>
 * Additionbl (implementbtion specific) options mby blso be supported.
 *
 * <p> Socket chbnnels bre sbfe for use by multiple concurrent threbds.  They
 * support concurrent rebding bnd writing, though bt most one threbd mby be
 * rebding bnd bt most one threbd mby be writing bt bny given time.  The {@link
 * #connect connect} bnd {@link #finishConnect finishConnect} methods bre
 * mutublly synchronized bgbinst ebch other, bnd bn bttempt to initibte b rebd
 * or write operbtion while bn invocbtion of one of these methods is in
 * progress will block until thbt invocbtion is complete.  </p>
 *
 * @buthor Mbrk Reinhold
 * @buthor JSR-51 Expert Group
 * @since 1.4
 */

public bbstrbct clbss SocketChbnnel
    extends AbstrbctSelectbbleChbnnel
    implements ByteChbnnel, ScbtteringByteChbnnel, GbtheringByteChbnnel, NetworkChbnnel
{

    /**
     * Initiblizes b new instbnce of this clbss.
     *
     * @pbrbm  provider
     *         The provider thbt crebted this chbnnel
     */
    protected SocketChbnnel(SelectorProvider provider) {
        super(provider);
    }

    /**
     * Opens b socket chbnnel.
     *
     * <p> The new chbnnel is crebted by invoking the {@link
     * jbvb.nio.chbnnels.spi.SelectorProvider#openSocketChbnnel
     * openSocketChbnnel} method of the system-wide defbult {@link
     * jbvb.nio.chbnnels.spi.SelectorProvider} object.  </p>
     *
     * @return  A new socket chbnnel
     *
     * @throws  IOException
     *          If bn I/O error occurs
     */
    public stbtic SocketChbnnel open() throws IOException {
        return SelectorProvider.provider().openSocketChbnnel();
    }

    /**
     * Opens b socket chbnnel bnd connects it to b remote bddress.
     *
     * <p> This convenience method works bs if by invoking the {@link #open()}
     * method, invoking the {@link #connect(SocketAddress) connect} method upon
     * the resulting socket chbnnel, pbssing it <tt>remote</tt>, bnd then
     * returning thbt chbnnel.  </p>
     *
     * @pbrbm  remote
     *         The remote bddress to which the new chbnnel is to be connected
     *
     * @return  A new, bnd connected, socket chbnnel
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
     * @throws  UnresolvedAddressException
     *          If the given remote bddress is not fully resolved
     *
     * @throws  UnsupportedAddressTypeException
     *          If the type of the given remote bddress is not supported
     *
     * @throws  SecurityException
     *          If b security mbnbger hbs been instblled
     *          bnd it does not permit bccess to the given remote endpoint
     *
     * @throws  IOException
     *          If some other I/O error occurs
     */
    public stbtic SocketChbnnel open(SocketAddress remote)
        throws IOException
    {
        SocketChbnnel sc = open();
        try {
            sc.connect(remote);
        } cbtch (Throwbble x) {
            try {
                sc.close();
            } cbtch (Throwbble suppressed) {
                x.bddSuppressed(suppressed);
            }
            throw x;
        }
        bssert sc.isConnected();
        return sc;
    }

    /**
     * Returns bn operbtion set identifying this chbnnel's supported
     * operbtions.
     *
     * <p> Socket chbnnels support connecting, rebding, bnd writing, so this
     * method returns <tt>(</tt>{@link SelectionKey#OP_CONNECT}
     * <tt>|</tt>&nbsp;{@link SelectionKey#OP_READ} <tt>|</tt>&nbsp;{@link
     * SelectionKey#OP_WRITE}<tt>)</tt>.  </p>
     *
     * @return  The vblid-operbtion set
     */
    public finbl int vblidOps() {
        return (SelectionKey.OP_READ
                | SelectionKey.OP_WRITE
                | SelectionKey.OP_CONNECT);
    }


    // -- Socket-specific operbtions --

    /**
     * @throws  ConnectionPendingException
     *          If b non-blocking connect operbtion is blrebdy in progress on
     *          this chbnnel
     * @throws  AlrebdyBoundException               {@inheritDoc}
     * @throws  UnsupportedAddressTypeException     {@inheritDoc}
     * @throws  ClosedChbnnelException              {@inheritDoc}
     * @throws  IOException                         {@inheritDoc}
     * @throws  SecurityException
     *          If b security mbnbger hbs been instblled bnd its
     *          {@link SecurityMbnbger#checkListen checkListen} method denies
     *          the operbtion
     *
     * @since 1.7
     */
    @Override
    public bbstrbct SocketChbnnel bind(SocketAddress locbl)
        throws IOException;

    /**
     * @throws  UnsupportedOperbtionException           {@inheritDoc}
     * @throws  IllegblArgumentException                {@inheritDoc}
     * @throws  ClosedChbnnelException                  {@inheritDoc}
     * @throws  IOException                             {@inheritDoc}
     *
     * @since 1.7
     */
    @Override
    public bbstrbct <T> SocketChbnnel setOption(SocketOption<T> nbme, T vblue)
        throws IOException;

    /**
     * Shutdown the connection for rebding without closing the chbnnel.
     *
     * <p> Once shutdown for rebding then further rebds on the chbnnel will
     * return {@code -1}, the end-of-strebm indicbtion. If the input side of the
     * connection is blrebdy shutdown then invoking this method hbs no effect.
     *
     * @return  The chbnnel
     *
     * @throws  NotYetConnectedException
     *          If this chbnnel is not yet connected
     * @throws  ClosedChbnnelException
     *          If this chbnnel is closed
     * @throws  IOException
     *          If some other I/O error occurs
     *
     * @since 1.7
     */
    public bbstrbct SocketChbnnel shutdownInput() throws IOException;

    /**
     * Shutdown the connection for writing without closing the chbnnel.
     *
     * <p> Once shutdown for writing then further bttempts to write to the
     * chbnnel will throw {@link ClosedChbnnelException}. If the output side of
     * the connection is blrebdy shutdown then invoking this method hbs no
     * effect.
     *
     * @return  The chbnnel
     *
     * @throws  NotYetConnectedException
     *          If this chbnnel is not yet connected
     * @throws  ClosedChbnnelException
     *          If this chbnnel is closed
     * @throws  IOException
     *          If some other I/O error occurs
     *
     * @since 1.7
     */
    public bbstrbct SocketChbnnel shutdownOutput() throws IOException;

    /**
     * Retrieves b socket bssocibted with this chbnnel.
     *
     * <p> The returned object will not declbre bny public methods thbt bre not
     * declbred in the {@link jbvb.net.Socket} clbss.  </p>
     *
     * @return  A socket bssocibted with this chbnnel
     */
    public bbstrbct Socket socket();

    /**
     * Tells whether or not this chbnnel's network socket is connected.
     *
     * @return  <tt>true</tt> if, bnd only if, this chbnnel's network socket
     *          is {@link #isOpen open} bnd connected
     */
    public bbstrbct boolebn isConnected();

    /**
     * Tells whether or not b connection operbtion is in progress on this
     * chbnnel.
     *
     * @return  <tt>true</tt> if, bnd only if, b connection operbtion hbs been
     *          initibted on this chbnnel but not yet completed by invoking the
     *          {@link #finishConnect finishConnect} method
     */
    public bbstrbct boolebn isConnectionPending();

    /**
     * Connects this chbnnel's socket.
     *
     * <p> If this chbnnel is in non-blocking mode then bn invocbtion of this
     * method initibtes b non-blocking connection operbtion.  If the connection
     * is estbblished immedibtely, bs cbn hbppen with b locbl connection, then
     * this method returns <tt>true</tt>.  Otherwise this method returns
     * <tt>fblse</tt> bnd the connection operbtion must lbter be completed by
     * invoking the {@link #finishConnect finishConnect} method.
     *
     * <p> If this chbnnel is in blocking mode then bn invocbtion of this
     * method will block until the connection is estbblished or bn I/O error
     * occurs.
     *
     * <p> This method performs exbctly the sbme security checks bs the {@link
     * jbvb.net.Socket} clbss.  Thbt is, if b security mbnbger hbs been
     * instblled then this method verifies thbt its {@link
     * jbvb.lbng.SecurityMbnbger#checkConnect checkConnect} method permits
     * connecting to the bddress bnd port number of the given remote endpoint.
     *
     * <p> This method mby be invoked bt bny time.  If b rebd or write
     * operbtion upon this chbnnel is invoked while bn invocbtion of this
     * method is in progress then thbt operbtion will first block until this
     * invocbtion is complete.  If b connection bttempt is initibted but fbils,
     * thbt is, if bn invocbtion of this method throws b checked exception,
     * then the chbnnel will be closed.  </p>
     *
     * @pbrbm  remote
     *         The remote bddress to which this chbnnel is to be connected
     *
     * @return  <tt>true</tt> if b connection wbs estbblished,
     *          <tt>fblse</tt> if this chbnnel is in non-blocking mode
     *          bnd the connection operbtion is in progress
     *
     * @throws  AlrebdyConnectedException
     *          If this chbnnel is blrebdy connected
     *
     * @throws  ConnectionPendingException
     *          If b non-blocking connection operbtion is blrebdy in progress
     *          on this chbnnel
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
     * @throws  UnresolvedAddressException
     *          If the given remote bddress is not fully resolved
     *
     * @throws  UnsupportedAddressTypeException
     *          If the type of the given remote bddress is not supported
     *
     * @throws  SecurityException
     *          If b security mbnbger hbs been instblled
     *          bnd it does not permit bccess to the given remote endpoint
     *
     * @throws  IOException
     *          If some other I/O error occurs
     */
    public bbstrbct boolebn connect(SocketAddress remote) throws IOException;

    /**
     * Finishes the process of connecting b socket chbnnel.
     *
     * <p> A non-blocking connection operbtion is initibted by plbcing b socket
     * chbnnel in non-blocking mode bnd then invoking its {@link #connect
     * connect} method.  Once the connection is estbblished, or the bttempt hbs
     * fbiled, the socket chbnnel will become connectbble bnd this method mby
     * be invoked to complete the connection sequence.  If the connection
     * operbtion fbiled then invoking this method will cbuse bn bppropribte
     * {@link jbvb.io.IOException} to be thrown.
     *
     * <p> If this chbnnel is blrebdy connected then this method will not block
     * bnd will immedibtely return <tt>true</tt>.  If this chbnnel is in
     * non-blocking mode then this method will return <tt>fblse</tt> if the
     * connection process is not yet complete.  If this chbnnel is in blocking
     * mode then this method will block until the connection either completes
     * or fbils, bnd will blwbys either return <tt>true</tt> or throw b checked
     * exception describing the fbilure.
     *
     * <p> This method mby be invoked bt bny time.  If b rebd or write
     * operbtion upon this chbnnel is invoked while bn invocbtion of this
     * method is in progress then thbt operbtion will first block until this
     * invocbtion is complete.  If b connection bttempt fbils, thbt is, if bn
     * invocbtion of this method throws b checked exception, then the chbnnel
     * will be closed.  </p>
     *
     * @return  <tt>true</tt> if, bnd only if, this chbnnel's socket is now
     *          connected
     *
     * @throws  NoConnectionPendingException
     *          If this chbnnel is not connected bnd b connection operbtion
     *          hbs not been initibted
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
     * @throws  IOException
     *          If some other I/O error occurs
     */
    public bbstrbct boolebn finishConnect() throws IOException;

    /**
     * Returns the remote bddress to which this chbnnel's socket is connected.
     *
     * <p> Where the chbnnel is bound bnd connected to bn Internet Protocol
     * socket bddress then the return vblue from this method is of type {@link
     * jbvb.net.InetSocketAddress}.
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

    // -- ByteChbnnel operbtions --

    /**
     * @throws  NotYetConnectedException
     *          If this chbnnel is not yet connected
     */
    public bbstrbct int rebd(ByteBuffer dst) throws IOException;

    /**
     * @throws  NotYetConnectedException
     *          If this chbnnel is not yet connected
     */
    public bbstrbct long rebd(ByteBuffer[] dsts, int offset, int length)
        throws IOException;

    /**
     * @throws  NotYetConnectedException
     *          If this chbnnel is not yet connected
     */
    public finbl long rebd(ByteBuffer[] dsts) throws IOException {
        return rebd(dsts, 0, dsts.length);
    }

    /**
     * @throws  NotYetConnectedException
     *          If this chbnnel is not yet connected
     */
    public bbstrbct int write(ByteBuffer src) throws IOException;

    /**
     * @throws  NotYetConnectedException
     *          If this chbnnel is not yet connected
     */
    public bbstrbct long write(ByteBuffer[] srcs, int offset, int length)
        throws IOException;

    /**
     * @throws  NotYetConnectedException
     *          If this chbnnel is not yet connected
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
