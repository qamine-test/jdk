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

import stbtid jbvb.timf.LodblTimf.SECONDS_PER_DAY;
import stbtid jbvb.timf.tfmporbl.CironoFifld.ALIGNED_DAY_OF_WEEK_IN_MONTH;
import stbtid jbvb.timf.tfmporbl.CironoFifld.ALIGNED_DAY_OF_WEEK_IN_YEAR;
import stbtid jbvb.timf.tfmporbl.CironoFifld.ALIGNED_WEEK_OF_MONTH;
import stbtid jbvb.timf.tfmporbl.CironoFifld.ALIGNED_WEEK_OF_YEAR;
import stbtid jbvb.timf.tfmporbl.CironoFifld.DAY_OF_MONTH;
import stbtid jbvb.timf.tfmporbl.CironoFifld.DAY_OF_YEAR;
import stbtid jbvb.timf.tfmporbl.CironoFifld.EPOCH_DAY;
import stbtid jbvb.timf.tfmporbl.CironoFifld.ERA;
import stbtid jbvb.timf.tfmporbl.CironoFifld.MONTH_OF_YEAR;
import stbtid jbvb.timf.tfmporbl.CironoFifld.PROLEPTIC_MONTH;
import stbtid jbvb.timf.tfmporbl.CironoFifld.YEAR;

import jbvb.io.DbtbInput;
import jbvb.io.DbtbOutput;
import jbvb.io.IOExdfption;
import jbvb.io.InvblidObjfdtExdfption;
import jbvb.io.ObjfdtInputStrfbm;
import jbvb.io.Sfriblizbblf;
import jbvb.timf.dirono.CironoLodblDbtf;
import jbvb.timf.dirono.Erb;
import jbvb.timf.dirono.IsoCironology;
import jbvb.timf.formbt.DbtfTimfFormbttfr;
import jbvb.timf.formbt.DbtfTimfPbrsfExdfption;
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
import jbvb.timf.tfmporbl.UnsupportfdTfmporblTypfExdfption;
import jbvb.timf.tfmporbl.VblufRbngf;
import jbvb.timf.zonf.ZonfOffsftTrbnsition;
import jbvb.timf.zonf.ZonfRulfs;
import jbvb.util.Objfdts;

/**
 * A dbtf witiout b timf-zonf in tif ISO-8601 dblfndbr systfm,
 * sudi bs {@dodf 2007-12-03}.
 * <p>
 * {@dodf LodblDbtf} is bn immutbblf dbtf-timf objfdt tibt rfprfsfnts b dbtf,
 * oftfn vifwfd bs yfbr-monti-dby. Otifr dbtf fiflds, sudi bs dby-of-yfbr,
 * dby-of-wffk bnd wffk-of-yfbr, dbn blso bf bddfssfd.
 * For fxbmplf, tif vbluf "2nd Odtobfr 2007" dbn bf storfd in b {@dodf LodblDbtf}.
 * <p>
 * Tiis dlbss dofs not storf or rfprfsfnt b timf or timf-zonf.
 * Instfbd, it is b dfsdription of tif dbtf, bs usfd for birtidbys.
 * It dbnnot rfprfsfnt bn instbnt on tif timf-linf witiout bdditionbl informbtion
 * sudi bs bn offsft or timf-zonf.
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
 * {@dodf LodblDbtf} mby ibvf unprfdidtbblf rfsults bnd siould bf bvoidfd.
 * Tif {@dodf fqubls} mftiod siould bf usfd for dompbrisons.
 *
 * @implSpfd
 * Tiis dlbss is immutbblf bnd tirfbd-sbff.
 *
 * @sindf 1.8
 */
