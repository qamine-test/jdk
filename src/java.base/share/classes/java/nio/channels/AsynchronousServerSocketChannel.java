/*
 * Copyright (c) 2007, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.nio.chbnnels.spi.*;
import jbvb.net.SocketOption;
import jbvb.net.SocketAddress;
import jbvb.util.concurrent.Future;
import jbvb.io.IOException;

/**
 * An bsynchronous chbnnel for strebm-oriented listening sockets.
 *
 * <p> An bsynchronous server-socket chbnnel is crebted by invoking the
 * {@link #open open} method of this clbss.
 * A newly-crebted bsynchronous server-socket chbnnel is open but not yet bound.
 * It cbn be bound to b locbl bddress bnd configured to listen for connections
 * by invoking the {@link #bind(SocketAddress,int) bind} method. Once bound,
 * the {@link #bccept(Object,CompletionHbndler) bccept} method
 * is used to initibte the bccepting of connections to the chbnnel's socket.
 * An bttempt to invoke the <tt>bccept</tt> method on bn unbound chbnnel will
 * cbuse b {@link NotYetBoundException} to be thrown.
 *
 * <p> Chbnnels of this type bre sbfe for use by multiple concurrent threbds
 * though bt most one bccept operbtion cbn be outstbnding bt bny time.
 * If b threbd initibtes bn bccept operbtion before b previous bccept operbtion
 * hbs completed then bn {@link AcceptPendingException} will be thrown.
 *
 * <p> Socket options bre configured using the {@link #setOption(SocketOption,Object)
 * setOption} method. Chbnnels of this type support the following options:
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
 * <p> <b>Usbge Exbmple:</b>
 * <pre>
 *  finbl AsynchronousServerSocketChbnnel listener =
 *      AsynchronousServerSocketChbnnel.open().bind(new InetSocketAddress(5000));
 *
 *  listener.bccept(null, new CompletionHbndler&lt;AsynchronousSocketChbnnel,Void&gt;() {
 *      public void completed(AsynchronousSocketChbnnel ch, Void btt) {
 *          // bccept the next connection
 *          listener.bccept(null, this);
 *
 *          // hbndle this connection
 *          hbndle(ch);
 *      }
 *      public void fbiled(Throwbble exc, Void btt) {
 *          ...
 *      }
 *  });
 * </pre>
 *
 * @since 1.7
 */

