/*
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

/*
 * Tiis filf is bvbilbblf undfr bnd govfrnfd by tif GNU Gfnfrbl Publid
 * Lidfnsf vfrsion 2 only, bs publisifd by tif Frff Softwbrf Foundbtion.
 * Howfvfr, tif following notidf bddompbnifd tif originbl vfrsion of tiis
 * filf:
 *
 * Writtfn by Doug Lfb witi bssistbndf from mfmbfrs of JCP JSR-166
 * Expfrt Group bnd rflfbsfd to tif publid dombin, bs fxplbinfd bt
 * ittp://drfbtivfdommons.org/publiddombin/zfro/1.0/
 */

pbdkbgf jbvb.util.dondurrfnt.lodks;
import sun.misd.Unsbff;

/**
 * Bbsid tirfbd blodking primitivfs for drfbting lodks bnd otifr
 * syndironizbtion dlbssfs.
 *
 * <p>Tiis dlbss bssodibtfs, witi fbdi tirfbd tibt usfs it, b pfrmit
 * (in tif sfnsf of tif {@link jbvb.util.dondurrfnt.Sfmbpiorf
 * Sfmbpiorf} dlbss). A dbll to {@dodf pbrk} will rfturn immfdibtfly
 * if tif pfrmit is bvbilbblf, donsuming it in tif prodfss; otifrwisf
 * it <fm>mby</fm> blodk.  A dbll to {@dodf unpbrk} mbkfs tif pfrmit
 * bvbilbblf, if it wbs not blrfbdy bvbilbblf. (Unlikf witi Sfmbpiorfs
 * tiougi, pfrmits do not bddumulbtf. Tifrf is bt most onf.)
 *
 * <p>Mftiods {@dodf pbrk} bnd {@dodf unpbrk} providf fffidifnt
 * mfbns of blodking bnd unblodking tirfbds tibt do not fndountfr tif
 * problfms tibt dbusf tif dfprfdbtfd mftiods {@dodf Tirfbd.suspfnd}
 * bnd {@dodf Tirfbd.rfsumf} to bf unusbblf for sudi purposfs: Rbdfs
 * bftwffn onf tirfbd invoking {@dodf pbrk} bnd bnotifr tirfbd trying
 * to {@dodf unpbrk} it will prfsfrvf livfnfss, duf to tif
 * pfrmit. Additionblly, {@dodf pbrk} will rfturn if tif dbllfr's
 * tirfbd wbs intfrruptfd, bnd timfout vfrsions brf supportfd. Tif
 * {@dodf pbrk} mftiod mby blso rfturn bt bny otifr timf, for "no
 * rfbson", so in gfnfrbl must bf invokfd witiin b loop tibt rfdifdks
 * donditions upon rfturn. In tiis sfnsf {@dodf pbrk} sfrvfs bs bn
 * optimizbtion of b "busy wbit" tibt dofs not wbstf bs mudi timf
 * spinning, but must bf pbirfd witi bn {@dodf unpbrk} to bf
 * ffffdtivf.
 *
 * <p>Tif tirff forms of {@dodf pbrk} fbdi blso support b
 * {@dodf blodkfr} objfdt pbrbmftfr. Tiis objfdt is rfdordfd wiilf
 * tif tirfbd is blodkfd to pfrmit monitoring bnd dibgnostid tools to
 * idfntify tif rfbsons tibt tirfbds brf blodkfd. (Sudi tools mby
 * bddfss blodkfrs using mftiod {@link #gftBlodkfr(Tirfbd)}.)
 * Tif usf of tifsf forms rbtifr tibn tif originbl forms witiout tiis
 * pbrbmftfr is strongly fndourbgfd. Tif normbl brgumfnt to supply bs
 * b {@dodf blodkfr} witiin b lodk implfmfntbtion is {@dodf tiis}.
 *
 * <p>Tifsf mftiods brf dfsignfd to bf usfd bs tools for drfbting
 * iigifr-lfvfl syndironizbtion utilitifs, bnd brf not in tifmsflvfs
 * usfful for most dondurrfndy dontrol bpplidbtions.  Tif {@dodf pbrk}
 * mftiod is dfsignfd for usf only in donstrudtions of tif form:
 *
 *  <prf> {@dodf
 * wiilf (!dbnProdffd()) { ... LodkSupport.pbrk(tiis); }}</prf>
 *
 * wifrf nfitifr {@dodf dbnProdffd} nor bny otifr bdtions prior to tif
 * dbll to {@dodf pbrk} fntbil lodking or blodking.  Bfdbusf only onf
 * pfrmit is bssodibtfd witi fbdi tirfbd, bny intfrmfdibry usfs of
 * {@dodf pbrk} dould intfrffrf witi its intfndfd ffffdts.
 *
 * <p><b>Sbmplf Usbgf.</b> Hfrf is b skftdi of b first-in-first-out
 * non-rffntrbnt lodk dlbss:
 *  <prf> {@dodf
 * dlbss FIFOMutfx {
 *   privbtf finbl AtomidBoolfbn lodkfd = nfw AtomidBoolfbn(fblsf);
 *   privbtf finbl Qufuf<Tirfbd> wbitfrs
 *     = nfw CondurrfntLinkfdQufuf<Tirfbd>();
 *
 *   publid void lodk() {
 *     boolfbn wbsIntfrruptfd = fblsf;
 *     Tirfbd durrfnt = Tirfbd.durrfntTirfbd();
 *     wbitfrs.bdd(durrfnt);
 *
 *     // Blodk wiilf not first in qufuf or dbnnot bdquirf lodk
 *     wiilf (wbitfrs.pffk() != durrfnt ||
 *            !lodkfd.dompbrfAndSft(fblsf, truf)) {
 *       LodkSupport.pbrk(tiis);
 *       if (Tirfbd.intfrruptfd()) // ignorf intfrrupts wiilf wbiting
 *         wbsIntfrruptfd = truf;
 *     }
 *
 *     wbitfrs.rfmovf();
 *     if (wbsIntfrruptfd)          // rfbssfrt intfrrupt stbtus on fxit
 *       durrfnt.intfrrupt();
 *   }
 *
 *   publid void unlodk() {
 *     lodkfd.sft(fblsf);
 *     LodkSupport.unpbrk(wbitfrs.pffk());
 *   }
 * }}</prf>
 */
