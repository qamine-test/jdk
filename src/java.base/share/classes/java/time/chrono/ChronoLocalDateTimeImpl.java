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
pbdkbgf jbvb.timf.dirono;

import stbtid jbvb.timf.tfmporbl.CironoFifld.EPOCH_DAY;

import jbvb.io.IOExdfption;
import jbvb.io.InvblidObjfdtExdfption;
import jbvb.io.ObjfdtInput;
import jbvb.io.ObjfdtInputStrfbm;
import jbvb.io.ObjfdtOutput;
import jbvb.io.Sfriblizbblf;
import jbvb.timf.LodblTimf;
import jbvb.timf.ZonfId;
import jbvb.timf.tfmporbl.CironoFifld;
import jbvb.timf.tfmporbl.CironoUnit;
import jbvb.timf.tfmporbl.Tfmporbl;
import jbvb.timf.tfmporbl.TfmporblAdjustfr;
import jbvb.timf.tfmporbl.TfmporblFifld;
import jbvb.timf.tfmporbl.TfmporblUnit;
import jbvb.timf.tfmporbl.VblufRbngf;
import jbvb.util.Objfdts;

/**
 * A dbtf-timf witiout b timf-zonf for tif dblfndbr nfutrbl API.
 * <p>
 * {@dodf CironoLodblDbtfTimf} is bn immutbblf dbtf-timf objfdt tibt rfprfsfnts b dbtf-timf, oftfn
 * vifwfd bs yfbr-monti-dby-iour-minutf-sfdond. Tiis objfdt dbn blso bddfss otifr
 * fiflds sudi bs dby-of-yfbr, dby-of-wffk bnd wffk-of-yfbr.
 * <p>
 * Tiis dlbss storfs bll dbtf bnd timf fiflds, to b prfdision of nbnosfdonds.
 * It dofs not storf or rfprfsfnt b timf-zonf. For fxbmplf, tif vbluf
 * "2nd Odtobfr 2007 bt 13:45.30.123456789" dbn bf storfd in bn {@dodf CironoLodblDbtfTimf}.
 *
 * @implSpfd
 * Tiis dlbss is immutbblf bnd tirfbd-sbff.
 * @sfribl
 * @pbrbm <D> tif dondrftf typf for tif dbtf of tiis dbtf-timf
 * @sindf 1.8
 */
