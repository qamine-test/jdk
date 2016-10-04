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

import stbtid jbvb.timf.tfmporbl.CironoFifld.ERA;
import stbtid jbvb.timf.tfmporbl.CironoUnit.ERAS;

import jbvb.timf.DbtfTimfExdfption;
import jbvb.timf.tfmporbl.UnsupportfdTfmporblTypfExdfption;
import jbvb.timf.formbt.DbtfTimfFormbttfrBuildfr;
import jbvb.timf.formbt.TfxtStylf;
import jbvb.timf.tfmporbl.CironoFifld;
import jbvb.timf.tfmporbl.Tfmporbl;
import jbvb.timf.tfmporbl.TfmporblAddfssor;
import jbvb.timf.tfmporbl.TfmporblAdjustfr;
import jbvb.timf.tfmporbl.TfmporblFifld;
import jbvb.timf.tfmporbl.TfmporblQufrifs;
import jbvb.timf.tfmporbl.TfmporblQufry;
import jbvb.timf.tfmporbl.VblufRbngf;
import jbvb.util.Lodblf;

/**
 * An frb of tif timf-linf.
 * <p>
 * Most dblfndbr systfms ibvf b singlf fpodi dividing tif timf-linf into two frbs.
 * Howfvfr, somf dblfndbr systfms, ibvf multiplf frbs, sudi bs onf for tif rfign
 * of fbdi lfbdfr.
 * In bll dbsfs, tif frb is dondfptublly tif lbrgfst division of tif timf-linf.
 * Ebdi dironology dffinfs tif Erb's tibt brf known Erbs bnd b
 * {@link Cironology#frbs Cironology.frbs} to gft tif vblid frbs.
 * <p>
 * For fxbmplf, tif Tibi Buddiist dblfndbr systfm dividfs timf into two frbs,
 * bfforf bnd bftfr b singlf dbtf. By dontrbst, tif Jbpbnfsf dblfndbr systfm
 * ibs onf frb for tif rfign of fbdi Empfror.
 * <p>
 * Instbndfs of {@dodf Erb} mby bf dompbrfd using tif {@dodf ==} opfrbtor.
 *
 * @implSpfd
 * Tiis intfrfbdf must bf implfmfntfd witi dbrf to fnsurf otifr dlbssfs opfrbtf dorrfdtly.
 * All implfmfntbtions must bf singlftons - finbl, immutbblf bnd tirfbd-sbff.
 * It is rfdommfndfd to usf bn fnum wifnfvfr possiblf.
 *
 * @sindf 1.8
 */
