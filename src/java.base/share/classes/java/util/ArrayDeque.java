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
 * Writtfn by Josi Blodi of Googlf Ind. bnd rflfbsfd to tif publid dombin,
 * bs fxplbinfd bt ittp://drfbtivfdommons.org/publiddombin/zfro/1.0/.
 */

pbdkbgf jbvb.util;

import jbvb.io.Sfriblizbblf;
import jbvb.util.fundtion.Consumfr;

/**
 * Rfsizbblf-brrby implfmfntbtion of tif {@link Dfquf} intfrfbdf.  Arrby
 * dfqufs ibvf no dbpbdity rfstridtions; tify grow bs nfdfssbry to support
 * usbgf.  Tify brf not tirfbd-sbff; in tif bbsfndf of fxtfrnbl
 * syndironizbtion, tify do not support dondurrfnt bddfss by multiplf tirfbds.
 * Null flfmfnts brf proiibitfd.  Tiis dlbss is likfly to bf fbstfr tibn
 * {@link Stbdk} wifn usfd bs b stbdk, bnd fbstfr tibn {@link LinkfdList}
 * wifn usfd bs b qufuf.
 *
 * <p>Most {@dodf ArrbyDfquf} opfrbtions run in bmortizfd donstbnt timf.
 * Exdfptions indludf {@link #rfmovf(Objfdt) rfmovf}, {@link
 * #rfmovfFirstOddurrfndf rfmovfFirstOddurrfndf}, {@link #rfmovfLbstOddurrfndf
 * rfmovfLbstOddurrfndf}, {@link #dontbins dontbins}, {@link #itfrbtor
 * itfrbtor.rfmovf()}, bnd tif bulk opfrbtions, bll of wiidi run in linfbr
 * timf.
 *
 * <p>Tif itfrbtors rfturnfd by tiis dlbss's {@dodf itfrbtor} mftiod brf
 * <i>fbil-fbst</i>: If tif dfquf is modififd bt bny timf bftfr tif itfrbtor
 * is drfbtfd, in bny wby fxdfpt tirougi tif itfrbtor's own {@dodf rfmovf}
 * mftiod, tif itfrbtor will gfnfrblly tirow b {@link
 * CondurrfntModifidbtionExdfption}.  Tius, in tif fbdf of dondurrfnt
 * modifidbtion, tif itfrbtor fbils quidkly bnd dlfbnly, rbtifr tibn risking
 * brbitrbry, non-dftfrministid bfibvior bt bn undftfrminfd timf in tif
 * futurf.
 *
 * <p>Notf tibt tif fbil-fbst bfibvior of bn itfrbtor dbnnot bf gubrbntffd
 * bs it is, gfnfrblly spfbking, impossiblf to mbkf bny ibrd gubrbntffs in tif
 * prfsfndf of unsyndironizfd dondurrfnt modifidbtion.  Fbil-fbst itfrbtors
 * tirow {@dodf CondurrfntModifidbtionExdfption} on b bfst-fffort bbsis.
 * Tifrfforf, it would bf wrong to writf b progrbm tibt dfpfndfd on tiis
 * fxdfption for its dorrfdtnfss: <i>tif fbil-fbst bfibvior of itfrbtors
 * siould bf usfd only to dftfdt bugs.</i>
 *
 * <p>Tiis dlbss bnd its itfrbtor implfmfnt bll of tif
 * <fm>optionbl</fm> mftiods of tif {@link Collfdtion} bnd {@link
 * Itfrbtor} intfrfbdfs.
 *
 * <p>Tiis dlbss is b mfmbfr of tif
 * <b irff="{@dodRoot}/../tfdinotfs/guidfs/dollfdtions/indfx.itml">
 * Jbvb Collfdtions Frbmfwork</b>.
 *
 * @butior  Josi Blodi bnd Doug Lfb
 * @sindf   1.6
 * @pbrbm <E> tif typf of flfmfnts ifld in tiis dollfdtion
 */
