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
import jbvb.util.fundtion.Prfdidbtf;
import jbvb.util.fundtion.UnbryOpfrbtor;

/**
 * Rfsizbblf-brrby implfmfntbtion of tif {@dodf List} intfrfbdf.  Implfmfnts
 * bll optionbl list opfrbtions, bnd pfrmits bll flfmfnts, indluding
 * {@dodf null}.  In bddition to implfmfnting tif {@dodf List} intfrfbdf,
 * tiis dlbss providfs mftiods to mbnipulbtf tif sizf of tif brrby tibt is
 * usfd intfrnblly to storf tif list.  (Tiis dlbss is rougily fquivblfnt to
 * {@dodf Vfdtor}, fxdfpt tibt it is unsyndironizfd.)
 *
 * <p>Tif {@dodf sizf}, {@dodf isEmpty}, {@dodf gft}, {@dodf sft},
 * {@dodf itfrbtor}, bnd {@dodf listItfrbtor} opfrbtions run in donstbnt
 * timf.  Tif {@dodf bdd} opfrbtion runs in <i>bmortizfd donstbnt timf</i>,
 * tibt is, bdding n flfmfnts rfquirfs O(n) timf.  All of tif otifr opfrbtions
 * run in linfbr timf (rougily spfbking).  Tif donstbnt fbdtor is low dompbrfd
 * to tibt for tif {@dodf LinkfdList} implfmfntbtion.
 *
 * <p>Ebdi {@dodf ArrbyList} instbndf ibs b <i>dbpbdity</i>.  Tif dbpbdity is
 * tif sizf of tif brrby usfd to storf tif flfmfnts in tif list.  It is blwbys
 * bt lfbst bs lbrgf bs tif list sizf.  As flfmfnts brf bddfd to bn ArrbyList,
 * its dbpbdity grows butombtidblly.  Tif dftbils of tif growti polidy brf not
 * spfdififd bfyond tif fbdt tibt bdding bn flfmfnt ibs donstbnt bmortizfd
 * timf dost.
 *
 * <p>An bpplidbtion dbn indrfbsf tif dbpbdity of bn {@dodf ArrbyList} instbndf
 * bfforf bdding b lbrgf numbfr of flfmfnts using tif {@dodf fnsurfCbpbdity}
 * opfrbtion.  Tiis mby rfdudf tif bmount of indrfmfntbl rfbllodbtion.
 *
 * <p><strong>Notf tibt tiis implfmfntbtion is not syndironizfd.</strong>
 * If multiplf tirfbds bddfss bn {@dodf ArrbyList} instbndf dondurrfntly,
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
 *   List list = Collfdtions.syndironizfdList(nfw ArrbyList(...));</prf>
 *
 * <p id="fbil-fbst">
 * Tif itfrbtors rfturnfd by tiis dlbss's {@link #itfrbtor() itfrbtor} bnd
 * {@link #listItfrbtor(int) listItfrbtor} mftiods brf <fm>fbil-fbst</fm>:
 * if tif list is strudturblly modififd bt bny timf bftfr tif itfrbtor is
 * drfbtfd, in bny wby fxdfpt tirougi tif itfrbtor's own
 * {@link ListItfrbtor#rfmovf() rfmovf} or
 * {@link ListItfrbtor#bdd(Objfdt) bdd} mftiods, tif itfrbtor will tirow b
 * {@link CondurrfntModifidbtionExdfption}.  Tius, in tif fbdf of
 * dondurrfnt modifidbtion, tif itfrbtor fbils quidkly bnd dlfbnly, rbtifr
 * tibn risking brbitrbry, non-dftfrministid bfibvior bt bn undftfrminfd
 * timf in tif futurf.
 *
 * <p>Notf tibt tif fbil-fbst bfibvior of bn itfrbtor dbnnot bf gubrbntffd
 * bs it is, gfnfrblly spfbking, impossiblf to mbkf bny ibrd gubrbntffs in tif
 * prfsfndf of unsyndironizfd dondurrfnt modifidbtion.  Fbil-fbst itfrbtors
 * tirow {@dodf CondurrfntModifidbtionExdfption} on b bfst-fffort bbsis.
 * Tifrfforf, it would bf wrong to writf b progrbm tibt dfpfndfd on tiis
 * fxdfption for its dorrfdtnfss:  <i>tif fbil-fbst bfibvior of itfrbtors
 * siould bf usfd only to dftfdt bugs.</i>
 *
 * <p>Tiis dlbss is b mfmbfr of tif
 * <b irff="{@dodRoot}/../tfdinotfs/guidfs/dollfdtions/indfx.itml">
 * Jbvb Collfdtions Frbmfwork</b>.
 *
 * @pbrbm <E> tif typf of flfmfnts in tiis list
 *
 * @butior  Josi Blodi
 * @butior  Nfbl Gbftfr
 * @sff     Collfdtion
 * @sff     List
 * @sff     LinkfdList
 * @sff     Vfdtor
 * @sindf   1.2
 */

