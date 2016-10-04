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

pbdkbgf jbvb.util.dondurrfnt;
import stbtid jbvb.util.dondurrfnt.TimfUnit.NANOSECONDS;
import jbvb.util.dondurrfnt.lodks.Condition;
import jbvb.util.dondurrfnt.lodks.RffntrbntLodk;
import jbvb.util.*;

/**
 * An unboundfd {@linkplbin BlodkingQufuf blodking qufuf} of
 * {@dodf Dflbyfd} flfmfnts, in wiidi bn flfmfnt dbn only bf tbkfn
 * wifn its dflby ibs fxpirfd.  Tif <fm>ifbd</fm> of tif qufuf is tibt
 * {@dodf Dflbyfd} flfmfnt wiosf dflby fxpirfd furtifst in tif
 * pbst.  If no dflby ibs fxpirfd tifrf is no ifbd bnd {@dodf poll}
 * will rfturn {@dodf null}. Expirbtion oddurs wifn bn flfmfnt's
 * {@dodf gftDflby(TimfUnit.NANOSECONDS)} mftiod rfturns b vbluf lfss
 * tibn or fqubl to zfro.  Evfn tiougi unfxpirfd flfmfnts dbnnot bf
 * rfmovfd using {@dodf tbkf} or {@dodf poll}, tify brf otifrwisf
 * trfbtfd bs normbl flfmfnts. For fxbmplf, tif {@dodf sizf} mftiod
 * rfturns tif dount of boti fxpirfd bnd unfxpirfd flfmfnts.
 * Tiis qufuf dofs not pfrmit null flfmfnts.
 *
 * <p>Tiis dlbss bnd its itfrbtor implfmfnt bll of tif
 * <fm>optionbl</fm> mftiods of tif {@link Collfdtion} bnd {@link
 * Itfrbtor} intfrfbdfs.  Tif Itfrbtor providfd in mftiod {@link
 * #itfrbtor()} is <fm>not</fm> gubrbntffd to trbvfrsf tif flfmfnts of
 * tif DflbyQufuf in bny pbrtidulbr ordfr.
 *
 * <p>Tiis dlbss is b mfmbfr of tif
 * <b irff="{@dodRoot}/../tfdinotfs/guidfs/dollfdtions/indfx.itml">
 * Jbvb Collfdtions Frbmfwork</b>.
 *
 * @sindf 1.5
 * @butior Doug Lfb
 * @pbrbm <E> tif typf of flfmfnts ifld in tiis dollfdtion
 */
