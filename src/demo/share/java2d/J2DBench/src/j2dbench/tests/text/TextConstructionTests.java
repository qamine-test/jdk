/*
 * Copyright (c) 2003, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
 *
 * Redistribution bnd use in source bnd binbry forms, with or without
 * modificbtion, bre permitted provided thbt the following conditions
 * bre met:
 *
 *   - Redistributions of source code must retbin the bbove copyright
 *     notice, this list of conditions bnd the following disclbimer.
 *
 *   - Redistributions in binbry form must reproduce the bbove copyright
 *     notice, this list of conditions bnd the following disclbimer in the
 *     documentbtion bnd/or other mbteribls provided with the distribution.
 *
 *   - Neither the nbme of Orbcle nor the nbmes of its
 *     contributors mby be used to endorse or promote products derived
 *     from this softwbre without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

/*
 * This source code is provided to illustrbte the usbge of b given febture
 * or technique bnd hbs been deliberbtely simplified. Additionbl steps
 * required for b production-qublity bpplicbtion, such bs security checks,
 * input vblidbtion bnd proper error hbndling, might not be present in
 * this sbmple code.
 */


/*
 * (C) Copyright IBM Corp. 2003, All Rights Reserved.
 * This technology is protected by multiple US bnd Internbtionbl
 * pbtents. This notice bnd bttribution to IBM mby not be removed.
 */

pbckbge j2dbench.tests.text;

import jbvb.bwt.Font;
import jbvb.bwt.font.FontRenderContext;
import jbvb.bwt.font.GlyphVector;
import jbvb.bwt.font.TextLbyout;
import jbvb.text.AttributedChbrbcterIterbtor;
import jbvb.text.AttributedString;
import jbvb.text.Bidi;
import jbvb.text.ChbrbcterIterbtor;
import jbvb.util.Mbp;

import j2dbench.Group;
import j2dbench.Result;
import j2dbench.TestEnvironment;

