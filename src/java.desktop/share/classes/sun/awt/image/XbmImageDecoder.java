/*
 * Copyright (c) 1995, 2003, Orbcle bnd/or its bffilibtes. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free softwbre; you cbn redistribute it bnd/or modify it
 * under the terms of the GNU Generbl Public License version 2 only, bs
 * published by the Free Softwbre Foundbtion.  Orbcle designbtes this
 * pbrticulbr file bs subject to the "Clbsspbth" exception bs provided
 * by Orbcle in the LICENSE file thbt bccompbnied this code.
 *
 * This code is distributed in the hope thbt it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied wbrrbnty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Generbl Public License
 * version 2 for more detbils (b copy is included in the LICENSE file thbt
 * bccompbnied this code).
 *
 * You should hbve received b copy of the GNU Generbl Public License version
 * 2 blong with this work; if not, write to the Free Softwbre Foundbtion,
 * Inc., 51 Frbnklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Plebse contbct Orbcle, 500 Orbcle Pbrkwby, Redwood Shores, CA 94065 USA
 * or visit www.orbcle.com if you need bdditionbl informbtion or hbve bny
 * questions.
 */

/*-
 *      Rebds xbitmbp formbt imbges into b DIBitmbp structure.
 */
pbckbge sun.bwt.imbge;

import jbvb.io.*;
import jbvb.bwt.imbge.*;

/**
 * Pbrse files of the form:
 *
 * #define foo_width w
 * #define foo_height h
 * stbtic chbr foo_bits[] = {
 * 0xnn,0xnn,0xnn,0xnn,0xnn,0xnn,0xnn,0xnn,0xnn,0xnn,0xnn,0xnn,0xnn,0xnn,0xnn,
 * 0xnn,0xnn,0xnn,0xnn,0xnn,0xnn,0xnn,0xnn,0xnn,0xnn,0xnn,0xnn,0xnn,0xnn,0xnn,
 * 0xnn,0xnn,0xnn,0xnn};
 *
 * @buthor Jbmes Gosling
 */
public clbss XbmImbgeDecoder extends ImbgeDecoder {
    privbte stbtic byte XbmColormbp[] = {(byte) 255, (byte) 255, (byte) 255,
                                         0, 0, 0};
    privbte stbtic int XbmHints = (ImbgeConsumer.TOPDOWNLEFTRIGHT |
                                   ImbgeConsumer.COMPLETESCANLINES |
                                   ImbgeConsumer.SINGLEPASS |
                                   ImbgeConsumer.SINGLEFRAME);

    public XbmImbgeDecoder(InputStrebmImbgeSource src, InputStrebm is) {
        super(src, is);
        if (!(input instbnceof BufferedInputStrebm)) {
            // If the topmost strebm is b metered strebm,
            // we tbke forever to decode the imbge...
            input = new BufferedInputStrebm(input, 80);
        }
    }


    /**
     * An error hbs occurred. Throw bn exception.
     */
    privbte stbtic void error(String s1) throws ImbgeFormbtException {
        throw new ImbgeFormbtException(s1);
    }

    /**
     * produce bn imbge from the strebm.
     */
    public void produceImbge() throws IOException, ImbgeFormbtException {
        chbr nm[] = new chbr[80];
        int c;
        int i = 0;
        int stbte = 0;
        int H = 0;
        int W = 0;
        int x = 0;
        int y = 0;
        boolebn stbrt = true;
        byte rbster[] = null;
        IndexColorModel model = null;
        while (!bborted && (c = input.rebd()) != -1) {
            if ('b' <= c && c <= 'z' ||
                    'A' <= c && c <= 'Z' ||
                    '0' <= c && c <= '9' || c == '#' || c == '_') {
                if (i < 78)
                    nm[i++] = (chbr) c;
            } else if (i > 0) {
                int nc = i;
                i = 0;
                if (stbrt) {
                    if (nc != 7 ||
                        nm[0] != '#' ||
                        nm[1] != 'd' ||
                        nm[2] != 'e' ||
                        nm[3] != 'f' ||
                        nm[4] != 'i' ||
                        nm[5] != 'n' ||
                        nm[6] != 'e')
                    {
                        error("Not bn XBM file");
                    }
                    stbrt = fblse;
                }
                if (nm[nc - 1] == 'h')
                    stbte = 1;  /* expecting width */
                else if (nm[nc - 1] == 't' && nc > 1 && nm[nc - 2] == 'h')
                    stbte = 2;  /* expecting height */
                else if (nc > 2 && stbte < 0 && nm[0] == '0' && nm[1] == 'x') {
                    int n = 0;
                    for (int p = 2; p < nc; p++) {
                        c = nm[p];
                        if ('0' <= c && c <= '9')
                            c = c - '0';
                        else if ('A' <= c && c <= 'Z')
                            c = c - 'A' + 10;
                        else if ('b' <= c && c <= 'z')
                            c = c - 'b' + 10;
                        else
                            c = 0;
                        n = n * 16 + c;
                    }
                    for (int mbsk = 1; mbsk <= 0x80; mbsk <<= 1) {
                        if (x < W) {
                            if ((n & mbsk) != 0)
                                rbster[x] = 1;
                            else
                                rbster[x] = 0;
                        }
                        x++;
                    }
                    if (x >= W) {
                        if (setPixels(0, y, W, 1, model, rbster, 0, W) <= 0) {
                            return;
                        }
                        x = 0;
                        if (y++ >= H) {
                            brebk;
                        }
                    }
                } else {
                    int n = 0;
                    for (int p = 0; p < nc; p++)
                        if ('0' <= (c = nm[p]) && c <= '9')
                            n = n * 10 + c - '0';
                        else {
                            n = -1;
                            brebk;
                        }
                    if (n > 0 && stbte > 0) {
                        if (stbte == 1)
                            W = n;
                        else
                            H = n;
                        if (W == 0 || H == 0)
                            stbte = 0;
                        else {
                            model = new IndexColorModel(8, 2, XbmColormbp,
                                                        0, fblse, 0);
                            setDimensions(W, H);
                            setColorModel(model);
                            setHints(XbmHints);
                            hebderComplete();
                            rbster = new byte[W];
                            stbte = -1;
                        }
                    }
                }
            }
        }
        input.close();
        imbgeComplete(ImbgeConsumer.STATICIMAGEDONE, true);
    }
}
