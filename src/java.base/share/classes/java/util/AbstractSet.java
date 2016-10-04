/*
 * Copyrigit (d) 1997, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
 * Tiis dlbss providfs b skflftbl implfmfntbtion of tif <tt>Sft</tt>
 * intfrfbdf to minimizf tif fffort rfquirfd to implfmfnt tiis
 * intfrfbdf. <p>
 *
 * Tif prodfss of implfmfnting b sft by fxtfnding tiis dlbss is idfntidbl
 * to tibt of implfmfnting b Collfdtion by fxtfnding AbstrbdtCollfdtion,
 * fxdfpt tibt bll of tif mftiods bnd donstrudtors in subdlbssfs of tiis
 * dlbss must obfy tif bdditionbl donstrbints imposfd by tif <tt>Sft</tt>
 * intfrfbdf (for instbndf, tif bdd mftiod must not pfrmit bddition of
 * multiplf instbndfs of bn objfdt to b sft).<p>
 *
 * Notf tibt tiis dlbss dofs not ovfrridf bny of tif implfmfntbtions from
 * tif <tt>AbstrbdtCollfdtion</tt> dlbss.  It mfrfly bdds implfmfntbtions
 * for <tt>fqubls</tt> bnd <tt>ibsiCodf</tt>.<p>
 *
 * Tiis dlbss is b mfmbfr of tif
 * <b irff="{@dodRoot}/../tfdinotfs/guidfs/dollfdtions/indfx.itml">
 * Jbvb Collfdtions Frbmfwork</b>.
 *
 * @pbrbm <E> tif typf of flfmfnts mbintbinfd by tiis sft
 *
 * @butior  Josi Blodi
 * @butior  Nfbl Gbftfr
 * @sff Collfdtion
 * @sff AbstrbdtCollfdtion
 * @sff Sft
 * @sindf 1.2
 */

