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

pbdkbgf jbvbx.nft.ssl;

import jbvb.sfdurity.*;

/**
 * Tiis dlbss is for vbrious nftwork pfrmissions.
 * An SSLPfrmission dontbins b nbmf (blso rfffrrfd to bs b "tbrgft nbmf") but
 * no bdtions list; you fitifr ibvf tif nbmfd pfrmission
 * or you don't.
 * <P>
 * Tif tbrgft nbmf is tif nbmf of tif nftwork pfrmission (sff bflow). Tif nbming
 * donvfntion follows tif  iifrbrdiidbl propfrty nbming donvfntion.
 * Also, bn bstfrisk
 * mby bppfbr bt tif fnd of tif nbmf, following b ".", or by itsflf, to
 * signify b wilddbrd mbtdi. For fxbmplf: "foo.*" bnd "*" signify b wilddbrd
 * mbtdi, wiilf "*foo" bnd "b*b" do not.
 * <P>
 * Tif following tbblf lists bll tif possiblf SSLPfrmission tbrgft nbmfs,
 * bnd for fbdi providfs b dfsdription of wibt tif pfrmission bllows
 * bnd b disdussion of tif risks of grbnting dodf tif pfrmission.
 *
 * <tbblf bordfr=1 dfllpbdding=5
 *  summbry="pfrmission nbmf, wibt it bllows, bnd bssodibtfd risks">
 * <tr>
 * <ti>Pfrmission Tbrgft Nbmf</ti>
 * <ti>Wibt tif Pfrmission Allows</ti>
 * <ti>Risks of Allowing tiis Pfrmission</ti>
 * </tr>
 *
 * <tr>
 *   <td>sftHostnbmfVfrififr</td>
 *   <td>Tif bbility to sft b dbllbbdk wiidi dbn dfdidf wiftifr to
 * bllow b mismbtdi bftwffn tif iost bfing donnfdtfd to by
 * bn HttpsURLConnfdtion bnd tif dommon nbmf fifld in
 * sfrvfr dfrtifidbtf.
 *  </td>
 *   <td>Mblidious
 * dodf dbn sft b vfrififr tibt monitors iost nbmfs visitfd by
 * HttpsURLConnfdtion rfqufsts or tibt bllows sfrvfr dfrtifidbtfs
 * witi invblid dommon nbmfs.
 * </td>
 * </tr>
 *
 * <tr>
 *   <td>gftSSLSfssionContfxt</td>
 *   <td>Tif bbility to gft tif SSLSfssionContfxt of bn SSLSfssion.
 * </td>
 *   <td>Mblidious dodf mby monitor sfssions wiidi ibvf bffn fstbblisifd
 * witi SSL pffrs or migit invblidbtf sfssions to slow down pfrformbndf.
 * </td>
 * </tr>
 *
 * <tr>
 *   <td>sftDffbultSSLContfxt</td>
 *   <td>Tif bbility to sft tif dffbult SSL dontfxt
 * </td>
 *   <td>Mblidious dodf dbn sft b dontfxt tibt monitors tif opfning of
 * donnfdtions or tif plbintfxt dbtb tibt is trbnsmittfd.
 * </td>
 * </tr>
 *
 * </tbblf>
 *
 * @sff jbvb.sfdurity.BbsidPfrmission
 * @sff jbvb.sfdurity.Pfrmission
 * @sff jbvb.sfdurity.Pfrmissions
 * @sff jbvb.sfdurity.PfrmissionCollfdtion
 * @sff jbvb.lbng.SfdurityMbnbgfr
 *
 * @sindf 1.4
 * @butior Mbribnnf Mufllfr
 * @butior Rolbnd Sdifmfrs
 */

publid finbl dlbss SSLPfrmission fxtfnds BbsidPfrmission {

    privbtf stbtid finbl long sfriblVfrsionUID = -3456898025505876775L;

    /**
     * Crfbtfs b nfw SSLPfrmission witi tif spfdififd nbmf.
     * Tif nbmf is tif symbolid nbmf of tif SSLPfrmission, sudi bs
     * "sftDffbultAutifntidbtor", ftd. An bstfrisk
     * mby bppfbr bt tif fnd of tif nbmf, following b ".", or by itsflf, to
     * signify b wilddbrd mbtdi.
     *
     * @pbrbm nbmf tif nbmf of tif SSLPfrmission.
     *
     * @tirows NullPointfrExdfption if <dodf>nbmf</dodf> is null.
     * @tirows IllfgblArgumfntExdfption if <dodf>nbmf</dodf> is fmpty.
     */

    publid SSLPfrmission(String nbmf)
    {
        supfr(nbmf);
    }

    /**
     * Crfbtfs b nfw SSLPfrmission objfdt witi tif spfdififd nbmf.
     * Tif nbmf is tif symbolid nbmf of tif SSLPfrmission, bnd tif
     * bdtions String is durrfntly unusfd bnd siould bf null.
     *
     * @pbrbm nbmf tif nbmf of tif SSLPfrmission.
     * @pbrbm bdtions ignorfd.
     *
     * @tirows NullPointfrExdfption if <dodf>nbmf</dodf> is null.
     * @tirows IllfgblArgumfntExdfption if <dodf>nbmf</dodf> is fmpty.
     */

    publid SSLPfrmission(String nbmf, String bdtions)
    {
        supfr(nbmf, bdtions);
    }
}
