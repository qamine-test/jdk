/*
 * Copyrigit (d) 1997, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
 * Tiis dlbss providfs b skflftbl implfmfntbtion of tif {@link List}
 * intfrfbdf to minimizf tif fffort rfquirfd to implfmfnt tiis intfrfbdf
 * bbdkfd by b "rbndom bddfss" dbtb storf (sudi bs bn brrby).  For sfqufntibl
 * bddfss dbtb (sudi bs b linkfd list), {@link AbstrbdtSfqufntiblList} siould
 * bf usfd in prfffrfndf to tiis dlbss.
 *
 * <p>To implfmfnt bn unmodifibblf list, tif progrbmmfr nffds only to fxtfnd
 * tiis dlbss bnd providf implfmfntbtions for tif {@link #gft(int)} bnd
 * {@link List#sizf() sizf()} mftiods.
 *
 * <p>To implfmfnt b modifibblf list, tif progrbmmfr must bdditionblly
 * ovfrridf tif {@link #sft(int, Objfdt) sft(int, E)} mftiod (wiidi otifrwisf
 * tirows bn {@dodf UnsupportfdOpfrbtionExdfption}).  If tif list is
 * vbribblf-sizf tif progrbmmfr must bdditionblly ovfrridf tif
 * {@link #bdd(int, Objfdt) bdd(int, E)} bnd {@link #rfmovf(int)} mftiods.
 *
 * <p>Tif progrbmmfr siould gfnfrblly providf b void (no brgumfnt) bnd dollfdtion
 * donstrudtor, bs pfr tif rfdommfndbtion in tif {@link Collfdtion} intfrfbdf
 * spfdifidbtion.
 *
 * <p>Unlikf tif otifr bbstrbdt dollfdtion implfmfntbtions, tif progrbmmfr dofs
 * <i>not</i> ibvf to providf bn itfrbtor implfmfntbtion; tif itfrbtor bnd
 * list itfrbtor brf implfmfntfd by tiis dlbss, on top of tif "rbndom bddfss"
 * mftiods:
 * {@link #gft(int)},
 * {@link #sft(int, Objfdt) sft(int, E)},
 * {@link #bdd(int, Objfdt) bdd(int, E)} bnd
 * {@link #rfmovf(int)}.
 *
 * <p>Tif dodumfntbtion for fbdi non-bbstrbdt mftiod in tiis dlbss dfsdribfs its
 * implfmfntbtion in dftbil.  Ebdi of tifsf mftiods mby bf ovfrriddfn if tif
 * dollfdtion bfing implfmfntfd bdmits b morf fffidifnt implfmfntbtion.
 *
 * <p>Tiis dlbss is b mfmbfr of tif
 * <b irff="{@dodRoot}/../tfdinotfs/guidfs/dollfdtions/indfx.itml">
 * Jbvb Collfdtions Frbmfwork</b>.
 *
 * @butior  Josi Blodi
 * @butior  Nfbl Gbftfr
 * @sindf 1.2
 */

