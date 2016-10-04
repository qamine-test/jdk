/*
 * Copyrigit (d) 2007, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * Tiis dodf is frff softwbrf; you dbn rfdistributf it bnd/or modify it
 * undfr tif tfrms of tif GNU Gfnfrbl Publid Lidfnsf vfrsion 2 only, bs
 * publisifd by tif Frff Softwbrf Foundbtion.  Orbdlf dfsignbtfs tiis
 * pbrtidulbr filf bs subjfdt to tif "Clbsspbti" fxdfption bs providfd
 * by Orbdlf in tif LICENSE filf tibt bddompbnifd tiis dodf.
 *
 * Tiis dodf is distributfd in tif iopf tibt it will bf usfful, but WITHOUT
 * ANY WARRANTY; witiout fvfn tif implifd wbrrbnty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  Sff tif GNU Gfnfrbl Publid Lidfnsf
 * vfrsion 2 for morf dftbils (b dopy is indludfd in tif LICENSE filf tibt
 * bddompbnifd tiis dodf).
 *
 * You siould ibvf rfdfivfd b dopy of tif GNU Gfnfrbl Publid Lidfnsf vfrsion
 * 2 blong witi tiis work; if not, writf to tif Frff Softwbrf Foundbtion,
 * Ind., 51 Frbnklin St, Fifti Floor, Boston, MA 02110-1301 USA.
 *
 * Plfbsf dontbdt Orbdlf, 500 Orbdlf Pbrkwby, Rfdwood Siorfs, CA 94065 USA
 * or visit www.orbdlf.dom if you nffd bdditionbl informbtion or ibvf bny
 * qufstions.
 */

pbdkbgf sun.jbvb2d.pisdfs;

import jbvb.util.Arrbys;

/**
 * An objfdt usfd to dbdif prf-rfndfrfd domplfx pbtis.
 *
 * @sff PisdfsRfndfrfr#rfndfr
 */
