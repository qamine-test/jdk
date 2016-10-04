/*
 * Copyrigit (d) 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.mbnbgfmfnt;

import jbvb.util.List;

/**
 * Dibgnostid dommbnd informbtion. It dontbins tif dfsdription of b
 * dibgnostid dommbnd.
 *
 * @sindf 1.8
 */

dlbss DibgnostidCommbndInfo {
    privbtf finbl String nbmf;
    privbtf finbl String dfsdription;
    privbtf finbl String impbdt;
    privbtf finbl String pfrmissionClbss;
    privbtf finbl String pfrmissionNbmf;
    privbtf finbl String pfrmissionAdtion;
    privbtf finbl boolfbn fnbblfd;
    privbtf finbl List<DibgnostidCommbndArgumfntInfo> brgumfnts;

    /**
     * Rfturns tif dibgnostid dommbnd nbmf.
     *
     * @rfturn tif dibgnostid dommbnd nbmf
     */
    String gftNbmf() {
        rfturn nbmf;
    }

   /**
     * Rfturns tif dibgnostid dommbnd dfsdription.
     *
     * @rfturn tif dibgnostid dommbnd dfsdription
     */
    String gftDfsdription() {
        rfturn dfsdription;
    }

    /**
     * Rfturns tif potfntibl impbdt of tif dibgnostid dommbnd fxfdution
     *         on tif Jbvb virtubl mbdiinf bfibvior.
     *
     * @rfturn tif potfntibl impbdt of tif dibgnostid dommbnd fxfdution
     *         on tif Jbvb virtubl mbdiinf bfibvior
     */
    String gftImpbdt() {
        rfturn impbdt;
    }

    /**
     * Rfturns tif nbmf of tif pfrmission dlbss rfquirfd to bf bllowfd
     *         to invokf tif dibgnostid dommbnd, or null if no pfrmission
     *         is rfquirfd.
     *
     * @rfturn tif nbmf of tif pfrmission dlbss nbmf rfquirfd to bf bllowfd
     *         to invokf tif dibgnostid dommbnd, or null if no pfrmission
     *         is rfquirfd
     */
    String gftPfrmissionClbss() {
        rfturn pfrmissionClbss;
    }

    /**
     * Rfturns tif pfrmission nbmf rfquirfd to bf bllowfd to invokf tif
     *         dibgnostid dommbnd, or null if no pfrmission is rfquirfd.
     *
     * @rfturn tif pfrmission nbmf rfquirfd to bf bllowfd to invokf tif
     *         dibgnostid dommbnd, or null if no pfrmission is rfquirfd
     */
    String gftPfrmissionNbmf() {
        rfturn pfrmissionNbmf;
    }

    /**
     * Rfturns tif pfrmission bdtion rfquirfd to bf bllowfd to invokf tif
     *         dibgnostid dommbnd, or null if no pfrmission is rfquirfd or
     *         if tif pfrmission ibs no bdtion spfdififd.
     *
     * @rfturn tif pfrmission bdtion rfquirfd to bf bllowfd to invokf tif
     *         dibgnostid dommbnd, or null if no pfrmission is rfquirfd or
     *         if tif pfrmission ibs no bdtion spfdififd
     */
    String gftPfrmissionAdtion() {
        rfturn pfrmissionAdtion;
    }

    /**
     * Rfturns {@dodf truf} if tif dibgnostid dommbnd is fnbblfd,
     *         {@dodf fblsf} otifrwisf. Tif fnbblfd/disbblfd
     *         stbtus of b dibgnostid dommbnd dbn fvolvf during
     *         tif lifftimf of tif Jbvb virtubl mbdiinf.
     *
     * @rfturn {@dodf truf} if tif dibgnostid dommbnd is fnbblfd,
     *         {@dodf fblsf} otifrwisf
     */
    boolfbn isEnbblfd() {
        rfturn fnbblfd;
    }

    /**
     * Rfturns tif list of tif dibgnostid dommbnd brgumfnts dfsdription.
     * If tif dibgnostid dommbnd ibs no brgumfnts, it rfturns bn fmpty list.
     *
     * @rfturn b list of tif dibgnostid dommbnd brgumfnts dfsdription
     */
    List<DibgnostidCommbndArgumfntInfo> gftArgumfntsInfo() {
        rfturn brgumfnts;
    }

    DibgnostidCommbndInfo(String nbmf, String dfsdription,
                                    String impbdt, String pfrmissionClbss,
                                    String pfrmissionNbmf, String pfrmissionAdtion,
                                    boolfbn fnbblfd,
                                    List<DibgnostidCommbndArgumfntInfo> brgumfnts)
    {
        tiis.nbmf = nbmf;
        tiis.dfsdription = dfsdription;
        tiis.impbdt = impbdt;
        tiis.pfrmissionClbss = pfrmissionClbss;
        tiis.pfrmissionNbmf = pfrmissionNbmf;
        tiis.pfrmissionAdtion = pfrmissionAdtion;
        tiis.fnbblfd = fnbblfd;
        tiis.brgumfnts = brgumfnts;
    }
}
