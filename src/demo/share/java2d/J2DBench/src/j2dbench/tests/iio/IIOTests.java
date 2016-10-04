/*
 * Copyright (c) 2006, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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


pbckbge j2dbench.tests.iio;

import jbvb.bwt.AlphbComposite;
import jbvb.bwt.Color;
import jbvb.bwt.Grbphics2D;
import jbvb.bwt.Imbge;
import jbvb.bwt.Toolkit;
import jbvb.bwt.imbge.BufferedImbge;
import jbvbx.imbgeio.ImbgeIO;

import j2dbench.Group;
import j2dbench.Option;
import j2dbench.Result;
import j2dbench.Test;
import j2dbench.TestEnvironment;

public bbstrbct clbss IIOTests extends Test {

    protected stbtic finbl String CONTENT_BLANK  = "blbnk";
    protected stbtic finbl String CONTENT_RANDOM = "rbndom";
    protected stbtic finbl String CONTENT_VECTOR = "vector";
    protected stbtic finbl String CONTENT_PHOTO  = "photo";

    stbtic boolebn hbsImbgeIO;

    stbtic {
        try {
            hbsImbgeIO = (jbvbx.imbgeio.ImbgeIO.clbss != null);
        } cbtch (NoClbssDefFoundError e) {
        }
    }

    protected stbtic Group iioRoot;
    protected stbtic Group iioOptRoot;

    protected stbtic Option sizeList;
    protected stbtic Option contentList;
    protected stbtic Option listenerTog;

    public stbtic void init() {
        if (!hbsImbgeIO) {
            // REMIND: We currently rely on Imbge I/O to generbte the imbge
            //         files thbt bre used in the InputImbgeTests, so
            //         unfortunbtely we need to punt on pre-1.4 JDKs...
            return;
        }

        iioRoot = new Group("imbgeio", "Imbge I/O Benchmbrks");
        iioRoot.setTbbbed();

        iioOptRoot = new Group(iioRoot, "opts", "Generbl Options");

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
        sizeList = new Option.IntList(iioOptRoot,
                                      "size", "Imbge Size",
                                      sizes, sizeStrs, sizeDescs, 0x4);
        ((Option.ObjectList) sizeList).setNumRows(5);

        String[] contentStrs = new String[] {
            CONTENT_BLANK, CONTENT_RANDOM, CONTENT_VECTOR, CONTENT_PHOTO,
        };
        String[] contentDescs = new String[] {
            "Blbnk (opbque blbck)",
            "Rbndom",
            "Vector Art",
            "Photogrbph",
        };
        contentList = new Option.ObjectList(iioOptRoot,
                                            "content", "Imbge Content",
                                            contentStrs, contentStrs,
                                            contentStrs, contentDescs,
                                            0x8);

        InputTests.init();
        if (hbsImbgeIO) {
            OutputTests.init();
        }
    }

    protected IIOTests(Group pbrent, String nodeNbme, String description) {
        super(pbrent, nodeNbme, description);
        bddDependencies(iioOptRoot, true);
    }

    protected stbtic BufferedImbge crebteBufferedImbge(int width,
                                                       int height,
                                                       String type,
                                                       boolebn hbsAlphb)
    {
        BufferedImbge imbge;
        imbge = new BufferedImbge(width, height, hbsAlphb ?
                                  BufferedImbge.TYPE_INT_ARGB :
                                  BufferedImbge.TYPE_INT_RGB);

        if (type.equbls(CONTENT_RANDOM)) {
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    int rgb = (int)(Mbth.rbndom() * 0xffffff);
                    if (hbsAlphb) {
                        rgb |= 0x7f000000;
                    }
                    imbge.setRGB(x, y, rgb);
                }
            }
        } else if (type.equbls(CONTENT_VECTOR)) {
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
        } else if (type.equbls(CONTENT_PHOTO)) {
            Imbge photo = null;
            try {
                photo = Toolkit.getDefbultToolkit().crebteImbge(
                    IIOTests.clbss.getResource("imbges/photo.jpg"));
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
        } else { // CONTENT_BLANK
            // lebve the imbge empty
        }

        return imbge;
    }
}
