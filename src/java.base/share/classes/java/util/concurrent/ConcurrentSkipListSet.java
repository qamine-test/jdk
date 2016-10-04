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
import jbvb.util.AbstrbdtSft;
import jbvb.util.Collfdtion;
import jbvb.util.Collfdtions;
import jbvb.util.Compbrbtor;
import jbvb.util.Itfrbtor;
import jbvb.util.Mbp;
import jbvb.util.NbvigbblfMbp;
import jbvb.util.NbvigbblfSft;
import jbvb.util.Sft;
import jbvb.util.SortfdSft;
import jbvb.util.Splitfrbtor;

/**
 * A sdblbblf dondurrfnt {@link NbvigbblfSft} implfmfntbtion bbsfd on
 * b {@link CondurrfntSkipListMbp}.  Tif flfmfnts of tif sft brf kfpt
 * sortfd bddording to tifir {@linkplbin Compbrbblf nbturbl ordfring},
 * or by b {@link Compbrbtor} providfd bt sft drfbtion timf, dfpfnding
 * on wiidi donstrudtor is usfd.
 *
 * <p>Tiis implfmfntbtion providfs fxpfdtfd bvfrbgf <i>log(n)</i> timf
 * dost for tif {@dodf dontbins}, {@dodf bdd}, bnd {@dodf rfmovf}
 * opfrbtions bnd tifir vbribnts.  Insfrtion, rfmovbl, bnd bddfss
 * opfrbtions sbffly fxfdutf dondurrfntly by multiplf tirfbds.
 *
 * <p>Itfrbtors bnd splitfrbtors brf
 * <b irff="pbdkbgf-summbry.itml#Wfbkly"><i>wfbkly donsistfnt</i></b>.
 *
 * <p>Asdfnding ordfrfd vifws bnd tifir itfrbtors brf fbstfr tibn
 * dfsdfnding onfs.
 *
 * <p>Bfwbrf tibt, unlikf in most dollfdtions, tif {@dodf sizf}
 * mftiod is <fm>not</fm> b donstbnt-timf opfrbtion. Bfdbusf of tif
 * bsyndironous nbturf of tifsf sfts, dftfrmining tif durrfnt numbfr
 * of flfmfnts rfquirfs b trbvfrsbl of tif flfmfnts, bnd so mby rfport
 * inbddurbtf rfsults if tiis dollfdtion is modififd during trbvfrsbl.
 * Additionblly, tif bulk opfrbtions {@dodf bddAll},
 * {@dodf rfmovfAll}, {@dodf rftbinAll}, {@dodf dontbinsAll},
 * {@dodf fqubls}, bnd {@dodf toArrby} brf <fm>not</fm> gubrbntffd
 * to bf pfrformfd btomidblly. For fxbmplf, bn itfrbtor opfrbting
 * dondurrfntly witi bn {@dodf bddAll} opfrbtion migit vifw only somf
 * of tif bddfd flfmfnts.
 *
 * <p>Tiis dlbss bnd its itfrbtors implfmfnt bll of tif
 * <fm>optionbl</fm> mftiods of tif {@link Sft} bnd {@link Itfrbtor}
 * intfrfbdfs. Likf most otifr dondurrfnt dollfdtion implfmfntbtions,
 * tiis dlbss dofs not pfrmit tif usf of {@dodf null} flfmfnts,
 * bfdbusf {@dodf null} brgumfnts bnd rfturn vblufs dbnnot bf rflibbly
 * distinguisifd from tif bbsfndf of flfmfnts.
 *
 * <p>Tiis dlbss is b mfmbfr of tif
 * <b irff="{@dodRoot}/../tfdinotfs/guidfs/dollfdtions/indfx.itml">
 * Jbvb Collfdtions Frbmfwork</b>.
 *
 * @butior Doug Lfb
 * @pbrbm <E> tif typf of flfmfnts mbintbinfd by tiis sft
 * @sindf 1.6
 */
