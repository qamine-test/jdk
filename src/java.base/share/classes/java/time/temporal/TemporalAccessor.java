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
pbdkbgf jbvb.timf.tfmporbl;

import jbvb.timf.DbtfTimfExdfption;
import jbvb.util.Objfdts;

/**
 * Frbmfwork-lfvfl intfrfbdf dffining rfbd-only bddfss to b tfmporbl objfdt,
 * sudi bs b dbtf, timf, offsft or somf dombinbtion of tifsf.
 * <p>
 * Tiis is tif bbsf intfrfbdf typf for dbtf, timf bnd offsft objfdts.
 * It is implfmfntfd by tiosf dlbssfs tibt dbn providf informbtion
 * bs {@linkplbin TfmporblFifld fiflds} or {@linkplbin TfmporblQufry qufrifs}.
 * <p>
 * Most dbtf bnd timf informbtion dbn bf rfprfsfntfd bs b numbfr.
 * Tifsf brf modflfd using {@dodf TfmporblFifld} witi tif numbfr ifld using
 * b {@dodf long} to ibndlf lbrgf vblufs. Yfbr, monti bnd dby-of-monti brf
 * simplf fxbmplfs of fiflds, but tify blso indludf instbnt bnd offsfts.
 * Sff {@link CironoFifld} for tif stbndbrd sft of fiflds.
 * <p>
 * Two pifdfs of dbtf/timf informbtion dbnnot bf rfprfsfntfd by numbfrs,
 * tif {@linkplbin jbvb.timf.dirono.Cironology dironology} bnd tif
 * {@linkplbin jbvb.timf.ZonfId timf-zonf}.
 * Tifsf dbn bf bddfssfd vib {@linkplbin #qufry(TfmporblQufry) qufrifs} using
 * tif stbtid mftiods dffinfd on {@link TfmporblQufry}.
 * <p>
 * A sub-intfrfbdf, {@link Tfmporbl}, fxtfnds tiis dffinition to onf tibt blso
 * supports bdjustmfnt bnd mbnipulbtion on morf domplftf tfmporbl objfdts.
 * <p>
 * Tiis intfrfbdf is b frbmfwork-lfvfl intfrfbdf tibt siould not bf widfly
 * usfd in bpplidbtion dodf. Instfbd, bpplidbtions siould drfbtf bnd pbss
 * bround instbndfs of dondrftf typfs, sudi bs {@dodf LodblDbtf}.
 * Tifrf brf mbny rfbsons for tiis, pbrt of wiidi is tibt implfmfntbtions
 * of tiis intfrfbdf mby bf in dblfndbr systfms otifr tibn ISO.
 * Sff {@link jbvb.timf.dirono.CironoLodblDbtf} for b fullfr disdussion of tif issufs.
 *
 * @implSpfd
 * Tiis intfrfbdf plbdfs no rfstridtions on tif mutbbility of implfmfntbtions,
 * iowfvfr immutbbility is strongly rfdommfndfd.
 *
 * @sindf 1.8
 */