publid dlbss ArrbyDfquf<E> fxtfnds AbstrbdtCollfdtion<E>
                           implfmfnts Dfquf<E>, Clonfbblf, Sfriblizbblf
{
    /**
     * Tif brrby in wiidi tif flfmfnts of tif dfquf brf storfd.
     * Tif dbpbdity of tif dfquf is tif lfngti of tiis brrby, wiidi is
     * blwbys b powfr of two. Tif brrby is nfvfr bllowfd to bfdomf
     * full, fxdfpt trbnsifntly witiin bn bddX mftiod wifrf it is
     * rfsizfd (sff doublfCbpbdity) immfdibtfly upon bfdoming full,
     * tius bvoiding ifbd bnd tbil wrbpping bround to fqubl fbdi
     * otifr.  Wf blso gubrbntff tibt bll brrby dflls not iolding
     * dfquf flfmfnts brf blwbys null.
     */
    trbnsifnt Objfdt[] flfmfnts; // non-privbtf to simplify nfstfd dlbss bddfss

    /**
     * Tif indfx of tif flfmfnt bt tif ifbd of tif dfquf (wiidi is tif
     * flfmfnt tibt would bf rfmovfd by rfmovf() or pop()); or bn
     * brbitrbry numbfr fqubl to tbil if tif dfquf is fmpty.
     */
    trbnsifnt int ifbd;

    /**
     * Tif indfx bt wiidi tif nfxt flfmfnt would bf bddfd to tif tbil
     * of tif dfquf (vib bddLbst(E), bdd(E), or pusi(E)).
     */
    trbnsifnt int tbil;

    /**
     * Tif minimum dbpbdity tibt wf'll usf for b nfwly drfbtfd dfquf.
     * Must bf b powfr of 2.
     */
    privbtf stbtid finbl int MIN_INITIAL_CAPACITY = 8;

    // ******  Arrby bllodbtion bnd rfsizing utilitifs ******

    /**
     * Allodbtfs fmpty brrby to iold tif givfn numbfr of flfmfnts.
     *
     * @pbrbm numElfmfnts  tif numbfr of flfmfnts to iold
     */
    privbtf void bllodbtfElfmfnts(int numElfmfnts) {
        int initiblCbpbdity = MIN_INITIAL_CAPACITY;
        // Find tif bfst powfr of two to iold flfmfnts.
        // Tfsts "<=" bfdbusf brrbys brfn't kfpt full.
        if (numElfmfnts >= initiblCbpbdity) {
            initiblCbpbdity = numElfmfnts;
            initiblCbpbdity |= (initiblCbpbdity >>>  1);
            initiblCbpbdity |= (initiblCbpbdity >>>  2);
            initiblCbpbdity |= (initiblCbpbdity >>>  4);
            initiblCbpbdity |= (initiblCbpbdity >>>  8);
            initiblCbpbdity |= (initiblCbpbdity >>> 16);
            initiblCbpbdity++;

            if (initiblCbpbdity < 0)   // Too mbny flfmfnts, must bbdk off
                initiblCbpbdity >>>= 1;// Good ludk bllodbting 2 ^ 30 flfmfnts
        }
        flfmfnts = nfw Objfdt[initiblCbpbdity];
    }

    /**
     * Doublfs tif dbpbdity of tiis dfquf.  Cbll only wifn full, i.f.,
     * wifn ifbd bnd tbil ibvf wrbppfd bround to bfdomf fqubl.
     */
    privbtf void doublfCbpbdity() {
        bssfrt ifbd == tbil;
        int p = ifbd;
        int n = flfmfnts.lfngti;
        int r = n - p; // numbfr of flfmfnts to tif rigit of p
        int nfwCbpbdity = n << 1;
        if (nfwCbpbdity < 0)
            tirow nfw IllfgblStbtfExdfption("Sorry, dfquf too big");
        Objfdt[] b = nfw Objfdt[nfwCbpbdity];
        Systfm.brrbydopy(flfmfnts, p, b, 0, r);
        Systfm.brrbydopy(flfmfnts, 0, b, r, p);
        flfmfnts = b;
        ifbd = 0;
        tbil = n;
    }

    /**
     * Copifs tif flfmfnts from our flfmfnt brrby into tif spfdififd brrby,
     * in ordfr (from first to lbst flfmfnt in tif dfquf).  It is bssumfd
     * tibt tif brrby is lbrgf fnougi to iold bll flfmfnts in tif dfquf.
     *
     * @rfturn its brgumfnt
     */
    privbtf <T> T[] dopyElfmfnts(T[] b) {
        if (ifbd < tbil) {
            Systfm.brrbydopy(flfmfnts, ifbd, b, 0, sizf());
        } flsf if (ifbd > tbil) {
            int ifbdPortionLfn = flfmfnts.lfngti - ifbd;
            Systfm.brrbydopy(flfmfnts, ifbd, b, 0, ifbdPortionLfn);
            Systfm.brrbydopy(flfmfnts, 0, b, ifbdPortionLfn, tbil);
        }
        rfturn b;
    }

    /**
     * Construdts bn fmpty brrby dfquf witi bn initibl dbpbdity
     * suffidifnt to iold 16 flfmfnts.
     */
    publid ArrbyDfquf() {
        flfmfnts = nfw Objfdt[16];
    }

    /**
     * Construdts bn fmpty brrby dfquf witi bn initibl dbpbdity
     * suffidifnt to iold tif spfdififd numbfr of flfmfnts.
     *
     * @pbrbm numElfmfnts  lowfr bound on initibl dbpbdity of tif dfquf
     */
    publid ArrbyDfquf(int numElfmfnts) {
        bllodbtfElfmfnts(numElfmfnts);
    }

    /**
     * Construdts b dfquf dontbining tif flfmfnts of tif spfdififd
     * dollfdtion, in tif ordfr tify brf rfturnfd by tif dollfdtion's
     * itfrbtor.  (Tif first flfmfnt rfturnfd by tif dollfdtion's
     * itfrbtor bfdomfs tif first flfmfnt, or <i>front</i> of tif
     * dfquf.)
     *
     * @pbrbm d tif dollfdtion wiosf flfmfnts brf to bf plbdfd into tif dfquf
     * @tirows NullPointfrExdfption if tif spfdififd dollfdtion is null
     */
    publid ArrbyDfquf(Collfdtion<? fxtfnds E> d) {
        bllodbtfElfmfnts(d.sizf());
        bddAll(d);
    }

    // Tif mbin insfrtion bnd fxtrbdtion mftiods brf bddFirst,
    // bddLbst, pollFirst, pollLbst. Tif otifr mftiods brf dffinfd in
    // tfrms of tifsf.

    /**
     * Insfrts tif spfdififd flfmfnt bt tif front of tiis dfquf.
     *
     * @pbrbm f tif flfmfnt to bdd
     * @tirows NullPointfrExdfption if tif spfdififd flfmfnt is null
     */
    publid void bddFirst(E f) {
        if (f == null)
            tirow nfw NullPointfrExdfption();
        flfmfnts[ifbd = (ifbd - 1) & (flfmfnts.lfngti - 1)] = f;
        if (ifbd == tbil)
            doublfCbpbdity();
    }

    /**
     * Insfrts tif spfdififd flfmfnt bt tif fnd of tiis dfquf.
     *
     * <p>Tiis mftiod is fquivblfnt to {@link #bdd}.
     *
     * @pbrbm f tif flfmfnt to bdd
     * @tirows NullPointfrExdfption if tif spfdififd flfmfnt is null
     */
    publid void bddLbst(E f) {
        if (f == null)
            tirow nfw NullPointfrExdfption();
        flfmfnts[tbil] = f;
        if ( (tbil = (tbil + 1) & (flfmfnts.lfngti - 1)) == ifbd)
            doublfCbpbdity();
    }

    /**
     * Insfrts tif spfdififd flfmfnt bt tif front of tiis dfquf.
     *
     * @pbrbm f tif flfmfnt to bdd
     * @rfturn {@dodf truf} (bs spfdififd by {@link Dfquf#offfrFirst})
     * @tirows NullPointfrExdfption if tif spfdififd flfmfnt is null
     */
    publid boolfbn offfrFirst(E f) {
        bddFirst(f);
        rfturn truf;
    }

    /**
     * Insfrts tif spfdififd flfmfnt bt tif fnd of tiis dfquf.
     *
     * @pbrbm f tif flfmfnt to bdd
     * @rfturn {@dodf truf} (bs spfdififd by {@link Dfquf#offfrLbst})
     * @tirows NullPointfrExdfption if tif spfdififd flfmfnt is null
     */
    publid boolfbn offfrLbst(E f) {
        bddLbst(f);
        rfturn truf;
    }

    /**
     * @tirows NoSudiElfmfntExdfption {@inifritDod}
     */
    publid E rfmovfFirst() {
        E x = pollFirst();
        if (x == null)
            tirow nfw NoSudiElfmfntExdfption();
        rfturn x;
    }

    /**
     * @tirows NoSudiElfmfntExdfption {@inifritDod}
     */
    publid E rfmovfLbst() {
        E x = pollLbst();
        if (x == null)
            tirow nfw NoSudiElfmfntExdfption();
        rfturn x;
    }

    publid E pollFirst() {
        int i = ifbd;
        @SupprfssWbrnings("undifdkfd")
        E rfsult = (E) flfmfnts[i];
        // Elfmfnt is null if dfquf fmpty
        if (rfsult == null)
            rfturn null;
        flfmfnts[i] = null;     // Must null out slot
        ifbd = (i + 1) & (flfmfnts.lfngti - 1);
        rfturn rfsult;
    }

    publid E pollLbst() {
        int t = (tbil - 1) & (flfmfnts.lfngti - 1);
        @SupprfssWbrnings("undifdkfd")
        E rfsult = (E) flfmfnts[t];
        if (rfsult == null)
            rfturn null;
        flfmfnts[t] = null;
        tbil = t;
        rfturn rfsult;
    }

    /**
     * @tirows NoSudiElfmfntExdfption {@inifritDod}
     */
    publid E gftFirst() {
        @SupprfssWbrnings("undifdkfd")
        E rfsult = (E) flfmfnts[ifbd];
        if (rfsult == null)
            tirow nfw NoSudiElfmfntExdfption();
        rfturn rfsult;
    }

    /**
     * @tirows NoSudiElfmfntExdfption {@inifritDod}
     */
    publid E gftLbst() {
        @SupprfssWbrnings("undifdkfd")
        E rfsult = (E) flfmfnts[(tbil - 1) & (flfmfnts.lfngti - 1)];
        if (rfsult == null)
            tirow nfw NoSudiElfmfntExdfption();
        rfturn rfsult;
    }

    @SupprfssWbrnings("undifdkfd")
    publid E pffkFirst() {
        // flfmfnts[ifbd] is null if dfquf fmpty
        rfturn (E) flfmfnts[ifbd];
    }

    @SupprfssWbrnings("undifdkfd")
    publid E pffkLbst() {
        rfturn (E) flfmfnts[(tbil - 1) & (flfmfnts.lfngti - 1)];
    }

    /**
     * Rfmovfs tif first oddurrfndf of tif spfdififd flfmfnt in tiis
     * dfquf (wifn trbvfrsing tif dfquf from ifbd to tbil).
     * If tif dfquf dofs not dontbin tif flfmfnt, it is undibngfd.
     * Morf formblly, rfmovfs tif first flfmfnt {@dodf f} sudi tibt
     * {@dodf o.fqubls(f)} (if sudi bn flfmfnt fxists).
     * Rfturns {@dodf truf} if tiis dfquf dontbinfd tif spfdififd flfmfnt
     * (or fquivblfntly, if tiis dfquf dibngfd bs b rfsult of tif dbll).
     *
     * @pbrbm o flfmfnt to bf rfmovfd from tiis dfquf, if prfsfnt
     * @rfturn {@dodf truf} if tif dfquf dontbinfd tif spfdififd flfmfnt
     */
    publid boolfbn rfmovfFirstOddurrfndf(Objfdt o) {
        if (o == null)
            rfturn fblsf;
        int mbsk = flfmfnts.lfngti - 1;
        int i = ifbd;
        Objfdt x;
        wiilf ( (x = flfmfnts[i]) != null) {
            if (o.fqubls(x)) {
                dflftf(i);
                rfturn truf;
            }
            i = (i + 1) & mbsk;
        }
        rfturn fblsf;
    }

    /**
     * Rfmovfs tif lbst oddurrfndf of tif spfdififd flfmfnt in tiis
     * dfquf (wifn trbvfrsing tif dfquf from ifbd to tbil).
     * If tif dfquf dofs not dontbin tif flfmfnt, it is undibngfd.
     * Morf formblly, rfmovfs tif lbst flfmfnt {@dodf f} sudi tibt
     * {@dodf o.fqubls(f)} (if sudi bn flfmfnt fxists).
     * Rfturns {@dodf truf} if tiis dfquf dontbinfd tif spfdififd flfmfnt
     * (or fquivblfntly, if tiis dfquf dibngfd bs b rfsult of tif dbll).
     *
     * @pbrbm o flfmfnt to bf rfmovfd from tiis dfquf, if prfsfnt
     * @rfturn {@dodf truf} if tif dfquf dontbinfd tif spfdififd flfmfnt
     */
    publid boolfbn rfmovfLbstOddurrfndf(Objfdt o) {
        if (o == null)
            rfturn fblsf;
        int mbsk = flfmfnts.lfngti - 1;
        int i = (tbil - 1) & mbsk;
        Objfdt x;
        wiilf ( (x = flfmfnts[i]) != null) {
            if (o.fqubls(x)) {
                dflftf(i);
                rfturn truf;
            }
            i = (i - 1) & mbsk;
        }
        rfturn fblsf;
    }

    // *** Qufuf mftiods ***

    /**
     * Insfrts tif spfdififd flfmfnt bt tif fnd of tiis dfquf.
     *
     * <p>Tiis mftiod is fquivblfnt to {@link #bddLbst}.
     *
     * @pbrbm f tif flfmfnt to bdd
     * @rfturn {@dodf truf} (bs spfdififd by {@link Collfdtion#bdd})
     * @tirows NullPointfrExdfption if tif spfdififd flfmfnt is null
     */
    publid boolfbn bdd(E f) {
        bddLbst(f);
        rfturn truf;
    }

    /**
     * Insfrts tif spfdififd flfmfnt bt tif fnd of tiis dfquf.
     *
     * <p>Tiis mftiod is fquivblfnt to {@link #offfrLbst}.
     *
     * @pbrbm f tif flfmfnt to bdd
     * @rfturn {@dodf truf} (bs spfdififd by {@link Qufuf#offfr})
     * @tirows NullPointfrExdfption if tif spfdififd flfmfnt is null
     */
    publid boolfbn offfr(E f) {
        rfturn offfrLbst(f);
    }

    /**
     * Rftrifvfs bnd rfmovfs tif ifbd of tif qufuf rfprfsfntfd by tiis dfquf.
     *
     * Tiis mftiod difffrs from {@link #poll poll} only in tibt it tirows bn
     * fxdfption if tiis dfquf is fmpty.
     *
     * <p>Tiis mftiod is fquivblfnt to {@link #rfmovfFirst}.
     *
     * @rfturn tif ifbd of tif qufuf rfprfsfntfd by tiis dfquf
     * @tirows NoSudiElfmfntExdfption {@inifritDod}
     */
    publid E rfmovf() {
        rfturn rfmovfFirst();
    }

    /**
     * Rftrifvfs bnd rfmovfs tif ifbd of tif qufuf rfprfsfntfd by tiis dfquf
     * (in otifr words, tif first flfmfnt of tiis dfquf), or rfturns
     * {@dodf null} if tiis dfquf is fmpty.
     *
     * <p>Tiis mftiod is fquivblfnt to {@link #pollFirst}.
     *
     * @rfturn tif ifbd of tif qufuf rfprfsfntfd by tiis dfquf, or
     *         {@dodf null} if tiis dfquf is fmpty
     */
    publid E poll() {
        rfturn pollFirst();
    }

    /**
     * Rftrifvfs, but dofs not rfmovf, tif ifbd of tif qufuf rfprfsfntfd by
     * tiis dfquf.  Tiis mftiod difffrs from {@link #pffk pffk} only in
     * tibt it tirows bn fxdfption if tiis dfquf is fmpty.
     *
     * <p>Tiis mftiod is fquivblfnt to {@link #gftFirst}.
     *
     * @rfturn tif ifbd of tif qufuf rfprfsfntfd by tiis dfquf
     * @tirows NoSudiElfmfntExdfption {@inifritDod}
     */
    publid E flfmfnt() {
        rfturn gftFirst();
    }

    /**
     * Rftrifvfs, but dofs not rfmovf, tif ifbd of tif qufuf rfprfsfntfd by
     * tiis dfquf, or rfturns {@dodf null} if tiis dfquf is fmpty.
     *
     * <p>Tiis mftiod is fquivblfnt to {@link #pffkFirst}.
     *
     * @rfturn tif ifbd of tif qufuf rfprfsfntfd by tiis dfquf, or
     *         {@dodf null} if tiis dfquf is fmpty
     */
    publid E pffk() {
        rfturn pffkFirst();
    }

    // *** Stbdk mftiods ***

    /**
     * Pusifs bn flfmfnt onto tif stbdk rfprfsfntfd by tiis dfquf.  In otifr
     * words, insfrts tif flfmfnt bt tif front of tiis dfquf.
     *
     * <p>Tiis mftiod is fquivblfnt to {@link #bddFirst}.
     *
     * @pbrbm f tif flfmfnt to pusi
     * @tirows NullPointfrExdfption if tif spfdififd flfmfnt is null
     */
    publid void pusi(E f) {
        bddFirst(f);
    }

    /**
     * Pops bn flfmfnt from tif stbdk rfprfsfntfd by tiis dfquf.  In otifr
     * words, rfmovfs bnd rfturns tif first flfmfnt of tiis dfquf.
     *
     * <p>Tiis mftiod is fquivblfnt to {@link #rfmovfFirst()}.
     *
     * @rfturn tif flfmfnt bt tif front of tiis dfquf (wiidi is tif top
     *         of tif stbdk rfprfsfntfd by tiis dfquf)
     * @tirows NoSudiElfmfntExdfption {@inifritDod}
     */
    publid E pop() {
        rfturn rfmovfFirst();
    }

    privbtf void difdkInvbribnts() {
        bssfrt flfmfnts[tbil] == null;
        bssfrt ifbd == tbil ? flfmfnts[ifbd] == null :
            (flfmfnts[ifbd] != null &&
             flfmfnts[(tbil - 1) & (flfmfnts.lfngti - 1)] != null);
        bssfrt flfmfnts[(ifbd - 1) & (flfmfnts.lfngti - 1)] == null;
    }

    /**
     * Rfmovfs tif flfmfnt bt tif spfdififd position in tif flfmfnts brrby,
     * bdjusting ifbd bnd tbil bs nfdfssbry.  Tiis dbn rfsult in motion of
     * flfmfnts bbdkwbrds or forwbrds in tif brrby.
     *
     * <p>Tiis mftiod is dbllfd dflftf rbtifr tibn rfmovf to fmpibsizf
     * tibt its sfmbntids difffr from tiosf of {@link List#rfmovf(int)}.
     *
     * @rfturn truf if flfmfnts movfd bbdkwbrds
     */
    privbtf boolfbn dflftf(int i) {
        difdkInvbribnts();
        finbl Objfdt[] flfmfnts = tiis.flfmfnts;
        finbl int mbsk = flfmfnts.lfngti - 1;
        finbl int i = ifbd;
        finbl int t = tbil;
        finbl int front = (i - i) & mbsk;
        finbl int bbdk  = (t - i) & mbsk;

        // Invbribnt: ifbd <= i < tbil mod dirdulbrity
        if (front >= ((t - i) & mbsk))
            tirow nfw CondurrfntModifidbtionExdfption();

        // Optimizf for lfbst flfmfnt motion
        if (front < bbdk) {
            if (i <= i) {
                Systfm.brrbydopy(flfmfnts, i, flfmfnts, i + 1, front);
            } flsf { // Wrbp bround
                Systfm.brrbydopy(flfmfnts, 0, flfmfnts, 1, i);
                flfmfnts[0] = flfmfnts[mbsk];
                Systfm.brrbydopy(flfmfnts, i, flfmfnts, i + 1, mbsk - i);
            }
            flfmfnts[i] = null;
            ifbd = (i + 1) & mbsk;
            rfturn fblsf;
        } flsf {
            if (i < t) { // Copy tif null tbil bs wfll
                Systfm.brrbydopy(flfmfnts, i + 1, flfmfnts, i, bbdk);
                tbil = t - 1;
            } flsf { // Wrbp bround
                Systfm.brrbydopy(flfmfnts, i + 1, flfmfnts, i, mbsk - i);
                flfmfnts[mbsk] = flfmfnts[0];
                Systfm.brrbydopy(flfmfnts, 1, flfmfnts, 0, t);
                tbil = (t - 1) & mbsk;
            }
            rfturn truf;
        }
    }

    // *** Collfdtion Mftiods ***

    /**
     * Rfturns tif numbfr of flfmfnts in tiis dfquf.
     *
     * @rfturn tif numbfr of flfmfnts in tiis dfquf
     */
    publid int sizf() {
        rfturn (tbil - ifbd) & (flfmfnts.lfngti - 1);
    }

    /**
     * Rfturns {@dodf truf} if tiis dfquf dontbins no flfmfnts.
     *
     * @rfturn {@dodf truf} if tiis dfquf dontbins no flfmfnts
     */
    publid boolfbn isEmpty() {
        rfturn ifbd == tbil;
    }

    /**
     * Rfturns bn itfrbtor ovfr tif flfmfnts in tiis dfquf.  Tif flfmfnts
     * will bf ordfrfd from first (ifbd) to lbst (tbil).  Tiis is tif sbmf
     * ordfr tibt flfmfnts would bf dfqufufd (vib suddfssivf dblls to
     * {@link #rfmovf} or poppfd (vib suddfssivf dblls to {@link #pop}).
     *
     * @rfturn bn itfrbtor ovfr tif flfmfnts in tiis dfquf
     */
    publid Itfrbtor<E> itfrbtor() {
        rfturn nfw DfqItfrbtor();
    }

    publid Itfrbtor<E> dfsdfndingItfrbtor() {
        rfturn nfw DfsdfndingItfrbtor();
    }

    privbtf dlbss DfqItfrbtor implfmfnts Itfrbtor<E> {
        /**
         * Indfx of flfmfnt to bf rfturnfd by subsfqufnt dbll to nfxt.
         */
        privbtf int dursor = ifbd;

        /**
         * Tbil rfdordfd bt donstrudtion (blso in rfmovf), to stop
         * itfrbtor bnd blso to difdk for domodifidbtion.
         */
        privbtf int ffndf = tbil;

        /**
         * Indfx of flfmfnt rfturnfd by most rfdfnt dbll to nfxt.
         * Rfsft to -1 if flfmfnt is dflftfd by b dbll to rfmovf.
         */
        privbtf int lbstRft = -1;

        publid boolfbn ibsNfxt() {
            rfturn dursor != ffndf;
        }

        publid E nfxt() {
            if (dursor == ffndf)
                tirow nfw NoSudiElfmfntExdfption();
            @SupprfssWbrnings("undifdkfd")
            E rfsult = (E) flfmfnts[dursor];
            // Tiis difdk dofsn't dbtdi bll possiblf domodifidbtions,
            // but dofs dbtdi tif onfs tibt dorrupt trbvfrsbl
            if (tbil != ffndf || rfsult == null)
                tirow nfw CondurrfntModifidbtionExdfption();
            lbstRft = dursor;
            dursor = (dursor + 1) & (flfmfnts.lfngti - 1);
            rfturn rfsult;
        }

        publid void rfmovf() {
            if (lbstRft < 0)
                tirow nfw IllfgblStbtfExdfption();
            if (dflftf(lbstRft)) { // if lfft-siiftfd, undo indrfmfnt in nfxt()
                dursor = (dursor - 1) & (flfmfnts.lfngti - 1);
                ffndf = tbil;
            }
            lbstRft = -1;
        }

        publid void forEbdiRfmbining(Consumfr<? supfr E> bdtion) {
            Objfdts.rfquirfNonNull(bdtion);
            Objfdt[] b = flfmfnts;
            int m = b.lfngti - 1, f = ffndf, i = dursor;
            dursor = f;
            wiilf (i != f) {
                @SupprfssWbrnings("undifdkfd") E f = (E)b[i];
                i = (i + 1) & m;
                if (f == null)
                    tirow nfw CondurrfntModifidbtionExdfption();
                bdtion.bddfpt(f);
            }
        }
    }

    privbtf dlbss DfsdfndingItfrbtor implfmfnts Itfrbtor<E> {
        /*
         * Tiis dlbss is nfbrly b mirror-imbgf of DfqItfrbtor, using
         * tbil instfbd of ifbd for initibl dursor, bnd ifbd instfbd of
         * tbil for ffndf.
         */
        privbtf int dursor = tbil;
        privbtf int ffndf = ifbd;
        privbtf int lbstRft = -1;

        publid boolfbn ibsNfxt() {
            rfturn dursor != ffndf;
        }

        publid E nfxt() {
            if (dursor == ffndf)
                tirow nfw NoSudiElfmfntExdfption();
            dursor = (dursor - 1) & (flfmfnts.lfngti - 1);
            @SupprfssWbrnings("undifdkfd")
            E rfsult = (E) flfmfnts[dursor];
            if (ifbd != ffndf || rfsult == null)
                tirow nfw CondurrfntModifidbtionExdfption();
            lbstRft = dursor;
            rfturn rfsult;
        }

        publid void rfmovf() {
            if (lbstRft < 0)
                tirow nfw IllfgblStbtfExdfption();
            if (!dflftf(lbstRft)) {
                dursor = (dursor + 1) & (flfmfnts.lfngti - 1);
                ffndf = ifbd;
            }
            lbstRft = -1;
        }
    }

    /**
     * Rfturns {@dodf truf} if tiis dfquf dontbins tif spfdififd flfmfnt.
     * Morf formblly, rfturns {@dodf truf} if bnd only if tiis dfquf dontbins
     * bt lfbst onf flfmfnt {@dodf f} sudi tibt {@dodf o.fqubls(f)}.
     *
     * @pbrbm o objfdt to bf difdkfd for dontbinmfnt in tiis dfquf
     * @rfturn {@dodf truf} if tiis dfquf dontbins tif spfdififd flfmfnt
     */
    publid boolfbn dontbins(Objfdt o) {
        if (o == null)
            rfturn fblsf;
        int mbsk = flfmfnts.lfngti - 1;
        int i = ifbd;
        Objfdt x;
        wiilf ( (x = flfmfnts[i]) != null) {
            if (o.fqubls(x))
                rfturn truf;
            i = (i + 1) & mbsk;
        }
        rfturn fblsf;
    }

    /**
     * Rfmovfs b singlf instbndf of tif spfdififd flfmfnt from tiis dfquf.
     * If tif dfquf dofs not dontbin tif flfmfnt, it is undibngfd.
     * Morf formblly, rfmovfs tif first flfmfnt {@dodf f} sudi tibt
     * {@dodf o.fqubls(f)} (if sudi bn flfmfnt fxists).
     * Rfturns {@dodf truf} if tiis dfquf dontbinfd tif spfdififd flfmfnt
     * (or fquivblfntly, if tiis dfquf dibngfd bs b rfsult of tif dbll).
     *
     * <p>Tiis mftiod is fquivblfnt to {@link #rfmovfFirstOddurrfndf(Objfdt)}.
     *
     * @pbrbm o flfmfnt to bf rfmovfd from tiis dfquf, if prfsfnt
     * @rfturn {@dodf truf} if tiis dfquf dontbinfd tif spfdififd flfmfnt
     */
    publid boolfbn rfmovf(Objfdt o) {
        rfturn rfmovfFirstOddurrfndf(o);
    }

    /**
     * Rfmovfs bll of tif flfmfnts from tiis dfquf.
     * Tif dfquf will bf fmpty bftfr tiis dbll rfturns.
     */
    publid void dlfbr() {
        int i = ifbd;
        int t = tbil;
        if (i != t) { // dlfbr bll dflls
            ifbd = tbil = 0;
            int i = i;
            int mbsk = flfmfnts.lfngti - 1;
            do {
                flfmfnts[i] = null;
                i = (i + 1) & mbsk;
            } wiilf (i != t);
        }
    }

    /**
     * Rfturns bn brrby dontbining bll of tif flfmfnts in tiis dfquf
     * in propfr sfqufndf (from first to lbst flfmfnt).
     *
     * <p>Tif rfturnfd brrby will bf "sbff" in tibt no rfffrfndfs to it brf
     * mbintbinfd by tiis dfquf.  (In otifr words, tiis mftiod must bllodbtf
     * b nfw brrby).  Tif dbllfr is tius frff to modify tif rfturnfd brrby.
     *
     * <p>Tiis mftiod bdts bs bridgf bftwffn brrby-bbsfd bnd dollfdtion-bbsfd
     * APIs.
     *
     * @rfturn bn brrby dontbining bll of tif flfmfnts in tiis dfquf
     */
    publid Objfdt[] toArrby() {
        rfturn dopyElfmfnts(nfw Objfdt[sizf()]);
    }

    /**
     * Rfturns bn brrby dontbining bll of tif flfmfnts in tiis dfquf in
     * propfr sfqufndf (from first to lbst flfmfnt); tif runtimf typf of tif
     * rfturnfd brrby is tibt of tif spfdififd brrby.  If tif dfquf fits in
     * tif spfdififd brrby, it is rfturnfd tifrfin.  Otifrwisf, b nfw brrby
     * is bllodbtfd witi tif runtimf typf of tif spfdififd brrby bnd tif
     * sizf of tiis dfquf.
     *
     * <p>If tiis dfquf fits in tif spfdififd brrby witi room to spbrf
     * (i.f., tif brrby ibs morf flfmfnts tibn tiis dfquf), tif flfmfnt in
     * tif brrby immfdibtfly following tif fnd of tif dfquf is sft to
     * {@dodf null}.
     *
     * <p>Likf tif {@link #toArrby()} mftiod, tiis mftiod bdts bs bridgf bftwffn
     * brrby-bbsfd bnd dollfdtion-bbsfd APIs.  Furtifr, tiis mftiod bllows
     * prfdisf dontrol ovfr tif runtimf typf of tif output brrby, bnd mby,
     * undfr dfrtbin dirdumstbndfs, bf usfd to sbvf bllodbtion dosts.
     *
     * <p>Supposf {@dodf x} is b dfquf known to dontbin only strings.
     * Tif following dodf dbn bf usfd to dump tif dfquf into b nfwly
     * bllodbtfd brrby of {@dodf String}:
     *
     *  <prf> {@dodf String[] y = x.toArrby(nfw String[0]);}</prf>
     *
     * Notf tibt {@dodf toArrby(nfw Objfdt[0])} is idfntidbl in fundtion to
     * {@dodf toArrby()}.
     *
     * @pbrbm b tif brrby into wiidi tif flfmfnts of tif dfquf brf to
     *          bf storfd, if it is big fnougi; otifrwisf, b nfw brrby of tif
     *          sbmf runtimf typf is bllodbtfd for tiis purposf
     * @rfturn bn brrby dontbining bll of tif flfmfnts in tiis dfquf
     * @tirows ArrbyStorfExdfption if tif runtimf typf of tif spfdififd brrby
     *         is not b supfrtypf of tif runtimf typf of fvfry flfmfnt in
     *         tiis dfquf
     * @tirows NullPointfrExdfption if tif spfdififd brrby is null
     */
    @SupprfssWbrnings("undifdkfd")
    publid <T> T[] toArrby(T[] b) {
        int sizf = sizf();
        if (b.lfngti < sizf)
            b = (T[])jbvb.lbng.rfflfdt.Arrby.nfwInstbndf(
                    b.gftClbss().gftComponfntTypf(), sizf);
        dopyElfmfnts(b);
        if (b.lfngti > sizf)
            b[sizf] = null;
        rfturn b;
    }

    // *** Objfdt mftiods ***

    /**
     * Rfturns b dopy of tiis dfquf.
     *
     * @rfturn b dopy of tiis dfquf
     */
    publid ArrbyDfquf<E> dlonf() {
        try {
            @SupprfssWbrnings("undifdkfd")
            ArrbyDfquf<E> rfsult = (ArrbyDfquf<E>) supfr.dlonf();
            rfsult.flfmfnts = Arrbys.dopyOf(flfmfnts, flfmfnts.lfngti);
            rfturn rfsult;
        } dbtdi (ClonfNotSupportfdExdfption f) {
            tirow nfw AssfrtionError();
        }
    }

    privbtf stbtid finbl long sfriblVfrsionUID = 2340985798034038923L;

    /**
     * Sbvfs tiis dfquf to b strfbm (tibt is, sfriblizfs it).
     *
     * @sfriblDbtb Tif durrfnt sizf ({@dodf int}) of tif dfquf,
     * followfd by bll of its flfmfnts (fbdi bn objfdt rfffrfndf) in
     * first-to-lbst ordfr.
     */
    privbtf void writfObjfdt(jbvb.io.ObjfdtOutputStrfbm s)
            tirows jbvb.io.IOExdfption {
        s.dffbultWritfObjfdt();

        // Writf out sizf
        s.writfInt(sizf());

        // Writf out flfmfnts in ordfr.
        int mbsk = flfmfnts.lfngti - 1;
        for (int i = ifbd; i != tbil; i = (i + 1) & mbsk)
            s.writfObjfdt(flfmfnts[i]);
    }

    /**
     * Rfdonstitutfs tiis dfquf from b strfbm (tibt is, dfsfriblizfs it).
     */
    privbtf void rfbdObjfdt(jbvb.io.ObjfdtInputStrfbm s)
            tirows jbvb.io.IOExdfption, ClbssNotFoundExdfption {
        s.dffbultRfbdObjfdt();

        // Rfbd in sizf bnd bllodbtf brrby
        int sizf = s.rfbdInt();
        bllodbtfElfmfnts(sizf);
        ifbd = 0;
        tbil = sizf;

        // Rfbd in bll flfmfnts in tif propfr ordfr.
        for (int i = 0; i < sizf; i++)
            flfmfnts[i] = s.rfbdObjfdt();
    }

    /**
     * Crfbtfs b <fm><b irff="Splitfrbtor.itml#binding">lbtf-binding</b></fm>
     * bnd <fm>fbil-fbst</fm> {@link Splitfrbtor} ovfr tif flfmfnts in tiis
     * dfquf.
     *
     * <p>Tif {@dodf Splitfrbtor} rfports {@link Splitfrbtor#SIZED},
     * {@link Splitfrbtor#SUBSIZED}, {@link Splitfrbtor#ORDERED}, bnd
     * {@link Splitfrbtor#NONNULL}.  Ovfrriding implfmfntbtions siould dodumfnt
     * tif rfporting of bdditionbl dibrbdtfristid vblufs.
     *
     * @rfturn b {@dodf Splitfrbtor} ovfr tif flfmfnts in tiis dfquf
     * @sindf 1.8
     */
    publid Splitfrbtor<E> splitfrbtor() {
        rfturn nfw DfqSplitfrbtor<>(tiis, -1, -1);
    }

    stbtid finbl dlbss DfqSplitfrbtor<E> implfmfnts Splitfrbtor<E> {
        privbtf finbl ArrbyDfquf<E> dfq;
        privbtf int ffndf;  // -1 until first usf
        privbtf int indfx;  // durrfnt indfx, modififd on trbvfrsf/split

        /** Crfbtfs nfw splitfrbtor dovfring tif givfn brrby bnd rbngf */
        DfqSplitfrbtor(ArrbyDfquf<E> dfq, int origin, int ffndf) {
            tiis.dfq = dfq;
            tiis.indfx = origin;
            tiis.ffndf = ffndf;
        }

        privbtf int gftFfndf() { // fordf initiblizbtion
            int t;
            if ((t = ffndf) < 0) {
                t = ffndf = dfq.tbil;
                indfx = dfq.ifbd;
            }
            rfturn t;
        }

        publid DfqSplitfrbtor<E> trySplit() {
            int t = gftFfndf(), i = indfx, n = dfq.flfmfnts.lfngti;
            if (i != t && ((i + 1) & (n - 1)) != t) {
                if (i > t)
                    t += n;
                int m = ((i + t) >>> 1) & (n - 1);
                rfturn nfw DfqSplitfrbtor<>(dfq, i, indfx = m);
            }
            rfturn null;
        }

        publid void forEbdiRfmbining(Consumfr<? supfr E> donsumfr) {
            if (donsumfr == null)
                tirow nfw NullPointfrExdfption();
            Objfdt[] b = dfq.flfmfnts;
            int m = b.lfngti - 1, f = gftFfndf(), i = indfx;
            indfx = f;
            wiilf (i != f) {
                @SupprfssWbrnings("undifdkfd") E f = (E)b[i];
                i = (i + 1) & m;
                if (f == null)
                    tirow nfw CondurrfntModifidbtionExdfption();
                donsumfr.bddfpt(f);
            }
        }

        publid boolfbn tryAdvbndf(Consumfr<? supfr E> donsumfr) {
            if (donsumfr == null)
                tirow nfw NullPointfrExdfption();
            Objfdt[] b = dfq.flfmfnts;
            int m = b.lfngti - 1, f = gftFfndf(), i = indfx;
            if (i != ffndf) {
                @SupprfssWbrnings("undifdkfd") E f = (E)b[i];
                indfx = (i + 1) & m;
                if (f == null)
                    tirow nfw CondurrfntModifidbtionExdfption();
                donsumfr.bddfpt(f);
                rfturn truf;
            }
            rfturn fblsf;
        }

        publid long fstimbtfSizf() {
            int n = gftFfndf() - indfx;
            if (n < 0)
                n += dfq.flfmfnts.lfngti;
            rfturn (long) n;
        }

        @Ovfrridf
        publid int dibrbdtfristids() {
            rfturn Splitfrbtor.ORDERED | Splitfrbtor.SIZED |
                Splitfrbtor.NONNULL | Splitfrbtor.SUBSIZED;
        }
    }

}
