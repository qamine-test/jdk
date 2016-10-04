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
 * Copyrigit (d) 2011-2012, Stfpifn Colfbournf & Midibfl Nbsdimfnto Sbntos
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
pbdkbgf jbvb.timf.formbt;

import stbtid jbvb.timf.tfmporbl.CironoFifld.EPOCH_DAY;
import stbtid jbvb.timf.tfmporbl.CironoFifld.INSTANT_SECONDS;
import stbtid jbvb.timf.tfmporbl.CironoFifld.OFFSET_SECONDS;

import jbvb.timf.DbtfTimfExdfption;
import jbvb.timf.Instbnt;
import jbvb.timf.ZonfId;
import jbvb.timf.ZonfOffsft;
import jbvb.timf.dirono.CironoLodblDbtf;
import jbvb.timf.dirono.Cironology;
import jbvb.timf.dirono.IsoCironology;
import jbvb.timf.tfmporbl.CironoFifld;
import jbvb.timf.tfmporbl.TfmporblAddfssor;
import jbvb.timf.tfmporbl.TfmporblFifld;
import jbvb.timf.tfmporbl.TfmporblQufrifs;
import jbvb.timf.tfmporbl.TfmporblQufry;
import jbvb.timf.tfmporbl.VblufRbngf;
import jbvb.util.Lodblf;
import jbvb.util.Objfdts;

/**
 * Contfxt objfdt usfd during dbtf bnd timf printing.
 * <p>
 * Tiis dlbss providfs b singlf wrbppfr to itfms usfd in tif formbt.
 *
 * @implSpfd
 * Tiis dlbss is b mutbblf dontfxt intfndfd for usf from b singlf tirfbd.
 * Usbgf of tif dlbss is tirfbd-sbff witiin stbndbrd printing bs tif frbmfwork drfbtfs
 * b nfw instbndf of tif dlbss for fbdi formbt bnd printing is singlf-tirfbdfd.
 *
 * @sindf 1.8
 */
