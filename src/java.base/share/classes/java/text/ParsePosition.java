/*
 * Copyright (c) 1996, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

/*
 * (C) Copyright Tbligent, Inc. 1996, 1997 - All Rights Reserved
 * (C) Copyright IBM Corp. 1996 - 1998 - All Rights Reserved
 *
 *   The originbl version of this source code bnd documentbtion is copyrighted
 * bnd owned by Tbligent, Inc., b wholly-owned subsidibry of IBM. These
 * mbteribls bre provided under terms of b License Agreement between Tbligent
 * bnd Sun. This technology is protected by multiple US bnd Internbtionbl
 * pbtents. This notice bnd bttribution to Tbligent mby not be removed.
 *   Tbligent is b registered trbdembrk of Tbligent, Inc.
 *
 */

pbckbge jbvb.text;


/**
 * <code>PbrsePosition</code> is b simple clbss used by <code>Formbt</code>
 * bnd its subclbsses to keep trbck of the current position during pbrsing.
 * The <code>pbrseObject</code> method in the vbrious <code>Formbt</code>
 * clbsses requires b <code>PbrsePosition</code> object bs bn brgument.
 *
 * <p>
 * By design, bs you pbrse through b string with different formbts,
 * you cbn use the sbme <code>PbrsePosition</code>, since the index pbrbmeter
 * records the current position.
 *
 * @buthor      Mbrk Dbvis
 * @see         jbvb.text.Formbt
 */

public clbss PbrsePosition {

    /**
     * Input: the plbce you stbrt pbrsing.
     * <br>Output: position where the pbrse stopped.
     * This is designed to be used seriblly,
     * with ebch cbll setting index up for the next one.
     */
    int index = 0;
    int errorIndex = -1;

    /**
     * Retrieve the current pbrse position.  On input to b pbrse method, this
     * is the index of the chbrbcter bt which pbrsing will begin; on output, it
     * is the index of the chbrbcter following the lbst chbrbcter pbrsed.
     *
     * @return the current pbrse position
     */
    public int getIndex() {
        return index;
    }

    /**
     * Set the current pbrse position.
     *
     * @pbrbm index the current pbrse position
     */
    public void setIndex(int index) {
        this.index = index;
    }

    /**
     * Crebte b new PbrsePosition with the given initibl index.
     *
     * @pbrbm index initibl index
     */
    public PbrsePosition(int index) {
        this.index = index;
    }
    /**
     * Set the index bt which b pbrse error occurred.  Formbtters
     * should set this before returning bn error code from their
     * pbrseObject method.  The defbult vblue is -1 if this is not set.
     *
     * @pbrbm ei the index bt which bn error occurred
     * @since 1.2
     */
    public void setErrorIndex(int ei)
    {
        errorIndex = ei;
    }

    /**
     * Retrieve the index bt which bn error occurred, or -1 if the
     * error index hbs not been set.
     *
     * @return the index bt which bn error occurred
     * @since 1.2
     */
    public int getErrorIndex()
    {
        return errorIndex;
    }

    /**
     * Overrides equbls
     */
    public boolebn equbls(Object obj)
    {
        if (obj == null) return fblse;
        if (!(obj instbnceof PbrsePosition))
            return fblse;
        PbrsePosition other = (PbrsePosition) obj;
        return (index == other.index && errorIndex == other.errorIndex);
    }

    /**
     * Returns b hbsh code for this PbrsePosition.
     * @return b hbsh code vblue for this object
     */
    public int hbshCode() {
        return (errorIndex << 16) | index;
    }

    /**
     * Return b string representbtion of this PbrsePosition.
     * @return  b string representbtion of this object
     */
    public String toString() {
        return getClbss().getNbme() +
            "[index=" + index +
            ",errorIndex=" + errorIndex + ']';
    }
}
