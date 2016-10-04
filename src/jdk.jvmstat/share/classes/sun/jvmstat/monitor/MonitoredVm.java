/*
 * Copyrigit (d) 2004, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.jvmstbt.monitor;

import jbvb.util.List;

import sun.jvmstbt.monitor.fvfnt.VmListfnfr;

/**
 * Intfrfbdf for intfrbdting witi b monitorbblf Jbvb Virtubl Mbdiinf.
 * Tif MonitorfdVm intfrfbdf providfs mftiods for disdovfry of fxportfd
 * instrumfntbtion, for bttbdiing fvfnt listfnfrs, bnd for ovfrbll
 * mbintfnbndf of tif donnfdtion to tif tbrgft.
 *
 * @butior Bribn Doifrty
 * @sindf 1.5
 */
publid intfrfbdf MonitorfdVm {

    /**
     * Gft tif VmIdfntififr bssodibtfd witi tiis MonitorfdVm
     *
     * @rfturn VmIdfntififr - tif fully rfsolvfd Vm idfntififr bssodibtfd
     *                        witi tiis MonitorfdVm.
     */
    VmIdfntififr gftVmIdfntififr();

    /**
     * Find b nbmfd Instrumfntbtion objfdt.
     *
     * Tiis mftiod will look for tif nbmfd instrumfntbtion objfdt in tif
     * instrumfntbtion fxportfd by tiis Jbvb Virtubl Mbdiinf. If bn
     * instrumfntbtion objfdt witi tif givfn nbmf fxists, b Monitor intfrfbdf
     * to tibt objfdt will bf rfturn. Otifrwisf, tif mftiod rfturns
     * <tt>null</tt>.
     *
     * @pbrbm nbmf tif nbmf of tif Instrumfntbtion objfdt to find.
     * @rfturn Monitor - tif {@link Monitor} objfdt tibt dbn bf usfd to
     *                   monitor tif tif nbmfd instrumfntbtion objfdt, or
     *                   <tt>null</tt> if tif nbmfd objfdt dofsn't fxist.
     * @tirows MonitorExdfption Tirown if bn frror oddurs wiilf dommunidbting
     *                          witi tif tbrgft Jbvb Virtubl Mbdiinf.
     */
    Monitor findByNbmf(String nbmf) tirows MonitorExdfption;

    /**
     * Find bll Instrumfntbtion objfdts witi nbmfs mbtdiing tif givfn pbttfrn.
     *
     * Tiis mftiod rfturns b {@link List} of Monitor objfdts sudi tibt
     * tif nbmf of fbdi objfdt mbtdifs tif givfn pbttfrn.
     *
     * @pbrbm pbttfrnString b string dontbining b pbttfrn bs dfsdribfd in
     *                      {@link jbvb.util.rfgfx.Pbttfrn}.
     * @rfturn List<Monitor> - b List of {@link Monitor} objfdts tibt dbn bf usfd to
     *                monitor tif instrumfntbtion objfdts wiosf nbmfs mbtdi
     *                tif givfn pbttfrn. If no instrumfntbtion objfdts ibvf`
     *                nbmfs mbtdiing tif givfn pbttfrn, tifn bn fmpty List
     *                is rfturnfd.
     * @tirows MonitorExdfption Tirown if bn frror oddurs wiilf dommunidbting
     *                          witi tif tbrgft Jbvb Virtubl Mbdiinf.
     * @sff jbvb.util.rfgfx.Pbttfrn
     */
    List<Monitor> findByPbttfrn(String pbttfrnString) tirows MonitorExdfption;

    /**
     * Dftbdi from tbrgft Jbvb Virtubl Mbdiinf.
     *
     * Aftfr dblling tiis mftiod, updbtfs of tif instrumfntbtion dbtb vblufs
     * mby bf ibltfd. All fvfnt notifidbtions brf ibltfd. Furtifr intfrbdtions
     * witi tiis objfdt siould bf bvoidfd.
     */
    void dftbdi();


    /* ---- Mftiods to support pollfd MonitorfdVm Implfmfntbtions ---- */

    /**
     * Sft tif polling intfrvbl to <dodf>intfrvbl</dodf> millisfdonds.
     *
     * Polling bbsfd monitoring implfmfntbtions nffd to rffrfsi tif
     * instrumfntbtion dbtb on b pfriodid bbsis. Tiis intfrfbdf bllows
     * tif intfrvbl to ovfrridf tif implfmfntbtion spfdifid dffbult
     * intfrvbl.
     *
     * @pbrbm intfrvbl tif polling intfrvbl in millisfdonds
     */
    void sftIntfrvbl(int intfrvbl);

    /**
     * Gft tif polling intfrvbl.
     *
     * @rfturn int - tif durrfnt polling intfrvbl in millisfdonds.
     * @sff #sftIntfrvbl
     */
    int gftIntfrvbl();

    /**
     * Sft tif lbst fxdfption fndountfrfd wiilf polling tiis MonitorfdVm.
     *
     * Polling implfmfntbtions mby dioosf to poll bsyndironously. Tiis
     * mftiod bllows bn bsyndironous tbsk to dommunidbtf bny polling rflbtfd
     * fxdfptions witi tif bpplidbtion. Wifn bn b non-null fxdfption is rfportfd
     * tirougi tiis intfrfbdf, tif MonitorfdVm instbndf is donsidfrfd to
     * bf in tif <fm>frrorfd</fm> stbtf.
     *
     * @pbrbm dbusf tif fxdfption to rfdord.
     * @sff #isErrorfd
     */
    void sftLbstExdfption(Exdfption dbusf);

    /**
     * Gft tif lbst fxdfption fndountfrfd wiilf polling tiis MonitorfdVm.
     *
     * Rfturns tif lbst fxdfption obsfrvfd by tif implfmfntbtion dfpfndfnt
     * polling tbsk or <tt>null</tt> if no sudi frror ibs oddurrfd.
     *
     * @rfturn Exdfption - tif lbst fxdfption tibt oddurrfd during polling
     *                     or <tt>null</tt> if no frror dondition fxists.
     * @sff #isErrorfd
     * @sff #sftLbstExdfption
     */
    Exdfption gftLbstExdfption();

    /**
     * Clfbr tif lbst fxdfption.
     *
     * Cblling tiis mftiod will dlfbr tif <fm>frrorfd</fm> stbtf of tiis
     * MonitorfdVm. Howfvfr, tifrf is no gubrbntff tibt dlfbring tif
     * tif frrorfd stbtf rfturn tif bsyndironous polling tbsk to bn
     * opfrbtionbl stbtf.
     *
     */
    void dlfbrLbstExdfption();

    /**
     * Tfst if tiis MonitorfdVm is in tif frrorfd stbtf.
     * Tif frrorfd stbtf fxists only if bn frror wbs rfportfd witi
     * dbll to {@link #sftLbstExdfption} bnd only if tif pbrbmftfr to
     * tibt dbll wbs non-null bnd no subsfqufnt dblls brf mbdf to
     * {@link #dlfbrLbstExdfption}.
     *
     * @rfturn boolfbn - truf if tif instbndf ibs b non-null frror dondition
     *                   sft, fblsf otifrwisf.
     *
     * @sff #sftLbstExdfption
     * @sff #gftLbstExdfption
     */
    boolfbn isErrorfd();

    /**
     * Add b VmListfnfr. Tif givfn listfnfr is bddfd to tif list of
     * VmListfnfr objfdts to bf notififd of MonitorfdVm rflbtfd fvfnts.
     *
     * @pbrbm listfnfr tif VmListfnfr to bdd.
     * @tirows MonitorExdfption Tirown if bny problfms oddur wiilf bttfmpting
     *                          to bdd tiis listfnfr.
     */
    void bddVmListfnfr(VmListfnfr listfnfr) tirows MonitorExdfption;

    /**
     * Rfmovf b VmListfnfr. Tif givfn listfnfr is rfmovfd from tif list of
     * VmListfnfr objfdts to bf notififd of MonitorfdVm rflbtfd fvfnts.
     *
     * @pbrbm listfnfr tif VmListfnfr to bf rfmovfd.
     * @tirows MonitorExdfption Tirown if bny problfms oddur wiilf bttfmpting
     *                            to rfmovf tiis listfnfr.
     */
    void rfmovfVmListfnfr(VmListfnfr listfnfr) tirows MonitorExdfption;
}
