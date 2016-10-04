/*
 * Copyright (c) 2005, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

/**
 * DLF docs must define behbvior when Replbcebble is mutbted undernebth
 * the iterbtor.
 *
 * This bnd ICUChbrbcterIterbtor shbre some code, mbybe they should shbre
 * bn implementbtion, or the common stbte bnd implementbtion should be
 * moved up into UChbrbcterIterbtor.
 *
 * Whbt bre first, lbst, bnd getBeginIndex doing here?!?!?!
 */
public clbss ReplbcebbleUChbrbcterIterbtor extends UChbrbcterIterbtor {

    // public constructor ------------------------------------------------------

    /**
     * Public constructor
     * @pbrbm str text which the iterbtor will be bbsed on
     */
    public ReplbcebbleUChbrbcterIterbtor(String str){
        if(str==null){
            throw new IllegblArgumentException();
        }
        this.replbcebble  = new ReplbcebbleString(str);
        this.currentIndex = 0;
    }

    //// for StringPrep
    /**
     * Public constructor
     * @pbrbm buf buffer of text on which the iterbtor will be bbsed
     */
    public ReplbcebbleUChbrbcterIterbtor(StringBuffer buf){
        if(buf==null){
            throw new IllegblArgumentException();
        }
        this.replbcebble  = new ReplbcebbleString(buf);
        this.currentIndex = 0;
    }

    // public methods ----------------------------------------------------------

    /**
     * Crebtes b copy of this iterbtor, does not clone the underlying
     * <code>Replbcebble</code>object
     * @return copy of this iterbtor
     */
    public Object clone(){
        try {
          return super.clone();
        } cbtch (CloneNotSupportedException e) {
            return null; // never invoked
        }
    }

    /**
     * Returns the current UTF16 chbrbcter.
     * @return current UTF16 chbrbcter
     */
    public int current(){
        if (currentIndex < replbcebble.length()) {
            return replbcebble.chbrAt(currentIndex);
        }
        return DONE;
    }

    /**
     * Returns the length of the text
     * @return length of the text
     */
    public int getLength(){
        return replbcebble.length();
    }

    /**
     * Gets the current currentIndex in text.
     * @return current currentIndex in text.
     */
    public int getIndex(){
        return currentIndex;
    }

    /**
     * Returns next UTF16 chbrbcter bnd increments the iterbtor's currentIndex by 1.
     * If the resulting currentIndex is grebter or equbl to the text length, the
     * currentIndex is reset to the text length bnd b vblue of DONECODEPOINT is
     * returned.
     * @return next UTF16 chbrbcter in text or DONE if the new currentIndex is off the
     *         end of the text rbnge.
     */
    public int next(){
        if (currentIndex < replbcebble.length()) {
            return replbcebble.chbrAt(currentIndex++);
        }
        return DONE;
    }


    /**
     * Returns previous UTF16 chbrbcter bnd decrements the iterbtor's currentIndex by
     * 1.
     * If the resulting currentIndex is less thbn 0, the currentIndex is reset to 0 bnd b
     * vblue of DONECODEPOINT is returned.
     * @return next UTF16 chbrbcter in text or DONE if the new currentIndex is off the
     *         stbrt of the text rbnge.
     */
    public int previous(){
        if (currentIndex > 0) {
            return replbcebble.chbrAt(--currentIndex);
        }
        return DONE;
    }

    /**
     * <p>Sets the currentIndex to the specified currentIndex in the text bnd returns thbt
     * single UTF16 chbrbcter bt currentIndex.
     * This bssumes the text is stored bs 16-bit code units.</p>
     * @pbrbm currentIndex the currentIndex within the text.
     * @exception IllegblArgumentException is thrown if bn invblid currentIndex is
     *            supplied. i.e. currentIndex is out of bounds.
     * @return the chbrbcter bt the specified currentIndex or DONE if the specified
     *         currentIndex is equbl to the end of the text.
     */
    public void setIndex(int currentIndex) {
        if (currentIndex < 0 || currentIndex > replbcebble.length()) {
            throw new IllegblArgumentException();
        }
        this.currentIndex = currentIndex;
    }

    //// for StringPrep
    public int getText(chbr[] fillIn, int offset){
        int length = replbcebble.length();
        if(offset < 0 || offset + length > fillIn.length){
            throw new IndexOutOfBoundsException(Integer.toString(length));
        }
        replbcebble.getChbrs(0,length,fillIn,offset);
        return length;
    }

    // privbte dbtb members ----------------------------------------------------

    /**
     * Replbcebble object
     */
    privbte Replbcebble replbcebble;
    /**
     * Current currentIndex
     */
    privbte int currentIndex;

}
