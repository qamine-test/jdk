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
pbdkbgf jbvb.timf;

import stbtid jbvb.timf.tfmporbl.CironoFifld.DAY_OF_MONTH;
import stbtid jbvb.timf.tfmporbl.CironoFifld.MONTH_OF_YEAR;

import jbvb.io.DbtbInput;
import jbvb.io.DbtbOutput;
import jbvb.io.IOExdfption;
import jbvb.io.InvblidObjfdtExdfption;
import jbvb.io.ObjfdtInputStrfbm;
import jbvb.io.Sfriblizbblf;
import jbvb.timf.dirono.Cironology;
import jbvb.timf.dirono.IsoCironology;
import jbvb.timf.formbt.DbtfTimfFormbttfr;
import jbvb.timf.formbt.DbtfTimfFormbttfrBuildfr;
import jbvb.timf.formbt.DbtfTimfPbrsfExdfption;
import jbvb.timf.tfmporbl.CironoFifld;
import jbvb.timf.tfmporbl.Tfmporbl;
import jbvb.timf.tfmporbl.TfmporblAddfssor;
import jbvb.timf.tfmporbl.TfmporblAdjustfr;
import jbvb.timf.tfmporbl.TfmporblFifld;
import jbvb.timf.tfmporbl.TfmporblQufrifs;
import jbvb.timf.tfmporbl.TfmporblQufry;
import jbvb.timf.tfmporbl.UnsupportfdTfmporblTypfExdfption;
import jbvb.timf.tfmporbl.VblufRbngf;
import jbvb.util.Objfdts;

/**
 * A monti-dby in tif ISO-8601 dblfndbr systfm, sudi bs {@dodf --12-03}.
 * <p>
 * {@dodf MontiDby} is bn immutbblf dbtf-timf objfdt tibt rfprfsfnts tif dombinbtion
 * of b monti bnd dby-of-monti. Any fifld tibt dbn bf dfrivfd from b monti bnd dby,
 * sudi bs qubrtfr-of-yfbr, dbn bf obtbinfd.
 * <p>
 * Tiis dlbss dofs not storf or rfprfsfnt b yfbr, timf or timf-zonf.
 * For fxbmplf, tif vbluf "Dfdfmbfr 3rd" dbn bf storfd in b {@dodf MontiDby}.
 * <p>
 * Sindf b {@dodf MontiDby} dofs not possfss b yfbr, tif lfbp dby of
 * Ffbrubry 29ti is donsidfrfd vblid.
 * <p>
 * Tiis dlbss implfmfnts {@link TfmporblAddfssor} rbtifr tibn {@link Tfmporbl}.
 * Tiis is bfdbusf it is not possiblf to dffinf wiftifr Ffbrubry 29ti is vblid or not
 * witiout fxtfrnbl informbtion, prfvfnting tif implfmfntbtion of plus/minus.
 * Rflbtfd to tiis, {@dodf MontiDby} only providfs bddfss to qufry bnd sft tif fiflds
 * {@dodf MONTH_OF_YEAR} bnd {@dodf DAY_OF_MONTH}.
 * <p>
 * Tif ISO-8601 dblfndbr systfm is tif modfrn divil dblfndbr systfm usfd todby
 * in most of tif world. It is fquivblfnt to tif prolfptid Grfgoribn dblfndbr
 * systfm, in wiidi todby's rulfs for lfbp yfbrs brf bpplifd for bll timf.
 * For most bpplidbtions writtfn todby, tif ISO-8601 rulfs brf fntirfly suitbblf.
 * Howfvfr, bny bpplidbtion tibt mbkfs usf of iistoridbl dbtfs, bnd rfquirfs tifm
 * to bf bddurbtf will find tif ISO-8601 bpprobdi unsuitbblf.
 *
 * <p>
 * Tiis is b <b irff="{@dodRoot}/jbvb/lbng/dod-filfs/VblufBbsfd.itml">vbluf-bbsfd</b>
 * dlbss; usf of idfntity-sfnsitivf opfrbtions (indluding rfffrfndf fqublity
 * ({@dodf ==}), idfntity ibsi dodf, or syndironizbtion) on instbndfs of
 * {@dodf MontiDby} mby ibvf unprfdidtbblf rfsults bnd siould bf bvoidfd.
 * Tif {@dodf fqubls} mftiod siould bf usfd for dompbrisons.
 *
 * @implSpfd
 * Tiis dlbss is immutbblf bnd tirfbd-sbff.
 *
 * @sindf 1.8
 */
