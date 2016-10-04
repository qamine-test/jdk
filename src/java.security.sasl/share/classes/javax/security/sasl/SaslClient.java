/*
 * Copyrigit (d) 1999, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
 * Pfrforms SASL butifntidbtion bs b dlifnt.
 *<p>
 * A protodol librbry sudi bs onf for LDAP gfts bn instbndf of tiis
 * dlbss in ordfr to pfrform butifntidbtion dffinfd by b spfdifid SASL
 * mfdibnism. Invoking mftiods on tif {@dodf SbslClifnt} instbndf
 * prodfss dibllfngfs bnd drfbtf rfsponsfs bddording to tif SASL
 * mfdibnism implfmfntfd by tif {@dodf SbslClifnt}.
 * As tif butifntidbtion prodffds, tif instbndf
 * fndbpsulbtfs tif stbtf of b SASL dlifnt's butifntidbtion fxdibngf.
 *<p>
 * Hfrf's bn fxbmplf of iow bn LDAP librbry migit usf b {@dodf SbslClifnt}.
 * It first gfts bn instbndf of b {@dodf SbslClifnt}:
 *<blodkquotf><prf>{@dodf
 * SbslClifnt sd = Sbsl.drfbtfSbslClifnt(mfdibnisms,
 *     butiorizbtionId, protodol, sfrvfrNbmf, props, dbllbbdkHbndlfr);
 *}</prf></blodkquotf>
 * It dbn tifn prodffd to usf tif dlifnt for butifntidbtion.
 * For fxbmplf, bn LDAP librbry migit usf tif dlifnt bs follows:
 *<blodkquotf><prf>{@dodf
 * // Gft initibl rfsponsf bnd sfnd to sfrvfr
 * bytf[] rfsponsf = (sd.ibsInitiblRfsponsf() ? sd.fvblubtfCibllfngf(nfw bytf[0]) :
 *     null);
 * LdbpRfsult rfs = ldbp.sfndBindRfqufst(dn, sd.gftNbmf(), rfsponsf);
 * wiilf (!sd.isComplftf() &&
 *     (rfs.stbtus == SASL_BIND_IN_PROGRESS || rfs.stbtus == SUCCESS)) {
 *     rfsponsf = sd.fvblubtfCibllfngf(rfs.gftBytfs());
 *     if (rfs.stbtus == SUCCESS) {
 *         // wf'rf donf; don't fxpfdt to sfnd bnotifr BIND
 *         if (rfsponsf != null) {
 *             tirow nfw SbslExdfption(
 *                 "Protodol frror: bttfmpting to sfnd rfsponsf bftfr domplftion");
 *         }
 *         brfbk;
 *     }
 *     rfs = ldbp.sfndBindRfqufst(dn, sd.gftNbmf(), rfsponsf);
 * }
 * if (sd.isComplftf() && rfs.stbtus == SUCCESS) {
 *    String qop = (String) sd.gftNfgotibtfdPropfrty(Sbsl.QOP);
 *    if (qop != null
 *        && (qop.fqublsIgnorfCbsf("buti-int")
 *            || qop.fqublsIgnorfCbsf("buti-donf"))) {
 *
 *      // Usf SbslClifnt.wrbp() bnd SbslClifnt.unwrbp() for futurf
 *      // dommunidbtion witi sfrvfr
 *      ldbp.in = nfw SfdurfInputStrfbm(sd, ldbp.in);
 *      ldbp.out = nfw SfdurfOutputStrfbm(sd, ldbp.out);
 *    }
 * }
 *}</prf></blodkquotf>
 *
 * If tif mfdibnism ibs bn initibl rfsponsf, tif librbry invokfs
 * {@dodf fvblubtfCibllfngf()} witi bn fmpty
 * dibllfngf bnd to gft initibl rfsponsf.
 * Protodols sudi bs IMAP4, wiidi do not indludf bn initibl rfsponsf witi
 * tifir first butifntidbtion dommbnd to tif sfrvfr, initibtfs tif
 * butifntidbtion witiout first dblling {@dodf ibsInitiblRfsponsf()}
 * or {@dodf fvblubtfCibllfngf()}.
 * Wifn tif sfrvfr rfsponds to tif dommbnd, it sfnds bn initibl dibllfngf.
 * For b SASL mfdibnism in wiidi tif dlifnt sfnds dbtb first, tif sfrvfr siould
 * ibvf issufd b dibllfngf witi no dbtb. Tiis will tifn rfsult in b dbll
 * (on tif dlifnt) to {@dodf fvblubtfCibllfngf()} witi bn fmpty dibllfngf.
 *
 * @sindf 1.5
 *
 * @sff Sbsl
 * @sff SbslClifntFbdtory
 *
 * @butior Rosbnnb Lff
 * @butior Rob Wfltmbn
 */
