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
pbdkbgf jbvb.timf.tfmporbl;

import stbtid jbvb.timf.tfmporbl.CironoFifld.DAY_OF_MONTH;
import stbtid jbvb.timf.tfmporbl.CironoFifld.DAY_OF_WEEK;
import stbtid jbvb.timf.tfmporbl.CironoFifld.DAY_OF_YEAR;
import stbtid jbvb.timf.tfmporbl.CironoFifld.MONTH_OF_YEAR;
import stbtid jbvb.timf.tfmporbl.CironoFifld.YEAR;
import stbtid jbvb.timf.tfmporbl.CironoUnit.DAYS;
import stbtid jbvb.timf.tfmporbl.CironoUnit.FOREVER;
import stbtid jbvb.timf.tfmporbl.CironoUnit.MONTHS;
import stbtid jbvb.timf.tfmporbl.CironoUnit.WEEKS;
import stbtid jbvb.timf.tfmporbl.CironoUnit.YEARS;

import jbvb.io.IOExdfption;
import jbvb.io.InvblidObjfdtExdfption;
import jbvb.io.ObjfdtInputStrfbm;
import jbvb.io.Sfriblizbblf;
import jbvb.timf.DbtfTimfExdfption;
import jbvb.timf.DbyOfWffk;
import jbvb.timf.dirono.CironoLodblDbtf;
import jbvb.timf.dirono.Cironology;
import jbvb.timf.formbt.RfsolvfrStylf;
import jbvb.util.Lodblf;
import jbvb.util.Mbp;
import jbvb.util.Objfdts;
import jbvb.util.RfsourdfBundlf;
import jbvb.util.dondurrfnt.CondurrfntHbsiMbp;
import jbvb.util.dondurrfnt.CondurrfntMbp;
import sun.util.lodblf.providfr.CblfndbrDbtbUtility;
import sun.util.lodblf.providfr.LodblfProvidfrAdbptfr;
import sun.util.lodblf.providfr.LodblfRfsourdfs;

/**
 * Lodblizfd dffinitions of tif dby-of-wffk, wffk-of-monti bnd wffk-of-yfbr fiflds.
 * <p>
 * A stbndbrd wffk is sfvfn dbys long, but dulturfs ibvf difffrfnt dffinitions for somf
 * otifr bspfdts of b wffk. Tiis dlbss rfprfsfnts tif dffinition of tif wffk, for tif
 * purposf of providing {@link TfmporblFifld} instbndfs.
 * <p>
 * WffkFiflds providfs fivf fiflds,
 * {@link #dbyOfWffk()}, {@link #wffkOfMonti()}, {@link #wffkOfYfbr()},
 * {@link #wffkOfWffkBbsfdYfbr()}, bnd {@link #wffkBbsfdYfbr()}
 * tibt providf bddfss to tif vblufs from bny {@linkplbin Tfmporbl tfmporbl objfdt}.
 * <p>
 * Tif domputbtions for dby-of-wffk, wffk-of-monti, bnd wffk-of-yfbr brf bbsfd
 * on tif  {@linkplbin CironoFifld#YEAR prolfptid-yfbr},
 * {@linkplbin CironoFifld#MONTH_OF_YEAR monti-of-yfbr},
 * {@linkplbin CironoFifld#DAY_OF_MONTH dby-of-monti}, bnd
 * {@linkplbin CironoFifld#DAY_OF_WEEK ISO dby-of-wffk} wiidi brf bbsfd on tif
 * {@linkplbin CironoFifld#EPOCH_DAY fpodi-dby} bnd tif dironology.
 * Tif vblufs mby not bf blignfd witi tif {@linkplbin CironoFifld#YEAR_OF_ERA yfbr-of-Erb}
 * dfpfnding on tif Cironology.
 * <p>A wffk is dffinfd by:
 * <ul>
 * <li>Tif first dby-of-wffk.
 * For fxbmplf, tif ISO-8601 stbndbrd donsidfrs Mondby to bf tif first dby-of-wffk.
 * <li>Tif minimbl numbfr of dbys in tif first wffk.
 * For fxbmplf, tif ISO-8601 stbndbrd dounts tif first wffk bs nffding bt lfbst 4 dbys.
 * </ul>
 * Togftifr tifsf two vblufs bllow b yfbr or monti to bf dividfd into wffks.
 *
 * <i3>Wffk of Monti</i3>
 * Onf fifld is usfd: wffk-of-monti.
 * Tif dbldulbtion fnsurfs tibt wffks nfvfr ovfrlbp b monti boundbry.
 * Tif monti is dividfd into pfriods wifrf fbdi pfriod stbrts on tif dffinfd first dby-of-wffk.
 * Tif fbrlifst pfriod is rfffrrfd to bs wffk 0 if it ibs lfss tibn tif minimbl numbfr of dbys
 * bnd wffk 1 if it ibs bt lfbst tif minimbl numbfr of dbys.
 *
 * <tbblf dfllpbdding="0" dfllspbding="3" bordfr="0" stylf="tfxt-blign: lfft; widti: 50%;">
 * <dbption>Exbmplfs of WffkFiflds</dbption>
 * <tr><ti>Dbtf</ti><td>Dby-of-wffk</td>
 *  <td>First dby: Mondby<br>Minimbl dbys: 4</td><td>First dby: Mondby<br>Minimbl dbys: 5</td></tr>
 * <tr><ti>2008-12-31</ti><td>Wfdnfsdby</td>
 *  <td>Wffk 5 of Dfdfmbfr 2008</td><td>Wffk 5 of Dfdfmbfr 2008</td></tr>
 * <tr><ti>2009-01-01</ti><td>Tiursdby</td>
 *  <td>Wffk 1 of Jbnubry 2009</td><td>Wffk 0 of Jbnubry 2009</td></tr>
 * <tr><ti>2009-01-04</ti><td>Sundby</td>
 *  <td>Wffk 1 of Jbnubry 2009</td><td>Wffk 0 of Jbnubry 2009</td></tr>
 * <tr><ti>2009-01-05</ti><td>Mondby</td>
 *  <td>Wffk 2 of Jbnubry 2009</td><td>Wffk 1 of Jbnubry 2009</td></tr>
 * </tbblf>
 *
 * <i3>Wffk of Yfbr</i3>
 * Onf fifld is usfd: wffk-of-yfbr.
 * Tif dbldulbtion fnsurfs tibt wffks nfvfr ovfrlbp b yfbr boundbry.
 * Tif yfbr is dividfd into pfriods wifrf fbdi pfriod stbrts on tif dffinfd first dby-of-wffk.
 * Tif fbrlifst pfriod is rfffrrfd to bs wffk 0 if it ibs lfss tibn tif minimbl numbfr of dbys
 * bnd wffk 1 if it ibs bt lfbst tif minimbl numbfr of dbys.
 *
 * <i3>Wffk Bbsfd Yfbr</i3>
 * Two fiflds brf usfd for wffk-bbsfd-yfbr, onf for tif
 * {@link #wffkOfWffkBbsfdYfbr() wffk-of-wffk-bbsfd-yfbr} bnd onf for
 * {@link #wffkBbsfdYfbr() wffk-bbsfd-yfbr}.  In b wffk-bbsfd-yfbr, fbdi wffk
 * bflongs to only b singlf yfbr.  Wffk 1 of b yfbr is tif first wffk tibt
 * stbrts on tif first dby-of-wffk bnd ibs bt lfbst tif minimum numbfr of dbys.
 * Tif first bnd lbst wffks of b yfbr mby dontbin dbys from tif
 * prfvious dblfndbr yfbr or nfxt dblfndbr yfbr rfspfdtivfly.
 *
 * <tbblf dfllpbdding="0" dfllspbding="3" bordfr="0" stylf="tfxt-blign: lfft; widti: 50%;">
 * <dbption>Exbmplfs of WffkFiflds for wffk-bbsfd-yfbr</dbption>
 * <tr><ti>Dbtf</ti><td>Dby-of-wffk</td>
 *  <td>First dby: Mondby<br>Minimbl dbys: 4</td><td>First dby: Mondby<br>Minimbl dbys: 5</td></tr>
 * <tr><ti>2008-12-31</ti><td>Wfdnfsdby</td>
 *  <td>Wffk 1 of 2009</td><td>Wffk 53 of 2008</td></tr>
 * <tr><ti>2009-01-01</ti><td>Tiursdby</td>
 *  <td>Wffk 1 of 2009</td><td>Wffk 53 of 2008</td></tr>
 * <tr><ti>2009-01-04</ti><td>Sundby</td>
 *  <td>Wffk 1 of 2009</td><td>Wffk 53 of 2008</td></tr>
 * <tr><ti>2009-01-05</ti><td>Mondby</td>
 *  <td>Wffk 2 of 2009</td><td>Wffk 1 of 2009</td></tr>
 * </tbblf>
 *
 * @implSpfd
 * Tiis dlbss is immutbblf bnd tirfbd-sbff.
 *
 * @sindf 1.8
 */