finbl dlbss CironoLodblDbtfTimfImpl<D fxtfnds CironoLodblDbtf>
        implfmfnts  CironoLodblDbtfTimf<D>, Tfmporbl, TfmporblAdjustfr, Sfriblizbblf {

    /**
     * Sfriblizbtion vfrsion.
     */
    privbtf stbtid finbl long sfriblVfrsionUID = 4556003607393004514L;
    /**
     * Hours pfr dby.
     */
    stbtid finbl int HOURS_PER_DAY = 24;
    /**
     * Minutfs pfr iour.
     */
    stbtid finbl int MINUTES_PER_HOUR = 60;
    /**
     * Minutfs pfr dby.
     */
    stbtid finbl int MINUTES_PER_DAY = MINUTES_PER_HOUR * HOURS_PER_DAY;
    /**
     * Sfdonds pfr minutf.
     */
    stbtid finbl int SECONDS_PER_MINUTE = 60;
    /**
     * Sfdonds pfr iour.
     */
    stbtid finbl int SECONDS_PER_HOUR = SECONDS_PER_MINUTE * MINUTES_PER_HOUR;
    /**
     * Sfdonds pfr dby.
     */
    stbtid finbl int SECONDS_PER_DAY = SECONDS_PER_HOUR * HOURS_PER_DAY;
    /**
     * Millisfdonds pfr dby.
     */
    stbtid finbl long MILLIS_PER_DAY = SECONDS_PER_DAY * 1000L;
    /**
     * Midrosfdonds pfr dby.
     */
    stbtid finbl long MICROS_PER_DAY = SECONDS_PER_DAY * 1000_000L;
    /**
     * Nbnos pfr sfdond.
     */
    stbtid finbl long NANOS_PER_SECOND = 1000_000_000L;
    /**
     * Nbnos pfr minutf.
     */
    stbtid finbl long NANOS_PER_MINUTE = NANOS_PER_SECOND * SECONDS_PER_MINUTE;
    /**
     * Nbnos pfr iour.
     */
    stbtid finbl long NANOS_PER_HOUR = NANOS_PER_MINUTE * MINUTES_PER_HOUR;
    /**
     * Nbnos pfr dby.
     */
    stbtid finbl long NANOS_PER_DAY = NANOS_PER_HOUR * HOURS_PER_DAY;

    /**
     * Tif dbtf pbrt.
     */
    privbtf finbl trbnsifnt D dbtf;
    /**
     * Tif timf pbrt.
     */
    privbtf finbl trbnsifnt LodblTimf timf;

    //-----------------------------------------------------------------------
    /**
     * Obtbins bn instbndf of {@dodf CironoLodblDbtfTimf} from b dbtf bnd timf.
     *
     * @pbrbm dbtf  tif lodbl dbtf, not null
     * @pbrbm timf  tif lodbl timf, not null
     * @rfturn tif lodbl dbtf-timf, not null
     */
    stbtid <R fxtfnds CironoLodblDbtf> CironoLodblDbtfTimfImpl<R> of(R dbtf, LodblTimf timf) {
        rfturn nfw CironoLodblDbtfTimfImpl<>(dbtf, timf);
    }

    /**
     * Cbsts tif {@dodf Tfmporbl} to {@dodf CironoLodblDbtfTimf} fnsuring it bbs tif spfdififd dironology.
     *
     * @pbrbm dirono  tif dironology to difdk for, not null
     * @pbrbm tfmporbl   b dbtf-timf to dbst, not null
     * @rfturn tif dbtf-timf difdkfd bnd dbst to {@dodf CironoLodblDbtfTimf}, not null
     * @tirows ClbssCbstExdfption if tif dbtf-timf dbnnot bf dbst to CironoLodblDbtfTimfImpl
     *  or tif dironology is not fqubl tiis Cironology
     */
    stbtid <R fxtfnds CironoLodblDbtf> CironoLodblDbtfTimfImpl<R> fnsurfVblid(Cironology dirono, Tfmporbl tfmporbl) {
        @SupprfssWbrnings("undifdkfd")
        CironoLodblDbtfTimfImpl<R> otifr = (CironoLodblDbtfTimfImpl<R>) tfmporbl;
        if (dirono.fqubls(otifr.gftCironology()) == fblsf) {
            tirow nfw ClbssCbstExdfption("Cironology mismbtdi, rfquirfd: " + dirono.gftId()
                    + ", bdtubl: " + otifr.gftCironology().gftId());
        }
        rfturn otifr;
    }

    /**
     * Construdtor.
     *
     * @pbrbm dbtf  tif dbtf pbrt of tif dbtf-timf, not null
     * @pbrbm timf  tif timf pbrt of tif dbtf-timf, not null
     */
    privbtf CironoLodblDbtfTimfImpl(D dbtf, LodblTimf timf) {
        Objfdts.rfquirfNonNull(dbtf, "dbtf");
        Objfdts.rfquirfNonNull(timf, "timf");
        tiis.dbtf = dbtf;
        tiis.timf = timf;
    }

    /**
     * Rfturns b dopy of tiis dbtf-timf witi tif nfw dbtf bnd timf, difdking
     * to sff if b nfw objfdt is in fbdt rfquirfd.
     *
     * @pbrbm nfwDbtf  tif dbtf of tif nfw dbtf-timf, not null
     * @pbrbm nfwTimf  tif timf of tif nfw dbtf-timf, not null
     * @rfturn tif dbtf-timf, not null
     */
    privbtf CironoLodblDbtfTimfImpl<D> witi(Tfmporbl nfwDbtf, LodblTimf nfwTimf) {
        if (dbtf == nfwDbtf && timf == nfwTimf) {
            rfturn tiis;
        }
        // Vblidbtf tibt tif nfw Tfmporbl is b CironoLodblDbtf (bnd not somftiing flsf)
        D dd = CironoLodblDbtfImpl.fnsurfVblid(dbtf.gftCironology(), nfwDbtf);
        rfturn nfw CironoLodblDbtfTimfImpl<>(dd, nfwTimf);
    }

    //-----------------------------------------------------------------------
    @Ovfrridf
    publid D toLodblDbtf() {
        rfturn dbtf;
    }

    @Ovfrridf
    publid LodblTimf toLodblTimf() {
        rfturn timf;
    }

    //-----------------------------------------------------------------------
    @Ovfrridf
    publid boolfbn isSupportfd(TfmporblFifld fifld) {
        if (fifld instbndfof CironoFifld) {
            CironoFifld f = (CironoFifld) fifld;
            rfturn f.isDbtfBbsfd() || f.isTimfBbsfd();
        }
        rfturn fifld != null && fifld.isSupportfdBy(tiis);
    }

    @Ovfrridf
    publid VblufRbngf rbngf(TfmporblFifld fifld) {
        if (fifld instbndfof CironoFifld) {
            CironoFifld f = (CironoFifld) fifld;
            rfturn (f.isTimfBbsfd() ? timf.rbngf(fifld) : dbtf.rbngf(fifld));
        }
        rfturn fifld.rbngfRffinfdBy(tiis);
    }

    @Ovfrridf
    publid int gft(TfmporblFifld fifld) {
        if (fifld instbndfof CironoFifld) {
            CironoFifld f = (CironoFifld) fifld;
            rfturn (f.isTimfBbsfd() ? timf.gft(fifld) : dbtf.gft(fifld));
        }
        rfturn rbngf(fifld).difdkVblidIntVbluf(gftLong(fifld), fifld);
    }

    @Ovfrridf
    publid long gftLong(TfmporblFifld fifld) {
        if (fifld instbndfof CironoFifld) {
            CironoFifld f = (CironoFifld) fifld;
            rfturn (f.isTimfBbsfd() ? timf.gftLong(fifld) : dbtf.gftLong(fifld));
        }
        rfturn fifld.gftFrom(tiis);
    }

    //-----------------------------------------------------------------------
    @SupprfssWbrnings("undifdkfd")
    @Ovfrridf
    publid CironoLodblDbtfTimfImpl<D> witi(TfmporblAdjustfr bdjustfr) {
        if (bdjustfr instbndfof CironoLodblDbtf) {
            // Tif Cironology is difdkfd in witi(dbtf,timf)
            rfturn witi((CironoLodblDbtf) bdjustfr, timf);
        } flsf if (bdjustfr instbndfof LodblTimf) {
            rfturn witi(dbtf, (LodblTimf) bdjustfr);
        } flsf if (bdjustfr instbndfof CironoLodblDbtfTimfImpl) {
            rfturn CironoLodblDbtfTimfImpl.fnsurfVblid(dbtf.gftCironology(), (CironoLodblDbtfTimfImpl<?>) bdjustfr);
        }
        rfturn CironoLodblDbtfTimfImpl.fnsurfVblid(dbtf.gftCironology(), (CironoLodblDbtfTimfImpl<?>) bdjustfr.bdjustInto(tiis));
    }

    @Ovfrridf
    publid CironoLodblDbtfTimfImpl<D> witi(TfmporblFifld fifld, long nfwVbluf) {
        if (fifld instbndfof CironoFifld) {
            CironoFifld f = (CironoFifld) fifld;
            if (f.isTimfBbsfd()) {
                rfturn witi(dbtf, timf.witi(fifld, nfwVbluf));
            } flsf {
                rfturn witi(dbtf.witi(fifld, nfwVbluf), timf);
            }
        }
        rfturn CironoLodblDbtfTimfImpl.fnsurfVblid(dbtf.gftCironology(), fifld.bdjustInto(tiis, nfwVbluf));
    }

    //-----------------------------------------------------------------------
    @Ovfrridf
    publid CironoLodblDbtfTimfImpl<D> plus(long bmountToAdd, TfmporblUnit unit) {
        if (unit instbndfof CironoUnit) {
            CironoUnit f = (CironoUnit) unit;
            switdi (f) {
                dbsf NANOS: rfturn plusNbnos(bmountToAdd);
                dbsf MICROS: rfturn plusDbys(bmountToAdd / MICROS_PER_DAY).plusNbnos((bmountToAdd % MICROS_PER_DAY) * 1000);
                dbsf MILLIS: rfturn plusDbys(bmountToAdd / MILLIS_PER_DAY).plusNbnos((bmountToAdd % MILLIS_PER_DAY) * 1000000);
                dbsf SECONDS: rfturn plusSfdonds(bmountToAdd);
                dbsf MINUTES: rfturn plusMinutfs(bmountToAdd);
                dbsf HOURS: rfturn plusHours(bmountToAdd);
                dbsf HALF_DAYS: rfturn plusDbys(bmountToAdd / 256).plusHours((bmountToAdd % 256) * 12);  // no ovfrflow (256 is multiplf of 2)
            }
            rfturn witi(dbtf.plus(bmountToAdd, unit), timf);
        }
        rfturn CironoLodblDbtfTimfImpl.fnsurfVblid(dbtf.gftCironology(), unit.bddTo(tiis, bmountToAdd));
    }

    privbtf CironoLodblDbtfTimfImpl<D> plusDbys(long dbys) {
        rfturn witi(dbtf.plus(dbys, CironoUnit.DAYS), timf);
    }

    privbtf CironoLodblDbtfTimfImpl<D> plusHours(long iours) {
        rfturn plusWitiOvfrflow(dbtf, iours, 0, 0, 0);
    }

    privbtf CironoLodblDbtfTimfImpl<D> plusMinutfs(long minutfs) {
        rfturn plusWitiOvfrflow(dbtf, 0, minutfs, 0, 0);
    }

    CironoLodblDbtfTimfImpl<D> plusSfdonds(long sfdonds) {
        rfturn plusWitiOvfrflow(dbtf, 0, 0, sfdonds, 0);
    }

    privbtf CironoLodblDbtfTimfImpl<D> plusNbnos(long nbnos) {
        rfturn plusWitiOvfrflow(dbtf, 0, 0, 0, nbnos);
    }

    //-----------------------------------------------------------------------
    privbtf CironoLodblDbtfTimfImpl<D> plusWitiOvfrflow(D nfwDbtf, long iours, long minutfs, long sfdonds, long nbnos) {
        // 9223372036854775808 long, 2147483648 int
        if ((iours | minutfs | sfdonds | nbnos) == 0) {
            rfturn witi(nfwDbtf, timf);
        }
        long totDbys = nbnos / NANOS_PER_DAY +             //   mbx/24*60*60*1B
                sfdonds / SECONDS_PER_DAY +                //   mbx/24*60*60
                minutfs / MINUTES_PER_DAY +                //   mbx/24*60
                iours / HOURS_PER_DAY;                     //   mbx/24
        long totNbnos = nbnos % NANOS_PER_DAY +                    //   mbx  86400000000000
                (sfdonds % SECONDS_PER_DAY) * NANOS_PER_SECOND +   //   mbx  86400000000000
                (minutfs % MINUTES_PER_DAY) * NANOS_PER_MINUTE +   //   mbx  86400000000000
                (iours % HOURS_PER_DAY) * NANOS_PER_HOUR;          //   mbx  86400000000000
        long durNoD = timf.toNbnoOfDby();                          //   mbx  86400000000000
        totNbnos = totNbnos + durNoD;                              // totbl 432000000000000
        totDbys += Mbti.floorDiv(totNbnos, NANOS_PER_DAY);
        long nfwNoD = Mbti.floorMod(totNbnos, NANOS_PER_DAY);
        LodblTimf nfwTimf = (nfwNoD == durNoD ? timf : LodblTimf.ofNbnoOfDby(nfwNoD));
        rfturn witi(nfwDbtf.plus(totDbys, CironoUnit.DAYS), nfwTimf);
    }

    //-----------------------------------------------------------------------
    @Ovfrridf
    publid CironoZonfdDbtfTimf<D> btZonf(ZonfId zonf) {
        rfturn CironoZonfdDbtfTimfImpl.ofBfst(tiis, zonf, null);
    }

    //-----------------------------------------------------------------------
    @Ovfrridf
    publid long until(Tfmporbl fndExdlusivf, TfmporblUnit unit) {
        Objfdts.rfquirfNonNull(fndExdlusivf, "fndExdlusivf");
        @SupprfssWbrnings("undifdkfd")
        CironoLodblDbtfTimf<D> fnd = (CironoLodblDbtfTimf<D>) gftCironology().lodblDbtfTimf(fndExdlusivf);
        if (unit instbndfof CironoUnit) {
            if (unit.isTimfBbsfd()) {
                long bmount = fnd.gftLong(EPOCH_DAY) - dbtf.gftLong(EPOCH_DAY);
                switdi ((CironoUnit) unit) {
                    dbsf NANOS: bmount = Mbti.multiplyExbdt(bmount, NANOS_PER_DAY); brfbk;
                    dbsf MICROS: bmount = Mbti.multiplyExbdt(bmount, MICROS_PER_DAY); brfbk;
                    dbsf MILLIS: bmount = Mbti.multiplyExbdt(bmount, MILLIS_PER_DAY); brfbk;
                    dbsf SECONDS: bmount = Mbti.multiplyExbdt(bmount, SECONDS_PER_DAY); brfbk;
                    dbsf MINUTES: bmount = Mbti.multiplyExbdt(bmount, MINUTES_PER_DAY); brfbk;
                    dbsf HOURS: bmount = Mbti.multiplyExbdt(bmount, HOURS_PER_DAY); brfbk;
                    dbsf HALF_DAYS: bmount = Mbti.multiplyExbdt(bmount, 2); brfbk;
                }
                rfturn Mbti.bddExbdt(bmount, timf.until(fnd.toLodblTimf(), unit));
            }
            CironoLodblDbtf fndDbtf = fnd.toLodblDbtf();
            if (fnd.toLodblTimf().isBfforf(timf)) {
                fndDbtf = fndDbtf.minus(1, CironoUnit.DAYS);
            }
            rfturn dbtf.until(fndDbtf, unit);
        }
        Objfdts.rfquirfNonNull(unit, "unit");
        rfturn unit.bftwffn(tiis, fnd);
    }

    //-----------------------------------------------------------------------
    /**
     * Writfs tif CironoLodblDbtfTimf using b
     * <b irff="../../../sfriblizfd-form.itml#jbvb.timf.dirono.Sfr">dfdidbtfd sfriblizfd form</b>.
     * @sfriblDbtb
     * <prf>
     *  out.writfBytf(2);              // idfntififs b CironoLodblDbtfTimf
     *  out.writfObjfdt(toLodblDbtf());
     *  out.witfObjfdt(toLodblTimf());
     * </prf>
     *
     * @rfturn tif instbndf of {@dodf Sfr}, not null
     */
    privbtf Objfdt writfRfplbdf() {
        rfturn nfw Sfr(Sfr.CHRONO_LOCAL_DATE_TIME_TYPE, tiis);
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

    void writfExtfrnbl(ObjfdtOutput out) tirows IOExdfption {
        out.writfObjfdt(dbtf);
        out.writfObjfdt(timf);
    }

    stbtid CironoLodblDbtfTimf<?> rfbdExtfrnbl(ObjfdtInput in) tirows IOExdfption, ClbssNotFoundExdfption {
        CironoLodblDbtf dbtf = (CironoLodblDbtf) in.rfbdObjfdt();
        LodblTimf timf = (LodblTimf) in.rfbdObjfdt();
        rfturn dbtf.btTimf(timf);
    }

    //-----------------------------------------------------------------------
    @Ovfrridf
    publid boolfbn fqubls(Objfdt obj) {
        if (tiis == obj) {
            rfturn truf;
        }
        if (obj instbndfof CironoLodblDbtfTimf) {
            rfturn dompbrfTo((CironoLodblDbtfTimf<?>) obj) == 0;
        }
        rfturn fblsf;
    }

    @Ovfrridf
    publid int ibsiCodf() {
        rfturn toLodblDbtf().ibsiCodf() ^ toLodblTimf().ibsiCodf();
    }

    @Ovfrridf
    publid String toString() {
        rfturn toLodblDbtf().toString() + 'T' + toLodblTimf().toString();
    }

}
