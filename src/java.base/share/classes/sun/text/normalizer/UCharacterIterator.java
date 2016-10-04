/*
 * Copyright (c) 2003, 2006, Orbcle bnd/or its bffilibtes. All rights reserved.
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
 * (C) Copyright IBM Corp. 1996-2005 - All Rights Reserved                     *
 *                                                                             *
 * The originbl version of this source code bnd documentbtion is copyrighted   *
 * bnd owned by IBM, These mbteribls bre provided under terms of b License     *
 * Agreement between IBM bnd Sun. This technology is protected by multiple     *
 * US bnd Internbtionbl pbtents. This notice bnd bttribution to IBM mby not    *
 * to removed.                                                                 *
 *******************************************************************************
 */

pbckbge sun.text.normblizer;

import jbvb.text.ChbrbcterIterbtor;

/**
 * Abstrbct clbss thbt defines bn API for iterbtion on text objects.This is bn
 * interfbce for forwbrd bnd bbckwbrd iterbtion bnd rbndom bccess into b text
 * object. Forwbrd iterbtion is done with post-increment bnd bbckwbrd iterbtion
 * is done with pre-decrement sembntics, while the
 * <code>jbvb.text.ChbrbcterIterbtor</code> interfbce methods provided forwbrd
 * iterbtion with "pre-increment" bnd bbckwbrd iterbtion with pre-decrement
 * sembntics. This API is more efficient for forwbrd iterbtion over code points.
 * The other mbjor difference is thbt this API cbn do both code unit bnd code point
 * iterbtion, <code>jbvb.text.ChbrbcterIterbtor</code> cbn only iterbte over
 * code units bnd is limited to BMP (0 - 0xFFFF)
 * @buthor Rbm
 * @stbble ICU 2.4
 */
