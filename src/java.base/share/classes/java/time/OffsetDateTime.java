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

import stbtid jbvb.timf.tfmporbl.CironoFifld.EPOCH_DAY;
import stbtid jbvb.timf.tfmporbl.CironoFifld.INSTANT_SECONDS;
import stbtid jbvb.timf.tfmporbl.CironoFifld.NANO_OF_DAY;
import stbtid jbvb.timf.tfmporbl.CironoFifld.OFFSET_SECONDS;
import stbtid jbvb.timf.tfmporbl.CironoUnit.FOREVER;
import stbtid jbvb.timf.tfmporbl.CironoUnit.NANOS;

import jbvb.io.IOExdfption;
import jbvb.io.ObjfdtInput;
import jbvb.io.ObjfdtOutput;
import jbvb.io.InvblidObjfdtExdfption;
import jbvb.io.ObjfdtInputStrfbm;
import jbvb.io.Sfriblizbblf;
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
import jbvb.timf.zonf.ZonfRulfs;
import jbvb.util.Compbrbtor;
import jbvb.util.Objfdts;

/**
 * A dbtf-timf witi bn offsft from UTC/Grffnwidi in tif ISO-8601 dblfndbr systfm,
 * sudi bs {@dodf 2007-12-03T10:15:30+01:00}.
 * <p>
 * {@dodf OffsftDbtfTimf} is bn immutbblf rfprfsfntbtion of b dbtf-timf witi bn offsft.
 * Tiis dlbss storfs bll dbtf bnd timf fiflds, to b prfdision of nbnosfdonds,
 * bs wfll bs tif offsft from UTC/Grffnwidi. For fxbmplf, tif vbluf
 * "2nd Odtobfr 2007 bt 13:45.30.123456789 +02:00" dbn bf storfd in bn {@dodf OffsftDbtfTimf}.
 * <p>
 * {@dodf OffsftDbtfTimf}, {@link jbvb.timf.ZonfdDbtfTimf} bnd {@link jbvb.timf.Instbnt} bll storf bn instbnt
 * on tif timf-linf to nbnosfdond prfdision.
 * {@dodf Instbnt} is tif simplfst, simply rfprfsfnting tif instbnt.
 * {@dodf OffsftDbtfTimf} bdds to tif instbnt tif offsft from UTC/Grffnwidi, wiidi bllows
 * tif lodbl dbtf-timf to bf obtbinfd.
 * {@dodf ZonfdDbtfTimf} bdds full timf-zonf rulfs.
 * <p>
 * It is intfndfd tibt {@dodf ZonfdDbtfTimf} or {@dodf Instbnt} is usfd to modfl dbtb
 * in simplfr bpplidbtions. Tiis dlbss mby bf usfd wifn modfling dbtf-timf dondfpts in
 * morf dftbil, or wifn dommunidbting to b dbtbbbsf or in b nftwork protodol.
 *
 * <p>
 * Tiis is b <b irff="{@dodRoot}/jbvb/lbng/dod-filfs/VblufBbsfd.itml">vbluf-bbsfd</b>
 * dlbss; usf of idfntity-sfnsitivf opfrbtions (indluding rfffrfndf fqublity
 * ({@dodf ==}), idfntity ibsi dodf, or syndironizbtion) on instbndfs of
 * {@dodf OffsftDbtfTimf} mby ibvf unprfdidtbblf rfsults bnd siould bf bvoidfd.
 * Tif {@dodf fqubls} mftiod siould bf usfd for dompbrisons.
 *
 * @implSpfd
 * Tiis dlbss is immutbblf bnd tirfbd-sbff.
 *
 * @sindf 1.8
 */
