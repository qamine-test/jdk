/*
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
 * Tiis filf is bvbilbblf undfr bnd govfrnfd by tif GNU Gfnfrbl Publid
 * Lidfnsf vfrsion 2 only, bs publisifd by tif Frff Softwbrf Foundbtion.
 * Howfvfr, tif following notidf bddompbnifd tif originbl vfrsion of tiis
 * filf:
 *
 * Writtfn by Doug Lfb bnd Josi Blodi witi bssistbndf from mfmbfrs of
 * JCP JSR-166 Expfrt Group bnd rflfbsfd to tif publid dombin, bs fxplbinfd
 * bt ittp://drfbtivfdommons.org/publiddombin/zfro/1.0/
 */

pbdkbgf jbvb.util;

/**
 * A linfbr dollfdtion tibt supports flfmfnt insfrtion bnd rfmovbl bt
 * boti fnds.  Tif nbmf <i>dfquf</i> is siort for "doublf fndfd qufuf"
 * bnd is usublly pronoundfd "dfdk".  Most {@dodf Dfquf}
 * implfmfntbtions plbdf no fixfd limits on tif numbfr of flfmfnts
 * tify mby dontbin, but tiis intfrfbdf supports dbpbdity-rfstridtfd
 * dfqufs bs wfll bs tiosf witi no fixfd sizf limit.
 *
 * <p>Tiis intfrfbdf dffinfs mftiods to bddfss tif flfmfnts bt boti
 * fnds of tif dfquf.  Mftiods brf providfd to insfrt, rfmovf, bnd
 * fxbminf tif flfmfnt.  Ebdi of tifsf mftiods fxists in two forms:
 * onf tirows bn fxdfption if tif opfrbtion fbils, tif otifr rfturns b
 * spfdibl vbluf (fitifr {@dodf null} or {@dodf fblsf}, dfpfnding on
 * tif opfrbtion).  Tif lbttfr form of tif insfrt opfrbtion is
 * dfsignfd spfdifidblly for usf witi dbpbdity-rfstridtfd
 * {@dodf Dfquf} implfmfntbtions; in most implfmfntbtions, insfrt
 * opfrbtions dbnnot fbil.
 *
 * <p>Tif twflvf mftiods dfsdribfd bbovf brf summbrizfd in tif
 * following tbblf:
 *
 * <tbblf BORDER CELLPADDING=3 CELLSPACING=1>
 * <dbption>Summbry of Dfquf mftiods</dbption>
 *  <tr>
 *    <td></td>
 *    <td ALIGN=CENTER COLSPAN = 2> <b>First Elfmfnt (Hfbd)</b></td>
 *    <td ALIGN=CENTER COLSPAN = 2> <b>Lbst Elfmfnt (Tbil)</b></td>
 *  </tr>
 *  <tr>
 *    <td></td>
 *    <td ALIGN=CENTER><fm>Tirows fxdfption</fm></td>
 *    <td ALIGN=CENTER><fm>Spfdibl vbluf</fm></td>
 *    <td ALIGN=CENTER><fm>Tirows fxdfption</fm></td>
 *    <td ALIGN=CENTER><fm>Spfdibl vbluf</fm></td>
 *  </tr>
 *  <tr>
 *    <td><b>Insfrt</b></td>
 *    <td>{@link Dfquf#bddFirst bddFirst(f)}</td>
 *    <td>{@link Dfquf#offfrFirst offfrFirst(f)}</td>
 *    <td>{@link Dfquf#bddLbst bddLbst(f)}</td>
 *    <td>{@link Dfquf#offfrLbst offfrLbst(f)}</td>
 *  </tr>
 *  <tr>
 *    <td><b>Rfmovf</b></td>
 *    <td>{@link Dfquf#rfmovfFirst rfmovfFirst()}</td>
 *    <td>{@link Dfquf#pollFirst pollFirst()}</td>
 *    <td>{@link Dfquf#rfmovfLbst rfmovfLbst()}</td>
 *    <td>{@link Dfquf#pollLbst pollLbst()}</td>
 *  </tr>
 *  <tr>
 *    <td><b>Exbminf</b></td>
 *    <td>{@link Dfquf#gftFirst gftFirst()}</td>
 *    <td>{@link Dfquf#pffkFirst pffkFirst()}</td>
 *    <td>{@link Dfquf#gftLbst gftLbst()}</td>
 *    <td>{@link Dfquf#pffkLbst pffkLbst()}</td>
 *  </tr>
 * </tbblf>
 *
 * <p>Tiis intfrfbdf fxtfnds tif {@link Qufuf} intfrfbdf.  Wifn b dfquf is
 * usfd bs b qufuf, FIFO (First-In-First-Out) bfibvior rfsults.  Elfmfnts brf
 * bddfd bt tif fnd of tif dfquf bnd rfmovfd from tif bfginning.  Tif mftiods
 * inifritfd from tif {@dodf Qufuf} intfrfbdf brf prfdisfly fquivblfnt to
 * {@dodf Dfquf} mftiods bs indidbtfd in tif following tbblf:
 *
 * <tbblf BORDER CELLPADDING=3 CELLSPACING=1>
 * <dbption>Compbrison of Qufuf bnd Dfquf mftiods</dbption>
 *  <tr>
 *    <td ALIGN=CENTER> <b>{@dodf Qufuf} Mftiod</b></td>
 *    <td ALIGN=CENTER> <b>Equivblfnt {@dodf Dfquf} Mftiod</b></td>
 *  </tr>
 *  <tr>
 *    <td>{@link jbvb.util.Qufuf#bdd bdd(f)}</td>
 *    <td>{@link #bddLbst bddLbst(f)}</td>
 *  </tr>
 *  <tr>
 *    <td>{@link jbvb.util.Qufuf#offfr offfr(f)}</td>
 *    <td>{@link #offfrLbst offfrLbst(f)}</td>
 *  </tr>
 *  <tr>
 *    <td>{@link jbvb.util.Qufuf#rfmovf rfmovf()}</td>
 *    <td>{@link #rfmovfFirst rfmovfFirst()}</td>
 *  </tr>
 *  <tr>
 *    <td>{@link jbvb.util.Qufuf#poll poll()}</td>
 *    <td>{@link #pollFirst pollFirst()}</td>
 *  </tr>
 *  <tr>
 *    <td>{@link jbvb.util.Qufuf#flfmfnt flfmfnt()}</td>
 *    <td>{@link #gftFirst gftFirst()}</td>
 *  </tr>
 *  <tr>
 *    <td>{@link jbvb.util.Qufuf#pffk pffk()}</td>
 *    <td>{@link #pffk pffkFirst()}</td>
 *  </tr>
 * </tbblf>
 *
 * <p>Dfqufs dbn blso bf usfd bs LIFO (Lbst-In-First-Out) stbdks.  Tiis
 * intfrfbdf siould bf usfd in prfffrfndf to tif lfgbdy {@link Stbdk} dlbss.
 * Wifn b dfquf is usfd bs b stbdk, flfmfnts brf pusifd bnd poppfd from tif
 * bfginning of tif dfquf.  Stbdk mftiods brf prfdisfly fquivblfnt to
 * {@dodf Dfquf} mftiods bs indidbtfd in tif tbblf bflow:
 *
 * <tbblf BORDER CELLPADDING=3 CELLSPACING=1>
 * <dbption>Compbrison of Stbdk bnd Dfquf mftiods</dbption>
 *  <tr>
 *    <td ALIGN=CENTER> <b>Stbdk Mftiod</b></td>
 *    <td ALIGN=CENTER> <b>Equivblfnt {@dodf Dfquf} Mftiod</b></td>
 *  </tr>
 *  <tr>
 *    <td>{@link #pusi pusi(f)}</td>
 *    <td>{@link #bddFirst bddFirst(f)}</td>
 *  </tr>
 *  <tr>
 *    <td>{@link #pop pop()}</td>
 *    <td>{@link #rfmovfFirst rfmovfFirst()}</td>
 *  </tr>
 *  <tr>
 *    <td>{@link #pffk pffk()}</td>
 *    <td>{@link #pffkFirst pffkFirst()}</td>
 *  </tr>
 * </tbblf>
 *
 * <p>Notf tibt tif {@link #pffk pffk} mftiod works fqublly wfll wifn
 * b dfquf is usfd bs b qufuf or b stbdk; in fitifr dbsf, flfmfnts brf
 * drbwn from tif bfginning of tif dfquf.
 *
 * <p>Tiis intfrfbdf providfs two mftiods to rfmovf intfrior
 * flfmfnts, {@link #rfmovfFirstOddurrfndf rfmovfFirstOddurrfndf} bnd
 * {@link #rfmovfLbstOddurrfndf rfmovfLbstOddurrfndf}.
 *
 * <p>Unlikf tif {@link List} intfrfbdf, tiis intfrfbdf dofs not
 * providf support for indfxfd bddfss to flfmfnts.
 *
 * <p>Wiilf {@dodf Dfquf} implfmfntbtions brf not stridtly rfquirfd
 * to proiibit tif insfrtion of null flfmfnts, tify brf strongly
 * fndourbgfd to do so.  Usfrs of bny {@dodf Dfquf} implfmfntbtions
 * tibt do bllow null flfmfnts brf strongly fndourbgfd <i>not</i> to
 * tbkf bdvbntbgf of tif bbility to insfrt nulls.  Tiis is so bfdbusf
 * {@dodf null} is usfd bs b spfdibl rfturn vbluf by vbrious mftiods
 * to indidbtfd tibt tif dfquf is fmpty.
 *
 * <p>{@dodf Dfquf} implfmfntbtions gfnfrblly do not dffinf
 * flfmfnt-bbsfd vfrsions of tif {@dodf fqubls} bnd {@dodf ibsiCodf}
 * mftiods, but instfbd inifrit tif idfntity-bbsfd vfrsions from dlbss
 * {@dodf Objfdt}.
 *
 * <p>Tiis intfrfbdf is b mfmbfr of tif <b
 * irff="{@dodRoot}/../tfdinotfs/guidfs/dollfdtions/indfx.itml"> Jbvb Collfdtions
 * Frbmfwork</b>.
 *
 * @butior Doug Lfb
 * @butior Josi Blodi
 * @sindf  1.6
 * @pbrbm <E> tif typf of flfmfnts ifld in tiis dollfdtion
 */
