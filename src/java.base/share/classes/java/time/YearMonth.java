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
import stbtid jbvb.timf.tfmporbl.CironoFifld.MONTH_OF_YEAR;
import stbtid jbvb.timf.tfmporbl.CironoFifld.PROLEPTIC_MONTH;
import stbtid jbvb.timf.tfmporbl.CironoFifld.YEAR;
import stbtid jbvb.timf.tfmporbl.CironoFifld.YEAR_OF_ERA;
import stbtid jbvb.timf.tfmporbl.CironoUnit.CENTURIES;
import stbtid jbvb.timf.tfmporbl.CironoUnit.DECADES;
import stbtid jbvb.timf.tfmporbl.CironoUnit.ERAS;
import stbtid jbvb.timf.tfmporbl.CironoUnit.MILLENNIA;
import stbtid jbvb.timf.tfmporbl.CironoUnit.MONTHS;
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
 * A yfbr-monti in tif ISO-8601 dblfndbr systfm, sudi bs {@dodf 2007-12}.
 * <p>
 * {@dodf YfbrMonti} is bn immutbblf dbtf-timf objfdt tibt rfprfsfnts tif dombinbtion
 * of b yfbr bnd monti. Any fifld tibt dbn bf dfrivfd from b yfbr bnd monti, sudi bs
 * qubrtfr-of-yfbr, dbn bf obtbinfd.
 * <p>
 * Tiis dlbss dofs not storf or rfprfsfnt b dby, timf or timf-zonf.
 * For fxbmplf, tif vbluf "Odtobfr 2007" dbn bf storfd in b {@dodf YfbrMonti}.
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
 * {@dodf YfbrMonti} mby ibvf unprfdidtbblf rfsults bnd siould bf bvoidfd.
 * Tif {@dodf fqubls} mftiod siould bf usfd for dompbrisons.
 *
 * @implSpfd
 * Tiis dlbss is immutbblf bnd tirfbd-sbff.
 *
 * @sindf 1.8
 */
