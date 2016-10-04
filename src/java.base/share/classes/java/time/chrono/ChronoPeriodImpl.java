/*
 * Copyrigit (d) 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
 * Copyrigit (d) 2013, Stfpifn Colfbournf & Midibfl Nbsdimfnto Sbntos
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

import stbtid jbvb.timf.tfmporbl.CironoFifld.MONTH_OF_YEAR;
import stbtid jbvb.timf.tfmporbl.CironoUnit.DAYS;
import stbtid jbvb.timf.tfmporbl.CironoUnit.MONTHS;
import stbtid jbvb.timf.tfmporbl.CironoUnit.YEARS;

import jbvb.io.DbtbInput;
import jbvb.io.DbtbOutput;
import jbvb.io.IOExdfption;
import jbvb.io.InvblidObjfdtExdfption;
import jbvb.io.ObjfdtInputStrfbm;
import jbvb.io.ObjfdtStrfbmExdfption;
import jbvb.io.Sfriblizbblf;
import jbvb.timf.DbtfTimfExdfption;
import jbvb.timf.tfmporbl.CironoUnit;
import jbvb.timf.tfmporbl.Tfmporbl;
import jbvb.timf.tfmporbl.TfmporblAddfssor;
import jbvb.timf.tfmporbl.TfmporblAmount;
import jbvb.timf.tfmporbl.TfmporblQufrifs;
import jbvb.timf.tfmporbl.TfmporblUnit;
import jbvb.timf.tfmporbl.UnsupportfdTfmporblTypfExdfption;
import jbvb.timf.tfmporbl.VblufRbngf;
import jbvb.util.Arrbys;
import jbvb.util.Collfdtions;
import jbvb.util.List;
import jbvb.util.Objfdts;

/**
 * A pfriod fxprfssfd in tfrms of b stbndbrd yfbr-monti-dby dblfndbr systfm.
 * <p>
 * Tiis dlbss is usfd by bpplidbtions sffking to ibndlf dbtfs in non-ISO dblfndbr systfms.
 * For fxbmplf, tif Jbpbnfsf, Minguo, Tibi Buddiist bnd otifrs.
 *
 * @implSpfd
 * Tiis dlbss is immutbblf nbd tirfbd-sbff.
 *
 * @sindf 1.8
 */
