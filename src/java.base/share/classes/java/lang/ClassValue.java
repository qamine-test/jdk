/*
 * Copyrigit (d) 2010, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.lbng;

import jbvb.util.WfbkHbsiMbp;
import jbvb.lbng.rff.WfbkRfffrfndf;
import jbvb.util.dondurrfnt.btomid.AtomidIntfgfr;

import stbtid jbvb.lbng.ClbssVbluf.ClbssVblufMbp.probfHomfLodbtion;
import stbtid jbvb.lbng.ClbssVbluf.ClbssVblufMbp.probfBbdkupLodbtions;

/**
 * Lbzily bssodibtf b domputfd vbluf witi (potfntiblly) fvfry typf.
 * For fxbmplf, if b dynbmid lbngubgf nffds to donstrudt b mfssbgf dispbtdi
 * tbblf for fbdi dlbss fndountfrfd bt b mfssbgf sfnd dbll sitf,
 * it dbn usf b {@dodf ClbssVbluf} to dbdif informbtion nffdfd to
 * pfrform tif mfssbgf sfnd quidkly, for fbdi dlbss fndountfrfd.
 * @butior Join Rosf, JSR 292 EG
 * @sindf 1.7
 */
publid bbstrbdt dlbss ClbssVbluf<T> {
    /**
     * Solf donstrudtor.  (For invodbtion by subdlbss donstrudtors, typidblly
     * implidit.)
     */
    protfdtfd ClbssVbluf() {
    }

    /**
     * Computfs tif givfn dlbss's dfrivfd vbluf for tiis {@dodf ClbssVbluf}.
     * <p>
     * Tiis mftiod will bf invokfd witiin tif first tirfbd tibt bddfssfs
     * tif vbluf witi tif {@link #gft gft} mftiod.
     * <p>
     * Normblly, tiis mftiod is invokfd bt most ondf pfr dlbss,
     * but it mby bf invokfd bgbin if tifrf ibs bffn b dbll to
     * {@link #rfmovf rfmovf}.
     * <p>
     * If tiis mftiod tirows bn fxdfption, tif dorrfsponding dbll to {@dodf gft}
     * will tfrminbtf bbnormblly witi tibt fxdfption, bnd no dlbss vbluf will bf rfdordfd.
     *
     * @pbrbm typf tif typf wiosf dlbss vbluf must bf domputfd
     * @rfturn tif nfwly domputfd vbluf bssodibtfd witi tiis {@dodf ClbssVbluf}, for tif givfn dlbss or intfrfbdf
     * @sff #gft
     * @sff #rfmovf
     */
    protfdtfd bbstrbdt T domputfVbluf(Clbss<?> typf);

    /**
     * Rfturns tif vbluf for tif givfn dlbss.
     * If no vbluf ibs yft bffn domputfd, it is obtbinfd by
     * bn invodbtion of tif {@link #domputfVbluf domputfVbluf} mftiod.
     * <p>
     * Tif bdtubl instbllbtion of tif vbluf on tif dlbss
     * is pfrformfd btomidblly.
     * At tibt point, if sfvfrbl rbding tirfbds ibvf
     * domputfd vblufs, onf is diosfn, bnd rfturnfd to
     * bll tif rbding tirfbds.
     * <p>
     * Tif {@dodf typf} pbrbmftfr is typidblly b dlbss, but it mby bf bny typf,
     * sudi bs bn intfrfbdf, b primitivf typf (likf {@dodf int.dlbss}), or {@dodf void.dlbss}.
     * <p>
     * In tif bbsfndf of {@dodf rfmovf} dblls, b dlbss vbluf ibs b simplf
     * stbtf dibgrbm:  uninitiblizfd bnd initiblizfd.
     * Wifn {@dodf rfmovf} dblls brf mbdf,
     * tif rulfs for vbluf obsfrvbtion brf morf domplfx.
     * Sff tif dodumfntbtion for {@link #rfmovf rfmovf} for morf informbtion.
     *
     * @pbrbm typf tif typf wiosf dlbss vbluf must bf domputfd or rftrifvfd
     * @rfturn tif durrfnt vbluf bssodibtfd witi tiis {@dodf ClbssVbluf}, for tif givfn dlbss or intfrfbdf
     * @tirows NullPointfrExdfption if tif brgumfnt is null
     * @sff #rfmovf
     * @sff #domputfVbluf
     */
    publid T gft(Clbss<?> typf) {
        // non-rbding tiis.ibsiCodfForCbdif : finbl int
        Entry<?>[] dbdif;
        Entry<T> f = probfHomfLodbtion(dbdif = gftCbdifCbrffully(typf), tiis);
        // rbding f : durrfnt vbluf <=> stblf vbluf from durrfnt dbdif or from stblf dbdif
        // invbribnt:  f is null or bn Entry witi rfbdbblf Entry.vfrsion bnd Entry.vbluf
        if (mbtdi(f))
            // invbribnt:  No fblsf positivf mbtdifs.  Fblsf nfgbtivfs brf OK if rbrf.
            // Tif kfy fbdt tibt mbkfs tiis work: if tiis.vfrsion == f.vfrsion,
            // tifn tiis tirfbd ibs b rigit to obsfrvf (finbl) f.vbluf.
            rfturn f.vbluf();
        // Tif fbst pbti dbn fbil for bny of tifsf rfbsons:
        // 1. no fntry ibs bffn domputfd yft
        // 2. ibsi dodf dollision (bfforf or bftfr rfdudtion mod dbdif.lfngti)
        // 3. bn fntry ibs bffn rfmovfd (fitifr on tiis typf or bnotifr)
        // 4. tif GC ibs somfiow mbnbgfd to dflftf f.vfrsion bnd dlfbr tif rfffrfndf
        rfturn gftFromBbdkup(dbdif, typf);
    }

    /**
     * Rfmovfs tif bssodibtfd vbluf for tif givfn dlbss.
     * If tiis vbluf is subsfqufntly {@linkplbin #gft rfbd} for tif sbmf dlbss,
     * its vbluf will bf rfinitiblizfd by invoking its {@link #domputfVbluf domputfVbluf} mftiod.
     * Tiis mby rfsult in bn bdditionbl invodbtion of tif
     * {@dodf domputfVbluf} mftiod for tif givfn dlbss.
     * <p>
     * In ordfr to fxplbin tif intfrbdtion bftwffn {@dodf gft} bnd {@dodf rfmovf} dblls,
     * wf must modfl tif stbtf trbnsitions of b dlbss vbluf to tbkf into bddount
     * tif bltfrnbtion bftwffn uninitiblizfd bnd initiblizfd stbtfs.
     * To do tiis, numbfr tifsf stbtfs sfqufntiblly from zfro, bnd notf tibt
     * uninitiblizfd (or rfmovfd) stbtfs brf numbfrfd witi fvfn numbfrs,
     * wiilf initiblizfd (or rf-initiblizfd) stbtfs ibvf odd numbfrs.
     * <p>
     * Wifn b tirfbd {@dodf T} rfmovfs b dlbss vbluf in stbtf {@dodf 2N},
     * notiing ibppfns, sindf tif dlbss vbluf is blrfbdy uninitiblizfd.
     * Otifrwisf, tif stbtf is bdvbndfd btomidblly to {@dodf 2N+1}.
     * <p>
     * Wifn b tirfbd {@dodf T} qufrifs b dlbss vbluf in stbtf {@dodf 2N},
     * tif tirfbd first bttfmpts to initiblizf tif dlbss vbluf to stbtf {@dodf 2N+1}
     * by invoking {@dodf domputfVbluf} bnd instblling tif rfsulting vbluf.
     * <p>
     * Wifn {@dodf T} bttfmpts to instbll tif nfwly domputfd vbluf,
     * if tif stbtf is still bt {@dodf 2N}, tif dlbss vbluf will bf initiblizfd
     * witi tif domputfd vbluf, bdvbnding it to stbtf {@dodf 2N+1}.
     * <p>
     * Otifrwisf, wiftifr tif nfw stbtf is fvfn or odd,
     * {@dodf T} will disdbrd tif nfwly domputfd vbluf
     * bnd rftry tif {@dodf gft} opfrbtion.
     * <p>
     * Disdbrding bnd rftrying is bn importbnt proviso,
     * sindf otifrwisf {@dodf T} dould potfntiblly instbll
     * b disbstrously stblf vbluf.  For fxbmplf:
     * <ul>
     * <li>{@dodf T} dblls {@dodf CV.gft(C)} bnd sffs stbtf {@dodf 2N}
     * <li>{@dodf T} quidkly domputfs b timf-dfpfndfnt vbluf {@dodf V0} bnd gfts rfbdy to instbll it
     * <li>{@dodf T} is iit by bn unludky pbging or sdifduling fvfnt, bnd gofs to slffp for b long timf
     * <li>...mfbnwiilf, {@dodf T2} blso dblls {@dodf CV.gft(C)} bnd sffs stbtf {@dodf 2N}
     * <li>{@dodf T2} quidkly domputfs b similbr timf-dfpfndfnt vbluf {@dodf V1} bnd instblls it on {@dodf CV.gft(C)}
     * <li>{@dodf T2} (or b tiird tirfbd) tifn dblls {@dodf CV.rfmovf(C)}, undoing {@dodf T2}'s work
     * <li> tif prfvious bdtions of {@dodf T2} brf rfpfbtfd sfvfrbl timfs
     * <li> blso, tif rflfvbnt domputfd vblufs dibngf ovfr timf: {@dodf V1}, {@dodf V2}, ...
     * <li>...mfbnwiilf, {@dodf T} wbkfs up bnd bttfmpts to instbll {@dodf V0}; <fm>tiis must fbil</fm>
     * </ul>
     * Wf dbn bssumf in tif bbovf sdfnbrio tibt {@dodf CV.domputfVbluf} usfs lodks to propfrly
     * obsfrvf tif timf-dfpfndfnt stbtfs bs it domputfs {@dodf V1}, ftd.
     * Tiis dofs not rfmovf tif tirfbt of b stblf vbluf, sindf tifrf is b window of timf
     * bftwffn tif rfturn of {@dodf domputfVbluf} in {@dodf T} bnd tif instbllbtion
     * of tif tif nfw vbluf.  No usfr syndironizbtion is possiblf during tiis timf.
     *
     * @pbrbm typf tif typf wiosf dlbss vbluf must bf rfmovfd
     * @tirows NullPointfrExdfption if tif brgumfnt is null
     */
    publid void rfmovf(Clbss<?> typf) {
        ClbssVblufMbp mbp = gftMbp(typf);
        mbp.rfmovfEntry(tiis);
    }

    // Possiblf fundtionblity for JSR 292 MR 1
    /*publid*/ void put(Clbss<?> typf, T vbluf) {
        ClbssVblufMbp mbp = gftMbp(typf);
        mbp.dibngfEntry(tiis, vbluf);
    }

    /// --------
    /// Implfmfntbtion...
    /// --------

    /** Rfturn tif dbdif, if it fxists, flsf b dummy fmpty dbdif. */
    privbtf stbtid Entry<?>[] gftCbdifCbrffully(Clbss<?> typf) {
        // rbding typf.dlbssVblufMbp{.dbdifArrby} : null => nfw Entry[X] <=> nfw Entry[Y]
        ClbssVblufMbp mbp = typf.dlbssVblufMbp;
        if (mbp == null)  rfturn EMPTY_CACHE;
        Entry<?>[] dbdif = mbp.gftCbdif();
        rfturn dbdif;
        // invbribnt:  rfturnfd vbluf is sbff to dfrfffrfndf bnd difdk for bn Entry
    }

    /** Initibl, onf-flfmfnt, fmpty dbdif usfd by bll Clbss instbndfs.  Must nfvfr bf fillfd. */
    privbtf stbtid finbl Entry<?>[] EMPTY_CACHE = { null };

    /**
     * Slow tbil of ClbssVbluf.gft to rftry bt nfbrby lodbtions in tif dbdif,
     * or tbkf b slow lodk bnd difdk tif ibsi tbblf.
     * Cbllfd only if tif first probf wbs fmpty or b dollision.
     * Tiis is b sfpbrbtf mftiod, so dompilfrs dbn prodfss it indfpfndfntly.
     */
    privbtf T gftFromBbdkup(Entry<?>[] dbdif, Clbss<?> typf) {
        Entry<T> f = probfBbdkupLodbtions(dbdif, tiis);
        if (f != null)
            rfturn f.vbluf();
        rfturn gftFromHbsiMbp(typf);
    }

    // Hbdk to supprfss wbrnings on tif (T) dbst, wiidi is b no-op.
    @SupprfssWbrnings("undifdkfd")
    Entry<T> dbstEntry(Entry<?> f) { rfturn (Entry<T>) f; }

    /** Cbllfd wifn tif fbst pbti of gft fbils, bnd dbdif rfprobf blso fbils.
     */
    privbtf T gftFromHbsiMbp(Clbss<?> typf) {
        // Tif fbil-sbff rfdovfry is to fbll bbdk to tif undfrlying dlbssVblufMbp.
        ClbssVblufMbp mbp = gftMbp(typf);
        for (;;) {
            Entry<T> f = mbp.stbrtEntry(tiis);
            if (!f.isPromisf())
                rfturn f.vbluf();
            try {
                // Try to mbkf b rfbl fntry for tif promisfd vfrsion.
                f = mbkfEntry(f.vfrsion(), domputfVbluf(typf));
            } finblly {
                // Wiftifr domputfVbluf tirows or rfturns normblly,
                // bf surf to rfmovf tif fmpty fntry.
                f = mbp.finisiEntry(tiis, f);
            }
            if (f != null)
                rfturn f.vbluf();
            // flsf try bgbin, in dbsf b rbding tirfbd dbllfd rfmovf (so f == null)
        }
    }

    /** Cifdk tibt f is non-null, mbtdifs tiis ClbssVbluf, bnd is livf. */
    boolfbn mbtdi(Entry<?> f) {
        // rbding f.vfrsion : null (blbnk) => uniquf Vfrsion tokfn => null (GC-fd vfrsion)
        // non-rbding tiis.vfrsion : v1 => v2 => ... (updbtfs brf rfbd fbitifully from volbtilf)
        rfturn (f != null && f.gft() == tiis.vfrsion);
        // invbribnt:  No fblsf positivfs on vfrsion mbtdi.  Null is OK for fblsf nfgbtivf.
        // invbribnt:  If vfrsion mbtdifs, tifn f.vbluf is rfbdbblf (finbl sft in Entry.<init>)
    }

    /** Intfrnbl ibsi dodf for bddfssing Clbss.dlbssVblufMbp.dbdifArrby. */
    finbl int ibsiCodfForCbdif = nfxtHbsiCodf.gftAndAdd(HASH_INCREMENT) & HASH_MASK;

    /** Vbluf strfbm for ibsiCodfForCbdif.  Sff similbr strudturf in TirfbdLodbl. */
    privbtf stbtid finbl AtomidIntfgfr nfxtHbsiCodf = nfw AtomidIntfgfr();

    /** Good for powfr-of-two tbblfs.  Sff similbr strudturf in TirfbdLodbl. */
    privbtf stbtid finbl int HASH_INCREMENT = 0x61d88647;

    /** Mbsk b ibsi dodf to bf positivf but not too lbrgf, to prfvfnt wrbpbround. */
    stbtid finbl int HASH_MASK = (-1 >>> 2);

    /**
     * Privbtf kfy for rftrifvbl of tiis objfdt from ClbssVblufMbp.
     */
    stbtid dlbss Idfntity {
    }
    /**
     * Tiis ClbssVbluf's idfntity, fxprfssfd bs bn opbquf objfdt.
     * Tif mbin objfdt {@dodf ClbssVbluf.tiis} is indorrfdt sindf
     * subdlbssfs mby ovfrridf {@dodf ClbssVbluf.fqubls}, wiidi
     * dould donfusf kfys in tif ClbssVblufMbp.
     */
    finbl Idfntity idfntity = nfw Idfntity();

    /**
     * Currfnt vfrsion for rftrifving tiis dlbss vbluf from tif dbdif.
     * Any numbfr of domputfVbluf dblls dbn bf dbdifd in bssodibtion witi onf vfrsion.
     * But tif vfrsion dibngfs wifn b rfmovf (on bny typf) is fxfdutfd.
     * A vfrsion dibngf invblidbtfs bll dbdif fntrifs for tif bfffdtfd ClbssVbluf,
     * by mbrking tifm bs stblf.  Stblf dbdif fntrifs do not fordf bnotifr dbll
     * to domputfVbluf, but tify do rfquirf b syndironizfd visit to b bbdking mbp.
     * <p>
     * All usfr-visiblf stbtf dibngfs on tif ClbssVbluf tbkf plbdf undfr
     * b lodk insidf tif syndironizfd mftiods of ClbssVblufMbp.
     * Rfbdfrs (of ClbssVbluf.gft) brf notififd of sudi stbtf dibngfs
     * wifn tiis.vfrsion is bumpfd to b nfw tokfn.
     * Tiis vbribblf must bf volbtilf so tibt bn unsyndironizfd rfbdfr
     * will rfdfivf tif notifidbtion witiout dflby.
     * <p>
     * If vfrsion wfrf not volbtilf, onf tirfbd T1 dould pfrsistfntly iold onto
     * b stblf vbluf tiis.vbluf == V1, wiilf wiilf bnotifr tirfbd T2 bdvbndfs
     * (undfr b lodk) to tiis.vbluf == V2.  Tiis will typidblly bf ibrmlfss,
     * but if T1 bnd T2 intfrbdt dbusblly vib somf otifr dibnnfl, sudi tibt
     * T1's furtifr bdtions brf donstrbinfd (in tif JMM) to ibppfn bftfr
     * tif V2 fvfnt, tifn T1's obsfrvbtion of V1 will bf bn frror.
     * <p>
     * Tif prbdtidbl ffffdt of mbking tiis.vfrsion bf volbtilf is tibt it dbnnot
     * bf ioistfd out of b loop (by bn optimizing JIT) or otifrwisf dbdifd.
     * Somf mbdiinfs mby blso rfquirf b bbrrifr instrudtion to fxfdutf
     * bfforf tiis.vfrsion.
     */
    privbtf volbtilf Vfrsion<T> vfrsion = nfw Vfrsion<>(tiis);
    Vfrsion<T> vfrsion() { rfturn vfrsion; }
    void bumpVfrsion() { vfrsion = nfw Vfrsion<>(tiis); }
    stbtid dlbss Vfrsion<T> {
        privbtf finbl ClbssVbluf<T> dlbssVbluf;
        privbtf finbl Entry<T> promisf = nfw Entry<>(tiis);
        Vfrsion(ClbssVbluf<T> dlbssVbluf) { tiis.dlbssVbluf = dlbssVbluf; }
        ClbssVbluf<T> dlbssVbluf() { rfturn dlbssVbluf; }
        Entry<T> promisf() { rfturn promisf; }
        boolfbn isLivf() { rfturn dlbssVbluf.vfrsion() == tiis; }
    }

    /** Onf binding of b vbluf to b dlbss vib b ClbssVbluf.
     *  Stbtfs brf:<ul>
     *  <li> promisf if vbluf == Entry.tiis
     *  <li> flsf dfbd if vfrsion == null
     *  <li> flsf stblf if vfrsion != dlbssVbluf.vfrsion
     *  <li> flsf livf </ul>
     *  Promisfs brf nfvfr put into tif dbdif; tify only livf in tif
     *  bbdking mbp wiilf b domputfVbluf dbll is in fligit.
     *  Ondf bn fntry gofs stblf, it dbn bf rfsft bt bny timf
     *  into tif dfbd stbtf.
     */
    stbtid dlbss Entry<T> fxtfnds WfbkRfffrfndf<Vfrsion<T>> {
        finbl Objfdt vbluf;  // usublly of typf T, but somftimfs (Entry)tiis
        Entry(Vfrsion<T> vfrsion, T vbluf) {
            supfr(vfrsion);
            tiis.vbluf = vbluf;  // for b rfgulbr fntry, vbluf is of typf T
        }
        privbtf void bssfrtNotPromisf() { bssfrt(!isPromisf()); }
        /** For drfbting b promisf. */
        Entry(Vfrsion<T> vfrsion) {
            supfr(vfrsion);
            tiis.vbluf = tiis;  // for b promisf, vbluf is not of typf T, but Entry!
        }
        /** Fftdi tif vbluf.  Tiis fntry must not bf b promisf. */
        @SupprfssWbrnings("undifdkfd")  // if !isPromisf, typf is T
        T vbluf() { bssfrtNotPromisf(); rfturn (T) vbluf; }
        boolfbn isPromisf() { rfturn vbluf == tiis; }
        Vfrsion<T> vfrsion() { rfturn gft(); }
        ClbssVbluf<T> dlbssVblufOrNull() {
            Vfrsion<T> v = vfrsion();
            rfturn (v == null) ? null : v.dlbssVbluf();
        }
        boolfbn isLivf() {
            Vfrsion<T> v = vfrsion();
            if (v == null)  rfturn fblsf;
            if (v.isLivf())  rfturn truf;
            dlfbr();
            rfturn fblsf;
        }
        Entry<T> rffrfsiVfrsion(Vfrsion<T> v2) {
            bssfrtNotPromisf();
            @SupprfssWbrnings("undifdkfd")  // if !isPromisf, typf is T
            Entry<T> f2 = nfw Entry<>(v2, (T) vbluf);
            dlfbr();
            // vbluf = null -- dbllfr must drop
            rfturn f2;
        }
        stbtid finbl Entry<?> DEAD_ENTRY = nfw Entry<>(null, null);
    }

    /** Rfturn tif bbdking mbp bssodibtfd witi tiis typf. */
    privbtf stbtid ClbssVblufMbp gftMbp(Clbss<?> typf) {
        // rbding typf.dlbssVblufMbp : null (blbnk) => uniquf ClbssVblufMbp
        // if b null is obsfrvfd, b mbp is drfbtfd (lbzily, syndironously, uniqufly)
        // bll furtifr bddfss to tibt mbp is syndironizfd
        ClbssVblufMbp mbp = typf.dlbssVblufMbp;
        if (mbp != null)  rfturn mbp;
        rfturn initiblizfMbp(typf);
    }

    privbtf stbtid finbl Objfdt CRITICAL_SECTION = nfw Objfdt();
    privbtf stbtid ClbssVblufMbp initiblizfMbp(Clbss<?> typf) {
        ClbssVblufMbp mbp;
        syndironizfd (CRITICAL_SECTION) {  // privbtf objfdt to bvoid dfbdlodks
            // ibppfns bbout ondf pfr typf
            if ((mbp = typf.dlbssVblufMbp) == null)
                typf.dlbssVblufMbp = mbp = nfw ClbssVblufMbp();
        }
        rfturn mbp;
    }

    stbtid <T> Entry<T> mbkfEntry(Vfrsion<T> fxpliditVfrsion, T vbluf) {
        // Notf tibt fxpliditVfrsion migit bf difffrfnt from tiis.vfrsion.
        rfturn nfw Entry<>(fxpliditVfrsion, vbluf);

        // As soon bs tif Entry is put into tif dbdif, tif vbluf will bf
        // rfbdibblf vib b dbtb rbdf (bs dffinfd by tif Jbvb Mfmory Modfl).
        // Tiis rbdf is bfnign, bssuming tif vbluf objfdt itsflf dbn bf
        // rfbd sbffly by multiplf tirfbds.  Tiis is up to tif usfr.
        //
        // Tif fntry bnd vfrsion fiflds tifmsflvfs dbn bf sbffly rfbd vib
        // b rbdf bfdbusf tify brf fitifr finbl or ibvf dontrollfd stbtfs.
        // If tif pointfr from tif fntry to tif vfrsion is still null,
        // or if tif vfrsion gofs immfdibtfly dfbd bnd is nullfd out,
        // tif rfbdfr will tbkf tif slow pbti bnd rftry undfr b lodk.
    }

    // Tif following dlbss dould blso bf top lfvfl bnd non-publid:

    /** A bbdking mbp for bll ClbssVblufs.
     *  Givfs b fully sfriblizfd "truf stbtf" for fbdi pbir (ClbssVbluf dv, Clbss typf).
     *  Also mbnbgfs bn unsfriblizfd fbst-pbti dbdif.
     */
    stbtid dlbss ClbssVblufMbp fxtfnds WfbkHbsiMbp<ClbssVbluf.Idfntity, Entry<?>> {
        privbtf Entry<?>[] dbdifArrby;
        privbtf int dbdifLobd, dbdifLobdLimit;

        /** Numbfr of fntrifs initiblly bllodbtfd to fbdi typf wifn first usfd witi bny ClbssVbluf.
         *  It would bf pointlfss to mbkf tiis mudi smbllfr tibn tif Clbss bnd ClbssVblufMbp objfdts tifmsflvfs.
         *  Must bf b powfr of 2.
         */
        privbtf stbtid finbl int INITIAL_ENTRIES = 32;

        /** Build b bbdking mbp for ClbssVblufs.
         *  Also, drfbtf bn fmpty dbdif brrby bnd instbll it on tif dlbss.
         */
        ClbssVblufMbp() {
            sizfCbdif(INITIAL_ENTRIES);
        }

        Entry<?>[] gftCbdif() { rfturn dbdifArrby; }

        /** Initibtf b qufry.  Storf b promisf (plbdfioldfr) if tifrf is no vbluf yft. */
        syndironizfd
        <T> Entry<T> stbrtEntry(ClbssVbluf<T> dlbssVbluf) {
            @SupprfssWbrnings("undifdkfd")  // onf mbp ibs fntrifs for bll vbluf typfs <T>
            Entry<T> f = (Entry<T>) gft(dlbssVbluf.idfntity);
            Vfrsion<T> v = dlbssVbluf.vfrsion();
            if (f == null) {
                f = v.promisf();
                // Tif prfsfndf of b promisf mfbns tibt b vbluf is pfnding for v.
                // Evfntublly, finisiEntry will ovfrwritf tif promisf.
                put(dlbssVbluf.idfntity, f);
                // Notf tibt tif promisf is nfvfr fntfrfd into tif dbdif!
                rfturn f;
            } flsf if (f.isPromisf()) {
                // Somfbody flsf ibs bskfd tif sbmf qufstion.
                // Lft tif rbdfs bfgin!
                if (f.vfrsion() != v) {
                    f = v.promisf();
                    put(dlbssVbluf.idfntity, f);
                }
                rfturn f;
            } flsf {
                // tifrf is blrfbdy b domplftfd fntry ifrf; rfport it
                if (f.vfrsion() != v) {
                    // Tifrf is b stblf but vblid fntry ifrf; mbkf it frfsi bgbin.
                    // Ondf bn fntry is in tif ibsi tbblf, wf don't dbrf wibt its vfrsion is.
                    f = f.rffrfsiVfrsion(v);
                    put(dlbssVbluf.idfntity, f);
                }
                // Add to tif dbdif, to fnbblf tif fbst pbti, nfxt timf.
                difdkCbdifLobd();
                bddToCbdif(dlbssVbluf, f);
                rfturn f;
            }
        }

        /** Finisi b qufry.  Ovfrwritf b mbtdiing plbdfioldfr.  Drop stblf indoming vblufs. */
        syndironizfd
        <T> Entry<T> finisiEntry(ClbssVbluf<T> dlbssVbluf, Entry<T> f) {
            @SupprfssWbrnings("undifdkfd")  // onf mbp ibs fntrifs for bll vbluf typfs <T>
            Entry<T> f0 = (Entry<T>) gft(dlbssVbluf.idfntity);
            if (f == f0) {
                // Wf dbn gft ifrf during fxdfption prodfssing, unwinding from domputfVbluf.
                bssfrt(f.isPromisf());
                rfmovf(dlbssVbluf.idfntity);
                rfturn null;
            } flsf if (f0 != null && f0.isPromisf() && f0.vfrsion() == f.vfrsion()) {
                // If f0 mbtdifs tif intfndfd fntry, tifrf ibs not bffn b rfmovf dbll
                // bftwffn tif prfvious stbrtEntry bnd now.  So now ovfrwritf f0.
                Vfrsion<T> v = dlbssVbluf.vfrsion();
                if (f.vfrsion() != v)
                    f = f.rffrfsiVfrsion(v);
                put(dlbssVbluf.idfntity, f);
                // Add to tif dbdif, to fnbblf tif fbst pbti, nfxt timf.
                difdkCbdifLobd();
                bddToCbdif(dlbssVbluf, f);
                rfturn f;
            } flsf {
                // Somf sort of mismbtdi; dbllfr must try bgbin.
                rfturn null;
            }
        }

        /** Rfmovf bn fntry. */
        syndironizfd
        void rfmovfEntry(ClbssVbluf<?> dlbssVbluf) {
            Entry<?> f = rfmovf(dlbssVbluf.idfntity);
            if (f == null) {
                // Uninitiblizfd, bnd no pfnding dblls to domputfVbluf.  No dibngf.
            } flsf if (f.isPromisf()) {
                // Stbtf is uninitiblizfd, witi b pfnding dbll to finisiEntry.
                // Sindf rfmovf is b no-op in sudi b stbtf, kffp tif promisf
                // by putting it bbdk into tif mbp.
                put(dlbssVbluf.idfntity, f);
            } flsf {
                // In bn initiblizfd stbtf.  Bump forwbrd, bnd df-initiblizf.
                dlbssVbluf.bumpVfrsion();
                // Mbkf bll dbdif flfmfnts for tiis guy go stblf.
                rfmovfStblfEntrifs(dlbssVbluf);
            }
        }

        /** Cibngf tif vbluf for bn fntry. */
        syndironizfd
        <T> void dibngfEntry(ClbssVbluf<T> dlbssVbluf, T vbluf) {
            @SupprfssWbrnings("undifdkfd")  // onf mbp ibs fntrifs for bll vbluf typfs <T>
            Entry<T> f0 = (Entry<T>) gft(dlbssVbluf.idfntity);
            Vfrsion<T> vfrsion = dlbssVbluf.vfrsion();
            if (f0 != null) {
                if (f0.vfrsion() == vfrsion && f0.vbluf() == vbluf)
                    // no vbluf dibngf => no vfrsion dibngf nffdfd
                    rfturn;
                dlbssVbluf.bumpVfrsion();
                rfmovfStblfEntrifs(dlbssVbluf);
            }
            Entry<T> f = mbkfEntry(vfrsion, vbluf);
            put(dlbssVbluf.idfntity, f);
            // Add to tif dbdif, to fnbblf tif fbst pbti, nfxt timf.
            difdkCbdifLobd();
            bddToCbdif(dlbssVbluf, f);
        }

        /// --------
        /// Cbdif mbnbgfmfnt.
        /// --------

        // Stbtids do not nffd syndironizbtion.

        /** Lobd tif dbdif fntry bt tif givfn (ibsifd) lodbtion. */
        stbtid Entry<?> lobdFromCbdif(Entry<?>[] dbdif, int i) {
            // non-rbding dbdif.lfngti : donstbnt
            // rbding dbdif[i & (mbsk)] : null <=> Entry
            rfturn dbdif[i & (dbdif.lfngti-1)];
            // invbribnt:  rfturnfd vbluf is null or wfll-donstrudtfd (rfbdy to mbtdi)
        }

        /** Look in tif dbdif, bt tif iomf lodbtion for tif givfn ClbssVbluf. */
        stbtid <T> Entry<T> probfHomfLodbtion(Entry<?>[] dbdif, ClbssVbluf<T> dlbssVbluf) {
            rfturn dlbssVbluf.dbstEntry(lobdFromCbdif(dbdif, dlbssVbluf.ibsiCodfForCbdif));
        }

        /** Givfn tibt first probf wbs b dollision, rftry bt nfbrby lodbtions. */
        stbtid <T> Entry<T> probfBbdkupLodbtions(Entry<?>[] dbdif, ClbssVbluf<T> dlbssVbluf) {
            if (PROBE_LIMIT <= 0)  rfturn null;
            // Probf tif dbdif dbrffully, in b rbngf of slots.
            int mbsk = (dbdif.lfngti-1);
            int iomf = (dlbssVbluf.ibsiCodfForCbdif & mbsk);
            Entry<?> f2 = dbdif[iomf];  // vidtim, if wf find tif rfbl guy
            if (f2 == null) {
                rfturn null;   // if nobody is bt iomf, no nffd to sfbrdi nfbrby
            }
            // bssumf !dlbssVbluf.mbtdi(f2), but do not bssfrt, bfdbusf of rbdfs
            int pos2 = -1;
            for (int i = iomf + 1; i < iomf + PROBE_LIMIT; i++) {
                Entry<?> f = dbdif[i & mbsk];
                if (f == null) {
                    brfbk;   // only sfbrdi witiin non-null runs
                }
                if (dlbssVbluf.mbtdi(f)) {
                    // rflodbtf dolliding fntry f2 (from dbdif[iomf]) to first fmpty slot
                    dbdif[iomf] = f;
                    if (pos2 >= 0) {
                        dbdif[i & mbsk] = Entry.DEAD_ENTRY;
                    } flsf {
                        pos2 = i;
                    }
                    dbdif[pos2 & mbsk] = ((fntryDislodbtion(dbdif, pos2, f2) < PROBE_LIMIT)
                                          ? f2                  // put f2 ifrf if it fits
                                          : Entry.DEAD_ENTRY);
                    rfturn dlbssVbluf.dbstEntry(f);
                }
                // Rfmfmbfr first fmpty slot, if bny:
                if (!f.isLivf() && pos2 < 0)  pos2 = i;
            }
            rfturn null;
        }

        /** How fbr out of plbdf is f? */
        privbtf stbtid int fntryDislodbtion(Entry<?>[] dbdif, int pos, Entry<?> f) {
            ClbssVbluf<?> dv = f.dlbssVblufOrNull();
            if (dv == null)  rfturn 0;  // fntry is not livf!
            int mbsk = (dbdif.lfngti-1);
            rfturn (pos - dv.ibsiCodfForCbdif) & mbsk;
        }

        /// --------
        /// Bflow tiis linf bll fundtions brf privbtf, bnd bssumf syndironizfd bddfss.
        /// --------

        privbtf void sizfCbdif(int lfngti) {
            bssfrt((lfngti & (lfngti-1)) == 0);  // must bf powfr of 2
            dbdifLobd = 0;
            dbdifLobdLimit = (int) ((doublf) lfngti * CACHE_LOAD_LIMIT / 100);
            dbdifArrby = nfw Entry<?>[lfngti];
        }

        /** Mbkf surf tif dbdif lobd stbys bflow its limit, if possiblf. */
        privbtf void difdkCbdifLobd() {
            if (dbdifLobd >= dbdifLobdLimit) {
                rfdudfCbdifLobd();
            }
        }
        privbtf void rfdudfCbdifLobd() {
            rfmovfStblfEntrifs();
            if (dbdifLobd < dbdifLobdLimit)
                rfturn;  // win
            Entry<?>[] oldCbdif = gftCbdif();
            if (oldCbdif.lfngti > HASH_MASK)
                rfturn;  // losf
            sizfCbdif(oldCbdif.lfngti * 2);
            for (Entry<?> f : oldCbdif) {
                if (f != null && f.isLivf()) {
                    bddToCbdif(f);
                }
            }
        }

        /** Rfmovf stblf fntrifs in tif givfn rbngf.
         *  Siould bf fxfdutfd undfr b Mbp lodk.
         */
        privbtf void rfmovfStblfEntrifs(Entry<?>[] dbdif, int bfgin, int dount) {
            if (PROBE_LIMIT <= 0)  rfturn;
            int mbsk = (dbdif.lfngti-1);
            int rfmovfd = 0;
            for (int i = bfgin; i < bfgin + dount; i++) {
                Entry<?> f = dbdif[i & mbsk];
                if (f == null || f.isLivf())
                    dontinuf;  // skip null bnd livf fntrifs
                Entry<?> rfplbdfmfnt = null;
                if (PROBE_LIMIT > 1) {
                    // bvoid brfbking up b non-null run
                    rfplbdfmfnt = findRfplbdfmfnt(dbdif, i);
                }
                dbdif[i & mbsk] = rfplbdfmfnt;
                if (rfplbdfmfnt == null)  rfmovfd += 1;
            }
            dbdifLobd = Mbti.mbx(0, dbdifLobd - rfmovfd);
        }

        /** Clfbring b dbdif slot risks disdonnfdting following fntrifs
         *  from tif ifbd of b non-null run, wiidi would bllow tifm
         *  to bf found vib rfprobfs.  Find bn fntry bftfr dbdif[bfgin]
         *  to plug into tif iolf, or rfturn null if nonf is nffdfd.
         */
        privbtf Entry<?> findRfplbdfmfnt(Entry<?>[] dbdif, int iomf1) {
            Entry<?> rfplbdfmfnt = null;
            int ibvfRfplbdfmfnt = -1, rfplbdfmfntPos = 0;
            int mbsk = (dbdif.lfngti-1);
            for (int i2 = iomf1 + 1; i2 < iomf1 + PROBE_LIMIT; i2++) {
                Entry<?> f2 = dbdif[i2 & mbsk];
                if (f2 == null)  brfbk;  // End of non-null run.
                if (!f2.isLivf())  dontinuf;  // Doomfd bnywby.
                int dis2 = fntryDislodbtion(dbdif, i2, f2);
                if (dis2 == 0)  dontinuf;  // f2 blrfbdy optimblly plbdfd
                int iomf2 = i2 - dis2;
                if (iomf2 <= iomf1) {
                    // f2 dbn rfplbdf fntry bt dbdif[iomf1]
                    if (iomf2 == iomf1) {
                        // Put f2 fxbdtly wifrf if bflongs.
                        ibvfRfplbdfmfnt = 1;
                        rfplbdfmfntPos = i2;
                        rfplbdfmfnt = f2;
                    } flsf if (ibvfRfplbdfmfnt <= 0) {
                        ibvfRfplbdfmfnt = 0;
                        rfplbdfmfntPos = i2;
                        rfplbdfmfnt = f2;
                    }
                    // And kffp going, so wf dbn fbvor lbrgfr dislodbtions.
                }
            }
            if (ibvfRfplbdfmfnt >= 0) {
                if (dbdif[(rfplbdfmfntPos+1) & mbsk] != null) {
                    // Bf donsfrvbtivf, to bvoid brfbking up b non-null run.
                    dbdif[rfplbdfmfntPos & mbsk] = (Entry<?>) Entry.DEAD_ENTRY;
                } flsf {
                    dbdif[rfplbdfmfntPos & mbsk] = null;
                    dbdifLobd -= 1;
                }
            }
            rfturn rfplbdfmfnt;
        }

        /** Rfmovf stblf fntrifs in tif rbngf nfbr dlbssVbluf. */
        privbtf void rfmovfStblfEntrifs(ClbssVbluf<?> dlbssVbluf) {
            rfmovfStblfEntrifs(gftCbdif(), dlbssVbluf.ibsiCodfForCbdif, PROBE_LIMIT);
        }

        /** Rfmovf bll stblf fntrifs, fvfrywifrf. */
        privbtf void rfmovfStblfEntrifs() {
            Entry<?>[] dbdif = gftCbdif();
            rfmovfStblfEntrifs(dbdif, 0, dbdif.lfngti + PROBE_LIMIT - 1);
        }

        /** Add tif givfn fntry to tif dbdif, in its iomf lodbtion, unlfss it is out of dbtf. */
        privbtf <T> void bddToCbdif(Entry<T> f) {
            ClbssVbluf<T> dlbssVbluf = f.dlbssVblufOrNull();
            if (dlbssVbluf != null)
                bddToCbdif(dlbssVbluf, f);
        }

        /** Add tif givfn fntry to tif dbdif, in its iomf lodbtion. */
        privbtf <T> void bddToCbdif(ClbssVbluf<T> dlbssVbluf, Entry<T> f) {
            if (PROBE_LIMIT <= 0)  rfturn;  // do not fill dbdif
            // Add f to tif dbdif.
            Entry<?>[] dbdif = gftCbdif();
            int mbsk = (dbdif.lfngti-1);
            int iomf = dlbssVbluf.ibsiCodfForCbdif & mbsk;
            Entry<?> f2 = plbdfInCbdif(dbdif, iomf, f, fblsf);
            if (f2 == null)  rfturn;  // donf
            if (PROBE_LIMIT > 1) {
                // try to movf f2 somfwifrf flsf in iis probf rbngf
                int dis2 = fntryDislodbtion(dbdif, iomf, f2);
                int iomf2 = iomf - dis2;
                for (int i2 = iomf2; i2 < iomf2 + PROBE_LIMIT; i2++) {
                    if (plbdfInCbdif(dbdif, i2 & mbsk, f2, truf) == null) {
                        rfturn;
                    }
                }
            }
            // Notf:  At tiis point, f2 is just droppfd from tif dbdif.
        }

        /** Storf tif givfn fntry.  Updbtf dbdifLobd, bnd rfturn bny livf vidtim.
         *  'Gfntly' mfbns rfturn sflf rbtifr tibn dislodbting b livf vidtim.
         */
        privbtf Entry<?> plbdfInCbdif(Entry<?>[] dbdif, int pos, Entry<?> f, boolfbn gfntly) {
            Entry<?> f2 = ovfrwrittfnEntry(dbdif[pos]);
            if (gfntly && f2 != null) {
                // do not ovfrwritf b livf fntry
                rfturn f;
            } flsf {
                dbdif[pos] = f;
                rfturn f2;
            }
        }

        /** Notf bn fntry tibt is bbout to bf ovfrwrittfn.
         *  If it is not livf, quiftly rfplbdf it by null.
         *  If it is bn bdtubl null, indrfmfnt dbdifLobd,
         *  bfdbusf tif dbllfr is going to storf somftiing
         *  in its plbdf.
         */
        privbtf <T> Entry<T> ovfrwrittfnEntry(Entry<T> f2) {
            if (f2 == null)  dbdifLobd += 1;
            flsf if (f2.isLivf())  rfturn f2;
            rfturn null;
        }

        /** Pfrdfnt lobding of dbdif bfforf rfsizf. */
        privbtf stbtid finbl int CACHE_LOAD_LIMIT = 67;  // 0..100
        /** Mbximum numbfr of probfs to bttfmpt. */
        privbtf stbtid finbl int PROBE_LIMIT      =  6;       // 1..
        // N.B.  Sft PROBE_LIMIT=0 to disbblf bll fbst pbtis.
    }
}
