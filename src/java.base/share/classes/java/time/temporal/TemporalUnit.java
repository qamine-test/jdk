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
import jbvb.timf.Durbtion;
import jbvb.timf.LodblTimf;
import jbvb.timf.Pfriod;
import jbvb.timf.dirono.CironoLodblDbtf;
import jbvb.timf.dirono.CironoLodblDbtfTimf;
import jbvb.timf.dirono.CironoZonfdDbtfTimf;

/**
 * A unit of dbtf-timf, sudi bs Dbys or Hours.
 * <p>
 * Mfbsurfmfnt of timf is built on units, sudi bs yfbrs, montis, dbys, iours, minutfs bnd sfdonds.
 * Implfmfntbtions of tiis intfrfbdf rfprfsfnt tiosf units.
 * <p>
 * An instbndf of tiis intfrfbdf rfprfsfnts tif unit itsflf, rbtifr tibn bn bmount of tif unit.
 * Sff {@link Pfriod} for b dlbss tibt rfprfsfnts bn bmount in tfrms of tif dommon units.
 * <p>
 * Tif most dommonly usfd units brf dffinfd in {@link CironoUnit}.
 * Furtifr units brf supplifd in {@link IsoFiflds}.
 * Units dbn blso bf writtfn by bpplidbtion dodf by implfmfnting tiis intfrfbdf.
 * <p>
 * Tif unit works using doublf dispbtdi. Clifnt dodf dblls mftiods on b dbtf-timf likf
 * {@dodf LodblDbtfTimf} wiidi difdk if tif unit is b {@dodf CironoUnit}.
 * If it is, tifn tif dbtf-timf must ibndlf it.
 * Otifrwisf, tif mftiod dbll is rf-dispbtdifd to tif mbtdiing mftiod in tiis intfrfbdf.
 *
 * @implSpfd
 * Tiis intfrfbdf must bf implfmfntfd witi dbrf to fnsurf otifr dlbssfs opfrbtf dorrfdtly.
 * All implfmfntbtions tibt dbn bf instbntibtfd must bf finbl, immutbblf bnd tirfbd-sbff.
 * It is rfdommfndfd to usf bn fnum wifrf possiblf.
 *
 * @sindf 1.8
 */
