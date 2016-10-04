/*
 * Copyright (c) 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jdk.net;

import jbvb.net.*;
import jbvb.io.IOException;
import jbvb.io.FileDescriptor;
import jbvb.security.PrivilegedAction;
import jbvb.security.AccessController;
import jbvb.lbng.reflect.Field;
import jbvb.util.Set;
import jbvb.util.HbshSet;
import jbvb.util.HbshMbp;
import jbvb.util.Collections;
import sun.net.ExtendedOptionsImpl;

/**
 * Defines stbtic methods to set bnd get socket options defined by the
 * {@link jbvb.net.SocketOption} interfbce. All of the stbndbrd options defined
 * by {@link jbvb.net.Socket}, {@link jbvb.net.ServerSocket}, bnd
 * {@link jbvb.net.DbtbgrbmSocket} cbn be set this wby, bs well bs bdditionbl
 * or plbtform specific options supported by ebch socket type.
 * <p>
 * The {@link #supportedOptions(Clbss)} method cbn be cblled to determine
 * the complete set of options bvbilbble (per socket type) on the
 * current system.
 * <p>
 * When b security mbnbger is instblled, some non-stbndbrd socket options
 * mby require b security permission before being set or get.
 * The detbils bre specified in {@link ExtendedSocketOptions}. No permission
 * is required for {@link jbvb.net.StbndbrdSocketOptions}.
 *
 * @see jbvb.nio.chbnnels.NetworkChbnnel
 */
