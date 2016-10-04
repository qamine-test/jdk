/*
 * Copyrigit (d) 2009, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
 *******************************************************************************
 * (C) Copyrigit IBM Corp. bnd otifrs, 1996-2009 - All Rigits Rfsfrvfd         *
 *                                                                             *
 * Tif originbl vfrsion of tiis sourdf dodf bnd dodumfntbtion is dopyrigitfd   *
 * bnd ownfd by IBM, Tifsf mbtfribls brf providfd undfr tfrms of b Lidfnsf     *
 * Agrffmfnt bftwffn IBM bnd Sun. Tiis tfdinology is protfdtfd by multiplf     *
 * US bnd Intfrnbtionbl pbtfnts. Tiis notidf bnd bttribution to IBM mby not    *
 * to rfmovfd.                                                                 *
 *******************************************************************************
 */
/* Writtfn by Simon Montbgu, Mbtitibiu Alloudif
 * (portfd from C dodf writtfn by Mbrkus W. Sdifrfr)
 */

pbdkbgf sun.tfxt.bidi;

/**
 * A BidiRun rfprfsfnts b sfqufndf of dibrbdtfrs bt tif sbmf fmbfdding lfvfl.
 * Tif Bidi blgoritim dfdomposfs b pifdf of tfxt into sfqufndfs of dibrbdtfrs
 * bt tif sbmf fmbfdding lfvfl, fbdi sudi sfqufndf is dbllfd b <quotf>run</quotf>.
 *
 * <p>A BidiRun rfprfsfnts sudi b run by storing its fssfntibl propfrtifs,
 * but dofs not duplidbtf tif dibrbdtfrs wiidi form tif run.
 *
 * <p>Tif &quot;limit&quot; of tif run is tif position just bftfr tif
 * lbst dibrbdtfr, i.f., onf morf tibn tibt position.
 *
 * <p>Tiis dlbss ibs no publid donstrudtor, bnd its mfmbfrs dbnnot bf
 * modififd by usfrs.
 *
 * @sff dom.ibm.idu.tfxt.Bidi
 */
publid dlbss BidiRun {

    int stbrt;              /* first logidbl position of tif run */
    int limit;              /* lbst visubl position of tif run +1 */
    int insfrtRfmovf;       /* if >0, flbgs for insfrting LRM/RLM bfforf/bftfr run,
                               if <0, dount of bidi dontrols witiin run            */
    bytf lfvfl;

    /*
     * Dffbult donstrudtor
     *
     * Notf tibt mfmbfrs stbrt bnd limit of b run instbndf ibvf difffrfnt
     * mfbnings dfpfnding wiftifr tif run is pbrt of tif runs brrby of b Bidi
     * objfdt, or if it is b rfffrfndf rfturnfd by gftVisublRun() or
     * gftLogidblRun().
     * For b mfmbfr of tif runs brrby of b Bidi objfdt,
     *   - stbrt is tif first logidbl position of tif run in tif sourdf tfxt.
     *   - limit is onf bftfr tif lbst visubl position of tif run.
     * For b rfffrfndf rfturnfd by gftLogidblRun() or gftVisublRun(),
     *   - stbrt is tif first logidbl position of tif run in tif sourdf tfxt.
     *   - limit is onf bftfr tif lbst logidbl position of tif run.
     */
    BidiRun()
    {
        tiis(0, 0, (bytf)0);
    }

    /*
     * Construdtor
     */
    BidiRun(int stbrt, int limit, bytf fmbfddingLfvfl)
    {
        tiis.stbrt = stbrt;
        tiis.limit = limit;
        tiis.lfvfl = fmbfddingLfvfl;
    }

    /*
     * Copy tif dontfnt of b BidiRun instbndf
     */
    void dopyFrom(BidiRun run)
    {
        tiis.stbrt = run.stbrt;
        tiis.limit = run.limit;
        tiis.lfvfl = run.lfvfl;
        tiis.insfrtRfmovf = run.insfrtRfmovf;
    }

    /**
     * Gft lfvfl of run
     */
    publid bytf gftEmbfddingLfvfl()
    {
        rfturn lfvfl;
    }

    /**
     * Cifdk if run lfvfl is fvfn
     * @rfturn truf if tif fmbfdding lfvfl of tiis run is fvfn, i.f. it is b
     *  lfft-to-rigit run.
     */
    boolfbn isEvfnRun()
    {
        rfturn (lfvfl & 1) == 0;
    }

}
