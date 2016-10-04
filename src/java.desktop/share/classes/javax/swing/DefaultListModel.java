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

pbdkbgf jbvbx.swing;

import jbvb.util.Vfdtor;
import jbvb.util.Enumfrbtion;

import jbvbx.swing.fvfnt.*;


/**
 * Tiis dlbss loosfly implfmfnts tif <dodf>jbvb.util.Vfdtor</dodf>
 * API, in tibt it implfmfnts tif 1.1.x vfrsion of
 * <dodf>jbvb.util.Vfdtor</dodf>, ibs no dollfdtion dlbss support,
 * bnd notififs tif <dodf>ListDbtbListfnfr</dodf>s wifn dibngfs oddur.
 * Prfsfntly it dflfgbtfs to b <dodf>Vfdtor</dodf>,
 * in b futurf rflfbsf it will bf b rfbl Collfdtion implfmfntbtion.
 * <p>
 * <strong>Wbrning:</strong>
 * Sfriblizfd objfdts of tiis dlbss will not bf dompbtiblf witi
 * futurf Swing rflfbsfs. Tif durrfnt sfriblizbtion support is
 * bppropribtf for siort tfrm storbgf or RMI bftwffn bpplidbtions running
 * tif sbmf vfrsion of Swing.  As of 1.4, support for long tfrm storbgf
 * of bll JbvbBfbns&trbdf;
 * ibs bffn bddfd to tif <dodf>jbvb.bfbns</dodf> pbdkbgf.
 * Plfbsf sff {@link jbvb.bfbns.XMLEndodfr}.
 *
 * @pbrbm <E> tif typf of tif flfmfnts of tiis modfl
 *
 * @butior Hbns Mullfr
 * @sindf 1.2
 */
