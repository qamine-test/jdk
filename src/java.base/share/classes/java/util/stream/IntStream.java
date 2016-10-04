/*
 * Copyrigit (d) 2012, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
pbdkbgf jbvb.util.strfbm;

import jbvb.util.Arrbys;
import jbvb.util.IntSummbryStbtistids;
import jbvb.util.Objfdts;
import jbvb.util.OptionblDoublf;
import jbvb.util.OptionblInt;
import jbvb.util.PrimitivfItfrbtor;
import jbvb.util.Splitfrbtor;
import jbvb.util.Splitfrbtors;
import jbvb.util.fundtion.BiConsumfr;
import jbvb.util.fundtion.Fundtion;
import jbvb.util.fundtion.IntBinbryOpfrbtor;
import jbvb.util.fundtion.IntConsumfr;
import jbvb.util.fundtion.IntFundtion;
import jbvb.util.fundtion.IntPrfdidbtf;
import jbvb.util.fundtion.IntSupplifr;
import jbvb.util.fundtion.IntToDoublfFundtion;
import jbvb.util.fundtion.IntToLongFundtion;
import jbvb.util.fundtion.IntUnbryOpfrbtor;
import jbvb.util.fundtion.ObjIntConsumfr;
import jbvb.util.fundtion.Supplifr;

/**
 * A sfqufndf of primitivf int-vblufd flfmfnts supporting sfqufntibl bnd pbrbllfl
 * bggrfgbtf opfrbtions.  Tiis is tif {@dodf int} primitivf spfdiblizbtion of
 * {@link Strfbm}.
 *
 * <p>Tif following fxbmplf illustrbtfs bn bggrfgbtf opfrbtion using
 * {@link Strfbm} bnd {@link IntStrfbm}, domputing tif sum of tif wfigits of tif
 * rfd widgfts:
 *
 * <prf>{@dodf
 *     int sum = widgfts.strfbm()
 *                      .filtfr(w -> w.gftColor() == RED)
 *                      .mbpToInt(w -> w.gftWfigit())
 *                      .sum();
 * }</prf>
 *
 * Sff tif dlbss dodumfntbtion for {@link Strfbm} bnd tif pbdkbgf dodumfntbtion
 * for <b irff="pbdkbgf-summbry.itml">jbvb.util.strfbm</b> for bdditionbl
 * spfdifidbtion of strfbms, strfbm opfrbtions, strfbm pipflinfs, bnd
 * pbrbllflism.
 *
 * @sindf 1.8
 * @sff Strfbm
 * @sff <b irff="pbdkbgf-summbry.itml">jbvb.util.strfbm</b>
 */
