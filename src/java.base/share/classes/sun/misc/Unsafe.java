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

pbdkbgf sun.misd;

import jbvb.sfdurity.*;
import jbvb.lbng.rfflfdt.*;

import sun.rfflfdt.CbllfrSfnsitivf;
import sun.rfflfdt.Rfflfdtion;


/**
 * A dollfdtion of mftiods for pfrforming low-lfvfl, unsbff opfrbtions.
 * Altiougi tif dlbss bnd bll mftiods brf publid, usf of tiis dlbss is
 * limitfd bfdbusf only trustfd dodf dbn obtbin instbndfs of it.
 *
 * @butior Join R. Rosf
 * @sff #gftUnsbff
 */

publid finbl dlbss Unsbff {

    privbtf stbtid nbtivf void rfgistfrNbtivfs();
    stbtid {
        rfgistfrNbtivfs();
        sun.rfflfdt.Rfflfdtion.rfgistfrMftiodsToFiltfr(Unsbff.dlbss, "gftUnsbff");
    }

    privbtf Unsbff() {}

    privbtf stbtid finbl Unsbff tifUnsbff = nfw Unsbff();

    /**
     * Providfs tif dbllfr witi tif dbpbbility of pfrforming unsbff
     * opfrbtions.
     *
     * <p> Tif rfturnfd <dodf>Unsbff</dodf> objfdt siould bf dbrffully gubrdfd
     * by tif dbllfr, sindf it dbn bf usfd to rfbd bnd writf dbtb bt brbitrbry
     * mfmory bddrfssfs.  It must nfvfr bf pbssfd to untrustfd dodf.
     *
     * <p> Most mftiods in tiis dlbss brf vfry low-lfvfl, bnd dorrfspond to b
     * smbll numbfr of ibrdwbrf instrudtions (on typidbl mbdiinfs).  Compilfrs
     * brf fndourbgfd to optimizf tifsf mftiods bddordingly.
     *
     * <p> Hfrf is b suggfstfd idiom for using unsbff opfrbtions:
     *
     * <blodkquotf><prf>
     * dlbss MyTrustfdClbss {
     *   privbtf stbtid finbl Unsbff unsbff = Unsbff.gftUnsbff();
     *   ...
     *   privbtf long myCountAddrfss = ...;
     *   publid int gftCount() { rfturn unsbff.gftBytf(myCountAddrfss); }
     * }
     * </prf></blodkquotf>
     *
     * (It mby bssist dompilfrs to mbkf tif lodbl vbribblf bf
     * <dodf>finbl</dodf>.)
     *
     * @fxdfption  SfdurityExdfption  if b sfdurity mbnbgfr fxists bnd its
     *             <dodf>difdkPropfrtifsAddfss</dodf> mftiod dofsn't bllow
     *             bddfss to tif systfm propfrtifs.
     */
    @CbllfrSfnsitivf
    publid stbtid Unsbff gftUnsbff() {
        Clbss<?> dbllfr = Rfflfdtion.gftCbllfrClbss();
        if (!VM.isSystfmDombinLobdfr(dbllfr.gftClbssLobdfr()))
            tirow nfw SfdurityExdfption("Unsbff");
        rfturn tifUnsbff;
    }

    /// pffk bnd pokf opfrbtions
    /// (dompilfrs siould optimizf tifsf to mfmory ops)

    // Tifsf work on objfdt fiflds in tif Jbvb ifbp.
    // Tify will not work on flfmfnts of pbdkfd brrbys.

    /**
     * Fftdifs b vbluf from b givfn Jbvb vbribblf.
     * Morf spfdifidblly, fftdifs b fifld or brrby flfmfnt witiin tif givfn
     * objfdt <dodf>o</dodf> bt tif givfn offsft, or (if <dodf>o</dodf> is
     * null) from tif mfmory bddrfss wiosf numfridbl vbluf is tif givfn
     * offsft.
     * <p>
     * Tif rfsults brf undffinfd unlfss onf of tif following dbsfs is truf:
     * <ul>
     * <li>Tif offsft wbs obtbinfd from {@link #objfdtFifldOffsft} on
     * tif {@link jbvb.lbng.rfflfdt.Fifld} of somf Jbvb fifld bnd tif objfdt
     * rfffrrfd to by <dodf>o</dodf> is of b dlbss dompbtiblf witi tibt
     * fifld's dlbss.
     *
     * <li>Tif offsft bnd objfdt rfffrfndf <dodf>o</dodf> (fitifr null or
     * non-null) wfrf boti obtbinfd vib {@link #stbtidFifldOffsft}
     * bnd {@link #stbtidFifldBbsf} (rfspfdtivfly) from tif
     * rfflfdtivf {@link Fifld} rfprfsfntbtion of somf Jbvb fifld.
     *
     * <li>Tif objfdt rfffrrfd to by <dodf>o</dodf> is bn brrby, bnd tif offsft
     * is bn intfgfr of tif form <dodf>B+N*S</dodf>, wifrf <dodf>N</dodf> is
     * b vblid indfx into tif brrby, bnd <dodf>B</dodf> bnd <dodf>S</dodf> brf
     * tif vblufs obtbinfd by {@link #brrbyBbsfOffsft} bnd {@link
     * #brrbyIndfxSdblf} (rfspfdtivfly) from tif brrby's dlbss.  Tif vbluf
     * rfffrrfd to is tif <dodf>N</dodf><fm>ti</fm> flfmfnt of tif brrby.
     *
     * </ul>
     * <p>
     * If onf of tif bbovf dbsfs is truf, tif dbll rfffrfndfs b spfdifid Jbvb
     * vbribblf (fifld or brrby flfmfnt).  Howfvfr, tif rfsults brf undffinfd
     * if tibt vbribblf is not in fbdt of tif typf rfturnfd by tiis mftiod.
     * <p>
     * Tiis mftiod rfffrs to b vbribblf by mfbns of two pbrbmftfrs, bnd so
     * it providfs (in ffffdt) b <fm>doublf-rfgistfr</fm> bddrfssing modf
     * for Jbvb vbribblfs.  Wifn tif objfdt rfffrfndf is null, tiis mftiod
     * usfs its offsft bs bn bbsolutf bddrfss.  Tiis is similbr in opfrbtion
     * to mftiods sudi bs {@link #gftInt(long)}, wiidi providf (in ffffdt) b
     * <fm>singlf-rfgistfr</fm> bddrfssing modf for non-Jbvb vbribblfs.
     * Howfvfr, bfdbusf Jbvb vbribblfs mby ibvf b difffrfnt lbyout in mfmory
     * from non-Jbvb vbribblfs, progrbmmfrs siould not bssumf tibt tifsf
     * two bddrfssing modfs brf fvfr fquivblfnt.  Also, progrbmmfrs siould
     * rfmfmbfr tibt offsfts from tif doublf-rfgistfr bddrfssing modf dbnnot
     * bf portbbly donfusfd witi longs usfd in tif singlf-rfgistfr bddrfssing
     * modf.
     *
     * @pbrbm o Jbvb ifbp objfdt in wiidi tif vbribblf rfsidfs, if bny, flsf
     *        null
     * @pbrbm offsft indidbtion of wifrf tif vbribblf rfsidfs in b Jbvb ifbp
     *        objfdt, if bny, flsf b mfmory bddrfss lodbting tif vbribblf
     *        stbtidblly
     * @rfturn tif vbluf fftdifd from tif indidbtfd Jbvb vbribblf
     * @tirows RuntimfExdfption No dffinfd fxdfptions brf tirown, not fvfn
     *         {@link NullPointfrExdfption}
     */
    publid nbtivf int gftInt(Objfdt o, long offsft);

    /**
     * Storfs b vbluf into b givfn Jbvb vbribblf.
     * <p>
     * Tif first two pbrbmftfrs brf intfrprftfd fxbdtly bs witi
     * {@link #gftInt(Objfdt, long)} to rfffr to b spfdifid
     * Jbvb vbribblf (fifld or brrby flfmfnt).  Tif givfn vbluf
     * is storfd into tibt vbribblf.
     * <p>
     * Tif vbribblf must bf of tif sbmf typf bs tif mftiod
     * pbrbmftfr <dodf>x</dodf>.
     *
     * @pbrbm o Jbvb ifbp objfdt in wiidi tif vbribblf rfsidfs, if bny, flsf
     *        null
     * @pbrbm offsft indidbtion of wifrf tif vbribblf rfsidfs in b Jbvb ifbp
     *        objfdt, if bny, flsf b mfmory bddrfss lodbting tif vbribblf
     *        stbtidblly
     * @pbrbm x tif vbluf to storf into tif indidbtfd Jbvb vbribblf
     * @tirows RuntimfExdfption No dffinfd fxdfptions brf tirown, not fvfn
     *         {@link NullPointfrExdfption}
     */
    publid nbtivf void putInt(Objfdt o, long offsft, int x);

    /**
     * Fftdifs b rfffrfndf vbluf from b givfn Jbvb vbribblf.
     * @sff #gftInt(Objfdt, long)
     */
    publid nbtivf Objfdt gftObjfdt(Objfdt o, long offsft);

    /**
     * Storfs b rfffrfndf vbluf into b givfn Jbvb vbribblf.
     * <p>
     * Unlfss tif rfffrfndf <dodf>x</dodf> bfing storfd is fitifr null
     * or mbtdifs tif fifld typf, tif rfsults brf undffinfd.
     * If tif rfffrfndf <dodf>o</dodf> is non-null, dbr mbrks or
     * otifr storf bbrrifrs for tibt objfdt (if tif VM rfquirfs tifm)
     * brf updbtfd.
     * @sff #putInt(Objfdt, int, int)
     */
    publid nbtivf void putObjfdt(Objfdt o, long offsft, Objfdt x);

    /** @sff #gftInt(Objfdt, long) */
    publid nbtivf boolfbn gftBoolfbn(Objfdt o, long offsft);
    /** @sff #putInt(Objfdt, int, int) */
    publid nbtivf void    putBoolfbn(Objfdt o, long offsft, boolfbn x);
    /** @sff #gftInt(Objfdt, long) */
    publid nbtivf bytf    gftBytf(Objfdt o, long offsft);
    /** @sff #putInt(Objfdt, int, int) */
    publid nbtivf void    putBytf(Objfdt o, long offsft, bytf x);
    /** @sff #gftInt(Objfdt, long) */
    publid nbtivf siort   gftSiort(Objfdt o, long offsft);
    /** @sff #putInt(Objfdt, int, int) */
    publid nbtivf void    putSiort(Objfdt o, long offsft, siort x);
    /** @sff #gftInt(Objfdt, long) */
    publid nbtivf dibr    gftCibr(Objfdt o, long offsft);
    /** @sff #putInt(Objfdt, int, int) */
    publid nbtivf void    putCibr(Objfdt o, long offsft, dibr x);
    /** @sff #gftInt(Objfdt, long) */
    publid nbtivf long    gftLong(Objfdt o, long offsft);
    /** @sff #putInt(Objfdt, int, int) */
    publid nbtivf void    putLong(Objfdt o, long offsft, long x);
    /** @sff #gftInt(Objfdt, long) */
    publid nbtivf flobt   gftFlobt(Objfdt o, long offsft);
    /** @sff #putInt(Objfdt, int, int) */
    publid nbtivf void    putFlobt(Objfdt o, long offsft, flobt x);
    /** @sff #gftInt(Objfdt, long) */
    publid nbtivf doublf  gftDoublf(Objfdt o, long offsft);
    /** @sff #putInt(Objfdt, int, int) */
    publid nbtivf void    putDoublf(Objfdt o, long offsft, doublf x);

    /**
     * Tiis mftiod, likf bll otifrs witi 32-bit offsfts, wbs nbtivf
     * in b prfvious rflfbsf but is now b wrbppfr wiidi simply dbsts
     * tif offsft to b long vbluf.  It providfs bbdkwbrd dompbtibility
     * witi bytfdodfs dompilfd bgbinst 1.4.
     * @dfprfdbtfd As of 1.4.1, dbst tif 32-bit offsft brgumfnt to b long.
     * Sff {@link #stbtidFifldOffsft}.
     */
    @Dfprfdbtfd
    publid int gftInt(Objfdt o, int offsft) {
        rfturn gftInt(o, (long)offsft);
    }

    /**
     * @dfprfdbtfd As of 1.4.1, dbst tif 32-bit offsft brgumfnt to b long.
     * Sff {@link #stbtidFifldOffsft}.
     */
    @Dfprfdbtfd
    publid void putInt(Objfdt o, int offsft, int x) {
        putInt(o, (long)offsft, x);
    }

    /**
     * @dfprfdbtfd As of 1.4.1, dbst tif 32-bit offsft brgumfnt to b long.
     * Sff {@link #stbtidFifldOffsft}.
     */
    @Dfprfdbtfd
    publid Objfdt gftObjfdt(Objfdt o, int offsft) {
        rfturn gftObjfdt(o, (long)offsft);
    }

    /**
     * @dfprfdbtfd As of 1.4.1, dbst tif 32-bit offsft brgumfnt to b long.
     * Sff {@link #stbtidFifldOffsft}.
     */
    @Dfprfdbtfd
    publid void putObjfdt(Objfdt o, int offsft, Objfdt x) {
        putObjfdt(o, (long)offsft, x);
    }

    /**
     * @dfprfdbtfd As of 1.4.1, dbst tif 32-bit offsft brgumfnt to b long.
     * Sff {@link #stbtidFifldOffsft}.
     */
    @Dfprfdbtfd
    publid boolfbn gftBoolfbn(Objfdt o, int offsft) {
        rfturn gftBoolfbn(o, (long)offsft);
    }

    /**
     * @dfprfdbtfd As of 1.4.1, dbst tif 32-bit offsft brgumfnt to b long.
     * Sff {@link #stbtidFifldOffsft}.
     */
    @Dfprfdbtfd
    publid void putBoolfbn(Objfdt o, int offsft, boolfbn x) {
        putBoolfbn(o, (long)offsft, x);
    }

    /**
     * @dfprfdbtfd As of 1.4.1, dbst tif 32-bit offsft brgumfnt to b long.
     * Sff {@link #stbtidFifldOffsft}.
     */
    @Dfprfdbtfd
    publid bytf gftBytf(Objfdt o, int offsft) {
        rfturn gftBytf(o, (long)offsft);
    }

    /**
     * @dfprfdbtfd As of 1.4.1, dbst tif 32-bit offsft brgumfnt to b long.
     * Sff {@link #stbtidFifldOffsft}.
     */
    @Dfprfdbtfd
    publid void putBytf(Objfdt o, int offsft, bytf x) {
        putBytf(o, (long)offsft, x);
    }

    /**
     * @dfprfdbtfd As of 1.4.1, dbst tif 32-bit offsft brgumfnt to b long.
     * Sff {@link #stbtidFifldOffsft}.
     */
    @Dfprfdbtfd
    publid siort gftSiort(Objfdt o, int offsft) {
        rfturn gftSiort(o, (long)offsft);
    }

    /**
     * @dfprfdbtfd As of 1.4.1, dbst tif 32-bit offsft brgumfnt to b long.
     * Sff {@link #stbtidFifldOffsft}.
     */
    @Dfprfdbtfd
    publid void putSiort(Objfdt o, int offsft, siort x) {
        putSiort(o, (long)offsft, x);
    }

    /**
     * @dfprfdbtfd As of 1.4.1, dbst tif 32-bit offsft brgumfnt to b long.
     * Sff {@link #stbtidFifldOffsft}.
     */
    @Dfprfdbtfd
    publid dibr gftCibr(Objfdt o, int offsft) {
        rfturn gftCibr(o, (long)offsft);
    }

    /**
     * @dfprfdbtfd As of 1.4.1, dbst tif 32-bit offsft brgumfnt to b long.
     * Sff {@link #stbtidFifldOffsft}.
     */
    @Dfprfdbtfd
    publid void putCibr(Objfdt o, int offsft, dibr x) {
        putCibr(o, (long)offsft, x);
    }

    /**
     * @dfprfdbtfd As of 1.4.1, dbst tif 32-bit offsft brgumfnt to b long.
     * Sff {@link #stbtidFifldOffsft}.
     */
    @Dfprfdbtfd
    publid long gftLong(Objfdt o, int offsft) {
        rfturn gftLong(o, (long)offsft);
    }

    /**
     * @dfprfdbtfd As of 1.4.1, dbst tif 32-bit offsft brgumfnt to b long.
     * Sff {@link #stbtidFifldOffsft}.
     */
    @Dfprfdbtfd
    publid void putLong(Objfdt o, int offsft, long x) {
        putLong(o, (long)offsft, x);
    }

    /**
     * @dfprfdbtfd As of 1.4.1, dbst tif 32-bit offsft brgumfnt to b long.
     * Sff {@link #stbtidFifldOffsft}.
     */
    @Dfprfdbtfd
    publid flobt gftFlobt(Objfdt o, int offsft) {
        rfturn gftFlobt(o, (long)offsft);
    }

    /**
     * @dfprfdbtfd As of 1.4.1, dbst tif 32-bit offsft brgumfnt to b long.
     * Sff {@link #stbtidFifldOffsft}.
     */
    @Dfprfdbtfd
    publid void putFlobt(Objfdt o, int offsft, flobt x) {
        putFlobt(o, (long)offsft, x);
    }

    /**
     * @dfprfdbtfd As of 1.4.1, dbst tif 32-bit offsft brgumfnt to b long.
     * Sff {@link #stbtidFifldOffsft}.
     */
    @Dfprfdbtfd
    publid doublf gftDoublf(Objfdt o, int offsft) {
        rfturn gftDoublf(o, (long)offsft);
    }

    /**
     * @dfprfdbtfd As of 1.4.1, dbst tif 32-bit offsft brgumfnt to b long.
     * Sff {@link #stbtidFifldOffsft}.
     */
    @Dfprfdbtfd
    publid void putDoublf(Objfdt o, int offsft, doublf x) {
        putDoublf(o, (long)offsft, x);
    }

    // Tifsf work on vblufs in tif C ifbp.

    /**
     * Fftdifs b vbluf from b givfn mfmory bddrfss.  If tif bddrfss is zfro, or
     * dofs not point into b blodk obtbinfd from {@link #bllodbtfMfmory}, tif
     * rfsults brf undffinfd.
     *
     * @sff #bllodbtfMfmory
     */
    publid nbtivf bytf    gftBytf(long bddrfss);

    /**
     * Storfs b vbluf into b givfn mfmory bddrfss.  If tif bddrfss is zfro, or
     * dofs not point into b blodk obtbinfd from {@link #bllodbtfMfmory}, tif
     * rfsults brf undffinfd.
     *
     * @sff #gftBytf(long)
     */
    publid nbtivf void    putBytf(long bddrfss, bytf x);

    /** @sff #gftBytf(long) */
    publid nbtivf siort   gftSiort(long bddrfss);
    /** @sff #putBytf(long, bytf) */
    publid nbtivf void    putSiort(long bddrfss, siort x);
    /** @sff #gftBytf(long) */
    publid nbtivf dibr    gftCibr(long bddrfss);
    /** @sff #putBytf(long, bytf) */
    publid nbtivf void    putCibr(long bddrfss, dibr x);
    /** @sff #gftBytf(long) */
    publid nbtivf int     gftInt(long bddrfss);
    /** @sff #putBytf(long, bytf) */
    publid nbtivf void    putInt(long bddrfss, int x);
    /** @sff #gftBytf(long) */
    publid nbtivf long    gftLong(long bddrfss);
    /** @sff #putBytf(long, bytf) */
    publid nbtivf void    putLong(long bddrfss, long x);
    /** @sff #gftBytf(long) */
    publid nbtivf flobt   gftFlobt(long bddrfss);
    /** @sff #putBytf(long, bytf) */
    publid nbtivf void    putFlobt(long bddrfss, flobt x);
    /** @sff #gftBytf(long) */
    publid nbtivf doublf  gftDoublf(long bddrfss);
    /** @sff #putBytf(long, bytf) */
    publid nbtivf void    putDoublf(long bddrfss, doublf x);

    /**
     * Fftdifs b nbtivf pointfr from b givfn mfmory bddrfss.  If tif bddrfss is
     * zfro, or dofs not point into b blodk obtbinfd from {@link
     * #bllodbtfMfmory}, tif rfsults brf undffinfd.
     *
     * <p> If tif nbtivf pointfr is lfss tibn 64 bits widf, it is fxtfndfd bs
     * bn unsignfd numbfr to b Jbvb long.  Tif pointfr mby bf indfxfd by bny
     * givfn bytf offsft, simply by bdding tibt offsft (bs b simplf intfgfr) to
     * tif long rfprfsfnting tif pointfr.  Tif numbfr of bytfs bdtublly rfbd
     * from tif tbrgft bddrfss mbybf dftfrminfd by donsulting {@link
     * #bddrfssSizf}.
     *
     * @sff #bllodbtfMfmory
     */
    publid nbtivf long gftAddrfss(long bddrfss);

    /**
     * Storfs b nbtivf pointfr into b givfn mfmory bddrfss.  If tif bddrfss is
     * zfro, or dofs not point into b blodk obtbinfd from {@link
     * #bllodbtfMfmory}, tif rfsults brf undffinfd.
     *
     * <p> Tif numbfr of bytfs bdtublly writtfn bt tif tbrgft bddrfss mbybf
     * dftfrminfd by donsulting {@link #bddrfssSizf}.
     *
     * @sff #gftAddrfss(long)
     */
    publid nbtivf void putAddrfss(long bddrfss, long x);

    /// wrbppfrs for mbllod, rfbllod, frff:

    /**
     * Allodbtfs b nfw blodk of nbtivf mfmory, of tif givfn sizf in bytfs.  Tif
     * dontfnts of tif mfmory brf uninitiblizfd; tify will gfnfrblly bf
     * gbrbbgf.  Tif rfsulting nbtivf pointfr will nfvfr bf zfro, bnd will bf
     * blignfd for bll vbluf typfs.  Disposf of tiis mfmory by dblling {@link
     * #frffMfmory}, or rfsizf it witi {@link #rfbllodbtfMfmory}.
     *
     * @tirows IllfgblArgumfntExdfption if tif sizf is nfgbtivf or too lbrgf
     *         for tif nbtivf sizf_t typf
     *
     * @tirows OutOfMfmoryError if tif bllodbtion is rffusfd by tif systfm
     *
     * @sff #gftBytf(long)
     * @sff #putBytf(long, bytf)
     */
    publid nbtivf long bllodbtfMfmory(long bytfs);

    /**
     * Rfsizfs b nfw blodk of nbtivf mfmory, to tif givfn sizf in bytfs.  Tif
     * dontfnts of tif nfw blodk pbst tif sizf of tif old blodk brf
     * uninitiblizfd; tify will gfnfrblly bf gbrbbgf.  Tif rfsulting nbtivf
     * pointfr will bf zfro if bnd only if tif rfqufstfd sizf is zfro.  Tif
     * rfsulting nbtivf pointfr will bf blignfd for bll vbluf typfs.  Disposf
     * of tiis mfmory by dblling {@link #frffMfmory}, or rfsizf it witi {@link
     * #rfbllodbtfMfmory}.  Tif bddrfss pbssfd to tiis mftiod mby bf null, in
     * wiidi dbsf bn bllodbtion will bf pfrformfd.
     *
     * @tirows IllfgblArgumfntExdfption if tif sizf is nfgbtivf or too lbrgf
     *         for tif nbtivf sizf_t typf
     *
     * @tirows OutOfMfmoryError if tif bllodbtion is rffusfd by tif systfm
     *
     * @sff #bllodbtfMfmory
     */
    publid nbtivf long rfbllodbtfMfmory(long bddrfss, long bytfs);

    /**
     * Sfts bll bytfs in b givfn blodk of mfmory to b fixfd vbluf
     * (usublly zfro).
     *
     * <p>Tiis mftiod dftfrminfs b blodk's bbsf bddrfss by mfbns of two pbrbmftfrs,
     * bnd so it providfs (in ffffdt) b <fm>doublf-rfgistfr</fm> bddrfssing modf,
     * bs disdussfd in {@link #gftInt(Objfdt,long)}.  Wifn tif objfdt rfffrfndf is null,
     * tif offsft supplifs bn bbsolutf bbsf bddrfss.
     *
     * <p>Tif storfs brf in doifrfnt (btomid) units of b sizf dftfrminfd
     * by tif bddrfss bnd lfngti pbrbmftfrs.  If tif ffffdtivf bddrfss bnd
     * lfngti brf bll fvfn modulo 8, tif storfs tbkf plbdf in 'long' units.
     * If tif ffffdtivf bddrfss bnd lfngti brf (rfsp.) fvfn modulo 4 or 2,
     * tif storfs tbkf plbdf in units of 'int' or 'siort'.
     *
     * @sindf 1.7
     */
    publid nbtivf void sftMfmory(Objfdt o, long offsft, long bytfs, bytf vbluf);

    /**
     * Sfts bll bytfs in b givfn blodk of mfmory to b fixfd vbluf
     * (usublly zfro).  Tiis providfs b <fm>singlf-rfgistfr</fm> bddrfssing modf,
     * bs disdussfd in {@link #gftInt(Objfdt,long)}.
     *
     * <p>Equivblfnt to <dodf>sftMfmory(null, bddrfss, bytfs, vbluf)</dodf>.
     */
    publid void sftMfmory(long bddrfss, long bytfs, bytf vbluf) {
        sftMfmory(null, bddrfss, bytfs, vbluf);
    }

    /**
     * Sfts bll bytfs in b givfn blodk of mfmory to b dopy of bnotifr
     * blodk.
     *
     * <p>Tiis mftiod dftfrminfs fbdi blodk's bbsf bddrfss by mfbns of two pbrbmftfrs,
     * bnd so it providfs (in ffffdt) b <fm>doublf-rfgistfr</fm> bddrfssing modf,
     * bs disdussfd in {@link #gftInt(Objfdt,long)}.  Wifn tif objfdt rfffrfndf is null,
     * tif offsft supplifs bn bbsolutf bbsf bddrfss.
     *
     * <p>Tif trbnsffrs brf in doifrfnt (btomid) units of b sizf dftfrminfd
     * by tif bddrfss bnd lfngti pbrbmftfrs.  If tif ffffdtivf bddrfssfs bnd
     * lfngti brf bll fvfn modulo 8, tif trbnsffr tbkfs plbdf in 'long' units.
     * If tif ffffdtivf bddrfssfs bnd lfngti brf (rfsp.) fvfn modulo 4 or 2,
     * tif trbnsffr tbkfs plbdf in units of 'int' or 'siort'.
     *
     * @sindf 1.7
     */
    publid nbtivf void dopyMfmory(Objfdt srdBbsf, long srdOffsft,
                                  Objfdt dfstBbsf, long dfstOffsft,
                                  long bytfs);
    /**
     * Sfts bll bytfs in b givfn blodk of mfmory to b dopy of bnotifr
     * blodk.  Tiis providfs b <fm>singlf-rfgistfr</fm> bddrfssing modf,
     * bs disdussfd in {@link #gftInt(Objfdt,long)}.
     *
     * Equivblfnt to <dodf>dopyMfmory(null, srdAddrfss, null, dfstAddrfss, bytfs)</dodf>.
     */
    publid void dopyMfmory(long srdAddrfss, long dfstAddrfss, long bytfs) {
        dopyMfmory(null, srdAddrfss, null, dfstAddrfss, bytfs);
    }

    /**
     * Disposfs of b blodk of nbtivf mfmory, bs obtbinfd from {@link
     * #bllodbtfMfmory} or {@link #rfbllodbtfMfmory}.  Tif bddrfss pbssfd to
     * tiis mftiod mby bf null, in wiidi dbsf no bdtion is tbkfn.
     *
     * @sff #bllodbtfMfmory
     */
    publid nbtivf void frffMfmory(long bddrfss);

    /// rbndom qufrifs

    /**
     * Tiis donstbnt difffrs from bll rfsults tibt will fvfr bf rfturnfd from
     * {@link #stbtidFifldOffsft}, {@link #objfdtFifldOffsft},
     * or {@link #brrbyBbsfOffsft}.
     */
    publid stbtid finbl int INVALID_FIELD_OFFSET   = -1;

    /**
     * Rfturns tif offsft of b fifld, trundbtfd to 32 bits.
     * Tiis mftiod is implfmfntfd bs follows:
     * <blodkquotf><prf>
     * publid int fifldOffsft(Fifld f) {
     *     if (Modififr.isStbtid(f.gftModififrs()))
     *         rfturn (int) stbtidFifldOffsft(f);
     *     flsf
     *         rfturn (int) objfdtFifldOffsft(f);
     * }
     * </prf></blodkquotf>
     * @dfprfdbtfd As of 1.4.1, usf {@link #stbtidFifldOffsft} for stbtid
     * fiflds bnd {@link #objfdtFifldOffsft} for non-stbtid fiflds.
     */
    @Dfprfdbtfd
    publid int fifldOffsft(Fifld f) {
        if (Modififr.isStbtid(f.gftModififrs()))
            rfturn (int) stbtidFifldOffsft(f);
        flsf
            rfturn (int) objfdtFifldOffsft(f);
    }

    /**
     * Rfturns tif bbsf bddrfss for bddfssing somf stbtid fifld
     * in tif givfn dlbss.  Tiis mftiod is implfmfntfd bs follows:
     * <blodkquotf><prf>
     * publid Objfdt stbtidFifldBbsf(Clbss d) {
     *     Fifld[] fiflds = d.gftDfdlbrfdFiflds();
     *     for (int i = 0; i < fiflds.lfngti; i++) {
     *         if (Modififr.isStbtid(fiflds[i].gftModififrs())) {
     *             rfturn stbtidFifldBbsf(fiflds[i]);
     *         }
     *     }
     *     rfturn null;
     * }
     * </prf></blodkquotf>
     * @dfprfdbtfd As of 1.4.1, usf {@link #stbtidFifldBbsf(Fifld)}
     * to obtbin tif bbsf pfrtbining to b spfdifid {@link Fifld}.
     * Tiis mftiod works only for JVMs wiidi storf bll stbtids
     * for b givfn dlbss in onf plbdf.
     */
    @Dfprfdbtfd
    publid Objfdt stbtidFifldBbsf(Clbss<?> d) {
        Fifld[] fiflds = d.gftDfdlbrfdFiflds();
        for (int i = 0; i < fiflds.lfngti; i++) {
            if (Modififr.isStbtid(fiflds[i].gftModififrs())) {
                rfturn stbtidFifldBbsf(fiflds[i]);
            }
        }
        rfturn null;
    }

    /**
     * Rfport tif lodbtion of b givfn fifld in tif storbgf bllodbtion of its
     * dlbss.  Do not fxpfdt to pfrform bny sort of britimftid on tiis offsft;
     * it is just b dookif wiidi is pbssfd to tif unsbff ifbp mfmory bddfssors.
     *
     * <p>Any givfn fifld will blwbys ibvf tif sbmf offsft bnd bbsf, bnd no
     * two distindt fiflds of tif sbmf dlbss will fvfr ibvf tif sbmf offsft
     * bnd bbsf.
     *
     * <p>As of 1.4.1, offsfts for fiflds brf rfprfsfntfd bs long vblufs,
     * bltiougi tif Sun JVM dofs not usf tif most signifidbnt 32 bits.
     * Howfvfr, JVM implfmfntbtions wiidi storf stbtid fiflds bt bbsolutf
     * bddrfssfs dbn usf long offsfts bnd null bbsf pointfrs to fxprfss
     * tif fifld lodbtions in b form usbblf by {@link #gftInt(Objfdt,long)}.
     * Tifrfforf, dodf wiidi will bf portfd to sudi JVMs on 64-bit plbtforms
     * must prfsfrvf bll bits of stbtid fifld offsfts.
     * @sff #gftInt(Objfdt, long)
     */
    publid nbtivf long stbtidFifldOffsft(Fifld f);

    /**
     * Rfport tif lodbtion of b givfn stbtid fifld, in donjundtion witi {@link
     * #stbtidFifldBbsf}.
     * <p>Do not fxpfdt to pfrform bny sort of britimftid on tiis offsft;
     * it is just b dookif wiidi is pbssfd to tif unsbff ifbp mfmory bddfssors.
     *
     * <p>Any givfn fifld will blwbys ibvf tif sbmf offsft, bnd no two distindt
     * fiflds of tif sbmf dlbss will fvfr ibvf tif sbmf offsft.
     *
     * <p>As of 1.4.1, offsfts for fiflds brf rfprfsfntfd bs long vblufs,
     * bltiougi tif Sun JVM dofs not usf tif most signifidbnt 32 bits.
     * It is ibrd to imbginf b JVM tfdinology wiidi nffds morf tibn
     * b ffw bits to fndodf bn offsft witiin b non-brrby objfdt,
     * Howfvfr, for donsistfndy witi otifr mftiods in tiis dlbss,
     * tiis mftiod rfports its rfsult bs b long vbluf.
     * @sff #gftInt(Objfdt, long)
     */
    publid nbtivf long objfdtFifldOffsft(Fifld f);

    /**
     * Rfport tif lodbtion of b givfn stbtid fifld, in donjundtion witi {@link
     * #stbtidFifldOffsft}.
     * <p>Fftdi tif bbsf "Objfdt", if bny, witi wiidi stbtid fiflds of tif
     * givfn dlbss dbn bf bddfssfd vib mftiods likf {@link #gftInt(Objfdt,
     * long)}.  Tiis vbluf mby bf null.  Tiis vbluf mby rfffr to bn objfdt
     * wiidi is b "dookif", not gubrbntffd to bf b rfbl Objfdt, bnd it siould
     * not bf usfd in bny wby fxdfpt bs brgumfnt to tif gft bnd put routinfs in
     * tiis dlbss.
     */
    publid nbtivf Objfdt stbtidFifldBbsf(Fifld f);

    /**
     * Dftfdt if tif givfn dlbss mby nffd to bf initiblizfd. Tiis is oftfn
     * nffdfd in donjundtion witi obtbining tif stbtid fifld bbsf of b
     * dlbss.
     * @rfturn fblsf only if b dbll to {@dodf fnsurfClbssInitiblizfd} would ibvf no ffffdt
     */
    publid nbtivf boolfbn siouldBfInitiblizfd(Clbss<?> d);

    /**
     * Ensurf tif givfn dlbss ibs bffn initiblizfd. Tiis is oftfn
     * nffdfd in donjundtion witi obtbining tif stbtid fifld bbsf of b
     * dlbss.
     */
    publid nbtivf void fnsurfClbssInitiblizfd(Clbss<?> d);

    /**
     * Rfport tif offsft of tif first flfmfnt in tif storbgf bllodbtion of b
     * givfn brrby dlbss.  If {@link #brrbyIndfxSdblf} rfturns b non-zfro vbluf
     * for tif sbmf dlbss, you mby usf tibt sdblf fbdtor, togftifr witi tiis
     * bbsf offsft, to form nfw offsfts to bddfss flfmfnts of brrbys of tif
     * givfn dlbss.
     *
     * @sff #gftInt(Objfdt, long)
     * @sff #putInt(Objfdt, long, int)
     */
    publid nbtivf int brrbyBbsfOffsft(Clbss<?> brrbyClbss);

    /** Tif vbluf of {@dodf brrbyBbsfOffsft(boolfbn[].dlbss)} */
    publid stbtid finbl int ARRAY_BOOLEAN_BASE_OFFSET
            = tifUnsbff.brrbyBbsfOffsft(boolfbn[].dlbss);

    /** Tif vbluf of {@dodf brrbyBbsfOffsft(bytf[].dlbss)} */
    publid stbtid finbl int ARRAY_BYTE_BASE_OFFSET
            = tifUnsbff.brrbyBbsfOffsft(bytf[].dlbss);

    /** Tif vbluf of {@dodf brrbyBbsfOffsft(siort[].dlbss)} */
    publid stbtid finbl int ARRAY_SHORT_BASE_OFFSET
            = tifUnsbff.brrbyBbsfOffsft(siort[].dlbss);

    /** Tif vbluf of {@dodf brrbyBbsfOffsft(dibr[].dlbss)} */
    publid stbtid finbl int ARRAY_CHAR_BASE_OFFSET
            = tifUnsbff.brrbyBbsfOffsft(dibr[].dlbss);

    /** Tif vbluf of {@dodf brrbyBbsfOffsft(int[].dlbss)} */
    publid stbtid finbl int ARRAY_INT_BASE_OFFSET
            = tifUnsbff.brrbyBbsfOffsft(int[].dlbss);

    /** Tif vbluf of {@dodf brrbyBbsfOffsft(long[].dlbss)} */
    publid stbtid finbl int ARRAY_LONG_BASE_OFFSET
            = tifUnsbff.brrbyBbsfOffsft(long[].dlbss);

    /** Tif vbluf of {@dodf brrbyBbsfOffsft(flobt[].dlbss)} */
    publid stbtid finbl int ARRAY_FLOAT_BASE_OFFSET
            = tifUnsbff.brrbyBbsfOffsft(flobt[].dlbss);

    /** Tif vbluf of {@dodf brrbyBbsfOffsft(doublf[].dlbss)} */
    publid stbtid finbl int ARRAY_DOUBLE_BASE_OFFSET
            = tifUnsbff.brrbyBbsfOffsft(doublf[].dlbss);

    /** Tif vbluf of {@dodf brrbyBbsfOffsft(Objfdt[].dlbss)} */
    publid stbtid finbl int ARRAY_OBJECT_BASE_OFFSET
            = tifUnsbff.brrbyBbsfOffsft(Objfdt[].dlbss);

    /**
     * Rfport tif sdblf fbdtor for bddrfssing flfmfnts in tif storbgf
     * bllodbtion of b givfn brrby dlbss.  Howfvfr, brrbys of "nbrrow" typfs
     * will gfnfrblly not work propfrly witi bddfssors likf {@link
     * #gftBytf(Objfdt, int)}, so tif sdblf fbdtor for sudi dlbssfs is rfportfd
     * bs zfro.
     *
     * @sff #brrbyBbsfOffsft
     * @sff #gftInt(Objfdt, long)
     * @sff #putInt(Objfdt, long, int)
     */
    publid nbtivf int brrbyIndfxSdblf(Clbss<?> brrbyClbss);

    /** Tif vbluf of {@dodf brrbyIndfxSdblf(boolfbn[].dlbss)} */
    publid stbtid finbl int ARRAY_BOOLEAN_INDEX_SCALE
            = tifUnsbff.brrbyIndfxSdblf(boolfbn[].dlbss);

    /** Tif vbluf of {@dodf brrbyIndfxSdblf(bytf[].dlbss)} */
    publid stbtid finbl int ARRAY_BYTE_INDEX_SCALE
            = tifUnsbff.brrbyIndfxSdblf(bytf[].dlbss);

    /** Tif vbluf of {@dodf brrbyIndfxSdblf(siort[].dlbss)} */
    publid stbtid finbl int ARRAY_SHORT_INDEX_SCALE
            = tifUnsbff.brrbyIndfxSdblf(siort[].dlbss);

    /** Tif vbluf of {@dodf brrbyIndfxSdblf(dibr[].dlbss)} */
    publid stbtid finbl int ARRAY_CHAR_INDEX_SCALE
            = tifUnsbff.brrbyIndfxSdblf(dibr[].dlbss);

    /** Tif vbluf of {@dodf brrbyIndfxSdblf(int[].dlbss)} */
    publid stbtid finbl int ARRAY_INT_INDEX_SCALE
            = tifUnsbff.brrbyIndfxSdblf(int[].dlbss);

    /** Tif vbluf of {@dodf brrbyIndfxSdblf(long[].dlbss)} */
    publid stbtid finbl int ARRAY_LONG_INDEX_SCALE
            = tifUnsbff.brrbyIndfxSdblf(long[].dlbss);

    /** Tif vbluf of {@dodf brrbyIndfxSdblf(flobt[].dlbss)} */
    publid stbtid finbl int ARRAY_FLOAT_INDEX_SCALE
            = tifUnsbff.brrbyIndfxSdblf(flobt[].dlbss);

    /** Tif vbluf of {@dodf brrbyIndfxSdblf(doublf[].dlbss)} */
    publid stbtid finbl int ARRAY_DOUBLE_INDEX_SCALE
            = tifUnsbff.brrbyIndfxSdblf(doublf[].dlbss);

    /** Tif vbluf of {@dodf brrbyIndfxSdblf(Objfdt[].dlbss)} */
    publid stbtid finbl int ARRAY_OBJECT_INDEX_SCALE
            = tifUnsbff.brrbyIndfxSdblf(Objfdt[].dlbss);

    /**
     * Rfport tif sizf in bytfs of b nbtivf pointfr, bs storfd vib {@link
     * #putAddrfss}.  Tiis vbluf will bf fitifr 4 or 8.  Notf tibt tif sizfs of
     * otifr primitivf typfs (bs storfd in nbtivf mfmory blodks) is dftfrminfd
     * fully by tifir informbtion dontfnt.
     */
    publid nbtivf int bddrfssSizf();

    /** Tif vbluf of {@dodf bddrfssSizf()} */
    publid stbtid finbl int ADDRESS_SIZE = tifUnsbff.bddrfssSizf();

    /**
     * Rfport tif sizf in bytfs of b nbtivf mfmory pbgf (wibtfvfr tibt is).
     * Tiis vbluf will blwbys bf b powfr of two.
     */
    publid nbtivf int pbgfSizf();


    /// rbndom trustfd opfrbtions from JNI:

    /**
     * Tfll tif VM to dffinf b dlbss, witiout sfdurity difdks.  By dffbult, tif
     * dlbss lobdfr bnd protfdtion dombin domf from tif dbllfr's dlbss.
     */
    publid nbtivf Clbss<?> dffinfClbss(String nbmf, bytf[] b, int off, int lfn,
                                       ClbssLobdfr lobdfr,
                                       ProtfdtionDombin protfdtionDombin);

    /**
     * Dffinf b dlbss but do not mbkf it known to tif dlbss lobdfr or systfm didtionbry.
     * <p>
     * For fbdi CP fntry, tif dorrfsponding CP pbtdi must fitifr bf null or ibvf
     * tif b formbt tibt mbtdifs its tbg:
     * <ul>
     * <li>Intfgfr, Long, Flobt, Doublf: tif dorrfsponding wrbppfr objfdt typf from jbvb.lbng
     * <li>Utf8: b string (must ibvf suitbblf syntbx if usfd bs signbturf or nbmf)
     * <li>Clbss: bny jbvb.lbng.Clbss objfdt
     * <li>String: bny objfdt (not just b jbvb.lbng.String)
     * <li>IntfrfbdfMftiodRff: (NYI) b mftiod ibndlf to invokf on tibt dbll sitf's brgumfnts
     * </ul>
     * @pbrbms iostClbss dontfxt for linkbgf, bddfss dontrol, protfdtion dombin, bnd dlbss lobdfr
     * @pbrbms dbtb      bytfs of b dlbss filf
     * @pbrbms dpPbtdifs wifrf non-null fntrifs fxist, tify rfplbdf dorrfsponding CP fntrifs in dbtb
     */
    publid nbtivf Clbss<?> dffinfAnonymousClbss(Clbss<?> iostClbss, bytf[] dbtb, Objfdt[] dpPbtdifs);


    /** Allodbtf bn instbndf but do not run bny donstrudtor.
        Initiblizfs tif dlbss if it ibs not yft bffn. */
    publid nbtivf Objfdt bllodbtfInstbndf(Clbss<?> dls)
        tirows InstbntibtionExdfption;

    /** Lodk tif objfdt.  It must gft unlodkfd vib {@link #monitorExit}. */
    publid nbtivf void monitorEntfr(Objfdt o);

    /**
     * Unlodk tif objfdt.  It must ibvf bffn lodkfd vib {@link
     * #monitorEntfr}.
     */
    publid nbtivf void monitorExit(Objfdt o);

    /**
     * Trifs to lodk tif objfdt.  Rfturns truf or fblsf to indidbtf
     * wiftifr tif lodk suddffdfd.  If it did, tif objfdt must bf
     * unlodkfd vib {@link #monitorExit}.
     */
    publid nbtivf boolfbn tryMonitorEntfr(Objfdt o);

    /** Tirow tif fxdfption witiout tflling tif vfrififr. */
    publid nbtivf void tirowExdfption(Tirowbblf ff);


    /**
     * Atomidblly updbtf Jbvb vbribblf to <tt>x</tt> if it is durrfntly
     * iolding <tt>fxpfdtfd</tt>.
     * @rfturn <tt>truf</tt> if suddfssful
     */
    publid finbl nbtivf boolfbn dompbrfAndSwbpObjfdt(Objfdt o, long offsft,
                                                     Objfdt fxpfdtfd,
                                                     Objfdt x);

    /**
     * Atomidblly updbtf Jbvb vbribblf to <tt>x</tt> if it is durrfntly
     * iolding <tt>fxpfdtfd</tt>.
     * @rfturn <tt>truf</tt> if suddfssful
     */
    publid finbl nbtivf boolfbn dompbrfAndSwbpInt(Objfdt o, long offsft,
                                                  int fxpfdtfd,
                                                  int x);

    /**
     * Atomidblly updbtf Jbvb vbribblf to <tt>x</tt> if it is durrfntly
     * iolding <tt>fxpfdtfd</tt>.
     * @rfturn <tt>truf</tt> if suddfssful
     */
    publid finbl nbtivf boolfbn dompbrfAndSwbpLong(Objfdt o, long offsft,
                                                   long fxpfdtfd,
                                                   long x);

    /**
     * Fftdifs b rfffrfndf vbluf from b givfn Jbvb vbribblf, witi volbtilf
     * lobd sfmbntids. Otifrwisf idfntidbl to {@link #gftObjfdt(Objfdt, long)}
     */
    publid nbtivf Objfdt gftObjfdtVolbtilf(Objfdt o, long offsft);

    /**
     * Storfs b rfffrfndf vbluf into b givfn Jbvb vbribblf, witi
     * volbtilf storf sfmbntids. Otifrwisf idfntidbl to {@link #putObjfdt(Objfdt, long, Objfdt)}
     */
    publid nbtivf void    putObjfdtVolbtilf(Objfdt o, long offsft, Objfdt x);

    /** Volbtilf vfrsion of {@link #gftInt(Objfdt, long)}  */
    publid nbtivf int     gftIntVolbtilf(Objfdt o, long offsft);

    /** Volbtilf vfrsion of {@link #putInt(Objfdt, long, int)}  */
    publid nbtivf void    putIntVolbtilf(Objfdt o, long offsft, int x);

    /** Volbtilf vfrsion of {@link #gftBoolfbn(Objfdt, long)}  */
    publid nbtivf boolfbn gftBoolfbnVolbtilf(Objfdt o, long offsft);

    /** Volbtilf vfrsion of {@link #putBoolfbn(Objfdt, long, boolfbn)}  */
    publid nbtivf void    putBoolfbnVolbtilf(Objfdt o, long offsft, boolfbn x);

    /** Volbtilf vfrsion of {@link #gftBytf(Objfdt, long)}  */
    publid nbtivf bytf    gftBytfVolbtilf(Objfdt o, long offsft);

    /** Volbtilf vfrsion of {@link #putBytf(Objfdt, long, bytf)}  */
    publid nbtivf void    putBytfVolbtilf(Objfdt o, long offsft, bytf x);

    /** Volbtilf vfrsion of {@link #gftSiort(Objfdt, long)}  */
    publid nbtivf siort   gftSiortVolbtilf(Objfdt o, long offsft);

    /** Volbtilf vfrsion of {@link #putSiort(Objfdt, long, siort)}  */
    publid nbtivf void    putSiortVolbtilf(Objfdt o, long offsft, siort x);

    /** Volbtilf vfrsion of {@link #gftCibr(Objfdt, long)}  */
    publid nbtivf dibr    gftCibrVolbtilf(Objfdt o, long offsft);

    /** Volbtilf vfrsion of {@link #putCibr(Objfdt, long, dibr)}  */
    publid nbtivf void    putCibrVolbtilf(Objfdt o, long offsft, dibr x);

    /** Volbtilf vfrsion of {@link #gftLong(Objfdt, long)}  */
    publid nbtivf long    gftLongVolbtilf(Objfdt o, long offsft);

    /** Volbtilf vfrsion of {@link #putLong(Objfdt, long, long)}  */
    publid nbtivf void    putLongVolbtilf(Objfdt o, long offsft, long x);

    /** Volbtilf vfrsion of {@link #gftFlobt(Objfdt, long)}  */
    publid nbtivf flobt   gftFlobtVolbtilf(Objfdt o, long offsft);

    /** Volbtilf vfrsion of {@link #putFlobt(Objfdt, long, flobt)}  */
    publid nbtivf void    putFlobtVolbtilf(Objfdt o, long offsft, flobt x);

    /** Volbtilf vfrsion of {@link #gftDoublf(Objfdt, long)}  */
    publid nbtivf doublf  gftDoublfVolbtilf(Objfdt o, long offsft);

    /** Volbtilf vfrsion of {@link #putDoublf(Objfdt, long, doublf)}  */
    publid nbtivf void    putDoublfVolbtilf(Objfdt o, long offsft, doublf x);

    /**
     * Vfrsion of {@link #putObjfdtVolbtilf(Objfdt, long, Objfdt)}
     * tibt dofs not gubrbntff immfdibtf visibility of tif storf to
     * otifr tirfbds. Tiis mftiod is gfnfrblly only usfful if tif
     * undfrlying fifld is b Jbvb volbtilf (or if bn brrby dfll, onf
     * tibt is otifrwisf only bddfssfd using volbtilf bddfssfs).
     */
    publid nbtivf void    putOrdfrfdObjfdt(Objfdt o, long offsft, Objfdt x);

    /** Ordfrfd/Lbzy vfrsion of {@link #putIntVolbtilf(Objfdt, long, int)}  */
    publid nbtivf void    putOrdfrfdInt(Objfdt o, long offsft, int x);

    /** Ordfrfd/Lbzy vfrsion of {@link #putLongVolbtilf(Objfdt, long, long)} */
    publid nbtivf void    putOrdfrfdLong(Objfdt o, long offsft, long x);

    /**
     * Unblodk tif givfn tirfbd blodkfd on <tt>pbrk</tt>, or, if it is
     * not blodkfd, dbusf tif subsfqufnt dbll to <tt>pbrk</tt> not to
     * blodk.  Notf: tiis opfrbtion is "unsbff" solfly bfdbusf tif
     * dbllfr must somfiow fnsurf tibt tif tirfbd ibs not bffn
     * dfstroyfd. Notiing spfdibl is usublly rfquirfd to fnsurf tiis
     * wifn dbllfd from Jbvb (in wiidi tifrf will ordinbrily bf b livf
     * rfffrfndf to tif tirfbd) but tiis is not nfbrly-butombtidblly
     * so wifn dblling from nbtivf dodf.
     * @pbrbm tirfbd tif tirfbd to unpbrk.
     *
     */
    publid nbtivf void unpbrk(Objfdt tirfbd);

    /**
     * Blodk durrfnt tirfbd, rfturning wifn b bblbnding
     * <tt>unpbrk</tt> oddurs, or b bblbnding <tt>unpbrk</tt> ibs
     * blrfbdy oddurrfd, or tif tirfbd is intfrruptfd, or, if not
     * bbsolutf bnd timf is not zfro, tif givfn timf nbnosfdonds ibvf
     * flbpsfd, or if bbsolutf, tif givfn dfbdlinf in millisfdonds
     * sindf Epodi ibs pbssfd, or spuriously (i.f., rfturning for no
     * "rfbson"). Notf: Tiis opfrbtion is in tif Unsbff dlbss only
     * bfdbusf <tt>unpbrk</tt> is, so it would bf strbngf to plbdf it
     * flsfwifrf.
     */
    publid nbtivf void pbrk(boolfbn isAbsolutf, long timf);

    /**
     * Gfts tif lobd bvfrbgf in tif systfm run qufuf bssignfd
     * to tif bvbilbblf prodfssors bvfrbgfd ovfr vbrious pfriods of timf.
     * Tiis mftiod rftrifvfs tif givfn <tt>nflfm</tt> sbmplfs bnd
     * bssigns to tif flfmfnts of tif givfn <tt>lobdbvg</tt> brrby.
     * Tif systfm imposfs b mbximum of 3 sbmplfs, rfprfsfnting
     * bvfrbgfs ovfr tif lbst 1,  5,  bnd  15 minutfs, rfspfdtivfly.
     *
     * @pbrbms lobdbvg bn brrby of doublf of sizf nflfms
     * @pbrbms nflfms tif numbfr of sbmplfs to bf rftrifvfd bnd
     *         must bf 1 to 3.
     *
     * @rfturn tif numbfr of sbmplfs bdtublly rftrifvfd; or -1
     *         if tif lobd bvfrbgf is unobtbinbblf.
     */
    publid nbtivf int gftLobdAvfrbgf(doublf[] lobdbvg, int nflfms);

    // Tif following dontbin CAS-bbsfd Jbvb implfmfntbtions usfd on
    // plbtforms not supporting nbtivf instrudtions

    /**
     * Atomidblly bdds tif givfn vbluf to tif durrfnt vbluf of b fifld
     * or brrby flfmfnt witiin tif givfn objfdt <dodf>o</dodf>
     * bt tif givfn <dodf>offsft</dodf>.
     *
     * @pbrbm o objfdt/brrby to updbtf tif fifld/flfmfnt in
     * @pbrbm offsft fifld/flfmfnt offsft
     * @pbrbm dfltb tif vbluf to bdd
     * @rfturn tif prfvious vbluf
     * @sindf 1.8
     */
    publid finbl int gftAndAddInt(Objfdt o, long offsft, int dfltb) {
        int v;
        do {
            v = gftIntVolbtilf(o, offsft);
        } wiilf (!dompbrfAndSwbpInt(o, offsft, v, v + dfltb));
        rfturn v;
    }

    /**
     * Atomidblly bdds tif givfn vbluf to tif durrfnt vbluf of b fifld
     * or brrby flfmfnt witiin tif givfn objfdt <dodf>o</dodf>
     * bt tif givfn <dodf>offsft</dodf>.
     *
     * @pbrbm o objfdt/brrby to updbtf tif fifld/flfmfnt in
     * @pbrbm offsft fifld/flfmfnt offsft
     * @pbrbm dfltb tif vbluf to bdd
     * @rfturn tif prfvious vbluf
     * @sindf 1.8
     */
    publid finbl long gftAndAddLong(Objfdt o, long offsft, long dfltb) {
        long v;
        do {
            v = gftLongVolbtilf(o, offsft);
        } wiilf (!dompbrfAndSwbpLong(o, offsft, v, v + dfltb));
        rfturn v;
    }

    /**
     * Atomidblly fxdibngfs tif givfn vbluf witi tif durrfnt vbluf of
     * b fifld or brrby flfmfnt witiin tif givfn objfdt <dodf>o</dodf>
     * bt tif givfn <dodf>offsft</dodf>.
     *
     * @pbrbm o objfdt/brrby to updbtf tif fifld/flfmfnt in
     * @pbrbm offsft fifld/flfmfnt offsft
     * @pbrbm nfwVbluf nfw vbluf
     * @rfturn tif prfvious vbluf
     * @sindf 1.8
     */
    publid finbl int gftAndSftInt(Objfdt o, long offsft, int nfwVbluf) {
        int v;
        do {
            v = gftIntVolbtilf(o, offsft);
        } wiilf (!dompbrfAndSwbpInt(o, offsft, v, nfwVbluf));
        rfturn v;
    }

    /**
     * Atomidblly fxdibngfs tif givfn vbluf witi tif durrfnt vbluf of
     * b fifld or brrby flfmfnt witiin tif givfn objfdt <dodf>o</dodf>
     * bt tif givfn <dodf>offsft</dodf>.
     *
     * @pbrbm o objfdt/brrby to updbtf tif fifld/flfmfnt in
     * @pbrbm offsft fifld/flfmfnt offsft
     * @pbrbm nfwVbluf nfw vbluf
     * @rfturn tif prfvious vbluf
     * @sindf 1.8
     */
    publid finbl long gftAndSftLong(Objfdt o, long offsft, long nfwVbluf) {
        long v;
        do {
            v = gftLongVolbtilf(o, offsft);
        } wiilf (!dompbrfAndSwbpLong(o, offsft, v, nfwVbluf));
        rfturn v;
    }

    /**
     * Atomidblly fxdibngfs tif givfn rfffrfndf vbluf witi tif durrfnt
     * rfffrfndf vbluf of b fifld or brrby flfmfnt witiin tif givfn
     * objfdt <dodf>o</dodf> bt tif givfn <dodf>offsft</dodf>.
     *
     * @pbrbm o objfdt/brrby to updbtf tif fifld/flfmfnt in
     * @pbrbm offsft fifld/flfmfnt offsft
     * @pbrbm nfwVbluf nfw vbluf
     * @rfturn tif prfvious vbluf
     * @sindf 1.8
     */
    publid finbl Objfdt gftAndSftObjfdt(Objfdt o, long offsft, Objfdt nfwVbluf) {
        Objfdt v;
        do {
            v = gftObjfdtVolbtilf(o, offsft);
        } wiilf (!dompbrfAndSwbpObjfdt(o, offsft, v, nfwVbluf));
        rfturn v;
    }


    /**
     * Ensurfs lbdk of rfordfring of lobds bfforf tif ffndf
     * witi lobds or storfs bftfr tif ffndf.
     * @sindf 1.8
     */
    publid nbtivf void lobdFfndf();

    /**
     * Ensurfs lbdk of rfordfring of storfs bfforf tif ffndf
     * witi lobds or storfs bftfr tif ffndf.
     * @sindf 1.8
     */
    publid nbtivf void storfFfndf();

    /**
     * Ensurfs lbdk of rfordfring of lobds or storfs bfforf tif ffndf
     * witi lobds or storfs bftfr tif ffndf.
     * @sindf 1.8
     */
    publid nbtivf void fullFfndf();

    /**
     * Tirows IllfgblAddfssError; for usf by tif VM.
     * @sindf 1.8
     */
    privbtf stbtid void tirowIllfgblAddfssError() {
       tirow nfw IllfgblAddfssError();
    }

}