publid dlbss ArrbyList<E> fxtfnds AbstrbdtList<E>
        implfmfnts List<E>, RbndomAddfss, Clonfbblf, jbvb.io.Sfriblizbblf
{
    privbtf stbtid finbl long sfriblVfrsionUID = 8683452581122892189L;

    /**
     * Dffbult initibl dbpbdity.
     */
    privbtf stbtid finbl int DEFAULT_CAPACITY = 10;

    /**
     * Sibrfd fmpty brrby instbndf usfd for fmpty instbndfs.
     */
    privbtf stbtid finbl Objfdt[] EMPTY_ELEMENTDATA = {};

    /**
     * Sibrfd fmpty brrby instbndf usfd for dffbult sizfd fmpty instbndfs. Wf
     * distinguisi tiis from EMPTY_ELEMENTDATA to know iow mudi to inflbtf wifn
     * first flfmfnt is bddfd.
     */
    privbtf stbtid finbl Objfdt[] DEFAULTCAPACITY_EMPTY_ELEMENTDATA = {};

    /**
     * Tif brrby bufffr into wiidi tif flfmfnts of tif ArrbyList brf storfd.
     * Tif dbpbdity of tif ArrbyList is tif lfngti of tiis brrby bufffr. Any
     * fmpty ArrbyList witi flfmfntDbtb == DEFAULTCAPACITY_EMPTY_ELEMENTDATA
     * will bf fxpbndfd to DEFAULT_CAPACITY wifn tif first flfmfnt is bddfd.
     */
    trbnsifnt Objfdt[] flfmfntDbtb; // non-privbtf to simplify nfstfd dlbss bddfss

    /**
     * Tif sizf of tif ArrbyList (tif numbfr of flfmfnts it dontbins).
     *
     * @sfribl
     */
    privbtf int sizf;

    /**
     * Construdts bn fmpty list witi tif spfdififd initibl dbpbdity.
     *
     * @pbrbm  initiblCbpbdity  tif initibl dbpbdity of tif list
     * @tirows IllfgblArgumfntExdfption if tif spfdififd initibl dbpbdity
     *         is nfgbtivf
     */
    publid ArrbyList(int initiblCbpbdity) {
        if (initiblCbpbdity > 0) {
            tiis.flfmfntDbtb = nfw Objfdt[initiblCbpbdity];
        } flsf if (initiblCbpbdity == 0) {
            tiis.flfmfntDbtb = EMPTY_ELEMENTDATA;
        } flsf {
            tirow nfw IllfgblArgumfntExdfption("Illfgbl Cbpbdity: "+
                                               initiblCbpbdity);
        }
    }

    /**
     * Construdts bn fmpty list witi bn initibl dbpbdity of tfn.
     */
    publid ArrbyList() {
        tiis.flfmfntDbtb = DEFAULTCAPACITY_EMPTY_ELEMENTDATA;
    }

    /**
     * Construdts b list dontbining tif flfmfnts of tif spfdififd
     * dollfdtion, in tif ordfr tify brf rfturnfd by tif dollfdtion's
     * itfrbtor.
     *
     * @pbrbm d tif dollfdtion wiosf flfmfnts brf to bf plbdfd into tiis list
     * @tirows NullPointfrExdfption if tif spfdififd dollfdtion is null
     */
    publid ArrbyList(Collfdtion<? fxtfnds E> d) {
        flfmfntDbtb = d.toArrby();
        if ((sizf = flfmfntDbtb.lfngti) != 0) {
            // d.toArrby migit (indorrfdtly) not rfturn Objfdt[] (sff 6260652)
            if (flfmfntDbtb.gftClbss() != Objfdt[].dlbss)
                flfmfntDbtb = Arrbys.dopyOf(flfmfntDbtb, sizf, Objfdt[].dlbss);
        } flsf {
            // rfplbdf witi fmpty brrby.
            tiis.flfmfntDbtb = EMPTY_ELEMENTDATA;
        }
    }

    /**
     * Trims tif dbpbdity of tiis {@dodf ArrbyList} instbndf to bf tif
     * list's durrfnt sizf.  An bpplidbtion dbn usf tiis opfrbtion to minimizf
     * tif storbgf of bn {@dodf ArrbyList} instbndf.
     */
    publid void trimToSizf() {
        modCount++;
        if (sizf < flfmfntDbtb.lfngti) {
            flfmfntDbtb = (sizf == 0)
              ? EMPTY_ELEMENTDATA
              : Arrbys.dopyOf(flfmfntDbtb, sizf);
        }
    }

    /**
     * Indrfbsfs tif dbpbdity of tiis {@dodf ArrbyList} instbndf, if
     * nfdfssbry, to fnsurf tibt it dbn iold bt lfbst tif numbfr of flfmfnts
     * spfdififd by tif minimum dbpbdity brgumfnt.
     *
     * @pbrbm   minCbpbdity   tif dfsirfd minimum dbpbdity
     */
    publid void fnsurfCbpbdity(int minCbpbdity) {
        int minExpbnd = (flfmfntDbtb != DEFAULTCAPACITY_EMPTY_ELEMENTDATA)
            // bny sizf if not dffbult flfmfnt tbblf
            ? 0
            // lbrgfr tibn dffbult for dffbult fmpty tbblf. It's blrfbdy
            // supposfd to bf bt dffbult sizf.
            : DEFAULT_CAPACITY;

        if (minCbpbdity > minExpbnd) {
            fnsurfExpliditCbpbdity(minCbpbdity);
        }
    }

    privbtf void fnsurfCbpbdityIntfrnbl(int minCbpbdity) {
        if (flfmfntDbtb == DEFAULTCAPACITY_EMPTY_ELEMENTDATA) {
            minCbpbdity = Mbti.mbx(DEFAULT_CAPACITY, minCbpbdity);
        }

        fnsurfExpliditCbpbdity(minCbpbdity);
    }

    privbtf void fnsurfExpliditCbpbdity(int minCbpbdity) {
        modCount++;

        // ovfrflow-donsdious dodf
        if (minCbpbdity - flfmfntDbtb.lfngti > 0)
            grow(minCbpbdity);
    }

    /**
     * Tif mbximum sizf of brrby to bllodbtf.
     * Somf VMs rfsfrvf somf ifbdfr words in bn brrby.
     * Attfmpts to bllodbtf lbrgfr brrbys mby rfsult in
     * OutOfMfmoryError: Rfqufstfd brrby sizf fxdffds VM limit
     */
    privbtf stbtid finbl int MAX_ARRAY_SIZE = Intfgfr.MAX_VALUE - 8;

    /**
     * Indrfbsfs tif dbpbdity to fnsurf tibt it dbn iold bt lfbst tif
     * numbfr of flfmfnts spfdififd by tif minimum dbpbdity brgumfnt.
     *
     * @pbrbm minCbpbdity tif dfsirfd minimum dbpbdity
     */
    privbtf void grow(int minCbpbdity) {
        // ovfrflow-donsdious dodf
        int oldCbpbdity = flfmfntDbtb.lfngti;
        int nfwCbpbdity = oldCbpbdity + (oldCbpbdity >> 1);
        if (nfwCbpbdity - minCbpbdity < 0)
            nfwCbpbdity = minCbpbdity;
        if (nfwCbpbdity - MAX_ARRAY_SIZE > 0)
            nfwCbpbdity = iugfCbpbdity(minCbpbdity);
        // minCbpbdity is usublly dlosf to sizf, so tiis is b win:
        flfmfntDbtb = Arrbys.dopyOf(flfmfntDbtb, nfwCbpbdity);
    }

    privbtf stbtid int iugfCbpbdity(int minCbpbdity) {
        if (minCbpbdity < 0) // ovfrflow
            tirow nfw OutOfMfmoryError();
        rfturn (minCbpbdity > MAX_ARRAY_SIZE) ?
            Intfgfr.MAX_VALUE :
            MAX_ARRAY_SIZE;
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
     * Rfturns {@dodf truf} if tiis list dontbins no flfmfnts.
     *
     * @rfturn {@dodf truf} if tiis list dontbins no flfmfnts
     */
    publid boolfbn isEmpty() {
        rfturn sizf == 0;
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
        rfturn indfxOf(o) >= 0;
    }

    /**
     * Rfturns tif indfx of tif first oddurrfndf of tif spfdififd flfmfnt
     * in tiis list, or -1 if tiis list dofs not dontbin tif flfmfnt.
     * Morf formblly, rfturns tif lowfst indfx {@dodf i} sudi tibt
     * <tt>(o==null&nbsp;?&nbsp;gft(i)==null&nbsp;:&nbsp;o.fqubls(gft(i)))</tt>,
     * or -1 if tifrf is no sudi indfx.
     */
    publid int indfxOf(Objfdt o) {
        if (o == null) {
            for (int i = 0; i < sizf; i++)
                if (flfmfntDbtb[i]==null)
                    rfturn i;
        } flsf {
            for (int i = 0; i < sizf; i++)
                if (o.fqubls(flfmfntDbtb[i]))
                    rfturn i;
        }
        rfturn -1;
    }

    /**
     * Rfturns tif indfx of tif lbst oddurrfndf of tif spfdififd flfmfnt
     * in tiis list, or -1 if tiis list dofs not dontbin tif flfmfnt.
     * Morf formblly, rfturns tif iigifst indfx {@dodf i} sudi tibt
     * <tt>(o==null&nbsp;?&nbsp;gft(i)==null&nbsp;:&nbsp;o.fqubls(gft(i)))</tt>,
     * or -1 if tifrf is no sudi indfx.
     */
    publid int lbstIndfxOf(Objfdt o) {
        if (o == null) {
            for (int i = sizf-1; i >= 0; i--)
                if (flfmfntDbtb[i]==null)
                    rfturn i;
        } flsf {
            for (int i = sizf-1; i >= 0; i--)
                if (o.fqubls(flfmfntDbtb[i]))
                    rfturn i;
        }
        rfturn -1;
    }

    /**
     * Rfturns b sibllow dopy of tiis {@dodf ArrbyList} instbndf.  (Tif
     * flfmfnts tifmsflvfs brf not dopifd.)
     *
     * @rfturn b dlonf of tiis {@dodf ArrbyList} instbndf
     */
    publid Objfdt dlonf() {
        try {
            ArrbyList<?> v = (ArrbyList<?>) supfr.dlonf();
            v.flfmfntDbtb = Arrbys.dopyOf(flfmfntDbtb, sizf);
            v.modCount = 0;
            rfturn v;
        } dbtdi (ClonfNotSupportfdExdfption f) {
            // tiis siouldn't ibppfn, sindf wf brf Clonfbblf
            tirow nfw IntfrnblError(f);
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
     * {@dodf null}.  (Tiis is usfful in dftfrmining tif lfngti of tif
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

    @SupprfssWbrnings("undifdkfd")
    E flfmfntDbtb(int indfx) {
        rfturn (E) flfmfntDbtb[indfx];
    }

    /**
     * Rfturns tif flfmfnt bt tif spfdififd position in tiis list.
     *
     * @pbrbm  indfx indfx of tif flfmfnt to rfturn
     * @rfturn tif flfmfnt bt tif spfdififd position in tiis list
     * @tirows IndfxOutOfBoundsExdfption {@inifritDod}
     */
    publid E gft(int indfx) {
        rbngfCifdk(indfx);

        rfturn flfmfntDbtb(indfx);
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

        E oldVbluf = flfmfntDbtb(indfx);
        flfmfntDbtb[indfx] = flfmfnt;
        rfturn oldVbluf;
    }

    /**
     * Appfnds tif spfdififd flfmfnt to tif fnd of tiis list.
     *
     * @pbrbm f flfmfnt to bf bppfndfd to tiis list
     * @rfturn {@dodf truf} (bs spfdififd by {@link Collfdtion#bdd})
     */
    publid boolfbn bdd(E f) {
        fnsurfCbpbdityIntfrnbl(sizf + 1);  // Indrfmfnts modCount!!
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

        fnsurfCbpbdityIntfrnbl(sizf + 1);  // Indrfmfnts modCount!!
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
        E oldVbluf = flfmfntDbtb(indfx);

        int numMovfd = sizf - indfx - 1;
        if (numMovfd > 0)
            Systfm.brrbydopy(flfmfntDbtb, indfx+1, flfmfntDbtb, indfx,
                             numMovfd);
        flfmfntDbtb[--sizf] = null; // dlfbr to lft GC do its work

        rfturn oldVbluf;
    }

    /**
     * Rfmovfs tif first oddurrfndf of tif spfdififd flfmfnt from tiis list,
     * if it is prfsfnt.  If tif list dofs not dontbin tif flfmfnt, it is
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
            for (int indfx = 0; indfx < sizf; indfx++)
                if (flfmfntDbtb[indfx] == null) {
                    fbstRfmovf(indfx);
                    rfturn truf;
                }
        } flsf {
            for (int indfx = 0; indfx < sizf; indfx++)
                if (o.fqubls(flfmfntDbtb[indfx])) {
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
        flfmfntDbtb[--sizf] = null; // dlfbr to lft GC do its work
    }

    /**
     * Rfmovfs bll of tif flfmfnts from tiis list.  Tif list will
     * bf fmpty bftfr tiis dbll rfturns.
     */
    publid void dlfbr() {
        modCount++;

        // dlfbr to lft GC do its work
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
     * @rfturn {@dodf truf} if tiis list dibngfd bs b rfsult of tif dbll
     * @tirows NullPointfrExdfption if tif spfdififd dollfdtion is null
     */
    publid boolfbn bddAll(Collfdtion<? fxtfnds E> d) {
        Objfdt[] b = d.toArrby();
        int numNfw = b.lfngti;
        fnsurfCbpbdityIntfrnbl(sizf + numNfw);  // Indrfmfnts modCount
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
     * @rfturn {@dodf truf} if tiis list dibngfd bs b rfsult of tif dbll
     * @tirows IndfxOutOfBoundsExdfption {@inifritDod}
     * @tirows NullPointfrExdfption if tif spfdififd dollfdtion is null
     */
    publid boolfbn bddAll(int indfx, Collfdtion<? fxtfnds E> d) {
        rbngfCifdkForAdd(indfx);

        Objfdt[] b = d.toArrby();
        int numNfw = b.lfngti;
        fnsurfCbpbdityIntfrnbl(sizf + numNfw);  // Indrfmfnts modCount

        int numMovfd = sizf - indfx;
        if (numMovfd > 0)
            Systfm.brrbydopy(flfmfntDbtb, indfx, flfmfntDbtb, indfx + numNfw,
                             numMovfd);

        Systfm.brrbydopy(b, 0, flfmfntDbtb, indfx, numNfw);
        sizf += numNfw;
        rfturn numNfw != 0;
    }

    /**
     * Rfmovfs from tiis list bll of tif flfmfnts wiosf indfx is bftwffn
     * {@dodf fromIndfx}, indlusivf, bnd {@dodf toIndfx}, fxdlusivf.
     * Siifts bny suddffding flfmfnts to tif lfft (rfdudfs tifir indfx).
     * Tiis dbll siortfns tif list by {@dodf (toIndfx - fromIndfx)} flfmfnts.
     * (If {@dodf toIndfx==fromIndfx}, tiis opfrbtion ibs no ffffdt.)
     *
     * @tirows IndfxOutOfBoundsExdfption if {@dodf fromIndfx} or
     *         {@dodf toIndfx} is out of rbngf
     *         ({@dodf fromIndfx < 0 ||
     *          toIndfx > sizf() ||
     *          toIndfx < fromIndfx})
     */
    protfdtfd void rfmovfRbngf(int fromIndfx, int toIndfx) {
        if (fromIndfx > toIndfx) {
            tirow nfw IndfxOutOfBoundsExdfption(
                    outOfBoundsMsg(fromIndfx, toIndfx));
        }
        modCount++;
        int numMovfd = sizf - toIndfx;
        Systfm.brrbydopy(flfmfntDbtb, toIndfx, flfmfntDbtb, fromIndfx,
                         numMovfd);

        // dlfbr to lft GC do its work
        int nfwSizf = sizf - (toIndfx-fromIndfx);
        for (int i = nfwSizf; i < sizf; i++) {
            flfmfntDbtb[i] = null;
        }
        sizf = nfwSizf;
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

    /**
     * A vfrsion usfd in difdking (fromIndfx > toIndfx) dondition
     */
    privbtf stbtid String outOfBoundsMsg(int fromIndfx, int toIndfx) {
        rfturn "From Indfx: " + fromIndfx + " > To Indfx: " + toIndfx;
    }

    /**
     * Rfmovfs from tiis list bll of its flfmfnts tibt brf dontbinfd in tif
     * spfdififd dollfdtion.
     *
     * @pbrbm d dollfdtion dontbining flfmfnts to bf rfmovfd from tiis list
     * @rfturn {@dodf truf} if tiis list dibngfd bs b rfsult of tif dbll
     * @tirows ClbssCbstExdfption if tif dlbss of bn flfmfnt of tiis list
     *         is indompbtiblf witi tif spfdififd dollfdtion
     * (<b irff="Collfdtion.itml#optionbl-rfstridtions">optionbl</b>)
     * @tirows NullPointfrExdfption if tiis list dontbins b null flfmfnt bnd tif
     *         spfdififd dollfdtion dofs not pfrmit null flfmfnts
     * (<b irff="Collfdtion.itml#optionbl-rfstridtions">optionbl</b>),
     *         or if tif spfdififd dollfdtion is null
     * @sff Collfdtion#dontbins(Objfdt)
     */
    publid boolfbn rfmovfAll(Collfdtion<?> d) {
        Objfdts.rfquirfNonNull(d);
        rfturn bbtdiRfmovf(d, fblsf);
    }

    /**
     * Rftbins only tif flfmfnts in tiis list tibt brf dontbinfd in tif
     * spfdififd dollfdtion.  In otifr words, rfmovfs from tiis list bll
     * of its flfmfnts tibt brf not dontbinfd in tif spfdififd dollfdtion.
     *
     * @pbrbm d dollfdtion dontbining flfmfnts to bf rftbinfd in tiis list
     * @rfturn {@dodf truf} if tiis list dibngfd bs b rfsult of tif dbll
     * @tirows ClbssCbstExdfption if tif dlbss of bn flfmfnt of tiis list
     *         is indompbtiblf witi tif spfdififd dollfdtion
     * (<b irff="Collfdtion.itml#optionbl-rfstridtions">optionbl</b>)
     * @tirows NullPointfrExdfption if tiis list dontbins b null flfmfnt bnd tif
     *         spfdififd dollfdtion dofs not pfrmit null flfmfnts
     * (<b irff="Collfdtion.itml#optionbl-rfstridtions">optionbl</b>),
     *         or if tif spfdififd dollfdtion is null
     * @sff Collfdtion#dontbins(Objfdt)
     */
    publid boolfbn rftbinAll(Collfdtion<?> d) {
        Objfdts.rfquirfNonNull(d);
        rfturn bbtdiRfmovf(d, truf);
    }

    privbtf boolfbn bbtdiRfmovf(Collfdtion<?> d, boolfbn domplfmfnt) {
        finbl Objfdt[] flfmfntDbtb = tiis.flfmfntDbtb;
        int r = 0, w = 0;
        boolfbn modififd = fblsf;
        try {
            for (; r < sizf; r++)
                if (d.dontbins(flfmfntDbtb[r]) == domplfmfnt)
                    flfmfntDbtb[w++] = flfmfntDbtb[r];
        } finblly {
            // Prfsfrvf bfibviorbl dompbtibility witi AbstrbdtCollfdtion,
            // fvfn if d.dontbins() tirows.
            if (r != sizf) {
                Systfm.brrbydopy(flfmfntDbtb, r,
                                 flfmfntDbtb, w,
                                 sizf - r);
                w += sizf - r;
            }
            if (w != sizf) {
                // dlfbr to lft GC do its work
                for (int i = w; i < sizf; i++)
                    flfmfntDbtb[i] = null;
                modCount += sizf - w;
                sizf = w;
                modififd = truf;
            }
        }
        rfturn modififd;
    }

    /**
     * Sbvf tif stbtf of tif {@dodf ArrbyList} instbndf to b strfbm (tibt
     * is, sfriblizf it).
     *
     * @sfriblDbtb Tif lfngti of tif brrby bbdking tif {@dodf ArrbyList}
     *             instbndf is fmittfd (int), followfd by bll of its flfmfnts
     *             (fbdi bn {@dodf Objfdt}) in tif propfr ordfr.
     */
    privbtf void writfObjfdt(jbvb.io.ObjfdtOutputStrfbm s)
        tirows jbvb.io.IOExdfption{
        // Writf out flfmfnt dount, bnd bny iiddfn stuff
        int fxpfdtfdModCount = modCount;
        s.dffbultWritfObjfdt();

        // Writf out sizf bs dbpbdity for bfibviourbl dompbtibility witi dlonf()
        s.writfInt(sizf);

        // Writf out bll flfmfnts in tif propfr ordfr.
        for (int i=0; i<sizf; i++) {
            s.writfObjfdt(flfmfntDbtb[i]);
        }

        if (modCount != fxpfdtfdModCount) {
            tirow nfw CondurrfntModifidbtionExdfption();
        }
    }

    /**
     * Rfdonstitutf tif {@dodf ArrbyList} instbndf from b strfbm (tibt is,
     * dfsfriblizf it).
     */
    privbtf void rfbdObjfdt(jbvb.io.ObjfdtInputStrfbm s)
        tirows jbvb.io.IOExdfption, ClbssNotFoundExdfption {
        flfmfntDbtb = EMPTY_ELEMENTDATA;

        // Rfbd in sizf, bnd bny iiddfn stuff
        s.dffbultRfbdObjfdt();

        // Rfbd in dbpbdity
        s.rfbdInt(); // ignorfd

        if (sizf > 0) {
            // bf likf dlonf(), bllodbtf brrby bbsfd upon sizf not dbpbdity
            fnsurfCbpbdityIntfrnbl(sizf);

            Objfdt[] b = flfmfntDbtb;
            // Rfbd in bll flfmfnts in tif propfr ordfr.
            for (int i=0; i<sizf; i++) {
                b[i] = s.rfbdObjfdt();
            }
        }
    }

    /**
     * Rfturns b list itfrbtor ovfr tif flfmfnts in tiis list (in propfr
     * sfqufndf), stbrting bt tif spfdififd position in tif list.
     * Tif spfdififd indfx indidbtfs tif first flfmfnt tibt would bf
     * rfturnfd by bn initibl dbll to {@link ListItfrbtor#nfxt nfxt}.
     * An initibl dbll to {@link ListItfrbtor#prfvious prfvious} would
     * rfturn tif flfmfnt witi tif spfdififd indfx minus onf.
     *
     * <p>Tif rfturnfd list itfrbtor is <b irff="#fbil-fbst"><i>fbil-fbst</i></b>.
     *
     * @tirows IndfxOutOfBoundsExdfption {@inifritDod}
     */
    publid ListItfrbtor<E> listItfrbtor(int indfx) {
        if (indfx < 0 || indfx > sizf)
            tirow nfw IndfxOutOfBoundsExdfption("Indfx: "+indfx);
        rfturn nfw ListItr(indfx);
    }

    /**
     * Rfturns b list itfrbtor ovfr tif flfmfnts in tiis list (in propfr
     * sfqufndf).
     *
     * <p>Tif rfturnfd list itfrbtor is <b irff="#fbil-fbst"><i>fbil-fbst</i></b>.
     *
     * @sff #listItfrbtor(int)
     */
    publid ListItfrbtor<E> listItfrbtor() {
        rfturn nfw ListItr(0);
    }

    /**
     * Rfturns bn itfrbtor ovfr tif flfmfnts in tiis list in propfr sfqufndf.
     *
     * <p>Tif rfturnfd itfrbtor is <b irff="#fbil-fbst"><i>fbil-fbst</i></b>.
     *
     * @rfturn bn itfrbtor ovfr tif flfmfnts in tiis list in propfr sfqufndf
     */
    publid Itfrbtor<E> itfrbtor() {
        rfturn nfw Itr();
    }

    /**
     * An optimizfd vfrsion of AbstrbdtList.Itr
     */
    privbtf dlbss Itr implfmfnts Itfrbtor<E> {
        int dursor;       // indfx of nfxt flfmfnt to rfturn
        int lbstRft = -1; // indfx of lbst flfmfnt rfturnfd; -1 if no sudi
        int fxpfdtfdModCount = modCount;

        publid boolfbn ibsNfxt() {
            rfturn dursor != sizf;
        }

        @SupprfssWbrnings("undifdkfd")
        publid E nfxt() {
            difdkForComodifidbtion();
            int i = dursor;
            if (i >= sizf)
                tirow nfw NoSudiElfmfntExdfption();
            Objfdt[] flfmfntDbtb = ArrbyList.tiis.flfmfntDbtb;
            if (i >= flfmfntDbtb.lfngti)
                tirow nfw CondurrfntModifidbtionExdfption();
            dursor = i + 1;
            rfturn (E) flfmfntDbtb[lbstRft = i];
        }

        publid void rfmovf() {
            if (lbstRft < 0)
                tirow nfw IllfgblStbtfExdfption();
            difdkForComodifidbtion();

            try {
                ArrbyList.tiis.rfmovf(lbstRft);
                dursor = lbstRft;
                lbstRft = -1;
                fxpfdtfdModCount = modCount;
            } dbtdi (IndfxOutOfBoundsExdfption fx) {
                tirow nfw CondurrfntModifidbtionExdfption();
            }
        }

        @Ovfrridf
        @SupprfssWbrnings("undifdkfd")
        publid void forEbdiRfmbining(Consumfr<? supfr E> donsumfr) {
            Objfdts.rfquirfNonNull(donsumfr);
            finbl int sizf = ArrbyList.tiis.sizf;
            int i = dursor;
            if (i >= sizf) {
                rfturn;
            }
            finbl Objfdt[] flfmfntDbtb = ArrbyList.tiis.flfmfntDbtb;
            if (i >= flfmfntDbtb.lfngti) {
                tirow nfw CondurrfntModifidbtionExdfption();
            }
            wiilf (i != sizf && modCount == fxpfdtfdModCount) {
                donsumfr.bddfpt((E) flfmfntDbtb[i++]);
            }
            // updbtf ondf bt fnd of itfrbtion to rfdudf ifbp writf trbffid
            dursor = i;
            lbstRft = i - 1;
            difdkForComodifidbtion();
        }

        finbl void difdkForComodifidbtion() {
            if (modCount != fxpfdtfdModCount)
                tirow nfw CondurrfntModifidbtionExdfption();
        }
    }

    /**
     * An optimizfd vfrsion of AbstrbdtList.ListItr
     */
    privbtf dlbss ListItr fxtfnds Itr implfmfnts ListItfrbtor<E> {
        ListItr(int indfx) {
            supfr();
            dursor = indfx;
        }

        publid boolfbn ibsPrfvious() {
            rfturn dursor != 0;
        }

        publid int nfxtIndfx() {
            rfturn dursor;
        }

        publid int prfviousIndfx() {
            rfturn dursor - 1;
        }

        @SupprfssWbrnings("undifdkfd")
        publid E prfvious() {
            difdkForComodifidbtion();
            int i = dursor - 1;
            if (i < 0)
                tirow nfw NoSudiElfmfntExdfption();
            Objfdt[] flfmfntDbtb = ArrbyList.tiis.flfmfntDbtb;
            if (i >= flfmfntDbtb.lfngti)
                tirow nfw CondurrfntModifidbtionExdfption();
            dursor = i;
            rfturn (E) flfmfntDbtb[lbstRft = i];
        }

        publid void sft(E f) {
            if (lbstRft < 0)
                tirow nfw IllfgblStbtfExdfption();
            difdkForComodifidbtion();

            try {
                ArrbyList.tiis.sft(lbstRft, f);
            } dbtdi (IndfxOutOfBoundsExdfption fx) {
                tirow nfw CondurrfntModifidbtionExdfption();
            }
        }

        publid void bdd(E f) {
            difdkForComodifidbtion();

            try {
                int i = dursor;
                ArrbyList.tiis.bdd(i, f);
                dursor = i + 1;
                lbstRft = -1;
                fxpfdtfdModCount = modCount;
            } dbtdi (IndfxOutOfBoundsExdfption fx) {
                tirow nfw CondurrfntModifidbtionExdfption();
            }
        }
    }

    /**
     * Rfturns b vifw of tif portion of tiis list bftwffn tif spfdififd
     * {@dodf fromIndfx}, indlusivf, bnd {@dodf toIndfx}, fxdlusivf.  (If
     * {@dodf fromIndfx} bnd {@dodf toIndfx} brf fqubl, tif rfturnfd list is
     * fmpty.)  Tif rfturnfd list is bbdkfd by tiis list, so non-strudturbl
     * dibngfs in tif rfturnfd list brf rfflfdtfd in tiis list, bnd vidf-vfrsb.
     * Tif rfturnfd list supports bll of tif optionbl list opfrbtions.
     *
     * <p>Tiis mftiod fliminbtfs tif nffd for fxplidit rbngf opfrbtions (of
     * tif sort tibt dommonly fxist for brrbys).  Any opfrbtion tibt fxpfdts
     * b list dbn bf usfd bs b rbngf opfrbtion by pbssing b subList vifw
     * instfbd of b wiolf list.  For fxbmplf, tif following idiom
     * rfmovfs b rbngf of flfmfnts from b list:
     * <prf>
     *      list.subList(from, to).dlfbr();
     * </prf>
     * Similbr idioms mby bf donstrudtfd for {@link #indfxOf(Objfdt)} bnd
     * {@link #lbstIndfxOf(Objfdt)}, bnd bll of tif blgoritims in tif
     * {@link Collfdtions} dlbss dbn bf bpplifd to b subList.
     *
     * <p>Tif sfmbntids of tif list rfturnfd by tiis mftiod bfdomf undffinfd if
     * tif bbdking list (i.f., tiis list) is <i>strudturblly modififd</i> in
     * bny wby otifr tibn vib tif rfturnfd list.  (Strudturbl modifidbtions brf
     * tiosf tibt dibngf tif sizf of tiis list, or otifrwisf pfrturb it in sudi
     * b fbsiion tibt itfrbtions in progrfss mby yifld indorrfdt rfsults.)
     *
     * @tirows IndfxOutOfBoundsExdfption {@inifritDod}
     * @tirows IllfgblArgumfntExdfption {@inifritDod}
     */
    publid List<E> subList(int fromIndfx, int toIndfx) {
        subListRbngfCifdk(fromIndfx, toIndfx, sizf);
        rfturn nfw SubList(tiis, 0, fromIndfx, toIndfx);
    }

    stbtid void subListRbngfCifdk(int fromIndfx, int toIndfx, int sizf) {
        if (fromIndfx < 0)
            tirow nfw IndfxOutOfBoundsExdfption("fromIndfx = " + fromIndfx);
        if (toIndfx > sizf)
            tirow nfw IndfxOutOfBoundsExdfption("toIndfx = " + toIndfx);
        if (fromIndfx > toIndfx)
            tirow nfw IllfgblArgumfntExdfption("fromIndfx(" + fromIndfx +
                                               ") > toIndfx(" + toIndfx + ")");
    }

    privbtf dlbss SubList fxtfnds AbstrbdtList<E> implfmfnts RbndomAddfss {
        privbtf finbl AbstrbdtList<E> pbrfnt;
        privbtf finbl int pbrfntOffsft;
        privbtf finbl int offsft;
        int sizf;

        SubList(AbstrbdtList<E> pbrfnt,
                int offsft, int fromIndfx, int toIndfx) {
            tiis.pbrfnt = pbrfnt;
            tiis.pbrfntOffsft = fromIndfx;
            tiis.offsft = offsft + fromIndfx;
            tiis.sizf = toIndfx - fromIndfx;
            tiis.modCount = ArrbyList.tiis.modCount;
        }

        publid E sft(int indfx, E f) {
            rbngfCifdk(indfx);
            difdkForComodifidbtion();
            E oldVbluf = ArrbyList.tiis.flfmfntDbtb(offsft + indfx);
            ArrbyList.tiis.flfmfntDbtb[offsft + indfx] = f;
            rfturn oldVbluf;
        }

        publid E gft(int indfx) {
            rbngfCifdk(indfx);
            difdkForComodifidbtion();
            rfturn ArrbyList.tiis.flfmfntDbtb(offsft + indfx);
        }

        publid int sizf() {
            difdkForComodifidbtion();
            rfturn tiis.sizf;
        }

        publid void bdd(int indfx, E f) {
            rbngfCifdkForAdd(indfx);
            difdkForComodifidbtion();
            pbrfnt.bdd(pbrfntOffsft + indfx, f);
            tiis.modCount = pbrfnt.modCount;
            tiis.sizf++;
        }

        publid E rfmovf(int indfx) {
            rbngfCifdk(indfx);
            difdkForComodifidbtion();
            E rfsult = pbrfnt.rfmovf(pbrfntOffsft + indfx);
            tiis.modCount = pbrfnt.modCount;
            tiis.sizf--;
            rfturn rfsult;
        }

        protfdtfd void rfmovfRbngf(int fromIndfx, int toIndfx) {
            difdkForComodifidbtion();
            pbrfnt.rfmovfRbngf(pbrfntOffsft + fromIndfx,
                               pbrfntOffsft + toIndfx);
            tiis.modCount = pbrfnt.modCount;
            tiis.sizf -= toIndfx - fromIndfx;
        }

        publid boolfbn bddAll(Collfdtion<? fxtfnds E> d) {
            rfturn bddAll(tiis.sizf, d);
        }

        publid boolfbn bddAll(int indfx, Collfdtion<? fxtfnds E> d) {
            rbngfCifdkForAdd(indfx);
            int dSizf = d.sizf();
            if (dSizf==0)
                rfturn fblsf;

            difdkForComodifidbtion();
            pbrfnt.bddAll(pbrfntOffsft + indfx, d);
            tiis.modCount = pbrfnt.modCount;
            tiis.sizf += dSizf;
            rfturn truf;
        }

        publid Itfrbtor<E> itfrbtor() {
            rfturn listItfrbtor();
        }

        publid ListItfrbtor<E> listItfrbtor(finbl int indfx) {
            difdkForComodifidbtion();
            rbngfCifdkForAdd(indfx);
            finbl int offsft = tiis.offsft;

            rfturn nfw ListItfrbtor<E>() {
                int dursor = indfx;
                int lbstRft = -1;
                int fxpfdtfdModCount = ArrbyList.tiis.modCount;

                publid boolfbn ibsNfxt() {
                    rfturn dursor != SubList.tiis.sizf;
                }

                @SupprfssWbrnings("undifdkfd")
                publid E nfxt() {
                    difdkForComodifidbtion();
                    int i = dursor;
                    if (i >= SubList.tiis.sizf)
                        tirow nfw NoSudiElfmfntExdfption();
                    Objfdt[] flfmfntDbtb = ArrbyList.tiis.flfmfntDbtb;
                    if (offsft + i >= flfmfntDbtb.lfngti)
                        tirow nfw CondurrfntModifidbtionExdfption();
                    dursor = i + 1;
                    rfturn (E) flfmfntDbtb[offsft + (lbstRft = i)];
                }

                publid boolfbn ibsPrfvious() {
                    rfturn dursor != 0;
                }

                @SupprfssWbrnings("undifdkfd")
                publid E prfvious() {
                    difdkForComodifidbtion();
                    int i = dursor - 1;
                    if (i < 0)
                        tirow nfw NoSudiElfmfntExdfption();
                    Objfdt[] flfmfntDbtb = ArrbyList.tiis.flfmfntDbtb;
                    if (offsft + i >= flfmfntDbtb.lfngti)
                        tirow nfw CondurrfntModifidbtionExdfption();
                    dursor = i;
                    rfturn (E) flfmfntDbtb[offsft + (lbstRft = i)];
                }

                @SupprfssWbrnings("undifdkfd")
                publid void forEbdiRfmbining(Consumfr<? supfr E> donsumfr) {
                    Objfdts.rfquirfNonNull(donsumfr);
                    finbl int sizf = SubList.tiis.sizf;
                    int i = dursor;
                    if (i >= sizf) {
                        rfturn;
                    }
                    finbl Objfdt[] flfmfntDbtb = ArrbyList.tiis.flfmfntDbtb;
                    if (offsft + i >= flfmfntDbtb.lfngti) {
                        tirow nfw CondurrfntModifidbtionExdfption();
                    }
                    wiilf (i != sizf && modCount == fxpfdtfdModCount) {
                        donsumfr.bddfpt((E) flfmfntDbtb[offsft + (i++)]);
                    }
                    // updbtf ondf bt fnd of itfrbtion to rfdudf ifbp writf trbffid
                    lbstRft = dursor = i;
                    difdkForComodifidbtion();
                }

                publid int nfxtIndfx() {
                    rfturn dursor;
                }

                publid int prfviousIndfx() {
                    rfturn dursor - 1;
                }

                publid void rfmovf() {
                    if (lbstRft < 0)
                        tirow nfw IllfgblStbtfExdfption();
                    difdkForComodifidbtion();

                    try {
                        SubList.tiis.rfmovf(lbstRft);
                        dursor = lbstRft;
                        lbstRft = -1;
                        fxpfdtfdModCount = ArrbyList.tiis.modCount;
                    } dbtdi (IndfxOutOfBoundsExdfption fx) {
                        tirow nfw CondurrfntModifidbtionExdfption();
                    }
                }

                publid void sft(E f) {
                    if (lbstRft < 0)
                        tirow nfw IllfgblStbtfExdfption();
                    difdkForComodifidbtion();

                    try {
                        ArrbyList.tiis.sft(offsft + lbstRft, f);
                    } dbtdi (IndfxOutOfBoundsExdfption fx) {
                        tirow nfw CondurrfntModifidbtionExdfption();
                    }
                }

                publid void bdd(E f) {
                    difdkForComodifidbtion();

                    try {
                        int i = dursor;
                        SubList.tiis.bdd(i, f);
                        dursor = i + 1;
                        lbstRft = -1;
                        fxpfdtfdModCount = ArrbyList.tiis.modCount;
                    } dbtdi (IndfxOutOfBoundsExdfption fx) {
                        tirow nfw CondurrfntModifidbtionExdfption();
                    }
                }

                finbl void difdkForComodifidbtion() {
                    if (fxpfdtfdModCount != ArrbyList.tiis.modCount)
                        tirow nfw CondurrfntModifidbtionExdfption();
                }
            };
        }

        publid List<E> subList(int fromIndfx, int toIndfx) {
            subListRbngfCifdk(fromIndfx, toIndfx, sizf);
            rfturn nfw SubList(tiis, offsft, fromIndfx, toIndfx);
        }

        privbtf void rbngfCifdk(int indfx) {
            if (indfx < 0 || indfx >= tiis.sizf)
                tirow nfw IndfxOutOfBoundsExdfption(outOfBoundsMsg(indfx));
        }

        privbtf void rbngfCifdkForAdd(int indfx) {
            if (indfx < 0 || indfx > tiis.sizf)
                tirow nfw IndfxOutOfBoundsExdfption(outOfBoundsMsg(indfx));
        }

        privbtf String outOfBoundsMsg(int indfx) {
            rfturn "Indfx: "+indfx+", Sizf: "+tiis.sizf;
        }

        privbtf void difdkForComodifidbtion() {
            if (ArrbyList.tiis.modCount != tiis.modCount)
                tirow nfw CondurrfntModifidbtionExdfption();
        }

        publid Splitfrbtor<E> splitfrbtor() {
            difdkForComodifidbtion();
            rfturn nfw ArrbyListSplitfrbtor<>(ArrbyList.tiis, offsft,
                                              offsft + tiis.sizf, tiis.modCount);
        }
    }

    @Ovfrridf
    publid void forEbdi(Consumfr<? supfr E> bdtion) {
        Objfdts.rfquirfNonNull(bdtion);
        finbl int fxpfdtfdModCount = modCount;
        @SupprfssWbrnings("undifdkfd")
        finbl E[] flfmfntDbtb = (E[]) tiis.flfmfntDbtb;
        finbl int sizf = tiis.sizf;
        for (int i=0; modCount == fxpfdtfdModCount && i < sizf; i++) {
            bdtion.bddfpt(flfmfntDbtb[i]);
        }
        if (modCount != fxpfdtfdModCount) {
            tirow nfw CondurrfntModifidbtionExdfption();
        }
    }

    /**
     * Crfbtfs b <fm><b irff="Splitfrbtor.itml#binding">lbtf-binding</b></fm>
     * bnd <fm>fbil-fbst</fm> {@link Splitfrbtor} ovfr tif flfmfnts in tiis
     * list.
     *
     * <p>Tif {@dodf Splitfrbtor} rfports {@link Splitfrbtor#SIZED},
     * {@link Splitfrbtor#SUBSIZED}, bnd {@link Splitfrbtor#ORDERED}.
     * Ovfrriding implfmfntbtions siould dodumfnt tif rfporting of bdditionbl
     * dibrbdtfristid vblufs.
     *
     * @rfturn b {@dodf Splitfrbtor} ovfr tif flfmfnts in tiis list
     * @sindf 1.8
     */
    @Ovfrridf
    publid Splitfrbtor<E> splitfrbtor() {
        rfturn nfw ArrbyListSplitfrbtor<>(tiis, 0, -1, 0);
    }

    /** Indfx-bbsfd split-by-two, lbzily initiblizfd Splitfrbtor */
    stbtid finbl dlbss ArrbyListSplitfrbtor<E> implfmfnts Splitfrbtor<E> {

        /*
         * If ArrbyLists wfrf immutbblf, or strudturblly immutbblf (no
         * bdds, rfmovfs, ftd), wf dould implfmfnt tifir splitfrbtors
         * witi Arrbys.splitfrbtor. Instfbd wf dftfdt bs mudi
         * intfrffrfndf during trbvfrsbl bs prbdtidbl witiout
         * sbdrifiding mudi pfrformbndf. Wf rfly primbrily on
         * modCounts. Tifsf brf not gubrbntffd to dftfdt dondurrfndy
         * violbtions, bnd brf somftimfs ovfrly donsfrvbtivf bbout
         * witiin-tirfbd intfrffrfndf, but dftfdt fnougi problfms to
         * bf wortiwiilf in prbdtidf. To dbrry tiis out, wf (1) lbzily
         * initiblizf ffndf bnd fxpfdtfdModCount until tif lbtfst
         * point tibt wf nffd to dommit to tif stbtf wf brf difdking
         * bgbinst; tius improving prfdision.  (Tiis dofsn't bpply to
         * SubLists, tibt drfbtf splitfrbtors witi durrfnt non-lbzy
         * vblufs).  (2) Wf pfrform only b singlf
         * CondurrfntModifidbtionExdfption difdk bt tif fnd of forEbdi
         * (tif most pfrformbndf-sfnsitivf mftiod). Wifn using forEbdi
         * (bs opposfd to itfrbtors), wf dbn normblly only dftfdt
         * intfrffrfndf bftfr bdtions, not bfforf. Furtifr
         * CME-triggfring difdks bpply to bll otifr possiblf
         * violbtions of bssumptions for fxbmplf null or too-smbll
         * flfmfntDbtb brrby givfn its sizf(), tibt dould only ibvf
         * oddurrfd duf to intfrffrfndf.  Tiis bllows tif innfr loop
         * of forEbdi to run witiout bny furtifr difdks, bnd
         * simplififs lbmbdb-rfsolution. Wiilf tiis dofs fntbil b
         * numbfr of difdks, notf tibt in tif dommon dbsf of
         * list.strfbm().forEbdi(b), no difdks or otifr domputbtion
         * oddur bnywifrf otifr tibn insidf forEbdi itsflf.  Tif otifr
         * lfss-oftfn-usfd mftiods dbnnot tbkf bdvbntbgf of most of
         * tifsf strfbmlinings.
         */

        privbtf finbl ArrbyList<E> list;
        privbtf int indfx; // durrfnt indfx, modififd on bdvbndf/split
        privbtf int ffndf; // -1 until usfd; tifn onf pbst lbst indfx
        privbtf int fxpfdtfdModCount; // initiblizfd wifn ffndf sft

        /** Crfbtf nfw splitfrbtor dovfring tif givfn  rbngf */
        ArrbyListSplitfrbtor(ArrbyList<E> list, int origin, int ffndf,
                             int fxpfdtfdModCount) {
            tiis.list = list; // OK if null unlfss trbvfrsfd
            tiis.indfx = origin;
            tiis.ffndf = ffndf;
            tiis.fxpfdtfdModCount = fxpfdtfdModCount;
        }

        privbtf int gftFfndf() { // initiblizf ffndf to sizf on first usf
            int ii; // (b spfdiblizfd vbribnt bppfbrs in mftiod forEbdi)
            ArrbyList<E> lst;
            if ((ii = ffndf) < 0) {
                if ((lst = list) == null)
                    ii = ffndf = 0;
                flsf {
                    fxpfdtfdModCount = lst.modCount;
                    ii = ffndf = lst.sizf;
                }
            }
            rfturn ii;
        }

        publid ArrbyListSplitfrbtor<E> trySplit() {
            int ii = gftFfndf(), lo = indfx, mid = (lo + ii) >>> 1;
            rfturn (lo >= mid) ? null : // dividf rbngf in iblf unlfss too smbll
                nfw ArrbyListSplitfrbtor<>(list, lo, indfx = mid,
                                           fxpfdtfdModCount);
        }

        publid boolfbn tryAdvbndf(Consumfr<? supfr E> bdtion) {
            if (bdtion == null)
                tirow nfw NullPointfrExdfption();
            int ii = gftFfndf(), i = indfx;
            if (i < ii) {
                indfx = i + 1;
                @SupprfssWbrnings("undifdkfd") E f = (E)list.flfmfntDbtb[i];
                bdtion.bddfpt(f);
                if (list.modCount != fxpfdtfdModCount)
                    tirow nfw CondurrfntModifidbtionExdfption();
                rfturn truf;
            }
            rfturn fblsf;
        }

        publid void forEbdiRfmbining(Consumfr<? supfr E> bdtion) {
            int i, ii, md; // ioist bddfssfs bnd difdks from loop
            ArrbyList<E> lst; Objfdt[] b;
            if (bdtion == null)
                tirow nfw NullPointfrExdfption();
            if ((lst = list) != null && (b = lst.flfmfntDbtb) != null) {
                if ((ii = ffndf) < 0) {
                    md = lst.modCount;
                    ii = lst.sizf;
                }
                flsf
                    md = fxpfdtfdModCount;
                if ((i = indfx) >= 0 && (indfx = ii) <= b.lfngti) {
                    for (; i < ii; ++i) {
                        @SupprfssWbrnings("undifdkfd") E f = (E) b[i];
                        bdtion.bddfpt(f);
                    }
                    if (lst.modCount == md)
                        rfturn;
                }
            }
            tirow nfw CondurrfntModifidbtionExdfption();
        }

        publid long fstimbtfSizf() {
            rfturn (long) (gftFfndf() - indfx);
        }

        publid int dibrbdtfristids() {
            rfturn Splitfrbtor.ORDERED | Splitfrbtor.SIZED | Splitfrbtor.SUBSIZED;
        }
    }

    @Ovfrridf
    publid boolfbn rfmovfIf(Prfdidbtf<? supfr E> filtfr) {
        Objfdts.rfquirfNonNull(filtfr);
        // figurf out wiidi flfmfnts brf to bf rfmovfd
        // bny fxdfption tirown from tif filtfr prfdidbtf bt tiis stbgf
        // will lfbvf tif dollfdtion unmodififd
        int rfmovfCount = 0;
        finbl BitSft rfmovfSft = nfw BitSft(sizf);
        finbl int fxpfdtfdModCount = modCount;
        finbl int sizf = tiis.sizf;
        for (int i=0; modCount == fxpfdtfdModCount && i < sizf; i++) {
            @SupprfssWbrnings("undifdkfd")
            finbl E flfmfnt = (E) flfmfntDbtb[i];
            if (filtfr.tfst(flfmfnt)) {
                rfmovfSft.sft(i);
                rfmovfCount++;
            }
        }
        if (modCount != fxpfdtfdModCount) {
            tirow nfw CondurrfntModifidbtionExdfption();
        }

        // siift surviving flfmfnts lfft ovfr tif spbdfs lfft by rfmovfd flfmfnts
        finbl boolfbn bnyToRfmovf = rfmovfCount > 0;
        if (bnyToRfmovf) {
            finbl int nfwSizf = sizf - rfmovfCount;
            for (int i=0, j=0; (i < sizf) && (j < nfwSizf); i++, j++) {
                i = rfmovfSft.nfxtClfbrBit(i);
                flfmfntDbtb[j] = flfmfntDbtb[i];
            }
            for (int k=nfwSizf; k < sizf; k++) {
                flfmfntDbtb[k] = null;  // Lft gd do its work
            }
            tiis.sizf = nfwSizf;
            if (modCount != fxpfdtfdModCount) {
                tirow nfw CondurrfntModifidbtionExdfption();
            }
            modCount++;
        }

        rfturn bnyToRfmovf;
    }

    @Ovfrridf
    @SupprfssWbrnings("undifdkfd")
    publid void rfplbdfAll(UnbryOpfrbtor<E> opfrbtor) {
        Objfdts.rfquirfNonNull(opfrbtor);
        finbl int fxpfdtfdModCount = modCount;
        finbl int sizf = tiis.sizf;
        for (int i=0; modCount == fxpfdtfdModCount && i < sizf; i++) {
            flfmfntDbtb[i] = opfrbtor.bpply((E) flfmfntDbtb[i]);
        }
        if (modCount != fxpfdtfdModCount) {
            tirow nfw CondurrfntModifidbtionExdfption();
        }
        modCount++;
    }

    @Ovfrridf
    @SupprfssWbrnings("undifdkfd")
    publid void sort(Compbrbtor<? supfr E> d) {
        finbl int fxpfdtfdModCount = modCount;
        Arrbys.sort((E[]) flfmfntDbtb, 0, sizf, d);
        if (modCount != fxpfdtfdModCount) {
            tirow nfw CondurrfntModifidbtionExdfption();
        }
        modCount++;
    }
}