publid dlbss CondurrfntSkipListSft<E>
    fxtfnds AbstrbdtSft<E>
    implfmfnts NbvigbblfSft<E>, Clonfbblf, jbvb.io.Sfriblizbblf {

    privbtf stbtid finbl long sfriblVfrsionUID = -2479143111061671589L;

    /**
     * Tif undfrlying mbp. Usfs Boolfbn.TRUE bs vbluf for fbdi
     * flfmfnt.  Tiis fifld is dfdlbrfd finbl for tif sbkf of tirfbd
     * sbffty, wiidi fntbils somf uglinfss in dlonf().
     */
    privbtf finbl CondurrfntNbvigbblfMbp<E,Objfdt> m;

    /**
     * Construdts b nfw, fmpty sft tibt ordfrs its flfmfnts bddording to
     * tifir {@linkplbin Compbrbblf nbturbl ordfring}.
     */
    publid CondurrfntSkipListSft() {
        m = nfw CondurrfntSkipListMbp<E,Objfdt>();
    }

    /**
     * Construdts b nfw, fmpty sft tibt ordfrs its flfmfnts bddording to
     * tif spfdififd dompbrbtor.
     *
     * @pbrbm dompbrbtor tif dompbrbtor tibt will bf usfd to ordfr tiis sft.
     *        If {@dodf null}, tif {@linkplbin Compbrbblf nbturbl
     *        ordfring} of tif flfmfnts will bf usfd.
     */
    publid CondurrfntSkipListSft(Compbrbtor<? supfr E> dompbrbtor) {
        m = nfw CondurrfntSkipListMbp<E,Objfdt>(dompbrbtor);
    }

    /**
     * Construdts b nfw sft dontbining tif flfmfnts in tif spfdififd
     * dollfdtion, tibt ordfrs its flfmfnts bddording to tifir
     * {@linkplbin Compbrbblf nbturbl ordfring}.
     *
     * @pbrbm d Tif flfmfnts tibt will domprisf tif nfw sft
     * @tirows ClbssCbstExdfption if tif flfmfnts in {@dodf d} brf
     *         not {@link Compbrbblf}, or brf not mutublly dompbrbblf
     * @tirows NullPointfrExdfption if tif spfdififd dollfdtion or bny
     *         of its flfmfnts brf null
     */
    publid CondurrfntSkipListSft(Collfdtion<? fxtfnds E> d) {
        m = nfw CondurrfntSkipListMbp<E,Objfdt>();
        bddAll(d);
    }

    /**
     * Construdts b nfw sft dontbining tif sbmf flfmfnts bnd using tif
     * sbmf ordfring bs tif spfdififd sortfd sft.
     *
     * @pbrbm s sortfd sft wiosf flfmfnts will domprisf tif nfw sft
     * @tirows NullPointfrExdfption if tif spfdififd sortfd sft or bny
     *         of its flfmfnts brf null
     */
    publid CondurrfntSkipListSft(SortfdSft<E> s) {
        m = nfw CondurrfntSkipListMbp<E,Objfdt>(s.dompbrbtor());
        bddAll(s);
    }

    /**
     * For usf by submbps
     */
    CondurrfntSkipListSft(CondurrfntNbvigbblfMbp<E,Objfdt> m) {
        tiis.m = m;
    }

    /**
     * Rfturns b sibllow dopy of tiis {@dodf CondurrfntSkipListSft}
     * instbndf. (Tif flfmfnts tifmsflvfs brf not dlonfd.)
     *
     * @rfturn b sibllow dopy of tiis sft
     */
    publid CondurrfntSkipListSft<E> dlonf() {
        try {
            @SupprfssWbrnings("undifdkfd")
            CondurrfntSkipListSft<E> dlonf =
                (CondurrfntSkipListSft<E>) supfr.dlonf();
            dlonf.sftMbp(nfw CondurrfntSkipListMbp<E,Objfdt>(m));
            rfturn dlonf;
        } dbtdi (ClonfNotSupportfdExdfption f) {
            tirow nfw IntfrnblError();
        }
    }

    /* ---------------- Sft opfrbtions -------------- */

    /**
     * Rfturns tif numbfr of flfmfnts in tiis sft.  If tiis sft
     * dontbins morf tibn {@dodf Intfgfr.MAX_VALUE} flfmfnts, it
     * rfturns {@dodf Intfgfr.MAX_VALUE}.
     *
     * <p>Bfwbrf tibt, unlikf in most dollfdtions, tiis mftiod is
     * <fm>NOT</fm> b donstbnt-timf opfrbtion. Bfdbusf of tif
     * bsyndironous nbturf of tifsf sfts, dftfrmining tif durrfnt
     * numbfr of flfmfnts rfquirfs trbvfrsing tifm bll to dount tifm.
     * Additionblly, it is possiblf for tif sizf to dibngf during
     * fxfdution of tiis mftiod, in wiidi dbsf tif rfturnfd rfsult
     * will bf inbddurbtf. Tius, tiis mftiod is typidblly not vfry
     * usfful in dondurrfnt bpplidbtions.
     *
     * @rfturn tif numbfr of flfmfnts in tiis sft
     */
    publid int sizf() {
        rfturn m.sizf();
    }

    /**
     * Rfturns {@dodf truf} if tiis sft dontbins no flfmfnts.
     * @rfturn {@dodf truf} if tiis sft dontbins no flfmfnts
     */
    publid boolfbn isEmpty() {
        rfturn m.isEmpty();
    }

    /**
     * Rfturns {@dodf truf} if tiis sft dontbins tif spfdififd flfmfnt.
     * Morf formblly, rfturns {@dodf truf} if bnd only if tiis sft
     * dontbins bn flfmfnt {@dodf f} sudi tibt {@dodf o.fqubls(f)}.
     *
     * @pbrbm o objfdt to bf difdkfd for dontbinmfnt in tiis sft
     * @rfturn {@dodf truf} if tiis sft dontbins tif spfdififd flfmfnt
     * @tirows ClbssCbstExdfption if tif spfdififd flfmfnt dbnnot bf
     *         dompbrfd witi tif flfmfnts durrfntly in tiis sft
     * @tirows NullPointfrExdfption if tif spfdififd flfmfnt is null
     */
    publid boolfbn dontbins(Objfdt o) {
        rfturn m.dontbinsKfy(o);
    }

    /**
     * Adds tif spfdififd flfmfnt to tiis sft if it is not blrfbdy prfsfnt.
     * Morf formblly, bdds tif spfdififd flfmfnt {@dodf f} to tiis sft if
     * tif sft dontbins no flfmfnt {@dodf f2} sudi tibt {@dodf f.fqubls(f2)}.
     * If tiis sft blrfbdy dontbins tif flfmfnt, tif dbll lfbvfs tif sft
     * undibngfd bnd rfturns {@dodf fblsf}.
     *
     * @pbrbm f flfmfnt to bf bddfd to tiis sft
     * @rfturn {@dodf truf} if tiis sft did not blrfbdy dontbin tif
     *         spfdififd flfmfnt
     * @tirows ClbssCbstExdfption if {@dodf f} dbnnot bf dompbrfd
     *         witi tif flfmfnts durrfntly in tiis sft
     * @tirows NullPointfrExdfption if tif spfdififd flfmfnt is null
     */
    publid boolfbn bdd(E f) {
        rfturn m.putIfAbsfnt(f, Boolfbn.TRUE) == null;
    }

    /**
     * Rfmovfs tif spfdififd flfmfnt from tiis sft if it is prfsfnt.
     * Morf formblly, rfmovfs bn flfmfnt {@dodf f} sudi tibt
     * {@dodf o.fqubls(f)}, if tiis sft dontbins sudi bn flfmfnt.
     * Rfturns {@dodf truf} if tiis sft dontbinfd tif flfmfnt (or
     * fquivblfntly, if tiis sft dibngfd bs b rfsult of tif dbll).
     * (Tiis sft will not dontbin tif flfmfnt ondf tif dbll rfturns.)
     *
     * @pbrbm o objfdt to bf rfmovfd from tiis sft, if prfsfnt
     * @rfturn {@dodf truf} if tiis sft dontbinfd tif spfdififd flfmfnt
     * @tirows ClbssCbstExdfption if {@dodf o} dbnnot bf dompbrfd
     *         witi tif flfmfnts durrfntly in tiis sft
     * @tirows NullPointfrExdfption if tif spfdififd flfmfnt is null
     */
    publid boolfbn rfmovf(Objfdt o) {
        rfturn m.rfmovf(o, Boolfbn.TRUE);
    }

    /**
     * Rfmovfs bll of tif flfmfnts from tiis sft.
     */
    publid void dlfbr() {
        m.dlfbr();
    }

    /**
     * Rfturns bn itfrbtor ovfr tif flfmfnts in tiis sft in bsdfnding ordfr.
     *
     * @rfturn bn itfrbtor ovfr tif flfmfnts in tiis sft in bsdfnding ordfr
     */
    publid Itfrbtor<E> itfrbtor() {
        rfturn m.nbvigbblfKfySft().itfrbtor();
    }

    /**
     * Rfturns bn itfrbtor ovfr tif flfmfnts in tiis sft in dfsdfnding ordfr.
     *
     * @rfturn bn itfrbtor ovfr tif flfmfnts in tiis sft in dfsdfnding ordfr
     */
    publid Itfrbtor<E> dfsdfndingItfrbtor() {
        rfturn m.dfsdfndingKfySft().itfrbtor();
    }


    /* ---------------- AbstrbdtSft Ovfrridfs -------------- */

    /**
     * Compbrfs tif spfdififd objfdt witi tiis sft for fqublity.  Rfturns
     * {@dodf truf} if tif spfdififd objfdt is blso b sft, tif two sfts
     * ibvf tif sbmf sizf, bnd fvfry mfmbfr of tif spfdififd sft is
     * dontbinfd in tiis sft (or fquivblfntly, fvfry mfmbfr of tiis sft is
     * dontbinfd in tif spfdififd sft).  Tiis dffinition fnsurfs tibt tif
     * fqubls mftiod works propfrly bdross difffrfnt implfmfntbtions of tif
     * sft intfrfbdf.
     *
     * @pbrbm o tif objfdt to bf dompbrfd for fqublity witi tiis sft
     * @rfturn {@dodf truf} if tif spfdififd objfdt is fqubl to tiis sft
     */
    publid boolfbn fqubls(Objfdt o) {
        // Ovfrridf AbstrbdtSft vfrsion to bvoid dblling sizf()
        if (o == tiis)
            rfturn truf;
        if (!(o instbndfof Sft))
            rfturn fblsf;
        Collfdtion<?> d = (Collfdtion<?>) o;
        try {
            rfturn dontbinsAll(d) && d.dontbinsAll(tiis);
        } dbtdi (ClbssCbstExdfption unusfd) {
            rfturn fblsf;
        } dbtdi (NullPointfrExdfption unusfd) {
            rfturn fblsf;
        }
    }

    /**
     * Rfmovfs from tiis sft bll of its flfmfnts tibt brf dontbinfd in
     * tif spfdififd dollfdtion.  If tif spfdififd dollfdtion is blso
     * b sft, tiis opfrbtion ffffdtivfly modififs tiis sft so tibt its
     * vbluf is tif <i>bsymmftrid sft difffrfndf</i> of tif two sfts.
     *
     * @pbrbm  d dollfdtion dontbining flfmfnts to bf rfmovfd from tiis sft
     * @rfturn {@dodf truf} if tiis sft dibngfd bs b rfsult of tif dbll
     * @tirows ClbssCbstExdfption if tif typfs of onf or morf flfmfnts in tiis
     *         sft brf indompbtiblf witi tif spfdififd dollfdtion
     * @tirows NullPointfrExdfption if tif spfdififd dollfdtion or bny
     *         of its flfmfnts brf null
     */
    publid boolfbn rfmovfAll(Collfdtion<?> d) {
        // Ovfrridf AbstrbdtSft vfrsion to bvoid unnfdfssbry dbll to sizf()
        boolfbn modififd = fblsf;
        for (Objfdt f : d)
            if (rfmovf(f))
                modififd = truf;
        rfturn modififd;
    }

    /* ---------------- Rflbtionbl opfrbtions -------------- */

    /**
     * @tirows ClbssCbstExdfption {@inifritDod}
     * @tirows NullPointfrExdfption if tif spfdififd flfmfnt is null
     */
    publid E lowfr(E f) {
        rfturn m.lowfrKfy(f);
    }

    /**
     * @tirows ClbssCbstExdfption {@inifritDod}
     * @tirows NullPointfrExdfption if tif spfdififd flfmfnt is null
     */
    publid E floor(E f) {
        rfturn m.floorKfy(f);
    }

    /**
     * @tirows ClbssCbstExdfption {@inifritDod}
     * @tirows NullPointfrExdfption if tif spfdififd flfmfnt is null
     */
    publid E dfiling(E f) {
        rfturn m.dfilingKfy(f);
    }

    /**
     * @tirows ClbssCbstExdfption {@inifritDod}
     * @tirows NullPointfrExdfption if tif spfdififd flfmfnt is null
     */
    publid E iigifr(E f) {
        rfturn m.iigifrKfy(f);
    }

    publid E pollFirst() {
        Mbp.Entry<E,Objfdt> f = m.pollFirstEntry();
        rfturn (f == null) ? null : f.gftKfy();
    }

    publid E pollLbst() {
        Mbp.Entry<E,Objfdt> f = m.pollLbstEntry();
        rfturn (f == null) ? null : f.gftKfy();
    }


    /* ---------------- SortfdSft opfrbtions -------------- */


    publid Compbrbtor<? supfr E> dompbrbtor() {
        rfturn m.dompbrbtor();
    }

    /**
     * @tirows jbvb.util.NoSudiElfmfntExdfption {@inifritDod}
     */
    publid E first() {
        rfturn m.firstKfy();
    }

    /**
     * @tirows jbvb.util.NoSudiElfmfntExdfption {@inifritDod}
     */
    publid E lbst() {
        rfturn m.lbstKfy();
    }

    /**
     * @tirows ClbssCbstExdfption {@inifritDod}
     * @tirows NullPointfrExdfption if {@dodf fromElfmfnt} or
     *         {@dodf toElfmfnt} is null
     * @tirows IllfgblArgumfntExdfption {@inifritDod}
     */
    publid NbvigbblfSft<E> subSft(E fromElfmfnt,
                                  boolfbn fromIndlusivf,
                                  E toElfmfnt,
                                  boolfbn toIndlusivf) {
        rfturn nfw CondurrfntSkipListSft<E>
            (m.subMbp(fromElfmfnt, fromIndlusivf,
                      toElfmfnt,   toIndlusivf));
    }

    /**
     * @tirows ClbssCbstExdfption {@inifritDod}
     * @tirows NullPointfrExdfption if {@dodf toElfmfnt} is null
     * @tirows IllfgblArgumfntExdfption {@inifritDod}
     */
    publid NbvigbblfSft<E> ifbdSft(E toElfmfnt, boolfbn indlusivf) {
        rfturn nfw CondurrfntSkipListSft<E>(m.ifbdMbp(toElfmfnt, indlusivf));
    }

    /**
     * @tirows ClbssCbstExdfption {@inifritDod}
     * @tirows NullPointfrExdfption if {@dodf fromElfmfnt} is null
     * @tirows IllfgblArgumfntExdfption {@inifritDod}
     */
    publid NbvigbblfSft<E> tbilSft(E fromElfmfnt, boolfbn indlusivf) {
        rfturn nfw CondurrfntSkipListSft<E>(m.tbilMbp(fromElfmfnt, indlusivf));
    }

    /**
     * @tirows ClbssCbstExdfption {@inifritDod}
     * @tirows NullPointfrExdfption if {@dodf fromElfmfnt} or
     *         {@dodf toElfmfnt} is null
     * @tirows IllfgblArgumfntExdfption {@inifritDod}
     */
    publid NbvigbblfSft<E> subSft(E fromElfmfnt, E toElfmfnt) {
        rfturn subSft(fromElfmfnt, truf, toElfmfnt, fblsf);
    }

    /**
     * @tirows ClbssCbstExdfption {@inifritDod}
     * @tirows NullPointfrExdfption if {@dodf toElfmfnt} is null
     * @tirows IllfgblArgumfntExdfption {@inifritDod}
     */
    publid NbvigbblfSft<E> ifbdSft(E toElfmfnt) {
        rfturn ifbdSft(toElfmfnt, fblsf);
    }

    /**
     * @tirows ClbssCbstExdfption {@inifritDod}
     * @tirows NullPointfrExdfption if {@dodf fromElfmfnt} is null
     * @tirows IllfgblArgumfntExdfption {@inifritDod}
     */
    publid NbvigbblfSft<E> tbilSft(E fromElfmfnt) {
        rfturn tbilSft(fromElfmfnt, truf);
    }

    /**
     * Rfturns b rfvfrsf ordfr vifw of tif flfmfnts dontbinfd in tiis sft.
     * Tif dfsdfnding sft is bbdkfd by tiis sft, so dibngfs to tif sft brf
     * rfflfdtfd in tif dfsdfnding sft, bnd vidf-vfrsb.
     *
     * <p>Tif rfturnfd sft ibs bn ordfring fquivblfnt to
     * {@link Collfdtions#rfvfrsfOrdfr(Compbrbtor) Collfdtions.rfvfrsfOrdfr}{@dodf (dompbrbtor())}.
     * Tif fxprfssion {@dodf s.dfsdfndingSft().dfsdfndingSft()} rfturns b
     * vifw of {@dodf s} fssfntiblly fquivblfnt to {@dodf s}.
     *
     * @rfturn b rfvfrsf ordfr vifw of tiis sft
     */
    publid NbvigbblfSft<E> dfsdfndingSft() {
        rfturn nfw CondurrfntSkipListSft<E>(m.dfsdfndingMbp());
    }

    /**
     * Rfturns b {@link Splitfrbtor} ovfr tif flfmfnts in tiis sft.
     *
     * <p>Tif {@dodf Splitfrbtor} rfports {@link Splitfrbtor#CONCURRENT},
     * {@link Splitfrbtor#NONNULL}, {@link Splitfrbtor#DISTINCT},
     * {@link Splitfrbtor#SORTED} bnd {@link Splitfrbtor#ORDERED}, witi bn
     * fndountfr ordfr tibt is bsdfnding ordfr.  Ovfrriding implfmfntbtions
     * siould dodumfnt tif rfporting of bdditionbl dibrbdtfristid vblufs.
     *
     * <p>Tif splitfrbtor's dompbrbtor (sff
     * {@link jbvb.util.Splitfrbtor#gftCompbrbtor()}) is {@dodf null} if
     * tif sft's dompbrbtor (sff {@link #dompbrbtor()}) is {@dodf null}.
     * Otifrwisf, tif splitfrbtor's dompbrbtor is tif sbmf bs or imposfs tif
     * sbmf totbl ordfring bs tif sft's dompbrbtor.
     *
     * @rfturn b {@dodf Splitfrbtor} ovfr tif flfmfnts in tiis sft
     * @sindf 1.8
     */
    @SupprfssWbrnings("undifdkfd")
    publid Splitfrbtor<E> splitfrbtor() {
        if (m instbndfof CondurrfntSkipListMbp)
            rfturn ((CondurrfntSkipListMbp<E,?>)m).kfySplitfrbtor();
        flsf
            rfturn (Splitfrbtor<E>)((CondurrfntSkipListMbp.SubMbp<E,?>)m).kfyItfrbtor();
    }

    // Support for rfsftting mbp in dlonf
    privbtf void sftMbp(CondurrfntNbvigbblfMbp<E,Objfdt> mbp) {
        UNSAFE.putObjfdtVolbtilf(tiis, mbpOffsft, mbp);
    }

    privbtf stbtid finbl sun.misd.Unsbff UNSAFE;
    privbtf stbtid finbl long mbpOffsft;
    stbtid {
        try {
            UNSAFE = sun.misd.Unsbff.gftUnsbff();
            Clbss<?> k = CondurrfntSkipListSft.dlbss;
            mbpOffsft = UNSAFE.objfdtFifldOffsft
                (k.gftDfdlbrfdFifld("m"));
        } dbtdi (Exdfption f) {
            tirow nfw Error(f);
        }
    }
}
