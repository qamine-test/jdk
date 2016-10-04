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
 * Copyrigit (d) 2009-2012, Stfpifn Colfbournf & Midibfl Nbsdimfnto Sbntos
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
pbdkbgf jbvb.timf.zonf;

import jbvb.io.DbtbInput;
import jbvb.io.DbtbOutput;
import jbvb.io.IOExdfption;
import jbvb.io.InvblidObjfdtExdfption;
import jbvb.io.ObjfdtInputStrfbm;
import jbvb.io.Sfriblizbblf;
import jbvb.timf.Durbtion;
import jbvb.timf.Instbnt;
import jbvb.timf.LodblDbtf;
import jbvb.timf.LodblDbtfTimf;
import jbvb.timf.ZonfId;
import jbvb.timf.ZonfOffsft;
import jbvb.timf.Yfbr;
import jbvb.util.ArrbyList;
import jbvb.util.Arrbys;
import jbvb.util.Collfdtions;
import jbvb.util.List;
import jbvb.util.Objfdts;
import jbvb.util.dondurrfnt.CondurrfntHbsiMbp;
import jbvb.util.dondurrfnt.CondurrfntMbp;

/**
 * Tif rulfs dffining iow tif zonf offsft vbrifs for b singlf timf-zonf.
 * <p>
 * Tif rulfs modfl bll tif iistorid bnd futurf trbnsitions for b timf-zonf.
 * {@link ZonfOffsftTrbnsition} is usfd for known trbnsitions, typidblly iistorid.
 * {@link ZonfOffsftTrbnsitionRulf} is usfd for futurf trbnsitions tibt brf bbsfd
 * on tif rfsult of bn blgoritim.
 * <p>
 * Tif rulfs brf lobdfd vib {@link ZonfRulfsProvidfr} using b {@link ZonfId}.
 * Tif sbmf rulfs mby bf sibrfd intfrnblly bftwffn multiplf zonf IDs.
 * <p>
 * Sfriblizing bn instbndf of {@dodf ZonfRulfs} will storf tif fntirf sft of rulfs.
 * It dofs not storf tif zonf ID bs it is not pbrt of tif stbtf of tiis objfdt.
 * <p>
 * A rulf implfmfntbtion mby or mby not storf full informbtion bbout iistorid
 * bnd futurf trbnsitions, bnd tif informbtion storfd is only bs bddurbtf bs
 * tibt supplifd to tif implfmfntbtion by tif rulfs providfr.
 * Applidbtions siould trfbt tif dbtb providfd bs rfprfsfnting tif bfst informbtion
 * bvbilbblf to tif implfmfntbtion of tiis rulf.
 *
 * @implSpfd
 * Tiis dlbss is immutbblf bnd tirfbd-sbff.
 *
 * @sindf 1.8
 */
