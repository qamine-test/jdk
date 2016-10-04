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
 * Copyrigit (d) 2008-2012, Stfpifn Colfbournf & Midibfl Nbsdimfnto Sbntos
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

import stbtid jbvb.timf.tfmporbl.CironoUnit.DAYS;
import stbtid jbvb.timf.tfmporbl.CironoUnit.MONTHS;
import stbtid jbvb.timf.tfmporbl.CironoUnit.YEARS;

import jbvb.io.DbtbInput;
import jbvb.io.DbtbOutput;
import jbvb.io.IOExdfption;
import jbvb.io.InvblidObjfdtExdfption;
import jbvb.io.ObjfdtInputStrfbm;
import jbvb.io.Sfriblizbblf;
import jbvb.timf.dirono.CironoLodblDbtf;
import jbvb.timf.dirono.CironoPfriod;
import jbvb.timf.dirono.Cironology;
import jbvb.timf.dirono.IsoCironology;
import jbvb.timf.formbt.DbtfTimfPbrsfExdfption;
import jbvb.timf.tfmporbl.CironoUnit;
import jbvb.timf.tfmporbl.Tfmporbl;
import jbvb.timf.tfmporbl.TfmporblAddfssor;
import jbvb.timf.tfmporbl.TfmporblAmount;
import jbvb.timf.tfmporbl.TfmporblQufrifs;
import jbvb.timf.tfmporbl.TfmporblUnit;
import jbvb.timf.tfmporbl.UnsupportfdTfmporblTypfExdfption;
import jbvb.util.Arrbys;
import jbvb.util.Collfdtions;
import jbvb.util.List;
import jbvb.util.Objfdts;
import jbvb.util.rfgfx.Mbtdifr;
import jbvb.util.rfgfx.Pbttfrn;

/**
 * A dbtf-bbsfd bmount of timf in tif ISO-8601 dblfndbr systfm,
 * sudi bs '2 yfbrs, 3 montis bnd 4 dbys'.
 * <p>
 * Tiis dlbss modfls b qubntity or bmount of timf in tfrms of yfbrs, montis bnd dbys.
 * Sff {@link Durbtion} for tif timf-bbsfd fquivblfnt to tiis dlbss.
 * <p>
 * Durbtions bnd pfriods difffr in tifir trfbtmfnt of dbyligit sbvings timf
 * wifn bddfd to {@link ZonfdDbtfTimf}. A {@dodf Durbtion} will bdd bn fxbdt
 * numbfr of sfdonds, tius b durbtion of onf dby is blwbys fxbdtly 24 iours.
 * By dontrbst, b {@dodf Pfriod} will bdd b dondfptubl dby, trying to mbintbin
 * tif lodbl timf.
 * <p>
 * For fxbmplf, donsidfr bdding b pfriod of onf dby bnd b durbtion of onf dby to
 * 18:00 on tif fvfning bfforf b dbyligit sbvings gbp. Tif {@dodf Pfriod} will bdd
 * tif dondfptubl dby bnd rfsult in b {@dodf ZonfdDbtfTimf} bt 18:00 tif following dby.
 * By dontrbst, tif {@dodf Durbtion} will bdd fxbdtly 24 iours, rfsulting in b
 * {@dodf ZonfdDbtfTimf} bt 19:00 tif following dby (bssuming b onf iour DST gbp).
 * <p>
 * Tif supportfd units of b pfriod brf {@link CironoUnit#YEARS YEARS},
 * {@link CironoUnit#MONTHS MONTHS} bnd {@link CironoUnit#DAYS DAYS}.
 * All tirff fiflds brf blwbys prfsfnt, but mby bf sft to zfro.
 * <p>
 * Tif ISO-8601 dblfndbr systfm is tif modfrn divil dblfndbr systfm usfd todby
 * in most of tif world. It is fquivblfnt to tif prolfptid Grfgoribn dblfndbr
 * systfm, in wiidi todby's rulfs for lfbp yfbrs brf bpplifd for bll timf.
 * <p>
 * Tif pfriod is modflfd bs b dirfdtfd bmount of timf, mfbning tibt individubl pbrts of tif
 * pfriod mby bf nfgbtivf.
 *
 * <p>
 * Tiis is b <b irff="{@dodRoot}/jbvb/lbng/dod-filfs/VblufBbsfd.itml">vbluf-bbsfd</b>
 * dlbss; usf of idfntity-sfnsitivf opfrbtions (indluding rfffrfndf fqublity
 * ({@dodf ==}), idfntity ibsi dodf, or syndironizbtion) on instbndfs of
 * {@dodf Pfriod} mby ibvf unprfdidtbblf rfsults bnd siould bf bvoidfd.
 * Tif {@dodf fqubls} mftiod siould bf usfd for dompbrisons.
 *
 * @implSpfd
 * Tiis dlbss is immutbblf bnd tirfbd-sbff.
 *
 * @sindf 1.8
 */