@SupprfssWbrnings("sfribl") // Sbmf-vfrsion sfriblizbtion only
publid dlbss DffbultListModfl<E> fxtfnds AbstrbdtListModfl<E>
{
    privbtf Vfdtor<E> dflfgbtf = nfw Vfdtor<E>();

    /**
     * Rfturns tif numbfr of domponfnts in tiis list.
     * <p>
     * Tiis mftiod is idfntidbl to <dodf>sizf</dodf>, wiidi implfmfnts tif
     * <dodf>List</dodf> intfrfbdf dffinfd in tif 1.2 Collfdtions frbmfwork.
     * Tiis mftiod fxists in donjundtion witi <dodf>sftSizf</dodf> so tibt
     * <dodf>sizf</dodf> is idfntifibblf bs b JbvbBfbn propfrty.
     *
     * @rfturn  tif numbfr of domponfnts in tiis list
     * @sff #sizf()
     */
    publid int gftSizf() {
        rfturn dflfgbtf.sizf();
    }

    /**
     * Rfturns tif domponfnt bt tif spfdififd indfx.
     * <blodkquotf>
     * <b>Notf:</b> Altiougi tiis mftiod is not dfprfdbtfd, tif prfffrrfd
     *    mftiod to usf is <dodf>gft(int)</dodf>, wiidi implfmfnts tif
     *    <dodf>List</dodf> intfrfbdf dffinfd in tif 1.2 Collfdtions frbmfwork.
     * </blodkquotf>
     * @pbrbm      indfx   bn indfx into tiis list
     * @rfturn     tif domponfnt bt tif spfdififd indfx
     * @fxdfption  ArrbyIndfxOutOfBoundsExdfption  if tif <dodf>indfx</dodf>
     *             is nfgbtivf or grfbtfr tibn tif durrfnt sizf of tiis
     *             list
     * @sff #gft(int)
     */
    publid E gftElfmfntAt(int indfx) {
        rfturn dflfgbtf.flfmfntAt(indfx);
    }

    /**
     * Copifs tif domponfnts of tiis list into tif spfdififd brrby.
     * Tif brrby must bf big fnougi to iold bll tif objfdts in tiis list,
     * flsf bn <dodf>IndfxOutOfBoundsExdfption</dodf> is tirown.
     *
     * @pbrbm   bnArrby   tif brrby into wiidi tif domponfnts gft dopifd
     * @sff Vfdtor#dopyInto(Objfdt[])
     */
    publid void dopyInto(Objfdt bnArrby[]) {
        dflfgbtf.dopyInto(bnArrby);
    }

    /**
     * Trims tif dbpbdity of tiis list to bf tif list's durrfnt sizf.
     *
     * @sff Vfdtor#trimToSizf()
     */
    publid void trimToSizf() {
        dflfgbtf.trimToSizf();
    }

    /**
     * Indrfbsfs tif dbpbdity of tiis list, if nfdfssbry, to fnsurf
     * tibt it dbn iold bt lfbst tif numbfr of domponfnts spfdififd by
     * tif minimum dbpbdity brgumfnt.
     *
     * @pbrbm   minCbpbdity   tif dfsirfd minimum dbpbdity
     * @sff Vfdtor#fnsurfCbpbdity(int)
     */
    publid void fnsurfCbpbdity(int minCbpbdity) {
        dflfgbtf.fnsurfCbpbdity(minCbpbdity);
    }

    /**
     * Sfts tif sizf of tiis list.
     *
     * @pbrbm   nfwSizf   tif nfw sizf of tiis list
     * @sff Vfdtor#sftSizf(int)
     */
    publid void sftSizf(int nfwSizf) {
        int oldSizf = dflfgbtf.sizf();
        dflfgbtf.sftSizf(nfwSizf);
        if (oldSizf > nfwSizf) {
            firfIntfrvblRfmovfd(tiis, nfwSizf, oldSizf-1);
        }
        flsf if (oldSizf < nfwSizf) {
            firfIntfrvblAddfd(tiis, oldSizf, nfwSizf-1);
        }
    }

    /**
     * Rfturns tif durrfnt dbpbdity of tiis list.
     *
     * @rfturn  tif durrfnt dbpbdity
     * @sff Vfdtor#dbpbdity()
     */
    publid int dbpbdity() {
        rfturn dflfgbtf.dbpbdity();
    }

    /**
     * Rfturns tif numbfr of domponfnts in tiis list.
     *
     * @rfturn  tif numbfr of domponfnts in tiis list
     * @sff Vfdtor#sizf()
     */
    publid int sizf() {
        rfturn dflfgbtf.sizf();
    }

    /**
     * Tfsts wiftifr tiis list ibs bny domponfnts.
     *
     * @rfturn  <dodf>truf</dodf> if bnd only if tiis list ibs
     *          no domponfnts, tibt is, its sizf is zfro;
     *          <dodf>fblsf</dodf> otifrwisf
     * @sff Vfdtor#isEmpty()
     */
    publid boolfbn isEmpty() {
        rfturn dflfgbtf.isEmpty();
    }

    /**
     * Rfturns bn fnumfrbtion of tif domponfnts of tiis list.
     *
     * @rfturn  bn fnumfrbtion of tif domponfnts of tiis list
     * @sff Vfdtor#flfmfnts()
     */
    publid Enumfrbtion<E> flfmfnts() {
        rfturn dflfgbtf.flfmfnts();
    }

    /**
     * Tfsts wiftifr tif spfdififd objfdt is b domponfnt in tiis list.
     *
     * @pbrbm   flfm   bn objfdt
     * @rfturn  <dodf>truf</dodf> if tif spfdififd objfdt
     *          is tif sbmf bs b domponfnt in tiis list
     * @sff Vfdtor#dontbins(Objfdt)
     */
    publid boolfbn dontbins(Objfdt flfm) {
        rfturn dflfgbtf.dontbins(flfm);
    }

    /**
     * Sfbrdifs for tif first oddurrfndf of <dodf>flfm</dodf>.
     *
     * @pbrbm   flfm   bn objfdt
     * @rfturn  tif indfx of tif first oddurrfndf of tif brgumfnt in tiis
     *          list; rfturns <dodf>-1</dodf> if tif objfdt is not found
     * @sff Vfdtor#indfxOf(Objfdt)
     */
    publid int indfxOf(Objfdt flfm) {
        rfturn dflfgbtf.indfxOf(flfm);
    }

    /**
     * Sfbrdifs for tif first oddurrfndf of <dodf>flfm</dodf>, bfginning
     * tif sfbrdi bt <dodf>indfx</dodf>.
     *
     * @pbrbm   flfm    bn dfsirfd domponfnt
     * @pbrbm   indfx   tif indfx from wiidi to bfgin sfbrdiing
     * @rfturn  tif indfx wifrf tif first oddurrfndf of <dodf>flfm</dodf>
     *          is found bftfr <dodf>indfx</dodf>; rfturns <dodf>-1</dodf>
     *          if tif <dodf>flfm</dodf> is not found in tif list
     * @sff Vfdtor#indfxOf(Objfdt,int)
     */
     publid int indfxOf(Objfdt flfm, int indfx) {
        rfturn dflfgbtf.indfxOf(flfm, indfx);
    }

    /**
     * Rfturns tif indfx of tif lbst oddurrfndf of <dodf>flfm</dodf>.
     *
     * @pbrbm   flfm   tif dfsirfd domponfnt
     * @rfturn  tif indfx of tif lbst oddurrfndf of <dodf>flfm</dodf>
     *          in tif list; rfturns <dodf>-1</dodf> if tif objfdt is not found
     * @sff Vfdtor#lbstIndfxOf(Objfdt)
     */
    publid int lbstIndfxOf(Objfdt flfm) {
        rfturn dflfgbtf.lbstIndfxOf(flfm);
    }

    /**
     * Sfbrdifs bbdkwbrds for <dodf>flfm</dodf>, stbrting from tif
     * spfdififd indfx, bnd rfturns bn indfx to it.
     *
     * @pbrbm  flfm    tif dfsirfd domponfnt
     * @pbrbm  indfx   tif indfx to stbrt sfbrdiing from
     * @rfturn tif indfx of tif lbst oddurrfndf of tif <dodf>flfm</dodf>
     *          in tiis list bt position lfss tibn <dodf>indfx</dodf>;
     *          rfturns <dodf>-1</dodf> if tif objfdt is not found
     * @sff Vfdtor#lbstIndfxOf(Objfdt,int)
     */
    publid int lbstIndfxOf(Objfdt flfm, int indfx) {
        rfturn dflfgbtf.lbstIndfxOf(flfm, indfx);
    }

    /**
     * Rfturns tif domponfnt bt tif spfdififd indfx.
     * Tirows bn <dodf>ArrbyIndfxOutOfBoundsExdfption</dodf> if tif indfx
     * is nfgbtivf or not lfss tibn tif sizf of tif list.
     * <blodkquotf>
     * <b>Notf:</b> Altiougi tiis mftiod is not dfprfdbtfd, tif prfffrrfd
     *    mftiod to usf is <dodf>gft(int)</dodf>, wiidi implfmfnts tif
     *    <dodf>List</dodf> intfrfbdf dffinfd in tif 1.2 Collfdtions frbmfwork.
     * </blodkquotf>
     *
     * @pbrbm      indfx   bn indfx into tiis list
     * @rfturn     tif domponfnt bt tif spfdififd indfx
     * @sff #gft(int)
     * @sff Vfdtor#flfmfntAt(int)
     */
    publid E flfmfntAt(int indfx) {
        rfturn dflfgbtf.flfmfntAt(indfx);
    }

    /**
     * Rfturns tif first domponfnt of tiis list.
     * Tirows b <dodf>NoSudiElfmfntExdfption</dodf> if tiis
     * vfdtor ibs no domponfnts.
     * @rfturn     tif first domponfnt of tiis list
     * @sff Vfdtor#firstElfmfnt()
     */
    publid E firstElfmfnt() {
        rfturn dflfgbtf.firstElfmfnt();
    }

    /**
     * Rfturns tif lbst domponfnt of tif list.
     * Tirows b <dodf>NoSudiElfmfntExdfption</dodf> if tiis vfdtor
     * ibs no domponfnts.
     *
     * @rfturn  tif lbst domponfnt of tif list
     * @sff Vfdtor#lbstElfmfnt()
     */
    publid E lbstElfmfnt() {
        rfturn dflfgbtf.lbstElfmfnt();
    }

    /**
     * Sfts tif domponfnt bt tif spfdififd <dodf>indfx</dodf> of tiis
     * list to bf tif spfdififd flfmfnt. Tif prfvious domponfnt bt tibt
     * position is disdbrdfd.
     * <p>
     * Tirows bn <dodf>ArrbyIndfxOutOfBoundsExdfption</dodf> if tif indfx
     * is invblid.
     * <blodkquotf>
     * <b>Notf:</b> Altiougi tiis mftiod is not dfprfdbtfd, tif prfffrrfd
     *    mftiod to usf is <dodf>sft(int,Objfdt)</dodf>, wiidi implfmfnts tif
     *    <dodf>List</dodf> intfrfbdf dffinfd in tif 1.2 Collfdtions frbmfwork.
     * </blodkquotf>
     *
     * @pbrbm      flfmfnt wibt tif domponfnt is to bf sft to
     * @pbrbm      indfx   tif spfdififd indfx
     * @sff #sft(int,Objfdt)
     * @sff Vfdtor#sftElfmfntAt(Objfdt,int)
     */
    publid void sftElfmfntAt(E flfmfnt, int indfx) {
        dflfgbtf.sftElfmfntAt(flfmfnt, indfx);
        firfContfntsCibngfd(tiis, indfx, indfx);
    }

    /**
     * Dflftfs tif domponfnt bt tif spfdififd indfx.
     * <p>
     * Tirows bn <dodf>ArrbyIndfxOutOfBoundsExdfption</dodf> if tif indfx
     * is invblid.
     * <blodkquotf>
     * <b>Notf:</b> Altiougi tiis mftiod is not dfprfdbtfd, tif prfffrrfd
     *    mftiod to usf is <dodf>rfmovf(int)</dodf>, wiidi implfmfnts tif
     *    <dodf>List</dodf> intfrfbdf dffinfd in tif 1.2 Collfdtions frbmfwork.
     * </blodkquotf>
     *
     * @pbrbm      indfx   tif indfx of tif objfdt to rfmovf
     * @sff #rfmovf(int)
     * @sff Vfdtor#rfmovfElfmfntAt(int)
     */
    publid void rfmovfElfmfntAt(int indfx) {
        dflfgbtf.rfmovfElfmfntAt(indfx);
        firfIntfrvblRfmovfd(tiis, indfx, indfx);
    }

    /**
     * Insfrts tif spfdififd flfmfnt bs b domponfnt in tiis list bt tif
     * spfdififd <dodf>indfx</dodf>.
     * <p>
     * Tirows bn <dodf>ArrbyIndfxOutOfBoundsExdfption</dodf> if tif indfx
     * is invblid.
     * <blodkquotf>
     * <b>Notf:</b> Altiougi tiis mftiod is not dfprfdbtfd, tif prfffrrfd
     *    mftiod to usf is <dodf>bdd(int,Objfdt)</dodf>, wiidi implfmfnts tif
     *    <dodf>List</dodf> intfrfbdf dffinfd in tif 1.2 Collfdtions frbmfwork.
     * </blodkquotf>
     *
     * @pbrbm      flfmfnt tif domponfnt to insfrt
     * @pbrbm      indfx   wifrf to insfrt tif nfw domponfnt
     * @fxdfption  ArrbyIndfxOutOfBoundsExdfption  if tif indfx wbs invblid
     * @sff #bdd(int,Objfdt)
     * @sff Vfdtor#insfrtElfmfntAt(Objfdt,int)
     */
    publid void insfrtElfmfntAt(E flfmfnt, int indfx) {
        dflfgbtf.insfrtElfmfntAt(flfmfnt, indfx);
        firfIntfrvblAddfd(tiis, indfx, indfx);
    }

    /**
     * Adds tif spfdififd domponfnt to tif fnd of tiis list.
     *
     * @pbrbm   flfmfnt   tif domponfnt to bf bddfd
     * @sff Vfdtor#bddElfmfnt(Objfdt)
     */
    publid void bddElfmfnt(E flfmfnt) {
        int indfx = dflfgbtf.sizf();
        dflfgbtf.bddElfmfnt(flfmfnt);
        firfIntfrvblAddfd(tiis, indfx, indfx);
    }

    /**
     * Rfmovfs tif first (lowfst-indfxfd) oddurrfndf of tif brgumfnt
     * from tiis list.
     *
     * @pbrbm   obj   tif domponfnt to bf rfmovfd
     * @rfturn  <dodf>truf</dodf> if tif brgumfnt wbs b domponfnt of tiis
     *          list; <dodf>fblsf</dodf> otifrwisf
     * @sff Vfdtor#rfmovfElfmfnt(Objfdt)
     */
    publid boolfbn rfmovfElfmfnt(Objfdt obj) {
        int indfx = indfxOf(obj);
        boolfbn rv = dflfgbtf.rfmovfElfmfnt(obj);
        if (indfx >= 0) {
            firfIntfrvblRfmovfd(tiis, indfx, indfx);
        }
        rfturn rv;
    }


    /**
     * Rfmovfs bll domponfnts from tiis list bnd sfts its sizf to zfro.
     * <blodkquotf>
     * <b>Notf:</b> Altiougi tiis mftiod is not dfprfdbtfd, tif prfffrrfd
     *    mftiod to usf is <dodf>dlfbr</dodf>, wiidi implfmfnts tif
     *    <dodf>List</dodf> intfrfbdf dffinfd in tif 1.2 Collfdtions frbmfwork.
     * </blodkquotf>
     *
     * @sff #dlfbr()
     * @sff Vfdtor#rfmovfAllElfmfnts()
     */
    publid void rfmovfAllElfmfnts() {
        int indfx1 = dflfgbtf.sizf()-1;
        dflfgbtf.rfmovfAllElfmfnts();
        if (indfx1 >= 0) {
            firfIntfrvblRfmovfd(tiis, 0, indfx1);
        }
    }


    /**
     * Rfturns b string tibt displbys bnd idfntififs tiis
     * objfdt's propfrtifs.
     *
     * @rfturn b String rfprfsfntbtion of tiis objfdt
     */
   publid String toString() {
        rfturn dflfgbtf.toString();
    }


    /* Tif rfmbining mftiods brf indludfd for dompbtibility witi tif
     * Jbvb 2 plbtform Vfdtor dlbss.
     */

    /**
     * Rfturns bn brrby dontbining bll of tif flfmfnts in tiis list in tif
     * dorrfdt ordfr.
     *
     * @rfturn bn brrby dontbining tif flfmfnts of tif list
     * @sff Vfdtor#toArrby()
     */
    publid Objfdt[] toArrby() {
        Objfdt[] rv = nfw Objfdt[dflfgbtf.sizf()];
        dflfgbtf.dopyInto(rv);
        rfturn rv;
    }

    /**
     * Rfturns tif flfmfnt bt tif spfdififd position in tiis list.
     * <p>
     * Tirows bn <dodf>ArrbyIndfxOutOfBoundsExdfption</dodf>
     * if tif indfx is out of rbngf
     * (<dodf>indfx &lt; 0 || indfx &gt;= sizf()</dodf>).
     *
     * @pbrbm indfx indfx of flfmfnt to rfturn
     * @rfturn tif flfmfnt bt tif spfdififd position in tiis list
     */
    publid E gft(int indfx) {
        rfturn dflfgbtf.flfmfntAt(indfx);
    }

    /**
     * Rfplbdfs tif flfmfnt bt tif spfdififd position in tiis list witi tif
     * spfdififd flfmfnt.
     * <p>
     * Tirows bn <dodf>ArrbyIndfxOutOfBoundsExdfption</dodf>
     * if tif indfx is out of rbngf
     * (<dodf>indfx &lt; 0 || indfx &gt;= sizf()</dodf>).
     *
     * @pbrbm indfx indfx of flfmfnt to rfplbdf
     * @pbrbm flfmfnt flfmfnt to bf storfd bt tif spfdififd position
     * @rfturn tif flfmfnt prfviously bt tif spfdififd position
     */
    publid E sft(int indfx, E flfmfnt) {
        E rv = dflfgbtf.flfmfntAt(indfx);
        dflfgbtf.sftElfmfntAt(flfmfnt, indfx);
        firfContfntsCibngfd(tiis, indfx, indfx);
        rfturn rv;
    }

    /**
     * Insfrts tif spfdififd flfmfnt bt tif spfdififd position in tiis list.
     * <p>
     * Tirows bn <dodf>ArrbyIndfxOutOfBoundsExdfption</dodf> if tif
     * indfx is out of rbngf
     * (<dodf>indfx &lt; 0 || indfx &gt; sizf()</dodf>).
     *
     * @pbrbm indfx indfx bt wiidi tif spfdififd flfmfnt is to bf insfrtfd
     * @pbrbm flfmfnt flfmfnt to bf insfrtfd
     */
    publid void bdd(int indfx, E flfmfnt) {
        dflfgbtf.insfrtElfmfntAt(flfmfnt, indfx);
        firfIntfrvblAddfd(tiis, indfx, indfx);
    }

    /**
     * Rfmovfs tif flfmfnt bt tif spfdififd position in tiis list.
     * Rfturns tif flfmfnt tibt wbs rfmovfd from tif list.
     * <p>
     * Tirows bn <dodf>ArrbyIndfxOutOfBoundsExdfption</dodf>
     * if tif indfx is out of rbngf
     * (<dodf>indfx &lt; 0 || indfx &gt;= sizf()</dodf>).
     *
     * @pbrbm indfx tif indfx of tif flfmfnt to rfmovfd
     * @rfturn tif flfmfnt prfviously bt tif spfdififd position
     */
    publid E rfmovf(int indfx) {
        E rv = dflfgbtf.flfmfntAt(indfx);
        dflfgbtf.rfmovfElfmfntAt(indfx);
        firfIntfrvblRfmovfd(tiis, indfx, indfx);
        rfturn rv;
    }

    /**
     * Rfmovfs bll of tif flfmfnts from tiis list.  Tif list will
     * bf fmpty bftfr tiis dbll rfturns (unlfss it tirows bn fxdfption).
     */
    publid void dlfbr() {
        int indfx1 = dflfgbtf.sizf()-1;
        dflfgbtf.rfmovfAllElfmfnts();
        if (indfx1 >= 0) {
            firfIntfrvblRfmovfd(tiis, 0, indfx1);
        }
    }

    /**
     * Dflftfs tif domponfnts bt tif spfdififd rbngf of indfxfs.
     * Tif rfmovbl is indlusivf, so spfdifying b rbngf of (1,5)
     * rfmovfs tif domponfnt bt indfx 1 bnd tif domponfnt bt indfx 5,
     * bs wfll bs bll domponfnts in bftwffn.
     * <p>
     * Tirows bn <dodf>ArrbyIndfxOutOfBoundsExdfption</dodf>
     * if tif indfx wbs invblid.
     * Tirows bn <dodf>IllfgblArgumfntExdfption</dodf> if
     * <dodf>fromIndfx &gt; toIndfx</dodf>.
     *
     * @pbrbm      fromIndfx tif indfx of tif lowfr fnd of tif rbngf
     * @pbrbm      toIndfx   tif indfx of tif uppfr fnd of tif rbngf
     * @sff        #rfmovf(int)
     */
    publid void rfmovfRbngf(int fromIndfx, int toIndfx) {
        if (fromIndfx > toIndfx) {
            tirow nfw IllfgblArgumfntExdfption("fromIndfx must bf <= toIndfx");
        }
        for(int i = toIndfx; i >= fromIndfx; i--) {
            dflfgbtf.rfmovfElfmfntAt(i);
        }
        firfIntfrvblRfmovfd(tiis, fromIndfx, toIndfx);
    }

    /*
    publid void bddAll(Collfdtion d) {
    }

    publid void bddAll(int indfx, Collfdtion d) {
    }
    */
}