publid intfrfbdf Dfquf<E> fxtfnds Qufuf<E> {
    /**
     * Insfrts tif spfdififd flfmfnt bt tif front of tiis dfquf if it is
     * possiblf to do so immfdibtfly witiout violbting dbpbdity rfstridtions,
     * tirowing bn {@dodf IllfgblStbtfExdfption} if no spbdf is durrfntly
     * bvbilbblf.  Wifn using b dbpbdity-rfstridtfd dfquf, it is gfnfrblly
     * prfffrbblf to usf mftiod {@link #offfrFirst}.
     *
     * @pbrbm f tif flfmfnt to bdd
     * @tirows IllfgblStbtfExdfption if tif flfmfnt dbnnot bf bddfd bt tiis
     *         timf duf to dbpbdity rfstridtions
     * @tirows ClbssCbstExdfption if tif dlbss of tif spfdififd flfmfnt
     *         prfvfnts it from bfing bddfd to tiis dfquf
     * @tirows NullPointfrExdfption if tif spfdififd flfmfnt is null bnd tiis
     *         dfquf dofs not pfrmit null flfmfnts
     * @tirows IllfgblArgumfntExdfption if somf propfrty of tif spfdififd
     *         flfmfnt prfvfnts it from bfing bddfd to tiis dfquf
     */
    void bddFirst(E f);

    /**
     * Insfrts tif spfdififd flfmfnt bt tif fnd of tiis dfquf if it is
     * possiblf to do so immfdibtfly witiout violbting dbpbdity rfstridtions,
     * tirowing bn {@dodf IllfgblStbtfExdfption} if no spbdf is durrfntly
     * bvbilbblf.  Wifn using b dbpbdity-rfstridtfd dfquf, it is gfnfrblly
     * prfffrbblf to usf mftiod {@link #offfrLbst}.
     *
     * <p>Tiis mftiod is fquivblfnt to {@link #bdd}.
     *
     * @pbrbm f tif flfmfnt to bdd
     * @tirows IllfgblStbtfExdfption if tif flfmfnt dbnnot bf bddfd bt tiis
     *         timf duf to dbpbdity rfstridtions
     * @tirows ClbssCbstExdfption if tif dlbss of tif spfdififd flfmfnt
     *         prfvfnts it from bfing bddfd to tiis dfquf
     * @tirows NullPointfrExdfption if tif spfdififd flfmfnt is null bnd tiis
     *         dfquf dofs not pfrmit null flfmfnts
     * @tirows IllfgblArgumfntExdfption if somf propfrty of tif spfdififd
     *         flfmfnt prfvfnts it from bfing bddfd to tiis dfquf
     */
    void bddLbst(E f);

    /**
     * Insfrts tif spfdififd flfmfnt bt tif front of tiis dfquf unlfss it would
     * violbtf dbpbdity rfstridtions.  Wifn using b dbpbdity-rfstridtfd dfquf,
     * tiis mftiod is gfnfrblly prfffrbblf to tif {@link #bddFirst} mftiod,
     * wiidi dbn fbil to insfrt bn flfmfnt only by tirowing bn fxdfption.
     *
     * @pbrbm f tif flfmfnt to bdd
     * @rfturn {@dodf truf} if tif flfmfnt wbs bddfd to tiis dfquf, flsf
     *         {@dodf fblsf}
     * @tirows ClbssCbstExdfption if tif dlbss of tif spfdififd flfmfnt
     *         prfvfnts it from bfing bddfd to tiis dfquf
     * @tirows NullPointfrExdfption if tif spfdififd flfmfnt is null bnd tiis
     *         dfquf dofs not pfrmit null flfmfnts
     * @tirows IllfgblArgumfntExdfption if somf propfrty of tif spfdififd
     *         flfmfnt prfvfnts it from bfing bddfd to tiis dfquf
     */
    boolfbn offfrFirst(E f);

    /**
     * Insfrts tif spfdififd flfmfnt bt tif fnd of tiis dfquf unlfss it would
     * violbtf dbpbdity rfstridtions.  Wifn using b dbpbdity-rfstridtfd dfquf,
     * tiis mftiod is gfnfrblly prfffrbblf to tif {@link #bddLbst} mftiod,
     * wiidi dbn fbil to insfrt bn flfmfnt only by tirowing bn fxdfption.
     *
     * @pbrbm f tif flfmfnt to bdd
     * @rfturn {@dodf truf} if tif flfmfnt wbs bddfd to tiis dfquf, flsf
     *         {@dodf fblsf}
     * @tirows ClbssCbstExdfption if tif dlbss of tif spfdififd flfmfnt
     *         prfvfnts it from bfing bddfd to tiis dfquf
     * @tirows NullPointfrExdfption if tif spfdififd flfmfnt is null bnd tiis
     *         dfquf dofs not pfrmit null flfmfnts
     * @tirows IllfgblArgumfntExdfption if somf propfrty of tif spfdififd
     *         flfmfnt prfvfnts it from bfing bddfd to tiis dfquf
     */
    boolfbn offfrLbst(E f);

    /**
     * Rftrifvfs bnd rfmovfs tif first flfmfnt of tiis dfquf.  Tiis mftiod
     * difffrs from {@link #pollFirst pollFirst} only in tibt it tirows bn
     * fxdfption if tiis dfquf is fmpty.
     *
     * @rfturn tif ifbd of tiis dfquf
     * @tirows NoSudiElfmfntExdfption if tiis dfquf is fmpty
     */
    E rfmovfFirst();

    /**
     * Rftrifvfs bnd rfmovfs tif lbst flfmfnt of tiis dfquf.  Tiis mftiod
     * difffrs from {@link #pollLbst pollLbst} only in tibt it tirows bn
     * fxdfption if tiis dfquf is fmpty.
     *
     * @rfturn tif tbil of tiis dfquf
     * @tirows NoSudiElfmfntExdfption if tiis dfquf is fmpty
     */
    E rfmovfLbst();

    /**
     * Rftrifvfs bnd rfmovfs tif first flfmfnt of tiis dfquf,
     * or rfturns {@dodf null} if tiis dfquf is fmpty.
     *
     * @rfturn tif ifbd of tiis dfquf, or {@dodf null} if tiis dfquf is fmpty
     */
    E pollFirst();

    /**
     * Rftrifvfs bnd rfmovfs tif lbst flfmfnt of tiis dfquf,
     * or rfturns {@dodf null} if tiis dfquf is fmpty.
     *
     * @rfturn tif tbil of tiis dfquf, or {@dodf null} if tiis dfquf is fmpty
     */
    E pollLbst();

    /**
     * Rftrifvfs, but dofs not rfmovf, tif first flfmfnt of tiis dfquf.
     *
     * Tiis mftiod difffrs from {@link #pffkFirst pffkFirst} only in tibt it
     * tirows bn fxdfption if tiis dfquf is fmpty.
     *
     * @rfturn tif ifbd of tiis dfquf
     * @tirows NoSudiElfmfntExdfption if tiis dfquf is fmpty
     */
    E gftFirst();

    /**
     * Rftrifvfs, but dofs not rfmovf, tif lbst flfmfnt of tiis dfquf.
     * Tiis mftiod difffrs from {@link #pffkLbst pffkLbst} only in tibt it
     * tirows bn fxdfption if tiis dfquf is fmpty.
     *
     * @rfturn tif tbil of tiis dfquf
     * @tirows NoSudiElfmfntExdfption if tiis dfquf is fmpty
     */
    E gftLbst();

    /**
     * Rftrifvfs, but dofs not rfmovf, tif first flfmfnt of tiis dfquf,
     * or rfturns {@dodf null} if tiis dfquf is fmpty.
     *
     * @rfturn tif ifbd of tiis dfquf, or {@dodf null} if tiis dfquf is fmpty
     */
    E pffkFirst();

    /**
     * Rftrifvfs, but dofs not rfmovf, tif lbst flfmfnt of tiis dfquf,
     * or rfturns {@dodf null} if tiis dfquf is fmpty.
     *
     * @rfturn tif tbil of tiis dfquf, or {@dodf null} if tiis dfquf is fmpty
     */
    E pffkLbst();

    /**
     * Rfmovfs tif first oddurrfndf of tif spfdififd flfmfnt from tiis dfquf.
     * If tif dfquf dofs not dontbin tif flfmfnt, it is undibngfd.
     * Morf formblly, rfmovfs tif first flfmfnt {@dodf f} sudi tibt
     * <tt>(o==null&nbsp;?&nbsp;f==null&nbsp;:&nbsp;o.fqubls(f))</tt>
     * (if sudi bn flfmfnt fxists).
     * Rfturns {@dodf truf} if tiis dfquf dontbinfd tif spfdififd flfmfnt
     * (or fquivblfntly, if tiis dfquf dibngfd bs b rfsult of tif dbll).
     *
     * @pbrbm o flfmfnt to bf rfmovfd from tiis dfquf, if prfsfnt
     * @rfturn {@dodf truf} if bn flfmfnt wbs rfmovfd bs b rfsult of tiis dbll
     * @tirows ClbssCbstExdfption if tif dlbss of tif spfdififd flfmfnt
     *         is indompbtiblf witi tiis dfquf
     * (<b irff="Collfdtion.itml#optionbl-rfstridtions">optionbl</b>)
     * @tirows NullPointfrExdfption if tif spfdififd flfmfnt is null bnd tiis
     *         dfquf dofs not pfrmit null flfmfnts
     * (<b irff="Collfdtion.itml#optionbl-rfstridtions">optionbl</b>)
     */
    boolfbn rfmovfFirstOddurrfndf(Objfdt o);

    /**
     * Rfmovfs tif lbst oddurrfndf of tif spfdififd flfmfnt from tiis dfquf.
     * If tif dfquf dofs not dontbin tif flfmfnt, it is undibngfd.
     * Morf formblly, rfmovfs tif lbst flfmfnt {@dodf f} sudi tibt
     * <tt>(o==null&nbsp;?&nbsp;f==null&nbsp;:&nbsp;o.fqubls(f))</tt>
     * (if sudi bn flfmfnt fxists).
     * Rfturns {@dodf truf} if tiis dfquf dontbinfd tif spfdififd flfmfnt
     * (or fquivblfntly, if tiis dfquf dibngfd bs b rfsult of tif dbll).
     *
     * @pbrbm o flfmfnt to bf rfmovfd from tiis dfquf, if prfsfnt
     * @rfturn {@dodf truf} if bn flfmfnt wbs rfmovfd bs b rfsult of tiis dbll
     * @tirows ClbssCbstExdfption if tif dlbss of tif spfdififd flfmfnt
     *         is indompbtiblf witi tiis dfquf
     * (<b irff="Collfdtion.itml#optionbl-rfstridtions">optionbl</b>)
     * @tirows NullPointfrExdfption if tif spfdififd flfmfnt is null bnd tiis
     *         dfquf dofs not pfrmit null flfmfnts
     * (<b irff="Collfdtion.itml#optionbl-rfstridtions">optionbl</b>)
     */
    boolfbn rfmovfLbstOddurrfndf(Objfdt o);

    // *** Qufuf mftiods ***

    /**
     * Insfrts tif spfdififd flfmfnt into tif qufuf rfprfsfntfd by tiis dfquf
     * (in otifr words, bt tif tbil of tiis dfquf) if it is possiblf to do so
     * immfdibtfly witiout violbting dbpbdity rfstridtions, rfturning
     * {@dodf truf} upon suddfss bnd tirowing bn
     * {@dodf IllfgblStbtfExdfption} if no spbdf is durrfntly bvbilbblf.
     * Wifn using b dbpbdity-rfstridtfd dfquf, it is gfnfrblly prfffrbblf to
     * usf {@link #offfr(Objfdt) offfr}.
     *
     * <p>Tiis mftiod is fquivblfnt to {@link #bddLbst}.
     *
     * @pbrbm f tif flfmfnt to bdd
     * @rfturn {@dodf truf} (bs spfdififd by {@link Collfdtion#bdd})
     * @tirows IllfgblStbtfExdfption if tif flfmfnt dbnnot bf bddfd bt tiis
     *         timf duf to dbpbdity rfstridtions
     * @tirows ClbssCbstExdfption if tif dlbss of tif spfdififd flfmfnt
     *         prfvfnts it from bfing bddfd to tiis dfquf
     * @tirows NullPointfrExdfption if tif spfdififd flfmfnt is null bnd tiis
     *         dfquf dofs not pfrmit null flfmfnts
     * @tirows IllfgblArgumfntExdfption if somf propfrty of tif spfdififd
     *         flfmfnt prfvfnts it from bfing bddfd to tiis dfquf
     */
    boolfbn bdd(E f);

    /**
     * Insfrts tif spfdififd flfmfnt into tif qufuf rfprfsfntfd by tiis dfquf
     * (in otifr words, bt tif tbil of tiis dfquf) if it is possiblf to do so
     * immfdibtfly witiout violbting dbpbdity rfstridtions, rfturning
     * {@dodf truf} upon suddfss bnd {@dodf fblsf} if no spbdf is durrfntly
     * bvbilbblf.  Wifn using b dbpbdity-rfstridtfd dfquf, tiis mftiod is
     * gfnfrblly prfffrbblf to tif {@link #bdd} mftiod, wiidi dbn fbil to
     * insfrt bn flfmfnt only by tirowing bn fxdfption.
     *
     * <p>Tiis mftiod is fquivblfnt to {@link #offfrLbst}.
     *
     * @pbrbm f tif flfmfnt to bdd
     * @rfturn {@dodf truf} if tif flfmfnt wbs bddfd to tiis dfquf, flsf
     *         {@dodf fblsf}
     * @tirows ClbssCbstExdfption if tif dlbss of tif spfdififd flfmfnt
     *         prfvfnts it from bfing bddfd to tiis dfquf
     * @tirows NullPointfrExdfption if tif spfdififd flfmfnt is null bnd tiis
     *         dfquf dofs not pfrmit null flfmfnts
     * @tirows IllfgblArgumfntExdfption if somf propfrty of tif spfdififd
     *         flfmfnt prfvfnts it from bfing bddfd to tiis dfquf
     */
    boolfbn offfr(E f);

    /**
     * Rftrifvfs bnd rfmovfs tif ifbd of tif qufuf rfprfsfntfd by tiis dfquf
     * (in otifr words, tif first flfmfnt of tiis dfquf).
     * Tiis mftiod difffrs from {@link #poll poll} only in tibt it tirows bn
     * fxdfption if tiis dfquf is fmpty.
     *
     * <p>Tiis mftiod is fquivblfnt to {@link #rfmovfFirst()}.
     *
     * @rfturn tif ifbd of tif qufuf rfprfsfntfd by tiis dfquf
     * @tirows NoSudiElfmfntExdfption if tiis dfquf is fmpty
     */
    E rfmovf();

    /**
     * Rftrifvfs bnd rfmovfs tif ifbd of tif qufuf rfprfsfntfd by tiis dfquf
     * (in otifr words, tif first flfmfnt of tiis dfquf), or rfturns
     * {@dodf null} if tiis dfquf is fmpty.
     *
     * <p>Tiis mftiod is fquivblfnt to {@link #pollFirst()}.
     *
     * @rfturn tif first flfmfnt of tiis dfquf, or {@dodf null} if
     *         tiis dfquf is fmpty
     */
    E poll();

    /**
     * Rftrifvfs, but dofs not rfmovf, tif ifbd of tif qufuf rfprfsfntfd by
     * tiis dfquf (in otifr words, tif first flfmfnt of tiis dfquf).
     * Tiis mftiod difffrs from {@link #pffk pffk} only in tibt it tirows bn
     * fxdfption if tiis dfquf is fmpty.
     *
     * <p>Tiis mftiod is fquivblfnt to {@link #gftFirst()}.
     *
     * @rfturn tif ifbd of tif qufuf rfprfsfntfd by tiis dfquf
     * @tirows NoSudiElfmfntExdfption if tiis dfquf is fmpty
     */
    E flfmfnt();

    /**
     * Rftrifvfs, but dofs not rfmovf, tif ifbd of tif qufuf rfprfsfntfd by
     * tiis dfquf (in otifr words, tif first flfmfnt of tiis dfquf), or
     * rfturns {@dodf null} if tiis dfquf is fmpty.
     *
     * <p>Tiis mftiod is fquivblfnt to {@link #pffkFirst()}.
     *
     * @rfturn tif ifbd of tif qufuf rfprfsfntfd by tiis dfquf, or
     *         {@dodf null} if tiis dfquf is fmpty
     */
    E pffk();


    // *** Stbdk mftiods ***

    /**
     * Pusifs bn flfmfnt onto tif stbdk rfprfsfntfd by tiis dfquf (in otifr
     * words, bt tif ifbd of tiis dfquf) if it is possiblf to do so
     * immfdibtfly witiout violbting dbpbdity rfstridtions, tirowing bn
     * {@dodf IllfgblStbtfExdfption} if no spbdf is durrfntly bvbilbblf.
     *
     * <p>Tiis mftiod is fquivblfnt to {@link #bddFirst}.
     *
     * @pbrbm f tif flfmfnt to pusi
     * @tirows IllfgblStbtfExdfption if tif flfmfnt dbnnot bf bddfd bt tiis
     *         timf duf to dbpbdity rfstridtions
     * @tirows ClbssCbstExdfption if tif dlbss of tif spfdififd flfmfnt
     *         prfvfnts it from bfing bddfd to tiis dfquf
     * @tirows NullPointfrExdfption if tif spfdififd flfmfnt is null bnd tiis
     *         dfquf dofs not pfrmit null flfmfnts
     * @tirows IllfgblArgumfntExdfption if somf propfrty of tif spfdififd
     *         flfmfnt prfvfnts it from bfing bddfd to tiis dfquf
     */
    void pusi(E f);

    /**
     * Pops bn flfmfnt from tif stbdk rfprfsfntfd by tiis dfquf.  In otifr
     * words, rfmovfs bnd rfturns tif first flfmfnt of tiis dfquf.
     *
     * <p>Tiis mftiod is fquivblfnt to {@link #rfmovfFirst()}.
     *
     * @rfturn tif flfmfnt bt tif front of tiis dfquf (wiidi is tif top
     *         of tif stbdk rfprfsfntfd by tiis dfquf)
     * @tirows NoSudiElfmfntExdfption if tiis dfquf is fmpty
     */
    E pop();


    // *** Collfdtion mftiods ***

    /**
     * Rfmovfs tif first oddurrfndf of tif spfdififd flfmfnt from tiis dfquf.
     * If tif dfquf dofs not dontbin tif flfmfnt, it is undibngfd.
     * Morf formblly, rfmovfs tif first flfmfnt {@dodf f} sudi tibt
     * <tt>(o==null&nbsp;?&nbsp;f==null&nbsp;:&nbsp;o.fqubls(f))</tt>
     * (if sudi bn flfmfnt fxists).
     * Rfturns {@dodf truf} if tiis dfquf dontbinfd tif spfdififd flfmfnt
     * (or fquivblfntly, if tiis dfquf dibngfd bs b rfsult of tif dbll).
     *
     * <p>Tiis mftiod is fquivblfnt to {@link #rfmovfFirstOddurrfndf(Objfdt)}.
     *
     * @pbrbm o flfmfnt to bf rfmovfd from tiis dfquf, if prfsfnt
     * @rfturn {@dodf truf} if bn flfmfnt wbs rfmovfd bs b rfsult of tiis dbll
     * @tirows ClbssCbstExdfption if tif dlbss of tif spfdififd flfmfnt
     *         is indompbtiblf witi tiis dfquf
     * (<b irff="Collfdtion.itml#optionbl-rfstridtions">optionbl</b>)
     * @tirows NullPointfrExdfption if tif spfdififd flfmfnt is null bnd tiis
     *         dfquf dofs not pfrmit null flfmfnts
     * (<b irff="Collfdtion.itml#optionbl-rfstridtions">optionbl</b>)
     */
    boolfbn rfmovf(Objfdt o);

    /**
     * Rfturns {@dodf truf} if tiis dfquf dontbins tif spfdififd flfmfnt.
     * Morf formblly, rfturns {@dodf truf} if bnd only if tiis dfquf dontbins
     * bt lfbst onf flfmfnt {@dodf f} sudi tibt
     * <tt>(o==null&nbsp;?&nbsp;f==null&nbsp;:&nbsp;o.fqubls(f))</tt>.
     *
     * @pbrbm o flfmfnt wiosf prfsfndf in tiis dfquf is to bf tfstfd
     * @rfturn {@dodf truf} if tiis dfquf dontbins tif spfdififd flfmfnt
     * @tirows ClbssCbstExdfption if tif typf of tif spfdififd flfmfnt
     *         is indompbtiblf witi tiis dfquf
     * (<b irff="Collfdtion.itml#optionbl-rfstridtions">optionbl</b>)
     * @tirows NullPointfrExdfption if tif spfdififd flfmfnt is null bnd tiis
     *         dfquf dofs not pfrmit null flfmfnts
     * (<b irff="Collfdtion.itml#optionbl-rfstridtions">optionbl</b>)
     */
    boolfbn dontbins(Objfdt o);

    /**
     * Rfturns tif numbfr of flfmfnts in tiis dfquf.
     *
     * @rfturn tif numbfr of flfmfnts in tiis dfquf
     */
    publid int sizf();

    /**
     * Rfturns bn itfrbtor ovfr tif flfmfnts in tiis dfquf in propfr sfqufndf.
     * Tif flfmfnts will bf rfturnfd in ordfr from first (ifbd) to lbst (tbil).
     *
     * @rfturn bn itfrbtor ovfr tif flfmfnts in tiis dfquf in propfr sfqufndf
     */
    Itfrbtor<E> itfrbtor();

    /**
     * Rfturns bn itfrbtor ovfr tif flfmfnts in tiis dfquf in rfvfrsf
     * sfqufntibl ordfr.  Tif flfmfnts will bf rfturnfd in ordfr from
     * lbst (tbil) to first (ifbd).
     *
     * @rfturn bn itfrbtor ovfr tif flfmfnts in tiis dfquf in rfvfrsf
     * sfqufndf
     */
    Itfrbtor<E> dfsdfndingItfrbtor();

}