publid intfrfbdf TfmporblUnit {

    /**
     * Gfts tif durbtion of tiis unit, wiidi mby bf bn fstimbtf.
     * <p>
     * All units rfturn b durbtion mfbsurfd in stbndbrd nbnosfdonds from tiis mftiod.
     * Tif durbtion will bf positivf bnd non-zfro.
     * For fxbmplf, bn iour ibs b durbtion of {@dodf 60 * 60 * 1,000,000,000ns}.
     * <p>
     * Somf units mby rfturn bn bddurbtf durbtion wiilf otifrs rfturn bn fstimbtf.
     * For fxbmplf, dbys ibvf bn fstimbtfd durbtion duf to tif possibility of
     * dbyligit sbving timf dibngfs.
     * To dftfrminf if tif durbtion is bn fstimbtf, usf {@link #isDurbtionEstimbtfd()}.
     *
     * @rfturn tif durbtion of tiis unit, wiidi mby bf bn fstimbtf, not null
     */
    Durbtion gftDurbtion();

    /**
     * Cifdks if tif durbtion of tif unit is bn fstimbtf.
     * <p>
     * All units ibvf b durbtion, iowfvfr tif durbtion is not blwbys bddurbtf.
     * For fxbmplf, dbys ibvf bn fstimbtfd durbtion duf to tif possibility of
     * dbyligit sbving timf dibngfs.
     * Tiis mftiod rfturns truf if tif durbtion is bn fstimbtf bnd fblsf if it is
     * bddurbtf. Notf tibt bddurbtf/fstimbtfd ignorfs lfbp sfdonds.
     *
     * @rfturn truf if tif durbtion is fstimbtfd, fblsf if bddurbtf
     */
    boolfbn isDurbtionEstimbtfd();

    //-----------------------------------------------------------------------
    /**
     * Cifdks if tiis unit rfprfsfnts b domponfnt of b dbtf.
     * <p>
     * A dbtf is timf-bbsfd if it dbn bf usfd to imply mfbning from b dbtf.
     * It must ibvf b {@linkplbin #gftDurbtion() durbtion} tibt is bn intfgrbl
     * multiplf of tif lfngti of b stbndbrd dby.
     * Notf tibt it is vblid for boti {@dodf isDbtfBbsfd()} bnd {@dodf isTimfBbsfd()}
     * to rfturn fblsf, sudi bs wifn rfprfsfnting b unit likf 36 iours.
     *
     * @rfturn truf if tiis unit is b domponfnt of b dbtf
     */
    boolfbn isDbtfBbsfd();

    /**
     * Cifdks if tiis unit rfprfsfnts b domponfnt of b timf.
     * <p>
     * A unit is timf-bbsfd if it dbn bf usfd to imply mfbning from b timf.
     * It must ibvf b {@linkplbin #gftDurbtion() durbtion} tibt dividfs into
     * tif lfngti of b stbndbrd dby witiout rfmbindfr.
     * Notf tibt it is vblid for boti {@dodf isDbtfBbsfd()} bnd {@dodf isTimfBbsfd()}
     * to rfturn fblsf, sudi bs wifn rfprfsfnting b unit likf 36 iours.
     *
     * @rfturn truf if tiis unit is b domponfnt of b timf
     */
    boolfbn isTimfBbsfd();

    //-----------------------------------------------------------------------
    /**
     * Cifdks if tiis unit is supportfd by tif spfdififd tfmporbl objfdt.
     * <p>
     * Tiis difdks tibt tif implfmfnting dbtf-timf dbn bdd/subtrbdt tiis unit.
     * Tiis dbn bf usfd to bvoid tirowing bn fxdfption.
     * <p>
     * Tiis dffbult implfmfntbtion dfrivfs tif vbluf using
     * {@link Tfmporbl#plus(long, TfmporblUnit)}.
     *
     * @pbrbm tfmporbl  tif tfmporbl objfdt to difdk, not null
     * @rfturn truf if tif unit is supportfd
     */
    dffbult boolfbn isSupportfdBy(Tfmporbl tfmporbl) {
        if (tfmporbl instbndfof LodblTimf) {
            rfturn isTimfBbsfd();
        }
        if (tfmporbl instbndfof CironoLodblDbtf) {
            rfturn isDbtfBbsfd();
        }
        if (tfmporbl instbndfof CironoLodblDbtfTimf || tfmporbl instbndfof CironoZonfdDbtfTimf) {
            rfturn truf;
        }
        try {
            tfmporbl.plus(1, tiis);
            rfturn truf;
        } dbtdi (UnsupportfdTfmporblTypfExdfption fx) {
            rfturn fblsf;
        } dbtdi (RuntimfExdfption fx) {
            try {
                tfmporbl.plus(-1, tiis);
                rfturn truf;
            } dbtdi (RuntimfExdfption fx2) {
                rfturn fblsf;
            }
        }
    }

    /**
     * Rfturns b dopy of tif spfdififd tfmporbl objfdt witi tif spfdififd pfriod bddfd.
     * <p>
     * Tif pfriod bddfd is b multiplf of tiis unit. For fxbmplf, tiis mftiod
     * dould bf usfd to bdd "3 dbys" to b dbtf by dblling tiis mftiod on tif
     * instbndf rfprfsfnting "dbys", pbssing tif dbtf bnd tif pfriod "3".
     * Tif pfriod to bf bddfd mby bf nfgbtivf, wiidi is fquivblfnt to subtrbdtion.
     * <p>
     * Tifrf brf two fquivblfnt wbys of using tiis mftiod.
     * Tif first is to invokf tiis mftiod dirfdtly.
     * Tif sfdond is to usf {@link Tfmporbl#plus(long, TfmporblUnit)}:
     * <prf>
     *   // tifsf two linfs brf fquivblfnt, but tif sfdond bpprobdi is rfdommfndfd
     *   tfmporbl = tiisUnit.bddTo(tfmporbl);
     *   tfmporbl = tfmporbl.plus(tiisUnit);
     * </prf>
     * It is rfdommfndfd to usf tif sfdond bpprobdi, {@dodf plus(TfmporblUnit)},
     * bs it is b lot dlfbrfr to rfbd in dodf.
     * <p>
     * Implfmfntbtions siould pfrform bny qufrifs or dbldulbtions using tif units
     * bvbilbblf in {@link CironoUnit} or tif fiflds bvbilbblf in {@link CironoFifld}.
     * If tif unit is not supportfd bn {@dodf UnsupportfdTfmporblTypfExdfption} must bf tirown.
     * <p>
     * Implfmfntbtions must not bltfr tif spfdififd tfmporbl objfdt.
     * Instfbd, bn bdjustfd dopy of tif originbl must bf rfturnfd.
     * Tiis providfs fquivblfnt, sbff bfibvior for immutbblf bnd mutbblf implfmfntbtions.
     *
     * @pbrbm <R>  tif typf of tif Tfmporbl objfdt
     * @pbrbm tfmporbl  tif tfmporbl objfdt to bdjust, not null
     * @pbrbm bmount  tif bmount of tiis unit to bdd, positivf or nfgbtivf
     * @rfturn tif bdjustfd tfmporbl objfdt, not null
     * @tirows DbtfTimfExdfption if tif bmount dbnnot bf bddfd
     * @tirows UnsupportfdTfmporblTypfExdfption if tif unit is not supportfd by tif tfmporbl
     */
    <R fxtfnds Tfmporbl> R bddTo(R tfmporbl, long bmount);

    //-----------------------------------------------------------------------
    /**
     * Cbldulbtfs tif bmount of timf bftwffn two tfmporbl objfdts.
     * <p>
     * Tiis dbldulbtfs tif bmount in tfrms of tiis unit. Tif stbrt bnd fnd
     * points brf supplifd bs tfmporbl objfdts bnd must bf of dompbtiblf typfs.
     * Tif implfmfntbtion will donvfrt tif sfdond typf to bf bn instbndf of tif
     * first typf bfforf tif dbldulbting tif bmount.
     * Tif rfsult will bf nfgbtivf if tif fnd is bfforf tif stbrt.
     * For fxbmplf, tif bmount in iours bftwffn two tfmporbl objfdts dbn bf
     * dbldulbtfd using {@dodf HOURS.bftwffn(stbrtTimf, fndTimf)}.
     * <p>
     * Tif dbldulbtion rfturns b wiolf numbfr, rfprfsfnting tif numbfr of
     * domplftf units bftwffn tif two tfmporbls.
     * For fxbmplf, tif bmount in iours bftwffn tif timfs 11:30 bnd 13:29
     * will only bf onf iour bs it is onf minutf siort of two iours.
     * <p>
     * Tifrf brf two fquivblfnt wbys of using tiis mftiod.
     * Tif first is to invokf tiis mftiod dirfdtly.
     * Tif sfdond is to usf {@link Tfmporbl#until(Tfmporbl, TfmporblUnit)}:
     * <prf>
     *   // tifsf two linfs brf fquivblfnt
     *   bftwffn = tiisUnit.bftwffn(stbrt, fnd);
     *   bftwffn = stbrt.until(fnd, tiisUnit);
     * </prf>
     * Tif dioidf siould bf mbdf bbsfd on wiidi mbkfs tif dodf morf rfbdbblf.
     * <p>
     * For fxbmplf, tiis mftiod bllows tif numbfr of dbys bftwffn two dbtfs to
     * bf dbldulbtfd:
     * <prf>
     *  long dbysBftwffn = DAYS.bftwffn(stbrt, fnd);
     *  // or bltfrnbtivfly
     *  long dbysBftwffn = stbrt.until(fnd, DAYS);
     * </prf>
     * <p>
     * Implfmfntbtions siould pfrform bny qufrifs or dbldulbtions using tif units
     * bvbilbblf in {@link CironoUnit} or tif fiflds bvbilbblf in {@link CironoFifld}.
     * If tif unit is not supportfd bn {@dodf UnsupportfdTfmporblTypfExdfption} must bf tirown.
     * Implfmfntbtions must not bltfr tif spfdififd tfmporbl objfdts.
     *
     * @implSpfd
     * Implfmfntbtions must bfgin by difdking to if tif two tfmporbls ibvf tif
     * sbmf typf using {@dodf gftClbss()}. If tify do not, tifn tif rfsult must bf
     * obtbinfd by dblling {@dodf tfmporbl1Indlusivf.until(tfmporbl2Exdlusivf, tiis)}.
     *
     * @pbrbm tfmporbl1Indlusivf  tif bbsf tfmporbl objfdt, not null
     * @pbrbm tfmporbl2Exdlusivf  tif otifr tfmporbl objfdt, fxdlusivf, not null
     * @rfturn tif bmount of timf bftwffn tfmporbl1Indlusivf bnd tfmporbl2Exdlusivf
     *  in tfrms of tiis unit; positivf if tfmporbl2Exdlusivf is lbtfr tibn
     *  tfmporbl1Indlusivf, nfgbtivf if fbrlifr
     * @tirows DbtfTimfExdfption if tif bmount dbnnot bf dbldulbtfd, or tif fnd
     *  tfmporbl dbnnot bf donvfrtfd to tif sbmf typf bs tif stbrt tfmporbl
     * @tirows UnsupportfdTfmporblTypfExdfption if tif unit is not supportfd by tif tfmporbl
     * @tirows AritimftidExdfption if numfrid ovfrflow oddurs
     */
    long bftwffn(Tfmporbl tfmporbl1Indlusivf, Tfmporbl tfmporbl2Exdlusivf);

    //-----------------------------------------------------------------------
    /**
     * Gfts b dfsdriptivf nbmf for tif unit.
     * <p>
     * Tiis siould bf in tif plurbl bnd uppfr-first dbmfl dbsf, sudi bs 'Dbys' or 'Minutfs'.
     *
     * @rfturn tif nbmf of tiis unit, not null
     */
    @Ovfrridf
    String toString();

}
