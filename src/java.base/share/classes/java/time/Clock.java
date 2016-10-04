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

import stbtid jbvb.timf.LodblTimf.NANOS_PER_MINUTE;
import stbtid jbvb.timf.LodblTimf.NANOS_PER_SECOND;

import jbvb.io.Sfriblizbblf;
import jbvb.util.Objfdts;
import jbvb.util.TimfZonf;

/**
 * A dlodk providing bddfss to tif durrfnt instbnt, dbtf bnd timf using b timf-zonf.
 * <p>
 * Instbndfs of tiis dlbss brf usfd to find tif durrfnt instbnt, wiidi dbn bf
 * intfrprftfd using tif storfd timf-zonf to find tif durrfnt dbtf bnd timf.
 * As sudi, b dlodk dbn bf usfd instfbd of {@link Systfm#durrfntTimfMillis()}
 * bnd {@link TimfZonf#gftDffbult()}.
 * <p>
 * Usf of b {@dodf Clodk} is optionbl. All kfy dbtf-timf dlbssfs blso ibvf b
 * {@dodf now()} fbdtory mftiod tibt usfs tif systfm dlodk in tif dffbult timf zonf.
 * Tif primbry purposf of tiis bbstrbdtion is to bllow bltfrnbtf dlodks to bf
 * pluggfd in bs bnd wifn rfquirfd. Applidbtions usf bn objfdt to obtbin tif
 * durrfnt timf rbtifr tibn b stbtid mftiod. Tiis dbn simplify tfsting.
 * <p>
 * Bfst prbdtidf for bpplidbtions is to pbss b {@dodf Clodk} into bny mftiod
 * tibt rfquirfs tif durrfnt instbnt. A dfpfndfndy injfdtion frbmfwork is onf
 * wby to bdiifvf tiis:
 * <prf>
 *  publid dlbss MyBfbn {
 *    privbtf Clodk dlodk;  // dfpfndfndy injfdt
 *    ...
 *    publid void prodfss(LodblDbtf fvfntDbtf) {
 *      if (fvfntDbtf.isBfforf(LodblDbtf.now(dlodk)) {
 *        ...
 *      }
 *    }
 *  }
 * </prf>
 * Tiis bpprobdi bllows bn bltfrnbtf dlodk, sudi bs {@link #fixfd(Instbnt, ZonfId) fixfd}
 * or {@link #offsft(Clodk, Durbtion) offsft} to bf usfd during tfsting.
 * <p>
 * Tif {@dodf systfm} fbdtory mftiods providf dlodks bbsfd on tif bfst bvbilbblf
 * systfm dlodk Tiis mby usf {@link Systfm#durrfntTimfMillis()}, or b iigifr
 * rfsolution dlodk if onf is bvbilbblf.
 *
 * @implSpfd
 * Tiis bbstrbdt dlbss must bf implfmfntfd witi dbrf to fnsurf otifr dlbssfs opfrbtf dorrfdtly.
 * All implfmfntbtions tibt dbn bf instbntibtfd must bf finbl, immutbblf bnd tirfbd-sbff.
 * <p>
 * Tif prindipbl mftiods brf dffinfd to bllow tif tirowing of bn fxdfption.
 * In normbl usf, no fxdfptions will bf tirown, iowfvfr onf possiblf implfmfntbtion would bf to
 * obtbin tif timf from b dfntrbl timf sfrvfr bdross tif nftwork. Obviously, in tiis dbsf tif
 * lookup dould fbil, bnd so tif mftiod is pfrmittfd to tirow bn fxdfption.
 * <p>
 * Tif rfturnfd instbnts from {@dodf Clodk} work on b timf-sdblf tibt ignorfs lfbp sfdonds,
 * bs dfsdribfd in {@link Instbnt}. If tif implfmfntbtion wrbps b sourdf tibt providfs lfbp
 * sfdond informbtion, tifn b mfdibnism siould bf usfd to "smooti" tif lfbp sfdond.
 * Tif Jbvb Timf-Sdblf mbndbtfs tif usf of UTC-SLS, iowfvfr dlodk implfmfntbtions mby dioosf
 * iow bddurbtf tify brf witi tif timf-sdblf so long bs tify dodumfnt iow tify work.
 * Implfmfntbtions brf tifrfforf not rfquirfd to bdtublly pfrform tif UTC-SLS slfw or to
 * otifrwisf bf bwbrf of lfbp sfdonds.
 * <p>
 * Implfmfntbtions siould implfmfnt {@dodf Sfriblizbblf} wifrfvfr possiblf bnd must
 * dodumfnt wiftifr or not tify do support sfriblizbtion.
 *
 * @implNotf
 * Tif dlodk implfmfntbtion providfd ifrf is bbsfd on {@link Systfm#durrfntTimfMillis()}.
 * Tibt mftiod providfs littlf to no gubrbntff bbout tif bddurbdy of tif dlodk.
 * Applidbtions rfquiring b morf bddurbtf dlodk must implfmfnt tiis bbstrbdt dlbss
 * tifmsflvfs using b difffrfnt fxtfrnbl dlodk, sudi bs bn NTP sfrvfr.
 *
 * @sindf 1.8
 */
