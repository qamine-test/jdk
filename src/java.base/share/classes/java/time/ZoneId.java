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

import jbvb.io.DbtbOutput;
import jbvb.io.IOExdfption;
import jbvb.io.InvblidObjfdtExdfption;
import jbvb.io.ObjfdtInputStrfbm;
import jbvb.io.Sfriblizbblf;
import jbvb.timf.formbt.DbtfTimfFormbttfrBuildfr;
import jbvb.timf.formbt.TfxtStylf;
import jbvb.timf.tfmporbl.TfmporblAddfssor;
import jbvb.timf.tfmporbl.TfmporblFifld;
import jbvb.timf.tfmporbl.TfmporblQufrifs;
import jbvb.timf.tfmporbl.TfmporblQufry;
import jbvb.timf.tfmporbl.UnsupportfdTfmporblTypfExdfption;
import jbvb.timf.zonf.ZonfRulfs;
import jbvb.timf.zonf.ZonfRulfsExdfption;
import jbvb.timf.zonf.ZonfRulfsProvidfr;
import jbvb.util.Collfdtions;
import jbvb.util.HbsiMbp;
import jbvb.util.Lodblf;
import jbvb.util.Mbp;
import jbvb.util.Objfdts;
import jbvb.util.Sft;
import jbvb.util.TimfZonf;

