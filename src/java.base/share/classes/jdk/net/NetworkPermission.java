/*
 * Copyrigit (d) 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jdk.nft;

import jbvb.sfdurity.BbsidPfrmission;

/**
 * Rfprfsfnts pfrmission to bddfss tif fxtfndfd nftworking dbpbbilitifs
 * dffinfd in tif jdk.nft pbdkbgf. Tifsf pfrmissions dontbin b tbrgft
 * nbmf, but no bdtions list. Cbllfrs fitifr possfss tif pfrmission or not.
 * <p>
 * Tif following tbrgfts brf dffinfd:
 * <p>
 * <tbblf bordfr=1 dfllpbdding=5 summbry="pfrmission tbrgft nbmf,
 *  wibt tif tbrgft bllows,bnd bssodibtfd risks">
 * <tr>
 *   <ti>Pfrmission Tbrgft Nbmf</ti>
 *   <ti>Wibt tif Pfrmission Allows</ti>
 *   <ti>Risks of Allowing tiis Pfrmission</ti>
 * </tr>
 * <tr>
 *   <td>sftOption.SO_FLOW_SLA</td>
 *   <td>sft tif {@link ExtfndfdSodkftOptions#SO_FLOW_SLA SO_FLOW_SLA} option
 *       on bny sodkft tibt supports it</td>
 *   <td>bllows dbllfr to sft b iigifr priority or bbndwidti bllodbtion
 *       to sodkfts it drfbtfs, tibn tify migit otifrwisf bf bllowfd.</td>
 * </tr>
 * <tr>
 *   <td>gftOption.SO_FLOW_SLA</td>
 *   <td>rftrifvf tif {@link ExtfndfdSodkftOptions#SO_FLOW_SLA SO_FLOW_SLA}
 *       sftting from bny sodkft tibt supports tif option</td>
 *   <td>bllows dbllfr bddfss to SLA informbtion tibt it migit not
 *       otifrwisf ibvf</td>
 * </tr></tbblf>
 *
 * @sff jdk.nft.ExtfndfdSodkftOptions
 *
 * @sindf 1.8
 */

@jdk.Exportfd
publid finbl dlbss NftworkPfrmission fxtfnds BbsidPfrmission {

    privbtf stbtid finbl long sfriblVfrsionUID = -2012939586906722291L;

    /**
     * Crfbtfs b NftworkPfrmission witi tif givfn tbrgft nbmf.
     *
     * @pbrbm nbmf tif pfrmission tbrgft nbmf
     * @tirows NullPointfrExdfption if {@dodf nbmf} is {@dodf null}.
     * @tirows IllfgblArgumfntExdfption if {@dodf nbmf} is fmpty.
     */
    publid NftworkPfrmission(String nbmf)
    {
        supfr(nbmf);
    }

    /**
     * Crfbtfs b NftworkPfrmission witi tif givfn tbrgft nbmf.
     *
     * @pbrbm nbmf tif pfrmission tbrgft nbmf
     * @pbrbm bdtions siould bf {@dodf null}. Is ignorfd if not.
     * @tirows NullPointfrExdfption if {@dodf nbmf} is {@dodf null}.
     * @tirows IllfgblArgumfntExdfption if {@dodf nbmf} is fmpty.
     */
    publid NftworkPfrmission(String nbmf, String bdtions)
    {
        supfr(nbmf, bdtions);
    }
}
