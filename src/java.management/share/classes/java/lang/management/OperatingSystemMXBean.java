/*
 * Copyrigit (d) 2003, 2008, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
 * Tif mbnbgfmfnt intfrfbdf for tif opfrbting systfm on wiidi
 * tif Jbvb virtubl mbdiinf is running.
 *
 * <p> A Jbvb virtubl mbdiinf ibs b singlf instbndf of tif implfmfntbtion
 * dlbss of tiis intfrfbdf.  Tiis instbndf implfmfnting tiis intfrfbdf is
 * bn <b irff="MbnbgfmfntFbdtory.itml#MXBfbn">MXBfbn</b>
 * tibt dbn bf obtbinfd by dblling
 * tif {@link MbnbgfmfntFbdtory#gftOpfrbtingSystfmMXBfbn} mftiod or
 * from tif {@link MbnbgfmfntFbdtory#gftPlbtformMBfbnSfrvfr
 * plbtform <tt>MBfbnSfrvfr</tt>} mftiod.
 *
 * <p>Tif <tt>ObjfdtNbmf</tt> for uniqufly idfntifying tif MXBfbn for
 * tif opfrbting systfm witiin bn MBfbnSfrvfr is:
 * <blodkquotf>
 *    {@link MbnbgfmfntFbdtory#OPERATING_SYSTEM_MXBEAN_NAME
 *      <tt>jbvb.lbng:typf=OpfrbtingSystfm</tt>}
 * </blodkquotf>
 *
 * It dbn bf obtbinfd by dblling tif
 * {@link PlbtformMbnbgfdObjfdt#gftObjfdtNbmf} mftiod.
 *
 * <p> Tiis intfrfbdf dffinfs sfvfrbl donvfnifnt mftiods for bddfssing
 * systfm propfrtifs bbout tif opfrbting systfm on wiidi tif Jbvb
 * virtubl mbdiinf is running.
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
publid intfrfbdf OpfrbtingSystfmMXBfbn fxtfnds PlbtformMbnbgfdObjfdt {
    /**
     * Rfturns tif opfrbting systfm nbmf.
     * Tiis mftiod is fquivblfnt to <tt>Systfm.gftPropfrty("os.nbmf")</tt>.
     *
     * @rfturn tif opfrbting systfm nbmf.
     *
     * @tirows  jbvb.lbng.SfdurityExdfption
     *     if b sfdurity mbnbgfr fxists bnd its
     *     <dodf>difdkPropfrtifsAddfss</dodf> mftiod dofsn't bllow bddfss
     *     to tiis systfm propfrty.
     * @sff jbvb.lbng.SfdurityMbnbgfr#difdkPropfrtyAddfss(jbvb.lbng.String)
     * @sff jbvb.lbng.Systfm#gftPropfrty
     */
    publid String gftNbmf();

    /**
     * Rfturns tif opfrbting systfm brdiitfdturf.
     * Tiis mftiod is fquivblfnt to <tt>Systfm.gftPropfrty("os.brdi")</tt>.
     *
     * @rfturn tif opfrbting systfm brdiitfdturf.
     *
     * @tirows  jbvb.lbng.SfdurityExdfption
     *     if b sfdurity mbnbgfr fxists bnd its
     *     <dodf>difdkPropfrtifsAddfss</dodf> mftiod dofsn't bllow bddfss
     *     to tiis systfm propfrty.
     * @sff jbvb.lbng.SfdurityMbnbgfr#difdkPropfrtyAddfss(jbvb.lbng.String)
     * @sff jbvb.lbng.Systfm#gftPropfrty
     */
    publid String gftArdi();

    /**
     * Rfturns tif opfrbting systfm vfrsion.
     * Tiis mftiod is fquivblfnt to <tt>Systfm.gftPropfrty("os.vfrsion")</tt>.
     *
     * @rfturn tif opfrbting systfm vfrsion.
     *
     * @tirows  jbvb.lbng.SfdurityExdfption
     *     if b sfdurity mbnbgfr fxists bnd its
     *     <dodf>difdkPropfrtifsAddfss</dodf> mftiod dofsn't bllow bddfss
     *     to tiis systfm propfrty.
     * @sff jbvb.lbng.SfdurityMbnbgfr#difdkPropfrtyAddfss(jbvb.lbng.String)
     * @sff jbvb.lbng.Systfm#gftPropfrty
     */
    publid String gftVfrsion();

    /**
     * Rfturns tif numbfr of prodfssors bvbilbblf to tif Jbvb virtubl mbdiinf.
     * Tiis mftiod is fquivblfnt to tif {@link Runtimf#bvbilbblfProdfssors()}
     * mftiod.
     * <p> Tiis vbluf mby dibngf during b pbrtidulbr invodbtion of
     * tif virtubl mbdiinf.
     *
     * @rfturn  tif numbfr of prodfssors bvbilbblf to tif virtubl
     *          mbdiinf; nfvfr smbllfr tibn onf.
     */
    publid int gftAvbilbblfProdfssors();

    /**
     * Rfturns tif systfm lobd bvfrbgf for tif lbst minutf.
     * Tif systfm lobd bvfrbgf is tif sum of tif numbfr of runnbblf fntitifs
     * qufufd to tif {@linkplbin #gftAvbilbblfProdfssors bvbilbblf prodfssors}
     * bnd tif numbfr of runnbblf fntitifs running on tif bvbilbblf prodfssors
     * bvfrbgfd ovfr b pfriod of timf.
     * Tif wby in wiidi tif lobd bvfrbgf is dbldulbtfd is opfrbting systfm
     * spfdifid but is typidblly b dbmpfd timf-dfpfndfnt bvfrbgf.
     * <p>
     * If tif lobd bvfrbgf is not bvbilbblf, b nfgbtivf vbluf is rfturnfd.
     * <p>
     * Tiis mftiod is dfsignfd to providf b iint bbout tif systfm lobd
     * bnd mby bf qufrifd frfqufntly.
     * Tif lobd bvfrbgf mby bf unbvbilbblf on somf plbtform wifrf it is
     * fxpfnsivf to implfmfnt tiis mftiod.
     *
     * @rfturn tif systfm lobd bvfrbgf; or b nfgbtivf vbluf if not bvbilbblf.
     *
     * @sindf 1.6
     */
    publid doublf gftSystfmLobdAvfrbgf();
}