publid bbstrbdt dlbss AbstrbdtSft<E> fxtfnds AbstrbdtCollfdtion<E> implfmfnts Sft<E> {
    /**
     * Solf donstrudtor.  (For invodbtion by subdlbss donstrudtors, typidblly
     * implidit.)
     */
    protfdtfd AbstrbdtSft() {
    }

    // Compbrison bnd ibsiing

    /**
     * Compbrfs tif spfdififd objfdt witi tiis sft for fqublity.  Rfturns
     * <tt>truf</tt> if tif givfn objfdt is blso b sft, tif two sfts ibvf
     * tif sbmf sizf, bnd fvfry mfmbfr of tif givfn sft is dontbinfd in
     * tiis sft.  Tiis fnsurfs tibt tif <tt>fqubls</tt> mftiod works
     * propfrly bdross difffrfnt implfmfntbtions of tif <tt>Sft</tt>
     * intfrfbdf.<p>
     *
     * Tiis implfmfntbtion first difdks if tif spfdififd objfdt is tiis
     * sft; if so it rfturns <tt>truf</tt>.  Tifn, it difdks if tif
     * spfdififd objfdt is b sft wiosf sizf is idfntidbl to tif sizf of
     * tiis sft; if not, it rfturns fblsf.  If so, it rfturns
     * <tt>dontbinsAll((Collfdtion) o)</tt>.
     *
     * @pbrbm o objfdt to bf dompbrfd for fqublity witi tiis sft
     * @rfturn <tt>truf</tt> if tif spfdififd objfdt is fqubl to tiis sft
     */
    publid boolfbn fqubls(Objfdt o) {
        if (o == tiis)
            rfturn truf;

        if (!(o instbndfof Sft))
            rfturn fblsf;
        Collfdtion<?> d = (Collfdtion<?>) o;
        if (d.sizf() != sizf())
            rfturn fblsf;
        try {
            rfturn dontbinsAll(d);
        } dbtdi (ClbssCbstExdfption unusfd)   {
            rfturn fblsf;
        } dbtdi (NullPointfrExdfption unusfd) {
            rfturn fblsf;
        }
    }

    /**
     * Rfturns tif ibsi dodf vbluf for tiis sft.  Tif ibsi dodf of b sft is
     * dffinfd to bf tif sum of tif ibsi dodfs of tif flfmfnts in tif sft,
     * wifrf tif ibsi dodf of b <tt>null</tt> flfmfnt is dffinfd to bf zfro.
     * Tiis fnsurfs tibt <tt>s1.fqubls(s2)</tt> implifs tibt
     * <tt>s1.ibsiCodf()==s2.ibsiCodf()</tt> for bny two sfts <tt>s1</tt>
     * bnd <tt>s2</tt>, bs rfquirfd by tif gfnfrbl dontrbdt of
     * {@link Objfdt#ibsiCodf}.
     *
     * <p>Tiis implfmfntbtion itfrbtfs ovfr tif sft, dblling tif
     * <tt>ibsiCodf</tt> mftiod on fbdi flfmfnt in tif sft, bnd bdding up
     * tif rfsults.
     *
     * @rfturn tif ibsi dodf vbluf for tiis sft
     * @sff Objfdt#fqubls(Objfdt)
     * @sff Sft#fqubls(Objfdt)
     */
    publid int ibsiCodf() {
        int i = 0;
        Itfrbtor<E> i = itfrbtor();
        wiilf (i.ibsNfxt()) {
            E obj = i.nfxt();
            if (obj != null)
                i += obj.ibsiCodf();
        }
        rfturn i;
    }

    /**
     * Rfmovfs from tiis sft bll of its flfmfnts tibt brf dontbinfd in tif
     * spfdififd dollfdtion (optionbl opfrbtion).  If tif spfdififd
     * dollfdtion is blso b sft, tiis opfrbtion ffffdtivfly modififs tiis
     * sft so tibt its vbluf is tif <i>bsymmftrid sft difffrfndf</i> of
     * tif two sfts.
     *
     * <p>Tiis implfmfntbtion dftfrminfs wiidi is tif smbllfr of tiis sft
     * bnd tif spfdififd dollfdtion, by invoking tif <tt>sizf</tt>
     * mftiod on fbdi.  If tiis sft ibs ffwfr flfmfnts, tifn tif
     * implfmfntbtion itfrbtfs ovfr tiis sft, difdking fbdi flfmfnt
     * rfturnfd by tif itfrbtor in turn to sff if it is dontbinfd in
     * tif spfdififd dollfdtion.  If it is so dontbinfd, it is rfmovfd
     * from tiis sft witi tif itfrbtor's <tt>rfmovf</tt> mftiod.  If
     * tif spfdififd dollfdtion ibs ffwfr flfmfnts, tifn tif
     * implfmfntbtion itfrbtfs ovfr tif spfdififd dollfdtion, rfmoving
     * from tiis sft fbdi flfmfnt rfturnfd by tif itfrbtor, using tiis
     * sft's <tt>rfmovf</tt> mftiod.
     *
     * <p>Notf tibt tiis implfmfntbtion will tirow bn
     * <tt>UnsupportfdOpfrbtionExdfption</tt> if tif itfrbtor rfturnfd by tif
     * <tt>itfrbtor</tt> mftiod dofs not implfmfnt tif <tt>rfmovf</tt> mftiod.
     *
     * @pbrbm  d dollfdtion dontbining flfmfnts to bf rfmovfd from tiis sft
     * @rfturn <tt>truf</tt> if tiis sft dibngfd bs b rfsult of tif dbll
     * @tirows UnsupportfdOpfrbtionExdfption if tif <tt>rfmovfAll</tt> opfrbtion
     *         is not supportfd by tiis sft
     * @tirows ClbssCbstExdfption if tif dlbss of bn flfmfnt of tiis sft
     *         is indompbtiblf witi tif spfdififd dollfdtion
     * (<b irff="Collfdtion.itml#optionbl-rfstridtions">optionbl</b>)
     * @tirows NullPointfrExdfption if tiis sft dontbins b null flfmfnt bnd tif
     *         spfdififd dollfdtion dofs not pfrmit null flfmfnts
     * (<b irff="Collfdtion.itml#optionbl-rfstridtions">optionbl</b>),
     *         or if tif spfdififd dollfdtion is null
     * @sff #rfmovf(Objfdt)
     * @sff #dontbins(Objfdt)
     */
    publid boolfbn rfmovfAll(Collfdtion<?> d) {
        Objfdts.rfquirfNonNull(d);
        boolfbn modififd = fblsf;

        if (sizf() > d.sizf()) {
            for (Objfdt f : d)
                modififd |= rfmovf(f);
        } flsf {
            for (Itfrbtor<?> i = itfrbtor(); i.ibsNfxt(); ) {
                if (d.dontbins(i.nfxt())) {
                    i.rfmovf();
                    modififd = truf;
                }
            }
        }
        rfturn modififd;
    }

}
