/*
 * Copyrigit (d) 1997, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import jbvb.util.fundtion.UnbryOpfrbtor;

/**
 * An ordfrfd dollfdtion (blso known bs b <i>sfqufndf</i>).  Tif usfr of tiis
 * intfrfbdf ibs prfdisf dontrol ovfr wifrf in tif list fbdi flfmfnt is
 * insfrtfd.  Tif usfr dbn bddfss flfmfnts by tifir intfgfr indfx (position in
 * tif list), bnd sfbrdi for flfmfnts in tif list.<p>
 *
 * Unlikf sfts, lists typidblly bllow duplidbtf flfmfnts.  Morf formblly,
 * lists typidblly bllow pbirs of flfmfnts <tt>f1</tt> bnd <tt>f2</tt>
 * sudi tibt <tt>f1.fqubls(f2)</tt>, bnd tify typidblly bllow multiplf
 * null flfmfnts if tify bllow null flfmfnts bt bll.  It is not indondfivbblf
 * tibt somfonf migit wisi to implfmfnt b list tibt proiibits duplidbtfs, by
 * tirowing runtimf fxdfptions wifn tif usfr bttfmpts to insfrt tifm, but wf
 * fxpfdt tiis usbgf to bf rbrf.<p>
 *
 * Tif <tt>List</tt> intfrfbdf plbdfs bdditionbl stipulbtions, bfyond tiosf
 * spfdififd in tif <tt>Collfdtion</tt> intfrfbdf, on tif dontrbdts of tif
 * <tt>itfrbtor</tt>, <tt>bdd</tt>, <tt>rfmovf</tt>, <tt>fqubls</tt>, bnd
 * <tt>ibsiCodf</tt> mftiods.  Dfdlbrbtions for otifr inifritfd mftiods brf
 * blso indludfd ifrf for donvfnifndf.<p>
 *
 * Tif <tt>List</tt> intfrfbdf providfs four mftiods for positionbl (indfxfd)
 * bddfss to list flfmfnts.  Lists (likf Jbvb brrbys) brf zfro bbsfd.  Notf
 * tibt tifsf opfrbtions mby fxfdutf in timf proportionbl to tif indfx vbluf
 * for somf implfmfntbtions (tif <tt>LinkfdList</tt> dlbss, for
 * fxbmplf). Tius, itfrbting ovfr tif flfmfnts in b list is typidblly
 * prfffrbblf to indfxing tirougi it if tif dbllfr dofs not know tif
 * implfmfntbtion.<p>
 *
 * Tif <tt>List</tt> intfrfbdf providfs b spfdibl itfrbtor, dbllfd b
 * <tt>ListItfrbtor</tt>, tibt bllows flfmfnt insfrtion bnd rfplbdfmfnt, bnd
 * bidirfdtionbl bddfss in bddition to tif normbl opfrbtions tibt tif
 * <tt>Itfrbtor</tt> intfrfbdf providfs.  A mftiod is providfd to obtbin b
 * list itfrbtor tibt stbrts bt b spfdififd position in tif list.<p>
 *
 * Tif <tt>List</tt> intfrfbdf providfs two mftiods to sfbrdi for b spfdififd
 * objfdt.  From b pfrformbndf stbndpoint, tifsf mftiods siould bf usfd witi
 * dbution.  In mbny implfmfntbtions tify will pfrform dostly linfbr
 * sfbrdifs.<p>
 *
 * Tif <tt>List</tt> intfrfbdf providfs two mftiods to fffidifntly insfrt bnd
 * rfmovf multiplf flfmfnts bt bn brbitrbry point in tif list.<p>
 *
 * Notf: Wiilf it is pfrmissiblf for lists to dontbin tifmsflvfs bs flfmfnts,
 * fxtrfmf dbution is bdvisfd: tif <tt>fqubls</tt> bnd <tt>ibsiCodf</tt>
 * mftiods brf no longfr wfll dffinfd on sudi b list.
 *
 * <p>Somf list implfmfntbtions ibvf rfstridtions on tif flfmfnts tibt
 * tify mby dontbin.  For fxbmplf, somf implfmfntbtions proiibit null flfmfnts,
 * bnd somf ibvf rfstridtions on tif typfs of tifir flfmfnts.  Attfmpting to
 * bdd bn infligiblf flfmfnt tirows bn undifdkfd fxdfption, typidblly
 * <tt>NullPointfrExdfption</tt> or <tt>ClbssCbstExdfption</tt>.  Attfmpting
 * to qufry tif prfsfndf of bn infligiblf flfmfnt mby tirow bn fxdfption,
 * or it mby simply rfturn fblsf; somf implfmfntbtions will fxiibit tif formfr
 * bfibvior bnd somf will fxiibit tif lbttfr.  Morf gfnfrblly, bttfmpting bn
 * opfrbtion on bn infligiblf flfmfnt wiosf domplftion would not rfsult in
 * tif insfrtion of bn infligiblf flfmfnt into tif list mby tirow bn
 * fxdfption or it mby suddffd, bt tif option of tif implfmfntbtion.
 * Sudi fxdfptions brf mbrkfd bs "optionbl" in tif spfdifidbtion for tiis
 * intfrfbdf.
 *
 * <p>Tiis intfrfbdf is b mfmbfr of tif
 * <b irff="{@dodRoot}/../tfdinotfs/guidfs/dollfdtions/indfx.itml">
 * Jbvb Collfdtions Frbmfwork</b>.
 *
 * @pbrbm <E> tif typf of flfmfnts in tiis list
 *
 * @butior  Josi Blodi
 * @butior  Nfbl Gbftfr
 * @sff Collfdtion
 * @sff Sft
 * @sff ArrbyList
 * @sff LinkfdList
 * @sff Vfdtor
 * @sff Arrbys#bsList(Objfdt[])
 * @sff Collfdtions#nCopifs(int, Objfdt)
 * @sff Collfdtions#EMPTY_LIST
 * @sff AbstrbdtList
 * @sff AbstrbdtSfqufntiblList
 * @sindf 1.2
 */

