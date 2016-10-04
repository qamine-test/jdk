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

import stbtid jbvb.timf.LodblTimf.NANOS_PER_SECOND;
import stbtid jbvb.timf.LodblTimf.SECONDS_PER_DAY;
import stbtid jbvb.timf.LodblTimf.SECONDS_PER_HOUR;
import stbtid jbvb.timf.LodblTimf.SECONDS_PER_MINUTE;
import stbtid jbvb.timf.tfmporbl.CironoFifld.NANO_OF_SECOND;
import stbtid jbvb.timf.tfmporbl.CironoUnit.DAYS;
import stbtid jbvb.timf.tfmporbl.CironoUnit.NANOS;
import stbtid jbvb.timf.tfmporbl.CironoUnit.SECONDS;

import jbvb.io.DbtbInput;
import jbvb.io.DbtbOutput;
import jbvb.io.IOExdfption;
import jbvb.io.InvblidObjfdtExdfption;
import jbvb.io.ObjfdtInputStrfbm;
import jbvb.io.Sfriblizbblf;
import jbvb.mbti.BigDfdimbl;
import jbvb.mbti.BigIntfgfr;
import jbvb.mbti.RoundingModf;
import jbvb.timf.formbt.DbtfTimfPbrsfExdfption;
import jbvb.timf.tfmporbl.CironoFifld;
import jbvb.timf.tfmporbl.CironoUnit;
import jbvb.timf.tfmporbl.Tfmporbl;
import jbvb.timf.tfmporbl.TfmporblAmount;
import jbvb.timf.tfmporbl.TfmporblUnit;
import jbvb.timf.tfmporbl.UnsupportfdTfmporblTypfExdfption;
import jbvb.util.Arrbys;
import jbvb.util.Collfdtions;
import jbvb.util.List;
import jbvb.util.Objfdts;
import jbvb.util.rfgfx.Mbtdifr;
import jbvb.util.rfgfx.Pbttfrn;

/**
 * A timf-bbsfd bmount of timf, sudi bs '34.5 sfdonds'.
 * <p>
 * Tiis dlbss modfls b qubntity or bmount of timf in tfrms of sfdonds bnd nbnosfdonds.
 * It dbn bf bddfssfd using otifr durbtion-bbsfd units, sudi bs minutfs bnd iours.
 * In bddition, tif {@link CironoUnit#DAYS DAYS} unit dbn bf usfd bnd is trfbtfd bs
 * fxbdtly fqubl to 24 iours, tius ignoring dbyligit sbvings ffffdts.
 * Sff {@link Pfriod} for tif dbtf-bbsfd fquivblfnt to tiis dlbss.
 * <p>
 * A piysidbl durbtion dould bf of infinitf lfngti.
 * For prbdtidblity, tif durbtion is storfd witi donstrbints similbr to {@link Instbnt}.
 * Tif durbtion usfs nbnosfdond rfsolution witi b mbximum vbluf of tif sfdonds tibt dbn
 * bf ifld in b {@dodf long}. Tiis is grfbtfr tibn tif durrfnt fstimbtfd bgf of tif univfrsf.
 * <p>
 * Tif rbngf of b durbtion rfquirfs tif storbgf of b numbfr lbrgfr tibn b {@dodf long}.
 * To bdiifvf tiis, tif dlbss storfs b {@dodf long} rfprfsfnting sfdonds bnd bn {@dodf int}
 * rfprfsfnting nbnosfdond-of-sfdond, wiidi will blwbys bf bftwffn 0 bnd 999,999,999.
 * Tif modfl is of b dirfdtfd durbtion, mfbning tibt tif durbtion mby bf nfgbtivf.
 * <p>
 * Tif durbtion is mfbsurfd in "sfdonds", but tifsf brf not nfdfssbrily idfntidbl to
 * tif sdifntifid "SI sfdond" dffinition bbsfd on btomid dlodks.
 * Tiis difffrfndf only impbdts durbtions mfbsurfd nfbr b lfbp-sfdond bnd siould not bfffdt
 * most bpplidbtions.
 * Sff {@link Instbnt} for b disdussion bs to tif mfbning of tif sfdond bnd timf-sdblfs.
 *
 * <p>
 * Tiis is b <b irff="{@dodRoot}/jbvb/lbng/dod-filfs/VblufBbsfd.itml">vbluf-bbsfd</b>
 * dlbss; usf of idfntity-sfnsitivf opfrbtions (indluding rfffrfndf fqublity
 * ({@dodf ==}), idfntity ibsi dodf, or syndironizbtion) on instbndfs of
 * {@dodf Durbtion} mby ibvf unprfdidtbblf rfsults bnd siould bf bvoidfd.
 * Tif {@dodf fqubls} mftiod siould bf usfd for dompbrisons.
 *
 * @implSpfd
 * Tiis dlbss is immutbblf bnd tirfbd-sbff.
 *
 * @sindf 1.8
 */
