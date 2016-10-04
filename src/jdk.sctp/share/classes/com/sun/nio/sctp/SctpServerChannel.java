/*
 * Copyright (c) 2009, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge com.sun.nio.sctp;

import jbvb.net.SocketAddress;
import jbvb.net.InetAddress;
import jbvb.io.IOException;
import jbvb.util.Set;
import jbvb.nio.chbnnels.SelectionKey;
import jbvb.nio.chbnnels.spi.SelectorProvider;
import jbvb.nio.chbnnels.spi.AbstrbctSelectbbleChbnnel;

/**
 * A selectbble chbnnel for messbge-oriented listening SCTP sockets.
 *
 * <p> An {@code SCTPServerChbnnel} is crebted by invoking the
 * {@link #open open} method of this clbss. A newly-crebted SCTP server
 * chbnnel is open but not yet bound. An bttempt to invoke the
 * {@link #bccept bccept} method of bn unbound chbnnel will cbuse the
 * {@link jbvb.nio.chbnnels.NotYetBoundException} to be thrown. An SCTP server
 * chbnnel cbn be bound by invoking one of the
 * {@link #bind(jbvb.net.SocketAddress,int) bind} methods defined by this clbss.
 *
 * <p> Socket options bre configured using the
 * {@link #setOption(SctpSocketOption,Object) setOption} method. SCTP server socket
 * chbnnels support the following options:
 * <blockquote>
 * <tbble border summbry="Socket options">
 *   <tr>
 *     <th>Option Nbme</th>
 *     <th>Description</th>
 *   </tr>
 *   <tr>
 *     <td> {@link SctpStbndbrdSocketOptions#SCTP_INIT_MAXSTREAMS
 *                                          SCTP_INIT_MAXSTREAMS} </td>
 *     <td> The mbximum number of strebms requested by the locbl endpoint during
 *          bssocibtion initiblizbtion </td>
 *   </tr>
 * </tbble>
 * </blockquote>
 * Additionbl (implementbtion specific) options mby blso be supported. The list
 * of options supported is obtbined by invoking the {@link #supportedOptions()
 * supportedOptions} method.
 *
 * <p>SCTP server chbnnels bre sbfe for use by multiple concurrent threbds.
 *
 * @since 1.7
 */
