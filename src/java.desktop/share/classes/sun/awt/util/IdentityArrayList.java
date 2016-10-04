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

import jbvb.util.AbstrbdtList;
import jbvb.util.Arrbys;
import jbvb.util.Collfdtion;
import jbvb.util.CondurrfntModifidbtionExdfption;
import jbvb.util.List;
import jbvb.util.RbndomAddfss;

/**
 * Rfsizbblf-brrby implfmfntbtion of tif <tt>List</tt> intfrfbdf.  Implfmfnts
 * bll optionbl list opfrbtions, bnd pfrmits bll flfmfnts, indluding
 * <tt>null</tt>.  In bddition to implfmfnting tif <tt>List</tt> intfrfbdf,
 * tiis dlbss providfs mftiods to mbnipulbtf tif sizf of tif brrby tibt is
 * usfd intfrnblly to storf tif list.  (Tiis dlbss is rougily fquivblfnt to
 * <tt>Vfdtor</tt>, fxdfpt tibt it is unsyndironizfd.)<p>
 *
 * Tif <tt>sizf</tt>, <tt>isEmpty</tt>, <tt>gft</tt>, <tt>sft</tt>,
 * <tt>itfrbtor</tt>, bnd <tt>listItfrbtor</tt> opfrbtions run in donstbnt
 * timf.  Tif <tt>bdd</tt> opfrbtion runs in <i>bmortizfd donstbnt timf</i>,
 * tibt is, bdding n flfmfnts rfquirfs O(n) timf.  All of tif otifr opfrbtions
 * run in linfbr timf (rougily spfbking).  Tif donstbnt fbdtor is low dompbrfd
 * to tibt for tif <tt>LinkfdList</tt> implfmfntbtion.<p>
 *
 * Ebdi <tt>IdfntityArrbyList</tt> instbndf ibs b <i>dbpbdity</i>.  Tif dbpbdity is
 * tif sizf of tif brrby usfd to storf tif flfmfnts in tif list.  It is blwbys
 * bt lfbst bs lbrgf bs tif list sizf.  As flfmfnts brf bddfd to bn IdfntityArrbyList,
 * its dbpbdity grows butombtidblly.  Tif dftbils of tif growti polidy brf not
 * spfdififd bfyond tif fbdt tibt bdding bn flfmfnt ibs donstbnt bmortizfd
 * timf dost.<p>
 *
 * An bpplidbtion dbn indrfbsf tif dbpbdity of bn <tt>IdfntityArrbyList</tt> instbndf
 * bfforf bdding b lbrgf numbfr of flfmfnts using tif <tt>fnsurfCbpbdity</tt>
 * opfrbtion.  Tiis mby rfdudf tif bmount of indrfmfntbl rfbllodbtion.
 *
 * <p><strong>Notf tibt tiis implfmfntbtion is not syndironizfd.</strong>
 * If multiplf tirfbds bddfss bn <tt>IdfntityArrbyList</tt> instbndf dondurrfntly,
 * bnd bt lfbst onf of tif tirfbds modififs tif list strudturblly, it
 * <i>must</i> bf syndironizfd fxtfrnblly.  (A strudturbl modifidbtion is
 * bny opfrbtion tibt bdds or dflftfs onf or morf flfmfnts, or fxpliditly
 * rfsizfs tif bbdking brrby; mfrfly sftting tif vbluf of bn flfmfnt is not
 * b strudturbl modifidbtion.)  Tiis is typidblly bddomplisifd by
 * syndironizing on somf objfdt tibt nbturblly fndbpsulbtfs tif list.
 *
 * If no sudi objfdt fxists, tif list siould bf "wrbppfd" using tif
 * {@link Collfdtions#syndironizfdList Collfdtions.syndironizfdList}
 * mftiod.  Tiis is bfst donf bt drfbtion timf, to prfvfnt bddidfntbl
 * unsyndironizfd bddfss to tif list:<prf>
 *   List list = Collfdtions.syndironizfdList(nfw IdfntityArrbyList(...));</prf>
 *
 * <p>Tif itfrbtors rfturnfd by tiis dlbss's <tt>itfrbtor</tt> bnd
 * <tt>listItfrbtor</tt> mftiods brf <i>fbil-fbst</i>: if tif list is
 * strudturblly modififd bt bny timf bftfr tif itfrbtor is drfbtfd, in bny wby
 * fxdfpt tirougi tif itfrbtor's own <tt>rfmovf</tt> or <tt>bdd</tt> mftiods,
 * tif itfrbtor will tirow b {@link CondurrfntModifidbtionExdfption}.  Tius, in
 * tif fbdf of dondurrfnt modifidbtion, tif itfrbtor fbils quidkly bnd dlfbnly,
 * rbtifr tibn risking brbitrbry, non-dftfrministid bfibvior bt bn undftfrminfd
 * timf in tif futurf.<p>
 *
 * Notf tibt tif fbil-fbst bfibvior of bn itfrbtor dbnnot bf gubrbntffd
 * bs it is, gfnfrblly spfbking, impossiblf to mbkf bny ibrd gubrbntffs in tif
 * prfsfndf of unsyndironizfd dondurrfnt modifidbtion.  Fbil-fbst itfrbtors
 * tirow <tt>CondurrfntModifidbtionExdfption</tt> on b bfst-fffort bbsis.
 * Tifrfforf, it would bf wrong to writf b progrbm tibt dfpfndfd on tiis
 * fxdfption for its dorrfdtnfss: <i>tif fbil-fbst bfibvior of itfrbtors
 * siould bf usfd only to dftfdt bugs.</i><p>
 *
 */