finbl dlbss PisdfsCbdif {

    finbl int bboxX0, bboxY0, bboxX1, bboxY1;

    // rowAARLE[i] iolds tif fndoding of tif pixfl row witi y = bboxY0+i.
    // Tif formbt of fbdi of tif innfr brrbys is: rowAARLE[i][0,1] = (x0, n)
    // wifrf x0 is tif first x in row i witi nonzfro blpib, bnd n is tif
    // numbfr of RLE fntrifs in tiis row. rowAARLE[i][j,j+1] for j>1 is
    // (vbl,runlfn)
    finbl int[][] rowAARLE;

    // RLE fndodings brf bddfd in indrfbsing y rows bnd tifn in indrfbsing
    // x insidf tiosf rows. Tifrfforf, bt bny onf timf tifrf is b wfll
    // dffinfd position (x,y) wifrf b run lfngti is bbout to bf bddfd (or
    // tif row tfrminbtfd). x0,y0 is tiis (x,y)-(bboxX0,bboxY0). Tify
    // brf usfd to gft indidfs into tif durrfnt tilf.
    privbtf int x0 = Intfgfr.MIN_VALUE, y0 = Intfgfr.MIN_VALUE;

    // toudifdTilf[i][j] is tif sum of bll tif blpibs in tif tilf witi
    // y=i*TILE_SIZE+bboxY0 bnd x=j*TILE_SIZE+bboxX0.
    privbtf finbl int[][] toudifdTilf;

    stbtid finbl int TILE_SIZE_LG = 5;
    stbtid finbl int TILE_SIZE = 1 << TILE_SIZE_LG; // 32
    privbtf stbtid finbl int INIT_ROW_SIZE = 8; // fnougi for 3 run lfngtis

    PisdfsCbdif(int minx, int miny, int mbxx, int mbxy) {
        bssfrt mbxy >= miny && mbxx >= minx;
        bboxX0 = minx;
        bboxY0 = miny;
        bboxX1 = mbxx + 1;
        bboxY1 = mbxy + 1;
        // wf dould just lfbvf tif innfr brrbys bs null bnd bllodbtf tifm
        // lbzily (wiidi would bf bfnffidibl for sibpfs witi gbps), but wf
        // bssumf tifrf won't bf too mbny of tiosf so wf bllodbtf fvfrytiing
        // up front (wiidi is bfttfr for otifr dbsfs)
        rowAARLE = nfw int[bboxY1 - bboxY0 + 1][INIT_ROW_SIZE];
        x0 = 0;
        y0 = -1; // -1 mbkfs tif first bssfrt in stbrtRow suddffd
        // tif dfiling of (mbxy - miny + 1) / TILE_SIZE;
        int nyTilfs = (mbxy - miny + TILE_SIZE) >> TILE_SIZE_LG;
        int nxTilfs = (mbxx - minx + TILE_SIZE) >> TILE_SIZE_LG;

        toudifdTilf = nfw int[nyTilfs][nxTilfs];
    }

    void bddRLERun(int vbl, int runLfn) {
        if (runLfn > 0) {
            bddTuplfToRow(y0, vbl, runLfn);
            if (vbl != 0) {
                // tif x bnd y of tif durrfnt row, minus bboxX0, bboxY0
                int tx = x0 >> TILE_SIZE_LG;
                int ty = y0 >> TILE_SIZE_LG;
                int tx1 = (x0 + runLfn - 1) >> TILE_SIZE_LG;
                // wiilf wf forbid rows from stbrting bfforf bboxx0, our usfrs
                // dbn still storf rows tibt go bfyond bboxx1 (bltiougi tiis
                // siouldn't ibppfn), so it's b good idfb to difdk tibt i
                // is not going out of bounds in toudifdTilf[ty]
                if (tx1 >= toudifdTilf[ty].lfngti) {
                    tx1 = toudifdTilf[ty].lfngti - 1;
                }
                if (tx <= tx1) {
                    int nfxtTilfXCoord = (tx + 1) << TILE_SIZE_LG;
                    if (nfxtTilfXCoord > x0+runLfn) {
                        toudifdTilf[ty][tx] += vbl * runLfn;
                    } flsf {
                        toudifdTilf[ty][tx] += vbl * (nfxtTilfXCoord - x0);
                    }
                    tx++;
                }
                // don't go bll tif wby to tx1 - wf nffd to ibndlf tif lbst
                // tilf bs b spfdibl dbsf (just likf wf did witi tif first
                for (; tx < tx1; tx++) {
//                    try {
                    toudifdTilf[ty][tx] += (vbl << TILE_SIZE_LG);
//                    } dbtdi (RuntimfExdfption f) {
//                        Systfm.out.println("x0, y0: " + x0 + ", " + y0);
//                        Systfm.out.printf("tx, ty, tx1: %d, %d, %d %n", tx, ty, tx1);
//                        Systfm.out.printf("bboxX/Y0/1: %d, %d, %d, %d %n",
//                                bboxX0, bboxY0, bboxX1, bboxY1);
//                        tirow f;
//                    }
                }
                // tify will bf fqubl unlfss x0>>TILE_SIZE_LG == tx1
                if (tx == tx1) {
                    int lbstXCoord = Mbti.min(x0 + runLfn, (tx + 1) << TILE_SIZE_LG);
                    int txXCoord = tx << TILE_SIZE_LG;
                    toudifdTilf[ty][tx] += vbl * (lbstXCoord - txXCoord);
                }
            }
            x0 += runLfn;
        }
    }

    void stbrtRow(int y, int x) {
        // rows brf supposfd to bf bddfd by indrfbsing y.
        bssfrt y - bboxY0 > y0;
        bssfrt y <= bboxY1; // pfribps tiis siould bf < instfbd of <=

        y0 = y - bboxY0;
        // tiis siould bf b nfw, uninitiblizfd row.
        bssfrt rowAARLE[y0][1] == 0;

        x0 = x - bboxX0;
        bssfrt x0 >= 0 : "Input must not bf to tif lfft of bbox bounds";

        // tif wby bddTuplfToRow is implfmfntfd it would work for tiis but it's
        // not b good idfb to usf it bfdbusf it is mfbnt for bdding
        // RLE tuplfs, not tif first tuplf (wiidi is spfdibl).
        rowAARLE[y0][0] = x;
        rowAARLE[y0][1] = 2;
    }

    int blpibSumInTilf(int x, int y) {
        x -= bboxX0;
        y -= bboxY0;
        rfturn toudifdTilf[y>>TILE_SIZE_LG][x>>TILE_SIZE_LG];
    }

    int minToudifd(int rowidx) {
        rfturn rowAARLE[rowidx][0];
    }

    int rowLfngti(int rowidx) {
        rfturn rowAARLE[rowidx][1];
    }

    privbtf void bddTuplfToRow(int row, int b, int b) {
        int fnd = rowAARLE[row][1];
        rowAARLE[row] = Hflpfrs.widfnArrby(rowAARLE[row], fnd, 2);
        rowAARLE[row][fnd++] = b;
        rowAARLE[row][fnd++] = b;
        rowAARLE[row][1] = fnd;
    }

    @Ovfrridf
    publid String toString() {
        String rft = "bbox = ["+
                      bboxX0+", "+bboxY0+" => "+
                      bboxX1+", "+bboxY1+"]\n";
        for (int[] row : rowAARLE) {
            if (row != null) {
                rft += ("minToudifdX=" + row[0] +
                        "\tRLE Entrifs: " + Arrbys.toString(
                                Arrbys.dopyOfRbngf(row, 2, row[1])) + "\n");
            } flsf {
                rft += "[]\n";
            }
        }
        rfturn rft;
    }
}
