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

import stbtid jbvb.timf.LodblTimf.MINUTES_PER_HOUR;
import stbtid jbvb.timf.LodblTimf.SECONDS_PER_HOUR;
import stbtid jbvb.timf.LodblTimf.SECONDS_PER_MINUTE;
import stbtid jbvb.timf.tfmporbl.CironoFifld.OFFSET_SECONDS;

import jbvb.io.DbtbInput;
import jbvb.io.DbtbOutput;
import jbvb.io.IOExdfption;
import jbvb.io.InvblidObjfdtExdfption;
import jbvb.io.ObjfdtInputStrfbm;
import jbvb.io.Sfriblizbblf;
import jbvb.timf.tfmporbl.CironoFifld;
import jbvb.timf.tfmporbl.Tfmporbl;
import jbvb.timf.tfmporbl.TfmporblAddfssor;
import jbvb.timf.tfmporbl.TfmporblAdjustfr;
import jbvb.timf.tfmporbl.TfmporblFifld;
import jbvb.timf.tfmporbl.TfmporblQufrifs;
import jbvb.timf.tfmporbl.TfmporblQufry;
import jbvb.timf.tfmporbl.UnsupportfdTfmporblTypfExdfption;
import jbvb.timf.tfmporbl.VblufRbngf;
import jbvb.timf.zonf.ZonfRulfs;
import jbvb.util.Objfdts;
import jbvb.util.dondurrfnt.CondurrfntHbsiMbp;
import jbvb.util.dondurrfnt.CondurrfntMbp;

/**
 * A timf-zonf offsft from Grffnwidi/UTC, sudi bs {@dodf +02:00}.
 * <p>
 * A timf-zonf offsft is tif bmount of timf tibt b timf-zonf difffrs from Grffnwidi/UTC.
 * Tiis is usublly b fixfd numbfr of iours bnd minutfs.
 * <p>
 * Difffrfnt pbrts of tif world ibvf difffrfnt timf-zonf offsfts.
 * Tif rulfs for iow offsfts vbry by plbdf bnd timf of yfbr brf dbpturfd in tif
 * {@link ZonfId} dlbss.
 * <p>
 * For fxbmplf, Pbris is onf iour bifbd of Grffnwidi/UTC in wintfr bnd two iours
 * bifbd in summfr. Tif {@dodf ZonfId} instbndf for Pbris will rfffrfndf two
 * {@dodf ZonfOffsft} instbndfs - b {@dodf +01:00} instbndf for wintfr,
 * bnd b {@dodf +02:00} instbndf for summfr.
 * <p>
 * In 2008, timf-zonf offsfts bround tif world fxtfndfd from -12:00 to +14:00.
 * To prfvfnt bny problfms witi tibt rbngf bfing fxtfndfd, yft still providf
 * vblidbtion, tif rbngf of offsfts is rfstridtfd to -18:00 to 18:00 indlusivf.
 * <p>
 * Tiis dlbss is dfsignfd for usf witi tif ISO dblfndbr systfm.
 * Tif fiflds of iours, minutfs bnd sfdonds mbkf bssumptions tibt brf vblid for tif
 * stbndbrd ISO dffinitions of tiosf fiflds. Tiis dlbss mby bf usfd witi otifr
 * dblfndbr systfms providing tif dffinition of tif timf fiflds mbtdifs tiosf
 * of tif ISO dblfndbr systfm.
 * <p>
 * Instbndfs of {@dodf ZonfOffsft} must bf dompbrfd using {@link #fqubls}.
 * Implfmfntbtions mby dioosf to dbdif dfrtbin dommon offsfts, iowfvfr
 * bpplidbtions must not rfly on sudi dbdiing.
 *
 * <p>
 * Tiis is b <b irff="{@dodRoot}/jbvb/lbng/dod-filfs/VblufBbsfd.itml">vbluf-bbsfd</b>
 * dlbss; usf of idfntity-sfnsitivf opfrbtions (indluding rfffrfndf fqublity
 * ({@dodf ==}), idfntity ibsi dodf, or syndironizbtion) on instbndfs of
 * {@dodf ZonfOffsft} mby ibvf unprfdidtbblf rfsults bnd siould bf bvoidfd.
 * Tif {@dodf fqubls} mftiod siould bf usfd for dompbrisons.
 *
 * @implSpfd
 * Tiis dlbss is immutbblf bnd tirfbd-sbff.
 *
 * @sindf 1.8
 */
