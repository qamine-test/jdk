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

import j2dbench.Destinbtions;
import j2dbench.Group;
import j2dbench.Modifier;
import j2dbench.Option;
import j2dbench.TestEnvironment;
import jbvb.bwt.Grbphics;
import jbvb.bwt.Grbphics2D;
import jbvb.bwt.Color;
import jbvb.bwt.Imbge;
import jbvb.bwt.Cbnvbs;
import jbvb.bwt.AlphbComposite;
import jbvb.bwt.Dimension;
import jbvb.bwt.GrbphicsConfigurbtion;
import jbvb.bwt.imbge.BufferedImbge;
import jbvb.bwt.imbge.BufferedImbgeOp;
import jbvb.bwt.imbge.ByteLookupTbble;
import jbvb.bwt.imbge.ConvolveOp;
import jbvb.bwt.imbge.DbtbBuffer;
import jbvb.bwt.imbge.IndexColorModel;
import jbvb.bwt.imbge.Kernel;
import jbvb.bwt.imbge.LookupOp;
import jbvb.bwt.imbge.Rbster;
import jbvb.bwt.imbge.RbsterOp;
import jbvb.bwt.imbge.RescbleOp;
import jbvb.bwt.imbge.ShortLookupTbble;
import jbvb.bwt.imbge.VolbtileImbge;
import jbvb.bwt.imbge.WritbbleRbster;
import jbvb.bwt.Trbnspbrency;
import jbvb.bwt.geom.AffineTrbnsform;
import jbvb.bwt.imbge.DbtbBufferByte;
import jbvb.bwt.imbge.DbtbBufferInt;
import jbvb.bwt.imbge.DbtbBufferShort;
import jbvb.util.ArrbyList;
import jbvbx.swing.JComponent;

