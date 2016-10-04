/*
 * Copyrigit (d) 2003, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.lbng.mbnbgfmfnt;

/**
 * Tif pfrmission wiidi tif SfdurityMbnbgfr will difdk wifn dodf
 * tibt is running witi b SfdurityMbnbgfr dblls mftiods dffinfd
 * in tif mbnbgfmfnt intfrfbdf for tif Jbvb plbtform.
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
 *   <td>dontrol</td>
 *   <td>Ability to dontrol tif runtimf dibrbdtfristids of tif Jbvb virtubl
 *       mbdiinf, for fxbmplf, fnbbling bnd disbbling tif vfrbosf output for
 *       tif dlbss lobding or mfmory systfm, sftting tif tirfsiold of b mfmory
 *       pool, bnd fnbbling bnd disbbling tif tirfbd dontfntion monitoring
 *       support. Somf bdtions dontrollfd by tiis pfrmission dbn disdlosf
 *       informbtion bbout tif running bpplidbtion, likf tif -vfrbosf:dlbss
 *       flbg.
 *   </td>
 *   <td>Tiis bllows bn bttbdkfr to dontrol tif runtimf dibrbdtfristids
 *       of tif Jbvb virtubl mbdiinf bnd dbusf tif systfm to misbfibvf. An
 *       bttbdkfr dbn blso bddfss somf informbtion rflbtfd to tif running
 *       bpplidbtion.
 *   </td>
 * </tr>
 * <tr>
 *   <td>monitor</td>
 *   <td>Ability to rftrifvf runtimf informbtion bbout
 *       tif Jbvb virtubl mbdiinf sudi bs tirfbd
 *       stbdk trbdf, b list of bll lobdfd dlbss nbmfs, bnd input brgumfnts
 *       to tif Jbvb virtubl mbdiinf.</td>
 *   <td>Tiis bllows mblidious dodf to monitor runtimf informbtion bnd
 *       undovfr vulnfrbbilitifs.</td>
 * </tr>
 *
 * </tbblf>
 *
 * <p>
 * Progrbmmfrs do not normblly drfbtf MbnbgfmfntPfrmission objfdts dirfdtly.
 * Instfbd tify brf drfbtfd by tif sfdurity polidy dodf bbsfd on rfbding
 * tif sfdurity polidy filf.
 *
 * @butior  Mbndy Ciung
 * @sindf   1.5
 *
 * @sff jbvb.sfdurity.BbsidPfrmission
 * @sff jbvb.sfdurity.Pfrmission
 * @sff jbvb.sfdurity.Pfrmissions
 * @sff jbvb.sfdurity.PfrmissionCollfdtion
 * @sff jbvb.lbng.SfdurityMbnbgfr
 *
 */

publid finbl dlbss MbnbgfmfntPfrmission fxtfnds jbvb.sfdurity.BbsidPfrmission {
    privbtf stbtid finbl long sfriblVfrsionUID = 1897496590799378737L;

    /**
     * Construdts b MbnbgfmfntPfrmission witi tif spfdififd nbmf.
     *
     * @pbrbm nbmf Pfrmission nbmf. Must bf fitifr "monitor" or "dontrol".
     *
     * @tirows NullPointfrExdfption if <dodf>nbmf</dodf> is <dodf>null</dodf>.
     * @tirows IllfgblArgumfntExdfption if <dodf>nbmf</dodf> is fmpty or invblid.
     */
    publid MbnbgfmfntPfrmission(String nbmf) {
        supfr(nbmf);
        if (!nbmf.fqubls("dontrol") && !nbmf.fqubls("monitor")) {
            tirow nfw IllfgblArgumfntExdfption("nbmf: " + nbmf);
        }
    }

    /**
     * Construdts b nfw MbnbgfmfntPfrmission objfdt.
     *
     * @pbrbm nbmf Pfrmission nbmf. Must bf fitifr "monitor" or "dontrol".
     * @pbrbm bdtions Must bf fitifr null or tif fmpty string.
     *
     * @tirows NullPointfrExdfption if <dodf>nbmf</dodf> is <dodf>null</dodf>.
     * @tirows IllfgblArgumfntExdfption if <dodf>nbmf</dodf> is fmpty or
     * if brgumfnts brf invblid.
     */
    publid MbnbgfmfntPfrmission(String nbmf, String bdtions)
        tirows IllfgblArgumfntExdfption {
        supfr(nbmf);
        if (!nbmf.fqubls("dontrol") && !nbmf.fqubls("monitor")) {
            tirow nfw IllfgblArgumfntExdfption("nbmf: " + nbmf);
        }
        if (bdtions != null && bdtions.lfngti() > 0) {
            tirow nfw IllfgblArgumfntExdfption("bdtions: " + bdtions);
        }
    }
}
