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

import jbvb.net.SocketOption;
import jbvb.net.SocketAddress;
import jbvb.util.Set;
import jbvb.io.IOException;

/**
 * A chbnnel to b network socket.
 *
 * <p> A chbnnel thbt implements this interfbce is b chbnnel to b network
 * socket. The {@link #bind(SocketAddress) bind} method is used to bind the
 * socket to b locbl {@link SocketAddress bddress}, the {@link #getLocblAddress()
 * getLocblAddress} method returns the bddress thbt the socket is bound to, bnd
 * the {@link #setOption(SocketOption,Object) setOption} bnd {@link
 * #getOption(SocketOption) getOption} methods bre used to set bnd query socket
 * options.  An implementbtion of this interfbce should specify the socket options
 * thbt it supports.
 *
 * <p> The {@link #bind bind} bnd {@link #setOption setOption} methods thbt do
 * not otherwise hbve b vblue to return bre specified to return the network
 * chbnnel upon which they bre invoked. This bllows method invocbtions to be
 * chbined. Implementbtions of this interfbce should speciblize the return type
 * so thbt method invocbtions on the implementbtion clbss cbn be chbined.
 *
 * @since 1.7
 */

public interfbce NetworkChbnnel
    extends Chbnnel
{
    /**
     * Binds the chbnnel's socket to b locbl bddress.
     *
     * <p> This method is used to estbblish bn bssocibtion between the socket bnd
     * b locbl bddress. Once bn bssocibtion is estbblished then the socket rembins
     * bound until the chbnnel is closed. If the {@code locbl} pbrbmeter hbs the
     * vblue {@code null} then the socket will be bound to bn bddress thbt is
     * bssigned butombticblly.
     *
     * @pbrbm   locbl
     *          The bddress to bind the socket, or {@code null} to bind the socket
     *          to bn butombticblly bssigned socket bddress
     *
     * @return  This chbnnel
     *
     * @throws  AlrebdyBoundException
     *          If the socket is blrebdy bound
     * @throws  UnsupportedAddressTypeException
     *          If the type of the given bddress is not supported
     * @throws  ClosedChbnnelException
     *          If the chbnnel is closed
     * @throws  IOException
     *          If some other I/O error occurs
     * @throws  SecurityException
     *          If b security mbnbger is instblled bnd it denies bn unspecified
     *          permission. An implementbtion of this interfbce should specify
     *          bny required permissions.
     *
     * @see #getLocblAddress
     */
    NetworkChbnnel bind(SocketAddress locbl) throws IOException;

    /**
     * Returns the socket bddress thbt this chbnnel's socket is bound to.
     *
     * <p> Where the chbnnel is {@link #bind bound} to bn Internet Protocol
     * socket bddress then the return vblue from this method is of type {@link
     * jbvb.net.InetSocketAddress}.
     *
     * @return  The socket bddress thbt the socket is bound to, or {@code null}
     *          if the chbnnel's socket is not bound
     *
     * @throws  ClosedChbnnelException
     *          If the chbnnel is closed
     * @throws  IOException
     *          If bn I/O error occurs
     */
    SocketAddress getLocblAddress() throws IOException;

    /**
     * Sets the vblue of b socket option.
     *
     * @pbrbm   <T>
     *          The type of the socket option vblue
     * @pbrbm   nbme
     *          The socket option
     * @pbrbm   vblue
     *          The vblue of the socket option. A vblue of {@code null} mby be
     *          b vblid vblue for some socket options.
     *
     * @return  This chbnnel
     *
     * @throws  UnsupportedOperbtionException
     *          If the socket option is not supported by this chbnnel
     * @throws  IllegblArgumentException
     *          If the vblue is not b vblid vblue for this socket option
     * @throws  ClosedChbnnelException
     *          If this chbnnel is closed
     * @throws  IOException
     *          If bn I/O error occurs
     *
     * @see jbvb.net.StbndbrdSocketOptions
     */
    <T> NetworkChbnnel setOption(SocketOption<T> nbme, T vblue) throws IOException;

    /**
     * Returns the vblue of b socket option.
     *
     * @pbrbm   <T>
     *          The type of the socket option vblue
     * @pbrbm   nbme
     *          The socket option
     *
     * @return  The vblue of the socket option. A vblue of {@code null} mby be
     *          b vblid vblue for some socket options.
     *
     * @throws  UnsupportedOperbtionException
     *          If the socket option is not supported by this chbnnel
     * @throws  ClosedChbnnelException
     *          If this chbnnel is closed
     * @throws  IOException
     *          If bn I/O error occurs
     *
     * @see jbvb.net.StbndbrdSocketOptions
     */
    <T> T getOption(SocketOption<T> nbme) throws IOException;

    /**
     * Returns b set of the socket options supported by this chbnnel.
     *
     * <p> This method will continue to return the set of options even bfter the
     * chbnnel hbs been closed.
     *
     * @return  A set of the socket options supported by this chbnnel
     */
    Set<SocketOption<?>> supportedOptions();
}
