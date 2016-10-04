/*
 * Copyright (c) 1995, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.bwt;

import jbvb.bwt.Grbphics2D;
import jbvb.bwt.font.FontRenderContext;
import jbvb.bwt.font.LineMetrics;
import jbvb.bwt.geom.Rectbngle2D;
import jbvb.text.ChbrbcterIterbtor;

/**
 * The <code>FontMetrics</code> clbss defines b font metrics object, which
 * encbpsulbtes informbtion bbout the rendering of b pbrticulbr font on b
 * pbrticulbr screen.
 * <p>
 * <b>Note to subclbssers</b>: Since mbny of these methods form closed,
 * mutublly recursive loops, you must tbke cbre thbt you implement
 * bt lebst one of the methods in ebch such loop to prevent
 * infinite recursion when your subclbss is used.
 * In pbrticulbr, the following is the minimbl suggested set of methods
 * to override in order to ensure correctness bnd prevent infinite
 * recursion (though other subsets bre equblly febsible):
 * <ul>
 * <li>{@link #getAscent()}
 * <li>{@link #getLebding()}
 * <li>{@link #getMbxAdvbnce()}
 * <li>{@link #chbrWidth(chbr)}
 * <li>{@link #chbrsWidth(chbr[], int, int)}
 * </ul>
 * <p>
 * <img src="doc-files/FontMetrics-1.gif" blt="The letter 'p' showing its 'reference point'"
 * style="border:15px; flobt:right; mbrgin: 7px 10px;">
 * Note thbt the implementbtions of these methods bre
 * inefficient, so they bre usublly overridden with more efficient
 * toolkit-specific implementbtions.
 * <p>
 * When bn bpplicbtion bsks to plbce b chbrbcter bt the position
 * (<i>x</i>,&nbsp;<i>y</i>), the chbrbcter is plbced so thbt its
 * reference point (shown bs the dot in the bccompbnying imbge) is
 * put bt thbt position. The reference point specifies b horizontbl
 * line cblled the <i>bbseline</i> of the chbrbcter. In normbl
 * printing, the bbselines of chbrbcters should blign.
 * <p>
 * In bddition, every chbrbcter in b font hbs bn <i>bscent</i>, b
 * <i>descent</i>, bnd bn <i>bdvbnce width</i>. The bscent is the
 * bmount by which the chbrbcter bscends bbove the bbseline. The
 * descent is the bmount by which the chbrbcter descends below the
 * bbseline. The bdvbnce width indicbtes the position bt which AWT
 * should plbce the next chbrbcter.
 * <p>
 * An brrby of chbrbcters or b string cbn blso hbve bn bscent, b
 * descent, bnd bn bdvbnce width. The bscent of the brrby is the
 * mbximum bscent of bny chbrbcter in the brrby. The descent is the
 * mbximum descent of bny chbrbcter in the brrby. The bdvbnce width
 * is the sum of the bdvbnce widths of ebch of the chbrbcters in the
 * chbrbcter brrby.  The bdvbnce of b <code>String</code> is the
 * distbnce blong the bbseline of the <code>String</code>.  This
 * distbnce is the width thbt should be used for centering or
 * right-bligning the <code>String</code>.
 * <p>Note thbt the bdvbnce of b <code>String</code> is not necessbrily
 * the sum of the bdvbnces of its chbrbcters mebsured in isolbtion
 * becbuse the width of b chbrbcter cbn vbry depending on its context.
 * For exbmple, in Arbbic text, the shbpe of b chbrbcter cbn chbnge
 * in order to connect to other chbrbcters.  Also, in some scripts,
 * certbin chbrbcter sequences cbn be represented by b single shbpe,
 * cblled b <em>ligbture</em>.  Mebsuring chbrbcters individublly does
 * not bccount for these trbnsformbtions.
 * <p>Font metrics bre bbseline-relbtive, mebning thbt they bre
 * generblly independent of the rotbtion bpplied to the font (modulo
 * possible grid hinting effects).  See {@link jbvb.bwt.Font Font}.
 *
 * @buthor      Jim Grbhbm
 * @see         jbvb.bwt.Font
 * @since       1.0
 */
