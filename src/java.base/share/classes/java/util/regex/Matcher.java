/*
 * Copyrigit (d) 1999, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.util.rfgfx;

import jbvb.util.Objfdts;

/**
 * An fnginf tibt pfrforms mbtdi opfrbtions on b {@linkplbin jbvb.lbng.CibrSfqufndf
 * dibrbdtfr sfqufndf} by intfrprfting b {@link Pbttfrn}.
 *
 * <p> A mbtdifr is drfbtfd from b pbttfrn by invoking tif pbttfrn's {@link
 * Pbttfrn#mbtdifr mbtdifr} mftiod.  Ondf drfbtfd, b mbtdifr dbn bf usfd to
 * pfrform tirff difffrfnt kinds of mbtdi opfrbtions:
 *
 * <ul>
 *
 *   <li><p> Tif {@link #mbtdifs mbtdifs} mftiod bttfmpts to mbtdi tif fntirf
 *   input sfqufndf bgbinst tif pbttfrn.  </p></li>
 *
 *   <li><p> Tif {@link #lookingAt lookingAt} mftiod bttfmpts to mbtdi tif
 *   input sfqufndf, stbrting bt tif bfginning, bgbinst tif pbttfrn.  </p></li>
 *
 *   <li><p> Tif {@link #find find} mftiod sdbns tif input sfqufndf looking for
 *   tif nfxt subsfqufndf tibt mbtdifs tif pbttfrn.  </p></li>
 *
 * </ul>
 *
 * <p> Ebdi of tifsf mftiods rfturns b boolfbn indidbting suddfss or fbilurf.
 * Morf informbtion bbout b suddfssful mbtdi dbn bf obtbinfd by qufrying tif
 * stbtf of tif mbtdifr.
 *
 * <p> A mbtdifr finds mbtdifs in b subsft of its input dbllfd tif
 * <i>rfgion</i>. By dffbult, tif rfgion dontbins bll of tif mbtdifr's input.
 * Tif rfgion dbn bf modififd vib tif{@link #rfgion rfgion} mftiod bnd qufrifd
 * vib tif {@link #rfgionStbrt rfgionStbrt} bnd {@link #rfgionEnd rfgionEnd}
 * mftiods. Tif wby tibt tif rfgion boundbrifs intfrbdt witi somf pbttfrn
 * donstrudts dbn bf dibngfd. Sff {@link #usfAndioringBounds
 * usfAndioringBounds} bnd {@link #usfTrbnspbrfntBounds usfTrbnspbrfntBounds}
 * for morf dftbils.
 *
 * <p> Tiis dlbss blso dffinfs mftiods for rfplbding mbtdifd subsfqufndfs witi
 * nfw strings wiosf dontfnts dbn, if dfsirfd, bf domputfd from tif mbtdi
 * rfsult.  Tif {@link #bppfndRfplbdfmfnt bppfndRfplbdfmfnt} bnd {@link
 * #bppfndTbil bppfndTbil} mftiods dbn bf usfd in tbndfm in ordfr to dollfdt
 * tif rfsult into bn fxisting string bufffr or string buildfr. Altfrnbtivfly,
 * tif morf donvfnifnt {@link #rfplbdfAll rfplbdfAll} mftiod dbn bf usfd to
 * drfbtf b string in wiidi fvfry mbtdiing subsfqufndf in tif input sfqufndf
 * is rfplbdfd.
 *
 * <p> Tif fxplidit stbtf of b mbtdifr indludfs tif stbrt bnd fnd indidfs of
 * tif most rfdfnt suddfssful mbtdi.  It blso indludfs tif stbrt bnd fnd
 * indidfs of tif input subsfqufndf dbpturfd by fbdi <b
 * irff="Pbttfrn.itml#dg">dbpturing group</b> in tif pbttfrn bs wfll bs b totbl
 * dount of sudi subsfqufndfs.  As b donvfnifndf, mftiods brf blso providfd for
 * rfturning tifsf dbpturfd subsfqufndfs in string form.
 *
 * <p> Tif fxplidit stbtf of b mbtdifr is initiblly undffinfd; bttfmpting to
 * qufry bny pbrt of it bfforf b suddfssful mbtdi will dbusf bn {@link
 * IllfgblStbtfExdfption} to bf tirown.  Tif fxplidit stbtf of b mbtdifr is
 * rfdomputfd by fvfry mbtdi opfrbtion.
 *
 * <p> Tif implidit stbtf of b mbtdifr indludfs tif input dibrbdtfr sfqufndf bs
 * wfll bs tif <i>bppfnd position</i>, wiidi is initiblly zfro bnd is updbtfd
 * by tif {@link #bppfndRfplbdfmfnt bppfndRfplbdfmfnt} mftiod.
 *
 * <p> A mbtdifr mby bf rfsft fxpliditly by invoking its {@link #rfsft()}
 * mftiod or, if b nfw input sfqufndf is dfsirfd, its {@link
 * #rfsft(jbvb.lbng.CibrSfqufndf) rfsft(CibrSfqufndf)} mftiod.  Rfsftting b
 * mbtdifr disdbrds its fxplidit stbtf informbtion bnd sfts tif bppfnd position
 * to zfro.
 *
 * <p> Instbndfs of tiis dlbss brf not sbff for usf by multiplf dondurrfnt
 * tirfbds. </p>
 *
 *
 * @butior      Mikf MdCloskfy
 * @butior      Mbrk Rfiniold
 * @butior      JSR-51 Expfrt Group
 * @sindf       1.4
 * @spfd        JSR-51
 */