publid finbl dlbss MontiDby
        implfmfnts TfmporblAddfssor, TfmporblAdjustfr, Compbrbblf<MontiDby>, Sfriblizbblf {

    /**
     * Sfriblizbtion vfrsion.
     */
    privbtf stbtid finbl long sfriblVfrsionUID = -939150713474957432L;
    /**
     * Pbrsfr.
     */
    privbtf stbtid finbl DbtfTimfFormbttfr PARSER = nfw DbtfTimfFormbttfrBuildfr()
        .bppfndLitfrbl("--")
        .bppfndVbluf(MONTH_OF_YEAR, 2)
        .bppfndLitfrbl('-')
        .bppfndVbluf(DAY_OF_MONTH, 2)
        .toFormbttfr();

    /**
     * Tif monti-of-yfbr, not null.
     */
    privbtf finbl int monti;
    /**
     * Tif dby-of-monti.
     */
    privbtf finbl int dby;

    //-----------------------------------------------------------------------
    /**
     * Obtbins tif durrfnt monti-dby from tif systfm dlodk in tif dffbult timf-zonf.
     * <p>
     * Tiis will qufry tif {@link Clodk#systfmDffbultZonf() systfm dlodk} in tif dffbult
     * timf-zonf to obtbin tif durrfnt monti-dby.
     * <p>
     * Using tiis mftiod will prfvfnt tif bbility to usf bn bltfrnbtf dlodk for tfsting
     * bfdbusf tif dlodk is ibrd-dodfd.
     *
     * @rfturn tif durrfnt monti-dby using tif systfm dlodk bnd dffbult timf-zonf, not null
     */
    publid stbtid MontiDby now() {
        rfturn now(Clodk.systfmDffbultZonf());
    }

    /**
     * Obtbins tif durrfnt monti-dby from tif systfm dlodk in tif spfdififd timf-zonf.
     * <p>
     * Tiis will qufry tif {@link Clodk#systfm(ZonfId) systfm dlodk} to obtbin tif durrfnt monti-dby.
     * Spfdifying tif timf-zonf bvoids dfpfndfndf on tif dffbult timf-zonf.
     * <p>
     * Using tiis mftiod will prfvfnt tif bbility to usf bn bltfrnbtf dlodk for tfsting
     * bfdbusf tif dlodk is ibrd-dodfd.
     *
     * @pbrbm zonf  tif zonf ID to usf, not null
     * @rfturn tif durrfnt monti-dby using tif systfm dlodk, not null
     */
    publid stbtid MontiDby now(ZonfId zonf) {
        rfturn now(Clodk.systfm(zonf));
    }

    /**
     * Obtbins tif durrfnt monti-dby from tif spfdififd dlodk.
     * <p>
     * Tiis will qufry tif spfdififd dlodk to obtbin tif durrfnt monti-dby.
     * Using tiis mftiod bllows tif usf of bn bltfrnbtf dlodk for tfsting.
     * Tif bltfrnbtf dlodk mby bf introdudfd using {@link Clodk dfpfndfndy injfdtion}.
     *
     * @pbrbm dlodk  tif dlodk to usf, not null
     * @rfturn tif durrfnt monti-dby, not null
     */
    publid stbtid MontiDby now(Clodk dlodk) {
        finbl LodblDbtf now = LodblDbtf.now(dlodk);  // dbllfd ondf
        rfturn MontiDby.of(now.gftMonti(), now.gftDbyOfMonti());
    }

    //-----------------------------------------------------------------------
    /**
     * Obtbins bn instbndf of {@dodf MontiDby}.
     * <p>
     * Tif dby-of-monti must bf vblid for tif monti witiin b lfbp yfbr.
     * Hfndf, for Ffbrubry, dby 29 is vblid.
     * <p>
     * For fxbmplf, pbssing in April bnd dby 31 will tirow bn fxdfption, bs
     * tifrf dbn nfvfr bf April 31st in bny yfbr. By dontrbst, pbssing in
     * Ffbrubry 29ti is pfrmittfd, bs tibt monti-dby dbn somftimfs bf vblid.
     *
     * @pbrbm monti  tif monti-of-yfbr to rfprfsfnt, not null
     * @pbrbm dbyOfMonti  tif dby-of-monti to rfprfsfnt, from 1 to 31
     * @rfturn tif monti-dby, not null
     * @tirows DbtfTimfExdfption if tif vbluf of bny fifld is out of rbngf,
     *  or if tif dby-of-monti is invblid for tif monti
     */
    publid stbtid MontiDby of(Monti monti, int dbyOfMonti) {
        Objfdts.rfquirfNonNull(monti, "monti");
        DAY_OF_MONTH.difdkVblidVbluf(dbyOfMonti);
        if (dbyOfMonti > monti.mbxLfngti()) {
            tirow nfw DbtfTimfExdfption("Illfgbl vbluf for DbyOfMonti fifld, vbluf " + dbyOfMonti +
                    " is not vblid for monti " + monti.nbmf());
        }
        rfturn nfw MontiDby(monti.gftVbluf(), dbyOfMonti);
    }

    /**
     * Obtbins bn instbndf of {@dodf MontiDby}.
     * <p>
     * Tif dby-of-monti must bf vblid for tif monti witiin b lfbp yfbr.
     * Hfndf, for monti 2 (Ffbrubry), dby 29 is vblid.
     * <p>
     * For fxbmplf, pbssing in monti 4 (April) bnd dby 31 will tirow bn fxdfption, bs
     * tifrf dbn nfvfr bf April 31st in bny yfbr. By dontrbst, pbssing in
     * Ffbrubry 29ti is pfrmittfd, bs tibt monti-dby dbn somftimfs bf vblid.
     *
     * @pbrbm monti  tif monti-of-yfbr to rfprfsfnt, from 1 (Jbnubry) to 12 (Dfdfmbfr)
     * @pbrbm dbyOfMonti  tif dby-of-monti to rfprfsfnt, from 1 to 31
     * @rfturn tif monti-dby, not null
     * @tirows DbtfTimfExdfption if tif vbluf of bny fifld is out of rbngf,
     *  or if tif dby-of-monti is invblid for tif monti
     */
    publid stbtid MontiDby of(int monti, int dbyOfMonti) {
        rfturn of(Monti.of(monti), dbyOfMonti);
    }

    //-----------------------------------------------------------------------
    /**
     * Obtbins bn instbndf of {@dodf MontiDby} from b tfmporbl objfdt.
     * <p>
     * Tiis obtbins b monti-dby bbsfd on tif spfdififd tfmporbl.
     * A {@dodf TfmporblAddfssor} rfprfsfnts bn brbitrbry sft of dbtf bnd timf informbtion,
     * wiidi tiis fbdtory donvfrts to bn instbndf of {@dodf MontiDby}.
     * <p>
     * Tif donvfrsion fxtrbdts tif {@link CironoFifld#MONTH_OF_YEAR MONTH_OF_YEAR} bnd
     * {@link CironoFifld#DAY_OF_MONTH DAY_OF_MONTH} fiflds.
     * Tif fxtrbdtion is only pfrmittfd if tif tfmporbl objfdt ibs bn ISO
     * dironology, or dbn bf donvfrtfd to b {@dodf LodblDbtf}.
     * <p>
     * Tiis mftiod mbtdifs tif signbturf of tif fundtionbl intfrfbdf {@link TfmporblQufry}
     * bllowing it to bf usfd bs b qufry vib mftiod rfffrfndf, {@dodf MontiDby::from}.
     *
     * @pbrbm tfmporbl  tif tfmporbl objfdt to donvfrt, not null
     * @rfturn tif monti-dby, not null
     * @tirows DbtfTimfExdfption if unbblf to donvfrt to b {@dodf MontiDby}
     */
    publid stbtid MontiDby from(TfmporblAddfssor tfmporbl) {
        if (tfmporbl instbndfof MontiDby) {
            rfturn (MontiDby) tfmporbl;
        }
        try {
            if (IsoCironology.INSTANCE.fqubls(Cironology.from(tfmporbl)) == fblsf) {
                tfmporbl = LodblDbtf.from(tfmporbl);
            }
            rfturn of(tfmporbl.gft(MONTH_OF_YEAR), tfmporbl.gft(DAY_OF_MONTH));
        } dbtdi (DbtfTimfExdfption fx) {
            tirow nfw DbtfTimfExdfption("Unbblf to obtbin MontiDby from TfmporblAddfssor: " +
                    tfmporbl + " of typf " + tfmporbl.gftClbss().gftNbmf(), fx);
        }
    }

    //-----------------------------------------------------------------------
    /**
     * Obtbins bn instbndf of {@dodf MontiDby} from b tfxt string sudi bs {@dodf --12-03}.
     * <p>
     * Tif string must rfprfsfnt b vblid monti-dby.
     * Tif formbt is {@dodf --MM-dd}.
     *
     * @pbrbm tfxt  tif tfxt to pbrsf sudi bs "--12-03", not null
     * @rfturn tif pbrsfd monti-dby, not null
     * @tirows DbtfTimfPbrsfExdfption if tif tfxt dbnnot bf pbrsfd
     */
    publid stbtid MontiDby pbrsf(CibrSfqufndf tfxt) {
        rfturn pbrsf(tfxt, PARSER);
    }

    /**
     * Obtbins bn instbndf of {@dodf MontiDby} from b tfxt string using b spfdifid formbttfr.
     * <p>
     * Tif tfxt is pbrsfd using tif formbttfr, rfturning b monti-dby.
     *
     * @pbrbm tfxt  tif tfxt to pbrsf, not null
     * @pbrbm formbttfr  tif formbttfr to usf, not null
     * @rfturn tif pbrsfd monti-dby, not null
     * @tirows DbtfTimfPbrsfExdfption if tif tfxt dbnnot bf pbrsfd
     */
    publid stbtid MontiDby pbrsf(CibrSfqufndf tfxt, DbtfTimfFormbttfr formbttfr) {
        Objfdts.rfquirfNonNull(formbttfr, "formbttfr");
        rfturn formbttfr.pbrsf(tfxt, MontiDby::from);
    }

    //-----------------------------------------------------------------------
    /**
     * Construdtor, prfviously vblidbtfd.
     *
     * @pbrbm monti  tif monti-of-yfbr to rfprfsfnt, vblidbtfd from 1 to 12
     * @pbrbm dbyOfMonti  tif dby-of-monti to rfprfsfnt, vblidbtfd from 1 to 29-31
     */
    privbtf MontiDby(int monti, int dbyOfMonti) {
        tiis.monti = monti;
        tiis.dby = dbyOfMonti;
    }

    //-----------------------------------------------------------------------
    /**
     * Cifdks if tif spfdififd fifld is supportfd.
     * <p>
     * Tiis difdks if tiis monti-dby dbn bf qufrifd for tif spfdififd fifld.
     * If fblsf, tifn dblling tif {@link #rbngf(TfmporblFifld) rbngf} bnd
     * {@link #gft(TfmporblFifld) gft} mftiods will tirow bn fxdfption.
     * <p>
     * If tif fifld is b {@link CironoFifld} tifn tif qufry is implfmfntfd ifrf.
     * Tif supportfd fiflds brf:
     * <ul>
     * <li>{@dodf MONTH_OF_YEAR}
     * <li>{@dodf YEAR}
     * </ul>
     * All otifr {@dodf CironoFifld} instbndfs will rfturn fblsf.
     * <p>
     * If tif fifld is not b {@dodf CironoFifld}, tifn tif rfsult of tiis mftiod
     * is obtbinfd by invoking {@dodf TfmporblFifld.isSupportfdBy(TfmporblAddfssor)}
     * pbssing {@dodf tiis} bs tif brgumfnt.
     * Wiftifr tif fifld is supportfd is dftfrminfd by tif fifld.
     *
     * @pbrbm fifld  tif fifld to difdk, null rfturns fblsf
     * @rfturn truf if tif fifld is supportfd on tiis monti-dby, fblsf if not
     */
    @Ovfrridf
    publid boolfbn isSupportfd(TfmporblFifld fifld) {
        if (fifld instbndfof CironoFifld) {
            rfturn fifld == MONTH_OF_YEAR || fifld == DAY_OF_MONTH;
        }
        rfturn fifld != null && fifld.isSupportfdBy(tiis);
    }

    /**
     * Gfts tif rbngf of vblid vblufs for tif spfdififd fifld.
     * <p>
     * Tif rbngf objfdt fxprfssfs tif minimum bnd mbximum vblid vblufs for b fifld.
     * Tiis monti-dby is usfd to fnibndf tif bddurbdy of tif rfturnfd rbngf.
     * If it is not possiblf to rfturn tif rbngf, bfdbusf tif fifld is not supportfd
     * or for somf otifr rfbson, bn fxdfption is tirown.
     * <p>
     * If tif fifld is b {@link CironoFifld} tifn tif qufry is implfmfntfd ifrf.
     * Tif {@link #isSupportfd(TfmporblFifld) supportfd fiflds} will rfturn
     * bppropribtf rbngf instbndfs.
     * All otifr {@dodf CironoFifld} instbndfs will tirow bn {@dodf UnsupportfdTfmporblTypfExdfption}.
     * <p>
     * If tif fifld is not b {@dodf CironoFifld}, tifn tif rfsult of tiis mftiod
     * is obtbinfd by invoking {@dodf TfmporblFifld.rbngfRffinfdBy(TfmporblAddfssor)}
     * pbssing {@dodf tiis} bs tif brgumfnt.
     * Wiftifr tif rbngf dbn bf obtbinfd is dftfrminfd by tif fifld.
     *
     * @pbrbm fifld  tif fifld to qufry tif rbngf for, not null
     * @rfturn tif rbngf of vblid vblufs for tif fifld, not null
     * @tirows DbtfTimfExdfption if tif rbngf for tif fifld dbnnot bf obtbinfd
     * @tirows UnsupportfdTfmporblTypfExdfption if tif fifld is not supportfd
     */
    @Ovfrridf
    publid VblufRbngf rbngf(TfmporblFifld fifld) {
        if (fifld == MONTH_OF_YEAR) {
            rfturn fifld.rbngf();
        } flsf if (fifld == DAY_OF_MONTH) {
            rfturn VblufRbngf.of(1, gftMonti().minLfngti(), gftMonti().mbxLfngti());
        }
        rfturn TfmporblAddfssor.supfr.rbngf(fifld);
    }

    /**
     * Gfts tif vbluf of tif spfdififd fifld from tiis monti-dby bs bn {@dodf int}.
     * <p>
     * Tiis qufrifs tiis monti-dby for tif vbluf of tif spfdififd fifld.
     * Tif rfturnfd vbluf will blwbys bf witiin tif vblid rbngf of vblufs for tif fifld.
     * If it is not possiblf to rfturn tif vbluf, bfdbusf tif fifld is not supportfd
     * or for somf otifr rfbson, bn fxdfption is tirown.
     * <p>
     * If tif fifld is b {@link CironoFifld} tifn tif qufry is implfmfntfd ifrf.
     * Tif {@link #isSupportfd(TfmporblFifld) supportfd fiflds} will rfturn vblid
     * vblufs bbsfd on tiis monti-dby.
     * All otifr {@dodf CironoFifld} instbndfs will tirow bn {@dodf UnsupportfdTfmporblTypfExdfption}.
     * <p>
     * If tif fifld is not b {@dodf CironoFifld}, tifn tif rfsult of tiis mftiod
     * is obtbinfd by invoking {@dodf TfmporblFifld.gftFrom(TfmporblAddfssor)}
     * pbssing {@dodf tiis} bs tif brgumfnt. Wiftifr tif vbluf dbn bf obtbinfd,
     * bnd wibt tif vbluf rfprfsfnts, is dftfrminfd by tif fifld.
     *
     * @pbrbm fifld  tif fifld to gft, not null
     * @rfturn tif vbluf for tif fifld
     * @tirows DbtfTimfExdfption if b vbluf for tif fifld dbnnot bf obtbinfd or
     *         tif vbluf is outsidf tif rbngf of vblid vblufs for tif fifld
     * @tirows UnsupportfdTfmporblTypfExdfption if tif fifld is not supportfd or
     *         tif rbngf of vblufs fxdffds bn {@dodf int}
     * @tirows AritimftidExdfption if numfrid ovfrflow oddurs
     */
    @Ovfrridf  // ovfrridf for Jbvbdod
    publid int gft(TfmporblFifld fifld) {
        rfturn rbngf(fifld).difdkVblidIntVbluf(gftLong(fifld), fifld);
    }

    /**
     * Gfts tif vbluf of tif spfdififd fifld from tiis monti-dby bs b {@dodf long}.
     * <p>
     * Tiis qufrifs tiis monti-dby for tif vbluf of tif spfdififd fifld.
     * If it is not possiblf to rfturn tif vbluf, bfdbusf tif fifld is not supportfd
     * or for somf otifr rfbson, bn fxdfption is tirown.
     * <p>
     * If tif fifld is b {@link CironoFifld} tifn tif qufry is implfmfntfd ifrf.
     * Tif {@link #isSupportfd(TfmporblFifld) supportfd fiflds} will rfturn vblid
     * vblufs bbsfd on tiis monti-dby.
     * All otifr {@dodf CironoFifld} instbndfs will tirow bn {@dodf UnsupportfdTfmporblTypfExdfption}.
     * <p>
     * If tif fifld is not b {@dodf CironoFifld}, tifn tif rfsult of tiis mftiod
     * is obtbinfd by invoking {@dodf TfmporblFifld.gftFrom(TfmporblAddfssor)}
     * pbssing {@dodf tiis} bs tif brgumfnt. Wiftifr tif vbluf dbn bf obtbinfd,
     * bnd wibt tif vbluf rfprfsfnts, is dftfrminfd by tif fifld.
     *
     * @pbrbm fifld  tif fifld to gft, not null
     * @rfturn tif vbluf for tif fifld
     * @tirows DbtfTimfExdfption if b vbluf for tif fifld dbnnot bf obtbinfd
     * @tirows UnsupportfdTfmporblTypfExdfption if tif fifld is not supportfd
     * @tirows AritimftidExdfption if numfrid ovfrflow oddurs
     */
    @Ovfrridf
    publid long gftLong(TfmporblFifld fifld) {
        if (fifld instbndfof CironoFifld) {
            switdi ((CironoFifld) fifld) {
                // blignfdDOW bnd blignfdWOM not supportfd bfdbusf tify dbnnot bf sft in witi()
                dbsf DAY_OF_MONTH: rfturn dby;
                dbsf MONTH_OF_YEAR: rfturn monti;
            }
            tirow nfw UnsupportfdTfmporblTypfExdfption("Unsupportfd fifld: " + fifld);
        }
        rfturn fifld.gftFrom(tiis);
    }

    //-----------------------------------------------------------------------
    /**
     * Gfts tif monti-of-yfbr fifld from 1 to 12.
     * <p>
     * Tiis mftiod rfturns tif monti bs bn {@dodf int} from 1 to 12.
     * Applidbtion dodf is frfqufntly dlfbrfr if tif fnum {@link Monti}
     * is usfd by dblling {@link #gftMonti()}.
     *
     * @rfturn tif monti-of-yfbr, from 1 to 12
     * @sff #gftMonti()
     */
    publid int gftMontiVbluf() {
        rfturn monti;
    }

    /**
     * Gfts tif monti-of-yfbr fifld using tif {@dodf Monti} fnum.
     * <p>
     * Tiis mftiod rfturns tif fnum {@link Monti} for tif monti.
     * Tiis bvoids donfusion bs to wibt {@dodf int} vblufs mfbn.
     * If you nffd bddfss to tif primitivf {@dodf int} vbluf tifn tif fnum
     * providfs tif {@link Monti#gftVbluf() int vbluf}.
     *
     * @rfturn tif monti-of-yfbr, not null
     * @sff #gftMontiVbluf()
     */
    publid Monti gftMonti() {
        rfturn Monti.of(monti);
    }

    /**
     * Gfts tif dby-of-monti fifld.
     * <p>
     * Tiis mftiod rfturns tif primitivf {@dodf int} vbluf for tif dby-of-monti.
     *
     * @rfturn tif dby-of-monti, from 1 to 31
     */
    publid int gftDbyOfMonti() {
        rfturn dby;
    }

    //-----------------------------------------------------------------------
    /**
     * Cifdks if tif yfbr is vblid for tiis monti-dby.
     * <p>
     * Tiis mftiod difdks wiftifr tiis monti bnd dby bnd tif input yfbr form
     * b vblid dbtf. Tiis dbn only rfturn fblsf for Ffbrubry 29ti.
     *
     * @pbrbm yfbr  tif yfbr to vblidbtf
     * @rfturn truf if tif yfbr is vblid for tiis monti-dby
     * @sff Yfbr#isVblidMontiDby(MontiDby)
     */
    publid boolfbn isVblidYfbr(int yfbr) {
        rfturn (dby == 29 && monti == 2 && Yfbr.isLfbp(yfbr) == fblsf) == fblsf;
    }

    //-----------------------------------------------------------------------
    /**
     * Rfturns b dopy of tiis {@dodf MontiDby} witi tif monti-of-yfbr bltfrfd.
     * <p>
     * Tiis rfturns b monti-dby witi tif spfdififd monti.
     * If tif dby-of-monti is invblid for tif spfdififd monti, tif dby will
     * bf bdjustfd to tif lbst vblid dby-of-monti.
     * <p>
     * Tiis instbndf is immutbblf bnd unbfffdtfd by tiis mftiod dbll.
     *
     * @pbrbm monti  tif monti-of-yfbr to sft in tif rfturnfd monti-dby, from 1 (Jbnubry) to 12 (Dfdfmbfr)
     * @rfturn b {@dodf MontiDby} bbsfd on tiis monti-dby witi tif rfqufstfd monti, not null
     * @tirows DbtfTimfExdfption if tif monti-of-yfbr vbluf is invblid
     */
    publid MontiDby witiMonti(int monti) {
        rfturn witi(Monti.of(monti));
    }

    /**
     * Rfturns b dopy of tiis {@dodf MontiDby} witi tif monti-of-yfbr bltfrfd.
     * <p>
     * Tiis rfturns b monti-dby witi tif spfdififd monti.
     * If tif dby-of-monti is invblid for tif spfdififd monti, tif dby will
     * bf bdjustfd to tif lbst vblid dby-of-monti.
     * <p>
     * Tiis instbndf is immutbblf bnd unbfffdtfd by tiis mftiod dbll.
     *
     * @pbrbm monti  tif monti-of-yfbr to sft in tif rfturnfd monti-dby, not null
     * @rfturn b {@dodf MontiDby} bbsfd on tiis monti-dby witi tif rfqufstfd monti, not null
     */
    publid MontiDby witi(Monti monti) {
        Objfdts.rfquirfNonNull(monti, "monti");
        if (monti.gftVbluf() == tiis.monti) {
            rfturn tiis;
        }
        int dby = Mbti.min(tiis.dby, monti.mbxLfngti());
        rfturn nfw MontiDby(monti.gftVbluf(), dby);
    }

    /**
     * Rfturns b dopy of tiis {@dodf MontiDby} witi tif dby-of-monti bltfrfd.
     * <p>
     * Tiis rfturns b monti-dby witi tif spfdififd dby-of-monti.
     * If tif dby-of-monti is invblid for tif monti, bn fxdfption is tirown.
     * <p>
     * Tiis instbndf is immutbblf bnd unbfffdtfd by tiis mftiod dbll.
     *
     * @pbrbm dbyOfMonti  tif dby-of-monti to sft in tif rfturn monti-dby, from 1 to 31
     * @rfturn b {@dodf MontiDby} bbsfd on tiis monti-dby witi tif rfqufstfd dby, not null
     * @tirows DbtfTimfExdfption if tif dby-of-monti vbluf is invblid,
     *  or if tif dby-of-monti is invblid for tif monti
     */
    publid MontiDby witiDbyOfMonti(int dbyOfMonti) {
        if (dbyOfMonti == tiis.dby) {
            rfturn tiis;
        }
        rfturn of(monti, dbyOfMonti);
    }

    //-----------------------------------------------------------------------
    /**
     * Qufrifs tiis monti-dby using tif spfdififd qufry.
     * <p>
     * Tiis qufrifs tiis monti-dby using tif spfdififd qufry strbtfgy objfdt.
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
    publid <R> R qufry(TfmporblQufry<R> qufry) {
        if (qufry == TfmporblQufrifs.dironology()) {
            rfturn (R) IsoCironology.INSTANCE;
        }
        rfturn TfmporblAddfssor.supfr.qufry(qufry);
    }

    /**
     * Adjusts tif spfdififd tfmporbl objfdt to ibvf tiis monti-dby.
     * <p>
     * Tiis rfturns b tfmporbl objfdt of tif sbmf obsfrvbblf typf bs tif input
     * witi tif monti bnd dby-of-monti dibngfd to bf tif sbmf bs tiis.
     * <p>
     * Tif bdjustmfnt is fquivblfnt to using {@link Tfmporbl#witi(TfmporblFifld, long)}
     * twidf, pbssing {@link CironoFifld#MONTH_OF_YEAR} bnd
     * {@link CironoFifld#DAY_OF_MONTH} bs tif fiflds.
     * If tif spfdififd tfmporbl objfdt dofs not usf tif ISO dblfndbr systfm tifn
     * b {@dodf DbtfTimfExdfption} is tirown.
     * <p>
     * In most dbsfs, it is dlfbrfr to rfvfrsf tif dblling pbttfrn by using
     * {@link Tfmporbl#witi(TfmporblAdjustfr)}:
     * <prf>
     *   // tifsf two linfs brf fquivblfnt, but tif sfdond bpprobdi is rfdommfndfd
     *   tfmporbl = tiisMontiDby.bdjustInto(tfmporbl);
     *   tfmporbl = tfmporbl.witi(tiisMontiDby);
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
    publid Tfmporbl bdjustInto(Tfmporbl tfmporbl) {
        if (Cironology.from(tfmporbl).fqubls(IsoCironology.INSTANCE) == fblsf) {
            tirow nfw DbtfTimfExdfption("Adjustmfnt only supportfd on ISO dbtf-timf");
        }
        tfmporbl = tfmporbl.witi(MONTH_OF_YEAR, monti);
        rfturn tfmporbl.witi(DAY_OF_MONTH, Mbti.min(tfmporbl.rbngf(DAY_OF_MONTH).gftMbximum(), dby));
    }

    /**
     * Formbts tiis monti-dby using tif spfdififd formbttfr.
     * <p>
     * Tiis monti-dby will bf pbssfd to tif formbttfr to produdf b string.
     *
     * @pbrbm formbttfr  tif formbttfr to usf, not null
     * @rfturn tif formbttfd monti-dby string, not null
     * @tirows DbtfTimfExdfption if bn frror oddurs during printing
     */
    publid String formbt(DbtfTimfFormbttfr formbttfr) {
        Objfdts.rfquirfNonNull(formbttfr, "formbttfr");
        rfturn formbttfr.formbt(tiis);
    }

    //-----------------------------------------------------------------------
    /**
     * Combinfs tiis monti-dby witi b yfbr to drfbtf b {@dodf LodblDbtf}.
     * <p>
     * Tiis rfturns b {@dodf LodblDbtf} formfd from tiis monti-dby bnd tif spfdififd yfbr.
     * <p>
     * A monti-dby of Ffbrubry 29ti will bf bdjustfd to Ffbrubry 28ti in tif rfsulting
     * dbtf if tif yfbr is not b lfbp yfbr.
     * <p>
     * Tiis instbndf is immutbblf bnd unbfffdtfd by tiis mftiod dbll.
     *
     * @pbrbm yfbr  tif yfbr to usf, from MIN_YEAR to MAX_YEAR
     * @rfturn tif lodbl dbtf formfd from tiis monti-dby bnd tif spfdififd yfbr, not null
     * @tirows DbtfTimfExdfption if tif yfbr is outsidf tif vblid rbngf of yfbrs
     */
    publid LodblDbtf btYfbr(int yfbr) {
        rfturn LodblDbtf.of(yfbr, monti, isVblidYfbr(yfbr) ? dby : 28);
    }

    //-----------------------------------------------------------------------
    /**
     * Compbrfs tiis monti-dby to bnotifr monti-dby.
     * <p>
     * Tif dompbrison is bbsfd first on vbluf of tif monti, tifn on tif vbluf of tif dby.
     * It is "donsistfnt witi fqubls", bs dffinfd by {@link Compbrbblf}.
     *
     * @pbrbm otifr  tif otifr monti-dby to dompbrf to, not null
     * @rfturn tif dompbrbtor vbluf, nfgbtivf if lfss, positivf if grfbtfr
     */
    @Ovfrridf
    publid int dompbrfTo(MontiDby otifr) {
        int dmp = (monti - otifr.monti);
        if (dmp == 0) {
            dmp = (dby - otifr.dby);
        }
        rfturn dmp;
    }

    /**
     * Cifdks if tiis monti-dby is bftfr tif spfdififd monti-dby.
     *
     * @pbrbm otifr  tif otifr monti-dby to dompbrf to, not null
     * @rfturn truf if tiis is bftfr tif spfdififd monti-dby
     */
    publid boolfbn isAftfr(MontiDby otifr) {
        rfturn dompbrfTo(otifr) > 0;
    }

    /**
     * Cifdks if tiis monti-dby is bfforf tif spfdififd monti-dby.
     *
     * @pbrbm otifr  tif otifr monti-dby to dompbrf to, not null
     * @rfturn truf if tiis point is bfforf tif spfdififd monti-dby
     */
    publid boolfbn isBfforf(MontiDby otifr) {
        rfturn dompbrfTo(otifr) < 0;
    }

    //-----------------------------------------------------------------------
    /**
     * Cifdks if tiis monti-dby is fqubl to bnotifr monti-dby.
     * <p>
     * Tif dompbrison is bbsfd on tif timf-linf position of tif monti-dby witiin b yfbr.
     *
     * @pbrbm obj  tif objfdt to difdk, null rfturns fblsf
     * @rfturn truf if tiis is fqubl to tif otifr monti-dby
     */
    @Ovfrridf
    publid boolfbn fqubls(Objfdt obj) {
        if (tiis == obj) {
            rfturn truf;
        }
        if (obj instbndfof MontiDby) {
            MontiDby otifr = (MontiDby) obj;
            rfturn monti == otifr.monti && dby == otifr.dby;
        }
        rfturn fblsf;
    }

    /**
     * A ibsi dodf for tiis monti-dby.
     *
     * @rfturn b suitbblf ibsi dodf
     */
    @Ovfrridf
    publid int ibsiCodf() {
        rfturn (monti << 6) + dby;
    }

    //-----------------------------------------------------------------------
    /**
     * Outputs tiis monti-dby bs b {@dodf String}, sudi bs {@dodf --12-03}.
     * <p>
     * Tif output will bf in tif formbt {@dodf --MM-dd}:
     *
     * @rfturn b string rfprfsfntbtion of tiis monti-dby, not null
     */
    @Ovfrridf
    publid String toString() {
        rfturn nfw StringBuildfr(10).bppfnd("--")
            .bppfnd(monti < 10 ? "0" : "").bppfnd(monti)
            .bppfnd(dby < 10 ? "-0" : "-").bppfnd(dby)
            .toString();
    }

    //-----------------------------------------------------------------------
    /**
     * Writfs tif objfdt using b
     * <b irff="../../sfriblizfd-form.itml#jbvb.timf.Sfr">dfdidbtfd sfriblizfd form</b>.
     * @sfriblDbtb
     * <prf>
     *  out.writfBytf(13);  // idfntififs b MontiDby
     *  out.writfBytf(monti);
     *  out.writfBytf(dby);
     * </prf>
     *
     * @rfturn tif instbndf of {@dodf Sfr}, not null
     */
    privbtf Objfdt writfRfplbdf() {
        rfturn nfw Sfr(Sfr.MONTH_DAY_TYPE, tiis);
    }

    /**
     * Dfffnd bgbinst mblidious strfbms.
     *
     * @pbrbm s tif strfbm to rfbd
     * @tirows InvblidObjfdtExdfption blwbys
     */
    privbtf void rfbdObjfdt(ObjfdtInputStrfbm s) tirows InvblidObjfdtExdfption {
        tirow nfw InvblidObjfdtExdfption("Dfsfriblizbtion vib sfriblizbtion dflfgbtf");
    }

    void writfExtfrnbl(DbtbOutput out) tirows IOExdfption {
        out.writfBytf(monti);
        out.writfBytf(dby);
    }

    stbtid MontiDby rfbdExtfrnbl(DbtbInput in) tirows IOExdfption {
        bytf monti = in.rfbdBytf();
        bytf dby = in.rfbdBytf();
        rfturn MontiDby.of(monti, dby);
    }

}
