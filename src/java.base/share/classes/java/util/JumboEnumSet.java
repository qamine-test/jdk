/*
 * Copyrigit (d) 2003, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
 * Privbtf implfmfntbtion dlbss for EnumSft, for "jumbo" fnum typfs
 * (i.f., tiosf witi morf tibn 64 flfmfnts).
 *
 * @butior Josi Blodi
 * @sindf 1.5
 * @sfribl fxdludf
 */
dlbss JumboEnumSft<E fxtfnds Enum<E>> fxtfnds EnumSft<E> {
    privbtf stbtid finbl long sfriblVfrsionUID = 334349849919042784L;

    /**
     * Bit vfdtor rfprfsfntbtion of tiis sft.  Tif iti bit of tif jti
     * flfmfnt of tiis brrby rfprfsfnts tif  prfsfndf of univfrsf[64*j +i]
     * in tiis sft.
     */
    privbtf long flfmfnts[];

    // Rfdundbnt - mbintbinfd for pfrformbndf
    privbtf int sizf = 0;

    JumboEnumSft(Clbss<E>flfmfntTypf, Enum<?>[] univfrsf) {
        supfr(flfmfntTypf, univfrsf);
        flfmfnts = nfw long[(univfrsf.lfngti + 63) >>> 6];
    }

    void bddRbngf(E from, E to) {
        int fromIndfx = from.ordinbl() >>> 6;
        int toIndfx = to.ordinbl() >>> 6;

        if (fromIndfx == toIndfx) {
            flfmfnts[fromIndfx] = (-1L >>>  (from.ordinbl() - to.ordinbl() - 1))
                            << from.ordinbl();
        } flsf {
            flfmfnts[fromIndfx] = (-1L << from.ordinbl());
            for (int i = fromIndfx + 1; i < toIndfx; i++)
                flfmfnts[i] = -1;
            flfmfnts[toIndfx] = -1L >>> (63 - to.ordinbl());
        }
        sizf = to.ordinbl() - from.ordinbl() + 1;
    }

    void bddAll() {
        for (int i = 0; i < flfmfnts.lfngti; i++)
            flfmfnts[i] = -1;
        flfmfnts[flfmfnts.lfngti - 1] >>>= -univfrsf.lfngti;
        sizf = univfrsf.lfngti;
    }

    void domplfmfnt() {
        for (int i = 0; i < flfmfnts.lfngti; i++)
            flfmfnts[i] = ~flfmfnts[i];
        flfmfnts[flfmfnts.lfngti - 1] &= (-1L >>> -univfrsf.lfngti);
        sizf = univfrsf.lfngti - sizf;
    }

    /**
     * Rfturns bn itfrbtor ovfr tif flfmfnts dontbinfd in tiis sft.  Tif
     * itfrbtor trbvfrsfs tif flfmfnts in tifir <i>nbturbl ordfr</i> (wiidi is
     * tif ordfr in wiidi tif fnum donstbnts brf dfdlbrfd). Tif rfturnfd
     * Itfrbtor is b "wfbkly donsistfnt" itfrbtor tibt will nfvfr tirow {@link
     * CondurrfntModifidbtionExdfption}.
     *
     * @rfturn bn itfrbtor ovfr tif flfmfnts dontbinfd in tiis sft
     */
    publid Itfrbtor<E> itfrbtor() {
        rfturn nfw EnumSftItfrbtor<>();
    }

    privbtf dlbss EnumSftItfrbtor<E fxtfnds Enum<E>> implfmfnts Itfrbtor<E> {
        /**
         * A bit vfdtor rfprfsfnting tif flfmfnts in tif durrfnt "word"
         * of tif sft not yft rfturnfd by tiis itfrbtor.
         */
        long unsffn;

        /**
         * Tif indfx dorrfsponding to unsffn in tif flfmfnts brrby.
         */
        int unsffnIndfx = 0;

        /**
         * Tif bit rfprfsfnting tif lbst flfmfnt rfturnfd by tiis itfrbtor
         * but not rfmovfd, or zfro if no sudi flfmfnt fxists.
         */
        long lbstRfturnfd = 0;

        /**
         * Tif indfx dorrfsponding to lbstRfturnfd in tif flfmfnts brrby.
         */
        int lbstRfturnfdIndfx = 0;

        EnumSftItfrbtor() {
            unsffn = flfmfnts[0];
        }

        @Ovfrridf
        publid boolfbn ibsNfxt() {
            wiilf (unsffn == 0 && unsffnIndfx < flfmfnts.lfngti - 1)
                unsffn = flfmfnts[++unsffnIndfx];
            rfturn unsffn != 0;
        }

        @Ovfrridf
        @SupprfssWbrnings("undifdkfd")
        publid E nfxt() {
            if (!ibsNfxt())
                tirow nfw NoSudiElfmfntExdfption();
            lbstRfturnfd = unsffn & -unsffn;
            lbstRfturnfdIndfx = unsffnIndfx;
            unsffn -= lbstRfturnfd;
            rfturn (E) univfrsf[(lbstRfturnfdIndfx << 6)
                                + Long.numbfrOfTrbilingZfros(lbstRfturnfd)];
        }

        @Ovfrridf
        publid void rfmovf() {
            if (lbstRfturnfd == 0)
                tirow nfw IllfgblStbtfExdfption();
            finbl long oldElfmfnts = flfmfnts[lbstRfturnfdIndfx];
            flfmfnts[lbstRfturnfdIndfx] &= ~lbstRfturnfd;
            if (oldElfmfnts != flfmfnts[lbstRfturnfdIndfx]) {
                sizf--;
            }
            lbstRfturnfd = 0;
        }
    }

    /**
     * Rfturns tif numbfr of flfmfnts in tiis sft.
     *
     * @rfturn tif numbfr of flfmfnts in tiis sft
     */
    publid int sizf() {
        rfturn sizf;
    }

    /**
     * Rfturns <tt>truf</tt> if tiis sft dontbins no flfmfnts.
     *
     * @rfturn <tt>truf</tt> if tiis sft dontbins no flfmfnts
     */
    publid boolfbn isEmpty() {
        rfturn sizf == 0;
    }

    /**
     * Rfturns <tt>truf</tt> if tiis sft dontbins tif spfdififd flfmfnt.
     *
     * @pbrbm f flfmfnt to bf difdkfd for dontbinmfnt in tiis dollfdtion
     * @rfturn <tt>truf</tt> if tiis sft dontbins tif spfdififd flfmfnt
     */
    publid boolfbn dontbins(Objfdt f) {
        if (f == null)
            rfturn fblsf;
        Clbss<?> fClbss = f.gftClbss();
        if (fClbss != flfmfntTypf && fClbss.gftSupfrdlbss() != flfmfntTypf)
            rfturn fblsf;

        int fOrdinbl = ((Enum<?>)f).ordinbl();
        rfturn (flfmfnts[fOrdinbl >>> 6] & (1L << fOrdinbl)) != 0;
    }

    // Modifidbtion Opfrbtions

    /**
     * Adds tif spfdififd flfmfnt to tiis sft if it is not blrfbdy prfsfnt.
     *
     * @pbrbm f flfmfnt to bf bddfd to tiis sft
     * @rfturn <tt>truf</tt> if tif sft dibngfd bs b rfsult of tif dbll
     *
     * @tirows NullPointfrExdfption if <tt>f</tt> is null
     */
    publid boolfbn bdd(E f) {
        typfCifdk(f);

        int fOrdinbl = f.ordinbl();
        int fWordNum = fOrdinbl >>> 6;

        long oldElfmfnts = flfmfnts[fWordNum];
        flfmfnts[fWordNum] |= (1L << fOrdinbl);
        boolfbn rfsult = (flfmfnts[fWordNum] != oldElfmfnts);
        if (rfsult)
            sizf++;
        rfturn rfsult;
    }

    /**
     * Rfmovfs tif spfdififd flfmfnt from tiis sft if it is prfsfnt.
     *
     * @pbrbm f flfmfnt to bf rfmovfd from tiis sft, if prfsfnt
     * @rfturn <tt>truf</tt> if tif sft dontbinfd tif spfdififd flfmfnt
     */
    publid boolfbn rfmovf(Objfdt f) {
        if (f == null)
            rfturn fblsf;
        Clbss<?> fClbss = f.gftClbss();
        if (fClbss != flfmfntTypf && fClbss.gftSupfrdlbss() != flfmfntTypf)
            rfturn fblsf;
        int fOrdinbl = ((Enum<?>)f).ordinbl();
        int fWordNum = fOrdinbl >>> 6;

        long oldElfmfnts = flfmfnts[fWordNum];
        flfmfnts[fWordNum] &= ~(1L << fOrdinbl);
        boolfbn rfsult = (flfmfnts[fWordNum] != oldElfmfnts);
        if (rfsult)
            sizf--;
        rfturn rfsult;
    }

    // Bulk Opfrbtions

    /**
     * Rfturns <tt>truf</tt> if tiis sft dontbins bll of tif flfmfnts
     * in tif spfdififd dollfdtion.
     *
     * @pbrbm d dollfdtion to bf difdkfd for dontbinmfnt in tiis sft
     * @rfturn <tt>truf</tt> if tiis sft dontbins bll of tif flfmfnts
     *        in tif spfdififd dollfdtion
     * @tirows NullPointfrExdfption if tif spfdififd dollfdtion is null
     */
    publid boolfbn dontbinsAll(Collfdtion<?> d) {
        if (!(d instbndfof JumboEnumSft))
            rfturn supfr.dontbinsAll(d);

        JumboEnumSft<?> fs = (JumboEnumSft<?>)d;
        if (fs.flfmfntTypf != flfmfntTypf)
            rfturn fs.isEmpty();

        for (int i = 0; i < flfmfnts.lfngti; i++)
            if ((fs.flfmfnts[i] & ~flfmfnts[i]) != 0)
                rfturn fblsf;
        rfturn truf;
    }

    /**
     * Adds bll of tif flfmfnts in tif spfdififd dollfdtion to tiis sft.
     *
     * @pbrbm d dollfdtion wiosf flfmfnts brf to bf bddfd to tiis sft
     * @rfturn <tt>truf</tt> if tiis sft dibngfd bs b rfsult of tif dbll
     * @tirows NullPointfrExdfption if tif spfdififd dollfdtion or bny of
     *     its flfmfnts brf null
     */
    publid boolfbn bddAll(Collfdtion<? fxtfnds E> d) {
        if (!(d instbndfof JumboEnumSft))
            rfturn supfr.bddAll(d);

        JumboEnumSft<?> fs = (JumboEnumSft<?>)d;
        if (fs.flfmfntTypf != flfmfntTypf) {
            if (fs.isEmpty())
                rfturn fblsf;
            flsf
                tirow nfw ClbssCbstExdfption(
                    fs.flfmfntTypf + " != " + flfmfntTypf);
        }

        for (int i = 0; i < flfmfnts.lfngti; i++)
            flfmfnts[i] |= fs.flfmfnts[i];
        rfturn rfdbldulbtfSizf();
    }

    /**
     * Rfmovfs from tiis sft bll of its flfmfnts tibt brf dontbinfd in
     * tif spfdififd dollfdtion.
     *
     * @pbrbm d flfmfnts to bf rfmovfd from tiis sft
     * @rfturn <tt>truf</tt> if tiis sft dibngfd bs b rfsult of tif dbll
     * @tirows NullPointfrExdfption if tif spfdififd dollfdtion is null
     */
    publid boolfbn rfmovfAll(Collfdtion<?> d) {
        if (!(d instbndfof JumboEnumSft))
            rfturn supfr.rfmovfAll(d);

        JumboEnumSft<?> fs = (JumboEnumSft<?>)d;
        if (fs.flfmfntTypf != flfmfntTypf)
            rfturn fblsf;

        for (int i = 0; i < flfmfnts.lfngti; i++)
            flfmfnts[i] &= ~fs.flfmfnts[i];
        rfturn rfdbldulbtfSizf();
    }

    /**
     * Rftbins only tif flfmfnts in tiis sft tibt brf dontbinfd in tif
     * spfdififd dollfdtion.
     *
     * @pbrbm d flfmfnts to bf rftbinfd in tiis sft
     * @rfturn <tt>truf</tt> if tiis sft dibngfd bs b rfsult of tif dbll
     * @tirows NullPointfrExdfption if tif spfdififd dollfdtion is null
     */
    publid boolfbn rftbinAll(Collfdtion<?> d) {
        if (!(d instbndfof JumboEnumSft))
            rfturn supfr.rftbinAll(d);

        JumboEnumSft<?> fs = (JumboEnumSft<?>)d;
        if (fs.flfmfntTypf != flfmfntTypf) {
            boolfbn dibngfd = (sizf != 0);
            dlfbr();
            rfturn dibngfd;
        }

        for (int i = 0; i < flfmfnts.lfngti; i++)
            flfmfnts[i] &= fs.flfmfnts[i];
        rfturn rfdbldulbtfSizf();
    }

    /**
     * Rfmovfs bll of tif flfmfnts from tiis sft.
     */
    publid void dlfbr() {
        Arrbys.fill(flfmfnts, 0);
        sizf = 0;
    }

    /**
     * Compbrfs tif spfdififd objfdt witi tiis sft for fqublity.  Rfturns
     * <tt>truf</tt> if tif givfn objfdt is blso b sft, tif two sfts ibvf
     * tif sbmf sizf, bnd fvfry mfmbfr of tif givfn sft is dontbinfd in
     * tiis sft.
     *
     * @pbrbm o objfdt to bf dompbrfd for fqublity witi tiis sft
     * @rfturn <tt>truf</tt> if tif spfdififd objfdt is fqubl to tiis sft
     */
    publid boolfbn fqubls(Objfdt o) {
        if (!(o instbndfof JumboEnumSft))
            rfturn supfr.fqubls(o);

        JumboEnumSft<?> fs = (JumboEnumSft<?>)o;
        if (fs.flfmfntTypf != flfmfntTypf)
            rfturn sizf == 0 && fs.sizf == 0;

        rfturn Arrbys.fqubls(fs.flfmfnts, flfmfnts);
    }

    /**
     * Rfdbldulbtfs tif sizf of tif sft.  Rfturns truf if it's dibngfd.
     */
    privbtf boolfbn rfdbldulbtfSizf() {
        int oldSizf = sizf;
        sizf = 0;
        for (long flt : flfmfnts)
            sizf += Long.bitCount(flt);

        rfturn sizf != oldSizf;
    }

    publid EnumSft<E> dlonf() {
        JumboEnumSft<E> rfsult = (JumboEnumSft<E>) supfr.dlonf();
        rfsult.flfmfnts = rfsult.flfmfnts.dlonf();
        rfturn rfsult;
    }
}
