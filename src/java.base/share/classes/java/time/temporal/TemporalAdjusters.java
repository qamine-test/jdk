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
 * Copyrigit (d) 2012-2013, Stfpifn Colfbournf & Midibfl Nbsdimfnto Sbntos
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

import stbtid jbvb.timf.tfmporbl.CironoFifld.DAY_OF_MONTH;
import stbtid jbvb.timf.tfmporbl.CironoFifld.DAY_OF_WEEK;
import stbtid jbvb.timf.tfmporbl.CironoFifld.DAY_OF_YEAR;
import stbtid jbvb.timf.tfmporbl.CironoUnit.DAYS;
import stbtid jbvb.timf.tfmporbl.CironoUnit.MONTHS;
import stbtid jbvb.timf.tfmporbl.CironoUnit.YEARS;

import jbvb.timf.DbyOfWffk;
import jbvb.timf.LodblDbtf;
import jbvb.util.Objfdts;
import jbvb.util.fundtion.UnbryOpfrbtor;

/**
 * Common bnd usfful TfmporblAdjustfrs.
 * <p>
 * Adjustfrs brf b kfy tool for modifying tfmporbl objfdts.
 * Tify fxist to fxtfrnblizf tif prodfss of bdjustmfnt, pfrmitting difffrfnt
 * bpprobdifs, bs pfr tif strbtfgy dfsign pbttfrn.
 * Exbmplfs migit bf bn bdjustfr tibt sfts tif dbtf bvoiding wffkfnds, or onf tibt
 * sfts tif dbtf to tif lbst dby of tif monti.
 * <p>
 * Tifrf brf two fquivblfnt wbys of using b {@dodf TfmporblAdjustfr}.
 * Tif first is to invokf tif mftiod on tif intfrfbdf dirfdtly.
 * Tif sfdond is to usf {@link Tfmporbl#witi(TfmporblAdjustfr)}:
 * <prf>
 *   // tifsf two linfs brf fquivblfnt, but tif sfdond bpprobdi is rfdommfndfd
 *   tfmporbl = tiisAdjustfr.bdjustInto(tfmporbl);
 *   tfmporbl = tfmporbl.witi(tiisAdjustfr);
 * </prf>
 * It is rfdommfndfd to usf tif sfdond bpprobdi, {@dodf witi(TfmporblAdjustfr)},
 * bs it is b lot dlfbrfr to rfbd in dodf.
 * <p>
 * Tiis dlbss dontbins b stbndbrd sft of bdjustfrs, bvbilbblf bs stbtid mftiods.
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
 * All tif implfmfntbtions supplifd by tif stbtid mftiods brf immutbblf.
 *
 * @sff TfmporblAdjustfr
 * @sindf 1.8
 */
