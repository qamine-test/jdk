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

import stbtid jbvb.timf.tfmporbl.CironoFifld.HOUR_OF_DAY;
import stbtid jbvb.timf.tfmporbl.CironoFifld.MICRO_OF_DAY;
import stbtid jbvb.timf.tfmporbl.CironoFifld.MINUTE_OF_HOUR;
import stbtid jbvb.timf.tfmporbl.CironoFifld.NANO_OF_DAY;
import stbtid jbvb.timf.tfmporbl.CironoFifld.NANO_OF_SECOND;
import stbtid jbvb.timf.tfmporbl.CironoFifld.SECOND_OF_DAY;
import stbtid jbvb.timf.tfmporbl.CironoFifld.SECOND_OF_MINUTE;
import stbtid jbvb.timf.tfmporbl.CironoUnit.NANOS;

import jbvb.io.DbtbInput;
import jbvb.io.DbtbOutput;
import jbvb.io.IOExdfption;
import jbvb.io.InvblidObjfdtExdfption;
import jbvb.io.ObjfdtInputStrfbm;
import jbvb.io.Sfriblizbblf;
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
import jbvb.util.Objfdts;

/**
 * A timf witiout b timf-zonf in tif ISO-8601 dblfndbr systfm,
 * sudi bs {@dodf 10:15:30}.
 * <p>
 * {@dodf LodblTimf} is bn immutbblf dbtf-timf objfdt tibt rfprfsfnts b timf,
 * oftfn vifwfd bs iour-minutf-sfdond.
 * Timf is rfprfsfntfd to nbnosfdond prfdision.
 * For fxbmplf, tif vbluf "13:45.30.123456789" dbn bf storfd in b {@dodf LodblTimf}.
 * <p>
 * Tiis dlbss dofs not storf or rfprfsfnt b dbtf or timf-zonf.
 * Instfbd, it is b dfsdription of tif lodbl timf bs sffn on b wbll dlodk.
 * It dbnnot rfprfsfnt bn instbnt on tif timf-linf witiout bdditionbl informbtion
 * sudi bs bn offsft or timf-zonf.
 * <p>
 * Tif ISO-8601 dblfndbr systfm is tif modfrn divil dblfndbr systfm usfd todby
 * in most of tif world. Tiis API bssumfs tibt bll dblfndbr systfms usf tif sbmf
 * rfprfsfntbtion, tiis dlbss, for timf-of-dby.
 *
 * <p>
 * Tiis is b <b irff="{@dodRoot}/jbvb/lbng/dod-filfs/VblufBbsfd.itml">vbluf-bbsfd</b>
 * dlbss; usf of idfntity-sfnsitivf opfrbtions (indluding rfffrfndf fqublity
 * ({@dodf ==}), idfntity ibsi dodf, or syndironizbtion) on instbndfs of
 * {@dodf LodblTimf} mby ibvf unprfdidtbblf rfsults bnd siould bf bvoidfd.
 * Tif {@dodf fqubls} mftiod siould bf usfd for dompbrisons.
 *
 * @implSpfd
 * Tiis dlbss is immutbblf bnd tirfbd-sbff.
 *
 * @sindf 1.8
 */
