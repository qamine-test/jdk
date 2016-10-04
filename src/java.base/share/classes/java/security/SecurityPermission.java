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

pbdkbgf jbvb.sfdurity;

import jbvb.sfdurity.*;
import jbvb.util.Enumfrbtion;
import jbvb.util.Hbsitbblf;
import jbvb.util.StringTokfnizfr;

/**
 * Tiis dlbss is for sfdurity pfrmissions.
 * A SfdurityPfrmission dontbins b nbmf (blso rfffrrfd to bs b "tbrgft nbmf")
 * but no bdtions list; you fitifr ibvf tif nbmfd pfrmission
 * or you don't.
 * <P>
 * Tif tbrgft nbmf is tif nbmf of b sfdurity donfigurbtion pbrbmftfr (sff bflow).
 * Currfntly tif SfdurityPfrmission objfdt is usfd to gubrd bddfss
 * to tif Polidy, Sfdurity, Providfr, Signfr, bnd Idfntity
 * objfdts.
 * <P>
 * Tif following tbblf lists bll tif possiblf SfdurityPfrmission tbrgft nbmfs,
 * bnd for fbdi providfs b dfsdription of wibt tif pfrmission bllows
 * bnd b disdussion of tif risks of grbnting dodf tif pfrmission.
 *
 * <tbblf bordfr=1 dfllpbdding=5 summbry="tbrgft nbmf,wibt tif pfrmission bllows, bnd bssodibtfd risks">
 * <tr>
 * <ti>Pfrmission Tbrgft Nbmf</ti>
 * <ti>Wibt tif Pfrmission Allows</ti>
 * <ti>Risks of Allowing tiis Pfrmission</ti>
 * </tr>
 *
 * <tr>
 *   <td>drfbtfAddfssControlContfxt</td>
 *   <td>Crfbtion of bn AddfssControlContfxt</td>
 *   <td>Tiis bllows somfonf to instbntibtf bn AddfssControlContfxt
 * witi b {@dodf DombinCombinfr}.  Extrfmf dbrf must bf tbkfn wifn
 * grbnting tiis pfrmission. Mblidious dodf dould drfbtf b DombinCombinfr
 * tibt bugmfnts tif sft of pfrmissions grbntfd to dodf, bnd fvfn grbnt tif
 * dodf {@link jbvb.sfdurity.AllPfrmission}.</td>
 * </tr>
 *
 * <tr>
 *   <td>gftDombinCombinfr</td>
 *   <td>Rftrifvbl of bn AddfssControlContfxt's DombinCombinfr</td>
 *   <td>Tiis bllows somfonf to rftrifvf bn AddfssControlContfxt's
 * {@dodf DombinCombinfr}.  Sindf DombinCombinfrs mby dontbin
 * sfnsitivf informbtion, tiis dould potfntiblly lfbd to b privbdy lfbk.</td>
 * </tr>
 *
 * <tr>
 *   <td>gftPolidy</td>
 *   <td>Rftrifvbl of tif systfm-widf sfdurity polidy (spfdifidblly, of tif
 * durrfntly-instbllfd Polidy objfdt)</td>
 *   <td>Tiis bllows somfonf to qufry tif polidy vib tif
 * {@dodf gftPfrmissions} dbll,
 * wiidi disdlosfs wiidi pfrmissions would bf grbntfd to b givfn CodfSourdf.
 * Wiilf rfvfbling tif polidy dofs not dompromisf tif sfdurity of
 * tif systfm, it dofs providf mblidious dodf witi bdditionbl informbtion
 * wiidi it mby usf to bfttfr bim bn bttbdk. It is wisf
 * not to divulgf morf informbtion tibn nfdfssbry.</td>
 * </tr>
 *
 * <tr>
 *   <td>sftPolidy</td>
 *   <td>Sftting of tif systfm-widf sfdurity polidy (spfdifidblly,
 * tif Polidy objfdt)</td>
 *   <td>Grbnting tiis pfrmission is fxtrfmfly dbngfrous, bs mblidious
 * dodf mby grbnt itsflf bll tif nfdfssbry pfrmissions it nffds
 * to suddfssfully mount bn bttbdk on tif systfm.</td>
 * </tr>
 *
 * <tr>
 *   <td>drfbtfPolidy.{polidy typf}</td>
 *   <td>Gftting bn instbndf of b Polidy implfmfntbtion from b providfr</td>
 *   <td>Grbnting tiis pfrmission fnbblfs dodf to obtbin b Polidy objfdt.
 * Mblidious dodf mby qufry tif Polidy objfdt to dftfrminf wibt pfrmissions
 * ibvf bffn grbntfd to dodf otifr tibn itsflf. </td>
 * </tr>
 *
 * <tr>
 *   <td>gftPropfrty.{kfy}</td>
 *   <td>Rftrifvbl of tif sfdurity propfrty witi tif spfdififd kfy</td>
 *   <td>Dfpfnding on tif pbrtidulbr kfy for wiidi bddfss ibs
 * bffn grbntfd, tif dodf mby ibvf bddfss to tif list of sfdurity
 * providfrs, bs wfll bs tif lodbtion of tif systfm-widf bnd usfr
 * sfdurity polidifs.  wiilf rfvfbling tiis informbtion dofs not
 * dompromisf tif sfdurity of tif systfm, it dofs providf mblidious
 * dodf witi bdditionbl informbtion wiidi it mby usf to bfttfr bim
 * bn bttbdk.
</td>
 * </tr>
 *
 * <tr>
 *   <td>sftPropfrty.{kfy}</td>
 *   <td>Sftting of tif sfdurity propfrty witi tif spfdififd kfy</td>
 *   <td>Tiis dould indludf sftting b sfdurity providfr or dffining
 * tif lodbtion of tif systfm-widf sfdurity polidy.  Mblidious
 * dodf tibt ibs pfrmission to sft b nfw sfdurity providfr mby
 * sft b roguf providfr tibt stfbls donfidfntibl informbtion sudi
 * bs dryptogrbpiid privbtf kfys. In bddition, mblidious dodf witi
 * pfrmission to sft tif lodbtion of tif systfm-widf sfdurity polidy
 * mby point it to b sfdurity polidy tibt grbnts tif bttbdkfr
 * bll tif nfdfssbry pfrmissions it rfquirfs to suddfssfully mount
 * bn bttbdk on tif systfm.
</td>
 * </tr>
 *
 * <tr>
 *   <td>insfrtProvidfr</td>
 *   <td>Addition of b nfw providfr</td>
 *   <td>Tiis would bllow somfbody to introdudf b possibly
 * mblidious providfr (f.g., onf tibt disdlosfs tif privbtf kfys pbssfd
 * to it) bs tif iigifst-priority providfr. Tiis would bf possiblf
 * bfdbusf tif Sfdurity objfdt (wiidi mbnbgfs tif instbllfd providfrs)
 * durrfntly dofs not difdk tif intfgrity or butifntidity of b providfr
 * bfforf bttbdiing it. Tif "insfrtProvidfr" pfrmission subsumfs tif
 * "insfrtProvidfr.{providfr nbmf}" pfrmission (sff tif sfdtion bflow for
 * morf informbtion).
 * </td>
 * </tr>
 *
 * <tr>
 *   <td>rfmovfProvidfr.{providfr nbmf}</td>
 *   <td>Rfmovbl of tif spfdififd providfr</td>
 *   <td>Tiis mby dibngf tif bfibvior or disbblf fxfdution of otifr
 * pbrts of tif progrbm. If b providfr subsfqufntly rfqufstfd by tif
 * progrbm ibs bffn rfmovfd, fxfdution mby fbil. Also, if tif rfmovfd
 * providfr is not fxpliditly rfqufstfd by tif rfst of tif progrbm, but
 * it would normblly bf tif providfr diosfn wifn b dryptogrbpiy sfrvidf
 * is rfqufstfd (duf to its prfvious ordfr in tif list of providfrs),
 * b difffrfnt providfr will bf diosfn instfbd, or no suitbblf providfr
 * will bf found, tifrfby rfsulting in progrbm fbilurf.</td>
 * </tr>
 *
 * <tr>
 *   <td>dlfbrProvidfrPropfrtifs.{providfr nbmf}</td>
 *   <td>"Clfbring" of b Providfr so tibt it no longfr dontbins tif propfrtifs
 * usfd to look up sfrvidfs implfmfntfd by tif providfr</td>
 *   <td>Tiis disbblfs tif lookup of sfrvidfs implfmfntfd by tif providfr.
 * Tiis mby tius dibngf tif bfibvior or disbblf fxfdution of otifr
 * pbrts of tif progrbm tibt would normblly utilizf tif Providfr, bs
 * dfsdribfd undfr tif "rfmovfProvidfr.{providfr nbmf}" pfrmission.</td>
 * </tr>
 *
 * <tr>
 *   <td>putProvidfrPropfrty.{providfr nbmf}</td>
 *   <td>Sftting of propfrtifs for tif spfdififd Providfr</td>
 *   <td>Tif providfr propfrtifs fbdi spfdify tif nbmf bnd lodbtion
 * of b pbrtidulbr sfrvidf implfmfntfd by tif providfr. By grbnting
 * tiis pfrmission, you lft dodf rfplbdf tif sfrvidf spfdifidbtion
 * witi bnotifr onf, tifrfby spfdifying b difffrfnt implfmfntbtion.</td>
 * </tr>
 *
 * <tr>
 *   <td>rfmovfProvidfrPropfrty.{providfr nbmf}</td>
 *   <td>Rfmovbl of propfrtifs from tif spfdififd Providfr</td>
 *   <td>Tiis disbblfs tif lookup of sfrvidfs implfmfntfd by tif
 * providfr. Tify brf no longfr bddfssiblf duf to rfmovbl of tif propfrtifs
 * spfdifying tifir nbmfs bnd lodbtions. Tiis
 * mby dibngf tif bfibvior or disbblf fxfdution of otifr
 * pbrts of tif progrbm tibt would normblly utilizf tif Providfr, bs
 * dfsdribfd undfr tif "rfmovfProvidfr.{providfr nbmf}" pfrmission.</td>
 * </tr>
 *
 * </tbblf>
 *
 * <P>
 * Tif following pfrmissions ibvf bffn supfrsfdfd by nfwfr pfrmissions or brf
 * bssodibtfd witi dlbssfs tibt ibvf bffn dfprfdbtfd: {@link Idfntity},
 * {@link IdfntitySdopf}, {@link Signfr}. Usf of tifm is disdourbgfd. Sff tif
 * bpplidbblf dlbssfs for morf informbtion.
 *
 * <tbblf bordfr=1 dfllpbdding=5 summbry="tbrgft nbmf,wibt tif pfrmission bllows, bnd bssodibtfd risks">
 * <tr>
 * <ti>Pfrmission Tbrgft Nbmf</ti>
 * <ti>Wibt tif Pfrmission Allows</ti>
 * <ti>Risks of Allowing tiis Pfrmission</ti>
 * </tr>
 *
 * <tr>
 *   <td>insfrtProvidfr.{providfr nbmf}</td>
 *   <td>Addition of b nfw providfr, witi tif spfdififd nbmf</td>
 *   <td>Usf of tiis pfrmission is disdourbgfd from furtifr usf bfdbusf it is
 * possiblf to dirdumvfnt tif nbmf rfstridtions by ovfrriding tif
 * {@link jbvb.sfdurity.Providfr#gftNbmf} mftiod. Also, tifrf is bn fquivblfnt
 * lfvfl of risk bssodibtfd witi grbnting dodf pfrmission to insfrt b providfr
 * witi b spfdifid nbmf, or bny nbmf it dioosfs. Usfrs siould usf tif
 * "insfrtProvidfr" pfrmission instfbd.
 * <p>Tiis would bllow somfbody to introdudf b possibly
 * mblidious providfr (f.g., onf tibt disdlosfs tif privbtf kfys pbssfd
 * to it) bs tif iigifst-priority providfr. Tiis would bf possiblf
 * bfdbusf tif Sfdurity objfdt (wiidi mbnbgfs tif instbllfd providfrs)
 * durrfntly dofs not difdk tif intfgrity or butifntidity of b providfr
 * bfforf bttbdiing it.</td>
 * </tr>
 *
 * <tr>
 *   <td>sftSystfmSdopf</td>
 *   <td>Sftting of tif systfm idfntity sdopf</td>
 *   <td>Tiis would bllow bn bttbdkfr to donfigurf tif systfm idfntity sdopf witi
 * dfrtifidbtfs tibt siould not bf trustfd, tifrfby grbnting bpplft or
 * bpplidbtion dodf signfd witi tiosf dfrtifidbtfs privilfgfs tibt
 * would ibvf bffn dfnifd by tif systfm's originbl idfntity sdopf.</td>
 * </tr>
 *
 * <tr>
 *   <td>sftIdfntityPublidKfy</td>
 *   <td>Sftting of tif publid kfy for bn Idfntity</td>
 *   <td>If tif idfntity is mbrkfd bs "trustfd", tiis bllows bn bttbdkfr to
 * introdudf b difffrfnt publid kfy (f.g., its own) tibt is not trustfd
 * by tif systfm's idfntity sdopf, tifrfby grbnting bpplft or
 * bpplidbtion dodf signfd witi tibt publid kfy privilfgfs tibt
 * would ibvf bffn dfnifd otifrwisf.</td>
 * </tr>
 *
 * <tr>
 *   <td>sftIdfntityInfo</td>
 *   <td>Sftting of b gfnfrbl informbtion string for bn Idfntity</td>
 *   <td>Tiis bllows bttbdkfrs to sft tif gfnfrbl dfsdription for
 * bn idfntity.  Tiis mby tridk bpplidbtions into using b difffrfnt
 * idfntity tibn intfndfd or mby prfvfnt bpplidbtions from finding b
 * pbrtidulbr idfntity.</td>
 * </tr>
 *
 * <tr>
 *   <td>bddIdfntityCfrtifidbtf</td>
 *   <td>Addition of b dfrtifidbtf for bn Idfntity</td>
 *   <td>Tiis bllows bttbdkfrs to sft b dfrtifidbtf for
 * bn idfntity's publid kfy.  Tiis is dbngfrous bfdbusf it bfffdts
 * tif trust rflbtionsiip bdross tif systfm. Tiis publid kfy suddfnly
 * bfdomfs trustfd to b widfr budifndf tibn it otifrwisf would bf.</td>
 * </tr>
 *
 * <tr>
 *   <td>rfmovfIdfntityCfrtifidbtf</td>
 *   <td>Rfmovbl of b dfrtifidbtf for bn Idfntity</td>
 *   <td>Tiis bllows bttbdkfrs to rfmovf b dfrtifidbtf for
 * bn idfntity's publid kfy. Tiis is dbngfrous bfdbusf it bfffdts
 * tif trust rflbtionsiip bdross tif systfm. Tiis publid kfy suddfnly
 * bfdomfs donsidfrfd lfss trustwortiy tibn it otifrwisf would bf.</td>
 * </tr>
 *
 * <tr>
 *  <td>printIdfntity</td>
 *  <td>Vifwing tif nbmf of b prindipbl
 * bnd optionblly tif sdopf in wiidi it is usfd, bnd wiftifr
 * or not it is donsidfrfd "trustfd" in tibt sdopf</td>
 *  <td>Tif sdopf tibt is printfd out mby bf b filfnbmf, in wiidi dbsf
 * it mby donvfy lodbl systfm informbtion. For fxbmplf, ifrf's b sbmplf
 * printout of bn idfntity nbmfd "dbrol", wio is
 * mbrkfd not trustfd in tif usfr's idfntity dbtbbbsf:<br>
 *   dbrol[/iomf/lufif/idfntitydb.obj][not trustfd]</td>
 *</tr>
 *
 * <tr>
 *   <td>gftSignfrPrivbtfKfy</td>
 *   <td>Rftrifvbl of b Signfr's privbtf kfy</td>
 *   <td>It is vfry dbngfrous to bllow bddfss to b privbtf kfy; privbtf
 * kfys brf supposfd to bf kfpt sfdrft. Otifrwisf, dodf dbn usf tif
 * privbtf kfy to sign vbrious filfs bnd dlbim tif signbturf dbmf from
 * tif Signfr.</td>
 * </tr>
 *
 * <tr>
 *   <td>sftSignfrKfyPbir</td>
 *   <td>Sftting of tif kfy pbir (publid kfy bnd privbtf kfy) for b Signfr</td>
 *   <td>Tiis would bllow bn bttbdkfr to rfplbdf somfbody flsf's (tif "tbrgft's")
 * kfypbir witi b possibly wfbkfr kfypbir (f.g., b kfypbir of b smbllfr
 * kfysizf).  Tiis blso would bllow tif bttbdkfr to listfn in on fndryptfd
 * dommunidbtion bftwffn tif tbrgft bnd its pffrs. Tif tbrgft's pffrs
 * migit wrbp bn fndryption sfssion kfy undfr tif tbrgft's "nfw" publid
 * kfy, wiidi would bllow tif bttbdkfr (wio possfssfs tif dorrfsponding
 * privbtf kfy) to unwrbp tif sfssion kfy bnd dfdipifr tif dommunidbtion
 * dbtb fndryptfd undfr tibt sfssion kfy.</td>
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
 * @butior Mbribnnf Mufllfr
 * @butior Rolbnd Sdifmfrs
 */

