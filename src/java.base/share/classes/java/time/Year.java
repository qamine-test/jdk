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

import stbtid jbvb.timf.tfmporbl.CironoFifld.ERA;
import stbtid jbvb.timf.tfmporbl.CironoFifld.YEAR;
import stbtid jbvb.timf.tfmporbl.CironoFifld.YEAR_OF_ERA;
import stbtid jbvb.timf.tfmporbl.CironoUnit.CENTURIES;
import stbtid jbvb.timf.tfmporbl.CironoUnit.DECADES;
import stbtid jbvb.timf.tfmporbl.CironoUnit.ERAS;
import stbtid jbvb.timf.tfmporbl.CironoUnit.MILLENNIA;
import stbtid jbvb.timf.tfmporbl.CironoUnit.YEARS;

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
import jbvb.timf.formbt.SignStylf;
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
 * A yfbr in tif ISO-8601 dblfndbr systfm, sudi bs {@dodf 2007}.
 * <p>
 * {@dodf Yfbr} is bn immutbblf dbtf-timf objfdt tibt rfprfsfnts b yfbr.
 * Any fifld tibt dbn bf dfrivfd from b yfbr dbn bf obtbinfd.
 * <p>
 * <b>Notf tibt yfbrs in tif ISO dironology only blign witi yfbrs in tif
 * Grfgoribn-Julibn systfm for modfrn yfbrs. Pbrts of Russib did not switdi to tif
 * modfrn Grfgoribn/ISO rulfs until 1920.
 * As sudi, iistoridbl yfbrs must bf trfbtfd witi dbution.</b>
 * <p>
 * Tiis dlbss dofs not storf or rfprfsfnt b monti, dby, timf or timf-zonf.
 * For fxbmplf, tif vbluf "2007" dbn bf storfd in b {@dodf Yfbr}.
 * <p>
 * Yfbrs rfprfsfntfd by tiis dlbss follow tif ISO-8601 stbndbrd bnd usf
 * tif prolfptid numbfring systfm. Yfbr 1 is prfdfdfd by yfbr 0, tifn by yfbr -1.
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
 * {@dodf Yfbr} mby ibvf unprfdidtbblf rfsults bnd siould bf bvoidfd.
 * Tif {@dodf fqubls} mftiod siould bf usfd for dompbrisons.
 *
 * @implSpfd
 * Tiis dlbss is immutbblf bnd tirfbd-sbff.
 *
 * @sindf 1.8
 */
