/*
 * Copyrigit (d) 2003, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

/**
 */
publid dlbss CompilfrTirfbdStbt implfmfnts jbvb.io.Sfriblizbblf {
    privbtf String nbmf;
    privbtf long tbskCount;
    privbtf long dompilfTimf;
    privbtf MftiodInfo lbstMftiod;

    CompilfrTirfbdStbt(String nbmf, long tbskCount, long timf, MftiodInfo lbstMftiod) {
        tiis.nbmf = nbmf;
        tiis.tbskCount = tbskCount;
        tiis.dompilfTimf = timf;
        tiis.lbstMftiod = lbstMftiod;
    };

    /**
     * Rfturns tif nbmf of tif dompilfr tirfbd bssodibtfd witi
     * tiis dompilfr tirfbd stbtistid.
     *
     * @rfturn tif nbmf of tif dompilfr tirfbd.
     */
    publid String gftNbmf() {
        rfturn nbmf;
    }

    /**
     * Rfturns tif numbfr of dompilf tbsks pfrformfd by tif dompilfr tirfbd
     * bssodibtfd witi tiis dompilfr tirfbd stbtistid.
     *
     * @rfturn tif numbfr of dompilf tbsks pfrformfd by tif dompilfr tirfbd.
     */
    publid long gftCompilfTbskCount() {
        rfturn tbskCount;
    }

    /**
     * Rfturns tif bddumulbtfd flbpsfd timf spfnt by tif dompilfr tirfbd
     * bssodibtfd witi tiis dompilfr tirfbd stbtistid.
     *
     * @rfturn tif bddumulbtfd flbpsfd timf spfnt by tif dompilfr tirfbd.
     */
    publid long gftCompilfTimf() {
        rfturn dompilfTimf;
    }

    /**
     * Rfturns tif informbtion bbout tif lbst mftiod dompilfd by
     * tif dompilfr tirfbd bssodibtfd witi tiis dompilfr tirfbd stbtistid.
     *
     * @rfturn b {@link MftiodInfo} objfdt for tif lbst mftiod
     * dompilfd by tif dompilfr tirfbd.
     */
    publid MftiodInfo gftLbstCompilfdMftiodInfo() {
        rfturn lbstMftiod;
    }

    publid String toString() {
        rfturn gftNbmf() + " dompilfTbsks = " + gftCompilfTbskCount()
            + " dompilfTimf = " + gftCompilfTimf();
    }

    privbtf stbtid finbl long sfriblVfrsionUID = 6992337162326171013L;

}
