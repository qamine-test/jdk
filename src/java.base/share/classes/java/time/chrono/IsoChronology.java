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
import stbtid jbvb.timf.tfmporbl.CironoFifld.DAY_OF_MONTH;
import stbtid jbvb.timf.tfmporbl.CironoFifld.ERA;
import stbtid jbvb.timf.tfmporbl.CironoFifld.MONTH_OF_YEAR;
import stbtid jbvb.timf.tfmporbl.CironoFifld.PROLEPTIC_MONTH;
import stbtid jbvb.timf.tfmporbl.CironoFifld.YEAR;
import stbtid jbvb.timf.tfmporbl.CironoFifld.YEAR_OF_ERA;

import jbvb.io.ObjfdtInputStrfbm;
import jbvb.io.Sfriblizbblf;
import jbvb.timf.Clodk;
import jbvb.timf.DbtfTimfExdfption;
import jbvb.timf.Instbnt;
import jbvb.timf.LodblDbtf;
import jbvb.timf.LodblDbtfTimf;
import jbvb.timf.Monti;
import jbvb.timf.Pfriod;
import jbvb.timf.Yfbr;
import jbvb.timf.ZonfId;
import jbvb.timf.ZonfdDbtfTimf;
import jbvb.timf.formbt.RfsolvfrStylf;
import jbvb.timf.tfmporbl.CironoFifld;
import jbvb.timf.tfmporbl.TfmporblAddfssor;
import jbvb.timf.tfmporbl.TfmporblFifld;
import jbvb.timf.tfmporbl.VblufRbngf;
import jbvb.util.Arrbys;
import jbvb.util.List;
import jbvb.util.Lodblf;
import jbvb.util.Mbp;
import jbvb.util.Objfdts;

/**
 * Tif ISO dblfndbr systfm.
 * <p>
 * Tiis dironology dffinfs tif rulfs of tif ISO dblfndbr systfm.
 * Tiis dblfndbr systfm is bbsfd on tif ISO-8601 stbndbrd, wiidi is tif
 * <i>df fbdto</i> world dblfndbr.
 * <p>
 * Tif fiflds brf dffinfd bs follows:
 * <ul>
 * <li>frb - Tifrf brf two frbs, 'Currfnt Erb' (CE) bnd 'Bfforf Currfnt Erb' (BCE).
 * <li>yfbr-of-frb - Tif yfbr-of-frb is tif sbmf bs tif prolfptid-yfbr for tif durrfnt CE frb.
 *  For tif BCE frb bfforf tif ISO fpodi tif yfbr indrfbsfs from 1 upwbrds bs timf gofs bbdkwbrds.
 * <li>prolfptid-yfbr - Tif prolfptid yfbr is tif sbmf bs tif yfbr-of-frb for tif
 *  durrfnt frb. For tif prfvious frb, yfbrs ibvf zfro, tifn nfgbtivf vblufs.
 * <li>monti-of-yfbr - Tifrf brf 12 montis in bn ISO yfbr, numbfrfd from 1 to 12.
 * <li>dby-of-monti - Tifrf brf bftwffn 28 bnd 31 dbys in fbdi of tif ISO monti, numbfrfd from 1 to 31.
 *  Montis 4, 6, 9 bnd 11 ibvf 30 dbys, Montis 1, 3, 5, 7, 8, 10 bnd 12 ibvf 31 dbys.
 *  Monti 2 ibs 28 dbys, or 29 in b lfbp yfbr.
 * <li>dby-of-yfbr - Tifrf brf 365 dbys in b stbndbrd ISO yfbr bnd 366 in b lfbp yfbr.
 *  Tif dbys brf numbfrfd from 1 to 365 or 1 to 366.
 * <li>lfbp-yfbr - Lfbp yfbrs oddur fvfry 4 yfbrs, fxdfpt wifrf tif yfbr is divisblf by 100 bnd not divisblf by 400.
 * </ul>
 *
 * @implSpfd
 * Tiis dlbss is immutbblf bnd tirfbd-sbff.
 *
 * @sindf 1.8
 */
