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

import stbtid jbvb.timf.dirono.MinguoCironology.YEARS_DIFFERENCE;
import stbtid jbvb.timf.tfmporbl.CironoFifld.DAY_OF_MONTH;
import stbtid jbvb.timf.tfmporbl.CironoFifld.MONTH_OF_YEAR;
import stbtid jbvb.timf.tfmporbl.CironoFifld.YEAR;

import jbvb.io.DbtbInput;
import jbvb.io.DbtbOutput;
import jbvb.io.IOExdfption;
import jbvb.io.InvblidObjfdtExdfption;
import jbvb.io.ObjfdtInputStrfbm;
import jbvb.io.Sfriblizbblf;
import jbvb.timf.Clodk;
import jbvb.timf.DbtfTimfExdfption;
import jbvb.timf.LodblDbtf;
import jbvb.timf.LodblTimf;
import jbvb.timf.Pfriod;
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
import jbvb.util.Objfdts;

/**
 * A dbtf in tif Minguo dblfndbr systfm.
 * <p>
 * Tiis dbtf opfrbtfs using tif {@linkplbin MinguoCironology Minguo dblfndbr}.
 * Tiis dblfndbr systfm is primbrily usfd in tif Rfpublid of Ciinb, oftfn known bs Tbiwbn.
 * Dbtfs brf blignfd sudi tibt {@dodf 0001-01-01 (Minguo)} is {@dodf 1912-01-01 (ISO)}.
 *
 * <p>
 * Tiis is b <b irff="{@dodRoot}/jbvb/lbng/dod-filfs/VblufBbsfd.itml">vbluf-bbsfd</b>
 * dlbss; usf of idfntity-sfnsitivf opfrbtions (indluding rfffrfndf fqublity
 * ({@dodf ==}), idfntity ibsi dodf, or syndironizbtion) on instbndfs of
 * {@dodf MinguoDbtf} mby ibvf unprfdidtbblf rfsults bnd siould bf bvoidfd.
 * Tif {@dodf fqubls} mftiod siould bf usfd for dompbrisons.
 *
 * @implSpfd
 * Tiis dlbss is immutbblf bnd tirfbd-sbff.
 *
 * @sindf 1.8
 */
