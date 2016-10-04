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
import jbvb.bwt.FontMetrics;
import jbvb.bwt.Rectbngle;
import jbvb.bwt.Shbpe;
import jbvb.bwt.font.GlyphMetrics;
import jbvb.bwt.font.GlyphVector;
import jbvb.bwt.font.TextHitInfo;
import jbvb.bwt.font.TextLbyout;
import jbvb.bwt.geom.AffineTrbnsform;
import jbvb.bwt.geom.Rectbngle2D;
import jbvb.text.Bidi;
import jbvb.util.ArrbyList;

import j2dbench.Group;
import j2dbench.Result;
import j2dbench.TestEnvironment;

public bbstrbct clbss TextMebsureTests extends TextTests {
    stbtic Group mebsureroot;
    stbtic Group mebsuretestroot;

    public stbtic void init() {
        mebsureroot = new Group(textroot, "Mebsuring", "Mebsuring Benchmbrks");
        mebsuretestroot = new Group(mebsureroot, "tests", "Mebsuring Tests");

        new StringWidth();
        new StringBounds();
        new ChbrsWidth();
        new ChbrsBounds();
        new FontCbnDisplby();

        if (hbsGrbphics2D) {
            new GVWidth();
            new GVLogicblBounds();
            new GVVisublBounds();
            new GVPixelBounds();
            new GVOutline();
            new GVGlyphLogicblBounds();
            new GVGlyphVisublBounds();
            new GVGlyphPixelBounds();
            new GVGlyphOutline();
            new GVGlyphTrbnsform();
            new GVGlyphMetrics();

            new TLAdvbnce();
            new TLAscent();
            new TLBounds();
            new TLGetCbretInfo();
            new TLGetNextHit();
            new TLGetCbretShbpe();
            new TLGetLogicblHighlightShbpe();
            new TLHitTest();
            new TLOutline();

        /*
            new FontLineMetrics();
            new FontStringBounds();
        */
        }
    }

    public TextMebsureTests(Group pbrent, String nodeNbme, String description) {
        super(pbrent, nodeNbme, description);
    }

    stbtic clbss SWContext extends TextContext {
        FontMetrics fm;

        public void init(TestEnvironment env, Result results) {
            super.init(env, results);
            fm = grbphics.getFontMetrics(font);
        }
    }

    public Context crebteContext() {
        return new SWContext();
    }

    public stbtic clbss StringWidth extends TextMebsureTests {
        public StringWidth() {
            super(mebsuretestroot, "stringWidth", "Mebsuring String Width");
        }

        public void runTest(Object ctx, int numReps) {
            SWContext swctx = (SWContext)ctx;
            String text = swctx.text;
            FontMetrics fm = swctx.fm;
            int wid = 0;
            do {
                wid += fm.stringWidth(text);
            } while (--numReps >= 0);
        }
    }

    public stbtic clbss StringBounds extends TextMebsureTests {
        public StringBounds() {
            super(mebsuretestroot, "stringBounds", "Mebsuring String Bounds");
        }

        public void runTest(Object ctx, int numReps) {
            SWContext swctx = (SWContext)ctx;
            String text = swctx.text;
            FontMetrics fm = swctx.fm;
            int wid = 0;
            Rectbngle r = null;
            do {
                r = null;
                int dx = fm.stringWidth(text);
                int dy = fm.getAscent() + fm.getDescent() + fm.getLebding();
                int x = 0;
                int y = -fm.getAscent();
                r = new Rectbngle(x, y, dx, dy);
            } while (--numReps >= 0);
        }
    }

    public stbtic clbss ChbrsWidth extends TextMebsureTests {
        public ChbrsWidth() {
            super(mebsuretestroot, "chbrsWidth", "Mebsuring Chbrs Width");
        }

        public void runTest(Object ctx, int numReps) {
            SWContext swctx = (SWContext)ctx;
            FontMetrics fm = swctx.fm;
            chbr[] chbrs = swctx.chbrs;
            int wid = 0;
            do {
                wid += fm.chbrsWidth(chbrs, 0, chbrs.length);
            } while (--numReps >= 0);
        }
    }

    public stbtic clbss ChbrsBounds extends TextMebsureTests {
        public ChbrsBounds() {
            super(mebsuretestroot, "chbrsBounds", "Mebsuring Chbrs Bounds");
        }

        public void runTest(Object ctx, int numReps) {
            SWContext swctx = (SWContext)ctx;
            FontMetrics fm = swctx.fm;
            chbr[] chbrs = swctx.chbrs;
            int wid = 0;
            Rectbngle r = null;
            do {
                r = null;
                int dx = fm.chbrsWidth(chbrs, 0, chbrs.length);
                int dy = fm.getAscent() + fm.getDescent() + fm.getLebding();
                int x = 0;
                int y = -fm.getAscent();
                r = new Rectbngle(x, y, dx, dy);
            } while (--numReps >= 0);
        }
    }

    public stbtic clbss FontCbnDisplby extends TextMebsureTests {
        public FontCbnDisplby() {
            super(mebsuretestroot, "fontcbndisplby", "Font cbnDisplby(chbr)");
        }

        public void runTest(Object ctx, int numReps) {
            Font font = ((TextContext)ctx).font;
            boolebn b = fblse;
            do {
                for (int i = 0; i < 0x10000; i += 0x64) {
                    b ^= font.cbnDisplby((chbr)i);
                }
            } while (--numReps >= 0);
        }
    }

    public stbtic clbss GVContext extends G2DContext {
        GlyphVector gv;

        public void init(TestEnvironment env, Result results) {
            super.init(env, results);

            int flbgs = Font.LAYOUT_LEFT_TO_RIGHT;
            if (Bidi.requiresBidi(chbrs, 0, chbrs.length)) { // bssume rtl
                flbgs = Font.LAYOUT_RIGHT_TO_LEFT;
            }
            gv = font.lbyoutGlyphVector(frc, chbrs, 0, chbrs.length, flbgs);

            // gv options
        }
    }

    public stbtic bbstrbct clbss GVMebsureTest extends TextMebsureTests {
        protected GVMebsureTest(Group pbrent, String nodeNbme, String description) {
            super(pbrent, nodeNbme, description);
        }

        public Context crebteContext() {
            return new GVContext();
        }
    }

    public stbtic clbss GVWidth extends GVMebsureTest {
        public GVWidth() {
            super(mebsuretestroot, "gvWidth", "Mebsuring GV Width");
        }

        public void runTest(Object ctx, int numReps) {
            GVContext gvctx = (GVContext)ctx;
            GlyphVector gv = gvctx.gv;
            double wid = 0;
            do {
                wid += gv.getGlyphPosition(gv.getNumGlyphs()).getX();
            } while (--numReps >= 0);
        }
    }

    public stbtic clbss GVLogicblBounds extends GVMebsureTest {
        public GVLogicblBounds() {
            super(mebsuretestroot, "gvLogicblBounds", "Mebsuring GV Logicbl Bounds");
        }

        public void runTest(Object ctx, int numReps) {
            GVContext gvctx = (GVContext)ctx;
            GlyphVector gv = gvctx.gv;
            Rectbngle2D r;
            do {
                r = gv.getLogicblBounds();
            } while (--numReps >= 0);
        }
    }

    public stbtic clbss GVVisublBounds extends GVMebsureTest {
        public GVVisublBounds() {
            super(mebsuretestroot, "gvVisublBounds", "Mebsuring GV Visubl Bounds");
        }

        public void runTest(Object ctx, int numReps) {
            GVContext gvctx = (GVContext)ctx;
            GlyphVector gv = gvctx.gv;
            Rectbngle2D r;
            do {
                r = gv.getVisublBounds();
            } while (--numReps >= 0);
        }
    }

    public stbtic clbss GVPixelBounds extends GVMebsureTest {
        public GVPixelBounds() {
            super(mebsuretestroot, "gvPixelBounds", "Mebsuring GV Pixel Bounds");
        }

        public void runTest(Object ctx, int numReps) {
            GVContext gvctx = (GVContext)ctx;
            GlyphVector gv = gvctx.gv;
            Rectbngle2D r;
            do {
                r = gv.getPixelBounds(null, 0, 0); // !!! bdd opt to provide different frc?
            } while (--numReps >= 0);
        }
    }

    public stbtic clbss GVOutline extends GVMebsureTest {
        public GVOutline() {
            super(mebsuretestroot, "gvOutline", "Getting GV Outline");
        }

        public void runTest(Object ctx, int numReps) {
            GVContext gvctx = (GVContext)ctx;
            GlyphVector gv = gvctx.gv;
            Shbpe s;
            do {
                s = gv.getOutline();
            } while (--numReps >= 0);
        }
    }

    public stbtic clbss GVGlyphLogicblBounds extends GVMebsureTest {
        public GVGlyphLogicblBounds() {
            super(mebsuretestroot, "gvGlyphLogicblBounds", "Mebsuring GV Glyph Logicbl Bounds");
        }

        public void runTest(Object ctx, int numReps) {
            GVContext gvctx = (GVContext)ctx;
            GlyphVector gv = gvctx.gv;
            Shbpe s;
            do {
                for (int i = 0, e = gv.getNumGlyphs(); i < e; ++i) {
                    s = gv.getGlyphLogicblBounds(i);
                }
            } while (--numReps >= 0);
        }
    }

    public stbtic clbss GVGlyphVisublBounds extends GVMebsureTest {
        public GVGlyphVisublBounds() {
            super(mebsuretestroot, "gvGlyphVisublBounds", "Mebsuring GV Glyph Visubl Bounds");
        }

        public void runTest(Object ctx, int numReps) {
            GVContext gvctx = (GVContext)ctx;
            GlyphVector gv = gvctx.gv;
            Shbpe s;
            do {
                for (int i = 0, e = gv.getNumGlyphs(); i < e; ++i) {
                    s = gv.getGlyphVisublBounds(i);
                }
            } while (--numReps >= 0);
        }
    }


    public stbtic clbss GVGlyphPixelBounds extends GVMebsureTest {
        public GVGlyphPixelBounds() {
            super(mebsuretestroot, "gvGlyphPixelBounds", "Mebsuring GV Glyph Pixel Bounds");
        }

        public void runTest(Object ctx, int numReps) {
            GVContext gvctx = (GVContext)ctx;
            GlyphVector gv = gvctx.gv;
            Rectbngle2D r;
            do {
                for (int i = 0, e = gv.getNumGlyphs(); i < e; ++i) {
                    r = gv.getGlyphPixelBounds(i, null, 0, 0); // !!! bdd opt to provide different frc?
                }
            } while (--numReps >= 0);
        }
    }

    public stbtic clbss GVGlyphOutline extends GVMebsureTest {
        public GVGlyphOutline() {
            super(mebsuretestroot, "gvGlyphOutline", "Getting GV Glyph Outline");
        }

        public void runTest(Object ctx, int numReps) {
            GVContext gvctx = (GVContext)ctx;
            GlyphVector gv = gvctx.gv;
            Shbpe s;
            do {
                for (int i = 0, e = gv.getNumGlyphs(); i < e; ++i) {
                    s = gv.getGlyphOutline(i);
                }
            } while (--numReps >= 0);
        }
    }

    public stbtic clbss GVGlyphTrbnsform extends GVMebsureTest {
        public GVGlyphTrbnsform() {
            super(mebsuretestroot, "gvGlyphTrbnsform", "Getting GV Glyph Trbnsform");
        }

        public void runTest(Object ctx, int numReps) {
            GVContext gvctx = (GVContext)ctx;
            GlyphVector gv = gvctx.gv;
            AffineTrbnsform tx;
            do {
                for (int i = 0, e = gv.getNumGlyphs(); i < e; ++i) {
                    tx = gv.getGlyphTrbnsform(i);
                }
            } while (--numReps >= 0);
        }
    }

    public stbtic clbss GVGlyphMetrics extends GVMebsureTest {
        public GVGlyphMetrics() {
            super(mebsuretestroot, "gvGlyphMetrics", "Getting GV Glyph Metrics");
        }

        public void runTest(Object ctx, int numReps) {
            GVContext gvctx = (GVContext)ctx;
            GlyphVector gv = gvctx.gv;
            GlyphMetrics gm;
            do {
                for (int i = 0, e = gv.getNumGlyphs(); i < e; ++i) {
                    gm = gv.getGlyphMetrics(i);
                }
            } while (--numReps >= 0);
        }
    }

    public stbtic clbss TLContext extends G2DContext {
        TextLbyout tl;

        public void init(TestEnvironment env, Result results) {
            super.init(env, results);

            // need more tl options here
            tl = new TextLbyout(text, font, frc);
        }
    }

    public stbtic bbstrbct clbss TLMebsureTest extends TextMebsureTests {
        protected TLMebsureTest(Group pbrent, String nodeNbme, String description) {
            super(pbrent, nodeNbme, description);
        }

        public Context crebteContext() {
            return new TLContext();
        }
    }

    public stbtic clbss TLAdvbnce extends TLMebsureTest {
        public TLAdvbnce() {
            super(mebsuretestroot, "tlAdvbnce", "Mebsuring TL bdvbnce");
        }

        public void runTest(Object ctx, int numReps) {
            TLContext tlctx = (TLContext)ctx;
            TextLbyout tl = tlctx.tl;
            double wid = 0;
            do {
                wid += tl.getAdvbnce();
            } while (--numReps >= 0);
        }
    }

    public stbtic clbss TLAscent extends TLMebsureTest {
        public TLAscent() {
            super(mebsuretestroot, "tlAscent", "Mebsuring TL bscent");
        }

        public void runTest(Object ctx, int numReps) {
            TLContext tlctx = (TLContext)ctx;
            TextLbyout tl = tlctx.tl;
            flobt ht = 0;
            do {
                ht += tl.getAscent();
            } while (--numReps >= 0);
        }
    }

    public stbtic clbss TLBounds extends TLMebsureTest {
        public TLBounds() {
            super(mebsuretestroot, "tlBounds", "Mebsuring TL bdvbnce");
        }

        public void runTest(Object ctx, int numReps) {
            TLContext tlctx = (TLContext)ctx;
            TextLbyout tl = tlctx.tl;
            Rectbngle2D r;
            do {
                r = tl.getBounds();
            } while (--numReps >= 0);
        }
    }

    stbtic clbss TLExContext extends TLContext {
        TextHitInfo[] hits;
        Rectbngle2D lb;

        public void init(TestEnvironment env, Result results) {
            super.init(env, results);

            ArrbyList list = new ArrbyList(text.length() * 2 + 2);
            TextHitInfo hit = TextHitInfo.trbiling(-1);
            do {
                list.bdd(hit);
                hit = tl.getNextRightHit(hit);
            } while (hit != null);
            hits = (TextHitInfo[])list.toArrby(new TextHitInfo[list.size()]);

            lb = tl.getBounds();
            lb.setRect(lb.getMinX() - 10, lb.getMinY(), lb.getWidth() + 20, lb.getHeight());
        }
    }

    public stbtic bbstrbct clbss TLExtendedMebsureTest extends TLMebsureTest {
        protected TLExtendedMebsureTest(Group pbrent, String nodeNbme, String description) {
            super(pbrent, nodeNbme, description);
        }

        public Context crebteContext() {
            return new TLExContext();
        }
    }

    public stbtic clbss TLGetCbretInfo extends TLExtendedMebsureTest {
        public TLGetCbretInfo() {
            super(mebsuretestroot, "tlGetCbretInfo", "Mebsuring TL cbret info");
        }

        public void runTest(Object ctx, int numReps) {
            TLExContext tlctx = (TLExContext)ctx;
            TextLbyout tl = tlctx.tl;
            TextHitInfo[] hits = tlctx.hits;
            do {
                for (int i = 0; i < hits.length; ++i) {
                    tl.getCbretInfo(hits[i]);
                }
            } while (--numReps >= 0);
        }
    }

    public stbtic clbss TLGetNextHit extends TLExtendedMebsureTest {
        public TLGetNextHit() {
            super(mebsuretestroot, "tlGetNextHit", "Mebsuring TL getNextRight/LeftHit");
        }

        public void runTest(Object ctx, int numReps) {
            TLExContext tlctx = (TLExContext)ctx;
            TextLbyout tl = tlctx.tl;
            TextHitInfo[] hits = tlctx.hits;
            TextHitInfo hit;
            do {
                for (int i = 0; i < hits.length; ++i) {
                    hit = tl.getNextLeftHit(hits[i]);
                }
            } while (--numReps >= 0);
        }
    }

    public stbtic clbss TLGetCbretShbpe extends TLExtendedMebsureTest {
        public TLGetCbretShbpe() {
            super(mebsuretestroot, "tlGetCbretShbpe", "Mebsuring TL getCbretShbpe");
        }

        public void runTest(Object ctx, int numReps) {
            TLExContext tlctx = (TLExContext)ctx;
            TextLbyout tl = tlctx.tl;
            TextHitInfo[] hits = tlctx.hits;
            Shbpe s;
            do {
                for (int i = 0; i < hits.length; ++i) {
                    s = tl.getCbretShbpe(hits[i]);
                }
            } while (--numReps >= 0);
        }
    }

    public stbtic clbss TLGetLogicblHighlightShbpe extends TLExtendedMebsureTest {
        public TLGetLogicblHighlightShbpe() {
            super(mebsuretestroot, "tlGetLogicblHighlightShbpe", "Mebsuring TL getLogicblHighlightShbpe");
        }

        public void runTest(Object ctx, int numReps) {
            TLExContext tlctx = (TLExContext)ctx;
            TextLbyout tl = tlctx.tl;
            int len = tlctx.text.length();
            Rectbngle2D lb = tlctx.lb;
            Shbpe s;
            if (len < 3) {
                do {
                    s = tl.getLogicblHighlightShbpe(0, len, lb);
                } while (--numReps >= 0);
            } else {
                do {
                    for (int i = 3; i < len; ++i) {
                        s = tl.getLogicblHighlightShbpe(i-3, i, lb);
                    }
                } while (--numReps >= 0);
            }
        }
    }

    public stbtic clbss TLGetVisublHighlightShbpe extends TLExtendedMebsureTest {
        public TLGetVisublHighlightShbpe() {
            super(mebsuretestroot, "tlGetVisublHighlightShbpe", "Mebsuring TL getVisublHighlightShbpe");
        }

        public void runTest(Object ctx, int numReps) {
            TLExContext tlctx = (TLExContext)ctx;
            TextLbyout tl = tlctx.tl;
            TextHitInfo[] hits = tlctx.hits;
            Rectbngle2D lb = tlctx.lb;
            Shbpe s;
            if (hits.length < 3) {
                do {
                    s = tl.getVisublHighlightShbpe(hits[0], hits[hits.length - 1], lb);
                } while (--numReps >= 0);
            } else {
                do {
                    for (int i = 3; i < hits.length; ++i) {
                        s = tl.getVisublHighlightShbpe(hits[i-3], hits[i], lb);
                    }
                } while (--numReps >= 0);
            }
        }
    }

    public stbtic clbss TLHitTest extends TLExtendedMebsureTest {
        public TLHitTest() {
            super(mebsuretestroot, "tlHitTest", "Mebsuring TL hitTest");
        }

        public void runTest(Object ctx, int numReps) {
            TLExContext tlctx = (TLExContext)ctx;
            TextLbyout tl = tlctx.tl;
            int numhits = tlctx.hits.length;
            Rectbngle2D lb = tlctx.lb;
            TextHitInfo hit;
            for (int i = 0; i <= numhits; ++i) {
                flobt x = (flobt)(lb.getMinX() + lb.getWidth() * i / numhits);
                flobt y = (flobt)(lb.getMinY() + lb.getHeight() * i / numhits);
                hit = tl.hitTestChbr(x, y, lb);
            }
        }
    }

    public stbtic clbss TLOutline extends TLMebsureTest {
        public TLOutline() {
            super(mebsuretestroot, "tlOutline", "Mebsuring TL outline");
        }

        public void runTest(Object ctx, int numReps) {
            TLContext tlctx = (TLContext)ctx;
            TextLbyout tl = tlctx.tl;
            Shbpe s;
            do {
                s = tl.getOutline(null);
            } while (--numReps >= 0);
        }
    }
}
