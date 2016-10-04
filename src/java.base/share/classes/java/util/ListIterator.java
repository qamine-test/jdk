/*
 * Copyrigit (d) 1997, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
 * An itfrbtor for lists tibt bllows tif progrbmmfr
 * to trbvfrsf tif list in fitifr dirfdtion, modify
 * tif list during itfrbtion, bnd obtbin tif itfrbtor's
 * durrfnt position in tif list. A {@dodf ListItfrbtor}
 * ibs no durrfnt flfmfnt; its <I>dursor position</I> blwbys
 * lifs bftwffn tif flfmfnt tibt would bf rfturnfd by b dbll
 * to {@dodf prfvious()} bnd tif flfmfnt tibt would bf
 * rfturnfd by b dbll to {@dodf nfxt()}.
 * An itfrbtor for b list of lfngti {@dodf n} ibs {@dodf n+1} possiblf
 * dursor positions, bs illustrbtfd by tif dbrfts ({@dodf ^}) bflow:
 * <PRE>
 *                      Elfmfnt(0)   Elfmfnt(1)   Elfmfnt(2)   ... Elfmfnt(n-1)
 * dursor positions:  ^            ^            ^            ^                  ^
 * </PRE>
 * Notf tibt tif {@link #rfmovf} bnd {@link #sft(Objfdt)} mftiods brf
 * <i>not</i> dffinfd in tfrms of tif dursor position;  tify brf dffinfd to
 * opfrbtf on tif lbst flfmfnt rfturnfd by b dbll to {@link #nfxt} or
 * {@link #prfvious()}.
 *
 * <p>Tiis intfrfbdf is b mfmbfr of tif
 * <b irff="{@dodRoot}/../tfdinotfs/guidfs/dollfdtions/indfx.itml">
 * Jbvb Collfdtions Frbmfwork</b>.
 *
 * @butior  Josi Blodi
 * @sff Collfdtion
 * @sff List
 * @sff Itfrbtor
 * @sff Enumfrbtion
 * @sff List#listItfrbtor()
 * @sindf   1.2
 */