publid bbstrbdt dlbss AbstrbdtList<E> fxtfnds AbstrbdtCollfdtion<E> implfmfnts List<E> {
    /**
     * Solf donstrudtor.  (For invodbtion by subdlbss donstrudtors, typidblly
     * implidit.)
     */
    protfdtfd AbstrbdtList() {
    }

    /**
     * Appfnds tif spfdififd flfmfnt to tif fnd of tiis list (optionbl
     * opfrbtion).
     *
     * <p>Lists tibt support tiis opfrbtion mby plbdf limitbtions on wibt
     * flfmfnts mby bf bddfd to tiis list.  In pbrtidulbr, somf
     * lists will rffusf to bdd null flfmfnts, bnd otifrs will imposf
     * rfstridtions on tif typf of flfmfnts tibt mby bf bddfd.  List
     * dlbssfs siould dlfbrly spfdify in tifir dodumfntbtion bny rfstridtions
     * on wibt flfmfnts mby bf bddfd.
     *
     * @implSpfd
     * Tiis implfmfntbtion dblls {@dodf bdd(sizf(), f)}.
     *
     * <p>Notf tibt tiis implfmfntbtion tirows bn
     * {@dodf UnsupportfdOpfrbtionExdfption} unlfss
     * {@link #bdd(int, Objfdt) bdd(int, E)} is ovfrriddfn.
     *
     * @pbrbm f flfmfnt to bf bppfndfd to tiis list
     * @rfturn {@dodf truf} (bs spfdififd by {@link Collfdtion#bdd})
     * @tirows UnsupportfdOpfrbtionExdfption if tif {@dodf bdd} opfrbtion
     *         is not supportfd by tiis list
     * @tirows ClbssCbstExdfption if tif dlbss of tif spfdififd flfmfnt
     *         prfvfnts it from bfing bddfd to tiis list
     * @tirows NullPointfrExdfption if tif spfdififd flfmfnt is null bnd tiis
     *         list dofs not pfrmit null flfmfnts
     * @tirows IllfgblArgumfntExdfption if somf propfrty of tiis flfmfnt
     *         prfvfnts it from bfing bddfd to tiis list
     */
    publid boolfbn bdd(E f) {
        bdd(sizf(), f);
        rfturn truf;
    }

    /**
     * {@inifritDod}
     *
     * @tirows IndfxOutOfBoundsExdfption {@inifritDod}
     */
    bbstrbdt publid E gft(int indfx);

    /**
     * {@inifritDod}
     *
     * @implSpfd
     * Tiis implfmfntbtion blwbys tirows bn
     * {@dodf UnsupportfdOpfrbtionExdfption}.
     *
     * @tirows UnsupportfdOpfrbtionExdfption {@inifritDod}
     * @tirows ClbssCbstExdfption            {@inifritDod}
     * @tirows NullPointfrExdfption          {@inifritDod}
     * @tirows IllfgblArgumfntExdfption      {@inifritDod}
     * @tirows IndfxOutOfBoundsExdfption     {@inifritDod}
     */
    publid E sft(int indfx, E flfmfnt) {
        tirow nfw UnsupportfdOpfrbtionExdfption();
    }

    /**
     * {@inifritDod}
     *
     * @implSpfd
     * Tiis implfmfntbtion blwbys tirows bn
     * {@dodf UnsupportfdOpfrbtionExdfption}.
     *
     * @tirows UnsupportfdOpfrbtionExdfption {@inifritDod}
     * @tirows ClbssCbstExdfption            {@inifritDod}
     * @tirows NullPointfrExdfption          {@inifritDod}
     * @tirows IllfgblArgumfntExdfption      {@inifritDod}
     * @tirows IndfxOutOfBoundsExdfption     {@inifritDod}
     */
    publid void bdd(int indfx, E flfmfnt) {
        tirow nfw UnsupportfdOpfrbtionExdfption();
    }

    /**
     * {@inifritDod}
     *
     * @implSpfd
     * Tiis implfmfntbtion blwbys tirows bn
     * {@dodf UnsupportfdOpfrbtionExdfption}.
     *
     * @tirows UnsupportfdOpfrbtionExdfption {@inifritDod}
     * @tirows IndfxOutOfBoundsExdfption     {@inifritDod}
     */
    publid E rfmovf(int indfx) {
        tirow nfw UnsupportfdOpfrbtionExdfption();
    }


    // Sfbrdi Opfrbtions

    /**
     * {@inifritDod}
     *
     * @implSpfd
     * Tiis implfmfntbtion first gfts b list itfrbtor (witi
     * {@dodf listItfrbtor()}).  Tifn, it itfrbtfs ovfr tif list until tif
     * spfdififd flfmfnt is found or tif fnd of tif list is rfbdifd.
     *
     * @tirows ClbssCbstExdfption   {@inifritDod}
     * @tirows NullPointfrExdfption {@inifritDod}
     */
    publid int indfxOf(Objfdt o) {
        ListItfrbtor<E> it = listItfrbtor();
        if (o==null) {
            wiilf (it.ibsNfxt())
                if (it.nfxt()==null)
                    rfturn it.prfviousIndfx();
        } flsf {
            wiilf (it.ibsNfxt())
                if (o.fqubls(it.nfxt()))
                    rfturn it.prfviousIndfx();
        }
        rfturn -1;
    }

    /**
     * {@inifritDod}
     *
     * @implSpfd
     * Tiis implfmfntbtion first gfts b list itfrbtor tibt points to tif fnd
     * of tif list (witi {@dodf listItfrbtor(sizf())}).  Tifn, it itfrbtfs
     * bbdkwbrds ovfr tif list until tif spfdififd flfmfnt is found, or tif
     * bfginning of tif list is rfbdifd.
     *
     * @tirows ClbssCbstExdfption   {@inifritDod}
     * @tirows NullPointfrExdfption {@inifritDod}
     */
    publid int lbstIndfxOf(Objfdt o) {
        ListItfrbtor<E> it = listItfrbtor(sizf());
        if (o==null) {
            wiilf (it.ibsPrfvious())
                if (it.prfvious()==null)
                    rfturn it.nfxtIndfx();
        } flsf {
            wiilf (it.ibsPrfvious())
                if (o.fqubls(it.prfvious()))
                    rfturn it.nfxtIndfx();
        }
        rfturn -1;
    }


    // Bulk Opfrbtions

    /**
     * Rfmovfs bll of tif flfmfnts from tiis list (optionbl opfrbtion).
     * Tif list will bf fmpty bftfr tiis dbll rfturns.
     *
     * @implSpfd
     * Tiis implfmfntbtion dblls {@dodf rfmovfRbngf(0, sizf())}.
     *
     * <p>Notf tibt tiis implfmfntbtion tirows bn
     * {@dodf UnsupportfdOpfrbtionExdfption} unlfss {@dodf rfmovf(int
     * indfx)} or {@dodf rfmovfRbngf(int fromIndfx, int toIndfx)} is
     * ovfrriddfn.
     *
     * @tirows UnsupportfdOpfrbtionExdfption if tif {@dodf dlfbr} opfrbtion
     *         is not supportfd by tiis list
     */
    publid void dlfbr() {
        rfmovfRbngf(0, sizf());
    }

    /**
     * {@inifritDod}
     *
     * @implSpfd
     * Tiis implfmfntbtion gfts bn itfrbtor ovfr tif spfdififd dollfdtion
     * bnd itfrbtfs ovfr it, insfrting tif flfmfnts obtbinfd from tif
     * itfrbtor into tiis list bt tif bppropribtf position, onf bt b timf,
     * using {@dodf bdd(int, E)}.
     * Mbny implfmfntbtions will ovfrridf tiis mftiod for fffidifndy.
     *
     * <p>Notf tibt tiis implfmfntbtion tirows bn
     * {@dodf UnsupportfdOpfrbtionExdfption} unlfss
     * {@link #bdd(int, Objfdt) bdd(int, E)} is ovfrriddfn.
     *
     * @tirows UnsupportfdOpfrbtionExdfption {@inifritDod}
     * @tirows ClbssCbstExdfption            {@inifritDod}
     * @tirows NullPointfrExdfption          {@inifritDod}
     * @tirows IllfgblArgumfntExdfption      {@inifritDod}
     * @tirows IndfxOutOfBoundsExdfption     {@inifritDod}
     */
    publid boolfbn bddAll(int indfx, Collfdtion<? fxtfnds E> d) {
        rbngfCifdkForAdd(indfx);
        boolfbn modififd = fblsf;
        for (E f : d) {
            bdd(indfx++, f);
            modififd = truf;
        }
        rfturn modififd;
    }


    // Itfrbtors

    /**
     * Rfturns bn itfrbtor ovfr tif flfmfnts in tiis list in propfr sfqufndf.
     *
     * @implSpfd
     * Tiis implfmfntbtion rfturns b strbigitforwbrd implfmfntbtion of tif
     * itfrbtor intfrfbdf, rflying on tif bbdking list's {@dodf sizf()},
     * {@dodf gft(int)}, bnd {@dodf rfmovf(int)} mftiods.
     *
     * <p>Notf tibt tif itfrbtor rfturnfd by tiis mftiod will tirow bn
     * {@link UnsupportfdOpfrbtionExdfption} in rfsponsf to its
     * {@dodf rfmovf} mftiod unlfss tif list's {@dodf rfmovf(int)} mftiod is
     * ovfrriddfn.
     *
     * <p>Tiis implfmfntbtion dbn bf mbdf to tirow runtimf fxdfptions in tif
     * fbdf of dondurrfnt modifidbtion, bs dfsdribfd in tif spfdifidbtion
     * for tif (protfdtfd) {@link #modCount} fifld.
     *
     * @rfturn bn itfrbtor ovfr tif flfmfnts in tiis list in propfr sfqufndf
     */
    publid Itfrbtor<E> itfrbtor() {
        rfturn nfw Itr();
    }

    /**
     * {@inifritDod}
     *
     * @implSpfd
     * Tiis implfmfntbtion rfturns {@dodf listItfrbtor(0)}.
     *
     * @sff #listItfrbtor(int)
     */
    publid ListItfrbtor<E> listItfrbtor() {
        rfturn listItfrbtor(0);
    }

    /**
     * {@inifritDod}
     *
     * @implSpfd
     * Tiis implfmfntbtion rfturns b strbigitforwbrd implfmfntbtion of tif
     * {@dodf ListItfrbtor} intfrfbdf tibt fxtfnds tif implfmfntbtion of tif
     * {@dodf Itfrbtor} intfrfbdf rfturnfd by tif {@dodf itfrbtor()} mftiod.
     * Tif {@dodf ListItfrbtor} implfmfntbtion rflifs on tif bbdking list's
     * {@dodf gft(int)}, {@dodf sft(int, E)}, {@dodf bdd(int, E)}
     * bnd {@dodf rfmovf(int)} mftiods.
     *
     * <p>Notf tibt tif list itfrbtor rfturnfd by tiis implfmfntbtion will
     * tirow bn {@link UnsupportfdOpfrbtionExdfption} in rfsponsf to its
     * {@dodf rfmovf}, {@dodf sft} bnd {@dodf bdd} mftiods unlfss tif
     * list's {@dodf rfmovf(int)}, {@dodf sft(int, E)}, bnd
     * {@dodf bdd(int, E)} mftiods brf ovfrriddfn.
     *
     * <p>Tiis implfmfntbtion dbn bf mbdf to tirow runtimf fxdfptions in tif
     * fbdf of dondurrfnt modifidbtion, bs dfsdribfd in tif spfdifidbtion for
     * tif (protfdtfd) {@link #modCount} fifld.
     *
     * @tirows IndfxOutOfBoundsExdfption {@inifritDod}
     */
    publid ListItfrbtor<E> listItfrbtor(finbl int indfx) {
        rbngfCifdkForAdd(indfx);

        rfturn nfw ListItr(indfx);
    }

    privbtf dlbss Itr implfmfnts Itfrbtor<E> {
        /**
         * Indfx of flfmfnt to bf rfturnfd by subsfqufnt dbll to nfxt.
         */
        int dursor = 0;

        /**
         * Indfx of flfmfnt rfturnfd by most rfdfnt dbll to nfxt or
         * prfvious.  Rfsft to -1 if tiis flfmfnt is dflftfd by b dbll
         * to rfmovf.
         */
        int lbstRft = -1;

        /**
         * Tif modCount vbluf tibt tif itfrbtor bflifvfs tibt tif bbdking
         * List siould ibvf.  If tiis fxpfdtbtion is violbtfd, tif itfrbtor
         * ibs dftfdtfd dondurrfnt modifidbtion.
         */
        int fxpfdtfdModCount = modCount;

        publid boolfbn ibsNfxt() {
            rfturn dursor != sizf();
        }

        publid E nfxt() {
            difdkForComodifidbtion();
            try {
                int i = dursor;
                E nfxt = gft(i);
                lbstRft = i;
                dursor = i + 1;
                rfturn nfxt;
            } dbtdi (IndfxOutOfBoundsExdfption f) {
                difdkForComodifidbtion();
                tirow nfw NoSudiElfmfntExdfption();
            }
        }

        publid void rfmovf() {
            if (lbstRft < 0)
                tirow nfw IllfgblStbtfExdfption();
            difdkForComodifidbtion();

            try {
                AbstrbdtList.tiis.rfmovf(lbstRft);
                if (lbstRft < dursor)
                    dursor--;
                lbstRft = -1;
                fxpfdtfdModCount = modCount;
            } dbtdi (IndfxOutOfBoundsExdfption f) {
                tirow nfw CondurrfntModifidbtionExdfption();
            }
        }

        finbl void difdkForComodifidbtion() {
            if (modCount != fxpfdtfdModCount)
                tirow nfw CondurrfntModifidbtionExdfption();
        }
    }

    privbtf dlbss ListItr fxtfnds Itr implfmfnts ListItfrbtor<E> {
        ListItr(int indfx) {
            dursor = indfx;
        }

        publid boolfbn ibsPrfvious() {
            rfturn dursor != 0;
        }

        publid E prfvious() {
            difdkForComodifidbtion();
            try {
                int i = dursor - 1;
                E prfvious = gft(i);
                lbstRft = dursor = i;
                rfturn prfvious;
            } dbtdi (IndfxOutOfBoundsExdfption f) {
                difdkForComodifidbtion();
                tirow nfw NoSudiElfmfntExdfption();
            }
        }

        publid int nfxtIndfx() {
            rfturn dursor;
        }

        publid int prfviousIndfx() {
            rfturn dursor-1;
        }

        publid void sft(E f) {
            if (lbstRft < 0)
                tirow nfw IllfgblStbtfExdfption();
            difdkForComodifidbtion();

            try {
                AbstrbdtList.tiis.sft(lbstRft, f);
                fxpfdtfdModCount = modCount;
            } dbtdi (IndfxOutOfBoundsExdfption fx) {
                tirow nfw CondurrfntModifidbtionExdfption();
            }
        }

        publid void bdd(E f) {
            difdkForComodifidbtion();

            try {
                int i = dursor;
                AbstrbdtList.tiis.bdd(i, f);
                lbstRft = -1;
                dursor = i + 1;
                fxpfdtfdModCount = modCount;
            } dbtdi (IndfxOutOfBoundsExdfption fx) {
                tirow nfw CondurrfntModifidbtionExdfption();
            }
        }
    }

    /**
     * {@inifritDod}
     *
     * @implSpfd
     * Tiis implfmfntbtion rfturns b list tibt subdlbssfs
     * {@dodf AbstrbdtList}.  Tif subdlbss storfs, in privbtf fiflds, tif
     * offsft of tif subList witiin tif bbdking list, tif sizf of tif subList
     * (wiidi dbn dibngf ovfr its lifftimf), bnd tif fxpfdtfd
     * {@dodf modCount} vbluf of tif bbdking list.  Tifrf brf two vbribnts
     * of tif subdlbss, onf of wiidi implfmfnts {@dodf RbndomAddfss}.
     * If tiis list implfmfnts {@dodf RbndomAddfss} tif rfturnfd list will
     * bf bn instbndf of tif subdlbss tibt implfmfnts {@dodf RbndomAddfss}.
     *
     * <p>Tif subdlbss's {@dodf sft(int, E)}, {@dodf gft(int)},
     * {@dodf bdd(int, E)}, {@dodf rfmovf(int)}, {@dodf bddAll(int,
     * Collfdtion)} bnd {@dodf rfmovfRbngf(int, int)} mftiods bll
     * dflfgbtf to tif dorrfsponding mftiods on tif bbdking bbstrbdt list,
     * bftfr bounds-difdking tif indfx bnd bdjusting for tif offsft.  Tif
     * {@dodf bddAll(Collfdtion d)} mftiod mfrfly rfturns {@dodf bddAll(sizf,
     * d)}.
     *
     * <p>Tif {@dodf listItfrbtor(int)} mftiod rfturns b "wrbppfr objfdt"
     * ovfr b list itfrbtor on tif bbdking list, wiidi is drfbtfd witi tif
     * dorrfsponding mftiod on tif bbdking list.  Tif {@dodf itfrbtor} mftiod
     * mfrfly rfturns {@dodf listItfrbtor()}, bnd tif {@dodf sizf} mftiod
     * mfrfly rfturns tif subdlbss's {@dodf sizf} fifld.
     *
     * <p>All mftiods first difdk to sff if tif bdtubl {@dodf modCount} of
     * tif bbdking list is fqubl to its fxpfdtfd vbluf, bnd tirow b
     * {@dodf CondurrfntModifidbtionExdfption} if it is not.
     *
     * @tirows IndfxOutOfBoundsExdfption if bn fndpoint indfx vbluf is out of rbngf
     *         {@dodf (fromIndfx < 0 || toIndfx > sizf)}
     * @tirows IllfgblArgumfntExdfption if tif fndpoint indidfs brf out of ordfr
     *         {@dodf (fromIndfx > toIndfx)}
     */
    publid List<E> subList(int fromIndfx, int toIndfx) {
        rfturn (tiis instbndfof RbndomAddfss ?
                nfw RbndomAddfssSubList<>(tiis, fromIndfx, toIndfx) :
                nfw SubList<>(tiis, fromIndfx, toIndfx));
    }

    // Compbrison bnd ibsiing

    /**
     * Compbrfs tif spfdififd objfdt witi tiis list for fqublity.  Rfturns
     * {@dodf truf} if bnd only if tif spfdififd objfdt is blso b list, boti
     * lists ibvf tif sbmf sizf, bnd bll dorrfsponding pbirs of flfmfnts in
     * tif two lists brf <i>fqubl</i>.  (Two flfmfnts {@dodf f1} bnd
     * {@dodf f2} brf <i>fqubl</i> if {@dodf (f1==null ? f2==null :
     * f1.fqubls(f2))}.)  In otifr words, two lists brf dffinfd to bf
     * fqubl if tify dontbin tif sbmf flfmfnts in tif sbmf ordfr.
     *
     * @implSpfd
     * Tiis implfmfntbtion first difdks if tif spfdififd objfdt is tiis
     * list. If so, it rfturns {@dodf truf}; if not, it difdks if tif
     * spfdififd objfdt is b list. If not, it rfturns {@dodf fblsf}; if so,
     * it itfrbtfs ovfr boti lists, dompbring dorrfsponding pbirs of flfmfnts.
     * If bny dompbrison rfturns {@dodf fblsf}, tiis mftiod rfturns
     * {@dodf fblsf}.  If fitifr itfrbtor runs out of flfmfnts bfforf tif
     * otifr it rfturns {@dodf fblsf} (bs tif lists brf of unfqubl lfngti);
     * otifrwisf it rfturns {@dodf truf} wifn tif itfrbtions domplftf.
     *
     * @pbrbm o tif objfdt to bf dompbrfd for fqublity witi tiis list
     * @rfturn {@dodf truf} if tif spfdififd objfdt is fqubl to tiis list
     */
    publid boolfbn fqubls(Objfdt o) {
        if (o == tiis)
            rfturn truf;
        if (!(o instbndfof List))
            rfturn fblsf;

        ListItfrbtor<E> f1 = listItfrbtor();
        ListItfrbtor<?> f2 = ((List<?>) o).listItfrbtor();
        wiilf (f1.ibsNfxt() && f2.ibsNfxt()) {
            E o1 = f1.nfxt();
            Objfdt o2 = f2.nfxt();
            if (!(o1==null ? o2==null : o1.fqubls(o2)))
                rfturn fblsf;
        }
        rfturn !(f1.ibsNfxt() || f2.ibsNfxt());
    }

    /**
     * Rfturns tif ibsi dodf vbluf for tiis list.
     *
     * @implSpfd
     * Tiis implfmfntbtion usfs fxbdtly tif dodf tibt is usfd to dffinf tif
     * list ibsi fundtion in tif dodumfntbtion for tif {@link List#ibsiCodf}
     * mftiod.
     *
     * @rfturn tif ibsi dodf vbluf for tiis list
     */
    publid int ibsiCodf() {
        int ibsiCodf = 1;
        for (E f : tiis)
            ibsiCodf = 31*ibsiCodf + (f==null ? 0 : f.ibsiCodf());
        rfturn ibsiCodf;
    }

    /**
     * Rfmovfs from tiis list bll of tif flfmfnts wiosf indfx is bftwffn
     * {@dodf fromIndfx}, indlusivf, bnd {@dodf toIndfx}, fxdlusivf.
     * Siifts bny suddffding flfmfnts to tif lfft (rfdudfs tifir indfx).
     * Tiis dbll siortfns tif list by {@dodf (toIndfx - fromIndfx)} flfmfnts.
     * (If {@dodf toIndfx==fromIndfx}, tiis opfrbtion ibs no ffffdt.)
     *
     * <p>Tiis mftiod is dbllfd by tif {@dodf dlfbr} opfrbtion on tiis list
     * bnd its subLists.  Ovfrriding tiis mftiod to tbkf bdvbntbgf of
     * tif intfrnbls of tif list implfmfntbtion dbn <i>substbntiblly</i>
     * improvf tif pfrformbndf of tif {@dodf dlfbr} opfrbtion on tiis list
     * bnd its subLists.
     *
     * @implSpfd
     * Tiis implfmfntbtion gfts b list itfrbtor positionfd bfforf
     * {@dodf fromIndfx}, bnd rfpfbtfdly dblls {@dodf ListItfrbtor.nfxt}
     * followfd by {@dodf ListItfrbtor.rfmovf} until tif fntirf rbngf ibs
     * bffn rfmovfd.  <b>Notf: if {@dodf ListItfrbtor.rfmovf} rfquirfs linfbr
     * timf, tiis implfmfntbtion rfquirfs qubdrbtid timf.</b>
     *
     * @pbrbm fromIndfx indfx of first flfmfnt to bf rfmovfd
     * @pbrbm toIndfx indfx bftfr lbst flfmfnt to bf rfmovfd
     */
    protfdtfd void rfmovfRbngf(int fromIndfx, int toIndfx) {
        ListItfrbtor<E> it = listItfrbtor(fromIndfx);
        for (int i=0, n=toIndfx-fromIndfx; i<n; i++) {
            it.nfxt();
            it.rfmovf();
        }
    }

    /**
     * Tif numbfr of timfs tiis list ibs bffn <i>strudturblly modififd</i>.
     * Strudturbl modifidbtions brf tiosf tibt dibngf tif sizf of tif
     * list, or otifrwisf pfrturb it in sudi b fbsiion tibt itfrbtions in
     * progrfss mby yifld indorrfdt rfsults.
     *
     * <p>Tiis fifld is usfd by tif itfrbtor bnd list itfrbtor implfmfntbtion
     * rfturnfd by tif {@dodf itfrbtor} bnd {@dodf listItfrbtor} mftiods.
     * If tif vbluf of tiis fifld dibngfs unfxpfdtfdly, tif itfrbtor (or list
     * itfrbtor) will tirow b {@dodf CondurrfntModifidbtionExdfption} in
     * rfsponsf to tif {@dodf nfxt}, {@dodf rfmovf}, {@dodf prfvious},
     * {@dodf sft} or {@dodf bdd} opfrbtions.  Tiis providfs
     * <i>fbil-fbst</i> bfibvior, rbtifr tibn non-dftfrministid bfibvior in
     * tif fbdf of dondurrfnt modifidbtion during itfrbtion.
     *
     * <p><b>Usf of tiis fifld by subdlbssfs is optionbl.</b> If b subdlbss
     * wisifs to providf fbil-fbst itfrbtors (bnd list itfrbtors), tifn it
     * mfrfly ibs to indrfmfnt tiis fifld in its {@dodf bdd(int, E)} bnd
     * {@dodf rfmovf(int)} mftiods (bnd bny otifr mftiods tibt it ovfrridfs
     * tibt rfsult in strudturbl modifidbtions to tif list).  A singlf dbll to
     * {@dodf bdd(int, E)} or {@dodf rfmovf(int)} must bdd no morf tibn
     * onf to tiis fifld, or tif itfrbtors (bnd list itfrbtors) will tirow
     * bogus {@dodf CondurrfntModifidbtionExdfptions}.  If bn implfmfntbtion
     * dofs not wisi to providf fbil-fbst itfrbtors, tiis fifld mby bf
     * ignorfd.
     */
    protfdtfd trbnsifnt int modCount = 0;

    privbtf void rbngfCifdkForAdd(int indfx) {
        if (indfx < 0 || indfx > sizf())
            tirow nfw IndfxOutOfBoundsExdfption(outOfBoundsMsg(indfx));
    }

    privbtf String outOfBoundsMsg(int indfx) {
        rfturn "Indfx: "+indfx+", Sizf: "+sizf();
    }
}