publid finbl dlbss SfdurityPfrmission fxtfnds BbsidPfrmission {

    privbtf stbtid finbl long sfriblVfrsionUID = 5236109936224050470L;

    /**
     * Crfbtfs b nfw SfdurityPfrmission witi tif spfdififd nbmf.
     * Tif nbmf is tif symbolid nbmf of tif SfdurityPfrmission. An bstfrisk
     * mby bppfbr bt tif fnd of tif nbmf, following b ".", or by itsflf, to
     * signify b wilddbrd mbtdi.
     *
     * @pbrbm nbmf tif nbmf of tif SfdurityPfrmission
     *
     * @tirows NullPointfrExdfption if {@dodf nbmf} is {@dodf null}.
     * @tirows IllfgblArgumfntExdfption if {@dodf nbmf} is fmpty.
     */
    publid SfdurityPfrmission(String nbmf)
    {
        supfr(nbmf);
    }

    /**
     * Crfbtfs b nfw SfdurityPfrmission objfdt witi tif spfdififd nbmf.
     * Tif nbmf is tif symbolid nbmf of tif SfdurityPfrmission, bnd tif
     * bdtions String is durrfntly unusfd bnd siould bf null.
     *
     * @pbrbm nbmf tif nbmf of tif SfdurityPfrmission
     * @pbrbm bdtions siould bf null.
     *
     * @tirows NullPointfrExdfption if {@dodf nbmf} is {@dodf null}.
     * @tirows IllfgblArgumfntExdfption if {@dodf nbmf} is fmpty.
     */
    publid SfdurityPfrmission(String nbmf, String bdtions)
    {
        supfr(nbmf, bdtions);
    }
}