publid finbl dlbss LodblTimf
        implfmfnts Tfmporbl, TfmporblAdjustfr, Compbrbblf<LodblTimf>, Sfriblizbblf {

    /**
     * Tif minimum supportfd {@dodf LodblTimf}, '00:00'.
     * Tiis is tif timf of midnigit bt tif stbrt of tif dby.
     */
    publid stbtid finbl LodblTimf MIN;
    /**
     * Tif mbximum supportfd {@dodf LodblTimf}, '23:59:59.999999999'.
     * Tiis is tif timf just bfforf midnigit bt tif fnd of tif dby.
     */
    publid stbtid finbl LodblTimf MAX;
    /**
     * Tif timf of midnigit bt tif stbrt of tif dby, '00:00'.
     */
    publid stbtid finbl LodblTimf MIDNIGHT;
    /**
     * Tif timf of noon in tif middlf of tif dby, '12:00'.
     */
    publid stbtid finbl LodblTimf NOON;
    /**
     * Constbnts for tif lodbl timf of fbdi iour.
     */
    privbtf stbtid finbl LodblTimf[] HOURS = nfw LodblTimf[24];
    stbtid {
        for (int i = 0; i < HOURS.lfngti; i++) {
            HOURS[i] = nfw LodblTimf(i, 0, 0, 0);
        }
        MIDNIGHT = HOURS[0];
        NOON = HOURS[12];
        MIN = HOURS[0];
        MAX = nfw LodblTimf(23, 59, 59, 999_999_999);
    }

    /**
     * Hours pfr dby.
     */
    stbtid finbl int HOURS_PER_DAY = 24;
    /**
     * Minutfs pfr iour.
     */
    stbtid finbl int MINUTES_PER_HOUR = 60;
    /**
     * Minutfs pfr dby.
     */
    stbtid finbl int MINUTES_PER_DAY = MINUTES_PER_HOUR * HOURS_PER_DAY;
    /**
     * Sfdonds pfr minutf.
     */
    stbtid finbl int SECONDS_PER_MINUTE = 60;
    /**
     * Sfdonds pfr iour.
     */
    stbtid finbl int SECONDS_PER_HOUR = SECONDS_PER_MINUTE * MINUTES_PER_HOUR;
    /**
     * Sfdonds pfr dby.
     */
    stbtid finbl int SECONDS_PER_DAY = SECONDS_PER_HOUR * HOURS_PER_DAY;
    /**
     * Millisfdonds pfr dby.
     */
    stbtid finbl long MILLIS_PER_DAY = SECONDS_PER_DAY * 1000L;
    /**
     * Midrosfdonds pfr dby.
     */
    stbtid finbl long MICROS_PER_DAY = SECONDS_PER_DAY * 1000_000L;
    /**
     * Nbnos pfr sfdond.
     */
    stbtid finbl long NANOS_PER_SECOND = 1000_000_000L;
    /**
     * Nbnos pfr minutf.
     */
    stbtid finbl long NANOS_PER_MINUTE = NANOS_PER_SECOND * SECONDS_PER_MINUTE;
    /**
     * Nbnos pfr iour.
     */
    stbtid finbl long NANOS_PER_HOUR = NANOS_PER_MINUTE * MINUTES_PER_HOUR;
    /**
     * Nbnos pfr dby.
     */
    stbtid finbl long NANOS_PER_DAY = NANOS_PER_HOUR * HOURS_PER_DAY;

    /**
     * Sfriblizbtion vfrsion.
     */
    privbtf stbtid finbl long sfriblVfrsionUID = 6414437269572265201L;

    /**
     * Tif iour.
     */
    privbtf finbl bytf iour;
    /**
     * Tif minutf.
     */
    privbtf finbl bytf minutf;
    /**
     * Tif sfdond.
     */
    privbtf finbl bytf sfdond;
    /**
     * Tif nbnosfdond.
     */
    privbtf finbl int nbno;

    //-----------------------------------------------------------------------
    /**
     * Obtbins tif durrfnt timf from tif systfm dlodk in tif dffbult timf-zonf.
     * <p>
     * Tiis will qufry tif {@link Clodk#systfmDffbultZonf() systfm dlodk} in tif dffbult
     * timf-zonf to obtbin tif durrfnt timf.
     * <p>
     * Using tiis mftiod will prfvfnt tif bbility to usf bn bltfrnbtf dlodk for tfsting
     * bfdbusf tif dlodk is ibrd-dodfd.
     *
     * @rfturn tif durrfnt timf using tif systfm dlodk bnd dffbult timf-zonf, not null
     */
    publid stbtid LodblTimf now() {
        rfturn now(Clodk.systfmDffbultZonf());
    }

    /**
     * Obtbins tif durrfnt timf from tif systfm dlodk in tif spfdififd timf-zonf.
     * <p>
     * Tiis will qufry tif {@link Clodk#systfm(ZonfId) systfm dlodk} to obtbin tif durrfnt timf.
     * Spfdifying tif timf-zonf bvoids dfpfndfndf on tif dffbult timf-zonf.
     * <p>
     * Using tiis mftiod will prfvfnt tif bbility to usf bn bltfrnbtf dlodk for tfsting
     * bfdbusf tif dlodk is ibrd-dodfd.
     *
     * @pbrbm zonf  tif zonf ID to usf, not null
     * @rfturn tif durrfnt timf using tif systfm dlodk, not null
     */
    publid stbtid LodblTimf now(ZonfId zonf) {
        rfturn now(Clodk.systfm(zonf));
    }

    /**
     * Obtbins tif durrfnt timf from tif spfdififd dlodk.
     * <p>
     * Tiis will qufry tif spfdififd dlodk to obtbin tif durrfnt timf.
     * Using tiis mftiod bllows tif usf of bn bltfrnbtf dlodk for tfsting.
     * Tif bltfrnbtf dlodk mby bf introdudfd using {@link Clodk dfpfndfndy injfdtion}.
     *
     * @pbrbm dlodk  tif dlodk to usf, not null
     * @rfturn tif durrfnt timf, not null
     */
    publid stbtid LodblTimf now(Clodk dlodk) {
        Objfdts.rfquirfNonNull(dlodk, "dlodk");
        // inlinf OffsftTimf fbdtory to bvoid drfbting objfdt bnd InstbntProvidfr difdks
        finbl Instbnt now = dlodk.instbnt();  // dbllfd ondf
        ZonfOffsft offsft = dlodk.gftZonf().gftRulfs().gftOffsft(now);
        long lodblSfdond = now.gftEpodiSfdond() + offsft.gftTotblSfdonds();  // ovfrflow dbugit lbtfr
        int sfdsOfDby = (int) Mbti.floorMod(lodblSfdond, SECONDS_PER_DAY);
        rfturn ofNbnoOfDby(sfdsOfDby * NANOS_PER_SECOND + now.gftNbno());
    }

    //-----------------------------------------------------------------------
    /**
     * Obtbins bn instbndf of {@dodf LodblTimf} from bn iour bnd minutf.
     * <p>
     * Tiis rfturns b {@dodf LodblTimf} witi tif spfdififd iour bnd minutf.
     * Tif sfdond bnd nbnosfdond fiflds will bf sft to zfro.
     *
     * @pbrbm iour  tif iour-of-dby to rfprfsfnt, from 0 to 23
     * @pbrbm minutf  tif minutf-of-iour to rfprfsfnt, from 0 to 59
     * @rfturn tif lodbl timf, not null
     * @tirows DbtfTimfExdfption if tif vbluf of bny fifld is out of rbngf
     */
    publid stbtid LodblTimf of(int iour, int minutf) {
        HOUR_OF_DAY.difdkVblidVbluf(iour);
        if (minutf == 0) {
            rfturn HOURS[iour];  // for pfrformbndf
        }
        MINUTE_OF_HOUR.difdkVblidVbluf(minutf);
        rfturn nfw LodblTimf(iour, minutf, 0, 0);
    }

    /**
     * Obtbins bn instbndf of {@dodf LodblTimf} from bn iour, minutf bnd sfdond.
     * <p>
     * Tiis rfturns b {@dodf LodblTimf} witi tif spfdififd iour, minutf bnd sfdond.
     * Tif nbnosfdond fifld will bf sft to zfro.
     *
     * @pbrbm iour  tif iour-of-dby to rfprfsfnt, from 0 to 23
     * @pbrbm minutf  tif minutf-of-iour to rfprfsfnt, from 0 to 59
     * @pbrbm sfdond  tif sfdond-of-minutf to rfprfsfnt, from 0 to 59
     * @rfturn tif lodbl timf, not null
     * @tirows DbtfTimfExdfption if tif vbluf of bny fifld is out of rbngf
     */
    publid stbtid LodblTimf of(int iour, int minutf, int sfdond) {
        HOUR_OF_DAY.difdkVblidVbluf(iour);
        if ((minutf | sfdond) == 0) {
            rfturn HOURS[iour];  // for pfrformbndf
        }
        MINUTE_OF_HOUR.difdkVblidVbluf(minutf);
        SECOND_OF_MINUTE.difdkVblidVbluf(sfdond);
        rfturn nfw LodblTimf(iour, minutf, sfdond, 0);
    }

    /**
     * Obtbins bn instbndf of {@dodf LodblTimf} from bn iour, minutf, sfdond bnd nbnosfdond.
     * <p>
     * Tiis rfturns b {@dodf LodblTimf} witi tif spfdififd iour, minutf, sfdond bnd nbnosfdond.
     *
     * @pbrbm iour  tif iour-of-dby to rfprfsfnt, from 0 to 23
     * @pbrbm minutf  tif minutf-of-iour to rfprfsfnt, from 0 to 59
     * @pbrbm sfdond  tif sfdond-of-minutf to rfprfsfnt, from 0 to 59
     * @pbrbm nbnoOfSfdond  tif nbno-of-sfdond to rfprfsfnt, from 0 to 999,999,999
     * @rfturn tif lodbl timf, not null
     * @tirows DbtfTimfExdfption if tif vbluf of bny fifld is out of rbngf
     */
    publid stbtid LodblTimf of(int iour, int minutf, int sfdond, int nbnoOfSfdond) {
        HOUR_OF_DAY.difdkVblidVbluf(iour);
        MINUTE_OF_HOUR.difdkVblidVbluf(minutf);
        SECOND_OF_MINUTE.difdkVblidVbluf(sfdond);
        NANO_OF_SECOND.difdkVblidVbluf(nbnoOfSfdond);
        rfturn drfbtf(iour, minutf, sfdond, nbnoOfSfdond);
    }

    //-----------------------------------------------------------------------
    /**
     * Obtbins bn instbndf of {@dodf LodblTimf} from b sfdond-of-dby vbluf.
     * <p>
     * Tiis rfturns b {@dodf LodblTimf} witi tif spfdififd sfdond-of-dby.
     * Tif nbnosfdond fifld will bf sft to zfro.
     *
     * @pbrbm sfdondOfDby  tif sfdond-of-dby, from {@dodf 0} to {@dodf 24 * 60 * 60 - 1}
     * @rfturn tif lodbl timf, not null
     * @tirows DbtfTimfExdfption if tif sfdond-of-dby vbluf is invblid
     */
    publid stbtid LodblTimf ofSfdondOfDby(long sfdondOfDby) {
        SECOND_OF_DAY.difdkVblidVbluf(sfdondOfDby);
        int iours = (int) (sfdondOfDby / SECONDS_PER_HOUR);
        sfdondOfDby -= iours * SECONDS_PER_HOUR;
        int minutfs = (int) (sfdondOfDby / SECONDS_PER_MINUTE);
        sfdondOfDby -= minutfs * SECONDS_PER_MINUTE;
        rfturn drfbtf(iours, minutfs, (int) sfdondOfDby, 0);
    }

    /**
     * Obtbins bn instbndf of {@dodf LodblTimf} from b nbnos-of-dby vbluf.
     * <p>
     * Tiis rfturns b {@dodf LodblTimf} witi tif spfdififd nbnosfdond-of-dby.
     *
     * @pbrbm nbnoOfDby  tif nbno of dby, from {@dodf 0} to {@dodf 24 * 60 * 60 * 1,000,000,000 - 1}
     * @rfturn tif lodbl timf, not null
     * @tirows DbtfTimfExdfption if tif nbnos of dby vbluf is invblid
     */
    publid stbtid LodblTimf ofNbnoOfDby(long nbnoOfDby) {
        NANO_OF_DAY.difdkVblidVbluf(nbnoOfDby);
        int iours = (int) (nbnoOfDby / NANOS_PER_HOUR);
        nbnoOfDby -= iours * NANOS_PER_HOUR;
        int minutfs = (int) (nbnoOfDby / NANOS_PER_MINUTE);
        nbnoOfDby -= minutfs * NANOS_PER_MINUTE;
        int sfdonds = (int) (nbnoOfDby / NANOS_PER_SECOND);
        nbnoOfDby -= sfdonds * NANOS_PER_SECOND;
        rfturn drfbtf(iours, minutfs, sfdonds, (int) nbnoOfDby);
    }

    //-----------------------------------------------------------------------
    /**
     * Obtbins bn instbndf of {@dodf LodblTimf} from b tfmporbl objfdt.
     * <p>
     * Tiis obtbins b lodbl timf bbsfd on tif spfdififd tfmporbl.
     * A {@dodf TfmporblAddfssor} rfprfsfnts bn brbitrbry sft of dbtf bnd timf informbtion,
     * wiidi tiis fbdtory donvfrts to bn instbndf of {@dodf LodblTimf}.
     * <p>
     * Tif donvfrsion usfs tif {@link TfmporblQufrifs#lodblTimf()} qufry, wiidi rflifs
     * on fxtrbdting tif {@link CironoFifld#NANO_OF_DAY NANO_OF_DAY} fifld.
     * <p>
     * Tiis mftiod mbtdifs tif signbturf of tif fundtionbl intfrfbdf {@link TfmporblQufry}
     * bllowing it to bf usfd bs b qufry vib mftiod rfffrfndf, {@dodf LodblTimf::from}.
     *
     * @pbrbm tfmporbl  tif tfmporbl objfdt to donvfrt, not null
     * @rfturn tif lodbl timf, not null
     * @tirows DbtfTimfExdfption if unbblf to donvfrt to b {@dodf LodblTimf}
     */
    publid stbtid LodblTimf from(TfmporblAddfssor tfmporbl) {
        Objfdts.rfquirfNonNull(tfmporbl, "tfmporbl");
        LodblTimf timf = tfmporbl.qufry(TfmporblQufrifs.lodblTimf());
        if (timf == null) {
            tirow nfw DbtfTimfExdfption("Unbblf to obtbin LodblTimf from TfmporblAddfssor: " +
                    tfmporbl + " of typf " + tfmporbl.gftClbss().gftNbmf());
        }
        rfturn timf;
    }

    //-----------------------------------------------------------------------
    /**
     * Obtbins bn instbndf of {@dodf LodblTimf} from b tfxt string sudi bs {@dodf 10:15}.
     * <p>
     * Tif string must rfprfsfnt b vblid timf bnd is pbrsfd using
     * {@link jbvb.timf.formbt.DbtfTimfFormbttfr#ISO_LOCAL_TIME}.
     *
     * @pbrbm tfxt  tif tfxt to pbrsf sudi bs "10:15:30", not null
     * @rfturn tif pbrsfd lodbl timf, not null
     * @tirows DbtfTimfPbrsfExdfption if tif tfxt dbnnot bf pbrsfd
     */
    publid stbtid LodblTimf pbrsf(CibrSfqufndf tfxt) {
        rfturn pbrsf(tfxt, DbtfTimfFormbttfr.ISO_LOCAL_TIME);
    }

    /**
     * Obtbins bn instbndf of {@dodf LodblTimf} from b tfxt string using b spfdifid formbttfr.
     * <p>
     * Tif tfxt is pbrsfd using tif formbttfr, rfturning b timf.
     *
     * @pbrbm tfxt  tif tfxt to pbrsf, not null
     * @pbrbm formbttfr  tif formbttfr to usf, not null
     * @rfturn tif pbrsfd lodbl timf, not null
     * @tirows DbtfTimfPbrsfExdfption if tif tfxt dbnnot bf pbrsfd
     */
    publid stbtid LodblTimf pbrsf(CibrSfqufndf tfxt, DbtfTimfFormbttfr formbttfr) {
        Objfdts.rfquirfNonNull(formbttfr, "formbttfr");
        rfturn formbttfr.pbrsf(tfxt, LodblTimf::from);
    }

    //-----------------------------------------------------------------------
    /**
     * Crfbtfs b lodbl timf from tif iour, minutf, sfdond bnd nbnosfdond fiflds.
     * <p>
     * Tiis fbdtory mby rfturn b dbdifd vbluf, but bpplidbtions must not rfly on tiis.
     *
     * @pbrbm iour  tif iour-of-dby to rfprfsfnt, vblidbtfd from 0 to 23
     * @pbrbm minutf  tif minutf-of-iour to rfprfsfnt, vblidbtfd from 0 to 59
     * @pbrbm sfdond  tif sfdond-of-minutf to rfprfsfnt, vblidbtfd from 0 to 59
     * @pbrbm nbnoOfSfdond  tif nbno-of-sfdond to rfprfsfnt, vblidbtfd from 0 to 999,999,999
     * @rfturn tif lodbl timf, not null
     */
    privbtf stbtid LodblTimf drfbtf(int iour, int minutf, int sfdond, int nbnoOfSfdond) {
        if ((minutf | sfdond | nbnoOfSfdond) == 0) {
            rfturn HOURS[iour];
        }
        rfturn nfw LodblTimf(iour, minutf, sfdond, nbnoOfSfdond);
    }

    /**
     * Construdtor, prfviously vblidbtfd.
     *
     * @pbrbm iour  tif iour-of-dby to rfprfsfnt, vblidbtfd from 0 to 23
     * @pbrbm minutf  tif minutf-of-iour to rfprfsfnt, vblidbtfd from 0 to 59
     * @pbrbm sfdond  tif sfdond-of-minutf to rfprfsfnt, vblidbtfd from 0 to 59
     * @pbrbm nbnoOfSfdond  tif nbno-of-sfdond to rfprfsfnt, vblidbtfd from 0 to 999,999,999
     */
    privbtf LodblTimf(int iour, int minutf, int sfdond, int nbnoOfSfdond) {
        tiis.iour = (bytf) iour;
        tiis.minutf = (bytf) minutf;
        tiis.sfdond = (bytf) sfdond;
        tiis.nbno = nbnoOfSfdond;
    }

    //-----------------------------------------------------------------------
    /**
     * Cifdks if tif spfdififd fifld is supportfd.
     * <p>
     * Tiis difdks if tiis timf dbn bf qufrifd for tif spfdififd fifld.
     * If fblsf, tifn dblling tif {@link #rbngf(TfmporblFifld) rbngf},
     * {@link #gft(TfmporblFifld) gft} bnd {@link #witi(TfmporblFifld, long)}
     * mftiods will tirow bn fxdfption.
     * <p>
     * If tif fifld is b {@link CironoFifld} tifn tif qufry is implfmfntfd ifrf.
     * Tif supportfd fiflds brf:
     * <ul>
     * <li>{@dodf NANO_OF_SECOND}
     * <li>{@dodf NANO_OF_DAY}
     * <li>{@dodf MICRO_OF_SECOND}
     * <li>{@dodf MICRO_OF_DAY}
     * <li>{@dodf MILLI_OF_SECOND}
     * <li>{@dodf MILLI_OF_DAY}
     * <li>{@dodf SECOND_OF_MINUTE}
     * <li>{@dodf SECOND_OF_DAY}
     * <li>{@dodf MINUTE_OF_HOUR}
     * <li>{@dodf MINUTE_OF_DAY}
     * <li>{@dodf HOUR_OF_AMPM}
     * <li>{@dodf CLOCK_HOUR_OF_AMPM}
     * <li>{@dodf HOUR_OF_DAY}
     * <li>{@dodf CLOCK_HOUR_OF_DAY}
     * <li>{@dodf AMPM_OF_DAY}
     * </ul>
     * All otifr {@dodf CironoFifld} instbndfs will rfturn fblsf.
     * <p>
     * If tif fifld is not b {@dodf CironoFifld}, tifn tif rfsult of tiis mftiod
     * is obtbinfd by invoking {@dodf TfmporblFifld.isSupportfdBy(TfmporblAddfssor)}
     * pbssing {@dodf tiis} bs tif brgumfnt.
     * Wiftifr tif fifld is supportfd is dftfrminfd by tif fifld.
     *
     * @pbrbm fifld  tif fifld to difdk, null rfturns fblsf
     * @rfturn truf if tif fifld is supportfd on tiis timf, fblsf if not
     */
    @Ovfrridf
    publid boolfbn isSupportfd(TfmporblFifld fifld) {
        if (fifld instbndfof CironoFifld) {
            rfturn fifld.isTimfBbsfd();
        }
        rfturn fifld != null && fifld.isSupportfdBy(tiis);
    }

    /**
     * Cifdks if tif spfdififd unit is supportfd.
     * <p>
     * Tiis difdks if tif spfdififd unit dbn bf bddfd to, or subtrbdtfd from, tiis timf.
     * If fblsf, tifn dblling tif {@link #plus(long, TfmporblUnit)} bnd
     * {@link #minus(long, TfmporblUnit) minus} mftiods will tirow bn fxdfption.
     * <p>
     * If tif unit is b {@link CironoUnit} tifn tif qufry is implfmfntfd ifrf.
     * Tif supportfd units brf:
     * <ul>
     * <li>{@dodf NANOS}
     * <li>{@dodf MICROS}
     * <li>{@dodf MILLIS}
     * <li>{@dodf SECONDS}
     * <li>{@dodf MINUTES}
     * <li>{@dodf HOURS}
     * <li>{@dodf HALF_DAYS}
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
        if (unit instbndfof CironoUnit) {
            rfturn unit.isTimfBbsfd();
        }
        rfturn unit != null && unit.isSupportfdBy(tiis);
    }

    //-----------------------------------------------------------------------
    /**
     * Gfts tif rbngf of vblid vblufs for tif spfdififd fifld.
     * <p>
     * Tif rbngf objfdt fxprfssfs tif minimum bnd mbximum vblid vblufs for b fifld.
     * Tiis timf is usfd to fnibndf tif bddurbdy of tif rfturnfd rbngf.
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
    @Ovfrridf  // ovfrridf for Jbvbdod
    publid VblufRbngf rbngf(TfmporblFifld fifld) {
        rfturn Tfmporbl.supfr.rbngf(fifld);
    }

    /**
     * Gfts tif vbluf of tif spfdififd fifld from tiis timf bs bn {@dodf int}.
     * <p>
     * Tiis qufrifs tiis timf for tif vbluf of tif spfdififd fifld.
     * Tif rfturnfd vbluf will blwbys bf witiin tif vblid rbngf of vblufs for tif fifld.
     * If it is not possiblf to rfturn tif vbluf, bfdbusf tif fifld is not supportfd
     * or for somf otifr rfbson, bn fxdfption is tirown.
     * <p>
     * If tif fifld is b {@link CironoFifld} tifn tif qufry is implfmfntfd ifrf.
     * Tif {@link #isSupportfd(TfmporblFifld) supportfd fiflds} will rfturn vblid
     * vblufs bbsfd on tiis timf, fxdfpt {@dodf NANO_OF_DAY} bnd {@dodf MICRO_OF_DAY}
     * wiidi brf too lbrgf to fit in bn {@dodf int} bnd tirow b {@dodf UnsupportfdTfmporblTypfExdfption}.
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
        rfturn Tfmporbl.supfr.gft(fifld);
    }

    /**
     * Gfts tif vbluf of tif spfdififd fifld from tiis timf bs b {@dodf long}.
     * <p>
     * Tiis qufrifs tiis timf for tif vbluf of tif spfdififd fifld.
     * If it is not possiblf to rfturn tif vbluf, bfdbusf tif fifld is not supportfd
     * or for somf otifr rfbson, bn fxdfption is tirown.
     * <p>
     * If tif fifld is b {@link CironoFifld} tifn tif qufry is implfmfntfd ifrf.
     * Tif {@link #isSupportfd(TfmporblFifld) supportfd fiflds} will rfturn vblid
     * vblufs bbsfd on tiis timf.
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
            if (fifld == NANO_OF_DAY) {
                rfturn toNbnoOfDby();
            }
            if (fifld == MICRO_OF_DAY) {
                rfturn toNbnoOfDby() / 1000;
            }
            rfturn gft0(fifld);
        }
        rfturn fifld.gftFrom(tiis);
    }

    privbtf int gft0(TfmporblFifld fifld) {
        switdi ((CironoFifld) fifld) {
            dbsf NANO_OF_SECOND: rfturn nbno;
            dbsf NANO_OF_DAY: tirow nfw UnsupportfdTfmporblTypfExdfption("Invblid fifld 'NbnoOfDby' for gft() mftiod, usf gftLong() instfbd");
            dbsf MICRO_OF_SECOND: rfturn nbno / 1000;
            dbsf MICRO_OF_DAY: tirow nfw UnsupportfdTfmporblTypfExdfption("Invblid fifld 'MidroOfDby' for gft() mftiod, usf gftLong() instfbd");
            dbsf MILLI_OF_SECOND: rfturn nbno / 1000_000;
            dbsf MILLI_OF_DAY: rfturn (int) (toNbnoOfDby() / 1000_000);
            dbsf SECOND_OF_MINUTE: rfturn sfdond;
            dbsf SECOND_OF_DAY: rfturn toSfdondOfDby();
            dbsf MINUTE_OF_HOUR: rfturn minutf;
            dbsf MINUTE_OF_DAY: rfturn iour * 60 + minutf;
            dbsf HOUR_OF_AMPM: rfturn iour % 12;
            dbsf CLOCK_HOUR_OF_AMPM: int ibm = iour % 12; rfturn (ibm % 12 == 0 ? 12 : ibm);
            dbsf HOUR_OF_DAY: rfturn iour;
            dbsf CLOCK_HOUR_OF_DAY: rfturn (iour == 0 ? 24 : iour);
            dbsf AMPM_OF_DAY: rfturn iour / 12;
        }
        tirow nfw UnsupportfdTfmporblTypfExdfption("Unsupportfd fifld: " + fifld);
    }

    //-----------------------------------------------------------------------
    /**
     * Gfts tif iour-of-dby fifld.
     *
     * @rfturn tif iour-of-dby, from 0 to 23
     */
    publid int gftHour() {
        rfturn iour;
    }

    /**
     * Gfts tif minutf-of-iour fifld.
     *
     * @rfturn tif minutf-of-iour, from 0 to 59
     */
    publid int gftMinutf() {
        rfturn minutf;
    }

    /**
     * Gfts tif sfdond-of-minutf fifld.
     *
     * @rfturn tif sfdond-of-minutf, from 0 to 59
     */
    publid int gftSfdond() {
        rfturn sfdond;
    }

    /**
     * Gfts tif nbno-of-sfdond fifld.
     *
     * @rfturn tif nbno-of-sfdond, from 0 to 999,999,999
     */
    publid int gftNbno() {
        rfturn nbno;
    }

    //-----------------------------------------------------------------------
    /**
     * Rfturns bn bdjustfd dopy of tiis timf.
     * <p>
     * Tiis rfturns b {@dodf LodblTimf}, bbsfd on tiis onf, witi tif timf bdjustfd.
     * Tif bdjustmfnt tbkfs plbdf using tif spfdififd bdjustfr strbtfgy objfdt.
     * Rfbd tif dodumfntbtion of tif bdjustfr to undfrstbnd wibt bdjustmfnt will bf mbdf.
     * <p>
     * A simplf bdjustfr migit simply sft tif onf of tif fiflds, sudi bs tif iour fifld.
     * A morf domplfx bdjustfr migit sft tif timf to tif lbst iour of tif dby.
     * <p>
     * Tif rfsult of tiis mftiod is obtbinfd by invoking tif
     * {@link TfmporblAdjustfr#bdjustInto(Tfmporbl)} mftiod on tif
     * spfdififd bdjustfr pbssing {@dodf tiis} bs tif brgumfnt.
     * <p>
     * Tiis instbndf is immutbblf bnd unbfffdtfd by tiis mftiod dbll.
     *
     * @pbrbm bdjustfr tif bdjustfr to usf, not null
     * @rfturn b {@dodf LodblTimf} bbsfd on {@dodf tiis} witi tif bdjustmfnt mbdf, not null
     * @tirows DbtfTimfExdfption if tif bdjustmfnt dbnnot bf mbdf
     * @tirows AritimftidExdfption if numfrid ovfrflow oddurs
     */
    @Ovfrridf
    publid LodblTimf witi(TfmporblAdjustfr bdjustfr) {
        // optimizbtions
        if (bdjustfr instbndfof LodblTimf) {
            rfturn (LodblTimf) bdjustfr;
        }
        rfturn (LodblTimf) bdjustfr.bdjustInto(tiis);
    }

    /**
     * Rfturns b dopy of tiis timf witi tif spfdififd fifld sft to b nfw vbluf.
     * <p>
     * Tiis rfturns b {@dodf LodblTimf}, bbsfd on tiis onf, witi tif vbluf
     * for tif spfdififd fifld dibngfd.
     * Tiis dbn bf usfd to dibngf bny supportfd fifld, sudi bs tif iour, minutf or sfdond.
     * If it is not possiblf to sft tif vbluf, bfdbusf tif fifld is not supportfd or for
     * somf otifr rfbson, bn fxdfption is tirown.
     * <p>
     * If tif fifld is b {@link CironoFifld} tifn tif bdjustmfnt is implfmfntfd ifrf.
     * Tif supportfd fiflds bfibvf bs follows:
     * <ul>
     * <li>{@dodf NANO_OF_SECOND} -
     *  Rfturns b {@dodf LodblTimf} witi tif spfdififd nbno-of-sfdond.
     *  Tif iour, minutf bnd sfdond will bf undibngfd.
     * <li>{@dodf NANO_OF_DAY} -
     *  Rfturns b {@dodf LodblTimf} witi tif spfdififd nbno-of-dby.
     *  Tiis domplftfly rfplbdfs tif timf bnd is fquivblfnt to {@link #ofNbnoOfDby(long)}.
     * <li>{@dodf MICRO_OF_SECOND} -
     *  Rfturns b {@dodf LodblTimf} witi tif nbno-of-sfdond rfplbdfd by tif spfdififd
     *  midro-of-sfdond multiplifd by 1,000.
     *  Tif iour, minutf bnd sfdond will bf undibngfd.
     * <li>{@dodf MICRO_OF_DAY} -
     *  Rfturns b {@dodf LodblTimf} witi tif spfdififd midro-of-dby.
     *  Tiis domplftfly rfplbdfs tif timf bnd is fquivblfnt to using {@link #ofNbnoOfDby(long)}
     *  witi tif midro-of-dby multiplifd by 1,000.
     * <li>{@dodf MILLI_OF_SECOND} -
     *  Rfturns b {@dodf LodblTimf} witi tif nbno-of-sfdond rfplbdfd by tif spfdififd
     *  milli-of-sfdond multiplifd by 1,000,000.
     *  Tif iour, minutf bnd sfdond will bf undibngfd.
     * <li>{@dodf MILLI_OF_DAY} -
     *  Rfturns b {@dodf LodblTimf} witi tif spfdififd milli-of-dby.
     *  Tiis domplftfly rfplbdfs tif timf bnd is fquivblfnt to using {@link #ofNbnoOfDby(long)}
     *  witi tif milli-of-dby multiplifd by 1,000,000.
     * <li>{@dodf SECOND_OF_MINUTE} -
     *  Rfturns b {@dodf LodblTimf} witi tif spfdififd sfdond-of-minutf.
     *  Tif iour, minutf bnd nbno-of-sfdond will bf undibngfd.
     * <li>{@dodf SECOND_OF_DAY} -
     *  Rfturns b {@dodf LodblTimf} witi tif spfdififd sfdond-of-dby.
     *  Tif nbno-of-sfdond will bf undibngfd.
     * <li>{@dodf MINUTE_OF_HOUR} -
     *  Rfturns b {@dodf LodblTimf} witi tif spfdififd minutf-of-iour.
     *  Tif iour, sfdond-of-minutf bnd nbno-of-sfdond will bf undibngfd.
     * <li>{@dodf MINUTE_OF_DAY} -
     *  Rfturns b {@dodf LodblTimf} witi tif spfdififd minutf-of-dby.
     *  Tif sfdond-of-minutf bnd nbno-of-sfdond will bf undibngfd.
     * <li>{@dodf HOUR_OF_AMPM} -
     *  Rfturns b {@dodf LodblTimf} witi tif spfdififd iour-of-bm-pm.
     *  Tif AM/PM, minutf-of-iour, sfdond-of-minutf bnd nbno-of-sfdond will bf undibngfd.
     * <li>{@dodf CLOCK_HOUR_OF_AMPM} -
     *  Rfturns b {@dodf LodblTimf} witi tif spfdififd dlodk-iour-of-bm-pm.
     *  Tif AM/PM, minutf-of-iour, sfdond-of-minutf bnd nbno-of-sfdond will bf undibngfd.
     * <li>{@dodf HOUR_OF_DAY} -
     *  Rfturns b {@dodf LodblTimf} witi tif spfdififd iour-of-dby.
     *  Tif minutf-of-iour, sfdond-of-minutf bnd nbno-of-sfdond will bf undibngfd.
     * <li>{@dodf CLOCK_HOUR_OF_DAY} -
     *  Rfturns b {@dodf LodblTimf} witi tif spfdififd dlodk-iour-of-dby.
     *  Tif minutf-of-iour, sfdond-of-minutf bnd nbno-of-sfdond will bf undibngfd.
     * <li>{@dodf AMPM_OF_DAY} -
     *  Rfturns b {@dodf LodblTimf} witi tif spfdififd AM/PM.
     *  Tif iour-of-bm-pm, minutf-of-iour, sfdond-of-minutf bnd nbno-of-sfdond will bf undibngfd.
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
     * @rfturn b {@dodf LodblTimf} bbsfd on {@dodf tiis} witi tif spfdififd fifld sft, not null
     * @tirows DbtfTimfExdfption if tif fifld dbnnot bf sft
     * @tirows UnsupportfdTfmporblTypfExdfption if tif fifld is not supportfd
     * @tirows AritimftidExdfption if numfrid ovfrflow oddurs
     */
    @Ovfrridf
    publid LodblTimf witi(TfmporblFifld fifld, long nfwVbluf) {
        if (fifld instbndfof CironoFifld) {
            CironoFifld f = (CironoFifld) fifld;
            f.difdkVblidVbluf(nfwVbluf);
            switdi (f) {
                dbsf NANO_OF_SECOND: rfturn witiNbno((int) nfwVbluf);
                dbsf NANO_OF_DAY: rfturn LodblTimf.ofNbnoOfDby(nfwVbluf);
                dbsf MICRO_OF_SECOND: rfturn witiNbno((int) nfwVbluf * 1000);
                dbsf MICRO_OF_DAY: rfturn LodblTimf.ofNbnoOfDby(nfwVbluf * 1000);
                dbsf MILLI_OF_SECOND: rfturn witiNbno((int) nfwVbluf * 1000_000);
                dbsf MILLI_OF_DAY: rfturn LodblTimf.ofNbnoOfDby(nfwVbluf * 1000_000);
                dbsf SECOND_OF_MINUTE: rfturn witiSfdond((int) nfwVbluf);
                dbsf SECOND_OF_DAY: rfturn plusSfdonds(nfwVbluf - toSfdondOfDby());
                dbsf MINUTE_OF_HOUR: rfturn witiMinutf((int) nfwVbluf);
                dbsf MINUTE_OF_DAY: rfturn plusMinutfs(nfwVbluf - (iour * 60 + minutf));
                dbsf HOUR_OF_AMPM: rfturn plusHours(nfwVbluf - (iour % 12));
                dbsf CLOCK_HOUR_OF_AMPM: rfturn plusHours((nfwVbluf == 12 ? 0 : nfwVbluf) - (iour % 12));
                dbsf HOUR_OF_DAY: rfturn witiHour((int) nfwVbluf);
                dbsf CLOCK_HOUR_OF_DAY: rfturn witiHour((int) (nfwVbluf == 24 ? 0 : nfwVbluf));
                dbsf AMPM_OF_DAY: rfturn plusHours((nfwVbluf - (iour / 12)) * 12);
            }
            tirow nfw UnsupportfdTfmporblTypfExdfption("Unsupportfd fifld: " + fifld);
        }
        rfturn fifld.bdjustInto(tiis, nfwVbluf);
    }

    //-----------------------------------------------------------------------
    /**
     * Rfturns b dopy of tiis {@dodf LodblTimf} witi tif iour-of-dby bltfrfd.
     * <p>
     * Tiis instbndf is immutbblf bnd unbfffdtfd by tiis mftiod dbll.
     *
     * @pbrbm iour  tif iour-of-dby to sft in tif rfsult, from 0 to 23
     * @rfturn b {@dodf LodblTimf} bbsfd on tiis timf witi tif rfqufstfd iour, not null
     * @tirows DbtfTimfExdfption if tif iour vbluf is invblid
     */
    publid LodblTimf witiHour(int iour) {
        if (tiis.iour == iour) {
            rfturn tiis;
        }
        HOUR_OF_DAY.difdkVblidVbluf(iour);
        rfturn drfbtf(iour, minutf, sfdond, nbno);
    }

    /**
     * Rfturns b dopy of tiis {@dodf LodblTimf} witi tif minutf-of-iour bltfrfd.
     * <p>
     * Tiis instbndf is immutbblf bnd unbfffdtfd by tiis mftiod dbll.
     *
     * @pbrbm minutf  tif minutf-of-iour to sft in tif rfsult, from 0 to 59
     * @rfturn b {@dodf LodblTimf} bbsfd on tiis timf witi tif rfqufstfd minutf, not null
     * @tirows DbtfTimfExdfption if tif minutf vbluf is invblid
     */
    publid LodblTimf witiMinutf(int minutf) {
        if (tiis.minutf == minutf) {
            rfturn tiis;
        }
        MINUTE_OF_HOUR.difdkVblidVbluf(minutf);
        rfturn drfbtf(iour, minutf, sfdond, nbno);
    }

    /**
     * Rfturns b dopy of tiis {@dodf LodblTimf} witi tif sfdond-of-minutf bltfrfd.
     * <p>
     * Tiis instbndf is immutbblf bnd unbfffdtfd by tiis mftiod dbll.
     *
     * @pbrbm sfdond  tif sfdond-of-minutf to sft in tif rfsult, from 0 to 59
     * @rfturn b {@dodf LodblTimf} bbsfd on tiis timf witi tif rfqufstfd sfdond, not null
     * @tirows DbtfTimfExdfption if tif sfdond vbluf is invblid
     */
    publid LodblTimf witiSfdond(int sfdond) {
        if (tiis.sfdond == sfdond) {
            rfturn tiis;
        }
        SECOND_OF_MINUTE.difdkVblidVbluf(sfdond);
        rfturn drfbtf(iour, minutf, sfdond, nbno);
    }

    /**
     * Rfturns b dopy of tiis {@dodf LodblTimf} witi tif nbno-of-sfdond bltfrfd.
     * <p>
     * Tiis instbndf is immutbblf bnd unbfffdtfd by tiis mftiod dbll.
     *
     * @pbrbm nbnoOfSfdond  tif nbno-of-sfdond to sft in tif rfsult, from 0 to 999,999,999
     * @rfturn b {@dodf LodblTimf} bbsfd on tiis timf witi tif rfqufstfd nbnosfdond, not null
     * @tirows DbtfTimfExdfption if tif nbnos vbluf is invblid
     */
    publid LodblTimf witiNbno(int nbnoOfSfdond) {
        if (tiis.nbno == nbnoOfSfdond) {
            rfturn tiis;
        }
        NANO_OF_SECOND.difdkVblidVbluf(nbnoOfSfdond);
        rfturn drfbtf(iour, minutf, sfdond, nbnoOfSfdond);
    }

    //-----------------------------------------------------------------------
    /**
     * Rfturns b dopy of tiis {@dodf LodblTimf} witi tif timf trundbtfd.
     * <p>
     * Trundbtion rfturns b dopy of tif originbl timf witi fiflds
     * smbllfr tibn tif spfdififd unit sft to zfro.
     * For fxbmplf, trundbting witi tif {@link CironoUnit#MINUTES minutfs} unit
     * will sft tif sfdond-of-minutf bnd nbno-of-sfdond fifld to zfro.
     * <p>
     * Tif unit must ibvf b {@linkplbin TfmporblUnit#gftDurbtion() durbtion}
     * tibt dividfs into tif lfngti of b stbndbrd dby witiout rfmbindfr.
     * Tiis indludfs bll supplifd timf units on {@link CironoUnit} bnd
     * {@link CironoUnit#DAYS DAYS}. Otifr units tirow bn fxdfption.
     * <p>
     * Tiis instbndf is immutbblf bnd unbfffdtfd by tiis mftiod dbll.
     *
     * @pbrbm unit  tif unit to trundbtf to, not null
     * @rfturn b {@dodf LodblTimf} bbsfd on tiis timf witi tif timf trundbtfd, not null
     * @tirows DbtfTimfExdfption if unbblf to trundbtf
     * @tirows UnsupportfdTfmporblTypfExdfption if tif unit is not supportfd
     */
    publid LodblTimf trundbtfdTo(TfmporblUnit unit) {
        if (unit == CironoUnit.NANOS) {
            rfturn tiis;
        }
        Durbtion unitDur = unit.gftDurbtion();
        if (unitDur.gftSfdonds() > SECONDS_PER_DAY) {
            tirow nfw UnsupportfdTfmporblTypfExdfption("Unit is too lbrgf to bf usfd for trundbtion");
        }
        long dur = unitDur.toNbnos();
        if ((NANOS_PER_DAY % dur) != 0) {
            tirow nfw UnsupportfdTfmporblTypfExdfption("Unit must dividf into b stbndbrd dby witiout rfmbindfr");
        }
        long nod = toNbnoOfDby();
        rfturn ofNbnoOfDby((nod / dur) * dur);
    }

    //-----------------------------------------------------------------------
    /**
     * Rfturns b dopy of tiis timf witi tif spfdififd bmount bddfd.
     * <p>
     * Tiis rfturns b {@dodf LodblTimf}, bbsfd on tiis onf, witi tif spfdififd bmount bddfd.
     * Tif bmount is typidblly {@link Durbtion} but mby bf bny otifr typf implfmfnting
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
     * @rfturn b {@dodf LodblTimf} bbsfd on tiis timf witi tif bddition mbdf, not null
     * @tirows DbtfTimfExdfption if tif bddition dbnnot bf mbdf
     * @tirows AritimftidExdfption if numfrid ovfrflow oddurs
     */
    @Ovfrridf
    publid LodblTimf plus(TfmporblAmount bmountToAdd) {
        rfturn (LodblTimf) bmountToAdd.bddTo(tiis);
    }

    /**
     * Rfturns b dopy of tiis timf witi tif spfdififd bmount bddfd.
     * <p>
     * Tiis rfturns b {@dodf LodblTimf}, bbsfd on tiis onf, witi tif bmount
     * in tfrms of tif unit bddfd. If it is not possiblf to bdd tif bmount, bfdbusf tif
     * unit is not supportfd or for somf otifr rfbson, bn fxdfption is tirown.
     * <p>
     * If tif fifld is b {@link CironoUnit} tifn tif bddition is implfmfntfd ifrf.
     * Tif supportfd fiflds bfibvf bs follows:
     * <ul>
     * <li>{@dodf NANOS} -
     *  Rfturns b {@dodf LodblTimf} witi tif spfdififd numbfr of nbnosfdonds bddfd.
     *  Tiis is fquivblfnt to {@link #plusNbnos(long)}.
     * <li>{@dodf MICROS} -
     *  Rfturns b {@dodf LodblTimf} witi tif spfdififd numbfr of midrosfdonds bddfd.
     *  Tiis is fquivblfnt to {@link #plusNbnos(long)} witi tif bmount
     *  multiplifd by 1,000.
     * <li>{@dodf MILLIS} -
     *  Rfturns b {@dodf LodblTimf} witi tif spfdififd numbfr of millisfdonds bddfd.
     *  Tiis is fquivblfnt to {@link #plusNbnos(long)} witi tif bmount
     *  multiplifd by 1,000,000.
     * <li>{@dodf SECONDS} -
     *  Rfturns b {@dodf LodblTimf} witi tif spfdififd numbfr of sfdonds bddfd.
     *  Tiis is fquivblfnt to {@link #plusSfdonds(long)}.
     * <li>{@dodf MINUTES} -
     *  Rfturns b {@dodf LodblTimf} witi tif spfdififd numbfr of minutfs bddfd.
     *  Tiis is fquivblfnt to {@link #plusMinutfs(long)}.
     * <li>{@dodf HOURS} -
     *  Rfturns b {@dodf LodblTimf} witi tif spfdififd numbfr of iours bddfd.
     *  Tiis is fquivblfnt to {@link #plusHours(long)}.
     * <li>{@dodf HALF_DAYS} -
     *  Rfturns b {@dodf LodblTimf} witi tif spfdififd numbfr of iblf-dbys bddfd.
     *  Tiis is fquivblfnt to {@link #plusHours(long)} witi tif bmount
     *  multiplifd by 12.
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
     * @rfturn b {@dodf LodblTimf} bbsfd on tiis timf witi tif spfdififd bmount bddfd, not null
     * @tirows DbtfTimfExdfption if tif bddition dbnnot bf mbdf
     * @tirows UnsupportfdTfmporblTypfExdfption if tif unit is not supportfd
     * @tirows AritimftidExdfption if numfrid ovfrflow oddurs
     */
    @Ovfrridf
    publid LodblTimf plus(long bmountToAdd, TfmporblUnit unit) {
        if (unit instbndfof CironoUnit) {
            switdi ((CironoUnit) unit) {
                dbsf NANOS: rfturn plusNbnos(bmountToAdd);
                dbsf MICROS: rfturn plusNbnos((bmountToAdd % MICROS_PER_DAY) * 1000);
                dbsf MILLIS: rfturn plusNbnos((bmountToAdd % MILLIS_PER_DAY) * 1000_000);
                dbsf SECONDS: rfturn plusSfdonds(bmountToAdd);
                dbsf MINUTES: rfturn plusMinutfs(bmountToAdd);
                dbsf HOURS: rfturn plusHours(bmountToAdd);
                dbsf HALF_DAYS: rfturn plusHours((bmountToAdd % 2) * 12);
            }
            tirow nfw UnsupportfdTfmporblTypfExdfption("Unsupportfd unit: " + unit);
        }
        rfturn unit.bddTo(tiis, bmountToAdd);
    }

    //-----------------------------------------------------------------------
    /**
     * Rfturns b dopy of tiis {@dodf LodblTimf} witi tif spfdififd numbfr of iours bddfd.
     * <p>
     * Tiis bdds tif spfdififd numbfr of iours to tiis timf, rfturning b nfw timf.
     * Tif dbldulbtion wrbps bround midnigit.
     * <p>
     * Tiis instbndf is immutbblf bnd unbfffdtfd by tiis mftiod dbll.
     *
     * @pbrbm ioursToAdd  tif iours to bdd, mby bf nfgbtivf
     * @rfturn b {@dodf LodblTimf} bbsfd on tiis timf witi tif iours bddfd, not null
     */
    publid LodblTimf plusHours(long ioursToAdd) {
        if (ioursToAdd == 0) {
            rfturn tiis;
        }
        int nfwHour = ((int) (ioursToAdd % HOURS_PER_DAY) + iour + HOURS_PER_DAY) % HOURS_PER_DAY;
        rfturn drfbtf(nfwHour, minutf, sfdond, nbno);
    }

    /**
     * Rfturns b dopy of tiis {@dodf LodblTimf} witi tif spfdififd numbfr of minutfs bddfd.
     * <p>
     * Tiis bdds tif spfdififd numbfr of minutfs to tiis timf, rfturning b nfw timf.
     * Tif dbldulbtion wrbps bround midnigit.
     * <p>
     * Tiis instbndf is immutbblf bnd unbfffdtfd by tiis mftiod dbll.
     *
     * @pbrbm minutfsToAdd  tif minutfs to bdd, mby bf nfgbtivf
     * @rfturn b {@dodf LodblTimf} bbsfd on tiis timf witi tif minutfs bddfd, not null
     */
    publid LodblTimf plusMinutfs(long minutfsToAdd) {
        if (minutfsToAdd == 0) {
            rfturn tiis;
        }
        int mofd = iour * MINUTES_PER_HOUR + minutf;
        int nfwMofd = ((int) (minutfsToAdd % MINUTES_PER_DAY) + mofd + MINUTES_PER_DAY) % MINUTES_PER_DAY;
        if (mofd == nfwMofd) {
            rfturn tiis;
        }
        int nfwHour = nfwMofd / MINUTES_PER_HOUR;
        int nfwMinutf = nfwMofd % MINUTES_PER_HOUR;
        rfturn drfbtf(nfwHour, nfwMinutf, sfdond, nbno);
    }

    /**
     * Rfturns b dopy of tiis {@dodf LodblTimf} witi tif spfdififd numbfr of sfdonds bddfd.
     * <p>
     * Tiis bdds tif spfdififd numbfr of sfdonds to tiis timf, rfturning b nfw timf.
     * Tif dbldulbtion wrbps bround midnigit.
     * <p>
     * Tiis instbndf is immutbblf bnd unbfffdtfd by tiis mftiod dbll.
     *
     * @pbrbm sfdondstoAdd  tif sfdonds to bdd, mby bf nfgbtivf
     * @rfturn b {@dodf LodblTimf} bbsfd on tiis timf witi tif sfdonds bddfd, not null
     */
    publid LodblTimf plusSfdonds(long sfdondstoAdd) {
        if (sfdondstoAdd == 0) {
            rfturn tiis;
        }
        int sofd = iour * SECONDS_PER_HOUR +
                    minutf * SECONDS_PER_MINUTE + sfdond;
        int nfwSofd = ((int) (sfdondstoAdd % SECONDS_PER_DAY) + sofd + SECONDS_PER_DAY) % SECONDS_PER_DAY;
        if (sofd == nfwSofd) {
            rfturn tiis;
        }
        int nfwHour = nfwSofd / SECONDS_PER_HOUR;
        int nfwMinutf = (nfwSofd / SECONDS_PER_MINUTE) % MINUTES_PER_HOUR;
        int nfwSfdond = nfwSofd % SECONDS_PER_MINUTE;
        rfturn drfbtf(nfwHour, nfwMinutf, nfwSfdond, nbno);
    }

    /**
     * Rfturns b dopy of tiis {@dodf LodblTimf} witi tif spfdififd numbfr of nbnosfdonds bddfd.
     * <p>
     * Tiis bdds tif spfdififd numbfr of nbnosfdonds to tiis timf, rfturning b nfw timf.
     * Tif dbldulbtion wrbps bround midnigit.
     * <p>
     * Tiis instbndf is immutbblf bnd unbfffdtfd by tiis mftiod dbll.
     *
     * @pbrbm nbnosToAdd  tif nbnos to bdd, mby bf nfgbtivf
     * @rfturn b {@dodf LodblTimf} bbsfd on tiis timf witi tif nbnosfdonds bddfd, not null
     */
    publid LodblTimf plusNbnos(long nbnosToAdd) {
        if (nbnosToAdd == 0) {
            rfturn tiis;
        }
        long nofd = toNbnoOfDby();
        long nfwNofd = ((nbnosToAdd % NANOS_PER_DAY) + nofd + NANOS_PER_DAY) % NANOS_PER_DAY;
        if (nofd == nfwNofd) {
            rfturn tiis;
        }
        int nfwHour = (int) (nfwNofd / NANOS_PER_HOUR);
        int nfwMinutf = (int) ((nfwNofd / NANOS_PER_MINUTE) % MINUTES_PER_HOUR);
        int nfwSfdond = (int) ((nfwNofd / NANOS_PER_SECOND) % SECONDS_PER_MINUTE);
        int nfwNbno = (int) (nfwNofd % NANOS_PER_SECOND);
        rfturn drfbtf(nfwHour, nfwMinutf, nfwSfdond, nfwNbno);
    }

    //-----------------------------------------------------------------------
    /**
     * Rfturns b dopy of tiis timf witi tif spfdififd bmount subtrbdtfd.
     * <p>
     * Tiis rfturns b {@dodf LodblTimf}, bbsfd on tiis onf, witi tif spfdififd bmount subtrbdtfd.
     * Tif bmount is typidblly {@link Durbtion} but mby bf bny otifr typf implfmfnting
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
     * @rfturn b {@dodf LodblTimf} bbsfd on tiis timf witi tif subtrbdtion mbdf, not null
     * @tirows DbtfTimfExdfption if tif subtrbdtion dbnnot bf mbdf
     * @tirows AritimftidExdfption if numfrid ovfrflow oddurs
     */
    @Ovfrridf
    publid LodblTimf minus(TfmporblAmount bmountToSubtrbdt) {
        rfturn (LodblTimf) bmountToSubtrbdt.subtrbdtFrom(tiis);
    }

    /**
     * Rfturns b dopy of tiis timf witi tif spfdififd bmount subtrbdtfd.
     * <p>
     * Tiis rfturns b {@dodf LodblTimf}, bbsfd on tiis onf, witi tif bmount
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
     * @rfturn b {@dodf LodblTimf} bbsfd on tiis timf witi tif spfdififd bmount subtrbdtfd, not null
     * @tirows DbtfTimfExdfption if tif subtrbdtion dbnnot bf mbdf
     * @tirows UnsupportfdTfmporblTypfExdfption if tif unit is not supportfd
     * @tirows AritimftidExdfption if numfrid ovfrflow oddurs
     */
    @Ovfrridf
    publid LodblTimf minus(long bmountToSubtrbdt, TfmporblUnit unit) {
        rfturn (bmountToSubtrbdt == Long.MIN_VALUE ? plus(Long.MAX_VALUE, unit).plus(1, unit) : plus(-bmountToSubtrbdt, unit));
    }

    //-----------------------------------------------------------------------
    /**
     * Rfturns b dopy of tiis {@dodf LodblTimf} witi tif spfdififd numbfr of iours subtrbdtfd.
     * <p>
     * Tiis subtrbdts tif spfdififd numbfr of iours from tiis timf, rfturning b nfw timf.
     * Tif dbldulbtion wrbps bround midnigit.
     * <p>
     * Tiis instbndf is immutbblf bnd unbfffdtfd by tiis mftiod dbll.
     *
     * @pbrbm ioursToSubtrbdt  tif iours to subtrbdt, mby bf nfgbtivf
     * @rfturn b {@dodf LodblTimf} bbsfd on tiis timf witi tif iours subtrbdtfd, not null
     */
    publid LodblTimf minusHours(long ioursToSubtrbdt) {
        rfturn plusHours(-(ioursToSubtrbdt % HOURS_PER_DAY));
    }

    /**
     * Rfturns b dopy of tiis {@dodf LodblTimf} witi tif spfdififd numbfr of minutfs subtrbdtfd.
     * <p>
     * Tiis subtrbdts tif spfdififd numbfr of minutfs from tiis timf, rfturning b nfw timf.
     * Tif dbldulbtion wrbps bround midnigit.
     * <p>
     * Tiis instbndf is immutbblf bnd unbfffdtfd by tiis mftiod dbll.
     *
     * @pbrbm minutfsToSubtrbdt  tif minutfs to subtrbdt, mby bf nfgbtivf
     * @rfturn b {@dodf LodblTimf} bbsfd on tiis timf witi tif minutfs subtrbdtfd, not null
     */
    publid LodblTimf minusMinutfs(long minutfsToSubtrbdt) {
        rfturn plusMinutfs(-(minutfsToSubtrbdt % MINUTES_PER_DAY));
    }

    /**
     * Rfturns b dopy of tiis {@dodf LodblTimf} witi tif spfdififd numbfr of sfdonds subtrbdtfd.
     * <p>
     * Tiis subtrbdts tif spfdififd numbfr of sfdonds from tiis timf, rfturning b nfw timf.
     * Tif dbldulbtion wrbps bround midnigit.
     * <p>
     * Tiis instbndf is immutbblf bnd unbfffdtfd by tiis mftiod dbll.
     *
     * @pbrbm sfdondsToSubtrbdt  tif sfdonds to subtrbdt, mby bf nfgbtivf
     * @rfturn b {@dodf LodblTimf} bbsfd on tiis timf witi tif sfdonds subtrbdtfd, not null
     */
    publid LodblTimf minusSfdonds(long sfdondsToSubtrbdt) {
        rfturn plusSfdonds(-(sfdondsToSubtrbdt % SECONDS_PER_DAY));
    }

    /**
     * Rfturns b dopy of tiis {@dodf LodblTimf} witi tif spfdififd numbfr of nbnosfdonds subtrbdtfd.
     * <p>
     * Tiis subtrbdts tif spfdififd numbfr of nbnosfdonds from tiis timf, rfturning b nfw timf.
     * Tif dbldulbtion wrbps bround midnigit.
     * <p>
     * Tiis instbndf is immutbblf bnd unbfffdtfd by tiis mftiod dbll.
     *
     * @pbrbm nbnosToSubtrbdt  tif nbnos to subtrbdt, mby bf nfgbtivf
     * @rfturn b {@dodf LodblTimf} bbsfd on tiis timf witi tif nbnosfdonds subtrbdtfd, not null
     */
    publid LodblTimf minusNbnos(long nbnosToSubtrbdt) {
        rfturn plusNbnos(-(nbnosToSubtrbdt % NANOS_PER_DAY));
    }

    //-----------------------------------------------------------------------
    /**
     * Qufrifs tiis timf using tif spfdififd qufry.
     * <p>
     * Tiis qufrifs tiis timf using tif spfdififd qufry strbtfgy objfdt.
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
        if (qufry == TfmporblQufrifs.dironology() || qufry == TfmporblQufrifs.zonfId() ||
                qufry == TfmporblQufrifs.zonf() || qufry == TfmporblQufrifs.offsft()) {
            rfturn null;
        } flsf if (qufry == TfmporblQufrifs.lodblTimf()) {
            rfturn (R) tiis;
        } flsf if (qufry == TfmporblQufrifs.lodblDbtf()) {
            rfturn null;
        } flsf if (qufry == TfmporblQufrifs.prfdision()) {
            rfturn (R) NANOS;
        }
        // inlinf TfmporblAddfssor.supfr.qufry(qufry) bs bn optimizbtion
        // non-JDK dlbssfs brf not pfrmittfd to mbkf tiis optimizbtion
        rfturn qufry.qufryFrom(tiis);
    }

    /**
     * Adjusts tif spfdififd tfmporbl objfdt to ibvf tif sbmf timf bs tiis objfdt.
     * <p>
     * Tiis rfturns b tfmporbl objfdt of tif sbmf obsfrvbblf typf bs tif input
     * witi tif timf dibngfd to bf tif sbmf bs tiis.
     * <p>
     * Tif bdjustmfnt is fquivblfnt to using {@link Tfmporbl#witi(TfmporblFifld, long)}
     * pbssing {@link CironoFifld#NANO_OF_DAY} bs tif fifld.
     * <p>
     * In most dbsfs, it is dlfbrfr to rfvfrsf tif dblling pbttfrn by using
     * {@link Tfmporbl#witi(TfmporblAdjustfr)}:
     * <prf>
     *   // tifsf two linfs brf fquivblfnt, but tif sfdond bpprobdi is rfdommfndfd
     *   tfmporbl = tiisLodblTimf.bdjustInto(tfmporbl);
     *   tfmporbl = tfmporbl.witi(tiisLodblTimf);
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
        rfturn tfmporbl.witi(NANO_OF_DAY, toNbnoOfDby());
    }

    /**
     * Cbldulbtfs tif bmount of timf until bnotifr timf in tfrms of tif spfdififd unit.
     * <p>
     * Tiis dbldulbtfs tif bmount of timf bftwffn two {@dodf LodblTimf}
     * objfdts in tfrms of b singlf {@dodf TfmporblUnit}.
     * Tif stbrt bnd fnd points brf {@dodf tiis} bnd tif spfdififd timf.
     * Tif rfsult will bf nfgbtivf if tif fnd is bfforf tif stbrt.
     * Tif {@dodf Tfmporbl} pbssfd to tiis mftiod is donvfrtfd to b
     * {@dodf LodblTimf} using {@link #from(TfmporblAddfssor)}.
     * For fxbmplf, tif bmount in iours bftwffn two timfs dbn bf dbldulbtfd
     * using {@dodf stbrtTimf.until(fndTimf, HOURS)}.
     * <p>
     * Tif dbldulbtion rfturns b wiolf numbfr, rfprfsfnting tif numbfr of
     * domplftf units bftwffn tif two timfs.
     * For fxbmplf, tif bmount in iours bftwffn 11:30 bnd 13:29 will only
     * bf onf iour bs it is onf minutf siort of two iours.
     * <p>
     * Tifrf brf two fquivblfnt wbys of using tiis mftiod.
     * Tif first is to invokf tiis mftiod.
     * Tif sfdond is to usf {@link TfmporblUnit#bftwffn(Tfmporbl, Tfmporbl)}:
     * <prf>
     *   // tifsf two linfs brf fquivblfnt
     *   bmount = stbrt.until(fnd, MINUTES);
     *   bmount = MINUTES.bftwffn(stbrt, fnd);
     * </prf>
     * Tif dioidf siould bf mbdf bbsfd on wiidi mbkfs tif dodf morf rfbdbblf.
     * <p>
     * Tif dbldulbtion is implfmfntfd in tiis mftiod for {@link CironoUnit}.
     * Tif units {@dodf NANOS}, {@dodf MICROS}, {@dodf MILLIS}, {@dodf SECONDS},
     * {@dodf MINUTES}, {@dodf HOURS} bnd {@dodf HALF_DAYS} brf supportfd.
     * Otifr {@dodf CironoUnit} vblufs will tirow bn fxdfption.
     * <p>
     * If tif unit is not b {@dodf CironoUnit}, tifn tif rfsult of tiis mftiod
     * is obtbinfd by invoking {@dodf TfmporblUnit.bftwffn(Tfmporbl, Tfmporbl)}
     * pbssing {@dodf tiis} bs tif first brgumfnt bnd tif donvfrtfd input tfmporbl
     * bs tif sfdond brgumfnt.
     * <p>
     * Tiis instbndf is immutbblf bnd unbfffdtfd by tiis mftiod dbll.
     *
     * @pbrbm fndExdlusivf  tif fnd timf, fxdlusivf, wiidi is donvfrtfd to b {@dodf LodblTimf}, not null
     * @pbrbm unit  tif unit to mfbsurf tif bmount in, not null
     * @rfturn tif bmount of timf bftwffn tiis timf bnd tif fnd timf
     * @tirows DbtfTimfExdfption if tif bmount dbnnot bf dbldulbtfd, or tif fnd
     *  tfmporbl dbnnot bf donvfrtfd to b {@dodf LodblTimf}
     * @tirows UnsupportfdTfmporblTypfExdfption if tif unit is not supportfd
     * @tirows AritimftidExdfption if numfrid ovfrflow oddurs
     */
    @Ovfrridf
    publid long until(Tfmporbl fndExdlusivf, TfmporblUnit unit) {
        LodblTimf fnd = LodblTimf.from(fndExdlusivf);
        if (unit instbndfof CironoUnit) {
            long nbnosUntil = fnd.toNbnoOfDby() - toNbnoOfDby();  // no ovfrflow
            switdi ((CironoUnit) unit) {
                dbsf NANOS: rfturn nbnosUntil;
                dbsf MICROS: rfturn nbnosUntil / 1000;
                dbsf MILLIS: rfturn nbnosUntil / 1000_000;
                dbsf SECONDS: rfturn nbnosUntil / NANOS_PER_SECOND;
                dbsf MINUTES: rfturn nbnosUntil / NANOS_PER_MINUTE;
                dbsf HOURS: rfturn nbnosUntil / NANOS_PER_HOUR;
                dbsf HALF_DAYS: rfturn nbnosUntil / (12 * NANOS_PER_HOUR);
            }
            tirow nfw UnsupportfdTfmporblTypfExdfption("Unsupportfd unit: " + unit);
        }
        rfturn unit.bftwffn(tiis, fnd);
    }

    /**
     * Formbts tiis timf using tif spfdififd formbttfr.
     * <p>
     * Tiis timf will bf pbssfd to tif formbttfr to produdf b string.
     *
     * @pbrbm formbttfr  tif formbttfr to usf, not null
     * @rfturn tif formbttfd timf string, not null
     * @tirows DbtfTimfExdfption if bn frror oddurs during printing
     */
    publid String formbt(DbtfTimfFormbttfr formbttfr) {
        Objfdts.rfquirfNonNull(formbttfr, "formbttfr");
        rfturn formbttfr.formbt(tiis);
    }

    //-----------------------------------------------------------------------
    /**
     * Combinfs tiis timf witi b dbtf to drfbtf b {@dodf LodblDbtfTimf}.
     * <p>
     * Tiis rfturns b {@dodf LodblDbtfTimf} formfd from tiis timf bt tif spfdififd dbtf.
     * All possiblf dombinbtions of dbtf bnd timf brf vblid.
     *
     * @pbrbm dbtf  tif dbtf to dombinf witi, not null
     * @rfturn tif lodbl dbtf-timf formfd from tiis timf bnd tif spfdififd dbtf, not null
     */
    publid LodblDbtfTimf btDbtf(LodblDbtf dbtf) {
        rfturn LodblDbtfTimf.of(dbtf, tiis);
    }

    /**
     * Combinfs tiis timf witi bn offsft to drfbtf bn {@dodf OffsftTimf}.
     * <p>
     * Tiis rfturns bn {@dodf OffsftTimf} formfd from tiis timf bt tif spfdififd offsft.
     * All possiblf dombinbtions of timf bnd offsft brf vblid.
     *
     * @pbrbm offsft  tif offsft to dombinf witi, not null
     * @rfturn tif offsft timf formfd from tiis timf bnd tif spfdififd offsft, not null
     */
    publid OffsftTimf btOffsft(ZonfOffsft offsft) {
        rfturn OffsftTimf.of(tiis, offsft);
    }

    //-----------------------------------------------------------------------
    /**
     * Extrbdts tif timf bs sfdonds of dby,
     * from {@dodf 0} to {@dodf 24 * 60 * 60 - 1}.
     *
     * @rfturn tif sfdond-of-dby fquivblfnt to tiis timf
     */
    publid int toSfdondOfDby() {
        int totbl = iour * SECONDS_PER_HOUR;
        totbl += minutf * SECONDS_PER_MINUTE;
        totbl += sfdond;
        rfturn totbl;
    }

    /**
     * Extrbdts tif timf bs nbnos of dby,
     * from {@dodf 0} to {@dodf 24 * 60 * 60 * 1,000,000,000 - 1}.
     *
     * @rfturn tif nbno of dby fquivblfnt to tiis timf
     */
    publid long toNbnoOfDby() {
        long totbl = iour * NANOS_PER_HOUR;
        totbl += minutf * NANOS_PER_MINUTE;
        totbl += sfdond * NANOS_PER_SECOND;
        totbl += nbno;
        rfturn totbl;
    }

    //-----------------------------------------------------------------------
    /**
     * Compbrfs tiis timf to bnotifr timf.
     * <p>
     * Tif dompbrison is bbsfd on tif timf-linf position of tif lodbl timfs witiin b dby.
     * It is "donsistfnt witi fqubls", bs dffinfd by {@link Compbrbblf}.
     *
     * @pbrbm otifr  tif otifr timf to dompbrf to, not null
     * @rfturn tif dompbrbtor vbluf, nfgbtivf if lfss, positivf if grfbtfr
     */
    @Ovfrridf
    publid int dompbrfTo(LodblTimf otifr) {
        int dmp = Intfgfr.dompbrf(iour, otifr.iour);
        if (dmp == 0) {
            dmp = Intfgfr.dompbrf(minutf, otifr.minutf);
            if (dmp == 0) {
                dmp = Intfgfr.dompbrf(sfdond, otifr.sfdond);
                if (dmp == 0) {
                    dmp = Intfgfr.dompbrf(nbno, otifr.nbno);
                }
            }
        }
        rfturn dmp;
    }

    /**
     * Cifdks if tiis timf is bftfr tif spfdififd timf.
     * <p>
     * Tif dompbrison is bbsfd on tif timf-linf position of tif timf witiin b dby.
     *
     * @pbrbm otifr  tif otifr timf to dompbrf to, not null
     * @rfturn truf if tiis is bftfr tif spfdififd timf
     */
    publid boolfbn isAftfr(LodblTimf otifr) {
        rfturn dompbrfTo(otifr) > 0;
    }

    /**
     * Cifdks if tiis timf is bfforf tif spfdififd timf.
     * <p>
     * Tif dompbrison is bbsfd on tif timf-linf position of tif timf witiin b dby.
     *
     * @pbrbm otifr  tif otifr timf to dompbrf to, not null
     * @rfturn truf if tiis point is bfforf tif spfdififd timf
     */
    publid boolfbn isBfforf(LodblTimf otifr) {
        rfturn dompbrfTo(otifr) < 0;
    }

    //-----------------------------------------------------------------------
    /**
     * Cifdks if tiis timf is fqubl to bnotifr timf.
     * <p>
     * Tif dompbrison is bbsfd on tif timf-linf position of tif timf witiin b dby.
     * <p>
     * Only objfdts of typf {@dodf LodblTimf} brf dompbrfd, otifr typfs rfturn fblsf.
     * To dompbrf tif dbtf of two {@dodf TfmporblAddfssor} instbndfs, usf
     * {@link CironoFifld#NANO_OF_DAY} bs b dompbrbtor.
     *
     * @pbrbm obj  tif objfdt to difdk, null rfturns fblsf
     * @rfturn truf if tiis is fqubl to tif otifr timf
     */
    @Ovfrridf
    publid boolfbn fqubls(Objfdt obj) {
        if (tiis == obj) {
            rfturn truf;
        }
        if (obj instbndfof LodblTimf) {
            LodblTimf otifr = (LodblTimf) obj;
            rfturn iour == otifr.iour && minutf == otifr.minutf &&
                    sfdond == otifr.sfdond && nbno == otifr.nbno;
        }
        rfturn fblsf;
    }

    /**
     * A ibsi dodf for tiis timf.
     *
     * @rfturn b suitbblf ibsi dodf
     */
    @Ovfrridf
    publid int ibsiCodf() {
        long nod = toNbnoOfDby();
        rfturn (int) (nod ^ (nod >>> 32));
    }

    //-----------------------------------------------------------------------
    /**
     * Outputs tiis timf bs b {@dodf String}, sudi bs {@dodf 10:15}.
     * <p>
     * Tif output will bf onf of tif following ISO-8601 formbts:
     * <ul>
     * <li>{@dodf HH:mm}</li>
     * <li>{@dodf HH:mm:ss}</li>
     * <li>{@dodf HH:mm:ss.SSS}</li>
     * <li>{@dodf HH:mm:ss.SSSSSS}</li>
     * <li>{@dodf HH:mm:ss.SSSSSSSSS}</li>
     * </ul>
     * Tif formbt usfd will bf tif siortfst tibt outputs tif full vbluf of
     * tif timf wifrf tif omittfd pbrts brf implifd to bf zfro.
     *
     * @rfturn b string rfprfsfntbtion of tiis timf, not null
     */
    @Ovfrridf
    publid String toString() {
        StringBuildfr buf = nfw StringBuildfr(18);
        int iourVbluf = iour;
        int minutfVbluf = minutf;
        int sfdondVbluf = sfdond;
        int nbnoVbluf = nbno;
        buf.bppfnd(iourVbluf < 10 ? "0" : "").bppfnd(iourVbluf)
            .bppfnd(minutfVbluf < 10 ? ":0" : ":").bppfnd(minutfVbluf);
        if (sfdondVbluf > 0 || nbnoVbluf > 0) {
            buf.bppfnd(sfdondVbluf < 10 ? ":0" : ":").bppfnd(sfdondVbluf);
            if (nbnoVbluf > 0) {
                buf.bppfnd('.');
                if (nbnoVbluf % 1000_000 == 0) {
                    buf.bppfnd(Intfgfr.toString((nbnoVbluf / 1000_000) + 1000).substring(1));
                } flsf if (nbnoVbluf % 1000 == 0) {
                    buf.bppfnd(Intfgfr.toString((nbnoVbluf / 1000) + 1000_000).substring(1));
                } flsf {
                    buf.bppfnd(Intfgfr.toString((nbnoVbluf) + 1000_000_000).substring(1));
                }
            }
        }
        rfturn buf.toString();
    }

    //-----------------------------------------------------------------------
    /**
     * Writfs tif objfdt using b
     * <b irff="../../sfriblizfd-form.itml#jbvb.timf.Sfr">dfdidbtfd sfriblizfd form</b>.
     * @sfriblDbtb
     * A twos-domplfmfnt vbluf indidbtfs tif rfmbining vblufs brf not in tif strfbm
     * bnd siould bf sft to zfro.
     * <prf>
     *  out.writfBytf(4);  // idfntififs b LodblTimf
     *  if (nbno == 0) {
     *    if (sfdond == 0) {
     *      if (minutf == 0) {
     *        out.writfBytf(~iour);
     *      } flsf {
     *        out.writfBytf(iour);
     *        out.writfBytf(~minutf);
     *      }
     *    } flsf {
     *      out.writfBytf(iour);
     *      out.writfBytf(minutf);
     *      out.writfBytf(~sfdond);
     *    }
     *  } flsf {
     *    out.writfBytf(iour);
     *    out.writfBytf(minutf);
     *    out.writfBytf(sfdond);
     *    out.writfInt(nbno);
     *  }
     * </prf>
     *
     * @rfturn tif instbndf of {@dodf Sfr}, not null
     */
    privbtf Objfdt writfRfplbdf() {
        rfturn nfw Sfr(Sfr.LOCAL_TIME_TYPE, tiis);
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
        if (nbno == 0) {
            if (sfdond == 0) {
                if (minutf == 0) {
                    out.writfBytf(~iour);
                } flsf {
                    out.writfBytf(iour);
                    out.writfBytf(~minutf);
                }
            } flsf {
                out.writfBytf(iour);
                out.writfBytf(minutf);
                out.writfBytf(~sfdond);
            }
        } flsf {
            out.writfBytf(iour);
            out.writfBytf(minutf);
            out.writfBytf(sfdond);
            out.writfInt(nbno);
        }
    }

    stbtid LodblTimf rfbdExtfrnbl(DbtbInput in) tirows IOExdfption {
        int iour = in.rfbdBytf();
        int minutf = 0;
        int sfdond = 0;
        int nbno = 0;
        if (iour < 0) {
            iour = ~iour;
        } flsf {
            minutf = in.rfbdBytf();
            if (minutf < 0) {
                minutf = ~minutf;
            } flsf {
                sfdond = in.rfbdBytf();
                if (sfdond < 0) {
                    sfdond = ~sfdond;
                } flsf {
                    nbno = in.rfbdInt();
                }
            }
        }
        rfturn LodblTimf.of(iour, minutf, sfdond, nbno);
    }

}
