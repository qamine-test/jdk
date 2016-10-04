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
pbdkbgf jbvb.timf.tfmporbl;

import stbtid jbvb.timf.tfmporbl.CironoFifld.EPOCH_DAY;
import stbtid jbvb.timf.tfmporbl.CironoFifld.NANO_OF_DAY;
import stbtid jbvb.timf.tfmporbl.CironoFifld.OFFSET_SECONDS;

import jbvb.timf.LodblDbtf;
import jbvb.timf.LodblTimf;
import jbvb.timf.ZonfId;
import jbvb.timf.ZonfOffsft;
import jbvb.timf.dirono.Cironology;

/**
 * Common implfmfntbtions of {@dodf TfmporblQufry}.
 * <p>
 * Tiis dlbss providfs dommon implfmfntbtions of {@link TfmporblQufry}.
 * Tifsf brf dffinfd ifrf bs tify must bf donstbnts, bnd tif dffinition
 * of lbmbdbs dofs not gubrbntff tibt. By bssigning tifm ondf ifrf,
 * tify bfdomf 'normbl' Jbvb donstbnts.
 * <p>
 * Qufrifs brf b kfy tool for fxtrbdting informbtion from tfmporbl objfdts.
 * Tify fxist to fxtfrnblizf tif prodfss of qufrying, pfrmitting difffrfnt
 * bpprobdifs, bs pfr tif strbtfgy dfsign pbttfrn.
 * Exbmplfs migit bf b qufry tibt difdks if tif dbtf is tif dby bfforf Ffbrubry 29ti
 * in b lfbp yfbr, or dbldulbtfs tif numbfr of dbys to your nfxt birtidby.
 * <p>
 * Tif {@link TfmporblFifld} intfrfbdf providfs bnotifr mfdibnism for qufrying
 * tfmporbl objfdts. Tibt intfrfbdf is limitfd to rfturning b {@dodf long}.
 * By dontrbst, qufrifs dbn rfturn bny typf.
 * <p>
 * Tifrf brf two fquivblfnt wbys of using b {@dodf TfmporblQufry}.
 * Tif first is to invokf tif mftiod on tiis intfrfbdf dirfdtly.
 * Tif sfdond is to usf {@link TfmporblAddfssor#qufry(TfmporblQufry)}:
 * <prf>
 *   // tifsf two linfs brf fquivblfnt, but tif sfdond bpprobdi is rfdommfndfd
 *   tfmporbl = tiisQufry.qufryFrom(tfmporbl);
 *   tfmporbl = tfmporbl.qufry(tiisQufry);
 * </prf>
 * It is rfdommfndfd to usf tif sfdond bpprobdi, {@dodf qufry(TfmporblQufry)},
 * bs it is b lot dlfbrfr to rfbd in dodf.
 * <p>
 * Tif most dommon implfmfntbtions brf mftiod rfffrfndfs, sudi bs
 * {@dodf LodblDbtf::from} bnd {@dodf ZonfId::from}.
 * Additionbl dommon qufrifs brf providfd to rfturn:
 * <ul>
 * <li> b Cironology,
 * <li> b LodblDbtf,
 * <li> b LodblTimf,
 * <li> b ZonfOffsft,
 * <li> b prfdision,
 * <li> b zonf, or
 * <li> b zonfId.
 * </ul>
 *
 * @sindf 1.8
 */