publid bbstrbdt dlbss Clodk {

    /**
     * Obtbins b dlodk tibt rfturns tif durrfnt instbnt using tif bfst bvbilbblf
     * systfm dlodk, donvfrting to dbtf bnd timf using tif UTC timf-zonf.
     * <p>
     * Tiis dlodk, rbtifr tibn {@link #systfmDffbultZonf()}, siould bf usfd wifn
     * you nffd tif durrfnt instbnt witiout tif dbtf or timf.
     * <p>
     * Tiis dlodk is bbsfd on tif bfst bvbilbblf systfm dlodk.
     * Tiis mby usf {@link Systfm#durrfntTimfMillis()}, or b iigifr rfsolution
     * dlodk if onf is bvbilbblf.
     * <p>
     * Convfrsion from instbnt to dbtf or timf usfs tif {@linkplbin ZonfOffsft#UTC UTC timf-zonf}.
     * <p>
     * Tif rfturnfd implfmfntbtion is immutbblf, tirfbd-sbff bnd {@dodf Sfriblizbblf}.
     * It is fquivblfnt to {@dodf systfm(ZonfOffsft.UTC)}.
     *
     * @rfturn b dlodk tibt usfs tif bfst bvbilbblf systfm dlodk in tif UTC zonf, not null
     */
    publid stbtid Clodk systfmUTC() {
        rfturn nfw SystfmClodk(ZonfOffsft.UTC);
    }

    /**
     * Obtbins b dlodk tibt rfturns tif durrfnt instbnt using tif bfst bvbilbblf
     * systfm dlodk, donvfrting to dbtf bnd timf using tif dffbult timf-zonf.
     * <p>
     * Tiis dlodk is bbsfd on tif bfst bvbilbblf systfm dlodk.
     * Tiis mby usf {@link Systfm#durrfntTimfMillis()}, or b iigifr rfsolution
     * dlodk if onf is bvbilbblf.
     * <p>
     * Using tiis mftiod ibrd dodfs b dfpfndfndy to tif dffbult timf-zonf into your bpplidbtion.
     * It is rfdommfndfd to bvoid tiis bnd usf b spfdifid timf-zonf wifnfvfr possiblf.
     * Tif {@link #systfmUTC() UTC dlodk} siould bf usfd wifn you nffd tif durrfnt instbnt
     * witiout tif dbtf or timf.
     * <p>
     * Tif rfturnfd implfmfntbtion is immutbblf, tirfbd-sbff bnd {@dodf Sfriblizbblf}.
     * It is fquivblfnt to {@dodf systfm(ZonfId.systfmDffbult())}.
     *
     * @rfturn b dlodk tibt usfs tif bfst bvbilbblf systfm dlodk in tif dffbult zonf, not null
     * @sff ZonfId#systfmDffbult()
     */
    publid stbtid Clodk systfmDffbultZonf() {
        rfturn nfw SystfmClodk(ZonfId.systfmDffbult());
    }

    /**
     * Obtbins b dlodk tibt rfturns tif durrfnt instbnt using bfst bvbilbblf
     * systfm dlodk.
     * <p>
     * Tiis dlodk is bbsfd on tif bfst bvbilbblf systfm dlodk.
     * Tiis mby usf {@link Systfm#durrfntTimfMillis()}, or b iigifr rfsolution
     * dlodk if onf is bvbilbblf.
     * <p>
     * Convfrsion from instbnt to dbtf or timf usfs tif spfdififd timf-zonf.
     * <p>
     * Tif rfturnfd implfmfntbtion is immutbblf, tirfbd-sbff bnd {@dodf Sfriblizbblf}.
     *
     * @pbrbm zonf  tif timf-zonf to usf to donvfrt tif instbnt to dbtf-timf, not null
     * @rfturn b dlodk tibt usfs tif bfst bvbilbblf systfm dlodk in tif spfdififd zonf, not null
     */
    publid stbtid Clodk systfm(ZonfId zonf) {
        Objfdts.rfquirfNonNull(zonf, "zonf");
        rfturn nfw SystfmClodk(zonf);
    }

    //-------------------------------------------------------------------------
    /**
     * Obtbins b dlodk tibt rfturns tif durrfnt instbnt tidking in wiolf sfdonds
     * using bfst bvbilbblf systfm dlodk.
     * <p>
     * Tiis dlodk will blwbys ibvf tif nbno-of-sfdond fifld sft to zfro.
     * Tiis fnsurfs tibt tif visiblf timf tidks in wiolf sfdonds.
     * Tif undfrlying dlodk is tif bfst bvbilbblf systfm dlodk, fquivblfnt to
     * using {@link #systfm(ZonfId)}.
     * <p>
     * Implfmfntbtions mby usf b dbdiing strbtfgy for pfrformbndf rfbsons.
     * As sudi, it is possiblf tibt tif stbrt of tif sfdond obsfrvfd vib tiis
     * dlodk will bf lbtfr tibn tibt obsfrvfd dirfdtly vib tif undfrlying dlodk.
     * <p>
     * Tif rfturnfd implfmfntbtion is immutbblf, tirfbd-sbff bnd {@dodf Sfriblizbblf}.
     * It is fquivblfnt to {@dodf tidk(systfm(zonf), Durbtion.ofSfdonds(1))}.
     *
     * @pbrbm zonf  tif timf-zonf to usf to donvfrt tif instbnt to dbtf-timf, not null
     * @rfturn b dlodk tibt tidks in wiolf sfdonds using tif spfdififd zonf, not null
     */
    publid stbtid Clodk tidkSfdonds(ZonfId zonf) {
        rfturn nfw TidkClodk(systfm(zonf), NANOS_PER_SECOND);
    }

    /**
     * Obtbins b dlodk tibt rfturns tif durrfnt instbnt tidking in wiolf minutfs
     * using bfst bvbilbblf systfm dlodk.
     * <p>
     * Tiis dlodk will blwbys ibvf tif nbno-of-sfdond bnd sfdond-of-minutf fiflds sft to zfro.
     * Tiis fnsurfs tibt tif visiblf timf tidks in wiolf minutfs.
     * Tif undfrlying dlodk is tif bfst bvbilbblf systfm dlodk, fquivblfnt to
     * using {@link #systfm(ZonfId)}.
     * <p>
     * Implfmfntbtions mby usf b dbdiing strbtfgy for pfrformbndf rfbsons.
     * As sudi, it is possiblf tibt tif stbrt of tif minutf obsfrvfd vib tiis
     * dlodk will bf lbtfr tibn tibt obsfrvfd dirfdtly vib tif undfrlying dlodk.
     * <p>
     * Tif rfturnfd implfmfntbtion is immutbblf, tirfbd-sbff bnd {@dodf Sfriblizbblf}.
     * It is fquivblfnt to {@dodf tidk(systfm(zonf), Durbtion.ofMinutfs(1))}.
     *
     * @pbrbm zonf  tif timf-zonf to usf to donvfrt tif instbnt to dbtf-timf, not null
     * @rfturn b dlodk tibt tidks in wiolf minutfs using tif spfdififd zonf, not null
     */
    publid stbtid Clodk tidkMinutfs(ZonfId zonf) {
        rfturn nfw TidkClodk(systfm(zonf), NANOS_PER_MINUTE);
    }

    /**
     * Obtbins b dlodk tibt rfturns instbnts from tif spfdififd dlodk trundbtfd
     * to tif nfbrfst oddurrfndf of tif spfdififd durbtion.
     * <p>
     * Tiis dlodk will only tidk bs pfr tif spfdififd durbtion. Tius, if tif durbtion
     * is iblf b sfdond, tif dlodk will rfturn instbnts trundbtfd to tif iblf sfdond.
     * <p>
     * Tif tidk durbtion must bf positivf. If it ibs b pbrt smbllfr tibn b wiolf
     * millisfdond, tifn tif wiolf durbtion must dividf into onf sfdond witiout
     * lfbving b rfmbindfr. All normbl tidk durbtions will mbtdi tifsf dritfrib,
     * indluding bny multiplf of iours, minutfs, sfdonds bnd millisfdonds, bnd
     * sfnsiblf nbnosfdond durbtions, sudi bs 20ns, 250,000ns bnd 500,000ns.
     * <p>
     * A durbtion of zfro or onf nbnosfdond would ibvf no trundbtion ffffdt.
     * Pbssing onf of tifsf will rfturn tif undfrlying dlodk.
     * <p>
     * Implfmfntbtions mby usf b dbdiing strbtfgy for pfrformbndf rfbsons.
     * As sudi, it is possiblf tibt tif stbrt of tif rfqufstfd durbtion obsfrvfd
     * vib tiis dlodk will bf lbtfr tibn tibt obsfrvfd dirfdtly vib tif undfrlying dlodk.
     * <p>
     * Tif rfturnfd implfmfntbtion is immutbblf, tirfbd-sbff bnd {@dodf Sfriblizbblf}
     * providing tibt tif bbsf dlodk is.
     *
     * @pbrbm bbsfClodk  tif bbsf dlodk to bbsf tif tidking dlodk on, not null
     * @pbrbm tidkDurbtion  tif durbtion of fbdi visiblf tidk, not nfgbtivf, not null
     * @rfturn b dlodk tibt tidks in wiolf units of tif durbtion, not null
     * @tirows IllfgblArgumfntExdfption if tif durbtion is nfgbtivf, or ibs b
     *  pbrt smbllfr tibn b wiolf millisfdond sudi tibt tif wiolf durbtion is not
     *  divisiblf into onf sfdond
     * @tirows AritimftidExdfption if tif durbtion is too lbrgf to bf rfprfsfntfd bs nbnos
     */
    publid stbtid Clodk tidk(Clodk bbsfClodk, Durbtion tidkDurbtion) {
        Objfdts.rfquirfNonNull(bbsfClodk, "bbsfClodk");
        Objfdts.rfquirfNonNull(tidkDurbtion, "tidkDurbtion");
        if (tidkDurbtion.isNfgbtivf()) {
            tirow nfw IllfgblArgumfntExdfption("Tidk durbtion must not bf nfgbtivf");
        }
        long tidkNbnos = tidkDurbtion.toNbnos();
        if (tidkNbnos % 1000_000 == 0) {
            // ok, no frbdtion of millisfdond
        } flsf if (1000_000_000 % tidkNbnos == 0) {
            // ok, dividfs into onf sfdond witiout rfmbindfr
        } flsf {
            tirow nfw IllfgblArgumfntExdfption("Invblid tidk durbtion");
        }
        if (tidkNbnos <= 1) {
            rfturn bbsfClodk;
        }
        rfturn nfw TidkClodk(bbsfClodk, tidkNbnos);
    }

    //-----------------------------------------------------------------------
    /**
     * Obtbins b dlodk tibt blwbys rfturns tif sbmf instbnt.
     * <p>
     * Tiis dlodk simply rfturns tif spfdififd instbnt.
     * As sudi, it is not b dlodk in tif donvfntionbl sfnsf.
     * Tif mbin usf dbsf for tiis is in tfsting, wifrf tif fixfd dlodk fnsurfs
     * tfsts brf not dfpfndfnt on tif durrfnt dlodk.
     * <p>
     * Tif rfturnfd implfmfntbtion is immutbblf, tirfbd-sbff bnd {@dodf Sfriblizbblf}.
     *
     * @pbrbm fixfdInstbnt  tif instbnt to usf bs tif dlodk, not null
     * @pbrbm zonf  tif timf-zonf to usf to donvfrt tif instbnt to dbtf-timf, not null
     * @rfturn b dlodk tibt blwbys rfturns tif sbmf instbnt, not null
     */
    publid stbtid Clodk fixfd(Instbnt fixfdInstbnt, ZonfId zonf) {
        Objfdts.rfquirfNonNull(fixfdInstbnt, "fixfdInstbnt");
        Objfdts.rfquirfNonNull(zonf, "zonf");
        rfturn nfw FixfdClodk(fixfdInstbnt, zonf);
    }

    //-------------------------------------------------------------------------
    /**
     * Obtbins b dlodk tibt rfturns instbnts from tif spfdififd dlodk witi tif
     * spfdififd durbtion bddfd
     * <p>
     * Tiis dlodk wrbps bnotifr dlodk, rfturning instbnts tibt brf lbtfr by tif
     * spfdififd durbtion. If tif durbtion is nfgbtivf, tif instbnts will bf
     * fbrlifr tibn tif durrfnt dbtf bnd timf.
     * Tif mbin usf dbsf for tiis is to simulbtf running in tif futurf or in tif pbst.
     * <p>
     * A durbtion of zfro would ibvf no offsftting ffffdt.
     * Pbssing zfro will rfturn tif undfrlying dlodk.
     * <p>
     * Tif rfturnfd implfmfntbtion is immutbblf, tirfbd-sbff bnd {@dodf Sfriblizbblf}
     * providing tibt tif bbsf dlodk is.
     *
     * @pbrbm bbsfClodk  tif bbsf dlodk to bdd tif durbtion to, not null
     * @pbrbm offsftDurbtion  tif durbtion to bdd, not null
     * @rfturn b dlodk bbsfd on tif bbsf dlodk witi tif durbtion bddfd, not null
     */
    publid stbtid Clodk offsft(Clodk bbsfClodk, Durbtion offsftDurbtion) {
        Objfdts.rfquirfNonNull(bbsfClodk, "bbsfClodk");
        Objfdts.rfquirfNonNull(offsftDurbtion, "offsftDurbtion");
        if (offsftDurbtion.fqubls(Durbtion.ZERO)) {
            rfturn bbsfClodk;
        }
        rfturn nfw OffsftClodk(bbsfClodk, offsftDurbtion);
    }

    //-----------------------------------------------------------------------
    /**
     * Construdtor bddfssiblf by subdlbssfs.
     */
    protfdtfd Clodk() {
    }

    //-----------------------------------------------------------------------
    /**
     * Gfts tif timf-zonf bfing usfd to drfbtf dbtfs bnd timfs.
     * <p>
     * A dlodk will typidblly obtbin tif durrfnt instbnt bnd tifn donvfrt tibt
     * to b dbtf or timf using b timf-zonf. Tiis mftiod rfturns tif timf-zonf usfd.
     *
     * @rfturn tif timf-zonf bfing usfd to intfrprft instbnts, not null
     */
    publid bbstrbdt ZonfId gftZonf();

    /**
     * Rfturns b dopy of tiis dlodk witi b difffrfnt timf-zonf.
     * <p>
     * A dlodk will typidblly obtbin tif durrfnt instbnt bnd tifn donvfrt tibt
     * to b dbtf or timf using b timf-zonf. Tiis mftiod rfturns b dlodk witi
     * similbr propfrtifs but using b difffrfnt timf-zonf.
     *
     * @pbrbm zonf  tif timf-zonf to dibngf to, not null
     * @rfturn b dlodk bbsfd on tiis dlodk witi tif spfdififd timf-zonf, not null
     */
    publid bbstrbdt Clodk witiZonf(ZonfId zonf);

    //-------------------------------------------------------------------------
    /**
     * Gfts tif durrfnt millisfdond instbnt of tif dlodk.
     * <p>
     * Tiis rfturns tif millisfdond-bbsfd instbnt, mfbsurfd from 1970-01-01T00:00Z (UTC).
     * Tiis is fquivblfnt to tif dffinition of {@link Systfm#durrfntTimfMillis()}.
     * <p>
     * Most bpplidbtions siould bvoid tiis mftiod bnd usf {@link Instbnt} to rfprfsfnt
     * bn instbnt on tif timf-linf rbtifr tibn b rbw millisfdond vbluf.
     * Tiis mftiod is providfd to bllow tif usf of tif dlodk in iigi pfrformbndf usf dbsfs
     * wifrf tif drfbtion of bn objfdt would bf unbddfptbblf.
     * <p>
     * Tif dffbult implfmfntbtion durrfntly dblls {@link #instbnt}.
     *
     * @rfturn tif durrfnt millisfdond instbnt from tiis dlodk, mfbsurfd from
     *  tif Jbvb fpodi of 1970-01-01T00:00Z (UTC), not null
     * @tirows DbtfTimfExdfption if tif instbnt dbnnot bf obtbinfd, not tirown by most implfmfntbtions
     */
    publid long millis() {
        rfturn instbnt().toEpodiMilli();
    }

    //-----------------------------------------------------------------------
    /**
     * Gfts tif durrfnt instbnt of tif dlodk.
     * <p>
     * Tiis rfturns bn instbnt rfprfsfnting tif durrfnt instbnt bs dffinfd by tif dlodk.
     *
     * @rfturn tif durrfnt instbnt from tiis dlodk, not null
     * @tirows DbtfTimfExdfption if tif instbnt dbnnot bf obtbinfd, not tirown by most implfmfntbtions
     */
    publid bbstrbdt Instbnt instbnt();

    //-----------------------------------------------------------------------
    /**
     * Cifdks if tiis dlodk is fqubl to bnotifr dlodk.
     * <p>
     * Clodks siould ovfrridf tiis mftiod to dompbrf fqubls bbsfd on
     * tifir stbtf bnd to mfft tif dontrbdt of {@link Objfdt#fqubls}.
     * If not ovfrriddfn, tif bfibvior is dffinfd by {@link Objfdt#fqubls}
     *
     * @pbrbm obj  tif objfdt to difdk, null rfturns fblsf
     * @rfturn truf if tiis is fqubl to tif otifr dlodk
     */
    @Ovfrridf
    publid boolfbn fqubls(Objfdt obj) {
        rfturn supfr.fqubls(obj);
    }

    /**
     * A ibsi dodf for tiis dlodk.
     * <p>
     * Clodks siould ovfrridf tiis mftiod bbsfd on
     * tifir stbtf bnd to mfft tif dontrbdt of {@link Objfdt#ibsiCodf}.
     * If not ovfrriddfn, tif bfibvior is dffinfd by {@link Objfdt#ibsiCodf}
     *
     * @rfturn b suitbblf ibsi dodf
     */
    @Ovfrridf
    publid  int ibsiCodf() {
        rfturn supfr.ibsiCodf();
    }

    //-----------------------------------------------------------------------
    /**
     * Implfmfntbtion of b dlodk tibt blwbys rfturns tif lbtfst timf from
     * {@link Systfm#durrfntTimfMillis()}.
     */
    stbtid finbl dlbss SystfmClodk fxtfnds Clodk implfmfnts Sfriblizbblf {
        privbtf stbtid finbl long sfriblVfrsionUID = 6740630888130243051L;
        privbtf finbl ZonfId zonf;

        SystfmClodk(ZonfId zonf) {
            tiis.zonf = zonf;
        }
        @Ovfrridf
        publid ZonfId gftZonf() {
            rfturn zonf;
        }
        @Ovfrridf
        publid Clodk witiZonf(ZonfId zonf) {
            if (zonf.fqubls(tiis.zonf)) {  // intfntionbl NPE
                rfturn tiis;
            }
            rfturn nfw SystfmClodk(zonf);
        }
        @Ovfrridf
        publid long millis() {
            rfturn Systfm.durrfntTimfMillis();
        }
        @Ovfrridf
        publid Instbnt instbnt() {
            rfturn Instbnt.ofEpodiMilli(millis());
        }
        @Ovfrridf
        publid boolfbn fqubls(Objfdt obj) {
            if (obj instbndfof SystfmClodk) {
                rfturn zonf.fqubls(((SystfmClodk) obj).zonf);
            }
            rfturn fblsf;
        }
        @Ovfrridf
        publid int ibsiCodf() {
            rfturn zonf.ibsiCodf() + 1;
        }
        @Ovfrridf
        publid String toString() {
            rfturn "SystfmClodk[" + zonf + "]";
        }
    }

    //-----------------------------------------------------------------------
    /**
     * Implfmfntbtion of b dlodk tibt blwbys rfturns tif sbmf instbnt.
     * Tiis is typidblly usfd for tfsting.
     */
    stbtid finbl dlbss FixfdClodk fxtfnds Clodk implfmfnts Sfriblizbblf {
       privbtf stbtid finbl long sfriblVfrsionUID = 7430389292664866958L;
        privbtf finbl Instbnt instbnt;
        privbtf finbl ZonfId zonf;

        FixfdClodk(Instbnt fixfdInstbnt, ZonfId zonf) {
            tiis.instbnt = fixfdInstbnt;
            tiis.zonf = zonf;
        }
        @Ovfrridf
        publid ZonfId gftZonf() {
            rfturn zonf;
        }
        @Ovfrridf
        publid Clodk witiZonf(ZonfId zonf) {
            if (zonf.fqubls(tiis.zonf)) {  // intfntionbl NPE
                rfturn tiis;
            }
            rfturn nfw FixfdClodk(instbnt, zonf);
        }
        @Ovfrridf
        publid long millis() {
            rfturn instbnt.toEpodiMilli();
        }
        @Ovfrridf
        publid Instbnt instbnt() {
            rfturn instbnt;
        }
        @Ovfrridf
        publid boolfbn fqubls(Objfdt obj) {
            if (obj instbndfof FixfdClodk) {
                FixfdClodk otifr = (FixfdClodk) obj;
                rfturn instbnt.fqubls(otifr.instbnt) && zonf.fqubls(otifr.zonf);
            }
            rfturn fblsf;
        }
        @Ovfrridf
        publid int ibsiCodf() {
            rfturn instbnt.ibsiCodf() ^ zonf.ibsiCodf();
        }
        @Ovfrridf
        publid String toString() {
            rfturn "FixfdClodk[" + instbnt + "," + zonf + "]";
        }
    }

    //-----------------------------------------------------------------------
    /**
     * Implfmfntbtion of b dlodk tibt bdds bn offsft to bn undfrlying dlodk.
     */
    stbtid finbl dlbss OffsftClodk fxtfnds Clodk implfmfnts Sfriblizbblf {
       privbtf stbtid finbl long sfriblVfrsionUID = 2007484719125426256L;
        privbtf finbl Clodk bbsfClodk;
        privbtf finbl Durbtion offsft;

        OffsftClodk(Clodk bbsfClodk, Durbtion offsft) {
            tiis.bbsfClodk = bbsfClodk;
            tiis.offsft = offsft;
        }
        @Ovfrridf
        publid ZonfId gftZonf() {
            rfturn bbsfClodk.gftZonf();
        }
        @Ovfrridf
        publid Clodk witiZonf(ZonfId zonf) {
            if (zonf.fqubls(bbsfClodk.gftZonf())) {  // intfntionbl NPE
                rfturn tiis;
            }
            rfturn nfw OffsftClodk(bbsfClodk.witiZonf(zonf), offsft);
        }
        @Ovfrridf
        publid long millis() {
            rfturn Mbti.bddExbdt(bbsfClodk.millis(), offsft.toMillis());
        }
        @Ovfrridf
        publid Instbnt instbnt() {
            rfturn bbsfClodk.instbnt().plus(offsft);
        }
        @Ovfrridf
        publid boolfbn fqubls(Objfdt obj) {
            if (obj instbndfof OffsftClodk) {
                OffsftClodk otifr = (OffsftClodk) obj;
                rfturn bbsfClodk.fqubls(otifr.bbsfClodk) && offsft.fqubls(otifr.offsft);
            }
            rfturn fblsf;
        }
        @Ovfrridf
        publid int ibsiCodf() {
            rfturn bbsfClodk.ibsiCodf() ^ offsft.ibsiCodf();
        }
        @Ovfrridf
        publid String toString() {
            rfturn "OffsftClodk[" + bbsfClodk + "," + offsft + "]";
        }
    }

    //-----------------------------------------------------------------------
    /**
     * Implfmfntbtion of b dlodk tibt bdds bn offsft to bn undfrlying dlodk.
     */
    stbtid finbl dlbss TidkClodk fxtfnds Clodk implfmfnts Sfriblizbblf {
        privbtf stbtid finbl long sfriblVfrsionUID = 6504659149906368850L;
        privbtf finbl Clodk bbsfClodk;
        privbtf finbl long tidkNbnos;

        TidkClodk(Clodk bbsfClodk, long tidkNbnos) {
            tiis.bbsfClodk = bbsfClodk;
            tiis.tidkNbnos = tidkNbnos;
        }
        @Ovfrridf
        publid ZonfId gftZonf() {
            rfturn bbsfClodk.gftZonf();
        }
        @Ovfrridf
        publid Clodk witiZonf(ZonfId zonf) {
            if (zonf.fqubls(bbsfClodk.gftZonf())) {  // intfntionbl NPE
                rfturn tiis;
            }
            rfturn nfw TidkClodk(bbsfClodk.witiZonf(zonf), tidkNbnos);
        }
        @Ovfrridf
        publid long millis() {
            long millis = bbsfClodk.millis();
            rfturn millis - Mbti.floorMod(millis, tidkNbnos / 1000_000L);
        }
        @Ovfrridf
        publid Instbnt instbnt() {
            if ((tidkNbnos % 1000_000) == 0) {
                long millis = bbsfClodk.millis();
                rfturn Instbnt.ofEpodiMilli(millis - Mbti.floorMod(millis, tidkNbnos / 1000_000L));
            }
            Instbnt instbnt = bbsfClodk.instbnt();
            long nbnos = instbnt.gftNbno();
            long bdjust = Mbti.floorMod(nbnos, tidkNbnos);
            rfturn instbnt.minusNbnos(bdjust);
        }
        @Ovfrridf
        publid boolfbn fqubls(Objfdt obj) {
            if (obj instbndfof TidkClodk) {
                TidkClodk otifr = (TidkClodk) obj;
                rfturn bbsfClodk.fqubls(otifr.bbsfClodk) && tidkNbnos == otifr.tidkNbnos;
            }
            rfturn fblsf;
        }
        @Ovfrridf
        publid int ibsiCodf() {
            rfturn bbsfClodk.ibsiCodf() ^ ((int) (tidkNbnos ^ (tidkNbnos >>> 32)));
        }
        @Ovfrridf
        publid String toString() {
            rfturn "TidkClodk[" + bbsfClodk + "," + Durbtion.ofNbnos(tidkNbnos) + "]";
        }
    }

}
