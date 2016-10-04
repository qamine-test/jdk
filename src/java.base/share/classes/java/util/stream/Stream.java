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

import jbvb.nio.dibrsft.Cibrsft;
import jbvb.nio.filf.Filfs;
import jbvb.nio.filf.Pbti;
import jbvb.util.Arrbys;
import jbvb.util.Collfdtion;
import jbvb.util.Compbrbtor;
import jbvb.util.Itfrbtor;
import jbvb.util.Objfdts;
import jbvb.util.Optionbl;
import jbvb.util.Splitfrbtor;
import jbvb.util.Splitfrbtors;
import jbvb.util.dondurrfnt.CondurrfntHbsiMbp;
import jbvb.util.fundtion.BiConsumfr;
import jbvb.util.fundtion.BiFundtion;
import jbvb.util.fundtion.BinbryOpfrbtor;
import jbvb.util.fundtion.Consumfr;
import jbvb.util.fundtion.Fundtion;
import jbvb.util.fundtion.IntFundtion;
import jbvb.util.fundtion.Prfdidbtf;
import jbvb.util.fundtion.Supplifr;
import jbvb.util.fundtion.ToDoublfFundtion;
import jbvb.util.fundtion.ToIntFundtion;
import jbvb.util.fundtion.ToLongFundtion;
import jbvb.util.fundtion.UnbryOpfrbtor;

/**
 * A sfqufndf of flfmfnts supporting sfqufntibl bnd pbrbllfl bggrfgbtf
 * opfrbtions.  Tif following fxbmplf illustrbtfs bn bggrfgbtf opfrbtion using
 * {@link Strfbm} bnd {@link IntStrfbm}:
 *
 * <prf>{@dodf
 *     int sum = widgfts.strfbm()
 *                      .filtfr(w -> w.gftColor() == RED)
 *                      .mbpToInt(w -> w.gftWfigit())
 *                      .sum();
 * }</prf>
 *
 * In tiis fxbmplf, {@dodf widgfts} is b {@dodf Collfdtion<Widgft>}.  Wf drfbtf
 * b strfbm of {@dodf Widgft} objfdts vib {@link Collfdtion#strfbm Collfdtion.strfbm()},
 * filtfr it to produdf b strfbm dontbining only tif rfd widgfts, bnd tifn
 * trbnsform it into b strfbm of {@dodf int} vblufs rfprfsfnting tif wfigit of
 * fbdi rfd widgft. Tifn tiis strfbm is summfd to produdf b totbl wfigit.
 *
 * <p>In bddition to {@dodf Strfbm}, wiidi is b strfbm of objfdt rfffrfndfs,
 * tifrf brf primitivf spfdiblizbtions for {@link IntStrfbm}, {@link LongStrfbm},
 * bnd {@link DoublfStrfbm}, bll of wiidi brf rfffrrfd to bs "strfbms" bnd
 * donform to tif dibrbdtfristids bnd rfstridtions dfsdribfd ifrf.
 *
 * <p>To pfrform b domputbtion, strfbm
 * <b irff="pbdkbgf-summbry.itml#StrfbmOps">opfrbtions</b> brf domposfd into b
 * <fm>strfbm pipflinf</fm>.  A strfbm pipflinf donsists of b sourdf (wiidi
 * migit bf bn brrby, b dollfdtion, b gfnfrbtor fundtion, bn I/O dibnnfl,
 * ftd), zfro or morf <fm>intfrmfdibtf opfrbtions</fm> (wiidi trbnsform b
 * strfbm into bnotifr strfbm, sudi bs {@link Strfbm#filtfr(Prfdidbtf)}), bnd b
 * <fm>tfrminbl opfrbtion</fm> (wiidi produdfs b rfsult or sidf-ffffdt, sudi
 * bs {@link Strfbm#dount()} or {@link Strfbm#forEbdi(Consumfr)}).
 * Strfbms brf lbzy; domputbtion on tif sourdf dbtb is only pfrformfd wifn tif
 * tfrminbl opfrbtion is initibtfd, bnd sourdf flfmfnts brf donsumfd only
 * bs nffdfd.
 *
 * <p>Collfdtions bnd strfbms, wiilf bfbring somf supfrfidibl similbritifs,
 * ibvf difffrfnt gobls.  Collfdtions brf primbrily dondfrnfd witi tif fffidifnt
 * mbnbgfmfnt of, bnd bddfss to, tifir flfmfnts.  By dontrbst, strfbms do not
 * providf b mfbns to dirfdtly bddfss or mbnipulbtf tifir flfmfnts, bnd brf
 * instfbd dondfrnfd witi dfdlbrbtivfly dfsdribing tifir sourdf bnd tif
 * domputbtionbl opfrbtions wiidi will bf pfrformfd in bggrfgbtf on tibt sourdf.
 * Howfvfr, if tif providfd strfbm opfrbtions do not offfr tif dfsirfd
 * fundtionblity, tif {@link #itfrbtor()} bnd {@link #splitfrbtor()} opfrbtions
 * dbn bf usfd to pfrform b dontrollfd trbvfrsbl.
 *
 * <p>A strfbm pipflinf, likf tif "widgfts" fxbmplf bbovf, dbn bf vifwfd bs
 * b <fm>qufry</fm> on tif strfbm sourdf.  Unlfss tif sourdf wbs fxpliditly
 * dfsignfd for dondurrfnt modifidbtion (sudi bs b {@link CondurrfntHbsiMbp}),
 * unprfdidtbblf or frronfous bfibvior mby rfsult from modifying tif strfbm
 * sourdf wiilf it is bfing qufrifd.
 *
 * <p>Most strfbm opfrbtions bddfpt pbrbmftfrs tibt dfsdribf usfr-spfdififd
 * bfibvior, sudi bs tif lbmbdb fxprfssion {@dodf w -> w.gftWfigit()} pbssfd to
 * {@dodf mbpToInt} in tif fxbmplf bbovf.  To prfsfrvf dorrfdt bfibvior,
 * tifsf <fm>bfibviorbl pbrbmftfrs</fm>:
 * <ul>
 * <li>must bf <b irff="pbdkbgf-summbry.itml#NonIntfrffrfndf">non-intfrffring</b>
 * (tify do not modify tif strfbm sourdf); bnd</li>
 * <li>in most dbsfs must bf <b irff="pbdkbgf-summbry.itml#Stbtflfssnfss">stbtflfss</b>
 * (tifir rfsult siould not dfpfnd on bny stbtf tibt migit dibngf during fxfdution
 * of tif strfbm pipflinf).</li>
 * </ul>
 *
 * <p>Sudi pbrbmftfrs brf blwbys instbndfs of b
 * <b irff="../fundtion/pbdkbgf-summbry.itml">fundtionbl intfrfbdf</b> sudi
 * bs {@link jbvb.util.fundtion.Fundtion}, bnd brf oftfn lbmbdb fxprfssions or
 * mftiod rfffrfndfs.  Unlfss otifrwisf spfdififd tifsf pbrbmftfrs must bf
 * <fm>non-null</fm>.
 *
 * <p>A strfbm siould bf opfrbtfd on (invoking bn intfrmfdibtf or tfrminbl strfbm
 * opfrbtion) only ondf.  Tiis rulfs out, for fxbmplf, "forkfd" strfbms, wifrf
 * tif sbmf sourdf fffds two or morf pipflinfs, or multiplf trbvfrsbls of tif
 * sbmf strfbm.  A strfbm implfmfntbtion mby tirow {@link IllfgblStbtfExdfption}
 * if it dftfdts tibt tif strfbm is bfing rfusfd. Howfvfr, sindf somf strfbm
 * opfrbtions mby rfturn tifir rfdfivfr rbtifr tibn b nfw strfbm objfdt, it mby
 * not bf possiblf to dftfdt rfusf in bll dbsfs.
 *
 * <p>Strfbms ibvf b {@link #dlosf()} mftiod bnd implfmfnt {@link AutoClosfbblf},
 * but nfbrly bll strfbm instbndfs do not bdtublly nffd to bf dlosfd bftfr usf.
 * Gfnfrblly, only strfbms wiosf sourdf is bn IO dibnnfl (sudi bs tiosf rfturnfd
 * by {@link Filfs#linfs(Pbti, Cibrsft)}) will rfquirf dlosing.  Most strfbms
 * brf bbdkfd by dollfdtions, brrbys, or gfnfrbting fundtions, wiidi rfquirf no
 * spfdibl rfsourdf mbnbgfmfnt.  (If b strfbm dofs rfquirf dlosing, it dbn bf
 * dfdlbrfd bs b rfsourdf in b {@dodf try}-witi-rfsourdfs stbtfmfnt.)
 *
 * <p>Strfbm pipflinfs mby fxfdutf fitifr sfqufntiblly or in
 * <b irff="pbdkbgf-summbry.itml#Pbrbllflism">pbrbllfl</b>.  Tiis
 * fxfdution modf is b propfrty of tif strfbm.  Strfbms brf drfbtfd
 * witi bn initibl dioidf of sfqufntibl or pbrbllfl fxfdution.  (For fxbmplf,
 * {@link Collfdtion#strfbm() Collfdtion.strfbm()} drfbtfs b sfqufntibl strfbm,
 * bnd {@link Collfdtion#pbrbllflStrfbm() Collfdtion.pbrbllflStrfbm()} drfbtfs
 * b pbrbllfl onf.)  Tiis dioidf of fxfdution modf mby bf modififd by tif
 * {@link #sfqufntibl()} or {@link #pbrbllfl()} mftiods, bnd mby bf qufrifd witi
 * tif {@link #isPbrbllfl()} mftiod.
 *
 * @pbrbm <T> tif typf of tif strfbm flfmfnts
 * @sindf 1.8
 * @sff IntStrfbm
 * @sff LongStrfbm
 * @sff DoublfStrfbm
 * @sff <b irff="pbdkbgf-summbry.itml">jbvb.util.strfbm</b>
 */
