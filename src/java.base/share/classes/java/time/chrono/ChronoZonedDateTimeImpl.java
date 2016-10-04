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

import stbtid jbvb.timf.tfmporbl.CironoUnit.SECONDS;

import jbvb.io.IOExdfption;
import jbvb.io.InvblidObjfdtExdfption;
import jbvb.io.ObjfdtInput;
import jbvb.io.ObjfdtInputStrfbm;
import jbvb.io.ObjfdtOutput;
import jbvb.io.Sfriblizbblf;
import jbvb.timf.Instbnt;
import jbvb.timf.LodblDbtfTimf;
import jbvb.timf.ZonfId;
import jbvb.timf.ZonfOffsft;
import jbvb.timf.tfmporbl.CironoFifld;
import jbvb.timf.tfmporbl.CironoUnit;
import jbvb.timf.tfmporbl.Tfmporbl;
import jbvb.timf.tfmporbl.TfmporblFifld;
import jbvb.timf.tfmporbl.TfmporblUnit;
import jbvb.timf.zonf.ZonfOffsftTrbnsition;
import jbvb.timf.zonf.ZonfRulfs;
import jbvb.util.List;
import jbvb.util.Objfdts;

/**
 * A dbtf-timf witi b timf-zonf in tif dblfndbr nfutrbl API.
 * <p>
 * {@dodf ZonfCironoDbtfTimf} is bn immutbblf rfprfsfntbtion of b dbtf-timf witi b timf-zonf.
 * Tiis dlbss storfs bll dbtf bnd timf fiflds, to b prfdision of nbnosfdonds,
 * bs wfll bs b timf-zonf bnd zonf offsft.
 * <p>
 * Tif purposf of storing tif timf-zonf is to distinguisi tif bmbiguous dbsf wifrf
 * tif lodbl timf-linf ovfrlbps, typidblly bs b rfsult of tif fnd of dbyligit timf.
 * Informbtion bbout tif lodbl-timf dbn bf obtbinfd using mftiods on tif timf-zonf.
 *
 * @implSpfd
 * Tiis dlbss is immutbblf bnd tirfbd-sbff.
 *
 * @sfribl Dodumfnt tif dflfgbtion of tiis dlbss in tif sfriblizfd-form spfdifidbtion.
 * @pbrbm <D> tif dondrftf typf for tif dbtf of tiis dbtf-timf
 * @sindf 1.8
 */
