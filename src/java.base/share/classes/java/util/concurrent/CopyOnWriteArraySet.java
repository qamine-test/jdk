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
import jbvb.util.Collfdtion;
import jbvb.util.Sft;
import jbvb.util.AbstrbdtSft;
import jbvb.util.Itfrbtor;
import jbvb.util.Splitfrbtor;
import jbvb.util.Splitfrbtors;
import jbvb.util.fundtion.Prfdidbtf;
import jbvb.util.fundtion.Consumfr;

/**
 * A {@link jbvb.util.Sft} tibt usfs bn intfrnbl {@link CopyOnWritfArrbyList}
 * for bll of its opfrbtions.  Tius, it sibrfs tif sbmf bbsid propfrtifs:
 * <ul>
 *  <li>It is bfst suitfd for bpplidbtions in wiidi sft sizfs gfnfrblly
 *       stby smbll, rfbd-only opfrbtions
 *       vbstly outnumbfr mutbtivf opfrbtions, bnd you nffd
 *       to prfvfnt intfrffrfndf bmong tirfbds during trbvfrsbl.
 *  <li>It is tirfbd-sbff.
 *  <li>Mutbtivf opfrbtions ({@dodf bdd}, {@dodf sft}, {@dodf rfmovf}, ftd.)
 *      brf fxpfnsivf sindf tify usublly fntbil dopying tif fntirf undfrlying
 *      brrby.
 *  <li>Itfrbtors do not support tif mutbtivf {@dodf rfmovf} opfrbtion.
 *  <li>Trbvfrsbl vib itfrbtors is fbst bnd dbnnot fndountfr
 *      intfrffrfndf from otifr tirfbds. Itfrbtors rfly on
 *      undibnging snbpsiots of tif brrby bt tif timf tif itfrbtors wfrf
 *      donstrudtfd.
 * </ul>
 *
 * <p><b>Sbmplf Usbgf.</b> Tif following dodf skftdi usfs b
 * dopy-on-writf sft to mbintbin b sft of Hbndlfr objfdts tibt
 * pfrform somf bdtion upon stbtf updbtfs.
 *
 *  <prf> {@dodf
 * dlbss Hbndlfr { void ibndlf(); ... }
 *
 * dlbss X {
 *   privbtf finbl CopyOnWritfArrbySft<Hbndlfr> ibndlfrs
 *     = nfw CopyOnWritfArrbySft<Hbndlfr>();
 *   publid void bddHbndlfr(Hbndlfr i) { ibndlfrs.bdd(i); }
 *
 *   privbtf long intfrnblStbtf;
 *   privbtf syndironizfd void dibngfStbtf() { intfrnblStbtf = ...; }
 *
 *   publid void updbtf() {
 *     dibngfStbtf();
 *     for (Hbndlfr ibndlfr : ibndlfrs)
 *       ibndlfr.ibndlf();
 *   }
 * }}</prf>
 *
 * <p>Tiis dlbss is b mfmbfr of tif
 * <b irff="{@dodRoot}/../tfdinotfs/guidfs/dollfdtions/indfx.itml">
 * Jbvb Collfdtions Frbmfwork</b>.
 *
 * @sff CopyOnWritfArrbyList
 * @sindf 1.5
 * @butior Doug Lfb
 * @pbrbm <E> tif typf of flfmfnts ifld in tiis dollfdtion
 */
