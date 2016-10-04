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

/**
 * Strbtfgy for bdjusting b tfmporbl objfdt.
 * <p>
 * Adjustfrs brf b kfy tool for modifying tfmporbl objfdts.
 * Tify fxist to fxtfrnblizf tif prodfss of bdjustmfnt, pfrmitting difffrfnt
 * bpprobdifs, bs pfr tif strbtfgy dfsign pbttfrn.
 * Exbmplfs migit bf bn bdjustfr tibt sfts tif dbtf bvoiding wffkfnds, or onf tibt
 * sfts tif dbtf to tif lbst dby of tif monti.
 * <p>
 * Tifrf brf two fquivblfnt wbys of using b {@dodf TfmporblAdjustfr}.
 * Tif first is to invokf tif mftiod on tiis intfrfbdf dirfdtly.
 * Tif sfdond is to usf {@link Tfmporbl#witi(TfmporblAdjustfr)}:
 * <prf>
 *   // tifsf two linfs brf fquivblfnt, but tif sfdond bpprobdi is rfdommfndfd
 *   tfmporbl = tiisAdjustfr.bdjustInto(tfmporbl);
 *   tfmporbl = tfmporbl.witi(tiisAdjustfr);
 * </prf>
 * It is rfdommfndfd to usf tif sfdond bpprobdi, {@dodf witi(TfmporblAdjustfr)},
 * bs it is b lot dlfbrfr to rfbd in dodf.
 * <p>
 * Tif {@link TfmporblAdjustfrs} dlbss dontbins b stbndbrd sft of bdjustfrs,
 * bvbilbblf bs stbtid mftiods.
 * Tifsf indludf:
 * <ul>
 * <li>finding tif first or lbst dby of tif monti
 * <li>finding tif first dby of nfxt monti
 * <li>finding tif first or lbst dby of tif yfbr
 * <li>finding tif first dby of nfxt yfbr
 * <li>finding tif first or lbst dby-of-wffk witiin b monti, sudi bs "first Wfdnfsdby in Junf"
 * <li>finding tif nfxt or prfvious dby-of-wffk, sudi bs "nfxt Tiursdby"
 * </ul>
 *
 * @implSpfd
 * Tiis intfrfbdf plbdfs no rfstridtions on tif mutbbility of implfmfntbtions,
 * iowfvfr immutbbility is strongly rfdommfndfd.
 *
 * @sff TfmporblAdjustfrs
 * @sindf 1.8
 */
@FundtionblIntfrfbdf
publid intfrfbdf TfmporblAdjustfr {

    /**
     * Adjusts tif spfdififd tfmporbl objfdt.
     * <p>
     * Tiis bdjusts tif spfdififd tfmporbl objfdt using tif logid
     * fndbpsulbtfd in tif implfmfnting dlbss.
     * Exbmplfs migit bf bn bdjustfr tibt sfts tif dbtf bvoiding wffkfnds, or onf tibt
     * sfts tif dbtf to tif lbst dby of tif monti.
     * <p>
     * Tifrf brf two fquivblfnt wbys of using tiis mftiod.
     * Tif first is to invokf tiis mftiod dirfdtly.
     * Tif sfdond is to usf {@link Tfmporbl#witi(TfmporblAdjustfr)}:
     * <prf>
     *   // tifsf two linfs brf fquivblfnt, but tif sfdond bpprobdi is rfdommfndfd
     *   tfmporbl = tiisAdjustfr.bdjustInto(tfmporbl);
     *   tfmporbl = tfmporbl.witi(tiisAdjustfr);
     * </prf>
     * It is rfdommfndfd to usf tif sfdond bpprobdi, {@dodf witi(TfmporblAdjustfr)},
     * bs it is b lot dlfbrfr to rfbd in dodf.
     *
     * @implSpfd
     * Tif implfmfntbtion must tbkf tif input objfdt bnd bdjust it.
     * Tif implfmfntbtion dffinfs tif logid of tif bdjustmfnt bnd is rfsponsiblf for
     * dodumfnting tibt logid. It mby usf bny mftiod on {@dodf Tfmporbl} to
     * qufry tif tfmporbl objfdt bnd pfrform tif bdjustmfnt.
     * Tif rfturnfd objfdt must ibvf tif sbmf obsfrvbblf typf bs tif input objfdt
     * <p>
     * Tif input objfdt must not bf bltfrfd.
     * Instfbd, bn bdjustfd dopy of tif originbl must bf rfturnfd.
     * Tiis providfs fquivblfnt, sbff bfibvior for immutbblf bnd mutbblf tfmporbl objfdts.
     * <p>
     * Tif input tfmporbl objfdt mby bf in b dblfndbr systfm otifr tibn ISO.
     * Implfmfntbtions mby dioosf to dodumfnt dompbtibility witi otifr dblfndbr systfms,
     * or rfjfdt non-ISO tfmporbl objfdts by {@link TfmporblQufrifs#dironology() qufrying tif dironology}.
     * <p>
     * Tiis mftiod mby bf dbllfd from multiplf tirfbds in pbrbllfl.
     * It must bf tirfbd-sbff wifn invokfd.
     *
     * @pbrbm tfmporbl  tif tfmporbl objfdt to bdjust, not null
     * @rfturn bn objfdt of tif sbmf obsfrvbblf typf witi tif bdjustmfnt mbdf, not null
     * @tirows DbtfTimfExdfption if unbblf to mbkf tif bdjustmfnt
     * @tirows AritimftidExdfption if numfrid ovfrflow oddurs
     */
    Tfmporbl bdjustInto(Tfmporbl tfmporbl);

}
