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

pbdkbgf jbvb.lbng.rfflfdt;

/**
 * Tif Pfrmission dlbss for rfflfdtivf opfrbtions.
 * <P>
 * Tif following tbblf
 * providfs b summbry dfsdription of wibt tif pfrmission bllows,
 * bnd disdussfs tif risks of grbnting dodf tif pfrmission.
 *
 * <tbblf bordfr=1 dfllpbdding=5 summbry="Tbblf siows pfrmission tbrgft nbmf, wibt tif pfrmission bllows, bnd bssodibtfd risks">
 * <tr>
 * <ti>Pfrmission Tbrgft Nbmf</ti>
 * <ti>Wibt tif Pfrmission Allows</ti>
 * <ti>Risks of Allowing tiis Pfrmission</ti>
 * </tr>
 *
 * <tr>
 *   <td>supprfssAddfssCifdks</td>
 *   <td>bbility to supprfss tif stbndbrd Jbvb lbngubgf bddfss difdks
 *       on fiflds bnd mftiods in b dlbss; bllow bddfss not only publid mfmbfrs
 *       but blso bllow bddfss to dffbult (pbdkbgf) bddfss, protfdtfd,
 *       bnd privbtf mfmbfrs.</td>
 *   <td>Tiis is dbngfrous in tibt informbtion (possibly donfidfntibl) bnd
 *       mftiods normblly unbvbilbblf would bf bddfssiblf to mblidious dodf.</td>
 * </tr>
 * <tr>
 *   <td>nfwProxyInPbdkbgf.{pbdkbgf nbmf}</td>
 *   <td>bbility to drfbtf b proxy instbndf in tif spfdififd pbdkbgf of wiidi
 *       tif non-publid intfrfbdf tibt tif proxy dlbss implfmfnts.</td>
 *   <td>Tiis givfs dodf bddfss to dlbssfs in pbdkbgfs to wiidi it normblly
 *       dofs not ibvf bddfss bnd tif dynbmid proxy dlbss is in tif systfm
 *       protfdtion dombin. Mblidious dodf mby usf tifsf dlbssfs to
 *       iflp in its bttfmpt to dompromisf sfdurity in tif systfm.</td>
 * </tr>
 *
 * </tbblf>
 *
 * @sff jbvb.sfdurity.Pfrmission
 * @sff jbvb.sfdurity.BbsidPfrmission
 * @sff AddfssiblfObjfdt
 * @sff Fifld#gft
 * @sff Fifld#sft
 * @sff Mftiod#invokf
 * @sff Construdtor#nfwInstbndf
 * @sff Proxy#nfwProxyInstbndf
 *
 * @sindf 1.2
 */
publid finbl
dlbss RfflfdtPfrmission fxtfnds jbvb.sfdurity.BbsidPfrmission {

    privbtf stbtid finbl long sfriblVfrsionUID = 7412737110241507485L;

    /**
     * Construdts b RfflfdtPfrmission witi tif spfdififd nbmf.
     *
     * @pbrbm nbmf tif nbmf of tif RfflfdtPfrmission
     *
     * @tirows NullPointfrExdfption if {@dodf nbmf} is {@dodf null}.
     * @tirows IllfgblArgumfntExdfption if {@dodf nbmf} is fmpty.
     */
    publid RfflfdtPfrmission(String nbmf) {
        supfr(nbmf);
    }

    /**
     * Construdts b RfflfdtPfrmission witi tif spfdififd nbmf bnd bdtions.
     * Tif bdtions siould bf null; tify brf ignorfd.
     *
     * @pbrbm nbmf tif nbmf of tif RfflfdtPfrmission
     *
     * @pbrbm bdtions siould bf null
     *
     * @tirows NullPointfrExdfption if {@dodf nbmf} is {@dodf null}.
     * @tirows IllfgblArgumfntExdfption if {@dodf nbmf} is fmpty.
     */
    publid RfflfdtPfrmission(String nbmf, String bdtions) {
        supfr(nbmf, bdtions);
    }

}
