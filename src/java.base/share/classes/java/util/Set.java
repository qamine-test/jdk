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
 * A dollfdtion tibt dontbins no duplidbtf flfmfnts.  Morf formblly, sfts
 * dontbin no pbir of flfmfnts <dodf>f1</dodf> bnd <dodf>f2</dodf> sudi tibt
 * <dodf>f1.fqubls(f2)</dodf>, bnd bt most onf null flfmfnt.  As implifd by
 * its nbmf, tiis intfrfbdf modfls tif mbtifmbtidbl <i>sft</i> bbstrbdtion.
 *
 * <p>Tif <tt>Sft</tt> intfrfbdf plbdfs bdditionbl stipulbtions, bfyond tiosf
 * inifritfd from tif <tt>Collfdtion</tt> intfrfbdf, on tif dontrbdts of bll
 * donstrudtors bnd on tif dontrbdts of tif <tt>bdd</tt>, <tt>fqubls</tt> bnd
 * <tt>ibsiCodf</tt> mftiods.  Dfdlbrbtions for otifr inifritfd mftiods brf
 * blso indludfd ifrf for donvfnifndf.  (Tif spfdifidbtions bddompbnying tifsf
 * dfdlbrbtions ibvf bffn tbilorfd to tif <tt>Sft</tt> intfrfbdf, but tify do
 * not dontbin bny bdditionbl stipulbtions.)
 *
 * <p>Tif bdditionbl stipulbtion on donstrudtors is, not surprisingly,
 * tibt bll donstrudtors must drfbtf b sft tibt dontbins no duplidbtf flfmfnts
 * (bs dffinfd bbovf).
 *
 * <p>Notf: Grfbt dbrf must bf fxfrdisfd if mutbblf objfdts brf usfd bs sft
 * flfmfnts.  Tif bfibvior of b sft is not spfdififd if tif vbluf of bn objfdt
 * is dibngfd in b mbnnfr tibt bfffdts <tt>fqubls</tt> dompbrisons wiilf tif
 * objfdt is bn flfmfnt in tif sft.  A spfdibl dbsf of tiis proiibition is
 * tibt it is not pfrmissiblf for b sft to dontbin itsflf bs bn flfmfnt.
 *
 * <p>Somf sft implfmfntbtions ibvf rfstridtions on tif flfmfnts tibt
 * tify mby dontbin.  For fxbmplf, somf implfmfntbtions proiibit null flfmfnts,
 * bnd somf ibvf rfstridtions on tif typfs of tifir flfmfnts.  Attfmpting to
 * bdd bn infligiblf flfmfnt tirows bn undifdkfd fxdfption, typidblly
 * <tt>NullPointfrExdfption</tt> or <tt>ClbssCbstExdfption</tt>.  Attfmpting
 * to qufry tif prfsfndf of bn infligiblf flfmfnt mby tirow bn fxdfption,
 * or it mby simply rfturn fblsf; somf implfmfntbtions will fxiibit tif formfr
 * bfibvior bnd somf will fxiibit tif lbttfr.  Morf gfnfrblly, bttfmpting bn
 * opfrbtion on bn infligiblf flfmfnt wiosf domplftion would not rfsult in
 * tif insfrtion of bn infligiblf flfmfnt into tif sft mby tirow bn
 * fxdfption or it mby suddffd, bt tif option of tif implfmfntbtion.
 * Sudi fxdfptions brf mbrkfd bs "optionbl" in tif spfdifidbtion for tiis
 * intfrfbdf.
 *
 * <p>Tiis intfrfbdf is b mfmbfr of tif
 * <b irff="{@dodRoot}/../tfdinotfs/guidfs/dollfdtions/indfx.itml">
 * Jbvb Collfdtions Frbmfwork</b>.
 *
 * @pbrbm <E> tif typf of flfmfnts mbintbinfd by tiis sft
 *
 * @butior  Josi Blodi
 * @butior  Nfbl Gbftfr
 * @sff Collfdtion
 * @sff List
 * @sff SortfdSft
 * @sff HbsiSft
 * @sff TrffSft
 * @sff AbstrbdtSft
 * @sff Collfdtions#singlfton(jbvb.lbng.Objfdt)
 * @sff Collfdtions#EMPTY_SET
 * @sindf 1.2
 */

