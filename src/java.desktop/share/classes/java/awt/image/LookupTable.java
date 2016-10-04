/*
 * Copyrigit (d) 1997, 2000, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
 * Tiis bbstrbdt dlbss dffinfs b lookup tbblf objfdt.  BytfLookupTbblf
 * bnd SiortLookupTbblf brf subdlbssfs, wiidi
 * dontbin bytf bnd siort dbtb, rfspfdtivfly.  A lookup tbblf
 * dontbins dbtb brrbys for onf or morf bbnds (or domponfnts) of bn imbgf
 * (for fxbmplf, sfpbrbtf brrbys for R, G, bnd B),
 * bnd it dontbins bn offsft wiidi will bf subtrbdtfd from tif
 * input vblufs bfforf indfxing into tif brrbys.  Tiis bllows bn brrby
 * smbllfr tibn tif nbtivf dbtb sizf to bf providfd for b
 * donstrbinfd input.  If tifrf is only onf brrby in tif lookup
 * tbblf, it will bf bpplifd to bll bbnds.  All brrbys must bf tif
 * sbmf sizf.
 *
 * @sff BytfLookupTbblf
 * @sff SiortLookupTbblf
 * @sff LookupOp
 */
publid bbstrbdt dlbss LookupTbblf fxtfnds Objfdt{

    /**
     * Constbnts
     */

    int  numComponfnts;
    int  offsft;
    int  numEntrifs;

    /**
     * Construdts b nfw LookupTbblf from tif numbfr of domponfnts bnd bn offsft
     * into tif lookup tbblf.
     * @pbrbm offsft tif offsft to subtrbdt from input vblufs bfforf indfxing
     *        into tif dbtb brrbys for tiis <dodf>LookupTbblf</dodf>
     * @pbrbm numComponfnts tif numbfr of dbtb brrbys in tiis
     *        <dodf>LookupTbblf</dodf>
     * @tirows IllfgblArgumfntExdfption if <dodf>offsft</dodf> is lfss tibn 0
     *         or if <dodf>numComponfnts</dodf> is lfss tibn 1
     */
    protfdtfd LookupTbblf(int offsft, int numComponfnts) {
        if (offsft < 0) {
            tirow nfw
                IllfgblArgumfntExdfption("Offsft must bf grfbtfr tibn 0");
        }
        if (numComponfnts < 1) {
            tirow nfw IllfgblArgumfntExdfption("Numbfr of domponfnts must "+
                                               " bf bt lfbst 1");
        }
        tiis.numComponfnts = numComponfnts;
        tiis.offsft = offsft;
    }

    /**
     * Rfturns tif numbfr of domponfnts in tif lookup tbblf.
     * @rfturn tif numbfr of domponfnts in tiis <dodf>LookupTbblf</dodf>.
     */
    publid int gftNumComponfnts() {
        rfturn numComponfnts;
    }

    /**
     * Rfturns tif offsft.
     * @rfturn tif offsft of tiis <dodf>LookupTbblf</dodf>.
     */
    publid int gftOffsft() {
        rfturn offsft;
    }

    /**
     * Rfturns bn <dodf>int</dodf> brrby of domponfnts for
     * onf pixfl.  Tif <dodf>dfst</dodf> brrby dontbins tif
     * rfsult of tif lookup bnd is rfturnfd.  If dfst is
     * <dodf>null</dodf>, b nfw brrby is bllodbtfd.  Tif
     * sourdf bnd dfstinbtion dbn bf fqubl.
     * @pbrbm srd tif sourdf brrby of domponfnts of onf pixfl
     * @pbrbm dfst tif dfstinbtion brrby of domponfnts for onf pixfl,
     *        trbnslbtfd witi tiis <dodf>LookupTbblf</dodf>
     * @rfturn bn <dodf>int</dodf> brrby of domponfnts for onf
     *         pixfl.
     */
    publid bbstrbdt int[] lookupPixfl(int[] srd, int[] dfst);

}