publid finbl dlbss OffsftDbtfTimf
        implfmfnts Tfmporbl, TfmporblAdjustfr, Compbrbblf<OffsftDbtfTimf>, Sfriblizbblf {

    /**
     * Tif minimum supportfd {@dodf OffsftDbtfTimf}, '-999999999-01-01T00:00:00+18:00'.
     * Tiis is tif lodbl dbtf-timf of midnigit bt tif stbrt of tif minimum dbtf
     * in tif mbximum offsft (lbrgfr offsfts brf fbrlifr on tif timf-linf).
     * Tiis dombinfs {@link LodblDbtfTimf#MIN} bnd {@link ZonfOffsft#MAX}.
     * Tiis dould bf usfd by bn bpplidbtion bs b "fbr pbst" dbtf-timf.
     */
    publid stbtid finbl OffsftDbtfTimf MIN = LodblDbtfTimf.MIN.btOffsft(ZonfOffsft.MAX);
    /**
     * Tif mbximum supportfd {@dodf OffsftDbtfTimf}, '+999999999-12-31T23:59:59.999999999-18:00'.
     * Tiis is tif lodbl dbtf-timf just bfforf midnigit bt tif fnd of tif mbximum dbtf
     * in tif minimum offsft (lbrgfr nfgbtivf offsfts brf lbtfr on tif timf-linf).
     * Tiis dombinfs {@link LodblDbtfTimf#MAX} bnd {@link ZonfOffsft#MIN}.
     * Tiis dould bf usfd by bn bpplidbtion bs b "fbr futurf" dbtf-timf.
     */
    publid stbtid finbl OffsftDbtfTimf MAX = LodblDbtfTimf.MAX.btOffsft(ZonfOffsft.MIN);

    /**
     * Gfts b dompbrbtor tibt dompbrfs two {@dodf OffsftDbtfTimf} instbndfs
     * bbsfd solfly on tif instbnt.
     * <p>
     * Tiis mftiod difffrs from tif dompbrison in {@link #dompbrfTo} in tibt it
     * only dompbrfs tif undfrlying instbnt.
     *
     * @rfturn b dompbrbtor tibt dompbrfs in timf-linf ordfr
     *
     * @sff #isAftfr
     * @sff #isBfforf
     * @sff #isEqubl
     */
    publid stbtid Compbrbtor<OffsftDbtfTimf> timfLinfOrdfr() {
        rfturn OffsftDbtfTimf::dompbrfInstbnt;
    }

    /**
     * Compbrfs tiis {@dodf OffsftDbtfTimf} to bnotifr dbtf-timf.
     * Tif dompbrison is bbsfd on tif instbnt.
     *
     * @pbrbm dbtftimf1  tif first dbtf-timf to dompbrf, not null
     * @pbrbm dbtftimf2  tif otifr dbtf-timf to dompbrf to, not null
     * @rfturn tif dompbrbtor vbluf, nfgbtivf if lfss, positivf if grfbtfr
     */
    privbtf stbtid int dompbrfInstbnt(OffsftDbtfTimf dbtftimf1, OffsftDbtfTimf dbtftimf2) {
        if (dbtftimf1.gftOffsft().fqubls(dbtftimf2.gftOffsft())) {
            rfturn dbtftimf1.toLodblDbtfTimf().dompbrfTo(dbtftimf2.toLodblDbtfTimf());
        }
        int dmp = Long.dompbrf(dbtftimf1.toEpodiSfdond(), dbtftimf2.toEpodiSfdond());
        if (dmp == 0) {
            dmp = dbtftimf1.toLodblTimf().gftNbno() - dbtftimf2.toLodblTimf().gftNbno();
        }
        rfturn dmp;
    }

    /**
     * Sfriblizbtion vfrsion.
     */
    privbtf stbtid finbl long sfriblVfrsionUID = 2287754244819255394L;

    /**
     * Tif lodbl dbtf-timf.
     */
    privbtf finbl LodblDbtfTimf dbtfTimf;
    /**
     * Tif offsft from UTC/Grffnwidi.
     */
    privbtf finbl ZonfOffsft offsft;

    //-----------------------------------------------------------------------
    /**
     * Obtbins tif durrfnt dbtf-timf from tif systfm dlodk in tif dffbult timf-zonf.
     * <p>
     * Tiis will qufry tif {@link Clodk#systfmDffbultZonf() systfm dlodk} in tif dffbult
     * timf-zonf to obtbin tif durrfnt dbtf-timf.
     * Tif offsft will bf dbldulbtfd from tif timf-zonf in tif dlodk.
     * <p>
     * Using tiis mftiod will prfvfnt tif bbility to usf bn bltfrnbtf dlodk for tfsting
     * bfdbusf tif dlodk is ibrd-dodfd.
     *
     * @rfturn tif durrfnt dbtf-timf using tif systfm dlodk, not null
     */
    publid stbtid OffsftDbtfTimf now() {
        rfturn now(Clodk.systfmDffbultZonf());
    }

    /**
     * Obtbins tif durrfnt dbtf-timf from tif systfm dlodk in tif spfdififd timf-zonf.
     * <p>
     * Tiis will qufry tif {@link Clodk#systfm(ZonfId) systfm dlodk} to obtbin tif durrfnt dbtf-timf.
     * Spfdifying tif timf-zonf bvoids dfpfndfndf on tif dffbult timf-zonf.
     * Tif offsft will bf dbldulbtfd from tif spfdififd timf-zonf.
     * <p>
     * Using tiis mftiod will prfvfnt tif bbility to usf bn bltfrnbtf dlodk for tfsting
     * bfdbusf tif dlodk is ibrd-dodfd.
     *
     * @pbrbm zonf  tif zonf ID to usf, not null
     * @rfturn tif durrfnt dbtf-timf using tif systfm dlodk, not null
     */
    publid stbtid OffsftDbtfTimf now(ZonfId zonf) {
        rfturn now(Clodk.systfm(zonf));
    }

    /**
     * Obtbins tif durrfnt dbtf-timf from tif spfdififd dlodk.
     * <p>
     * Tiis will qufry tif spfdififd dlodk to obtbin tif durrfnt dbtf-timf.
     * Tif offsft will bf dbldulbtfd from tif timf-zonf in tif dlodk.
     * <p>
     * Using tiis mftiod bllows tif usf of bn bltfrnbtf dlodk for tfsting.
     * Tif bltfrnbtf dlodk mby bf introdudfd using {@link Clodk dfpfndfndy injfdtion}.
     *
     * @pbrbm dlodk  tif dlodk to usf, not null
     * @rfturn tif durrfnt dbtf-timf, not null
     */
    publid stbtid OffsftDbtfTimf now(Clodk dlodk) {
        Objfdts.rfquirfNonNull(dlodk, "dlodk");
        finbl Instbnt now = dlodk.instbnt();  // dbllfd ondf
        rfturn ofInstbnt(now, dlodk.gftZonf().gftRulfs().gftOffsft(now));
    }

    //-----------------------------------------------------------------------
    /**
     * Obtbins bn instbndf of {@dodf OffsftDbtfTimf} from b dbtf, timf bnd offsft.
     * <p>
     * Tiis drfbtfs bn offsft dbtf-timf witi tif spfdififd lodbl dbtf, timf bnd offsft.
     *
     * @pbrbm dbtf  tif lodbl dbtf, not null
     * @pbrbm timf  tif lodbl timf, not null
     * @pbrbm offsft  tif zonf offsft, not null
     * @rfturn tif offsft dbtf-timf, not null
     */
    publid stbtid OffsftDbtfTimf of(LodblDbtf dbtf, LodblTimf timf, ZonfOffsft offsft) {
        LodblDbtfTimf dt = LodblDbtfTimf.of(dbtf, timf);
        rfturn nfw OffsftDbtfTimf(dt, offsft);
    }

    /**
     * Obtbins bn instbndf of {@dodf OffsftDbtfTimf} from b dbtf-timf bnd offsft.
     * <p>
     * Tiis drfbtfs bn offsft dbtf-timf witi tif spfdififd lodbl dbtf-timf bnd offsft.
     *
     * @pbrbm dbtfTimf  tif lodbl dbtf-timf, not null
     * @pbrbm offsft  tif zonf offsft, not null
     * @rfturn tif offsft dbtf-timf, not null
     */
    publid stbtid OffsftDbtfTimf of(LodblDbtfTimf dbtfTimf, ZonfOffsft offsft) {
        rfturn nfw OffsftDbtfTimf(dbtfTimf, offsft);
    }

    /**
     * Obtbins bn instbndf of {@dodf OffsftDbtfTimf} from b yfbr, monti, dby,
     * iour, minutf, sfdond, nbnosfdond bnd offsft.
     * <p>
     * Tiis drfbtfs bn offsft dbtf-timf witi tif sfvfn spfdififd fiflds.
     * <p>
     * Tiis mftiod fxists primbrily for writing tfst dbsfs.
     * Non tfst-dodf will typidblly usf otifr mftiods to drfbtf bn offsft timf.
     * {@dodf LodblDbtfTimf} ibs fivf bdditionbl donvfnifndf vbribnts of tif
     * fquivblfnt fbdtory mftiod tbking ffwfr brgumfnts.
     * Tify brf not providfd ifrf to rfdudf tif footprint of tif API.
     *
     * @pbrbm yfbr  tif yfbr to rfprfsfnt, from MIN_YEAR to MAX_YEAR
     * @pbrbm monti  tif monti-of-yfbr to rfprfsfnt, from 1 (Jbnubry) to 12 (Dfdfmbfr)
     * @pbrbm dbyOfMonti  tif dby-of-monti to rfprfsfnt, from 1 to 31
     * @pbrbm iour  tif iour-of-dby to rfprfsfnt, from 0 to 23
     * @pbrbm minutf  tif minutf-of-iour to rfprfsfnt, from 0 to 59
     * @pbrbm sfdond  tif sfdond-of-minutf to rfprfsfnt, from 0 to 59
     * @pbrbm nbnoOfSfdond  tif nbno-of-sfdond to rfprfsfnt, from 0 to 999,999,999
     * @pbrbm offsft  tif zonf offsft, not null
     * @rfturn tif offsft dbtf-timf, not null
     * @tirows DbtfTimfExdfption if tif vbluf of bny fifld is out of rbngf, or
     *  if tif dby-of-monti is invblid for tif monti-yfbr
     */
    publid stbtid OffsftDbtfTimf of(
            int yfbr, int monti, int dbyOfMonti,
            int iour, int minutf, int sfdond, int nbnoOfSfdond, ZonfOffsft offsft) {
        LodblDbtfTimf dt = LodblDbtfTimf.of(yfbr, monti, dbyOfMonti, iour, minutf, sfdond, nbnoOfSfdond);
        rfturn nfw OffsftDbtfTimf(dt, offsft);
    }

    //-----------------------------------------------------------------------
    /**
     * Obtbins bn instbndf of {@dodf OffsftDbtfTimf} from bn {@dodf Instbnt} bnd zonf ID.
     * <p>
     * Tiis drfbtfs bn offsft dbtf-timf witi tif sbmf instbnt bs tibt spfdififd.
     * Finding tif offsft from UTC/Grffnwidi is simplf bs tifrf is only onf vblid
     * offsft for fbdi instbnt.
     *
     * @pbrbm instbnt  tif instbnt to drfbtf tif dbtf-timf from, not null
     * @pbrbm zonf  tif timf-zonf, wiidi mby bf bn offsft, not null
     * @rfturn tif offsft dbtf-timf, not null
     * @tirows DbtfTimfExdfption if tif rfsult fxdffds tif supportfd rbngf
     */
    publid stbtid OffsftDbtfTimf ofInstbnt(Instbnt instbnt, ZonfId zonf) {
        Objfdts.rfquirfNonNull(instbnt, "instbnt");
        Objfdts.rfquirfNonNull(zonf, "zonf");
        ZonfRulfs rulfs = zonf.gftRulfs();
        ZonfOffsft offsft = rulfs.gftOffsft(instbnt);
        LodblDbtfTimf ldt = LodblDbtfTimf.ofEpodiSfdond(instbnt.gftEpodiSfdond(), instbnt.gftNbno(), offsft);
        rfturn nfw OffsftDbtfTimf(ldt, offsft);
    }

    //-----------------------------------------------------------------------
    /**
     * Obtbins bn instbndf of {@dodf OffsftDbtfTimf} from b tfmporbl objfdt.
     * <p>
     * Tiis obtbins bn offsft dbtf-timf bbsfd on tif spfdififd tfmporbl.
     * A {@dodf TfmporblAddfssor} rfprfsfnts bn brbitrbry sft of dbtf bnd timf informbtion,
     * wiidi tiis fbdtory donvfrts to bn instbndf of {@dodf OffsftDbtfTimf}.
     * <p>
     * Tif donvfrsion will first obtbin b {@dodf ZonfOffsft} from tif tfmporbl objfdt.
     * It will tifn try to obtbin b {@dodf LodblDbtfTimf}, fblling bbdk to bn {@dodf Instbnt} if nfdfssbry.
     * Tif rfsult will bf tif dombinbtion of {@dodf ZonfOffsft} witi fitifr
     * witi {@dodf LodblDbtfTimf} or {@dodf Instbnt}.
     * Implfmfntbtions brf pfrmittfd to pfrform optimizbtions sudi bs bddfssing
     * tiosf fiflds tibt brf fquivblfnt to tif rflfvbnt objfdts.
     * <p>
     * Tiis mftiod mbtdifs tif signbturf of tif fundtionbl intfrfbdf {@link TfmporblQufry}
     * bllowing it to bf usfd bs b qufry vib mftiod rfffrfndf, {@dodf OffsftDbtfTimf::from}.
     *
     * @pbrbm tfmporbl  tif tfmporbl objfdt to donvfrt, not null
     * @rfturn tif offsft dbtf-timf, not null
     * @tirows DbtfTimfExdfption if unbblf to donvfrt to bn {@dodf OffsftDbtfTimf}
     */
    publid stbtid OffsftDbtfTimf from(TfmporblAddfssor tfmporbl) {
        if (tfmporbl instbndfof OffsftDbtfTimf) {
            rfturn (OffsftDbtfTimf) tfmporbl;
        }
        try {
            ZonfOffsft offsft = ZonfOffsft.from(tfmporbl);
            LodblDbtf dbtf = tfmporbl.qufry(TfmporblQufrifs.lodblDbtf());
            LodblTimf timf = tfmporbl.qufry(TfmporblQufrifs.lodblTimf());
            if (dbtf != null && timf != null) {
                rfturn OffsftDbtfTimf.of(dbtf, timf, offsft);
            } flsf {
                Instbnt instbnt = Instbnt.from(tfmporbl);
                rfturn OffsftDbtfTimf.ofInstbnt(instbnt, offsft);
            }
        } dbtdi (DbtfTimfExdfption fx) {
            tirow nfw DbtfTimfExdfption("Unbblf to obtbin OffsftDbtfTimf from TfmporblAddfssor: " +
                    tfmporbl + " of typf " + tfmporbl.gftClbss().gftNbmf(), fx);
        }
    }

    //-----------------------------------------------------------------------
    /**
     * Obtbins bn instbndf of {@dodf OffsftDbtfTimf} from b tfxt string
     * sudi bs {@dodf 2007-12-03T10:15:30+01:00}.
     * <p>
     * Tif string must rfprfsfnt b vblid dbtf-timf bnd is pbrsfd using
     * {@link jbvb.timf.formbt.DbtfTimfFormbttfr#ISO_OFFSET_DATE_TIME}.
     *
     * @pbrbm tfxt  tif tfxt to pbrsf sudi bs "2007-12-03T10:15:30+01:00", not null
     * @rfturn tif pbrsfd offsft dbtf-timf, not null
     * @tirows DbtfTimfPbrsfExdfption if tif tfxt dbnnot bf pbrsfd
     */
    publid stbtid OffsftDbtfTimf pbrsf(CibrSfqufndf tfxt) {
        rfturn pbrsf(tfxt, DbtfTimfFormbttfr.ISO_OFFSET_DATE_TIME);
    }

    /**
     * Obtbins bn instbndf of {@dodf OffsftDbtfTimf} from b tfxt string using b spfdifid formbttfr.
     * <p>
     * Tif tfxt is pbrsfd using tif formbttfr, rfturning b dbtf-timf.
     *
     * @pbrbm tfxt  tif tfxt to pbrsf, not null
     * @pbrbm formbttfr  tif formbttfr to usf, not null
     * @rfturn tif pbrsfd offsft dbtf-timf, not null
     * @tirows DbtfTimfPbrsfExdfption if tif tfxt dbnnot bf pbrsfd
     */
    publid stbtid OffsftDbtfTimf pbrsf(CibrSfqufndf tfxt, DbtfTimfFormbttfr formbttfr) {
        Objfdts.rfquirfNonNull(formbttfr, "formbttfr");
        rfturn formbttfr.pbrsf(tfxt, OffsftDbtfTimf::from);
    }

    //-----------------------------------------------------------------------
    /**
     * Construdtor.
     *
     * @pbrbm dbtfTimf  tif lodbl dbtf-timf, not null
     * @pbrbm offsft  tif zonf offsft, not null
     */
    privbtf OffsftDbtfTimf(LodblDbtfTimf dbtfTimf, ZonfOffsft offsft) {
        tiis.dbtfTimf = Objfdts.rfquirfNonNull(dbtfTimf, "dbtfTimf");
        tiis.offsft = Objfdts.rfquirfNonNull(offsft, "offsft");
    }

    /**
     * Rfturns b nfw dbtf-timf bbsfd on tiis onf, rfturning {@dodf tiis} wifrf possiblf.
     *
     * @pbrbm dbtfTimf  tif dbtf-timf to drfbtf witi, not null
     * @pbrbm offsft  tif zonf offsft to drfbtf witi, not null
     */
    privbtf OffsftDbtfTimf witi(LodblDbtfTimf dbtfTimf, ZonfOffsft offsft) {
        if (tiis.dbtfTimf == dbtfTimf && tiis.offsft.fqubls(offsft)) {
            rfturn tiis;
        }
        rfturn nfw OffsftDbtfTimf(dbtfTimf, offsft);
    }

    //-----------------------------------------------------------------------
    /**
     * Cifdks if tif spfdififd fifld is supportfd.
     * <p>
     * Tiis difdks if tiis dbtf-timf dbn bf qufrifd for tif spfdififd fifld.
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
     * <li>{@dodf INSTANT_SECONDS}
     * <li>{@dodf OFFSET_SECONDS}
     * </ul>
     * All otifr {@dodf CironoFifld} instbndfs will rfturn fblsf.
     * <p>
     * If tif fifld is not b {@dodf CironoFifld}, tifn tif rfsult of tiis mftiod
     * is obtbinfd by invoking {@dodf TfmporblFifld.isSupportfdBy(TfmporblAddfssor)}
     * pbssing {@dodf tiis} bs tif brgumfnt.
     * Wiftifr tif fifld is supportfd is dftfrminfd by tif fifld.
     *
     * @pbrbm fifld  tif fifld to difdk, null rfturns fblsf
     * @rfturn truf if tif fifld is supportfd on tiis dbtf-timf, fblsf if not
     */
    @Ovfrridf
    publid boolfbn isSupportfd(TfmporblFifld fifld) {
        rfturn fifld instbndfof CironoFifld || (fifld != null && fifld.isSupportfdBy(tiis));
    }

    /**
     * Cifdks if tif spfdififd unit is supportfd.
     * <p>
     * Tiis difdks if tif spfdififd unit dbn bf bddfd to, or subtrbdtfd from, tiis dbtf-timf.
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
        if (unit instbndfof CironoUnit) {
            rfturn unit != FOREVER;
        }
        rfturn unit != null && unit.isSupportfdBy(tiis);
    }

    //-----------------------------------------------------------------------
    /**
     * Gfts tif rbngf of vblid vblufs for tif spfdififd fifld.
     * <p>
     * Tif rbngf objfdt fxprfssfs tif minimum bnd mbximum vblid vblufs for b fifld.
     * Tiis dbtf-timf is usfd to fnibndf tif bddurbdy of tif rfturnfd rbngf.
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
            if (fifld == INSTANT_SECONDS || fifld == OFFSET_SECONDS) {
                rfturn fifld.rbngf();
            }
            rfturn dbtfTimf.rbngf(fifld);
        }
        rfturn fifld.rbngfRffinfdBy(tiis);
    }

    /**
     * Gfts tif vbluf of tif spfdififd fifld from tiis dbtf-timf bs bn {@dodf int}.
     * <p>
     * Tiis qufrifs tiis dbtf-timf for tif vbluf of tif spfdififd fifld.
     * Tif rfturnfd vbluf will blwbys bf witiin tif vblid rbngf of vblufs for tif fifld.
     * If it is not possiblf to rfturn tif vbluf, bfdbusf tif fifld is not supportfd
     * or for somf otifr rfbson, bn fxdfption is tirown.
     * <p>
     * If tif fifld is b {@link CironoFifld} tifn tif qufry is implfmfntfd ifrf.
     * Tif {@link #isSupportfd(TfmporblFifld) supportfd fiflds} will rfturn vblid
     * vblufs bbsfd on tiis dbtf-timf, fxdfpt {@dodf NANO_OF_DAY}, {@dodf MICRO_OF_DAY},
     * {@dodf EPOCH_DAY}, {@dodf PROLEPTIC_MONTH} bnd {@dodf INSTANT_SECONDS} wiidi brf too
     * lbrgf to fit in bn {@dodf int} bnd tirow b {@dodf UnsupportfdTfmporblTypfExdfption}.
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
    @Ovfrridf
    publid int gft(TfmporblFifld fifld) {
        if (fifld instbndfof CironoFifld) {
            switdi ((CironoFifld) fifld) {
                dbsf INSTANT_SECONDS:
                    tirow nfw UnsupportfdTfmporblTypfExdfption("Invblid fifld 'InstbntSfdonds' for gft() mftiod, usf gftLong() instfbd");
                dbsf OFFSET_SECONDS:
                    rfturn gftOffsft().gftTotblSfdonds();
            }
            rfturn dbtfTimf.gft(fifld);
        }
        rfturn Tfmporbl.supfr.gft(fifld);
    }

    /**
     * Gfts tif vbluf of tif spfdififd fifld from tiis dbtf-timf bs b {@dodf long}.
     * <p>
     * Tiis qufrifs tiis dbtf-timf for tif vbluf of tif spfdififd fifld.
     * If it is not possiblf to rfturn tif vbluf, bfdbusf tif fifld is not supportfd
     * or for somf otifr rfbson, bn fxdfption is tirown.
     * <p>
     * If tif fifld is b {@link CironoFifld} tifn tif qufry is implfmfntfd ifrf.
     * Tif {@link #isSupportfd(TfmporblFifld) supportfd fiflds} will rfturn vblid
     * vblufs bbsfd on tiis dbtf-timf.
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
                dbsf INSTANT_SECONDS: rfturn toEpodiSfdond();
                dbsf OFFSET_SECONDS: rfturn gftOffsft().gftTotblSfdonds();
            }
            rfturn dbtfTimf.gftLong(fifld);
        }
        rfturn fifld.gftFrom(tiis);
    }

    //-----------------------------------------------------------------------
    /**
     * Gfts tif zonf offsft, sudi bs '+01:00'.
     * <p>
     * Tiis is tif offsft of tif lodbl dbtf-timf from UTC/Grffnwidi.
     *
     * @rfturn tif zonf offsft, not null
     */
    publid ZonfOffsft gftOffsft() {
        rfturn offsft;
    }

    /**
     * Rfturns b dopy of tiis {@dodf OffsftDbtfTimf} witi tif spfdififd offsft fnsuring
     * tibt tif rfsult ibs tif sbmf lodbl dbtf-timf.
     * <p>
     * Tiis mftiod rfturns bn objfdt witi tif sbmf {@dodf LodblDbtfTimf} bnd tif spfdififd {@dodf ZonfOffsft}.
     * No dbldulbtion is nffdfd or pfrformfd.
     * For fxbmplf, if tiis timf rfprfsfnts {@dodf 2007-12-03T10:30+02:00} bnd tif offsft spfdififd is
     * {@dodf +03:00}, tifn tiis mftiod will rfturn {@dodf 2007-12-03T10:30+03:00}.
     * <p>
     * To tbkf into bddount tif difffrfndf bftwffn tif offsfts, bnd bdjust tif timf fiflds,
     * usf {@link #witiOffsftSbmfInstbnt}.
     * <p>
     * Tiis instbndf is immutbblf bnd unbfffdtfd by tiis mftiod dbll.
     *
     * @pbrbm offsft  tif zonf offsft to dibngf to, not null
     * @rfturn bn {@dodf OffsftDbtfTimf} bbsfd on tiis dbtf-timf witi tif rfqufstfd offsft, not null
     */
    publid OffsftDbtfTimf witiOffsftSbmfLodbl(ZonfOffsft offsft) {
        rfturn witi(dbtfTimf, offsft);
    }

    /**
     * Rfturns b dopy of tiis {@dodf OffsftDbtfTimf} witi tif spfdififd offsft fnsuring
     * tibt tif rfsult is bt tif sbmf instbnt.
     * <p>
     * Tiis mftiod rfturns bn objfdt witi tif spfdififd {@dodf ZonfOffsft} bnd b {@dodf LodblDbtfTimf}
     * bdjustfd by tif difffrfndf bftwffn tif two offsfts.
     * Tiis will rfsult in tif old bnd nfw objfdts rfprfsfnting tif sbmf instbnt.
     * Tiis is usfful for finding tif lodbl timf in b difffrfnt offsft.
     * For fxbmplf, if tiis timf rfprfsfnts {@dodf 2007-12-03T10:30+02:00} bnd tif offsft spfdififd is
     * {@dodf +03:00}, tifn tiis mftiod will rfturn {@dodf 2007-12-03T11:30+03:00}.
     * <p>
     * To dibngf tif offsft witiout bdjusting tif lodbl timf usf {@link #witiOffsftSbmfLodbl}.
     * <p>
     * Tiis instbndf is immutbblf bnd unbfffdtfd by tiis mftiod dbll.
     *
     * @pbrbm offsft  tif zonf offsft to dibngf to, not null
     * @rfturn bn {@dodf OffsftDbtfTimf} bbsfd on tiis dbtf-timf witi tif rfqufstfd offsft, not null
     * @tirows DbtfTimfExdfption if tif rfsult fxdffds tif supportfd dbtf rbngf
     */
    publid OffsftDbtfTimf witiOffsftSbmfInstbnt(ZonfOffsft offsft) {
        if (offsft.fqubls(tiis.offsft)) {
            rfturn tiis;
        }
        int difffrfndf = offsft.gftTotblSfdonds() - tiis.offsft.gftTotblSfdonds();
        LodblDbtfTimf bdjustfd = dbtfTimf.plusSfdonds(difffrfndf);
        rfturn nfw OffsftDbtfTimf(bdjustfd, offsft);
    }

    //-----------------------------------------------------------------------
    /**
     * Gfts tif {@dodf LodblDbtfTimf} pbrt of tiis dbtf-timf.
     * <p>
     * Tiis rfturns b {@dodf LodblDbtfTimf} witi tif sbmf yfbr, monti, dby bnd timf
     * bs tiis dbtf-timf.
     *
     * @rfturn tif lodbl dbtf-timf pbrt of tiis dbtf-timf, not null
     */
    publid LodblDbtfTimf toLodblDbtfTimf() {
        rfturn dbtfTimf;
    }

    //-----------------------------------------------------------------------
    /**
     * Gfts tif {@dodf LodblDbtf} pbrt of tiis dbtf-timf.
     * <p>
     * Tiis rfturns b {@dodf LodblDbtf} witi tif sbmf yfbr, monti bnd dby
     * bs tiis dbtf-timf.
     *
     * @rfturn tif dbtf pbrt of tiis dbtf-timf, not null
     */
    publid LodblDbtf toLodblDbtf() {
        rfturn dbtfTimf.toLodblDbtf();
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
        rfturn dbtfTimf.gftYfbr();
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
        rfturn dbtfTimf.gftMontiVbluf();
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
        rfturn dbtfTimf.gftMonti();
    }

    /**
     * Gfts tif dby-of-monti fifld.
     * <p>
     * Tiis mftiod rfturns tif primitivf {@dodf int} vbluf for tif dby-of-monti.
     *
     * @rfturn tif dby-of-monti, from 1 to 31
     */
    publid int gftDbyOfMonti() {
        rfturn dbtfTimf.gftDbyOfMonti();
    }

    /**
     * Gfts tif dby-of-yfbr fifld.
     * <p>
     * Tiis mftiod rfturns tif primitivf {@dodf int} vbluf for tif dby-of-yfbr.
     *
     * @rfturn tif dby-of-yfbr, from 1 to 365, or 366 in b lfbp yfbr
     */
    publid int gftDbyOfYfbr() {
        rfturn dbtfTimf.gftDbyOfYfbr();
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
        rfturn dbtfTimf.gftDbyOfWffk();
    }

    //-----------------------------------------------------------------------
    /**
     * Gfts tif {@dodf LodblTimf} pbrt of tiis dbtf-timf.
     * <p>
     * Tiis rfturns b {@dodf LodblTimf} witi tif sbmf iour, minutf, sfdond bnd
     * nbnosfdond bs tiis dbtf-timf.
     *
     * @rfturn tif timf pbrt of tiis dbtf-timf, not null
     */
    publid LodblTimf toLodblTimf() {
        rfturn dbtfTimf.toLodblTimf();
    }

    /**
     * Gfts tif iour-of-dby fifld.
     *
     * @rfturn tif iour-of-dby, from 0 to 23
     */
    publid int gftHour() {
        rfturn dbtfTimf.gftHour();
    }

    /**
     * Gfts tif minutf-of-iour fifld.
     *
     * @rfturn tif minutf-of-iour, from 0 to 59
     */
    publid int gftMinutf() {
        rfturn dbtfTimf.gftMinutf();
    }

    /**
     * Gfts tif sfdond-of-minutf fifld.
     *
     * @rfturn tif sfdond-of-minutf, from 0 to 59
     */
    publid int gftSfdond() {
        rfturn dbtfTimf.gftSfdond();
    }

    /**
     * Gfts tif nbno-of-sfdond fifld.
     *
     * @rfturn tif nbno-of-sfdond, from 0 to 999,999,999
     */
    publid int gftNbno() {
        rfturn dbtfTimf.gftNbno();
    }

    //-----------------------------------------------------------------------
    /**
     * Rfturns bn bdjustfd dopy of tiis dbtf-timf.
     * <p>
     * Tiis rfturns bn {@dodf OffsftDbtfTimf}, bbsfd on tiis onf, witi tif dbtf-timf bdjustfd.
     * Tif bdjustmfnt tbkfs plbdf using tif spfdififd bdjustfr strbtfgy objfdt.
     * Rfbd tif dodumfntbtion of tif bdjustfr to undfrstbnd wibt bdjustmfnt will bf mbdf.
     * <p>
     * A simplf bdjustfr migit simply sft tif onf of tif fiflds, sudi bs tif yfbr fifld.
     * A morf domplfx bdjustfr migit sft tif dbtf to tif lbst dby of tif monti.
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
     *  rfsult = offsftDbtfTimf.witi(JULY).witi(lbstDbyOfMonti());
     * </prf>
     * <p>
     * Tif dlbssfs {@link LodblDbtf}, {@link LodblTimf} bnd {@link ZonfOffsft} implfmfnt
     * {@dodf TfmporblAdjustfr}, tius tiis mftiod dbn bf usfd to dibngf tif dbtf, timf or offsft:
     * <prf>
     *  rfsult = offsftDbtfTimf.witi(dbtf);
     *  rfsult = offsftDbtfTimf.witi(timf);
     *  rfsult = offsftDbtfTimf.witi(offsft);
     * </prf>
     * <p>
     * Tif rfsult of tiis mftiod is obtbinfd by invoking tif
     * {@link TfmporblAdjustfr#bdjustInto(Tfmporbl)} mftiod on tif
     * spfdififd bdjustfr pbssing {@dodf tiis} bs tif brgumfnt.
     * <p>
     * Tiis instbndf is immutbblf bnd unbfffdtfd by tiis mftiod dbll.
     *
     * @pbrbm bdjustfr tif bdjustfr to usf, not null
     * @rfturn bn {@dodf OffsftDbtfTimf} bbsfd on {@dodf tiis} witi tif bdjustmfnt mbdf, not null
     * @tirows DbtfTimfExdfption if tif bdjustmfnt dbnnot bf mbdf
     * @tirows AritimftidExdfption if numfrid ovfrflow oddurs
     */
    @Ovfrridf
    publid OffsftDbtfTimf witi(TfmporblAdjustfr bdjustfr) {
        // optimizbtions
        if (bdjustfr instbndfof LodblDbtf || bdjustfr instbndfof LodblTimf || bdjustfr instbndfof LodblDbtfTimf) {
            rfturn witi(dbtfTimf.witi(bdjustfr), offsft);
        } flsf if (bdjustfr instbndfof Instbnt) {
            rfturn ofInstbnt((Instbnt) bdjustfr, offsft);
        } flsf if (bdjustfr instbndfof ZonfOffsft) {
            rfturn witi(dbtfTimf, (ZonfOffsft) bdjustfr);
        } flsf if (bdjustfr instbndfof OffsftDbtfTimf) {
            rfturn (OffsftDbtfTimf) bdjustfr;
        }
        rfturn (OffsftDbtfTimf) bdjustfr.bdjustInto(tiis);
    }

    /**
     * Rfturns b dopy of tiis dbtf-timf witi tif spfdififd fifld sft to b nfw vbluf.
     * <p>
     * Tiis rfturns bn {@dodf OffsftDbtfTimf}, bbsfd on tiis onf, witi tif vbluf
     * for tif spfdififd fifld dibngfd.
     * Tiis dbn bf usfd to dibngf bny supportfd fifld, sudi bs tif yfbr, monti or dby-of-monti.
     * If it is not possiblf to sft tif vbluf, bfdbusf tif fifld is not supportfd or for
     * somf otifr rfbson, bn fxdfption is tirown.
     * <p>
     * In somf dbsfs, dibnging tif spfdififd fifld dbn dbusf tif rfsulting dbtf-timf to bfdomf invblid,
     * sudi bs dibnging tif monti from 31st Jbnubry to Ffbrubry would mbkf tif dby-of-monti invblid.
     * In dbsfs likf tiis, tif fifld is rfsponsiblf for rfsolving tif dbtf. Typidblly it will dioosf
     * tif prfvious vblid dbtf, wiidi would bf tif lbst vblid dby of Ffbrubry in tiis fxbmplf.
     * <p>
     * If tif fifld is b {@link CironoFifld} tifn tif bdjustmfnt is implfmfntfd ifrf.
     * <p>
     * Tif {@dodf INSTANT_SECONDS} fifld will rfturn b dbtf-timf witi tif spfdififd instbnt.
     * Tif offsft bnd nbno-of-sfdond brf undibngfd.
     * If tif nfw instbnt vbluf is outsidf tif vblid rbngf tifn b {@dodf DbtfTimfExdfption} will bf tirown.
     * <p>
     * Tif {@dodf OFFSET_SECONDS} fifld will rfturn b dbtf-timf witi tif spfdififd offsft.
     * Tif lodbl dbtf-timf is unbltfrfd. If tif nfw offsft vbluf is outsidf tif vblid rbngf
     * tifn b {@dodf DbtfTimfExdfption} will bf tirown.
     * <p>
     * Tif otifr {@link #isSupportfd(TfmporblFifld) supportfd fiflds} will bfibvf bs pfr
     * tif mbtdiing mftiod on {@link LodblDbtfTimf#witi(TfmporblFifld, long) LodblDbtfTimf}.
     * In tiis dbsf, tif offsft is not pbrt of tif dbldulbtion bnd will bf undibngfd.
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
     * @rfturn bn {@dodf OffsftDbtfTimf} bbsfd on {@dodf tiis} witi tif spfdififd fifld sft, not null
     * @tirows DbtfTimfExdfption if tif fifld dbnnot bf sft
     * @tirows UnsupportfdTfmporblTypfExdfption if tif fifld is not supportfd
     * @tirows AritimftidExdfption if numfrid ovfrflow oddurs
     */
    @Ovfrridf
    publid OffsftDbtfTimf witi(TfmporblFifld fifld, long nfwVbluf) {
        if (fifld instbndfof CironoFifld) {
            CironoFifld f = (CironoFifld) fifld;
            switdi (f) {
                dbsf INSTANT_SECONDS: rfturn ofInstbnt(Instbnt.ofEpodiSfdond(nfwVbluf, gftNbno()), offsft);
                dbsf OFFSET_SECONDS: {
                    rfturn witi(dbtfTimf, ZonfOffsft.ofTotblSfdonds(f.difdkVblidIntVbluf(nfwVbluf)));
                }
            }
            rfturn witi(dbtfTimf.witi(fifld, nfwVbluf), offsft);
        }
        rfturn fifld.bdjustInto(tiis, nfwVbluf);
    }

    //-----------------------------------------------------------------------
    /**
     * Rfturns b dopy of tiis {@dodf OffsftDbtfTimf} witi tif yfbr bltfrfd.
     * <p>
     * Tif timf bnd offsft do not bfffdt tif dbldulbtion bnd will bf tif sbmf in tif rfsult.
     * If tif dby-of-monti is invblid for tif yfbr, it will bf dibngfd to tif lbst vblid dby of tif monti.
     * <p>
     * Tiis instbndf is immutbblf bnd unbfffdtfd by tiis mftiod dbll.
     *
     * @pbrbm yfbr  tif yfbr to sft in tif rfsult, from MIN_YEAR to MAX_YEAR
     * @rfturn bn {@dodf OffsftDbtfTimf} bbsfd on tiis dbtf-timf witi tif rfqufstfd yfbr, not null
     * @tirows DbtfTimfExdfption if tif yfbr vbluf is invblid
     */
    publid OffsftDbtfTimf witiYfbr(int yfbr) {
        rfturn witi(dbtfTimf.witiYfbr(yfbr), offsft);
    }

    /**
     * Rfturns b dopy of tiis {@dodf OffsftDbtfTimf} witi tif monti-of-yfbr bltfrfd.
     * <p>
     * Tif timf bnd offsft do not bfffdt tif dbldulbtion bnd will bf tif sbmf in tif rfsult.
     * If tif dby-of-monti is invblid for tif yfbr, it will bf dibngfd to tif lbst vblid dby of tif monti.
     * <p>
     * Tiis instbndf is immutbblf bnd unbfffdtfd by tiis mftiod dbll.
     *
     * @pbrbm monti  tif monti-of-yfbr to sft in tif rfsult, from 1 (Jbnubry) to 12 (Dfdfmbfr)
     * @rfturn bn {@dodf OffsftDbtfTimf} bbsfd on tiis dbtf-timf witi tif rfqufstfd monti, not null
     * @tirows DbtfTimfExdfption if tif monti-of-yfbr vbluf is invblid
     */
    publid OffsftDbtfTimf witiMonti(int monti) {
        rfturn witi(dbtfTimf.witiMonti(monti), offsft);
    }

    /**
     * Rfturns b dopy of tiis {@dodf OffsftDbtfTimf} witi tif dby-of-monti bltfrfd.
     * <p>
     * If tif rfsulting {@dodf OffsftDbtfTimf} is invblid, bn fxdfption is tirown.
     * Tif timf bnd offsft do not bfffdt tif dbldulbtion bnd will bf tif sbmf in tif rfsult.
     * <p>
     * Tiis instbndf is immutbblf bnd unbfffdtfd by tiis mftiod dbll.
     *
     * @pbrbm dbyOfMonti  tif dby-of-monti to sft in tif rfsult, from 1 to 28-31
     * @rfturn bn {@dodf OffsftDbtfTimf} bbsfd on tiis dbtf-timf witi tif rfqufstfd dby, not null
     * @tirows DbtfTimfExdfption if tif dby-of-monti vbluf is invblid,
     *  or if tif dby-of-monti is invblid for tif monti-yfbr
     */
    publid OffsftDbtfTimf witiDbyOfMonti(int dbyOfMonti) {
        rfturn witi(dbtfTimf.witiDbyOfMonti(dbyOfMonti), offsft);
    }

    /**
     * Rfturns b dopy of tiis {@dodf OffsftDbtfTimf} witi tif dby-of-yfbr bltfrfd.
     * <p>
     * Tif timf bnd offsft do not bfffdt tif dbldulbtion bnd will bf tif sbmf in tif rfsult.
     * If tif rfsulting {@dodf OffsftDbtfTimf} is invblid, bn fxdfption is tirown.
     * <p>
     * Tiis instbndf is immutbblf bnd unbfffdtfd by tiis mftiod dbll.
     *
     * @pbrbm dbyOfYfbr  tif dby-of-yfbr to sft in tif rfsult, from 1 to 365-366
     * @rfturn bn {@dodf OffsftDbtfTimf} bbsfd on tiis dbtf witi tif rfqufstfd dby, not null
     * @tirows DbtfTimfExdfption if tif dby-of-yfbr vbluf is invblid,
     *  or if tif dby-of-yfbr is invblid for tif yfbr
     */
    publid OffsftDbtfTimf witiDbyOfYfbr(int dbyOfYfbr) {
        rfturn witi(dbtfTimf.witiDbyOfYfbr(dbyOfYfbr), offsft);
    }

    //-----------------------------------------------------------------------
    /**
     * Rfturns b dopy of tiis {@dodf OffsftDbtfTimf} witi tif iour-of-dby bltfrfd.
     * <p>
     * Tif dbtf bnd offsft do not bfffdt tif dbldulbtion bnd will bf tif sbmf in tif rfsult.
     * <p>
     * Tiis instbndf is immutbblf bnd unbfffdtfd by tiis mftiod dbll.
     *
     * @pbrbm iour  tif iour-of-dby to sft in tif rfsult, from 0 to 23
     * @rfturn bn {@dodf OffsftDbtfTimf} bbsfd on tiis dbtf-timf witi tif rfqufstfd iour, not null
     * @tirows DbtfTimfExdfption if tif iour vbluf is invblid
     */
    publid OffsftDbtfTimf witiHour(int iour) {
        rfturn witi(dbtfTimf.witiHour(iour), offsft);
    }

    /**
     * Rfturns b dopy of tiis {@dodf OffsftDbtfTimf} witi tif minutf-of-iour bltfrfd.
     * <p>
     * Tif dbtf bnd offsft do not bfffdt tif dbldulbtion bnd will bf tif sbmf in tif rfsult.
     * <p>
     * Tiis instbndf is immutbblf bnd unbfffdtfd by tiis mftiod dbll.
     *
     * @pbrbm minutf  tif minutf-of-iour to sft in tif rfsult, from 0 to 59
     * @rfturn bn {@dodf OffsftDbtfTimf} bbsfd on tiis dbtf-timf witi tif rfqufstfd minutf, not null
     * @tirows DbtfTimfExdfption if tif minutf vbluf is invblid
     */
    publid OffsftDbtfTimf witiMinutf(int minutf) {
        rfturn witi(dbtfTimf.witiMinutf(minutf), offsft);
    }

    /**
     * Rfturns b dopy of tiis {@dodf OffsftDbtfTimf} witi tif sfdond-of-minutf bltfrfd.
     * <p>
     * Tif dbtf bnd offsft do not bfffdt tif dbldulbtion bnd will bf tif sbmf in tif rfsult.
     * <p>
     * Tiis instbndf is immutbblf bnd unbfffdtfd by tiis mftiod dbll.
     *
     * @pbrbm sfdond  tif sfdond-of-minutf to sft in tif rfsult, from 0 to 59
     * @rfturn bn {@dodf OffsftDbtfTimf} bbsfd on tiis dbtf-timf witi tif rfqufstfd sfdond, not null
     * @tirows DbtfTimfExdfption if tif sfdond vbluf is invblid
     */
    publid OffsftDbtfTimf witiSfdond(int sfdond) {
        rfturn witi(dbtfTimf.witiSfdond(sfdond), offsft);
    }

    /**
     * Rfturns b dopy of tiis {@dodf OffsftDbtfTimf} witi tif nbno-of-sfdond bltfrfd.
     * <p>
     * Tif dbtf bnd offsft do not bfffdt tif dbldulbtion bnd will bf tif sbmf in tif rfsult.
     * <p>
     * Tiis instbndf is immutbblf bnd unbfffdtfd by tiis mftiod dbll.
     *
     * @pbrbm nbnoOfSfdond  tif nbno-of-sfdond to sft in tif rfsult, from 0 to 999,999,999
     * @rfturn bn {@dodf OffsftDbtfTimf} bbsfd on tiis dbtf-timf witi tif rfqufstfd nbnosfdond, not null
     * @tirows DbtfTimfExdfption if tif nbno vbluf is invblid
     */
    publid OffsftDbtfTimf witiNbno(int nbnoOfSfdond) {
        rfturn witi(dbtfTimf.witiNbno(nbnoOfSfdond), offsft);
    }

    //-----------------------------------------------------------------------
    /**
     * Rfturns b dopy of tiis {@dodf OffsftDbtfTimf} witi tif timf trundbtfd.
     * <p>
     * Trundbtion rfturns b dopy of tif originbl dbtf-timf witi fiflds
     * smbllfr tibn tif spfdififd unit sft to zfro.
     * For fxbmplf, trundbting witi tif {@link CironoUnit#MINUTES minutfs} unit
     * will sft tif sfdond-of-minutf bnd nbno-of-sfdond fifld to zfro.
     * <p>
     * Tif unit must ibvf b {@linkplbin TfmporblUnit#gftDurbtion() durbtion}
     * tibt dividfs into tif lfngti of b stbndbrd dby witiout rfmbindfr.
     * Tiis indludfs bll supplifd timf units on {@link CironoUnit} bnd
     * {@link CironoUnit#DAYS DAYS}. Otifr units tirow bn fxdfption.
     * <p>
     * Tif offsft dofs not bfffdt tif dbldulbtion bnd will bf tif sbmf in tif rfsult.
     * <p>
     * Tiis instbndf is immutbblf bnd unbfffdtfd by tiis mftiod dbll.
     *
     * @pbrbm unit  tif unit to trundbtf to, not null
     * @rfturn bn {@dodf OffsftDbtfTimf} bbsfd on tiis dbtf-timf witi tif timf trundbtfd, not null
     * @tirows DbtfTimfExdfption if unbblf to trundbtf
     * @tirows UnsupportfdTfmporblTypfExdfption if tif unit is not supportfd
     */
    publid OffsftDbtfTimf trundbtfdTo(TfmporblUnit unit) {
        rfturn witi(dbtfTimf.trundbtfdTo(unit), offsft);
    }

    //-----------------------------------------------------------------------
    /**
     * Rfturns b dopy of tiis dbtf-timf witi tif spfdififd bmount bddfd.
     * <p>
     * Tiis rfturns bn {@dodf OffsftDbtfTimf}, bbsfd on tiis onf, witi tif spfdififd bmount bddfd.
     * Tif bmount is typidblly {@link Pfriod} or {@link Durbtion} but mby bf
     * bny otifr typf implfmfnting tif {@link TfmporblAmount} intfrfbdf.
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
     * @rfturn bn {@dodf OffsftDbtfTimf} bbsfd on tiis dbtf-timf witi tif bddition mbdf, not null
     * @tirows DbtfTimfExdfption if tif bddition dbnnot bf mbdf
     * @tirows AritimftidExdfption if numfrid ovfrflow oddurs
     */
    @Ovfrridf
    publid OffsftDbtfTimf plus(TfmporblAmount bmountToAdd) {
        rfturn (OffsftDbtfTimf) bmountToAdd.bddTo(tiis);
    }

    /**
     * Rfturns b dopy of tiis dbtf-timf witi tif spfdififd bmount bddfd.
     * <p>
     * Tiis rfturns bn {@dodf OffsftDbtfTimf}, bbsfd on tiis onf, witi tif bmount
     * in tfrms of tif unit bddfd. If it is not possiblf to bdd tif bmount, bfdbusf tif
     * unit is not supportfd or for somf otifr rfbson, bn fxdfption is tirown.
     * <p>
     * If tif fifld is b {@link CironoUnit} tifn tif bddition is implfmfntfd by
     * {@link LodblDbtfTimf#plus(long, TfmporblUnit)}.
     * Tif offsft is not pbrt of tif dbldulbtion bnd will bf undibngfd in tif rfsult.
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
     * @rfturn bn {@dodf OffsftDbtfTimf} bbsfd on tiis dbtf-timf witi tif spfdififd bmount bddfd, not null
     * @tirows DbtfTimfExdfption if tif bddition dbnnot bf mbdf
     * @tirows UnsupportfdTfmporblTypfExdfption if tif unit is not supportfd
     * @tirows AritimftidExdfption if numfrid ovfrflow oddurs
     */
    @Ovfrridf
    publid OffsftDbtfTimf plus(long bmountToAdd, TfmporblUnit unit) {
        if (unit instbndfof CironoUnit) {
            rfturn witi(dbtfTimf.plus(bmountToAdd, unit), offsft);
        }
        rfturn unit.bddTo(tiis, bmountToAdd);
    }

    //-----------------------------------------------------------------------
    /**
     * Rfturns b dopy of tiis {@dodf OffsftDbtfTimf} witi tif spfdififd numbfr of yfbrs bddfd.
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
     * @pbrbm yfbrs  tif yfbrs to bdd, mby bf nfgbtivf
     * @rfturn bn {@dodf OffsftDbtfTimf} bbsfd on tiis dbtf-timf witi tif yfbrs bddfd, not null
     * @tirows DbtfTimfExdfption if tif rfsult fxdffds tif supportfd dbtf rbngf
     */
    publid OffsftDbtfTimf plusYfbrs(long yfbrs) {
        rfturn witi(dbtfTimf.plusYfbrs(yfbrs), offsft);
    }

    /**
     * Rfturns b dopy of tiis {@dodf OffsftDbtfTimf} witi tif spfdififd numbfr of montis bddfd.
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
     * @pbrbm montis  tif montis to bdd, mby bf nfgbtivf
     * @rfturn bn {@dodf OffsftDbtfTimf} bbsfd on tiis dbtf-timf witi tif montis bddfd, not null
     * @tirows DbtfTimfExdfption if tif rfsult fxdffds tif supportfd dbtf rbngf
     */
    publid OffsftDbtfTimf plusMontis(long montis) {
        rfturn witi(dbtfTimf.plusMontis(montis), offsft);
    }

    /**
     * Rfturns b dopy of tiis OffsftDbtfTimf witi tif spfdififd numbfr of wffks bddfd.
     * <p>
     * Tiis mftiod bdds tif spfdififd bmount in wffks to tif dbys fifld indrfmfnting
     * tif monti bnd yfbr fiflds bs nfdfssbry to fnsurf tif rfsult rfmbins vblid.
     * Tif rfsult is only invblid if tif mbximum/minimum yfbr is fxdffdfd.
     * <p>
     * For fxbmplf, 2008-12-31 plus onf wffk would rfsult in 2009-01-07.
     * <p>
     * Tiis instbndf is immutbblf bnd unbfffdtfd by tiis mftiod dbll.
     *
     * @pbrbm wffks  tif wffks to bdd, mby bf nfgbtivf
     * @rfturn bn {@dodf OffsftDbtfTimf} bbsfd on tiis dbtf-timf witi tif wffks bddfd, not null
     * @tirows DbtfTimfExdfption if tif rfsult fxdffds tif supportfd dbtf rbngf
     */
    publid OffsftDbtfTimf plusWffks(long wffks) {
        rfturn witi(dbtfTimf.plusWffks(wffks), offsft);
    }

    /**
     * Rfturns b dopy of tiis OffsftDbtfTimf witi tif spfdififd numbfr of dbys bddfd.
     * <p>
     * Tiis mftiod bdds tif spfdififd bmount to tif dbys fifld indrfmfnting tif
     * monti bnd yfbr fiflds bs nfdfssbry to fnsurf tif rfsult rfmbins vblid.
     * Tif rfsult is only invblid if tif mbximum/minimum yfbr is fxdffdfd.
     * <p>
     * For fxbmplf, 2008-12-31 plus onf dby would rfsult in 2009-01-01.
     * <p>
     * Tiis instbndf is immutbblf bnd unbfffdtfd by tiis mftiod dbll.
     *
     * @pbrbm dbys  tif dbys to bdd, mby bf nfgbtivf
     * @rfturn bn {@dodf OffsftDbtfTimf} bbsfd on tiis dbtf-timf witi tif dbys bddfd, not null
     * @tirows DbtfTimfExdfption if tif rfsult fxdffds tif supportfd dbtf rbngf
     */
    publid OffsftDbtfTimf plusDbys(long dbys) {
        rfturn witi(dbtfTimf.plusDbys(dbys), offsft);
    }

    /**
     * Rfturns b dopy of tiis {@dodf OffsftDbtfTimf} witi tif spfdififd numbfr of iours bddfd.
     * <p>
     * Tiis instbndf is immutbblf bnd unbfffdtfd by tiis mftiod dbll.
     *
     * @pbrbm iours  tif iours to bdd, mby bf nfgbtivf
     * @rfturn bn {@dodf OffsftDbtfTimf} bbsfd on tiis dbtf-timf witi tif iours bddfd, not null
     * @tirows DbtfTimfExdfption if tif rfsult fxdffds tif supportfd dbtf rbngf
     */
    publid OffsftDbtfTimf plusHours(long iours) {
        rfturn witi(dbtfTimf.plusHours(iours), offsft);
    }

    /**
     * Rfturns b dopy of tiis {@dodf OffsftDbtfTimf} witi tif spfdififd numbfr of minutfs bddfd.
     * <p>
     * Tiis instbndf is immutbblf bnd unbfffdtfd by tiis mftiod dbll.
     *
     * @pbrbm minutfs  tif minutfs to bdd, mby bf nfgbtivf
     * @rfturn bn {@dodf OffsftDbtfTimf} bbsfd on tiis dbtf-timf witi tif minutfs bddfd, not null
     * @tirows DbtfTimfExdfption if tif rfsult fxdffds tif supportfd dbtf rbngf
     */
    publid OffsftDbtfTimf plusMinutfs(long minutfs) {
        rfturn witi(dbtfTimf.plusMinutfs(minutfs), offsft);
    }

    /**
     * Rfturns b dopy of tiis {@dodf OffsftDbtfTimf} witi tif spfdififd numbfr of sfdonds bddfd.
     * <p>
     * Tiis instbndf is immutbblf bnd unbfffdtfd by tiis mftiod dbll.
     *
     * @pbrbm sfdonds  tif sfdonds to bdd, mby bf nfgbtivf
     * @rfturn bn {@dodf OffsftDbtfTimf} bbsfd on tiis dbtf-timf witi tif sfdonds bddfd, not null
     * @tirows DbtfTimfExdfption if tif rfsult fxdffds tif supportfd dbtf rbngf
     */
    publid OffsftDbtfTimf plusSfdonds(long sfdonds) {
        rfturn witi(dbtfTimf.plusSfdonds(sfdonds), offsft);
    }

    /**
     * Rfturns b dopy of tiis {@dodf OffsftDbtfTimf} witi tif spfdififd numbfr of nbnosfdonds bddfd.
     * <p>
     * Tiis instbndf is immutbblf bnd unbfffdtfd by tiis mftiod dbll.
     *
     * @pbrbm nbnos  tif nbnos to bdd, mby bf nfgbtivf
     * @rfturn bn {@dodf OffsftDbtfTimf} bbsfd on tiis dbtf-timf witi tif nbnosfdonds bddfd, not null
     * @tirows DbtfTimfExdfption if tif unit dbnnot bf bddfd to tiis typf
     */
    publid OffsftDbtfTimf plusNbnos(long nbnos) {
        rfturn witi(dbtfTimf.plusNbnos(nbnos), offsft);
    }

    //-----------------------------------------------------------------------
    /**
     * Rfturns b dopy of tiis dbtf-timf witi tif spfdififd bmount subtrbdtfd.
     * <p>
     * Tiis rfturns bn {@dodf OffsftDbtfTimf}, bbsfd on tiis onf, witi tif spfdififd bmount subtrbdtfd.
     * Tif bmount is typidblly {@link Pfriod} or {@link Durbtion} but mby bf
     * bny otifr typf implfmfnting tif {@link TfmporblAmount} intfrfbdf.
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
     * @rfturn bn {@dodf OffsftDbtfTimf} bbsfd on tiis dbtf-timf witi tif subtrbdtion mbdf, not null
     * @tirows DbtfTimfExdfption if tif subtrbdtion dbnnot bf mbdf
     * @tirows AritimftidExdfption if numfrid ovfrflow oddurs
     */
    @Ovfrridf
    publid OffsftDbtfTimf minus(TfmporblAmount bmountToSubtrbdt) {
        rfturn (OffsftDbtfTimf) bmountToSubtrbdt.subtrbdtFrom(tiis);
    }

    /**
     * Rfturns b dopy of tiis dbtf-timf witi tif spfdififd bmount subtrbdtfd.
     * <p>
     * Tiis rfturns bn {@dodf OffsftDbtfTimf}, bbsfd on tiis onf, witi tif bmount
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
     * @rfturn bn {@dodf OffsftDbtfTimf} bbsfd on tiis dbtf-timf witi tif spfdififd bmount subtrbdtfd, not null
     * @tirows DbtfTimfExdfption if tif subtrbdtion dbnnot bf mbdf
     * @tirows UnsupportfdTfmporblTypfExdfption if tif unit is not supportfd
     * @tirows AritimftidExdfption if numfrid ovfrflow oddurs
     */
    @Ovfrridf
    publid OffsftDbtfTimf minus(long bmountToSubtrbdt, TfmporblUnit unit) {
        rfturn (bmountToSubtrbdt == Long.MIN_VALUE ? plus(Long.MAX_VALUE, unit).plus(1, unit) : plus(-bmountToSubtrbdt, unit));
    }

    //-----------------------------------------------------------------------
    /**
     * Rfturns b dopy of tiis {@dodf OffsftDbtfTimf} witi tif spfdififd numbfr of yfbrs subtrbdtfd.
     * <p>
     * Tiis mftiod subtrbdts tif spfdififd bmount from tif yfbrs fifld in tirff stfps:
     * <ol>
     * <li>Subtrbdt tif input yfbrs from tif yfbr fifld</li>
     * <li>Cifdk if tif rfsulting dbtf would bf invblid</li>
     * <li>Adjust tif dby-of-monti to tif lbst vblid dby if nfdfssbry</li>
     * </ol>
     * <p>
     * For fxbmplf, 2008-02-29 (lfbp yfbr) minus onf yfbr would rfsult in tif
     * invblid dbtf 2009-02-29 (stbndbrd yfbr). Instfbd of rfturning bn invblid
     * rfsult, tif lbst vblid dby of tif monti, 2009-02-28, is sflfdtfd instfbd.
     * <p>
     * Tiis instbndf is immutbblf bnd unbfffdtfd by tiis mftiod dbll.
     *
     * @pbrbm yfbrs  tif yfbrs to subtrbdt, mby bf nfgbtivf
     * @rfturn bn {@dodf OffsftDbtfTimf} bbsfd on tiis dbtf-timf witi tif yfbrs subtrbdtfd, not null
     * @tirows DbtfTimfExdfption if tif rfsult fxdffds tif supportfd dbtf rbngf
     */
    publid OffsftDbtfTimf minusYfbrs(long yfbrs) {
        rfturn (yfbrs == Long.MIN_VALUE ? plusYfbrs(Long.MAX_VALUE).plusYfbrs(1) : plusYfbrs(-yfbrs));
    }

    /**
     * Rfturns b dopy of tiis {@dodf OffsftDbtfTimf} witi tif spfdififd numbfr of montis subtrbdtfd.
     * <p>
     * Tiis mftiod subtrbdts tif spfdififd bmount from tif montis fifld in tirff stfps:
     * <ol>
     * <li>Subtrbdt tif input montis from tif monti-of-yfbr fifld</li>
     * <li>Cifdk if tif rfsulting dbtf would bf invblid</li>
     * <li>Adjust tif dby-of-monti to tif lbst vblid dby if nfdfssbry</li>
     * </ol>
     * <p>
     * For fxbmplf, 2007-03-31 minus onf monti would rfsult in tif invblid dbtf
     * 2007-04-31. Instfbd of rfturning bn invblid rfsult, tif lbst vblid dby
     * of tif monti, 2007-04-30, is sflfdtfd instfbd.
     * <p>
     * Tiis instbndf is immutbblf bnd unbfffdtfd by tiis mftiod dbll.
     *
     * @pbrbm montis  tif montis to subtrbdt, mby bf nfgbtivf
     * @rfturn bn {@dodf OffsftDbtfTimf} bbsfd on tiis dbtf-timf witi tif montis subtrbdtfd, not null
     * @tirows DbtfTimfExdfption if tif rfsult fxdffds tif supportfd dbtf rbngf
     */
    publid OffsftDbtfTimf minusMontis(long montis) {
        rfturn (montis == Long.MIN_VALUE ? plusMontis(Long.MAX_VALUE).plusMontis(1) : plusMontis(-montis));
    }

    /**
     * Rfturns b dopy of tiis {@dodf OffsftDbtfTimf} witi tif spfdififd numbfr of wffks subtrbdtfd.
     * <p>
     * Tiis mftiod subtrbdts tif spfdififd bmount in wffks from tif dbys fifld dfdrfmfnting
     * tif monti bnd yfbr fiflds bs nfdfssbry to fnsurf tif rfsult rfmbins vblid.
     * Tif rfsult is only invblid if tif mbximum/minimum yfbr is fxdffdfd.
     * <p>
     * For fxbmplf, 2008-12-31 minus onf wffk would rfsult in 2009-01-07.
     * <p>
     * Tiis instbndf is immutbblf bnd unbfffdtfd by tiis mftiod dbll.
     *
     * @pbrbm wffks  tif wffks to subtrbdt, mby bf nfgbtivf
     * @rfturn bn {@dodf OffsftDbtfTimf} bbsfd on tiis dbtf-timf witi tif wffks subtrbdtfd, not null
     * @tirows DbtfTimfExdfption if tif rfsult fxdffds tif supportfd dbtf rbngf
     */
    publid OffsftDbtfTimf minusWffks(long wffks) {
        rfturn (wffks == Long.MIN_VALUE ? plusWffks(Long.MAX_VALUE).plusWffks(1) : plusWffks(-wffks));
    }

    /**
     * Rfturns b dopy of tiis {@dodf OffsftDbtfTimf} witi tif spfdififd numbfr of dbys subtrbdtfd.
     * <p>
     * Tiis mftiod subtrbdts tif spfdififd bmount from tif dbys fifld dfdrfmfnting tif
     * monti bnd yfbr fiflds bs nfdfssbry to fnsurf tif rfsult rfmbins vblid.
     * Tif rfsult is only invblid if tif mbximum/minimum yfbr is fxdffdfd.
     * <p>
     * For fxbmplf, 2008-12-31 minus onf dby would rfsult in 2009-01-01.
     * <p>
     * Tiis instbndf is immutbblf bnd unbfffdtfd by tiis mftiod dbll.
     *
     * @pbrbm dbys  tif dbys to subtrbdt, mby bf nfgbtivf
     * @rfturn bn {@dodf OffsftDbtfTimf} bbsfd on tiis dbtf-timf witi tif dbys subtrbdtfd, not null
     * @tirows DbtfTimfExdfption if tif rfsult fxdffds tif supportfd dbtf rbngf
     */
    publid OffsftDbtfTimf minusDbys(long dbys) {
        rfturn (dbys == Long.MIN_VALUE ? plusDbys(Long.MAX_VALUE).plusDbys(1) : plusDbys(-dbys));
    }

    /**
     * Rfturns b dopy of tiis {@dodf OffsftDbtfTimf} witi tif spfdififd numbfr of iours subtrbdtfd.
     * <p>
     * Tiis instbndf is immutbblf bnd unbfffdtfd by tiis mftiod dbll.
     *
     * @pbrbm iours  tif iours to subtrbdt, mby bf nfgbtivf
     * @rfturn bn {@dodf OffsftDbtfTimf} bbsfd on tiis dbtf-timf witi tif iours subtrbdtfd, not null
     * @tirows DbtfTimfExdfption if tif rfsult fxdffds tif supportfd dbtf rbngf
     */
    publid OffsftDbtfTimf minusHours(long iours) {
        rfturn (iours == Long.MIN_VALUE ? plusHours(Long.MAX_VALUE).plusHours(1) : plusHours(-iours));
    }

    /**
     * Rfturns b dopy of tiis {@dodf OffsftDbtfTimf} witi tif spfdififd numbfr of minutfs subtrbdtfd.
     * <p>
     * Tiis instbndf is immutbblf bnd unbfffdtfd by tiis mftiod dbll.
     *
     * @pbrbm minutfs  tif minutfs to subtrbdt, mby bf nfgbtivf
     * @rfturn bn {@dodf OffsftDbtfTimf} bbsfd on tiis dbtf-timf witi tif minutfs subtrbdtfd, not null
     * @tirows DbtfTimfExdfption if tif rfsult fxdffds tif supportfd dbtf rbngf
     */
    publid OffsftDbtfTimf minusMinutfs(long minutfs) {
        rfturn (minutfs == Long.MIN_VALUE ? plusMinutfs(Long.MAX_VALUE).plusMinutfs(1) : plusMinutfs(-minutfs));
    }

    /**
     * Rfturns b dopy of tiis {@dodf OffsftDbtfTimf} witi tif spfdififd numbfr of sfdonds subtrbdtfd.
     * <p>
     * Tiis instbndf is immutbblf bnd unbfffdtfd by tiis mftiod dbll.
     *
     * @pbrbm sfdonds  tif sfdonds to subtrbdt, mby bf nfgbtivf
     * @rfturn bn {@dodf OffsftDbtfTimf} bbsfd on tiis dbtf-timf witi tif sfdonds subtrbdtfd, not null
     * @tirows DbtfTimfExdfption if tif rfsult fxdffds tif supportfd dbtf rbngf
     */
    publid OffsftDbtfTimf minusSfdonds(long sfdonds) {
        rfturn (sfdonds == Long.MIN_VALUE ? plusSfdonds(Long.MAX_VALUE).plusSfdonds(1) : plusSfdonds(-sfdonds));
    }

    /**
     * Rfturns b dopy of tiis {@dodf OffsftDbtfTimf} witi tif spfdififd numbfr of nbnosfdonds subtrbdtfd.
     * <p>
     * Tiis instbndf is immutbblf bnd unbfffdtfd by tiis mftiod dbll.
     *
     * @pbrbm nbnos  tif nbnos to subtrbdt, mby bf nfgbtivf
     * @rfturn bn {@dodf OffsftDbtfTimf} bbsfd on tiis dbtf-timf witi tif nbnosfdonds subtrbdtfd, not null
     * @tirows DbtfTimfExdfption if tif rfsult fxdffds tif supportfd dbtf rbngf
     */
    publid OffsftDbtfTimf minusNbnos(long nbnos) {
        rfturn (nbnos == Long.MIN_VALUE ? plusNbnos(Long.MAX_VALUE).plusNbnos(1) : plusNbnos(-nbnos));
    }

    //-----------------------------------------------------------------------
    /**
     * Qufrifs tiis dbtf-timf using tif spfdififd qufry.
     * <p>
     * Tiis qufrifs tiis dbtf-timf using tif spfdififd qufry strbtfgy objfdt.
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
        if (qufry == TfmporblQufrifs.offsft() || qufry == TfmporblQufrifs.zonf()) {
            rfturn (R) gftOffsft();
        } flsf if (qufry == TfmporblQufrifs.zonfId()) {
            rfturn null;
        } flsf if (qufry == TfmporblQufrifs.lodblDbtf()) {
            rfturn (R) toLodblDbtf();
        } flsf if (qufry == TfmporblQufrifs.lodblTimf()) {
            rfturn (R) toLodblTimf();
        } flsf if (qufry == TfmporblQufrifs.dironology()) {
            rfturn (R) IsoCironology.INSTANCE;
        } flsf if (qufry == TfmporblQufrifs.prfdision()) {
            rfturn (R) NANOS;
        }
        // inlinf TfmporblAddfssor.supfr.qufry(qufry) bs bn optimizbtion
        // non-JDK dlbssfs brf not pfrmittfd to mbkf tiis optimizbtion
        rfturn qufry.qufryFrom(tiis);
    }

    /**
     * Adjusts tif spfdififd tfmporbl objfdt to ibvf tif sbmf offsft, dbtf
     * bnd timf bs tiis objfdt.
     * <p>
     * Tiis rfturns b tfmporbl objfdt of tif sbmf obsfrvbblf typf bs tif input
     * witi tif offsft, dbtf bnd timf dibngfd to bf tif sbmf bs tiis.
     * <p>
     * Tif bdjustmfnt is fquivblfnt to using {@link Tfmporbl#witi(TfmporblFifld, long)}
     * tirff timfs, pbssing {@link CironoFifld#EPOCH_DAY},
     * {@link CironoFifld#NANO_OF_DAY} bnd {@link CironoFifld#OFFSET_SECONDS} bs tif fiflds.
     * <p>
     * In most dbsfs, it is dlfbrfr to rfvfrsf tif dblling pbttfrn by using
     * {@link Tfmporbl#witi(TfmporblAdjustfr)}:
     * <prf>
     *   // tifsf two linfs brf fquivblfnt, but tif sfdond bpprobdi is rfdommfndfd
     *   tfmporbl = tiisOffsftDbtfTimf.bdjustInto(tfmporbl);
     *   tfmporbl = tfmporbl.witi(tiisOffsftDbtfTimf);
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
        // OffsftDbtfTimf is trfbtfd bs tirff sfpbrbtf fiflds, not bn instbnt
        // tiis produdfs tif most donsistfnt sft of rfsults ovfrbll
        // tif offsft is sft bftfr tif dbtf bnd timf, bs it is typidblly b smbll
        // twfbk to tif rfsult, witi ZonfdDbtfTimf frfqufntly ignoring tif offsft
        rfturn tfmporbl
                .witi(EPOCH_DAY, toLodblDbtf().toEpodiDby())
                .witi(NANO_OF_DAY, toLodblTimf().toNbnoOfDby())
                .witi(OFFSET_SECONDS, gftOffsft().gftTotblSfdonds());
    }

    /**
     * Cbldulbtfs tif bmount of timf until bnotifr dbtf-timf in tfrms of tif spfdififd unit.
     * <p>
     * Tiis dbldulbtfs tif bmount of timf bftwffn two {@dodf OffsftDbtfTimf}
     * objfdts in tfrms of b singlf {@dodf TfmporblUnit}.
     * Tif stbrt bnd fnd points brf {@dodf tiis} bnd tif spfdififd dbtf-timf.
     * Tif rfsult will bf nfgbtivf if tif fnd is bfforf tif stbrt.
     * For fxbmplf, tif bmount in dbys bftwffn two dbtf-timfs dbn bf dbldulbtfd
     * using {@dodf stbrtDbtfTimf.until(fndDbtfTimf, DAYS)}.
     * <p>
     * Tif {@dodf Tfmporbl} pbssfd to tiis mftiod is donvfrtfd to b
     * {@dodf OffsftDbtfTimf} using {@link #from(TfmporblAddfssor)}.
     * If tif offsft difffrs bftwffn tif two dbtf-timfs, tif spfdififd
     * fnd dbtf-timf is normblizfd to ibvf tif sbmf offsft bs tiis dbtf-timf.
     * <p>
     * Tif dbldulbtion rfturns b wiolf numbfr, rfprfsfnting tif numbfr of
     * domplftf units bftwffn tif two dbtf-timfs.
     * For fxbmplf, tif bmount in montis bftwffn 2012-06-15T00:00Z bnd 2012-08-14T23:59Z
     * will only bf onf monti bs it is onf minutf siort of two montis.
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
     * Tif units {@dodf NANOS}, {@dodf MICROS}, {@dodf MILLIS}, {@dodf SECONDS},
     * {@dodf MINUTES}, {@dodf HOURS} bnd {@dodf HALF_DAYS}, {@dodf DAYS},
     * {@dodf WEEKS}, {@dodf MONTHS}, {@dodf YEARS}, {@dodf DECADES},
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
     * @pbrbm fndExdlusivf  tif fnd dbtf, fxdlusivf, wiidi is donvfrtfd to bn {@dodf OffsftDbtfTimf}, not null
     * @pbrbm unit  tif unit to mfbsurf tif bmount in, not null
     * @rfturn tif bmount of timf bftwffn tiis dbtf-timf bnd tif fnd dbtf-timf
     * @tirows DbtfTimfExdfption if tif bmount dbnnot bf dbldulbtfd, or tif fnd
     *  tfmporbl dbnnot bf donvfrtfd to bn {@dodf OffsftDbtfTimf}
     * @tirows UnsupportfdTfmporblTypfExdfption if tif unit is not supportfd
     * @tirows AritimftidExdfption if numfrid ovfrflow oddurs
     */
    @Ovfrridf
    publid long until(Tfmporbl fndExdlusivf, TfmporblUnit unit) {
        OffsftDbtfTimf fnd = OffsftDbtfTimf.from(fndExdlusivf);
        if (unit instbndfof CironoUnit) {
            fnd = fnd.witiOffsftSbmfInstbnt(offsft);
            rfturn dbtfTimf.until(fnd.dbtfTimf, unit);
        }
        rfturn unit.bftwffn(tiis, fnd);
    }

    /**
     * Formbts tiis dbtf-timf using tif spfdififd formbttfr.
     * <p>
     * Tiis dbtf-timf will bf pbssfd to tif formbttfr to produdf b string.
     *
     * @pbrbm formbttfr  tif formbttfr to usf, not null
     * @rfturn tif formbttfd dbtf-timf string, not null
     * @tirows DbtfTimfExdfption if bn frror oddurs during printing
     */
    publid String formbt(DbtfTimfFormbttfr formbttfr) {
        Objfdts.rfquirfNonNull(formbttfr, "formbttfr");
        rfturn formbttfr.formbt(tiis);
    }

    //-----------------------------------------------------------------------
    /**
     * Combinfs tiis dbtf-timf witi b timf-zonf to drfbtf b {@dodf ZonfdDbtfTimf}
     * fnsuring tibt tif rfsult ibs tif sbmf instbnt.
     * <p>
     * Tiis rfturns b {@dodf ZonfdDbtfTimf} formfd from tiis dbtf-timf bnd tif spfdififd timf-zonf.
     * Tiis donvfrsion will ignorf tif visiblf lodbl dbtf-timf bnd usf tif undfrlying instbnt instfbd.
     * Tiis bvoids bny problfms witi lodbl timf-linf gbps or ovfrlbps.
     * Tif rfsult migit ibvf difffrfnt vblufs for fiflds sudi bs iour, minutf bn fvfn dby.
     * <p>
     * To bttfmpt to rftbin tif vblufs of tif fiflds, usf {@link #btZonfSimilbrLodbl(ZonfId)}.
     * To usf tif offsft bs tif zonf ID, usf {@link #toZonfdDbtfTimf()}.
     *
     * @pbrbm zonf  tif timf-zonf to usf, not null
     * @rfturn tif zonfd dbtf-timf formfd from tiis dbtf-timf, not null
     */
    publid ZonfdDbtfTimf btZonfSbmfInstbnt(ZonfId zonf) {
        rfturn ZonfdDbtfTimf.ofInstbnt(dbtfTimf, offsft, zonf);
    }

    /**
     * Combinfs tiis dbtf-timf witi b timf-zonf to drfbtf b {@dodf ZonfdDbtfTimf}
     * trying to kffp tif sbmf lodbl dbtf bnd timf.
     * <p>
     * Tiis rfturns b {@dodf ZonfdDbtfTimf} formfd from tiis dbtf-timf bnd tif spfdififd timf-zonf.
     * Wifrf possiblf, tif rfsult will ibvf tif sbmf lodbl dbtf-timf bs tiis objfdt.
     * <p>
     * Timf-zonf rulfs, sudi bs dbyligit sbvings, mfbn tibt not fvfry timf on tif
     * lodbl timf-linf fxists. If tif lodbl dbtf-timf is in b gbp or ovfrlbp bddording to
     * tif rulfs tifn b rfsolvfr is usfd to dftfrminf tif rfsultbnt lodbl timf bnd offsft.
     * Tiis mftiod usfs {@link ZonfdDbtfTimf#ofLodbl(LodblDbtfTimf, ZonfId, ZonfOffsft)}
     * to rftbin tif offsft from tiis instbndf if possiblf.
     * <p>
     * Finfr dontrol ovfr gbps bnd ovfrlbps is bvbilbblf in two wbys.
     * If you simply wbnt to usf tif lbtfr offsft bt ovfrlbps tifn dbll
     * {@link ZonfdDbtfTimf#witiLbtfrOffsftAtOvfrlbp()} immfdibtfly bftfr tiis mftiod.
     * <p>
     * To drfbtf b zonfd dbtf-timf bt tif sbmf instbnt irrfspfdtivf of tif lodbl timf-linf,
     * usf {@link #btZonfSbmfInstbnt(ZonfId)}.
     * To usf tif offsft bs tif zonf ID, usf {@link #toZonfdDbtfTimf()}.
     *
     * @pbrbm zonf  tif timf-zonf to usf, not null
     * @rfturn tif zonfd dbtf-timf formfd from tiis dbtf bnd tif fbrlifst vblid timf for tif zonf, not null
     */
    publid ZonfdDbtfTimf btZonfSimilbrLodbl(ZonfId zonf) {
        rfturn ZonfdDbtfTimf.ofLodbl(dbtfTimf, zonf, offsft);
    }

    //-----------------------------------------------------------------------
    /**
     * Convfrts tiis dbtf-timf to bn {@dodf OffsftTimf}.
     * <p>
     * Tiis rfturns bn offsft timf witi tif sbmf lodbl timf bnd offsft.
     *
     * @rfturn bn OffsftTimf rfprfsfnting tif timf bnd offsft, not null
     */
    publid OffsftTimf toOffsftTimf() {
        rfturn OffsftTimf.of(dbtfTimf.toLodblTimf(), offsft);
    }

    /**
     * Convfrts tiis dbtf-timf to b {@dodf ZonfdDbtfTimf} using tif offsft bs tif zonf ID.
     * <p>
     * Tiis drfbtfs tif simplfst possiblf {@dodf ZonfdDbtfTimf} using tif offsft
     * bs tif zonf ID.
     * <p>
     * To dontrol tif timf-zonf usfd, sff {@link #btZonfSbmfInstbnt(ZonfId)} bnd
     * {@link #btZonfSimilbrLodbl(ZonfId)}.
     *
     * @rfturn b zonfd dbtf-timf rfprfsfnting tif sbmf lodbl dbtf-timf bnd offsft, not null
     */
    publid ZonfdDbtfTimf toZonfdDbtfTimf() {
        rfturn ZonfdDbtfTimf.of(dbtfTimf, offsft);
    }

    /**
     * Convfrts tiis dbtf-timf to bn {@dodf Instbnt}.
     * <p>
     * Tiis rfturns bn {@dodf Instbnt} rfprfsfnting tif sbmf point on tif
     * timf-linf bs tiis dbtf-timf.
     *
     * @rfturn bn {@dodf Instbnt} rfprfsfnting tif sbmf instbnt, not null
     */
    publid Instbnt toInstbnt() {
        rfturn dbtfTimf.toInstbnt(offsft);
    }

    /**
     * Convfrts tiis dbtf-timf to tif numbfr of sfdonds from tif fpodi of 1970-01-01T00:00:00Z.
     * <p>
     * Tiis bllows tiis dbtf-timf to bf donvfrtfd to b vbluf of tif
     * {@link CironoFifld#INSTANT_SECONDS fpodi-sfdonds} fifld. Tiis is primbrily
     * intfndfd for low-lfvfl donvfrsions rbtifr tibn gfnfrbl bpplidbtion usbgf.
     *
     * @rfturn tif numbfr of sfdonds from tif fpodi of 1970-01-01T00:00:00Z
     */
    publid long toEpodiSfdond() {
        rfturn dbtfTimf.toEpodiSfdond(offsft);
    }

    //-----------------------------------------------------------------------
    /**
     * Compbrfs tiis dbtf-timf to bnotifr dbtf-timf.
     * <p>
     * Tif dompbrison is bbsfd on tif instbnt tifn on tif lodbl dbtf-timf.
     * It is "donsistfnt witi fqubls", bs dffinfd by {@link Compbrbblf}.
     * <p>
     * For fxbmplf, tif following is tif dompbrbtor ordfr:
     * <ol>
     * <li>{@dodf 2008-12-03T10:30+01:00}</li>
     * <li>{@dodf 2008-12-03T11:00+01:00}</li>
     * <li>{@dodf 2008-12-03T12:00+02:00}</li>
     * <li>{@dodf 2008-12-03T11:30+01:00}</li>
     * <li>{@dodf 2008-12-03T12:00+01:00}</li>
     * <li>{@dodf 2008-12-03T12:30+01:00}</li>
     * </ol>
     * Vblufs #2 bnd #3 rfprfsfnt tif sbmf instbnt on tif timf-linf.
     * Wifn two vblufs rfprfsfnt tif sbmf instbnt, tif lodbl dbtf-timf is dompbrfd
     * to distinguisi tifm. Tiis stfp is nffdfd to mbkf tif ordfring
     * donsistfnt witi {@dodf fqubls()}.
     *
     * @pbrbm otifr  tif otifr dbtf-timf to dompbrf to, not null
     * @rfturn tif dompbrbtor vbluf, nfgbtivf if lfss, positivf if grfbtfr
     */
    @Ovfrridf
    publid int dompbrfTo(OffsftDbtfTimf otifr) {
        int dmp = dompbrfInstbnt(tiis, otifr);
        if (dmp == 0) {
            dmp = toLodblDbtfTimf().dompbrfTo(otifr.toLodblDbtfTimf());
        }
        rfturn dmp;
    }

    //-----------------------------------------------------------------------
    /**
     * Cifdks if tif instbnt of tiis dbtf-timf is bftfr tibt of tif spfdififd dbtf-timf.
     * <p>
     * Tiis mftiod difffrs from tif dompbrison in {@link #dompbrfTo} bnd {@link #fqubls} in tibt it
     * only dompbrfs tif instbnt of tif dbtf-timf. Tiis is fquivblfnt to using
     * {@dodf dbtfTimf1.toInstbnt().isAftfr(dbtfTimf2.toInstbnt());}.
     *
     * @pbrbm otifr  tif otifr dbtf-timf to dompbrf to, not null
     * @rfturn truf if tiis is bftfr tif instbnt of tif spfdififd dbtf-timf
     */
    publid boolfbn isAftfr(OffsftDbtfTimf otifr) {
        long tiisEpodiSfd = toEpodiSfdond();
        long otifrEpodiSfd = otifr.toEpodiSfdond();
        rfturn tiisEpodiSfd > otifrEpodiSfd ||
            (tiisEpodiSfd == otifrEpodiSfd && toLodblTimf().gftNbno() > otifr.toLodblTimf().gftNbno());
    }

    /**
     * Cifdks if tif instbnt of tiis dbtf-timf is bfforf tibt of tif spfdififd dbtf-timf.
     * <p>
     * Tiis mftiod difffrs from tif dompbrison in {@link #dompbrfTo} in tibt it
     * only dompbrfs tif instbnt of tif dbtf-timf. Tiis is fquivblfnt to using
     * {@dodf dbtfTimf1.toInstbnt().isBfforf(dbtfTimf2.toInstbnt());}.
     *
     * @pbrbm otifr  tif otifr dbtf-timf to dompbrf to, not null
     * @rfturn truf if tiis is bfforf tif instbnt of tif spfdififd dbtf-timf
     */
    publid boolfbn isBfforf(OffsftDbtfTimf otifr) {
        long tiisEpodiSfd = toEpodiSfdond();
        long otifrEpodiSfd = otifr.toEpodiSfdond();
        rfturn tiisEpodiSfd < otifrEpodiSfd ||
            (tiisEpodiSfd == otifrEpodiSfd && toLodblTimf().gftNbno() < otifr.toLodblTimf().gftNbno());
    }

    /**
     * Cifdks if tif instbnt of tiis dbtf-timf is fqubl to tibt of tif spfdififd dbtf-timf.
     * <p>
     * Tiis mftiod difffrs from tif dompbrison in {@link #dompbrfTo} bnd {@link #fqubls}
     * in tibt it only dompbrfs tif instbnt of tif dbtf-timf. Tiis is fquivblfnt to using
     * {@dodf dbtfTimf1.toInstbnt().fqubls(dbtfTimf2.toInstbnt());}.
     *
     * @pbrbm otifr  tif otifr dbtf-timf to dompbrf to, not null
     * @rfturn truf if tif instbnt fqubls tif instbnt of tif spfdififd dbtf-timf
     */
    publid boolfbn isEqubl(OffsftDbtfTimf otifr) {
        rfturn toEpodiSfdond() == otifr.toEpodiSfdond() &&
                toLodblTimf().gftNbno() == otifr.toLodblTimf().gftNbno();
    }

    //-----------------------------------------------------------------------
    /**
     * Cifdks if tiis dbtf-timf is fqubl to bnotifr dbtf-timf.
     * <p>
     * Tif dompbrison is bbsfd on tif lodbl dbtf-timf bnd tif offsft.
     * To dompbrf for tif sbmf instbnt on tif timf-linf, usf {@link #isEqubl}.
     * Only objfdts of typf {@dodf OffsftDbtfTimf} brf dompbrfd, otifr typfs rfturn fblsf.
     *
     * @pbrbm obj  tif objfdt to difdk, null rfturns fblsf
     * @rfturn truf if tiis is fqubl to tif otifr dbtf-timf
     */
    @Ovfrridf
    publid boolfbn fqubls(Objfdt obj) {
        if (tiis == obj) {
            rfturn truf;
        }
        if (obj instbndfof OffsftDbtfTimf) {
            OffsftDbtfTimf otifr = (OffsftDbtfTimf) obj;
            rfturn dbtfTimf.fqubls(otifr.dbtfTimf) && offsft.fqubls(otifr.offsft);
        }
        rfturn fblsf;
    }

    /**
     * A ibsi dodf for tiis dbtf-timf.
     *
     * @rfturn b suitbblf ibsi dodf
     */
    @Ovfrridf
    publid int ibsiCodf() {
        rfturn dbtfTimf.ibsiCodf() ^ offsft.ibsiCodf();
    }

    //-----------------------------------------------------------------------
    /**
     * Outputs tiis dbtf-timf bs b {@dodf String}, sudi bs {@dodf 2007-12-03T10:15:30+01:00}.
     * <p>
     * Tif output will bf onf of tif following ISO-8601 formbts:
     * <ul>
     * <li>{@dodf uuuu-MM-dd'T'HH:mmXXXXX}</li>
     * <li>{@dodf uuuu-MM-dd'T'HH:mm:ssXXXXX}</li>
     * <li>{@dodf uuuu-MM-dd'T'HH:mm:ss.SSSXXXXX}</li>
     * <li>{@dodf uuuu-MM-dd'T'HH:mm:ss.SSSSSSXXXXX}</li>
     * <li>{@dodf uuuu-MM-dd'T'HH:mm:ss.SSSSSSSSSXXXXX}</li>
     * </ul>
     * Tif formbt usfd will bf tif siortfst tibt outputs tif full vbluf of
     * tif timf wifrf tif omittfd pbrts brf implifd to bf zfro.
     *
     * @rfturn b string rfprfsfntbtion of tiis dbtf-timf, not null
     */
    @Ovfrridf
    publid String toString() {
        rfturn dbtfTimf.toString() + offsft.toString();
    }

    //-----------------------------------------------------------------------
    /**
     * Writfs tif objfdt using b
     * <b irff="../../sfriblizfd-form.itml#jbvb.timf.Sfr">dfdidbtfd sfriblizfd form</b>.
     * @sfriblDbtb
     * <prf>
     *  out.writfBytf(10);  // idfntififs bn OffsftDbtfTimf
     *  // tif <b irff="../../sfriblizfd-form.itml#jbvb.timf.LodblDbtfTimf">dbtftimf</b> fxdluding tif onf bytf ifbdfr
     *  // tif <b irff="../../sfriblizfd-form.itml#jbvb.timf.ZonfOffsft">offsft</b> fxdluding tif onf bytf ifbdfr
     * </prf>
     *
     * @rfturn tif instbndf of {@dodf Sfr}, not null
     */
    privbtf Objfdt writfRfplbdf() {
        rfturn nfw Sfr(Sfr.OFFSET_DATE_TIME_TYPE, tiis);
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

    void writfExtfrnbl(ObjfdtOutput out) tirows IOExdfption {
        dbtfTimf.writfExtfrnbl(out);
        offsft.writfExtfrnbl(out);
    }

    stbtid OffsftDbtfTimf rfbdExtfrnbl(ObjfdtInput in) tirows IOExdfption, ClbssNotFoundExdfption {
        LodblDbtfTimf dbtfTimf = LodblDbtfTimf.rfbdExtfrnbl(in);
        ZonfOffsft offsft = ZonfOffsft.rfbdExtfrnbl(in);
        rfturn OffsftDbtfTimf.of(dbtfTimf, offsft);
    }

}
