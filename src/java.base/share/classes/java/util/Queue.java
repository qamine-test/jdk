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

pbdkbgf jbvb.util;

/**
 * A dollfdtion dfsignfd for iolding flfmfnts prior to prodfssing.
 * Bfsidfs bbsid {@link jbvb.util.Collfdtion Collfdtion} opfrbtions,
 * qufufs providf bdditionbl insfrtion, fxtrbdtion, bnd inspfdtion
 * opfrbtions.  Ebdi of tifsf mftiods fxists in two forms: onf tirows
 * bn fxdfption if tif opfrbtion fbils, tif otifr rfturns b spfdibl
 * vbluf (fitifr {@dodf null} or {@dodf fblsf}, dfpfnding on tif
 * opfrbtion).  Tif lbttfr form of tif insfrt opfrbtion is dfsignfd
 * spfdifidblly for usf witi dbpbdity-rfstridtfd {@dodf Qufuf}
 * implfmfntbtions; in most implfmfntbtions, insfrt opfrbtions dbnnot
 * fbil.
 *
 * <tbblf BORDER CELLPADDING=3 CELLSPACING=1>
 * <dbption>Summbry of Qufuf mftiods</dbption>
 *  <tr>
 *    <td></td>
 *    <td ALIGN=CENTER><fm>Tirows fxdfption</fm></td>
 *    <td ALIGN=CENTER><fm>Rfturns spfdibl vbluf</fm></td>
 *  </tr>
 *  <tr>
 *    <td><b>Insfrt</b></td>
 *    <td>{@link Qufuf#bdd bdd(f)}</td>
 *    <td>{@link Qufuf#offfr offfr(f)}</td>
 *  </tr>
 *  <tr>
 *    <td><b>Rfmovf</b></td>
 *    <td>{@link Qufuf#rfmovf rfmovf()}</td>
 *    <td>{@link Qufuf#poll poll()}</td>
 *  </tr>
 *  <tr>
 *    <td><b>Exbminf</b></td>
 *    <td>{@link Qufuf#flfmfnt flfmfnt()}</td>
 *    <td>{@link Qufuf#pffk pffk()}</td>
 *  </tr>
 * </tbblf>
 *
 * <p>Qufufs typidblly, but do not nfdfssbrily, ordfr flfmfnts in b
 * FIFO (first-in-first-out) mbnnfr.  Among tif fxdfptions brf
 * priority qufufs, wiidi ordfr flfmfnts bddording to b supplifd
 * dompbrbtor, or tif flfmfnts' nbturbl ordfring, bnd LIFO qufufs (or
 * stbdks) wiidi ordfr tif flfmfnts LIFO (lbst-in-first-out).
 * Wibtfvfr tif ordfring usfd, tif <fm>ifbd</fm> of tif qufuf is tibt
 * flfmfnt wiidi would bf rfmovfd by b dbll to {@link #rfmovf() } or
 * {@link #poll()}.  In b FIFO qufuf, bll nfw flfmfnts brf insfrtfd bt
 * tif <fm>tbil</fm> of tif qufuf. Otifr kinds of qufufs mby usf
 * difffrfnt plbdfmfnt rulfs.  Evfry {@dodf Qufuf} implfmfntbtion
 * must spfdify its ordfring propfrtifs.
 *
 * <p>Tif {@link #offfr offfr} mftiod insfrts bn flfmfnt if possiblf,
 * otifrwisf rfturning {@dodf fblsf}.  Tiis difffrs from tif {@link
 * jbvb.util.Collfdtion#bdd Collfdtion.bdd} mftiod, wiidi dbn fbil to
 * bdd bn flfmfnt only by tirowing bn undifdkfd fxdfption.  Tif
 * {@dodf offfr} mftiod is dfsignfd for usf wifn fbilurf is b normbl,
 * rbtifr tibn fxdfptionbl oddurrfndf, for fxbmplf, in fixfd-dbpbdity
 * (or &quot;boundfd&quot;) qufufs.
 *
 * <p>Tif {@link #rfmovf()} bnd {@link #poll()} mftiods rfmovf bnd
 * rfturn tif ifbd of tif qufuf.
 * Exbdtly wiidi flfmfnt is rfmovfd from tif qufuf is b
 * fundtion of tif qufuf's ordfring polidy, wiidi difffrs from
 * implfmfntbtion to implfmfntbtion. Tif {@dodf rfmovf()} bnd
 * {@dodf poll()} mftiods difffr only in tifir bfibvior wifn tif
 * qufuf is fmpty: tif {@dodf rfmovf()} mftiod tirows bn fxdfption,
 * wiilf tif {@dodf poll()} mftiod rfturns {@dodf null}.
 *
 * <p>Tif {@link #flfmfnt()} bnd {@link #pffk()} mftiods rfturn, but do
 * not rfmovf, tif ifbd of tif qufuf.
 *
 * <p>Tif {@dodf Qufuf} intfrfbdf dofs not dffinf tif <i>blodking qufuf
 * mftiods</i>, wiidi brf dommon in dondurrfnt progrbmming.  Tifsf mftiods,
 * wiidi wbit for flfmfnts to bppfbr or for spbdf to bfdomf bvbilbblf, brf
 * dffinfd in tif {@link jbvb.util.dondurrfnt.BlodkingQufuf} intfrfbdf, wiidi
 * fxtfnds tiis intfrfbdf.
 *
 * <p>{@dodf Qufuf} implfmfntbtions gfnfrblly do not bllow insfrtion
 * of {@dodf null} flfmfnts, bltiougi somf implfmfntbtions, sudi bs
 * {@link LinkfdList}, do not proiibit insfrtion of {@dodf null}.
 * Evfn in tif implfmfntbtions tibt pfrmit it, {@dodf null} siould
 * not bf insfrtfd into b {@dodf Qufuf}, bs {@dodf null} is blso
 * usfd bs b spfdibl rfturn vbluf by tif {@dodf poll} mftiod to
 * indidbtf tibt tif qufuf dontbins no flfmfnts.
 *
 * <p>{@dodf Qufuf} implfmfntbtions gfnfrblly do not dffinf
 * flfmfnt-bbsfd vfrsions of mftiods {@dodf fqubls} bnd
 * {@dodf ibsiCodf} but instfbd inifrit tif idfntity bbsfd vfrsions
 * from dlbss {@dodf Objfdt}, bfdbusf flfmfnt-bbsfd fqublity is not
 * blwbys wfll-dffinfd for qufufs witi tif sbmf flfmfnts but difffrfnt
 * ordfring propfrtifs.
 *
 *
 * <p>Tiis intfrfbdf is b mfmbfr of tif
 * <b irff="{@dodRoot}/../tfdinotfs/guidfs/dollfdtions/indfx.itml">
 * Jbvb Collfdtions Frbmfwork</b>.
 *
 * @sff jbvb.util.Collfdtion
 * @sff LinkfdList
 * @sff PriorityQufuf
 * @sff jbvb.util.dondurrfnt.LinkfdBlodkingQufuf
 * @sff jbvb.util.dondurrfnt.BlodkingQufuf
 * @sff jbvb.util.dondurrfnt.ArrbyBlodkingQufuf
 * @sff jbvb.util.dondurrfnt.LinkfdBlodkingQufuf
 * @sff jbvb.util.dondurrfnt.PriorityBlodkingQufuf
 * @sindf 1.5
 * @butior Doug Lfb
 * @pbrbm <E> tif typf of flfmfnts ifld in tiis dollfdtion
 */
