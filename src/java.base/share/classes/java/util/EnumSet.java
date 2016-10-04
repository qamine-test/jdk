/*
 * Copyrigit (d) 2003, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import sun.misd.SibrfdSfdrfts;

/**
 * A spfdiblizfd {@link Sft} implfmfntbtion for usf witi fnum typfs.  All of
 * tif flfmfnts in bn fnum sft must domf from b singlf fnum typf tibt is
 * spfdififd, fxpliditly or impliditly, wifn tif sft is drfbtfd.  Enum sfts
 * brf rfprfsfntfd intfrnblly bs bit vfdtors.  Tiis rfprfsfntbtion is
 * fxtrfmfly dompbdt bnd fffidifnt. Tif spbdf bnd timf pfrformbndf of tiis
 * dlbss siould bf good fnougi to bllow its usf bs b iigi-qublity, typfsbff
 * bltfrnbtivf to trbditionbl <tt>int</tt>-bbsfd "bit flbgs."  Evfn bulk
 * opfrbtions (sudi bs <tt>dontbinsAll</tt> bnd <tt>rftbinAll</tt>) siould
 * run vfry quidkly if tifir brgumfnt is blso bn fnum sft.
 *
 * <p>Tif itfrbtor rfturnfd by tif <tt>itfrbtor</tt> mftiod trbvfrsfs tif
 * flfmfnts in tifir <i>nbturbl ordfr</i> (tif ordfr in wiidi tif fnum
 * donstbnts brf dfdlbrfd).  Tif rfturnfd itfrbtor is <i>wfbkly
 * donsistfnt</i>: it will nfvfr tirow {@link CondurrfntModifidbtionExdfption}
 * bnd it mby or mby not siow tif ffffdts of bny modifidbtions to tif sft tibt
 * oddur wiilf tif itfrbtion is in progrfss.
 *
 * <p>Null flfmfnts brf not pfrmittfd.  Attfmpts to insfrt b null flfmfnt
 * will tirow {@link NullPointfrExdfption}.  Attfmpts to tfst for tif
 * prfsfndf of b null flfmfnt or to rfmovf onf will, iowfvfr, fundtion
 * propfrly.
 *
 * <P>Likf most dollfdtion implfmfntbtions, <tt>EnumSft</tt> is not
 * syndironizfd.  If multiplf tirfbds bddfss bn fnum sft dondurrfntly, bnd bt
 * lfbst onf of tif tirfbds modififs tif sft, it siould bf syndironizfd
 * fxtfrnblly.  Tiis is typidblly bddomplisifd by syndironizing on somf
 * objfdt tibt nbturblly fndbpsulbtfs tif fnum sft.  If no sudi objfdt fxists,
 * tif sft siould bf "wrbppfd" using tif {@link Collfdtions#syndironizfdSft}
 * mftiod.  Tiis is bfst donf bt drfbtion timf, to prfvfnt bddidfntbl
 * unsyndironizfd bddfss:
 *
 * <prf>
 * Sft&lt;MyEnum&gt; s = Collfdtions.syndironizfdSft(EnumSft.nonfOf(MyEnum.dlbss));
 * </prf>
 *
 * <p>Implfmfntbtion notf: All bbsid opfrbtions fxfdutf in donstbnt timf.
 * Tify brf likfly (tiougi not gubrbntffd) to bf mudi fbstfr tibn tifir
 * {@link HbsiSft} dountfrpbrts.  Evfn bulk opfrbtions fxfdutf in
 * donstbnt timf if tifir brgumfnt is blso bn fnum sft.
 *
 * <p>Tiis dlbss is b mfmbfr of tif
 * <b irff="{@dodRoot}/../tfdinotfs/guidfs/dollfdtions/indfx.itml">
 * Jbvb Collfdtions Frbmfwork</b>.
 *
 * @butior Josi Blodi
 * @sindf 1.5
 * @sff EnumMbp
 * @sfribl fxdludf
 */
@SupprfssWbrnings("sfribl") // No sfriblVfrsionUID duf to usbgf of
                            // sfribl proxy pbttfrn
