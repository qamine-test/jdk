/*
 * Copyright (c) 2000, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge jbvbx.swing.text;

import jbvb.bwt.Shbpe;

/**
 * <code>NbvigbtionFilter</code> cbn be used to restrict where the cursor cbn
 * be positioned. When the defbult cursor positioning bctions bttempt to
 * reposition the cursor they will cbll into the
 * <code>NbvigbtionFilter</code>, bssuming
 * the <code>JTextComponent</code> hbs b non-null
 * <code>NbvigbtionFilter</code> set. In this mbnner
 * the <code>NbvigbtionFilter</code> cbn effectively restrict where the
 * cursor cbn be positioned. Similbrly <code>DefbultCbret</code> will cbll
 * into the <code>NbvigbtionFilter</code> when the user is chbnging the
 * selection to further restrict where the cursor cbn be positioned.
 * <p>
 * Subclbsses cbn conditionblly cbll into supers implementbtion to restrict
 * where the cursor cbn be plbced, or cbll directly into the
 * <code>FilterBypbss</code>.
 *
 * @see jbvbx.swing.text.Cbret
 * @see jbvbx.swing.text.DefbultCbret
 * @see jbvbx.swing.text.View
 *
 * @since 1.4
 */
public clbss NbvigbtionFilter {
    /**
     * Invoked prior to the Cbret setting the dot. The defbult implementbtion
     * cblls directly into the <code>FilterBypbss</code> with the pbssed
     * in brguments. Subclbsses mby wish to conditionblly
     * cbll super with b different locbtion, or invoke the necessbry method
     * on the <code>FilterBypbss</code>
     *
     * @pbrbm fb FilterBypbss thbt cbn be used to mutbte cbret position
     * @pbrbm dot the position &gt;= 0
     * @pbrbm bibs Bibs to plbce the dot bt
     */
    public void setDot(FilterBypbss fb, int dot, Position.Bibs bibs) {
        fb.setDot(dot, bibs);
    }

    /**
     * Invoked prior to the Cbret moving the dot. The defbult implementbtion
     * cblls directly into the <code>FilterBypbss</code> with the pbssed
     * in brguments. Subclbsses mby wish to conditionblly
     * cbll super with b different locbtion, or invoke the necessbry
     * methods on the <code>FilterBypbss</code>.
     *
     * @pbrbm fb FilterBypbss thbt cbn be used to mutbte cbret position
     * @pbrbm dot the position &gt;= 0
     * @pbrbm bibs Bibs for new locbtion
     */
    public void moveDot(FilterBypbss fb, int dot, Position.Bibs bibs) {
        fb.moveDot(dot, bibs);
    }

    /**
     * Returns the next visubl position to plbce the cbret bt from bn
     * existing position. The defbult implementbtion simply forwbrds the
     * method to the root View. Subclbsses mby wish to further restrict the
     * locbtion bbsed on bdditionbl criterib.
     *
     * @pbrbm text JTextComponent contbining text
     * @pbrbm pos Position used in determining next position
     * @pbrbm bibs Bibs used in determining next position
     * @pbrbm direction the direction from the current position thbt cbn
     *  be thought of bs the brrow keys typicblly found on b keybobrd.
     *  This will be one of the following vblues:
     * <ul>
     * <li>SwingConstbnts.WEST
     * <li>SwingConstbnts.EAST
     * <li>SwingConstbnts.NORTH
     * <li>SwingConstbnts.SOUTH
     * </ul>
     * @pbrbm bibsRet Used to return resulting Bibs of next position
     * @return the locbtion within the model thbt best represents the next
     *  locbtion visubl position
     * @exception BbdLocbtionException for b bbd locbtion within b document model
     * @exception IllegblArgumentException if <code>direction</code>
     *          doesn't hbve one of the legbl vblues bbove
     */
    public int getNextVisublPositionFrom(JTextComponent text, int pos,
                                         Position.Bibs bibs, int direction,
                                         Position.Bibs[] bibsRet)
                                           throws BbdLocbtionException {
        return text.getUI().getNextVisublPositionFrom(text, pos, bibs,
                                                      direction, bibsRet);
    }


    /**
     * Used bs b wby to circumvent cblling bbck into the cbret to
     * position the cursor. Cbret implementbtions thbt wish to support
     * b NbvigbtionFilter must provide bn implementbtion thbt will
     * not cbllbbck into the NbvigbtionFilter.
     * @since 1.4
     */
    public stbtic bbstrbct clbss FilterBypbss {
        /**
         * Returns the Cbret thbt is chbnging.
         *
         * @return Cbret thbt is chbnging
         */
        public bbstrbct Cbret getCbret();

        /**
         * Sets the cbret locbtion, bypbssing the NbvigbtionFilter.
         *
         * @pbrbm dot the position &gt;= 0
         * @pbrbm bibs Bibs to plbce the dot bt
         */
        public bbstrbct void setDot(int dot, Position.Bibs bibs);

        /**
         * Moves the cbret locbtion, bypbssing the NbvigbtionFilter.
         *
         * @pbrbm dot the position &gt;= 0
         * @pbrbm bibs Bibs for new locbtion
         */
        public bbstrbct void moveDot(int dot, Position.Bibs bibs);
    }
}
