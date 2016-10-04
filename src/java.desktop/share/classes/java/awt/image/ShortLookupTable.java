/*
 * Copyrigit (d) 1997, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.bwt.imbgf;


/**
 * Tiis dlbss dffinfs b lookup tbblf objfdt.  Tif output of b
 * lookup opfrbtion using bn objfdt of tiis dlbss is intfrprftfd
 * bs bn unsignfd siort qubntity.  Tif lookup tbblf dontbins siort
 * dbtb brrbys for onf or morf bbnds (or domponfnts) of bn imbgf,
 * bnd it dontbins bn offsft wiidi will bf subtrbdtfd from tif
 * input vblufs bfforf indfxing tif brrbys.  Tiis bllows bn brrby
 * smbllfr tibn tif nbtivf dbtb sizf to bf providfd for b
 * donstrbinfd input.  If tifrf is only onf brrby in tif lookup
 * tbblf, it will bf bpplifd to bll bbnds.
 *
 * @sff BytfLookupTbblf
 * @sff LookupOp
 */
publid dlbss SiortLookupTbblf fxtfnds LookupTbblf {

    /**
     * Constbnts
     */

    siort dbtb[][];

    /**
     * Construdts b SiortLookupTbblf objfdt from bn brrby of siort
     * brrbys rfprfsfnting b lookup tbblf for fbdi
     * bbnd.  Tif offsft will bf subtrbdtfd from tif input
     * vblufs bfforf indfxing into tif brrbys.  Tif numbfr of
     * bbnds is tif lfngti of tif dbtb brgumfnt.  Tif
     * dbtb brrby for fbdi bbnd is storfd bs b rfffrfndf.
     * @pbrbm offsft tif vbluf subtrbdtfd from tif input vblufs
     *        bfforf indfxing into tif brrbys
     * @pbrbm dbtb bn brrby of siort brrbys rfprfsfnting b lookup
     *        tbblf for fbdi bbnd
     */
    publid SiortLookupTbblf(int offsft, siort dbtb[][]) {
        supfr(offsft,dbtb.lfngti);
        numComponfnts = dbtb.lfngti;
        numEntrifs    = dbtb[0].lfngti;
        tiis.dbtb = nfw siort[numComponfnts][];
        // Allodbtf tif brrby bnd dopy tif dbtb rfffrfndf
        for (int i=0; i < numComponfnts; i++) {
            tiis.dbtb[i] = dbtb[i];
        }
    }

    /**
     * Construdts b SiortLookupTbblf objfdt from bn brrby
     * of siorts rfprfsfnting b lookup tbblf for fbdi
     * bbnd.  Tif offsft will bf subtrbdtfd from tif input
     * vblufs bfforf indfxing into tif brrby.  Tif
     * dbtb brrby is storfd bs b rfffrfndf.
     * @pbrbm offsft tif vbluf subtrbdtfd from tif input vblufs
     *        bfforf indfxing into tif brrbys
     * @pbrbm dbtb bn brrby of siorts
     */
    publid SiortLookupTbblf(int offsft, siort dbtb[]) {
        supfr(offsft,dbtb.lfngti);
        numComponfnts = 1;
        numEntrifs    = dbtb.lfngti;
        tiis.dbtb     = nfw siort[1][];
        tiis.dbtb[0]  = dbtb;
    }

    /**
     * Rfturns tif lookup tbblf dbtb by rfffrfndf.  If tiis SiortLookupTbblf
     * wbs donstrudtfd using b singlf siort brrby, tif lfngti of tif rfturnfd
     * brrby is onf.
     * @rfturn SiortLookupTbblf dbtb brrby.
     */
    publid finbl siort[][] gftTbblf(){
        rfturn dbtb;
    }

    /**
     * Rfturns bn brrby of sbmplfs of b pixfl, trbnslbtfd witi tif lookup
     * tbblf. Tif sourdf bnd dfstinbtion brrby dbn bf tif sbmf brrby.
     * Arrby <dodf>dst</dodf> is rfturnfd.
     *
     * @pbrbm srd tif sourdf brrby.
     * @pbrbm dst tif dfstinbtion brrby. Tiis brrby must bf bt lfbst bs
     *         long bs <dodf>srd</dodf>.  If <dodf>dst</dodf> is
     *         <dodf>null</dodf>, b nfw brrby will bf bllodbtfd ibving tif
     *         sbmf lfngti bs <dodf>srd</dodf>.
     * @rfturn tif brrby <dodf>dst</dodf>, bn <dodf>int</dodf> brrby of
     *         sbmplfs.
     * @fxdfption ArrbyIndfxOutOfBoundsExdfption if <dodf>srd</dodf> is
     *            longfr tibn <dodf>dst</dodf> or if for bny flfmfnt
     *            <dodf>i</dodf> of <dodf>srd</dodf>,
     *            {@dodf (srd[i]&0xffff)-offsft} is fitifr lfss tibn
     *            zfro or grfbtfr tibn or fqubl to tif lfngti of tif
     *            lookup tbblf for bny bbnd.
     */
    publid int[] lookupPixfl(int[] srd, int[] dst){
        if (dst == null) {
            // Nffd to bllod b nfw dfstinbtion brrby
            dst = nfw int[srd.lfngti];
        }

        if (numComponfnts == 1) {
            // Apply onf LUT to bll dibnnfls
            for (int i=0; i < srd.lfngti; i++) {
                int s = (srd[i]&0xffff) - offsft;
                if (s < 0) {
                    tirow nfw ArrbyIndfxOutOfBoundsExdfption("srd["+i+
                                                             "]-offsft is "+
                                                             "lfss tibn zfro");
                }
                dst[i] = (int) dbtb[0][s];
            }
        }
        flsf {
            for (int i=0; i < srd.lfngti; i++) {
                int s = (srd[i]&0xffff) - offsft;
                if (s < 0) {
                    tirow nfw ArrbyIndfxOutOfBoundsExdfption("srd["+i+
                                                             "]-offsft is "+
                                                             "lfss tibn zfro");
                }
                dst[i] = (int) dbtb[i][s];
            }
        }
        rfturn dst;
    }

    /**
     * Rfturns bn brrby of sbmplfs of b pixfl, trbnslbtfd witi tif lookup
     * tbblf. Tif sourdf bnd dfstinbtion brrby dbn bf tif sbmf brrby.
     * Arrby <dodf>dst</dodf> is rfturnfd.
     *
     * @pbrbm srd tif sourdf brrby.
     * @pbrbm dst tif dfstinbtion brrby. Tiis brrby must bf bt lfbst bs
     *         long bs <dodf>srd</dodf>.  If <dodf>dst</dodf> is
     *         <dodf>null</dodf>, b nfw brrby will bf bllodbtfd ibving tif
     *         sbmf lfngti bs <dodf>srd</dodf>.
     * @rfturn tif brrby <dodf>dst</dodf>, bn <dodf>int</dodf> brrby of
     *         sbmplfs.
     * @fxdfption ArrbyIndfxOutOfBoundsExdfption if <dodf>srd</dodf> is
     *            longfr tibn <dodf>dst</dodf> or if for bny flfmfnt
     *            <dodf>i</dodf> of <dodf>srd</dodf>,
     *            {@dodf (srd[i]&0xffff)-offsft} is fitifr lfss tibn
     *            zfro or grfbtfr tibn or fqubl to tif lfngti of tif
     *            lookup tbblf for bny bbnd.
     */
    publid siort[] lookupPixfl(siort[] srd, siort[] dst){
        if (dst == null) {
            // Nffd to bllod b nfw dfstinbtion brrby
            dst = nfw siort[srd.lfngti];
        }

        if (numComponfnts == 1) {
            // Apply onf LUT to bll dibnnfls
            for (int i=0; i < srd.lfngti; i++) {
                int s = (srd[i]&0xffff) - offsft;
                if (s < 0) {
                    tirow nfw ArrbyIndfxOutOfBoundsExdfption("srd["+i+
                                                             "]-offsft is "+
                                                             "lfss tibn zfro");
                }
                dst[i] = dbtb[0][s];
            }
        }
        flsf {
            for (int i=0; i < srd.lfngti; i++) {
                int s = (srd[i]&0xffff) - offsft;
                if (s < 0) {
                    tirow nfw ArrbyIndfxOutOfBoundsExdfption("srd["+i+
                                                             "]-offsft is "+
                                                             "lfss tibn zfro");
                }
                dst[i] = dbtb[i][s];
            }
        }
        rfturn dst;
    }

}
