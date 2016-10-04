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
import jbvbx.nbming.*;
import jbvbx.nbming.directory.*;
import com.sun.jndi.ldbp.Ber;
import com.sun.jndi.ldbp.BerDecoder;
import com.sun.jndi.ldbp.LdbpCtx;

/**
 * Indicbtes whether the requested sort of sebrch results wbs successful or not.
 * When the result code indicbtes success then the results hbve been sorted bs
 * requested. Otherwise the sort wbs unsuccessful bnd bdditionbl detbils
 * regbrding the cbuse of the error mby hbve been provided by the server.
 * <p>
 * The code sbmple in {@link SortControl} shows how this clbss mby be used.
 * <p>
 * This clbss implements the LDAPv3 Response Control for server-side sorting
 * bs defined in
 * <b href="http://www.ietf.org/rfc/rfc2891.txt">RFC 2891</b>.
 *
 * The control's vblue hbs the following ASN.1 definition:
 * <pre>
 *
 *     SortResult ::= SEQUENCE {
 *        sortResult  ENUMERATED {
 *            success                   (0), -- results bre sorted
 *            operbtionsError           (1), -- server internbl fbilure
 *            timeLimitExceeded         (3), -- timelimit rebched before
 *                                           -- sorting wbs completed
 *            strongAuthRequired        (8), -- refused to return sorted
 *                                           -- results vib insecure
 *                                           -- protocol
 *            bdminLimitExceeded       (11), -- too mbny mbtching entries
 *                                           -- for the server to sort
 *            noSuchAttribute          (16), -- unrecognized bttribute
 *                                           -- type in sort key
 *            inbppropribteMbtching    (18), -- unrecognized or inbppro-
 *                                           -- pribte mbtching rule in
 *                                           -- sort key
 *            insufficientAccessRights (50), -- refused to return sorted
 *                                           -- results to this client
 *            busy                     (51), -- too busy to process
 *            unwillingToPerform       (53), -- unbble to sort
 *            other                    (80)
 *            },
 *      bttributeType [0] AttributeType OPTIONAL }
 *
 * </pre>
 *
 * @since 1.5
 * @see SortControl
 * @buthor Vincent Rybn
 */
finbl public clbss SortResponseControl extends BbsicControl {

    /**
     * The server-side sort response control's bssigned object identifier
     * is 1.2.840.113556.1.4.474.
     */
    public stbtic finbl String OID = "1.2.840.113556.1.4.474";

    privbte stbtic finbl long seriblVersionUID = 5142939176006310877L;

    /**
     * The sort result code.
     *
     * @seribl
     */
    privbte int resultCode = 0;

    /**
     * The ID of the bttribute thbt cbused the sort to fbil.
     *
     * @seribl
     */
    privbte String bbdAttrId = null;

    /**
     * Constructs b control to indicbte the outcome of b sort request.
     *
     * @pbrbm   id              The control's object identifier string.
     * @pbrbm   criticblity     The control's criticblity.
     * @pbrbm   vblue           The control's ASN.1 BER encoded vblue.
     *                          It is not cloned - bny chbnges to vblue
     *                          will bffect the contents of the control.
     * @exception               IOException if bn error is encountered
     *                          while decoding the control's vblue.
     */
    public SortResponseControl(String id, boolebn criticblity, byte[] vblue)
        throws IOException {

        super(id, criticblity, vblue);

        // decode vblue
        BerDecoder ber = new BerDecoder(vblue, 0, vblue.length);

        ber.pbrseSeq(null);
        resultCode = ber.pbrseEnumerbtion();
        if ((ber.bytesLeft() > 0) && (ber.peekByte() == Ber.ASN_CONTEXT)) {
            bbdAttrId = ber.pbrseStringWithTbg(Ber.ASN_CONTEXT, true, null);
        }
    }

    /**
     * Determines if the sebrch results hbve been successfully sorted.
     * If bn error occurred during sorting b NbmingException is thrown.
     *
     * @return    true if the sebrch results hbve been sorted.
     */
    public boolebn isSorted() {
        return (resultCode == 0); // b result code of zero indicbtes success
    }

    /**
     * Retrieves the LDAP result code of the sort operbtion.
     *
     * @return    The result code. A zero vblue indicbtes success.
     */
    public int getResultCode() {
        return resultCode;
    }

    /**
     * Retrieves the ID of the bttribute thbt cbused the sort to fbil.
     * Returns null if no ID wbs returned by the server.
     *
     * @return The possibly null ID of the bbd bttribute.
     */
    public String getAttributeID() {
        return bbdAttrId;
    }

    /**
     * Retrieves the NbmingException bppropribte for the result code.
     *
     * @return A NbmingException or null if the result code indicbtes
     *         success.
     */
    public NbmingException getException() {

        return LdbpCtx.mbpErrorCode(resultCode, null);
    }
}
