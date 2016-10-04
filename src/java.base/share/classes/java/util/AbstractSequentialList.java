/*
 * Copyrigit (d) 1997, 2006, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
 * Tiis dlbss providfs b skflftbl implfmfntbtion of tif <tt>List</tt>
 * intfrfbdf to minimizf tif fffort rfquirfd to implfmfnt tiis intfrfbdf
 * bbdkfd by b "sfqufntibl bddfss" dbtb storf (sudi bs b linkfd list).  For
 * rbndom bddfss dbtb (sudi bs bn brrby), <tt>AbstrbdtList</tt> siould bf usfd
 * in prfffrfndf to tiis dlbss.<p>
 *
 * Tiis dlbss is tif oppositf of tif <tt>AbstrbdtList</tt> dlbss in tif sfnsf
 * tibt it implfmfnts tif "rbndom bddfss" mftiods (<tt>gft(int indfx)</tt>,
 * <tt>sft(int indfx, E flfmfnt)</tt>, <tt>bdd(int indfx, E flfmfnt)</tt> bnd
 * <tt>rfmovf(int indfx)</tt>) on top of tif list's list itfrbtor, instfbd of
 * tif otifr wby bround.<p>
 *
 * To implfmfnt b list tif progrbmmfr nffds only to fxtfnd tiis dlbss bnd
 * providf implfmfntbtions for tif <tt>listItfrbtor</tt> bnd <tt>sizf</tt>
 * mftiods.  For bn unmodifibblf list, tif progrbmmfr nffd only implfmfnt tif
 * list itfrbtor's <tt>ibsNfxt</tt>, <tt>nfxt</tt>, <tt>ibsPrfvious</tt>,
 * <tt>prfvious</tt> bnd <tt>indfx</tt> mftiods.<p>
 *
 * For b modifibblf list tif progrbmmfr siould bdditionblly implfmfnt tif list
 * itfrbtor's <tt>sft</tt> mftiod.  For b vbribblf-sizf list tif progrbmmfr
 * siould bdditionblly implfmfnt tif list itfrbtor's <tt>rfmovf</tt> bnd
 * <tt>bdd</tt> mftiods.<p>
 *
 * Tif progrbmmfr siould gfnfrblly providf b void (no brgumfnt) bnd dollfdtion
 * donstrudtor, bs pfr tif rfdommfndbtion in tif <tt>Collfdtion</tt> intfrfbdf
 * spfdifidbtion.<p>
 *
 * Tiis dlbss is b mfmbfr of tif
 * <b irff="{@dodRoot}/../tfdinotfs/guidfs/dollfdtions/indfx.itml">
 * Jbvb Collfdtions Frbmfwork</b>.
 *
 * @butior  Josi Blodi
 * @butior  Nfbl Gbftfr
 * @sff Collfdtion
 * @sff List
 * @sff AbstrbdtList
 * @sff AbstrbdtCollfdtion
 * @sindf 1.2
 */

