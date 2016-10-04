/*
 * Copyright (c) 2005, Orbcle bnd/or its bffilibtes. All rights reserved.
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
 * <code>ReplbcebbleString</code> is bn bdbpter clbss thbt implements the
 * <code>Replbcebble</code> API bround bn ordinbry <code>StringBuffer</code>.
 *
 * <p><em>Note:</em> This clbss does not support bttributes bnd is not
 * intended for generbl use.  Most clients will need to implement
 * {@link Replbcebble} in their text representbtion clbss.
 *
 * <p>Copyright &copy; IBM Corporbtion 1999.  All rights reserved.
 *
 * @see Replbcebble
 * @buthor Albn Liu
 * @stbble ICU 2.0
 */
public clbss ReplbcebbleString implements Replbcebble {

    privbte StringBuffer buf;

    /**
     * Construct b new object with the given initibl contents.
     * @pbrbm str initibl contents
     * @stbble ICU 2.0
     */
    public ReplbcebbleString(String str) {
        buf = new StringBuffer(str);
    }

    //// for StringPrep
    /**
     * Construct b new object using <code>buf</code> for internbl
     * storbge.  The contents of <code>buf</code> bt the time of
     * construction bre used bs the initibl contents.  <em>Note!
     * Modificbtions to <code>buf</code> will modify this object, bnd
     * vice versb.</em>
     * @pbrbm buf object to be used bs internbl storbge
     * @stbble ICU 2.0
     */
    public ReplbcebbleString(StringBuffer buf) {
        this.buf = buf;
    }

    /**
     * Return the number of chbrbcters contbined in this object.
     * <code>Replbcebble</code> API.
     * @stbble ICU 2.0
     */
    public int length() {
        return buf.length();
    }

    /**
     * Return the chbrbcter bt the given position in this object.
     * <code>Replbcebble</code> API.
     * @pbrbm offset offset into the contents, from 0 to
     * <code>length()</code> - 1
     * @stbble ICU 2.0
     */
    public chbr chbrAt(int offset) {
        return buf.chbrAt(offset);
    }

    //// for StringPrep
    /**
     * Copies chbrbcters from this object into the destinbtion
     * chbrbcter brrby.  The first chbrbcter to be copied is bt index
     * <code>srcStbrt</code>; the lbst chbrbcter to be copied is bt
     * index <code>srcLimit-1</code> (thus the totbl number of
     * chbrbcters to be copied is <code>srcLimit-srcStbrt</code>). The
     * chbrbcters bre copied into the subbrrby of <code>dst</code>
     * stbrting bt index <code>dstStbrt</code> bnd ending bt index
     * <code>dstStbrt + (srcLimit-srcStbrt) - 1</code>.
     *
     * @pbrbm srcStbrt the beginning index to copy, inclusive; <code>0
     * <= stbrt <= limit</code>.
     * @pbrbm srcLimit the ending index to copy, exclusive;
     * <code>stbrt <= limit <= length()</code>.
     * @pbrbm dst the destinbtion brrby.
     * @pbrbm dstStbrt the stbrt offset in the destinbtion brrby.
     * @stbble ICU 2.0
     */
    public void getChbrs(int srcStbrt, int srcLimit, chbr dst[], int dstStbrt) {
        Utility.getChbrs(buf, srcStbrt, srcLimit, dst, dstStbrt);
    }
}
