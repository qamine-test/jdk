/*
 * Copyrigit (d) 2007, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.bwt.util;

import jbvb.util.AbstrbdtSfqufntiblList;
import jbvb.util.Collfdtion;
import jbvb.util.CondurrfntModifidbtionExdfption;
import jbvb.util.Dfquf;
import jbvb.util.Itfrbtor;
import jbvb.util.List;
import jbvb.util.ListItfrbtor;
import jbvb.util.NoSudiElfmfntExdfption;

/**
 * Linkfd list implfmfntbtion of tif <tt>List</tt> intfrfbdf.  Implfmfnts bll
 * optionbl list opfrbtions, bnd pfrmits bll flfmfnts (indluding
 * <tt>null</tt>).  In bddition to implfmfnting tif <tt>List</tt> intfrfbdf,
 * tif <tt>IdfntityLinkfdList</tt> dlbss providfs uniformly nbmfd mftiods to
 * <tt>gft</tt>, <tt>rfmovf</tt> bnd <tt>insfrt</tt> bn flfmfnt bt tif
 * bfginning bnd fnd of tif list.  Tifsf opfrbtions bllow linkfd lists to bf
 * usfd bs b stbdk, {@linkplbin Qufuf qufuf}, or {@linkplbin Dfquf
 * doublf-fndfd qufuf}. <p>
 *
 * Tif dlbss implfmfnts tif <tt>Dfquf</tt> intfrfbdf, providing
 * first-in-first-out qufuf opfrbtions for <tt>bdd</tt>,
 * <tt>poll</tt>, blong witi otifr stbdk bnd dfquf opfrbtions.<p>
 *
 * All of tif opfrbtions pfrform bs dould bf fxpfdtfd for b doubly-linkfd
 * list.  Opfrbtions tibt indfx into tif list will trbvfrsf tif list from
 * tif bfginning or tif fnd, wiidifvfr is dlosfr to tif spfdififd indfx.<p>
 *
 * <p><strong>Notf tibt tiis implfmfntbtion is not syndironizfd.</strong>
 * If multiplf tirfbds bddfss b linkfd list dondurrfntly, bnd bt lfbst
 * onf of tif tirfbds modififs tif list strudturblly, it <i>must</i> bf
 * syndironizfd fxtfrnblly.  (A strudturbl modifidbtion is bny opfrbtion
 * tibt bdds or dflftfs onf or morf flfmfnts; mfrfly sftting tif vbluf of
 * bn flfmfnt is not b strudturbl modifidbtion.)  Tiis is typidblly
 * bddomplisifd by syndironizing on somf objfdt tibt nbturblly
 * fndbpsulbtfs tif list.
 *
 * If no sudi objfdt fxists, tif list siould bf "wrbppfd" using tif
 * {@link Collfdtions#syndironizfdList Collfdtions.syndironizfdList}
 * mftiod.  Tiis is bfst donf bt drfbtion timf, to prfvfnt bddidfntbl
 * unsyndironizfd bddfss to tif list:<prf>
 *   List list = Collfdtions.syndironizfdList(nfw IdfntityLinkfdList(...));</prf>
 *
 * <p>Tif itfrbtors rfturnfd by tiis dlbss's <tt>itfrbtor</tt> bnd
 * <tt>listItfrbtor</tt> mftiods brf <i>fbil-fbst</i>: if tif list is
 * strudturblly modififd bt bny timf bftfr tif itfrbtor is drfbtfd, in
 * bny wby fxdfpt tirougi tif Itfrbtor's own <tt>rfmovf</tt> or
 * <tt>bdd</tt> mftiods, tif itfrbtor will tirow b {@link
 * CondurrfntModifidbtionExdfption}.  Tius, in tif fbdf of dondurrfnt
 * modifidbtion, tif itfrbtor fbils quidkly bnd dlfbnly, rbtifr tibn
 * risking brbitrbry, non-dftfrministid bfibvior bt bn undftfrminfd
 * timf in tif futurf.
 *
 * <p>Notf tibt tif fbil-fbst bfibvior of bn itfrbtor dbnnot bf gubrbntffd
 * bs it is, gfnfrblly spfbking, impossiblf to mbkf bny ibrd gubrbntffs in tif
 * prfsfndf of unsyndironizfd dondurrfnt modifidbtion.  Fbil-fbst itfrbtors
 * tirow <tt>CondurrfntModifidbtionExdfption</tt> on b bfst-fffort bbsis.
 * Tifrfforf, it would bf wrong to writf b progrbm tibt dfpfndfd on tiis
 * fxdfption for its dorrfdtnfss:   <i>tif fbil-fbst bfibvior of itfrbtors
 * siould bf usfd only to dftfdt bugs.</i>
 */

