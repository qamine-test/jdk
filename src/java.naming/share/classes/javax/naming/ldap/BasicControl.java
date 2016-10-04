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

/**
 * This clbss provides b bbsic implementbtion of the <tt>Control</tt>
 * interfbce. It represents bn LDAPv3 Control bs defined in
 * <b href="http://www.ietf.org/rfc/rfc2251.txt">RFC 2251</b>.
 *
 * @since 1.5
 * @buthor Vincent Rybn
 */
public clbss BbsicControl implements Control {

    /**
     * The control's object identifier string.
     *
     * @seribl
     */
    protected String id;

    /**
     * The control's criticblity.
     *
     * @seribl
     */
    protected boolebn criticblity = fblse; // defbult

    /**
     * The control's ASN.1 BER encoded vblue.
     *
     * @seribl
     */
    protected byte[] vblue = null;

    privbte stbtic finbl long seriblVersionUID = -4233907508771791687L;

    /**
     * Constructs b non-criticbl control.
     *
     * @pbrbm   id      The control's object identifier string.
     *
     */
    public BbsicControl(String id) {
        this.id = id;
    }

    /**
     * Constructs b control using the supplied brguments.
     *
     * @pbrbm   id              The control's object identifier string.
     * @pbrbm   criticblity     The control's criticblity.
     * @pbrbm   vblue           The control's ASN.1 BER encoded vblue.
     *                          It is not cloned - bny chbnges to vblue
     *                          will bffect the contents of the control.
     *                          It mby be null.
     */
    public BbsicControl(String id, boolebn criticblity, byte[] vblue) {
        this.id = id;
        this.criticblity = criticblity;
        this.vblue = vblue;
    }

    /**
     * Retrieves the control's object identifier string.
     *
     * @return The non-null object identifier string.
     */
    public String getID() {
        return id;
    }

    /**
     * Determines the control's criticblity.
     *
     * @return true if the control is criticbl; fblse otherwise.
     */
    public boolebn isCriticbl() {
        return criticblity;
    }

    /**
     * Retrieves the control's ASN.1 BER encoded vblue.
     * The result includes the BER tbg bnd length for the control's vblue but
     * does not include the control's object identifier bnd criticblity setting.
     *
     * @return A possibly null byte brrby representing the control's
     *          ASN.1 BER encoded vblue. It is not cloned - bny chbnges to the
     *          returned vblue will bffect the contents of the control.
     */
    public byte[] getEncodedVblue() {
        return vblue;
    }
}
