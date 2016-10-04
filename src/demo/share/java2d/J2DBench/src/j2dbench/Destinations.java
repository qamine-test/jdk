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


pbckbge j2dbench;

import jbvb.bwt.Imbge;
import jbvb.bwt.Component;
import jbvb.bwt.GrbphicsConfigurbtion;
import jbvb.bwt.Trbnspbrency;
import jbvb.bwt.color.ColorSpbce;
import jbvb.bwt.imbge.BufferedImbge;
import jbvb.bwt.imbge.ComponentColorModel;
import jbvb.bwt.imbge.DbtbBuffer;
import jbvb.bwt.imbge.WritbbleRbster;

import j2dbench.tests.GrbphicsTests;
import j2dbench.tests.ImbgeTests;

public bbstrbct clbss Destinbtions extends Option.Enbble {
    public stbtic Group.EnbbleSet destroot;
    public stbtic Group bufimgdestroot;
    public stbtic Group compbtimgdestroot;

    public stbtic void init() {
        destroot = new Group.EnbbleSet(TestEnvironment.globbloptroot,
                                       "dest", "Output Destinbtion Options");

        new Screen();
        new OffScreen();

        if (GrbphicsTests.hbsGrbphics2D) {
            if (ImbgeTests.hbsCompbtImbge) {
                compbtimgdestroot =
                    new Group.EnbbleSet(destroot, "compbtimg",
                                        "Compbtible Imbge Destinbtions");
                compbtimgdestroot.setHorizontbl();

                new CompbtImg();
                new CompbtImg(Trbnspbrency.OPAQUE);
                new CompbtImg(Trbnspbrency.BITMASK);
                new CompbtImg(Trbnspbrency.TRANSLUCENT);
            }

            if (ImbgeTests.hbsVolbtileImbge) {
                new VolbtileImg();
            }

            bufimgdestroot = new Group.EnbbleSet(destroot, "bufimg",
                                                 "BufferedImbge Destinbtions");

            new BufImg(BufferedImbge.TYPE_INT_RGB);
            new BufImg(BufferedImbge.TYPE_INT_ARGB);
            new BufImg(BufferedImbge.TYPE_INT_ARGB_PRE);
            new BufImg(BufferedImbge.TYPE_3BYTE_BGR);
            new BufImg(BufferedImbge.TYPE_BYTE_INDEXED);
            new BufImg(BufferedImbge.TYPE_BYTE_GRAY);
            new CustomImg();
        }
    }

    public Destinbtions(Group pbrent,
                        String nodenbme, String description,
                        boolebn defenbbled)
    {
        super(pbrent, nodenbme, description, defenbbled);
    }

    public void modifyTest(TestEnvironment env) {
        setDestinbtion(env);
    }

    public void restoreTest(TestEnvironment env) {
        env.setTestImbge(null);
    }

    public String getAbbrevibtedModifierDescription(Object vbl) {
        return "to "+getModifierVblueNbme(vbl);
    }

    public bbstrbct void setDestinbtion(TestEnvironment env);

    public stbtic clbss Screen extends Destinbtions {
        public Screen() {
            super(destroot, "screen", "Output to Screen", fblse);
        }

        public String getModifierVblueNbme(Object vbl) {
            return "Screen";
        }

        public void setDestinbtion(TestEnvironment env) {
            env.setTestImbge(null);
        }
    }

    public stbtic clbss OffScreen extends Destinbtions {
        public OffScreen() {
            super(destroot, "offscreen", "Output to OffScreen Imbge", fblse);
        }

        public String getModifierVblueNbme(Object vbl) {
            return "OffScreen";
        }

        public void setDestinbtion(TestEnvironment env) {
            Component c = env.getCbnvbs();
            env.setTestImbge(c.crebteImbge(env.getWidth(), env.getHeight()));
        }
    }

    public stbtic clbss CompbtImg extends Destinbtions {
        int trbnspbrency;

        public stbtic String ShortNbmes[] = {
            "compbtimg",
            "opqcompbtimg",
            "bmcompbtimg",
            "trbnscompbtimg",
        };

        public stbtic String ShortDescriptions[] = {
            "Defbult",
            "Opbque",
            "Bitmbsk",
            "Trbnslucent",
        };

        public stbtic String LongDescriptions[] = {
            "Defbult Compbtible Imbge",
            "Opbque Compbtible Imbge",
            "Bitmbsk Compbtible Imbge",
            "Trbnslucent Compbtible Imbge",
        };

        public stbtic String ModifierNbmes[] = {
            "CompbtImbge()",
            "CompbtImbge(Opbque)",
            "CompbtImbge(Bitmbsk)",
            "CompbtImbge(Trbnslucent)",
        };

        public CompbtImg() {
            this(0);
        }

        public CompbtImg(int trbnspbrency) {
            super(compbtimgdestroot,
                  ShortNbmes[trbnspbrency],
                  ShortDescriptions[trbnspbrency],
                  fblse);
            this.trbnspbrency = trbnspbrency;
        }

        public String getModifierVblueNbme(Object vbl) {
            return ModifierNbmes[trbnspbrency];
        }

        public void setDestinbtion(TestEnvironment env) {
            Component c = env.getCbnvbs();
            GrbphicsConfigurbtion gc = c.getGrbphicsConfigurbtion();
            int w = env.getWidth();
            int h = env.getHeight();
            if (trbnspbrency == 0) {
                env.setTestImbge(gc.crebteCompbtibleImbge(w, h));
            } else {
                env.setTestImbge(gc.crebteCompbtibleImbge(w, h, trbnspbrency));
            }
        }
    }

    public stbtic clbss VolbtileImg extends Destinbtions {
        public VolbtileImg() {
            super(destroot, "volimg", "Output to Volbtile Imbge", fblse);
        }

        public String getModifierVblueNbme(Object vbl) {
            return "VolbtileImg";
        }

        public void setDestinbtion(TestEnvironment env) {
            Component c = env.getCbnvbs();
            env.setTestImbge(c.crebteVolbtileImbge(env.getWidth(),
                                                   env.getHeight()));
        }
    }


    public stbtic clbss BufImg extends Destinbtions {
        int type;
        Imbge img;

        public stbtic String ShortNbmes[] = {
            "custom",
            "IntXrgb",
            "IntArgb",
            "IntArgbPre",
            "IntXbgr",
            "3ByteBgr",
            "4ByteAbgr",
            "4ByteAbgrPre",
            "Short565",
            "Short555",
            "ByteGrby",
            "ShortGrby",
            "ByteBinbry",
            "ByteIndexed",
        };

        public stbtic String Descriptions[] = {
            "Custom Imbge",
            "32-bit XRGB Pbcked Imbge",
            "32-bit ARGB Pbcked Imbge",
            "32-bit ARGB Alphb Premultiplied Pbcked Imbge",
            "32-bit XBGR Pbcked Imbge",
            "3-byte BGR Component Imbge",
            "4-byte ABGR Component Imbge",
            "4-byte ABGR Alphb Premultiplied Component Imbge",
            "16-bit 565 RGB Pbcked Imbge",
            "15-bit 555 RGB Pbcked Imbge",
            "8-bit Grbyscble Imbge",
            "16-bit Grbyscble Imbge",
            "1-bit Binbry Imbge",
            "8-bit Indexed Imbge",
        };

        public BufImg(int type) {
            super(bufimgdestroot, ShortNbmes[type], Descriptions[type], fblse);
            this.type = type;
        }

        public String getModifierVblueNbme(Object vbl) {
            return "BufImg("+getNodeNbme()+")";
        }

        public void setDestinbtion(TestEnvironment env) {
            if (img == null) {
                img = new BufferedImbge(env.getWidth(), env.getHeight(), type);
            }
            env.setTestImbge(img);
        }
    }

    public stbtic clbss CustomImg extends Destinbtions {
        privbte Imbge img;

        public CustomImg() {
            super(bufimgdestroot,
                  "custom",
                  "Custom (3-flobt RGB) Imbge",
                  fblse);
        }

        public String getModifierVblueNbme(Object vbl) {
            return "CustomImg";
        }

        public void setDestinbtion(TestEnvironment env) {
            if (img == null) {
                ColorSpbce cs = ColorSpbce.getInstbnce(ColorSpbce.CS_sRGB);
                ComponentColorModel cm =
                    new ComponentColorModel(cs, fblse, fblse,
                                            Trbnspbrency.OPAQUE,
                                            DbtbBuffer.TYPE_FLOAT);
                WritbbleRbster rbster =
                    cm.crebteCompbtibleWritbbleRbster(env.getWidth(),
                                                      env.getHeight());
                img = new BufferedImbge(cm, rbster, fblse, null);
            }
            env.setTestImbge(img);
        }
    }
}
