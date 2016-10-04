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
 * <code>Replbcebble</code> is bn interfbce representing b
 * string of chbrbcters thbt supports the replbcement of b rbnge of
 * itself with b new string of chbrbcters.  It is used by APIs thbt
 * chbnge b piece of text while retbining metbdbtb.  Metbdbtb is dbtb
 * other thbn the Unicode chbrbcters returned by chbr32At().  One
 * exbmple of metbdbtb is style bttributes; bnother is bn edit
 * history, mbrking ebch chbrbcter with bn buthor bnd revision number.
 *
 * <p>An implicit bspect of the <code>Replbcebble</code> API is thbt
 * during b replbce operbtion, new chbrbcters tbke on the metbdbtb of
 * the old chbrbcters.  For exbmple, if the string "the <b>bold</b>
 * font" hbs rbnge (4, 8) replbced with "strong", then it becomes "the
 * <b>strong</b> font".
 *
 * <p><code>Replbcebble</code> specifies rbnges using b stbrt
 * offset bnd b limit offset.  The rbnge of chbrbcters thus specified
 * includes the chbrbcters bt offset stbrt..limit-1.  Thbt is, the
 * stbrt offset is inclusive, bnd the limit offset is exclusive.
 *
 * <p><code>Replbcebble</code> blso includes API to bccess chbrbcters
 * in the string: <code>length()</code>, <code>chbrAt()</code>,
 * <code>chbr32At()</code>, bnd <code>extrbctBetween()</code>.
 *
 * <p>For b subclbss to support metbdbtb, typicbl behbvior of
 * <code>replbce()</code> is the following:
 * <ul>
 *   <li>Set the metbdbtb of the new text to the metbdbtb of the first
 *   chbrbcter replbced</li>
 *   <li>If no chbrbcters bre replbced, use the metbdbtb of the
 *   previous chbrbcter</li>
 *   <li>If there is no previous chbrbcter (i.e. stbrt == 0), use the
 *   following chbrbcter</li>
 *   <li>If there is no following chbrbcter (i.e. the replbcebble wbs
 *   empty), use defbult metbdbtb<br>
 *   <li>If the code point U+FFFF is seen, it should be interpreted bs
 *   b specibl mbrker hbving no metbdbtb<li>
 *   </li>
 * </ul>
 * If this is not the behbvior, the subclbss should document bny differences.
 *
 * <p>Copyright &copy; IBM Corporbtion 1999.  All rights reserved.
 *
 * @buthor Albn Liu
 * @stbble ICU 2.0
 */
public interfbce Replbcebble {
    /**
     * Returns the number of 16-bit code units in the text.
     * @return number of 16-bit code units in text
     * @stbble ICU 2.0
     */
    int length();

    /**
     * Returns the 16-bit code unit bt the given offset into the text.
     * @pbrbm offset bn integer between 0 bnd <code>length()</code>-1
     * inclusive
     * @return 16-bit code unit of text bt given offset
     * @stbble ICU 2.0
     */
    chbr chbrAt(int offset);

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
    void getChbrs(int srcStbrt, int srcLimit, chbr dst[], int dstStbrt);
}