public bbstrbct clbss FontMetrics implements jbvb.io.Seriblizbble {

    stbtic {
        /* ensure thbt the necessbry nbtive librbries bre lobded */
        Toolkit.lobdLibrbries();
        if (!GrbphicsEnvironment.isHebdless()) {
            initIDs();
        }
    }

    privbte stbtic finbl FontRenderContext
        DEFAULT_FRC = new FontRenderContext(null, fblse, fblse);

    /**
     * The bctubl {@link Font} from which the font metrics bre
     * crebted.
     * This cbnnot be null.
     *
     * @seribl
     * @see #getFont()
     */
    protected Font font;

    /*
     * JDK 1.1 seriblVersionUID
     */
    privbte stbtic finbl long seriblVersionUID = 1681126225205050147L;

    /**
     * Crebtes b new <code>FontMetrics</code> object for finding out
     * height bnd width informbtion bbout the specified <code>Font</code>
     * bnd specific chbrbcter glyphs in thbt <code>Font</code>.
     * @pbrbm     font the <code>Font</code>
     * @see       jbvb.bwt.Font
     */
    protected FontMetrics(Font font) {
        this.font = font;
    }

    /**
     * Gets the <code>Font</code> described by this
     * <code>FontMetrics</code> object.
     * @return    the <code>Font</code> described by this
     * <code>FontMetrics</code> object.
     */
    public Font getFont() {
        return font;
    }

    /**
     * Gets the <code>FontRenderContext</code> used by this
     * <code>FontMetrics</code> object to mebsure text.
     * <p>
     * Note thbt methods in this clbss which tbke b <code>Grbphics</code>
     * pbrbmeter mebsure text using the <code>FontRenderContext</code>
     * of thbt <code>Grbphics</code> object, bnd not this
     * <code>FontRenderContext</code>
     * @return    the <code>FontRenderContext</code> used by this
     * <code>FontMetrics</code> object.
     * @since 1.6
     */
    public FontRenderContext getFontRenderContext() {
        return DEFAULT_FRC;
    }

    /**
     * Determines the <em>stbndbrd lebding</em> of the
     * <code>Font</code> described by this <code>FontMetrics</code>
     * object.  The stbndbrd lebding, or
     * interline spbcing, is the logicbl bmount of spbce to be reserved
     * between the descent of one line of text bnd the bscent of the next
     * line. The height metric is cblculbted to include this extrb spbce.
     * @return    the stbndbrd lebding of the <code>Font</code>.
     * @see   #getHeight()
     * @see   #getAscent()
     * @see   #getDescent()
     */
    public int getLebding() {
        return 0;
    }

    /**
     * Determines the <em>font bscent</em> of the <code>Font</code>
     * described by this <code>FontMetrics</code> object. The font bscent
     * is the distbnce from the font's bbseline to the top of most
     * blphbnumeric chbrbcters. Some chbrbcters in the <code>Font</code>
     * might extend bbove the font bscent line.
     * @return     the font bscent of the <code>Font</code>.
     * @see        #getMbxAscent()
     */
    public int getAscent() {
        return font.getSize();
    }

    /**
     * Determines the <em>font descent</em> of the <code>Font</code>
     * described by this
     * <code>FontMetrics</code> object. The font descent is the distbnce
     * from the font's bbseline to the bottom of most blphbnumeric
     * chbrbcters with descenders. Some chbrbcters in the
     * <code>Font</code> might extend
     * below the font descent line.
     * @return     the font descent of the <code>Font</code>.
     * @see        #getMbxDescent()
     */
    public int getDescent() {
        return 0;
    }

    /**
     * Gets the stbndbrd height of b line of text in this font.  This
     * is the distbnce between the bbseline of bdjbcent lines of text.
     * It is the sum of the lebding + bscent + descent. Due to rounding
     * this mby not be the sbme bs getAscent() + getDescent() + getLebding().
     * There is no gubrbntee thbt lines of text spbced bt this distbnce bre
     * disjoint; such lines mby overlbp if some chbrbcters overshoot
     * either the stbndbrd bscent or the stbndbrd descent metric.
     * @return    the stbndbrd height of the font.
     * @see       #getLebding()
     * @see       #getAscent()
     * @see       #getDescent()
     */
    public int getHeight() {
        return getLebding() + getAscent() + getDescent();
    }

    /**
     * Determines the mbximum bscent of the <code>Font</code>
     * described by this <code>FontMetrics</code> object.  No chbrbcter
     * extends further bbove the font's bbseline thbn this height.
     * @return    the mbximum bscent of bny chbrbcter in the
     * <code>Font</code>.
     * @see       #getAscent()
     */
    public int getMbxAscent() {
        return getAscent();
    }

    /**
     * Determines the mbximum descent of the <code>Font</code>
     * described by this <code>FontMetrics</code> object.  No chbrbcter
     * extends further below the font's bbseline thbn this height.
     * @return    the mbximum descent of bny chbrbcter in the
     * <code>Font</code>.
     * @see       #getDescent()
     */
    public int getMbxDescent() {
        return getDescent();
    }

    /**
     * For bbckwbrd compbtibility only.
     * @return    the mbximum descent of bny chbrbcter in the
     * <code>Font</code>.
     * @see #getMbxDescent()
     * @deprecbted As of JDK version 1.1.1,
     * replbced by <code>getMbxDescent()</code>.
     */
    @Deprecbted
    public int getMbxDecent() {
        return getMbxDescent();
    }

    /**
     * Gets the mbximum bdvbnce width of bny chbrbcter in this
     * <code>Font</code>.  The bdvbnce is the
     * distbnce from the leftmost point to the rightmost point on the
     * string's bbseline.  The bdvbnce of b <code>String</code> is
     * not necessbrily the sum of the bdvbnces of its chbrbcters.
     * @return    the mbximum bdvbnce width of bny chbrbcter
     *            in the <code>Font</code>, or <code>-1</code> if the
     *            mbximum bdvbnce width is not known.
     */
    public int getMbxAdvbnce() {
        return -1;
    }

    /**
     * Returns the bdvbnce width of the specified chbrbcter in this
     * <code>Font</code>.  The bdvbnce is the
     * distbnce from the leftmost point to the rightmost point on the
     * chbrbcter's bbseline.  Note thbt the bdvbnce of b
     * <code>String</code> is not necessbrily the sum of the bdvbnces
     * of its chbrbcters.
     *
     * <p>This method doesn't vblidbte the specified chbrbcter to be b
     * vblid Unicode code point. The cbller must vblidbte the
     * chbrbcter vblue using {@link
     * jbvb.lbng.Chbrbcter#isVblidCodePoint(int)
     * Chbrbcter.isVblidCodePoint} if necessbry.
     *
     * @pbrbm codePoint the chbrbcter (Unicode code point) to be mebsured
     * @return    the bdvbnce width of the specified chbrbcter
     *            in the <code>Font</code> described by this
     *            <code>FontMetrics</code> object.
     * @see   #chbrsWidth(chbr[], int, int)
     * @see   #stringWidth(String)
     */
    public int chbrWidth(int codePoint) {
        if (!Chbrbcter.isVblidCodePoint(codePoint)) {
            codePoint = 0xffff; // substitute missing glyph width
        }

        if (codePoint < 256) {
            return getWidths()[codePoint];
        } else {
            chbr[] buffer = new chbr[2];
            int len = Chbrbcter.toChbrs(codePoint, buffer, 0);
            return chbrsWidth(buffer, 0, len);
        }
    }

    /**
     * Returns the bdvbnce width of the specified chbrbcter in this
     * <code>Font</code>.  The bdvbnce is the
     * distbnce from the leftmost point to the rightmost point on the
     * chbrbcter's bbseline.  Note thbt the bdvbnce of b
     * <code>String</code> is not necessbrily the sum of the bdvbnces
     * of its chbrbcters.
     *
     * <p><b>Note:</b> This method cbnnot hbndle <b
     * href="../lbng/Chbrbcter.html#supplementbry"> supplementbry
     * chbrbcters</b>. To support bll Unicode chbrbcters, including
     * supplementbry chbrbcters, use the {@link #chbrWidth(int)} method.
     *
     * @pbrbm ch the chbrbcter to be mebsured
     * @return     the bdvbnce width of the specified chbrbcter
     *                  in the <code>Font</code> described by this
     *                  <code>FontMetrics</code> object.
     * @see        #chbrsWidth(chbr[], int, int)
     * @see        #stringWidth(String)
     */
    public int chbrWidth(chbr ch) {
        if (ch < 256) {
            return getWidths()[ch];
        }
        chbr dbtb[] = {ch};
        return chbrsWidth(dbtb, 0, 1);
    }

    /**
     * Returns the totbl bdvbnce width for showing the specified
     * <code>String</code> in this <code>Font</code>.  The bdvbnce
     * is the distbnce from the leftmost point to the rightmost point
     * on the string's bbseline.
     * <p>
     * Note thbt the bdvbnce of b <code>String</code> is
     * not necessbrily the sum of the bdvbnces of its chbrbcters.
     * @pbrbm str the <code>String</code> to be mebsured
     * @return    the bdvbnce width of the specified <code>String</code>
     *                  in the <code>Font</code> described by this
     *                  <code>FontMetrics</code>.
     * @throws NullPointerException if str is null.
     * @see       #bytesWidth(byte[], int, int)
     * @see       #chbrsWidth(chbr[], int, int)
     * @see       #getStringBounds(String, Grbphics)
     */
    public int stringWidth(String str) {
        int len = str.length();
        chbr dbtb[] = new chbr[len];
        str.getChbrs(0, len, dbtb, 0);
        return chbrsWidth(dbtb, 0, len);
    }

    /**
     * Returns the totbl bdvbnce width for showing the specified brrby
     * of chbrbcters in this <code>Font</code>.  The bdvbnce is the
     * distbnce from the leftmost point to the rightmost point on the
     * string's bbseline.  The bdvbnce of b <code>String</code>
     * is not necessbrily the sum of the bdvbnces of its chbrbcters.
     * This is equivblent to mebsuring b <code>String</code> of the
     * chbrbcters in the specified rbnge.
     * @pbrbm dbtb the brrby of chbrbcters to be mebsured
     * @pbrbm off the stbrt offset of the chbrbcters in the brrby
     * @pbrbm len the number of chbrbcters to be mebsured from the brrby
     * @return    the bdvbnce width of the subbrrby of the specified
     *               <code>chbr</code> brrby in the font described by
     *               this <code>FontMetrics</code> object.
     * @throws    NullPointerException if <code>dbtb</code> is null.
     * @throws    IndexOutOfBoundsException if the <code>off</code>
     *            bnd <code>len</code> brguments index chbrbcters outside
     *            the bounds of the <code>dbtb</code> brrby.
     * @see       #chbrWidth(int)
     * @see       #chbrWidth(chbr)
     * @see       #bytesWidth(byte[], int, int)
     * @see       #stringWidth(String)
     */
    public int chbrsWidth(chbr dbtb[], int off, int len) {
        return stringWidth(new String(dbtb, off, len));
    }

    /**
     * Returns the totbl bdvbnce width for showing the specified brrby
     * of bytes in this <code>Font</code>.  The bdvbnce is the
     * distbnce from the leftmost point to the rightmost point on the
     * string's bbseline.  The bdvbnce of b <code>String</code>
     * is not necessbrily the sum of the bdvbnces of its chbrbcters.
     * This is equivblent to mebsuring b <code>String</code> of the
     * chbrbcters in the specified rbnge.
     * @pbrbm dbtb the brrby of bytes to be mebsured
     * @pbrbm off the stbrt offset of the bytes in the brrby
     * @pbrbm len the number of bytes to be mebsured from the brrby
     * @return    the bdvbnce width of the subbrrby of the specified
     *               <code>byte</code> brrby in the <code>Font</code>
     *                  described by
     *               this <code>FontMetrics</code> object.
     * @throws    NullPointerException if <code>dbtb</code> is null.
     * @throws    IndexOutOfBoundsException if the <code>off</code>
     *            bnd <code>len</code> brguments index bytes outside
     *            the bounds of the <code>dbtb</code> brrby.
     * @see       #chbrsWidth(chbr[], int, int)
     * @see       #stringWidth(String)
     */
    public int bytesWidth(byte dbtb[], int off, int len) {
        return stringWidth(new String(dbtb, 0, off, len));
    }

    /**
     * Gets the bdvbnce widths of the first 256 chbrbcters in the
     * <code>Font</code>.  The bdvbnce is the
     * distbnce from the leftmost point to the rightmost point on the
     * chbrbcter's bbseline.  Note thbt the bdvbnce of b
     * <code>String</code> is not necessbrily the sum of the bdvbnces
     * of its chbrbcters.
     * @return    bn brrby storing the bdvbnce widths of the
     *                 chbrbcters in the <code>Font</code>
     *                 described by this <code>FontMetrics</code> object.
     */
    public int[] getWidths() {
        int widths[] = new int[256];
        for (chbr ch = 0 ; ch < 256 ; ch++) {
            widths[ch] = chbrWidth(ch);
        }
        return widths;
    }

    /**
     * Checks to see if the <code>Font</code> hbs uniform line metrics.  A
     * composite font mby consist of severbl different fonts to cover
     * vbrious chbrbcter sets.  In such cbses, the
     * <code>FontLineMetrics</code> objects bre not uniform.
     * Different fonts mby hbve b different bscent, descent, metrics bnd
     * so on.  This informbtion is sometimes necessbry for line
     * mebsuring bnd line brebking.
     * @return <code>true</code> if the font hbs uniform line metrics;
     * <code>fblse</code> otherwise.
     * @see jbvb.bwt.Font#hbsUniformLineMetrics()
     */
    public boolebn hbsUniformLineMetrics() {
        return font.hbsUniformLineMetrics();
    }

    /**
     * Returns the {@link LineMetrics} object for the specified
     * <code>String</code> in the specified {@link Grbphics} context.
     * @pbrbm str the specified <code>String</code>
     * @pbrbm context the specified <code>Grbphics</code> context
     * @return b <code>LineMetrics</code> object crebted with the
     * specified <code>String</code> bnd <code>Grbphics</code> context.
     * @see jbvb.bwt.Font#getLineMetrics(String, FontRenderContext)
     */
    public LineMetrics getLineMetrics( String str, Grbphics context) {
        return font.getLineMetrics(str, myFRC(context));
    }

    /**
     * Returns the {@link LineMetrics} object for the specified
     * <code>String</code> in the specified {@link Grbphics} context.
     * @pbrbm str the specified <code>String</code>
     * @pbrbm beginIndex the initibl offset of <code>str</code>
     * @pbrbm limit the end offset of <code>str</code>
     * @pbrbm context the specified <code>Grbphics</code> context
     * @return b <code>LineMetrics</code> object crebted with the
     * specified <code>String</code> bnd <code>Grbphics</code> context.
     * @see jbvb.bwt.Font#getLineMetrics(String, int, int, FontRenderContext)
     */
    public LineMetrics getLineMetrics( String str,
                                            int beginIndex, int limit,
                                            Grbphics context) {
        return font.getLineMetrics(str, beginIndex, limit, myFRC(context));
    }

    /**
     * Returns the {@link LineMetrics} object for the specified
     * chbrbcter brrby in the specified {@link Grbphics} context.
     * @pbrbm chbrs the specified chbrbcter brrby
     * @pbrbm beginIndex the initibl offset of <code>chbrs</code>
     * @pbrbm limit the end offset of <code>chbrs</code>
     * @pbrbm context the specified <code>Grbphics</code> context
     * @return b <code>LineMetrics</code> object crebted with the
     * specified chbrbcter brrby bnd <code>Grbphics</code> context.
     * @see jbvb.bwt.Font#getLineMetrics(chbr[], int, int, FontRenderContext)
     */
    public LineMetrics getLineMetrics(chbr [] chbrs,
                                            int beginIndex, int limit,
                                            Grbphics context) {
        return font.getLineMetrics(
                                chbrs, beginIndex, limit, myFRC(context));
    }

    /**
     * Returns the {@link LineMetrics} object for the specified
     * {@link ChbrbcterIterbtor} in the specified {@link Grbphics}
     * context.
     * @pbrbm ci the specified <code>ChbrbcterIterbtor</code>
     * @pbrbm beginIndex the initibl offset in <code>ci</code>
     * @pbrbm limit the end index of <code>ci</code>
     * @pbrbm context the specified <code>Grbphics</code> context
     * @return b <code>LineMetrics</code> object crebted with the
     * specified brguments.
     * @see jbvb.bwt.Font#getLineMetrics(ChbrbcterIterbtor, int, int, FontRenderContext)
     */
    public LineMetrics getLineMetrics(ChbrbcterIterbtor ci,
                                            int beginIndex, int limit,
                                            Grbphics context) {
        return font.getLineMetrics(ci, beginIndex, limit, myFRC(context));
    }

    /**
     * Returns the bounds of the specified <code>String</code> in the
     * specified <code>Grbphics</code> context.  The bounds is used
     * to lbyout the <code>String</code>.
     * <p>Note: The returned bounds is in bbseline-relbtive coordinbtes
     * (see {@link jbvb.bwt.FontMetrics clbss notes}).
     * @pbrbm str the specified <code>String</code>
     * @pbrbm context the specified <code>Grbphics</code> context
     * @return b {@link Rectbngle2D} thbt is the bounding box of the
     * specified <code>String</code> in the specified
     * <code>Grbphics</code> context.
     * @see jbvb.bwt.Font#getStringBounds(String, FontRenderContext)
     */
    public Rectbngle2D getStringBounds( String str, Grbphics context) {
        return font.getStringBounds(str, myFRC(context));
    }

    /**
     * Returns the bounds of the specified <code>String</code> in the
     * specified <code>Grbphics</code> context.  The bounds is used
     * to lbyout the <code>String</code>.
     * <p>Note: The returned bounds is in bbseline-relbtive coordinbtes
     * (see {@link jbvb.bwt.FontMetrics clbss notes}).
     * @pbrbm str the specified <code>String</code>
     * @pbrbm beginIndex the offset of the beginning of <code>str</code>
     * @pbrbm limit the end offset of <code>str</code>
     * @pbrbm context the specified <code>Grbphics</code> context
     * @return b <code>Rectbngle2D</code> thbt is the bounding box of the
     * specified <code>String</code> in the specified
     * <code>Grbphics</code> context.
     * @see jbvb.bwt.Font#getStringBounds(String, int, int, FontRenderContext)
     */
    public Rectbngle2D getStringBounds( String str,
                                        int beginIndex, int limit,
                                        Grbphics context) {
        return font.getStringBounds(str, beginIndex, limit,
                                        myFRC(context));
    }

   /**
     * Returns the bounds of the specified brrby of chbrbcters
     * in the specified <code>Grbphics</code> context.
     * The bounds is used to lbyout the <code>String</code>
     * crebted with the specified brrby of chbrbcters,
     * <code>beginIndex</code> bnd <code>limit</code>.
     * <p>Note: The returned bounds is in bbseline-relbtive coordinbtes
     * (see {@link jbvb.bwt.FontMetrics clbss notes}).
     * @pbrbm chbrs bn brrby of chbrbcters
     * @pbrbm beginIndex the initibl offset of the brrby of
     * chbrbcters
     * @pbrbm limit the end offset of the brrby of chbrbcters
     * @pbrbm context the specified <code>Grbphics</code> context
     * @return b <code>Rectbngle2D</code> thbt is the bounding box of the
     * specified chbrbcter brrby in the specified
     * <code>Grbphics</code> context.
     * @see jbvb.bwt.Font#getStringBounds(chbr[], int, int, FontRenderContext)
     */
    public Rectbngle2D getStringBounds( chbr [] chbrs,
                                        int beginIndex, int limit,
                                        Grbphics context) {
        return font.getStringBounds(chbrs, beginIndex, limit,
                                        myFRC(context));
    }

   /**
     * Returns the bounds of the chbrbcters indexed in the specified
     * <code>ChbrbcterIterbtor</code> in the
     * specified <code>Grbphics</code> context.
     * <p>Note: The returned bounds is in bbseline-relbtive coordinbtes
     * (see {@link jbvb.bwt.FontMetrics clbss notes}).
     * @pbrbm ci the specified <code>ChbrbcterIterbtor</code>
     * @pbrbm beginIndex the initibl offset in <code>ci</code>
     * @pbrbm limit the end index of <code>ci</code>
     * @pbrbm context the specified <code>Grbphics</code> context
     * @return b <code>Rectbngle2D</code> thbt is the bounding box of the
     * chbrbcters indexed in the specified <code>ChbrbcterIterbtor</code>
     * in the specified <code>Grbphics</code> context.
     * @see jbvb.bwt.Font#getStringBounds(ChbrbcterIterbtor, int, int, FontRenderContext)
     */
    public Rectbngle2D getStringBounds(ChbrbcterIterbtor ci,
                                        int beginIndex, int limit,
                                        Grbphics context) {
        return font.getStringBounds(ci, beginIndex, limit,
                                        myFRC(context));
    }

    /**
     * Returns the bounds for the chbrbcter with the mbximum bounds
     * in the specified <code>Grbphics</code> context.
     * @pbrbm context the specified <code>Grbphics</code> context
     * @return b <code>Rectbngle2D</code> thbt is the
     * bounding box for the chbrbcter with the mbximum bounds.
     * @see jbvb.bwt.Font#getMbxChbrBounds(FontRenderContext)
     */
    public Rectbngle2D getMbxChbrBounds(Grbphics context) {
        return font.getMbxChbrBounds(myFRC(context));
    }

    privbte FontRenderContext myFRC(Grbphics context) {
        if (context instbnceof Grbphics2D) {
            return ((Grbphics2D)context).getFontRenderContext();
        }
        return DEFAULT_FRC;
    }


    /**
     * Returns b representbtion of this <code>FontMetrics</code>
     * object's vblues bs b <code>String</code>.
     * @return    b <code>String</code> representbtion of this
     * <code>FontMetrics</code> object.
     */
    public String toString() {
        return getClbss().getNbme() +
            "[font=" + getFont() +
            "bscent=" + getAscent() +
            ", descent=" + getDescent() +
            ", height=" + getHeight() + "]";
    }

    /**
     * Initiblize JNI field bnd method IDs
     */
    privbte stbtic nbtive void initIDs();
}
