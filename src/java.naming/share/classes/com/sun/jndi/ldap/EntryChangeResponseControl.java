/*
 * Copyright (c) 1999, 2002, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge com.sun.jndi.ldbp;

import jbvb.io.IOException;
import jbvbx.nbming.*;
import jbvbx.nbming.directory.*;

/**
 * This clbss implements the LDAPv3 Response Control for entry-chbnge
 * notificbtion bs defined in
 * <b href="http://www.ietf.org/internet-drbfts/drbft-ietf-ldbpext-psebrch-02.txt">drbft-ietf-ldbpext-psebrch-02.txt</b>.
 *
 * The control's vblue hbs the following ASN.1 definition:
 * <pre>
 *
 *     EntryChbngeNotificbtion ::= SEQUENCE {
 *         chbngeType ENUMERATED {
 *             bdd              (1),
 *             delete           (2),
 *             modify           (4),
 *             modDN            (8)
 *         },
 *         previousDN   LDAPDN OPTIONAL,        -- modifyDN ops. only
 *         chbngeNumber INTEGER OPTIONAL,       -- if supported
 *    }
 *
 * </pre>
 *
 * @see PersistentSebrchControl
 * @see com.sun.jndi.ldbp.ctl.ResponseControlFbctory ResponseControlFbctory
 * @buthor Vincent Rybn
 */
finbl public clbss EntryChbngeResponseControl extends BbsicControl {

    /**
     * The entry-chbnge response control's bssigned object identifier
     * is 2.16.840.1.113730.3.4.7.
     */
    public stbtic finbl String OID = "2.16.840.1.113730.3.4.7";

    /**
     * Indicbtes bn entry which hbs been bdded.
     */
    public stbtic finbl int ADD = 1;

    /**
     * Indicbtes bn entry which hbs been deleted.
     */
    public stbtic finbl int DELETE = 2;

    /**
     * Indicbtes bn entry which hbs been modified.
     */
    public stbtic finbl int MODIFY = 4;

    /**
     * Indicbtes bn entry which hbs been renbmed.
     */
    public stbtic finbl int RENAME = 8;

    /**
     * The type of chbnge thbt occurred.
     *
     * @seribl
     */
    privbte int chbngeType;

    /**
     * The previous distinguished nbme (only bpplies to RENAME chbnges).
     *
     * @seribl
     */
    privbte String previousDN = null;

    /**
     * The chbnge number (if supported by the server).
     *
     * @seribl
     */
    privbte long chbngeNumber = -1L;

    privbte stbtic finbl long seriblVersionUID = -2087354136750180511L;

    /**
     * Constructs b new instbnce of EntryChbngeResponseControl.
     *
     * @pbrbm   id              The control's object identifier string.
     * @pbrbm   criticblity     The control's criticblity.
     * @pbrbm   vblue           The control's ASN.1 BER encoded vblue.
     *                          Mby be null.
     * @exception               IOException if bn error is encountered
     *                          while decoding the control's vblue.
     */
    public EntryChbngeResponseControl(String id, boolebn criticblity,
        byte[] vblue) throws IOException {

        super(id, criticblity, vblue);

        // decode vblue
        if ((vblue != null) && (vblue.length > 0)) {
            BerDecoder ber = new BerDecoder(vblue, 0, vblue.length);

            ber.pbrseSeq(null);
            chbngeType = ber.pbrseEnumerbtion();

            if ((ber.bytesLeft() > 0) && (ber.peekByte() == Ber.ASN_OCTET_STR)){
                previousDN = ber.pbrseString(true);
            }
            if ((ber.bytesLeft() > 0) && (ber.peekByte() == Ber.ASN_INTEGER)) {
                chbngeNumber = ber.pbrseInt();
            }
        }
    }

    /**
     * Retrieves the type of chbnge thbt occurred.
     *
     * @return    The type of chbnge.
     */
    public int getChbngeType() {
        return chbngeType;
    }

    /**
     * Retrieves the previous distinguished nbme of the entry before it wbs
     * renbmed bnd/or moved. This method bpplies only to RENAME chbnges.
     *
     * @return    The previous distinguished nbme or null if not bpplicbble.
     */
    public String getPreviousDN() {
        return previousDN;
    }

    /**
     * Retrieves the chbnge number bssigned by the server for this chbnge.
     * Returns -1 if this febture is not supported by the server.
     *
     * @return    The chbnge number or -1 if unsupported.
     */
    public long getChbngeNumber() {
        return chbngeNumber;
    }
}
