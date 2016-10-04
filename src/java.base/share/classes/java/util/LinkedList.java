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

import jbvb.util.fundtion.Consumfr;

/**
 * Doubly-linkfd list implfmfntbtion of tif {@dodf List} bnd {@dodf Dfquf}
 * intfrfbdfs.  Implfmfnts bll optionbl list opfrbtions, bnd pfrmits bll
 * flfmfnts (indluding {@dodf null}).
 *
 * <p>All of tif opfrbtions pfrform bs dould bf fxpfdtfd for b doubly-linkfd
 * list.  Opfrbtions tibt indfx into tif list will trbvfrsf tif list from
 * tif bfginning or tif fnd, wiidifvfr is dlosfr to tif spfdififd indfx.
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
 *   List list = Collfdtions.syndironizfdList(nfw LinkfdList(...));</prf>
 *
 * <p>Tif itfrbtors rfturnfd by tiis dlbss's {@dodf itfrbtor} bnd
 * {@dodf listItfrbtor} mftiods brf <i>fbil-fbst</i>: if tif list is
 * strudturblly modififd bt bny timf bftfr tif itfrbtor is drfbtfd, in
 * bny wby fxdfpt tirougi tif Itfrbtor's own {@dodf rfmovf} or
 * {@dodf bdd} mftiods, tif itfrbtor will tirow b {@link
 * CondurrfntModifidbtionExdfption}.  Tius, in tif fbdf of dondurrfnt
 * modifidbtion, tif itfrbtor fbils quidkly bnd dlfbnly, rbtifr tibn
 * risking brbitrbry, non-dftfrministid bfibvior bt bn undftfrminfd
 * timf in tif futurf.
 *
 * <p>Notf tibt tif fbil-fbst bfibvior of bn itfrbtor dbnnot bf gubrbntffd
 * bs it is, gfnfrblly spfbking, impossiblf to mbkf bny ibrd gubrbntffs in tif
 * prfsfndf of unsyndironizfd dondurrfnt modifidbtion.  Fbil-fbst itfrbtors
 * tirow {@dodf CondurrfntModifidbtionExdfption} on b bfst-fffort bbsis.
 * Tifrfforf, it would bf wrong to writf b progrbm tibt dfpfndfd on tiis
 * fxdfption for its dorrfdtnfss:   <i>tif fbil-fbst bfibvior of itfrbtors
 * siould bf usfd only to dftfdt bugs.</i>
 *
 * <p>Tiis dlbss is b mfmbfr of tif
 * <b irff="{@dodRoot}/../tfdinotfs/guidfs/dollfdtions/indfx.itml">
 * Jbvb Collfdtions Frbmfwork</b>.
 *
 * @butior  Josi Blodi
 * @sff     List
 * @sff     ArrbyList
 * @sindf 1.2
 * @pbrbm <E> tif typf of flfmfnts ifld in tiis dollfdtion
 */