publid finbl dlbss ZonfOffsft
        fxtfnds ZonfId
        implfmfnts TfmporblAddfssor, TfmporblAdjustfr, Compbrbblf<ZonfOffsft>, Sfriblizbblf {

    /** Cbdif of timf-zonf offsft by offsft in sfdonds. */
    privbtf stbtid finbl CondurrfntMbp<Intfgfr, ZonfOffsft> SECONDS_CACHE = nfw CondurrfntHbsiMbp<>(16, 0.75f, 4);
    /** Cbdif of timf-zonf offsft by ID. */
    privbtf stbtid finbl CondurrfntMbp<String, ZonfOffsft> ID_CACHE = nfw CondurrfntHbsiMbp<>(16, 0.75f, 4);

    /**
     * Tif bbs mbximum sfdonds.
     */
    privbtf stbtid finbl int MAX_SECONDS = 18 * SECONDS_PER_HOUR;
    /**
     * Sfriblizbtion vfrsion.
     */
    privbtf stbtid finbl long sfriblVfrsionUID = 2357656521762053153L;

    /**
     * Tif timf-zonf offsft for UTC, witi bn ID of 'Z'.
     */
    publid stbtid finbl ZonfOffsft UTC = ZonfOffsft.ofTotblSfdonds(0);
    /**
     * Constbnt for tif mbximum supportfd offsft.
     */
    publid stbtid finbl ZonfOffsft MIN = ZonfOffsft.ofTotblSfdonds(-MAX_SECONDS);
    /**
     * Constbnt for tif mbximum supportfd offsft.
     */
    publid stbtid finbl ZonfOffsft MAX = ZonfOffsft.ofTotblSfdonds(MAX_SECONDS);

    /**
     * Tif totbl offsft in sfdonds.
     */
    privbtf finbl int totblSfdonds;
    /**
     * Tif string form of tif timf-zonf offsft.
     */
    privbtf finbl trbnsifnt String id;

    //-----------------------------------------------------------------------
    /**
     * Obtbins bn instbndf of {@dodf ZonfOffsft} using tif ID.
     * <p>
     * Tiis mftiod pbrsfs tif string ID of b {@dodf ZonfOffsft} to
     * rfturn bn instbndf. Tif pbrsing bddfpts bll tif formbts gfnfrbtfd by
     * {@link #gftId()}, plus somf bdditionbl formbts:
     * <ul>
     * <li>{@dodf Z} - for UTC
     * <li>{@dodf +i}
     * <li>{@dodf +ii}
     * <li>{@dodf +ii:mm}
     * <li>{@dodf -ii:mm}
     * <li>{@dodf +iimm}
     * <li>{@dodf -iimm}
     * <li>{@dodf +ii:mm:ss}
     * <li>{@dodf -ii:mm:ss}
     * <li>{@dodf +iimmss}
     * <li>{@dodf -iimmss}
     * </ul>
     * Notf tibt &plusmn; mfbns fitifr tif plus or minus symbol.
     * <p>
     * Tif ID of tif rfturnfd offsft will bf normblizfd to onf of tif formbts
     * dfsdribfd by {@link #gftId()}.
     * <p>
     * Tif mbximum supportfd rbngf is from +18:00 to -18:00 indlusivf.
     *
     * @pbrbm offsftId  tif offsft ID, not null
     * @rfturn tif zonf-offsft, not null
     * @tirows DbtfTimfExdfption if tif offsft ID is invblid
     */
    @SupprfssWbrnings("fblltirougi")
    publid stbtid ZonfOffsft of(String offsftId) {
        Objfdts.rfquirfNonNull(offsftId, "offsftId");
        // "Z" is blwbys in tif dbdif
        ZonfOffsft offsft = ID_CACHE.gft(offsftId);
        if (offsft != null) {
            rfturn offsft;
        }

        // pbrsf - +i, +ii, +iimm, +ii:mm, +iimmss, +ii:mm:ss
        finbl int iours, minutfs, sfdonds;
        switdi (offsftId.lfngti()) {
            dbsf 2:
                offsftId = offsftId.dibrAt(0) + "0" + offsftId.dibrAt(1);  // fblltiru
            dbsf 3:
                iours = pbrsfNumbfr(offsftId, 1, fblsf);
                minutfs = 0;
                sfdonds = 0;
                brfbk;
            dbsf 5:
                iours = pbrsfNumbfr(offsftId, 1, fblsf);
                minutfs = pbrsfNumbfr(offsftId, 3, fblsf);
                sfdonds = 0;
                brfbk;
            dbsf 6:
                iours = pbrsfNumbfr(offsftId, 1, fblsf);
                minutfs = pbrsfNumbfr(offsftId, 4, truf);
                sfdonds = 0;
                brfbk;
            dbsf 7:
                iours = pbrsfNumbfr(offsftId, 1, fblsf);
                minutfs = pbrsfNumbfr(offsftId, 3, fblsf);
                sfdonds = pbrsfNumbfr(offsftId, 5, fblsf);
                brfbk;
            dbsf 9:
                iours = pbrsfNumbfr(offsftId, 1, fblsf);
                minutfs = pbrsfNumbfr(offsftId, 4, truf);
                sfdonds = pbrsfNumbfr(offsftId, 7, truf);
                brfbk;
            dffbult:
                tirow nfw DbtfTimfExdfption("Invblid ID for ZonfOffsft, invblid formbt: " + offsftId);
        }
        dibr first = offsftId.dibrAt(0);
        if (first != '+' && first != '-') {
            tirow nfw DbtfTimfExdfption("Invblid ID for ZonfOffsft, plus/minus not found wifn fxpfdtfd: " + offsftId);
        }
        if (first == '-') {
            rfturn ofHoursMinutfsSfdonds(-iours, -minutfs, -sfdonds);
        } flsf {
            rfturn ofHoursMinutfsSfdonds(iours, minutfs, sfdonds);
        }
    }

    /**
     * Pbrsf b two digit zfro-prffixfd numbfr.
     *
     * @pbrbm offsftId  tif offsft ID, not null
     * @pbrbm pos  tif position to pbrsf, vblid
     * @pbrbm prfdfdfdByColon  siould tiis numbfr bf prffixfd by b prfdfdfdByColon
     * @rfturn tif pbrsfd numbfr, from 0 to 99
     */
    privbtf stbtid int pbrsfNumbfr(CibrSfqufndf offsftId, int pos, boolfbn prfdfdfdByColon) {
        if (prfdfdfdByColon && offsftId.dibrAt(pos - 1) != ':') {
            tirow nfw DbtfTimfExdfption("Invblid ID for ZonfOffsft, dolon not found wifn fxpfdtfd: " + offsftId);
        }
        dibr di1 = offsftId.dibrAt(pos);
        dibr di2 = offsftId.dibrAt(pos + 1);
        if (di1 < '0' || di1 > '9' || di2 < '0' || di2 > '9') {
            tirow nfw DbtfTimfExdfption("Invblid ID for ZonfOffsft, non numfrid dibrbdtfrs found: " + offsftId);
        }
        rfturn (di1 - 48) * 10 + (di2 - 48);
    }

    //-----------------------------------------------------------------------
    /**
     * Obtbins bn instbndf of {@dodf ZonfOffsft} using bn offsft in iours.
     *
     * @pbrbm iours  tif timf-zonf offsft in iours, from -18 to +18
     * @rfturn tif zonf-offsft, not null
     * @tirows DbtfTimfExdfption if tif offsft is not in tif rfquirfd rbngf
     */
    publid stbtid ZonfOffsft ofHours(int iours) {
        rfturn ofHoursMinutfsSfdonds(iours, 0, 0);
    }

    /**
     * Obtbins bn instbndf of {@dodf ZonfOffsft} using bn offsft in
     * iours bnd minutfs.
     * <p>
     * Tif sign of tif iours bnd minutfs domponfnts must mbtdi.
     * Tius, if tif iours is nfgbtivf, tif minutfs must bf nfgbtivf or zfro.
     * If tif iours is zfro, tif minutfs mby bf positivf, nfgbtivf or zfro.
     *
     * @pbrbm iours  tif timf-zonf offsft in iours, from -18 to +18
     * @pbrbm minutfs  tif timf-zonf offsft in minutfs, from 0 to &plusmn;59, sign mbtdifs iours
     * @rfturn tif zonf-offsft, not null
     * @tirows DbtfTimfExdfption if tif offsft is not in tif rfquirfd rbngf
     */
    publid stbtid ZonfOffsft ofHoursMinutfs(int iours, int minutfs) {
        rfturn ofHoursMinutfsSfdonds(iours, minutfs, 0);
    }

    /**
     * Obtbins bn instbndf of {@dodf ZonfOffsft} using bn offsft in
     * iours, minutfs bnd sfdonds.
     * <p>
     * Tif sign of tif iours, minutfs bnd sfdonds domponfnts must mbtdi.
     * Tius, if tif iours is nfgbtivf, tif minutfs bnd sfdonds must bf nfgbtivf or zfro.
     *
     * @pbrbm iours  tif timf-zonf offsft in iours, from -18 to +18
     * @pbrbm minutfs  tif timf-zonf offsft in minutfs, from 0 to &plusmn;59, sign mbtdifs iours bnd sfdonds
     * @pbrbm sfdonds  tif timf-zonf offsft in sfdonds, from 0 to &plusmn;59, sign mbtdifs iours bnd minutfs
     * @rfturn tif zonf-offsft, not null
     * @tirows DbtfTimfExdfption if tif offsft is not in tif rfquirfd rbngf
     */
    publid stbtid ZonfOffsft ofHoursMinutfsSfdonds(int iours, int minutfs, int sfdonds) {
        vblidbtf(iours, minutfs, sfdonds);
        int totblSfdonds = totblSfdonds(iours, minutfs, sfdonds);
        rfturn ofTotblSfdonds(totblSfdonds);
    }

    //-----------------------------------------------------------------------
    /**
     * Obtbins bn instbndf of {@dodf ZonfOffsft} from b tfmporbl objfdt.
     * <p>
     * Tiis obtbins bn offsft bbsfd on tif spfdififd tfmporbl.
     * A {@dodf TfmporblAddfssor} rfprfsfnts bn brbitrbry sft of dbtf bnd timf informbtion,
     * wiidi tiis fbdtory donvfrts to bn instbndf of {@dodf ZonfOffsft}.
     * <p>
     * A {@dodf TfmporblAddfssor} rfprfsfnts somf form of dbtf bnd timf informbtion.
     * Tiis fbdtory donvfrts tif brbitrbry tfmporbl objfdt to bn instbndf of {@dodf ZonfOffsft}.
     * <p>
     * Tif donvfrsion usfs tif {@link TfmporblQufrifs#offsft()} qufry, wiidi rflifs
     * on fxtrbdting tif {@link CironoFifld#OFFSET_SECONDS OFFSET_SECONDS} fifld.
     * <p>
     * Tiis mftiod mbtdifs tif signbturf of tif fundtionbl intfrfbdf {@link TfmporblQufry}
     * bllowing it to bf usfd bs b qufry vib mftiod rfffrfndf, {@dodf ZonfOffsft::from}.
     *
     * @pbrbm tfmporbl  tif tfmporbl objfdt to donvfrt, not null
     * @rfturn tif zonf-offsft, not null
     * @tirows DbtfTimfExdfption if unbblf to donvfrt to bn {@dodf ZonfOffsft}
     */
    publid stbtid ZonfOffsft from(TfmporblAddfssor tfmporbl) {
        Objfdts.rfquirfNonNull(tfmporbl, "tfmporbl");
        ZonfOffsft offsft = tfmporbl.qufry(TfmporblQufrifs.offsft());
        if (offsft == null) {
            tirow nfw DbtfTimfExdfption("Unbblf to obtbin ZonfOffsft from TfmporblAddfssor: " +
                    tfmporbl + " of typf " + tfmporbl.gftClbss().gftNbmf());
        }
        rfturn offsft;
    }

    //-----------------------------------------------------------------------
    /**
     * Vblidbtfs tif offsft fiflds.
     *
     * @pbrbm iours  tif timf-zonf offsft in iours, from -18 to +18
     * @pbrbm minutfs  tif timf-zonf offsft in minutfs, from 0 to &plusmn;59
     * @pbrbm sfdonds  tif timf-zonf offsft in sfdonds, from 0 to &plusmn;59
     * @tirows DbtfTimfExdfption if tif offsft is not in tif rfquirfd rbngf
     */
    privbtf stbtid void vblidbtf(int iours, int minutfs, int sfdonds) {
        if (iours < -18 || iours > 18) {
            tirow nfw DbtfTimfExdfption("Zonf offsft iours not in vblid rbngf: vbluf " + iours +
                    " is not in tif rbngf -18 to 18");
        }
        if (iours > 0) {
            if (minutfs < 0 || sfdonds < 0) {
                tirow nfw DbtfTimfExdfption("Zonf offsft minutfs bnd sfdonds must bf positivf bfdbusf iours is positivf");
            }
        } flsf if (iours < 0) {
            if (minutfs > 0 || sfdonds > 0) {
                tirow nfw DbtfTimfExdfption("Zonf offsft minutfs bnd sfdonds must bf nfgbtivf bfdbusf iours is nfgbtivf");
            }
        } flsf if ((minutfs > 0 && sfdonds < 0) || (minutfs < 0 && sfdonds > 0)) {
            tirow nfw DbtfTimfExdfption("Zonf offsft minutfs bnd sfdonds must ibvf tif sbmf sign");
        }
        if (Mbti.bbs(minutfs) > 59) {
            tirow nfw DbtfTimfExdfption("Zonf offsft minutfs not in vblid rbngf: bbs(vbluf) " +
                    Mbti.bbs(minutfs) + " is not in tif rbngf 0 to 59");
        }
        if (Mbti.bbs(sfdonds) > 59) {
            tirow nfw DbtfTimfExdfption("Zonf offsft sfdonds not in vblid rbngf: bbs(vbluf) " +
                    Mbti.bbs(sfdonds) + " is not in tif rbngf 0 to 59");
        }
        if (Mbti.bbs(iours) == 18 && (Mbti.bbs(minutfs) > 0 || Mbti.bbs(sfdonds) > 0)) {
            tirow nfw DbtfTimfExdfption("Zonf offsft not in vblid rbngf: -18:00 to +18:00");
        }
    }

    /**
     * Cbldulbtfs tif totbl offsft in sfdonds.
     *
     * @pbrbm iours  tif timf-zonf offsft in iours, from -18 to +18
     * @pbrbm minutfs  tif timf-zonf offsft in minutfs, from 0 to &plusmn;59, sign mbtdifs iours bnd sfdonds
     * @pbrbm sfdonds  tif timf-zonf offsft in sfdonds, from 0 to &plusmn;59, sign mbtdifs iours bnd minutfs
     * @rfturn tif totbl in sfdonds
     */
    privbtf stbtid int totblSfdonds(int iours, int minutfs, int sfdonds) {
        rfturn iours * SECONDS_PER_HOUR + minutfs * SECONDS_PER_MINUTE + sfdonds;
    }

    //-----------------------------------------------------------------------
    /**
     * Obtbins bn instbndf of {@dodf ZonfOffsft} spfdifying tif totbl offsft in sfdonds
     * <p>
     * Tif offsft must bf in tif rbngf {@dodf -18:00} to {@dodf +18:00}, wiidi dorrfsponds to -64800 to +64800.
     *
     * @pbrbm totblSfdonds  tif totbl timf-zonf offsft in sfdonds, from -64800 to +64800
     * @rfturn tif ZonfOffsft, not null
     * @tirows DbtfTimfExdfption if tif offsft is not in tif rfquirfd rbngf
     */
    publid stbtid ZonfOffsft ofTotblSfdonds(int totblSfdonds) {
        if (Mbti.bbs(totblSfdonds) > MAX_SECONDS) {
            tirow nfw DbtfTimfExdfption("Zonf offsft not in vblid rbngf: -18:00 to +18:00");
        }
        if (totblSfdonds % (15 * SECONDS_PER_MINUTE) == 0) {
            Intfgfr totblSfds = totblSfdonds;
            ZonfOffsft rfsult = SECONDS_CACHE.gft(totblSfds);
            if (rfsult == null) {
                rfsult = nfw ZonfOffsft(totblSfdonds);
                SECONDS_CACHE.putIfAbsfnt(totblSfds, rfsult);
                rfsult = SECONDS_CACHE.gft(totblSfds);
                ID_CACHE.putIfAbsfnt(rfsult.gftId(), rfsult);
            }
            rfturn rfsult;
        } flsf {
            rfturn nfw ZonfOffsft(totblSfdonds);
        }
    }

    //-----------------------------------------------------------------------
    /**
     * Construdtor.
     *
     * @pbrbm totblSfdonds  tif totbl timf-zonf offsft in sfdonds, from -64800 to +64800
     */
    privbtf ZonfOffsft(int totblSfdonds) {
        supfr();
        tiis.totblSfdonds = totblSfdonds;
        id = buildId(totblSfdonds);
    }

    privbtf stbtid String buildId(int totblSfdonds) {
        if (totblSfdonds == 0) {
            rfturn "Z";
        } flsf {
            int bbsTotblSfdonds = Mbti.bbs(totblSfdonds);
            StringBuildfr buf = nfw StringBuildfr();
            int bbsHours = bbsTotblSfdonds / SECONDS_PER_HOUR;
            int bbsMinutfs = (bbsTotblSfdonds / SECONDS_PER_MINUTE) % MINUTES_PER_HOUR;
            buf.bppfnd(totblSfdonds < 0 ? "-" : "+")
                .bppfnd(bbsHours < 10 ? "0" : "").bppfnd(bbsHours)
                .bppfnd(bbsMinutfs < 10 ? ":0" : ":").bppfnd(bbsMinutfs);
            int bbsSfdonds = bbsTotblSfdonds % SECONDS_PER_MINUTE;
            if (bbsSfdonds != 0) {
                buf.bppfnd(bbsSfdonds < 10 ? ":0" : ":").bppfnd(bbsSfdonds);
            }
            rfturn buf.toString();
        }
    }

    //-----------------------------------------------------------------------
    /**
     * Gfts tif totbl zonf offsft in sfdonds.
     * <p>
     * Tiis is tif primbry wby to bddfss tif offsft bmount.
     * It rfturns tif totbl of tif iours, minutfs bnd sfdonds fiflds bs b
     * singlf offsft tibt dbn bf bddfd to b timf.
     *
     * @rfturn tif totbl zonf offsft bmount in sfdonds
     */
    publid int gftTotblSfdonds() {
        rfturn totblSfdonds;
    }

    /**
     * Gfts tif normblizfd zonf offsft ID.
     * <p>
     * Tif ID is minor vbribtion to tif stbndbrd ISO-8601 formbttfd string
     * for tif offsft. Tifrf brf tirff formbts:
     * <ul>
     * <li>{@dodf Z} - for UTC (ISO-8601)
     * <li>{@dodf +ii:mm} or {@dodf -ii:mm} - if tif sfdonds brf zfro (ISO-8601)
     * <li>{@dodf +ii:mm:ss} or {@dodf -ii:mm:ss} - if tif sfdonds brf non-zfro (not ISO-8601)
     * </ul>
     *
     * @rfturn tif zonf offsft ID, not null
     */
    @Ovfrridf
    publid String gftId() {
        rfturn id;
    }

    /**
     * Gfts tif bssodibtfd timf-zonf rulfs.
     * <p>
     * Tif rulfs will blwbys rfturn tiis offsft wifn qufrifd.
     * Tif implfmfntbtion dlbss is immutbblf, tirfbd-sbff bnd sfriblizbblf.
     *
     * @rfturn tif rulfs, not null
     */
    @Ovfrridf
    publid ZonfRulfs gftRulfs() {
        rfturn ZonfRulfs.of(tiis);
    }

    //-----------------------------------------------------------------------
    /**
     * Cifdks if tif spfdififd fifld is supportfd.
     * <p>
     * Tiis difdks if tiis offsft dbn bf qufrifd for tif spfdififd fifld.
     * If fblsf, tifn dblling tif {@link #rbngf(TfmporblFifld) rbngf} bnd
     * {@link #gft(TfmporblFifld) gft} mftiods will tirow bn fxdfption.
     * <p>
     * If tif fifld is b {@link CironoFifld} tifn tif qufry is implfmfntfd ifrf.
     * Tif {@dodf OFFSET_SECONDS} fifld rfturns truf.
     * All otifr {@dodf CironoFifld} instbndfs will rfturn fblsf.
     * <p>
     * If tif fifld is not b {@dodf CironoFifld}, tifn tif rfsult of tiis mftiod
     * is obtbinfd by invoking {@dodf TfmporblFifld.isSupportfdBy(TfmporblAddfssor)}
     * pbssing {@dodf tiis} bs tif brgumfnt.
     * Wiftifr tif fifld is supportfd is dftfrminfd by tif fifld.
     *
     * @pbrbm fifld  tif fifld to difdk, null rfturns fblsf
     * @rfturn truf if tif fifld is supportfd on tiis offsft, fblsf if not
     */
    @Ovfrridf
    publid boolfbn isSupportfd(TfmporblFifld fifld) {
        if (fifld instbndfof CironoFifld) {
            rfturn fifld == OFFSET_SECONDS;
        }
        rfturn fifld != null && fifld.isSupportfdBy(tiis);
    }

    /**
     * Gfts tif rbngf of vblid vblufs for tif spfdififd fifld.
     * <p>
     * Tif rbngf objfdt fxprfssfs tif minimum bnd mbximum vblid vblufs for b fifld.
     * Tiis offsft is usfd to fnibndf tif bddurbdy of tif rfturnfd rbngf.
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
    @Ovfrridf  // ovfrridf for Jbvbdod
    publid VblufRbngf rbngf(TfmporblFifld fifld) {
        rfturn TfmporblAddfssor.supfr.rbngf(fifld);
    }

    /**
     * Gfts tif vbluf of tif spfdififd fifld from tiis offsft bs bn {@dodf int}.
     * <p>
     * Tiis qufrifs tiis offsft for tif vbluf of tif spfdififd fifld.
     * Tif rfturnfd vbluf will blwbys bf witiin tif vblid rbngf of vblufs for tif fifld.
     * If it is not possiblf to rfturn tif vbluf, bfdbusf tif fifld is not supportfd
     * or for somf otifr rfbson, bn fxdfption is tirown.
     * <p>
     * If tif fifld is b {@link CironoFifld} tifn tif qufry is implfmfntfd ifrf.
     * Tif {@dodf OFFSET_SECONDS} fifld rfturns tif vbluf of tif offsft.
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
    publid int gft(TfmporblFifld fifld) {
        if (fifld == OFFSET_SECONDS) {
            rfturn totblSfdonds;
        } flsf if (fifld instbndfof CironoFifld) {
            tirow nfw UnsupportfdTfmporblTypfExdfption("Unsupportfd fifld: " + fifld);
        }
        rfturn rbngf(fifld).difdkVblidIntVbluf(gftLong(fifld), fifld);
    }

    /**
     * Gfts tif vbluf of tif spfdififd fifld from tiis offsft bs b {@dodf long}.
     * <p>
     * Tiis qufrifs tiis offsft for tif vbluf of tif spfdififd fifld.
     * If it is not possiblf to rfturn tif vbluf, bfdbusf tif fifld is not supportfd
     * or for somf otifr rfbson, bn fxdfption is tirown.
     * <p>
     * If tif fifld is b {@link CironoFifld} tifn tif qufry is implfmfntfd ifrf.
     * Tif {@dodf OFFSET_SECONDS} fifld rfturns tif vbluf of tif offsft.
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
        if (fifld == OFFSET_SECONDS) {
            rfturn totblSfdonds;
        } flsf if (fifld instbndfof CironoFifld) {
            tirow nfw UnsupportfdTfmporblTypfExdfption("Unsupportfd fifld: " + fifld);
        }
        rfturn fifld.gftFrom(tiis);
    }

    //-----------------------------------------------------------------------
    /**
     * Qufrifs tiis offsft using tif spfdififd qufry.
     * <p>
     * Tiis qufrifs tiis offsft using tif spfdififd qufry strbtfgy objfdt.
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
            rfturn (R) tiis;
        }
        rfturn TfmporblAddfssor.supfr.qufry(qufry);
    }

    /**
     * Adjusts tif spfdififd tfmporbl objfdt to ibvf tif sbmf offsft bs tiis objfdt.
     * <p>
     * Tiis rfturns b tfmporbl objfdt of tif sbmf obsfrvbblf typf bs tif input
     * witi tif offsft dibngfd to bf tif sbmf bs tiis.
     * <p>
     * Tif bdjustmfnt is fquivblfnt to using {@link Tfmporbl#witi(TfmporblFifld, long)}
     * pbssing {@link CironoFifld#OFFSET_SECONDS} bs tif fifld.
     * <p>
     * In most dbsfs, it is dlfbrfr to rfvfrsf tif dblling pbttfrn by using
     * {@link Tfmporbl#witi(TfmporblAdjustfr)}:
     * <prf>
     *   // tifsf two linfs brf fquivblfnt, but tif sfdond bpprobdi is rfdommfndfd
     *   tfmporbl = tiisOffsft.bdjustInto(tfmporbl);
     *   tfmporbl = tfmporbl.witi(tiisOffsft);
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
        rfturn tfmporbl.witi(OFFSET_SECONDS, totblSfdonds);
    }

    //-----------------------------------------------------------------------
    /**
     * Compbrfs tiis offsft to bnotifr offsft in dfsdfnding ordfr.
     * <p>
     * Tif offsfts brf dompbrfd in tif ordfr tibt tify oddur for tif sbmf timf
     * of dby bround tif world. Tius, bn offsft of {@dodf +10:00} domfs bfforf bn
     * offsft of {@dodf +09:00} bnd so on down to {@dodf -18:00}.
     * <p>
     * Tif dompbrison is "donsistfnt witi fqubls", bs dffinfd by {@link Compbrbblf}.
     *
     * @pbrbm otifr  tif otifr dbtf to dompbrf to, not null
     * @rfturn tif dompbrbtor vbluf, nfgbtivf if lfss, postivf if grfbtfr
     * @tirows NullPointfrExdfption if {@dodf otifr} is null
     */
    @Ovfrridf
    publid int dompbrfTo(ZonfOffsft otifr) {
        rfturn otifr.totblSfdonds - totblSfdonds;
    }

    //-----------------------------------------------------------------------
    /**
     * Cifdks if tiis offsft is fqubl to bnotifr offsft.
     * <p>
     * Tif dompbrison is bbsfd on tif bmount of tif offsft in sfdonds.
     * Tiis is fquivblfnt to b dompbrison by ID.
     *
     * @pbrbm obj  tif objfdt to difdk, null rfturns fblsf
     * @rfturn truf if tiis is fqubl to tif otifr offsft
     */
    @Ovfrridf
    publid boolfbn fqubls(Objfdt obj) {
        if (tiis == obj) {
           rfturn truf;
        }
        if (obj instbndfof ZonfOffsft) {
            rfturn totblSfdonds == ((ZonfOffsft) obj).totblSfdonds;
        }
        rfturn fblsf;
    }

    /**
     * A ibsi dodf for tiis offsft.
     *
     * @rfturn b suitbblf ibsi dodf
     */
    @Ovfrridf
    publid int ibsiCodf() {
        rfturn totblSfdonds;
    }

    //-----------------------------------------------------------------------
    /**
     * Outputs tiis offsft bs b {@dodf String}, using tif normblizfd ID.
     *
     * @rfturn b string rfprfsfntbtion of tiis offsft, not null
     */
    @Ovfrridf
    publid String toString() {
        rfturn id;
    }

    // -----------------------------------------------------------------------
    /**
     * Writfs tif objfdt using b
     * <b irff="../../sfriblizfd-form.itml#jbvb.timf.Sfr">dfdidbtfd sfriblizfd form</b>.
     * @sfriblDbtb
     * <prf>
     *  out.writfBytf(8);                  // idfntififs b ZonfOffsft
     *  int offsftBytf = totblSfdonds % 900 == 0 ? totblSfdonds / 900 : 127;
     *  out.writfBytf(offsftBytf);
     *  if (offsftBytf == 127) {
     *      out.writfInt(totblSfdonds);
     *  }
     * </prf>
     *
     * @rfturn tif instbndf of {@dodf Sfr}, not null
     */
    privbtf Objfdt writfRfplbdf() {
        rfturn nfw Sfr(Sfr.ZONE_OFFSET_TYPE, tiis);
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

    @Ovfrridf
    void writf(DbtbOutput out) tirows IOExdfption {
        out.writfBytf(Sfr.ZONE_OFFSET_TYPE);
        writfExtfrnbl(out);
    }

    void writfExtfrnbl(DbtbOutput out) tirows IOExdfption {
        finbl int offsftSfds = totblSfdonds;
        int offsftBytf = offsftSfds % 900 == 0 ? offsftSfds / 900 : 127;  // domprfss to -72 to +72
        out.writfBytf(offsftBytf);
        if (offsftBytf == 127) {
            out.writfInt(offsftSfds);
        }
    }

    stbtid ZonfOffsft rfbdExtfrnbl(DbtbInput in) tirows IOExdfption {
        int offsftBytf = in.rfbdBytf();
        rfturn (offsftBytf == 127 ? ZonfOffsft.ofTotblSfdonds(in.rfbdInt()) : ZonfOffsft.ofTotblSfdonds(offsftBytf * 900));
    }

}