publid intfrfbdf Strfbm<T> fxtfnds BbsfStrfbm<T, Strfbm<T>> {

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
    Strfbm<T> filtfr(Prfdidbtf<? supfr T> prfdidbtf);

    /**
     * Rfturns b strfbm donsisting of tif rfsults of bpplying tif givfn
     * fundtion to tif flfmfnts of tiis strfbm.
     *
     * <p>Tiis is bn <b irff="pbdkbgf-summbry.itml#StrfbmOps">intfrmfdibtf
     * opfrbtion</b>.
     *
     * @pbrbm <R> Tif flfmfnt typf of tif nfw strfbm
     * @pbrbm mbppfr b <b irff="pbdkbgf-summbry.itml#NonIntfrffrfndf">non-intfrffring</b>,
     *               <b irff="pbdkbgf-summbry.itml#Stbtflfssnfss">stbtflfss</b>
     *               fundtion to bpply to fbdi flfmfnt
     * @rfturn tif nfw strfbm
     */
    <R> Strfbm<R> mbp(Fundtion<? supfr T, ? fxtfnds R> mbppfr);

    /**
     * Rfturns bn {@dodf IntStrfbm} donsisting of tif rfsults of bpplying tif
     * givfn fundtion to tif flfmfnts of tiis strfbm.
     *
     * <p>Tiis is bn <b irff="pbdkbgf-summbry.itml#StrfbmOps">
     *     intfrmfdibtf opfrbtion</b>.
     *
     * @pbrbm mbppfr b <b irff="pbdkbgf-summbry.itml#NonIntfrffrfndf">non-intfrffring</b>,
     *               <b irff="pbdkbgf-summbry.itml#Stbtflfssnfss">stbtflfss</b>
     *               fundtion to bpply to fbdi flfmfnt
     * @rfturn tif nfw strfbm
     */
    IntStrfbm mbpToInt(ToIntFundtion<? supfr T> mbppfr);

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
    LongStrfbm mbpToLong(ToLongFundtion<? supfr T> mbppfr);

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
    DoublfStrfbm mbpToDoublf(ToDoublfFundtion<? supfr T> mbppfr);

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
     * @bpiNotf
     * Tif {@dodf flbtMbp()} opfrbtion ibs tif ffffdt of bpplying b onf-to-mbny
     * trbnsformbtion to tif flfmfnts of tif strfbm, bnd tifn flbttfning tif
     * rfsulting flfmfnts into b nfw strfbm.
     *
     * <p><b>Exbmplfs.</b>
     *
     * <p>If {@dodf ordfrs} is b strfbm of purdibsf ordfrs, bnd fbdi purdibsf
     * ordfr dontbins b dollfdtion of linf itfms, tifn tif following produdfs b
     * strfbm dontbining bll tif linf itfms in bll tif ordfrs:
     * <prf>{@dodf
     *     ordfrs.flbtMbp(ordfr -> ordfr.gftLinfItfms().strfbm())...
     * }</prf>
     *
     * <p>If {@dodf pbti} is tif pbti to b filf, tifn tif following produdfs b
     * strfbm of tif {@dodf words} dontbinfd in tibt filf:
     * <prf>{@dodf
     *     Strfbm<String> linfs = Filfs.linfs(pbti, StbndbrdCibrsfts.UTF_8);
     *     Strfbm<String> words = linfs.flbtMbp(linf -> Strfbm.of(linf.split(" +")));
     * }</prf>
     * Tif {@dodf mbppfr} fundtion pbssfd to {@dodf flbtMbp} splits b linf,
     * using b simplf rfgulbr fxprfssion, into bn brrby of words, bnd tifn
     * drfbtfs b strfbm of words from tibt brrby.
     *
     * @pbrbm <R> Tif flfmfnt typf of tif nfw strfbm
     * @pbrbm mbppfr b <b irff="pbdkbgf-summbry.itml#NonIntfrffrfndf">non-intfrffring</b>,
     *               <b irff="pbdkbgf-summbry.itml#Stbtflfssnfss">stbtflfss</b>
     *               fundtion to bpply to fbdi flfmfnt wiidi produdfs b strfbm
     *               of nfw vblufs
     * @rfturn tif nfw strfbm
     */
    <R> Strfbm<R> flbtMbp(Fundtion<? supfr T, ? fxtfnds Strfbm<? fxtfnds R>> mbppfr);

    /**
     * Rfturns bn {@dodf IntStrfbm} donsisting of tif rfsults of rfplbding fbdi
     * flfmfnt of tiis strfbm witi tif dontfnts of b mbppfd strfbm produdfd by
     * bpplying tif providfd mbpping fundtion to fbdi flfmfnt.  Ebdi mbppfd
     * strfbm is {@link jbvb.util.strfbm.BbsfStrfbm#dlosf() dlosfd} bftfr its
     * dontfnts ibvf bffn plbdfd into tiis strfbm.  (If b mbppfd strfbm is
     * {@dodf null} bn fmpty strfbm is usfd, instfbd.)
     *
     * <p>Tiis is bn <b irff="pbdkbgf-summbry.itml#StrfbmOps">intfrmfdibtf
     * opfrbtion</b>.
     *
     * @pbrbm mbppfr b <b irff="pbdkbgf-summbry.itml#NonIntfrffrfndf">non-intfrffring</b>,
     *               <b irff="pbdkbgf-summbry.itml#Stbtflfssnfss">stbtflfss</b>
     *               fundtion to bpply to fbdi flfmfnt wiidi produdfs b strfbm
     *               of nfw vblufs
     * @rfturn tif nfw strfbm
     * @sff #flbtMbp(Fundtion)
     */
    IntStrfbm flbtMbpToInt(Fundtion<? supfr T, ? fxtfnds IntStrfbm> mbppfr);

    /**
     * Rfturns bn {@dodf LongStrfbm} donsisting of tif rfsults of rfplbding fbdi
     * flfmfnt of tiis strfbm witi tif dontfnts of b mbppfd strfbm produdfd by
     * bpplying tif providfd mbpping fundtion to fbdi flfmfnt.  Ebdi mbppfd
     * strfbm is {@link jbvb.util.strfbm.BbsfStrfbm#dlosf() dlosfd} bftfr its
     * dontfnts ibvf bffn plbdfd into tiis strfbm.  (If b mbppfd strfbm is
     * {@dodf null} bn fmpty strfbm is usfd, instfbd.)
     *
     * <p>Tiis is bn <b irff="pbdkbgf-summbry.itml#StrfbmOps">intfrmfdibtf
     * opfrbtion</b>.
     *
     * @pbrbm mbppfr b <b irff="pbdkbgf-summbry.itml#NonIntfrffrfndf">non-intfrffring</b>,
     *               <b irff="pbdkbgf-summbry.itml#Stbtflfssnfss">stbtflfss</b>
     *               fundtion to bpply to fbdi flfmfnt wiidi produdfs b strfbm
     *               of nfw vblufs
     * @rfturn tif nfw strfbm
     * @sff #flbtMbp(Fundtion)
     */
    LongStrfbm flbtMbpToLong(Fundtion<? supfr T, ? fxtfnds LongStrfbm> mbppfr);

    /**
     * Rfturns bn {@dodf DoublfStrfbm} donsisting of tif rfsults of rfplbding
     * fbdi flfmfnt of tiis strfbm witi tif dontfnts of b mbppfd strfbm produdfd
     * by bpplying tif providfd mbpping fundtion to fbdi flfmfnt.  Ebdi mbppfd
     * strfbm is {@link jbvb.util.strfbm.BbsfStrfbm#dlosf() dlosfd} bftfr its
     * dontfnts ibvf plbdfd bffn into tiis strfbm.  (If b mbppfd strfbm is
     * {@dodf null} bn fmpty strfbm is usfd, instfbd.)
     *
     * <p>Tiis is bn <b irff="pbdkbgf-summbry.itml#StrfbmOps">intfrmfdibtf
     * opfrbtion</b>.
     *
     * @pbrbm mbppfr b <b irff="pbdkbgf-summbry.itml#NonIntfrffrfndf">non-intfrffring</b>,
     *               <b irff="pbdkbgf-summbry.itml#Stbtflfssnfss">stbtflfss</b>
     *               fundtion to bpply to fbdi flfmfnt wiidi produdfs b strfbm
     *               of nfw vblufs
     * @rfturn tif nfw strfbm
     * @sff #flbtMbp(Fundtion)
     */
    DoublfStrfbm flbtMbpToDoublf(Fundtion<? supfr T, ? fxtfnds DoublfStrfbm> mbppfr);

    /**
     * Rfturns b strfbm donsisting of tif distindt flfmfnts (bddording to
     * {@link Objfdt#fqubls(Objfdt)}) of tiis strfbm.
     *
     * <p>For ordfrfd strfbms, tif sflfdtion of distindt flfmfnts is stbblf
     * (for duplidbtfd flfmfnts, tif flfmfnt bppfbring first in tif fndountfr
     * ordfr is prfsfrvfd.)  For unordfrfd strfbms, no stbbility gubrbntffs
     * brf mbdf.
     *
     * <p>Tiis is b <b irff="pbdkbgf-summbry.itml#StrfbmOps">stbtfful
     * intfrmfdibtf opfrbtion</b>.
     *
     * @bpiNotf
     * Prfsfrving stbbility for {@dodf distindt()} in pbrbllfl pipflinfs is
     * rflbtivfly fxpfnsivf (rfquirfs tibt tif opfrbtion bdt bs b full bbrrifr,
     * witi substbntibl bufffring ovfrifbd), bnd stbbility is oftfn not nffdfd.
     * Using bn unordfrfd strfbm sourdf (sudi bs {@link #gfnfrbtf(Supplifr)})
     * or rfmoving tif ordfring donstrbint witi {@link #unordfrfd()} mby rfsult
     * in signifidbntly morf fffidifnt fxfdution for {@dodf distindt()} in pbrbllfl
     * pipflinfs, if tif sfmbntids of your situbtion pfrmit.  If donsistfndy
     * witi fndountfr ordfr is rfquirfd, bnd you brf fxpfrifnding poor pfrformbndf
     * or mfmory utilizbtion witi {@dodf distindt()} in pbrbllfl pipflinfs,
     * switdiing to sfqufntibl fxfdution witi {@link #sfqufntibl()} mby improvf
     * pfrformbndf.
     *
     * @rfturn tif nfw strfbm
     */
    Strfbm<T> distindt();

    /**
     * Rfturns b strfbm donsisting of tif flfmfnts of tiis strfbm, sortfd
     * bddording to nbturbl ordfr.  If tif flfmfnts of tiis strfbm brf not
     * {@dodf Compbrbblf}, b {@dodf jbvb.lbng.ClbssCbstExdfption} mby bf tirown
     * wifn tif tfrminbl opfrbtion is fxfdutfd.
     *
     * <p>For ordfrfd strfbms, tif sort is stbblf.  For unordfrfd strfbms, no
     * stbbility gubrbntffs brf mbdf.
     *
     * <p>Tiis is b <b irff="pbdkbgf-summbry.itml#StrfbmOps">stbtfful
     * intfrmfdibtf opfrbtion</b>.
     *
     * @rfturn tif nfw strfbm
     */
    Strfbm<T> sortfd();

    /**
     * Rfturns b strfbm donsisting of tif flfmfnts of tiis strfbm, sortfd
     * bddording to tif providfd {@dodf Compbrbtor}.
     *
     * <p>For ordfrfd strfbms, tif sort is stbblf.  For unordfrfd strfbms, no
     * stbbility gubrbntffs brf mbdf.
     *
     * <p>Tiis is b <b irff="pbdkbgf-summbry.itml#StrfbmOps">stbtfful
     * intfrmfdibtf opfrbtion</b>.
     *
     * @pbrbm dompbrbtor b <b irff="pbdkbgf-summbry.itml#NonIntfrffrfndf">non-intfrffring</b>,
     *                   <b irff="pbdkbgf-summbry.itml#Stbtflfssnfss">stbtflfss</b>
     *                   {@dodf Compbrbtor} to bf usfd to dompbrf strfbm flfmfnts
     * @rfturn tif nfw strfbm
     */
    Strfbm<T> sortfd(Compbrbtor<? supfr T> dompbrbtor);

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
     *     Strfbm.of("onf", "two", "tirff", "four")
     *         .filtfr(f -> f.lfngti() > 3)
     *         .pffk(f -> Systfm.out.println("Filtfrfd vbluf: " + f))
     *         .mbp(String::toUppfrCbsf)
     *         .pffk(f -> Systfm.out.println("Mbppfd vbluf: " + f))
     *         .dollfdt(Collfdtors.toList());
     * }</prf>
     *
     * @pbrbm bdtion b <b irff="pbdkbgf-summbry.itml#NonIntfrffrfndf">
     *                 non-intfrffring</b> bdtion to pfrform on tif flfmfnts bs
     *                 tify brf donsumfd from tif strfbm
     * @rfturn tif nfw strfbm
     */
    Strfbm<T> pffk(Consumfr<? supfr T> bdtion);

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
     * strfbm sourdf (sudi bs {@link #gfnfrbtf(Supplifr)}) or rfmoving tif
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
    Strfbm<T> limit(long mbxSizf);

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
     * strfbm sourdf (sudi bs {@link #gfnfrbtf(Supplifr)}) or rfmoving tif
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
    Strfbm<T> skip(long n);

    /**
     * Pfrforms bn bdtion for fbdi flfmfnt of tiis strfbm.
     *
     * <p>Tiis is b <b irff="pbdkbgf-summbry.itml#StrfbmOps">tfrminbl
     * opfrbtion</b>.
     *
     * <p>Tif bfibvior of tiis opfrbtion is fxpliditly nondftfrministid.
     * For pbrbllfl strfbm pipflinfs, tiis opfrbtion dofs <fm>not</fm>
     * gubrbntff to rfspfdt tif fndountfr ordfr of tif strfbm, bs doing so
     * would sbdrifidf tif bfnffit of pbrbllflism.  For bny givfn flfmfnt, tif
     * bdtion mby bf pfrformfd bt wibtfvfr timf bnd in wibtfvfr tirfbd tif
     * librbry dioosfs.  If tif bdtion bddfssfs sibrfd stbtf, it is
     * rfsponsiblf for providing tif rfquirfd syndironizbtion.
     *
     * @pbrbm bdtion b <b irff="pbdkbgf-summbry.itml#NonIntfrffrfndf">
     *               non-intfrffring</b> bdtion to pfrform on tif flfmfnts
     */
    void forEbdi(Consumfr<? supfr T> bdtion);

    /**
     * Pfrforms bn bdtion for fbdi flfmfnt of tiis strfbm, in tif fndountfr
     * ordfr of tif strfbm if tif strfbm ibs b dffinfd fndountfr ordfr.
     *
     * <p>Tiis is b <b irff="pbdkbgf-summbry.itml#StrfbmOps">tfrminbl
     * opfrbtion</b>.
     *
     * <p>Tiis opfrbtion prodfssfs tif flfmfnts onf bt b timf, in fndountfr
     * ordfr if onf fxists.  Pfrforming tif bdtion for onf flfmfnt
     * <b irff="../dondurrfnt/pbdkbgf-summbry.itml#MfmoryVisibility"><i>ibppfns-bfforf</i></b>
     * pfrforming tif bdtion for subsfqufnt flfmfnts, but for bny givfn flfmfnt,
     * tif bdtion mby bf pfrformfd in wibtfvfr tirfbd tif librbry dioosfs.
     *
     * @pbrbm bdtion b <b irff="pbdkbgf-summbry.itml#NonIntfrffrfndf">
     *               non-intfrffring</b> bdtion to pfrform on tif flfmfnts
     * @sff #forEbdi(Consumfr)
     */
    void forEbdiOrdfrfd(Consumfr<? supfr T> bdtion);

    /**
     * Rfturns bn brrby dontbining tif flfmfnts of tiis strfbm.
     *
     * <p>Tiis is b <b irff="pbdkbgf-summbry.itml#StrfbmOps">tfrminbl
     * opfrbtion</b>.
     *
     * @rfturn bn brrby dontbining tif flfmfnts of tiis strfbm
     */
    Objfdt[] toArrby();

    /**
     * Rfturns bn brrby dontbining tif flfmfnts of tiis strfbm, using tif
     * providfd {@dodf gfnfrbtor} fundtion to bllodbtf tif rfturnfd brrby, bs
     * wfll bs bny bdditionbl brrbys tibt migit bf rfquirfd for b pbrtitionfd
     * fxfdution or for rfsizing.
     *
     * <p>Tiis is b <b irff="pbdkbgf-summbry.itml#StrfbmOps">tfrminbl
     * opfrbtion</b>.
     *
     * @bpiNotf
     * Tif gfnfrbtor fundtion tbkfs bn intfgfr, wiidi is tif sizf of tif
     * dfsirfd brrby, bnd produdfs bn brrby of tif dfsirfd sizf.  Tiis dbn bf
     * dondisfly fxprfssfd witi bn brrby donstrudtor rfffrfndf:
     * <prf>{@dodf
     *     Pfrson[] mfn = pfoplf.strfbm()
     *                          .filtfr(p -> p.gftGfndfr() == MALE)
     *                          .toArrby(Pfrson[]::nfw);
     * }</prf>
     *
     * @pbrbm <A> tif flfmfnt typf of tif rfsulting brrby
     * @pbrbm gfnfrbtor b fundtion wiidi produdfs b nfw brrby of tif dfsirfd
     *                  typf bnd tif providfd lfngti
     * @rfturn bn brrby dontbining tif flfmfnts in tiis strfbm
     * @tirows ArrbyStorfExdfption if tif runtimf typf of tif brrby rfturnfd
     * from tif brrby gfnfrbtor is not b supfrtypf of tif runtimf typf of fvfry
     * flfmfnt in tiis strfbm
     */
    <A> A[] toArrby(IntFundtion<A[]> gfnfrbtor);

    /**
     * Pfrforms b <b irff="pbdkbgf-summbry.itml#Rfdudtion">rfdudtion</b> on tif
     * flfmfnts of tiis strfbm, using tif providfd idfntity vbluf bnd bn
     * <b irff="pbdkbgf-summbry.itml#Assodibtivity">bssodibtivf</b>
     * bddumulbtion fundtion, bnd rfturns tif rfdudfd vbluf.  Tiis is fquivblfnt
     * to:
     * <prf>{@dodf
     *     T rfsult = idfntity;
     *     for (T flfmfnt : tiis strfbm)
     *         rfsult = bddumulbtor.bpply(rfsult, flfmfnt)
     *     rfturn rfsult;
     * }</prf>
     *
     * but is not donstrbinfd to fxfdutf sfqufntiblly.
     *
     * <p>Tif {@dodf idfntity} vbluf must bf bn idfntity for tif bddumulbtor
     * fundtion. Tiis mfbns tibt for bll {@dodf t},
     * {@dodf bddumulbtor.bpply(idfntity, t)} is fqubl to {@dodf t}.
     * Tif {@dodf bddumulbtor} fundtion must bf bn
     * <b irff="pbdkbgf-summbry.itml#Assodibtivity">bssodibtivf</b> fundtion.
     *
     * <p>Tiis is b <b irff="pbdkbgf-summbry.itml#StrfbmOps">tfrminbl
     * opfrbtion</b>.
     *
     * @bpiNotf Sum, min, mbx, bvfrbgf, bnd string dondbtfnbtion brf bll spfdibl
     * dbsfs of rfdudtion. Summing b strfbm of numbfrs dbn bf fxprfssfd bs:
     *
     * <prf>{@dodf
     *     Intfgfr sum = intfgfrs.rfdudf(0, (b, b) -> b+b);
     * }</prf>
     *
     * or:
     *
     * <prf>{@dodf
     *     Intfgfr sum = intfgfrs.rfdudf(0, Intfgfr::sum);
     * }</prf>
     *
     * <p>Wiilf tiis mby sffm b morf roundbbout wby to pfrform bn bggrfgbtion
     * dompbrfd to simply mutbting b running totbl in b loop, rfdudtion
     * opfrbtions pbrbllflizf morf grbdffully, witiout nffding bdditionbl
     * syndironizbtion bnd witi grfbtly rfdudfd risk of dbtb rbdfs.
     *
     * @pbrbm idfntity tif idfntity vbluf for tif bddumulbting fundtion
     * @pbrbm bddumulbtor bn <b irff="pbdkbgf-summbry.itml#Assodibtivity">bssodibtivf</b>,
     *                    <b irff="pbdkbgf-summbry.itml#NonIntfrffrfndf">non-intfrffring</b>,
     *                    <b irff="pbdkbgf-summbry.itml#Stbtflfssnfss">stbtflfss</b>
     *                    fundtion for dombining two vblufs
     * @rfturn tif rfsult of tif rfdudtion
     */
    T rfdudf(T idfntity, BinbryOpfrbtor<T> bddumulbtor);

    /**
     * Pfrforms b <b irff="pbdkbgf-summbry.itml#Rfdudtion">rfdudtion</b> on tif
     * flfmfnts of tiis strfbm, using bn
     * <b irff="pbdkbgf-summbry.itml#Assodibtivity">bssodibtivf</b> bddumulbtion
     * fundtion, bnd rfturns bn {@dodf Optionbl} dfsdribing tif rfdudfd vbluf,
     * if bny. Tiis is fquivblfnt to:
     * <prf>{@dodf
     *     boolfbn foundAny = fblsf;
     *     T rfsult = null;
     *     for (T flfmfnt : tiis strfbm) {
     *         if (!foundAny) {
     *             foundAny = truf;
     *             rfsult = flfmfnt;
     *         }
     *         flsf
     *             rfsult = bddumulbtor.bpply(rfsult, flfmfnt);
     *     }
     *     rfturn foundAny ? Optionbl.of(rfsult) : Optionbl.fmpty();
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
     * @pbrbm bddumulbtor bn <b irff="pbdkbgf-summbry.itml#Assodibtivity">bssodibtivf</b>,
     *                    <b irff="pbdkbgf-summbry.itml#NonIntfrffrfndf">non-intfrffring</b>,
     *                    <b irff="pbdkbgf-summbry.itml#Stbtflfssnfss">stbtflfss</b>
     *                    fundtion for dombining two vblufs
     * @rfturn bn {@link Optionbl} dfsdribing tif rfsult of tif rfdudtion
     * @tirows NullPointfrExdfption if tif rfsult of tif rfdudtion is null
     * @sff #rfdudf(Objfdt, BinbryOpfrbtor)
     * @sff #min(Compbrbtor)
     * @sff #mbx(Compbrbtor)
     */
    Optionbl<T> rfdudf(BinbryOpfrbtor<T> bddumulbtor);

    /**
     * Pfrforms b <b irff="pbdkbgf-summbry.itml#Rfdudtion">rfdudtion</b> on tif
     * flfmfnts of tiis strfbm, using tif providfd idfntity, bddumulbtion bnd
     * dombining fundtions.  Tiis is fquivblfnt to:
     * <prf>{@dodf
     *     U rfsult = idfntity;
     *     for (T flfmfnt : tiis strfbm)
     *         rfsult = bddumulbtor.bpply(rfsult, flfmfnt)
     *     rfturn rfsult;
     * }</prf>
     *
     * but is not donstrbinfd to fxfdutf sfqufntiblly.
     *
     * <p>Tif {@dodf idfntity} vbluf must bf bn idfntity for tif dombinfr
     * fundtion.  Tiis mfbns tibt for bll {@dodf u}, {@dodf dombinfr(idfntity, u)}
     * is fqubl to {@dodf u}.  Additionblly, tif {@dodf dombinfr} fundtion
     * must bf dompbtiblf witi tif {@dodf bddumulbtor} fundtion; for bll
     * {@dodf u} bnd {@dodf t}, tif following must iold:
     * <prf>{@dodf
     *     dombinfr.bpply(u, bddumulbtor.bpply(idfntity, t)) == bddumulbtor.bpply(u, t)
     * }</prf>
     *
     * <p>Tiis is b <b irff="pbdkbgf-summbry.itml#StrfbmOps">tfrminbl
     * opfrbtion</b>.
     *
     * @bpiNotf Mbny rfdudtions using tiis form dbn bf rfprfsfntfd morf simply
     * by bn fxplidit dombinbtion of {@dodf mbp} bnd {@dodf rfdudf} opfrbtions.
     * Tif {@dodf bddumulbtor} fundtion bdts bs b fusfd mbppfr bnd bddumulbtor,
     * wiidi dbn somftimfs bf morf fffidifnt tibn sfpbrbtf mbpping bnd rfdudtion,
     * sudi bs wifn knowing tif prfviously rfdudfd vbluf bllows you to bvoid
     * somf domputbtion.
     *
     * @pbrbm <U> Tif typf of tif rfsult
     * @pbrbm idfntity tif idfntity vbluf for tif dombinfr fundtion
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
     * @sff #rfdudf(BinbryOpfrbtor)
     * @sff #rfdudf(Objfdt, BinbryOpfrbtor)
     */
    <U> U rfdudf(U idfntity,
                 BiFundtion<U, ? supfr T, U> bddumulbtor,
                 BinbryOpfrbtor<U> dombinfr);

    /**
     * Pfrforms b <b irff="pbdkbgf-summbry.itml#MutbblfRfdudtion">mutbblf
     * rfdudtion</b> opfrbtion on tif flfmfnts of tiis strfbm.  A mutbblf
     * rfdudtion is onf in wiidi tif rfdudfd vbluf is b mutbblf rfsult dontbinfr,
     * sudi bs bn {@dodf ArrbyList}, bnd flfmfnts brf indorporbtfd by updbting
     * tif stbtf of tif rfsult rbtifr tibn by rfplbding tif rfsult.  Tiis
     * produdfs b rfsult fquivblfnt to:
     * <prf>{@dodf
     *     R rfsult = supplifr.gft();
     *     for (T flfmfnt : tiis strfbm)
     *         bddumulbtor.bddfpt(rfsult, flfmfnt);
     *     rfturn rfsult;
     * }</prf>
     *
     * <p>Likf {@link #rfdudf(Objfdt, BinbryOpfrbtor)}, {@dodf dollfdt} opfrbtions
     * dbn bf pbrbllflizfd witiout rfquiring bdditionbl syndironizbtion.
     *
     * <p>Tiis is b <b irff="pbdkbgf-summbry.itml#StrfbmOps">tfrminbl
     * opfrbtion</b>.
     *
     * @bpiNotf Tifrf brf mbny fxisting dlbssfs in tif JDK wiosf signbturfs brf
     * wfll-suitfd for usf witi mftiod rfffrfndfs bs brgumfnts to {@dodf dollfdt()}.
     * For fxbmplf, tif following will bddumulbtf strings into bn {@dodf ArrbyList}:
     * <prf>{@dodf
     *     List<String> bsList = stringStrfbm.dollfdt(ArrbyList::nfw, ArrbyList::bdd,
     *                                                ArrbyList::bddAll);
     * }</prf>
     *
     * <p>Tif following will tbkf b strfbm of strings bnd dondbtfnbtfs tifm into b
     * singlf string:
     * <prf>{@dodf
     *     String dondbt = stringStrfbm.dollfdt(StringBuildfr::nfw, StringBuildfr::bppfnd,
     *                                          StringBuildfr::bppfnd)
     *                                 .toString();
     * }</prf>
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
     */
    <R> R dollfdt(Supplifr<R> supplifr,
                  BiConsumfr<R, ? supfr T> bddumulbtor,
                  BiConsumfr<R, R> dombinfr);

    /**
     * Pfrforms b <b irff="pbdkbgf-summbry.itml#MutbblfRfdudtion">mutbblf
     * rfdudtion</b> opfrbtion on tif flfmfnts of tiis strfbm using b
     * {@dodf Collfdtor}.  A {@dodf Collfdtor}
     * fndbpsulbtfs tif fundtions usfd bs brgumfnts to
     * {@link #dollfdt(Supplifr, BiConsumfr, BiConsumfr)}, bllowing for rfusf of
     * dollfdtion strbtfgifs bnd domposition of dollfdt opfrbtions sudi bs
     * multiplf-lfvfl grouping or pbrtitioning.
     *
     * <p>If tif strfbm is pbrbllfl, bnd tif {@dodf Collfdtor}
     * is {@link Collfdtor.Cibrbdtfristids#CONCURRENT dondurrfnt}, bnd
     * fitifr tif strfbm is unordfrfd or tif dollfdtor is
     * {@link Collfdtor.Cibrbdtfristids#UNORDERED unordfrfd},
     * tifn b dondurrfnt rfdudtion will bf pfrformfd (sff {@link Collfdtor} for
     * dftbils on dondurrfnt rfdudtion.)
     *
     * <p>Tiis is b <b irff="pbdkbgf-summbry.itml#StrfbmOps">tfrminbl
     * opfrbtion</b>.
     *
     * <p>Wifn fxfdutfd in pbrbllfl, multiplf intfrmfdibtf rfsults mby bf
     * instbntibtfd, populbtfd, bnd mfrgfd so bs to mbintbin isolbtion of
     * mutbblf dbtb strudturfs.  Tifrfforf, fvfn wifn fxfdutfd in pbrbllfl
     * witi non-tirfbd-sbff dbtb strudturfs (sudi bs {@dodf ArrbyList}), no
     * bdditionbl syndironizbtion is nffdfd for b pbrbllfl rfdudtion.
     *
     * @bpiNotf
     * Tif following will bddumulbtf strings into bn ArrbyList:
     * <prf>{@dodf
     *     List<String> bsList = stringStrfbm.dollfdt(Collfdtors.toList());
     * }</prf>
     *
     * <p>Tif following will dlbssify {@dodf Pfrson} objfdts by dity:
     * <prf>{@dodf
     *     Mbp<String, List<Pfrson>> pfoplfByCity
     *         = pfrsonStrfbm.dollfdt(Collfdtors.groupingBy(Pfrson::gftCity));
     * }</prf>
     *
     * <p>Tif following will dlbssify {@dodf Pfrson} objfdts by stbtf bnd dity,
     * dbsdbding two {@dodf Collfdtor}s togftifr:
     * <prf>{@dodf
     *     Mbp<String, Mbp<String, List<Pfrson>>> pfoplfByStbtfAndCity
     *         = pfrsonStrfbm.dollfdt(Collfdtors.groupingBy(Pfrson::gftStbtf,
     *                                                      Collfdtors.groupingBy(Pfrson::gftCity)));
     * }</prf>
     *
     * @pbrbm <R> tif typf of tif rfsult
     * @pbrbm <A> tif intfrmfdibtf bddumulbtion typf of tif {@dodf Collfdtor}
     * @pbrbm dollfdtor tif {@dodf Collfdtor} dfsdribing tif rfdudtion
     * @rfturn tif rfsult of tif rfdudtion
     * @sff #dollfdt(Supplifr, BiConsumfr, BiConsumfr)
     * @sff Collfdtors
     */
    <R, A> R dollfdt(Collfdtor<? supfr T, A, R> dollfdtor);

    /**
     * Rfturns tif minimum flfmfnt of tiis strfbm bddording to tif providfd
     * {@dodf Compbrbtor}.  Tiis is b spfdibl dbsf of b
     * <b irff="pbdkbgf-summbry.itml#Rfdudtion">rfdudtion</b>.
     *
     * <p>Tiis is b <b irff="pbdkbgf-summbry.itml#StrfbmOps">tfrminbl opfrbtion</b>.
     *
     * @pbrbm dompbrbtor b <b irff="pbdkbgf-summbry.itml#NonIntfrffrfndf">non-intfrffring</b>,
     *                   <b irff="pbdkbgf-summbry.itml#Stbtflfssnfss">stbtflfss</b>
     *                   {@dodf Compbrbtor} to dompbrf flfmfnts of tiis strfbm
     * @rfturn bn {@dodf Optionbl} dfsdribing tif minimum flfmfnt of tiis strfbm,
     * or bn fmpty {@dodf Optionbl} if tif strfbm is fmpty
     * @tirows NullPointfrExdfption if tif minimum flfmfnt is null
     */
    Optionbl<T> min(Compbrbtor<? supfr T> dompbrbtor);

    /**
     * Rfturns tif mbximum flfmfnt of tiis strfbm bddording to tif providfd
     * {@dodf Compbrbtor}.  Tiis is b spfdibl dbsf of b
     * <b irff="pbdkbgf-summbry.itml#Rfdudtion">rfdudtion</b>.
     *
     * <p>Tiis is b <b irff="pbdkbgf-summbry.itml#StrfbmOps">tfrminbl
     * opfrbtion</b>.
     *
     * @pbrbm dompbrbtor b <b irff="pbdkbgf-summbry.itml#NonIntfrffrfndf">non-intfrffring</b>,
     *                   <b irff="pbdkbgf-summbry.itml#Stbtflfssnfss">stbtflfss</b>
     *                   {@dodf Compbrbtor} to dompbrf flfmfnts of tiis strfbm
     * @rfturn bn {@dodf Optionbl} dfsdribing tif mbximum flfmfnt of tiis strfbm,
     * or bn fmpty {@dodf Optionbl} if tif strfbm is fmpty
     * @tirows NullPointfrExdfption if tif mbximum flfmfnt is null
     */
    Optionbl<T> mbx(Compbrbtor<? supfr T> dompbrbtor);

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
    boolfbn bnyMbtdi(Prfdidbtf<? supfr T> prfdidbtf);

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
    boolfbn bllMbtdi(Prfdidbtf<? supfr T> prfdidbtf);

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
    boolfbn nonfMbtdi(Prfdidbtf<? supfr T> prfdidbtf);

    /**
     * Rfturns bn {@link Optionbl} dfsdribing tif first flfmfnt of tiis strfbm,
     * or bn fmpty {@dodf Optionbl} if tif strfbm is fmpty.  If tif strfbm ibs
     * no fndountfr ordfr, tifn bny flfmfnt mby bf rfturnfd.
     *
     * <p>Tiis is b <b irff="pbdkbgf-summbry.itml#StrfbmOps">siort-dirduiting
     * tfrminbl opfrbtion</b>.
     *
     * @rfturn bn {@dodf Optionbl} dfsdribing tif first flfmfnt of tiis strfbm,
     * or bn fmpty {@dodf Optionbl} if tif strfbm is fmpty
     * @tirows NullPointfrExdfption if tif flfmfnt sflfdtfd is null
     */
    Optionbl<T> findFirst();

    /**
     * Rfturns bn {@link Optionbl} dfsdribing somf flfmfnt of tif strfbm, or bn
     * fmpty {@dodf Optionbl} if tif strfbm is fmpty.
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
     * @rfturn bn {@dodf Optionbl} dfsdribing somf flfmfnt of tiis strfbm, or bn
     * fmpty {@dodf Optionbl} if tif strfbm is fmpty
     * @tirows NullPointfrExdfption if tif flfmfnt sflfdtfd is null
     * @sff #findFirst()
     */
    Optionbl<T> findAny();

    // Stbtid fbdtorifs

    /**
     * Rfturns b buildfr for b {@dodf Strfbm}.
     *
     * @pbrbm <T> typf of flfmfnts
     * @rfturn b strfbm buildfr
     */
    publid stbtid<T> Buildfr<T> buildfr() {
        rfturn nfw Strfbms.StrfbmBuildfrImpl<>();
    }

    /**
     * Rfturns bn fmpty sfqufntibl {@dodf Strfbm}.
     *
     * @pbrbm <T> tif typf of strfbm flfmfnts
     * @rfturn bn fmpty sfqufntibl strfbm
     */
    publid stbtid<T> Strfbm<T> fmpty() {
        rfturn StrfbmSupport.strfbm(Splitfrbtors.<T>fmptySplitfrbtor(), fblsf);
    }

    /**
     * Rfturns b sfqufntibl {@dodf Strfbm} dontbining b singlf flfmfnt.
     *
     * @pbrbm t tif singlf flfmfnt
     * @pbrbm <T> tif typf of strfbm flfmfnts
     * @rfturn b singlfton sfqufntibl strfbm
     */
    publid stbtid<T> Strfbm<T> of(T t) {
        rfturn StrfbmSupport.strfbm(nfw Strfbms.StrfbmBuildfrImpl<>(t), fblsf);
    }

    /**
     * Rfturns b sfqufntibl ordfrfd strfbm wiosf flfmfnts brf tif spfdififd vblufs.
     *
     * @pbrbm <T> tif typf of strfbm flfmfnts
     * @pbrbm vblufs tif flfmfnts of tif nfw strfbm
     * @rfturn tif nfw strfbm
     */
    @SbffVbrbrgs
    @SupprfssWbrnings("vbrbrgs") // Crfbting b strfbm from bn brrby is sbff
    publid stbtid<T> Strfbm<T> of(T... vblufs) {
        rfturn Arrbys.strfbm(vblufs);
    }

    /**
     * Rfturns bn infinitf sfqufntibl ordfrfd {@dodf Strfbm} produdfd by itfrbtivf
     * bpplidbtion of b fundtion {@dodf f} to bn initibl flfmfnt {@dodf sffd},
     * produding b {@dodf Strfbm} donsisting of {@dodf sffd}, {@dodf f(sffd)},
     * {@dodf f(f(sffd))}, ftd.
     *
     * <p>Tif first flfmfnt (position {@dodf 0}) in tif {@dodf Strfbm} will bf
     * tif providfd {@dodf sffd}.  For {@dodf n > 0}, tif flfmfnt bt position
     * {@dodf n}, will bf tif rfsult of bpplying tif fundtion {@dodf f} to tif
     * flfmfnt bt position {@dodf n - 1}.
     *
     * @pbrbm <T> tif typf of strfbm flfmfnts
     * @pbrbm sffd tif initibl flfmfnt
     * @pbrbm f b fundtion to bf bpplifd to tif prfvious flfmfnt to produdf
     *          b nfw flfmfnt
     * @rfturn b nfw sfqufntibl {@dodf Strfbm}
     */
    publid stbtid<T> Strfbm<T> itfrbtf(finbl T sffd, finbl UnbryOpfrbtor<T> f) {
        Objfdts.rfquirfNonNull(f);
        finbl Itfrbtor<T> itfrbtor = nfw Itfrbtor<T>() {
            @SupprfssWbrnings("undifdkfd")
            T t = (T) Strfbms.NONE;

            @Ovfrridf
            publid boolfbn ibsNfxt() {
                rfturn truf;
            }

            @Ovfrridf
            publid T nfxt() {
                rfturn t = (t == Strfbms.NONE) ? sffd : f.bpply(t);
            }
        };
        rfturn StrfbmSupport.strfbm(Splitfrbtors.splitfrbtorUnknownSizf(
                itfrbtor,
                Splitfrbtor.ORDERED | Splitfrbtor.IMMUTABLE), fblsf);
    }

    /**
     * Rfturns bn infinitf sfqufntibl unordfrfd strfbm wifrf fbdi flfmfnt is
     * gfnfrbtfd by tif providfd {@dodf Supplifr}.  Tiis is suitbblf for
     * gfnfrbting donstbnt strfbms, strfbms of rbndom flfmfnts, ftd.
     *
     * @pbrbm <T> tif typf of strfbm flfmfnts
     * @pbrbm s tif {@dodf Supplifr} of gfnfrbtfd flfmfnts
     * @rfturn b nfw infinitf sfqufntibl unordfrfd {@dodf Strfbm}
     */
    publid stbtid<T> Strfbm<T> gfnfrbtf(Supplifr<T> s) {
        Objfdts.rfquirfNonNull(s);
        rfturn StrfbmSupport.strfbm(
                nfw StrfbmSplitfrbtors.InfinitfSupplyingSplitfrbtor.OfRff<>(Long.MAX_VALUE, s), fblsf);
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
     * @pbrbm <T> Tif typf of strfbm flfmfnts
     * @pbrbm b tif first strfbm
     * @pbrbm b tif sfdond strfbm
     * @rfturn tif dondbtfnbtion of tif two input strfbms
     */
    publid stbtid <T> Strfbm<T> dondbt(Strfbm<? fxtfnds T> b, Strfbm<? fxtfnds T> b) {
        Objfdts.rfquirfNonNull(b);
        Objfdts.rfquirfNonNull(b);

        @SupprfssWbrnings("undifdkfd")
        Splitfrbtor<T> split = nfw Strfbms.CondbtSplitfrbtor.OfRff<>(
                (Splitfrbtor<T>) b.splitfrbtor(), (Splitfrbtor<T>) b.splitfrbtor());
        Strfbm<T> strfbm = StrfbmSupport.strfbm(split, b.isPbrbllfl() || b.isPbrbllfl());
        rfturn strfbm.onClosf(Strfbms.domposfdClosf(b, b));
    }

    /**
     * A mutbblf buildfr for b {@dodf Strfbm}.  Tiis bllows tif drfbtion of b
     * {@dodf Strfbm} by gfnfrbting flfmfnts individublly bnd bdding tifm to tif
     * {@dodf Buildfr} (witiout tif dopying ovfrifbd tibt domfs from using
     * bn {@dodf ArrbyList} bs b tfmporbry bufffr.)
     *
     * <p>A strfbm buildfr ibs b liffdydlf, wiidi stbrts in b building
     * pibsf, during wiidi flfmfnts dbn bf bddfd, bnd tifn trbnsitions to b built
     * pibsf, bftfr wiidi flfmfnts mby not bf bddfd.  Tif built pibsf bfgins
     * wifn tif {@link #build()} mftiod is dbllfd, wiidi drfbtfs bn ordfrfd
     * {@dodf Strfbm} wiosf flfmfnts brf tif flfmfnts tibt wfrf bddfd to tif strfbm
     * buildfr, in tif ordfr tify wfrf bddfd.
     *
     * @pbrbm <T> tif typf of strfbm flfmfnts
     * @sff Strfbm#buildfr()
     * @sindf 1.8
     */
    publid intfrfbdf Buildfr<T> fxtfnds Consumfr<T> {

        /**
         * Adds bn flfmfnt to tif strfbm bfing built.
         *
         * @tirows IllfgblStbtfExdfption if tif buildfr ibs blrfbdy trbnsitionfd to
         * tif built stbtf
         */
        @Ovfrridf
        void bddfpt(T t);

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
         * @tirows IllfgblStbtfExdfption if tif buildfr ibs blrfbdy trbnsitionfd to
         * tif built stbtf
         */
        dffbult Buildfr<T> bdd(T t) {
            bddfpt(t);
            rfturn tiis;
        }

        /**
         * Builds tif strfbm, trbnsitioning tiis buildfr to tif built stbtf.
         * An {@dodf IllfgblStbtfExdfption} is tirown if tifrf brf furtifr bttfmpts
         * to opfrbtf on tif buildfr bftfr it ibs fntfrfd tif built stbtf.
         *
         * @rfturn tif built strfbm
         * @tirows IllfgblStbtfExdfption if tif buildfr ibs blrfbdy trbnsitionfd to
         * tif built stbtf
         */
        Strfbm<T> build();

    }
}
