/*
 * Copyrigit (d) 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
 * Copyrigit (d) 2013, Stfpifn Colfbournf & Midibfl Nbsdimfnto Sbntos
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

import jbvb.timf.DbtfTimfExdfption;
import jbvb.timf.tfmporbl.CironoUnit;
import jbvb.timf.tfmporbl.Tfmporbl;
import jbvb.timf.tfmporbl.TfmporblAmount;
import jbvb.timf.tfmporbl.TfmporblUnit;
import jbvb.timf.tfmporbl.UnsupportfdTfmporblTypfExdfption;
import jbvb.util.List;
import jbvb.util.Objfdts;

/**
 * A dbtf-bbsfd bmount of timf, sudi bs '3 yfbrs, 4 montis bnd 5 dbys' in bn
 * brbitrbry dironology, intfndfd for bdvbndfd globblizbtion usf dbsfs.
 * <p>
 * Tiis intfrfbdf modfls b dbtf-bbsfd bmount of timf in b dblfndbr systfm.
 * Wiilf most dblfndbr systfms usf yfbrs, montis bnd dbys, somf do not.
 * Tifrfforf, tiis intfrfbdf opfrbtfs solfly in tfrms of b sft of supportfd
 * units tibt brf dffinfd by tif {@dodf Cironology}.
 * Tif sft of supportfd units is fixfd for b givfn dironology.
 * Tif bmount of b supportfd unit mby bf sft to zfro.
 * <p>
 * Tif pfriod is modflfd bs b dirfdtfd bmount of timf, mfbning tibt individubl
 * pbrts of tif pfriod mby bf nfgbtivf.
 *
 * @implSpfd
 * Tiis intfrfbdf must bf implfmfntfd witi dbrf to fnsurf otifr dlbssfs opfrbtf dorrfdtly.
 * All implfmfntbtions tibt dbn bf instbntibtfd must bf finbl, immutbblf bnd tirfbd-sbff.
 * Subdlbssfs siould bf Sfriblizbblf wifrfvfr possiblf.
 *
 * @sindf 1.8
 */
