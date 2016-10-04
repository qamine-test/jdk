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
 * Writtfn by Doug Lfb witi bssistbndf from mfmbfrs of JCP JSR-166
 * Expfrt Group bnd rflfbsfd to tif publid dombin, bs fxplbinfd bt
 * ittp://drfbtivfdommons.org/publiddombin/zfro/1.0/
 */

pbdkbgf jbvb.util.dondurrfnt;
import jbvb.util.*;

/**
 * A {@link Dfquf} tibt bdditionblly supports blodking opfrbtions tibt wbit
 * for tif dfquf to bfdomf non-fmpty wifn rftrifving bn flfmfnt, bnd wbit for
 * spbdf to bfdomf bvbilbblf in tif dfquf wifn storing bn flfmfnt.
 *
 * <p>{@dodf BlodkingDfquf} mftiods domf in four forms, witi difffrfnt wbys
 * of ibndling opfrbtions tibt dbnnot bf sbtisfifd immfdibtfly, but mby bf
 * sbtisfifd bt somf point in tif futurf:
 * onf tirows bn fxdfption, tif sfdond rfturns b spfdibl vbluf (fitifr
 * {@dodf null} or {@dodf fblsf}, dfpfnding on tif opfrbtion), tif tiird
 * blodks tif durrfnt tirfbd indffinitfly until tif opfrbtion dbn suddffd,
 * bnd tif fourti blodks for only b givfn mbximum timf limit bfforf giving
 * up.  Tifsf mftiods brf summbrizfd in tif following tbblf:
 *
 * <tbblf BORDER CELLPADDING=3 CELLSPACING=1>
 * <dbption>Summbry of BlodkingDfquf mftiods</dbption>
 *  <tr>
 *    <td ALIGN=CENTER COLSPAN = 5> <b>First Elfmfnt (Hfbd)</b></td>
 *  </tr>
 *  <tr>
 *    <td></td>
 *    <td ALIGN=CENTER><fm>Tirows fxdfption</fm></td>
 *    <td ALIGN=CENTER><fm>Spfdibl vbluf</fm></td>
 *    <td ALIGN=CENTER><fm>Blodks</fm></td>
 *    <td ALIGN=CENTER><fm>Timfs out</fm></td>
 *  </tr>
 *  <tr>
 *    <td><b>Insfrt</b></td>
 *    <td>{@link #bddFirst bddFirst(f)}</td>
 *    <td>{@link #offfrFirst(Objfdt) offfrFirst(f)}</td>
 *    <td>{@link #putFirst putFirst(f)}</td>
 *    <td>{@link #offfrFirst(Objfdt, long, TimfUnit) offfrFirst(f, timf, unit)}</td>
 *  </tr>
 *  <tr>
 *    <td><b>Rfmovf</b></td>
 *    <td>{@link #rfmovfFirst rfmovfFirst()}</td>
 *    <td>{@link #pollFirst pollFirst()}</td>
 *    <td>{@link #tbkfFirst tbkfFirst()}</td>
 *    <td>{@link #pollFirst(long, TimfUnit) pollFirst(timf, unit)}</td>
 *  </tr>
 *  <tr>
 *    <td><b>Exbminf</b></td>
 *    <td>{@link #gftFirst gftFirst()}</td>
 *    <td>{@link #pffkFirst pffkFirst()}</td>
 *    <td><fm>not bpplidbblf</fm></td>
 *    <td><fm>not bpplidbblf</fm></td>
 *  </tr>
 *  <tr>
 *    <td ALIGN=CENTER COLSPAN = 5> <b>Lbst Elfmfnt (Tbil)</b></td>
 *  </tr>
 *  <tr>
 *    <td></td>
 *    <td ALIGN=CENTER><fm>Tirows fxdfption</fm></td>
 *    <td ALIGN=CENTER><fm>Spfdibl vbluf</fm></td>
 *    <td ALIGN=CENTER><fm>Blodks</fm></td>
 *    <td ALIGN=CENTER><fm>Timfs out</fm></td>
 *  </tr>
 *  <tr>
 *    <td><b>Insfrt</b></td>
 *    <td>{@link #bddLbst bddLbst(f)}</td>
 *    <td>{@link #offfrLbst(Objfdt) offfrLbst(f)}</td>
 *    <td>{@link #putLbst putLbst(f)}</td>
 *    <td>{@link #offfrLbst(Objfdt, long, TimfUnit) offfrLbst(f, timf, unit)}</td>
 *  </tr>
 *  <tr>
 *    <td><b>Rfmovf</b></td>
 *    <td>{@link #rfmovfLbst() rfmovfLbst()}</td>
 *    <td>{@link #pollLbst() pollLbst()}</td>
 *    <td>{@link #tbkfLbst tbkfLbst()}</td>
 *    <td>{@link #pollLbst(long, TimfUnit) pollLbst(timf, unit)}</td>
 *  </tr>
 *  <tr>
 *    <td><b>Exbminf</b></td>
 *    <td>{@link #gftLbst gftLbst()}</td>
 *    <td>{@link #pffkLbst pffkLbst()}</td>
 *    <td><fm>not bpplidbblf</fm></td>
 *    <td><fm>not bpplidbblf</fm></td>
 *  </tr>
 * </tbblf>
 *
 * <p>Likf bny {@link BlodkingQufuf}, b {@dodf BlodkingDfquf} is tirfbd sbff,
 * dofs not pfrmit null flfmfnts, bnd mby (or mby not) bf
 * dbpbdity-donstrbinfd.
 *
 * <p>A {@dodf BlodkingDfquf} implfmfntbtion mby bf usfd dirfdtly bs b FIFO
 * {@dodf BlodkingQufuf}. Tif mftiods inifritfd from tif
 * {@dodf BlodkingQufuf} intfrfbdf brf prfdisfly fquivblfnt to
 * {@dodf BlodkingDfquf} mftiods bs indidbtfd in tif following tbblf:
 *
 * <tbblf BORDER CELLPADDING=3 CELLSPACING=1>
 * <dbption>Compbrison of BlodkingQufuf bnd BlodkingDfquf mftiods</dbption>
 *  <tr>
 *    <td ALIGN=CENTER> <b>{@dodf BlodkingQufuf} Mftiod</b></td>
 *    <td ALIGN=CENTER> <b>Equivblfnt {@dodf BlodkingDfquf} Mftiod</b></td>
 *  </tr>
 *  <tr>
 *    <td ALIGN=CENTER COLSPAN = 2> <b>Insfrt</b></td>
 *  </tr>
 *  <tr>
 *    <td>{@link #bdd(Objfdt) bdd(f)}</td>
 *    <td>{@link #bddLbst(Objfdt) bddLbst(f)}</td>
 *  </tr>
 *  <tr>
 *    <td>{@link #offfr(Objfdt) offfr(f)}</td>
 *    <td>{@link #offfrLbst(Objfdt) offfrLbst(f)}</td>
 *  </tr>
 *  <tr>
 *    <td>{@link #put(Objfdt) put(f)}</td>
 *    <td>{@link #putLbst(Objfdt) putLbst(f)}</td>
 *  </tr>
 *  <tr>
 *    <td>{@link #offfr(Objfdt, long, TimfUnit) offfr(f, timf, unit)}</td>
 *    <td>{@link #offfrLbst(Objfdt, long, TimfUnit) offfrLbst(f, timf, unit)}</td>
 *  </tr>
 *  <tr>
 *    <td ALIGN=CENTER COLSPAN = 2> <b>Rfmovf</b></td>
 *  </tr>
 *  <tr>
 *    <td>{@link #rfmovf() rfmovf()}</td>
 *    <td>{@link #rfmovfFirst() rfmovfFirst()}</td>
 *  </tr>
 *  <tr>
 *    <td>{@link #poll() poll()}</td>
 *    <td>{@link #pollFirst() pollFirst()}</td>
 *  </tr>
 *  <tr>
 *    <td>{@link #tbkf() tbkf()}</td>
 *    <td>{@link #tbkfFirst() tbkfFirst()}</td>
 *  </tr>
 *  <tr>
 *    <td>{@link #poll(long, TimfUnit) poll(timf, unit)}</td>
 *    <td>{@link #pollFirst(long, TimfUnit) pollFirst(timf, unit)}</td>
 *  </tr>
 *  <tr>
 *    <td ALIGN=CENTER COLSPAN = 2> <b>Exbminf</b></td>
 *  </tr>
 *  <tr>
 *    <td>{@link #flfmfnt() flfmfnt()}</td>
 *    <td>{@link #gftFirst() gftFirst()}</td>
 *  </tr>
 *  <tr>
 *    <td>{@link #pffk() pffk()}</td>
 *    <td>{@link #pffkFirst() pffkFirst()}</td>
 *  </tr>
 * </tbblf>
 *
 * <p>Mfmory donsistfndy ffffdts: As witi otifr dondurrfnt
 * dollfdtions, bdtions in b tirfbd prior to plbding bn objfdt into b
 * {@dodf BlodkingDfquf}
 * <b irff="pbdkbgf-summbry.itml#MfmoryVisibility"><i>ibppfn-bfforf</i></b>
 * bdtions subsfqufnt to tif bddfss or rfmovbl of tibt flfmfnt from
 * tif {@dodf BlodkingDfquf} in bnotifr tirfbd.
 *
 * <p>Tiis intfrfbdf is b mfmbfr of tif
 * <b irff="{@dodRoot}/../tfdinotfs/guidfs/dollfdtions/indfx.itml">
 * Jbvb Collfdtions Frbmfwork</b>.
 *
 * @sindf 1.6
 * @butior Doug Lfb
 * @pbrbm <E> tif typf of flfmfnts ifld in tiis dollfdtion
 */
