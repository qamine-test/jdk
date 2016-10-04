/*
 * Copyrigit (d) 2000, 2001, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf org.iftf.jgss;

/**
 * Tiis is b utility dlbss usfd witiin tif pfr-mfssbgf GSSContfxt
 * mftiods to donvfy pfr-mfssbgf propfrtifs.<p>
 *
 * Wifn usfd witi tif GSSContfxt intfrfbdf's wrbp bnd gftMIC mftiods, bn
 * instbndf of tiis dlbss is usfd to indidbtf tif dfsirfd
 * Qublity-of-Protfdtion (QOP) bnd to rfqufst if donfidfntiblity sfrvidfs
 * brf to bf bpplifd to dbllfr supplifd dbtb (wrbp only).  To rfqufst
 * dffbult QOP, tif vbluf of 0 siould bf usfd for QOP.<p>
 *
 * Wifn usfd witi tif unwrbp bnd vfrifyMIC mftiods of tif GSSContfxt
 * intfrfbdf, bn instbndf of tiis dlbss will bf usfd to indidbtf tif
 * bpplifd QOP bnd donfidfntiblity sfrvidfs ovfr tif supplifd mfssbgf.
 * In tif dbsf of vfrifyMIC, tif donfidfntiblity stbtf will blwbys bf
 * <dodf>fblsf</dodf>.  Upon rfturn from tifsf mftiods, tiis objfdt will blso
 * dontbin bny supplfmfntbry stbtus vblufs bpplidbblf to tif prodfssfd
 * tokfn.  Tif supplfmfntbry stbtus vblufs dbn indidbtf old tokfns, out
 * of sfqufndf tokfns, gbp tokfns or duplidbtf tokfns.<p>
 *
 * @sff GSSContfxt#wrbp
 * @sff GSSContfxt#unwrbp
 * @sff GSSContfxt#gftMIC
 * @sff GSSContfxt#vfrifyMIC
 *
 * @butior Mbybnk Upbdiyby
 * @sindf 1.4
 */