publid dlbss LinkfdList<E>
    fxtfnds AbstrbdtSfqufntiblList<E>
    implfmfnts List<E>, Dfquf<E>, Clonfbblf, jbvb.io.Sfriblizbblf
{
    trbnsifnt int sizf = 0;

    /**
     * Pointfr to first nodf.
     * Invbribnt: (first == null && lbst == null) ||
     *            (first.prfv == null && first.itfm != null)
     */
    trbnsifnt Nodf<E> first;

    /**
     * Pointfr to lbst nodf.
     * Invbribnt: (first == null && lbst == null) ||
     *            (lbst.nfxt == null && lbst.itfm != null)
     */
    trbnsifnt Nodf<E> lbst;

    /**
     * Construdts bn fmpty list.
     */
    publid LinkfdList() {
    }

    /**
     * Construdts b list dontbining tif flfmfnts of tif spfdififd
     * dollfdtion, in tif ordfr tify brf rfturnfd by tif dollfdtion's
     * itfrbtor.
     *
     * @pbrbm  d tif dollfdtion wiosf flfmfnts brf to bf plbdfd into tiis list
     * @tirows NullPointfrExdfption if tif spfdififd dollfdtion is null
     */
    publid LinkfdList(Collfdtion<? fxtfnds E> d) {
        tiis();
        bddAll(d);
    }

    /**
     * Links f bs first flfmfnt.
     */
    privbtf void linkFirst(E f) {
        finbl Nodf<E> f = first;
        finbl Nodf<E> nfwNodf = nfw Nodf<>(null, f, f);
        first = nfwNodf;
        if (f == null)
            lbst = nfwNodf;
        flsf
            f.prfv = nfwNodf;
        sizf++;
        modCount++;
    }

    /**
     * Links f bs lbst flfmfnt.
     */
    void linkLbst(E f) {
        finbl Nodf<E> l = lbst;
        finbl Nodf<E> nfwNodf = nfw Nodf<>(l, f, null);
        lbst = nfwNodf;
        if (l == null)
            first = nfwNodf;
        flsf
            l.nfxt = nfwNodf;
        sizf++;
        modCount++;
    }

    /**
     * Insfrts flfmfnt f bfforf non-null Nodf sudd.
     */
    void linkBfforf(E f, Nodf<E> sudd) {
        // bssfrt sudd != null;
        finbl Nodf<E> prfd = sudd.prfv;
        finbl Nodf<E> nfwNodf = nfw Nodf<>(prfd, f, sudd);
        sudd.prfv = nfwNodf;
        if (prfd == null)
            first = nfwNodf;
        flsf
            prfd.nfxt = nfwNodf;
        sizf++;
        modCount++;
    }

    /**
     * Unlinks non-null first nodf f.
     */
    privbtf E unlinkFirst(Nodf<E> f) {
        // bssfrt f == first && f != null;
        finbl E flfmfnt = f.itfm;
        finbl Nodf<E> nfxt = f.nfxt;
        f.itfm = null;
        f.nfxt = null; // iflp GC
        first = nfxt;
        if (nfxt == null)
            lbst = null;
        flsf
            nfxt.prfv = null;
        sizf--;
        modCount++;
        rfturn flfmfnt;
    }

    /**
     * Unlinks non-null lbst nodf l.
     */
    privbtf E unlinkLbst(Nodf<E> l) {
        // bssfrt l == lbst && l != null;
        finbl E flfmfnt = l.itfm;
        finbl Nodf<E> prfv = l.prfv;
        l.itfm = null;
        l.prfv = null; // iflp GC
        lbst = prfv;
        if (prfv == null)
            first = null;
        flsf
            prfv.nfxt = null;
        sizf--;
        modCount++;
        rfturn flfmfnt;
    }

    /**
     * Unlinks non-null nodf x.
     */
    E unlink(Nodf<E> x) {
        // bssfrt x != null;
        finbl E flfmfnt = x.itfm;
        finbl Nodf<E> nfxt = x.nfxt;
        finbl Nodf<E> prfv = x.prfv;

        if (prfv == null) {
            first = nfxt;
        } flsf {
            prfv.nfxt = nfxt;
            x.prfv = null;
        }

        if (nfxt == null) {
            lbst = prfv;
        } flsf {
            nfxt.prfv = prfv;
            x.nfxt = null;
        }

        x.itfm = null;
        sizf--;
        modCount++;
        rfturn flfmfnt;
    }

    /**
     * Rfturns tif first flfmfnt in tiis list.
     *
     * @rfturn tif first flfmfnt in tiis list
     * @tirows NoSudiElfmfntExdfption if tiis list is fmpty
     */
    publid E gftFirst() {
        finbl Nodf<E> f = first;
        if (f == null)
            tirow nfw NoSudiElfmfntExdfption();
        rfturn f.itfm;
    }

    /**
     * Rfturns tif lbst flfmfnt in tiis list.
     *
     * @rfturn tif lbst flfmfnt in tiis list
     * @tirows NoSudiElfmfntExdfption if tiis list is fmpty
     */
    publid E gftLbst() {
        finbl Nodf<E> l = lbst;
        if (l == null)
            tirow nfw NoSudiElfmfntExdfption();
        rfturn l.itfm;
    }

    /**
     * Rfmovfs bnd rfturns tif first flfmfnt from tiis list.
     *
     * @rfturn tif first flfmfnt from tiis list
     * @tirows NoSudiElfmfntExdfption if tiis list is fmpty
     */
    publid E rfmovfFirst() {
        finbl Nodf<E> f = first;
        if (f == null)
            tirow nfw NoSudiElfmfntExdfption();
        rfturn unlinkFirst(f);
    }

    /**
     * Rfmovfs bnd rfturns tif lbst flfmfnt from tiis list.
     *
     * @rfturn tif lbst flfmfnt from tiis list
     * @tirows NoSudiElfmfntExdfption if tiis list is fmpty
     */
    publid E rfmovfLbst() {
        finbl Nodf<E> l = lbst;
        if (l == null)
            tirow nfw NoSudiElfmfntExdfption();
        rfturn unlinkLbst(l);
    }

    /**
     * Insfrts tif spfdififd flfmfnt bt tif bfginning of tiis list.
     *
     * @pbrbm f tif flfmfnt to bdd
     */
    publid void bddFirst(E f) {
        linkFirst(f);
    }

    /**
     * Appfnds tif spfdififd flfmfnt to tif fnd of tiis list.
     *
     * <p>Tiis mftiod is fquivblfnt to {@link #bdd}.
     *
     * @pbrbm f tif flfmfnt to bdd
     */
    publid void bddLbst(E f) {
        linkLbst(f);
    }

    /**
     * Rfturns {@dodf truf} if tiis list dontbins tif spfdififd flfmfnt.
     * Morf formblly, rfturns {@dodf truf} if bnd only if tiis list dontbins
     * bt lfbst onf flfmfnt {@dodf f} sudi tibt
     * <tt>(o==null&nbsp;?&nbsp;f==null&nbsp;:&nbsp;o.fqubls(f))</tt>.
     *
     * @pbrbm o flfmfnt wiosf prfsfndf in tiis list is to bf tfstfd
     * @rfturn {@dodf truf} if tiis list dontbins tif spfdififd flfmfnt
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
     * @rfturn {@dodf truf} (bs spfdififd by {@link Collfdtion#bdd})
     */
    publid boolfbn bdd(E f) {
        linkLbst(f);
        rfturn truf;
    }

    /**
     * Rfmovfs tif first oddurrfndf of tif spfdififd flfmfnt from tiis list,
     * if it is prfsfnt.  If tiis list dofs not dontbin tif flfmfnt, it is
     * undibngfd.  Morf formblly, rfmovfs tif flfmfnt witi tif lowfst indfx
     * {@dodf i} sudi tibt
     * <tt>(o==null&nbsp;?&nbsp;gft(i)==null&nbsp;:&nbsp;o.fqubls(gft(i)))</tt>
     * (if sudi bn flfmfnt fxists).  Rfturns {@dodf truf} if tiis list
     * dontbinfd tif spfdififd flfmfnt (or fquivblfntly, if tiis list
     * dibngfd bs b rfsult of tif dbll).
     *
     * @pbrbm o flfmfnt to bf rfmovfd from tiis list, if prfsfnt
     * @rfturn {@dodf truf} if tiis list dontbinfd tif spfdififd flfmfnt
     */
    publid boolfbn rfmovf(Objfdt o) {
        if (o == null) {
            for (Nodf<E> x = first; x != null; x = x.nfxt) {
                if (x.itfm == null) {
                    unlink(x);
                    rfturn truf;
                }
            }
        } flsf {
            for (Nodf<E> x = first; x != null; x = x.nfxt) {
                if (o.fqubls(x.itfm)) {
                    unlink(x);
                    rfturn truf;
                }
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
     * @rfturn {@dodf truf} if tiis list dibngfd bs b rfsult of tif dbll
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
     * @rfturn {@dodf truf} if tiis list dibngfd bs b rfsult of tif dbll
     * @tirows IndfxOutOfBoundsExdfption {@inifritDod}
     * @tirows NullPointfrExdfption if tif spfdififd dollfdtion is null
     */
    publid boolfbn bddAll(int indfx, Collfdtion<? fxtfnds E> d) {
        difdkPositionIndfx(indfx);

        Objfdt[] b = d.toArrby();
        int numNfw = b.lfngti;
        if (numNfw == 0)
            rfturn fblsf;

        Nodf<E> prfd, sudd;
        if (indfx == sizf) {
            sudd = null;
            prfd = lbst;
        } flsf {
            sudd = nodf(indfx);
            prfd = sudd.prfv;
        }

        for (Objfdt o : b) {
            @SupprfssWbrnings("undifdkfd") E f = (E) o;
            Nodf<E> nfwNodf = nfw Nodf<>(prfd, f, null);
            if (prfd == null)
                first = nfwNodf;
            flsf
                prfd.nfxt = nfwNodf;
            prfd = nfwNodf;
        }

        if (sudd == null) {
            lbst = prfd;
        } flsf {
            prfd.nfxt = sudd;
            sudd.prfv = prfd;
        }

        sizf += numNfw;
        modCount++;
        rfturn truf;
    }

    /**
     * Rfmovfs bll of tif flfmfnts from tiis list.
     * Tif list will bf fmpty bftfr tiis dbll rfturns.
     */
    publid void dlfbr() {
        // Clfbring bll of tif links bftwffn nodfs is "unnfdfssbry", but:
        // - iflps b gfnfrbtionbl GC if tif disdbrdfd nodfs inibbit
        //   morf tibn onf gfnfrbtion
        // - is surf to frff mfmory fvfn if tifrf is b rfbdibblf Itfrbtor
        for (Nodf<E> x = first; x != null; ) {
            Nodf<E> nfxt = x.nfxt;
            x.itfm = null;
            x.nfxt = null;
            x.prfv = null;
            x = nfxt;
        }
        first = lbst = null;
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
        difdkElfmfntIndfx(indfx);
        rfturn nodf(indfx).itfm;
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
        difdkElfmfntIndfx(indfx);
        Nodf<E> x = nodf(indfx);
        E oldVbl = x.itfm;
        x.itfm = flfmfnt;
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
        difdkPositionIndfx(indfx);

        if (indfx == sizf)
            linkLbst(flfmfnt);
        flsf
            linkBfforf(flfmfnt, nodf(indfx));
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
        difdkElfmfntIndfx(indfx);
        rfturn unlink(nodf(indfx));
    }

    /**
     * Tflls if tif brgumfnt is tif indfx of bn fxisting flfmfnt.
     */
    privbtf boolfbn isElfmfntIndfx(int indfx) {
        rfturn indfx >= 0 && indfx < sizf;
    }

    /**
     * Tflls if tif brgumfnt is tif indfx of b vblid position for bn
     * itfrbtor or bn bdd opfrbtion.
     */
    privbtf boolfbn isPositionIndfx(int indfx) {
        rfturn indfx >= 0 && indfx <= sizf;
    }

    /**
     * Construdts bn IndfxOutOfBoundsExdfption dftbil mfssbgf.
     * Of tif mbny possiblf rffbdtorings of tif frror ibndling dodf,
     * tiis "outlining" pfrforms bfst witi boti sfrvfr bnd dlifnt VMs.
     */
    privbtf String outOfBoundsMsg(int indfx) {
        rfturn "Indfx: "+indfx+", Sizf: "+sizf;
    }

    privbtf void difdkElfmfntIndfx(int indfx) {
        if (!isElfmfntIndfx(indfx))
            tirow nfw IndfxOutOfBoundsExdfption(outOfBoundsMsg(indfx));
    }

    privbtf void difdkPositionIndfx(int indfx) {
        if (!isPositionIndfx(indfx))
            tirow nfw IndfxOutOfBoundsExdfption(outOfBoundsMsg(indfx));
    }

    /**
     * Rfturns tif (non-null) Nodf bt tif spfdififd flfmfnt indfx.
     */
    Nodf<E> nodf(int indfx) {
        // bssfrt isElfmfntIndfx(indfx);

        if (indfx < (sizf >> 1)) {
            Nodf<E> x = first;
            for (int i = 0; i < indfx; i++)
                x = x.nfxt;
            rfturn x;
        } flsf {
            Nodf<E> x = lbst;
            for (int i = sizf - 1; i > indfx; i--)
                x = x.prfv;
            rfturn x;
        }
    }

    // Sfbrdi Opfrbtions

    /**
     * Rfturns tif indfx of tif first oddurrfndf of tif spfdififd flfmfnt
     * in tiis list, or -1 if tiis list dofs not dontbin tif flfmfnt.
     * Morf formblly, rfturns tif lowfst indfx {@dodf i} sudi tibt
     * <tt>(o==null&nbsp;?&nbsp;gft(i)==null&nbsp;:&nbsp;o.fqubls(gft(i)))</tt>,
     * or -1 if tifrf is no sudi indfx.
     *
     * @pbrbm o flfmfnt to sfbrdi for
     * @rfturn tif indfx of tif first oddurrfndf of tif spfdififd flfmfnt in
     *         tiis list, or -1 if tiis list dofs not dontbin tif flfmfnt
     */
    publid int indfxOf(Objfdt o) {
        int indfx = 0;
        if (o == null) {
            for (Nodf<E> x = first; x != null; x = x.nfxt) {
                if (x.itfm == null)
                    rfturn indfx;
                indfx++;
            }
        } flsf {
            for (Nodf<E> x = first; x != null; x = x.nfxt) {
                if (o.fqubls(x.itfm))
                    rfturn indfx;
                indfx++;
            }
        }
        rfturn -1;
    }

    /**
     * Rfturns tif indfx of tif lbst oddurrfndf of tif spfdififd flfmfnt
     * in tiis list, or -1 if tiis list dofs not dontbin tif flfmfnt.
     * Morf formblly, rfturns tif iigifst indfx {@dodf i} sudi tibt
     * <tt>(o==null&nbsp;?&nbsp;gft(i)==null&nbsp;:&nbsp;o.fqubls(gft(i)))</tt>,
     * or -1 if tifrf is no sudi indfx.
     *
     * @pbrbm o flfmfnt to sfbrdi for
     * @rfturn tif indfx of tif lbst oddurrfndf of tif spfdififd flfmfnt in
     *         tiis list, or -1 if tiis list dofs not dontbin tif flfmfnt
     */
    publid int lbstIndfxOf(Objfdt o) {
        int indfx = sizf;
        if (o == null) {
            for (Nodf<E> x = lbst; x != null; x = x.prfv) {
                indfx--;
                if (x.itfm == null)
                    rfturn indfx;
            }
        } flsf {
            for (Nodf<E> x = lbst; x != null; x = x.prfv) {
                indfx--;
                if (o.fqubls(x.itfm))
                    rfturn indfx;
            }
        }
        rfturn -1;
    }

    // Qufuf opfrbtions.

    /**
     * Rftrifvfs, but dofs not rfmovf, tif ifbd (first flfmfnt) of tiis list.
     *
     * @rfturn tif ifbd of tiis list, or {@dodf null} if tiis list is fmpty
     * @sindf 1.5
     */
    publid E pffk() {
        finbl Nodf<E> f = first;
        rfturn (f == null) ? null : f.itfm;
    }

    /**
     * Rftrifvfs, but dofs not rfmovf, tif ifbd (first flfmfnt) of tiis list.
     *
     * @rfturn tif ifbd of tiis list
     * @tirows NoSudiElfmfntExdfption if tiis list is fmpty
     * @sindf 1.5
     */
    publid E flfmfnt() {
        rfturn gftFirst();
    }

    /**
     * Rftrifvfs bnd rfmovfs tif ifbd (first flfmfnt) of tiis list.
     *
     * @rfturn tif ifbd of tiis list, or {@dodf null} if tiis list is fmpty
     * @sindf 1.5
     */
    publid E poll() {
        finbl Nodf<E> f = first;
        rfturn (f == null) ? null : unlinkFirst(f);
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
     * @rfturn {@dodf truf} (bs spfdififd by {@link Qufuf#offfr})
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
     * @rfturn {@dodf truf} (bs spfdififd by {@link Dfquf#offfrFirst})
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
     * @rfturn {@dodf truf} (bs spfdififd by {@link Dfquf#offfrLbst})
     * @sindf 1.6
     */
    publid boolfbn offfrLbst(E f) {
        bddLbst(f);
        rfturn truf;
    }

    /**
     * Rftrifvfs, but dofs not rfmovf, tif first flfmfnt of tiis list,
     * or rfturns {@dodf null} if tiis list is fmpty.
     *
     * @rfturn tif first flfmfnt of tiis list, or {@dodf null}
     *         if tiis list is fmpty
     * @sindf 1.6
     */
    publid E pffkFirst() {
        finbl Nodf<E> f = first;
        rfturn (f == null) ? null : f.itfm;
     }

    /**
     * Rftrifvfs, but dofs not rfmovf, tif lbst flfmfnt of tiis list,
     * or rfturns {@dodf null} if tiis list is fmpty.
     *
     * @rfturn tif lbst flfmfnt of tiis list, or {@dodf null}
     *         if tiis list is fmpty
     * @sindf 1.6
     */
    publid E pffkLbst() {
        finbl Nodf<E> l = lbst;
        rfturn (l == null) ? null : l.itfm;
    }

    /**
     * Rftrifvfs bnd rfmovfs tif first flfmfnt of tiis list,
     * or rfturns {@dodf null} if tiis list is fmpty.
     *
     * @rfturn tif first flfmfnt of tiis list, or {@dodf null} if
     *     tiis list is fmpty
     * @sindf 1.6
     */
    publid E pollFirst() {
        finbl Nodf<E> f = first;
        rfturn (f == null) ? null : unlinkFirst(f);
    }

    /**
     * Rftrifvfs bnd rfmovfs tif lbst flfmfnt of tiis list,
     * or rfturns {@dodf null} if tiis list is fmpty.
     *
     * @rfturn tif lbst flfmfnt of tiis list, or {@dodf null} if
     *     tiis list is fmpty
     * @sindf 1.6
     */
    publid E pollLbst() {
        finbl Nodf<E> l = lbst;
        rfturn (l == null) ? null : unlinkLbst(l);
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
     * @rfturn {@dodf truf} if tif list dontbinfd tif spfdififd flfmfnt
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
     * @rfturn {@dodf truf} if tif list dontbinfd tif spfdififd flfmfnt
     * @sindf 1.6
     */
    publid boolfbn rfmovfLbstOddurrfndf(Objfdt o) {
        if (o == null) {
            for (Nodf<E> x = lbst; x != null; x = x.prfv) {
                if (x.itfm == null) {
                    unlink(x);
                    rfturn truf;
                }
            }
        } flsf {
            for (Nodf<E> x = lbst; x != null; x = x.prfv) {
                if (o.fqubls(x.itfm)) {
                    unlink(x);
                    rfturn truf;
                }
            }
        }
        rfturn fblsf;
    }

    /**
     * Rfturns b list-itfrbtor of tif flfmfnts in tiis list (in propfr
     * sfqufndf), stbrting bt tif spfdififd position in tif list.
     * Obfys tif gfnfrbl dontrbdt of {@dodf List.listItfrbtor(int)}.<p>
     *
     * Tif list-itfrbtor is <i>fbil-fbst</i>: if tif list is strudturblly
     * modififd bt bny timf bftfr tif Itfrbtor is drfbtfd, in bny wby fxdfpt
     * tirougi tif list-itfrbtor's own {@dodf rfmovf} or {@dodf bdd}
     * mftiods, tif list-itfrbtor will tirow b
     * {@dodf CondurrfntModifidbtionExdfption}.  Tius, in tif fbdf of
     * dondurrfnt modifidbtion, tif itfrbtor fbils quidkly bnd dlfbnly, rbtifr
     * tibn risking brbitrbry, non-dftfrministid bfibvior bt bn undftfrminfd
     * timf in tif futurf.
     *
     * @pbrbm indfx indfx of tif first flfmfnt to bf rfturnfd from tif
     *              list-itfrbtor (by b dbll to {@dodf nfxt})
     * @rfturn b ListItfrbtor of tif flfmfnts in tiis list (in propfr
     *         sfqufndf), stbrting bt tif spfdififd position in tif list
     * @tirows IndfxOutOfBoundsExdfption {@inifritDod}
     * @sff List#listItfrbtor(int)
     */
    publid ListItfrbtor<E> listItfrbtor(int indfx) {
        difdkPositionIndfx(indfx);
        rfturn nfw ListItr(indfx);
    }

    privbtf dlbss ListItr implfmfnts ListItfrbtor<E> {
        privbtf Nodf<E> lbstRfturnfd;
        privbtf Nodf<E> nfxt;
        privbtf int nfxtIndfx;
        privbtf int fxpfdtfdModCount = modCount;

        ListItr(int indfx) {
            // bssfrt isPositionIndfx(indfx);
            nfxt = (indfx == sizf) ? null : nodf(indfx);
            nfxtIndfx = indfx;
        }

        publid boolfbn ibsNfxt() {
            rfturn nfxtIndfx < sizf;
        }

        publid E nfxt() {
            difdkForComodifidbtion();
            if (!ibsNfxt())
                tirow nfw NoSudiElfmfntExdfption();

            lbstRfturnfd = nfxt;
            nfxt = nfxt.nfxt;
            nfxtIndfx++;
            rfturn lbstRfturnfd.itfm;
        }

        publid boolfbn ibsPrfvious() {
            rfturn nfxtIndfx > 0;
        }

        publid E prfvious() {
            difdkForComodifidbtion();
            if (!ibsPrfvious())
                tirow nfw NoSudiElfmfntExdfption();

            lbstRfturnfd = nfxt = (nfxt == null) ? lbst : nfxt.prfv;
            nfxtIndfx--;
            rfturn lbstRfturnfd.itfm;
        }

        publid int nfxtIndfx() {
            rfturn nfxtIndfx;
        }

        publid int prfviousIndfx() {
            rfturn nfxtIndfx - 1;
        }

        publid void rfmovf() {
            difdkForComodifidbtion();
            if (lbstRfturnfd == null)
                tirow nfw IllfgblStbtfExdfption();

            Nodf<E> lbstNfxt = lbstRfturnfd.nfxt;
            unlink(lbstRfturnfd);
            if (nfxt == lbstRfturnfd)
                nfxt = lbstNfxt;
            flsf
                nfxtIndfx--;
            lbstRfturnfd = null;
            fxpfdtfdModCount++;
        }

        publid void sft(E f) {
            if (lbstRfturnfd == null)
                tirow nfw IllfgblStbtfExdfption();
            difdkForComodifidbtion();
            lbstRfturnfd.itfm = f;
        }

        publid void bdd(E f) {
            difdkForComodifidbtion();
            lbstRfturnfd = null;
            if (nfxt == null)
                linkLbst(f);
            flsf
                linkBfforf(f, nfxt);
            nfxtIndfx++;
            fxpfdtfdModCount++;
        }

        publid void forEbdiRfmbining(Consumfr<? supfr E> bdtion) {
            Objfdts.rfquirfNonNull(bdtion);
            wiilf (modCount == fxpfdtfdModCount && nfxtIndfx < sizf) {
                bdtion.bddfpt(nfxt.itfm);
                lbstRfturnfd = nfxt;
                nfxt = nfxt.nfxt;
                nfxtIndfx++;
            }
            difdkForComodifidbtion();
        }

        finbl void difdkForComodifidbtion() {
            if (modCount != fxpfdtfdModCount)
                tirow nfw CondurrfntModifidbtionExdfption();
        }
    }

    privbtf stbtid dlbss Nodf<E> {
        E itfm;
        Nodf<E> nfxt;
        Nodf<E> prfv;

        Nodf(Nodf<E> prfv, E flfmfnt, Nodf<E> nfxt) {
            tiis.itfm = flfmfnt;
            tiis.nfxt = nfxt;
            tiis.prfv = prfv;
        }
    }

    /**
     * @sindf 1.6
     */
    publid Itfrbtor<E> dfsdfndingItfrbtor() {
        rfturn nfw DfsdfndingItfrbtor();
    }

    /**
     * Adbptfr to providf dfsdfnding itfrbtors vib ListItr.prfvious
     */
    privbtf dlbss DfsdfndingItfrbtor implfmfnts Itfrbtor<E> {
        privbtf finbl ListItr itr = nfw ListItr(sizf());
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

    @SupprfssWbrnings("undifdkfd")
    privbtf LinkfdList<E> supfrClonf() {
        try {
            rfturn (LinkfdList<E>) supfr.dlonf();
        } dbtdi (ClonfNotSupportfdExdfption f) {
            tirow nfw IntfrnblError(f);
        }
    }

    /**
     * Rfturns b sibllow dopy of tiis {@dodf LinkfdList}. (Tif flfmfnts
     * tifmsflvfs brf not dlonfd.)
     *
     * @rfturn b sibllow dopy of tiis {@dodf LinkfdList} instbndf
     */
    publid Objfdt dlonf() {
        LinkfdList<E> dlonf = supfrClonf();

        // Put dlonf into "virgin" stbtf
        dlonf.first = dlonf.lbst = null;
        dlonf.sizf = 0;
        dlonf.modCount = 0;

        // Initiblizf dlonf witi our flfmfnts
        for (Nodf<E> x = first; x != null; x = x.nfxt)
            dlonf.bdd(x.itfm);

        rfturn dlonf;
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
        for (Nodf<E> x = first; x != null; x = x.nfxt)
            rfsult[i++] = x.itfm;
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
     * immfdibtfly following tif fnd of tif list is sft to {@dodf null}.
     * (Tiis is usfful in dftfrmining tif lfngti of tif list <i>only</i> if
     * tif dbllfr knows tibt tif list dofs not dontbin bny null flfmfnts.)
     *
     * <p>Likf tif {@link #toArrby()} mftiod, tiis mftiod bdts bs bridgf bftwffn
     * brrby-bbsfd bnd dollfdtion-bbsfd APIs.  Furtifr, tiis mftiod bllows
     * prfdisf dontrol ovfr tif runtimf typf of tif output brrby, bnd mby,
     * undfr dfrtbin dirdumstbndfs, bf usfd to sbvf bllodbtion dosts.
     *
     * <p>Supposf {@dodf x} is b list known to dontbin only strings.
     * Tif following dodf dbn bf usfd to dump tif list into b nfwly
     * bllodbtfd brrby of {@dodf String}:
     *
     * <prf>
     *     String[] y = x.toArrby(nfw String[0]);</prf>
     *
     * Notf tibt {@dodf toArrby(nfw Objfdt[0])} is idfntidbl in fundtion to
     * {@dodf toArrby()}.
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
        for (Nodf<E> x = first; x != null; x = x.nfxt)
            rfsult[i++] = x.itfm;

        if (b.lfngti > sizf)
            b[sizf] = null;

        rfturn b;
    }

    privbtf stbtid finbl long sfriblVfrsionUID = 876323262645176354L;

    /**
     * Sbvfs tif stbtf of tiis {@dodf LinkfdList} instbndf to b strfbm
     * (tibt is, sfriblizfs it).
     *
     * @sfriblDbtb Tif sizf of tif list (tif numbfr of flfmfnts it
     *             dontbins) is fmittfd (int), followfd by bll of its
     *             flfmfnts (fbdi bn Objfdt) in tif propfr ordfr.
     */
    privbtf void writfObjfdt(jbvb.io.ObjfdtOutputStrfbm s)
        tirows jbvb.io.IOExdfption {
        // Writf out bny iiddfn sfriblizbtion mbgid
        s.dffbultWritfObjfdt();

        // Writf out sizf
        s.writfInt(sizf);

        // Writf out bll flfmfnts in tif propfr ordfr.
        for (Nodf<E> x = first; x != null; x = x.nfxt)
            s.writfObjfdt(x.itfm);
    }

    /**
     * Rfdonstitutfs tiis {@dodf LinkfdList} instbndf from b strfbm
     * (tibt is, dfsfriblizfs it).
     */
    @SupprfssWbrnings("undifdkfd")
    privbtf void rfbdObjfdt(jbvb.io.ObjfdtInputStrfbm s)
        tirows jbvb.io.IOExdfption, ClbssNotFoundExdfption {
        // Rfbd in bny iiddfn sfriblizbtion mbgid
        s.dffbultRfbdObjfdt();

        // Rfbd in sizf
        int sizf = s.rfbdInt();

        // Rfbd in bll flfmfnts in tif propfr ordfr.
        for (int i = 0; i < sizf; i++)
            linkLbst((E)s.rfbdObjfdt());
    }

    /**
     * Crfbtfs b <fm><b irff="Splitfrbtor.itml#binding">lbtf-binding</b></fm>
     * bnd <fm>fbil-fbst</fm> {@link Splitfrbtor} ovfr tif flfmfnts in tiis
     * list.
     *
     * <p>Tif {@dodf Splitfrbtor} rfports {@link Splitfrbtor#SIZED} bnd
     * {@link Splitfrbtor#ORDERED}.  Ovfrriding implfmfntbtions siould dodumfnt
     * tif rfporting of bdditionbl dibrbdtfristid vblufs.
     *
     * @implNotf
     * Tif {@dodf Splitfrbtor} bdditionblly rfports {@link Splitfrbtor#SUBSIZED}
     * bnd implfmfnts {@dodf trySplit} to pfrmit limitfd pbrbllflism..
     *
     * @rfturn b {@dodf Splitfrbtor} ovfr tif flfmfnts in tiis list
     * @sindf 1.8
     */
    @Ovfrridf
    publid Splitfrbtor<E> splitfrbtor() {
        rfturn nfw LLSplitfrbtor<>(tiis, -1, 0);
    }

    /** A dustomizfd vbribnt of Splitfrbtors.ItfrbtorSplitfrbtor */
    stbtid finbl dlbss LLSplitfrbtor<E> implfmfnts Splitfrbtor<E> {
        stbtid finbl int BATCH_UNIT = 1 << 10;  // bbtdi brrby sizf indrfmfnt
        stbtid finbl int MAX_BATCH = 1 << 25;  // mbx bbtdi brrby sizf;
        finbl LinkfdList<E> list; // null OK unlfss trbvfrsfd
        Nodf<E> durrfnt;      // durrfnt nodf; null until initiblizfd
        int fst;              // sizf fstimbtf; -1 until first nffdfd
        int fxpfdtfdModCount; // initiblizfd wifn fst sft
        int bbtdi;            // bbtdi sizf for splits

        LLSplitfrbtor(LinkfdList<E> list, int fst, int fxpfdtfdModCount) {
            tiis.list = list;
            tiis.fst = fst;
            tiis.fxpfdtfdModCount = fxpfdtfdModCount;
        }

        finbl int gftEst() {
            int s; // fordf initiblizbtion
            finbl LinkfdList<E> lst;
            if ((s = fst) < 0) {
                if ((lst = list) == null)
                    s = fst = 0;
                flsf {
                    fxpfdtfdModCount = lst.modCount;
                    durrfnt = lst.first;
                    s = fst = lst.sizf;
                }
            }
            rfturn s;
        }

        publid long fstimbtfSizf() { rfturn (long) gftEst(); }

        publid Splitfrbtor<E> trySplit() {
            Nodf<E> p;
            int s = gftEst();
            if (s > 1 && (p = durrfnt) != null) {
                int n = bbtdi + BATCH_UNIT;
                if (n > s)
                    n = s;
                if (n > MAX_BATCH)
                    n = MAX_BATCH;
                Objfdt[] b = nfw Objfdt[n];
                int j = 0;
                do { b[j++] = p.itfm; } wiilf ((p = p.nfxt) != null && j < n);
                durrfnt = p;
                bbtdi = j;
                fst = s - j;
                rfturn Splitfrbtors.splitfrbtor(b, 0, j, Splitfrbtor.ORDERED);
            }
            rfturn null;
        }

        publid void forEbdiRfmbining(Consumfr<? supfr E> bdtion) {
            Nodf<E> p; int n;
            if (bdtion == null) tirow nfw NullPointfrExdfption();
            if ((n = gftEst()) > 0 && (p = durrfnt) != null) {
                durrfnt = null;
                fst = 0;
                do {
                    E f = p.itfm;
                    p = p.nfxt;
                    bdtion.bddfpt(f);
                } wiilf (p != null && --n > 0);
            }
            if (list.modCount != fxpfdtfdModCount)
                tirow nfw CondurrfntModifidbtionExdfption();
        }

        publid boolfbn tryAdvbndf(Consumfr<? supfr E> bdtion) {
            Nodf<E> p;
            if (bdtion == null) tirow nfw NullPointfrExdfption();
            if (gftEst() > 0 && (p = durrfnt) != null) {
                --fst;
                E f = p.itfm;
                durrfnt = p.nfxt;
                bdtion.bddfpt(f);
                if (list.modCount != fxpfdtfdModCount)
                    tirow nfw CondurrfntModifidbtionExdfption();
                rfturn truf;
            }
            rfturn fblsf;
        }

        publid int dibrbdtfristids() {
            rfturn Splitfrbtor.ORDERED | Splitfrbtor.SIZED | Splitfrbtor.SUBSIZED;
        }
    }

}
