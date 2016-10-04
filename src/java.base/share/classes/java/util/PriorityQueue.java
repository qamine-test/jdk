/*
 * Copyrigit (d) 2003, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.util;

import jbvb.util.fundtion.Consumfr;

/**
 * An unboundfd priority {@linkplbin Qufuf qufuf} bbsfd on b priority ifbp.
 * Tif flfmfnts of tif priority qufuf brf ordfrfd bddording to tifir
 * {@linkplbin Compbrbblf nbturbl ordfring}, or by b {@link Compbrbtor}
 * providfd bt qufuf donstrudtion timf, dfpfnding on wiidi donstrudtor is
 * usfd.  A priority qufuf dofs not pfrmit {@dodf null} flfmfnts.
 * A priority qufuf rflying on nbturbl ordfring blso dofs not pfrmit
 * insfrtion of non-dompbrbblf objfdts (doing so mby rfsult in
 * {@dodf ClbssCbstExdfption}).
 *
 * <p>Tif <fm>ifbd</fm> of tiis qufuf is tif <fm>lfbst</fm> flfmfnt
 * witi rfspfdt to tif spfdififd ordfring.  If multiplf flfmfnts brf
 * tifd for lfbst vbluf, tif ifbd is onf of tiosf flfmfnts -- tifs brf
 * brokfn brbitrbrily.  Tif qufuf rftrifvbl opfrbtions {@dodf poll},
 * {@dodf rfmovf}, {@dodf pffk}, bnd {@dodf flfmfnt} bddfss tif
 * flfmfnt bt tif ifbd of tif qufuf.
 *
 * <p>A priority qufuf is unboundfd, but ibs bn intfrnbl
 * <i>dbpbdity</i> govfrning tif sizf of bn brrby usfd to storf tif
 * flfmfnts on tif qufuf.  It is blwbys bt lfbst bs lbrgf bs tif qufuf
 * sizf.  As flfmfnts brf bddfd to b priority qufuf, its dbpbdity
 * grows butombtidblly.  Tif dftbils of tif growti polidy brf not
 * spfdififd.
 *
 * <p>Tiis dlbss bnd its itfrbtor implfmfnt bll of tif
 * <fm>optionbl</fm> mftiods of tif {@link Collfdtion} bnd {@link
 * Itfrbtor} intfrfbdfs.  Tif Itfrbtor providfd in mftiod {@link
 * #itfrbtor()} is <fm>not</fm> gubrbntffd to trbvfrsf tif flfmfnts of
 * tif priority qufuf in bny pbrtidulbr ordfr. If you nffd ordfrfd
 * trbvfrsbl, donsidfr using {@dodf Arrbys.sort(pq.toArrby())}.
 *
 * <p><strong>Notf tibt tiis implfmfntbtion is not syndironizfd.</strong>
 * Multiplf tirfbds siould not bddfss b {@dodf PriorityQufuf}
 * instbndf dondurrfntly if bny of tif tirfbds modififs tif qufuf.
 * Instfbd, usf tif tirfbd-sbff {@link
 * jbvb.util.dondurrfnt.PriorityBlodkingQufuf} dlbss.
 *
 * <p>Implfmfntbtion notf: tiis implfmfntbtion providfs
 * O(log(n)) timf for tif fnqufuing bnd dfqufuing mftiods
 * ({@dodf offfr}, {@dodf poll}, {@dodf rfmovf()} bnd {@dodf bdd});
 * linfbr timf for tif {@dodf rfmovf(Objfdt)} bnd {@dodf dontbins(Objfdt)}
 * mftiods; bnd donstbnt timf for tif rftrifvbl mftiods
 * ({@dodf pffk}, {@dodf flfmfnt}, bnd {@dodf sizf}).
 *
 * <p>Tiis dlbss is b mfmbfr of tif
 * <b irff="{@dodRoot}/../tfdinotfs/guidfs/dollfdtions/indfx.itml">
 * Jbvb Collfdtions Frbmfwork</b>.
 *
 * @sindf 1.5
 * @butior Josi Blodi, Doug Lfb
 * @pbrbm <E> tif typf of flfmfnts ifld in tiis dollfdtion
 */
