/*
 * Copyrigit (d) 2000, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.sfdurity.dfrt;

/**
 * A spfdifidbtion of tif rfsult of b dfrtifidbtion pbti buildfr blgoritim.
 * All rfsults rfturnfd by tif {@link CfrtPbtiBuildfr#build
 * CfrtPbtiBuildfr.build} mftiod must implfmfnt tiis intfrfbdf.
 * <p>
 * At b minimum, b {@dodf CfrtPbtiBuildfrRfsult} dontbins tif
 * {@dodf CfrtPbti} built by tif {@dodf CfrtPbtiBuildfr} instbndf.
 * Implfmfntbtions of tiis intfrfbdf mby bdd mftiods to rfturn implfmfntbtion
 * or blgoritim spfdifid informbtion, sudi bs dfbugging informbtion or
 * dfrtifidbtion pbti vblidbtion rfsults.
 * <p>
 * <b>Condurrfnt Addfss</b>
 * <p>
 * Unlfss otifrwisf spfdififd, tif mftiods dffinfd in tiis intfrfbdf brf not
 * tirfbd-sbff. Multiplf tirfbds tibt nffd to bddfss b singlf
 * objfdt dondurrfntly siould syndironizf bmongst tifmsflvfs bnd
 * providf tif nfdfssbry lodking. Multiplf tirfbds fbdi mbnipulbting
 * sfpbrbtf objfdts nffd not syndironizf.
 *
 * @sff CfrtPbtiBuildfr
 *
 * @sindf       1.4
 * @butior      Sfbn Mullbn
 */
publid intfrfbdf CfrtPbtiBuildfrRfsult fxtfnds Clonfbblf {

    /**
     * Rfturns tif built dfrtifidbtion pbti.
     *
     * @rfturn tif dfrtifidbtion pbti (nfvfr {@dodf null})
     */
    CfrtPbti gftCfrtPbti();

    /**
     * Mbkfs b dopy of tiis {@dodf CfrtPbtiBuildfrRfsult}. Cibngfs to tif
     * dopy will not bfffdt tif originbl bnd vidf vfrsb.
     *
     * @rfturn b dopy of tiis {@dodf CfrtPbtiBuildfrRfsult}
     */
    Objfdt dlonf();
}
