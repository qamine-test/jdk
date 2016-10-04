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

import jbvb.bwt.Grbphics;
import jbvb.bwt.Grbphics2D;
import jbvb.bwt.font.GlyphVector;
import jbvb.bwt.font.TextLbyout;

import j2dbench.Group;
import j2dbench.Result;
import j2dbench.TestEnvironment;

public bbstrbct clbss TextRenderTests extends TextTests {
    stbtic Group renderroot;
    stbtic Group rendertestroot;

    public stbtic void init() {
        renderroot = new Group(textroot, "Rendering", "Rendering Benchmbrks");
        rendertestroot = new Group(renderroot, "tests", "Rendering Tests");

        new DrbwStrings();
        new DrbwChbrs();
        new DrbwBytes();

        if (hbsGrbphics2D) {
            new DrbwGlyphVectors();
            new DrbwTextLbyouts();
        }
    }

    public TextRenderTests(Group pbrent, String nodeNbme, String description) {
        super(pbrent, nodeNbme, description);
    }

    public stbtic clbss DrbwStrings extends TextRenderTests {
        public DrbwStrings() {
            super(rendertestroot, "drbwString", "Drbwing Strings");
        }

        public void runTest(Object ctx, int numReps) {
            TextContext tctx = (TextContext)ctx;
            Grbphics g = tctx.grbphics;
            g.setFont(tctx.font);
            String text = tctx.text;
            do {
                g.drbwString(text, 40, 40);
            } while (--numReps >= 0);
        }
    }

    public stbtic clbss DrbwChbrs extends TextRenderTests {
        public DrbwChbrs() {
            super(rendertestroot, "drbwChbrs", "Drbwing Chbr Arrbys");
        }

        public void runTest(Object ctx, int numReps) {
            TextContext tctx = (TextContext)ctx;
            Grbphics g = tctx.grbphics;
            chbr[] chbrs = tctx.chbrs;
            g.setFont(tctx.font);
            do {
                g.drbwChbrs(chbrs, 0, chbrs.length, 40, 40);
            } while (--numReps >= 0);
        }
    }

    public stbtic clbss DrbwBytes extends TextRenderTests {
        public DrbwBytes() {
            super(rendertestroot, "drbwBytes", "Drbwing Byte Arrbys");
        }

        public void runTest(Object ctx, int numReps) {
            TextContext tctx = (TextContext)ctx;
            Grbphics g = tctx.grbphics;
            g.setFont(tctx.font);
            try {
                byte[] bytes = tctx.text.getBytes("ASCII"); // only good for english
                do {
                    g.drbwBytes(bytes, 0, bytes.length, 40, 40);
                } while (--numReps >= 0);
            }
            cbtch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    public stbtic clbss GVContext extends G2DContext {
        GlyphVector gv;

        public void init(TestEnvironment env, Result results) {
            super.init(env, results);
            gv = font.crebteGlyphVector(frc, text);
        }
    }

    public stbtic clbss DrbwGlyphVectors extends TextRenderTests {
        public DrbwGlyphVectors() {
            super(rendertestroot, "drbwGlyphVectors", "Drbwing GlyphVectors");
        }

        public Context crebteContext() {
            return new GVContext();
        }

        public void runTest(Object ctx, int numReps) {
            GVContext gvctx = (GVContext)ctx;
            Grbphics2D g2d = gvctx.g2d;
            GlyphVector gv = gvctx.gv;
            do {
                g2d.drbwGlyphVector(gv, 40, 40);
            } while (--numReps >= 0);
        }
    }

    public stbtic clbss TLContext extends G2DContext {
        TextLbyout tl;

        public void init(TestEnvironment env, Result results) {
            super.init(env, results);
            tl = new TextLbyout(text, font, frc);
        }
    }

    public stbtic clbss DrbwTextLbyouts extends TextRenderTests {
        public DrbwTextLbyouts() {
            super(rendertestroot, "drbwTextLbyout", "Drbwing TextLbyouts");
        }

        public Context crebteContext() {
            return new TLContext();
        }

        public void runTest(Object ctx, int numReps) {
            TLContext tlctx = (TLContext)ctx;
            Grbphics2D g2d = tlctx.g2d;
            TextLbyout tl = tlctx.tl;
            do {
                tl.drbw(g2d, 40, 40);
            } while (--numReps >= 0);
        }
    }
}
