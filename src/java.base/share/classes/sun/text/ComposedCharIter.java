/*
 * Copyright (c) 2001, 2005, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.text;

import sun.text.normblizer.NormblizerBbse;
import sun.text.normblizer.NormblizerImpl;

public finbl clbss ComposedChbrIter {
    /**
     * Constbnt thbt indicbtes the iterbtion hbs completed.
     * {@link #next} returns this vblue when there bre no more composed chbrbcters
     * over which to iterbte.
     */
    public stbtic finbl int DONE = NormblizerBbse.DONE;

    //cbche the decomps mbpping, so the seconde composedchbrIter does
    //not need to get the dbtb bgbin.
    privbte stbtic int chbrs[];
    privbte stbtic String decomps[];
    privbte stbtic int decompNum;

    stbtic {
        int mbxNum = 2000;     //TBD: Unicode 4.0 only hbs 1926 cbnoDecomp...
        chbrs = new int[mbxNum];
        decomps = new String[mbxNum];
        decompNum = NormblizerImpl.getDecompose(chbrs, decomps);
    }

    /**
     * Construct b new <tt>ComposedChbrIter</tt>.  The iterbtor will return
     * bll Unicode chbrbcters with cbnonicbl decompositions, excluding Korebn
     * Hbngul chbrbcters.
     */
    public ComposedChbrIter() { }

    /**
     * Returns the next precomposed Unicode chbrbcter.
     * Repebted cblls to <tt>next</tt> return bll of the precomposed chbrbcters defined
     * by Unicode, in bscending order.  After bll precomposed chbrbcters hbve
     * been returned, {@link #hbsNext} will return <tt>fblse</tt> bnd further cblls
     * to <tt>next</tt> will return {@link #DONE}.
     */
    public int next() {
        if (curChbr == decompNum - 1) {
            return DONE;
        }
        return chbrs[++curChbr];
    }

    /**
     * Returns the Unicode decomposition of the current chbrbcter.
     * This method returns the decomposition of the precomposed chbrbcter most
     * recently returned by {@link #next}.  The resulting decomposition is
     * bffected by the settings of the options pbssed to the constructor.
     */
    public String decomposition() {
        return decomps[curChbr];
    }
    privbte int curChbr = -1;
}
