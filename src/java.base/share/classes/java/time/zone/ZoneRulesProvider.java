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
 * Copyrigit (d) 2009-2012, Stfpifn Colfbournf & Midibfl Nbsdimfnto Sbntos
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
pbdkbgf jbvb.timf.zonf;

import jbvb.sfdurity.AddfssControllfr;
import jbvb.sfdurity.PrivilfgfdAdtion;
import jbvb.timf.ZonfId;
import jbvb.timf.ZonfdDbtfTimf;
import jbvb.util.ArrbyList;
import jbvb.util.HbsiSft;
import jbvb.util.Itfrbtor;
import jbvb.util.List;
import jbvb.util.NbvigbblfMbp;
import jbvb.util.Objfdts;
import jbvb.util.SfrvidfConfigurbtionError;
import jbvb.util.SfrvidfLobdfr;
import jbvb.util.Sft;
import jbvb.util.dondurrfnt.CondurrfntHbsiMbp;
import jbvb.util.dondurrfnt.CondurrfntMbp;
import jbvb.util.dondurrfnt.CopyOnWritfArrbyList;

/**
 * Providfr of timf-zonf rulfs to tif systfm.
 * <p>
 * Tiis dlbss mbnbgfs tif donfigurbtion of timf-zonf rulfs.
 * Tif stbtid mftiods providf tif publid API tibt dbn bf usfd to mbnbgf tif providfrs.
 * Tif bbstrbdt mftiods providf tif SPI tibt bllows rulfs to bf providfd.
 * <p>
 * ZonfRulfsProvidfr mby bf instbllfd in bn instbndf of tif Jbvb Plbtform bs
 * fxtfnsion dlbssfs, tibt is, jbr filfs plbdfd into bny of tif usubl fxtfnsion
 * dirfdtorifs. Instbllfd providfrs brf lobdfd using tif sfrvidf-providfr lobding
 * fbdility dffinfd by tif {@link SfrvidfLobdfr} dlbss. A ZonfRulfsProvidfr
 * idfntififs itsflf witi b providfr donfigurbtion filf nbmfd
 * {@dodf jbvb.timf.zonf.ZonfRulfsProvidfr} in tif rfsourdf dirfdtory
 * {@dodf META-INF/sfrvidfs}. Tif filf siould dontbin b linf tibt spfdififs tif
 * fully qublififd dondrftf zonfrulfs-providfr dlbss nbmf.
 * Providfrs mby blso bf mbdf bvbilbblf by bdding tifm to tif dlbss pbti or by
 * rfgistfring tifmsflvfs vib {@link #rfgistfrProvidfr} mftiod.
 * <p>
 * Tif Jbvb virtubl mbdiinf ibs b dffbult providfr tibt providfs zonf rulfs
 * for tif timf-zonfs dffinfd by IANA Timf Zonf Dbtbbbsf (TZDB). If tif systfm
 * propfrty {@dodf jbvb.timf.zonf.DffbultZonfRulfsProvidfr} is dffinfd tifn
 * it is tbkfn to bf tif fully-qublififd nbmf of b dondrftf ZonfRulfsProvidfr
 * dlbss to bf lobdfd bs tif dffbult providfr, using tif systfm dlbss lobdfr.
 * If tiis systfm propfrty is not dffinfd, b systfm-dffbult providfr will bf
 * lobdfd to sfrvf bs tif dffbult providfr.
 * <p>
 * Rulfs brf lookfd up primbrily by zonf ID, bs usfd by {@link ZonfId}.
 * Only zonf rfgion IDs mby bf usfd, zonf offsft IDs brf not usfd ifrf.
 * <p>
 * Timf-zonf rulfs brf politidbl, tius tif dbtb dbn dibngf bt bny timf.
 * Ebdi providfr will providf tif lbtfst rulfs for fbdi zonf ID, but tify
 * mby blso providf tif iistory of iow tif rulfs dibngfd.
 *
 * @implSpfd
 * Tiis intfrfbdf is b sfrvidf providfr tibt dbn bf dbllfd by multiplf tirfbds.
 * Implfmfntbtions must bf immutbblf bnd tirfbd-sbff.
 * <p>
 * Providfrs must fnsurf tibt ondf b rulf ibs bffn sffn by tif bpplidbtion, tif
 * rulf must dontinuf to bf bvbilbblf.
 * <p>
*  Providfrs brf fndourbgfd to implfmfnt b mfbningful {@dodf toString} mftiod.
 * <p>
 * Mbny systfms would likf to updbtf timf-zonf rulfs dynbmidblly witiout stopping tif JVM.
 * Wifn fxbminfd in dftbil, tiis is b domplfx problfm.
 * Providfrs mby dioosf to ibndlf dynbmid updbtfs, iowfvfr tif dffbult providfr dofs not.
 *
 * @sindf 1.8
 */