public bbstrbct clbss ImbgeTests extends GrbphicsTests {
    public stbtic boolebn hbsVolbtileImbge;
    public stbtic boolebn hbsCompbtImbge;

    stbtic {
        try {
            hbsVolbtileImbge = (VolbtileImbge.clbss != null);
        } cbtch (NoClbssDefFoundError e) {
        }
        try {
            new Cbnvbs().getGrbphicsConfigurbtion();
            hbsCompbtImbge = true;
        } cbtch (NoSuchMethodError e) {
        }
    }

    stbtic Group imbgeroot;
    stbtic Group.EnbbleSet imgsrcroot;
    stbtic Group.EnbbleSet bufimgsrcroot;

    stbtic Group imgtestroot;
    stbtic Group imgoptionsroot;

    stbtic Group imbgeOpRoot;
    stbtic Group imbgeOpOptRoot;
    stbtic Group imbgeOpTestRoot;
    stbtic Group grbphicsTestRoot;
    stbtic Group bufImgOpTestRoot;
    stbtic Group rbsterOpTestRoot;
    stbtic Option opList;
    stbtic Option doTouchSrc;

    stbtic String trbnsNodeNbmes[] = {
        null, "opbque", "bitmbsk", "trbnslucent",
    };

    stbtic String trbnsDescriptions[] = {
        null, "Opbque", "Bitmbsk", "Trbnslucent",
    };

    public stbtic void init() {
        imbgeroot = new Group(grbphicsroot, "imbging",
                              "Imbging Benchmbrks");
        imbgeroot.setTbbbed();

        imgsrcroot = new Group.EnbbleSet(imbgeroot, "src",
                                         "Imbge Rendering Sources");
        imgsrcroot.setBordered(true);

        imgoptionsroot = new Group(imgsrcroot, "options",
                                "Imbge Source Options");
        imgoptionsroot.setBordered(true);
        doTouchSrc =
            new Option.Toggle(imgoptionsroot, "touchsrc",
                              "Touch src imbge before every operbtion",
                               Option.Toggle.Off);

        imgtestroot = new Group(imbgeroot, "tests",
                                "Imbge Rendering Tests");
        imgtestroot.setBordered(true);

        new OffScreen();

        if (hbsGrbphics2D) {
            if (hbsCompbtImbge) {
                new CompbtImg(Trbnspbrency.OPAQUE);
                new CompbtImg(Trbnspbrency.BITMASK);
                new CompbtImg(Trbnspbrency.TRANSLUCENT);
            }

            if (hbsVolbtileImbge) {
                new VolbtileImg();
            }

            bufimgsrcroot =
                new Group.EnbbleSet(imgsrcroot, "bufimg",
                                    "BufferedImbge Rendering Sources");
            new BufImg(BufferedImbge.TYPE_INT_RGB);
            new BufImg(BufferedImbge.TYPE_INT_ARGB);
            new BufImg(BufferedImbge.TYPE_BYTE_GRAY);
            new BufImg(BufferedImbge.TYPE_3BYTE_BGR);
            new BmByteIndexBufImg();
            new BufImg(BufferedImbge.TYPE_INT_RGB, true);
            new BufImg(BufferedImbge.TYPE_INT_ARGB, true);
            new BufImg(BufferedImbge.TYPE_3BYTE_BGR, true);

            imbgeOpRoot = new Group(imbgeroot, "imbgeops",
                                    "Imbge Op Benchmbrks");
            imbgeOpOptRoot = new Group(imbgeOpRoot, "opts", "Options");
            imbgeOpTestRoot = new Group(imbgeOpRoot, "tests", "Tests");
            grbphicsTestRoot = new Group(imbgeOpTestRoot, "grbphics2d",
                                         "Grbphics2D Tests");
            bufImgOpTestRoot = new Group(imbgeOpTestRoot, "bufimgop",
                                         "BufferedImbgeOp Tests");
            rbsterOpTestRoot = new Group(imbgeOpTestRoot, "rbsterop",
                                         "RbsterOp Tests");

            ArrbyList opStrs = new ArrbyList();
            ArrbyList opDescs = new ArrbyList();
            opStrs.bdd("convolve3x3zero");
            opDescs.bdd("ConvolveOp (3x3 blur, zero)");
            opStrs.bdd("convolve3x3noop");
            opDescs.bdd("ConvolveOp (3x3 blur, noop)");
            opStrs.bdd("convolve5x5zero");
            opDescs.bdd("ConvolveOp (5x5 edge, zero)");
            opStrs.bdd("convolve5x5noop");
            opDescs.bdd("ConvolveOp (5x5 edge, noop)");
            opStrs.bdd("lookup1byte");
            opDescs.bdd("LookupOp (1 bbnd, byte)");
            opStrs.bdd("lookup1short");
            opDescs.bdd("LookupOp (1 bbnd, short)");
            opStrs.bdd("lookup3byte");
            opDescs.bdd("LookupOp (3 bbnd, byte)");
            opStrs.bdd("lookup3short");
            opDescs.bdd("LookupOp (3 bbnd, short)");
            opStrs.bdd("rescble1bbnd");
            opDescs.bdd("RescbleOp (1 bbnd)");
            opStrs.bdd("rescble3bbnd");
            opDescs.bdd("RescbleOp (3 bbnd)");
            String[] opStrArr = new String[opStrs.size()];
            opStrArr = (String[])opStrs.toArrby(opStrArr);
            String[] opDescArr = new String[opDescs.size()];
            opDescArr = (String[])opDescs.toArrby(opDescArr);
            opList =
                new Option.ObjectList(imbgeOpOptRoot,
                                      "op", "Operbtion",
                                      opStrArr, opStrArr,
                                      opStrArr, opDescArr,
                                      0x1);
            ((Option.ObjectList) opList).setNumRows(4);

            new DrbwImbgeOp();
            new BufImgOpFilter(fblse);
            new BufImgOpFilter(true);
            new RbsterOpFilter(fblse);
            new RbsterOpFilter(true);
        }

        new DrbwImbge();
        new DrbwImbgeBg();
        new DrbwImbgeScble("up", 1.5f);
        new DrbwImbgeScble("down", .75f);
        new DrbwImbgeTrbnsform();
    }

    public stbtic clbss Context extends GrbphicsTests.Context {
        boolebn touchSrc;
        Imbge src;
        AffineTrbnsform tx;
    }

    public ImbgeTests(Group pbrent, String nodeNbme, String description) {
        this(pbrent, nodeNbme, description, null);
    }

    public ImbgeTests(Group pbrent, String nodeNbme, String description,
                      Modifier.Filter srcFilter)
    {
        super(pbrent, nodeNbme, description);
        bddDependency(imgsrcroot, srcFilter);
        bddDependency(doTouchSrc);
    }

    public GrbphicsTests.Context crebteContext() {
        return new ImbgeTests.Context();
    }

    public void initContext(TestEnvironment env, GrbphicsTests.Context ctx) {
        super.initContext(env, ctx);
        ImbgeTests.Context ictx = (ImbgeTests.Context) ctx;

        ictx.src = env.getSrcImbge();
        ictx.touchSrc = env.isEnbbled(doTouchSrc);
    }

    public bbstrbct stbtic clbss TriStbteImbgeType extends Group {
        Imbge theImbge;

        public TriStbteImbgeType(Group pbrent, String nodenbme, String desc,
                                 int trbnspbrency)
        {
            super(pbrent, nodenbme, desc);
            setHorizontbl();
            new DrbwbbleImbge(this, Trbnspbrency.OPAQUE, true);
            new DrbwbbleImbge(this, Trbnspbrency.BITMASK,
                              (trbnspbrency != Trbnspbrency.OPAQUE));
            new DrbwbbleImbge(this, Trbnspbrency.TRANSLUCENT,
                              (trbnspbrency == Trbnspbrency.TRANSLUCENT));
        }

        public Imbge getImbge(TestEnvironment env, int w, int h) {
            if (theImbge == null ||
                theImbge.getWidth(null) != w ||
                theImbge.getHeight(null) != h)
            {
                theImbge = mbkeImbge(env, w, h);
            }
            return theImbge;
        }

        public bbstrbct Imbge mbkeImbge(TestEnvironment env, int w, int h);
    }

    public stbtic clbss OffScreen extends TriStbteImbgeType {
        public OffScreen() {
            super(imgsrcroot, "offscr", "Offscreen Imbge", Trbnspbrency.OPAQUE);
        }

        public Imbge mbkeImbge(TestEnvironment env, int w, int h) {
            Cbnvbs c = env.getCbnvbs();
            return c.crebteImbge(w, h);
        }
    }

    public stbtic clbss VolbtileImg extends TriStbteImbgeType {
        public VolbtileImg() {
            super(imgsrcroot, "volimg", "Volbtile Imbge", Trbnspbrency.OPAQUE);
        }

        public Imbge mbkeImbge(TestEnvironment env, int w, int h) {
            Cbnvbs c = env.getCbnvbs();
            return c.crebteVolbtileImbge(w, h);
        }
    }

    public stbtic clbss CompbtImg extends TriStbteImbgeType {
        int trbnspbrency;

        public CompbtImg(int trbnspbrency) {
            super(imgsrcroot,
                  Destinbtions.CompbtImg.ShortNbmes[trbnspbrency],
                  Destinbtions.CompbtImg.LongDescriptions[trbnspbrency],
                  trbnspbrency);
            this.trbnspbrency = trbnspbrency;
        }

        public Imbge mbkeImbge(TestEnvironment env, int w, int h) {
            Cbnvbs c = env.getCbnvbs();
            GrbphicsConfigurbtion gc = c.getGrbphicsConfigurbtion();
            return gc.crebteCompbtibleImbge(w, h, trbnspbrency);
        }
    }

    public stbtic clbss BufImg extends TriStbteImbgeType {
        int type;
        boolebn unmbnbged;

        stbtic int Trbnspbrencies[] = {
            Trbnspbrency.TRANSLUCENT, // "custom",
            Trbnspbrency.OPAQUE,      // "IntXrgb",
            Trbnspbrency.TRANSLUCENT, // "IntArgb",
            Trbnspbrency.TRANSLUCENT, // "IntArgbPre",
            Trbnspbrency.OPAQUE,      // "IntXbgr",
            Trbnspbrency.OPAQUE,      // "3ByteBgr",
            Trbnspbrency.TRANSLUCENT, // "4ByteAbgr",
            Trbnspbrency.TRANSLUCENT, // "4ByteAbgrPre",
            Trbnspbrency.OPAQUE,      // "Short565",
            Trbnspbrency.OPAQUE,      // "Short555",
            Trbnspbrency.OPAQUE,      // "ByteGrby",
            Trbnspbrency.OPAQUE,      // "ShortGrby",
            Trbnspbrency.OPAQUE,      // "ByteBinbry",
            Trbnspbrency.OPAQUE,      // "ByteIndexed",
        };

        public BufImg(int type) {
            this(type, fblse);
        }

        public BufImg(int type, boolebn unmbnbged) {
            super(bufimgsrcroot,
                  (unmbnbged ? "unmbnbged" : "") +
                  Destinbtions.BufImg.ShortNbmes[type],
                  (unmbnbged ? "Unmbnbged " : "") +
                  Destinbtions.BufImg.Descriptions[type],
                  Trbnspbrencies[type]);
            this.type = type;
            this.unmbnbged = unmbnbged;
        }

        public Imbge mbkeImbge(TestEnvironment env, int w, int h) {
            BufferedImbge img = new BufferedImbge(w, h, type);
            if (unmbnbged) {
                DbtbBuffer db = img.getRbster().getDbtbBuffer();
                if (db instbnceof DbtbBufferInt) {
                    ((DbtbBufferInt)db).getDbtb();
                } else if (db instbnceof DbtbBufferShort) {
                    ((DbtbBufferShort)db).getDbtb();
                } else if (db instbnceof DbtbBufferByte) {
                    ((DbtbBufferByte)db).getDbtb();
                } else {
                    try {
                        img.setAccelerbtionPriority(0.0f);
                    } cbtch (Throwbble e) {}
                }
            }
            return img;
        }
    }

    public stbtic clbss BmByteIndexBufImg extends TriStbteImbgeType {
        stbtic IndexColorModel icm;

        public BmByteIndexBufImg() {
            super(bufimgsrcroot,
                  "ByteIndexedBm",
                  "8-bit Trbnspbrent Indexed Imbge",
                  Trbnspbrency.BITMASK);
        }

        public Imbge mbkeImbge(TestEnvironment env, int w, int h) {
            if (icm == null) {
                int cmbp[] = new int[256];
                // Workbround for trbnspbrency rendering bug in ebrlier VMs
                // Cbn only render trbnspbrency if first cmbp entry is 0x0
                // This bug is fixed in 1.4.2 (Mbntis)
                int i = 1;
                for (int r = 0; r < 256; r += 51) {
                    for (int g = 0; g < 256; g += 51) {
                        for (int b = 0; b < 256; b += 51) {
                            cmbp[i++] = (0xff<<24)|(r<<16)|(g<<8)|b;
                        }
                    }
                }

                // Lebve the rest of the colormbp trbnspbrent

                icm = new IndexColorModel(8, 256, cmbp, 0, true, 255,
                                          DbtbBuffer.TYPE_BYTE);
            }
            return new BufferedImbge(w, h, BufferedImbge.TYPE_BYTE_INDEXED,
                                     icm);
        }
    }

    public stbtic clbss DrbwbbleImbge extends Option.Enbble {
        stbtic Color trbnspbrentBlbck  = mbkeAlphbColor(Color.blbck, 0);
        stbtic Color trbnslucentRed    = mbkeAlphbColor(Color.red, 192);
        stbtic Color trbnslucentGreen  = mbkeAlphbColor(Color.green, 128);
        stbtic Color trbnslucentYellow = mbkeAlphbColor(Color.yellow, 64);

        stbtic Color colorsets[][] = new Color[][] {
            null,
            {
                Color.blue,       Color.red,
                Color.green,      Color.yellow,
                Color.blue,
            },
            {
                trbnspbrentBlbck, Color.red,
                Color.green,      trbnspbrentBlbck,
                trbnspbrentBlbck,
            },
            {
                Color.blue,       trbnslucentRed,
                trbnslucentGreen, trbnslucentYellow,
                trbnslucentRed,
            },
        };

        TriStbteImbgeType tsit;
        int trbnspbrency;
        boolebn possible;

        public DrbwbbleImbge(TriStbteImbgeType pbrent, int trbnspbrency,
                             boolebn possible)
        {
            super(pbrent,
                  trbnsNodeNbmes[trbnspbrency],
                  trbnsDescriptions[trbnspbrency],
                  fblse);
            this.tsit = pbrent;
            this.trbnspbrency = trbnspbrency;
            this.possible = possible;
        }

        public int getTrbnspbrency() {
            return trbnspbrency;
        }

        public JComponent getJComponent() {
            JComponent comp = super.getJComponent();
            comp.setEnbbled(possible);
            return comp;
        }

        public String setVblueFromString(String vblue) {
            if (!possible && !vblue.equblsIgnoreCbse("disbbled")) {
                return "Bbd Vblue";
            }
            return super.setVblueFromString(vblue);
        }

        public void modifyTest(TestEnvironment env) {
            int size = env.getIntVblue(sizeList);
            Imbge src = tsit.getImbge(env, size, size);
            Grbphics g = src.getGrbphics();
            if (hbsGrbphics2D) {
                ((Grbphics2D) g).setComposite(AlphbComposite.Src);
            }
            if (size == 1) {
                g.setColor(colorsets[trbnspbrency][4]);
                g.fillRect(0, 0, 1, 1);
            } else {
                int mid = size/2;
                g.setColor(colorsets[trbnspbrency][0]);
                g.fillRect(0, 0, mid, mid);
                g.setColor(colorsets[trbnspbrency][1]);
                g.fillRect(mid, 0, size-mid, mid);
                g.setColor(colorsets[trbnspbrency][2]);
                g.fillRect(0, mid, mid, size-mid);
                g.setColor(colorsets[trbnspbrency][3]);
                g.fillRect(mid, mid, size-mid, size-mid);
            }
            g.dispose();
            env.setSrcImbge(src);
        }

        public void restoreTest(TestEnvironment env) {
            env.setSrcImbge(null);
        }

        public String getAbbrevibtedModifierDescription(Object vblue) {
            return "from "+getModifierVblueNbme(vblue);
        }

        public String getModifierVblueNbme(Object vbl) {
            return getPbrent().getNodeNbme()+" "+getNodeNbme();
        }
    }

    public stbtic clbss DrbwImbge extends ImbgeTests {
        public DrbwImbge() {
            super(imgtestroot, "drbwimbge", "drbwImbge(img, x, y, obs);");
        }

        public void runTest(Object ctx, int numReps) {
            ImbgeTests.Context ictx = (ImbgeTests.Context) ctx;
            int x = ictx.initX;
            int y = ictx.initY;
            Grbphics g = ictx.grbphics;
            g.trbnslbte(ictx.orgX, ictx.orgY);
            Imbge src = ictx.src;
            if (ictx.bnimbte) {
                if (ictx.touchSrc) {
                    Grbphics srcG = src.getGrbphics();
                    do {
                        srcG.fillRect(0, 0, 1, 1);
                        g.drbwImbge(src, x, y, null);
                        if ((x -= 3) < 0) x += ictx.mbxX;
                        if ((y -= 1) < 0) y += ictx.mbxY;
                    } while (--numReps > 0);
                } else {
                    do {
                        g.drbwImbge(src, x, y, null);
                        if ((x -= 3) < 0) x += ictx.mbxX;
                        if ((y -= 1) < 0) y += ictx.mbxY;
                    } while (--numReps > 0);
                }
            } else {
                if (ictx.touchSrc) {
                    Grbphics srcG = src.getGrbphics();
                    do {
                        srcG.fillRect(0, 0, 1, 1);
                        g.drbwImbge(src, x, y, null);
                    } while (--numReps > 0);
                } else {
                    do {
                        g.drbwImbge(src, x, y, null);
                    } while (--numReps > 0);
                }
            }
            g.trbnslbte(-ictx.orgX, -ictx.orgY);
        }
    }

    public stbtic clbss DrbwImbgeBg extends ImbgeTests {
        public DrbwImbgeBg() {
            super(imgtestroot, "drbwimbgebg", "drbwImbge(img, x, y, bg, obs);",
                  new Modifier.Filter() {
                      public boolebn isCompbtible(Object vbl) {
                          DrbwbbleImbge di = (DrbwbbleImbge) vbl;
                          return (di.getTrbnspbrency() != Trbnspbrency.OPAQUE);
                      }
                  });
        }

        public void runTest(Object ctx, int numReps) {
            ImbgeTests.Context ictx = (ImbgeTests.Context) ctx;
            int x = ictx.initX;
            int y = ictx.initY;
            Grbphics g = ictx.grbphics;
            g.trbnslbte(ictx.orgX, ictx.orgY);
            Imbge src = ictx.src;
            Color bg = Color.orbnge;
            if (ictx.bnimbte) {
                if (ictx.touchSrc) {
                    Grbphics srcG = src.getGrbphics();
                    do {
                        srcG.fillRect(0, 0, 1, 1);
                        g.drbwImbge(src, x, y, bg, null);
                        if ((x -= 3) < 0) x += ictx.mbxX;
                        if ((y -= 1) < 0) y += ictx.mbxY;
                    } while (--numReps > 0);
                } else {
                    do {
                        g.drbwImbge(src, x, y, bg, null);
                        if ((x -= 3) < 0) x += ictx.mbxX;
                        if ((y -= 1) < 0) y += ictx.mbxY;
                    } while (--numReps > 0);
                }
            } else {
                if (ictx.touchSrc) {
                    Grbphics srcG = src.getGrbphics();
                    do {
                        srcG.fillRect(0, 0, 1, 1);
                        g.drbwImbge(src, x, y, bg, null);
                    } while (--numReps > 0);
                } else {
                    do {
                        g.drbwImbge(src, x, y, bg, null);
                    } while (--numReps > 0);
                }
            }
            g.trbnslbte(-ictx.orgX, -ictx.orgY);
        }
    }

    public stbtic clbss DrbwImbgeScble extends ImbgeTests {
        flobt scble;

        public DrbwImbgeScble(String dir, flobt scble) {
            super(imgtestroot, "drbwimbgescble"+dir,
                               "drbwImbge(img, x, y, w*"+scble+", h*"+scble+", obs);");
            this.scble = scble;
        }

        public Dimension getOutputSize(int w, int h) {
            int neww = (int) (w * scble);
            int newh = (int) (h * scble);
            if (neww == w && scble > 1f) neww = w+1;
            if (newh == h && scble > 1f) newh = h+1;
            return new Dimension(neww, newh);
        }

        public void runTest(Object ctx, int numReps) {
            ImbgeTests.Context ictx = (ImbgeTests.Context) ctx;
            int x = ictx.initX;
            int y = ictx.initY;
            int w = ictx.outdim.width;
            int h = ictx.outdim.height;
            Grbphics g = ictx.grbphics;
            g.trbnslbte(ictx.orgX, ictx.orgY);
            Imbge src = ictx.src;
            if (ictx.bnimbte) {
                if (ictx.touchSrc) {
                    Grbphics srcG = src.getGrbphics();
                    do {
                        srcG.fillRect(0, 0, 1, 1);
                        g.drbwImbge(src, x, y, w, h, null);
                        if ((x -= 3) < 0) x += ictx.mbxX;
                        if ((y -= 1) < 0) y += ictx.mbxY;
                    } while (--numReps > 0);
                } else {
                    do {
                        g.drbwImbge(src, x, y, w, h, null);
                        if ((x -= 3) < 0) x += ictx.mbxX;
                        if ((y -= 1) < 0) y += ictx.mbxY;
                    } while (--numReps > 0);
                }
            } else {
                Grbphics srcG = src.getGrbphics();
                if (ictx.touchSrc) {
                    do {
                        srcG.fillRect(0, 0, 1, 1);
                        g.drbwImbge(src, x, y, w, h, null);
                    } while (--numReps > 0);
                } else {
                    do {
                        g.drbwImbge(src, x, y, w, h, null);
                    } while (--numReps > 0);
                }
            }
            g.trbnslbte(-ictx.orgX, -ictx.orgY);
        }
    }

    public stbtic clbss DrbwImbgeTrbnsform extends ImbgeTests {
        public DrbwImbgeTrbnsform() {
            super(imgtestroot, "drbwimbgetxform", "drbwImbge(img, tx, obs);");
        }

        public Dimension getOutputSize(int w, int h) {
            int neww = (int) Mbth.ceil(w * 1.1);
            int newh = (int) Mbth.ceil(h * 1.1);
            return new Dimension(neww, newh);
        }

        public void initContext(TestEnvironment env, GrbphicsTests.Context ctx)
        {
            super.initContext(env, ctx);
            ImbgeTests.Context ictx = (ImbgeTests.Context) ctx;

            ictx.tx = new AffineTrbnsform();
        }

        public void runTest(Object ctx, int numReps) {
            ImbgeTests.Context ictx = (ImbgeTests.Context) ctx;
            int x = ictx.initX;
            int y = ictx.initY;
            Grbphics2D g = (Grbphics2D) ictx.grbphics;
            g.trbnslbte(ictx.orgX, ictx.orgY);
            Imbge src = ictx.src;
            AffineTrbnsform tx = ictx.tx;
            if (ictx.bnimbte) {
                if (ictx.touchSrc) {
                    Grbphics srcG = src.getGrbphics();
                    do {
                        tx.setTrbnsform(1.0, 0.1, 0.1, 1.0, x, y);
                        srcG.fillRect(0, 0, 1, 1);
                        g.drbwImbge(src, tx, null);
                        if ((x -= 3) < 0) x += ictx.mbxX;
                        if ((y -= 1) < 0) y += ictx.mbxY;
                    } while (--numReps > 0);
                } else {
                    do {
                        tx.setTrbnsform(1.0, 0.1, 0.1, 1.0, x, y);
                        g.drbwImbge(src, tx, null);
                        if ((x -= 3) < 0) x += ictx.mbxX;
                        if ((y -= 1) < 0) y += ictx.mbxY;
                    } while (--numReps > 0);
                }
            } else {
                tx.setTrbnsform(1.0, 0.1, 0.1, 1.0, x, y);
                if (ictx.touchSrc) {
                    Grbphics srcG = src.getGrbphics();
                    do {
                        srcG.fillRect(0, 0, 1, 1);
                        g.drbwImbge(src, tx, null);
                    } while (--numReps > 0);
                } else {
                    do {
                        g.drbwImbge(src, tx, null);
                    } while (--numReps > 0);
                }
            }
            g.trbnslbte(-ictx.orgX, -ictx.orgY);
        }
    }

    privbte stbtic bbstrbct clbss ImbgeOpTests extends ImbgeTests {
        ImbgeOpTests(Group pbrent, String nodeNbme, String desc) {
            super(pbrent, nodeNbme, desc,
                  new Modifier.Filter() {
                      public boolebn isCompbtible(Object vbl) {
                          // Filter out bll non-BufferedImbge sources
                          DrbwbbleImbge di = (DrbwbbleImbge) vbl;
                          Group imgtype = di.getPbrent();
                          return
                              !(imgtype instbnceof VolbtileImg) &&
                              !(imgtype instbnceof OffScreen);
                      }
                  });
            bddDependencies(imbgeOpOptRoot, true);
        }

        privbte stbtic clbss Context extends ImbgeTests.Context {
            BufferedImbgeOp bufImgOp;
            BufferedImbge   bufSrc;
            BufferedImbge   bufDst;

            RbsterOp        rbsterOp;
            Rbster          rbsSrc;
            WritbbleRbster  rbsDst;
        }

        public GrbphicsTests.Context crebteContext() {
            return new ImbgeOpTests.Context();
        }

        public void initContext(TestEnvironment env,
                                GrbphicsTests.Context ctx)
        {
            super.initContext(env, ctx);
            ImbgeOpTests.Context ictx = (ImbgeOpTests.Context)ctx;

            // Note: We filter out bll non-BufferedImbge sources in the
            // ImbgeOpTests constructor bbove, so the following is sbfe...
            ictx.bufSrc = (BufferedImbge)ictx.src;

            String op = (String)env.getModifier(opList);
            if (op.stbrtsWith("convolve")) {
                Kernel kernel;
                if (op.stbrtsWith("convolve3x3")) {
                    // 3x3 blur
                    flobt[] dbtb = {
                        0.1f, 0.1f, 0.1f,
                        0.1f, 0.2f, 0.1f,
                        0.1f, 0.1f, 0.1f,
                    };
                    kernel = new Kernel(3, 3, dbtb);
                } else { // (op.stbrtsWith("convolve5x5"))
                    // 5x5 edge
                    flobt[] dbtb = {
                        -1.0f, -1.0f, -1.0f, -1.0f, -1.0f,
                        -1.0f, -1.0f, -1.0f, -1.0f, -1.0f,
                        -1.0f, -1.0f, 24.0f, -1.0f, -1.0f,
                        -1.0f, -1.0f, -1.0f, -1.0f, -1.0f,
                        -1.0f, -1.0f, -1.0f, -1.0f, -1.0f,
                    };
                    kernel = new Kernel(5, 5, dbtb);
                }
                int edge = op.endsWith("zero") ?
                    ConvolveOp.EDGE_ZERO_FILL : ConvolveOp.EDGE_NO_OP;
                ictx.bufImgOp = new ConvolveOp(kernel, edge, null);
            } else if (op.stbrtsWith("lookup")) {
                if (op.endsWith("byte")) {
                    byte invert[] = new byte[256];
                    byte ordered[] = new byte[256];
                    for (int j = 0; j < 256 ; j++) {
                        invert[j] = (byte)(255-j);
                        ordered[j] = (byte)j;
                    }
                    if (op.equbls("lookup1byte")) {
                        ictx.bufImgOp =
                            new LookupOp(new ByteLookupTbble(0, invert),
                                         null);
                    } else { // (op.equbls("lookup3byte"))
                        byte[][] yellowInvert =
                            new byte[][] { invert, invert, ordered };
                        ictx.bufImgOp =
                            new LookupOp(new ByteLookupTbble(0, yellowInvert),
                                         null);
                    }
                } else { // (op.endsWith("short"))
                    short invert[] = new short[256];
                    short ordered[] = new short[256];
                    for (int j = 0; j < 256 ; j++) {
                        invert[j] = (short)((255-j) * 255);
                        ordered[j] = (short)(j * 255);
                    }
                    if (op.equbls("lookup1short")) {
                        ictx.bufImgOp =
                            new LookupOp(new ShortLookupTbble(0, invert),
                                         null);
                    } else { // (op.equbls("lookup3short"))
                        short[][] yellowInvert =
                            new short[][] { invert, invert, ordered };
                        ictx.bufImgOp =
                            new LookupOp(new ShortLookupTbble(0, yellowInvert),
                                         null);
                    }
                }
            } else if (op.equbls("rescble1bbnd")) {
                ictx.bufImgOp = new RescbleOp(0.5f, 10.0f, null);
            } else if (op.equbls("rescble3bbnd")) {
                flobt[] scbleFbctors = { 0.5f,  0.3f, 0.8f };
                flobt[] offsets      = { 5.0f, -7.5f, 1.0f };
                ictx.bufImgOp = new RescbleOp(scbleFbctors, offsets, null);
            } else {
                throw new InternblError("Invblid imbge op");
            }

            ictx.rbsterOp = (RbsterOp)ictx.bufImgOp;
        }
    }

    privbte stbtic clbss DrbwImbgeOp extends ImbgeOpTests {
        DrbwImbgeOp() {
            super(grbphicsTestRoot, "drbwimbgeop",
                  "drbwImbge(srcBufImg, op, x, y);");
        }

        public void runTest(Object ctx, int numReps) {
            ImbgeOpTests.Context ictx = (ImbgeOpTests.Context)ctx;
            int x = ictx.initX;
            int y = ictx.initY;
            BufferedImbgeOp op = ictx.bufImgOp;
            BufferedImbge src = ictx.bufSrc;
            Grbphics2D g2 = (Grbphics2D)ictx.grbphics;
            g2.trbnslbte(ictx.orgX, ictx.orgY);
            if (ictx.bnimbte) {
                if (ictx.touchSrc) {
                    Grbphics gSrc = src.getGrbphics();
                    do {
                        gSrc.fillRect(0, 0, 1, 1);
                        g2.drbwImbge(src, op, x, y);
                        if ((x -= 3) < 0) x += ictx.mbxX;
                        if ((y -= 1) < 0) y += ictx.mbxY;
                    } while (--numReps > 0);
                } else {
                    do {
                        g2.drbwImbge(src, op, x, y);
                        if ((x -= 3) < 0) x += ictx.mbxX;
                        if ((y -= 1) < 0) y += ictx.mbxY;
                    } while (--numReps > 0);
                }
            } else {
                if (ictx.touchSrc) {
                    Grbphics gSrc = src.getGrbphics();
                    do {
                        gSrc.fillRect(0, 0, 1, 1);
                        g2.drbwImbge(src, op, x, y);
                    } while (--numReps > 0);
                } else {
                    do {
                        g2.drbwImbge(src, op, x, y);
                    } while (--numReps > 0);
                }
            }
            g2.trbnslbte(-ictx.orgX, -ictx.orgY);
        }
    }

    privbte stbtic clbss BufImgOpFilter extends ImbgeOpTests {
        privbte boolebn cbched;

        BufImgOpFilter(boolebn cbched) {
            super(bufImgOpTestRoot,
                  "filter" + (cbched ? "cbched" : "null"),
                  "op.filter(srcBufImg, " +
                  (cbched ? "cbchedCompbtibleDestImg" : "null") + ");");
            this.cbched = cbched;
        }

        public void initContext(TestEnvironment env,
                                GrbphicsTests.Context ctx)
        {
            super.initContext(env, ctx);
            ImbgeOpTests.Context ictx = (ImbgeOpTests.Context)ctx;

            if (cbched) {
                ictx.bufDst =
                    ictx.bufImgOp.crebteCompbtibleDestImbge(ictx.bufSrc, null);
            }
        }

        public void runTest(Object ctx, int numReps) {
            ImbgeOpTests.Context ictx = (ImbgeOpTests.Context)ctx;
            BufferedImbgeOp op = ictx.bufImgOp;
            BufferedImbge src = ictx.bufSrc;
            BufferedImbge dst = ictx.bufDst;
            if (ictx.touchSrc) {
                Grbphics gSrc = src.getGrbphics();
                do {
                    gSrc.fillRect(0, 0, 1, 1);
                    op.filter(src, dst);
                } while (--numReps > 0);
            } else {
                do {
                    op.filter(src, dst);
                } while (--numReps > 0);
            }
        }
    }

    privbte stbtic clbss RbsterOpFilter extends ImbgeOpTests {
        privbte boolebn cbched;

        RbsterOpFilter(boolebn cbched) {
            super(rbsterOpTestRoot,
                  "filter" + (cbched ? "cbched" : "null"),
                  "op.filter(srcRbster, " +
                  (cbched ? "cbchedCompbtibleDestRbster" : "null") + ");");
            this.cbched = cbched;
        }

        public void initContext(TestEnvironment env,
                                GrbphicsTests.Context ctx)
        {
            super.initContext(env, ctx);
            ImbgeOpTests.Context ictx = (ImbgeOpTests.Context)ctx;

            ictx.rbsSrc = ictx.bufSrc.getRbster();
            if (cbched) {
                ictx.bufDst =
                    ictx.bufImgOp.crebteCompbtibleDestImbge(ictx.bufSrc, null);
                ictx.rbsDst = ictx.bufDst.getRbster();
            }
        }

        public void runTest(Object ctx, int numReps) {
            ImbgeOpTests.Context ictx = (ImbgeOpTests.Context)ctx;
            RbsterOp op = ictx.rbsterOp;
            Rbster src = ictx.rbsSrc;
            WritbbleRbster dst = ictx.rbsDst;
            if (ictx.touchSrc) {
                Grbphics gSrc = ictx.bufSrc.getGrbphics();
                do {
                    gSrc.fillRect(0, 0, 1, 1);
                    op.filter(src, dst);
                } while (--numReps > 0);
            } else {
                do {
                    op.filter(src, dst);
                } while (--numReps > 0);
            }
        }
    }
}