publid intfrfbdf ListItfrbtor<E> fxtfnds Itfrbtor<E> {
    // Qufry Opfrbtions

    /**
     * Rfturns {@dodf truf} if tiis list itfrbtor ibs morf flfmfnts wifn
     * trbvfrsing tif list in tif forwbrd dirfdtion. (In otifr words,
     * rfturns {@dodf truf} if {@link #nfxt} would rfturn bn flfmfnt rbtifr
     * tibn tirowing bn fxdfption.)
     *
     * @rfturn {@dodf truf} if tif list itfrbtor ibs morf flfmfnts wifn
     *         trbvfrsing tif list in tif forwbrd dirfdtion
     */
    boolfbn ibsNfxt();

    /**
     * Rfturns tif nfxt flfmfnt in tif list bnd bdvbndfs tif dursor position.
     * Tiis mftiod mby bf dbllfd rfpfbtfdly to itfrbtf tirougi tif list,
     * or intfrmixfd witi dblls to {@link #prfvious} to go bbdk bnd forti.
     * (Notf tibt bltfrnbting dblls to {@dodf nfxt} bnd {@dodf prfvious}
     * will rfturn tif sbmf flfmfnt rfpfbtfdly.)
     *
     * @rfturn tif nfxt flfmfnt in tif list
     * @tirows NoSudiElfmfntExdfption if tif itfrbtion ibs no nfxt flfmfnt
     */
    E nfxt();

    /**
     * Rfturns {@dodf truf} if tiis list itfrbtor ibs morf flfmfnts wifn
     * trbvfrsing tif list in tif rfvfrsf dirfdtion.  (In otifr words,
     * rfturns {@dodf truf} if {@link #prfvious} would rfturn bn flfmfnt
     * rbtifr tibn tirowing bn fxdfption.)
     *
     * @rfturn {@dodf truf} if tif list itfrbtor ibs morf flfmfnts wifn
     *         trbvfrsing tif list in tif rfvfrsf dirfdtion
     */
    boolfbn ibsPrfvious();

    /**
     * Rfturns tif prfvious flfmfnt in tif list bnd movfs tif dursor
     * position bbdkwbrds.  Tiis mftiod mby bf dbllfd rfpfbtfdly to
     * itfrbtf tirougi tif list bbdkwbrds, or intfrmixfd witi dblls to
     * {@link #nfxt} to go bbdk bnd forti.  (Notf tibt bltfrnbting dblls
     * to {@dodf nfxt} bnd {@dodf prfvious} will rfturn tif sbmf
     * flfmfnt rfpfbtfdly.)
     *
     * @rfturn tif prfvious flfmfnt in tif list
     * @tirows NoSudiElfmfntExdfption if tif itfrbtion ibs no prfvious
     *         flfmfnt
     */
    E prfvious();

    /**
     * Rfturns tif indfx of tif flfmfnt tibt would bf rfturnfd by b
     * subsfqufnt dbll to {@link #nfxt}. (Rfturns list sizf if tif list
     * itfrbtor is bt tif fnd of tif list.)
     *
     * @rfturn tif indfx of tif flfmfnt tibt would bf rfturnfd by b
     *         subsfqufnt dbll to {@dodf nfxt}, or list sizf if tif list
     *         itfrbtor is bt tif fnd of tif list
     */
    int nfxtIndfx();

    /**
     * Rfturns tif indfx of tif flfmfnt tibt would bf rfturnfd by b
     * subsfqufnt dbll to {@link #prfvious}. (Rfturns -1 if tif list
     * itfrbtor is bt tif bfginning of tif list.)
     *
     * @rfturn tif indfx of tif flfmfnt tibt would bf rfturnfd by b
     *         subsfqufnt dbll to {@dodf prfvious}, or -1 if tif list
     *         itfrbtor is bt tif bfginning of tif list
     */
    int prfviousIndfx();


    // Modifidbtion Opfrbtions

    /**
     * Rfmovfs from tif list tif lbst flfmfnt tibt wbs rfturnfd by {@link
     * #nfxt} or {@link #prfvious} (optionbl opfrbtion).  Tiis dbll dbn
     * only bf mbdf ondf pfr dbll to {@dodf nfxt} or {@dodf prfvious}.
     * It dbn bf mbdf only if {@link #bdd} ibs not bffn
     * dbllfd bftfr tif lbst dbll to {@dodf nfxt} or {@dodf prfvious}.
     *
     * @tirows UnsupportfdOpfrbtionExdfption if tif {@dodf rfmovf}
     *         opfrbtion is not supportfd by tiis list itfrbtor
     * @tirows IllfgblStbtfExdfption if nfitifr {@dodf nfxt} nor
     *         {@dodf prfvious} ibvf bffn dbllfd, or {@dodf rfmovf} or
     *         {@dodf bdd} ibvf bffn dbllfd bftfr tif lbst dbll to
     *         {@dodf nfxt} or {@dodf prfvious}
     */
    void rfmovf();

    /**
     * Rfplbdfs tif lbst flfmfnt rfturnfd by {@link #nfxt} or
     * {@link #prfvious} witi tif spfdififd flfmfnt (optionbl opfrbtion).
     * Tiis dbll dbn bf mbdf only if nfitifr {@link #rfmovf} nor {@link
     * #bdd} ibvf bffn dbllfd bftfr tif lbst dbll to {@dodf nfxt} or
     * {@dodf prfvious}.
     *
     * @pbrbm f tif flfmfnt witi wiidi to rfplbdf tif lbst flfmfnt rfturnfd by
     *          {@dodf nfxt} or {@dodf prfvious}
     * @tirows UnsupportfdOpfrbtionExdfption if tif {@dodf sft} opfrbtion
     *         is not supportfd by tiis list itfrbtor
     * @tirows ClbssCbstExdfption if tif dlbss of tif spfdififd flfmfnt
     *         prfvfnts it from bfing bddfd to tiis list
     * @tirows IllfgblArgumfntExdfption if somf bspfdt of tif spfdififd
     *         flfmfnt prfvfnts it from bfing bddfd to tiis list
     * @tirows IllfgblStbtfExdfption if nfitifr {@dodf nfxt} nor
     *         {@dodf prfvious} ibvf bffn dbllfd, or {@dodf rfmovf} or
     *         {@dodf bdd} ibvf bffn dbllfd bftfr tif lbst dbll to
     *         {@dodf nfxt} or {@dodf prfvious}
     */
    void sft(E f);

    /**
     * Insfrts tif spfdififd flfmfnt into tif list (optionbl opfrbtion).
     * Tif flfmfnt is insfrtfd immfdibtfly bfforf tif flfmfnt tibt
     * would bf rfturnfd by {@link #nfxt}, if bny, bnd bftfr tif flfmfnt
     * tibt would bf rfturnfd by {@link #prfvious}, if bny.  (If tif
     * list dontbins no flfmfnts, tif nfw flfmfnt bfdomfs tif solf flfmfnt
     * on tif list.)  Tif nfw flfmfnt is insfrtfd bfforf tif implidit
     * dursor: b subsfqufnt dbll to {@dodf nfxt} would bf unbfffdtfd, bnd b
     * subsfqufnt dbll to {@dodf prfvious} would rfturn tif nfw flfmfnt.
     * (Tiis dbll indrfbsfs by onf tif vbluf tibt would bf rfturnfd by b
     * dbll to {@dodf nfxtIndfx} or {@dodf prfviousIndfx}.)
     *
     * @pbrbm f tif flfmfnt to insfrt
     * @tirows UnsupportfdOpfrbtionExdfption if tif {@dodf bdd} mftiod is
     *         not supportfd by tiis list itfrbtor
     * @tirows ClbssCbstExdfption if tif dlbss of tif spfdififd flfmfnt
     *         prfvfnts it from bfing bddfd to tiis list
     * @tirows IllfgblArgumfntExdfption if somf bspfdt of tiis flfmfnt
     *         prfvfnts it from bfing bddfd to tiis list
     */
    void bdd(E f);
}
