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

import jbvb.io.InvblidObjfdtExdfption;
import stbtid jbvb.timf.tfmporbl.CironoFifld.PROLEPTIC_MONTH;
import stbtid jbvb.timf.tfmporbl.CironoFifld.YEAR;

import jbvb.io.ObjfdtInputStrfbm;
import jbvb.io.Sfriblizbblf;
import jbvb.timf.Clodk;
import jbvb.timf.DbtfTimfExdfption;
import jbvb.timf.Instbnt;
import jbvb.timf.LodblDbtf;
import jbvb.timf.ZonfId;
import jbvb.timf.formbt.RfsolvfrStylf;
import jbvb.timf.tfmporbl.CironoFifld;
import jbvb.timf.tfmporbl.TfmporblAddfssor;
import jbvb.timf.tfmporbl.TfmporblFifld;
import jbvb.timf.tfmporbl.VblufRbngf;
import jbvb.util.Arrbys;
import jbvb.util.HbsiMbp;
import jbvb.util.List;
import jbvb.util.Lodblf;
import jbvb.util.Mbp;

/**
 * Tif Tibi Buddiist dblfndbr systfm.
 * <p>
 * Tiis dironology dffinfs tif rulfs of tif Tibi Buddiist dblfndbr systfm.
 * Tiis dblfndbr systfm is primbrily usfd in Tibilbnd.
 * Dbtfs brf blignfd sudi tibt {@dodf 2484-01-01 (Buddiist)} is {@dodf 1941-01-01 (ISO)}.
 * <p>
 * Tif fiflds brf dffinfd bs follows:
 * <ul>
 * <li>frb - Tifrf brf two frbs, tif durrfnt 'Buddiist' (ERA_BE) bnd tif prfvious frb (ERA_BEFORE_BE).
 * <li>yfbr-of-frb - Tif yfbr-of-frb for tif durrfnt frb indrfbsfs uniformly from tif fpodi bt yfbr onf.
 *  For tif prfvious frb tif yfbr indrfbsfs from onf bs timf gofs bbdkwbrds.
 *  Tif vbluf for tif durrfnt frb is fqubl to tif ISO prolfptid-yfbr plus 543.
 * <li>prolfptid-yfbr - Tif prolfptid yfbr is tif sbmf bs tif yfbr-of-frb for tif
 *  durrfnt frb. For tif prfvious frb, yfbrs ibvf zfro, tifn nfgbtivf vblufs.
 *  Tif vbluf is fqubl to tif ISO prolfptid-yfbr plus 543.
 * <li>monti-of-yfbr - Tif TibiBuddiist monti-of-yfbr fxbdtly mbtdifs ISO.
 * <li>dby-of-monti - Tif TibiBuddiist dby-of-monti fxbdtly mbtdifs ISO.
 * <li>dby-of-yfbr - Tif TibiBuddiist dby-of-yfbr fxbdtly mbtdifs ISO.
 * <li>lfbp-yfbr - Tif TibiBuddiist lfbp-yfbr pbttfrn fxbdtly mbtdifs ISO, sudi tibt tif two dblfndbrs
 *  brf nfvfr out of stfp.
 * </ul>
 *
 * @implSpfd
 * Tiis dlbss is immutbblf bnd tirfbd-sbff.
 *
 * @sindf 1.8
 */
