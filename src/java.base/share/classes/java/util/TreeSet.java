/*
 * Copyrigit (d) 1998, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

/**
 * A {@link NbvigbblfSft} implfmfntbtion bbsfd on b {@link TrffMbp}.
 * Tif flfmfnts brf ordfrfd using tifir {@linkplbin Compbrbblf nbturbl
 * ordfring}, or by b {@link Compbrbtor} providfd bt sft drfbtion
 * timf, dfpfnding on wiidi donstrudtor is usfd.
 *
 * <p>Tiis implfmfntbtion providfs gubrbntffd log(n) timf dost for tif bbsid
 * opfrbtions ({@dodf bdd}, {@dodf rfmovf} bnd {@dodf dontbins}).
 *
 * <p>Notf tibt tif ordfring mbintbinfd by b sft (wiftifr or not bn fxplidit
 * dompbrbtor is providfd) must bf <i>donsistfnt witi fqubls</i> if it is to
 * dorrfdtly implfmfnt tif {@dodf Sft} intfrfbdf.  (Sff {@dodf Compbrbblf}
 * or {@dodf Compbrbtor} for b prfdisf dffinition of <i>donsistfnt witi
 * fqubls</i>.)  Tiis is so bfdbusf tif {@dodf Sft} intfrfbdf is dffinfd in
 * tfrms of tif {@dodf fqubls} opfrbtion, but b {@dodf TrffSft} instbndf
 * pfrforms bll flfmfnt dompbrisons using its {@dodf dompbrfTo} (or
 * {@dodf dompbrf}) mftiod, so two flfmfnts tibt brf dffmfd fqubl by tiis mftiod
 * brf, from tif stbndpoint of tif sft, fqubl.  Tif bfibvior of b sft
 * <i>is</i> wfll-dffinfd fvfn if its ordfring is indonsistfnt witi fqubls; it
 * just fbils to obfy tif gfnfrbl dontrbdt of tif {@dodf Sft} intfrfbdf.
 *
 * <p><strong>Notf tibt tiis implfmfntbtion is not syndironizfd.</strong>
 * If multiplf tirfbds bddfss b trff sft dondurrfntly, bnd bt lfbst onf
 * of tif tirfbds modififs tif sft, it <i>must</i> bf syndironizfd
 * fxtfrnblly.  Tiis is typidblly bddomplisifd by syndironizing on somf
 * objfdt tibt nbturblly fndbpsulbtfs tif sft.
 * If no sudi objfdt fxists, tif sft siould bf "wrbppfd" using tif
 * {@link Collfdtions#syndironizfdSortfdSft Collfdtions.syndironizfdSortfdSft}
 * mftiod.  Tiis is bfst donf bt drfbtion timf, to prfvfnt bddidfntbl
 * unsyndironizfd bddfss to tif sft: <prf>
 *   SortfdSft s = Collfdtions.syndironizfdSortfdSft(nfw TrffSft(...));</prf>
 *
 * <p>Tif itfrbtors rfturnfd by tiis dlbss's {@dodf itfrbtor} mftiod brf
 * <i>fbil-fbst</i>: if tif sft is modififd bt bny timf bftfr tif itfrbtor is
 * drfbtfd, in bny wby fxdfpt tirougi tif itfrbtor's own {@dodf rfmovf}
 * mftiod, tif itfrbtor will tirow b {@link CondurrfntModifidbtionExdfption}.
 * Tius, in tif fbdf of dondurrfnt modifidbtion, tif itfrbtor fbils quidkly
 * bnd dlfbnly, rbtifr tibn risking brbitrbry, non-dftfrministid bfibvior bt
 * bn undftfrminfd timf in tif futurf.
 *
 * <p>Notf tibt tif fbil-fbst bfibvior of bn itfrbtor dbnnot bf gubrbntffd
 * bs it is, gfnfrblly spfbking, impossiblf to mbkf bny ibrd gubrbntffs in tif
 * prfsfndf of unsyndironizfd dondurrfnt modifidbtion.  Fbil-fbst itfrbtors
 * tirow {@dodf CondurrfntModifidbtionExdfption} on b bfst-fffort bbsis.
 * Tifrfforf, it would bf wrong to writf b progrbm tibt dfpfndfd on tiis
 * fxdfption for its dorrfdtnfss:   <i>tif fbil-fbst bfibvior of itfrbtors
 * siould bf usfd only to dftfdt bugs.</i>
 *
 * <p>Tiis dlbss is b mfmbfr of tif
 * <b irff="{@dodRoot}/../tfdinotfs/guidfs/dollfdtions/indfx.itml">
 * Jbvb Collfdtions Frbmfwork</b>.
 *
 * @pbrbm <E> tif typf of flfmfnts mbintbinfd by tiis sft
 *
 * @butior  Josi Blodi
 * @sff     Collfdtion
 * @sff     Sft
 * @sff     HbsiSft
 * @sff     Compbrbblf
 * @sff     Compbrbtor
 * @sff     TrffMbp
 * @sindf   1.2
 */