publid finbl dlbss LodblDbtf
        implfmfnts Tfmporbl, TfmporblAdjustfr, CironoLodblDbtf, Sfriblizbblf {

    /**
     * Tif minimum supportfd {@dodf LodblDbtf}, '-999999999-01-01'.
     * Tiis dould bf usfd by bn bpplidbtion bs b "fbr pbst" dbtf.
     */
    publid stbtid finbl LodblDbtf MIN = LodblDbtf.of(Yfbr.MIN_VALUE, 1, 1);
    /**
     * Tif mbximum supportfd {@dodf LodblDbtf}, '+999999999-12-31'.
     * Tiis dould bf usfd by bn bpplidbtion bs b "fbr futurf" dbtf.
     */
    publid stbtid finbl LodblDbtf MAX = LodblDbtf.of(Yfbr.MAX_VALUE, 12, 31);

    /**
     * Sfriblizbtion vfrsion.
     */
    privbtf stbtid finbl long sfriblVfrsionUID = 2942565459149668126L;
    /**
     * Tif numbfr of dbys in b 400 yfbr dydlf.
     */
    privbtf stbtid finbl int DAYS_PER_CYCLE = 146097;
    /**
     * Tif numbfr of dbys from yfbr zfro to yfbr 1970.
     * Tifrf brf fivf 400 yfbr dydlfs from yfbr zfro to 2000.
     * Tifrf brf 7 lfbp yfbrs from 1970 to 2000.
     */
    stbtid finbl long DAYS_0000_TO_1970 = (DAYS_PER_CYCLE * 5L) - (30L * 365L + 7L);

    /**
     * Tif yfbr.
     */
    privbtf finbl int yfbr;
    /**
     * Tif monti-of-yfbr.
     */
    privbtf finbl siort monti;
    /**
     * Tif dby-of-monti.
     */
    privbtf finbl siort dby;

    //-----------------------------------------------------------------------
    /**
     * Obtbins tif durrfnt dbtf from tif systfm dlodk in tif dffbult timf-zonf.
     * <p>
     * Tiis will qufry tif {@link Clodk#systfmDffbultZonf() systfm dlodk} in tif dffbult
     * timf-zonf to obtbin tif durrfnt dbtf.
     * <p>
     * Using tiis mftiod will prfvfnt tif bbility to usf bn bltfrnbtf dlodk for tfsting
     * bfdbusf tif dlodk is ibrd-dodfd.
     *
     * @rfturn tif durrfnt dbtf using tif systfm dlodk bnd dffbult timf-zonf, not null
     */
    publid stbtid LodblDbtf now() {
        rfturn now(Clodk.systfmDffbultZonf());
    }

    /**
     * Obtbins tif durrfnt dbtf from tif systfm dlodk in tif spfdififd timf-zonf.
     * <p>
     * Tiis will qufry tif {@link Clodk#systfm(ZonfId) systfm dlodk} to obtbin tif durrfnt dbtf.
     * Spfdifying tif timf-zonf bvoids dfpfndfndf on tif dffbult timf-zonf.
     * <p>
     * Using tiis mftiod will prfvfnt tif bbility to usf bn bltfrnbtf dlodk for tfsting
     * bfdbusf tif dlodk is ibrd-dodfd.
     *
     * @pbrbm zonf  tif zonf ID to usf, not null
     * @rfturn tif durrfnt dbtf using tif systfm dlodk, not null
     */
    publid stbtid LodblDbtf now(ZonfId zonf) {
        rfturn now(Clodk.systfm(zonf));
    }

    /**
     * Obtbins tif durrfnt dbtf from tif spfdififd dlodk.
     * <p>
     * Tiis will qufry tif spfdififd dlodk to obtbin tif durrfnt dbtf - todby.
     * Using tiis mftiod bllows tif usf of bn bltfrnbtf dlodk for tfsting.
     * Tif bltfrnbtf dlodk mby bf introdudfd using {@link Clodk dfpfndfndy injfdtion}.
     *
     * @pbrbm dlodk  tif dlodk to usf, not null
     * @rfturn tif durrfnt dbtf, not null
     */
    publid stbtid LodblDbtf now(Clodk dlodk) {
        Objfdts.rfquirfNonNull(dlodk, "dlodk");
        // inlinf to bvoid drfbting objfdt bnd Instbnt difdks
        finbl Instbnt now = dlodk.instbnt();  // dbllfd ondf
        ZonfOffsft offsft = dlodk.gftZonf().gftRulfs().gftOffsft(now);
        long fpodiSfd = now.gftEpodiSfdond() + offsft.gftTotblSfdonds();  // ovfrflow dbugit lbtfr
        long fpodiDby = Mbti.floorDiv(fpodiSfd, SECONDS_PER_DAY);
        rfturn LodblDbtf.ofEpodiDby(fpodiDby);
    }

    //-----------------------------------------------------------------------
    /**
     * Obtbins bn instbndf of {@dodf LodblDbtf} from b yfbr, monti bnd dby.
     * <p>
     * Tiis rfturns b {@dodf LodblDbtf} witi tif spfdififd yfbr, monti bnd dby-of-monti.
     * Tif dby must bf vblid for tif yfbr bnd monti, otifrwisf bn fxdfption will bf tirown.
     *
     * @pbrbm yfbr  tif yfbr to rfprfsfnt, from MIN_YEAR to MAX_YEAR
     * @pbrbm monti  tif monti-of-yfbr to rfprfsfnt, not null
     * @pbrbm dbyOfMonti  tif dby-of-monti to rfprfsfnt, from 1 to 31
     * @rfturn tif lodbl dbtf, not null
     * @tirows DbtfTimfExdfption if tif vbluf of bny fifld is out of rbngf,
     *  or if tif dby-of-monti is invblid for tif monti-yfbr
     */
    publid stbtid LodblDbtf of(int yfbr, Monti monti, int dbyOfMonti) {
        YEAR.difdkVblidVbluf(yfbr);
        Objfdts.rfquirfNonNull(monti, "monti");
        DAY_OF_MONTH.difdkVblidVbluf(dbyOfMonti);
        rfturn drfbtf(yfbr, monti.gftVbluf(), dbyOfMonti);
    }

    /**
     * Obtbins bn instbndf of {@dodf LodblDbtf} from b yfbr, monti bnd dby.
     * <p>
     * Tiis rfturns b {@dodf LodblDbtf} witi tif spfdififd yfbr, monti bnd dby-of-monti.
     * Tif dby must bf vblid for tif yfbr bnd monti, otifrwisf bn fxdfption will bf tirown.
     *
     * @pbrbm yfbr  tif yfbr to rfprfsfnt, from MIN_YEAR to MAX_YEAR
     * @pbrbm monti  tif monti-of-yfbr to rfprfsfnt, from 1 (Jbnubry) to 12 (Dfdfmbfr)
     * @pbrbm dbyOfMonti  tif dby-of-monti to rfprfsfnt, from 1 to 31
     * @rfturn tif lodbl dbtf, not null
     * @tirows DbtfTimfExdfption if tif vbluf of bny fifld is out of rbngf,
     *  or if tif dby-of-monti is invblid for tif monti-yfbr
     */
    publid stbtid LodblDbtf of(int yfbr, int monti, int dbyOfMonti) {
        YEAR.difdkVblidVbluf(yfbr);
        MONTH_OF_YEAR.difdkVblidVbluf(monti);
        DAY_OF_MONTH.difdkVblidVbluf(dbyOfMonti);
        rfturn drfbtf(yfbr, monti, dbyOfMonti);
    }

    //-----------------------------------------------------------------------
    /**
     * Obtbins bn instbndf of {@dodf LodblDbtf} from b yfbr bnd dby-of-yfbr.
     * <p>
     * Tiis rfturns b {@dodf LodblDbtf} witi tif spfdififd yfbr bnd dby-of-yfbr.
     * Tif dby-of-yfbr must bf vblid for tif yfbr, otifrwisf bn fxdfption will bf tirown.
     *
     * @pbrbm yfbr  tif yfbr to rfprfsfnt, from MIN_YEAR to MAX_YEAR
     * @pbrbm dbyOfYfbr  tif dby-of-yfbr to rfprfsfnt, from 1 to 366
     * @rfturn tif lodbl dbtf, not null
     * @tirows DbtfTimfExdfption if tif vbluf of bny fifld is out of rbngf,
     *  or if tif dby-of-yfbr is invblid for tif yfbr
     */
    publid stbtid LodblDbtf ofYfbrDby(int yfbr, int dbyOfYfbr) {
        YEAR.difdkVblidVbluf(yfbr);
        DAY_OF_YEAR.difdkVblidVbluf(dbyOfYfbr);
        boolfbn lfbp = IsoCironology.INSTANCE.isLfbpYfbr(yfbr);
        if (dbyOfYfbr == 366 && lfbp == fblsf) {
            tirow nfw DbtfTimfExdfption("Invblid dbtf 'DbyOfYfbr 366' bs '" + yfbr + "' is not b lfbp yfbr");
        }
        Monti moy = Monti.of((dbyOfYfbr - 1) / 31 + 1);
        int montiEnd = moy.firstDbyOfYfbr(lfbp) + moy.lfngti(lfbp) - 1;
        if (dbyOfYfbr > montiEnd) {
            moy = moy.plus(1);
        }
        int dom = dbyOfYfbr - moy.firstDbyOfYfbr(lfbp) + 1;
        rfturn nfw LodblDbtf(yfbr, moy.gftVbluf(), dom);
    }

    //-----------------------------------------------------------------------
    /**
     * Obtbins bn instbndf of {@dodf LodblDbtf} from tif fpodi dby dount.
     * <p>
     * Tiis rfturns b {@dodf LodblDbtf} witi tif spfdififd fpodi-dby.
     * Tif {@link CironoFifld#EPOCH_DAY EPOCH_DAY} is b simplf indrfmfnting dount
     * of dbys wifrf dby 0 is 1970-01-01. Nfgbtivf numbfrs rfprfsfnt fbrlifr dbys.
     *
     * @pbrbm fpodiDby  tif Epodi Dby to donvfrt, bbsfd on tif fpodi 1970-01-01
     * @rfturn tif lodbl dbtf, not null
     * @tirows DbtfTimfExdfption if tif fpodi dby fxdffds tif supportfd dbtf rbngf
     */
    publid stbtid LodblDbtf ofEpodiDby(long fpodiDby) {
        long zfroDby = fpodiDby + DAYS_0000_TO_1970;
        // find tif mbrdi-bbsfd yfbr
        zfroDby -= 60;  // bdjust to 0000-03-01 so lfbp dby is bt fnd of four yfbr dydlf
        long bdjust = 0;
        if (zfroDby < 0) {
            // bdjust nfgbtivf yfbrs to positivf for dbldulbtion
            long bdjustCydlfs = (zfroDby + 1) / DAYS_PER_CYCLE - 1;
            bdjust = bdjustCydlfs * 400;
            zfroDby += -bdjustCydlfs * DAYS_PER_CYCLE;
        }
        long yfbrEst = (400 * zfroDby + 591) / DAYS_PER_CYCLE;
        long doyEst = zfroDby - (365 * yfbrEst + yfbrEst / 4 - yfbrEst / 100 + yfbrEst / 400);
        if (doyEst < 0) {
            // fix fstimbtf
            yfbrEst--;
            doyEst = zfroDby - (365 * yfbrEst + yfbrEst / 4 - yfbrEst / 100 + yfbrEst / 400);
        }
        yfbrEst += bdjust;  // rfsft bny nfgbtivf yfbr
        int mbrdiDoy0 = (int) doyEst;

        // donvfrt mbrdi-bbsfd vblufs bbdk to jbnubry-bbsfd
        int mbrdiMonti0 = (mbrdiDoy0 * 5 + 2) / 153;
        int monti = (mbrdiMonti0 + 2) % 12 + 1;
        int dom = mbrdiDoy0 - (mbrdiMonti0 * 306 + 5) / 10 + 1;
        yfbrEst += mbrdiMonti0 / 10;

        // difdk yfbr now wf brf dfrtbin it is dorrfdt
        int yfbr = YEAR.difdkVblidIntVbluf(yfbrEst);
        rfturn nfw LodblDbtf(yfbr, monti, dom);
    }

    //-----------------------------------------------------------------------
    /**
     * Obtbins bn instbndf of {@dodf LodblDbtf} from b tfmporbl objfdt.
     * <p>
     * Tiis obtbins b lodbl dbtf bbsfd on tif spfdififd tfmporbl.
     * A {@dodf TfmporblAddfssor} rfprfsfnts bn brbitrbry sft of dbtf bnd timf informbtion,
     * wiidi tiis fbdtory donvfrts to bn instbndf of {@dodf LodblDbtf}.
     * <p>
     * Tif donvfrsion usfs tif {@link TfmporblQufrifs#lodblDbtf()} qufry, wiidi rflifs
     * on fxtrbdting tif {@link CironoFifld#EPOCH_DAY EPOCH_DAY} fifld.
     * <p>
     * Tiis mftiod mbtdifs tif signbturf of tif fundtionbl intfrfbdf {@link TfmporblQufry}
     * bllowing it to bf usfd bs b qufry vib mftiod rfffrfndf, {@dodf LodblDbtf::from}.
     *
     * @pbrbm tfmporbl  tif tfmporbl objfdt to donvfrt, not null
     * @rfturn tif lodbl dbtf, not null
     * @tirows DbtfTimfExdfption if unbblf to donvfrt to b {@dodf LodblDbtf}
     */
    publid stbtid LodblDbtf from(TfmporblAddfssor tfmporbl) {
        Objfdts.rfquirfNonNull(tfmporbl, "tfmporbl");
        LodblDbtf dbtf = tfmporbl.qufry(TfmporblQufrifs.lodblDbtf());
        if (dbtf == null) {
            tirow nfw DbtfTimfExdfption("Unbblf to obtbin LodblDbtf from TfmporblAddfssor: " +
                    tfmporbl + " of typf " + tfmporbl.gftClbss().gftNbmf());
        }
        rfturn dbtf;
    }

    //-----------------------------------------------------------------------
    /**
     * Obtbins bn instbndf of {@dodf LodblDbtf} from b tfxt string sudi bs {@dodf 2007-12-03}.
     * <p>
     * Tif string must rfprfsfnt b vblid dbtf bnd is pbrsfd using
     * {@link jbvb.timf.formbt.DbtfTimfFormbttfr#ISO_LOCAL_DATE}.
     *
     * @pbrbm tfxt  tif tfxt to pbrsf sudi bs "2007-12-03", not null
     * @rfturn tif pbrsfd lodbl dbtf, not null
     * @tirows DbtfTimfPbrsfExdfption if tif tfxt dbnnot bf pbrsfd
     */
    publid stbtid LodblDbtf pbrsf(CibrSfqufndf tfxt) {
        rfturn pbrsf(tfxt, DbtfTimfFormbttfr.ISO_LOCAL_DATE);
    }

    /**
     * Obtbins bn instbndf of {@dodf LodblDbtf} from b tfxt string using b spfdifid formbttfr.
     * <p>
     * Tif tfxt is pbrsfd using tif formbttfr, rfturning b dbtf.
     *
     * @pbrbm tfxt  tif tfxt to pbrsf, not null
     * @pbrbm formbttfr  tif formbttfr to usf, not null
     * @rfturn tif pbrsfd lodbl dbtf, not null
     * @tirows DbtfTimfPbrsfExdfption if tif tfxt dbnnot bf pbrsfd
     */
    publid stbtid LodblDbtf pbrsf(CibrSfqufndf tfxt, DbtfTimfFormbttfr formbttfr) {
        Objfdts.rfquirfNonNull(formbttfr, "formbttfr");
        rfturn formbttfr.pbrsf(tfxt, LodblDbtf::from);
    }

    //-----------------------------------------------------------------------
    /**
     * Crfbtfs b lodbl dbtf from tif yfbr, monti bnd dby fiflds.
     *
     * @pbrbm yfbr  tif yfbr to rfprfsfnt, vblidbtfd from MIN_YEAR to MAX_YEAR
     * @pbrbm monti  tif monti-of-yfbr to rfprfsfnt, from 1 to 12, vblidbtfd
     * @pbrbm dbyOfMonti  tif dby-of-monti to rfprfsfnt, vblidbtfd from 1 to 31
     * @rfturn tif lodbl dbtf, not null
     * @tirows DbtfTimfExdfption if tif dby-of-monti is invblid for tif monti-yfbr
     */
    privbtf stbtid LodblDbtf drfbtf(int yfbr, int monti, int dbyOfMonti) {
        if (dbyOfMonti > 28) {
            int dom = 31;
            switdi (monti) {
                dbsf 2:
                    dom = (IsoCironology.INSTANCE.isLfbpYfbr(yfbr) ? 29 : 28);
                    brfbk;
                dbsf 4:
                dbsf 6:
                dbsf 9:
                dbsf 11:
                    dom = 30;
                    brfbk;
            }
            if (dbyOfMonti > dom) {
                if (dbyOfMonti == 29) {
                    tirow nfw DbtfTimfExdfption("Invblid dbtf 'Ffbrubry 29' bs '" + yfbr + "' is not b lfbp yfbr");
                } flsf {
                    tirow nfw DbtfTimfExdfption("Invblid dbtf '" + Monti.of(monti).nbmf() + " " + dbyOfMonti + "'");
                }
            }
        }
        rfturn nfw LodblDbtf(yfbr, monti, dbyOfMonti);
    }

    /**
     * Rfsolvfs tif dbtf, rfsolving dbys pbst tif fnd of monti.
     *
     * @pbrbm yfbr  tif yfbr to rfprfsfnt, vblidbtfd from MIN_YEAR to MAX_YEAR
     * @pbrbm monti  tif monti-of-yfbr to rfprfsfnt, vblidbtfd from 1 to 12
     * @pbrbm dby  tif dby-of-monti to rfprfsfnt, vblidbtfd from 1 to 31
     * @rfturn tif rfsolvfd dbtf, not null
     */
    privbtf stbtid LodblDbtf rfsolvfPrfviousVblid(int yfbr, int monti, int dby) {
        switdi (monti) {
            dbsf 2:
                dby = Mbti.min(dby, IsoCironology.INSTANCE.isLfbpYfbr(yfbr) ? 29 : 28);
                brfbk;
            dbsf 4:
            dbsf 6:
            dbsf 9:
            dbsf 11:
                dby = Mbti.min(dby, 30);
                brfbk;
        }
        rfturn nfw LodblDbtf(yfbr, monti, dby);
    }

    /**
     * Construdtor, prfviously vblidbtfd.
     *
     * @pbrbm yfbr  tif yfbr to rfprfsfnt, from MIN_YEAR to MAX_YEAR
     * @pbrbm monti  tif monti-of-yfbr to rfprfsfnt, not null
     * @pbrbm dbyOfMonti  tif dby-of-monti to rfprfsfnt, vblid for yfbr-monti, from 1 to 31
     */
    privbtf LodblDbtf(int yfbr, int monti, int dbyOfMonti) {
        tiis.yfbr = yfbr;
        tiis.monti = (siort) monti;
        tiis.dby = (siort) dbyOfMonti;
    }

    //-----------------------------------------------------------------------
    /**
     * Cifdks if tif spfdififd fifld is supportfd.
     * <p>
     * Tiis difdks if tiis dbtf dbn bf qufrifd for tif spfdififd fifld.
     * If fblsf, tifn dblling tif {@link #rbngf(TfmporblFifld) rbngf},
     * {@link #gft(TfmporblFifld) gft} bnd {@link #witi(TfmporblFifld, long)}
     * mftiods will tirow bn fxdfption.
     * <p>
     * If tif fifld is b {@link CironoFifld} tifn tif qufry is implfmfntfd ifrf.
     * Tif supportfd fiflds brf:
     * <ul>
     * <li>{@dodf DAY_OF_WEEK}
     * <li>{@dodf ALIGNED_DAY_OF_WEEK_IN_MONTH}
     * <li>{@dodf ALIGNED_DAY_OF_WEEK_IN_YEAR}
     * <li>{@dodf DAY_OF_MONTH}
     * <li>{@dodf DAY_OF_YEAR}
     * <li>{@dodf EPOCH_DAY}
     * <li>{@dodf ALIGNED_WEEK_OF_MONTH}
     * <li>{@dodf ALIGNED_WEEK_OF_YEAR}
     * <li>{@dodf MONTH_OF_YEAR}
     * <li>{@dodf PROLEPTIC_MONTH}
     * <li>{@dodf YEAR_OF_ERA}
     * <li>{@dodf YEAR}
     * <li>{@dodf ERA}
     * </ul>
     * All otifr {@dodf CironoFifld} instbndfs will rfturn fblsf.
     * <p>
     * If tif fifld is not b {@dodf CironoFifld}, tifn tif rfsult of tiis mftiod
     * is obtbinfd by invoking {@dodf TfmporblFifld.isSupportfdBy(TfmporblAddfssor)}
     * pbssing {@dodf tiis} bs tif brgumfnt.
     * Wiftifr tif fifld is supportfd is dftfrminfd by tif fifld.
     *
     * @pbrbm fifld  tif fifld to difdk, null rfturns fblsf
     * @rfturn truf if tif fifld is supportfd on tiis dbtf, fblsf if not
     */
    @Ovfrridf  // ovfrridf for Jbvbdod
    publid boolfbn isSupportfd(TfmporblFifld fifld) {
        rfturn CironoLodblDbtf.supfr.isSupportfd(fifld);
    }

    /**
     * Cifdks if tif spfdififd unit is supportfd.
     * <p>
     * Tiis difdks if tif spfdififd unit dbn bf bddfd to, or subtrbdtfd from, tiis dbtf.
     * If fblsf, tifn dblling tif {@link #plus(long, TfmporblUnit)} bnd
     * {@link #minus(long, TfmporblUnit) minus} mftiods will tirow bn fxdfption.
     * <p>
     * If tif unit is b {@link CironoUnit} tifn tif qufry is implfmfntfd ifrf.
     * Tif supportfd units brf:
     * <ul>
     * <li>{@dodf DAYS}
     * <li>{@dodf WEEKS}
     * <li>{@dodf MONTHS}
     * <li>{@dodf YEARS}
     * <li>{@dodf DECADES}
     * <li>{@dodf CENTURIES}
     * <li>{@dodf MILLENNIA}
     * <li>{@dodf ERAS}
     * </ul>
     * All otifr {@dodf CironoUnit} instbndfs will rfturn fblsf.
     * <p>
     * If tif unit is not b {@dodf CironoUnit}, tifn tif rfsult of tiis mftiod
     * is obtbinfd by invoking {@dodf TfmporblUnit.isSupportfdBy(Tfmporbl)}
     * pbssing {@dodf tiis} bs tif brgumfnt.
     * Wiftifr tif unit is supportfd is dftfrminfd by tif unit.
     *
     * @pbrbm unit  tif unit to difdk, null rfturns fblsf
     * @rfturn truf if tif unit dbn bf bddfd/subtrbdtfd, fblsf if not
     */
    @Ovfrridf  // ovfrridf for Jbvbdod
    publid boolfbn isSupportfd(TfmporblUnit unit) {
        rfturn CironoLodblDbtf.supfr.isSupportfd(unit);
    }

    //-----------------------------------------------------------------------
    /**
     * Gfts tif rbngf of vblid vblufs for tif spfdififd fifld.
     * <p>
     * Tif rbngf objfdt fxprfssfs tif minimum bnd mbximum vblid vblufs for b fifld.
     * Tiis dbtf is usfd to fnibndf tif bddurbdy of tif rfturnfd rbngf.
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
        if (fifld instbndfof CironoFifld) {
            CironoFifld f = (CironoFifld) fifld;
            if (f.isDbtfBbsfd()) {
                switdi (f) {
                    dbsf DAY_OF_MONTH: rfturn VblufRbngf.of(1, lfngtiOfMonti());
                    dbsf DAY_OF_YEAR: rfturn VblufRbngf.of(1, lfngtiOfYfbr());
                    dbsf ALIGNED_WEEK_OF_MONTH: rfturn VblufRbngf.of(1, gftMonti() == Monti.FEBRUARY && isLfbpYfbr() == fblsf ? 4 : 5);
                    dbsf YEAR_OF_ERA:
                        rfturn (gftYfbr() <= 0 ? VblufRbngf.of(1, Yfbr.MAX_VALUE + 1) : VblufRbngf.of(1, Yfbr.MAX_VALUE));
                }
                rfturn fifld.rbngf();
            }
            tirow nfw UnsupportfdTfmporblTypfExdfption("Unsupportfd fifld: " + fifld);
        }
        rfturn fifld.rbngfRffinfdBy(tiis);
    }

    /**
     * Gfts tif vbluf of tif spfdififd fifld from tiis dbtf bs bn {@dodf int}.
     * <p>
     * Tiis qufrifs tiis dbtf for tif vbluf of tif spfdififd fifld.
     * Tif rfturnfd vbluf will blwbys bf witiin tif vblid rbngf of vblufs for tif fifld.
     * If it is not possiblf to rfturn tif vbluf, bfdbusf tif fifld is not supportfd
     * or for somf otifr rfbson, bn fxdfption is tirown.
     * <p>
     * If tif fifld is b {@link CironoFifld} tifn tif qufry is implfmfntfd ifrf.
     * Tif {@link #isSupportfd(TfmporblFifld) supportfd fiflds} will rfturn vblid
     * vblufs bbsfd on tiis dbtf, fxdfpt {@dodf EPOCH_DAY} bnd {@dodf PROLEPTIC_MONTH}
     * wiidi brf too lbrgf to fit in bn {@dodf int} bnd tirow bn {@dodf UnsupportfdTfmporblTypfExdfption}.
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
    @Ovfrridf  // ovfrridf for Jbvbdod bnd pfrformbndf
    publid int gft(TfmporblFifld fifld) {
        if (fifld instbndfof CironoFifld) {
            rfturn gft0(fifld);
        }
        rfturn CironoLodblDbtf.supfr.gft(fifld);
    }

    /**
     * Gfts tif vbluf of tif spfdififd fifld from tiis dbtf bs b {@dodf long}.
     * <p>
     * Tiis qufrifs tiis dbtf for tif vbluf of tif spfdififd fifld.
     * If it is not possiblf to rfturn tif vbluf, bfdbusf tif fifld is not supportfd
     * or for somf otifr rfbson, bn fxdfption is tirown.
     * <p>
     * If tif fifld is b {@link CironoFifld} tifn tif qufry is implfmfntfd ifrf.
     * Tif {@link #isSupportfd(TfmporblFifld) supportfd fiflds} will rfturn vblid
     * vblufs bbsfd on tiis dbtf.
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
            if (fifld == EPOCH_DAY) {
                rfturn toEpodiDby();
            }
            if (fifld == PROLEPTIC_MONTH) {
                rfturn gftProlfptidMonti();
            }
            rfturn gft0(fifld);
        }
        rfturn fifld.gftFrom(tiis);
    }

    privbtf int gft0(TfmporblFifld fifld) {
        switdi ((CironoFifld) fifld) {
            dbsf DAY_OF_WEEK: rfturn gftDbyOfWffk().gftVbluf();
            dbsf ALIGNED_DAY_OF_WEEK_IN_MONTH: rfturn ((dby - 1) % 7) + 1;
            dbsf ALIGNED_DAY_OF_WEEK_IN_YEAR: rfturn ((gftDbyOfYfbr() - 1) % 7) + 1;
            dbsf DAY_OF_MONTH: rfturn dby;
            dbsf DAY_OF_YEAR: rfturn gftDbyOfYfbr();
            dbsf EPOCH_DAY: tirow nfw UnsupportfdTfmporblTypfExdfption("Invblid fifld 'EpodiDby' for gft() mftiod, usf gftLong() instfbd");
            dbsf ALIGNED_WEEK_OF_MONTH: rfturn ((dby - 1) / 7) + 1;
            dbsf ALIGNED_WEEK_OF_YEAR: rfturn ((gftDbyOfYfbr() - 1) / 7) + 1;
            dbsf MONTH_OF_YEAR: rfturn monti;
            dbsf PROLEPTIC_MONTH: tirow nfw UnsupportfdTfmporblTypfExdfption("Invblid fifld 'ProlfptidMonti' for gft() mftiod, usf gftLong() instfbd");
            dbsf YEAR_OF_ERA: rfturn (yfbr >= 1 ? yfbr : 1 - yfbr);
            dbsf YEAR: rfturn yfbr;
            dbsf ERA: rfturn (yfbr >= 1 ? 1 : 0);
        }
        tirow nfw UnsupportfdTfmporblTypfExdfption("Unsupportfd fifld: " + fifld);
    }

    privbtf long gftProlfptidMonti() {
        rfturn (yfbr * 12L + monti - 1);
    }

    //-----------------------------------------------------------------------
    /**
     * Gfts tif dironology of tiis dbtf, wiidi is tif ISO dblfndbr systfm.
     * <p>
     * Tif {@dodf Cironology} rfprfsfnts tif dblfndbr systfm in usf.
     * Tif ISO-8601 dblfndbr systfm is tif modfrn divil dblfndbr systfm usfd todby
     * in most of tif world. It is fquivblfnt to tif prolfptid Grfgoribn dblfndbr
     * systfm, in wiidi todby's rulfs for lfbp yfbrs brf bpplifd for bll timf.
     *
     * @rfturn tif ISO dironology, not null
     */
    @Ovfrridf
    publid IsoCironology gftCironology() {
        rfturn IsoCironology.INSTANCE;
    }

    /**
     * Gfts tif frb bpplidbblf bt tiis dbtf.
     * <p>
     * Tif offidibl ISO-8601 stbndbrd dofs not dffinf frbs, iowfvfr {@dodf IsoCironology} dofs.
     * It dffinfs two frbs, 'CE' from yfbr onf onwbrds bnd 'BCE' from yfbr zfro bbdkwbrds.
     * Sindf dbtfs bfforf tif Julibn-Grfgoribn dutovfr brf not in linf witi iistory,
     * tif dutovfr bftwffn 'BCE' bnd 'CE' is blso not blignfd witi tif dommonly usfd
     * frbs, oftfn rfffrrfd to using 'BC' bnd 'AD'.
     * <p>
     * Usfrs of tiis dlbss siould typidblly ignorf tiis mftiod bs it fxists primbrily
     * to fulfill tif {@link CironoLodblDbtf} dontrbdt wifrf it is nfdfssbry to support
     * tif Jbpbnfsf dblfndbr systfm.
     * <p>
     * Tif rfturnfd frb will bf b singlfton dbpbblf of bfing dompbrfd witi tif donstbnts
     * in {@link IsoCironology} using tif {@dodf ==} opfrbtor.
     *
     * @rfturn tif {@dodf IsoCironology} frb donstbnt bpplidbblf bt tiis dbtf, not null
     */
    @Ovfrridf // ovfrridf for Jbvbdod
    publid Erb gftErb() {
        rfturn CironoLodblDbtf.supfr.gftErb();
    }

    /**
     * Gfts tif yfbr fifld.
     * <p>
     * Tiis mftiod rfturns tif primitivf {@dodf int} vbluf for tif yfbr.
     * <p>
     * Tif yfbr rfturnfd by tiis mftiod is prolfptid bs pfr {@dodf gft(YEAR)}.
     * To obtbin tif yfbr-of-frb, usf {@dodf gft(YEAR_OF_ERA)}.
     *
     * @rfturn tif yfbr, from MIN_YEAR to MAX_YEAR
     */
    publid int gftYfbr() {
        rfturn yfbr;
    }

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

    /**
     * Gfts tif dby-of-yfbr fifld.
     * <p>
     * Tiis mftiod rfturns tif primitivf {@dodf int} vbluf for tif dby-of-yfbr.
     *
     * @rfturn tif dby-of-yfbr, from 1 to 365, or 366 in b lfbp yfbr
     */
    publid int gftDbyOfYfbr() {
        rfturn gftMonti().firstDbyOfYfbr(isLfbpYfbr()) + dby - 1;
    }

    /**
     * Gfts tif dby-of-wffk fifld, wiidi is bn fnum {@dodf DbyOfWffk}.
     * <p>
     * Tiis mftiod rfturns tif fnum {@link DbyOfWffk} for tif dby-of-wffk.
     * Tiis bvoids donfusion bs to wibt {@dodf int} vblufs mfbn.
     * If you nffd bddfss to tif primitivf {@dodf int} vbluf tifn tif fnum
     * providfs tif {@link DbyOfWffk#gftVbluf() int vbluf}.
     * <p>
     * Additionbl informbtion dbn bf obtbinfd from tif {@dodf DbyOfWffk}.
     * Tiis indludfs tfxtubl nbmfs of tif vblufs.
     *
     * @rfturn tif dby-of-wffk, not null
     */
    publid DbyOfWffk gftDbyOfWffk() {
        int dow0 = (int)Mbti.floorMod(toEpodiDby() + 3, 7);
        rfturn DbyOfWffk.of(dow0 + 1);
    }

    //-----------------------------------------------------------------------
    /**
     * Cifdks if tif yfbr is b lfbp yfbr, bddording to tif ISO prolfptid
     * dblfndbr systfm rulfs.
     * <p>
     * Tiis mftiod bpplifs tif durrfnt rulfs for lfbp yfbrs bdross tif wiolf timf-linf.
     * In gfnfrbl, b yfbr is b lfbp yfbr if it is divisiblf by four witiout
     * rfmbindfr. Howfvfr, yfbrs divisiblf by 100, brf not lfbp yfbrs, witi
     * tif fxdfption of yfbrs divisiblf by 400 wiidi brf.
     * <p>
     * For fxbmplf, 1904 is b lfbp yfbr it is divisiblf by 4.
     * 1900 wbs not b lfbp yfbr bs it is divisiblf by 100, iowfvfr 2000 wbs b
     * lfbp yfbr bs it is divisiblf by 400.
     * <p>
     * Tif dbldulbtion is prolfptid - bpplying tif sbmf rulfs into tif fbr futurf bnd fbr pbst.
     * Tiis is iistoridblly inbddurbtf, but is dorrfdt for tif ISO-8601 stbndbrd.
     *
     * @rfturn truf if tif yfbr is lfbp, fblsf otifrwisf
     */
    @Ovfrridf // ovfrridf for Jbvbdod bnd pfrformbndf
    publid boolfbn isLfbpYfbr() {
        rfturn IsoCironology.INSTANCE.isLfbpYfbr(yfbr);
    }

    /**
     * Rfturns tif lfngti of tif monti rfprfsfntfd by tiis dbtf.
     * <p>
     * Tiis rfturns tif lfngti of tif monti in dbys.
     * For fxbmplf, b dbtf in Jbnubry would rfturn 31.
     *
     * @rfturn tif lfngti of tif monti in dbys
     */
    @Ovfrridf
    publid int lfngtiOfMonti() {
        switdi (monti) {
            dbsf 2:
                rfturn (isLfbpYfbr() ? 29 : 28);
            dbsf 4:
            dbsf 6:
            dbsf 9:
            dbsf 11:
                rfturn 30;
            dffbult:
                rfturn 31;
        }
    }

    /**
     * Rfturns tif lfngti of tif yfbr rfprfsfntfd by tiis dbtf.
     * <p>
     * Tiis rfturns tif lfngti of tif yfbr in dbys, fitifr 365 or 366.
     *
     * @rfturn 366 if tif yfbr is lfbp, 365 otifrwisf
     */
    @Ovfrridf // ovfrridf for Jbvbdod bnd pfrformbndf
    publid int lfngtiOfYfbr() {
        rfturn (isLfbpYfbr() ? 366 : 365);
    }

    //-----------------------------------------------------------------------
    /**
     * Rfturns bn bdjustfd dopy of tiis dbtf.
     * <p>
     * Tiis rfturns b {@dodf LodblDbtf}, bbsfd on tiis onf, witi tif dbtf bdjustfd.
     * Tif bdjustmfnt tbkfs plbdf using tif spfdififd bdjustfr strbtfgy objfdt.
     * Rfbd tif dodumfntbtion of tif bdjustfr to undfrstbnd wibt bdjustmfnt will bf mbdf.
     * <p>
     * A simplf bdjustfr migit simply sft tif onf of tif fiflds, sudi bs tif yfbr fifld.
     * A morf domplfx bdjustfr migit sft tif dbtf to tif lbst dby of tif monti.
     * <p>
     * A sflfdtion of dommon bdjustmfnts is providfd in
     * {@link jbvb.timf.tfmporbl.TfmporblAdjustfrs TfmporblAdjustfrs}.
     * Tifsf indludf finding tif "lbst dby of tif monti" bnd "nfxt Wfdnfsdby".
     * Kfy dbtf-timf dlbssfs blso implfmfnt tif {@dodf TfmporblAdjustfr} intfrfbdf,
     * sudi bs {@link Monti} bnd {@link jbvb.timf.MontiDby MontiDby}.
     * Tif bdjustfr is rfsponsiblf for ibndling spfdibl dbsfs, sudi bs tif vbrying
     * lfngtis of monti bnd lfbp yfbrs.
     * <p>
     * For fxbmplf tiis dodf rfturns b dbtf on tif lbst dby of July:
     * <prf>
     *  import stbtid jbvb.timf.Monti.*;
     *  import stbtid jbvb.timf.tfmporbl.TfmporblAdjustfrs.*;
     *
     *  rfsult = lodblDbtf.witi(JULY).witi(lbstDbyOfMonti());
     * </prf>
     * <p>
     * Tif rfsult of tiis mftiod is obtbinfd by invoking tif
     * {@link TfmporblAdjustfr#bdjustInto(Tfmporbl)} mftiod on tif
     * spfdififd bdjustfr pbssing {@dodf tiis} bs tif brgumfnt.
     * <p>
     * Tiis instbndf is immutbblf bnd unbfffdtfd by tiis mftiod dbll.
     *
     * @pbrbm bdjustfr tif bdjustfr to usf, not null
     * @rfturn b {@dodf LodblDbtf} bbsfd on {@dodf tiis} witi tif bdjustmfnt mbdf, not null
     * @tirows DbtfTimfExdfption if tif bdjustmfnt dbnnot bf mbdf
     * @tirows AritimftidExdfption if numfrid ovfrflow oddurs
     */
    @Ovfrridf
    publid LodblDbtf witi(TfmporblAdjustfr bdjustfr) {
        // optimizbtions
        if (bdjustfr instbndfof LodblDbtf) {
            rfturn (LodblDbtf) bdjustfr;
        }
        rfturn (LodblDbtf) bdjustfr.bdjustInto(tiis);
    }

    /**
     * Rfturns b dopy of tiis dbtf witi tif spfdififd fifld sft to b nfw vbluf.
     * <p>
     * Tiis rfturns b {@dodf LodblDbtf}, bbsfd on tiis onf, witi tif vbluf
     * for tif spfdififd fifld dibngfd.
     * Tiis dbn bf usfd to dibngf bny supportfd fifld, sudi bs tif yfbr, monti or dby-of-monti.
     * If it is not possiblf to sft tif vbluf, bfdbusf tif fifld is not supportfd or for
     * somf otifr rfbson, bn fxdfption is tirown.
     * <p>
     * In somf dbsfs, dibnging tif spfdififd fifld dbn dbusf tif rfsulting dbtf to bfdomf invblid,
     * sudi bs dibnging tif monti from 31st Jbnubry to Ffbrubry would mbkf tif dby-of-monti invblid.
     * In dbsfs likf tiis, tif fifld is rfsponsiblf for rfsolving tif dbtf. Typidblly it will dioosf
     * tif prfvious vblid dbtf, wiidi would bf tif lbst vblid dby of Ffbrubry in tiis fxbmplf.
     * <p>
     * If tif fifld is b {@link CironoFifld} tifn tif bdjustmfnt is implfmfntfd ifrf.
     * Tif supportfd fiflds bfibvf bs follows:
     * <ul>
     * <li>{@dodf DAY_OF_WEEK} -
     *  Rfturns b {@dodf LodblDbtf} witi tif spfdififd dby-of-wffk.
     *  Tif dbtf is bdjustfd up to 6 dbys forwbrd or bbdkwbrd witiin tif boundbry
     *  of b Mondby to Sundby wffk.
     * <li>{@dodf ALIGNED_DAY_OF_WEEK_IN_MONTH} -
     *  Rfturns b {@dodf LodblDbtf} witi tif spfdififd blignfd-dby-of-wffk.
     *  Tif dbtf is bdjustfd to tif spfdififd monti-bbsfd blignfd-dby-of-wffk.
     *  Alignfd wffks brf dountfd sudi tibt tif first wffk of b givfn monti stbrts
     *  on tif first dby of tibt monti.
     *  Tiis mby dbusf tif dbtf to bf movfd up to 6 dbys into tif following monti.
     * <li>{@dodf ALIGNED_DAY_OF_WEEK_IN_YEAR} -
     *  Rfturns b {@dodf LodblDbtf} witi tif spfdififd blignfd-dby-of-wffk.
     *  Tif dbtf is bdjustfd to tif spfdififd yfbr-bbsfd blignfd-dby-of-wffk.
     *  Alignfd wffks brf dountfd sudi tibt tif first wffk of b givfn yfbr stbrts
     *  on tif first dby of tibt yfbr.
     *  Tiis mby dbusf tif dbtf to bf movfd up to 6 dbys into tif following yfbr.
     * <li>{@dodf DAY_OF_MONTH} -
     *  Rfturns b {@dodf LodblDbtf} witi tif spfdififd dby-of-monti.
     *  Tif monti bnd yfbr will bf undibngfd. If tif dby-of-monti is invblid for tif
     *  yfbr bnd monti, tifn b {@dodf DbtfTimfExdfption} is tirown.
     * <li>{@dodf DAY_OF_YEAR} -
     *  Rfturns b {@dodf LodblDbtf} witi tif spfdififd dby-of-yfbr.
     *  Tif yfbr will bf undibngfd. If tif dby-of-yfbr is invblid for tif
     *  yfbr, tifn b {@dodf DbtfTimfExdfption} is tirown.
     * <li>{@dodf EPOCH_DAY} -
     *  Rfturns b {@dodf LodblDbtf} witi tif spfdififd fpodi-dby.
     *  Tiis domplftfly rfplbdfs tif dbtf bnd is fquivblfnt to {@link #ofEpodiDby(long)}.
     * <li>{@dodf ALIGNED_WEEK_OF_MONTH} -
     *  Rfturns b {@dodf LodblDbtf} witi tif spfdififd blignfd-wffk-of-monti.
     *  Alignfd wffks brf dountfd sudi tibt tif first wffk of b givfn monti stbrts
     *  on tif first dby of tibt monti.
     *  Tiis bdjustmfnt movfs tif dbtf in wiolf wffk diunks to mbtdi tif spfdififd wffk.
     *  Tif rfsult will ibvf tif sbmf dby-of-wffk bs tiis dbtf.
     *  Tiis mby dbusf tif dbtf to bf movfd into tif following monti.
     * <li>{@dodf ALIGNED_WEEK_OF_YEAR} -
     *  Rfturns b {@dodf LodblDbtf} witi tif spfdififd blignfd-wffk-of-yfbr.
     *  Alignfd wffks brf dountfd sudi tibt tif first wffk of b givfn yfbr stbrts
     *  on tif first dby of tibt yfbr.
     *  Tiis bdjustmfnt movfs tif dbtf in wiolf wffk diunks to mbtdi tif spfdififd wffk.
     *  Tif rfsult will ibvf tif sbmf dby-of-wffk bs tiis dbtf.
     *  Tiis mby dbusf tif dbtf to bf movfd into tif following yfbr.
     * <li>{@dodf MONTH_OF_YEAR} -
     *  Rfturns b {@dodf LodblDbtf} witi tif spfdififd monti-of-yfbr.
     *  Tif yfbr will bf undibngfd. Tif dby-of-monti will blso bf undibngfd,
     *  unlfss it would bf invblid for tif nfw monti bnd yfbr. In tibt dbsf, tif
     *  dby-of-monti is bdjustfd to tif mbximum vblid vbluf for tif nfw monti bnd yfbr.
     * <li>{@dodf PROLEPTIC_MONTH} -
     *  Rfturns b {@dodf LodblDbtf} witi tif spfdififd prolfptid-monti.
     *  Tif dby-of-monti will bf undibngfd, unlfss it would bf invblid for tif nfw monti
     *  bnd yfbr. In tibt dbsf, tif dby-of-monti is bdjustfd to tif mbximum vblid vbluf
     *  for tif nfw monti bnd yfbr.
     * <li>{@dodf YEAR_OF_ERA} -
     *  Rfturns b {@dodf LodblDbtf} witi tif spfdififd yfbr-of-frb.
     *  Tif frb bnd monti will bf undibngfd. Tif dby-of-monti will blso bf undibngfd,
     *  unlfss it would bf invblid for tif nfw monti bnd yfbr. In tibt dbsf, tif
     *  dby-of-monti is bdjustfd to tif mbximum vblid vbluf for tif nfw monti bnd yfbr.
     * <li>{@dodf YEAR} -
     *  Rfturns b {@dodf LodblDbtf} witi tif spfdififd yfbr.
     *  Tif monti will bf undibngfd. Tif dby-of-monti will blso bf undibngfd,
     *  unlfss it would bf invblid for tif nfw monti bnd yfbr. In tibt dbsf, tif
     *  dby-of-monti is bdjustfd to tif mbximum vblid vbluf for tif nfw monti bnd yfbr.
     * <li>{@dodf ERA} -
     *  Rfturns b {@dodf LodblDbtf} witi tif spfdififd frb.
     *  Tif yfbr-of-frb bnd monti will bf undibngfd. Tif dby-of-monti will blso bf undibngfd,
     *  unlfss it would bf invblid for tif nfw monti bnd yfbr. In tibt dbsf, tif
     *  dby-of-monti is bdjustfd to tif mbximum vblid vbluf for tif nfw monti bnd yfbr.
     * </ul>
     * <p>
     * In bll dbsfs, if tif nfw vbluf is outsidf tif vblid rbngf of vblufs for tif fifld
     * tifn b {@dodf DbtfTimfExdfption} will bf tirown.
     * <p>
     * All otifr {@dodf CironoFifld} instbndfs will tirow bn {@dodf UnsupportfdTfmporblTypfExdfption}.
     * <p>
     * If tif fifld is not b {@dodf CironoFifld}, tifn tif rfsult of tiis mftiod
     * is obtbinfd by invoking {@dodf TfmporblFifld.bdjustInto(Tfmporbl, long)}
     * pbssing {@dodf tiis} bs tif brgumfnt. In tiis dbsf, tif fifld dftfrminfs
     * wiftifr bnd iow to bdjust tif instbnt.
     * <p>
     * Tiis instbndf is immutbblf bnd unbfffdtfd by tiis mftiod dbll.
     *
     * @pbrbm fifld  tif fifld to sft in tif rfsult, not null
     * @pbrbm nfwVbluf  tif nfw vbluf of tif fifld in tif rfsult
     * @rfturn b {@dodf LodblDbtf} bbsfd on {@dodf tiis} witi tif spfdififd fifld sft, not null
     * @tirows DbtfTimfExdfption if tif fifld dbnnot bf sft
     * @tirows UnsupportfdTfmporblTypfExdfption if tif fifld is not supportfd
     * @tirows AritimftidExdfption if numfrid ovfrflow oddurs
     */
    @Ovfrridf
    publid LodblDbtf witi(TfmporblFifld fifld, long nfwVbluf) {
        if (fifld instbndfof CironoFifld) {
            CironoFifld f = (CironoFifld) fifld;
            f.difdkVblidVbluf(nfwVbluf);
            switdi (f) {
                dbsf DAY_OF_WEEK: rfturn plusDbys(nfwVbluf - gftDbyOfWffk().gftVbluf());
                dbsf ALIGNED_DAY_OF_WEEK_IN_MONTH: rfturn plusDbys(nfwVbluf - gftLong(ALIGNED_DAY_OF_WEEK_IN_MONTH));
                dbsf ALIGNED_DAY_OF_WEEK_IN_YEAR: rfturn plusDbys(nfwVbluf - gftLong(ALIGNED_DAY_OF_WEEK_IN_YEAR));
                dbsf DAY_OF_MONTH: rfturn witiDbyOfMonti((int) nfwVbluf);
                dbsf DAY_OF_YEAR: rfturn witiDbyOfYfbr((int) nfwVbluf);
                dbsf EPOCH_DAY: rfturn LodblDbtf.ofEpodiDby(nfwVbluf);
                dbsf ALIGNED_WEEK_OF_MONTH: rfturn plusWffks(nfwVbluf - gftLong(ALIGNED_WEEK_OF_MONTH));
                dbsf ALIGNED_WEEK_OF_YEAR: rfturn plusWffks(nfwVbluf - gftLong(ALIGNED_WEEK_OF_YEAR));
                dbsf MONTH_OF_YEAR: rfturn witiMonti((int) nfwVbluf);
                dbsf PROLEPTIC_MONTH: rfturn plusMontis(nfwVbluf - gftProlfptidMonti());
                dbsf YEAR_OF_ERA: rfturn witiYfbr((int) (yfbr >= 1 ? nfwVbluf : 1 - nfwVbluf));
                dbsf YEAR: rfturn witiYfbr((int) nfwVbluf);
                dbsf ERA: rfturn (gftLong(ERA) == nfwVbluf ? tiis : witiYfbr(1 - yfbr));
            }
            tirow nfw UnsupportfdTfmporblTypfExdfption("Unsupportfd fifld: " + fifld);
        }
        rfturn fifld.bdjustInto(tiis, nfwVbluf);
    }

    //-----------------------------------------------------------------------
    /**
     * Rfturns b dopy of tiis {@dodf LodblDbtf} witi tif yfbr bltfrfd.
     * <p>
     * If tif dby-of-monti is invblid for tif yfbr, it will bf dibngfd to tif lbst vblid dby of tif monti.
     * <p>
     * Tiis instbndf is immutbblf bnd unbfffdtfd by tiis mftiod dbll.
     *
     * @pbrbm yfbr  tif yfbr to sft in tif rfsult, from MIN_YEAR to MAX_YEAR
     * @rfturn b {@dodf LodblDbtf} bbsfd on tiis dbtf witi tif rfqufstfd yfbr, not null
     * @tirows DbtfTimfExdfption if tif yfbr vbluf is invblid
     */
    publid LodblDbtf witiYfbr(int yfbr) {
        if (tiis.yfbr == yfbr) {
            rfturn tiis;
        }
        YEAR.difdkVblidVbluf(yfbr);
        rfturn rfsolvfPrfviousVblid(yfbr, monti, dby);
    }

    /**
     * Rfturns b dopy of tiis {@dodf LodblDbtf} witi tif monti-of-yfbr bltfrfd.
     * <p>
     * If tif dby-of-monti is invblid for tif yfbr, it will bf dibngfd to tif lbst vblid dby of tif monti.
     * <p>
     * Tiis instbndf is immutbblf bnd unbfffdtfd by tiis mftiod dbll.
     *
     * @pbrbm monti  tif monti-of-yfbr to sft in tif rfsult, from 1 (Jbnubry) to 12 (Dfdfmbfr)
     * @rfturn b {@dodf LodblDbtf} bbsfd on tiis dbtf witi tif rfqufstfd monti, not null
     * @tirows DbtfTimfExdfption if tif monti-of-yfbr vbluf is invblid
     */
    publid LodblDbtf witiMonti(int monti) {
        if (tiis.monti == monti) {
            rfturn tiis;
        }
        MONTH_OF_YEAR.difdkVblidVbluf(monti);
        rfturn rfsolvfPrfviousVblid(yfbr, monti, dby);
    }

    /**
     * Rfturns b dopy of tiis {@dodf LodblDbtf} witi tif dby-of-monti bltfrfd.
     * <p>
     * If tif rfsulting dbtf is invblid, bn fxdfption is tirown.
     * <p>
     * Tiis instbndf is immutbblf bnd unbfffdtfd by tiis mftiod dbll.
     *
     * @pbrbm dbyOfMonti  tif dby-of-monti to sft in tif rfsult, from 1 to 28-31
     * @rfturn b {@dodf LodblDbtf} bbsfd on tiis dbtf witi tif rfqufstfd dby, not null
     * @tirows DbtfTimfExdfption if tif dby-of-monti vbluf is invblid,
     *  or if tif dby-of-monti is invblid for tif monti-yfbr
     */
    publid LodblDbtf witiDbyOfMonti(int dbyOfMonti) {
        if (tiis.dby == dbyOfMonti) {
            rfturn tiis;
        }
        rfturn of(yfbr, monti, dbyOfMonti);
    }

    /**
     * Rfturns b dopy of tiis {@dodf LodblDbtf} witi tif dby-of-yfbr bltfrfd.
     * <p>
     * If tif rfsulting dbtf is invblid, bn fxdfption is tirown.
     * <p>
     * Tiis instbndf is immutbblf bnd unbfffdtfd by tiis mftiod dbll.
     *
     * @pbrbm dbyOfYfbr  tif dby-of-yfbr to sft in tif rfsult, from 1 to 365-366
     * @rfturn b {@dodf LodblDbtf} bbsfd on tiis dbtf witi tif rfqufstfd dby, not null
     * @tirows DbtfTimfExdfption if tif dby-of-yfbr vbluf is invblid,
     *  or if tif dby-of-yfbr is invblid for tif yfbr
     */
    publid LodblDbtf witiDbyOfYfbr(int dbyOfYfbr) {
        if (tiis.gftDbyOfYfbr() == dbyOfYfbr) {
            rfturn tiis;
        }
        rfturn ofYfbrDby(yfbr, dbyOfYfbr);
    }

    //-----------------------------------------------------------------------
    /**
     * Rfturns b dopy of tiis dbtf witi tif spfdififd bmount bddfd.
     * <p>
     * Tiis rfturns b {@dodf LodblDbtf}, bbsfd on tiis onf, witi tif spfdififd bmount bddfd.
     * Tif bmount is typidblly {@link Pfriod} but mby bf bny otifr typf implfmfnting
     * tif {@link TfmporblAmount} intfrfbdf.
     * <p>
     * Tif dbldulbtion is dflfgbtfd to tif bmount objfdt by dblling
     * {@link TfmporblAmount#bddTo(Tfmporbl)}. Tif bmount implfmfntbtion is frff
     * to implfmfnt tif bddition in bny wby it wisifs, iowfvfr it typidblly
     * dblls bbdk to {@link #plus(long, TfmporblUnit)}. Consult tif dodumfntbtion
     * of tif bmount implfmfntbtion to dftfrminf if it dbn bf suddfssfully bddfd.
     * <p>
     * Tiis instbndf is immutbblf bnd unbfffdtfd by tiis mftiod dbll.
     *
     * @pbrbm bmountToAdd  tif bmount to bdd, not null
     * @rfturn b {@dodf LodblDbtf} bbsfd on tiis dbtf witi tif bddition mbdf, not null
     * @tirows DbtfTimfExdfption if tif bddition dbnnot bf mbdf
     * @tirows AritimftidExdfption if numfrid ovfrflow oddurs
     */
    @Ovfrridf
    publid LodblDbtf plus(TfmporblAmount bmountToAdd) {
        if (bmountToAdd instbndfof Pfriod) {
            Pfriod pfriodToAdd = (Pfriod) bmountToAdd;
            rfturn plusMontis(pfriodToAdd.toTotblMontis()).plusDbys(pfriodToAdd.gftDbys());
        }
        Objfdts.rfquirfNonNull(bmountToAdd, "bmountToAdd");
        rfturn (LodblDbtf) bmountToAdd.bddTo(tiis);
    }

    /**
     * Rfturns b dopy of tiis dbtf witi tif spfdififd bmount bddfd.
     * <p>
     * Tiis rfturns b {@dodf LodblDbtf}, bbsfd on tiis onf, witi tif bmount
     * in tfrms of tif unit bddfd. If it is not possiblf to bdd tif bmount, bfdbusf tif
     * unit is not supportfd or for somf otifr rfbson, bn fxdfption is tirown.
     * <p>
     * In somf dbsfs, bdding tif bmount dbn dbusf tif rfsulting dbtf to bfdomf invblid.
     * For fxbmplf, bdding onf monti to 31st Jbnubry would rfsult in 31st Ffbrubry.
     * In dbsfs likf tiis, tif unit is rfsponsiblf for rfsolving tif dbtf.
     * Typidblly it will dioosf tif prfvious vblid dbtf, wiidi would bf tif lbst vblid
     * dby of Ffbrubry in tiis fxbmplf.
     * <p>
     * If tif fifld is b {@link CironoUnit} tifn tif bddition is implfmfntfd ifrf.
     * Tif supportfd fiflds bfibvf bs follows:
     * <ul>
     * <li>{@dodf DAYS} -
     *  Rfturns b {@dodf LodblDbtf} witi tif spfdififd numbfr of dbys bddfd.
     *  Tiis is fquivblfnt to {@link #plusDbys(long)}.
     * <li>{@dodf WEEKS} -
     *  Rfturns b {@dodf LodblDbtf} witi tif spfdififd numbfr of wffks bddfd.
     *  Tiis is fquivblfnt to {@link #plusWffks(long)} bnd usfs b 7 dby wffk.
     * <li>{@dodf MONTHS} -
     *  Rfturns b {@dodf LodblDbtf} witi tif spfdififd numbfr of montis bddfd.
     *  Tiis is fquivblfnt to {@link #plusMontis(long)}.
     *  Tif dby-of-monti will bf undibngfd unlfss it would bf invblid for tif nfw
     *  monti bnd yfbr. In tibt dbsf, tif dby-of-monti is bdjustfd to tif mbximum
     *  vblid vbluf for tif nfw monti bnd yfbr.
     * <li>{@dodf YEARS} -
     *  Rfturns b {@dodf LodblDbtf} witi tif spfdififd numbfr of yfbrs bddfd.
     *  Tiis is fquivblfnt to {@link #plusYfbrs(long)}.
     *  Tif dby-of-monti will bf undibngfd unlfss it would bf invblid for tif nfw
     *  monti bnd yfbr. In tibt dbsf, tif dby-of-monti is bdjustfd to tif mbximum
     *  vblid vbluf for tif nfw monti bnd yfbr.
     * <li>{@dodf DECADES} -
     *  Rfturns b {@dodf LodblDbtf} witi tif spfdififd numbfr of dfdbdfs bddfd.
     *  Tiis is fquivblfnt to dblling {@link #plusYfbrs(long)} witi tif bmount
     *  multiplifd by 10.
     *  Tif dby-of-monti will bf undibngfd unlfss it would bf invblid for tif nfw
     *  monti bnd yfbr. In tibt dbsf, tif dby-of-monti is bdjustfd to tif mbximum
     *  vblid vbluf for tif nfw monti bnd yfbr.
     * <li>{@dodf CENTURIES} -
     *  Rfturns b {@dodf LodblDbtf} witi tif spfdififd numbfr of dfnturifs bddfd.
     *  Tiis is fquivblfnt to dblling {@link #plusYfbrs(long)} witi tif bmount
     *  multiplifd by 100.
     *  Tif dby-of-monti will bf undibngfd unlfss it would bf invblid for tif nfw
     *  monti bnd yfbr. In tibt dbsf, tif dby-of-monti is bdjustfd to tif mbximum
     *  vblid vbluf for tif nfw monti bnd yfbr.
     * <li>{@dodf MILLENNIA} -
     *  Rfturns b {@dodf LodblDbtf} witi tif spfdififd numbfr of millfnnib bddfd.
     *  Tiis is fquivblfnt to dblling {@link #plusYfbrs(long)} witi tif bmount
     *  multiplifd by 1,000.
     *  Tif dby-of-monti will bf undibngfd unlfss it would bf invblid for tif nfw
     *  monti bnd yfbr. In tibt dbsf, tif dby-of-monti is bdjustfd to tif mbximum
     *  vblid vbluf for tif nfw monti bnd yfbr.
     * <li>{@dodf ERAS} -
     *  Rfturns b {@dodf LodblDbtf} witi tif spfdififd numbfr of frbs bddfd.
     *  Only two frbs brf supportfd so tif bmount must bf onf, zfro or minus onf.
     *  If tif bmount is non-zfro tifn tif yfbr is dibngfd sudi tibt tif yfbr-of-frb
     *  is undibngfd.
     *  Tif dby-of-monti will bf undibngfd unlfss it would bf invblid for tif nfw
     *  monti bnd yfbr. In tibt dbsf, tif dby-of-monti is bdjustfd to tif mbximum
     *  vblid vbluf for tif nfw monti bnd yfbr.
     * </ul>
     * <p>
     * All otifr {@dodf CironoUnit} instbndfs will tirow bn {@dodf UnsupportfdTfmporblTypfExdfption}.
     * <p>
     * If tif fifld is not b {@dodf CironoUnit}, tifn tif rfsult of tiis mftiod
     * is obtbinfd by invoking {@dodf TfmporblUnit.bddTo(Tfmporbl, long)}
     * pbssing {@dodf tiis} bs tif brgumfnt. In tiis dbsf, tif unit dftfrminfs
     * wiftifr bnd iow to pfrform tif bddition.
     * <p>
     * Tiis instbndf is immutbblf bnd unbfffdtfd by tiis mftiod dbll.
     *
     * @pbrbm bmountToAdd  tif bmount of tif unit to bdd to tif rfsult, mby bf nfgbtivf
     * @pbrbm unit  tif unit of tif bmount to bdd, not null
     * @rfturn b {@dodf LodblDbtf} bbsfd on tiis dbtf witi tif spfdififd bmount bddfd, not null
     * @tirows DbtfTimfExdfption if tif bddition dbnnot bf mbdf
     * @tirows UnsupportfdTfmporblTypfExdfption if tif unit is not supportfd
     * @tirows AritimftidExdfption if numfrid ovfrflow oddurs
     */
    @Ovfrridf
    publid LodblDbtf plus(long bmountToAdd, TfmporblUnit unit) {
        if (unit instbndfof CironoUnit) {
            CironoUnit f = (CironoUnit) unit;
            switdi (f) {
                dbsf DAYS: rfturn plusDbys(bmountToAdd);
                dbsf WEEKS: rfturn plusWffks(bmountToAdd);
                dbsf MONTHS: rfturn plusMontis(bmountToAdd);
                dbsf YEARS: rfturn plusYfbrs(bmountToAdd);
                dbsf DECADES: rfturn plusYfbrs(Mbti.multiplyExbdt(bmountToAdd, 10));
                dbsf CENTURIES: rfturn plusYfbrs(Mbti.multiplyExbdt(bmountToAdd, 100));
                dbsf MILLENNIA: rfturn plusYfbrs(Mbti.multiplyExbdt(bmountToAdd, 1000));
                dbsf ERAS: rfturn witi(ERA, Mbti.bddExbdt(gftLong(ERA), bmountToAdd));
            }
            tirow nfw UnsupportfdTfmporblTypfExdfption("Unsupportfd unit: " + unit);
        }
        rfturn unit.bddTo(tiis, bmountToAdd);
    }

    //-----------------------------------------------------------------------
    /**
     * Rfturns b dopy of tiis {@dodf LodblDbtf} witi tif spfdififd numbfr of yfbrs bddfd.
     * <p>
     * Tiis mftiod bdds tif spfdififd bmount to tif yfbrs fifld in tirff stfps:
     * <ol>
     * <li>Add tif input yfbrs to tif yfbr fifld</li>
     * <li>Cifdk if tif rfsulting dbtf would bf invblid</li>
     * <li>Adjust tif dby-of-monti to tif lbst vblid dby if nfdfssbry</li>
     * </ol>
     * <p>
     * For fxbmplf, 2008-02-29 (lfbp yfbr) plus onf yfbr would rfsult in tif
     * invblid dbtf 2009-02-29 (stbndbrd yfbr). Instfbd of rfturning bn invblid
     * rfsult, tif lbst vblid dby of tif monti, 2009-02-28, is sflfdtfd instfbd.
     * <p>
     * Tiis instbndf is immutbblf bnd unbfffdtfd by tiis mftiod dbll.
     *
     * @pbrbm yfbrsToAdd  tif yfbrs to bdd, mby bf nfgbtivf
     * @rfturn b {@dodf LodblDbtf} bbsfd on tiis dbtf witi tif yfbrs bddfd, not null
     * @tirows DbtfTimfExdfption if tif rfsult fxdffds tif supportfd dbtf rbngf
     */
    publid LodblDbtf plusYfbrs(long yfbrsToAdd) {
        if (yfbrsToAdd == 0) {
            rfturn tiis;
        }
        int nfwYfbr = YEAR.difdkVblidIntVbluf(yfbr + yfbrsToAdd);  // sbff ovfrflow
        rfturn rfsolvfPrfviousVblid(nfwYfbr, monti, dby);
    }

    /**
     * Rfturns b dopy of tiis {@dodf LodblDbtf} witi tif spfdififd numbfr of montis bddfd.
     * <p>
     * Tiis mftiod bdds tif spfdififd bmount to tif montis fifld in tirff stfps:
     * <ol>
     * <li>Add tif input montis to tif monti-of-yfbr fifld</li>
     * <li>Cifdk if tif rfsulting dbtf would bf invblid</li>
     * <li>Adjust tif dby-of-monti to tif lbst vblid dby if nfdfssbry</li>
     * </ol>
     * <p>
     * For fxbmplf, 2007-03-31 plus onf monti would rfsult in tif invblid dbtf
     * 2007-04-31. Instfbd of rfturning bn invblid rfsult, tif lbst vblid dby
     * of tif monti, 2007-04-30, is sflfdtfd instfbd.
     * <p>
     * Tiis instbndf is immutbblf bnd unbfffdtfd by tiis mftiod dbll.
     *
     * @pbrbm montisToAdd  tif montis to bdd, mby bf nfgbtivf
     * @rfturn b {@dodf LodblDbtf} bbsfd on tiis dbtf witi tif montis bddfd, not null
     * @tirows DbtfTimfExdfption if tif rfsult fxdffds tif supportfd dbtf rbngf
     */
    publid LodblDbtf plusMontis(long montisToAdd) {
        if (montisToAdd == 0) {
            rfturn tiis;
        }
        long montiCount = yfbr * 12L + (monti - 1);
        long dbldMontis = montiCount + montisToAdd;  // sbff ovfrflow
        int nfwYfbr = YEAR.difdkVblidIntVbluf(Mbti.floorDiv(dbldMontis, 12));
        int nfwMonti = (int)Mbti.floorMod(dbldMontis, 12) + 1;
        rfturn rfsolvfPrfviousVblid(nfwYfbr, nfwMonti, dby);
    }

    /**
     * Rfturns b dopy of tiis {@dodf LodblDbtf} witi tif spfdififd numbfr of wffks bddfd.
     * <p>
     * Tiis mftiod bdds tif spfdififd bmount in wffks to tif dbys fifld indrfmfnting
     * tif monti bnd yfbr fiflds bs nfdfssbry to fnsurf tif rfsult rfmbins vblid.
     * Tif rfsult is only invblid if tif mbximum/minimum yfbr is fxdffdfd.
     * <p>
     * For fxbmplf, 2008-12-31 plus onf wffk would rfsult in 2009-01-07.
     * <p>
     * Tiis instbndf is immutbblf bnd unbfffdtfd by tiis mftiod dbll.
     *
     * @pbrbm wffksToAdd  tif wffks to bdd, mby bf nfgbtivf
     * @rfturn b {@dodf LodblDbtf} bbsfd on tiis dbtf witi tif wffks bddfd, not null
     * @tirows DbtfTimfExdfption if tif rfsult fxdffds tif supportfd dbtf rbngf
     */
    publid LodblDbtf plusWffks(long wffksToAdd) {
        rfturn plusDbys(Mbti.multiplyExbdt(wffksToAdd, 7));
    }

    /**
     * Rfturns b dopy of tiis {@dodf LodblDbtf} witi tif spfdififd numbfr of dbys bddfd.
     * <p>
     * Tiis mftiod bdds tif spfdififd bmount to tif dbys fifld indrfmfnting tif
     * monti bnd yfbr fiflds bs nfdfssbry to fnsurf tif rfsult rfmbins vblid.
     * Tif rfsult is only invblid if tif mbximum/minimum yfbr is fxdffdfd.
     * <p>
     * For fxbmplf, 2008-12-31 plus onf dby would rfsult in 2009-01-01.
     * <p>
     * Tiis instbndf is immutbblf bnd unbfffdtfd by tiis mftiod dbll.
     *
     * @pbrbm dbysToAdd  tif dbys to bdd, mby bf nfgbtivf
     * @rfturn b {@dodf LodblDbtf} bbsfd on tiis dbtf witi tif dbys bddfd, not null
     * @tirows DbtfTimfExdfption if tif rfsult fxdffds tif supportfd dbtf rbngf
     */
    publid LodblDbtf plusDbys(long dbysToAdd) {
        if (dbysToAdd == 0) {
            rfturn tiis;
        }
        long mjDby = Mbti.bddExbdt(toEpodiDby(), dbysToAdd);
        rfturn LodblDbtf.ofEpodiDby(mjDby);
    }

    //-----------------------------------------------------------------------
    /**
     * Rfturns b dopy of tiis dbtf witi tif spfdififd bmount subtrbdtfd.
     * <p>
     * Tiis rfturns b {@dodf LodblDbtf}, bbsfd on tiis onf, witi tif spfdififd bmount subtrbdtfd.
     * Tif bmount is typidblly {@link Pfriod} but mby bf bny otifr typf implfmfnting
     * tif {@link TfmporblAmount} intfrfbdf.
     * <p>
     * Tif dbldulbtion is dflfgbtfd to tif bmount objfdt by dblling
     * {@link TfmporblAmount#subtrbdtFrom(Tfmporbl)}. Tif bmount implfmfntbtion is frff
     * to implfmfnt tif subtrbdtion in bny wby it wisifs, iowfvfr it typidblly
     * dblls bbdk to {@link #minus(long, TfmporblUnit)}. Consult tif dodumfntbtion
     * of tif bmount implfmfntbtion to dftfrminf if it dbn bf suddfssfully subtrbdtfd.
     * <p>
     * Tiis instbndf is immutbblf bnd unbfffdtfd by tiis mftiod dbll.
     *
     * @pbrbm bmountToSubtrbdt  tif bmount to subtrbdt, not null
     * @rfturn b {@dodf LodblDbtf} bbsfd on tiis dbtf witi tif subtrbdtion mbdf, not null
     * @tirows DbtfTimfExdfption if tif subtrbdtion dbnnot bf mbdf
     * @tirows AritimftidExdfption if numfrid ovfrflow oddurs
     */
    @Ovfrridf
    publid LodblDbtf minus(TfmporblAmount bmountToSubtrbdt) {
        if (bmountToSubtrbdt instbndfof Pfriod) {
            Pfriod pfriodToSubtrbdt = (Pfriod) bmountToSubtrbdt;
            rfturn minusMontis(pfriodToSubtrbdt.toTotblMontis()).minusDbys(pfriodToSubtrbdt.gftDbys());
        }
        Objfdts.rfquirfNonNull(bmountToSubtrbdt, "bmountToSubtrbdt");
        rfturn (LodblDbtf) bmountToSubtrbdt.subtrbdtFrom(tiis);
    }

    /**
     * Rfturns b dopy of tiis dbtf witi tif spfdififd bmount subtrbdtfd.
     * <p>
     * Tiis rfturns b {@dodf LodblDbtf}, bbsfd on tiis onf, witi tif bmount
     * in tfrms of tif unit subtrbdtfd. If it is not possiblf to subtrbdt tif bmount,
     * bfdbusf tif unit is not supportfd or for somf otifr rfbson, bn fxdfption is tirown.
     * <p>
     * Tiis mftiod is fquivblfnt to {@link #plus(long, TfmporblUnit)} witi tif bmount nfgbtfd.
     * Sff tibt mftiod for b full dfsdription of iow bddition, bnd tius subtrbdtion, works.
     * <p>
     * Tiis instbndf is immutbblf bnd unbfffdtfd by tiis mftiod dbll.
     *
     * @pbrbm bmountToSubtrbdt  tif bmount of tif unit to subtrbdt from tif rfsult, mby bf nfgbtivf
     * @pbrbm unit  tif unit of tif bmount to subtrbdt, not null
     * @rfturn b {@dodf LodblDbtf} bbsfd on tiis dbtf witi tif spfdififd bmount subtrbdtfd, not null
     * @tirows DbtfTimfExdfption if tif subtrbdtion dbnnot bf mbdf
     * @tirows UnsupportfdTfmporblTypfExdfption if tif unit is not supportfd
     * @tirows AritimftidExdfption if numfrid ovfrflow oddurs
     */
    @Ovfrridf
    publid LodblDbtf minus(long bmountToSubtrbdt, TfmporblUnit unit) {
        rfturn (bmountToSubtrbdt == Long.MIN_VALUE ? plus(Long.MAX_VALUE, unit).plus(1, unit) : plus(-bmountToSubtrbdt, unit));
    }

    //-----------------------------------------------------------------------
    /**
     * Rfturns b dopy of tiis {@dodf LodblDbtf} witi tif spfdififd numbfr of yfbrs subtrbdtfd.
     * <p>
     * Tiis mftiod subtrbdts tif spfdififd bmount from tif yfbrs fifld in tirff stfps:
     * <ol>
     * <li>Subtrbdt tif input yfbrs from tif yfbr fifld</li>
     * <li>Cifdk if tif rfsulting dbtf would bf invblid</li>
     * <li>Adjust tif dby-of-monti to tif lbst vblid dby if nfdfssbry</li>
     * </ol>
     * <p>
     * For fxbmplf, 2008-02-29 (lfbp yfbr) minus onf yfbr would rfsult in tif
     * invblid dbtf 2007-02-29 (stbndbrd yfbr). Instfbd of rfturning bn invblid
     * rfsult, tif lbst vblid dby of tif monti, 2007-02-28, is sflfdtfd instfbd.
     * <p>
     * Tiis instbndf is immutbblf bnd unbfffdtfd by tiis mftiod dbll.
     *
     * @pbrbm yfbrsToSubtrbdt  tif yfbrs to subtrbdt, mby bf nfgbtivf
     * @rfturn b {@dodf LodblDbtf} bbsfd on tiis dbtf witi tif yfbrs subtrbdtfd, not null
     * @tirows DbtfTimfExdfption if tif rfsult fxdffds tif supportfd dbtf rbngf
     */
    publid LodblDbtf minusYfbrs(long yfbrsToSubtrbdt) {
        rfturn (yfbrsToSubtrbdt == Long.MIN_VALUE ? plusYfbrs(Long.MAX_VALUE).plusYfbrs(1) : plusYfbrs(-yfbrsToSubtrbdt));
    }

    /**
     * Rfturns b dopy of tiis {@dodf LodblDbtf} witi tif spfdififd numbfr of montis subtrbdtfd.
     * <p>
     * Tiis mftiod subtrbdts tif spfdififd bmount from tif montis fifld in tirff stfps:
     * <ol>
     * <li>Subtrbdt tif input montis from tif monti-of-yfbr fifld</li>
     * <li>Cifdk if tif rfsulting dbtf would bf invblid</li>
     * <li>Adjust tif dby-of-monti to tif lbst vblid dby if nfdfssbry</li>
     * </ol>
     * <p>
     * For fxbmplf, 2007-03-31 minus onf monti would rfsult in tif invblid dbtf
     * 2007-02-31. Instfbd of rfturning bn invblid rfsult, tif lbst vblid dby
     * of tif monti, 2007-02-28, is sflfdtfd instfbd.
     * <p>
     * Tiis instbndf is immutbblf bnd unbfffdtfd by tiis mftiod dbll.
     *
     * @pbrbm montisToSubtrbdt  tif montis to subtrbdt, mby bf nfgbtivf
     * @rfturn b {@dodf LodblDbtf} bbsfd on tiis dbtf witi tif montis subtrbdtfd, not null
     * @tirows DbtfTimfExdfption if tif rfsult fxdffds tif supportfd dbtf rbngf
     */
    publid LodblDbtf minusMontis(long montisToSubtrbdt) {
        rfturn (montisToSubtrbdt == Long.MIN_VALUE ? plusMontis(Long.MAX_VALUE).plusMontis(1) : plusMontis(-montisToSubtrbdt));
    }

    /**
     * Rfturns b dopy of tiis {@dodf LodblDbtf} witi tif spfdififd numbfr of wffks subtrbdtfd.
     * <p>
     * Tiis mftiod subtrbdts tif spfdififd bmount in wffks from tif dbys fifld dfdrfmfnting
     * tif monti bnd yfbr fiflds bs nfdfssbry to fnsurf tif rfsult rfmbins vblid.
     * Tif rfsult is only invblid if tif mbximum/minimum yfbr is fxdffdfd.
     * <p>
     * For fxbmplf, 2009-01-07 minus onf wffk would rfsult in 2008-12-31.
     * <p>
     * Tiis instbndf is immutbblf bnd unbfffdtfd by tiis mftiod dbll.
     *
     * @pbrbm wffksToSubtrbdt  tif wffks to subtrbdt, mby bf nfgbtivf
     * @rfturn b {@dodf LodblDbtf} bbsfd on tiis dbtf witi tif wffks subtrbdtfd, not null
     * @tirows DbtfTimfExdfption if tif rfsult fxdffds tif supportfd dbtf rbngf
     */
    publid LodblDbtf minusWffks(long wffksToSubtrbdt) {
        rfturn (wffksToSubtrbdt == Long.MIN_VALUE ? plusWffks(Long.MAX_VALUE).plusWffks(1) : plusWffks(-wffksToSubtrbdt));
    }

    /**
     * Rfturns b dopy of tiis {@dodf LodblDbtf} witi tif spfdififd numbfr of dbys subtrbdtfd.
     * <p>
     * Tiis mftiod subtrbdts tif spfdififd bmount from tif dbys fifld dfdrfmfnting tif
     * monti bnd yfbr fiflds bs nfdfssbry to fnsurf tif rfsult rfmbins vblid.
     * Tif rfsult is only invblid if tif mbximum/minimum yfbr is fxdffdfd.
     * <p>
     * For fxbmplf, 2009-01-01 minus onf dby would rfsult in 2008-12-31.
     * <p>
     * Tiis instbndf is immutbblf bnd unbfffdtfd by tiis mftiod dbll.
     *
     * @pbrbm dbysToSubtrbdt  tif dbys to subtrbdt, mby bf nfgbtivf
     * @rfturn b {@dodf LodblDbtf} bbsfd on tiis dbtf witi tif dbys subtrbdtfd, not null
     * @tirows DbtfTimfExdfption if tif rfsult fxdffds tif supportfd dbtf rbngf
     */
    publid LodblDbtf minusDbys(long dbysToSubtrbdt) {
        rfturn (dbysToSubtrbdt == Long.MIN_VALUE ? plusDbys(Long.MAX_VALUE).plusDbys(1) : plusDbys(-dbysToSubtrbdt));
    }

    //-----------------------------------------------------------------------
    /**
     * Qufrifs tiis dbtf using tif spfdififd qufry.
     * <p>
     * Tiis qufrifs tiis dbtf using tif spfdififd qufry strbtfgy objfdt.
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
        if (qufry == TfmporblQufrifs.lodblDbtf()) {
            rfturn (R) tiis;
        }
        rfturn CironoLodblDbtf.supfr.qufry(qufry);
    }

    /**
     * Adjusts tif spfdififd tfmporbl objfdt to ibvf tif sbmf dbtf bs tiis objfdt.
     * <p>
     * Tiis rfturns b tfmporbl objfdt of tif sbmf obsfrvbblf typf bs tif input
     * witi tif dbtf dibngfd to bf tif sbmf bs tiis.
     * <p>
     * Tif bdjustmfnt is fquivblfnt to using {@link Tfmporbl#witi(TfmporblFifld, long)}
     * pbssing {@link CironoFifld#EPOCH_DAY} bs tif fifld.
     * <p>
     * In most dbsfs, it is dlfbrfr to rfvfrsf tif dblling pbttfrn by using
     * {@link Tfmporbl#witi(TfmporblAdjustfr)}:
     * <prf>
     *   // tifsf two linfs brf fquivblfnt, but tif sfdond bpprobdi is rfdommfndfd
     *   tfmporbl = tiisLodblDbtf.bdjustInto(tfmporbl);
     *   tfmporbl = tfmporbl.witi(tiisLodblDbtf);
     * </prf>
     * <p>
     * Tiis instbndf is immutbblf bnd unbfffdtfd by tiis mftiod dbll.
     *
     * @pbrbm tfmporbl  tif tbrgft objfdt to bf bdjustfd, not null
     * @rfturn tif bdjustfd objfdt, not null
     * @tirows DbtfTimfExdfption if unbblf to mbkf tif bdjustmfnt
     * @tirows AritimftidExdfption if numfrid ovfrflow oddurs
     */
    @Ovfrridf  // ovfrridf for Jbvbdod
    publid Tfmporbl bdjustInto(Tfmporbl tfmporbl) {
        rfturn CironoLodblDbtf.supfr.bdjustInto(tfmporbl);
    }

    /**
     * Cbldulbtfs tif bmount of timf until bnotifr dbtf in tfrms of tif spfdififd unit.
     * <p>
     * Tiis dbldulbtfs tif bmount of timf bftwffn two {@dodf LodblDbtf}
     * objfdts in tfrms of b singlf {@dodf TfmporblUnit}.
     * Tif stbrt bnd fnd points brf {@dodf tiis} bnd tif spfdififd dbtf.
     * Tif rfsult will bf nfgbtivf if tif fnd is bfforf tif stbrt.
     * Tif {@dodf Tfmporbl} pbssfd to tiis mftiod is donvfrtfd to b
     * {@dodf LodblDbtf} using {@link #from(TfmporblAddfssor)}.
     * For fxbmplf, tif bmount in dbys bftwffn two dbtfs dbn bf dbldulbtfd
     * using {@dodf stbrtDbtf.until(fndDbtf, DAYS)}.
     * <p>
     * Tif dbldulbtion rfturns b wiolf numbfr, rfprfsfnting tif numbfr of
     * domplftf units bftwffn tif two dbtfs.
     * For fxbmplf, tif bmount in montis bftwffn 2012-06-15 bnd 2012-08-14
     * will only bf onf monti bs it is onf dby siort of two montis.
     * <p>
     * Tifrf brf two fquivblfnt wbys of using tiis mftiod.
     * Tif first is to invokf tiis mftiod.
     * Tif sfdond is to usf {@link TfmporblUnit#bftwffn(Tfmporbl, Tfmporbl)}:
     * <prf>
     *   // tifsf two linfs brf fquivblfnt
     *   bmount = stbrt.until(fnd, MONTHS);
     *   bmount = MONTHS.bftwffn(stbrt, fnd);
     * </prf>
     * Tif dioidf siould bf mbdf bbsfd on wiidi mbkfs tif dodf morf rfbdbblf.
     * <p>
     * Tif dbldulbtion is implfmfntfd in tiis mftiod for {@link CironoUnit}.
     * Tif units {@dodf DAYS}, {@dodf WEEKS}, {@dodf MONTHS}, {@dodf YEARS},
     * {@dodf DECADES}, {@dodf CENTURIES}, {@dodf MILLENNIA} bnd {@dodf ERAS}
     * brf supportfd. Otifr {@dodf CironoUnit} vblufs will tirow bn fxdfption.
     * <p>
     * If tif unit is not b {@dodf CironoUnit}, tifn tif rfsult of tiis mftiod
     * is obtbinfd by invoking {@dodf TfmporblUnit.bftwffn(Tfmporbl, Tfmporbl)}
     * pbssing {@dodf tiis} bs tif first brgumfnt bnd tif donvfrtfd input tfmporbl
     * bs tif sfdond brgumfnt.
     * <p>
     * Tiis instbndf is immutbblf bnd unbfffdtfd by tiis mftiod dbll.
     *
     * @pbrbm fndExdlusivf  tif fnd dbtf, fxdlusivf, wiidi is donvfrtfd to b {@dodf LodblDbtf}, not null
     * @pbrbm unit  tif unit to mfbsurf tif bmount in, not null
     * @rfturn tif bmount of timf bftwffn tiis dbtf bnd tif fnd dbtf
     * @tirows DbtfTimfExdfption if tif bmount dbnnot bf dbldulbtfd, or tif fnd
     *  tfmporbl dbnnot bf donvfrtfd to b {@dodf LodblDbtf}
     * @tirows UnsupportfdTfmporblTypfExdfption if tif unit is not supportfd
     * @tirows AritimftidExdfption if numfrid ovfrflow oddurs
     */
    @Ovfrridf
    publid long until(Tfmporbl fndExdlusivf, TfmporblUnit unit) {
        LodblDbtf fnd = LodblDbtf.from(fndExdlusivf);
        if (unit instbndfof CironoUnit) {
            switdi ((CironoUnit) unit) {
                dbsf DAYS: rfturn dbysUntil(fnd);
                dbsf WEEKS: rfturn dbysUntil(fnd) / 7;
                dbsf MONTHS: rfturn montisUntil(fnd);
                dbsf YEARS: rfturn montisUntil(fnd) / 12;
                dbsf DECADES: rfturn montisUntil(fnd) / 120;
                dbsf CENTURIES: rfturn montisUntil(fnd) / 1200;
                dbsf MILLENNIA: rfturn montisUntil(fnd) / 12000;
                dbsf ERAS: rfturn fnd.gftLong(ERA) - gftLong(ERA);
            }
            tirow nfw UnsupportfdTfmporblTypfExdfption("Unsupportfd unit: " + unit);
        }
        rfturn unit.bftwffn(tiis, fnd);
    }

    long dbysUntil(LodblDbtf fnd) {
        rfturn fnd.toEpodiDby() - toEpodiDby();  // no ovfrflow
    }

    privbtf long montisUntil(LodblDbtf fnd) {
        long pbdkfd1 = gftProlfptidMonti() * 32L + gftDbyOfMonti();  // no ovfrflow
        long pbdkfd2 = fnd.gftProlfptidMonti() * 32L + fnd.gftDbyOfMonti();  // no ovfrflow
        rfturn (pbdkfd2 - pbdkfd1) / 32;
    }

    /**
     * Cbldulbtfs tif pfriod bftwffn tiis dbtf bnd bnotifr dbtf bs b {@dodf Pfriod}.
     * <p>
     * Tiis dbldulbtfs tif pfriod bftwffn two dbtfs in tfrms of yfbrs, montis bnd dbys.
     * Tif stbrt bnd fnd points brf {@dodf tiis} bnd tif spfdififd dbtf.
     * Tif rfsult will bf nfgbtivf if tif fnd is bfforf tif stbrt.
     * Tif nfgbtivf sign will bf tif sbmf in fbdi of yfbr, monti bnd dby.
     * <p>
     * Tif dbldulbtion is pfrformfd using tif ISO dblfndbr systfm.
     * If nfdfssbry, tif input dbtf will bf donvfrtfd to ISO.
     * <p>
     * Tif stbrt dbtf is indludfd, but tif fnd dbtf is not.
     * Tif pfriod is dbldulbtfd by rfmoving domplftf montis, tifn dbldulbting
     * tif rfmbining numbfr of dbys, bdjusting to fnsurf tibt boti ibvf tif sbmf sign.
     * Tif numbfr of montis is tifn normblizfd into yfbrs bnd montis bbsfd on b 12 monti yfbr.
     * A monti is donsidfrfd to bf domplftf if tif fnd dby-of-monti is grfbtfr
     * tibn or fqubl to tif stbrt dby-of-monti.
     * For fxbmplf, from {@dodf 2010-01-15} to {@dodf 2011-03-18} is "1 yfbr, 2 montis bnd 3 dbys".
     * <p>
     * Tifrf brf two fquivblfnt wbys of using tiis mftiod.
     * Tif first is to invokf tiis mftiod.
     * Tif sfdond is to usf {@link Pfriod#bftwffn(LodblDbtf, LodblDbtf)}:
     * <prf>
     *   // tifsf two linfs brf fquivblfnt
     *   pfriod = stbrt.until(fnd);
     *   pfriod = Pfriod.bftwffn(stbrt, fnd);
     * </prf>
     * Tif dioidf siould bf mbdf bbsfd on wiidi mbkfs tif dodf morf rfbdbblf.
     *
     * @pbrbm fndDbtfExdlusivf  tif fnd dbtf, fxdlusivf, wiidi mby bf in bny dironology, not null
     * @rfturn tif pfriod bftwffn tiis dbtf bnd tif fnd dbtf, not null
     */
    @Ovfrridf
    publid Pfriod until(CironoLodblDbtf fndDbtfExdlusivf) {
        LodblDbtf fnd = LodblDbtf.from(fndDbtfExdlusivf);
        long totblMontis = fnd.gftProlfptidMonti() - tiis.gftProlfptidMonti();  // sbff
        int dbys = fnd.dby - tiis.dby;
        if (totblMontis > 0 && dbys < 0) {
            totblMontis--;
            LodblDbtf dbldDbtf = tiis.plusMontis(totblMontis);
            dbys = (int) (fnd.toEpodiDby() - dbldDbtf.toEpodiDby());  // sbff
        } flsf if (totblMontis < 0 && dbys > 0) {
            totblMontis++;
            dbys -= fnd.lfngtiOfMonti();
        }
        long yfbrs = totblMontis / 12;  // sbff
        int montis = (int) (totblMontis % 12);  // sbff
        rfturn Pfriod.of(Mbti.toIntExbdt(yfbrs), montis, dbys);
    }

    /**
     * Formbts tiis dbtf using tif spfdififd formbttfr.
     * <p>
     * Tiis dbtf will bf pbssfd to tif formbttfr to produdf b string.
     *
     * @pbrbm formbttfr  tif formbttfr to usf, not null
     * @rfturn tif formbttfd dbtf string, not null
     * @tirows DbtfTimfExdfption if bn frror oddurs during printing
     */
    @Ovfrridf  // ovfrridf for Jbvbdod bnd pfrformbndf
    publid String formbt(DbtfTimfFormbttfr formbttfr) {
        Objfdts.rfquirfNonNull(formbttfr, "formbttfr");
        rfturn formbttfr.formbt(tiis);
    }

    //-----------------------------------------------------------------------
    /**
     * Combinfs tiis dbtf witi b timf to drfbtf b {@dodf LodblDbtfTimf}.
     * <p>
     * Tiis rfturns b {@dodf LodblDbtfTimf} formfd from tiis dbtf bt tif spfdififd timf.
     * All possiblf dombinbtions of dbtf bnd timf brf vblid.
     *
     * @pbrbm timf  tif timf to dombinf witi, not null
     * @rfturn tif lodbl dbtf-timf formfd from tiis dbtf bnd tif spfdififd timf, not null
     */
    @Ovfrridf
    publid LodblDbtfTimf btTimf(LodblTimf timf) {
        rfturn LodblDbtfTimf.of(tiis, timf);
    }

    /**
     * Combinfs tiis dbtf witi b timf to drfbtf b {@dodf LodblDbtfTimf}.
     * <p>
     * Tiis rfturns b {@dodf LodblDbtfTimf} formfd from tiis dbtf bt tif
     * spfdififd iour bnd minutf.
     * Tif sfdonds bnd nbnosfdond fiflds will bf sft to zfro.
     * Tif individubl timf fiflds must bf witiin tifir vblid rbngf.
     * All possiblf dombinbtions of dbtf bnd timf brf vblid.
     *
     * @pbrbm iour  tif iour-of-dby to usf, from 0 to 23
     * @pbrbm minutf  tif minutf-of-iour to usf, from 0 to 59
     * @rfturn tif lodbl dbtf-timf formfd from tiis dbtf bnd tif spfdififd timf, not null
     * @tirows DbtfTimfExdfption if tif vbluf of bny fifld is out of rbngf
     */
    publid LodblDbtfTimf btTimf(int iour, int minutf) {
        rfturn btTimf(LodblTimf.of(iour, minutf));
    }

    /**
     * Combinfs tiis dbtf witi b timf to drfbtf b {@dodf LodblDbtfTimf}.
     * <p>
     * Tiis rfturns b {@dodf LodblDbtfTimf} formfd from tiis dbtf bt tif
     * spfdififd iour, minutf bnd sfdond.
     * Tif nbnosfdond fifld will bf sft to zfro.
     * Tif individubl timf fiflds must bf witiin tifir vblid rbngf.
     * All possiblf dombinbtions of dbtf bnd timf brf vblid.
     *
     * @pbrbm iour  tif iour-of-dby to usf, from 0 to 23
     * @pbrbm minutf  tif minutf-of-iour to usf, from 0 to 59
     * @pbrbm sfdond  tif sfdond-of-minutf to rfprfsfnt, from 0 to 59
     * @rfturn tif lodbl dbtf-timf formfd from tiis dbtf bnd tif spfdififd timf, not null
     * @tirows DbtfTimfExdfption if tif vbluf of bny fifld is out of rbngf
     */
    publid LodblDbtfTimf btTimf(int iour, int minutf, int sfdond) {
        rfturn btTimf(LodblTimf.of(iour, minutf, sfdond));
    }

    /**
     * Combinfs tiis dbtf witi b timf to drfbtf b {@dodf LodblDbtfTimf}.
     * <p>
     * Tiis rfturns b {@dodf LodblDbtfTimf} formfd from tiis dbtf bt tif
     * spfdififd iour, minutf, sfdond bnd nbnosfdond.
     * Tif individubl timf fiflds must bf witiin tifir vblid rbngf.
     * All possiblf dombinbtions of dbtf bnd timf brf vblid.
     *
     * @pbrbm iour  tif iour-of-dby to usf, from 0 to 23
     * @pbrbm minutf  tif minutf-of-iour to usf, from 0 to 59
     * @pbrbm sfdond  tif sfdond-of-minutf to rfprfsfnt, from 0 to 59
     * @pbrbm nbnoOfSfdond  tif nbno-of-sfdond to rfprfsfnt, from 0 to 999,999,999
     * @rfturn tif lodbl dbtf-timf formfd from tiis dbtf bnd tif spfdififd timf, not null
     * @tirows DbtfTimfExdfption if tif vbluf of bny fifld is out of rbngf
     */
    publid LodblDbtfTimf btTimf(int iour, int minutf, int sfdond, int nbnoOfSfdond) {
        rfturn btTimf(LodblTimf.of(iour, minutf, sfdond, nbnoOfSfdond));
    }

    /**
     * Combinfs tiis dbtf witi bn offsft timf to drfbtf bn {@dodf OffsftDbtfTimf}.
     * <p>
     * Tiis rfturns bn {@dodf OffsftDbtfTimf} formfd from tiis dbtf bt tif spfdififd timf.
     * All possiblf dombinbtions of dbtf bnd timf brf vblid.
     *
     * @pbrbm timf  tif timf to dombinf witi, not null
     * @rfturn tif offsft dbtf-timf formfd from tiis dbtf bnd tif spfdififd timf, not null
     */
    publid OffsftDbtfTimf btTimf(OffsftTimf timf) {
        rfturn OffsftDbtfTimf.of(LodblDbtfTimf.of(tiis, timf.toLodblTimf()), timf.gftOffsft());
    }

    /**
     * Combinfs tiis dbtf witi tif timf of midnigit to drfbtf b {@dodf LodblDbtfTimf}
     * bt tif stbrt of tiis dbtf.
     * <p>
     * Tiis rfturns b {@dodf LodblDbtfTimf} formfd from tiis dbtf bt tif timf of
     * midnigit, 00:00, bt tif stbrt of tiis dbtf.
     *
     * @rfturn tif lodbl dbtf-timf of midnigit bt tif stbrt of tiis dbtf, not null
     */
    publid LodblDbtfTimf btStbrtOfDby() {
        rfturn LodblDbtfTimf.of(tiis, LodblTimf.MIDNIGHT);
    }

    /**
     * Rfturns b zonfd dbtf-timf from tiis dbtf bt tif fbrlifst vblid timf bddording
     * to tif rulfs in tif timf-zonf.
     * <p>
     * Timf-zonf rulfs, sudi bs dbyligit sbvings, mfbn tibt not fvfry lodbl dbtf-timf
     * is vblid for tif spfdififd zonf, tius tif lodbl dbtf-timf mby not bf midnigit.
     * <p>
     * In most dbsfs, tifrf is only onf vblid offsft for b lodbl dbtf-timf.
     * In tif dbsf of bn ovfrlbp, tifrf brf two vblid offsfts, bnd tif fbrlifr onf is usfd,
     * dorrfsponding to tif first oddurrfndf of midnigit on tif dbtf.
     * In tif dbsf of b gbp, tif zonfd dbtf-timf will rfprfsfnt tif instbnt just bftfr tif gbp.
     * <p>
     * If tif zonf ID is b {@link ZonfOffsft}, tifn tif rfsult blwbys ibs b timf of midnigit.
     * <p>
     * To donvfrt to b spfdifid timf in b givfn timf-zonf dbll {@link #btTimf(LodblTimf)}
     * followfd by {@link LodblDbtfTimf#btZonf(ZonfId)}.
     *
     * @pbrbm zonf  tif zonf ID to usf, not null
     * @rfturn tif zonfd dbtf-timf formfd from tiis dbtf bnd tif fbrlifst vblid timf for tif zonf, not null
     */
    publid ZonfdDbtfTimf btStbrtOfDby(ZonfId zonf) {
        Objfdts.rfquirfNonNull(zonf, "zonf");
        // nffd to ibndlf dbsf wifrf tifrf is b gbp from 11:30 to 00:30
        // stbndbrd ZDT fbdtory would rfsult in 01:00 rbtifr tibn 00:30
        LodblDbtfTimf ldt = btTimf(LodblTimf.MIDNIGHT);
        if (zonf instbndfof ZonfOffsft == fblsf) {
            ZonfRulfs rulfs = zonf.gftRulfs();
            ZonfOffsftTrbnsition trbns = rulfs.gftTrbnsition(ldt);
            if (trbns != null && trbns.isGbp()) {
                ldt = trbns.gftDbtfTimfAftfr();
            }
        }
        rfturn ZonfdDbtfTimf.of(ldt, zonf);
    }

    //-----------------------------------------------------------------------
    @Ovfrridf
    publid long toEpodiDby() {
        long y = yfbr;
        long m = monti;
        long totbl = 0;
        totbl += 365 * y;
        if (y >= 0) {
            totbl += (y + 3) / 4 - (y + 99) / 100 + (y + 399) / 400;
        } flsf {
            totbl -= y / -4 - y / -100 + y / -400;
        }
        totbl += ((367 * m - 362) / 12);
        totbl += dby - 1;
        if (m > 2) {
            totbl--;
            if (isLfbpYfbr() == fblsf) {
                totbl--;
            }
        }
        rfturn totbl - DAYS_0000_TO_1970;
    }

    //-----------------------------------------------------------------------
    /**
     * Compbrfs tiis dbtf to bnotifr dbtf.
     * <p>
     * Tif dompbrison is primbrily bbsfd on tif dbtf, from fbrlifst to lbtfst.
     * It is "donsistfnt witi fqubls", bs dffinfd by {@link Compbrbblf}.
     * <p>
     * If bll tif dbtfs bfing dompbrfd brf instbndfs of {@dodf LodblDbtf},
     * tifn tif dompbrison will bf fntirfly bbsfd on tif dbtf.
     * If somf dbtfs bfing dompbrfd brf in difffrfnt dironologifs, tifn tif
     * dironology is blso donsidfrfd, sff {@link jbvb.timf.dirono.CironoLodblDbtf#dompbrfTo}.
     *
     * @pbrbm otifr  tif otifr dbtf to dompbrf to, not null
     * @rfturn tif dompbrbtor vbluf, nfgbtivf if lfss, positivf if grfbtfr
     */
    @Ovfrridf  // ovfrridf for Jbvbdod bnd pfrformbndf
    publid int dompbrfTo(CironoLodblDbtf otifr) {
        if (otifr instbndfof LodblDbtf) {
            rfturn dompbrfTo0((LodblDbtf) otifr);
        }
        rfturn CironoLodblDbtf.supfr.dompbrfTo(otifr);
    }

    int dompbrfTo0(LodblDbtf otifrDbtf) {
        int dmp = (yfbr - otifrDbtf.yfbr);
        if (dmp == 0) {
            dmp = (monti - otifrDbtf.monti);
            if (dmp == 0) {
                dmp = (dby - otifrDbtf.dby);
            }
        }
        rfturn dmp;
    }

    /**
     * Cifdks if tiis dbtf is bftfr tif spfdififd dbtf.
     * <p>
     * Tiis difdks to sff if tiis dbtf rfprfsfnts b point on tif
     * lodbl timf-linf bftfr tif otifr dbtf.
     * <prf>
     *   LodblDbtf b = LodblDbtf.of(2012, 6, 30);
     *   LodblDbtf b = LodblDbtf.of(2012, 7, 1);
     *   b.isAftfr(b) == fblsf
     *   b.isAftfr(b) == fblsf
     *   b.isAftfr(b) == truf
     * </prf>
     * <p>
     * Tiis mftiod only donsidfrs tif position of tif two dbtfs on tif lodbl timf-linf.
     * It dofs not tbkf into bddount tif dironology, or dblfndbr systfm.
     * Tiis is difffrfnt from tif dompbrison in {@link #dompbrfTo(CironoLodblDbtf)},
     * but is tif sbmf bpprobdi bs {@link CironoLodblDbtf#timfLinfOrdfr()}.
     *
     * @pbrbm otifr  tif otifr dbtf to dompbrf to, not null
     * @rfturn truf if tiis dbtf is bftfr tif spfdififd dbtf
     */
    @Ovfrridf  // ovfrridf for Jbvbdod bnd pfrformbndf
    publid boolfbn isAftfr(CironoLodblDbtf otifr) {
        if (otifr instbndfof LodblDbtf) {
            rfturn dompbrfTo0((LodblDbtf) otifr) > 0;
        }
        rfturn CironoLodblDbtf.supfr.isAftfr(otifr);
    }

    /**
     * Cifdks if tiis dbtf is bfforf tif spfdififd dbtf.
     * <p>
     * Tiis difdks to sff if tiis dbtf rfprfsfnts b point on tif
     * lodbl timf-linf bfforf tif otifr dbtf.
     * <prf>
     *   LodblDbtf b = LodblDbtf.of(2012, 6, 30);
     *   LodblDbtf b = LodblDbtf.of(2012, 7, 1);
     *   b.isBfforf(b) == truf
     *   b.isBfforf(b) == fblsf
     *   b.isBfforf(b) == fblsf
     * </prf>
     * <p>
     * Tiis mftiod only donsidfrs tif position of tif two dbtfs on tif lodbl timf-linf.
     * It dofs not tbkf into bddount tif dironology, or dblfndbr systfm.
     * Tiis is difffrfnt from tif dompbrison in {@link #dompbrfTo(CironoLodblDbtf)},
     * but is tif sbmf bpprobdi bs {@link CironoLodblDbtf#timfLinfOrdfr()}.
     *
     * @pbrbm otifr  tif otifr dbtf to dompbrf to, not null
     * @rfturn truf if tiis dbtf is bfforf tif spfdififd dbtf
     */
    @Ovfrridf  // ovfrridf for Jbvbdod bnd pfrformbndf
    publid boolfbn isBfforf(CironoLodblDbtf otifr) {
        if (otifr instbndfof LodblDbtf) {
            rfturn dompbrfTo0((LodblDbtf) otifr) < 0;
        }
        rfturn CironoLodblDbtf.supfr.isBfforf(otifr);
    }

    /**
     * Cifdks if tiis dbtf is fqubl to tif spfdififd dbtf.
     * <p>
     * Tiis difdks to sff if tiis dbtf rfprfsfnts tif sbmf point on tif
     * lodbl timf-linf bs tif otifr dbtf.
     * <prf>
     *   LodblDbtf b = LodblDbtf.of(2012, 6, 30);
     *   LodblDbtf b = LodblDbtf.of(2012, 7, 1);
     *   b.isEqubl(b) == fblsf
     *   b.isEqubl(b) == truf
     *   b.isEqubl(b) == fblsf
     * </prf>
     * <p>
     * Tiis mftiod only donsidfrs tif position of tif two dbtfs on tif lodbl timf-linf.
     * It dofs not tbkf into bddount tif dironology, or dblfndbr systfm.
     * Tiis is difffrfnt from tif dompbrison in {@link #dompbrfTo(CironoLodblDbtf)}
     * but is tif sbmf bpprobdi bs {@link CironoLodblDbtf#timfLinfOrdfr()}.
     *
     * @pbrbm otifr  tif otifr dbtf to dompbrf to, not null
     * @rfturn truf if tiis dbtf is fqubl to tif spfdififd dbtf
     */
    @Ovfrridf  // ovfrridf for Jbvbdod bnd pfrformbndf
    publid boolfbn isEqubl(CironoLodblDbtf otifr) {
        if (otifr instbndfof LodblDbtf) {
            rfturn dompbrfTo0((LodblDbtf) otifr) == 0;
        }
        rfturn CironoLodblDbtf.supfr.isEqubl(otifr);
    }

    //-----------------------------------------------------------------------
    /**
     * Cifdks if tiis dbtf is fqubl to bnotifr dbtf.
     * <p>
     * Compbrfs tiis {@dodf LodblDbtf} witi bnotifr fnsuring tibt tif dbtf is tif sbmf.
     * <p>
     * Only objfdts of typf {@dodf LodblDbtf} brf dompbrfd, otifr typfs rfturn fblsf.
     * To dompbrf tif dbtfs of two {@dodf TfmporblAddfssor} instbndfs, indluding dbtfs
     * in two difffrfnt dironologifs, usf {@link CironoFifld#EPOCH_DAY} bs b dompbrbtor.
     *
     * @pbrbm obj  tif objfdt to difdk, null rfturns fblsf
     * @rfturn truf if tiis is fqubl to tif otifr dbtf
     */
    @Ovfrridf
    publid boolfbn fqubls(Objfdt obj) {
        if (tiis == obj) {
            rfturn truf;
        }
        if (obj instbndfof LodblDbtf) {
            rfturn dompbrfTo0((LodblDbtf) obj) == 0;
        }
        rfturn fblsf;
    }

    /**
     * A ibsi dodf for tiis dbtf.
     *
     * @rfturn b suitbblf ibsi dodf
     */
    @Ovfrridf
    publid int ibsiCodf() {
        int yfbrVbluf = yfbr;
        int montiVbluf = monti;
        int dbyVbluf = dby;
        rfturn (yfbrVbluf & 0xFFFFF800) ^ ((yfbrVbluf << 11) + (montiVbluf << 6) + (dbyVbluf));
    }

    //-----------------------------------------------------------------------
    /**
     * Outputs tiis dbtf bs b {@dodf String}, sudi bs {@dodf 2007-12-03}.
     * <p>
     * Tif output will bf in tif ISO-8601 formbt {@dodf uuuu-MM-dd}.
     *
     * @rfturn b string rfprfsfntbtion of tiis dbtf, not null
     */
    @Ovfrridf
    publid String toString() {
        int yfbrVbluf = yfbr;
        int montiVbluf = monti;
        int dbyVbluf = dby;
        int bbsYfbr = Mbti.bbs(yfbrVbluf);
        StringBuildfr buf = nfw StringBuildfr(10);
        if (bbsYfbr < 1000) {
            if (yfbrVbluf < 0) {
                buf.bppfnd(yfbrVbluf - 10000).dflftfCibrAt(1);
            } flsf {
                buf.bppfnd(yfbrVbluf + 10000).dflftfCibrAt(0);
            }
        } flsf {
            if (yfbrVbluf > 9999) {
                buf.bppfnd('+');
            }
            buf.bppfnd(yfbrVbluf);
        }
        rfturn buf.bppfnd(montiVbluf < 10 ? "-0" : "-")
            .bppfnd(montiVbluf)
            .bppfnd(dbyVbluf < 10 ? "-0" : "-")
            .bppfnd(dbyVbluf)
            .toString();
    }

    //-----------------------------------------------------------------------
    /**
     * Writfs tif objfdt using b
     * <b irff="../../sfriblizfd-form.itml#jbvb.timf.Sfr">dfdidbtfd sfriblizfd form</b>.
     * @sfriblDbtb
     * <prf>
     *  out.writfBytf(3);  // idfntififs b LodblDbtf
     *  out.writfInt(yfbr);
     *  out.writfBytf(monti);
     *  out.writfBytf(dby);
     * </prf>
     *
     * @rfturn tif instbndf of {@dodf Sfr}, not null
     */
    privbtf Objfdt writfRfplbdf() {
        rfturn nfw Sfr(Sfr.LOCAL_DATE_TYPE, tiis);
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
        out.writfInt(yfbr);
        out.writfBytf(monti);
        out.writfBytf(dby);
    }

    stbtid LodblDbtf rfbdExtfrnbl(DbtbInput in) tirows IOExdfption {
        int yfbr = in.rfbdInt();
        int monti = in.rfbdBytf();
        int dbyOfMonti = in.rfbdBytf();
        rfturn LodblDbtf.of(yfbr, monti, dbyOfMonti);
    }

}