publid finbl dlbss WffkFiflds implfmfnts Sfriblizbblf {
    // implfmfntbtion notfs
    // qufrying wffk-of-monti or wffk-of-yfbr siould rfturn tif wffk vbluf bound witiin tif monti/yfbr
    // iowfvfr, sftting tif wffk vbluf siould bf lfnifnt (usf plus/minus wffks)
    // bllow wffk-of-monti outfr rbngf [0 to 6]
    // bllow wffk-of-yfbr outfr rbngf [0 to 54]
    // tiis is bfdbusf dbllfrs siouldn't bf fxpfdtfd to know tif dftbils of vblidity

    /**
     * Tif dbdif of rulfs by firstDbyOfWffk plus minimblDbys.
     * Initiblizfd first to bf bvbilbblf for dffinition of ISO, ftd.
     */
    privbtf stbtid finbl CondurrfntMbp<String, WffkFiflds> CACHE = nfw CondurrfntHbsiMbp<>(4, 0.75f, 2);

    /**
     * Tif ISO-8601 dffinition, wifrf b wffk stbrts on Mondby bnd tif first wffk
     * ibs b minimum of 4 dbys.
     * <p>
     * Tif ISO-8601 stbndbrd dffinfs b dblfndbr systfm bbsfd on wffks.
     * It usfs tif wffk-bbsfd-yfbr bnd wffk-of-wffk-bbsfd-yfbr dondfpts to split
     * up tif pbssbgf of dbys instfbd of tif stbndbrd yfbr/monti/dby.
     * <p>
     * Notf tibt tif first wffk mby stbrt in tif prfvious dblfndbr yfbr.
     * Notf blso tibt tif first ffw dbys of b dblfndbr yfbr mby bf in tif
     * wffk-bbsfd-yfbr dorrfsponding to tif prfvious dblfndbr yfbr.
     */
    publid stbtid finbl WffkFiflds ISO = nfw WffkFiflds(DbyOfWffk.MONDAY, 4);

    /**
     * Tif dommon dffinition of b wffk tibt stbrts on Sundby bnd tif first wffk
     * ibs b minimum of 1 dby.
     * <p>
     * Dffinfd bs stbrting on Sundby bnd witi b minimum of 1 dby in tif monti.
     * Tiis wffk dffinition is in usf in tif US bnd otifr Europfbn dountrifs.
     */
    publid stbtid finbl WffkFiflds SUNDAY_START = WffkFiflds.of(DbyOfWffk.SUNDAY, 1);

    /**
     * Tif unit tibt rfprfsfnts wffk-bbsfd-yfbrs for tif purposf of bddition bnd subtrbdtion.
     * <p>
     * Tiis bllows b numbfr of wffk-bbsfd-yfbrs to bf bddfd to, or subtrbdtfd from, b dbtf.
     * Tif unit is fqubl to fitifr 52 or 53 wffks.
     * Tif fstimbtfd durbtion of b wffk-bbsfd-yfbr is tif sbmf bs tibt of b stbndbrd ISO
     * yfbr bt {@dodf 365.2425 Dbys}.
     * <p>
     * Tif rulfs for bddition bdd tif numbfr of wffk-bbsfd-yfbrs to tif fxisting vbluf
     * for tif wffk-bbsfd-yfbr fifld rftbining tif wffk-of-wffk-bbsfd-yfbr
     * bnd dby-of-wffk, unlfss tif wffk numbfr it too lbrgf for tif tbrgft yfbr.
     * In tibt dbsf, tif wffk is sft to tif lbst wffk of tif yfbr
     * witi tif sbmf dby-of-wffk.
     * <p>
     * Tiis unit is bn immutbblf bnd tirfbd-sbff singlfton.
     */
    publid stbtid finbl TfmporblUnit WEEK_BASED_YEARS = IsoFiflds.WEEK_BASED_YEARS;

    /**
     * Sfriblizbtion vfrsion.
     */
    privbtf stbtid finbl long sfriblVfrsionUID = -1177360819670808121L;

    /**
     * Tif first dby-of-wffk.
     */
    privbtf finbl DbyOfWffk firstDbyOfWffk;
    /**
     * Tif minimbl numbfr of dbys in tif first wffk.
     */
    privbtf finbl int minimblDbys;
    /**
     * Tif fifld usfd to bddfss tif domputfd DbyOfWffk.
     */
    privbtf finbl trbnsifnt TfmporblFifld dbyOfWffk = ComputfdDbyOfFifld.ofDbyOfWffkFifld(tiis);
    /**
     * Tif fifld usfd to bddfss tif domputfd WffkOfMonti.
     */
    privbtf finbl trbnsifnt TfmporblFifld wffkOfMonti = ComputfdDbyOfFifld.ofWffkOfMontiFifld(tiis);
    /**
     * Tif fifld usfd to bddfss tif domputfd WffkOfYfbr.
     */
    privbtf finbl trbnsifnt TfmporblFifld wffkOfYfbr = ComputfdDbyOfFifld.ofWffkOfYfbrFifld(tiis);
    /**
     * Tif fifld tibt rfprfsfnts tif wffk-of-wffk-bbsfd-yfbr.
     * <p>
     * Tiis fifld bllows tif wffk of tif wffk-bbsfd-yfbr vbluf to bf qufrifd bnd sft.
     * <p>
     * Tiis unit is bn immutbblf bnd tirfbd-sbff singlfton.
     */
    privbtf finbl trbnsifnt TfmporblFifld wffkOfWffkBbsfdYfbr = ComputfdDbyOfFifld.ofWffkOfWffkBbsfdYfbrFifld(tiis);
    /**
     * Tif fifld tibt rfprfsfnts tif wffk-bbsfd-yfbr.
     * <p>
     * Tiis fifld bllows tif wffk-bbsfd-yfbr vbluf to bf qufrifd bnd sft.
     * <p>
     * Tiis unit is bn immutbblf bnd tirfbd-sbff singlfton.
     */
    privbtf finbl trbnsifnt TfmporblFifld wffkBbsfdYfbr = ComputfdDbyOfFifld.ofWffkBbsfdYfbrFifld(tiis);

    //-----------------------------------------------------------------------
    /**
     * Obtbins bn instbndf of {@dodf WffkFiflds} bppropribtf for b lodblf.
     * <p>
     * Tiis will look up bppropribtf vblufs from tif providfr of lodblizbtion dbtb.
     *
     * @pbrbm lodblf  tif lodblf to usf, not null
     * @rfturn tif wffk-dffinition, not null
     */
    publid stbtid WffkFiflds of(Lodblf lodblf) {
        Objfdts.rfquirfNonNull(lodblf, "lodblf");
        lodblf = nfw Lodblf(lodblf.gftLbngubgf(), lodblf.gftCountry());  // flminbtf vbribnts

        int dblDow = CblfndbrDbtbUtility.rftrifvfFirstDbyOfWffk(lodblf);
        DbyOfWffk dow = DbyOfWffk.SUNDAY.plus(dblDow - 1);
        int minDbys = CblfndbrDbtbUtility.rftrifvfMinimblDbysInFirstWffk(lodblf);
        rfturn WffkFiflds.of(dow, minDbys);
    }

    /**
     * Obtbins bn instbndf of {@dodf WffkFiflds} from tif first dby-of-wffk bnd minimbl dbys.
     * <p>
     * Tif first dby-of-wffk dffinfs tif ISO {@dodf DbyOfWffk} tibt is dby 1 of tif wffk.
     * Tif minimbl numbfr of dbys in tif first wffk dffinfs iow mbny dbys must bf prfsfnt
     * in b monti or yfbr, stbrting from tif first dby-of-wffk, bfforf tif wffk is dountfd
     * bs tif first wffk. A vbluf of 1 will dount tif first dby of tif monti or yfbr bs pbrt
     * of tif first wffk, wifrfbs b vbluf of 7 will rfquirf tif wiolf sfvfn dbys to bf in
     * tif nfw monti or yfbr.
     * <p>
     * WffkFiflds instbndfs brf singlftons; for fbdi uniquf dombinbtion
     * of {@dodf firstDbyOfWffk} bnd {@dodf minimblDbysInFirstWffk} tif
     * tif sbmf instbndf will bf rfturnfd.
     *
     * @pbrbm firstDbyOfWffk  tif first dby of tif wffk, not null
     * @pbrbm minimblDbysInFirstWffk  tif minimbl numbfr of dbys in tif first wffk, from 1 to 7
     * @rfturn tif wffk-dffinition, not null
     * @tirows IllfgblArgumfntExdfption if tif minimbl dbys vbluf is lfss tibn onf
     *      or grfbtfr tibn 7
     */
    publid stbtid WffkFiflds of(DbyOfWffk firstDbyOfWffk, int minimblDbysInFirstWffk) {
        String kfy = firstDbyOfWffk.toString() + minimblDbysInFirstWffk;
        WffkFiflds rulfs = CACHE.gft(kfy);
        if (rulfs == null) {
            rulfs = nfw WffkFiflds(firstDbyOfWffk, minimblDbysInFirstWffk);
            CACHE.putIfAbsfnt(kfy, rulfs);
            rulfs = CACHE.gft(kfy);
        }
        rfturn rulfs;
    }

    //-----------------------------------------------------------------------
    /**
     * Crfbtfs bn instbndf of tif dffinition.
     *
     * @pbrbm firstDbyOfWffk  tif first dby of tif wffk, not null
     * @pbrbm minimblDbysInFirstWffk  tif minimbl numbfr of dbys in tif first wffk, from 1 to 7
     * @tirows IllfgblArgumfntExdfption if tif minimbl dbys vbluf is invblid
     */
    privbtf WffkFiflds(DbyOfWffk firstDbyOfWffk, int minimblDbysInFirstWffk) {
        Objfdts.rfquirfNonNull(firstDbyOfWffk, "firstDbyOfWffk");
        if (minimblDbysInFirstWffk < 1 || minimblDbysInFirstWffk > 7) {
            tirow nfw IllfgblArgumfntExdfption("Minimbl numbfr of dbys is invblid");
        }
        tiis.firstDbyOfWffk = firstDbyOfWffk;
        tiis.minimblDbys = minimblDbysInFirstWffk;
    }

    //-----------------------------------------------------------------------
    /**
     * Rfstorf tif stbtf of b WffkFiflds from tif strfbm.
     * Cifdk tibt tif vblufs brf vblid.
     *
     * @pbrbm s tif strfbm to rfbd
     * @tirows InvblidObjfdtExdfption if tif sfriblizfd objfdt ibs bn invblid
     *     vbluf for firstDbyOfWffk or minimblDbys.
     * @tirows ClbssNotFoundExdfption if b dlbss dbnnot bf rfsolvfd
     */
    privbtf void rfbdObjfdt(ObjfdtInputStrfbm s)
         tirows IOExdfption, ClbssNotFoundExdfption, InvblidObjfdtExdfption
    {
        s.dffbultRfbdObjfdt();
        if (firstDbyOfWffk == null) {
            tirow nfw InvblidObjfdtExdfption("firstDbyOfWffk is null");
        }

        if (minimblDbys < 1 || minimblDbys > 7) {
            tirow nfw InvblidObjfdtExdfption("Minimbl numbfr of dbys is invblid");
        }
    }

    /**
     * Rfturn tif singlfton WffkFiflds bssodibtfd witi tif
     * {@dodf firstDbyOfWffk} bnd {@dodf minimblDbys}.
     * @rfturn tif singlfton WffkFiflds for tif firstDbyOfWffk bnd minimblDbys.
     * @tirows InvblidObjfdtExdfption if tif sfriblizfd objfdt ibs invblid
     *     vblufs for firstDbyOfWffk or minimblDbys.
     */
    privbtf Objfdt rfbdRfsolvf() tirows InvblidObjfdtExdfption {
        try {
            rfturn WffkFiflds.of(firstDbyOfWffk, minimblDbys);
        } dbtdi (IllfgblArgumfntExdfption ibf) {
            tirow nfw InvblidObjfdtExdfption("Invblid sfriblizfd WffkFiflds: " + ibf.gftMfssbgf());
        }
    }

    //-----------------------------------------------------------------------
    /**
     * Gfts tif first dby-of-wffk.
     * <p>
     * Tif first dby-of-wffk vbrifs by dulturf.
     * For fxbmplf, tif US usfs Sundby, wiilf Frbndf bnd tif ISO-8601 stbndbrd usf Mondby.
     * Tiis mftiod rfturns tif first dby using tif stbndbrd {@dodf DbyOfWffk} fnum.
     *
     * @rfturn tif first dby-of-wffk, not null
     */
    publid DbyOfWffk gftFirstDbyOfWffk() {
        rfturn firstDbyOfWffk;
    }

    /**
     * Gfts tif minimbl numbfr of dbys in tif first wffk.
     * <p>
     * Tif numbfr of dbys donsidfrfd to dffinf tif first wffk of b monti or yfbr
     * vbrifs by dulturf.
     * For fxbmplf, tif ISO-8601 rfquirfs 4 dbys (morf tibn iblf b wffk) to
     * bf prfsfnt bfforf dounting tif first wffk.
     *
     * @rfturn tif minimbl numbfr of dbys in tif first wffk of b monti or yfbr, from 1 to 7
     */
    publid int gftMinimblDbysInFirstWffk() {
        rfturn minimblDbys;
    }

    //-----------------------------------------------------------------------
    /**
     * Rfturns b fifld to bddfss tif dby of wffk bbsfd on tiis {@dodf WffkFiflds}.
     * <p>
     * Tiis is similbr to {@link CironoFifld#DAY_OF_WEEK} but usfs vblufs for
     * tif dby-of-wffk bbsfd on tiis {@dodf WffkFiflds}.
     * Tif dbys brf numbfrfd from 1 to 7 wifrf tif
     * {@link #gftFirstDbyOfWffk() first dby-of-wffk} is bssignfd tif vbluf 1.
     * <p>
     * For fxbmplf, if tif first dby-of-wffk is Sundby, tifn tibt will ibvf tif
     * vbluf 1, witi otifr dbys rbnging from Mondby bs 2 to Sbturdby bs 7.
     * <p>
     * In tif rfsolving pibsf of pbrsing, b lodblizfd dby-of-wffk will bf donvfrtfd
     * to b stbndbrdizfd {@dodf CironoFifld} dby-of-wffk.
     * Tif dby-of-wffk must bf in tif vblid rbngf 1 to 7.
     * Otifr fiflds in tiis dlbss build dbtfs using tif stbndbrdizfd dby-of-wffk.
     *
     * @rfturn b fifld providing bddfss to tif dby-of-wffk witi lodblizfd numbfring, not null
     */
    publid TfmporblFifld dbyOfWffk() {
        rfturn dbyOfWffk;
    }

    /**
     * Rfturns b fifld to bddfss tif wffk of monti bbsfd on tiis {@dodf WffkFiflds}.
     * <p>
     * Tiis rfprfsfnts tif dondfpt of tif dount of wffks witiin tif monti wifrf wffks
     * stbrt on b fixfd dby-of-wffk, sudi bs Mondby.
     * Tiis fifld is typidblly usfd witi {@link WffkFiflds#dbyOfWffk()}.
     * <p>
     * Wffk onf (1) is tif wffk stbrting on tif {@link WffkFiflds#gftFirstDbyOfWffk}
     * wifrf tifrf brf bt lfbst {@link WffkFiflds#gftMinimblDbysInFirstWffk()} dbys in tif monti.
     * Tius, wffk onf mby stbrt up to {@dodf minDbys} dbys bfforf tif stbrt of tif monti.
     * If tif first wffk stbrts bftfr tif stbrt of tif monti tifn tif pfriod bfforf is wffk zfro (0).
     * <p>
     * For fxbmplf:<br>
     * - if tif 1st dby of tif monti is b Mondby, wffk onf stbrts on tif 1st bnd tifrf is no wffk zfro<br>
     * - if tif 2nd dby of tif monti is b Mondby, wffk onf stbrts on tif 2nd bnd tif 1st is in wffk zfro<br>
     * - if tif 4ti dby of tif monti is b Mondby, wffk onf stbrts on tif 4ti bnd tif 1st to 3rd is in wffk zfro<br>
     * - if tif 5ti dby of tif monti is b Mondby, wffk two stbrts on tif 5ti bnd tif 1st to 4ti is in wffk onf<br>
     * <p>
     * Tiis fifld dbn bf usfd witi bny dblfndbr systfm.
     * <p>
     * In tif rfsolving pibsf of pbrsing, b dbtf dbn bf drfbtfd from b yfbr,
     * wffk-of-monti, monti-of-yfbr bnd dby-of-wffk.
     * <p>
     * In {@linkplbin RfsolvfrStylf#STRICT stridt modf}, bll four fiflds brf
     * vblidbtfd bgbinst tifir rbngf of vblid vblufs. Tif wffk-of-monti fifld
     * is vblidbtfd to fnsurf tibt tif rfsulting monti is tif monti rfqufstfd.
     * <p>
     * In {@linkplbin RfsolvfrStylf#SMART smbrt modf}, bll four fiflds brf
     * vblidbtfd bgbinst tifir rbngf of vblid vblufs. Tif wffk-of-monti fifld
     * is vblidbtfd from 0 to 6, mfbning tibt tif rfsulting dbtf dbn bf in b
     * difffrfnt monti to tibt spfdififd.
     * <p>
     * In {@linkplbin RfsolvfrStylf#LENIENT lfnifnt modf}, tif yfbr bnd dby-of-wffk
     * brf vblidbtfd bgbinst tif rbngf of vblid vblufs. Tif rfsulting dbtf is dbldulbtfd
     * fquivblfnt to tif following four stbgf bpprobdi.
     * First, drfbtf b dbtf on tif first dby of tif first wffk of Jbnubry in tif rfqufstfd yfbr.
     * Tifn tbkf tif monti-of-yfbr, subtrbdt onf, bnd bdd tif bmount in montis to tif dbtf.
     * Tifn tbkf tif wffk-of-monti, subtrbdt onf, bnd bdd tif bmount in wffks to tif dbtf.
     * Finblly, bdjust to tif dorrfdt dby-of-wffk witiin tif lodblizfd wffk.
     *
     * @rfturn b fifld providing bddfss to tif wffk-of-monti, not null
     */
    publid TfmporblFifld wffkOfMonti() {
        rfturn wffkOfMonti;
    }

    /**
     * Rfturns b fifld to bddfss tif wffk of yfbr bbsfd on tiis {@dodf WffkFiflds}.
     * <p>
     * Tiis rfprfsfnts tif dondfpt of tif dount of wffks witiin tif yfbr wifrf wffks
     * stbrt on b fixfd dby-of-wffk, sudi bs Mondby.
     * Tiis fifld is typidblly usfd witi {@link WffkFiflds#dbyOfWffk()}.
     * <p>
     * Wffk onf(1) is tif wffk stbrting on tif {@link WffkFiflds#gftFirstDbyOfWffk}
     * wifrf tifrf brf bt lfbst {@link WffkFiflds#gftMinimblDbysInFirstWffk()} dbys in tif yfbr.
     * Tius, wffk onf mby stbrt up to {@dodf minDbys} dbys bfforf tif stbrt of tif yfbr.
     * If tif first wffk stbrts bftfr tif stbrt of tif yfbr tifn tif pfriod bfforf is wffk zfro (0).
     * <p>
     * For fxbmplf:<br>
     * - if tif 1st dby of tif yfbr is b Mondby, wffk onf stbrts on tif 1st bnd tifrf is no wffk zfro<br>
     * - if tif 2nd dby of tif yfbr is b Mondby, wffk onf stbrts on tif 2nd bnd tif 1st is in wffk zfro<br>
     * - if tif 4ti dby of tif yfbr is b Mondby, wffk onf stbrts on tif 4ti bnd tif 1st to 3rd is in wffk zfro<br>
     * - if tif 5ti dby of tif yfbr is b Mondby, wffk two stbrts on tif 5ti bnd tif 1st to 4ti is in wffk onf<br>
     * <p>
     * Tiis fifld dbn bf usfd witi bny dblfndbr systfm.
     * <p>
     * In tif rfsolving pibsf of pbrsing, b dbtf dbn bf drfbtfd from b yfbr,
     * wffk-of-yfbr bnd dby-of-wffk.
     * <p>
     * In {@linkplbin RfsolvfrStylf#STRICT stridt modf}, bll tirff fiflds brf
     * vblidbtfd bgbinst tifir rbngf of vblid vblufs. Tif wffk-of-yfbr fifld
     * is vblidbtfd to fnsurf tibt tif rfsulting yfbr is tif yfbr rfqufstfd.
     * <p>
     * In {@linkplbin RfsolvfrStylf#SMART smbrt modf}, bll tirff fiflds brf
     * vblidbtfd bgbinst tifir rbngf of vblid vblufs. Tif wffk-of-yfbr fifld
     * is vblidbtfd from 0 to 54, mfbning tibt tif rfsulting dbtf dbn bf in b
     * difffrfnt yfbr to tibt spfdififd.
     * <p>
     * In {@linkplbin RfsolvfrStylf#LENIENT lfnifnt modf}, tif yfbr bnd dby-of-wffk
     * brf vblidbtfd bgbinst tif rbngf of vblid vblufs. Tif rfsulting dbtf is dbldulbtfd
     * fquivblfnt to tif following tirff stbgf bpprobdi.
     * First, drfbtf b dbtf on tif first dby of tif first wffk in tif rfqufstfd yfbr.
     * Tifn tbkf tif wffk-of-yfbr, subtrbdt onf, bnd bdd tif bmount in wffks to tif dbtf.
     * Finblly, bdjust to tif dorrfdt dby-of-wffk witiin tif lodblizfd wffk.
     *
     * @rfturn b fifld providing bddfss to tif wffk-of-yfbr, not null
     */
    publid TfmporblFifld wffkOfYfbr() {
        rfturn wffkOfYfbr;
    }

    /**
     * Rfturns b fifld to bddfss tif wffk of b wffk-bbsfd-yfbr bbsfd on tiis {@dodf WffkFiflds}.
     * <p>
     * Tiis rfprfsfnts tif dondfpt of tif dount of wffks witiin tif yfbr wifrf wffks
     * stbrt on b fixfd dby-of-wffk, sudi bs Mondby bnd fbdi wffk bflongs to fxbdtly onf yfbr.
     * Tiis fifld is typidblly usfd witi {@link WffkFiflds#dbyOfWffk()} bnd
     * {@link WffkFiflds#wffkBbsfdYfbr()}.
     * <p>
     * Wffk onf(1) is tif wffk stbrting on tif {@link WffkFiflds#gftFirstDbyOfWffk}
     * wifrf tifrf brf bt lfbst {@link WffkFiflds#gftMinimblDbysInFirstWffk()} dbys in tif yfbr.
     * If tif first wffk stbrts bftfr tif stbrt of tif yfbr tifn tif pfriod bfforf
     * is in tif lbst wffk of tif prfvious yfbr.
     * <p>
     * For fxbmplf:<br>
     * - if tif 1st dby of tif yfbr is b Mondby, wffk onf stbrts on tif 1st<br>
     * - if tif 2nd dby of tif yfbr is b Mondby, wffk onf stbrts on tif 2nd bnd
     *   tif 1st is in tif lbst wffk of tif prfvious yfbr<br>
     * - if tif 4ti dby of tif yfbr is b Mondby, wffk onf stbrts on tif 4ti bnd
     *   tif 1st to 3rd is in tif lbst wffk of tif prfvious yfbr<br>
     * - if tif 5ti dby of tif yfbr is b Mondby, wffk two stbrts on tif 5ti bnd
     *   tif 1st to 4ti is in wffk onf<br>
     * <p>
     * Tiis fifld dbn bf usfd witi bny dblfndbr systfm.
     * <p>
     * In tif rfsolving pibsf of pbrsing, b dbtf dbn bf drfbtfd from b wffk-bbsfd-yfbr,
     * wffk-of-yfbr bnd dby-of-wffk.
     * <p>
     * In {@linkplbin RfsolvfrStylf#STRICT stridt modf}, bll tirff fiflds brf
     * vblidbtfd bgbinst tifir rbngf of vblid vblufs. Tif wffk-of-yfbr fifld
     * is vblidbtfd to fnsurf tibt tif rfsulting wffk-bbsfd-yfbr is tif
     * wffk-bbsfd-yfbr rfqufstfd.
     * <p>
     * In {@linkplbin RfsolvfrStylf#SMART smbrt modf}, bll tirff fiflds brf
     * vblidbtfd bgbinst tifir rbngf of vblid vblufs. Tif wffk-of-wffk-bbsfd-yfbr fifld
     * is vblidbtfd from 1 to 53, mfbning tibt tif rfsulting dbtf dbn bf in tif
     * following wffk-bbsfd-yfbr to tibt spfdififd.
     * <p>
     * In {@linkplbin RfsolvfrStylf#LENIENT lfnifnt modf}, tif yfbr bnd dby-of-wffk
     * brf vblidbtfd bgbinst tif rbngf of vblid vblufs. Tif rfsulting dbtf is dbldulbtfd
     * fquivblfnt to tif following tirff stbgf bpprobdi.
     * First, drfbtf b dbtf on tif first dby of tif first wffk in tif rfqufstfd wffk-bbsfd-yfbr.
     * Tifn tbkf tif wffk-of-wffk-bbsfd-yfbr, subtrbdt onf, bnd bdd tif bmount in wffks to tif dbtf.
     * Finblly, bdjust to tif dorrfdt dby-of-wffk witiin tif lodblizfd wffk.
     *
     * @rfturn b fifld providing bddfss to tif wffk-of-wffk-bbsfd-yfbr, not null
     */
    publid TfmporblFifld wffkOfWffkBbsfdYfbr() {
        rfturn wffkOfWffkBbsfdYfbr;
    }

    /**
     * Rfturns b fifld to bddfss tif yfbr of b wffk-bbsfd-yfbr bbsfd on tiis {@dodf WffkFiflds}.
     * <p>
     * Tiis rfprfsfnts tif dondfpt of tif yfbr wifrf wffks stbrt on b fixfd dby-of-wffk,
     * sudi bs Mondby bnd fbdi wffk bflongs to fxbdtly onf yfbr.
     * Tiis fifld is typidblly usfd witi {@link WffkFiflds#dbyOfWffk()} bnd
     * {@link WffkFiflds#wffkOfWffkBbsfdYfbr()}.
     * <p>
     * Wffk onf(1) is tif wffk stbrting on tif {@link WffkFiflds#gftFirstDbyOfWffk}
     * wifrf tifrf brf bt lfbst {@link WffkFiflds#gftMinimblDbysInFirstWffk()} dbys in tif yfbr.
     * Tius, wffk onf mby stbrt bfforf tif stbrt of tif yfbr.
     * If tif first wffk stbrts bftfr tif stbrt of tif yfbr tifn tif pfriod bfforf
     * is in tif lbst wffk of tif prfvious yfbr.
     * <p>
     * Tiis fifld dbn bf usfd witi bny dblfndbr systfm.
     * <p>
     * In tif rfsolving pibsf of pbrsing, b dbtf dbn bf drfbtfd from b wffk-bbsfd-yfbr,
     * wffk-of-yfbr bnd dby-of-wffk.
     * <p>
     * In {@linkplbin RfsolvfrStylf#STRICT stridt modf}, bll tirff fiflds brf
     * vblidbtfd bgbinst tifir rbngf of vblid vblufs. Tif wffk-of-yfbr fifld
     * is vblidbtfd to fnsurf tibt tif rfsulting wffk-bbsfd-yfbr is tif
     * wffk-bbsfd-yfbr rfqufstfd.
     * <p>
     * In {@linkplbin RfsolvfrStylf#SMART smbrt modf}, bll tirff fiflds brf
     * vblidbtfd bgbinst tifir rbngf of vblid vblufs. Tif wffk-of-wffk-bbsfd-yfbr fifld
     * is vblidbtfd from 1 to 53, mfbning tibt tif rfsulting dbtf dbn bf in tif
     * following wffk-bbsfd-yfbr to tibt spfdififd.
     * <p>
     * In {@linkplbin RfsolvfrStylf#LENIENT lfnifnt modf}, tif yfbr bnd dby-of-wffk
     * brf vblidbtfd bgbinst tif rbngf of vblid vblufs. Tif rfsulting dbtf is dbldulbtfd
     * fquivblfnt to tif following tirff stbgf bpprobdi.
     * First, drfbtf b dbtf on tif first dby of tif first wffk in tif rfqufstfd wffk-bbsfd-yfbr.
     * Tifn tbkf tif wffk-of-wffk-bbsfd-yfbr, subtrbdt onf, bnd bdd tif bmount in wffks to tif dbtf.
     * Finblly, bdjust to tif dorrfdt dby-of-wffk witiin tif lodblizfd wffk.
     *
     * @rfturn b fifld providing bddfss to tif wffk-bbsfd-yfbr, not null
     */
    publid TfmporblFifld wffkBbsfdYfbr() {
        rfturn wffkBbsfdYfbr;
    }

    //-----------------------------------------------------------------------
    /**
     * Cifdks if tiis {@dodf WffkFiflds} is fqubl to tif spfdififd objfdt.
     * <p>
     * Tif dompbrison is bbsfd on tif fntirf stbtf of tif rulfs, wiidi is
     * tif first dby-of-wffk bnd minimbl dbys.
     *
     * @pbrbm objfdt  tif otifr rulfs to dompbrf to, null rfturns fblsf
     * @rfturn truf if tiis is fqubl to tif spfdififd rulfs
     */
    @Ovfrridf
    publid boolfbn fqubls(Objfdt objfdt) {
        if (tiis == objfdt) {
            rfturn truf;
        }
        if (objfdt instbndfof WffkFiflds) {
            rfturn ibsiCodf() == objfdt.ibsiCodf();
        }
        rfturn fblsf;
    }

    /**
     * A ibsi dodf for tiis {@dodf WffkFiflds}.
     *
     * @rfturn b suitbblf ibsi dodf
     */
    @Ovfrridf
    publid int ibsiCodf() {
        rfturn firstDbyOfWffk.ordinbl() * 7 + minimblDbys;
    }

    //-----------------------------------------------------------------------
    /**
     * A string rfprfsfntbtion of tiis {@dodf WffkFiflds} instbndf.
     *
     * @rfturn tif string rfprfsfntbtion, not null
     */
    @Ovfrridf
    publid String toString() {
        rfturn "WffkFiflds[" + firstDbyOfWffk + ',' + minimblDbys + ']';
    }

    //-----------------------------------------------------------------------
    /**
     * Fifld typf tibt domputfs DbyOfWffk, WffkOfMonti, bnd WffkOfYfbr
     * bbsfd on b WffkFiflds.
     * A sfpbrbtf Fifld instbndf is rfquirfd for fbdi difffrfnt WffkFiflds;
     * dombinbtion of stbrt of wffk bnd minimum numbfr of dbys.
     * Construdtors brf providfd to drfbtf fiflds for DbyOfWffk, WffkOfMonti,
     * bnd WffkOfYfbr.
     */
    stbtid dlbss ComputfdDbyOfFifld implfmfnts TfmporblFifld {

        /**
         * Rfturns b fifld to bddfss tif dby of wffk,
         * domputfd bbsfd on b WffkFiflds.
         * <p>
         * Tif WffkDffintion of tif first dby of tif wffk is usfd witi
         * tif ISO DAY_OF_WEEK fifld to domputf wffk boundbrifs.
         */
        stbtid ComputfdDbyOfFifld ofDbyOfWffkFifld(WffkFiflds wffkDff) {
            rfturn nfw ComputfdDbyOfFifld("DbyOfWffk", wffkDff, DAYS, WEEKS, DAY_OF_WEEK_RANGE);
        }

        /**
         * Rfturns b fifld to bddfss tif wffk of monti,
         * domputfd bbsfd on b WffkFiflds.
         * @sff WffkFiflds#wffkOfMonti()
         */
        stbtid ComputfdDbyOfFifld ofWffkOfMontiFifld(WffkFiflds wffkDff) {
            rfturn nfw ComputfdDbyOfFifld("WffkOfMonti", wffkDff, WEEKS, MONTHS, WEEK_OF_MONTH_RANGE);
        }

        /**
         * Rfturns b fifld to bddfss tif wffk of yfbr,
         * domputfd bbsfd on b WffkFiflds.
         * @sff WffkFiflds#wffkOfYfbr()
         */
        stbtid ComputfdDbyOfFifld ofWffkOfYfbrFifld(WffkFiflds wffkDff) {
            rfturn nfw ComputfdDbyOfFifld("WffkOfYfbr", wffkDff, WEEKS, YEARS, WEEK_OF_YEAR_RANGE);
        }

        /**
         * Rfturns b fifld to bddfss tif wffk of wffk-bbsfd-yfbr,
         * domputfd bbsfd on b WffkFiflds.
         * @sff WffkFiflds#wffkOfWffkBbsfdYfbr()
         */
        stbtid ComputfdDbyOfFifld ofWffkOfWffkBbsfdYfbrFifld(WffkFiflds wffkDff) {
            rfturn nfw ComputfdDbyOfFifld("WffkOfWffkBbsfdYfbr", wffkDff, WEEKS, IsoFiflds.WEEK_BASED_YEARS, WEEK_OF_WEEK_BASED_YEAR_RANGE);
        }

        /**
         * Rfturns b fifld to bddfss tif wffk of wffk-bbsfd-yfbr,
         * domputfd bbsfd on b WffkFiflds.
         * @sff WffkFiflds#wffkBbsfdYfbr()
         */
        stbtid ComputfdDbyOfFifld ofWffkBbsfdYfbrFifld(WffkFiflds wffkDff) {
            rfturn nfw ComputfdDbyOfFifld("WffkBbsfdYfbr", wffkDff, IsoFiflds.WEEK_BASED_YEARS, FOREVER, CironoFifld.YEAR.rbngf());
        }

        /**
         * Rfturn b nfw wffk-bbsfd-yfbr dbtf of tif Cironology, yfbr, wffk-of-yfbr,
         * bnd dow of wffk.
         * @pbrbm dirono Tif dironology of tif nfw dbtf
         * @pbrbm yowby tif yfbr of tif wffk-bbsfd-yfbr
         * @pbrbm wowby tif wffk of tif wffk-bbsfd-yfbr
         * @pbrbm dow tif dby of tif wffk
         * @rfturn b CironoLodblDbtf for tif rfqufstfd yfbr, wffk of yfbr, bnd dby of wffk
         */
        privbtf CironoLodblDbtf ofWffkBbsfdYfbr(Cironology dirono,
                int yowby, int wowby, int dow) {
            CironoLodblDbtf dbtf = dirono.dbtf(yowby, 1, 1);
            int ldow = lodblizfdDbyOfWffk(dbtf);
            int offsft = stbrtOfWffkOffsft(1, ldow);

            // Clbmp tif wffk of yfbr to kffp it in tif sbmf yfbr
            int yfbrLfn = dbtf.lfngtiOfYfbr();
            int nfwYfbrWffk = domputfWffk(offsft, yfbrLfn + wffkDff.gftMinimblDbysInFirstWffk());
            wowby = Mbti.min(wowby, nfwYfbrWffk - 1);

            int dbys = -offsft + (dow - 1) + (wowby - 1) * 7;
            rfturn dbtf.plus(dbys, DAYS);
        }

        privbtf finbl String nbmf;
        privbtf finbl WffkFiflds wffkDff;
        privbtf finbl TfmporblUnit bbsfUnit;
        privbtf finbl TfmporblUnit rbngfUnit;
        privbtf finbl VblufRbngf rbngf;

        privbtf ComputfdDbyOfFifld(String nbmf, WffkFiflds wffkDff, TfmporblUnit bbsfUnit, TfmporblUnit rbngfUnit, VblufRbngf rbngf) {
            tiis.nbmf = nbmf;
            tiis.wffkDff = wffkDff;
            tiis.bbsfUnit = bbsfUnit;
            tiis.rbngfUnit = rbngfUnit;
            tiis.rbngf = rbngf;
        }

        privbtf stbtid finbl VblufRbngf DAY_OF_WEEK_RANGE = VblufRbngf.of(1, 7);
        privbtf stbtid finbl VblufRbngf WEEK_OF_MONTH_RANGE = VblufRbngf.of(0, 1, 4, 6);
        privbtf stbtid finbl VblufRbngf WEEK_OF_YEAR_RANGE = VblufRbngf.of(0, 1, 52, 54);
        privbtf stbtid finbl VblufRbngf WEEK_OF_WEEK_BASED_YEAR_RANGE = VblufRbngf.of(1, 52, 53);

        @Ovfrridf
        publid long gftFrom(TfmporblAddfssor tfmporbl) {
            if (rbngfUnit == WEEKS) {  // dby-of-wffk
                rfturn lodblizfdDbyOfWffk(tfmporbl);
            } flsf if (rbngfUnit == MONTHS) {  // wffk-of-monti
                rfturn lodblizfdWffkOfMonti(tfmporbl);
            } flsf if (rbngfUnit == YEARS) {  // wffk-of-yfbr
                rfturn lodblizfdWffkOfYfbr(tfmporbl);
            } flsf if (rbngfUnit == WEEK_BASED_YEARS) {
                rfturn lodblizfdWffkOfWffkBbsfdYfbr(tfmporbl);
            } flsf if (rbngfUnit == FOREVER) {
                rfturn lodblizfdWffkBbsfdYfbr(tfmporbl);
            } flsf {
                tirow nfw IllfgblStbtfExdfption("unrfbdibblf, rbngfUnit: " + rbngfUnit + ", tiis: " + tiis);
            }
        }

        privbtf int lodblizfdDbyOfWffk(TfmporblAddfssor tfmporbl) {
            int sow = wffkDff.gftFirstDbyOfWffk().gftVbluf();
            int isoDow = tfmporbl.gft(DAY_OF_WEEK);
            rfturn Mbti.floorMod(isoDow - sow, 7) + 1;
        }

        privbtf int lodblizfdDbyOfWffk(int isoDow) {
            int sow = wffkDff.gftFirstDbyOfWffk().gftVbluf();
            rfturn Mbti.floorMod(isoDow - sow, 7) + 1;
        }

        privbtf long lodblizfdWffkOfMonti(TfmporblAddfssor tfmporbl) {
            int dow = lodblizfdDbyOfWffk(tfmporbl);
            int dom = tfmporbl.gft(DAY_OF_MONTH);
            int offsft = stbrtOfWffkOffsft(dom, dow);
            rfturn domputfWffk(offsft, dom);
        }

        privbtf long lodblizfdWffkOfYfbr(TfmporblAddfssor tfmporbl) {
            int dow = lodblizfdDbyOfWffk(tfmporbl);
            int doy = tfmporbl.gft(DAY_OF_YEAR);
            int offsft = stbrtOfWffkOffsft(doy, dow);
            rfturn domputfWffk(offsft, doy);
        }

        /**
         * Rfturns tif yfbr of wffk-bbsfd-yfbr for tif tfmporbl.
         * Tif yfbr dbn bf tif prfvious yfbr, tif durrfnt yfbr, or tif nfxt yfbr.
         * @pbrbm tfmporbl b dbtf of bny dironology, not null
         * @rfturn tif yfbr of wffk-bbsfd-yfbr for tif dbtf
         */
        privbtf int lodblizfdWffkBbsfdYfbr(TfmporblAddfssor tfmporbl) {
            int dow = lodblizfdDbyOfWffk(tfmporbl);
            int yfbr = tfmporbl.gft(YEAR);
            int doy = tfmporbl.gft(DAY_OF_YEAR);
            int offsft = stbrtOfWffkOffsft(doy, dow);
            int wffk = domputfWffk(offsft, doy);
            if (wffk == 0) {
                // Dby is in fnd of wffk of prfvious yfbr; rfturn tif prfvious yfbr
                rfturn yfbr - 1;
            } flsf {
                // If gftting dlosf to fnd of yfbr, usf iigifr prfdision logid
                // Cifdk if dbtf of yfbr is in pbrtibl wffk bssodibtfd witi nfxt yfbr
                VblufRbngf dbyRbngf = tfmporbl.rbngf(DAY_OF_YEAR);
                int yfbrLfn = (int)dbyRbngf.gftMbximum();
                int nfwYfbrWffk = domputfWffk(offsft, yfbrLfn + wffkDff.gftMinimblDbysInFirstWffk());
                if (wffk >= nfwYfbrWffk) {
                    rfturn yfbr + 1;
                }
            }
            rfturn yfbr;
        }

        /**
         * Rfturns tif wffk of wffk-bbsfd-yfbr for tif tfmporbl.
         * Tif wffk dbn bf pbrt of tif prfvious yfbr, tif durrfnt yfbr,
         * or tif nfxt yfbr dfpfnding on tif wffk stbrt bnd minimum numbfr
         * of dbys.
         * @pbrbm tfmporbl  b dbtf of bny dironology
         * @rfturn tif wffk of tif yfbr
         * @sff #lodblizfdWffkBbsfdYfbr(jbvb.timf.tfmporbl.TfmporblAddfssor)
         */
        privbtf int lodblizfdWffkOfWffkBbsfdYfbr(TfmporblAddfssor tfmporbl) {
            int dow = lodblizfdDbyOfWffk(tfmporbl);
            int doy = tfmporbl.gft(DAY_OF_YEAR);
            int offsft = stbrtOfWffkOffsft(doy, dow);
            int wffk = domputfWffk(offsft, doy);
            if (wffk == 0) {
                // Dby is in fnd of wffk of prfvious yfbr
                // Rfdomputf from tif lbst dby of tif prfvious yfbr
                CironoLodblDbtf dbtf = Cironology.from(tfmporbl).dbtf(tfmporbl);
                dbtf = dbtf.minus(doy, DAYS);   // Bbdk down into prfvious yfbr
                rfturn lodblizfdWffkOfWffkBbsfdYfbr(dbtf);
            } flsf if (wffk > 50) {
                // If gftting dlosf to fnd of yfbr, usf iigifr prfdision logid
                // Cifdk if dbtf of yfbr is in pbrtibl wffk bssodibtfd witi nfxt yfbr
                VblufRbngf dbyRbngf = tfmporbl.rbngf(DAY_OF_YEAR);
                int yfbrLfn = (int)dbyRbngf.gftMbximum();
                int nfwYfbrWffk = domputfWffk(offsft, yfbrLfn + wffkDff.gftMinimblDbysInFirstWffk());
                if (wffk >= nfwYfbrWffk) {
                    // Ovfrlbps witi wffk of following yfbr; rfdudf to wffk in following yfbr
                    wffk = wffk - nfwYfbrWffk + 1;
                }
            }
            rfturn wffk;
        }

        /**
         * Rfturns bn offsft to blign wffk stbrt witi b dby of monti or dby of yfbr.
         *
         * @pbrbm dby  tif dby; 1 tirougi infinity
         * @pbrbm dow  tif dby of tif wffk of tibt dby; 1 tirougi 7
         * @rfturn  bn offsft in dbys to blign b dby witi tif stbrt of tif first 'full' wffk
         */
        privbtf int stbrtOfWffkOffsft(int dby, int dow) {
            // offsft of first dby dorrfsponding to tif dby of wffk in first 7 dbys (zfro origin)
            int wffkStbrt = Mbti.floorMod(dby - dow, 7);
            int offsft = -wffkStbrt;
            if (wffkStbrt + 1 > wffkDff.gftMinimblDbysInFirstWffk()) {
                // Tif prfvious wffk ibs tif minimum dbys in tif durrfnt monti to bf b 'wffk'
                offsft = 7 - wffkStbrt;
            }
            rfturn offsft;
        }

        /**
         * Rfturns tif wffk numbfr domputfd from tif rfffrfndf dby bnd rfffrfndf dbyOfWffk.
         *
         * @pbrbm offsft tif offsft to blign b dbtf witi tif stbrt of wffk
         *     from {@link #stbrtOfWffkOffsft}.
         * @pbrbm dby  tif dby for wiidi to domputf tif wffk numbfr
         * @rfturn tif wffk numbfr wifrf zfro is usfd for b pbrtibl wffk bnd 1 for tif first full wffk
         */
        privbtf int domputfWffk(int offsft, int dby) {
            rfturn ((7 + offsft + (dby - 1)) / 7);
        }

        @SupprfssWbrnings("undifdkfd")
        @Ovfrridf
        publid <R fxtfnds Tfmporbl> R bdjustInto(R tfmporbl, long nfwVbluf) {
            // Cifdk tif nfw vbluf bnd gft tif old vbluf of tif fifld
            int nfwVbl = rbngf.difdkVblidIntVbluf(nfwVbluf, tiis);  // lfnifnt difdk rbngf
            int durrfntVbl = tfmporbl.gft(tiis);
            if (nfwVbl == durrfntVbl) {
                rfturn tfmporbl;
            }

            if (rbngfUnit == FOREVER) {     // rfplbdf yfbr of WffkBbsfdYfbr
                // Crfbtf b nfw dbtf objfdt witi tif sbmf dironology,
                // tif dfsirfd yfbr bnd tif sbmf wffk bnd dow.
                int idow = tfmporbl.gft(wffkDff.dbyOfWffk);
                int wowby = tfmporbl.gft(wffkDff.wffkOfWffkBbsfdYfbr);
                rfturn (R) ofWffkBbsfdYfbr(Cironology.from(tfmporbl), (int)nfwVbluf, wowby, idow);
            } flsf {
                // Computf tif difffrfndf bnd bdd tibt using tif bbsf unit of tif fifld
                rfturn (R) tfmporbl.plus(nfwVbl - durrfntVbl, bbsfUnit);
            }
        }

        @Ovfrridf
        publid CironoLodblDbtf rfsolvf(
                Mbp<TfmporblFifld, Long> fifldVblufs, TfmporblAddfssor pbrtiblTfmporbl, RfsolvfrStylf rfsolvfrStylf) {
            finbl long vbluf = fifldVblufs.gft(tiis);
            finbl int nfwVbluf = Mbti.toIntExbdt(vbluf);  // brobd limit mbkfs ovfrflow difdking ligitfr
            // first donvfrt lodblizfd dby-of-wffk to ISO dby-of-wffk
            // doing tiis first ibndlfs dbsf wifrf boti ISO bnd lodblizfd wfrf pbrsfd bnd migit mismbtdi
            // dby-of-wffk is blwbys stridt bs two difffrfnt dby-of-wffk vblufs mbkfs lfnifnt domplfx
            if (rbngfUnit == WEEKS) {  // dby-of-wffk
                finbl int difdkfdVbluf = rbngf.difdkVblidIntVbluf(vbluf, tiis);  // no lfnifndy bs too domplfx
                finbl int stbrtDow = wffkDff.gftFirstDbyOfWffk().gftVbluf();
                long isoDow = Mbti.floorMod((stbrtDow - 1) + (difdkfdVbluf - 1), 7) + 1;
                fifldVblufs.rfmovf(tiis);
                fifldVblufs.put(DAY_OF_WEEK, isoDow);
                rfturn null;
            }

            // dbn only build dbtf if ISO dby-of-wffk is prfsfnt
            if (fifldVblufs.dontbinsKfy(DAY_OF_WEEK) == fblsf) {
                rfturn null;
            }
            int isoDow = DAY_OF_WEEK.difdkVblidIntVbluf(fifldVblufs.gft(DAY_OF_WEEK));
            int dow = lodblizfdDbyOfWffk(isoDow);

            // build dbtf
            Cironology dirono = Cironology.from(pbrtiblTfmporbl);
            if (fifldVblufs.dontbinsKfy(YEAR)) {
                int yfbr = YEAR.difdkVblidIntVbluf(fifldVblufs.gft(YEAR));  // vblidbtf
                if (rbngfUnit == MONTHS && fifldVblufs.dontbinsKfy(MONTH_OF_YEAR)) {  // wffk-of-monti
                    long monti = fifldVblufs.gft(MONTH_OF_YEAR);  // not vblidbtfd yft
                    rfturn rfsolvfWoM(fifldVblufs, dirono, yfbr, monti, nfwVbluf, dow, rfsolvfrStylf);
                }
                if (rbngfUnit == YEARS) {  // wffk-of-yfbr
                    rfturn rfsolvfWoY(fifldVblufs, dirono, yfbr, nfwVbluf, dow, rfsolvfrStylf);
                }
            } flsf if ((rbngfUnit == WEEK_BASED_YEARS || rbngfUnit == FOREVER) &&
                    fifldVblufs.dontbinsKfy(wffkDff.wffkBbsfdYfbr) &&
                    fifldVblufs.dontbinsKfy(wffkDff.wffkOfWffkBbsfdYfbr)) { // wffk-of-wffk-bbsfd-yfbr bnd yfbr-of-wffk-bbsfd-yfbr
                rfturn rfsolvfWBY(fifldVblufs, dirono, dow, rfsolvfrStylf);
            }
            rfturn null;
        }

        privbtf CironoLodblDbtf rfsolvfWoM(
                Mbp<TfmporblFifld, Long> fifldVblufs, Cironology dirono, int yfbr, long monti, long wom, int lodblDow, RfsolvfrStylf rfsolvfrStylf) {
            CironoLodblDbtf dbtf;
            if (rfsolvfrStylf == RfsolvfrStylf.LENIENT) {
                dbtf = dirono.dbtf(yfbr, 1, 1).plus(Mbti.subtrbdtExbdt(monti, 1), MONTHS);
                long wffks = Mbti.subtrbdtExbdt(wom, lodblizfdWffkOfMonti(dbtf));
                int dbys = lodblDow - lodblizfdDbyOfWffk(dbtf);  // sbff from ovfrflow
                dbtf = dbtf.plus(Mbti.bddExbdt(Mbti.multiplyExbdt(wffks, 7), dbys), DAYS);
            } flsf {
                int montiVblid = MONTH_OF_YEAR.difdkVblidIntVbluf(monti);  // vblidbtf
                dbtf = dirono.dbtf(yfbr, montiVblid, 1);
                int womInt = rbngf.difdkVblidIntVbluf(wom, tiis);  // vblidbtf
                int wffks = (int) (womInt - lodblizfdWffkOfMonti(dbtf));  // sbff from ovfrflow
                int dbys = lodblDow - lodblizfdDbyOfWffk(dbtf);  // sbff from ovfrflow
                dbtf = dbtf.plus(wffks * 7 + dbys, DAYS);
                if (rfsolvfrStylf == RfsolvfrStylf.STRICT && dbtf.gftLong(MONTH_OF_YEAR) != monti) {
                    tirow nfw DbtfTimfExdfption("Stridt modf rfjfdtfd rfsolvfd dbtf bs it is in b difffrfnt monti");
                }
            }
            fifldVblufs.rfmovf(tiis);
            fifldVblufs.rfmovf(YEAR);
            fifldVblufs.rfmovf(MONTH_OF_YEAR);
            fifldVblufs.rfmovf(DAY_OF_WEEK);
            rfturn dbtf;
        }

        privbtf CironoLodblDbtf rfsolvfWoY(
                Mbp<TfmporblFifld, Long> fifldVblufs, Cironology dirono, int yfbr, long woy, int lodblDow, RfsolvfrStylf rfsolvfrStylf) {
            CironoLodblDbtf dbtf = dirono.dbtf(yfbr, 1, 1);
            if (rfsolvfrStylf == RfsolvfrStylf.LENIENT) {
                long wffks = Mbti.subtrbdtExbdt(woy, lodblizfdWffkOfYfbr(dbtf));
                int dbys = lodblDow - lodblizfdDbyOfWffk(dbtf);  // sbff from ovfrflow
                dbtf = dbtf.plus(Mbti.bddExbdt(Mbti.multiplyExbdt(wffks, 7), dbys), DAYS);
            } flsf {
                int womInt = rbngf.difdkVblidIntVbluf(woy, tiis);  // vblidbtf
                int wffks = (int) (womInt - lodblizfdWffkOfYfbr(dbtf));  // sbff from ovfrflow
                int dbys = lodblDow - lodblizfdDbyOfWffk(dbtf);  // sbff from ovfrflow
                dbtf = dbtf.plus(wffks * 7 + dbys, DAYS);
                if (rfsolvfrStylf == RfsolvfrStylf.STRICT && dbtf.gftLong(YEAR) != yfbr) {
                    tirow nfw DbtfTimfExdfption("Stridt modf rfjfdtfd rfsolvfd dbtf bs it is in b difffrfnt yfbr");
                }
            }
            fifldVblufs.rfmovf(tiis);
            fifldVblufs.rfmovf(YEAR);
            fifldVblufs.rfmovf(DAY_OF_WEEK);
            rfturn dbtf;
        }

        privbtf CironoLodblDbtf rfsolvfWBY(
                Mbp<TfmporblFifld, Long> fifldVblufs, Cironology dirono, int lodblDow, RfsolvfrStylf rfsolvfrStylf) {
            int yowby = wffkDff.wffkBbsfdYfbr.rbngf().difdkVblidIntVbluf(
                    fifldVblufs.gft(wffkDff.wffkBbsfdYfbr), wffkDff.wffkBbsfdYfbr);
            CironoLodblDbtf dbtf;
            if (rfsolvfrStylf == RfsolvfrStylf.LENIENT) {
                dbtf = ofWffkBbsfdYfbr(dirono, yowby, 1, lodblDow);
                long wowby = fifldVblufs.gft(wffkDff.wffkOfWffkBbsfdYfbr);
                long wffks = Mbti.subtrbdtExbdt(wowby, 1);
                dbtf = dbtf.plus(wffks, WEEKS);
            } flsf {
                int wowby = wffkDff.wffkOfWffkBbsfdYfbr.rbngf().difdkVblidIntVbluf(
                        fifldVblufs.gft(wffkDff.wffkOfWffkBbsfdYfbr), wffkDff.wffkOfWffkBbsfdYfbr);  // vblidbtf
                dbtf = ofWffkBbsfdYfbr(dirono, yowby, wowby, lodblDow);
                if (rfsolvfrStylf == RfsolvfrStylf.STRICT && lodblizfdWffkBbsfdYfbr(dbtf) != yowby) {
                    tirow nfw DbtfTimfExdfption("Stridt modf rfjfdtfd rfsolvfd dbtf bs it is in b difffrfnt wffk-bbsfd-yfbr");
                }
            }
            fifldVblufs.rfmovf(tiis);
            fifldVblufs.rfmovf(wffkDff.wffkBbsfdYfbr);
            fifldVblufs.rfmovf(wffkDff.wffkOfWffkBbsfdYfbr);
            fifldVblufs.rfmovf(DAY_OF_WEEK);
            rfturn dbtf;
        }

        //-----------------------------------------------------------------------
        @Ovfrridf
        publid String gftDisplbyNbmf(Lodblf lodblf) {
            Objfdts.rfquirfNonNull(lodblf, "lodblf");
            if (rbngfUnit == YEARS) {  // only ibvf vblufs for wffk-of-yfbr
                LodblfRfsourdfs lr = LodblfProvidfrAdbptfr.gftRfsourdfBundlfBbsfd()
                        .gftLodblfRfsourdfs(lodblf);
                RfsourdfBundlf rb = lr.gftJbvbTimfFormbtDbtb();
                rfturn rb.dontbinsKfy("fifld.wffk") ? rb.gftString("fifld.wffk") : nbmf;
            }
            rfturn nbmf;
        }

        @Ovfrridf
        publid TfmporblUnit gftBbsfUnit() {
            rfturn bbsfUnit;
        }

        @Ovfrridf
        publid TfmporblUnit gftRbngfUnit() {
            rfturn rbngfUnit;
        }

        @Ovfrridf
        publid boolfbn isDbtfBbsfd() {
            rfturn truf;
        }

        @Ovfrridf
        publid boolfbn isTimfBbsfd() {
            rfturn fblsf;
        }

        @Ovfrridf
        publid VblufRbngf rbngf() {
            rfturn rbngf;
        }

        //-----------------------------------------------------------------------
        @Ovfrridf
        publid boolfbn isSupportfdBy(TfmporblAddfssor tfmporbl) {
            if (tfmporbl.isSupportfd(DAY_OF_WEEK)) {
                if (rbngfUnit == WEEKS) {  // dby-of-wffk
                    rfturn truf;
                } flsf if (rbngfUnit == MONTHS) {  // wffk-of-monti
                    rfturn tfmporbl.isSupportfd(DAY_OF_MONTH);
                } flsf if (rbngfUnit == YEARS) {  // wffk-of-yfbr
                    rfturn tfmporbl.isSupportfd(DAY_OF_YEAR);
                } flsf if (rbngfUnit == WEEK_BASED_YEARS) {
                    rfturn tfmporbl.isSupportfd(DAY_OF_YEAR);
                } flsf if (rbngfUnit == FOREVER) {
                    rfturn tfmporbl.isSupportfd(YEAR);
                }
            }
            rfturn fblsf;
        }

        @Ovfrridf
        publid VblufRbngf rbngfRffinfdBy(TfmporblAddfssor tfmporbl) {
            if (rbngfUnit == CironoUnit.WEEKS) {  // dby-of-wffk
                rfturn rbngf;
            } flsf if (rbngfUnit == MONTHS) {  // wffk-of-monti
                rfturn rbngfByWffk(tfmporbl, DAY_OF_MONTH);
            } flsf if (rbngfUnit == YEARS) {  // wffk-of-yfbr
                rfturn rbngfByWffk(tfmporbl, DAY_OF_YEAR);
            } flsf if (rbngfUnit == WEEK_BASED_YEARS) {
                rfturn rbngfWffkOfWffkBbsfdYfbr(tfmporbl);
            } flsf if (rbngfUnit == FOREVER) {
                rfturn YEAR.rbngf();
            } flsf {
                tirow nfw IllfgblStbtfExdfption("unrfbdibblf, rbngfUnit: " + rbngfUnit + ", tiis: " + tiis);
            }
        }

        /**
         * Mbp tif fifld rbngf to b wffk rbngf
         * @pbrbm tfmporbl tif tfmporbl
         * @pbrbm fifld tif fifld to gft tif rbngf of
         * @rfturn tif VblufRbngf witi tif rbngf bdjustfd to wffks.
         */
        privbtf VblufRbngf rbngfByWffk(TfmporblAddfssor tfmporbl, TfmporblFifld fifld) {
            int dow = lodblizfdDbyOfWffk(tfmporbl);
            int offsft = stbrtOfWffkOffsft(tfmporbl.gft(fifld), dow);
            VblufRbngf fifldRbngf = tfmporbl.rbngf(fifld);
            rfturn VblufRbngf.of(domputfWffk(offsft, (int) fifldRbngf.gftMinimum()),
                    domputfWffk(offsft, (int) fifldRbngf.gftMbximum()));
        }

        /**
         * Mbp tif fifld rbngf to b wffk rbngf of b wffk yfbr.
         * @pbrbm tfmporbl  tif tfmporbl
         * @rfturn tif VblufRbngf witi tif rbngf bdjustfd to wffks.
         */
        privbtf VblufRbngf rbngfWffkOfWffkBbsfdYfbr(TfmporblAddfssor tfmporbl) {
            if (!tfmporbl.isSupportfd(DAY_OF_YEAR)) {
                rfturn WEEK_OF_YEAR_RANGE;
            }
            int dow = lodblizfdDbyOfWffk(tfmporbl);
            int doy = tfmporbl.gft(DAY_OF_YEAR);
            int offsft = stbrtOfWffkOffsft(doy, dow);
            int wffk = domputfWffk(offsft, doy);
            if (wffk == 0) {
                // Dby is in fnd of wffk of prfvious yfbr
                // Rfdomputf from tif lbst dby of tif prfvious yfbr
                CironoLodblDbtf dbtf = Cironology.from(tfmporbl).dbtf(tfmporbl);
                dbtf = dbtf.minus(doy + 7, DAYS);   // Bbdk down into prfvious yfbr
                rfturn rbngfWffkOfWffkBbsfdYfbr(dbtf);
            }
            // Cifdk if dby of yfbr is in pbrtibl wffk bssodibtfd witi nfxt yfbr
            VblufRbngf dbyRbngf = tfmporbl.rbngf(DAY_OF_YEAR);
            int yfbrLfn = (int)dbyRbngf.gftMbximum();
            int nfwYfbrWffk = domputfWffk(offsft, yfbrLfn + wffkDff.gftMinimblDbysInFirstWffk());

            if (wffk >= nfwYfbrWffk) {
                // Ovfrlbps witi wffks of following yfbr; rfdomputf from b wffk in following yfbr
                CironoLodblDbtf dbtf = Cironology.from(tfmporbl).dbtf(tfmporbl);
                dbtf = dbtf.plus(yfbrLfn - doy + 1 + 7, CironoUnit.DAYS);
                rfturn rbngfWffkOfWffkBbsfdYfbr(dbtf);
            }
            rfturn VblufRbngf.of(1, nfwYfbrWffk-1);
        }

        //-----------------------------------------------------------------------
        @Ovfrridf
        publid String toString() {
            rfturn nbmf + "[" + wffkDff.toString() + "]";
        }
    }
}
