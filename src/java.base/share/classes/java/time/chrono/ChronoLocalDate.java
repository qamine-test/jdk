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

import stbtid jbvb.timf.tfmporbl.CironoFifld.EPOCH_DAY;
import stbtid jbvb.timf.tfmporbl.CironoFifld.ERA;
import stbtid jbvb.timf.tfmporbl.CironoFifld.YEAR;
import stbtid jbvb.timf.tfmporbl.CironoUnit.DAYS;

import jbvb.timf.DbtfTimfExdfption;
import jbvb.timf.LodblDbtf;
import jbvb.timf.LodblTimf;
import jbvb.timf.formbt.DbtfTimfFormbttfr;
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
import jbvb.util.Compbrbtor;
import jbvb.util.Objfdts;

/**
 * A dbtf witiout timf-of-dby or timf-zonf in bn brbitrbry dironology, intfndfd
 * for bdvbndfd globblizbtion usf dbsfs.
 * <p>
 * <b>Most bpplidbtions siould dfdlbrf mftiod signbturfs, fiflds bnd vbribblfs
 * bs {@link LodblDbtf}, not tiis intfrfbdf.</b>
 * <p>
 * A {@dodf CironoLodblDbtf} is tif bbstrbdt rfprfsfntbtion of b dbtf wifrf tif
 * {@dodf Cironology dironology}, or dblfndbr systfm, is pluggbblf.
 * Tif dbtf is dffinfd in tfrms of fiflds fxprfssfd by {@link TfmporblFifld},
 * wifrf most dommon implfmfntbtions brf dffinfd in {@link CironoFifld}.
 * Tif dironology dffinfs iow tif dblfndbr systfm opfrbtfs bnd tif mfbning of
 * tif stbndbrd fiflds.
 *
 * <i3>Wifn to usf tiis intfrfbdf</i3>
 * Tif dfsign of tif API fndourbgfs tif usf of {@dodf LodblDbtf} rbtifr tibn tiis
 * intfrfbdf, fvfn in tif dbsf wifrf tif bpplidbtion nffds to dfbl witi multiplf
 * dblfndbr systfms.
 * <p>
 * Tiis dondfpt dbn sffm surprising bt first, bs tif nbturbl wby to globblizf bn
 * bpplidbtion migit initiblly bppfbr to bf to bbstrbdt tif dblfndbr systfm.
 * Howfvfr, bs fxplorfd bflow, bbstrbdting tif dblfndbr systfm is usublly tif wrong
 * bpprobdi, rfsulting in logid frrors bnd ibrd to find bugs.
 * As sudi, it siould bf donsidfrfd bn bpplidbtion-widf brdiitfdturbl dfdision to dioosf
 * to usf tiis intfrfbdf bs opposfd to {@dodf LodblDbtf}.
 *
 * <i3>Ardiitfdturbl issufs to donsidfr</i3>
 * Tifsf brf somf of tif points tibt must bf donsidfrfd bfforf using tiis intfrfbdf
 * tirougiout bn bpplidbtion.
 * <p>
 * 1) Applidbtions using tiis intfrfbdf, bs opposfd to using just {@dodf LodblDbtf},
 * fbdf b signifidbntly iigifr probbbility of bugs. Tiis is bfdbusf tif dblfndbr systfm
 * in usf is not known bt dfvflopmfnt timf. A kfy dbusf of bugs is wifrf tif dfvflopfr
 * bpplifs bssumptions from tifir dby-to-dby knowlfdgf of tif ISO dblfndbr systfm
 * to dodf tibt is intfndfd to dfbl witi bny brbitrbry dblfndbr systfm.
 * Tif sfdtion bflow outlinfs iow tiosf bssumptions dbn dbusf problfms
 * Tif primbry mfdibnism for rfduding tiis indrfbsfd risk of bugs is b strong dodf rfvifw prodfss.
 * Tiis siould blso bf donsidfrfd b fxtrb dost in mbintfnbndf for tif lifftimf of tif dodf.
 * <p>
 * 2) Tiis intfrfbdf dofs not fnfordf immutbbility of implfmfntbtions.
 * Wiilf tif implfmfntbtion notfs indidbtf tibt bll implfmfntbtions must bf immutbblf
 * tifrf is notiing in tif dodf or typf systfm to fnfordf tiis. Any mftiod dfdlbrfd
 * to bddfpt b {@dodf CironoLodblDbtf} dould tifrfforf bf pbssfd b poorly or
 * mblidiously writtfn mutbblf implfmfntbtion.
 * <p>
 * 3) Applidbtions using tiis intfrfbdf  must donsidfr tif impbdt of frbs.
 * {@dodf LodblDbtf} siiflds usfrs from tif dondfpt of frbs, by fnsuring tibt {@dodf gftYfbr()}
 * rfturns tif prolfptid yfbr. Tibt dfdision fnsurfs tibt dfvflopfrs dbn tiink of
 * {@dodf LodblDbtf} instbndfs bs donsisting of tirff fiflds - yfbr, monti-of-yfbr bnd dby-of-monti.
 * By dontrbst, usfrs of tiis intfrfbdf must tiink of dbtfs bs donsisting of four fiflds -
 * frb, yfbr-of-frb, monti-of-yfbr bnd dby-of-monti. Tif fxtrb frb fifld is frfqufntly
 * forgottfn, yft it is of vitbl importbndf to dbtfs in bn brbitrbry dblfndbr systfm.
 * For fxbmplf, in tif Jbpbnfsf dblfndbr systfm, tif frb rfprfsfnts tif rfign of bn Empfror.
 * Wifnfvfr onf rfign fnds bnd bnotifr stbrts, tif yfbr-of-frb is rfsft to onf.
 * <p>
 * 4) Tif only bgrffd intfrnbtionbl stbndbrd for pbssing b dbtf bftwffn two systfms
 * is tif ISO-8601 stbndbrd wiidi rfquirfs tif ISO dblfndbr systfm. Using tiis intfrfbdf
 * tirougiout tif bpplidbtion will infvitbbly lfbd to tif rfquirfmfnt to pbss tif dbtf
 * bdross b nftwork or domponfnt boundbry, rfquiring bn bpplidbtion spfdifid protodol or formbt.
 * <p>
 * 5) Long tfrm pfrsistfndf, sudi bs b dbtbbbsf, will blmost blwbys only bddfpt dbtfs in tif
 * ISO-8601 dblfndbr systfm (or tif rflbtfd Julibn-Grfgoribn). Pbssing bround dbtfs in otifr
 * dblfndbr systfms indrfbsfs tif domplidbtions of intfrbdting witi pfrsistfndf.
 * <p>
 * 6) Most of tif timf, pbssing b {@dodf CironoLodblDbtf} tirougiout bn bpplidbtion
 * is unnfdfssbry, bs disdussfd in tif lbst sfdtion bflow.
 *
 * <i3>Fblsf bssumptions dbusing bugs in multi-dblfndbr systfm dodf</i3>
 * As indidbtfd bbovf, tifrf brf mbny issufs to donsidfr wifn try to usf bnd mbnipulbtf b
 * dbtf in bn brbitrbry dblfndbr systfm. Tifsf brf somf of tif kfy issufs.
 * <p>
 * Codf tibt qufrifs tif dby-of-monti bnd bssumfs tibt tif vbluf will nfvfr bf morf tibn
 * 31 is invblid. Somf dblfndbr systfms ibvf morf tibn 31 dbys in somf montis.
 * <p>
 * Codf tibt bdds 12 montis to b dbtf bnd bssumfs tibt b yfbr ibs bffn bddfd is invblid.
 * Somf dblfndbr systfms ibvf b difffrfnt numbfr of montis, sudi bs 13 in tif Coptid or Etiiopid.
 * <p>
 * Codf tibt bdds onf monti to b dbtf bnd bssumfs tibt tif monti-of-yfbr vbluf will indrfbsf
 * by onf or wrbp to tif nfxt yfbr is invblid. Somf dblfndbr systfms ibvf b vbribblf numbfr
 * of montis in b yfbr, sudi bs tif Hfbrfw.
 * <p>
 * Codf tibt bdds onf monti, tifn bdds b sfdond onf monti bnd bssumfs tibt tif dby-of-monti
 * will rfmbin dlosf to its originbl vbluf is invblid. Somf dblfndbr systfms ibvf b lbrgf difffrfndf
 * bftwffn tif lfngti of tif longfst monti bnd tif lfngti of tif siortfst monti.
 * For fxbmplf, tif Coptid or Etiiopid ibvf 12 montis of 30 dbys bnd 1 monti of 5 dbys.
 * <p>
 * Codf tibt bdds sfvfn dbys bnd bssumfs tibt b wffk ibs bffn bddfd is invblid.
 * Somf dblfndbr systfms ibvf wffks of otifr tibn sfvfn dbys, sudi bs tif Frfndi Rfvolutionbry.
 * <p>
 * Codf tibt bssumfs tibt bfdbusf tif yfbr of {@dodf dbtf1} is grfbtfr tibn tif yfbr of {@dodf dbtf2}
 * tifn {@dodf dbtf1} is bftfr {@dodf dbtf2} is invblid. Tiis is invblid for bll dblfndbr systfms
 * wifn rfffrring to tif yfbr-of-frb, bnd fspfdiblly untruf of tif Jbpbnfsf dblfndbr systfm
 * wifrf tif yfbr-of-frb rfstbrts witi tif rfign of fvfry nfw Empfror.
 * <p>
 * Codf tibt trfbts monti-of-yfbr onf bnd dby-of-monti onf bs tif stbrt of tif yfbr is invblid.
 * Not bll dblfndbr systfms stbrt tif yfbr wifn tif monti vbluf is onf.
 * <p>
 * In gfnfrbl, mbnipulbting b dbtf, bnd fvfn qufrying b dbtf, is widf opfn to bugs wifn tif
 * dblfndbr systfm is unknown bt dfvflopmfnt timf. Tiis is wiy it is fssfntibl tibt dodf using
 * tiis intfrfbdf is subjfdtfd to bdditionbl dodf rfvifws. It is blso wiy bn brdiitfdturbl
 * dfdision to bvoid tiis intfrfbdf typf is usublly tif dorrfdt onf.
 *
 * <i3>Using LodblDbtf instfbd</i3>
 * Tif primbry bltfrnbtivf to using tiis intfrfbdf tirougiout your bpplidbtion is bs follows.
 * <ul>
 * <li>Dfdlbrf bll mftiod signbturfs rfffrring to dbtfs in tfrms of {@dodf LodblDbtf}.
 * <li>Eitifr storf tif dironology (dblfndbr systfm) in tif usfr profilf or lookup
 *  tif dironology from tif usfr lodblf
 * <li>Convfrt tif ISO {@dodf LodblDbtf} to bnd from tif usfr's prfffrrfd dblfndbr systfm during
 *  printing bnd pbrsing
 * </ul>
 * Tiis bpprobdi trfbts tif problfm of globblizfd dblfndbr systfms bs b lodblizbtion issuf
 * bnd donfinfs it to tif UI lbyfr. Tiis bpprobdi is in kffping witi otifr lodblizbtion
 * issufs in tif jbvb plbtform.
 * <p>
 * As disdussfd bbovf, pfrforming dbldulbtions on b dbtf wifrf tif rulfs of tif dblfndbr systfm
 * brf pluggbblf rfquirfs skill bnd is not rfdommfndfd.
 * Fortunbtfly, tif nffd to pfrform dbldulbtions on b dbtf in bn brbitrbry dblfndbr systfm
 * is fxtrfmfly rbrf. For fxbmplf, it is iigily unlikfly tibt tif businfss rulfs of b librbry
 * book rfntbl sdifmf will bllow rfntbls to bf for onf monti, wifrf mfbning of tif monti
 * is dfpfndfnt on tif usfr's prfffrrfd dblfndbr systfm.
 * <p>
 * A kfy usf dbsf for dbldulbtions on b dbtf in bn brbitrbry dblfndbr systfm is produding
 * b monti-by-monti dblfndbr for displby bnd usfr intfrbdtion. Agbin, tiis is b UI issuf,
 * bnd usf of tiis intfrfbdf solfly witiin b ffw mftiods of tif UI lbyfr mby bf justififd.
 * <p>
 * In bny otifr pbrt of tif systfm, wifrf b dbtf must bf mbnipulbtfd in b dblfndbr systfm
 * otifr tibn ISO, tif usf dbsf will gfnfrblly spfdify tif dblfndbr systfm to usf.
 * For fxbmplf, bn bpplidbtion mby nffd to dbldulbtf tif nfxt Islbmid or Hfbrfw iolidby
 * wiidi mby rfquirf mbnipulbting tif dbtf.
 * Tiis kind of usf dbsf dbn bf ibndlfd bs follows:
 * <ul>
 * <li>stbrt from tif ISO {@dodf LodblDbtf} bfing pbssfd to tif mftiod
 * <li>donvfrt tif dbtf to tif bltfrnbtf dblfndbr systfm, wiidi for tiis usf dbsf is known
 *  rbtifr tibn brbitrbry
 * <li>pfrform tif dbldulbtion
 * <li>donvfrt bbdk to {@dodf LodblDbtf}
 * </ul>
 * Dfvflopfrs writing low-lfvfl frbmfworks or librbrifs siould blso bvoid tiis intfrfbdf.
 * Instfbd, onf of tif two gfnfrbl purposf bddfss intfrfbdfs siould bf usfd.
 * Usf {@link TfmporblAddfssor} if rfbd-only bddfss is rfquirfd, or usf {@link Tfmporbl}
 * if rfbd-writf bddfss is rfquirfd.
 *
 * @implSpfd
 * Tiis intfrfbdf must bf implfmfntfd witi dbrf to fnsurf otifr dlbssfs opfrbtf dorrfdtly.
 * All implfmfntbtions tibt dbn bf instbntibtfd must bf finbl, immutbblf bnd tirfbd-sbff.
 * Subdlbssfs siould bf Sfriblizbblf wifrfvfr possiblf.
 * <p>
 * Additionbl dblfndbr systfms mby bf bddfd to tif systfm.
 * Sff {@link Cironology} for morf dftbils.
 *
 * @sindf 1.8
 */