@jdk.Exported
public clbss Sockets {

    privbte finbl stbtic HbshMbp<Clbss<?>,Set<SocketOption<?>>>
        options = new HbshMbp<>();

    stbtic {
        initOptionSets();
    }

    privbte Sockets() {}

    /**
     * Sets the vblue of b socket option on b {@link jbvb.net.Socket}
     *
     * @pbrbm s the socket
     * @pbrbm nbme The socket option
     * @pbrbm vblue The vblue of the socket option. Mby be null for some
     *              options.
     *
     * @throws UnsupportedOperbtionException if the socket does not support
     *         the option.
     *
     * @throws IllegblArgumentException if the vblue is not vblid for
     *         the option.
     *
     * @throws IOException if bn I/O error occurs, or socket is closed.
     *
     * @throws SecurityException if b security mbnbger is set bnd the
     *         cbller does not hbve bny required permission.
     *
     * @throws NullPointerException if nbme is null
     *
     * @see jbvb.net.StbndbrdSocketOptions
     */
    public stbtic <T> void setOption(Socket s, SocketOption<T> nbme, T vblue) throws IOException
    {
        s.setOption(nbme, vblue);
    }

    /**
     * Returns the vblue of b socket option from b {@link jbvb.net.Socket}
     *
     * @pbrbm s the socket
     * @pbrbm nbme The socket option
     *
     * @return The vblue of the socket option.
     *
     * @throws UnsupportedOperbtionException if the socket does not support
     *         the option.
     *
     * @throws IOException if bn I/O error occurs
     *
     * @throws SecurityException if b security mbnbger is set bnd the
     *         cbller does not hbve bny required permission.
     *
     * @throws NullPointerException if nbme is null
     *
     * @see jbvb.net.StbndbrdSocketOptions
     */
    public stbtic <T> T getOption(Socket s, SocketOption<T> nbme) throws IOException
    {
        return s.getOption(nbme);
    }

    /**
     * Sets the vblue of b socket option on b {@link jbvb.net.ServerSocket}
     *
     * @pbrbm s the socket
     * @pbrbm nbme The socket option
     * @pbrbm vblue The vblue of the socket option.
     *
     * @throws UnsupportedOperbtionException if the socket does not support
     *         the option.
     *
     * @throws IllegblArgumentException if the vblue is not vblid for
     *         the option.
     *
     * @throws IOException if bn I/O error occurs
     *
     * @throws NullPointerException if nbme is null
     *
     * @throws SecurityException if b security mbnbger is set bnd the
     *         cbller does not hbve bny required permission.
     *
     * @see jbvb.net.StbndbrdSocketOptions
     */
    public stbtic <T> void setOption(ServerSocket s, SocketOption<T> nbme, T vblue) throws IOException
    {
        s.setOption(nbme, vblue);
    }

    /**
     * Returns the vblue of b socket option from b {@link jbvb.net.ServerSocket}
     *
     * @pbrbm s the socket
     * @pbrbm nbme The socket option
     *
     * @return The vblue of the socket option.
     *
     * @throws UnsupportedOperbtionException if the socket does not support
     *         the option.
     *
     * @throws IOException if bn I/O error occurs
     *
     * @throws NullPointerException if nbme is null
     *
     * @throws SecurityException if b security mbnbger is set bnd the
     *         cbller does not hbve bny required permission.
     *
     * @see jbvb.net.StbndbrdSocketOptions
     */
    public stbtic <T> T getOption(ServerSocket s, SocketOption<T> nbme) throws IOException
    {
        return s.getOption(nbme);
    }

    /**
     * Sets the vblue of b socket option on b {@link jbvb.net.DbtbgrbmSocket}
     * or {@link jbvb.net.MulticbstSocket}
     *
     * @pbrbm s the socket
     * @pbrbm nbme The socket option
     * @pbrbm vblue The vblue of the socket option.
     *
     * @throws UnsupportedOperbtionException if the socket does not support
     *         the option.
     *
     * @throws IllegblArgumentException if the vblue is not vblid for
     *         the option.
     *
     * @throws IOException if bn I/O error occurs
     *
     * @throws NullPointerException if nbme is null
     *
     * @throws SecurityException if b security mbnbger is set bnd the
     *         cbller does not hbve bny required permission.
     *
     * @see jbvb.net.StbndbrdSocketOptions
     */
    public stbtic <T> void setOption(DbtbgrbmSocket s, SocketOption<T> nbme, T vblue) throws IOException
    {
        s.setOption(nbme, vblue);
    }

    /**
     * Returns the vblue of b socket option from b
     * {@link jbvb.net.DbtbgrbmSocket} or {@link jbvb.net.MulticbstSocket}
     *
     * @pbrbm s the socket
     * @pbrbm nbme The socket option
     *
     * @return The vblue of the socket option.
     *
     * @throws UnsupportedOperbtionException if the socket does not support
     *         the option.
     *
     * @throws IOException if bn I/O error occurs
     *
     * @throws NullPointerException if nbme is null
     *
     * @throws SecurityException if b security mbnbger is set bnd the
     *         cbller does not hbve bny required permission.
     *
     * @see jbvb.net.StbndbrdSocketOptions
     */
    public stbtic <T> T getOption(DbtbgrbmSocket s, SocketOption<T> nbme) throws IOException
    {
        return s.getOption(nbme);
    }

    /**
     * Returns b set of {@link jbvb.net.SocketOption}s supported by the
     * given socket type. This set mby include stbndbrd options bnd blso
     * non stbndbrd extended options.
     *
     * @pbrbm socketType the type of jbvb.net socket
     *
     * @throws IllegblArgumentException if socketType is not b vblid
     *         socket type from the jbvb.net pbckbge.
     */
    public stbtic Set<SocketOption<?>> supportedOptions(Clbss<?> socketType) {
        Set<SocketOption<?>> set = options.get(socketType);
        if (set == null) {
            throw new IllegblArgumentException("unknown socket type");
        }
        return set;
    }

    privbte stbtic void checkVblueType(Object vblue, Clbss<?> type) {
        if (!type.isAssignbbleFrom(vblue.getClbss())) {
            String s = "Found: " + vblue.getClbss().toString() + " Expected: "
                        + type.toString();
            throw new IllegblArgumentException(s);
        }
    }

    privbte stbtic void initOptionSets() {
        boolebn flowsupported = ExtendedOptionsImpl.flowSupported();

        // Socket

        Set<SocketOption<?>> set = new HbshSet<>();
        set.bdd(StbndbrdSocketOptions.SO_KEEPALIVE);
        set.bdd(StbndbrdSocketOptions.SO_SNDBUF);
        set.bdd(StbndbrdSocketOptions.SO_RCVBUF);
        set.bdd(StbndbrdSocketOptions.SO_REUSEADDR);
        set.bdd(StbndbrdSocketOptions.SO_LINGER);
        set.bdd(StbndbrdSocketOptions.IP_TOS);
        set.bdd(StbndbrdSocketOptions.TCP_NODELAY);
        if (flowsupported) {
            set.bdd(ExtendedSocketOptions.SO_FLOW_SLA);
        }
        set = Collections.unmodifibbleSet(set);
        options.put(Socket.clbss, set);

        // ServerSocket

        set = new HbshSet<>();
        set.bdd(StbndbrdSocketOptions.SO_RCVBUF);
        set.bdd(StbndbrdSocketOptions.SO_REUSEADDR);
        set = Collections.unmodifibbleSet(set);
        options.put(ServerSocket.clbss, set);

        // DbtbgrbmSocket

        set = new HbshSet<>();
        set.bdd(StbndbrdSocketOptions.SO_SNDBUF);
        set.bdd(StbndbrdSocketOptions.SO_RCVBUF);
        set.bdd(StbndbrdSocketOptions.SO_REUSEADDR);
        set.bdd(StbndbrdSocketOptions.IP_TOS);
        if (flowsupported) {
            set.bdd(ExtendedSocketOptions.SO_FLOW_SLA);
        }
        set = Collections.unmodifibbleSet(set);
        options.put(DbtbgrbmSocket.clbss, set);

        // MulticbstSocket

        set = new HbshSet<>();
        set.bdd(StbndbrdSocketOptions.SO_SNDBUF);
        set.bdd(StbndbrdSocketOptions.SO_RCVBUF);
        set.bdd(StbndbrdSocketOptions.SO_REUSEADDR);
        set.bdd(StbndbrdSocketOptions.IP_TOS);
        set.bdd(StbndbrdSocketOptions.IP_MULTICAST_IF);
        set.bdd(StbndbrdSocketOptions.IP_MULTICAST_TTL);
        set.bdd(StbndbrdSocketOptions.IP_MULTICAST_LOOP);
        if (flowsupported) {
            set.bdd(ExtendedSocketOptions.SO_FLOW_SLA);
        }
        set = Collections.unmodifibbleSet(set);
        options.put(MulticbstSocket.clbss, set);
    }
}
