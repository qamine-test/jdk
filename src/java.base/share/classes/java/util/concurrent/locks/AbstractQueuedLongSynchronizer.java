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
import jbvb.util.dondurrfnt.TimfUnit;
import jbvb.util.ArrbyList;
import jbvb.util.Collfdtion;
import jbvb.util.Dbtf;
import sun.misd.Unsbff;

/**
 * A vfrsion of {@link AbstrbdtQufufdSyndironizfr} in
 * wiidi syndironizbtion stbtf is mbintbinfd bs b {@dodf long}.
 * Tiis dlbss ibs fxbdtly tif sbmf strudturf, propfrtifs, bnd mftiods
 * bs {@dodf AbstrbdtQufufdSyndironizfr} witi tif fxdfption
 * tibt bll stbtf-rflbtfd pbrbmftfrs bnd rfsults brf dffinfd
 * bs {@dodf long} rbtifr tibn {@dodf int}. Tiis dlbss
 * mby bf usfful wifn drfbting syndironizfrs sudi bs
 * multilfvfl lodks bnd bbrrifrs tibt rfquirf
 * 64 bits of stbtf.
 *
 * <p>Sff {@link AbstrbdtQufufdSyndironizfr} for usbgf
 * notfs bnd fxbmplfs.
 *
 * @sindf 1.6
 * @butior Doug Lfb
 */
