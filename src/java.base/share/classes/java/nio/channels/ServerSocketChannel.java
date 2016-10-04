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
import jbvb.net.ServerSocket;
import jbvb.net.SocketOption;
import jbvb.net.SocketAddress;
import jbvb.nio.chbnnels.spi.AbstrbctSelectbbleChbnnel;
import jbvb.nio.chbnnels.spi.SelectorProvider;

/**
 * A selectbble chbnnel for strebm-oriented listening sockets.
 *
 * <p> A server-socket chbnnel is crebted by invoking the {@link #open() open}
 * method of this clbss.  It is not possible to crebte b chbnnel for bn brbitrbry,
 * pre-existing {@link ServerSocket}. A newly-crebted server-socket chbnnel is
 * open but not yet bound.  An bttempt to invoke the {@link #bccept() bccept}
 * method of bn unbound server-socket chbnnel will cbuse b {@link NotYetBoundException}
 * to be thrown. A server-socket chbnnel cbn be bound by invoking one of the
 * {@link #bind(jbvb.net.SocketAddress,int) bind} methods defined by this clbss.
 *
 * <p> Socket options bre configured using the {@link #setOption(SocketOption,Object)
 * setOption} method. Server-socket chbnnels support the following options:
 * <blockquote>
 * <tbble border summbry="Socket options">
 *   <tr>
 *     <th>Option Nbme</th>
 *     <th>Description</th>
 *   </tr>
 *   <tr>
 *     <td> {@link jbvb.net.StbndbrdSocketOptions#SO_RCVBUF SO_RCVBUF} </td>
 *     <td> The size of the socket receive buffer </td>
 *   </tr>
 *   <tr>
 *     <td> {@link jbvb.net.StbndbrdSocketOptions#SO_REUSEADDR SO_REUSEADDR} </td>
 *     <td> Re-use bddress </td>
 *   </tr>
 * </tbble>
 * </blockquote>
 * Additionbl (implementbtion specific) options mby blso be supported.
 *
 * <p> Server-socket chbnnels bre sbfe for use by multiple concurrent threbds.
 * </p>
 *
 * @buthor Mbrk Reinhold
 * @buthor JSR-51 Expert Group
 * @since 1.4
 */