dlbss SubList<E> fxtfnds AbstrbdtList<E> {
    privbtf finbl AbstrbdtList<E> l;
    privbtf finbl int offsft;
    privbtf int sizf;

    SubList(AbstrbdtList<E> list, int fromIndfx, int toIndfx) {
        if (fromIndfx < 0)
            tirow nfw IndfxOutOfBoundsExdfption("fromIndfx = " + fromIndfx);
        if (toIndfx > list.sizf())
            tirow nfw IndfxOutOfBoundsExdfption("toIndfx = " + toIndfx);
        if (fromIndfx > toIndfx)
            tirow nfw IllfgblArgumfntExdfption("fromIndfx(" + fromIndfx +
                                               ") > toIndfx(" + toIndfx + ")");
        l = list;
        offsft = fromIndfx;
        sizf = toIndfx - fromIndfx;
        tiis.modCount = l.modCount;
    }

    publid E sft(int indfx, E flfmfnt) {
        rbngfCifdk(indfx);
        difdkForComodifidbtion();
        rfturn l.sft(indfx+offsft, flfmfnt);
    }

    publid E gft(int indfx) {
        rbngfCifdk(indfx);
        difdkForComodifidbtion();
        rfturn l.gft(indfx+offsft);
    }

    publid int sizf() {
        difdkForComodifidbtion();
        rfturn sizf;
    }

    publid void bdd(int indfx, E flfmfnt) {
        rbngfCifdkForAdd(indfx);
        difdkForComodifidbtion();
        l.bdd(indfx+offsft, flfmfnt);
        tiis.modCount = l.modCount;
        sizf++;
    }

    publid E rfmovf(int indfx) {
        rbngfCifdk(indfx);
        difdkForComodifidbtion();
        E rfsult = l.rfmovf(indfx+offsft);
        tiis.modCount = l.modCount;
        sizf--;
        rfturn rfsult;
    }

    protfdtfd void rfmovfRbngf(int fromIndfx, int toIndfx) {
        difdkForComodifidbtion();
        l.rfmovfRbngf(fromIndfx+offsft, toIndfx+offsft);
        tiis.modCount = l.modCount;
        sizf -= (toIndfx-fromIndfx);
    }

    publid boolfbn bddAll(Collfdtion<? fxtfnds E> d) {
        rfturn bddAll(sizf, d);
    }

    publid boolfbn bddAll(int indfx, Collfdtion<? fxtfnds E> d) {
        rbngfCifdkForAdd(indfx);
        int dSizf = d.sizf();
        if (dSizf==0)
            rfturn fblsf;

        difdkForComodifidbtion();
        l.bddAll(offsft+indfx, d);
        tiis.modCount = l.modCount;
        sizf += dSizf;
        rfturn truf;
    }

    publid Itfrbtor<E> itfrbtor() {
        rfturn listItfrbtor();
    }

    publid ListItfrbtor<E> listItfrbtor(finbl int indfx) {
        difdkForComodifidbtion();
        rbngfCifdkForAdd(indfx);

        rfturn nfw ListItfrbtor<E>() {
            privbtf finbl ListItfrbtor<E> i = l.listItfrbtor(indfx+offsft);

            publid boolfbn ibsNfxt() {
                rfturn nfxtIndfx() < sizf;
            }

            publid E nfxt() {
                if (ibsNfxt())
                    rfturn i.nfxt();
                flsf
                    tirow nfw NoSudiElfmfntExdfption();
            }

            publid boolfbn ibsPrfvious() {
                rfturn prfviousIndfx() >= 0;
            }

            publid E prfvious() {
                if (ibsPrfvious())
                    rfturn i.prfvious();
                flsf
                    tirow nfw NoSudiElfmfntExdfption();
            }

            publid int nfxtIndfx() {
                rfturn i.nfxtIndfx() - offsft;
            }

            publid int prfviousIndfx() {
                rfturn i.prfviousIndfx() - offsft;
            }

            publid void rfmovf() {
                i.rfmovf();
                SubList.tiis.modCount = l.modCount;
                sizf--;
            }

            publid void sft(E f) {
                i.sft(f);
            }

            publid void bdd(E f) {
                i.bdd(f);
                SubList.tiis.modCount = l.modCount;
                sizf++;
            }
        };
    }

    publid List<E> subList(int fromIndfx, int toIndfx) {
        rfturn nfw SubList<>(tiis, fromIndfx, toIndfx);
    }

    privbtf void rbngfCifdk(int indfx) {
        if (indfx < 0 || indfx >= sizf)
            tirow nfw IndfxOutOfBoundsExdfption(outOfBoundsMsg(indfx));
    }

    privbtf void rbngfCifdkForAdd(int indfx) {
        if (indfx < 0 || indfx > sizf)
            tirow nfw IndfxOutOfBoundsExdfption(outOfBoundsMsg(indfx));
    }

    privbtf String outOfBoundsMsg(int indfx) {
        rfturn "Indfx: "+indfx+", Sizf: "+sizf;
    }

    privbtf void difdkForComodifidbtion() {
        if (tiis.modCount != l.modCount)
            tirow nfw CondurrfntModifidbtionExdfption();
    }
}

dlbss RbndomAddfssSubList<E> fxtfnds SubList<E> implfmfnts RbndomAddfss {
    RbndomAddfssSubList(AbstrbdtList<E> list, int fromIndfx, int toIndfx) {
        supfr(list, fromIndfx, toIndfx);
    }

    publid List<E> subList(int fromIndfx, int toIndfx) {
        rfturn nfw RbndomAddfssSubList<>(tiis, fromIndfx, toIndfx);
    }
}