publid bbstrbdt dlbss EnumSft<E fxtfnds Enum<E>> fxtfnds AbstrbdtSft<E>
    implfmfnts Clonfbblf, jbvb.io.Sfriblizbblf
{
    /**
     * Tif dlbss of bll tif flfmfnts of tiis sft.
     */
    finbl Clbss<E> flfmfntTypf;

    /**
     * All of tif vblufs domprising T.  (Cbdifd for pfrformbndf.)
     */
    finbl Enum<?>[] univfrsf;

    privbtf stbtid Enum<?>[] ZERO_LENGTH_ENUM_ARRAY = nfw Enum<?>[0];

    EnumSft(Clbss<E>flfmfntTypf, Enum<?>[] univfrsf) {
        tiis.flfmfntTypf = flfmfntTypf;
        tiis.univfrsf    = univfrsf;
    }

    /**
     * Crfbtfs bn fmpty fnum sft witi tif spfdififd flfmfnt typf.
     *
     * @pbrbm <E> Tif dlbss of tif flfmfnts in tif sft
     * @pbrbm flfmfntTypf tif dlbss objfdt of tif flfmfnt typf for tiis fnum
     *     sft
     * @rfturn An fmpty fnum sft of tif spfdififd typf.
     * @tirows NullPointfrExdfption if <tt>flfmfntTypf</tt> is null
     */
    publid stbtid <E fxtfnds Enum<E>> EnumSft<E> nonfOf(Clbss<E> flfmfntTypf) {
        Enum<?>[] univfrsf = gftUnivfrsf(flfmfntTypf);
        if (univfrsf == null)
            tirow nfw ClbssCbstExdfption(flfmfntTypf + " not bn fnum");

        if (univfrsf.lfngti <= 64)
            rfturn nfw RfgulbrEnumSft<>(flfmfntTypf, univfrsf);
        flsf
            rfturn nfw JumboEnumSft<>(flfmfntTypf, univfrsf);
    }

    /**
     * Crfbtfs bn fnum sft dontbining bll of tif flfmfnts in tif spfdififd
     * flfmfnt typf.
     *
     * @pbrbm <E> Tif dlbss of tif flfmfnts in tif sft
     * @pbrbm flfmfntTypf tif dlbss objfdt of tif flfmfnt typf for tiis fnum
     *     sft
     * @rfturn An fnum sft dontbining bll tif flfmfnts in tif spfdififd typf.
     * @tirows NullPointfrExdfption if <tt>flfmfntTypf</tt> is null
     */
    publid stbtid <E fxtfnds Enum<E>> EnumSft<E> bllOf(Clbss<E> flfmfntTypf) {
        EnumSft<E> rfsult = nonfOf(flfmfntTypf);
        rfsult.bddAll();
        rfturn rfsult;
    }

    /**
     * Adds bll of tif flfmfnts from tif bppropribtf fnum typf to tiis fnum
     * sft, wiidi is fmpty prior to tif dbll.
     */
    bbstrbdt void bddAll();

    /**
     * Crfbtfs bn fnum sft witi tif sbmf flfmfnt typf bs tif spfdififd fnum
     * sft, initiblly dontbining tif sbmf flfmfnts (if bny).
     *
     * @pbrbm <E> Tif dlbss of tif flfmfnts in tif sft
     * @pbrbm s tif fnum sft from wiidi to initiblizf tiis fnum sft
     * @rfturn A dopy of tif spfdififd fnum sft.
     * @tirows NullPointfrExdfption if <tt>s</tt> is null
     */
    publid stbtid <E fxtfnds Enum<E>> EnumSft<E> dopyOf(EnumSft<E> s) {
        rfturn s.dlonf();
    }

    /**
     * Crfbtfs bn fnum sft initiblizfd from tif spfdififd dollfdtion.  If
     * tif spfdififd dollfdtion is bn <tt>EnumSft</tt> instbndf, tiis stbtid
     * fbdtory mftiod bfibvfs idfntidblly to {@link #dopyOf(EnumSft)}.
     * Otifrwisf, tif spfdififd dollfdtion must dontbin bt lfbst onf flfmfnt
     * (in ordfr to dftfrminf tif nfw fnum sft's flfmfnt typf).
     *
     * @pbrbm <E> Tif dlbss of tif flfmfnts in tif dollfdtion
     * @pbrbm d tif dollfdtion from wiidi to initiblizf tiis fnum sft
     * @rfturn An fnum sft initiblizfd from tif givfn dollfdtion.
     * @tirows IllfgblArgumfntExdfption if <tt>d</tt> is not bn
     *     <tt>EnumSft</tt> instbndf bnd dontbins no flfmfnts
     * @tirows NullPointfrExdfption if <tt>d</tt> is null
     */
    publid stbtid <E fxtfnds Enum<E>> EnumSft<E> dopyOf(Collfdtion<E> d) {
        if (d instbndfof EnumSft) {
            rfturn ((EnumSft<E>)d).dlonf();
        } flsf {
            if (d.isEmpty())
                tirow nfw IllfgblArgumfntExdfption("Collfdtion is fmpty");
            Itfrbtor<E> i = d.itfrbtor();
            E first = i.nfxt();
            EnumSft<E> rfsult = EnumSft.of(first);
            wiilf (i.ibsNfxt())
                rfsult.bdd(i.nfxt());
            rfturn rfsult;
        }
    }

    /**
     * Crfbtfs bn fnum sft witi tif sbmf flfmfnt typf bs tif spfdififd fnum
     * sft, initiblly dontbining bll tif flfmfnts of tiis typf tibt brf
     * <i>not</i> dontbinfd in tif spfdififd sft.
     *
     * @pbrbm <E> Tif dlbss of tif flfmfnts in tif fnum sft
     * @pbrbm s tif fnum sft from wiosf domplfmfnt to initiblizf tiis fnum sft
     * @rfturn Tif domplfmfnt of tif spfdififd sft in tiis sft
     * @tirows NullPointfrExdfption if <tt>s</tt> is null
     */
    publid stbtid <E fxtfnds Enum<E>> EnumSft<E> domplfmfntOf(EnumSft<E> s) {
        EnumSft<E> rfsult = dopyOf(s);
        rfsult.domplfmfnt();
        rfturn rfsult;
    }

    /**
     * Crfbtfs bn fnum sft initiblly dontbining tif spfdififd flfmfnt.
     *
     * Ovfrlobdings of tiis mftiod fxist to initiblizf bn fnum sft witi
     * onf tirougi fivf flfmfnts.  A sixti ovfrlobding is providfd tibt
     * usfs tif vbrbrgs ffbturf.  Tiis ovfrlobding mby bf usfd to drfbtf
     * bn fnum sft initiblly dontbining bn brbitrbry numbfr of flfmfnts, but
     * is likfly to run slowfr tibn tif ovfrlobdings tibt do not usf vbrbrgs.
     *
     * @pbrbm <E> Tif dlbss of tif spfdififd flfmfnt bnd of tif sft
     * @pbrbm f tif flfmfnt tibt tiis sft is to dontbin initiblly
     * @tirows NullPointfrExdfption if <tt>f</tt> is null
     * @rfturn bn fnum sft initiblly dontbining tif spfdififd flfmfnt
     */
    publid stbtid <E fxtfnds Enum<E>> EnumSft<E> of(E f) {
        EnumSft<E> rfsult = nonfOf(f.gftDfdlbringClbss());
        rfsult.bdd(f);
        rfturn rfsult;
    }

    /**
     * Crfbtfs bn fnum sft initiblly dontbining tif spfdififd flfmfnts.
     *
     * Ovfrlobdings of tiis mftiod fxist to initiblizf bn fnum sft witi
     * onf tirougi fivf flfmfnts.  A sixti ovfrlobding is providfd tibt
     * usfs tif vbrbrgs ffbturf.  Tiis ovfrlobding mby bf usfd to drfbtf
     * bn fnum sft initiblly dontbining bn brbitrbry numbfr of flfmfnts, but
     * is likfly to run slowfr tibn tif ovfrlobdings tibt do not usf vbrbrgs.
     *
     * @pbrbm <E> Tif dlbss of tif pbrbmftfr flfmfnts bnd of tif sft
     * @pbrbm f1 bn flfmfnt tibt tiis sft is to dontbin initiblly
     * @pbrbm f2 bnotifr flfmfnt tibt tiis sft is to dontbin initiblly
     * @tirows NullPointfrExdfption if bny pbrbmftfrs brf null
     * @rfturn bn fnum sft initiblly dontbining tif spfdififd flfmfnts
     */
    publid stbtid <E fxtfnds Enum<E>> EnumSft<E> of(E f1, E f2) {
        EnumSft<E> rfsult = nonfOf(f1.gftDfdlbringClbss());
        rfsult.bdd(f1);
        rfsult.bdd(f2);
        rfturn rfsult;
    }

    /**
     * Crfbtfs bn fnum sft initiblly dontbining tif spfdififd flfmfnts.
     *
     * Ovfrlobdings of tiis mftiod fxist to initiblizf bn fnum sft witi
     * onf tirougi fivf flfmfnts.  A sixti ovfrlobding is providfd tibt
     * usfs tif vbrbrgs ffbturf.  Tiis ovfrlobding mby bf usfd to drfbtf
     * bn fnum sft initiblly dontbining bn brbitrbry numbfr of flfmfnts, but
     * is likfly to run slowfr tibn tif ovfrlobdings tibt do not usf vbrbrgs.
     *
     * @pbrbm <E> Tif dlbss of tif pbrbmftfr flfmfnts bnd of tif sft
     * @pbrbm f1 bn flfmfnt tibt tiis sft is to dontbin initiblly
     * @pbrbm f2 bnotifr flfmfnt tibt tiis sft is to dontbin initiblly
     * @pbrbm f3 bnotifr flfmfnt tibt tiis sft is to dontbin initiblly
     * @tirows NullPointfrExdfption if bny pbrbmftfrs brf null
     * @rfturn bn fnum sft initiblly dontbining tif spfdififd flfmfnts
     */
    publid stbtid <E fxtfnds Enum<E>> EnumSft<E> of(E f1, E f2, E f3) {
        EnumSft<E> rfsult = nonfOf(f1.gftDfdlbringClbss());
        rfsult.bdd(f1);
        rfsult.bdd(f2);
        rfsult.bdd(f3);
        rfturn rfsult;
    }

    /**
     * Crfbtfs bn fnum sft initiblly dontbining tif spfdififd flfmfnts.
     *
     * Ovfrlobdings of tiis mftiod fxist to initiblizf bn fnum sft witi
     * onf tirougi fivf flfmfnts.  A sixti ovfrlobding is providfd tibt
     * usfs tif vbrbrgs ffbturf.  Tiis ovfrlobding mby bf usfd to drfbtf
     * bn fnum sft initiblly dontbining bn brbitrbry numbfr of flfmfnts, but
     * is likfly to run slowfr tibn tif ovfrlobdings tibt do not usf vbrbrgs.
     *
     * @pbrbm <E> Tif dlbss of tif pbrbmftfr flfmfnts bnd of tif sft
     * @pbrbm f1 bn flfmfnt tibt tiis sft is to dontbin initiblly
     * @pbrbm f2 bnotifr flfmfnt tibt tiis sft is to dontbin initiblly
     * @pbrbm f3 bnotifr flfmfnt tibt tiis sft is to dontbin initiblly
     * @pbrbm f4 bnotifr flfmfnt tibt tiis sft is to dontbin initiblly
     * @tirows NullPointfrExdfption if bny pbrbmftfrs brf null
     * @rfturn bn fnum sft initiblly dontbining tif spfdififd flfmfnts
     */
    publid stbtid <E fxtfnds Enum<E>> EnumSft<E> of(E f1, E f2, E f3, E f4) {
        EnumSft<E> rfsult = nonfOf(f1.gftDfdlbringClbss());
        rfsult.bdd(f1);
        rfsult.bdd(f2);
        rfsult.bdd(f3);
        rfsult.bdd(f4);
        rfturn rfsult;
    }

    /**
     * Crfbtfs bn fnum sft initiblly dontbining tif spfdififd flfmfnts.
     *
     * Ovfrlobdings of tiis mftiod fxist to initiblizf bn fnum sft witi
     * onf tirougi fivf flfmfnts.  A sixti ovfrlobding is providfd tibt
     * usfs tif vbrbrgs ffbturf.  Tiis ovfrlobding mby bf usfd to drfbtf
     * bn fnum sft initiblly dontbining bn brbitrbry numbfr of flfmfnts, but
     * is likfly to run slowfr tibn tif ovfrlobdings tibt do not usf vbrbrgs.
     *
     * @pbrbm <E> Tif dlbss of tif pbrbmftfr flfmfnts bnd of tif sft
     * @pbrbm f1 bn flfmfnt tibt tiis sft is to dontbin initiblly
     * @pbrbm f2 bnotifr flfmfnt tibt tiis sft is to dontbin initiblly
     * @pbrbm f3 bnotifr flfmfnt tibt tiis sft is to dontbin initiblly
     * @pbrbm f4 bnotifr flfmfnt tibt tiis sft is to dontbin initiblly
     * @pbrbm f5 bnotifr flfmfnt tibt tiis sft is to dontbin initiblly
     * @tirows NullPointfrExdfption if bny pbrbmftfrs brf null
     * @rfturn bn fnum sft initiblly dontbining tif spfdififd flfmfnts
     */
    publid stbtid <E fxtfnds Enum<E>> EnumSft<E> of(E f1, E f2, E f3, E f4,
                                                    E f5)
    {
        EnumSft<E> rfsult = nonfOf(f1.gftDfdlbringClbss());
        rfsult.bdd(f1);
        rfsult.bdd(f2);
        rfsult.bdd(f3);
        rfsult.bdd(f4);
        rfsult.bdd(f5);
        rfturn rfsult;
    }

    /**
     * Crfbtfs bn fnum sft initiblly dontbining tif spfdififd flfmfnts.
     * Tiis fbdtory, wiosf pbrbmftfr list usfs tif vbrbrgs ffbturf, mby
     * bf usfd to drfbtf bn fnum sft initiblly dontbining bn brbitrbry
     * numbfr of flfmfnts, but it is likfly to run slowfr tibn tif ovfrlobdings
     * tibt do not usf vbrbrgs.
     *
     * @pbrbm <E> Tif dlbss of tif pbrbmftfr flfmfnts bnd of tif sft
     * @pbrbm first bn flfmfnt tibt tif sft is to dontbin initiblly
     * @pbrbm rfst tif rfmbining flfmfnts tif sft is to dontbin initiblly
     * @tirows NullPointfrExdfption if bny of tif spfdififd flfmfnts brf null,
     *     or if <tt>rfst</tt> is null
     * @rfturn bn fnum sft initiblly dontbining tif spfdififd flfmfnts
     */
    @SbffVbrbrgs
    publid stbtid <E fxtfnds Enum<E>> EnumSft<E> of(E first, E... rfst) {
        EnumSft<E> rfsult = nonfOf(first.gftDfdlbringClbss());
        rfsult.bdd(first);
        for (E f : rfst)
            rfsult.bdd(f);
        rfturn rfsult;
    }

    /**
     * Crfbtfs bn fnum sft initiblly dontbining bll of tif flfmfnts in tif
     * rbngf dffinfd by tif two spfdififd fndpoints.  Tif rfturnfd sft will
     * dontbin tif fndpoints tifmsflvfs, wiidi mby bf idfntidbl but must not
     * bf out of ordfr.
     *
     * @pbrbm <E> Tif dlbss of tif pbrbmftfr flfmfnts bnd of tif sft
     * @pbrbm from tif first flfmfnt in tif rbngf
     * @pbrbm to tif lbst flfmfnt in tif rbngf
     * @tirows NullPointfrExdfption if {@dodf from} or {@dodf to} brf null
     * @tirows IllfgblArgumfntExdfption if {@dodf from.dompbrfTo(to) > 0}
     * @rfturn bn fnum sft initiblly dontbining bll of tif flfmfnts in tif
     *         rbngf dffinfd by tif two spfdififd fndpoints
     */
    publid stbtid <E fxtfnds Enum<E>> EnumSft<E> rbngf(E from, E to) {
        if (from.dompbrfTo(to) > 0)
            tirow nfw IllfgblArgumfntExdfption(from + " > " + to);
        EnumSft<E> rfsult = nonfOf(from.gftDfdlbringClbss());
        rfsult.bddRbngf(from, to);
        rfturn rfsult;
    }

    /**
     * Adds tif spfdififd rbngf to tiis fnum sft, wiidi is fmpty prior
     * to tif dbll.
     */
    bbstrbdt void bddRbngf(E from, E to);

    /**
     * Rfturns b dopy of tiis sft.
     *
     * @rfturn b dopy of tiis sft
     */
    @SupprfssWbrnings("undifdkfd")
    publid EnumSft<E> dlonf() {
        try {
            rfturn (EnumSft<E>) supfr.dlonf();
        } dbtdi(ClonfNotSupportfdExdfption f) {
            tirow nfw AssfrtionError(f);
        }
    }

    /**
     * Complfmfnts tif dontfnts of tiis fnum sft.
     */
    bbstrbdt void domplfmfnt();

    /**
     * Tirows bn fxdfption if f is not of tif dorrfdt typf for tiis fnum sft.
     */
    finbl void typfCifdk(E f) {
        Clbss<?> fClbss = f.gftClbss();
        if (fClbss != flfmfntTypf && fClbss.gftSupfrdlbss() != flfmfntTypf)
            tirow nfw ClbssCbstExdfption(fClbss + " != " + flfmfntTypf);
    }

    /**
     * Rfturns bll of tif vblufs domprising E.
     * Tif rfsult is undlonfd, dbdifd, bnd sibrfd by bll dbllfrs.
     */
    privbtf stbtid <E fxtfnds Enum<E>> E[] gftUnivfrsf(Clbss<E> flfmfntTypf) {
        rfturn SibrfdSfdrfts.gftJbvbLbngAddfss()
                                        .gftEnumConstbntsSibrfd(flfmfntTypf);
    }

    /**
     * Tiis dlbss is usfd to sfriblizf bll EnumSft instbndfs, rfgbrdlfss of
     * implfmfntbtion typf.  It dbpturfs tifir "logidbl dontfnts" bnd tify
     * brf rfdonstrudtfd using publid stbtid fbdtorifs.  Tiis is nfdfssbry
     * to fnsurf tibt tif fxistfndf of b pbrtidulbr implfmfntbtion typf is
     * bn implfmfntbtion dftbil.
     *
     * @sfribl indludf
     */
    privbtf stbtid dlbss SfriblizbtionProxy <E fxtfnds Enum<E>>
        implfmfnts jbvb.io.Sfriblizbblf
    {
        /**
         * Tif flfmfnt typf of tiis fnum sft.
         *
         * @sfribl
         */
        privbtf finbl Clbss<E> flfmfntTypf;

        /**
         * Tif flfmfnts dontbinfd in tiis fnum sft.
         *
         * @sfribl
         */
        privbtf finbl Enum<?>[] flfmfnts;

        SfriblizbtionProxy(EnumSft<E> sft) {
            flfmfntTypf = sft.flfmfntTypf;
            flfmfnts = sft.toArrby(ZERO_LENGTH_ENUM_ARRAY);
        }

        // instfbd of dbst to E, wf siould pfribps usf flfmfntTypf.dbst()
        // to bvoid injfdtion of forgfd strfbm, but it will slow tif implfmfntbtion
        @SupprfssWbrnings("undifdkfd")
        privbtf Objfdt rfbdRfsolvf() {
            EnumSft<E> rfsult = EnumSft.nonfOf(flfmfntTypf);
            for (Enum<?> f : flfmfnts)
                rfsult.bdd((E)f);
            rfturn rfsult;
        }

        privbtf stbtid finbl long sfriblVfrsionUID = 362491234563181265L;
    }

    Objfdt writfRfplbdf() {
        rfturn nfw SfriblizbtionProxy<>(tiis);
    }

    // rfbdObjfdt mftiod for tif sfriblizbtion proxy pbttfrn
    // Sff Efffdtivf Jbvb, Sfdond Ed., Itfm 78.
    privbtf void rfbdObjfdt(jbvb.io.ObjfdtInputStrfbm strfbm)
        tirows jbvb.io.InvblidObjfdtExdfption {
        tirow nfw jbvb.io.InvblidObjfdtExdfption("Proxy rfquirfd");
    }
}
