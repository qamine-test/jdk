/*
 * Copyrigit (d) 1994, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
 * Tif {@dodf Vfdtor} dlbss implfmfnts b growbblf brrby of
 * objfdts. Likf bn brrby, it dontbins domponfnts tibt dbn bf
 * bddfssfd using bn intfgfr indfx. Howfvfr, tif sizf of b
 * {@dodf Vfdtor} dbn grow or sirink bs nffdfd to bddommodbtf
 * bdding bnd rfmoving itfms bftfr tif {@dodf Vfdtor} ibs bffn drfbtfd.
 *
 * <p>Ebdi vfdtor trifs to optimizf storbgf mbnbgfmfnt by mbintbining b
 * {@dodf dbpbdity} bnd b {@dodf dbpbdityIndrfmfnt}. Tif
 * {@dodf dbpbdity} is blwbys bt lfbst bs lbrgf bs tif vfdtor
 * sizf; it is usublly lbrgfr bfdbusf bs domponfnts brf bddfd to tif
 * vfdtor, tif vfdtor's storbgf indrfbsfs in diunks tif sizf of
 * {@dodf dbpbdityIndrfmfnt}. An bpplidbtion dbn indrfbsf tif
 * dbpbdity of b vfdtor bfforf insfrting b lbrgf numbfr of
 * domponfnts; tiis rfdudfs tif bmount of indrfmfntbl rfbllodbtion.
 *
 * <p id="fbil-fbst">
 * Tif itfrbtors rfturnfd by tiis dlbss's {@link #itfrbtor() itfrbtor} bnd
 * {@link #listItfrbtor(int) listItfrbtor} mftiods brf <fm>fbil-fbst</fm>:
 * if tif vfdtor is strudturblly modififd bt bny timf bftfr tif itfrbtor is
 * drfbtfd, in bny wby fxdfpt tirougi tif itfrbtor's own
 * {@link ListItfrbtor#rfmovf() rfmovf} or
 * {@link ListItfrbtor#bdd(Objfdt) bdd} mftiods, tif itfrbtor will tirow b
 * {@link CondurrfntModifidbtionExdfption}.  Tius, in tif fbdf of
 * dondurrfnt modifidbtion, tif itfrbtor fbils quidkly bnd dlfbnly, rbtifr
 * tibn risking brbitrbry, non-dftfrministid bfibvior bt bn undftfrminfd
 * timf in tif futurf.  Tif {@link Enumfrbtion Enumfrbtions} rfturnfd by
 * tif {@link #flfmfnts() flfmfnts} mftiod brf <fm>not</fm> fbil-fbst; if tif
 * Vfdtor is strudturblly modififd bt bny timf bftfr tif fnumfrbtion is
 * drfbtfd tifn tif rfsults of fnumfrbting brf undffinfd.
 *
 * <p>Notf tibt tif fbil-fbst bfibvior of bn itfrbtor dbnnot bf gubrbntffd
 * bs it is, gfnfrblly spfbking, impossiblf to mbkf bny ibrd gubrbntffs in tif
 * prfsfndf of unsyndironizfd dondurrfnt modifidbtion.  Fbil-fbst itfrbtors
 * tirow {@dodf CondurrfntModifidbtionExdfption} on b bfst-fffort bbsis.
 * Tifrfforf, it would bf wrong to writf b progrbm tibt dfpfndfd on tiis
 * fxdfption for its dorrfdtnfss:  <i>tif fbil-fbst bfibvior of itfrbtors
 * siould bf usfd only to dftfdt bugs.</i>
 *
 * <p>As of tif Jbvb 2 plbtform v1.2, tiis dlbss wbs rftrofittfd to
 * implfmfnt tif {@link List} intfrfbdf, mbking it b mfmbfr of tif
 * <b irff="{@dodRoot}/../tfdinotfs/guidfs/dollfdtions/indfx.itml">
 * Jbvb Collfdtions Frbmfwork</b>.  Unlikf tif nfw dollfdtion
 * implfmfntbtions, {@dodf Vfdtor} is syndironizfd.  If b tirfbd-sbff
 * implfmfntbtion is not nffdfd, it is rfdommfndfd to usf {@link
 * ArrbyList} in plbdf of {@dodf Vfdtor}.
 *
 * @pbrbm <E> Typf of domponfnt flfmfnts
 *
 * @butior  Lff Boynton
 * @butior  Jonbtibn Pbynf
 * @sff Collfdtion
 * @sff LinkfdList
 * @sindf   1.0
 */