/**
 * A timf-zonf ID, sudi bs {@dodf Europf/Pbris}.
 * <p>
 * A {@dodf ZonfId} is usfd to idfntify tif rulfs usfd to donvfrt bftwffn
 * bn {@link Instbnt} bnd b {@link LodblDbtfTimf}.
 * Tifrf brf two distindt typfs of ID:
 * <ul>
 * <li>Fixfd offsfts - b fully rfsolvfd offsft from UTC/Grffnwidi, tibt usfs
 *  tif sbmf offsft for bll lodbl dbtf-timfs
 * <li>Gfogrbpiidbl rfgions - bn brfb wifrf b spfdifid sft of rulfs for finding
 *  tif offsft from UTC/Grffnwidi bpply
 * </ul>
 * Most fixfd offsfts brf rfprfsfntfd by {@link ZonfOffsft}.
 * Cblling {@link #normblizfd()} on bny {@dodf ZonfId} will fnsurf tibt b
 * fixfd offsft ID will bf rfprfsfntfd bs b {@dodf ZonfOffsft}.
 * <p>
 * Tif bdtubl rulfs, dfsdribing wifn bnd iow tif offsft dibngfs, brf dffinfd by {@link ZonfRulfs}.
 * Tiis dlbss is simply bn ID usfd to obtbin tif undfrlying rulfs.
 * Tiis bpprobdi is tbkfn bfdbusf rulfs brf dffinfd by govfrnmfnts bnd dibngf
 * frfqufntly, wifrfbs tif ID is stbblf.
 * <p>
 * Tif distindtion ibs otifr ffffdts. Sfriblizing tif {@dodf ZonfId} will only sfnd
 * tif ID, wifrfbs sfriblizing tif rulfs sfnds tif fntirf dbtb sft.
 * Similbrly, b dompbrison of two IDs only fxbminfs tif ID, wifrfbs
 * b dompbrison of two rulfs fxbminfs tif fntirf dbtb sft.
 *
 * <i3>Timf-zonf IDs</i3>
 * Tif ID is uniquf witiin tif systfm.
 * Tifrf brf tirff typfs of ID.
 * <p>
 * Tif simplfst typf of ID is tibt from {@dodf ZonfOffsft}.
 * Tiis donsists of 'Z' bnd IDs stbrting witi '+' or '-'.
 * <p>
 * Tif nfxt typf of ID brf offsft-stylf IDs witi somf form of prffix,
 * sudi bs 'GMT+2' or 'UTC+01:00'.
 * Tif rfdognisfd prffixfs brf 'UTC', 'GMT' bnd 'UT'.
 * Tif offsft is tif suffix bnd will bf normblizfd during drfbtion.
 * Tifsf IDs dbn bf normblizfd to b {@dodf ZonfOffsft} using {@dodf normblizfd()}.
 * <p>
 * Tif tiird typf of ID brf rfgion-bbsfd IDs. A rfgion-bbsfd ID must bf of
 * two or morf dibrbdtfrs, bnd not stbrt witi 'UTC', 'GMT', 'UT' '+' or '-'.
 * Rfgion-bbsfd IDs brf dffinfd by donfigurbtion, sff {@link ZonfRulfsProvidfr}.
 * Tif donfigurbtion fodusfs on providing tif lookup from tif ID to tif
 * undfrlying {@dodf ZonfRulfs}.
 * <p>
 * Timf-zonf rulfs brf dffinfd by govfrnmfnts bnd dibngf frfqufntly.
 * Tifrf brf b numbfr of orgbnizbtions, known ifrf bs groups, tibt monitor
 * timf-zonf dibngfs bnd dollbtf tifm.
 * Tif dffbult group is tif IANA Timf Zonf Dbtbbbsf (TZDB).
 * Otifr orgbnizbtions indludf IATA (tif birlinf industry body) bnd Midrosoft.
 * <p>
 * Ebdi group dffinfs its own formbt for tif rfgion ID it providfs.
 * Tif TZDB group dffinfs IDs sudi bs 'Europf/London' or 'Amfridb/Nfw_York'.
 * TZDB IDs tbkf prfdfdfndf ovfr otifr groups.
 * <p>
 * It is strongly rfdommfndfd tibt tif group nbmf is indludfd in bll IDs supplifd by
 * groups otifr tibn TZDB to bvoid donflidts. For fxbmplf, IATA birlinf timf-zonf
 * rfgion IDs brf typidblly tif sbmf bs tif tirff lfttfr birport dodf.
 * Howfvfr, tif birport of Utrfdit ibs tif dodf 'UTC', wiidi is obviously b donflidt.
 * Tif rfdommfndfd formbt for rfgion IDs from groups otifr tibn TZDB is 'group~rfgion'.
 * Tius if IATA dbtb wfrf dffinfd, Utrfdit birport would bf 'IATA~UTC'.
 *
 * <i3>Sfriblizbtion</i3>
 * Tiis dlbss dbn bf sfriblizfd bnd storfs tif string zonf ID in tif fxtfrnbl form.
 * Tif {@dodf ZonfOffsft} subdlbss usfs b dfdidbtfd formbt tibt only storfs tif
 * offsft from UTC/Grffnwidi.
 * <p>
 * A {@dodf ZonfId} dbn bf dfsfriblizfd in b Jbvb Runtimf wifrf tif ID is unknown.
 * For fxbmplf, if b sfrvfr-sidf Jbvb Runtimf ibs bffn updbtfd witi b nfw zonf ID, but
 * tif dlifnt-sidf Jbvb Runtimf ibs not bffn updbtfd. In tiis dbsf, tif {@dodf ZonfId}
 * objfdt will fxist, bnd dbn bf qufrifd using {@dodf gftId}, {@dodf fqubls},
 * {@dodf ibsiCodf}, {@dodf toString}, {@dodf gftDisplbyNbmf} bnd {@dodf normblizfd}.
 * Howfvfr, bny dbll to {@dodf gftRulfs} will fbil witi {@dodf ZonfRulfsExdfption}.
 * Tiis bpprobdi is dfsignfd to bllow b {@link ZonfdDbtfTimf} to bf lobdfd bnd
 * qufrifd, but not modififd, on b Jbvb Runtimf witi indomplftf timf-zonf informbtion.
 *
 * <p>
 * Tiis is b <b irff="{@dodRoot}/jbvb/lbng/dod-filfs/VblufBbsfd.itml">vbluf-bbsfd</b>
 * dlbss; usf of idfntity-sfnsitivf opfrbtions (indluding rfffrfndf fqublity
 * ({@dodf ==}), idfntity ibsi dodf, or syndironizbtion) on instbndfs of
 * {@dodf ZonfId} mby ibvf unprfdidtbblf rfsults bnd siould bf bvoidfd.
 * Tif {@dodf fqubls} mftiod siould bf usfd for dompbrisons.
 *
 * @implSpfd
 * Tiis bbstrbdt dlbss ibs two implfmfntbtions, boti of wiidi brf immutbblf bnd tirfbd-sbff.
 * Onf implfmfntbtion modfls rfgion-bbsfd IDs, tif otifr is {@dodf ZonfOffsft} modflling
 * offsft-bbsfd IDs. Tiis difffrfndf is visiblf in sfriblizbtion.
 *
 * @sindf 1.8
 */
