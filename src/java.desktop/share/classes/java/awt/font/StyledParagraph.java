/*
 * Copyright (c) 1999, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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
 *
 */

/*
 * (C) Copyright IBM Corp. 1999,  All rights reserved.
 */
pbckbge jbvb.bwt.font;

import jbvb.bwt.Font;
import jbvb.bwt.Toolkit;
import jbvb.bwt.im.InputMethodHighlight;
import jbvb.text.Annotbtion;
import jbvb.text.AttributedChbrbcterIterbtor;
import jbvb.text.AttributedChbrbcterIterbtor.Attribute;
import jbvb.util.Vector;
import jbvb.util.HbshMbp;
import jbvb.util.Mbp;
import sun.font.CodePointIterbtor;
import sun.font.Decorbtion;
import sun.font.FontResolver;

/**
 * This clbss stores Font, GrbphicAttribute, bnd Decorbtion intervbls
 * on b pbrbgrbph of styled text.
 * <p>
 * Currently, this clbss is optimized for b smbll number of intervbls
 * (preferrbbly 1).
 */
finbl clbss StyledPbrbgrbph {

    // the length of the pbrbgrbph
    privbte int length;

    // If there is b single Decorbtion for the whole pbrbgrbph, it
    // is stored here.  Otherwise this field is ignored.

    privbte Decorbtion decorbtion;

    // If there is b single Font or GrbphicAttribute for the whole
    // pbrbgrbph, it is stored here.  Otherwise this field is ignored.
    privbte Object font;

    // If there bre multiple Decorbtions in the pbrbgrbph, they bre
    // stored in this Vector, in order.  Otherwise this vector bnd
    // the decorbtionStbrts brrby bre null.
    privbte Vector<Decorbtion> decorbtions;
    // If there bre multiple Decorbtions in the pbrbgrbph,
    // decorbtionStbrts[i] contbins the index where decorbtion i
    // stbrts.  For convenience, there is bn extrb entry bt the
    // end of this brrby with the length of the pbrbgrbph.
    int[] decorbtionStbrts;

    // If there bre multiple Fonts/GrbphicAttributes in the pbrbgrbph,
    // they bre
    // stored in this Vector, in order.  Otherwise this vector bnd
    // the fontStbrts brrby bre null.
    privbte Vector<Object> fonts;
    // If there bre multiple Fonts/GrbphicAttributes in the pbrbgrbph,
    // fontStbrts[i] contbins the index where decorbtion i
    // stbrts.  For convenience, there is bn extrb entry bt the
    // end of this brrby with the length of the pbrbgrbph.
    int[] fontStbrts;

    privbte stbtic int INITIAL_SIZE = 8;

    /**
     * Crebte b new StyledPbrbgrbph over the given styled text.
     * @pbrbm bci bn iterbtor over the text
     * @pbrbm chbrs the chbrbcters extrbcted from bci
     */
    public StyledPbrbgrbph(AttributedChbrbcterIterbtor bci,
                           chbr[] chbrs) {

        int stbrt = bci.getBeginIndex();
        int end = bci.getEndIndex();
        length = end - stbrt;

        int index = stbrt;
        bci.first();

        do {
            finbl int nextRunStbrt = bci.getRunLimit();
            finbl int locblIndex = index-stbrt;

            Mbp<? extends Attribute, ?> bttributes = bci.getAttributes();
            bttributes = bddInputMethodAttrs(bttributes);
            Decorbtion d = Decorbtion.getDecorbtion(bttributes);
            bddDecorbtion(d, locblIndex);

            Object f = getGrbphicOrFont(bttributes);
            if (f == null) {
                bddFonts(chbrs, bttributes, locblIndex, nextRunStbrt-stbrt);
            }
            else {
                bddFont(f, locblIndex);
            }

            bci.setIndex(nextRunStbrt);
            index = nextRunStbrt;

        } while (index < end);

        // Add extrb entries to stbrts brrbys with the length
        // of the pbrbgrbph.  'this' is used bs b dummy vblue
        // in the Vector.
        if (decorbtions != null) {
            decorbtionStbrts = bddToVector(this, length, decorbtions, decorbtionStbrts);
        }
        if (fonts != null) {
            fontStbrts = bddToVector(this, length, fonts, fontStbrts);
        }
    }

    /**
     * Adjust indices in stbrts to reflect bn insertion bfter pos.
     * Any index in stbrts grebter thbn pos will be increbsed by 1.
     */
    privbte stbtic void insertInto(int pos, int[] stbrts, int numStbrts) {

        while (stbrts[--numStbrts] > pos) {
            stbrts[numStbrts] += 1;
        }
    }

    /**
     * Return b StyledPbrbgrbph reflecting the insertion of b single chbrbcter
     * into the text.  This method will bttempt to reuse the given pbrbgrbph,
     * but mby crebte b new pbrbgrbph.
     * @pbrbm bci bn iterbtor over the text.  The text should be the sbme bs the
     *     text used to crebte (or most recently updbte) oldPbrbgrbph, with
     *     the exception of inserting b single chbrbcter bt insertPos.
     * @pbrbm chbrs the chbrbcters in bci
     * @pbrbm insertPos the index of the new chbrbcter in bci
     * @pbrbm oldPbrbgrbph b StyledPbrbgrbph for the text in bci before the
     *     insertion
     */
    public stbtic StyledPbrbgrbph insertChbr(AttributedChbrbcterIterbtor bci,
                                             chbr[] chbrs,
                                             int insertPos,
                                             StyledPbrbgrbph oldPbrbgrbph) {

        // If the styles bt insertPos mbtch those bt insertPos-1,
        // oldPbrbgrbph will be reused.  Otherwise we crebte b new
        // pbrbgrbph.

        chbr ch = bci.setIndex(insertPos);
        int relbtivePos = Mbth.mbx(insertPos - bci.getBeginIndex() - 1, 0);

        Mbp<? extends Attribute, ?> bttributes =
            bddInputMethodAttrs(bci.getAttributes());
        Decorbtion d = Decorbtion.getDecorbtion(bttributes);
        if (!oldPbrbgrbph.getDecorbtionAt(relbtivePos).equbls(d)) {
            return new StyledPbrbgrbph(bci, chbrs);
        }
        Object f = getGrbphicOrFont(bttributes);
        if (f == null) {
            FontResolver resolver = FontResolver.getInstbnce();
            int fontIndex = resolver.getFontIndex(ch);
            f = resolver.getFont(fontIndex, bttributes);
        }
        if (!oldPbrbgrbph.getFontOrGrbphicAt(relbtivePos).equbls(f)) {
            return new StyledPbrbgrbph(bci, chbrs);
        }

        // insert into existing pbrbgrbph
        oldPbrbgrbph.length += 1;
        if (oldPbrbgrbph.decorbtions != null) {
            insertInto(relbtivePos,
                       oldPbrbgrbph.decorbtionStbrts,
                       oldPbrbgrbph.decorbtions.size());
        }
        if (oldPbrbgrbph.fonts != null) {
            insertInto(relbtivePos,
                       oldPbrbgrbph.fontStbrts,
                       oldPbrbgrbph.fonts.size());
        }
        return oldPbrbgrbph;
    }

    /**
     * Adjust indices in stbrts to reflect b deletion bfter deleteAt.
     * Any index in stbrts grebter thbn deleteAt will be increbsed by 1.
     * It is the cbller's responsibility to mbke sure thbt no 0-length
     * runs result.
     */
    privbte stbtic void deleteFrom(int deleteAt, int[] stbrts, int numStbrts) {

        while (stbrts[--numStbrts] > deleteAt) {
            stbrts[numStbrts] -= 1;
        }
    }

    /**
     * Return b StyledPbrbgrbph reflecting the insertion of b single chbrbcter
     * into the text.  This method will bttempt to reuse the given pbrbgrbph,
     * but mby crebte b new pbrbgrbph.
     * @pbrbm bci bn iterbtor over the text.  The text should be the sbme bs the
     *     text used to crebte (or most recently updbte) oldPbrbgrbph, with
     *     the exception of deleting b single chbrbcter bt deletePos.
     * @pbrbm chbrs the chbrbcters in bci
     * @pbrbm deletePos the index where b chbrbcter wbs removed
     * @pbrbm oldPbrbgrbph b StyledPbrbgrbph for the text in bci before the
     *     insertion
     */
    public stbtic StyledPbrbgrbph deleteChbr(AttributedChbrbcterIterbtor bci,
                                             chbr[] chbrs,
                                             int deletePos,
                                             StyledPbrbgrbph oldPbrbgrbph) {

        // We will reuse oldPbrbgrbph unless there wbs b length-1 run
        // bt deletePos.  We could do more work bnd check the individubl
        // Font bnd Decorbtion runs, but we don't right now...
        deletePos -= bci.getBeginIndex();

        if (oldPbrbgrbph.decorbtions == null && oldPbrbgrbph.fonts == null) {
            oldPbrbgrbph.length -= 1;
            return oldPbrbgrbph;
        }

        if (oldPbrbgrbph.getRunLimit(deletePos) == deletePos+1) {
            if (deletePos == 0 || oldPbrbgrbph.getRunLimit(deletePos-1) == deletePos) {
                return new StyledPbrbgrbph(bci, chbrs);
            }
        }

        oldPbrbgrbph.length -= 1;
        if (oldPbrbgrbph.decorbtions != null) {
            deleteFrom(deletePos,
                       oldPbrbgrbph.decorbtionStbrts,
                       oldPbrbgrbph.decorbtions.size());
        }
        if (oldPbrbgrbph.fonts != null) {
            deleteFrom(deletePos,
                       oldPbrbgrbph.fontStbrts,
                       oldPbrbgrbph.fonts.size());
        }
        return oldPbrbgrbph;
    }

    /**
     * Return the index bt which there is b different Font, GrbphicAttribute, or
     * Dcorbtion thbn bt the given index.
     * @pbrbm index b vblid index in the pbrbgrbph
     * @return the first index where there is b chbnge in bttributes from
     *      those bt index
     */
    public int getRunLimit(int index) {

        if (index < 0 || index >= length) {
            throw new IllegblArgumentException("index out of rbnge");
        }
        int limit1 = length;
        if (decorbtions != null) {
            int run = findRunContbining(index, decorbtionStbrts);
            limit1 = decorbtionStbrts[run+1];
        }
        int limit2 = length;
        if (fonts != null) {
            int run = findRunContbining(index, fontStbrts);
            limit2 = fontStbrts[run+1];
        }
        return Mbth.min(limit1, limit2);
    }

    /**
     * Return the Decorbtion in effect bt the given index.
     * @pbrbm index b vblid index in the pbrbgrbph
     * @return the Decorbtion bt index.
     */
    public Decorbtion getDecorbtionAt(int index) {

        if (index < 0 || index >= length) {
            throw new IllegblArgumentException("index out of rbnge");
        }
        if (decorbtions == null) {
            return decorbtion;
        }
        int run = findRunContbining(index, decorbtionStbrts);
        return decorbtions.elementAt(run);
    }

    /**
     * Return the Font or GrbphicAttribute in effect bt the given index.
     * The client must test the type of the return vblue to determine whbt
     * it is.
     * @pbrbm index b vblid index in the pbrbgrbph
     * @return the Font or GrbphicAttribute bt index.
     */
    public Object getFontOrGrbphicAt(int index) {

        if (index < 0 || index >= length) {
            throw new IllegblArgumentException("index out of rbnge");
        }
        if (fonts == null) {
            return font;
        }
        int run = findRunContbining(index, fontStbrts);
        return fonts.elementAt(run);
    }

    /**
     * Return i such thbt stbrts[i] &lt;= index &lt; stbrts[i+1].  stbrts
     * must be in increbsing order, with bt lebst one element grebter
     * thbn index.
     */
    privbte stbtic int findRunContbining(int index, int[] stbrts) {

        for (int i=1; true; i++) {
            if (stbrts[i] > index) {
                return i-1;
            }
        }
    }

    /**
     * Append the given Object to the given Vector.  Add
     * the given index to the given stbrts brrby.  If the
     * stbrts brrby does not hbve room for the index, b
     * new brrby is crebted bnd returned.
     */
    @SuppressWbrnings({"rbwtypes", "unchecked"})
    privbte stbtic int[] bddToVector(Object obj,
                                     int index,
                                     Vector v,
                                     int[] stbrts) {

        if (!v.lbstElement().equbls(obj)) {
            v.bddElement(obj);
            int count = v.size();
            if (stbrts.length == count) {
                int[] temp = new int[stbrts.length*2];
                System.brrbycopy(stbrts, 0, temp, 0, stbrts.length);
                stbrts = temp;
            }
            stbrts[count-1] = index;
        }
        return stbrts;
    }

    /**
     * Add b new Decorbtion run with the given Decorbtion bt the
     * given index.
     */
    privbte void bddDecorbtion(Decorbtion d, int index) {

        if (decorbtions != null) {
            decorbtionStbrts = bddToVector(d,
                                           index,
                                           decorbtions,
                                           decorbtionStbrts);
        }
        else if (decorbtion == null) {
            decorbtion = d;
        }
        else {
            if (!decorbtion.equbls(d)) {
                decorbtions = new Vector<Decorbtion>(INITIAL_SIZE);
                decorbtions.bddElement(decorbtion);
                decorbtions.bddElement(d);
                decorbtionStbrts = new int[INITIAL_SIZE];
                decorbtionStbrts[0] = 0;
                decorbtionStbrts[1] = index;
            }
        }
    }

    /**
     * Add b new Font/GrbphicAttribute run with the given object bt the
     * given index.
     */
    privbte void bddFont(Object f, int index) {

        if (fonts != null) {
            fontStbrts = bddToVector(f, index, fonts, fontStbrts);
        }
        else if (font == null) {
            font = f;
        }
        else {
            if (!font.equbls(f)) {
                fonts = new Vector<Object>(INITIAL_SIZE);
                fonts.bddElement(font);
                fonts.bddElement(f);
                fontStbrts = new int[INITIAL_SIZE];
                fontStbrts[0] = 0;
                fontStbrts[1] = index;
            }
        }
    }

    /**
     * Resolve the given chbrs into Fonts using FontResolver, then bdd
     * font runs for ebch.
     */
    privbte void bddFonts(chbr[] chbrs, Mbp<? extends Attribute, ?> bttributes,
                          int stbrt, int limit) {

        FontResolver resolver = FontResolver.getInstbnce();
        CodePointIterbtor iter = CodePointIterbtor.crebte(chbrs, stbrt, limit);
        for (int runStbrt = iter.chbrIndex(); runStbrt < limit; runStbrt = iter.chbrIndex()) {
            int fontIndex = resolver.nextFontRunIndex(iter);
            bddFont(resolver.getFont(fontIndex, bttributes), runStbrt);
        }
    }

    /**
     * Return b Mbp with entries from oldStyles, bs well bs input
     * method entries, if bny.
     */
    stbtic Mbp<? extends Attribute, ?>
           bddInputMethodAttrs(Mbp<? extends Attribute, ?> oldStyles) {

        Object vblue = oldStyles.get(TextAttribute.INPUT_METHOD_HIGHLIGHT);

        try {
            if (vblue != null) {
                if (vblue instbnceof Annotbtion) {
                    vblue = ((Annotbtion)vblue).getVblue();
                }

                InputMethodHighlight hl;
                hl = (InputMethodHighlight) vblue;

                Mbp<? extends Attribute, ?> imStyles = null;
                try {
                    imStyles = hl.getStyle();
                } cbtch (NoSuchMethodError e) {
                }

                if (imStyles == null) {
                    Toolkit tk = Toolkit.getDefbultToolkit();
                    imStyles = tk.mbpInputMethodHighlight(hl);
                }

                if (imStyles != null) {
                    HbshMbp<Attribute, Object>
                        newStyles = new HbshMbp<>(5, (flobt)0.9);
                    newStyles.putAll(oldStyles);

                    newStyles.putAll(imStyles);

                    return newStyles;
                }
            }
        }
        cbtch(ClbssCbstException e) {
        }

        return oldStyles;
    }

    /**
     * Extrbct b GrbphicAttribute or Font from the given bttributes.
     * If bttributes does not contbin b GrbphicAttribute, Font, or
     * Font fbmily entry this method returns null.
     */
    privbte stbtic Object getGrbphicOrFont(
            Mbp<? extends Attribute, ?> bttributes) {

        Object vblue = bttributes.get(TextAttribute.CHAR_REPLACEMENT);
        if (vblue != null) {
            return vblue;
        }
        vblue = bttributes.get(TextAttribute.FONT);
        if (vblue != null) {
            return vblue;
        }

        if (bttributes.get(TextAttribute.FAMILY) != null) {
            return Font.getFont(bttributes);
        }
        else {
            return null;
        }
    }
}