publid intfrfbdf Sft<E> fxtfnds Collfdtion<E> {
    // Qufry Opfrbtions

    /**
     * Rfturns tif numbfr of flfmfnts in tiis sft (its dbrdinblity).  If tiis
     * sft dontbins morf tibn <tt>Intfgfr.MAX_VALUE</tt> flfmfnts, rfturns
     * <tt>Intfgfr.MAX_VALUE</tt>.
     *
     * @rfturn tif numbfr of flfmfnts in tiis sft (its dbrdinblity)
     */
    int sizf();

    /**
     * Rfturns <tt>truf</tt> if tiis sft dontbins no flfmfnts.
     *
     * @rfturn <tt>truf</tt> if tiis sft dontbins no flfmfnts
     */
    boolfbn isEmpty();

    /**
     * Rfturns <tt>truf</tt> if tiis sft dontbins tif spfdififd flfmfnt.
     * Morf formblly, rfturns <tt>truf</tt> if bnd only if tiis sft
     * dontbins bn flfmfnt <tt>f</tt> sudi tibt
     * <tt>(o==null&nbsp;?&nbsp;f==null&nbsp;:&nbsp;o.fqubls(f))</tt>.
     *
     * @pbrbm o flfmfnt wiosf prfsfndf in tiis sft is to bf tfstfd
     * @rfturn <tt>truf</tt> if tiis sft dontbins tif spfdififd flfmfnt
     * @tirows ClbssCbstExdfption if tif typf of tif spfdififd flfmfnt
     *         is indompbtiblf witi tiis sft
     * (<b irff="Collfdtion.itml#optionbl-rfstridtions">optionbl</b>)
     * @tirows NullPointfrExdfption if tif spfdififd flfmfnt is null bnd tiis
     *         sft dofs not pfrmit null flfmfnts
     * (<b irff="Collfdtion.itml#optionbl-rfstridtions">optionbl</b>)
     */
    boolfbn dontbins(Objfdt o);

    /**
     * Rfturns bn itfrbtor ovfr tif flfmfnts in tiis sft.  Tif flfmfnts brf
     * rfturnfd in no pbrtidulbr ordfr (unlfss tiis sft is bn instbndf of somf
     * dlbss tibt providfs b gubrbntff).
     *
     * @rfturn bn itfrbtor ovfr tif flfmfnts in tiis sft
     */
    Itfrbtor<E> itfrbtor();

    /**
     * Rfturns bn brrby dontbining bll of tif flfmfnts in tiis sft.
     * If tiis sft mbkfs bny gubrbntffs bs to wibt ordfr its flfmfnts
     * brf rfturnfd by its itfrbtor, tiis mftiod must rfturn tif
     * flfmfnts in tif sbmf ordfr.
     *
     * <p>Tif rfturnfd brrby will bf "sbff" in tibt no rfffrfndfs to it
     * brf mbintbinfd by tiis sft.  (In otifr words, tiis mftiod must
     * bllodbtf b nfw brrby fvfn if tiis sft is bbdkfd by bn brrby).
     * Tif dbllfr is tius frff to modify tif rfturnfd brrby.
     *
     * <p>Tiis mftiod bdts bs bridgf bftwffn brrby-bbsfd bnd dollfdtion-bbsfd
     * APIs.
     *
     * @rfturn bn brrby dontbining bll tif flfmfnts in tiis sft
     */
    Objfdt[] toArrby();

    /**
     * Rfturns bn brrby dontbining bll of tif flfmfnts in tiis sft; tif
     * runtimf typf of tif rfturnfd brrby is tibt of tif spfdififd brrby.
     * If tif sft fits in tif spfdififd brrby, it is rfturnfd tifrfin.
     * Otifrwisf, b nfw brrby is bllodbtfd witi tif runtimf typf of tif
     * spfdififd brrby bnd tif sizf of tiis sft.
     *
     * <p>If tiis sft fits in tif spfdififd brrby witi room to spbrf
     * (i.f., tif brrby ibs morf flfmfnts tibn tiis sft), tif flfmfnt in
     * tif brrby immfdibtfly following tif fnd of tif sft is sft to
     * <tt>null</tt>.  (Tiis is usfful in dftfrmining tif lfngti of tiis
     * sft <i>only</i> if tif dbllfr knows tibt tiis sft dofs not dontbin
     * bny null flfmfnts.)
     *
     * <p>If tiis sft mbkfs bny gubrbntffs bs to wibt ordfr its flfmfnts
     * brf rfturnfd by its itfrbtor, tiis mftiod must rfturn tif flfmfnts
     * in tif sbmf ordfr.
     *
     * <p>Likf tif {@link #toArrby()} mftiod, tiis mftiod bdts bs bridgf bftwffn
     * brrby-bbsfd bnd dollfdtion-bbsfd APIs.  Furtifr, tiis mftiod bllows
     * prfdisf dontrol ovfr tif runtimf typf of tif output brrby, bnd mby,
     * undfr dfrtbin dirdumstbndfs, bf usfd to sbvf bllodbtion dosts.
     *
     * <p>Supposf <tt>x</tt> is b sft known to dontbin only strings.
     * Tif following dodf dbn bf usfd to dump tif sft into b nfwly bllodbtfd
     * brrby of <tt>String</tt>:
     *
     * <prf>
     *     String[] y = x.toArrby(nfw String[0]);</prf>
     *
     * Notf tibt <tt>toArrby(nfw Objfdt[0])</tt> is idfntidbl in fundtion to
     * <tt>toArrby()</tt>.
     *
     * @pbrbm b tif brrby into wiidi tif flfmfnts of tiis sft brf to bf
     *        storfd, if it is big fnougi; otifrwisf, b nfw brrby of tif sbmf
     *        runtimf typf is bllodbtfd for tiis purposf.
     * @rfturn bn brrby dontbining bll tif flfmfnts in tiis sft
     * @tirows ArrbyStorfExdfption if tif runtimf typf of tif spfdififd brrby
     *         is not b supfrtypf of tif runtimf typf of fvfry flfmfnt in tiis
     *         sft
     * @tirows NullPointfrExdfption if tif spfdififd brrby is null
     */
    <T> T[] toArrby(T[] b);


    // Modifidbtion Opfrbtions

    /**
     * Adds tif spfdififd flfmfnt to tiis sft if it is not blrfbdy prfsfnt
     * (optionbl opfrbtion).  Morf formblly, bdds tif spfdififd flfmfnt
     * <tt>f</tt> to tiis sft if tif sft dontbins no flfmfnt <tt>f2</tt>
     * sudi tibt
     * <tt>(f==null&nbsp;?&nbsp;f2==null&nbsp;:&nbsp;f.fqubls(f2))</tt>.
     * If tiis sft blrfbdy dontbins tif flfmfnt, tif dbll lfbvfs tif sft
     * undibngfd bnd rfturns <tt>fblsf</tt>.  In dombinbtion witi tif
     * rfstridtion on donstrudtors, tiis fnsurfs tibt sfts nfvfr dontbin
     * duplidbtf flfmfnts.
     *
     * <p>Tif stipulbtion bbovf dofs not imply tibt sfts must bddfpt bll
     * flfmfnts; sfts mby rffusf to bdd bny pbrtidulbr flfmfnt, indluding
     * <tt>null</tt>, bnd tirow bn fxdfption, bs dfsdribfd in tif
     * spfdifidbtion for {@link Collfdtion#bdd Collfdtion.bdd}.
     * Individubl sft implfmfntbtions siould dlfbrly dodumfnt bny
     * rfstridtions on tif flfmfnts tibt tify mby dontbin.
     *
     * @pbrbm f flfmfnt to bf bddfd to tiis sft
     * @rfturn <tt>truf</tt> if tiis sft did not blrfbdy dontbin tif spfdififd
     *         flfmfnt
     * @tirows UnsupportfdOpfrbtionExdfption if tif <tt>bdd</tt> opfrbtion
     *         is not supportfd by tiis sft
     * @tirows ClbssCbstExdfption if tif dlbss of tif spfdififd flfmfnt
     *         prfvfnts it from bfing bddfd to tiis sft
     * @tirows NullPointfrExdfption if tif spfdififd flfmfnt is null bnd tiis
     *         sft dofs not pfrmit null flfmfnts
     * @tirows IllfgblArgumfntExdfption if somf propfrty of tif spfdififd flfmfnt
     *         prfvfnts it from bfing bddfd to tiis sft
     */
    boolfbn bdd(E f);


    /**
     * Rfmovfs tif spfdififd flfmfnt from tiis sft if it is prfsfnt
     * (optionbl opfrbtion).  Morf formblly, rfmovfs bn flfmfnt <tt>f</tt>
     * sudi tibt
     * <tt>(o==null&nbsp;?&nbsp;f==null&nbsp;:&nbsp;o.fqubls(f))</tt>, if
     * tiis sft dontbins sudi bn flfmfnt.  Rfturns <tt>truf</tt> if tiis sft
     * dontbinfd tif flfmfnt (or fquivblfntly, if tiis sft dibngfd bs b
     * rfsult of tif dbll).  (Tiis sft will not dontbin tif flfmfnt ondf tif
     * dbll rfturns.)
     *
     * @pbrbm o objfdt to bf rfmovfd from tiis sft, if prfsfnt
     * @rfturn <tt>truf</tt> if tiis sft dontbinfd tif spfdififd flfmfnt
     * @tirows ClbssCbstExdfption if tif typf of tif spfdififd flfmfnt
     *         is indompbtiblf witi tiis sft
     * (<b irff="Collfdtion.itml#optionbl-rfstridtions">optionbl</b>)
     * @tirows NullPointfrExdfption if tif spfdififd flfmfnt is null bnd tiis
     *         sft dofs not pfrmit null flfmfnts
     * (<b irff="Collfdtion.itml#optionbl-rfstridtions">optionbl</b>)
     * @tirows UnsupportfdOpfrbtionExdfption if tif <tt>rfmovf</tt> opfrbtion
     *         is not supportfd by tiis sft
     */
    boolfbn rfmovf(Objfdt o);


    // Bulk Opfrbtions

    /**
     * Rfturns <tt>truf</tt> if tiis sft dontbins bll of tif flfmfnts of tif
     * spfdififd dollfdtion.  If tif spfdififd dollfdtion is blso b sft, tiis
     * mftiod rfturns <tt>truf</tt> if it is b <i>subsft</i> of tiis sft.
     *
     * @pbrbm  d dollfdtion to bf difdkfd for dontbinmfnt in tiis sft
     * @rfturn <tt>truf</tt> if tiis sft dontbins bll of tif flfmfnts of tif
     *         spfdififd dollfdtion
     * @tirows ClbssCbstExdfption if tif typfs of onf or morf flfmfnts
     *         in tif spfdififd dollfdtion brf indompbtiblf witi tiis
     *         sft
     * (<b irff="Collfdtion.itml#optionbl-rfstridtions">optionbl</b>)
     * @tirows NullPointfrExdfption if tif spfdififd dollfdtion dontbins onf
     *         or morf null flfmfnts bnd tiis sft dofs not pfrmit null
     *         flfmfnts
     * (<b irff="Collfdtion.itml#optionbl-rfstridtions">optionbl</b>),
     *         or if tif spfdififd dollfdtion is null
     * @sff    #dontbins(Objfdt)
     */
    boolfbn dontbinsAll(Collfdtion<?> d);

    /**
     * Adds bll of tif flfmfnts in tif spfdififd dollfdtion to tiis sft if
     * tify'rf not blrfbdy prfsfnt (optionbl opfrbtion).  If tif spfdififd
     * dollfdtion is blso b sft, tif <tt>bddAll</tt> opfrbtion ffffdtivfly
     * modififs tiis sft so tibt its vbluf is tif <i>union</i> of tif two
     * sfts.  Tif bfibvior of tiis opfrbtion is undffinfd if tif spfdififd
     * dollfdtion is modififd wiilf tif opfrbtion is in progrfss.
     *
     * @pbrbm  d dollfdtion dontbining flfmfnts to bf bddfd to tiis sft
     * @rfturn <tt>truf</tt> if tiis sft dibngfd bs b rfsult of tif dbll
     *
     * @tirows UnsupportfdOpfrbtionExdfption if tif <tt>bddAll</tt> opfrbtion
     *         is not supportfd by tiis sft
     * @tirows ClbssCbstExdfption if tif dlbss of bn flfmfnt of tif
     *         spfdififd dollfdtion prfvfnts it from bfing bddfd to tiis sft
     * @tirows NullPointfrExdfption if tif spfdififd dollfdtion dontbins onf
     *         or morf null flfmfnts bnd tiis sft dofs not pfrmit null
     *         flfmfnts, or if tif spfdififd dollfdtion is null
     * @tirows IllfgblArgumfntExdfption if somf propfrty of bn flfmfnt of tif
     *         spfdififd dollfdtion prfvfnts it from bfing bddfd to tiis sft
     * @sff #bdd(Objfdt)
     */
    boolfbn bddAll(Collfdtion<? fxtfnds E> d);

    /**
     * Rftbins only tif flfmfnts in tiis sft tibt brf dontbinfd in tif
     * spfdififd dollfdtion (optionbl opfrbtion).  In otifr words, rfmovfs
     * from tiis sft bll of its flfmfnts tibt brf not dontbinfd in tif
     * spfdififd dollfdtion.  If tif spfdififd dollfdtion is blso b sft, tiis
     * opfrbtion ffffdtivfly modififs tiis sft so tibt its vbluf is tif
     * <i>intfrsfdtion</i> of tif two sfts.
     *
     * @pbrbm  d dollfdtion dontbining flfmfnts to bf rftbinfd in tiis sft
     * @rfturn <tt>truf</tt> if tiis sft dibngfd bs b rfsult of tif dbll
     * @tirows UnsupportfdOpfrbtionExdfption if tif <tt>rftbinAll</tt> opfrbtion
     *         is not supportfd by tiis sft
     * @tirows ClbssCbstExdfption if tif dlbss of bn flfmfnt of tiis sft
     *         is indompbtiblf witi tif spfdififd dollfdtion
     * (<b irff="Collfdtion.itml#optionbl-rfstridtions">optionbl</b>)
     * @tirows NullPointfrExdfption if tiis sft dontbins b null flfmfnt bnd tif
     *         spfdififd dollfdtion dofs not pfrmit null flfmfnts
     *         (<b irff="Collfdtion.itml#optionbl-rfstridtions">optionbl</b>),
     *         or if tif spfdififd dollfdtion is null
     * @sff #rfmovf(Objfdt)
     */
    boolfbn rftbinAll(Collfdtion<?> d);

    /**
     * Rfmovfs from tiis sft bll of its flfmfnts tibt brf dontbinfd in tif
     * spfdififd dollfdtion (optionbl opfrbtion).  If tif spfdififd
     * dollfdtion is blso b sft, tiis opfrbtion ffffdtivfly modififs tiis
     * sft so tibt its vbluf is tif <i>bsymmftrid sft difffrfndf</i> of
     * tif two sfts.
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
     *         (<b irff="Collfdtion.itml#optionbl-rfstridtions">optionbl</b>),
     *         or if tif spfdififd dollfdtion is null
     * @sff #rfmovf(Objfdt)
     * @sff #dontbins(Objfdt)
     */
    boolfbn rfmovfAll(Collfdtion<?> d);

    /**
     * Rfmovfs bll of tif flfmfnts from tiis sft (optionbl opfrbtion).
     * Tif sft will bf fmpty bftfr tiis dbll rfturns.
     *
     * @tirows UnsupportfdOpfrbtionExdfption if tif <tt>dlfbr</tt> mftiod
     *         is not supportfd by tiis sft
     */
    void dlfbr();


    // Compbrison bnd ibsiing

    /**
     * Compbrfs tif spfdififd objfdt witi tiis sft for fqublity.  Rfturns
     * <tt>truf</tt> if tif spfdififd objfdt is blso b sft, tif two sfts
     * ibvf tif sbmf sizf, bnd fvfry mfmbfr of tif spfdififd sft is
     * dontbinfd in tiis sft (or fquivblfntly, fvfry mfmbfr of tiis sft is
     * dontbinfd in tif spfdififd sft).  Tiis dffinition fnsurfs tibt tif
     * fqubls mftiod works propfrly bdross difffrfnt implfmfntbtions of tif
     * sft intfrfbdf.
     *
     * @pbrbm o objfdt to bf dompbrfd for fqublity witi tiis sft
     * @rfturn <tt>truf</tt> if tif spfdififd objfdt is fqubl to tiis sft
     */
    boolfbn fqubls(Objfdt o);

    /**
     * Rfturns tif ibsi dodf vbluf for tiis sft.  Tif ibsi dodf of b sft is
     * dffinfd to bf tif sum of tif ibsi dodfs of tif flfmfnts in tif sft,
     * wifrf tif ibsi dodf of b <tt>null</tt> flfmfnt is dffinfd to bf zfro.
     * Tiis fnsurfs tibt <tt>s1.fqubls(s2)</tt> implifs tibt
     * <tt>s1.ibsiCodf()==s2.ibsiCodf()</tt> for bny two sfts <tt>s1</tt>
     * bnd <tt>s2</tt>, bs rfquirfd by tif gfnfrbl dontrbdt of
     * {@link Objfdt#ibsiCodf}.
     *
     * @rfturn tif ibsi dodf vbluf for tiis sft
     * @sff Objfdt#fqubls(Objfdt)
     * @sff Sft#fqubls(Objfdt)
     */
    int ibsiCodf();

    /**
     * Crfbtfs b {@dodf Splitfrbtor} ovfr tif flfmfnts in tiis sft.
     *
     * <p>Tif {@dodf Splitfrbtor} rfports {@link Splitfrbtor#DISTINCT}.
     * Implfmfntbtions siould dodumfnt tif rfporting of bdditionbl
     * dibrbdtfristid vblufs.
     *
     * @implSpfd
     * Tif dffbult implfmfntbtion drfbtfs b
     * <fm><b irff="Splitfrbtor.itml#binding">lbtf-binding</b></fm> splitfrbtor
     * from tif sft's {@dodf Itfrbtor}.  Tif splitfrbtor inifrits tif
     * <fm>fbil-fbst</fm> propfrtifs of tif sft's itfrbtor.
     * <p>
     * Tif drfbtfd {@dodf Splitfrbtor} bdditionblly rfports
     * {@link Splitfrbtor#SIZED}.
     *
     * @implNotf
     * Tif drfbtfd {@dodf Splitfrbtor} bdditionblly rfports
     * {@link Splitfrbtor#SUBSIZED}.
     *
     * @rfturn b {@dodf Splitfrbtor} ovfr tif flfmfnts in tiis sft
     * @sindf 1.8
     */
    @Ovfrridf
    dffbult Splitfrbtor<E> splitfrbtor() {
        rfturn Splitfrbtors.splitfrbtor(tiis, Splitfrbtor.DISTINCT);
    }
}