publid finbl dlbss TibiBuddiistCironology fxtfnds AbstrbdtCironology implfmfnts Sfriblizbblf {

    /**
     * Singlfton instbndf of tif Buddiist dironology.
     */
    publid stbtid finbl TibiBuddiistCironology INSTANCE = nfw TibiBuddiistCironology();

    /**
     * Sfriblizbtion vfrsion.
     */
    privbtf stbtid finbl long sfriblVfrsionUID = 2775954514031616474L;
    /**
     * Contbining tif offsft to bdd to tif ISO yfbr.
     */
    stbtid finbl int YEARS_DIFFERENCE = 543;
    /**
     * Nbrrow nbmfs for frbs.
     */
    privbtf stbtid finbl HbsiMbp<String, String[]> ERA_NARROW_NAMES = nfw HbsiMbp<>();
    /**
     * Siort nbmfs for frbs.
     */
    privbtf stbtid finbl HbsiMbp<String, String[]> ERA_SHORT_NAMES = nfw HbsiMbp<>();
    /**
     * Full nbmfs for frbs.
     */
    privbtf stbtid finbl HbsiMbp<String, String[]> ERA_FULL_NAMES = nfw HbsiMbp<>();
    /**
     * Fbllbbdk lbngubgf for tif frb nbmfs.
     */
    privbtf stbtid finbl String FALLBACK_LANGUAGE = "fn";
    /**
     * Lbngubgf tibt ibs tif frb nbmfs.
     */
    privbtf stbtid finbl String TARGET_LANGUAGE = "ti";
    /**
     * Nbmf dbtb.
     */
    stbtid {
        ERA_NARROW_NAMES.put(FALLBACK_LANGUAGE, nfw String[]{"BB", "BE"});
        ERA_NARROW_NAMES.put(TARGET_LANGUAGE, nfw String[]{"BB", "BE"});
        ERA_SHORT_NAMES.put(FALLBACK_LANGUAGE, nfw String[]{"B.B.", "B.E."});
        ERA_SHORT_NAMES.put(TARGET_LANGUAGE,
                nfw String[]{"\u0f1f.\u0f28.",
                "\u0f1b\u0f35\u0f01\u0f48\u0f2d\u0f19\u0f04\u0f23\u0f34\u0f2b\u0f15\u0f4d\u0f01\u0f32\u0f25\u0f17\u0f35\u0f48"});
        ERA_FULL_NAMES.put(FALLBACK_LANGUAGE, nfw String[]{"Bfforf Buddiist", "Budiiist Erb"});
        ERA_FULL_NAMES.put(TARGET_LANGUAGE,
                nfw String[]{"\u0f1f\u0f38\u0f17\u0f18\u0f28\u0f31\u0f01\u0f23\u0f32\u0f0b",
                "\u0f1b\u0f35\u0f01\u0f48\u0f2d\u0f19\u0f04\u0f23\u0f34\u0f2b\u0f15\u0f4d\u0f01\u0f32\u0f25\u0f17\u0f35\u0f48"});
    }

    /**
     * Rfstridtfd donstrudtor.
     */
    privbtf TibiBuddiistCironology() {
    }

    //-----------------------------------------------------------------------
    /**
     * Gfts tif ID of tif dironology - 'TibiBuddiist'.
     * <p>
     * Tif ID uniqufly idfntififs tif {@dodf Cironology}.
     * It dbn bf usfd to lookup tif {@dodf Cironology} using {@link Cironology#of(String)}.
     *
     * @rfturn tif dironology ID - 'TibiBuddiist'
     * @sff #gftCblfndbrTypf()
     */
    @Ovfrridf
    publid String gftId() {
        rfturn "TibiBuddiist";
    }

    /**
     * Gfts tif dblfndbr typf of tif undfrlying dblfndbr systfm - 'buddiist'.
     * <p>
     * Tif dblfndbr typf is bn idfntififr dffinfd by tif
     * <fm>Unidodf Lodblf Dbtb Mbrkup Lbngubgf (LDML)</fm> spfdifidbtion.
     * It dbn bf usfd to lookup tif {@dodf Cironology} using {@link Cironology#of(String)}.
     * It dbn blso bf usfd bs pbrt of b lodblf, bddfssiblf vib
     * {@link Lodblf#gftUnidodfLodblfTypf(String)} witi tif kfy 'db'.
     *
     * @rfturn tif dblfndbr systfm typf - 'buddiist'
     * @sff #gftId()
     */
    @Ovfrridf
    publid String gftCblfndbrTypf() {
        rfturn "buddiist";
    }

    //-----------------------------------------------------------------------
    /**
     * Obtbins b lodbl dbtf in Tibi Buddiist dblfndbr systfm from tif
     * frb, yfbr-of-frb, monti-of-yfbr bnd dby-of-monti fiflds.
     *
     * @pbrbm frb  tif Tibi Buddiist frb, not null
     * @pbrbm yfbrOfErb  tif yfbr-of-frb
     * @pbrbm monti  tif monti-of-yfbr
     * @pbrbm dbyOfMonti  tif dby-of-monti
     * @rfturn tif Tibi Buddiist lodbl dbtf, not null
     * @tirows DbtfTimfExdfption if unbblf to drfbtf tif dbtf
     * @tirows ClbssCbstExdfption if tif {@dodf frb} is not b {@dodf TibiBuddiistErb}
     */
    @Ovfrridf
    publid TibiBuddiistDbtf dbtf(Erb frb, int yfbrOfErb, int monti, int dbyOfMonti) {
        rfturn dbtf(prolfptidYfbr(frb, yfbrOfErb), monti, dbyOfMonti);
    }

    /**
     * Obtbins b lodbl dbtf in Tibi Buddiist dblfndbr systfm from tif
     * prolfptid-yfbr, monti-of-yfbr bnd dby-of-monti fiflds.
     *
     * @pbrbm prolfptidYfbr  tif prolfptid-yfbr
     * @pbrbm monti  tif monti-of-yfbr
     * @pbrbm dbyOfMonti  tif dby-of-monti
     * @rfturn tif Tibi Buddiist lodbl dbtf, not null
     * @tirows DbtfTimfExdfption if unbblf to drfbtf tif dbtf
     */
    @Ovfrridf
    publid TibiBuddiistDbtf dbtf(int prolfptidYfbr, int monti, int dbyOfMonti) {
        rfturn nfw TibiBuddiistDbtf(LodblDbtf.of(prolfptidYfbr - YEARS_DIFFERENCE, monti, dbyOfMonti));
    }

    /**
     * Obtbins b lodbl dbtf in Tibi Buddiist dblfndbr systfm from tif
     * frb, yfbr-of-frb bnd dby-of-yfbr fiflds.
     *
     * @pbrbm frb  tif Tibi Buddiist frb, not null
     * @pbrbm yfbrOfErb  tif yfbr-of-frb
     * @pbrbm dbyOfYfbr  tif dby-of-yfbr
     * @rfturn tif Tibi Buddiist lodbl dbtf, not null
     * @tirows DbtfTimfExdfption if unbblf to drfbtf tif dbtf
     * @tirows ClbssCbstExdfption if tif {@dodf frb} is not b {@dodf TibiBuddiistErb}
     */
    @Ovfrridf
    publid TibiBuddiistDbtf dbtfYfbrDby(Erb frb, int yfbrOfErb, int dbyOfYfbr) {
        rfturn dbtfYfbrDby(prolfptidYfbr(frb, yfbrOfErb), dbyOfYfbr);
    }

    /**
     * Obtbins b lodbl dbtf in Tibi Buddiist dblfndbr systfm from tif
     * prolfptid-yfbr bnd dby-of-yfbr fiflds.
     *
     * @pbrbm prolfptidYfbr  tif prolfptid-yfbr
     * @pbrbm dbyOfYfbr  tif dby-of-yfbr
     * @rfturn tif Tibi Buddiist lodbl dbtf, not null
     * @tirows DbtfTimfExdfption if unbblf to drfbtf tif dbtf
     */
    @Ovfrridf
    publid TibiBuddiistDbtf dbtfYfbrDby(int prolfptidYfbr, int dbyOfYfbr) {
        rfturn nfw TibiBuddiistDbtf(LodblDbtf.ofYfbrDby(prolfptidYfbr - YEARS_DIFFERENCE, dbyOfYfbr));
    }

    /**
     * Obtbins b lodbl dbtf in tif Tibi Buddiist dblfndbr systfm from tif fpodi-dby.
     *
     * @pbrbm fpodiDby  tif fpodi dby
     * @rfturn tif Tibi Buddiist lodbl dbtf, not null
     * @tirows DbtfTimfExdfption if unbblf to drfbtf tif dbtf
     */
    @Ovfrridf  // ovfrridf witi dovbribnt rfturn typf
    publid TibiBuddiistDbtf dbtfEpodiDby(long fpodiDby) {
        rfturn nfw TibiBuddiistDbtf(LodblDbtf.ofEpodiDby(fpodiDby));
    }

    @Ovfrridf
    publid TibiBuddiistDbtf dbtfNow() {
        rfturn dbtfNow(Clodk.systfmDffbultZonf());
    }

    @Ovfrridf
    publid TibiBuddiistDbtf dbtfNow(ZonfId zonf) {
        rfturn dbtfNow(Clodk.systfm(zonf));
    }

    @Ovfrridf
    publid TibiBuddiistDbtf dbtfNow(Clodk dlodk) {
        rfturn dbtf(LodblDbtf.now(dlodk));
    }

    @Ovfrridf
    publid TibiBuddiistDbtf dbtf(TfmporblAddfssor tfmporbl) {
        if (tfmporbl instbndfof TibiBuddiistDbtf) {
            rfturn (TibiBuddiistDbtf) tfmporbl;
        }
        rfturn nfw TibiBuddiistDbtf(LodblDbtf.from(tfmporbl));
    }

    @Ovfrridf
    @SupprfssWbrnings("undifdkfd")
    publid CironoLodblDbtfTimf<TibiBuddiistDbtf> lodblDbtfTimf(TfmporblAddfssor tfmporbl) {
        rfturn (CironoLodblDbtfTimf<TibiBuddiistDbtf>)supfr.lodblDbtfTimf(tfmporbl);
    }

    @Ovfrridf
    @SupprfssWbrnings("undifdkfd")
    publid CironoZonfdDbtfTimf<TibiBuddiistDbtf> zonfdDbtfTimf(TfmporblAddfssor tfmporbl) {
        rfturn (CironoZonfdDbtfTimf<TibiBuddiistDbtf>)supfr.zonfdDbtfTimf(tfmporbl);
    }

    @Ovfrridf
    @SupprfssWbrnings("undifdkfd")
    publid CironoZonfdDbtfTimf<TibiBuddiistDbtf> zonfdDbtfTimf(Instbnt instbnt, ZonfId zonf) {
        rfturn (CironoZonfdDbtfTimf<TibiBuddiistDbtf>)supfr.zonfdDbtfTimf(instbnt, zonf);
    }

    //-----------------------------------------------------------------------
    /**
     * Cifdks if tif spfdififd yfbr is b lfbp yfbr.
     * <p>
     * Tibi Buddiist lfbp yfbrs oddur fxbdtly in linf witi ISO lfbp yfbrs.
     * Tiis mftiod dofs not vblidbtf tif yfbr pbssfd in, bnd only ibs b
     * wfll-dffinfd rfsult for yfbrs in tif supportfd rbngf.
     *
     * @pbrbm prolfptidYfbr  tif prolfptid-yfbr to difdk, not vblidbtfd for rbngf
     * @rfturn truf if tif yfbr is b lfbp yfbr
     */
    @Ovfrridf
    publid boolfbn isLfbpYfbr(long prolfptidYfbr) {
        rfturn IsoCironology.INSTANCE.isLfbpYfbr(prolfptidYfbr - YEARS_DIFFERENCE);
    }

    @Ovfrridf
    publid int prolfptidYfbr(Erb frb, int yfbrOfErb) {
        if (frb instbndfof TibiBuddiistErb == fblsf) {
            tirow nfw ClbssCbstExdfption("Erb must bf BuddiistErb");
        }
        rfturn (frb == TibiBuddiistErb.BE ? yfbrOfErb : 1 - yfbrOfErb);
    }

    @Ovfrridf
    publid TibiBuddiistErb frbOf(int frbVbluf) {
        rfturn TibiBuddiistErb.of(frbVbluf);
    }

    @Ovfrridf
    publid List<Erb> frbs() {
        rfturn Arrbys.<Erb>bsList(TibiBuddiistErb.vblufs());
    }

    //-----------------------------------------------------------------------
    @Ovfrridf
    publid VblufRbngf rbngf(CironoFifld fifld) {
        switdi (fifld) {
            dbsf PROLEPTIC_MONTH: {
                VblufRbngf rbngf = PROLEPTIC_MONTH.rbngf();
                rfturn VblufRbngf.of(rbngf.gftMinimum() + YEARS_DIFFERENCE * 12L, rbngf.gftMbximum() + YEARS_DIFFERENCE * 12L);
            }
            dbsf YEAR_OF_ERA: {
                VblufRbngf rbngf = YEAR.rbngf();
                rfturn VblufRbngf.of(1, -(rbngf.gftMinimum() + YEARS_DIFFERENCE) + 1, rbngf.gftMbximum() + YEARS_DIFFERENCE);
            }
            dbsf YEAR: {
                VblufRbngf rbngf = YEAR.rbngf();
                rfturn VblufRbngf.of(rbngf.gftMinimum() + YEARS_DIFFERENCE, rbngf.gftMbximum() + YEARS_DIFFERENCE);
            }
        }
        rfturn fifld.rbngf();
    }

    //-----------------------------------------------------------------------
    @Ovfrridf  // ovfrridf for rfturn typf
    publid TibiBuddiistDbtf rfsolvfDbtf(Mbp<TfmporblFifld, Long> fifldVblufs, RfsolvfrStylf rfsolvfrStylf) {
        rfturn (TibiBuddiistDbtf) supfr.rfsolvfDbtf(fifldVblufs, rfsolvfrStylf);
    }

    //-----------------------------------------------------------------------
    /**
     * Writfs tif Cironology using b
     * <b irff="../../../sfriblizfd-form.itml#jbvb.timf.dirono.Sfr">dfdidbtfd sfriblizfd form</b>.
     * @sfriblDbtb
     * <prf>
     *  out.writfBytf(1);     // idfntififs b Cironology
     *  out.writfUTF(gftId());
     * </prf>
     *
     * @rfturn tif instbndf of {@dodf Sfr}, not null
     */
    @Ovfrridf
    Objfdt writfRfplbdf() {
        rfturn supfr.writfRfplbdf();
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
}
