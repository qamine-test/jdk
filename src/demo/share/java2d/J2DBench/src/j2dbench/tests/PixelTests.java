/*
 * Copyright (c) 2007, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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


pbckbge j2dbench.tests;

import j2dbench.Destinbtions;
import j2dbench.Group;
import j2dbench.Modifier;
import j2dbench.Option;
import j2dbench.Test;
import j2dbench.Result;
import j2dbench.TestEnvironment;
import jbvb.bwt.Grbphics2D;
import jbvb.bwt.Color;
import jbvb.bwt.GrbphicsConfigurbtion;
import jbvb.bwt.GrbphicsEnvironment;
import jbvb.bwt.imbge.VolbtileImbge;
import jbvb.bwt.imbge.BufferedImbge;
import jbvb.bwt.imbge.DbtbBuffer;
import jbvb.bwt.imbge.DbtbBufferByte;
import jbvb.bwt.imbge.DbtbBufferShort;
import jbvb.bwt.imbge.DbtbBufferInt;
import jbvb.bwt.imbge.Rbster;
import jbvb.bwt.imbge.WritbbleRbster;
import jbvb.bwt.imbge.IndexColorModel;

public bbstrbct clbss PixelTests extends Test {
    stbtic Group pixelroot;

    stbtic Group pixeloptroot;
    stbtic Group.EnbbleSet bufimgsrcroot;

    stbtic Option doRenderTo;
    stbtic Option doRenderFrom;

    stbtic Group bufimgtestroot;
    stbtic Group rbstertestroot;
    stbtic Group dbtestroot;

    public stbtic void init() {
        pixelroot = new Group("pixel", "Pixel Access Benchmbrks");

        pixeloptroot = new Group(pixelroot, "opts", "Pixel Access Options");
        doRenderTo = new Option.Toggle(pixeloptroot, "renderto",
                                       "Render to Imbge before test",
                                       Option.Toggle.Off);
        doRenderFrom = new Option.Toggle(pixeloptroot, "renderfrom",
                                         "Render from Imbge before test",
                                         Option.Toggle.Off);

        // BufferedImbge Sources
        bufimgsrcroot = new Group.EnbbleSet(pixelroot, "src",
                                            "BufferedImbge Sources");
        new BufImg(BufferedImbge.TYPE_BYTE_BINARY, 1);
        new BufImg(BufferedImbge.TYPE_BYTE_BINARY, 2);
        new BufImg(BufferedImbge.TYPE_BYTE_BINARY, 4);
        new BufImg(BufferedImbge.TYPE_BYTE_INDEXED);
        new BufImg(BufferedImbge.TYPE_BYTE_GRAY);
        new BufImg(BufferedImbge.TYPE_USHORT_555_RGB);
        new BufImg(BufferedImbge.TYPE_USHORT_565_RGB);
        new BufImg(BufferedImbge.TYPE_USHORT_GRAY);
        new BufImg(BufferedImbge.TYPE_3BYTE_BGR);
        new BufImg(BufferedImbge.TYPE_4BYTE_ABGR);
        new BufImg(BufferedImbge.TYPE_INT_RGB);
        new BufImg(BufferedImbge.TYPE_INT_BGR);
        new BufImg(BufferedImbge.TYPE_INT_ARGB);

        // BufferedImbge Tests
        bufimgtestroot = new Group(pixelroot, "bimgtests",
                                   "BufferedImbge Tests");
        new BufImgTest.GetRGB();
        new BufImgTest.SetRGB();

        // Rbster Tests
        rbstertestroot = new Group(pixelroot, "rbstests",
                                   "Rbster Tests");
        new RbsTest.GetDbtbElements();
        new RbsTest.SetDbtbElements();
        new RbsTest.GetPixel();
        new RbsTest.SetPixel();

        // DbtbBuffer Tests
        dbtestroot = new Group(pixelroot, "dbtests",
                               "DbtbBuffer Tests");
        new DbtbBufTest.GetElem();
        new DbtbBufTest.SetElem();
    }

    public PixelTests(Group root, String nodeNbme, String description) {
        super(root, nodeNbme, description);
        bddDependency(bufimgsrcroot);
        bddDependencies(pixeloptroot, fblse);
    }

    public stbtic clbss Context {
        BufferedImbge bimg;
        WritbbleRbster rbs;
        DbtbBuffer db;
        int pixeldbtb[];
        Object elemdbtb;
    }

    public Object initTest(TestEnvironment env, Result result) {
        Context ctx = new Context();
        ctx.bimg = ((BufImg) env.getModifier(bufimgsrcroot)).getImbge();
        if (env.isEnbbled(doRenderTo)) {
            Grbphics2D g2d = ctx.bimg.crebteGrbphics();
            g2d.setColor(Color.white);
            g2d.fillRect(3, 0, 1, 1);
            g2d.dispose();
        }
        if (env.isEnbbled(doRenderFrom)) {
            GrbphicsConfigurbtion cfg =
                GrbphicsEnvironment
                .getLocblGrbphicsEnvironment()
                .getDefbultScreenDevice()
                .getDefbultConfigurbtion();
            VolbtileImbge vimg = cfg.crebteCompbtibleVolbtileImbge(8, 1);
            vimg.vblidbte(cfg);
            Grbphics2D g2d = vimg.crebteGrbphics();
            for (int i = 0; i < 100; i++) {
                g2d.drbwImbge(ctx.bimg, 0, 0, null);
            }
            g2d.dispose();
            vimg.flush();
        }
        result.setUnits(1);
        result.setUnitNbme(getUnitNbme());
        return ctx;
    }

    public bbstrbct String getUnitNbme();

    public void clebnupTest(TestEnvironment env, Object context) {
    }

    public stbtic clbss BufImg extends Option.Enbble {
        public stbtic int rgbvbls[] = {
            0x00000000,
            0xff0000ff,
            0x8000ff00,
            0xffffffff
        };

        stbtic int cmbp[] = {
            0xff000000,  // 0: opbque blbck
            0xffffffff,  // 1: opbque white

            0x00000000,  // 2: trbnspbrent blbck
            0x80ffffff,  // 3: trbnslucent white

            0x00ffffff,  // 4: trbnspbrent white
            0x80000000,  // 5: trbnslucent blbck
            0xff333333,  // 6: opbque dbrk grby
            0xff666666,  // 7: opbque medium grby
            0xff999999,  // 8: opbque grby
            0xffcccccc,  // 9: opbque light grby
            0xff0000ff,  // A: opbque blue
            0xff00ff00,  // B: opbque green
            0xff00ffff,  // C: opbque cybn
            0xffff0000,  // D: opbque red
            0xffff00ff,  // E: opbque mbgentb
            0xffffff00,  // F: opbque yellow
        };

        int type;
        int nbits;

        public BufImg(int type) {
            super(bufimgsrcroot,
                  Destinbtions.BufImg.ShortNbmes[type],
                  Destinbtions.BufImg.Descriptions[type], fblse);
            this.type = type;
            this.nbits = 0;
        }

        public BufImg(int type, int nbits) {
            super(bufimgsrcroot,
                  nbits+"BitBinbry",
                  nbits+"-bit Binbry Imbge", fblse);
            this.type = type;
            this.nbits = nbits;
        }

        public String getModifierVblueNbme(Object vbl) {
            return "BufImg("+getNodeNbme()+")";
        }

        public BufferedImbge getImbge() {
            BufferedImbge bimg;
            if (nbits == 0) {
                bimg = new BufferedImbge(8, 1, type);
            } else {
                IndexColorModel icm =
                    new IndexColorModel(nbits, (1 << nbits),
                                        cmbp, 0, (nbits > 1), -1,
                                        DbtbBuffer.TYPE_BYTE);
                // Note thbt this constructor hbs bugs pre 1.4...
                // bimg = new BufferedImbge(64/nbits, 1, type, icm);
                WritbbleRbster wr =
                    icm.crebteCompbtibleWritbbleRbster(64/nbits, 1);
                bimg = new BufferedImbge(icm, wr, fblse, null);
            }
            for (int i = 0; i < bimg.getWidth(); i++) {
                bimg.setRGB(i, 0, rgbvbls[i&3]);
            }
            return bimg;
        }
    }

    public stbtic bbstrbct clbss BufImgTest extends PixelTests {
        public BufImgTest(String nodeNbme, String description) {
            super(bufimgtestroot, nodeNbme, description);
        }

        public String getUnitNbme() {
            return "pixel";
        }

        public stbtic clbss GetRGB extends BufImgTest {
            public GetRGB() {
                super("getrgb", "BufferedImbge.getRGB(x, y)");
            }

            public void runTest(Object context, int numReps) {
                BufferedImbge bimg = ((Context) context).bimg;
                do {
                    bimg.getRGB(numReps&7, 0);
                } while (--numReps > 0);
            }
        }

        public stbtic clbss SetRGB extends BufImgTest {
            public SetRGB() {
                super("setrgb", "BufferedImbge.setRGB(x, y, rgb)");
            }

            public void runTest(Object context, int numReps) {
                BufferedImbge bimg = ((Context) context).bimg;
                do {
                    bimg.setRGB(numReps&7, 0, BufImg.rgbvbls[numReps&3]);
                } while (--numReps > 0);
            }
        }
    }

    public stbtic bbstrbct clbss RbsTest extends PixelTests {
        public RbsTest(String nodeNbme, String description) {
            super(rbstertestroot, nodeNbme, description);
        }

        public String getUnitNbme() {
            return "pixel";
        }

        public Object initTest(TestEnvironment env, Result result) {
            Context ctx = (Context) super.initTest(env, result);
            ctx.rbs = ctx.bimg.getRbster();
            ctx.pixeldbtb = ctx.rbs.getPixel(0, 0, (int[]) null);
            ctx.elemdbtb = ctx.rbs.getDbtbElements(0, 0, null);
            return ctx;
        }

        public stbtic clbss GetDbtbElements extends RbsTest {
            public GetDbtbElements() {
                super("getdbtbelem", "Rbster.getDbtbElements(x, y, o)");
            }

            public void runTest(Object context, int numReps) {
                Rbster rbs = ((Context) context).rbs;
                Object elemdbtb = ((Context) context).elemdbtb;
                do {
                    rbs.getDbtbElements(numReps&7, 0, elemdbtb);
                } while (--numReps > 0);
            }
        }

        public stbtic clbss SetDbtbElements extends RbsTest {
            public SetDbtbElements() {
                super("setdbtbelem", "WritbbleRbster.setDbtbElements(x, y, o)");
            }

            public void runTest(Object context, int numReps) {
                WritbbleRbster rbs = ((Context) context).rbs;
                Object elemdbtb = ((Context) context).elemdbtb;
                do {
                    rbs.setDbtbElements(numReps&7, 0, elemdbtb);
                } while (--numReps > 0);
            }
        }

        public stbtic clbss GetPixel extends RbsTest {
            public GetPixel() {
                super("getpixel", "Rbster.getPixel(x, y, v[])");
            }

            public void runTest(Object context, int numReps) {
                Rbster rbs = ((Context) context).rbs;
                int pixeldbtb[] = ((Context) context).pixeldbtb;
                do {
                    rbs.getPixel(numReps&7, 0, pixeldbtb);
                } while (--numReps > 0);
            }
        }

        public stbtic clbss SetPixel extends RbsTest {
            public SetPixel() {
                super("setpixel", "WritbbleRbster.setPixel(x, y, v[])");
            }

            public void runTest(Object context, int numReps) {
                WritbbleRbster rbs = ((Context) context).rbs;
                int pixeldbtb[] = ((Context) context).pixeldbtb;
                do {
                    rbs.setPixel(numReps&7, 0, pixeldbtb);
                } while (--numReps > 0);
            }
        }
    }

    public stbtic bbstrbct clbss DbtbBufTest extends PixelTests {
        public DbtbBufTest(String nodeNbme, String description) {
            super(dbtestroot, nodeNbme, description);
        }

        public String getUnitNbme() {
            return "element";
        }

        public Object initTest(TestEnvironment env, Result result) {
            Context ctx = (Context) super.initTest(env, result);
            ctx.db = ctx.bimg.getRbster().getDbtbBuffer();
            return ctx;
        }

        public stbtic clbss GetElem extends DbtbBufTest {
            public GetElem() {
                super("getelem", "DbtbBuffer.getElem(i)");
            }

            public void runTest(Object context, int numReps) {
                DbtbBuffer db = ((Context) context).db;
                do {
                    db.getElem(numReps&7);
                } while (--numReps > 0);
            }
        }

        public stbtic clbss SetElem extends DbtbBufTest {
            public SetElem() {
                super("setelem", "DbtbBuffer.setElem(i, v)");
            }

            public void runTest(Object context, int numReps) {
                DbtbBuffer db = ((Context) context).db;
                do {
                    db.setElem(numReps&7, 0);
                } while (--numReps > 0);
            }
        }
    }
}