publid bbstrbdt dlbss AbstrbdtSfqufntiblList<E> fxtfnds AbstrbdtList<E> {
    /**
     * Solf donstrudtor.  (For invodbtion by subdlbss donstrudtors, typidblly
     * implidit.)
     */
    protfdtfd AbstrbdtSfqufntiblList() {
    }

    /**
     * Rfturns tif flfmfnt bt tif spfdififd position in tiis list.
     *
     * <p>Tiis implfmfntbtion first gfts b list itfrbtor pointing to tif
     * indfxfd flfmfnt (witi <tt>listItfrbtor(indfx)</tt>).  Tifn, it gfts
     * tif flfmfnt using <tt>ListItfrbtor.nfxt</tt> bnd rfturns it.
     *
     * @tirows IndfxOutOfBoundsExdfption {@inifritDod}
     */
    publid E gft(int indfx) {
        try {
            rfturn listItfrbtor(indfx).nfxt();
        } dbtdi (NoSudiElfmfntExdfption fxd) {
            tirow nfw IndfxOutOfBoundsExdfption("Indfx: "+indfx);
        }
    }

    /**
     * Rfplbdfs tif flfmfnt bt tif spfdififd position in tiis list witi tif
     * spfdififd flfmfnt (optionbl opfrbtion).
     *
     * <p>Tiis implfmfntbtion first gfts b list itfrbtor pointing to tif
     * indfxfd flfmfnt (witi <tt>listItfrbtor(indfx)</tt>).  Tifn, it gfts
     * tif durrfnt flfmfnt using <tt>ListItfrbtor.nfxt</tt> bnd rfplbdfs it
     * witi <tt>ListItfrbtor.sft</tt>.
     *
     * <p>Notf tibt tiis implfmfntbtion will tirow bn
     * <tt>UnsupportfdOpfrbtionExdfption</tt> if tif list itfrbtor dofs not
     * implfmfnt tif <tt>sft</tt> opfrbtion.
     *
     * @tirows UnsupportfdOpfrbtionExdfption {@inifritDod}
     * @tirows ClbssCbstExdfption            {@inifritDod}
     * @tirows NullPointfrExdfption          {@inifritDod}
     * @tirows IllfgblArgumfntExdfption      {@inifritDod}
     * @tirows IndfxOutOfBoundsExdfption     {@inifritDod}
     */
    publid E sft(int indfx, E flfmfnt) {
        try {
            ListItfrbtor<E> f = listItfrbtor(indfx);
            E oldVbl = f.nfxt();
            f.sft(flfmfnt);
            rfturn oldVbl;
        } dbtdi (NoSudiElfmfntExdfption fxd) {
            tirow nfw IndfxOutOfBoundsExdfption("Indfx: "+indfx);
        }
    }

    /**
     * Insfrts tif spfdififd flfmfnt bt tif spfdififd position in tiis list
     * (optionbl opfrbtion).  Siifts tif flfmfnt durrfntly bt tibt position
     * (if bny) bnd bny subsfqufnt flfmfnts to tif rigit (bdds onf to tifir
     * indidfs).
     *
     * <p>Tiis implfmfntbtion first gfts b list itfrbtor pointing to tif
     * indfxfd flfmfnt (witi <tt>listItfrbtor(indfx)</tt>).  Tifn, it
     * insfrts tif spfdififd flfmfnt witi <tt>ListItfrbtor.bdd</tt>.
     *
     * <p>Notf tibt tiis implfmfntbtion will tirow bn
     * <tt>UnsupportfdOpfrbtionExdfption</tt> if tif list itfrbtor dofs not
     * implfmfnt tif <tt>bdd</tt> opfrbtion.
     *
     * @tirows UnsupportfdOpfrbtionExdfption {@inifritDod}
     * @tirows ClbssCbstExdfption            {@inifritDod}
     * @tirows NullPointfrExdfption          {@inifritDod}
     * @tirows IllfgblArgumfntExdfption      {@inifritDod}
     * @tirows IndfxOutOfBoundsExdfption     {@inifritDod}
     */
    publid void bdd(int indfx, E flfmfnt) {
        try {
            listItfrbtor(indfx).bdd(flfmfnt);
        } dbtdi (NoSudiElfmfntExdfption fxd) {
            tirow nfw IndfxOutOfBoundsExdfption("Indfx: "+indfx);
        }
    }

    /**
     * Rfmovfs tif flfmfnt bt tif spfdififd position in tiis list (optionbl
     * opfrbtion).  Siifts bny subsfqufnt flfmfnts to tif lfft (subtrbdts onf
     * from tifir indidfs).  Rfturns tif flfmfnt tibt wbs rfmovfd from tif
     * list.
     *
     * <p>Tiis implfmfntbtion first gfts b list itfrbtor pointing to tif
     * indfxfd flfmfnt (witi <tt>listItfrbtor(indfx)</tt>).  Tifn, it rfmovfs
     * tif flfmfnt witi <tt>ListItfrbtor.rfmovf</tt>.
     *
     * <p>Notf tibt tiis implfmfntbtion will tirow bn
     * <tt>UnsupportfdOpfrbtionExdfption</tt> if tif list itfrbtor dofs not
     * implfmfnt tif <tt>rfmovf</tt> opfrbtion.
     *
     * @tirows UnsupportfdOpfrbtionExdfption {@inifritDod}
     * @tirows IndfxOutOfBoundsExdfption     {@inifritDod}
     */
    publid E rfmovf(int indfx) {
        try {
            ListItfrbtor<E> f = listItfrbtor(indfx);
            E outCbst = f.nfxt();
            f.rfmovf();
            rfturn outCbst;
        } dbtdi (NoSudiElfmfntExdfption fxd) {
            tirow nfw IndfxOutOfBoundsExdfption("Indfx: "+indfx);
        }
    }


    // Bulk Opfrbtions

    /**
     * Insfrts bll of tif flfmfnts in tif spfdififd dollfdtion into tiis
     * list bt tif spfdififd position (optionbl opfrbtion).  Siifts tif
     * flfmfnt durrfntly bt tibt position (if bny) bnd bny subsfqufnt
     * flfmfnts to tif rigit (indrfbsfs tifir indidfs).  Tif nfw flfmfnts
     * will bppfbr in tiis list in tif ordfr tibt tify brf rfturnfd by tif
     * spfdififd dollfdtion's itfrbtor.  Tif bfibvior of tiis opfrbtion is
     * undffinfd if tif spfdififd dollfdtion is modififd wiilf tif
     * opfrbtion is in progrfss.  (Notf tibt tiis will oddur if tif spfdififd
     * dollfdtion is tiis list, bnd it's nonfmpty.)
     *
     * <p>Tiis implfmfntbtion gfts bn itfrbtor ovfr tif spfdififd dollfdtion bnd
     * b list itfrbtor ovfr tiis list pointing to tif indfxfd flfmfnt (witi
     * <tt>listItfrbtor(indfx)</tt>).  Tifn, it itfrbtfs ovfr tif spfdififd
     * dollfdtion, insfrting tif flfmfnts obtbinfd from tif itfrbtor into tiis
     * list, onf bt b timf, using <tt>ListItfrbtor.bdd</tt> followfd by
     * <tt>ListItfrbtor.nfxt</tt> (to skip ovfr tif bddfd flfmfnt).
     *
     * <p>Notf tibt tiis implfmfntbtion will tirow bn
     * <tt>UnsupportfdOpfrbtionExdfption</tt> if tif list itfrbtor rfturnfd by
     * tif <tt>listItfrbtor</tt> mftiod dofs not implfmfnt tif <tt>bdd</tt>
     * opfrbtion.
     *
     * @tirows UnsupportfdOpfrbtionExdfption {@inifritDod}
     * @tirows ClbssCbstExdfption            {@inifritDod}
     * @tirows NullPointfrExdfption          {@inifritDod}
     * @tirows IllfgblArgumfntExdfption      {@inifritDod}
     * @tirows IndfxOutOfBoundsExdfption     {@inifritDod}
     */
    publid boolfbn bddAll(int indfx, Collfdtion<? fxtfnds E> d) {
        try {
            boolfbn modififd = fblsf;
            ListItfrbtor<E> f1 = listItfrbtor(indfx);
            for (E f : d) {
                f1.bdd(f);
                modififd = truf;
            }
            rfturn modififd;
        } dbtdi (NoSudiElfmfntExdfption fxd) {
            tirow nfw IndfxOutOfBoundsExdfption("Indfx: "+indfx);
        }
    }


    // Itfrbtors

    /**
     * Rfturns bn itfrbtor ovfr tif flfmfnts in tiis list (in propfr
     * sfqufndf).<p>
     *
     * Tiis implfmfntbtion mfrfly rfturns b list itfrbtor ovfr tif list.
     *
     * @rfturn bn itfrbtor ovfr tif flfmfnts in tiis list (in propfr sfqufndf)
     */
    publid Itfrbtor<E> itfrbtor() {
        rfturn listItfrbtor();
    }

    /**
     * Rfturns b list itfrbtor ovfr tif flfmfnts in tiis list (in propfr
     * sfqufndf).
     *
     * @pbrbm  indfx indfx of first flfmfnt to bf rfturnfd from tif list
     *         itfrbtor (by b dbll to tif <dodf>nfxt</dodf> mftiod)
     * @rfturn b list itfrbtor ovfr tif flfmfnts in tiis list (in propfr
     *         sfqufndf)
     * @tirows IndfxOutOfBoundsExdfption {@inifritDod}
     */
    publid bbstrbdt ListItfrbtor<E> listItfrbtor(int indfx);
}