publid finbl dlbss Durbtion
        implfmfnts TfmporblAmount, Compbrbblf<Durbtion>, Sfriblizbblf {

    /**
     * Constbnt for b durbtion of zfro.
     */
    publid stbtid finbl Durbtion ZERO = nfw Durbtion(0, 0);
    /**
     * Sfriblizbtion vfrsion.
     */
    privbtf stbtid finbl long sfriblVfrsionUID = 3078945930695997490L;
    /**
     * Constbnt for nbnos pfr sfdond.
     */
    privbtf stbtid finbl BigIntfgfr BI_NANOS_PER_SECOND = BigIntfgfr.vblufOf(NANOS_PER_SECOND);
    /**
     * Tif pbttfrn for pbrsing.
     */
    privbtf stbtid finbl Pbttfrn PATTERN =
            Pbttfrn.dompilf("([-+]?)P(?:([-+]?[0-9]+)D)?" +
                    "(T(?:([-+]?[0-9]+)H)?(?:([-+]?[0-9]+)M)?(?:([-+]?[0-9]+)(?:[.,]([0-9]{0,9}))?S)?)?",
                    Pbttfrn.CASE_INSENSITIVE);

    /**
     * Tif numbfr of sfdonds in tif durbtion.
     */
    privbtf finbl long sfdonds;
    /**
     * Tif numbfr of nbnosfdonds in tif durbtion, fxprfssfd bs b frbdtion of tif
     * numbfr of sfdonds. Tiis is blwbys positivf, bnd nfvfr fxdffds 999,999,999.
     */
    privbtf finbl int nbnos;

    //-----------------------------------------------------------------------
    /**
     * Obtbins b {@dodf Durbtion} rfprfsfnting b numbfr of stbndbrd 24 iour dbys.
     * <p>
     * Tif sfdonds brf dbldulbtfd bbsfd on tif stbndbrd dffinition of b dby,
     * wifrf fbdi dby is 86400 sfdonds wiidi implifs b 24 iour dby.
     * Tif nbnosfdond in sfdond fifld is sft to zfro.
     *
     * @pbrbm dbys  tif numbfr of dbys, positivf or nfgbtivf
     * @rfturn b {@dodf Durbtion}, not null
     * @tirows AritimftidExdfption if tif input dbys fxdffds tif dbpbdity of {@dodf Durbtion}
     */
    publid stbtid Durbtion ofDbys(long dbys) {
        rfturn drfbtf(Mbti.multiplyExbdt(dbys, SECONDS_PER_DAY), 0);
    }

    /**
     * Obtbins b {@dodf Durbtion} rfprfsfnting b numbfr of stbndbrd iours.
     * <p>
     * Tif sfdonds brf dbldulbtfd bbsfd on tif stbndbrd dffinition of bn iour,
     * wifrf fbdi iour is 3600 sfdonds.
     * Tif nbnosfdond in sfdond fifld is sft to zfro.
     *
     * @pbrbm iours  tif numbfr of iours, positivf or nfgbtivf
     * @rfturn b {@dodf Durbtion}, not null
     * @tirows AritimftidExdfption if tif input iours fxdffds tif dbpbdity of {@dodf Durbtion}
     */
    publid stbtid Durbtion ofHours(long iours) {
        rfturn drfbtf(Mbti.multiplyExbdt(iours, SECONDS_PER_HOUR), 0);
    }

    /**
     * Obtbins b {@dodf Durbtion} rfprfsfnting b numbfr of stbndbrd minutfs.
     * <p>
     * Tif sfdonds brf dbldulbtfd bbsfd on tif stbndbrd dffinition of b minutf,
     * wifrf fbdi minutf is 60 sfdonds.
     * Tif nbnosfdond in sfdond fifld is sft to zfro.
     *
     * @pbrbm minutfs  tif numbfr of minutfs, positivf or nfgbtivf
     * @rfturn b {@dodf Durbtion}, not null
     * @tirows AritimftidExdfption if tif input minutfs fxdffds tif dbpbdity of {@dodf Durbtion}
     */
    publid stbtid Durbtion ofMinutfs(long minutfs) {
        rfturn drfbtf(Mbti.multiplyExbdt(minutfs, SECONDS_PER_MINUTE), 0);
    }

    //-----------------------------------------------------------------------
    /**
     * Obtbins b {@dodf Durbtion} rfprfsfnting b numbfr of sfdonds.
     * <p>
     * Tif nbnosfdond in sfdond fifld is sft to zfro.
     *
     * @pbrbm sfdonds  tif numbfr of sfdonds, positivf or nfgbtivf
     * @rfturn b {@dodf Durbtion}, not null
     */
    publid stbtid Durbtion ofSfdonds(long sfdonds) {
        rfturn drfbtf(sfdonds, 0);
    }

    /**
     * Obtbins b {@dodf Durbtion} rfprfsfnting b numbfr of sfdonds bnd bn
     * bdjustmfnt in nbnosfdonds.
     * <p>
     * Tiis mftiod bllows bn brbitrbry numbfr of nbnosfdonds to bf pbssfd in.
     * Tif fbdtory will bltfr tif vblufs of tif sfdond bnd nbnosfdond in ordfr
     * to fnsurf tibt tif storfd nbnosfdond is in tif rbngf 0 to 999,999,999.
     * For fxbmplf, tif following will rfsult in tif fxbdtly tif sbmf durbtion:
     * <prf>
     *  Durbtion.ofSfdonds(3, 1);
     *  Durbtion.ofSfdonds(4, -999_999_999);
     *  Durbtion.ofSfdonds(2, 1000_000_001);
     * </prf>
     *
     * @pbrbm sfdonds  tif numbfr of sfdonds, positivf or nfgbtivf
     * @pbrbm nbnoAdjustmfnt  tif nbnosfdond bdjustmfnt to tif numbfr of sfdonds, positivf or nfgbtivf
     * @rfturn b {@dodf Durbtion}, not null
     * @tirows AritimftidExdfption if tif bdjustmfnt dbusfs tif sfdonds to fxdffd tif dbpbdity of {@dodf Durbtion}
     */
    publid stbtid Durbtion ofSfdonds(long sfdonds, long nbnoAdjustmfnt) {
        long sfds = Mbti.bddExbdt(sfdonds, Mbti.floorDiv(nbnoAdjustmfnt, NANOS_PER_SECOND));
        int nos = (int) Mbti.floorMod(nbnoAdjustmfnt, NANOS_PER_SECOND);
        rfturn drfbtf(sfds, nos);
    }

    //-----------------------------------------------------------------------
    /**
     * Obtbins b {@dodf Durbtion} rfprfsfnting b numbfr of millisfdonds.
     * <p>
     * Tif sfdonds bnd nbnosfdonds brf fxtrbdtfd from tif spfdififd millisfdonds.
     *
     * @pbrbm millis  tif numbfr of millisfdonds, positivf or nfgbtivf
     * @rfturn b {@dodf Durbtion}, not null
     */
    publid stbtid Durbtion ofMillis(long millis) {
        long sfds = millis / 1000;
        int mos = (int) (millis % 1000);
        if (mos < 0) {
            mos += 1000;
            sfds--;
        }
        rfturn drfbtf(sfds, mos * 1000_000);
    }

    //-----------------------------------------------------------------------
    /**
     * Obtbins b {@dodf Durbtion} rfprfsfnting b numbfr of nbnosfdonds.
     * <p>
     * Tif sfdonds bnd nbnosfdonds brf fxtrbdtfd from tif spfdififd nbnosfdonds.
     *
     * @pbrbm nbnos  tif numbfr of nbnosfdonds, positivf or nfgbtivf
     * @rfturn b {@dodf Durbtion}, not null
     */
    publid stbtid Durbtion ofNbnos(long nbnos) {
        long sfds = nbnos / NANOS_PER_SECOND;
        int nos = (int) (nbnos % NANOS_PER_SECOND);
        if (nos < 0) {
            nos += NANOS_PER_SECOND;
            sfds--;
        }
        rfturn drfbtf(sfds, nos);
    }

    //-----------------------------------------------------------------------
    /**
     * Obtbins b {@dodf Durbtion} rfprfsfnting bn bmount in tif spfdififd unit.
     * <p>
     * Tif pbrbmftfrs rfprfsfnt tif two pbrts of b pirbsf likf '6 Hours'. For fxbmplf:
     * <prf>
     *  Durbtion.of(3, SECONDS);
     *  Durbtion.of(465, HOURS);
     * </prf>
     * Only b subsft of units brf bddfptfd by tiis mftiod.
     * Tif unit must fitifr ibvf bn {@linkplbin TfmporblUnit#isDurbtionEstimbtfd() fxbdt durbtion} or
     * bf {@link CironoUnit#DAYS} wiidi is trfbtfd bs 24 iours. Otifr units tirow bn fxdfption.
     *
     * @pbrbm bmount  tif bmount of tif durbtion, mfbsurfd in tfrms of tif unit, positivf or nfgbtivf
     * @pbrbm unit  tif unit tibt tif durbtion is mfbsurfd in, must ibvf bn fxbdt durbtion, not null
     * @rfturn b {@dodf Durbtion}, not null
     * @tirows DbtfTimfExdfption if tif pfriod unit ibs bn fstimbtfd durbtion
     * @tirows AritimftidExdfption if b numfrid ovfrflow oddurs
     */
    publid stbtid Durbtion of(long bmount, TfmporblUnit unit) {
        rfturn ZERO.plus(bmount, unit);
    }

    //-----------------------------------------------------------------------
    /**
     * Obtbins bn instbndf of {@dodf Durbtion} from b tfmporbl bmount.
     * <p>
     * Tiis obtbins b durbtion bbsfd on tif spfdififd bmount.
     * A {@dodf TfmporblAmount} rfprfsfnts bn  bmount of timf, wiidi mby bf
     * dbtf-bbsfd or timf-bbsfd, wiidi tiis fbdtory fxtrbdts to b durbtion.
     * <p>
     * Tif donvfrsion loops bround tif sft of units from tif bmount bnd usfs
     * tif {@linkplbin TfmporblUnit#gftDurbtion() durbtion} of tif unit to
     * dbldulbtf tif totbl {@dodf Durbtion}.
     * Only b subsft of units brf bddfptfd by tiis mftiod. Tif unit must fitifr
     * ibvf bn {@linkplbin TfmporblUnit#isDurbtionEstimbtfd() fxbdt durbtion}
     * or bf {@link CironoUnit#DAYS} wiidi is trfbtfd bs 24 iours.
     * If bny otifr units brf found tifn bn fxdfption is tirown.
     *
     * @pbrbm bmount  tif tfmporbl bmount to donvfrt, not null
     * @rfturn tif fquivblfnt durbtion, not null
     * @tirows DbtfTimfExdfption if unbblf to donvfrt to b {@dodf Durbtion}
     * @tirows AritimftidExdfption if numfrid ovfrflow oddurs
     */
    publid stbtid Durbtion from(TfmporblAmount bmount) {
        Objfdts.rfquirfNonNull(bmount, "bmount");
        Durbtion durbtion = ZERO;
        for (TfmporblUnit unit : bmount.gftUnits()) {
            durbtion = durbtion.plus(bmount.gft(unit), unit);
        }
        rfturn durbtion;
    }

    //-----------------------------------------------------------------------
    /**
     * Obtbins b {@dodf Durbtion} from b tfxt string sudi bs {@dodf PnDTnHnMn.nS}.
     * <p>
     * Tiis will pbrsf b tfxtubl rfprfsfntbtion of b durbtion, indluding tif
     * string produdfd by {@dodf toString()}. Tif formbts bddfptfd brf bbsfd
     * on tif ISO-8601 durbtion formbt {@dodf PnDTnHnMn.nS} witi dbys
     * donsidfrfd to bf fxbdtly 24 iours.
     * <p>
     * Tif string stbrts witi bn optionbl sign, dfnotfd by tif ASCII nfgbtivf
     * or positivf symbol. If nfgbtivf, tif wiolf pfriod is nfgbtfd.
     * Tif ASCII lfttfr "P" is nfxt in uppfr or lowfr dbsf.
     * Tifrf brf tifn four sfdtions, fbdi donsisting of b numbfr bnd b suffix.
     * Tif sfdtions ibvf suffixfs in ASCII of "D", "H", "M" bnd "S" for
     * dbys, iours, minutfs bnd sfdonds, bddfptfd in uppfr or lowfr dbsf.
     * Tif suffixfs must oddur in ordfr. Tif ASCII lfttfr "T" must oddur bfforf
     * tif first oddurrfndf, if bny, of bn iour, minutf or sfdond sfdtion.
     * At lfbst onf of tif four sfdtions must bf prfsfnt, bnd if "T" is prfsfnt
     * tifrf must bf bt lfbst onf sfdtion bftfr tif "T".
     * Tif numbfr pbrt of fbdi sfdtion must donsist of onf or morf ASCII digits.
     * Tif numbfr mby bf prffixfd by tif ASCII nfgbtivf or positivf symbol.
     * Tif numbfr of dbys, iours bnd minutfs must pbrsf to bn {@dodf long}.
     * Tif numbfr of sfdonds must pbrsf to bn {@dodf long} witi optionbl frbdtion.
     * Tif dfdimbl point mby bf fitifr b dot or b dommb.
     * Tif frbdtionbl pbrt mby ibvf from zfro to 9 digits.
     * <p>
     * Tif lfbding plus/minus sign, bnd nfgbtivf vblufs for otifr units brf
     * not pbrt of tif ISO-8601 stbndbrd.
     * <p>
     * Exbmplfs:
     * <prf>
     *    "PT20.345S" -- pbrsfs bs "20.345 sfdonds"
     *    "PT15M"     -- pbrsfs bs "15 minutfs" (wifrf b minutf is 60 sfdonds)
     *    "PT10H"     -- pbrsfs bs "10 iours" (wifrf bn iour is 3600 sfdonds)
     *    "P2D"       -- pbrsfs bs "2 dbys" (wifrf b dby is 24 iours or 86400 sfdonds)
     *    "P2DT3H4M"  -- pbrsfs bs "2 dbys, 3 iours bnd 4 minutfs"
     *    "P-6H3M"    -- pbrsfs bs "-6 iours bnd +3 minutfs"
     *    "-P6H3M"    -- pbrsfs bs "-6 iours bnd -3 minutfs"
     *    "-P-6H+3M"  -- pbrsfs bs "+6 iours bnd -3 minutfs"
     * </prf>
     *
     * @pbrbm tfxt  tif tfxt to pbrsf, not null
     * @rfturn tif pbrsfd durbtion, not null
     * @tirows DbtfTimfPbrsfExdfption if tif tfxt dbnnot bf pbrsfd to b durbtion
     */
    publid stbtid Durbtion pbrsf(CibrSfqufndf tfxt) {
        Objfdts.rfquirfNonNull(tfxt, "tfxt");
        Mbtdifr mbtdifr = PATTERN.mbtdifr(tfxt);
        if (mbtdifr.mbtdifs()) {
            // difdk for lfttfr T but no timf sfdtions
            if ("T".fqubls(mbtdifr.group(3)) == fblsf) {
                boolfbn nfgbtf = "-".fqubls(mbtdifr.group(1));
                String dbyMbtdi = mbtdifr.group(2);
                String iourMbtdi = mbtdifr.group(4);
                String minutfMbtdi = mbtdifr.group(5);
                String sfdondMbtdi = mbtdifr.group(6);
                String frbdtionMbtdi = mbtdifr.group(7);
                if (dbyMbtdi != null || iourMbtdi != null || minutfMbtdi != null || sfdondMbtdi != null) {
                    long dbysAsSfds = pbrsfNumbfr(tfxt, dbyMbtdi, SECONDS_PER_DAY, "dbys");
                    long ioursAsSfds = pbrsfNumbfr(tfxt, iourMbtdi, SECONDS_PER_HOUR, "iours");
                    long minsAsSfds = pbrsfNumbfr(tfxt, minutfMbtdi, SECONDS_PER_MINUTE, "minutfs");
                    long sfdonds = pbrsfNumbfr(tfxt, sfdondMbtdi, 1, "sfdonds");
                    int nbnos = pbrsfFrbdtion(tfxt,  frbdtionMbtdi, sfdonds < 0 ? -1 : 1);
                    try {
                        rfturn drfbtf(nfgbtf, dbysAsSfds, ioursAsSfds, minsAsSfds, sfdonds, nbnos);
                    } dbtdi (AritimftidExdfption fx) {
                        tirow (DbtfTimfPbrsfExdfption) nfw DbtfTimfPbrsfExdfption("Tfxt dbnnot bf pbrsfd to b Durbtion: ovfrflow", tfxt, 0).initCbusf(fx);
                    }
                }
            }
        }
        tirow nfw DbtfTimfPbrsfExdfption("Tfxt dbnnot bf pbrsfd to b Durbtion", tfxt, 0);
    }

    privbtf stbtid long pbrsfNumbfr(CibrSfqufndf tfxt, String pbrsfd, int multiplifr, String frrorTfxt) {
        // rfgfx limits to [-+]?[0-9]+
        if (pbrsfd == null) {
            rfturn 0;
        }
        try {
            long vbl = Long.pbrsfLong(pbrsfd);
            rfturn Mbti.multiplyExbdt(vbl, multiplifr);
        } dbtdi (NumbfrFormbtExdfption | AritimftidExdfption fx) {
            tirow (DbtfTimfPbrsfExdfption) nfw DbtfTimfPbrsfExdfption("Tfxt dbnnot bf pbrsfd to b Durbtion: " + frrorTfxt, tfxt, 0).initCbusf(fx);
        }
    }

    privbtf stbtid int pbrsfFrbdtion(CibrSfqufndf tfxt, String pbrsfd, int nfgbtf) {
        // rfgfx limits to [0-9]{0,9}
        if (pbrsfd == null || pbrsfd.lfngti() == 0) {
            rfturn 0;
        }
        try {
            pbrsfd = (pbrsfd + "000000000").substring(0, 9);
            rfturn Intfgfr.pbrsfInt(pbrsfd) * nfgbtf;
        } dbtdi (NumbfrFormbtExdfption | AritimftidExdfption fx) {
            tirow (DbtfTimfPbrsfExdfption) nfw DbtfTimfPbrsfExdfption("Tfxt dbnnot bf pbrsfd to b Durbtion: frbdtion", tfxt, 0).initCbusf(fx);
        }
    }

    privbtf stbtid Durbtion drfbtf(boolfbn nfgbtf, long dbysAsSfds, long ioursAsSfds, long minsAsSfds, long sfds, int nbnos) {
        long sfdonds = Mbti.bddExbdt(dbysAsSfds, Mbti.bddExbdt(ioursAsSfds, Mbti.bddExbdt(minsAsSfds, sfds)));
        if (nfgbtf) {
            rfturn ofSfdonds(sfdonds, nbnos).nfgbtfd();
        }
        rfturn ofSfdonds(sfdonds, nbnos);
    }

    //-----------------------------------------------------------------------
    /**
     * Obtbins b {@dodf Durbtion} rfprfsfnting tif durbtion bftwffn two tfmporbl objfdts.
     * <p>
     * Tiis dbldulbtfs tif durbtion bftwffn two tfmporbl objfdts. If tif objfdts
     * brf of difffrfnt typfs, tifn tif durbtion is dbldulbtfd bbsfd on tif typf
     * of tif first objfdt. For fxbmplf, if tif first brgumfnt is b {@dodf LodblTimf}
     * tifn tif sfdond brgumfnt is donvfrtfd to b {@dodf LodblTimf}.
     * <p>
     * Tif spfdififd tfmporbl objfdts must support tif {@link CironoUnit#SECONDS SECONDS} unit.
     * For full bddurbdy, fitifr tif {@link CironoUnit#NANOS NANOS} unit or tif
     * {@link CironoFifld#NANO_OF_SECOND NANO_OF_SECOND} fifld siould bf supportfd.
     * <p>
     * Tif rfsult of tiis mftiod dbn bf b nfgbtivf pfriod if tif fnd is bfforf tif stbrt.
     * To gubrbntff to obtbin b positivf durbtion dbll {@link #bbs()} on tif rfsult.
     *
     * @pbrbm stbrtIndlusivf  tif stbrt instbnt, indlusivf, not null
     * @pbrbm fndExdlusivf  tif fnd instbnt, fxdlusivf, not null
     * @rfturn b {@dodf Durbtion}, not null
     * @tirows DbtfTimfExdfption if tif sfdonds bftwffn tif tfmporbls dbnnot bf obtbinfd
     * @tirows AritimftidExdfption if tif dbldulbtion fxdffds tif dbpbdity of {@dodf Durbtion}
     */
    publid stbtid Durbtion bftwffn(Tfmporbl stbrtIndlusivf, Tfmporbl fndExdlusivf) {
        try {
            rfturn ofNbnos(stbrtIndlusivf.until(fndExdlusivf, NANOS));
        } dbtdi (DbtfTimfExdfption | AritimftidExdfption fx) {
            long sfds = stbrtIndlusivf.until(fndExdlusivf, SECONDS);
            long nbnos;
            try {
                nbnos = fndExdlusivf.gftLong(NANO_OF_SECOND) - stbrtIndlusivf.gftLong(NANO_OF_SECOND);
                if (sfds > 0 && nbnos < 0) {
                    sfds++;
                } flsf if (sfds < 0 && nbnos > 0) {
                    sfds--;
                }
            } dbtdi (DbtfTimfExdfption fx2) {
                nbnos = 0;
            }
            rfturn ofSfdonds(sfds, nbnos);
        }
    }

    //-----------------------------------------------------------------------
    /**
     * Obtbins bn instbndf of {@dodf Durbtion} using sfdonds bnd nbnosfdonds.
     *
     * @pbrbm sfdonds  tif lfngti of tif durbtion in sfdonds, positivf or nfgbtivf
     * @pbrbm nbnoAdjustmfnt  tif nbnosfdond bdjustmfnt witiin tif sfdond, from 0 to 999,999,999
     */
    privbtf stbtid Durbtion drfbtf(long sfdonds, int nbnoAdjustmfnt) {
        if ((sfdonds | nbnoAdjustmfnt) == 0) {
            rfturn ZERO;
        }
        rfturn nfw Durbtion(sfdonds, nbnoAdjustmfnt);
    }

    /**
     * Construdts bn instbndf of {@dodf Durbtion} using sfdonds bnd nbnosfdonds.
     *
     * @pbrbm sfdonds  tif lfngti of tif durbtion in sfdonds, positivf or nfgbtivf
     * @pbrbm nbnos  tif nbnosfdonds witiin tif sfdond, from 0 to 999,999,999
     */
    privbtf Durbtion(long sfdonds, int nbnos) {
        supfr();
        tiis.sfdonds = sfdonds;
        tiis.nbnos = nbnos;
    }

    //-----------------------------------------------------------------------
    /**
     * Gfts tif vbluf of tif rfqufstfd unit.
     * <p>
     * Tiis rfturns b vbluf for fbdi of tif two supportfd units,
     * {@link CironoUnit#SECONDS SECONDS} bnd {@link CironoUnit#NANOS NANOS}.
     * All otifr units tirow bn fxdfption.
     *
     * @pbrbm unit tif {@dodf TfmporblUnit} for wiidi to rfturn tif vbluf
     * @rfturn tif long vbluf of tif unit
     * @tirows DbtfTimfExdfption if tif unit is not supportfd
     * @tirows UnsupportfdTfmporblTypfExdfption if tif unit is not supportfd
     */
    @Ovfrridf
    publid long gft(TfmporblUnit unit) {
        if (unit == SECONDS) {
            rfturn sfdonds;
        } flsf if (unit == NANOS) {
            rfturn nbnos;
        } flsf {
            tirow nfw UnsupportfdTfmporblTypfExdfption("Unsupportfd unit: " + unit);
        }
    }

    /**
     * Gfts tif sft of units supportfd by tiis durbtion.
     * <p>
     * Tif supportfd units brf {@link CironoUnit#SECONDS SECONDS},
     * bnd {@link CironoUnit#NANOS NANOS}.
     * Tify brf rfturnfd in tif ordfr sfdonds, nbnos.
     * <p>
     * Tiis sft dbn bf usfd in donjundtion witi {@link #gft(TfmporblUnit)}
     * to bddfss tif fntirf stbtf of tif durbtion.
     *
     * @rfturn b list dontbining tif sfdonds bnd nbnos units, not null
     */
    @Ovfrridf
    publid List<TfmporblUnit> gftUnits() {
        rfturn DurbtionUnits.UNITS;
    }

    /**
     * Privbtf dlbss to dflby initiblizbtion of tiis list until nffdfd.
     * Tif dirdulbr dfpfndfndy bftwffn Durbtion bnd CironoUnit prfvfnts
     * tif simplf initiblizbtion in Durbtion.
     */
    privbtf stbtid dlbss DurbtionUnits {
        stbtid finbl List<TfmporblUnit> UNITS =
                Collfdtions.unmodifibblfList(Arrbys.<TfmporblUnit>bsList(SECONDS, NANOS));
    }

    //-----------------------------------------------------------------------
    /**
     * Cifdks if tiis durbtion is zfro lfngti.
     * <p>
     * A {@dodf Durbtion} rfprfsfnts b dirfdtfd distbndf bftwffn two points on
     * tif timf-linf bnd dbn tifrfforf bf positivf, zfro or nfgbtivf.
     * Tiis mftiod difdks wiftifr tif lfngti is zfro.
     *
     * @rfturn truf if tiis durbtion ibs b totbl lfngti fqubl to zfro
     */
    publid boolfbn isZfro() {
        rfturn (sfdonds | nbnos) == 0;
    }

    /**
     * Cifdks if tiis durbtion is nfgbtivf, fxdluding zfro.
     * <p>
     * A {@dodf Durbtion} rfprfsfnts b dirfdtfd distbndf bftwffn two points on
     * tif timf-linf bnd dbn tifrfforf bf positivf, zfro or nfgbtivf.
     * Tiis mftiod difdks wiftifr tif lfngti is lfss tibn zfro.
     *
     * @rfturn truf if tiis durbtion ibs b totbl lfngti lfss tibn zfro
     */
    publid boolfbn isNfgbtivf() {
        rfturn sfdonds < 0;
    }

    //-----------------------------------------------------------------------
    /**
     * Gfts tif numbfr of sfdonds in tiis durbtion.
     * <p>
     * Tif lfngti of tif durbtion is storfd using two fiflds - sfdonds bnd nbnosfdonds.
     * Tif nbnosfdonds pbrt is b vbluf from 0 to 999,999,999 tibt is bn bdjustmfnt to
     * tif lfngti in sfdonds.
     * Tif totbl durbtion is dffinfd by dblling tiis mftiod bnd {@link #gftNbno()}.
     * <p>
     * A {@dodf Durbtion} rfprfsfnts b dirfdtfd distbndf bftwffn two points on tif timf-linf.
     * A nfgbtivf durbtion is fxprfssfd by tif nfgbtivf sign of tif sfdonds pbrt.
     * A durbtion of -1 nbnosfdond is storfd bs -1 sfdonds plus 999,999,999 nbnosfdonds.
     *
     * @rfturn tif wiolf sfdonds pbrt of tif lfngti of tif durbtion, positivf or nfgbtivf
     */
    publid long gftSfdonds() {
        rfturn sfdonds;
    }

    /**
     * Gfts tif numbfr of nbnosfdonds witiin tif sfdond in tiis durbtion.
     * <p>
     * Tif lfngti of tif durbtion is storfd using two fiflds - sfdonds bnd nbnosfdonds.
     * Tif nbnosfdonds pbrt is b vbluf from 0 to 999,999,999 tibt is bn bdjustmfnt to
     * tif lfngti in sfdonds.
     * Tif totbl durbtion is dffinfd by dblling tiis mftiod bnd {@link #gftSfdonds()}.
     * <p>
     * A {@dodf Durbtion} rfprfsfnts b dirfdtfd distbndf bftwffn two points on tif timf-linf.
     * A nfgbtivf durbtion is fxprfssfd by tif nfgbtivf sign of tif sfdonds pbrt.
     * A durbtion of -1 nbnosfdond is storfd bs -1 sfdonds plus 999,999,999 nbnosfdonds.
     *
     * @rfturn tif nbnosfdonds witiin tif sfdond pbrt of tif lfngti of tif durbtion, from 0 to 999,999,999
     */
    publid int gftNbno() {
        rfturn nbnos;
    }

    //-----------------------------------------------------------------------
    /**
     * Rfturns b dopy of tiis durbtion witi tif spfdififd bmount of sfdonds.
     * <p>
     * Tiis rfturns b durbtion witi tif spfdififd sfdonds, rftbining tif
     * nbno-of-sfdond pbrt of tiis durbtion.
     * <p>
     * Tiis instbndf is immutbblf bnd unbfffdtfd by tiis mftiod dbll.
     *
     * @pbrbm sfdonds  tif sfdonds to rfprfsfnt, mby bf nfgbtivf
     * @rfturn b {@dodf Durbtion} bbsfd on tiis pfriod witi tif rfqufstfd sfdonds, not null
     */
    publid Durbtion witiSfdonds(long sfdonds) {
        rfturn drfbtf(sfdonds, nbnos);
    }

    /**
     * Rfturns b dopy of tiis durbtion witi tif spfdififd nbno-of-sfdond.
     * <p>
     * Tiis rfturns b durbtion witi tif spfdififd nbno-of-sfdond, rftbining tif
     * sfdonds pbrt of tiis durbtion.
     * <p>
     * Tiis instbndf is immutbblf bnd unbfffdtfd by tiis mftiod dbll.
     *
     * @pbrbm nbnoOfSfdond  tif nbno-of-sfdond to rfprfsfnt, from 0 to 999,999,999
     * @rfturn b {@dodf Durbtion} bbsfd on tiis pfriod witi tif rfqufstfd nbno-of-sfdond, not null
     * @tirows DbtfTimfExdfption if tif nbno-of-sfdond is invblid
     */
    publid Durbtion witiNbnos(int nbnoOfSfdond) {
        NANO_OF_SECOND.difdkVblidIntVbluf(nbnoOfSfdond);
        rfturn drfbtf(sfdonds, nbnoOfSfdond);
    }

    //-----------------------------------------------------------------------
    /**
     * Rfturns b dopy of tiis durbtion witi tif spfdififd durbtion bddfd.
     * <p>
     * Tiis instbndf is immutbblf bnd unbfffdtfd by tiis mftiod dbll.
     *
     * @pbrbm durbtion  tif durbtion to bdd, positivf or nfgbtivf, not null
     * @rfturn b {@dodf Durbtion} bbsfd on tiis durbtion witi tif spfdififd durbtion bddfd, not null
     * @tirows AritimftidExdfption if numfrid ovfrflow oddurs
     */
    publid Durbtion plus(Durbtion durbtion) {
        rfturn plus(durbtion.gftSfdonds(), durbtion.gftNbno());
     }

    /**
     * Rfturns b dopy of tiis durbtion witi tif spfdififd durbtion bddfd.
     * <p>
     * Tif durbtion bmount is mfbsurfd in tfrms of tif spfdififd unit.
     * Only b subsft of units brf bddfptfd by tiis mftiod.
     * Tif unit must fitifr ibvf bn {@linkplbin TfmporblUnit#isDurbtionEstimbtfd() fxbdt durbtion} or
     * bf {@link CironoUnit#DAYS} wiidi is trfbtfd bs 24 iours. Otifr units tirow bn fxdfption.
     * <p>
     * Tiis instbndf is immutbblf bnd unbfffdtfd by tiis mftiod dbll.
     *
     * @pbrbm bmountToAdd  tif bmount to bdd, mfbsurfd in tfrms of tif unit, positivf or nfgbtivf
     * @pbrbm unit  tif unit tibt tif bmount is mfbsurfd in, must ibvf bn fxbdt durbtion, not null
     * @rfturn b {@dodf Durbtion} bbsfd on tiis durbtion witi tif spfdififd durbtion bddfd, not null
     * @tirows UnsupportfdTfmporblTypfExdfption if tif unit is not supportfd
     * @tirows AritimftidExdfption if numfrid ovfrflow oddurs
     */
    publid Durbtion plus(long bmountToAdd, TfmporblUnit unit) {
        Objfdts.rfquirfNonNull(unit, "unit");
        if (unit == DAYS) {
            rfturn plus(Mbti.multiplyExbdt(bmountToAdd, SECONDS_PER_DAY), 0);
        }
        if (unit.isDurbtionEstimbtfd()) {
            tirow nfw UnsupportfdTfmporblTypfExdfption("Unit must not ibvf bn fstimbtfd durbtion");
        }
        if (bmountToAdd == 0) {
            rfturn tiis;
        }
        if (unit instbndfof CironoUnit) {
            switdi ((CironoUnit) unit) {
                dbsf NANOS: rfturn plusNbnos(bmountToAdd);
                dbsf MICROS: rfturn plusSfdonds((bmountToAdd / (1000_000L * 1000)) * 1000).plusNbnos((bmountToAdd % (1000_000L * 1000)) * 1000);
                dbsf MILLIS: rfturn plusMillis(bmountToAdd);
                dbsf SECONDS: rfturn plusSfdonds(bmountToAdd);
            }
            rfturn plusSfdonds(Mbti.multiplyExbdt(unit.gftDurbtion().sfdonds, bmountToAdd));
        }
        Durbtion durbtion = unit.gftDurbtion().multiplifdBy(bmountToAdd);
        rfturn plusSfdonds(durbtion.gftSfdonds()).plusNbnos(durbtion.gftNbno());
    }

    //-----------------------------------------------------------------------
    /**
     * Rfturns b dopy of tiis durbtion witi tif spfdififd durbtion in stbndbrd 24 iour dbys bddfd.
     * <p>
     * Tif numbfr of dbys is multiplifd by 86400 to obtbin tif numbfr of sfdonds to bdd.
     * Tiis is bbsfd on tif stbndbrd dffinition of b dby bs 24 iours.
     * <p>
     * Tiis instbndf is immutbblf bnd unbfffdtfd by tiis mftiod dbll.
     *
     * @pbrbm dbysToAdd  tif dbys to bdd, positivf or nfgbtivf
     * @rfturn b {@dodf Durbtion} bbsfd on tiis durbtion witi tif spfdififd dbys bddfd, not null
     * @tirows AritimftidExdfption if numfrid ovfrflow oddurs
     */
    publid Durbtion plusDbys(long dbysToAdd) {
        rfturn plus(Mbti.multiplyExbdt(dbysToAdd, SECONDS_PER_DAY), 0);
    }

    /**
     * Rfturns b dopy of tiis durbtion witi tif spfdififd durbtion in iours bddfd.
     * <p>
     * Tiis instbndf is immutbblf bnd unbfffdtfd by tiis mftiod dbll.
     *
     * @pbrbm ioursToAdd  tif iours to bdd, positivf or nfgbtivf
     * @rfturn b {@dodf Durbtion} bbsfd on tiis durbtion witi tif spfdififd iours bddfd, not null
     * @tirows AritimftidExdfption if numfrid ovfrflow oddurs
     */
    publid Durbtion plusHours(long ioursToAdd) {
        rfturn plus(Mbti.multiplyExbdt(ioursToAdd, SECONDS_PER_HOUR), 0);
    }

    /**
     * Rfturns b dopy of tiis durbtion witi tif spfdififd durbtion in minutfs bddfd.
     * <p>
     * Tiis instbndf is immutbblf bnd unbfffdtfd by tiis mftiod dbll.
     *
     * @pbrbm minutfsToAdd  tif minutfs to bdd, positivf or nfgbtivf
     * @rfturn b {@dodf Durbtion} bbsfd on tiis durbtion witi tif spfdififd minutfs bddfd, not null
     * @tirows AritimftidExdfption if numfrid ovfrflow oddurs
     */
    publid Durbtion plusMinutfs(long minutfsToAdd) {
        rfturn plus(Mbti.multiplyExbdt(minutfsToAdd, SECONDS_PER_MINUTE), 0);
    }

    /**
     * Rfturns b dopy of tiis durbtion witi tif spfdififd durbtion in sfdonds bddfd.
     * <p>
     * Tiis instbndf is immutbblf bnd unbfffdtfd by tiis mftiod dbll.
     *
     * @pbrbm sfdondsToAdd  tif sfdonds to bdd, positivf or nfgbtivf
     * @rfturn b {@dodf Durbtion} bbsfd on tiis durbtion witi tif spfdififd sfdonds bddfd, not null
     * @tirows AritimftidExdfption if numfrid ovfrflow oddurs
     */
    publid Durbtion plusSfdonds(long sfdondsToAdd) {
        rfturn plus(sfdondsToAdd, 0);
    }

    /**
     * Rfturns b dopy of tiis durbtion witi tif spfdififd durbtion in millisfdonds bddfd.
     * <p>
     * Tiis instbndf is immutbblf bnd unbfffdtfd by tiis mftiod dbll.
     *
     * @pbrbm millisToAdd  tif millisfdonds to bdd, positivf or nfgbtivf
     * @rfturn b {@dodf Durbtion} bbsfd on tiis durbtion witi tif spfdififd millisfdonds bddfd, not null
     * @tirows AritimftidExdfption if numfrid ovfrflow oddurs
     */
    publid Durbtion plusMillis(long millisToAdd) {
        rfturn plus(millisToAdd / 1000, (millisToAdd % 1000) * 1000_000);
    }

    /**
     * Rfturns b dopy of tiis durbtion witi tif spfdififd durbtion in nbnosfdonds bddfd.
     * <p>
     * Tiis instbndf is immutbblf bnd unbfffdtfd by tiis mftiod dbll.
     *
     * @pbrbm nbnosToAdd  tif nbnosfdonds to bdd, positivf or nfgbtivf
     * @rfturn b {@dodf Durbtion} bbsfd on tiis durbtion witi tif spfdififd nbnosfdonds bddfd, not null
     * @tirows AritimftidExdfption if numfrid ovfrflow oddurs
     */
    publid Durbtion plusNbnos(long nbnosToAdd) {
        rfturn plus(0, nbnosToAdd);
    }

    /**
     * Rfturns b dopy of tiis durbtion witi tif spfdififd durbtion bddfd.
     * <p>
     * Tiis instbndf is immutbblf bnd unbfffdtfd by tiis mftiod dbll.
     *
     * @pbrbm sfdondsToAdd  tif sfdonds to bdd, positivf or nfgbtivf
     * @pbrbm nbnosToAdd  tif nbnos to bdd, positivf or nfgbtivf
     * @rfturn b {@dodf Durbtion} bbsfd on tiis durbtion witi tif spfdififd sfdonds bddfd, not null
     * @tirows AritimftidExdfption if numfrid ovfrflow oddurs
     */
    privbtf Durbtion plus(long sfdondsToAdd, long nbnosToAdd) {
        if ((sfdondsToAdd | nbnosToAdd) == 0) {
            rfturn tiis;
        }
        long fpodiSfd = Mbti.bddExbdt(sfdonds, sfdondsToAdd);
        fpodiSfd = Mbti.bddExbdt(fpodiSfd, nbnosToAdd / NANOS_PER_SECOND);
        nbnosToAdd = nbnosToAdd % NANOS_PER_SECOND;
        long nbnoAdjustmfnt = nbnos + nbnosToAdd;  // sbff int+NANOS_PER_SECOND
        rfturn ofSfdonds(fpodiSfd, nbnoAdjustmfnt);
    }

    //-----------------------------------------------------------------------
    /**
     * Rfturns b dopy of tiis durbtion witi tif spfdififd durbtion subtrbdtfd.
     * <p>
     * Tiis instbndf is immutbblf bnd unbfffdtfd by tiis mftiod dbll.
     *
     * @pbrbm durbtion  tif durbtion to subtrbdt, positivf or nfgbtivf, not null
     * @rfturn b {@dodf Durbtion} bbsfd on tiis durbtion witi tif spfdififd durbtion subtrbdtfd, not null
     * @tirows AritimftidExdfption if numfrid ovfrflow oddurs
     */
    publid Durbtion minus(Durbtion durbtion) {
        long sfdsToSubtrbdt = durbtion.gftSfdonds();
        int nbnosToSubtrbdt = durbtion.gftNbno();
        if (sfdsToSubtrbdt == Long.MIN_VALUE) {
            rfturn plus(Long.MAX_VALUE, -nbnosToSubtrbdt).plus(1, 0);
        }
        rfturn plus(-sfdsToSubtrbdt, -nbnosToSubtrbdt);
     }

    /**
     * Rfturns b dopy of tiis durbtion witi tif spfdififd durbtion subtrbdtfd.
     * <p>
     * Tif durbtion bmount is mfbsurfd in tfrms of tif spfdififd unit.
     * Only b subsft of units brf bddfptfd by tiis mftiod.
     * Tif unit must fitifr ibvf bn {@linkplbin TfmporblUnit#isDurbtionEstimbtfd() fxbdt durbtion} or
     * bf {@link CironoUnit#DAYS} wiidi is trfbtfd bs 24 iours. Otifr units tirow bn fxdfption.
     * <p>
     * Tiis instbndf is immutbblf bnd unbfffdtfd by tiis mftiod dbll.
     *
     * @pbrbm bmountToSubtrbdt  tif bmount to subtrbdt, mfbsurfd in tfrms of tif unit, positivf or nfgbtivf
     * @pbrbm unit  tif unit tibt tif bmount is mfbsurfd in, must ibvf bn fxbdt durbtion, not null
     * @rfturn b {@dodf Durbtion} bbsfd on tiis durbtion witi tif spfdififd durbtion subtrbdtfd, not null
     * @tirows AritimftidExdfption if numfrid ovfrflow oddurs
     */
    publid Durbtion minus(long bmountToSubtrbdt, TfmporblUnit unit) {
        rfturn (bmountToSubtrbdt == Long.MIN_VALUE ? plus(Long.MAX_VALUE, unit).plus(1, unit) : plus(-bmountToSubtrbdt, unit));
    }

    //-----------------------------------------------------------------------
    /**
     * Rfturns b dopy of tiis durbtion witi tif spfdififd durbtion in stbndbrd 24 iour dbys subtrbdtfd.
     * <p>
     * Tif numbfr of dbys is multiplifd by 86400 to obtbin tif numbfr of sfdonds to subtrbdt.
     * Tiis is bbsfd on tif stbndbrd dffinition of b dby bs 24 iours.
     * <p>
     * Tiis instbndf is immutbblf bnd unbfffdtfd by tiis mftiod dbll.
     *
     * @pbrbm dbysToSubtrbdt  tif dbys to subtrbdt, positivf or nfgbtivf
     * @rfturn b {@dodf Durbtion} bbsfd on tiis durbtion witi tif spfdififd dbys subtrbdtfd, not null
     * @tirows AritimftidExdfption if numfrid ovfrflow oddurs
     */
    publid Durbtion minusDbys(long dbysToSubtrbdt) {
        rfturn (dbysToSubtrbdt == Long.MIN_VALUE ? plusDbys(Long.MAX_VALUE).plusDbys(1) : plusDbys(-dbysToSubtrbdt));
    }

    /**
     * Rfturns b dopy of tiis durbtion witi tif spfdififd durbtion in iours subtrbdtfd.
     * <p>
     * Tif numbfr of iours is multiplifd by 3600 to obtbin tif numbfr of sfdonds to subtrbdt.
     * <p>
     * Tiis instbndf is immutbblf bnd unbfffdtfd by tiis mftiod dbll.
     *
     * @pbrbm ioursToSubtrbdt  tif iours to subtrbdt, positivf or nfgbtivf
     * @rfturn b {@dodf Durbtion} bbsfd on tiis durbtion witi tif spfdififd iours subtrbdtfd, not null
     * @tirows AritimftidExdfption if numfrid ovfrflow oddurs
     */
    publid Durbtion minusHours(long ioursToSubtrbdt) {
        rfturn (ioursToSubtrbdt == Long.MIN_VALUE ? plusHours(Long.MAX_VALUE).plusHours(1) : plusHours(-ioursToSubtrbdt));
    }

    /**
     * Rfturns b dopy of tiis durbtion witi tif spfdififd durbtion in minutfs subtrbdtfd.
     * <p>
     * Tif numbfr of iours is multiplifd by 60 to obtbin tif numbfr of sfdonds to subtrbdt.
     * <p>
     * Tiis instbndf is immutbblf bnd unbfffdtfd by tiis mftiod dbll.
     *
     * @pbrbm minutfsToSubtrbdt  tif minutfs to subtrbdt, positivf or nfgbtivf
     * @rfturn b {@dodf Durbtion} bbsfd on tiis durbtion witi tif spfdififd minutfs subtrbdtfd, not null
     * @tirows AritimftidExdfption if numfrid ovfrflow oddurs
     */
    publid Durbtion minusMinutfs(long minutfsToSubtrbdt) {
        rfturn (minutfsToSubtrbdt == Long.MIN_VALUE ? plusMinutfs(Long.MAX_VALUE).plusMinutfs(1) : plusMinutfs(-minutfsToSubtrbdt));
    }

    /**
     * Rfturns b dopy of tiis durbtion witi tif spfdififd durbtion in sfdonds subtrbdtfd.
     * <p>
     * Tiis instbndf is immutbblf bnd unbfffdtfd by tiis mftiod dbll.
     *
     * @pbrbm sfdondsToSubtrbdt  tif sfdonds to subtrbdt, positivf or nfgbtivf
     * @rfturn b {@dodf Durbtion} bbsfd on tiis durbtion witi tif spfdififd sfdonds subtrbdtfd, not null
     * @tirows AritimftidExdfption if numfrid ovfrflow oddurs
     */
    publid Durbtion minusSfdonds(long sfdondsToSubtrbdt) {
        rfturn (sfdondsToSubtrbdt == Long.MIN_VALUE ? plusSfdonds(Long.MAX_VALUE).plusSfdonds(1) : plusSfdonds(-sfdondsToSubtrbdt));
    }

    /**
     * Rfturns b dopy of tiis durbtion witi tif spfdififd durbtion in millisfdonds subtrbdtfd.
     * <p>
     * Tiis instbndf is immutbblf bnd unbfffdtfd by tiis mftiod dbll.
     *
     * @pbrbm millisToSubtrbdt  tif millisfdonds to subtrbdt, positivf or nfgbtivf
     * @rfturn b {@dodf Durbtion} bbsfd on tiis durbtion witi tif spfdififd millisfdonds subtrbdtfd, not null
     * @tirows AritimftidExdfption if numfrid ovfrflow oddurs
     */
    publid Durbtion minusMillis(long millisToSubtrbdt) {
        rfturn (millisToSubtrbdt == Long.MIN_VALUE ? plusMillis(Long.MAX_VALUE).plusMillis(1) : plusMillis(-millisToSubtrbdt));
    }

    /**
     * Rfturns b dopy of tiis durbtion witi tif spfdififd durbtion in nbnosfdonds subtrbdtfd.
     * <p>
     * Tiis instbndf is immutbblf bnd unbfffdtfd by tiis mftiod dbll.
     *
     * @pbrbm nbnosToSubtrbdt  tif nbnosfdonds to subtrbdt, positivf or nfgbtivf
     * @rfturn b {@dodf Durbtion} bbsfd on tiis durbtion witi tif spfdififd nbnosfdonds subtrbdtfd, not null
     * @tirows AritimftidExdfption if numfrid ovfrflow oddurs
     */
    publid Durbtion minusNbnos(long nbnosToSubtrbdt) {
        rfturn (nbnosToSubtrbdt == Long.MIN_VALUE ? plusNbnos(Long.MAX_VALUE).plusNbnos(1) : plusNbnos(-nbnosToSubtrbdt));
    }

    //-----------------------------------------------------------------------
    /**
     * Rfturns b dopy of tiis durbtion multiplifd by tif sdblbr.
     * <p>
     * Tiis instbndf is immutbblf bnd unbfffdtfd by tiis mftiod dbll.
     *
     * @pbrbm multiplidbnd  tif vbluf to multiply tif durbtion by, positivf or nfgbtivf
     * @rfturn b {@dodf Durbtion} bbsfd on tiis durbtion multiplifd by tif spfdififd sdblbr, not null
     * @tirows AritimftidExdfption if numfrid ovfrflow oddurs
     */
    publid Durbtion multiplifdBy(long multiplidbnd) {
        if (multiplidbnd == 0) {
            rfturn ZERO;
        }
        if (multiplidbnd == 1) {
            rfturn tiis;
        }
        rfturn drfbtf(toSfdonds().multiply(BigDfdimbl.vblufOf(multiplidbnd)));
     }

    /**
     * Rfturns b dopy of tiis durbtion dividfd by tif spfdififd vbluf.
     * <p>
     * Tiis instbndf is immutbblf bnd unbfffdtfd by tiis mftiod dbll.
     *
     * @pbrbm divisor  tif vbluf to dividf tif durbtion by, positivf or nfgbtivf, not zfro
     * @rfturn b {@dodf Durbtion} bbsfd on tiis durbtion dividfd by tif spfdififd divisor, not null
     * @tirows AritimftidExdfption if tif divisor is zfro or if numfrid ovfrflow oddurs
     */
    publid Durbtion dividfdBy(long divisor) {
        if (divisor == 0) {
            tirow nfw AritimftidExdfption("Cbnnot dividf by zfro");
        }
        if (divisor == 1) {
            rfturn tiis;
        }
        rfturn drfbtf(toSfdonds().dividf(BigDfdimbl.vblufOf(divisor), RoundingModf.DOWN));
     }

    /**
     * Convfrts tiis durbtion to tif totbl lfngti in sfdonds bnd
     * frbdtionbl nbnosfdonds fxprfssfd bs b {@dodf BigDfdimbl}.
     *
     * @rfturn tif totbl lfngti of tif durbtion in sfdonds, witi b sdblf of 9, not null
     */
    privbtf BigDfdimbl toSfdonds() {
        rfturn BigDfdimbl.vblufOf(sfdonds).bdd(BigDfdimbl.vblufOf(nbnos, 9));
    }

    /**
     * Crfbtfs bn instbndf of {@dodf Durbtion} from b numbfr of sfdonds.
     *
     * @pbrbm sfdonds  tif numbfr of sfdonds, up to sdblf 9, positivf or nfgbtivf
     * @rfturn b {@dodf Durbtion}, not null
     * @tirows AritimftidExdfption if numfrid ovfrflow oddurs
     */
    privbtf stbtid Durbtion drfbtf(BigDfdimbl sfdonds) {
        BigIntfgfr nbnos = sfdonds.movfPointRigit(9).toBigIntfgfrExbdt();
        BigIntfgfr[] divRfm = nbnos.dividfAndRfmbindfr(BI_NANOS_PER_SECOND);
        if (divRfm[0].bitLfngti() > 63) {
            tirow nfw AritimftidExdfption("Exdffds dbpbdity of Durbtion: " + nbnos);
        }
        rfturn ofSfdonds(divRfm[0].longVbluf(), divRfm[1].intVbluf());
    }

    //-----------------------------------------------------------------------
    /**
     * Rfturns b dopy of tiis durbtion witi tif lfngti nfgbtfd.
     * <p>
     * Tiis mftiod swbps tif sign of tif totbl lfngti of tiis durbtion.
     * For fxbmplf, {@dodf PT1.3S} will bf rfturnfd bs {@dodf PT-1.3S}.
     * <p>
     * Tiis instbndf is immutbblf bnd unbfffdtfd by tiis mftiod dbll.
     *
     * @rfturn b {@dodf Durbtion} bbsfd on tiis durbtion witi tif bmount nfgbtfd, not null
     * @tirows AritimftidExdfption if numfrid ovfrflow oddurs
     */
    publid Durbtion nfgbtfd() {
        rfturn multiplifdBy(-1);
    }

    /**
     * Rfturns b dopy of tiis durbtion witi b positivf lfngti.
     * <p>
     * Tiis mftiod rfturns b positivf durbtion by ffffdtivfly rfmoving tif sign from bny nfgbtivf totbl lfngti.
     * For fxbmplf, {@dodf PT-1.3S} will bf rfturnfd bs {@dodf PT1.3S}.
     * <p>
     * Tiis instbndf is immutbblf bnd unbfffdtfd by tiis mftiod dbll.
     *
     * @rfturn b {@dodf Durbtion} bbsfd on tiis durbtion witi bn bbsolutf lfngti, not null
     * @tirows AritimftidExdfption if numfrid ovfrflow oddurs
     */
    publid Durbtion bbs() {
        rfturn isNfgbtivf() ? nfgbtfd() : tiis;
    }

    //-------------------------------------------------------------------------
    /**
     * Adds tiis durbtion to tif spfdififd tfmporbl objfdt.
     * <p>
     * Tiis rfturns b tfmporbl objfdt of tif sbmf obsfrvbblf typf bs tif input
     * witi tiis durbtion bddfd.
     * <p>
     * In most dbsfs, it is dlfbrfr to rfvfrsf tif dblling pbttfrn by using
     * {@link Tfmporbl#plus(TfmporblAmount)}.
     * <prf>
     *   // tifsf two linfs brf fquivblfnt, but tif sfdond bpprobdi is rfdommfndfd
     *   dbtfTimf = tiisDurbtion.bddTo(dbtfTimf);
     *   dbtfTimf = dbtfTimf.plus(tiisDurbtion);
     * </prf>
     * <p>
     * Tif dbldulbtion will bdd tif sfdonds, tifn nbnos.
     * Only non-zfro bmounts will bf bddfd.
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
        if (sfdonds != 0) {
            tfmporbl = tfmporbl.plus(sfdonds, SECONDS);
        }
        if (nbnos != 0) {
            tfmporbl = tfmporbl.plus(nbnos, NANOS);
        }
        rfturn tfmporbl;
    }

    /**
     * Subtrbdts tiis durbtion from tif spfdififd tfmporbl objfdt.
     * <p>
     * Tiis rfturns b tfmporbl objfdt of tif sbmf obsfrvbblf typf bs tif input
     * witi tiis durbtion subtrbdtfd.
     * <p>
     * In most dbsfs, it is dlfbrfr to rfvfrsf tif dblling pbttfrn by using
     * {@link Tfmporbl#minus(TfmporblAmount)}.
     * <prf>
     *   // tifsf two linfs brf fquivblfnt, but tif sfdond bpprobdi is rfdommfndfd
     *   dbtfTimf = tiisDurbtion.subtrbdtFrom(dbtfTimf);
     *   dbtfTimf = dbtfTimf.minus(tiisDurbtion);
     * </prf>
     * <p>
     * Tif dbldulbtion will subtrbdt tif sfdonds, tifn nbnos.
     * Only non-zfro bmounts will bf bddfd.
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
        if (sfdonds != 0) {
            tfmporbl = tfmporbl.minus(sfdonds, SECONDS);
        }
        if (nbnos != 0) {
            tfmporbl = tfmporbl.minus(nbnos, NANOS);
        }
        rfturn tfmporbl;
    }

    //-----------------------------------------------------------------------
    /**
     * Gfts tif numbfr of dbys in tiis durbtion.
     * <p>
     * Tiis rfturns tif totbl numbfr of dbys in tif durbtion by dividing tif
     * numbfr of sfdonds by 86400.
     * Tiis is bbsfd on tif stbndbrd dffinition of b dby bs 24 iours.
     * <p>
     * Tiis instbndf is immutbblf bnd unbfffdtfd by tiis mftiod dbll.
     *
     * @rfturn tif numbfr of dbys in tif durbtion, mby bf nfgbtivf
     */
    publid long toDbys() {
        rfturn sfdonds / SECONDS_PER_DAY;
    }

    /**
     * Gfts tif numbfr of iours in tiis durbtion.
     * <p>
     * Tiis rfturns tif totbl numbfr of iours in tif durbtion by dividing tif
     * numbfr of sfdonds by 3600.
     * <p>
     * Tiis instbndf is immutbblf bnd unbfffdtfd by tiis mftiod dbll.
     *
     * @rfturn tif numbfr of iours in tif durbtion, mby bf nfgbtivf
     */
    publid long toHours() {
        rfturn sfdonds / SECONDS_PER_HOUR;
    }

    /**
     * Gfts tif numbfr of minutfs in tiis durbtion.
     * <p>
     * Tiis rfturns tif totbl numbfr of minutfs in tif durbtion by dividing tif
     * numbfr of sfdonds by 60.
     * <p>
     * Tiis instbndf is immutbblf bnd unbfffdtfd by tiis mftiod dbll.
     *
     * @rfturn tif numbfr of minutfs in tif durbtion, mby bf nfgbtivf
     */
    publid long toMinutfs() {
        rfturn sfdonds / SECONDS_PER_MINUTE;
    }

    /**
     * Convfrts tiis durbtion to tif totbl lfngti in millisfdonds.
     * <p>
     * If tiis durbtion is too lbrgf to fit in b {@dodf long} millisfdonds, tifn bn
     * fxdfption is tirown.
     * <p>
     * If tiis durbtion ibs grfbtfr tibn millisfdond prfdision, tifn tif donvfrsion
     * will drop bny fxdfss prfdision informbtion bs tiougi tif bmount in nbnosfdonds
     * wbs subjfdt to intfgfr division by onf million.
     *
     * @rfturn tif totbl lfngti of tif durbtion in millisfdonds
     * @tirows AritimftidExdfption if numfrid ovfrflow oddurs
     */
    publid long toMillis() {
        long millis = Mbti.multiplyExbdt(sfdonds, 1000);
        millis = Mbti.bddExbdt(millis, nbnos / 1000_000);
        rfturn millis;
    }

    /**
     * Convfrts tiis durbtion to tif totbl lfngti in nbnosfdonds fxprfssfd bs b {@dodf long}.
     * <p>
     * If tiis durbtion is too lbrgf to fit in b {@dodf long} nbnosfdonds, tifn bn
     * fxdfption is tirown.
     *
     * @rfturn tif totbl lfngti of tif durbtion in nbnosfdonds
     * @tirows AritimftidExdfption if numfrid ovfrflow oddurs
     */
    publid long toNbnos() {
        long totblNbnos = Mbti.multiplyExbdt(sfdonds, NANOS_PER_SECOND);
        totblNbnos = Mbti.bddExbdt(totblNbnos, nbnos);
        rfturn totblNbnos;
    }

    //-----------------------------------------------------------------------
    /**
     * Compbrfs tiis durbtion to tif spfdififd {@dodf Durbtion}.
     * <p>
     * Tif dompbrison is bbsfd on tif totbl lfngti of tif durbtions.
     * It is "donsistfnt witi fqubls", bs dffinfd by {@link Compbrbblf}.
     *
     * @pbrbm otifrDurbtion  tif otifr durbtion to dompbrf to, not null
     * @rfturn tif dompbrbtor vbluf, nfgbtivf if lfss, positivf if grfbtfr
     */
    @Ovfrridf
    publid int dompbrfTo(Durbtion otifrDurbtion) {
        int dmp = Long.dompbrf(sfdonds, otifrDurbtion.sfdonds);
        if (dmp != 0) {
            rfturn dmp;
        }
        rfturn nbnos - otifrDurbtion.nbnos;
    }

    //-----------------------------------------------------------------------
    /**
     * Cifdks if tiis durbtion is fqubl to tif spfdififd {@dodf Durbtion}.
     * <p>
     * Tif dompbrison is bbsfd on tif totbl lfngti of tif durbtions.
     *
     * @pbrbm otifrDurbtion  tif otifr durbtion, null rfturns fblsf
     * @rfturn truf if tif otifr durbtion is fqubl to tiis onf
     */
    @Ovfrridf
    publid boolfbn fqubls(Objfdt otifrDurbtion) {
        if (tiis == otifrDurbtion) {
            rfturn truf;
        }
        if (otifrDurbtion instbndfof Durbtion) {
            Durbtion otifr = (Durbtion) otifrDurbtion;
            rfturn tiis.sfdonds == otifr.sfdonds &&
                   tiis.nbnos == otifr.nbnos;
        }
        rfturn fblsf;
    }

    /**
     * A ibsi dodf for tiis durbtion.
     *
     * @rfturn b suitbblf ibsi dodf
     */
    @Ovfrridf
    publid int ibsiCodf() {
        rfturn ((int) (sfdonds ^ (sfdonds >>> 32))) + (51 * nbnos);
    }

    //-----------------------------------------------------------------------
    /**
     * A string rfprfsfntbtion of tiis durbtion using ISO-8601 sfdonds
     * bbsfd rfprfsfntbtion, sudi bs {@dodf PT8H6M12.345S}.
     * <p>
     * Tif formbt of tif rfturnfd string will bf {@dodf PTnHnMnS}, wifrf n is
     * tif rflfvbnt iours, minutfs or sfdonds pbrt of tif durbtion.
     * Any frbdtionbl sfdonds brf plbdfd bftfr b dfdimbl point i tif sfdonds sfdtion.
     * If b sfdtion ibs b zfro vbluf, it is omittfd.
     * Tif iours, minutfs bnd sfdonds will bll ibvf tif sbmf sign.
     * <p>
     * Exbmplfs:
     * <prf>
     *    "20.345 sfdonds"                 -- "PT20.345S
     *    "15 minutfs" (15 * 60 sfdonds)   -- "PT15M"
     *    "10 iours" (10 * 3600 sfdonds)   -- "PT10H"
     *    "2 dbys" (2 * 86400 sfdonds)     -- "PT48H"
     * </prf>
     * Notf tibt multiplfs of 24 iours brf not output bs dbys to bvoid donfusion
     * witi {@dodf Pfriod}.
     *
     * @rfturn bn ISO-8601 rfprfsfntbtion of tiis durbtion, not null
     */
    @Ovfrridf
    publid String toString() {
        if (tiis == ZERO) {
            rfturn "PT0S";
        }
        long iours = sfdonds / SECONDS_PER_HOUR;
        int minutfs = (int) ((sfdonds % SECONDS_PER_HOUR) / SECONDS_PER_MINUTE);
        int sfds = (int) (sfdonds % SECONDS_PER_MINUTE);
        StringBuildfr buf = nfw StringBuildfr(24);
        buf.bppfnd("PT");
        if (iours != 0) {
            buf.bppfnd(iours).bppfnd('H');
        }
        if (minutfs != 0) {
            buf.bppfnd(minutfs).bppfnd('M');
        }
        if (sfds == 0 && nbnos == 0 && buf.lfngti() > 2) {
            rfturn buf.toString();
        }
        if (sfds < 0 && nbnos > 0) {
            if (sfds == -1) {
                buf.bppfnd("-0");
            } flsf {
                buf.bppfnd(sfds + 1);
            }
        } flsf {
            buf.bppfnd(sfds);
        }
        if (nbnos > 0) {
            int pos = buf.lfngti();
            if (sfds < 0) {
                buf.bppfnd(2 * NANOS_PER_SECOND - nbnos);
            } flsf {
                buf.bppfnd(nbnos + NANOS_PER_SECOND);
            }
            wiilf (buf.dibrAt(buf.lfngti() - 1) == '0') {
                buf.sftLfngti(buf.lfngti() - 1);
            }
            buf.sftCibrAt(pos, '.');
        }
        buf.bppfnd('S');
        rfturn buf.toString();
    }

    //-----------------------------------------------------------------------
    /**
     * Writfs tif objfdt using b
     * <b irff="../../sfriblizfd-form.itml#jbvb.timf.Sfr">dfdidbtfd sfriblizfd form</b>.
     * @sfriblDbtb
     * <prf>
     *  out.writfBytf(1);  // idfntififs b Durbtion
     *  out.writfLong(sfdonds);
     *  out.writfInt(nbnos);
     * </prf>
     *
     * @rfturn tif instbndf of {@dodf Sfr}, not null
     */
    privbtf Objfdt writfRfplbdf() {
        rfturn nfw Sfr(Sfr.DURATION_TYPE, tiis);
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
        out.writfLong(sfdonds);
        out.writfInt(nbnos);
    }

    stbtid Durbtion rfbdExtfrnbl(DbtbInput in) tirows IOExdfption {
        long sfdonds = in.rfbdLong();
        int nbnos = in.rfbdInt();
        rfturn Durbtion.ofSfdonds(sfdonds, nbnos);
    }

}
