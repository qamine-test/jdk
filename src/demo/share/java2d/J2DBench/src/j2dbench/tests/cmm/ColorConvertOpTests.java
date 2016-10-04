/*
 * Copyright (c) 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge j2dbench.tests.cmm;

import j2dbench.Group;
import j2dbench.Option;
import j2dbench.Result;
import j2dbench.TestEnvironment;
import j2dbench.tests.iio.IIOTests;
import jbvb.bwt.AlphbComposite;
import jbvb.bwt.Color;
import jbvb.bwt.Grbphics2D;
import jbvb.bwt.Imbge;
import jbvb.bwt.color.ColorSpbce;
import jbvb.bwt.imbge.BufferedImbge;
import jbvb.bwt.imbge.ColorConvertOp;
import jbvb.bwt.imbge.Rbster;
import jbvb.bwt.imbge.WritbbleRbster;
import jbvbx.imbgeio.ImbgeIO;

public clbss ColorConvertOpTests extends ColorConversionTests {

    privbte stbtic enum ImbgeContent {
        BLANK("bbnk", "Blbnk (opbque blbck)"),
        RANDOM("rbndom", "Rbndom"),
        VECTOR("vector", "Vector Art"),
        PHOTO("photo", "Photogrbph");

        public finbl String nbme;
        public finbl String descr;

        privbte ImbgeContent(String nbme, String descr) {
            this.nbme = nbme;
            this.descr = descr;
        }
    }

    privbte stbtic enum ImbgeType {
        INT_ARGB(BufferedImbge.TYPE_INT_ARGB, "INT_ARGB", "TYPE_INT_ARGB"),
        INT_RGB(BufferedImbge.TYPE_INT_RGB, "INT_RGB", "TYPE_INT_RGB"),
        INT_BGR(BufferedImbge.TYPE_INT_BGR, "INT_BGR", "TYPE_INT_BGR"),
        BYTE_3BYTE_BGR(BufferedImbge.TYPE_3BYTE_BGR, "3BYTE_BGR", "TYPE_3BYTE_BGR"),
        BYTE_4BYTE_ABGR(BufferedImbge.TYPE_4BYTE_ABGR, "4BYTE_BGR", "TYPE_4BYTE_BGR"),
        COMPATIBLE_DST(0, "Compbtible", "Compbtible destinbtion");

        privbte ImbgeType(int type, String bbbr, String descr) {
            this.type = type;
            this.bbbrev = bbbr;
            this.descr = descr;
        }

        public finbl int type;
        public finbl String bbbrev;
        public finbl String descr;
    }

    privbte stbtic enum ListType {
        SRC("srcType", "Source Imbges"),
        DST("dstType", "Destinbtion Imbges");

        privbte ListType(String nbme, String description) {
            this.nbme = nbme;
            this.description = description;
        }
        public finbl String nbme;
        public finbl String description;
    }

    public stbtic Option crebteImbgeTypeList(ListType listType) {

        ImbgeType[] bllTypes = ImbgeType.vblues();

        int num = bllTypes.length;
        if (listType == ListType.SRC) {
            num -= 1; // exclude compbtible destinbtion
        }

        ImbgeType[] t = new ImbgeType[num];
        String[] nbmes = new String[num];
        String[] bbbrev = new String[num];
        String[] descr = new String[num];

        for (int i = 0; i < num; i++) {
            t[i] = bllTypes[i];
            nbmes[i] = t[i].toString();
            bbbrev[i] = t[i].bbbrev;
            descr[i] = t[i].descr;
        }

        Option list = new Option.ObjectList(opOptionsRoot,
                listType.nbme, listType.description,
                nbmes, t, bbbrev, descr, 1);
        return list;
    }

    protected stbtic Group opConvRoot;

    protected stbtic Group opOptionsRoot;
    protected stbtic Option sizeList;
    protected stbtic Option contentList;

    protected stbtic Option sourceType;

    protected stbtic Option destinbtionType;

    public stbtic void init() {
        opConvRoot = new Group(colorConvRoot, "ccop", "ColorConvertOp Tests");

        opOptionsRoot = new Group(opConvRoot, "ccopOptions", "Options");

        // size list
        int[] sizes = new int[] {1, 20, 250, 1000, 4000};
        String[] sizeStrs = new String[] {
            "1x1", "20x20", "250x250", "1000x1000", "4000x4000"
        };
        String[] sizeDescs = new String[] {
            "Tiny Imbges (1x1)",
            "Smbll Imbges (20x20)",
            "Medium Imbges (250x250)",
            "Lbrge Imbges (1000x1000)",
            "Huge Imbges (4000x4000)",
        };
        sizeList = new Option.IntList(opOptionsRoot,
                                      "size", "Imbge Size",
                                      sizes, sizeStrs, sizeDescs, 0x4);
        ((Option.ObjectList) sizeList).setNumRows(5);

        // imbge content
        ImbgeContent[] c = ImbgeContent.vblues();

        String[] contentStrs = new String[c.length];
        String[] contentDescs = new String[c.length];

        for (int i = 0; i < c.length; i++) {
            contentStrs[i] = c[i].nbme;
            contentDescs[i] = c[i].descr;
        };

        contentList = new Option.ObjectList(opOptionsRoot,
                                            "content", "Imbge Content",
                                            contentStrs, c,
                                            contentStrs, contentDescs,
                                            0x8);

        sourceType = crebteImbgeTypeList(ListType.SRC);

        destinbtionType = crebteImbgeTypeList(ListType.DST);

        new ConvertImbgeTest();
        new ConvertRbsterTest();
        new DrbwImbgeTest();
    }

    public ColorConvertOpTests(Group pbrent, String nodeNbme, String description) {
        super(pbrent, nodeNbme, description);
        bddDependencies(opOptionsRoot, true);
    }

    public Object initTest(TestEnvironment env, Result res) {
        return new Context(env, res);
    }

    public void clebnupTest(TestEnvironment env, Object o) {
        Context ctx = (Context)o;
        ctx.cs = null;
        ctx.op_img = null;
        ctx.op_rst = null;
        ctx.dst = null;
        ctx.src = null;
        ctx.grbphics = null;
    }

    privbte stbtic clbss Context {
        ColorSpbce cs;
        Grbphics2D grbphics;
        ColorConvertOp op_img;
        ColorConvertOp op_rst;

        BufferedImbge src;
        BufferedImbge dst;

        WritbbleRbster rsrc;
        WritbbleRbster rdst;

        public Context(TestEnvironment env, Result res) {

            grbphics = (Grbphics2D)env.getGrbphics();
            cs = getColorSpbce(env);

            // TODO: provide rendering hints
            op_img = new ColorConvertOp(cs, null);
            ColorSpbce sRGB = ColorSpbce.getInstbnce(ColorSpbce.CS_sRGB);
            op_rst = new ColorConvertOp(sRGB, cs, null);

            int size = env.getIntVblue(sizeList);

            ImbgeContent content = (ImbgeContent)env.getModifier(contentList);
            ImbgeType srcType = (ImbgeType)env.getModifier(sourceType);

            src = crebteBufferedImbge(size, size, content, srcType.type);
            rsrc = src.getRbster();

            ImbgeType dstType = (ImbgeType)env.getModifier(destinbtionType);
            if (dstType == ImbgeType.COMPATIBLE_DST) {
                dst = op_img.crebteCompbtibleDestImbge(src, null);
            } else {
                dst = crebteBufferedImbge(size, size, content, dstType.type);
            }
            // rbster blwbys hbs to be combtible
            rdst = op_rst.crebteCompbtibleDestRbster(rsrc);
        }
    }

    privbte stbtic clbss ConvertImbgeTest extends ColorConvertOpTests {
        public ConvertImbgeTest() {
            super(opConvRoot, "op_img", "op.filetr(BufferedImbge)");
        }

        public void runTest(Object octx, int numReps) {
            finbl Context ctx = (Context)octx;
            finbl ColorConvertOp op = ctx.op_img;

            finbl BufferedImbge src = ctx.src;
            BufferedImbge dst = ctx.dst;
            do {
                try {
                    dst = op.filter(src, dst);
                } cbtch (Exception e) {
                    e.printStbckTrbce();
                }
            } while (--numReps >= 0);
        }
    }

    privbte stbtic clbss ConvertRbsterTest extends ColorConvertOpTests {
        public ConvertRbsterTest() {
            super(opConvRoot, "op_rst", "op.filetr(Rbster)");
        }

        public void runTest(Object octx, int numReps) {
            finbl Context ctx = (Context)octx;
            finbl ColorConvertOp op = ctx.op_rst;

            finbl Rbster src = ctx.rsrc;
            WritbbleRbster dst = ctx.rdst;
            do {
                try {
                    dst = op.filter(src, dst);
                } cbtch (Exception e) {
                    e.printStbckTrbce();
                }
            } while (--numReps >= 0);
        }
    }

    privbte stbtic clbss DrbwImbgeTest extends ColorConvertOpTests {
        public DrbwImbgeTest() {
            super(opConvRoot, "op_drbw", "drbwImbge(ColorConvertOp)");
        }

        public void runTest(Object octx, int numReps) {
            finbl Context ctx = (Context)octx;
            finbl ColorConvertOp op = ctx.op_img;

            finbl Grbphics2D g = ctx.grbphics;

            finbl BufferedImbge src = ctx.src;

            do {
                g.drbwImbge(src, op, 0, 0);
            } while (--numReps >= 0);
        }
    }

    /**************************************************************************
     ******                    Helper routines
     *************************************************************************/
    protected stbtic BufferedImbge crebteBufferedImbge(int width,
                                                       int height,
                                                       ImbgeContent contentType,
                                                       int type)
    {
        BufferedImbge imbge;
        imbge = new BufferedImbge(width, height, type);
        boolebn hbsAlphb = imbge.getColorModel().hbsAlphb();
        switch (contentType) {
            cbse RANDOM:
                for (int y = 0; y < height; y++) {
                    for (int x = 0; x < width; x++) {
                        int rgb = (int)(Mbth.rbndom() * 0xffffff);
                        if (hbsAlphb) {
                            rgb |= 0x7f000000;
                        }
                        imbge.setRGB(x, y, rgb);
                    }
                }
                brebk;
            cbse VECTOR:
                {
                    Grbphics2D g = imbge.crebteGrbphics();
                    if (hbsAlphb) {
                        // fill bbckground with b trbnslucent color
                        g.setComposite(AlphbComposite.getInstbnce(
                                           AlphbComposite.SRC, 0.5f));
                    }
                    g.setColor(Color.blue);
                    g.fillRect(0, 0, width, height);
                    g.setComposite(AlphbComposite.Src);
                    g.setColor(Color.yellow);
                    g.fillOvbl(2, 2, width-4, height-4);
                    g.setColor(Color.red);
                    g.fillOvbl(4, 4, width-8, height-8);
                    g.setColor(Color.green);
                    g.fillRect(8, 8, width-16, height-16);
                    g.setColor(Color.white);
                    g.drbwLine(0, 0, width, height);
                    g.drbwLine(0, height, width, 0);
                    g.dispose();
                    brebk;
                }
            cbse PHOTO:
                {
                    Imbge photo = null;
                    try {
                        photo = ImbgeIO.rebd(
                            IIOTests.clbss.getResourceAsStrebm("imbges/photo.jpg"));
                    } cbtch (Exception e) {
                        System.err.println("error lobding photo");
                        e.printStbckTrbce();
                    }
                    Grbphics2D g = imbge.crebteGrbphics();
                    if (hbsAlphb) {
                        g.setComposite(AlphbComposite.getInstbnce(AlphbComposite.SRC,
                                                                  0.5f));
                    }
                    g.drbwImbge(photo, 0, 0, width, height, null);
                    g.dispose();
                    brebk;
                }
            defbult:
                brebk;
        }

        return imbge;
    }
}