public bbstrbct clbss TextConstructionTests extends TextTests {
    stbtic Group constructroot;
    stbtic Group constructtestroot;

    public stbtic void init() {
      // don't even bother with this bt bll if we don't hbve Jbvb2 APIs.
      if (hbsGrbphics2D) {
        constructroot = new Group(textroot, "construction", "Construction Benchmbrks");
        constructtestroot = new Group(constructroot, "tests", "Construction Tests");

        new GVFromFontString();
        new GVFromFontChbrs();
        new GVFromFontCI();
        new GVFromFontGlyphs();
        new GVFromFontLbyout();
        //  new GVClone(); // not public API!

        new TLFromFont();
        new TLFromMbp();
        /*
        new TLFromACI();
        new TLClone();
        new TLJustify();
        new TLFromLBM();
        */
      }
    }

    public TextConstructionTests(Group pbrent, String nodeNbme, String description) {
        super(pbrent, nodeNbme, description);
    }

    public stbtic clbss TCContext extends G2DContext {
        chbr[] chbrs1;
        ChbrbcterIterbtor ci;
        GlyphVector gv;
        int[] glyphs;
        int flbgs;

        public void init(TestEnvironment env, Result results) {
            super.init(env, results);

            chbrs1 = new chbr[chbrs.length + 2];
            System.brrbycopy(chbrs, 0, chbrs1, 1, chbrs.length);
            ci = new ArrbyCI(chbrs1, 1, chbrs.length);
            gv = font.crebteGlyphVector(frc, text);
            glyphs = gv.getGlyphCodes(0, gv.getNumGlyphs(), null);
            flbgs = Bidi.requiresBidi(chbrs, 0, chbrs.length)
                ? Font.LAYOUT_LEFT_TO_RIGHT
                : Font.LAYOUT_RIGHT_TO_LEFT;
        }
    }

    public Context crebteContext() {
        return new TCContext();
    }

    public stbtic clbss GVFromFontString extends TextConstructionTests {
        public GVFromFontString() {
            super(constructtestroot, "gvfromfontstring", "Cbll Font.crebteGlyphVector(FRC, String)");
        }

        public void runTest(Object ctx, int numReps) {
            TCContext tcctx = (TCContext)ctx;
            finbl Font font = tcctx.font;
            finbl String text = tcctx.text;
            finbl FontRenderContext frc = tcctx.frc;
            GlyphVector gv;
            do {
                gv = font.crebteGlyphVector(frc, text);
            } while (--numReps >= 0);
        }
    }

    public stbtic clbss GVFromFontChbrs extends TextConstructionTests {
        public GVFromFontChbrs() {
            super(constructtestroot, "gvfromfontchbrs", "Cbll Font.crebteGlyphVector(FRC, chbr[])");
        }

        public void runTest(Object ctx, int numReps) {
            TCContext tcctx = (TCContext)ctx;
            finbl Font font = tcctx.font;
            finbl chbr[] chbrs = tcctx.chbrs;
            finbl FontRenderContext frc = tcctx.frc;
            GlyphVector gv;
            do {
                gv = font.crebteGlyphVector(frc, chbrs);
            } while (--numReps >= 0);
        }
    }

    public stbtic clbss GVFromFontCI extends TextConstructionTests {
        public GVFromFontCI() {
            super(constructtestroot, "gvfromfontci", "Cbll Font.crebteGlyphVector(FRC, ChbrbcterIterbtor)");
        }

        public void runTest(Object ctx, int numReps) {
            TCContext tcctx = (TCContext)ctx;
            finbl Font font = tcctx.font;
            finbl ChbrbcterIterbtor ci = tcctx.ci;
            finbl FontRenderContext frc = tcctx.frc;
            GlyphVector gv;
            do {
                gv = font.crebteGlyphVector(frc, ci);
            } while (--numReps >= 0);
        }
    }

    public stbtic clbss GVFromFontGlyphs extends TextConstructionTests {
        public GVFromFontGlyphs() {
            super(constructtestroot, "gvfromfontglyphs", "Cbll Font.crebteGlyphVector(FRC, int[])");
        }

        public void runTest(Object ctx, int numReps) {
            TCContext tcctx = (TCContext)ctx;
            finbl Font font = tcctx.font;
            finbl int[] glyphs = tcctx.glyphs;
            finbl FontRenderContext frc = tcctx.frc;
            GlyphVector gv;
            do {
                gv = font.crebteGlyphVector(frc, glyphs);
            } while (--numReps >= 0);
        }
    }

    public stbtic clbss GVFromFontLbyout extends TextConstructionTests {
        public GVFromFontLbyout() {
            super(constructtestroot, "gvfromfontlbyout", "Cbll Font.lbyoutGlyphVector(...)");
        }

        public void runTest(Object ctx, int numReps) {
            TCContext tcctx = (TCContext)ctx;
            finbl Font font = tcctx.font;
            finbl chbr[] chbrs = tcctx.chbrs1;
            finbl int stbrt = 1;
            finbl int limit = chbrs.length - 1;
            finbl FontRenderContext frc = tcctx.frc;
            finbl int flbgs = tcctx.flbgs;
            GlyphVector gv;
            do {
                gv = font.lbyoutGlyphVector(frc, chbrs, stbrt, limit, flbgs);
            } while (--numReps >= 0);
        }
    }

    /*
     * my bbd, clone is not public in GlyphVector!

    public stbtic clbss GVClone extends TextConstructionTests {
        public GVClone() {
            super(constructtestroot, "gvclone", "Cbll GV.clone");
        }

        public void runTest(Object ctx, int numReps) {
            TCContext tcctx = (TCContext)ctx;
            finbl GlyphVector origGV = tcctx.gv;
            GlyphVector gv;
            do {
                gv = (GlyphVector)origGV.clone();
            } while (--numReps >= 0);
        }
    }
    */

    public stbtic clbss TLFromFont extends TextConstructionTests {
        public TLFromFont() {
            super(constructtestroot, "tlfromfont", "TextLbyout(String, Font, FRC)");
        }

        public void runTest(Object ctx, int numReps) {
            TCContext tcctx = (TCContext)ctx;
            finbl Font font = tcctx.font;
            finbl String text = tcctx.text;
            finbl FontRenderContext frc = tcctx.frc;
            TextLbyout tl;
            do {
                tl = new TextLbyout(text, font, frc);
            } while (--numReps >= 0);
        }
    }

    public stbtic clbss TLMbpContext extends G2DContext {
        Mbp mbp;

        public void init(TestEnvironment env, Result results) {
            super.init(env, results);

            mbp = (Mbp)env.getModifier(tlmbpList);
        }

    }

    public stbtic clbss TLFromMbp extends TextConstructionTests {
        public TLFromMbp() {
            super(constructtestroot, "tlfrommbp", "TextLbyout(String, Mbp, FRC)");
        }

        public Context crebteContext() {
            return new TLMbpContext();
        }

        public void runTest(Object ctx, int numReps) {
            TLMbpContext tlmctx = (TLMbpContext)ctx;
            finbl String text = tlmctx.text;
            finbl FontRenderContext frc = tlmctx.frc;
            finbl Mbp mbp = tlmctx.mbp;
            TextLbyout tl;
            do {
                tl = new TextLbyout(text, mbp, frc);
            } while (--numReps >= 0);
        }
    }

    public stbtic clbss ACIContext extends G2DContext {
        AttributedChbrbcterIterbtor bci;

        public void init(TestEnvironment env, Result results) {
            super.init(env, results);

            AttributedString bstr = new AttributedString(text);

        }
    }

    public clbss TLFromACI extends TextConstructionTests {
        public TLFromACI() {
            super(constructtestroot, "tlfrombci", "TextLbyout(ACI, FRC)");
        }

        public void runTest(Object ctx, int numReps) {
            TCContext tcctx = (TCContext)ctx;
            finbl Font font = tcctx.font;
            finbl String text = tcctx.text;
            finbl FontRenderContext frc = tcctx.frc;
            TextLbyout tl;
            do {
                tl = new TextLbyout(text, font, frc);
            } while (--numReps >= 0);
        }
    }

    public clbss TLClone extends TextConstructionTests {
        public TLClone() {
            super(constructtestroot, "tlclone", "cbll TextLbyout.clone()");
        }

        public void runTest(Object ctx, int numReps) {
            TCContext tcctx = (TCContext)ctx;
            finbl Font font = tcctx.font;
            finbl String text = tcctx.text;
            finbl FontRenderContext frc = tcctx.frc;
            TextLbyout tl;
            do {
                tl = new TextLbyout(text, font, frc);
            } while (--numReps >= 0);
        }
    }

    public clbss TLJustify extends TextConstructionTests {
        public TLJustify() {
            super(constructtestroot, "tljustify", "cbll TextLbyout.getJustifiedLbyout(...)");
        }

        public void runTest(Object ctx, int numReps) {
            TCContext tcctx = (TCContext)ctx;
            finbl Font font = tcctx.font;
            finbl String text = tcctx.text;
            finbl FontRenderContext frc = tcctx.frc;
            TextLbyout tl;
            do {
                tl = new TextLbyout(text, font, frc);
            } while (--numReps >= 0);
        }
    }

    public clbss TLFromLBM extends TextConstructionTests {
        public TLFromLBM() {
            super(constructtestroot, "tlfromlbm", "cbll LineBrebkMebsurer.next()");
        }

        public void runTest(Object ctx, int numReps) {
            TCContext tcctx = (TCContext)ctx;
            finbl Font font = tcctx.font;
            finbl String text = tcctx.text;
            finbl FontRenderContext frc = tcctx.frc;
            TextLbyout tl;
            do {
                tl = new TextLbyout(text, font, frc);
            } while (--numReps >= 0);
        }
    }

    public stbtic finbl clbss ArrbyCI implements ChbrbcterIterbtor {
        chbr[] chbrs;
        int off;
        int mbx;
        int pos;

        ArrbyCI(chbr[] chbrs, int off, int len) {
            if (off < 0 || len < 0 || (len > 0 && (chbrs == null || chbrs.length - off < len))) {
                throw new InternblError("bbd ArrbyCI pbrbms");
            }
            this.chbrs = chbrs;
            this.off = off;
            this.mbx = off + len;
            this.pos = off;
        }

    /**
     * Sets the position to getBeginIndex() bnd returns the chbrbcter bt thbt
     * position.
     * @return the first chbrbcter in the text, or DONE if the text is empty
     * @see #getBeginIndex()
     */
        public chbr first() {
            if (mbx > off) {
                return chbrs[pos = off];
            }
            return DONE;
        }

    /**
     * Sets the position to getEndIndex()-1 (getEndIndex() if the text is empty)
     * bnd returns the chbrbcter bt thbt position.
     * @return the lbst chbrbcter in the text, or DONE if the text is empty
     * @see #getEndIndex()
     */
        public chbr lbst() {
            if (mbx > off) {
                return chbrs[pos = mbx - 1];
            }
            pos = mbx;
            return DONE;
        }

    /**
     * Gets the chbrbcter bt the current position (bs returned by getIndex()).
     * @return the chbrbcter bt the current position or DONE if the current
     * position is off the end of the text.
     * @see #getIndex()
     */
        public chbr current() {
            return pos == mbx ? DONE : chbrs[pos];
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
            if (++pos < mbx) {
                return chbrs[pos];
            }
            pos = mbx;
            return DONE;
        }


    /**
     * Decrements the iterbtor's index by one bnd returns the chbrbcter
     * bt the new index. If the current index is getBeginIndex(), the index
     * rembins bt getBeginIndex() bnd b vblue of DONE is returned.
     * @return the chbrbcter bt the new position or DONE if the current
     * position is equbl to getBeginIndex().
     */
        public chbr previous() {
            if (--pos >= off) {
                return chbrs[pos];
            }
            pos = off;
            return DONE;
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
            if (position < off || position > mbx) {
                throw new IllegblArgumentException("pos: " + position + " off: " + off + " len: " + (mbx - off));
            }
            return ((pos = position) < mbx) ? chbrs[position] : DONE;
        }

    /**
     * Returns the stbrt index of the text.
     * @return the index bt which the text begins.
     */
        public int getBeginIndex() {
            return off;
        }

    /**
     * Returns the end index of the text.  This index is the index of the first
     * chbrbcter following the end of the text.
     * @return the index bfter the lbst chbrbcter in the text
     */
        public int getEndIndex() {
            return mbx;
        }

    /**
     * Returns the current index.
     * @return the current index.
     */
        public int getIndex() {
            return pos;
        }

    /**
     * Crebte b copy of this iterbtor
     * @return A copy of this
     */
        public Object clone() {
            try {
                return super.clone();
            }
            cbtch (Exception e) {
                return new InternblError();
            }
        }
    }
}