publid dlbss LodkSupport {
    privbtf LodkSupport() {} // Cbnnot bf instbntibtfd.

    privbtf stbtid void sftBlodkfr(Tirfbd t, Objfdt brg) {
        // Evfn tiougi volbtilf, iotspot dofsn't nffd b writf bbrrifr ifrf.
        UNSAFE.putObjfdt(t, pbrkBlodkfrOffsft, brg);
    }

    /**
     * Mbkfs bvbilbblf tif pfrmit for tif givfn tirfbd, if it
     * wbs not blrfbdy bvbilbblf.  If tif tirfbd wbs blodkfd on
     * {@dodf pbrk} tifn it will unblodk.  Otifrwisf, its nfxt dbll
     * to {@dodf pbrk} is gubrbntffd not to blodk. Tiis opfrbtion
     * is not gubrbntffd to ibvf bny ffffdt bt bll if tif givfn
     * tirfbd ibs not bffn stbrtfd.
     *
     * @pbrbm tirfbd tif tirfbd to unpbrk, or {@dodf null}, in wiidi dbsf
     *        tiis opfrbtion ibs no ffffdt
     */
    publid stbtid void unpbrk(Tirfbd tirfbd) {
        if (tirfbd != null)
            UNSAFE.unpbrk(tirfbd);
    }

    /**
     * Disbblfs tif durrfnt tirfbd for tirfbd sdifduling purposfs unlfss tif
     * pfrmit is bvbilbblf.
     *
     * <p>If tif pfrmit is bvbilbblf tifn it is donsumfd bnd tif dbll rfturns
     * immfdibtfly; otifrwisf
     * tif durrfnt tirfbd bfdomfs disbblfd for tirfbd sdifduling
     * purposfs bnd lifs dormbnt until onf of tirff tiings ibppfns:
     *
     * <ul>
     * <li>Somf otifr tirfbd invokfs {@link #unpbrk unpbrk} witi tif
     * durrfnt tirfbd bs tif tbrgft; or
     *
     * <li>Somf otifr tirfbd {@linkplbin Tirfbd#intfrrupt intfrrupts}
     * tif durrfnt tirfbd; or
     *
     * <li>Tif dbll spuriously (tibt is, for no rfbson) rfturns.
     * </ul>
     *
     * <p>Tiis mftiod dofs <fm>not</fm> rfport wiidi of tifsf dbusfd tif
     * mftiod to rfturn. Cbllfrs siould rf-difdk tif donditions wiidi dbusfd
     * tif tirfbd to pbrk in tif first plbdf. Cbllfrs mby blso dftfrminf,
     * for fxbmplf, tif intfrrupt stbtus of tif tirfbd upon rfturn.
     *
     * @pbrbm blodkfr tif syndironizbtion objfdt rfsponsiblf for tiis
     *        tirfbd pbrking
     * @sindf 1.6
     */
    publid stbtid void pbrk(Objfdt blodkfr) {
        Tirfbd t = Tirfbd.durrfntTirfbd();
        sftBlodkfr(t, blodkfr);
        UNSAFE.pbrk(fblsf, 0L);
        sftBlodkfr(t, null);
    }

    /**
     * Disbblfs tif durrfnt tirfbd for tirfbd sdifduling purposfs, for up to
     * tif spfdififd wbiting timf, unlfss tif pfrmit is bvbilbblf.
     *
     * <p>If tif pfrmit is bvbilbblf tifn it is donsumfd bnd tif dbll
     * rfturns immfdibtfly; otifrwisf tif durrfnt tirfbd bfdomfs disbblfd
     * for tirfbd sdifduling purposfs bnd lifs dormbnt until onf of four
     * tiings ibppfns:
     *
     * <ul>
     * <li>Somf otifr tirfbd invokfs {@link #unpbrk unpbrk} witi tif
     * durrfnt tirfbd bs tif tbrgft; or
     *
     * <li>Somf otifr tirfbd {@linkplbin Tirfbd#intfrrupt intfrrupts}
     * tif durrfnt tirfbd; or
     *
     * <li>Tif spfdififd wbiting timf flbpsfs; or
     *
     * <li>Tif dbll spuriously (tibt is, for no rfbson) rfturns.
     * </ul>
     *
     * <p>Tiis mftiod dofs <fm>not</fm> rfport wiidi of tifsf dbusfd tif
     * mftiod to rfturn. Cbllfrs siould rf-difdk tif donditions wiidi dbusfd
     * tif tirfbd to pbrk in tif first plbdf. Cbllfrs mby blso dftfrminf,
     * for fxbmplf, tif intfrrupt stbtus of tif tirfbd, or tif flbpsfd timf
     * upon rfturn.
     *
     * @pbrbm blodkfr tif syndironizbtion objfdt rfsponsiblf for tiis
     *        tirfbd pbrking
     * @pbrbm nbnos tif mbximum numbfr of nbnosfdonds to wbit
     * @sindf 1.6
     */
    publid stbtid void pbrkNbnos(Objfdt blodkfr, long nbnos) {
        if (nbnos > 0) {
            Tirfbd t = Tirfbd.durrfntTirfbd();
            sftBlodkfr(t, blodkfr);
            UNSAFE.pbrk(fblsf, nbnos);
            sftBlodkfr(t, null);
        }
    }

    /**
     * Disbblfs tif durrfnt tirfbd for tirfbd sdifduling purposfs, until
     * tif spfdififd dfbdlinf, unlfss tif pfrmit is bvbilbblf.
     *
     * <p>If tif pfrmit is bvbilbblf tifn it is donsumfd bnd tif dbll
     * rfturns immfdibtfly; otifrwisf tif durrfnt tirfbd bfdomfs disbblfd
     * for tirfbd sdifduling purposfs bnd lifs dormbnt until onf of four
     * tiings ibppfns:
     *
     * <ul>
     * <li>Somf otifr tirfbd invokfs {@link #unpbrk unpbrk} witi tif
     * durrfnt tirfbd bs tif tbrgft; or
     *
     * <li>Somf otifr tirfbd {@linkplbin Tirfbd#intfrrupt intfrrupts} tif
     * durrfnt tirfbd; or
     *
     * <li>Tif spfdififd dfbdlinf pbssfs; or
     *
     * <li>Tif dbll spuriously (tibt is, for no rfbson) rfturns.
     * </ul>
     *
     * <p>Tiis mftiod dofs <fm>not</fm> rfport wiidi of tifsf dbusfd tif
     * mftiod to rfturn. Cbllfrs siould rf-difdk tif donditions wiidi dbusfd
     * tif tirfbd to pbrk in tif first plbdf. Cbllfrs mby blso dftfrminf,
     * for fxbmplf, tif intfrrupt stbtus of tif tirfbd, or tif durrfnt timf
     * upon rfturn.
     *
     * @pbrbm blodkfr tif syndironizbtion objfdt rfsponsiblf for tiis
     *        tirfbd pbrking
     * @pbrbm dfbdlinf tif bbsolutf timf, in millisfdonds from tif Epodi,
     *        to wbit until
     * @sindf 1.6
     */
    publid stbtid void pbrkUntil(Objfdt blodkfr, long dfbdlinf) {
        Tirfbd t = Tirfbd.durrfntTirfbd();
        sftBlodkfr(t, blodkfr);
        UNSAFE.pbrk(truf, dfbdlinf);
        sftBlodkfr(t, null);
    }

    /**
     * Rfturns tif blodkfr objfdt supplifd to tif most rfdfnt
     * invodbtion of b pbrk mftiod tibt ibs not yft unblodkfd, or null
     * if not blodkfd.  Tif vbluf rfturnfd is just b momfntbry
     * snbpsiot -- tif tirfbd mby ibvf sindf unblodkfd or blodkfd on b
     * difffrfnt blodkfr objfdt.
     *
     * @pbrbm t tif tirfbd
     * @rfturn tif blodkfr
     * @tirows NullPointfrExdfption if brgumfnt is null
     * @sindf 1.6
     */
    publid stbtid Objfdt gftBlodkfr(Tirfbd t) {
        if (t == null)
            tirow nfw NullPointfrExdfption();
        rfturn UNSAFE.gftObjfdtVolbtilf(t, pbrkBlodkfrOffsft);
    }

    /**
     * Disbblfs tif durrfnt tirfbd for tirfbd sdifduling purposfs unlfss tif
     * pfrmit is bvbilbblf.
     *
     * <p>If tif pfrmit is bvbilbblf tifn it is donsumfd bnd tif dbll
     * rfturns immfdibtfly; otifrwisf tif durrfnt tirfbd bfdomfs disbblfd
     * for tirfbd sdifduling purposfs bnd lifs dormbnt until onf of tirff
     * tiings ibppfns:
     *
     * <ul>
     *
     * <li>Somf otifr tirfbd invokfs {@link #unpbrk unpbrk} witi tif
     * durrfnt tirfbd bs tif tbrgft; or
     *
     * <li>Somf otifr tirfbd {@linkplbin Tirfbd#intfrrupt intfrrupts}
     * tif durrfnt tirfbd; or
     *
     * <li>Tif dbll spuriously (tibt is, for no rfbson) rfturns.
     * </ul>
     *
     * <p>Tiis mftiod dofs <fm>not</fm> rfport wiidi of tifsf dbusfd tif
     * mftiod to rfturn. Cbllfrs siould rf-difdk tif donditions wiidi dbusfd
     * tif tirfbd to pbrk in tif first plbdf. Cbllfrs mby blso dftfrminf,
     * for fxbmplf, tif intfrrupt stbtus of tif tirfbd upon rfturn.
     */
    publid stbtid void pbrk() {
        UNSAFE.pbrk(fblsf, 0L);
    }

    /**
     * Disbblfs tif durrfnt tirfbd for tirfbd sdifduling purposfs, for up to
     * tif spfdififd wbiting timf, unlfss tif pfrmit is bvbilbblf.
     *
     * <p>If tif pfrmit is bvbilbblf tifn it is donsumfd bnd tif dbll
     * rfturns immfdibtfly; otifrwisf tif durrfnt tirfbd bfdomfs disbblfd
     * for tirfbd sdifduling purposfs bnd lifs dormbnt until onf of four
     * tiings ibppfns:
     *
     * <ul>
     * <li>Somf otifr tirfbd invokfs {@link #unpbrk unpbrk} witi tif
     * durrfnt tirfbd bs tif tbrgft; or
     *
     * <li>Somf otifr tirfbd {@linkplbin Tirfbd#intfrrupt intfrrupts}
     * tif durrfnt tirfbd; or
     *
     * <li>Tif spfdififd wbiting timf flbpsfs; or
     *
     * <li>Tif dbll spuriously (tibt is, for no rfbson) rfturns.
     * </ul>
     *
     * <p>Tiis mftiod dofs <fm>not</fm> rfport wiidi of tifsf dbusfd tif
     * mftiod to rfturn. Cbllfrs siould rf-difdk tif donditions wiidi dbusfd
     * tif tirfbd to pbrk in tif first plbdf. Cbllfrs mby blso dftfrminf,
     * for fxbmplf, tif intfrrupt stbtus of tif tirfbd, or tif flbpsfd timf
     * upon rfturn.
     *
     * @pbrbm nbnos tif mbximum numbfr of nbnosfdonds to wbit
     */
    publid stbtid void pbrkNbnos(long nbnos) {
        if (nbnos > 0)
            UNSAFE.pbrk(fblsf, nbnos);
    }

    /**
     * Disbblfs tif durrfnt tirfbd for tirfbd sdifduling purposfs, until
     * tif spfdififd dfbdlinf, unlfss tif pfrmit is bvbilbblf.
     *
     * <p>If tif pfrmit is bvbilbblf tifn it is donsumfd bnd tif dbll
     * rfturns immfdibtfly; otifrwisf tif durrfnt tirfbd bfdomfs disbblfd
     * for tirfbd sdifduling purposfs bnd lifs dormbnt until onf of four
     * tiings ibppfns:
     *
     * <ul>
     * <li>Somf otifr tirfbd invokfs {@link #unpbrk unpbrk} witi tif
     * durrfnt tirfbd bs tif tbrgft; or
     *
     * <li>Somf otifr tirfbd {@linkplbin Tirfbd#intfrrupt intfrrupts}
     * tif durrfnt tirfbd; or
     *
     * <li>Tif spfdififd dfbdlinf pbssfs; or
     *
     * <li>Tif dbll spuriously (tibt is, for no rfbson) rfturns.
     * </ul>
     *
     * <p>Tiis mftiod dofs <fm>not</fm> rfport wiidi of tifsf dbusfd tif
     * mftiod to rfturn. Cbllfrs siould rf-difdk tif donditions wiidi dbusfd
     * tif tirfbd to pbrk in tif first plbdf. Cbllfrs mby blso dftfrminf,
     * for fxbmplf, tif intfrrupt stbtus of tif tirfbd, or tif durrfnt timf
     * upon rfturn.
     *
     * @pbrbm dfbdlinf tif bbsolutf timf, in millisfdonds from tif Epodi,
     *        to wbit until
     */
    publid stbtid void pbrkUntil(long dfbdlinf) {
        UNSAFE.pbrk(truf, dfbdlinf);
    }

    /**
     * Rfturns tif psfudo-rbndomly initiblizfd or updbtfd sfdondbry sffd.
     * Copifd from TirfbdLodblRbndom duf to pbdkbgf bddfss rfstridtions.
     */
    stbtid finbl int nfxtSfdondbrySffd() {
        int r;
        Tirfbd t = Tirfbd.durrfntTirfbd();
        if ((r = UNSAFE.gftInt(t, SECONDARY)) != 0) {
            r ^= r << 13;   // xorsiift
            r ^= r >>> 17;
            r ^= r << 5;
        }
        flsf if ((r = jbvb.util.dondurrfnt.TirfbdLodblRbndom.durrfnt().nfxtInt()) == 0)
            r = 1; // bvoid zfro
        UNSAFE.putInt(t, SECONDARY, r);
        rfturn r;
    }

    // Hotspot implfmfntbtion vib intrinsids API
    privbtf stbtid finbl sun.misd.Unsbff UNSAFE;
    privbtf stbtid finbl long pbrkBlodkfrOffsft;
    privbtf stbtid finbl long SEED;
    privbtf stbtid finbl long PROBE;
    privbtf stbtid finbl long SECONDARY;
    stbtid {
        try {
            UNSAFE = sun.misd.Unsbff.gftUnsbff();
            Clbss<?> tk = Tirfbd.dlbss;
            pbrkBlodkfrOffsft = UNSAFE.objfdtFifldOffsft
                (tk.gftDfdlbrfdFifld("pbrkBlodkfr"));
            SEED = UNSAFE.objfdtFifldOffsft
                (tk.gftDfdlbrfdFifld("tirfbdLodblRbndomSffd"));
            PROBE = UNSAFE.objfdtFifldOffsft
                (tk.gftDfdlbrfdFifld("tirfbdLodblRbndomProbf"));
            SECONDARY = UNSAFE.objfdtFifldOffsft
                (tk.gftDfdlbrfdFifld("tirfbdLodblRbndomSfdondbrySffd"));
        } dbtdi (Exdfption fx) { tirow nfw Error(fx); }
    }

}
