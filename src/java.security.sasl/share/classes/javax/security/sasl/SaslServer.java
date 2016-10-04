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

pbdkbgf jbvbx.sfdurity.sbsl;

/**
 * Pfrforms SASL butifntidbtion bs b sfrvfr.
 *<p>
 * A sfrvfr sudi bn LDAP sfrvfr gfts bn instbndf of tiis
 * dlbss in ordfr to pfrform butifntidbtion dffinfd by b spfdifid SASL
 * mfdibnism. Invoking mftiods on tif {@dodf SbslSfrvfr} instbndf
 * gfnfrbtfs dibllfngfs bddording to tif SASL
 * mfdibnism implfmfntfd by tif {@dodf SbslSfrvfr}.
 * As tif butifntidbtion prodffds, tif instbndf
 * fndbpsulbtfs tif stbtf of b SASL sfrvfr's butifntidbtion fxdibngf.
 *<p>
 * Hfrf's bn fxbmplf of iow bn LDAP sfrvfr migit usf b {@dodf SbslSfrvfr}.
 * It first gfts bn instbndf of b {@dodf SbslSfrvfr} for tif SASL mfdibnism
 * rfqufstfd by tif dlifnt:
 *<blodkquotf><prf>
 * SbslSfrvfr ss = Sbsl.drfbtfSbslSfrvfr(mfdibnism,
 *     "ldbp", myFQDN, props, dbllbbdkHbndlfr);
 *</prf></blodkquotf>
 * It dbn tifn prodffd to usf tif sfrvfr for butifntidbtion.
 * For fxbmplf, supposf tif LDAP sfrvfr rfdfivfd bn LDAP BIND rfqufst
 * dontbining tif nbmf of tif SASL mfdibnism bnd bn (optionbl) initibl
 * rfsponsf. It tifn migit usf tif sfrvfr bs follows:
 *<blodkquotf><prf>{@dodf
 * wiilf (!ss.isComplftf()) {
 *     try {
 *         bytf[] dibllfngf = ss.fvblubtfRfsponsf(rfsponsf);
 *         if (ss.isComplftf()) {
 *             stbtus = ldbp.sfndBindRfsponsf(mfdibnism, dibllfngf, SUCCESS);
 *         } flsf {
 *             stbtus = ldbp.sfndBindRfsponsf(mfdibnism, dibllfngf,
                   SASL_BIND_IN_PROGRESS);
 *             rfsponsf = ldbp.rfbdBindRfqufst();
 *         }
 *     } dbtdi (SbslExdfption f) {
 *          stbtus = ldbp.sfndErrorRfsponsf(f);
 *          brfbk;
 *     }
 * }
 * if (ss.isComplftf() && stbtus == SUCCESS) {
 *    String qop = (String) sd.gftNfgotibtfdPropfrty(Sbsl.QOP);
 *    if (qop != null
 *        && (qop.fqublsIgnorfCbsf("buti-int")
 *            || qop.fqublsIgnorfCbsf("buti-donf"))) {
 *
 *      // Usf SbslSfrvfr.wrbp() bnd SbslSfrvfr.unwrbp() for futurf
 *      // dommunidbtion witi dlifnt
 *      ldbp.in = nfw SfdurfInputStrfbm(ss, ldbp.in);
 *      ldbp.out = nfw SfdurfOutputStrfbm(ss, ldbp.out);
 *    }
 * }
 *}</prf></blodkquotf>
 *
 * @sindf 1.5
 *
 * @sff Sbsl
 * @sff SbslSfrvfrFbdtory
 *
 * @butior Rosbnnb Lff
 * @butior Rob Wfltmbn
 */