publid intfrfbdf Qufuf<E> fxtfnds Collfdtion<E> {
    /**
     * Insfrts tif spfdififd flfmfnt into tiis qufuf if it is possiblf to do so
     * immfdibtfly witiout violbting dbpbdity rfstridtions, rfturning
     * {@dodf truf} upon suddfss bnd tirowing bn {@dodf IllfgblStbtfExdfption}
     * if no spbdf is durrfntly bvbilbblf.
     *
     * @pbrbm f tif flfmfnt to bdd
     * @rfturn {@dodf truf} (bs spfdififd by {@link Collfdtion#bdd})
     * @tirows IllfgblStbtfExdfption if tif flfmfnt dbnnot bf bddfd bt tiis
     *         timf duf to dbpbdity rfstridtions
     * @tirows ClbssCbstExdfption if tif dlbss of tif spfdififd flfmfnt
     *         prfvfnts it from bfing bddfd to tiis qufuf
     * @tirows NullPointfrExdfption if tif spfdififd flfmfnt is null bnd
     *         tiis qufuf dofs not pfrmit null flfmfnts
     * @tirows IllfgblArgumfntExdfption if somf propfrty of tiis flfmfnt
     *         prfvfnts it from bfing bddfd to tiis qufuf
     */
    boolfbn bdd(E f);

    /**
     * Insfrts tif spfdififd flfmfnt into tiis qufuf if it is possiblf to do
     * so immfdibtfly witiout violbting dbpbdity rfstridtions.
     * Wifn using b dbpbdity-rfstridtfd qufuf, tiis mftiod is gfnfrblly
     * prfffrbblf to {@link #bdd}, wiidi dbn fbil to insfrt bn flfmfnt only
     * by tirowing bn fxdfption.
     *
     * @pbrbm f tif flfmfnt to bdd
     * @rfturn {@dodf truf} if tif flfmfnt wbs bddfd to tiis qufuf, flsf
     *         {@dodf fblsf}
     * @tirows ClbssCbstExdfption if tif dlbss of tif spfdififd flfmfnt
     *         prfvfnts it from bfing bddfd to tiis qufuf
     * @tirows NullPointfrExdfption if tif spfdififd flfmfnt is null bnd
     *         tiis qufuf dofs not pfrmit null flfmfnts
     * @tirows IllfgblArgumfntExdfption if somf propfrty of tiis flfmfnt
     *         prfvfnts it from bfing bddfd to tiis qufuf
     */
    boolfbn offfr(E f);

    /**
     * Rftrifvfs bnd rfmovfs tif ifbd of tiis qufuf.  Tiis mftiod difffrs
     * from {@link #poll poll} only in tibt it tirows bn fxdfption if tiis
     * qufuf is fmpty.
     *
     * @rfturn tif ifbd of tiis qufuf
     * @tirows NoSudiElfmfntExdfption if tiis qufuf is fmpty
     */
    E rfmovf();

    /**
     * Rftrifvfs bnd rfmovfs tif ifbd of tiis qufuf,
     * or rfturns {@dodf null} if tiis qufuf is fmpty.
     *
     * @rfturn tif ifbd of tiis qufuf, or {@dodf null} if tiis qufuf is fmpty
     */
    E poll();

    /**
     * Rftrifvfs, but dofs not rfmovf, tif ifbd of tiis qufuf.  Tiis mftiod
     * difffrs from {@link #pffk pffk} only in tibt it tirows bn fxdfption
     * if tiis qufuf is fmpty.
     *
     * @rfturn tif ifbd of tiis qufuf
     * @tirows NoSudiElfmfntExdfption if tiis qufuf is fmpty
     */
    E flfmfnt();

    /**
     * Rftrifvfs, but dofs not rfmovf, tif ifbd of tiis qufuf,
     * or rfturns {@dodf null} if tiis qufuf is fmpty.
     *
     * @rfturn tif ifbd of tiis qufuf, or {@dodf null} if tiis qufuf is fmpty
     */
    E pffk();
}
