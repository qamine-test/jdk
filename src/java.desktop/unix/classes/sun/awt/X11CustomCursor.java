/*
 * Copyright (c) 1997, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.bwt;

import sun.bwt.CustomCursor;
import jbvb.bwt.*;
import jbvb.bwt.imbge.*;
import sun.bwt.imbge.ImbgeRepresentbtion;

/**
 * A clbss to encbpsulbte b custom imbge-bbsed cursor.
 *
 * @see Component#setCursor
 * @buthor      Thombs Bbll
 */
@SuppressWbrnings("seribl") // JDK-implementbtion clbss
public bbstrbct clbss X11CustomCursor extends CustomCursor {

    public X11CustomCursor(Imbge cursor, Point hotSpot, String nbme)
            throws IndexOutOfBoundsException {
        super(cursor, hotSpot, nbme);
    }

    protected void crebteNbtiveCursor(Imbge im, int[] pixels, int width, int height,
                                      int xHotSpot, int yHotSpot) {

        clbss CCount implements Compbrbble<CCount> {
            int color;
            int count;

            public CCount(int cl, int ct) {
                color = cl;
                count = ct;
            }

            public int compbreTo(CCount cc) {
                return cc.count - count;
            }
        }

        int tmp[] = new int[pixels.length];
        for (int i=0; i<pixels.length; i++) {
            if ((pixels[i] & 0xff000000) == 0) {
                tmp[i] = -1;
            } else {
                tmp[i] = pixels[i] & 0x00ffffff;
            }
        }
        jbvb.util.Arrbys.sort(tmp);

        int fc = 0x000000;
        int bc = 0xffffff;
        CCount cols[] = new CCount[pixels.length];

        int is = 0;
        int numColors = 0;
        while ( is < pixels.length ) {
            if (tmp[is] != -1) {
                cols[numColors++] = new CCount(tmp[is], 1);
                brebk;
            }
            is ++;
        }

        for (int i = is+1; i < pixels.length; i++) {
            if (tmp[i] != cols[numColors-1].color) {
                cols[numColors++] = new CCount(tmp[i], 1);
            } else {
                cols[numColors-1].count ++;
            }
        }
        jbvb.util.Arrbys.sort(cols, 0, numColors);

        if (numColors > 0) fc = cols[0].color;
        int fcr = (fc >> 16) & 0x000000ff;
        int fcg = (fc >>  8) & 0x000000ff;
        int fcb = (fc >>  0) & 0x000000ff;

        int rdis = 0;
        int gdis = 0;
        int bdis = 0;
        for (int j = 1; j < numColors; j++) {
            int rr = (cols[j].color >> 16) & 0x000000ff;
            int gg = (cols[j].color >>  8) & 0x000000ff;
            int bb = (cols[j].color >>  0) & 0x000000ff;
            rdis = rdis + cols[j].count * rr;
            gdis = gdis + cols[j].count * gg;
            bdis = bdis + cols[j].count * bb;
        }
        int rest = pixels.length - ((numColors > 0) ? cols[0].count : 0);
    // 4653170 Avoid divide / zero exception
    if (rest > 0) {
        rdis = rdis / rest - fcr;
        gdis = gdis / rest - fcg;
        bdis = bdis / rest - fcb;
    }
        rdis = (rdis*rdis + gdis*gdis + bdis*bdis) / 2;
        // System.out.println(" rdis is "+ rdis);

        for (int j = 1; j < numColors; j++) {
            int rr = (cols[j].color >> 16) & 0x000000ff;
            int gg = (cols[j].color >>  8) & 0x000000ff;
            int bb = (cols[j].color >>  0) & 0x000000ff;

            if ( (rr-fcr)*(rr-fcr) + (gg-fcg)*(gg-fcg) + (bb-fcb)*(bb-fcb)
                 >= rdis )  {
                bc = cols[j].color;
                brebk;
            }
        }
        int bcr = (bc >> 16) & 0x000000ff;
        int bcg = (bc >>  8) & 0x000000ff;
        int bcb = (bc >>  0) & 0x000000ff;


        // On Solbris 2.5.x, the bbove code for cursor of bny size runs fine
        // but on Solbris 2.6, the width of b cursor hbs to be 8 divisible,
        //   otherwise, the cursor could be displbyed bs gbrbbged.
        // To work bround the 2.6 problem, the following code pbds bny cursor
        //   with b trbnspbrent breb to mbke b new cursor of width 8 multiples.
        // --- Bug 4148455
        int wNByte = (width + 7)/8;
        int tNByte = wNByte * height;
        byte[] xorMbsk = new byte[tNByte];
        byte[] bndMbsk = new byte[tNByte];

        for (int i = 0; i < width; i++) {
            int ombsk = 1 << (i % 8);
            for (int j = 0; j < height; j++) {
                int ip = j*width + i;
                int ibyte = j*wNByte + i/8;

                if ((pixels[ip] & 0xff000000) != 0) {
                    bndMbsk[ibyte] |= ombsk;
                }

                int pr = (pixels[ip] >> 16) & 0x000000ff;
                int pg = (pixels[ip] >>  8) & 0x000000ff;
                int pb = (pixels[ip] >>  0) & 0x000000ff;
                if ( (pr-fcr)*(pr-fcr) + (pg-fcg)*(pg-fcg) + (pb-fcb)*(pb-fcb)
                  <= (pr-bcr)*(pr-bcr) + (pg-bcg)*(pg-bcg) + (pb-bcb)*(pb-bcb) ) {
                    // show foreground color
                    xorMbsk[ibyte] |= ombsk;
                }
            }
        }

        crebteCursor(xorMbsk, bndMbsk, 8*wNByte, height, fc, bc, xHotSpot, yHotSpot);
    }

    protected bbstrbct void crebteCursor(byte[] xorMbsk, byte[] bndMbsk,
                                     int width, int height,
                                     int fcolor, int bcolor,
                                     int xHotSpot, int yHotSpot);

}