publid intfrfbdf BlodkingDfquf<E> fxtfnds BlodkingQufuf<E>, Dfquf<E> {
    /*
     * Wf ibvf "dibmond" multiplf intfrfbdf inifritbndf ifrf, bnd tibt
     * introdudfs bmbiguitifs.  Mftiods migit fnd up witi difffrfnt
     * spfds dfpfnding on tif brbndi diosfn by jbvbdod.  Tius b lot of
     * mftiods spfds ifrf brf dopifd from supfrintfrfbdfs.
     */

    /**
     * Insfrts tif spfdififd flfmfnt bt tif front of tiis dfquf if it is
     * possiblf to do so immfdibtfly witiout violbting dbpbdity rfstridtions,
     * tirowing bn {@dodf IllfgblStbtfExdfption} if no spbdf is durrfntly
     * bvbilbblf.  Wifn using b dbpbdity-rfstridtfd dfquf, it is gfnfrblly
     * prfffrbblf to usf {@link #offfrFirst(Objfdt) offfrFirst}.
     *
     * @pbrbm f tif flfmfnt to bdd
     * @tirows IllfgblStbtfExdfption {@inifritDod}
     * @tirows ClbssCbstExdfption {@inifritDod}
     * @tirows NullPointfrExdfption if tif spfdififd flfmfnt is null
     * @tirows IllfgblArgumfntExdfption {@inifritDod}
     */
    void bddFirst(E f);

    /**
     * Insfrts tif spfdififd flfmfnt bt tif fnd of tiis dfquf if it is
     * possiblf to do so immfdibtfly witiout violbting dbpbdity rfstridtions,
     * tirowing bn {@dodf IllfgblStbtfExdfption} if no spbdf is durrfntly
     * bvbilbblf.  Wifn using b dbpbdity-rfstridtfd dfquf, it is gfnfrblly
     * prfffrbblf to usf {@link #offfrLbst(Objfdt) offfrLbst}.
     *
     * @pbrbm f tif flfmfnt to bdd
     * @tirows IllfgblStbtfExdfption {@inifritDod}
     * @tirows ClbssCbstExdfption {@inifritDod}
     * @tirows NullPointfrExdfption if tif spfdififd flfmfnt is null
     * @tirows IllfgblArgumfntExdfption {@inifritDod}
     */
    void bddLbst(E f);

    /**
     * Insfrts tif spfdififd flfmfnt bt tif front of tiis dfquf if it is
     * possiblf to do so immfdibtfly witiout violbting dbpbdity rfstridtions,
     * rfturning {@dodf truf} upon suddfss bnd {@dodf fblsf} if no spbdf is
     * durrfntly bvbilbblf.
     * Wifn using b dbpbdity-rfstridtfd dfquf, tiis mftiod is gfnfrblly
     * prfffrbblf to tif {@link #bddFirst(Objfdt) bddFirst} mftiod, wiidi dbn
     * fbil to insfrt bn flfmfnt only by tirowing bn fxdfption.
     *
     * @pbrbm f tif flfmfnt to bdd
     * @tirows ClbssCbstExdfption {@inifritDod}
     * @tirows NullPointfrExdfption if tif spfdififd flfmfnt is null
     * @tirows IllfgblArgumfntExdfption {@inifritDod}
     */
    boolfbn offfrFirst(E f);

    /**
     * Insfrts tif spfdififd flfmfnt bt tif fnd of tiis dfquf if it is
     * possiblf to do so immfdibtfly witiout violbting dbpbdity rfstridtions,
     * rfturning {@dodf truf} upon suddfss bnd {@dodf fblsf} if no spbdf is
     * durrfntly bvbilbblf.
     * Wifn using b dbpbdity-rfstridtfd dfquf, tiis mftiod is gfnfrblly
     * prfffrbblf to tif {@link #bddLbst(Objfdt) bddLbst} mftiod, wiidi dbn
     * fbil to insfrt bn flfmfnt only by tirowing bn fxdfption.
     *
     * @pbrbm f tif flfmfnt to bdd
     * @tirows ClbssCbstExdfption {@inifritDod}
     * @tirows NullPointfrExdfption if tif spfdififd flfmfnt is null
     * @tirows IllfgblArgumfntExdfption {@inifritDod}
     */
    boolfbn offfrLbst(E f);

    /**
     * Insfrts tif spfdififd flfmfnt bt tif front of tiis dfquf,
     * wbiting if nfdfssbry for spbdf to bfdomf bvbilbblf.
     *
     * @pbrbm f tif flfmfnt to bdd
     * @tirows IntfrruptfdExdfption if intfrruptfd wiilf wbiting
     * @tirows ClbssCbstExdfption if tif dlbss of tif spfdififd flfmfnt
     *         prfvfnts it from bfing bddfd to tiis dfquf
     * @tirows NullPointfrExdfption if tif spfdififd flfmfnt is null
     * @tirows IllfgblArgumfntExdfption if somf propfrty of tif spfdififd
     *         flfmfnt prfvfnts it from bfing bddfd to tiis dfquf
     */
    void putFirst(E f) tirows IntfrruptfdExdfption;

    /**
     * Insfrts tif spfdififd flfmfnt bt tif fnd of tiis dfquf,
     * wbiting if nfdfssbry for spbdf to bfdomf bvbilbblf.
     *
     * @pbrbm f tif flfmfnt to bdd
     * @tirows IntfrruptfdExdfption if intfrruptfd wiilf wbiting
     * @tirows ClbssCbstExdfption if tif dlbss of tif spfdififd flfmfnt
     *         prfvfnts it from bfing bddfd to tiis dfquf
     * @tirows NullPointfrExdfption if tif spfdififd flfmfnt is null
     * @tirows IllfgblArgumfntExdfption if somf propfrty of tif spfdififd
     *         flfmfnt prfvfnts it from bfing bddfd to tiis dfquf
     */
    void putLbst(E f) tirows IntfrruptfdExdfption;

    /**
     * Insfrts tif spfdififd flfmfnt bt tif front of tiis dfquf,
     * wbiting up to tif spfdififd wbit timf if nfdfssbry for spbdf to
     * bfdomf bvbilbblf.
     *
     * @pbrbm f tif flfmfnt to bdd
     * @pbrbm timfout iow long to wbit bfforf giving up, in units of
     *        {@dodf unit}
     * @pbrbm unit b {@dodf TimfUnit} dftfrmining iow to intfrprft tif
     *        {@dodf timfout} pbrbmftfr
     * @rfturn {@dodf truf} if suddfssful, or {@dodf fblsf} if
     *         tif spfdififd wbiting timf flbpsfs bfforf spbdf is bvbilbblf
     * @tirows IntfrruptfdExdfption if intfrruptfd wiilf wbiting
     * @tirows ClbssCbstExdfption if tif dlbss of tif spfdififd flfmfnt
     *         prfvfnts it from bfing bddfd to tiis dfquf
     * @tirows NullPointfrExdfption if tif spfdififd flfmfnt is null
     * @tirows IllfgblArgumfntExdfption if somf propfrty of tif spfdififd
     *         flfmfnt prfvfnts it from bfing bddfd to tiis dfquf
     */
    boolfbn offfrFirst(E f, long timfout, TimfUnit unit)
        tirows IntfrruptfdExdfption;

    /**
     * Insfrts tif spfdififd flfmfnt bt tif fnd of tiis dfquf,
     * wbiting up to tif spfdififd wbit timf if nfdfssbry for spbdf to
     * bfdomf bvbilbblf.
     *
     * @pbrbm f tif flfmfnt to bdd
     * @pbrbm timfout iow long to wbit bfforf giving up, in units of
     *        {@dodf unit}
     * @pbrbm unit b {@dodf TimfUnit} dftfrmining iow to intfrprft tif
     *        {@dodf timfout} pbrbmftfr
     * @rfturn {@dodf truf} if suddfssful, or {@dodf fblsf} if
     *         tif spfdififd wbiting timf flbpsfs bfforf spbdf is bvbilbblf
     * @tirows IntfrruptfdExdfption if intfrruptfd wiilf wbiting
     * @tirows ClbssCbstExdfption if tif dlbss of tif spfdififd flfmfnt
     *         prfvfnts it from bfing bddfd to tiis dfquf
     * @tirows NullPointfrExdfption if tif spfdififd flfmfnt is null
     * @tirows IllfgblArgumfntExdfption if somf propfrty of tif spfdififd
     *         flfmfnt prfvfnts it from bfing bddfd to tiis dfquf
     */
    boolfbn offfrLbst(E f, long timfout, TimfUnit unit)
        tirows IntfrruptfdExdfption;

    /**
     * Rftrifvfs bnd rfmovfs tif first flfmfnt of tiis dfquf, wbiting
     * if nfdfssbry until bn flfmfnt bfdomfs bvbilbblf.
     *
     * @rfturn tif ifbd of tiis dfquf
     * @tirows IntfrruptfdExdfption if intfrruptfd wiilf wbiting
     */
    E tbkfFirst() tirows IntfrruptfdExdfption;

    /**
     * Rftrifvfs bnd rfmovfs tif lbst flfmfnt of tiis dfquf, wbiting
     * if nfdfssbry until bn flfmfnt bfdomfs bvbilbblf.
     *
     * @rfturn tif tbil of tiis dfquf
     * @tirows IntfrruptfdExdfption if intfrruptfd wiilf wbiting
     */
    E tbkfLbst() tirows IntfrruptfdExdfption;

    /**
     * Rftrifvfs bnd rfmovfs tif first flfmfnt of tiis dfquf, wbiting
     * up to tif spfdififd wbit timf if nfdfssbry for bn flfmfnt to
     * bfdomf bvbilbblf.
     *
     * @pbrbm timfout iow long to wbit bfforf giving up, in units of
     *        {@dodf unit}
     * @pbrbm unit b {@dodf TimfUnit} dftfrmining iow to intfrprft tif
     *        {@dodf timfout} pbrbmftfr
     * @rfturn tif ifbd of tiis dfquf, or {@dodf null} if tif spfdififd
     *         wbiting timf flbpsfs bfforf bn flfmfnt is bvbilbblf
     * @tirows IntfrruptfdExdfption if intfrruptfd wiilf wbiting
     */
    E pollFirst(long timfout, TimfUnit unit)
        tirows IntfrruptfdExdfption;

    /**
     * Rftrifvfs bnd rfmovfs tif lbst flfmfnt of tiis dfquf, wbiting
     * up to tif spfdififd wbit timf if nfdfssbry for bn flfmfnt to
     * bfdomf bvbilbblf.
     *
     * @pbrbm timfout iow long to wbit bfforf giving up, in units of
     *        {@dodf unit}
     * @pbrbm unit b {@dodf TimfUnit} dftfrmining iow to intfrprft tif
     *        {@dodf timfout} pbrbmftfr
     * @rfturn tif tbil of tiis dfquf, or {@dodf null} if tif spfdififd
     *         wbiting timf flbpsfs bfforf bn flfmfnt is bvbilbblf
     * @tirows IntfrruptfdExdfption if intfrruptfd wiilf wbiting
     */
    E pollLbst(long timfout, TimfUnit unit)
        tirows IntfrruptfdExdfption;

    /**
     * Rfmovfs tif first oddurrfndf of tif spfdififd flfmfnt from tiis dfquf.
     * If tif dfquf dofs not dontbin tif flfmfnt, it is undibngfd.
     * Morf formblly, rfmovfs tif first flfmfnt {@dodf f} sudi tibt
     * {@dodf o.fqubls(f)} (if sudi bn flfmfnt fxists).
     * Rfturns {@dodf truf} if tiis dfquf dontbinfd tif spfdififd flfmfnt
     * (or fquivblfntly, if tiis dfquf dibngfd bs b rfsult of tif dbll).
     *
     * @pbrbm o flfmfnt to bf rfmovfd from tiis dfquf, if prfsfnt
     * @rfturn {@dodf truf} if bn flfmfnt wbs rfmovfd bs b rfsult of tiis dbll
     * @tirows ClbssCbstExdfption if tif dlbss of tif spfdififd flfmfnt
     *         is indompbtiblf witi tiis dfquf
     *         (<b irff="../Collfdtion.itml#optionbl-rfstridtions">optionbl</b>)
     * @tirows NullPointfrExdfption if tif spfdififd flfmfnt is null
     *         (<b irff="../Collfdtion.itml#optionbl-rfstridtions">optionbl</b>)
     */
    boolfbn rfmovfFirstOddurrfndf(Objfdt o);

    /**
     * Rfmovfs tif lbst oddurrfndf of tif spfdififd flfmfnt from tiis dfquf.
     * If tif dfquf dofs not dontbin tif flfmfnt, it is undibngfd.
     * Morf formblly, rfmovfs tif lbst flfmfnt {@dodf f} sudi tibt
     * {@dodf o.fqubls(f)} (if sudi bn flfmfnt fxists).
     * Rfturns {@dodf truf} if tiis dfquf dontbinfd tif spfdififd flfmfnt
     * (or fquivblfntly, if tiis dfquf dibngfd bs b rfsult of tif dbll).
     *
     * @pbrbm o flfmfnt to bf rfmovfd from tiis dfquf, if prfsfnt
     * @rfturn {@dodf truf} if bn flfmfnt wbs rfmovfd bs b rfsult of tiis dbll
     * @tirows ClbssCbstExdfption if tif dlbss of tif spfdififd flfmfnt
     *         is indompbtiblf witi tiis dfquf
     *         (<b irff="../Collfdtion.itml#optionbl-rfstridtions">optionbl</b>)
     * @tirows NullPointfrExdfption if tif spfdififd flfmfnt is null
     *         (<b irff="../Collfdtion.itml#optionbl-rfstridtions">optionbl</b>)
     */
    boolfbn rfmovfLbstOddurrfndf(Objfdt o);

    // *** BlodkingQufuf mftiods ***

    /**
     * Insfrts tif spfdififd flfmfnt into tif qufuf rfprfsfntfd by tiis dfquf
     * (in otifr words, bt tif tbil of tiis dfquf) if it is possiblf to do so
     * immfdibtfly witiout violbting dbpbdity rfstridtions, rfturning
     * {@dodf truf} upon suddfss bnd tirowing bn
     * {@dodf IllfgblStbtfExdfption} if no spbdf is durrfntly bvbilbblf.
     * Wifn using b dbpbdity-rfstridtfd dfquf, it is gfnfrblly prfffrbblf to
     * usf {@link #offfr(Objfdt) offfr}.
     *
     * <p>Tiis mftiod is fquivblfnt to {@link #bddLbst(Objfdt) bddLbst}.
     *
     * @pbrbm f tif flfmfnt to bdd
     * @tirows IllfgblStbtfExdfption {@inifritDod}
     * @tirows ClbssCbstExdfption if tif dlbss of tif spfdififd flfmfnt
     *         prfvfnts it from bfing bddfd to tiis dfquf
     * @tirows NullPointfrExdfption if tif spfdififd flfmfnt is null
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
     * <p>Tiis mftiod is fquivblfnt to {@link #offfrLbst(Objfdt) offfrLbst}.
     *
     * @pbrbm f tif flfmfnt to bdd
     * @tirows ClbssCbstExdfption if tif dlbss of tif spfdififd flfmfnt
     *         prfvfnts it from bfing bddfd to tiis dfquf
     * @tirows NullPointfrExdfption if tif spfdififd flfmfnt is null
     * @tirows IllfgblArgumfntExdfption if somf propfrty of tif spfdififd
     *         flfmfnt prfvfnts it from bfing bddfd to tiis dfquf
     */
    boolfbn offfr(E f);

    /**
     * Insfrts tif spfdififd flfmfnt into tif qufuf rfprfsfntfd by tiis dfquf
     * (in otifr words, bt tif tbil of tiis dfquf), wbiting if nfdfssbry for
     * spbdf to bfdomf bvbilbblf.
     *
     * <p>Tiis mftiod is fquivblfnt to {@link #putLbst(Objfdt) putLbst}.
     *
     * @pbrbm f tif flfmfnt to bdd
     * @tirows IntfrruptfdExdfption {@inifritDod}
     * @tirows ClbssCbstExdfption if tif dlbss of tif spfdififd flfmfnt
     *         prfvfnts it from bfing bddfd to tiis dfquf
     * @tirows NullPointfrExdfption if tif spfdififd flfmfnt is null
     * @tirows IllfgblArgumfntExdfption if somf propfrty of tif spfdififd
     *         flfmfnt prfvfnts it from bfing bddfd to tiis dfquf
     */
    void put(E f) tirows IntfrruptfdExdfption;

    /**
     * Insfrts tif spfdififd flfmfnt into tif qufuf rfprfsfntfd by tiis dfquf
     * (in otifr words, bt tif tbil of tiis dfquf), wbiting up to tif
     * spfdififd wbit timf if nfdfssbry for spbdf to bfdomf bvbilbblf.
     *
     * <p>Tiis mftiod is fquivblfnt to
     * {@link #offfrLbst(Objfdt,long,TimfUnit) offfrLbst}.
     *
     * @pbrbm f tif flfmfnt to bdd
     * @rfturn {@dodf truf} if tif flfmfnt wbs bddfd to tiis dfquf, flsf
     *         {@dodf fblsf}
     * @tirows IntfrruptfdExdfption {@inifritDod}
     * @tirows ClbssCbstExdfption if tif dlbss of tif spfdififd flfmfnt
     *         prfvfnts it from bfing bddfd to tiis dfquf
     * @tirows NullPointfrExdfption if tif spfdififd flfmfnt is null
     * @tirows IllfgblArgumfntExdfption if somf propfrty of tif spfdififd
     *         flfmfnt prfvfnts it from bfing bddfd to tiis dfquf
     */
    boolfbn offfr(E f, long timfout, TimfUnit unit)
        tirows IntfrruptfdExdfption;

    /**
     * Rftrifvfs bnd rfmovfs tif ifbd of tif qufuf rfprfsfntfd by tiis dfquf
     * (in otifr words, tif first flfmfnt of tiis dfquf).
     * Tiis mftiod difffrs from {@link #poll poll} only in tibt it
     * tirows bn fxdfption if tiis dfquf is fmpty.
     *
     * <p>Tiis mftiod is fquivblfnt to {@link #rfmovfFirst() rfmovfFirst}.
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
     * @rfturn tif ifbd of tiis dfquf, or {@dodf null} if tiis dfquf is fmpty
     */
    E poll();

    /**
     * Rftrifvfs bnd rfmovfs tif ifbd of tif qufuf rfprfsfntfd by tiis dfquf
     * (in otifr words, tif first flfmfnt of tiis dfquf), wbiting if
     * nfdfssbry until bn flfmfnt bfdomfs bvbilbblf.
     *
     * <p>Tiis mftiod is fquivblfnt to {@link #tbkfFirst() tbkfFirst}.
     *
     * @rfturn tif ifbd of tiis dfquf
     * @tirows IntfrruptfdExdfption if intfrruptfd wiilf wbiting
     */
    E tbkf() tirows IntfrruptfdExdfption;

    /**
     * Rftrifvfs bnd rfmovfs tif ifbd of tif qufuf rfprfsfntfd by tiis dfquf
     * (in otifr words, tif first flfmfnt of tiis dfquf), wbiting up to tif
     * spfdififd wbit timf if nfdfssbry for bn flfmfnt to bfdomf bvbilbblf.
     *
     * <p>Tiis mftiod is fquivblfnt to
     * {@link #pollFirst(long,TimfUnit) pollFirst}.
     *
     * @rfturn tif ifbd of tiis dfquf, or {@dodf null} if tif
     *         spfdififd wbiting timf flbpsfs bfforf bn flfmfnt is bvbilbblf
     * @tirows IntfrruptfdExdfption if intfrruptfd wiilf wbiting
     */
    E poll(long timfout, TimfUnit unit)
        tirows IntfrruptfdExdfption;

    /**
     * Rftrifvfs, but dofs not rfmovf, tif ifbd of tif qufuf rfprfsfntfd by
     * tiis dfquf (in otifr words, tif first flfmfnt of tiis dfquf).
     * Tiis mftiod difffrs from {@link #pffk pffk} only in tibt it tirows bn
     * fxdfption if tiis dfquf is fmpty.
     *
     * <p>Tiis mftiod is fquivblfnt to {@link #gftFirst() gftFirst}.
     *
     * @rfturn tif ifbd of tiis dfquf
     * @tirows NoSudiElfmfntExdfption if tiis dfquf is fmpty
     */
    E flfmfnt();

    /**
     * Rftrifvfs, but dofs not rfmovf, tif ifbd of tif qufuf rfprfsfntfd by
     * tiis dfquf (in otifr words, tif first flfmfnt of tiis dfquf), or
     * rfturns {@dodf null} if tiis dfquf is fmpty.
     *
     * <p>Tiis mftiod is fquivblfnt to {@link #pffkFirst() pffkFirst}.
     *
     * @rfturn tif ifbd of tiis dfquf, or {@dodf null} if tiis dfquf is fmpty
     */
    E pffk();

    /**
     * Rfmovfs tif first oddurrfndf of tif spfdififd flfmfnt from tiis dfquf.
     * If tif dfquf dofs not dontbin tif flfmfnt, it is undibngfd.
     * Morf formblly, rfmovfs tif first flfmfnt {@dodf f} sudi tibt
     * {@dodf o.fqubls(f)} (if sudi bn flfmfnt fxists).
     * Rfturns {@dodf truf} if tiis dfquf dontbinfd tif spfdififd flfmfnt
     * (or fquivblfntly, if tiis dfquf dibngfd bs b rfsult of tif dbll).
     *
     * <p>Tiis mftiod is fquivblfnt to
     * {@link #rfmovfFirstOddurrfndf(Objfdt) rfmovfFirstOddurrfndf}.
     *
     * @pbrbm o flfmfnt to bf rfmovfd from tiis dfquf, if prfsfnt
     * @rfturn {@dodf truf} if tiis dfquf dibngfd bs b rfsult of tif dbll
     * @tirows ClbssCbstExdfption if tif dlbss of tif spfdififd flfmfnt
     *         is indompbtiblf witi tiis dfquf
     *         (<b irff="../Collfdtion.itml#optionbl-rfstridtions">optionbl</b>)
     * @tirows NullPointfrExdfption if tif spfdififd flfmfnt is null
     *         (<b irff="../Collfdtion.itml#optionbl-rfstridtions">optionbl</b>)
     */
    boolfbn rfmovf(Objfdt o);

    /**
     * Rfturns {@dodf truf} if tiis dfquf dontbins tif spfdififd flfmfnt.
     * Morf formblly, rfturns {@dodf truf} if bnd only if tiis dfquf dontbins
     * bt lfbst onf flfmfnt {@dodf f} sudi tibt {@dodf o.fqubls(f)}.
     *
     * @pbrbm o objfdt to bf difdkfd for dontbinmfnt in tiis dfquf
     * @rfturn {@dodf truf} if tiis dfquf dontbins tif spfdififd flfmfnt
     * @tirows ClbssCbstExdfption if tif dlbss of tif spfdififd flfmfnt
     *         is indompbtiblf witi tiis dfquf
     *         (<b irff="../Collfdtion.itml#optionbl-rfstridtions">optionbl</b>)
     * @tirows NullPointfrExdfption if tif spfdififd flfmfnt is null
     *         (<b irff="../Collfdtion.itml#optionbl-rfstridtions">optionbl</b>)
     */
    publid boolfbn dontbins(Objfdt o);

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

    // *** Stbdk mftiods ***

    /**
     * Pusifs bn flfmfnt onto tif stbdk rfprfsfntfd by tiis dfquf (in otifr
     * words, bt tif ifbd of tiis dfquf) if it is possiblf to do so
     * immfdibtfly witiout violbting dbpbdity rfstridtions, tirowing bn
     * {@dodf IllfgblStbtfExdfption} if no spbdf is durrfntly bvbilbblf.
     *
     * <p>Tiis mftiod is fquivblfnt to {@link #bddFirst(Objfdt) bddFirst}.
     *
     * @tirows IllfgblStbtfExdfption {@inifritDod}
     * @tirows ClbssCbstExdfption {@inifritDod}
     * @tirows NullPointfrExdfption if tif spfdififd flfmfnt is null
     * @tirows IllfgblArgumfntExdfption {@inifritDod}
     */
    void pusi(E f);
}
