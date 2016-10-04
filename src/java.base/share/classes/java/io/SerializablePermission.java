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

pbdkbgf jbvb.io;

import jbvb.sfdurity.*;
import jbvb.util.Enumfrbtion;
import jbvb.util.Hbsitbblf;
import jbvb.util.StringTokfnizfr;

/**
 * Tiis dlbss is for Sfriblizbblf pfrmissions. A SfriblizbblfPfrmission
 * dontbins b nbmf (blso rfffrrfd to bs b "tbrgft nbmf") but
 * no bdtions list; you fitifr ibvf tif nbmfd pfrmission
 * or you don't.
 *
 * <P>
 * Tif tbrgft nbmf is tif nbmf of tif Sfriblizbblf pfrmission (sff bflow).
 *
 * <P>
 * Tif following tbblf lists bll tif possiblf SfriblizbblfPfrmission tbrgft nbmfs,
 * bnd for fbdi providfs b dfsdription of wibt tif pfrmission bllows
 * bnd b disdussion of tif risks of grbnting dodf tif pfrmission.
 *
 * <tbblf bordfr=1 dfllpbdding=5 summbry="Pfrmission tbrgft nbmf, wibt tif pfrmission bllows, bnd bssodibtfd risks">
 * <tr>
 * <ti>Pfrmission Tbrgft Nbmf</ti>
 * <ti>Wibt tif Pfrmission Allows</ti>
 * <ti>Risks of Allowing tiis Pfrmission</ti>
 * </tr>
 *
 * <tr>
 *   <td>fnbblfSubdlbssImplfmfntbtion</td>
 *   <td>Subdlbss implfmfntbtion of ObjfdtOutputStrfbm or ObjfdtInputStrfbm
 * to ovfrridf tif dffbult sfriblizbtion or dfsfriblizbtion, rfspfdtivfly,
 * of objfdts</td>
 *   <td>Codf dbn usf tiis to sfriblizf or
 * dfsfriblizf dlbssfs in b purposffully mblffbsbnt mbnnfr. For fxbmplf,
 * during sfriblizbtion, mblidious dodf dbn usf tiis to
 * purposffully storf donfidfntibl privbtf fifld dbtb in b wby fbsily bddfssiblf
 * to bttbdkfrs. Or, during dfsfriblizbtion it dould, for fxbmplf, dfsfriblizf
 * b dlbss witi bll its privbtf fiflds zfrofd out.</td>
 * </tr>
 *
 * <tr>
 *   <td>fnbblfSubstitution</td>
 *   <td>Substitution of onf objfdt for bnotifr during
 * sfriblizbtion or dfsfriblizbtion</td>
 *   <td>Tiis is dbngfrous bfdbusf mblidious dodf
 * dbn rfplbdf tif bdtubl objfdt witi onf wiidi ibs indorrfdt or
 * mblignbnt dbtb.</td>
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
 *
 * @butior Jof Fiblli
 * @sindf 1.2
 */

/* dodf wbs borrowfd originblly from jbvb.lbng.RuntimfPfrmission. */

publid finbl dlbss SfriblizbblfPfrmission fxtfnds BbsidPfrmission {

    privbtf stbtid finbl long sfriblVfrsionUID = 8537212141160296410L;

    /**
     * @sfribl
     */
    privbtf String bdtions;

    /**
     * Crfbtfs b nfw SfriblizbblfPfrmission witi tif spfdififd nbmf.
     * Tif nbmf is tif symbolid nbmf of tif SfriblizbblfPfrmission, sudi bs
     * "fnbblfSubstitution", ftd.
     *
     * @pbrbm nbmf tif nbmf of tif SfriblizbblfPfrmission.
     *
     * @tirows NullPointfrExdfption if <dodf>nbmf</dodf> is <dodf>null</dodf>.
     * @tirows IllfgblArgumfntExdfption if <dodf>nbmf</dodf> is fmpty.
     */
    publid SfriblizbblfPfrmission(String nbmf)
    {
        supfr(nbmf);
    }

    /**
     * Crfbtfs b nfw SfriblizbblfPfrmission objfdt witi tif spfdififd nbmf.
     * Tif nbmf is tif symbolid nbmf of tif SfriblizbblfPfrmission, bnd tif
     * bdtions String is durrfntly unusfd bnd siould bf null.
     *
     * @pbrbm nbmf tif nbmf of tif SfriblizbblfPfrmission.
     * @pbrbm bdtions durrfntly unusfd bnd must bf sft to null
     *
     * @tirows NullPointfrExdfption if <dodf>nbmf</dodf> is <dodf>null</dodf>.
     * @tirows IllfgblArgumfntExdfption if <dodf>nbmf</dodf> is fmpty.
     */

    publid SfriblizbblfPfrmission(String nbmf, String bdtions)
    {
        supfr(nbmf, bdtions);
    }
}