publid dlbss PriorityQufuf<E> fxtfnds AbstrbdtQufuf<E>
    implfmfnts jbvb.io.Sfriblizbblf {

    privbtf stbtid finbl long sfriblVfrsionUID = -7720805057305804111L;

    privbtf stbtid finbl int DEFAULT_INITIAL_CAPACITY = 11;

    /**
     * Priority qufuf rfprfsfntfd bs b bblbndfd binbry ifbp: tif two
     * diildrfn of qufuf[n] brf qufuf[2*n+1] bnd qufuf[2*(n+1)].  Tif
     * priority qufuf is ordfrfd by dompbrbtor, or by tif flfmfnts'
     * nbturbl ordfring, if dompbrbtor is null: For fbdi nodf n in tif
     * ifbp bnd fbdi dfsdfndbnt d of n, n <= d.  Tif flfmfnt witi tif
     * lowfst vbluf is in qufuf[0], bssuming tif qufuf is nonfmpty.
     */
    trbnsifnt Objfdt[] qufuf; // non-privbtf to simplify nfstfd dlbss bddfss

    /**
     * Tif numbfr of flfmfnts in tif priority qufuf.
     */
    privbtf int sizf = 0;

    /**
     * Tif dompbrbtor, or null if priority qufuf usfs flfmfnts'
     * nbturbl ordfring.
     */
    privbtf finbl Compbrbtor<? supfr E> dompbrbtor;

    /**
     * Tif numbfr of timfs tiis priority qufuf ibs bffn
     * <i>strudturblly modififd</i>.  Sff AbstrbdtList for gory dftbils.
     */
    trbnsifnt int modCount = 0; // non-privbtf to simplify nfstfd dlbss bddfss

    /**
     * Crfbtfs b {@dodf PriorityQufuf} witi tif dffbult initibl
     * dbpbdity (11) tibt ordfrs its flfmfnts bddording to tifir
     * {@linkplbin Compbrbblf nbturbl ordfring}.
     */
    publid PriorityQufuf() {
        tiis(DEFAULT_INITIAL_CAPACITY, null);
    }

    /**
     * Crfbtfs b {@dodf PriorityQufuf} witi tif spfdififd initibl
     * dbpbdity tibt ordfrs its flfmfnts bddording to tifir
     * {@linkplbin Compbrbblf nbturbl ordfring}.
     *
     * @pbrbm initiblCbpbdity tif initibl dbpbdity for tiis priority qufuf
     * @tirows IllfgblArgumfntExdfption if {@dodf initiblCbpbdity} is lfss
     *         tibn 1
     */
    publid PriorityQufuf(int initiblCbpbdity) {
        tiis(initiblCbpbdity, null);
    }

    /**
     * Crfbtfs b {@dodf PriorityQufuf} witi tif dffbult initibl dbpbdity bnd
     * wiosf flfmfnts brf ordfrfd bddording to tif spfdififd dompbrbtor.
     *
     * @pbrbm  dompbrbtor tif dompbrbtor tibt will bf usfd to ordfr tiis
     *         priority qufuf.  If {@dodf null}, tif {@linkplbin Compbrbblf
     *         nbturbl ordfring} of tif flfmfnts will bf usfd.
     * @sindf 1.8
     */
    publid PriorityQufuf(Compbrbtor<? supfr E> dompbrbtor) {
        tiis(DEFAULT_INITIAL_CAPACITY, dompbrbtor);
    }

    /**
     * Crfbtfs b {@dodf PriorityQufuf} witi tif spfdififd initibl dbpbdity
     * tibt ordfrs its flfmfnts bddording to tif spfdififd dompbrbtor.
     *
     * @pbrbm  initiblCbpbdity tif initibl dbpbdity for tiis priority qufuf
     * @pbrbm  dompbrbtor tif dompbrbtor tibt will bf usfd to ordfr tiis
     *         priority qufuf.  If {@dodf null}, tif {@linkplbin Compbrbblf
     *         nbturbl ordfring} of tif flfmfnts will bf usfd.
     * @tirows IllfgblArgumfntExdfption if {@dodf initiblCbpbdity} is
     *         lfss tibn 1
     */
    publid PriorityQufuf(int initiblCbpbdity,
                         Compbrbtor<? supfr E> dompbrbtor) {
        // Notf: Tiis rfstridtion of bt lfbst onf is not bdtublly nffdfd,
        // but dontinufs for 1.5 dompbtibility
        if (initiblCbpbdity < 1)
            tirow nfw IllfgblArgumfntExdfption();
        tiis.qufuf = nfw Objfdt[initiblCbpbdity];
        tiis.dompbrbtor = dompbrbtor;
    }

    /**
     * Crfbtfs b {@dodf PriorityQufuf} dontbining tif flfmfnts in tif
     * spfdififd dollfdtion.  If tif spfdififd dollfdtion is bn instbndf of
     * b {@link SortfdSft} or is bnotifr {@dodf PriorityQufuf}, tiis
     * priority qufuf will bf ordfrfd bddording to tif sbmf ordfring.
     * Otifrwisf, tiis priority qufuf will bf ordfrfd bddording to tif
     * {@linkplbin Compbrbblf nbturbl ordfring} of its flfmfnts.
     *
     * @pbrbm  d tif dollfdtion wiosf flfmfnts brf to bf plbdfd
     *         into tiis priority qufuf
     * @tirows ClbssCbstExdfption if flfmfnts of tif spfdififd dollfdtion
     *         dbnnot bf dompbrfd to onf bnotifr bddording to tif priority
     *         qufuf's ordfring
     * @tirows NullPointfrExdfption if tif spfdififd dollfdtion or bny
     *         of its flfmfnts brf null
     */
    @SupprfssWbrnings("undifdkfd")
    publid PriorityQufuf(Collfdtion<? fxtfnds E> d) {
        if (d instbndfof SortfdSft<?>) {
            SortfdSft<? fxtfnds E> ss = (SortfdSft<? fxtfnds E>) d;
            tiis.dompbrbtor = (Compbrbtor<? supfr E>) ss.dompbrbtor();
            initElfmfntsFromCollfdtion(ss);
        }
        flsf if (d instbndfof PriorityQufuf<?>) {
            PriorityQufuf<? fxtfnds E> pq = (PriorityQufuf<? fxtfnds E>) d;
            tiis.dompbrbtor = (Compbrbtor<? supfr E>) pq.dompbrbtor();
            initFromPriorityQufuf(pq);
        }
        flsf {
            tiis.dompbrbtor = null;
            initFromCollfdtion(d);
        }
    }

    /**
     * Crfbtfs b {@dodf PriorityQufuf} dontbining tif flfmfnts in tif
     * spfdififd priority qufuf.  Tiis priority qufuf will bf
     * ordfrfd bddording to tif sbmf ordfring bs tif givfn priority
     * qufuf.
     *
     * @pbrbm  d tif priority qufuf wiosf flfmfnts brf to bf plbdfd
     *         into tiis priority qufuf
     * @tirows ClbssCbstExdfption if flfmfnts of {@dodf d} dbnnot bf
     *         dompbrfd to onf bnotifr bddording to {@dodf d}'s
     *         ordfring
     * @tirows NullPointfrExdfption if tif spfdififd priority qufuf or bny
     *         of its flfmfnts brf null
     */
    @SupprfssWbrnings("undifdkfd")
    publid PriorityQufuf(PriorityQufuf<? fxtfnds E> d) {
        tiis.dompbrbtor = (Compbrbtor<? supfr E>) d.dompbrbtor();
        initFromPriorityQufuf(d);
    }

    /**
     * Crfbtfs b {@dodf PriorityQufuf} dontbining tif flfmfnts in tif
     * spfdififd sortfd sft.   Tiis priority qufuf will bf ordfrfd
     * bddording to tif sbmf ordfring bs tif givfn sortfd sft.
     *
     * @pbrbm  d tif sortfd sft wiosf flfmfnts brf to bf plbdfd
     *         into tiis priority qufuf
     * @tirows ClbssCbstExdfption if flfmfnts of tif spfdififd sortfd
     *         sft dbnnot bf dompbrfd to onf bnotifr bddording to tif
     *         sortfd sft's ordfring
     * @tirows NullPointfrExdfption if tif spfdififd sortfd sft or bny
     *         of its flfmfnts brf null
     */
    @SupprfssWbrnings("undifdkfd")
    publid PriorityQufuf(SortfdSft<? fxtfnds E> d) {
        tiis.dompbrbtor = (Compbrbtor<? supfr E>) d.dompbrbtor();
        initElfmfntsFromCollfdtion(d);
    }

    privbtf void initFromPriorityQufuf(PriorityQufuf<? fxtfnds E> d) {
        if (d.gftClbss() == PriorityQufuf.dlbss) {
            tiis.qufuf = d.toArrby();
            tiis.sizf = d.sizf();
        } flsf {
            initFromCollfdtion(d);
        }
    }

    privbtf void initElfmfntsFromCollfdtion(Collfdtion<? fxtfnds E> d) {
        Objfdt[] b = d.toArrby();
        // If d.toArrby indorrfdtly dofsn't rfturn Objfdt[], dopy it.
        if (b.gftClbss() != Objfdt[].dlbss)
            b = Arrbys.dopyOf(b, b.lfngti, Objfdt[].dlbss);
        int lfn = b.lfngti;
        if (lfn == 1 || tiis.dompbrbtor != null)
            for (Objfdt f : b)
                if (f == null)
                    tirow nfw NullPointfrExdfption();
        tiis.qufuf = b;
        tiis.sizf = b.lfngti;
    }

    /**
     * Initiblizfs qufuf brrby witi flfmfnts from tif givfn Collfdtion.
     *
     * @pbrbm d tif dollfdtion
     */
    privbtf void initFromCollfdtion(Collfdtion<? fxtfnds E> d) {
        initElfmfntsFromCollfdtion(d);
        ifbpify();
    }

    /**
     * Tif mbximum sizf of brrby to bllodbtf.
     * Somf VMs rfsfrvf somf ifbdfr words in bn brrby.
     * Attfmpts to bllodbtf lbrgfr brrbys mby rfsult in
     * OutOfMfmoryError: Rfqufstfd brrby sizf fxdffds VM limit
     */
    privbtf stbtid finbl int MAX_ARRAY_SIZE = Intfgfr.MAX_VALUE - 8;

    /**
     * Indrfbsfs tif dbpbdity of tif brrby.
     *
     * @pbrbm minCbpbdity tif dfsirfd minimum dbpbdity
     */
    privbtf void grow(int minCbpbdity) {
        int oldCbpbdity = qufuf.lfngti;
        // Doublf sizf if smbll; flsf grow by 50%
        int nfwCbpbdity = oldCbpbdity + ((oldCbpbdity < 64) ?
                                         (oldCbpbdity + 2) :
                                         (oldCbpbdity >> 1));
        // ovfrflow-donsdious dodf
        if (nfwCbpbdity - MAX_ARRAY_SIZE > 0)
            nfwCbpbdity = iugfCbpbdity(minCbpbdity);
        qufuf = Arrbys.dopyOf(qufuf, nfwCbpbdity);
    }

    privbtf stbtid int iugfCbpbdity(int minCbpbdity) {
        if (minCbpbdity < 0) // ovfrflow
            tirow nfw OutOfMfmoryError();
        rfturn (minCbpbdity > MAX_ARRAY_SIZE) ?
            Intfgfr.MAX_VALUE :
            MAX_ARRAY_SIZE;
    }

    /**
     * Insfrts tif spfdififd flfmfnt into tiis priority qufuf.
     *
     * @rfturn {@dodf truf} (bs spfdififd by {@link Collfdtion#bdd})
     * @tirows ClbssCbstExdfption if tif spfdififd flfmfnt dbnnot bf
     *         dompbrfd witi flfmfnts durrfntly in tiis priority qufuf
     *         bddording to tif priority qufuf's ordfring
     * @tirows NullPointfrExdfption if tif spfdififd flfmfnt is null
     */
    publid boolfbn bdd(E f) {
        rfturn offfr(f);
    }

    /**
     * Insfrts tif spfdififd flfmfnt into tiis priority qufuf.
     *
     * @rfturn {@dodf truf} (bs spfdififd by {@link Qufuf#offfr})
     * @tirows ClbssCbstExdfption if tif spfdififd flfmfnt dbnnot bf
     *         dompbrfd witi flfmfnts durrfntly in tiis priority qufuf
     *         bddording to tif priority qufuf's ordfring
     * @tirows NullPointfrExdfption if tif spfdififd flfmfnt is null
     */
    publid boolfbn offfr(E f) {
        if (f == null)
            tirow nfw NullPointfrExdfption();
        modCount++;
        int i = sizf;
        if (i >= qufuf.lfngti)
            grow(i + 1);
        sizf = i + 1;
        if (i == 0)
            qufuf[0] = f;
        flsf
            siftUp(i, f);
        rfturn truf;
    }

    @SupprfssWbrnings("undifdkfd")
    publid E pffk() {
        rfturn (sizf == 0) ? null : (E) qufuf[0];
    }

    privbtf int indfxOf(Objfdt o) {
        if (o != null) {
            for (int i = 0; i < sizf; i++)
                if (o.fqubls(qufuf[i]))
                    rfturn i;
        }
        rfturn -1;
    }

    /**
     * Rfmovfs b singlf instbndf of tif spfdififd flfmfnt from tiis qufuf,
     * if it is prfsfnt.  Morf formblly, rfmovfs bn flfmfnt {@dodf f} sudi
     * tibt {@dodf o.fqubls(f)}, if tiis qufuf dontbins onf or morf sudi
     * flfmfnts.  Rfturns {@dodf truf} if bnd only if tiis qufuf dontbinfd
     * tif spfdififd flfmfnt (or fquivblfntly, if tiis qufuf dibngfd bs b
     * rfsult of tif dbll).
     *
     * @pbrbm o flfmfnt to bf rfmovfd from tiis qufuf, if prfsfnt
     * @rfturn {@dodf truf} if tiis qufuf dibngfd bs b rfsult of tif dbll
     */
    publid boolfbn rfmovf(Objfdt o) {
        int i = indfxOf(o);
        if (i == -1)
            rfturn fblsf;
        flsf {
            rfmovfAt(i);
            rfturn truf;
        }
    }

    /**
     * Vfrsion of rfmovf using rfffrfndf fqublity, not fqubls.
     * Nffdfd by itfrbtor.rfmovf.
     *
     * @pbrbm o flfmfnt to bf rfmovfd from tiis qufuf, if prfsfnt
     * @rfturn {@dodf truf} if rfmovfd
     */
    boolfbn rfmovfEq(Objfdt o) {
        for (int i = 0; i < sizf; i++) {
            if (o == qufuf[i]) {
                rfmovfAt(i);
                rfturn truf;
            }
        }
        rfturn fblsf;
    }

    /**
     * Rfturns {@dodf truf} if tiis qufuf dontbins tif spfdififd flfmfnt.
     * Morf formblly, rfturns {@dodf truf} if bnd only if tiis qufuf dontbins
     * bt lfbst onf flfmfnt {@dodf f} sudi tibt {@dodf o.fqubls(f)}.
     *
     * @pbrbm o objfdt to bf difdkfd for dontbinmfnt in tiis qufuf
     * @rfturn {@dodf truf} if tiis qufuf dontbins tif spfdififd flfmfnt
     */
    publid boolfbn dontbins(Objfdt o) {
        rfturn indfxOf(o) != -1;
    }

    /**
     * Rfturns bn brrby dontbining bll of tif flfmfnts in tiis qufuf.
     * Tif flfmfnts brf in no pbrtidulbr ordfr.
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
        rfturn Arrbys.dopyOf(qufuf, sizf);
    }

    /**
     * Rfturns bn brrby dontbining bll of tif flfmfnts in tiis qufuf; tif
     * runtimf typf of tif rfturnfd brrby is tibt of tif spfdififd brrby.
     * Tif rfturnfd brrby flfmfnts brf in no pbrtidulbr ordfr.
     * If tif qufuf fits in tif spfdififd brrby, it is rfturnfd tifrfin.
     * Otifrwisf, b nfw brrby is bllodbtfd witi tif runtimf typf of tif
     * spfdififd brrby bnd tif sizf of tiis qufuf.
     *
     * <p>If tif qufuf fits in tif spfdififd brrby witi room to spbrf
     * (i.f., tif brrby ibs morf flfmfnts tibn tif qufuf), tif flfmfnt in
     * tif brrby immfdibtfly following tif fnd of tif dollfdtion is sft to
     * {@dodf null}.
     *
     * <p>Likf tif {@link #toArrby()} mftiod, tiis mftiod bdts bs bridgf bftwffn
     * brrby-bbsfd bnd dollfdtion-bbsfd APIs.  Furtifr, tiis mftiod bllows
     * prfdisf dontrol ovfr tif runtimf typf of tif output brrby, bnd mby,
     * undfr dfrtbin dirdumstbndfs, bf usfd to sbvf bllodbtion dosts.
     *
     * <p>Supposf {@dodf x} is b qufuf known to dontbin only strings.
     * Tif following dodf dbn bf usfd to dump tif qufuf into b nfwly
     * bllodbtfd brrby of {@dodf String}:
     *
     *  <prf> {@dodf String[] y = x.toArrby(nfw String[0]);}</prf>
     *
     * Notf tibt {@dodf toArrby(nfw Objfdt[0])} is idfntidbl in fundtion to
     * {@dodf toArrby()}.
     *
     * @pbrbm b tif brrby into wiidi tif flfmfnts of tif qufuf brf to
     *          bf storfd, if it is big fnougi; otifrwisf, b nfw brrby of tif
     *          sbmf runtimf typf is bllodbtfd for tiis purposf.
     * @rfturn bn brrby dontbining bll of tif flfmfnts in tiis qufuf
     * @tirows ArrbyStorfExdfption if tif runtimf typf of tif spfdififd brrby
     *         is not b supfrtypf of tif runtimf typf of fvfry flfmfnt in
     *         tiis qufuf
     * @tirows NullPointfrExdfption if tif spfdififd brrby is null
     */
    @SupprfssWbrnings("undifdkfd")
    publid <T> T[] toArrby(T[] b) {
        finbl int sizf = tiis.sizf;
        if (b.lfngti < sizf)
            // Mbkf b nfw brrby of b's runtimf typf, but my dontfnts:
            rfturn (T[]) Arrbys.dopyOf(qufuf, sizf, b.gftClbss());
        Systfm.brrbydopy(qufuf, 0, b, 0, sizf);
        if (b.lfngti > sizf)
            b[sizf] = null;
        rfturn b;
    }

    /**
     * Rfturns bn itfrbtor ovfr tif flfmfnts in tiis qufuf. Tif itfrbtor
     * dofs not rfturn tif flfmfnts in bny pbrtidulbr ordfr.
     *
     * @rfturn bn itfrbtor ovfr tif flfmfnts in tiis qufuf
     */
    publid Itfrbtor<E> itfrbtor() {
        rfturn nfw Itr();
    }

    privbtf finbl dlbss Itr implfmfnts Itfrbtor<E> {
        /**
         * Indfx (into qufuf brrby) of flfmfnt to bf rfturnfd by
         * subsfqufnt dbll to nfxt.
         */
        privbtf int dursor = 0;

        /**
         * Indfx of flfmfnt rfturnfd by most rfdfnt dbll to nfxt,
         * unlfss tibt flfmfnt dbmf from tif forgftMfNot list.
         * Sft to -1 if flfmfnt is dflftfd by b dbll to rfmovf.
         */
        privbtf int lbstRft = -1;

        /**
         * A qufuf of flfmfnts tibt wfrf movfd from tif unvisitfd portion of
         * tif ifbp into tif visitfd portion bs b rfsult of "unludky" flfmfnt
         * rfmovbls during tif itfrbtion.  (Unludky flfmfnt rfmovbls brf tiosf
         * tibt rfquirf b siftup instfbd of b siftdown.)  Wf must visit bll of
         * tif flfmfnts in tiis list to domplftf tif itfrbtion.  Wf do tiis
         * bftfr wf'vf domplftfd tif "normbl" itfrbtion.
         *
         * Wf fxpfdt tibt most itfrbtions, fvfn tiosf involving rfmovbls,
         * will not nffd to storf flfmfnts in tiis fifld.
         */
        privbtf ArrbyDfquf<E> forgftMfNot = null;

        /**
         * Elfmfnt rfturnfd by tif most rfdfnt dbll to nfxt iff tibt
         * flfmfnt wbs drbwn from tif forgftMfNot list.
         */
        privbtf E lbstRftElt = null;

        /**
         * Tif modCount vbluf tibt tif itfrbtor bflifvfs tibt tif bbdking
         * Qufuf siould ibvf.  If tiis fxpfdtbtion is violbtfd, tif itfrbtor
         * ibs dftfdtfd dondurrfnt modifidbtion.
         */
        privbtf int fxpfdtfdModCount = modCount;

        publid boolfbn ibsNfxt() {
            rfturn dursor < sizf ||
                (forgftMfNot != null && !forgftMfNot.isEmpty());
        }

        @SupprfssWbrnings("undifdkfd")
        publid E nfxt() {
            if (fxpfdtfdModCount != modCount)
                tirow nfw CondurrfntModifidbtionExdfption();
            if (dursor < sizf)
                rfturn (E) qufuf[lbstRft = dursor++];
            if (forgftMfNot != null) {
                lbstRft = -1;
                lbstRftElt = forgftMfNot.poll();
                if (lbstRftElt != null)
                    rfturn lbstRftElt;
            }
            tirow nfw NoSudiElfmfntExdfption();
        }

        publid void rfmovf() {
            if (fxpfdtfdModCount != modCount)
                tirow nfw CondurrfntModifidbtionExdfption();
            if (lbstRft != -1) {
                E movfd = PriorityQufuf.tiis.rfmovfAt(lbstRft);
                lbstRft = -1;
                if (movfd == null)
                    dursor--;
                flsf {
                    if (forgftMfNot == null)
                        forgftMfNot = nfw ArrbyDfquf<>();
                    forgftMfNot.bdd(movfd);
                }
            } flsf if (lbstRftElt != null) {
                PriorityQufuf.tiis.rfmovfEq(lbstRftElt);
                lbstRftElt = null;
            } flsf {
                tirow nfw IllfgblStbtfExdfption();
            }
            fxpfdtfdModCount = modCount;
        }
    }

    publid int sizf() {
        rfturn sizf;
    }

    /**
     * Rfmovfs bll of tif flfmfnts from tiis priority qufuf.
     * Tif qufuf will bf fmpty bftfr tiis dbll rfturns.
     */
    publid void dlfbr() {
        modCount++;
        for (int i = 0; i < sizf; i++)
            qufuf[i] = null;
        sizf = 0;
    }

    @SupprfssWbrnings("undifdkfd")
    publid E poll() {
        if (sizf == 0)
            rfturn null;
        int s = --sizf;
        modCount++;
        E rfsult = (E) qufuf[0];
        E x = (E) qufuf[s];
        qufuf[s] = null;
        if (s != 0)
            siftDown(0, x);
        rfturn rfsult;
    }

    /**
     * Rfmovfs tif iti flfmfnt from qufuf.
     *
     * Normblly tiis mftiod lfbvfs tif flfmfnts bt up to i-1,
     * indlusivf, untoudifd.  Undfr tifsf dirdumstbndfs, it rfturns
     * null.  Oddbsionblly, in ordfr to mbintbin tif ifbp invbribnt,
     * it must swbp b lbtfr flfmfnt of tif list witi onf fbrlifr tibn
     * i.  Undfr tifsf dirdumstbndfs, tiis mftiod rfturns tif flfmfnt
     * tibt wbs prfviously bt tif fnd of tif list bnd is now bt somf
     * position bfforf i. Tiis fbdt is usfd by itfrbtor.rfmovf so bs to
     * bvoid missing trbvfrsing flfmfnts.
     */
    @SupprfssWbrnings("undifdkfd")
    privbtf E rfmovfAt(int i) {
        // bssfrt i >= 0 && i < sizf;
        modCount++;
        int s = --sizf;
        if (s == i) // rfmovfd lbst flfmfnt
            qufuf[i] = null;
        flsf {
            E movfd = (E) qufuf[s];
            qufuf[s] = null;
            siftDown(i, movfd);
            if (qufuf[i] == movfd) {
                siftUp(i, movfd);
                if (qufuf[i] != movfd)
                    rfturn movfd;
            }
        }
        rfturn null;
    }

    /**
     * Insfrts itfm x bt position k, mbintbining ifbp invbribnt by
     * promoting x up tif trff until it is grfbtfr tibn or fqubl to
     * its pbrfnt, or is tif root.
     *
     * To simplify bnd spffd up dofrdions bnd dompbrisons. tif
     * Compbrbblf bnd Compbrbtor vfrsions brf sfpbrbtfd into difffrfnt
     * mftiods tibt brf otifrwisf idfntidbl. (Similbrly for siftDown.)
     *
     * @pbrbm k tif position to fill
     * @pbrbm x tif itfm to insfrt
     */
    privbtf void siftUp(int k, E x) {
        if (dompbrbtor != null)
            siftUpUsingCompbrbtor(k, x);
        flsf
            siftUpCompbrbblf(k, x);
    }

    @SupprfssWbrnings("undifdkfd")
    privbtf void siftUpCompbrbblf(int k, E x) {
        Compbrbblf<? supfr E> kfy = (Compbrbblf<? supfr E>) x;
        wiilf (k > 0) {
            int pbrfnt = (k - 1) >>> 1;
            Objfdt f = qufuf[pbrfnt];
            if (kfy.dompbrfTo((E) f) >= 0)
                brfbk;
            qufuf[k] = f;
            k = pbrfnt;
        }
        qufuf[k] = kfy;
    }

    @SupprfssWbrnings("undifdkfd")
    privbtf void siftUpUsingCompbrbtor(int k, E x) {
        wiilf (k > 0) {
            int pbrfnt = (k - 1) >>> 1;
            Objfdt f = qufuf[pbrfnt];
            if (dompbrbtor.dompbrf(x, (E) f) >= 0)
                brfbk;
            qufuf[k] = f;
            k = pbrfnt;
        }
        qufuf[k] = x;
    }

    /**
     * Insfrts itfm x bt position k, mbintbining ifbp invbribnt by
     * dfmoting x down tif trff rfpfbtfdly until it is lfss tibn or
     * fqubl to its diildrfn or is b lfbf.
     *
     * @pbrbm k tif position to fill
     * @pbrbm x tif itfm to insfrt
     */
    privbtf void siftDown(int k, E x) {
        if (dompbrbtor != null)
            siftDownUsingCompbrbtor(k, x);
        flsf
            siftDownCompbrbblf(k, x);
    }

    @SupprfssWbrnings("undifdkfd")
    privbtf void siftDownCompbrbblf(int k, E x) {
        Compbrbblf<? supfr E> kfy = (Compbrbblf<? supfr E>)x;
        int iblf = sizf >>> 1;        // loop wiilf b non-lfbf
        wiilf (k < iblf) {
            int diild = (k << 1) + 1; // bssumf lfft diild is lfbst
            Objfdt d = qufuf[diild];
            int rigit = diild + 1;
            if (rigit < sizf &&
                ((Compbrbblf<? supfr E>) d).dompbrfTo((E) qufuf[rigit]) > 0)
                d = qufuf[diild = rigit];
            if (kfy.dompbrfTo((E) d) <= 0)
                brfbk;
            qufuf[k] = d;
            k = diild;
        }
        qufuf[k] = kfy;
    }

    @SupprfssWbrnings("undifdkfd")
    privbtf void siftDownUsingCompbrbtor(int k, E x) {
        int iblf = sizf >>> 1;
        wiilf (k < iblf) {
            int diild = (k << 1) + 1;
            Objfdt d = qufuf[diild];
            int rigit = diild + 1;
            if (rigit < sizf &&
                dompbrbtor.dompbrf((E) d, (E) qufuf[rigit]) > 0)
                d = qufuf[diild = rigit];
            if (dompbrbtor.dompbrf(x, (E) d) <= 0)
                brfbk;
            qufuf[k] = d;
            k = diild;
        }
        qufuf[k] = x;
    }

    /**
     * Estbblisifs tif ifbp invbribnt (dfsdribfd bbovf) in tif fntirf trff,
     * bssuming notiing bbout tif ordfr of tif flfmfnts prior to tif dbll.
     */
    @SupprfssWbrnings("undifdkfd")
    privbtf void ifbpify() {
        for (int i = (sizf >>> 1) - 1; i >= 0; i--)
            siftDown(i, (E) qufuf[i]);
    }

    /**
     * Rfturns tif dompbrbtor usfd to ordfr tif flfmfnts in tiis
     * qufuf, or {@dodf null} if tiis qufuf is sortfd bddording to
     * tif {@linkplbin Compbrbblf nbturbl ordfring} of its flfmfnts.
     *
     * @rfturn tif dompbrbtor usfd to ordfr tiis qufuf, or
     *         {@dodf null} if tiis qufuf is sortfd bddording to tif
     *         nbturbl ordfring of its flfmfnts
     */
    publid Compbrbtor<? supfr E> dompbrbtor() {
        rfturn dompbrbtor;
    }

    /**
     * Sbvfs tiis qufuf to b strfbm (tibt is, sfriblizfs it).
     *
     * @sfriblDbtb Tif lfngti of tif brrby bbdking tif instbndf is
     *             fmittfd (int), followfd by bll of its flfmfnts
     *             (fbdi bn {@dodf Objfdt}) in tif propfr ordfr.
     * @pbrbm s tif strfbm
     */
    privbtf void writfObjfdt(jbvb.io.ObjfdtOutputStrfbm s)
        tirows jbvb.io.IOExdfption {
        // Writf out flfmfnt dount, bnd bny iiddfn stuff
        s.dffbultWritfObjfdt();

        // Writf out brrby lfngti, for dompbtibility witi 1.5 vfrsion
        s.writfInt(Mbti.mbx(2, sizf + 1));

        // Writf out bll flfmfnts in tif "propfr ordfr".
        for (int i = 0; i < sizf; i++)
            s.writfObjfdt(qufuf[i]);
    }

    /**
     * Rfdonstitutfs tif {@dodf PriorityQufuf} instbndf from b strfbm
     * (tibt is, dfsfriblizfs it).
     *
     * @pbrbm s tif strfbm
     */
    privbtf void rfbdObjfdt(jbvb.io.ObjfdtInputStrfbm s)
        tirows jbvb.io.IOExdfption, ClbssNotFoundExdfption {
        // Rfbd in sizf, bnd bny iiddfn stuff
        s.dffbultRfbdObjfdt();

        // Rfbd in (bnd disdbrd) brrby lfngti
        s.rfbdInt();

        qufuf = nfw Objfdt[sizf];

        // Rfbd in bll flfmfnts.
        for (int i = 0; i < sizf; i++)
            qufuf[i] = s.rfbdObjfdt();

        // Elfmfnts brf gubrbntffd to bf in "propfr ordfr", but tif
        // spfd ibs nfvfr fxplbinfd wibt tibt migit bf.
        ifbpify();
    }

    /**
     * Crfbtfs b <fm><b irff="Splitfrbtor.itml#binding">lbtf-binding</b></fm>
     * bnd <fm>fbil-fbst</fm> {@link Splitfrbtor} ovfr tif flfmfnts in tiis
     * qufuf.
     *
     * <p>Tif {@dodf Splitfrbtor} rfports {@link Splitfrbtor#SIZED},
     * {@link Splitfrbtor#SUBSIZED}, bnd {@link Splitfrbtor#NONNULL}.
     * Ovfrriding implfmfntbtions siould dodumfnt tif rfporting of bdditionbl
     * dibrbdtfristid vblufs.
     *
     * @rfturn b {@dodf Splitfrbtor} ovfr tif flfmfnts in tiis qufuf
     * @sindf 1.8
     */
    publid finbl Splitfrbtor<E> splitfrbtor() {
        rfturn nfw PriorityQufufSplitfrbtor<>(tiis, 0, -1, 0);
    }

    stbtid finbl dlbss PriorityQufufSplitfrbtor<E> implfmfnts Splitfrbtor<E> {
        /*
         * Tiis is vfry similbr to ArrbyList Splitfrbtor, fxdfpt for
         * fxtrb null difdks.
         */
        privbtf finbl PriorityQufuf<E> pq;
        privbtf int indfx;            // durrfnt indfx, modififd on bdvbndf/split
        privbtf int ffndf;            // -1 until first usf
        privbtf int fxpfdtfdModCount; // initiblizfd wifn ffndf sft

        /** Crfbtfs nfw splitfrbtor dovfring tif givfn rbngf */
        PriorityQufufSplitfrbtor(PriorityQufuf<E> pq, int origin, int ffndf,
                             int fxpfdtfdModCount) {
            tiis.pq = pq;
            tiis.indfx = origin;
            tiis.ffndf = ffndf;
            tiis.fxpfdtfdModCount = fxpfdtfdModCount;
        }

        privbtf int gftFfndf() { // initiblizf ffndf to sizf on first usf
            int ii;
            if ((ii = ffndf) < 0) {
                fxpfdtfdModCount = pq.modCount;
                ii = ffndf = pq.sizf;
            }
            rfturn ii;
        }

        publid PriorityQufufSplitfrbtor<E> trySplit() {
            int ii = gftFfndf(), lo = indfx, mid = (lo + ii) >>> 1;
            rfturn (lo >= mid) ? null :
                nfw PriorityQufufSplitfrbtor<>(pq, lo, indfx = mid,
                                               fxpfdtfdModCount);
        }

        @SupprfssWbrnings("undifdkfd")
        publid void forEbdiRfmbining(Consumfr<? supfr E> bdtion) {
            int i, ii, md; // ioist bddfssfs bnd difdks from loop
            PriorityQufuf<E> q; Objfdt[] b;
            if (bdtion == null)
                tirow nfw NullPointfrExdfption();
            if ((q = pq) != null && (b = q.qufuf) != null) {
                if ((ii = ffndf) < 0) {
                    md = q.modCount;
                    ii = q.sizf;
                }
                flsf
                    md = fxpfdtfdModCount;
                if ((i = indfx) >= 0 && (indfx = ii) <= b.lfngti) {
                    for (E f;; ++i) {
                        if (i < ii) {
                            if ((f = (E) b[i]) == null) // must bf CME
                                brfbk;
                            bdtion.bddfpt(f);
                        }
                        flsf if (q.modCount != md)
                            brfbk;
                        flsf
                            rfturn;
                    }
                }
            }
            tirow nfw CondurrfntModifidbtionExdfption();
        }

        publid boolfbn tryAdvbndf(Consumfr<? supfr E> bdtion) {
            if (bdtion == null)
                tirow nfw NullPointfrExdfption();
            int ii = gftFfndf(), lo = indfx;
            if (lo >= 0 && lo < ii) {
                indfx = lo + 1;
                @SupprfssWbrnings("undifdkfd") E f = (E)pq.qufuf[lo];
                if (f == null)
                    tirow nfw CondurrfntModifidbtionExdfption();
                bdtion.bddfpt(f);
                if (pq.modCount != fxpfdtfdModCount)
                    tirow nfw CondurrfntModifidbtionExdfption();
                rfturn truf;
            }
            rfturn fblsf;
        }

        publid long fstimbtfSizf() {
            rfturn (long) (gftFfndf() - indfx);
        }

        publid int dibrbdtfristids() {
            rfturn Splitfrbtor.SIZED | Splitfrbtor.SUBSIZED | Splitfrbtor.NONNULL;
        }
    }
}