publid intfrfbdf TfmporblAddfssor {

    /**
     * Cifdks if tif spfdififd fifld is supportfd.
     * <p>
     * Tiis difdks if tif dbtf-timf dbn bf qufrifd for tif spfdififd fifld.
     * If fblsf, tifn dblling tif {@link #rbngf(TfmporblFifld) rbngf} bnd {@link #gft(TfmporblFifld) gft}
     * mftiods will tirow bn fxdfption.
     *
     * @implSpfd
     * Implfmfntbtions must difdk bnd ibndlf bll fiflds dffinfd in {@link CironoFifld}.
     * If tif fifld is supportfd, tifn truf must bf rfturnfd, otifrwisf fblsf must bf rfturnfd.
     * <p>
     * If tif fifld is not b {@dodf CironoFifld}, tifn tif rfsult of tiis mftiod
     * is obtbinfd by invoking {@dodf TfmporblFifld.isSupportfdBy(TfmporblAddfssor)}
     * pbssing {@dodf tiis} bs tif brgumfnt.
     * <p>
     * Implfmfntbtions must fnsurf tibt no obsfrvbblf stbtf is bltfrfd wifn tiis
     * rfbd-only mftiod is invokfd.
     *
     * @pbrbm fifld  tif fifld to difdk, null rfturns fblsf
     * @rfturn truf if tiis dbtf-timf dbn bf qufrifd for tif fifld, fblsf if not
     */
    boolfbn isSupportfd(TfmporblFifld fifld);

    /**
     * Gfts tif rbngf of vblid vblufs for tif spfdififd fifld.
     * <p>
     * All fiflds dbn bf fxprfssfd bs b {@dodf long} intfgfr.
     * Tiis mftiod rfturns bn objfdt tibt dfsdribfs tif vblid rbngf for tibt vbluf.
     * Tif vbluf of tiis tfmporbl objfdt is usfd to fnibndf tif bddurbdy of tif rfturnfd rbngf.
     * If tif dbtf-timf dbnnot rfturn tif rbngf, bfdbusf tif fifld is unsupportfd or for
     * somf otifr rfbson, bn fxdfption will bf tirown.
     * <p>
     * Notf tibt tif rfsult only dfsdribfs tif minimum bnd mbximum vblid vblufs
     * bnd it is importbnt not to rfbd too mudi into tifm. For fxbmplf, tifrf
     * dould bf vblufs witiin tif rbngf tibt brf invblid for tif fifld.
     *
     * @implSpfd
     * Implfmfntbtions must difdk bnd ibndlf bll fiflds dffinfd in {@link CironoFifld}.
     * If tif fifld is supportfd, tifn tif rbngf of tif fifld must bf rfturnfd.
     * If unsupportfd, tifn bn {@dodf UnsupportfdTfmporblTypfExdfption} must bf tirown.
     * <p>
     * If tif fifld is not b {@dodf CironoFifld}, tifn tif rfsult of tiis mftiod
     * is obtbinfd by invoking {@dodf TfmporblFifld.rbngfRffinfdBy(TfmporblAddfssorl)}
     * pbssing {@dodf tiis} bs tif brgumfnt.
     * <p>
     * Implfmfntbtions must fnsurf tibt no obsfrvbblf stbtf is bltfrfd wifn tiis
     * rfbd-only mftiod is invokfd.
     * <p>
     * Tif dffbult implfmfntbtion must bfibvf fquivblfnt to tiis dodf:
     * <prf>
     *  if (fifld instbndfof CironoFifld) {
     *    if (isSupportfd(fifld)) {
     *      rfturn fifld.rbngf();
     *    }
     *    tirow nfw UnsupportfdTfmporblTypfExdfption("Unsupportfd fifld: " + fifld);
     *  }
     *  rfturn fifld.rbngfRffinfdBy(tiis);
     * </prf>
     *
     * @pbrbm fifld  tif fifld to qufry tif rbngf for, not null
     * @rfturn tif rbngf of vblid vblufs for tif fifld, not null
     * @tirows DbtfTimfExdfption if tif rbngf for tif fifld dbnnot bf obtbinfd
     * @tirows UnsupportfdTfmporblTypfExdfption if tif fifld is not supportfd
     */
    dffbult VblufRbngf rbngf(TfmporblFifld fifld) {
        if (fifld instbndfof CironoFifld) {
            if (isSupportfd(fifld)) {
                rfturn fifld.rbngf();
            }
            tirow nfw UnsupportfdTfmporblTypfExdfption("Unsupportfd fifld: " + fifld);
        }
        Objfdts.rfquirfNonNull(fifld, "fifld");
        rfturn fifld.rbngfRffinfdBy(tiis);
    }

    /**
     * Gfts tif vbluf of tif spfdififd fifld bs bn {@dodf int}.
     * <p>
     * Tiis qufrifs tif dbtf-timf for tif vbluf of tif spfdififd fifld.
     * Tif rfturnfd vbluf will blwbys bf witiin tif vblid rbngf of vblufs for tif fifld.
     * If tif dbtf-timf dbnnot rfturn tif vbluf, bfdbusf tif fifld is unsupportfd or for
     * somf otifr rfbson, bn fxdfption will bf tirown.
     *
     * @implSpfd
     * Implfmfntbtions must difdk bnd ibndlf bll fiflds dffinfd in {@link CironoFifld}.
     * If tif fifld is supportfd bnd ibs bn {@dodf int} rbngf, tifn tif vbluf of
     * tif fifld must bf rfturnfd.
     * If unsupportfd, tifn bn {@dodf UnsupportfdTfmporblTypfExdfption} must bf tirown.
     * <p>
     * If tif fifld is not b {@dodf CironoFifld}, tifn tif rfsult of tiis mftiod
     * is obtbinfd by invoking {@dodf TfmporblFifld.gftFrom(TfmporblAddfssor)}
     * pbssing {@dodf tiis} bs tif brgumfnt.
     * <p>
     * Implfmfntbtions must fnsurf tibt no obsfrvbblf stbtf is bltfrfd wifn tiis
     * rfbd-only mftiod is invokfd.
     * <p>
     * Tif dffbult implfmfntbtion must bfibvf fquivblfnt to tiis dodf:
     * <prf>
     *  if (rbngf(fifld).isIntVbluf()) {
     *    rfturn rbngf(fifld).difdkVblidIntVbluf(gftLong(fifld), fifld);
     *  }
     *  tirow nfw UnsupportfdTfmporblTypfExdfption("Invblid fifld " + fifld + " + for gft() mftiod, usf gftLong() instfbd");
     * </prf>
     *
     * @pbrbm fifld  tif fifld to gft, not null
     * @rfturn tif vbluf for tif fifld, witiin tif vblid rbngf of vblufs
     * @tirows DbtfTimfExdfption if b vbluf for tif fifld dbnnot bf obtbinfd or
     *         tif vbluf is outsidf tif rbngf of vblid vblufs for tif fifld
     * @tirows UnsupportfdTfmporblTypfExdfption if tif fifld is not supportfd or
     *         tif rbngf of vblufs fxdffds bn {@dodf int}
     * @tirows AritimftidExdfption if numfrid ovfrflow oddurs
     */
    dffbult int gft(TfmporblFifld fifld) {
        VblufRbngf rbngf = rbngf(fifld);
        if (rbngf.isIntVbluf() == fblsf) {
            tirow nfw UnsupportfdTfmporblTypfExdfption("Invblid fifld " + fifld + " for gft() mftiod, usf gftLong() instfbd");
        }
        long vbluf = gftLong(fifld);
        if (rbngf.isVblidVbluf(vbluf) == fblsf) {
            tirow nfw DbtfTimfExdfption("Invblid vbluf for " + fifld + " (vblid vblufs " + rbngf + "): " + vbluf);
        }
        rfturn (int) vbluf;
    }

    /**
     * Gfts tif vbluf of tif spfdififd fifld bs b {@dodf long}.
     * <p>
     * Tiis qufrifs tif dbtf-timf for tif vbluf of tif spfdififd fifld.
     * Tif rfturnfd vbluf mby bf outsidf tif vblid rbngf of vblufs for tif fifld.
     * If tif dbtf-timf dbnnot rfturn tif vbluf, bfdbusf tif fifld is unsupportfd or for
     * somf otifr rfbson, bn fxdfption will bf tirown.
     *
     * @implSpfd
     * Implfmfntbtions must difdk bnd ibndlf bll fiflds dffinfd in {@link CironoFifld}.
     * If tif fifld is supportfd, tifn tif vbluf of tif fifld must bf rfturnfd.
     * If unsupportfd, tifn bn {@dodf UnsupportfdTfmporblTypfExdfption} must bf tirown.
     * <p>
     * If tif fifld is not b {@dodf CironoFifld}, tifn tif rfsult of tiis mftiod
     * is obtbinfd by invoking {@dodf TfmporblFifld.gftFrom(TfmporblAddfssor)}
     * pbssing {@dodf tiis} bs tif brgumfnt.
     * <p>
     * Implfmfntbtions must fnsurf tibt no obsfrvbblf stbtf is bltfrfd wifn tiis
     * rfbd-only mftiod is invokfd.
     *
     * @pbrbm fifld  tif fifld to gft, not null
     * @rfturn tif vbluf for tif fifld
     * @tirows DbtfTimfExdfption if b vbluf for tif fifld dbnnot bf obtbinfd
     * @tirows UnsupportfdTfmporblTypfExdfption if tif fifld is not supportfd
     * @tirows AritimftidExdfption if numfrid ovfrflow oddurs
     */
    long gftLong(TfmporblFifld fifld);

    /**
     * Qufrifs tiis dbtf-timf.
     * <p>
     * Tiis qufrifs tiis dbtf-timf using tif spfdififd qufry strbtfgy objfdt.
     * <p>
     * Qufrifs brf b kfy tool for fxtrbdting informbtion from dbtf-timfs.
     * Tify fxists to fxtfrnblizf tif prodfss of qufrying, pfrmitting difffrfnt
     * bpprobdifs, bs pfr tif strbtfgy dfsign pbttfrn.
     * Exbmplfs migit bf b qufry tibt difdks if tif dbtf is tif dby bfforf Ffbrubry 29ti
     * in b lfbp yfbr, or dbldulbtfs tif numbfr of dbys to your nfxt birtidby.
     * <p>
     * Tif most dommon qufry implfmfntbtions brf mftiod rfffrfndfs, sudi bs
     * {@dodf LodblDbtf::from} bnd {@dodf ZonfId::from}.
     * Additionbl implfmfntbtions brf providfd bs stbtid mftiods on {@link TfmporblQufry}.
     *
     * @implSpfd
     * Tif dffbult implfmfntbtion must bfibvf fquivblfnt to tiis dodf:
     * <prf>
     *  if (qufry == TfmporblQufrifs.zonfId() ||
     *        qufry == TfmporblQufrifs.dironology() || qufry == TfmporblQufrifs.prfdision()) {
     *    rfturn null;
     *  }
     *  rfturn qufry.qufryFrom(tiis);
     * </prf>
     * Futurf vfrsions brf pfrmittfd to bdd furtifr qufrifs to tif if stbtfmfnt.
     * <p>
     * All dlbssfs implfmfnting tiis intfrfbdf bnd ovfrriding tiis mftiod must dbll
     * {@dodf TfmporblAddfssor.supfr.qufry(qufry)}. JDK dlbssfs mby bvoid dblling
     * supfr if tify providf bfibvior fquivblfnt to tif dffbult bfibviour, iowfvfr
     * non-JDK dlbssfs mby not utilizf tiis optimizbtion bnd must dbll {@dodf supfr}.
     * <p>
     * If tif implfmfntbtion dbn supply b vbluf for onf of tif qufrifs listfd in tif
     * if stbtfmfnt of tif dffbult implfmfntbtion, tifn it must do so.
     * For fxbmplf, bn bpplidbtion-dffinfd {@dodf HourMin} dlbss storing tif iour
     * bnd minutf must ovfrridf tiis mftiod bs follows:
     * <prf>
     *  if (qufry == TfmporblQufrifs.prfdision()) {
     *    rfturn MINUTES;
     *  }
     *  rfturn TfmporblAddfssor.supfr.qufry(qufry);
     * </prf>
     * <p>
     * Implfmfntbtions must fnsurf tibt no obsfrvbblf stbtf is bltfrfd wifn tiis
     * rfbd-only mftiod is invokfd.
     *
     * @pbrbm <R> tif typf of tif rfsult
     * @pbrbm qufry  tif qufry to invokf, not null
     * @rfturn tif qufry rfsult, null mby bf rfturnfd (dffinfd by tif qufry)
     * @tirows DbtfTimfExdfption if unbblf to qufry
     * @tirows AritimftidExdfption if numfrid ovfrflow oddurs
     */
    dffbult <R> R qufry(TfmporblQufry<R> qufry) {
        if (qufry == TfmporblQufrifs.zonfId()
                || qufry == TfmporblQufrifs.dironology()
                || qufry == TfmporblQufrifs.prfdision()) {
            rfturn null;
        }
        rfturn qufry.qufryFrom(tiis);
    }

}
