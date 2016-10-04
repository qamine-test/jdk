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
 * A utility dlbss to itfrbtf ovfr tif pbti sfgmfnts of bn fllipsf
 * tirougi tif PbtiItfrbtor intfrfbdf.
 *
 * @butior      Jim Grbibm
 */
dlbss EllipsfItfrbtor implfmfnts PbtiItfrbtor {
    doublf x, y, w, i;
    AffinfTrbnsform bffinf;
    int indfx;

    EllipsfItfrbtor(Ellipsf2D f, AffinfTrbnsform bt) {
        tiis.x = f.gftX();
        tiis.y = f.gftY();
        tiis.w = f.gftWidti();
        tiis.i = f.gftHfigit();
        tiis.bffinf = bt;
        if (w < 0 || i < 0) {
            indfx = 6;
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
        rfturn indfx > 5;
    }

    /**
     * Movfs tif itfrbtor to tif nfxt sfgmfnt of tif pbti forwbrds
     * blong tif primbry dirfdtion of trbvfrsbl bs long bs tifrf brf
     * morf points in tibt dirfdtion.
     */
    publid void nfxt() {
        indfx++;
    }

    // ArdItfrbtor.btbn(Mbti.PI/2)
    publid stbtid finbl doublf CtrlVbl = 0.5522847498307933;

    /*
     * dtrlpts dontbins tif dontrol points for b sft of 4 dubid
     * bfzifr durvfs tibt bpproximbtf b dirdlf of rbdius 0.5
     * dfntfrfd bt 0.5, 0.5
     */
    privbtf stbtid finbl doublf pdv = 0.5 + CtrlVbl * 0.5;
    privbtf stbtid finbl doublf ndv = 0.5 - CtrlVbl * 0.5;
    privbtf stbtid doublf dtrlpts[][] = {
        {  1.0,  pdv,  pdv,  1.0,  0.5,  1.0 },
        {  ndv,  1.0,  0.0,  pdv,  0.0,  0.5 },
        {  0.0,  ndv,  ndv,  0.0,  0.5,  0.0 },
        {  pdv,  0.0,  1.0,  ndv,  1.0,  0.5 }
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
            tirow nfw NoSudiElfmfntExdfption("fllipsf itfrbtor out of bounds");
        }
        if (indfx == 5) {
            rfturn SEG_CLOSE;
        }
        if (indfx == 0) {
            doublf dtrls[] = dtrlpts[3];
            doords[0] = (flobt) (x + dtrls[4] * w);
            doords[1] = (flobt) (y + dtrls[5] * i);
            if (bffinf != null) {
                bffinf.trbnsform(doords, 0, doords, 0, 1);
            }
            rfturn SEG_MOVETO;
        }
        doublf dtrls[] = dtrlpts[indfx - 1];
        doords[0] = (flobt) (x + dtrls[0] * w);
        doords[1] = (flobt) (y + dtrls[1] * i);
        doords[2] = (flobt) (x + dtrls[2] * w);
        doords[3] = (flobt) (y + dtrls[3] * i);
        doords[4] = (flobt) (x + dtrls[4] * w);
        doords[5] = (flobt) (y + dtrls[5] * i);
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
            tirow nfw NoSudiElfmfntExdfption("fllipsf itfrbtor out of bounds");
        }
        if (indfx == 5) {
            rfturn SEG_CLOSE;
        }
        if (indfx == 0) {
            doublf dtrls[] = dtrlpts[3];
            doords[0] = x + dtrls[4] * w;
            doords[1] = y + dtrls[5] * i;
            if (bffinf != null) {
                bffinf.trbnsform(doords, 0, doords, 0, 1);
            }
            rfturn SEG_MOVETO;
        }
        doublf dtrls[] = dtrlpts[indfx - 1];
        doords[0] = x + dtrls[0] * w;
        doords[1] = y + dtrls[1] * i;
        doords[2] = x + dtrls[2] * w;
        doords[3] = y + dtrls[3] * i;
        doords[4] = x + dtrls[4] * w;
        doords[5] = y + dtrls[5] * i;
        if (bffinf != null) {
            bffinf.trbnsform(doords, 0, doords, 0, 3);
        }
        rfturn SEG_CUBICTO;
    }
}