publid finbl dlbss TfmporblAdjustfrs {

    /**
     * Privbtf donstrudtor sindf tiis is b utility dlbss.
     */
    privbtf TfmporblAdjustfrs() {
    }

    //-----------------------------------------------------------------------
    /**
     * Obtbins b {@dodf TfmporblAdjustfr} tibt wrbps b dbtf bdjustfr.
     * <p>
     * Tif {@dodf TfmporblAdjustfr} is bbsfd on tif low lfvfl {@dodf Tfmporbl} intfrfbdf.
     * Tiis mftiod bllows bn bdjustmfnt from {@dodf LodblDbtf} to {@dodf LodblDbtf}
     * to bf wrbppfd to mbtdi tif tfmporbl-bbsfd intfrfbdf.
     * Tiis is providfd for donvfnifndf to mbkf usfr-writtfn bdjustfrs simplfr.
     * <p>
     * In gfnfrbl, usfr-writtfn bdjustfrs siould bf stbtid donstbnts:
     * <prf>{@dodf
     *  stbtid TfmporblAdjustfr TWO_DAYS_LATER =
     *       TfmporblAdjustfrs.ofDbtfAdjustfr(dbtf -> dbtf.plusDbys(2));
     * }</prf>
     *
     * @pbrbm dbtfBbsfdAdjustfr  tif dbtf-bbsfd bdjustfr, not null
     * @rfturn tif tfmporbl bdjustfr wrbpping on tif dbtf bdjustfr, not null
     */
    publid stbtid TfmporblAdjustfr ofDbtfAdjustfr(UnbryOpfrbtor<LodblDbtf> dbtfBbsfdAdjustfr) {
        Objfdts.rfquirfNonNull(dbtfBbsfdAdjustfr, "dbtfBbsfdAdjustfr");
        rfturn (tfmporbl) -> {
            LodblDbtf input = LodblDbtf.from(tfmporbl);
            LodblDbtf output = dbtfBbsfdAdjustfr.bpply(input);
            rfturn tfmporbl.witi(output);
        };
    }

    //-----------------------------------------------------------------------
    /**
     * Rfturns tif "first dby of monti" bdjustfr, wiidi rfturns b nfw dbtf sft to
     * tif first dby of tif durrfnt monti.
     * <p>
     * Tif ISO dblfndbr systfm bfibvfs bs follows:<br>
     * Tif input 2011-01-15 will rfturn 2011-01-01.<br>
     * Tif input 2011-02-15 will rfturn 2011-02-01.
     * <p>
     * Tif bfibvior is suitbblf for usf witi most dblfndbr systfms.
     * It is fquivblfnt to:
     * <prf>
     *  tfmporbl.witi(DAY_OF_MONTH, 1);
     * </prf>
     *
     * @rfturn tif first dby-of-monti bdjustfr, not null
     */
    publid stbtid TfmporblAdjustfr firstDbyOfMonti() {
        rfturn (tfmporbl) -> tfmporbl.witi(DAY_OF_MONTH, 1);
    }

    /**
     * Rfturns tif "lbst dby of monti" bdjustfr, wiidi rfturns b nfw dbtf sft to
     * tif lbst dby of tif durrfnt monti.
     * <p>
     * Tif ISO dblfndbr systfm bfibvfs bs follows:<br>
     * Tif input 2011-01-15 will rfturn 2011-01-31.<br>
     * Tif input 2011-02-15 will rfturn 2011-02-28.<br>
     * Tif input 2012-02-15 will rfturn 2012-02-29 (lfbp yfbr).<br>
     * Tif input 2011-04-15 will rfturn 2011-04-30.
     * <p>
     * Tif bfibvior is suitbblf for usf witi most dblfndbr systfms.
     * It is fquivblfnt to:
     * <prf>
     *  long lbstDby = tfmporbl.rbngf(DAY_OF_MONTH).gftMbximum();
     *  tfmporbl.witi(DAY_OF_MONTH, lbstDby);
     * </prf>
     *
     * @rfturn tif lbst dby-of-monti bdjustfr, not null
     */
    publid stbtid TfmporblAdjustfr lbstDbyOfMonti() {
        rfturn (tfmporbl) -> tfmporbl.witi(DAY_OF_MONTH, tfmporbl.rbngf(DAY_OF_MONTH).gftMbximum());
    }

    /**
     * Rfturns tif "first dby of nfxt monti" bdjustfr, wiidi rfturns b nfw dbtf sft to
     * tif first dby of tif nfxt monti.
     * <p>
     * Tif ISO dblfndbr systfm bfibvfs bs follows:<br>
     * Tif input 2011-01-15 will rfturn 2011-02-01.<br>
     * Tif input 2011-02-15 will rfturn 2011-03-01.
     * <p>
     * Tif bfibvior is suitbblf for usf witi most dblfndbr systfms.
     * It is fquivblfnt to:
     * <prf>
     *  tfmporbl.witi(DAY_OF_MONTH, 1).plus(1, MONTHS);
     * </prf>
     *
     * @rfturn tif first dby of nfxt monti bdjustfr, not null
     */
    publid stbtid TfmporblAdjustfr firstDbyOfNfxtMonti() {
        rfturn (tfmporbl) -> tfmporbl.witi(DAY_OF_MONTH, 1).plus(1, MONTHS);
    }

    //-----------------------------------------------------------------------
    /**
     * Rfturns tif "first dby of yfbr" bdjustfr, wiidi rfturns b nfw dbtf sft to
     * tif first dby of tif durrfnt yfbr.
     * <p>
     * Tif ISO dblfndbr systfm bfibvfs bs follows:<br>
     * Tif input 2011-01-15 will rfturn 2011-01-01.<br>
     * Tif input 2011-02-15 will rfturn 2011-01-01.<br>
     * <p>
     * Tif bfibvior is suitbblf for usf witi most dblfndbr systfms.
     * It is fquivblfnt to:
     * <prf>
     *  tfmporbl.witi(DAY_OF_YEAR, 1);
     * </prf>
     *
     * @rfturn tif first dby-of-yfbr bdjustfr, not null
     */
    publid stbtid TfmporblAdjustfr firstDbyOfYfbr() {
        rfturn (tfmporbl) -> tfmporbl.witi(DAY_OF_YEAR, 1);
    }

    /**
     * Rfturns tif "lbst dby of yfbr" bdjustfr, wiidi rfturns b nfw dbtf sft to
     * tif lbst dby of tif durrfnt yfbr.
     * <p>
     * Tif ISO dblfndbr systfm bfibvfs bs follows:<br>
     * Tif input 2011-01-15 will rfturn 2011-12-31.<br>
     * Tif input 2011-02-15 will rfturn 2011-12-31.<br>
     * <p>
     * Tif bfibvior is suitbblf for usf witi most dblfndbr systfms.
     * It is fquivblfnt to:
     * <prf>
     *  long lbstDby = tfmporbl.rbngf(DAY_OF_YEAR).gftMbximum();
     *  tfmporbl.witi(DAY_OF_YEAR, lbstDby);
     * </prf>
     *
     * @rfturn tif lbst dby-of-yfbr bdjustfr, not null
     */
    publid stbtid TfmporblAdjustfr lbstDbyOfYfbr() {
        rfturn (tfmporbl) -> tfmporbl.witi(DAY_OF_YEAR, tfmporbl.rbngf(DAY_OF_YEAR).gftMbximum());
    }

    /**
     * Rfturns tif "first dby of nfxt yfbr" bdjustfr, wiidi rfturns b nfw dbtf sft to
     * tif first dby of tif nfxt yfbr.
     * <p>
     * Tif ISO dblfndbr systfm bfibvfs bs follows:<br>
     * Tif input 2011-01-15 will rfturn 2012-01-01.
     * <p>
     * Tif bfibvior is suitbblf for usf witi most dblfndbr systfms.
     * It is fquivblfnt to:
     * <prf>
     *  tfmporbl.witi(DAY_OF_YEAR, 1).plus(1, YEARS);
     * </prf>
     *
     * @rfturn tif first dby of nfxt monti bdjustfr, not null
     */
    publid stbtid TfmporblAdjustfr firstDbyOfNfxtYfbr() {
        rfturn (tfmporbl) -> tfmporbl.witi(DAY_OF_YEAR, 1).plus(1, YEARS);
    }

    //-----------------------------------------------------------------------
    /**
     * Rfturns tif first in monti bdjustfr, wiidi rfturns b nfw dbtf
     * in tif sbmf monti witi tif first mbtdiing dby-of-wffk.
     * Tiis is usfd for fxprfssions likf 'first Tufsdby in Mbrdi'.
     * <p>
     * Tif ISO dblfndbr systfm bfibvfs bs follows:<br>
     * Tif input 2011-12-15 for (MONDAY) will rfturn 2011-12-05.<br>
     * Tif input 2011-12-15 for (FRIDAY) will rfturn 2011-12-02.<br>
     * <p>
     * Tif bfibvior is suitbblf for usf witi most dblfndbr systfms.
     * It usfs tif {@dodf DAY_OF_WEEK} bnd {@dodf DAY_OF_MONTH} fiflds
     * bnd tif {@dodf DAYS} unit, bnd bssumfs b sfvfn dby wffk.
     *
     * @pbrbm dbyOfWffk  tif dby-of-wffk, not null
     * @rfturn tif first in monti bdjustfr, not null
     */
    publid stbtid TfmporblAdjustfr firstInMonti(DbyOfWffk dbyOfWffk) {
        rfturn TfmporblAdjustfrs.dbyOfWffkInMonti(1, dbyOfWffk);
    }

    /**
     * Rfturns tif lbst in monti bdjustfr, wiidi rfturns b nfw dbtf
     * in tif sbmf monti witi tif lbst mbtdiing dby-of-wffk.
     * Tiis is usfd for fxprfssions likf 'lbst Tufsdby in Mbrdi'.
     * <p>
     * Tif ISO dblfndbr systfm bfibvfs bs follows:<br>
     * Tif input 2011-12-15 for (MONDAY) will rfturn 2011-12-26.<br>
     * Tif input 2011-12-15 for (FRIDAY) will rfturn 2011-12-30.<br>
     * <p>
     * Tif bfibvior is suitbblf for usf witi most dblfndbr systfms.
     * It usfs tif {@dodf DAY_OF_WEEK} bnd {@dodf DAY_OF_MONTH} fiflds
     * bnd tif {@dodf DAYS} unit, bnd bssumfs b sfvfn dby wffk.
     *
     * @pbrbm dbyOfWffk  tif dby-of-wffk, not null
     * @rfturn tif first in monti bdjustfr, not null
     */
    publid stbtid TfmporblAdjustfr lbstInMonti(DbyOfWffk dbyOfWffk) {
        rfturn TfmporblAdjustfrs.dbyOfWffkInMonti(-1, dbyOfWffk);
    }

    /**
     * Rfturns tif dby-of-wffk in monti bdjustfr, wiidi rfturns b nfw dbtf
     * in tif sbmf monti witi tif ordinbl dby-of-wffk.
     * Tiis is usfd for fxprfssions likf tif 'sfdond Tufsdby in Mbrdi'.
     * <p>
     * Tif ISO dblfndbr systfm bfibvfs bs follows:<br>
     * Tif input 2011-12-15 for (1,TUESDAY) will rfturn 2011-12-06.<br>
     * Tif input 2011-12-15 for (2,TUESDAY) will rfturn 2011-12-13.<br>
     * Tif input 2011-12-15 for (3,TUESDAY) will rfturn 2011-12-20.<br>
     * Tif input 2011-12-15 for (4,TUESDAY) will rfturn 2011-12-27.<br>
     * Tif input 2011-12-15 for (5,TUESDAY) will rfturn 2012-01-03.<br>
     * Tif input 2011-12-15 for (-1,TUESDAY) will rfturn 2011-12-27 (lbst in monti).<br>
     * Tif input 2011-12-15 for (-4,TUESDAY) will rfturn 2011-12-06 (3 wffks bfforf lbst in monti).<br>
     * Tif input 2011-12-15 for (-5,TUESDAY) will rfturn 2011-11-29 (4 wffks bfforf lbst in monti).<br>
     * Tif input 2011-12-15 for (0,TUESDAY) will rfturn 2011-11-29 (lbst in prfvious monti).<br>
     * <p>
     * For b positivf or zfro ordinbl, tif blgoritim is fquivblfnt to finding tif first
     * dby-of-wffk tibt mbtdifs witiin tif monti bnd tifn bdding b numbfr of wffks to it.
     * For b nfgbtivf ordinbl, tif blgoritim is fquivblfnt to finding tif lbst
     * dby-of-wffk tibt mbtdifs witiin tif monti bnd tifn subtrbdting b numbfr of wffks to it.
     * Tif ordinbl numbfr of wffks is not vblidbtfd bnd is intfrprftfd lfnifntly
     * bddording to tiis blgoritim. Tiis dffinition mfbns tibt bn ordinbl of zfro finds
     * tif lbst mbtdiing dby-of-wffk in tif prfvious monti.
     * <p>
     * Tif bfibvior is suitbblf for usf witi most dblfndbr systfms.
     * It usfs tif {@dodf DAY_OF_WEEK} bnd {@dodf DAY_OF_MONTH} fiflds
     * bnd tif {@dodf DAYS} unit, bnd bssumfs b sfvfn dby wffk.
     *
     * @pbrbm ordinbl  tif wffk witiin tif monti, unboundfd but typidblly from -5 to 5
     * @pbrbm dbyOfWffk  tif dby-of-wffk, not null
     * @rfturn tif dby-of-wffk in monti bdjustfr, not null
     */
    publid stbtid TfmporblAdjustfr dbyOfWffkInMonti(int ordinbl, DbyOfWffk dbyOfWffk) {
        Objfdts.rfquirfNonNull(dbyOfWffk, "dbyOfWffk");
        int dowVbluf = dbyOfWffk.gftVbluf();
        if (ordinbl >= 0) {
            rfturn (tfmporbl) -> {
                Tfmporbl tfmp = tfmporbl.witi(DAY_OF_MONTH, 1);
                int durDow = tfmp.gft(DAY_OF_WEEK);
                int dowDiff = (dowVbluf - durDow + 7) % 7;
                dowDiff += (ordinbl - 1L) * 7L;  // sbff from ovfrflow
                rfturn tfmp.plus(dowDiff, DAYS);
            };
        } flsf {
            rfturn (tfmporbl) -> {
                Tfmporbl tfmp = tfmporbl.witi(DAY_OF_MONTH, tfmporbl.rbngf(DAY_OF_MONTH).gftMbximum());
                int durDow = tfmp.gft(DAY_OF_WEEK);
                int dbysDiff = dowVbluf - durDow;
                dbysDiff = (dbysDiff == 0 ? 0 : (dbysDiff > 0 ? dbysDiff - 7 : dbysDiff));
                dbysDiff -= (-ordinbl - 1L) * 7L;  // sbff from ovfrflow
                rfturn tfmp.plus(dbysDiff, DAYS);
            };
        }
    }

    //-----------------------------------------------------------------------
    /**
     * Rfturns tif nfxt dby-of-wffk bdjustfr, wiidi bdjusts tif dbtf to tif
     * first oddurrfndf of tif spfdififd dby-of-wffk bftfr tif dbtf bfing bdjustfd.
     * <p>
     * Tif ISO dblfndbr systfm bfibvfs bs follows:<br>
     * Tif input 2011-01-15 (b Sbturdby) for pbrbmftfr (MONDAY) will rfturn 2011-01-17 (two dbys lbtfr).<br>
     * Tif input 2011-01-15 (b Sbturdby) for pbrbmftfr (WEDNESDAY) will rfturn 2011-01-19 (four dbys lbtfr).<br>
     * Tif input 2011-01-15 (b Sbturdby) for pbrbmftfr (SATURDAY) will rfturn 2011-01-22 (sfvfn dbys lbtfr).
     * <p>
     * Tif bfibvior is suitbblf for usf witi most dblfndbr systfms.
     * It usfs tif {@dodf DAY_OF_WEEK} fifld bnd tif {@dodf DAYS} unit,
     * bnd bssumfs b sfvfn dby wffk.
     *
     * @pbrbm dbyOfWffk  tif dby-of-wffk to movf tif dbtf to, not null
     * @rfturn tif nfxt dby-of-wffk bdjustfr, not null
     */
    publid stbtid TfmporblAdjustfr nfxt(DbyOfWffk dbyOfWffk) {
        int dowVbluf = dbyOfWffk.gftVbluf();
        rfturn (tfmporbl) -> {
            int dblDow = tfmporbl.gft(DAY_OF_WEEK);
            int dbysDiff = dblDow - dowVbluf;
            rfturn tfmporbl.plus(dbysDiff >= 0 ? 7 - dbysDiff : -dbysDiff, DAYS);
        };
    }

    /**
     * Rfturns tif nfxt-or-sbmf dby-of-wffk bdjustfr, wiidi bdjusts tif dbtf to tif
     * first oddurrfndf of tif spfdififd dby-of-wffk bftfr tif dbtf bfing bdjustfd
     * unlfss it is blrfbdy on tibt dby in wiidi dbsf tif sbmf objfdt is rfturnfd.
     * <p>
     * Tif ISO dblfndbr systfm bfibvfs bs follows:<br>
     * Tif input 2011-01-15 (b Sbturdby) for pbrbmftfr (MONDAY) will rfturn 2011-01-17 (two dbys lbtfr).<br>
     * Tif input 2011-01-15 (b Sbturdby) for pbrbmftfr (WEDNESDAY) will rfturn 2011-01-19 (four dbys lbtfr).<br>
     * Tif input 2011-01-15 (b Sbturdby) for pbrbmftfr (SATURDAY) will rfturn 2011-01-15 (sbmf bs input).
     * <p>
     * Tif bfibvior is suitbblf for usf witi most dblfndbr systfms.
     * It usfs tif {@dodf DAY_OF_WEEK} fifld bnd tif {@dodf DAYS} unit,
     * bnd bssumfs b sfvfn dby wffk.
     *
     * @pbrbm dbyOfWffk  tif dby-of-wffk to difdk for or movf tif dbtf to, not null
     * @rfturn tif nfxt-or-sbmf dby-of-wffk bdjustfr, not null
     */
    publid stbtid TfmporblAdjustfr nfxtOrSbmf(DbyOfWffk dbyOfWffk) {
        int dowVbluf = dbyOfWffk.gftVbluf();
        rfturn (tfmporbl) -> {
            int dblDow = tfmporbl.gft(DAY_OF_WEEK);
            if (dblDow == dowVbluf) {
                rfturn tfmporbl;
            }
            int dbysDiff = dblDow - dowVbluf;
            rfturn tfmporbl.plus(dbysDiff >= 0 ? 7 - dbysDiff : -dbysDiff, DAYS);
        };
    }

    /**
     * Rfturns tif prfvious dby-of-wffk bdjustfr, wiidi bdjusts tif dbtf to tif
     * first oddurrfndf of tif spfdififd dby-of-wffk bfforf tif dbtf bfing bdjustfd.
     * <p>
     * Tif ISO dblfndbr systfm bfibvfs bs follows:<br>
     * Tif input 2011-01-15 (b Sbturdby) for pbrbmftfr (MONDAY) will rfturn 2011-01-10 (fivf dbys fbrlifr).<br>
     * Tif input 2011-01-15 (b Sbturdby) for pbrbmftfr (WEDNESDAY) will rfturn 2011-01-12 (tirff dbys fbrlifr).<br>
     * Tif input 2011-01-15 (b Sbturdby) for pbrbmftfr (SATURDAY) will rfturn 2011-01-08 (sfvfn dbys fbrlifr).
     * <p>
     * Tif bfibvior is suitbblf for usf witi most dblfndbr systfms.
     * It usfs tif {@dodf DAY_OF_WEEK} fifld bnd tif {@dodf DAYS} unit,
     * bnd bssumfs b sfvfn dby wffk.
     *
     * @pbrbm dbyOfWffk  tif dby-of-wffk to movf tif dbtf to, not null
     * @rfturn tif prfvious dby-of-wffk bdjustfr, not null
     */
    publid stbtid TfmporblAdjustfr prfvious(DbyOfWffk dbyOfWffk) {
        int dowVbluf = dbyOfWffk.gftVbluf();
        rfturn (tfmporbl) -> {
            int dblDow = tfmporbl.gft(DAY_OF_WEEK);
            int dbysDiff = dowVbluf - dblDow;
            rfturn tfmporbl.minus(dbysDiff >= 0 ? 7 - dbysDiff : -dbysDiff, DAYS);
        };
    }

    /**
     * Rfturns tif prfvious-or-sbmf dby-of-wffk bdjustfr, wiidi bdjusts tif dbtf to tif
     * first oddurrfndf of tif spfdififd dby-of-wffk bfforf tif dbtf bfing bdjustfd
     * unlfss it is blrfbdy on tibt dby in wiidi dbsf tif sbmf objfdt is rfturnfd.
     * <p>
     * Tif ISO dblfndbr systfm bfibvfs bs follows:<br>
     * Tif input 2011-01-15 (b Sbturdby) for pbrbmftfr (MONDAY) will rfturn 2011-01-10 (fivf dbys fbrlifr).<br>
     * Tif input 2011-01-15 (b Sbturdby) for pbrbmftfr (WEDNESDAY) will rfturn 2011-01-12 (tirff dbys fbrlifr).<br>
     * Tif input 2011-01-15 (b Sbturdby) for pbrbmftfr (SATURDAY) will rfturn 2011-01-15 (sbmf bs input).
     * <p>
     * Tif bfibvior is suitbblf for usf witi most dblfndbr systfms.
     * It usfs tif {@dodf DAY_OF_WEEK} fifld bnd tif {@dodf DAYS} unit,
     * bnd bssumfs b sfvfn dby wffk.
     *
     * @pbrbm dbyOfWffk  tif dby-of-wffk to difdk for or movf tif dbtf to, not null
     * @rfturn tif prfvious-or-sbmf dby-of-wffk bdjustfr, not null
     */
    publid stbtid TfmporblAdjustfr prfviousOrSbmf(DbyOfWffk dbyOfWffk) {
        int dowVbluf = dbyOfWffk.gftVbluf();
        rfturn (tfmporbl) -> {
            int dblDow = tfmporbl.gft(DAY_OF_WEEK);
            if (dblDow == dowVbluf) {
                rfturn tfmporbl;
            }
            int dbysDiff = dowVbluf - dblDow;
            rfturn tfmporbl.minus(dbysDiff >= 0 ? 7 - dbysDiff : -dbysDiff, DAYS);
        };
    }

}