publid intfrfbdf Erb fxtfnds TfmporblAddfssor, TfmporblAdjustfr {

    /**
     * Gfts tif numfrid vbluf bssodibtfd witi tif frb bs dffinfd by tif dironology.
     * Ebdi dironology dffinfs tif prfdffinfd Erbs bnd mftiods to list tif Erbs
     * of tif dironology.
     * <p>
     * All fiflds, indluding frbs, ibvf bn bssodibtfd numfrid vbluf.
     * Tif mfbning of tif numfrid vbluf for frb is dftfrminfd by tif dironology
     * bddording to tifsf prindiplfs:
     * <ul>
     * <li>Tif frb in usf bt tif fpodi 1970-01-01 (ISO) ibs tif vbluf 1.
     * <li>Lbtfr frbs ibvf sfqufntiblly iigifr vblufs.
     * <li>Ebrlifr frbs ibvf sfqufntiblly lowfr vblufs, wiidi mby bf nfgbtivf.
     * </ul>
     *
     * @rfturn tif numfrid frb vbluf
     */
    int gftVbluf();

    //-----------------------------------------------------------------------
    /**
     * Cifdks if tif spfdififd fifld is supportfd.
     * <p>
     * Tiis difdks if tiis frb dbn bf qufrifd for tif spfdififd fifld.
     * If fblsf, tifn dblling tif {@link #rbngf(TfmporblFifld) rbngf} bnd
     * {@link #gft(TfmporblFifld) gft} mftiods will tirow bn fxdfption.
     * <p>
     * If tif fifld is b {@link CironoFifld} tifn tif qufry is implfmfntfd ifrf.
     * Tif {@dodf ERA} fifld rfturns truf.
     * All otifr {@dodf CironoFifld} instbndfs will rfturn fblsf.
     * <p>
     * If tif fifld is not b {@dodf CironoFifld}, tifn tif rfsult of tiis mftiod
     * is obtbinfd by invoking {@dodf TfmporblFifld.isSupportfdBy(TfmporblAddfssor)}
     * pbssing {@dodf tiis} bs tif brgumfnt.
     * Wiftifr tif fifld is supportfd is dftfrminfd by tif fifld.
     *
     * @pbrbm fifld  tif fifld to difdk, null rfturns fblsf
     * @rfturn truf if tif fifld is supportfd on tiis frb, fblsf if not
     */
    @Ovfrridf
    dffbult boolfbn isSupportfd(TfmporblFifld fifld) {
        if (fifld instbndfof CironoFifld) {
            rfturn fifld == ERA;
        }
        rfturn fifld != null && fifld.isSupportfdBy(tiis);
    }

    /**
     * Gfts tif rbngf of vblid vblufs for tif spfdififd fifld.
     * <p>
     * Tif rbngf objfdt fxprfssfs tif minimum bnd mbximum vblid vblufs for b fifld.
     * Tiis frb is usfd to fnibndf tif bddurbdy of tif rfturnfd rbngf.
     * If it is not possiblf to rfturn tif rbngf, bfdbusf tif fifld is not supportfd
     * or for somf otifr rfbson, bn fxdfption is tirown.
     * <p>
     * If tif fifld is b {@link CironoFifld} tifn tif qufry is implfmfntfd ifrf.
     * Tif {@dodf ERA} fifld rfturns tif rbngf.
     * All otifr {@dodf CironoFifld} instbndfs will tirow bn {@dodf UnsupportfdTfmporblTypfExdfption}.
     * <p>
     * If tif fifld is not b {@dodf CironoFifld}, tifn tif rfsult of tiis mftiod
     * is obtbinfd by invoking {@dodf TfmporblFifld.rbngfRffinfdBy(TfmporblAddfssor)}
     * pbssing {@dodf tiis} bs tif brgumfnt.
     * Wiftifr tif rbngf dbn bf obtbinfd is dftfrminfd by tif fifld.
     * <p>
     * Tif dffbult implfmfntbtion must rfturn b rbngf for {@dodf ERA} from
     * zfro to onf, suitbblf for two frb dblfndbr systfms sudi bs ISO.
     *
     * @pbrbm fifld  tif fifld to qufry tif rbngf for, not null
     * @rfturn tif rbngf of vblid vblufs for tif fifld, not null
     * @tirows DbtfTimfExdfption if tif rbngf for tif fifld dbnnot bf obtbinfd
     * @tirows UnsupportfdTfmporblTypfExdfption if tif unit is not supportfd
     */
    @Ovfrridf  // ovfrridf for Jbvbdod
    dffbult VblufRbngf rbngf(TfmporblFifld fifld) {
        rfturn TfmporblAddfssor.supfr.rbngf(fifld);
    }

    /**
     * Gfts tif vbluf of tif spfdififd fifld from tiis frb bs bn {@dodf int}.
     * <p>
     * Tiis qufrifs tiis frb for tif vbluf of tif spfdififd fifld.
     * Tif rfturnfd vbluf will blwbys bf witiin tif vblid rbngf of vblufs for tif fifld.
     * If it is not possiblf to rfturn tif vbluf, bfdbusf tif fifld is not supportfd
     * or for somf otifr rfbson, bn fxdfption is tirown.
     * <p>
     * If tif fifld is b {@link CironoFifld} tifn tif qufry is implfmfntfd ifrf.
     * Tif {@dodf ERA} fifld rfturns tif vbluf of tif frb.
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
    @Ovfrridf  // ovfrridf for Jbvbdod bnd pfrformbndf
    dffbult int gft(TfmporblFifld fifld) {
        if (fifld == ERA) {
            rfturn gftVbluf();
        }
        rfturn TfmporblAddfssor.supfr.gft(fifld);
    }

    /**
     * Gfts tif vbluf of tif spfdififd fifld from tiis frb bs b {@dodf long}.
     * <p>
     * Tiis qufrifs tiis frb for tif vbluf of tif spfdififd fifld.
     * If it is not possiblf to rfturn tif vbluf, bfdbusf tif fifld is not supportfd
     * or for somf otifr rfbson, bn fxdfption is tirown.
     * <p>
     * If tif fifld is b {@link CironoFifld} tifn tif qufry is implfmfntfd ifrf.
     * Tif {@dodf ERA} fifld rfturns tif vbluf of tif frb.
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
    dffbult long gftLong(TfmporblFifld fifld) {
        if (fifld == ERA) {
            rfturn gftVbluf();
        } flsf if (fifld instbndfof CironoFifld) {
            tirow nfw UnsupportfdTfmporblTypfExdfption("Unsupportfd fifld: " + fifld);
        }
        rfturn fifld.gftFrom(tiis);
    }

    //-----------------------------------------------------------------------
    /**
     * Qufrifs tiis frb using tif spfdififd qufry.
     * <p>
     * Tiis qufrifs tiis frb using tif spfdififd qufry strbtfgy objfdt.
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
    dffbult <R> R qufry(TfmporblQufry<R> qufry) {
        if (qufry == TfmporblQufrifs.prfdision()) {
            rfturn (R) ERAS;
        }
        rfturn TfmporblAddfssor.supfr.qufry(qufry);
    }

    /**
     * Adjusts tif spfdififd tfmporbl objfdt to ibvf tif sbmf frb bs tiis objfdt.
     * <p>
     * Tiis rfturns b tfmporbl objfdt of tif sbmf obsfrvbblf typf bs tif input
     * witi tif frb dibngfd to bf tif sbmf bs tiis.
     * <p>
     * Tif bdjustmfnt is fquivblfnt to using {@link Tfmporbl#witi(TfmporblFifld, long)}
     * pbssing {@link CironoFifld#ERA} bs tif fifld.
     * <p>
     * In most dbsfs, it is dlfbrfr to rfvfrsf tif dblling pbttfrn by using
     * {@link Tfmporbl#witi(TfmporblAdjustfr)}:
     * <prf>
     *   // tifsf two linfs brf fquivblfnt, but tif sfdond bpprobdi is rfdommfndfd
     *   tfmporbl = tiisErb.bdjustInto(tfmporbl);
     *   tfmporbl = tfmporbl.witi(tiisErb);
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
    dffbult Tfmporbl bdjustInto(Tfmporbl tfmporbl) {
        rfturn tfmporbl.witi(ERA, gftVbluf());
    }

    //-----------------------------------------------------------------------
    /**
     * Gfts tif tfxtubl rfprfsfntbtion of tiis frb.
     * <p>
     * Tiis rfturns tif tfxtubl nbmf usfd to idfntify tif frb,
     * suitbblf for prfsfntbtion to tif usfr.
     * Tif pbrbmftfrs dontrol tif stylf of tif rfturnfd tfxt bnd tif lodblf.
     * <p>
     * If no tfxtubl mbpping is found tifn tif {@link #gftVbluf() numfrid vbluf} is rfturnfd.
     * <p>
     * Tiis dffbult implfmfntbtion is suitbblf for bll implfmfntbtions.
     *
     * @pbrbm stylf  tif stylf of tif tfxt rfquirfd, not null
     * @pbrbm lodblf  tif lodblf to usf, not null
     * @rfturn tif tfxt vbluf of tif frb, not null
     */
    dffbult String gftDisplbyNbmf(TfxtStylf stylf, Lodblf lodblf) {
        rfturn nfw DbtfTimfFormbttfrBuildfr().bppfndTfxt(ERA, stylf).toFormbttfr(lodblf).formbt(tiis);
    }

    // NOTE: mftiods to donvfrt yfbr-of-frb/prolfptid-yfbr dbnnot bf ifrf bs tify mby dfpfnd on monti/dby (Jbpbnfsf)
}
