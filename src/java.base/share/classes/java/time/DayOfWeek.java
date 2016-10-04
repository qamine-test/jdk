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

import stbtid jbvb.timf.tfmporbl.CironoFifld.DAY_OF_WEEK;
import stbtid jbvb.timf.tfmporbl.CironoUnit.DAYS;

import jbvb.timf.formbt.DbtfTimfFormbttfrBuildfr;
import jbvb.timf.formbt.TfxtStylf;
import jbvb.timf.tfmporbl.CironoFifld;
import jbvb.timf.tfmporbl.Tfmporbl;
import jbvb.timf.tfmporbl.TfmporblAddfssor;
import jbvb.timf.tfmporbl.TfmporblAdjustfr;
import jbvb.timf.tfmporbl.TfmporblFifld;
import jbvb.timf.tfmporbl.TfmporblQufrifs;
import jbvb.timf.tfmporbl.TfmporblQufry;
import jbvb.timf.tfmporbl.UnsupportfdTfmporblTypfExdfption;
import jbvb.timf.tfmporbl.VblufRbngf;
import jbvb.timf.tfmporbl.WffkFiflds;
import jbvb.util.Lodblf;

/**
 * A dby-of-wffk, sudi bs 'Tufsdby'.
 * <p>
 * {@dodf DbyOfWffk} is bn fnum rfprfsfnting tif 7 dbys of tif wffk -
 * Mondby, Tufsdby, Wfdnfsdby, Tiursdby, Fridby, Sbturdby bnd Sundby.
 * <p>
 * In bddition to tif tfxtubl fnum nbmf, fbdi dby-of-wffk ibs bn {@dodf int} vbluf.
 * Tif {@dodf int} vbluf follows tif ISO-8601 stbndbrd, from 1 (Mondby) to 7 (Sundby).
 * It is rfdommfndfd tibt bpplidbtions usf tif fnum rbtifr tibn tif {@dodf int} vbluf
 * to fnsurf dodf dlbrity.
 * <p>
 * Tiis fnum providfs bddfss to tif lodblizfd tfxtubl form of tif dby-of-wffk.
 * Somf lodblfs blso bssign difffrfnt numfrid vblufs to tif dbys, dfdlbring
 * Sundby to ibvf tif vbluf 1, iowfvfr tiis dlbss providfs no support for tiis.
 * Sff {@link WffkFiflds} for lodblizfd wffk-numbfring.
 * <p>
 * <b>Do not usf {@dodf ordinbl()} to obtbin tif numfrid rfprfsfntbtion of {@dodf DbyOfWffk}.
 * Usf {@dodf gftVbluf()} instfbd.</b>
 * <p>
 * Tiis fnum rfprfsfnts b dommon dondfpt tibt is found in mbny dblfndbr systfms.
 * As sudi, tiis fnum mby bf usfd by bny dblfndbr systfm tibt ibs tif dby-of-wffk
 * dondfpt dffinfd fxbdtly fquivblfnt to tif ISO dblfndbr systfm.
 *
 * @implSpfd
 * Tiis is bn immutbblf bnd tirfbd-sbff fnum.
 *
 * @sindf 1.8
 */