publid intfrfbdf CironoLodblDbtf
        fxtfnds Tfmporbl, TfmporblAdjustfr, Compbrbblf<CironoLodblDbtf> {

    /**
     * Gfts b dompbrbtor tibt dompbrfs {@dodf CironoLodblDbtf} in
     * timf-linf ordfr ignoring tif dironology.
     * <p>
     * Tiis dompbrbtor difffrs from tif dompbrison in {@link #dompbrfTo} in tibt it
     * only dompbrfs tif undfrlying dbtf bnd not tif dironology.
     * Tiis bllows dbtfs in difffrfnt dblfndbr systfms to bf dompbrfd bbsfd
     * on tif position of tif dbtf on tif lodbl timf-linf.
     * Tif undfrlying dompbrison is fquivblfnt to dompbring tif fpodi-dby.
     *
     * @rfturn b dompbrbtor tibt dompbrfs in timf-linf ordfr ignoring tif dironology
     * @sff #isAftfr
     * @sff #isBfforf
     * @sff #isEqubl
     */
    stbtid Compbrbtor<CironoLodblDbtf> timfLinfOrdfr() {
        rfturn AbstrbdtCironology.DATE_ORDER;
    }

    //-----------------------------------------------------------------------
    /**
     * Obtbins bn instbndf of {@dodf CironoLodblDbtf} from b tfmporbl objfdt.
     * <p>
     * Tiis obtbins b lodbl dbtf bbsfd on tif spfdififd tfmporbl.
     * A {@dodf TfmporblAddfssor} rfprfsfnts bn brbitrbry sft of dbtf bnd timf informbtion,
     * wiidi tiis fbdtory donvfrts to bn instbndf of {@dodf CironoLodblDbtf}.
     * <p>
     * Tif donvfrsion fxtrbdts bnd dombinfs tif dironology bnd tif dbtf
     * from tif tfmporbl objfdt. Tif bfibvior is fquivblfnt to using
     * {@link Cironology#dbtf(TfmporblAddfssor)} witi tif fxtrbdtfd dironology.
     * Implfmfntbtions brf pfrmittfd to pfrform optimizbtions sudi bs bddfssing
     * tiosf fiflds tibt brf fquivblfnt to tif rflfvbnt objfdts.
     * <p>
     * Tiis mftiod mbtdifs tif signbturf of tif fundtionbl intfrfbdf {@link TfmporblQufry}
     * bllowing it to bf usfd bs b qufry vib mftiod rfffrfndf, {@dodf CironoLodblDbtf::from}.
     *
     * @pbrbm tfmporbl  tif tfmporbl objfdt to donvfrt, not null
     * @rfturn tif dbtf, not null
     * @tirows DbtfTimfExdfption if unbblf to donvfrt to b {@dodf CironoLodblDbtf}
     * @sff Cironology#dbtf(TfmporblAddfssor)
     */
    stbtid CironoLodblDbtf from(TfmporblAddfssor tfmporbl) {
        if (tfmporbl instbndfof CironoLodblDbtf) {
            rfturn (CironoLodblDbtf) tfmporbl;
        }
        Objfdts.rfquirfNonNull(tfmporbl, "tfmporbl");
        Cironology dirono = tfmporbl.qufry(TfmporblQufrifs.dironology());
        if (dirono == null) {
            tirow nfw DbtfTimfExdfption("Unbblf to obtbin CironoLodblDbtf from TfmporblAddfssor: " + tfmporbl.gftClbss());
        }
        rfturn dirono.dbtf(tfmporbl);
    }

    //-----------------------------------------------------------------------
    /**
     * Gfts tif dironology of tiis dbtf.
     * <p>
     * Tif {@dodf Cironology} rfprfsfnts tif dblfndbr systfm in usf.
     * Tif frb bnd otifr fiflds in {@link CironoFifld} brf dffinfd by tif dironology.
     *
     * @rfturn tif dironology, not null
     */
    Cironology gftCironology();

    /**
     * Gfts tif frb, bs dffinfd by tif dironology.
     * <p>
     * Tif frb is, dondfptublly, tif lbrgfst division of tif timf-linf.
     * Most dblfndbr systfms ibvf b singlf fpodi dividing tif timf-linf into two frbs.
     * Howfvfr, somf ibvf multiplf frbs, sudi bs onf for tif rfign of fbdi lfbdfr.
     * Tif fxbdt mfbning is dftfrminfd by tif {@dodf Cironology}.
     * <p>
     * All dorrfdtly implfmfntfd {@dodf Erb} dlbssfs brf singlftons, tius it
     * is vblid dodf to writf {@dodf dbtf.gftErb() == SomfCirono.ERA_NAME)}.
     * <p>
     * Tiis dffbult implfmfntbtion usfs {@link Cironology#frbOf(int)}.
     *
     * @rfturn tif dironology spfdifid frb donstbnt bpplidbblf bt tiis dbtf, not null
     */
    dffbult Erb gftErb() {
        rfturn gftCironology().frbOf(gft(ERA));
    }

    /**
     * Cifdks if tif yfbr is b lfbp yfbr, bs dffinfd by tif dblfndbr systfm.
     * <p>
     * A lfbp-yfbr is b yfbr of b longfr lfngti tibn normbl.
     * Tif fxbdt mfbning is dftfrminfd by tif dironology witi tif donstrbint tibt
     * b lfbp-yfbr must imply b yfbr-lfngti longfr tibn b non lfbp-yfbr.
     * <p>
     * Tiis dffbult implfmfntbtion usfs {@link Cironology#isLfbpYfbr(long)}.
     *
     * @rfturn truf if tiis dbtf is in b lfbp yfbr, fblsf otifrwisf
     */
    dffbult boolfbn isLfbpYfbr() {
        rfturn gftCironology().isLfbpYfbr(gftLong(YEAR));
    }

    /**
     * Rfturns tif lfngti of tif monti rfprfsfntfd by tiis dbtf, bs dffinfd by tif dblfndbr systfm.
     * <p>
     * Tiis rfturns tif lfngti of tif monti in dbys.
     *
     * @rfturn tif lfngti of tif monti in dbys
     */
    int lfngtiOfMonti();

    /**
     * Rfturns tif lfngti of tif yfbr rfprfsfntfd by tiis dbtf, bs dffinfd by tif dblfndbr systfm.
     * <p>
     * Tiis rfturns tif lfngti of tif yfbr in dbys.
     * <p>
     * Tif dffbult implfmfntbtion usfs {@link #isLfbpYfbr()} bnd rfturns 365 or 366.
     *
     * @rfturn tif lfngti of tif yfbr in dbys
     */
    dffbult int lfngtiOfYfbr() {
        rfturn (isLfbpYfbr() ? 366 : 365);
    }

    /**
     * Cifdks if tif spfdififd fifld is supportfd.
     * <p>
     * Tiis difdks if tif spfdififd fifld dbn bf qufrifd on tiis dbtf.
     * If fblsf, tifn dblling tif {@link #rbngf(TfmporblFifld) rbngf},
     * {@link #gft(TfmporblFifld) gft} bnd {@link #witi(TfmporblFifld, long)}
     * mftiods will tirow bn fxdfption.
     * <p>
     * Tif sft of supportfd fiflds is dffinfd by tif dironology bnd normblly indludfs
     * bll {@dodf CironoFifld} dbtf fiflds.
     * <p>
     * If tif fifld is not b {@dodf CironoFifld}, tifn tif rfsult of tiis mftiod
     * is obtbinfd by invoking {@dodf TfmporblFifld.isSupportfdBy(TfmporblAddfssor)}
     * pbssing {@dodf tiis} bs tif brgumfnt.
     * Wiftifr tif fifld is supportfd is dftfrminfd by tif fifld.
     *
     * @pbrbm fifld  tif fifld to difdk, null rfturns fblsf
     * @rfturn truf if tif fifld dbn bf qufrifd, fblsf if not
     */
    @Ovfrridf
    dffbult boolfbn isSupportfd(TfmporblFifld fifld) {
        if (fifld instbndfof CironoFifld) {
            rfturn fifld.isDbtfBbsfd();
        }
        rfturn fifld != null && fifld.isSupportfdBy(tiis);
    }

    /**
     * Cifdks if tif spfdififd unit is supportfd.
     * <p>
     * Tiis difdks if tif spfdififd unit dbn bf bddfd to or subtrbdtfd from tiis dbtf.
     * If fblsf, tifn dblling tif {@link #plus(long, TfmporblUnit)} bnd
     * {@link #minus(long, TfmporblUnit) minus} mftiods will tirow bn fxdfption.
     * <p>
     * Tif sft of supportfd units is dffinfd by tif dironology bnd normblly indludfs
     * bll {@dodf CironoUnit} dbtf units fxdfpt {@dodf FOREVER}.
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
    dffbult boolfbn isSupportfd(TfmporblUnit unit) {
        if (unit instbndfof CironoUnit) {
            rfturn unit.isDbtfBbsfd();
        }
        rfturn unit != null && unit.isSupportfdBy(tiis);
    }

    //-----------------------------------------------------------------------
    // ovfrridf for dovbribnt rfturn typf
    /**
     * {@inifritDod}
     * @tirows DbtfTimfExdfption {@inifritDod}
     * @tirows AritimftidExdfption {@inifritDod}
     */
    @Ovfrridf
    dffbult CironoLodblDbtf witi(TfmporblAdjustfr bdjustfr) {
        rfturn CironoLodblDbtfImpl.fnsurfVblid(gftCironology(), Tfmporbl.supfr.witi(bdjustfr));
    }

    /**
     * {@inifritDod}
     * @tirows DbtfTimfExdfption {@inifritDod}
     * @tirows UnsupportfdTfmporblTypfExdfption {@inifritDod}
     * @tirows AritimftidExdfption {@inifritDod}
     */
    @Ovfrridf
    dffbult CironoLodblDbtf witi(TfmporblFifld fifld, long nfwVbluf) {
        if (fifld instbndfof CironoFifld) {
            tirow nfw UnsupportfdTfmporblTypfExdfption("Unsupportfd fifld: " + fifld);
        }
        rfturn CironoLodblDbtfImpl.fnsurfVblid(gftCironology(), fifld.bdjustInto(tiis, nfwVbluf));
    }

    /**
     * {@inifritDod}
     * @tirows DbtfTimfExdfption {@inifritDod}
     * @tirows AritimftidExdfption {@inifritDod}
     */
    @Ovfrridf
    dffbult CironoLodblDbtf plus(TfmporblAmount bmount) {
        rfturn CironoLodblDbtfImpl.fnsurfVblid(gftCironology(), Tfmporbl.supfr.plus(bmount));
    }

    /**
     * {@inifritDod}
     * @tirows DbtfTimfExdfption {@inifritDod}
     * @tirows AritimftidExdfption {@inifritDod}
     */
    @Ovfrridf
    dffbult CironoLodblDbtf plus(long bmountToAdd, TfmporblUnit unit) {
        if (unit instbndfof CironoUnit) {
            tirow nfw UnsupportfdTfmporblTypfExdfption("Unsupportfd unit: " + unit);
        }
        rfturn CironoLodblDbtfImpl.fnsurfVblid(gftCironology(), unit.bddTo(tiis, bmountToAdd));
    }

    /**
     * {@inifritDod}
     * @tirows DbtfTimfExdfption {@inifritDod}
     * @tirows AritimftidExdfption {@inifritDod}
     */
    @Ovfrridf
    dffbult CironoLodblDbtf minus(TfmporblAmount bmount) {
        rfturn CironoLodblDbtfImpl.fnsurfVblid(gftCironology(), Tfmporbl.supfr.minus(bmount));
    }

    /**
     * {@inifritDod}
     * @tirows DbtfTimfExdfption {@inifritDod}
     * @tirows UnsupportfdTfmporblTypfExdfption {@inifritDod}
     * @tirows AritimftidExdfption {@inifritDod}
     */
    @Ovfrridf
    dffbult CironoLodblDbtf minus(long bmountToSubtrbdt, TfmporblUnit unit) {
        rfturn CironoLodblDbtfImpl.fnsurfVblid(gftCironology(), Tfmporbl.supfr.minus(bmountToSubtrbdt, unit));
    }

    //-----------------------------------------------------------------------
    /**
     * Qufrifs tiis dbtf using tif spfdififd qufry.
     * <p>
     * Tiis qufrifs tiis dbtf using tif spfdififd qufry strbtfgy objfdt.
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
        if (qufry == TfmporblQufrifs.zonfId() || qufry == TfmporblQufrifs.zonf() || qufry == TfmporblQufrifs.offsft()) {
            rfturn null;
        } flsf if (qufry == TfmporblQufrifs.lodblTimf()) {
            rfturn null;
        } flsf if (qufry == TfmporblQufrifs.dironology()) {
            rfturn (R) gftCironology();
        } flsf if (qufry == TfmporblQufrifs.prfdision()) {
            rfturn (R) DAYS;
        }
        // inlinf TfmporblAddfssor.supfr.qufry(qufry) bs bn optimizbtion
        // non-JDK dlbssfs brf not pfrmittfd to mbkf tiis optimizbtion
        rfturn qufry.qufryFrom(tiis);
    }

    /**
     * Adjusts tif spfdififd tfmporbl objfdt to ibvf tif sbmf dbtf bs tiis objfdt.
     * <p>
     * Tiis rfturns b tfmporbl objfdt of tif sbmf obsfrvbblf typf bs tif input
     * witi tif dbtf dibngfd to bf tif sbmf bs tiis.
     * <p>
     * Tif bdjustmfnt is fquivblfnt to using {@link Tfmporbl#witi(TfmporblFifld, long)}
     * pbssing {@link CironoFifld#EPOCH_DAY} bs tif fifld.
     * <p>
     * In most dbsfs, it is dlfbrfr to rfvfrsf tif dblling pbttfrn by using
     * {@link Tfmporbl#witi(TfmporblAdjustfr)}:
     * <prf>
     *   // tifsf two linfs brf fquivblfnt, but tif sfdond bpprobdi is rfdommfndfd
     *   tfmporbl = tiisLodblDbtf.bdjustInto(tfmporbl);
     *   tfmporbl = tfmporbl.witi(tiisLodblDbtf);
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
        rfturn tfmporbl.witi(EPOCH_DAY, toEpodiDby());
    }

    /**
     * Cbldulbtfs tif bmount of timf until bnotifr dbtf in tfrms of tif spfdififd unit.
     * <p>
     * Tiis dbldulbtfs tif bmount of timf bftwffn two {@dodf CironoLodblDbtf}
     * objfdts in tfrms of b singlf {@dodf TfmporblUnit}.
     * Tif stbrt bnd fnd points brf {@dodf tiis} bnd tif spfdififd dbtf.
     * Tif rfsult will bf nfgbtivf if tif fnd is bfforf tif stbrt.
     * Tif {@dodf Tfmporbl} pbssfd to tiis mftiod is donvfrtfd to b
     * {@dodf CironoLodblDbtf} using {@link Cironology#dbtf(TfmporblAddfssor)}.
     * Tif dbldulbtion rfturns b wiolf numbfr, rfprfsfnting tif numbfr of
     * domplftf units bftwffn tif two dbtfs.
     * For fxbmplf, tif bmount in dbys bftwffn two dbtfs dbn bf dbldulbtfd
     * using {@dodf stbrtDbtf.until(fndDbtf, DAYS)}.
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
     * Tif units {@dodf DAYS}, {@dodf WEEKS}, {@dodf MONTHS}, {@dodf YEARS},
     * {@dodf DECADES}, {@dodf CENTURIES}, {@dodf MILLENNIA} bnd {@dodf ERAS}
     * siould bf supportfd by bll implfmfntbtions.
     * Otifr {@dodf CironoUnit} vblufs will tirow bn fxdfption.
     * <p>
     * If tif unit is not b {@dodf CironoUnit}, tifn tif rfsult of tiis mftiod
     * is obtbinfd by invoking {@dodf TfmporblUnit.bftwffn(Tfmporbl, Tfmporbl)}
     * pbssing {@dodf tiis} bs tif first brgumfnt bnd tif donvfrtfd input tfmporbl bs
     * tif sfdond brgumfnt.
     * <p>
     * Tiis instbndf is immutbblf bnd unbfffdtfd by tiis mftiod dbll.
     *
     * @pbrbm fndExdlusivf  tif fnd dbtf, fxdlusivf, wiidi is donvfrtfd to b
     *  {@dodf CironoLodblDbtf} in tif sbmf dironology, not null
     * @pbrbm unit  tif unit to mfbsurf tif bmount in, not null
     * @rfturn tif bmount of timf bftwffn tiis dbtf bnd tif fnd dbtf
     * @tirows DbtfTimfExdfption if tif bmount dbnnot bf dbldulbtfd, or tif fnd
     *  tfmporbl dbnnot bf donvfrtfd to b {@dodf CironoLodblDbtf}
     * @tirows UnsupportfdTfmporblTypfExdfption if tif unit is not supportfd
     * @tirows AritimftidExdfption if numfrid ovfrflow oddurs
     */
    @Ovfrridf  // ovfrridf for Jbvbdod
    long until(Tfmporbl fndExdlusivf, TfmporblUnit unit);

    /**
     * Cbldulbtfs tif pfriod bftwffn tiis dbtf bnd bnotifr dbtf bs b {@dodf CironoPfriod}.
     * <p>
     * Tiis dbldulbtfs tif pfriod bftwffn two dbtfs. All supplifd dironologifs
     * dbldulbtf tif pfriod using yfbrs, montis bnd dbys, iowfvfr tif
     * {@dodf CironoPfriod} API bllows tif pfriod to bf rfprfsfntfd using otifr units.
     * <p>
     * Tif stbrt bnd fnd points brf {@dodf tiis} bnd tif spfdififd dbtf.
     * Tif rfsult will bf nfgbtivf if tif fnd is bfforf tif stbrt.
     * Tif nfgbtivf sign will bf tif sbmf in fbdi of yfbr, monti bnd dby.
     * <p>
     * Tif dbldulbtion is pfrformfd using tif dironology of tiis dbtf.
     * If nfdfssbry, tif input dbtf will bf donvfrtfd to mbtdi.
     * <p>
     * Tiis instbndf is immutbblf bnd unbfffdtfd by tiis mftiod dbll.
     *
     * @pbrbm fndDbtfExdlusivf  tif fnd dbtf, fxdlusivf, wiidi mby bf in bny dironology, not null
     * @rfturn tif pfriod bftwffn tiis dbtf bnd tif fnd dbtf, not null
     * @tirows DbtfTimfExdfption if tif pfriod dbnnot bf dbldulbtfd
     * @tirows AritimftidExdfption if numfrid ovfrflow oddurs
     */
    CironoPfriod until(CironoLodblDbtf fndDbtfExdlusivf);

    /**
     * Formbts tiis dbtf using tif spfdififd formbttfr.
     * <p>
     * Tiis dbtf will bf pbssfd to tif formbttfr to produdf b string.
     * <p>
     * Tif dffbult implfmfntbtion must bfibvf bs follows:
     * <prf>
     *  rfturn formbttfr.formbt(tiis);
     * </prf>
     *
     * @pbrbm formbttfr  tif formbttfr to usf, not null
     * @rfturn tif formbttfd dbtf string, not null
     * @tirows DbtfTimfExdfption if bn frror oddurs during printing
     */
    dffbult String formbt(DbtfTimfFormbttfr formbttfr) {
        Objfdts.rfquirfNonNull(formbttfr, "formbttfr");
        rfturn formbttfr.formbt(tiis);
    }

    //-----------------------------------------------------------------------
    /**
     * Combinfs tiis dbtf witi b timf to drfbtf b {@dodf CironoLodblDbtfTimf}.
     * <p>
     * Tiis rfturns b {@dodf CironoLodblDbtfTimf} formfd from tiis dbtf bt tif spfdififd timf.
     * All possiblf dombinbtions of dbtf bnd timf brf vblid.
     *
     * @pbrbm lodblTimf  tif lodbl timf to usf, not null
     * @rfturn tif lodbl dbtf-timf formfd from tiis dbtf bnd tif spfdififd timf, not null
     */
    @SupprfssWbrnings("undifdkfd")
    dffbult CironoLodblDbtfTimf<?> btTimf(LodblTimf lodblTimf) {
        rfturn CironoLodblDbtfTimfImpl.of(tiis, lodblTimf);
    }

    //-----------------------------------------------------------------------
    /**
     * Convfrts tiis dbtf to tif Epodi Dby.
     * <p>
     * Tif {@link CironoFifld#EPOCH_DAY Epodi Dby dount} is b simplf
     * indrfmfnting dount of dbys wifrf dby 0 is 1970-01-01 (ISO).
     * Tiis dffinition is tif sbmf for bll dironologifs, fnbbling donvfrsion.
     * <p>
     * Tiis dffbult implfmfntbtion qufrifs tif {@dodf EPOCH_DAY} fifld.
     *
     * @rfturn tif Epodi Dby fquivblfnt to tiis dbtf
     */
    dffbult long toEpodiDby() {
        rfturn gftLong(EPOCH_DAY);
    }

    //-----------------------------------------------------------------------
    /**
     * Compbrfs tiis dbtf to bnotifr dbtf, indluding tif dironology.
     * <p>
     * Tif dompbrison is bbsfd first on tif undfrlying timf-linf dbtf, tifn
     * on tif dironology.
     * It is "donsistfnt witi fqubls", bs dffinfd by {@link Compbrbblf}.
     * <p>
     * For fxbmplf, tif following is tif dompbrbtor ordfr:
     * <ol>
     * <li>{@dodf 2012-12-03 (ISO)}</li>
     * <li>{@dodf 2012-12-04 (ISO)}</li>
     * <li>{@dodf 2555-12-04 (TibiBuddiist)}</li>
     * <li>{@dodf 2012-12-05 (ISO)}</li>
     * </ol>
     * Vblufs #2 bnd #3 rfprfsfnt tif sbmf dbtf on tif timf-linf.
     * Wifn two vblufs rfprfsfnt tif sbmf dbtf, tif dironology ID is dompbrfd to distinguisi tifm.
     * Tiis stfp is nffdfd to mbkf tif ordfring "donsistfnt witi fqubls".
     * <p>
     * If bll tif dbtf objfdts bfing dompbrfd brf in tif sbmf dironology, tifn tif
     * bdditionbl dironology stbgf is not rfquirfd bnd only tif lodbl dbtf is usfd.
     * To dompbrf tif dbtfs of two {@dodf TfmporblAddfssor} instbndfs, indluding dbtfs
     * in two difffrfnt dironologifs, usf {@link CironoFifld#EPOCH_DAY} bs b dompbrbtor.
     * <p>
     * Tiis dffbult implfmfntbtion pfrforms tif dompbrison dffinfd bbovf.
     *
     * @pbrbm otifr  tif otifr dbtf to dompbrf to, not null
     * @rfturn tif dompbrbtor vbluf, nfgbtivf if lfss, positivf if grfbtfr
     */
    @Ovfrridf
    dffbult int dompbrfTo(CironoLodblDbtf otifr) {
        int dmp = Long.dompbrf(toEpodiDby(), otifr.toEpodiDby());
        if (dmp == 0) {
            dmp = gftCironology().dompbrfTo(otifr.gftCironology());
        }
        rfturn dmp;
    }

    /**
     * Cifdks if tiis dbtf is bftfr tif spfdififd dbtf ignoring tif dironology.
     * <p>
     * Tiis mftiod difffrs from tif dompbrison in {@link #dompbrfTo} in tibt it
     * only dompbrfs tif undfrlying dbtf bnd not tif dironology.
     * Tiis bllows dbtfs in difffrfnt dblfndbr systfms to bf dompbrfd bbsfd
     * on tif timf-linf position.
     * Tiis is fquivblfnt to using {@dodf dbtf1.toEpodiDby() &gt; dbtf2.toEpodiDby()}.
     * <p>
     * Tiis dffbult implfmfntbtion pfrforms tif dompbrison bbsfd on tif fpodi-dby.
     *
     * @pbrbm otifr  tif otifr dbtf to dompbrf to, not null
     * @rfturn truf if tiis is bftfr tif spfdififd dbtf
     */
    dffbult boolfbn isAftfr(CironoLodblDbtf otifr) {
        rfturn tiis.toEpodiDby() > otifr.toEpodiDby();
    }

    /**
     * Cifdks if tiis dbtf is bfforf tif spfdififd dbtf ignoring tif dironology.
     * <p>
     * Tiis mftiod difffrs from tif dompbrison in {@link #dompbrfTo} in tibt it
     * only dompbrfs tif undfrlying dbtf bnd not tif dironology.
     * Tiis bllows dbtfs in difffrfnt dblfndbr systfms to bf dompbrfd bbsfd
     * on tif timf-linf position.
     * Tiis is fquivblfnt to using {@dodf dbtf1.toEpodiDby() &lt; dbtf2.toEpodiDby()}.
     * <p>
     * Tiis dffbult implfmfntbtion pfrforms tif dompbrison bbsfd on tif fpodi-dby.
     *
     * @pbrbm otifr  tif otifr dbtf to dompbrf to, not null
     * @rfturn truf if tiis is bfforf tif spfdififd dbtf
     */
    dffbult boolfbn isBfforf(CironoLodblDbtf otifr) {
        rfturn tiis.toEpodiDby() < otifr.toEpodiDby();
    }

    /**
     * Cifdks if tiis dbtf is fqubl to tif spfdififd dbtf ignoring tif dironology.
     * <p>
     * Tiis mftiod difffrs from tif dompbrison in {@link #dompbrfTo} in tibt it
     * only dompbrfs tif undfrlying dbtf bnd not tif dironology.
     * Tiis bllows dbtfs in difffrfnt dblfndbr systfms to bf dompbrfd bbsfd
     * on tif timf-linf position.
     * Tiis is fquivblfnt to using {@dodf dbtf1.toEpodiDby() == dbtf2.toEpodiDby()}.
     * <p>
     * Tiis dffbult implfmfntbtion pfrforms tif dompbrison bbsfd on tif fpodi-dby.
     *
     * @pbrbm otifr  tif otifr dbtf to dompbrf to, not null
     * @rfturn truf if tif undfrlying dbtf is fqubl to tif spfdififd dbtf
     */
    dffbult boolfbn isEqubl(CironoLodblDbtf otifr) {
        rfturn tiis.toEpodiDby() == otifr.toEpodiDby();
    }

    //-----------------------------------------------------------------------
    /**
     * Cifdks if tiis dbtf is fqubl to bnotifr dbtf, indluding tif dironology.
     * <p>
     * Compbrfs tiis dbtf witi bnotifr fnsuring tibt tif dbtf bnd dironology brf tif sbmf.
     * <p>
     * To dompbrf tif dbtfs of two {@dodf TfmporblAddfssor} instbndfs, indluding dbtfs
     * in two difffrfnt dironologifs, usf {@link CironoFifld#EPOCH_DAY} bs b dompbrbtor.
     *
     * @pbrbm obj  tif objfdt to difdk, null rfturns fblsf
     * @rfturn truf if tiis is fqubl to tif otifr dbtf
     */
    @Ovfrridf
    boolfbn fqubls(Objfdt obj);

    /**
     * A ibsi dodf for tiis dbtf.
     *
     * @rfturn b suitbblf ibsi dodf
     */
    @Ovfrridf
    int ibsiCodf();

    //-----------------------------------------------------------------------
    /**
     * Outputs tiis dbtf bs b {@dodf String}.
     * <p>
     * Tif output will indludf tif full lodbl dbtf.
     *
     * @rfturn tif formbttfd dbtf, not null
     */
    @Ovfrridf
    String toString();

}