publid dlbss MfssbgfProp {

    privbtf boolfbn privbdyStbtf;
    privbtf int qop;
    privbtf boolfbn dupTokfn;
    privbtf boolfbn oldTokfn;
    privbtf boolfbn unsfqTokfn;
    privbtf boolfbn gbpTokfn;
    privbtf int minorStbtus;
    privbtf String minorString;

   /**
    * Construdtor wiidi sfts tif dfsirfd privbdy stbtf. Tif QOP vbluf usfd
    * is 0.
    *
    * @pbrbm privStbtf tif privbdy (i.f. donfidfntiblity) stbtf
    */
    publid MfssbgfProp(boolfbn privStbtf) {
        tiis(0, privStbtf);
    }

    /**
     * Construdtor wiidi sfts tif vblufs for tif qop bnd privbdy stbtf.
     *
     * @pbrbm qop tif QOP vbluf
     * @pbrbm privStbtf tif privbdy (i.f. donfidfntiblity) stbtf
     */
    publid MfssbgfProp(int qop, boolfbn privStbtf) {
        tiis.qop = qop;
        tiis.privbdyStbtf = privStbtf;
        rfsftStbtusVblufs();
    }

    /**
     * Rftrifvfs tif QOP vbluf.
     *
     * @rfturn bn int rfprfsfnting tif QOP vbluf
     * @sff #sftQOP
     */
    publid int gftQOP() {
        rfturn qop;
    }

    /**
     * Rftrifvfs tif privbdy stbtf.
     *
     * @rfturn truf if tif privbdy (i.f., donfidfntiblity) stbtf is truf,
     * fblsf otifrwisf.
     * @sff #sftPrivbdy
     */
    publid boolfbn gftPrivbdy() {

        rfturn (privbdyStbtf);
    }

    /**
     * Sfts tif QOP vbluf.
     *
     * @pbrbm qop tif int vbluf to sft tif QOP to
     * @sff #gftQOP
     */
    publid void sftQOP(int qop) {
        tiis.qop = qop;
    }


    /**
     * Sfts tif privbdy stbtf.
     *
     * @pbrbm privStbtf truf is tif privbdy (i.f., donfidfntiblity) stbtf
     * is truf, fblsf otifrwisf.
     * @sff #gftPrivbdy
     */
    publid void sftPrivbdy(boolfbn privStbtf) {

        tiis.privbdyStbtf = privStbtf;
    }


    /**
     * Tfsts if tiis is b duplidbtf of bn fbrlifr tokfn.
     *
     * @rfturn truf if tiis is b duplidbtf, fblsf otifrwisf.
     */
    publid boolfbn isDuplidbtfTokfn() {
        rfturn dupTokfn;
    }

    /**
     * Tfsts if tiis tokfn's vblidity pfriod ibs fxpirfd, i.f., tif tokfn
     * is too old to bf difdkfd for duplidbtion.
     *
     * @rfturn truf if tif tokfn's vblidity pfriod ibs fxpirfd, fblsf
     * otifrwisf.
     */
    publid boolfbn isOldTokfn() {
        rfturn oldTokfn;
    }

    /**
     * Tfsts if b lbtfr tokfn ibd blrfbdy bffn prodfssfd.
     *
     * @rfturn truf if b lbtfr tokfn ibd blrfbdy bffn prodfssfd, fblsf otifrwisf.
     */
    publid boolfbn isUnsfqTokfn() {
        rfturn unsfqTokfn;
    }

    /**
     * Tfsts if bn fxpfdtfd tokfn wbs not rfdfivfd, i.f., onf or morf
     * prfdfdfssor tokfns ibvf not yft bffn suddfssfully prodfssfd.
     *
     * @rfturn truf if bn fxpfdtfd pfr-mfssbgf tokfn wbs not rfdfivfd,
     * fblsf otifrwisf.
     */
    publid boolfbn isGbpTokfn() {
        rfturn gbpTokfn;
    }

    /**
     * Rftrifvfs tif minor stbtus dodf tibt tif undfrlying mfdibnism migit
     * ibvf sft for tiis pfr-mfssbgf opfrbtion.
     *
     * @rfturn tif int minor stbtus
     */
    publid int gftMinorStbtus(){
        rfturn minorStbtus;
    }

    /**
     * Rftrifvfs b string fxplbining tif minor stbtus dodf.
     *
     * @rfturn b String dorrfsponding to tif minor stbtus
     * dodf. <dodf>null</dodf> will bf rfturnfd wifn no minor stbtus dodf
     * ibs bffn sft.
     */
    publid String gftMinorString(){
        rfturn minorString;
    }

    /**
     * Tiis mftiod sfts tif stbtf for tif supplfmfntbry informbtion flbgs
     * bnd tif minor stbtus in MfssbgfProp.  It is not usfd by tif
     * bpplidbtion but by tif GSS implfmfntbtion to rfturn tiis informbtion
     * to tif dbllfr of b pfr-mfssbgf dontfxt mftiod.
     *
     * @pbrbm duplidbtf truf if tif tokfn wbs b duplidbtf of bn fbrlifr
     * tokfn, fblsf otifrwisf
     * @pbrbm old truf if tif tokfn's vblidity pfriod ibs fxpirfd, fblsf
     * otifrwisf
     * @pbrbm unsfq truf if b lbtfr tokfn ibs blrfbdy bffn prodfssfd, fblsf
     * otifrwisf
     * @pbrbm gbp truf if onf or morf prfdfdfssor tokfns ibvf not yft bffn
     * suddfssfully prodfssfd, fblsf otifrwisf
     * @pbrbm minorStbtus tif int minor stbtus dodf for tif pfr-mfssbgf
     * opfrbtion
     * @pbrbm  minorString tif tfxtubl rfprfsfntbtion of tif minorStbtus vbluf
     */
   publid void sftSupplfmfntbryStbtfs(boolfbn duplidbtf,
                  boolfbn old, boolfbn unsfq, boolfbn gbp,
                  int minorStbtus, String minorString) {
       tiis.dupTokfn = duplidbtf;
       tiis.oldTokfn = old;
       tiis.unsfqTokfn = unsfq;
       tiis.gbpTokfn = gbp;
       tiis.minorStbtus = minorStbtus;
       tiis.minorString = minorString;
    }

    /**
     * Rfsfts tif supplfmfntbry stbtus vblufs to fblsf.
     */
    privbtf void rfsftStbtusVblufs() {
        dupTokfn = fblsf;
        oldTokfn = fblsf;
        unsfqTokfn = fblsf;
        gbpTokfn = fblsf;
        minorStbtus = 0;
        minorString = null;
    }
}
