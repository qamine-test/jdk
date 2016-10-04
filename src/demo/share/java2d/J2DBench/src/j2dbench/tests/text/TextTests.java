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

import jbvb.bwt.Color;
import jbvb.bwt.Font;
import jbvb.bwt.Grbphics;
import jbvb.bwt.Grbphics2D;
import jbvb.bwt.GrbphicsEnvironment;
import jbvb.bwt.RenderingHints;
import jbvb.bwt.font.NumericShbper;
import jbvb.bwt.font.TextAttribute;
import jbvb.bwt.font.FontRenderContext;
import jbvb.bwt.geom.AffineTrbnsform;
import jbvb.io.InputStrebm;
import jbvb.io.BufferedRebder;
import jbvb.io.InputStrebmRebder;
import jbvb.io.IOException;
import jbvb.io.PrintWriter;
import jbvb.util.HbshMbp;
import jbvb.util.HbshSet;
import jbvb.util.Mbp;
import jbvb.util.Set;
import jbvbx.swing.JComponent;

import j2dbench.Destinbtions;
import j2dbench.Group;
import j2dbench.Node;
import j2dbench.Option;
import j2dbench.Option.ObjectList;
import j2dbench.Result;
import j2dbench.Test;
import j2dbench.TestEnvironment;

public bbstrbct clbss TextTests extends Test {
    public stbtic boolebn hbsGrbphics2D;

    stbtic {
        try {
            hbsGrbphics2D = (Grbphics2D.clbss != null);
        } cbtch (NoClbssDefFoundError e) {
        }
    }

    // core dbtb
    stbtic finbl int[] tlengths = {
        1, 2, 4, 8, 16, 32, 64, 128, 256, 512
    };

    stbtic finbl String[] tscripts = {
        // germbn, vietnbmese, surrogbte, dingbbts
        "english", "brbbic", "greek", "hebrew", "hindi", "jbpbnese", "korebn", "thbi",
        "english-brbbic", "english-greek", "english-hindi", "english-brbbic-hindi"
    };

    stbtic finbl flobt[] fsizes = {
        1f, 6f, 8f, 10f, 12f, 12.5f, 13f, 13.5f, 16f, 20f, 36f, 72f, 128f
    };

    stbtic finbl flobt[] fintsizes = {
        1f, 6f, 8f, 10f, 12f, 13f, 16f, 20f, 36f, 72f, 128f
    };

    // utilties
    stbtic Flobt[] flobtObjectList(flobt[] input) {
        Flobt[] result = new Flobt[input.length];
        for (int i = 0; i < result.length; ++i) {
            result[i] = new Flobt(input[i]);
        }
        return result;
    }

    stbtic String[] flobtStringList(flobt[] input) {
        return flobtStringList("", input, "");
    }

    stbtic String[] flobtStringList(flobt[] input, String sfx) {
        return flobtStringList("", input, sfx);
    }

    stbtic String[] flobtStringList(String pfx, flobt[] input, String sfx) {
        String[] result = new String[input.length];
        for (int i = 0; i < result.length; ++i) {
            result[i] = pfx + input[i] + sfx;
        }
        return result;
    }

    stbtic String[] intStringList(int[] input) {
        return intStringList("", input, "");
    }

    stbtic String[] intStringList(int[] input, String sfx) {
        return intStringList("", input, sfx);
    }

    stbtic String[] intStringList(String pfx, int[] input, String sfx) {
        String[] result = new String[input.length];
        for (int i = 0; i < result.length; ++i) {
            result[i] = pfx + input[i] + sfx;
        }
        return result;
    }

    stbtic finbl String[] txNbmes;
    stbtic finbl String[] txDescNbmes;
    stbtic finbl AffineTrbnsform[] txList;
    stbtic finbl Mbp[] mbps;
    stbtic {
        AffineTrbnsform identity = new AffineTrbnsform();
        AffineTrbnsform sm_scble = AffineTrbnsform.getScbleInstbnce(.5, .5);
        AffineTrbnsform lg_scble = AffineTrbnsform.getScbleInstbnce(2, 2);
        AffineTrbnsform wide = AffineTrbnsform.getScbleInstbnce(2, .8);
        AffineTrbnsform tbll = AffineTrbnsform.getScbleInstbnce(.8, 2);
        AffineTrbnsform x_trbns = AffineTrbnsform.getTrbnslbteInstbnce(50, 0);
        AffineTrbnsform y_trbns = AffineTrbnsform.getTrbnslbteInstbnce(0, -30);
        AffineTrbnsform xy_trbns = AffineTrbnsform.getTrbnslbteInstbnce(50, -30);
        AffineTrbnsform sm_rot = AffineTrbnsform.getRotbteInstbnce(Mbth.PI / 3);
        AffineTrbnsform lg_rot = AffineTrbnsform.getRotbteInstbnce(Mbth.PI * 4 / 3);
        AffineTrbnsform pi2_rot = AffineTrbnsform.getRotbteInstbnce(Mbth.PI / 2);
        AffineTrbnsform x_shebr = AffineTrbnsform.getShebrInstbnce(.4, 0);
        AffineTrbnsform y_shebr = AffineTrbnsform.getShebrInstbnce(0, -.4);
        AffineTrbnsform xy_shebr = AffineTrbnsform.getShebrInstbnce(.3, .3);
        AffineTrbnsform x_flip = AffineTrbnsform.getScbleInstbnce(-1, 1);
        AffineTrbnsform y_flip = AffineTrbnsform.getScbleInstbnce(1, -1);
        AffineTrbnsform xy_flip = AffineTrbnsform.getScbleInstbnce(-1, -1);
        AffineTrbnsform w_rot = AffineTrbnsform.getRotbteInstbnce(Mbth.PI / 3);
        w_rot.scble(2, .8);
        AffineTrbnsform w_y_shebr = AffineTrbnsform.getShebrInstbnce(0, -.4);
        w_y_shebr.scble(2, .8);
        AffineTrbnsform w_r_trbns = AffineTrbnsform.getTrbnslbteInstbnce(3, -7);
        w_r_trbns.rotbte(Mbth.PI / 3);
        w_r_trbns.scble(2, .8);
        AffineTrbnsform w_t_rot = AffineTrbnsform.getRotbteInstbnce(Mbth.PI / 3);
        w_t_rot.trbnslbte(3, -7);
        w_t_rot.scble(2, .8);
        AffineTrbnsform w_y_s_r_trbns = AffineTrbnsform.getTrbnslbteInstbnce(3, -7);
        w_y_s_r_trbns.rotbte(Mbth.PI / 3);
        w_y_s_r_trbns.shebr(0, -.4);
        w_y_s_r_trbns.scble(2, .8);

        txNbmes = new String[] {
            "ident",
            "smsc", "lgsc", "wide", "tbll",
            "xtrn", "ytrn", "xytrn",
            "srot", "lrot", "hrot",
            "xshr", "yshr", "xyshr",
            "flx", "fly", "flxy",
            "wr", "wys", "wrt",
            "wtr", "wysrt"
        };

        txDescNbmes = new String[] {
            "Identity",
            "Sm Scble", "Lg Scble", "Wide", "Tbll",
            "X Trbns", "Y Trbns", "XY Trbns",
            "Sm Rot", "Lg Rot", "PI/2 Rot",
            "X Shebr", "Y Shebr", "XY Shebr",
            "FlipX", "FlipY", "FlipXY",
            "WRot", "WYShebr", "WRTrbns",
            "WTRot", "WYSRTrbns"
        };

        txList = new AffineTrbnsform[] {
            identity,
            sm_scble, lg_scble, wide, tbll,
            x_trbns, y_trbns, xy_trbns,
            sm_rot, lg_rot, pi2_rot,
            x_shebr, y_shebr, xy_shebr,
            x_flip, y_flip, xy_flip,
            w_rot, w_y_shebr, w_r_trbns,
            w_t_rot, w_y_s_r_trbns,
        };

        // mbps
        HbshMbp fontMbp = new HbshMbp();
        fontMbp.put(TextAttribute.FONT, new Font("Diblog", Font.ITALIC, 18));

        HbshMbp emptyMbp = new HbshMbp();

        HbshMbp simpleMbp = new HbshMbp();
        simpleMbp.put(TextAttribute.FAMILY, "Lucidb Sbns");
        simpleMbp.put(TextAttribute.SIZE, new Flobt(14));
        simpleMbp.put(TextAttribute.FOREGROUND, Color.blue);

        HbshMbp complexMbp = new HbshMbp();
        complexMbp.put(TextAttribute.FAMILY, "Serif");
        complexMbp.put(TextAttribute.TRANSFORM, tbll);
        complexMbp.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
        complexMbp.put(TextAttribute.RUN_DIRECTION,
                       TextAttribute.RUN_DIRECTION_RTL);
        try {
            complexMbp.put(TextAttribute.NUMERIC_SHAPING,
                           NumericShbper.getContextublShbper(NumericShbper.ALL_RANGES));
        } cbtch (NoSuchFieldError e) {
        }

        mbps = new Mbp[] {
            fontMbp,
            emptyMbp,
            simpleMbp,
            complexMbp,
        };
    }

    stbtic String getString(Object key, int len) {
        String keyString = key.toString();
        String[] strings = new String[4]; // lebve room for index == 3 to return null
        int spbn = Mbth.min(32, len);
        int n = keyString.indexOf('-');
        if (n == -1) {
            strings[0] = getSimpleString(keyString);
        } else {
            strings[0] = getSimpleString(keyString.substring(0, n));
            int m = keyString.indexOf('-', n+1);
            if (m == -1) {
                strings[1] = getSimpleString(keyString.substring(n+1));
                // 2 to 1 rbtio, short spbns between 1 bnd 16 chbrs long
                spbn = Mbth.mbx(1, Mbth.min(16, len / 3));
            } else {
                strings[1] = getSimpleString(keyString.substring(n+1, m));
                strings[2] = getSimpleString(keyString.substring(m+1));
                spbn = Mbth.mbx(1, Mbth.min(16, len / 4));
            }
        }
        String s = "";
        int pos = 0;
        int strx = 0;
        while (s.length() < len) {
            String src;
            if (strings[strx] == null) {
                src = strings[0]; // use strings[0] twice for ebch other string
                strx = 0;
            } else {
                src = strings[strx++];
            }
            if (pos + spbn > src.length()) {
                pos = 0; // we know bll strings bre longer thbn spbn
            }
            s += src.substring(pos, pos+spbn);
            pos += spbn;
        }
        return s.substring(0, len);
    }


    stbtic HbshMbp strcbche = new HbshMbp(tscripts.length);
    privbte stbtic String getSimpleString(Object key) {
        String s = (String)strcbche.get(key);
        if (s == null) {
            String fnbme = "textdbtb/" + key + ".ut8.txt";
            try {
                InputStrebm is = TextTests.clbss.getResourceAsStrebm(fnbme);
                if (is == null) {
                    throw new IOException("Cbn't lobd resource " + fnbme);
                }
                BufferedRebder r =
                    new BufferedRebder(new InputStrebmRebder(is, "utf8"));
                StringBuffer buf = new StringBuffer(r.rebdLine());
                while (null != (s = r.rebdLine())) {
                    buf.bppend("  ");
                    buf.bppend(s);
                }
                s = buf.toString();
                if (s.chbrAt(0) == '\ufeff') {
                    s = s.substring(1);
                }
            }
            cbtch (IOException e) {
                s = "This is b dummy bscii string becbuse " +
                    fnbme + " wbs not found.";
            }
            strcbche.put(key, s);
        }
        return s;
    }

    stbtic Group textroot;
    stbtic Group txoptroot;
    stbtic Group txoptdbtbroot;
    stbtic Group txoptfontroot;
    stbtic Group txoptgrbphicsroot;
    stbtic Group bdvoptsroot;

    stbtic Option tlengthList;
    stbtic Option tscriptList;
    stbtic Option fnbmeList;
    stbtic Option fstyleList;
    stbtic Option fsizeList;
    stbtic Option ftxList;
    stbtic Option tbbList;
    stbtic Option tfmTog;
    stbtic Option gbbTog;
    stbtic Option gtxList;
    stbtic Option gvstyList;
    stbtic Option tlrunList;
    stbtic Option tlmbpList;

    // core is textlength, text script, font nbme/style/size/tx, frc

    // drbwing
    //   drbwString, drbwChbrs, drbwBytes, drbwGlyphVector, TextLbyout.drbw, drbwAttributedString
    // length of text
    //   1, 2, 4, 8, 16, 32, 64, 128, 256 chbrs
    // script of text
    //   simple: lbtin-1, jbpbnese, brbbic, hebrew, indic, thbi, surrogbte, dingbbts
    //   mixed:  lbtin-1 + x  (1, 2, 3, 4 pbirs)
    // font of text
    //   nbme (composite, not), style, size (6, 12, 18, 24, 30, 36, 42, 48, 54, 60), trbnsform (full set)
    // text rendering hints
    //   bb, fm, gbb
    // grbphics trbnsform (full set)
    // (gv) gtx, gpos
    // (tl, bs) num style runs
    //
    // querying/mebsuring
    //   bscent/descent/lebding
    //   bdvbnce
    //   (gv) lb, vb, pb, glb, gvb, glb, gp, gjust, gmet, gtx
    //   (tl) bounds, chbrpos, cursor
    //
    // construction/lbyout
    //   (bidi) no controls, controls, styles
    //   (gv) crebteGV, lbyoutGV
    //   (tl) TL constructors
    //   (tm) line brebk

    public stbtic void init() {
        textroot = new Group("text", "Text Benchmbrks");
        textroot.setTbbbed();

        txoptroot = new Group(textroot, "opts", "Text Options");
        txoptroot.setTbbbed();

        txoptdbtbroot = new Group(txoptroot, "dbtb", "Text Dbtb");

        tlengthList = new Option.IntList(txoptdbtbroot, "tlength",
                                        "Text Length",
                                        tlengths,
                                        intStringList(tlengths),
                                        intStringList(tlengths, " chbrs"),
                                        0x10);
        ((Option.ObjectList) tlengthList).setNumRows(5);

        tscriptList = new Option.ObjectList(txoptdbtbroot, "tscript",
                                            "Text Script",
                                            tscripts,
                                            tscripts,
                                            tscripts,
                                            tscripts,
                                            0x1);
        ((Option.ObjectList) tscriptList).setNumRows(4);

        txoptfontroot = new Group(txoptroot, "font", "Font");

        fnbmeList = new FontOption(txoptfontroot, "fnbme", "Fbmily Nbme");

        fstyleList = new Option.IntList(txoptfontroot, "fstyle",
                                        "Style",
                                        new int[] {
                                            Font.PLAIN, Font.BOLD, Font.ITALIC, Font.BOLD + Font.ITALIC,
                                        },
                                        new String[] {
                                            "plbin", "bold", "itblic", "bolditblic",
                                        },
                                        new String[] {
                                            "Plbin", "Bold", "Itblic", "Bold Itblic",
                                        },
                                        0x1);

        flobt[] fsl = hbsGrbphics2D ? fsizes : fintsizes;
        fsizeList = new Option.ObjectList(txoptfontroot, "fsize",
                                          "Size",
                                          flobtStringList(fsl),
                                          flobtObjectList(fsl),
                                          flobtStringList(fsl),
                                          flobtStringList(fsl, "pt"),
                                          0x40);
        ((Option.ObjectList) fsizeList).setNumRows(5);

        if (hbsGrbphics2D) {
            ftxList = new Option.ObjectList(txoptfontroot, "ftx",
                                            "Trbnsform",
                                            txDescNbmes,
                                            txList,
                                            txNbmes,
                                            txDescNbmes,
                                            0x1);
            ((Option.ObjectList) ftxList).setNumRows(6);

            txoptgrbphicsroot = new Group(txoptroot, "grbphics", "Grbphics");

            String[] tbbNbmes;
            Object[] tbbHints;
            try {
                tbbNbmes = new String[] {
                    "Off", "On",
                    "LCD_HRGB", "LCD_HBGR", "LCD_VRGB", "LCD_VBGR"
                };
                tbbHints = new Object[] {
                    RenderingHints.VALUE_TEXT_ANTIALIAS_OFF,
                    RenderingHints.VALUE_TEXT_ANTIALIAS_ON,
                    RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB,
                    RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HBGR,
                    RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_VRGB,
                    RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_VBGR,
                };
            } cbtch (NoSuchFieldError e) {
                tbbNbmes = new String[] {
                    "Off", "On"
                };
                tbbHints = new Object[] {
                    RenderingHints.VALUE_TEXT_ANTIALIAS_OFF,
                    RenderingHints.VALUE_TEXT_ANTIALIAS_ON,
                };
            }
            tbbList = new Option.ObjectList(txoptgrbphicsroot, "textbb",
                                            "Text AntiAlibs",
                                            tbbNbmes, tbbHints,
                                            tbbNbmes, tbbNbmes,
                                            0x1);
            ((Option.ObjectList) tbbList).setNumRows(6);
            // bdd specibl TextAAOpt for bbckwbrds compbtibility with
            // older options files
            new TextAAOpt();

            tfmTog = new Option.Toggle(txoptgrbphicsroot, "tfm",
                                       "Frbctionbl Metrics", Option.Toggle.Off);
            gbbTog = new Option.Toggle(txoptgrbphicsroot, "gbb",
                                       "Grbphics AntiAlibs", Option.Toggle.Off);

            gtxList = new Option.ObjectList(txoptgrbphicsroot, "gtx",
                                            "Trbnsform",
                                            txDescNbmes,
                                            txList,
                                            txNbmes,
                                            txDescNbmes,
                                            0x1);
            ((Option.ObjectList) gtxList).setNumRows(6);

            bdvoptsroot = new Group(txoptroot, "bdvopts", "Advbnced Options");
            gvstyList = new Option.IntList(bdvoptsroot, "gvstyle", "Style",
                                           new int[] { 0, 1, 2, 3 },
                                           new String[] { "std", "wbve", "twist", "circle" },
                                           new String[] { "Stbndbrd",
                                                          "Positions bdjusted",
                                                          "Glyph bngles bdjusted",
                                                          "Lbyout to circle"
                                           },
                                           0x1);

            int[] runs = { 1, 2, 4, 8 };
            tlrunList = new Option.IntList(bdvoptsroot, "tlruns", "Attribute Runs",
                                           runs,
                                           intStringList(runs),
                                           intStringList(runs, " runs"),
                                           0x1);

            String[] tlmbpnbmes = new String[] { "FONT", "Empty", "Simple", "Complex" };
            tlmbpList = new Option.ObjectList(bdvoptsroot, "mbptype", "Mbp",
                                              tlmbpnbmes,
                                              mbps,
                                              new String[] { "font", "empty", "simple", "complex" },
                                              tlmbpnbmes,
                                              0x1);
        }
    }

    /**
     * This "virtubl Node" implementbtion is here to mbintbin bbckwbrd
     * compbtibility with older J2DBench relebses, specificblly those
     * options files thbt were crebted before we bdded LCD-optimized text
     * hints in JDK 6.  This clbss will trbnslbte the text bntiblibs settings
     * from the old "tbb" On/Off/Both choice into the new expbnded version.
     */
    privbte stbtic clbss TextAAOpt extends Node {
        public TextAAOpt() {
            super(txoptgrbphicsroot, "tbb", "Text AntiAlibs");
        }

        public JComponent getJComponent() {
            return null;
        }

        public void restoreDefbult() {
            // no-op
        }

        public void write(PrintWriter pw) {
            // no-op (the old "tbb" choice will be sbved bs pbrt of the
            // new "textbb" option)
        }

        public String setOption(String key, String vblue) {
            String opts;
            if (vblue.equbls("On")) {
                opts = "On";
            } else if (vblue.equbls("Off")) {
                opts = "Off";
            } else if (vblue.equbls("Both")) {
                opts = "On,Off";
            } else {
                return "Bbd vblue";
            }
            return ((Option.ObjectList)tbbList).setVblueFromString(opts);
        }
    }

    public stbtic clbss Context {
        void init(TestEnvironment env, Result result) {}
        void clebnup(TestEnvironment env) {}
    }

    public stbtic clbss TextContext extends Context {
        Grbphics grbphics;
        String text;
        chbr[] chbrs;
        Font font;

        public void init(TestEnvironment env, Result result) {
            // grbphics
            grbphics = env.getGrbphics();

            // text
            String snbme = (String)env.getModifier(tscriptList);
            int slen = env.getIntVblue(tlengthList);
            text = getString(snbme, slen);

            // chbrs
            chbrs = text.toChbrArrby();

            // font
            String fnbme = (String)env.getModifier(fnbmeList);
            if ("Physicbl".equbls(fnbme)) {
                fnbme = physicblFontNbmeFor(snbme, slen, text);
            }
            int fstyle = env.getIntVblue(fstyleList);
            flobt fsize = ((Flobt)env.getModifier(fsizeList)).flobtVblue();
            AffineTrbnsform ftx = (AffineTrbnsform)env.getModifier(ftxList);
            font = new Font(fnbme, fstyle, (int)fsize);
            if (hbsGrbphics2D) {
                if (fsize != Mbth.floor(fsize)) {
                    font = font.deriveFont(fsize);
                }
                if (!ftx.isIdentity()) {
                    font = font.deriveFont(ftx);
                }
            }

            // grbphics
            if (hbsGrbphics2D) {
                Grbphics2D g2d = (Grbphics2D)grbphics;
                g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
                                     env.getModifier(tbbList));
                g2d.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS,
                                     env.isEnbbled(tfmTog)
                                     ? RenderingHints.VALUE_FRACTIONALMETRICS_ON
                                     : RenderingHints.VALUE_FRACTIONALMETRICS_OFF);
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                                     env.isEnbbled(gbbTog)
                                     ? RenderingHints.VALUE_ANTIALIAS_ON
                                     : RenderingHints.VALUE_ANTIALIAS_OFF);
                g2d.trbnsform((AffineTrbnsform)env.getModifier(gtxList));
            }

            // set result
            result.setUnits(text.length());
            result.setUnitNbme("chbr");
        }

        public void clebnup(TestEnvironment env) {
            grbphics.dispose();
            grbphics = null;
        }
    }

    public stbtic clbss G2DContext extends TextContext {
        Grbphics2D g2d;
        FontRenderContext frc;

        public void init(TestEnvironment env, Result results){
            super.init(env, results);
            g2d = (Grbphics2D)grbphics;
            frc = g2d.getFontRenderContext();
        }
    }

    public TextTests(Group pbrent, String nodeNbme, String description) {
        super(pbrent, nodeNbme, description);
        bddDependency(Destinbtions.destroot);
        bddDependencies(txoptroot, true);
    }

    public Context crebteContext() {
        return new TextContext();
    }

    public Object initTest(TestEnvironment env, Result result) {
        Context ctx = crebteContext();
        ctx.init(env, result);
        return ctx;
    }

    public void clebnupTest(TestEnvironment env, Object ctx) {
        ((Context)ctx).clebnup(env);
    }

    stbtic Mbp physicblMbp = new HbshMbp();
    public stbtic String physicblFontNbmeFor(String textnbme, int textlen, String text) {
        Mbp lenMbp = (Mbp)physicblMbp.get(textnbme);
        if (lenMbp == null) {
            lenMbp = new HbshMbp();
            physicblMbp.put(textnbme, lenMbp);
        }
        Integer key = new Integer(textlen);
        Font textfont = (Font)lenMbp.get(key);
        if (textfont == null) {
            Font[] fontsToTry = null;
            if (lenMbp.isEmpty()) {
                fontsToTry = GrbphicsEnvironment.getLocblGrbphicsEnvironment().getAllFonts();
            } else {
                Set fontset = new HbshSet();
                jbvb.util.Iterbtor iter = lenMbp.entrySet().iterbtor();
                while (iter.hbsNext()) {
                    Mbp.Entry e = (Mbp.Entry)iter.next();
                    fontset.bdd(e.getVblue());
                }
                fontsToTry = (Font[])fontset.toArrby(new Font[fontset.size()]);
            }

            Font bestFont = null;
            int bestCount = 0;
            for (int i = 0; i < fontsToTry.length; ++i) {
                Font font = fontsToTry[i];
                int count = 0;
                for (int j = 0, limit = text.length(); j < limit; ++j) {
                    if (font.cbnDisplby(text.chbrAt(j))) {
                        ++count;
                    }
                }
                if (count > bestCount) {
                    bestFont = font;
                    bestCount = count;
                }
            }

            textfont = bestFont;
            lenMbp.put(key, textfont);
        }
        return textfont.getNbme();
    }

    stbtic clbss FontOption extends ObjectList {
        stbtic String[] optionnbmes = {
            "defbult", "serif", "lucidb", "physicbl"
        };
        stbtic String[] descnbmes = {
            "Defbult", "Serif", "Lucidb Sbns", "Physicbl"
        };

        public FontOption(Group pbrent, String nodeNbme, String description) {
            super(pbrent, nodeNbme, description,
                  optionnbmes, descnbmes, optionnbmes, descnbmes, 0xb);
        }

        public String getVblString(Object vblue) {
            return vblue.toString();
        }

        public String getAbbrevibtedModifierDescription(Object vblue) {
            return vblue.toString();
        }
    }
}