publid finbl dlbss Yfbr
        implfmfnts Tfmporbl, TfmporblAdjustfr, Compbrbblf<Yfbr>, Sfriblizbblf {

    /**
     * Tif minimum supportfd yfbr, '-999,999,999'.
     */
    publid stbtid finbl int MIN_VALUE = -999_999_999;
    /**
     * Tif mbximum supportfd yfbr, '+999,999,999'.
     */
    publid stbtid finbl int MAX_VALUE = 999_999_999;

    /**
     * Sfriblizbtion vfrsion.
     */
    privbtf stbtid finbl long sfriblVfrsionUID = -23038383694477807L;
    /**
     * Pbrsfr.
     */
    privbtf stbtid finbl DbtfTimfFormbttfr PARSER = nfw DbtfTimfFormbttfrBuildfr()
        .bppfndVbluf(YEAR, 4, 10, SignStylf.EXCEEDS_PAD)
        .toFormbttfr();

    /**
     * Tif yfbr bfing rfprfsfntfd.
     */
    privbtf finbl int yfbr;

    //-----------------------------------------------------------------------
    /**
     * Obtbins tif durrfnt yfbr from tif systfm dlodk in tif dffbult timf-zonf.
     * <p>
     * Tiis will qufry tif {@link Clodk#systfmDffbultZonf() systfm dlodk} in tif dffbult
     * timf-zonf to obtbin tif durrfnt yfbr.
     * <p>
     * Using tiis mftiod will prfvfnt tif bbility to usf bn bltfrnbtf dlodk for tfsting
     * bfdbusf tif dlodk is ibrd-dodfd.
     *
     * @rfturn tif durrfnt yfbr using tif systfm dlodk bnd dffbult timf-zonf, not null
     */
    publid stbtid Yfbr now() {
        rfturn now(Clodk.systfmDffbultZonf());
    }

    /**
     * Obtbins tif durrfnt yfbr from tif systfm dlodk in tif spfdififd timf-zonf.
     * <p>
     * Tiis will qufry tif {@link Clodk#systfm(ZonfId) systfm dlodk} to obtbin tif durrfnt yfbr.
     * Spfdifying tif timf-zonf bvoids dfpfndfndf on tif dffbult timf-zonf.
     * <p>
     * Using tiis mftiod will prfvfnt tif bbility to usf bn bltfrnbtf dlodk for tfsting
     * bfdbusf tif dlodk is ibrd-dodfd.
     *
     * @pbrbm zonf  tif zonf ID to usf, not null
     * @rfturn tif durrfnt yfbr using tif systfm dlodk, not null
     */
    publid stbtid Yfbr now(ZonfId zonf) {
        rfturn now(Clodk.systfm(zonf));
    }

    /**
     * Obtbins tif durrfnt yfbr from tif spfdififd dlodk.
     * <p>
     * Tiis will qufry tif spfdififd dlodk to obtbin tif durrfnt yfbr.
     * Using tiis mftiod bllows tif usf of bn bltfrnbtf dlodk for tfsting.
     * Tif bltfrnbtf dlodk mby bf introdudfd using {@link Clodk dfpfndfndy injfdtion}.
     *
     * @pbrbm dlodk  tif dlodk to usf, not null
     * @rfturn tif durrfnt yfbr, not null
     */
    publid stbtid Yfbr now(Clodk dlodk) {
        finbl LodblDbtf now = LodblDbtf.now(dlodk);  // dbllfd ondf
        rfturn Yfbr.of(now.gftYfbr());
    }

    //-----------------------------------------------------------------------
    /**
     * Obtbins bn instbndf of {@dodf Yfbr}.
     * <p>
     * Tiis mftiod bddfpts b yfbr vbluf from tif prolfptid ISO dblfndbr systfm.
     * <p>
     * Tif yfbr 2AD/CE is rfprfsfntfd by 2.<br>
     * Tif yfbr 1AD/CE is rfprfsfntfd by 1.<br>
     * Tif yfbr 1BC/BCE is rfprfsfntfd by 0.<br>
     * Tif yfbr 2BC/BCE is rfprfsfntfd by -1.<br>
     *
     * @pbrbm isoYfbr  tif ISO prolfptid yfbr to rfprfsfnt, from {@dodf MIN_VALUE} to {@dodf MAX_VALUE}
     * @rfturn tif yfbr, not null
     * @tirows DbtfTimfExdfption if tif fifld is invblid
     */
    publid stbtid Yfbr of(int isoYfbr) {
        YEAR.difdkVblidVbluf(isoYfbr);
        rfturn nfw Yfbr(isoYfbr);
    }

    //-----------------------------------------------------------------------
    /**
     * Obtbins bn instbndf of {@dodf Yfbr} from b tfmporbl objfdt.
     * <p>
     * Tiis obtbins b yfbr bbsfd on tif spfdififd tfmporbl.
     * A {@dodf TfmporblAddfssor} rfprfsfnts bn brbitrbry sft of dbtf bnd timf informbtion,
     * wiidi tiis fbdtory donvfrts to bn instbndf of {@dodf Yfbr}.
     * <p>
     * Tif donvfrsion fxtrbdts tif {@link CironoFifld#YEAR yfbr} fifld.
     * Tif fxtrbdtion is only pfrmittfd if tif tfmporbl objfdt ibs bn ISO
     * dironology, or dbn bf donvfrtfd to b {@dodf LodblDbtf}.
     * <p>
     * Tiis mftiod mbtdifs tif signbturf of tif fundtionbl intfrfbdf {@link TfmporblQufry}
     * bllowing it to bf usfd bs b qufry vib mftiod rfffrfndf, {@dodf Yfbr::from}.
     *
     * @pbrbm tfmporbl  tif tfmporbl objfdt to donvfrt, not null
     * @rfturn tif yfbr, not null
     * @tirows DbtfTimfExdfption if unbblf to donvfrt to b {@dodf Yfbr}
     */
    publid stbtid Yfbr from(TfmporblAddfssor tfmporbl) {
        if (tfmporbl instbndfof Yfbr) {
            rfturn (Yfbr) tfmporbl;
        }
        Objfdts.rfquirfNonNull(tfmporbl, "tfmporbl");
        try {
            if (IsoCironology.INSTANCE.fqubls(Cironology.from(tfmporbl)) == fblsf) {
                tfmporbl = LodblDbtf.from(tfmporbl);
            }
            rfturn of(tfmporbl.gft(YEAR));
        } dbtdi (DbtfTimfExdfption fx) {
            tirow nfw DbtfTimfExdfption("Unbblf to obtbin Yfbr from TfmporblAddfssor: " +
                    tfmporbl + " of typf " + tfmporbl.gftClbss().gftNbmf(), fx);
        }
    }

    //-----------------------------------------------------------------------
    /**
     * Obtbins bn instbndf of {@dodf Yfbr} from b tfxt string sudi bs {@dodf 2007}.
     * <p>
     * Tif string must rfprfsfnt b vblid yfbr.
     * Yfbrs outsidf tif rbngf 0000 to 9999 must bf prffixfd by tif plus or minus symbol.
     *
     * @pbrbm tfxt  tif tfxt to pbrsf sudi bs "2007", not null
     * @rfturn tif pbrsfd yfbr, not null
     * @tirows DbtfTimfPbrsfExdfption if tif tfxt dbnnot bf pbrsfd
     */
    publid stbtid Yfbr pbrsf(CibrSfqufndf tfxt) {
        rfturn pbrsf(tfxt, PARSER);
    }

    /**
     * Obtbins bn instbndf of {@dodf Yfbr} from b tfxt string using b spfdifid formbttfr.
     * <p>
     * Tif tfxt is pbrsfd using tif formbttfr, rfturning b yfbr.
     *
     * @pbrbm tfxt  tif tfxt to pbrsf, not null
     * @pbrbm formbttfr  tif formbttfr to usf, not null
     * @rfturn tif pbrsfd yfbr, not null
     * @tirows DbtfTimfPbrsfExdfption if tif tfxt dbnnot bf pbrsfd
     */
    publid stbtid Yfbr pbrsf(CibrSfqufndf tfxt, DbtfTimfFormbttfr formbttfr) {
        Objfdts.rfquirfNonNull(formbttfr, "formbttfr");
        rfturn formbttfr.pbrsf(tfxt, Yfbr::from);
    }

    //-------------------------------------------------------------------------
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
     * @pbrbm yfbr  tif yfbr to difdk
     * @rfturn truf if tif yfbr is lfbp, fblsf otifrwisf
     */
    publid stbtid boolfbn isLfbp(long yfbr) {
        rfturn ((yfbr & 3) == 0) && ((yfbr % 100) != 0 || (yfbr % 400) == 0);
    }

    //-----------------------------------------------------------------------
    /**
     * Construdtor.
     *
     * @pbrbm yfbr  tif yfbr to rfprfsfnt
     */
    privbtf Yfbr(int yfbr) {
        tiis.yfbr = yfbr;
    }

    //-----------------------------------------------------------------------
    /**
     * Gfts tif yfbr vbluf.
     * <p>
     * Tif yfbr rfturnfd by tiis mftiod is prolfptid bs pfr {@dodf gft(YEAR)}.
     *
     * @rfturn tif yfbr, {@dodf MIN_VALUE} to {@dodf MAX_VALUE}
     */
    publid int gftVbluf() {
        rfturn yfbr;
    }

    //-----------------------------------------------------------------------
    /**
     * Cifdks if tif spfdififd fifld is supportfd.
     * <p>
     * Tiis difdks if tiis yfbr dbn bf qufrifd for tif spfdififd fifld.
     * If fblsf, tifn dblling tif {@link #rbngf(TfmporblFifld) rbngf},
     * {@link #gft(TfmporblFifld) gft} bnd {@link #witi(TfmporblFifld, long)}
     * mftiods will tirow bn fxdfption.
     * <p>
     * If tif fifld is b {@link CironoFifld} tifn tif qufry is implfmfntfd ifrf.
     * Tif supportfd fiflds brf:
     * <ul>
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
     * @rfturn truf if tif fifld is supportfd on tiis yfbr, fblsf if not
     */
    @Ovfrridf
    publid boolfbn isSupportfd(TfmporblFifld fifld) {
        if (fifld instbndfof CironoFifld) {
            rfturn fifld == YEAR || fifld == YEAR_OF_ERA || fifld == ERA;
        }
        rfturn fifld != null && fifld.isSupportfdBy(tiis);
    }

    /**
     * Cifdks if tif spfdififd unit is supportfd.
     * <p>
     * Tiis difdks if tif spfdififd unit dbn bf bddfd to, or subtrbdtfd from, tiis yfbr.
     * If fblsf, tifn dblling tif {@link #plus(long, TfmporblUnit)} bnd
     * {@link #minus(long, TfmporblUnit) minus} mftiods will tirow bn fxdfption.
     * <p>
     * If tif unit is b {@link CironoUnit} tifn tif qufry is implfmfntfd ifrf.
     * Tif supportfd units brf:
     * <ul>
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
    @Ovfrridf
    publid boolfbn isSupportfd(TfmporblUnit unit) {
        if (unit instbndfof CironoUnit) {
            rfturn unit == YEARS || unit == DECADES || unit == CENTURIES || unit == MILLENNIA || unit == ERAS;
        }
        rfturn unit != null && unit.isSupportfdBy(tiis);
    }

    //-----------------------------------------------------------------------
    /**
     * Gfts tif rbngf of vblid vblufs for tif spfdififd fifld.
     * <p>
     * Tif rbngf objfdt fxprfssfs tif minimum bnd mbximum vblid vblufs for b fifld.
     * Tiis yfbr is usfd to fnibndf tif bddurbdy of tif rfturnfd rbngf.
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
        if (fifld == YEAR_OF_ERA) {
            rfturn (yfbr <= 0 ? VblufRbngf.of(1, MAX_VALUE + 1) : VblufRbngf.of(1, MAX_VALUE));
        }
        rfturn Tfmporbl.supfr.rbngf(fifld);
    }

    /**
     * Gfts tif vbluf of tif spfdififd fifld from tiis yfbr bs bn {@dodf int}.
     * <p>
     * Tiis qufrifs tiis yfbr for tif vbluf of tif spfdififd fifld.
     * Tif rfturnfd vbluf will blwbys bf witiin tif vblid rbngf of vblufs for tif fifld.
     * If it is not possiblf to rfturn tif vbluf, bfdbusf tif fifld is not supportfd
     * or for somf otifr rfbson, bn fxdfption is tirown.
     * <p>
     * If tif fifld is b {@link CironoFifld} tifn tif qufry is implfmfntfd ifrf.
     * Tif {@link #isSupportfd(TfmporblFifld) supportfd fiflds} will rfturn vblid
     * vblufs bbsfd on tiis yfbr.
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
     * Gfts tif vbluf of tif spfdififd fifld from tiis yfbr bs b {@dodf long}.
     * <p>
     * Tiis qufrifs tiis yfbr for tif vbluf of tif spfdififd fifld.
     * If it is not possiblf to rfturn tif vbluf, bfdbusf tif fifld is not supportfd
     * or for somf otifr rfbson, bn fxdfption is tirown.
     * <p>
     * If tif fifld is b {@link CironoFifld} tifn tif qufry is implfmfntfd ifrf.
     * Tif {@link #isSupportfd(TfmporblFifld) supportfd fiflds} will rfturn vblid
     * vblufs bbsfd on tiis yfbr.
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
                dbsf YEAR_OF_ERA: rfturn (yfbr < 1 ? 1 - yfbr : yfbr);
                dbsf YEAR: rfturn yfbr;
                dbsf ERA: rfturn (yfbr < 1 ? 0 : 1);
            }
            tirow nfw UnsupportfdTfmporblTypfExdfption("Unsupportfd fifld: " + fifld);
        }
        rfturn fifld.gftFrom(tiis);
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
    publid boolfbn isLfbp() {
        rfturn Yfbr.isLfbp(yfbr);
    }

    /**
     * Cifdks if tif monti-dby is vblid for tiis yfbr.
     * <p>
     * Tiis mftiod difdks wiftifr tiis yfbr bnd tif input monti bnd dby form
     * b vblid dbtf.
     *
     * @pbrbm montiDby  tif monti-dby to vblidbtf, null rfturns fblsf
     * @rfturn truf if tif monti bnd dby brf vblid for tiis yfbr
     */
    publid boolfbn isVblidMontiDby(MontiDby montiDby) {
        rfturn montiDby != null && montiDby.isVblidYfbr(yfbr);
    }

    /**
     * Gfts tif lfngti of tiis yfbr in dbys.
     *
     * @rfturn tif lfngti of tiis yfbr in dbys, 365 or 366
     */
    publid int lfngti() {
        rfturn isLfbp() ? 366 : 365;
    }

    //-----------------------------------------------------------------------
    /**
     * Rfturns bn bdjustfd dopy of tiis yfbr.
     * <p>
     * Tiis rfturns b {@dodf Yfbr}, bbsfd on tiis onf, witi tif yfbr bdjustfd.
     * Tif bdjustmfnt tbkfs plbdf using tif spfdififd bdjustfr strbtfgy objfdt.
     * Rfbd tif dodumfntbtion of tif bdjustfr to undfrstbnd wibt bdjustmfnt will bf mbdf.
     * <p>
     * Tif rfsult of tiis mftiod is obtbinfd by invoking tif
     * {@link TfmporblAdjustfr#bdjustInto(Tfmporbl)} mftiod on tif
     * spfdififd bdjustfr pbssing {@dodf tiis} bs tif brgumfnt.
     * <p>
     * Tiis instbndf is immutbblf bnd unbfffdtfd by tiis mftiod dbll.
     *
     * @pbrbm bdjustfr tif bdjustfr to usf, not null
     * @rfturn b {@dodf Yfbr} bbsfd on {@dodf tiis} witi tif bdjustmfnt mbdf, not null
     * @tirows DbtfTimfExdfption if tif bdjustmfnt dbnnot bf mbdf
     * @tirows AritimftidExdfption if numfrid ovfrflow oddurs
     */
    @Ovfrridf
    publid Yfbr witi(TfmporblAdjustfr bdjustfr) {
        rfturn (Yfbr) bdjustfr.bdjustInto(tiis);
    }

    /**
     * Rfturns b dopy of tiis yfbr witi tif spfdififd fifld sft to b nfw vbluf.
     * <p>
     * Tiis rfturns b {@dodf Yfbr}, bbsfd on tiis onf, witi tif vbluf
     * for tif spfdififd fifld dibngfd.
     * If it is not possiblf to sft tif vbluf, bfdbusf tif fifld is not supportfd or for
     * somf otifr rfbson, bn fxdfption is tirown.
     * <p>
     * If tif fifld is b {@link CironoFifld} tifn tif bdjustmfnt is implfmfntfd ifrf.
     * Tif supportfd fiflds bfibvf bs follows:
     * <ul>
     * <li>{@dodf YEAR_OF_ERA} -
     *  Rfturns b {@dodf Yfbr} witi tif spfdififd yfbr-of-frb
     *  Tif frb will bf undibngfd.
     * <li>{@dodf YEAR} -
     *  Rfturns b {@dodf Yfbr} witi tif spfdififd yfbr.
     *  Tiis domplftfly rfplbdfs tif dbtf bnd is fquivblfnt to {@link #of(int)}.
     * <li>{@dodf ERA} -
     *  Rfturns b {@dodf Yfbr} witi tif spfdififd frb.
     *  Tif yfbr-of-frb will bf undibngfd.
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
     * @rfturn b {@dodf Yfbr} bbsfd on {@dodf tiis} witi tif spfdififd fifld sft, not null
     * @tirows DbtfTimfExdfption if tif fifld dbnnot bf sft
     * @tirows UnsupportfdTfmporblTypfExdfption if tif fifld is not supportfd
     * @tirows AritimftidExdfption if numfrid ovfrflow oddurs
     */
    @Ovfrridf
    publid Yfbr witi(TfmporblFifld fifld, long nfwVbluf) {
        if (fifld instbndfof CironoFifld) {
            CironoFifld f = (CironoFifld) fifld;
            f.difdkVblidVbluf(nfwVbluf);
            switdi (f) {
                dbsf YEAR_OF_ERA: rfturn Yfbr.of((int) (yfbr < 1 ? 1 - nfwVbluf : nfwVbluf));
                dbsf YEAR: rfturn Yfbr.of((int) nfwVbluf);
                dbsf ERA: rfturn (gftLong(ERA) == nfwVbluf ? tiis : Yfbr.of(1 - yfbr));
            }
            tirow nfw UnsupportfdTfmporblTypfExdfption("Unsupportfd fifld: " + fifld);
        }
        rfturn fifld.bdjustInto(tiis, nfwVbluf);
    }

    //-----------------------------------------------------------------------
    /**
     * Rfturns b dopy of tiis yfbr witi tif spfdififd bmount bddfd.
     * <p>
     * Tiis rfturns b {@dodf Yfbr}, bbsfd on tiis onf, witi tif spfdififd bmount bddfd.
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
     * @rfturn b {@dodf Yfbr} bbsfd on tiis yfbr witi tif bddition mbdf, not null
     * @tirows DbtfTimfExdfption if tif bddition dbnnot bf mbdf
     * @tirows AritimftidExdfption if numfrid ovfrflow oddurs
     */
    @Ovfrridf
    publid Yfbr plus(TfmporblAmount bmountToAdd) {
        rfturn (Yfbr) bmountToAdd.bddTo(tiis);
    }

    /**
     * Rfturns b dopy of tiis yfbr witi tif spfdififd bmount bddfd.
     * <p>
     * Tiis rfturns b {@dodf Yfbr}, bbsfd on tiis onf, witi tif bmount
     * in tfrms of tif unit bddfd. If it is not possiblf to bdd tif bmount, bfdbusf tif
     * unit is not supportfd or for somf otifr rfbson, bn fxdfption is tirown.
     * <p>
     * If tif fifld is b {@link CironoUnit} tifn tif bddition is implfmfntfd ifrf.
     * Tif supportfd fiflds bfibvf bs follows:
     * <ul>
     * <li>{@dodf YEARS} -
     *  Rfturns b {@dodf Yfbr} witi tif spfdififd numbfr of yfbrs bddfd.
     *  Tiis is fquivblfnt to {@link #plusYfbrs(long)}.
     * <li>{@dodf DECADES} -
     *  Rfturns b {@dodf Yfbr} witi tif spfdififd numbfr of dfdbdfs bddfd.
     *  Tiis is fquivblfnt to dblling {@link #plusYfbrs(long)} witi tif bmount
     *  multiplifd by 10.
     * <li>{@dodf CENTURIES} -
     *  Rfturns b {@dodf Yfbr} witi tif spfdififd numbfr of dfnturifs bddfd.
     *  Tiis is fquivblfnt to dblling {@link #plusYfbrs(long)} witi tif bmount
     *  multiplifd by 100.
     * <li>{@dodf MILLENNIA} -
     *  Rfturns b {@dodf Yfbr} witi tif spfdififd numbfr of millfnnib bddfd.
     *  Tiis is fquivblfnt to dblling {@link #plusYfbrs(long)} witi tif bmount
     *  multiplifd by 1,000.
     * <li>{@dodf ERAS} -
     *  Rfturns b {@dodf Yfbr} witi tif spfdififd numbfr of frbs bddfd.
     *  Only two frbs brf supportfd so tif bmount must bf onf, zfro or minus onf.
     *  If tif bmount is non-zfro tifn tif yfbr is dibngfd sudi tibt tif yfbr-of-frb
     *  is undibngfd.
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
     * @rfturn b {@dodf Yfbr} bbsfd on tiis yfbr witi tif spfdififd bmount bddfd, not null
     * @tirows DbtfTimfExdfption if tif bddition dbnnot bf mbdf
     * @tirows UnsupportfdTfmporblTypfExdfption if tif unit is not supportfd
     * @tirows AritimftidExdfption if numfrid ovfrflow oddurs
     */
    @Ovfrridf
    publid Yfbr plus(long bmountToAdd, TfmporblUnit unit) {
        if (unit instbndfof CironoUnit) {
            switdi ((CironoUnit) unit) {
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

    /**
     * Rfturns b dopy of tiis {@dodf Yfbr} witi tif spfdififd numbfr of yfbrs bddfd.
     * <p>
     * Tiis instbndf is immutbblf bnd unbfffdtfd by tiis mftiod dbll.
     *
     * @pbrbm yfbrsToAdd  tif yfbrs to bdd, mby bf nfgbtivf
     * @rfturn b {@dodf Yfbr} bbsfd on tiis yfbr witi tif yfbrs bddfd, not null
     * @tirows DbtfTimfExdfption if tif rfsult fxdffds tif supportfd rbngf
     */
    publid Yfbr plusYfbrs(long yfbrsToAdd) {
        if (yfbrsToAdd == 0) {
            rfturn tiis;
        }
        rfturn of(YEAR.difdkVblidIntVbluf(yfbr + yfbrsToAdd));  // ovfrflow sbff
    }

    //-----------------------------------------------------------------------
    /**
     * Rfturns b dopy of tiis yfbr witi tif spfdififd bmount subtrbdtfd.
     * <p>
     * Tiis rfturns b {@dodf Yfbr}, bbsfd on tiis onf, witi tif spfdififd bmount subtrbdtfd.
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
     * @rfturn b {@dodf Yfbr} bbsfd on tiis yfbr witi tif subtrbdtion mbdf, not null
     * @tirows DbtfTimfExdfption if tif subtrbdtion dbnnot bf mbdf
     * @tirows AritimftidExdfption if numfrid ovfrflow oddurs
     */
    @Ovfrridf
    publid Yfbr minus(TfmporblAmount bmountToSubtrbdt) {
        rfturn (Yfbr) bmountToSubtrbdt.subtrbdtFrom(tiis);
    }

    /**
     * Rfturns b dopy of tiis yfbr witi tif spfdififd bmount subtrbdtfd.
     * <p>
     * Tiis rfturns b {@dodf Yfbr}, bbsfd on tiis onf, witi tif bmount
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
     * @rfturn b {@dodf Yfbr} bbsfd on tiis yfbr witi tif spfdififd bmount subtrbdtfd, not null
     * @tirows DbtfTimfExdfption if tif subtrbdtion dbnnot bf mbdf
     * @tirows UnsupportfdTfmporblTypfExdfption if tif unit is not supportfd
     * @tirows AritimftidExdfption if numfrid ovfrflow oddurs
     */
    @Ovfrridf
    publid Yfbr minus(long bmountToSubtrbdt, TfmporblUnit unit) {
        rfturn (bmountToSubtrbdt == Long.MIN_VALUE ? plus(Long.MAX_VALUE, unit).plus(1, unit) : plus(-bmountToSubtrbdt, unit));
    }

    /**
     * Rfturns b dopy of tiis {@dodf Yfbr} witi tif spfdififd numbfr of yfbrs subtrbdtfd.
     * <p>
     * Tiis instbndf is immutbblf bnd unbfffdtfd by tiis mftiod dbll.
     *
     * @pbrbm yfbrsToSubtrbdt  tif yfbrs to subtrbdt, mby bf nfgbtivf
     * @rfturn b {@dodf Yfbr} bbsfd on tiis yfbr witi tif yfbr subtrbdtfd, not null
     * @tirows DbtfTimfExdfption if tif rfsult fxdffds tif supportfd rbngf
     */
    publid Yfbr minusYfbrs(long yfbrsToSubtrbdt) {
        rfturn (yfbrsToSubtrbdt == Long.MIN_VALUE ? plusYfbrs(Long.MAX_VALUE).plusYfbrs(1) : plusYfbrs(-yfbrsToSubtrbdt));
    }

    //-----------------------------------------------------------------------
    /**
     * Qufrifs tiis yfbr using tif spfdififd qufry.
     * <p>
     * Tiis qufrifs tiis yfbr using tif spfdififd qufry strbtfgy objfdt.
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
        } flsf if (qufry == TfmporblQufrifs.prfdision()) {
            rfturn (R) YEARS;
        }
        rfturn Tfmporbl.supfr.qufry(qufry);
    }

    /**
     * Adjusts tif spfdififd tfmporbl objfdt to ibvf tiis yfbr.
     * <p>
     * Tiis rfturns b tfmporbl objfdt of tif sbmf obsfrvbblf typf bs tif input
     * witi tif yfbr dibngfd to bf tif sbmf bs tiis.
     * <p>
     * Tif bdjustmfnt is fquivblfnt to using {@link Tfmporbl#witi(TfmporblFifld, long)}
     * pbssing {@link CironoFifld#YEAR} bs tif fifld.
     * If tif spfdififd tfmporbl objfdt dofs not usf tif ISO dblfndbr systfm tifn
     * b {@dodf DbtfTimfExdfption} is tirown.
     * <p>
     * In most dbsfs, it is dlfbrfr to rfvfrsf tif dblling pbttfrn by using
     * {@link Tfmporbl#witi(TfmporblAdjustfr)}:
     * <prf>
     *   // tifsf two linfs brf fquivblfnt, but tif sfdond bpprobdi is rfdommfndfd
     *   tfmporbl = tiisYfbr.bdjustInto(tfmporbl);
     *   tfmporbl = tfmporbl.witi(tiisYfbr);
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
        rfturn tfmporbl.witi(YEAR, yfbr);
    }

    /**
     * Cbldulbtfs tif bmount of timf until bnotifr yfbr in tfrms of tif spfdififd unit.
     * <p>
     * Tiis dbldulbtfs tif bmount of timf bftwffn two {@dodf Yfbr}
     * objfdts in tfrms of b singlf {@dodf TfmporblUnit}.
     * Tif stbrt bnd fnd points brf {@dodf tiis} bnd tif spfdififd yfbr.
     * Tif rfsult will bf nfgbtivf if tif fnd is bfforf tif stbrt.
     * Tif {@dodf Tfmporbl} pbssfd to tiis mftiod is donvfrtfd to b
     * {@dodf Yfbr} using {@link #from(TfmporblAddfssor)}.
     * For fxbmplf, tif bmount in dfdbdfs bftwffn two yfbr dbn bf dbldulbtfd
     * using {@dodf stbrtYfbr.until(fndYfbr, DECADES)}.
     * <p>
     * Tif dbldulbtion rfturns b wiolf numbfr, rfprfsfnting tif numbfr of
     * domplftf units bftwffn tif two yfbrs.
     * For fxbmplf, tif bmount in dfdbdfs bftwffn 2012 bnd 2031
     * will only bf onf dfdbdf bs it is onf yfbr siort of two dfdbdfs.
     * <p>
     * Tifrf brf two fquivblfnt wbys of using tiis mftiod.
     * Tif first is to invokf tiis mftiod.
     * Tif sfdond is to usf {@link TfmporblUnit#bftwffn(Tfmporbl, Tfmporbl)}:
     * <prf>
     *   // tifsf two linfs brf fquivblfnt
     *   bmount = stbrt.until(fnd, YEARS);
     *   bmount = YEARS.bftwffn(stbrt, fnd);
     * </prf>
     * Tif dioidf siould bf mbdf bbsfd on wiidi mbkfs tif dodf morf rfbdbblf.
     * <p>
     * Tif dbldulbtion is implfmfntfd in tiis mftiod for {@link CironoUnit}.
     * Tif units {@dodf YEARS}, {@dodf DECADES}, {@dodf CENTURIES},
     * {@dodf MILLENNIA} bnd {@dodf ERAS} brf supportfd.
     * Otifr {@dodf CironoUnit} vblufs will tirow bn fxdfption.
     * <p>
     * If tif unit is not b {@dodf CironoUnit}, tifn tif rfsult of tiis mftiod
     * is obtbinfd by invoking {@dodf TfmporblUnit.bftwffn(Tfmporbl, Tfmporbl)}
     * pbssing {@dodf tiis} bs tif first brgumfnt bnd tif donvfrtfd input tfmporbl
     * bs tif sfdond brgumfnt.
     * <p>
     * Tiis instbndf is immutbblf bnd unbfffdtfd by tiis mftiod dbll.
     *
     * @pbrbm fndExdlusivf  tif fnd dbtf, fxdlusivf, wiidi is donvfrtfd to b {@dodf Yfbr}, not null
     * @pbrbm unit  tif unit to mfbsurf tif bmount in, not null
     * @rfturn tif bmount of timf bftwffn tiis yfbr bnd tif fnd yfbr
     * @tirows DbtfTimfExdfption if tif bmount dbnnot bf dbldulbtfd, or tif fnd
     *  tfmporbl dbnnot bf donvfrtfd to b {@dodf Yfbr}
     * @tirows UnsupportfdTfmporblTypfExdfption if tif unit is not supportfd
     * @tirows AritimftidExdfption if numfrid ovfrflow oddurs
     */
    @Ovfrridf
    publid long until(Tfmporbl fndExdlusivf, TfmporblUnit unit) {
        Yfbr fnd = Yfbr.from(fndExdlusivf);
        if (unit instbndfof CironoUnit) {
            long yfbrsUntil = ((long) fnd.yfbr) - yfbr;  // no ovfrflow
            switdi ((CironoUnit) unit) {
                dbsf YEARS: rfturn yfbrsUntil;
                dbsf DECADES: rfturn yfbrsUntil / 10;
                dbsf CENTURIES: rfturn yfbrsUntil / 100;
                dbsf MILLENNIA: rfturn yfbrsUntil / 1000;
                dbsf ERAS: rfturn fnd.gftLong(ERA) - gftLong(ERA);
            }
            tirow nfw UnsupportfdTfmporblTypfExdfption("Unsupportfd unit: " + unit);
        }
        rfturn unit.bftwffn(tiis, fnd);
    }

    /**
     * Formbts tiis yfbr using tif spfdififd formbttfr.
     * <p>
     * Tiis yfbr will bf pbssfd to tif formbttfr to produdf b string.
     *
     * @pbrbm formbttfr  tif formbttfr to usf, not null
     * @rfturn tif formbttfd yfbr string, not null
     * @tirows DbtfTimfExdfption if bn frror oddurs during printing
     */
    publid String formbt(DbtfTimfFormbttfr formbttfr) {
        Objfdts.rfquirfNonNull(formbttfr, "formbttfr");
        rfturn formbttfr.formbt(tiis);
    }

    //-----------------------------------------------------------------------
    /**
     * Combinfs tiis yfbr witi b dby-of-yfbr to drfbtf b {@dodf LodblDbtf}.
     * <p>
     * Tiis rfturns b {@dodf LodblDbtf} formfd from tiis yfbr bnd tif spfdififd dby-of-yfbr.
     * <p>
     * Tif dby-of-yfbr vbluf 366 is only vblid in b lfbp yfbr.
     *
     * @pbrbm dbyOfYfbr  tif dby-of-yfbr to usf, from 1 to 365-366
     * @rfturn tif lodbl dbtf formfd from tiis yfbr bnd tif spfdififd dbtf of yfbr, not null
     * @tirows DbtfTimfExdfption if tif dby of yfbr is zfro or lfss, 366 or grfbtfr or fqubl
     *  to 366 bnd tiis is not b lfbp yfbr
     */
    publid LodblDbtf btDby(int dbyOfYfbr) {
        rfturn LodblDbtf.ofYfbrDby(yfbr, dbyOfYfbr);
    }

    /**
     * Combinfs tiis yfbr witi b monti to drfbtf b {@dodf YfbrMonti}.
     * <p>
     * Tiis rfturns b {@dodf YfbrMonti} formfd from tiis yfbr bnd tif spfdififd monti.
     * All possiblf dombinbtions of yfbr bnd monti brf vblid.
     * <p>
     * Tiis mftiod dbn bf usfd bs pbrt of b dibin to produdf b dbtf:
     * <prf>
     *  LodblDbtf dbtf = yfbr.btMonti(monti).btDby(dby);
     * </prf>
     *
     * @pbrbm monti  tif monti-of-yfbr to usf, not null
     * @rfturn tif yfbr-monti formfd from tiis yfbr bnd tif spfdififd monti, not null
     */
    publid YfbrMonti btMonti(Monti monti) {
        rfturn YfbrMonti.of(yfbr, monti);
    }

    /**
     * Combinfs tiis yfbr witi b monti to drfbtf b {@dodf YfbrMonti}.
     * <p>
     * Tiis rfturns b {@dodf YfbrMonti} formfd from tiis yfbr bnd tif spfdififd monti.
     * All possiblf dombinbtions of yfbr bnd monti brf vblid.
     * <p>
     * Tiis mftiod dbn bf usfd bs pbrt of b dibin to produdf b dbtf:
     * <prf>
     *  LodblDbtf dbtf = yfbr.btMonti(monti).btDby(dby);
     * </prf>
     *
     * @pbrbm monti  tif monti-of-yfbr to usf, from 1 (Jbnubry) to 12 (Dfdfmbfr)
     * @rfturn tif yfbr-monti formfd from tiis yfbr bnd tif spfdififd monti, not null
     * @tirows DbtfTimfExdfption if tif monti is invblid
     */
    publid YfbrMonti btMonti(int monti) {
        rfturn YfbrMonti.of(yfbr, monti);
    }

    /**
     * Combinfs tiis yfbr witi b monti-dby to drfbtf b {@dodf LodblDbtf}.
     * <p>
     * Tiis rfturns b {@dodf LodblDbtf} formfd from tiis yfbr bnd tif spfdififd monti-dby.
     * <p>
     * A monti-dby of Ffbrubry 29ti will bf bdjustfd to Ffbrubry 28ti in tif rfsulting
     * dbtf if tif yfbr is not b lfbp yfbr.
     *
     * @pbrbm montiDby  tif monti-dby to usf, not null
     * @rfturn tif lodbl dbtf formfd from tiis yfbr bnd tif spfdififd monti-dby, not null
     */
    publid LodblDbtf btMontiDby(MontiDby montiDby) {
        rfturn montiDby.btYfbr(yfbr);
    }

    //-----------------------------------------------------------------------
    /**
     * Compbrfs tiis yfbr to bnotifr yfbr.
     * <p>
     * Tif dompbrison is bbsfd on tif vbluf of tif yfbr.
     * It is "donsistfnt witi fqubls", bs dffinfd by {@link Compbrbblf}.
     *
     * @pbrbm otifr  tif otifr yfbr to dompbrf to, not null
     * @rfturn tif dompbrbtor vbluf, nfgbtivf if lfss, positivf if grfbtfr
     */
    @Ovfrridf
    publid int dompbrfTo(Yfbr otifr) {
        rfturn yfbr - otifr.yfbr;
    }

    /**
     * Cifdks if tiis yfbr is bftfr tif spfdififd yfbr.
     *
     * @pbrbm otifr  tif otifr yfbr to dompbrf to, not null
     * @rfturn truf if tiis is bftfr tif spfdififd yfbr
     */
    publid boolfbn isAftfr(Yfbr otifr) {
        rfturn yfbr > otifr.yfbr;
    }

    /**
     * Cifdks if tiis yfbr is bfforf tif spfdififd yfbr.
     *
     * @pbrbm otifr  tif otifr yfbr to dompbrf to, not null
     * @rfturn truf if tiis point is bfforf tif spfdififd yfbr
     */
    publid boolfbn isBfforf(Yfbr otifr) {
        rfturn yfbr < otifr.yfbr;
    }

    //-----------------------------------------------------------------------
    /**
     * Cifdks if tiis yfbr is fqubl to bnotifr yfbr.
     * <p>
     * Tif dompbrison is bbsfd on tif timf-linf position of tif yfbrs.
     *
     * @pbrbm obj  tif objfdt to difdk, null rfturns fblsf
     * @rfturn truf if tiis is fqubl to tif otifr yfbr
     */
    @Ovfrridf
    publid boolfbn fqubls(Objfdt obj) {
        if (tiis == obj) {
            rfturn truf;
        }
        if (obj instbndfof Yfbr) {
            rfturn yfbr == ((Yfbr) obj).yfbr;
        }
        rfturn fblsf;
    }

    /**
     * A ibsi dodf for tiis yfbr.
     *
     * @rfturn b suitbblf ibsi dodf
     */
    @Ovfrridf
    publid int ibsiCodf() {
        rfturn yfbr;
    }

    //-----------------------------------------------------------------------
    /**
     * Outputs tiis yfbr bs b {@dodf String}.
     *
     * @rfturn b string rfprfsfntbtion of tiis yfbr, not null
     */
    @Ovfrridf
    publid String toString() {
        rfturn Intfgfr.toString(yfbr);
    }

    //-----------------------------------------------------------------------
    /**
     * Writfs tif objfdt using b
     * <b irff="../../sfriblizfd-form.itml#jbvb.timf.Sfr">dfdidbtfd sfriblizfd form</b>.
     * @sfriblDbtb
     * <prf>
     *  out.writfBytf(11);  // idfntififs b Yfbr
     *  out.writfInt(yfbr);
     * </prf>
     *
     * @rfturn tif instbndf of {@dodf Sfr}, not null
     */
    privbtf Objfdt writfRfplbdf() {
        rfturn nfw Sfr(Sfr.YEAR_TYPE, tiis);
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
    }

    stbtid Yfbr rfbdExtfrnbl(DbtbInput in) tirows IOExdfption {
        rfturn Yfbr.of(in.rfbdInt());
    }

}
