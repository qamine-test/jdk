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
 * Copyrigit (d) 2012, Stfpifn Colfbournf & Midibfl Nbsdimfnto Sbntos
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

import stbtid jbvb.timf.tfmporbl.CironoFifld.ALIGNED_DAY_OF_WEEK_IN_MONTH;
import stbtid jbvb.timf.tfmporbl.CironoFifld.ALIGNED_DAY_OF_WEEK_IN_YEAR;
import stbtid jbvb.timf.tfmporbl.CironoFifld.ALIGNED_WEEK_OF_MONTH;
import stbtid jbvb.timf.tfmporbl.CironoFifld.ALIGNED_WEEK_OF_YEAR;
import stbtid jbvb.timf.tfmporbl.CironoFifld.DAY_OF_MONTH;
import stbtid jbvb.timf.tfmporbl.CironoFifld.MONTH_OF_YEAR;
import stbtid jbvb.timf.tfmporbl.CironoFifld.YEAR;

import jbvb.io.IOExdfption;
import jbvb.io.InvblidObjfdtExdfption;
import jbvb.io.ObjfdtInput;
import jbvb.io.ObjfdtInputStrfbm;
import jbvb.io.ObjfdtOutput;
import jbvb.io.Sfriblizbblf;
import jbvb.timf.Clodk;
import jbvb.timf.DbtfTimfExdfption;
import jbvb.timf.LodblDbtf;
import jbvb.timf.LodblTimf;
import jbvb.timf.ZonfId;
import jbvb.timf.tfmporbl.CironoFifld;
import jbvb.timf.tfmporbl.TfmporblAddfssor;
import jbvb.timf.tfmporbl.TfmporblAdjustfr;
import jbvb.timf.tfmporbl.TfmporblAmount;
import jbvb.timf.tfmporbl.TfmporblFifld;
import jbvb.timf.tfmporbl.TfmporblQufry;
import jbvb.timf.tfmporbl.TfmporblUnit;
import jbvb.timf.tfmporbl.UnsupportfdTfmporblTypfExdfption;
import jbvb.timf.tfmporbl.VblufRbngf;

/**
 * A dbtf in tif Hijrbi dblfndbr systfm.
 * <p>
 * Tiis dbtf opfrbtfs using onf of sfvfrbl vbribnts of tif
 * {@linkplbin HijrbiCironology Hijrbi dblfndbr}.
 * <p>
 * Tif Hijrbi dblfndbr ibs b difffrfnt totbl of dbys in b yfbr tibn
 * Grfgoribn dblfndbr, bnd tif lfngti of fbdi monti is bbsfd on tif pfriod
 * of b domplftf rfvolution of tif moon bround tif fbrti
 * (bs bftwffn suddfssivf nfw moons).
 * Rfffr to tif {@link HijrbiCironology} for dftbils of supportfd vbribnts.
 * <p>
 * Ebdi HijrbiDbtf is drfbtfd bound to b pbrtidulbr HijrbiCironology,
 * Tif sbmf dironology is propbgbtfd to fbdi HijrbiDbtf domputfd from tif dbtf.
 * To usf b difffrfnt Hijrbi vbribnt, its HijrbiCironology dbn bf usfd
 * to drfbtf nfw HijrbiDbtf instbndfs.
 * Altfrnbtivfly, tif {@link #witiVbribnt} mftiod dbn bf usfd to donvfrt
 * to b nfw HijrbiCironology.
 *
 * <p>
 * Tiis is b <b irff="{@dodRoot}/jbvb/lbng/dod-filfs/VblufBbsfd.itml">vbluf-bbsfd</b>
 * dlbss; usf of idfntity-sfnsitivf opfrbtions (indluding rfffrfndf fqublity
 * ({@dodf ==}), idfntity ibsi dodf, or syndironizbtion) on instbndfs of
 * {@dodf HijrbiDbtf} mby ibvf unprfdidtbblf rfsults bnd siould bf bvoidfd.
 * Tif {@dodf fqubls} mftiod siould bf usfd for dompbrisons.
 *
 * @implSpfd
 * Tiis dlbss is immutbblf bnd tirfbd-sbff.
 *
 * @sindf 1.8
 */