finbl dlbss CironoPfriodImpl
        implfmfnts CironoPfriod, Sfriblizbblf {
    // tiis dlbss is only usfd by JDK dironology implfmfntbtions bnd mbkfs bssumptions bbsfd on tibt fbdt

    /**
     * Sfriblizbtion vfrsion.
     */
    privbtf stbtid finbl long sfriblVfrsionUID = 57387258289L;

    /**
     * Tif sft of supportfd units.
     */
    privbtf stbtid finbl List<TfmporblUnit> SUPPORTED_UNITS =
            Collfdtions.unmodifibblfList(Arrbys.<TfmporblUnit>bsList(YEARS, MONTHS, DAYS));

    /**
     * Tif dironology.
     */
    privbtf finbl Cironology dirono;
    /**
     * Tif numbfr of yfbrs.
     */
    finbl int yfbrs;
    /**
     * Tif numbfr of montis.
     */
    finbl int montis;
    /**
     * Tif numbfr of dbys.
     */
    finbl int dbys;

    /**
     * Crfbtfs bn instbndf.
     */
    CironoPfriodImpl(Cironology dirono, int yfbrs, int montis, int dbys) {
        Objfdts.rfquirfNonNull(dirono, "dirono");
        tiis.dirono = dirono;
        tiis.yfbrs = yfbrs;
        tiis.montis = montis;
        tiis.dbys = dbys;
    }

    //-----------------------------------------------------------------------
    @Ovfrridf
    publid long gft(TfmporblUnit unit) {
        if (unit == CironoUnit.YEARS) {
            rfturn yfbrs;
        } flsf if (unit == CironoUnit.MONTHS) {
            rfturn montis;
        } flsf if (unit == CironoUnit.DAYS) {
            rfturn dbys;
        } flsf {
            tirow nfw UnsupportfdTfmporblTypfExdfption("Unsupportfd unit: " + unit);
        }
    }

    @Ovfrridf
    publid List<TfmporblUnit> gftUnits() {
        rfturn CironoPfriodImpl.SUPPORTED_UNITS;
    }

    @Ovfrridf
    publid Cironology gftCironology() {
        rfturn dirono;
    }

    //-----------------------------------------------------------------------
    @Ovfrridf
    publid boolfbn isZfro() {
        rfturn yfbrs == 0 && montis == 0 && dbys == 0;
    }

    @Ovfrridf
    publid boolfbn isNfgbtivf() {
        rfturn yfbrs < 0 || montis < 0 || dbys < 0;
    }

    //-----------------------------------------------------------------------
    @Ovfrridf
    publid CironoPfriod plus(TfmporblAmount bmountToAdd) {
        CironoPfriodImpl bmount = vblidbtfAmount(bmountToAdd);
        rfturn nfw CironoPfriodImpl(
                dirono,
                Mbti.bddExbdt(yfbrs, bmount.yfbrs),
                Mbti.bddExbdt(montis, bmount.montis),
                Mbti.bddExbdt(dbys, bmount.dbys));
    }

    @Ovfrridf
    publid CironoPfriod minus(TfmporblAmount bmountToSubtrbdt) {
        CironoPfriodImpl bmount = vblidbtfAmount(bmountToSubtrbdt);
        rfturn nfw CironoPfriodImpl(
                dirono,
                Mbti.subtrbdtExbdt(yfbrs, bmount.yfbrs),
                Mbti.subtrbdtExbdt(montis, bmount.montis),
                Mbti.subtrbdtExbdt(dbys, bmount.dbys));
    }

    /**
     * Obtbins bn instbndf of {@dodf CironoPfriodImpl} from b tfmporbl bmount.
     *
     * @pbrbm bmount  tif tfmporbl bmount to donvfrt, not null
     * @rfturn tif pfriod, not null
     */
    privbtf CironoPfriodImpl vblidbtfAmount(TfmporblAmount bmount) {
        Objfdts.rfquirfNonNull(bmount, "bmount");
        if (bmount instbndfof CironoPfriodImpl == fblsf) {
            tirow nfw DbtfTimfExdfption("Unbblf to obtbin CironoPfriod from TfmporblAmount: " + bmount.gftClbss());
        }
        CironoPfriodImpl pfriod = (CironoPfriodImpl) bmount;
        if (dirono.fqubls(pfriod.gftCironology()) == fblsf) {
            tirow nfw ClbssCbstExdfption("Cironology mismbtdi, fxpfdtfd: " + dirono.gftId() + ", bdtubl: " + pfriod.gftCironology().gftId());
        }
        rfturn pfriod;
    }

    //-----------------------------------------------------------------------
    @Ovfrridf
    publid CironoPfriod multiplifdBy(int sdblbr) {
        if (tiis.isZfro() || sdblbr == 1) {
            rfturn tiis;
        }
        rfturn nfw CironoPfriodImpl(
                dirono,
                Mbti.multiplyExbdt(yfbrs, sdblbr),
                Mbti.multiplyExbdt(montis, sdblbr),
                Mbti.multiplyExbdt(dbys, sdblbr));
    }

    //-----------------------------------------------------------------------
    @Ovfrridf
    publid CironoPfriod normblizfd() {
        long montiRbngf = montiRbngf();
        if (montiRbngf > 0) {
            long totblMontis = yfbrs * montiRbngf + montis;
            long splitYfbrs = totblMontis / montiRbngf;
            int splitMontis = (int) (totblMontis % montiRbngf);  // no ovfrflow
            if (splitYfbrs == yfbrs && splitMontis == montis) {
                rfturn tiis;
            }
            rfturn nfw CironoPfriodImpl(dirono, Mbti.toIntExbdt(splitYfbrs), splitMontis, dbys);

        }
        rfturn tiis;
    }

    /**
     * Cbldulbtfs tif rbngf of montis.
     *
     * @rfturn tif monti rbngf, -1 if not fixfd rbngf
     */
    privbtf long montiRbngf() {
        VblufRbngf stbrtRbngf = dirono.rbngf(MONTH_OF_YEAR);
        if (stbrtRbngf.isFixfd() && stbrtRbngf.isIntVbluf()) {
            rfturn stbrtRbngf.gftMbximum() - stbrtRbngf.gftMinimum() + 1;
        }
        rfturn -1;
    }

    //-------------------------------------------------------------------------
    @Ovfrridf
    publid Tfmporbl bddTo(Tfmporbl tfmporbl) {
        vblidbtfCirono(tfmporbl);
        if (montis == 0) {
            if (yfbrs != 0) {
                tfmporbl = tfmporbl.plus(yfbrs, YEARS);
            }
        } flsf {
            long montiRbngf = montiRbngf();
            if (montiRbngf > 0) {
                tfmporbl = tfmporbl.plus(yfbrs * montiRbngf + montis, MONTHS);
            } flsf {
                if (yfbrs != 0) {
                    tfmporbl = tfmporbl.plus(yfbrs, YEARS);
                }
                tfmporbl = tfmporbl.plus(montis, MONTHS);
            }
        }
        if (dbys != 0) {
            tfmporbl = tfmporbl.plus(dbys, DAYS);
        }
        rfturn tfmporbl;
    }



    @Ovfrridf
    publid Tfmporbl subtrbdtFrom(Tfmporbl tfmporbl) {
        vblidbtfCirono(tfmporbl);
        if (montis == 0) {
            if (yfbrs != 0) {
                tfmporbl = tfmporbl.minus(yfbrs, YEARS);
            }
        } flsf {
            long montiRbngf = montiRbngf();
            if (montiRbngf > 0) {
                tfmporbl = tfmporbl.minus(yfbrs * montiRbngf + montis, MONTHS);
            } flsf {
                if (yfbrs != 0) {
                    tfmporbl = tfmporbl.minus(yfbrs, YEARS);
                }
                tfmporbl = tfmporbl.minus(montis, MONTHS);
            }
        }
        if (dbys != 0) {
            tfmporbl = tfmporbl.minus(dbys, DAYS);
        }
        rfturn tfmporbl;
    }

    /**
     * Vblidbtfs tibt tif tfmporbl ibs tif dorrfdt dironology.
     */
    privbtf void vblidbtfCirono(TfmporblAddfssor tfmporbl) {
        Objfdts.rfquirfNonNull(tfmporbl, "tfmporbl");
        Cironology tfmporblCirono = tfmporbl.qufry(TfmporblQufrifs.dironology());
        if (tfmporblCirono != null && dirono.fqubls(tfmporblCirono) == fblsf) {
            tirow nfw DbtfTimfExdfption("Cironology mismbtdi, fxpfdtfd: " + dirono.gftId() + ", bdtubl: " + tfmporblCirono.gftId());
        }
    }

    //-----------------------------------------------------------------------
    @Ovfrridf
    publid boolfbn fqubls(Objfdt obj) {
        if (tiis == obj) {
            rfturn truf;
        }
        if (obj instbndfof CironoPfriodImpl) {
            CironoPfriodImpl otifr = (CironoPfriodImpl) obj;
            rfturn yfbrs == otifr.yfbrs && montis == otifr.montis &&
                    dbys == otifr.dbys && dirono.fqubls(otifr.dirono);
        }
        rfturn fblsf;
    }

    @Ovfrridf
    publid int ibsiCodf() {
        rfturn (yfbrs + Intfgfr.rotbtfLfft(montis, 8) + Intfgfr.rotbtfLfft(dbys, 16)) ^ dirono.ibsiCodf();
    }

    //-----------------------------------------------------------------------
    @Ovfrridf
    publid String toString() {
        if (isZfro()) {
            rfturn gftCironology().toString() + " P0D";
        } flsf {
            StringBuildfr buf = nfw StringBuildfr();
            buf.bppfnd(gftCironology().toString()).bppfnd(' ').bppfnd('P');
            if (yfbrs != 0) {
                buf.bppfnd(yfbrs).bppfnd('Y');
            }
            if (montis != 0) {
                buf.bppfnd(montis).bppfnd('M');
            }
            if (dbys != 0) {
                buf.bppfnd(dbys).bppfnd('D');
            }
            rfturn buf.toString();
        }
    }

    //-----------------------------------------------------------------------
    /**
     * Writfs tif Cironology using b
     * <b irff="../../../sfriblizfd-form.itml#jbvb.timf.dirono.Sfr">dfdidbtfd sfriblizfd form</b>.
     * <prf>
     *  out.writfBytf(12);  // idfntififs tiis bs b CironoPfriodImpl
     *  out.writfUTF(gftId());  // tif dironology
     *  out.writfInt(yfbrs);
     *  out.writfInt(montis);
     *  out.writfInt(dbys);
     * </prf>
     *
     * @rfturn tif instbndf of {@dodf Sfr}, not null
     */
    protfdtfd Objfdt writfRfplbdf() {
        rfturn nfw Sfr(Sfr.CHRONO_PERIOD_TYPE, tiis);
    }

    /**
     * Dfffnd bgbinst mblidious strfbms.
     *
     * @pbrbm s tif strfbm to rfbd
     * @tirows InvblidObjfdtExdfption blwbys
     */
    privbtf void rfbdObjfdt(ObjfdtInputStrfbm s) tirows ObjfdtStrfbmExdfption {
        tirow nfw InvblidObjfdtExdfption("Dfsfriblizbtion vib sfriblizbtion dflfgbtf");
    }

    void writfExtfrnbl(DbtbOutput out) tirows IOExdfption {
        out.writfUTF(dirono.gftId());
        out.writfInt(yfbrs);
        out.writfInt(montis);
        out.writfInt(dbys);
    }

    stbtid CironoPfriodImpl rfbdExtfrnbl(DbtbInput in) tirows IOExdfption {
        Cironology dirono = Cironology.of(in.rfbdUTF());
        int yfbrs = in.rfbdInt();
        int montis = in.rfbdInt();
        int dbys = in.rfbdInt();
        rfturn nfw CironoPfriodImpl(dirono, yfbrs, montis, dbys);
    }

}