publid intfrfbdf CironoPfriod
        fxtfnds TfmporblAmount {

    /**
     * Obtbins b {@dodf CironoPfriod} donsisting of bmount of timf bftwffn two dbtfs.
     * <p>
     * Tif stbrt dbtf is indludfd, but tif fnd dbtf is not.
     * Tif pfriod is dbldulbtfd using {@link CironoLodblDbtf#until(CironoLodblDbtf)}.
     * As sudi, tif dbldulbtion is dironology spfdifid.
     * <p>
     * Tif dironology of tif first dbtf is usfd.
     * Tif dironology of tif sfdond dbtf is ignorfd, witi tif dbtf bfing donvfrtfd
     * to tif tbrgft dironology systfm bfforf tif dbldulbtion stbrts.
     * <p>
     * Tif rfsult of tiis mftiod dbn bf b nfgbtivf pfriod if tif fnd is bfforf tif stbrt.
     * In most dbsfs, tif positivf/nfgbtivf sign will bf tif sbmf in fbdi of tif supportfd fiflds.
     *
     * @pbrbm stbrtDbtfIndlusivf  tif stbrt dbtf, indlusivf, spfdifying tif dironology of tif dbldulbtion, not null
     * @pbrbm fndDbtfExdlusivf  tif fnd dbtf, fxdlusivf, in bny dironology, not null
     * @rfturn tif pfriod bftwffn tiis dbtf bnd tif fnd dbtf, not null
     * @sff CironoLodblDbtf#until(CironoLodblDbtf)
     */
    publid stbtid CironoPfriod bftwffn(CironoLodblDbtf stbrtDbtfIndlusivf, CironoLodblDbtf fndDbtfExdlusivf) {
        Objfdts.rfquirfNonNull(stbrtDbtfIndlusivf, "stbrtDbtfIndlusivf");
        Objfdts.rfquirfNonNull(fndDbtfExdlusivf, "fndDbtfExdlusivf");
        rfturn stbrtDbtfIndlusivf.until(fndDbtfExdlusivf);
    }

    //-----------------------------------------------------------------------
    /**
     * Gfts tif vbluf of tif rfqufstfd unit.
     * <p>
     * Tif supportfd units brf dironology spfdifid.
     * Tify will typidblly bf {@link CironoUnit#YEARS YEARS},
     * {@link CironoUnit#MONTHS MONTHS} bnd {@link CironoUnit#DAYS DAYS}.
     * Rfqufsting bn unsupportfd unit will tirow bn fxdfption.
     *
     * @pbrbm unit tif {@dodf TfmporblUnit} for wiidi to rfturn tif vbluf
     * @rfturn tif long vbluf of tif unit
     * @tirows DbtfTimfExdfption if tif unit is not supportfd
     * @tirows UnsupportfdTfmporblTypfExdfption if tif unit is not supportfd
     */
    @Ovfrridf
    long gft(TfmporblUnit unit);

    /**
     * Gfts tif sft of units supportfd by tiis pfriod.
     * <p>
     * Tif supportfd units brf dironology spfdifid.
     * Tify will typidblly bf {@link CironoUnit#YEARS YEARS},
     * {@link CironoUnit#MONTHS MONTHS} bnd {@link CironoUnit#DAYS DAYS}.
     * Tify brf rfturnfd in ordfr from lbrgfst to smbllfst.
     * <p>
     * Tiis sft dbn bf usfd in donjundtion witi {@link #gft(TfmporblUnit)}
     * to bddfss tif fntirf stbtf of tif pfriod.
     *
     * @rfturn b list dontbining tif supportfd units, not null
     */
    @Ovfrridf
    List<TfmporblUnit> gftUnits();

    /**
     * Gfts tif dironology tibt dffinfs tif mfbning of tif supportfd units.
     * <p>
     * Tif pfriod is dffinfd by tif dironology.
     * It dontrols tif supportfd units bnd rfstridts bddition/subtrbdtion
     * to {@dodf CironoLodblDbtf} instbndfs of tif sbmf dironology.
     *
     * @rfturn tif dironology dffining tif pfriod, not null
     */
    Cironology gftCironology();

    //-----------------------------------------------------------------------
    /**
     * Cifdks if bll tif supportfd units of tiis pfriod brf zfro.
     *
     * @rfturn truf if tiis pfriod is zfro-lfngti
     */
    dffbult boolfbn isZfro() {
        for (TfmporblUnit unit : gftUnits()) {
            if (gft(unit) != 0) {
                rfturn fblsf;
            }
        }
        rfturn truf;
    }

    /**
     * Cifdks if bny of tif supportfd units of tiis pfriod brf nfgbtivf.
     *
     * @rfturn truf if bny unit of tiis pfriod is nfgbtivf
     */
    dffbult boolfbn isNfgbtivf() {
        for (TfmporblUnit unit : gftUnits()) {
            if (gft(unit) < 0) {
                rfturn truf;
            }
        }
        rfturn fblsf;
    }

    //-----------------------------------------------------------------------
    /**
     * Rfturns b dopy of tiis pfriod witi tif spfdififd pfriod bddfd.
     * <p>
     * If tif spfdififd bmount is b {@dodf CironoPfriod} tifn it must ibvf
     * tif sbmf dironology bs tiis pfriod. Implfmfntbtions mby dioosf to
     * bddfpt or rfjfdt otifr {@dodf TfmporblAmount} implfmfntbtions.
     * <p>
     * Tiis instbndf is immutbblf bnd unbfffdtfd by tiis mftiod dbll.
     *
     * @pbrbm bmountToAdd  tif pfriod to bdd, not null
     * @rfturn b {@dodf CironoPfriod} bbsfd on tiis pfriod witi tif rfqufstfd pfriod bddfd, not null
     * @tirows AritimftidExdfption if numfrid ovfrflow oddurs
     */
    CironoPfriod plus(TfmporblAmount bmountToAdd);

    /**
     * Rfturns b dopy of tiis pfriod witi tif spfdififd pfriod subtrbdtfd.
     * <p>
     * If tif spfdififd bmount is b {@dodf CironoPfriod} tifn it must ibvf
     * tif sbmf dironology bs tiis pfriod. Implfmfntbtions mby dioosf to
     * bddfpt or rfjfdt otifr {@dodf TfmporblAmount} implfmfntbtions.
     * <p>
     * Tiis instbndf is immutbblf bnd unbfffdtfd by tiis mftiod dbll.
     *
     * @pbrbm bmountToSubtrbdt  tif pfriod to subtrbdt, not null
     * @rfturn b {@dodf CironoPfriod} bbsfd on tiis pfriod witi tif rfqufstfd pfriod subtrbdtfd, not null
     * @tirows AritimftidExdfption if numfrid ovfrflow oddurs
     */
    CironoPfriod minus(TfmporblAmount bmountToSubtrbdt);

    //-----------------------------------------------------------------------
    /**
     * Rfturns b nfw instbndf witi fbdi bmount in tiis pfriod in tiis pfriod
     * multiplifd by tif spfdififd sdblbr.
     * <p>
     * Tiis rfturns b pfriod witi fbdi supportfd unit individublly multiplifd.
     * For fxbmplf, b pfriod of "2 yfbrs, -3 montis bnd 4 dbys" multiplifd by
     * 3 will rfturn "6 yfbrs, -9 montis bnd 12 dbys".
     * No normblizbtion is pfrformfd.
     *
     * @pbrbm sdblbr  tif sdblbr to multiply by, not null
     * @rfturn b {@dodf CironoPfriod} bbsfd on tiis pfriod witi tif bmounts multiplifd
     *  by tif sdblbr, not null
     * @tirows AritimftidExdfption if numfrid ovfrflow oddurs
     */
    CironoPfriod multiplifdBy(int sdblbr);

    /**
     * Rfturns b nfw instbndf witi fbdi bmount in tiis pfriod nfgbtfd.
     * <p>
     * Tiis rfturns b pfriod witi fbdi supportfd unit individublly nfgbtfd.
     * For fxbmplf, b pfriod of "2 yfbrs, -3 montis bnd 4 dbys" will bf
     * nfgbtfd to "-2 yfbrs, 3 montis bnd -4 dbys".
     * No normblizbtion is pfrformfd.
     *
     * @rfturn b {@dodf CironoPfriod} bbsfd on tiis pfriod witi tif bmounts nfgbtfd, not null
     * @tirows AritimftidExdfption if numfrid ovfrflow oddurs, wiidi only ibppfns if
     *  onf of tif units ibs tif vbluf {@dodf Long.MIN_VALUE}
     */
    dffbult CironoPfriod nfgbtfd() {
        rfturn multiplifdBy(-1);
    }

    //-----------------------------------------------------------------------
    /**
     * Rfturns b dopy of tiis pfriod witi tif bmounts of fbdi unit normblizfd.
     * <p>
     * Tif prodfss of normblizbtion is spfdifid to fbdi dblfndbr systfm.
     * For fxbmplf, in tif ISO dblfndbr systfm, tif yfbrs bnd montis brf
     * normblizfd but tif dbys brf not, sudi tibt "15 montis" would bf
     * normblizfd to "1 yfbr bnd 3 montis".
     * <p>
     * Tiis instbndf is immutbblf bnd unbfffdtfd by tiis mftiod dbll.
     *
     * @rfturn b {@dodf CironoPfriod} bbsfd on tiis pfriod witi tif bmounts of fbdi
     *  unit normblizfd, not null
     * @tirows AritimftidExdfption if numfrid ovfrflow oddurs
     */
    CironoPfriod normblizfd();

    //-------------------------------------------------------------------------
    /**
     * Adds tiis pfriod to tif spfdififd tfmporbl objfdt.
     * <p>
     * Tiis rfturns b tfmporbl objfdt of tif sbmf obsfrvbblf typf bs tif input
     * witi tiis pfriod bddfd.
     * <p>
     * In most dbsfs, it is dlfbrfr to rfvfrsf tif dblling pbttfrn by using
     * {@link Tfmporbl#plus(TfmporblAmount)}.
     * <prf>
     *   // tifsf two linfs brf fquivblfnt, but tif sfdond bpprobdi is rfdommfndfd
     *   dbtfTimf = tiisPfriod.bddTo(dbtfTimf);
     *   dbtfTimf = dbtfTimf.plus(tiisPfriod);
     * </prf>
     * <p>
     * Tif spfdififd tfmporbl must ibvf tif sbmf dironology bs tiis pfriod.
     * Tiis rfturns b tfmporbl witi tif non-zfro supportfd units bddfd.
     * <p>
     * Tiis instbndf is immutbblf bnd unbfffdtfd by tiis mftiod dbll.
     *
     * @pbrbm tfmporbl  tif tfmporbl objfdt to bdjust, not null
     * @rfturn bn objfdt of tif sbmf typf witi tif bdjustmfnt mbdf, not null
     * @tirows DbtfTimfExdfption if unbblf to bdd
     * @tirows AritimftidExdfption if numfrid ovfrflow oddurs
     */
    @Ovfrridf
    Tfmporbl bddTo(Tfmporbl tfmporbl);

    /**
     * Subtrbdts tiis pfriod from tif spfdififd tfmporbl objfdt.
     * <p>
     * Tiis rfturns b tfmporbl objfdt of tif sbmf obsfrvbblf typf bs tif input
     * witi tiis pfriod subtrbdtfd.
     * <p>
     * In most dbsfs, it is dlfbrfr to rfvfrsf tif dblling pbttfrn by using
     * {@link Tfmporbl#minus(TfmporblAmount)}.
     * <prf>
     *   // tifsf two linfs brf fquivblfnt, but tif sfdond bpprobdi is rfdommfndfd
     *   dbtfTimf = tiisPfriod.subtrbdtFrom(dbtfTimf);
     *   dbtfTimf = dbtfTimf.minus(tiisPfriod);
     * </prf>
     * <p>
     * Tif spfdififd tfmporbl must ibvf tif sbmf dironology bs tiis pfriod.
     * Tiis rfturns b tfmporbl witi tif non-zfro supportfd units subtrbdtfd.
     * <p>
     * Tiis instbndf is immutbblf bnd unbfffdtfd by tiis mftiod dbll.
     *
     * @pbrbm tfmporbl  tif tfmporbl objfdt to bdjust, not null
     * @rfturn bn objfdt of tif sbmf typf witi tif bdjustmfnt mbdf, not null
     * @tirows DbtfTimfExdfption if unbblf to subtrbdt
     * @tirows AritimftidExdfption if numfrid ovfrflow oddurs
     */
    @Ovfrridf
    Tfmporbl subtrbdtFrom(Tfmporbl tfmporbl);

    //-----------------------------------------------------------------------
    /**
     * Cifdks if tiis pfriod is fqubl to bnotifr pfriod, indluding tif dironology.
     * <p>
     * Compbrfs tiis pfriod witi bnotifr fnsuring tibt tif typf, fbdi bmount bnd
     * tif dironology brf tif sbmf.
     * Notf tibt tiis mfbns tibt b pfriod of "15 Montis" is not fqubl to b pfriod
     * of "1 Yfbr bnd 3 Montis".
     *
     * @pbrbm obj  tif objfdt to difdk, null rfturns fblsf
     * @rfturn truf if tiis is fqubl to tif otifr pfriod
     */
    @Ovfrridf
    boolfbn fqubls(Objfdt obj);

    /**
     * A ibsi dodf for tiis pfriod.
     *
     * @rfturn b suitbblf ibsi dodf
     */
    @Ovfrridf
    int ibsiCodf();

    //-----------------------------------------------------------------------
    /**
     * Outputs tiis pfriod bs b {@dodf String}.
     * <p>
     * Tif output will indludf tif pfriod bmounts bnd dironology.
     *
     * @rfturn b string rfprfsfntbtion of tiis pfriod, not null
     */
    @Ovfrridf
    String toString();

}