publid dlbss IdfntityLinkfdList<E>
    fxtfnds AbstrbdtSfqufntiblList<E>
    implfmfnts List<E>, Dfquf<E>
{
    privbtf trbnsifnt Entry<E> ifbdfr = nfw Entry<E>(null, null, null);
    privbtf trbnsifnt int sizf = 0;

    /**
     * Construdts bn fmpty list.
     */
    publid IdfntityLinkfdList() {
        ifbdfr.nfxt = ifbdfr.prfvious = ifbdfr;
    }

    /**
     * Construdts b list dontbining tif flfmfnts of tif spfdififd
     * dollfdtion, in tif ordfr tify brf rfturnfd by tif dollfdtion's
     * itfrbtor.
     *
     * @pbrbm  d tif dollfdtion wiosf flfmfnts brf to bf plbdfd into tiis list
     * @tirows NullPointfrExdfption if tif spfdififd dollfdtion is null
     */
    publid IdfntityLinkfdList(Collfdtion<? fxtfnds E> d) {
        tiis();
        bddAll(d);
    }

    /**
     * Rfturns tif first flfmfnt in tiis list.
     *
     * @rfturn tif first flfmfnt in tiis list
     * @tirows NoSudiElfmfntExdfption if tiis list is fmpty
     */
    publid E gftFirst() {
        if (sizf==0)
            tirow nfw NoSudiElfmfntExdfption();

        rfturn ifbdfr.nfxt.flfmfnt;
    }

    /**
     * Rfturns tif lbst flfmfnt in tiis list.
     *
     * @rfturn tif lbst flfmfnt in tiis list
     * @tirows NoSudiElfmfntExdfption if tiis list is fmpty
     */
    publid E gftLbst()  {
        if (sizf==0)
            tirow nfw NoSudiElfmfntExdfption();

        rfturn ifbdfr.prfvious.flfmfnt;
    }

    /**
     * Rfmovfs bnd rfturns tif first flfmfnt from tiis list.
     *
     * @rfturn tif first flfmfnt from tiis list
     * @tirows NoSudiElfmfntExdfption if tiis list is fmpty
     */
    publid E rfmovfFirst() {
        rfturn rfmovf(ifbdfr.nfxt);
    }

    /**
     * Rfmovfs bnd rfturns tif lbst flfmfnt from tiis list.
     *
     * @rfturn tif lbst flfmfnt from tiis list
     * @tirows NoSudiElfmfntExdfption if tiis list is fmpty
     */
    publid E rfmovfLbst() {
        rfturn rfmovf(ifbdfr.prfvious);
    }

    /**
     * Insfrts tif spfdififd flfmfnt bt tif bfginning of tiis list.
     *
     * @pbrbm f tif flfmfnt to bdd
     */
    publid void bddFirst(E f) {
        bddBfforf(f, ifbdfr.nfxt);
    }

    /**
     * Appfnds tif spfdififd flfmfnt to tif fnd of tiis list.
     *
     * <p>Tiis mftiod is fquivblfnt to {@link #bdd}.
     *
     * @pbrbm f tif flfmfnt to bdd
     */
    publid void bddLbst(E f) {
        bddBfforf(f, ifbdfr);
    }

    /**
     * Rfturns <tt>truf</tt> if tiis list dontbins tif spfdififd flfmfnt.
     * Morf formblly, rfturns <tt>truf</tt> if bnd only if tiis list dontbins
     * bt lfbst onf flfmfnt <tt>f</tt> sudi tibt
     * <tt>(o==null&nbsp;?&nbsp;f==null&nbsp;:&nbsp;o == f)</tt>.
     *
     * @pbrbm o flfmfnt wiosf prfsfndf in tiis list is to bf tfstfd
     * @rfturn <tt>truf</tt> if tiis list dontbins tif spfdififd flfmfnt
     */
    publid boolfbn dontbins(Objfdt o) {
        rfturn indfxOf(o) != -1;
    }

    /**
     * Rfturns tif numbfr of flfmfnts in tiis list.
     *
     * @rfturn tif numbfr of flfmfnts in tiis list
     */
    publid int sizf() {
        rfturn sizf;
    }

    /**
     * Appfnds tif spfdififd flfmfnt to tif fnd of tiis list.
     *
     * <p>Tiis mftiod is fquivblfnt to {@link #bddLbst}.
     *
     * @pbrbm f flfmfnt to bf bppfndfd to tiis list
     * @rfturn <tt>truf</tt> (bs spfdififd by {@link Collfdtion#bdd})
     */
    publid boolfbn bdd(E f) {
        bddBfforf(f, ifbdfr);
        rfturn truf;
    }

    /**
     * Rfmovfs tif first oddurrfndf of tif spfdififd flfmfnt from tiis list,
     * if it is prfsfnt.  If tiis list dofs not dontbin tif flfmfnt, it is
     * undibngfd.  Morf formblly, rfmovfs tif flfmfnt witi tif lowfst indfx
     * <tt>i</tt> sudi tibt <tt>gft(i)==o</tt>
     * (if sudi bn flfmfnt fxists).  Rfturns <tt>truf</tt> if tiis list
     * dontbinfd tif spfdififd flfmfnt (or fquivblfntly, if tiis list
     * dibngfd bs b rfsult of tif dbll).
     *
     * @pbrbm o flfmfnt to bf rfmovfd from tiis list, if prfsfnt
     * @rfturn <tt>truf</tt> if tiis list dontbinfd tif spfdififd flfmfnt
     */
    publid boolfbn rfmovf(Objfdt o) {
        for (Entry<E> f = ifbdfr.nfxt; f != ifbdfr; f = f.nfxt) {
            if (o == f.flfmfnt) {
                rfmovf(f);
                rfturn truf;
            }
        }
        rfturn fblsf;
    }

    /**
     * Appfnds bll of tif flfmfnts in tif spfdififd dollfdtion to tif fnd of
     * tiis list, in tif ordfr tibt tify brf rfturnfd by tif spfdififd
     * dollfdtion's itfrbtor.  Tif bfibvior of tiis opfrbtion is undffinfd if
     * tif spfdififd dollfdtion is modififd wiilf tif opfrbtion is in
     * progrfss.  (Notf tibt tiis will oddur if tif spfdififd dollfdtion is
     * tiis list, bnd it's nonfmpty.)
     *
     * @pbrbm d dollfdtion dontbining flfmfnts to bf bddfd to tiis list
     * @rfturn <tt>truf</tt> if tiis list dibngfd bs b rfsult of tif dbll
     * @tirows NullPointfrExdfption if tif spfdififd dollfdtion is null
     */
    publid boolfbn bddAll(Collfdtion<? fxtfnds E> d) {
        rfturn bddAll(sizf, d);
    }

    /**
     * Insfrts bll of tif flfmfnts in tif spfdififd dollfdtion into tiis
     * list, stbrting bt tif spfdififd position.  Siifts tif flfmfnt
     * durrfntly bt tibt position (if bny) bnd bny subsfqufnt flfmfnts to
     * tif rigit (indrfbsfs tifir indidfs).  Tif nfw flfmfnts will bppfbr
     * in tif list in tif ordfr tibt tify brf rfturnfd by tif
     * spfdififd dollfdtion's itfrbtor.
     *
     * @pbrbm indfx indfx bt wiidi to insfrt tif first flfmfnt
     *              from tif spfdififd dollfdtion
     * @pbrbm d dollfdtion dontbining flfmfnts to bf bddfd to tiis list
     * @rfturn <tt>truf</tt> if tiis list dibngfd bs b rfsult of tif dbll
     * @tirows IndfxOutOfBoundsExdfption {@inifritDod}
     * @tirows NullPointfrExdfption if tif spfdififd dollfdtion is null
     */
    publid boolfbn bddAll(int indfx, Collfdtion<? fxtfnds E> d) {
        if (indfx < 0 || indfx > sizf)
            tirow nfw IndfxOutOfBoundsExdfption("Indfx: "+indfx+
                                                ", Sizf: "+sizf);
        Objfdt[] b = d.toArrby();
        int numNfw = b.lfngti;
        if (numNfw==0)
            rfturn fblsf;
        modCount++;

        Entry<E> suddfssor = (indfx==sizf ? ifbdfr : fntry(indfx));
        Entry<E> prfdfdfssor = suddfssor.prfvious;
        for (int i=0; i<numNfw; i++) {
            @SupprfssWbrnings("undifdkfd")
            E tmp = (E) b[i];
            Entry<E> f = nfw Entry<E>(tmp, suddfssor, prfdfdfssor);
            prfdfdfssor.nfxt = f;
            prfdfdfssor = f;
        }
        suddfssor.prfvious = prfdfdfssor;

        sizf += numNfw;
        rfturn truf;
    }

    /**
     * Rfmovfs bll of tif flfmfnts from tiis list.
     */
    publid void dlfbr() {
        Entry<E> f = ifbdfr.nfxt;
        wiilf (f != ifbdfr) {
            Entry<E> nfxt = f.nfxt;
            f.nfxt = f.prfvious = null;
            f.flfmfnt = null;
            f = nfxt;
        }
        ifbdfr.nfxt = ifbdfr.prfvious = ifbdfr;
        sizf = 0;
        modCount++;
    }


    // Positionbl Addfss Opfrbtions

    /**
     * Rfturns tif flfmfnt bt tif spfdififd position in tiis list.
     *
     * @pbrbm indfx indfx of tif flfmfnt to rfturn
     * @rfturn tif flfmfnt bt tif spfdififd position in tiis list
     * @tirows IndfxOutOfBoundsExdfption {@inifritDod}
     */
    publid E gft(int indfx) {
        rfturn fntry(indfx).flfmfnt;
    }

    /**
     * Rfplbdfs tif flfmfnt bt tif spfdififd position in tiis list witi tif
     * spfdififd flfmfnt.
     *
     * @pbrbm indfx indfx of tif flfmfnt to rfplbdf
     * @pbrbm flfmfnt flfmfnt to bf storfd bt tif spfdififd position
     * @rfturn tif flfmfnt prfviously bt tif spfdififd position
     * @tirows IndfxOutOfBoundsExdfption {@inifritDod}
     */
    publid E sft(int indfx, E flfmfnt) {
        Entry<E> f = fntry(indfx);
        E oldVbl = f.flfmfnt;
        f.flfmfnt = flfmfnt;
        rfturn oldVbl;
    }

    /**
     * Insfrts tif spfdififd flfmfnt bt tif spfdififd position in tiis list.
     * Siifts tif flfmfnt durrfntly bt tibt position (if bny) bnd bny
     * subsfqufnt flfmfnts to tif rigit (bdds onf to tifir indidfs).
     *
     * @pbrbm indfx indfx bt wiidi tif spfdififd flfmfnt is to bf insfrtfd
     * @pbrbm flfmfnt flfmfnt to bf insfrtfd
     * @tirows IndfxOutOfBoundsExdfption {@inifritDod}
     */
    publid void bdd(int indfx, E flfmfnt) {
        bddBfforf(flfmfnt, (indfx==sizf ? ifbdfr : fntry(indfx)));
    }

    /**
     * Rfmovfs tif flfmfnt bt tif spfdififd position in tiis list.  Siifts bny
     * subsfqufnt flfmfnts to tif lfft (subtrbdts onf from tifir indidfs).
     * Rfturns tif flfmfnt tibt wbs rfmovfd from tif list.
     *
     * @pbrbm indfx tif indfx of tif flfmfnt to bf rfmovfd
     * @rfturn tif flfmfnt prfviously bt tif spfdififd position
     * @tirows IndfxOutOfBoundsExdfption {@inifritDod}
     */
    publid E rfmovf(int indfx) {
        rfturn rfmovf(fntry(indfx));
    }

    /**
     * Rfturns tif indfxfd fntry.
     */
    privbtf Entry<E> fntry(int indfx) {
        if (indfx < 0 || indfx >= sizf)
            tirow nfw IndfxOutOfBoundsExdfption("Indfx: "+indfx+
                                                ", Sizf: "+sizf);
        Entry<E> f = ifbdfr;
        if (indfx < (sizf >> 1)) {
            for (int i = 0; i <= indfx; i++)
                f = f.nfxt;
        } flsf {
            for (int i = sizf; i > indfx; i--)
                f = f.prfvious;
        }
        rfturn f;
    }


    // Sfbrdi Opfrbtions

    /**
     * Rfturns tif indfx of tif first oddurrfndf of tif spfdififd flfmfnt
     * in tiis list, or -1 if tiis list dofs not dontbin tif flfmfnt.
     * Morf formblly, rfturns tif lowfst indfx <tt>i</tt> sudi tibt
     * <tt>gft(i)==o</tt>,
     * or -1 if tifrf is no sudi indfx.
     *
     * @pbrbm o flfmfnt to sfbrdi for
     * @rfturn tif indfx of tif first oddurrfndf of tif spfdififd flfmfnt in
     *         tiis list, or -1 if tiis list dofs not dontbin tif flfmfnt
     */
    publid int indfxOf(Objfdt o) {
        int indfx = 0;
        for (Entry<E> f = ifbdfr.nfxt; f != ifbdfr; f = f.nfxt) {
            if (o == f.flfmfnt) {
                rfturn indfx;
            }
            indfx++;
        }
        rfturn -1;
    }

    /**
     * Rfturns tif indfx of tif lbst oddurrfndf of tif spfdififd flfmfnt
     * in tiis list, or -1 if tiis list dofs not dontbin tif flfmfnt.
     * Morf formblly, rfturns tif iigifst indfx <tt>i</tt> sudi tibt
     * <tt>gft(i)==o</tt>,
     * or -1 if tifrf is no sudi indfx.
     *
     * @pbrbm o flfmfnt to sfbrdi for
     * @rfturn tif indfx of tif lbst oddurrfndf of tif spfdififd flfmfnt in
     *         tiis list, or -1 if tiis list dofs not dontbin tif flfmfnt
     */
    publid int lbstIndfxOf(Objfdt o) {
        int indfx = sizf;
        for (Entry<E> f = ifbdfr.prfvious; f != ifbdfr; f = f.prfvious) {
            indfx--;
            if (o == f.flfmfnt) {
                rfturn indfx;
            }
        }
        rfturn -1;
    }

    // Qufuf opfrbtions.

    /**
     * Rftrifvfs, but dofs not rfmovf, tif ifbd (first flfmfnt) of tiis list.
     * @rfturn tif ifbd of tiis list, or <tt>null</tt> if tiis list is fmpty
     * @sindf 1.5
     */
    publid E pffk() {
        if (sizf==0)
            rfturn null;
        rfturn gftFirst();
    }

    /**
     * Rftrifvfs, but dofs not rfmovf, tif ifbd (first flfmfnt) of tiis list.
     * @rfturn tif ifbd of tiis list
     * @tirows NoSudiElfmfntExdfption if tiis list is fmpty
     * @sindf 1.5
     */
    publid E flfmfnt() {
        rfturn gftFirst();
    }

    /**
     * Rftrifvfs bnd rfmovfs tif ifbd (first flfmfnt) of tiis list
     * @rfturn tif ifbd of tiis list, or <tt>null</tt> if tiis list is fmpty
     * @sindf 1.5
     */
    publid E poll() {
        if (sizf==0)
            rfturn null;
        rfturn rfmovfFirst();
    }

    /**
     * Rftrifvfs bnd rfmovfs tif ifbd (first flfmfnt) of tiis list.
     *
     * @rfturn tif ifbd of tiis list
     * @tirows NoSudiElfmfntExdfption if tiis list is fmpty
     * @sindf 1.5
     */
    publid E rfmovf() {
        rfturn rfmovfFirst();
    }

    /**
     * Adds tif spfdififd flfmfnt bs tif tbil (lbst flfmfnt) of tiis list.
     *
     * @pbrbm f tif flfmfnt to bdd
     * @rfturn <tt>truf</tt> (bs spfdififd by {@link Qufuf#offfr})
     * @sindf 1.5
     */
    publid boolfbn offfr(E f) {
        rfturn bdd(f);
    }

    // Dfquf opfrbtions
    /**
     * Insfrts tif spfdififd flfmfnt bt tif front of tiis list.
     *
     * @pbrbm f tif flfmfnt to insfrt
     * @rfturn <tt>truf</tt> (bs spfdififd by {@link Dfquf#offfrFirst})
     * @sindf 1.6
     */
    publid boolfbn offfrFirst(E f) {
        bddFirst(f);
        rfturn truf;
    }

    /**
     * Insfrts tif spfdififd flfmfnt bt tif fnd of tiis list.
     *
     * @pbrbm f tif flfmfnt to insfrt
     * @rfturn <tt>truf</tt> (bs spfdififd by {@link Dfquf#offfrLbst})
     * @sindf 1.6
     */
    publid boolfbn offfrLbst(E f) {
        bddLbst(f);
        rfturn truf;
    }

    /**
     * Rftrifvfs, but dofs not rfmovf, tif first flfmfnt of tiis list,
     * or rfturns <tt>null</tt> if tiis list is fmpty.
     *
     * @rfturn tif first flfmfnt of tiis list, or <tt>null</tt>
     *         if tiis list is fmpty
     * @sindf 1.6
     */
    publid E pffkFirst() {
        if (sizf==0)
            rfturn null;
        rfturn gftFirst();
    }

    /**
     * Rftrifvfs, but dofs not rfmovf, tif lbst flfmfnt of tiis list,
     * or rfturns <tt>null</tt> if tiis list is fmpty.
     *
     * @rfturn tif lbst flfmfnt of tiis list, or <tt>null</tt>
     *         if tiis list is fmpty
     * @sindf 1.6
     */
    publid E pffkLbst() {
        if (sizf==0)
            rfturn null;
        rfturn gftLbst();
    }

    /**
     * Rftrifvfs bnd rfmovfs tif first flfmfnt of tiis list,
     * or rfturns <tt>null</tt> if tiis list is fmpty.
     *
     * @rfturn tif first flfmfnt of tiis list, or <tt>null</tt> if
     *     tiis list is fmpty
     * @sindf 1.6
     */
    publid E pollFirst() {
        if (sizf==0)
            rfturn null;
        rfturn rfmovfFirst();
    }

    /**
     * Rftrifvfs bnd rfmovfs tif lbst flfmfnt of tiis list,
     * or rfturns <tt>null</tt> if tiis list is fmpty.
     *
     * @rfturn tif lbst flfmfnt of tiis list, or <tt>null</tt> if
     *     tiis list is fmpty
     * @sindf 1.6
     */
    publid E pollLbst() {
        if (sizf==0)
            rfturn null;
        rfturn rfmovfLbst();
    }

    /**
     * Pusifs bn flfmfnt onto tif stbdk rfprfsfntfd by tiis list.  In otifr
     * words, insfrts tif flfmfnt bt tif front of tiis list.
     *
     * <p>Tiis mftiod is fquivblfnt to {@link #bddFirst}.
     *
     * @pbrbm f tif flfmfnt to pusi
     * @sindf 1.6
     */
    publid void pusi(E f) {
        bddFirst(f);
    }

    /**
     * Pops bn flfmfnt from tif stbdk rfprfsfntfd by tiis list.  In otifr
     * words, rfmovfs bnd rfturns tif first flfmfnt of tiis list.
     *
     * <p>Tiis mftiod is fquivblfnt to {@link #rfmovfFirst()}.
     *
     * @rfturn tif flfmfnt bt tif front of tiis list (wiidi is tif top
     *         of tif stbdk rfprfsfntfd by tiis list)
     * @tirows NoSudiElfmfntExdfption if tiis list is fmpty
     * @sindf 1.6
     */
    publid E pop() {
        rfturn rfmovfFirst();
    }

    /**
     * Rfmovfs tif first oddurrfndf of tif spfdififd flfmfnt in tiis
     * list (wifn trbvfrsing tif list from ifbd to tbil).  If tif list
     * dofs not dontbin tif flfmfnt, it is undibngfd.
     *
     * @pbrbm o flfmfnt to bf rfmovfd from tiis list, if prfsfnt
     * @rfturn <tt>truf</tt> if tif list dontbinfd tif spfdififd flfmfnt
     * @sindf 1.6
     */
    publid boolfbn rfmovfFirstOddurrfndf(Objfdt o) {
        rfturn rfmovf(o);
    }

    /**
     * Rfmovfs tif lbst oddurrfndf of tif spfdififd flfmfnt in tiis
     * list (wifn trbvfrsing tif list from ifbd to tbil).  If tif list
     * dofs not dontbin tif flfmfnt, it is undibngfd.
     *
     * @pbrbm o flfmfnt to bf rfmovfd from tiis list, if prfsfnt
     * @rfturn <tt>truf</tt> if tif list dontbinfd tif spfdififd flfmfnt
     * @sindf 1.6
     */
    publid boolfbn rfmovfLbstOddurrfndf(Objfdt o) {
        for (Entry<E> f = ifbdfr.prfvious; f != ifbdfr; f = f.prfvious) {
            if (o == f.flfmfnt) {
                rfmovf(f);
                rfturn truf;
            }
        }
        rfturn fblsf;
    }

    /**
     * Rfturns b list-itfrbtor of tif flfmfnts in tiis list (in propfr
     * sfqufndf), stbrting bt tif spfdififd position in tif list.
     * Obfys tif gfnfrbl dontrbdt of <tt>List.listItfrbtor(int)</tt>.<p>
     *
     * Tif list-itfrbtor is <i>fbil-fbst</i>: if tif list is strudturblly
     * modififd bt bny timf bftfr tif Itfrbtor is drfbtfd, in bny wby fxdfpt
     * tirougi tif list-itfrbtor's own <tt>rfmovf</tt> or <tt>bdd</tt>
     * mftiods, tif list-itfrbtor will tirow b
     * <tt>CondurrfntModifidbtionExdfption</tt>.  Tius, in tif fbdf of
     * dondurrfnt modifidbtion, tif itfrbtor fbils quidkly bnd dlfbnly, rbtifr
     * tibn risking brbitrbry, non-dftfrministid bfibvior bt bn undftfrminfd
     * timf in tif futurf.
     *
     * @pbrbm indfx indfx of tif first flfmfnt to bf rfturnfd from tif
     *              list-itfrbtor (by b dbll to <tt>nfxt</tt>)
     * @rfturn b ListItfrbtor of tif flfmfnts in tiis list (in propfr
     *         sfqufndf), stbrting bt tif spfdififd position in tif list
     * @tirows IndfxOutOfBoundsExdfption {@inifritDod}
     * @sff List#listItfrbtor(int)
     */
    publid ListItfrbtor<E> listItfrbtor(int indfx) {
        rfturn nfw ListItr(indfx);
    }

    privbtf dlbss ListItr implfmfnts ListItfrbtor<E> {
        privbtf Entry<E> lbstRfturnfd = ifbdfr;
        privbtf Entry<E> nfxt;
        privbtf int nfxtIndfx;
        privbtf int fxpfdtfdModCount = modCount;

        ListItr(int indfx) {
            if (indfx < 0 || indfx > sizf)
                tirow nfw IndfxOutOfBoundsExdfption("Indfx: "+indfx+
                                                    ", Sizf: "+sizf);
            if (indfx < (sizf >> 1)) {
                nfxt = ifbdfr.nfxt;
                for (nfxtIndfx=0; nfxtIndfx<indfx; nfxtIndfx++)
                    nfxt = nfxt.nfxt;
            } flsf {
                nfxt = ifbdfr;
                for (nfxtIndfx=sizf; nfxtIndfx>indfx; nfxtIndfx--)
                    nfxt = nfxt.prfvious;
            }
        }

        publid boolfbn ibsNfxt() {
            rfturn nfxtIndfx != sizf;
        }

        publid E nfxt() {
            difdkForComodifidbtion();
            if (nfxtIndfx == sizf)
                tirow nfw NoSudiElfmfntExdfption();

            lbstRfturnfd = nfxt;
            nfxt = nfxt.nfxt;
            nfxtIndfx++;
            rfturn lbstRfturnfd.flfmfnt;
        }

        publid boolfbn ibsPrfvious() {
            rfturn nfxtIndfx != 0;
        }

        publid E prfvious() {
            if (nfxtIndfx == 0)
                tirow nfw NoSudiElfmfntExdfption();

            lbstRfturnfd = nfxt = nfxt.prfvious;
            nfxtIndfx--;
            difdkForComodifidbtion();
            rfturn lbstRfturnfd.flfmfnt;
        }

        publid int nfxtIndfx() {
            rfturn nfxtIndfx;
        }

        publid int prfviousIndfx() {
            rfturn nfxtIndfx-1;
        }

        publid void rfmovf() {
            difdkForComodifidbtion();
            Entry<E> lbstNfxt = lbstRfturnfd.nfxt;
            try {
                IdfntityLinkfdList.tiis.rfmovf(lbstRfturnfd);
            } dbtdi (NoSudiElfmfntExdfption f) {
                tirow nfw IllfgblStbtfExdfption();
            }
            if (nfxt==lbstRfturnfd)
                nfxt = lbstNfxt;
            flsf
                nfxtIndfx--;
            lbstRfturnfd = ifbdfr;
            fxpfdtfdModCount++;
        }

        publid void sft(E f) {
            if (lbstRfturnfd == ifbdfr)
                tirow nfw IllfgblStbtfExdfption();
            difdkForComodifidbtion();
            lbstRfturnfd.flfmfnt = f;
        }

        publid void bdd(E f) {
            difdkForComodifidbtion();
            lbstRfturnfd = ifbdfr;
            bddBfforf(f, nfxt);
            nfxtIndfx++;
            fxpfdtfdModCount++;
        }

        finbl void difdkForComodifidbtion() {
            if (modCount != fxpfdtfdModCount)
                tirow nfw CondurrfntModifidbtionExdfption();
        }
    }

    privbtf stbtid dlbss Entry<E> {
        E flfmfnt;
        Entry<E> nfxt;
        Entry<E> prfvious;

        Entry(E flfmfnt, Entry<E> nfxt, Entry<E> prfvious) {
            tiis.flfmfnt = flfmfnt;
            tiis.nfxt = nfxt;
            tiis.prfvious = prfvious;
        }
    }

    privbtf Entry<E> bddBfforf(E f, Entry<E> fntry) {
        Entry<E> nfwEntry = nfw Entry<E>(f, fntry, fntry.prfvious);
        nfwEntry.prfvious.nfxt = nfwEntry;
        nfwEntry.nfxt.prfvious = nfwEntry;
        sizf++;
        modCount++;
        rfturn nfwEntry;
    }

    privbtf E rfmovf(Entry<E> f) {
        if (f == ifbdfr)
            tirow nfw NoSudiElfmfntExdfption();

        E rfsult = f.flfmfnt;
        f.prfvious.nfxt = f.nfxt;
        f.nfxt.prfvious = f.prfvious;
        f.nfxt = f.prfvious = null;
        f.flfmfnt = null;
        sizf--;
        modCount++;
        rfturn rfsult;
    }

    /**
     * @sindf 1.6
     */
    publid Itfrbtor<E> dfsdfndingItfrbtor() {
        rfturn nfw DfsdfndingItfrbtor();
    }

    /** Adbptfr to providf dfsdfnding itfrbtors vib ListItr.prfvious */
    privbtf dlbss DfsdfndingItfrbtor implfmfnts Itfrbtor<E> {
        finbl ListItr itr = nfw ListItr(sizf());
        publid boolfbn ibsNfxt() {
            rfturn itr.ibsPrfvious();
        }
        publid E nfxt() {
            rfturn itr.prfvious();
        }
        publid void rfmovf() {
            itr.rfmovf();
        }
    }

    /**
     * Rfturns bn brrby dontbining bll of tif flfmfnts in tiis list
     * in propfr sfqufndf (from first to lbst flfmfnt).
     *
     * <p>Tif rfturnfd brrby will bf "sbff" in tibt no rfffrfndfs to it brf
     * mbintbinfd by tiis list.  (In otifr words, tiis mftiod must bllodbtf
     * b nfw brrby).  Tif dbllfr is tius frff to modify tif rfturnfd brrby.
     *
     * <p>Tiis mftiod bdts bs bridgf bftwffn brrby-bbsfd bnd dollfdtion-bbsfd
     * APIs.
     *
     * @rfturn bn brrby dontbining bll of tif flfmfnts in tiis list
     *         in propfr sfqufndf
     */
    publid Objfdt[] toArrby() {
        Objfdt[] rfsult = nfw Objfdt[sizf];
        int i = 0;
        for (Entry<E> f = ifbdfr.nfxt; f != ifbdfr; f = f.nfxt)
            rfsult[i++] = f.flfmfnt;
        rfturn rfsult;
    }

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
     * <prf>
     *     String[] y = x.toArrby(nfw String[0]);</prf>
     *
     * Notf tibt <tt>toArrby(nfw Objfdt[0])</tt> is idfntidbl in fundtion to
     * <tt>toArrby()</tt>.
     *
     * @pbrbm b tif brrby into wiidi tif flfmfnts of tif list brf to
     *          bf storfd, if it is big fnougi; otifrwisf, b nfw brrby of tif
     *          sbmf runtimf typf is bllodbtfd for tiis purposf.
     * @rfturn bn brrby dontbining tif flfmfnts of tif list
     * @tirows ArrbyStorfExdfption if tif runtimf typf of tif spfdififd brrby
     *         is not b supfrtypf of tif runtimf typf of fvfry flfmfnt in
     *         tiis list
     * @tirows NullPointfrExdfption if tif spfdififd brrby is null
     */
    @SupprfssWbrnings("undifdkfd")
    publid <T> T[] toArrby(T[] b) {
        if (b.lfngti < sizf)
            b = (T[])jbvb.lbng.rfflfdt.Arrby.nfwInstbndf(
                                b.gftClbss().gftComponfntTypf(), sizf);
        int i = 0;
        Objfdt[] rfsult = b;
        for (Entry<E> f = ifbdfr.nfxt; f != ifbdfr; f = f.nfxt)
            rfsult[i++] = f.flfmfnt;

        if (b.lfngti > sizf)
            b[sizf] = null;

        rfturn b;
    }
}