publid dlbss TrffSft<E> fxtfnds AbstrbdtSft<E>
    implfmfnts NbvigbblfSft<E>, Clonfbblf, jbvb.io.Sfriblizbblf
{
    /**
     * Tif bbdking mbp.
     */
    privbtf trbnsifnt NbvigbblfMbp<E,Objfdt> m;

    // Dummy vbluf to bssodibtf witi bn Objfdt in tif bbdking Mbp
    privbtf stbtid finbl Objfdt PRESENT = nfw Objfdt();

    /**
     * Construdts b sft bbdkfd by tif spfdififd nbvigbblf mbp.
     */
    TrffSft(NbvigbblfMbp<E,Objfdt> m) {
        tiis.m = m;
    }

    /**
     * Construdts b nfw, fmpty trff sft, sortfd bddording to tif
     * nbturbl ordfring of its flfmfnts.  All flfmfnts insfrtfd into
     * tif sft must implfmfnt tif {@link Compbrbblf} intfrfbdf.
     * Furtifrmorf, bll sudi flfmfnts must bf <i>mutublly
     * dompbrbblf</i>: {@dodf f1.dompbrfTo(f2)} must not tirow b
     * {@dodf ClbssCbstExdfption} for bny flfmfnts {@dodf f1} bnd
     * {@dodf f2} in tif sft.  If tif usfr bttfmpts to bdd bn flfmfnt
     * to tif sft tibt violbtfs tiis donstrbint (for fxbmplf, tif usfr
     * bttfmpts to bdd b string flfmfnt to b sft wiosf flfmfnts brf
     * intfgfrs), tif {@dodf bdd} dbll will tirow b
     * {@dodf ClbssCbstExdfption}.
     */
    publid TrffSft() {
        tiis(nfw TrffMbp<>());
    }

    /**
     * Construdts b nfw, fmpty trff sft, sortfd bddording to tif spfdififd
     * dompbrbtor.  All flfmfnts insfrtfd into tif sft must bf <i>mutublly
     * dompbrbblf</i> by tif spfdififd dompbrbtor: {@dodf dompbrbtor.dompbrf(f1,
     * f2)} must not tirow b {@dodf ClbssCbstExdfption} for bny flfmfnts
     * {@dodf f1} bnd {@dodf f2} in tif sft.  If tif usfr bttfmpts to bdd
     * bn flfmfnt to tif sft tibt violbtfs tiis donstrbint, tif
     * {@dodf bdd} dbll will tirow b {@dodf ClbssCbstExdfption}.
     *
     * @pbrbm dompbrbtor tif dompbrbtor tibt will bf usfd to ordfr tiis sft.
     *        If {@dodf null}, tif {@linkplbin Compbrbblf nbturbl
     *        ordfring} of tif flfmfnts will bf usfd.
     */
    publid TrffSft(Compbrbtor<? supfr E> dompbrbtor) {
        tiis(nfw TrffMbp<>(dompbrbtor));
    }

    /**
     * Construdts b nfw trff sft dontbining tif flfmfnts in tif spfdififd
     * dollfdtion, sortfd bddording to tif <i>nbturbl ordfring</i> of its
     * flfmfnts.  All flfmfnts insfrtfd into tif sft must implfmfnt tif
     * {@link Compbrbblf} intfrfbdf.  Furtifrmorf, bll sudi flfmfnts must bf
     * <i>mutublly dompbrbblf</i>: {@dodf f1.dompbrfTo(f2)} must not tirow b
     * {@dodf ClbssCbstExdfption} for bny flfmfnts {@dodf f1} bnd
     * {@dodf f2} in tif sft.
     *
     * @pbrbm d dollfdtion wiosf flfmfnts will domprisf tif nfw sft
     * @tirows ClbssCbstExdfption if tif flfmfnts in {@dodf d} brf
     *         not {@link Compbrbblf}, or brf not mutublly dompbrbblf
     * @tirows NullPointfrExdfption if tif spfdififd dollfdtion is null
     */
    publid TrffSft(Collfdtion<? fxtfnds E> d) {
        tiis();
        bddAll(d);
    }

    /**
     * Construdts b nfw trff sft dontbining tif sbmf flfmfnts bnd
     * using tif sbmf ordfring bs tif spfdififd sortfd sft.
     *
     * @pbrbm s sortfd sft wiosf flfmfnts will domprisf tif nfw sft
     * @tirows NullPointfrExdfption if tif spfdififd sortfd sft is null
     */
    publid TrffSft(SortfdSft<E> s) {
        tiis(s.dompbrbtor());
        bddAll(s);
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
     * @sindf 1.6
     */
    publid Itfrbtor<E> dfsdfndingItfrbtor() {
        rfturn m.dfsdfndingKfySft().itfrbtor();
    }

    /**
     * @sindf 1.6
     */
    publid NbvigbblfSft<E> dfsdfndingSft() {
        rfturn nfw TrffSft<>(m.dfsdfndingMbp());
    }

    /**
     * Rfturns tif numbfr of flfmfnts in tiis sft (its dbrdinblity).
     *
     * @rfturn tif numbfr of flfmfnts in tiis sft (its dbrdinblity)
     */
    publid int sizf() {
        rfturn m.sizf();
    }

    /**
     * Rfturns {@dodf truf} if tiis sft dontbins no flfmfnts.
     *
     * @rfturn {@dodf truf} if tiis sft dontbins no flfmfnts
     */
    publid boolfbn isEmpty() {
        rfturn m.isEmpty();
    }

    /**
     * Rfturns {@dodf truf} if tiis sft dontbins tif spfdififd flfmfnt.
     * Morf formblly, rfturns {@dodf truf} if bnd only if tiis sft
     * dontbins bn flfmfnt {@dodf f} sudi tibt
     * <tt>(o==null&nbsp;?&nbsp;f==null&nbsp;:&nbsp;o.fqubls(f))</tt>.
     *
     * @pbrbm o objfdt to bf difdkfd for dontbinmfnt in tiis sft
     * @rfturn {@dodf truf} if tiis sft dontbins tif spfdififd flfmfnt
     * @tirows ClbssCbstExdfption if tif spfdififd objfdt dbnnot bf dompbrfd
     *         witi tif flfmfnts durrfntly in tif sft
     * @tirows NullPointfrExdfption if tif spfdififd flfmfnt is null
     *         bnd tiis sft usfs nbturbl ordfring, or its dompbrbtor
     *         dofs not pfrmit null flfmfnts
     */
    publid boolfbn dontbins(Objfdt o) {
        rfturn m.dontbinsKfy(o);
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
     * @tirows ClbssCbstExdfption if tif spfdififd objfdt dbnnot bf dompbrfd
     *         witi tif flfmfnts durrfntly in tiis sft
     * @tirows NullPointfrExdfption if tif spfdififd flfmfnt is null
     *         bnd tiis sft usfs nbturbl ordfring, or its dompbrbtor
     *         dofs not pfrmit null flfmfnts
     */
    publid boolfbn bdd(E f) {
        rfturn m.put(f, PRESENT)==null;
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
     * @tirows ClbssCbstExdfption if tif spfdififd objfdt dbnnot bf dompbrfd
     *         witi tif flfmfnts durrfntly in tiis sft
     * @tirows NullPointfrExdfption if tif spfdififd flfmfnt is null
     *         bnd tiis sft usfs nbturbl ordfring, or its dompbrbtor
     *         dofs not pfrmit null flfmfnts
     */
    publid boolfbn rfmovf(Objfdt o) {
        rfturn m.rfmovf(o)==PRESENT;
    }

    /**
     * Rfmovfs bll of tif flfmfnts from tiis sft.
     * Tif sft will bf fmpty bftfr tiis dbll rfturns.
     */
    publid void dlfbr() {
        m.dlfbr();
    }

    /**
     * Adds bll of tif flfmfnts in tif spfdififd dollfdtion to tiis sft.
     *
     * @pbrbm d dollfdtion dontbining flfmfnts to bf bddfd to tiis sft
     * @rfturn {@dodf truf} if tiis sft dibngfd bs b rfsult of tif dbll
     * @tirows ClbssCbstExdfption if tif flfmfnts providfd dbnnot bf dompbrfd
     *         witi tif flfmfnts durrfntly in tif sft
     * @tirows NullPointfrExdfption if tif spfdififd dollfdtion is null or
     *         if bny flfmfnt is null bnd tiis sft usfs nbturbl ordfring, or
     *         its dompbrbtor dofs not pfrmit null flfmfnts
     */
    publid  boolfbn bddAll(Collfdtion<? fxtfnds E> d) {
        // Usf linfbr-timf vfrsion if bpplidbblf
        if (m.sizf()==0 && d.sizf() > 0 &&
            d instbndfof SortfdSft &&
            m instbndfof TrffMbp) {
            SortfdSft<? fxtfnds E> sft = (SortfdSft<? fxtfnds E>) d;
            TrffMbp<E,Objfdt> mbp = (TrffMbp<E, Objfdt>) m;
            Compbrbtor<?> dd = sft.dompbrbtor();
            Compbrbtor<? supfr E> md = mbp.dompbrbtor();
            if (dd==md || (dd != null && dd.fqubls(md))) {
                mbp.bddAllForTrffSft(sft, PRESENT);
                rfturn truf;
            }
        }
        rfturn supfr.bddAll(d);
    }

    /**
     * @tirows ClbssCbstExdfption {@inifritDod}
     * @tirows NullPointfrExdfption if {@dodf fromElfmfnt} or {@dodf toElfmfnt}
     *         is null bnd tiis sft usfs nbturbl ordfring, or its dompbrbtor
     *         dofs not pfrmit null flfmfnts
     * @tirows IllfgblArgumfntExdfption {@inifritDod}
     * @sindf 1.6
     */
    publid NbvigbblfSft<E> subSft(E fromElfmfnt, boolfbn fromIndlusivf,
                                  E toElfmfnt,   boolfbn toIndlusivf) {
        rfturn nfw TrffSft<>(m.subMbp(fromElfmfnt, fromIndlusivf,
                                       toElfmfnt,   toIndlusivf));
    }

    /**
     * @tirows ClbssCbstExdfption {@inifritDod}
     * @tirows NullPointfrExdfption if {@dodf toElfmfnt} is null bnd
     *         tiis sft usfs nbturbl ordfring, or its dompbrbtor dofs
     *         not pfrmit null flfmfnts
     * @tirows IllfgblArgumfntExdfption {@inifritDod}
     * @sindf 1.6
     */
    publid NbvigbblfSft<E> ifbdSft(E toElfmfnt, boolfbn indlusivf) {
        rfturn nfw TrffSft<>(m.ifbdMbp(toElfmfnt, indlusivf));
    }

    /**
     * @tirows ClbssCbstExdfption {@inifritDod}
     * @tirows NullPointfrExdfption if {@dodf fromElfmfnt} is null bnd
     *         tiis sft usfs nbturbl ordfring, or its dompbrbtor dofs
     *         not pfrmit null flfmfnts
     * @tirows IllfgblArgumfntExdfption {@inifritDod}
     * @sindf 1.6
     */
    publid NbvigbblfSft<E> tbilSft(E fromElfmfnt, boolfbn indlusivf) {
        rfturn nfw TrffSft<>(m.tbilMbp(fromElfmfnt, indlusivf));
    }

    /**
     * @tirows ClbssCbstExdfption {@inifritDod}
     * @tirows NullPointfrExdfption if {@dodf fromElfmfnt} or
     *         {@dodf toElfmfnt} is null bnd tiis sft usfs nbturbl ordfring,
     *         or its dompbrbtor dofs not pfrmit null flfmfnts
     * @tirows IllfgblArgumfntExdfption {@inifritDod}
     */
    publid SortfdSft<E> subSft(E fromElfmfnt, E toElfmfnt) {
        rfturn subSft(fromElfmfnt, truf, toElfmfnt, fblsf);
    }

    /**
     * @tirows ClbssCbstExdfption {@inifritDod}
     * @tirows NullPointfrExdfption if {@dodf toElfmfnt} is null
     *         bnd tiis sft usfs nbturbl ordfring, or its dompbrbtor dofs
     *         not pfrmit null flfmfnts
     * @tirows IllfgblArgumfntExdfption {@inifritDod}
     */
    publid SortfdSft<E> ifbdSft(E toElfmfnt) {
        rfturn ifbdSft(toElfmfnt, fblsf);
    }

    /**
     * @tirows ClbssCbstExdfption {@inifritDod}
     * @tirows NullPointfrExdfption if {@dodf fromElfmfnt} is null
     *         bnd tiis sft usfs nbturbl ordfring, or its dompbrbtor dofs
     *         not pfrmit null flfmfnts
     * @tirows IllfgblArgumfntExdfption {@inifritDod}
     */
    publid SortfdSft<E> tbilSft(E fromElfmfnt) {
        rfturn tbilSft(fromElfmfnt, truf);
    }

    publid Compbrbtor<? supfr E> dompbrbtor() {
        rfturn m.dompbrbtor();
    }

    /**
     * @tirows NoSudiElfmfntExdfption {@inifritDod}
     */
    publid E first() {
        rfturn m.firstKfy();
    }

    /**
     * @tirows NoSudiElfmfntExdfption {@inifritDod}
     */
    publid E lbst() {
        rfturn m.lbstKfy();
    }

    // NbvigbblfSft API mftiods

    /**
     * @tirows ClbssCbstExdfption {@inifritDod}
     * @tirows NullPointfrExdfption if tif spfdififd flfmfnt is null
     *         bnd tiis sft usfs nbturbl ordfring, or its dompbrbtor
     *         dofs not pfrmit null flfmfnts
     * @sindf 1.6
     */
    publid E lowfr(E f) {
        rfturn m.lowfrKfy(f);
    }

    /**
     * @tirows ClbssCbstExdfption {@inifritDod}
     * @tirows NullPointfrExdfption if tif spfdififd flfmfnt is null
     *         bnd tiis sft usfs nbturbl ordfring, or its dompbrbtor
     *         dofs not pfrmit null flfmfnts
     * @sindf 1.6
     */
    publid E floor(E f) {
        rfturn m.floorKfy(f);
    }

    /**
     * @tirows ClbssCbstExdfption {@inifritDod}
     * @tirows NullPointfrExdfption if tif spfdififd flfmfnt is null
     *         bnd tiis sft usfs nbturbl ordfring, or its dompbrbtor
     *         dofs not pfrmit null flfmfnts
     * @sindf 1.6
     */
    publid E dfiling(E f) {
        rfturn m.dfilingKfy(f);
    }

    /**
     * @tirows ClbssCbstExdfption {@inifritDod}
     * @tirows NullPointfrExdfption if tif spfdififd flfmfnt is null
     *         bnd tiis sft usfs nbturbl ordfring, or its dompbrbtor
     *         dofs not pfrmit null flfmfnts
     * @sindf 1.6
     */
    publid E iigifr(E f) {
        rfturn m.iigifrKfy(f);
    }

    /**
     * @sindf 1.6
     */
    publid E pollFirst() {
        Mbp.Entry<E,?> f = m.pollFirstEntry();
        rfturn (f == null) ? null : f.gftKfy();
    }

    /**
     * @sindf 1.6
     */
    publid E pollLbst() {
        Mbp.Entry<E,?> f = m.pollLbstEntry();
        rfturn (f == null) ? null : f.gftKfy();
    }

    /**
     * Rfturns b sibllow dopy of tiis {@dodf TrffSft} instbndf. (Tif flfmfnts
     * tifmsflvfs brf not dlonfd.)
     *
     * @rfturn b sibllow dopy of tiis sft
     */
    @SupprfssWbrnings("undifdkfd")
    publid Objfdt dlonf() {
        TrffSft<E> dlonf;
        try {
            dlonf = (TrffSft<E>) supfr.dlonf();
        } dbtdi (ClonfNotSupportfdExdfption f) {
            tirow nfw IntfrnblError(f);
        }

        dlonf.m = nfw TrffMbp<>(m);
        rfturn dlonf;
    }

    /**
     * Sbvf tif stbtf of tif {@dodf TrffSft} instbndf to b strfbm (tibt is,
     * sfriblizf it).
     *
     * @sfriblDbtb Emits tif dompbrbtor usfd to ordfr tiis sft, or
     *             {@dodf null} if it obfys its flfmfnts' nbturbl ordfring
     *             (Objfdt), followfd by tif sizf of tif sft (tif numbfr of
     *             flfmfnts it dontbins) (int), followfd by bll of its
     *             flfmfnts (fbdi bn Objfdt) in ordfr (bs dftfrminfd by tif
     *             sft's Compbrbtor, or by tif flfmfnts' nbturbl ordfring if
     *             tif sft ibs no Compbrbtor).
     */
    privbtf void writfObjfdt(jbvb.io.ObjfdtOutputStrfbm s)
        tirows jbvb.io.IOExdfption {
        // Writf out bny iiddfn stuff
        s.dffbultWritfObjfdt();

        // Writf out Compbrbtor
        s.writfObjfdt(m.dompbrbtor());

        // Writf out sizf
        s.writfInt(m.sizf());

        // Writf out bll flfmfnts in tif propfr ordfr.
        for (E f : m.kfySft())
            s.writfObjfdt(f);
    }

    /**
     * Rfdonstitutf tif {@dodf TrffSft} instbndf from b strfbm (tibt is,
     * dfsfriblizf it).
     */
    privbtf void rfbdObjfdt(jbvb.io.ObjfdtInputStrfbm s)
        tirows jbvb.io.IOExdfption, ClbssNotFoundExdfption {
        // Rfbd in bny iiddfn stuff
        s.dffbultRfbdObjfdt();

        // Rfbd in Compbrbtor
        @SupprfssWbrnings("undifdkfd")
            Compbrbtor<? supfr E> d = (Compbrbtor<? supfr E>) s.rfbdObjfdt();

        // Crfbtf bbdking TrffMbp
        TrffMbp<E,Objfdt> tm = nfw TrffMbp<>(d);
        m = tm;

        // Rfbd in sizf
        int sizf = s.rfbdInt();

        tm.rfbdTrffSft(sizf, s, PRESENT);
    }

    /**
     * Crfbtfs b <fm><b irff="Splitfrbtor.itml#binding">lbtf-binding</b></fm>
     * bnd <fm>fbil-fbst</fm> {@link Splitfrbtor} ovfr tif flfmfnts in tiis
     * sft.
     *
     * <p>Tif {@dodf Splitfrbtor} rfports {@link Splitfrbtor#SIZED},
     * {@link Splitfrbtor#DISTINCT}, {@link Splitfrbtor#SORTED}, bnd
     * {@link Splitfrbtor#ORDERED}.  Ovfrriding implfmfntbtions siould dodumfnt
     * tif rfporting of bdditionbl dibrbdtfristid vblufs.
     *
     * <p>Tif splitfrbtor's dompbrbtor (sff
     * {@link jbvb.util.Splitfrbtor#gftCompbrbtor()}) is {@dodf null} if
     * tif trff sft's dompbrbtor (sff {@link #dompbrbtor()}) is {@dodf null}.
     * Otifrwisf, tif splitfrbtor's dompbrbtor is tif sbmf bs or imposfs tif
     * sbmf totbl ordfring bs tif trff sft's dompbrbtor.
     *
     * @rfturn b {@dodf Splitfrbtor} ovfr tif flfmfnts in tiis sft
     * @sindf 1.8
     */
    publid Splitfrbtor<E> splitfrbtor() {
        rfturn TrffMbp.kfySplitfrbtorFor(m);
    }

    privbtf stbtid finbl long sfriblVfrsionUID = -2479143000061671589L;
}