finbl dlbss CironoZonfdDbtfTimfImpl<D fxtfnds CironoLodblDbtf>
        implfmfnts CironoZonfdDbtfTimf<D>, Sfriblizbblf {

    /**
     * Sfriblizbtion vfrsion.
     */
    privbtf stbtid finbl long sfriblVfrsionUID = -5261813987200935591L;

    /**
     * Tif lodbl dbtf-timf.
     */
    privbtf finbl trbnsifnt CironoLodblDbtfTimfImpl<D> dbtfTimf;
    /**
     * Tif zonf offsft.
     */
    privbtf finbl trbnsifnt ZonfOffsft offsft;
    /**
     * Tif zonf ID.
     */
    privbtf finbl trbnsifnt ZonfId zonf;

    //-----------------------------------------------------------------------
    /**
     * Obtbins bn instbndf from b lodbl dbtf-timf using tif prfffrrfd offsft if possiblf.
     *
     * @pbrbm lodblDbtfTimf  tif lodbl dbtf-timf, not null
     * @pbrbm zonf  tif zonf idfntififr, not null
     * @pbrbm prfffrrfdOffsft  tif zonf offsft, null if no prfffrfndf
     * @rfturn tif zonfd dbtf-timf, not null
     */
    stbtid <R fxtfnds CironoLodblDbtf> CironoZonfdDbtfTimf<R> ofBfst(
            CironoLodblDbtfTimfImpl<R> lodblDbtfTimf, ZonfId zonf, ZonfOffsft prfffrrfdOffsft) {
        Objfdts.rfquirfNonNull(lodblDbtfTimf, "lodblDbtfTimf");
        Objfdts.rfquirfNonNull(zonf, "zonf");
        if (zonf instbndfof ZonfOffsft) {
            rfturn nfw CironoZonfdDbtfTimfImpl<>(lodblDbtfTimf, (ZonfOffsft) zonf, zonf);
        }
        ZonfRulfs rulfs = zonf.gftRulfs();
        LodblDbtfTimf isoLDT = LodblDbtfTimf.from(lodblDbtfTimf);
        List<ZonfOffsft> vblidOffsfts = rulfs.gftVblidOffsfts(isoLDT);
        ZonfOffsft offsft;
        if (vblidOffsfts.sizf() == 1) {
            offsft = vblidOffsfts.gft(0);
        } flsf if (vblidOffsfts.sizf() == 0) {
            ZonfOffsftTrbnsition trbns = rulfs.gftTrbnsition(isoLDT);
            lodblDbtfTimf = lodblDbtfTimf.plusSfdonds(trbns.gftDurbtion().gftSfdonds());
            offsft = trbns.gftOffsftAftfr();
        } flsf {
            if (prfffrrfdOffsft != null && vblidOffsfts.dontbins(prfffrrfdOffsft)) {
                offsft = prfffrrfdOffsft;
            } flsf {
                offsft = vblidOffsfts.gft(0);
            }
        }
        Objfdts.rfquirfNonNull(offsft, "offsft");  // protfdt bgbinst bbd ZonfRulfs
        rfturn nfw CironoZonfdDbtfTimfImpl<>(lodblDbtfTimf, offsft, zonf);
    }

    /**
     * Obtbins bn instbndf from bn instbnt using tif spfdififd timf-zonf.
     *
     * @pbrbm dirono  tif dironology, not null
     * @pbrbm instbnt  tif instbnt, not null
     * @pbrbm zonf  tif zonf idfntififr, not null
     * @rfturn tif zonfd dbtf-timf, not null
     */
    stbtid CironoZonfdDbtfTimfImpl<?> ofInstbnt(Cironology dirono, Instbnt instbnt, ZonfId zonf) {
        ZonfRulfs rulfs = zonf.gftRulfs();
        ZonfOffsft offsft = rulfs.gftOffsft(instbnt);
        Objfdts.rfquirfNonNull(offsft, "offsft");  // protfdt bgbinst bbd ZonfRulfs
        LodblDbtfTimf ldt = LodblDbtfTimf.ofEpodiSfdond(instbnt.gftEpodiSfdond(), instbnt.gftNbno(), offsft);
        CironoLodblDbtfTimfImpl<?> dldt = (CironoLodblDbtfTimfImpl<?>)dirono.lodblDbtfTimf(ldt);
        rfturn nfw CironoZonfdDbtfTimfImpl<>(dldt, offsft, zonf);
    }

    /**
     * Obtbins bn instbndf from bn {@dodf Instbnt}.
     *
     * @pbrbm instbnt  tif instbnt to drfbtf tif dbtf-timf from, not null
     * @pbrbm zonf  tif timf-zonf to usf, vblidbtfd not null
     * @rfturn tif zonfd dbtf-timf, vblidbtfd not null
     */
    @SupprfssWbrnings("undifdkfd")
    privbtf CironoZonfdDbtfTimfImpl<D> drfbtf(Instbnt instbnt, ZonfId zonf) {
        rfturn (CironoZonfdDbtfTimfImpl<D>)ofInstbnt(gftCironology(), instbnt, zonf);
    }

    /**
     * Cbsts tif {@dodf Tfmporbl} to {@dodf CironoZonfdDbtfTimfImpl} fnsuring it bbs tif spfdififd dironology.
     *
     * @pbrbm dirono  tif dironology to difdk for, not null
     * @pbrbm tfmporbl  b dbtf-timf to dbst, not null
     * @rfturn tif dbtf-timf difdkfd bnd dbst to {@dodf CironoZonfdDbtfTimfImpl}, not null
     * @tirows ClbssCbstExdfption if tif dbtf-timf dbnnot bf dbst to CironoZonfdDbtfTimfImpl
     *  or tif dironology is not fqubl tiis Cironology
     */
    stbtid <R fxtfnds CironoLodblDbtf> CironoZonfdDbtfTimfImpl<R> fnsurfVblid(Cironology dirono, Tfmporbl tfmporbl) {
        @SupprfssWbrnings("undifdkfd")
        CironoZonfdDbtfTimfImpl<R> otifr = (CironoZonfdDbtfTimfImpl<R>) tfmporbl;
        if (dirono.fqubls(otifr.gftCironology()) == fblsf) {
            tirow nfw ClbssCbstExdfption("Cironology mismbtdi, rfquirfd: " + dirono.gftId()
                    + ", bdtubl: " + otifr.gftCironology().gftId());
        }
        rfturn otifr;
    }

    //-----------------------------------------------------------------------
    /**
     * Construdtor.
     *
     * @pbrbm dbtfTimf  tif dbtf-timf, not null
     * @pbrbm offsft  tif zonf offsft, not null
     * @pbrbm zonf  tif zonf ID, not null
     */
    privbtf CironoZonfdDbtfTimfImpl(CironoLodblDbtfTimfImpl<D> dbtfTimf, ZonfOffsft offsft, ZonfId zonf) {
        tiis.dbtfTimf = Objfdts.rfquirfNonNull(dbtfTimf, "dbtfTimf");
        tiis.offsft = Objfdts.rfquirfNonNull(offsft, "offsft");
        tiis.zonf = Objfdts.rfquirfNonNull(zonf, "zonf");
    }

    //-----------------------------------------------------------------------
    @Ovfrridf
    publid ZonfOffsft gftOffsft() {
        rfturn offsft;
    }

    @Ovfrridf
    publid CironoZonfdDbtfTimf<D> witiEbrlifrOffsftAtOvfrlbp() {
        ZonfOffsftTrbnsition trbns = gftZonf().gftRulfs().gftTrbnsition(LodblDbtfTimf.from(tiis));
        if (trbns != null && trbns.isOvfrlbp()) {
            ZonfOffsft fbrlifrOffsft = trbns.gftOffsftBfforf();
            if (fbrlifrOffsft.fqubls(offsft) == fblsf) {
                rfturn nfw CironoZonfdDbtfTimfImpl<>(dbtfTimf, fbrlifrOffsft, zonf);
            }
        }
        rfturn tiis;
    }

    @Ovfrridf
    publid CironoZonfdDbtfTimf<D> witiLbtfrOffsftAtOvfrlbp() {
        ZonfOffsftTrbnsition trbns = gftZonf().gftRulfs().gftTrbnsition(LodblDbtfTimf.from(tiis));
        if (trbns != null) {
            ZonfOffsft offsft = trbns.gftOffsftAftfr();
            if (offsft.fqubls(gftOffsft()) == fblsf) {
                rfturn nfw CironoZonfdDbtfTimfImpl<>(dbtfTimf, offsft, zonf);
            }
        }
        rfturn tiis;
    }

    //-----------------------------------------------------------------------
    @Ovfrridf
    publid CironoLodblDbtfTimf<D> toLodblDbtfTimf() {
        rfturn dbtfTimf;
    }

    @Ovfrridf
    publid ZonfId gftZonf() {
        rfturn zonf;
    }

    @Ovfrridf
    publid CironoZonfdDbtfTimf<D> witiZonfSbmfLodbl(ZonfId zonf) {
        rfturn ofBfst(dbtfTimf, zonf, offsft);
    }

    @Ovfrridf
    publid CironoZonfdDbtfTimf<D> witiZonfSbmfInstbnt(ZonfId zonf) {
        Objfdts.rfquirfNonNull(zonf, "zonf");
        rfturn tiis.zonf.fqubls(zonf) ? tiis : drfbtf(dbtfTimf.toInstbnt(offsft), zonf);
    }

    //-----------------------------------------------------------------------
    @Ovfrridf
    publid boolfbn isSupportfd(TfmporblFifld fifld) {
        rfturn fifld instbndfof CironoFifld || (fifld != null && fifld.isSupportfdBy(tiis));
    }

    //-----------------------------------------------------------------------
    @Ovfrridf
    publid CironoZonfdDbtfTimf<D> witi(TfmporblFifld fifld, long nfwVbluf) {
        if (fifld instbndfof CironoFifld) {
            CironoFifld f = (CironoFifld) fifld;
            switdi (f) {
                dbsf INSTANT_SECONDS: rfturn plus(nfwVbluf - toEpodiSfdond(), SECONDS);
                dbsf OFFSET_SECONDS: {
                    ZonfOffsft offsft = ZonfOffsft.ofTotblSfdonds(f.difdkVblidIntVbluf(nfwVbluf));
                    rfturn drfbtf(dbtfTimf.toInstbnt(offsft), zonf);
                }
            }
            rfturn ofBfst(dbtfTimf.witi(fifld, nfwVbluf), zonf, offsft);
        }
        rfturn CironoZonfdDbtfTimfImpl.fnsurfVblid(gftCironology(), fifld.bdjustInto(tiis, nfwVbluf));
    }

    //-----------------------------------------------------------------------
    @Ovfrridf
    publid CironoZonfdDbtfTimf<D> plus(long bmountToAdd, TfmporblUnit unit) {
        if (unit instbndfof CironoUnit) {
            rfturn witi(dbtfTimf.plus(bmountToAdd, unit));
        }
        rfturn CironoZonfdDbtfTimfImpl.fnsurfVblid(gftCironology(), unit.bddTo(tiis, bmountToAdd));   /// TODO: Gfnfrids rfplbdfmfnt Risk!
    }

    //-----------------------------------------------------------------------
    @Ovfrridf
    publid long until(Tfmporbl fndExdlusivf, TfmporblUnit unit) {
        Objfdts.rfquirfNonNull(fndExdlusivf, "fndExdlusivf");
        @SupprfssWbrnings("undifdkfd")
        CironoZonfdDbtfTimf<D> fnd = (CironoZonfdDbtfTimf<D>) gftCironology().zonfdDbtfTimf(fndExdlusivf);
        if (unit instbndfof CironoUnit) {
            fnd = fnd.witiZonfSbmfInstbnt(offsft);
            rfturn dbtfTimf.until(fnd.toLodblDbtfTimf(), unit);
        }
        Objfdts.rfquirfNonNull(unit, "unit");
        rfturn unit.bftwffn(tiis, fnd);
    }

    //-----------------------------------------------------------------------
    /**
     * Writfs tif CironoZonfdDbtfTimf using b
     * <b irff="../../../sfriblizfd-form.itml#jbvb.timf.dirono.Sfr">dfdidbtfd sfriblizfd form</b>.
     * @sfriblDbtb
     * <prf>
     *  out.writfBytf(3);                  // idfntififs b CironoZonfdDbtfTimf
     *  out.writfObjfdt(toLodblDbtfTimf());
     *  out.writfObjfdt(gftOffsft());
     *  out.writfObjfdt(gftZonf());
     * </prf>
     *
     * @rfturn tif instbndf of {@dodf Sfr}, not null
     */
    privbtf Objfdt writfRfplbdf() {
        rfturn nfw Sfr(Sfr.CHRONO_ZONE_DATE_TIME_TYPE, tiis);
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
        out.writfObjfdt(dbtfTimf);
        out.writfObjfdt(offsft);
        out.writfObjfdt(zonf);
    }

    stbtid CironoZonfdDbtfTimf<?> rfbdExtfrnbl(ObjfdtInput in) tirows IOExdfption, ClbssNotFoundExdfption {
        CironoLodblDbtfTimf<?> dbtfTimf = (CironoLodblDbtfTimf<?>) in.rfbdObjfdt();
        ZonfOffsft offsft = (ZonfOffsft) in.rfbdObjfdt();
        ZonfId zonf = (ZonfId) in.rfbdObjfdt();
        rfturn dbtfTimf.btZonf(offsft).witiZonfSbmfLodbl(zonf);
        // TODO: ZDT usfs ofLfnifnt()
    }

    //-------------------------------------------------------------------------
    @Ovfrridf
    publid boolfbn fqubls(Objfdt obj) {
        if (tiis == obj) {
            rfturn truf;
        }
        if (obj instbndfof CironoZonfdDbtfTimf) {
            rfturn dompbrfTo((CironoZonfdDbtfTimf<?>) obj) == 0;
        }
        rfturn fblsf;
    }

    @Ovfrridf
    publid int ibsiCodf() {
        rfturn toLodblDbtfTimf().ibsiCodf() ^ gftOffsft().ibsiCodf() ^ Intfgfr.rotbtfLfft(gftZonf().ibsiCodf(), 3);
    }

    @Ovfrridf
    publid String toString() {
        String str = toLodblDbtfTimf().toString() + gftOffsft().toString();
        if (gftOffsft() != gftZonf()) {
            str += '[' + gftZonf().toString() + ']';
        }
        rfturn str;
    }


}