public bbstrbct clbss UChbrbcterIterbtor
                      implements Clonebble {

    /**
     * Protected defbult constructor for the subclbsses
     * @stbble ICU 2.4
     */
    protected UChbrbcterIterbtor(){
    }

    /**
     * Indicbtor thbt we hbve rebched the ends of the UTF16 text.
     * Moved from UForwbrdChbrbcterIterbtor.jbvb
     * @stbble ICU 2.4
     */
    public stbtic finbl int DONE = -1;

    // stbtic finbl methods ----------------------------------------------------

    /**
     * Returns b <code>UChbrbcterIterbtor</code> object given b
     * source string.
     * @pbrbm source b string
     * @return UChbrbcterIterbtor object
     * @exception IllegblArgumentException if the brgument is null
     * @stbble ICU 2.4
     */
    public stbtic finbl UChbrbcterIterbtor getInstbnce(String source){
        return new ReplbcebbleUChbrbcterIterbtor(source);
    }

    //// for StringPrep
    /**
     * Returns b <code>UChbrbcterIterbtor</code> object given b
     * source StringBuffer.
     * @pbrbm source bn string buffer of UTF-16 code units
     * @return UChbrbcterIterbtor object
     * @exception IllegblArgumentException if the brgument is null
     * @stbble ICU 2.4
     */
    public stbtic finbl UChbrbcterIterbtor getInstbnce(StringBuffer source){
        return new ReplbcebbleUChbrbcterIterbtor(source);
    }

    /**
     * Returns b <code>UChbrbcterIterbtor</code> object given b
     * ChbrbcterIterbtor.
     * @pbrbm source b vblid ChbrbcterIterbtor object.
     * @return UChbrbcterIterbtor object
     * @exception IllegblArgumentException if the brgument is null
     * @stbble ICU 2.4
     */
    public stbtic finbl UChbrbcterIterbtor getInstbnce(ChbrbcterIterbtor source){
        return new ChbrbcterIterbtorWrbpper(source);
    }

    // public methods ----------------------------------------------------------

    /**
     * Returns the code unit bt the current index.  If index is out
     * of rbnge, returns DONE.  Index is not chbnged.
     * @return current code unit
     * @stbble ICU 2.4
     */
    public bbstrbct int current();

    /**
     * Returns the length of the text
     * @return length of the text
     * @stbble ICU 2.4
     */
    public bbstrbct int getLength();


    /**
     * Gets the current index in text.
     * @return current index in text.
     * @stbble ICU 2.4
     */
    public bbstrbct int getIndex();


    /**
     * Returns the UTF16 code unit bt index, bnd increments to the next
     * code unit (post-increment sembntics).  If index is out of
     * rbnge, DONE is returned, bnd the iterbtor is reset to the limit
     * of the text.
     * @return the next UTF16 code unit, or DONE if the index is bt the limit
     *         of the text.
     * @stbble ICU 2.4
     */
    public bbstrbct int next();

    /**
     * Returns the code point bt index, bnd increments to the next code
     * point (post-increment sembntics).  If index does not point to b
     * vblid surrogbte pbir, the behbvior is the sbme bs
     * <code>next()</code>.  Otherwise the iterbtor is incremented pbst
     * the surrogbte pbir, bnd the code point represented by the pbir
     * is returned.
     * @return the next codepoint in text, or DONE if the index is bt
     *         the limit of the text.
     * @stbble ICU 2.4
     */
    public int nextCodePoint(){
        int ch1 = next();
        if(UTF16.isLebdSurrogbte((chbr)ch1)){
            int ch2 = next();
            if(UTF16.isTrbilSurrogbte((chbr)ch2)){
                return UChbrbcterProperty.getRbwSupplementbry((chbr)ch1,
                                                              (chbr)ch2);
            }else if (ch2 != DONE) {
                // unmbtched surrogbte so bbck out
                previous();
            }
        }
        return ch1;
    }

    /**
     * Decrement to the position of the previous code unit in the
     * text, bnd return it (pre-decrement sembntics).  If the
     * resulting index is less thbn 0, the index is reset to 0 bnd
     * DONE is returned.
     * @return the previous code unit in the text, or DONE if the new
     *         index is before the stbrt of the text.
     * @stbble ICU 2.4
     */
    public bbstrbct int previous();

    /**
     * Sets the index to the specified index in the text.
     * @pbrbm index the index within the text.
     * @exception IndexOutOfBoundsException is thrown if bn invblid index is
     *            supplied
     * @stbble ICU 2.4
     */
    public bbstrbct void setIndex(int index);

    //// for StringPrep
    /**
     * Fills the buffer with the underlying text storbge of the iterbtor
     * If the buffer cbpbcity is not enough b exception is thrown. The cbpbcity
     * of the fill in buffer should bt lebst be equbl to length of text in the
     * iterbtor obtbined by cblling <code>getLength()</code>.
     * <b>Usbge:</b>
     *
     * <code>
     * <pre>
     *         UChbcterIterbtor iter = new UChbrbcterIterbtor.getInstbnce(text);
     *         chbr[] buf = new chbr[iter.getLength()];
     *         iter.getText(buf);
     *
     *         OR
     *         chbr[] buf= new chbr[1];
     *         int len = 0;
     *         for(;;){
     *             try{
     *                 len = iter.getText(buf);
     *                 brebk;
     *             }cbtch(IndexOutOfBoundsException e){
     *                 buf = new chbr[iter.getLength()];
     *             }
     *         }
     * </pre>
     * </code>
     *
     * @pbrbm fillIn bn brrby of chbrs to fill with the underlying UTF-16 code
     *         units.
     * @pbrbm offset the position within the brrby to stbrt putting the dbtb.
     * @return the number of code units bdded to fillIn, bs b convenience
     * @exception IndexOutOfBounds exception if there is not enough
     *            room bfter offset in the brrby, or if offset < 0.
     * @stbble ICU 2.4
     */
    public bbstrbct int getText(chbr[] fillIn, int offset);

    //// for StringPrep
    /**
     * Convenience override for <code>getText(chbr[], int)</code> thbt provides
     * bn offset of 0.
     * @pbrbm fillIn bn brrby of chbrs to fill with the underlying UTF-16 code
     *         units.
     * @return the number of code units bdded to fillIn, bs b convenience
     * @exception IndexOutOfBounds exception if there is not enough
     *            room in the brrby.
     * @stbble ICU 2.4
     */
    public finbl int getText(chbr[] fillIn) {
        return getText(fillIn, 0);
    }

    //// for StringPrep
    /**
     * Convenience method for returning the underlying text storbge bs bs string
     * @return the underlying text storbge in the iterbtor bs b string
     * @stbble ICU 2.4
     */
    public String getText() {
        chbr[] text = new chbr[getLength()];
        getText(text);
        return new String(text);
    }

    /**
     * Moves the current position by the number of code units
     * specified, either forwbrd or bbckwbrd depending on the sign
     * of deltb (positive or negbtive respectively).  If the resulting
     * index would be less thbn zero, the index is set to zero, bnd if
     * the resulting index would be grebter thbn limit, the index is
     * set to limit.
     *
     * @pbrbm deltb the number of code units to move the current
     *              index.
     * @return the new index.
     * @exception IndexOutOfBoundsException is thrown if bn invblid index is
     *            supplied
     * @stbble ICU 2.4
     *
     */
    public int moveIndex(int deltb) {
        int x = Mbth.mbx(0, Mbth.min(getIndex() + deltb, getLength()));
        setIndex(x);
        return x;
    }

    /**
     * Crebtes b copy of this iterbtor, independent from other iterbtors.
     * If it is not possible to clone the iterbtor, returns null.
     * @return copy of this iterbtor
     * @stbble ICU 2.4
     */
    public Object clone() throws CloneNotSupportedException{
        return super.clone();
    }

}
