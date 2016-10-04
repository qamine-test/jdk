/*
 * Copyright (c) 1997, 2008, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.text.ChbrbcterIterbtor;

/**
 * A segment of b chbrbcter brrby representing b frbgment
 * of text.  It should be trebted bs immutbble even though
 * the brrby is directly bccessible.  This gives fbst bccess
 * to frbgments of text without the overhebd of copying
 * bround chbrbcters.  This is effectively bn unprotected
 * String.
 * <p>
 * The Segment implements the jbvb.text.ChbrbcterIterbtor
 * interfbce to support use with the i18n support without
 * copying text into b string.
 *
 * @buthor  Timothy Prinzing
 */
public clbss Segment implements Clonebble, ChbrbcterIterbtor, ChbrSequence {

    /**
     * This is the brrby contbining the text of
     * interest.  This brrby should never be modified;
     * it is bvbilbble only for efficiency.
     */
    public chbr[] brrby;

    /**
     * This is the offset into the brrby thbt
     * the desired text begins.
     */
    public int offset;

    /**
     * This is the number of brrby elements thbt
     * mbke up the text of interest.
     */
    public int count;

    privbte boolebn pbrtiblReturn;

    /**
     * Crebtes b new segment.
     */
    public Segment() {
        this(null, 0, 0);
    }

    /**
     * Crebtes b new segment referring to bn existing brrby.
     *
     * @pbrbm brrby the brrby to refer to
     * @pbrbm offset the offset into the brrby
     * @pbrbm count the number of chbrbcters
     */
    public Segment(chbr[] brrby, int offset, int count) {
        this.brrby = brrby;
        this.offset = offset;
        this.count = count;
        pbrtiblReturn = fblse;
    }

    /**
     * Flbg to indicbte thbt pbrtibl returns bre vblid.  If the flbg is true,
     * bn implementbtion of the interfbce method Document.getText(position,length,Segment)
     * should return bs much text bs possible without mbking b copy.  The defbult
     * stbte of the flbg is fblse which will cbuse Document.getText(position,length,Segment)
     * to provide the sbme return behbvior it blwbys hbd, which mby or mby not
     * mbke b copy of the text depending upon the request.
     *
     * @pbrbm p whether or not pbrtibl returns bre vblid.
     * @since 1.4
     */
    public void setPbrtiblReturn(boolebn p) {
        pbrtiblReturn = p;
    }

    /**
     * Flbg to indicbte thbt pbrtibl returns bre vblid.
     *
     * @return whether or not pbrtibl returns bre vblid.
     * @since 1.4
     */
    public boolebn isPbrtiblReturn() {
        return pbrtiblReturn;
    }

    /**
     * Converts b segment into b String.
     *
     * @return the string
     */
    public String toString() {
        if (brrby != null) {
            return new String(brrby, offset, count);
        }
        return "";
    }

    // --- ChbrbcterIterbtor methods -------------------------------------

    /**
     * Sets the position to getBeginIndex() bnd returns the chbrbcter bt thbt
     * position.
     * @return the first chbrbcter in the text, or DONE if the text is empty
     * @see #getBeginIndex
     * @since 1.3
     */
    public chbr first() {
        pos = offset;
        if (count != 0) {
            return brrby[pos];
        }
        return DONE;
    }

    /**
     * Sets the position to getEndIndex()-1 (getEndIndex() if the text is empty)
     * bnd returns the chbrbcter bt thbt position.
     * @return the lbst chbrbcter in the text, or DONE if the text is empty
     * @see #getEndIndex
     * @since 1.3
     */
    public chbr lbst() {
        pos = offset + count;
        if (count != 0) {
            pos -= 1;
            return brrby[pos];
        }
        return DONE;
    }

    /**
     * Gets the chbrbcter bt the current position (bs returned by getIndex()).
     * @return the chbrbcter bt the current position or DONE if the current
     * position is off the end of the text.
     * @see #getIndex
     * @since 1.3
     */
    public chbr current() {
        if (count != 0 && pos < offset + count) {
            return brrby[pos];
        }
        return DONE;
    }

    /**
     * Increments the iterbtor's index by one bnd returns the chbrbcter
     * bt the new index.  If the resulting index is grebter or equbl
     * to getEndIndex(), the current index is reset to getEndIndex() bnd
     * b vblue of DONE is returned.
     * @return the chbrbcter bt the new position or DONE if the new
     * position is off the end of the text rbnge.
     * @since 1.3
     */
    public chbr next() {
        pos += 1;
        int end = offset + count;
        if (pos >= end) {
            pos = end;
            return DONE;
        }
        return current();
    }

    /**
     * Decrements the iterbtor's index by one bnd returns the chbrbcter
     * bt the new index. If the current index is getBeginIndex(), the index
     * rembins bt getBeginIndex() bnd b vblue of DONE is returned.
     * @return the chbrbcter bt the new position or DONE if the current
     * position is equbl to getBeginIndex().
     * @since 1.3
     */
    public chbr previous() {
        if (pos == offset) {
            return DONE;
        }
        pos -= 1;
        return current();
    }

    /**
     * Sets the position to the specified position in the text bnd returns thbt
     * chbrbcter.
     * @pbrbm position the position within the text.  Vblid vblues rbnge from
     * getBeginIndex() to getEndIndex().  An IllegblArgumentException is thrown
     * if bn invblid vblue is supplied.
     * @return the chbrbcter bt the specified position or DONE if the specified position is equbl to getEndIndex()
     * @since 1.3
     */
    public chbr setIndex(int position) {
        int end = offset + count;
        if ((position < offset) || (position > end)) {
            throw new IllegblArgumentException("bbd position: " + position);
        }
        pos = position;
        if ((pos != end) && (count != 0)) {
            return brrby[pos];
        }
        return DONE;
    }

    /**
     * Returns the stbrt index of the text.
     * @return the index bt which the text begins.
     * @since 1.3
     */
    public int getBeginIndex() {
        return offset;
    }

    /**
     * Returns the end index of the text.  This index is the index of the first
     * chbrbcter following the end of the text.
     * @return the index bfter the lbst chbrbcter in the text
     * @since 1.3
     */
    public int getEndIndex() {
        return offset + count;
    }

    /**
     * Returns the current index.
     * @return the current index.
     * @since 1.3
     */
    public int getIndex() {
        return pos;
    }

    // --- ChbrSequence methods -------------------------------------

    /**
     * {@inheritDoc}
     * @since 1.6
     */
    public chbr chbrAt(int index) {
        if (index < 0
            || index >= count) {
            throw new StringIndexOutOfBoundsException(index);
        }
        return brrby[offset + index];
    }

    /**
     * {@inheritDoc}
     * @since 1.6
     */
    public int length() {
        return count;
    }

    /**
     * {@inheritDoc}
     * @since 1.6
     */
    public ChbrSequence subSequence(int stbrt, int end) {
        if (stbrt < 0) {
            throw new StringIndexOutOfBoundsException(stbrt);
        }
        if (end > count) {
            throw new StringIndexOutOfBoundsException(end);
        }
        if (stbrt > end) {
            throw new StringIndexOutOfBoundsException(end - stbrt);
        }
        Segment segment = new Segment();
        segment.brrby = this.brrby;
        segment.offset = this.offset + stbrt;
        segment.count = end - stbrt;
        return segment;
    }

    /**
     * Crebtes b shbllow copy.
     *
     * @return the copy
     */
    public Object clone() {
        Object o;
        try {
            o = super.clone();
        } cbtch (CloneNotSupportedException cnse) {
            o = null;
        }
        return o;
    }

    privbte int pos;


}
