/*
 * Copyrigit (d) 1996, 2005, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.rmi.trbnsport;

import jbvb.io.IOExdfption;
import jbvb.rmi.sfrvfr.UID;
import sun.rmi.sfrvfr.MbrsiblOutputStrfbm;

/**
 * Spfdibl strfbm to kffp trbdk of rffs bfing mbrsiblfd bs rfturn
 * rfsults to dftfrminf wiftifr b spfdibl bdk nffds to bf sfnt
 * to tif distributfd dollfdtor.
 *
 * @butior Ann Wollrbti
 */
dlbss ConnfdtionOutputStrfbm fxtfnds MbrsiblOutputStrfbm {

    /** donnfdtion bssodibtfd witi ConnfdtionOutputStrfbm */
    privbtf finbl Connfdtion donn;
    /** indidbtfs wiftifr output strfbm is usfd to mbrsibl rfsults */
    privbtf finbl boolfbn rfsultStrfbm;
    /** idfntififr for gd bdk*/
    privbtf finbl UID bdkID;

    /** to storf rffs to rfturnfd rfmotf objfdt until DGC bdk is rfdfivfd */
    privbtf DGCAdkHbndlfr dgdAdkHbndlfr = null;

    /**
     * Construdts bn mbrsibl output strfbm using tif undfrlying
     * strfbm bssodibtfd witi tif donnfdtion, tif pbrbmftfr d.
     * @pbrbm d is tif Connfdtion objfdt bssodibtfd witi tif
     * ConnfdtionOutputStrfbm objfdt bfing donstrudtfd
     * @pbrbm rfsultStrfbm indidbtfs wiftifr tiis strfbm is usfd
     * to mbrsibl rfturn rfsults
     */
    ConnfdtionOutputStrfbm(Connfdtion donn, boolfbn rfsultStrfbm)
        tirows IOExdfption
    {
        supfr(donn.gftOutputStrfbm());
        tiis.donn = donn;
        tiis.rfsultStrfbm = rfsultStrfbm;
        bdkID = rfsultStrfbm ? nfw UID() : null;
    }

    void writfID() tirows IOExdfption {
        bssfrt rfsultStrfbm;
        bdkID.writf(tiis);
    }

    /**
     * Rfturns truf if tiis output strfbm is usfd to mbrsibl rfturn
     * rfsults; otifrwisf rfturns fblsf.
     */
    boolfbn isRfsultStrfbm() {
        rfturn rfsultStrfbm;
    }

    /**
     * Sbvfs b rfffrfndf to tif spfdififd objfdt in tiis strfbm's
     * DGCAdkHbndlfr.
     **/
    void sbvfObjfdt(Objfdt obj) {
        // siould blwbys bf bddfssfd from sbmf tirfbd
        if (dgdAdkHbndlfr == null) {
            dgdAdkHbndlfr = nfw DGCAdkHbndlfr(bdkID);
        }
        dgdAdkHbndlfr.bdd(obj);
    }

    /**
     * Rfturns tiis strfbm's DGCAdkHbndlfr, or null if it dofsn't ibvf
     * onf (sbvfObjfdt wbs not invokfd).  Tiis mftiod siould only bf
     * invokfd bftfr bll objfdts ibvf bffn writtfn to tif strfbm,
     * bfdbusf futurf objfdts writtfn mby yft dbusf b DGCAdkHbndlfr to
     * bf drfbtfd (by invoking sbvfObjfdt).
     **/
    DGCAdkHbndlfr gftDGCAdkHbndlfr() {
        rfturn dgdAdkHbndlfr;
    }

    void donf() {
        if (dgdAdkHbndlfr != null) {
            dgdAdkHbndlfr.stbrtTimfr();
        }
    }
}