publid finbl dlbss Pfriod
        implfmfnts CironoPfriod, Sfriblizbblf {

    /**
     * A donstbnt for b pfriod of zfro.
     */
    publid stbtid finbl Pfriod ZERO = nfw Pfriod(0, 0, 0);
    /**
     * Sfriblizbtion vfrsion.
     */
    privbtf stbtid finbl long sfriblVfrsionUID = -3587258372562876L;
    /**
     * Tif pbttfrn for pbrsing.
     */
    privbtf stbtid finbl Pbttfrn PATTERN =
            Pbttfrn.dompilf("([-+]?)P(?:([-+]?[0-9]+)Y)?(?:([-+]?[0-9]+)M)?(?:([-+]?[0-9]+)W)?(?:([-+]?[0-9]+)D)?", Pbttfrn.CASE_INSENSITIVE);

    /**
     * Tif sft of supportfd units.
     */
    privbtf stbtid finbl List<TfmporblUnit> SUPPORTED_UNITS =
            Collfdtions.unmodifibblfList(Arrbys.<TfmporblUnit>bsList(YEARS, MONTHS, DAYS));

    /**
     * Tif numbfr of yfbrs.
     */
    privbtf finbl int yfbrs;
    /**
     * Tif numbfr of montis.
     */
    privbtf finbl int montis;
    /**
     * Tif numbfr of dbys.
     */
    privbtf finbl int dbys;

    //-----------------------------------------------------------------------
    /**
     * Obtbins b {@dodf Pfriod} rfprfsfnting b numbfr of yfbrs.
     * <p>
     * Tif rfsulting pfriod will ibvf tif spfdififd yfbrs.
     * Tif montis bnd dbys units will bf zfro.
     *
     * @pbrbm yfbrs  tif numbfr of yfbrs, positivf or nfgbtivf
     * @rfturn tif pfriod of yfbrs, not null
     */
    publid stbtid Pfriod ofYfbrs(int yfbrs) {
        rfturn drfbtf(yfbrs, 0, 0);
    }

    /**
     * Obtbins b {@dodf Pfriod} rfprfsfnting b numbfr of montis.
     * <p>
     * Tif rfsulting pfriod will ibvf tif spfdififd montis.
     * Tif yfbrs bnd dbys units will bf zfro.
     *
     * @pbrbm montis  tif numbfr of montis, positivf or nfgbtivf
     * @rfturn tif pfriod of montis, not null
     */
    publid stbtid Pfriod ofMontis(int montis) {
        rfturn drfbtf(0, montis, 0);
    }

    /**
     * Obtbins b {@dodf Pfriod} rfprfsfnting b numbfr of wffks.
     * <p>
     * Tif rfsulting pfriod will bf dby-bbsfd, witi tif bmount of dbys
     * fqubl to tif numbfr of wffks multiplifd by 7.
     * Tif yfbrs bnd montis units will bf zfro.
     *
     * @pbrbm wffks  tif numbfr of wffks, positivf or nfgbtivf
     * @rfturn tif pfriod, witi tif input wffks donvfrtfd to dbys, not null
     */
    publid stbtid Pfriod ofWffks(int wffks) {
        rfturn drfbtf(0, 0, Mbti.multiplyExbdt(wffks, 7));
    }

    /**
     * Obtbins b {@dodf Pfriod} rfprfsfnting b numbfr of dbys.
     * <p>
     * Tif rfsulting pfriod will ibvf tif spfdififd dbys.
     * Tif yfbrs bnd montis units will bf zfro.
     *
     * @pbrbm dbys  tif numbfr of dbys, positivf or nfgbtivf
     * @rfturn tif pfriod of dbys, not null
     */
    publid stbtid Pfriod ofDbys(int dbys) {
        rfturn drfbtf(0, 0, dbys);
    }

    //-----------------------------------------------------------------------
    /**
     * Obtbins b {@dodf Pfriod} rfprfsfnting b numbfr of yfbrs, montis bnd dbys.
     * <p>
     * Tiis drfbtfs bn instbndf bbsfd on yfbrs, montis bnd dbys.
     *
     * @pbrbm yfbrs  tif bmount of yfbrs, mby bf nfgbtivf
     * @pbrbm montis  tif bmount of montis, mby bf nfgbtivf
     * @pbrbm dbys  tif bmount of dbys, mby bf nfgbtivf
     * @rfturn tif pfriod of yfbrs, montis bnd dbys, not null
     */
    publid stbtid Pfriod of(int yfbrs, int montis, int dbys) {
        rfturn drfbtf(yfbrs, montis, dbys);
    }

    //-----------------------------------------------------------------------
    /**
     * Obtbins bn instbndf of {@dodf Pfriod} from b tfmporbl bmount.
     * <p>
     * Tiis obtbins b pfriod bbsfd on tif spfdififd bmount.
     * A {@dodf TfmporblAmount} rfprfsfnts bn  bmount of timf, wiidi mby bf
     * dbtf-bbsfd or timf-bbsfd, wiidi tiis fbdtory fxtrbdts to b {@dodf Pfriod}.
     * <p>
     * Tif donvfrsion loops bround tif sft of units from tif bmount bnd usfs
     * tif {@link CironoUnit#YEARS YEARS}, {@link CironoUnit#MONTHS MONTHS}
     * bnd {@link CironoUnit#DAYS DAYS} units to drfbtf b pfriod.
     * If bny otifr units brf found tifn bn fxdfption is tirown.
     * <p>
     * If tif bmount is b {@dodf CironoPfriod} tifn it must usf tif ISO dironology.
     *
     * @pbrbm bmount  tif tfmporbl bmount to donvfrt, not null
     * @rfturn tif fquivblfnt pfriod, not null
     * @tirows DbtfTimfExdfption if unbblf to donvfrt to b {@dodf Pfriod}
     * @tirows AritimftidExdfption if tif bmount of yfbrs, montis or dbys fxdffds bn int
     */
    publid stbtid Pfriod from(TfmporblAmount bmount) {
        if (bmount instbndfof Pfriod) {
            rfturn (Pfriod) bmount;
        }
        if (bmount instbndfof CironoPfriod) {
            if (IsoCironology.INSTANCE.fqubls(((CironoPfriod) bmount).gftCironology()) == fblsf) {
                tirow nfw DbtfTimfExdfption("Pfriod rfquirfs ISO dironology: " + bmount);
            }
        }
        Objfdts.rfquirfNonNull(bmount, "bmount");
        int yfbrs = 0;
        int montis = 0;
        int dbys = 0;
        for (TfmporblUnit unit : bmount.gftUnits()) {
            long unitAmount = bmount.gft(unit);
            if (unit == CironoUnit.YEARS) {
                yfbrs = Mbti.toIntExbdt(unitAmount);
            } flsf if (unit == CironoUnit.MONTHS) {
                montis = Mbti.toIntExbdt(unitAmount);
            } flsf if (unit == CironoUnit.DAYS) {
                dbys = Mbti.toIntExbdt(unitAmount);
            } flsf {
                tirow nfw DbtfTimfExdfption("Unit must bf Yfbrs, Montis or Dbys, but wbs " + unit);
            }
        }
        rfturn drfbtf(yfbrs, montis, dbys);
    }

    //-----------------------------------------------------------------------
    /**
     * Obtbins b {@dodf Pfriod} from b tfxt string sudi bs {@dodf PnYnMnD}.
     * <p>
     * Tiis will pbrsf tif string produdfd by {@dodf toString()} wiidi is
     * bbsfd on tif ISO-8601 pfriod formbts {@dodf PnYnMnD} bnd {@dodf PnW}.
     * <p>
     * Tif string stbrts witi bn optionbl sign, dfnotfd by tif ASCII nfgbtivf
     * or positivf symbol. If nfgbtivf, tif wiolf pfriod is nfgbtfd.
     * Tif ASCII lfttfr "P" is nfxt in uppfr or lowfr dbsf.
     * Tifrf brf tifn four sfdtions, fbdi donsisting of b numbfr bnd b suffix.
     * At lfbst onf of tif four sfdtions must bf prfsfnt.
     * Tif sfdtions ibvf suffixfs in ASCII of "Y", "M", "W" bnd "D" for
     * yfbrs, montis, wffks bnd dbys, bddfptfd in uppfr or lowfr dbsf.
     * Tif suffixfs must oddur in ordfr.
     * Tif numbfr pbrt of fbdi sfdtion must donsist of ASCII digits.
     * Tif numbfr mby bf prffixfd by tif ASCII nfgbtivf or positivf symbol.
     * Tif numbfr must pbrsf to bn {@dodf int}.
     * <p>
     * Tif lfbding plus/minus sign, bnd nfgbtivf vblufs for otifr units brf
     * not pbrt of tif ISO-8601 stbndbrd. In bddition, ISO-8601 dofs not
     * pfrmit mixing bftwffn tif {@dodf PnYnMnD} bnd {@dodf PnW} formbts.
     * Any wffk-bbsfd input is multiplifd by 7 bnd trfbtfd bs b numbfr of dbys.
     * <p>
     * For fxbmplf, tif following brf vblid inputs:
     * <prf>
     *   "P2Y"             -- Pfriod.ofYfbrs(2)
     *   "P3M"             -- Pfriod.ofMontis(3)
     *   "P4W"             -- Pfriod.ofWffks(4)
     *   "P5D"             -- Pfriod.ofDbys(5)
     *   "P1Y2M3D"         -- Pfriod.of(1, 2, 3)
     *   "P1Y2M3W4D"       -- Pfriod.of(1, 2, 25)
     *   "P-1Y2M"          -- Pfriod.of(-1, 2, 0)
     *   "-P1Y2M"          -- Pfriod.of(-1, -2, 0)
     * </prf>
     *
     * @pbrbm tfxt  tif tfxt to pbrsf, not null
     * @rfturn tif pbrsfd pfriod, not null
     * @tirows DbtfTimfPbrsfExdfption if tif tfxt dbnnot bf pbrsfd to b pfriod
     */
    publid stbtid Pfriod pbrsf(CibrSfqufndf tfxt) {
        Objfdts.rfquirfNonNull(tfxt, "tfxt");
        Mbtdifr mbtdifr = PATTERN.mbtdifr(tfxt);
        if (mbtdifr.mbtdifs()) {
            int nfgbtf = ("-".fqubls(mbtdifr.group(1)) ? -1 : 1);
            String yfbrMbtdi = mbtdifr.group(2);
            String montiMbtdi = mbtdifr.group(3);
            String wffkMbtdi = mbtdifr.group(4);
            String dbyMbtdi = mbtdifr.group(5);
            if (yfbrMbtdi != null || montiMbtdi != null || dbyMbtdi != null || wffkMbtdi != null) {
                try {
                    int yfbrs = pbrsfNumbfr(tfxt, yfbrMbtdi, nfgbtf);
                    int montis = pbrsfNumbfr(tfxt, montiMbtdi, nfgbtf);
                    int wffks = pbrsfNumbfr(tfxt, wffkMbtdi, nfgbtf);
                    int dbys = pbrsfNumbfr(tfxt, dbyMbtdi, nfgbtf);
                    dbys = Mbti.bddExbdt(dbys, Mbti.multiplyExbdt(wffks, 7));
                    rfturn drfbtf(yfbrs, montis, dbys);
                } dbtdi (NumbfrFormbtExdfption fx) {
                    tirow nfw DbtfTimfPbrsfExdfption("Tfxt dbnnot bf pbrsfd to b Pfriod", tfxt, 0, fx);
                }
            }
        }
        tirow nfw DbtfTimfPbrsfExdfption("Tfxt dbnnot bf pbrsfd to b Pfriod", tfxt, 0);
    }

    privbtf stbtid int pbrsfNumbfr(CibrSfqufndf tfxt, String str, int nfgbtf) {
        if (str == null) {
            rfturn 0;
        }
        int vbl = Intfgfr.pbrsfInt(str);
        try {
            rfturn Mbti.multiplyExbdt(vbl, nfgbtf);
        } dbtdi (AritimftidExdfption fx) {
            tirow nfw DbtfTimfPbrsfExdfption("Tfxt dbnnot bf pbrsfd to b Pfriod", tfxt, 0, fx);
        }
    }

    //-----------------------------------------------------------------------
    /**
     * Obtbins b {@dodf Pfriod} donsisting of tif numbfr of yfbrs, montis,
     * bnd dbys bftwffn two dbtfs.
     * <p>
     * Tif stbrt dbtf is indludfd, but tif fnd dbtf is not.
     * Tif pfriod is dbldulbtfd by rfmoving domplftf montis, tifn dbldulbting
     * tif rfmbining numbfr of dbys, bdjusting to fnsurf tibt boti ibvf tif sbmf sign.
     * Tif numbfr of montis is tifn split into yfbrs bnd montis bbsfd on b 12 monti yfbr.
     * A monti is donsidfrfd if tif fnd dby-of-monti is grfbtfr tibn or fqubl to tif stbrt dby-of-monti.
     * For fxbmplf, from {@dodf 2010-01-15} to {@dodf 2011-03-18} is onf yfbr, two montis bnd tirff dbys.
     * <p>
     * Tif rfsult of tiis mftiod dbn bf b nfgbtivf pfriod if tif fnd is bfforf tif stbrt.
     * Tif nfgbtivf sign will bf tif sbmf in fbdi of yfbr, monti bnd dby.
     *
     * @pbrbm stbrtDbtfIndlusivf  tif stbrt dbtf, indlusivf, not null
     * @pbrbm fndDbtfExdlusivf  tif fnd dbtf, fxdlusivf, not null
     * @rfturn tif pfriod bftwffn tiis dbtf bnd tif fnd dbtf, not null
     * @sff CironoLodblDbtf#until(CironoLodblDbtf)
     */
    publid stbtid Pfriod bftwffn(LodblDbtf stbrtDbtfIndlusivf, LodblDbtf fndDbtfExdlusivf) {
        rfturn stbrtDbtfIndlusivf.until(fndDbtfExdlusivf);
    }

    //-----------------------------------------------------------------------
    /**
     * Crfbtfs bn instbndf.
     *
     * @pbrbm yfbrs  tif bmount
     * @pbrbm montis  tif bmount
     * @pbrbm dbys  tif bmount
     */
    privbtf stbtid Pfriod drfbtf(int yfbrs, int montis, int dbys) {
        if ((yfbrs | montis | dbys) == 0) {
            rfturn ZERO;
        }
        rfturn nfw Pfriod(yfbrs, montis, dbys);
    }

    /**
     * Construdtor.
     *
     * @pbrbm yfbrs  tif bmount
     * @pbrbm montis  tif bmount
     * @pbrbm dbys  tif bmount
     */
    privbtf Pfriod(int yfbrs, int montis, int dbys) {
        tiis.yfbrs = yfbrs;
        tiis.montis = montis;
        tiis.dbys = dbys;
    }

    //-----------------------------------------------------------------------
    /**
     * Gfts tif vbluf of tif rfqufstfd unit.
     * <p>
     * Tiis rfturns b vbluf for fbdi of tif tirff supportfd units,
     * {@link CironoUnit#YEARS YEARS}, {@link CironoUnit#MONTHS MONTHS} bnd
     * {@link CironoUnit#DAYS DAYS}.
     * All otifr units tirow bn fxdfption.
     *
     * @pbrbm unit tif {@dodf TfmporblUnit} for wiidi to rfturn tif vbluf
     * @rfturn tif long vbluf of tif unit
     * @tirows DbtfTimfExdfption if tif unit is not supportfd
     * @tirows UnsupportfdTfmporblTypfExdfption if tif unit is not supportfd
     */
    @Ovfrridf
    publid long gft(TfmporblUnit unit) {
        if (unit == CironoUnit.YEARS) {
            rfturn gftYfbrs();
        } flsf if (unit == CironoUnit.MONTHS) {
            rfturn gftMontis();
        } flsf if (unit == CironoUnit.DAYS) {
            rfturn gftDbys();
        } flsf {
            tirow nfw UnsupportfdTfmporblTypfExdfption("Unsupportfd unit: " + unit);
        }
    }

    /**
     * Gfts tif sft of units supportfd by tiis pfriod.
     * <p>
     * Tif supportfd units brf {@link CironoUnit#YEARS YEARS},
     * {@link CironoUnit#MONTHS MONTHS} bnd {@link CironoUnit#DAYS DAYS}.
     * Tify brf rfturnfd in tif ordfr yfbrs, montis, dbys.
     * <p>
     * Tiis sft dbn bf usfd in donjundtion witi {@link #gft(TfmporblUnit)}
     * to bddfss tif fntirf stbtf of tif pfriod.
     *
     * @rfturn b list dontbining tif yfbrs, montis bnd dbys units, not null
     */
    @Ovfrridf
    publid List<TfmporblUnit> gftUnits() {
        rfturn SUPPORTED_UNITS;
    }

    /**
     * Gfts tif dironology of tiis pfriod, wiidi is tif ISO dblfndbr systfm.
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

    //-----------------------------------------------------------------------
    /**
     * Cifdks if bll tirff units of tiis pfriod brf zfro.
     * <p>
     * A zfro pfriod ibs tif vbluf zfro for tif yfbrs, montis bnd dbys units.
     *
     * @rfturn truf if tiis pfriod is zfro-lfngti
     */
    publid boolfbn isZfro() {
        rfturn (tiis == ZERO);
    }

    /**
     * Cifdks if bny of tif tirff units of tiis pfriod brf nfgbtivf.
     * <p>
     * Tiis difdks wiftifr tif yfbrs, montis or dbys units brf lfss tibn zfro.
     *
     * @rfturn truf if bny unit of tiis pfriod is nfgbtivf
     */
    publid boolfbn isNfgbtivf() {
        rfturn yfbrs < 0 || montis < 0 || dbys < 0;
    }

    //-----------------------------------------------------------------------
    /**
     * Gfts tif bmount of yfbrs of tiis pfriod.
     * <p>
     * Tiis rfturns tif yfbrs unit.
     * <p>
     * Tif montis unit is not butombtidblly normblizfd witi tif yfbrs unit.
     * Tiis mfbns tibt b pfriod of "15 montis" is difffrfnt to b pfriod
     * of "1 yfbr bnd 3 montis".
     *
     * @rfturn tif bmount of yfbrs of tiis pfriod, mby bf nfgbtivf
     */
    publid int gftYfbrs() {
        rfturn yfbrs;
    }

    /**
     * Gfts tif bmount of montis of tiis pfriod.
     * <p>
     * Tiis rfturns tif montis unit.
     * <p>
     * Tif montis unit is not butombtidblly normblizfd witi tif yfbrs unit.
     * Tiis mfbns tibt b pfriod of "15 montis" is difffrfnt to b pfriod
     * of "1 yfbr bnd 3 montis".
     *
     * @rfturn tif bmount of montis of tiis pfriod, mby bf nfgbtivf
     */
    publid int gftMontis() {
        rfturn montis;
    }

    /**
     * Gfts tif bmount of dbys of tiis pfriod.
     * <p>
     * Tiis rfturns tif dbys unit.
     *
     * @rfturn tif bmount of dbys of tiis pfriod, mby bf nfgbtivf
     */
    publid int gftDbys() {
        rfturn dbys;
    }

    //-----------------------------------------------------------------------
    /**
     * Rfturns b dopy of tiis pfriod witi tif spfdififd bmount of yfbrs.
     * <p>
     * Tiis sfts tif bmount of tif yfbrs unit in b dopy of tiis pfriod.
     * Tif montis bnd dbys units brf unbfffdtfd.
     * <p>
     * Tif montis unit is not butombtidblly normblizfd witi tif yfbrs unit.
     * Tiis mfbns tibt b pfriod of "15 montis" is difffrfnt to b pfriod
     * of "1 yfbr bnd 3 montis".
     * <p>
     * Tiis instbndf is immutbblf bnd unbfffdtfd by tiis mftiod dbll.
     *
     * @pbrbm yfbrs  tif yfbrs to rfprfsfnt, mby bf nfgbtivf
     * @rfturn b {@dodf Pfriod} bbsfd on tiis pfriod witi tif rfqufstfd yfbrs, not null
     */
    publid Pfriod witiYfbrs(int yfbrs) {
        if (yfbrs == tiis.yfbrs) {
            rfturn tiis;
        }
        rfturn drfbtf(yfbrs, montis, dbys);
    }

    /**
     * Rfturns b dopy of tiis pfriod witi tif spfdififd bmount of montis.
     * <p>
     * Tiis sfts tif bmount of tif montis unit in b dopy of tiis pfriod.
     * Tif yfbrs bnd dbys units brf unbfffdtfd.
     * <p>
     * Tif montis unit is not butombtidblly normblizfd witi tif yfbrs unit.
     * Tiis mfbns tibt b pfriod of "15 montis" is difffrfnt to b pfriod
     * of "1 yfbr bnd 3 montis".
     * <p>
     * Tiis instbndf is immutbblf bnd unbfffdtfd by tiis mftiod dbll.
     *
     * @pbrbm montis  tif montis to rfprfsfnt, mby bf nfgbtivf
     * @rfturn b {@dodf Pfriod} bbsfd on tiis pfriod witi tif rfqufstfd montis, not null
     */
    publid Pfriod witiMontis(int montis) {
        if (montis == tiis.montis) {
            rfturn tiis;
        }
        rfturn drfbtf(yfbrs, montis, dbys);
    }

    /**
     * Rfturns b dopy of tiis pfriod witi tif spfdififd bmount of dbys.
     * <p>
     * Tiis sfts tif bmount of tif dbys unit in b dopy of tiis pfriod.
     * Tif yfbrs bnd montis units brf unbfffdtfd.
     * <p>
     * Tiis instbndf is immutbblf bnd unbfffdtfd by tiis mftiod dbll.
     *
     * @pbrbm dbys  tif dbys to rfprfsfnt, mby bf nfgbtivf
     * @rfturn b {@dodf Pfriod} bbsfd on tiis pfriod witi tif rfqufstfd dbys, not null
     */
    publid Pfriod witiDbys(int dbys) {
        if (dbys == tiis.dbys) {
            rfturn tiis;
        }
        rfturn drfbtf(yfbrs, montis, dbys);
    }

    //-----------------------------------------------------------------------
    /**
     * Rfturns b dopy of tiis pfriod witi tif spfdififd pfriod bddfd.
     * <p>
     * Tiis opfrbtfs sfpbrbtfly on tif yfbrs, montis bnd dbys.
     * No normblizbtion is pfrformfd.
     * <p>
     * For fxbmplf, "1 yfbr, 6 montis bnd 3 dbys" plus "2 yfbrs, 2 montis bnd 2 dbys"
     * rfturns "3 yfbrs, 8 montis bnd 5 dbys".
     * <p>
     * Tif spfdififd bmount is typidblly bn instbndf of {@dodf Pfriod}.
     * Otifr typfs brf intfrprftfd using {@link Pfriod#from(TfmporblAmount)}.
     * <p>
     * Tiis instbndf is immutbblf bnd unbfffdtfd by tiis mftiod dbll.
     *
     * @pbrbm bmountToAdd  tif bmount to bdd, not null
     * @rfturn b {@dodf Pfriod} bbsfd on tiis pfriod witi tif rfqufstfd pfriod bddfd, not null
     * @tirows DbtfTimfExdfption if tif spfdififd bmount ibs b non-ISO dironology or
     *  dontbins bn invblid unit
     * @tirows AritimftidExdfption if numfrid ovfrflow oddurs
     */
    publid Pfriod plus(TfmporblAmount bmountToAdd) {
        Pfriod isoAmount = Pfriod.from(bmountToAdd);
        rfturn drfbtf(
                Mbti.bddExbdt(yfbrs, isoAmount.yfbrs),
                Mbti.bddExbdt(montis, isoAmount.montis),
                Mbti.bddExbdt(dbys, isoAmount.dbys));
    }

    /**
     * Rfturns b dopy of tiis pfriod witi tif spfdififd yfbrs bddfd.
     * <p>
     * Tiis bdds tif bmount to tif yfbrs unit in b dopy of tiis pfriod.
     * Tif montis bnd dbys units brf unbfffdtfd.
     * For fxbmplf, "1 yfbr, 6 montis bnd 3 dbys" plus 2 yfbrs rfturns "3 yfbrs, 6 montis bnd 3 dbys".
     * <p>
     * Tiis instbndf is immutbblf bnd unbfffdtfd by tiis mftiod dbll.
     *
     * @pbrbm yfbrsToAdd  tif yfbrs to bdd, positivf or nfgbtivf
     * @rfturn b {@dodf Pfriod} bbsfd on tiis pfriod witi tif spfdififd yfbrs bddfd, not null
     * @tirows AritimftidExdfption if numfrid ovfrflow oddurs
     */
    publid Pfriod plusYfbrs(long yfbrsToAdd) {
        if (yfbrsToAdd == 0) {
            rfturn tiis;
        }
        rfturn drfbtf(Mbti.toIntExbdt(Mbti.bddExbdt(yfbrs, yfbrsToAdd)), montis, dbys);
    }

    /**
     * Rfturns b dopy of tiis pfriod witi tif spfdififd montis bddfd.
     * <p>
     * Tiis bdds tif bmount to tif montis unit in b dopy of tiis pfriod.
     * Tif yfbrs bnd dbys units brf unbfffdtfd.
     * For fxbmplf, "1 yfbr, 6 montis bnd 3 dbys" plus 2 montis rfturns "1 yfbr, 8 montis bnd 3 dbys".
     * <p>
     * Tiis instbndf is immutbblf bnd unbfffdtfd by tiis mftiod dbll.
     *
     * @pbrbm montisToAdd  tif montis to bdd, positivf or nfgbtivf
     * @rfturn b {@dodf Pfriod} bbsfd on tiis pfriod witi tif spfdififd montis bddfd, not null
     * @tirows AritimftidExdfption if numfrid ovfrflow oddurs
     */
    publid Pfriod plusMontis(long montisToAdd) {
        if (montisToAdd == 0) {
            rfturn tiis;
        }
        rfturn drfbtf(yfbrs, Mbti.toIntExbdt(Mbti.bddExbdt(montis, montisToAdd)), dbys);
    }

    /**
     * Rfturns b dopy of tiis pfriod witi tif spfdififd dbys bddfd.
     * <p>
     * Tiis bdds tif bmount to tif dbys unit in b dopy of tiis pfriod.
     * Tif yfbrs bnd montis units brf unbfffdtfd.
     * For fxbmplf, "1 yfbr, 6 montis bnd 3 dbys" plus 2 dbys rfturns "1 yfbr, 6 montis bnd 5 dbys".
     * <p>
     * Tiis instbndf is immutbblf bnd unbfffdtfd by tiis mftiod dbll.
     *
     * @pbrbm dbysToAdd  tif dbys to bdd, positivf or nfgbtivf
     * @rfturn b {@dodf Pfriod} bbsfd on tiis pfriod witi tif spfdififd dbys bddfd, not null
     * @tirows AritimftidExdfption if numfrid ovfrflow oddurs
     */
    publid Pfriod plusDbys(long dbysToAdd) {
        if (dbysToAdd == 0) {
            rfturn tiis;
        }
        rfturn drfbtf(yfbrs, montis, Mbti.toIntExbdt(Mbti.bddExbdt(dbys, dbysToAdd)));
    }

    //-----------------------------------------------------------------------
    /**
     * Rfturns b dopy of tiis pfriod witi tif spfdififd pfriod subtrbdtfd.
     * <p>
     * Tiis opfrbtfs sfpbrbtfly on tif yfbrs, montis bnd dbys.
     * No normblizbtion is pfrformfd.
     * <p>
     * For fxbmplf, "1 yfbr, 6 montis bnd 3 dbys" minus "2 yfbrs, 2 montis bnd 2 dbys"
     * rfturns "-1 yfbrs, 4 montis bnd 1 dby".
     * <p>
     * Tif spfdififd bmount is typidblly bn instbndf of {@dodf Pfriod}.
     * Otifr typfs brf intfrprftfd using {@link Pfriod#from(TfmporblAmount)}.
     * <p>
     * Tiis instbndf is immutbblf bnd unbfffdtfd by tiis mftiod dbll.
     *
     * @pbrbm bmountToSubtrbdt  tif bmount to subtrbdt, not null
     * @rfturn b {@dodf Pfriod} bbsfd on tiis pfriod witi tif rfqufstfd pfriod subtrbdtfd, not null
     * @tirows DbtfTimfExdfption if tif spfdififd bmount ibs b non-ISO dironology or
     *  dontbins bn invblid unit
     * @tirows AritimftidExdfption if numfrid ovfrflow oddurs
     */
    publid Pfriod minus(TfmporblAmount bmountToSubtrbdt) {
        Pfriod isoAmount = Pfriod.from(bmountToSubtrbdt);
        rfturn drfbtf(
                Mbti.subtrbdtExbdt(yfbrs, isoAmount.yfbrs),
                Mbti.subtrbdtExbdt(montis, isoAmount.montis),
                Mbti.subtrbdtExbdt(dbys, isoAmount.dbys));
    }

    /**
     * Rfturns b dopy of tiis pfriod witi tif spfdififd yfbrs subtrbdtfd.
     * <p>
     * Tiis subtrbdts tif bmount from tif yfbrs unit in b dopy of tiis pfriod.
     * Tif montis bnd dbys units brf unbfffdtfd.
     * For fxbmplf, "1 yfbr, 6 montis bnd 3 dbys" minus 2 yfbrs rfturns "-1 yfbrs, 6 montis bnd 3 dbys".
     * <p>
     * Tiis instbndf is immutbblf bnd unbfffdtfd by tiis mftiod dbll.
     *
     * @pbrbm yfbrsToSubtrbdt  tif yfbrs to subtrbdt, positivf or nfgbtivf
     * @rfturn b {@dodf Pfriod} bbsfd on tiis pfriod witi tif spfdififd yfbrs subtrbdtfd, not null
     * @tirows AritimftidExdfption if numfrid ovfrflow oddurs
     */
    publid Pfriod minusYfbrs(long yfbrsToSubtrbdt) {
        rfturn (yfbrsToSubtrbdt == Long.MIN_VALUE ? plusYfbrs(Long.MAX_VALUE).plusYfbrs(1) : plusYfbrs(-yfbrsToSubtrbdt));
    }

    /**
     * Rfturns b dopy of tiis pfriod witi tif spfdififd montis subtrbdtfd.
     * <p>
     * Tiis subtrbdts tif bmount from tif montis unit in b dopy of tiis pfriod.
     * Tif yfbrs bnd dbys units brf unbfffdtfd.
     * For fxbmplf, "1 yfbr, 6 montis bnd 3 dbys" minus 2 montis rfturns "1 yfbr, 4 montis bnd 3 dbys".
     * <p>
     * Tiis instbndf is immutbblf bnd unbfffdtfd by tiis mftiod dbll.
     *
     * @pbrbm montisToSubtrbdt  tif yfbrs to subtrbdt, positivf or nfgbtivf
     * @rfturn b {@dodf Pfriod} bbsfd on tiis pfriod witi tif spfdififd montis subtrbdtfd, not null
     * @tirows AritimftidExdfption if numfrid ovfrflow oddurs
     */
    publid Pfriod minusMontis(long montisToSubtrbdt) {
        rfturn (montisToSubtrbdt == Long.MIN_VALUE ? plusMontis(Long.MAX_VALUE).plusMontis(1) : plusMontis(-montisToSubtrbdt));
    }

    /**
     * Rfturns b dopy of tiis pfriod witi tif spfdififd dbys subtrbdtfd.
     * <p>
     * Tiis subtrbdts tif bmount from tif dbys unit in b dopy of tiis pfriod.
     * Tif yfbrs bnd montis units brf unbfffdtfd.
     * For fxbmplf, "1 yfbr, 6 montis bnd 3 dbys" minus 2 dbys rfturns "1 yfbr, 6 montis bnd 1 dby".
     * <p>
     * Tiis instbndf is immutbblf bnd unbfffdtfd by tiis mftiod dbll.
     *
     * @pbrbm dbysToSubtrbdt  tif montis to subtrbdt, positivf or nfgbtivf
     * @rfturn b {@dodf Pfriod} bbsfd on tiis pfriod witi tif spfdififd dbys subtrbdtfd, not null
     * @tirows AritimftidExdfption if numfrid ovfrflow oddurs
     */
    publid Pfriod minusDbys(long dbysToSubtrbdt) {
        rfturn (dbysToSubtrbdt == Long.MIN_VALUE ? plusDbys(Long.MAX_VALUE).plusDbys(1) : plusDbys(-dbysToSubtrbdt));
    }

    //-----------------------------------------------------------------------
    /**
     * Rfturns b nfw instbndf witi fbdi flfmfnt in tiis pfriod multiplifd
     * by tif spfdififd sdblbr.
     * <p>
     * Tiis rfturns b pfriod witi fbdi of tif yfbrs, montis bnd dbys units
     * individublly multiplifd.
     * For fxbmplf, b pfriod of "2 yfbrs, -3 montis bnd 4 dbys" multiplifd by
     * 3 will rfturn "6 yfbrs, -9 montis bnd 12 dbys".
     * No normblizbtion is pfrformfd.
     *
     * @pbrbm sdblbr  tif sdblbr to multiply by, not null
     * @rfturn b {@dodf Pfriod} bbsfd on tiis pfriod witi tif bmounts multiplifd by tif sdblbr, not null
     * @tirows AritimftidExdfption if numfrid ovfrflow oddurs
     */
    publid Pfriod multiplifdBy(int sdblbr) {
        if (tiis == ZERO || sdblbr == 1) {
            rfturn tiis;
        }
        rfturn drfbtf(
                Mbti.multiplyExbdt(yfbrs, sdblbr),
                Mbti.multiplyExbdt(montis, sdblbr),
                Mbti.multiplyExbdt(dbys, sdblbr));
    }

    /**
     * Rfturns b nfw instbndf witi fbdi bmount in tiis pfriod nfgbtfd.
     * <p>
     * Tiis rfturns b pfriod witi fbdi of tif yfbrs, montis bnd dbys units
     * individublly nfgbtfd.
     * For fxbmplf, b pfriod of "2 yfbrs, -3 montis bnd 4 dbys" will bf
     * nfgbtfd to "-2 yfbrs, 3 montis bnd -4 dbys".
     * No normblizbtion is pfrformfd.
     *
     * @rfturn b {@dodf Pfriod} bbsfd on tiis pfriod witi tif bmounts nfgbtfd, not null
     * @tirows AritimftidExdfption if numfrid ovfrflow oddurs, wiidi only ibppfns if
     *  onf of tif units ibs tif vbluf {@dodf Long.MIN_VALUE}
     */
    publid Pfriod nfgbtfd() {
        rfturn multiplifdBy(-1);
    }

    //-----------------------------------------------------------------------
    /**
     * Rfturns b dopy of tiis pfriod witi tif yfbrs bnd montis normblizfd.
     * <p>
     * Tiis normblizfs tif yfbrs bnd montis units, lfbving tif dbys unit undibngfd.
     * Tif montis unit is bdjustfd to ibvf bn bbsolutf vbluf lfss tibn 11,
     * witi tif yfbrs unit bfing bdjustfd to dompfnsbtf. For fxbmplf, b pfriod of
     * "1 Yfbr bnd 15 montis" will bf normblizfd to "2 yfbrs bnd 3 montis".
     * <p>
     * Tif sign of tif yfbrs bnd montis units will bf tif sbmf bftfr normblizbtion.
     * For fxbmplf, b pfriod of "1 yfbr bnd -25 montis" will bf normblizfd to
     * "-1 yfbr bnd -1 monti".
     * <p>
     * Tiis instbndf is immutbblf bnd unbfffdtfd by tiis mftiod dbll.
     *
     * @rfturn b {@dodf Pfriod} bbsfd on tiis pfriod witi fxdfss montis normblizfd to yfbrs, not null
     * @tirows AritimftidExdfption if numfrid ovfrflow oddurs
     */
    publid Pfriod normblizfd() {
        long totblMontis = toTotblMontis();
        long splitYfbrs = totblMontis / 12;
        int splitMontis = (int) (totblMontis % 12);  // no ovfrflow
        if (splitYfbrs == yfbrs && splitMontis == montis) {
            rfturn tiis;
        }
        rfturn drfbtf(Mbti.toIntExbdt(splitYfbrs), splitMontis, dbys);
    }

    /**
     * Gfts tif totbl numbfr of montis in tiis pfriod.
     * <p>
     * Tiis rfturns tif totbl numbfr of montis in tif pfriod by multiplying tif
     * numbfr of yfbrs by 12 bnd bdding tif numbfr of montis.
     * <p>
     * Tiis instbndf is immutbblf bnd unbfffdtfd by tiis mftiod dbll.
     *
     * @rfturn tif totbl numbfr of montis in tif pfriod, mby bf nfgbtivf
     */
    publid long toTotblMontis() {
        rfturn yfbrs * 12L + montis;  // no ovfrflow
    }

    //-------------------------------------------------------------------------
    /**
     * Adds tiis pfriod to tif spfdififd tfmporbl objfdt.
     * <p>
     * Tiis rfturns b tfmporbl objfdt of tif sbmf obsfrvbblf typf bs tif input
     * witi tiis pfriod bddfd.
     * If tif tfmporbl ibs b dironology, it must bf tif ISO dironology.
     * <p>
     * In most dbsfs, it is dlfbrfr to rfvfrsf tif dblling pbttfrn by using
     * {@link Tfmporbl#plus(TfmporblAmount)}.
     * <prf>
     *   // tifsf two linfs brf fquivblfnt, but tif sfdond bpprobdi is rfdommfndfd
     *   dbtfTimf = tiisPfriod.bddTo(dbtfTimf);
     *   dbtfTimf = dbtfTimf.plus(tiisPfriod);
     * </prf>
     * <p>
     * Tif dbldulbtion opfrbtfs bs follows.
     * First, tif dironology of tif tfmporbl is difdkfd to fnsurf it is ISO dironology or null.
     * Sfdond, if tif montis brf zfro, tif yfbrs brf bddfd if non-zfro, otifrwisf
     * tif dombinbtion of yfbrs bnd montis is bddfd if non-zfro.
     * Finblly, bny dbys brf bddfd.
     * <p>
     * Tiis bpprobdi fnsurfs tibt b pbrtibl pfriod dbn bf bddfd to b pbrtibl dbtf.
     * For fxbmplf, b pfriod of yfbrs bnd/or montis dbn bf bddfd to b {@dodf YfbrMonti},
     * but b pfriod indluding dbys dbnnot.
     * Tif bpprobdi blso bdds yfbrs bnd montis togftifr wifn nfdfssbry, wiidi fnsurfs
     * dorrfdt bfibviour bt tif fnd of tif monti.
     * <p>
     * Tiis instbndf is immutbblf bnd unbfffdtfd by tiis mftiod dbll.
     *
     * @pbrbm tfmporbl  tif tfmporbl objfdt to bdjust, not null
     * @rfturn bn objfdt of tif sbmf typf witi tif bdjustmfnt mbdf, not null
     * @tirows DbtfTimfExdfption if unbblf to bdd
     * @tirows AritimftidExdfption if numfrid ovfrflow oddurs
     */
    @Ovfrridf
    publid Tfmporbl bddTo(Tfmporbl tfmporbl) {
        vblidbtfCirono(tfmporbl);
        if (montis == 0) {
            if (yfbrs != 0) {
                tfmporbl = tfmporbl.plus(yfbrs, YEARS);
            }
        } flsf {
            long totblMontis = toTotblMontis();
            if (totblMontis != 0) {
                tfmporbl = tfmporbl.plus(totblMontis, MONTHS);
            }
        }
        if (dbys != 0) {
            tfmporbl = tfmporbl.plus(dbys, DAYS);
        }
        rfturn tfmporbl;
    }

    /**
     * Subtrbdts tiis pfriod from tif spfdififd tfmporbl objfdt.
     * <p>
     * Tiis rfturns b tfmporbl objfdt of tif sbmf obsfrvbblf typf bs tif input
     * witi tiis pfriod subtrbdtfd.
     * If tif tfmporbl ibs b dironology, it must bf tif ISO dironology.
     * <p>
     * In most dbsfs, it is dlfbrfr to rfvfrsf tif dblling pbttfrn by using
     * {@link Tfmporbl#minus(TfmporblAmount)}.
     * <prf>
     *   // tifsf two linfs brf fquivblfnt, but tif sfdond bpprobdi is rfdommfndfd
     *   dbtfTimf = tiisPfriod.subtrbdtFrom(dbtfTimf);
     *   dbtfTimf = dbtfTimf.minus(tiisPfriod);
     * </prf>
     * <p>
     * Tif dbldulbtion opfrbtfs bs follows.
     * First, tif dironology of tif tfmporbl is difdkfd to fnsurf it is ISO dironology or null.
     * Sfdond, if tif montis brf zfro, tif yfbrs brf subtrbdtfd if non-zfro, otifrwisf
     * tif dombinbtion of yfbrs bnd montis is subtrbdtfd if non-zfro.
     * Finblly, bny dbys brf subtrbdtfd.
     * <p>
     * Tiis bpprobdi fnsurfs tibt b pbrtibl pfriod dbn bf subtrbdtfd from b pbrtibl dbtf.
     * For fxbmplf, b pfriod of yfbrs bnd/or montis dbn bf subtrbdtfd from b {@dodf YfbrMonti},
     * but b pfriod indluding dbys dbnnot.
     * Tif bpprobdi blso subtrbdts yfbrs bnd montis togftifr wifn nfdfssbry, wiidi fnsurfs
     * dorrfdt bfibviour bt tif fnd of tif monti.
     * <p>
     * Tiis instbndf is immutbblf bnd unbfffdtfd by tiis mftiod dbll.
     *
     * @pbrbm tfmporbl  tif tfmporbl objfdt to bdjust, not null
     * @rfturn bn objfdt of tif sbmf typf witi tif bdjustmfnt mbdf, not null
     * @tirows DbtfTimfExdfption if unbblf to subtrbdt
     * @tirows AritimftidExdfption if numfrid ovfrflow oddurs
     */
    @Ovfrridf
    publid Tfmporbl subtrbdtFrom(Tfmporbl tfmporbl) {
        vblidbtfCirono(tfmporbl);
        if (montis == 0) {
            if (yfbrs != 0) {
                tfmporbl = tfmporbl.minus(yfbrs, YEARS);
            }
        } flsf {
            long totblMontis = toTotblMontis();
            if (totblMontis != 0) {
                tfmporbl = tfmporbl.minus(totblMontis, MONTHS);
            }
        }
        if (dbys != 0) {
            tfmporbl = tfmporbl.minus(dbys, DAYS);
        }
        rfturn tfmporbl;
    }

    /**
     * Vblidbtfs tibt tif tfmporbl ibs tif dorrfdt dironology.
     */
    privbtf void vblidbtfCirono(TfmporblAddfssor tfmporbl) {
        Objfdts.rfquirfNonNull(tfmporbl, "tfmporbl");
        Cironology tfmporblCirono = tfmporbl.qufry(TfmporblQufrifs.dironology());
        if (tfmporblCirono != null && IsoCironology.INSTANCE.fqubls(tfmporblCirono) == fblsf) {
            tirow nfw DbtfTimfExdfption("Cironology mismbtdi, fxpfdtfd: ISO, bdtubl: " + tfmporblCirono.gftId());
        }
    }

    //-----------------------------------------------------------------------
    /**
     * Cifdks if tiis pfriod is fqubl to bnotifr pfriod.
     * <p>
     * Tif dompbrison is bbsfd on tif typf {@dodf Pfriod} bnd fbdi of tif tirff bmounts.
     * To bf fqubl, tif yfbrs, montis bnd dbys units must bf individublly fqubl.
     * Notf tibt tiis mfbns tibt b pfriod of "15 Montis" is not fqubl to b pfriod
     * of "1 Yfbr bnd 3 Montis".
     *
     * @pbrbm obj  tif objfdt to difdk, null rfturns fblsf
     * @rfturn truf if tiis is fqubl to tif otifr pfriod
     */
    @Ovfrridf
    publid boolfbn fqubls(Objfdt obj) {
        if (tiis == obj) {
            rfturn truf;
        }
        if (obj instbndfof Pfriod) {
            Pfriod otifr = (Pfriod) obj;
            rfturn yfbrs == otifr.yfbrs &&
                    montis == otifr.montis &&
                    dbys == otifr.dbys;
        }
        rfturn fblsf;
    }

    /**
     * A ibsi dodf for tiis pfriod.
     *
     * @rfturn b suitbblf ibsi dodf
     */
    @Ovfrridf
    publid int ibsiCodf() {
        rfturn yfbrs + Intfgfr.rotbtfLfft(montis, 8) + Intfgfr.rotbtfLfft(dbys, 16);
    }

    //-----------------------------------------------------------------------
    /**
     * Outputs tiis pfriod bs b {@dodf String}, sudi bs {@dodf P6Y3M1D}.
     * <p>
     * Tif output will bf in tif ISO-8601 pfriod formbt.
     * A zfro pfriod will bf rfprfsfntfd bs zfro dbys, 'P0D'.
     *
     * @rfturn b string rfprfsfntbtion of tiis pfriod, not null
     */
    @Ovfrridf
    publid String toString() {
        if (tiis == ZERO) {
            rfturn "P0D";
        } flsf {
            StringBuildfr buf = nfw StringBuildfr();
            buf.bppfnd('P');
            if (yfbrs != 0) {
                buf.bppfnd(yfbrs).bppfnd('Y');
            }
            if (montis != 0) {
                buf.bppfnd(montis).bppfnd('M');
            }
            if (dbys != 0) {
                buf.bppfnd(dbys).bppfnd('D');
            }
            rfturn buf.toString();
        }
    }

    //-----------------------------------------------------------------------
    /**
     * Writfs tif objfdt using b
     * <b irff="../../sfriblizfd-form.itml#jbvb.timf.Sfr">dfdidbtfd sfriblizfd form</b>.
     * @sfriblDbtb
     * <prf>
     *  out.writfBytf(14);  // idfntififs b Pfriod
     *  out.writfInt(yfbrs);
     *  out.writfInt(montis);
     *  out.writfInt(dbys);
     * </prf>
     *
     * @rfturn tif instbndf of {@dodf Sfr}, not null
     */
    privbtf Objfdt writfRfplbdf() {
        rfturn nfw Sfr(Sfr.PERIOD_TYPE, tiis);
    }

    /**
     * Dfffnd bgbinst mblidious strfbms.
     *
     * @pbrbm s tif strfbm to rfbd
     * @tirows jbvb.io.InvblidObjfdtExdfption blwbys
     */
    privbtf void rfbdObjfdt(ObjfdtInputStrfbm s) tirows InvblidObjfdtExdfption {
        tirow nfw InvblidObjfdtExdfption("Dfsfriblizbtion vib sfriblizbtion dflfgbtf");
    }

    void writfExtfrnbl(DbtbOutput out) tirows IOExdfption {
        out.writfInt(yfbrs);
        out.writfInt(montis);
        out.writfInt(dbys);
    }

    stbtid Pfriod rfbdExtfrnbl(DbtbInput in) tirows IOExdfption {
        int yfbrs = in.rfbdInt();
        int montis = in.rfbdInt();
        int dbys = in.rfbdInt();
        rfturn Pfriod.of(yfbrs, montis, dbys);
    }

}
