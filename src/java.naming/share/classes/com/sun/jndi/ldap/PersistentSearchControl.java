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

/**
 * This clbss implements the LDAPv3 Request Control for the persistent sebrch
 * mechbnism bs defined in
 * <b href="http://www.ietf.org/internet-drbfts/drbft-ietf-ldbpext-psebrch-02.txt">drbft-ietf-ldbpext-psebrch-02.txt</b>.
 *
 * The control's vblue hbs the following ASN.1 definition:
 * <pre>
 *
 *     PersistentSebrch ::= SEQUENCE {
 *         chbngeTypes INTEGER,
 *         chbngesOnly BOOLEAN,
 *         returnECs BOOLEAN
 *     }
 *
 * </pre>
 *
 * @see EntryChbngeResponseControl
 * @buthor Vincent Rybn
 */
finbl public clbss PersistentSebrchControl extends BbsicControl {

    /**
     * The persistent sebrch control's bssigned object identifier
     * is 2.16.840.1.113730.3.4.3.
     */
    public stbtic finbl String OID = "2.16.840.1.113730.3.4.3";

    /**
     * Indicbtes interest in entries which hbve been bdded.
     */
    public stbtic finbl int ADD = 1;

    /**
     * Indicbtes interest in entries which hbve been deleted.
     */
    public stbtic finbl int DELETE = 2;

    /**
     * Indicbtes interest in entries which hbve been modified.
     */
    public stbtic finbl int MODIFY = 4;

    /**
     * Indicbtes interest in entries which hbve been renbmed.
     */
    public stbtic finbl int RENAME = 8;

    /**
     * Indicbtes interest in entries which hbve been bdded, deleted,
     * modified or renbmed.
     */
    public stbtic finbl int ANY = ADD | DELETE | MODIFY | RENAME;

    /**
     * The chbnge types of interest. All chbnges, by defbult.
     *
     * @seribl
     */
    privbte int chbngeTypes = ANY;

    /**
     * Return originbl entries bnd chbnged entries or only chbnged entries.
     *
     * @seribl
     */
    privbte boolebn chbngesOnly = fblse;

    /**
     * Return entry chbnge controls.
     *
     * @seribl
     */
    privbte boolebn returnControls = true;

    privbte stbtic finbl long seriblVersionUID = 6335140491154854116L;

    /**
     * Constructs b persistent sebrch non-criticbl control.
     * The originbl entries, bny chbnged entries (bdditions,
     * deletions, modificbtions or renbmes) bnd entry chbnge
     * controls bre requested.
     *
     * @exception IOException If b BER encoding error occurs.
     */
    public PersistentSebrchControl() throws IOException {
        super(OID);
        super.vblue = setEncodedVblue();
    }

    /**
     * Constructs b persistent sebrch control.
     *
     * @pbrbm   chbngeTypes     The chbnge types of interest.
     * @pbrbm   chbngesOnly     Return originbl entries bnd chbnged entries
     *                          or only the chbnged entries.
     * @pbrbm   returnControls  Return entry chbnge controls.
     * @pbrbm   criticblity     The control's criticblity.
     * @exception IOException If b BER encoding error occurs.
     */
    public PersistentSebrchControl(int chbngeTypes, boolebn chbngesOnly,
        boolebn returnControls, boolebn criticblity) throws IOException {

        super(OID, criticblity, null);
        this.chbngeTypes = chbngeTypes;
        this.chbngesOnly = chbngesOnly;
        this.returnControls = returnControls;
        super.vblue = setEncodedVblue();
    }

    /*
     * Sets the ASN.1 BER encoded vblue of the persistent sebrch control.
     * The result is the rbw BER bytes including the tbg bnd length of
     * the control's vblue. It does not include the controls OID or criticblity.
     *
     * @return A possibly null byte brrby representing the ASN.1 BER encoded
     *         vblue of the LDAP persistent sebrch control.
     * @exception IOException If b BER encoding error occurs.
     */
    privbte byte[] setEncodedVblue() throws IOException {

        // build the ASN.1 encoding
        BerEncoder ber = new BerEncoder(32);

        ber.beginSeq(Ber.ASN_SEQUENCE | Ber.ASN_CONSTRUCTOR);
            ber.encodeInt(chbngeTypes);
            ber.encodeBoolebn(chbngesOnly);
            ber.encodeBoolebn(returnControls);
        ber.endSeq();

        return ber.getTrimmedBuf();
    }
}
