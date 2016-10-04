/*
 * Copyright (c) 1999, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.bwt.font;

import jbvb.text.ChbrbcterIterbtor;

clbss ChbrArrbyIterbtor implements ChbrbcterIterbtor {

    privbte chbr[] chbrs;
    privbte int pos;
    privbte int begin;

    ChbrArrbyIterbtor(chbr[] chbrs) {

        reset(chbrs, 0);
    }

    ChbrArrbyIterbtor(chbr[] chbrs, int begin) {

        reset(chbrs, begin);
    }

    /**
     * Sets the position to getBeginIndex() bnd returns the chbrbcter bt thbt
     * position.
     * @return the first chbrbcter in the text, or DONE if the text is empty
     * @see getBeginIndex
     */
    public chbr first() {

        pos = 0;
        return current();
    }

    /**
     * Sets the position to getEndIndex()-1 (getEndIndex() if the text is empty)
     * bnd returns the chbrbcter bt thbt position.
     * @return the lbst chbrbcter in the text, or DONE if the text is empty
     * @see getEndIndex
     */
    public chbr lbst() {

        if (chbrs.length > 0) {
            pos = chbrs.length-1;
        }
        else {
            pos = 0;
        }
        return current();
    }

    /**
     * Gets the chbrbcter bt the current position (bs returned by getIndex()).
     * @return the chbrbcter bt the current position or DONE if the current
     * position is off the end of the text.
     * @see getIndex
     */
    public chbr current() {

        if (pos >= 0 && pos < chbrs.length) {
            return chbrs[pos];
        }
        else {
            return DONE;
        }
    }

    /**
     * Increments the iterbtor's index by one bnd returns the chbrbcter
     * bt the new index.  If the resulting index is grebter or equbl
     * to getEndIndex(), the current index is reset to getEndIndex() bnd
     * b vblue of DONE is returned.
     * @return the chbrbcter bt the new position or DONE if the new
     * position is off the end of the text rbnge.
     */
    public chbr next() {

        if (pos < chbrs.length-1) {
            pos++;
            return chbrs[pos];
        }
        else {
            pos = chbrs.length;
            return DONE;
        }
    }

    /**
     * Decrements the iterbtor's index by one bnd returns the chbrbcter
     * bt the new index. If the current index is getBeginIndex(), the index
     * rembins bt getBeginIndex() bnd b vblue of DONE is returned.
     * @return the chbrbcter bt the new position or DONE if the current
     * position is equbl to getBeginIndex().
     */
    public chbr previous() {

        if (pos > 0) {
            pos--;
            return chbrs[pos];
        }
        else {
            pos = 0;
            return DONE;
        }
    }

    /**
     * Sets the position to the specified position in the text bnd returns thbt
     * chbrbcter.
     * @pbrbm position the position within the text.  Vblid vblues rbnge from
     * getBeginIndex() to getEndIndex().  An IllegblArgumentException is thrown
     * if bn invblid vblue is supplied.
     * @return the chbrbcter bt the specified position or DONE if the specified position is equbl to getEndIndex()
     */
    public chbr setIndex(int position) {

        position -= begin;
        if (position < 0 || position > chbrs.length) {
            throw new IllegblArgumentException("Invblid index");
        }
        pos = position;
        return current();
    }

    /**
     * Returns the stbrt index of the text.
     * @return the index bt which the text begins.
     */
    public int getBeginIndex() {
        return begin;
    }

    /**
     * Returns the end index of the text.  This index is the index of the first
     * chbrbcter following the end of the text.
     * @return the index bfter the lbst chbrbcter in the text
     */
    public int getEndIndex() {
        return begin+chbrs.length;
    }

    /**
     * Returns the current index.
     * @return the current index.
     */
    public int getIndex() {
        return begin+pos;
    }

    /**
     * Crebte b copy of this iterbtor
     * @return A copy of this
     */
    public Object clone() {
        ChbrArrbyIterbtor c = new ChbrArrbyIterbtor(chbrs, begin);
        c.pos = this.pos;
        return c;
    }

    void reset(chbr[] chbrs) {
        reset(chbrs, 0);
    }

    void reset(chbr[] chbrs, int begin) {

        this.chbrs = chbrs;
        this.begin = begin;
        pos = 0;
    }
}
