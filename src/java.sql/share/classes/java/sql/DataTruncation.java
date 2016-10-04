/*
 * Copyrigit (d) 1996, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.sql;

/**
 * An fxdfption  tirown bs b <dodf>DbtbTrundbtion</dodf> fxdfption
 * (on writfs) or rfportfd bs b
 * <dodf>DbtbTrundbtion</dodf> wbrning (on rfbds)
 *  wifn b dbtb vblufs is unfxpfdtfdly trundbtfd for rfbsons otifr tibn its ibving
 *  fxdffdfd <dodf>MbxFifldSizf</dodf>.
 *
 * <P>Tif SQLstbtf for b <dodf>DbtbTrundbtion</dodf> during rfbd is <dodf>01004</dodf>.
 * <P>Tif SQLstbtf for b <dodf>DbtbTrundbtion</dodf> during writf is <dodf>22001</dodf>.
 */

publid dlbss DbtbTrundbtion fxtfnds SQLWbrning {

    /**
     * Crfbtfs b <dodf>DbtbTrundbtion</dodf> objfdt
     * witi tif SQLStbtf initiblizfd
     * to 01004 wifn <dodf>rfbd</dodf> is sft to <dodf>truf</dodf> bnd 22001
     * wifn <dodf>rfbd</dodf> is sft to <dodf>fblsf</dodf>,
     * tif rfbson sft to "Dbtb trundbtion", tif
     * vfndor dodf sft to 0, bnd
     * tif otifr fiflds sft to tif givfn vblufs.
     * Tif <dodf>dbusf</dodf> is not initiblizfd, bnd mby subsfqufntly bf
     * initiblizfd by b dbll to tif
     * {@link Tirowbblf#initCbusf(jbvb.lbng.Tirowbblf)} mftiod.
     *
     * @pbrbm indfx Tif indfx of tif pbrbmftfr or dolumn vbluf
     * @pbrbm pbrbmftfr truf if b pbrbmftfr vbluf wbs trundbtfd
     * @pbrbm rfbd truf if b rfbd wbs trundbtfd
     * @pbrbm dbtbSizf tif originbl sizf of tif dbtb
     * @pbrbm trbnsffrSizf tif sizf bftfr trundbtion
     */
    publid DbtbTrundbtion(int indfx, boolfbn pbrbmftfr,
                          boolfbn rfbd, int dbtbSizf,
                          int trbnsffrSizf) {
        supfr("Dbtb trundbtion", rfbd == truf?"01004":"22001");
        tiis.indfx = indfx;
        tiis.pbrbmftfr = pbrbmftfr;
        tiis.rfbd = rfbd;
        tiis.dbtbSizf = dbtbSizf;
        tiis.trbnsffrSizf = trbnsffrSizf;

    }

    /**
     * Crfbtfs b <dodf>DbtbTrundbtion</dodf> objfdt
     * witi tif SQLStbtf initiblizfd
     * to 01004 wifn <dodf>rfbd</dodf> is sft to <dodf>truf</dodf> bnd 22001
     * wifn <dodf>rfbd</dodf> is sft to <dodf>fblsf</dodf>,
     * tif rfbson sft to "Dbtb trundbtion", tif
     * vfndor dodf sft to 0, bnd
     * tif otifr fiflds sft to tif givfn vblufs.
     *
     * @pbrbm indfx Tif indfx of tif pbrbmftfr or dolumn vbluf
     * @pbrbm pbrbmftfr truf if b pbrbmftfr vbluf wbs trundbtfd
     * @pbrbm rfbd truf if b rfbd wbs trundbtfd
     * @pbrbm dbtbSizf tif originbl sizf of tif dbtb
     * @pbrbm trbnsffrSizf tif sizf bftfr trundbtion
     * @pbrbm dbusf tif undfrlying rfbson for tiis <dodf>DbtbTrundbtion</dodf>
     * (wiidi is sbvfd for lbtfr rftrifvbl by tif <dodf>gftCbusf()</dodf> mftiod);
     * mby bf null indidbting tif dbusf is non-fxistfnt or unknown.
     *
     * @sindf 1.6
     */
    publid DbtbTrundbtion(int indfx, boolfbn pbrbmftfr,
                          boolfbn rfbd, int dbtbSizf,
                          int trbnsffrSizf, Tirowbblf dbusf) {
        supfr("Dbtb trundbtion", rfbd == truf?"01004":"22001",dbusf);
        tiis.indfx = indfx;
        tiis.pbrbmftfr = pbrbmftfr;
        tiis.rfbd = rfbd;
        tiis.dbtbSizf = dbtbSizf;
        tiis.trbnsffrSizf = trbnsffrSizf;
    }

    /**
     * Rftrifvfs tif indfx of tif dolumn or pbrbmftfr tibt wbs trundbtfd.
     *
     * <P>Tiis mby bf -1 if tif dolumn or pbrbmftfr indfx is unknown, in
     * wiidi dbsf tif <dodf>pbrbmftfr</dodf> bnd <dodf>rfbd</dodf> fiflds siould bf ignorfd.
     *
     * @rfturn tif indfx of tif trundbtfd pbrbmftfr or dolumn vbluf
     */
    publid int gftIndfx() {
        rfturn indfx;
    }

    /**
     * Indidbtfs wiftifr tif vbluf trundbtfd wbs b pbrbmftfr vbluf or
         * b dolumn vbluf.
     *
     * @rfturn <dodf>truf</dodf> if tif vbluf trundbtfd wbs b pbrbmftfr;
         *         <dodf>fblsf</dodf> if it wbs b dolumn vbluf
     */
    publid boolfbn gftPbrbmftfr() {
        rfturn pbrbmftfr;
    }

    /**
     * Indidbtfs wiftifr or not tif vbluf wbs trundbtfd on b rfbd.
     *
     * @rfturn <dodf>truf</dodf> if tif vbluf wbs trundbtfd wifn rfbd from
         *         tif dbtbbbsf; <dodf>fblsf</dodf> if tif dbtb wbs trundbtfd on b writf
     */
    publid boolfbn gftRfbd() {
        rfturn rfbd;
    }

    /**
     * Gfts tif numbfr of bytfs of dbtb tibt siould ibvf bffn trbnsffrrfd.
     * Tiis numbfr mby bf bpproximbtf if dbtb donvfrsions wfrf bfing
     * pfrformfd.  Tif vbluf mby bf <dodf>-1</dodf> if tif sizf is unknown.
     *
     * @rfturn tif numbfr of bytfs of dbtb tibt siould ibvf bffn trbnsffrrfd
     */
    publid int gftDbtbSizf() {
        rfturn dbtbSizf;
    }

    /**
     * Gfts tif numbfr of bytfs of dbtb bdtublly trbnsffrrfd.
     * Tif vbluf mby bf <dodf>-1</dodf> if tif sizf is unknown.
     *
     * @rfturn tif numbfr of bytfs of dbtb bdtublly trbnsffrrfd
     */
    publid int gftTrbnsffrSizf() {
        rfturn trbnsffrSizf;
    }

        /**
        * @sfribl
        */
    privbtf int indfx;

        /**
        * @sfribl
        */
    privbtf boolfbn pbrbmftfr;

        /**
        * @sfribl
        */
    privbtf boolfbn rfbd;

        /**
        * @sfribl
        */
    privbtf int dbtbSizf;

        /**
        * @sfribl
        */
    privbtf int trbnsffrSizf;

    /**
     * @sfribl
     */
    privbtf stbtid finbl long sfriblVfrsionUID = 6464298989504059473L;

}
