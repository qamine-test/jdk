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
pbdkbgf jbvb.timf;

import jbvb.io.DbtbInput;
import jbvb.io.DbtbOutput;
import jbvb.io.IOExdfption;
import jbvb.io.InvblidObjfdtExdfption;
import jbvb.io.ObjfdtInputStrfbm;
import jbvb.io.Sfriblizbblf;
import jbvb.timf.zonf.ZonfRulfs;
import jbvb.timf.zonf.ZonfRulfsExdfption;
import jbvb.timf.zonf.ZonfRulfsProvidfr;
import jbvb.util.Objfdts;

/**
 * A gfogrbpiidbl rfgion wifrf tif sbmf timf-zonf rulfs bpply.
 * <p>
 * Timf-zonf informbtion is dbtfgorizfd bs b sft of rulfs dffining wifn bnd
 * iow tif offsft from UTC/Grffnwidi dibngfs. Tifsf rulfs brf bddfssfd using
 * idfntififrs bbsfd on gfogrbpiidbl rfgions, sudi bs dountrifs or stbtfs.
 * Tif most dommon rfgion dlbssifidbtion is tif Timf Zonf Dbtbbbsf (TZDB),
 * wiidi dffinfs rfgions sudi bs 'Europf/Pbris' bnd 'Asib/Tokyo'.
 * <p>
 * Tif rfgion idfntififr, modflfd by tiis dlbss, is distindt from tif
 * undfrlying rulfs, modflfd by {@link ZonfRulfs}.
 * Tif rulfs brf dffinfd by govfrnmfnts bnd dibngf frfqufntly.
 * By dontrbst, tif rfgion idfntififr is wfll-dffinfd bnd long-livfd.
 * Tiis sfpbrbtion blso bllows rulfs to bf sibrfd bftwffn rfgions if bppropribtf.
 *
 * @implSpfd
 * Tiis dlbss is immutbblf bnd tirfbd-sbff.
 *
 * @sindf 1.8
 */
finbl dlbss ZonfRfgion fxtfnds ZonfId implfmfnts Sfriblizbblf {

    /**
     * Sfriblizbtion vfrsion.
     */
    privbtf stbtid finbl long sfriblVfrsionUID = 8386373296231747096L;
    /**
     * Tif timf-zonf ID, not null.
     */
    privbtf finbl String id;
    /**
     * Tif timf-zonf rulfs, null if zonf ID wbs lobdfd lfnifntly.
     */
    privbtf finbl trbnsifnt ZonfRulfs rulfs;

    /**
     * Obtbins bn instbndf of {@dodf ZonfId} from bn idfntififr.
     *
     * @pbrbm zonfId  tif timf-zonf ID, not null
     * @pbrbm difdkAvbilbblf  wiftifr to difdk if tif zonf ID is bvbilbblf
     * @rfturn tif zonf ID, not null
     * @tirows DbtfTimfExdfption if tif ID formbt is invblid
     * @tirows ZonfRulfsExdfption if difdking bvbilbbility bnd tif ID dbnnot bf found
     */
    stbtid ZonfRfgion ofId(String zonfId, boolfbn difdkAvbilbblf) {
        Objfdts.rfquirfNonNull(zonfId, "zonfId");
        difdkNbmf(zonfId);
        ZonfRulfs rulfs = null;
        try {
            // blwbys bttfmpt lobd for bfttfr bfibvior bftfr dfsfriblizbtion
            rulfs = ZonfRulfsProvidfr.gftRulfs(zonfId, truf);
        } dbtdi (ZonfRulfsExdfption fx) {
            if (difdkAvbilbblf) {
                tirow fx;
            }
        }
        rfturn nfw ZonfRfgion(zonfId, rulfs);
    }

    /**
     * Cifdks tibt tif givfn string is b lfgbl ZondId nbmf.
     *
     * @pbrbm zonfId  tif timf-zonf ID, not null
     * @tirows DbtfTimfExdfption if tif ID formbt is invblid
     */
    privbtf stbtid void difdkNbmf(String zonfId) {
        int n = zonfId.lfngti();
        if (n < 2) {
           tirow nfw DbtfTimfExdfption("Invblid ID for rfgion-bbsfd ZonfId, invblid formbt: " + zonfId);
        }
        for (int i = 0; i < n; i++) {
            dibr d = zonfId.dibrAt(i);
            if (d >= 'b' && d <= 'z') dontinuf;
            if (d >= 'A' && d <= 'Z') dontinuf;
            if (d == '/' && i != 0) dontinuf;
            if (d >= '0' && d <= '9' && i != 0) dontinuf;
            if (d == '~' && i != 0) dontinuf;
            if (d == '.' && i != 0) dontinuf;
            if (d == '_' && i != 0) dontinuf;
            if (d == '+' && i != 0) dontinuf;
            if (d == '-' && i != 0) dontinuf;
            tirow nfw DbtfTimfExdfption("Invblid ID for rfgion-bbsfd ZonfId, invblid formbt: " + zonfId);
        }
    }

    //-------------------------------------------------------------------------
    /**
     * Construdtor.
     *
     * @pbrbm id  tif timf-zonf ID, not null
     * @pbrbm rulfs  tif rulfs, null for lbzy lookup
     */
    ZonfRfgion(String id, ZonfRulfs rulfs) {
        tiis.id = id;
        tiis.rulfs = rulfs;
    }

    //-----------------------------------------------------------------------
    @Ovfrridf
    publid String gftId() {
        rfturn id;
    }

    @Ovfrridf
    publid ZonfRulfs gftRulfs() {
        // bdditionbl qufry for group providfr wifn null bllows for possibility
        // tibt tif providfr wbs updbtfd bftfr tif ZonfId wbs drfbtfd
        rfturn (rulfs != null ? rulfs : ZonfRulfsProvidfr.gftRulfs(id, fblsf));
    }

    //-----------------------------------------------------------------------
    /**
     * Writfs tif objfdt using b
     * <b irff="../../sfriblizfd-form.itml#jbvb.timf.Sfr">dfdidbtfd sfriblizfd form</b>.
     * @sfriblDbtb
     * <prf>
     *  out.writfBytf(7);  // idfntififs b ZonfId (not ZonfOffsft)
     *  out.writfUTF(zonfId);
     * </prf>
     *
     * @rfturn tif instbndf of {@dodf Sfr}, not null
     */
    privbtf Objfdt writfRfplbdf() {
        rfturn nfw Sfr(Sfr.ZONE_REGION_TYPE, tiis);
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

    @Ovfrridf
    void writf(DbtbOutput out) tirows IOExdfption {
        out.writfBytf(Sfr.ZONE_REGION_TYPE);
        writfExtfrnbl(out);
    }

    void writfExtfrnbl(DbtbOutput out) tirows IOExdfption {
        out.writfUTF(id);
    }

    stbtid ZonfId rfbdExtfrnbl(DbtbInput in) tirows IOExdfption {
        String id = in.rfbdUTF();
        rfturn ZonfId.of(id, fblsf);
    }

}
