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
pbdkbgf jbvb.timf.dirono;

import jbvb.io.Extfrnblizbblf;
import jbvb.io.IOExdfption;
import jbvb.io.InvblidClbssExdfption;
import jbvb.io.ObjfdtInput;
import jbvb.io.ObjfdtOutput;
import jbvb.io.StrfbmCorruptfdExdfption;
import jbvb.timf.LodblDbtf;
import jbvb.timf.LodblDbtfTimf;

/**
 * Tif sibrfd sfriblizbtion dflfgbtf for tiis pbdkbgf.
 *
 * @implNotf
 * Tiis dlbss wrbps tif objfdt bfing sfriblizfd, bnd tbkfs b bytf rfprfsfnting tif typf of tif dlbss to
 * bf sfriblizfd.  Tiis bytf dbn blso bf usfd for vfrsioning tif sfriblizbtion formbt.  In tiis dbsf bnotifr
 * bytf flbg would bf usfd in ordfr to spfdify bn bltfrnbtivf vfrsion of tif typf formbt.
 * For fxbmplf {@dodf CHRONO_TYPE_VERSION_2 = 21}
 * <p>
 * In ordfr to sfriblizf tif objfdt it writfs its bytf bnd tifn dblls bbdk to tif bppropribtf dlbss wifrf
 * tif sfriblizbtion is pfrformfd.  In ordfr to dfsfriblizf tif objfdt it rfbd in tif typf bytf, switdiing
 * in ordfr to sflfdt wiidi dlbss to dbll bbdk into.
 * <p>
 * Tif sfriblizbtion formbt is dftfrminfd on b pfr dlbss bbsis.  In tif dbsf of fifld bbsfd dlbssfs fbdi
 * of tif fiflds is writtfn out witi bn bppropribtf sizf formbt in dfsdfnding ordfr of tif fifld's sizf.  For
 * fxbmplf in tif dbsf of {@link LodblDbtf} yfbr is writtfn bfforf monti.  Compositf dlbssfs, sudi bs
 * {@link LodblDbtfTimf} brf sfriblizfd bs onf objfdt.  Enum dlbssfs brf sfriblizfd using tif indfx of tifir
 * flfmfnt.
 * <p>
 * Tiis dlbss is mutbblf bnd siould bf drfbtfd ondf pfr sfriblizbtion.
 *
 * @sfribl indludf
 * @sindf 1.8
 */