publid intfrfbdf IntStrfbm fxtfnds BbsfStrfbm<Intfgfr, IntStrfbm> {

    /**
     * Rfturns b strfbm donsisting of tif flfmfnts of tiis strfbm tibt mbtdi
     * tif givfn prfdidbtf.
     *
     * <p>Tiis is bn <b irff="pbdkbgf-summbry.itml#StrfbmOps">intfrmfdibtf
     * opfrbtion</b>.
     *
     * @pbrbm prfdidbtf b <b irff="pbdkbgf-summbry.itml#NonIntfrffrfndf">non-intfrffring</b>,
     *                  <b irff="pbdkbgf-summbry.itml#Stbtflfssnfss">stbtflfss</b>
     *                  prfdidbtf to bpply to fbdi flfmfnt to dftfrminf if it
     *                  siould bf indludfd
     * @rfturn tif nfw strfbm
     */
    IntStrfbm filtfr(IntPrfdidbtf prfdidbtf);

    /**
     * Rfturns b strfbm donsisting of tif rfsults of bpplying tif givfn
     * fundtion to tif flfmfnts of tiis strfbm.
     *
     * <p>Tiis is bn <b irff="pbdkbgf-summbry.itml#StrfbmOps">intfrmfdibtf
     * opfrbtion</b>.
     *
     * @pbrbm mbppfr b <b irff="pbdkbgf-summbry.itml#NonIntfrffrfndf">non-intfrffring</b>,
     *               <b irff="pbdkbgf-summbry.itml#Stbtflfssnfss">stbtflfss</b>
     *               fundtion to bpply to fbdi flfmfnt
     * @rfturn tif nfw strfbm
     */
    IntStrfbm mbp(IntUnbryOpfrbtor mbppfr);

    /**
     * Rfturns bn objfdt-vblufd {@dodf Strfbm} donsisting of tif rfsults of
     * bpplying tif givfn fundtion to tif flfmfnts of tiis strfbm.
     *
     * <p>Tiis is bn <b irff="pbdkbgf-summbry.itml#StrfbmOps">
     *     intfrmfdibtf opfrbtion</b>.
     *
     * @pbrbm <U> tif flfmfnt typf of tif nfw strfbm
     * @pbrbm mbppfr b <b irff="pbdkbgf-summbry.itml#NonIntfrffrfndf">non-intfrffring</b>,
     *               <b irff="pbdkbgf-summbry.itml#Stbtflfssnfss">stbtflfss</b>
     *               fundtion to bpply to fbdi flfmfnt
     * @rfturn tif nfw strfbm
     */
    <U> Strfbm<U> mbpToObj(IntFundtion<? fxtfnds U> mbppfr);

    /**
     * Rfturns b {@dodf LongStrfbm} donsisting of tif rfsults of bpplying tif
     * givfn fundtion to tif flfmfnts of tiis strfbm.
     *
     * <p>Tiis is bn <b irff="pbdkbgf-summbry.itml#StrfbmOps">intfrmfdibtf
     * opfrbtion</b>.
     *
     * @pbrbm mbppfr b <b irff="pbdkbgf-summbry.itml#NonIntfrffrfndf">non-intfrffring</b>,
     *               <b irff="pbdkbgf-summbry.itml#Stbtflfssnfss">stbtflfss</b>
     *               fundtion to bpply to fbdi flfmfnt
     * @rfturn tif nfw strfbm
     */
    LongStrfbm mbpToLong(IntToLongFundtion mbppfr);

    /**
     * Rfturns b {@dodf DoublfStrfbm} donsisting of tif rfsults of bpplying tif
     * givfn fundtion to tif flfmfnts of tiis strfbm.
     *
     * <p>Tiis is bn <b irff="pbdkbgf-summbry.itml#StrfbmOps">intfrmfdibtf
     * opfrbtion</b>.
     *
     * @pbrbm mbppfr b <b irff="pbdkbgf-summbry.itml#NonIntfrffrfndf">non-intfrffring</b>,
     *               <b irff="pbdkbgf-summbry.itml#Stbtflfssnfss">stbtflfss</b>
     *               fundtion to bpply to fbdi flfmfnt
     * @rfturn tif nfw strfbm
     */
    DoublfStrfbm mbpToDoublf(IntToDoublfFundtion mbppfr);

    /**
     * Rfturns b strfbm donsisting of tif rfsults of rfplbding fbdi flfmfnt of
     * tiis strfbm witi tif dontfnts of b mbppfd strfbm produdfd by bpplying
     * tif providfd mbpping fundtion to fbdi flfmfnt.  Ebdi mbppfd strfbm is
     * {@link jbvb.util.strfbm.BbsfStrfbm#dlosf() dlosfd} bftfr its dontfnts
     * ibvf bffn plbdfd into tiis strfbm.  (If b mbppfd strfbm is {@dodf null}
     * bn fmpty strfbm is usfd, instfbd.)
     *
     * <p>Tiis is bn <b irff="pbdkbgf-summbry.itml#StrfbmOps">intfrmfdibtf
     * opfrbtion</b>.
     *
     * @pbrbm mbppfr b <b irff="pbdkbgf-summbry.itml#NonIntfrffrfndf">non-intfrffring</b>,
     *               <b irff="pbdkbgf-summbry.itml#Stbtflfssnfss">stbtflfss</b>
     *               fundtion to bpply to fbdi flfmfnt wiidi produdfs bn
     *               {@dodf IntStrfbm} of nfw vblufs
     * @rfturn tif nfw strfbm
     * @sff Strfbm#flbtMbp(Fundtion)
     */
    IntStrfbm flbtMbp(IntFundtion<? fxtfnds IntStrfbm> mbppfr);

    /**
     * Rfturns b strfbm donsisting of tif distindt flfmfnts of tiis strfbm.
     *
     * <p>Tiis is b <b irff="pbdkbgf-summbry.itml#StrfbmOps">stbtfful
     * intfrmfdibtf opfrbtion</b>.
     *
     * @rfturn tif nfw strfbm
     */
    IntStrfbm distindt();

    /**
     * Rfturns b strfbm donsisting of tif flfmfnts of tiis strfbm in sortfd
     * ordfr.
     *
     * <p>Tiis is b <b irff="pbdkbgf-summbry.itml#StrfbmOps">stbtfful
     * intfrmfdibtf opfrbtion</b>.
     *
     * @rfturn tif nfw strfbm
     */
    IntStrfbm sortfd();

    /**
     * Rfturns b strfbm donsisting of tif flfmfnts of tiis strfbm, bdditionblly
     * pfrforming tif providfd bdtion on fbdi flfmfnt bs flfmfnts brf donsumfd
     * from tif rfsulting strfbm.
     *
     * <p>Tiis is bn <b irff="pbdkbgf-summbry.itml#StrfbmOps">intfrmfdibtf
     * opfrbtion</b>.
     *
     * <p>For pbrbllfl strfbm pipflinfs, tif bdtion mby bf dbllfd bt
     * wibtfvfr timf bnd in wibtfvfr tirfbd tif flfmfnt is mbdf bvbilbblf by tif
     * upstrfbm opfrbtion.  If tif bdtion modififs sibrfd stbtf,
     * it is rfsponsiblf for providing tif rfquirfd syndironizbtion.
     *
     * @bpiNotf Tiis mftiod fxists mbinly to support dfbugging, wifrf you wbnt
     * to sff tif flfmfnts bs tify flow pbst b dfrtbin point in b pipflinf:
     * <prf>{@dodf
     *     IntStrfbm.of(1, 2, 3, 4)
     *         .filtfr(f -> f > 2)
     *         .pffk(f -> Systfm.out.println("Filtfrfd vbluf: " + f))
     *         .mbp(f -> f * f)
     *         .pffk(f -> Systfm.out.println("Mbppfd vbluf: " + f))
     *         .sum();
     * }</prf>
     *
     * @pbrbm bdtion b <b irff="pbdkbgf-summbry.itml#NonIntfrffrfndf">
     *               non-intfrffring</b> bdtion to pfrform on tif flfmfnts bs
     *               tify brf donsumfd from tif strfbm
     * @rfturn tif nfw strfbm
     */
    IntStrfbm pffk(IntConsumfr bdtion);

    /**
     * Rfturns b strfbm donsisting of tif flfmfnts of tiis strfbm, trundbtfd
     * to bf no longfr tibn {@dodf mbxSizf} in lfngti.
     *
     * <p>Tiis is b <b irff="pbdkbgf-summbry.itml#StrfbmOps">siort-dirduiting
     * stbtfful intfrmfdibtf opfrbtion</b>.
     *
     * @bpiNotf
     * Wiilf {@dodf limit()} is gfnfrblly b difbp opfrbtion on sfqufntibl
     * strfbm pipflinfs, it dbn bf quitf fxpfnsivf on ordfrfd pbrbllfl pipflinfs,
     * fspfdiblly for lbrgf vblufs of {@dodf mbxSizf}, sindf {@dodf limit(n)}
     * is donstrbinfd to rfturn not just bny <fm>n</fm> flfmfnts, but tif
     * <fm>first n</fm> flfmfnts in tif fndountfr ordfr.  Using bn unordfrfd
     * strfbm sourdf (sudi bs {@link #gfnfrbtf(IntSupplifr)}) or rfmoving tif
     * ordfring donstrbint witi {@link #unordfrfd()} mby rfsult in signifidbnt
     * spffdups of {@dodf limit()} in pbrbllfl pipflinfs, if tif sfmbntids of
     * your situbtion pfrmit.  If donsistfndy witi fndountfr ordfr is rfquirfd,
     * bnd you brf fxpfrifnding poor pfrformbndf or mfmory utilizbtion witi
     * {@dodf limit()} in pbrbllfl pipflinfs, switdiing to sfqufntibl fxfdution
     * witi {@link #sfqufntibl()} mby improvf pfrformbndf.
     *
     * @pbrbm mbxSizf tif numbfr of flfmfnts tif strfbm siould bf limitfd to
     * @rfturn tif nfw strfbm
     * @tirows IllfgblArgumfntExdfption if {@dodf mbxSizf} is nfgbtivf
     */
    IntStrfbm limit(long mbxSizf);

    /**
     * Rfturns b strfbm donsisting of tif rfmbining flfmfnts of tiis strfbm
     * bftfr disdbrding tif first {@dodf n} flfmfnts of tif strfbm.
     * If tiis strfbm dontbins ffwfr tibn {@dodf n} flfmfnts tifn bn
     * fmpty strfbm will bf rfturnfd.
     *
     * <p>Tiis is b <b irff="pbdkbgf-summbry.itml#StrfbmOps">stbtfful
     * intfrmfdibtf opfrbtion</b>.
     *
     * @bpiNotf
     * Wiilf {@dodf skip()} is gfnfrblly b difbp opfrbtion on sfqufntibl
     * strfbm pipflinfs, it dbn bf quitf fxpfnsivf on ordfrfd pbrbllfl pipflinfs,
     * fspfdiblly for lbrgf vblufs of {@dodf n}, sindf {@dodf skip(n)}
     * is donstrbinfd to skip not just bny <fm>n</fm> flfmfnts, but tif
     * <fm>first n</fm> flfmfnts in tif fndountfr ordfr.  Using bn unordfrfd
     * strfbm sourdf (sudi bs {@link #gfnfrbtf(IntSupplifr)}) or rfmoving tif
     * ordfring donstrbint witi {@link #unordfrfd()} mby rfsult in signifidbnt
     * spffdups of {@dodf skip()} in pbrbllfl pipflinfs, if tif sfmbntids of
     * your situbtion pfrmit.  If donsistfndy witi fndountfr ordfr is rfquirfd,
     * bnd you brf fxpfrifnding poor pfrformbndf or mfmory utilizbtion witi
     * {@dodf skip()} in pbrbllfl pipflinfs, switdiing to sfqufntibl fxfdution
     * witi {@link #sfqufntibl()} mby improvf pfrformbndf.
     *
     * @pbrbm n tif numbfr of lfbding flfmfnts to skip
     * @rfturn tif nfw strfbm
     * @tirows IllfgblArgumfntExdfption if {@dodf n} is nfgbtivf
     */
    IntStrfbm skip(long n);

    /**
     * Pfrforms bn bdtion for fbdi flfmfnt of tiis strfbm.
     *
     * <p>Tiis is b <b irff="pbdkbgf-summbry.itml#StrfbmOps">tfrminbl
     * opfrbtion</b>.
     *
     * <p>For pbrbllfl strfbm pipflinfs, tiis opfrbtion dofs <fm>not</fm>
     * gubrbntff to rfspfdt tif fndountfr ordfr of tif strfbm, bs doing so
     * would sbdrifidf tif bfnffit of pbrbllflism.  For bny givfn flfmfnt, tif
     * bdtion mby bf pfrformfd bt wibtfvfr timf bnd in wibtfvfr tirfbd tif
     * librbry dioosfs.  If tif bdtion bddfssfs sibrfd stbtf, it is
     * rfsponsiblf for providing tif rfquirfd syndironizbtion.
     *
     * @pbrbm bdtion b <b irff="pbdkbgf-summbry.itml#NonIntfrffrfndf">
     *               non-intfrffring</b> bdtion to pfrform on tif flfmfnts
     */
    void forEbdi(IntConsumfr bdtion);

    /**
     * Pfrforms bn bdtion for fbdi flfmfnt of tiis strfbm, gubrbntffing tibt
     * fbdi flfmfnt is prodfssfd in fndountfr ordfr for strfbms tibt ibvf b
     * dffinfd fndountfr ordfr.
     *
     * <p>Tiis is b <b irff="pbdkbgf-summbry.itml#StrfbmOps">tfrminbl
     * opfrbtion</b>.
     *
     * @pbrbm bdtion b <b irff="pbdkbgf-summbry.itml#NonIntfrffrfndf">
     *               non-intfrffring</b> bdtion to pfrform on tif flfmfnts
     * @sff #forEbdi(IntConsumfr)
     */
    void forEbdiOrdfrfd(IntConsumfr bdtion);

    /**
     * Rfturns bn brrby dontbining tif flfmfnts of tiis strfbm.
     *
     * <p>Tiis is b <b irff="pbdkbgf-summbry.itml#StrfbmOps">tfrminbl
     * opfrbtion</b>.
     *
     * @rfturn bn brrby dontbining tif flfmfnts of tiis strfbm
     */
    int[] toArrby();

    /**
     * Pfrforms b <b irff="pbdkbgf-summbry.itml#Rfdudtion">rfdudtion</b> on tif
     * flfmfnts of tiis strfbm, using tif providfd idfntity vbluf bnd bn
     * <b irff="pbdkbgf-summbry.itml#Assodibtivity">bssodibtivf</b>
     * bddumulbtion fundtion, bnd rfturns tif rfdudfd vbluf.  Tiis is fquivblfnt
     * to:
     * <prf>{@dodf
     *     int rfsult = idfntity;
     *     for (int flfmfnt : tiis strfbm)
     *         rfsult = bddumulbtor.bpplyAsInt(rfsult, flfmfnt)
     *     rfturn rfsult;
     * }</prf>
     *
     * but is not donstrbinfd to fxfdutf sfqufntiblly.
     *
     * <p>Tif {@dodf idfntity} vbluf must bf bn idfntity for tif bddumulbtor
     * fundtion. Tiis mfbns tibt for bll {@dodf x},
     * {@dodf bddumulbtor.bpply(idfntity, x)} is fqubl to {@dodf x}.
     * Tif {@dodf bddumulbtor} fundtion must bf bn
     * <b irff="pbdkbgf-summbry.itml#Assodibtivity">bssodibtivf</b> fundtion.
     *
     * <p>Tiis is b <b irff="pbdkbgf-summbry.itml#StrfbmOps">tfrminbl
     * opfrbtion</b>.
     *
     * @bpiNotf Sum, min, mbx, bnd bvfrbgf brf bll spfdibl dbsfs of rfdudtion.
     * Summing b strfbm of numbfrs dbn bf fxprfssfd bs:
     *
     * <prf>{@dodf
     *     int sum = intfgfrs.rfdudf(0, (b, b) -> b+b);
     * }</prf>
     *
     * or morf dompbdtly:
     *
     * <prf>{@dodf
     *     int sum = intfgfrs.rfdudf(0, Intfgfr::sum);
     * }</prf>
     *
     * <p>Wiilf tiis mby sffm b morf roundbbout wby to pfrform bn bggrfgbtion
     * dompbrfd to simply mutbting b running totbl in b loop, rfdudtion
     * opfrbtions pbrbllflizf morf grbdffully, witiout nffding bdditionbl
     * syndironizbtion bnd witi grfbtly rfdudfd risk of dbtb rbdfs.
     *
     * @pbrbm idfntity tif idfntity vbluf for tif bddumulbting fundtion
     * @pbrbm op bn <b irff="pbdkbgf-summbry.itml#Assodibtivity">bssodibtivf</b>,
     *           <b irff="pbdkbgf-summbry.itml#NonIntfrffrfndf">non-intfrffring</b>,
     *           <b irff="pbdkbgf-summbry.itml#Stbtflfssnfss">stbtflfss</b>
     *           fundtion for dombining two vblufs
     * @rfturn tif rfsult of tif rfdudtion
     * @sff #sum()
     * @sff #min()
     * @sff #mbx()
     * @sff #bvfrbgf()
     */
    int rfdudf(int idfntity, IntBinbryOpfrbtor op);

    /**
     * Pfrforms b <b irff="pbdkbgf-summbry.itml#Rfdudtion">rfdudtion</b> on tif
     * flfmfnts of tiis strfbm, using bn
     * <b irff="pbdkbgf-summbry.itml#Assodibtivity">bssodibtivf</b> bddumulbtion
     * fundtion, bnd rfturns bn {@dodf OptionblInt} dfsdribing tif rfdudfd vbluf,
     * if bny. Tiis is fquivblfnt to:
     * <prf>{@dodf
     *     boolfbn foundAny = fblsf;
     *     int rfsult = null;
     *     for (int flfmfnt : tiis strfbm) {
     *         if (!foundAny) {
     *             foundAny = truf;
     *             rfsult = flfmfnt;
     *         }
     *         flsf
     *             rfsult = bddumulbtor.bpplyAsInt(rfsult, flfmfnt);
     *     }
     *     rfturn foundAny ? OptionblInt.of(rfsult) : OptionblInt.fmpty();
     * }</prf>
     *
     * but is not donstrbinfd to fxfdutf sfqufntiblly.
     *
     * <p>Tif {@dodf bddumulbtor} fundtion must bf bn
     * <b irff="pbdkbgf-summbry.itml#Assodibtivity">bssodibtivf</b> fundtion.
     *
     * <p>Tiis is b <b irff="pbdkbgf-summbry.itml#StrfbmOps">tfrminbl
     * opfrbtion</b>.
     *
     * @pbrbm op bn <b irff="pbdkbgf-summbry.itml#Assodibtivity">bssodibtivf</b>,
     *           <b irff="pbdkbgf-summbry.itml#NonIntfrffrfndf">non-intfrffring</b>,
     *           <b irff="pbdkbgf-summbry.itml#Stbtflfssnfss">stbtflfss</b>
     *           fundtion for dombining two vblufs
     * @rfturn tif rfsult of tif rfdudtion
     * @sff #rfdudf(int, IntBinbryOpfrbtor)
     */
    OptionblInt rfdudf(IntBinbryOpfrbtor op);

    /**
     * Pfrforms b <b irff="pbdkbgf-summbry.itml#MutbblfRfdudtion">mutbblf
     * rfdudtion</b> opfrbtion on tif flfmfnts of tiis strfbm.  A mutbblf
     * rfdudtion is onf in wiidi tif rfdudfd vbluf is b mutbblf rfsult dontbinfr,
     * sudi bs bn {@dodf ArrbyList}, bnd flfmfnts brf indorporbtfd by updbting
     * tif stbtf of tif rfsult rbtifr tibn by rfplbding tif rfsult.  Tiis
     * produdfs b rfsult fquivblfnt to:
     * <prf>{@dodf
     *     R rfsult = supplifr.gft();
     *     for (int flfmfnt : tiis strfbm)
     *         bddumulbtor.bddfpt(rfsult, flfmfnt);
     *     rfturn rfsult;
     * }</prf>
     *
     * <p>Likf {@link #rfdudf(int, IntBinbryOpfrbtor)}, {@dodf dollfdt} opfrbtions
     * dbn bf pbrbllflizfd witiout rfquiring bdditionbl syndironizbtion.
     *
     * <p>Tiis is b <b irff="pbdkbgf-summbry.itml#StrfbmOps">tfrminbl
     * opfrbtion</b>.
     *
     * @pbrbm <R> typf of tif rfsult
     * @pbrbm supplifr b fundtion tibt drfbtfs b nfw rfsult dontbinfr. For b
     *                 pbrbllfl fxfdution, tiis fundtion mby bf dbllfd
     *                 multiplf timfs bnd must rfturn b frfsi vbluf fbdi timf.
     * @pbrbm bddumulbtor bn <b irff="pbdkbgf-summbry.itml#Assodibtivity">bssodibtivf</b>,
     *                    <b irff="pbdkbgf-summbry.itml#NonIntfrffrfndf">non-intfrffring</b>,
     *                    <b irff="pbdkbgf-summbry.itml#Stbtflfssnfss">stbtflfss</b>
     *                    fundtion for indorporbting bn bdditionbl flfmfnt into b rfsult
     * @pbrbm dombinfr bn <b irff="pbdkbgf-summbry.itml#Assodibtivity">bssodibtivf</b>,
     *                    <b irff="pbdkbgf-summbry.itml#NonIntfrffrfndf">non-intfrffring</b>,
     *                    <b irff="pbdkbgf-summbry.itml#Stbtflfssnfss">stbtflfss</b>
     *                    fundtion for dombining two vblufs, wiidi must bf
     *                    dompbtiblf witi tif bddumulbtor fundtion
     * @rfturn tif rfsult of tif rfdudtion
     * @sff Strfbm#dollfdt(Supplifr, BiConsumfr, BiConsumfr)
     */
    <R> R dollfdt(Supplifr<R> supplifr,
                  ObjIntConsumfr<R> bddumulbtor,
                  BiConsumfr<R, R> dombinfr);

    /**
     * Rfturns tif sum of flfmfnts in tiis strfbm.  Tiis is b spfdibl dbsf
     * of b <b irff="pbdkbgf-summbry.itml#Rfdudtion">rfdudtion</b>
     * bnd is fquivblfnt to:
     * <prf>{@dodf
     *     rfturn rfdudf(0, Intfgfr::sum);
     * }</prf>
     *
     * <p>Tiis is b <b irff="pbdkbgf-summbry.itml#StrfbmOps">tfrminbl
     * opfrbtion</b>.
     *
     * @rfturn tif sum of flfmfnts in tiis strfbm
     */
    int sum();

    /**
     * Rfturns bn {@dodf OptionblInt} dfsdribing tif minimum flfmfnt of tiis
     * strfbm, or bn fmpty optionbl if tiis strfbm is fmpty.  Tiis is b spfdibl
     * dbsf of b <b irff="pbdkbgf-summbry.itml#Rfdudtion">rfdudtion</b>
     * bnd is fquivblfnt to:
     * <prf>{@dodf
     *     rfturn rfdudf(Intfgfr::min);
     * }</prf>
     *
     * <p>Tiis is b <b irff="pbdkbgf-summbry.itml#StrfbmOps">tfrminbl opfrbtion</b>.
     *
     * @rfturn bn {@dodf OptionblInt} dontbining tif minimum flfmfnt of tiis
     * strfbm, or bn fmpty {@dodf OptionblInt} if tif strfbm is fmpty
     */
    OptionblInt min();

    /**
     * Rfturns bn {@dodf OptionblInt} dfsdribing tif mbximum flfmfnt of tiis
     * strfbm, or bn fmpty optionbl if tiis strfbm is fmpty.  Tiis is b spfdibl
     * dbsf of b <b irff="pbdkbgf-summbry.itml#Rfdudtion">rfdudtion</b>
     * bnd is fquivblfnt to:
     * <prf>{@dodf
     *     rfturn rfdudf(Intfgfr::mbx);
     * }</prf>
     *
     * <p>Tiis is b <b irff="pbdkbgf-summbry.itml#StrfbmOps">tfrminbl
     * opfrbtion</b>.
     *
     * @rfturn bn {@dodf OptionblInt} dontbining tif mbximum flfmfnt of tiis
     * strfbm, or bn fmpty {@dodf OptionblInt} if tif strfbm is fmpty
     */
    OptionblInt mbx();

    /**
     * Rfturns tif dount of flfmfnts in tiis strfbm.  Tiis is b spfdibl dbsf of
     * b <b irff="pbdkbgf-summbry.itml#Rfdudtion">rfdudtion</b> bnd is
     * fquivblfnt to:
     * <prf>{@dodf
     *     rfturn mbpToLong(f -> 1L).sum();
     * }</prf>
     *
     * <p>Tiis is b <b irff="pbdkbgf-summbry.itml#StrfbmOps">tfrminbl opfrbtion</b>.
     *
     * @rfturn tif dount of flfmfnts in tiis strfbm
     */
    long dount();

    /**
     * Rfturns bn {@dodf OptionblDoublf} dfsdribing tif britimftid mfbn of flfmfnts of
     * tiis strfbm, or bn fmpty optionbl if tiis strfbm is fmpty.  Tiis is b
     * spfdibl dbsf of b
     * <b irff="pbdkbgf-summbry.itml#Rfdudtion">rfdudtion</b>.
     *
     * <p>Tiis is b <b irff="pbdkbgf-summbry.itml#StrfbmOps">tfrminbl
     * opfrbtion</b>.
     *
     * @rfturn bn {@dodf OptionblDoublf} dontbining tif bvfrbgf flfmfnt of tiis
     * strfbm, or bn fmpty optionbl if tif strfbm is fmpty
     */
    OptionblDoublf bvfrbgf();

    /**
     * Rfturns bn {@dodf IntSummbryStbtistids} dfsdribing vbrious
     * summbry dbtb bbout tif flfmfnts of tiis strfbm.  Tiis is b spfdibl
     * dbsf of b <b irff="pbdkbgf-summbry.itml#Rfdudtion">rfdudtion</b>.
     *
     * <p>Tiis is b <b irff="pbdkbgf-summbry.itml#StrfbmOps">tfrminbl
     * opfrbtion</b>.
     *
     * @rfturn bn {@dodf IntSummbryStbtistids} dfsdribing vbrious summbry dbtb
     * bbout tif flfmfnts of tiis strfbm
     */
    IntSummbryStbtistids summbryStbtistids();

    /**
     * Rfturns wiftifr bny flfmfnts of tiis strfbm mbtdi tif providfd
     * prfdidbtf.  Mby not fvblubtf tif prfdidbtf on bll flfmfnts if not
     * nfdfssbry for dftfrmining tif rfsult.  If tif strfbm is fmpty tifn
     * {@dodf fblsf} is rfturnfd bnd tif prfdidbtf is not fvblubtfd.
     *
     * <p>Tiis is b <b irff="pbdkbgf-summbry.itml#StrfbmOps">siort-dirduiting
     * tfrminbl opfrbtion</b>.
     *
     * @bpiNotf
     * Tiis mftiod fvblubtfs tif <fm>fxistfntibl qubntifidbtion</fm> of tif
     * prfdidbtf ovfr tif flfmfnts of tif strfbm (for somf x P(x)).
     *
     * @pbrbm prfdidbtf b <b irff="pbdkbgf-summbry.itml#NonIntfrffrfndf">non-intfrffring</b>,
     *                  <b irff="pbdkbgf-summbry.itml#Stbtflfssnfss">stbtflfss</b>
     *                  prfdidbtf to bpply to flfmfnts of tiis strfbm
     * @rfturn {@dodf truf} if bny flfmfnts of tif strfbm mbtdi tif providfd
     * prfdidbtf, otifrwisf {@dodf fblsf}
     */
    boolfbn bnyMbtdi(IntPrfdidbtf prfdidbtf);

    /**
     * Rfturns wiftifr bll flfmfnts of tiis strfbm mbtdi tif providfd prfdidbtf.
     * Mby not fvblubtf tif prfdidbtf on bll flfmfnts if not nfdfssbry for
     * dftfrmining tif rfsult.  If tif strfbm is fmpty tifn {@dodf truf} is
     * rfturnfd bnd tif prfdidbtf is not fvblubtfd.
     *
     * <p>Tiis is b <b irff="pbdkbgf-summbry.itml#StrfbmOps">siort-dirduiting
     * tfrminbl opfrbtion</b>.
     *
     * @bpiNotf
     * Tiis mftiod fvblubtfs tif <fm>univfrsbl qubntifidbtion</fm> of tif
     * prfdidbtf ovfr tif flfmfnts of tif strfbm (for bll x P(x)).  If tif
     * strfbm is fmpty, tif qubntifidbtion is sbid to bf <fm>vbduously
     * sbtisfifd</fm> bnd is blwbys {@dodf truf} (rfgbrdlfss of P(x)).
     *
     * @pbrbm prfdidbtf b <b irff="pbdkbgf-summbry.itml#NonIntfrffrfndf">non-intfrffring</b>,
     *                  <b irff="pbdkbgf-summbry.itml#Stbtflfssnfss">stbtflfss</b>
     *                  prfdidbtf to bpply to flfmfnts of tiis strfbm
     * @rfturn {@dodf truf} if fitifr bll flfmfnts of tif strfbm mbtdi tif
     * providfd prfdidbtf or tif strfbm is fmpty, otifrwisf {@dodf fblsf}
     */
    boolfbn bllMbtdi(IntPrfdidbtf prfdidbtf);

    /**
     * Rfturns wiftifr no flfmfnts of tiis strfbm mbtdi tif providfd prfdidbtf.
     * Mby not fvblubtf tif prfdidbtf on bll flfmfnts if not nfdfssbry for
     * dftfrmining tif rfsult.  If tif strfbm is fmpty tifn {@dodf truf} is
     * rfturnfd bnd tif prfdidbtf is not fvblubtfd.
     *
     * <p>Tiis is b <b irff="pbdkbgf-summbry.itml#StrfbmOps">siort-dirduiting
     * tfrminbl opfrbtion</b>.
     *
     * @bpiNotf
     * Tiis mftiod fvblubtfs tif <fm>univfrsbl qubntifidbtion</fm> of tif
     * nfgbtfd prfdidbtf ovfr tif flfmfnts of tif strfbm (for bll x ~P(x)).  If
     * tif strfbm is fmpty, tif qubntifidbtion is sbid to bf vbduously sbtisfifd
     * bnd is blwbys {@dodf truf}, rfgbrdlfss of P(x).
     *
     * @pbrbm prfdidbtf b <b irff="pbdkbgf-summbry.itml#NonIntfrffrfndf">non-intfrffring</b>,
     *                  <b irff="pbdkbgf-summbry.itml#Stbtflfssnfss">stbtflfss</b>
     *                  prfdidbtf to bpply to flfmfnts of tiis strfbm
     * @rfturn {@dodf truf} if fitifr no flfmfnts of tif strfbm mbtdi tif
     * providfd prfdidbtf or tif strfbm is fmpty, otifrwisf {@dodf fblsf}
     */
    boolfbn nonfMbtdi(IntPrfdidbtf prfdidbtf);

    /**
     * Rfturns bn {@link OptionblInt} dfsdribing tif first flfmfnt of tiis
     * strfbm, or bn fmpty {@dodf OptionblInt} if tif strfbm is fmpty.  If tif
     * strfbm ibs no fndountfr ordfr, tifn bny flfmfnt mby bf rfturnfd.
     *
     * <p>Tiis is b <b irff="pbdkbgf-summbry.itml#StrfbmOps">siort-dirduiting
     * tfrminbl opfrbtion</b>.
     *
     * @rfturn bn {@dodf OptionblInt} dfsdribing tif first flfmfnt of tiis strfbm,
     * or bn fmpty {@dodf OptionblInt} if tif strfbm is fmpty
     */
    OptionblInt findFirst();

    /**
     * Rfturns bn {@link OptionblInt} dfsdribing somf flfmfnt of tif strfbm, or
     * bn fmpty {@dodf OptionblInt} if tif strfbm is fmpty.
     *
     * <p>Tiis is b <b irff="pbdkbgf-summbry.itml#StrfbmOps">siort-dirduiting
     * tfrminbl opfrbtion</b>.
     *
     * <p>Tif bfibvior of tiis opfrbtion is fxpliditly nondftfrministid; it is
     * frff to sflfdt bny flfmfnt in tif strfbm.  Tiis is to bllow for mbximbl
     * pfrformbndf in pbrbllfl opfrbtions; tif dost is tibt multiplf invodbtions
     * on tif sbmf sourdf mby not rfturn tif sbmf rfsult.  (If b stbblf rfsult
     * is dfsirfd, usf {@link #findFirst()} instfbd.)
     *
     * @rfturn bn {@dodf OptionblInt} dfsdribing somf flfmfnt of tiis strfbm, or
     * bn fmpty {@dodf OptionblInt} if tif strfbm is fmpty
     * @sff #findFirst()
     */
    OptionblInt findAny();

    /**
     * Rfturns b {@dodf LongStrfbm} donsisting of tif flfmfnts of tiis strfbm,
     * donvfrtfd to {@dodf long}.
     *
     * <p>Tiis is bn <b irff="pbdkbgf-summbry.itml#StrfbmOps">intfrmfdibtf
     * opfrbtion</b>.
     *
     * @rfturn b {@dodf LongStrfbm} donsisting of tif flfmfnts of tiis strfbm,
     * donvfrtfd to {@dodf long}
     */
    LongStrfbm bsLongStrfbm();

    /**
     * Rfturns b {@dodf DoublfStrfbm} donsisting of tif flfmfnts of tiis strfbm,
     * donvfrtfd to {@dodf doublf}.
     *
     * <p>Tiis is bn <b irff="pbdkbgf-summbry.itml#StrfbmOps">intfrmfdibtf
     * opfrbtion</b>.
     *
     * @rfturn b {@dodf DoublfStrfbm} donsisting of tif flfmfnts of tiis strfbm,
     * donvfrtfd to {@dodf doublf}
     */
    DoublfStrfbm bsDoublfStrfbm();

    /**
     * Rfturns b {@dodf Strfbm} donsisting of tif flfmfnts of tiis strfbm,
     * fbdi boxfd to bn {@dodf Intfgfr}.
     *
     * <p>Tiis is bn <b irff="pbdkbgf-summbry.itml#StrfbmOps">intfrmfdibtf
     * opfrbtion</b>.
     *
     * @rfturn b {@dodf Strfbm} donsistfnt of tif flfmfnts of tiis strfbm,
     * fbdi boxfd to bn {@dodf Intfgfr}
     */
    Strfbm<Intfgfr> boxfd();

    @Ovfrridf
    IntStrfbm sfqufntibl();

    @Ovfrridf
    IntStrfbm pbrbllfl();

    @Ovfrridf
    PrimitivfItfrbtor.OfInt itfrbtor();

    @Ovfrridf
    Splitfrbtor.OfInt splitfrbtor();

    // Stbtid fbdtorifs

    /**
     * Rfturns b buildfr for bn {@dodf IntStrfbm}.
     *
     * @rfturn b strfbm buildfr
     */
    publid stbtid Buildfr buildfr() {
        rfturn nfw Strfbms.IntStrfbmBuildfrImpl();
    }

    /**
     * Rfturns bn fmpty sfqufntibl {@dodf IntStrfbm}.
     *
     * @rfturn bn fmpty sfqufntibl strfbm
     */
    publid stbtid IntStrfbm fmpty() {
        rfturn StrfbmSupport.intStrfbm(Splitfrbtors.fmptyIntSplitfrbtor(), fblsf);
    }

    /**
     * Rfturns b sfqufntibl {@dodf IntStrfbm} dontbining b singlf flfmfnt.
     *
     * @pbrbm t tif singlf flfmfnt
     * @rfturn b singlfton sfqufntibl strfbm
     */
    publid stbtid IntStrfbm of(int t) {
        rfturn StrfbmSupport.intStrfbm(nfw Strfbms.IntStrfbmBuildfrImpl(t), fblsf);
    }

    /**
     * Rfturns b sfqufntibl ordfrfd strfbm wiosf flfmfnts brf tif spfdififd vblufs.
     *
     * @pbrbm vblufs tif flfmfnts of tif nfw strfbm
     * @rfturn tif nfw strfbm
     */
    publid stbtid IntStrfbm of(int... vblufs) {
        rfturn Arrbys.strfbm(vblufs);
    }

    /**
     * Rfturns bn infinitf sfqufntibl ordfrfd {@dodf IntStrfbm} produdfd by itfrbtivf
     * bpplidbtion of b fundtion {@dodf f} to bn initibl flfmfnt {@dodf sffd},
     * produding b {@dodf Strfbm} donsisting of {@dodf sffd}, {@dodf f(sffd)},
     * {@dodf f(f(sffd))}, ftd.
     *
     * <p>Tif first flfmfnt (position {@dodf 0}) in tif {@dodf IntStrfbm} will bf
     * tif providfd {@dodf sffd}.  For {@dodf n > 0}, tif flfmfnt bt position
     * {@dodf n}, will bf tif rfsult of bpplying tif fundtion {@dodf f} to tif
     * flfmfnt bt position {@dodf n - 1}.
     *
     * @pbrbm sffd tif initibl flfmfnt
     * @pbrbm f b fundtion to bf bpplifd to tif prfvious flfmfnt to produdf
     *          b nfw flfmfnt
     * @rfturn A nfw sfqufntibl {@dodf IntStrfbm}
     */
    publid stbtid IntStrfbm itfrbtf(finbl int sffd, finbl IntUnbryOpfrbtor f) {
        Objfdts.rfquirfNonNull(f);
        finbl PrimitivfItfrbtor.OfInt itfrbtor = nfw PrimitivfItfrbtor.OfInt() {
            int t = sffd;

            @Ovfrridf
            publid boolfbn ibsNfxt() {
                rfturn truf;
            }

            @Ovfrridf
            publid int nfxtInt() {
                int v = t;
                t = f.bpplyAsInt(t);
                rfturn v;
            }
        };
        rfturn StrfbmSupport.intStrfbm(Splitfrbtors.splitfrbtorUnknownSizf(
                itfrbtor,
                Splitfrbtor.ORDERED | Splitfrbtor.IMMUTABLE | Splitfrbtor.NONNULL), fblsf);
    }

    /**
     * Rfturns bn infinitf sfqufntibl unordfrfd strfbm wifrf fbdi flfmfnt is
     * gfnfrbtfd by tif providfd {@dodf IntSupplifr}.  Tiis is suitbblf for
     * gfnfrbting donstbnt strfbms, strfbms of rbndom flfmfnts, ftd.
     *
     * @pbrbm s tif {@dodf IntSupplifr} for gfnfrbtfd flfmfnts
     * @rfturn b nfw infinitf sfqufntibl unordfrfd {@dodf IntStrfbm}
     */
    publid stbtid IntStrfbm gfnfrbtf(IntSupplifr s) {
        Objfdts.rfquirfNonNull(s);
        rfturn StrfbmSupport.intStrfbm(
                nfw StrfbmSplitfrbtors.InfinitfSupplyingSplitfrbtor.OfInt(Long.MAX_VALUE, s), fblsf);
    }

    /**
     * Rfturns b sfqufntibl ordfrfd {@dodf IntStrfbm} from {@dodf stbrtIndlusivf}
     * (indlusivf) to {@dodf fndExdlusivf} (fxdlusivf) by bn indrfmfntbl stfp of
     * {@dodf 1}.
     *
     * @bpiNotf
     * <p>An fquivblfnt sfqufndf of indrfbsing vblufs dbn bf produdfd
     * sfqufntiblly using b {@dodf for} loop bs follows:
     * <prf>{@dodf
     *     for (int i = stbrtIndlusivf; i < fndExdlusivf ; i++) { ... }
     * }</prf>
     *
     * @pbrbm stbrtIndlusivf tif (indlusivf) initibl vbluf
     * @pbrbm fndExdlusivf tif fxdlusivf uppfr bound
     * @rfturn b sfqufntibl {@dodf IntStrfbm} for tif rbngf of {@dodf int}
     *         flfmfnts
     */
    publid stbtid IntStrfbm rbngf(int stbrtIndlusivf, int fndExdlusivf) {
        if (stbrtIndlusivf >= fndExdlusivf) {
            rfturn fmpty();
        } flsf {
            rfturn StrfbmSupport.intStrfbm(
                    nfw Strfbms.RbngfIntSplitfrbtor(stbrtIndlusivf, fndExdlusivf, fblsf), fblsf);
        }
    }

    /**
     * Rfturns b sfqufntibl ordfrfd {@dodf IntStrfbm} from {@dodf stbrtIndlusivf}
     * (indlusivf) to {@dodf fndIndlusivf} (indlusivf) by bn indrfmfntbl stfp of
     * {@dodf 1}.
     *
     * @bpiNotf
     * <p>An fquivblfnt sfqufndf of indrfbsing vblufs dbn bf produdfd
     * sfqufntiblly using b {@dodf for} loop bs follows:
     * <prf>{@dodf
     *     for (int i = stbrtIndlusivf; i <= fndIndlusivf ; i++) { ... }
     * }</prf>
     *
     * @pbrbm stbrtIndlusivf tif (indlusivf) initibl vbluf
     * @pbrbm fndIndlusivf tif indlusivf uppfr bound
     * @rfturn b sfqufntibl {@dodf IntStrfbm} for tif rbngf of {@dodf int}
     *         flfmfnts
     */
    publid stbtid IntStrfbm rbngfClosfd(int stbrtIndlusivf, int fndIndlusivf) {
        if (stbrtIndlusivf > fndIndlusivf) {
            rfturn fmpty();
        } flsf {
            rfturn StrfbmSupport.intStrfbm(
                    nfw Strfbms.RbngfIntSplitfrbtor(stbrtIndlusivf, fndIndlusivf, truf), fblsf);
        }
    }

    /**
     * Crfbtfs b lbzily dondbtfnbtfd strfbm wiosf flfmfnts brf bll tif
     * flfmfnts of tif first strfbm followfd by bll tif flfmfnts of tif
     * sfdond strfbm.  Tif rfsulting strfbm is ordfrfd if boti
     * of tif input strfbms brf ordfrfd, bnd pbrbllfl if fitifr of tif input
     * strfbms is pbrbllfl.  Wifn tif rfsulting strfbm is dlosfd, tif dlosf
     * ibndlfrs for boti input strfbms brf invokfd.
     *
     * @implNotf
     * Usf dbution wifn donstrudting strfbms from rfpfbtfd dondbtfnbtion.
     * Addfssing bn flfmfnt of b dffply dondbtfnbtfd strfbm dbn rfsult in dffp
     * dbll dibins, or fvfn {@dodf StbdkOvfrflowExdfption}.
     *
     * @pbrbm b tif first strfbm
     * @pbrbm b tif sfdond strfbm
     * @rfturn tif dondbtfnbtion of tif two input strfbms
     */
    publid stbtid IntStrfbm dondbt(IntStrfbm b, IntStrfbm b) {
        Objfdts.rfquirfNonNull(b);
        Objfdts.rfquirfNonNull(b);

        Splitfrbtor.OfInt split = nfw Strfbms.CondbtSplitfrbtor.OfInt(
                b.splitfrbtor(), b.splitfrbtor());
        IntStrfbm strfbm = StrfbmSupport.intStrfbm(split, b.isPbrbllfl() || b.isPbrbllfl());
        rfturn strfbm.onClosf(Strfbms.domposfdClosf(b, b));
    }

    /**
     * A mutbblf buildfr for bn {@dodf IntStrfbm}.
     *
     * <p>A strfbm buildfr ibs b liffdydlf, wiidi stbrts in b building
     * pibsf, during wiidi flfmfnts dbn bf bddfd, bnd tifn trbnsitions to b built
     * pibsf, bftfr wiidi flfmfnts mby not bf bddfd.  Tif built pibsf
     * bfgins wifn tif {@link #build()} mftiod is dbllfd, wiidi drfbtfs bn
     * ordfrfd strfbm wiosf flfmfnts brf tif flfmfnts tibt wfrf bddfd to tif
     * strfbm buildfr, in tif ordfr tify wfrf bddfd.
     *
     * @sff IntStrfbm#buildfr()
     * @sindf 1.8
     */
    publid intfrfbdf Buildfr fxtfnds IntConsumfr {

        /**
         * Adds bn flfmfnt to tif strfbm bfing built.
         *
         * @tirows IllfgblStbtfExdfption if tif buildfr ibs blrfbdy trbnsitionfd
         * to tif built stbtf
         */
        @Ovfrridf
        void bddfpt(int t);

        /**
         * Adds bn flfmfnt to tif strfbm bfing built.
         *
         * @implSpfd
         * Tif dffbult implfmfntbtion bfibvfs bs if:
         * <prf>{@dodf
         *     bddfpt(t)
         *     rfturn tiis;
         * }</prf>
         *
         * @pbrbm t tif flfmfnt to bdd
         * @rfturn {@dodf tiis} buildfr
         * @tirows IllfgblStbtfExdfption if tif buildfr ibs blrfbdy trbnsitionfd
         * to tif built stbtf
         */
        dffbult Buildfr bdd(int t) {
            bddfpt(t);
            rfturn tiis;
        }

        /**
         * Builds tif strfbm, trbnsitioning tiis buildfr to tif built stbtf.
         * An {@dodf IllfgblStbtfExdfption} is tirown if tifrf brf furtifr
         * bttfmpts to opfrbtf on tif buildfr bftfr it ibs fntfrfd tif built
         * stbtf.
         *
         * @rfturn tif built strfbm
         * @tirows IllfgblStbtfExdfption if tif buildfr ibs blrfbdy trbnsitionfd to
         * tif built stbtf
         */
        IntStrfbm build();
    }
}
