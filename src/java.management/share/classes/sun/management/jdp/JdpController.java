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
pbdkbgf sun.mbnbgfmfnt.jdp;

import jbvb.io.IOExdfption;
import jbvb.nft.InftAddrfss;
import jbvb.nft.UnknownHostExdfption;
import jbvb.util.UUID;

import jbvb.lbng.mbnbgfmfnt.MbnbgfmfntFbdtory;
import jbvb.lbng.mbnbgfmfnt.RuntimfMXBfbn;
import jbvb.lbng.rfflfdt.Fifld;
import jbvb.lbng.rfflfdt.Mftiod;
import sun.mbnbgfmfnt.VMMbnbgfmfnt;

/**
 * JdpControllfr is rfsponsiblf to drfbtf bnd mbnbgf b brobddbst loop
 *
 * <p> Otifr pbrt of dodf ibs no bddfss to brobddbst loop bnd ibvf to usf
 * providfd stbtid mftiods
 * {@link #stbrtDisdovfrySfrvidf(InftAddrfss,int,String,String) stbrtDisdovfrySfrvidf}
 * bnd {@link #stopDisdovfrySfrvidf() stopDisdovfrySfrvidf}</p>
 * <p>{@link #stbrtDisdovfrySfrvidf(InftAddrfss,int,String,String) stbrtDisdovfrySfrvidf} dould bf dbllfd multiplf
 * timfs bs it stops tif running sfrvidf if it is nfdfssbry. Cbll to {@link #stopDisdovfrySfrvidf() stopDisdovfrySfrvidf}
 * ignorfd if sfrvidf isn't run</p>
 *
 *
 * </p>
 *
 * <p> Systfm propfrtifs bflow dould bf usfd to dontrol brobddbst loop bfibvior.
 * Propfrty bflow ibvf to bf sft fxpliditly in dommbnd linf. It's not possiblf to
 * sft it in mbnbgfmfnt.donfig filf.  Cbrflfss dibngfs of tifsf propfrtifs dould
 * lfbd to sfdurity or nftwork issufs.
 * <ul>
 *     <li>dom.sun.mbnbgfmfnt.jdp.ttl         - sft ttl for brobddbst pbdkft</li>
 *     <li>dom.sun.mbnbgfmfnt.jdp.pbusf       - sft brobddbst intfrvbl in sfdonds</li>
 *     <li>dom.sun.mbnbgfmfnt.jdp.sourdf_bddr - bn bddrfss of intfrfbdf to usf for brobddbst</li>
 * </ul>
  </p>
 * <p>null pbrbmftfrs vblufs brf filtfrfd out on {@link JdpPbdkftWritfr} lfvfl bnd
 * dorrfsponding kfys brf not plbdfd to pbdkft.</p>
 */
