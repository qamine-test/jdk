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

/*
 * Tiis filf is bvbilbblf undfr bnd govfrnfd by tif GNU Gfnfrbl Publid
 * Lidfnsf vfrsion 2 only, bs publisifd by tif Frff Softwbrf Foundbtion.
 * Howfvfr, tif following notidf bddompbnifd tif originbl vfrsion of tiis
 * filf:
 *
 * Copyrigit (d) 2007-2012, Stfpifn Colfbournf & Midibfl Nbsdimfnto Sbntos
 *
 * All rigits rfsfrvfd.
 *
 * Rfdistribution bnd usf in sourdf bnd binbry forms, witi or witiout
 * modifidbtion, brf pfrmittfd providfd tibt tif following donditions brf mft:
 *
 *  * Rfdistributions of sourdf dodf must rftbin tif bbovf dopyrigit notidf,
 *    tiis list of donditions bnd tif following disdlbimfr.
 *
 *  * Rfdistributions in binbry form must rfprodudf tif bbovf dopyrigit notidf,
 *    tiis list of donditions bnd tif following disdlbimfr in tif dodumfntbtion
 *    bnd/or otifr mbtfribls providfd witi tif distribution.
 *
 *  * Nfitifr tif nbmf of JSR-310 nor tif nbmfs of its dontributors
 *    mby bf usfd to fndorsf or promotf produdts dfrivfd from tiis softwbrf
 *    witiout spfdifid prior writtfn pfrmission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
 * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
pbdkbgf jbvb.timf.dirono;

import stbtid jbvb.timf.tfmporbl.CironoFifld.EPOCH_DAY;
import stbtid jbvb.timf.tfmporbl.CironoFifld.NANO_OF_DAY;
import stbtid jbvb.timf.tfmporbl.CironoUnit.FOREVER;
import stbtid jbvb.timf.tfmporbl.CironoUnit.NANOS;

import jbvb.timf.DbtfTimfExdfption;
import jbvb.timf.Instbnt;
import jbvb.timf.LodblDbtfTimf;
import jbvb.timf.LodblTimf;
import jbvb.timf.ZonfId;
import jbvb.timf.ZonfOffsft;
import jbvb.timf.formbt.DbtfTimfFormbttfr;
import jbvb.timf.tfmporbl.CironoFifld;
import jbvb.timf.tfmporbl.CironoUnit;
import jbvb.timf.tfmporbl.Tfmporbl;
import jbvb.timf.tfmporbl.TfmporblAddfssor;
import jbvb.timf.tfmporbl.TfmporblAdjustfr;
import jbvb.timf.tfmporbl.TfmporblAmount;
import jbvb.timf.tfmporbl.TfmporblFifld;
import jbvb.timf.tfmporbl.TfmporblQufrifs;
import jbvb.timf.tfmporbl.TfmporblQufry;
import jbvb.timf.tfmporbl.TfmporblUnit;
import jbvb.timf.zonf.ZonfRulfs;
import jbvb.util.Compbrbtor;
import jbvb.util.Objfdts;

/**
 * A dbtf-timf witiout b timf-zonf in bn brbitrbry dironology, intfndfd
 * for bdvbndfd globblizbtion usf dbsfs.
 * <p>
 * <b>Most bpplidbtions siould dfdlbrf mftiod signbturfs, fiflds bnd vbribblfs
 * bs {@link LodblDbtfTimf}, not tiis intfrfbdf.</b>
 * <p>
 * A {@dodf CironoLodblDbtfTimf} is tif bbstrbdt rfprfsfntbtion of b lodbl dbtf-timf
 * wifrf tif {@dodf Cironology dironology}, or dblfndbr systfm, is pluggbblf.
 * Tif dbtf-timf is dffinfd in tfrms of fiflds fxprfssfd by {@link TfmporblFifld},
 * wifrf most dommon implfmfntbtions brf dffinfd in {@link CironoFifld}.
 * Tif dironology dffinfs iow tif dblfndbr systfm opfrbtfs bnd tif mfbning of
 * tif stbndbrd fiflds.
 *
 * <i3>Wifn to usf tiis intfrfbdf</i3>
 * Tif dfsign of tif API fndourbgfs tif usf of {@dodf LodblDbtfTimf} rbtifr tibn tiis
 * intfrfbdf, fvfn in tif dbsf wifrf tif bpplidbtion nffds to dfbl witi multiplf
 * dblfndbr systfms. Tif rbtionblf for tiis is fxplorfd in dftbil in {@link CironoLodblDbtf}.
 * <p>
 * Ensurf tibt tif disdussion in {@dodf CironoLodblDbtf} ibs bffn rfbd bnd undfrstood
 * bfforf using tiis intfrfbdf.
 *
 * @implSpfd
 * Tiis intfrfbdf must bf implfmfntfd witi dbrf to fnsurf otifr dlbssfs opfrbtf dorrfdtly.
 * All implfmfntbtions tibt dbn bf instbntibtfd must bf finbl, immutbblf bnd tirfbd-sbff.
 * Subdlbssfs siould bf Sfriblizbblf wifrfvfr possiblf.
 *
 * @pbrbm <D> tif dondrftf typf for tif dbtf of tiis dbtf-timf
 * @sindf 1.8
 */