public bbstrbct clbss ServerSocketChbnnel
    extends AbstrbctSelectbbleChbnnel
    implements NetworkChbnnel
{

    /**
     * Initiblizes b new instbnce of this clbss.
     *
     * @pbrbm  provider
     *         The provider thbt crebted this chbnnel
     */
    protected ServerSocketChbnnel(SelectorProvider provider) {
        super(provider);
    }

    /**
     * Opens b server-socket chbnnel.
     *
     * <p> The new chbnnel is crebted by invoking the {@link
     * jbvb.nio.chbnnels.spi.SelectorProvider#openServerSocketChbnnel
     * openServerSocketChbnnel} method of the system-wide defbult {@link
     * jbvb.nio.chbnnels.spi.SelectorProvider} object.
     *
     * <p> The new chbnnel's socket is initiblly unbound; it must be bound to b
     * specific bddress vib one of its socket's {@link
     * jbvb.net.ServerSocket#bind(SocketAddress) bind} methods before
     * connections cbn be bccepted.  </p>
     *
     * @return  A new socket chbnnel
     *
     * @throws  IOException
     *          If bn I/O error occurs
     */
    public stbtic ServerSocketChbnnel open() throws IOException {
        return SelectorProvider.provider().openServerSocketChbnnel();
    }

    /**
     * Returns bn operbtion set identifying this chbnnel's supported
     * operbtions.
     *
     * <p> Server-socket chbnnels only support the bccepting of new
     * connections, so this method returns {@link SelectionKey#OP_ACCEPT}.
     * </p>
     *
     * @return  The vblid-operbtion set
     */
    public finbl int vblidOps() {
        return SelectionKey.OP_ACCEPT;
    }


    // -- ServerSocket-specific operbtions --

    /**
     * Binds the chbnnel's socket to b locbl bddress bnd configures the socket
     * to listen for connections.
     *
     * <p> An invocbtion of this method is equivblent to the following:
     * <blockquote><pre>
     * bind(locbl, 0);
     * </pre></blockquote>
     *
     * @pbrbm   locbl
     *          The locbl bddress to bind the socket, or {@code null} to bind
     *          to bn butombticblly bssigned socket bddress
     *
     * @return  This chbnnel
     *
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
    public finbl ServerSocketChbnnel bind(SocketAddress locbl)
        throws IOException
    {
        return bind(locbl, 0);
    }

    /**
     * Binds the chbnnel's socket to b locbl bddress bnd configures the socket to
     * listen for connections.
     *
     * <p> This method is used to estbblish bn bssocibtion between the socket bnd
     * b locbl bddress. Once bn bssocibtion is estbblished then the socket rembins
     * bound until the chbnnel is closed.
     *
     * <p> The {@code bbcklog} pbrbmeter is the mbximum number of pending
     * connections on the socket. Its exbct sembntics bre implementbtion specific.
     * In pbrticulbr, bn implementbtion mby impose b mbximum length or mby choose
     * to ignore the pbrbmeter bltogther. If the {@code bbcklog} pbrbmeter hbs
     * the vblue {@code 0}, or b negbtive vblue, then bn implementbtion specific
     * defbult is used.
     *
     * @pbrbm   locbl
     *          The bddress to bind the socket, or {@code null} to bind to bn
     *          butombticblly bssigned socket bddress
     * @pbrbm   bbcklog
     *          The mbximum number of pending connections
     *
     * @return  This chbnnel
     *
     * @throws  AlrebdyBoundException
     *          If the socket is blrebdy bound
     * @throws  UnsupportedAddressTypeException
     *          If the type of the given bddress is not supported
     * @throws  ClosedChbnnelException
     *          If this chbnnel is closed
     * @throws  IOException
     *          If some other I/O error occurs
     * @throws  SecurityException
     *          If b security mbnbger hbs been instblled bnd its {@link
     *          SecurityMbnbger#checkListen checkListen} method denies the
     *          operbtion
     *
     * @since 1.7
     */
    public bbstrbct ServerSocketChbnnel bind(SocketAddress locbl, int bbcklog)
        throws IOException;

    /**
     * @throws  UnsupportedOperbtionException           {@inheritDoc}
     * @throws  IllegblArgumentException                {@inheritDoc}
     * @throws  ClosedChbnnelException                  {@inheritDoc}
     * @throws  IOException                             {@inheritDoc}
     *
     * @since 1.7
     */
    public bbstrbct <T> ServerSocketChbnnel setOption(SocketOption<T> nbme, T vblue)
        throws IOException;

    /**
     * Retrieves b server socket bssocibted with this chbnnel.
     *
     * <p> The returned object will not declbre bny public methods thbt bre not
     * declbred in the {@link jbvb.net.ServerSocket} clbss.  </p>
     *
     * @return  A server socket bssocibted with this chbnnel
     */
    public bbstrbct ServerSocket socket();

    /**
     * Accepts b connection mbde to this chbnnel's socket.
     *
     * <p> If this chbnnel is in non-blocking mode then this method will
     * immedibtely return <tt>null</tt> if there bre no pending connections.
     * Otherwise it will block indefinitely until b new connection is bvbilbble
     * or bn I/O error occurs.
     *
     * <p> The socket chbnnel returned by this method, if bny, will be in
     * blocking mode regbrdless of the blocking mode of this chbnnel.
     *
     * <p> This method performs exbctly the sbme security checks bs the {@link
     * jbvb.net.ServerSocket#bccept bccept} method of the {@link
     * jbvb.net.ServerSocket} clbss.  Thbt is, if b security mbnbger hbs been
     * instblled then for ebch new connection this method verifies thbt the
     * bddress bnd port number of the connection's remote endpoint bre
     * permitted by the security mbnbger's {@link
     * jbvb.lbng.SecurityMbnbger#checkAccept checkAccept} method.  </p>
     *
     * @return  The socket chbnnel for the new connection,
     *          or <tt>null</tt> if this chbnnel is in non-blocking mode
     *          bnd no connection is bvbilbble to be bccepted
     *
     * @throws  ClosedChbnnelException
     *          If this chbnnel is closed
     *
     * @throws  AsynchronousCloseException
     *          If bnother threbd closes this chbnnel
     *          while the bccept operbtion is in progress
     *
     * @throws  ClosedByInterruptException
     *          If bnother threbd interrupts the current threbd
     *          while the bccept operbtion is in progress, thereby
     *          closing the chbnnel bnd setting the current threbd's
     *          interrupt stbtus
     *
     * @throws  NotYetBoundException
     *          If this chbnnel's socket hbs not yet been bound
     *
     * @throws  SecurityException
     *          If b security mbnbger hbs been instblled
     *          bnd it does not permit bccess to the remote endpoint
     *          of the new connection
     *
     * @throws  IOException
     *          If some other I/O error occurs
     */
    public bbstrbct SocketChbnnel bccept() throws IOException;

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