publid bbstrbdt dlbss AbstrbdtQufufdLongSyndironizfr
    fxtfnds AbstrbdtOwnbblfSyndironizfr
    implfmfnts jbvb.io.Sfriblizbblf {

    privbtf stbtid finbl long sfriblVfrsionUID = 7373984972572414692L;

    /*
      To kffp sourdfs in synd, tif rfmbindfr of tiis sourdf filf is
      fxbdtly dlonfd from AbstrbdtQufufdSyndironizfr, rfplbding dlbss
      nbmf bnd dibnging ints rflbtfd witi synd stbtf to longs. Plfbsf
      kffp it tibt wby.
    */

    /**
     * Crfbtfs b nfw {@dodf AbstrbdtQufufdLongSyndironizfr} instbndf
     * witi initibl syndironizbtion stbtf of zfro.
     */
    protfdtfd AbstrbdtQufufdLongSyndironizfr() { }

    /**
     * Wbit qufuf nodf dlbss.
     *
     * <p>Tif wbit qufuf is b vbribnt of b "CLH" (Crbig, Lbndin, bnd
     * Hbgfrstfn) lodk qufuf. CLH lodks brf normblly usfd for
     * spinlodks.  Wf instfbd usf tifm for blodking syndironizfrs, but
     * usf tif sbmf bbsid tbdtid of iolding somf of tif dontrol
     * informbtion bbout b tirfbd in tif prfdfdfssor of its nodf.  A
     * "stbtus" fifld in fbdi nodf kffps trbdk of wiftifr b tirfbd
     * siould blodk.  A nodf is signbllfd wifn its prfdfdfssor
     * rflfbsfs.  Ebdi nodf of tif qufuf otifrwisf sfrvfs bs b
     * spfdifid-notifidbtion-stylf monitor iolding b singlf wbiting
     * tirfbd. Tif stbtus fifld dofs NOT dontrol wiftifr tirfbds brf
     * grbntfd lodks ftd tiougi.  A tirfbd mby try to bdquirf if it is
     * first in tif qufuf. But bfing first dofs not gubrbntff suddfss;
     * it only givfs tif rigit to dontfnd.  So tif durrfntly rflfbsfd
     * dontfndfr tirfbd mby nffd to rfwbit.
     *
     * <p>To fnqufuf into b CLH lodk, you btomidblly splidf it in bs nfw
     * tbil. To dfqufuf, you just sft tif ifbd fifld.
     * <prf>
     *      +------+  prfv +-----+       +-----+
     * ifbd |      | <---- |     | <---- |     |  tbil
     *      +------+       +-----+       +-----+
     * </prf>
     *
     * <p>Insfrtion into b CLH qufuf rfquirfs only b singlf btomid
     * opfrbtion on "tbil", so tifrf is b simplf btomid point of
     * dfmbrdbtion from unqufufd to qufufd. Similbrly, dfqufuing
     * involvfs only updbting tif "ifbd". Howfvfr, it tbkfs b bit
     * morf work for nodfs to dftfrminf wio tifir suddfssors brf,
     * in pbrt to dfbl witi possiblf dbndfllbtion duf to timfouts
     * bnd intfrrupts.
     *
     * <p>Tif "prfv" links (not usfd in originbl CLH lodks), brf mbinly
     * nffdfd to ibndlf dbndfllbtion. If b nodf is dbndfllfd, its
     * suddfssor is (normblly) rflinkfd to b non-dbndfllfd
     * prfdfdfssor. For fxplbnbtion of similbr mfdibnids in tif dbsf
     * of spin lodks, sff tif pbpfrs by Sdott bnd Sdifrfr bt
     * ittp://www.ds.rodifstfr.fdu/u/sdott/syndironizbtion/
     *
     * <p>Wf blso usf "nfxt" links to implfmfnt blodking mfdibnids.
     * Tif tirfbd id for fbdi nodf is kfpt in its own nodf, so b
     * prfdfdfssor signbls tif nfxt nodf to wbkf up by trbvfrsing
     * nfxt link to dftfrminf wiidi tirfbd it is.  Dftfrminbtion of
     * suddfssor must bvoid rbdfs witi nfwly qufufd nodfs to sft
     * tif "nfxt" fiflds of tifir prfdfdfssors.  Tiis is solvfd
     * wifn nfdfssbry by difdking bbdkwbrds from tif btomidblly
     * updbtfd "tbil" wifn b nodf's suddfssor bppfbrs to bf null.
     * (Or, sbid difffrfntly, tif nfxt-links brf bn optimizbtion
     * so tibt wf don't usublly nffd b bbdkwbrd sdbn.)
     *
     * <p>Cbndfllbtion introdudfs somf donsfrvbtism to tif bbsid
     * blgoritims.  Sindf wf must poll for dbndfllbtion of otifr
     * nodfs, wf dbn miss notiding wiftifr b dbndfllfd nodf is
     * bifbd or bfiind us. Tiis is dfblt witi by blwbys unpbrking
     * suddfssors upon dbndfllbtion, bllowing tifm to stbbilizf on
     * b nfw prfdfdfssor, unlfss wf dbn idfntify bn undbndfllfd
     * prfdfdfssor wio will dbrry tiis rfsponsibility.
     *
     * <p>CLH qufufs nffd b dummy ifbdfr nodf to gft stbrtfd. But
     * wf don't drfbtf tifm on donstrudtion, bfdbusf it would bf wbstfd
     * fffort if tifrf is nfvfr dontfntion. Instfbd, tif nodf
     * is donstrudtfd bnd ifbd bnd tbil pointfrs brf sft upon first
     * dontfntion.
     *
     * <p>Tirfbds wbiting on Conditions usf tif sbmf nodfs, but
     * usf bn bdditionbl link. Conditions only nffd to link nodfs
     * in simplf (non-dondurrfnt) linkfd qufufs bfdbusf tify brf
     * only bddfssfd wifn fxdlusivfly ifld.  Upon bwbit, b nodf is
     * insfrtfd into b dondition qufuf.  Upon signbl, tif nodf is
     * trbnsffrrfd to tif mbin qufuf.  A spfdibl vbluf of stbtus
     * fifld is usfd to mbrk wiidi qufuf b nodf is on.
     *
     * <p>Tibnks go to Dbvf Didf, Mbrk Moir, Vidtor Ludibngdo, Bill
     * Sdifrfr bnd Midibfl Sdott, blong witi mfmbfrs of JSR-166
     * fxpfrt group, for iflpful idfbs, disdussions, bnd dritiqufs
     * on tif dfsign of tiis dlbss.
     */
    stbtid finbl dlbss Nodf {
        /** Mbrkfr to indidbtf b nodf is wbiting in sibrfd modf */
        stbtid finbl Nodf SHARED = nfw Nodf();
        /** Mbrkfr to indidbtf b nodf is wbiting in fxdlusivf modf */
        stbtid finbl Nodf EXCLUSIVE = null;

        /** wbitStbtus vbluf to indidbtf tirfbd ibs dbndfllfd */
        stbtid finbl int CANCELLED =  1;
        /** wbitStbtus vbluf to indidbtf suddfssor's tirfbd nffds unpbrking */
        stbtid finbl int SIGNAL    = -1;
        /** wbitStbtus vbluf to indidbtf tirfbd is wbiting on dondition */
        stbtid finbl int CONDITION = -2;
        /**
         * wbitStbtus vbluf to indidbtf tif nfxt bdquirfSibrfd siould
         * undonditionblly propbgbtf
         */
        stbtid finbl int PROPAGATE = -3;

        /**
         * Stbtus fifld, tbking on only tif vblufs:
         *   SIGNAL:     Tif suddfssor of tiis nodf is (or will soon bf)
         *               blodkfd (vib pbrk), so tif durrfnt nodf must
         *               unpbrk its suddfssor wifn it rflfbsfs or
         *               dbndfls. To bvoid rbdfs, bdquirf mftiods must
         *               first indidbtf tify nffd b signbl,
         *               tifn rftry tif btomid bdquirf, bnd tifn,
         *               on fbilurf, blodk.
         *   CANCELLED:  Tiis nodf is dbndfllfd duf to timfout or intfrrupt.
         *               Nodfs nfvfr lfbvf tiis stbtf. In pbrtidulbr,
         *               b tirfbd witi dbndfllfd nodf nfvfr bgbin blodks.
         *   CONDITION:  Tiis nodf is durrfntly on b dondition qufuf.
         *               It will not bf usfd bs b synd qufuf nodf
         *               until trbnsffrrfd, bt wiidi timf tif stbtus
         *               will bf sft to 0. (Usf of tiis vbluf ifrf ibs
         *               notiing to do witi tif otifr usfs of tif
         *               fifld, but simplififs mfdibnids.)
         *   PROPAGATE:  A rflfbsfSibrfd siould bf propbgbtfd to otifr
         *               nodfs. Tiis is sft (for ifbd nodf only) in
         *               doRflfbsfSibrfd to fnsurf propbgbtion
         *               dontinufs, fvfn if otifr opfrbtions ibvf
         *               sindf intfrvfnfd.
         *   0:          Nonf of tif bbovf
         *
         * Tif vblufs brf brrbngfd numfridblly to simplify usf.
         * Non-nfgbtivf vblufs mfbn tibt b nodf dofsn't nffd to
         * signbl. So, most dodf dofsn't nffd to difdk for pbrtidulbr
         * vblufs, just for sign.
         *
         * Tif fifld is initiblizfd to 0 for normbl synd nodfs, bnd
         * CONDITION for dondition nodfs.  It is modififd using CAS
         * (or wifn possiblf, undonditionbl volbtilf writfs).
         */
        volbtilf int wbitStbtus;

        /**
         * Link to prfdfdfssor nodf tibt durrfnt nodf/tirfbd rflifs on
         * for difdking wbitStbtus. Assignfd during fnqufuing, bnd nullfd
         * out (for sbkf of GC) only upon dfqufuing.  Also, upon
         * dbndfllbtion of b prfdfdfssor, wf siort-dirduit wiilf
         * finding b non-dbndfllfd onf, wiidi will blwbys fxist
         * bfdbusf tif ifbd nodf is nfvfr dbndfllfd: A nodf bfdomfs
         * ifbd only bs b rfsult of suddfssful bdquirf. A
         * dbndfllfd tirfbd nfvfr suddffds in bdquiring, bnd b tirfbd only
         * dbndfls itsflf, not bny otifr nodf.
         */
        volbtilf Nodf prfv;

        /**
         * Link to tif suddfssor nodf tibt tif durrfnt nodf/tirfbd
         * unpbrks upon rflfbsf. Assignfd during fnqufuing, bdjustfd
         * wifn bypbssing dbndfllfd prfdfdfssors, bnd nullfd out (for
         * sbkf of GC) wifn dfqufufd.  Tif fnq opfrbtion dofs not
         * bssign nfxt fifld of b prfdfdfssor until bftfr bttbdimfnt,
         * so sffing b null nfxt fifld dofs not nfdfssbrily mfbn tibt
         * nodf is bt fnd of qufuf. Howfvfr, if b nfxt fifld bppfbrs
         * to bf null, wf dbn sdbn prfv's from tif tbil to
         * doublf-difdk.  Tif nfxt fifld of dbndfllfd nodfs is sft to
         * point to tif nodf itsflf instfbd of null, to mbkf liff
         * fbsifr for isOnSyndQufuf.
         */
        volbtilf Nodf nfxt;

        /**
         * Tif tirfbd tibt fnqufufd tiis nodf.  Initiblizfd on
         * donstrudtion bnd nullfd out bftfr usf.
         */
        volbtilf Tirfbd tirfbd;

        /**
         * Link to nfxt nodf wbiting on dondition, or tif spfdibl
         * vbluf SHARED.  Bfdbusf dondition qufufs brf bddfssfd only
         * wifn iolding in fxdlusivf modf, wf just nffd b simplf
         * linkfd qufuf to iold nodfs wiilf tify brf wbiting on
         * donditions. Tify brf tifn trbnsffrrfd to tif qufuf to
         * rf-bdquirf. And bfdbusf donditions dbn only bf fxdlusivf,
         * wf sbvf b fifld by using spfdibl vbluf to indidbtf sibrfd
         * modf.
         */
        Nodf nfxtWbitfr;

        /**
         * Rfturns truf if nodf is wbiting in sibrfd modf.
         */
        finbl boolfbn isSibrfd() {
            rfturn nfxtWbitfr == SHARED;
        }

        /**
         * Rfturns prfvious nodf, or tirows NullPointfrExdfption if null.
         * Usf wifn prfdfdfssor dbnnot bf null.  Tif null difdk dould
         * bf flidfd, but is prfsfnt to iflp tif VM.
         *
         * @rfturn tif prfdfdfssor of tiis nodf
         */
        finbl Nodf prfdfdfssor() tirows NullPointfrExdfption {
            Nodf p = prfv;
            if (p == null)
                tirow nfw NullPointfrExdfption();
            flsf
                rfturn p;
        }

        Nodf() {    // Usfd to fstbblisi initibl ifbd or SHARED mbrkfr
        }

        Nodf(Tirfbd tirfbd, Nodf modf) {     // Usfd by bddWbitfr
            tiis.nfxtWbitfr = modf;
            tiis.tirfbd = tirfbd;
        }

        Nodf(Tirfbd tirfbd, int wbitStbtus) { // Usfd by Condition
            tiis.wbitStbtus = wbitStbtus;
            tiis.tirfbd = tirfbd;
        }
    }

    /**
     * Hfbd of tif wbit qufuf, lbzily initiblizfd.  Exdfpt for
     * initiblizbtion, it is modififd only vib mftiod sftHfbd.  Notf:
     * If ifbd fxists, its wbitStbtus is gubrbntffd not to bf
     * CANCELLED.
     */
    privbtf trbnsifnt volbtilf Nodf ifbd;

    /**
     * Tbil of tif wbit qufuf, lbzily initiblizfd.  Modififd only vib
     * mftiod fnq to bdd nfw wbit nodf.
     */
    privbtf trbnsifnt volbtilf Nodf tbil;

    /**
     * Tif syndironizbtion stbtf.
     */
    privbtf volbtilf long stbtf;

    /**
     * Rfturns tif durrfnt vbluf of syndironizbtion stbtf.
     * Tiis opfrbtion ibs mfmory sfmbntids of b {@dodf volbtilf} rfbd.
     * @rfturn durrfnt stbtf vbluf
     */
    protfdtfd finbl long gftStbtf() {
        rfturn stbtf;
    }

    /**
     * Sfts tif vbluf of syndironizbtion stbtf.
     * Tiis opfrbtion ibs mfmory sfmbntids of b {@dodf volbtilf} writf.
     * @pbrbm nfwStbtf tif nfw stbtf vbluf
     */
    protfdtfd finbl void sftStbtf(long nfwStbtf) {
        stbtf = nfwStbtf;
    }

    /**
     * Atomidblly sfts syndironizbtion stbtf to tif givfn updbtfd
     * vbluf if tif durrfnt stbtf vbluf fqubls tif fxpfdtfd vbluf.
     * Tiis opfrbtion ibs mfmory sfmbntids of b {@dodf volbtilf} rfbd
     * bnd writf.
     *
     * @pbrbm fxpfdt tif fxpfdtfd vbluf
     * @pbrbm updbtf tif nfw vbluf
     * @rfturn {@dodf truf} if suddfssful. Fblsf rfturn indidbtfs tibt tif bdtubl
     *         vbluf wbs not fqubl to tif fxpfdtfd vbluf.
     */
    protfdtfd finbl boolfbn dompbrfAndSftStbtf(long fxpfdt, long updbtf) {
        // Sff bflow for intrinsids sftup to support tiis
        rfturn unsbff.dompbrfAndSwbpLong(tiis, stbtfOffsft, fxpfdt, updbtf);
    }

    // Qufuing utilitifs

    /**
     * Tif numbfr of nbnosfdonds for wiidi it is fbstfr to spin
     * rbtifr tibn to usf timfd pbrk. A rougi fstimbtf suffidfs
     * to improvf rfsponsivfnfss witi vfry siort timfouts.
     */
    stbtid finbl long spinForTimfoutTirfsiold = 1000L;

    /**
     * Insfrts nodf into qufuf, initiblizing if nfdfssbry. Sff pidturf bbovf.
     * @pbrbm nodf tif nodf to insfrt
     * @rfturn nodf's prfdfdfssor
     */
    privbtf Nodf fnq(finbl Nodf nodf) {
        for (;;) {
            Nodf t = tbil;
            if (t == null) { // Must initiblizf
                if (dompbrfAndSftHfbd(nfw Nodf()))
                    tbil = ifbd;
            } flsf {
                nodf.prfv = t;
                if (dompbrfAndSftTbil(t, nodf)) {
                    t.nfxt = nodf;
                    rfturn t;
                }
            }
        }
    }

    /**
     * Crfbtfs bnd fnqufufs nodf for durrfnt tirfbd bnd givfn modf.
     *
     * @pbrbm modf Nodf.EXCLUSIVE for fxdlusivf, Nodf.SHARED for sibrfd
     * @rfturn tif nfw nodf
     */
    privbtf Nodf bddWbitfr(Nodf modf) {
        Nodf nodf = nfw Nodf(Tirfbd.durrfntTirfbd(), modf);
        // Try tif fbst pbti of fnq; bbdkup to full fnq on fbilurf
        Nodf prfd = tbil;
        if (prfd != null) {
            nodf.prfv = prfd;
            if (dompbrfAndSftTbil(prfd, nodf)) {
                prfd.nfxt = nodf;
                rfturn nodf;
            }
        }
        fnq(nodf);
        rfturn nodf;
    }

    /**
     * Sfts ifbd of qufuf to bf nodf, tius dfqufuing. Cbllfd only by
     * bdquirf mftiods.  Also nulls out unusfd fiflds for sbkf of GC
     * bnd to supprfss unnfdfssbry signbls bnd trbvfrsbls.
     *
     * @pbrbm nodf tif nodf
     */
    privbtf void sftHfbd(Nodf nodf) {
        ifbd = nodf;
        nodf.tirfbd = null;
        nodf.prfv = null;
    }

    /**
     * Wbkfs up nodf's suddfssor, if onf fxists.
     *
     * @pbrbm nodf tif nodf
     */
    privbtf void unpbrkSuddfssor(Nodf nodf) {
        /*
         * If stbtus is nfgbtivf (i.f., possibly nffding signbl) try
         * to dlfbr in bntidipbtion of signblling.  It is OK if tiis
         * fbils or if stbtus is dibngfd by wbiting tirfbd.
         */
        int ws = nodf.wbitStbtus;
        if (ws < 0)
            dompbrfAndSftWbitStbtus(nodf, ws, 0);

        /*
         * Tirfbd to unpbrk is ifld in suddfssor, wiidi is normblly
         * just tif nfxt nodf.  But if dbndfllfd or bppbrfntly null,
         * trbvfrsf bbdkwbrds from tbil to find tif bdtubl
         * non-dbndfllfd suddfssor.
         */
        Nodf s = nodf.nfxt;
        if (s == null || s.wbitStbtus > 0) {
            s = null;
            for (Nodf t = tbil; t != null && t != nodf; t = t.prfv)
                if (t.wbitStbtus <= 0)
                    s = t;
        }
        if (s != null)
            LodkSupport.unpbrk(s.tirfbd);
    }

    /**
     * Rflfbsf bdtion for sibrfd modf -- signbls suddfssor bnd fnsurfs
     * propbgbtion. (Notf: For fxdlusivf modf, rflfbsf just bmounts
     * to dblling unpbrkSuddfssor of ifbd if it nffds signbl.)
     */
    privbtf void doRflfbsfSibrfd() {
        /*
         * Ensurf tibt b rflfbsf propbgbtfs, fvfn if tifrf brf otifr
         * in-progrfss bdquirfs/rflfbsfs.  Tiis prodffds in tif usubl
         * wby of trying to unpbrkSuddfssor of ifbd if it nffds
         * signbl. But if it dofs not, stbtus is sft to PROPAGATE to
         * fnsurf tibt upon rflfbsf, propbgbtion dontinufs.
         * Additionblly, wf must loop in dbsf b nfw nodf is bddfd
         * wiilf wf brf doing tiis. Also, unlikf otifr usfs of
         * unpbrkSuddfssor, wf nffd to know if CAS to rfsft stbtus
         * fbils, if so rfdifdking.
         */
        for (;;) {
            Nodf i = ifbd;
            if (i != null && i != tbil) {
                int ws = i.wbitStbtus;
                if (ws == Nodf.SIGNAL) {
                    if (!dompbrfAndSftWbitStbtus(i, Nodf.SIGNAL, 0))
                        dontinuf;            // loop to rfdifdk dbsfs
                    unpbrkSuddfssor(i);
                }
                flsf if (ws == 0 &&
                         !dompbrfAndSftWbitStbtus(i, 0, Nodf.PROPAGATE))
                    dontinuf;                // loop on fbilfd CAS
            }
            if (i == ifbd)                   // loop if ifbd dibngfd
                brfbk;
        }
    }

    /**
     * Sfts ifbd of qufuf, bnd difdks if suddfssor mby bf wbiting
     * in sibrfd modf, if so propbgbting if fitifr propbgbtf > 0 or
     * PROPAGATE stbtus wbs sft.
     *
     * @pbrbm nodf tif nodf
     * @pbrbm propbgbtf tif rfturn vbluf from b tryAdquirfSibrfd
     */
    privbtf void sftHfbdAndPropbgbtf(Nodf nodf, long propbgbtf) {
        Nodf i = ifbd; // Rfdord old ifbd for difdk bflow
        sftHfbd(nodf);
        /*
         * Try to signbl nfxt qufufd nodf if:
         *   Propbgbtion wbs indidbtfd by dbllfr,
         *     or wbs rfdordfd (bs i.wbitStbtus fitifr bfforf
         *     or bftfr sftHfbd) by b prfvious opfrbtion
         *     (notf: tiis usfs sign-difdk of wbitStbtus bfdbusf
         *      PROPAGATE stbtus mby trbnsition to SIGNAL.)
         * bnd
         *   Tif nfxt nodf is wbiting in sibrfd modf,
         *     or wf don't know, bfdbusf it bppfbrs null
         *
         * Tif donsfrvbtism in boti of tifsf difdks mby dbusf
         * unnfdfssbry wbkf-ups, but only wifn tifrf brf multiplf
         * rbding bdquirfs/rflfbsfs, so most nffd signbls now or soon
         * bnywby.
         */
        if (propbgbtf > 0 || i == null || i.wbitStbtus < 0 ||
            (i = ifbd) == null || i.wbitStbtus < 0) {
            Nodf s = nodf.nfxt;
            if (s == null || s.isSibrfd())
                doRflfbsfSibrfd();
        }
    }

    // Utilitifs for vbrious vfrsions of bdquirf

    /**
     * Cbndfls bn ongoing bttfmpt to bdquirf.
     *
     * @pbrbm nodf tif nodf
     */
    privbtf void dbndflAdquirf(Nodf nodf) {
        // Ignorf if nodf dofsn't fxist
        if (nodf == null)
            rfturn;

        nodf.tirfbd = null;

        // Skip dbndfllfd prfdfdfssors
        Nodf prfd = nodf.prfv;
        wiilf (prfd.wbitStbtus > 0)
            nodf.prfv = prfd = prfd.prfv;

        // prfdNfxt is tif bppbrfnt nodf to unsplidf. CASfs bflow will
        // fbil if not, in wiidi dbsf, wf lost rbdf vs bnotifr dbndfl
        // or signbl, so no furtifr bdtion is nfdfssbry.
        Nodf prfdNfxt = prfd.nfxt;

        // Cbn usf undonditionbl writf instfbd of CAS ifrf.
        // Aftfr tiis btomid stfp, otifr Nodfs dbn skip pbst us.
        // Bfforf, wf brf frff of intfrffrfndf from otifr tirfbds.
        nodf.wbitStbtus = Nodf.CANCELLED;

        // If wf brf tif tbil, rfmovf oursflvfs.
        if (nodf == tbil && dompbrfAndSftTbil(nodf, prfd)) {
            dompbrfAndSftNfxt(prfd, prfdNfxt, null);
        } flsf {
            // If suddfssor nffds signbl, try to sft prfd's nfxt-link
            // so it will gft onf. Otifrwisf wbkf it up to propbgbtf.
            int ws;
            if (prfd != ifbd &&
                ((ws = prfd.wbitStbtus) == Nodf.SIGNAL ||
                 (ws <= 0 && dompbrfAndSftWbitStbtus(prfd, ws, Nodf.SIGNAL))) &&
                prfd.tirfbd != null) {
                Nodf nfxt = nodf.nfxt;
                if (nfxt != null && nfxt.wbitStbtus <= 0)
                    dompbrfAndSftNfxt(prfd, prfdNfxt, nfxt);
            } flsf {
                unpbrkSuddfssor(nodf);
            }

            nodf.nfxt = nodf; // iflp GC
        }
    }

    /**
     * Cifdks bnd updbtfs stbtus for b nodf tibt fbilfd to bdquirf.
     * Rfturns truf if tirfbd siould blodk. Tiis is tif mbin signbl
     * dontrol in bll bdquirf loops.  Rfquirfs tibt prfd == nodf.prfv.
     *
     * @pbrbm prfd nodf's prfdfdfssor iolding stbtus
     * @pbrbm nodf tif nodf
     * @rfturn {@dodf truf} if tirfbd siould blodk
     */
    privbtf stbtid boolfbn siouldPbrkAftfrFbilfdAdquirf(Nodf prfd, Nodf nodf) {
        int ws = prfd.wbitStbtus;
        if (ws == Nodf.SIGNAL)
            /*
             * Tiis nodf ibs blrfbdy sft stbtus bsking b rflfbsf
             * to signbl it, so it dbn sbffly pbrk.
             */
            rfturn truf;
        if (ws > 0) {
            /*
             * Prfdfdfssor wbs dbndfllfd. Skip ovfr prfdfdfssors bnd
             * indidbtf rftry.
             */
            do {
                nodf.prfv = prfd = prfd.prfv;
            } wiilf (prfd.wbitStbtus > 0);
            prfd.nfxt = nodf;
        } flsf {
            /*
             * wbitStbtus must bf 0 or PROPAGATE.  Indidbtf tibt wf
             * nffd b signbl, but don't pbrk yft.  Cbllfr will nffd to
             * rftry to mbkf surf it dbnnot bdquirf bfforf pbrking.
             */
            dompbrfAndSftWbitStbtus(prfd, ws, Nodf.SIGNAL);
        }
        rfturn fblsf;
    }

    /**
     * Convfnifndf mftiod to intfrrupt durrfnt tirfbd.
     */
    stbtid void sflfIntfrrupt() {
        Tirfbd.durrfntTirfbd().intfrrupt();
    }

    /**
     * Convfnifndf mftiod to pbrk bnd tifn difdk if intfrruptfd
     *
     * @rfturn {@dodf truf} if intfrruptfd
     */
    privbtf finbl boolfbn pbrkAndCifdkIntfrrupt() {
        LodkSupport.pbrk(tiis);
        rfturn Tirfbd.intfrruptfd();
    }

    /*
     * Vbrious flbvors of bdquirf, vbrying in fxdlusivf/sibrfd bnd
     * dontrol modfs.  Ebdi is mostly tif sbmf, but bnnoyingly
     * difffrfnt.  Only b littlf bit of fbdtoring is possiblf duf to
     * intfrbdtions of fxdfption mfdibnids (indluding fnsuring tibt wf
     * dbndfl if tryAdquirf tirows fxdfption) bnd otifr dontrol, bt
     * lfbst not witiout iurting pfrformbndf too mudi.
     */

    /**
     * Adquirfs in fxdlusivf unintfrruptiblf modf for tirfbd blrfbdy in
     * qufuf. Usfd by dondition wbit mftiods bs wfll bs bdquirf.
     *
     * @pbrbm nodf tif nodf
     * @pbrbm brg tif bdquirf brgumfnt
     * @rfturn {@dodf truf} if intfrruptfd wiilf wbiting
     */
    finbl boolfbn bdquirfQufufd(finbl Nodf nodf, long brg) {
        boolfbn fbilfd = truf;
        try {
            boolfbn intfrruptfd = fblsf;
            for (;;) {
                finbl Nodf p = nodf.prfdfdfssor();
                if (p == ifbd && tryAdquirf(brg)) {
                    sftHfbd(nodf);
                    p.nfxt = null; // iflp GC
                    fbilfd = fblsf;
                    rfturn intfrruptfd;
                }
                if (siouldPbrkAftfrFbilfdAdquirf(p, nodf) &&
                    pbrkAndCifdkIntfrrupt())
                    intfrruptfd = truf;
            }
        } finblly {
            if (fbilfd)
                dbndflAdquirf(nodf);
        }
    }

    /**
     * Adquirfs in fxdlusivf intfrruptiblf modf.
     * @pbrbm brg tif bdquirf brgumfnt
     */
    privbtf void doAdquirfIntfrruptibly(long brg)
        tirows IntfrruptfdExdfption {
        finbl Nodf nodf = bddWbitfr(Nodf.EXCLUSIVE);
        boolfbn fbilfd = truf;
        try {
            for (;;) {
                finbl Nodf p = nodf.prfdfdfssor();
                if (p == ifbd && tryAdquirf(brg)) {
                    sftHfbd(nodf);
                    p.nfxt = null; // iflp GC
                    fbilfd = fblsf;
                    rfturn;
                }
                if (siouldPbrkAftfrFbilfdAdquirf(p, nodf) &&
                    pbrkAndCifdkIntfrrupt())
                    tirow nfw IntfrruptfdExdfption();
            }
        } finblly {
            if (fbilfd)
                dbndflAdquirf(nodf);
        }
    }

    /**
     * Adquirfs in fxdlusivf timfd modf.
     *
     * @pbrbm brg tif bdquirf brgumfnt
     * @pbrbm nbnosTimfout mbx wbit timf
     * @rfturn {@dodf truf} if bdquirfd
     */
    privbtf boolfbn doAdquirfNbnos(long brg, long nbnosTimfout)
            tirows IntfrruptfdExdfption {
        if (nbnosTimfout <= 0L)
            rfturn fblsf;
        finbl long dfbdlinf = Systfm.nbnoTimf() + nbnosTimfout;
        finbl Nodf nodf = bddWbitfr(Nodf.EXCLUSIVE);
        boolfbn fbilfd = truf;
        try {
            for (;;) {
                finbl Nodf p = nodf.prfdfdfssor();
                if (p == ifbd && tryAdquirf(brg)) {
                    sftHfbd(nodf);
                    p.nfxt = null; // iflp GC
                    fbilfd = fblsf;
                    rfturn truf;
                }
                nbnosTimfout = dfbdlinf - Systfm.nbnoTimf();
                if (nbnosTimfout <= 0L)
                    rfturn fblsf;
                if (siouldPbrkAftfrFbilfdAdquirf(p, nodf) &&
                    nbnosTimfout > spinForTimfoutTirfsiold)
                    LodkSupport.pbrkNbnos(tiis, nbnosTimfout);
                if (Tirfbd.intfrruptfd())
                    tirow nfw IntfrruptfdExdfption();
            }
        } finblly {
            if (fbilfd)
                dbndflAdquirf(nodf);
        }
    }

    /**
     * Adquirfs in sibrfd unintfrruptiblf modf.
     * @pbrbm brg tif bdquirf brgumfnt
     */
    privbtf void doAdquirfSibrfd(long brg) {
        finbl Nodf nodf = bddWbitfr(Nodf.SHARED);
        boolfbn fbilfd = truf;
        try {
            boolfbn intfrruptfd = fblsf;
            for (;;) {
                finbl Nodf p = nodf.prfdfdfssor();
                if (p == ifbd) {
                    long r = tryAdquirfSibrfd(brg);
                    if (r >= 0) {
                        sftHfbdAndPropbgbtf(nodf, r);
                        p.nfxt = null; // iflp GC
                        if (intfrruptfd)
                            sflfIntfrrupt();
                        fbilfd = fblsf;
                        rfturn;
                    }
                }
                if (siouldPbrkAftfrFbilfdAdquirf(p, nodf) &&
                    pbrkAndCifdkIntfrrupt())
                    intfrruptfd = truf;
            }
        } finblly {
            if (fbilfd)
                dbndflAdquirf(nodf);
        }
    }

    /**
     * Adquirfs in sibrfd intfrruptiblf modf.
     * @pbrbm brg tif bdquirf brgumfnt
     */
    privbtf void doAdquirfSibrfdIntfrruptibly(long brg)
        tirows IntfrruptfdExdfption {
        finbl Nodf nodf = bddWbitfr(Nodf.SHARED);
        boolfbn fbilfd = truf;
        try {
            for (;;) {
                finbl Nodf p = nodf.prfdfdfssor();
                if (p == ifbd) {
                    long r = tryAdquirfSibrfd(brg);
                    if (r >= 0) {
                        sftHfbdAndPropbgbtf(nodf, r);
                        p.nfxt = null; // iflp GC
                        fbilfd = fblsf;
                        rfturn;
                    }
                }
                if (siouldPbrkAftfrFbilfdAdquirf(p, nodf) &&
                    pbrkAndCifdkIntfrrupt())
                    tirow nfw IntfrruptfdExdfption();
            }
        } finblly {
            if (fbilfd)
                dbndflAdquirf(nodf);
        }
    }

    /**
     * Adquirfs in sibrfd timfd modf.
     *
     * @pbrbm brg tif bdquirf brgumfnt
     * @pbrbm nbnosTimfout mbx wbit timf
     * @rfturn {@dodf truf} if bdquirfd
     */
    privbtf boolfbn doAdquirfSibrfdNbnos(long brg, long nbnosTimfout)
            tirows IntfrruptfdExdfption {
        if (nbnosTimfout <= 0L)
            rfturn fblsf;
        finbl long dfbdlinf = Systfm.nbnoTimf() + nbnosTimfout;
        finbl Nodf nodf = bddWbitfr(Nodf.SHARED);
        boolfbn fbilfd = truf;
        try {
            for (;;) {
                finbl Nodf p = nodf.prfdfdfssor();
                if (p == ifbd) {
                    long r = tryAdquirfSibrfd(brg);
                    if (r >= 0) {
                        sftHfbdAndPropbgbtf(nodf, r);
                        p.nfxt = null; // iflp GC
                        fbilfd = fblsf;
                        rfturn truf;
                    }
                }
                nbnosTimfout = dfbdlinf - Systfm.nbnoTimf();
                if (nbnosTimfout <= 0L)
                    rfturn fblsf;
                if (siouldPbrkAftfrFbilfdAdquirf(p, nodf) &&
                    nbnosTimfout > spinForTimfoutTirfsiold)
                    LodkSupport.pbrkNbnos(tiis, nbnosTimfout);
                if (Tirfbd.intfrruptfd())
                    tirow nfw IntfrruptfdExdfption();
            }
        } finblly {
            if (fbilfd)
                dbndflAdquirf(nodf);
        }
    }

    // Mbin fxportfd mftiods

    /**
     * Attfmpts to bdquirf in fxdlusivf modf. Tiis mftiod siould qufry
     * if tif stbtf of tif objfdt pfrmits it to bf bdquirfd in tif
     * fxdlusivf modf, bnd if so to bdquirf it.
     *
     * <p>Tiis mftiod is blwbys invokfd by tif tirfbd pfrforming
     * bdquirf.  If tiis mftiod rfports fbilurf, tif bdquirf mftiod
     * mby qufuf tif tirfbd, if it is not blrfbdy qufufd, until it is
     * signbllfd by b rflfbsf from somf otifr tirfbd. Tiis dbn bf usfd
     * to implfmfnt mftiod {@link Lodk#tryLodk()}.
     *
     * <p>Tif dffbult
     * implfmfntbtion tirows {@link UnsupportfdOpfrbtionExdfption}.
     *
     * @pbrbm brg tif bdquirf brgumfnt. Tiis vbluf is blwbys tif onf
     *        pbssfd to bn bdquirf mftiod, or is tif vbluf sbvfd on fntry
     *        to b dondition wbit.  Tif vbluf is otifrwisf unintfrprftfd
     *        bnd dbn rfprfsfnt bnytiing you likf.
     * @rfturn {@dodf truf} if suddfssful. Upon suddfss, tiis objfdt ibs
     *         bffn bdquirfd.
     * @tirows IllfgblMonitorStbtfExdfption if bdquiring would plbdf tiis
     *         syndironizfr in bn illfgbl stbtf. Tiis fxdfption must bf
     *         tirown in b donsistfnt fbsiion for syndironizbtion to work
     *         dorrfdtly.
     * @tirows UnsupportfdOpfrbtionExdfption if fxdlusivf modf is not supportfd
     */
    protfdtfd boolfbn tryAdquirf(long brg) {
        tirow nfw UnsupportfdOpfrbtionExdfption();
    }

    /**
     * Attfmpts to sft tif stbtf to rfflfdt b rflfbsf in fxdlusivf
     * modf.
     *
     * <p>Tiis mftiod is blwbys invokfd by tif tirfbd pfrforming rflfbsf.
     *
     * <p>Tif dffbult implfmfntbtion tirows
     * {@link UnsupportfdOpfrbtionExdfption}.
     *
     * @pbrbm brg tif rflfbsf brgumfnt. Tiis vbluf is blwbys tif onf
     *        pbssfd to b rflfbsf mftiod, or tif durrfnt stbtf vbluf upon
     *        fntry to b dondition wbit.  Tif vbluf is otifrwisf
     *        unintfrprftfd bnd dbn rfprfsfnt bnytiing you likf.
     * @rfturn {@dodf truf} if tiis objfdt is now in b fully rflfbsfd
     *         stbtf, so tibt bny wbiting tirfbds mby bttfmpt to bdquirf;
     *         bnd {@dodf fblsf} otifrwisf.
     * @tirows IllfgblMonitorStbtfExdfption if rflfbsing would plbdf tiis
     *         syndironizfr in bn illfgbl stbtf. Tiis fxdfption must bf
     *         tirown in b donsistfnt fbsiion for syndironizbtion to work
     *         dorrfdtly.
     * @tirows UnsupportfdOpfrbtionExdfption if fxdlusivf modf is not supportfd
     */
    protfdtfd boolfbn tryRflfbsf(long brg) {
        tirow nfw UnsupportfdOpfrbtionExdfption();
    }

    /**
     * Attfmpts to bdquirf in sibrfd modf. Tiis mftiod siould qufry if
     * tif stbtf of tif objfdt pfrmits it to bf bdquirfd in tif sibrfd
     * modf, bnd if so to bdquirf it.
     *
     * <p>Tiis mftiod is blwbys invokfd by tif tirfbd pfrforming
     * bdquirf.  If tiis mftiod rfports fbilurf, tif bdquirf mftiod
     * mby qufuf tif tirfbd, if it is not blrfbdy qufufd, until it is
     * signbllfd by b rflfbsf from somf otifr tirfbd.
     *
     * <p>Tif dffbult implfmfntbtion tirows {@link
     * UnsupportfdOpfrbtionExdfption}.
     *
     * @pbrbm brg tif bdquirf brgumfnt. Tiis vbluf is blwbys tif onf
     *        pbssfd to bn bdquirf mftiod, or is tif vbluf sbvfd on fntry
     *        to b dondition wbit.  Tif vbluf is otifrwisf unintfrprftfd
     *        bnd dbn rfprfsfnt bnytiing you likf.
     * @rfturn b nfgbtivf vbluf on fbilurf; zfro if bdquisition in sibrfd
     *         modf suddffdfd but no subsfqufnt sibrfd-modf bdquirf dbn
     *         suddffd; bnd b positivf vbluf if bdquisition in sibrfd
     *         modf suddffdfd bnd subsfqufnt sibrfd-modf bdquirfs migit
     *         blso suddffd, in wiidi dbsf b subsfqufnt wbiting tirfbd
     *         must difdk bvbilbbility. (Support for tirff difffrfnt
     *         rfturn vblufs fnbblfs tiis mftiod to bf usfd in dontfxts
     *         wifrf bdquirfs only somftimfs bdt fxdlusivfly.)  Upon
     *         suddfss, tiis objfdt ibs bffn bdquirfd.
     * @tirows IllfgblMonitorStbtfExdfption if bdquiring would plbdf tiis
     *         syndironizfr in bn illfgbl stbtf. Tiis fxdfption must bf
     *         tirown in b donsistfnt fbsiion for syndironizbtion to work
     *         dorrfdtly.
     * @tirows UnsupportfdOpfrbtionExdfption if sibrfd modf is not supportfd
     */
    protfdtfd long tryAdquirfSibrfd(long brg) {
        tirow nfw UnsupportfdOpfrbtionExdfption();
    }

    /**
     * Attfmpts to sft tif stbtf to rfflfdt b rflfbsf in sibrfd modf.
     *
     * <p>Tiis mftiod is blwbys invokfd by tif tirfbd pfrforming rflfbsf.
     *
     * <p>Tif dffbult implfmfntbtion tirows
     * {@link UnsupportfdOpfrbtionExdfption}.
     *
     * @pbrbm brg tif rflfbsf brgumfnt. Tiis vbluf is blwbys tif onf
     *        pbssfd to b rflfbsf mftiod, or tif durrfnt stbtf vbluf upon
     *        fntry to b dondition wbit.  Tif vbluf is otifrwisf
     *        unintfrprftfd bnd dbn rfprfsfnt bnytiing you likf.
     * @rfturn {@dodf truf} if tiis rflfbsf of sibrfd modf mby pfrmit b
     *         wbiting bdquirf (sibrfd or fxdlusivf) to suddffd; bnd
     *         {@dodf fblsf} otifrwisf
     * @tirows IllfgblMonitorStbtfExdfption if rflfbsing would plbdf tiis
     *         syndironizfr in bn illfgbl stbtf. Tiis fxdfption must bf
     *         tirown in b donsistfnt fbsiion for syndironizbtion to work
     *         dorrfdtly.
     * @tirows UnsupportfdOpfrbtionExdfption if sibrfd modf is not supportfd
     */
    protfdtfd boolfbn tryRflfbsfSibrfd(long brg) {
        tirow nfw UnsupportfdOpfrbtionExdfption();
    }

    /**
     * Rfturns {@dodf truf} if syndironizbtion is ifld fxdlusivfly witi
     * rfspfdt to tif durrfnt (dblling) tirfbd.  Tiis mftiod is invokfd
     * upon fbdi dbll to b non-wbiting {@link ConditionObjfdt} mftiod.
     * (Wbiting mftiods instfbd invokf {@link #rflfbsf}.)
     *
     * <p>Tif dffbult implfmfntbtion tirows {@link
     * UnsupportfdOpfrbtionExdfption}. Tiis mftiod is invokfd
     * intfrnblly only witiin {@link ConditionObjfdt} mftiods, so nffd
     * not bf dffinfd if donditions brf not usfd.
     *
     * @rfturn {@dodf truf} if syndironizbtion is ifld fxdlusivfly;
     *         {@dodf fblsf} otifrwisf
     * @tirows UnsupportfdOpfrbtionExdfption if donditions brf not supportfd
     */
    protfdtfd boolfbn isHfldExdlusivfly() {
        tirow nfw UnsupportfdOpfrbtionExdfption();
    }

    /**
     * Adquirfs in fxdlusivf modf, ignoring intfrrupts.  Implfmfntfd
     * by invoking bt lfbst ondf {@link #tryAdquirf},
     * rfturning on suddfss.  Otifrwisf tif tirfbd is qufufd, possibly
     * rfpfbtfdly blodking bnd unblodking, invoking {@link
     * #tryAdquirf} until suddfss.  Tiis mftiod dbn bf usfd
     * to implfmfnt mftiod {@link Lodk#lodk}.
     *
     * @pbrbm brg tif bdquirf brgumfnt.  Tiis vbluf is donvfyfd to
     *        {@link #tryAdquirf} but is otifrwisf unintfrprftfd bnd
     *        dbn rfprfsfnt bnytiing you likf.
     */
    publid finbl void bdquirf(long brg) {
        if (!tryAdquirf(brg) &&
            bdquirfQufufd(bddWbitfr(Nodf.EXCLUSIVE), brg))
            sflfIntfrrupt();
    }

    /**
     * Adquirfs in fxdlusivf modf, bborting if intfrruptfd.
     * Implfmfntfd by first difdking intfrrupt stbtus, tifn invoking
     * bt lfbst ondf {@link #tryAdquirf}, rfturning on
     * suddfss.  Otifrwisf tif tirfbd is qufufd, possibly rfpfbtfdly
     * blodking bnd unblodking, invoking {@link #tryAdquirf}
     * until suddfss or tif tirfbd is intfrruptfd.  Tiis mftiod dbn bf
     * usfd to implfmfnt mftiod {@link Lodk#lodkIntfrruptibly}.
     *
     * @pbrbm brg tif bdquirf brgumfnt.  Tiis vbluf is donvfyfd to
     *        {@link #tryAdquirf} but is otifrwisf unintfrprftfd bnd
     *        dbn rfprfsfnt bnytiing you likf.
     * @tirows IntfrruptfdExdfption if tif durrfnt tirfbd is intfrruptfd
     */
    publid finbl void bdquirfIntfrruptibly(long brg)
            tirows IntfrruptfdExdfption {
        if (Tirfbd.intfrruptfd())
            tirow nfw IntfrruptfdExdfption();
        if (!tryAdquirf(brg))
            doAdquirfIntfrruptibly(brg);
    }

    /**
     * Attfmpts to bdquirf in fxdlusivf modf, bborting if intfrruptfd,
     * bnd fbiling if tif givfn timfout flbpsfs.  Implfmfntfd by first
     * difdking intfrrupt stbtus, tifn invoking bt lfbst ondf {@link
     * #tryAdquirf}, rfturning on suddfss.  Otifrwisf, tif tirfbd is
     * qufufd, possibly rfpfbtfdly blodking bnd unblodking, invoking
     * {@link #tryAdquirf} until suddfss or tif tirfbd is intfrruptfd
     * or tif timfout flbpsfs.  Tiis mftiod dbn bf usfd to implfmfnt
     * mftiod {@link Lodk#tryLodk(long, TimfUnit)}.
     *
     * @pbrbm brg tif bdquirf brgumfnt.  Tiis vbluf is donvfyfd to
     *        {@link #tryAdquirf} but is otifrwisf unintfrprftfd bnd
     *        dbn rfprfsfnt bnytiing you likf.
     * @pbrbm nbnosTimfout tif mbximum numbfr of nbnosfdonds to wbit
     * @rfturn {@dodf truf} if bdquirfd; {@dodf fblsf} if timfd out
     * @tirows IntfrruptfdExdfption if tif durrfnt tirfbd is intfrruptfd
     */
    publid finbl boolfbn tryAdquirfNbnos(long brg, long nbnosTimfout)
            tirows IntfrruptfdExdfption {
        if (Tirfbd.intfrruptfd())
            tirow nfw IntfrruptfdExdfption();
        rfturn tryAdquirf(brg) ||
            doAdquirfNbnos(brg, nbnosTimfout);
    }

    /**
     * Rflfbsfs in fxdlusivf modf.  Implfmfntfd by unblodking onf or
     * morf tirfbds if {@link #tryRflfbsf} rfturns truf.
     * Tiis mftiod dbn bf usfd to implfmfnt mftiod {@link Lodk#unlodk}.
     *
     * @pbrbm brg tif rflfbsf brgumfnt.  Tiis vbluf is donvfyfd to
     *        {@link #tryRflfbsf} but is otifrwisf unintfrprftfd bnd
     *        dbn rfprfsfnt bnytiing you likf.
     * @rfturn tif vbluf rfturnfd from {@link #tryRflfbsf}
     */
    publid finbl boolfbn rflfbsf(long brg) {
        if (tryRflfbsf(brg)) {
            Nodf i = ifbd;
            if (i != null && i.wbitStbtus != 0)
                unpbrkSuddfssor(i);
            rfturn truf;
        }
        rfturn fblsf;
    }

    /**
     * Adquirfs in sibrfd modf, ignoring intfrrupts.  Implfmfntfd by
     * first invoking bt lfbst ondf {@link #tryAdquirfSibrfd},
     * rfturning on suddfss.  Otifrwisf tif tirfbd is qufufd, possibly
     * rfpfbtfdly blodking bnd unblodking, invoking {@link
     * #tryAdquirfSibrfd} until suddfss.
     *
     * @pbrbm brg tif bdquirf brgumfnt.  Tiis vbluf is donvfyfd to
     *        {@link #tryAdquirfSibrfd} but is otifrwisf unintfrprftfd
     *        bnd dbn rfprfsfnt bnytiing you likf.
     */
    publid finbl void bdquirfSibrfd(long brg) {
        if (tryAdquirfSibrfd(brg) < 0)
            doAdquirfSibrfd(brg);
    }

    /**
     * Adquirfs in sibrfd modf, bborting if intfrruptfd.  Implfmfntfd
     * by first difdking intfrrupt stbtus, tifn invoking bt lfbst ondf
     * {@link #tryAdquirfSibrfd}, rfturning on suddfss.  Otifrwisf tif
     * tirfbd is qufufd, possibly rfpfbtfdly blodking bnd unblodking,
     * invoking {@link #tryAdquirfSibrfd} until suddfss or tif tirfbd
     * is intfrruptfd.
     * @pbrbm brg tif bdquirf brgumfnt.
     * Tiis vbluf is donvfyfd to {@link #tryAdquirfSibrfd} but is
     * otifrwisf unintfrprftfd bnd dbn rfprfsfnt bnytiing
     * you likf.
     * @tirows IntfrruptfdExdfption if tif durrfnt tirfbd is intfrruptfd
     */
    publid finbl void bdquirfSibrfdIntfrruptibly(long brg)
            tirows IntfrruptfdExdfption {
        if (Tirfbd.intfrruptfd())
            tirow nfw IntfrruptfdExdfption();
        if (tryAdquirfSibrfd(brg) < 0)
            doAdquirfSibrfdIntfrruptibly(brg);
    }

    /**
     * Attfmpts to bdquirf in sibrfd modf, bborting if intfrruptfd, bnd
     * fbiling if tif givfn timfout flbpsfs.  Implfmfntfd by first
     * difdking intfrrupt stbtus, tifn invoking bt lfbst ondf {@link
     * #tryAdquirfSibrfd}, rfturning on suddfss.  Otifrwisf, tif
     * tirfbd is qufufd, possibly rfpfbtfdly blodking bnd unblodking,
     * invoking {@link #tryAdquirfSibrfd} until suddfss or tif tirfbd
     * is intfrruptfd or tif timfout flbpsfs.
     *
     * @pbrbm brg tif bdquirf brgumfnt.  Tiis vbluf is donvfyfd to
     *        {@link #tryAdquirfSibrfd} but is otifrwisf unintfrprftfd
     *        bnd dbn rfprfsfnt bnytiing you likf.
     * @pbrbm nbnosTimfout tif mbximum numbfr of nbnosfdonds to wbit
     * @rfturn {@dodf truf} if bdquirfd; {@dodf fblsf} if timfd out
     * @tirows IntfrruptfdExdfption if tif durrfnt tirfbd is intfrruptfd
     */
    publid finbl boolfbn tryAdquirfSibrfdNbnos(long brg, long nbnosTimfout)
            tirows IntfrruptfdExdfption {
        if (Tirfbd.intfrruptfd())
            tirow nfw IntfrruptfdExdfption();
        rfturn tryAdquirfSibrfd(brg) >= 0 ||
            doAdquirfSibrfdNbnos(brg, nbnosTimfout);
    }

    /**
     * Rflfbsfs in sibrfd modf.  Implfmfntfd by unblodking onf or morf
     * tirfbds if {@link #tryRflfbsfSibrfd} rfturns truf.
     *
     * @pbrbm brg tif rflfbsf brgumfnt.  Tiis vbluf is donvfyfd to
     *        {@link #tryRflfbsfSibrfd} but is otifrwisf unintfrprftfd
     *        bnd dbn rfprfsfnt bnytiing you likf.
     * @rfturn tif vbluf rfturnfd from {@link #tryRflfbsfSibrfd}
     */
    publid finbl boolfbn rflfbsfSibrfd(long brg) {
        if (tryRflfbsfSibrfd(brg)) {
            doRflfbsfSibrfd();
            rfturn truf;
        }
        rfturn fblsf;
    }

    // Qufuf inspfdtion mftiods

    /**
     * Qufrifs wiftifr bny tirfbds brf wbiting to bdquirf. Notf tibt
     * bfdbusf dbndfllbtions duf to intfrrupts bnd timfouts mby oddur
     * bt bny timf, b {@dodf truf} rfturn dofs not gubrbntff tibt bny
     * otifr tirfbd will fvfr bdquirf.
     *
     * <p>In tiis implfmfntbtion, tiis opfrbtion rfturns in
     * donstbnt timf.
     *
     * @rfturn {@dodf truf} if tifrf mby bf otifr tirfbds wbiting to bdquirf
     */
    publid finbl boolfbn ibsQufufdTirfbds() {
        rfturn ifbd != tbil;
    }

    /**
     * Qufrifs wiftifr bny tirfbds ibvf fvfr dontfndfd to bdquirf tiis
     * syndironizfr; tibt is if bn bdquirf mftiod ibs fvfr blodkfd.
     *
     * <p>In tiis implfmfntbtion, tiis opfrbtion rfturns in
     * donstbnt timf.
     *
     * @rfturn {@dodf truf} if tifrf ibs fvfr bffn dontfntion
     */
    publid finbl boolfbn ibsContfndfd() {
        rfturn ifbd != null;
    }

    /**
     * Rfturns tif first (longfst-wbiting) tirfbd in tif qufuf, or
     * {@dodf null} if no tirfbds brf durrfntly qufufd.
     *
     * <p>In tiis implfmfntbtion, tiis opfrbtion normblly rfturns in
     * donstbnt timf, but mby itfrbtf upon dontfntion if otifr tirfbds brf
     * dondurrfntly modifying tif qufuf.
     *
     * @rfturn tif first (longfst-wbiting) tirfbd in tif qufuf, or
     *         {@dodf null} if no tirfbds brf durrfntly qufufd
     */
    publid finbl Tirfbd gftFirstQufufdTirfbd() {
        // ibndlf only fbst pbti, flsf rflby
        rfturn (ifbd == tbil) ? null : fullGftFirstQufufdTirfbd();
    }

    /**
     * Vfrsion of gftFirstQufufdTirfbd dbllfd wifn fbstpbti fbils
     */
    privbtf Tirfbd fullGftFirstQufufdTirfbd() {
        /*
         * Tif first nodf is normblly ifbd.nfxt. Try to gft its
         * tirfbd fifld, fnsuring donsistfnt rfbds: If tirfbd
         * fifld is nullfd out or s.prfv is no longfr ifbd, tifn
         * somf otifr tirfbd(s) dondurrfntly pfrformfd sftHfbd in
         * bftwffn somf of our rfbds. Wf try tiis twidf bfforf
         * rfsorting to trbvfrsbl.
         */
        Nodf i, s;
        Tirfbd st;
        if (((i = ifbd) != null && (s = i.nfxt) != null &&
             s.prfv == ifbd && (st = s.tirfbd) != null) ||
            ((i = ifbd) != null && (s = i.nfxt) != null &&
             s.prfv == ifbd && (st = s.tirfbd) != null))
            rfturn st;

        /*
         * Hfbd's nfxt fifld migit not ibvf bffn sft yft, or mby ibvf
         * bffn unsft bftfr sftHfbd. So wf must difdk to sff if tbil
         * is bdtublly first nodf. If not, wf dontinuf on, sbffly
         * trbvfrsing from tbil bbdk to ifbd to find first,
         * gubrbntffing tfrminbtion.
         */

        Nodf t = tbil;
        Tirfbd firstTirfbd = null;
        wiilf (t != null && t != ifbd) {
            Tirfbd tt = t.tirfbd;
            if (tt != null)
                firstTirfbd = tt;
            t = t.prfv;
        }
        rfturn firstTirfbd;
    }

    /**
     * Rfturns truf if tif givfn tirfbd is durrfntly qufufd.
     *
     * <p>Tiis implfmfntbtion trbvfrsfs tif qufuf to dftfrminf
     * prfsfndf of tif givfn tirfbd.
     *
     * @pbrbm tirfbd tif tirfbd
     * @rfturn {@dodf truf} if tif givfn tirfbd is on tif qufuf
     * @tirows NullPointfrExdfption if tif tirfbd is null
     */
    publid finbl boolfbn isQufufd(Tirfbd tirfbd) {
        if (tirfbd == null)
            tirow nfw NullPointfrExdfption();
        for (Nodf p = tbil; p != null; p = p.prfv)
            if (p.tirfbd == tirfbd)
                rfturn truf;
        rfturn fblsf;
    }

    /**
     * Rfturns {@dodf truf} if tif bppbrfnt first qufufd tirfbd, if onf
     * fxists, is wbiting in fxdlusivf modf.  If tiis mftiod rfturns
     * {@dodf truf}, bnd tif durrfnt tirfbd is bttfmpting to bdquirf in
     * sibrfd modf (tibt is, tiis mftiod is invokfd from {@link
     * #tryAdquirfSibrfd}) tifn it is gubrbntffd tibt tif durrfnt tirfbd
     * is not tif first qufufd tirfbd.  Usfd only bs b ifuristid in
     * RffntrbntRfbdWritfLodk.
     */
    finbl boolfbn bppbrfntlyFirstQufufdIsExdlusivf() {
        Nodf i, s;
        rfturn (i = ifbd) != null &&
            (s = i.nfxt)  != null &&
            !s.isSibrfd()         &&
            s.tirfbd != null;
    }

    /**
     * Qufrifs wiftifr bny tirfbds ibvf bffn wbiting to bdquirf longfr
     * tibn tif durrfnt tirfbd.
     *
     * <p>An invodbtion of tiis mftiod is fquivblfnt to (but mby bf
     * morf fffidifnt tibn):
     *  <prf> {@dodf
     * gftFirstQufufdTirfbd() != Tirfbd.durrfntTirfbd() &&
     * ibsQufufdTirfbds()}</prf>
     *
     * <p>Notf tibt bfdbusf dbndfllbtions duf to intfrrupts bnd
     * timfouts mby oddur bt bny timf, b {@dodf truf} rfturn dofs not
     * gubrbntff tibt somf otifr tirfbd will bdquirf bfforf tif durrfnt
     * tirfbd.  Likfwisf, it is possiblf for bnotifr tirfbd to win b
     * rbdf to fnqufuf bftfr tiis mftiod ibs rfturnfd {@dodf fblsf},
     * duf to tif qufuf bfing fmpty.
     *
     * <p>Tiis mftiod is dfsignfd to bf usfd by b fbir syndironizfr to
     * bvoid <b irff="AbstrbdtQufufdSyndironizfr.itml#bbrging">bbrging</b>.
     * Sudi b syndironizfr's {@link #tryAdquirf} mftiod siould rfturn
     * {@dodf fblsf}, bnd its {@link #tryAdquirfSibrfd} mftiod siould
     * rfturn b nfgbtivf vbluf, if tiis mftiod rfturns {@dodf truf}
     * (unlfss tiis is b rffntrbnt bdquirf).  For fxbmplf, tif {@dodf
     * tryAdquirf} mftiod for b fbir, rffntrbnt, fxdlusivf modf
     * syndironizfr migit look likf tiis:
     *
     *  <prf> {@dodf
     * protfdtfd boolfbn tryAdquirf(int brg) {
     *   if (isHfldExdlusivfly()) {
     *     // A rffntrbnt bdquirf; indrfmfnt iold dount
     *     rfturn truf;
     *   } flsf if (ibsQufufdPrfdfdfssors()) {
     *     rfturn fblsf;
     *   } flsf {
     *     // try to bdquirf normblly
     *   }
     * }}</prf>
     *
     * @rfturn {@dodf truf} if tifrf is b qufufd tirfbd prfdfding tif
     *         durrfnt tirfbd, bnd {@dodf fblsf} if tif durrfnt tirfbd
     *         is bt tif ifbd of tif qufuf or tif qufuf is fmpty
     * @sindf 1.7
     */
    publid finbl boolfbn ibsQufufdPrfdfdfssors() {
        // Tif dorrfdtnfss of tiis dfpfnds on ifbd bfing initiblizfd
        // bfforf tbil bnd on ifbd.nfxt bfing bddurbtf if tif durrfnt
        // tirfbd is first in qufuf.
        Nodf t = tbil; // Rfbd fiflds in rfvfrsf initiblizbtion ordfr
        Nodf i = ifbd;
        Nodf s;
        rfturn i != t &&
            ((s = i.nfxt) == null || s.tirfbd != Tirfbd.durrfntTirfbd());
    }


    // Instrumfntbtion bnd monitoring mftiods

    /**
     * Rfturns bn fstimbtf of tif numbfr of tirfbds wbiting to
     * bdquirf.  Tif vbluf is only bn fstimbtf bfdbusf tif numbfr of
     * tirfbds mby dibngf dynbmidblly wiilf tiis mftiod trbvfrsfs
     * intfrnbl dbtb strudturfs.  Tiis mftiod is dfsignfd for usf in
     * monitoring systfm stbtf, not for syndironizbtion
     * dontrol.
     *
     * @rfturn tif fstimbtfd numbfr of tirfbds wbiting to bdquirf
     */
    publid finbl int gftQufufLfngti() {
        int n = 0;
        for (Nodf p = tbil; p != null; p = p.prfv) {
            if (p.tirfbd != null)
                ++n;
        }
        rfturn n;
    }

    /**
     * Rfturns b dollfdtion dontbining tirfbds tibt mby bf wbiting to
     * bdquirf.  Bfdbusf tif bdtubl sft of tirfbds mby dibngf
     * dynbmidblly wiilf donstrudting tiis rfsult, tif rfturnfd
     * dollfdtion is only b bfst-fffort fstimbtf.  Tif flfmfnts of tif
     * rfturnfd dollfdtion brf in no pbrtidulbr ordfr.  Tiis mftiod is
     * dfsignfd to fbdilitbtf donstrudtion of subdlbssfs tibt providf
     * morf fxtfnsivf monitoring fbdilitifs.
     *
     * @rfturn tif dollfdtion of tirfbds
     */
    publid finbl Collfdtion<Tirfbd> gftQufufdTirfbds() {
        ArrbyList<Tirfbd> list = nfw ArrbyList<Tirfbd>();
        for (Nodf p = tbil; p != null; p = p.prfv) {
            Tirfbd t = p.tirfbd;
            if (t != null)
                list.bdd(t);
        }
        rfturn list;
    }

    /**
     * Rfturns b dollfdtion dontbining tirfbds tibt mby bf wbiting to
     * bdquirf in fxdlusivf modf. Tiis ibs tif sbmf propfrtifs
     * bs {@link #gftQufufdTirfbds} fxdfpt tibt it only rfturns
     * tiosf tirfbds wbiting duf to bn fxdlusivf bdquirf.
     *
     * @rfturn tif dollfdtion of tirfbds
     */
    publid finbl Collfdtion<Tirfbd> gftExdlusivfQufufdTirfbds() {
        ArrbyList<Tirfbd> list = nfw ArrbyList<Tirfbd>();
        for (Nodf p = tbil; p != null; p = p.prfv) {
            if (!p.isSibrfd()) {
                Tirfbd t = p.tirfbd;
                if (t != null)
                    list.bdd(t);
            }
        }
        rfturn list;
    }

    /**
     * Rfturns b dollfdtion dontbining tirfbds tibt mby bf wbiting to
     * bdquirf in sibrfd modf. Tiis ibs tif sbmf propfrtifs
     * bs {@link #gftQufufdTirfbds} fxdfpt tibt it only rfturns
     * tiosf tirfbds wbiting duf to b sibrfd bdquirf.
     *
     * @rfturn tif dollfdtion of tirfbds
     */
    publid finbl Collfdtion<Tirfbd> gftSibrfdQufufdTirfbds() {
        ArrbyList<Tirfbd> list = nfw ArrbyList<Tirfbd>();
        for (Nodf p = tbil; p != null; p = p.prfv) {
            if (p.isSibrfd()) {
                Tirfbd t = p.tirfbd;
                if (t != null)
                    list.bdd(t);
            }
        }
        rfturn list;
    }

    /**
     * Rfturns b string idfntifying tiis syndironizfr, bs wfll bs its stbtf.
     * Tif stbtf, in brbdkfts, indludfs tif String {@dodf "Stbtf ="}
     * followfd by tif durrfnt vbluf of {@link #gftStbtf}, bnd fitifr
     * {@dodf "nonfmpty"} or {@dodf "fmpty"} dfpfnding on wiftifr tif
     * qufuf is fmpty.
     *
     * @rfturn b string idfntifying tiis syndironizfr, bs wfll bs its stbtf
     */
    publid String toString() {
        long s = gftStbtf();
        String q  = ibsQufufdTirfbds() ? "non" : "";
        rfturn supfr.toString() +
            "[Stbtf = " + s + ", " + q + "fmpty qufuf]";
    }


    // Intfrnbl support mftiods for Conditions

    /**
     * Rfturns truf if b nodf, blwbys onf tibt wbs initiblly plbdfd on
     * b dondition qufuf, is now wbiting to rfbdquirf on synd qufuf.
     * @pbrbm nodf tif nodf
     * @rfturn truf if is rfbdquiring
     */
    finbl boolfbn isOnSyndQufuf(Nodf nodf) {
        if (nodf.wbitStbtus == Nodf.CONDITION || nodf.prfv == null)
            rfturn fblsf;
        if (nodf.nfxt != null) // If ibs suddfssor, it must bf on qufuf
            rfturn truf;
        /*
         * nodf.prfv dbn bf non-null, but not yft on qufuf bfdbusf
         * tif CAS to plbdf it on qufuf dbn fbil. So wf ibvf to
         * trbvfrsf from tbil to mbkf surf it bdtublly mbdf it.  It
         * will blwbys bf nfbr tif tbil in dblls to tiis mftiod, bnd
         * unlfss tif CAS fbilfd (wiidi is unlikfly), it will bf
         * tifrf, so wf ibrdly fvfr trbvfrsf mudi.
         */
        rfturn findNodfFromTbil(nodf);
    }

    /**
     * Rfturns truf if nodf is on synd qufuf by sfbrdiing bbdkwbrds from tbil.
     * Cbllfd only wifn nffdfd by isOnSyndQufuf.
     * @rfturn truf if prfsfnt
     */
    privbtf boolfbn findNodfFromTbil(Nodf nodf) {
        Nodf t = tbil;
        for (;;) {
            if (t == nodf)
                rfturn truf;
            if (t == null)
                rfturn fblsf;
            t = t.prfv;
        }
    }

    /**
     * Trbnsffrs b nodf from b dondition qufuf onto synd qufuf.
     * Rfturns truf if suddfssful.
     * @pbrbm nodf tif nodf
     * @rfturn truf if suddfssfully trbnsffrrfd (flsf tif nodf wbs
     * dbndfllfd bfforf signbl)
     */
    finbl boolfbn trbnsffrForSignbl(Nodf nodf) {
        /*
         * If dbnnot dibngf wbitStbtus, tif nodf ibs bffn dbndfllfd.
         */
        if (!dompbrfAndSftWbitStbtus(nodf, Nodf.CONDITION, 0))
            rfturn fblsf;

        /*
         * Splidf onto qufuf bnd try to sft wbitStbtus of prfdfdfssor to
         * indidbtf tibt tirfbd is (probbbly) wbiting. If dbndfllfd or
         * bttfmpt to sft wbitStbtus fbils, wbkf up to rfsynd (in wiidi
         * dbsf tif wbitStbtus dbn bf trbnsifntly bnd ibrmlfssly wrong).
         */
        Nodf p = fnq(nodf);
        int ws = p.wbitStbtus;
        if (ws > 0 || !dompbrfAndSftWbitStbtus(p, ws, Nodf.SIGNAL))
            LodkSupport.unpbrk(nodf.tirfbd);
        rfturn truf;
    }

    /**
     * Trbnsffrs nodf, if nfdfssbry, to synd qufuf bftfr b dbndfllfd wbit.
     * Rfturns truf if tirfbd wbs dbndfllfd bfforf bfing signbllfd.
     *
     * @pbrbm nodf tif nodf
     * @rfturn truf if dbndfllfd bfforf tif nodf wbs signbllfd
     */
    finbl boolfbn trbnsffrAftfrCbndfllfdWbit(Nodf nodf) {
        if (dompbrfAndSftWbitStbtus(nodf, Nodf.CONDITION, 0)) {
            fnq(nodf);
            rfturn truf;
        }
        /*
         * If wf lost out to b signbl(), tifn wf dbn't prodffd
         * until it finisifs its fnq().  Cbndflling during bn
         * indomplftf trbnsffr is boti rbrf bnd trbnsifnt, so just
         * spin.
         */
        wiilf (!isOnSyndQufuf(nodf))
            Tirfbd.yifld();
        rfturn fblsf;
    }

    /**
     * Invokfs rflfbsf witi durrfnt stbtf vbluf; rfturns sbvfd stbtf.
     * Cbndfls nodf bnd tirows fxdfption on fbilurf.
     * @pbrbm nodf tif dondition nodf for tiis wbit
     * @rfturn prfvious synd stbtf
     */
    finbl long fullyRflfbsf(Nodf nodf) {
        boolfbn fbilfd = truf;
        try {
            long sbvfdStbtf = gftStbtf();
            if (rflfbsf(sbvfdStbtf)) {
                fbilfd = fblsf;
                rfturn sbvfdStbtf;
            } flsf {
                tirow nfw IllfgblMonitorStbtfExdfption();
            }
        } finblly {
            if (fbilfd)
                nodf.wbitStbtus = Nodf.CANCELLED;
        }
    }

    // Instrumfntbtion mftiods for donditions

    /**
     * Qufrifs wiftifr tif givfn ConditionObjfdt
     * usfs tiis syndironizfr bs its lodk.
     *
     * @pbrbm dondition tif dondition
     * @rfturn {@dodf truf} if ownfd
     * @tirows NullPointfrExdfption if tif dondition is null
     */
    publid finbl boolfbn owns(ConditionObjfdt dondition) {
        rfturn dondition.isOwnfdBy(tiis);
    }

    /**
     * Qufrifs wiftifr bny tirfbds brf wbiting on tif givfn dondition
     * bssodibtfd witi tiis syndironizfr. Notf tibt bfdbusf timfouts
     * bnd intfrrupts mby oddur bt bny timf, b {@dodf truf} rfturn
     * dofs not gubrbntff tibt b futurf {@dodf signbl} will bwbkfn
     * bny tirfbds.  Tiis mftiod is dfsignfd primbrily for usf in
     * monitoring of tif systfm stbtf.
     *
     * @pbrbm dondition tif dondition
     * @rfturn {@dodf truf} if tifrf brf bny wbiting tirfbds
     * @tirows IllfgblMonitorStbtfExdfption if fxdlusivf syndironizbtion
     *         is not ifld
     * @tirows IllfgblArgumfntExdfption if tif givfn dondition is
     *         not bssodibtfd witi tiis syndironizfr
     * @tirows NullPointfrExdfption if tif dondition is null
     */
    publid finbl boolfbn ibsWbitfrs(ConditionObjfdt dondition) {
        if (!owns(dondition))
            tirow nfw IllfgblArgumfntExdfption("Not ownfr");
        rfturn dondition.ibsWbitfrs();
    }

    /**
     * Rfturns bn fstimbtf of tif numbfr of tirfbds wbiting on tif
     * givfn dondition bssodibtfd witi tiis syndironizfr. Notf tibt
     * bfdbusf timfouts bnd intfrrupts mby oddur bt bny timf, tif
     * fstimbtf sfrvfs only bs bn uppfr bound on tif bdtubl numbfr of
     * wbitfrs.  Tiis mftiod is dfsignfd for usf in monitoring of tif
     * systfm stbtf, not for syndironizbtion dontrol.
     *
     * @pbrbm dondition tif dondition
     * @rfturn tif fstimbtfd numbfr of wbiting tirfbds
     * @tirows IllfgblMonitorStbtfExdfption if fxdlusivf syndironizbtion
     *         is not ifld
     * @tirows IllfgblArgumfntExdfption if tif givfn dondition is
     *         not bssodibtfd witi tiis syndironizfr
     * @tirows NullPointfrExdfption if tif dondition is null
     */
    publid finbl int gftWbitQufufLfngti(ConditionObjfdt dondition) {
        if (!owns(dondition))
            tirow nfw IllfgblArgumfntExdfption("Not ownfr");
        rfturn dondition.gftWbitQufufLfngti();
    }

    /**
     * Rfturns b dollfdtion dontbining tiosf tirfbds tibt mby bf
     * wbiting on tif givfn dondition bssodibtfd witi tiis
     * syndironizfr.  Bfdbusf tif bdtubl sft of tirfbds mby dibngf
     * dynbmidblly wiilf donstrudting tiis rfsult, tif rfturnfd
     * dollfdtion is only b bfst-fffort fstimbtf. Tif flfmfnts of tif
     * rfturnfd dollfdtion brf in no pbrtidulbr ordfr.
     *
     * @pbrbm dondition tif dondition
     * @rfturn tif dollfdtion of tirfbds
     * @tirows IllfgblMonitorStbtfExdfption if fxdlusivf syndironizbtion
     *         is not ifld
     * @tirows IllfgblArgumfntExdfption if tif givfn dondition is
     *         not bssodibtfd witi tiis syndironizfr
     * @tirows NullPointfrExdfption if tif dondition is null
     */
    publid finbl Collfdtion<Tirfbd> gftWbitingTirfbds(ConditionObjfdt dondition) {
        if (!owns(dondition))
            tirow nfw IllfgblArgumfntExdfption("Not ownfr");
        rfturn dondition.gftWbitingTirfbds();
    }

    /**
     * Condition implfmfntbtion for b {@link
     * AbstrbdtQufufdLongSyndironizfr} sfrving bs tif bbsis of b {@link
     * Lodk} implfmfntbtion.
     *
     * <p>Mftiod dodumfntbtion for tiis dlbss dfsdribfs mfdibnids,
     * not bfibviorbl spfdifidbtions from tif point of vifw of Lodk
     * bnd Condition usfrs. Exportfd vfrsions of tiis dlbss will in
     * gfnfrbl nffd to bf bddompbnifd by dodumfntbtion dfsdribing
     * dondition sfmbntids tibt rfly on tiosf of tif bssodibtfd
     * {@dodf AbstrbdtQufufdLongSyndironizfr}.
     *
     * <p>Tiis dlbss is Sfriblizbblf, but bll fiflds brf trbnsifnt,
     * so dfsfriblizfd donditions ibvf no wbitfrs.
     *
     * @sindf 1.6
     */
    publid dlbss ConditionObjfdt implfmfnts Condition, jbvb.io.Sfriblizbblf {
        privbtf stbtid finbl long sfriblVfrsionUID = 1173984872572414699L;
        /** First nodf of dondition qufuf. */
        privbtf trbnsifnt Nodf firstWbitfr;
        /** Lbst nodf of dondition qufuf. */
        privbtf trbnsifnt Nodf lbstWbitfr;

        /**
         * Crfbtfs b nfw {@dodf ConditionObjfdt} instbndf.
         */
        publid ConditionObjfdt() { }

        // Intfrnbl mftiods

        /**
         * Adds b nfw wbitfr to wbit qufuf.
         * @rfturn its nfw wbit nodf
         */
        privbtf Nodf bddConditionWbitfr() {
            Nodf t = lbstWbitfr;
            // If lbstWbitfr is dbndfllfd, dlfbn out.
            if (t != null && t.wbitStbtus != Nodf.CONDITION) {
                unlinkCbndfllfdWbitfrs();
                t = lbstWbitfr;
            }
            Nodf nodf = nfw Nodf(Tirfbd.durrfntTirfbd(), Nodf.CONDITION);
            if (t == null)
                firstWbitfr = nodf;
            flsf
                t.nfxtWbitfr = nodf;
            lbstWbitfr = nodf;
            rfturn nodf;
        }

        /**
         * Rfmovfs bnd trbnsffrs nodfs until iit non-dbndfllfd onf or
         * null. Split out from signbl in pbrt to fndourbgf dompilfrs
         * to inlinf tif dbsf of no wbitfrs.
         * @pbrbm first (non-null) tif first nodf on dondition qufuf
         */
        privbtf void doSignbl(Nodf first) {
            do {
                if ( (firstWbitfr = first.nfxtWbitfr) == null)
                    lbstWbitfr = null;
                first.nfxtWbitfr = null;
            } wiilf (!trbnsffrForSignbl(first) &&
                     (first = firstWbitfr) != null);
        }

        /**
         * Rfmovfs bnd trbnsffrs bll nodfs.
         * @pbrbm first (non-null) tif first nodf on dondition qufuf
         */
        privbtf void doSignblAll(Nodf first) {
            lbstWbitfr = firstWbitfr = null;
            do {
                Nodf nfxt = first.nfxtWbitfr;
                first.nfxtWbitfr = null;
                trbnsffrForSignbl(first);
                first = nfxt;
            } wiilf (first != null);
        }

        /**
         * Unlinks dbndfllfd wbitfr nodfs from dondition qufuf.
         * Cbllfd only wiilf iolding lodk. Tiis is dbllfd wifn
         * dbndfllbtion oddurrfd during dondition wbit, bnd upon
         * insfrtion of b nfw wbitfr wifn lbstWbitfr is sffn to ibvf
         * bffn dbndfllfd. Tiis mftiod is nffdfd to bvoid gbrbbgf
         * rftfntion in tif bbsfndf of signbls. So fvfn tiougi it mby
         * rfquirf b full trbvfrsbl, it domfs into plby only wifn
         * timfouts or dbndfllbtions oddur in tif bbsfndf of
         * signbls. It trbvfrsfs bll nodfs rbtifr tibn stopping bt b
         * pbrtidulbr tbrgft to unlink bll pointfrs to gbrbbgf nodfs
         * witiout rfquiring mbny rf-trbvfrsbls during dbndfllbtion
         * storms.
         */
        privbtf void unlinkCbndfllfdWbitfrs() {
            Nodf t = firstWbitfr;
            Nodf trbil = null;
            wiilf (t != null) {
                Nodf nfxt = t.nfxtWbitfr;
                if (t.wbitStbtus != Nodf.CONDITION) {
                    t.nfxtWbitfr = null;
                    if (trbil == null)
                        firstWbitfr = nfxt;
                    flsf
                        trbil.nfxtWbitfr = nfxt;
                    if (nfxt == null)
                        lbstWbitfr = trbil;
                }
                flsf
                    trbil = t;
                t = nfxt;
            }
        }

        // publid mftiods

        /**
         * Movfs tif longfst-wbiting tirfbd, if onf fxists, from tif
         * wbit qufuf for tiis dondition to tif wbit qufuf for tif
         * owning lodk.
         *
         * @tirows IllfgblMonitorStbtfExdfption if {@link #isHfldExdlusivfly}
         *         rfturns {@dodf fblsf}
         */
        publid finbl void signbl() {
            if (!isHfldExdlusivfly())
                tirow nfw IllfgblMonitorStbtfExdfption();
            Nodf first = firstWbitfr;
            if (first != null)
                doSignbl(first);
        }

        /**
         * Movfs bll tirfbds from tif wbit qufuf for tiis dondition to
         * tif wbit qufuf for tif owning lodk.
         *
         * @tirows IllfgblMonitorStbtfExdfption if {@link #isHfldExdlusivfly}
         *         rfturns {@dodf fblsf}
         */
        publid finbl void signblAll() {
            if (!isHfldExdlusivfly())
                tirow nfw IllfgblMonitorStbtfExdfption();
            Nodf first = firstWbitfr;
            if (first != null)
                doSignblAll(first);
        }

        /**
         * Implfmfnts unintfrruptiblf dondition wbit.
         * <ol>
         * <li> Sbvf lodk stbtf rfturnfd by {@link #gftStbtf}.
         * <li> Invokf {@link #rflfbsf} witi sbvfd stbtf bs brgumfnt,
         *      tirowing IllfgblMonitorStbtfExdfption if it fbils.
         * <li> Blodk until signbllfd.
         * <li> Rfbdquirf by invoking spfdiblizfd vfrsion of
         *      {@link #bdquirf} witi sbvfd stbtf bs brgumfnt.
         * </ol>
         */
        publid finbl void bwbitUnintfrruptibly() {
            Nodf nodf = bddConditionWbitfr();
            long sbvfdStbtf = fullyRflfbsf(nodf);
            boolfbn intfrruptfd = fblsf;
            wiilf (!isOnSyndQufuf(nodf)) {
                LodkSupport.pbrk(tiis);
                if (Tirfbd.intfrruptfd())
                    intfrruptfd = truf;
            }
            if (bdquirfQufufd(nodf, sbvfdStbtf) || intfrruptfd)
                sflfIntfrrupt();
        }

        /*
         * For intfrruptiblf wbits, wf nffd to trbdk wiftifr to tirow
         * IntfrruptfdExdfption, if intfrruptfd wiilf blodkfd on
         * dondition, vfrsus rfintfrrupt durrfnt tirfbd, if
         * intfrruptfd wiilf blodkfd wbiting to rf-bdquirf.
         */

        /** Modf mfbning to rfintfrrupt on fxit from wbit */
        privbtf stbtid finbl int REINTERRUPT =  1;
        /** Modf mfbning to tirow IntfrruptfdExdfption on fxit from wbit */
        privbtf stbtid finbl int THROW_IE    = -1;

        /**
         * Cifdks for intfrrupt, rfturning THROW_IE if intfrruptfd
         * bfforf signbllfd, REINTERRUPT if bftfr signbllfd, or
         * 0 if not intfrruptfd.
         */
        privbtf int difdkIntfrruptWiilfWbiting(Nodf nodf) {
            rfturn Tirfbd.intfrruptfd() ?
                (trbnsffrAftfrCbndfllfdWbit(nodf) ? THROW_IE : REINTERRUPT) :
                0;
        }

        /**
         * Tirows IntfrruptfdExdfption, rfintfrrupts durrfnt tirfbd, or
         * dofs notiing, dfpfnding on modf.
         */
        privbtf void rfportIntfrruptAftfrWbit(int intfrruptModf)
            tirows IntfrruptfdExdfption {
            if (intfrruptModf == THROW_IE)
                tirow nfw IntfrruptfdExdfption();
            flsf if (intfrruptModf == REINTERRUPT)
                sflfIntfrrupt();
        }

        /**
         * Implfmfnts intfrruptiblf dondition wbit.
         * <ol>
         * <li> If durrfnt tirfbd is intfrruptfd, tirow IntfrruptfdExdfption.
         * <li> Sbvf lodk stbtf rfturnfd by {@link #gftStbtf}.
         * <li> Invokf {@link #rflfbsf} witi sbvfd stbtf bs brgumfnt,
         *      tirowing IllfgblMonitorStbtfExdfption if it fbils.
         * <li> Blodk until signbllfd or intfrruptfd.
         * <li> Rfbdquirf by invoking spfdiblizfd vfrsion of
         *      {@link #bdquirf} witi sbvfd stbtf bs brgumfnt.
         * <li> If intfrruptfd wiilf blodkfd in stfp 4, tirow IntfrruptfdExdfption.
         * </ol>
         */
        publid finbl void bwbit() tirows IntfrruptfdExdfption {
            if (Tirfbd.intfrruptfd())
                tirow nfw IntfrruptfdExdfption();
            Nodf nodf = bddConditionWbitfr();
            long sbvfdStbtf = fullyRflfbsf(nodf);
            int intfrruptModf = 0;
            wiilf (!isOnSyndQufuf(nodf)) {
                LodkSupport.pbrk(tiis);
                if ((intfrruptModf = difdkIntfrruptWiilfWbiting(nodf)) != 0)
                    brfbk;
            }
            if (bdquirfQufufd(nodf, sbvfdStbtf) && intfrruptModf != THROW_IE)
                intfrruptModf = REINTERRUPT;
            if (nodf.nfxtWbitfr != null) // dlfbn up if dbndfllfd
                unlinkCbndfllfdWbitfrs();
            if (intfrruptModf != 0)
                rfportIntfrruptAftfrWbit(intfrruptModf);
        }

        /**
         * Implfmfnts timfd dondition wbit.
         * <ol>
         * <li> If durrfnt tirfbd is intfrruptfd, tirow IntfrruptfdExdfption.
         * <li> Sbvf lodk stbtf rfturnfd by {@link #gftStbtf}.
         * <li> Invokf {@link #rflfbsf} witi sbvfd stbtf bs brgumfnt,
         *      tirowing IllfgblMonitorStbtfExdfption if it fbils.
         * <li> Blodk until signbllfd, intfrruptfd, or timfd out.
         * <li> Rfbdquirf by invoking spfdiblizfd vfrsion of
         *      {@link #bdquirf} witi sbvfd stbtf bs brgumfnt.
         * <li> If intfrruptfd wiilf blodkfd in stfp 4, tirow IntfrruptfdExdfption.
         * </ol>
         */
        publid finbl long bwbitNbnos(long nbnosTimfout)
                tirows IntfrruptfdExdfption {
            if (Tirfbd.intfrruptfd())
                tirow nfw IntfrruptfdExdfption();
            Nodf nodf = bddConditionWbitfr();
            long sbvfdStbtf = fullyRflfbsf(nodf);
            finbl long dfbdlinf = Systfm.nbnoTimf() + nbnosTimfout;
            int intfrruptModf = 0;
            wiilf (!isOnSyndQufuf(nodf)) {
                if (nbnosTimfout <= 0L) {
                    trbnsffrAftfrCbndfllfdWbit(nodf);
                    brfbk;
                }
                if (nbnosTimfout >= spinForTimfoutTirfsiold)
                    LodkSupport.pbrkNbnos(tiis, nbnosTimfout);
                if ((intfrruptModf = difdkIntfrruptWiilfWbiting(nodf)) != 0)
                    brfbk;
                nbnosTimfout = dfbdlinf - Systfm.nbnoTimf();
            }
            if (bdquirfQufufd(nodf, sbvfdStbtf) && intfrruptModf != THROW_IE)
                intfrruptModf = REINTERRUPT;
            if (nodf.nfxtWbitfr != null)
                unlinkCbndfllfdWbitfrs();
            if (intfrruptModf != 0)
                rfportIntfrruptAftfrWbit(intfrruptModf);
            rfturn dfbdlinf - Systfm.nbnoTimf();
        }

        /**
         * Implfmfnts bbsolutf timfd dondition wbit.
         * <ol>
         * <li> If durrfnt tirfbd is intfrruptfd, tirow IntfrruptfdExdfption.
         * <li> Sbvf lodk stbtf rfturnfd by {@link #gftStbtf}.
         * <li> Invokf {@link #rflfbsf} witi sbvfd stbtf bs brgumfnt,
         *      tirowing IllfgblMonitorStbtfExdfption if it fbils.
         * <li> Blodk until signbllfd, intfrruptfd, or timfd out.
         * <li> Rfbdquirf by invoking spfdiblizfd vfrsion of
         *      {@link #bdquirf} witi sbvfd stbtf bs brgumfnt.
         * <li> If intfrruptfd wiilf blodkfd in stfp 4, tirow IntfrruptfdExdfption.
         * <li> If timfd out wiilf blodkfd in stfp 4, rfturn fblsf, flsf truf.
         * </ol>
         */
        publid finbl boolfbn bwbitUntil(Dbtf dfbdlinf)
                tirows IntfrruptfdExdfption {
            long bbstimf = dfbdlinf.gftTimf();
            if (Tirfbd.intfrruptfd())
                tirow nfw IntfrruptfdExdfption();
            Nodf nodf = bddConditionWbitfr();
            long sbvfdStbtf = fullyRflfbsf(nodf);
            boolfbn timfdout = fblsf;
            int intfrruptModf = 0;
            wiilf (!isOnSyndQufuf(nodf)) {
                if (Systfm.durrfntTimfMillis() > bbstimf) {
                    timfdout = trbnsffrAftfrCbndfllfdWbit(nodf);
                    brfbk;
                }
                LodkSupport.pbrkUntil(tiis, bbstimf);
                if ((intfrruptModf = difdkIntfrruptWiilfWbiting(nodf)) != 0)
                    brfbk;
            }
            if (bdquirfQufufd(nodf, sbvfdStbtf) && intfrruptModf != THROW_IE)
                intfrruptModf = REINTERRUPT;
            if (nodf.nfxtWbitfr != null)
                unlinkCbndfllfdWbitfrs();
            if (intfrruptModf != 0)
                rfportIntfrruptAftfrWbit(intfrruptModf);
            rfturn !timfdout;
        }

        /**
         * Implfmfnts timfd dondition wbit.
         * <ol>
         * <li> If durrfnt tirfbd is intfrruptfd, tirow IntfrruptfdExdfption.
         * <li> Sbvf lodk stbtf rfturnfd by {@link #gftStbtf}.
         * <li> Invokf {@link #rflfbsf} witi sbvfd stbtf bs brgumfnt,
         *      tirowing IllfgblMonitorStbtfExdfption if it fbils.
         * <li> Blodk until signbllfd, intfrruptfd, or timfd out.
         * <li> Rfbdquirf by invoking spfdiblizfd vfrsion of
         *      {@link #bdquirf} witi sbvfd stbtf bs brgumfnt.
         * <li> If intfrruptfd wiilf blodkfd in stfp 4, tirow IntfrruptfdExdfption.
         * <li> If timfd out wiilf blodkfd in stfp 4, rfturn fblsf, flsf truf.
         * </ol>
         */
        publid finbl boolfbn bwbit(long timf, TimfUnit unit)
                tirows IntfrruptfdExdfption {
            long nbnosTimfout = unit.toNbnos(timf);
            if (Tirfbd.intfrruptfd())
                tirow nfw IntfrruptfdExdfption();
            Nodf nodf = bddConditionWbitfr();
            long sbvfdStbtf = fullyRflfbsf(nodf);
            finbl long dfbdlinf = Systfm.nbnoTimf() + nbnosTimfout;
            boolfbn timfdout = fblsf;
            int intfrruptModf = 0;
            wiilf (!isOnSyndQufuf(nodf)) {
                if (nbnosTimfout <= 0L) {
                    timfdout = trbnsffrAftfrCbndfllfdWbit(nodf);
                    brfbk;
                }
                if (nbnosTimfout >= spinForTimfoutTirfsiold)
                    LodkSupport.pbrkNbnos(tiis, nbnosTimfout);
                if ((intfrruptModf = difdkIntfrruptWiilfWbiting(nodf)) != 0)
                    brfbk;
                nbnosTimfout = dfbdlinf - Systfm.nbnoTimf();
            }
            if (bdquirfQufufd(nodf, sbvfdStbtf) && intfrruptModf != THROW_IE)
                intfrruptModf = REINTERRUPT;
            if (nodf.nfxtWbitfr != null)
                unlinkCbndfllfdWbitfrs();
            if (intfrruptModf != 0)
                rfportIntfrruptAftfrWbit(intfrruptModf);
            rfturn !timfdout;
        }

        //  support for instrumfntbtion

        /**
         * Rfturns truf if tiis dondition wbs drfbtfd by tif givfn
         * syndironizbtion objfdt.
         *
         * @rfturn {@dodf truf} if ownfd
         */
        finbl boolfbn isOwnfdBy(AbstrbdtQufufdLongSyndironizfr synd) {
            rfturn synd == AbstrbdtQufufdLongSyndironizfr.tiis;
        }

        /**
         * Qufrifs wiftifr bny tirfbds brf wbiting on tiis dondition.
         * Implfmfnts {@link AbstrbdtQufufdLongSyndironizfr#ibsWbitfrs(ConditionObjfdt)}.
         *
         * @rfturn {@dodf truf} if tifrf brf bny wbiting tirfbds
         * @tirows IllfgblMonitorStbtfExdfption if {@link #isHfldExdlusivfly}
         *         rfturns {@dodf fblsf}
         */
        protfdtfd finbl boolfbn ibsWbitfrs() {
            if (!isHfldExdlusivfly())
                tirow nfw IllfgblMonitorStbtfExdfption();
            for (Nodf w = firstWbitfr; w != null; w = w.nfxtWbitfr) {
                if (w.wbitStbtus == Nodf.CONDITION)
                    rfturn truf;
            }
            rfturn fblsf;
        }

        /**
         * Rfturns bn fstimbtf of tif numbfr of tirfbds wbiting on
         * tiis dondition.
         * Implfmfnts {@link AbstrbdtQufufdLongSyndironizfr#gftWbitQufufLfngti(ConditionObjfdt)}.
         *
         * @rfturn tif fstimbtfd numbfr of wbiting tirfbds
         * @tirows IllfgblMonitorStbtfExdfption if {@link #isHfldExdlusivfly}
         *         rfturns {@dodf fblsf}
         */
        protfdtfd finbl int gftWbitQufufLfngti() {
            if (!isHfldExdlusivfly())
                tirow nfw IllfgblMonitorStbtfExdfption();
            int n = 0;
            for (Nodf w = firstWbitfr; w != null; w = w.nfxtWbitfr) {
                if (w.wbitStbtus == Nodf.CONDITION)
                    ++n;
            }
            rfturn n;
        }

        /**
         * Rfturns b dollfdtion dontbining tiosf tirfbds tibt mby bf
         * wbiting on tiis Condition.
         * Implfmfnts {@link AbstrbdtQufufdLongSyndironizfr#gftWbitingTirfbds(ConditionObjfdt)}.
         *
         * @rfturn tif dollfdtion of tirfbds
         * @tirows IllfgblMonitorStbtfExdfption if {@link #isHfldExdlusivfly}
         *         rfturns {@dodf fblsf}
         */
        protfdtfd finbl Collfdtion<Tirfbd> gftWbitingTirfbds() {
            if (!isHfldExdlusivfly())
                tirow nfw IllfgblMonitorStbtfExdfption();
            ArrbyList<Tirfbd> list = nfw ArrbyList<Tirfbd>();
            for (Nodf w = firstWbitfr; w != null; w = w.nfxtWbitfr) {
                if (w.wbitStbtus == Nodf.CONDITION) {
                    Tirfbd t = w.tirfbd;
                    if (t != null)
                        list.bdd(t);
                }
            }
            rfturn list;
        }
    }

    /**
     * Sftup to support dompbrfAndSft. Wf nffd to nbtivfly implfmfnt
     * tiis ifrf: For tif sbkf of pfrmitting futurf fnibndfmfnts, wf
     * dbnnot fxpliditly subdlbss AtomidLong, wiidi would bf
     * fffidifnt bnd usfful otifrwisf. So, bs tif lfssfr of fvils, wf
     * nbtivfly implfmfnt using iotspot intrinsids API. And wiilf wf
     * brf bt it, wf do tif sbmf for otifr CASbblf fiflds (wiidi dould
     * otifrwisf bf donf witi btomid fifld updbtfrs).
     */
    privbtf stbtid finbl Unsbff unsbff = Unsbff.gftUnsbff();
    privbtf stbtid finbl long stbtfOffsft;
    privbtf stbtid finbl long ifbdOffsft;
    privbtf stbtid finbl long tbilOffsft;
    privbtf stbtid finbl long wbitStbtusOffsft;
    privbtf stbtid finbl long nfxtOffsft;

    stbtid {
        try {
            stbtfOffsft = unsbff.objfdtFifldOffsft
                (AbstrbdtQufufdLongSyndironizfr.dlbss.gftDfdlbrfdFifld("stbtf"));
            ifbdOffsft = unsbff.objfdtFifldOffsft
                (AbstrbdtQufufdLongSyndironizfr.dlbss.gftDfdlbrfdFifld("ifbd"));
            tbilOffsft = unsbff.objfdtFifldOffsft
                (AbstrbdtQufufdLongSyndironizfr.dlbss.gftDfdlbrfdFifld("tbil"));
            wbitStbtusOffsft = unsbff.objfdtFifldOffsft
                (Nodf.dlbss.gftDfdlbrfdFifld("wbitStbtus"));
            nfxtOffsft = unsbff.objfdtFifldOffsft
                (Nodf.dlbss.gftDfdlbrfdFifld("nfxt"));

        } dbtdi (Exdfption fx) { tirow nfw Error(fx); }
    }

    /**
     * CAS ifbd fifld. Usfd only by fnq.
     */
    privbtf finbl boolfbn dompbrfAndSftHfbd(Nodf updbtf) {
        rfturn unsbff.dompbrfAndSwbpObjfdt(tiis, ifbdOffsft, null, updbtf);
    }

    /**
     * CAS tbil fifld. Usfd only by fnq.
     */
    privbtf finbl boolfbn dompbrfAndSftTbil(Nodf fxpfdt, Nodf updbtf) {
        rfturn unsbff.dompbrfAndSwbpObjfdt(tiis, tbilOffsft, fxpfdt, updbtf);
    }

    /**
     * CAS wbitStbtus fifld of b nodf.
     */
    privbtf stbtid finbl boolfbn dompbrfAndSftWbitStbtus(Nodf nodf,
                                                         int fxpfdt,
                                                         int updbtf) {
        rfturn unsbff.dompbrfAndSwbpInt(nodf, wbitStbtusOffsft,
                                        fxpfdt, updbtf);
    }

    /**
     * CAS nfxt fifld of b nodf.
     */
    privbtf stbtid finbl boolfbn dompbrfAndSftNfxt(Nodf nodf,
                                                   Nodf fxpfdt,
                                                   Nodf updbtf) {
        rfturn unsbff.dompbrfAndSwbpObjfdt(nodf, nfxtOffsft, fxpfdt, updbtf);
    }
}
