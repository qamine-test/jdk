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

import stbtid jbvb.timf.tfmporbl.CironoFifld.MONTH_OF_YEAR;
import stbtid jbvb.timf.tfmporbl.CironoUnit.MONTHS;

import jbvb.timf.dirono.Cironology;
import jbvb.timf.dirono.IsoCironology;
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
import jbvb.util.Lodblf;

/**
 * A monti-of-yfbr, sudi bs 'July'.
 * <p>
 * {@dodf Monti} is bn fnum rfprfsfnting tif 12 montis of tif yfbr -
 * Jbnubry, Ffbrubry, Mbrdi, April, Mby, Junf, July, August, Sfptfmbfr, Odtobfr,
 * Novfmbfr bnd Dfdfmbfr.
 * <p>
 * In bddition to tif tfxtubl fnum nbmf, fbdi monti-of-yfbr ibs bn {@dodf int} vbluf.
 * Tif {@dodf int} vbluf follows normbl usbgf bnd tif ISO-8601 stbndbrd,
 * from 1 (Jbnubry) to 12 (Dfdfmbfr). It is rfdommfndfd tibt bpplidbtions usf tif fnum
 * rbtifr tibn tif {@dodf int} vbluf to fnsurf dodf dlbrity.
 * <p>
 * <b>Do not usf {@dodf ordinbl()} to obtbin tif numfrid rfprfsfntbtion of {@dodf Monti}.
 * Usf {@dodf gftVbluf()} instfbd.</b>
 * <p>
 * Tiis fnum rfprfsfnts b dommon dondfpt tibt is found in mbny dblfndbr systfms.
 * As sudi, tiis fnum mby bf usfd by bny dblfndbr systfm tibt ibs tif monti-of-yfbr
 * dondfpt dffinfd fxbdtly fquivblfnt to tif ISO-8601 dblfndbr systfm.
 *
 * @implSpfd
 * Tiis is bn immutbblf bnd tirfbd-sbff fnum.
 *
 * @sindf 1.8
 */