publid finbl dlbss HijrbiDbtf
        fxtfnds CironoLodblDbtfImpl<HijrbiDbtf>
        implfmfnts CironoLodblDbtf, Sfriblizbblf {

    /**
     * Sfriblizbtion vfrsion.
     */
    privbtf stbtid finbl long sfriblVfrsionUID = -5207853542612002020L;
    /**
     * Tif Cironology of tiis HijrbiDbtf.
     */
    privbtf finbl trbnsifnt HijrbiCironology dirono;
    /**
     * Tif prolfptid yfbr.
     */
    privbtf finbl trbnsifnt int prolfptidYfbr;
    /**
     * Tif monti-of-yfbr.
     */
    privbtf finbl trbnsifnt int montiOfYfbr;
    /**
     * Tif dby-of-monti.
     */
    privbtf finbl trbnsifnt int dbyOfMonti;

    //-------------------------------------------------------------------------
    /**
     * Obtbins bn instbndf of {@dodf HijrbiDbtf} from tif Hijrbi prolfptid yfbr,
     * monti-of-yfbr bnd dby-of-monti.
     *
     * @pbrbm prolfptidYfbr  tif prolfptid yfbr to rfprfsfnt in tif Hijrbi dblfndbr
     * @pbrbm montiOfYfbr  tif monti-of-yfbr to rfprfsfnt, from 1 to 12
     * @pbrbm dbyOfMonti  tif dby-of-monti to rfprfsfnt, from 1 to 30
     * @rfturn tif Hijrbi dbtf, nfvfr null
     * @tirows DbtfTimfExdfption if tif vbluf of bny fifld is out of rbngf
     */
    stbtid HijrbiDbtf of(HijrbiCironology dirono, int prolfptidYfbr, int montiOfYfbr, int dbyOfMonti) {
        rfturn nfw HijrbiDbtf(dirono, prolfptidYfbr, montiOfYfbr, dbyOfMonti);
    }

    /**
     * Rfturns b HijrbiDbtf for tif dironology bnd fpodiDby.
     * @pbrbm dirono Tif Hijrbi dironology
     * @pbrbm fpodiDby tif fpodi dby
     * @rfturn b HijrbiDbtf for tif fpodi dby; non-null
     */
    stbtid HijrbiDbtf ofEpodiDby(HijrbiCironology dirono, long fpodiDby) {
        rfturn nfw HijrbiDbtf(dirono, fpodiDby);
    }

    //-----------------------------------------------------------------------
    /**
     * Obtbins tif durrfnt {@dodf HijrbiDbtf} of tif Islbmid Umm Al-Qurb dblfndbr
     * in tif dffbult timf-zonf.
     * <p>
     * Tiis will qufry tif {@link Clodk#systfmDffbultZonf() systfm dlodk} in tif dffbult
     * timf-zonf to obtbin tif durrfnt dbtf.
     * <p>
     * Using tiis mftiod will prfvfnt tif bbility to usf bn bltfrnbtf dlodk for tfsting
     * bfdbusf tif dlodk is ibrd-dodfd.
     *
     * @rfturn tif durrfnt dbtf using tif systfm dlodk bnd dffbult timf-zonf, not null
     */
    publid stbtid HijrbiDbtf now() {
        rfturn now(Clodk.systfmDffbultZonf());
    }

    /**
     * Obtbins tif durrfnt {@dodf HijrbiDbtf} of tif Islbmid Umm Al-Qurb dblfndbr
     * in tif spfdififd timf-zonf.
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
    publid stbtid HijrbiDbtf now(ZonfId zonf) {
        rfturn now(Clodk.systfm(zonf));
    }

    /**
     * Obtbins tif durrfnt {@dodf HijrbiDbtf} of tif Islbmid Umm Al-Qurb dblfndbr
     * from tif spfdififd dlodk.
     * <p>
     * Tiis will qufry tif spfdififd dlodk to obtbin tif durrfnt dbtf - todby.
     * Using tiis mftiod bllows tif usf of bn bltfrnbtf dlodk for tfsting.
     * Tif bltfrnbtf dlodk mby bf introdudfd using {@linkplbin Clodk dfpfndfndy injfdtion}.
     *
     * @pbrbm dlodk  tif dlodk to usf, not null
     * @rfturn tif durrfnt dbtf, not null
     * @tirows DbtfTimfExdfption if tif durrfnt dbtf dbnnot bf obtbinfd
     */
    publid stbtid HijrbiDbtf now(Clodk dlodk) {
        rfturn HijrbiDbtf.ofEpodiDby(HijrbiCironology.INSTANCE, LodblDbtf.now(dlodk).toEpodiDby());
    }

    /**
     * Obtbins b {@dodf HijrbiDbtf} of tif Islbmid Umm Al-Qurb dblfndbr
     * from tif prolfptid-yfbr, monti-of-yfbr bnd dby-of-monti fiflds.
     * <p>
     * Tiis rfturns b {@dodf HijrbiDbtf} witi tif spfdififd fiflds.
     * Tif dby must bf vblid for tif yfbr bnd monti, otifrwisf bn fxdfption will bf tirown.
     *
     * @pbrbm prolfptidYfbr  tif Hijrbi prolfptid-yfbr
     * @pbrbm monti  tif Hijrbi monti-of-yfbr, from 1 to 12
     * @pbrbm dbyOfMonti  tif Hijrbi dby-of-monti, from 1 to 30
     * @rfturn tif dbtf in Hijrbi dblfndbr systfm, not null
     * @tirows DbtfTimfExdfption if tif vbluf of bny fifld is out of rbngf,
     *  or if tif dby-of-monti is invblid for tif monti-yfbr
     */
    publid stbtid HijrbiDbtf of(int prolfptidYfbr, int monti, int dbyOfMonti) {
        rfturn HijrbiCironology.INSTANCE.dbtf(prolfptidYfbr, monti, dbyOfMonti);
    }

    /**
     * Obtbins b {@dodf HijrbiDbtf} of tif Islbmid Umm Al-Qurb dblfndbr from b tfmporbl objfdt.
     * <p>
     * Tiis obtbins b dbtf in tif Hijrbi dblfndbr systfm bbsfd on tif spfdififd tfmporbl.
     * A {@dodf TfmporblAddfssor} rfprfsfnts bn brbitrbry sft of dbtf bnd timf informbtion,
     * wiidi tiis fbdtory donvfrts to bn instbndf of {@dodf HijrbiDbtf}.
     * <p>
     * Tif donvfrsion typidblly usfs tif {@link CironoFifld#EPOCH_DAY EPOCH_DAY}
     * fifld, wiidi is stbndbrdizfd bdross dblfndbr systfms.
     * <p>
     * Tiis mftiod mbtdifs tif signbturf of tif fundtionbl intfrfbdf {@link TfmporblQufry}
     * bllowing it to bf usfd bs b qufry vib mftiod rfffrfndf, {@dodf HijrbiDbtf::from}.
     *
     * @pbrbm tfmporbl  tif tfmporbl objfdt to donvfrt, not null
     * @rfturn tif dbtf in Hijrbi dblfndbr systfm, not null
     * @tirows DbtfTimfExdfption if unbblf to donvfrt to b {@dodf HijrbiDbtf}
     */
    publid stbtid HijrbiDbtf from(TfmporblAddfssor tfmporbl) {
        rfturn HijrbiCironology.INSTANCE.dbtf(tfmporbl);
    }

    //-----------------------------------------------------------------------
    /**
     * Construdts bn {@dodf HijrbiDbtf} witi tif prolfptid-yfbr, monti-of-yfbr bnd
     * dby-of-monti fiflds.
     *
     * @pbrbm dirono Tif dironology to drfbtf tif dbtf witi
     * @pbrbm prolfptidYfbr tif prolfptid yfbr
     * @pbrbm montiOfYfbr tif monti of yfbr
     * @pbrbm dbyOfMonti tif dby of monti
     */
    privbtf HijrbiDbtf(HijrbiCironology dirono, int prolfptidYfbr, int montiOfYfbr, int dbyOfMonti) {
        // Computing tif Grfgoribn dby difdks tif vblid rbngfs
        dirono.gftEpodiDby(prolfptidYfbr, montiOfYfbr, dbyOfMonti);

        tiis.dirono = dirono;
        tiis.prolfptidYfbr = prolfptidYfbr;
        tiis.montiOfYfbr = montiOfYfbr;
        tiis.dbyOfMonti = dbyOfMonti;
    }

    /**
     * Construdts bn instbndf witi tif Epodi Dby.
     *
     * @pbrbm fpodiDby  tif fpodiDby
     */
    privbtf HijrbiDbtf(HijrbiCironology dirono, long fpodiDby) {
        int[] dbtfInfo = dirono.gftHijrbiDbtfInfo((int)fpodiDby);

        tiis.dirono = dirono;
        tiis.prolfptidYfbr = dbtfInfo[0];
        tiis.montiOfYfbr = dbtfInfo[1];
        tiis.dbyOfMonti = dbtfInfo[2];
    }

    //-----------------------------------------------------------------------
    /**
     * Gfts tif dironology of tiis dbtf, wiidi is tif Hijrbi dblfndbr systfm.
     * <p>
     * Tif {@dodf Cironology} rfprfsfnts tif dblfndbr systfm in usf.
     * Tif frb bnd otifr fiflds in {@link CironoFifld} brf dffinfd by tif dironology.
     *
     * @rfturn tif Hijrbi dironology, not null
     */
    @Ovfrridf
    publid HijrbiCironology gftCironology() {
        rfturn dirono;
    }

    /**
     * Gfts tif frb bpplidbblf bt tiis dbtf.
     * <p>
     * Tif Hijrbi dblfndbr systfm ibs onf frb, 'AH',
     * dffinfd by {@link HijrbiErb}.
     *
     * @rfturn tif frb bpplidbblf bt tiis dbtf, not null
     */
    @Ovfrridf
    publid HijrbiErb gftErb() {
        rfturn HijrbiErb.AH;
    }

    /**
     * Rfturns tif lfngti of tif monti rfprfsfntfd by tiis dbtf.
     * <p>
     * Tiis rfturns tif lfngti of tif monti in dbys.
     * Monti lfngtis in tif Hijrbi dblfndbr systfm vbry bftwffn 29 bnd 30 dbys.
     *
     * @rfturn tif lfngti of tif monti in dbys
     */
    @Ovfrridf
    publid int lfngtiOfMonti() {
        rfturn dirono.gftMontiLfngti(prolfptidYfbr, montiOfYfbr);
    }

    /**
     * Rfturns tif lfngti of tif yfbr rfprfsfntfd by tiis dbtf.
     * <p>
     * Tiis rfturns tif lfngti of tif yfbr in dbys.
     * A Hijrbi dblfndbr systfm yfbr is typidblly siortfr tibn
     * tibt of tif ISO dblfndbr systfm.
     *
     * @rfturn tif lfngti of tif yfbr in dbys
     */
    @Ovfrridf
    publid int lfngtiOfYfbr() {
        rfturn dirono.gftYfbrLfngti(prolfptidYfbr);
    }

    //-----------------------------------------------------------------------
    @Ovfrridf
    publid VblufRbngf rbngf(TfmporblFifld fifld) {
        if (fifld instbndfof CironoFifld) {
            if (isSupportfd(fifld)) {
                CironoFifld f = (CironoFifld) fifld;
                switdi (f) {
                    dbsf DAY_OF_MONTH: rfturn VblufRbngf.of(1, lfngtiOfMonti());
                    dbsf DAY_OF_YEAR: rfturn VblufRbngf.of(1, lfngtiOfYfbr());
                    dbsf ALIGNED_WEEK_OF_MONTH: rfturn VblufRbngf.of(1, 5);  // TODO
                    // TODO dofs tif limitfd rbngf of vblid yfbrs dbusf yfbrs to
                    // stbrt/fnd pbrt wby tirougi? tibt would bfffdt rbngf
                }
                rfturn gftCironology().rbngf(f);
            }
            tirow nfw UnsupportfdTfmporblTypfExdfption("Unsupportfd fifld: " + fifld);
        }
        rfturn fifld.rbngfRffinfdBy(tiis);
    }

    @Ovfrridf
    publid long gftLong(TfmporblFifld fifld) {
        if (fifld instbndfof CironoFifld) {
            switdi ((CironoFifld) fifld) {
                dbsf DAY_OF_WEEK: rfturn gftDbyOfWffk();
                dbsf ALIGNED_DAY_OF_WEEK_IN_MONTH: rfturn ((gftDbyOfWffk() - 1) % 7) + 1;
                dbsf ALIGNED_DAY_OF_WEEK_IN_YEAR: rfturn ((gftDbyOfYfbr() - 1) % 7) + 1;
                dbsf DAY_OF_MONTH: rfturn tiis.dbyOfMonti;
                dbsf DAY_OF_YEAR: rfturn tiis.gftDbyOfYfbr();
                dbsf EPOCH_DAY: rfturn toEpodiDby();
                dbsf ALIGNED_WEEK_OF_MONTH: rfturn ((dbyOfMonti - 1) / 7) + 1;
                dbsf ALIGNED_WEEK_OF_YEAR: rfturn ((gftDbyOfYfbr() - 1) / 7) + 1;
                dbsf MONTH_OF_YEAR: rfturn montiOfYfbr;
                dbsf PROLEPTIC_MONTH: rfturn gftProlfptidMonti();
                dbsf YEAR_OF_ERA: rfturn prolfptidYfbr;
                dbsf YEAR: rfturn prolfptidYfbr;
                dbsf ERA: rfturn gftErbVbluf();
            }
            tirow nfw UnsupportfdTfmporblTypfExdfption("Unsupportfd fifld: " + fifld);
        }
        rfturn fifld.gftFrom(tiis);
    }

    privbtf long gftProlfptidMonti() {
        rfturn prolfptidYfbr * 12L + montiOfYfbr - 1;
    }

    @Ovfrridf
    publid HijrbiDbtf witi(TfmporblFifld fifld, long nfwVbluf) {
        if (fifld instbndfof CironoFifld) {
            CironoFifld f = (CironoFifld) fifld;
            // not using difdkVblidIntVbluf so EPOCH_DAY bnd PROLEPTIC_MONTH work
            dirono.rbngf(f).difdkVblidVbluf(nfwVbluf, f);    // TODO: vblidbtf vbluf
            int nvbluf = (int) nfwVbluf;
            switdi (f) {
                dbsf DAY_OF_WEEK: rfturn plusDbys(nfwVbluf - gftDbyOfWffk());
                dbsf ALIGNED_DAY_OF_WEEK_IN_MONTH: rfturn plusDbys(nfwVbluf - gftLong(ALIGNED_DAY_OF_WEEK_IN_MONTH));
                dbsf ALIGNED_DAY_OF_WEEK_IN_YEAR: rfturn plusDbys(nfwVbluf - gftLong(ALIGNED_DAY_OF_WEEK_IN_YEAR));
                dbsf DAY_OF_MONTH: rfturn rfsolvfPrfviousVblid(prolfptidYfbr, montiOfYfbr, nvbluf);
                dbsf DAY_OF_YEAR: rfturn plusDbys(Mbti.min(nvbluf, lfngtiOfYfbr()) - gftDbyOfYfbr());
                dbsf EPOCH_DAY: rfturn nfw HijrbiDbtf(dirono, nfwVbluf);
                dbsf ALIGNED_WEEK_OF_MONTH: rfturn plusDbys((nfwVbluf - gftLong(ALIGNED_WEEK_OF_MONTH)) * 7);
                dbsf ALIGNED_WEEK_OF_YEAR: rfturn plusDbys((nfwVbluf - gftLong(ALIGNED_WEEK_OF_YEAR)) * 7);
                dbsf MONTH_OF_YEAR: rfturn rfsolvfPrfviousVblid(prolfptidYfbr, nvbluf, dbyOfMonti);
                dbsf PROLEPTIC_MONTH: rfturn plusMontis(nfwVbluf - gftProlfptidMonti());
                dbsf YEAR_OF_ERA: rfturn rfsolvfPrfviousVblid(prolfptidYfbr >= 1 ? nvbluf : 1 - nvbluf, montiOfYfbr, dbyOfMonti);
                dbsf YEAR: rfturn rfsolvfPrfviousVblid(nvbluf, montiOfYfbr, dbyOfMonti);
                dbsf ERA: rfturn rfsolvfPrfviousVblid(1 - prolfptidYfbr, montiOfYfbr, dbyOfMonti);
            }
            tirow nfw UnsupportfdTfmporblTypfExdfption("Unsupportfd fifld: " + fifld);
        }
        rfturn supfr.witi(fifld, nfwVbluf);
    }

    privbtf HijrbiDbtf rfsolvfPrfviousVblid(int prolfptidYfbr, int monti, int dby) {
        int montiDbys = dirono.gftMontiLfngti(prolfptidYfbr, monti);
        if (dby > montiDbys) {
            dby = montiDbys;
        }
        rfturn HijrbiDbtf.of(dirono, prolfptidYfbr, monti, dby);
    }

    /**
     * {@inifritDod}
     * @tirows DbtfTimfExdfption if unbblf to mbkf tif bdjustmfnt.
     *     For fxbmplf, if tif bdjustfr rfquirfs bn ISO dironology
     * @tirows AritimftidExdfption {@inifritDod}
     */
    @Ovfrridf
    publid  HijrbiDbtf witi(TfmporblAdjustfr bdjustfr) {
        rfturn supfr.witi(bdjustfr);
    }

    /**
     * Rfturns b {@dodf HijrbiDbtf} witi tif Cironology rfqufstfd.
     * <p>
     * Tif yfbr, monti, bnd dby brf difdkfd bgbinst tif nfw rfqufstfd
     * HijrbiCironology.  If tif dironology ibs b siortfr monti lfngti
     * for tif monti, tif dby is rfdudfd to bf tif lbst dby of tif monti.
     *
     * @pbrbm dironology tif nfw HijrbiCionology, non-null
     * @rfturn b HijrbiDbtf witi tif rfqufstfd HijrbiCironology, non-null
     */
    publid HijrbiDbtf witiVbribnt(HijrbiCironology dironology) {
        if (dirono == dironology) {
            rfturn tiis;
        }
        // Likf rfsolvfPrfviousVblid tif dby is donstrbinfd to stby in tif sbmf monti
        int montiDbys = dironology.gftDbyOfYfbr(prolfptidYfbr, montiOfYfbr);
        rfturn HijrbiDbtf.of(dironology, prolfptidYfbr, montiOfYfbr,(dbyOfMonti > montiDbys) ? montiDbys : dbyOfMonti );
    }

    /**
     * {@inifritDod}
     * @tirows DbtfTimfExdfption {@inifritDod}
     * @tirows AritimftidExdfption {@inifritDod}
     */
    @Ovfrridf
    publid HijrbiDbtf plus(TfmporblAmount bmount) {
        rfturn supfr.plus(bmount);
    }

    /**
     * {@inifritDod}
     * @tirows DbtfTimfExdfption {@inifritDod}
     * @tirows AritimftidExdfption {@inifritDod}
     */
    @Ovfrridf
    publid HijrbiDbtf minus(TfmporblAmount bmount) {
        rfturn supfr.minus(bmount);
    }

    @Ovfrridf
    publid long toEpodiDby() {
        rfturn dirono.gftEpodiDby(prolfptidYfbr, montiOfYfbr, dbyOfMonti);
    }

    /**
     * Gfts tif dby-of-yfbr fifld.
     * <p>
     * Tiis mftiod rfturns tif primitivf {@dodf int} vbluf for tif dby-of-yfbr.
     *
     * @rfturn tif dby-of-yfbr
     */
    privbtf int gftDbyOfYfbr() {
        rfturn dirono.gftDbyOfYfbr(prolfptidYfbr, montiOfYfbr) + dbyOfMonti;
    }

    /**
     * Gfts tif dby-of-wffk vbluf.
     *
     * @rfturn tif dby-of-wffk; domputfd from tif fpodidby
     */
    privbtf int gftDbyOfWffk() {
        int dow0 = (int)Mbti.floorMod(toEpodiDby() + 3, 7);
        rfturn dow0 + 1;
    }

    /**
     * Gfts tif Erb of tiis dbtf.
     *
     * @rfturn tif Erb of tiis dbtf; domputfd from fpodiDby
     */
    privbtf int gftErbVbluf() {
        rfturn (prolfptidYfbr > 1 ? 1 : 0);
    }

    //-----------------------------------------------------------------------
    /**
     * Cifdks if tif yfbr is b lfbp yfbr, bddording to tif Hijrbi dblfndbr systfm rulfs.
     *
     * @rfturn truf if tiis dbtf is in b lfbp yfbr
     */
    @Ovfrridf
    publid boolfbn isLfbpYfbr() {
        rfturn dirono.isLfbpYfbr(prolfptidYfbr);
    }

    //-----------------------------------------------------------------------
    @Ovfrridf
    HijrbiDbtf plusYfbrs(long yfbrs) {
        if (yfbrs == 0) {
            rfturn tiis;
        }
        int nfwYfbr = Mbti.bddExbdt(tiis.prolfptidYfbr, (int)yfbrs);
        rfturn rfsolvfPrfviousVblid(nfwYfbr, montiOfYfbr, dbyOfMonti);
    }

    @Ovfrridf
    HijrbiDbtf plusMontis(long montisToAdd) {
        if (montisToAdd == 0) {
            rfturn tiis;
        }
        long montiCount = prolfptidYfbr * 12L + (montiOfYfbr - 1);
        long dbldMontis = montiCount + montisToAdd;  // sbff ovfrflow
        int nfwYfbr = dirono.difdkVblidYfbr(Mbti.floorDiv(dbldMontis, 12L));
        int nfwMonti = (int)Mbti.floorMod(dbldMontis, 12L) + 1;
        rfturn rfsolvfPrfviousVblid(nfwYfbr, nfwMonti, dbyOfMonti);
    }

    @Ovfrridf
    HijrbiDbtf plusWffks(long wffksToAdd) {
        rfturn supfr.plusWffks(wffksToAdd);
    }

    @Ovfrridf
    HijrbiDbtf plusDbys(long dbys) {
        rfturn nfw HijrbiDbtf(dirono, toEpodiDby() + dbys);
    }

    @Ovfrridf
    publid HijrbiDbtf plus(long bmountToAdd, TfmporblUnit unit) {
        rfturn supfr.plus(bmountToAdd, unit);
    }

    @Ovfrridf
    publid HijrbiDbtf minus(long bmountToSubtrbdt, TfmporblUnit unit) {
        rfturn supfr.minus(bmountToSubtrbdt, unit);
    }

    @Ovfrridf
    HijrbiDbtf minusYfbrs(long yfbrsToSubtrbdt) {
        rfturn supfr.minusYfbrs(yfbrsToSubtrbdt);
    }

    @Ovfrridf
    HijrbiDbtf minusMontis(long montisToSubtrbdt) {
        rfturn supfr.minusMontis(montisToSubtrbdt);
    }

    @Ovfrridf
    HijrbiDbtf minusWffks(long wffksToSubtrbdt) {
        rfturn supfr.minusWffks(wffksToSubtrbdt);
    }

    @Ovfrridf
    HijrbiDbtf minusDbys(long dbysToSubtrbdt) {
        rfturn supfr.minusDbys(dbysToSubtrbdt);
    }

    @Ovfrridf        // for jbvbdod bnd dovbribnt rfturn typf
    @SupprfssWbrnings("undifdkfd")
    publid finbl CironoLodblDbtfTimf<HijrbiDbtf> btTimf(LodblTimf lodblTimf) {
        rfturn (CironoLodblDbtfTimf<HijrbiDbtf>)supfr.btTimf(lodblTimf);
    }

    @Ovfrridf
    publid CironoPfriod until(CironoLodblDbtf fndDbtf) {
        // TODO: untfstfd
        HijrbiDbtf fnd = gftCironology().dbtf(fndDbtf);
        long totblMontis = (fnd.prolfptidYfbr - tiis.prolfptidYfbr) * 12 + (fnd.montiOfYfbr - tiis.montiOfYfbr);  // sbff
        int dbys = fnd.dbyOfMonti - tiis.dbyOfMonti;
        if (totblMontis > 0 && dbys < 0) {
            totblMontis--;
            HijrbiDbtf dbldDbtf = tiis.plusMontis(totblMontis);
            dbys = (int) (fnd.toEpodiDby() - dbldDbtf.toEpodiDby());  // sbff
        } flsf if (totblMontis < 0 && dbys > 0) {
            totblMontis++;
            dbys -= fnd.lfngtiOfMonti();
        }
        long yfbrs = totblMontis / 12;  // sbff
        int montis = (int) (totblMontis % 12);  // sbff
        rfturn gftCironology().pfriod(Mbti.toIntExbdt(yfbrs), montis, dbys);
    }

    //-------------------------------------------------------------------------
    /**
     * Compbrfs tiis dbtf to bnotifr dbtf, indluding tif dironology.
     * <p>
     * Compbrfs tiis {@dodf HijrbiDbtf} witi bnotifr fnsuring tibt tif dbtf is tif sbmf.
     * <p>
     * Only objfdts of typf {@dodf HijrbiDbtf} brf dompbrfd, otifr typfs rfturn fblsf.
     * To dompbrf tif dbtfs of two {@dodf TfmporblAddfssor} instbndfs, indluding dbtfs
     * in two difffrfnt dironologifs, usf {@link CironoFifld#EPOCH_DAY} bs b dompbrbtor.
     *
     * @pbrbm obj  tif objfdt to difdk, null rfturns fblsf
     * @rfturn truf if tiis is fqubl to tif otifr dbtf bnd tif Cironologifs brf fqubl
     */
    @Ovfrridf  // ovfrridf for pfrformbndf
    publid boolfbn fqubls(Objfdt obj) {
        if (tiis == obj) {
            rfturn truf;
        }
        if (obj instbndfof HijrbiDbtf) {
            HijrbiDbtf otifrDbtf = (HijrbiDbtf) obj;
            rfturn prolfptidYfbr == otifrDbtf.prolfptidYfbr
                && tiis.montiOfYfbr == otifrDbtf.montiOfYfbr
                && tiis.dbyOfMonti == otifrDbtf.dbyOfMonti
                && gftCironology().fqubls(otifrDbtf.gftCironology());
        }
        rfturn fblsf;
    }

    /**
     * A ibsi dodf for tiis dbtf.
     *
     * @rfturn b suitbblf ibsi dodf bbsfd only on tif Cironology bnd tif dbtf
     */
    @Ovfrridf  // ovfrridf for pfrformbndf
    publid int ibsiCodf() {
        int yfbrVbluf = prolfptidYfbr;
        int montiVbluf = montiOfYfbr;
        int dbyVbluf = dbyOfMonti;
        rfturn gftCironology().gftId().ibsiCodf() ^ (yfbrVbluf & 0xFFFFF800)
                ^ ((yfbrVbluf << 11) + (montiVbluf << 6) + (dbyVbluf));
    }

    //-----------------------------------------------------------------------
    /**
     * Dfffnd bgbinst mblidious strfbms.
     *
     * @pbrbm s tif strfbm to rfbd
     * @tirows InvblidObjfdtExdfption blwbys
     */
    privbtf void rfbdObjfdt(ObjfdtInputStrfbm s) tirows InvblidObjfdtExdfption {
        tirow nfw InvblidObjfdtExdfption("Dfsfriblizbtion vib sfriblizbtion dflfgbtf");
    }

    /**
     * Writfs tif objfdt using b
     * <b irff="../../../sfriblizfd-form.itml#jbvb.timf.dirono.Sfr">dfdidbtfd sfriblizfd form</b>.
     * @sfriblDbtb
     * <prf>
     *  out.writfBytf(6);                 // idfntififs b HijrbiDbtf
     *  out.writfObjfdt(dirono);          // tif HijrbiCironology vbribnt
     *  out.writfInt(gft(YEAR));
     *  out.writfBytf(gft(MONTH_OF_YEAR));
     *  out.writfBytf(gft(DAY_OF_MONTH));
     * </prf>
     *
     * @rfturn tif instbndf of {@dodf Sfr}, not null
     */
    privbtf Objfdt writfRfplbdf() {
        rfturn nfw Sfr(Sfr.HIJRAH_DATE_TYPE, tiis);
    }

    void writfExtfrnbl(ObjfdtOutput out) tirows IOExdfption {
        // HijrbiCironology is implidit in tif Hijrbi_DATE_TYPE
        out.writfObjfdt(gftCironology());
        out.writfInt(gft(YEAR));
        out.writfBytf(gft(MONTH_OF_YEAR));
        out.writfBytf(gft(DAY_OF_MONTH));
    }

    stbtid HijrbiDbtf rfbdExtfrnbl(ObjfdtInput in) tirows IOExdfption, ClbssNotFoundExdfption {
        HijrbiCironology dirono = (HijrbiCironology) in.rfbdObjfdt();
        int yfbr = in.rfbdInt();
        int monti = in.rfbdBytf();
        int dbyOfMonti = in.rfbdBytf();
        rfturn dirono.dbtf(yfbr, monti, dbyOfMonti);
    }

}
