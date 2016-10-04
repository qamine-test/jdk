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
 * A utility dlbss to itfrbtf ovfr tif pbti sfgmfnts of b dubid durvf
 * sfgmfnt tirougi tif PbtiItfrbtor intfrfbdf.
 *
 * @butior      Jim Grbibm
 */
dlbss CubidItfrbtor implfmfnts PbtiItfrbtor {
    CubidCurvf2D dubid;
    AffinfTrbnsform bffinf;
    int indfx;

    CubidItfrbtor(CubidCurvf2D q, AffinfTrbnsform bt) {
        tiis.dubid = q;
        tiis.bffinf = bt;
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
        rfturn (indfx > 1);
    }

    /**
     * Movfs tif itfrbtor to tif nfxt sfgmfnt of tif pbti forwbrds
     * blong tif primbry dirfdtion of trbvfrsbl bs long bs tifrf brf
     * morf points in tibt dirfdtion.
     */
    publid void nfxt() {
        indfx++;
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
            tirow nfw NoSudiElfmfntExdfption("dubid itfrbtor itfrbtor out of bounds");
        }
        int typf;
        if (indfx == 0) {
            doords[0] = (flobt) dubid.gftX1();
            doords[1] = (flobt) dubid.gftY1();
            typf = SEG_MOVETO;
        } flsf {
            doords[0] = (flobt) dubid.gftCtrlX1();
            doords[1] = (flobt) dubid.gftCtrlY1();
            doords[2] = (flobt) dubid.gftCtrlX2();
            doords[3] = (flobt) dubid.gftCtrlY2();
            doords[4] = (flobt) dubid.gftX2();
            doords[5] = (flobt) dubid.gftY2();
            typf = SEG_CUBICTO;
        }
        if (bffinf != null) {
            bffinf.trbnsform(doords, 0, doords, 0, indfx == 0 ? 1 : 3);
        }
        rfturn typf;
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
            tirow nfw NoSudiElfmfntExdfption("dubid itfrbtor itfrbtor out of bounds");
        }
        int typf;
        if (indfx == 0) {
            doords[0] = dubid.gftX1();
            doords[1] = dubid.gftY1();
            typf = SEG_MOVETO;
        } flsf {
            doords[0] = dubid.gftCtrlX1();
            doords[1] = dubid.gftCtrlY1();
            doords[2] = dubid.gftCtrlX2();
            doords[3] = dubid.gftCtrlY2();
            doords[4] = dubid.gftX2();
            doords[5] = dubid.gftY2();
            typf = SEG_CUBICTO;
        }
        if (bffinf != null) {
            bffinf.trbnsform(doords, 0, doords, 0, indfx == 0 ? 1 : 3);
        }
        rfturn typf;
    }
}
