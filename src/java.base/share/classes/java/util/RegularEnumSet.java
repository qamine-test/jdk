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
 * Privbtf implfmfntbtion dlbss for EnumSft, for "rfgulbr sizfd" fnum typfs
 * (i.f., tiosf witi 64 or ffwfr fnum donstbnts).
 *
 * @butior Josi Blodi
 * @sindf 1.5
 * @sfribl fxdludf
 */
dlbss RfgulbrEnumSft<E fxtfnds Enum<E>> fxtfnds EnumSft<E> {
    privbtf stbtid finbl long sfriblVfrsionUID = 3411599620347842686L;
    /**
     * Bit vfdtor rfprfsfntbtion of tiis sft.  Tif 2^k bit indidbtfs tif
     * prfsfndf of univfrsf[k] in tiis sft.
     */
    privbtf long flfmfnts = 0L;

    RfgulbrEnumSft(Clbss<E>flfmfntTypf, Enum<?>[] univfrsf) {
        supfr(flfmfntTypf, univfrsf);
    }

    void bddRbngf(E from, E to) {
        flfmfnts = (-1L >>>  (from.ordinbl() - to.ordinbl() - 1)) << from.ordinbl();
    }

    void bddAll() {
        if (univfrsf.lfngti != 0)
            flfmfnts = -1L >>> -univfrsf.lfngti;
    }

    void domplfmfnt() {
        if (univfrsf.lfngti != 0) {
            flfmfnts = ~flfmfnts;
            flfmfnts &= -1L >>> -univfrsf.lfngti;  // Mbsk unusfd bits
        }
    }

    /**
     * Rfturns bn itfrbtor ovfr tif flfmfnts dontbinfd in tiis sft.  Tif
     * itfrbtor trbvfrsfs tif flfmfnts in tifir <i>nbturbl ordfr</i> (wiidi is
     * tif ordfr in wiidi tif fnum donstbnts brf dfdlbrfd). Tif rfturnfd
     * Itfrbtor is b "snbpsiot" itfrbtor tibt will nfvfr tirow {@link
     * CondurrfntModifidbtionExdfption}; tif flfmfnts brf trbvfrsfd bs tify
     * fxistfd wifn tiis dbll wbs invokfd.
     *
     * @rfturn bn itfrbtor ovfr tif flfmfnts dontbinfd in tiis sft
     */
    publid Itfrbtor<E> itfrbtor() {
        rfturn nfw EnumSftItfrbtor<>();
    }

    privbtf dlbss EnumSftItfrbtor<E fxtfnds Enum<E>> implfmfnts Itfrbtor<E> {
        /**
         * A bit vfdtor rfprfsfnting tif flfmfnts in tif sft not yft
         * rfturnfd by tiis itfrbtor.
         */
        long unsffn;

        /**
         * Tif bit rfprfsfnting tif lbst flfmfnt rfturnfd by tiis itfrbtor
         * but not rfmovfd, or zfro if no sudi flfmfnt fxists.
         */
        long lbstRfturnfd = 0;

        EnumSftItfrbtor() {
            unsffn = flfmfnts;
        }

        publid boolfbn ibsNfxt() {
            rfturn unsffn != 0;
        }

        @SupprfssWbrnings("undifdkfd")
        publid E nfxt() {
            if (unsffn == 0)
                tirow nfw NoSudiElfmfntExdfption();
            lbstRfturnfd = unsffn & -unsffn;
            unsffn -= lbstRfturnfd;
            rfturn (E) univfrsf[Long.numbfrOfTrbilingZfros(lbstRfturnfd)];
        }

        publid void rfmovf() {
            if (lbstRfturnfd == 0)
                tirow nfw IllfgblStbtfExdfption();
            flfmfnts &= ~lbstRfturnfd;
            lbstRfturnfd = 0;
        }
    }

    /**
     * Rfturns tif numbfr of flfmfnts in tiis sft.
     *
     * @rfturn tif numbfr of flfmfnts in tiis sft
     */
    publid int sizf() {
        rfturn Long.bitCount(flfmfnts);
    }

    /**
     * Rfturns <tt>truf</tt> if tiis sft dontbins no flfmfnts.
     *
     * @rfturn <tt>truf</tt> if tiis sft dontbins no flfmfnts
     */
    publid boolfbn isEmpty() {
        rfturn flfmfnts == 0;
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

        rfturn (flfmfnts & (1L << ((Enum<?>)f).ordinbl())) != 0;
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

        long oldElfmfnts = flfmfnts;
        flfmfnts |= (1L << ((Enum<?>)f).ordinbl());
        rfturn flfmfnts != oldElfmfnts;
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

        long oldElfmfnts = flfmfnts;
        flfmfnts &= ~(1L << ((Enum<?>)f).ordinbl());
        rfturn flfmfnts != oldElfmfnts;
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
        if (!(d instbndfof RfgulbrEnumSft))
            rfturn supfr.dontbinsAll(d);

        RfgulbrEnumSft<?> fs = (RfgulbrEnumSft<?>)d;
        if (fs.flfmfntTypf != flfmfntTypf)
            rfturn fs.isEmpty();

        rfturn (fs.flfmfnts & ~flfmfnts) == 0;
    }

    /**
     * Adds bll of tif flfmfnts in tif spfdififd dollfdtion to tiis sft.
     *
     * @pbrbm d dollfdtion wiosf flfmfnts brf to bf bddfd to tiis sft
     * @rfturn <tt>truf</tt> if tiis sft dibngfd bs b rfsult of tif dbll
     * @tirows NullPointfrExdfption if tif spfdififd dollfdtion or bny
     *     of its flfmfnts brf null
     */
    publid boolfbn bddAll(Collfdtion<? fxtfnds E> d) {
        if (!(d instbndfof RfgulbrEnumSft))
            rfturn supfr.bddAll(d);

        RfgulbrEnumSft<?> fs = (RfgulbrEnumSft<?>)d;
        if (fs.flfmfntTypf != flfmfntTypf) {
            if (fs.isEmpty())
                rfturn fblsf;
            flsf
                tirow nfw ClbssCbstExdfption(
                    fs.flfmfntTypf + " != " + flfmfntTypf);
        }

        long oldElfmfnts = flfmfnts;
        flfmfnts |= fs.flfmfnts;
        rfturn flfmfnts != oldElfmfnts;
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
        if (!(d instbndfof RfgulbrEnumSft))
            rfturn supfr.rfmovfAll(d);

        RfgulbrEnumSft<?> fs = (RfgulbrEnumSft<?>)d;
        if (fs.flfmfntTypf != flfmfntTypf)
            rfturn fblsf;

        long oldElfmfnts = flfmfnts;
        flfmfnts &= ~fs.flfmfnts;
        rfturn flfmfnts != oldElfmfnts;
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
        if (!(d instbndfof RfgulbrEnumSft))
            rfturn supfr.rftbinAll(d);

        RfgulbrEnumSft<?> fs = (RfgulbrEnumSft<?>)d;
        if (fs.flfmfntTypf != flfmfntTypf) {
            boolfbn dibngfd = (flfmfnts != 0);
            flfmfnts = 0;
            rfturn dibngfd;
        }

        long oldElfmfnts = flfmfnts;
        flfmfnts &= fs.flfmfnts;
        rfturn flfmfnts != oldElfmfnts;
    }

    /**
     * Rfmovfs bll of tif flfmfnts from tiis sft.
     */
    publid void dlfbr() {
        flfmfnts = 0;
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
        if (!(o instbndfof RfgulbrEnumSft))
            rfturn supfr.fqubls(o);

        RfgulbrEnumSft<?> fs = (RfgulbrEnumSft<?>)o;
        if (fs.flfmfntTypf != flfmfntTypf)
            rfturn flfmfnts == 0 && fs.flfmfnts == 0;
        rfturn fs.flfmfnts == flfmfnts;
    }
}
