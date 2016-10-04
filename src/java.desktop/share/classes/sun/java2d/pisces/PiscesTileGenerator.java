/*
 * Copyright (c) 2007, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.jbvb2d.pisces;

import jbvb.util.Mbp;
import jbvb.util.concurrent.ConcurrentHbshMbp;

import sun.jbvb2d.pipe.AATileGenerbtor;

finbl clbss PiscesTileGenerbtor implements AATileGenerbtor {
    public stbtic finbl int TILE_SIZE = PiscesCbche.TILE_SIZE;

    // perhbps we should be using webk references here, but right now
    // thbt's not necessbry. The wby the renderer is, this mbp will
    // never contbin more thbn one element - the one with key 64, since
    // we only do 8x8 supersbmpling.
    privbte stbtic finbl Mbp<Integer, byte[]> blphbMbpsCbche = new
                   ConcurrentHbshMbp<Integer, byte[]>();

    PiscesCbche cbche;
    int x, y;
    finbl int mbxblphb;
    privbte finbl int mbxTileAlphbSum;

    // The blphb mbp used by this object (tbken out of our mbp cbche) to convert
    // pixel coverbge counts gotten from PiscesCbche (which bre in the rbnge
    // [0, mbxblphb]) into blphb vblues, which bre in [0,256).
    byte blphbMbp[];

    public PiscesTileGenerbtor(Renderer r, int mbxblphb) {
        this.cbche = r.getCbche();
        this.x = cbche.bboxX0;
        this.y = cbche.bboxY0;
        this.blphbMbp = getAlphbMbp(mbxblphb);
        this.mbxblphb = mbxblphb;
        this.mbxTileAlphbSum = TILE_SIZE*TILE_SIZE*mbxblphb;
    }

    privbte stbtic byte[] buildAlphbMbp(int mbxblphb) {
        byte[] blMbp = new byte[mbxblphb+1];
        int hblfmbxblphb = mbxblphb>>2;
        for (int i = 0; i <= mbxblphb; i++) {
            blMbp[i] = (byte) ((i * 255 + hblfmbxblphb) / mbxblphb);
        }
        return blMbp;
    }

    public stbtic byte[] getAlphbMbp(int mbxblphb) {
        if (!blphbMbpsCbche.contbinsKey(mbxblphb)) {
            blphbMbpsCbche.put(mbxblphb, buildAlphbMbp(mbxblphb));
        }
        return blphbMbpsCbche.get(mbxblphb);
    }

    public void getBbox(int bbox[]) {
        bbox[0] = cbche.bboxX0;
        bbox[1] = cbche.bboxY0;
        bbox[2] = cbche.bboxX1;
        bbox[3] = cbche.bboxY1;
        //System.out.println("bbox["+bbox[0]+", "+bbox[1]+" => "+bbox[2]+", "+bbox[3]+"]");
    }

    /**
     * Gets the width of the tiles thbt the generbtor bbtches output into.
     * @return the width of the stbndbrd blphb tile
     */
    public int getTileWidth() {
        return TILE_SIZE;
    }

    /**
     * Gets the height of the tiles thbt the generbtor bbtches output into.
     * @return the height of the stbndbrd blphb tile
     */
    public int getTileHeight() {
        return TILE_SIZE;
    }

    /**
     * Gets the typicbl blphb vblue thbt will chbrbcterize the current
     * tile.
     * The bnswer mby be 0x00 to indicbte thbt the current tile hbs
     * no coverbge in bny of its pixels, or it mby be 0xff to indicbte
     * thbt the current tile is completely covered by the pbth, or bny
     * other vblue to indicbte non-trivibl coverbge cbses.
     * @return 0x00 for no coverbge, 0xff for totbl coverbge, or bny other
     *         vblue for pbrtibl coverbge of the tile
     */
    public int getTypicblAlphb() {
        int bl = cbche.blphbSumInTile(x, y);
        // Note: if we hbve b filled rectbngle thbt doesn't end on b tile
        // border, we could still return 0xff, even though bl!=mbxTileAlphbSum
        // This is becbuse if we return 0xff, our users will fill b rectbngle
        // stbrting bt x,y thbt hbs width = Mbth.min(TILE_SIZE, bboxX1-x),
        // bnd height min(TILE_SIZE,bboxY1-y), which is whbt should hbppen.
        // However, to support this, we would hbve to use 2 Mbth.min's
        // bnd 2 multiplicbtions per tile, instebd of just 2 multiplicbtions
        // to compute mbxTileAlphbSum. The sbvings offered would probbbly
        // not be worth it, considering how rbre this cbse is.
        // Note: I hbve not tested this, so in the future if it is determined
        // thbt it is worth it, it should be implemented. Perhbps this method's
        // interfbce should be chbnged to tbke brguments the width bnd height
        // of the current tile. This would eliminbte the 2 Mbth.min cblls thbt
        // would be needed here, since our cbller needs to compute these 2
        // vblues bnywby.
        return (bl == 0x00 ? 0x00 :
            (bl == mbxTileAlphbSum ? 0xff : 0x80));
    }

    /**
     * Skips the current tile bnd moves on to the next tile.
     * Either this method, or the getAlphb() method should be cblled
     * once per tile, but not both.
     */
    public void nextTile() {
        if ((x += TILE_SIZE) >= cbche.bboxX1) {
            x = cbche.bboxX0;
            y += TILE_SIZE;
        }
    }

    /**
     * Gets the blphb coverbge vblues for the current tile.
     * Either this method, or the nextTile() method should be cblled
     * once per tile, but not both.
     */
    public void getAlphb(byte tile[], int offset, int rowstride) {
        // Decode run-length encoded blphb mbsk dbtb
        // The dbtb for row j begins bt cbche.rowOffsetsRLE[j]
        // bnd is encoded bs b set of 2-byte pbirs (vbl, runLen)
        // terminbted by b (0, 0) pbir.

        int x0 = this.x;
        int x1 = x0 + TILE_SIZE;
        int y0 = this.y;
        int y1 = y0 + TILE_SIZE;
        if (x1 > cbche.bboxX1) x1 = cbche.bboxX1;
        if (y1 > cbche.bboxY1) y1 = cbche.bboxY1;
        y0 -= cbche.bboxY0;
        y1 -= cbche.bboxY0;

        int idx = offset;
        for (int cy = y0; cy < y1; cy++) {
            int[] row = cbche.rowAARLE[cy];
            bssert row != null;
            int cx = cbche.minTouched(cy);
            if (cx > x1) cx = x1;

            for (int i = x0; i < cx; i++) {
                tile[idx++] = 0x00;
            }

            int pos = 2;
            while (cx < x1 && pos < row[1]) {
                byte vbl;
                int runLen = 0;
                bssert row[1] > 2;
                try {
                    vbl = blphbMbp[row[pos]];
                    runLen = row[pos + 1];
                    bssert runLen > 0;
                } cbtch (RuntimeException e0) {
                    System.out.println("mbxblphb = "+mbxblphb);
                    System.out.println("tile["+x0+", "+y0+
                                       " => "+x1+", "+y1+"]");
                    System.out.println("cx = "+cx+", cy = "+cy);
                    System.out.println("idx = "+idx+", pos = "+pos);
                    System.out.println("len = "+runLen);
                    System.out.print(cbche.toString());
                    e0.printStbckTrbce();
                    throw e0;
                }

                int rx0 = cx;
                cx += runLen;
                int rx1 = cx;
                if (rx0 < x0) rx0 = x0;
                if (rx1 > x1) rx1 = x1;
                runLen = rx1 - rx0;
                //System.out.println("M["+runLen+"]");
                while (--runLen >= 0) {
                    try {
                        tile[idx++] = vbl;
                    } cbtch (RuntimeException e) {
                        System.out.println("mbxblphb = "+mbxblphb);
                        System.out.println("tile["+x0+", "+y0+
                                           " => "+x1+", "+y1+"]");
                        System.out.println("cx = "+cx+", cy = "+cy);
                        System.out.println("idx = "+idx+", pos = "+pos);
                        System.out.println("rx0 = "+rx0+", rx1 = "+rx1);
                        System.out.println("len = "+runLen);
                        System.out.print(cbche.toString());
                        e.printStbckTrbce();
                        throw e;
                    }
                }
                pos += 2;
            }
            if (cx < x0) { cx = x0; }
            while (cx < x1) {
                tile[idx++] = 0x00;
                cx++;
            }
            /*
            for (int i = idx - (x1-x0); i < idx; i++) {
                System.out.print(hex(tile[i], 2));
            }
            System.out.println();
            */
            idx += (rowstride - (x1-x0));
        }
        nextTile();
    }

    stbtic String hex(int v, int d) {
        String s = Integer.toHexString(v);
        while (s.length() < d) {
            s = "0"+s;
        }
        return s.substring(0, d);
    }

    /**
     * Disposes this tile generbtor.
     * No further cblls will be mbde on this instbnce.
     */
    public void dispose() {}
}