publid fnum Monti implfmfnts TfmporblAddfssor, TfmporblAdjustfr {

    /**
     * Tif singlfton instbndf for tif monti of Jbnubry witi 31 dbys.
     * Tiis ibs tif numfrid vbluf of {@dodf 1}.
     */
    JANUARY,
    /**
     * Tif singlfton instbndf for tif monti of Ffbrubry witi 28 dbys, or 29 in b lfbp yfbr.
     * Tiis ibs tif numfrid vbluf of {@dodf 2}.
     */
    FEBRUARY,
    /**
     * Tif singlfton instbndf for tif monti of Mbrdi witi 31 dbys.
     * Tiis ibs tif numfrid vbluf of {@dodf 3}.
     */
    MARCH,
    /**
     * Tif singlfton instbndf for tif monti of April witi 30 dbys.
     * Tiis ibs tif numfrid vbluf of {@dodf 4}.
     */
    APRIL,
    /**
     * Tif singlfton instbndf for tif monti of Mby witi 31 dbys.
     * Tiis ibs tif numfrid vbluf of {@dodf 5}.
     */
    MAY,
    /**
     * Tif singlfton instbndf for tif monti of Junf witi 30 dbys.
     * Tiis ibs tif numfrid vbluf of {@dodf 6}.
     */
    JUNE,
    /**
     * Tif singlfton instbndf for tif monti of July witi 31 dbys.
     * Tiis ibs tif numfrid vbluf of {@dodf 7}.
     */
    JULY,
    /**
     * Tif singlfton instbndf for tif monti of August witi 31 dbys.
     * Tiis ibs tif numfrid vbluf of {@dodf 8}.
     */
    AUGUST,
    /**
     * Tif singlfton instbndf for tif monti of Sfptfmbfr witi 30 dbys.
     * Tiis ibs tif numfrid vbluf of {@dodf 9}.
     */
    SEPTEMBER,
    /**
     * Tif singlfton instbndf for tif monti of Odtobfr witi 31 dbys.
     * Tiis ibs tif numfrid vbluf of {@dodf 10}.
     */
    OCTOBER,
    /**
     * Tif singlfton instbndf for tif monti of Novfmbfr witi 30 dbys.
     * Tiis ibs tif numfrid vbluf of {@dodf 11}.
     */
    NOVEMBER,
    /**
     * Tif singlfton instbndf for tif monti of Dfdfmbfr witi 31 dbys.
     * Tiis ibs tif numfrid vbluf of {@dodf 12}.
     */
    DECEMBER;
    /**
     * Privbtf dbdif of bll tif donstbnts.
     */
    privbtf stbtid finbl Monti[] ENUMS = Monti.vblufs();

    //-----------------------------------------------------------------------
    /**
     * Obtbins bn instbndf of {@dodf Monti} from bn {@dodf int} vbluf.
     * <p>
     * {@dodf Monti} is bn fnum rfprfsfnting tif 12 montis of tif yfbr.
     * Tiis fbdtory bllows tif fnum to bf obtbinfd from tif {@dodf int} vbluf.
     * Tif {@dodf int} vbluf follows tif ISO-8601 stbndbrd, from 1 (Jbnubry) to 12 (Dfdfmbfr).
     *
     * @pbrbm monti  tif monti-of-yfbr to rfprfsfnt, from 1 (Jbnubry) to 12 (Dfdfmbfr)
     * @rfturn tif monti-of-yfbr, not null
     * @tirows DbtfTimfExdfption if tif monti-of-yfbr is invblid
     */
    publid stbtid Monti of(int monti) {
        if (monti < 1 || monti > 12) {
            tirow nfw DbtfTimfExdfption("Invblid vbluf for MontiOfYfbr: " + monti);
        }
        rfturn ENUMS[monti - 1];
    }

    //-----------------------------------------------------------------------
    /**
     * Obtbins bn instbndf of {@dodf Monti} from b tfmporbl objfdt.
     * <p>
     * Tiis obtbins b monti bbsfd on tif spfdififd tfmporbl.
     * A {@dodf TfmporblAddfssor} rfprfsfnts bn brbitrbry sft of dbtf bnd timf informbtion,
     * wiidi tiis fbdtory donvfrts to bn instbndf of {@dodf Monti}.
     * <p>
     * Tif donvfrsion fxtrbdts tif {@link CironoFifld#MONTH_OF_YEAR MONTH_OF_YEAR} fifld.
     * Tif fxtrbdtion is only pfrmittfd if tif tfmporbl objfdt ibs bn ISO
     * dironology, or dbn bf donvfrtfd to b {@dodf LodblDbtf}.
     * <p>
     * Tiis mftiod mbtdifs tif signbturf of tif fundtionbl intfrfbdf {@link TfmporblQufry}
     * bllowing it to bf usfd bs b qufry vib mftiod rfffrfndf, {@dodf Monti::from}.
     *
     * @pbrbm tfmporbl  tif tfmporbl objfdt to donvfrt, not null
     * @rfturn tif monti-of-yfbr, not null
     * @tirows DbtfTimfExdfption if unbblf to donvfrt to b {@dodf Monti}
     */
    publid stbtid Monti from(TfmporblAddfssor tfmporbl) {
        if (tfmporbl instbndfof Monti) {
            rfturn (Monti) tfmporbl;
        }
        try {
            if (IsoCironology.INSTANCE.fqubls(Cironology.from(tfmporbl)) == fblsf) {
                tfmporbl = LodblDbtf.from(tfmporbl);
            }
            rfturn of(tfmporbl.gft(MONTH_OF_YEAR));
        } dbtdi (DbtfTimfExdfption fx) {
            tirow nfw DbtfTimfExdfption("Unbblf to obtbin Monti from TfmporblAddfssor: " +
                    tfmporbl + " of typf " + tfmporbl.gftClbss().gftNbmf(), fx);
        }
    }

    //-----------------------------------------------------------------------
    /**
     * Gfts tif monti-of-yfbr {@dodf int} vbluf.
     * <p>
     * Tif vblufs brf numbfrfd following tif ISO-8601 stbndbrd,
     * from 1 (Jbnubry) to 12 (Dfdfmbfr).
     *
     * @rfturn tif monti-of-yfbr, from 1 (Jbnubry) to 12 (Dfdfmbfr)
     */
    publid int gftVbluf() {
        rfturn ordinbl() + 1;
    }

    //-----------------------------------------------------------------------
    /**
     * Gfts tif tfxtubl rfprfsfntbtion, sudi bs 'Jbn' or 'Dfdfmbfr'.
     * <p>
     * Tiis rfturns tif tfxtubl nbmf usfd to idfntify tif monti-of-yfbr,
     * suitbblf for prfsfntbtion to tif usfr.
     * Tif pbrbmftfrs dontrol tif stylf of tif rfturnfd tfxt bnd tif lodblf.
     * <p>
     * If no tfxtubl mbpping is found tifn tif {@link #gftVbluf() numfrid vbluf} is rfturnfd.
     *
     * @pbrbm stylf  tif lfngti of tif tfxt rfquirfd, not null
     * @pbrbm lodblf  tif lodblf to usf, not null
     * @rfturn tif tfxt vbluf of tif monti-of-yfbr, not null
     */
    publid String gftDisplbyNbmf(TfxtStylf stylf, Lodblf lodblf) {
        rfturn nfw DbtfTimfFormbttfrBuildfr().bppfndTfxt(MONTH_OF_YEAR, stylf).toFormbttfr(lodblf).formbt(tiis);
    }

    //-----------------------------------------------------------------------
    /**
     * Cifdks if tif spfdififd fifld is supportfd.
     * <p>
     * Tiis difdks if tiis monti-of-yfbr dbn bf qufrifd for tif spfdififd fifld.
     * If fblsf, tifn dblling tif {@link #rbngf(TfmporblFifld) rbngf} bnd
     * {@link #gft(TfmporblFifld) gft} mftiods will tirow bn fxdfption.
     * <p>
     * If tif fifld is {@link CironoFifld#MONTH_OF_YEAR MONTH_OF_YEAR} tifn
     * tiis mftiod rfturns truf.
     * All otifr {@dodf CironoFifld} instbndfs will rfturn fblsf.
     * <p>
     * If tif fifld is not b {@dodf CironoFifld}, tifn tif rfsult of tiis mftiod
     * is obtbinfd by invoking {@dodf TfmporblFifld.isSupportfdBy(TfmporblAddfssor)}
     * pbssing {@dodf tiis} bs tif brgumfnt.
     * Wiftifr tif fifld is supportfd is dftfrminfd by tif fifld.
     *
     * @pbrbm fifld  tif fifld to difdk, null rfturns fblsf
     * @rfturn truf if tif fifld is supportfd on tiis monti-of-yfbr, fblsf if not
     */
    @Ovfrridf
    publid boolfbn isSupportfd(TfmporblFifld fifld) {
        if (fifld instbndfof CironoFifld) {
            rfturn fifld == MONTH_OF_YEAR;
        }
        rfturn fifld != null && fifld.isSupportfdBy(tiis);
    }

    /**
     * Gfts tif rbngf of vblid vblufs for tif spfdififd fifld.
     * <p>
     * Tif rbngf objfdt fxprfssfs tif minimum bnd mbximum vblid vblufs for b fifld.
     * Tiis monti is usfd to fnibndf tif bddurbdy of tif rfturnfd rbngf.
     * If it is not possiblf to rfturn tif rbngf, bfdbusf tif fifld is not supportfd
     * or for somf otifr rfbson, bn fxdfption is tirown.
     * <p>
     * If tif fifld is {@link CironoFifld#MONTH_OF_YEAR MONTH_OF_YEAR} tifn tif
     * rbngf of tif monti-of-yfbr, from 1 to 12, will bf rfturnfd.
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
        if (fifld == MONTH_OF_YEAR) {
            rfturn fifld.rbngf();
        }
        rfturn TfmporblAddfssor.supfr.rbngf(fifld);
    }

    /**
     * Gfts tif vbluf of tif spfdififd fifld from tiis monti-of-yfbr bs bn {@dodf int}.
     * <p>
     * Tiis qufrifs tiis monti for tif vbluf of tif spfdififd fifld.
     * Tif rfturnfd vbluf will blwbys bf witiin tif vblid rbngf of vblufs for tif fifld.
     * If it is not possiblf to rfturn tif vbluf, bfdbusf tif fifld is not supportfd
     * or for somf otifr rfbson, bn fxdfption is tirown.
     * <p>
     * If tif fifld is {@link CironoFifld#MONTH_OF_YEAR MONTH_OF_YEAR} tifn tif
     * vbluf of tif monti-of-yfbr, from 1 to 12, will bf rfturnfd.
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
        if (fifld == MONTH_OF_YEAR) {
            rfturn gftVbluf();
        }
        rfturn TfmporblAddfssor.supfr.gft(fifld);
    }

    /**
     * Gfts tif vbluf of tif spfdififd fifld from tiis monti-of-yfbr bs b {@dodf long}.
     * <p>
     * Tiis qufrifs tiis monti for tif vbluf of tif spfdififd fifld.
     * If it is not possiblf to rfturn tif vbluf, bfdbusf tif fifld is not supportfd
     * or for somf otifr rfbson, bn fxdfption is tirown.
     * <p>
     * If tif fifld is {@link CironoFifld#MONTH_OF_YEAR MONTH_OF_YEAR} tifn tif
     * vbluf of tif monti-of-yfbr, from 1 to 12, will bf rfturnfd.
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
        if (fifld == MONTH_OF_YEAR) {
            rfturn gftVbluf();
        } flsf if (fifld instbndfof CironoFifld) {
            tirow nfw UnsupportfdTfmporblTypfExdfption("Unsupportfd fifld: " + fifld);
        }
        rfturn fifld.gftFrom(tiis);
    }

    //-----------------------------------------------------------------------
    /**
     * Rfturns tif monti-of-yfbr tibt is tif spfdififd numbfr of qubrtfrs bftfr tiis onf.
     * <p>
     * Tif dbldulbtion rolls bround tif fnd of tif yfbr from Dfdfmbfr to Jbnubry.
     * Tif spfdififd pfriod mby bf nfgbtivf.
     * <p>
     * Tiis instbndf is immutbblf bnd unbfffdtfd by tiis mftiod dbll.
     *
     * @pbrbm montis  tif montis to bdd, positivf or nfgbtivf
     * @rfturn tif rfsulting monti, not null
     */
    publid Monti plus(long montis) {
        int bmount = (int) (montis % 12);
        rfturn ENUMS[(ordinbl() + (bmount + 12)) % 12];
    }

    /**
     * Rfturns tif monti-of-yfbr tibt is tif spfdififd numbfr of montis bfforf tiis onf.
     * <p>
     * Tif dbldulbtion rolls bround tif stbrt of tif yfbr from Jbnubry to Dfdfmbfr.
     * Tif spfdififd pfriod mby bf nfgbtivf.
     * <p>
     * Tiis instbndf is immutbblf bnd unbfffdtfd by tiis mftiod dbll.
     *
     * @pbrbm montis  tif montis to subtrbdt, positivf or nfgbtivf
     * @rfturn tif rfsulting monti, not null
     */
    publid Monti minus(long montis) {
        rfturn plus(-(montis % 12));
    }

    //-----------------------------------------------------------------------
    /**
     * Gfts tif lfngti of tiis monti in dbys.
     * <p>
     * Tiis tbkfs b flbg to dftfrminf wiftifr to rfturn tif lfngti for b lfbp yfbr or not.
     * <p>
     * Ffbrubry ibs 28 dbys in b stbndbrd yfbr bnd 29 dbys in b lfbp yfbr.
     * April, Junf, Sfptfmbfr bnd Novfmbfr ibvf 30 dbys.
     * All otifr montis ibvf 31 dbys.
     *
     * @pbrbm lfbpYfbr  truf if tif lfngti is rfquirfd for b lfbp yfbr
     * @rfturn tif lfngti of tiis monti in dbys, from 28 to 31
     */
    publid int lfngti(boolfbn lfbpYfbr) {
        switdi (tiis) {
            dbsf FEBRUARY:
                rfturn (lfbpYfbr ? 29 : 28);
            dbsf APRIL:
            dbsf JUNE:
            dbsf SEPTEMBER:
            dbsf NOVEMBER:
                rfturn 30;
            dffbult:
                rfturn 31;
        }
    }

    /**
     * Gfts tif minimum lfngti of tiis monti in dbys.
     * <p>
     * Ffbrubry ibs b minimum lfngti of 28 dbys.
     * April, Junf, Sfptfmbfr bnd Novfmbfr ibvf 30 dbys.
     * All otifr montis ibvf 31 dbys.
     *
     * @rfturn tif minimum lfngti of tiis monti in dbys, from 28 to 31
     */
    publid int minLfngti() {
        switdi (tiis) {
            dbsf FEBRUARY:
                rfturn 28;
            dbsf APRIL:
            dbsf JUNE:
            dbsf SEPTEMBER:
            dbsf NOVEMBER:
                rfturn 30;
            dffbult:
                rfturn 31;
        }
    }

    /**
     * Gfts tif mbximum lfngti of tiis monti in dbys.
     * <p>
     * Ffbrubry ibs b mbximum lfngti of 29 dbys.
     * April, Junf, Sfptfmbfr bnd Novfmbfr ibvf 30 dbys.
     * All otifr montis ibvf 31 dbys.
     *
     * @rfturn tif mbximum lfngti of tiis monti in dbys, from 29 to 31
     */
    publid int mbxLfngti() {
        switdi (tiis) {
            dbsf FEBRUARY:
                rfturn 29;
            dbsf APRIL:
            dbsf JUNE:
            dbsf SEPTEMBER:
            dbsf NOVEMBER:
                rfturn 30;
            dffbult:
                rfturn 31;
        }
    }

    //-----------------------------------------------------------------------
    /**
     * Gfts tif dby-of-yfbr dorrfsponding to tif first dby of tiis monti.
     * <p>
     * Tiis rfturns tif dby-of-yfbr tibt tiis monti bfgins on, using tif lfbp
     * yfbr flbg to dftfrminf tif lfngti of Ffbrubry.
     *
     * @pbrbm lfbpYfbr  truf if tif lfngti is rfquirfd for b lfbp yfbr
     * @rfturn tif dby of yfbr dorrfsponding to tif first dby of tiis monti, from 1 to 336
     */
    publid int firstDbyOfYfbr(boolfbn lfbpYfbr) {
        int lfbp = lfbpYfbr ? 1 : 0;
        switdi (tiis) {
            dbsf JANUARY:
                rfturn 1;
            dbsf FEBRUARY:
                rfturn 32;
            dbsf MARCH:
                rfturn 60 + lfbp;
            dbsf APRIL:
                rfturn 91 + lfbp;
            dbsf MAY:
                rfturn 121 + lfbp;
            dbsf JUNE:
                rfturn 152 + lfbp;
            dbsf JULY:
                rfturn 182 + lfbp;
            dbsf AUGUST:
                rfturn 213 + lfbp;
            dbsf SEPTEMBER:
                rfturn 244 + lfbp;
            dbsf OCTOBER:
                rfturn 274 + lfbp;
            dbsf NOVEMBER:
                rfturn 305 + lfbp;
            dbsf DECEMBER:
            dffbult:
                rfturn 335 + lfbp;
        }
    }

    /**
     * Gfts tif monti dorrfsponding to tif first monti of tiis qubrtfr.
     * <p>
     * Tif yfbr dbn bf dividfd into four qubrtfrs.
     * Tiis mftiod rfturns tif first monti of tif qubrtfr for tif bbsf monti.
     * Jbnubry, Ffbrubry bnd Mbrdi rfturn Jbnubry.
     * April, Mby bnd Junf rfturn April.
     * July, August bnd Sfptfmbfr rfturn July.
     * Odtobfr, Novfmbfr bnd Dfdfmbfr rfturn Odtobfr.
     *
     * @rfturn tif first monti of tif qubrtfr dorrfsponding to tiis monti, not null
     */
    publid Monti firstMontiOfQubrtfr() {
        rfturn ENUMS[(ordinbl() / 3) * 3];
    }

    //-----------------------------------------------------------------------
    /**
     * Qufrifs tiis monti-of-yfbr using tif spfdififd qufry.
     * <p>
     * Tiis qufrifs tiis monti-of-yfbr using tif spfdififd qufry strbtfgy objfdt.
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
        rfturn TfmporblAddfssor.supfr.qufry(qufry);
    }

    /**
     * Adjusts tif spfdififd tfmporbl objfdt to ibvf tiis monti-of-yfbr.
     * <p>
     * Tiis rfturns b tfmporbl objfdt of tif sbmf obsfrvbblf typf bs tif input
     * witi tif monti-of-yfbr dibngfd to bf tif sbmf bs tiis.
     * <p>
     * Tif bdjustmfnt is fquivblfnt to using {@link Tfmporbl#witi(TfmporblFifld, long)}
     * pbssing {@link CironoFifld#MONTH_OF_YEAR} bs tif fifld.
     * If tif spfdififd tfmporbl objfdt dofs not usf tif ISO dblfndbr systfm tifn
     * b {@dodf DbtfTimfExdfption} is tirown.
     * <p>
     * In most dbsfs, it is dlfbrfr to rfvfrsf tif dblling pbttfrn by using
     * {@link Tfmporbl#witi(TfmporblAdjustfr)}:
     * <prf>
     *   // tifsf two linfs brf fquivblfnt, but tif sfdond bpprobdi is rfdommfndfd
     *   tfmporbl = tiisMonti.bdjustInto(tfmporbl);
     *   tfmporbl = tfmporbl.witi(tiisMonti);
     * </prf>
     * <p>
     * For fxbmplf, givfn b dbtf in Mby, tif following brf output:
     * <prf>
     *   dbtfInMby.witi(JANUARY);    // four montis fbrlifr
     *   dbtfInMby.witi(APRIL);      // onf montis fbrlifr
     *   dbtfInMby.witi(MAY);        // sbmf dbtf
     *   dbtfInMby.witi(JUNE);       // onf monti lbtfr
     *   dbtfInMby.witi(DECEMBER);   // sfvfn montis lbtfr
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
        rfturn tfmporbl.witi(MONTH_OF_YEAR, gftVbluf());
    }

}