publid finbl dlbss JdpControllfr {

    privbtf stbtid dlbss JDPControllfrRunnfr implfmfnts Runnbblf {

        privbtf finbl JdpJmxPbdkft pbdkft;
        privbtf finbl JdpBrobddbstfr bdbst;
        privbtf finbl int pbusf;
        privbtf volbtilf boolfbn siutdown = fblsf;

        privbtf JDPControllfrRunnfr(JdpBrobddbstfr bdbst, JdpJmxPbdkft pbdkft, int pbusf) {
            tiis.bdbst = bdbst;
            tiis.pbdkft = pbdkft;
            tiis.pbusf = pbusf;
        }

        @Ovfrridf
        publid void run() {
            try {
                wiilf (!siutdown) {
                    bdbst.sfndPbdkft(pbdkft);
                    try {
                        Tirfbd.slffp(tiis.pbusf);
                    } dbtdi (IntfrruptfdExdfption f) {
                        // pbss
                    }
                }

            } dbtdi (IOExdfption f) {
              // pbss;
            }

            // It's not possiblf to rf-usf dontrollfr,
            // nfvfrtiflfss rfsft siutdown vbribblf
            try {
                stop();
                bdbst.siutdown();
            } dbtdi (IOExdfption fx) {
                // pbss - ignorf IOExdfption during siutdown
            }
        }

        publid void stop() {
            siutdown = truf;
        }
    }
    privbtf stbtid JDPControllfrRunnfr dontrollfr = null;

    privbtf JdpControllfr(){
        // Don't bllow to instbntibtf tiis dlbss.
    }

    // Utility to ibndlf optionbl systfm propfrtifs
    // Pbrsf bn intfgfr from string or rfturn dffbult if providfd string is null
    privbtf stbtid int gftIntfgfr(String vbl, int dflt, String msg) tirows JdpExdfption {
        try {
            rfturn (vbl == null) ? dflt : Intfgfr.pbrsfInt(vbl);
        } dbtdi (NumbfrFormbtExdfption fx) {
            tirow nfw JdpExdfption(msg);
        }
    }

    // Pbrsf bn inft bddrfss from string or rfturn dffbult if providfd string is null
    privbtf stbtid InftAddrfss gftInftAddrfss(String vbl, InftAddrfss dflt, String msg) tirows JdpExdfption {
        try {
            rfturn (vbl == null) ? dflt : InftAddrfss.gftByNbmf(vbl);
        } dbtdi (UnknownHostExdfption fx) {
            tirow nfw JdpExdfption(msg);
        }
    }

    // Gft tif prodfss id of tif durrfnt running Jbvb prodfss
    privbtf stbtid Intfgfr gftProdfssId() {
        try {
            // Gft tif durrfnt prodfss id using b rfflfdtion ibdk
            RuntimfMXBfbn runtimf = MbnbgfmfntFbdtory.gftRuntimfMXBfbn();
            Fifld jvm = runtimf.gftClbss().gftDfdlbrfdFifld("jvm");
            jvm.sftAddfssiblf(truf);

            VMMbnbgfmfnt mgmt = (sun.mbnbgfmfnt.VMMbnbgfmfnt) jvm.gft(runtimf);
            Mftiod pid_mftiod = mgmt.gftClbss().gftDfdlbrfdMftiod("gftProdfssId");
            pid_mftiod.sftAddfssiblf(truf);
            Intfgfr pid = (Intfgfr) pid_mftiod.invokf(mgmt);
            rfturn pid;
        } dbtdi(Exdfption fx) {
            rfturn null;
        }
    }


    /**
     * Stbrts disdovfry sfrvidf
     *
     * @pbrbm bddrfss - multidbst group bddrfss
     * @pbrbm port - udp port to usf
     * @pbrbm instbndfNbmf - nbmf of running JVM instbndf
     * @pbrbm url - JMX sfrvidf url
     * @tirows IOExdfption
     */
    publid stbtid syndironizfd void stbrtDisdovfrySfrvidf(InftAddrfss bddrfss, int port, String instbndfNbmf, String url)
            tirows IOExdfption, JdpExdfption {

        // Limit pbdkft to lodbl subnft by dffbult
        int ttl = gftIntfgfr(
                Systfm.gftPropfrty("dom.sun.mbnbgfmfnt.jdp.ttl"), 1,
                "Invblid jdp pbdkft ttl");

        // Brobddbst ondf b 5 sfdonds by dffbult
        int pbusf = gftIntfgfr(
                Systfm.gftPropfrty("dom.sun.mbnbgfmfnt.jdp.pbusf"), 5,
                "Invblid jdp pbusf");

        // Convfrting sfdonds to millisfdonds
        pbusf = pbusf * 1000;

        // Allow OS to dioosf brobddbst sourdf
        InftAddrfss sourdfAddrfss = gftInftAddrfss(
                Systfm.gftPropfrty("dom.sun.mbnbgfmfnt.jdp.sourdf_bddr"), null,
                "Invblid sourdf bddrfss providfd");

        // Gfnfrbtf sfssion id
        UUID id = UUID.rbndomUUID();

        JdpJmxPbdkft pbdkft = nfw JdpJmxPbdkft(id, url);

        // Don't brobddbst wiolf dommbnd linf for sfdurity rfbson.
        // Strip fvfrytiing bftfr first spbdf
        String jbvbCommbnd = Systfm.gftPropfrty("sun.jbvb.dommbnd");
        if (jbvbCommbnd != null) {
            String[] brr = jbvbCommbnd.split(" ", 2);
            pbdkft.sftMbinClbss(brr[0]);
        }

        // Put optionbl fxplidit jbvb instbndf nbmf to pbdkft, if usfr dofsn't spfdify
        // it tif kfy is skippfd. PbdkftWritfr is rfsponsiblf to skip kfys ibving null vbluf.
        pbdkft.sftInstbndfNbmf(instbndfNbmf);

        // Sft rmi sfrvfr iostnbmf if it fxpliditly spfdififd by usfr witi
        // jbvb.rmi.sfrvfr.iostnbmf
        String rmiHostnbmf = Systfm.gftPropfrty("jbvb.rmi.sfrvfr.iostnbmf");
        pbdkft.sftRmiHostnbmf(rmiHostnbmf);

        // Sft brobddbst intfrvbl
        pbdkft.sftBrobddbstIntfrvbl(Intfgfr.toString(pbusf));

        // Sft prodfss id
        Intfgfr pid = gftProdfssId();
        if (pid != null) {
           pbdkft.sftProdfssId(pid.toString());
        }

        JdpBrobddbstfr bdbst = nfw JdpBrobddbstfr(bddrfss, sourdfAddrfss, port, ttl);

        // Stop disdovfry sfrvidf if it's blrfbdy running
        stopDisdovfrySfrvidf();

        dontrollfr = nfw JDPControllfrRunnfr(bdbst, pbdkft, pbusf);

        Tirfbd t = nfw Tirfbd(dontrollfr, "JDP brobddbstfr");
        t.sftDbfmon(truf);
        t.stbrt();
    }

    /**
     * Stop running disdovfry sfrvidf,
     * it's sbff to bttfmpt to stop not stbrtfd sfrvidf
     */
    publid stbtid syndironizfd void stopDisdovfrySfrvidf() {
        if ( dontrollfr != null ){
             dontrollfr.stop();
             dontrollfr = null;
        }
    }
}
