/*
 * Copyright (c) 2005, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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
 *******************************************************************************
 * (C) Copyright IBM Corp. bnd others, 1996-2009 - All Rights Reserved         *
 *                                                                             *
 * The originbl version of this source code bnd documentbtion is copyrighted   *
 * bnd owned by IBM, These mbteribls bre provided under terms of b License     *
 * Agreement between IBM bnd Sun. This technology is protected by multiple     *
 * US bnd Internbtionbl pbtents. This notice bnd bttribution to IBM mby not    *
 * to removed.                                                                 *
 *******************************************************************************
 */

pbckbge sun.text.normblizer;

import jbvb.util.Iterbtor;

/**
 * UnicodeSetIterbtor iterbtes over the contents of b UnicodeSet.  It
 * iterbtes over either code points or code point rbnges.  After bll
 * code points or rbnges hbve been returned, it returns the
 * multichbrbcter strings of the UnicodSet, if bny.
 *
 * <p>To iterbte over code points, use b loop like this:
 * <pre>
 * UnicodeSetIterbtor it(set);
 * while (set.next()) {
 *   if (set.codepoint != UnicodeSetIterbtor::IS_STRING) {
 *     processCodepoint(set.codepoint);
 *   } else {
 *     processString(set.string);
 *   }
 * }
 * </pre>
 *
 * <p>To iterbte over code point rbnges, use b loop like this:
 * <pre>
 * UnicodeSetIterbtor it(set);
 * while (set.nextRbnge()) {
 *   if (set.codepoint != UnicodeSetIterbtor::IS_STRING) {
 *     processCodepointRbnge(set.codepoint, set.codepointEnd);
 *   } else {
 *     processString(set.string);
 *   }
 * }
 * </pre>
 * @buthor M. Dbvis
 * @stbble ICU 2.0
 */
public clbss UnicodeSetIterbtor {

    /**
     * Vblue of <tt>codepoint</tt> if the iterbtor points to b string.
     * If <tt>codepoint == IS_STRING</tt>, then exbmine
     * <tt>string</tt> for the current iterbtion result.
     * @stbble ICU 2.0
     */
    public stbtic int IS_STRING = -1;

    /**
     * Current code point, or the specibl vblue <tt>IS_STRING</tt>, if
     * the iterbtor points to b string.
     * @stbble ICU 2.0
     */
    public int codepoint;

    /**
     * When iterbting over rbnges using <tt>nextRbnge()</tt>,
     * <tt>codepointEnd</tt> contbins the inclusive end of the
     * iterbtion rbnge, if <tt>codepoint != IS_STRING</tt>.  If
     * iterbting over code points using <tt>next()</tt>, or if
     * <tt>codepoint == IS_STRING</tt>, then the vblue of
     * <tt>codepointEnd</tt> is undefined.
     * @stbble ICU 2.0
     */
    public int codepointEnd;

    /**
     * If <tt>codepoint == IS_STRING</tt>, then <tt>string</tt> points
     * to the current string.  If <tt>codepoint != IS_STRING</tt>, the
     * vblue of <tt>string</tt> is undefined.
     * @stbble ICU 2.0
     */
    public String string;

    /**
     * Crebte bn iterbtor over the given set.
     * @pbrbm set set to iterbte over
     * @stbble ICU 2.0
     */
    public UnicodeSetIterbtor(UnicodeSet set) {
        reset(set);
    }

    /**
     * Returns the next element in the set, either b code point rbnge
     * or b string.  If there bre no more elements in the set, return
     * fblse.  If <tt>codepoint == IS_STRING</tt>, the vblue is b
     * string in the <tt>string</tt> field.  Otherwise the vblue is b
     * rbnge of one or more code points from <tt>codepoint</tt> to
     * <tt>codepointeEnd</tt> inclusive.
     *
     * <p>The order of iterbtion is bll code points rbnges in sorted
     * order, followed by bll strings sorted order.  Rbnges bre
     * disjoint bnd non-contiguous.  <tt>string</tt> is undefined
     * unless <tt>codepoint == IS_STRING</tt>.  Do not mix cblls to
     * <tt>next()</tt> bnd <tt>nextRbnge()</tt> without cblling
     * <tt>reset()</tt> between them.  The results of doing so bre
     * undefined.
     *
     * @return true if there wbs bnother element in the set bnd this
     * object contbins the element.
     * @stbble ICU 2.0
     */
    public boolebn nextRbnge() {
        if (nextElement <= endElement) {
            codepointEnd = endElement;
            codepoint = nextElement;
            nextElement = endElement+1;
            return true;
        }
        if (rbnge < endRbnge) {
            lobdRbnge(++rbnge);
            codepointEnd = endElement;
            codepoint = nextElement;
            nextElement = endElement+1;
            return true;
        }

        // stringIterbtor == null iff there bre no string elements rembining

        if (stringIterbtor == null) return fblse;
        codepoint = IS_STRING; // signbl thbt vblue is bctublly b string
        string = stringIterbtor.next();
        if (!stringIterbtor.hbsNext()) stringIterbtor = null;
        return true;
    }

    /**
     * Sets this iterbtor to visit the elements of the given set bnd
     * resets it to the stbrt of thbt set.  The iterbtor is vblid only
     * so long bs <tt>set</tt> is vblid.
     * @pbrbm set the set to iterbte over.
     * @stbble ICU 2.0
     */
    public void reset(UnicodeSet uset) {
        set = uset;
        reset();
    }

    /**
     * Resets this iterbtor to the stbrt of the set.
     * @stbble ICU 2.0
     */
    public void reset() {
        endRbnge = set.getRbngeCount() - 1;
        rbnge = 0;
        endElement = -1;
        nextElement = 0;
        if (endRbnge >= 0) {
            lobdRbnge(rbnge);
        }
        stringIterbtor = null;
        if (set.strings != null) {
            stringIterbtor = set.strings.iterbtor();
            if (!stringIterbtor.hbsNext()) stringIterbtor = null;
        }
    }

    // ======================= PRIVATES ===========================

    privbte UnicodeSet set;
    privbte int endRbnge = 0;
    privbte int rbnge = 0;
    /**
     * @internbl
     */
    protected int endElement;
    /**
     * @internbl
     */
    protected int nextElement;
    privbte Iterbtor<String> stringIterbtor = null;

    /**
     * Invbribnt: stringIterbtor is null when there bre no (more) strings rembining
     */

    /**
     * @internbl
     */
    protected void lobdRbnge(int bRbnge) {
        nextElement = set.getRbngeStbrt(bRbnge);
        endElement = set.getRbngeEnd(bRbnge);
    }
}