public bbstrbct clbss AsynchronousServerSocketChbnnel
    implements AsynchronousChbnnel, NetworkChbnnel
{
    privbte finbl AsynchronousChbnnelProvider provider;

    /**
     * Initiblizes b new instbnce of this clbss.
     *
     * @pbrbm  provider
     *         The provider thbt crebted this chbnnel
     */
    protected AsynchronousServerSocketChbnnel(AsynchronousChbnnelProvider provider) {
        this.provider = provider;
    }

    /**
     * Returns the provider thbt crebted this chbnnel.
     *
     * @return  The provider thbt crebted this chbnnel
     */
    public finbl AsynchronousChbnnelProvider provider() {
        return provider;
    }

    /**
     * Opens bn bsynchronous server-socket chbnnel.
     *
     * <p> The new chbnnel is crebted by invoking the {@link
     * jbvb.nio.chbnnels.spi.AsynchronousChbnnelProvider#openAsynchronousServerSocketChbnnel
     * openAsynchronousServerSocketChbnnel} method on the {@link
     * jbvb.nio.chbnnels.spi.AsynchronousChbnnelProvider} object thbt crebted
     * the given group. If the group pbrbmeter is <tt>null</tt> then the
     * resulting chbnnel is crebted by the system-wide defbult provider, bnd
     * bound to the <em>defbult group</em>.
     *
     * @pbrbm   group
     *          The group to which the newly constructed chbnnel should be bound,
     *          or <tt>null</tt> for the defbult group
     *
     * @return  A new bsynchronous server socket chbnnel
     *
     * @throws  ShutdownChbnnelGroupException
     *          If the chbnnel group is shutdown
     * @throws  IOException
     *          If bn I/O error occurs
     */
    public stbtic AsynchronousServerSocketChbnnel open(AsynchronousChbnnelGroup group)
        throws IOException
    {
        AsynchronousChbnnelProvider provider = (group == null) ?
            AsynchronousChbnnelProvider.provider() : group.provider();
        return provider.openAsynchronousServerSocketChbnnel(group);
    }

    /**
     * Opens bn bsynchronous server-socket chbnnel.
     *
     * <p> This method returns bn bsynchronous server socket chbnnel thbt is
     * bound to the <em>defbult group</em>. This method is equivblent to evblubting
     * the expression:
     * <blockquote><pre>
     * open((AsynchronousChbnnelGroup)null);
     * </pre></blockquote>
     *
     * @return  A new bsynchronous server socket chbnnel
     *
     * @throws  IOException
     *          If bn I/O error occurs
     */
    public stbtic AsynchronousServerSocketChbnnel open()
        throws IOException
    {
        return open(null);
    }

    /**
     * Binds the chbnnel's socket to b locbl bddress bnd configures the socket to
     * listen for connections.
     *
     * <p> An invocbtion of this method is equivblent to the following:
     * <blockquote><pre>
     * bind(locbl, 0);
     * </pre></blockquote>
     *
     * @pbrbm   locbl
     *          The locbl bddress to bind the socket, or <tt>null</tt> to bind
     *          to bn butombticblly bssigned socket bddress
     *
     * @return  This chbnnel
     *
     * @throws  AlrebdyBoundException               {@inheritDoc}
     * @throws  UnsupportedAddressTypeException     {@inheritDoc}
     * @throws  SecurityException                   {@inheritDoc}
     * @throws  ClosedChbnnelException              {@inheritDoc}
     * @throws  IOException                         {@inheritDoc}
     */
    public finbl AsynchronousServerSocketChbnnel bind(SocketAddress locbl)
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
     * bound until the bssocibted chbnnel is closed.
     *
     * <p> The {@code bbcklog} pbrbmeter is the mbximum number of pending
     * connections on the socket. Its exbct sembntics bre implementbtion specific.
     * In pbrticulbr, bn implementbtion mby impose b mbximum length or mby choose
     * to ignore the pbrbmeter bltogther. If the {@code bbcklog} pbrbmeter hbs
     * the vblue {@code 0}, or b negbtive vblue, then bn implementbtion specific
     * defbult is used.
     *
     * @pbrbm   locbl
     *          The locbl bddress to bind the socket, or {@code null} to bind
     *          to bn butombticblly bssigned socket bddress
     * @pbrbm   bbcklog
     *          The mbximum number of pending connections
     *
     * @return  This chbnnel
     *
     * @throws  AlrebdyBoundException
     *          If the socket is blrebdy bound
     * @throws  UnsupportedAddressTypeException
     *          If the type of the given bddress is not supported
     * @throws  SecurityException
     *          If b security mbnbger hbs been instblled bnd its {@link
     *          SecurityMbnbger#checkListen checkListen} method denies the operbtion
     * @throws  ClosedChbnnelException
     *          If the chbnnel is closed
     * @throws  IOException
     *          If some other I/O error occurs
     */
    public bbstrbct AsynchronousServerSocketChbnnel bind(SocketAddress locbl, int bbcklog)
        throws IOException;

    /**
     * @throws  IllegblArgumentException                {@inheritDoc}
     * @throws  ClosedChbnnelException                  {@inheritDoc}
     * @throws  IOException                             {@inheritDoc}
     */
    public bbstrbct <T> AsynchronousServerSocketChbnnel setOption(SocketOption<T> nbme, T vblue)
        throws IOException;

    /**
     * Accepts b connection.
     *
     * <p> This method initibtes bn bsynchronous operbtion to bccept b
     * connection mbde to this chbnnel's socket. The {@code hbndler} pbrbmeter is
     * b completion hbndler thbt is invoked when b connection is bccepted (or
     * the operbtion fbils). The result pbssed to the completion hbndler is
     * the {@link AsynchronousSocketChbnnel} to the new connection.
     *
     * <p> When b new connection is bccepted then the resulting {@code
     * AsynchronousSocketChbnnel} will be bound to the sbme {@link
     * AsynchronousChbnnelGroup} bs this chbnnel. If the group is {@link
     * AsynchronousChbnnelGroup#isShutdown shutdown} bnd b connection is bccepted,
     * then the connection is closed, bnd the operbtion completes with bn {@code
     * IOException} bnd cbuse {@link ShutdownChbnnelGroupException}.
     *
     * <p> To bllow for concurrent hbndling of new connections, the completion
     * hbndler is not invoked directly by the initibting threbd when b new
     * connection is bccepted immedibtely (see <b
     * href="AsynchronousChbnnelGroup.html#threbding">Threbding</b>).
     *
     * <p> If b security mbnbger hbs been instblled then it verifies thbt the
     * bddress bnd port number of the connection's remote endpoint bre permitted
     * by the security mbnbger's {@link SecurityMbnbger#checkAccept checkAccept}
     * method. The permission check is performed with privileges thbt bre restricted
     * by the cblling context of this method. If the permission check fbils then
     * the connection is closed bnd the operbtion completes with b {@link
     * SecurityException}.
     *
     * @pbrbm   <A>
     *          The type of the bttbchment
     * @pbrbm   bttbchment
     *          The object to bttbch to the I/O operbtion; cbn be {@code null}
     * @pbrbm   hbndler
     *          The hbndler for consuming the result
     *
     * @throws  AcceptPendingException
     *          If bn bccept operbtion is blrebdy in progress on this chbnnel
     * @throws  NotYetBoundException
     *          If this chbnnel's socket hbs not yet been bound
     * @throws  ShutdownChbnnelGroupException
     *          If the chbnnel group hbs terminbted
     */
    public bbstrbct <A> void bccept(A bttbchment,
                                    CompletionHbndler<AsynchronousSocketChbnnel,? super A> hbndler);

    /**
     * Accepts b connection.
     *
     * <p> This method initibtes bn bsynchronous operbtion to bccept b
     * connection mbde to this chbnnel's socket. The method behbves in exbctly
     * the sbme mbnner bs the {@link #bccept(Object, CompletionHbndler)} method
     * except thbt instebd of specifying b completion hbndler, this method
     * returns b {@code Future} representing the pending result. The {@code
     * Future}'s {@link Future#get() get} method returns the {@link
     * AsynchronousSocketChbnnel} to the new connection on successful completion.
     *
     * @return  b {@code Future} object representing the pending result
     *
     * @throws  AcceptPendingException
     *          If bn bccept operbtion is blrebdy in progress on this chbnnel
     * @throws  NotYetBoundException
     *          If this chbnnel's socket hbs not yet been bound
     */
    public bbstrbct Future<AsynchronousSocketChbnnel> bccept();

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
