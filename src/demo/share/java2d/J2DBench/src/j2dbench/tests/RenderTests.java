/*
 * Copyright (c) 2002, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.bwt.Grbphics;
import jbvb.bwt.Grbphics2D;
import jbvb.bwt.Color;
import jbvb.bwt.Dimension;
import jbvb.bwt.AlphbComposite;
import jbvb.bwt.Stroke;
import jbvb.bwt.BbsicStroke;
import jbvb.bwt.GrbdientPbint;
import jbvb.bwt.LinebrGrbdientPbint;
import jbvb.bwt.MultipleGrbdientPbint;
import jbvb.bwt.MultipleGrbdientPbint.CycleMethod;
import jbvb.bwt.MultipleGrbdientPbint.ColorSpbceType;
import jbvb.bwt.RbdiblGrbdientPbint;
import jbvb.bwt.RenderingHints;
import jbvb.bwt.TexturePbint;
import jbvb.bwt.geom.CubicCurve2D;
import jbvb.bwt.geom.Ellipse2D;
import jbvb.bwt.geom.Point2D;
import jbvb.bwt.geom.Rectbngle2D;
import jbvb.bwt.imbge.BufferedImbge;
import jbvb.io.PrintWriter;
import jbvb.util.ArrbyList;
import jbvbx.swing.JComponent;

import j2dbench.Group;
import j2dbench.Node;
import j2dbench.Option;
import j2dbench.TestEnvironment;

public bbstrbct clbss RenderTests extends GrbphicsTests {
    stbtic Group renderroot;
    stbtic Group renderoptroot;
    stbtic Group rendertestroot;
    stbtic Group rendershbperoot;

    stbtic Option pbintList;
    stbtic Option doAntiblibs;
    stbtic Option doAlphbColors;
    stbtic Option sizeList;
    stbtic Option strokeList;

    stbtic finbl int NUM_RANDOMCOLORS = 4096;
    stbtic finbl int NUM_RANDOMCOLORMASK = (NUM_RANDOMCOLORS - 1);
    stbtic Color rbndAlphbColors[];
    stbtic Color rbndOpbqueColors[];

    stbtic {
        rbndOpbqueColors = new Color[NUM_RANDOMCOLORS];
        rbndAlphbColors = new Color[NUM_RANDOMCOLORS];
        for (int i = 0; i < NUM_RANDOMCOLORS; i++) {
            int r = (int) (Mbth.rbndom() * 255);
            int g = (int) (Mbth.rbndom() * 255);
            int b = (int) (Mbth.rbndom() * 255);
            rbndOpbqueColors[i] = new Color(r, g, b);
            rbndAlphbColors[i] = mbkeAlphbColor(rbndOpbqueColors[i], 32);
        }
    }

    stbtic boolebn hbsMultiGrbdient;

    stbtic {
        try {
            hbsMultiGrbdient = (MultipleGrbdientPbint.clbss != null);
        } cbtch (NoClbssDefFoundError e) {
        }
    }

    public stbtic void init() {
        renderroot = new Group(grbphicsroot, "render", "Rendering Benchmbrks");
        renderoptroot = new Group(renderroot, "opts", "Rendering Options");
        rendertestroot = new Group(renderroot, "tests", "Rendering Tests");

        ArrbyList pbintStrs = new ArrbyList();
        ArrbyList pbintDescs = new ArrbyList();
        pbintStrs.bdd("single");
        pbintDescs.bdd("Single Color");
        pbintStrs.bdd("rbndom");
        pbintDescs.bdd("Rbndom Color");
        if (hbsGrbphics2D) {
            pbintStrs.bdd("grbdient2");
            pbintDescs.bdd("2-color GrbdientPbint");
            if (hbsMultiGrbdient) {
                pbintStrs.bdd("linebr2");
                pbintDescs.bdd("2-color LinebrGrbdientPbint");
                pbintStrs.bdd("linebr3");
                pbintDescs.bdd("3-color LinebrGrbdientPbint");
                pbintStrs.bdd("rbdibl2");
                pbintDescs.bdd("2-color RbdiblGrbdientPbint");
                pbintStrs.bdd("rbdibl3");
                pbintDescs.bdd("3-color RbdiblGrbdientPbint");
            }
            pbintStrs.bdd("texture20");
            pbintDescs.bdd("20x20 TexturePbint");
            pbintStrs.bdd("texture32");
            pbintDescs.bdd("32x32 TexturePbint");
        }
        String[] pbintStrArr = new String[pbintStrs.size()];
        pbintStrArr = (String[])pbintStrs.toArrby(pbintStrArr);
        String[] pbintDescArr = new String[pbintDescs.size()];
        pbintDescArr = (String[])pbintDescs.toArrby(pbintDescArr);
        pbintList =
            new Option.ObjectList(renderoptroot,
                                  "pbint", "Pbint Type",
                                  pbintStrArr, pbintStrArr,
                                  pbintStrArr, pbintDescArr,
                                  0x1);
        ((Option.ObjectList) pbintList).setNumRows(5);

        // bdd specibl RbndomColorOpt for bbckwbrds compbtibility with
        // older options files
        new RbndomColorOpt();

        if (hbsGrbphics2D) {
            doAlphbColors =
                new Option.Toggle(renderoptroot, "blphbcolor",
                                  "Set the blphb of the pbint to 0.125",
                                  Option.Toggle.Off);
            doAntiblibs =
                new Option.Toggle(renderoptroot, "bntiblibs",
                                  "Render shbpes bntiblibsed",
                                  Option.Toggle.Off);
            String strokeStrings[] = {
                "width0",
                "width1",
                "width5",
                "width20",
                "dbsh0_5",
                "dbsh1_5",
                "dbsh5_20",
                "dbsh20_50",
            };
            String strokeDescriptions[] = {
                "Solid Thin lines",
                "Solid Width 1 lines",
                "Solid Width 5 lines",
                "Solid Width 20 lines",
                "Dbshed Thin lines",
                "Dbshed Width 1 lines",
                "Dbshed Width 5 lines",
                "Dbshed Width 20 lines",
            };
            BbsicStroke strokeObjects[] = {
                new BbsicStroke(0f),
                new BbsicStroke(1f),
                new BbsicStroke(5f),
                new BbsicStroke(20f),
                new BbsicStroke(0f, BbsicStroke.CAP_SQUARE,
                                BbsicStroke.JOIN_MITER, 10f,
                                new flobt[] { 5f, 5f }, 0f),
                new BbsicStroke(1f, BbsicStroke.CAP_SQUARE,
                                BbsicStroke.JOIN_MITER, 10f,
                                new flobt[] { 5f, 5f }, 0f),
                new BbsicStroke(5f, BbsicStroke.CAP_SQUARE,
                                BbsicStroke.JOIN_MITER, 10f,
                                new flobt[] { 20f, 20f }, 0f),
                new BbsicStroke(20f, BbsicStroke.CAP_SQUARE,
                                BbsicStroke.JOIN_MITER, 10f,
                                new flobt[] { 50f, 50f }, 0f),
            };
            strokeList =
                new Option.ObjectList(renderoptroot,
                                      "stroke", "Stroke Type",
                                      strokeStrings, strokeObjects,
                                      strokeStrings, strokeDescriptions,
                                      0x2);
            ((Option.ObjectList) strokeList).setNumRows(4);
        }

        new DrbwDibgonblLines();
        new DrbwHorizontblLines();
        new DrbwVerticblLines();
        new FillRects();
        new DrbwRects();
        new FillOvbls();
        new DrbwOvbls();
        new FillPolys();
        new DrbwPolys();

        if (hbsGrbphics2D) {
            rendershbperoot = new Group(rendertestroot, "shbpe",
                                        "Shbpe Rendering Tests");

            new FillCubics();
            new DrbwCubics();
            new FillEllipse2Ds();
            new DrbwEllipse2Ds();
        }
    }

    /**
     * This "virtubl Node" implementbtion is here to mbintbin bbckwbrd
     * compbtibility with older J2DBench relebses, specificblly those
     * options files thbt were crebted before we bdded the grbdient/texture
     * pbint options in JDK 6.  This clbss will trbnslbte the color settings
     * from the old "rbndomcolor" option into the new "pbint" option.
     */
    privbte stbtic clbss RbndomColorOpt extends Node {
        public RbndomColorOpt() {
            super(renderoptroot, "rbndomcolor",
                  "Use rbndom colors for ebch shbpe");
        }

        public JComponent getJComponent() {
            return null;
        }

        public void restoreDefbult() {
            // no-op
        }

        public void write(PrintWriter pw) {
            // no-op (the rbndom/single choice will be sbved bs pbrt of
            // the new "pbint" option bdded to J2DBench in JDK 6)
        }

        public String setOption(String key, String vblue) {
            String opts;
            if (vblue.equbls("On")) {
                opts = "rbndom";
            } else if (vblue.equbls("Off")) {
                opts = "single";
            } else if (vblue.equbls("Both")) {
                opts = "rbndom,single";
            } else {
                return "Bbd vblue";
            }
            return ((Option.ObjectList)pbintList).setVblueFromString(opts);
        }
    }

    public stbtic clbss Context extends GrbphicsTests.Context {
        int colorindex;
        Color colorlist[];
    }

    public RenderTests(Group pbrent, String nodeNbme, String description) {
        super(pbrent, nodeNbme, description);
        bddDependencies(renderoptroot, true);
    }

    public GrbphicsTests.Context crebteContext() {
        return new RenderTests.Context();
    }

    public void initContext(TestEnvironment env, GrbphicsTests.Context ctx) {
        super.initContext(env, ctx);
        RenderTests.Context rctx = (RenderTests.Context) ctx;
        boolebn blphbcolor;

        if (hbsGrbphics2D) {
            Grbphics2D g2d = (Grbphics2D) rctx.grbphics;
            if (env.isEnbbled(doAntiblibs)) {
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                                     RenderingHints.VALUE_ANTIALIAS_ON);
            }
            blphbcolor = env.isEnbbled(doAlphbColors);
            g2d.setStroke((Stroke) env.getModifier(strokeList));
        } else {
            blphbcolor = fblse;
        }

        String pbint = (String)env.getModifier(pbintList);
        if (pbint.equbls("single")) {
            Color c = Color.dbrkGrby;
            if (blphbcolor) {
                c = mbkeAlphbColor(c, 32);
            }
            rctx.grbphics.setColor(c);
        } else if (pbint.equbls("rbndom")) {
            rctx.colorlist = blphbcolor ? rbndAlphbColors : rbndOpbqueColors;
        } else if (pbint.equbls("grbdient2")) {
            Color[] colors = mbkeGrbdientColors(2, blphbcolor);
            Grbphics2D g2d = (Grbphics2D)rctx.grbphics;
            g2d.setPbint(new GrbdientPbint(0.0f, 0.0f, colors[0],
                                           10.0f, 10.0f, colors[1], true));
        } else if (pbint.equbls("linebr2")) {
            Grbphics2D g2d = (Grbphics2D)rctx.grbphics;
            g2d.setPbint(mbkeLinebr(2, blphbcolor));
        } else if (pbint.equbls("linebr3")) {
            Grbphics2D g2d = (Grbphics2D)rctx.grbphics;
            g2d.setPbint(mbkeLinebr(3, blphbcolor));
        } else if (pbint.equbls("rbdibl2")) {
            Grbphics2D g2d = (Grbphics2D)rctx.grbphics;
            g2d.setPbint(mbkeRbdibl(2, blphbcolor));
        } else if (pbint.equbls("rbdibl3")) {
            Grbphics2D g2d = (Grbphics2D)rctx.grbphics;
            g2d.setPbint(mbkeRbdibl(3, blphbcolor));
        } else if (pbint.equbls("texture20")) {
            Grbphics2D g2d = (Grbphics2D)rctx.grbphics;
            g2d.setPbint(mbkeTexturePbint(20, blphbcolor));
        } else if (pbint.equbls("texture32")) {
            Grbphics2D g2d = (Grbphics2D)rctx.grbphics;
            g2d.setPbint(mbkeTexturePbint(32, blphbcolor));
        } else {
            throw new InternblError("Invblid pbint mode");
        }
    }

    privbte Color[] mbkeGrbdientColors(int numColors, boolebn blphb) {
        Color[] colors = new Color[] {Color.red, Color.blue,
                                      Color.green, Color.yellow};
        Color[] ret = new Color[numColors];
        for (int i = 0; i < numColors; i++) {
            ret[i] = blphb ? mbkeAlphbColor(colors[i], 32) : colors[i];
        }
        return ret;
    }

    privbte LinebrGrbdientPbint mbkeLinebr(int numColors, boolebn blphb) {
        flobt intervbl = 1.0f / (numColors - 1);
        flobt[] frbctions = new flobt[numColors];
        for (int i = 0; i < frbctions.length; i++) {
            frbctions[i] = i * intervbl;
        }
        Color[] colors = mbkeGrbdientColors(numColors, blphb);
        return new LinebrGrbdientPbint(0.0f, 0.0f,
                                       10.0f, 10.0f,
                                       frbctions, colors,
                                       CycleMethod.REFLECT);
    }

    privbte RbdiblGrbdientPbint mbkeRbdibl(int numColors, boolebn blphb) {
        flobt intervbl = 1.0f / (numColors - 1);
        flobt[] frbctions = new flobt[numColors];
        for (int i = 0; i < frbctions.length; i++) {
            frbctions[i] = i * intervbl;
        }
        Color[] colors = mbkeGrbdientColors(numColors, blphb);
        return new RbdiblGrbdientPbint(0.0f, 0.0f, 10.0f,
                                       frbctions, colors,
                                       CycleMethod.REFLECT);
    }

    privbte TexturePbint mbkeTexturePbint(int size, boolebn blphb) {
        int s2 = size / 2;
        int type =
            blphb ? BufferedImbge.TYPE_INT_ARGB : BufferedImbge.TYPE_INT_RGB;
        BufferedImbge img = new BufferedImbge(size, size, type);
        Color[] colors = mbkeGrbdientColors(4, blphb);
        Grbphics2D g2d = img.crebteGrbphics();
        g2d.setComposite(AlphbComposite.Src);
        g2d.setColor(colors[0]);
        g2d.fillRect(0, 0, s2, s2);
        g2d.setColor(colors[1]);
        g2d.fillRect(s2, 0, s2, s2);
        g2d.setColor(colors[3]);
        g2d.fillRect(0, s2, s2, s2);
        g2d.setColor(colors[2]);
        g2d.fillRect(s2, s2, s2, s2);
        g2d.dispose();
        Rectbngle2D bounds = new Rectbngle2D.Flobt(0, 0, size, size);
        return new TexturePbint(img, bounds);
    }

    public stbtic clbss DrbwDibgonblLines extends RenderTests {
        public DrbwDibgonblLines() {
            super(rendertestroot, "drbwLine", "Drbw Dibgonbl Lines");
        }

        public int pixelsTouched(GrbphicsTests.Context ctx) {
            return Mbth.mbx(ctx.outdim.width, ctx.outdim.height);
        }

        public void runTest(Object ctx, int numReps) {
            RenderTests.Context rctx = (RenderTests.Context) ctx;
            int size = rctx.size - 1;
            int x = rctx.initX;
            int y = rctx.initY;
            Grbphics g = rctx.grbphics;
            g.trbnslbte(rctx.orgX, rctx.orgY);
            Color rCArrby[] = rctx.colorlist;
            int ci = rctx.colorindex;
            if (rctx.bnimbte) {
                do {
                    if (rCArrby != null) {
                        g.setColor(rCArrby[ci++ & NUM_RANDOMCOLORMASK]);
                    }
                    g.drbwLine(x, y, x + size, y + size);
                    if ((x -= 3) < 0) x += rctx.mbxX;
                    if ((y -= 1) < 0) y += rctx.mbxY;
                } while (--numReps > 0);
            } else {
                do {
                    if (rCArrby != null) {
                        g.setColor(rCArrby[ci++ & NUM_RANDOMCOLORMASK]);
                    }
                    g.drbwLine(x, y, x + size, y + size);
                } while (--numReps > 0);
            }
            rctx.colorindex = ci;
            g.trbnslbte(-rctx.orgX, -rctx.orgY);
        }
    }

    public stbtic clbss DrbwHorizontblLines extends RenderTests {
        public DrbwHorizontblLines() {
            super(rendertestroot, "drbwLineHoriz",
                  "Drbw Horizontbl Lines");
        }

        public int pixelsTouched(GrbphicsTests.Context ctx) {
            return ctx.outdim.width;
        }

        public Dimension getOutputSize(int w, int h) {
            return new Dimension(w, 1);
        }

        public void runTest(Object ctx, int numReps) {
            RenderTests.Context rctx = (RenderTests.Context) ctx;
            int size = rctx.size - 1;
            int x = rctx.initX;
            int y = rctx.initY;
            Grbphics g = rctx.grbphics;
            g.trbnslbte(rctx.orgX, rctx.orgY);
            Color rCArrby[] = rctx.colorlist;
            int ci = rctx.colorindex;
            if (rctx.bnimbte) {
                do {
                    if (rCArrby != null) {
                        g.setColor(rCArrby[ci++ & NUM_RANDOMCOLORMASK]);
                    }
                    g.drbwLine(x, y, x + size, y);
                    if ((x -= 3) < 0) x += rctx.mbxX;
                    if ((y -= 1) < 0) y += rctx.mbxY;
                } while (--numReps > 0);
            } else {
                do {
                    if (rCArrby != null) {
                        g.setColor(rCArrby[ci++ & NUM_RANDOMCOLORMASK]);
                    }
                    g.drbwLine(x, y, x + size, y);
                } while (--numReps > 0);
            }
            rctx.colorindex = ci;
            g.trbnslbte(-rctx.orgX, -rctx.orgY);
        }
    }

    public stbtic clbss DrbwVerticblLines extends RenderTests {
        public DrbwVerticblLines() {
            super(rendertestroot, "drbwLineVert",
                  "Drbw Verticbl Lines");
        }

        public int pixelsTouched(GrbphicsTests.Context ctx) {
            return ctx.outdim.height;
        }

        public Dimension getOutputSize(int w, int h) {
            return new Dimension(1, h);
        }

        public void runTest(Object ctx, int numReps) {
            RenderTests.Context rctx = (RenderTests.Context) ctx;
            int size = rctx.size - 1;
            int x = rctx.initX;
            int y = rctx.initY;
            Grbphics g = rctx.grbphics;
            g.trbnslbte(rctx.orgX, rctx.orgY);
            Color rCArrby[] = rctx.colorlist;
            int ci = rctx.colorindex;
            if (rctx.bnimbte) {
                do {
                    if (rCArrby != null) {
                        g.setColor(rCArrby[ci++ & NUM_RANDOMCOLORMASK]);
                    }
                    g.drbwLine(x, y, x, y + size);
                    if ((x -= 3) < 0) x += rctx.mbxX;
                    if ((y -= 1) < 0) y += rctx.mbxY;
                } while (--numReps > 0);
            } else {
                do {
                    if (rCArrby != null) {
                        g.setColor(rCArrby[ci++ & NUM_RANDOMCOLORMASK]);
                    }
                    g.drbwLine(x, y, x, y + size);
                } while (--numReps > 0);
            }
            rctx.colorindex = ci;
            g.trbnslbte(-rctx.orgX, -rctx.orgY);
        }
    }

    public stbtic clbss FillRects extends RenderTests {
        public FillRects() {
            super(rendertestroot, "fillRect", "Fill Rectbngles");
        }

        public void runTest(Object ctx, int numReps) {
            RenderTests.Context rctx = (RenderTests.Context) ctx;
            int size = rctx.size;
            int x = rctx.initX;
            int y = rctx.initY;
            Grbphics g = rctx.grbphics;
            g.trbnslbte(rctx.orgX, rctx.orgY);
            Color rCArrby[] = rctx.colorlist;
            int ci = rctx.colorindex;
            if (rctx.bnimbte) {
                do {
                    if (rCArrby != null) {
                        g.setColor(rCArrby[ci++ & NUM_RANDOMCOLORMASK]);
                    }
                    g.fillRect(x, y, size, size);
                    if ((x -= 3) < 0) x += rctx.mbxX;
                    if ((y -= 1) < 0) y += rctx.mbxY;
                } while (--numReps > 0);
            } else {
                do {
                    if (rCArrby != null) {
                        g.setColor(rCArrby[ci++ & NUM_RANDOMCOLORMASK]);
                    }
                    g.fillRect(x, y, size, size);
                } while (--numReps > 0);
            }
            rctx.colorindex = ci;
            g.trbnslbte(-rctx.orgX, -rctx.orgY);
        }
    }

    public stbtic clbss DrbwRects extends RenderTests {
        public DrbwRects() {
            super(rendertestroot, "drbwRect", "Drbw Rectbngles");
        }

        public int pixelsTouched(GrbphicsTests.Context ctx) {
            int w = ctx.outdim.width;
            int h = ctx.outdim.height;
            if (w < 2 || h < 2) {
                // If one dimension is less thbn 2 then there is no
                // gbp in the middle, so we get b solid filled rectbngle.
                return w * h;
            }
            return (w * 2) + ((h - 2) * 2);
        }

        public void runTest(Object ctx, int numReps) {
            RenderTests.Context rctx = (RenderTests.Context) ctx;
            int size = rctx.size - 1;
            int x = rctx.initX;
            int y = rctx.initY;
            Grbphics g = rctx.grbphics;
            g.trbnslbte(rctx.orgX, rctx.orgY);
            Color rCArrby[] = rctx.colorlist;
            int ci = rctx.colorindex;
            if (rctx.bnimbte) {
                do {
                    if (rCArrby != null) {
                        g.setColor(rCArrby[ci++ & NUM_RANDOMCOLORMASK]);
                    }
                    g.drbwRect(x, y, size, size);
                    if ((x -= 3) < 0) x += rctx.mbxX;
                    if ((y -= 1) < 0) y += rctx.mbxY;
                } while (--numReps > 0);
            } else {
                do {
                    if (rCArrby != null) {
                        g.setColor(rCArrby[ci++ & NUM_RANDOMCOLORMASK]);
                    }
                    g.drbwRect(x, y, size, size);
                } while (--numReps > 0);
            }
            rctx.colorindex = ci;
            g.trbnslbte(-rctx.orgX, -rctx.orgY);
        }
    }

    public stbtic clbss FillOvbls extends RenderTests {
        public FillOvbls() {
            super(rendertestroot, "fillOvbl", "Fill Ellipses");
        }

        public int pixelsTouched(GrbphicsTests.Context ctx) {
            // Approximbted
            double xbxis = ctx.outdim.width / 2.0;
            double ybxis = ctx.outdim.height / 2.0;
            return (int) (xbxis * ybxis * Mbth.PI);
        }

        public void runTest(Object ctx, int numReps) {
            RenderTests.Context rctx = (RenderTests.Context) ctx;
            int size = rctx.size;
            int x = rctx.initX;
            int y = rctx.initY;
            Grbphics g = rctx.grbphics;
            g.trbnslbte(rctx.orgX, rctx.orgY);
            Color rCArrby[] = rctx.colorlist;
            int ci = rctx.colorindex;
            if (rctx.bnimbte) {
                do {
                    if (rCArrby != null) {
                        g.setColor(rCArrby[ci++ & NUM_RANDOMCOLORMASK]);
                    }
                    g.fillOvbl(x, y, size, size);
                    if ((x -= 3) < 0) x += rctx.mbxX;
                    if ((y -= 1) < 0) y += rctx.mbxY;
                } while (--numReps > 0);
            } else {
                do {
                    if (rCArrby != null) {
                        g.setColor(rCArrby[ci++ & NUM_RANDOMCOLORMASK]);
                    }
                    g.fillOvbl(x, y, size, size);
                } while (--numReps > 0);
            }
            rctx.colorindex = ci;
            g.trbnslbte(-rctx.orgX, -rctx.orgY);
        }
    }

    public stbtic clbss DrbwOvbls extends RenderTests {
        public DrbwOvbls() {
            super(rendertestroot, "drbwOvbl", "Drbw Ellipses");
        }

        public int pixelsTouched(GrbphicsTests.Context ctx) {
            /*
             * Approximbtion: We figured thbt the verticbl chord connecting
             * the +45 deg bnd -45 deg points on the ellipse is bbout
             * height/sqrt(2) pixels long.  Likewise, the horizontbl chord
             * connecting the +45 deg bnd +135 deg points on the ellipse is
             * bbout width/sqrt(2) pixels long.  Ebch of these chords hbs
             * b pbrbllel on the opposite side of the respective bxis (there
             * bre two horizontbl chords bnd two verticbl chords).  Altogether
             * this gives b rebsonbble bpproximbtion of the totbl number of
             * pixels touched by the ellipse, so we hbve:
             *     2*(w/sqrt(2)) + 2*(h/sqrt(2))
             *  == (2/sqrt(2))*(w+h)
             *  == (sqrt(2))*(w+h)
             */
            return (int)(Mbth.sqrt(2.0)*(ctx.outdim.width+ctx.outdim.height));
        }

        public void runTest(Object ctx, int numReps) {
            RenderTests.Context rctx = (RenderTests.Context) ctx;
            int size = rctx.size - 1;
            int x = rctx.initX;
            int y = rctx.initY;
            Grbphics g = rctx.grbphics;
            g.trbnslbte(rctx.orgX, rctx.orgY);
            Color rCArrby[] = rctx.colorlist;
            int ci = rctx.colorindex;
            if (rctx.bnimbte) {
                do {
                    if (rCArrby != null) {
                        g.setColor(rCArrby[ci++ & NUM_RANDOMCOLORMASK]);
                    }
                    g.drbwOvbl(x, y, size, size);
                    if ((x -= 3) < 0) x += rctx.mbxX;
                    if ((y -= 1) < 0) y += rctx.mbxY;
                } while (--numReps > 0);
            } else {
                do {
                    if (rCArrby != null) {
                        g.setColor(rCArrby[ci++ & NUM_RANDOMCOLORMASK]);
                    }
                    g.drbwOvbl(x, y, size, size);
                } while (--numReps > 0);
            }
            rctx.colorindex = ci;
            g.trbnslbte(-rctx.orgX, -rctx.orgY);
        }
    }

    public stbtic clbss FillPolys extends RenderTests {
        public FillPolys() {
            super(rendertestroot, "fillPoly", "Fill Hexbgonbl Polygons");
        }

        public int pixelsTouched(GrbphicsTests.Context ctx) {
            /*
             * The polygon is b hexbgon inscribed inside the squbre but
             * missing b tribngle bt ebch of the four corners of size
             * (w/4) by (h/2).
             *
             * Putting 2 of these tribngles together gives b rectbngle
             * of size (w/4) by (h/2).
             *
             * Putting 2 of these rectbngles together gives b totbl
             * missing rectbngle size of (w/2) by (h/2).
             *
             * Thus, exbctly one qubrter of the whole squbre is not
             * touched by the filled polygon.
             */
            int size = ctx.outdim.width * ctx.outdim.height;
            return size - (size / 4);
        }

        public void runTest(Object ctx, int numReps) {
            RenderTests.Context rctx = (RenderTests.Context) ctx;
            int size = rctx.size;
            int x = rctx.initX;
            int y = rctx.initY;
            int hexbX[] = new int[6];
            int hexbY[] = new int[6];
            Grbphics g = rctx.grbphics;
            g.trbnslbte(rctx.orgX, rctx.orgY);
            Color rCArrby[] = rctx.colorlist;
            int ci = rctx.colorindex;
            do {
                hexbX[0] = x;
                hexbX[1] = hexbX[5] = x+size/4;
                hexbX[2] = hexbX[4] = x+size-size/4;
                hexbX[3] = x+size;
                hexbY[1] = hexbY[2] = y;
                hexbY[0] = hexbY[3] = y+size/2;
                hexbY[4] = hexbY[5] = y+size;

                if (rCArrby != null) {
                    g.setColor(rCArrby[ci++ & NUM_RANDOMCOLORMASK]);
                }
                g.fillPolygon(hexbX, hexbY, 6);
                if ((x -= 3) < 0) x += rctx.mbxX;
                if ((y -= 1) < 0) y += rctx.mbxY;
            } while (--numReps > 0);
            rctx.colorindex = ci;
            g.trbnslbte(-rctx.orgX, -rctx.orgY);
        }
    }

    public stbtic clbss DrbwPolys extends RenderTests {
        public DrbwPolys() {
            super(rendertestroot, "drbwPoly", "Drbw Hexbgonbl Polygons");
        }

        public int pixelsTouched(GrbphicsTests.Context ctx) {
            /*
             * The two horizontbl segments hbve exbctly two pixels per column.
             * Since the dibgonbls bre more verticbl thbn horizontbl, using
             * h*2 would be b good wby to count the pixels in those sections.
             * We then hbve to figure out the size of the rembinder of the
             * horizontbl lines bt top bnd bottom to get the bnswer:
             *
             *     (dibgonbls less endpoints)*2 + (horizontbls)*2
             *
             *  or:
             *
             *     (h-2)*2 + ((x+w-1-w/4)-(x+w/4)+1)*2
             *
             *  since (w == h == size), we then hbve:
             *
             *     (size - size/4 - 1) * 4
             */
            int size = ctx.size;
            if (size <= 1) {
                return 1;
            } else {
                return (size - (size / 4) - 1) * 4;
            }
        }

        public void runTest(Object ctx, int numReps) {
            RenderTests.Context rctx = (RenderTests.Context) ctx;
            // subtrbct 1 to bccount for the fbct thbt lines bre drbwn to
            // bnd including the finbl coordinbte...
            int size = rctx.size - 1;
            int x = rctx.initX;
            int y = rctx.initY;
            int hexbX[] = new int[6];
            int hexbY[] = new int[6];
            Grbphics g = rctx.grbphics;
            g.trbnslbte(rctx.orgX, rctx.orgY);
            Color rCArrby[] = rctx.colorlist;
            int ci = rctx.colorindex;
            do {
                hexbX[0] = x;
                hexbX[1] = hexbX[5] = x+size/4;
                hexbX[2] = hexbX[4] = x+size-size/4;
                hexbX[3] = x+size;
                hexbY[1] = hexbY[2] = y;
                hexbY[0] = hexbY[3] = y+size/2;
                hexbY[4] = hexbY[5] = y+size;

                if (rCArrby != null) {
                    g.setColor(rCArrby[ci++ & NUM_RANDOMCOLORMASK]);
                }
                g.drbwPolygon(hexbX, hexbY, 6);
                if ((x -= 3) < 0) x += rctx.mbxX;
                if ((y -= 1) < 0) y += rctx.mbxY;
            } while (--numReps > 0);
            rctx.colorindex = ci;
            g.trbnslbte(-rctx.orgX, -rctx.orgY);
        }
    }

    public stbtic clbss FillCubics extends RenderTests {
        stbtic finbl double relTmbx = .5 - Mbth.sqrt(3) / 6;
        stbtic finbl double relYmbx = ((6*relTmbx - 9)*relTmbx + 3)*relTmbx;

        public FillCubics() {
            super(rendershbperoot, "fillCubic", "Fill Bezier Curves");
        }

        public int pixelsTouched(GrbphicsTests.Context ctx) {
            /*
             * The cubic only touches 2 qubdrbnts in the squbre, thus
             * bt lebst hblf of the squbre is unfilled.  The integrbls
             * to figure out the exbct breb bre not trivibl so for the
             * other 2 qubdrbnts, I'm going to guess thbt the cubic only
             * encloses somewhere between 1/2 bnd 3/4ths of the pixels
             * in those qubdrbnts - we will sby 5/8ths.  Thus only
             * 5/16ths of the totbl squbre is filled.
             */
            // Note: 2x2 ends up hitting exbctly 1 pixel...
            int size = ctx.size;
            if (size < 2) size = 2;
            return size * size * 5 / 16;
        }

        public stbtic clbss Context extends RenderTests.Context {
            CubicCurve2D curve = new CubicCurve2D.Flobt();
        }

        public GrbphicsTests.Context crebteContext() {
            return new FillCubics.Context();
        }

        public void runTest(Object ctx, int numReps) {
            FillCubics.Context cctx = (FillCubics.Context) ctx;
            int size = cctx.size;
            // Note: 2x2 ends up hitting exbctly 1 pixel...
            if (size < 2) size = 2;
            int x = cctx.initX;
            int y = cctx.initY;
            int cpoffset = (int) (size/relYmbx/2);
            CubicCurve2D curve = cctx.curve;
            Grbphics2D g2d = (Grbphics2D) cctx.grbphics;
            g2d.trbnslbte(cctx.orgX, cctx.orgY);
            Color rCArrby[] = cctx.colorlist;
            int ci = cctx.colorindex;
            do {
                curve.setCurve(x, y+size/2.0,
                               x+size/2.0, y+size/2.0-cpoffset,
                               x+size/2.0, y+size/2.0+cpoffset,
                               x+size, y+size/2.0);

                if (rCArrby != null) {
                    g2d.setColor(rCArrby[ci++ & NUM_RANDOMCOLORMASK]);
                }
                g2d.fill(curve);
                if ((x -= 3) < 0) x += cctx.mbxX;
                if ((y -= 1) < 0) y += cctx.mbxY;
            } while (--numReps > 0);
            cctx.colorindex = ci;
            g2d.trbnslbte(-cctx.orgX, -cctx.orgY);
        }
    }

    public stbtic clbss DrbwCubics extends RenderTests {
        stbtic finbl double relTmbx = .5 - Mbth.sqrt(3) / 6;
        stbtic finbl double relYmbx = ((6*relTmbx - 9)*relTmbx + 3)*relTmbx;

        public DrbwCubics() {
            super(rendershbperoot, "drbwCubic", "Drbw Bezier Curves");
        }

        public int pixelsTouched(GrbphicsTests.Context ctx) {
            // Gross bpproximbtion
            int size = ctx.size;
            if (size < 2) size = 2;
            return size;
        }

        public stbtic clbss Context extends RenderTests.Context {
            CubicCurve2D curve = new CubicCurve2D.Flobt();
        }

        public GrbphicsTests.Context crebteContext() {
            return new DrbwCubics.Context();
        }

        public void runTest(Object ctx, int numReps) {
            DrbwCubics.Context cctx = (DrbwCubics.Context) ctx;
            int size = cctx.size;
            // Note: 2x2 ends up hitting exbctly 1 pixel...
            if (size < 2) size = 2;
            int x = cctx.initX;
            int y = cctx.initY;
            int cpoffset = (int) (size/relYmbx/2);
            CubicCurve2D curve = cctx.curve;
            Grbphics2D g2d = (Grbphics2D) cctx.grbphics;
            g2d.trbnslbte(cctx.orgX, cctx.orgY);
            Color rCArrby[] = cctx.colorlist;
            int ci = cctx.colorindex;
            do {
                curve.setCurve(x, y+size/2.0,
                               x+size/2.0, y+size/2.0-cpoffset,
                               x+size/2.0, y+size/2.0+cpoffset,
                               x+size, y+size/2.0);

                if (rCArrby != null) {
                    g2d.setColor(rCArrby[ci++ & NUM_RANDOMCOLORMASK]);
                }
                g2d.drbw(curve);
                if ((x -= 3) < 0) x += cctx.mbxX;
                if ((y -= 1) < 0) y += cctx.mbxY;
            } while (--numReps > 0);
            cctx.colorindex = ci;
            g2d.trbnslbte(-cctx.orgX, -cctx.orgY);
        }
    }

    public stbtic clbss FillEllipse2Ds extends RenderTests {
        public FillEllipse2Ds() {
            super(rendershbperoot, "fillEllipse2D", "Fill Ellipse2Ds");
        }

        public int pixelsTouched(GrbphicsTests.Context ctx) {
            // Approximbted (copied from FillOvbls.pixelsTouched())
            double xbxis = ctx.outdim.width / 2.0;
            double ybxis = ctx.outdim.height / 2.0;
            return (int) (xbxis * ybxis * Mbth.PI);
        }

        public stbtic clbss Context extends RenderTests.Context {
            Ellipse2D ellipse = new Ellipse2D.Flobt();
        }

        public GrbphicsTests.Context crebteContext() {
            return new FillEllipse2Ds.Context();
        }

        public void runTest(Object ctx, int numReps) {
            FillEllipse2Ds.Context cctx = (FillEllipse2Ds.Context) ctx;
            int size = cctx.size;
            int x = cctx.initX;
            int y = cctx.initY;
            Ellipse2D ellipse = cctx.ellipse;
            Grbphics2D g2d = (Grbphics2D) cctx.grbphics;
            g2d.trbnslbte(cctx.orgX, cctx.orgY);
            Color rCArrby[] = cctx.colorlist;
            int ci = cctx.colorindex;
            do {
                if (rCArrby != null) {
                    g2d.setColor(rCArrby[ci++ & NUM_RANDOMCOLORMASK]);
                }
                ellipse.setFrbme(x, y, size, size);
                g2d.fill(ellipse);
                if ((x -= 3) < 0) x += cctx.mbxX;
                if ((y -= 1) < 0) y += cctx.mbxY;
            } while (--numReps > 0);
            cctx.colorindex = ci;
            g2d.trbnslbte(-cctx.orgX, -cctx.orgY);
        }
    }

    public stbtic clbss DrbwEllipse2Ds extends RenderTests {
        public DrbwEllipse2Ds() {
            super(rendershbperoot, "drbwEllipse2D", "Drbw Ellipse2Ds");
        }

        public int pixelsTouched(GrbphicsTests.Context ctx) {
            // Approximbted (copied from DrbwOvbls.pixelsTouched())
            return (int)(Mbth.sqrt(2.0)*(ctx.outdim.width+ctx.outdim.height));
        }

        public stbtic clbss Context extends RenderTests.Context {
            Ellipse2D ellipse = new Ellipse2D.Flobt();
        }

        public GrbphicsTests.Context crebteContext() {
            return new DrbwEllipse2Ds.Context();
        }

        public void runTest(Object ctx, int numReps) {
            DrbwEllipse2Ds.Context cctx = (DrbwEllipse2Ds.Context) ctx;
            int size = cctx.size;
            int x = cctx.initX;
            int y = cctx.initY;
            Ellipse2D ellipse = cctx.ellipse;
            Grbphics2D g2d = (Grbphics2D) cctx.grbphics;
            g2d.trbnslbte(cctx.orgX, cctx.orgY);
            Color rCArrby[] = cctx.colorlist;
            int ci = cctx.colorindex;
            do {
                if (rCArrby != null) {
                    g2d.setColor(rCArrby[ci++ & NUM_RANDOMCOLORMASK]);
                }
                ellipse.setFrbme(x, y, size, size);
                g2d.drbw(ellipse);
                if ((x -= 3) < 0) x += cctx.mbxX;
                if ((y -= 1) < 0) y += cctx.mbxY;
            } while (--numReps > 0);
            cctx.colorindex = ci;
            g2d.trbnslbte(-cctx.orgX, -cctx.orgY);
        }
    }
}
