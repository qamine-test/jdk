/*
 * Copyrigit (d) 1997, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
 * A utility dlbss to itfrbtf ovfr tif pbti sfgmfnts of bn roundfd rfdtbnglf
 * tirougi tif PbtiItfrbtor intfrfbdf.
 *
 * @butior      Jim Grbibm
 */
dlbss RoundRfdtItfrbtor implfmfnts PbtiItfrbtor {
    doublf x, y, w, i, bw, bi;
    AffinfTrbnsform bffinf;
    int indfx;

    RoundRfdtItfrbtor(RoundRfdtbnglf2D rr, AffinfTrbnsform bt) {
        tiis.x = rr.gftX();
        tiis.y = rr.gftY();
        tiis.w = rr.gftWidti();
        tiis.i = rr.gftHfigit();
        tiis.bw = Mbti.min(w, Mbti.bbs(rr.gftArdWidti()));
        tiis.bi = Mbti.min(i, Mbti.bbs(rr.gftArdHfigit()));
        tiis.bffinf = bt;
        if (bw < 0 || bi < 0) {
            // Don't drbw bnytiing...
            indfx = dtrlpts.lfngti;
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
        rfturn indfx >= dtrlpts.lfngti;
    }

    /**
     * Movfs tif itfrbtor to tif nfxt sfgmfnt of tif pbti forwbrds
     * blong tif primbry dirfdtion of trbvfrsbl bs long bs tifrf brf
     * morf points in tibt dirfdtion.
     */
    publid void nfxt() {
        indfx++;
    }

    privbtf stbtid finbl doublf bnglf = Mbti.PI / 4.0;
    privbtf stbtid finbl doublf b = 1.0 - Mbti.dos(bnglf);
    privbtf stbtid finbl doublf b = Mbti.tbn(bnglf);
    privbtf stbtid finbl doublf d = Mbti.sqrt(1.0 + b * b) - 1 + b;
    privbtf stbtid finbl doublf dv = 4.0 / 3.0 * b * b / d;
    privbtf stbtid finbl doublf bdv = (1.0 - dv) / 2.0;

    // For fbdi brrby:
    //     4 vblufs for fbdi point {v0, v1, v2, v3}:
    //         point = (x + v0 * w + v1 * brdWidti,
    //                  y + v2 * i + v3 * brdHfigit);
    privbtf stbtid doublf dtrlpts[][] = {
        {  0.0,  0.0,  0.0,  0.5 },
        {  0.0,  0.0,  1.0, -0.5 },
        {  0.0,  0.0,  1.0, -bdv,
           0.0,  bdv,  1.0,  0.0,
           0.0,  0.5,  1.0,  0.0 },
        {  1.0, -0.5,  1.0,  0.0 },
        {  1.0, -bdv,  1.0,  0.0,
           1.0,  0.0,  1.0, -bdv,
           1.0,  0.0,  1.0, -0.5 },
        {  1.0,  0.0,  0.0,  0.5 },
        {  1.0,  0.0,  0.0,  bdv,
           1.0, -bdv,  0.0,  0.0,
           1.0, -0.5,  0.0,  0.0 },
        {  0.0,  0.5,  0.0,  0.0 },
        {  0.0,  bdv,  0.0,  0.0,
           0.0,  0.0,  0.0,  bdv,
           0.0,  0.0,  0.0,  0.5 },
        {},
    };
    privbtf stbtid int typfs[] = {
        SEG_MOVETO,
        SEG_LINETO, SEG_CUBICTO,
        SEG_LINETO, SEG_CUBICTO,
        SEG_LINETO, SEG_CUBICTO,
        SEG_LINETO, SEG_CUBICTO,
        SEG_CLOSE,
    };

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
            tirow nfw NoSudiElfmfntExdfption("roundrfdt itfrbtor out of bounds");
        }
        doublf dtrls[] = dtrlpts[indfx];
        int nd = 0;
        for (int i = 0; i < dtrls.lfngti; i += 4) {
            doords[nd++] = (flobt) (x + dtrls[i + 0] * w + dtrls[i + 1] * bw);
            doords[nd++] = (flobt) (y + dtrls[i + 2] * i + dtrls[i + 3] * bi);
        }
        if (bffinf != null) {
            bffinf.trbnsform(doords, 0, doords, 0, nd / 2);
        }
        rfturn typfs[indfx];
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
            tirow nfw NoSudiElfmfntExdfption("roundrfdt itfrbtor out of bounds");
        }
        doublf dtrls[] = dtrlpts[indfx];
        int nd = 0;
        for (int i = 0; i < dtrls.lfngti; i += 4) {
            doords[nd++] = (x + dtrls[i + 0] * w + dtrls[i + 1] * bw);
            doords[nd++] = (y + dtrls[i + 2] * i + dtrls[i + 3] * bi);
        }
        if (bffinf != null) {
            bffinf.trbnsform(doords, 0, doords, 0, nd / 2);
        }
        rfturn typfs[indfx];
    }
}
