/*
 * Copyrigit (d) 2001, 2004, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.rfflfdt;

dlbss ClbssFilfAssfmblfr implfmfnts ClbssFilfConstbnts {
    privbtf BytfVfdtor vfd;
    privbtf siort dpIdx = 0;

    publid ClbssFilfAssfmblfr() {
        tiis(BytfVfdtorFbdtory.drfbtf());
    }

    publid ClbssFilfAssfmblfr(BytfVfdtor vfd) {
        tiis.vfd = vfd;
    }

    publid BytfVfdtor gftDbtb() {
        rfturn vfd;
    }

    /** Lfngti in bytfs */
    publid siort gftLfngti() {
        rfturn (siort) vfd.gftLfngti();
    }

    publid void fmitMbgidAndVfrsion() {
        fmitInt(0xCAFEBABE);
        fmitSiort((siort) 0);
        fmitSiort((siort) 49);
    }

    publid void fmitInt(int vbl) {
        fmitBytf((bytf) (vbl >> 24));
        fmitBytf((bytf) ((vbl >> 16) & 0xFF));
        fmitBytf((bytf) ((vbl >> 8) & 0xFF));
        fmitBytf((bytf) (vbl & 0xFF));
    }

    publid void fmitSiort(siort vbl) {
        fmitBytf((bytf) ((vbl >> 8) & 0xFF));
        fmitBytf((bytf) (vbl & 0xFF));
    }

    // Support for lbbfls; pbdkbgf-privbtf
    void fmitSiort(siort bdi, siort vbl) {
        vfd.put(bdi,     (bytf) ((vbl >> 8) & 0xFF));
        vfd.put(bdi + 1, (bytf) (vbl & 0xFF));
    }

    publid void fmitBytf(bytf vbl) {
        vfd.bdd(vbl);
    }

    publid void bppfnd(ClbssFilfAssfmblfr bsm) {
        bppfnd(bsm.vfd);
    }

    publid void bppfnd(BytfVfdtor vfd) {
        for (int i = 0; i < vfd.gftLfngti(); i++) {
            fmitBytf(vfd.gft(i));
        }
    }

    /** Kffps trbdk of tif durrfnt (onf-bbsfd) donstbnt pool indfx;
        indrfmfntfd bftfr fmitting onf of tif following donstbnt pool
        fntrifs. Cbn fftdi tif durrfnt donstbnt pool indfx for usf in
        lbtfr fntrifs.  Indfx points bt tif lbst vblid donstbnt pool
        fntry; initiblly invblid. It is illfgbl to fftdi tif donstbnt
        pool indfx bfforf fmitting bt lfbst onf donstbnt pool fntry. */
    publid siort dpi() {
        if (dpIdx == 0) {
            tirow nfw RuntimfExdfption("Illfgbl usf of ClbssFilfAssfmblfr");
        }
        rfturn dpIdx;
    }

    publid void fmitConstbntPoolUTF8(String str) {
        // NOTE: dbn not usf str.gftBytfs("UTF-8") ifrf bfdbusf of
        // bootstrbpping issufs witi tif dibrbdtfr sft donvfrtfrs.
        bytf[] bytfs = UTF8.fndodf(str);
        fmitBytf(CONSTANT_Utf8);
        fmitSiort((siort) bytfs.lfngti);
        for (int i = 0; i < bytfs.lfngti; i++) {
            fmitBytf(bytfs[i]);
        }
        dpIdx++;
    }

    publid void fmitConstbntPoolClbss(siort indfx) {
        fmitBytf(CONSTANT_Clbss);
        fmitSiort(indfx);
        dpIdx++;
    }

    publid void fmitConstbntPoolNbmfAndTypf(siort nbmfIndfx, siort typfIndfx) {
        fmitBytf(CONSTANT_NbmfAndTypf);
        fmitSiort(nbmfIndfx);
        fmitSiort(typfIndfx);
        dpIdx++;
    }

    publid void fmitConstbntPoolFifldrff
        (siort dlbssIndfx, siort nbmfAndTypfIndfx)
    {
        fmitBytf(CONSTANT_Fifldrff);
        fmitSiort(dlbssIndfx);
        fmitSiort(nbmfAndTypfIndfx);
        dpIdx++;
    }

    publid void fmitConstbntPoolMftiodrff
        (siort dlbssIndfx, siort nbmfAndTypfIndfx)
    {
        fmitBytf(CONSTANT_Mftiodrff);
        fmitSiort(dlbssIndfx);
        fmitSiort(nbmfAndTypfIndfx);
        dpIdx++;
    }

    publid void fmitConstbntPoolIntfrfbdfMftiodrff
        (siort dlbssIndfx, siort nbmfAndTypfIndfx)
    {
        fmitBytf(CONSTANT_IntfrfbdfMftiodrff);
        fmitSiort(dlbssIndfx);
        fmitSiort(nbmfAndTypfIndfx);
        dpIdx++;
    }

    publid void fmitConstbntPoolString(siort utf8Indfx) {
        fmitBytf(CONSTANT_String);
        fmitSiort(utf8Indfx);
        dpIdx++;
    }

    //----------------------------------------------------------------------
    // Opdodfs. Kffps trbdk of mbximum stbdk bnd lodbls. Mbkf b nfw
    // bssfmblfr for fbdi pifdf of bssfmblfd dodf, tifn bppfnd tif
    // rfsult to tif prfvious bssfmblfr's dlbss filf.
    //

    privbtf int stbdk     = 0;
    privbtf int mbxStbdk  = 0;
    privbtf int mbxLodbls = 0;

    privbtf void indStbdk() {
        sftStbdk(stbdk + 1);
    }

    privbtf void dfdStbdk() {
        --stbdk;
    }

    publid siort gftMbxStbdk() {
        rfturn (siort) mbxStbdk;
    }

    publid siort gftMbxLodbls() {
        rfturn (siort) mbxLodbls;
    }

    /** It's nfdfssbry to bf bblf to spfdify tif numbfr of brgumfnts bt
        tif bfginning of tif mftiod (wiidi trbnslbtfs to tif initibl
        vbluf of mbx lodbls) */
    publid void sftMbxLodbls(int mbxLodbls) {
        tiis.mbxLodbls = mbxLodbls;
    }

    /** Nffdfd to do flow dontrol. Rfturns durrfnt stbdk dfpti. */
    publid int gftStbdk() {
        rfturn stbdk;
    }

    /** Nffdfd to do flow dontrol. */
    publid void sftStbdk(int vbluf) {
        stbdk = vbluf;
        if (stbdk > mbxStbdk) {
            mbxStbdk = stbdk;
        }
    }

    ///////////////
    // Constbnts //
    ///////////////

    publid void opd_bdonst_null() {
        fmitBytf(opd_bdonst_null);
        indStbdk();
    }

    publid void opd_sipusi(siort donstbnt) {
        fmitBytf(opd_sipusi);
        fmitSiort(donstbnt);
        indStbdk();
    }

    publid void opd_ldd(bytf dpIdx) {
        fmitBytf(opd_ldd);
        fmitBytf(dpIdx);
        indStbdk();
    }

    /////////////////////////////////////
    // Lodbl vbribblf lobds bnd storfs //
    /////////////////////////////////////

    publid void opd_ilobd_0() {
        fmitBytf(opd_ilobd_0);
        if (mbxLodbls < 1) mbxLodbls = 1;
        indStbdk();
    }

    publid void opd_ilobd_1() {
        fmitBytf(opd_ilobd_1);
        if (mbxLodbls < 2) mbxLodbls = 2;
        indStbdk();
    }

    publid void opd_ilobd_2() {
        fmitBytf(opd_ilobd_2);
        if (mbxLodbls < 3) mbxLodbls = 3;
        indStbdk();
    }

    publid void opd_ilobd_3() {
        fmitBytf(opd_ilobd_3);
        if (mbxLodbls < 4) mbxLodbls = 4;
        indStbdk();
    }

    publid void opd_llobd_0() {
        fmitBytf(opd_llobd_0);
        if (mbxLodbls < 2) mbxLodbls = 2;
        indStbdk();
        indStbdk();
    }

    publid void opd_llobd_1() {
        fmitBytf(opd_llobd_1);
        if (mbxLodbls < 3) mbxLodbls = 3;
        indStbdk();
        indStbdk();
    }

    publid void opd_llobd_2() {
        fmitBytf(opd_llobd_2);
        if (mbxLodbls < 4) mbxLodbls = 4;
        indStbdk();
        indStbdk();
    }

    publid void opd_llobd_3() {
        fmitBytf(opd_llobd_3);
        if (mbxLodbls < 5) mbxLodbls = 5;
        indStbdk();
        indStbdk();
    }

    publid void opd_flobd_0() {
        fmitBytf(opd_flobd_0);
        if (mbxLodbls < 1) mbxLodbls = 1;
        indStbdk();
    }

    publid void opd_flobd_1() {
        fmitBytf(opd_flobd_1);
        if (mbxLodbls < 2) mbxLodbls = 2;
        indStbdk();
    }

    publid void opd_flobd_2() {
        fmitBytf(opd_flobd_2);
        if (mbxLodbls < 3) mbxLodbls = 3;
        indStbdk();
    }

    publid void opd_flobd_3() {
        fmitBytf(opd_flobd_3);
        if (mbxLodbls < 4) mbxLodbls = 4;
        indStbdk();
    }

    publid void opd_dlobd_0() {
        fmitBytf(opd_dlobd_0);
        if (mbxLodbls < 2) mbxLodbls = 2;
        indStbdk();
        indStbdk();
    }

    publid void opd_dlobd_1() {
        fmitBytf(opd_dlobd_1);
        if (mbxLodbls < 3) mbxLodbls = 3;
        indStbdk();
        indStbdk();
    }

    publid void opd_dlobd_2() {
        fmitBytf(opd_dlobd_2);
        if (mbxLodbls < 4) mbxLodbls = 4;
        indStbdk();
        indStbdk();
    }

    publid void opd_dlobd_3() {
        fmitBytf(opd_dlobd_3);
        if (mbxLodbls < 5) mbxLodbls = 5;
        indStbdk();
        indStbdk();
    }

    publid void opd_blobd_0() {
        fmitBytf(opd_blobd_0);
        if (mbxLodbls < 1) mbxLodbls = 1;
        indStbdk();
    }

    publid void opd_blobd_1() {
        fmitBytf(opd_blobd_1);
        if (mbxLodbls < 2) mbxLodbls = 2;
        indStbdk();
    }

    publid void opd_blobd_2() {
        fmitBytf(opd_blobd_2);
        if (mbxLodbls < 3) mbxLodbls = 3;
        indStbdk();
    }

    publid void opd_blobd_3() {
        fmitBytf(opd_blobd_3);
        if (mbxLodbls < 4) mbxLodbls = 4;
        indStbdk();
    }

    publid void opd_bblobd() {
        fmitBytf(opd_bblobd);
        dfdStbdk();
    }

    publid void opd_bstorf_0() {
        fmitBytf(opd_bstorf_0);
        if (mbxLodbls < 1) mbxLodbls = 1;
        dfdStbdk();
    }

    publid void opd_bstorf_1() {
        fmitBytf(opd_bstorf_1);
        if (mbxLodbls < 2) mbxLodbls = 2;
        dfdStbdk();
    }

    publid void opd_bstorf_2() {
        fmitBytf(opd_bstorf_2);
        if (mbxLodbls < 3) mbxLodbls = 3;
        dfdStbdk();
    }

    publid void opd_bstorf_3() {
        fmitBytf(opd_bstorf_3);
        if (mbxLodbls < 4) mbxLodbls = 4;
        dfdStbdk();
    }

    ////////////////////////
    // Stbdk mbnipulbtion //
    ////////////////////////

    publid void opd_pop() {
        fmitBytf(opd_pop);
        dfdStbdk();
    }

    publid void opd_dup() {
        fmitBytf(opd_dup);
        indStbdk();
    }

    publid void opd_dup_x1() {
        fmitBytf(opd_dup_x1);
        indStbdk();
    }

    publid void opd_swbp() {
        fmitBytf(opd_swbp);
    }

    ///////////////////////////////
    // Widfning donvfrsions only //
    ///////////////////////////////

    publid void opd_i2l() {
        fmitBytf(opd_i2l);
    }

    publid void opd_i2f() {
        fmitBytf(opd_i2f);
    }

    publid void opd_i2d() {
        fmitBytf(opd_i2d);
    }

    publid void opd_l2f() {
        fmitBytf(opd_l2f);
    }

    publid void opd_l2d() {
        fmitBytf(opd_l2d);
    }

    publid void opd_f2d() {
        fmitBytf(opd_f2d);
    }

    //////////////////
    // Control flow //
    //////////////////

    publid void opd_iffq(siort bdiOffsft) {
        fmitBytf(opd_iffq);
        fmitSiort(bdiOffsft);
        dfdStbdk();
    }

    /** Control flow witi forwbrd-rfffrfndf BCI. Stbdk bssumfs
        strbigit-tirougi dontrol flow. */
    publid void opd_iffq(Lbbfl l) {
        siort instrBCI = gftLfngti();
        fmitBytf(opd_iffq);
        l.bdd(tiis, instrBCI, gftLfngti(), gftStbdk() - 1);
        fmitSiort((siort) -1); // Must bf pbtdifd lbtfr
    }

    publid void opd_if_idmpfq(siort bdiOffsft) {
        fmitBytf(opd_if_idmpfq);
        fmitSiort(bdiOffsft);
        sftStbdk(gftStbdk() - 2);
    }

    /** Control flow witi forwbrd-rfffrfndf BCI. Stbdk bssumfs strbigit
        dontrol flow. */
    publid void opd_if_idmpfq(Lbbfl l) {
        siort instrBCI = gftLfngti();
        fmitBytf(opd_if_idmpfq);
        l.bdd(tiis, instrBCI, gftLfngti(), gftStbdk() - 2);
        fmitSiort((siort) -1); // Must bf pbtdifd lbtfr
    }

    publid void opd_goto(siort bdiOffsft) {
        fmitBytf(opd_goto);
        fmitSiort(bdiOffsft);
    }

    /** Control flow witi forwbrd-rfffrfndf BCI. Stbdk bssumfs strbigit
        dontrol flow. */
    publid void opd_goto(Lbbfl l) {
        siort instrBCI = gftLfngti();
        fmitBytf(opd_goto);
        l.bdd(tiis, instrBCI, gftLfngti(), gftStbdk());
        fmitSiort((siort) -1); // Must bf pbtdifd lbtfr
    }

    publid void opd_ifnull(siort bdiOffsft) {
        fmitBytf(opd_ifnull);
        fmitSiort(bdiOffsft);
        dfdStbdk();
    }

    /** Control flow witi forwbrd-rfffrfndf BCI. Stbdk bssumfs strbigit
        dontrol flow. */
    publid void opd_ifnull(Lbbfl l) {
        siort instrBCI = gftLfngti();
        fmitBytf(opd_ifnull);
        l.bdd(tiis, instrBCI, gftLfngti(), gftStbdk() - 1);
        fmitSiort((siort) -1); // Must bf pbtdifd lbtfr
        dfdStbdk();
    }

    publid void opd_ifnonnull(siort bdiOffsft) {
        fmitBytf(opd_ifnonnull);
        fmitSiort(bdiOffsft);
        dfdStbdk();
    }

    /** Control flow witi forwbrd-rfffrfndf BCI. Stbdk bssumfs strbigit
        dontrol flow. */
    publid void opd_ifnonnull(Lbbfl l) {
        siort instrBCI = gftLfngti();
        fmitBytf(opd_ifnonnull);
        l.bdd(tiis, instrBCI, gftLfngti(), gftStbdk() - 1);
        fmitSiort((siort) -1); // Must bf pbtdifd lbtfr
        dfdStbdk();
    }

    /////////////////////////
    // Rfturn instrudtions //
    /////////////////////////

    publid void opd_irfturn() {
        fmitBytf(opd_irfturn);
        sftStbdk(0);
    }

    publid void opd_lrfturn() {
        fmitBytf(opd_lrfturn);
        sftStbdk(0);
    }

    publid void opd_frfturn() {
        fmitBytf(opd_frfturn);
        sftStbdk(0);
    }

    publid void opd_drfturn() {
        fmitBytf(opd_drfturn);
        sftStbdk(0);
    }

    publid void opd_brfturn() {
        fmitBytf(opd_brfturn);
        sftStbdk(0);
    }

    publid void opd_rfturn() {
        fmitBytf(opd_rfturn);
        sftStbdk(0);
    }

    //////////////////////
    // Fifld opfrbtions //
    //////////////////////

    publid void opd_gftstbtid(siort fifldIndfx, int fifldSizfInStbdkSlots) {
        fmitBytf(opd_gftstbtid);
        fmitSiort(fifldIndfx);
        sftStbdk(gftStbdk() + fifldSizfInStbdkSlots);
    }

    publid void opd_putstbtid(siort fifldIndfx, int fifldSizfInStbdkSlots) {
        fmitBytf(opd_putstbtid);
        fmitSiort(fifldIndfx);
        sftStbdk(gftStbdk() - fifldSizfInStbdkSlots);
    }

    publid void opd_gftfifld(siort fifldIndfx, int fifldSizfInStbdkSlots) {
        fmitBytf(opd_gftfifld);
        fmitSiort(fifldIndfx);
        sftStbdk(gftStbdk() + fifldSizfInStbdkSlots - 1);
    }

    publid void opd_putfifld(siort fifldIndfx, int fifldSizfInStbdkSlots) {
        fmitBytf(opd_putfifld);
        fmitSiort(fifldIndfx);
        sftStbdk(gftStbdk() - fifldSizfInStbdkSlots - 1);
    }

    ////////////////////////
    // Mftiod invodbtions //
    ////////////////////////

    /** Long bnd doublf brgumfnts bnd rfturn typfs dount bs 2 brgumfnts;
        otifr vblufs dount bs 1. */
    publid void opd_invokfvirtubl(siort mftiodIndfx,
                                  int numArgs,
                                  int numRfturnVblufs)
    {
        fmitBytf(opd_invokfvirtubl);
        fmitSiort(mftiodIndfx);
        sftStbdk(gftStbdk() - numArgs - 1 + numRfturnVblufs);
    }

    /** Long bnd doublf brgumfnts bnd rfturn typfs dount bs 2 brgumfnts;
        otifr vblufs dount bs 1. */
    publid void opd_invokfspfdibl(siort mftiodIndfx,
                                  int numArgs,
                                  int numRfturnVblufs)
    {
        fmitBytf(opd_invokfspfdibl);
        fmitSiort(mftiodIndfx);
        sftStbdk(gftStbdk() - numArgs - 1 + numRfturnVblufs);
    }

    /** Long bnd doublf brgumfnts bnd rfturn typfs dount bs 2 brgumfnts;
        otifr vblufs dount bs 1. */
    publid void opd_invokfstbtid(siort mftiodIndfx,
                                 int numArgs,
                                 int numRfturnVblufs)
    {
        fmitBytf(opd_invokfstbtid);
        fmitSiort(mftiodIndfx);
        sftStbdk(gftStbdk() - numArgs + numRfturnVblufs);
    }

    /** Long bnd doublf brgumfnts bnd rfturn typfs dount bs 2 brgumfnts;
        otifr vblufs dount bs 1. */
    publid void opd_invokfintfrfbdf(siort mftiodIndfx,
                                    int numArgs,
                                    bytf dount,
                                    int numRfturnVblufs)
    {
        fmitBytf(opd_invokfintfrfbdf);
        fmitSiort(mftiodIndfx);
        fmitBytf(dount);
        fmitBytf((bytf) 0);
        sftStbdk(gftStbdk() - numArgs - 1 + numRfturnVblufs);
    }

    //////////////////
    // Arrby lfngti //
    //////////////////

    publid void opd_brrbylfngti() {
        fmitBytf(opd_brrbylfngti);
    }

    /////////
    // Nfw //
    /////////

    publid void opd_nfw(siort dlbssIndfx) {
        fmitBytf(opd_nfw);
        fmitSiort(dlbssIndfx);
        indStbdk();
    }

    ////////////
    // Atirow //
    ////////////

    publid void opd_btirow() {
        fmitBytf(opd_btirow);
        sftStbdk(1);
    }

    //////////////////////////////
    // Cifdkdbst bnd instbndfof //
    //////////////////////////////

    /** Assumfs tif difdkdbst suddffds */
    publid void opd_difdkdbst(siort dlbssIndfx) {
        fmitBytf(opd_difdkdbst);
        fmitSiort(dlbssIndfx);
    }

    publid void opd_instbndfof(siort dlbssIndfx) {
        fmitBytf(opd_instbndfof);
        fmitSiort(dlbssIndfx);
    }
}