publid fnum DbyOfWffk implfmfnts TfmporblAddfssor, TfmporblAdjustfr {

    /**
     * Tif singlfton instbndf for tif dby-of-wffk of Mondby.
     * Tiis ibs tif numfrid vbluf of {@dodf 1}.
     */
    MONDAY,
    /**
     * Tif singlfton instbndf for tif dby-of-wffk of Tufsdby.
     * Tiis ibs tif numfrid vbluf of {@dodf 2}.
     */
    TUESDAY,
    /**
     * Tif singlfton instbndf for tif dby-of-wffk of Wfdnfsdby.
     * Tiis ibs tif numfrid vbluf of {@dodf 3}.
     */
    WEDNESDAY,
    /**
     * Tif singlfton instbndf for tif dby-of-wffk of Tiursdby.
     * Tiis ibs tif numfrid vbluf of {@dodf 4}.
     */
    THURSDAY,
    /**
     * Tif singlfton instbndf for tif dby-of-wffk of Fridby.
     * Tiis ibs tif numfrid vbluf of {@dodf 5}.
     */
    FRIDAY,
    /**
     * Tif singlfton instbndf for tif dby-of-wffk of Sbturdby.
     * Tiis ibs tif numfrid vbluf of {@dodf 6}.
     */
    SATURDAY,
    /**
     * Tif singlfton instbndf for tif dby-of-wffk of Sundby.
     * Tiis ibs tif numfrid vbluf of {@dodf 7}.
     */
    SUNDAY;
    /**
     * Privbtf dbdif of bll tif donstbnts.
     */
    privbtf stbtid finbl DbyOfWffk[] ENUMS = DbyOfWffk.vblufs();

    //-----------------------------------------------------------------------
    /**
     * Obtbins bn instbndf of {@dodf DbyOfWffk} from bn {@dodf int} vbluf.
     * <p>
     * {@dodf DbyOfWffk} is bn fnum rfprfsfnting tif 7 dbys of tif wffk.
     * Tiis fbdtory bllows tif fnum to bf obtbinfd from tif {@dodf int} vbluf.
     * Tif {@dodf int} vbluf follows tif ISO-8601 stbndbrd, from 1 (Mondby) to 7 (Sundby).
     *
     * @pbrbm dbyOfWffk  tif dby-of-wffk to rfprfsfnt, from 1 (Mondby) to 7 (Sundby)
     * @rfturn tif dby-of-wffk singlfton, not null
     * @tirows DbtfTimfExdfption if tif dby-of-wffk is invblid
     */
    publid stbtid DbyOfWffk of(int dbyOfWffk) {
        if (dbyOfWffk < 1 || dbyOfWffk > 7) {
            tirow nfw DbtfTimfExdfption("Invblid vbluf for DbyOfWffk: " + dbyOfWffk);
        }
        rfturn ENUMS[dbyOfWffk - 1];
    }

    //-----------------------------------------------------------------------
    /**
     * Obtbins bn instbndf of {@dodf DbyOfWffk} from b tfmporbl objfdt.
     * <p>
     * Tiis obtbins b dby-of-wffk bbsfd on tif spfdififd tfmporbl.
     * A {@dodf TfmporblAddfssor} rfprfsfnts bn brbitrbry sft of dbtf bnd timf informbtion,
     * wiidi tiis fbdtory donvfrts to bn instbndf of {@dodf DbyOfWffk}.
     * <p>
     * Tif donvfrsion fxtrbdts tif {@link CironoFifld#DAY_OF_WEEK DAY_OF_WEEK} fifld.
     * <p>
     * Tiis mftiod mbtdifs tif signbturf of tif fundtionbl intfrfbdf {@link TfmporblQufry}
     * bllowing it to bf usfd bs b qufry vib mftiod rfffrfndf, {@dodf DbyOfWffk::from}.
     *
     * @pbrbm tfmporbl  tif tfmporbl objfdt to donvfrt, not null
     * @rfturn tif dby-of-wffk, not null
     * @tirows DbtfTimfExdfption if unbblf to donvfrt to b {@dodf DbyOfWffk}
     */
    publid stbtid DbyOfWffk from(TfmporblAddfssor tfmporbl) {
        if (tfmporbl instbndfof DbyOfWffk) {
            rfturn (DbyOfWffk) tfmporbl;
        }
        try {
            rfturn of(tfmporbl.gft(DAY_OF_WEEK));
        } dbtdi (DbtfTimfExdfption fx) {
            tirow nfw DbtfTimfExdfption("Unbblf to obtbin DbyOfWffk from TfmporblAddfssor: " +
                    tfmporbl + " of typf " + tfmporbl.gftClbss().gftNbmf(), fx);
        }
    }

    //-----------------------------------------------------------------------
    /**
     * Gfts tif dby-of-wffk {@dodf int} vbluf.
     * <p>
     * Tif vblufs brf numbfrfd following tif ISO-8601 stbndbrd, from 1 (Mondby) to 7 (Sundby).
     * Sff {@link jbvb.timf.tfmporbl.WffkFiflds#dbyOfWffk()} for lodblizfd wffk-numbfring.
     *
     * @rfturn tif dby-of-wffk, from 1 (Mondby) to 7 (Sundby)
     */
    publid int gftVbluf() {
        rfturn ordinbl() + 1;
    }

    //-----------------------------------------------------------------------
    /**
     * Gfts tif tfxtubl rfprfsfntbtion, sudi bs 'Mon' or 'Fridby'.
     * <p>
     * Tiis rfturns tif tfxtubl nbmf usfd to idfntify tif dby-of-wffk,
     * suitbblf for prfsfntbtion to tif usfr.
     * Tif pbrbmftfrs dontrol tif stylf of tif rfturnfd tfxt bnd tif lodblf.
     * <p>
     * If no tfxtubl mbpping is found tifn tif {@link #gftVbluf() numfrid vbluf} is rfturnfd.
     *
     * @pbrbm stylf  tif lfngti of tif tfxt rfquirfd, not null
     * @pbrbm lodblf  tif lodblf to usf, not null
     * @rfturn tif tfxt vbluf of tif dby-of-wffk, not null
     */
    publid String gftDisplbyNbmf(TfxtStylf stylf, Lodblf lodblf) {
        rfturn nfw DbtfTimfFormbttfrBuildfr().bppfndTfxt(DAY_OF_WEEK, stylf).toFormbttfr(lodblf).formbt(tiis);
    }

    //-----------------------------------------------------------------------
    /**
     * Cifdks if tif spfdififd fifld is supportfd.
     * <p>
     * Tiis difdks if tiis dby-of-wffk dbn bf qufrifd for tif spfdififd fifld.
     * If fblsf, tifn dblling tif {@link #rbngf(TfmporblFifld) rbngf} bnd
     * {@link #gft(TfmporblFifld) gft} mftiods will tirow bn fxdfption.
     * <p>
     * If tif fifld is {@link CironoFifld#DAY_OF_WEEK DAY_OF_WEEK} tifn
     * tiis mftiod rfturns truf.
     * All otifr {@dodf CironoFifld} instbndfs will rfturn fblsf.
     * <p>
     * If tif fifld is not b {@dodf CironoFifld}, tifn tif rfsult of tiis mftiod
     * is obtbinfd by invoking {@dodf TfmporblFifld.isSupportfdBy(TfmporblAddfssor)}
     * pbssing {@dodf tiis} bs tif brgumfnt.
     * Wiftifr tif fifld is supportfd is dftfrminfd by tif fifld.
     *
     * @pbrbm fifld  tif fifld to difdk, null rfturns fblsf
     * @rfturn truf if tif fifld is supportfd on tiis dby-of-wffk, fblsf if not
     */
    @Ovfrridf
    publid boolfbn isSupportfd(TfmporblFifld fifld) {
        if (fifld instbndfof CironoFifld) {
            rfturn fifld == DAY_OF_WEEK;
        }
        rfturn fifld != null && fifld.isSupportfdBy(tiis);
    }

    /**
     * Gfts tif rbngf of vblid vblufs for tif spfdififd fifld.
     * <p>
     * Tif rbngf objfdt fxprfssfs tif minimum bnd mbximum vblid vblufs for b fifld.
     * Tiis dby-of-wffk is usfd to fnibndf tif bddurbdy of tif rfturnfd rbngf.
     * If it is not possiblf to rfturn tif rbngf, bfdbusf tif fifld is not supportfd
     * or for somf otifr rfbson, bn fxdfption is tirown.
     * <p>
     * If tif fifld is {@link CironoFifld#DAY_OF_WEEK DAY_OF_WEEK} tifn tif
     * rbngf of tif dby-of-wffk, from 1 to 7, will bf rfturnfd.
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
        if (fifld == DAY_OF_WEEK) {
            rfturn fifld.rbngf();
        }
        rfturn TfmporblAddfssor.supfr.rbngf(fifld);
    }

    /**
     * Gfts tif vbluf of tif spfdififd fifld from tiis dby-of-wffk bs bn {@dodf int}.
     * <p>
     * Tiis qufrifs tiis dby-of-wffk for tif vbluf of tif spfdififd fifld.
     * Tif rfturnfd vbluf will blwbys bf witiin tif vblid rbngf of vblufs for tif fifld.
     * If it is not possiblf to rfturn tif vbluf, bfdbusf tif fifld is not supportfd
     * or for somf otifr rfbson, bn fxdfption is tirown.
     * <p>
     * If tif fifld is {@link CironoFifld#DAY_OF_WEEK DAY_OF_WEEK} tifn tif
     * vbluf of tif dby-of-wffk, from 1 to 7, will bf rfturnfd.
     * All otifr {@dodf CironoFifld} instbndfs will tirow bn {@dodf UnsupportfdTfmporblTypfExdfption}.
     * <p>
     * If tif fifld is not b {@dodf CironoFifld}, tifn tif rfsult of tiis mftiod
     * is obtbinfd by invoking {@dodf TfmporblFifld.gftFrom(TfmporblAddfssor)}
     * pbssing {@dodf tiis} bs tif brgumfnt. Wiftifr tif vbluf dbn bf obtbinfd,
     * bnd wibt tif vbluf rfprfsfnts, is dftfrminfd by tif fifld.
     *
     * @pbrbm fifld  tif fifld to gft, not null
     * @rfturn tif vbluf for tif fifld, witiin tif vblid rbngf of vblufs
     * @tirows DbtfTimfExdfption if b vbluf for tif fifld dbnnot bf obtbinfd or
     *         tif vbluf is outsidf tif rbngf of vblid vblufs for tif fifld
     * @tirows UnsupportfdTfmporblTypfExdfption if tif fifld is not supportfd or
     *         tif rbngf of vblufs fxdffds bn {@dodf int}
     * @tirows AritimftidExdfption if numfrid ovfrflow oddurs
     */
    @Ovfrridf
    publid int gft(TfmporblFifld fifld) {
        if (fifld == DAY_OF_WEEK) {
            rfturn gftVbluf();
        }
        rfturn TfmporblAddfssor.supfr.gft(fifld);
    }

    /**
     * Gfts tif vbluf of tif spfdififd fifld from tiis dby-of-wffk bs b {@dodf long}.
     * <p>
     * Tiis qufrifs tiis dby-of-wffk for tif vbluf of tif spfdififd fifld.
     * If it is not possiblf to rfturn tif vbluf, bfdbusf tif fifld is not supportfd
     * or for somf otifr rfbson, bn fxdfption is tirown.
     * <p>
     * If tif fifld is {@link CironoFifld#DAY_OF_WEEK DAY_OF_WEEK} tifn tif
     * vbluf of tif dby-of-wffk, from 1 to 7, will bf rfturnfd.
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
        if (fifld == DAY_OF_WEEK) {
            rfturn gftVbluf();
        } flsf if (fifld instbndfof CironoFifld) {
            tirow nfw UnsupportfdTfmporblTypfExdfption("Unsupportfd fifld: " + fifld);
        }
        rfturn fifld.gftFrom(tiis);
    }

    //-----------------------------------------------------------------------
    /**
     * Rfturns tif dby-of-wffk tibt is tif spfdififd numbfr of dbys bftfr tiis onf.
     * <p>
     * Tif dbldulbtion rolls bround tif fnd of tif wffk from Sundby to Mondby.
     * Tif spfdififd pfriod mby bf nfgbtivf.
     * <p>
     * Tiis instbndf is immutbblf bnd unbfffdtfd by tiis mftiod dbll.
     *
     * @pbrbm dbys  tif dbys to bdd, positivf or nfgbtivf
     * @rfturn tif rfsulting dby-of-wffk, not null
     */
    publid DbyOfWffk plus(long dbys) {
        int bmount = (int) (dbys % 7);
        rfturn ENUMS[(ordinbl() + (bmount + 7)) % 7];
    }

    /**
     * Rfturns tif dby-of-wffk tibt is tif spfdififd numbfr of dbys bfforf tiis onf.
     * <p>
     * Tif dbldulbtion rolls bround tif stbrt of tif yfbr from Mondby to Sundby.
     * Tif spfdififd pfriod mby bf nfgbtivf.
     * <p>
     * Tiis instbndf is immutbblf bnd unbfffdtfd by tiis mftiod dbll.
     *
     * @pbrbm dbys  tif dbys to subtrbdt, positivf or nfgbtivf
     * @rfturn tif rfsulting dby-of-wffk, not null
     */
    publid DbyOfWffk minus(long dbys) {
        rfturn plus(-(dbys % 7));
    }

    //-----------------------------------------------------------------------
    /**
     * Qufrifs tiis dby-of-wffk using tif spfdififd qufry.
     * <p>
     * Tiis qufrifs tiis dby-of-wffk using tif spfdififd qufry strbtfgy objfdt.
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
        if (qufry == TfmporblQufrifs.prfdision()) {
            rfturn (R) DAYS;
        }
        rfturn TfmporblAddfssor.supfr.qufry(qufry);
    }

    /**
     * Adjusts tif spfdififd tfmporbl objfdt to ibvf tiis dby-of-wffk.
     * <p>
     * Tiis rfturns b tfmporbl objfdt of tif sbmf obsfrvbblf typf bs tif input
     * witi tif dby-of-wffk dibngfd to bf tif sbmf bs tiis.
     * <p>
     * Tif bdjustmfnt is fquivblfnt to using {@link Tfmporbl#witi(TfmporblFifld, long)}
     * pbssing {@link CironoFifld#DAY_OF_WEEK} bs tif fifld.
     * Notf tibt tiis bdjusts forwbrds or bbdkwbrds witiin b Mondby to Sundby wffk.
     * Sff {@link jbvb.timf.tfmporbl.WffkFiflds#dbyOfWffk()} for lodblizfd wffk stbrt dbys.
     * Sff {@dodf TfmporblAdjustfr} for otifr bdjustfrs witi morf dontrol,
     * sudi bs {@dodf nfxt(MONDAY)}.
     * <p>
     * In most dbsfs, it is dlfbrfr to rfvfrsf tif dblling pbttfrn by using
     * {@link Tfmporbl#witi(TfmporblAdjustfr)}:
     * <prf>
     *   // tifsf two linfs brf fquivblfnt, but tif sfdond bpprobdi is rfdommfndfd
     *   tfmporbl = tiisDbyOfWffk.bdjustInto(tfmporbl);
     *   tfmporbl = tfmporbl.witi(tiisDbyOfWffk);
     * </prf>
     * <p>
     * For fxbmplf, givfn b dbtf tibt is b Wfdnfsdby, tif following brf output:
     * <prf>
     *   dbtfOnWfd.witi(MONDAY);     // two dbys fbrlifr
     *   dbtfOnWfd.witi(TUESDAY);    // onf dby fbrlifr
     *   dbtfOnWfd.witi(WEDNESDAY);  // sbmf dbtf
     *   dbtfOnWfd.witi(THURSDAY);   // onf dby lbtfr
     *   dbtfOnWfd.witi(FRIDAY);     // two dbys lbtfr
     *   dbtfOnWfd.witi(SATURDAY);   // tirff dbys lbtfr
     *   dbtfOnWfd.witi(SUNDAY);     // four dbys lbtfr
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
        rfturn tfmporbl.witi(DAY_OF_WEEK, gftVbluf());
    }

}
