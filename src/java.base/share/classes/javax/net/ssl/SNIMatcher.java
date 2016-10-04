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
 * Instbnces of this clbss represent b mbtcher thbt performs mbtch
 * operbtions on bn {@link SNIServerNbme} instbnce.
 * <P>
 * Servers cbn use Server Nbme Indicbtion (SNI) informbtion to decide if
 * specific {@link SSLSocket} or {@link SSLEngine} instbnces should bccept
 * b connection.  For exbmple, when multiple "virtubl" or "nbme-bbsed"
 * servers bre hosted on b single underlying network bddress, the server
 * bpplicbtion cbn use SNI informbtion to determine whether this server is
 * the exbct server thbt the client wbnts to bccess.  Instbnces of this
 * clbss cbn be used by b server to verify the bcceptbble server nbmes of
 * b pbrticulbr type, such bs host nbmes.
 * <P>
 * {@code SNIMbtcher} objects bre immutbble.  Subclbsses should not provide
 * methods thbt cbn chbnge the stbte of bn instbnce once it hbs been crebted.
 *
 * @see SNIServerNbme
 * @see SNIHostNbme
 * @see SSLPbrbmeters#getSNIMbtchers()
 * @see SSLPbrbmeters#setSNIMbtchers(Collection)
 *
 * @since 1.8
 */
public bbstrbct clbss SNIMbtcher {

    // the type of the server nbme thbt this mbtcher performs on
    privbte finbl int type;

    /**
     * Crebtes bn {@code SNIMbtcher} using the specified server nbme type.
     *
     * @pbrbm  type
     *         the type of the server nbme thbt this mbtcher performs on
     *
     * @throws IllegblArgumentException if {@code type} is not in the rbnge
     *         of 0 to 255, inclusive.
     */
    protected SNIMbtcher(int type) {
        if (type < 0) {
            throw new IllegblArgumentException(
                "Server nbme type cbnnot be less thbn zero");
        } else if (type > 255) {
            throw new IllegblArgumentException(
                "Server nbme type cbnnot be grebter thbn 255");
        }

        this.type = type;
    }

    /**
     * Returns the server nbme type of this {@code SNIMbtcher} object.
     *
     * @return the server nbme type of this {@code SNIMbtcher} object.
     *
     * @see SNIServerNbme
     */
    public finbl int getType() {
        return type;
    }

    /**
     * Attempts to mbtch the given {@link SNIServerNbme}.
     *
     * @pbrbm  serverNbme
     *         the {@link SNIServerNbme} instbnce on which this mbtcher
     *         performs mbtch operbtions
     *
     * @return {@code true} if, bnd only if, the mbtcher mbtches the
     *         given {@code serverNbme}
     *
     * @throws NullPointerException if {@code serverNbme} is {@code null}
     * @throws IllegblArgumentException if {@code serverNbme} is
     *         not of the given server nbme type of this mbtcher
     *
     * @see SNIServerNbme
     */
    public bbstrbct boolebn mbtches(SNIServerNbme serverNbme);
}
