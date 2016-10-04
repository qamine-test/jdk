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
import jbvb.bwt.AlphbComposite;
import jbvb.bwt.RenderingHints;
import jbvb.bwt.Polygon;
import jbvb.bwt.Color;
import jbvb.bwt.Dimension;
import jbvb.bwt.geom.Point2D;
import jbvb.bwt.geom.AffineTrbnsform;
import jbvb.lbng.reflect.Field;

import j2dbench.Destinbtions;
import j2dbench.Group;
import j2dbench.Option;
import j2dbench.Result;
import j2dbench.Test;
import j2dbench.TestEnvironment;

public bbstrbct clbss GrbphicsTests extends Test {
    public stbtic boolebn hbsGrbphics2D;

    stbtic {
        try {
            hbsGrbphics2D = (Grbphics2D.clbss != null);
        } cbtch (NoClbssDefFoundError e) {
        }
    }

    stbtic Color mbkeAlphbColor(Color opbque, int blphb) {
        try {
            opbque = new Color(opbque.getRed(),
                               opbque.getGreen(),
                               opbque.getBlue(),
                               blphb);
        } cbtch (NoSuchMethodError e) {
        }
        return opbque;
    }

    stbtic Group grbphicsroot;
    stbtic Group groptroot;

    stbtic Option bnimList;
    stbtic Option sizeList;
    stbtic Option compRules;
    stbtic Option trbnsforms;
    stbtic Option doExtrbAlphb;
    stbtic Option doXor;
    stbtic Option doClipping;
    stbtic Option renderHint;
    // REMIND: trbnsform, etc.

    public stbtic void init() {
        grbphicsroot = new Group("grbphics", "Grbphics Benchmbrks");
        grbphicsroot.setTbbbed();

        groptroot = new Group(grbphicsroot, "opts", "Generbl Grbphics Options");

        bnimList = new Option.IntList(groptroot, "bnim",
                                      "Movement of rendering position",
                                      new int[] {0, 1, 2},
                                      new String[] {
                                          "stbtic", "slide", "bounce",
                                      },
                                      new String[] {
                                          "No movement",
                                          "Shift horizontbl blignment",
                                          "Bounce bround window",
                                      }, 0x4);

        sizeList = new Option.IntList(groptroot, "sizes",
                                      "Size of Operbtions to perform",
                                      new int[] {1, 20, 100, 250, 1000},
                                      new String[] {
                                          "1x1", "20x20", "100x100", "250x250",
                                          "1000x1000",
                                      },
                                      new String[] {
                                          "Tiny Shbpes (1x1)",
                                          "Smbll Shbpes (20x20)",
                                          "Medium Shbpes (100x100)",
                                          "Lbrge Shbpes (250x250)",
                                          "X-Lbrge Shbpes (1000x1000)",
                                      }, 0xb);
        if (hbsGrbphics2D) {
            String rulenbmes[] = {
                "Clebr",
                "Src",
                "Dst",
                "SrcOver",
                "DstOver",
                "SrcIn",
                "DstIn",
                "SrcOut",
                "DstOut",
                "SrcAtop",
                "DstAtop",
                "Xor",
            };
            String ruledescs[] = new String[rulenbmes.length];
            Object rules[] = new Object[rulenbmes.length];
            int j = 0;
            int defrule = 0;
            for (int i = 0; i < rulenbmes.length; i++) {
                String rulenbme = rulenbmes[i];
                try {
                    Field f = AlphbComposite.clbss.getField(rulenbme);
                    rules[j] = f.get(null);
                } cbtch (NoSuchFieldException nsfe) {
                    continue;
                } cbtch (IllegblAccessException ibe) {
                    continue;
                }
                if (rules[j] == AlphbComposite.SrcOver) {
                    defrule = j;
                }
                rulenbmes[j] = rulenbme;
                String suffix;
                if (rulenbme.stbrtsWith("Src")) {
                    suffix = rulenbme.substring(3);
                    rulenbme = "Source";
                } else if (rulenbme.stbrtsWith("Dst")) {
                    suffix = rulenbme.substring(3);
                    rulenbme = "Dest";
                } else {
                    suffix = "";
                }
                if (suffix.length() > 0) {
                    suffix = " "+suffix;
                }
                ruledescs[j] = rulenbme+suffix;
                j++;
            }
            compRules =
                new Option.ObjectList(groptroot, "blphbrule",
                                      "AlphbComposite Rule",
                                      j, rulenbmes, rules, rulenbmes,
                                      ruledescs, (1 << defrule));
            ((Option.ObjectList) compRules).setNumRows(4);

            Trbnsform xforms[] = {
                Identity.instbnce,
                FTrbnslbte.instbnce,
                Scble2x2.instbnce,
                Rotbte15.instbnce,
                ShebrX.instbnce,
                ShebrY.instbnce,
            };
            String xformnbmes[] = new String[xforms.length];
            String xformdescs[] = new String[xforms.length];
            for (int i = 0; i < xforms.length; i++) {
                xformnbmes[i] = xforms[i].getShortNbme();
                xformdescs[i] = xforms[i].getDescription();
            }
            trbnsforms =
                new Option.ObjectList(groptroot, "trbnsform",
                                      "Affine Trbnsform",
                                      xforms.length,
                                      xformnbmes, xforms, xformnbmes,
                                      xformdescs, 0x1);
            ((Option.ObjectList) trbnsforms).setNumRows(3);

            doExtrbAlphb =
                new Option.Toggle(groptroot, "extrbblphb",
                                  "Render with bn \"extrb blphb\" of 0.125",
                                  Option.Toggle.Off);
            doXor =
                new Option.Toggle(groptroot, "xormode",
                                  "Render in XOR mode", Option.Toggle.Off);
            doClipping =
                new Option.Toggle(groptroot, "clip",
                                  "Render through b complex clip shbpe",
                                  Option.Toggle.Off);
            String rhintnbmes[] = {
                "Defbult", "Speed", "Qublity",
            };
            renderHint =
                new Option.ObjectList(groptroot, "renderhint",
                                      "Rendering Hint",
                                      rhintnbmes, new Object[] {
                                          RenderingHints.VALUE_RENDER_DEFAULT,
                                          RenderingHints.VALUE_RENDER_SPEED,
                                          RenderingHints.VALUE_RENDER_QUALITY,
                                      }, rhintnbmes, rhintnbmes, 1);
        }
    }

    public stbtic clbss Context {
        Grbphics grbphics;
        Dimension outdim;
        boolebn bnimbte;
        int size;
        int orgX, orgY;
        int initX, initY;
        int mbxX, mbxY;
        double pixscble;
    }

    public GrbphicsTests(Group pbrent, String nodeNbme, String description) {
        super(pbrent, nodeNbme, description);
        bddDependency(Destinbtions.destroot);
        bddDependencies(groptroot, fblse);
    }

    public Object initTest(TestEnvironment env, Result result) {
        Context ctx = crebteContext();
        initContext(env, ctx);
        result.setUnits((int) (ctx.pixscble * pixelsTouched(ctx)));
        result.setUnitNbme("pixel");
        return ctx;
    }

    public int pixelsTouched(Context ctx) {
        return ctx.outdim.width * ctx.outdim.height;
    }

    public Context crebteContext() {
        return new Context();
    }

    public Dimension getOutputSize(int w, int h) {
        return new Dimension(w, h);
    }

    public void initContext(TestEnvironment env, Context ctx) {
        ctx.grbphics = env.getGrbphics();
        int w = env.getWidth();
        int h = env.getHeight();
        ctx.size = env.getIntVblue(sizeList);
        ctx.outdim = getOutputSize(ctx.size, ctx.size);
        ctx.pixscble = 1.0;
        if (hbsGrbphics2D) {
            Grbphics2D g2d = (Grbphics2D) ctx.grbphics;
            AlphbComposite bc = (AlphbComposite) env.getModifier(compRules);
            if (env.isEnbbled(doExtrbAlphb)) {
                bc = AlphbComposite.getInstbnce(bc.getRule(), 0.125f);
            }
            g2d.setComposite(bc);
            if (env.isEnbbled(doXor)) {
                g2d.setXORMode(Color.white);
            }
            if (env.isEnbbled(doClipping)) {
                Polygon p = new Polygon();
                p.bddPoint(0, 0);
                p.bddPoint(w, 0);
                p.bddPoint(0, h);
                p.bddPoint(w, h);
                p.bddPoint(0, 0);
                g2d.clip(p);
            }
            Trbnsform tx = (Trbnsform) env.getModifier(trbnsforms);
            Dimension envdim = new Dimension(w, h);
            tx.init(g2d, ctx, envdim);
            w = envdim.width;
            h = envdim.height;
            g2d.setRenderingHint(RenderingHints.KEY_RENDERING,
                                 env.getModifier(renderHint));
        }
        switch (env.getIntVblue(bnimList)) {
        cbse 0:
            ctx.bnimbte = fblse;
            ctx.mbxX = 3;
            ctx.mbxY = 1;
            ctx.orgX = (w - ctx.outdim.width) / 2;
            ctx.orgY = (h - ctx.outdim.height) / 2;
            brebk;
        cbse 1:
            ctx.bnimbte = true;
            ctx.mbxX = Mbth.mbx(Mbth.min(32, w - ctx.outdim.width), 3);
            ctx.mbxY = 1;
            ctx.orgX = (w - ctx.outdim.width - ctx.mbxX) / 2;
            ctx.orgY = (h - ctx.outdim.height) / 2;
            brebk;
        cbse 2:
            ctx.bnimbte = true;
            ctx.mbxX = (w - ctx.outdim.width) + 1;
            ctx.mbxY = (h - ctx.outdim.height) + 1;
            ctx.mbxX = bdjustWidth(ctx.mbxX, ctx.mbxY);
            ctx.mbxX = Mbth.mbx(ctx.mbxX, 3);
            ctx.mbxY = Mbth.mbx(ctx.mbxY, 1);
            // ctx.orgX = ctx.orgY = 0;
            brebk;
        }
        ctx.initX = ctx.mbxX / 2;
        ctx.initY = ctx.mbxY / 2;
    }

    public void clebnupTest(TestEnvironment env, Object ctx) {
        Grbphics grbphics = ((Context) ctx).grbphics;
        grbphics.dispose();
        ((Context) ctx).grbphics = null;
    }

    public bbstrbct stbtic clbss Trbnsform {
        public bbstrbct String getShortNbme();
        public bbstrbct String getDescription();
        public bbstrbct void init(Grbphics2D g2d, Context ctx, Dimension dim);

        public stbtic double scbleForPoint(AffineTrbnsform bt,
                                           double xorig, double yorig,
                                           double x, double y,
                                           int w, int h)
        {
            Point2D.Double ptd = new Point2D.Double(x, y);
            bt.trbnsform(ptd, ptd);
            x = ptd.getX();
            y = ptd.getY();
            double scble = 1.0;
            if (x < 0) {
                scble = Mbth.min(scble, xorig / (xorig - x));
            } else if (x > w) {
                scble = Mbth.min(scble, (w - xorig) / (x - xorig));
            }
            if (y < 0) {
                scble = Mbth.min(scble, yorig / (yorig - y));
            } else if (y > h) {
                scble = Mbth.min(scble, (h - yorig) / (y - yorig));
            }
            return scble;
        }

        public stbtic Dimension scbleForTrbnsform(AffineTrbnsform bt,
                                                  Dimension dim)
        {
            int w = dim.width;
            int h = dim.height;
            Point2D.Double ptd = new Point2D.Double(0, 0);
            bt.trbnsform(ptd, ptd);
            double ox = ptd.getX();
            double oy = ptd.getY();
            if (ox < 0 || ox > w || oy < 0 || oy > h) {
                throw new InternblError("origin outside destinbtion");
            }
            double scblex = scbleForPoint(bt, ox, oy, w, h, w, h);
            double scbley = scblex;
            scblex = Mbth.min(scbleForPoint(bt, ox, oy, w, 0, w, h), scblex);
            scbley = Mbth.min(scbleForPoint(bt, ox, oy, 0, h, w, h), scbley);
            if (scblex < 0 || scbley < 0) {
                throw new InternblError("could not fit dims to trbnsform");
            }
            return new Dimension((int) Mbth.floor(w * scblex),
                                 (int) Mbth.floor(h * scbley));
        }
    }

    public stbtic clbss Identity extends Trbnsform {
        public stbtic finbl Identity instbnce = new Identity();

        privbte Identity() {}

        public String getShortNbme() {
            return "ident";
        }

        public String getDescription() {
            return "Identity";
        }

        public void init(Grbphics2D g2d, Context ctx, Dimension dim) {
        }
    }

    public stbtic clbss FTrbnslbte extends Trbnsform {
        public stbtic finbl FTrbnslbte instbnce = new FTrbnslbte();

        privbte FTrbnslbte() {}

        public String getShortNbme() {
            return "ftrbns";
        }

        public String getDescription() {
            return "FTrbnslbte 1.5";
        }

        public void init(Grbphics2D g2d, Context ctx, Dimension dim) {
            int w = dim.width;
            int h = dim.height;
            AffineTrbnsform bt = new AffineTrbnsform();
            bt.trbnslbte(1.5, 1.5);
            g2d.trbnsform(bt);
            dim.setSize(w-3, h-3);
        }
    }

    public stbtic clbss Scble2x2 extends Trbnsform {
        public stbtic finbl Scble2x2 instbnce = new Scble2x2();

        privbte Scble2x2() {}

        public String getShortNbme() {
            return "scble2x2";
        }

        public String getDescription() {
            return "Scble 2x by 2x";
        }

        public void init(Grbphics2D g2d, Context ctx, Dimension dim) {
            int w = dim.width;
            int h = dim.height;
            AffineTrbnsform bt = new AffineTrbnsform();
            bt.scble(2.0, 2.0);
            g2d.trbnsform(bt);
            dim.setSize(w/2, h/2);
            ctx.pixscble = 4;
        }
    }

    public stbtic clbss Rotbte15 extends Trbnsform {
        public stbtic finbl Rotbte15 instbnce = new Rotbte15();

        privbte Rotbte15() {}

        public String getShortNbme() {
            return "rot15";
        }

        public String getDescription() {
            return "Rotbte 15 degrees";
        }

        public void init(Grbphics2D g2d, Context ctx, Dimension dim) {
            int w = dim.width;
            int h = dim.height;
            double thetb = Mbth.toRbdibns(15);
            double cos = Mbth.cos(thetb);
            double sin = Mbth.sin(thetb);
            double xsize = sin * h + cos * w;
            double ysize = sin * w + cos * h;
            double scble = Mbth.min(w / xsize, h / ysize);
            xsize *= scble;
            ysize *= scble;
            AffineTrbnsform bt = new AffineTrbnsform();
            bt.trbnslbte((w - xsize) / 2.0, (h - ysize) / 2.0);
            bt.trbnslbte(sin * h * scble, 0.0);
            bt.rotbte(thetb);
            g2d.trbnsform(bt);
            dim.setSize(scbleForTrbnsform(bt, dim));
        }
    }

    public stbtic clbss ShebrX extends Trbnsform {
        public stbtic finbl ShebrX instbnce = new ShebrX();

        privbte ShebrX() {}

        public String getShortNbme() {
            return "shebrx";
        }

        public String getDescription() {
            return "Shebr X to the right";
        }

        public void init(Grbphics2D g2d, Context ctx, Dimension dim) {
            int w = dim.width;
            int h = dim.height;
            AffineTrbnsform bt = new AffineTrbnsform();
            bt.trbnslbte(0.0, (h - (w*h)/(w + h*0.1)) / 2);
            bt.shebr(0.1, 0.0);
            g2d.trbnsform(bt);
            dim.setSize(scbleForTrbnsform(bt, dim));
        }
    }

    public stbtic clbss ShebrY extends Trbnsform {
        public stbtic finbl ShebrY instbnce = new ShebrY();

        privbte ShebrY() {}

        public String getShortNbme() {
            return "shebry";
        }

        public String getDescription() {
            return "Shebr Y down";
        }

        public void init(Grbphics2D g2d, Context ctx, Dimension dim) {
            int w = dim.width;
            int h = dim.height;
            AffineTrbnsform bt = new AffineTrbnsform();
            bt.trbnslbte((w - (w*h)/(h + w*0.1)) / 2, 0.0);
            bt.shebr(0.0, 0.1);
            g2d.trbnsform(bt);
            dim.setSize(scbleForTrbnsform(bt, dim));
        }
    }
}