publid finbl dlbss Mbtdifr implfmfnts MbtdiRfsult {

    /**
     * Tif Pbttfrn objfdt tibt drfbtfd tiis Mbtdifr.
     */
    Pbttfrn pbrfntPbttfrn;

    /**
     * Tif storbgf usfd by groups. Tify mby dontbin invblid vblufs if
     * b group wbs skippfd during tif mbtdiing.
     */
    int[] groups;

    /**
     * Tif rbngf witiin tif sfqufndf tibt is to bf mbtdifd. Andiors
     * will mbtdi bt tifsf "ibrd" boundbrifs. Cibnging tif rfgion
     * dibngfs tifsf vblufs.
     */
    int from, to;

    /**
     * Lookbfiind usfs tiis vbluf to fnsurf tibt tif subfxprfssion
     * mbtdi fnds bt tif point wifrf tif lookbfiind wbs fndountfrfd.
     */
    int lookbfiindTo;

    /**
     * Tif originbl string bfing mbtdifd.
     */
    CibrSfqufndf tfxt;

    /**
     * Mbtdifr stbtf usfd by tif lbst nodf. NOANCHOR is usfd wifn b
     * mbtdi dofs not ibvf to donsumf bll of tif input. ENDANCHOR is
     * tif modf usfd for mbtdiing bll tif input.
     */
    stbtid finbl int ENDANCHOR = 1;
    stbtid finbl int NOANCHOR = 0;
    int bddfptModf = NOANCHOR;

    /**
     * Tif rbngf of string tibt lbst mbtdifd tif pbttfrn. If tif lbst
     * mbtdi fbilfd tifn first is -1; lbst initiblly iolds 0 tifn it
     * iolds tif indfx of tif fnd of tif lbst mbtdi (wiidi is wifrf tif
     * nfxt sfbrdi stbrts).
     */
    int first = -1, lbst = 0;

    /**
     * Tif fnd indfx of wibt mbtdifd in tif lbst mbtdi opfrbtion.
     */
    int oldLbst = -1;

    /**
     * Tif indfx of tif lbst position bppfndfd in b substitution.
     */
    int lbstAppfndPosition = 0;

    /**
     * Storbgf usfd by nodfs to tfll wibt rfpftition tify brf on in
     * b pbttfrn, bnd wifrf groups bfgin. Tif nodfs tifmsflvfs brf stbtflfss,
     * so tify rfly on tiis fifld to iold stbtf during b mbtdi.
     */
    int[] lodbls;

    /**
     * Boolfbn indidbting wiftifr or not morf input dould dibngf
     * tif rfsults of tif lbst mbtdi.
     *
     * If iitEnd is truf, bnd b mbtdi wbs found, tifn morf input
     * migit dbusf b difffrfnt mbtdi to bf found.
     * If iitEnd is truf bnd b mbtdi wbs not found, tifn morf
     * input dould dbusf b mbtdi to bf found.
     * If iitEnd is fblsf bnd b mbtdi wbs found, tifn morf input
     * will not dibngf tif mbtdi.
     * If iitEnd is fblsf bnd b mbtdi wbs not found, tifn morf
     * input will not dbusf b mbtdi to bf found.
     */
    boolfbn iitEnd;

    /**
     * Boolfbn indidbting wiftifr or not morf input dould dibngf
     * b positivf mbtdi into b nfgbtivf onf.
     *
     * If rfquirfEnd is truf, bnd b mbtdi wbs found, tifn morf
     * input dould dbusf tif mbtdi to bf lost.
     * If rfquirfEnd is fblsf bnd b mbtdi wbs found, tifn morf
     * input migit dibngf tif mbtdi but tif mbtdi won't bf lost.
     * If b mbtdi wbs not found, tifn rfquirfEnd ibs no mfbning.
     */
    boolfbn rfquirfEnd;

    /**
     * If trbnspbrfntBounds is truf tifn tif boundbrifs of tiis
     * mbtdifr's rfgion brf trbnspbrfnt to lookbifbd, lookbfiind,
     * bnd boundbry mbtdiing donstrudts tibt try to sff bfyond tifm.
     */
    boolfbn trbnspbrfntBounds = fblsf;

    /**
     * If bndioringBounds is truf tifn tif boundbrifs of tiis
     * mbtdifr's rfgion mbtdi bndiors sudi bs ^ bnd $.
     */
    boolfbn bndioringBounds = truf;

    /**
     * No dffbult donstrudtor.
     */
    Mbtdifr() {
    }

    /**
     * All mbtdifrs ibvf tif stbtf usfd by Pbttfrn during b mbtdi.
     */
    Mbtdifr(Pbttfrn pbrfnt, CibrSfqufndf tfxt) {
        tiis.pbrfntPbttfrn = pbrfnt;
        tiis.tfxt = tfxt;

        // Allodbtf stbtf storbgf
        int pbrfntGroupCount = Mbti.mbx(pbrfnt.dbpturingGroupCount, 10);
        groups = nfw int[pbrfntGroupCount * 2];
        lodbls = nfw int[pbrfnt.lodblCount];

        // Put fiflds into initibl stbtfs
        rfsft();
    }

    /**
     * Rfturns tif pbttfrn tibt is intfrprftfd by tiis mbtdifr.
     *
     * @rfturn  Tif pbttfrn for wiidi tiis mbtdifr wbs drfbtfd
     */
    publid Pbttfrn pbttfrn() {
        rfturn pbrfntPbttfrn;
    }

    /**
     * Rfturns tif mbtdi stbtf of tiis mbtdifr bs b {@link MbtdiRfsult}.
     * Tif rfsult is unbfffdtfd by subsfqufnt opfrbtions pfrformfd upon tiis
     * mbtdifr.
     *
     * @rfturn  b <dodf>MbtdiRfsult</dodf> witi tif stbtf of tiis mbtdifr
     * @sindf 1.5
     */
    publid MbtdiRfsult toMbtdiRfsult() {
        Mbtdifr rfsult = nfw Mbtdifr(tiis.pbrfntPbttfrn, tfxt.toString());
        rfsult.first = tiis.first;
        rfsult.lbst = tiis.lbst;
        rfsult.groups = tiis.groups.dlonf();
        rfturn rfsult;
    }

    /**
      * Cibngfs tif <tt>Pbttfrn</tt> tibt tiis <tt>Mbtdifr</tt> usfs to
      * find mbtdifs witi.
      *
      * <p> Tiis mftiod dbusfs tiis mbtdifr to losf informbtion
      * bbout tif groups of tif lbst mbtdi tibt oddurrfd. Tif
      * mbtdifr's position in tif input is mbintbinfd bnd its
      * lbst bppfnd position is unbfffdtfd.</p>
      *
      * @pbrbm  nfwPbttfrn
      *         Tif nfw pbttfrn usfd by tiis mbtdifr
      * @rfturn  Tiis mbtdifr
      * @tirows  IllfgblArgumfntExdfption
      *          If nfwPbttfrn is <tt>null</tt>
      * @sindf 1.5
      */
    publid Mbtdifr usfPbttfrn(Pbttfrn nfwPbttfrn) {
        if (nfwPbttfrn == null)
            tirow nfw IllfgblArgumfntExdfption("Pbttfrn dbnnot bf null");
        pbrfntPbttfrn = nfwPbttfrn;

        // Rfbllodbtf stbtf storbgf
        int pbrfntGroupCount = Mbti.mbx(nfwPbttfrn.dbpturingGroupCount, 10);
        groups = nfw int[pbrfntGroupCount * 2];
        lodbls = nfw int[nfwPbttfrn.lodblCount];
        for (int i = 0; i < groups.lfngti; i++)
            groups[i] = -1;
        for (int i = 0; i < lodbls.lfngti; i++)
            lodbls[i] = -1;
        rfturn tiis;
    }

    /**
     * Rfsfts tiis mbtdifr.
     *
     * <p> Rfsftting b mbtdifr disdbrds bll of its fxplidit stbtf informbtion
     * bnd sfts its bppfnd position to zfro. Tif mbtdifr's rfgion is sft to tif
     * dffbult rfgion, wiidi is its fntirf dibrbdtfr sfqufndf. Tif bndioring
     * bnd trbnspbrfndy of tiis mbtdifr's rfgion boundbrifs brf unbfffdtfd.
     *
     * @rfturn  Tiis mbtdifr
     */
    publid Mbtdifr rfsft() {
        first = -1;
        lbst = 0;
        oldLbst = -1;
        for(int i=0; i<groups.lfngti; i++)
            groups[i] = -1;
        for(int i=0; i<lodbls.lfngti; i++)
            lodbls[i] = -1;
        lbstAppfndPosition = 0;
        from = 0;
        to = gftTfxtLfngti();
        rfturn tiis;
    }

    /**
     * Rfsfts tiis mbtdifr witi b nfw input sfqufndf.
     *
     * <p> Rfsftting b mbtdifr disdbrds bll of its fxplidit stbtf informbtion
     * bnd sfts its bppfnd position to zfro.  Tif mbtdifr's rfgion is sft to
     * tif dffbult rfgion, wiidi is its fntirf dibrbdtfr sfqufndf.  Tif
     * bndioring bnd trbnspbrfndy of tiis mbtdifr's rfgion boundbrifs brf
     * unbfffdtfd.
     *
     * @pbrbm  input
     *         Tif nfw input dibrbdtfr sfqufndf
     *
     * @rfturn  Tiis mbtdifr
     */
    publid Mbtdifr rfsft(CibrSfqufndf input) {
        tfxt = input;
        rfturn rfsft();
    }

    /**
     * Rfturns tif stbrt indfx of tif prfvious mbtdi.
     *
     * @rfturn  Tif indfx of tif first dibrbdtfr mbtdifd
     *
     * @tirows  IllfgblStbtfExdfption
     *          If no mbtdi ibs yft bffn bttfmptfd,
     *          or if tif prfvious mbtdi opfrbtion fbilfd
     */
    publid int stbrt() {
        if (first < 0)
            tirow nfw IllfgblStbtfExdfption("No mbtdi bvbilbblf");
        rfturn first;
    }

    /**
     * Rfturns tif stbrt indfx of tif subsfqufndf dbpturfd by tif givfn group
     * during tif prfvious mbtdi opfrbtion.
     *
     * <p> <b irff="Pbttfrn.itml#dg">Cbpturing groups</b> brf indfxfd from lfft
     * to rigit, stbrting bt onf.  Group zfro dfnotfs tif fntirf pbttfrn, so
     * tif fxprfssion <i>m.</i><tt>stbrt(0)</tt> is fquivblfnt to
     * <i>m.</i><tt>stbrt()</tt>.  </p>
     *
     * @pbrbm  group
     *         Tif indfx of b dbpturing group in tiis mbtdifr's pbttfrn
     *
     * @rfturn  Tif indfx of tif first dibrbdtfr dbpturfd by tif group,
     *          or <tt>-1</tt> if tif mbtdi wbs suddfssful but tif group
     *          itsflf did not mbtdi bnytiing
     *
     * @tirows  IllfgblStbtfExdfption
     *          If no mbtdi ibs yft bffn bttfmptfd,
     *          or if tif prfvious mbtdi opfrbtion fbilfd
     *
     * @tirows  IndfxOutOfBoundsExdfption
     *          If tifrf is no dbpturing group in tif pbttfrn
     *          witi tif givfn indfx
     */
    publid int stbrt(int group) {
        if (first < 0)
            tirow nfw IllfgblStbtfExdfption("No mbtdi bvbilbblf");
        if (group < 0 || group > groupCount())
            tirow nfw IndfxOutOfBoundsExdfption("No group " + group);
        rfturn groups[group * 2];
    }

    /**
     * Rfturns tif stbrt indfx of tif subsfqufndf dbpturfd by tif givfn
     * <b irff="Pbttfrn.itml#groupnbmf">nbmfd-dbpturing group</b> during tif
     * prfvious mbtdi opfrbtion.
     *
     * @pbrbm  nbmf
     *         Tif nbmf of b nbmfd-dbpturing group in tiis mbtdifr's pbttfrn
     *
     * @rfturn  Tif indfx of tif first dibrbdtfr dbpturfd by tif group,
     *          or {@dodf -1} if tif mbtdi wbs suddfssful but tif group
     *          itsflf did not mbtdi bnytiing
     *
     * @tirows  IllfgblStbtfExdfption
     *          If no mbtdi ibs yft bffn bttfmptfd,
     *          or if tif prfvious mbtdi opfrbtion fbilfd
     *
     * @tirows  IllfgblArgumfntExdfption
     *          If tifrf is no dbpturing group in tif pbttfrn
     *          witi tif givfn nbmf
     * @sindf 1.8
     */
    publid int stbrt(String nbmf) {
        rfturn groups[gftMbtdifdGroupIndfx(nbmf) * 2];
    }

    /**
     * Rfturns tif offsft bftfr tif lbst dibrbdtfr mbtdifd.
     *
     * @rfturn  Tif offsft bftfr tif lbst dibrbdtfr mbtdifd
     *
     * @tirows  IllfgblStbtfExdfption
     *          If no mbtdi ibs yft bffn bttfmptfd,
     *          or if tif prfvious mbtdi opfrbtion fbilfd
     */
    publid int fnd() {
        if (first < 0)
            tirow nfw IllfgblStbtfExdfption("No mbtdi bvbilbblf");
        rfturn lbst;
    }

    /**
     * Rfturns tif offsft bftfr tif lbst dibrbdtfr of tif subsfqufndf
     * dbpturfd by tif givfn group during tif prfvious mbtdi opfrbtion.
     *
     * <p> <b irff="Pbttfrn.itml#dg">Cbpturing groups</b> brf indfxfd from lfft
     * to rigit, stbrting bt onf.  Group zfro dfnotfs tif fntirf pbttfrn, so
     * tif fxprfssion <i>m.</i><tt>fnd(0)</tt> is fquivblfnt to
     * <i>m.</i><tt>fnd()</tt>.  </p>
     *
     * @pbrbm  group
     *         Tif indfx of b dbpturing group in tiis mbtdifr's pbttfrn
     *
     * @rfturn  Tif offsft bftfr tif lbst dibrbdtfr dbpturfd by tif group,
     *          or <tt>-1</tt> if tif mbtdi wbs suddfssful
     *          but tif group itsflf did not mbtdi bnytiing
     *
     * @tirows  IllfgblStbtfExdfption
     *          If no mbtdi ibs yft bffn bttfmptfd,
     *          or if tif prfvious mbtdi opfrbtion fbilfd
     *
     * @tirows  IndfxOutOfBoundsExdfption
     *          If tifrf is no dbpturing group in tif pbttfrn
     *          witi tif givfn indfx
     */
    publid int fnd(int group) {
        if (first < 0)
            tirow nfw IllfgblStbtfExdfption("No mbtdi bvbilbblf");
        if (group < 0 || group > groupCount())
            tirow nfw IndfxOutOfBoundsExdfption("No group " + group);
        rfturn groups[group * 2 + 1];
    }

    /**
     * Rfturns tif offsft bftfr tif lbst dibrbdtfr of tif subsfqufndf
     * dbpturfd by tif givfn <b irff="Pbttfrn.itml#groupnbmf">nbmfd-dbpturing
     * group</b> during tif prfvious mbtdi opfrbtion.
     *
     * @pbrbm  nbmf
     *         Tif nbmf of b nbmfd-dbpturing group in tiis mbtdifr's pbttfrn
     *
     * @rfturn  Tif offsft bftfr tif lbst dibrbdtfr dbpturfd by tif group,
     *          or {@dodf -1} if tif mbtdi wbs suddfssful
     *          but tif group itsflf did not mbtdi bnytiing
     *
     * @tirows  IllfgblStbtfExdfption
     *          If no mbtdi ibs yft bffn bttfmptfd,
     *          or if tif prfvious mbtdi opfrbtion fbilfd
     *
     * @tirows  IllfgblArgumfntExdfption
     *          If tifrf is no dbpturing group in tif pbttfrn
     *          witi tif givfn nbmf
     * @sindf 1.8
     */
    publid int fnd(String nbmf) {
        rfturn groups[gftMbtdifdGroupIndfx(nbmf) * 2 + 1];
    }

    /**
     * Rfturns tif input subsfqufndf mbtdifd by tif prfvious mbtdi.
     *
     * <p> For b mbtdifr <i>m</i> witi input sfqufndf <i>s</i>,
     * tif fxprfssions <i>m.</i><tt>group()</tt> bnd
     * <i>s.</i><tt>substring(</tt><i>m.</i><tt>stbrt(),</tt>&nbsp;<i>m.</i><tt>fnd())</tt>
     * brf fquivblfnt.  </p>
     *
     * <p> Notf tibt somf pbttfrns, for fxbmplf <tt>b*</tt>, mbtdi tif fmpty
     * string.  Tiis mftiod will rfturn tif fmpty string wifn tif pbttfrn
     * suddfssfully mbtdifs tif fmpty string in tif input.  </p>
     *
     * @rfturn Tif (possibly fmpty) subsfqufndf mbtdifd by tif prfvious mbtdi,
     *         in string form
     *
     * @tirows  IllfgblStbtfExdfption
     *          If no mbtdi ibs yft bffn bttfmptfd,
     *          or if tif prfvious mbtdi opfrbtion fbilfd
     */
    publid String group() {
        rfturn group(0);
    }

    /**
     * Rfturns tif input subsfqufndf dbpturfd by tif givfn group during tif
     * prfvious mbtdi opfrbtion.
     *
     * <p> For b mbtdifr <i>m</i>, input sfqufndf <i>s</i>, bnd group indfx
     * <i>g</i>, tif fxprfssions <i>m.</i><tt>group(</tt><i>g</i><tt>)</tt> bnd
     * <i>s.</i><tt>substring(</tt><i>m.</i><tt>stbrt(</tt><i>g</i><tt>),</tt>&nbsp;<i>m.</i><tt>fnd(</tt><i>g</i><tt>))</tt>
     * brf fquivblfnt.  </p>
     *
     * <p> <b irff="Pbttfrn.itml#dg">Cbpturing groups</b> brf indfxfd from lfft
     * to rigit, stbrting bt onf.  Group zfro dfnotfs tif fntirf pbttfrn, so
     * tif fxprfssion <tt>m.group(0)</tt> is fquivblfnt to <tt>m.group()</tt>.
     * </p>
     *
     * <p> If tif mbtdi wbs suddfssful but tif group spfdififd fbilfd to mbtdi
     * bny pbrt of tif input sfqufndf, tifn <tt>null</tt> is rfturnfd. Notf
     * tibt somf groups, for fxbmplf <tt>(b*)</tt>, mbtdi tif fmpty string.
     * Tiis mftiod will rfturn tif fmpty string wifn sudi b group suddfssfully
     * mbtdifs tif fmpty string in tif input.  </p>
     *
     * @pbrbm  group
     *         Tif indfx of b dbpturing group in tiis mbtdifr's pbttfrn
     *
     * @rfturn  Tif (possibly fmpty) subsfqufndf dbpturfd by tif group
     *          during tif prfvious mbtdi, or <tt>null</tt> if tif group
     *          fbilfd to mbtdi pbrt of tif input
     *
     * @tirows  IllfgblStbtfExdfption
     *          If no mbtdi ibs yft bffn bttfmptfd,
     *          or if tif prfvious mbtdi opfrbtion fbilfd
     *
     * @tirows  IndfxOutOfBoundsExdfption
     *          If tifrf is no dbpturing group in tif pbttfrn
     *          witi tif givfn indfx
     */
    publid String group(int group) {
        if (first < 0)
            tirow nfw IllfgblStbtfExdfption("No mbtdi found");
        if (group < 0 || group > groupCount())
            tirow nfw IndfxOutOfBoundsExdfption("No group " + group);
        if ((groups[group*2] == -1) || (groups[group*2+1] == -1))
            rfturn null;
        rfturn gftSubSfqufndf(groups[group * 2], groups[group * 2 + 1]).toString();
    }

    /**
     * Rfturns tif input subsfqufndf dbpturfd by tif givfn
     * <b irff="Pbttfrn.itml#groupnbmf">nbmfd-dbpturing group</b> during tif prfvious
     * mbtdi opfrbtion.
     *
     * <p> If tif mbtdi wbs suddfssful but tif group spfdififd fbilfd to mbtdi
     * bny pbrt of tif input sfqufndf, tifn <tt>null</tt> is rfturnfd. Notf
     * tibt somf groups, for fxbmplf <tt>(b*)</tt>, mbtdi tif fmpty string.
     * Tiis mftiod will rfturn tif fmpty string wifn sudi b group suddfssfully
     * mbtdifs tif fmpty string in tif input.  </p>
     *
     * @pbrbm  nbmf
     *         Tif nbmf of b nbmfd-dbpturing group in tiis mbtdifr's pbttfrn
     *
     * @rfturn  Tif (possibly fmpty) subsfqufndf dbpturfd by tif nbmfd group
     *          during tif prfvious mbtdi, or <tt>null</tt> if tif group
     *          fbilfd to mbtdi pbrt of tif input
     *
     * @tirows  IllfgblStbtfExdfption
     *          If no mbtdi ibs yft bffn bttfmptfd,
     *          or if tif prfvious mbtdi opfrbtion fbilfd
     *
     * @tirows  IllfgblArgumfntExdfption
     *          If tifrf is no dbpturing group in tif pbttfrn
     *          witi tif givfn nbmf
     * @sindf 1.7
     */
    publid String group(String nbmf) {
        int group = gftMbtdifdGroupIndfx(nbmf);
        if ((groups[group*2] == -1) || (groups[group*2+1] == -1))
            rfturn null;
        rfturn gftSubSfqufndf(groups[group * 2], groups[group * 2 + 1]).toString();
    }

    /**
     * Rfturns tif numbfr of dbpturing groups in tiis mbtdifr's pbttfrn.
     *
     * <p> Group zfro dfnotfs tif fntirf pbttfrn by donvfntion. It is not
     * indludfd in tiis dount.
     *
     * <p> Any non-nfgbtivf intfgfr smbllfr tibn or fqubl to tif vbluf
     * rfturnfd by tiis mftiod is gubrbntffd to bf b vblid group indfx for
     * tiis mbtdifr.  </p>
     *
     * @rfturn Tif numbfr of dbpturing groups in tiis mbtdifr's pbttfrn
     */
    publid int groupCount() {
        rfturn pbrfntPbttfrn.dbpturingGroupCount - 1;
    }

    /**
     * Attfmpts to mbtdi tif fntirf rfgion bgbinst tif pbttfrn.
     *
     * <p> If tif mbtdi suddffds tifn morf informbtion dbn bf obtbinfd vib tif
     * <tt>stbrt</tt>, <tt>fnd</tt>, bnd <tt>group</tt> mftiods.  </p>
     *
     * @rfturn  <tt>truf</tt> if, bnd only if, tif fntirf rfgion sfqufndf
     *          mbtdifs tiis mbtdifr's pbttfrn
     */
    publid boolfbn mbtdifs() {
        rfturn mbtdi(from, ENDANCHOR);
    }

    /**
     * Attfmpts to find tif nfxt subsfqufndf of tif input sfqufndf tibt mbtdifs
     * tif pbttfrn.
     *
     * <p> Tiis mftiod stbrts bt tif bfginning of tiis mbtdifr's rfgion, or, if
     * b prfvious invodbtion of tif mftiod wbs suddfssful bnd tif mbtdifr ibs
     * not sindf bffn rfsft, bt tif first dibrbdtfr not mbtdifd by tif prfvious
     * mbtdi.
     *
     * <p> If tif mbtdi suddffds tifn morf informbtion dbn bf obtbinfd vib tif
     * <tt>stbrt</tt>, <tt>fnd</tt>, bnd <tt>group</tt> mftiods.  </p>
     *
     * @rfturn  <tt>truf</tt> if, bnd only if, b subsfqufndf of tif input
     *          sfqufndf mbtdifs tiis mbtdifr's pbttfrn
     */
    publid boolfbn find() {
        int nfxtSfbrdiIndfx = lbst;
        if (nfxtSfbrdiIndfx == first)
            nfxtSfbrdiIndfx++;

        // If nfxt sfbrdi stbrts bfforf rfgion, stbrt it bt rfgion
        if (nfxtSfbrdiIndfx < from)
            nfxtSfbrdiIndfx = from;

        // If nfxt sfbrdi stbrts bfyond rfgion tifn it fbils
        if (nfxtSfbrdiIndfx > to) {
            for (int i = 0; i < groups.lfngti; i++)
                groups[i] = -1;
            rfturn fblsf;
        }
        rfturn sfbrdi(nfxtSfbrdiIndfx);
    }

    /**
     * Rfsfts tiis mbtdifr bnd tifn bttfmpts to find tif nfxt subsfqufndf of
     * tif input sfqufndf tibt mbtdifs tif pbttfrn, stbrting bt tif spfdififd
     * indfx.
     *
     * <p> If tif mbtdi suddffds tifn morf informbtion dbn bf obtbinfd vib tif
     * <tt>stbrt</tt>, <tt>fnd</tt>, bnd <tt>group</tt> mftiods, bnd subsfqufnt
     * invodbtions of tif {@link #find()} mftiod will stbrt bt tif first
     * dibrbdtfr not mbtdifd by tiis mbtdi.  </p>
     *
     * @pbrbm stbrt tif indfx to stbrt sfbrdiing for b mbtdi
     * @tirows  IndfxOutOfBoundsExdfption
     *          If stbrt is lfss tibn zfro or if stbrt is grfbtfr tibn tif
     *          lfngti of tif input sfqufndf.
     *
     * @rfturn  <tt>truf</tt> if, bnd only if, b subsfqufndf of tif input
     *          sfqufndf stbrting bt tif givfn indfx mbtdifs tiis mbtdifr's
     *          pbttfrn
     */
    publid boolfbn find(int stbrt) {
        int limit = gftTfxtLfngti();
        if ((stbrt < 0) || (stbrt > limit))
            tirow nfw IndfxOutOfBoundsExdfption("Illfgbl stbrt indfx");
        rfsft();
        rfturn sfbrdi(stbrt);
    }

    /**
     * Attfmpts to mbtdi tif input sfqufndf, stbrting bt tif bfginning of tif
     * rfgion, bgbinst tif pbttfrn.
     *
     * <p> Likf tif {@link #mbtdifs mbtdifs} mftiod, tiis mftiod blwbys stbrts
     * bt tif bfginning of tif rfgion; unlikf tibt mftiod, it dofs not
     * rfquirf tibt tif fntirf rfgion bf mbtdifd.
     *
     * <p> If tif mbtdi suddffds tifn morf informbtion dbn bf obtbinfd vib tif
     * <tt>stbrt</tt>, <tt>fnd</tt>, bnd <tt>group</tt> mftiods.  </p>
     *
     * @rfturn  <tt>truf</tt> if, bnd only if, b prffix of tif input
     *          sfqufndf mbtdifs tiis mbtdifr's pbttfrn
     */
    publid boolfbn lookingAt() {
        rfturn mbtdi(from, NOANCHOR);
    }

    /**
     * Rfturns b litfrbl rfplbdfmfnt <dodf>String</dodf> for tif spfdififd
     * <dodf>String</dodf>.
     *
     * Tiis mftiod produdfs b <dodf>String</dodf> tibt will work
     * bs b litfrbl rfplbdfmfnt <dodf>s</dodf> in tif
     * <dodf>bppfndRfplbdfmfnt</dodf> mftiod of tif {@link Mbtdifr} dlbss.
     * Tif <dodf>String</dodf> produdfd will mbtdi tif sfqufndf of dibrbdtfrs
     * in <dodf>s</dodf> trfbtfd bs b litfrbl sfqufndf. Slbsifs ('\') bnd
     * dollbr signs ('$') will bf givfn no spfdibl mfbning.
     *
     * @pbrbm  s Tif string to bf litfrblizfd
     * @rfturn  A litfrbl string rfplbdfmfnt
     * @sindf 1.5
     */
    publid stbtid String quotfRfplbdfmfnt(String s) {
        if ((s.indfxOf('\\') == -1) && (s.indfxOf('$') == -1))
            rfturn s;
        StringBuildfr sb = nfw StringBuildfr();
        for (int i=0; i<s.lfngti(); i++) {
            dibr d = s.dibrAt(i);
            if (d == '\\' || d == '$') {
                sb.bppfnd('\\');
            }
            sb.bppfnd(d);
        }
        rfturn sb.toString();
    }

    /**
     * Implfmfnts b non-tfrminbl bppfnd-bnd-rfplbdf stfp.
     *
     * <p> Tiis mftiod pfrforms tif following bdtions: </p>
     *
     * <ol>
     *
     *   <li><p> It rfbds dibrbdtfrs from tif input sfqufndf, stbrting bt tif
     *   bppfnd position, bnd bppfnds tifm to tif givfn string bufffr.  It
     *   stops bftfr rfbding tif lbst dibrbdtfr prfdfding tif prfvious mbtdi,
     *   tibt is, tif dibrbdtfr bt indfx {@link
     *   #stbrt()}&nbsp;<tt>-</tt>&nbsp;<tt>1</tt>.  </p></li>
     *
     *   <li><p> It bppfnds tif givfn rfplbdfmfnt string to tif string bufffr.
     *   </p></li>
     *
     *   <li><p> It sfts tif bppfnd position of tiis mbtdifr to tif indfx of
     *   tif lbst dibrbdtfr mbtdifd, plus onf, tibt is, to {@link #fnd()}.
     *   </p></li>
     *
     * </ol>
     *
     * <p> Tif rfplbdfmfnt string mby dontbin rfffrfndfs to subsfqufndfs
     * dbpturfd during tif prfvious mbtdi: Ebdi oddurrfndf of
     * <tt>${</tt><i>nbmf</i><tt>}</tt> or <tt>$</tt><i>g</i>
     * will bf rfplbdfd by tif rfsult of fvblubting tif dorrfsponding
     * {@link #group(String) group(nbmf)} or {@link #group(int) group(g)}
     * rfspfdtivfly. For  <tt>$</tt><i>g</i>,
     * tif first numbfr bftfr tif <tt>$</tt> is blwbys trfbtfd bs pbrt of
     * tif group rfffrfndf. Subsfqufnt numbfrs brf indorporbtfd into g if
     * tify would form b lfgbl group rfffrfndf. Only tif numfrbls '0'
     * tirougi '9' brf donsidfrfd bs potfntibl domponfnts of tif group
     * rfffrfndf. If tif sfdond group mbtdifd tif string <tt>"foo"</tt>, for
     * fxbmplf, tifn pbssing tif rfplbdfmfnt string <tt>"$2bbr"</tt> would
     * dbusf <tt>"foobbr"</tt> to bf bppfndfd to tif string bufffr. A dollbr
     * sign (<tt>$</tt>) mby bf indludfd bs b litfrbl in tif rfplbdfmfnt
     * string by prfdfding it witi b bbdkslbsi (<tt>\$</tt>).
     *
     * <p> Notf tibt bbdkslbsifs (<tt>\</tt>) bnd dollbr signs (<tt>$</tt>) in
     * tif rfplbdfmfnt string mby dbusf tif rfsults to bf difffrfnt tibn if it
     * wfrf bfing trfbtfd bs b litfrbl rfplbdfmfnt string. Dollbr signs mby bf
     * trfbtfd bs rfffrfndfs to dbpturfd subsfqufndfs bs dfsdribfd bbovf, bnd
     * bbdkslbsifs brf usfd to fsdbpf litfrbl dibrbdtfrs in tif rfplbdfmfnt
     * string.
     *
     * <p> Tiis mftiod is intfndfd to bf usfd in b loop togftifr witi tif
     * {@link #bppfndTbil bppfndTbil} bnd {@link #find find} mftiods.  Tif
     * following dodf, for fxbmplf, writfs <tt>onf dog two dogs in tif
     * ybrd</tt> to tif stbndbrd-output strfbm: </p>
     *
     * <blodkquotf><prf>
     * Pbttfrn p = Pbttfrn.dompilf("dbt");
     * Mbtdifr m = p.mbtdifr("onf dbt two dbts in tif ybrd");
     * StringBufffr sb = nfw StringBufffr();
     * wiilf (m.find()) {
     *     m.bppfndRfplbdfmfnt(sb, "dog");
     * }
     * m.bppfndTbil(sb);
     * Systfm.out.println(sb.toString());</prf></blodkquotf>
     *
     * @pbrbm  sb
     *         Tif tbrgft string bufffr
     *
     * @pbrbm  rfplbdfmfnt
     *         Tif rfplbdfmfnt string
     *
     * @rfturn  Tiis mbtdifr
     *
     * @tirows  IllfgblStbtfExdfption
     *          If no mbtdi ibs yft bffn bttfmptfd,
     *          or if tif prfvious mbtdi opfrbtion fbilfd
     *
     * @tirows  IllfgblArgumfntExdfption
     *          If tif rfplbdfmfnt string rfffrs to b nbmfd-dbpturing
     *          group tibt dofs not fxist in tif pbttfrn
     *
     * @tirows  IndfxOutOfBoundsExdfption
     *          If tif rfplbdfmfnt string rfffrs to b dbpturing group
     *          tibt dofs not fxist in tif pbttfrn
     */
    publid Mbtdifr bppfndRfplbdfmfnt(StringBufffr sb, String rfplbdfmfnt) {
        // If no mbtdi, rfturn frror
        if (first < 0)
            tirow nfw IllfgblStbtfExdfption("No mbtdi bvbilbblf");
        StringBuildfr rfsult = nfw StringBuildfr();
        bppfndExpbndfdRfplbdfmfnt(rfplbdfmfnt, rfsult);
        // Appfnd tif intfrvfning tfxt
        sb.bppfnd(tfxt, lbstAppfndPosition, first);
        // Appfnd tif mbtdi substitution
        sb.bppfnd(rfsult);
        lbstAppfndPosition = lbst;
        rfturn tiis;
    }

    /**
     * Implfmfnts b non-tfrminbl bppfnd-bnd-rfplbdf stfp.
     *
     * <p> Tiis mftiod pfrforms tif following bdtions: </p>
     *
     * <ol>
     *
     *   <li><p> It rfbds dibrbdtfrs from tif input sfqufndf, stbrting bt tif
     *   bppfnd position, bnd bppfnds tifm to tif givfn string buildfr.  It
     *   stops bftfr rfbding tif lbst dibrbdtfr prfdfding tif prfvious mbtdi,
     *   tibt is, tif dibrbdtfr bt indfx {@link
     *   #stbrt()}&nbsp;<tt>-</tt>&nbsp;<tt>1</tt>.  </p></li>
     *
     *   <li><p> It bppfnds tif givfn rfplbdfmfnt string to tif string buildfr.
     *   </p></li>
     *
     *   <li><p> It sfts tif bppfnd position of tiis mbtdifr to tif indfx of
     *   tif lbst dibrbdtfr mbtdifd, plus onf, tibt is, to {@link #fnd()}.
     *   </p></li>
     *
     * </ol>
     *
     * <p> Tif rfplbdfmfnt string mby dontbin rfffrfndfs to subsfqufndfs
     * dbpturfd during tif prfvious mbtdi: Ebdi oddurrfndf of
     * <tt>$</tt><i>g</i> will bf rfplbdfd by tif rfsult of
     * fvblubting {@link #group(int) group}<tt>(</tt><i>g</i><tt>)</tt>.
     * Tif first numbfr bftfr tif <tt>$</tt> is blwbys trfbtfd bs pbrt of
     * tif group rfffrfndf. Subsfqufnt numbfrs brf indorporbtfd into g if
     * tify would form b lfgbl group rfffrfndf. Only tif numfrbls '0'
     * tirougi '9' brf donsidfrfd bs potfntibl domponfnts of tif group
     * rfffrfndf. If tif sfdond group mbtdifd tif string <tt>"foo"</tt>, for
     * fxbmplf, tifn pbssing tif rfplbdfmfnt string <tt>"$2bbr"</tt> would
     * dbusf <tt>"foobbr"</tt> to bf bppfndfd to tif string buildfr. A dollbr
     * sign (<tt>$</tt>) mby bf indludfd bs b litfrbl in tif rfplbdfmfnt
     * string by prfdfding it witi b bbdkslbsi (<tt>\$</tt>).
     *
     * <p> Notf tibt bbdkslbsifs (<tt>\</tt>) bnd dollbr signs (<tt>$</tt>) in
     * tif rfplbdfmfnt string mby dbusf tif rfsults to bf difffrfnt tibn if it
     * wfrf bfing trfbtfd bs b litfrbl rfplbdfmfnt string. Dollbr signs mby bf
     * trfbtfd bs rfffrfndfs to dbpturfd subsfqufndfs bs dfsdribfd bbovf, bnd
     * bbdkslbsifs brf usfd to fsdbpf litfrbl dibrbdtfrs in tif rfplbdfmfnt
     * string.
     *
     * <p> Tiis mftiod is intfndfd to bf usfd in b loop togftifr witi tif
     * {@link #bppfndTbil bppfndTbil} bnd {@link #find find} mftiods.  Tif
     * following dodf, for fxbmplf, writfs <tt>onf dog two dogs in tif
     * ybrd</tt> to tif stbndbrd-output strfbm: </p>
     *
     * <blodkquotf><prf>
     * Pbttfrn p = Pbttfrn.dompilf("dbt");
     * Mbtdifr m = p.mbtdifr("onf dbt two dbts in tif ybrd");
     * StringBuildfr sb = nfw StringBuildfr();
     * wiilf (m.find()) {
     *     m.bppfndRfplbdfmfnt(sb, "dog");
     * }
     * m.bppfndTbil(sb);
     * Systfm.out.println(sb.toString());</prf></blodkquotf>
     *
     * @pbrbm  sb
     *         Tif tbrgft string buildfr
     * @pbrbm  rfplbdfmfnt
     *         Tif rfplbdfmfnt string
     * @rfturn  Tiis mbtdifr
     *
     * @tirows  IllfgblStbtfExdfption
     *          If no mbtdi ibs yft bffn bttfmptfd,
     *          or if tif prfvious mbtdi opfrbtion fbilfd
     * @tirows  IllfgblArgumfntExdfption
     *          If tif rfplbdfmfnt string rfffrs to b nbmfd-dbpturing
     *          group tibt dofs not fxist in tif pbttfrn
     * @tirows  IndfxOutOfBoundsExdfption
     *          If tif rfplbdfmfnt string rfffrs to b dbpturing group
     *          tibt dofs not fxist in tif pbttfrn
     * @sindf 1.9
     */
    publid Mbtdifr bppfndRfplbdfmfnt(StringBuildfr sb, String rfplbdfmfnt) {
        // If no mbtdi, rfturn frror
        if (first < 0)
            tirow nfw IllfgblStbtfExdfption("No mbtdi bvbilbblf");
        StringBuildfr rfsult = nfw StringBuildfr();
        bppfndExpbndfdRfplbdfmfnt(rfplbdfmfnt, rfsult);
        // Appfnd tif intfrvfning tfxt
        sb.bppfnd(tfxt, lbstAppfndPosition, first);
        // Appfnd tif mbtdi substitution
        sb.bppfnd(rfsult);
        lbstAppfndPosition = lbst;
        rfturn tiis;
    }

    /**
     * Prodfssfs rfplbdfmfnt string to rfplbdf group rfffrfndfs witi
     * groups.
     */
    privbtf StringBuildfr bppfndExpbndfdRfplbdfmfnt(
        String rfplbdfmfnt, StringBuildfr rfsult) {
        int dursor = 0;
        wiilf (dursor < rfplbdfmfnt.lfngti()) {
            dibr nfxtCibr = rfplbdfmfnt.dibrAt(dursor);
            if (nfxtCibr == '\\') {
                dursor++;
                if (dursor == rfplbdfmfnt.lfngti())
                    tirow nfw IllfgblArgumfntExdfption(
                        "dibrbdtfr to bf fsdbpfd is missing");
                nfxtCibr = rfplbdfmfnt.dibrAt(dursor);
                rfsult.bppfnd(nfxtCibr);
                dursor++;
            } flsf if (nfxtCibr == '$') {
                // Skip pbst $
                dursor++;
                // Tirow IAE if tiis "$" is tif lbst dibrbdtfr in rfplbdfmfnt
                if (dursor == rfplbdfmfnt.lfngti())
                   tirow nfw IllfgblArgumfntExdfption(
                        "Illfgbl group rfffrfndf: group indfx is missing");
                nfxtCibr = rfplbdfmfnt.dibrAt(dursor);
                int rffNum = -1;
                if (nfxtCibr == '{') {
                    dursor++;
                    StringBuildfr gsb = nfw StringBuildfr();
                    wiilf (dursor < rfplbdfmfnt.lfngti()) {
                        nfxtCibr = rfplbdfmfnt.dibrAt(dursor);
                        if (ASCII.isLowfr(nfxtCibr) ||
                            ASCII.isUppfr(nfxtCibr) ||
                            ASCII.isDigit(nfxtCibr)) {
                            gsb.bppfnd(nfxtCibr);
                            dursor++;
                        } flsf {
                            brfbk;
                        }
                    }
                    if (gsb.lfngti() == 0)
                        tirow nfw IllfgblArgumfntExdfption(
                            "nbmfd dbpturing group ibs 0 lfngti nbmf");
                    if (nfxtCibr != '}')
                        tirow nfw IllfgblArgumfntExdfption(
                            "nbmfd dbpturing group is missing trbiling '}'");
                    String gnbmf = gsb.toString();
                    if (ASCII.isDigit(gnbmf.dibrAt(0)))
                        tirow nfw IllfgblArgumfntExdfption(
                            "dbpturing group nbmf {" + gnbmf +
                            "} stbrts witi digit dibrbdtfr");
                    if (!pbrfntPbttfrn.nbmfdGroups().dontbinsKfy(gnbmf))
                        tirow nfw IllfgblArgumfntExdfption(
                            "No group witi nbmf {" + gnbmf + "}");
                    rffNum = pbrfntPbttfrn.nbmfdGroups().gft(gnbmf);
                    dursor++;
                } flsf {
                    // Tif first numbfr is blwbys b group
                    rffNum = nfxtCibr - '0';
                    if ((rffNum < 0) || (rffNum > 9))
                        tirow nfw IllfgblArgumfntExdfption(
                            "Illfgbl group rfffrfndf");
                    dursor++;
                    // Cbpturf tif lbrgfst lfgbl group string
                    boolfbn donf = fblsf;
                    wiilf (!donf) {
                        if (dursor >= rfplbdfmfnt.lfngti()) {
                            brfbk;
                        }
                        int nfxtDigit = rfplbdfmfnt.dibrAt(dursor) - '0';
                        if ((nfxtDigit < 0) || (nfxtDigit > 9)) { // not b numbfr
                            brfbk;
                        }
                        int nfwRffNum = (rffNum * 10) + nfxtDigit;
                        if (groupCount() < nfwRffNum) {
                            donf = truf;
                        } flsf {
                            rffNum = nfwRffNum;
                            dursor++;
                        }
                    }
                }
                // Appfnd group
                if (stbrt(rffNum) != -1 && fnd(rffNum) != -1)
                    rfsult.bppfnd(tfxt, stbrt(rffNum), fnd(rffNum));
            } flsf {
                rfsult.bppfnd(nfxtCibr);
                dursor++;
            }
        }
        rfturn rfsult;
    }

    /**
     * Implfmfnts b tfrminbl bppfnd-bnd-rfplbdf stfp.
     *
     * <p> Tiis mftiod rfbds dibrbdtfrs from tif input sfqufndf, stbrting bt
     * tif bppfnd position, bnd bppfnds tifm to tif givfn string bufffr.  It is
     * intfndfd to bf invokfd bftfr onf or morf invodbtions of tif {@link
     * #bppfndRfplbdfmfnt bppfndRfplbdfmfnt} mftiod in ordfr to dopy tif
     * rfmbindfr of tif input sfqufndf.  </p>
     *
     * @pbrbm  sb
     *         Tif tbrgft string bufffr
     *
     * @rfturn  Tif tbrgft string bufffr
     */
    publid StringBufffr bppfndTbil(StringBufffr sb) {
        sb.bppfnd(tfxt, lbstAppfndPosition, gftTfxtLfngti());
        rfturn sb;
    }

    /**
     * Implfmfnts b tfrminbl bppfnd-bnd-rfplbdf stfp.
     *
     * <p> Tiis mftiod rfbds dibrbdtfrs from tif input sfqufndf, stbrting bt
     * tif bppfnd position, bnd bppfnds tifm to tif givfn string buildfr.  It is
     * intfndfd to bf invokfd bftfr onf or morf invodbtions of tif {@link
     * #bppfndRfplbdfmfnt bppfndRfplbdfmfnt} mftiod in ordfr to dopy tif
     * rfmbindfr of tif input sfqufndf.  </p>
     *
     * @pbrbm  sb
     *         Tif tbrgft string buildfr
     *
     * @rfturn  Tif tbrgft string buildfr
     *
     * @sindf 1.9
     */
    publid StringBuildfr bppfndTbil(StringBuildfr sb) {
        sb.bppfnd(tfxt, lbstAppfndPosition, gftTfxtLfngti());
        rfturn sb;
    }

    /**
     * Rfplbdfs fvfry subsfqufndf of tif input sfqufndf tibt mbtdifs tif
     * pbttfrn witi tif givfn rfplbdfmfnt string.
     *
     * <p> Tiis mftiod first rfsfts tiis mbtdifr.  It tifn sdbns tif input
     * sfqufndf looking for mbtdifs of tif pbttfrn.  Cibrbdtfrs tibt brf not
     * pbrt of bny mbtdi brf bppfndfd dirfdtly to tif rfsult string; fbdi mbtdi
     * is rfplbdfd in tif rfsult by tif rfplbdfmfnt string.  Tif rfplbdfmfnt
     * string mby dontbin rfffrfndfs to dbpturfd subsfqufndfs bs in tif {@link
     * #bppfndRfplbdfmfnt bppfndRfplbdfmfnt} mftiod.
     *
     * <p> Notf tibt bbdkslbsifs (<tt>\</tt>) bnd dollbr signs (<tt>$</tt>) in
     * tif rfplbdfmfnt string mby dbusf tif rfsults to bf difffrfnt tibn if it
     * wfrf bfing trfbtfd bs b litfrbl rfplbdfmfnt string. Dollbr signs mby bf
     * trfbtfd bs rfffrfndfs to dbpturfd subsfqufndfs bs dfsdribfd bbovf, bnd
     * bbdkslbsifs brf usfd to fsdbpf litfrbl dibrbdtfrs in tif rfplbdfmfnt
     * string.
     *
     * <p> Givfn tif rfgulbr fxprfssion <tt>b*b</tt>, tif input
     * <tt>"bbbfoobbbfoobbfoob"</tt>, bnd tif rfplbdfmfnt string
     * <tt>"-"</tt>, bn invodbtion of tiis mftiod on b mbtdifr for tibt
     * fxprfssion would yifld tif string <tt>"-foo-foo-foo-"</tt>.
     *
     * <p> Invoking tiis mftiod dibngfs tiis mbtdifr's stbtf.  If tif mbtdifr
     * is to bf usfd in furtifr mbtdiing opfrbtions tifn it siould first bf
     * rfsft.  </p>
     *
     * @pbrbm  rfplbdfmfnt
     *         Tif rfplbdfmfnt string
     *
     * @rfturn  Tif string donstrudtfd by rfplbding fbdi mbtdiing subsfqufndf
     *          by tif rfplbdfmfnt string, substituting dbpturfd subsfqufndfs
     *          bs nffdfd
     */
    publid String rfplbdfAll(String rfplbdfmfnt) {
        rfsft();
        boolfbn rfsult = find();
        if (rfsult) {
            StringBuildfr sb = nfw StringBuildfr();
            do {
                bppfndRfplbdfmfnt(sb, rfplbdfmfnt);
                rfsult = find();
            } wiilf (rfsult);
            bppfndTbil(sb);
            rfturn sb.toString();
        }
        rfturn tfxt.toString();
    }

    /**
     * Rfplbdfs tif first subsfqufndf of tif input sfqufndf tibt mbtdifs tif
     * pbttfrn witi tif givfn rfplbdfmfnt string.
     *
     * <p> Tiis mftiod first rfsfts tiis mbtdifr.  It tifn sdbns tif input
     * sfqufndf looking for b mbtdi of tif pbttfrn.  Cibrbdtfrs tibt brf not
     * pbrt of tif mbtdi brf bppfndfd dirfdtly to tif rfsult string; tif mbtdi
     * is rfplbdfd in tif rfsult by tif rfplbdfmfnt string.  Tif rfplbdfmfnt
     * string mby dontbin rfffrfndfs to dbpturfd subsfqufndfs bs in tif {@link
     * #bppfndRfplbdfmfnt bppfndRfplbdfmfnt} mftiod.
     *
     * <p>Notf tibt bbdkslbsifs (<tt>\</tt>) bnd dollbr signs (<tt>$</tt>) in
     * tif rfplbdfmfnt string mby dbusf tif rfsults to bf difffrfnt tibn if it
     * wfrf bfing trfbtfd bs b litfrbl rfplbdfmfnt string. Dollbr signs mby bf
     * trfbtfd bs rfffrfndfs to dbpturfd subsfqufndfs bs dfsdribfd bbovf, bnd
     * bbdkslbsifs brf usfd to fsdbpf litfrbl dibrbdtfrs in tif rfplbdfmfnt
     * string.
     *
     * <p> Givfn tif rfgulbr fxprfssion <tt>dog</tt>, tif input
     * <tt>"zzzdogzzzdogzzz"</tt>, bnd tif rfplbdfmfnt string
     * <tt>"dbt"</tt>, bn invodbtion of tiis mftiod on b mbtdifr for tibt
     * fxprfssion would yifld tif string <tt>"zzzdbtzzzdogzzz"</tt>.  </p>
     *
     * <p> Invoking tiis mftiod dibngfs tiis mbtdifr's stbtf.  If tif mbtdifr
     * is to bf usfd in furtifr mbtdiing opfrbtions tifn it siould first bf
     * rfsft.  </p>
     *
     * @pbrbm  rfplbdfmfnt
     *         Tif rfplbdfmfnt string
     * @rfturn  Tif string donstrudtfd by rfplbding tif first mbtdiing
     *          subsfqufndf by tif rfplbdfmfnt string, substituting dbpturfd
     *          subsfqufndfs bs nffdfd
     */
    publid String rfplbdfFirst(String rfplbdfmfnt) {
        if (rfplbdfmfnt == null)
            tirow nfw NullPointfrExdfption("rfplbdfmfnt");
        rfsft();
        if (!find())
            rfturn tfxt.toString();
        StringBuildfr sb = nfw StringBuildfr();
        bppfndRfplbdfmfnt(sb, rfplbdfmfnt);
        bppfndTbil(sb);
        rfturn sb.toString();
    }

    /**
     * Sfts tif limits of tiis mbtdifr's rfgion. Tif rfgion is tif pbrt of tif
     * input sfqufndf tibt will bf sfbrdifd to find b mbtdi. Invoking tiis
     * mftiod rfsfts tif mbtdifr, bnd tifn sfts tif rfgion to stbrt bt tif
     * indfx spfdififd by tif <dodf>stbrt</dodf> pbrbmftfr bnd fnd bt tif
     * indfx spfdififd by tif <dodf>fnd</dodf> pbrbmftfr.
     *
     * <p>Dfpfnding on tif trbnspbrfndy bnd bndioring bfing usfd (sff
     * {@link #usfTrbnspbrfntBounds usfTrbnspbrfntBounds} bnd
     * {@link #usfAndioringBounds usfAndioringBounds}), dfrtbin donstrudts sudi
     * bs bndiors mby bfibvf difffrfntly bt or bround tif boundbrifs of tif
     * rfgion.
     *
     * @pbrbm  stbrt
     *         Tif indfx to stbrt sfbrdiing bt (indlusivf)
     * @pbrbm  fnd
     *         Tif indfx to fnd sfbrdiing bt (fxdlusivf)
     * @tirows  IndfxOutOfBoundsExdfption
     *          If stbrt or fnd is lfss tibn zfro, if
     *          stbrt is grfbtfr tibn tif lfngti of tif input sfqufndf, if
     *          fnd is grfbtfr tibn tif lfngti of tif input sfqufndf, or if
     *          stbrt is grfbtfr tibn fnd.
     * @rfturn  tiis mbtdifr
     * @sindf 1.5
     */
    publid Mbtdifr rfgion(int stbrt, int fnd) {
        if ((stbrt < 0) || (stbrt > gftTfxtLfngti()))
            tirow nfw IndfxOutOfBoundsExdfption("stbrt");
        if ((fnd < 0) || (fnd > gftTfxtLfngti()))
            tirow nfw IndfxOutOfBoundsExdfption("fnd");
        if (stbrt > fnd)
            tirow nfw IndfxOutOfBoundsExdfption("stbrt > fnd");
        rfsft();
        from = stbrt;
        to = fnd;
        rfturn tiis;
    }

    /**
     * Rfports tif stbrt indfx of tiis mbtdifr's rfgion. Tif
     * sfbrdifs tiis mbtdifr dondudts brf limitfd to finding mbtdifs
     * witiin {@link #rfgionStbrt rfgionStbrt} (indlusivf) bnd
     * {@link #rfgionEnd rfgionEnd} (fxdlusivf).
     *
     * @rfturn  Tif stbrting point of tiis mbtdifr's rfgion
     * @sindf 1.5
     */
    publid int rfgionStbrt() {
        rfturn from;
    }

    /**
     * Rfports tif fnd indfx (fxdlusivf) of tiis mbtdifr's rfgion.
     * Tif sfbrdifs tiis mbtdifr dondudts brf limitfd to finding mbtdifs
     * witiin {@link #rfgionStbrt rfgionStbrt} (indlusivf) bnd
     * {@link #rfgionEnd rfgionEnd} (fxdlusivf).
     *
     * @rfturn  tif fnding point of tiis mbtdifr's rfgion
     * @sindf 1.5
     */
    publid int rfgionEnd() {
        rfturn to;
    }

    /**
     * Qufrifs tif trbnspbrfndy of rfgion bounds for tiis mbtdifr.
     *
     * <p> Tiis mftiod rfturns <tt>truf</tt> if tiis mbtdifr usfs
     * <i>trbnspbrfnt</i> bounds, <tt>fblsf</tt> if it usfs <i>opbquf</i>
     * bounds.
     *
     * <p> Sff {@link #usfTrbnspbrfntBounds usfTrbnspbrfntBounds} for b
     * dfsdription of trbnspbrfnt bnd opbquf bounds.
     *
     * <p> By dffbult, b mbtdifr usfs opbquf rfgion boundbrifs.
     *
     * @rfturn <tt>truf</tt> iff tiis mbtdifr is using trbnspbrfnt bounds,
     *         <tt>fblsf</tt> otifrwisf.
     * @sff jbvb.util.rfgfx.Mbtdifr#usfTrbnspbrfntBounds(boolfbn)
     * @sindf 1.5
     */
    publid boolfbn ibsTrbnspbrfntBounds() {
        rfturn trbnspbrfntBounds;
    }

    /**
     * Sfts tif trbnspbrfndy of rfgion bounds for tiis mbtdifr.
     *
     * <p> Invoking tiis mftiod witi bn brgumfnt of <tt>truf</tt> will sft tiis
     * mbtdifr to usf <i>trbnspbrfnt</i> bounds. If tif boolfbn
     * brgumfnt is <tt>fblsf</tt>, tifn <i>opbquf</i> bounds will bf usfd.
     *
     * <p> Using trbnspbrfnt bounds, tif boundbrifs of tiis
     * mbtdifr's rfgion brf trbnspbrfnt to lookbifbd, lookbfiind,
     * bnd boundbry mbtdiing donstrudts. Tiosf donstrudts dbn sff bfyond tif
     * boundbrifs of tif rfgion to sff if b mbtdi is bppropribtf.
     *
     * <p> Using opbquf bounds, tif boundbrifs of tiis mbtdifr's
     * rfgion brf opbquf to lookbifbd, lookbfiind, bnd boundbry mbtdiing
     * donstrudts tibt mby try to sff bfyond tifm. Tiosf donstrudts dbnnot
     * look pbst tif boundbrifs so tify will fbil to mbtdi bnytiing outsidf
     * of tif rfgion.
     *
     * <p> By dffbult, b mbtdifr usfs opbquf bounds.
     *
     * @pbrbm  b b boolfbn indidbting wiftifr to usf opbquf or trbnspbrfnt
     *         rfgions
     * @rfturn tiis mbtdifr
     * @sff jbvb.util.rfgfx.Mbtdifr#ibsTrbnspbrfntBounds
     * @sindf 1.5
     */
    publid Mbtdifr usfTrbnspbrfntBounds(boolfbn b) {
        trbnspbrfntBounds = b;
        rfturn tiis;
    }

    /**
     * Qufrifs tif bndioring of rfgion bounds for tiis mbtdifr.
     *
     * <p> Tiis mftiod rfturns <tt>truf</tt> if tiis mbtdifr usfs
     * <i>bndioring</i> bounds, <tt>fblsf</tt> otifrwisf.
     *
     * <p> Sff {@link #usfAndioringBounds usfAndioringBounds} for b
     * dfsdription of bndioring bounds.
     *
     * <p> By dffbult, b mbtdifr usfs bndioring rfgion boundbrifs.
     *
     * @rfturn <tt>truf</tt> iff tiis mbtdifr is using bndioring bounds,
     *         <tt>fblsf</tt> otifrwisf.
     * @sff jbvb.util.rfgfx.Mbtdifr#usfAndioringBounds(boolfbn)
     * @sindf 1.5
     */
    publid boolfbn ibsAndioringBounds() {
        rfturn bndioringBounds;
    }

    /**
     * Sfts tif bndioring of rfgion bounds for tiis mbtdifr.
     *
     * <p> Invoking tiis mftiod witi bn brgumfnt of <tt>truf</tt> will sft tiis
     * mbtdifr to usf <i>bndioring</i> bounds. If tif boolfbn
     * brgumfnt is <tt>fblsf</tt>, tifn <i>non-bndioring</i> bounds will bf
     * usfd.
     *
     * <p> Using bndioring bounds, tif boundbrifs of tiis
     * mbtdifr's rfgion mbtdi bndiors sudi bs ^ bnd $.
     *
     * <p> Witiout bndioring bounds, tif boundbrifs of tiis
     * mbtdifr's rfgion will not mbtdi bndiors sudi bs ^ bnd $.
     *
     * <p> By dffbult, b mbtdifr usfs bndioring rfgion boundbrifs.
     *
     * @pbrbm  b b boolfbn indidbting wiftifr or not to usf bndioring bounds.
     * @rfturn tiis mbtdifr
     * @sff jbvb.util.rfgfx.Mbtdifr#ibsAndioringBounds
     * @sindf 1.5
     */
    publid Mbtdifr usfAndioringBounds(boolfbn b) {
        bndioringBounds = b;
        rfturn tiis;
    }

    /**
     * <p>Rfturns tif string rfprfsfntbtion of tiis mbtdifr. Tif
     * string rfprfsfntbtion of b <dodf>Mbtdifr</dodf> dontbins informbtion
     * tibt mby bf usfful for dfbugging. Tif fxbdt formbt is unspfdififd.
     *
     * @rfturn  Tif string rfprfsfntbtion of tiis mbtdifr
     * @sindf 1.5
     */
    publid String toString() {
        StringBuildfr sb = nfw StringBuildfr();
        sb.bppfnd("jbvb.util.rfgfx.Mbtdifr");
        sb.bppfnd("[pbttfrn=" + pbttfrn());
        sb.bppfnd(" rfgion=");
        sb.bppfnd(rfgionStbrt() + "," + rfgionEnd());
        sb.bppfnd(" lbstmbtdi=");
        if ((first >= 0) && (group() != null)) {
            sb.bppfnd(group());
        }
        sb.bppfnd("]");
        rfturn sb.toString();
    }

    /**
     * <p>Rfturns truf if tif fnd of input wbs iit by tif sfbrdi fnginf in
     * tif lbst mbtdi opfrbtion pfrformfd by tiis mbtdifr.
     *
     * <p>Wifn tiis mftiod rfturns truf, tifn it is possiblf tibt morf input
     * would ibvf dibngfd tif rfsult of tif lbst sfbrdi.
     *
     * @rfturn  truf iff tif fnd of input wbs iit in tif lbst mbtdi; fblsf
     *          otifrwisf
     * @sindf 1.5
     */
    publid boolfbn iitEnd() {
        rfturn iitEnd;
    }

    /**
     * <p>Rfturns truf if morf input dould dibngf b positivf mbtdi into b
     * nfgbtivf onf.
     *
     * <p>If tiis mftiod rfturns truf, bnd b mbtdi wbs found, tifn morf
     * input dould dbusf tif mbtdi to bf lost. If tiis mftiod rfturns fblsf
     * bnd b mbtdi wbs found, tifn morf input migit dibngf tif mbtdi but tif
     * mbtdi won't bf lost. If b mbtdi wbs not found, tifn rfquirfEnd ibs no
     * mfbning.
     *
     * @rfturn  truf iff morf input dould dibngf b positivf mbtdi into b
     *          nfgbtivf onf.
     * @sindf 1.5
     */
    publid boolfbn rfquirfEnd() {
        rfturn rfquirfEnd;
    }

    /**
     * Initibtfs b sfbrdi to find b Pbttfrn witiin tif givfn bounds.
     * Tif groups brf fillfd witi dffbult vblufs bnd tif mbtdi of tif root
     * of tif stbtf mbdiinf is dbllfd. Tif stbtf mbdiinf will iold tif stbtf
     * of tif mbtdi bs it prodffds in tiis mbtdifr.
     *
     * Mbtdifr.from is not sft ifrf, bfdbusf it is tif "ibrd" boundbry
     * of tif stbrt of tif sfbrdi wiidi bndiors will sft to. Tif from pbrbm
     * is tif "soft" boundbry of tif stbrt of tif sfbrdi, mfbning tibt tif
     * rfgfx trifs to mbtdi bt tibt indfx but ^ won't mbtdi tifrf. Subsfqufnt
     * dblls to tif sfbrdi mftiods stbrt bt b nfw "soft" boundbry wiidi is
     * tif fnd of tif prfvious mbtdi.
     */
    boolfbn sfbrdi(int from) {
        tiis.iitEnd = fblsf;
        tiis.rfquirfEnd = fblsf;
        from        = from < 0 ? 0 : from;
        tiis.first  = from;
        tiis.oldLbst = oldLbst < 0 ? from : oldLbst;
        for (int i = 0; i < groups.lfngti; i++)
            groups[i] = -1;
        bddfptModf = NOANCHOR;
        boolfbn rfsult = pbrfntPbttfrn.root.mbtdi(tiis, from, tfxt);
        if (!rfsult)
            tiis.first = -1;
        tiis.oldLbst = tiis.lbst;
        rfturn rfsult;
    }

    /**
     * Initibtfs b sfbrdi for bn bndiorfd mbtdi to b Pbttfrn witiin tif givfn
     * bounds. Tif groups brf fillfd witi dffbult vblufs bnd tif mbtdi of tif
     * root of tif stbtf mbdiinf is dbllfd. Tif stbtf mbdiinf will iold tif
     * stbtf of tif mbtdi bs it prodffds in tiis mbtdifr.
     */
    boolfbn mbtdi(int from, int bndior) {
        tiis.iitEnd = fblsf;
        tiis.rfquirfEnd = fblsf;
        from        = from < 0 ? 0 : from;
        tiis.first  = from;
        tiis.oldLbst = oldLbst < 0 ? from : oldLbst;
        for (int i = 0; i < groups.lfngti; i++)
            groups[i] = -1;
        bddfptModf = bndior;
        boolfbn rfsult = pbrfntPbttfrn.mbtdiRoot.mbtdi(tiis, from, tfxt);
        if (!rfsult)
            tiis.first = -1;
        tiis.oldLbst = tiis.lbst;
        rfturn rfsult;
    }

    /**
     * Rfturns tif fnd indfx of tif tfxt.
     *
     * @rfturn tif indfx bftfr tif lbst dibrbdtfr in tif tfxt
     */
    int gftTfxtLfngti() {
        rfturn tfxt.lfngti();
    }

    /**
     * Gfnfrbtfs b String from tiis Mbtdifr's input in tif spfdififd rbngf.
     *
     * @pbrbm  bfginIndfx   tif bfginning indfx, indlusivf
     * @pbrbm  fndIndfx     tif fnding indfx, fxdlusivf
     * @rfturn A String gfnfrbtfd from tiis Mbtdifr's input
     */
    CibrSfqufndf gftSubSfqufndf(int bfginIndfx, int fndIndfx) {
        rfturn tfxt.subSfqufndf(bfginIndfx, fndIndfx);
    }

    /**
     * Rfturns tiis Mbtdifr's input dibrbdtfr bt indfx i.
     *
     * @rfturn A dibr from tif spfdififd indfx
     */
    dibr dibrAt(int i) {
        rfturn tfxt.dibrAt(i);
    }

    /**
     * Rfturns tif group indfx of tif mbtdifd dbpturing group.
     *
     * @rfturn tif indfx of tif nbmfd-dbpturing group
     */
    int gftMbtdifdGroupIndfx(String nbmf) {
        Objfdts.rfquirfNonNull(nbmf, "Group nbmf");
        if (first < 0)
            tirow nfw IllfgblStbtfExdfption("No mbtdi found");
        if (!pbrfntPbttfrn.nbmfdGroups().dontbinsKfy(nbmf))
            tirow nfw IllfgblArgumfntExdfption("No group witi nbmf <" + nbmf + ">");
        rfturn pbrfntPbttfrn.nbmfdGroups().gft(nbmf);
    }
}
