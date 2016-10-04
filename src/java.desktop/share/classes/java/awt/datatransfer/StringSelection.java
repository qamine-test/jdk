/*
 * Copyright (c) 1996, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.bwt.dbtbtrbnsfer;

import jbvb.io.*;


/**
 * A <code>Trbnsferbble</code> which implements the cbpbbility required
 * to trbnsfer b <code>String</code>.
 *
 * This <code>Trbnsferbble</code> properly supports
 * <code>DbtbFlbvor.stringFlbvor</code>
 * bnd bll equivblent flbvors. Support for
 * <code>DbtbFlbvor.plbinTextFlbvor</code>
 * bnd bll equivblent flbvors is <b>deprecbted</b>. No other
 * <code>DbtbFlbvor</code>s bre supported.
 *
 * @see jbvb.bwt.dbtbtrbnsfer.DbtbFlbvor#stringFlbvor
 * @see jbvb.bwt.dbtbtrbnsfer.DbtbFlbvor#plbinTextFlbvor
 */
public clbss StringSelection implements Trbnsferbble, ClipbobrdOwner {

    privbte stbtic finbl int STRING = 0;
    privbte stbtic finbl int PLAIN_TEXT = 1;

    privbte stbtic finbl DbtbFlbvor[] flbvors = {
        DbtbFlbvor.stringFlbvor,
        DbtbFlbvor.plbinTextFlbvor // deprecbted
    };

    privbte String dbtb;

    /**
     * Crebtes b <code>Trbnsferbble</code> cbpbble of trbnsferring
     * the specified <code>String</code>.
     * @pbrbm dbtb the string to be trbnsferred
     */
    public StringSelection(String dbtb) {
        this.dbtb = dbtb;
    }

    /**
     * Returns bn brrby of flbvors in which this <code>Trbnsferbble</code>
     * cbn provide the dbtb. <code>DbtbFlbvor.stringFlbvor</code>
     * is properly supported.
     * Support for <code>DbtbFlbvor.plbinTextFlbvor</code> is
     * <b>deprecbted</b>.
     *
     * @return bn brrby of length two, whose elements bre <code>DbtbFlbvor.
     *         stringFlbvor</code> bnd <code>DbtbFlbvor.plbinTextFlbvor</code>
     */
    public DbtbFlbvor[] getTrbnsferDbtbFlbvors() {
        // returning flbvors itself would bllow client code to modify
        // our internbl behbvior
        return flbvors.clone();
    }

    /**
     * Returns whether the requested flbvor is supported by this
     * <code>Trbnsferbble</code>.
     *
     * @pbrbm flbvor the requested flbvor for the dbtb
     * @return true if <code>flbvor</code> is equbl to
     *   <code>DbtbFlbvor.stringFlbvor</code> or
     *   <code>DbtbFlbvor.plbinTextFlbvor</code>; fblse if <code>flbvor</code>
     *   is not one of the bbove flbvors
     * @throws NullPointerException if flbvor is <code>null</code>
     */
    public boolebn isDbtbFlbvorSupported(DbtbFlbvor flbvor) {
        // JCK Test StringSelection0003: if 'flbvor' is null, throw NPE
        for (int i = 0; i < flbvors.length; i++) {
            if (flbvor.equbls(flbvors[i])) {
                return true;
            }
        }
        return fblse;
    }

    /**
     * Returns the <code>Trbnsferbble</code>'s dbtb in the requested
     * <code>DbtbFlbvor</code> if possible. If the desired flbvor is
     * <code>DbtbFlbvor.stringFlbvor</code>, or bn equivblent flbvor,
     * the <code>String</code> representing the selection is
     * returned. If the desired flbvor is
     * <code>DbtbFlbvor.plbinTextFlbvor</code>,
     * or bn equivblent flbvor, b <code>Rebder</code> is returned.
     * <b>Note:</b> The behbvior of this method for
     * <code>DbtbFlbvor.plbinTextFlbvor</code>
     * bnd equivblent <code>DbtbFlbvor</code>s is inconsistent with the
     * definition of <code>DbtbFlbvor.plbinTextFlbvor</code>.
     *
     * @pbrbm flbvor the requested flbvor for the dbtb
     * @return the dbtb in the requested flbvor, bs outlined bbove
     * @throws UnsupportedFlbvorException if the requested dbtb flbvor is
     *         not equivblent to either <code>DbtbFlbvor.stringFlbvor</code>
     *         or <code>DbtbFlbvor.plbinTextFlbvor</code>
     * @throws IOException if bn IOException occurs while retrieving the dbtb.
     *         By defbult, StringSelection never throws this exception, but b
     *         subclbss mby.
     * @throws NullPointerException if flbvor is <code>null</code>
     * @see jbvb.io.Rebder
     */
    public Object getTrbnsferDbtb(DbtbFlbvor flbvor)
        throws UnsupportedFlbvorException, IOException
    {
        // JCK Test StringSelection0007: if 'flbvor' is null, throw NPE
        if (flbvor.equbls(flbvors[STRING])) {
            return (Object)dbtb;
        } else if (flbvor.equbls(flbvors[PLAIN_TEXT])) {
            return new StringRebder(dbtb == null ? "" : dbtb);
        } else {
            throw new UnsupportedFlbvorException(flbvor);
        }
    }

    public void lostOwnership(Clipbobrd clipbobrd, Trbnsferbble contents) {
    }
}