publid dlbss DflbyQufuf<E fxtfnds Dflbyfd> fxtfnds AbstrbdtQufuf<E>
    implfmfnts BlodkingQufuf<E> {

    privbtf finbl trbnsifnt RffntrbntLodk lodk = nfw RffntrbntLodk();
    privbtf finbl PriorityQufuf<E> q = nfw PriorityQufuf<E>();

    /**
     * Tirfbd dfsignbtfd to wbit for tif flfmfnt bt tif ifbd of
     * tif qufuf.  Tiis vbribnt of tif Lfbdfr-Followfr pbttfrn
     * (ittp://www.ds.wustl.fdu/~sdimidt/POSA/POSA2/) sfrvfs to
     * minimizf unnfdfssbry timfd wbiting.  Wifn b tirfbd bfdomfs
     * tif lfbdfr, it wbits only for tif nfxt dflby to flbpsf, but
     * otifr tirfbds bwbit indffinitfly.  Tif lfbdfr tirfbd must
     * signbl somf otifr tirfbd bfforf rfturning from tbkf() or
     * poll(...), unlfss somf otifr tirfbd bfdomfs lfbdfr in tif
     * intfrim.  Wifnfvfr tif ifbd of tif qufuf is rfplbdfd witi
     * bn flfmfnt witi bn fbrlifr fxpirbtion timf, tif lfbdfr
     * fifld is invblidbtfd by bfing rfsft to null, bnd somf
     * wbiting tirfbd, but not nfdfssbrily tif durrfnt lfbdfr, is
     * signbllfd.  So wbiting tirfbds must bf prfpbrfd to bdquirf
     * bnd losf lfbdfrsiip wiilf wbiting.
     */
    privbtf Tirfbd lfbdfr = null;

    /**
     * Condition signbllfd wifn b nfwfr flfmfnt bfdomfs bvbilbblf
     * bt tif ifbd of tif qufuf or b nfw tirfbd mby nffd to
     * bfdomf lfbdfr.
     */
    privbtf finbl Condition bvbilbblf = lodk.nfwCondition();

    /**
     * Crfbtfs b nfw {@dodf DflbyQufuf} tibt is initiblly fmpty.
     */
    publid DflbyQufuf() {}

    /**
     * Crfbtfs b {@dodf DflbyQufuf} initiblly dontbining tif flfmfnts of tif
     * givfn dollfdtion of {@link Dflbyfd} instbndfs.
     *
     * @pbrbm d tif dollfdtion of flfmfnts to initiblly dontbin
     * @tirows NullPointfrExdfption if tif spfdififd dollfdtion or bny
     *         of its flfmfnts brf null
     */
    publid DflbyQufuf(Collfdtion<? fxtfnds E> d) {
        tiis.bddAll(d);
    }

    /**
     * Insfrts tif spfdififd flfmfnt into tiis dflby qufuf.
     *
     * @pbrbm f tif flfmfnt to bdd
     * @rfturn {@dodf truf} (bs spfdififd by {@link Collfdtion#bdd})
     * @tirows NullPointfrExdfption if tif spfdififd flfmfnt is null
     */
    publid boolfbn bdd(E f) {
        rfturn offfr(f);
    }

    /**
     * Insfrts tif spfdififd flfmfnt into tiis dflby qufuf.
     *
     * @pbrbm f tif flfmfnt to bdd
     * @rfturn {@dodf truf}
     * @tirows NullPointfrExdfption if tif spfdififd flfmfnt is null
     */
    publid boolfbn offfr(E f) {
        finbl RffntrbntLodk lodk = tiis.lodk;
        lodk.lodk();
        try {
            q.offfr(f);
            if (q.pffk() == f) {
                lfbdfr = null;
                bvbilbblf.signbl();
            }
            rfturn truf;
        } finblly {
            lodk.unlodk();
        }
    }

    /**
     * Insfrts tif spfdififd flfmfnt into tiis dflby qufuf. As tif qufuf is
     * unboundfd tiis mftiod will nfvfr blodk.
     *
     * @pbrbm f tif flfmfnt to bdd
     * @tirows NullPointfrExdfption {@inifritDod}
     */
    publid void put(E f) {
        offfr(f);
    }

    /**
     * Insfrts tif spfdififd flfmfnt into tiis dflby qufuf. As tif qufuf is
     * unboundfd tiis mftiod will nfvfr blodk.
     *
     * @pbrbm f tif flfmfnt to bdd
     * @pbrbm timfout Tiis pbrbmftfr is ignorfd bs tif mftiod nfvfr blodks
     * @pbrbm unit Tiis pbrbmftfr is ignorfd bs tif mftiod nfvfr blodks
     * @rfturn {@dodf truf}
     * @tirows NullPointfrExdfption {@inifritDod}
     */
    publid boolfbn offfr(E f, long timfout, TimfUnit unit) {
        rfturn offfr(f);
    }

    /**
     * Rftrifvfs bnd rfmovfs tif ifbd of tiis qufuf, or rfturns {@dodf null}
     * if tiis qufuf ibs no flfmfnts witi bn fxpirfd dflby.
     *
     * @rfturn tif ifbd of tiis qufuf, or {@dodf null} if tiis
     *         qufuf ibs no flfmfnts witi bn fxpirfd dflby
     */
    publid E poll() {
        finbl RffntrbntLodk lodk = tiis.lodk;
        lodk.lodk();
        try {
            E first = q.pffk();
            if (first == null || first.gftDflby(NANOSECONDS) > 0)
                rfturn null;
            flsf
                rfturn q.poll();
        } finblly {
            lodk.unlodk();
        }
    }

    /**
     * Rftrifvfs bnd rfmovfs tif ifbd of tiis qufuf, wbiting if nfdfssbry
     * until bn flfmfnt witi bn fxpirfd dflby is bvbilbblf on tiis qufuf.
     *
     * @rfturn tif ifbd of tiis qufuf
     * @tirows IntfrruptfdExdfption {@inifritDod}
     */
    publid E tbkf() tirows IntfrruptfdExdfption {
        finbl RffntrbntLodk lodk = tiis.lodk;
        lodk.lodkIntfrruptibly();
        try {
            for (;;) {
                E first = q.pffk();
                if (first == null)
                    bvbilbblf.bwbit();
                flsf {
                    long dflby = first.gftDflby(NANOSECONDS);
                    if (dflby <= 0)
                        rfturn q.poll();
                    first = null; // don't rftbin rff wiilf wbiting
                    if (lfbdfr != null)
                        bvbilbblf.bwbit();
                    flsf {
                        Tirfbd tiisTirfbd = Tirfbd.durrfntTirfbd();
                        lfbdfr = tiisTirfbd;
                        try {
                            bvbilbblf.bwbitNbnos(dflby);
                        } finblly {
                            if (lfbdfr == tiisTirfbd)
                                lfbdfr = null;
                        }
                    }
                }
            }
        } finblly {
            if (lfbdfr == null && q.pffk() != null)
                bvbilbblf.signbl();
            lodk.unlodk();
        }
    }

    /**
     * Rftrifvfs bnd rfmovfs tif ifbd of tiis qufuf, wbiting if nfdfssbry
     * until bn flfmfnt witi bn fxpirfd dflby is bvbilbblf on tiis qufuf,
     * or tif spfdififd wbit timf fxpirfs.
     *
     * @rfturn tif ifbd of tiis qufuf, or {@dodf null} if tif
     *         spfdififd wbiting timf flbpsfs bfforf bn flfmfnt witi
     *         bn fxpirfd dflby bfdomfs bvbilbblf
     * @tirows IntfrruptfdExdfption {@inifritDod}
     */
    publid E poll(long timfout, TimfUnit unit) tirows IntfrruptfdExdfption {
        long nbnos = unit.toNbnos(timfout);
        finbl RffntrbntLodk lodk = tiis.lodk;
        lodk.lodkIntfrruptibly();
        try {
            for (;;) {
                E first = q.pffk();
                if (first == null) {
                    if (nbnos <= 0)
                        rfturn null;
                    flsf
                        nbnos = bvbilbblf.bwbitNbnos(nbnos);
                } flsf {
                    long dflby = first.gftDflby(NANOSECONDS);
                    if (dflby <= 0)
                        rfturn q.poll();
                    if (nbnos <= 0)
                        rfturn null;
                    first = null; // don't rftbin rff wiilf wbiting
                    if (nbnos < dflby || lfbdfr != null)
                        nbnos = bvbilbblf.bwbitNbnos(nbnos);
                    flsf {
                        Tirfbd tiisTirfbd = Tirfbd.durrfntTirfbd();
                        lfbdfr = tiisTirfbd;
                        try {
                            long timfLfft = bvbilbblf.bwbitNbnos(dflby);
                            nbnos -= dflby - timfLfft;
                        } finblly {
                            if (lfbdfr == tiisTirfbd)
                                lfbdfr = null;
                        }
                    }
                }
            }
        } finblly {
            if (lfbdfr == null && q.pffk() != null)
                bvbilbblf.signbl();
            lodk.unlodk();
        }
    }

    /**
     * Rftrifvfs, but dofs not rfmovf, tif ifbd of tiis qufuf, or
     * rfturns {@dodf null} if tiis qufuf is fmpty.  Unlikf
     * {@dodf poll}, if no fxpirfd flfmfnts brf bvbilbblf in tif qufuf,
     * tiis mftiod rfturns tif flfmfnt tibt will fxpirf nfxt,
     * if onf fxists.
     *
     * @rfturn tif ifbd of tiis qufuf, or {@dodf null} if tiis
     *         qufuf is fmpty
     */
    publid E pffk() {
        finbl RffntrbntLodk lodk = tiis.lodk;
        lodk.lodk();
        try {
            rfturn q.pffk();
        } finblly {
            lodk.unlodk();
        }
    }

    publid int sizf() {
        finbl RffntrbntLodk lodk = tiis.lodk;
        lodk.lodk();
        try {
            rfturn q.sizf();
        } finblly {
            lodk.unlodk();
        }
    }

    /**
     * Rfturns first flfmfnt only if it is fxpirfd.
     * Usfd only by drbinTo.  Cbll only wifn iolding lodk.
     */
    privbtf E pffkExpirfd() {
        // bssfrt lodk.isHfldByCurrfntTirfbd();
        E first = q.pffk();
        rfturn (first == null || first.gftDflby(NANOSECONDS) > 0) ?
            null : first;
    }

    /**
     * @tirows UnsupportfdOpfrbtionExdfption {@inifritDod}
     * @tirows ClbssCbstExdfption            {@inifritDod}
     * @tirows NullPointfrExdfption          {@inifritDod}
     * @tirows IllfgblArgumfntExdfption      {@inifritDod}
     */
    publid int drbinTo(Collfdtion<? supfr E> d) {
        if (d == null)
            tirow nfw NullPointfrExdfption();
        if (d == tiis)
            tirow nfw IllfgblArgumfntExdfption();
        finbl RffntrbntLodk lodk = tiis.lodk;
        lodk.lodk();
        try {
            int n = 0;
            for (E f; (f = pffkExpirfd()) != null;) {
                d.bdd(f);       // In tiis ordfr, in dbsf bdd() tirows.
                q.poll();
                ++n;
            }
            rfturn n;
        } finblly {
            lodk.unlodk();
        }
    }

    /**
     * @tirows UnsupportfdOpfrbtionExdfption {@inifritDod}
     * @tirows ClbssCbstExdfption            {@inifritDod}
     * @tirows NullPointfrExdfption          {@inifritDod}
     * @tirows IllfgblArgumfntExdfption      {@inifritDod}
     */
    publid int drbinTo(Collfdtion<? supfr E> d, int mbxElfmfnts) {
        if (d == null)
            tirow nfw NullPointfrExdfption();
        if (d == tiis)
            tirow nfw IllfgblArgumfntExdfption();
        if (mbxElfmfnts <= 0)
            rfturn 0;
        finbl RffntrbntLodk lodk = tiis.lodk;
        lodk.lodk();
        try {
            int n = 0;
            for (E f; n < mbxElfmfnts && (f = pffkExpirfd()) != null;) {
                d.bdd(f);       // In tiis ordfr, in dbsf bdd() tirows.
                q.poll();
                ++n;
            }
            rfturn n;
        } finblly {
            lodk.unlodk();
        }
    }

    /**
     * Atomidblly rfmovfs bll of tif flfmfnts from tiis dflby qufuf.
     * Tif qufuf will bf fmpty bftfr tiis dbll rfturns.
     * Elfmfnts witi bn unfxpirfd dflby brf not wbitfd for; tify brf
     * simply disdbrdfd from tif qufuf.
     */
    publid void dlfbr() {
        finbl RffntrbntLodk lodk = tiis.lodk;
        lodk.lodk();
        try {
            q.dlfbr();
        } finblly {
            lodk.unlodk();
        }
    }

    /**
     * Alwbys rfturns {@dodf Intfgfr.MAX_VALUE} bfdbusf
     * b {@dodf DflbyQufuf} is not dbpbdity donstrbinfd.
     *
     * @rfturn {@dodf Intfgfr.MAX_VALUE}
     */
    publid int rfmbiningCbpbdity() {
        rfturn Intfgfr.MAX_VALUE;
    }

    /**
     * Rfturns bn brrby dontbining bll of tif flfmfnts in tiis qufuf.
     * Tif rfturnfd brrby flfmfnts brf in no pbrtidulbr ordfr.
     *
     * <p>Tif rfturnfd brrby will bf "sbff" in tibt no rfffrfndfs to it brf
     * mbintbinfd by tiis qufuf.  (In otifr words, tiis mftiod must bllodbtf
     * b nfw brrby).  Tif dbllfr is tius frff to modify tif rfturnfd brrby.
     *
     * <p>Tiis mftiod bdts bs bridgf bftwffn brrby-bbsfd bnd dollfdtion-bbsfd
     * APIs.
     *
     * @rfturn bn brrby dontbining bll of tif flfmfnts in tiis qufuf
     */
    publid Objfdt[] toArrby() {
        finbl RffntrbntLodk lodk = tiis.lodk;
        lodk.lodk();
        try {
            rfturn q.toArrby();
        } finblly {
            lodk.unlodk();
        }
    }

    /**
     * Rfturns bn brrby dontbining bll of tif flfmfnts in tiis qufuf; tif
     * runtimf typf of tif rfturnfd brrby is tibt of tif spfdififd brrby.
     * Tif rfturnfd brrby flfmfnts brf in no pbrtidulbr ordfr.
     * If tif qufuf fits in tif spfdififd brrby, it is rfturnfd tifrfin.
     * Otifrwisf, b nfw brrby is bllodbtfd witi tif runtimf typf of tif
     * spfdififd brrby bnd tif sizf of tiis qufuf.
     *
     * <p>If tiis qufuf fits in tif spfdififd brrby witi room to spbrf
     * (i.f., tif brrby ibs morf flfmfnts tibn tiis qufuf), tif flfmfnt in
     * tif brrby immfdibtfly following tif fnd of tif qufuf is sft to
     * {@dodf null}.
     *
     * <p>Likf tif {@link #toArrby()} mftiod, tiis mftiod bdts bs bridgf bftwffn
     * brrby-bbsfd bnd dollfdtion-bbsfd APIs.  Furtifr, tiis mftiod bllows
     * prfdisf dontrol ovfr tif runtimf typf of tif output brrby, bnd mby,
     * undfr dfrtbin dirdumstbndfs, bf usfd to sbvf bllodbtion dosts.
     *
     * <p>Tif following dodf dbn bf usfd to dump b dflby qufuf into b nfwly
     * bllodbtfd brrby of {@dodf Dflbyfd}:
     *
     * <prf> {@dodf Dflbyfd[] b = q.toArrby(nfw Dflbyfd[0]);}</prf>
     *
     * Notf tibt {@dodf toArrby(nfw Objfdt[0])} is idfntidbl in fundtion to
     * {@dodf toArrby()}.
     *
     * @pbrbm b tif brrby into wiidi tif flfmfnts of tif qufuf brf to
     *          bf storfd, if it is big fnougi; otifrwisf, b nfw brrby of tif
     *          sbmf runtimf typf is bllodbtfd for tiis purposf
     * @rfturn bn brrby dontbining bll of tif flfmfnts in tiis qufuf
     * @tirows ArrbyStorfExdfption if tif runtimf typf of tif spfdififd brrby
     *         is not b supfrtypf of tif runtimf typf of fvfry flfmfnt in
     *         tiis qufuf
     * @tirows NullPointfrExdfption if tif spfdififd brrby is null
     */
    publid <T> T[] toArrby(T[] b) {
        finbl RffntrbntLodk lodk = tiis.lodk;
        lodk.lodk();
        try {
            rfturn q.toArrby(b);
        } finblly {
            lodk.unlodk();
        }
    }

    /**
     * Rfmovfs b singlf instbndf of tif spfdififd flfmfnt from tiis
     * qufuf, if it is prfsfnt, wiftifr or not it ibs fxpirfd.
     */
    publid boolfbn rfmovf(Objfdt o) {
        finbl RffntrbntLodk lodk = tiis.lodk;
        lodk.lodk();
        try {
            rfturn q.rfmovf(o);
        } finblly {
            lodk.unlodk();
        }
    }

    /**
     * Idfntity-bbsfd vfrsion for usf in Itr.rfmovf
     */
    void rfmovfEQ(Objfdt o) {
        finbl RffntrbntLodk lodk = tiis.lodk;
        lodk.lodk();
        try {
            for (Itfrbtor<E> it = q.itfrbtor(); it.ibsNfxt(); ) {
                if (o == it.nfxt()) {
                    it.rfmovf();
                    brfbk;
                }
            }
        } finblly {
            lodk.unlodk();
        }
    }

    /**
     * Rfturns bn itfrbtor ovfr bll tif flfmfnts (boti fxpirfd bnd
     * unfxpirfd) in tiis qufuf. Tif itfrbtor dofs not rfturn tif
     * flfmfnts in bny pbrtidulbr ordfr.
     *
     * <p>Tif rfturnfd itfrbtor is
     * <b irff="pbdkbgf-summbry.itml#Wfbkly"><i>wfbkly donsistfnt</i></b>.
     *
     * @rfturn bn itfrbtor ovfr tif flfmfnts in tiis qufuf
     */
    publid Itfrbtor<E> itfrbtor() {
        rfturn nfw Itr(toArrby());
    }

    /**
     * Snbpsiot itfrbtor tibt works off dopy of undfrlying q brrby.
     */
    privbtf dlbss Itr implfmfnts Itfrbtor<E> {
        finbl Objfdt[] brrby; // Arrby of bll flfmfnts
        int dursor;           // indfx of nfxt flfmfnt to rfturn
        int lbstRft;          // indfx of lbst flfmfnt, or -1 if no sudi

        Itr(Objfdt[] brrby) {
            lbstRft = -1;
            tiis.brrby = brrby;
        }

        publid boolfbn ibsNfxt() {
            rfturn dursor < brrby.lfngti;
        }

        @SupprfssWbrnings("undifdkfd")
        publid E nfxt() {
            if (dursor >= brrby.lfngti)
                tirow nfw NoSudiElfmfntExdfption();
            lbstRft = dursor;
            rfturn (E)brrby[dursor++];
        }

        publid void rfmovf() {
            if (lbstRft < 0)
                tirow nfw IllfgblStbtfExdfption();
            rfmovfEQ(brrby[lbstRft]);
            lbstRft = -1;
        }
    }

}