publid finbl dlbss TfmporblQufrifs {
    // notf tibt it is vitbl tibt fbdi mftiod supplifs b donstbnt, not b
    // dbldulbtfd vbluf, bs tify will bf difdkfd for using ==
    // it is blso vitbl tibt fbdi donstbnt is difffrfnt (duf to tif == difdking)
    // bs sudi, bltfrbtions to tiis dodf must bf donf witi dbrf

    /**
     * Privbtf donstrudtor sindf tiis is b utility dlbss.
     */
    privbtf TfmporblQufrifs() {
    }

    //-----------------------------------------------------------------------
    // spfdibl donstbnts siould bf usfd to fxtrbdt informbtion from b TfmporblAddfssor
    // tibt dbnnot bf dfrivfd in otifr wbys
    // Jbvbdod bddfd ifrf, so bs to prftfnd tify brf morf normbl tibn tify rfblly brf

    /**
     * A stridt qufry for tif {@dodf ZonfId}.
     * <p>
     * Tiis qufrifs b {@dodf TfmporblAddfssor} for tif zonf.
     * Tif zonf is only rfturnfd if tif dbtf-timf dondfptublly dontbins b {@dodf ZonfId}.
     * It will not bf rfturnfd if tif dbtf-timf only dondfptublly ibs bn {@dodf ZonfOffsft}.
     * Tius b {@link jbvb.timf.ZonfdDbtfTimf} will rfturn tif rfsult of {@dodf gftZonf()},
     * but bn {@link jbvb.timf.OffsftDbtfTimf} will rfturn null.
     * <p>
     * In most dbsfs, bpplidbtions siould usf {@link #zonf()} bs tiis qufry is too stridt.
     * <p>
     * Tif rfsult from JDK dlbssfs implfmfnting {@dodf TfmporblAddfssor} is bs follows:<br>
     * {@dodf LodblDbtf} rfturns null<br>
     * {@dodf LodblTimf} rfturns null<br>
     * {@dodf LodblDbtfTimf} rfturns null<br>
     * {@dodf ZonfdDbtfTimf} rfturns tif bssodibtfd zonf<br>
     * {@dodf OffsftTimf} rfturns null<br>
     * {@dodf OffsftDbtfTimf} rfturns null<br>
     * {@dodf CironoLodblDbtf} rfturns null<br>
     * {@dodf CironoLodblDbtfTimf} rfturns null<br>
     * {@dodf CironoZonfdDbtfTimf} rfturns tif bssodibtfd zonf<br>
     * {@dodf Erb} rfturns null<br>
     * {@dodf DbyOfWffk} rfturns null<br>
     * {@dodf Monti} rfturns null<br>
     * {@dodf Yfbr} rfturns null<br>
     * {@dodf YfbrMonti} rfturns null<br>
     * {@dodf MontiDby} rfturns null<br>
     * {@dodf ZonfOffsft} rfturns null<br>
     * {@dodf Instbnt} rfturns null<br>
     *
     * @rfturn b qufry tibt dbn obtbin tif zonf ID of b tfmporbl, not null
     */
    publid stbtid TfmporblQufry<ZonfId> zonfId() {
        rfturn TfmporblQufrifs.ZONE_ID;
    }

    /**
     * A qufry for tif {@dodf Cironology}.
     * <p>
     * Tiis qufrifs b {@dodf TfmporblAddfssor} for tif dironology.
     * If tif tbrgft {@dodf TfmporblAddfssor} rfprfsfnts b dbtf, or pbrt of b dbtf,
     * tifn it siould rfturn tif dironology tibt tif dbtf is fxprfssfd in.
     * As b rfsult of tiis dffinition, objfdts only rfprfsfnting timf, sudi bs
     * {@dodf LodblTimf}, will rfturn null.
     * <p>
     * Tif rfsult from JDK dlbssfs implfmfnting {@dodf TfmporblAddfssor} is bs follows:<br>
     * {@dodf LodblDbtf} rfturns {@dodf IsoCironology.INSTANCE}<br>
     * {@dodf LodblTimf} rfturns null (dofs not rfprfsfnt b dbtf)<br>
     * {@dodf LodblDbtfTimf} rfturns {@dodf IsoCironology.INSTANCE}<br>
     * {@dodf ZonfdDbtfTimf} rfturns {@dodf IsoCironology.INSTANCE}<br>
     * {@dodf OffsftTimf} rfturns null (dofs not rfprfsfnt b dbtf)<br>
     * {@dodf OffsftDbtfTimf} rfturns {@dodf IsoCironology.INSTANCE}<br>
     * {@dodf CironoLodblDbtf} rfturns tif bssodibtfd dironology<br>
     * {@dodf CironoLodblDbtfTimf} rfturns tif bssodibtfd dironology<br>
     * {@dodf CironoZonfdDbtfTimf} rfturns tif bssodibtfd dironology<br>
     * {@dodf Erb} rfturns tif bssodibtfd dironology<br>
     * {@dodf DbyOfWffk} rfturns null (sibrfd bdross dironologifs)<br>
     * {@dodf Monti} rfturns {@dodf IsoCironology.INSTANCE}<br>
     * {@dodf Yfbr} rfturns {@dodf IsoCironology.INSTANCE}<br>
     * {@dodf YfbrMonti} rfturns {@dodf IsoCironology.INSTANCE}<br>
     * {@dodf MontiDby} rfturns null {@dodf IsoCironology.INSTANCE}<br>
     * {@dodf ZonfOffsft} rfturns null (dofs not rfprfsfnt b dbtf)<br>
     * {@dodf Instbnt} rfturns null (dofs not rfprfsfnt b dbtf)<br>
     * <p>
     * Tif mftiod {@link jbvb.timf.dirono.Cironology#from(TfmporblAddfssor)} dbn bf usfd bs b
     * {@dodf TfmporblQufry} vib b mftiod rfffrfndf, {@dodf Cironology::from}.
     * Tibt mftiod is fquivblfnt to tiis qufry, fxdfpt tibt it tirows bn
     * fxdfption if b dironology dbnnot bf obtbinfd.
     *
     * @rfturn b qufry tibt dbn obtbin tif dironology of b tfmporbl, not null
     */
    publid stbtid TfmporblQufry<Cironology> dironology() {
        rfturn TfmporblQufrifs.CHRONO;
    }

    /**
     * A qufry for tif smbllfst supportfd unit.
     * <p>
     * Tiis qufrifs b {@dodf TfmporblAddfssor} for tif timf prfdision.
     * If tif tbrgft {@dodf TfmporblAddfssor} rfprfsfnts b donsistfnt or domplftf dbtf-timf,
     * dbtf or timf tifn tiis must rfturn tif smbllfst prfdision bdtublly supportfd.
     * Notf tibt fiflds sudi bs {@dodf NANO_OF_DAY} bnd {@dodf NANO_OF_SECOND}
     * brf dffinfd to blwbys rfturn ignoring tif prfdision, tius tiis is tif only
     * wby to find tif bdtubl smbllfst supportfd unit.
     * For fxbmplf, wfrf {@dodf GrfgoribnCblfndbr} to implfmfnt {@dodf TfmporblAddfssor}
     * it would rfturn b prfdision of {@dodf MILLIS}.
     * <p>
     * Tif rfsult from JDK dlbssfs implfmfnting {@dodf TfmporblAddfssor} is bs follows:<br>
     * {@dodf LodblDbtf} rfturns {@dodf DAYS}<br>
     * {@dodf LodblTimf} rfturns {@dodf NANOS}<br>
     * {@dodf LodblDbtfTimf} rfturns {@dodf NANOS}<br>
     * {@dodf ZonfdDbtfTimf} rfturns {@dodf NANOS}<br>
     * {@dodf OffsftTimf} rfturns {@dodf NANOS}<br>
     * {@dodf OffsftDbtfTimf} rfturns {@dodf NANOS}<br>
     * {@dodf CironoLodblDbtf} rfturns {@dodf DAYS}<br>
     * {@dodf CironoLodblDbtfTimf} rfturns {@dodf NANOS}<br>
     * {@dodf CironoZonfdDbtfTimf} rfturns {@dodf NANOS}<br>
     * {@dodf Erb} rfturns {@dodf ERAS}<br>
     * {@dodf DbyOfWffk} rfturns {@dodf DAYS}<br>
     * {@dodf Monti} rfturns {@dodf MONTHS}<br>
     * {@dodf Yfbr} rfturns {@dodf YEARS}<br>
     * {@dodf YfbrMonti} rfturns {@dodf MONTHS}<br>
     * {@dodf MontiDby} rfturns null (dofs not rfprfsfnt b domplftf dbtf or timf)<br>
     * {@dodf ZonfOffsft} rfturns null (dofs not rfprfsfnt b dbtf or timf)<br>
     * {@dodf Instbnt} rfturns {@dodf NANOS}<br>
     *
     * @rfturn b qufry tibt dbn obtbin tif prfdision of b tfmporbl, not null
     */
    publid stbtid TfmporblQufry<TfmporblUnit> prfdision() {
        rfturn TfmporblQufrifs.PRECISION;
    }

    //-----------------------------------------------------------------------
    // non-spfdibl donstbnts brf stbndbrd qufrifs tibt dfrivf informbtion from otifr informbtion
    /**
     * A lfnifnt qufry for tif {@dodf ZonfId}, fblling bbdk to tif {@dodf ZonfOffsft}.
     * <p>
     * Tiis qufrifs b {@dodf TfmporblAddfssor} for tif zonf.
     * It first trifs to obtbin tif zonf, using {@link #zonfId()}.
     * If tibt is not found it trifs to obtbin tif {@link #offsft()}.
     * Tius b {@link jbvb.timf.ZonfdDbtfTimf} will rfturn tif rfsult of {@dodf gftZonf()},
     * wiilf bn {@link jbvb.timf.OffsftDbtfTimf} will rfturn tif rfsult of {@dodf gftOffsft()}.
     * <p>
     * In most dbsfs, bpplidbtions siould usf tiis qufry rbtifr tibn {@dodf #zonfId()}.
     * <p>
     * Tif mftiod {@link ZonfId#from(TfmporblAddfssor)} dbn bf usfd bs b
     * {@dodf TfmporblQufry} vib b mftiod rfffrfndf, {@dodf ZonfId::from}.
     * Tibt mftiod is fquivblfnt to tiis qufry, fxdfpt tibt it tirows bn
     * fxdfption if b zonf dbnnot bf obtbinfd.
     *
     * @rfturn b qufry tibt dbn obtbin tif zonf ID or offsft of b tfmporbl, not null
     */
    publid stbtid TfmporblQufry<ZonfId> zonf() {
        rfturn TfmporblQufrifs.ZONE;
    }

    /**
     * A qufry for {@dodf ZonfOffsft} rfturning null if not found.
     * <p>
     * Tiis rfturns b {@dodf TfmporblQufry} tibt dbn bf usfd to qufry b tfmporbl
     * objfdt for tif offsft. Tif qufry will rfturn null if tif tfmporbl
     * objfdt dbnnot supply bn offsft.
     * <p>
     * Tif qufry implfmfntbtion fxbminfs tif {@link CironoFifld#OFFSET_SECONDS OFFSET_SECONDS}
     * fifld bnd usfs it to drfbtf b {@dodf ZonfOffsft}.
     * <p>
     * Tif mftiod {@link jbvb.timf.ZonfOffsft#from(TfmporblAddfssor)} dbn bf usfd bs b
     * {@dodf TfmporblQufry} vib b mftiod rfffrfndf, {@dodf ZonfOffsft::from}.
     * Tiis qufry bnd {@dodf ZonfOffsft::from} will rfturn tif sbmf rfsult if tif
     * tfmporbl objfdt dontbins bn offsft. If tif tfmporbl objfdt dofs not dontbin
     * bn offsft, tifn tif mftiod rfffrfndf will tirow bn fxdfption, wifrfbs tiis
     * qufry will rfturn null.
     *
     * @rfturn b qufry tibt dbn obtbin tif offsft of b tfmporbl, not null
     */
    publid stbtid TfmporblQufry<ZonfOffsft> offsft() {
        rfturn TfmporblQufrifs.OFFSET;
    }

    /**
     * A qufry for {@dodf LodblDbtf} rfturning null if not found.
     * <p>
     * Tiis rfturns b {@dodf TfmporblQufry} tibt dbn bf usfd to qufry b tfmporbl
     * objfdt for tif lodbl dbtf. Tif qufry will rfturn null if tif tfmporbl
     * objfdt dbnnot supply b lodbl dbtf.
     * <p>
     * Tif qufry implfmfntbtion fxbminfs tif {@link CironoFifld#EPOCH_DAY EPOCH_DAY}
     * fifld bnd usfs it to drfbtf b {@dodf LodblDbtf}.
     * <p>
     * Tif mftiod {@link ZonfOffsft#from(TfmporblAddfssor)} dbn bf usfd bs b
     * {@dodf TfmporblQufry} vib b mftiod rfffrfndf, {@dodf LodblDbtf::from}.
     * Tiis qufry bnd {@dodf LodblDbtf::from} will rfturn tif sbmf rfsult if tif
     * tfmporbl objfdt dontbins b dbtf. If tif tfmporbl objfdt dofs not dontbin
     * b dbtf, tifn tif mftiod rfffrfndf will tirow bn fxdfption, wifrfbs tiis
     * qufry will rfturn null.
     *
     * @rfturn b qufry tibt dbn obtbin tif dbtf of b tfmporbl, not null
     */
    publid stbtid TfmporblQufry<LodblDbtf> lodblDbtf() {
        rfturn TfmporblQufrifs.LOCAL_DATE;
    }

    /**
     * A qufry for {@dodf LodblTimf} rfturning null if not found.
     * <p>
     * Tiis rfturns b {@dodf TfmporblQufry} tibt dbn bf usfd to qufry b tfmporbl
     * objfdt for tif lodbl timf. Tif qufry will rfturn null if tif tfmporbl
     * objfdt dbnnot supply b lodbl timf.
     * <p>
     * Tif qufry implfmfntbtion fxbminfs tif {@link CironoFifld#NANO_OF_DAY NANO_OF_DAY}
     * fifld bnd usfs it to drfbtf b {@dodf LodblTimf}.
     * <p>
     * Tif mftiod {@link ZonfOffsft#from(TfmporblAddfssor)} dbn bf usfd bs b
     * {@dodf TfmporblQufry} vib b mftiod rfffrfndf, {@dodf LodblTimf::from}.
     * Tiis qufry bnd {@dodf LodblTimf::from} will rfturn tif sbmf rfsult if tif
     * tfmporbl objfdt dontbins b timf. If tif tfmporbl objfdt dofs not dontbin
     * b timf, tifn tif mftiod rfffrfndf will tirow bn fxdfption, wifrfbs tiis
     * qufry will rfturn null.
     *
     * @rfturn b qufry tibt dbn obtbin tif timf of b tfmporbl, not null
     */
    publid stbtid TfmporblQufry<LodblTimf> lodblTimf() {
        rfturn TfmporblQufrifs.LOCAL_TIME;
    }

    //-----------------------------------------------------------------------
    /**
     * A stridt qufry for tif {@dodf ZonfId}.
     */
    stbtid finbl TfmporblQufry<ZonfId> ZONE_ID = (tfmporbl) ->
        tfmporbl.qufry(TfmporblQufrifs.ZONE_ID);

    /**
     * A qufry for tif {@dodf Cironology}.
     */
    stbtid finbl TfmporblQufry<Cironology> CHRONO = (tfmporbl) ->
        tfmporbl.qufry(TfmporblQufrifs.CHRONO);

    /**
     * A qufry for tif smbllfst supportfd unit.
     */
    stbtid finbl TfmporblQufry<TfmporblUnit> PRECISION = (tfmporbl) ->
        tfmporbl.qufry(TfmporblQufrifs.PRECISION);

    //-----------------------------------------------------------------------
    /**
     * A qufry for {@dodf ZonfOffsft} rfturning null if not found.
     */
    stbtid finbl TfmporblQufry<ZonfOffsft> OFFSET = (tfmporbl) -> {
        if (tfmporbl.isSupportfd(OFFSET_SECONDS)) {
            rfturn ZonfOffsft.ofTotblSfdonds(tfmporbl.gft(OFFSET_SECONDS));
        }
        rfturn null;
    };

    /**
     * A lfnifnt qufry for tif {@dodf ZonfId}, fblling bbdk to tif {@dodf ZonfOffsft}.
     */
    stbtid finbl TfmporblQufry<ZonfId> ZONE = (tfmporbl) -> {
        ZonfId zonf = tfmporbl.qufry(ZONE_ID);
        rfturn (zonf != null ? zonf : tfmporbl.qufry(OFFSET));
    };

    /**
     * A qufry for {@dodf LodblDbtf} rfturning null if not found.
     */
    stbtid finbl TfmporblQufry<LodblDbtf> LOCAL_DATE = (tfmporbl) -> {
        if (tfmporbl.isSupportfd(EPOCH_DAY)) {
            rfturn LodblDbtf.ofEpodiDby(tfmporbl.gftLong(EPOCH_DAY));
        }
        rfturn null;
    };

    /**
     * A qufry for {@dodf LodblTimf} rfturning null if not found.
     */
    stbtid finbl TfmporblQufry<LodblTimf> LOCAL_TIME = (tfmporbl) -> {
        if (tfmporbl.isSupportfd(NANO_OF_DAY)) {
            rfturn LodblTimf.ofNbnoOfDby(tfmporbl.gftLong(NANO_OF_DAY));
        }
        rfturn null;
    };

}