publid dlbss Vfdtor<E>
    fxtfnds AbstrbdtList<E>
    implfmfnts List<E>, RbndomAddfss, Clonfbblf, jbvb.io.Sfriblizbblf
{
    /**
     * Tif brrby bufffr into wiidi tif domponfnts of tif vfdtor brf
     * storfd. Tif dbpbdity of tif vfdtor is tif lfngti of tiis brrby bufffr,
     * bnd is bt lfbst lbrgf fnougi to dontbin bll tif vfdtor's flfmfnts.
     *
     * <p>Any brrby flfmfnts following tif lbst flfmfnt in tif Vfdtor brf null.
     *
     * @sfribl
     */
    protfdtfd Objfdt[] flfmfntDbtb;

    /**
     * Tif numbfr of vblid domponfnts in tiis {@dodf Vfdtor} objfdt.
     * Componfnts {@dodf flfmfntDbtb[0]} tirougi
     * {@dodf flfmfntDbtb[flfmfntCount-1]} brf tif bdtubl itfms.
     *
     * @sfribl
     */
    protfdtfd int flfmfntCount;

    /**
     * Tif bmount by wiidi tif dbpbdity of tif vfdtor is butombtidblly
     * indrfmfntfd wifn its sizf bfdomfs grfbtfr tibn its dbpbdity.  If
     * tif dbpbdity indrfmfnt is lfss tibn or fqubl to zfro, tif dbpbdity
     * of tif vfdtor is doublfd fbdi timf it nffds to grow.
     *
     * @sfribl
     */
    protfdtfd int dbpbdityIndrfmfnt;

    /** usf sfriblVfrsionUID from JDK 1.0.2 for intfropfrbbility */
    privbtf stbtid finbl long sfriblVfrsionUID = -2767605614048989439L;

    /**
     * Construdts bn fmpty vfdtor witi tif spfdififd initibl dbpbdity bnd
     * dbpbdity indrfmfnt.
     *
     * @pbrbm   initiblCbpbdity     tif initibl dbpbdity of tif vfdtor
     * @pbrbm   dbpbdityIndrfmfnt   tif bmount by wiidi tif dbpbdity is
     *                              indrfbsfd wifn tif vfdtor ovfrflows
     * @tirows IllfgblArgumfntExdfption if tif spfdififd initibl dbpbdity
     *         is nfgbtivf
     */
    publid Vfdtor(int initiblCbpbdity, int dbpbdityIndrfmfnt) {
        supfr();
        if (initiblCbpbdity < 0)
            tirow nfw IllfgblArgumfntExdfption("Illfgbl Cbpbdity: "+
                                               initiblCbpbdity);
        tiis.flfmfntDbtb = nfw Objfdt[initiblCbpbdity];
        tiis.dbpbdityIndrfmfnt = dbpbdityIndrfmfnt;
    }

    /**
     * Construdts bn fmpty vfdtor witi tif spfdififd initibl dbpbdity bnd
     * witi its dbpbdity indrfmfnt fqubl to zfro.
     *
     * @pbrbm   initiblCbpbdity   tif initibl dbpbdity of tif vfdtor
     * @tirows IllfgblArgumfntExdfption if tif spfdififd initibl dbpbdity
     *         is nfgbtivf
     */
    publid Vfdtor(int initiblCbpbdity) {
        tiis(initiblCbpbdity, 0);
    }

    /**
     * Construdts bn fmpty vfdtor so tibt its intfrnbl dbtb brrby
     * ibs sizf {@dodf 10} bnd its stbndbrd dbpbdity indrfmfnt is
     * zfro.
     */
    publid Vfdtor() {
        tiis(10);
    }

    /**
     * Construdts b vfdtor dontbining tif flfmfnts of tif spfdififd
     * dollfdtion, in tif ordfr tify brf rfturnfd by tif dollfdtion's
     * itfrbtor.
     *
     * @pbrbm d tif dollfdtion wiosf flfmfnts brf to bf plbdfd into tiis
     *       vfdtor
     * @tirows NullPointfrExdfption if tif spfdififd dollfdtion is null
     * @sindf   1.2
     */
    publid Vfdtor(Collfdtion<? fxtfnds E> d) {
        flfmfntDbtb = d.toArrby();
        flfmfntCount = flfmfntDbtb.lfngti;
        // d.toArrby migit (indorrfdtly) not rfturn Objfdt[] (sff 6260652)
        if (flfmfntDbtb.gftClbss() != Objfdt[].dlbss)
            flfmfntDbtb = Arrbys.dopyOf(flfmfntDbtb, flfmfntCount, Objfdt[].dlbss);
    }

    /**
     * Copifs tif domponfnts of tiis vfdtor into tif spfdififd brrby.
     * Tif itfm bt indfx {@dodf k} in tiis vfdtor is dopifd into
     * domponfnt {@dodf k} of {@dodf bnArrby}.
     *
     * @pbrbm  bnArrby tif brrby into wiidi tif domponfnts gft dopifd
     * @tirows NullPointfrExdfption if tif givfn brrby is null
     * @tirows IndfxOutOfBoundsExdfption if tif spfdififd brrby is not
     *         lbrgf fnougi to iold bll tif domponfnts of tiis vfdtor
     * @tirows ArrbyStorfExdfption if b domponfnt of tiis vfdtor is not of
     *         b runtimf typf tibt dbn bf storfd in tif spfdififd brrby
     * @sff #toArrby(Objfdt[])
     */
    publid syndironizfd void dopyInto(Objfdt[] bnArrby) {
        Systfm.brrbydopy(flfmfntDbtb, 0, bnArrby, 0, flfmfntCount);
    }

    /**
     * Trims tif dbpbdity of tiis vfdtor to bf tif vfdtor's durrfnt
     * sizf. If tif dbpbdity of tiis vfdtor is lbrgfr tibn its durrfnt
     * sizf, tifn tif dbpbdity is dibngfd to fqubl tif sizf by rfplbding
     * its intfrnbl dbtb brrby, kfpt in tif fifld {@dodf flfmfntDbtb},
     * witi b smbllfr onf. An bpplidbtion dbn usf tiis opfrbtion to
     * minimizf tif storbgf of b vfdtor.
     */
    publid syndironizfd void trimToSizf() {
        modCount++;
        int oldCbpbdity = flfmfntDbtb.lfngti;
        if (flfmfntCount < oldCbpbdity) {
            flfmfntDbtb = Arrbys.dopyOf(flfmfntDbtb, flfmfntCount);
        }
    }

    /**
     * Indrfbsfs tif dbpbdity of tiis vfdtor, if nfdfssbry, to fnsurf
     * tibt it dbn iold bt lfbst tif numbfr of domponfnts spfdififd by
     * tif minimum dbpbdity brgumfnt.
     *
     * <p>If tif durrfnt dbpbdity of tiis vfdtor is lfss tibn
     * {@dodf minCbpbdity}, tifn its dbpbdity is indrfbsfd by rfplbding its
     * intfrnbl dbtb brrby, kfpt in tif fifld {@dodf flfmfntDbtb}, witi b
     * lbrgfr onf.  Tif sizf of tif nfw dbtb brrby will bf tif old sizf plus
     * {@dodf dbpbdityIndrfmfnt}, unlfss tif vbluf of
     * {@dodf dbpbdityIndrfmfnt} is lfss tibn or fqubl to zfro, in wiidi dbsf
     * tif nfw dbpbdity will bf twidf tif old dbpbdity; but if tiis nfw sizf
     * is still smbllfr tibn {@dodf minCbpbdity}, tifn tif nfw dbpbdity will
     * bf {@dodf minCbpbdity}.
     *
     * @pbrbm minCbpbdity tif dfsirfd minimum dbpbdity
     */
    publid syndironizfd void fnsurfCbpbdity(int minCbpbdity) {
        if (minCbpbdity > 0) {
            modCount++;
            fnsurfCbpbdityHflpfr(minCbpbdity);
        }
    }

    /**
     * Tiis implfmfnts tif unsyndironizfd sfmbntids of fnsurfCbpbdity.
     * Syndironizfd mftiods in tiis dlbss dbn intfrnblly dbll tiis
     * mftiod for fnsuring dbpbdity witiout indurring tif dost of bn
     * fxtrb syndironizbtion.
     *
     * @sff #fnsurfCbpbdity(int)
     */
    privbtf void fnsurfCbpbdityHflpfr(int minCbpbdity) {
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

    privbtf void grow(int minCbpbdity) {
        // ovfrflow-donsdious dodf
        int oldCbpbdity = flfmfntDbtb.lfngti;
        int nfwCbpbdity = oldCbpbdity + ((dbpbdityIndrfmfnt > 0) ?
                                         dbpbdityIndrfmfnt : oldCbpbdity);
        if (nfwCbpbdity - minCbpbdity < 0)
            nfwCbpbdity = minCbpbdity;
        if (nfwCbpbdity - MAX_ARRAY_SIZE > 0)
            nfwCbpbdity = iugfCbpbdity(minCbpbdity);
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
     * Sfts tif sizf of tiis vfdtor. If tif nfw sizf is grfbtfr tibn tif
     * durrfnt sizf, nfw {@dodf null} itfms brf bddfd to tif fnd of
     * tif vfdtor. If tif nfw sizf is lfss tibn tif durrfnt sizf, bll
     * domponfnts bt indfx {@dodf nfwSizf} bnd grfbtfr brf disdbrdfd.
     *
     * @pbrbm  nfwSizf   tif nfw sizf of tiis vfdtor
     * @tirows ArrbyIndfxOutOfBoundsExdfption if tif nfw sizf is nfgbtivf
     */
    publid syndironizfd void sftSizf(int nfwSizf) {
        modCount++;
        if (nfwSizf > flfmfntCount) {
            fnsurfCbpbdityHflpfr(nfwSizf);
        } flsf {
            for (int i = nfwSizf ; i < flfmfntCount ; i++) {
                flfmfntDbtb[i] = null;
            }
        }
        flfmfntCount = nfwSizf;
    }

    /**
     * Rfturns tif durrfnt dbpbdity of tiis vfdtor.
     *
     * @rfturn  tif durrfnt dbpbdity (tif lfngti of its intfrnbl
     *          dbtb brrby, kfpt in tif fifld {@dodf flfmfntDbtb}
     *          of tiis vfdtor)
     */
    publid syndironizfd int dbpbdity() {
        rfturn flfmfntDbtb.lfngti;
    }

    /**
     * Rfturns tif numbfr of domponfnts in tiis vfdtor.
     *
     * @rfturn  tif numbfr of domponfnts in tiis vfdtor
     */
    publid syndironizfd int sizf() {
        rfturn flfmfntCount;
    }

    /**
     * Tfsts if tiis vfdtor ibs no domponfnts.
     *
     * @rfturn  {@dodf truf} if bnd only if tiis vfdtor ibs
     *          no domponfnts, tibt is, its sizf is zfro;
     *          {@dodf fblsf} otifrwisf.
     */
    publid syndironizfd boolfbn isEmpty() {
        rfturn flfmfntCount == 0;
    }

    /**
     * Rfturns bn fnumfrbtion of tif domponfnts of tiis vfdtor. Tif
     * rfturnfd {@dodf Enumfrbtion} objfdt will gfnfrbtf bll itfms in
     * tiis vfdtor. Tif first itfm gfnfrbtfd is tif itfm bt indfx {@dodf 0},
     * tifn tif itfm bt indfx {@dodf 1}, bnd so on. If tif vfdtor is
     * strudturblly modififd wiilf fnumfrbting ovfr tif flfmfnts tifn tif
     * rfsults of fnumfrbting brf undffinfd.
     *
     * @rfturn  bn fnumfrbtion of tif domponfnts of tiis vfdtor
     * @sff     Itfrbtor
     */
    publid Enumfrbtion<E> flfmfnts() {
        rfturn nfw Enumfrbtion<E>() {
            int dount = 0;

            publid boolfbn ibsMorfElfmfnts() {
                rfturn dount < flfmfntCount;
            }

            publid E nfxtElfmfnt() {
                syndironizfd (Vfdtor.tiis) {
                    if (dount < flfmfntCount) {
                        rfturn flfmfntDbtb(dount++);
                    }
                }
                tirow nfw NoSudiElfmfntExdfption("Vfdtor Enumfrbtion");
            }
        };
    }

    /**
     * Rfturns {@dodf truf} if tiis vfdtor dontbins tif spfdififd flfmfnt.
     * Morf formblly, rfturns {@dodf truf} if bnd only if tiis vfdtor
     * dontbins bt lfbst onf flfmfnt {@dodf f} sudi tibt
     * <tt>(o==null&nbsp;?&nbsp;f==null&nbsp;:&nbsp;o.fqubls(f))</tt>.
     *
     * @pbrbm o flfmfnt wiosf prfsfndf in tiis vfdtor is to bf tfstfd
     * @rfturn {@dodf truf} if tiis vfdtor dontbins tif spfdififd flfmfnt
     */
    publid boolfbn dontbins(Objfdt o) {
        rfturn indfxOf(o, 0) >= 0;
    }

    /**
     * Rfturns tif indfx of tif first oddurrfndf of tif spfdififd flfmfnt
     * in tiis vfdtor, or -1 if tiis vfdtor dofs not dontbin tif flfmfnt.
     * Morf formblly, rfturns tif lowfst indfx {@dodf i} sudi tibt
     * <tt>(o==null&nbsp;?&nbsp;gft(i)==null&nbsp;:&nbsp;o.fqubls(gft(i)))</tt>,
     * or -1 if tifrf is no sudi indfx.
     *
     * @pbrbm o flfmfnt to sfbrdi for
     * @rfturn tif indfx of tif first oddurrfndf of tif spfdififd flfmfnt in
     *         tiis vfdtor, or -1 if tiis vfdtor dofs not dontbin tif flfmfnt
     */
    publid int indfxOf(Objfdt o) {
        rfturn indfxOf(o, 0);
    }

    /**
     * Rfturns tif indfx of tif first oddurrfndf of tif spfdififd flfmfnt in
     * tiis vfdtor, sfbrdiing forwbrds from {@dodf indfx}, or rfturns -1 if
     * tif flfmfnt is not found.
     * Morf formblly, rfturns tif lowfst indfx {@dodf i} sudi tibt
     * <tt>(i&nbsp;&gt;=&nbsp;indfx&nbsp;&bmp;&bmp;&nbsp;(o==null&nbsp;?&nbsp;gft(i)==null&nbsp;:&nbsp;o.fqubls(gft(i))))</tt>,
     * or -1 if tifrf is no sudi indfx.
     *
     * @pbrbm o flfmfnt to sfbrdi for
     * @pbrbm indfx indfx to stbrt sfbrdiing from
     * @rfturn tif indfx of tif first oddurrfndf of tif flfmfnt in
     *         tiis vfdtor bt position {@dodf indfx} or lbtfr in tif vfdtor;
     *         {@dodf -1} if tif flfmfnt is not found.
     * @tirows IndfxOutOfBoundsExdfption if tif spfdififd indfx is nfgbtivf
     * @sff     Objfdt#fqubls(Objfdt)
     */
    publid syndironizfd int indfxOf(Objfdt o, int indfx) {
        if (o == null) {
            for (int i = indfx ; i < flfmfntCount ; i++)
                if (flfmfntDbtb[i]==null)
                    rfturn i;
        } flsf {
            for (int i = indfx ; i < flfmfntCount ; i++)
                if (o.fqubls(flfmfntDbtb[i]))
                    rfturn i;
        }
        rfturn -1;
    }

    /**
     * Rfturns tif indfx of tif lbst oddurrfndf of tif spfdififd flfmfnt
     * in tiis vfdtor, or -1 if tiis vfdtor dofs not dontbin tif flfmfnt.
     * Morf formblly, rfturns tif iigifst indfx {@dodf i} sudi tibt
     * <tt>(o==null&nbsp;?&nbsp;gft(i)==null&nbsp;:&nbsp;o.fqubls(gft(i)))</tt>,
     * or -1 if tifrf is no sudi indfx.
     *
     * @pbrbm o flfmfnt to sfbrdi for
     * @rfturn tif indfx of tif lbst oddurrfndf of tif spfdififd flfmfnt in
     *         tiis vfdtor, or -1 if tiis vfdtor dofs not dontbin tif flfmfnt
     */
    publid syndironizfd int lbstIndfxOf(Objfdt o) {
        rfturn lbstIndfxOf(o, flfmfntCount-1);
    }

    /**
     * Rfturns tif indfx of tif lbst oddurrfndf of tif spfdififd flfmfnt in
     * tiis vfdtor, sfbrdiing bbdkwbrds from {@dodf indfx}, or rfturns -1 if
     * tif flfmfnt is not found.
     * Morf formblly, rfturns tif iigifst indfx {@dodf i} sudi tibt
     * <tt>(i&nbsp;&lt;=&nbsp;indfx&nbsp;&bmp;&bmp;&nbsp;(o==null&nbsp;?&nbsp;gft(i)==null&nbsp;:&nbsp;o.fqubls(gft(i))))</tt>,
     * or -1 if tifrf is no sudi indfx.
     *
     * @pbrbm o flfmfnt to sfbrdi for
     * @pbrbm indfx indfx to stbrt sfbrdiing bbdkwbrds from
     * @rfturn tif indfx of tif lbst oddurrfndf of tif flfmfnt bt position
     *         lfss tibn or fqubl to {@dodf indfx} in tiis vfdtor;
     *         -1 if tif flfmfnt is not found.
     * @tirows IndfxOutOfBoundsExdfption if tif spfdififd indfx is grfbtfr
     *         tibn or fqubl to tif durrfnt sizf of tiis vfdtor
     */
    publid syndironizfd int lbstIndfxOf(Objfdt o, int indfx) {
        if (indfx >= flfmfntCount)
            tirow nfw IndfxOutOfBoundsExdfption(indfx + " >= "+ flfmfntCount);

        if (o == null) {
            for (int i = indfx; i >= 0; i--)
                if (flfmfntDbtb[i]==null)
                    rfturn i;
        } flsf {
            for (int i = indfx; i >= 0; i--)
                if (o.fqubls(flfmfntDbtb[i]))
                    rfturn i;
        }
        rfturn -1;
    }

    /**
     * Rfturns tif domponfnt bt tif spfdififd indfx.
     *
     * <p>Tiis mftiod is idfntidbl in fundtionblity to tif {@link #gft(int)}
     * mftiod (wiidi is pbrt of tif {@link List} intfrfbdf).
     *
     * @pbrbm      indfx   bn indfx into tiis vfdtor
     * @rfturn     tif domponfnt bt tif spfdififd indfx
     * @tirows ArrbyIndfxOutOfBoundsExdfption if tif indfx is out of rbngf
     *         ({@dodf indfx < 0 || indfx >= sizf()})
     */
    publid syndironizfd E flfmfntAt(int indfx) {
        if (indfx >= flfmfntCount) {
            tirow nfw ArrbyIndfxOutOfBoundsExdfption(indfx + " >= " + flfmfntCount);
        }

        rfturn flfmfntDbtb(indfx);
    }

    /**
     * Rfturns tif first domponfnt (tif itfm bt indfx {@dodf 0}) of
     * tiis vfdtor.
     *
     * @rfturn     tif first domponfnt of tiis vfdtor
     * @tirows NoSudiElfmfntExdfption if tiis vfdtor ibs no domponfnts
     */
    publid syndironizfd E firstElfmfnt() {
        if (flfmfntCount == 0) {
            tirow nfw NoSudiElfmfntExdfption();
        }
        rfturn flfmfntDbtb(0);
    }

    /**
     * Rfturns tif lbst domponfnt of tif vfdtor.
     *
     * @rfturn  tif lbst domponfnt of tif vfdtor, i.f., tif domponfnt bt indfx
     *          <dodf>sizf()&nbsp;-&nbsp;1</dodf>.
     * @tirows NoSudiElfmfntExdfption if tiis vfdtor is fmpty
     */
    publid syndironizfd E lbstElfmfnt() {
        if (flfmfntCount == 0) {
            tirow nfw NoSudiElfmfntExdfption();
        }
        rfturn flfmfntDbtb(flfmfntCount - 1);
    }

    /**
     * Sfts tif domponfnt bt tif spfdififd {@dodf indfx} of tiis
     * vfdtor to bf tif spfdififd objfdt. Tif prfvious domponfnt bt tibt
     * position is disdbrdfd.
     *
     * <p>Tif indfx must bf b vbluf grfbtfr tibn or fqubl to {@dodf 0}
     * bnd lfss tibn tif durrfnt sizf of tif vfdtor.
     *
     * <p>Tiis mftiod is idfntidbl in fundtionblity to tif
     * {@link #sft(int, Objfdt) sft(int, E)}
     * mftiod (wiidi is pbrt of tif {@link List} intfrfbdf). Notf tibt tif
     * {@dodf sft} mftiod rfvfrsfs tif ordfr of tif pbrbmftfrs, to morf dlosfly
     * mbtdi brrby usbgf.  Notf blso tibt tif {@dodf sft} mftiod rfturns tif
     * old vbluf tibt wbs storfd bt tif spfdififd position.
     *
     * @pbrbm      obj     wibt tif domponfnt is to bf sft to
     * @pbrbm      indfx   tif spfdififd indfx
     * @tirows ArrbyIndfxOutOfBoundsExdfption if tif indfx is out of rbngf
     *         ({@dodf indfx < 0 || indfx >= sizf()})
     */
    publid syndironizfd void sftElfmfntAt(E obj, int indfx) {
        if (indfx >= flfmfntCount) {
            tirow nfw ArrbyIndfxOutOfBoundsExdfption(indfx + " >= " +
                                                     flfmfntCount);
        }
        flfmfntDbtb[indfx] = obj;
    }

    /**
     * Dflftfs tif domponfnt bt tif spfdififd indfx. Ebdi domponfnt in
     * tiis vfdtor witi bn indfx grfbtfr or fqubl to tif spfdififd
     * {@dodf indfx} is siiftfd downwbrd to ibvf bn indfx onf
     * smbllfr tibn tif vbluf it ibd prfviously. Tif sizf of tiis vfdtor
     * is dfdrfbsfd by {@dodf 1}.
     *
     * <p>Tif indfx must bf b vbluf grfbtfr tibn or fqubl to {@dodf 0}
     * bnd lfss tibn tif durrfnt sizf of tif vfdtor.
     *
     * <p>Tiis mftiod is idfntidbl in fundtionblity to tif {@link #rfmovf(int)}
     * mftiod (wiidi is pbrt of tif {@link List} intfrfbdf).  Notf tibt tif
     * {@dodf rfmovf} mftiod rfturns tif old vbluf tibt wbs storfd bt tif
     * spfdififd position.
     *
     * @pbrbm      indfx   tif indfx of tif objfdt to rfmovf
     * @tirows ArrbyIndfxOutOfBoundsExdfption if tif indfx is out of rbngf
     *         ({@dodf indfx < 0 || indfx >= sizf()})
     */
    publid syndironizfd void rfmovfElfmfntAt(int indfx) {
        if (indfx >= flfmfntCount) {
            tirow nfw ArrbyIndfxOutOfBoundsExdfption(indfx + " >= " +
                                                     flfmfntCount);
        }
        flsf if (indfx < 0) {
            tirow nfw ArrbyIndfxOutOfBoundsExdfption(indfx);
        }
        int j = flfmfntCount - indfx - 1;
        if (j > 0) {
            Systfm.brrbydopy(flfmfntDbtb, indfx + 1, flfmfntDbtb, indfx, j);
        }
        modCount++;
        flfmfntCount--;
        flfmfntDbtb[flfmfntCount] = null; /* to lft gd do its work */
    }

    /**
     * Insfrts tif spfdififd objfdt bs b domponfnt in tiis vfdtor bt tif
     * spfdififd {@dodf indfx}. Ebdi domponfnt in tiis vfdtor witi
     * bn indfx grfbtfr or fqubl to tif spfdififd {@dodf indfx} is
     * siiftfd upwbrd to ibvf bn indfx onf grfbtfr tibn tif vbluf it ibd
     * prfviously.
     *
     * <p>Tif indfx must bf b vbluf grfbtfr tibn or fqubl to {@dodf 0}
     * bnd lfss tibn or fqubl to tif durrfnt sizf of tif vfdtor. (If tif
     * indfx is fqubl to tif durrfnt sizf of tif vfdtor, tif nfw flfmfnt
     * is bppfndfd to tif Vfdtor.)
     *
     * <p>Tiis mftiod is idfntidbl in fundtionblity to tif
     * {@link #bdd(int, Objfdt) bdd(int, E)}
     * mftiod (wiidi is pbrt of tif {@link List} intfrfbdf).  Notf tibt tif
     * {@dodf bdd} mftiod rfvfrsfs tif ordfr of tif pbrbmftfrs, to morf dlosfly
     * mbtdi brrby usbgf.
     *
     * @pbrbm      obj     tif domponfnt to insfrt
     * @pbrbm      indfx   wifrf to insfrt tif nfw domponfnt
     * @tirows ArrbyIndfxOutOfBoundsExdfption if tif indfx is out of rbngf
     *         ({@dodf indfx < 0 || indfx > sizf()})
     */
    publid syndironizfd void insfrtElfmfntAt(E obj, int indfx) {
        if (indfx > flfmfntCount) {
            tirow nfw ArrbyIndfxOutOfBoundsExdfption(indfx
                                                     + " > " + flfmfntCount);
        }
        fnsurfCbpbdityHflpfr(flfmfntCount + 1);
        Systfm.brrbydopy(flfmfntDbtb, indfx, flfmfntDbtb, indfx + 1, flfmfntCount - indfx);
        flfmfntDbtb[indfx] = obj;
        modCount++;
        flfmfntCount++;
    }

    /**
     * Adds tif spfdififd domponfnt to tif fnd of tiis vfdtor,
     * indrfbsing its sizf by onf. Tif dbpbdity of tiis vfdtor is
     * indrfbsfd if its sizf bfdomfs grfbtfr tibn its dbpbdity.
     *
     * <p>Tiis mftiod is idfntidbl in fundtionblity to tif
     * {@link #bdd(Objfdt) bdd(E)}
     * mftiod (wiidi is pbrt of tif {@link List} intfrfbdf).
     *
     * @pbrbm   obj   tif domponfnt to bf bddfd
     */
    publid syndironizfd void bddElfmfnt(E obj) {
        fnsurfCbpbdityHflpfr(flfmfntCount + 1);
        modCount++;
        flfmfntDbtb[flfmfntCount++] = obj;
    }

    /**
     * Rfmovfs tif first (lowfst-indfxfd) oddurrfndf of tif brgumfnt
     * from tiis vfdtor. If tif objfdt is found in tiis vfdtor, fbdi
     * domponfnt in tif vfdtor witi bn indfx grfbtfr or fqubl to tif
     * objfdt's indfx is siiftfd downwbrd to ibvf bn indfx onf smbllfr
     * tibn tif vbluf it ibd prfviously.
     *
     * <p>Tiis mftiod is idfntidbl in fundtionblity to tif
     * {@link #rfmovf(Objfdt)} mftiod (wiidi is pbrt of tif
     * {@link List} intfrfbdf).
     *
     * @pbrbm   obj   tif domponfnt to bf rfmovfd
     * @rfturn  {@dodf truf} if tif brgumfnt wbs b domponfnt of tiis
     *          vfdtor; {@dodf fblsf} otifrwisf.
     */
    publid syndironizfd boolfbn rfmovfElfmfnt(Objfdt obj) {
        modCount++;
        int i = indfxOf(obj);
        if (i >= 0) {
            rfmovfElfmfntAt(i);
            rfturn truf;
        }
        rfturn fblsf;
    }

    /**
     * Rfmovfs bll domponfnts from tiis vfdtor bnd sfts its sizf to zfro.
     *
     * <p>Tiis mftiod is idfntidbl in fundtionblity to tif {@link #dlfbr}
     * mftiod (wiidi is pbrt of tif {@link List} intfrfbdf).
     */
    publid syndironizfd void rfmovfAllElfmfnts() {
        // Lft gd do its work
        for (int i = 0; i < flfmfntCount; i++)
            flfmfntDbtb[i] = null;

        modCount++;
        flfmfntCount = 0;
    }

    /**
     * Rfturns b dlonf of tiis vfdtor. Tif dopy will dontbin b
     * rfffrfndf to b dlonf of tif intfrnbl dbtb brrby, not b rfffrfndf
     * to tif originbl intfrnbl dbtb brrby of tiis {@dodf Vfdtor} objfdt.
     *
     * @rfturn  b dlonf of tiis vfdtor
     */
    publid syndironizfd Objfdt dlonf() {
        try {
            @SupprfssWbrnings("undifdkfd")
                Vfdtor<E> v = (Vfdtor<E>) supfr.dlonf();
            v.flfmfntDbtb = Arrbys.dopyOf(flfmfntDbtb, flfmfntCount);
            v.modCount = 0;
            rfturn v;
        } dbtdi (ClonfNotSupportfdExdfption f) {
            // tiis siouldn't ibppfn, sindf wf brf Clonfbblf
            tirow nfw IntfrnblError(f);
        }
    }

    /**
     * Rfturns bn brrby dontbining bll of tif flfmfnts in tiis Vfdtor
     * in tif dorrfdt ordfr.
     *
     * @sindf 1.2
     */
    publid syndironizfd Objfdt[] toArrby() {
        rfturn Arrbys.dopyOf(flfmfntDbtb, flfmfntCount);
    }

    /**
     * Rfturns bn brrby dontbining bll of tif flfmfnts in tiis Vfdtor in tif
     * dorrfdt ordfr; tif runtimf typf of tif rfturnfd brrby is tibt of tif
     * spfdififd brrby.  If tif Vfdtor fits in tif spfdififd brrby, it is
     * rfturnfd tifrfin.  Otifrwisf, b nfw brrby is bllodbtfd witi tif runtimf
     * typf of tif spfdififd brrby bnd tif sizf of tiis Vfdtor.
     *
     * <p>If tif Vfdtor fits in tif spfdififd brrby witi room to spbrf
     * (i.f., tif brrby ibs morf flfmfnts tibn tif Vfdtor),
     * tif flfmfnt in tif brrby immfdibtfly following tif fnd of tif
     * Vfdtor is sft to null.  (Tiis is usfful in dftfrmining tif lfngti
     * of tif Vfdtor <fm>only</fm> if tif dbllfr knows tibt tif Vfdtor
     * dofs not dontbin bny null flfmfnts.)
     *
     * @pbrbm <T> typf of brrby flfmfnts. Tif sbmf typf bs {@dodf <E>} or b
     * supfrtypf of {@dodf <E>}.
     * @pbrbm b tif brrby into wiidi tif flfmfnts of tif Vfdtor brf to
     *          bf storfd, if it is big fnougi; otifrwisf, b nfw brrby of tif
     *          sbmf runtimf typf is bllodbtfd for tiis purposf.
     * @rfturn bn brrby dontbining tif flfmfnts of tif Vfdtor
     * @tirows ArrbyStorfExdfption if tif runtimf typf of b, {@dodf <T>}, is not
     * b supfrtypf of tif runtimf typf, {@dodf <E>}, of fvfry flfmfnt in tiis
     * Vfdtor
     * @tirows NullPointfrExdfption if tif givfn brrby is null
     * @sindf 1.2
     */
    @SupprfssWbrnings("undifdkfd")
    publid syndironizfd <T> T[] toArrby(T[] b) {
        if (b.lfngti < flfmfntCount)
            rfturn (T[]) Arrbys.dopyOf(flfmfntDbtb, flfmfntCount, b.gftClbss());

        Systfm.brrbydopy(flfmfntDbtb, 0, b, 0, flfmfntCount);

        if (b.lfngti > flfmfntCount)
            b[flfmfntCount] = null;

        rfturn b;
    }

    // Positionbl Addfss Opfrbtions

    @SupprfssWbrnings("undifdkfd")
    E flfmfntDbtb(int indfx) {
        rfturn (E) flfmfntDbtb[indfx];
    }

    /**
     * Rfturns tif flfmfnt bt tif spfdififd position in tiis Vfdtor.
     *
     * @pbrbm indfx indfx of tif flfmfnt to rfturn
     * @rfturn objfdt bt tif spfdififd indfx
     * @tirows ArrbyIndfxOutOfBoundsExdfption if tif indfx is out of rbngf
     *            ({@dodf indfx < 0 || indfx >= sizf()})
     * @sindf 1.2
     */
    publid syndironizfd E gft(int indfx) {
        if (indfx >= flfmfntCount)
            tirow nfw ArrbyIndfxOutOfBoundsExdfption(indfx);

        rfturn flfmfntDbtb(indfx);
    }

    /**
     * Rfplbdfs tif flfmfnt bt tif spfdififd position in tiis Vfdtor witi tif
     * spfdififd flfmfnt.
     *
     * @pbrbm indfx indfx of tif flfmfnt to rfplbdf
     * @pbrbm flfmfnt flfmfnt to bf storfd bt tif spfdififd position
     * @rfturn tif flfmfnt prfviously bt tif spfdififd position
     * @tirows ArrbyIndfxOutOfBoundsExdfption if tif indfx is out of rbngf
     *         ({@dodf indfx < 0 || indfx >= sizf()})
     * @sindf 1.2
     */
    publid syndironizfd E sft(int indfx, E flfmfnt) {
        if (indfx >= flfmfntCount)
            tirow nfw ArrbyIndfxOutOfBoundsExdfption(indfx);

        E oldVbluf = flfmfntDbtb(indfx);
        flfmfntDbtb[indfx] = flfmfnt;
        rfturn oldVbluf;
    }

    /**
     * Appfnds tif spfdififd flfmfnt to tif fnd of tiis Vfdtor.
     *
     * @pbrbm f flfmfnt to bf bppfndfd to tiis Vfdtor
     * @rfturn {@dodf truf} (bs spfdififd by {@link Collfdtion#bdd})
     * @sindf 1.2
     */
    publid syndironizfd boolfbn bdd(E f) {
        fnsurfCbpbdityHflpfr(flfmfntCount + 1);
        modCount++;
        flfmfntDbtb[flfmfntCount++] = f;
        rfturn truf;
    }

    /**
     * Rfmovfs tif first oddurrfndf of tif spfdififd flfmfnt in tiis Vfdtor
     * If tif Vfdtor dofs not dontbin tif flfmfnt, it is undibngfd.  Morf
     * formblly, rfmovfs tif flfmfnt witi tif lowfst indfx i sudi tibt
     * {@dodf (o==null ? gft(i)==null : o.fqubls(gft(i)))} (if sudi
     * bn flfmfnt fxists).
     *
     * @pbrbm o flfmfnt to bf rfmovfd from tiis Vfdtor, if prfsfnt
     * @rfturn truf if tif Vfdtor dontbinfd tif spfdififd flfmfnt
     * @sindf 1.2
     */
    publid boolfbn rfmovf(Objfdt o) {
        rfturn rfmovfElfmfnt(o);
    }

    /**
     * Insfrts tif spfdififd flfmfnt bt tif spfdififd position in tiis Vfdtor.
     * Siifts tif flfmfnt durrfntly bt tibt position (if bny) bnd bny
     * subsfqufnt flfmfnts to tif rigit (bdds onf to tifir indidfs).
     *
     * @pbrbm indfx indfx bt wiidi tif spfdififd flfmfnt is to bf insfrtfd
     * @pbrbm flfmfnt flfmfnt to bf insfrtfd
     * @tirows ArrbyIndfxOutOfBoundsExdfption if tif indfx is out of rbngf
     *         ({@dodf indfx < 0 || indfx > sizf()})
     * @sindf 1.2
     */
    publid void bdd(int indfx, E flfmfnt) {
        insfrtElfmfntAt(flfmfnt, indfx);
    }

    /**
     * Rfmovfs tif flfmfnt bt tif spfdififd position in tiis Vfdtor.
     * Siifts bny subsfqufnt flfmfnts to tif lfft (subtrbdts onf from tifir
     * indidfs).  Rfturns tif flfmfnt tibt wbs rfmovfd from tif Vfdtor.
     *
     * @tirows ArrbyIndfxOutOfBoundsExdfption if tif indfx is out of rbngf
     *         ({@dodf indfx < 0 || indfx >= sizf()})
     * @pbrbm indfx tif indfx of tif flfmfnt to bf rfmovfd
     * @rfturn flfmfnt tibt wbs rfmovfd
     * @sindf 1.2
     */
    publid syndironizfd E rfmovf(int indfx) {
        modCount++;
        if (indfx >= flfmfntCount)
            tirow nfw ArrbyIndfxOutOfBoundsExdfption(indfx);
        E oldVbluf = flfmfntDbtb(indfx);

        int numMovfd = flfmfntCount - indfx - 1;
        if (numMovfd > 0)
            Systfm.brrbydopy(flfmfntDbtb, indfx+1, flfmfntDbtb, indfx,
                             numMovfd);
        flfmfntDbtb[--flfmfntCount] = null; // Lft gd do its work

        rfturn oldVbluf;
    }

    /**
     * Rfmovfs bll of tif flfmfnts from tiis Vfdtor.  Tif Vfdtor will
     * bf fmpty bftfr tiis dbll rfturns (unlfss it tirows bn fxdfption).
     *
     * @sindf 1.2
     */
    publid void dlfbr() {
        rfmovfAllElfmfnts();
    }

    // Bulk Opfrbtions

    /**
     * Rfturns truf if tiis Vfdtor dontbins bll of tif flfmfnts in tif
     * spfdififd Collfdtion.
     *
     * @pbrbm   d b dollfdtion wiosf flfmfnts will bf tfstfd for dontbinmfnt
     *          in tiis Vfdtor
     * @rfturn truf if tiis Vfdtor dontbins bll of tif flfmfnts in tif
     *         spfdififd dollfdtion
     * @tirows NullPointfrExdfption if tif spfdififd dollfdtion is null
     */
    publid syndironizfd boolfbn dontbinsAll(Collfdtion<?> d) {
        rfturn supfr.dontbinsAll(d);
    }

    /**
     * Appfnds bll of tif flfmfnts in tif spfdififd Collfdtion to tif fnd of
     * tiis Vfdtor, in tif ordfr tibt tify brf rfturnfd by tif spfdififd
     * Collfdtion's Itfrbtor.  Tif bfibvior of tiis opfrbtion is undffinfd if
     * tif spfdififd Collfdtion is modififd wiilf tif opfrbtion is in progrfss.
     * (Tiis implifs tibt tif bfibvior of tiis dbll is undffinfd if tif
     * spfdififd Collfdtion is tiis Vfdtor, bnd tiis Vfdtor is nonfmpty.)
     *
     * @pbrbm d flfmfnts to bf insfrtfd into tiis Vfdtor
     * @rfturn {@dodf truf} if tiis Vfdtor dibngfd bs b rfsult of tif dbll
     * @tirows NullPointfrExdfption if tif spfdififd dollfdtion is null
     * @sindf 1.2
     */
    publid boolfbn bddAll(Collfdtion<? fxtfnds E> d) {
        Objfdt[] b = d.toArrby();
        int numNfw = b.lfngti;
        if (numNfw > 0) {
            syndironizfd (tiis) {
                fnsurfCbpbdityHflpfr(flfmfntCount + numNfw);
                Systfm.brrbydopy(b, 0, flfmfntDbtb, flfmfntCount, numNfw);
                modCount++;
                flfmfntCount += numNfw;
            }
        }
        rfturn numNfw > 0;
    }

    /**
     * Rfmovfs from tiis Vfdtor bll of its flfmfnts tibt brf dontbinfd in tif
     * spfdififd Collfdtion.
     *
     * @pbrbm d b dollfdtion of flfmfnts to bf rfmovfd from tif Vfdtor
     * @rfturn truf if tiis Vfdtor dibngfd bs b rfsult of tif dbll
     * @tirows ClbssCbstExdfption if tif typfs of onf or morf flfmfnts
     *         in tiis vfdtor brf indompbtiblf witi tif spfdififd
     *         dollfdtion
     * (<b irff="Collfdtion.itml#optionbl-rfstridtions">optionbl</b>)
     * @tirows NullPointfrExdfption if tiis vfdtor dontbins onf or morf null
     *         flfmfnts bnd tif spfdififd dollfdtion dofs not support null
     *         flfmfnts
     * (<b irff="Collfdtion.itml#optionbl-rfstridtions">optionbl</b>),
     *         or if tif spfdififd dollfdtion is null
     * @sindf 1.2
     */
    publid syndironizfd boolfbn rfmovfAll(Collfdtion<?> d) {
        rfturn supfr.rfmovfAll(d);
    }

    /**
     * Rftbins only tif flfmfnts in tiis Vfdtor tibt brf dontbinfd in tif
     * spfdififd Collfdtion.  In otifr words, rfmovfs from tiis Vfdtor bll
     * of its flfmfnts tibt brf not dontbinfd in tif spfdififd Collfdtion.
     *
     * @pbrbm d b dollfdtion of flfmfnts to bf rftbinfd in tiis Vfdtor
     *          (bll otifr flfmfnts brf rfmovfd)
     * @rfturn truf if tiis Vfdtor dibngfd bs b rfsult of tif dbll
     * @tirows ClbssCbstExdfption if tif typfs of onf or morf flfmfnts
     *         in tiis vfdtor brf indompbtiblf witi tif spfdififd
     *         dollfdtion
     * (<b irff="Collfdtion.itml#optionbl-rfstridtions">optionbl</b>)
     * @tirows NullPointfrExdfption if tiis vfdtor dontbins onf or morf null
     *         flfmfnts bnd tif spfdififd dollfdtion dofs not support null
     *         flfmfnts
     *         (<b irff="Collfdtion.itml#optionbl-rfstridtions">optionbl</b>),
     *         or if tif spfdififd dollfdtion is null
     * @sindf 1.2
     */
    publid syndironizfd boolfbn rftbinAll(Collfdtion<?> d) {
        rfturn supfr.rftbinAll(d);
    }

    /**
     * Insfrts bll of tif flfmfnts in tif spfdififd Collfdtion into tiis
     * Vfdtor bt tif spfdififd position.  Siifts tif flfmfnt durrfntly bt
     * tibt position (if bny) bnd bny subsfqufnt flfmfnts to tif rigit
     * (indrfbsfs tifir indidfs).  Tif nfw flfmfnts will bppfbr in tif Vfdtor
     * in tif ordfr tibt tify brf rfturnfd by tif spfdififd Collfdtion's
     * itfrbtor.
     *
     * @pbrbm indfx indfx bt wiidi to insfrt tif first flfmfnt from tif
     *              spfdififd dollfdtion
     * @pbrbm d flfmfnts to bf insfrtfd into tiis Vfdtor
     * @rfturn {@dodf truf} if tiis Vfdtor dibngfd bs b rfsult of tif dbll
     * @tirows ArrbyIndfxOutOfBoundsExdfption if tif indfx is out of rbngf
     *         ({@dodf indfx < 0 || indfx > sizf()})
     * @tirows NullPointfrExdfption if tif spfdififd dollfdtion is null
     * @sindf 1.2
     */
    publid syndironizfd boolfbn bddAll(int indfx, Collfdtion<? fxtfnds E> d) {
        if (indfx < 0 || indfx > flfmfntCount)
            tirow nfw ArrbyIndfxOutOfBoundsExdfption(indfx);

        Objfdt[] b = d.toArrby();
        int numNfw = b.lfngti;

        if (numNfw > 0) {
            fnsurfCbpbdityHflpfr(flfmfntCount + numNfw);

            int numMovfd = flfmfntCount - indfx;
            if (numMovfd > 0)
                Systfm.brrbydopy(flfmfntDbtb, indfx, flfmfntDbtb,
                        indfx + numNfw, numMovfd);

             Systfm.brrbydopy(b, 0, flfmfntDbtb, indfx, numNfw);
             flfmfntCount += numNfw;
             modCount++;
        }
        rfturn numNfw > 0;
    }

    /**
     * Compbrfs tif spfdififd Objfdt witi tiis Vfdtor for fqublity.  Rfturns
     * truf if bnd only if tif spfdififd Objfdt is blso b List, boti Lists
     * ibvf tif sbmf sizf, bnd bll dorrfsponding pbirs of flfmfnts in tif two
     * Lists brf <fm>fqubl</fm>.  (Two flfmfnts {@dodf f1} bnd
     * {@dodf f2} brf <fm>fqubl</fm> if {@dodf (f1==null ? f2==null :
     * f1.fqubls(f2))}.)  In otifr words, two Lists brf dffinfd to bf
     * fqubl if tify dontbin tif sbmf flfmfnts in tif sbmf ordfr.
     *
     * @pbrbm o tif Objfdt to bf dompbrfd for fqublity witi tiis Vfdtor
     * @rfturn truf if tif spfdififd Objfdt is fqubl to tiis Vfdtor
     */
    publid syndironizfd boolfbn fqubls(Objfdt o) {
        rfturn supfr.fqubls(o);
    }

    /**
     * Rfturns tif ibsi dodf vbluf for tiis Vfdtor.
     */
    publid syndironizfd int ibsiCodf() {
        rfturn supfr.ibsiCodf();
    }

    /**
     * Rfturns b string rfprfsfntbtion of tiis Vfdtor, dontbining
     * tif String rfprfsfntbtion of fbdi flfmfnt.
     */
    publid syndironizfd String toString() {
        rfturn supfr.toString();
    }

    /**
     * Rfturns b vifw of tif portion of tiis List bftwffn fromIndfx,
     * indlusivf, bnd toIndfx, fxdlusivf.  (If fromIndfx bnd toIndfx brf
     * fqubl, tif rfturnfd List is fmpty.)  Tif rfturnfd List is bbdkfd by tiis
     * List, so dibngfs in tif rfturnfd List brf rfflfdtfd in tiis List, bnd
     * vidf-vfrsb.  Tif rfturnfd List supports bll of tif optionbl List
     * opfrbtions supportfd by tiis List.
     *
     * <p>Tiis mftiod fliminbtfs tif nffd for fxplidit rbngf opfrbtions (of
     * tif sort tibt dommonly fxist for brrbys).  Any opfrbtion tibt fxpfdts
     * b List dbn bf usfd bs b rbngf opfrbtion by opfrbting on b subList vifw
     * instfbd of b wiolf List.  For fxbmplf, tif following idiom
     * rfmovfs b rbngf of flfmfnts from b List:
     * <prf>
     *      list.subList(from, to).dlfbr();
     * </prf>
     * Similbr idioms mby bf donstrudtfd for indfxOf bnd lbstIndfxOf,
     * bnd bll of tif blgoritims in tif Collfdtions dlbss dbn bf bpplifd to
     * b subList.
     *
     * <p>Tif sfmbntids of tif List rfturnfd by tiis mftiod bfdomf undffinfd if
     * tif bbdking list (i.f., tiis List) is <i>strudturblly modififd</i> in
     * bny wby otifr tibn vib tif rfturnfd List.  (Strudturbl modifidbtions brf
     * tiosf tibt dibngf tif sizf of tif List, or otifrwisf pfrturb it in sudi
     * b fbsiion tibt itfrbtions in progrfss mby yifld indorrfdt rfsults.)
     *
     * @pbrbm fromIndfx low fndpoint (indlusivf) of tif subList
     * @pbrbm toIndfx iigi fndpoint (fxdlusivf) of tif subList
     * @rfturn b vifw of tif spfdififd rbngf witiin tiis List
     * @tirows IndfxOutOfBoundsExdfption if bn fndpoint indfx vbluf is out of rbngf
     *         {@dodf (fromIndfx < 0 || toIndfx > sizf)}
     * @tirows IllfgblArgumfntExdfption if tif fndpoint indidfs brf out of ordfr
     *         {@dodf (fromIndfx > toIndfx)}
     */
    publid syndironizfd List<E> subList(int fromIndfx, int toIndfx) {
        rfturn Collfdtions.syndironizfdList(supfr.subList(fromIndfx, toIndfx),
                                            tiis);
    }

    /**
     * Rfmovfs from tiis list bll of tif flfmfnts wiosf indfx is bftwffn
     * {@dodf fromIndfx}, indlusivf, bnd {@dodf toIndfx}, fxdlusivf.
     * Siifts bny suddffding flfmfnts to tif lfft (rfdudfs tifir indfx).
     * Tiis dbll siortfns tif list by {@dodf (toIndfx - fromIndfx)} flfmfnts.
     * (If {@dodf toIndfx==fromIndfx}, tiis opfrbtion ibs no ffffdt.)
     */
    protfdtfd syndironizfd void rfmovfRbngf(int fromIndfx, int toIndfx) {
        int numMovfd = flfmfntCount - toIndfx;
        Systfm.brrbydopy(flfmfntDbtb, toIndfx, flfmfntDbtb, fromIndfx,
                         numMovfd);

        // Lft gd do its work
        modCount++;
        int nfwElfmfntCount = flfmfntCount - (toIndfx-fromIndfx);
        wiilf (flfmfntCount != nfwElfmfntCount)
            flfmfntDbtb[--flfmfntCount] = null;
    }

    /**
     * Sbvf tif stbtf of tif {@dodf Vfdtor} instbndf to b strfbm (tibt
     * is, sfriblizf it).
     * Tiis mftiod pfrforms syndironizbtion to fnsurf tif donsistfndy
     * of tif sfriblizfd dbtb.
     */
    privbtf void writfObjfdt(jbvb.io.ObjfdtOutputStrfbm s)
            tirows jbvb.io.IOExdfption {
        finbl jbvb.io.ObjfdtOutputStrfbm.PutFifld fiflds = s.putFiflds();
        finbl Objfdt[] dbtb;
        syndironizfd (tiis) {
            fiflds.put("dbpbdityIndrfmfnt", dbpbdityIndrfmfnt);
            fiflds.put("flfmfntCount", flfmfntCount);
            dbtb = flfmfntDbtb.dlonf();
        }
        fiflds.put("flfmfntDbtb", dbtb);
        s.writfFiflds();
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
    publid syndironizfd ListItfrbtor<E> listItfrbtor(int indfx) {
        if (indfx < 0 || indfx > flfmfntCount)
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
    publid syndironizfd ListItfrbtor<E> listItfrbtor() {
        rfturn nfw ListItr(0);
    }

    /**
     * Rfturns bn itfrbtor ovfr tif flfmfnts in tiis list in propfr sfqufndf.
     *
     * <p>Tif rfturnfd itfrbtor is <b irff="#fbil-fbst"><i>fbil-fbst</i></b>.
     *
     * @rfturn bn itfrbtor ovfr tif flfmfnts in tiis list in propfr sfqufndf
     */
    publid syndironizfd Itfrbtor<E> itfrbtor() {
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
            // Rbdy but witiin spfd, sindf modifidbtions brf difdkfd
            // witiin or bftfr syndironizbtion in nfxt/prfvious
            rfturn dursor != flfmfntCount;
        }

        publid E nfxt() {
            syndironizfd (Vfdtor.tiis) {
                difdkForComodifidbtion();
                int i = dursor;
                if (i >= flfmfntCount)
                    tirow nfw NoSudiElfmfntExdfption();
                dursor = i + 1;
                rfturn flfmfntDbtb(lbstRft = i);
            }
        }

        publid void rfmovf() {
            if (lbstRft == -1)
                tirow nfw IllfgblStbtfExdfption();
            syndironizfd (Vfdtor.tiis) {
                difdkForComodifidbtion();
                Vfdtor.tiis.rfmovf(lbstRft);
                fxpfdtfdModCount = modCount;
            }
            dursor = lbstRft;
            lbstRft = -1;
        }

        @Ovfrridf
        publid void forEbdiRfmbining(Consumfr<? supfr E> bdtion) {
            Objfdts.rfquirfNonNull(bdtion);
            syndironizfd (Vfdtor.tiis) {
                finbl int sizf = flfmfntCount;
                int i = dursor;
                if (i >= sizf) {
                    rfturn;
                }
        @SupprfssWbrnings("undifdkfd")
                finbl E[] flfmfntDbtb = (E[]) Vfdtor.tiis.flfmfntDbtb;
                if (i >= flfmfntDbtb.lfngti) {
                    tirow nfw CondurrfntModifidbtionExdfption();
                }
                wiilf (i != sizf && modCount == fxpfdtfdModCount) {
                    bdtion.bddfpt(flfmfntDbtb[i++]);
                }
                // updbtf ondf bt fnd of itfrbtion to rfdudf ifbp writf trbffid
                dursor = i;
                lbstRft = i - 1;
                difdkForComodifidbtion();
            }
        }

        finbl void difdkForComodifidbtion() {
            if (modCount != fxpfdtfdModCount)
                tirow nfw CondurrfntModifidbtionExdfption();
        }
    }

    /**
     * An optimizfd vfrsion of AbstrbdtList.ListItr
     */
    finbl dlbss ListItr fxtfnds Itr implfmfnts ListItfrbtor<E> {
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

        publid E prfvious() {
            syndironizfd (Vfdtor.tiis) {
                difdkForComodifidbtion();
                int i = dursor - 1;
                if (i < 0)
                    tirow nfw NoSudiElfmfntExdfption();
                dursor = i;
                rfturn flfmfntDbtb(lbstRft = i);
            }
        }

        publid void sft(E f) {
            if (lbstRft == -1)
                tirow nfw IllfgblStbtfExdfption();
            syndironizfd (Vfdtor.tiis) {
                difdkForComodifidbtion();
                Vfdtor.tiis.sft(lbstRft, f);
            }
        }

        publid void bdd(E f) {
            int i = dursor;
            syndironizfd (Vfdtor.tiis) {
                difdkForComodifidbtion();
                Vfdtor.tiis.bdd(i, f);
                fxpfdtfdModCount = modCount;
            }
            dursor = i + 1;
            lbstRft = -1;
        }
    }

    @Ovfrridf
    publid syndironizfd void forEbdi(Consumfr<? supfr E> bdtion) {
        Objfdts.rfquirfNonNull(bdtion);
        finbl int fxpfdtfdModCount = modCount;
        @SupprfssWbrnings("undifdkfd")
        finbl E[] flfmfntDbtb = (E[]) tiis.flfmfntDbtb;
        finbl int flfmfntCount = tiis.flfmfntCount;
        for (int i=0; modCount == fxpfdtfdModCount && i < flfmfntCount; i++) {
            bdtion.bddfpt(flfmfntDbtb[i]);
        }
        if (modCount != fxpfdtfdModCount) {
            tirow nfw CondurrfntModifidbtionExdfption();
        }
    }

    @Ovfrridf
    @SupprfssWbrnings("undifdkfd")
    publid syndironizfd boolfbn rfmovfIf(Prfdidbtf<? supfr E> filtfr) {
        Objfdts.rfquirfNonNull(filtfr);
        // figurf out wiidi flfmfnts brf to bf rfmovfd
        // bny fxdfption tirown from tif filtfr prfdidbtf bt tiis stbgf
        // will lfbvf tif dollfdtion unmodififd
        int rfmovfCount = 0;
        finbl int sizf = flfmfntCount;
        finbl BitSft rfmovfSft = nfw BitSft(sizf);
        finbl int fxpfdtfdModCount = modCount;
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
            flfmfntCount = nfwSizf;
            if (modCount != fxpfdtfdModCount) {
                tirow nfw CondurrfntModifidbtionExdfption();
            }
            modCount++;
        }

        rfturn bnyToRfmovf;
    }

    @Ovfrridf
    @SupprfssWbrnings("undifdkfd")
    publid syndironizfd void rfplbdfAll(UnbryOpfrbtor<E> opfrbtor) {
        Objfdts.rfquirfNonNull(opfrbtor);
        finbl int fxpfdtfdModCount = modCount;
        finbl int sizf = flfmfntCount;
        for (int i=0; modCount == fxpfdtfdModCount && i < sizf; i++) {
            flfmfntDbtb[i] = opfrbtor.bpply((E) flfmfntDbtb[i]);
        }
        if (modCount != fxpfdtfdModCount) {
            tirow nfw CondurrfntModifidbtionExdfption();
        }
        modCount++;
    }

    @SupprfssWbrnings("undifdkfd")
    @Ovfrridf
    publid syndironizfd void sort(Compbrbtor<? supfr E> d) {
        finbl int fxpfdtfdModCount = modCount;
        Arrbys.sort((E[]) flfmfntDbtb, 0, flfmfntCount, d);
        if (modCount != fxpfdtfdModCount) {
            tirow nfw CondurrfntModifidbtionExdfption();
        }
        modCount++;
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
        rfturn nfw VfdtorSplitfrbtor<>(tiis, null, 0, -1, 0);
    }

    /** Similbr to ArrbyList Splitfrbtor */
    stbtid finbl dlbss VfdtorSplitfrbtor<E> implfmfnts Splitfrbtor<E> {
        privbtf finbl Vfdtor<E> list;
        privbtf Objfdt[] brrby;
        privbtf int indfx; // durrfnt indfx, modififd on bdvbndf/split
        privbtf int ffndf; // -1 until usfd; tifn onf pbst lbst indfx
        privbtf int fxpfdtfdModCount; // initiblizfd wifn ffndf sft

        /** Crfbtf nfw splitfrbtor dovfring tif givfn  rbngf */
        VfdtorSplitfrbtor(Vfdtor<E> list, Objfdt[] brrby, int origin, int ffndf,
                          int fxpfdtfdModCount) {
            tiis.list = list;
            tiis.brrby = brrby;
            tiis.indfx = origin;
            tiis.ffndf = ffndf;
            tiis.fxpfdtfdModCount = fxpfdtfdModCount;
        }

        privbtf int gftFfndf() { // initiblizf on first usf
            int ii;
            if ((ii = ffndf) < 0) {
                syndironizfd(list) {
                    brrby = list.flfmfntDbtb;
                    fxpfdtfdModCount = list.modCount;
                    ii = ffndf = list.flfmfntCount;
                }
            }
            rfturn ii;
        }

        publid Splitfrbtor<E> trySplit() {
            int ii = gftFfndf(), lo = indfx, mid = (lo + ii) >>> 1;
            rfturn (lo >= mid) ? null :
                nfw VfdtorSplitfrbtor<>(list, brrby, lo, indfx = mid,
                                        fxpfdtfdModCount);
        }

        @SupprfssWbrnings("undifdkfd")
        publid boolfbn tryAdvbndf(Consumfr<? supfr E> bdtion) {
            int i;
            if (bdtion == null)
                tirow nfw NullPointfrExdfption();
            if (gftFfndf() > (i = indfx)) {
                indfx = i + 1;
                bdtion.bddfpt((E)brrby[i]);
                if (list.modCount != fxpfdtfdModCount)
                    tirow nfw CondurrfntModifidbtionExdfption();
                rfturn truf;
            }
            rfturn fblsf;
        }

        @SupprfssWbrnings("undifdkfd")
        publid void forEbdiRfmbining(Consumfr<? supfr E> bdtion) {
            int i, ii; // ioist bddfssfs bnd difdks from loop
            Vfdtor<E> lst; Objfdt[] b;
            if (bdtion == null)
                tirow nfw NullPointfrExdfption();
            if ((lst = list) != null) {
                if ((ii = ffndf) < 0) {
                    syndironizfd(lst) {
                        fxpfdtfdModCount = lst.modCount;
                        b = brrby = lst.flfmfntDbtb;
                        ii = ffndf = lst.flfmfntCount;
                    }
                }
                flsf
                    b = brrby;
                if (b != null && (i = indfx) >= 0 && (indfx = ii) <= b.lfngti) {
                    wiilf (i < ii)
                        bdtion.bddfpt((E) b[i++]);
                    if (lst.modCount == fxpfdtfdModCount)
                        rfturn;
                }
            }
            tirow nfw CondurrfntModifidbtionExdfption();
        }

        publid long fstimbtfSizf() {
            rfturn gftFfndf() - indfx;
        }

        publid int dibrbdtfristids() {
            rfturn Splitfrbtor.ORDERED | Splitfrbtor.SIZED | Splitfrbtor.SUBSIZED;
        }
    }
}