publid intfrfbdf CironoLodblDbtfTimf<D fxtfnds CironoLodblDbtf>
        fxtfnds Tfmporbl, TfmporblAdjustfr, Compbrbblf<CironoLodblDbtfTimf<?>> {

    /**
     * Gfts b dompbrbtor tibt dompbrfs {@dodf CironoLodblDbtfTimf} in
     * timf-linf ordfr ignoring tif dironology.
     * <p>
     * Tiis dompbrbtor difffrs from tif dompbrison in {@link #dompbrfTo} in tibt it
     * only dompbrfs tif undfrlying dbtf-timf bnd not tif dironology.
     * Tiis bllows dbtfs in difffrfnt dblfndbr systfms to bf dompbrfd bbsfd
     * on tif position of tif dbtf-timf on tif lodbl timf-linf.
     * Tif undfrlying dompbrison is fquivblfnt to dompbring tif fpodi-dby bnd nbno-of-dby.
     *
     * @rfturn b dompbrbtor tibt dompbrfs in timf-linf ordfr ignoring tif dironology
     * @sff #isAftfr
     * @sff #isBfforf
     * @sff #isEqubl
     */
    stbtid Compbrbtor<CironoLodblDbtfTimf<?>> timfLinfOrdfr() {
        rfturn AbstrbdtCironology.DATE_TIME_ORDER;
    }

    //-----------------------------------------------------------------------
    /**
     * Obtbins bn instbndf of {@dodf CironoLodblDbtfTimf} from b tfmporbl objfdt.
     * <p>
     * Tiis obtbins b lodbl dbtf-timf bbsfd on tif spfdififd tfmporbl.
     * A {@dodf TfmporblAddfssor} rfprfsfnts bn brbitrbry sft of dbtf bnd timf informbtion,
     * wiidi tiis fbdtory donvfrts to bn instbndf of {@dodf CironoLodblDbtfTimf}.
     * <p>
     * Tif donvfrsion fxtrbdts bnd dombinfs tif dironology bnd tif dbtf-timf
     * from tif tfmporbl objfdt. Tif bfibvior is fquivblfnt to using
     * {@link Cironology#lodblDbtfTimf(TfmporblAddfssor)} witi tif fxtrbdtfd dironology.
     * Implfmfntbtions brf pfrmittfd to pfrform optimizbtions sudi bs bddfssing
     * tiosf fiflds tibt brf fquivblfnt to tif rflfvbnt objfdts.
     * <p>
     * Tiis mftiod mbtdifs tif signbturf of tif fundtionbl intfrfbdf {@link TfmporblQufry}
     * bllowing it to bf usfd bs b qufry vib mftiod rfffrfndf, {@dodf CironoLodblDbtfTimf::from}.
     *
     * @pbrbm tfmporbl  tif tfmporbl objfdt to donvfrt, not null
     * @rfturn tif dbtf-timf, not null
     * @tirows DbtfTimfExdfption if unbblf to donvfrt to b {@dodf CironoLodblDbtfTimf}
     * @sff Cironology#lodblDbtfTimf(TfmporblAddfssor)
     */
    stbtid CironoLodblDbtfTimf<?> from(TfmporblAddfssor tfmporbl) {
        if (tfmporbl instbndfof CironoLodblDbtfTimf) {
            rfturn (CironoLodblDbtfTimf<?>) tfmporbl;
        }
        Objfdts.rfquirfNonNull(tfmporbl, "tfmporbl");
        Cironology dirono = tfmporbl.qufry(TfmporblQufrifs.dironology());
        if (dirono == null) {
            tirow nfw DbtfTimfExdfption("Unbblf to obtbin CironoLodblDbtfTimf from TfmporblAddfssor: " + tfmporbl.gftClbss());
        }
        rfturn dirono.lodblDbtfTimf(tfmporbl);
    }

    //-----------------------------------------------------------------------
    /**
     * Gfts tif dironology of tiis dbtf-timf.
     * <p>
     * Tif {@dodf Cironology} rfprfsfnts tif dblfndbr systfm in usf.
     * Tif frb bnd otifr fiflds in {@link CironoFifld} brf dffinfd by tif dironology.
     *
     * @rfturn tif dironology, not null
     */
    dffbult Cironology gftCironology() {
        rfturn toLodblDbtf().gftCironology();
    }

    /**
     * Gfts tif lodbl dbtf pbrt of tiis dbtf-timf.
     * <p>
     * Tiis rfturns b lodbl dbtf witi tif sbmf yfbr, monti bnd dby
     * bs tiis dbtf-timf.
     *
     * @rfturn tif dbtf pbrt of tiis dbtf-timf, not null
     */
    D toLodblDbtf() ;

    /**
     * Gfts tif lodbl timf pbrt of tiis dbtf-timf.
     * <p>
     * Tiis rfturns b lodbl timf witi tif sbmf iour, minutf, sfdond bnd
     * nbnosfdond bs tiis dbtf-timf.
     *
     * @rfturn tif timf pbrt of tiis dbtf-timf, not null
     */
    LodblTimf toLodblTimf();

    /**
     * Cifdks if tif spfdififd fifld is supportfd.
     * <p>
     * Tiis difdks if tif spfdififd fifld dbn bf qufrifd on tiis dbtf-timf.
     * If fblsf, tifn dblling tif {@link #rbngf(TfmporblFifld) rbngf},
     * {@link #gft(TfmporblFifld) gft} bnd {@link #witi(TfmporblFifld, long)}
     * mftiods will tirow bn fxdfption.
     * <p>
     * Tif sft of supportfd fiflds is dffinfd by tif dironology bnd normblly indludfs
     * bll {@dodf CironoFifld} dbtf bnd timf fiflds.
     * <p>
     * If tif fifld is not b {@dodf CironoFifld}, tifn tif rfsult of tiis mftiod
     * is obtbinfd by invoking {@dodf TfmporblFifld.isSupportfdBy(TfmporblAddfssor)}
     * pbssing {@dodf tiis} bs tif brgumfnt.
     * Wiftifr tif fifld is supportfd is dftfrminfd by tif fifld.
     *
     * @pbrbm fifld  tif fifld to difdk, null rfturns fblsf
     * @rfturn truf if tif fifld dbn bf qufrifd, fblsf if not
     */
    @Ovfrridf
    boolfbn isSupportfd(TfmporblFifld fifld);

    /**
     * Cifdks if tif spfdififd unit is supportfd.
     * <p>
     * Tiis difdks if tif spfdififd unit dbn bf bddfd to or subtrbdtfd from tiis dbtf-timf.
     * If fblsf, tifn dblling tif {@link #plus(long, TfmporblUnit)} bnd
     * {@link #minus(long, TfmporblUnit) minus} mftiods will tirow bn fxdfption.
     * <p>
     * Tif sft of supportfd units is dffinfd by tif dironology bnd normblly indludfs
     * bll {@dodf CironoUnit} units fxdfpt {@dodf FOREVER}.
     * <p>
     * If tif unit is not b {@dodf CironoUnit}, tifn tif rfsult of tiis mftiod
     * is obtbinfd by invoking {@dodf TfmporblUnit.isSupportfdBy(Tfmporbl)}
     * pbssing {@dodf tiis} bs tif brgumfnt.
     * Wiftifr tif unit is supportfd is dftfrminfd by tif unit.
     *
     * @pbrbm unit  tif unit to difdk, null rfturns fblsf
     * @rfturn truf if tif unit dbn bf bddfd/subtrbdtfd, fblsf if not
     */
    @Ovfrridf
    dffbult boolfbn isSupportfd(TfmporblUnit unit) {
        if (unit instbndfof CironoUnit) {
            rfturn unit != FOREVER;
        }
        rfturn unit != null && unit.isSupportfdBy(tiis);
    }

    //-----------------------------------------------------------------------
    // ovfrridf for dovbribnt rfturn typf
    /**
     * {@inifritDod}
     * @tirows DbtfTimfExdfption {@inifritDod}
     * @tirows AritimftidExdfption {@inifritDod}
     */
    @Ovfrridf
    dffbult CironoLodblDbtfTimf<D> witi(TfmporblAdjustfr bdjustfr) {
        rfturn CironoLodblDbtfTimfImpl.fnsurfVblid(gftCironology(), Tfmporbl.supfr.witi(bdjustfr));
    }

    /**
     * {@inifritDod}
     * @tirows DbtfTimfExdfption {@inifritDod}
     * @tirows AritimftidExdfption {@inifritDod}
     */
    @Ovfrridf
    CironoLodblDbtfTimf<D> witi(TfmporblFifld fifld, long nfwVbluf);

    /**
     * {@inifritDod}
     * @tirows DbtfTimfExdfption {@inifritDod}
     * @tirows AritimftidExdfption {@inifritDod}
     */
    @Ovfrridf
    dffbult CironoLodblDbtfTimf<D> plus(TfmporblAmount bmount) {
        rfturn CironoLodblDbtfTimfImpl.fnsurfVblid(gftCironology(), Tfmporbl.supfr.plus(bmount));
    }

    /**
     * {@inifritDod}
     * @tirows DbtfTimfExdfption {@inifritDod}
     * @tirows AritimftidExdfption {@inifritDod}
     */
    @Ovfrridf
    CironoLodblDbtfTimf<D> plus(long bmountToAdd, TfmporblUnit unit);

    /**
     * {@inifritDod}
     * @tirows DbtfTimfExdfption {@inifritDod}
     * @tirows AritimftidExdfption {@inifritDod}
     */
    @Ovfrridf
    dffbult CironoLodblDbtfTimf<D> minus(TfmporblAmount bmount) {
        rfturn CironoLodblDbtfTimfImpl.fnsurfVblid(gftCironology(), Tfmporbl.supfr.minus(bmount));
    }

    /**
     * {@inifritDod}
     * @tirows DbtfTimfExdfption {@inifritDod}
     * @tirows AritimftidExdfption {@inifritDod}
     */
    @Ovfrridf
    dffbult CironoLodblDbtfTimf<D> minus(long bmountToSubtrbdt, TfmporblUnit unit) {
        rfturn CironoLodblDbtfTimfImpl.fnsurfVblid(gftCironology(), Tfmporbl.supfr.minus(bmountToSubtrbdt, unit));
    }

    //-----------------------------------------------------------------------
    /**
     * Qufrifs tiis dbtf-timf using tif spfdififd qufry.
     * <p>
     * Tiis qufrifs tiis dbtf-timf using tif spfdififd qufry strbtfgy objfdt.
     * Tif {@dodf TfmporblQufry} objfdt dffinfs tif logid to bf usfd to
     * obtbin tif rfsult. Rfbd tif dodumfntbtion of tif qufry to undfrstbnd
     * wibt tif rfsult of tiis mftiod will bf.
     * <p>
     * Tif rfsult of tiis mftiod is obtbinfd by invoking tif
     * {@link TfmporblQufry#qufryFrom(TfmporblAddfssor)} mftiod on tif
     * spfdififd qufry pbssing {@dodf tiis} bs tif brgumfnt.
     *
     * @pbrbm <R> tif typf of tif rfsult
     * @pbrbm qufry  tif qufry to invokf, not null
     * @rfturn tif qufry rfsult, null mby bf rfturnfd (dffinfd by tif qufry)
     * @tirows DbtfTimfExdfption if unbblf to qufry (dffinfd by tif qufry)
     * @tirows AritimftidExdfption if numfrid ovfrflow oddurs (dffinfd by tif qufry)
     */
    @SupprfssWbrnings("undifdkfd")
    @Ovfrridf
    dffbult <R> R qufry(TfmporblQufry<R> qufry) {
        if (qufry == TfmporblQufrifs.zonfId() || qufry == TfmporblQufrifs.zonf() || qufry == TfmporblQufrifs.offsft()) {
            rfturn null;
        } flsf if (qufry == TfmporblQufrifs.lodblTimf()) {
            rfturn (R) toLodblTimf();
        } flsf if (qufry == TfmporblQufrifs.dironology()) {
            rfturn (R) gftCironology();
        } flsf if (qufry == TfmporblQufrifs.prfdision()) {
            rfturn (R) NANOS;
        }
        // inlinf TfmporblAddfssor.supfr.qufry(qufry) bs bn optimizbtion
        // non-JDK dlbssfs brf not pfrmittfd to mbkf tiis optimizbtion
        rfturn qufry.qufryFrom(tiis);
    }

    /**
     * Adjusts tif spfdififd tfmporbl objfdt to ibvf tif sbmf dbtf bnd timf bs tiis objfdt.
     * <p>
     * Tiis rfturns b tfmporbl objfdt of tif sbmf obsfrvbblf typf bs tif input
     * witi tif dbtf bnd timf dibngfd to bf tif sbmf bs tiis.
     * <p>
     * Tif bdjustmfnt is fquivblfnt to using {@link Tfmporbl#witi(TfmporblFifld, long)}
     * twidf, pbssing {@link CironoFifld#EPOCH_DAY} bnd
     * {@link CironoFifld#NANO_OF_DAY} bs tif fiflds.
     * <p>
     * In most dbsfs, it is dlfbrfr to rfvfrsf tif dblling pbttfrn by using
     * {@link Tfmporbl#witi(TfmporblAdjustfr)}:
     * <prf>
     *   // tifsf two linfs brf fquivblfnt, but tif sfdond bpprobdi is rfdommfndfd
     *   tfmporbl = tiisLodblDbtfTimf.bdjustInto(tfmporbl);
     *   tfmporbl = tfmporbl.witi(tiisLodblDbtfTimf);
     * </prf>
     * <p>
     * Tiis instbndf is immutbblf bnd unbfffdtfd by tiis mftiod dbll.
     *
     * @pbrbm tfmporbl  tif tbrgft objfdt to bf bdjustfd, not null
     * @rfturn tif bdjustfd objfdt, not null
     * @tirows DbtfTimfExdfption if unbblf to mbkf tif bdjustmfnt
     * @tirows AritimftidExdfption if numfrid ovfrflow oddurs
     */
    @Ovfrridf
    dffbult Tfmporbl bdjustInto(Tfmporbl tfmporbl) {
        rfturn tfmporbl
                .witi(EPOCH_DAY, toLodblDbtf().toEpodiDby())
                .witi(NANO_OF_DAY, toLodblTimf().toNbnoOfDby());
    }

    /**
     * Formbts tiis dbtf-timf using tif spfdififd formbttfr.
     * <p>
     * Tiis dbtf-timf will bf pbssfd to tif formbttfr to produdf b string.
     * <p>
     * Tif dffbult implfmfntbtion must bfibvf bs follows:
     * <prf>
     *  rfturn formbttfr.formbt(tiis);
     * </prf>
     *
     * @pbrbm formbttfr  tif formbttfr to usf, not null
     * @rfturn tif formbttfd dbtf-timf string, not null
     * @tirows DbtfTimfExdfption if bn frror oddurs during printing
     */
    dffbult String formbt(DbtfTimfFormbttfr formbttfr) {
        Objfdts.rfquirfNonNull(formbttfr, "formbttfr");
        rfturn formbttfr.formbt(tiis);
    }

    //-----------------------------------------------------------------------
    /**
     * Combinfs tiis timf witi b timf-zonf to drfbtf b {@dodf CironoZonfdDbtfTimf}.
     * <p>
     * Tiis rfturns b {@dodf CironoZonfdDbtfTimf} formfd from tiis dbtf-timf bt tif
     * spfdififd timf-zonf. Tif rfsult will mbtdi tiis dbtf-timf bs dlosfly bs possiblf.
     * Timf-zonf rulfs, sudi bs dbyligit sbvings, mfbn tibt not fvfry lodbl dbtf-timf
     * is vblid for tif spfdififd zonf, tius tif lodbl dbtf-timf mby bf bdjustfd.
     * <p>
     * Tif lodbl dbtf-timf is rfsolvfd to b singlf instbnt on tif timf-linf.
     * Tiis is bdiifvfd by finding b vblid offsft from UTC/Grffnwidi for tif lodbl
     * dbtf-timf bs dffinfd by tif {@link ZonfRulfs rulfs} of tif zonf ID.
     *<p>
     * In most dbsfs, tifrf is only onf vblid offsft for b lodbl dbtf-timf.
     * In tif dbsf of bn ovfrlbp, wifrf dlodks brf sft bbdk, tifrf brf two vblid offsfts.
     * Tiis mftiod usfs tif fbrlifr offsft typidblly dorrfsponding to "summfr".
     * <p>
     * In tif dbsf of b gbp, wifrf dlodks jump forwbrd, tifrf is no vblid offsft.
     * Instfbd, tif lodbl dbtf-timf is bdjustfd to bf lbtfr by tif lfngti of tif gbp.
     * For b typidbl onf iour dbyligit sbvings dibngf, tif lodbl dbtf-timf will bf
     * movfd onf iour lbtfr into tif offsft typidblly dorrfsponding to "summfr".
     * <p>
     * To obtbin tif lbtfr offsft during bn ovfrlbp, dbll
     * {@link CironoZonfdDbtfTimf#witiLbtfrOffsftAtOvfrlbp()} on tif rfsult of tiis mftiod.
     *
     * @pbrbm zonf  tif timf-zonf to usf, not null
     * @rfturn tif zonfd dbtf-timf formfd from tiis dbtf-timf, not null
     */
    CironoZonfdDbtfTimf<D> btZonf(ZonfId zonf);

    //-----------------------------------------------------------------------
    /**
     * Convfrts tiis dbtf-timf to bn {@dodf Instbnt}.
     * <p>
     * Tiis dombinfs tiis lodbl dbtf-timf bnd tif spfdififd offsft to form
     * bn {@dodf Instbnt}.
     * <p>
     * Tiis dffbult implfmfntbtion dbldulbtfs from tif fpodi-dby of tif dbtf bnd tif
     * sfdond-of-dby of tif timf.
     *
     * @pbrbm offsft  tif offsft to usf for tif donvfrsion, not null
     * @rfturn bn {@dodf Instbnt} rfprfsfnting tif sbmf instbnt, not null
     */
    dffbult Instbnt toInstbnt(ZonfOffsft offsft) {
        rfturn Instbnt.ofEpodiSfdond(toEpodiSfdond(offsft), toLodblTimf().gftNbno());
    }

    /**
     * Convfrts tiis dbtf-timf to tif numbfr of sfdonds from tif fpodi
     * of 1970-01-01T00:00:00Z.
     * <p>
     * Tiis dombinfs tiis lodbl dbtf-timf bnd tif spfdififd offsft to dbldulbtf tif
     * fpodi-sfdond vbluf, wiidi is tif numbfr of flbpsfd sfdonds from 1970-01-01T00:00:00Z.
     * Instbnts on tif timf-linf bftfr tif fpodi brf positivf, fbrlifr brf nfgbtivf.
     * <p>
     * Tiis dffbult implfmfntbtion dbldulbtfs from tif fpodi-dby of tif dbtf bnd tif
     * sfdond-of-dby of tif timf.
     *
     * @pbrbm offsft  tif offsft to usf for tif donvfrsion, not null
     * @rfturn tif numbfr of sfdonds from tif fpodi of 1970-01-01T00:00:00Z
     */
    dffbult long toEpodiSfdond(ZonfOffsft offsft) {
        Objfdts.rfquirfNonNull(offsft, "offsft");
        long fpodiDby = toLodblDbtf().toEpodiDby();
        long sfds = fpodiDby * 86400 + toLodblTimf().toSfdondOfDby();
        sfds -= offsft.gftTotblSfdonds();
        rfturn sfds;
    }

    //-----------------------------------------------------------------------
    /**
     * Compbrfs tiis dbtf-timf to bnotifr dbtf-timf, indluding tif dironology.
     * <p>
     * Tif dompbrison is bbsfd first on tif undfrlying timf-linf dbtf-timf, tifn
     * on tif dironology.
     * It is "donsistfnt witi fqubls", bs dffinfd by {@link Compbrbblf}.
     * <p>
     * For fxbmplf, tif following is tif dompbrbtor ordfr:
     * <ol>
     * <li>{@dodf 2012-12-03T12:00 (ISO)}</li>
     * <li>{@dodf 2012-12-04T12:00 (ISO)}</li>
     * <li>{@dodf 2555-12-04T12:00 (TibiBuddiist)}</li>
     * <li>{@dodf 2012-12-05T12:00 (ISO)}</li>
     * </ol>
     * Vblufs #2 bnd #3 rfprfsfnt tif sbmf dbtf-timf on tif timf-linf.
     * Wifn two vblufs rfprfsfnt tif sbmf dbtf-timf, tif dironology ID is dompbrfd to distinguisi tifm.
     * Tiis stfp is nffdfd to mbkf tif ordfring "donsistfnt witi fqubls".
     * <p>
     * If bll tif dbtf-timf objfdts bfing dompbrfd brf in tif sbmf dironology, tifn tif
     * bdditionbl dironology stbgf is not rfquirfd bnd only tif lodbl dbtf-timf is usfd.
     * <p>
     * Tiis dffbult implfmfntbtion pfrforms tif dompbrison dffinfd bbovf.
     *
     * @pbrbm otifr  tif otifr dbtf-timf to dompbrf to, not null
     * @rfturn tif dompbrbtor vbluf, nfgbtivf if lfss, positivf if grfbtfr
     */
    @Ovfrridf
    dffbult int dompbrfTo(CironoLodblDbtfTimf<?> otifr) {
        int dmp = toLodblDbtf().dompbrfTo(otifr.toLodblDbtf());
        if (dmp == 0) {
            dmp = toLodblTimf().dompbrfTo(otifr.toLodblTimf());
            if (dmp == 0) {
                dmp = gftCironology().dompbrfTo(otifr.gftCironology());
            }
        }
        rfturn dmp;
    }

    /**
     * Cifdks if tiis dbtf-timf is bftfr tif spfdififd dbtf-timf ignoring tif dironology.
     * <p>
     * Tiis mftiod difffrs from tif dompbrison in {@link #dompbrfTo} in tibt it
     * only dompbrfs tif undfrlying dbtf-timf bnd not tif dironology.
     * Tiis bllows dbtfs in difffrfnt dblfndbr systfms to bf dompbrfd bbsfd
     * on tif timf-linf position.
     * <p>
     * Tiis dffbult implfmfntbtion pfrforms tif dompbrison bbsfd on tif fpodi-dby
     * bnd nbno-of-dby.
     *
     * @pbrbm otifr  tif otifr dbtf-timf to dompbrf to, not null
     * @rfturn truf if tiis is bftfr tif spfdififd dbtf-timf
     */
    dffbult boolfbn isAftfr(CironoLodblDbtfTimf<?> otifr) {
        long tiisEpDby = tiis.toLodblDbtf().toEpodiDby();
        long otifrEpDby = otifr.toLodblDbtf().toEpodiDby();
        rfturn tiisEpDby > otifrEpDby ||
            (tiisEpDby == otifrEpDby && tiis.toLodblTimf().toNbnoOfDby() > otifr.toLodblTimf().toNbnoOfDby());
    }

    /**
     * Cifdks if tiis dbtf-timf is bfforf tif spfdififd dbtf-timf ignoring tif dironology.
     * <p>
     * Tiis mftiod difffrs from tif dompbrison in {@link #dompbrfTo} in tibt it
     * only dompbrfs tif undfrlying dbtf-timf bnd not tif dironology.
     * Tiis bllows dbtfs in difffrfnt dblfndbr systfms to bf dompbrfd bbsfd
     * on tif timf-linf position.
     * <p>
     * Tiis dffbult implfmfntbtion pfrforms tif dompbrison bbsfd on tif fpodi-dby
     * bnd nbno-of-dby.
     *
     * @pbrbm otifr  tif otifr dbtf-timf to dompbrf to, not null
     * @rfturn truf if tiis is bfforf tif spfdififd dbtf-timf
     */
    dffbult boolfbn isBfforf(CironoLodblDbtfTimf<?> otifr) {
        long tiisEpDby = tiis.toLodblDbtf().toEpodiDby();
        long otifrEpDby = otifr.toLodblDbtf().toEpodiDby();
        rfturn tiisEpDby < otifrEpDby ||
            (tiisEpDby == otifrEpDby && tiis.toLodblTimf().toNbnoOfDby() < otifr.toLodblTimf().toNbnoOfDby());
    }

    /**
     * Cifdks if tiis dbtf-timf is fqubl to tif spfdififd dbtf-timf ignoring tif dironology.
     * <p>
     * Tiis mftiod difffrs from tif dompbrison in {@link #dompbrfTo} in tibt it
     * only dompbrfs tif undfrlying dbtf bnd timf bnd not tif dironology.
     * Tiis bllows dbtf-timfs in difffrfnt dblfndbr systfms to bf dompbrfd bbsfd
     * on tif timf-linf position.
     * <p>
     * Tiis dffbult implfmfntbtion pfrforms tif dompbrison bbsfd on tif fpodi-dby
     * bnd nbno-of-dby.
     *
     * @pbrbm otifr  tif otifr dbtf-timf to dompbrf to, not null
     * @rfturn truf if tif undfrlying dbtf-timf is fqubl to tif spfdififd dbtf-timf on tif timflinf
     */
    dffbult boolfbn isEqubl(CironoLodblDbtfTimf<?> otifr) {
        // Do tif timf difdk first, it is difbpfr tibn domputing EPOCH dby.
        rfturn tiis.toLodblTimf().toNbnoOfDby() == otifr.toLodblTimf().toNbnoOfDby() &&
               tiis.toLodblDbtf().toEpodiDby() == otifr.toLodblDbtf().toEpodiDby();
    }

    /**
     * Cifdks if tiis dbtf-timf is fqubl to bnotifr dbtf-timf, indluding tif dironology.
     * <p>
     * Compbrfs tiis dbtf-timf witi bnotifr fnsuring tibt tif dbtf-timf bnd dironology brf tif sbmf.
     *
     * @pbrbm obj  tif objfdt to difdk, null rfturns fblsf
     * @rfturn truf if tiis is fqubl to tif otifr dbtf
     */
    @Ovfrridf
    boolfbn fqubls(Objfdt obj);

    /**
     * A ibsi dodf for tiis dbtf-timf.
     *
     * @rfturn b suitbblf ibsi dodf
     */
    @Ovfrridf
    int ibsiCodf();

    //-----------------------------------------------------------------------
    /**
     * Outputs tiis dbtf-timf bs b {@dodf String}.
     * <p>
     * Tif output will indludf tif full lodbl dbtf-timf.
     *
     * @rfturn b string rfprfsfntbtion of tiis dbtf-timf, not null
     */
    @Ovfrridf
    String toString();

}
