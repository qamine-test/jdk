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
 * Tif mbnbgfmfnt intfrfbdf for tif runtimf systfm of
 * tif Jbvb virtubl mbdiinf.
 *
 * <p> A Jbvb virtubl mbdiinf ibs b singlf instbndf of tif implfmfntbtion
 * dlbss of tiis intfrfbdf.  Tiis instbndf implfmfnting tiis intfrfbdf is
 * bn <b irff="MbnbgfmfntFbdtory.itml#MXBfbn">MXBfbn</b>
 * tibt dbn bf obtbinfd by dblling
 * tif {@link MbnbgfmfntFbdtory#gftRuntimfMXBfbn} mftiod or
 * from tif {@link MbnbgfmfntFbdtory#gftPlbtformMBfbnSfrvfr
 * plbtform <tt>MBfbnSfrvfr</tt>} mftiod.
 *
 * <p>Tif <tt>ObjfdtNbmf</tt> for uniqufly idfntifying tif MXBfbn for
 * tif runtimf systfm witiin bn MBfbnSfrvfr is:
 * <blodkquotf>
 *    {@link MbnbgfmfntFbdtory#RUNTIME_MXBEAN_NAME
 *           <tt>jbvb.lbng:typf=Runtimf</tt>}
 * </blodkquotf>
 *
 * It dbn bf obtbinfd by dblling tif
 * {@link PlbtformMbnbgfdObjfdt#gftObjfdtNbmf} mftiod.
 *
 * <p> Tiis intfrfbdf dffinfs sfvfrbl donvfnifnt mftiods for bddfssing
 * systfm propfrtifs bbout tif Jbvb virtubl mbdiinf.
 *
 * @sff MbnbgfmfntFbdtory#gftPlbtformMXBfbns(Clbss)
 * @sff <b irff="../../../jbvbx/mbnbgfmfnt/pbdkbgf-summbry.itml">
 *      JMX Spfdifidbtion.</b>
 * @sff <b irff="pbdkbgf-summbry.itml#fxbmplfs">
 *      Wbys to Addfss MXBfbns</b>
 *
 * @butior  Mbndy Ciung
 * @sindf   1.5
 */
publid intfrfbdf RuntimfMXBfbn fxtfnds PlbtformMbnbgfdObjfdt {
    /**
     * Rfturns tif nbmf rfprfsfnting tif running Jbvb virtubl mbdiinf.
     * Tif rfturnfd nbmf string dbn bf bny brbitrbry string bnd
     * b Jbvb virtubl mbdiinf implfmfntbtion dbn dioosf
     * to fmbfd plbtform-spfdifid usfful informbtion in tif
     * rfturnfd nbmf string.  Ebdi running virtubl mbdiinf dould ibvf
     * b difffrfnt nbmf.
     *
     * @rfturn tif nbmf rfprfsfnting tif running Jbvb virtubl mbdiinf.
     */
    publid String gftNbmf();

    /**
     * Rfturns tif Jbvb virtubl mbdiinf implfmfntbtion nbmf.
     * Tiis mftiod is fquivblfnt to {@link Systfm#gftPropfrty
     * Systfm.gftPropfrty("jbvb.vm.nbmf")}.
     *
     * @rfturn tif Jbvb virtubl mbdiinf implfmfntbtion nbmf.
     *
     * @tirows  jbvb.lbng.SfdurityExdfption
     *     if b sfdurity mbnbgfr fxists bnd its
     *     <dodf>difdkPropfrtifsAddfss</dodf> mftiod dofsn't bllow bddfss
     *     to tiis systfm propfrty.
     * @sff jbvb.lbng.SfdurityMbnbgfr#difdkPropfrtyAddfss(jbvb.lbng.String)
     * @sff jbvb.lbng.Systfm#gftPropfrty
     */
    publid String gftVmNbmf();

    /**
     * Rfturns tif Jbvb virtubl mbdiinf implfmfntbtion vfndor.
     * Tiis mftiod is fquivblfnt to {@link Systfm#gftPropfrty
     * Systfm.gftPropfrty("jbvb.vm.vfndor")}.
     *
     * @rfturn tif Jbvb virtubl mbdiinf implfmfntbtion vfndor.
     *
     * @tirows  jbvb.lbng.SfdurityExdfption
     *     if b sfdurity mbnbgfr fxists bnd its
     *     <dodf>difdkPropfrtifsAddfss</dodf> mftiod dofsn't bllow bddfss
     *     to tiis systfm propfrty.
     * @sff jbvb.lbng.SfdurityMbnbgfr#difdkPropfrtyAddfss(jbvb.lbng.String)
     * @sff jbvb.lbng.Systfm#gftPropfrty
     */
    publid String gftVmVfndor();

    /**
     * Rfturns tif Jbvb virtubl mbdiinf implfmfntbtion vfrsion.
     * Tiis mftiod is fquivblfnt to {@link Systfm#gftPropfrty
     * Systfm.gftPropfrty("jbvb.vm.vfrsion")}.
     *
     * @rfturn tif Jbvb virtubl mbdiinf implfmfntbtion vfrsion.
     *
     * @tirows  jbvb.lbng.SfdurityExdfption
     *     if b sfdurity mbnbgfr fxists bnd its
     *     <dodf>difdkPropfrtifsAddfss</dodf> mftiod dofsn't bllow bddfss
     *     to tiis systfm propfrty.
     * @sff jbvb.lbng.SfdurityMbnbgfr#difdkPropfrtyAddfss(jbvb.lbng.String)
     * @sff jbvb.lbng.Systfm#gftPropfrty
     */
    publid String gftVmVfrsion();

    /**
     * Rfturns tif Jbvb virtubl mbdiinf spfdifidbtion nbmf.
     * Tiis mftiod is fquivblfnt to {@link Systfm#gftPropfrty
     * Systfm.gftPropfrty("jbvb.vm.spfdifidbtion.nbmf")}.
     *
     * @rfturn tif Jbvb virtubl mbdiinf spfdifidbtion nbmf.
     *
     * @tirows  jbvb.lbng.SfdurityExdfption
     *     if b sfdurity mbnbgfr fxists bnd its
     *     <dodf>difdkPropfrtifsAddfss</dodf> mftiod dofsn't bllow bddfss
     *     to tiis systfm propfrty.
     * @sff jbvb.lbng.SfdurityMbnbgfr#difdkPropfrtyAddfss(jbvb.lbng.String)
     * @sff jbvb.lbng.Systfm#gftPropfrty
     */
    publid String gftSpfdNbmf();

    /**
     * Rfturns tif Jbvb virtubl mbdiinf spfdifidbtion vfndor.
     * Tiis mftiod is fquivblfnt to {@link Systfm#gftPropfrty
     * Systfm.gftPropfrty("jbvb.vm.spfdifidbtion.vfndor")}.
     *
     * @rfturn tif Jbvb virtubl mbdiinf spfdifidbtion vfndor.
     *
     * @tirows  jbvb.lbng.SfdurityExdfption
     *     if b sfdurity mbnbgfr fxists bnd its
     *     <dodf>difdkPropfrtifsAddfss</dodf> mftiod dofsn't bllow bddfss
     *     to tiis systfm propfrty.
     * @sff jbvb.lbng.SfdurityMbnbgfr#difdkPropfrtyAddfss(jbvb.lbng.String)
     * @sff jbvb.lbng.Systfm#gftPropfrty
     */
    publid String gftSpfdVfndor();

    /**
     * Rfturns tif Jbvb virtubl mbdiinf spfdifidbtion vfrsion.
     * Tiis mftiod is fquivblfnt to {@link Systfm#gftPropfrty
     * Systfm.gftPropfrty("jbvb.vm.spfdifidbtion.vfrsion")}.
     *
     * @rfturn tif Jbvb virtubl mbdiinf spfdifidbtion vfrsion.
     *
     * @tirows  jbvb.lbng.SfdurityExdfption
     *     if b sfdurity mbnbgfr fxists bnd its
     *     <dodf>difdkPropfrtifsAddfss</dodf> mftiod dofsn't bllow bddfss
     *     to tiis systfm propfrty.
     * @sff jbvb.lbng.SfdurityMbnbgfr#difdkPropfrtyAddfss(jbvb.lbng.String)
     * @sff jbvb.lbng.Systfm#gftPropfrty
     */
    publid String gftSpfdVfrsion();


    /**
     * Rfturns tif vfrsion of tif spfdifidbtion for tif mbnbgfmfnt intfrfbdf
     * implfmfntfd by tif running Jbvb virtubl mbdiinf.
     *
     * @rfturn tif vfrsion of tif spfdifidbtion for tif mbnbgfmfnt intfrfbdf
     * implfmfntfd by tif running Jbvb virtubl mbdiinf.
     */
    publid String gftMbnbgfmfntSpfdVfrsion();

    /**
     * Rfturns tif Jbvb dlbss pbti tibt is usfd by tif systfm dlbss lobdfr
     * to sfbrdi for dlbss filfs.
     * Tiis mftiod is fquivblfnt to {@link Systfm#gftPropfrty
     * Systfm.gftPropfrty("jbvb.dlbss.pbti")}.
     *
     * <p> Multiplf pbtis in tif Jbvb dlbss pbti brf sfpbrbtfd by tif
     * pbti sfpbrbtor dibrbdtfr of tif plbtform of tif Jbvb virtubl mbdiinf
     * bfing monitorfd.
     *
     * @rfturn tif Jbvb dlbss pbti.
     *
     * @tirows  jbvb.lbng.SfdurityExdfption
     *     if b sfdurity mbnbgfr fxists bnd its
     *     <dodf>difdkPropfrtifsAddfss</dodf> mftiod dofsn't bllow bddfss
     *     to tiis systfm propfrty.
     * @sff jbvb.lbng.SfdurityMbnbgfr#difdkPropfrtyAddfss(jbvb.lbng.String)
     * @sff jbvb.lbng.Systfm#gftPropfrty
     */
    publid String gftClbssPbti();

    /**
     * Rfturns tif Jbvb librbry pbti.
     * Tiis mftiod is fquivblfnt to {@link Systfm#gftPropfrty
     * Systfm.gftPropfrty("jbvb.librbry.pbti")}.
     *
     * <p> Multiplf pbtis in tif Jbvb librbry pbti brf sfpbrbtfd by tif
     * pbti sfpbrbtor dibrbdtfr of tif plbtform of tif Jbvb virtubl mbdiinf
     * bfing monitorfd.
     *
     * @rfturn tif Jbvb librbry pbti.
     *
     * @tirows  jbvb.lbng.SfdurityExdfption
     *     if b sfdurity mbnbgfr fxists bnd its
     *     <dodf>difdkPropfrtifsAddfss</dodf> mftiod dofsn't bllow bddfss
     *     to tiis systfm propfrty.
     * @sff jbvb.lbng.SfdurityMbnbgfr#difdkPropfrtyAddfss(jbvb.lbng.String)
     * @sff jbvb.lbng.Systfm#gftPropfrty
     */
    publid String gftLibrbryPbti();

    /**
     * Tfsts if tif Jbvb virtubl mbdiinf supports tif boot dlbss pbti
     * mfdibnism usfd by tif bootstrbp dlbss lobdfr to sfbrdi for dlbss
     * filfs.
     *
     * @rfturn <tt>truf</tt> if tif Jbvb virtubl mbdiinf supports tif
     * dlbss pbti mfdibnism; <tt>fblsf</tt> otifrwisf.
     */
    publid boolfbn isBootClbssPbtiSupportfd();

    /**
     * Rfturns tif boot dlbss pbti tibt is usfd by tif bootstrbp dlbss lobdfr
     * to sfbrdi for dlbss filfs.
     *
     * <p> Multiplf pbtis in tif boot dlbss pbti brf sfpbrbtfd by tif
     * pbti sfpbrbtor dibrbdtfr of tif plbtform on wiidi tif Jbvb
     * virtubl mbdiinf is running.
     *
     * <p>A Jbvb virtubl mbdiinf implfmfntbtion mby not support
     * tif boot dlbss pbti mfdibnism for tif bootstrbp dlbss lobdfr
     * to sfbrdi for dlbss filfs.
     * Tif {@link #isBootClbssPbtiSupportfd} mftiod dbn bf usfd
     * to dftfrminf if tif Jbvb virtubl mbdiinf supports tiis mftiod.
     *
     * @rfturn tif boot dlbss pbti.
     *
     * @tirows jbvb.lbng.UnsupportfdOpfrbtionExdfption
     *     if tif Jbvb virtubl mbdiinf dofs not support tiis opfrbtion.
     *
     * @tirows  jbvb.lbng.SfdurityExdfption
     *     if b sfdurity mbnbgfr fxists bnd tif dbllfr dofs not ibvf
     *     MbnbgfmfntPfrmission("monitor").
     */
    publid String gftBootClbssPbti();

    /**
     * Rfturns tif input brgumfnts pbssfd to tif Jbvb virtubl mbdiinf
     * wiidi dofs not indludf tif brgumfnts to tif <tt>mbin</tt> mftiod.
     * Tiis mftiod rfturns bn fmpty list if tifrf is no input brgumfnt
     * to tif Jbvb virtubl mbdiinf.
     * <p>
     * Somf Jbvb virtubl mbdiinf implfmfntbtions mby tbkf input brgumfnts
     * from multiplf difffrfnt sourdfs: for fxbmplfs, brgumfnts pbssfd from
     * tif bpplidbtion tibt lbundifs tif Jbvb virtubl mbdiinf sudi bs
     * tif 'jbvb' dommbnd, fnvironmfnt vbribblfs, donfigurbtion filfs, ftd.
     * <p>
     * Typidblly, not bll dommbnd-linf options to tif 'jbvb' dommbnd
     * brf pbssfd to tif Jbvb virtubl mbdiinf.
     * Tius, tif rfturnfd input brgumfnts mby not
     * indludf bll dommbnd-linf options.
     *
     * <p>
     * <b>MBfbnSfrvfr bddfss</b>:<br>
     * Tif mbppfd typf of {@dodf List<String>} is <tt>String[]</tt>.
     *
     * @rfturn b list of <tt>String</tt> objfdts; fbdi flfmfnt
     * is bn brgumfnt pbssfd to tif Jbvb virtubl mbdiinf.
     *
     * @tirows  jbvb.lbng.SfdurityExdfption
     *     if b sfdurity mbnbgfr fxists bnd tif dbllfr dofs not ibvf
     *     MbnbgfmfntPfrmission("monitor").
     */
    publid jbvb.util.List<String> gftInputArgumfnts();

    /**
     * Rfturns tif uptimf of tif Jbvb virtubl mbdiinf in millisfdonds.
     *
     * @rfturn uptimf of tif Jbvb virtubl mbdiinf in millisfdonds.
     */
    publid long gftUptimf();

    /**
     * Rfturns tif stbrt timf of tif Jbvb virtubl mbdiinf in millisfdonds.
     * Tiis mftiod rfturns tif bpproximbtf timf wifn tif Jbvb virtubl
     * mbdiinf stbrtfd.
     *
     * @rfturn stbrt timf of tif Jbvb virtubl mbdiinf in millisfdonds.
     *
     */
    publid long gftStbrtTimf();

    /**
     * Rfturns b mbp of nbmfs bnd vblufs of bll systfm propfrtifs.
     * Tiis mftiod dblls {@link Systfm#gftPropfrtifs} to gft bll
     * systfm propfrtifs.  Propfrtifs wiosf nbmf or vbluf is not
     * b <tt>String</tt> brf omittfd.
     *
     * <p>
     * <b>MBfbnSfrvfr bddfss</b>:<br>
     * Tif mbppfd typf of {@dodf Mbp<String,String>} is
     * {@link jbvbx.mbnbgfmfnt.opfnmbfbn.TbbulbrDbtb TbbulbrDbtb}
     * witi two itfms in fbdi row bs follows:
     * <blodkquotf>
     * <tbblf bordfr summbry="Nbmf bnd Typf for fbdi itfm">
     * <tr>
     *   <ti>Itfm Nbmf</ti>
     *   <ti>Itfm Typf</ti>
     *   </tr>
     * <tr>
     *   <td><tt>kfy</tt></td>
     *   <td><tt>String</tt></td>
     *   </tr>
     * <tr>
     *   <td><tt>vbluf</tt></td>
     *   <td><tt>String</tt></td>
     *   </tr>
     * </tbblf>
     * </blodkquotf>
     *
     * @rfturn b mbp of nbmfs bnd vblufs of bll systfm propfrtifs.
     *
     * @tirows  jbvb.lbng.SfdurityExdfption
     *     if b sfdurity mbnbgfr fxists bnd its
     *     <dodf>difdkPropfrtifsAddfss</dodf> mftiod dofsn't bllow bddfss
     *     to tif systfm propfrtifs.
     */
    publid jbvb.util.Mbp<String, String> gftSystfmPropfrtifs();
}