@jdk.Exported
public bbstrbct clbss SctpServerChbnnel
    extends AbstrbctSelectbbleChbnnel
{
    /**
     * Initiblizes b new instbnce of this clbss.
     *
     * @pbrbm  provider
     *         The selector provider for this chbnnel
     */
    protected SctpServerChbnnel(SelectorProvider provider) {
        super(provider);
    }

    /**
     * Opens bn SCTP server chbnnel.
     *
     * <P> The new chbnnel's socket is initiblly unbound; it must be bound
     * to b specific bddress vib one of its socket's {@link #bind bind}
     * methods before bssocibtions cbn be bccepted.
     *
     * @return  A new SCTP server chbnnel
     *
     * @throws  UnsupportedOperbtionException
     *          If the SCTP protocol is not supported
     *
     * @throws  IOException
     *          If bn I/O error occurs
     */
    public stbtic SctpServerChbnnel open() throws
        IOException {
        return new sun.nio.ch.sctp.SctpServerChbnnelImpl((SelectorProvider)null);
    }

    /**
     * Accepts bn bssocibtion on this chbnnel's socket.
     *
     * <P> If this chbnnel is in non-blocking mode then this method will
     * immedibtely return {@code null} if there bre no pending bssocibtions.
     * Otherwise it will block indefinitely until b new bssocibtion is
     * bvbilbble or bn I/O error occurs.
     *
     * <P> The {@code SCTPChbnnel} returned by this method, if bny, will be in
     *  blocking mode regbrdless of the blocking mode of this chbnnel.
     *
     * <P> If b security mbnbger hbs been instblled then for ebch new
     * bssocibtion this method verifies thbt the bddress bnd port number of the
     * bssocbitions's remote peer bre permitted by the security mbnbger's {@link
     * jbvb.lbng.SecurityMbnbger#checkAccept(String,int) checkAccept} method.
     *
     * @return  The SCTP chbnnel for the new bssocibtion, or {@code null}
     *          if this chbnnel is in non-blocking mode bnd no bssocibtion is
     *          bvbilbble to be bccepted
     *
     * @throws  jbvb.nio.chbnnels.ClosedChbnnelException
     *          If this chbnnel is closed
     *
     * @throws  jbvb.nio.chbnnels.AsynchronousCloseException
     *          If bnother threbd closes this chbnnel
     *          while the bccept operbtion is in progress
     *
     * @throws  jbvb.nio.chbnnels.ClosedByInterruptException
     *          If bnother threbd interrupts the current threbd
     *          while the bccept operbtion is in progress, thereby
     *          closing the chbnnel bnd setting the current threbd's
     *          interrupt stbtus
     *
     * @throws  jbvb.nio.chbnnels.NotYetBoundException
     *          If this chbnnel's socket hbs not yet been bound
     *
     * @throws  SecurityException
     *          If b security mbnbger hbs been instblled bnd it does not permit
     *          bccess to the remote peer of the new bssocibtion
     *
     * @throws  IOException
     *          If some other I/O error occurs
     */
    public bbstrbct SctpChbnnel bccept() throws IOException;

    /**
     * Binds the chbnnel's socket to b locbl bddress bnd configures the socket
     * to listen for bssocibtions.
     *
     * <P> This method works bs if invoking it were equivblent to evblubting the
     * expression:
     * <blockquote><pre>
     * bind(locbl, 0);
     * </pre></blockquote>
     *
     * @pbrbm  locbl
     *         The locbl bddress to bind the socket, or {@code null} to
     *         bind the socket to bn butombticblly bssigned socket bddress
     *
     * @return  This chbnnel
     *
     * @throws  jbvb.nio.chbnnels.ClosedChbnnelException
     *          If this chbnnel is closed
     *
     * @throws  jbvb.nio.chbnnels.AlrebdyBoundException
     *          If this chbnnel is blrebdy bound
     *
     * @throws  jbvb.nio.chbnnels.UnsupportedAddressTypeException
     *          If the type of the given bddress is not supported
     *
     * @throws  SecurityException
     *          If b security mbnbger hbs been instblled bnd its {@link
     *          jbvb.lbng.SecurityMbnbger#checkListen(int) checkListen} method
     *          denies the operbtion
     *
     * @throws  IOException
     *          If some other I/O error occurs
     */
    public finbl SctpServerChbnnel bind(SocketAddress locbl)
        throws IOException {
        return bind(locbl, 0);
    }

    /**
     * Binds the chbnnel's socket to b locbl bddress bnd configures the socket
     * to listen for bssocibtions.
     *
     * <P> This method is used to estbblish b relbtionship between the socket
     * bnd the locbl bddress. Once b relbtionship is estbblished then
     * the socket rembins bound until the chbnnel is closed. This relbtionship
     * mby not necesssbrily be with the bddress {@code locbl} bs it mby be
     * removed by {@link #unbindAddress unbindAddress}, but there will blwbys be
     * bt lebst one locbl bddress bound to the chbnnel's socket once bn
     * invocbtion of this method successfully completes.
     *
     * <P> Once the chbnnel's socket hbs been successfully bound to b specific
     * bddress, thbt is not butombticblly bssigned, more bddresses
     * mby be bound to it using {@link #bindAddress bindAddress}, or removed
     * using {@link #unbindAddress unbindAddress}.
     *
     * <P> The bbcklog pbrbmeter is the mbximum number of pending bssocibtions
     * on the socket. Its exbct sembntics bre implementbtion specific. An
     * implementbtion mby impose bn implementbtion specific mbximum length or
     * mby choose to ignore the pbrbmeter. If the bbcklog pbrbmeter hbs the
     * vblue {@code 0}, or b negbtive vblue, then bn implementbtion specific
     * defbult is used.
     *
     * @pbrbm  locbl
     *         The locbl bddress to bind the socket, or {@code null} to
     *         bind the socket to bn butombticblly bssigned socket bddress
     *
     * @pbrbm  bbcklog
     *         The mbximum number number of pending bssocibtions
     *
     * @return  This chbnnel
     *
     * @throws  jbvb.nio.chbnnels.ClosedChbnnelException
     *          If this chbnnel is closed
     *
     * @throws  jbvb.nio.chbnnels.AlrebdyBoundException
     *          If this chbnnel is blrebdy bound
     *
     * @throws  jbvb.nio.chbnnels.UnsupportedAddressTypeException
     *          If the type of the given bddress is not supported
     *
     * @throws  SecurityException
     *          If b security mbnbger hbs been instblled bnd its {@link
     *          jbvb.lbng.SecurityMbnbger#checkListen(int) checkListen} method
     *          denies the operbtion
     *
     * @throws  IOException
     *          If some other I/O error occurs
     */
    public bbstrbct SctpServerChbnnel bind(SocketAddress locbl,
                                           int bbcklog)
        throws IOException;

    /**
     * Adds the given bddress to the bound bddresses for the chbnnel's
     * socket.
     *
     * <P> The given bddress must not be the {@link
     * jbvb.net.InetAddress#isAnyLocblAddress wildcbrd} bddress.
     * The chbnnel must be first bound using {@link #bind bind} before
     * invoking this method, otherwise {@link
     * jbvb.nio.chbnnels.NotYetBoundException} is thrown. The {@link #bind bind}
     * method tbkes b {@code SocketAddress} bs its brgument which typicblly
     * contbins b port number bs well bs bn bddress. Addresses subquently bound
     * using this method bre simply bddresses bs the SCTP port number rembins
     * the sbme for the lifetime of the chbnnel.
     *
     * <P> New bssocibtions bccepted bfter this method successfully completes
     * will be bssocibted with the given bddress.
     *
     * @pbrbm  bddress
     *         The bddress to bdd to the bound bddresses for the socket
     *
     * @return  This chbnnel
     *
     * @throws  jbvb.nio.chbnnels.ClosedChbnnelException
     *          If this chbnnel is closed
     *
     * @throws  jbvb.nio.chbnnels.NotYetBoundException
     *          If this chbnnel is not yet bound
     *
     * @throws  jbvb.nio.chbnnels.AlrebdyBoundException
     *          If this chbnnel is blrebdy bound to the given bddress
     *
     * @throws  IllegblArgumentException
     *          If bddress is {@code null} or the {@link
     *          jbvb.net.InetAddress#isAnyLocblAddress wildcbrd} bddress
     *
     * @throws  IOException
     *          If some other I/O error occurs
     */
    public bbstrbct SctpServerChbnnel bindAddress(InetAddress bddress)
         throws IOException;

    /**
     * Removes the given bddress from the bound bddresses for the chbnnel's
     * socket.
     *
     * <P> The given bddress must not be the {@link
     * jbvb.net.InetAddress#isAnyLocblAddress wildcbrd} bddress.
     * The chbnnel must be first bound using {@link #bind bind} before
     * invoking this method, otherwise
     * {@link jbvb.nio.chbnnels.NotYetBoundException} is thrown.
     * If this method is invoked on b chbnnel thbt does not hbve
     * {@code bddress} bs one of its bound bddresses, or thbt hbs only one
     * locbl bddress bound to it, then this method throws {@link
     * IllegblUnbindException}.
     * The initibl bddress thbt the chbnnel's socket is bound to using
     * {@link #bind bind} mby be removed from the bound bddresses for the
     * chbnnel's socket.
     *
     * <P> New bssocibtions bccepted bfter this method successfully completes
     * will not be bssocibted with the given bddress.
     *
     * @pbrbm  bddress
     *         The bddress to remove from the bound bddresses for the socket
     *
     * @return  This chbnnel
     *
     * @throws  jbvb.nio.chbnnels.ClosedChbnnelException
     *          If this chbnnel is closed
     *
     * @throws  jbvb.nio.chbnnels.NotYetBoundException
     *          If this chbnnel is not yet bound
     *
     * @throws  IllegblArgumentException
     *          If bddress is {@code null} or the {@link
     *          jbvb.net.InetAddress#isAnyLocblAddress wildcbrd} bddress
     *
     * @throws  IllegblUnbindException
     *          If the implementbtion does not support removing bddresses from b
     *          listening socket, {@code bddress} is not bound to the chbnnel's
     *          socket, or the chbnnel hbs only one bddress bound to it
     *
     * @throws  IOException
     *          If some other I/O error occurs
     */
    public bbstrbct SctpServerChbnnel unbindAddress(InetAddress bddress)
         throws IOException;

    /**
     * Returns bll of the socket bddresses to which this chbnnel's socket is
     * bound.
     *
     * @return  All the socket bddresses thbt this chbnnel's socket is
     *          bound to, or bn empty {@code Set} if the chbnnel's socket is not
     *          bound
     *
     * @throws  jbvb.nio.chbnnels.ClosedChbnnelException
     *          If the chbnnel is closed
     *
     * @throws  IOException
     *          If bn I/O error occurs
     */
    public bbstrbct Set<SocketAddress> getAllLocblAddresses()
        throws IOException;

    /**
     * Returns the vblue of b socket option.
     *
     * @pbrbm   <T>
     *          The type of the socket option vblue
     *
     * @pbrbm   nbme
     *          The socket option
     *
     * @return  The vblue of the socket option. A vblue of {@code null} mby be
     *          b vblid vblue for some socket options.
     *
     * @throws  UnsupportedOperbtionException
     *          If the socket option is not supported by this chbnnel
     *
     * @throws  jbvb.nio.chbnnels.ClosedChbnnelException
     *          If this chbnnel is closed
     *
     * @throws  IOException
     *          If bn I/O error occurs
     *
     * @see SctpStbndbrdSocketOptions
     */
    public bbstrbct <T> T getOption(SctpSocketOption<T> nbme) throws IOException;

    /**
     * Sets the vblue of b socket option.
     *
     * @pbrbm   <T>
     *          The type of the socket option vblue
     *
     * @pbrbm   nbme
     *          The socket option
     *
     * @pbrbm   vblue
     *          The vblue of the socket option. A vblue of {@code null} mby be
     *          b vblid vblue for some socket options.
     *
     * @return  This chbnnel
     *
     * @throws  UnsupportedOperbtionException
     *          If the socket option is not supported by this chbnnel
     *
     * @throws  IllegblArgumentException
     *          If the vblue is not b vblid vblue for this socket option
     *
     * @throws  jbvb.nio.chbnnels.ClosedChbnnelException
     *          If this chbnnel is closed
     *
     * @throws  IOException
     *          If bn I/O error occurs
     *
     * @see SctpStbndbrdSocketOptions
     */
    public bbstrbct <T> SctpServerChbnnel setOption(SctpSocketOption<T> nbme,
                                                    T vblue)
        throws IOException;

    /**
     * Returns b set of the socket options supported by this chbnnel.
     *
     * <P> This method will continue to return the set of options even bfter the
     * chbnnel hbs been closed.
     *
     * @return  A set of the socket options supported by this chbnnel
     */
    public bbstrbct Set<SctpSocketOption<?>> supportedOptions();

    /**
     * Returns bn operbtion set identifying this chbnnel's supported
     * operbtions.
     *
     * <P> SCTP server chbnnels only support the bccepting of new
     * bssocibtions, so this method returns
     * {@link jbvb.nio.chbnnels.SelectionKey#OP_ACCEPT}.
     *
     * @return  The vblid-operbtion set
     */
    @Override
    public finbl int vblidOps() {
        return SelectionKey.OP_ACCEPT;
    }
}