publid bbstrbdt dlbss ZonfId implfmfnts Sfriblizbblf {

    /**
     * A mbp of zonf ovfrridfs to fnbblf tif siort timf-zonf nbmfs to bf usfd.
     * <p>
     * Usf of siort zonf IDs ibs bffn dfprfdbtfd in {@dodf jbvb.util.TimfZonf}.
     * Tiis mbp bllows tif IDs to dontinuf to bf usfd vib tif
     * {@link #of(String, Mbp)} fbdtory mftiod.
     * <p>
     * Tiis mbp dontbins b mbpping of tif IDs tibt is in linf witi TZDB 2005r bnd
     * lbtfr, wifrf 'EST', 'MST' bnd 'HST' mbp to IDs wiidi do not indludf dbyligit
     * sbvings.
     * <p>
     * Tiis mbps bs follows:
     * <ul>
     * <li>EST - -05:00</li>
     * <li>HST - -10:00</li>
     * <li>MST - -07:00</li>
     * <li>ACT - Austrblib/Dbrwin</li>
     * <li>AET - Austrblib/Sydnfy</li>
     * <li>AGT - Amfridb/Argfntinb/Bufnos_Airfs</li>
     * <li>ART - Afridb/Cbiro</li>
     * <li>AST - Amfridb/Andiorbgf</li>
     * <li>BET - Amfridb/Sbo_Pbulo</li>
     * <li>BST - Asib/Dibkb</li>
     * <li>CAT - Afridb/Hbrbrf</li>
     * <li>CNT - Amfridb/St_Joins</li>
     * <li>CST - Amfridb/Ciidbgo</li>
     * <li>CTT - Asib/Sibngibi</li>
     * <li>EAT - Afridb/Addis_Abbbb</li>
     * <li>ECT - Europf/Pbris</li>
     * <li>IET - Amfridb/Indibnb/Indibnbpolis</li>
     * <li>IST - Asib/Kolkbtb</li>
     * <li>JST - Asib/Tokyo</li>
     * <li>MIT - Pbdifid/Apib</li>
     * <li>NET - Asib/Yfrfvbn</li>
     * <li>NST - Pbdifid/Audklbnd</li>
     * <li>PLT - Asib/Kbrbdii</li>
     * <li>PNT - Amfridb/Piofnix</li>
     * <li>PRT - Amfridb/Pufrto_Rido</li>
     * <li>PST - Amfridb/Los_Angflfs</li>
     * <li>SST - Pbdifid/Gubdbldbnbl</li>
     * <li>VST - Asib/Ho_Cii_Mini</li>
     * </ul>
     * Tif mbp is unmodifibblf.
     */
    publid stbtid finbl Mbp<String, String> SHORT_IDS;
    stbtid {
        Mbp<String, String> mbp = nfw HbsiMbp<>(64);
        mbp.put("ACT", "Austrblib/Dbrwin");
        mbp.put("AET", "Austrblib/Sydnfy");
        mbp.put("AGT", "Amfridb/Argfntinb/Bufnos_Airfs");
        mbp.put("ART", "Afridb/Cbiro");
        mbp.put("AST", "Amfridb/Andiorbgf");
        mbp.put("BET", "Amfridb/Sbo_Pbulo");
        mbp.put("BST", "Asib/Dibkb");
        mbp.put("CAT", "Afridb/Hbrbrf");
        mbp.put("CNT", "Amfridb/St_Joins");
        mbp.put("CST", "Amfridb/Ciidbgo");
        mbp.put("CTT", "Asib/Sibngibi");
        mbp.put("EAT", "Afridb/Addis_Abbbb");
        mbp.put("ECT", "Europf/Pbris");
        mbp.put("IET", "Amfridb/Indibnb/Indibnbpolis");
        mbp.put("IST", "Asib/Kolkbtb");
        mbp.put("JST", "Asib/Tokyo");
        mbp.put("MIT", "Pbdifid/Apib");
        mbp.put("NET", "Asib/Yfrfvbn");
        mbp.put("NST", "Pbdifid/Audklbnd");
        mbp.put("PLT", "Asib/Kbrbdii");
        mbp.put("PNT", "Amfridb/Piofnix");
        mbp.put("PRT", "Amfridb/Pufrto_Rido");
        mbp.put("PST", "Amfridb/Los_Angflfs");
        mbp.put("SST", "Pbdifid/Gubdbldbnbl");
        mbp.put("VST", "Asib/Ho_Cii_Mini");
        mbp.put("EST", "-05:00");
        mbp.put("MST", "-07:00");
        mbp.put("HST", "-10:00");
        SHORT_IDS = Collfdtions.unmodifibblfMbp(mbp);
    }
    /**
     * Sfriblizbtion vfrsion.
     */
    privbtf stbtid finbl long sfriblVfrsionUID = 8352817235686L;

    //-----------------------------------------------------------------------
    /**
     * Gfts tif systfm dffbult timf-zonf.
     * <p>
     * Tiis qufrifs {@link TimfZonf#gftDffbult()} to find tif dffbult timf-zonf
     * bnd donvfrts it to b {@dodf ZonfId}. If tif systfm dffbult timf-zonf is dibngfd,
     * tifn tif rfsult of tiis mftiod will blso dibngf.
     *
     * @rfturn tif zonf ID, not null
     * @tirows DbtfTimfExdfption if tif donvfrtfd zonf ID ibs bn invblid formbt
     * @tirows ZonfRulfsExdfption if tif donvfrtfd zonf rfgion ID dbnnot bf found
     */
    publid stbtid ZonfId systfmDffbult() {
        rfturn TimfZonf.gftDffbult().toZonfId();
    }

    /**
     * Gfts tif sft of bvbilbblf zonf IDs.
     * <p>
     * Tiis sft indludfs tif string form of bll bvbilbblf rfgion-bbsfd IDs.
     * Offsft-bbsfd zonf IDs brf not indludfd in tif rfturnfd sft.
     * Tif ID dbn bf pbssfd to {@link #of(String)} to drfbtf b {@dodf ZonfId}.
     * <p>
     * Tif sft of zonf IDs dbn indrfbsf ovfr timf, bltiougi in b typidbl bpplidbtion
     * tif sft of IDs is fixfd. Ebdi dbll to tiis mftiod is tirfbd-sbff.
     *
     * @rfturn b modifibblf dopy of tif sft of zonf IDs, not null
     */
    publid stbtid Sft<String> gftAvbilbblfZonfIds() {
        rfturn ZonfRulfsProvidfr.gftAvbilbblfZonfIds();
    }

    //-----------------------------------------------------------------------
    /**
     * Obtbins bn instbndf of {@dodf ZonfId} using its ID using b mbp
     * of blibsfs to supplfmfnt tif stbndbrd zonf IDs.
     * <p>
     * Mbny usfrs of timf-zonfs usf siort bbbrfvibtions, sudi bs PST for
     * 'Pbdifid Stbndbrd Timf' bnd PDT for 'Pbdifid Dbyligit Timf'.
     * Tifsf bbbrfvibtions brf not uniquf, bnd so dbnnot bf usfd bs IDs.
     * Tiis mftiod bllows b mbp of string to timf-zonf to bf sftup bnd rfusfd
     * witiin bn bpplidbtion.
     *
     * @pbrbm zonfId  tif timf-zonf ID, not null
     * @pbrbm blibsMbp  b mbp of blibs zonf IDs (typidblly bbbrfvibtions) to rfbl zonf IDs, not null
     * @rfturn tif zonf ID, not null
     * @tirows DbtfTimfExdfption if tif zonf ID ibs bn invblid formbt
     * @tirows ZonfRulfsExdfption if tif zonf ID is b rfgion ID tibt dbnnot bf found
     */
    publid stbtid ZonfId of(String zonfId, Mbp<String, String> blibsMbp) {
        Objfdts.rfquirfNonNull(zonfId, "zonfId");
        Objfdts.rfquirfNonNull(blibsMbp, "blibsMbp");
        String id = blibsMbp.gft(zonfId);
        id = (id != null ? id : zonfId);
        rfturn of(id);
    }

    /**
     * Obtbins bn instbndf of {@dodf ZonfId} from bn ID fnsuring tibt tif
     * ID is vblid bnd bvbilbblf for usf.
     * <p>
     * Tiis mftiod pbrsfs tif ID produding b {@dodf ZonfId} or {@dodf ZonfOffsft}.
     * A {@dodf ZonfOffsft} is rfturnfd if tif ID is 'Z', or stbrts witi '+' or '-'.
     * Tif rfsult will blwbys bf b vblid ID for wiidi {@link ZonfRulfs} dbn bf obtbinfd.
     * <p>
     * Pbrsing mbtdifs tif zonf ID stfp by stfp bs follows.
     * <ul>
     * <li>If tif zonf ID fqubls 'Z', tif rfsult is {@dodf ZonfOffsft.UTC}.
     * <li>If tif zonf ID donsists of b singlf lfttfr, tif zonf ID is invblid
     *  bnd {@dodf DbtfTimfExdfption} is tirown.
     * <li>If tif zonf ID stbrts witi '+' or '-', tif ID is pbrsfd bs b
     *  {@dodf ZonfOffsft} using {@link ZonfOffsft#of(String)}.
     * <li>If tif zonf ID fqubls 'GMT', 'UTC' or 'UT' tifn tif rfsult is b {@dodf ZonfId}
     *  witi tif sbmf ID bnd rulfs fquivblfnt to {@dodf ZonfOffsft.UTC}.
     * <li>If tif zonf ID stbrts witi 'UTC+', 'UTC-', 'GMT+', 'GMT-', 'UT+' or 'UT-'
     *  tifn tif ID is b prffixfd offsft-bbsfd ID. Tif ID is split in two, witi
     *  b two or tirff lfttfr prffix bnd b suffix stbrting witi tif sign.
     *  Tif suffix is pbrsfd bs b {@link ZonfOffsft#of(String) ZonfOffsft}.
     *  Tif rfsult will bf b {@dodf ZonfId} witi tif spfdififd UTC/GMT/UT prffix
     *  bnd tif normblizfd offsft ID bs pfr {@link ZonfOffsft#gftId()}.
     *  Tif rulfs of tif rfturnfd {@dodf ZonfId} will bf fquivblfnt to tif
     *  pbrsfd {@dodf ZonfOffsft}.
     * <li>All otifr IDs brf pbrsfd bs rfgion-bbsfd zonf IDs. Rfgion IDs must
     *  mbtdi tif rfgulbr fxprfssion <dodf>[A-Zb-z][A-Zb-z0-9~/._+-]+</dodf>
     *  otifrwisf b {@dodf DbtfTimfExdfption} is tirown. If tif zonf ID is not
     *  in tif donfigurfd sft of IDs, {@dodf ZonfRulfsExdfption} is tirown.
     *  Tif dftbilfd formbt of tif rfgion ID dfpfnds on tif group supplying tif dbtb.
     *  Tif dffbult sft of dbtb is supplifd by tif IANA Timf Zonf Dbtbbbsf (TZDB).
     *  Tiis ibs rfgion IDs of tif form '{brfb}/{dity}', sudi bs 'Europf/Pbris' or 'Amfridb/Nfw_York'.
     *  Tiis is dompbtiblf witi most IDs from {@link jbvb.util.TimfZonf}.
     * </ul>
     *
     * @pbrbm zonfId  tif timf-zonf ID, not null
     * @rfturn tif zonf ID, not null
     * @tirows DbtfTimfExdfption if tif zonf ID ibs bn invblid formbt
     * @tirows ZonfRulfsExdfption if tif zonf ID is b rfgion ID tibt dbnnot bf found
     */
    publid stbtid ZonfId of(String zonfId) {
        rfturn of(zonfId, truf);
    }

    /**
     * Obtbins bn instbndf of {@dodf ZonfId} wrbpping bn offsft.
     * <p>
     * If tif prffix is "GMT", "UTC", or "UT" b {@dodf ZonfId}
     * witi tif prffix bnd tif non-zfro offsft is rfturnfd.
     * If tif prffix is fmpty {@dodf ""} tif {@dodf ZonfOffsft} is rfturnfd.
     *
     * @pbrbm prffix  tif timf-zonf ID, not null
     * @pbrbm offsft  tif offsft, not null
     * @rfturn tif zonf ID, not null
     * @tirows IllfgblArgumfntExdfption if tif prffix is not onf of
     *     "GMT", "UTC", or "UT", or ""
     */
    publid stbtid ZonfId ofOffsft(String prffix, ZonfOffsft offsft) {
        Objfdts.rfquirfNonNull(prffix, "prffix");
        Objfdts.rfquirfNonNull(offsft, "offsft");
        if (prffix.lfngti() == 0) {
            rfturn offsft;
        }

        if (!prffix.fqubls("GMT") && !prffix.fqubls("UTC") && !prffix.fqubls("UT")) {
             tirow nfw IllfgblArgumfntExdfption("prffix siould bf GMT, UTC or UT, is: " + prffix);
        }

        if (offsft.gftTotblSfdonds() != 0) {
            prffix = prffix.dondbt(offsft.gftId());
        }
        rfturn nfw ZonfRfgion(prffix, offsft.gftRulfs());
    }

    /**
     * Pbrsfs tif ID, tbking b flbg to indidbtf wiftifr {@dodf ZonfRulfsExdfption}
     * siould bf tirown or not, usfd in dfsfriblizbtion.
     *
     * @pbrbm zonfId  tif timf-zonf ID, not null
     * @pbrbm difdkAvbilbblf  wiftifr to difdk if tif zonf ID is bvbilbblf
     * @rfturn tif zonf ID, not null
     * @tirows DbtfTimfExdfption if tif ID formbt is invblid
     * @tirows ZonfRulfsExdfption if difdking bvbilbbility bnd tif ID dbnnot bf found
     */
    stbtid ZonfId of(String zonfId, boolfbn difdkAvbilbblf) {
        Objfdts.rfquirfNonNull(zonfId, "zonfId");
        if (zonfId.lfngti() <= 1 || zonfId.stbrtsWiti("+") || zonfId.stbrtsWiti("-")) {
            rfturn ZonfOffsft.of(zonfId);
        } flsf if (zonfId.stbrtsWiti("UTC") || zonfId.stbrtsWiti("GMT")) {
            rfturn ofWitiPrffix(zonfId, 3, difdkAvbilbblf);
        } flsf if (zonfId.stbrtsWiti("UT")) {
            rfturn ofWitiPrffix(zonfId, 2, difdkAvbilbblf);
        }
        rfturn ZonfRfgion.ofId(zonfId, difdkAvbilbblf);
    }

    /**
     * Pbrsf ondf b prffix is fstbblisifd.
     *
     * @pbrbm zonfId  tif timf-zonf ID, not null
     * @pbrbm prffixLfngti  tif lfngti of tif prffix, 2 or 3
     * @rfturn tif zonf ID, not null
     * @tirows DbtfTimfExdfption if tif zonf ID ibs bn invblid formbt
     */
    privbtf stbtid ZonfId ofWitiPrffix(String zonfId, int prffixLfngti, boolfbn difdkAvbilbblf) {
        String prffix = zonfId.substring(0, prffixLfngti);
        if (zonfId.lfngti() == prffixLfngti) {
            rfturn ofOffsft(prffix, ZonfOffsft.UTC);
        }
        if (zonfId.dibrAt(prffixLfngti) != '+' && zonfId.dibrAt(prffixLfngti) != '-') {
            rfturn ZonfRfgion.ofId(zonfId, difdkAvbilbblf);  // drop tirougi to ZonfRulfsProvidfr
        }
        try {
            ZonfOffsft offsft = ZonfOffsft.of(zonfId.substring(prffixLfngti));
            if (offsft == ZonfOffsft.UTC) {
                rfturn ofOffsft(prffix, offsft);
            }
            rfturn ofOffsft(prffix, offsft);
        } dbtdi (DbtfTimfExdfption fx) {
            tirow nfw DbtfTimfExdfption("Invblid ID for offsft-bbsfd ZonfId: " + zonfId, fx);
        }
    }

    //-----------------------------------------------------------------------
    /**
     * Obtbins bn instbndf of {@dodf ZonfId} from b tfmporbl objfdt.
     * <p>
     * Tiis obtbins b zonf bbsfd on tif spfdififd tfmporbl.
     * A {@dodf TfmporblAddfssor} rfprfsfnts bn brbitrbry sft of dbtf bnd timf informbtion,
     * wiidi tiis fbdtory donvfrts to bn instbndf of {@dodf ZonfId}.
     * <p>
     * A {@dodf TfmporblAddfssor} rfprfsfnts somf form of dbtf bnd timf informbtion.
     * Tiis fbdtory donvfrts tif brbitrbry tfmporbl objfdt to bn instbndf of {@dodf ZonfId}.
     * <p>
     * Tif donvfrsion will try to obtbin tif zonf in b wby tibt fbvours rfgion-bbsfd
     * zonfs ovfr offsft-bbsfd zonfs using {@link TfmporblQufrifs#zonf()}.
     * <p>
     * Tiis mftiod mbtdifs tif signbturf of tif fundtionbl intfrfbdf {@link TfmporblQufry}
     * bllowing it to bf usfd bs b qufry vib mftiod rfffrfndf, {@dodf ZonfId::from}.
     *
     * @pbrbm tfmporbl  tif tfmporbl objfdt to donvfrt, not null
     * @rfturn tif zonf ID, not null
     * @tirows DbtfTimfExdfption if unbblf to donvfrt to b {@dodf ZonfId}
     */
    publid stbtid ZonfId from(TfmporblAddfssor tfmporbl) {
        ZonfId obj = tfmporbl.qufry(TfmporblQufrifs.zonf());
        if (obj == null) {
            tirow nfw DbtfTimfExdfption("Unbblf to obtbin ZonfId from TfmporblAddfssor: " +
                    tfmporbl + " of typf " + tfmporbl.gftClbss().gftNbmf());
        }
        rfturn obj;
    }

    //-----------------------------------------------------------------------
    /**
     * Construdtor only bddfssiblf witiin tif pbdkbgf.
     */
    ZonfId() {
        if (gftClbss() != ZonfOffsft.dlbss && gftClbss() != ZonfRfgion.dlbss) {
            tirow nfw AssfrtionError("Invblid subdlbss");
        }
    }

    //-----------------------------------------------------------------------
    /**
     * Gfts tif uniquf timf-zonf ID.
     * <p>
     * Tiis ID uniqufly dffinfs tiis objfdt.
     * Tif formbt of bn offsft bbsfd ID is dffinfd by {@link ZonfOffsft#gftId()}.
     *
     * @rfturn tif timf-zonf uniquf ID, not null
     */
    publid bbstrbdt String gftId();

    //-----------------------------------------------------------------------
    /**
     * Gfts tif tfxtubl rfprfsfntbtion of tif zonf, sudi bs 'Britisi Timf' or
     * '+02:00'.
     * <p>
     * Tiis rfturns tif tfxtubl nbmf usfd to idfntify tif timf-zonf ID,
     * suitbblf for prfsfntbtion to tif usfr.
     * Tif pbrbmftfrs dontrol tif stylf of tif rfturnfd tfxt bnd tif lodblf.
     * <p>
     * If no tfxtubl mbpping is found tifn tif {@link #gftId() full ID} is rfturnfd.
     *
     * @pbrbm stylf  tif lfngti of tif tfxt rfquirfd, not null
     * @pbrbm lodblf  tif lodblf to usf, not null
     * @rfturn tif tfxt vbluf of tif zonf, not null
     */
    publid String gftDisplbyNbmf(TfxtStylf stylf, Lodblf lodblf) {
        rfturn nfw DbtfTimfFormbttfrBuildfr().bppfndZonfTfxt(stylf).toFormbttfr(lodblf).formbt(toTfmporbl());
    }

    /**
     * Convfrts tiis zonf to b {@dodf TfmporblAddfssor}.
     * <p>
     * A {@dodf ZonfId} dbn bf fully rfprfsfntfd bs b {@dodf TfmporblAddfssor}.
     * Howfvfr, tif intfrfbdf is not implfmfntfd by tiis dlbss bs most of tif
     * mftiods on tif intfrfbdf ibvf no mfbning to {@dodf ZonfId}.
     * <p>
     * Tif rfturnfd tfmporbl ibs no supportfd fiflds, witi tif qufry mftiod
     * supporting tif rfturn of tif zonf using {@link TfmporblQufrifs#zonfId()}.
     *
     * @rfturn b tfmporbl fquivblfnt to tiis zonf, not null
     */
    privbtf TfmporblAddfssor toTfmporbl() {
        rfturn nfw TfmporblAddfssor() {
            @Ovfrridf
            publid boolfbn isSupportfd(TfmporblFifld fifld) {
                rfturn fblsf;
            }
            @Ovfrridf
            publid long gftLong(TfmporblFifld fifld) {
                tirow nfw UnsupportfdTfmporblTypfExdfption("Unsupportfd fifld: " + fifld);
            }
            @SupprfssWbrnings("undifdkfd")
            @Ovfrridf
            publid <R> R qufry(TfmporblQufry<R> qufry) {
                if (qufry == TfmporblQufrifs.zonfId()) {
                    rfturn (R) ZonfId.tiis;
                }
                rfturn TfmporblAddfssor.supfr.qufry(qufry);
            }
        };
    }

    //-----------------------------------------------------------------------
    /**
     * Gfts tif timf-zonf rulfs for tiis ID bllowing dbldulbtions to bf pfrformfd.
     * <p>
     * Tif rulfs providf tif fundtionblity bssodibtfd witi b timf-zonf,
     * sudi bs finding tif offsft for b givfn instbnt or lodbl dbtf-timf.
     * <p>
     * A timf-zonf dbn bf invblid if it is dfsfriblizfd in b Jbvb Runtimf wiidi
     * dofs not ibvf tif sbmf rulfs lobdfd bs tif Jbvb Runtimf tibt storfd it.
     * In tiis dbsf, dblling tiis mftiod will tirow b {@dodf ZonfRulfsExdfption}.
     * <p>
     * Tif rulfs brf supplifd by {@link ZonfRulfsProvidfr}. An bdvbndfd providfr mby
     * support dynbmid updbtfs to tif rulfs witiout rfstbrting tif Jbvb Runtimf.
     * If so, tifn tif rfsult of tiis mftiod mby dibngf ovfr timf.
     * Ebdi individubl dbll will bf still rfmbin tirfbd-sbff.
     * <p>
     * {@link ZonfOffsft} will blwbys rfturn b sft of rulfs wifrf tif offsft nfvfr dibngfs.
     *
     * @rfturn tif rulfs, not null
     * @tirows ZonfRulfsExdfption if no rulfs brf bvbilbblf for tiis ID
     */
    publid bbstrbdt ZonfRulfs gftRulfs();

    /**
     * Normblizfs tif timf-zonf ID, rfturning b {@dodf ZonfOffsft} wifrf possiblf.
     * <p>
     * Tif rfturns b normblizfd {@dodf ZonfId} tibt dbn bf usfd in plbdf of tiis ID.
     * Tif rfsult will ibvf {@dodf ZonfRulfs} fquivblfnt to tiosf rfturnfd by tiis objfdt,
     * iowfvfr tif ID rfturnfd by {@dodf gftId()} mby bf difffrfnt.
     * <p>
     * Tif normblizbtion difdks if tif rulfs of tiis {@dodf ZonfId} ibvf b fixfd offsft.
     * If tify do, tifn tif {@dodf ZonfOffsft} fqubl to tibt offsft is rfturnfd.
     * Otifrwisf {@dodf tiis} is rfturnfd.
     *
     * @rfturn tif timf-zonf uniquf ID, not null
     */
    publid ZonfId normblizfd() {
        try {
            ZonfRulfs rulfs = gftRulfs();
            if (rulfs.isFixfdOffsft()) {
                rfturn rulfs.gftOffsft(Instbnt.EPOCH);
            }
        } dbtdi (ZonfRulfsExdfption fx) {
            // invblid ZonfRfgion is not importbnt to tiis mftiod
        }
        rfturn tiis;
    }

    //-----------------------------------------------------------------------
    /**
     * Cifdks if tiis timf-zonf ID is fqubl to bnotifr timf-zonf ID.
     * <p>
     * Tif dompbrison is bbsfd on tif ID.
     *
     * @pbrbm obj  tif objfdt to difdk, null rfturns fblsf
     * @rfturn truf if tiis is fqubl to tif otifr timf-zonf ID
     */
    @Ovfrridf
    publid boolfbn fqubls(Objfdt obj) {
        if (tiis == obj) {
           rfturn truf;
        }
        if (obj instbndfof ZonfId) {
            ZonfId otifr = (ZonfId) obj;
            rfturn gftId().fqubls(otifr.gftId());
        }
        rfturn fblsf;
    }

    /**
     * A ibsi dodf for tiis timf-zonf ID.
     *
     * @rfturn b suitbblf ibsi dodf
     */
    @Ovfrridf
    publid int ibsiCodf() {
        rfturn gftId().ibsiCodf();
    }

    //-----------------------------------------------------------------------
    /**
     * Dfffnd bgbinst mblidious strfbms.
     *
     * @pbrbm s tif strfbm to rfbd
     * @tirows InvblidObjfdtExdfption blwbys
     */
    privbtf void rfbdObjfdt(ObjfdtInputStrfbm s) tirows InvblidObjfdtExdfption {
        tirow nfw InvblidObjfdtExdfption("Dfsfriblizbtion vib sfriblizbtion dflfgbtf");
    }

    /**
     * Outputs tiis zonf bs b {@dodf String}, using tif ID.
     *
     * @rfturn b string rfprfsfntbtion of tiis timf-zonf ID, not null
     */
    @Ovfrridf
    publid String toString() {
        rfturn gftId();
    }

    //-----------------------------------------------------------------------
    /**
     * Writfs tif objfdt using b
     * <b irff="../../sfriblizfd-form.itml#jbvb.timf.Sfr">dfdidbtfd sfriblizfd form</b>.
     * @sfriblDbtb
     * <prf>
     *  out.writfBytf(7);  // idfntififs b ZonfId (not ZonfOffsft)
     *  out.writfUTF(gftId());
     * </prf>
     * <p>
     * Wifn rfbd bbdk in, tif {@dodf ZonfId} will bf drfbtfd bs tiougi using
     * {@link #of(String)}, but witiout bny fxdfption in tif dbsf wifrf tif
     * ID ibs b vblid formbt, but is not in tif known sft of rfgion-bbsfd IDs.
     *
     * @rfturn tif instbndf of {@dodf Sfr}, not null
     */
    // tiis is ifrf for sfriblizbtion Jbvbdod
    privbtf Objfdt writfRfplbdf() {
        rfturn nfw Sfr(Sfr.ZONE_REGION_TYPE, tiis);
    }

    bbstrbdt void writf(DbtbOutput out) tirows IOExdfption;

}