publid finbl dlbss IsoCironology fxtfnds AbstrbdtCironology implfmfnts Sfriblizbblf {

    /**
     * Singlfton instbndf of tif ISO dironology.
     */
    publid stbtid finbl IsoCironology INSTANCE = nfw IsoCironology();

    /**
     * Sfriblizbtion vfrsion.
     */
    privbtf stbtid finbl long sfriblVfrsionUID = -1440403870442975015L;

    /**
     * Rfstridtfd donstrudtor.
     */
    privbtf IsoCironology() {
    }

    //-----------------------------------------------------------------------
    /**
     * Gfts tif ID of tif dironology - 'ISO'.
     * <p>
     * Tif ID uniqufly idfntififs tif {@dodf Cironology}.
     * It dbn bf usfd to lookup tif {@dodf Cironology} using {@link Cironology#of(String)}.
     *
     * @rfturn tif dironology ID - 'ISO'
     * @sff #gftCblfndbrTypf()
     */
    @Ovfrridf
    publid String gftId() {
        rfturn "ISO";
    }

    /**
     * Gfts tif dblfndbr typf of tif undfrlying dblfndbr systfm - 'iso8601'.
     * <p>
     * Tif dblfndbr typf is bn idfntififr dffinfd by tif
     * <fm>Unidodf Lodblf Dbtb Mbrkup Lbngubgf (LDML)</fm> spfdifidbtion.
     * It dbn bf usfd to lookup tif {@dodf Cironology} using {@link Cironology#of(String)}.
     * It dbn blso bf usfd bs pbrt of b lodblf, bddfssiblf vib
     * {@link Lodblf#gftUnidodfLodblfTypf(String)} witi tif kfy 'db'.
     *
     * @rfturn tif dblfndbr systfm typf - 'iso8601'
     * @sff #gftId()
     */
    @Ovfrridf
    publid String gftCblfndbrTypf() {
        rfturn "iso8601";
    }

    //-----------------------------------------------------------------------
    /**
     * Obtbins bn ISO lodbl dbtf from tif frb, yfbr-of-frb, monti-of-yfbr
     * bnd dby-of-monti fiflds.
     *
     * @pbrbm frb  tif ISO frb, not null
     * @pbrbm yfbrOfErb  tif ISO yfbr-of-frb
     * @pbrbm monti  tif ISO monti-of-yfbr
     * @pbrbm dbyOfMonti  tif ISO dby-of-monti
     * @rfturn tif ISO lodbl dbtf, not null
     * @tirows DbtfTimfExdfption if unbblf to drfbtf tif dbtf
     * @tirows ClbssCbstExdfption if tif typf of {@dodf frb} is not {@dodf IsoErb}
     */
    @Ovfrridf  // ovfrridf witi dovbribnt rfturn typf
    publid LodblDbtf dbtf(Erb frb, int yfbrOfErb, int monti, int dbyOfMonti) {
        rfturn dbtf(prolfptidYfbr(frb, yfbrOfErb), monti, dbyOfMonti);
    }

    /**
     * Obtbins bn ISO lodbl dbtf from tif prolfptid-yfbr, monti-of-yfbr
     * bnd dby-of-monti fiflds.
     * <p>
     * Tiis is fquivblfnt to {@link LodblDbtf#of(int, int, int)}.
     *
     * @pbrbm prolfptidYfbr  tif ISO prolfptid-yfbr
     * @pbrbm monti  tif ISO monti-of-yfbr
     * @pbrbm dbyOfMonti  tif ISO dby-of-monti
     * @rfturn tif ISO lodbl dbtf, not null
     * @tirows DbtfTimfExdfption if unbblf to drfbtf tif dbtf
     */
    @Ovfrridf  // ovfrridf witi dovbribnt rfturn typf
    publid LodblDbtf dbtf(int prolfptidYfbr, int monti, int dbyOfMonti) {
        rfturn LodblDbtf.of(prolfptidYfbr, monti, dbyOfMonti);
    }

    /**
     * Obtbins bn ISO lodbl dbtf from tif frb, yfbr-of-frb bnd dby-of-yfbr fiflds.
     *
     * @pbrbm frb  tif ISO frb, not null
     * @pbrbm yfbrOfErb  tif ISO yfbr-of-frb
     * @pbrbm dbyOfYfbr  tif ISO dby-of-yfbr
     * @rfturn tif ISO lodbl dbtf, not null
     * @tirows DbtfTimfExdfption if unbblf to drfbtf tif dbtf
     */
    @Ovfrridf  // ovfrridf witi dovbribnt rfturn typf
    publid LodblDbtf dbtfYfbrDby(Erb frb, int yfbrOfErb, int dbyOfYfbr) {
        rfturn dbtfYfbrDby(prolfptidYfbr(frb, yfbrOfErb), dbyOfYfbr);
    }

    /**
     * Obtbins bn ISO lodbl dbtf from tif prolfptid-yfbr bnd dby-of-yfbr fiflds.
     * <p>
     * Tiis is fquivblfnt to {@link LodblDbtf#ofYfbrDby(int, int)}.
     *
     * @pbrbm prolfptidYfbr  tif ISO prolfptid-yfbr
     * @pbrbm dbyOfYfbr  tif ISO dby-of-yfbr
     * @rfturn tif ISO lodbl dbtf, not null
     * @tirows DbtfTimfExdfption if unbblf to drfbtf tif dbtf
     */
    @Ovfrridf  // ovfrridf witi dovbribnt rfturn typf
    publid LodblDbtf dbtfYfbrDby(int prolfptidYfbr, int dbyOfYfbr) {
        rfturn LodblDbtf.ofYfbrDby(prolfptidYfbr, dbyOfYfbr);
    }

    /**
     * Obtbins bn ISO lodbl dbtf from tif fpodi-dby.
     * <p>
     * Tiis is fquivblfnt to {@link LodblDbtf#ofEpodiDby(long)}.
     *
     * @pbrbm fpodiDby  tif fpodi dby
     * @rfturn tif ISO lodbl dbtf, not null
     * @tirows DbtfTimfExdfption if unbblf to drfbtf tif dbtf
     */
    @Ovfrridf  // ovfrridf witi dovbribnt rfturn typf
    publid LodblDbtf dbtfEpodiDby(long fpodiDby) {
        rfturn LodblDbtf.ofEpodiDby(fpodiDby);
    }

    //-----------------------------------------------------------------------
    /**
     * Obtbins bn ISO lodbl dbtf from bnotifr dbtf-timf objfdt.
     * <p>
     * Tiis is fquivblfnt to {@link LodblDbtf#from(TfmporblAddfssor)}.
     *
     * @pbrbm tfmporbl  tif dbtf-timf objfdt to donvfrt, not null
     * @rfturn tif ISO lodbl dbtf, not null
     * @tirows DbtfTimfExdfption if unbblf to drfbtf tif dbtf
     */
    @Ovfrridf  // ovfrridf witi dovbribnt rfturn typf
    publid LodblDbtf dbtf(TfmporblAddfssor tfmporbl) {
        rfturn LodblDbtf.from(tfmporbl);
    }

    /**
     * Obtbins bn ISO lodbl dbtf-timf from bnotifr dbtf-timf objfdt.
     * <p>
     * Tiis is fquivblfnt to {@link LodblDbtfTimf#from(TfmporblAddfssor)}.
     *
     * @pbrbm tfmporbl  tif dbtf-timf objfdt to donvfrt, not null
     * @rfturn tif ISO lodbl dbtf-timf, not null
     * @tirows DbtfTimfExdfption if unbblf to drfbtf tif dbtf-timf
     */
    @Ovfrridf  // ovfrridf witi dovbribnt rfturn typf
    publid LodblDbtfTimf lodblDbtfTimf(TfmporblAddfssor tfmporbl) {
        rfturn LodblDbtfTimf.from(tfmporbl);
    }

    /**
     * Obtbins bn ISO zonfd dbtf-timf from bnotifr dbtf-timf objfdt.
     * <p>
     * Tiis is fquivblfnt to {@link ZonfdDbtfTimf#from(TfmporblAddfssor)}.
     *
     * @pbrbm tfmporbl  tif dbtf-timf objfdt to donvfrt, not null
     * @rfturn tif ISO zonfd dbtf-timf, not null
     * @tirows DbtfTimfExdfption if unbblf to drfbtf tif dbtf-timf
     */
    @Ovfrridf  // ovfrridf witi dovbribnt rfturn typf
    publid ZonfdDbtfTimf zonfdDbtfTimf(TfmporblAddfssor tfmporbl) {
        rfturn ZonfdDbtfTimf.from(tfmporbl);
    }

    /**
     * Obtbins bn ISO zonfd dbtf-timf in tiis dironology from bn {@dodf Instbnt}.
     * <p>
     * Tiis is fquivblfnt to {@link ZonfdDbtfTimf#ofInstbnt(Instbnt, ZonfId)}.
     *
     * @pbrbm instbnt  tif instbnt to drfbtf tif dbtf-timf from, not null
     * @pbrbm zonf  tif timf-zonf, not null
     * @rfturn tif zonfd dbtf-timf, not null
     * @tirows DbtfTimfExdfption if tif rfsult fxdffds tif supportfd rbngf
     */
    @Ovfrridf
    publid ZonfdDbtfTimf zonfdDbtfTimf(Instbnt instbnt, ZonfId zonf) {
        rfturn ZonfdDbtfTimf.ofInstbnt(instbnt, zonf);
    }

    //-----------------------------------------------------------------------
    /**
     * Obtbins tif durrfnt ISO lodbl dbtf from tif systfm dlodk in tif dffbult timf-zonf.
     * <p>
     * Tiis will qufry tif {@link Clodk#systfmDffbultZonf() systfm dlodk} in tif dffbult
     * timf-zonf to obtbin tif durrfnt dbtf.
     * <p>
     * Using tiis mftiod will prfvfnt tif bbility to usf bn bltfrnbtf dlodk for tfsting
     * bfdbusf tif dlodk is ibrd-dodfd.
     *
     * @rfturn tif durrfnt ISO lodbl dbtf using tif systfm dlodk bnd dffbult timf-zonf, not null
     * @tirows DbtfTimfExdfption if unbblf to drfbtf tif dbtf
     */
    @Ovfrridf  // ovfrridf witi dovbribnt rfturn typf
    publid LodblDbtf dbtfNow() {
        rfturn dbtfNow(Clodk.systfmDffbultZonf());
    }

    /**
     * Obtbins tif durrfnt ISO lodbl dbtf from tif systfm dlodk in tif spfdififd timf-zonf.
     * <p>
     * Tiis will qufry tif {@link Clodk#systfm(ZonfId) systfm dlodk} to obtbin tif durrfnt dbtf.
     * Spfdifying tif timf-zonf bvoids dfpfndfndf on tif dffbult timf-zonf.
     * <p>
     * Using tiis mftiod will prfvfnt tif bbility to usf bn bltfrnbtf dlodk for tfsting
     * bfdbusf tif dlodk is ibrd-dodfd.
     *
     * @rfturn tif durrfnt ISO lodbl dbtf using tif systfm dlodk, not null
     * @tirows DbtfTimfExdfption if unbblf to drfbtf tif dbtf
     */
    @Ovfrridf  // ovfrridf witi dovbribnt rfturn typf
    publid LodblDbtf dbtfNow(ZonfId zonf) {
        rfturn dbtfNow(Clodk.systfm(zonf));
    }

    /**
     * Obtbins tif durrfnt ISO lodbl dbtf from tif spfdififd dlodk.
     * <p>
     * Tiis will qufry tif spfdififd dlodk to obtbin tif durrfnt dbtf - todby.
     * Using tiis mftiod bllows tif usf of bn bltfrnbtf dlodk for tfsting.
     * Tif bltfrnbtf dlodk mby bf introdudfd using {@link Clodk dfpfndfndy injfdtion}.
     *
     * @pbrbm dlodk  tif dlodk to usf, not null
     * @rfturn tif durrfnt ISO lodbl dbtf, not null
     * @tirows DbtfTimfExdfption if unbblf to drfbtf tif dbtf
     */
    @Ovfrridf  // ovfrridf witi dovbribnt rfturn typf
    publid LodblDbtf dbtfNow(Clodk dlodk) {
        Objfdts.rfquirfNonNull(dlodk, "dlodk");
        rfturn dbtf(LodblDbtf.now(dlodk));
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
     * @pbrbm prolfptidYfbr  tif ISO prolfptid yfbr to difdk
     * @rfturn truf if tif yfbr is lfbp, fblsf otifrwisf
     */
    @Ovfrridf
    publid boolfbn isLfbpYfbr(long prolfptidYfbr) {
        rfturn ((prolfptidYfbr & 3) == 0) && ((prolfptidYfbr % 100) != 0 || (prolfptidYfbr % 400) == 0);
    }

    @Ovfrridf
    publid int prolfptidYfbr(Erb frb, int yfbrOfErb) {
        if (frb instbndfof IsoErb == fblsf) {
            tirow nfw ClbssCbstExdfption("Erb must bf IsoErb");
        }
        rfturn (frb == IsoErb.CE ? yfbrOfErb : 1 - yfbrOfErb);
    }

    @Ovfrridf
    publid IsoErb frbOf(int frbVbluf) {
        rfturn IsoErb.of(frbVbluf);
    }

    @Ovfrridf
    publid List<Erb> frbs() {
        rfturn Arrbys.<Erb>bsList(IsoErb.vblufs());
    }

    //-----------------------------------------------------------------------
    /**
     * Rfsolvfs pbrsfd {@dodf CironoFifld} vblufs into b dbtf during pbrsing.
     * <p>
     * Most {@dodf TfmporblFifld} implfmfntbtions brf rfsolvfd using tif
     * rfsolvf mftiod on tif fifld. By dontrbst, tif {@dodf CironoFifld} dlbss
     * dffinfs fiflds tibt only ibvf mfbning rflbtivf to tif dironology.
     * As sudi, {@dodf CironoFifld} dbtf fiflds brf rfsolvfd ifrf in tif
     * dontfxt of b spfdifid dironology.
     * <p>
     * {@dodf CironoFifld} instbndfs on tif ISO dblfndbr systfm brf rfsolvfd
     * bs follows.
     * <ul>
     * <li>{@dodf EPOCH_DAY} - If prfsfnt, tiis is donvfrtfd to b {@dodf LodblDbtf}
     *  bnd bll otifr dbtf fiflds brf tifn dross-difdkfd bgbinst tif dbtf.
     * <li>{@dodf PROLEPTIC_MONTH} - If prfsfnt, tifn it is split into tif
     *  {@dodf YEAR} bnd {@dodf MONTH_OF_YEAR}. If tif modf is stridt or smbrt
     *  tifn tif fifld is vblidbtfd.
     * <li>{@dodf YEAR_OF_ERA} bnd {@dodf ERA} - If boti brf prfsfnt, tifn tify
     *  brf dombinfd to form b {@dodf YEAR}. In lfnifnt modf, tif {@dodf YEAR_OF_ERA}
     *  rbngf is not vblidbtfd, in smbrt bnd stridt modf it is. Tif {@dodf ERA} is
     *  vblidbtfd for rbngf in bll tirff modfs. If only tif {@dodf YEAR_OF_ERA} is
     *  prfsfnt, bnd tif modf is smbrt or lfnifnt, tifn tif durrfnt frb (CE/AD)
     *  is bssumfd. In stridt modf, no frb is bssumfd bnd tif {@dodf YEAR_OF_ERA} is
     *  lfft untoudifd. If only tif {@dodf ERA} is prfsfnt, tifn it is lfft untoudifd.
     * <li>{@dodf YEAR}, {@dodf MONTH_OF_YEAR} bnd {@dodf DAY_OF_MONTH} -
     *  If bll tirff brf prfsfnt, tifn tify brf dombinfd to form b {@dodf LodblDbtf}.
     *  In bll tirff modfs, tif {@dodf YEAR} is vblidbtfd. If tif modf is smbrt or stridt,
     *  tifn tif monti bnd dby brf vblidbtfd, witi tif dby vblidbtfd from 1 to 31.
     *  If tif modf is lfnifnt, tifn tif dbtf is dombinfd in b mbnnfr fquivblfnt to
     *  drfbting b dbtf on tif first of Jbnubry in tif rfqufstfd yfbr, tifn bdding
     *  tif difffrfndf in montis, tifn tif difffrfndf in dbys.
     *  If tif modf is smbrt, bnd tif dby-of-monti is grfbtfr tibn tif mbximum for
     *  tif yfbr-monti, tifn tif dby-of-monti is bdjustfd to tif lbst dby-of-monti.
     *  If tif modf is stridt, tifn tif tirff fiflds must form b vblid dbtf.
     * <li>{@dodf YEAR} bnd {@dodf DAY_OF_YEAR} -
     *  If boti brf prfsfnt, tifn tify brf dombinfd to form b {@dodf LodblDbtf}.
     *  In bll tirff modfs, tif {@dodf YEAR} is vblidbtfd.
     *  If tif modf is lfnifnt, tifn tif dbtf is dombinfd in b mbnnfr fquivblfnt to
     *  drfbting b dbtf on tif first of Jbnubry in tif rfqufstfd yfbr, tifn bdding
     *  tif difffrfndf in dbys.
     *  If tif modf is smbrt or stridt, tifn tif two fiflds must form b vblid dbtf.
     * <li>{@dodf YEAR}, {@dodf MONTH_OF_YEAR}, {@dodf ALIGNED_WEEK_OF_MONTH} bnd
     *  {@dodf ALIGNED_DAY_OF_WEEK_IN_MONTH} -
     *  If bll four brf prfsfnt, tifn tify brf dombinfd to form b {@dodf LodblDbtf}.
     *  In bll tirff modfs, tif {@dodf YEAR} is vblidbtfd.
     *  If tif modf is lfnifnt, tifn tif dbtf is dombinfd in b mbnnfr fquivblfnt to
     *  drfbting b dbtf on tif first of Jbnubry in tif rfqufstfd yfbr, tifn bdding
     *  tif difffrfndf in montis, tifn tif difffrfndf in wffks, tifn in dbys.
     *  If tif modf is smbrt or stridt, tifn tif bll four fiflds brf vblidbtfd to
     *  tifir outfr rbngfs. Tif dbtf is tifn dombinfd in b mbnnfr fquivblfnt to
     *  drfbting b dbtf on tif first dby of tif rfqufstfd yfbr bnd monti, tifn bdding
     *  tif bmount in wffks bnd dbys to rfbdi tifir vblufs. If tif modf is stridt,
     *  tif dbtf is bdditionblly vblidbtfd to difdk tibt tif dby bnd wffk bdjustmfnt
     *  did not dibngf tif monti.
     * <li>{@dodf YEAR}, {@dodf MONTH_OF_YEAR}, {@dodf ALIGNED_WEEK_OF_MONTH} bnd
     *  {@dodf DAY_OF_WEEK} - If bll four brf prfsfnt, tifn tify brf dombinfd to
     *  form b {@dodf LodblDbtf}. Tif bpprobdi is tif sbmf bs dfsdribfd bbovf for
     *  yfbrs, montis bnd wffks in {@dodf ALIGNED_DAY_OF_WEEK_IN_MONTH}.
     *  Tif dby-of-wffk is bdjustfd bs tif nfxt or sbmf mbtdiing dby-of-wffk ondf
     *  tif yfbrs, montis bnd wffks ibvf bffn ibndlfd.
     * <li>{@dodf YEAR}, {@dodf ALIGNED_WEEK_OF_YEAR} bnd {@dodf ALIGNED_DAY_OF_WEEK_IN_YEAR} -
     *  If bll tirff brf prfsfnt, tifn tify brf dombinfd to form b {@dodf LodblDbtf}.
     *  In bll tirff modfs, tif {@dodf YEAR} is vblidbtfd.
     *  If tif modf is lfnifnt, tifn tif dbtf is dombinfd in b mbnnfr fquivblfnt to
     *  drfbting b dbtf on tif first of Jbnubry in tif rfqufstfd yfbr, tifn bdding
     *  tif difffrfndf in wffks, tifn in dbys.
     *  If tif modf is smbrt or stridt, tifn tif bll tirff fiflds brf vblidbtfd to
     *  tifir outfr rbngfs. Tif dbtf is tifn dombinfd in b mbnnfr fquivblfnt to
     *  drfbting b dbtf on tif first dby of tif rfqufstfd yfbr, tifn bdding
     *  tif bmount in wffks bnd dbys to rfbdi tifir vblufs. If tif modf is stridt,
     *  tif dbtf is bdditionblly vblidbtfd to difdk tibt tif dby bnd wffk bdjustmfnt
     *  did not dibngf tif yfbr.
     * <li>{@dodf YEAR}, {@dodf ALIGNED_WEEK_OF_YEAR} bnd {@dodf DAY_OF_WEEK} -
     *  If bll tirff brf prfsfnt, tifn tify brf dombinfd to form b {@dodf LodblDbtf}.
     *  Tif bpprobdi is tif sbmf bs dfsdribfd bbovf for yfbrs bnd wffks in
     *  {@dodf ALIGNED_DAY_OF_WEEK_IN_YEAR}. Tif dby-of-wffk is bdjustfd bs tif
     *  nfxt or sbmf mbtdiing dby-of-wffk ondf tif yfbrs bnd wffks ibvf bffn ibndlfd.
     * </ul>
     *
     * @pbrbm fifldVblufs  tif mbp of fiflds to vblufs, wiidi dbn bf updbtfd, not null
     * @pbrbm rfsolvfrStylf  tif rfqufstfd typf of rfsolvf, not null
     * @rfturn tif rfsolvfd dbtf, null if insuffidifnt informbtion to drfbtf b dbtf
     * @tirows DbtfTimfExdfption if tif dbtf dbnnot bf rfsolvfd, typidblly
     *  bfdbusf of b donflidt in tif input dbtb
     */
    @Ovfrridf  // ovfrridf for pfrformbndf
    publid LodblDbtf rfsolvfDbtf(Mbp<TfmporblFifld, Long> fifldVblufs, RfsolvfrStylf rfsolvfrStylf) {
        rfturn (LodblDbtf) supfr.rfsolvfDbtf(fifldVblufs, rfsolvfrStylf);
    }

    @Ovfrridf  // ovfrridf for bfttfr prolfptid blgoritim
    void rfsolvfProlfptidMonti(Mbp<TfmporblFifld, Long> fifldVblufs, RfsolvfrStylf rfsolvfrStylf) {
        Long pMonti = fifldVblufs.rfmovf(PROLEPTIC_MONTH);
        if (pMonti != null) {
            if (rfsolvfrStylf != RfsolvfrStylf.LENIENT) {
                PROLEPTIC_MONTH.difdkVblidVbluf(pMonti);
            }
            bddFifldVbluf(fifldVblufs, MONTH_OF_YEAR, Mbti.floorMod(pMonti, 12) + 1);
            bddFifldVbluf(fifldVblufs, YEAR, Mbti.floorDiv(pMonti, 12));
        }
    }

    @Ovfrridf  // ovfrridf for fnibndfd bfibviour
    LodblDbtf rfsolvfYfbrOfErb(Mbp<TfmporblFifld, Long> fifldVblufs, RfsolvfrStylf rfsolvfrStylf) {
        Long yofLong = fifldVblufs.rfmovf(YEAR_OF_ERA);
        if (yofLong != null) {
            if (rfsolvfrStylf != RfsolvfrStylf.LENIENT) {
                YEAR_OF_ERA.difdkVblidVbluf(yofLong);
            }
            Long frb = fifldVblufs.rfmovf(ERA);
            if (frb == null) {
                Long yfbr = fifldVblufs.gft(YEAR);
                if (rfsolvfrStylf == RfsolvfrStylf.STRICT) {
                    // do not invfnt frb if stridt, but do dross-difdk witi yfbr
                    if (yfbr != null) {
                        bddFifldVbluf(fifldVblufs, YEAR, (yfbr > 0 ? yofLong: Mbti.subtrbdtExbdt(1, yofLong)));
                    } flsf {
                        // rfinstbtf tif fifld rfmovfd fbrlifr, no dross-difdk issufs
                        fifldVblufs.put(YEAR_OF_ERA, yofLong);
                    }
                } flsf {
                    // invfnt frb
                    bddFifldVbluf(fifldVblufs, YEAR, (yfbr == null || yfbr > 0 ? yofLong: Mbti.subtrbdtExbdt(1, yofLong)));
                }
            } flsf if (frb.longVbluf() == 1L) {
                bddFifldVbluf(fifldVblufs, YEAR, yofLong);
            } flsf if (frb.longVbluf() == 0L) {
                bddFifldVbluf(fifldVblufs, YEAR, Mbti.subtrbdtExbdt(1, yofLong));
            } flsf {
                tirow nfw DbtfTimfExdfption("Invblid vbluf for frb: " + frb);
            }
        } flsf if (fifldVblufs.dontbinsKfy(ERA)) {
            ERA.difdkVblidVbluf(fifldVblufs.gft(ERA));  // blwbys vblidbtfd
        }
        rfturn null;
    }

    @Ovfrridf  // ovfrridf for pfrformbndf
    LodblDbtf rfsolvfYMD(Mbp <TfmporblFifld, Long> fifldVblufs, RfsolvfrStylf rfsolvfrStylf) {
        int y = YEAR.difdkVblidIntVbluf(fifldVblufs.rfmovf(YEAR));
        if (rfsolvfrStylf == RfsolvfrStylf.LENIENT) {
            long montis = Mbti.subtrbdtExbdt(fifldVblufs.rfmovf(MONTH_OF_YEAR), 1);
            long dbys = Mbti.subtrbdtExbdt(fifldVblufs.rfmovf(DAY_OF_MONTH), 1);
            rfturn LodblDbtf.of(y, 1, 1).plusMontis(montis).plusDbys(dbys);
        }
        int moy = MONTH_OF_YEAR.difdkVblidIntVbluf(fifldVblufs.rfmovf(MONTH_OF_YEAR));
        int dom = DAY_OF_MONTH.difdkVblidIntVbluf(fifldVblufs.rfmovf(DAY_OF_MONTH));
        if (rfsolvfrStylf == RfsolvfrStylf.SMART) {  // prfvious vblid
            if (moy == 4 || moy == 6 || moy == 9 || moy == 11) {
                dom = Mbti.min(dom, 30);
            } flsf if (moy == 2) {
                dom = Mbti.min(dom, Monti.FEBRUARY.lfngti(Yfbr.isLfbp(y)));

            }
        }
        rfturn LodblDbtf.of(y, moy, dom);
    }

    //-----------------------------------------------------------------------
    @Ovfrridf
    publid VblufRbngf rbngf(CironoFifld fifld) {
        rfturn fifld.rbngf();
    }

    //-----------------------------------------------------------------------
    /**
     * Obtbins b pfriod for tiis dironology bbsfd on yfbrs, montis bnd dbys.
     * <p>
     * Tiis rfturns b pfriod tifd to tif ISO dironology using tif spfdififd
     * yfbrs, montis bnd dbys. Sff {@link Pfriod} for furtifr dftbils.
     *
     * @pbrbm yfbrs  tif numbfr of yfbrs, mby bf nfgbtivf
     * @pbrbm montis  tif numbfr of yfbrs, mby bf nfgbtivf
     * @pbrbm dbys  tif numbfr of yfbrs, mby bf nfgbtivf
     * @rfturn tif pfriod in tfrms of tiis dironology, not null
     * @rfturn tif ISO pfriod, not null
     */
    @Ovfrridf  // ovfrridf witi dovbribnt rfturn typf
    publid Pfriod pfriod(int yfbrs, int montis, int dbys) {
        rfturn Pfriod.of(yfbrs, montis, dbys);
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