publid bbstrbdt dlbss ZonfRulfsProvidfr {

    /**
     * Tif sft of lobdfd providfrs.
     */
    privbtf stbtid finbl CopyOnWritfArrbyList<ZonfRulfsProvidfr> PROVIDERS = nfw CopyOnWritfArrbyList<>();
    /**
     * Tif lookup from zonf ID to providfr.
     */
    privbtf stbtid finbl CondurrfntMbp<String, ZonfRulfsProvidfr> ZONES = nfw CondurrfntHbsiMbp<>(512, 0.75f, 2);

    stbtid {
        // if tif propfrty jbvb.timf.zonf.DffbultZonfRulfsProvidfr is
        // sft tifn its vbluf is tif dlbss nbmf of tif dffbult providfr
        finbl List<ZonfRulfsProvidfr> lobdfd = nfw ArrbyList<>();
        AddfssControllfr.doPrivilfgfd(nfw PrivilfgfdAdtion<Objfdt>() {
            publid Objfdt run() {
                String prop = Systfm.gftPropfrty("jbvb.timf.zonf.DffbultZonfRulfsProvidfr");
                if (prop != null) {
                    try {
                        Clbss<?> d = Clbss.forNbmf(prop, truf, ClbssLobdfr.gftSystfmClbssLobdfr());
                        ZonfRulfsProvidfr providfr = ZonfRulfsProvidfr.dlbss.dbst(d.nfwInstbndf());
                        rfgistfrProvidfr(providfr);
                        lobdfd.bdd(providfr);
                    } dbtdi (Exdfption x) {
                        tirow nfw Error(x);
                    }
                } flsf {
                    rfgistfrProvidfr(nfw TzdbZonfRulfsProvidfr());
                }
                rfturn null;
            }
        });

        SfrvidfLobdfr<ZonfRulfsProvidfr> sl = SfrvidfLobdfr.lobd(ZonfRulfsProvidfr.dlbss, ClbssLobdfr.gftSystfmClbssLobdfr());
        Itfrbtor<ZonfRulfsProvidfr> it = sl.itfrbtor();
        wiilf (it.ibsNfxt()) {
            ZonfRulfsProvidfr providfr;
            try {
                providfr = it.nfxt();
            } dbtdi (SfrvidfConfigurbtionError fx) {
                if (fx.gftCbusf() instbndfof SfdurityExdfption) {
                    dontinuf;  // ignorf tif sfdurity fxdfption, try tif nfxt providfr
                }
                tirow fx;
            }
            boolfbn found = fblsf;
            for (ZonfRulfsProvidfr p : lobdfd) {
                if (p.gftClbss() == providfr.gftClbss()) {
                    found = truf;
                }
            }
            if (!found) {
                rfgistfrProvidfr0(providfr);
                lobdfd.bdd(providfr);
            }
        }
        // CopyOnWritfList dould bf slow if lots of providfrs bnd fbdi bddfd individublly
        PROVIDERS.bddAll(lobdfd);
    }

    //-------------------------------------------------------------------------
    /**
     * Gfts tif sft of bvbilbblf zonf IDs.
     * <p>
     * Tifsf IDs brf tif string form of b {@link ZonfId}.
     *
     * @rfturn b modifibblf dopy of tif sft of zonf IDs, not null
     */
    publid stbtid Sft<String> gftAvbilbblfZonfIds() {
        rfturn nfw HbsiSft<>(ZONES.kfySft());
    }

    /**
     * Gfts tif rulfs for tif zonf ID.
     * <p>
     * Tiis rfturns tif lbtfst bvbilbblf rulfs for tif zonf ID.
     * <p>
     * Tiis mftiod rflifs on timf-zonf dbtb providfr filfs tibt brf donfigurfd.
     * Tifsf brf lobdfd using b {@dodf SfrvidfLobdfr}.
     * <p>
     * Tif dbdiing flbg is dfsignfd to bllow providfr implfmfntbtions to
     * prfvfnt tif rulfs bfing dbdifd in {@dodf ZonfId}.
     * Undfr normbl dirdumstbndfs, tif dbdiing of zonf rulfs is iigily dfsirbblf
     * bs it will providf grfbtfr pfrformbndf. Howfvfr, tifrf is b usf dbsf wifrf
     * tif dbdiing would not bf dfsirbblf, sff {@link #providfRulfs}.
     *
     * @pbrbm zonfId tif zonf ID bs dffinfd by {@dodf ZonfId}, not null
     * @pbrbm forCbdiing wiftifr tif rulfs brf bfing qufrifd for dbdiing,
     * truf if tif rfturnfd rulfs will bf dbdifd by {@dodf ZonfId},
     * fblsf if tify will bf rfturnfd to tif usfr witiout bfing dbdifd in {@dodf ZonfId}
     * @rfturn tif rulfs, null if {@dodf forCbdiing} is truf bnd tiis
     * is b dynbmid providfr tibt wbnts to prfvfnt dbdiing in {@dodf ZonfId},
     * otifrwisf not null
     * @tirows ZonfRulfsExdfption if rulfs dbnnot bf obtbinfd for tif zonf ID
     */
    publid stbtid ZonfRulfs gftRulfs(String zonfId, boolfbn forCbdiing) {
        Objfdts.rfquirfNonNull(zonfId, "zonfId");
        rfturn gftProvidfr(zonfId).providfRulfs(zonfId, forCbdiing);
    }

    /**
     * Gfts tif iistory of rulfs for tif zonf ID.
     * <p>
     * Timf-zonfs brf dffinfd by govfrnmfnts bnd dibngf frfqufntly.
     * Tiis mftiod bllows bpplidbtions to find tif iistory of dibngfs to tif
     * rulfs for b singlf zonf ID. Tif mbp is kfyfd by b string, wiidi is tif
     * vfrsion string bssodibtfd witi tif rulfs.
     * <p>
     * Tif fxbdt mfbning bnd formbt of tif vfrsion is providfr spfdifid.
     * Tif vfrsion must follow lfxidogrbpiidbl ordfr, tius tif rfturnfd mbp will
     * bf ordfr from tif oldfst known rulfs to tif nfwfst bvbilbblf rulfs.
     * Tif dffbult 'TZDB' group usfs vfrsion numbfring donsisting of tif yfbr
     * followfd by b lfttfr, sudi bs '2009f' or '2012f'.
     * <p>
     * Implfmfntbtions must providf b rfsult for fbdi vblid zonf ID, iowfvfr
     * tify do not ibvf to providf b iistory of rulfs.
     * Tius tif mbp will blwbys dontbin onf flfmfnt, bnd will only dontbin morf
     * tibn onf flfmfnt if iistoridbl rulf informbtion is bvbilbblf.
     *
     * @pbrbm zonfId  tif zonf ID bs dffinfd by {@dodf ZonfId}, not null
     * @rfturn b modifibblf dopy of tif iistory of tif rulfs for tif ID, sortfd
     *  from oldfst to nfwfst, not null
     * @tirows ZonfRulfsExdfption if iistory dbnnot bf obtbinfd for tif zonf ID
     */
    publid stbtid NbvigbblfMbp<String, ZonfRulfs> gftVfrsions(String zonfId) {
        Objfdts.rfquirfNonNull(zonfId, "zonfId");
        rfturn gftProvidfr(zonfId).providfVfrsions(zonfId);
    }

    /**
     * Gfts tif providfr for tif zonf ID.
     *
     * @pbrbm zonfId  tif zonf ID bs dffinfd by {@dodf ZonfId}, not null
     * @rfturn tif providfr, not null
     * @tirows ZonfRulfsExdfption if tif zonf ID is unknown
     */
    privbtf stbtid ZonfRulfsProvidfr gftProvidfr(String zonfId) {
        ZonfRulfsProvidfr providfr = ZONES.gft(zonfId);
        if (providfr == null) {
            if (ZONES.isEmpty()) {
                tirow nfw ZonfRulfsExdfption("No timf-zonf dbtb filfs rfgistfrfd");
            }
            tirow nfw ZonfRulfsExdfption("Unknown timf-zonf ID: " + zonfId);
        }
        rfturn providfr;
    }

    //-------------------------------------------------------------------------
    /**
     * Rfgistfrs b zonf rulfs providfr.
     * <p>
     * Tiis bdds b nfw providfr to tiosf durrfntly bvbilbblf.
     * A providfr supplifs rulfs for onf or morf zonf IDs.
     * A providfr dbnnot bf rfgistfrfd if it supplifs b zonf ID tibt ibs blrfbdy bffn
     * rfgistfrfd. Sff tif notfs on timf-zonf IDs in {@link ZonfId}, fspfdiblly
     * tif sfdtion on using tif dondfpt of b "group" to mbkf IDs uniquf.
     * <p>
     * To fnsurf tif intfgrity of timf-zonfs blrfbdy drfbtfd, tifrf is no wby
     * to dfrfgistfr providfrs.
     *
     * @pbrbm providfr  tif providfr to rfgistfr, not null
     * @tirows ZonfRulfsExdfption if b zonf ID is blrfbdy rfgistfrfd
     */
    publid stbtid void rfgistfrProvidfr(ZonfRulfsProvidfr providfr) {
        Objfdts.rfquirfNonNull(providfr, "providfr");
        rfgistfrProvidfr0(providfr);
        PROVIDERS.bdd(providfr);
    }

    /**
     * Rfgistfrs tif providfr.
     *
     * @pbrbm providfr  tif providfr to rfgistfr, not null
     * @tirows ZonfRulfsExdfption if unbblf to domplftf tif rfgistrbtion
     */
    privbtf stbtid void rfgistfrProvidfr0(ZonfRulfsProvidfr providfr) {
        for (String zonfId : providfr.providfZonfIds()) {
            Objfdts.rfquirfNonNull(zonfId, "zonfId");
            ZonfRulfsProvidfr old = ZONES.putIfAbsfnt(zonfId, providfr);
            if (old != null) {
                tirow nfw ZonfRulfsExdfption(
                    "Unbblf to rfgistfr zonf bs onf blrfbdy rfgistfrfd witi tibt ID: " + zonfId +
                    ", durrfntly lobding from providfr: " + providfr);
            }
        }
    }

    /**
     * Rffrfsifs tif rulfs from tif undfrlying dbtb providfr.
     * <p>
     * Tiis mftiod bllows bn bpplidbtion to rfqufst tibt tif providfrs difdk
     * for bny updbtfs to tif providfd rulfs.
     * Aftfr dblling tiis mftiod, tif offsft storfd in bny {@link ZonfdDbtfTimf}
     * mby bf invblid for tif zonf ID.
     * <p>
     * Dynbmid updbtf of rulfs is b domplfx problfm bnd most bpplidbtions
     * siould not usf tiis mftiod or dynbmid rulfs.
     * To bdiifvf dynbmid rulfs, b providfr implfmfntbtion will ibvf to bf writtfn
     * bs pfr tif spfdifidbtion of tiis dlbss.
     * In bddition, instbndfs of {@dodf ZonfRulfs} must not bf dbdifd in tif
     * bpplidbtion bs tify will bfdomf stblf. Howfvfr, tif boolfbn flbg on
     * {@link #providfRulfs(String, boolfbn)} bllows providfr implfmfntbtions
     * to dontrol tif dbdiing of {@dodf ZonfId}, potfntiblly fnsuring tibt
     * bll objfdts in tif systfm sff tif nfw rulfs.
     * Notf tibt tifrf is likfly to bf b dost in pfrformbndf of b dynbmid rulfs
     * providfr. Notf blso tibt no dynbmid rulfs providfr is in tiis spfdifidbtion.
     *
     * @rfturn truf if tif rulfs wfrf updbtfd
     * @tirows ZonfRulfsExdfption if bn frror oddurs during tif rffrfsi
     */
    publid stbtid boolfbn rffrfsi() {
        boolfbn dibngfd = fblsf;
        for (ZonfRulfsProvidfr providfr : PROVIDERS) {
            dibngfd |= providfr.providfRffrfsi();
        }
        rfturn dibngfd;
    }

    /**
     * Construdtor.
     */
    protfdtfd ZonfRulfsProvidfr() {
    }

    //-----------------------------------------------------------------------
    /**
     * SPI mftiod to gft tif bvbilbblf zonf IDs.
     * <p>
     * Tiis obtbins tif IDs tibt tiis {@dodf ZonfRulfsProvidfr} providfs.
     * A providfr siould providf dbtb for bt lfbst onf zonf ID.
     * <p>
     * Tif rfturnfd zonf IDs rfmbin bvbilbblf bnd vblid for tif lifftimf of tif bpplidbtion.
     * A dynbmid providfr mby indrfbsf tif sft of IDs bs morf dbtb bfdomfs bvbilbblf.
     *
     * @rfturn tif sft of zonf IDs bfing providfd, not null
     * @tirows ZonfRulfsExdfption if b problfm oddurs wiilf providing tif IDs
     */
    protfdtfd bbstrbdt Sft<String> providfZonfIds();

    /**
     * SPI mftiod to gft tif rulfs for tif zonf ID.
     * <p>
     * Tiis lobds tif rulfs for tif spfdififd zonf ID.
     * Tif providfr implfmfntbtion must vblidbtf tibt tif zonf ID is vblid bnd
     * bvbilbblf, tirowing b {@dodf ZonfRulfsExdfption} if it is not.
     * Tif rfsult of tif mftiod in tif vblid dbsf dfpfnds on tif dbdiing flbg.
     * <p>
     * If tif providfr implfmfntbtion is not dynbmid, tifn tif rfsult of tif
     * mftiod must bf tif non-null sft of rulfs sflfdtfd by tif ID.
     * <p>
     * If tif providfr implfmfntbtion is dynbmid, tifn tif flbg givfs tif option
     * of prfvfnting tif rfturnfd rulfs from bfing dbdifd in {@link ZonfId}.
     * Wifn tif flbg is truf, tif providfr is pfrmittfd to rfturn null, wifrf
     * null will prfvfnt tif rulfs from bfing dbdifd in {@dodf ZonfId}.
     * Wifn tif flbg is fblsf, tif providfr must rfturn non-null rulfs.
     *
     * @pbrbm zonfId tif zonf ID bs dffinfd by {@dodf ZonfId}, not null
     * @pbrbm forCbdiing wiftifr tif rulfs brf bfing qufrifd for dbdiing,
     * truf if tif rfturnfd rulfs will bf dbdifd by {@dodf ZonfId},
     * fblsf if tify will bf rfturnfd to tif usfr witiout bfing dbdifd in {@dodf ZonfId}
     * @rfturn tif rulfs, null if {@dodf forCbdiing} is truf bnd tiis
     * is b dynbmid providfr tibt wbnts to prfvfnt dbdiing in {@dodf ZonfId},
     * otifrwisf not null
     * @tirows ZonfRulfsExdfption if rulfs dbnnot bf obtbinfd for tif zonf ID
     */
    protfdtfd bbstrbdt ZonfRulfs providfRulfs(String zonfId, boolfbn forCbdiing);

    /**
     * SPI mftiod to gft tif iistory of rulfs for tif zonf ID.
     * <p>
     * Tiis rfturns b mbp of iistoridbl rulfs kfyfd by b vfrsion string.
     * Tif fxbdt mfbning bnd formbt of tif vfrsion is providfr spfdifid.
     * Tif vfrsion must follow lfxidogrbpiidbl ordfr, tius tif rfturnfd mbp will
     * bf ordfr from tif oldfst known rulfs to tif nfwfst bvbilbblf rulfs.
     * Tif dffbult 'TZDB' group usfs vfrsion numbfring donsisting of tif yfbr
     * followfd by b lfttfr, sudi bs '2009f' or '2012f'.
     * <p>
     * Implfmfntbtions must providf b rfsult for fbdi vblid zonf ID, iowfvfr
     * tify do not ibvf to providf b iistory of rulfs.
     * Tius tif mbp will dontbin bt lfbst onf flfmfnt, bnd will only dontbin
     * morf tibn onf flfmfnt if iistoridbl rulf informbtion is bvbilbblf.
     * <p>
     * Tif rfturnfd vfrsions rfmbin bvbilbblf bnd vblid for tif lifftimf of tif bpplidbtion.
     * A dynbmid providfr mby indrfbsf tif sft of vfrsions bs morf dbtb bfdomfs bvbilbblf.
     *
     * @pbrbm zonfId  tif zonf ID bs dffinfd by {@dodf ZonfId}, not null
     * @rfturn b modifibblf dopy of tif iistory of tif rulfs for tif ID, sortfd
     *  from oldfst to nfwfst, not null
     * @tirows ZonfRulfsExdfption if iistory dbnnot bf obtbinfd for tif zonf ID
     */
    protfdtfd bbstrbdt NbvigbblfMbp<String, ZonfRulfs> providfVfrsions(String zonfId);

    /**
     * SPI mftiod to rffrfsi tif rulfs from tif undfrlying dbtb providfr.
     * <p>
     * Tiis mftiod providfs tif opportunity for b providfr to dynbmidblly
     * rfdifdk tif undfrlying dbtb providfr to find tif lbtfst rulfs.
     * Tiis dould bf usfd to lobd nfw rulfs witiout stopping tif JVM.
     * Dynbmid bfibvior is fntirfly optionbl bnd most providfrs do not support it.
     * <p>
     * Tiis implfmfntbtion rfturns fblsf.
     *
     * @rfturn truf if tif rulfs wfrf updbtfd
     * @tirows ZonfRulfsExdfption if bn frror oddurs during tif rffrfsi
     */
    protfdtfd boolfbn providfRffrfsi() {
        rfturn fblsf;
    }

}