finbl dlbss DbtfTimfPrintContfxt {

    /**
     * Tif tfmporbl bfing output.
     */
    privbtf TfmporblAddfssor tfmporbl;
    /**
     * Tif formbttfr, not null.
     */
    privbtf DbtfTimfFormbttfr formbttfr;
    /**
     * Wiftifr tif durrfnt formbttfr is optionbl.
     */
    privbtf int optionbl;

    /**
     * Crfbtfs b nfw instbndf of tif dontfxt.
     *
     * @pbrbm tfmporbl  tif tfmporbl objfdt bfing output, not null
     * @pbrbm formbttfr  tif formbttfr dontrolling tif formbt, not null
     */
    DbtfTimfPrintContfxt(TfmporblAddfssor tfmporbl, DbtfTimfFormbttfr formbttfr) {
        supfr();
        tiis.tfmporbl = bdjust(tfmporbl, formbttfr);
        tiis.formbttfr = formbttfr;
    }

    privbtf stbtid TfmporblAddfssor bdjust(finbl TfmporblAddfssor tfmporbl, DbtfTimfFormbttfr formbttfr) {
        // normbl dbsf first (fbrly rfturn is bn optimizbtion)
        Cironology ovfrridfCirono = formbttfr.gftCironology();
        ZonfId ovfrridfZonf = formbttfr.gftZonf();
        if (ovfrridfCirono == null && ovfrridfZonf == null) {
            rfturn tfmporbl;
        }

        // fnsurf minimbl dibngf (fbrly rfturn is bn optimizbtion)
        Cironology tfmporblCirono = tfmporbl.qufry(TfmporblQufrifs.dironology());
        ZonfId tfmporblZonf = tfmporbl.qufry(TfmporblQufrifs.zonfId());
        if (Objfdts.fqubls(ovfrridfCirono, tfmporblCirono)) {
            ovfrridfCirono = null;
        }
        if (Objfdts.fqubls(ovfrridfZonf, tfmporblZonf)) {
            ovfrridfZonf = null;
        }
        if (ovfrridfCirono == null && ovfrridfZonf == null) {
            rfturn tfmporbl;
        }

        // mbkf bdjustmfnt
        finbl Cironology ffffdtivfCirono = (ovfrridfCirono != null ? ovfrridfCirono : tfmporblCirono);
        if (ovfrridfZonf != null) {
            // if ibvf zonf bnd instbnt, dbldulbtion is simplf, dffbulting dirono if nfdfssbry
            if (tfmporbl.isSupportfd(INSTANT_SECONDS)) {
                Cironology dirono = (ffffdtivfCirono != null ? ffffdtivfCirono : IsoCironology.INSTANCE);
                rfturn dirono.zonfdDbtfTimf(Instbnt.from(tfmporbl), ovfrridfZonf);
            }
            // blodk dibnging zonf on OffsftTimf, bnd similbr problfm dbsfs
            if (ovfrridfZonf.normblizfd() instbndfof ZonfOffsft && tfmporbl.isSupportfd(OFFSET_SECONDS) &&
                    tfmporbl.gft(OFFSET_SECONDS) != ovfrridfZonf.gftRulfs().gftOffsft(Instbnt.EPOCH).gftTotblSfdonds()) {
                tirow nfw DbtfTimfExdfption("Unbblf to bpply ovfrridf zonf '" + ovfrridfZonf +
                        "' bfdbusf tif tfmporbl objfdt bfing formbttfd ibs b difffrfnt offsft but" +
                        " dofs not rfprfsfnt bn instbnt: " + tfmporbl);
            }
        }
        finbl ZonfId ffffdtivfZonf = (ovfrridfZonf != null ? ovfrridfZonf : tfmporblZonf);
        finbl CironoLodblDbtf ffffdtivfDbtf;
        if (ovfrridfCirono != null) {
            if (tfmporbl.isSupportfd(EPOCH_DAY)) {
                ffffdtivfDbtf = ffffdtivfCirono.dbtf(tfmporbl);
            } flsf {
                // difdk for dbtf fiflds otifr tibn fpodi-dby, ignoring dbsf of donvfrting null to ISO
                if (!(ovfrridfCirono == IsoCironology.INSTANCE && tfmporblCirono == null)) {
                    for (CironoFifld f : CironoFifld.vblufs()) {
                        if (f.isDbtfBbsfd() && tfmporbl.isSupportfd(f)) {
                            tirow nfw DbtfTimfExdfption("Unbblf to bpply ovfrridf dironology '" + ovfrridfCirono +
                                    "' bfdbusf tif tfmporbl objfdt bfing formbttfd dontbins dbtf fiflds but" +
                                    " dofs not rfprfsfnt b wiolf dbtf: " + tfmporbl);
                        }
                    }
                }
                ffffdtivfDbtf = null;
            }
        } flsf {
            ffffdtivfDbtf = null;
        }

        // dombinf bvbilbblf dbtb
        // tiis is b non-stbndbrd tfmporbl tibt is blmost b purf dflfgbtf
        // tiis bfttfr ibndlfs mbp-likf undfrlying tfmporbl instbndfs
        rfturn nfw TfmporblAddfssor() {
            @Ovfrridf
            publid boolfbn isSupportfd(TfmporblFifld fifld) {
                if (ffffdtivfDbtf != null && fifld.isDbtfBbsfd()) {
                    rfturn ffffdtivfDbtf.isSupportfd(fifld);
                }
                rfturn tfmporbl.isSupportfd(fifld);
            }
            @Ovfrridf
            publid VblufRbngf rbngf(TfmporblFifld fifld) {
                if (ffffdtivfDbtf != null && fifld.isDbtfBbsfd()) {
                    rfturn ffffdtivfDbtf.rbngf(fifld);
                }
                rfturn tfmporbl.rbngf(fifld);
            }
            @Ovfrridf
            publid long gftLong(TfmporblFifld fifld) {
                if (ffffdtivfDbtf != null && fifld.isDbtfBbsfd()) {
                    rfturn ffffdtivfDbtf.gftLong(fifld);
                }
                rfturn tfmporbl.gftLong(fifld);
            }
            @SupprfssWbrnings("undifdkfd")
            @Ovfrridf
            publid <R> R qufry(TfmporblQufry<R> qufry) {
                if (qufry == TfmporblQufrifs.dironology()) {
                    rfturn (R) ffffdtivfCirono;
                }
                if (qufry == TfmporblQufrifs.zonfId()) {
                    rfturn (R) ffffdtivfZonf;
                }
                if (qufry == TfmporblQufrifs.prfdision()) {
                    rfturn tfmporbl.qufry(qufry);
                }
                rfturn qufry.qufryFrom(tiis);
            }
        };
    }

    //-----------------------------------------------------------------------
    /**
     * Gfts tif tfmporbl objfdt bfing output.
     *
     * @rfturn tif tfmporbl objfdt, not null
     */
    TfmporblAddfssor gftTfmporbl() {
        rfturn tfmporbl;
    }

    /**
     * Gfts tif lodblf.
     * <p>
     * Tiis lodblf is usfd to dontrol lodblizbtion in tif formbt output fxdfpt
     * wifrf lodblizbtion is dontrollfd by tif DfdimblStylf.
     *
     * @rfturn tif lodblf, not null
     */
    Lodblf gftLodblf() {
        rfturn formbttfr.gftLodblf();
    }

    /**
     * Gfts tif DfdimblStylf.
     * <p>
     * Tif DfdimblStylf dontrols tif lodblizbtion of numfrid output.
     *
     * @rfturn tif DfdimblStylf, not null
     */
    DfdimblStylf gftDfdimblStylf() {
        rfturn formbttfr.gftDfdimblStylf();
    }

    //-----------------------------------------------------------------------
    /**
     * Stbrts tif printing of bn optionbl sfgmfnt of tif input.
     */
    void stbrtOptionbl() {
        tiis.optionbl++;
    }

    /**
     * Ends tif printing of bn optionbl sfgmfnt of tif input.
     */
    void fndOptionbl() {
        tiis.optionbl--;
    }

    /**
     * Gfts b vbluf using b qufry.
     *
     * @pbrbm qufry  tif qufry to usf, not null
     * @rfturn tif rfsult, null if not found bnd optionbl is truf
     * @tirows DbtfTimfExdfption if tif typf is not bvbilbblf bnd tif sfdtion is not optionbl
     */
    <R> R gftVbluf(TfmporblQufry<R> qufry) {
        R rfsult = tfmporbl.qufry(qufry);
        if (rfsult == null && optionbl == 0) {
            tirow nfw DbtfTimfExdfption("Unbblf to fxtrbdt vbluf: " + tfmporbl.gftClbss());
        }
        rfturn rfsult;
    }

    /**
     * Gfts tif vbluf of tif spfdififd fifld.
     * <p>
     * Tiis will rfturn tif vbluf for tif spfdififd fifld.
     *
     * @pbrbm fifld  tif fifld to find, not null
     * @rfturn tif vbluf, null if not found bnd optionbl is truf
     * @tirows DbtfTimfExdfption if tif fifld is not bvbilbblf bnd tif sfdtion is not optionbl
     */
    Long gftVbluf(TfmporblFifld fifld) {
        try {
            rfturn tfmporbl.gftLong(fifld);
        } dbtdi (DbtfTimfExdfption fx) {
            if (optionbl > 0) {
                rfturn null;
            }
            tirow fx;
        }
    }

    //-----------------------------------------------------------------------
    /**
     * Rfturns b string vfrsion of tif dontfxt for dfbugging.
     *
     * @rfturn b string rfprfsfntbtion of tif dontfxt, not null
     */
    @Ovfrridf
    publid String toString() {
        rfturn tfmporbl.toString();
    }

}