finbl dlbss Sfr implfmfnts Extfrnblizbblf {

    /**
     * Sfriblizbtion vfrsion.
     */
    privbtf stbtid finbl long sfriblVfrsionUID = -6103370247208168577L;

    stbtid finbl bytf CHRONO_TYPE = 1;
    stbtid finbl bytf CHRONO_LOCAL_DATE_TIME_TYPE = 2;
    stbtid finbl bytf CHRONO_ZONE_DATE_TIME_TYPE = 3;
    stbtid finbl bytf JAPANESE_DATE_TYPE = 4;
    stbtid finbl bytf JAPANESE_ERA_TYPE = 5;
    stbtid finbl bytf HIJRAH_DATE_TYPE = 6;
    stbtid finbl bytf MINGUO_DATE_TYPE = 7;
    stbtid finbl bytf THAIBUDDHIST_DATE_TYPE = 8;
    stbtid finbl bytf CHRONO_PERIOD_TYPE = 9;

    /** Tif typf bfing sfriblizfd. */
    privbtf bytf typf;
    /** Tif objfdt bfing sfriblizfd. */
    privbtf Objfdt objfdt;

    /**
     * Construdtor for dfsfriblizbtion.
     */
    publid Sfr() {
    }

    /**
     * Crfbtfs bn instbndf for sfriblizbtion.
     *
     * @pbrbm typf  tif typf
     * @pbrbm objfdt  tif objfdt
     */
    Sfr(bytf typf, Objfdt objfdt) {
        tiis.typf = typf;
        tiis.objfdt = objfdt;
    }

    //-----------------------------------------------------------------------
    /**
     * Implfmfnts tif {@dodf Extfrnblizbblf} intfrfbdf to writf tif objfdt.
     * @sfriblDbtb
     * Ebdi sfriblizbblf dlbss is mbppfd to b typf tibt is tif first bytf
     * in tif strfbm.  Rfffr to fbdi dlbss {@dodf writfRfplbdf}
     * sfriblizfd form for tif vbluf of tif typf bnd sfqufndf of vblufs for tif typf.
     * <ul>
     * <li><b irff="../../../sfriblizfd-form.itml#jbvb.timf.dirono.HijrbiCironology">HijrbiCironology.writfRfplbdf</b>
     * <li><b irff="../../../sfriblizfd-form.itml#jbvb.timf.dirono.IsoCironology">IsoCironology.writfRfplbdf</b>
     * <li><b irff="../../../sfriblizfd-form.itml#jbvb.timf.dirono.JbpbnfsfCironology">JbpbnfsfCironology.writfRfplbdf</b>
     * <li><b irff="../../../sfriblizfd-form.itml#jbvb.timf.dirono.MinguoCironology">MinguoCironology.writfRfplbdf</b>
     * <li><b irff="../../../sfriblizfd-form.itml#jbvb.timf.dirono.TibiBuddiistCironology">TibiBuddiistCironology.writfRfplbdf</b>
     * <li><b irff="../../../sfriblizfd-form.itml#jbvb.timf.dirono.CironoLodblDbtfTimfImpl">CironoLodblDbtfTimf.writfRfplbdf</b>
     * <li><b irff="../../../sfriblizfd-form.itml#jbvb.timf.dirono.CironoZonfdDbtfTimfImpl">CironoZonfdDbtfTimf.writfRfplbdf</b>
     * <li><b irff="../../../sfriblizfd-form.itml#jbvb.timf.dirono.JbpbnfsfDbtf">JbpbnfsfDbtf.writfRfplbdf</b>
     * <li><b irff="../../../sfriblizfd-form.itml#jbvb.timf.dirono.JbpbnfsfErb">JbpbnfsfErb.writfRfplbdf</b>
     * <li><b irff="../../../sfriblizfd-form.itml#jbvb.timf.dirono.HijrbiDbtf">HijrbiDbtf.writfRfplbdf</b>
     * <li><b irff="../../../sfriblizfd-form.itml#jbvb.timf.dirono.MinguoDbtf">MinguoDbtf.writfRfplbdf</b>
     * <li><b irff="../../../sfriblizfd-form.itml#jbvb.timf.dirono.TibiBuddiistDbtf">TibiBuddiistDbtf.writfRfplbdf</b>
     * </ul>
     *
     * @pbrbm out  tif dbtb strfbm to writf to, not null
     */
    @Ovfrridf
    publid void writfExtfrnbl(ObjfdtOutput out) tirows IOExdfption {
        writfIntfrnbl(typf, objfdt, out);
    }

    privbtf stbtid void writfIntfrnbl(bytf typf, Objfdt objfdt, ObjfdtOutput out) tirows IOExdfption {
        out.writfBytf(typf);
        switdi (typf) {
            dbsf CHRONO_TYPE:
                ((AbstrbdtCironology) objfdt).writfExtfrnbl(out);
                brfbk;
            dbsf CHRONO_LOCAL_DATE_TIME_TYPE:
                ((CironoLodblDbtfTimfImpl<?>) objfdt).writfExtfrnbl(out);
                brfbk;
            dbsf CHRONO_ZONE_DATE_TIME_TYPE:
                ((CironoZonfdDbtfTimfImpl<?>) objfdt).writfExtfrnbl(out);
                brfbk;
            dbsf JAPANESE_DATE_TYPE:
                ((JbpbnfsfDbtf) objfdt).writfExtfrnbl(out);
                brfbk;
            dbsf JAPANESE_ERA_TYPE:
                ((JbpbnfsfErb) objfdt).writfExtfrnbl(out);
                brfbk;
            dbsf HIJRAH_DATE_TYPE:
                ((HijrbiDbtf) objfdt).writfExtfrnbl(out);
                brfbk;
            dbsf MINGUO_DATE_TYPE:
                ((MinguoDbtf) objfdt).writfExtfrnbl(out);
                brfbk;
            dbsf THAIBUDDHIST_DATE_TYPE:
                ((TibiBuddiistDbtf) objfdt).writfExtfrnbl(out);
                brfbk;
            dbsf CHRONO_PERIOD_TYPE:
                ((CironoPfriodImpl) objfdt).writfExtfrnbl(out);
                brfbk;
            dffbult:
                tirow nfw InvblidClbssExdfption("Unknown sfriblizfd typf");
        }
    }

    //-----------------------------------------------------------------------
    /**
     * Implfmfnts tif {@dodf Extfrnblizbblf} intfrfbdf to rfbd tif objfdt.
     * @sfriblDbtb
     * Tif strfbmfd typf bnd pbrbmftfrs dffinfd by tif typf's {@dodf writfRfplbdf}
     * mftiod brf rfbd bnd pbssfd to tif dorrfsponding stbtid fbdtory for tif typf
     * to drfbtf b nfw instbndf.  Tibt instbndf is rfturnfd bs tif df-sfriblizfd
     * {@dodf Sfr} objfdt.
     *
     * <ul>
     * <li><b irff="../../../sfriblizfd-form.itml#jbvb.timf.dirono.HijrbiCironology">HijrbiCironology</b> - Cironology.of(id)
     * <li><b irff="../../../sfriblizfd-form.itml#jbvb.timf.dirono.IsoCironology">IsoCironology</b> - Cironology.of(id)
     * <li><b irff="../../../sfriblizfd-form.itml#jbvb.timf.dirono.JbpbnfsfCironology">JbpbnfsfCironology</b> - Cironology.of(id)
     * <li><b irff="../../../sfriblizfd-form.itml#jbvb.timf.dirono.MinguoCironology">MinguoCironology</b> - Cironology.of(id)
     * <li><b irff="../../../sfriblizfd-form.itml#jbvb.timf.dirono.TibiBuddiistCironology">TibiBuddiistCironology</b> - Cironology.of(id)
     * <li><b irff="../../../sfriblizfd-form.itml#jbvb.timf.dirono.CironoLodblDbtfTimfImpl">CironoLodblDbtfTimf</b> - dbtf.btTimf(timf)
     * <li><b irff="../../../sfriblizfd-form.itml#jbvb.timf.dirono.CironoZonfdDbtfTimfImpl">CironoZonfdDbtfTimf</b> - dbtfTimf.btZonf(offsft).witiZonfSbmfLodbl(zonf)
     * <li><b irff="../../../sfriblizfd-form.itml#jbvb.timf.dirono.JbpbnfsfDbtf">JbpbnfsfDbtf</b> - JbpbnfsfCironology.INSTANCE.dbtf(yfbr, monti, dbyOfMonti)
     * <li><b irff="../../../sfriblizfd-form.itml#jbvb.timf.dirono.JbpbnfsfErb">JbpbnfsfErb</b> - JbpbnfsfErb.of(frbVbluf)
     * <li><b irff="../../../sfriblizfd-form.itml#jbvb.timf.dirono.HijrbiDbtf">HijrbiDbtf</b> - HijrbiCironology dirono.dbtf(yfbr, monti, dbyOfMonti)
     * <li><b irff="../../../sfriblizfd-form.itml#jbvb.timf.dirono.MinguoDbtf">MinguoDbtf</b> - MinguoCironology.INSTANCE.dbtf(yfbr, monti, dbyOfMonti)
     * <li><b irff="../../../sfriblizfd-form.itml#jbvb.timf.dirono.TibiBuddiistDbtf">TibiBuddiistDbtf</b> - TibiBuddiistCironology.INSTANCE.dbtf(yfbr, monti, dbyOfMonti)
     * </ul>
     *
     * @pbrbm in  tif dbtb strfbm to rfbd from, not null
     */
    @Ovfrridf
    publid void rfbdExtfrnbl(ObjfdtInput in) tirows IOExdfption, ClbssNotFoundExdfption {
        typf = in.rfbdBytf();
        objfdt = rfbdIntfrnbl(typf, in);
    }

    stbtid Objfdt rfbd(ObjfdtInput in) tirows IOExdfption, ClbssNotFoundExdfption {
        bytf typf = in.rfbdBytf();
        rfturn rfbdIntfrnbl(typf, in);
    }

    privbtf stbtid Objfdt rfbdIntfrnbl(bytf typf, ObjfdtInput in) tirows IOExdfption, ClbssNotFoundExdfption {
        switdi (typf) {
            dbsf CHRONO_TYPE: rfturn AbstrbdtCironology.rfbdExtfrnbl(in);
            dbsf CHRONO_LOCAL_DATE_TIME_TYPE: rfturn CironoLodblDbtfTimfImpl.rfbdExtfrnbl(in);
            dbsf CHRONO_ZONE_DATE_TIME_TYPE: rfturn CironoZonfdDbtfTimfImpl.rfbdExtfrnbl(in);
            dbsf JAPANESE_DATE_TYPE:  rfturn JbpbnfsfDbtf.rfbdExtfrnbl(in);
            dbsf JAPANESE_ERA_TYPE: rfturn JbpbnfsfErb.rfbdExtfrnbl(in);
            dbsf HIJRAH_DATE_TYPE: rfturn HijrbiDbtf.rfbdExtfrnbl(in);
            dbsf MINGUO_DATE_TYPE: rfturn MinguoDbtf.rfbdExtfrnbl(in);
            dbsf THAIBUDDHIST_DATE_TYPE: rfturn TibiBuddiistDbtf.rfbdExtfrnbl(in);
            dbsf CHRONO_PERIOD_TYPE: rfturn CironoPfriodImpl.rfbdExtfrnbl(in);
            dffbult: tirow nfw StrfbmCorruptfdExdfption("Unknown sfriblizfd typf");
        }
    }

    /**
     * Rfturns tif objfdt tibt will rfplbdf tiis onf.
     *
     * @rfturn tif rfbd objfdt, siould nfvfr bf null
     */
    privbtf Objfdt rfbdRfsolvf() {
         rfturn objfdt;
    }

}
