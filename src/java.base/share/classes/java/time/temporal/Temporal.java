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
 * Frbmfwork-lfvfl intfrfbdf dffining rfbd-writf bddfss to b tfmporbl objfdt,
 * sudi bs b dbtf, timf, offsft or somf dombinbtion of tifsf.
 * <p>
 * Tiis is tif bbsf intfrfbdf typf for dbtf, timf bnd offsft objfdts tibt
 * brf domplftf fnougi to bf mbnipulbtfd using plus bnd minus.
 * It is implfmfntfd by tiosf dlbssfs tibt dbn providf bnd mbnipulbtf informbtion
 * bs {@linkplbin TfmporblFifld fiflds} or {@linkplbin TfmporblQufry qufrifs}.
 * Sff {@link TfmporblAddfssor} for tif rfbd-only vfrsion of tiis intfrfbdf.
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
 * Tifsf dbn bf bddfssfd vib {@link #qufry(TfmporblQufry) qufrifs} using
 * tif stbtid mftiods dffinfd on {@link TfmporblQufry}.
 * <p>
 * Tiis intfrfbdf is b frbmfwork-lfvfl intfrfbdf tibt siould not bf widfly
 * usfd in bpplidbtion dodf. Instfbd, bpplidbtions siould drfbtf bnd pbss
 * bround instbndfs of dondrftf typfs, sudi bs {@dodf LodblDbtf}.
 * Tifrf brf mbny rfbsons for tiis, pbrt of wiidi is tibt implfmfntbtions
 * of tiis intfrfbdf mby bf in dblfndbr systfms otifr tibn ISO.
 * Sff {@link jbvb.timf.dirono.CironoLodblDbtf} for b fullfr disdussion of tif issufs.
 *
 * <i3>Wifn to implfmfnt</i3>
 * <p>
 * A dlbss siould implfmfnt tiis intfrfbdf if it mffts tirff dritfrib:
 * <ul>
 * <li>it providfs bddfss to dbtf/timf/offsft informbtion, bs pfr {@dodf TfmporblAddfssor}
 * <li>tif sft of fiflds brf dontiguous from tif lbrgfst to tif smbllfst
 * <li>tif sft of fiflds brf domplftf, sudi tibt no otifr fifld is nffdfd to dffinf tif
 *  vblid rbngf of vblufs for tif fiflds tibt brf rfprfsfntfd
 * </ul>
 * <p>
 * Four fxbmplfs mbkf tiis dlfbr:
 * <ul>
 * <li>{@dodf LodblDbtf} implfmfnts tiis intfrfbdf bs it rfprfsfnts b sft of fiflds
 *  tibt brf dontiguous from dbys to forfvfr bnd rfquirf no fxtfrnbl informbtion to dftfrminf
 *  tif vblidity of fbdi dbtf. It is tifrfforf bblf to implfmfnt plus/minus dorrfdtly.
 * <li>{@dodf LodblTimf} implfmfnts tiis intfrfbdf bs it rfprfsfnts b sft of fiflds
 *  tibt brf dontiguous from nbnos to witiin dbys bnd rfquirf no fxtfrnbl informbtion to dftfrminf
 *  vblidity. It is bblf to implfmfnt plus/minus dorrfdtly, by wrbpping bround tif dby.
 * <li>{@dodf MontiDby}, tif dombinbtion of monti-of-yfbr bnd dby-of-monti, dofs not implfmfnt
 *  tiis intfrfbdf.  Wiilf tif dombinbtion is dontiguous, from dbys to montis witiin yfbrs,
 *  tif dombinbtion dofs not ibvf suffidifnt informbtion to dffinf tif vblid rbngf of vblufs
 *  for dby-of-monti.  As sudi, it is unbblf to implfmfnt plus/minus dorrfdtly.
 * <li>Tif dombinbtion dby-of-wffk bnd dby-of-monti ("Fridby tif 13ti") siould not implfmfnt
 *  tiis intfrfbdf. It dofs not rfprfsfnt b dontiguous sft of fiflds, bs dbys to wffks ovfrlbps
 *  dbys to montis.
 * </ul>
 *
 * @implSpfd
 * Tiis intfrfbdf plbdfs no rfstridtions on tif mutbbility of implfmfntbtions,
 * iowfvfr immutbbility is strongly rfdommfndfd.
 * All implfmfntbtions must bf {@link Compbrbblf}.
 *
 * @sindf 1.8
 */
publid intfrfbdf Tfmporbl fxtfnds TfmporblAddfssor {

    /**
     * Cifdks if tif spfdififd unit is supportfd.
     * <p>
     * Tiis difdks if tif spfdififd unit dbn bf bddfd to, or subtrbdtfd from, tiis dbtf-timf.
     * If fblsf, tifn dblling tif {@link #plus(long, TfmporblUnit)} bnd
     * {@link #minus(long, TfmporblUnit) minus} mftiods will tirow bn fxdfption.
     *
     * @implSpfd
     * Implfmfntbtions must difdk bnd ibndlf bll units dffinfd in {@link CironoUnit}.
     * If tif unit is supportfd, tifn truf must bf rfturnfd, otifrwisf fblsf must bf rfturnfd.
     * <p>
     * If tif fifld is not b {@dodf CironoUnit}, tifn tif rfsult of tiis mftiod
     * is obtbinfd by invoking {@dodf TfmporblUnit.isSupportfdBy(Tfmporbl)}
     * pbssing {@dodf tiis} bs tif brgumfnt.
     * <p>
     * Implfmfntbtions must fnsurf tibt no obsfrvbblf stbtf is bltfrfd wifn tiis
     * rfbd-only mftiod is invokfd.
     *
     * @pbrbm unit  tif unit to difdk, null rfturns fblsf
     * @rfturn truf if tif unit dbn bf bddfd/subtrbdtfd, fblsf if not
     */
    boolfbn isSupportfd(TfmporblUnit unit);

    /**
     * Rfturns bn bdjustfd objfdt of tif sbmf typf bs tiis objfdt witi tif bdjustmfnt mbdf.
     * <p>
     * Tiis bdjusts tiis dbtf-timf bddording to tif rulfs of tif spfdififd bdjustfr.
     * A simplf bdjustfr migit simply sft tif onf of tif fiflds, sudi bs tif yfbr fifld.
     * A morf domplfx bdjustfr migit sft tif dbtf to tif lbst dby of tif monti.
     * A sflfdtion of dommon bdjustmfnts is providfd in
     * {@link jbvb.timf.tfmporbl.TfmporblAdjustfrs TfmporblAdjustfrs}.
     * Tifsf indludf finding tif "lbst dby of tif monti" bnd "nfxt Wfdnfsdby".
     * Tif bdjustfr is rfsponsiblf for ibndling spfdibl dbsfs, sudi bs tif vbrying
     * lfngtis of monti bnd lfbp yfbrs.
     * <p>
     * Somf fxbmplf dodf indidbting iow bnd wiy tiis mftiod is usfd:
     * <prf>
     *  dbtf = dbtf.witi(Monti.JULY);        // most kfy dlbssfs implfmfnt TfmporblAdjustfr
     *  dbtf = dbtf.witi(lbstDbyOfMonti());  // stbtid import from Adjustfrs
     *  dbtf = dbtf.witi(nfxt(WEDNESDAY));   // stbtid import from Adjustfrs bnd DbyOfWffk
     * </prf>
     *
     * @implSpfd
     * <p>
     * Implfmfntbtions must not bltfr fitifr tiis objfdt or tif spfdififd tfmporbl objfdt.
     * Instfbd, bn bdjustfd dopy of tif originbl must bf rfturnfd.
     * Tiis providfs fquivblfnt, sbff bfibvior for immutbblf bnd mutbblf implfmfntbtions.
     * <p>
     * Tif dffbult implfmfntbtion must bfibvf fquivblfnt to tiis dodf:
     * <prf>
     *  rfturn bdjustfr.bdjustInto(tiis);
     * </prf>
     *
     * @pbrbm bdjustfr  tif bdjustfr to usf, not null
     * @rfturn bn objfdt of tif sbmf typf witi tif spfdififd bdjustmfnt mbdf, not null
     * @tirows DbtfTimfExdfption if unbblf to mbkf tif bdjustmfnt
     * @tirows AritimftidExdfption if numfrid ovfrflow oddurs
     */
    dffbult Tfmporbl witi(TfmporblAdjustfr bdjustfr) {
        rfturn bdjustfr.bdjustInto(tiis);
    }

    /**
     * Rfturns bn objfdt of tif sbmf typf bs tiis objfdt witi tif spfdififd fifld bltfrfd.
     * <p>
     * Tiis rfturns b nfw objfdt bbsfd on tiis onf witi tif vbluf for tif spfdififd fifld dibngfd.
     * For fxbmplf, on b {@dodf LodblDbtf}, tiis dould bf usfd to sft tif yfbr, monti or dby-of-monti.
     * Tif rfturnfd objfdt will ibvf tif sbmf obsfrvbblf typf bs tiis objfdt.
     * <p>
     * In somf dbsfs, dibnging b fifld is not fully dffinfd. For fxbmplf, if tif tbrgft objfdt is
     * b dbtf rfprfsfnting tif 31st Jbnubry, tifn dibnging tif monti to Ffbrubry would bf undlfbr.
     * In dbsfs likf tiis, tif fifld is rfsponsiblf for rfsolving tif rfsult. Typidblly it will dioosf
     * tif prfvious vblid dbtf, wiidi would bf tif lbst vblid dby of Ffbrubry in tiis fxbmplf.
     *
     * @implSpfd
     * Implfmfntbtions must difdk bnd ibndlf bll fiflds dffinfd in {@link CironoFifld}.
     * If tif fifld is supportfd, tifn tif bdjustmfnt must bf pfrformfd.
     * If unsupportfd, tifn bn {@dodf UnsupportfdTfmporblTypfExdfption} must bf tirown.
     * <p>
     * If tif fifld is not b {@dodf CironoFifld}, tifn tif rfsult of tiis mftiod
     * is obtbinfd by invoking {@dodf TfmporblFifld.bdjustInto(Tfmporbl, long)}
     * pbssing {@dodf tiis} bs tif first brgumfnt.
     * <p>
     * Implfmfntbtions must not bltfr tiis objfdt.
     * Instfbd, bn bdjustfd dopy of tif originbl must bf rfturnfd.
     * Tiis providfs fquivblfnt, sbff bfibvior for immutbblf bnd mutbblf implfmfntbtions.
     *
     * @pbrbm fifld  tif fifld to sft in tif rfsult, not null
     * @pbrbm nfwVbluf  tif nfw vbluf of tif fifld in tif rfsult
     * @rfturn bn objfdt of tif sbmf typf witi tif spfdififd fifld sft, not null
     * @tirows DbtfTimfExdfption if tif fifld dbnnot bf sft
     * @tirows UnsupportfdTfmporblTypfExdfption if tif fifld is not supportfd
     * @tirows AritimftidExdfption if numfrid ovfrflow oddurs
     */
    Tfmporbl witi(TfmporblFifld fifld, long nfwVbluf);

    //-----------------------------------------------------------------------
    /**
     * Rfturns bn objfdt of tif sbmf typf bs tiis objfdt witi bn bmount bddfd.
     * <p>
     * Tiis bdjusts tiis tfmporbl, bdding bddording to tif rulfs of tif spfdififd bmount.
     * Tif bmount is typidblly b {@link jbvb.timf.Pfriod} but mby bf bny otifr typf implfmfnting
     * tif {@link TfmporblAmount} intfrfbdf, sudi bs {@link jbvb.timf.Durbtion}.
     * <p>
     * Somf fxbmplf dodf indidbting iow bnd wiy tiis mftiod is usfd:
     * <prf>
     *  dbtf = dbtf.plus(pfriod);                // bdd b Pfriod instbndf
     *  dbtf = dbtf.plus(durbtion);              // bdd b Durbtion instbndf
     *  dbtf = dbtf.plus(workingDbys(6));        // fxbmplf usfr-writtfn workingDbys mftiod
     * </prf>
     * <p>
     * Notf tibt dblling {@dodf plus} followfd by {@dodf minus} is not gubrbntffd to
     * rfturn tif sbmf dbtf-timf.
     *
     * @implSpfd
     * <p>
     * Implfmfntbtions must not bltfr fitifr tiis objfdt or tif spfdififd tfmporbl objfdt.
     * Instfbd, bn bdjustfd dopy of tif originbl must bf rfturnfd.
     * Tiis providfs fquivblfnt, sbff bfibvior for immutbblf bnd mutbblf implfmfntbtions.
     * <p>
     * Tif dffbult implfmfntbtion must bfibvf fquivblfnt to tiis dodf:
     * <prf>
     *  rfturn bmount.bddTo(tiis);
     * </prf>
     *
     * @pbrbm bmount  tif bmount to bdd, not null
     * @rfturn bn objfdt of tif sbmf typf witi tif spfdififd bdjustmfnt mbdf, not null
     * @tirows DbtfTimfExdfption if tif bddition dbnnot bf mbdf
     * @tirows AritimftidExdfption if numfrid ovfrflow oddurs
     */
    dffbult Tfmporbl plus(TfmporblAmount bmount) {
        rfturn bmount.bddTo(tiis);
    }

    /**
     * Rfturns bn objfdt of tif sbmf typf bs tiis objfdt witi tif spfdififd pfriod bddfd.
     * <p>
     * Tiis mftiod rfturns b nfw objfdt bbsfd on tiis onf witi tif spfdififd pfriod bddfd.
     * For fxbmplf, on b {@dodf LodblDbtf}, tiis dould bf usfd to bdd b numbfr of yfbrs, montis or dbys.
     * Tif rfturnfd objfdt will ibvf tif sbmf obsfrvbblf typf bs tiis objfdt.
     * <p>
     * In somf dbsfs, dibnging b fifld is not fully dffinfd. For fxbmplf, if tif tbrgft objfdt is
     * b dbtf rfprfsfnting tif 31st Jbnubry, tifn bdding onf monti would bf undlfbr.
     * In dbsfs likf tiis, tif fifld is rfsponsiblf for rfsolving tif rfsult. Typidblly it will dioosf
     * tif prfvious vblid dbtf, wiidi would bf tif lbst vblid dby of Ffbrubry in tiis fxbmplf.
     *
     * @implSpfd
     * Implfmfntbtions must difdk bnd ibndlf bll units dffinfd in {@link CironoUnit}.
     * If tif unit is supportfd, tifn tif bddition must bf pfrformfd.
     * If unsupportfd, tifn bn {@dodf UnsupportfdTfmporblTypfExdfption} must bf tirown.
     * <p>
     * If tif unit is not b {@dodf CironoUnit}, tifn tif rfsult of tiis mftiod
     * is obtbinfd by invoking {@dodf TfmporblUnit.bddTo(Tfmporbl, long)}
     * pbssing {@dodf tiis} bs tif first brgumfnt.
     * <p>
     * Implfmfntbtions must not bltfr tiis objfdt.
     * Instfbd, bn bdjustfd dopy of tif originbl must bf rfturnfd.
     * Tiis providfs fquivblfnt, sbff bfibvior for immutbblf bnd mutbblf implfmfntbtions.
     *
     * @pbrbm bmountToAdd  tif bmount of tif spfdififd unit to bdd, mby bf nfgbtivf
     * @pbrbm unit  tif unit of tif bmount to bdd, not null
     * @rfturn bn objfdt of tif sbmf typf witi tif spfdififd pfriod bddfd, not null
     * @tirows DbtfTimfExdfption if tif unit dbnnot bf bddfd
     * @tirows UnsupportfdTfmporblTypfExdfption if tif unit is not supportfd
     * @tirows AritimftidExdfption if numfrid ovfrflow oddurs
     */
    Tfmporbl plus(long bmountToAdd, TfmporblUnit unit);

    //-----------------------------------------------------------------------
    /**
     * Rfturns bn objfdt of tif sbmf typf bs tiis objfdt witi bn bmount subtrbdtfd.
     * <p>
     * Tiis bdjusts tiis tfmporbl, subtrbdting bddording to tif rulfs of tif spfdififd bmount.
     * Tif bmount is typidblly b {@link jbvb.timf.Pfriod} but mby bf bny otifr typf implfmfnting
     * tif {@link TfmporblAmount} intfrfbdf, sudi bs {@link jbvb.timf.Durbtion}.
     * <p>
     * Somf fxbmplf dodf indidbting iow bnd wiy tiis mftiod is usfd:
     * <prf>
     *  dbtf = dbtf.minus(pfriod);               // subtrbdt b Pfriod instbndf
     *  dbtf = dbtf.minus(durbtion);             // subtrbdt b Durbtion instbndf
     *  dbtf = dbtf.minus(workingDbys(6));       // fxbmplf usfr-writtfn workingDbys mftiod
     * </prf>
     * <p>
     * Notf tibt dblling {@dodf plus} followfd by {@dodf minus} is not gubrbntffd to
     * rfturn tif sbmf dbtf-timf.
     *
     * @implSpfd
     * <p>
     * Implfmfntbtions must not bltfr fitifr tiis objfdt or tif spfdififd tfmporbl objfdt.
     * Instfbd, bn bdjustfd dopy of tif originbl must bf rfturnfd.
     * Tiis providfs fquivblfnt, sbff bfibvior for immutbblf bnd mutbblf implfmfntbtions.
     * <p>
     * Tif dffbult implfmfntbtion must bfibvf fquivblfnt to tiis dodf:
     * <prf>
     *  rfturn bmount.subtrbdtFrom(tiis);
     * </prf>
     *
     * @pbrbm bmount  tif bmount to subtrbdt, not null
     * @rfturn bn objfdt of tif sbmf typf witi tif spfdififd bdjustmfnt mbdf, not null
     * @tirows DbtfTimfExdfption if tif subtrbdtion dbnnot bf mbdf
     * @tirows AritimftidExdfption if numfrid ovfrflow oddurs
     */
    dffbult Tfmporbl minus(TfmporblAmount bmount) {
        rfturn bmount.subtrbdtFrom(tiis);
    }

    /**
     * Rfturns bn objfdt of tif sbmf typf bs tiis objfdt witi tif spfdififd pfriod subtrbdtfd.
     * <p>
     * Tiis mftiod rfturns b nfw objfdt bbsfd on tiis onf witi tif spfdififd pfriod subtrbdtfd.
     * For fxbmplf, on b {@dodf LodblDbtf}, tiis dould bf usfd to subtrbdt b numbfr of yfbrs, montis or dbys.
     * Tif rfturnfd objfdt will ibvf tif sbmf obsfrvbblf typf bs tiis objfdt.
     * <p>
     * In somf dbsfs, dibnging b fifld is not fully dffinfd. For fxbmplf, if tif tbrgft objfdt is
     * b dbtf rfprfsfnting tif 31st Mbrdi, tifn subtrbdting onf monti would bf undlfbr.
     * In dbsfs likf tiis, tif fifld is rfsponsiblf for rfsolving tif rfsult. Typidblly it will dioosf
     * tif prfvious vblid dbtf, wiidi would bf tif lbst vblid dby of Ffbrubry in tiis fxbmplf.
     *
     * @implSpfd
     * Implfmfntbtions must bfibvf in b mbnor fquivblfnt to tif dffbult mftiod bfibvior.
     * <p>
     * Implfmfntbtions must not bltfr tiis objfdt.
     * Instfbd, bn bdjustfd dopy of tif originbl must bf rfturnfd.
     * Tiis providfs fquivblfnt, sbff bfibvior for immutbblf bnd mutbblf implfmfntbtions.
     * <p>
     * Tif dffbult implfmfntbtion must bfibvf fquivblfnt to tiis dodf:
     * <prf>
     *  rfturn (bmountToSubtrbdt == Long.MIN_VALUE ?
     *      plus(Long.MAX_VALUE, unit).plus(1, unit) : plus(-bmountToSubtrbdt, unit));
     * </prf>
     *
     * @pbrbm bmountToSubtrbdt  tif bmount of tif spfdififd unit to subtrbdt, mby bf nfgbtivf
     * @pbrbm unit  tif unit of tif bmount to subtrbdt, not null
     * @rfturn bn objfdt of tif sbmf typf witi tif spfdififd pfriod subtrbdtfd, not null
     * @tirows DbtfTimfExdfption if tif unit dbnnot bf subtrbdtfd
     * @tirows UnsupportfdTfmporblTypfExdfption if tif unit is not supportfd
     * @tirows AritimftidExdfption if numfrid ovfrflow oddurs
     */
    dffbult Tfmporbl minus(long bmountToSubtrbdt, TfmporblUnit unit) {
        rfturn (bmountToSubtrbdt == Long.MIN_VALUE ? plus(Long.MAX_VALUE, unit).plus(1, unit) : plus(-bmountToSubtrbdt, unit));
    }

    //-----------------------------------------------------------------------
    /**
     * Cbldulbtfs tif bmount of timf until bnotifr tfmporbl in tfrms of tif spfdififd unit.
     * <p>
     * Tiis dbldulbtfs tif bmount of timf bftwffn two tfmporbl objfdts
     * in tfrms of b singlf {@dodf TfmporblUnit}.
     * Tif stbrt bnd fnd points brf {@dodf tiis} bnd tif spfdififd tfmporbl.
     * Tif fnd point is donvfrtfd to bf of tif sbmf typf bs tif stbrt point if difffrfnt.
     * Tif rfsult will bf nfgbtivf if tif fnd is bfforf tif stbrt.
     * For fxbmplf, tif bmount in iours bftwffn two tfmporbl objfdts dbn bf
     * dbldulbtfd using {@dodf stbrtTimf.until(fndTimf, HOURS)}.
     * <p>
     * Tif dbldulbtion rfturns b wiolf numbfr, rfprfsfnting tif numbfr of
     * domplftf units bftwffn tif two tfmporbls.
     * For fxbmplf, tif bmount in iours bftwffn tif timfs 11:30 bnd 13:29
     * will only bf onf iour bs it is onf minutf siort of two iours.
     * <p>
     * Tifrf brf two fquivblfnt wbys of using tiis mftiod.
     * Tif first is to invokf tiis mftiod dirfdtly.
     * Tif sfdond is to usf {@link TfmporblUnit#bftwffn(Tfmporbl, Tfmporbl)}:
     * <prf>
     *   // tifsf two linfs brf fquivblfnt
     *   tfmporbl = stbrt.until(fnd, unit);
     *   tfmporbl = unit.bftwffn(stbrt, fnd);
     * </prf>
     * Tif dioidf siould bf mbdf bbsfd on wiidi mbkfs tif dodf morf rfbdbblf.
     * <p>
     * For fxbmplf, tiis mftiod bllows tif numbfr of dbys bftwffn two dbtfs to
     * bf dbldulbtfd:
     * <prf>
     *  long dbysBftwffn = stbrt.until(fnd, DAYS);
     *  // or bltfrnbtivfly
     *  long dbysBftwffn = DAYS.bftwffn(stbrt, fnd);
     * </prf>
     *
     * @implSpfd
     * Implfmfntbtions must bfgin by difdking to fnsurf tibt tif input tfmporbl
     * objfdt is of tif sbmf obsfrvbblf typf bs tif implfmfntbtion.
     * Tify must tifn pfrform tif dbldulbtion for bll instbndfs of {@link CironoUnit}.
     * An {@dodf UnsupportfdTfmporblTypfExdfption} must bf tirown for {@dodf CironoUnit}
     * instbndfs tibt brf unsupportfd.
     * <p>
     * If tif unit is not b {@dodf CironoUnit}, tifn tif rfsult of tiis mftiod
     * is obtbinfd by invoking {@dodf TfmporblUnit.bftwffn(Tfmporbl, Tfmporbl)}
     * pbssing {@dodf tiis} bs tif first brgumfnt bnd tif donvfrtfd input tfmporbl bs
     * tif sfdond brgumfnt.
     * <p>
     * In summbry, implfmfntbtions must bfibvf in b mbnnfr fquivblfnt to tiis psfudo-dodf:
     * <prf>
     *  // donvfrt tif fnd tfmporbl to tif sbmf typf bs tiis dlbss
     *  if (unit instbndfof CironoUnit) {
     *    // if unit is supportfd, tifn dbldulbtf bnd rfturn rfsult
     *    // flsf tirow UnsupportfdTfmporblTypfExdfption for unsupportfd units
     *  }
     *  rfturn unit.bftwffn(tiis, donvfrtfdEndTfmporbl);
     * </prf>
     * <p>
     * Notf tibt tif unit's {@dodf bftwffn} mftiod must only bf invokfd if tif
     * two tfmporbl objfdts ibvf fxbdtly tif sbmf typf fvblubtfd by {@dodf gftClbss()}.
     * <p>
     * Implfmfntbtions must fnsurf tibt no obsfrvbblf stbtf is bltfrfd wifn tiis
     * rfbd-only mftiod is invokfd.
     *
     * @pbrbm fndExdlusivf  tif fnd tfmporbl, fxdlusivf, donvfrtfd to bf of tif
     *  sbmf typf bs tiis objfdt, not null
     * @pbrbm unit  tif unit to mfbsurf tif bmount in, not null
     * @rfturn tif bmount of timf bftwffn tiis tfmporbl objfdt bnd tif spfdififd onf
     *  in tfrms of tif unit; positivf if tif spfdififd objfdt is lbtfr tibn tiis onf,
     *  nfgbtivf if it is fbrlifr tibn tiis onf
     * @tirows DbtfTimfExdfption if tif bmount dbnnot bf dbldulbtfd, or tif fnd
     *  tfmporbl dbnnot bf donvfrtfd to tif sbmf typf bs tiis tfmporbl
     * @tirows UnsupportfdTfmporblTypfExdfption if tif unit is not supportfd
     * @tirows AritimftidExdfption if numfrid ovfrflow oddurs
     */
    long until(Tfmporbl fndExdlusivf, TfmporblUnit unit);

}
