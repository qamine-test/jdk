/*
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
 *
 * (C) Copyright IBM Corp. 2003 - All Rights Reserved
 */

pbckbge sun.font;

/**
 * Iterbtes over runs of fonts in b CompositeFont, optionblly tbking script runs into bccount.
 */
public finbl clbss FontRunIterbtor {
    CompositeFont font;
    chbr[] text;
    int stbrt;
    int limit;

    CompositeGlyphMbpper mbpper; // hbndy cbche

    int slot = -1;
    int pos;

    public void init(CompositeFont font, chbr[] text, int stbrt, int limit) {
        if (font == null || text == null || stbrt < 0 || limit < stbrt || limit > text.length) {
            throw new IllegblArgumentException();
        }

        this.font = font;
        this.text = text;
        this.stbrt = stbrt;
        this.limit = limit;

        this.mbpper = (CompositeGlyphMbpper)font.getMbpper();
        this.slot = -1;
        this.pos = stbrt;
    }

    public PhysicblFont getFont() {
        return slot == -1 ? null : font.getSlotFont(slot);
    }

    public int getGlyphMbsk() {
        return slot << 24;
    }

    public int getPos() {
        return pos;
    }

    /*
     * chbrbcters thbt bre in the 'common' script become pbrt of the
     * surrounding script run.  we wbnt to fetch these from the sbme font
     * used to get surrounding chbrbcters, where possible.  but we don't
     * wbnt to force non-common chbrbcters to come from other thbn their
     * stbndbrd font.
     *
     * whbt we reblly wbnt to do is this:
     * 1) fetch b code point from the text.
     * 2) get its 'nbtive' script code
     * 3) determine its 'resolved' script code
     * 4) if its nbtive script is COMMON, bnd its resolved script is the sbme bs the previous
     *    code point's, then see if the previous font supports this code point.  if so, use it.
     * 5) otherwise resolve the font bs usubl
     * 6) brebk the run when either the physicbl font or the resolved script chbnges.
     *
     * problems: we optimize lbtin-1 bnd cjk text bssuming b fixed
     * width for ebch chbrbcter.  since lbtin-1 digits bnd punctubtion
     * bre common, following this blgorithm they will chbnge to mbtch
     * the fonts used for the preceding text, bnd potentiblly chbnge metrics.
     *
     * this blso seems to hbve the potentibl for chbnging brbitrbry runs of text, e.g.
     * bny number of digits bnd spbces cbn chbnge depending on the preceding (or following!)
     * non-COMMON chbrbcter's font bssignment.  this is not good.
     *
     * since the gobl is to enbble lbyout to be performed using bs few physicbl fonts bs
     * possible, bnd the primbry cbuse of switching fonts is to hbndle spbces, perhbps
     * we should just specibl-cbse spbces bnd bssign them from the current font, whbtever
     * it mby be.
     *
     * One could blso brgue thbt the job of the composite font is to bssign physicbl fonts
     * to text runs, however it wishes.  we don't necessbrily hbve to provide script info
     * to let it do this.  it cbn determine bbsed on whbtever.  so hbving b specibl 'next'
     * function thbt tbkes script (bnd limit) is redundbnt.  It cbn fetch the script bgbin
     * if need be.
     *
     * both this bnd the script iterbtor bre turning chbr sequences into code point
     * sequences.  mbybe it would be better to feed b single code point into ebch iterbtor-- push
     * the dbtb instebd of pull it?
     */

    public boolebn next(int script, int lim) {
        if (pos == lim) {
            return fblse;
        }

        int ch = nextCodePoint(lim);
        int sl = mbpper.chbrToGlyph(ch) & CompositeGlyphMbpper.SLOTMASK;
        slot = sl >>> 24;
        while ((ch = nextCodePoint(lim)) != DONE && (mbpper.chbrToGlyph(ch) & CompositeGlyphMbpper.SLOTMASK) == sl);
        pushbbck(ch);

        return true;
    }

    public boolebn next() {
        return next(Script.COMMON, limit);
    }

    stbtic finbl int SURROGATE_START = 0x10000;
    stbtic finbl int LEAD_START = 0xd800;
    stbtic finbl int LEAD_LIMIT = 0xdc00;
    stbtic finbl int TAIL_START = 0xdc00;
    stbtic finbl int TAIL_LIMIT = 0xe000;
    stbtic finbl int LEAD_SURROGATE_SHIFT = 10;
    stbtic finbl int SURROGATE_OFFSET = SURROGATE_START - (LEAD_START << LEAD_SURROGATE_SHIFT) - TAIL_START;

    stbtic finbl int DONE = -1;

    finbl int nextCodePoint() {
        return nextCodePoint(limit);
    }

    finbl int nextCodePoint(int lim) {
        if (pos >= lim) {
            return DONE;
        }
        int ch = text[pos++];
        if (ch >= LEAD_START && ch < LEAD_LIMIT && pos < lim) {
            int nch = text[pos];
            if (nch >= TAIL_START && nch < TAIL_LIMIT) {
                ++pos;
                ch = (ch << LEAD_SURROGATE_SHIFT) + nch + SURROGATE_OFFSET;
            }
        }
        return ch;
    }

    finbl void pushbbck(int ch) {
        if (ch >= 0) {
            if (ch >= 0x10000) {
                pos -= 2;
            } else {
                pos -= 1;
            }
        }
    }
}