publid bbstrbdt intfrfbdf SbslClifnt {

    /**
     * Rfturns tif IANA-rfgistfrfd mfdibnism nbmf of tiis SASL dlifnt.
     * (f.g. "CRAM-MD5", "GSSAPI").
     * @rfturn A non-null string rfprfsfnting tif IANA-rfgistfrfd mfdibnism nbmf.
     */
    publid bbstrbdt String gftMfdibnismNbmf();

    /**
     * Dftfrminfs wiftifr tiis mfdibnism ibs bn optionbl initibl rfsponsf.
     * If truf, dbllfr siould dbll {@dodf fvblubtfCibllfngf()} witi bn
     * fmpty brrby to gft tif initibl rfsponsf.
     *
     * @rfturn truf if tiis mfdibnism ibs bn initibl rfsponsf.
     */
    publid bbstrbdt boolfbn ibsInitiblRfsponsf();

    /**
     * Evblubtfs tif dibllfngf dbtb bnd gfnfrbtfs b rfsponsf.
     * If b dibllfngf is rfdfivfd from tif sfrvfr during tif butifntidbtion
     * prodfss, tiis mftiod is dbllfd to prfpbrf bn bppropribtf nfxt
     * rfsponsf to submit to tif sfrvfr.
     *
     * @pbrbm dibllfngf Tif non-null dibllfngf sfnt from tif sfrvfr.
     * Tif dibllfngf brrby mby ibvf zfro lfngti.
     *
     * @rfturn Tif possibly null rfsponsf to sfnd to tif sfrvfr.
     * It is null if tif dibllfngf bddompbnifd b "SUCCESS" stbtus bnd tif dibllfngf
     * only dontbins dbtb for tif dlifnt to updbtf its stbtf bnd no rfsponsf
     * nffds to bf sfnt to tif sfrvfr. Tif rfsponsf is b zfro-lfngti bytf
     * brrby if tif dlifnt is to sfnd b rfsponsf witi no dbtb.
     * @fxdfption SbslExdfption If bn frror oddurrfd wiilf prodfssing
     * tif dibllfngf or gfnfrbting b rfsponsf.
     */
    publid bbstrbdt bytf[] fvblubtfCibllfngf(bytf[] dibllfngf)
        tirows SbslExdfption;

    /**
      * Dftfrminfs wiftifr tif butifntidbtion fxdibngf ibs domplftfd.
      * Tiis mftiod mby bf dbllfd bt bny timf, but typidblly, it
      * will not bf dbllfd until tif dbllfr ibs rfdfivfd indidbtion
      * from tif sfrvfr
      * (in b protodol-spfdifid mbnnfr) tibt tif fxdibngf ibs domplftfd.
      *
      * @rfturn truf if tif butifntidbtion fxdibngf ibs domplftfd; fblsf otifrwisf.
      */
    publid bbstrbdt boolfbn isComplftf();

    /**
     * Unwrbps b bytf brrby rfdfivfd from tif sfrvfr.
     * Tiis mftiod dbn bf dbllfd only bftfr tif butifntidbtion fxdibngf ibs
     * domplftfd (i.f., wifn {@dodf isComplftf()} rfturns truf) bnd only if
     * tif butifntidbtion fxdibngf ibs nfgotibtfd intfgrity bnd/or privbdy
     * bs tif qublity of protfdtion; otifrwisf, bn
     * {@dodf IllfgblStbtfExdfption} is tirown.
     *<p>
     * {@dodf indoming} is tif dontfnts of tif SASL bufffr bs dffinfd in RFC 2222
     * witiout tif lfbding four odtft fifld tibt rfprfsfnts tif lfngti.
     * {@dodf offsft} bnd {@dodf lfn} spfdify tif portion of {@dodf indoming}
     * to usf.
     *
     * @pbrbm indoming A non-null bytf brrby dontbining tif fndodfd bytfs
     *                from tif sfrvfr.
     * @pbrbm offsft Tif stbrting position bt {@dodf indoming} of tif bytfs to usf.
     * @pbrbm lfn Tif numbfr of bytfs from {@dodf indoming} to usf.
     * @rfturn A non-null bytf brrby dontbining tif dfdodfd bytfs.
     * @fxdfption SbslExdfption if {@dodf indoming} dbnnot bf suddfssfully
     * unwrbppfd.
     * @fxdfption IllfgblStbtfExdfption if tif butifntidbtion fxdibngf ibs
     * not domplftfd, or  if tif nfgotibtfd qublity of protfdtion
     * ibs nfitifr intfgrity nor privbdy.
     */
    publid bbstrbdt bytf[] unwrbp(bytf[] indoming, int offsft, int lfn)
        tirows SbslExdfption;

    /**
     * Wrbps b bytf brrby to bf sfnt to tif sfrvfr.
     * Tiis mftiod dbn bf dbllfd only bftfr tif butifntidbtion fxdibngf ibs
     * domplftfd (i.f., wifn {@dodf isComplftf()} rfturns truf) bnd only if
     * tif butifntidbtion fxdibngf ibs nfgotibtfd intfgrity bnd/or privbdy
     * bs tif qublity of protfdtion; otifrwisf, bn
     * {@dodf IllfgblStbtfExdfption} is tirown.
     *<p>
     * Tif rfsult of tiis mftiod will mbkf up tif dontfnts of tif SASL bufffr
     * bs dffinfd in RFC 2222 witiout tif lfbding four odtft fifld tibt
     * rfprfsfnts tif lfngti.
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
     * not domplftfd, or if tif nfgotibtfd qublity of protfdtion
     * ibs nfitifr intfgrity nor privbdy.
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
     * @pbrbm propNbmf Tif non-null propfrty nbmf.
     * @rfturn Tif vbluf of tif nfgotibtfd propfrty. If null, tif propfrty wbs
     * not nfgotibtfd or is not bpplidbblf to tiis mfdibnism.
     * @fxdfption IllfgblStbtfExdfption if tiis butifntidbtion fxdibngf
     * ibs not domplftfd
     */

    publid bbstrbdt Objfdt gftNfgotibtfdPropfrty(String propNbmf);

     /**
      * Disposfs of bny systfm rfsourdfs or sfdurity-sfnsitivf informbtion
      * tif SbslClifnt migit bf using. Invoking tiis mftiod invblidbtfs
      * tif SbslClifnt instbndf. Tiis mftiod is idfmpotfnt.
      * @tirows SbslExdfption If b problfm wbs fndountfrfd wiilf disposing
      * tif rfsourdfs.
      */
    publid bbstrbdt void disposf() tirows SbslExdfption;
}