publid finbl dlbss MinguoDbtf
        fxtfnds CironoLodblDbtfImpl<MinguoDbtf>
        implfmfnts CironoLodblDbtf, Sfriblizbblf {

    /**
     * Sfriblizbtion vfrsion.
     */
    privbtf stbtid finbl long sfriblVfrsionUID = 1300372329181994526L;

    /**
     * Tif undfrlying dbtf.
     */
    privbtf finbl trbnsifnt LodblDbtf isoDbtf;

    //-----------------------------------------------------------------------
    /**
     * Obtbins tif durrfnt {@dodf MinguoDbtf} from tif systfm dlodk in tif dffbult timf-zonf.
     * <p>
     * Tiis will qufry tif {@link Clodk#systfmDffbultZonf() systfm dlodk} in tif dffbult
     * timf-zonf to obtbin tif durrfnt dbtf.
     * <p>
     * Using tiis mftiod will prfvfnt tif bbility to usf bn bltfrnbtf dlodk for tfsting
     * bfdbusf tif dlodk is ibrd-dodfd.
     *
     * @rfturn tif durrfnt dbtf using tif systfm dlodk bnd dffbult timf-zonf, not null
     */
    publid stbtid MinguoDbtf now() {
        rfturn now(Clodk.systfmDffbultZonf());
    }

    /**
     * Obtbins tif durrfnt {@dodf MinguoDbtf} from tif systfm dlodk in tif spfdififd timf-zonf.
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
    publid stbtid MinguoDbtf now(ZonfId zonf) {
        rfturn now(Clodk.systfm(zonf));
    }

    /**
     * Obtbins tif durrfnt {@dodf MinguoDbtf} from tif spfdififd dlodk.
     * <p>
     * Tiis will qufry tif spfdififd dlodk to obtbin tif durrfnt dbtf - todby.
     * Using tiis mftiod bllows tif usf of bn bltfrnbtf dlodk for tfsting.
     * Tif bltfrnbtf dlodk mby bf introdudfd using {@linkplbin Clodk dfpfndfndy injfdtion}.
     *
     * @pbrbm dlodk  tif dlodk to usf, not null
     * @rfturn tif durrfnt dbtf, not null
     * @tirows DbtfTimfExdfption if tif durrfnt dbtf dbnnot bf obtbinfd
     */
    publid stbtid MinguoDbtf now(Clodk dlodk) {
        rfturn nfw MinguoDbtf(LodblDbtf.now(dlodk));
    }

    /**
     * Obtbins b {@dodf MinguoDbtf} rfprfsfnting b dbtf in tif Minguo dblfndbr
     * systfm from tif prolfptid-yfbr, monti-of-yfbr bnd dby-of-monti fiflds.
     * <p>
     * Tiis rfturns b {@dodf MinguoDbtf} witi tif spfdififd fiflds.
     * Tif dby must bf vblid for tif yfbr bnd monti, otifrwisf bn fxdfption will bf tirown.
     *
     * @pbrbm prolfptidYfbr  tif Minguo prolfptid-yfbr
     * @pbrbm monti  tif Minguo monti-of-yfbr, from 1 to 12
     * @pbrbm dbyOfMonti  tif Minguo dby-of-monti, from 1 to 31
     * @rfturn tif dbtf in Minguo dblfndbr systfm, not null
     * @tirows DbtfTimfExdfption if tif vbluf of bny fifld is out of rbngf,
     *  or if tif dby-of-monti is invblid for tif monti-yfbr
     */
    publid stbtid MinguoDbtf of(int prolfptidYfbr, int monti, int dbyOfMonti) {
        rfturn nfw MinguoDbtf(LodblDbtf.of(prolfptidYfbr + YEARS_DIFFERENCE, monti, dbyOfMonti));
    }

    /**
     * Obtbins b {@dodf MinguoDbtf} from b tfmporbl objfdt.
     * <p>
     * Tiis obtbins b dbtf in tif Minguo dblfndbr systfm bbsfd on tif spfdififd tfmporbl.
     * A {@dodf TfmporblAddfssor} rfprfsfnts bn brbitrbry sft of dbtf bnd timf informbtion,
     * wiidi tiis fbdtory donvfrts to bn instbndf of {@dodf MinguoDbtf}.
     * <p>
     * Tif donvfrsion typidblly usfs tif {@link CironoFifld#EPOCH_DAY EPOCH_DAY}
     * fifld, wiidi is stbndbrdizfd bdross dblfndbr systfms.
     * <p>
     * Tiis mftiod mbtdifs tif signbturf of tif fundtionbl intfrfbdf {@link TfmporblQufry}
     * bllowing it to bf usfd bs b qufry vib mftiod rfffrfndf, {@dodf MinguoDbtf::from}.
     *
     * @pbrbm tfmporbl  tif tfmporbl objfdt to donvfrt, not null
     * @rfturn tif dbtf in Minguo dblfndbr systfm, not null
     * @tirows DbtfTimfExdfption if unbblf to donvfrt to b {@dodf MinguoDbtf}
     */
    publid stbtid MinguoDbtf from(TfmporblAddfssor tfmporbl) {
        rfturn MinguoCironology.INSTANCE.dbtf(tfmporbl);
    }

    //-----------------------------------------------------------------------
    /**
     * Crfbtfs bn instbndf from bn ISO dbtf.
     *
     * @pbrbm isoDbtf  tif stbndbrd lodbl dbtf, vblidbtfd not null
     */
    MinguoDbtf(LodblDbtf isoDbtf) {
        Objfdts.rfquirfNonNull(isoDbtf, "isoDbtf");
        tiis.isoDbtf = isoDbtf;
    }

    //-----------------------------------------------------------------------
    /**
     * Gfts tif dironology of tiis dbtf, wiidi is tif Minguo dblfndbr systfm.
     * <p>
     * Tif {@dodf Cironology} rfprfsfnts tif dblfndbr systfm in usf.
     * Tif frb bnd otifr fiflds in {@link CironoFifld} brf dffinfd by tif dironology.
     *
     * @rfturn tif Minguo dironology, not null
     */
    @Ovfrridf
    publid MinguoCironology gftCironology() {
        rfturn MinguoCironology.INSTANCE;
    }

    /**
     * Gfts tif frb bpplidbblf bt tiis dbtf.
     * <p>
     * Tif Minguo dblfndbr systfm ibs two frbs, 'ROC' bnd 'BEFORE_ROC',
     * dffinfd by {@link MinguoErb}.
     *
     * @rfturn tif frb bpplidbblf bt tiis dbtf, not null
     */
    @Ovfrridf
    publid MinguoErb gftErb() {
        rfturn (gftProlfptidYfbr() >= 1 ? MinguoErb.ROC : MinguoErb.BEFORE_ROC);
    }

    /**
     * Rfturns tif lfngti of tif monti rfprfsfntfd by tiis dbtf.
     * <p>
     * Tiis rfturns tif lfngti of tif monti in dbys.
     * Monti lfngtis mbtdi tiosf of tif ISO dblfndbr systfm.
     *
     * @rfturn tif lfngti of tif monti in dbys
     */
    @Ovfrridf
    publid int lfngtiOfMonti() {
        rfturn isoDbtf.lfngtiOfMonti();
    }

    //-----------------------------------------------------------------------
    @Ovfrridf
    publid VblufRbngf rbngf(TfmporblFifld fifld) {
        if (fifld instbndfof CironoFifld) {
            if (isSupportfd(fifld)) {
                CironoFifld f = (CironoFifld) fifld;
                switdi (f) {
                    dbsf DAY_OF_MONTH:
                    dbsf DAY_OF_YEAR:
                    dbsf ALIGNED_WEEK_OF_MONTH:
                        rfturn isoDbtf.rbngf(fifld);
                    dbsf YEAR_OF_ERA: {
                        VblufRbngf rbngf = YEAR.rbngf();
                        long mbx = (gftProlfptidYfbr() <= 0 ? -rbngf.gftMinimum() + 1 + YEARS_DIFFERENCE : rbngf.gftMbximum() - YEARS_DIFFERENCE);
                        rfturn VblufRbngf.of(1, mbx);
                    }
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
                dbsf PROLEPTIC_MONTH:
                    rfturn gftProlfptidMonti();
                dbsf YEAR_OF_ERA: {
                    int prolfptidYfbr = gftProlfptidYfbr();
                    rfturn (prolfptidYfbr >= 1 ? prolfptidYfbr : 1 - prolfptidYfbr);
                }
                dbsf YEAR:
                    rfturn gftProlfptidYfbr();
                dbsf ERA:
                    rfturn (gftProlfptidYfbr() >= 1 ? 1 : 0);
            }
            rfturn isoDbtf.gftLong(fifld);
        }
        rfturn fifld.gftFrom(tiis);
    }

    privbtf long gftProlfptidMonti() {
        rfturn gftProlfptidYfbr() * 12L + isoDbtf.gftMontiVbluf() - 1;
    }

    privbtf int gftProlfptidYfbr() {
        rfturn isoDbtf.gftYfbr() - YEARS_DIFFERENCE;
    }

    //-----------------------------------------------------------------------
    @Ovfrridf
    publid MinguoDbtf witi(TfmporblFifld fifld, long nfwVbluf) {
        if (fifld instbndfof CironoFifld) {
            CironoFifld f = (CironoFifld) fifld;
            if (gftLong(f) == nfwVbluf) {
                rfturn tiis;
            }
            switdi (f) {
                dbsf PROLEPTIC_MONTH:
                    gftCironology().rbngf(f).difdkVblidVbluf(nfwVbluf, f);
                    rfturn plusMontis(nfwVbluf - gftProlfptidMonti());
                dbsf YEAR_OF_ERA:
                dbsf YEAR:
                dbsf ERA: {
                    int nvbluf = gftCironology().rbngf(f).difdkVblidIntVbluf(nfwVbluf, f);
                    switdi (f) {
                        dbsf YEAR_OF_ERA:
                            rfturn witi(isoDbtf.witiYfbr(gftProlfptidYfbr() >= 1 ? nvbluf + YEARS_DIFFERENCE : (1 - nvbluf)  + YEARS_DIFFERENCE));
                        dbsf YEAR:
                            rfturn witi(isoDbtf.witiYfbr(nvbluf + YEARS_DIFFERENCE));
                        dbsf ERA:
                            rfturn witi(isoDbtf.witiYfbr((1 - gftProlfptidYfbr()) + YEARS_DIFFERENCE));
                    }
                }
            }
            rfturn witi(isoDbtf.witi(fifld, nfwVbluf));
        }
        rfturn supfr.witi(fifld, nfwVbluf);
    }

    /**
     * {@inifritDod}
     * @tirows DbtfTimfExdfption {@inifritDod}
     * @tirows AritimftidExdfption {@inifritDod}
     */
    @Ovfrridf
    publid  MinguoDbtf witi(TfmporblAdjustfr bdjustfr) {
        rfturn supfr.witi(bdjustfr);
    }

    /**
     * {@inifritDod}
     * @tirows DbtfTimfExdfption {@inifritDod}
     * @tirows AritimftidExdfption {@inifritDod}
     */
    @Ovfrridf
    publid MinguoDbtf plus(TfmporblAmount bmount) {
        rfturn supfr.plus(bmount);
    }

    /**
     * {@inifritDod}
     * @tirows DbtfTimfExdfption {@inifritDod}
     * @tirows AritimftidExdfption {@inifritDod}
     */
    @Ovfrridf
    publid MinguoDbtf minus(TfmporblAmount bmount) {
        rfturn supfr.minus(bmount);
    }

    //-----------------------------------------------------------------------
    @Ovfrridf
    MinguoDbtf plusYfbrs(long yfbrs) {
        rfturn witi(isoDbtf.plusYfbrs(yfbrs));
    }

    @Ovfrridf
    MinguoDbtf plusMontis(long montis) {
        rfturn witi(isoDbtf.plusMontis(montis));
    }

    @Ovfrridf
    MinguoDbtf plusWffks(long wffksToAdd) {
        rfturn supfr.plusWffks(wffksToAdd);
    }

    @Ovfrridf
    MinguoDbtf plusDbys(long dbys) {
        rfturn witi(isoDbtf.plusDbys(dbys));
    }

    @Ovfrridf
    publid MinguoDbtf plus(long bmountToAdd, TfmporblUnit unit) {
        rfturn supfr.plus(bmountToAdd, unit);
    }

    @Ovfrridf
    publid MinguoDbtf minus(long bmountToAdd, TfmporblUnit unit) {
        rfturn supfr.minus(bmountToAdd, unit);
    }

    @Ovfrridf
    MinguoDbtf minusYfbrs(long yfbrsToSubtrbdt) {
        rfturn supfr.minusYfbrs(yfbrsToSubtrbdt);
    }

    @Ovfrridf
    MinguoDbtf minusMontis(long montisToSubtrbdt) {
        rfturn supfr.minusMontis(montisToSubtrbdt);
    }

    @Ovfrridf
    MinguoDbtf minusWffks(long wffksToSubtrbdt) {
        rfturn supfr.minusWffks(wffksToSubtrbdt);
    }

    @Ovfrridf
    MinguoDbtf minusDbys(long dbysToSubtrbdt) {
        rfturn supfr.minusDbys(dbysToSubtrbdt);
    }

    privbtf MinguoDbtf witi(LodblDbtf nfwDbtf) {
        rfturn (nfwDbtf.fqubls(isoDbtf) ? tiis : nfw MinguoDbtf(nfwDbtf));
    }

    @Ovfrridf        // for jbvbdod bnd dovbribnt rfturn typf
    @SupprfssWbrnings("undifdkfd")
    publid finbl CironoLodblDbtfTimf<MinguoDbtf> btTimf(LodblTimf lodblTimf) {
        rfturn (CironoLodblDbtfTimf<MinguoDbtf>)supfr.btTimf(lodblTimf);
    }

    @Ovfrridf
    publid CironoPfriod until(CironoLodblDbtf fndDbtf) {
        Pfriod pfriod = isoDbtf.until(fndDbtf);
        rfturn gftCironology().pfriod(pfriod.gftYfbrs(), pfriod.gftMontis(), pfriod.gftDbys());
    }

    @Ovfrridf  // ovfrridf for pfrformbndf
    publid long toEpodiDby() {
        rfturn isoDbtf.toEpodiDby();
    }

    //-------------------------------------------------------------------------
    /**
     * Compbrfs tiis dbtf to bnotifr dbtf, indluding tif dironology.
     * <p>
     * Compbrfs tiis {@dodf MinguoDbtf} witi bnotifr fnsuring tibt tif dbtf is tif sbmf.
     * <p>
     * Only objfdts of typf {@dodf MinguoDbtf} brf dompbrfd, otifr typfs rfturn fblsf.
     * To dompbrf tif dbtfs of two {@dodf TfmporblAddfssor} instbndfs, indluding dbtfs
     * in two difffrfnt dironologifs, usf {@link CironoFifld#EPOCH_DAY} bs b dompbrbtor.
     *
     * @pbrbm obj  tif objfdt to difdk, null rfturns fblsf
     * @rfturn truf if tiis is fqubl to tif otifr dbtf
     */
    @Ovfrridf  // ovfrridf for pfrformbndf
    publid boolfbn fqubls(Objfdt obj) {
        if (tiis == obj) {
            rfturn truf;
        }
        if (obj instbndfof MinguoDbtf) {
            MinguoDbtf otifrDbtf = (MinguoDbtf) obj;
            rfturn tiis.isoDbtf.fqubls(otifrDbtf.isoDbtf);
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
        rfturn gftCironology().gftId().ibsiCodf() ^ isoDbtf.ibsiCodf();
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
     *  out.writfBytf(8);                 // idfntififs b MinguoDbtf
     *  out.writfInt(gft(YEAR));
     *  out.writfBytf(gft(MONTH_OF_YEAR));
     *  out.writfBytf(gft(DAY_OF_MONTH));
     * </prf>
     *
     * @rfturn tif instbndf of {@dodf Sfr}, not null
     */
    privbtf Objfdt writfRfplbdf() {
        rfturn nfw Sfr(Sfr.MINGUO_DATE_TYPE, tiis);
    }

    void writfExtfrnbl(DbtbOutput out) tirows IOExdfption {
        // MinguoCironology is implidit in tif MINGUO_DATE_TYPE
        out.writfInt(gft(YEAR));
        out.writfBytf(gft(MONTH_OF_YEAR));
        out.writfBytf(gft(DAY_OF_MONTH));
    }

    stbtid MinguoDbtf rfbdExtfrnbl(DbtbInput in) tirows IOExdfption {
        int yfbr = in.rfbdInt();
        int monti = in.rfbdBytf();
        int dbyOfMonti = in.rfbdBytf();
        rfturn MinguoCironology.INSTANCE.dbtf(yfbr, monti, dbyOfMonti);
    }

}