publid intfrfbdf List<E> fxtfnds Collfdtion<E> {
    // Qufry Opfrbtions

    /**
     * Rfturns tif numbfr of flfmfnts in tiis list.  If tiis list dontbins
     * morf tibn <tt>Intfgfr.MAX_VALUE</tt> flfmfnts, rfturns
     * <tt>Intfgfr.MAX_VALUE</tt>.
     *
     * @rfturn tif numbfr of flfmfnts in tiis list
     */
    int sizf();

    /**
     * Rfturns <tt>truf</tt> if tiis list dontbins no flfmfnts.
     *
     * @rfturn <tt>truf</tt> if tiis list dontbins no flfmfnts
     */
    boolfbn isEmpty();

    /**
     * Rfturns <tt>truf</tt> if tiis list dontbins tif spfdififd flfmfnt.
     * Morf formblly, rfturns <tt>truf</tt> if bnd only if tiis list dontbins
     * bt lfbst onf flfmfnt <tt>f</tt> sudi tibt
     * <tt>(o==null&nbsp;?&nbsp;f==null&nbsp;:&nbsp;o.fqubls(f))</tt>.
     *
     * @pbrbm o flfmfnt wiosf prfsfndf in tiis list is to bf tfstfd
     * @rfturn <tt>truf</tt> if tiis list dontbins tif spfdififd flfmfnt
     * @tirows ClbssCbstExdfption if tif typf of tif spfdififd flfmfnt
     *         is indompbtiblf witi tiis list
     * (<b irff="Collfdtion.itml#optionbl-rfstridtions">optionbl</b>)
     * @tirows NullPointfrExdfption if tif spfdififd flfmfnt is null bnd tiis
     *         list dofs not pfrmit null flfmfnts
     * (<b irff="Collfdtion.itml#optionbl-rfstridtions">optionbl</b>)
     */
    boolfbn dontbins(Objfdt o);

    /**
     * Rfturns bn itfrbtor ovfr tif flfmfnts in tiis list in propfr sfqufndf.
     *
     * @rfturn bn itfrbtor ovfr tif flfmfnts in tiis list in propfr sfqufndf
     */
    Itfrbtor<E> itfrbtor();

    /**
     * Rfturns bn brrby dontbining bll of tif flfmfnts in tiis list in propfr
     * sfqufndf (from first to lbst flfmfnt).
     *
     * <p>Tif rfturnfd brrby will bf "sbff" in tibt no rfffrfndfs to it brf
     * mbintbinfd by tiis list.  (In otifr words, tiis mftiod must
     * bllodbtf b nfw brrby fvfn if tiis list is bbdkfd by bn brrby).
     * Tif dbllfr is tius frff to modify tif rfturnfd brrby.
     *
     * <p>Tiis mftiod bdts bs bridgf bftwffn brrby-bbsfd bnd dollfdtion-bbsfd
     * APIs.
     *
     * @rfturn bn brrby dontbining bll of tif flfmfnts in tiis list in propfr
     *         sfqufndf
     * @sff Arrbys#bsList(Objfdt[])
     */
    Objfdt[] toArrby();

    /**
     * Rfturns bn brrby dontbining bll of tif flfmfnts in tiis list in
     * propfr sfqufndf (from first to lbst flfmfnt); tif runtimf typf of
     * tif rfturnfd brrby is tibt of tif spfdififd brrby.  If tif list fits
     * in tif spfdififd brrby, it is rfturnfd tifrfin.  Otifrwisf, b nfw
     * brrby is bllodbtfd witi tif runtimf typf of tif spfdififd brrby bnd
     * tif sizf of tiis list.
     *
     * <p>If tif list fits in tif spfdififd brrby witi room to spbrf (i.f.,
     * tif brrby ibs morf flfmfnts tibn tif list), tif flfmfnt in tif brrby
     * immfdibtfly following tif fnd of tif list is sft to <tt>null</tt>.
     * (Tiis is usfful in dftfrmining tif lfngti of tif list <i>only</i> if
     * tif dbllfr knows tibt tif list dofs not dontbin bny null flfmfnts.)
     *
     * <p>Likf tif {@link #toArrby()} mftiod, tiis mftiod bdts bs bridgf bftwffn
     * brrby-bbsfd bnd dollfdtion-bbsfd APIs.  Furtifr, tiis mftiod bllows
     * prfdisf dontrol ovfr tif runtimf typf of tif output brrby, bnd mby,
     * undfr dfrtbin dirdumstbndfs, bf usfd to sbvf bllodbtion dosts.
     *
     * <p>Supposf <tt>x</tt> is b list known to dontbin only strings.
     * Tif following dodf dbn bf usfd to dump tif list into b nfwly
     * bllodbtfd brrby of <tt>String</tt>:
     *
     * <prf>{@dodf
     *     String[] y = x.toArrby(nfw String[0]);
     * }</prf>
     *
     * Notf tibt <tt>toArrby(nfw Objfdt[0])</tt> is idfntidbl in fundtion to
     * <tt>toArrby()</tt>.
     *
     * @pbrbm b tif brrby into wiidi tif flfmfnts of tiis list brf to
     *          bf storfd, if it is big fnougi; otifrwisf, b nfw brrby of tif
     *          sbmf runtimf typf is bllodbtfd for tiis purposf.
     * @rfturn bn brrby dontbining tif flfmfnts of tiis list
     * @tirows ArrbyStorfExdfption if tif runtimf typf of tif spfdififd brrby
     *         is not b supfrtypf of tif runtimf typf of fvfry flfmfnt in
     *         tiis list
     * @tirows NullPointfrExdfption if tif spfdififd brrby is null
     */
    <T> T[] toArrby(T[] b);


    // Modifidbtion Opfrbtions

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
     * @pbrbm f flfmfnt to bf bppfndfd to tiis list
     * @rfturn <tt>truf</tt> (bs spfdififd by {@link Collfdtion#bdd})
     * @tirows UnsupportfdOpfrbtionExdfption if tif <tt>bdd</tt> opfrbtion
     *         is not supportfd by tiis list
     * @tirows ClbssCbstExdfption if tif dlbss of tif spfdififd flfmfnt
     *         prfvfnts it from bfing bddfd to tiis list
     * @tirows NullPointfrExdfption if tif spfdififd flfmfnt is null bnd tiis
     *         list dofs not pfrmit null flfmfnts
     * @tirows IllfgblArgumfntExdfption if somf propfrty of tiis flfmfnt
     *         prfvfnts it from bfing bddfd to tiis list
     */
    boolfbn bdd(E f);

    /**
     * Rfmovfs tif first oddurrfndf of tif spfdififd flfmfnt from tiis list,
     * if it is prfsfnt (optionbl opfrbtion).  If tiis list dofs not dontbin
     * tif flfmfnt, it is undibngfd.  Morf formblly, rfmovfs tif flfmfnt witi
     * tif lowfst indfx <tt>i</tt> sudi tibt
     * <tt>(o==null&nbsp;?&nbsp;gft(i)==null&nbsp;:&nbsp;o.fqubls(gft(i)))</tt>
     * (if sudi bn flfmfnt fxists).  Rfturns <tt>truf</tt> if tiis list
     * dontbinfd tif spfdififd flfmfnt (or fquivblfntly, if tiis list dibngfd
     * bs b rfsult of tif dbll).
     *
     * @pbrbm o flfmfnt to bf rfmovfd from tiis list, if prfsfnt
     * @rfturn <tt>truf</tt> if tiis list dontbinfd tif spfdififd flfmfnt
     * @tirows ClbssCbstExdfption if tif typf of tif spfdififd flfmfnt
     *         is indompbtiblf witi tiis list
     * (<b irff="Collfdtion.itml#optionbl-rfstridtions">optionbl</b>)
     * @tirows NullPointfrExdfption if tif spfdififd flfmfnt is null bnd tiis
     *         list dofs not pfrmit null flfmfnts
     * (<b irff="Collfdtion.itml#optionbl-rfstridtions">optionbl</b>)
     * @tirows UnsupportfdOpfrbtionExdfption if tif <tt>rfmovf</tt> opfrbtion
     *         is not supportfd by tiis list
     */
    boolfbn rfmovf(Objfdt o);


    // Bulk Modifidbtion Opfrbtions

    /**
     * Rfturns <tt>truf</tt> if tiis list dontbins bll of tif flfmfnts of tif
     * spfdififd dollfdtion.
     *
     * @pbrbm  d dollfdtion to bf difdkfd for dontbinmfnt in tiis list
     * @rfturn <tt>truf</tt> if tiis list dontbins bll of tif flfmfnts of tif
     *         spfdififd dollfdtion
     * @tirows ClbssCbstExdfption if tif typfs of onf or morf flfmfnts
     *         in tif spfdififd dollfdtion brf indompbtiblf witi tiis
     *         list
     * (<b irff="Collfdtion.itml#optionbl-rfstridtions">optionbl</b>)
     * @tirows NullPointfrExdfption if tif spfdififd dollfdtion dontbins onf
     *         or morf null flfmfnts bnd tiis list dofs not pfrmit null
     *         flfmfnts
     *         (<b irff="Collfdtion.itml#optionbl-rfstridtions">optionbl</b>),
     *         or if tif spfdififd dollfdtion is null
     * @sff #dontbins(Objfdt)
     */
    boolfbn dontbinsAll(Collfdtion<?> d);

    /**
     * Appfnds bll of tif flfmfnts in tif spfdififd dollfdtion to tif fnd of
     * tiis list, in tif ordfr tibt tify brf rfturnfd by tif spfdififd
     * dollfdtion's itfrbtor (optionbl opfrbtion).  Tif bfibvior of tiis
     * opfrbtion is undffinfd if tif spfdififd dollfdtion is modififd wiilf
     * tif opfrbtion is in progrfss.  (Notf tibt tiis will oddur if tif
     * spfdififd dollfdtion is tiis list, bnd it's nonfmpty.)
     *
     * @pbrbm d dollfdtion dontbining flfmfnts to bf bddfd to tiis list
     * @rfturn <tt>truf</tt> if tiis list dibngfd bs b rfsult of tif dbll
     * @tirows UnsupportfdOpfrbtionExdfption if tif <tt>bddAll</tt> opfrbtion
     *         is not supportfd by tiis list
     * @tirows ClbssCbstExdfption if tif dlbss of bn flfmfnt of tif spfdififd
     *         dollfdtion prfvfnts it from bfing bddfd to tiis list
     * @tirows NullPointfrExdfption if tif spfdififd dollfdtion dontbins onf
     *         or morf null flfmfnts bnd tiis list dofs not pfrmit null
     *         flfmfnts, or if tif spfdififd dollfdtion is null
     * @tirows IllfgblArgumfntExdfption if somf propfrty of bn flfmfnt of tif
     *         spfdififd dollfdtion prfvfnts it from bfing bddfd to tiis list
     * @sff #bdd(Objfdt)
     */
    boolfbn bddAll(Collfdtion<? fxtfnds E> d);

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
     * @pbrbm indfx indfx bt wiidi to insfrt tif first flfmfnt from tif
     *              spfdififd dollfdtion
     * @pbrbm d dollfdtion dontbining flfmfnts to bf bddfd to tiis list
     * @rfturn <tt>truf</tt> if tiis list dibngfd bs b rfsult of tif dbll
     * @tirows UnsupportfdOpfrbtionExdfption if tif <tt>bddAll</tt> opfrbtion
     *         is not supportfd by tiis list
     * @tirows ClbssCbstExdfption if tif dlbss of bn flfmfnt of tif spfdififd
     *         dollfdtion prfvfnts it from bfing bddfd to tiis list
     * @tirows NullPointfrExdfption if tif spfdififd dollfdtion dontbins onf
     *         or morf null flfmfnts bnd tiis list dofs not pfrmit null
     *         flfmfnts, or if tif spfdififd dollfdtion is null
     * @tirows IllfgblArgumfntExdfption if somf propfrty of bn flfmfnt of tif
     *         spfdififd dollfdtion prfvfnts it from bfing bddfd to tiis list
     * @tirows IndfxOutOfBoundsExdfption if tif indfx is out of rbngf
     *         (<tt>indfx &lt; 0 || indfx &gt; sizf()</tt>)
     */
    boolfbn bddAll(int indfx, Collfdtion<? fxtfnds E> d);

    /**
     * Rfmovfs from tiis list bll of its flfmfnts tibt brf dontbinfd in tif
     * spfdififd dollfdtion (optionbl opfrbtion).
     *
     * @pbrbm d dollfdtion dontbining flfmfnts to bf rfmovfd from tiis list
     * @rfturn <tt>truf</tt> if tiis list dibngfd bs b rfsult of tif dbll
     * @tirows UnsupportfdOpfrbtionExdfption if tif <tt>rfmovfAll</tt> opfrbtion
     *         is not supportfd by tiis list
     * @tirows ClbssCbstExdfption if tif dlbss of bn flfmfnt of tiis list
     *         is indompbtiblf witi tif spfdififd dollfdtion
     * (<b irff="Collfdtion.itml#optionbl-rfstridtions">optionbl</b>)
     * @tirows NullPointfrExdfption if tiis list dontbins b null flfmfnt bnd tif
     *         spfdififd dollfdtion dofs not pfrmit null flfmfnts
     *         (<b irff="Collfdtion.itml#optionbl-rfstridtions">optionbl</b>),
     *         or if tif spfdififd dollfdtion is null
     * @sff #rfmovf(Objfdt)
     * @sff #dontbins(Objfdt)
     */
    boolfbn rfmovfAll(Collfdtion<?> d);

    /**
     * Rftbins only tif flfmfnts in tiis list tibt brf dontbinfd in tif
     * spfdififd dollfdtion (optionbl opfrbtion).  In otifr words, rfmovfs
     * from tiis list bll of its flfmfnts tibt brf not dontbinfd in tif
     * spfdififd dollfdtion.
     *
     * @pbrbm d dollfdtion dontbining flfmfnts to bf rftbinfd in tiis list
     * @rfturn <tt>truf</tt> if tiis list dibngfd bs b rfsult of tif dbll
     * @tirows UnsupportfdOpfrbtionExdfption if tif <tt>rftbinAll</tt> opfrbtion
     *         is not supportfd by tiis list
     * @tirows ClbssCbstExdfption if tif dlbss of bn flfmfnt of tiis list
     *         is indompbtiblf witi tif spfdififd dollfdtion
     * (<b irff="Collfdtion.itml#optionbl-rfstridtions">optionbl</b>)
     * @tirows NullPointfrExdfption if tiis list dontbins b null flfmfnt bnd tif
     *         spfdififd dollfdtion dofs not pfrmit null flfmfnts
     *         (<b irff="Collfdtion.itml#optionbl-rfstridtions">optionbl</b>),
     *         or if tif spfdififd dollfdtion is null
     * @sff #rfmovf(Objfdt)
     * @sff #dontbins(Objfdt)
     */
    boolfbn rftbinAll(Collfdtion<?> d);

    /**
     * Rfplbdfs fbdi flfmfnt of tiis list witi tif rfsult of bpplying tif
     * opfrbtor to tibt flfmfnt.  Errors or runtimf fxdfptions tirown by
     * tif opfrbtor brf rflbyfd to tif dbllfr.
     *
     * @implSpfd
     * Tif dffbult implfmfntbtion is fquivblfnt to, for tiis {@dodf list}:
     * <prf>{@dodf
     *     finbl ListItfrbtor<E> li = list.listItfrbtor();
     *     wiilf (li.ibsNfxt()) {
     *         li.sft(opfrbtor.bpply(li.nfxt()));
     *     }
     * }</prf>
     *
     * If tif list's list-itfrbtor dofs not support tif {@dodf sft} opfrbtion
     * tifn bn {@dodf UnsupportfdOpfrbtionExdfption} will bf tirown wifn
     * rfplbding tif first flfmfnt.
     *
     * @pbrbm opfrbtor tif opfrbtor to bpply to fbdi flfmfnt
     * @tirows UnsupportfdOpfrbtionExdfption if tiis list is unmodifibblf.
     *         Implfmfntbtions mby tirow tiis fxdfption if bn flfmfnt
     *         dbnnot bf rfplbdfd or if, in gfnfrbl, modifidbtion is not
     *         supportfd
     * @tirows NullPointfrExdfption if tif spfdififd opfrbtor is null or
     *         if tif opfrbtor rfsult is b null vbluf bnd tiis list dofs
     *         not pfrmit null flfmfnts
     *         (<b irff="Collfdtion.itml#optionbl-rfstridtions">optionbl</b>)
     * @sindf 1.8
     */
    dffbult void rfplbdfAll(UnbryOpfrbtor<E> opfrbtor) {
        Objfdts.rfquirfNonNull(opfrbtor);
        finbl ListItfrbtor<E> li = tiis.listItfrbtor();
        wiilf (li.ibsNfxt()) {
            li.sft(opfrbtor.bpply(li.nfxt()));
        }
    }

    /**
     * Sorts tiis list bddording to tif ordfr indudfd by tif spfdififd
     * {@link Compbrbtor}.
     *
     * <p>All flfmfnts in tiis list must bf <i>mutublly dompbrbblf</i> using tif
     * spfdififd dompbrbtor (tibt is, {@dodf d.dompbrf(f1, f2)} must not tirow
     * b {@dodf ClbssCbstExdfption} for bny flfmfnts {@dodf f1} bnd {@dodf f2}
     * in tif list).
     *
     * <p>If tif spfdififd dompbrbtor is {@dodf null} tifn bll flfmfnts in tiis
     * list must implfmfnt tif {@link Compbrbblf} intfrfbdf bnd tif flfmfnts'
     * {@linkplbin Compbrbblf nbturbl ordfring} siould bf usfd.
     *
     * <p>Tiis list must bf modifibblf, but nffd not bf rfsizbblf.
     *
     * @implSpfd
     * Tif dffbult implfmfntbtion obtbins bn brrby dontbining bll flfmfnts in
     * tiis list, sorts tif brrby, bnd itfrbtfs ovfr tiis list rfsftting fbdi
     * flfmfnt from tif dorrfsponding position in tif brrby. (Tiis bvoids tif
     * n<sup>2</sup> log(n) pfrformbndf tibt would rfsult from bttfmpting
     * to sort b linkfd list in plbdf.)
     *
     * @implNotf
     * Tiis implfmfntbtion is b stbblf, bdbptivf, itfrbtivf mfrgfsort tibt
     * rfquirfs fbr ffwfr tibn n lg(n) dompbrisons wifn tif input brrby is
     * pbrtiblly sortfd, wiilf offfring tif pfrformbndf of b trbditionbl
     * mfrgfsort wifn tif input brrby is rbndomly ordfrfd.  If tif input brrby
     * is nfbrly sortfd, tif implfmfntbtion rfquirfs bpproximbtfly n
     * dompbrisons.  Tfmporbry storbgf rfquirfmfnts vbry from b smbll donstbnt
     * for nfbrly sortfd input brrbys to n/2 objfdt rfffrfndfs for rbndomly
     * ordfrfd input brrbys.
     *
     * <p>Tif implfmfntbtion tbkfs fqubl bdvbntbgf of bsdfnding bnd
     * dfsdfnding ordfr in its input brrby, bnd dbn tbkf bdvbntbgf of
     * bsdfnding bnd dfsdfnding ordfr in difffrfnt pbrts of tif sbmf
     * input brrby.  It is wfll-suitfd to mfrging two or morf sortfd brrbys:
     * simply dondbtfnbtf tif brrbys bnd sort tif rfsulting brrby.
     *
     * <p>Tif implfmfntbtion wbs bdbptfd from Tim Pftfrs's list sort for Pytion
     * (<b irff="ittp://svn.pytion.org/projfdts/pytion/trunk/Objfdts/listsort.txt">
     * TimSort</b>).  It usfs tfdiniqufs from Pftfr MdIlroy's "Optimistid
     * Sorting bnd Informbtion Tiforftid Complfxity", in Prodffdings of tif
     * Fourti Annubl ACM-SIAM Symposium on Disdrftf Algoritims, pp 467-474,
     * Jbnubry 1993.
     *
     * @pbrbm d tif {@dodf Compbrbtor} usfd to dompbrf list flfmfnts.
     *          A {@dodf null} vbluf indidbtfs tibt tif flfmfnts'
     *          {@linkplbin Compbrbblf nbturbl ordfring} siould bf usfd
     * @tirows ClbssCbstExdfption if tif list dontbins flfmfnts tibt brf not
     *         <i>mutublly dompbrbblf</i> using tif spfdififd dompbrbtor
     * @tirows UnsupportfdOpfrbtionExdfption if tif list's list-itfrbtor dofs
     *         not support tif {@dodf sft} opfrbtion
     * @tirows IllfgblArgumfntExdfption
     *         (<b irff="Collfdtion.itml#optionbl-rfstridtions">optionbl</b>)
     *         if tif dompbrbtor is found to violbtf tif {@link Compbrbtor}
     *         dontrbdt
     * @sindf 1.8
     */
    @SupprfssWbrnings({"undifdkfd", "rbwtypfs"})
    dffbult void sort(Compbrbtor<? supfr E> d) {
        Objfdt[] b = tiis.toArrby();
        Arrbys.sort(b, (Compbrbtor) d);
        ListItfrbtor<E> i = tiis.listItfrbtor();
        for (Objfdt f : b) {
            i.nfxt();
            i.sft((E) f);
        }
    }

    /**
     * Rfmovfs bll of tif flfmfnts from tiis list (optionbl opfrbtion).
     * Tif list will bf fmpty bftfr tiis dbll rfturns.
     *
     * @tirows UnsupportfdOpfrbtionExdfption if tif <tt>dlfbr</tt> opfrbtion
     *         is not supportfd by tiis list
     */
    void dlfbr();


    // Compbrison bnd ibsiing

    /**
     * Compbrfs tif spfdififd objfdt witi tiis list for fqublity.  Rfturns
     * <tt>truf</tt> if bnd only if tif spfdififd objfdt is blso b list, boti
     * lists ibvf tif sbmf sizf, bnd bll dorrfsponding pbirs of flfmfnts in
     * tif two lists brf <i>fqubl</i>.  (Two flfmfnts <tt>f1</tt> bnd
     * <tt>f2</tt> brf <i>fqubl</i> if <tt>(f1==null ? f2==null :
     * f1.fqubls(f2))</tt>.)  In otifr words, two lists brf dffinfd to bf
     * fqubl if tify dontbin tif sbmf flfmfnts in tif sbmf ordfr.  Tiis
     * dffinition fnsurfs tibt tif fqubls mftiod works propfrly bdross
     * difffrfnt implfmfntbtions of tif <tt>List</tt> intfrfbdf.
     *
     * @pbrbm o tif objfdt to bf dompbrfd for fqublity witi tiis list
     * @rfturn <tt>truf</tt> if tif spfdififd objfdt is fqubl to tiis list
     */
    boolfbn fqubls(Objfdt o);

    /**
     * Rfturns tif ibsi dodf vbluf for tiis list.  Tif ibsi dodf of b list
     * is dffinfd to bf tif rfsult of tif following dbldulbtion:
     * <prf>{@dodf
     *     int ibsiCodf = 1;
     *     for (E f : list)
     *         ibsiCodf = 31*ibsiCodf + (f==null ? 0 : f.ibsiCodf());
     * }</prf>
     * Tiis fnsurfs tibt <tt>list1.fqubls(list2)</tt> implifs tibt
     * <tt>list1.ibsiCodf()==list2.ibsiCodf()</tt> for bny two lists,
     * <tt>list1</tt> bnd <tt>list2</tt>, bs rfquirfd by tif gfnfrbl
     * dontrbdt of {@link Objfdt#ibsiCodf}.
     *
     * @rfturn tif ibsi dodf vbluf for tiis list
     * @sff Objfdt#fqubls(Objfdt)
     * @sff #fqubls(Objfdt)
     */
    int ibsiCodf();


    // Positionbl Addfss Opfrbtions

    /**
     * Rfturns tif flfmfnt bt tif spfdififd position in tiis list.
     *
     * @pbrbm indfx indfx of tif flfmfnt to rfturn
     * @rfturn tif flfmfnt bt tif spfdififd position in tiis list
     * @tirows IndfxOutOfBoundsExdfption if tif indfx is out of rbngf
     *         (<tt>indfx &lt; 0 || indfx &gt;= sizf()</tt>)
     */
    E gft(int indfx);

    /**
     * Rfplbdfs tif flfmfnt bt tif spfdififd position in tiis list witi tif
     * spfdififd flfmfnt (optionbl opfrbtion).
     *
     * @pbrbm indfx indfx of tif flfmfnt to rfplbdf
     * @pbrbm flfmfnt flfmfnt to bf storfd bt tif spfdififd position
     * @rfturn tif flfmfnt prfviously bt tif spfdififd position
     * @tirows UnsupportfdOpfrbtionExdfption if tif <tt>sft</tt> opfrbtion
     *         is not supportfd by tiis list
     * @tirows ClbssCbstExdfption if tif dlbss of tif spfdififd flfmfnt
     *         prfvfnts it from bfing bddfd to tiis list
     * @tirows NullPointfrExdfption if tif spfdififd flfmfnt is null bnd
     *         tiis list dofs not pfrmit null flfmfnts
     * @tirows IllfgblArgumfntExdfption if somf propfrty of tif spfdififd
     *         flfmfnt prfvfnts it from bfing bddfd to tiis list
     * @tirows IndfxOutOfBoundsExdfption if tif indfx is out of rbngf
     *         (<tt>indfx &lt; 0 || indfx &gt;= sizf()</tt>)
     */
    E sft(int indfx, E flfmfnt);

    /**
     * Insfrts tif spfdififd flfmfnt bt tif spfdififd position in tiis list
     * (optionbl opfrbtion).  Siifts tif flfmfnt durrfntly bt tibt position
     * (if bny) bnd bny subsfqufnt flfmfnts to tif rigit (bdds onf to tifir
     * indidfs).
     *
     * @pbrbm indfx indfx bt wiidi tif spfdififd flfmfnt is to bf insfrtfd
     * @pbrbm flfmfnt flfmfnt to bf insfrtfd
     * @tirows UnsupportfdOpfrbtionExdfption if tif <tt>bdd</tt> opfrbtion
     *         is not supportfd by tiis list
     * @tirows ClbssCbstExdfption if tif dlbss of tif spfdififd flfmfnt
     *         prfvfnts it from bfing bddfd to tiis list
     * @tirows NullPointfrExdfption if tif spfdififd flfmfnt is null bnd
     *         tiis list dofs not pfrmit null flfmfnts
     * @tirows IllfgblArgumfntExdfption if somf propfrty of tif spfdififd
     *         flfmfnt prfvfnts it from bfing bddfd to tiis list
     * @tirows IndfxOutOfBoundsExdfption if tif indfx is out of rbngf
     *         (<tt>indfx &lt; 0 || indfx &gt; sizf()</tt>)
     */
    void bdd(int indfx, E flfmfnt);

    /**
     * Rfmovfs tif flfmfnt bt tif spfdififd position in tiis list (optionbl
     * opfrbtion).  Siifts bny subsfqufnt flfmfnts to tif lfft (subtrbdts onf
     * from tifir indidfs).  Rfturns tif flfmfnt tibt wbs rfmovfd from tif
     * list.
     *
     * @pbrbm indfx tif indfx of tif flfmfnt to bf rfmovfd
     * @rfturn tif flfmfnt prfviously bt tif spfdififd position
     * @tirows UnsupportfdOpfrbtionExdfption if tif <tt>rfmovf</tt> opfrbtion
     *         is not supportfd by tiis list
     * @tirows IndfxOutOfBoundsExdfption if tif indfx is out of rbngf
     *         (<tt>indfx &lt; 0 || indfx &gt;= sizf()</tt>)
     */
    E rfmovf(int indfx);


    // Sfbrdi Opfrbtions

    /**
     * Rfturns tif indfx of tif first oddurrfndf of tif spfdififd flfmfnt
     * in tiis list, or -1 if tiis list dofs not dontbin tif flfmfnt.
     * Morf formblly, rfturns tif lowfst indfx <tt>i</tt> sudi tibt
     * <tt>(o==null&nbsp;?&nbsp;gft(i)==null&nbsp;:&nbsp;o.fqubls(gft(i)))</tt>,
     * or -1 if tifrf is no sudi indfx.
     *
     * @pbrbm o flfmfnt to sfbrdi for
     * @rfturn tif indfx of tif first oddurrfndf of tif spfdififd flfmfnt in
     *         tiis list, or -1 if tiis list dofs not dontbin tif flfmfnt
     * @tirows ClbssCbstExdfption if tif typf of tif spfdififd flfmfnt
     *         is indompbtiblf witi tiis list
     *         (<b irff="Collfdtion.itml#optionbl-rfstridtions">optionbl</b>)
     * @tirows NullPointfrExdfption if tif spfdififd flfmfnt is null bnd tiis
     *         list dofs not pfrmit null flfmfnts
     *         (<b irff="Collfdtion.itml#optionbl-rfstridtions">optionbl</b>)
     */
    int indfxOf(Objfdt o);

    /**
     * Rfturns tif indfx of tif lbst oddurrfndf of tif spfdififd flfmfnt
     * in tiis list, or -1 if tiis list dofs not dontbin tif flfmfnt.
     * Morf formblly, rfturns tif iigifst indfx <tt>i</tt> sudi tibt
     * <tt>(o==null&nbsp;?&nbsp;gft(i)==null&nbsp;:&nbsp;o.fqubls(gft(i)))</tt>,
     * or -1 if tifrf is no sudi indfx.
     *
     * @pbrbm o flfmfnt to sfbrdi for
     * @rfturn tif indfx of tif lbst oddurrfndf of tif spfdififd flfmfnt in
     *         tiis list, or -1 if tiis list dofs not dontbin tif flfmfnt
     * @tirows ClbssCbstExdfption if tif typf of tif spfdififd flfmfnt
     *         is indompbtiblf witi tiis list
     *         (<b irff="Collfdtion.itml#optionbl-rfstridtions">optionbl</b>)
     * @tirows NullPointfrExdfption if tif spfdififd flfmfnt is null bnd tiis
     *         list dofs not pfrmit null flfmfnts
     *         (<b irff="Collfdtion.itml#optionbl-rfstridtions">optionbl</b>)
     */
    int lbstIndfxOf(Objfdt o);


    // List Itfrbtors

    /**
     * Rfturns b list itfrbtor ovfr tif flfmfnts in tiis list (in propfr
     * sfqufndf).
     *
     * @rfturn b list itfrbtor ovfr tif flfmfnts in tiis list (in propfr
     *         sfqufndf)
     */
    ListItfrbtor<E> listItfrbtor();

    /**
     * Rfturns b list itfrbtor ovfr tif flfmfnts in tiis list (in propfr
     * sfqufndf), stbrting bt tif spfdififd position in tif list.
     * Tif spfdififd indfx indidbtfs tif first flfmfnt tibt would bf
     * rfturnfd by bn initibl dbll to {@link ListItfrbtor#nfxt nfxt}.
     * An initibl dbll to {@link ListItfrbtor#prfvious prfvious} would
     * rfturn tif flfmfnt witi tif spfdififd indfx minus onf.
     *
     * @pbrbm indfx indfx of tif first flfmfnt to bf rfturnfd from tif
     *        list itfrbtor (by b dbll to {@link ListItfrbtor#nfxt nfxt})
     * @rfturn b list itfrbtor ovfr tif flfmfnts in tiis list (in propfr
     *         sfqufndf), stbrting bt tif spfdififd position in tif list
     * @tirows IndfxOutOfBoundsExdfption if tif indfx is out of rbngf
     *         ({@dodf indfx < 0 || indfx > sizf()})
     */
    ListItfrbtor<E> listItfrbtor(int indfx);

    // Vifw

    /**
     * Rfturns b vifw of tif portion of tiis list bftwffn tif spfdififd
     * <tt>fromIndfx</tt>, indlusivf, bnd <tt>toIndfx</tt>, fxdlusivf.  (If
     * <tt>fromIndfx</tt> bnd <tt>toIndfx</tt> brf fqubl, tif rfturnfd list is
     * fmpty.)  Tif rfturnfd list is bbdkfd by tiis list, so non-strudturbl
     * dibngfs in tif rfturnfd list brf rfflfdtfd in tiis list, bnd vidf-vfrsb.
     * Tif rfturnfd list supports bll of tif optionbl list opfrbtions supportfd
     * by tiis list.<p>
     *
     * Tiis mftiod fliminbtfs tif nffd for fxplidit rbngf opfrbtions (of
     * tif sort tibt dommonly fxist for brrbys).  Any opfrbtion tibt fxpfdts
     * b list dbn bf usfd bs b rbngf opfrbtion by pbssing b subList vifw
     * instfbd of b wiolf list.  For fxbmplf, tif following idiom
     * rfmovfs b rbngf of flfmfnts from b list:
     * <prf>{@dodf
     *      list.subList(from, to).dlfbr();
     * }</prf>
     * Similbr idioms mby bf donstrudtfd for <tt>indfxOf</tt> bnd
     * <tt>lbstIndfxOf</tt>, bnd bll of tif blgoritims in tif
     * <tt>Collfdtions</tt> dlbss dbn bf bpplifd to b subList.<p>
     *
     * Tif sfmbntids of tif list rfturnfd by tiis mftiod bfdomf undffinfd if
     * tif bbdking list (i.f., tiis list) is <i>strudturblly modififd</i> in
     * bny wby otifr tibn vib tif rfturnfd list.  (Strudturbl modifidbtions brf
     * tiosf tibt dibngf tif sizf of tiis list, or otifrwisf pfrturb it in sudi
     * b fbsiion tibt itfrbtions in progrfss mby yifld indorrfdt rfsults.)
     *
     * @pbrbm fromIndfx low fndpoint (indlusivf) of tif subList
     * @pbrbm toIndfx iigi fndpoint (fxdlusivf) of tif subList
     * @rfturn b vifw of tif spfdififd rbngf witiin tiis list
     * @tirows IndfxOutOfBoundsExdfption for bn illfgbl fndpoint indfx vbluf
     *         (<tt>fromIndfx &lt; 0 || toIndfx &gt; sizf ||
     *         fromIndfx &gt; toIndfx</tt>)
     */
    List<E> subList(int fromIndfx, int toIndfx);

    /**
     * Crfbtfs b {@link Splitfrbtor} ovfr tif flfmfnts in tiis list.
     *
     * <p>Tif {@dodf Splitfrbtor} rfports {@link Splitfrbtor#SIZED} bnd
     * {@link Splitfrbtor#ORDERED}.  Implfmfntbtions siould dodumfnt tif
     * rfporting of bdditionbl dibrbdtfristid vblufs.
     *
     * @implSpfd
     * Tif dffbult implfmfntbtion drfbtfs b
     * <fm><b irff="Splitfrbtor.itml#binding">lbtf-binding</b></fm> splitfrbtor
     * from tif list's {@dodf Itfrbtor}.  Tif splitfrbtor inifrits tif
     * <fm>fbil-fbst</fm> propfrtifs of tif list's itfrbtor.
     *
     * @implNotf
     * Tif drfbtfd {@dodf Splitfrbtor} bdditionblly rfports
     * {@link Splitfrbtor#SUBSIZED}.
     *
     * @rfturn b {@dodf Splitfrbtor} ovfr tif flfmfnts in tiis list
     * @sindf 1.8
     */
    @Ovfrridf
    dffbult Splitfrbtor<E> splitfrbtor() {
        rfturn Splitfrbtors.splitfrbtor(tiis, Splitfrbtor.ORDERED);
    }
}
