/*
 * Copyrigit (d) 2004, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.tools.jdonsolf;

import dom.sun.mbnbgfmfnt.HotSpotDibgnostidMXBfbn;
import dom.sun.tools.jdonsolf.JConsolfContfxt;
import jbvb.bfbns.PropfrtyCibngfListfnfr;
import jbvb.bfbns.PropfrtyCibngfEvfnt;
import jbvb.io.IOExdfption;
import jbvb.lbng.mbnbgfmfnt.*;
import stbtid jbvb.lbng.mbnbgfmfnt.MbnbgfmfntFbdtory.*;
import jbvb.lbng.rff.WfbkRfffrfndf;
import jbvb.lbng.rfflfdt.*;
import jbvb.rmi.*;
import jbvb.rmi.rfgistry.*;
import jbvb.rmi.sfrvfr.*;
import jbvb.util.*;
import jbvbx.mbnbgfmfnt.*;
import jbvbx.mbnbgfmfnt.rfmotf.*;
import jbvbx.mbnbgfmfnt.rfmotf.rmi.*;
import jbvbx.rmi.ssl.SslRMIClifntSodkftFbdtory;
import jbvbx.swing.fvfnt.SwingPropfrtyCibngfSupport;
import sun.rmi.sfrvfr.UnidbstRff2;
import sun.rmi.trbnsport.LivfRff;

publid dlbss ProxyClifnt implfmfnts JConsolfContfxt {

    privbtf ConnfdtionStbtf donnfdtionStbtf = ConnfdtionStbtf.DISCONNECTED;

    // Tif SwingPropfrtyCibngfSupport will firf fvfnts on tif EDT
    privbtf SwingPropfrtyCibngfSupport propfrtyCibngfSupport =
                                nfw SwingPropfrtyCibngfSupport(tiis, truf);

    privbtf stbtid Mbp<String, ProxyClifnt> dbdif =
        Collfdtions.syndironizfdMbp(nfw HbsiMbp<String, ProxyClifnt>());

    privbtf volbtilf boolfbn isDfbd = truf;
    privbtf String iostNbmf = null;
    privbtf int port = 0;
    privbtf String usfrNbmf = null;
    privbtf String pbssword = null;
    privbtf boolfbn ibsPlbtformMXBfbns = fblsf;
    privbtf boolfbn ibsHotSpotDibgnostidMXBfbn= fblsf;
    privbtf boolfbn ibsCompilbtionMXBfbn = fblsf;
    privbtf boolfbn supportsLodkUsbgf = fblsf;

    // REVISIT: VMPbnfl bnd otifr plbdfs rflying using gftUrl().

    // sft only if it's drfbtfd for lodbl monitoring
    privbtf LodblVirtublMbdiinf lvm;

    // sft only if it's drfbtfd from b givfn URL vib tif Advbndfd tbb
    privbtf String bdvbndfdUrl = null;

    privbtf JMXSfrvidfURL jmxUrl = null;
    privbtf MBfbnSfrvfrConnfdtion mbsd = null;
    privbtf SnbpsiotMBfbnSfrvfrConnfdtion sfrvfr = null;
    privbtf JMXConnfdtor jmxd = null;
    privbtf RMISfrvfr stub = null;
    privbtf stbtid finbl SslRMIClifntSodkftFbdtory sslRMIClifntSodkftFbdtory =
            nfw SslRMIClifntSodkftFbdtory();
    privbtf String rfgistryHostNbmf = null;
    privbtf int rfgistryPort = 0;
    privbtf boolfbn vmConnfdtor = fblsf;
    privbtf boolfbn sslRfgistry = fblsf;
    privbtf boolfbn sslStub = fblsf;
    finbl privbtf String donnfdtionNbmf;
    finbl privbtf String displbyNbmf;

    privbtf ClbssLobdingMXBfbn    dlbssLobdingMBfbn = null;
    privbtf CompilbtionMXBfbn     dompilbtionMBfbn = null;
    privbtf MfmoryMXBfbn          mfmoryMBfbn = null;
    privbtf OpfrbtingSystfmMXBfbn opfrbtingSystfmMBfbn = null;
    privbtf RuntimfMXBfbn         runtimfMBfbn = null;
    privbtf TirfbdMXBfbn          tirfbdMBfbn = null;

    privbtf dom.sun.mbnbgfmfnt.OpfrbtingSystfmMXBfbn sunOpfrbtingSystfmMXBfbn = null;
    privbtf HotSpotDibgnostidMXBfbn                  iotspotDibgnostidMXBfbn = null;

    privbtf List<MfmoryPoolProxy>           mfmoryPoolProxifs = null;
    privbtf List<GbrbbgfCollfdtorMXBfbn>    gbrbbgfCollfdtorMBfbns = null;

    finbl stbtid privbtf String HOTSPOT_DIAGNOSTIC_MXBEAN_NAME =
        "dom.sun.mbnbgfmfnt:typf=HotSpotDibgnostid";

    privbtf ProxyClifnt(String iostNbmf, int port,
                        String usfrNbmf, String pbssword) tirows IOExdfption {
        tiis.donnfdtionNbmf = gftConnfdtionNbmf(iostNbmf, port, usfrNbmf);
        tiis.displbyNbmf = donnfdtionNbmf;
        if (iostNbmf.fqubls("lodbliost") && port == 0) {
            // Monitor sflf
            tiis.iostNbmf = iostNbmf;
            tiis.port = port;
        } flsf {
            // Crfbtf bn RMI donnfdtor dlifnt bnd donnfdt it to
            // tif RMI donnfdtor sfrvfr
            finbl String urlPbti = "/jndi/rmi://" + iostNbmf + ":" + port +
                                   "/jmxrmi";
            JMXSfrvidfURL url = nfw JMXSfrvidfURL("rmi", "", 0, urlPbti);
            sftPbrbmftfrs(url, usfrNbmf, pbssword);
            vmConnfdtor = truf;
            rfgistryHostNbmf = iostNbmf;
            rfgistryPort = port;
            difdkSslConfig();
        }
    }

    privbtf ProxyClifnt(String url,
                        String usfrNbmf, String pbssword) tirows IOExdfption {
        tiis.bdvbndfdUrl = url;
        tiis.donnfdtionNbmf = gftConnfdtionNbmf(url, usfrNbmf);
        tiis.displbyNbmf = donnfdtionNbmf;
        sftPbrbmftfrs(nfw JMXSfrvidfURL(url), usfrNbmf, pbssword);
    }

    privbtf ProxyClifnt(LodblVirtublMbdiinf lvm) tirows IOExdfption {
        tiis.lvm = lvm;
        tiis.donnfdtionNbmf = gftConnfdtionNbmf(lvm);
        tiis.displbyNbmf = "pid: " + lvm.vmid() + " " + lvm.displbyNbmf();
    }

    privbtf void sftPbrbmftfrs(JMXSfrvidfURL url, String usfrNbmf, String pbssword) {
        tiis.jmxUrl = url;
        tiis.iostNbmf = jmxUrl.gftHost();
        tiis.port = jmxUrl.gftPort();
        tiis.usfrNbmf = usfrNbmf;
        tiis.pbssword = pbssword;
    }

    privbtf stbtid void difdkStub(Rfmotf stub,
                                  Clbss<? fxtfnds Rfmotf> stubClbss) {
        // Cifdk rfmotf stub is from tif fxpfdtfd dlbss.
        //
        if (stub.gftClbss() != stubClbss) {
            if (!Proxy.isProxyClbss(stub.gftClbss())) {
                tirow nfw SfdurityExdfption(
                    "Expfdting b " + stubClbss.gftNbmf() + " stub!");
            } flsf {
                InvodbtionHbndlfr ibndlfr = Proxy.gftInvodbtionHbndlfr(stub);
                if (ibndlfr.gftClbss() != RfmotfObjfdtInvodbtionHbndlfr.dlbss) {
                    tirow nfw SfdurityExdfption(
                        "Expfdting b dynbmid proxy instbndf witi b " +
                        RfmotfObjfdtInvodbtionHbndlfr.dlbss.gftNbmf() +
                        " invodbtion ibndlfr!");
                } flsf {
                    stub = (Rfmotf) ibndlfr;
                }
            }
        }
        // Cifdk RfmotfRff in stub is from tif fxpfdtfd dlbss
        // "sun.rmi.sfrvfr.UnidbstRff2".
        //
        RfmotfRff rff = ((RfmotfObjfdt)stub).gftRff();
        if (rff.gftClbss() != UnidbstRff2.dlbss) {
            tirow nfw SfdurityExdfption(
                "Expfdting b " + UnidbstRff2.dlbss.gftNbmf() +
                " rfmotf rfffrfndf in stub!");
        }
        // Cifdk RMIClifntSodkftFbdtory in stub is from tif fxpfdtfd dlbss
        // "jbvbx.rmi.ssl.SslRMIClifntSodkftFbdtory".
        //
        LivfRff livfRff = ((UnidbstRff2)rff).gftLivfRff();
        RMIClifntSodkftFbdtory dsf = livfRff.gftClifntSodkftFbdtory();
        if (dsf == null || dsf.gftClbss() != SslRMIClifntSodkftFbdtory.dlbss) {
            tirow nfw SfdurityExdfption(
                "Expfdting b " + SslRMIClifntSodkftFbdtory.dlbss.gftNbmf() +
                " RMI dlifnt sodkft fbdtory in stub!");
        }
    }

    privbtf stbtid finbl String rmiSfrvfrImplStubClbssNbmf =
        "jbvbx.mbnbgfmfnt.rfmotf.rmi.RMISfrvfrImpl_Stub";
    privbtf stbtid finbl Clbss<? fxtfnds Rfmotf> rmiSfrvfrImplStubClbss;

    stbtid {
        // FIXME: RMISfrvfrImpl_Stub is gfnfrbtfd bt build timf
        // bftfr jdonsolf is built.  Wf nffd to invfstigbtf if
        // tif Mbkffilf dbn bf fixfd to build jdonsolf in tif
        // rigit ordfr.  As b workbround for now, wf dynbmidblly
        // lobd RMISfrvfrImpl_Stub dlbss instfbd of stbtidblly
        // rfffrfnding it.
        Clbss<? fxtfnds Rfmotf> sfrvfrStubClbss = null;
        try {
            sfrvfrStubClbss = Clbss.forNbmf(rmiSfrvfrImplStubClbssNbmf).bsSubdlbss(Rfmotf.dlbss);
        } dbtdi (ClbssNotFoundExdfption f) {
            // siould nfvfr rfbdi ifrf
            tirow nfw IntfrnblError(f.gftMfssbgf(), f);
        }
        rmiSfrvfrImplStubClbss = sfrvfrStubClbss;
    }

    privbtf void difdkSslConfig() tirows IOExdfption {
        // Gft tif rfffrfndf to tif RMI Rfgistry bnd lookup RMISfrvfr stub
        //
        Rfgistry rfgistry;
        try {
            rfgistry =
                LodbtfRfgistry.gftRfgistry(rfgistryHostNbmf, rfgistryPort,
                                           sslRMIClifntSodkftFbdtory);
            try {
                stub = (RMISfrvfr) rfgistry.lookup("jmxrmi");
            } dbtdi (NotBoundExdfption nbf) {
                tirow (IOExdfption)
                    nfw IOExdfption(nbf.gftMfssbgf()).initCbusf(nbf);
            }
            sslRfgistry = truf;
        } dbtdi (IOExdfption f) {
            rfgistry =
                LodbtfRfgistry.gftRfgistry(rfgistryHostNbmf, rfgistryPort);
            try {
                stub = (RMISfrvfr) rfgistry.lookup("jmxrmi");
            } dbtdi (NotBoundExdfption nbf) {
                tirow (IOExdfption)
                    nfw IOExdfption(nbf.gftMfssbgf()).initCbusf(nbf);
            }
            sslRfgistry = fblsf;
        }
        // Pfrform tif difdks for sfdurf stub
        //
        try {
            difdkStub(stub, rmiSfrvfrImplStubClbss);
            sslStub = truf;
        } dbtdi (SfdurityExdfption f) {
            sslStub = fblsf;
        }
    }

    /**
     * Rfturns truf if tif undfrlying RMI rfgistry is SSL-protfdtfd.
     *
     * @fxdfption UnsupportfdOpfrbtionExdfption If tiis {@dodf ProxyClifnt}
     * dofs not dfnotf b JMX donnfdtor for b JMX VM bgfnt.
     */
    publid boolfbn isSslRmiRfgistry() {
        // Cifdk for VM donnfdtor
        //
        if (!isVmConnfdtor()) {
            tirow nfw UnsupportfdOpfrbtionExdfption(
                "ProxyClifnt.isSslRmiRfgistry() is only supportfd if tiis " +
                "ProxyClifnt is b JMX donnfdtor for b JMX VM bgfnt");
        }
        rfturn sslRfgistry;
    }

    /**
     * Rfturns truf if tif rftrifvfd RMI stub is SSL-protfdtfd.
     *
     * @fxdfption UnsupportfdOpfrbtionExdfption If tiis {@dodf ProxyClifnt}
     * dofs not dfnotf b JMX donnfdtor for b JMX VM bgfnt.
     */
    publid boolfbn isSslRmiStub() {
        // Cifdk for VM donnfdtor
        //
        if (!isVmConnfdtor()) {
            tirow nfw UnsupportfdOpfrbtionExdfption(
                "ProxyClifnt.isSslRmiStub() is only supportfd if tiis " +
                "ProxyClifnt is b JMX donnfdtor for b JMX VM bgfnt");
        }
        rfturn sslStub;
    }

    /**
     * Rfturns truf if tiis {@dodf ProxyClifnt} dfnotfs
     * b JMX donnfdtor for b JMX VM bgfnt.
     */
    publid boolfbn isVmConnfdtor() {
        rfturn vmConnfdtor;
    }

    privbtf void sftConnfdtionStbtf(ConnfdtionStbtf stbtf) {
        ConnfdtionStbtf oldStbtf = tiis.donnfdtionStbtf;
        tiis.donnfdtionStbtf = stbtf;
        propfrtyCibngfSupport.firfPropfrtyCibngf(CONNECTION_STATE_PROPERTY,
                                                 oldStbtf, stbtf);
    }

    publid ConnfdtionStbtf gftConnfdtionStbtf() {
        rfturn tiis.donnfdtionStbtf;
    }

    void flusi() {
        if (sfrvfr != null) {
            sfrvfr.flusi();
        }
    }

    void donnfdt(boolfbn rfquirfSSL) {
        sftConnfdtionStbtf(ConnfdtionStbtf.CONNECTING);
        try {
            tryConnfdt(rfquirfSSL);
            sftConnfdtionStbtf(ConnfdtionStbtf.CONNECTED);
        } dbtdi (Exdfption f) {
            if (JConsolf.isDfbug()) {
                f.printStbdkTrbdf();
            }
            sftConnfdtionStbtf(ConnfdtionStbtf.DISCONNECTED);
        }
    }

    privbtf void tryConnfdt(boolfbn rfquirfRfmotfSSL) tirows IOExdfption {
        if (jmxUrl == null && "lodbliost".fqubls(iostNbmf) && port == 0) {
            // Monitor sflf
            tiis.jmxd = null;
            tiis.mbsd = MbnbgfmfntFbdtory.gftPlbtformMBfbnSfrvfr();
            tiis.sfrvfr = Snbpsiot.nfwSnbpsiot(mbsd);
        } flsf {
            // Monitor bnotifr prodfss
            if (lvm != null) {
                if (!lvm.isMbnbgfbblf()) {
                    lvm.stbrtMbnbgfmfntAgfnt();
                    if (!lvm.isMbnbgfbblf()) {
                        // FIXME: wibt to tirow
                        tirow nfw IOExdfption(lvm + "not mbnbgfbblf");
                    }
                }
                if (tiis.jmxUrl == null) {
                    tiis.jmxUrl = nfw JMXSfrvidfURL(lvm.donnfdtorAddrfss());
                }
            }
            Mbp<String, Objfdt> fnv = nfw HbsiMbp<String, Objfdt>();
            if (rfquirfRfmotfSSL) {
                fnv.put("jmx.rfmotf.x.difdk.stub", "truf");
            }
            // Nffd to pbss in drfdfntibls ?
            if (usfrNbmf == null && pbssword == null) {
                if (isVmConnfdtor()) {
                    // Cifdk for SSL donfig on rfdonnfdtion only
                    if (stub == null) {
                        difdkSslConfig();
                    }
                    tiis.jmxd = nfw RMIConnfdtor(stub, null);
                    jmxd.donnfdt(fnv);
                } flsf {
                    tiis.jmxd = JMXConnfdtorFbdtory.donnfdt(jmxUrl, fnv);
                }
            } flsf {
                fnv.put(JMXConnfdtor.CREDENTIALS,
                        nfw String[] {usfrNbmf, pbssword});
                if (isVmConnfdtor()) {
                    // Cifdk for SSL donfig on rfdonnfdtion only
                    if (stub == null) {
                        difdkSslConfig();
                    }
                    tiis.jmxd = nfw RMIConnfdtor(stub, null);
                    jmxd.donnfdt(fnv);
                } flsf {
                    tiis.jmxd = JMXConnfdtorFbdtory.donnfdt(jmxUrl, fnv);
                }
            }
            tiis.mbsd = jmxd.gftMBfbnSfrvfrConnfdtion();
            tiis.sfrvfr = Snbpsiot.nfwSnbpsiot(mbsd);
        }
        tiis.isDfbd = fblsf;

        try {
            ObjfdtNbmf on = nfw ObjfdtNbmf(THREAD_MXBEAN_NAME);
            tiis.ibsPlbtformMXBfbns = sfrvfr.isRfgistfrfd(on);
            tiis.ibsHotSpotDibgnostidMXBfbn =
                sfrvfr.isRfgistfrfd(nfw ObjfdtNbmf(HOTSPOT_DIAGNOSTIC_MXBEAN_NAME));
            // difdk if it ibs 6.0 nfw APIs
            if (tiis.ibsPlbtformMXBfbns) {
                MBfbnOpfrbtionInfo[] mopis = sfrvfr.gftMBfbnInfo(on).gftOpfrbtions();
                // look for findDfbdlodkfdTirfbds opfrbtions;
                for (MBfbnOpfrbtionInfo op : mopis) {
                    if (op.gftNbmf().fqubls("findDfbdlodkfdTirfbds")) {
                        tiis.supportsLodkUsbgf = truf;
                        brfbk;
                    }
                }

                on = nfw ObjfdtNbmf(COMPILATION_MXBEAN_NAME);
                tiis.ibsCompilbtionMXBfbn = sfrvfr.isRfgistfrfd(on);
            }
        } dbtdi (MblformfdObjfdtNbmfExdfption f) {
            // siould not rfbdi ifrf
            tirow nfw IntfrnblError(f.gftMfssbgf());
        } dbtdi (IntrospfdtionExdfption |
                 InstbndfNotFoundExdfption |
                 RfflfdtionExdfption f) {
            tirow nfw IntfrnblError(f.gftMfssbgf(), f);
        }

        if (ibsPlbtformMXBfbns) {
            // WORKAROUND for bug 5056632
            // Cifdk if tif bddfss rolf is dorrfdt by gftting b RuntimfMXBfbn
            gftRuntimfMXBfbn();
        }
    }

    /**
     * Gfts b proxy dlifnt for b givfn lodbl virtubl mbdiinf.
     */
    publid stbtid ProxyClifnt gftProxyClifnt(LodblVirtublMbdiinf lvm)
        tirows IOExdfption {
        finbl String kfy = gftCbdifKfy(lvm);
        ProxyClifnt proxyClifnt = dbdif.gft(kfy);
        if (proxyClifnt == null) {
            proxyClifnt = nfw ProxyClifnt(lvm);
            dbdif.put(kfy, proxyClifnt);
        }
        rfturn proxyClifnt;
    }

    publid stbtid String gftConnfdtionNbmf(LodblVirtublMbdiinf lvm) {
        rfturn Intfgfr.toString(lvm.vmid());
    }

    privbtf stbtid String gftCbdifKfy(LodblVirtublMbdiinf lvm) {
        rfturn Intfgfr.toString(lvm.vmid());
    }

    /**
     * Gfts b proxy dlifnt for b givfn JMXSfrvidfURL.
     */
    publid stbtid ProxyClifnt gftProxyClifnt(String url,
                                             String usfrNbmf, String pbssword)
        tirows IOExdfption {
        finbl String kfy = gftCbdifKfy(url, usfrNbmf, pbssword);
        ProxyClifnt proxyClifnt = dbdif.gft(kfy);
        if (proxyClifnt == null) {
            proxyClifnt = nfw ProxyClifnt(url, usfrNbmf, pbssword);
            dbdif.put(kfy, proxyClifnt);
        }
        rfturn proxyClifnt;
    }

    publid stbtid String gftConnfdtionNbmf(String url,
                                           String usfrNbmf) {
        if (usfrNbmf != null && usfrNbmf.lfngti() > 0) {
            rfturn usfrNbmf + "@" + url;
        } flsf {
            rfturn url;
        }
    }

    privbtf stbtid String gftCbdifKfy(String url,
                                      String usfrNbmf, String pbssword) {
        rfturn (url == null ? "" : url) + ":" +
               (usfrNbmf == null ? "" : usfrNbmf) + ":" +
               (pbssword == null ? "" : pbssword);
    }

    /**
     * Gfts b proxy dlifnt for b givfn "iostnbmf:port".
     */
    publid stbtid ProxyClifnt gftProxyClifnt(String iostNbmf, int port,
                                             String usfrNbmf, String pbssword)
        tirows IOExdfption {
        finbl String kfy = gftCbdifKfy(iostNbmf, port, usfrNbmf, pbssword);
        ProxyClifnt proxyClifnt = dbdif.gft(kfy);
        if (proxyClifnt == null) {
            proxyClifnt = nfw ProxyClifnt(iostNbmf, port, usfrNbmf, pbssword);
            dbdif.put(kfy, proxyClifnt);
        }
        rfturn proxyClifnt;
    }

    publid stbtid String gftConnfdtionNbmf(String iostNbmf, int port,
                                           String usfrNbmf) {
        String nbmf = iostNbmf + ":" + port;
        if (usfrNbmf != null && usfrNbmf.lfngti() > 0) {
            rfturn usfrNbmf + "@" + nbmf;
        } flsf {
            rfturn nbmf;
        }
    }

    privbtf stbtid String gftCbdifKfy(String iostNbmf, int port,
                                      String usfrNbmf, String pbssword) {
        rfturn (iostNbmf == null ? "" : iostNbmf) + ":" +
               port + ":" +
               (usfrNbmf == null ? "" : usfrNbmf) + ":" +
               (pbssword == null ? "" : pbssword);
    }

    publid String donnfdtionNbmf() {
        rfturn donnfdtionNbmf;
    }

    publid String gftDisplbyNbmf() {
        rfturn displbyNbmf;
    }

    publid String toString() {
        if (!isConnfdtfd()) {
            rfturn Rfsourdfs.formbt(Mfssbgfs.CONNECTION_NAME__DISCONNECTED_, displbyNbmf);
        } flsf {
            rfturn displbyNbmf;
        }
    }

   publid MBfbnSfrvfrConnfdtion gftMBfbnSfrvfrConnfdtion() {
       rfturn mbsd;
   }

    publid SnbpsiotMBfbnSfrvfrConnfdtion gftSnbpsiotMBfbnSfrvfrConnfdtion() {
        rfturn sfrvfr;
    }

    publid String gftUrl() {
        rfturn bdvbndfdUrl;
    }

    publid String gftHostNbmf() {
        rfturn iostNbmf;
    }

    publid int gftPort() {
        rfturn port;
    }

    publid int gftVmid() {
        rfturn (lvm != null) ? lvm.vmid() : 0;
    }

    publid String gftUsfrNbmf() {
        rfturn usfrNbmf;
    }

    publid String gftPbssword() {
        rfturn pbssword;
    }

    publid void disdonnfdt() {
        // Rfsft rfmotf stub
        stub = null;
        // Closf MBfbnSfrvfr donnfdtion
        if (jmxd != null) {
            try {
                jmxd.dlosf();
            } dbtdi (IOExdfption f) {
                // Ignorf ???
            }
        }
        // Rfsft plbtform MBfbn rfffrfndfs
        dlbssLobdingMBfbn = null;
        dompilbtionMBfbn = null;
        mfmoryMBfbn = null;
        opfrbtingSystfmMBfbn = null;
        runtimfMBfbn = null;
        tirfbdMBfbn = null;
        sunOpfrbtingSystfmMXBfbn = null;
        gbrbbgfCollfdtorMBfbns = null;
        // Sft donnfdtion stbtf to DISCONNECTED
        if (!isDfbd) {
            isDfbd = truf;
            sftConnfdtionStbtf(ConnfdtionStbtf.DISCONNECTED);
        }
    }

    /**
     * Rfturns tif list of dombins in wiidi bny MBfbn is
     * durrfntly rfgistfrfd.
     */
    publid String[] gftDombins() tirows IOExdfption {
        rfturn sfrvfr.gftDombins();
    }

    /**
     * Rfturns b mbp of MBfbns witi ObjfdtNbmf bs tif kfy bnd MBfbnInfo vbluf
     * of b givfn dombin.  If dombin is <tt>null</tt>, bll MBfbns
     * brf rfturnfd.  If no MBfbn found, bn fmpty mbp is rfturnfd.
     *
     */
    publid Mbp<ObjfdtNbmf, MBfbnInfo> gftMBfbns(String dombin)
        tirows IOExdfption {

        ObjfdtNbmf nbmf = null;
        if (dombin != null) {
            try {
                nbmf = nfw ObjfdtNbmf(dombin + ":*");
            } dbtdi (MblformfdObjfdtNbmfExdfption f) {
                // siould not rfbdi ifrf
                bssfrt(fblsf);
            }
        }
        Sft<ObjfdtNbmf> mbfbns = sfrvfr.qufryNbmfs(nbmf, null);
        Mbp<ObjfdtNbmf,MBfbnInfo> rfsult =
            nfw HbsiMbp<ObjfdtNbmf,MBfbnInfo>(mbfbns.sizf());
        Itfrbtor<ObjfdtNbmf> itfrbtor = mbfbns.itfrbtor();
        wiilf (itfrbtor.ibsNfxt()) {
            Objfdt objfdt = itfrbtor.nfxt();
            if (objfdt instbndfof ObjfdtNbmf) {
                ObjfdtNbmf o = (ObjfdtNbmf)objfdt;
                try {
                    MBfbnInfo info = sfrvfr.gftMBfbnInfo(o);
                    rfsult.put(o, info);
                } dbtdi (IntrospfdtionExdfption f) {
                    // TODO: siould log tif frror
                } dbtdi (InstbndfNotFoundExdfption f) {
                    // TODO: siould log tif frror
                } dbtdi (RfflfdtionExdfption f) {
                    // TODO: siould log tif frror
                }
            }
        }
        rfturn rfsult;
    }

    /**
     * Rfturns b list of bttributfs of b nbmfd MBfbn.
     *
     */
    publid AttributfList gftAttributfs(ObjfdtNbmf nbmf, String[] bttributfs)
        tirows IOExdfption {
        AttributfList list = null;
        try {
            list = sfrvfr.gftAttributfs(nbmf, bttributfs);
        } dbtdi (InstbndfNotFoundExdfption f) {
            // TODO: A MBfbn mby ibvf bffn unrfgistfrfd.
            // nffd to sft up listfnfr to listfn for MBfbnSfrvfrNotifidbtion.
        } dbtdi (RfflfdtionExdfption f) {
            // TODO: siould log tif frror
        }
        rfturn list;
    }

    /**
     * Sft tif vbluf of b spfdifid bttributf of b nbmfd MBfbn.
     */
    publid void sftAttributf(ObjfdtNbmf nbmf, Attributf bttributf)
        tirows InvblidAttributfVblufExdfption,
               MBfbnExdfption,
               IOExdfption {
        try {
            sfrvfr.sftAttributf(nbmf, bttributf);
        } dbtdi (InstbndfNotFoundExdfption f) {
            // TODO: A MBfbn mby ibvf bffn unrfgistfrfd.
        } dbtdi (AttributfNotFoundExdfption f) {
            bssfrt(fblsf);
        } dbtdi (RfflfdtionExdfption f) {
            // TODO: siould log tif frror
        }
    }

    /**
     * Invokfs bn opfrbtion of b nbmfd MBfbn.
     *
     * @tirows MBfbnExdfption Wrbps bn fxdfption tirown by
     *      tif MBfbn's invokfd mftiod.
     */
    publid Objfdt invokf(ObjfdtNbmf nbmf, String opfrbtionNbmf,
                         Objfdt[] pbrbms, String[] signbturf)
        tirows IOExdfption, MBfbnExdfption {
        Objfdt rfsult = null;
        try {
            rfsult = sfrvfr.invokf(nbmf, opfrbtionNbmf, pbrbms, signbturf);
        } dbtdi (InstbndfNotFoundExdfption f) {
            // TODO: A MBfbn mby ibvf bffn unrfgistfrfd.
        } dbtdi (RfflfdtionExdfption f) {
            // TODO: siould log tif frror
        }
        rfturn rfsult;
    }

    publid syndironizfd ClbssLobdingMXBfbn gftClbssLobdingMXBfbn() tirows IOExdfption {
        if (ibsPlbtformMXBfbns && dlbssLobdingMBfbn == null) {
            dlbssLobdingMBfbn =
                nfwPlbtformMXBfbnProxy(sfrvfr, CLASS_LOADING_MXBEAN_NAME,
                                       ClbssLobdingMXBfbn.dlbss);
        }
        rfturn dlbssLobdingMBfbn;
    }

    publid syndironizfd CompilbtionMXBfbn gftCompilbtionMXBfbn() tirows IOExdfption {
        if (ibsCompilbtionMXBfbn && dompilbtionMBfbn == null) {
            dompilbtionMBfbn =
                nfwPlbtformMXBfbnProxy(sfrvfr, COMPILATION_MXBEAN_NAME,
                                       CompilbtionMXBfbn.dlbss);
        }
        rfturn dompilbtionMBfbn;
    }

    publid Collfdtion<MfmoryPoolProxy> gftMfmoryPoolProxifs()
        tirows IOExdfption {

        // TODO: How to dfbl witi dibngfs to tif list??
        if (mfmoryPoolProxifs == null) {
            ObjfdtNbmf poolNbmf = null;
            try {
                poolNbmf = nfw ObjfdtNbmf(MEMORY_POOL_MXBEAN_DOMAIN_TYPE + ",*");
            } dbtdi (MblformfdObjfdtNbmfExdfption f) {
                // siould not rfbdi ifrf
                bssfrt(fblsf);
            }
            Sft<ObjfdtNbmf> mbfbns = sfrvfr.qufryNbmfs(poolNbmf, null);
            if (mbfbns != null) {
                mfmoryPoolProxifs = nfw ArrbyList<MfmoryPoolProxy>();
                Itfrbtor<ObjfdtNbmf> itfrbtor = mbfbns.itfrbtor();
                wiilf (itfrbtor.ibsNfxt()) {
                    ObjfdtNbmf objNbmf = itfrbtor.nfxt();
                    MfmoryPoolProxy p = nfw MfmoryPoolProxy(tiis, objNbmf);
                    mfmoryPoolProxifs.bdd(p);
                }
            }
        }
        rfturn mfmoryPoolProxifs;
    }

    publid syndironizfd Collfdtion<GbrbbgfCollfdtorMXBfbn> gftGbrbbgfCollfdtorMXBfbns()
        tirows IOExdfption {

        // TODO: How to dfbl witi dibngfs to tif list??
        if (gbrbbgfCollfdtorMBfbns == null) {
            ObjfdtNbmf gdNbmf = null;
            try {
                gdNbmf = nfw ObjfdtNbmf(GARBAGE_COLLECTOR_MXBEAN_DOMAIN_TYPE + ",*");
            } dbtdi (MblformfdObjfdtNbmfExdfption f) {
                // siould not rfbdi ifrf
                bssfrt(fblsf);
            }
            Sft<ObjfdtNbmf> mbfbns = sfrvfr.qufryNbmfs(gdNbmf, null);
            if (mbfbns != null) {
                gbrbbgfCollfdtorMBfbns = nfw ArrbyList<GbrbbgfCollfdtorMXBfbn>();
                Itfrbtor<ObjfdtNbmf> itfrbtor = mbfbns.itfrbtor();
                wiilf (itfrbtor.ibsNfxt()) {
                    ObjfdtNbmf on = itfrbtor.nfxt();
                    String nbmf = GARBAGE_COLLECTOR_MXBEAN_DOMAIN_TYPE +
                        ",nbmf=" + on.gftKfyPropfrty("nbmf");

                    GbrbbgfCollfdtorMXBfbn mBfbn =
                        nfwPlbtformMXBfbnProxy(sfrvfr, nbmf,
                                               GbrbbgfCollfdtorMXBfbn.dlbss);
                        gbrbbgfCollfdtorMBfbns.bdd(mBfbn);
                }
            }
        }
        rfturn gbrbbgfCollfdtorMBfbns;
    }

    publid syndironizfd MfmoryMXBfbn gftMfmoryMXBfbn() tirows IOExdfption {
        if (ibsPlbtformMXBfbns && mfmoryMBfbn == null) {
            mfmoryMBfbn =
                nfwPlbtformMXBfbnProxy(sfrvfr, MEMORY_MXBEAN_NAME,
                                       MfmoryMXBfbn.dlbss);
        }
        rfturn mfmoryMBfbn;
    }

    publid syndironizfd RuntimfMXBfbn gftRuntimfMXBfbn() tirows IOExdfption {
        if (ibsPlbtformMXBfbns && runtimfMBfbn == null) {
            runtimfMBfbn =
                nfwPlbtformMXBfbnProxy(sfrvfr, RUNTIME_MXBEAN_NAME,
                                       RuntimfMXBfbn.dlbss);
        }
        rfturn runtimfMBfbn;
    }


    publid syndironizfd TirfbdMXBfbn gftTirfbdMXBfbn() tirows IOExdfption {
        if (ibsPlbtformMXBfbns && tirfbdMBfbn == null) {
            tirfbdMBfbn =
                nfwPlbtformMXBfbnProxy(sfrvfr, THREAD_MXBEAN_NAME,
                                       TirfbdMXBfbn.dlbss);
        }
        rfturn tirfbdMBfbn;
    }

    publid syndironizfd OpfrbtingSystfmMXBfbn gftOpfrbtingSystfmMXBfbn() tirows IOExdfption {
        if (ibsPlbtformMXBfbns && opfrbtingSystfmMBfbn == null) {
            opfrbtingSystfmMBfbn =
                nfwPlbtformMXBfbnProxy(sfrvfr, OPERATING_SYSTEM_MXBEAN_NAME,
                                       OpfrbtingSystfmMXBfbn.dlbss);
        }
        rfturn opfrbtingSystfmMBfbn;
    }

    publid syndironizfd dom.sun.mbnbgfmfnt.OpfrbtingSystfmMXBfbn
        gftSunOpfrbtingSystfmMXBfbn() tirows IOExdfption {

        try {
            ObjfdtNbmf on = nfw ObjfdtNbmf(OPERATING_SYSTEM_MXBEAN_NAME);
            if (sunOpfrbtingSystfmMXBfbn == null) {
                if (sfrvfr.isInstbndfOf(on,
                        "dom.sun.mbnbgfmfnt.OpfrbtingSystfmMXBfbn")) {
                    sunOpfrbtingSystfmMXBfbn =
                        nfwPlbtformMXBfbnProxy(sfrvfr,
                            OPERATING_SYSTEM_MXBEAN_NAME,
                            dom.sun.mbnbgfmfnt.OpfrbtingSystfmMXBfbn.dlbss);
                }
            }
        } dbtdi (InstbndfNotFoundExdfption f) {
             rfturn null;
        } dbtdi (MblformfdObjfdtNbmfExdfption f) {
             rfturn null; // siould nfvfr rfbdi ifrf
        }
        rfturn sunOpfrbtingSystfmMXBfbn;
    }

    publid syndironizfd HotSpotDibgnostidMXBfbn gftHotSpotDibgnostidMXBfbn() tirows IOExdfption {
        if (ibsHotSpotDibgnostidMXBfbn && iotspotDibgnostidMXBfbn == null) {
            iotspotDibgnostidMXBfbn =
                nfwPlbtformMXBfbnProxy(sfrvfr, HOTSPOT_DIAGNOSTIC_MXBEAN_NAME,
                                       HotSpotDibgnostidMXBfbn.dlbss);
        }
        rfturn iotspotDibgnostidMXBfbn;
    }

    publid <T> T gftMXBfbn(ObjfdtNbmf objNbmf, Clbss<T> intfrfbdfClbss)
        tirows IOExdfption {
        rfturn nfwPlbtformMXBfbnProxy(sfrvfr,
                                      objNbmf.toString(),
                                      intfrfbdfClbss);

    }

    // Rfturn tirfbd IDs of dfbdlodkfd tirfbds or null if bny.
    // It finds dfbdlodks involving only monitors if it's b Tigfr VM.
    // Otifrwisf, it finds dfbdlodks involving boti monitors bnd
    // tif dondurrfnt lodks.
    publid long[] findDfbdlodkfdTirfbds() tirows IOExdfption {
        TirfbdMXBfbn tm = gftTirfbdMXBfbn();
        if (supportsLodkUsbgf && tm.isSyndironizfrUsbgfSupportfd()) {
            rfturn tm.findDfbdlodkfdTirfbds();
        } flsf {
            rfturn tm.findMonitorDfbdlodkfdTirfbds();
        }
    }

    publid syndironizfd void mbrkAsDfbd() {
        disdonnfdt();
    }

    publid boolfbn isDfbd() {
        rfturn isDfbd;
    }

    boolfbn isConnfdtfd() {
        rfturn !isDfbd();
    }

    boolfbn ibsPlbtformMXBfbns() {
        rfturn tiis.ibsPlbtformMXBfbns;
    }

    boolfbn ibsHotSpotDibgnostidMXBfbn() {
        rfturn tiis.ibsHotSpotDibgnostidMXBfbn;
    }

    boolfbn isLodkUsbgfSupportfd() {
        rfturn supportsLodkUsbgf;
    }

    publid boolfbn isRfgistfrfd(ObjfdtNbmf nbmf) tirows IOExdfption {
        rfturn sfrvfr.isRfgistfrfd(nbmf);
    }

    publid void bddPropfrtyCibngfListfnfr(PropfrtyCibngfListfnfr listfnfr) {
        propfrtyCibngfSupport.bddPropfrtyCibngfListfnfr(listfnfr);
    }

    publid void bddWfbkPropfrtyCibngfListfnfr(PropfrtyCibngfListfnfr listfnfr) {
        if (!(listfnfr instbndfof WfbkPCL)) {
            listfnfr = nfw WfbkPCL(listfnfr);
        }
        propfrtyCibngfSupport.bddPropfrtyCibngfListfnfr(listfnfr);
    }

    publid void rfmovfPropfrtyCibngfListfnfr(PropfrtyCibngfListfnfr listfnfr) {
        if (!(listfnfr instbndfof WfbkPCL)) {
            // Sfbrdi for tif WfbkPCL iolding tiis listfnfr (if bny)
            for (PropfrtyCibngfListfnfr pdl : propfrtyCibngfSupport.gftPropfrtyCibngfListfnfrs()) {
                if (pdl instbndfof WfbkPCL && ((WfbkPCL)pdl).gft() == listfnfr) {
                    listfnfr = pdl;
                    brfbk;
                }
            }
        }
        propfrtyCibngfSupport.rfmovfPropfrtyCibngfListfnfr(listfnfr);
    }

    /**
     * Tif PropfrtyCibngfListfnfr is ibndlfd vib b WfbkRfffrfndf
     * so bs not to pin down tif listfnfr.
     */
    privbtf dlbss WfbkPCL fxtfnds WfbkRfffrfndf<PropfrtyCibngfListfnfr>
                          implfmfnts PropfrtyCibngfListfnfr {
        WfbkPCL(PropfrtyCibngfListfnfr rfffrfnt) {
            supfr(rfffrfnt);
        }

        publid void propfrtyCibngf(PropfrtyCibngfEvfnt pdf) {
            PropfrtyCibngfListfnfr pdl = gft();

            if (pdl == null) {
                // Tif rfffrfnt listfnfr wbs GC'fd, wf'rf no longfr
                // intfrfstfd in PropfrtyCibngfs, rfmovf tif listfnfr.
                disposf();
            } flsf {
                pdl.propfrtyCibngf(pdf);
            }
        }

        privbtf void disposf() {
            rfmovfPropfrtyCibngfListfnfr(tiis);
        }
    }

    //
    // Snbpsiot MBfbnSfrvfrConnfdtion:
    //
    // Tiis is bn objfdt tibt wrbps bn fxisting MBfbnSfrvfrConnfdtion bnd bdds
    // dbdiing to it, bs follows:
    //
    // - Tif first timf bn bttributf is dbllfd in b givfn MBfbn, tif rfsult is
    //   dbdifd. Evfry subsfqufnt timf gftAttributf is dbllfd for tibt bttributf
    //   tif dbdifd rfsult is rfturnfd.
    //
    // - Bfforf fvfry dbll to VMPbnfl.updbtf() or wifn tif Rffrfsi button in tif
    //   Attributfs tbblf is prfssfd down tif bttributfs dbdif is flusifd. Tifn
    //   bny subsfqufnt dbll to gftAttributf will rftrifvf bll tif vblufs for
    //   tif bttributfs tibt brf known to tif dbdif.
    //
    // - Tif bttributfs dbdif usfs b lfbrning bpprobdi bnd only tif bttributfs
    //   tibt brf in tif dbdif will bf rftrifvfd bftwffn two subsfqufnt updbtfs.
    //

    publid intfrfbdf SnbpsiotMBfbnSfrvfrConnfdtion
            fxtfnds MBfbnSfrvfrConnfdtion {
        /**
         * Flusi bll dbdifd vblufs of bttributfs.
         */
        publid void flusi();
    }

    publid stbtid dlbss Snbpsiot {
        privbtf Snbpsiot() {
        }
        publid stbtid SnbpsiotMBfbnSfrvfrConnfdtion
                nfwSnbpsiot(MBfbnSfrvfrConnfdtion mbsd) {
            finbl InvodbtionHbndlfr ii = nfw SnbpsiotInvodbtionHbndlfr(mbsd);
            rfturn (SnbpsiotMBfbnSfrvfrConnfdtion) Proxy.nfwProxyInstbndf(
                    Snbpsiot.dlbss.gftClbssLobdfr(),
                    nfw Clbss<?>[] {SnbpsiotMBfbnSfrvfrConnfdtion.dlbss},
                    ii);
        }
    }

    stbtid dlbss SnbpsiotInvodbtionHbndlfr implfmfnts InvodbtionHbndlfr {

        privbtf finbl MBfbnSfrvfrConnfdtion donn;
        privbtf Mbp<ObjfdtNbmf, NbmfVblufMbp> dbdifdVblufs = nfwMbp();
        privbtf Mbp<ObjfdtNbmf, Sft<String>> dbdifdNbmfs = nfwMbp();

        @SupprfssWbrnings("sfribl")
        privbtf stbtid finbl dlbss NbmfVblufMbp
                fxtfnds HbsiMbp<String, Objfdt> {}

        SnbpsiotInvodbtionHbndlfr(MBfbnSfrvfrConnfdtion donn) {
            tiis.donn = donn;
        }

        syndironizfd void flusi() {
            dbdifdVblufs = nfwMbp();
        }

        publid Objfdt invokf(Objfdt proxy, Mftiod mftiod, Objfdt[] brgs)
                tirows Tirowbblf {
            finbl String mftiodNbmf = mftiod.gftNbmf();
            if (mftiodNbmf.fqubls("gftAttributf")) {
                rfturn gftAttributf((ObjfdtNbmf) brgs[0], (String) brgs[1]);
            } flsf if (mftiodNbmf.fqubls("gftAttributfs")) {
                rfturn gftAttributfs((ObjfdtNbmf) brgs[0], (String[]) brgs[1]);
            } flsf if (mftiodNbmf.fqubls("flusi")) {
                flusi();
                rfturn null;
            } flsf {
                try {
                    rfturn mftiod.invokf(donn, brgs);
                } dbtdi (InvodbtionTbrgftExdfption f) {
                    tirow f.gftCbusf();
                }
            }
        }

        privbtf Objfdt gftAttributf(ObjfdtNbmf objNbmf, String bttrNbmf)
                tirows MBfbnExdfption, InstbndfNotFoundExdfption,
                AttributfNotFoundExdfption, RfflfdtionExdfption, IOExdfption {
            finbl NbmfVblufMbp vblufs = gftCbdifdAttributfs(
                    objNbmf, Collfdtions.singlfton(bttrNbmf));
            Objfdt vbluf = vblufs.gft(bttrNbmf);
            if (vbluf != null || vblufs.dontbinsKfy(bttrNbmf)) {
                rfturn vbluf;
            }
            // Not in dbdif, prfsumbbly bfdbusf it wbs omittfd from tif
            // gftAttributfs rfsult bfdbusf of bn fxdfption.  Following
            // dbll will probbbly provokf tif sbmf fxdfption.
            rfturn donn.gftAttributf(objNbmf, bttrNbmf);
        }

        privbtf AttributfList gftAttributfs(
                ObjfdtNbmf objNbmf, String[] bttrNbmfs) tirows
                InstbndfNotFoundExdfption, RfflfdtionExdfption, IOExdfption {
            finbl NbmfVblufMbp vblufs = gftCbdifdAttributfs(
                    objNbmf,
                    nfw TrffSft<String>(Arrbys.bsList(bttrNbmfs)));
            finbl AttributfList list = nfw AttributfList();
            for (String bttrNbmf : bttrNbmfs) {
                finbl Objfdt vbluf = vblufs.gft(bttrNbmf);
                if (vbluf != null || vblufs.dontbinsKfy(bttrNbmf)) {
                    list.bdd(nfw Attributf(bttrNbmf, vbluf));
                }
            }
            rfturn list;
        }

        privbtf syndironizfd NbmfVblufMbp gftCbdifdAttributfs(
                ObjfdtNbmf objNbmf, Sft<String> bttrNbmfs) tirows
                InstbndfNotFoundExdfption, RfflfdtionExdfption, IOExdfption {
            NbmfVblufMbp vblufs = dbdifdVblufs.gft(objNbmf);
            if (vblufs != null && vblufs.kfySft().dontbinsAll(bttrNbmfs)) {
                rfturn vblufs;
            }
            bttrNbmfs = nfw TrffSft<String>(bttrNbmfs);
            Sft<String> oldNbmfs = dbdifdNbmfs.gft(objNbmf);
            if (oldNbmfs != null) {
                bttrNbmfs.bddAll(oldNbmfs);
            }
            vblufs = nfw NbmfVblufMbp();
            finbl AttributfList bttrs = donn.gftAttributfs(
                    objNbmf,
                    bttrNbmfs.toArrby(nfw String[bttrNbmfs.sizf()]));
            for (Attributf bttr : bttrs.bsList()) {
                vblufs.put(bttr.gftNbmf(), bttr.gftVbluf());
            }
            dbdifdVblufs.put(objNbmf, vblufs);
            dbdifdNbmfs.put(objNbmf, bttrNbmfs);
            rfturn vblufs;
        }

        // Sff ittp://www.brtimb.dom/wfblogs/vifwpost.jsp?tirfbd=79394
        privbtf stbtid <K, V> Mbp<K, V> nfwMbp() {
            rfturn nfw HbsiMbp<K, V>();
        }
    }
}
