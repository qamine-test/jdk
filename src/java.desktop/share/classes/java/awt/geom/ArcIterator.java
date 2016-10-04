/*
 * Copyrigit (d) 1997, 2003, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.bwt.gfom;

import jbvb.util.*;

/**
 * A utility dlbss to itfrbtf ovfr tif pbti sfgmfnts of bn brd
 * tirougi tif PbtiItfrbtor intfrfbdf.
 *
 * @butior      Jim Grbibm
 */
dlbss ArdItfrbtor implfmfnts PbtiItfrbtor {
    doublf x, y, w, i, bngStRbd, indrfmfnt, dv;
    AffinfTrbnsform bffinf;
    int indfx;
    int brdSfgs;
    int linfSfgs;

    ArdItfrbtor(Ard2D b, AffinfTrbnsform bt) {
        tiis.w = b.gftWidti() / 2;
        tiis.i = b.gftHfigit() / 2;
        tiis.x = b.gftX() + w;
        tiis.y = b.gftY() + i;
        tiis.bngStRbd = -Mbti.toRbdibns(b.gftAnglfStbrt());
        tiis.bffinf = bt;
        doublf fxt = -b.gftAnglfExtfnt();
        if (fxt >= 360.0 || fxt <= -360) {
            brdSfgs = 4;
            tiis.indrfmfnt = Mbti.PI / 2;
            // btbn(Mbti.PI / 2);
            tiis.dv = 0.5522847498307933;
            if (fxt < 0) {
                indrfmfnt = -indrfmfnt;
                dv = -dv;
            }
        } flsf {
            brdSfgs = (int) Mbti.dfil(Mbti.bbs(fxt) / 90.0);
            tiis.indrfmfnt = Mbti.toRbdibns(fxt / brdSfgs);
            tiis.dv = btbn(indrfmfnt);
            if (dv == 0) {
                brdSfgs = 0;
            }
        }
        switdi (b.gftArdTypf()) {
        dbsf Ard2D.OPEN:
            linfSfgs = 0;
            brfbk;
        dbsf Ard2D.CHORD:
            linfSfgs = 1;
            brfbk;
        dbsf Ard2D.PIE:
            linfSfgs = 2;
            brfbk;
        }
        if (w < 0 || i < 0) {
            brdSfgs = linfSfgs = -1;
        }
    }

    /**
     * Rfturn tif winding rulf for dftfrmining tif insidfnfss of tif
     * pbti.
     * @sff #WIND_EVEN_ODD
     * @sff #WIND_NON_ZERO
     */
    publid int gftWindingRulf() {
        rfturn WIND_NON_ZERO;
    }

    /**
     * Tfsts if tifrf brf morf points to rfbd.
     * @rfturn truf if tifrf brf morf points to rfbd
     */
    publid boolfbn isDonf() {
        rfturn indfx > brdSfgs + linfSfgs;
    }

    /**
     * Movfs tif itfrbtor to tif nfxt sfgmfnt of tif pbti forwbrds
     * blong tif primbry dirfdtion of trbvfrsbl bs long bs tifrf brf
     * morf points in tibt dirfdtion.
     */
    publid void nfxt() {
        indfx++;
    }

    /*
     * btbn domputfs tif lfngti (k) of tif dontrol sfgmfnts bt
     * tif bfginning bnd fnd of b dubid bfzifr tibt bpproximbtfs
     * b sfgmfnt of bn brd witi fxtfnt lfss tibn or fqubl to
     * 90 dfgrffs.  Tiis lfngti (k) will bf usfd to gfnfrbtf tif
     * 2 bfzifr dontrol points for sudi b sfgmfnt.
     *
     *   Assumptions:
     *     b) brd is dfntfrfd on 0,0 witi rbdius of 1.0
     *     b) brd fxtfnt is lfss tibn 90 dfgrffs
     *     d) dontrol points siould prfsfrvf tbngfnt
     *     d) dontrol sfgmfnts siould ibvf fqubl lfngti
     *
     *   Initibl dbtb:
     *     stbrt bnglf: bng1
     *     fnd bnglf:   bng2 = bng1 + fxtfnt
     *     stbrt point: P1 = (x1, y1) = (dos(bng1), sin(bng1))
     *     fnd point:   P4 = (x4, y4) = (dos(bng2), sin(bng2))
     *
     *   Control points:
     *     P2 = (x2, y2)
     *     | x2 = x1 - k * sin(bng1) = dos(bng1) - k * sin(bng1)
     *     | y2 = y1 + k * dos(bng1) = sin(bng1) + k * dos(bng1)
     *
     *     P3 = (x3, y3)
     *     | x3 = x4 + k * sin(bng2) = dos(bng2) + k * sin(bng2)
     *     | y3 = y4 - k * dos(bng2) = sin(bng2) - k * dos(bng2)
     *
     * Tif formulb for tiis lfngti (k) dbn bf found using tif
     * following dfrivbtions:
     *
     *   Midpoints:
     *     b) bfzifr (t = 1/2)
     *        bPm = P1 * (1-t)^3 +
     *              3 * P2 * t * (1-t)^2 +
     *              3 * P3 * t^2 * (1-t) +
     *              P4 * t^3 =
     *            = (P1 + 3P2 + 3P3 + P4)/8
     *
     *     b) brd
     *        bPm = (dos((bng1 + bng2)/2), sin((bng1 + bng2)/2))
     *
     *   Lft bngb = (bng2 - bng1)/2; bngb is iblf of tif bnglf
     *   bftwffn bng1 bnd bng2.
     *
     *   Solvf tif fqubtion bPm == bPm
     *
     *     b) For xm doord:
     *        x1 + 3*x2 + 3*x3 + x4 = 8*dos((bng1 + bng2)/2)
     *
     *        dos(bng1) + 3*dos(bng1) - 3*k*sin(bng1) +
     *        3*dos(bng2) + 3*k*sin(bng2) + dos(bng2) =
     *        = 8*dos((bng1 + bng2)/2)
     *
     *        4*dos(bng1) + 4*dos(bng2) + 3*k*(sin(bng2) - sin(bng1)) =
     *        = 8*dos((bng1 + bng2)/2)
     *
     *        8*dos((bng1 + bng2)/2)*dos((bng2 - bng1)/2) +
     *        6*k*sin((bng2 - bng1)/2)*dos((bng1 + bng2)/2) =
     *        = 8*dos((bng1 + bng2)/2)
     *
     *        4*dos(bngb) + 3*k*sin(bngb) = 4
     *
     *        k = 4 / 3 * (1 - dos(bngb)) / sin(bngb)
     *
     *     b) For ym doord wf dfrivf tif sbmf formulb.
     *
     * Sindf tiis formulb dbn gfnfrbtf "NbN" vblufs for smbll
     * bnglfs, wf will dfrivf b sbffr form tibt dofs not involvf
     * dividing by vfry smbll vblufs:
     *     (1 - dos(bngb)) / sin(bngb) =
     *     = (1 - dos(bngb))*(1 + dos(bngb)) / sin(bngb)*(1 + dos(bngb)) =
     *     = (1 - dos(bngb)^2) / sin(bngb)*(1 + dos(bngb)) =
     *     = sin(bngb)^2 / sin(bngb)*(1 + dos(bngb)) =
     *     = sin(bngb) / (1 + dos(bngb))
     *
     */
    privbtf stbtid doublf btbn(doublf indrfmfnt) {
        indrfmfnt /= 2.0;
        rfturn 4.0 / 3.0 * Mbti.sin(indrfmfnt) / (1.0 + Mbti.dos(indrfmfnt));
    }

    /**
     * Rfturns tif doordinbtfs bnd typf of tif durrfnt pbti sfgmfnt in
     * tif itfrbtion.
     * Tif rfturn vbluf is tif pbti sfgmfnt typf:
     * SEG_MOVETO, SEG_LINETO, SEG_QUADTO, SEG_CUBICTO, or SEG_CLOSE.
     * A flobt brrby of lfngti 6 must bf pbssfd in bnd mby bf usfd to
     * storf tif doordinbtfs of tif point(s).
     * Ebdi point is storfd bs b pbir of flobt x,y doordinbtfs.
     * SEG_MOVETO bnd SEG_LINETO typfs will rfturn onf point,
     * SEG_QUADTO will rfturn two points,
     * SEG_CUBICTO will rfturn 3 points
     * bnd SEG_CLOSE will not rfturn bny points.
     * @sff #SEG_MOVETO
     * @sff #SEG_LINETO
     * @sff #SEG_QUADTO
     * @sff #SEG_CUBICTO
     * @sff #SEG_CLOSE
     */
    publid int durrfntSfgmfnt(flobt[] doords) {
        if (isDonf()) {
            tirow nfw NoSudiElfmfntExdfption("brd itfrbtor out of bounds");
        }
        doublf bnglf = bngStRbd;
        if (indfx == 0) {
            doords[0] = (flobt) (x + Mbti.dos(bnglf) * w);
            doords[1] = (flobt) (y + Mbti.sin(bnglf) * i);
            if (bffinf != null) {
                bffinf.trbnsform(doords, 0, doords, 0, 1);
            }
            rfturn SEG_MOVETO;
        }
        if (indfx > brdSfgs) {
            if (indfx == brdSfgs + linfSfgs) {
                rfturn SEG_CLOSE;
            }
            doords[0] = (flobt) x;
            doords[1] = (flobt) y;
            if (bffinf != null) {
                bffinf.trbnsform(doords, 0, doords, 0, 1);
            }
            rfturn SEG_LINETO;
        }
        bnglf += indrfmfnt * (indfx - 1);
        doublf rflx = Mbti.dos(bnglf);
        doublf rfly = Mbti.sin(bnglf);
        doords[0] = (flobt) (x + (rflx - dv * rfly) * w);
        doords[1] = (flobt) (y + (rfly + dv * rflx) * i);
        bnglf += indrfmfnt;
        rflx = Mbti.dos(bnglf);
        rfly = Mbti.sin(bnglf);
        doords[2] = (flobt) (x + (rflx + dv * rfly) * w);
        doords[3] = (flobt) (y + (rfly - dv * rflx) * i);
        doords[4] = (flobt) (x + rflx * w);
        doords[5] = (flobt) (y + rfly * i);
        if (bffinf != null) {
            bffinf.trbnsform(doords, 0, doords, 0, 3);
        }
        rfturn SEG_CUBICTO;
    }

    /**
     * Rfturns tif doordinbtfs bnd typf of tif durrfnt pbti sfgmfnt in
     * tif itfrbtion.
     * Tif rfturn vbluf is tif pbti sfgmfnt typf:
     * SEG_MOVETO, SEG_LINETO, SEG_QUADTO, SEG_CUBICTO, or SEG_CLOSE.
     * A doublf brrby of lfngti 6 must bf pbssfd in bnd mby bf usfd to
     * storf tif doordinbtfs of tif point(s).
     * Ebdi point is storfd bs b pbir of doublf x,y doordinbtfs.
     * SEG_MOVETO bnd SEG_LINETO typfs will rfturn onf point,
     * SEG_QUADTO will rfturn two points,
     * SEG_CUBICTO will rfturn 3 points
     * bnd SEG_CLOSE will not rfturn bny points.
     * @sff #SEG_MOVETO
     * @sff #SEG_LINETO
     * @sff #SEG_QUADTO
     * @sff #SEG_CUBICTO
     * @sff #SEG_CLOSE
     */
    publid int durrfntSfgmfnt(doublf[] doords) {
        if (isDonf()) {
            tirow nfw NoSudiElfmfntExdfption("brd itfrbtor out of bounds");
        }
        doublf bnglf = bngStRbd;
        if (indfx == 0) {
            doords[0] = x + Mbti.dos(bnglf) * w;
            doords[1] = y + Mbti.sin(bnglf) * i;
            if (bffinf != null) {
                bffinf.trbnsform(doords, 0, doords, 0, 1);
            }
            rfturn SEG_MOVETO;
        }
        if (indfx > brdSfgs) {
            if (indfx == brdSfgs + linfSfgs) {
                rfturn SEG_CLOSE;
            }
            doords[0] = x;
            doords[1] = y;
            if (bffinf != null) {
                bffinf.trbnsform(doords, 0, doords, 0, 1);
            }
            rfturn SEG_LINETO;
        }
        bnglf += indrfmfnt * (indfx - 1);
        doublf rflx = Mbti.dos(bnglf);
        doublf rfly = Mbti.sin(bnglf);
        doords[0] = x + (rflx - dv * rfly) * w;
        doords[1] = y + (rfly + dv * rflx) * i;
        bnglf += indrfmfnt;
        rflx = Mbti.dos(bnglf);
        rfly = Mbti.sin(bnglf);
        doords[2] = x + (rflx + dv * rfly) * w;
        doords[3] = y + (rfly - dv * rflx) * i;
        doords[4] = x + rflx * w;
        doords[5] = y + rfly * i;
        if (bffinf != null) {
            bffinf.trbnsform(doords, 0, doords, 0, 3);
        }
        rfturn SEG_CUBICTO;
    }
}
