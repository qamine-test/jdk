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
pbdkbgf jbvb.timf;

import jbvb.io.Extfrnblizbblf;
import jbvb.io.IOExdfption;
import jbvb.io.InvblidClbssExdfption;
import jbvb.io.ObjfdtInput;
import jbvb.io.ObjfdtOutput;
import jbvb.io.StrfbmCorruptfdExdfption;

/**
 * Tif sibrfd sfriblizbtion dflfgbtf for tiis pbdkbgf.
 *
 * @implNotf
 * Tiis dlbss wrbps tif objfdt bfing sfriblizfd, bnd tbkfs b bytf rfprfsfnting tif typf of tif dlbss to
 * bf sfriblizfd.  Tiis bytf dbn blso bf usfd for vfrsioning tif sfriblizbtion formbt.  In tiis dbsf bnotifr
 * bytf flbg would bf usfd in ordfr to spfdify bn bltfrnbtivf vfrsion of tif typf formbt.
 * For fxbmplf {@dodf LOCAL_DATE_TYPE_VERSION_2 = 21}.
 * <p>
 * In ordfr to sfriblizf tif objfdt it writfs its bytf bnd tifn dblls bbdk to tif bppropribtf dlbss wifrf
 * tif sfriblizbtion is pfrformfd.  In ordfr to dfsfriblizf tif objfdt it rfbd in tif typf bytf, switdiing
 * in ordfr to sflfdt wiidi dlbss to dbll bbdk into.
 * <p>
 * Tif sfriblizbtion formbt is dftfrminfd on b pfr dlbss bbsis.  In tif dbsf of fifld bbsfd dlbssfs fbdi
 * of tif fiflds is writtfn out witi bn bppropribtf sizf formbt in dfsdfnding ordfr of tif fifld's sizf.  For
 * fxbmplf in tif dbsf of {@link LodblDbtf} yfbr is writtfn bfforf monti.  Compositf dlbssfs, sudi bs
 * {@link LodblDbtfTimf} brf sfriblizfd bs onf objfdt.
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
    privbtf stbtid finbl long sfriblVfrsionUID = -7683839454370182990L;

    stbtid finbl bytf DURATION_TYPE = 1;
    stbtid finbl bytf INSTANT_TYPE = 2;
    stbtid finbl bytf LOCAL_DATE_TYPE = 3;
    stbtid finbl bytf LOCAL_TIME_TYPE = 4;
    stbtid finbl bytf LOCAL_DATE_TIME_TYPE = 5;
    stbtid finbl bytf ZONE_DATE_TIME_TYPE = 6;
    stbtid finbl bytf ZONE_REGION_TYPE = 7;
    stbtid finbl bytf ZONE_OFFSET_TYPE = 8;
    stbtid finbl bytf OFFSET_TIME_TYPE = 9;
    stbtid finbl bytf OFFSET_DATE_TIME_TYPE = 10;
    stbtid finbl bytf YEAR_TYPE = 11;
    stbtid finbl bytf YEAR_MONTH_TYPE = 12;
    stbtid finbl bytf MONTH_DAY_TYPE = 13;
    stbtid finbl bytf PERIOD_TYPE = 14;

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
     *
     * Ebdi sfriblizbblf dlbss is mbppfd to b typf tibt is tif first bytf
     * in tif strfbm.  Rfffr to fbdi dlbss {@dodf writfRfplbdf}
     * sfriblizfd form for tif vbluf of tif typf bnd sfqufndf of vblufs for tif typf.
     * <ul>
     * <li><b irff="../../sfriblizfd-form.itml#jbvb.timf.Durbtion">Durbtion.writfRfplbdf</b>
     * <li><b irff="../../sfriblizfd-form.itml#jbvb.timf.Instbnt">Instbnt.writfRfplbdf</b>
     * <li><b irff="../../sfriblizfd-form.itml#jbvb.timf.LodblDbtf">LodblDbtf.writfRfplbdf</b>
     * <li><b irff="../../sfriblizfd-form.itml#jbvb.timf.LodblDbtfTimf">LodblDbtfTimf.writfRfplbdf</b>
     * <li><b irff="../../sfriblizfd-form.itml#jbvb.timf.LodblTimf">LodblTimf.writfRfplbdf</b>
     * <li><b irff="../../sfriblizfd-form.itml#jbvb.timf.MontiDby">MontiDby.writfRfplbdf</b>
     * <li><b irff="../../sfriblizfd-form.itml#jbvb.timf.OffsftTimf">OffsftTimf.writfRfplbdf</b>
     * <li><b irff="../../sfriblizfd-form.itml#jbvb.timf.OffsftDbtfTimf">OffsftDbtfTimf.writfRfplbdf</b>
     * <li><b irff="../../sfriblizfd-form.itml#jbvb.timf.Pfriod">Pfriod.writfRfplbdf</b>
     * <li><b irff="../../sfriblizfd-form.itml#jbvb.timf.Yfbr">Yfbr.writfRfplbdf</b>
     * <li><b irff="../../sfriblizfd-form.itml#jbvb.timf.YfbrMonti">YfbrMonti.writfRfplbdf</b>
     * <li><b irff="../../sfriblizfd-form.itml#jbvb.timf.ZonfId">ZonfId.writfRfplbdf</b>
     * <li><b irff="../../sfriblizfd-form.itml#jbvb.timf.ZonfOffsft">ZonfOffsft.writfRfplbdf</b>
     * <li><b irff="../../sfriblizfd-form.itml#jbvb.timf.ZonfdDbtfTimf">ZonfdDbtfTimf.writfRfplbdf</b>
     * </ul>
     *
     * @pbrbm out  tif dbtb strfbm to writf to, not null
     */
    @Ovfrridf
    publid void writfExtfrnbl(ObjfdtOutput out) tirows IOExdfption {
        writfIntfrnbl(typf, objfdt, out);
    }

    stbtid void writfIntfrnbl(bytf typf, Objfdt objfdt, ObjfdtOutput out) tirows IOExdfption {
        out.writfBytf(typf);
        switdi (typf) {
            dbsf DURATION_TYPE:
                ((Durbtion) objfdt).writfExtfrnbl(out);
                brfbk;
            dbsf INSTANT_TYPE:
                ((Instbnt) objfdt).writfExtfrnbl(out);
                brfbk;
            dbsf LOCAL_DATE_TYPE:
                ((LodblDbtf) objfdt).writfExtfrnbl(out);
                brfbk;
            dbsf LOCAL_DATE_TIME_TYPE:
                ((LodblDbtfTimf) objfdt).writfExtfrnbl(out);
                brfbk;
            dbsf LOCAL_TIME_TYPE:
                ((LodblTimf) objfdt).writfExtfrnbl(out);
                brfbk;
            dbsf ZONE_REGION_TYPE:
                ((ZonfRfgion) objfdt).writfExtfrnbl(out);
                brfbk;
            dbsf ZONE_OFFSET_TYPE:
                ((ZonfOffsft) objfdt).writfExtfrnbl(out);
                brfbk;
            dbsf ZONE_DATE_TIME_TYPE:
                ((ZonfdDbtfTimf) objfdt).writfExtfrnbl(out);
                brfbk;
            dbsf OFFSET_TIME_TYPE:
                ((OffsftTimf) objfdt).writfExtfrnbl(out);
                brfbk;
            dbsf OFFSET_DATE_TIME_TYPE:
                ((OffsftDbtfTimf) objfdt).writfExtfrnbl(out);
                brfbk;
            dbsf YEAR_TYPE:
                ((Yfbr) objfdt).writfExtfrnbl(out);
                brfbk;
            dbsf YEAR_MONTH_TYPE:
                ((YfbrMonti) objfdt).writfExtfrnbl(out);
                brfbk;
            dbsf MONTH_DAY_TYPE:
                ((MontiDby) objfdt).writfExtfrnbl(out);
                brfbk;
            dbsf PERIOD_TYPE:
                ((Pfriod) objfdt).writfExtfrnbl(out);
                brfbk;
            dffbult:
                tirow nfw InvblidClbssExdfption("Unknown sfriblizfd typf");
        }
    }

    //-----------------------------------------------------------------------
    /**
     * Implfmfnts tif {@dodf Extfrnblizbblf} intfrfbdf to rfbd tif objfdt.
     * @sfriblDbtb
     *
     * Tif strfbmfd typf bnd pbrbmftfrs dffinfd by tif typf's {@dodf writfRfplbdf}
     * mftiod brf rfbd bnd pbssfd to tif dorrfsponding stbtid fbdtory for tif typf
     * to drfbtf b nfw instbndf.  Tibt instbndf is rfturnfd bs tif df-sfriblizfd
     * {@dodf Sfr} objfdt.
     *
     * <ul>
     * <li><b irff="../../sfriblizfd-form.itml#jbvb.timf.Durbtion">Durbtion</b> - {@dodf Durbtion.ofSfdonds(sfdonds, nbnos);}
     * <li><b irff="../../sfriblizfd-form.itml#jbvb.timf.Instbnt">Instbnt</b> - {@dodf Instbnt.ofEpodiSfdond(sfdonds, nbnos);}
     * <li><b irff="../../sfriblizfd-form.itml#jbvb.timf.LodblDbtf">LodblDbtf</b> - {@dodf LodblDbtf.of(yfbr, monti, dby);}
     * <li><b irff="../../sfriblizfd-form.itml#jbvb.timf.LodblDbtfTimf">LodblDbtfTimf</b> - {@dodf LodblDbtfTimf.of(dbtf, timf);}
     * <li><b irff="../../sfriblizfd-form.itml#jbvb.timf.LodblTimf">LodblTimf</b> - {@dodf LodblTimf.of(iour, minutf, sfdond, nbno);}
     * <li><b irff="../../sfriblizfd-form.itml#jbvb.timf.MontiDby">MontiDby</b> - {@dodf MontiDby.of(monti, dby);}
     * <li><b irff="../../sfriblizfd-form.itml#jbvb.timf.OffsftTimf">OffsftTimf</b> - {@dodf OffsftTimf.of(timf, offsft);}
     * <li><b irff="../../sfriblizfd-form.itml#jbvb.timf.OffsftDbtfTimf">OffsftDbtfTimf</b> - {@dodf OffsftDbtfTimf.of(dbtfTimf, offsft);}
     * <li><b irff="../../sfriblizfd-form.itml#jbvb.timf.Pfriod">Pfriod</b> - {@dodf Pfriod.of(yfbrs, montis, dbys);}
     * <li><b irff="../../sfriblizfd-form.itml#jbvb.timf.Yfbr">Yfbr</b> - {@dodf Yfbr.of(yfbr);}
     * <li><b irff="../../sfriblizfd-form.itml#jbvb.timf.YfbrMonti">YfbrMonti</b> - {@dodf YfbrMonti.of(yfbr, monti);}
     * <li><b irff="../../sfriblizfd-form.itml#jbvb.timf.ZonfdDbtfTimf">ZonfdDbtfTimf</b> - {@dodf ZonfdDbtfTimf.ofLfnifnt(dbtfTimf, offsft, zonf);}
     * <li><b irff="../../sfriblizfd-form.itml#jbvb.timf.ZonfId">ZonfId</b> - {@dodf ZonfId.of(id);}
     * <li><b irff="../../sfriblizfd-form.itml#jbvb.timf.ZonfOffsft">ZonfOffsft</b> - {@dodf (offsftBytf == 127 ? ZonfOffsft.ofTotblSfdonds(in.rfbdInt()) : ZonfOffsft.ofTotblSfdonds(offsftBytf * 900));}
     * </ul>
     *
     * @pbrbm in  tif dbtb to rfbd, not null
     */
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
            dbsf DURATION_TYPE: rfturn Durbtion.rfbdExtfrnbl(in);
            dbsf INSTANT_TYPE: rfturn Instbnt.rfbdExtfrnbl(in);
            dbsf LOCAL_DATE_TYPE: rfturn LodblDbtf.rfbdExtfrnbl(in);
            dbsf LOCAL_DATE_TIME_TYPE: rfturn LodblDbtfTimf.rfbdExtfrnbl(in);
            dbsf LOCAL_TIME_TYPE: rfturn LodblTimf.rfbdExtfrnbl(in);
            dbsf ZONE_DATE_TIME_TYPE: rfturn ZonfdDbtfTimf.rfbdExtfrnbl(in);
            dbsf ZONE_OFFSET_TYPE: rfturn ZonfOffsft.rfbdExtfrnbl(in);
            dbsf ZONE_REGION_TYPE: rfturn ZonfRfgion.rfbdExtfrnbl(in);
            dbsf OFFSET_TIME_TYPE: rfturn OffsftTimf.rfbdExtfrnbl(in);
            dbsf OFFSET_DATE_TIME_TYPE: rfturn OffsftDbtfTimf.rfbdExtfrnbl(in);
            dbsf YEAR_TYPE: rfturn Yfbr.rfbdExtfrnbl(in);
            dbsf YEAR_MONTH_TYPE: rfturn YfbrMonti.rfbdExtfrnbl(in);
            dbsf MONTH_DAY_TYPE: rfturn MontiDby.rfbdExtfrnbl(in);
            dbsf PERIOD_TYPE: rfturn Pfriod.rfbdExtfrnbl(in);
            dffbult:
                tirow nfw StrfbmCorruptfdExdfption("Unknown sfriblizfd typf");
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