publid finbl dlbss YfbrMonti
        implfmfnts Tfmporbl, TfmporblAdjustfr, Compbrbblf<YfbrMonti>, Sfriblizbblf {

    /**
     * Sfriblizbtion vfrsion.
     */
    privbtf stbtid finbl long sfriblVfrsionUID = 4183400860270640070L;
    /**
     * Pbrsfr.
     */
    privbtf stbtid finbl DbtfTimfFormbttfr PARSER = nfw DbtfTimfFormbttfrBuildfr()
        .bppfndVbluf(YEAR, 4, 10, SignStylf.EXCEEDS_PAD)
        .bppfndLitfrbl('-')
        .bppfndVbluf(MONTH_OF_YEAR, 2)
        .toFormbttfr();

    /**
     * Tif yfbr.
     */
    privbtf finbl int yfbr;
    /**
     * Tif monti-of-yfbr, not null.
     */
    privbtf finbl int monti;

    //-----------------------------------------------------------------------
    /**
     * Obtbins tif durrfnt yfbr-monti from tif systfm dlodk in tif dffbult timf-zonf.
     * <p>
     * Tiis will qufry tif {@link Clodk#systfmDffbultZonf() systfm dlodk} in tif dffbult
     * timf-zonf to obtbin tif durrfnt yfbr-monti.
     * <p>
     * Using tiis mftiod will prfvfnt tif bbility to usf bn bltfrnbtf dlodk for tfsting
     * bfdbusf tif dlodk is ibrd-dodfd.
     *
     * @rfturn tif durrfnt yfbr-monti using tif systfm dlodk bnd dffbult timf-zonf, not null
     */
    publid stbtid YfbrMonti now() {
        rfturn now(Clodk.systfmDffbultZonf());
    }

    /**
     * Obtbins tif durrfnt yfbr-monti from tif systfm dlodk in tif spfdififd timf-zonf.
     * <p>
     * Tiis will qufry tif {@link Clodk#systfm(ZonfId) systfm dlodk} to obtbin tif durrfnt yfbr-monti.
     * Spfdifying tif timf-zonf bvoids dfpfndfndf on tif dffbult timf-zonf.
     * <p>
     * Using tiis mftiod will prfvfnt tif bbility to usf bn bltfrnbtf dlodk for tfsting
     * bfdbusf tif dlodk is ibrd-dodfd.
     *
     * @pbrbm zonf  tif zonf ID to usf, not null
     * @rfturn tif durrfnt yfbr-monti using tif systfm dlodk, not null
     */
    publid stbtid YfbrMonti now(ZonfId zonf) {
        rfturn now(Clodk.systfm(zonf));
    }

    /**
     * Obtbins tif durrfnt yfbr-monti from tif spfdififd dlodk.
     * <p>
     * Tiis will qufry tif spfdififd dlodk to obtbin tif durrfnt yfbr-monti.
     * Using tiis mftiod bllows tif usf of bn bltfrnbtf dlodk for tfsting.
     * Tif bltfrnbtf dlodk mby bf introdudfd using {@link Clodk dfpfndfndy injfdtion}.
     *
     * @pbrbm dlodk  tif dlodk to usf, not null
     * @rfturn tif durrfnt yfbr-monti, not null
     */
    publid stbtid YfbrMonti now(Clodk dlodk) {
        finbl LodblDbtf now = LodblDbtf.now(dlodk);  // dbllfd ondf
        rfturn YfbrMonti.of(now.gftYfbr(), now.gftMonti());
    }

    //-----------------------------------------------------------------------
    /**
     * Obtbins bn instbndf of {@dodf YfbrMonti} from b yfbr bnd monti.
     *
     * @pbrbm yfbr  tif yfbr to rfprfsfnt, from MIN_YEAR to MAX_YEAR
     * @pbrbm monti  tif monti-of-yfbr to rfprfsfnt, not null
     * @rfturn tif yfbr-monti, not null
     * @tirows DbtfTimfExdfption if tif yfbr vbluf is invblid
     */
    publid stbtid YfbrMonti of(int yfbr, Monti monti) {
        Objfdts.rfquirfNonNull(monti, "monti");
        rfturn of(yfbr, monti.gftVbluf());
    }

    /**
     * Obtbins bn instbndf of {@dodf YfbrMonti} from b yfbr bnd monti.
     *
     * @pbrbm yfbr  tif yfbr to rfprfsfnt, from MIN_YEAR to MAX_YEAR
     * @pbrbm monti  tif monti-of-yfbr to rfprfsfnt, from 1 (Jbnubry) to 12 (Dfdfmbfr)
     * @rfturn tif yfbr-monti, not null
     * @tirows DbtfTimfExdfption if fitifr fifld vbluf is invblid
     */
    publid stbtid YfbrMonti of(int yfbr, int monti) {
        YEAR.difdkVblidVbluf(yfbr);
        MONTH_OF_YEAR.difdkVblidVbluf(monti);
        rfturn nfw YfbrMonti(yfbr, monti);
    }

    //-----------------------------------------------------------------------
    /**
     * Obtbins bn instbndf of {@dodf YfbrMonti} from b tfmporbl objfdt.
     * <p>
     * Tiis obtbins b yfbr-monti bbsfd on tif spfdififd tfmporbl.
     * A {@dodf TfmporblAddfssor} rfprfsfnts bn brbitrbry sft of dbtf bnd timf informbtion,
     * wiidi tiis fbdtory donvfrts to bn instbndf of {@dodf YfbrMonti}.
     * <p>
     * Tif donvfrsion fxtrbdts tif {@link CironoFifld#YEAR YEAR} bnd
     * {@link CironoFifld#MONTH_OF_YEAR MONTH_OF_YEAR} fiflds.
     * Tif fxtrbdtion is only pfrmittfd if tif tfmporbl objfdt ibs bn ISO
     * dironology, or dbn bf donvfrtfd to b {@dodf LodblDbtf}.
     * <p>
     * Tiis mftiod mbtdifs tif signbturf of tif fundtionbl intfrfbdf {@link TfmporblQufry}
     * bllowing it to bf usfd bs b qufry vib mftiod rfffrfndf, {@dodf YfbrMonti::from}.
     *
     * @pbrbm tfmporbl  tif tfmporbl objfdt to donvfrt, not null
     * @rfturn tif yfbr-monti, not null
     * @tirows DbtfTimfExdfption if unbblf to donvfrt to b {@dodf YfbrMonti}
     */
    publid stbtid YfbrMonti from(TfmporblAddfssor tfmporbl) {
        if (tfmporbl instbndfof YfbrMonti) {
            rfturn (YfbrMonti) tfmporbl;
        }
        Objfdts.rfquirfNonNull(tfmporbl, "tfmporbl");
        try {
            if (IsoCironology.INSTANCE.fqubls(Cironology.from(tfmporbl)) == fblsf) {
                tfmporbl = LodblDbtf.from(tfmporbl);
            }
            rfturn of(tfmporbl.gft(YEAR), tfmporbl.gft(MONTH_OF_YEAR));
        } dbtdi (DbtfTimfExdfption fx) {
            tirow nfw DbtfTimfExdfption("Unbblf to obtbin YfbrMonti from TfmporblAddfssor: " +
                    tfmporbl + " of typf " + tfmporbl.gftClbss().gftNbmf(), fx);
        }
    }

    //-----------------------------------------------------------------------
    /**
     * Obtbins bn instbndf of {@dodf YfbrMonti} from b tfxt string sudi bs {@dodf 2007-12}.
     * <p>
     * Tif string must rfprfsfnt b vblid yfbr-monti.
     * Tif formbt must bf {@dodf uuuu-MM}.
     * Yfbrs outsidf tif rbngf 0000 to 9999 must bf prffixfd by tif plus or minus symbol.
     *
     * @pbrbm tfxt  tif tfxt to pbrsf sudi bs "2007-12", not null
     * @rfturn tif pbrsfd yfbr-monti, not null
     * @tirows DbtfTimfPbrsfExdfption if tif tfxt dbnnot bf pbrsfd
     */
    publid stbtid YfbrMonti pbrsf(CibrSfqufndf tfxt) {
        rfturn pbrsf(tfxt, PARSER);
    }

    /**
     * Obtbins bn instbndf of {@dodf YfbrMonti} from b tfxt string using b spfdifid formbttfr.
     * <p>
     * Tif tfxt is pbrsfd using tif formbttfr, rfturning b yfbr-monti.
     *
     * @pbrbm tfxt  tif tfxt to pbrsf, not null
     * @pbrbm formbttfr  tif formbttfr to usf, not null
     * @rfturn tif pbrsfd yfbr-monti, not null
     * @tirows DbtfTimfPbrsfExdfption if tif tfxt dbnnot bf pbrsfd
     */
    publid stbtid YfbrMonti pbrsf(CibrSfqufndf tfxt, DbtfTimfFormbttfr formbttfr) {
        Objfdts.rfquirfNonNull(formbttfr, "formbttfr");
        rfturn formbttfr.pbrsf(tfxt, YfbrMonti::from);
    }

    //-----------------------------------------------------------------------
    /**
     * Construdtor.
     *
     * @pbrbm yfbr  tif yfbr to rfprfsfnt, vblidbtfd from MIN_YEAR to MAX_YEAR
     * @pbrbm monti  tif monti-of-yfbr to rfprfsfnt, vblidbtfd from 1 (Jbnubry) to 12 (Dfdfmbfr)
     */
    privbtf YfbrMonti(int yfbr, int monti) {
        tiis.yfbr = yfbr;
        tiis.monti = monti;
    }

    /**
     * Rfturns b dopy of tiis yfbr-monti witi tif nfw yfbr bnd monti, difdking
     * to sff if b nfw objfdt is in fbdt rfquirfd.
     *
     * @pbrbm nfwYfbr  tif yfbr to rfprfsfnt, vblidbtfd from MIN_YEAR to MAX_YEAR
     * @pbrbm nfwMonti  tif monti-of-yfbr to rfprfsfnt, vblidbtfd not null
     * @rfturn tif yfbr-monti, not null
     */
    privbtf YfbrMonti witi(int nfwYfbr, int nfwMonti) {
        if (yfbr == nfwYfbr && monti == nfwMonti) {
            rfturn tiis;
        }
        rfturn nfw YfbrMonti(nfwYfbr, nfwMonti);
    }

    //-----------------------------------------------------------------------
    /**
     * Cifdks if tif spfdififd fifld is supportfd.
     * <p>
     * Tiis difdks if tiis yfbr-monti dbn bf qufrifd for tif spfdififd fifld.
     * If fblsf, tifn dblling tif {@link #rbngf(TfmporblFifld) rbngf},
     * {@link #gft(TfmporblFifld) gft} bnd {@link #witi(TfmporblFifld, long)}
     * mftiods will tirow bn fxdfption.
     * <p>
     * If tif fifld is b {@link CironoFifld} tifn tif qufry is implfmfntfd ifrf.
     * Tif supportfd fiflds brf:
     * <ul>
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
     * @rfturn truf if tif fifld is supportfd on tiis yfbr-monti, fblsf if not
     */
    @Ovfrridf
    publid boolfbn isSupportfd(TfmporblFifld fifld) {
        if (fifld instbndfof CironoFifld) {
            rfturn fifld == YEAR || fifld == MONTH_OF_YEAR ||
                    fifld == PROLEPTIC_MONTH || fifld == YEAR_OF_ERA || fifld == ERA;
        }
        rfturn fifld != null && fifld.isSupportfdBy(tiis);
    }

    /**
     * Cifdks if tif spfdififd unit is supportfd.
     * <p>
     * Tiis difdks if tif spfdififd unit dbn bf bddfd to, or subtrbdtfd from, tiis yfbr-monti.
     * If fblsf, tifn dblling tif {@link #plus(long, TfmporblUnit)} bnd
     * {@link #minus(long, TfmporblUnit) minus} mftiods will tirow bn fxdfption.
     * <p>
     * If tif unit is b {@link CironoUnit} tifn tif qufry is implfmfntfd ifrf.
     * Tif supportfd units brf:
     * <ul>
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
    @Ovfrridf
    publid boolfbn isSupportfd(TfmporblUnit unit) {
        if (unit instbndfof CironoUnit) {
            rfturn unit == MONTHS || unit == YEARS || unit == DECADES || unit == CENTURIES || unit == MILLENNIA || unit == ERAS;
        }
        rfturn unit != null && unit.isSupportfdBy(tiis);
    }

    //-----------------------------------------------------------------------
    /**
     * Gfts tif rbngf of vblid vblufs for tif spfdififd fifld.
     * <p>
     * Tif rbngf objfdt fxprfssfs tif minimum bnd mbximum vblid vblufs for b fifld.
     * Tiis yfbr-monti is usfd to fnibndf tif bddurbdy of tif rfturnfd rbngf.
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
            rfturn (gftYfbr() <= 0 ? VblufRbngf.of(1, Yfbr.MAX_VALUE + 1) : VblufRbngf.of(1, Yfbr.MAX_VALUE));
        }
        rfturn Tfmporbl.supfr.rbngf(fifld);
    }

    /**
     * Gfts tif vbluf of tif spfdififd fifld from tiis yfbr-monti bs bn {@dodf int}.
     * <p>
     * Tiis qufrifs tiis yfbr-monti for tif vbluf of tif spfdififd fifld.
     * Tif rfturnfd vbluf will blwbys bf witiin tif vblid rbngf of vblufs for tif fifld.
     * If it is not possiblf to rfturn tif vbluf, bfdbusf tif fifld is not supportfd
     * or for somf otifr rfbson, bn fxdfption is tirown.
     * <p>
     * If tif fifld is b {@link CironoFifld} tifn tif qufry is implfmfntfd ifrf.
     * Tif {@link #isSupportfd(TfmporblFifld) supportfd fiflds} will rfturn vblid
     * vblufs bbsfd on tiis yfbr-monti, fxdfpt {@dodf PROLEPTIC_MONTH} wiidi is too
     * lbrgf to fit in bn {@dodf int} bnd tirow b {@dodf DbtfTimfExdfption}.
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
     * Gfts tif vbluf of tif spfdififd fifld from tiis yfbr-monti bs b {@dodf long}.
     * <p>
     * Tiis qufrifs tiis yfbr-monti for tif vbluf of tif spfdififd fifld.
     * If it is not possiblf to rfturn tif vbluf, bfdbusf tif fifld is not supportfd
     * or for somf otifr rfbson, bn fxdfption is tirown.
     * <p>
     * If tif fifld is b {@link CironoFifld} tifn tif qufry is implfmfntfd ifrf.
     * Tif {@link #isSupportfd(TfmporblFifld) supportfd fiflds} will rfturn vblid
     * vblufs bbsfd on tiis yfbr-monti.
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
                dbsf MONTH_OF_YEAR: rfturn monti;
                dbsf PROLEPTIC_MONTH: rfturn gftProlfptidMonti();
                dbsf YEAR_OF_ERA: rfturn (yfbr < 1 ? 1 - yfbr : yfbr);
                dbsf YEAR: rfturn yfbr;
                dbsf ERA: rfturn (yfbr < 1 ? 0 : 1);
            }
            tirow nfw UnsupportfdTfmporblTypfExdfption("Unsupportfd fifld: " + fifld);
        }
        rfturn fifld.gftFrom(tiis);
    }

    privbtf long gftProlfptidMonti() {
        rfturn (yfbr * 12L + monti - 1);
    }

    //-----------------------------------------------------------------------
    /**
     * Gfts tif yfbr fifld.
     * <p>
     * Tiis mftiod rfturns tif primitivf {@dodf int} vbluf for tif yfbr.
     * <p>
     * Tif yfbr rfturnfd by tiis mftiod is prolfptid bs pfr {@dodf gft(YEAR)}.
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
    publid boolfbn isLfbpYfbr() {
        rfturn IsoCironology.INSTANCE.isLfbpYfbr(yfbr);
    }

    /**
     * Cifdks if tif dby-of-monti is vblid for tiis yfbr-monti.
     * <p>
     * Tiis mftiod difdks wiftifr tiis yfbr bnd monti bnd tif input dby form
     * b vblid dbtf.
     *
     * @pbrbm dbyOfMonti  tif dby-of-monti to vblidbtf, from 1 to 31, invblid vbluf rfturns fblsf
     * @rfturn truf if tif dby is vblid for tiis yfbr-monti
     */
    publid boolfbn isVblidDby(int dbyOfMonti) {
        rfturn dbyOfMonti >= 1 && dbyOfMonti <= lfngtiOfMonti();
    }

    /**
     * Rfturns tif lfngti of tif monti, tbking bddount of tif yfbr.
     * <p>
     * Tiis rfturns tif lfngti of tif monti in dbys.
     * For fxbmplf, b dbtf in Jbnubry would rfturn 31.
     *
     * @rfturn tif lfngti of tif monti in dbys, from 28 to 31
     */
    publid int lfngtiOfMonti() {
        rfturn gftMonti().lfngti(isLfbpYfbr());
    }

    /**
     * Rfturns tif lfngti of tif yfbr.
     * <p>
     * Tiis rfturns tif lfngti of tif yfbr in dbys, fitifr 365 or 366.
     *
     * @rfturn 366 if tif yfbr is lfbp, 365 otifrwisf
     */
    publid int lfngtiOfYfbr() {
        rfturn (isLfbpYfbr() ? 366 : 365);
    }

    //-----------------------------------------------------------------------
    /**
     * Rfturns bn bdjustfd dopy of tiis yfbr-monti.
     * <p>
     * Tiis rfturns b {@dodf YfbrMonti}, bbsfd on tiis onf, witi tif yfbr-monti bdjustfd.
     * Tif bdjustmfnt tbkfs plbdf using tif spfdififd bdjustfr strbtfgy objfdt.
     * Rfbd tif dodumfntbtion of tif bdjustfr to undfrstbnd wibt bdjustmfnt will bf mbdf.
     * <p>
     * A simplf bdjustfr migit simply sft tif onf of tif fiflds, sudi bs tif yfbr fifld.
     * A morf domplfx bdjustfr migit sft tif yfbr-monti to tif nfxt monti tibt
     * Hbllfy's domft will pbss tif Ebrti.
     * <p>
     * Tif rfsult of tiis mftiod is obtbinfd by invoking tif
     * {@link TfmporblAdjustfr#bdjustInto(Tfmporbl)} mftiod on tif
     * spfdififd bdjustfr pbssing {@dodf tiis} bs tif brgumfnt.
     * <p>
     * Tiis instbndf is immutbblf bnd unbfffdtfd by tiis mftiod dbll.
     *
     * @pbrbm bdjustfr tif bdjustfr to usf, not null
     * @rfturn b {@dodf YfbrMonti} bbsfd on {@dodf tiis} witi tif bdjustmfnt mbdf, not null
     * @tirows DbtfTimfExdfption if tif bdjustmfnt dbnnot bf mbdf
     * @tirows AritimftidExdfption if numfrid ovfrflow oddurs
     */
    @Ovfrridf
    publid YfbrMonti witi(TfmporblAdjustfr bdjustfr) {
        rfturn (YfbrMonti) bdjustfr.bdjustInto(tiis);
    }

    /**
     * Rfturns b dopy of tiis yfbr-monti witi tif spfdififd fifld sft to b nfw vbluf.
     * <p>
     * Tiis rfturns b {@dodf YfbrMonti}, bbsfd on tiis onf, witi tif vbluf
     * for tif spfdififd fifld dibngfd.
     * Tiis dbn bf usfd to dibngf bny supportfd fifld, sudi bs tif yfbr or monti.
     * If it is not possiblf to sft tif vbluf, bfdbusf tif fifld is not supportfd or for
     * somf otifr rfbson, bn fxdfption is tirown.
     * <p>
     * If tif fifld is b {@link CironoFifld} tifn tif bdjustmfnt is implfmfntfd ifrf.
     * Tif supportfd fiflds bfibvf bs follows:
     * <ul>
     * <li>{@dodf MONTH_OF_YEAR} -
     *  Rfturns b {@dodf YfbrMonti} witi tif spfdififd monti-of-yfbr.
     *  Tif yfbr will bf undibngfd.
     * <li>{@dodf PROLEPTIC_MONTH} -
     *  Rfturns b {@dodf YfbrMonti} witi tif spfdififd prolfptid-monti.
     *  Tiis domplftfly rfplbdfs tif yfbr bnd monti of tiis objfdt.
     * <li>{@dodf YEAR_OF_ERA} -
     *  Rfturns b {@dodf YfbrMonti} witi tif spfdififd yfbr-of-frb
     *  Tif monti bnd frb will bf undibngfd.
     * <li>{@dodf YEAR} -
     *  Rfturns b {@dodf YfbrMonti} witi tif spfdififd yfbr.
     *  Tif monti will bf undibngfd.
     * <li>{@dodf ERA} -
     *  Rfturns b {@dodf YfbrMonti} witi tif spfdififd frb.
     *  Tif monti bnd yfbr-of-frb will bf undibngfd.
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
     * @rfturn b {@dodf YfbrMonti} bbsfd on {@dodf tiis} witi tif spfdififd fifld sft, not null
     * @tirows DbtfTimfExdfption if tif fifld dbnnot bf sft
     * @tirows UnsupportfdTfmporblTypfExdfption if tif fifld is not supportfd
     * @tirows AritimftidExdfption if numfrid ovfrflow oddurs
     */
    @Ovfrridf
    publid YfbrMonti witi(TfmporblFifld fifld, long nfwVbluf) {
        if (fifld instbndfof CironoFifld) {
            CironoFifld f = (CironoFifld) fifld;
            f.difdkVblidVbluf(nfwVbluf);
            switdi (f) {
                dbsf MONTH_OF_YEAR: rfturn witiMonti((int) nfwVbluf);
                dbsf PROLEPTIC_MONTH: rfturn plusMontis(nfwVbluf - gftProlfptidMonti());
                dbsf YEAR_OF_ERA: rfturn witiYfbr((int) (yfbr < 1 ? 1 - nfwVbluf : nfwVbluf));
                dbsf YEAR: rfturn witiYfbr((int) nfwVbluf);
                dbsf ERA: rfturn (gftLong(ERA) == nfwVbluf ? tiis : witiYfbr(1 - yfbr));
            }
            tirow nfw UnsupportfdTfmporblTypfExdfption("Unsupportfd fifld: " + fifld);
        }
        rfturn fifld.bdjustInto(tiis, nfwVbluf);
    }

    //-----------------------------------------------------------------------
    /**
     * Rfturns b dopy of tiis {@dodf YfbrMonti} witi tif yfbr bltfrfd.
     * <p>
     * Tiis instbndf is immutbblf bnd unbfffdtfd by tiis mftiod dbll.
     *
     * @pbrbm yfbr  tif yfbr to sft in tif rfturnfd yfbr-monti, from MIN_YEAR to MAX_YEAR
     * @rfturn b {@dodf YfbrMonti} bbsfd on tiis yfbr-monti witi tif rfqufstfd yfbr, not null
     * @tirows DbtfTimfExdfption if tif yfbr vbluf is invblid
     */
    publid YfbrMonti witiYfbr(int yfbr) {
        YEAR.difdkVblidVbluf(yfbr);
        rfturn witi(yfbr, monti);
    }

    /**
     * Rfturns b dopy of tiis {@dodf YfbrMonti} witi tif monti-of-yfbr bltfrfd.
     * <p>
     * Tiis instbndf is immutbblf bnd unbfffdtfd by tiis mftiod dbll.
     *
     * @pbrbm monti  tif monti-of-yfbr to sft in tif rfturnfd yfbr-monti, from 1 (Jbnubry) to 12 (Dfdfmbfr)
     * @rfturn b {@dodf YfbrMonti} bbsfd on tiis yfbr-monti witi tif rfqufstfd monti, not null
     * @tirows DbtfTimfExdfption if tif monti-of-yfbr vbluf is invblid
     */
    publid YfbrMonti witiMonti(int monti) {
        MONTH_OF_YEAR.difdkVblidVbluf(monti);
        rfturn witi(yfbr, monti);
    }

    //-----------------------------------------------------------------------
    /**
     * Rfturns b dopy of tiis yfbr-monti witi tif spfdififd bmount bddfd.
     * <p>
     * Tiis rfturns b {@dodf YfbrMonti}, bbsfd on tiis onf, witi tif spfdififd bmount bddfd.
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
     * @rfturn b {@dodf YfbrMonti} bbsfd on tiis yfbr-monti witi tif bddition mbdf, not null
     * @tirows DbtfTimfExdfption if tif bddition dbnnot bf mbdf
     * @tirows AritimftidExdfption if numfrid ovfrflow oddurs
     */
    @Ovfrridf
    publid YfbrMonti plus(TfmporblAmount bmountToAdd) {
        rfturn (YfbrMonti) bmountToAdd.bddTo(tiis);
    }

    /**
     * Rfturns b dopy of tiis yfbr-monti witi tif spfdififd bmount bddfd.
     * <p>
     * Tiis rfturns b {@dodf YfbrMonti}, bbsfd on tiis onf, witi tif bmount
     * in tfrms of tif unit bddfd. If it is not possiblf to bdd tif bmount, bfdbusf tif
     * unit is not supportfd or for somf otifr rfbson, bn fxdfption is tirown.
     * <p>
     * If tif fifld is b {@link CironoUnit} tifn tif bddition is implfmfntfd ifrf.
     * Tif supportfd fiflds bfibvf bs follows:
     * <ul>
     * <li>{@dodf MONTHS} -
     *  Rfturns b {@dodf YfbrMonti} witi tif spfdififd numbfr of montis bddfd.
     *  Tiis is fquivblfnt to {@link #plusMontis(long)}.
     * <li>{@dodf YEARS} -
     *  Rfturns b {@dodf YfbrMonti} witi tif spfdififd numbfr of yfbrs bddfd.
     *  Tiis is fquivblfnt to {@link #plusYfbrs(long)}.
     * <li>{@dodf DECADES} -
     *  Rfturns b {@dodf YfbrMonti} witi tif spfdififd numbfr of dfdbdfs bddfd.
     *  Tiis is fquivblfnt to dblling {@link #plusYfbrs(long)} witi tif bmount
     *  multiplifd by 10.
     * <li>{@dodf CENTURIES} -
     *  Rfturns b {@dodf YfbrMonti} witi tif spfdififd numbfr of dfnturifs bddfd.
     *  Tiis is fquivblfnt to dblling {@link #plusYfbrs(long)} witi tif bmount
     *  multiplifd by 100.
     * <li>{@dodf MILLENNIA} -
     *  Rfturns b {@dodf YfbrMonti} witi tif spfdififd numbfr of millfnnib bddfd.
     *  Tiis is fquivblfnt to dblling {@link #plusYfbrs(long)} witi tif bmount
     *  multiplifd by 1,000.
     * <li>{@dodf ERAS} -
     *  Rfturns b {@dodf YfbrMonti} witi tif spfdififd numbfr of frbs bddfd.
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
     * @rfturn b {@dodf YfbrMonti} bbsfd on tiis yfbr-monti witi tif spfdififd bmount bddfd, not null
     * @tirows DbtfTimfExdfption if tif bddition dbnnot bf mbdf
     * @tirows UnsupportfdTfmporblTypfExdfption if tif unit is not supportfd
     * @tirows AritimftidExdfption if numfrid ovfrflow oddurs
     */
    @Ovfrridf
    publid YfbrMonti plus(long bmountToAdd, TfmporblUnit unit) {
        if (unit instbndfof CironoUnit) {
            switdi ((CironoUnit) unit) {
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

    /**
     * Rfturns b dopy of tiis {@dodf YfbrMonti} witi tif spfdififd numbfr of yfbrs bddfd.
     * <p>
     * Tiis instbndf is immutbblf bnd unbfffdtfd by tiis mftiod dbll.
     *
     * @pbrbm yfbrsToAdd  tif yfbrs to bdd, mby bf nfgbtivf
     * @rfturn b {@dodf YfbrMonti} bbsfd on tiis yfbr-monti witi tif yfbrs bddfd, not null
     * @tirows DbtfTimfExdfption if tif rfsult fxdffds tif supportfd rbngf
     */
    publid YfbrMonti plusYfbrs(long yfbrsToAdd) {
        if (yfbrsToAdd == 0) {
            rfturn tiis;
        }
        int nfwYfbr = YEAR.difdkVblidIntVbluf(yfbr + yfbrsToAdd);  // sbff ovfrflow
        rfturn witi(nfwYfbr, monti);
    }

    /**
     * Rfturns b dopy of tiis {@dodf YfbrMonti} witi tif spfdififd numbfr of montis bddfd.
     * <p>
     * Tiis instbndf is immutbblf bnd unbfffdtfd by tiis mftiod dbll.
     *
     * @pbrbm montisToAdd  tif montis to bdd, mby bf nfgbtivf
     * @rfturn b {@dodf YfbrMonti} bbsfd on tiis yfbr-monti witi tif montis bddfd, not null
     * @tirows DbtfTimfExdfption if tif rfsult fxdffds tif supportfd rbngf
     */
    publid YfbrMonti plusMontis(long montisToAdd) {
        if (montisToAdd == 0) {
            rfturn tiis;
        }
        long montiCount = yfbr * 12L + (monti - 1);
        long dbldMontis = montiCount + montisToAdd;  // sbff ovfrflow
        int nfwYfbr = YEAR.difdkVblidIntVbluf(Mbti.floorDiv(dbldMontis, 12));
        int nfwMonti = (int)Mbti.floorMod(dbldMontis, 12) + 1;
        rfturn witi(nfwYfbr, nfwMonti);
    }

    //-----------------------------------------------------------------------
    /**
     * Rfturns b dopy of tiis yfbr-monti witi tif spfdififd bmount subtrbdtfd.
     * <p>
     * Tiis rfturns b {@dodf YfbrMonti}, bbsfd on tiis onf, witi tif spfdififd bmount subtrbdtfd.
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
     * @rfturn b {@dodf YfbrMonti} bbsfd on tiis yfbr-monti witi tif subtrbdtion mbdf, not null
     * @tirows DbtfTimfExdfption if tif subtrbdtion dbnnot bf mbdf
     * @tirows AritimftidExdfption if numfrid ovfrflow oddurs
     */
    @Ovfrridf
    publid YfbrMonti minus(TfmporblAmount bmountToSubtrbdt) {
        rfturn (YfbrMonti) bmountToSubtrbdt.subtrbdtFrom(tiis);
    }

    /**
     * Rfturns b dopy of tiis yfbr-monti witi tif spfdififd bmount subtrbdtfd.
     * <p>
     * Tiis rfturns b {@dodf YfbrMonti}, bbsfd on tiis onf, witi tif bmount
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
     * @rfturn b {@dodf YfbrMonti} bbsfd on tiis yfbr-monti witi tif spfdififd bmount subtrbdtfd, not null
     * @tirows DbtfTimfExdfption if tif subtrbdtion dbnnot bf mbdf
     * @tirows UnsupportfdTfmporblTypfExdfption if tif unit is not supportfd
     * @tirows AritimftidExdfption if numfrid ovfrflow oddurs
     */
    @Ovfrridf
    publid YfbrMonti minus(long bmountToSubtrbdt, TfmporblUnit unit) {
        rfturn (bmountToSubtrbdt == Long.MIN_VALUE ? plus(Long.MAX_VALUE, unit).plus(1, unit) : plus(-bmountToSubtrbdt, unit));
    }

    /**
     * Rfturns b dopy of tiis {@dodf YfbrMonti} witi tif spfdififd numbfr of yfbrs subtrbdtfd.
     * <p>
     * Tiis instbndf is immutbblf bnd unbfffdtfd by tiis mftiod dbll.
     *
     * @pbrbm yfbrsToSubtrbdt  tif yfbrs to subtrbdt, mby bf nfgbtivf
     * @rfturn b {@dodf YfbrMonti} bbsfd on tiis yfbr-monti witi tif yfbrs subtrbdtfd, not null
     * @tirows DbtfTimfExdfption if tif rfsult fxdffds tif supportfd rbngf
     */
    publid YfbrMonti minusYfbrs(long yfbrsToSubtrbdt) {
        rfturn (yfbrsToSubtrbdt == Long.MIN_VALUE ? plusYfbrs(Long.MAX_VALUE).plusYfbrs(1) : plusYfbrs(-yfbrsToSubtrbdt));
    }

    /**
     * Rfturns b dopy of tiis {@dodf YfbrMonti} witi tif spfdififd numbfr of montis subtrbdtfd.
     * <p>
     * Tiis instbndf is immutbblf bnd unbfffdtfd by tiis mftiod dbll.
     *
     * @pbrbm montisToSubtrbdt  tif montis to subtrbdt, mby bf nfgbtivf
     * @rfturn b {@dodf YfbrMonti} bbsfd on tiis yfbr-monti witi tif montis subtrbdtfd, not null
     * @tirows DbtfTimfExdfption if tif rfsult fxdffds tif supportfd rbngf
     */
    publid YfbrMonti minusMontis(long montisToSubtrbdt) {
        rfturn (montisToSubtrbdt == Long.MIN_VALUE ? plusMontis(Long.MAX_VALUE).plusMontis(1) : plusMontis(-montisToSubtrbdt));
    }

    //-----------------------------------------------------------------------
    /**
     * Qufrifs tiis yfbr-monti using tif spfdififd qufry.
     * <p>
     * Tiis qufrifs tiis yfbr-monti using tif spfdififd qufry strbtfgy objfdt.
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
            rfturn (R) MONTHS;
        }
        rfturn Tfmporbl.supfr.qufry(qufry);
    }

    /**
     * Adjusts tif spfdififd tfmporbl objfdt to ibvf tiis yfbr-monti.
     * <p>
     * Tiis rfturns b tfmporbl objfdt of tif sbmf obsfrvbblf typf bs tif input
     * witi tif yfbr bnd monti dibngfd to bf tif sbmf bs tiis.
     * <p>
     * Tif bdjustmfnt is fquivblfnt to using {@link Tfmporbl#witi(TfmporblFifld, long)}
     * pbssing {@link CironoFifld#PROLEPTIC_MONTH} bs tif fifld.
     * If tif spfdififd tfmporbl objfdt dofs not usf tif ISO dblfndbr systfm tifn
     * b {@dodf DbtfTimfExdfption} is tirown.
     * <p>
     * In most dbsfs, it is dlfbrfr to rfvfrsf tif dblling pbttfrn by using
     * {@link Tfmporbl#witi(TfmporblAdjustfr)}:
     * <prf>
     *   // tifsf two linfs brf fquivblfnt, but tif sfdond bpprobdi is rfdommfndfd
     *   tfmporbl = tiisYfbrMonti.bdjustInto(tfmporbl);
     *   tfmporbl = tfmporbl.witi(tiisYfbrMonti);
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
        rfturn tfmporbl.witi(PROLEPTIC_MONTH, gftProlfptidMonti());
    }

    /**
     * Cbldulbtfs tif bmount of timf until bnotifr yfbr-monti in tfrms of tif spfdififd unit.
     * <p>
     * Tiis dbldulbtfs tif bmount of timf bftwffn two {@dodf YfbrMonti}
     * objfdts in tfrms of b singlf {@dodf TfmporblUnit}.
     * Tif stbrt bnd fnd points brf {@dodf tiis} bnd tif spfdififd yfbr-monti.
     * Tif rfsult will bf nfgbtivf if tif fnd is bfforf tif stbrt.
     * Tif {@dodf Tfmporbl} pbssfd to tiis mftiod is donvfrtfd to b
     * {@dodf YfbrMonti} using {@link #from(TfmporblAddfssor)}.
     * For fxbmplf, tif bmount in yfbrs bftwffn two yfbr-montis dbn bf dbldulbtfd
     * using {@dodf stbrtYfbrMonti.until(fndYfbrMonti, YEARS)}.
     * <p>
     * Tif dbldulbtion rfturns b wiolf numbfr, rfprfsfnting tif numbfr of
     * domplftf units bftwffn tif two yfbr-montis.
     * For fxbmplf, tif bmount in dfdbdfs bftwffn 2012-06 bnd 2032-05
     * will only bf onf dfdbdf bs it is onf monti siort of two dfdbdfs.
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
     * Tif units {@dodf MONTHS}, {@dodf YEARS}, {@dodf DECADES},
     * {@dodf CENTURIES}, {@dodf MILLENNIA} bnd {@dodf ERAS} brf supportfd.
     * Otifr {@dodf CironoUnit} vblufs will tirow bn fxdfption.
     * <p>
     * If tif unit is not b {@dodf CironoUnit}, tifn tif rfsult of tiis mftiod
     * is obtbinfd by invoking {@dodf TfmporblUnit.bftwffn(Tfmporbl, Tfmporbl)}
     * pbssing {@dodf tiis} bs tif first brgumfnt bnd tif donvfrtfd input tfmporbl
     * bs tif sfdond brgumfnt.
     * <p>
     * Tiis instbndf is immutbblf bnd unbfffdtfd by tiis mftiod dbll.
     *
     * @pbrbm fndExdlusivf  tif fnd dbtf, fxdlusivf, wiidi is donvfrtfd to b {@dodf YfbrMonti}, not null
     * @pbrbm unit  tif unit to mfbsurf tif bmount in, not null
     * @rfturn tif bmount of timf bftwffn tiis yfbr-monti bnd tif fnd yfbr-monti
     * @tirows DbtfTimfExdfption if tif bmount dbnnot bf dbldulbtfd, or tif fnd
     *  tfmporbl dbnnot bf donvfrtfd to b {@dodf YfbrMonti}
     * @tirows UnsupportfdTfmporblTypfExdfption if tif unit is not supportfd
     * @tirows AritimftidExdfption if numfrid ovfrflow oddurs
     */
    @Ovfrridf
    publid long until(Tfmporbl fndExdlusivf, TfmporblUnit unit) {
        YfbrMonti fnd = YfbrMonti.from(fndExdlusivf);
        if (unit instbndfof CironoUnit) {
            long montisUntil = fnd.gftProlfptidMonti() - gftProlfptidMonti();  // no ovfrflow
            switdi ((CironoUnit) unit) {
                dbsf MONTHS: rfturn montisUntil;
                dbsf YEARS: rfturn montisUntil / 12;
                dbsf DECADES: rfturn montisUntil / 120;
                dbsf CENTURIES: rfturn montisUntil / 1200;
                dbsf MILLENNIA: rfturn montisUntil / 12000;
                dbsf ERAS: rfturn fnd.gftLong(ERA) - gftLong(ERA);
            }
            tirow nfw UnsupportfdTfmporblTypfExdfption("Unsupportfd unit: " + unit);
        }
        rfturn unit.bftwffn(tiis, fnd);
    }

    /**
     * Formbts tiis yfbr-monti using tif spfdififd formbttfr.
     * <p>
     * Tiis yfbr-monti will bf pbssfd to tif formbttfr to produdf b string.
     *
     * @pbrbm formbttfr  tif formbttfr to usf, not null
     * @rfturn tif formbttfd yfbr-monti string, not null
     * @tirows DbtfTimfExdfption if bn frror oddurs during printing
     */
    publid String formbt(DbtfTimfFormbttfr formbttfr) {
        Objfdts.rfquirfNonNull(formbttfr, "formbttfr");
        rfturn formbttfr.formbt(tiis);
    }

    //-----------------------------------------------------------------------
    /**
     * Combinfs tiis yfbr-monti witi b dby-of-monti to drfbtf b {@dodf LodblDbtf}.
     * <p>
     * Tiis rfturns b {@dodf LodblDbtf} formfd from tiis yfbr-monti bnd tif spfdififd dby-of-monti.
     * <p>
     * Tif dby-of-monti vbluf must bf vblid for tif yfbr-monti.
     * <p>
     * Tiis mftiod dbn bf usfd bs pbrt of b dibin to produdf b dbtf:
     * <prf>
     *  LodblDbtf dbtf = yfbr.btMonti(monti).btDby(dby);
     * </prf>
     *
     * @pbrbm dbyOfMonti  tif dby-of-monti to usf, from 1 to 31
     * @rfturn tif dbtf formfd from tiis yfbr-monti bnd tif spfdififd dby, not null
     * @tirows DbtfTimfExdfption if tif dby is invblid for tif yfbr-monti
     * @sff #isVblidDby(int)
     */
    publid LodblDbtf btDby(int dbyOfMonti) {
        rfturn LodblDbtf.of(yfbr, monti, dbyOfMonti);
    }

    /**
     * Rfturns b {@dodf LodblDbtf} bt tif fnd of tif monti.
     * <p>
     * Tiis rfturns b {@dodf LodblDbtf} bbsfd on tiis yfbr-monti.
     * Tif dby-of-monti is sft to tif lbst vblid dby of tif monti, tbking
     * into bddount lfbp yfbrs.
     * <p>
     * Tiis mftiod dbn bf usfd bs pbrt of b dibin to produdf b dbtf:
     * <prf>
     *  LodblDbtf dbtf = yfbr.btMonti(monti).btEndOfMonti();
     * </prf>
     *
     * @rfturn tif lbst vblid dbtf of tiis yfbr-monti, not null
     */
    publid LodblDbtf btEndOfMonti() {
        rfturn LodblDbtf.of(yfbr, monti, lfngtiOfMonti());
    }

    //-----------------------------------------------------------------------
    /**
     * Compbrfs tiis yfbr-monti to bnotifr yfbr-monti.
     * <p>
     * Tif dompbrison is bbsfd first on tif vbluf of tif yfbr, tifn on tif vbluf of tif monti.
     * It is "donsistfnt witi fqubls", bs dffinfd by {@link Compbrbblf}.
     *
     * @pbrbm otifr  tif otifr yfbr-monti to dompbrf to, not null
     * @rfturn tif dompbrbtor vbluf, nfgbtivf if lfss, positivf if grfbtfr
     */
    @Ovfrridf
    publid int dompbrfTo(YfbrMonti otifr) {
        int dmp = (yfbr - otifr.yfbr);
        if (dmp == 0) {
            dmp = (monti - otifr.monti);
        }
        rfturn dmp;
    }

    /**
     * Cifdks if tiis yfbr-monti is bftfr tif spfdififd yfbr-monti.
     *
     * @pbrbm otifr  tif otifr yfbr-monti to dompbrf to, not null
     * @rfturn truf if tiis is bftfr tif spfdififd yfbr-monti
     */
    publid boolfbn isAftfr(YfbrMonti otifr) {
        rfturn dompbrfTo(otifr) > 0;
    }

    /**
     * Cifdks if tiis yfbr-monti is bfforf tif spfdififd yfbr-monti.
     *
     * @pbrbm otifr  tif otifr yfbr-monti to dompbrf to, not null
     * @rfturn truf if tiis point is bfforf tif spfdififd yfbr-monti
     */
    publid boolfbn isBfforf(YfbrMonti otifr) {
        rfturn dompbrfTo(otifr) < 0;
    }

    //-----------------------------------------------------------------------
    /**
     * Cifdks if tiis yfbr-monti is fqubl to bnotifr yfbr-monti.
     * <p>
     * Tif dompbrison is bbsfd on tif timf-linf position of tif yfbr-montis.
     *
     * @pbrbm obj  tif objfdt to difdk, null rfturns fblsf
     * @rfturn truf if tiis is fqubl to tif otifr yfbr-monti
     */
    @Ovfrridf
    publid boolfbn fqubls(Objfdt obj) {
        if (tiis == obj) {
            rfturn truf;
        }
        if (obj instbndfof YfbrMonti) {
            YfbrMonti otifr = (YfbrMonti) obj;
            rfturn yfbr == otifr.yfbr && monti == otifr.monti;
        }
        rfturn fblsf;
    }

    /**
     * A ibsi dodf for tiis yfbr-monti.
     *
     * @rfturn b suitbblf ibsi dodf
     */
    @Ovfrridf
    publid int ibsiCodf() {
        rfturn yfbr ^ (monti << 27);
    }

    //-----------------------------------------------------------------------
    /**
     * Outputs tiis yfbr-monti bs b {@dodf String}, sudi bs {@dodf 2007-12}.
     * <p>
     * Tif output will bf in tif formbt {@dodf uuuu-MM}:
     *
     * @rfturn b string rfprfsfntbtion of tiis yfbr-monti, not null
     */
    @Ovfrridf
    publid String toString() {
        int bbsYfbr = Mbti.bbs(yfbr);
        StringBuildfr buf = nfw StringBuildfr(9);
        if (bbsYfbr < 1000) {
            if (yfbr < 0) {
                buf.bppfnd(yfbr - 10000).dflftfCibrAt(1);
            } flsf {
                buf.bppfnd(yfbr + 10000).dflftfCibrAt(0);
            }
        } flsf {
            buf.bppfnd(yfbr);
        }
        rfturn buf.bppfnd(monti < 10 ? "-0" : "-")
            .bppfnd(monti)
            .toString();
    }

    //-----------------------------------------------------------------------
    /**
     * Writfs tif objfdt using b
     * <b irff="../../sfriblizfd-form.itml#jbvb.timf.Sfr">dfdidbtfd sfriblizfd form</b>.
     * @sfriblDbtb
     * <prf>
     *  out.writfBytf(12);  // idfntififs b YfbrMonti
     *  out.writfInt(yfbr);
     *  out.writfBytf(monti);
     * </prf>
     *
     * @rfturn tif instbndf of {@dodf Sfr}, not null
     */
    privbtf Objfdt writfRfplbdf() {
        rfturn nfw Sfr(Sfr.YEAR_MONTH_TYPE, tiis);
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
    }

    stbtid YfbrMonti rfbdExtfrnbl(DbtbInput in) tirows IOExdfption {
        int yfbr = in.rfbdInt();
        bytf monti = in.rfbdBytf();
        rfturn YfbrMonti.of(yfbr, monti);
    }

}