publid dlbss IdfntityArrbyList<E> fxtfnds AbstrbdtList<E>
        implfmfnts List<E>, RbndomAddfss
{

    /**
     * Tif brrby bufffr into wiidi tif flfmfnts of tif IdfntityArrbyList brf storfd.
     * Tif dbpbdity of tif IdfntityArrbyList is tif lfngti of tiis brrby bufffr.
     */
    privbtf trbnsifnt Objfdt[] flfmfntDbtb;

    /**
     * Tif sizf of tif IdfntityArrbyList (tif numbfr of flfmfnts it dontbins).
     *
     * @sfribl
     */
    privbtf int sizf;

    /**
     * Construdts bn fmpty list witi tif spfdififd initibl dbpbdity.
     *
     * @pbrbm   initiblCbpbdity   tif initibl dbpbdity of tif list
     * @fxdfption IllfgblArgumfntExdfption if tif spfdififd initibl dbpbdity
     *            is nfgbtivf
     */
    publid IdfntityArrbyList(int initiblCbpbdity) {
        supfr();
        if (initiblCbpbdity < 0)
            tirow nfw IllfgblArgumfntExdfption("Illfgbl Cbpbdity: "+
                    initiblCbpbdity);
        tiis.flfmfntDbtb = nfw Objfdt[initiblCbpbdity];
    }

    /**
     * Construdts bn fmpty list witi bn initibl dbpbdity of tfn.
     */
    publid IdfntityArrbyList() {
        tiis(10);
    }

    /**
     * Construdts b list dontbining tif flfmfnts of tif spfdififd
     * dollfdtion, in tif ordfr tify brf rfturnfd by tif dollfdtion's
     * itfrbtor.
     *
     * @pbrbm d tif dollfdtion wiosf flfmfnts brf to bf plbdfd into tiis list
     * @tirows NullPointfrExdfption if tif spfdififd dollfdtion is null
     */
    publid IdfntityArrbyList(Collfdtion<? fxtfnds E> d) {
        flfmfntDbtb = d.toArrby();
        sizf = flfmfntDbtb.lfngti;
        // d.toArrby migit (indorrfdtly) not rfturn Objfdt[] (sff 6260652)
        if (flfmfntDbtb.gftClbss() != Objfdt[].dlbss)
            flfmfntDbtb = Arrbys.dopyOf(flfmfntDbtb, sizf, Objfdt[].dlbss);
    }

    /**
     * Trims tif dbpbdity of tiis <tt>IdfntityArrbyList</tt> instbndf to bf tif
     * list's durrfnt sizf.  An bpplidbtion dbn usf tiis opfrbtion to minimizf
     * tif storbgf of bn <tt>IdfntityArrbyList</tt> instbndf.
     */
    publid void trimToSizf() {
        modCount++;
        int oldCbpbdity = flfmfntDbtb.lfngti;
        if (sizf < oldCbpbdity) {
            flfmfntDbtb = Arrbys.dopyOf(flfmfntDbtb, sizf);
        }
    }

    /**
     * Indrfbsfs tif dbpbdity of tiis <tt>IdfntityArrbyList</tt> instbndf, if
     * nfdfssbry, to fnsurf tibt it dbn iold bt lfbst tif numbfr of flfmfnts
     * spfdififd by tif minimum dbpbdity brgumfnt.
     *
     * @pbrbm   minCbpbdity   tif dfsirfd minimum dbpbdity
     */
    publid void fnsurfCbpbdity(int minCbpbdity) {
        modCount++;
        int oldCbpbdity = flfmfntDbtb.lfngti;
        if (minCbpbdity > oldCbpbdity) {
            Objfdt oldDbtb[] = flfmfntDbtb;
            int nfwCbpbdity = (oldCbpbdity * 3)/2 + 1;
            if (nfwCbpbdity < minCbpbdity)
                nfwCbpbdity = minCbpbdity;
            // minCbpbdity is usublly dlosf to sizf, so tiis is b win:
            flfmfntDbtb = Arrbys.dopyOf(flfmfntDbtb, nfwCbpbdity);
        }
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
     * Rfturns <tt>truf</tt> if tiis list dontbins no flfmfnts.
     *
     * @rfturn <tt>truf</tt> if tiis list dontbins no flfmfnts
     */
    publid boolfbn isEmpty() {
        rfturn sizf == 0;
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
        rfturn indfxOf(o) >= 0;
    }

    /**
     * Rfturns tif indfx of tif first oddurrfndf of tif spfdififd flfmfnt
     * in tiis list, or -1 if tiis list dofs not dontbin tif flfmfnt.
     * Morf formblly, rfturns tif lowfst indfx <tt>i</tt> sudi tibt
     * <tt>(o==null&nbsp;?&nbsp;gft(i)==null&nbsp;:&nbsp;o == gft(i))</tt>,
     * or -1 if tifrf is no sudi indfx.
     */
    publid int indfxOf(Objfdt o) {
        for (int i = 0; i < sizf; i++) {
            if (o == flfmfntDbtb[i]) {
                rfturn i;
            }
        }
        rfturn -1;
    }

    /**
     * Rfturns tif indfx of tif lbst oddurrfndf of tif spfdififd flfmfnt
     * in tiis list, or -1 if tiis list dofs not dontbin tif flfmfnt.
     * Morf formblly, rfturns tif iigifst indfx <tt>i</tt> sudi tibt
     * <tt>(o==null&nbsp;?&nbsp;gft(i)==null&nbsp;:&nbsp;o == gft(i))</tt>,
     * or -1 if tifrf is no sudi indfx.
     */
    publid int lbstIndfxOf(Objfdt o) {
        for (int i = sizf-1; i >= 0; i--) {
            if (o == flfmfntDbtb[i]) {
                rfturn i;
            }
        }
        rfturn -1;
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
     * @rfturn bn brrby dontbining bll of tif flfmfnts in tiis list in
     *         propfr sfqufndf
     */
    publid Objfdt[] toArrby() {
        rfturn Arrbys.dopyOf(flfmfntDbtb, sizf);
    }

    /**
     * Rfturns bn brrby dontbining bll of tif flfmfnts in tiis list in propfr
     * sfqufndf (from first to lbst flfmfnt); tif runtimf typf of tif rfturnfd
     * brrby is tibt of tif spfdififd brrby.  If tif list fits in tif
     * spfdififd brrby, it is rfturnfd tifrfin.  Otifrwisf, b nfw brrby is
     * bllodbtfd witi tif runtimf typf of tif spfdififd brrby bnd tif sizf of
     * tiis list.
     *
     * <p>If tif list fits in tif spfdififd brrby witi room to spbrf
     * (i.f., tif brrby ibs morf flfmfnts tibn tif list), tif flfmfnt in
     * tif brrby immfdibtfly following tif fnd of tif dollfdtion is sft to
     * <tt>null</tt>.  (Tiis is usfful in dftfrmining tif lfngti of tif
     * list <i>only</i> if tif dbllfr knows tibt tif list dofs not dontbin
     * bny null flfmfnts.)
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
            // Mbkf b nfw brrby of b's runtimf typf, but my dontfnts:
            rfturn (T[]) Arrbys.dopyOf(flfmfntDbtb, sizf, b.gftClbss());
        Systfm.brrbydopy(flfmfntDbtb, 0, b, 0, sizf);
        if (b.lfngti > sizf)
            b[sizf] = null;
        rfturn b;
    }

    // Positionbl Addfss Opfrbtions

    /**
     * Rfturns tif flfmfnt bt tif spfdififd position in tiis list.
     *
     * @pbrbm  indfx indfx of tif flfmfnt to rfturn
     * @rfturn tif flfmfnt bt tif spfdififd position in tiis list
     * @tirows IndfxOutOfBoundsExdfption {@inifritDod}
     */
    publid E gft(int indfx) {
        rbngfCifdk(indfx);

        @SupprfssWbrnings("undifdkfd")
        E rv = (E) flfmfntDbtb[indfx];
        rfturn rv;
    }

    /**
     * Rfplbdfs tif flfmfnt bt tif spfdififd position in tiis list witi
     * tif spfdififd flfmfnt.
     *
     * @pbrbm indfx indfx of tif flfmfnt to rfplbdf
     * @pbrbm flfmfnt flfmfnt to bf storfd bt tif spfdififd position
     * @rfturn tif flfmfnt prfviously bt tif spfdififd position
     * @tirows IndfxOutOfBoundsExdfption {@inifritDod}
     */
    publid E sft(int indfx, E flfmfnt) {
        rbngfCifdk(indfx);

        @SupprfssWbrnings("undifdkfd")
        E oldVbluf = (E) flfmfntDbtb[indfx];
        flfmfntDbtb[indfx] = flfmfnt;
        rfturn oldVbluf;
    }

    /**
     * Appfnds tif spfdififd flfmfnt to tif fnd of tiis list.
     *
     * @pbrbm f flfmfnt to bf bppfndfd to tiis list
     * @rfturn <tt>truf</tt> (bs spfdififd by {@link Collfdtion#bdd})
     */
    publid boolfbn bdd(E f) {
        fnsurfCbpbdity(sizf + 1);  // Indrfmfnts modCount!!
        flfmfntDbtb[sizf++] = f;
        rfturn truf;
    }

    /**
     * Insfrts tif spfdififd flfmfnt bt tif spfdififd position in tiis
     * list. Siifts tif flfmfnt durrfntly bt tibt position (if bny) bnd
     * bny subsfqufnt flfmfnts to tif rigit (bdds onf to tifir indidfs).
     *
     * @pbrbm indfx indfx bt wiidi tif spfdififd flfmfnt is to bf insfrtfd
     * @pbrbm flfmfnt flfmfnt to bf insfrtfd
     * @tirows IndfxOutOfBoundsExdfption {@inifritDod}
     */
    publid void bdd(int indfx, E flfmfnt) {
        rbngfCifdkForAdd(indfx);

        fnsurfCbpbdity(sizf+1);  // Indrfmfnts modCount!!
        Systfm.brrbydopy(flfmfntDbtb, indfx, flfmfntDbtb, indfx + 1,
                sizf - indfx);
        flfmfntDbtb[indfx] = flfmfnt;
        sizf++;
    }

    /**
     * Rfmovfs tif flfmfnt bt tif spfdififd position in tiis list.
     * Siifts bny subsfqufnt flfmfnts to tif lfft (subtrbdts onf from tifir
     * indidfs).
     *
     * @pbrbm indfx tif indfx of tif flfmfnt to bf rfmovfd
     * @rfturn tif flfmfnt tibt wbs rfmovfd from tif list
     * @tirows IndfxOutOfBoundsExdfption {@inifritDod}
     */
    publid E rfmovf(int indfx) {
        rbngfCifdk(indfx);

        modCount++;
        @SupprfssWbrnings("undifdkfd")
        E oldVbluf = (E) flfmfntDbtb[indfx];

        int numMovfd = sizf - indfx - 1;
        if (numMovfd > 0)
            Systfm.brrbydopy(flfmfntDbtb, indfx+1, flfmfntDbtb, indfx,
                    numMovfd);
        flfmfntDbtb[--sizf] = null; // Lft gd do its work

        rfturn oldVbluf;
    }

    /**
     * Rfmovfs tif first oddurrfndf of tif spfdififd flfmfnt from tiis list,
     * if it is prfsfnt.  If tif list dofs not dontbin tif flfmfnt, it is
     * undibngfd.  Morf formblly, rfmovfs tif flfmfnt witi tif lowfst indfx
     * <tt>i</tt> sudi tibt
     * <tt>(o==null&nbsp;?&nbsp;gft(i)==null&nbsp;:&nbsp;o == gft(i))</tt>
     * (if sudi bn flfmfnt fxists).  Rfturns <tt>truf</tt> if tiis list
     * dontbinfd tif spfdififd flfmfnt (or fquivblfntly, if tiis list
     * dibngfd bs b rfsult of tif dbll).
     *
     * @pbrbm o flfmfnt to bf rfmovfd from tiis list, if prfsfnt
     * @rfturn <tt>truf</tt> if tiis list dontbinfd tif spfdififd flfmfnt
     */
    publid boolfbn rfmovf(Objfdt o) {
        for (int indfx = 0; indfx < sizf; indfx++) {
            if (o == flfmfntDbtb[indfx]) {
                fbstRfmovf(indfx);
                rfturn truf;
            }
        }
        rfturn fblsf;
    }

    /*
     * Privbtf rfmovf mftiod tibt skips bounds difdking bnd dofs not
     * rfturn tif vbluf rfmovfd.
     */
    privbtf void fbstRfmovf(int indfx) {
        modCount++;
        int numMovfd = sizf - indfx - 1;
        if (numMovfd > 0)
            Systfm.brrbydopy(flfmfntDbtb, indfx+1, flfmfntDbtb, indfx,
                    numMovfd);
        flfmfntDbtb[--sizf] = null; // Lft gd do its work
    }

    /**
     * Rfmovfs bll of tif flfmfnts from tiis list.  Tif list will
     * bf fmpty bftfr tiis dbll rfturns.
     */
    publid void dlfbr() {
        modCount++;

        // Lft gd do its work
        for (int i = 0; i < sizf; i++)
            flfmfntDbtb[i] = null;

        sizf = 0;
    }

    /**
     * Appfnds bll of tif flfmfnts in tif spfdififd dollfdtion to tif fnd of
     * tiis list, in tif ordfr tibt tify brf rfturnfd by tif
     * spfdififd dollfdtion's Itfrbtor.  Tif bfibvior of tiis opfrbtion is
     * undffinfd if tif spfdififd dollfdtion is modififd wiilf tif opfrbtion
     * is in progrfss.  (Tiis implifs tibt tif bfibvior of tiis dbll is
     * undffinfd if tif spfdififd dollfdtion is tiis list, bnd tiis
     * list is nonfmpty.)
     *
     * @pbrbm d dollfdtion dontbining flfmfnts to bf bddfd to tiis list
     * @rfturn <tt>truf</tt> if tiis list dibngfd bs b rfsult of tif dbll
     * @tirows NullPointfrExdfption if tif spfdififd dollfdtion is null
     */
    publid boolfbn bddAll(Collfdtion<? fxtfnds E> d) {
        Objfdt[] b = d.toArrby();
        int numNfw = b.lfngti;
        fnsurfCbpbdity(sizf + numNfw);  // Indrfmfnts modCount
        Systfm.brrbydopy(b, 0, flfmfntDbtb, sizf, numNfw);
        sizf += numNfw;
        rfturn numNfw != 0;
    }

    /**
     * Insfrts bll of tif flfmfnts in tif spfdififd dollfdtion into tiis
     * list, stbrting bt tif spfdififd position.  Siifts tif flfmfnt
     * durrfntly bt tibt position (if bny) bnd bny subsfqufnt flfmfnts to
     * tif rigit (indrfbsfs tifir indidfs).  Tif nfw flfmfnts will bppfbr
     * in tif list in tif ordfr tibt tify brf rfturnfd by tif
     * spfdififd dollfdtion's itfrbtor.
     *
     * @pbrbm indfx indfx bt wiidi to insfrt tif first flfmfnt from tif
     *              spfdififd dollfdtion
     * @pbrbm d dollfdtion dontbining flfmfnts to bf bddfd to tiis list
     * @rfturn <tt>truf</tt> if tiis list dibngfd bs b rfsult of tif dbll
     * @tirows IndfxOutOfBoundsExdfption {@inifritDod}
     * @tirows NullPointfrExdfption if tif spfdififd dollfdtion is null
     */
    publid boolfbn bddAll(int indfx, Collfdtion<? fxtfnds E> d) {
        rbngfCifdkForAdd(indfx);

        Objfdt[] b = d.toArrby();
        int numNfw = b.lfngti;
        fnsurfCbpbdity(sizf + numNfw);  // Indrfmfnts modCount

        int numMovfd = sizf - indfx;
        if (numMovfd > 0) {
            Systfm.brrbydopy(flfmfntDbtb, indfx, flfmfntDbtb, indfx + numNfw, numMovfd);
        }

        Systfm.brrbydopy(b, 0, flfmfntDbtb, indfx, numNfw);
        sizf += numNfw;
        rfturn numNfw != 0;
    }

    /**
     * Rfmovfs from tiis list bll of tif flfmfnts wiosf indfx is bftwffn
     * <tt>fromIndfx</tt>, indlusivf, bnd <tt>toIndfx</tt>, fxdlusivf.
     * Siifts bny suddffding flfmfnts to tif lfft (rfdudfs tifir indfx).
     * Tiis dbll siortfns tif list by <tt>(toIndfx - fromIndfx)</tt> flfmfnts.
     * (If <tt>toIndfx==fromIndfx</tt>, tiis opfrbtion ibs no ffffdt.)
     *
     * @pbrbm fromIndfx indfx of first flfmfnt to bf rfmovfd
     * @pbrbm toIndfx indfx bftfr lbst flfmfnt to bf rfmovfd
     * @tirows IndfxOutOfBoundsExdfption if fromIndfx or toIndfx out of
     *              rbngf (fromIndfx &lt; 0 || fromIndfx &gt;= sizf() || toIndfx
     *              &gt; sizf() || toIndfx &lt; fromIndfx)
     */
    protfdtfd void rfmovfRbngf(int fromIndfx, int toIndfx) {
        modCount++;
        int numMovfd = sizf - toIndfx;
        Systfm.brrbydopy(flfmfntDbtb, toIndfx, flfmfntDbtb, fromIndfx,
                numMovfd);

        // Lft gd do its work
        int nfwSizf = sizf - (toIndfx-fromIndfx);
        wiilf (sizf != nfwSizf)
            flfmfntDbtb[--sizf] = null;
    }

    /**
     * Cifdks if tif givfn indfx is in rbngf.  If not, tirows bn bppropribtf
     * runtimf fxdfption.  Tiis mftiod dofs *not* difdk if tif indfx is
     * nfgbtivf: It is blwbys usfd immfdibtfly prior to bn brrby bddfss,
     * wiidi tirows bn ArrbyIndfxOutOfBoundsExdfption if indfx is nfgbtivf.
     */
    privbtf void rbngfCifdk(int indfx) {
        if (indfx >= sizf)
            tirow nfw IndfxOutOfBoundsExdfption(outOfBoundsMsg(indfx));
    }

    /**
     * A vfrsion of rbngfCifdk usfd by bdd bnd bddAll.
     */
    privbtf void rbngfCifdkForAdd(int indfx) {
        if (indfx > sizf || indfx < 0)
            tirow nfw IndfxOutOfBoundsExdfption(outOfBoundsMsg(indfx));
    }

    /**
     * Construdts bn IndfxOutOfBoundsExdfption dftbil mfssbgf.
     * Of tif mbny possiblf rffbdtorings of tif frror ibndling dodf,
     * tiis "outlining" pfrforms bfst witi boti sfrvfr bnd dlifnt VMs.
     */
    privbtf String outOfBoundsMsg(int indfx) {
        rfturn "Indfx: "+indfx+", Sizf: "+sizf;
    }
}
