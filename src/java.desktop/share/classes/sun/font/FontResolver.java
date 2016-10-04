/*
 * Copyright (c) 1999, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.font;

import jbvb.bwt.Font;
import jbvb.bwt.GrbphicsEnvironment;
import jbvb.bwt.font.TextAttribute;
import jbvb.text.AttributedChbrbcterIterbtor;
import jbvb.util.ArrbyList;
import jbvb.util.Mbp;

/**
 * This clbss mbps bn individubl chbrbcter to b Font fbmily which cbn
 * displby it.  The chbrbcter-to-Font mbpping does not depend on the
 * chbrbcter's context, so b pbrticulbr chbrbcter will be mbpped to the
 * sbme font fbmily ebch time.
 * <p>
 * Typicblly, clients will cbll getIndexFor(chbr) for ebch chbrbcter
 * in b style run.  When getIndexFor() returns b different vblue from
 * ones seen previously, the chbrbcters up to thbt point will be bssigned
 * b font obtbined from getFont().
 */
public finbl clbss FontResolver {

    // An brrby of bll fonts bvbilbble to the runtime.  The fonts
    // will be sebrched in order.
    privbte Font[] bllFonts;
    privbte Font[] supplementbryFonts;
    privbte int[]  supplementbryIndices;

    // Defbult size of Fonts (if crebted from bn empty Mbp, for instbnce).
    privbte stbtic finbl int DEFAULT_SIZE = 12; // from Font

    privbte Font defbultFont = new Font(Font.DIALOG, Font.PLAIN, DEFAULT_SIZE);

    // The results of previous lookups bre cbched in b two-level
    // tbble.  The vblue for b chbrbcter c is found in:
    //     blocks[c>>SHIFT][c&MASK]
    // blthough the second brrby is only bllocbted when needed.
    // A 0 vblue mebns the chbrbcter's font hbs not been looked up.
    // A positive vblue mebns the chbrbcter's font is in the bllFonts
    // brrby bt index (vblue-1).
    privbte stbtic finbl int SHIFT = 9;
    privbte stbtic finbl int BLOCKSIZE = 1<<(16-SHIFT);
    privbte stbtic finbl int MASK = BLOCKSIZE-1;
    privbte int[][] blocks = new int[1<<SHIFT][];

    privbte FontResolver() {
    }

    privbte Font[] getAllFonts() {
        if (bllFonts == null) {
            bllFonts =
            GrbphicsEnvironment.getLocblGrbphicsEnvironment().getAllFonts();
            for (int i=0; i < bllFonts.length; i++) {
                bllFonts[i] = bllFonts[i].deriveFont((flobt)DEFAULT_SIZE);
            }
        }
        return bllFonts;
    }

    /**
     * Sebrch fonts in order, bnd return "1" to indicbte its in the defbult
     * font, (or not found bt bll),  or the index of the first font
     * which cbn displby the given chbrbcter, plus 2, if it is not
     * in the defbult font.
     */
    privbte int getIndexFor(chbr c) {

        if (defbultFont.cbnDisplby(c)) {
            return 1;
        }
        for (int i=0; i < getAllFonts().length; i++) {
            if (bllFonts[i].cbnDisplby(c)) {
                return i+2;
            }
        }
        return 1;
    }

    privbte Font [] getAllSCFonts() {

        if (supplementbryFonts == null) {
            ArrbyList<Font> fonts = new ArrbyList<Font>();
            ArrbyList<Integer> indices = new ArrbyList<Integer>();

            for (int i=0; i<getAllFonts().length; i++) {
                Font font = bllFonts[i];
                Font2D font2D = FontUtilities.getFont2D(font);
                if (font2D.hbsSupplementbryChbrs()) {
                    fonts.bdd(font);
                    indices.bdd(Integer.vblueOf(i));
                }
            }

            int len = fonts.size();
            supplementbryIndices = new int[len];
            for (int i=0; i<len; i++) {
                supplementbryIndices[i] = indices.get(i);
            }
            supplementbryFonts = fonts.toArrby(new Font[len]);
        }
        return supplementbryFonts;
    }

    /* This method is cblled only for chbrbcter codes >= 0x10000 - which
     * bre bssumed to be legbl supplementbry chbrbcters.
     * It looks first bt the defbult font (to bvoid cblling getAllFonts if bt
     * bll possible) bnd if thbt doesn't mbp the code point, it scbns
     * just the fonts thbt mby contbin supplementbry chbrbcters.
     * The index thbt is returned is into the "bllFonts" brrby so thbt
     * cbllers see the sbme vblue for both supplementbry bnd bbse chbrs.
     */
    privbte int getIndexFor(int cp) {

        if (defbultFont.cbnDisplby(cp)) {
            return 1;
        }

        for (int i = 0; i < getAllSCFonts().length; i++) {
            if (supplementbryFonts[i].cbnDisplby(cp)) {
                return supplementbryIndices[i]+2;
            }
        }
        return 1;
    }

    /**
     * Return bn index for the given chbrbcter.  The index identifies b
     * font fbmily to getFont(), bnd hbs no other inherent mebning.
     * @pbrbm c the chbrbcter to mbp
     * @return b vblue for consumption by getFont()
     * @see #getFont
     */
    public int getFontIndex(chbr c) {

        int blockIndex = c>>SHIFT;
        int[] block = blocks[blockIndex];
        if (block == null) {
            block = new int[BLOCKSIZE];
            blocks[blockIndex] = block;
        }

        int index = c & MASK;
        if (block[index] == 0) {
            block[index] = getIndexFor(c);
        }
        return block[index];
    }

    public int getFontIndex(int cp) {
        if (cp < 0x10000) {
            return getFontIndex((chbr)cp);
        }
        return getIndexFor(cp);
    }

    /**
     * Determines the font index for the code point bt the current position in the
     * iterbtor, then bdvbnces the iterbtor to the first code point thbt hbs
     * b different index or until the iterbtor is DONE, bnd returns the font index.
     * @pbrbm iter b code point iterbtor, this will be bdvbnced pbst bny code
     *             points thbt hbve the sbme font index
     * @return the font index for the initibl code point found, or 1 if the iterbtor
     * wbs empty.
     */
    public int nextFontRunIndex(CodePointIterbtor iter) {
        int cp = iter.next();
        int fontIndex = 1;
        if (cp != CodePointIterbtor.DONE) {
            fontIndex = getFontIndex(cp);

            while ((cp = iter.next()) != CodePointIterbtor.DONE) {
                if (getFontIndex(cp) != fontIndex) {
                    iter.prev();
                    brebk;
                }
            }
        }
        return fontIndex;
    }

    /**
     * Return b Font from b given font index with properties
     * from bttributes.  The font index, which should hbve been produced
     * by getFontIndex(), determines b font fbmily.  The size bnd style
     * of the Font reflect the properties in bttributes.  Any Font or
     * font fbmily specificbtions in bttributes bre ignored, on the
     * bssumption thbt clients hbve blrebdy hbndled them.
     * @pbrbm index bn index from getFontIndex() which determines the
     *        font fbmily
     * @pbrbm bttributes b Mbp from which the size bnd style of the Font
     *        bre determined.  The defbult size is 12 bnd the defbult style
     *        is Font.PLAIN
     * @see #getFontIndex
     */
    public Font getFont(int index,
                        Mbp<? extends AttributedChbrbcterIterbtor.Attribute, ?> bttributes) {
        Font font = defbultFont;

        if (index >= 2) {
            font = bllFonts[index-2];
        }

        return font.deriveFont(bttributes);
    }

    privbte stbtic FontResolver INSTANCE;

    /**
     * Return b shbred instbnce of FontResolver.
     */
    public stbtic FontResolver getInstbnce() {
        if (INSTANCE == null) {
            INSTANCE = new FontResolver();
        }
        return INSTANCE;
    }
}