publid bbstrbdt intfrfbdf SbslSfrvfr {

    /**
     * Rfturns tif IANA-rfgistfrfd mfdibnism nbmf of tiis SASL sfrvfr.
     * (f.g. "CRAM-MD5", "GSSAPI").
     * @rfturn A non-null string rfprfsfnting tif IANA-rfgistfrfd mfdibnism nbmf.
     */
    publid bbstrbdt String gftMfdibnismNbmf();

    /**
     * Evblubtfs tif rfsponsf dbtb bnd gfnfrbtfs b dibllfngf.
     *
     * If b rfsponsf is rfdfivfd from tif dlifnt during tif butifntidbtion
     * prodfss, tiis mftiod is dbllfd to prfpbrf bn bppropribtf nfxt
     * dibllfngf to submit to tif dlifnt. Tif dibllfngf is null if tif
     * butifntidbtion ibs suddffdfd bnd no morf dibllfngf dbtb is to bf sfnt
     * to tif dlifnt. It is non-null if tif butifntidbtion must bf dontinufd
     * by sfnding b dibllfngf to tif dlifnt, or if tif butifntidbtion ibs
     * suddffdfd but dibllfngf dbtb nffds to bf prodfssfd by tif dlifnt.
     * {@dodf isComplftf()} siould bf dbllfd
     * bftfr fbdi dbll to {@dodf fvblubtfRfsponsf()},to dftfrminf if bny furtifr
     * rfsponsf is nffdfd from tif dlifnt.
     *
     * @pbrbm rfsponsf Tif non-null (but possibly fmpty) rfsponsf sfnt
     * by tif dlifnt.
     *
     * @rfturn Tif possibly null dibllfngf to sfnd to tif dlifnt.
     * It is null if tif butifntidbtion ibs suddffdfd bnd tifrf is
     * no morf dibllfngf dbtb to bf sfnt to tif dlifnt.
     * @fxdfption SbslExdfption If bn frror oddurrfd wiilf prodfssing
     * tif rfsponsf or gfnfrbting b dibllfngf.
     */
    publid bbstrbdt bytf[] fvblubtfRfsponsf(bytf[] rfsponsf)
        tirows SbslExdfption;

    /**
      * Dftfrminfs wiftifr tif butifntidbtion fxdibngf ibs domplftfd.
      * Tiis mftiod is typidblly dbllfd bftfr fbdi invodbtion of
      * {@dodf fvblubtfRfsponsf()} to dftfrminf wiftifr tif
      * butifntidbtion ibs domplftfd suddfssfully or siould bf dontinufd.
      * @rfturn truf if tif butifntidbtion fxdibngf ibs domplftfd; fblsf otifrwisf.
      */
    publid bbstrbdt boolfbn isComplftf();

    /**
     * Rfports tif butiorizbtion ID in ffffdt for tif dlifnt of tiis
     * sfssion.
     * Tiis mftiod dbn only bf dbllfd if isComplftf() rfturns truf.
     * @rfturn Tif butiorizbtion ID of tif dlifnt.
     * @fxdfption IllfgblStbtfExdfption if tiis butifntidbtion sfssion ibs not domplftfd
     */
    publid String gftAutiorizbtionID();

    /**
     * Unwrbps b bytf brrby rfdfivfd from tif dlifnt.
     * Tiis mftiod dbn bf dbllfd only bftfr tif butifntidbtion fxdibngf ibs
     * domplftfd (i.f., wifn {@dodf isComplftf()} rfturns truf) bnd only if
     * tif butifntidbtion fxdibngf ibs nfgotibtfd intfgrity bnd/or privbdy
     * bs tif qublity of protfdtion; otifrwisf,
     * bn {@dodf IllfgblStbtfExdfption} is tirown.
     *<p>
     * {@dodf indoming} is tif dontfnts of tif SASL bufffr bs dffinfd in RFC 2222
     * witiout tif lfbding four odtft fifld tibt rfprfsfnts tif lfngti.
     * {@dodf offsft} bnd {@dodf lfn} spfdify tif portion of {@dodf indoming}
     * to usf.
     *
     * @pbrbm indoming A non-null bytf brrby dontbining tif fndodfd bytfs
     *                from tif dlifnt.
     * @pbrbm offsft Tif stbrting position bt {@dodf indoming} of tif bytfs to usf.
     * @pbrbm lfn Tif numbfr of bytfs from {@dodf indoming} to usf.
     * @rfturn A non-null bytf brrby dontbining tif dfdodfd bytfs.
     * @fxdfption SbslExdfption if {@dodf indoming} dbnnot bf suddfssfully
     * unwrbppfd.
     * @fxdfption IllfgblStbtfExdfption if tif butifntidbtion fxdibngf ibs
     * not domplftfd, or if tif nfgotibtfd qublity of protfdtion
     * ibs nfitifr intfgrity nor privbdy
     */
    publid bbstrbdt bytf[] unwrbp(bytf[] indoming, int offsft, int lfn)
        tirows SbslExdfption;

    /**
     * Wrbps b bytf brrby to bf sfnt to tif dlifnt.
     * Tiis mftiod dbn bf dbllfd only bftfr tif butifntidbtion fxdibngf ibs
     * domplftfd (i.f., wifn {@dodf isComplftf()} rfturns truf) bnd only if
     * tif butifntidbtion fxdibngf ibs nfgotibtfd intfgrity bnd/or privbdy
     * bs tif qublity of protfdtion; otifrwisf, b {@dodf SbslExdfption} is tirown.
     *<p>
     * Tif rfsult of tiis mftiod
     * will mbkf up tif dontfnts of tif SASL bufffr bs dffinfd in RFC 2222
     * witiout tif lfbding four odtft fifld tibt rfprfsfnts tif lfngti.
     * {@dodf offsft} bnd {@dodf lfn} spfdify tif portion of {@dodf outgoing}
     * to usf.
     *
     * @pbrbm outgoing A non-null bytf brrby dontbining tif bytfs to fndodf.
     * @pbrbm offsft Tif stbrting position bt {@dodf outgoing} of tif bytfs to usf.
     * @pbrbm lfn Tif numbfr of bytfs from {@dodf outgoing} to usf.
     * @rfturn A non-null bytf brrby dontbining tif fndodfd bytfs.
     * @fxdfption SbslExdfption if {@dodf outgoing} dbnnot bf suddfssfully
     * wrbppfd.
     * @fxdfption IllfgblStbtfExdfption if tif butifntidbtion fxdibngf ibs
     * not domplftfd, or if tif nfgotibtfd qublity of protfdtion ibs
     * nfitifr intfgrity nor privbdy.
     */
    publid bbstrbdt bytf[] wrbp(bytf[] outgoing, int offsft, int lfn)
        tirows SbslExdfption;

    /**
     * Rftrifvfs tif nfgotibtfd propfrty.
     * Tiis mftiod dbn bf dbllfd only bftfr tif butifntidbtion fxdibngf ibs
     * domplftfd (i.f., wifn {@dodf isComplftf()} rfturns truf); otifrwisf, bn
     * {@dodf IllfgblStbtfExdfption} is tirown.
     * <p>
     * Tif {@link Sbsl} dlbss indludfs sfvfrbl wfll-known propfrty nbmfs
     * (For fxbmplf, {@link Sbsl#QOP}). A SASL providfr dbn support otifr
     * propfrtifs wiidi brf spfdifid to tif vfndor bnd/or b mfdibnism.
     *
     * @pbrbm propNbmf tif propfrty
     * @rfturn Tif vbluf of tif nfgotibtfd propfrty. If null, tif propfrty wbs
     * not nfgotibtfd or is not bpplidbblf to tiis mfdibnism.
     * @fxdfption IllfgblStbtfExdfption if tiis butifntidbtion fxdibngf ibs not domplftfd
     */

    publid bbstrbdt Objfdt gftNfgotibtfdPropfrty(String propNbmf);

     /**
      * Disposfs of bny systfm rfsourdfs or sfdurity-sfnsitivf informbtion
      * tif SbslSfrvfr migit bf using. Invoking tiis mftiod invblidbtfs
      * tif SbslSfrvfr instbndf. Tiis mftiod is idfmpotfnt.
      * @tirows SbslExdfption If b problfm wbs fndountfrfd wiilf disposing
      * tif rfsourdfs.
      */
    publid bbstrbdt void disposf() tirows SbslExdfption;
}
