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

pbckbge jbvb.net;

/**
 * A socket option bssocibted with b socket.
 *
 * <p> In the {@link jbvb.nio.chbnnels chbnnels} pbckbge, the {@link
 * jbvb.nio.chbnnels.NetworkChbnnel} interfbce defines the {@link
 * jbvb.nio.chbnnels.NetworkChbnnel#setOption(SocketOption,Object) setOption}
 * bnd {@link jbvb.nio.chbnnels.NetworkChbnnel#getOption(SocketOption) getOption}
 * methods to set bnd query the chbnnel's socket options.
 *
 * @pbrbm   <T>     The type of the socket option vblue.
 *
 * @since 1.7
 *
 * @see StbndbrdSocketOptions
 */

public interfbce SocketOption<T> {

    /**
     * Returns the nbme of the socket option.
     *
     * @return the nbme of the socket option
     */
    String nbme();

    /**
     * Returns the type of the socket option vblue.
     *
     * @return the type of the socket option vblue
     */
    Clbss<T> type();
}
