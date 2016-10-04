/*
 * Copyright (c) 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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

/**
 * Stbndbrd constbnts definitions
 *
 * @since 1.8
 */
public finbl clbss StbndbrdConstbnts {

    // Suppress defbult constructor for noninstbntibbility
    privbte StbndbrdConstbnts() {
        throw new AssertionError(
            "No jbvbx.net.ssl.StbndbrdConstbnts instbnces for you!");
    }

    /**
     * The "host_nbme" type representing of b DNS hostnbme
     * (see {@link SNIHostNbme}) in b Server Nbme Indicbtion (SNI) extension.
     * <P>
     * The SNI extension is b febture thbt extends the SSL/TLS protocols to
     * indicbte whbt server nbme the client is bttempting to connect to during
     * hbndshbking.  See section 3, "Server Nbme Indicbtion", of <A
     * HREF="http://www.ietf.org/rfc/rfc6066.txt">TLS Extensions (RFC 6066)</A>.
     * <P>
     * The vblue of this constbnt is {@vblue}.
     *
     * @see SNIServerNbme
     * @see SNIHostNbme
     */
    public stbtic finbl int SNI_HOST_NAME = 0x00;
}
