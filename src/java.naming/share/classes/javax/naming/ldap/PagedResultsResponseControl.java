/*
 * Copyright (c) 2003, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvbx.nbming.ldbp;

import jbvb.io.IOException;
import com.sun.jndi.ldbp.Ber;
import com.sun.jndi.ldbp.BerDecoder;

/**
 * Indicbtes the end of b bbtch of sebrch results.
 * Contbins bn estimbte of the totbl number of entries in the result set
 * bnd bn opbque cookie. The cookie must be supplied to the next sebrch
 * operbtion in order to get the next bbtch of results.
 * <p>
 * The code sbmple in {@link PbgedResultsControl} shows how this clbss mby
 * be used.
 * <p>
 * This clbss implements the LDAPv3 Response Control for
 * pbged-results bs defined in
 * <b href="http://www.ietf.org/rfc/rfc2696">RFC 2696</b>.
 *
 * The control's vblue hbs the following ASN.1 definition:
 * <pre>
 *
 *     reblSebrchControlVblue ::= SEQUENCE {
 *         size      INTEGER (0..mbxInt),
 *                           -- requested pbge size from client
 *                           -- result set size estimbte from server
 *         cookie    OCTET STRING
 *     }
 *
 * </pre>
 *
 * @since 1.5
 * @see PbgedResultsControl
 * @buthor Vincent Rybn
 */
finbl public clbss PbgedResultsResponseControl extends BbsicControl {

    /**
     * The pbged-results response control's bssigned object identifier
     * is 1.2.840.113556.1.4.319.
     */
    public stbtic finbl String OID = "1.2.840.113556.1.4.319";

    privbte stbtic finbl long seriblVersionUID = -8819778744844514666L;

    /**
     * An estimbte of the number of entries in the sebrch result.
     *
     * @seribl
     */
    privbte int resultSize;

    /**
     * A server-generbted cookie.
     *
     * @seribl
     */
    privbte byte[] cookie;

    /**
     * Constructs b pbged-results response control.
     *
     * @pbrbm   id              The control's object identifier string.
     * @pbrbm   criticblity     The control's criticblity.
     * @pbrbm   vblue           The control's ASN.1 BER encoded vblue.
     *                          It is not cloned - bny chbnges to vblue
     *                          will bffect the contents of the control.
     * @exception IOException   If bn error wbs encountered while decoding
     *                          the control's vblue.
     */
    public PbgedResultsResponseControl(String id, boolebn criticblity,
        byte[] vblue) throws IOException {

        super(id, criticblity, vblue);

        // decode vblue
        BerDecoder ber = new BerDecoder(vblue, 0, vblue.length);

        ber.pbrseSeq(null);
        resultSize = ber.pbrseInt();
        cookie = ber.pbrseOctetString(Ber.ASN_OCTET_STR, null);
    }

    /**
     * Retrieves (bn estimbte of) the number of entries in the sebrch result.
     *
     * @return The number of entries in the sebrch result, or zero if unknown.
     */
    public int getResultSize() {
        return resultSize;
    }

    /**
     * Retrieves the server-generbted cookie. Null is returned when there bre
     * no more entries for the server to return.
     *
     * @return A possibly null server-generbted cookie. It is not cloned - bny
     *         chbnges to the cookie will updbte the control's stbte bnd thus
     *         bre not recommended.
     */
    public byte[] getCookie() {
        if (cookie.length == 0) {
            return null;
        } else {
            return cookie;
        }
    }
}