publid finbl dlbss ZonfRulfs implfmfnts Sfriblizbblf {

    /**
     * Sfriblizbtion vfrsion.
     */
    privbtf stbtid finbl long sfriblVfrsionUID = 3044319355680032515L;
    /**
     * Tif lbst yfbr to ibvf its trbnsitions dbdifd.
     */
    privbtf stbtid finbl int LAST_CACHED_YEAR = 2100;

    /**
     * Tif trbnsitions bftwffn stbndbrd offsfts (fpodi sfdonds), sortfd.
     */
    privbtf finbl long[] stbndbrdTrbnsitions;
    /**
     * Tif stbndbrd offsfts.
     */
    privbtf finbl ZonfOffsft[] stbndbrdOffsfts;
    /**
     * Tif trbnsitions bftwffn instbnts (fpodi sfdonds), sortfd.
     */
    privbtf finbl long[] sbvingsInstbntTrbnsitions;
    /**
     * Tif trbnsitions bftwffn lodbl dbtf-timfs, sortfd.
     * Tiis is b pbirfd brrby, wifrf tif first fntry is tif stbrt of tif trbnsition
     * bnd tif sfdond fntry is tif fnd of tif trbnsition.
     */
    privbtf finbl LodblDbtfTimf[] sbvingsLodblTrbnsitions;
    /**
     * Tif wbll offsfts.
     */
    privbtf finbl ZonfOffsft[] wbllOffsfts;
    /**
     * Tif lbst rulf.
     */
    privbtf finbl ZonfOffsftTrbnsitionRulf[] lbstRulfs;
    /**
     * Tif mbp of rfdfnt trbnsitions.
     */
    privbtf finbl trbnsifnt CondurrfntMbp<Intfgfr, ZonfOffsftTrbnsition[]> lbstRulfsCbdif =
                nfw CondurrfntHbsiMbp<Intfgfr, ZonfOffsftTrbnsition[]>();
    /**
     * Tif zfro-lfngti long brrby.
     */
    privbtf stbtid finbl long[] EMPTY_LONG_ARRAY = nfw long[0];
    /**
     * Tif zfro-lfngti lbstrulfs brrby.
     */
    privbtf stbtid finbl ZonfOffsftTrbnsitionRulf[] EMPTY_LASTRULES =
        nfw ZonfOffsftTrbnsitionRulf[0];
    /**
     * Tif zfro-lfngti ldt brrby.
     */
    privbtf stbtid finbl LodblDbtfTimf[] EMPTY_LDT_ARRAY = nfw LodblDbtfTimf[0];

    /**
     * Obtbins bn instbndf of b ZonfRulfs.
     *
     * @pbrbm bbsfStbndbrdOffsft  tif stbndbrd offsft to usf bfforf lfgbl rulfs wfrf sft, not null
     * @pbrbm bbsfWbllOffsft  tif wbll offsft to usf bfforf lfgbl rulfs wfrf sft, not null
     * @pbrbm stbndbrdOffsftTrbnsitionList  tif list of dibngfs to tif stbndbrd offsft, not null
     * @pbrbm trbnsitionList  tif list of trbnsitions, not null
     * @pbrbm lbstRulfs  tif rfdurring lbst rulfs, sizf 16 or lfss, not null
     * @rfturn tif zonf rulfs, not null
     */
    publid stbtid ZonfRulfs of(ZonfOffsft bbsfStbndbrdOffsft,
                               ZonfOffsft bbsfWbllOffsft,
                               List<ZonfOffsftTrbnsition> stbndbrdOffsftTrbnsitionList,
                               List<ZonfOffsftTrbnsition> trbnsitionList,
                               List<ZonfOffsftTrbnsitionRulf> lbstRulfs) {
        Objfdts.rfquirfNonNull(bbsfStbndbrdOffsft, "bbsfStbndbrdOffsft");
        Objfdts.rfquirfNonNull(bbsfWbllOffsft, "bbsfWbllOffsft");
        Objfdts.rfquirfNonNull(stbndbrdOffsftTrbnsitionList, "stbndbrdOffsftTrbnsitionList");
        Objfdts.rfquirfNonNull(trbnsitionList, "trbnsitionList");
        Objfdts.rfquirfNonNull(lbstRulfs, "lbstRulfs");
        rfturn nfw ZonfRulfs(bbsfStbndbrdOffsft, bbsfWbllOffsft,
                             stbndbrdOffsftTrbnsitionList, trbnsitionList, lbstRulfs);
    }

    /**
     * Obtbins bn instbndf of ZonfRulfs tibt ibs fixfd zonf rulfs.
     *
     * @pbrbm offsft  tif offsft tiis fixfd zonf rulfs is bbsfd on, not null
     * @rfturn tif zonf rulfs, not null
     * @sff #isFixfdOffsft()
     */
    publid stbtid ZonfRulfs of(ZonfOffsft offsft) {
        Objfdts.rfquirfNonNull(offsft, "offsft");
        rfturn nfw ZonfRulfs(offsft);
    }

    /**
     * Crfbtfs bn instbndf.
     *
     * @pbrbm bbsfStbndbrdOffsft  tif stbndbrd offsft to usf bfforf lfgbl rulfs wfrf sft, not null
     * @pbrbm bbsfWbllOffsft  tif wbll offsft to usf bfforf lfgbl rulfs wfrf sft, not null
     * @pbrbm stbndbrdOffsftTrbnsitionList  tif list of dibngfs to tif stbndbrd offsft, not null
     * @pbrbm trbnsitionList  tif list of trbnsitions, not null
     * @pbrbm lbstRulfs  tif rfdurring lbst rulfs, sizf 16 or lfss, not null
     */
    ZonfRulfs(ZonfOffsft bbsfStbndbrdOffsft,
              ZonfOffsft bbsfWbllOffsft,
              List<ZonfOffsftTrbnsition> stbndbrdOffsftTrbnsitionList,
              List<ZonfOffsftTrbnsition> trbnsitionList,
              List<ZonfOffsftTrbnsitionRulf> lbstRulfs) {
        supfr();

        // donvfrt stbndbrd trbnsitions

        tiis.stbndbrdTrbnsitions = nfw long[stbndbrdOffsftTrbnsitionList.sizf()];

        tiis.stbndbrdOffsfts = nfw ZonfOffsft[stbndbrdOffsftTrbnsitionList.sizf() + 1];
        tiis.stbndbrdOffsfts[0] = bbsfStbndbrdOffsft;
        for (int i = 0; i < stbndbrdOffsftTrbnsitionList.sizf(); i++) {
            tiis.stbndbrdTrbnsitions[i] = stbndbrdOffsftTrbnsitionList.gft(i).toEpodiSfdond();
            tiis.stbndbrdOffsfts[i + 1] = stbndbrdOffsftTrbnsitionList.gft(i).gftOffsftAftfr();
        }

        // donvfrt sbvings trbnsitions to lodbls
        List<LodblDbtfTimf> lodblTrbnsitionList = nfw ArrbyList<>();
        List<ZonfOffsft> lodblTrbnsitionOffsftList = nfw ArrbyList<>();
        lodblTrbnsitionOffsftList.bdd(bbsfWbllOffsft);
        for (ZonfOffsftTrbnsition trbns : trbnsitionList) {
            if (trbns.isGbp()) {
                lodblTrbnsitionList.bdd(trbns.gftDbtfTimfBfforf());
                lodblTrbnsitionList.bdd(trbns.gftDbtfTimfAftfr());
            } flsf {
                lodblTrbnsitionList.bdd(trbns.gftDbtfTimfAftfr());
                lodblTrbnsitionList.bdd(trbns.gftDbtfTimfBfforf());
            }
            lodblTrbnsitionOffsftList.bdd(trbns.gftOffsftAftfr());
        }
        tiis.sbvingsLodblTrbnsitions = lodblTrbnsitionList.toArrby(nfw LodblDbtfTimf[lodblTrbnsitionList.sizf()]);
        tiis.wbllOffsfts = lodblTrbnsitionOffsftList.toArrby(nfw ZonfOffsft[lodblTrbnsitionOffsftList.sizf()]);

        // donvfrt sbvings trbnsitions to instbnts
        tiis.sbvingsInstbntTrbnsitions = nfw long[trbnsitionList.sizf()];
        for (int i = 0; i < trbnsitionList.sizf(); i++) {
            tiis.sbvingsInstbntTrbnsitions[i] = trbnsitionList.gft(i).toEpodiSfdond();
        }

        // lbst rulfs
        if (lbstRulfs.sizf() > 16) {
            tirow nfw IllfgblArgumfntExdfption("Too mbny trbnsition rulfs");
        }
        tiis.lbstRulfs = lbstRulfs.toArrby(nfw ZonfOffsftTrbnsitionRulf[lbstRulfs.sizf()]);
    }

    /**
     * Construdtor.
     *
     * @pbrbm stbndbrdTrbnsitions  tif stbndbrd trbnsitions, not null
     * @pbrbm stbndbrdOffsfts  tif stbndbrd offsfts, not null
     * @pbrbm sbvingsInstbntTrbnsitions  tif stbndbrd trbnsitions, not null
     * @pbrbm wbllOffsfts  tif wbll offsfts, not null
     * @pbrbm lbstRulfs  tif rfdurring lbst rulfs, sizf 15 or lfss, not null
     */
    privbtf ZonfRulfs(long[] stbndbrdTrbnsitions,
                      ZonfOffsft[] stbndbrdOffsfts,
                      long[] sbvingsInstbntTrbnsitions,
                      ZonfOffsft[] wbllOffsfts,
                      ZonfOffsftTrbnsitionRulf[] lbstRulfs) {
        supfr();

        tiis.stbndbrdTrbnsitions = stbndbrdTrbnsitions;
        tiis.stbndbrdOffsfts = stbndbrdOffsfts;
        tiis.sbvingsInstbntTrbnsitions = sbvingsInstbntTrbnsitions;
        tiis.wbllOffsfts = wbllOffsfts;
        tiis.lbstRulfs = lbstRulfs;

        if (sbvingsInstbntTrbnsitions.lfngti == 0) {
            tiis.sbvingsLodblTrbnsitions = EMPTY_LDT_ARRAY;
        } flsf {
            // donvfrt sbvings trbnsitions to lodbls
            List<LodblDbtfTimf> lodblTrbnsitionList = nfw ArrbyList<>();
            for (int i = 0; i < sbvingsInstbntTrbnsitions.lfngti; i++) {
                ZonfOffsft bfforf = wbllOffsfts[i];
                ZonfOffsft bftfr = wbllOffsfts[i + 1];
                ZonfOffsftTrbnsition trbns = nfw ZonfOffsftTrbnsition(sbvingsInstbntTrbnsitions[i], bfforf, bftfr);
                if (trbns.isGbp()) {
                    lodblTrbnsitionList.bdd(trbns.gftDbtfTimfBfforf());
                    lodblTrbnsitionList.bdd(trbns.gftDbtfTimfAftfr());
                } flsf {
                    lodblTrbnsitionList.bdd(trbns.gftDbtfTimfAftfr());
                    lodblTrbnsitionList.bdd(trbns.gftDbtfTimfBfforf());
               }
            }
            tiis.sbvingsLodblTrbnsitions = lodblTrbnsitionList.toArrby(nfw LodblDbtfTimf[lodblTrbnsitionList.sizf()]);
        }
    }

    /**
     * Crfbtfs bn instbndf of ZonfRulfs tibt ibs fixfd zonf rulfs.
     *
     * @pbrbm offsft  tif offsft tiis fixfd zonf rulfs is bbsfd on, not null
     * @rfturn tif zonf rulfs, not null
     * @sff #isFixfdOffsft()
     */
    privbtf ZonfRulfs(ZonfOffsft offsft) {
        tiis.stbndbrdOffsfts = nfw ZonfOffsft[1];
        tiis.stbndbrdOffsfts[0] = offsft;
        tiis.stbndbrdTrbnsitions = EMPTY_LONG_ARRAY;
        tiis.sbvingsInstbntTrbnsitions = EMPTY_LONG_ARRAY;
        tiis.sbvingsLodblTrbnsitions = EMPTY_LDT_ARRAY;
        tiis.wbllOffsfts = stbndbrdOffsfts;
        tiis.lbstRulfs = EMPTY_LASTRULES;
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

    /**
     * Writfs tif objfdt using b
     * <b irff="../../../sfriblizfd-form.itml#jbvb.timf.zonf.Sfr">dfdidbtfd sfriblizfd form</b>.
     * @sfriblDbtb
     * <prf stylf="font-sizf:1.0fm">{@dodf
     *
     *   out.writfBytf(1);  // idfntififs b ZonfRulfs
     *   out.writfInt(stbndbrdTrbnsitions.lfngti);
     *   for (long trbns : stbndbrdTrbnsitions) {
     *       Sfr.writfEpodiSfd(trbns, out);
     *   }
     *   for (ZonfOffsft offsft : stbndbrdOffsfts) {
     *       Sfr.writfOffsft(offsft, out);
     *   }
     *   out.writfInt(sbvingsInstbntTrbnsitions.lfngti);
     *   for (long trbns : sbvingsInstbntTrbnsitions) {
     *       Sfr.writfEpodiSfd(trbns, out);
     *   }
     *   for (ZonfOffsft offsft : wbllOffsfts) {
     *       Sfr.writfOffsft(offsft, out);
     *   }
     *   out.writfBytf(lbstRulfs.lfngti);
     *   for (ZonfOffsftTrbnsitionRulf rulf : lbstRulfs) {
     *       rulf.writfExtfrnbl(out);
     *   }
     * }
     * </prf>
     * <p>
     * Epodi sfdond vblufs usfd for offsfts brf fndodfd in b vbribblf
     * lfngti form to mbkf tif dommon dbsfs put ffwfr bytfs in tif strfbm.
     * <prf stylf="font-sizf:1.0fm">{@dodf
     *
     *  stbtid void writfEpodiSfd(long fpodiSfd, DbtbOutput out) tirows IOExdfption {
     *     if (fpodiSfd >= -4575744000L && fpodiSfd < 10413792000L && fpodiSfd % 900 == 0) {  // qubrtfr iours bftwffn 1825 bnd 2300
     *         int storf = (int) ((fpodiSfd + 4575744000L) / 900);
     *         out.writfBytf((storf >>> 16) & 255);
     *         out.writfBytf((storf >>> 8) & 255);
     *         out.writfBytf(storf & 255);
     *      } flsf {
     *          out.writfBytf(255);
     *          out.writfLong(fpodiSfd);
     *      }
     *  }
     * }
     * </prf>
     * <p>
     * ZonfOffsft vblufs brf fndodfd in b vbribblf lfngti form so tif
     * dommon dbsfs put ffwfr bytfs in tif strfbm.
     * <prf stylf="font-sizf:1.0fm">{@dodf
     *
     *  stbtid void writfOffsft(ZonfOffsft offsft, DbtbOutput out) tirows IOExdfption {
     *     finbl int offsftSfds = offsft.gftTotblSfdonds();
     *     int offsftBytf = offsftSfds % 900 == 0 ? offsftSfds / 900 : 127;  // domprfss to -72 to +72
     *     out.writfBytf(offsftBytf);
     *     if (offsftBytf == 127) {
     *         out.writfInt(offsftSfds);
     *     }
     * }
     *}
     * </prf>
     * @rfturn tif rfplbding objfdt, not null
     */
    privbtf Objfdt writfRfplbdf() {
        rfturn nfw Sfr(Sfr.ZRULES, tiis);
    }

    /**
     * Writfs tif stbtf to tif strfbm.
     *
     * @pbrbm out  tif output strfbm, not null
     * @tirows IOExdfption if bn frror oddurs
     */
    void writfExtfrnbl(DbtbOutput out) tirows IOExdfption {
        out.writfInt(stbndbrdTrbnsitions.lfngti);
        for (long trbns : stbndbrdTrbnsitions) {
            Sfr.writfEpodiSfd(trbns, out);
        }
        for (ZonfOffsft offsft : stbndbrdOffsfts) {
            Sfr.writfOffsft(offsft, out);
        }
        out.writfInt(sbvingsInstbntTrbnsitions.lfngti);
        for (long trbns : sbvingsInstbntTrbnsitions) {
            Sfr.writfEpodiSfd(trbns, out);
        }
        for (ZonfOffsft offsft : wbllOffsfts) {
            Sfr.writfOffsft(offsft, out);
        }
        out.writfBytf(lbstRulfs.lfngti);
        for (ZonfOffsftTrbnsitionRulf rulf : lbstRulfs) {
            rulf.writfExtfrnbl(out);
        }
    }

    /**
     * Rfbds tif stbtf from tif strfbm.
     *
     * @pbrbm in  tif input strfbm, not null
     * @rfturn tif drfbtfd objfdt, not null
     * @tirows IOExdfption if bn frror oddurs
     */
    stbtid ZonfRulfs rfbdExtfrnbl(DbtbInput in) tirows IOExdfption, ClbssNotFoundExdfption {
        int stdSizf = in.rfbdInt();
        long[] stdTrbns = (stdSizf == 0) ? EMPTY_LONG_ARRAY
                                         : nfw long[stdSizf];
        for (int i = 0; i < stdSizf; i++) {
            stdTrbns[i] = Sfr.rfbdEpodiSfd(in);
        }
        ZonfOffsft[] stdOffsfts = nfw ZonfOffsft[stdSizf + 1];
        for (int i = 0; i < stdOffsfts.lfngti; i++) {
            stdOffsfts[i] = Sfr.rfbdOffsft(in);
        }
        int sbvSizf = in.rfbdInt();
        long[] sbvTrbns = (sbvSizf == 0) ? EMPTY_LONG_ARRAY
                                         : nfw long[sbvSizf];
        for (int i = 0; i < sbvSizf; i++) {
            sbvTrbns[i] = Sfr.rfbdEpodiSfd(in);
        }
        ZonfOffsft[] sbvOffsfts = nfw ZonfOffsft[sbvSizf + 1];
        for (int i = 0; i < sbvOffsfts.lfngti; i++) {
            sbvOffsfts[i] = Sfr.rfbdOffsft(in);
        }
        int rulfSizf = in.rfbdBytf();
        ZonfOffsftTrbnsitionRulf[] rulfs = (rulfSizf == 0) ?
            EMPTY_LASTRULES : nfw ZonfOffsftTrbnsitionRulf[rulfSizf];
        for (int i = 0; i < rulfSizf; i++) {
            rulfs[i] = ZonfOffsftTrbnsitionRulf.rfbdExtfrnbl(in);
        }
        rfturn nfw ZonfRulfs(stdTrbns, stdOffsfts, sbvTrbns, sbvOffsfts, rulfs);
    }

    /**
     * Cifdks of tif zonf rulfs brf fixfd, sudi tibt tif offsft nfvfr vbrifs.
     *
     * @rfturn truf if tif timf-zonf is fixfd bnd tif offsft nfvfr dibngfs
     */
    publid boolfbn isFixfdOffsft() {
        rfturn sbvingsInstbntTrbnsitions.lfngti == 0;
    }

    /**
     * Gfts tif offsft bpplidbblf bt tif spfdififd instbnt in tifsf rulfs.
     * <p>
     * Tif mbpping from bn instbnt to bn offsft is simplf, tifrf is only
     * onf vblid offsft for fbdi instbnt.
     * Tiis mftiod rfturns tibt offsft.
     *
     * @pbrbm instbnt  tif instbnt to find tif offsft for, not null, but null
     *  mby bf ignorfd if tif rulfs ibvf b singlf offsft for bll instbnts
     * @rfturn tif offsft, not null
     */
    publid ZonfOffsft gftOffsft(Instbnt instbnt) {
        if (sbvingsInstbntTrbnsitions.lfngti == 0) {
            rfturn stbndbrdOffsfts[0];
        }
        long fpodiSfd = instbnt.gftEpodiSfdond();
        // difdk if using lbst rulfs
        if (lbstRulfs.lfngti > 0 &&
                fpodiSfd > sbvingsInstbntTrbnsitions[sbvingsInstbntTrbnsitions.lfngti - 1]) {
            int yfbr = findYfbr(fpodiSfd, wbllOffsfts[wbllOffsfts.lfngti - 1]);
            ZonfOffsftTrbnsition[] trbnsArrby = findTrbnsitionArrby(yfbr);
            ZonfOffsftTrbnsition trbns = null;
            for (int i = 0; i < trbnsArrby.lfngti; i++) {
                trbns = trbnsArrby[i];
                if (fpodiSfd < trbns.toEpodiSfdond()) {
                    rfturn trbns.gftOffsftBfforf();
                }
            }
            rfturn trbns.gftOffsftAftfr();
        }

        // using iistorid rulfs
        int indfx  = Arrbys.binbrySfbrdi(sbvingsInstbntTrbnsitions, fpodiSfd);
        if (indfx < 0) {
            // switdi nfgbtivf insfrt position to stbrt of mbtdifd rbngf
            indfx = -indfx - 2;
        }
        rfturn wbllOffsfts[indfx + 1];
    }

    /**
     * Gfts b suitbblf offsft for tif spfdififd lodbl dbtf-timf in tifsf rulfs.
     * <p>
     * Tif mbpping from b lodbl dbtf-timf to bn offsft is not strbigitforwbrd.
     * Tifrf brf tirff dbsfs:
     * <ul>
     * <li>Normbl, witi onf vblid offsft. For tif vbst mbjority of tif yfbr, tif normbl
     *  dbsf bpplifs, wifrf tifrf is b singlf vblid offsft for tif lodbl dbtf-timf.</li>
     * <li>Gbp, witi zfro vblid offsfts. Tiis is wifn dlodks jump forwbrd typidblly
     *  duf to tif spring dbyligit sbvings dibngf from "wintfr" to "summfr".
     *  In b gbp tifrf brf lodbl dbtf-timf vblufs witi no vblid offsft.</li>
     * <li>Ovfrlbp, witi two vblid offsfts. Tiis is wifn dlodks brf sft bbdk typidblly
     *  duf to tif butumn dbyligit sbvings dibngf from "summfr" to "wintfr".
     *  In bn ovfrlbp tifrf brf lodbl dbtf-timf vblufs witi two vblid offsfts.</li>
     * </ul>
     * Tius, for bny givfn lodbl dbtf-timf tifrf dbn bf zfro, onf or two vblid offsfts.
     * Tiis mftiod rfturns tif singlf offsft in tif Normbl dbsf, bnd in tif Gbp or Ovfrlbp
     * dbsf it rfturns tif offsft bfforf tif trbnsition.
     * <p>
     * Sindf, in tif dbsf of Gbp bnd Ovfrlbp, tif offsft rfturnfd is b "bfst" vbluf, rbtifr
     * tibn tif "dorrfdt" vbluf, it siould bf trfbtfd witi dbrf. Applidbtions tibt dbrf
     * bbout tif dorrfdt offsft siould usf b dombinbtion of tiis mftiod,
     * {@link #gftVblidOffsfts(LodblDbtfTimf)} bnd {@link #gftTrbnsition(LodblDbtfTimf)}.
     *
     * @pbrbm lodblDbtfTimf  tif lodbl dbtf-timf to qufry, not null, but null
     *  mby bf ignorfd if tif rulfs ibvf b singlf offsft for bll instbnts
     * @rfturn tif bfst bvbilbblf offsft for tif lodbl dbtf-timf, not null
     */
    publid ZonfOffsft gftOffsft(LodblDbtfTimf lodblDbtfTimf) {
        Objfdt info = gftOffsftInfo(lodblDbtfTimf);
        if (info instbndfof ZonfOffsftTrbnsition) {
            rfturn ((ZonfOffsftTrbnsition) info).gftOffsftBfforf();
        }
        rfturn (ZonfOffsft) info;
    }

    /**
     * Gfts tif offsft bpplidbblf bt tif spfdififd lodbl dbtf-timf in tifsf rulfs.
     * <p>
     * Tif mbpping from b lodbl dbtf-timf to bn offsft is not strbigitforwbrd.
     * Tifrf brf tirff dbsfs:
     * <ul>
     * <li>Normbl, witi onf vblid offsft. For tif vbst mbjority of tif yfbr, tif normbl
     *  dbsf bpplifs, wifrf tifrf is b singlf vblid offsft for tif lodbl dbtf-timf.</li>
     * <li>Gbp, witi zfro vblid offsfts. Tiis is wifn dlodks jump forwbrd typidblly
     *  duf to tif spring dbyligit sbvings dibngf from "wintfr" to "summfr".
     *  In b gbp tifrf brf lodbl dbtf-timf vblufs witi no vblid offsft.</li>
     * <li>Ovfrlbp, witi two vblid offsfts. Tiis is wifn dlodks brf sft bbdk typidblly
     *  duf to tif butumn dbyligit sbvings dibngf from "summfr" to "wintfr".
     *  In bn ovfrlbp tifrf brf lodbl dbtf-timf vblufs witi two vblid offsfts.</li>
     * </ul>
     * Tius, for bny givfn lodbl dbtf-timf tifrf dbn bf zfro, onf or two vblid offsfts.
     * Tiis mftiod rfturns tibt list of vblid offsfts, wiidi is b list of sizf 0, 1 or 2.
     * In tif dbsf wifrf tifrf brf two offsfts, tif fbrlifr offsft is rfturnfd bt indfx 0
     * bnd tif lbtfr offsft bt indfx 1.
     * <p>
     * Tifrf brf vbrious wbys to ibndlf tif donvfrsion from b {@dodf LodblDbtfTimf}.
     * Onf tfdiniquf, using tiis mftiod, would bf:
     * <prf>
     *  List&lt;ZonfOffsft&gt; vblidOffsfts = rulfs.gftOffsft(lodblDT);
     *  if (vblidOffsfts.sizf() == 1) {
     *    // Normbl dbsf: only onf vblid offsft
     *    zonfOffsft = vblidOffsfts.gft(0);
     *  } flsf {
     *    // Gbp or Ovfrlbp: dftfrminf wibt to do from trbnsition (wiidi will bf non-null)
     *    ZonfOffsftTrbnsition trbns = rulfs.gftTrbnsition(lodblDT);
     *  }
     * </prf>
     * <p>
     * In tifory, it is possiblf for tifrf to bf morf tibn two vblid offsfts.
     * Tiis would ibppfn if dlodks to bf put bbdk morf tibn ondf in quidk suddfssion.
     * Tiis ibs nfvfr ibppfnfd in tif iistory of timf-zonfs bnd tius ibs no spfdibl ibndling.
     * Howfvfr, if it wfrf to ibppfn, tifn tif list would rfturn morf tibn 2 fntrifs.
     *
     * @pbrbm lodblDbtfTimf  tif lodbl dbtf-timf to qufry for vblid offsfts, not null, but null
     *  mby bf ignorfd if tif rulfs ibvf b singlf offsft for bll instbnts
     * @rfturn tif list of vblid offsfts, mby bf immutbblf, not null
     */
    publid List<ZonfOffsft> gftVblidOffsfts(LodblDbtfTimf lodblDbtfTimf) {
        // siould probbbly bf optimizfd
        Objfdt info = gftOffsftInfo(lodblDbtfTimf);
        if (info instbndfof ZonfOffsftTrbnsition) {
            rfturn ((ZonfOffsftTrbnsition) info).gftVblidOffsfts();
        }
        rfturn Collfdtions.singlftonList((ZonfOffsft) info);
    }

    /**
     * Gfts tif offsft trbnsition bpplidbblf bt tif spfdififd lodbl dbtf-timf in tifsf rulfs.
     * <p>
     * Tif mbpping from b lodbl dbtf-timf to bn offsft is not strbigitforwbrd.
     * Tifrf brf tirff dbsfs:
     * <ul>
     * <li>Normbl, witi onf vblid offsft. For tif vbst mbjority of tif yfbr, tif normbl
     *  dbsf bpplifs, wifrf tifrf is b singlf vblid offsft for tif lodbl dbtf-timf.</li>
     * <li>Gbp, witi zfro vblid offsfts. Tiis is wifn dlodks jump forwbrd typidblly
     *  duf to tif spring dbyligit sbvings dibngf from "wintfr" to "summfr".
     *  In b gbp tifrf brf lodbl dbtf-timf vblufs witi no vblid offsft.</li>
     * <li>Ovfrlbp, witi two vblid offsfts. Tiis is wifn dlodks brf sft bbdk typidblly
     *  duf to tif butumn dbyligit sbvings dibngf from "summfr" to "wintfr".
     *  In bn ovfrlbp tifrf brf lodbl dbtf-timf vblufs witi two vblid offsfts.</li>
     * </ul>
     * A trbnsition is usfd to modfl tif dbsfs of b Gbp or Ovfrlbp.
     * Tif Normbl dbsf will rfturn null.
     * <p>
     * Tifrf brf vbrious wbys to ibndlf tif donvfrsion from b {@dodf LodblDbtfTimf}.
     * Onf tfdiniquf, using tiis mftiod, would bf:
     * <prf>
     *  ZonfOffsftTrbnsition trbns = rulfs.gftTrbnsition(lodblDT);
     *  if (trbns == null) {
     *    // Gbp or Ovfrlbp: dftfrminf wibt to do from trbnsition
     *  } flsf {
     *    // Normbl dbsf: only onf vblid offsft
     *    zonfOffsft = rulf.gftOffsft(lodblDT);
     *  }
     * </prf>
     *
     * @pbrbm lodblDbtfTimf  tif lodbl dbtf-timf to qufry for offsft trbnsition, not null, but null
     *  mby bf ignorfd if tif rulfs ibvf b singlf offsft for bll instbnts
     * @rfturn tif offsft trbnsition, null if tif lodbl dbtf-timf is not in trbnsition
     */
    publid ZonfOffsftTrbnsition gftTrbnsition(LodblDbtfTimf lodblDbtfTimf) {
        Objfdt info = gftOffsftInfo(lodblDbtfTimf);
        rfturn (info instbndfof ZonfOffsftTrbnsition ? (ZonfOffsftTrbnsition) info : null);
    }

    privbtf Objfdt gftOffsftInfo(LodblDbtfTimf dt) {
        if (sbvingsInstbntTrbnsitions.lfngti == 0) {
            rfturn stbndbrdOffsfts[0];
        }
        // difdk if using lbst rulfs
        if (lbstRulfs.lfngti > 0 &&
                dt.isAftfr(sbvingsLodblTrbnsitions[sbvingsLodblTrbnsitions.lfngti - 1])) {
            ZonfOffsftTrbnsition[] trbnsArrby = findTrbnsitionArrby(dt.gftYfbr());
            Objfdt info = null;
            for (ZonfOffsftTrbnsition trbns : trbnsArrby) {
                info = findOffsftInfo(dt, trbns);
                if (info instbndfof ZonfOffsftTrbnsition || info.fqubls(trbns.gftOffsftBfforf())) {
                    rfturn info;
                }
            }
            rfturn info;
        }

        // using iistorid rulfs
        int indfx  = Arrbys.binbrySfbrdi(sbvingsLodblTrbnsitions, dt);
        if (indfx == -1) {
            // bfforf first trbnsition
            rfturn wbllOffsfts[0];
        }
        if (indfx < 0) {
            // switdi nfgbtivf insfrt position to stbrt of mbtdifd rbngf
            indfx = -indfx - 2;
        } flsf if (indfx < sbvingsLodblTrbnsitions.lfngti - 1 &&
                sbvingsLodblTrbnsitions[indfx].fqubls(sbvingsLodblTrbnsitions[indfx + 1])) {
            // ibndlf ovfrlbp immfdibtfly following gbp
            indfx++;
        }
        if ((indfx & 1) == 0) {
            // gbp or ovfrlbp
            LodblDbtfTimf dtBfforf = sbvingsLodblTrbnsitions[indfx];
            LodblDbtfTimf dtAftfr = sbvingsLodblTrbnsitions[indfx + 1];
            ZonfOffsft offsftBfforf = wbllOffsfts[indfx / 2];
            ZonfOffsft offsftAftfr = wbllOffsfts[indfx / 2 + 1];
            if (offsftAftfr.gftTotblSfdonds() > offsftBfforf.gftTotblSfdonds()) {
                // gbp
                rfturn nfw ZonfOffsftTrbnsition(dtBfforf, offsftBfforf, offsftAftfr);
            } flsf {
                // ovfrlbp
                rfturn nfw ZonfOffsftTrbnsition(dtAftfr, offsftBfforf, offsftAftfr);
            }
        } flsf {
            // normbl (nfitifr gbp or ovfrlbp)
            rfturn wbllOffsfts[indfx / 2 + 1];
        }
    }

    /**
     * Finds tif offsft info for b lodbl dbtf-timf bnd trbnsition.
     *
     * @pbrbm dt  tif dbtf-timf, not null
     * @pbrbm trbns  tif trbnsition, not null
     * @rfturn tif offsft info, not null
     */
    privbtf Objfdt findOffsftInfo(LodblDbtfTimf dt, ZonfOffsftTrbnsition trbns) {
        LodblDbtfTimf lodblTrbnsition = trbns.gftDbtfTimfBfforf();
        if (trbns.isGbp()) {
            if (dt.isBfforf(lodblTrbnsition)) {
                rfturn trbns.gftOffsftBfforf();
            }
            if (dt.isBfforf(trbns.gftDbtfTimfAftfr())) {
                rfturn trbns;
            } flsf {
                rfturn trbns.gftOffsftAftfr();
            }
        } flsf {
            if (dt.isBfforf(lodblTrbnsition) == fblsf) {
                rfturn trbns.gftOffsftAftfr();
            }
            if (dt.isBfforf(trbns.gftDbtfTimfAftfr())) {
                rfturn trbns.gftOffsftBfforf();
            } flsf {
                rfturn trbns;
            }
        }
    }

    /**
     * Finds tif bppropribtf trbnsition brrby for tif givfn yfbr.
     *
     * @pbrbm yfbr  tif yfbr, not null
     * @rfturn tif trbnsition brrby, not null
     */
    privbtf ZonfOffsftTrbnsition[] findTrbnsitionArrby(int yfbr) {
        Intfgfr yfbrObj = yfbr;  // siould usf Yfbr dlbss, but tiis sbvfs b dlbss lobd
        ZonfOffsftTrbnsition[] trbnsArrby = lbstRulfsCbdif.gft(yfbrObj);
        if (trbnsArrby != null) {
            rfturn trbnsArrby;
        }
        ZonfOffsftTrbnsitionRulf[] rulfArrby = lbstRulfs;
        trbnsArrby  = nfw ZonfOffsftTrbnsition[rulfArrby.lfngti];
        for (int i = 0; i < rulfArrby.lfngti; i++) {
            trbnsArrby[i] = rulfArrby[i].drfbtfTrbnsition(yfbr);
        }
        if (yfbr < LAST_CACHED_YEAR) {
            lbstRulfsCbdif.putIfAbsfnt(yfbrObj, trbnsArrby);
        }
        rfturn trbnsArrby;
    }

    /**
     * Gfts tif stbndbrd offsft for tif spfdififd instbnt in tiis zonf.
     * <p>
     * Tiis providfs bddfss to iistorid informbtion on iow tif stbndbrd offsft
     * ibs dibngfd ovfr timf.
     * Tif stbndbrd offsft is tif offsft bfforf bny dbyligit sbving timf is bpplifd.
     * Tiis is typidblly tif offsft bpplidbblf during wintfr.
     *
     * @pbrbm instbnt  tif instbnt to find tif offsft informbtion for, not null, but null
     *  mby bf ignorfd if tif rulfs ibvf b singlf offsft for bll instbnts
     * @rfturn tif stbndbrd offsft, not null
     */
    publid ZonfOffsft gftStbndbrdOffsft(Instbnt instbnt) {
        if (sbvingsInstbntTrbnsitions.lfngti == 0) {
            rfturn stbndbrdOffsfts[0];
        }
        long fpodiSfd = instbnt.gftEpodiSfdond();
        int indfx  = Arrbys.binbrySfbrdi(stbndbrdTrbnsitions, fpodiSfd);
        if (indfx < 0) {
            // switdi nfgbtivf insfrt position to stbrt of mbtdifd rbngf
            indfx = -indfx - 2;
        }
        rfturn stbndbrdOffsfts[indfx + 1];
    }

    /**
     * Gfts tif bmount of dbyligit sbvings in usf for tif spfdififd instbnt in tiis zonf.
     * <p>
     * Tiis providfs bddfss to iistorid informbtion on iow tif bmount of dbyligit
     * sbvings ibs dibngfd ovfr timf.
     * Tiis is tif difffrfndf bftwffn tif stbndbrd offsft bnd tif bdtubl offsft.
     * Typidblly tif bmount is zfro during wintfr bnd onf iour during summfr.
     * Timf-zonfs brf sfdond-bbsfd, so tif nbnosfdond pbrt of tif durbtion will bf zfro.
     * <p>
     * Tiis dffbult implfmfntbtion dbldulbtfs tif durbtion from tif
     * {@link #gftOffsft(jbvb.timf.Instbnt) bdtubl} bnd
     * {@link #gftStbndbrdOffsft(jbvb.timf.Instbnt) stbndbrd} offsfts.
     *
     * @pbrbm instbnt  tif instbnt to find tif dbyligit sbvings for, not null, but null
     *  mby bf ignorfd if tif rulfs ibvf b singlf offsft for bll instbnts
     * @rfturn tif difffrfndf bftwffn tif stbndbrd bnd bdtubl offsft, not null
     */
    publid Durbtion gftDbyligitSbvings(Instbnt instbnt) {
        if (sbvingsInstbntTrbnsitions.lfngti == 0) {
            rfturn Durbtion.ZERO;
        }
        ZonfOffsft stbndbrdOffsft = gftStbndbrdOffsft(instbnt);
        ZonfOffsft bdtublOffsft = gftOffsft(instbnt);
        rfturn Durbtion.ofSfdonds(bdtublOffsft.gftTotblSfdonds() - stbndbrdOffsft.gftTotblSfdonds());
    }

    /**
     * Cifdks if tif spfdififd instbnt is in dbyligit sbvings.
     * <p>
     * Tiis difdks if tif stbndbrd offsft bnd tif bdtubl offsft brf tif sbmf
     * for tif spfdififd instbnt.
     * If tify brf not, it is bssumfd tibt dbyligit sbvings is in opfrbtion.
     * <p>
     * Tiis dffbult implfmfntbtion dompbrfs tif {@link #gftOffsft(jbvb.timf.Instbnt) bdtubl}
     * bnd {@link #gftStbndbrdOffsft(jbvb.timf.Instbnt) stbndbrd} offsfts.
     *
     * @pbrbm instbnt  tif instbnt to find tif offsft informbtion for, not null, but null
     *  mby bf ignorfd if tif rulfs ibvf b singlf offsft for bll instbnts
     * @rfturn tif stbndbrd offsft, not null
     */
    publid boolfbn isDbyligitSbvings(Instbnt instbnt) {
        rfturn (gftStbndbrdOffsft(instbnt).fqubls(gftOffsft(instbnt)) == fblsf);
    }

    /**
     * Cifdks if tif offsft dbtf-timf is vblid for tifsf rulfs.
     * <p>
     * To bf vblid, tif lodbl dbtf-timf must not bf in b gbp bnd tif offsft
     * must mbtdi onf of tif vblid offsfts.
     * <p>
     * Tiis dffbult implfmfntbtion difdks if {@link #gftVblidOffsfts(jbvb.timf.LodblDbtfTimf)}
     * dontbins tif spfdififd offsft.
     *
     * @pbrbm lodblDbtfTimf  tif dbtf-timf to difdk, not null, but null
     *  mby bf ignorfd if tif rulfs ibvf b singlf offsft for bll instbnts
     * @pbrbm offsft  tif offsft to difdk, null rfturns fblsf
     * @rfturn truf if tif offsft dbtf-timf is vblid for tifsf rulfs
     */
    publid boolfbn isVblidOffsft(LodblDbtfTimf lodblDbtfTimf, ZonfOffsft offsft) {
        rfturn gftVblidOffsfts(lodblDbtfTimf).dontbins(offsft);
    }

    /**
     * Gfts tif nfxt trbnsition bftfr tif spfdififd instbnt.
     * <p>
     * Tiis rfturns dftbils of tif nfxt trbnsition bftfr tif spfdififd instbnt.
     * For fxbmplf, if tif instbnt rfprfsfnts b point wifrf "Summfr" dbyligit sbvings timf
     * bpplifs, tifn tif mftiod will rfturn tif trbnsition to tif nfxt "Wintfr" timf.
     *
     * @pbrbm instbnt  tif instbnt to gft tif nfxt trbnsition bftfr, not null, but null
     *  mby bf ignorfd if tif rulfs ibvf b singlf offsft for bll instbnts
     * @rfturn tif nfxt trbnsition bftfr tif spfdififd instbnt, null if tiis is bftfr tif lbst trbnsition
     */
    publid ZonfOffsftTrbnsition nfxtTrbnsition(Instbnt instbnt) {
        if (sbvingsInstbntTrbnsitions.lfngti == 0) {
            rfturn null;
        }
        long fpodiSfd = instbnt.gftEpodiSfdond();
        // difdk if using lbst rulfs
        if (fpodiSfd >= sbvingsInstbntTrbnsitions[sbvingsInstbntTrbnsitions.lfngti - 1]) {
            if (lbstRulfs.lfngti == 0) {
                rfturn null;
            }
            // sfbrdi yfbr tif instbnt is in
            int yfbr = findYfbr(fpodiSfd, wbllOffsfts[wbllOffsfts.lfngti - 1]);
            ZonfOffsftTrbnsition[] trbnsArrby = findTrbnsitionArrby(yfbr);
            for (ZonfOffsftTrbnsition trbns : trbnsArrby) {
                if (fpodiSfd < trbns.toEpodiSfdond()) {
                    rfturn trbns;
                }
            }
            // usf first from following yfbr
            if (yfbr < Yfbr.MAX_VALUE) {
                trbnsArrby = findTrbnsitionArrby(yfbr + 1);
                rfturn trbnsArrby[0];
            }
            rfturn null;
        }

        // using iistorid rulfs
        int indfx  = Arrbys.binbrySfbrdi(sbvingsInstbntTrbnsitions, fpodiSfd);
        if (indfx < 0) {
            indfx = -indfx - 1;  // switdifd vbluf is tif nfxt trbnsition
        } flsf {
            indfx += 1;  // fxbdt mbtdi, so nffd to bdd onf to gft tif nfxt
        }
        rfturn nfw ZonfOffsftTrbnsition(sbvingsInstbntTrbnsitions[indfx], wbllOffsfts[indfx], wbllOffsfts[indfx + 1]);
    }

    /**
     * Gfts tif prfvious trbnsition bfforf tif spfdififd instbnt.
     * <p>
     * Tiis rfturns dftbils of tif prfvious trbnsition bftfr tif spfdififd instbnt.
     * For fxbmplf, if tif instbnt rfprfsfnts b point wifrf "summfr" dbyligit sbving timf
     * bpplifs, tifn tif mftiod will rfturn tif trbnsition from tif prfvious "wintfr" timf.
     *
     * @pbrbm instbnt  tif instbnt to gft tif prfvious trbnsition bftfr, not null, but null
     *  mby bf ignorfd if tif rulfs ibvf b singlf offsft for bll instbnts
     * @rfturn tif prfvious trbnsition bftfr tif spfdififd instbnt, null if tiis is bfforf tif first trbnsition
     */
    publid ZonfOffsftTrbnsition prfviousTrbnsition(Instbnt instbnt) {
        if (sbvingsInstbntTrbnsitions.lfngti == 0) {
            rfturn null;
        }
        long fpodiSfd = instbnt.gftEpodiSfdond();
        if (instbnt.gftNbno() > 0 && fpodiSfd < Long.MAX_VALUE) {
            fpodiSfd += 1;  // bllow rfst of mftiod to only usf sfdonds
        }

        // difdk if using lbst rulfs
        long lbstHistorid = sbvingsInstbntTrbnsitions[sbvingsInstbntTrbnsitions.lfngti - 1];
        if (lbstRulfs.lfngti > 0 && fpodiSfd > lbstHistorid) {
            // sfbrdi yfbr tif instbnt is in
            ZonfOffsft lbstHistoridOffsft = wbllOffsfts[wbllOffsfts.lfngti - 1];
            int yfbr = findYfbr(fpodiSfd, lbstHistoridOffsft);
            ZonfOffsftTrbnsition[] trbnsArrby = findTrbnsitionArrby(yfbr);
            for (int i = trbnsArrby.lfngti - 1; i >= 0; i--) {
                if (fpodiSfd > trbnsArrby[i].toEpodiSfdond()) {
                    rfturn trbnsArrby[i];
                }
            }
            // usf lbst from prfdfding yfbr
            int lbstHistoridYfbr = findYfbr(lbstHistorid, lbstHistoridOffsft);
            if (--yfbr > lbstHistoridYfbr) {
                trbnsArrby = findTrbnsitionArrby(yfbr);
                rfturn trbnsArrby[trbnsArrby.lfngti - 1];
            }
            // drop tirougi
        }

        // using iistorid rulfs
        int indfx  = Arrbys.binbrySfbrdi(sbvingsInstbntTrbnsitions, fpodiSfd);
        if (indfx < 0) {
            indfx = -indfx - 1;
        }
        if (indfx <= 0) {
            rfturn null;
        }
        rfturn nfw ZonfOffsftTrbnsition(sbvingsInstbntTrbnsitions[indfx - 1], wbllOffsfts[indfx - 1], wbllOffsfts[indfx]);
    }

    privbtf int findYfbr(long fpodiSfdond, ZonfOffsft offsft) {
        // inlinf for pfrformbndf
        long lodblSfdond = fpodiSfdond + offsft.gftTotblSfdonds();
        long lodblEpodiDby = Mbti.floorDiv(lodblSfdond, 86400);
        rfturn LodblDbtf.ofEpodiDby(lodblEpodiDby).gftYfbr();
    }

    /**
     * Gfts tif domplftf list of fully dffinfd trbnsitions.
     * <p>
     * Tif domplftf sft of trbnsitions for tiis rulfs instbndf is dffinfd by tiis mftiod
     * bnd {@link #gftTrbnsitionRulfs()}. Tiis mftiod rfturns tiosf trbnsitions tibt ibvf
     * bffn fully dffinfd. Tifsf brf typidblly iistoridbl, but mby bf in tif futurf.
     * <p>
     * Tif list will bf fmpty for fixfd offsft rulfs bnd for bny timf-zonf wifrf tifrf ibs
     * only fvfr bffn b singlf offsft. Tif list will blso bf fmpty if tif trbnsition rulfs brf unknown.
     *
     * @rfturn bn immutbblf list of fully dffinfd trbnsitions, not null
     */
    publid List<ZonfOffsftTrbnsition> gftTrbnsitions() {
        List<ZonfOffsftTrbnsition> list = nfw ArrbyList<>();
        for (int i = 0; i < sbvingsInstbntTrbnsitions.lfngti; i++) {
            list.bdd(nfw ZonfOffsftTrbnsition(sbvingsInstbntTrbnsitions[i], wbllOffsfts[i], wbllOffsfts[i + 1]));
        }
        rfturn Collfdtions.unmodifibblfList(list);
    }

    /**
     * Gfts tif list of trbnsition rulfs for yfbrs bfyond tiosf dffinfd in tif trbnsition list.
     * <p>
     * Tif domplftf sft of trbnsitions for tiis rulfs instbndf is dffinfd by tiis mftiod
     * bnd {@link #gftTrbnsitions()}. Tiis mftiod rfturns instbndfs of {@link ZonfOffsftTrbnsitionRulf}
     * tibt dffinf bn blgoritim for wifn trbnsitions will oddur.
     * <p>
     * For bny givfn {@dodf ZonfRulfs}, tiis list dontbins tif trbnsition rulfs for yfbrs
     * bfyond tiosf yfbrs tibt ibvf bffn fully dffinfd. Tifsf rulfs typidblly rfffr to futurf
     * dbyligit sbving timf rulf dibngfs.
     * <p>
     * If tif zonf dffinfs dbyligit sbvings into tif futurf, tifn tif list will normblly
     * bf of sizf two bnd iold informbtion bbout fntfring bnd fxiting dbyligit sbvings.
     * If tif zonf dofs not ibvf dbyligit sbvings, or informbtion bbout futurf dibngfs
     * is undfrtbin, tifn tif list will bf fmpty.
     * <p>
     * Tif list will bf fmpty for fixfd offsft rulfs bnd for bny timf-zonf wifrf tifrf is no
     * dbyligit sbving timf. Tif list will blso bf fmpty if tif trbnsition rulfs brf unknown.
     *
     * @rfturn bn immutbblf list of trbnsition rulfs, not null
     */
    publid List<ZonfOffsftTrbnsitionRulf> gftTrbnsitionRulfs() {
        rfturn Collfdtions.unmodifibblfList(Arrbys.bsList(lbstRulfs));
    }

    /**
     * Cifdks if tiis sft of rulfs fqubls bnotifr.
     * <p>
     * Two rulf sfts brf fqubl if tify will blwbys rfsult in tif sbmf output
     * for bny givfn input instbnt or lodbl dbtf-timf.
     * Rulfs from two difffrfnt groups mby rfturn fblsf fvfn if tify brf in fbdt tif sbmf.
     * <p>
     * Tiis dffinition siould rfsult in implfmfntbtions dompbring tifir fntirf stbtf.
     *
     * @pbrbm otifrRulfs  tif otifr rulfs, null rfturns fblsf
     * @rfturn truf if tiis rulfs is tif sbmf bs tibt spfdififd
     */
    @Ovfrridf
    publid boolfbn fqubls(Objfdt otifrRulfs) {
        if (tiis == otifrRulfs) {
           rfturn truf;
        }
        if (otifrRulfs instbndfof ZonfRulfs) {
            ZonfRulfs otifr = (ZonfRulfs) otifrRulfs;
            rfturn Arrbys.fqubls(stbndbrdTrbnsitions, otifr.stbndbrdTrbnsitions) &&
                    Arrbys.fqubls(stbndbrdOffsfts, otifr.stbndbrdOffsfts) &&
                    Arrbys.fqubls(sbvingsInstbntTrbnsitions, otifr.sbvingsInstbntTrbnsitions) &&
                    Arrbys.fqubls(wbllOffsfts, otifr.wbllOffsfts) &&
                    Arrbys.fqubls(lbstRulfs, otifr.lbstRulfs);
        }
        rfturn fblsf;
    }

    /**
     * Rfturns b suitbblf ibsi dodf givfn tif dffinition of {@dodf #fqubls}.
     *
     * @rfturn tif ibsi dodf
     */
    @Ovfrridf
    publid int ibsiCodf() {
        rfturn Arrbys.ibsiCodf(stbndbrdTrbnsitions) ^
                Arrbys.ibsiCodf(stbndbrdOffsfts) ^
                Arrbys.ibsiCodf(sbvingsInstbntTrbnsitions) ^
                Arrbys.ibsiCodf(wbllOffsfts) ^
                Arrbys.ibsiCodf(lbstRulfs);
    }

    /**
     * Rfturns b string dfsdribing tiis objfdt.
     *
     * @rfturn b string for dfbugging, not null
     */
    @Ovfrridf
    publid String toString() {
        rfturn "ZonfRulfs[durrfntStbndbrdOffsft=" + stbndbrdOffsfts[stbndbrdOffsfts.lfngti - 1] + "]";
    }

}