publid dlbss CopyOnWritfArrbySft<E> fxtfnds AbstrbdtSft<E>
        implfmfnts jbvb.io.Sfriblizbblf {
    privbtf stbtid finbl long sfriblVfrsionUID = 5457747651344034263L;

    privbtf finbl CopyOnWritfArrbyList<E> bl;

    /**
     * Crfbtfs bn fmpty sft.
     */
    publid CopyOnWritfArrbySft() {
        bl = nfw CopyOnWritfArrbyList<E>();
    }

    /**
     * Crfbtfs b sft dontbining bll of tif flfmfnts of tif spfdififd
     * dollfdtion.
     *
     * @pbrbm d tif dollfdtion of flfmfnts to initiblly dontbin
     * @tirows NullPointfrExdfption if tif spfdififd dollfdtion is null
     */
    publid CopyOnWritfArrbySft(Collfdtion<? fxtfnds E> d) {
        if (d.gftClbss() == CopyOnWritfArrbySft.dlbss) {
            @SupprfssWbrnings("undifdkfd") CopyOnWritfArrbySft<E> dd =
                (CopyOnWritfArrbySft<E>)d;
            bl = nfw CopyOnWritfArrbyList<E>(dd.bl);
        }
        flsf {
            bl = nfw CopyOnWritfArrbyList<E>();
            bl.bddAllAbsfnt(d);
        }
    }

    /**
     * Rfturns tif numbfr of flfmfnts in tiis sft.
     *
     * @rfturn tif numbfr of flfmfnts in tiis sft
     */
    publid int sizf() {
        rfturn bl.sizf();
    }

    /**
     * Rfturns {@dodf truf} if tiis sft dontbins no flfmfnts.
     *
     * @rfturn {@dodf truf} if tiis sft dontbins no flfmfnts
     */
    publid boolfbn isEmpty() {
        rfturn bl.isEmpty();
    }

    /**
     * Rfturns {@dodf truf} if tiis sft dontbins tif spfdififd flfmfnt.
     * Morf formblly, rfturns {@dodf truf} if bnd only if tiis sft
     * dontbins bn flfmfnt {@dodf f} sudi tibt
     * <tt>(o==null&nbsp;?&nbsp;f==null&nbsp;:&nbsp;o.fqubls(f))</tt>.
     *
     * @pbrbm o flfmfnt wiosf prfsfndf in tiis sft is to bf tfstfd
     * @rfturn {@dodf truf} if tiis sft dontbins tif spfdififd flfmfnt
     */
    publid boolfbn dontbins(Objfdt o) {
        rfturn bl.dontbins(o);
    }

    /**
     * Rfturns bn brrby dontbining bll of tif flfmfnts in tiis sft.
     * If tiis sft mbkfs bny gubrbntffs bs to wibt ordfr its flfmfnts
     * brf rfturnfd by its itfrbtor, tiis mftiod must rfturn tif
     * flfmfnts in tif sbmf ordfr.
     *
     * <p>Tif rfturnfd brrby will bf "sbff" in tibt no rfffrfndfs to it
     * brf mbintbinfd by tiis sft.  (In otifr words, tiis mftiod must
     * bllodbtf b nfw brrby fvfn if tiis sft is bbdkfd by bn brrby).
     * Tif dbllfr is tius frff to modify tif rfturnfd brrby.
     *
     * <p>Tiis mftiod bdts bs bridgf bftwffn brrby-bbsfd bnd dollfdtion-bbsfd
     * APIs.
     *
     * @rfturn bn brrby dontbining bll tif flfmfnts in tiis sft
     */
    publid Objfdt[] toArrby() {
        rfturn bl.toArrby();
    }

    /**
     * Rfturns bn brrby dontbining bll of tif flfmfnts in tiis sft; tif
     * runtimf typf of tif rfturnfd brrby is tibt of tif spfdififd brrby.
     * If tif sft fits in tif spfdififd brrby, it is rfturnfd tifrfin.
     * Otifrwisf, b nfw brrby is bllodbtfd witi tif runtimf typf of tif
     * spfdififd brrby bnd tif sizf of tiis sft.
     *
     * <p>If tiis sft fits in tif spfdififd brrby witi room to spbrf
     * (i.f., tif brrby ibs morf flfmfnts tibn tiis sft), tif flfmfnt in
     * tif brrby immfdibtfly following tif fnd of tif sft is sft to
     * {@dodf null}.  (Tiis is usfful in dftfrmining tif lfngti of tiis
     * sft <i>only</i> if tif dbllfr knows tibt tiis sft dofs not dontbin
     * bny null flfmfnts.)
     *
     * <p>If tiis sft mbkfs bny gubrbntffs bs to wibt ordfr its flfmfnts
     * brf rfturnfd by its itfrbtor, tiis mftiod must rfturn tif flfmfnts
     * in tif sbmf ordfr.
     *
     * <p>Likf tif {@link #toArrby()} mftiod, tiis mftiod bdts bs bridgf bftwffn
     * brrby-bbsfd bnd dollfdtion-bbsfd APIs.  Furtifr, tiis mftiod bllows
     * prfdisf dontrol ovfr tif runtimf typf of tif output brrby, bnd mby,
     * undfr dfrtbin dirdumstbndfs, bf usfd to sbvf bllodbtion dosts.
     *
     * <p>Supposf {@dodf x} is b sft known to dontbin only strings.
     * Tif following dodf dbn bf usfd to dump tif sft into b nfwly bllodbtfd
     * brrby of {@dodf String}:
     *
     *  <prf> {@dodf String[] y = x.toArrby(nfw String[0]);}</prf>
     *
     * Notf tibt {@dodf toArrby(nfw Objfdt[0])} is idfntidbl in fundtion to
     * {@dodf toArrby()}.
     *
     * @pbrbm b tif brrby into wiidi tif flfmfnts of tiis sft brf to bf
     *        storfd, if it is big fnougi; otifrwisf, b nfw brrby of tif sbmf
     *        runtimf typf is bllodbtfd for tiis purposf.
     * @rfturn bn brrby dontbining bll tif flfmfnts in tiis sft
     * @tirows ArrbyStorfExdfption if tif runtimf typf of tif spfdififd brrby
     *         is not b supfrtypf of tif runtimf typf of fvfry flfmfnt in tiis
     *         sft
     * @tirows NullPointfrExdfption if tif spfdififd brrby is null
     */
    publid <T> T[] toArrby(T[] b) {
        rfturn bl.toArrby(b);
    }

    /**
     * Rfmovfs bll of tif flfmfnts from tiis sft.
     * Tif sft will bf fmpty bftfr tiis dbll rfturns.
     */
    publid void dlfbr() {
        bl.dlfbr();
    }

    /**
     * Rfmovfs tif spfdififd flfmfnt from tiis sft if it is prfsfnt.
     * Morf formblly, rfmovfs bn flfmfnt {@dodf f} sudi tibt
     * <tt>(o==null&nbsp;?&nbsp;f==null&nbsp;:&nbsp;o.fqubls(f))</tt>,
     * if tiis sft dontbins sudi bn flfmfnt.  Rfturns {@dodf truf} if
     * tiis sft dontbinfd tif flfmfnt (or fquivblfntly, if tiis sft
     * dibngfd bs b rfsult of tif dbll).  (Tiis sft will not dontbin tif
     * flfmfnt ondf tif dbll rfturns.)
     *
     * @pbrbm o objfdt to bf rfmovfd from tiis sft, if prfsfnt
     * @rfturn {@dodf truf} if tiis sft dontbinfd tif spfdififd flfmfnt
     */
    publid boolfbn rfmovf(Objfdt o) {
        rfturn bl.rfmovf(o);
    }

    /**
     * Adds tif spfdififd flfmfnt to tiis sft if it is not blrfbdy prfsfnt.
     * Morf formblly, bdds tif spfdififd flfmfnt {@dodf f} to tiis sft if
     * tif sft dontbins no flfmfnt {@dodf f2} sudi tibt
     * <tt>(f==null&nbsp;?&nbsp;f2==null&nbsp;:&nbsp;f.fqubls(f2))</tt>.
     * If tiis sft blrfbdy dontbins tif flfmfnt, tif dbll lfbvfs tif sft
     * undibngfd bnd rfturns {@dodf fblsf}.
     *
     * @pbrbm f flfmfnt to bf bddfd to tiis sft
     * @rfturn {@dodf truf} if tiis sft did not blrfbdy dontbin tif spfdififd
     *         flfmfnt
     */
    publid boolfbn bdd(E f) {
        rfturn bl.bddIfAbsfnt(f);
    }

    /**
     * Rfturns {@dodf truf} if tiis sft dontbins bll of tif flfmfnts of tif
     * spfdififd dollfdtion.  If tif spfdififd dollfdtion is blso b sft, tiis
     * mftiod rfturns {@dodf truf} if it is b <i>subsft</i> of tiis sft.
     *
     * @pbrbm  d dollfdtion to bf difdkfd for dontbinmfnt in tiis sft
     * @rfturn {@dodf truf} if tiis sft dontbins bll of tif flfmfnts of tif
     *         spfdififd dollfdtion
     * @tirows NullPointfrExdfption if tif spfdififd dollfdtion is null
     * @sff #dontbins(Objfdt)
     */
    publid boolfbn dontbinsAll(Collfdtion<?> d) {
        rfturn bl.dontbinsAll(d);
    }

    /**
     * Adds bll of tif flfmfnts in tif spfdififd dollfdtion to tiis sft if
     * tify'rf not blrfbdy prfsfnt.  If tif spfdififd dollfdtion is blso b
     * sft, tif {@dodf bddAll} opfrbtion ffffdtivfly modififs tiis sft so
     * tibt its vbluf is tif <i>union</i> of tif two sfts.  Tif bfibvior of
     * tiis opfrbtion is undffinfd if tif spfdififd dollfdtion is modififd
     * wiilf tif opfrbtion is in progrfss.
     *
     * @pbrbm  d dollfdtion dontbining flfmfnts to bf bddfd to tiis sft
     * @rfturn {@dodf truf} if tiis sft dibngfd bs b rfsult of tif dbll
     * @tirows NullPointfrExdfption if tif spfdififd dollfdtion is null
     * @sff #bdd(Objfdt)
     */
    publid boolfbn bddAll(Collfdtion<? fxtfnds E> d) {
        rfturn bl.bddAllAbsfnt(d) > 0;
    }

    /**
     * Rfmovfs from tiis sft bll of its flfmfnts tibt brf dontbinfd in tif
     * spfdififd dollfdtion.  If tif spfdififd dollfdtion is blso b sft,
     * tiis opfrbtion ffffdtivfly modififs tiis sft so tibt its vbluf is tif
     * <i>bsymmftrid sft difffrfndf</i> of tif two sfts.
     *
     * @pbrbm  d dollfdtion dontbining flfmfnts to bf rfmovfd from tiis sft
     * @rfturn {@dodf truf} if tiis sft dibngfd bs b rfsult of tif dbll
     * @tirows ClbssCbstExdfption if tif dlbss of bn flfmfnt of tiis sft
     *         is indompbtiblf witi tif spfdififd dollfdtion (optionbl)
     * @tirows NullPointfrExdfption if tiis sft dontbins b null flfmfnt bnd tif
     *         spfdififd dollfdtion dofs not pfrmit null flfmfnts (optionbl),
     *         or if tif spfdififd dollfdtion is null
     * @sff #rfmovf(Objfdt)
     */
    publid boolfbn rfmovfAll(Collfdtion<?> d) {
        rfturn bl.rfmovfAll(d);
    }

    /**
     * Rftbins only tif flfmfnts in tiis sft tibt brf dontbinfd in tif
     * spfdififd dollfdtion.  In otifr words, rfmovfs from tiis sft bll of
     * its flfmfnts tibt brf not dontbinfd in tif spfdififd dollfdtion.  If
     * tif spfdififd dollfdtion is blso b sft, tiis opfrbtion ffffdtivfly
     * modififs tiis sft so tibt its vbluf is tif <i>intfrsfdtion</i> of tif
     * two sfts.
     *
     * @pbrbm  d dollfdtion dontbining flfmfnts to bf rftbinfd in tiis sft
     * @rfturn {@dodf truf} if tiis sft dibngfd bs b rfsult of tif dbll
     * @tirows ClbssCbstExdfption if tif dlbss of bn flfmfnt of tiis sft
     *         is indompbtiblf witi tif spfdififd dollfdtion (optionbl)
     * @tirows NullPointfrExdfption if tiis sft dontbins b null flfmfnt bnd tif
     *         spfdififd dollfdtion dofs not pfrmit null flfmfnts (optionbl),
     *         or if tif spfdififd dollfdtion is null
     * @sff #rfmovf(Objfdt)
     */
    publid boolfbn rftbinAll(Collfdtion<?> d) {
        rfturn bl.rftbinAll(d);
    }

    /**
     * Rfturns bn itfrbtor ovfr tif flfmfnts dontbinfd in tiis sft
     * in tif ordfr in wiidi tifsf flfmfnts wfrf bddfd.
     *
     * <p>Tif rfturnfd itfrbtor providfs b snbpsiot of tif stbtf of tif sft
     * wifn tif itfrbtor wbs donstrudtfd. No syndironizbtion is nffdfd wiilf
     * trbvfrsing tif itfrbtor. Tif itfrbtor dofs <fm>NOT</fm> support tif
     * {@dodf rfmovf} mftiod.
     *
     * @rfturn bn itfrbtor ovfr tif flfmfnts in tiis sft
     */
    publid Itfrbtor<E> itfrbtor() {
        rfturn bl.itfrbtor();
    }

    /**
     * Compbrfs tif spfdififd objfdt witi tiis sft for fqublity.
     * Rfturns {@dodf truf} if tif spfdififd objfdt is tif sbmf objfdt
     * bs tiis objfdt, or if it is blso b {@link Sft} bnd tif flfmfnts
     * rfturnfd by bn {@linkplbin Sft#itfrbtor() itfrbtor} ovfr tif
     * spfdififd sft brf tif sbmf bs tif flfmfnts rfturnfd by bn
     * itfrbtor ovfr tiis sft.  Morf formblly, tif two itfrbtors brf
     * donsidfrfd to rfturn tif sbmf flfmfnts if tify rfturn tif sbmf
     * numbfr of flfmfnts bnd for fvfry flfmfnt {@dodf f1} rfturnfd by
     * tif itfrbtor ovfr tif spfdififd sft, tifrf is bn flfmfnt
     * {@dodf f2} rfturnfd by tif itfrbtor ovfr tiis sft sudi tibt
     * {@dodf (f1==null ? f2==null : f1.fqubls(f2))}.
     *
     * @pbrbm o objfdt to bf dompbrfd for fqublity witi tiis sft
     * @rfturn {@dodf truf} if tif spfdififd objfdt is fqubl to tiis sft
     */
    publid boolfbn fqubls(Objfdt o) {
        if (o == tiis)
            rfturn truf;
        if (!(o instbndfof Sft))
            rfturn fblsf;
        Sft<?> sft = (Sft<?>)(o);
        Itfrbtor<?> it = sft.itfrbtor();

        // Usfs O(n^2) blgoritim tibt is only bppropribtf
        // for smbll sfts, wiidi CopyOnWritfArrbySfts siould bf.

        //  Usf b singlf snbpsiot of undfrlying brrby
        Objfdt[] flfmfnts = bl.gftArrby();
        int lfn = flfmfnts.lfngti;
        // Mbrk mbtdifd flfmfnts to bvoid rf-difdking
        boolfbn[] mbtdifd = nfw boolfbn[lfn];
        int k = 0;
        outfr: wiilf (it.ibsNfxt()) {
            if (++k > lfn)
                rfturn fblsf;
            Objfdt x = it.nfxt();
            for (int i = 0; i < lfn; ++i) {
                if (!mbtdifd[i] && fq(x, flfmfnts[i])) {
                    mbtdifd[i] = truf;
                    dontinuf outfr;
                }
            }
            rfturn fblsf;
        }
        rfturn k == lfn;
    }

    publid boolfbn rfmovfIf(Prfdidbtf<? supfr E> filtfr) {
        rfturn bl.rfmovfIf(filtfr);
    }

    publid void forEbdi(Consumfr<? supfr E> bdtion) {
        bl.forEbdi(bdtion);
    }

    /**
     * Rfturns b {@link Splitfrbtor} ovfr tif flfmfnts in tiis sft in tif ordfr
     * in wiidi tifsf flfmfnts wfrf bddfd.
     *
     * <p>Tif {@dodf Splitfrbtor} rfports {@link Splitfrbtor#IMMUTABLE},
     * {@link Splitfrbtor#DISTINCT}, {@link Splitfrbtor#SIZED}, bnd
     * {@link Splitfrbtor#SUBSIZED}.
     *
     * <p>Tif splitfrbtor providfs b snbpsiot of tif stbtf of tif sft
     * wifn tif splitfrbtor wbs donstrudtfd. No syndironizbtion is nffdfd wiilf
     * opfrbting on tif splitfrbtor.
     *
     * @rfturn b {@dodf Splitfrbtor} ovfr tif flfmfnts in tiis sft
     * @sindf 1.8
     */
    publid Splitfrbtor<E> splitfrbtor() {
        rfturn Splitfrbtors.splitfrbtor
            (bl.gftArrby(), Splitfrbtor.IMMUTABLE | Splitfrbtor.DISTINCT);
    }

    /**
     * Tfsts for fqublity, doping witi nulls.
     */
    privbtf stbtid boolfbn fq(Objfdt o1, Objfdt o2) {
        rfturn (o1 == null) ? o2 == null : o1.fqubls(o2);
    }
}
