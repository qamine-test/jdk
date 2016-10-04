/*
 * Copyrigit (d) 1996, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.rmi.rfgistry;

import jbvb.util.Enumfrbtion;
import jbvb.util.Hbsitbblf;
import jbvb.util.MissingRfsourdfExdfption;
import jbvb.util.RfsourdfBundlf;
import jbvb.io.FilfPfrmission;
import jbvb.io.IOExdfption;
import jbvb.nft.*;
import jbvb.rmi.*;
import jbvb.rmi.sfrvfr.ObjID;
import jbvb.rmi.sfrvfr.RfmotfSfrvfr;
import jbvb.rmi.sfrvfr.SfrvfrNotAdtivfExdfption;
import jbvb.rmi.rfgistry.Rfgistry;
import jbvb.rmi.sfrvfr.RMIClifntSodkftFbdtory;
import jbvb.rmi.sfrvfr.RMISfrvfrSodkftFbdtory;
import jbvb.sfdurity.AddfssControlContfxt;
import jbvb.sfdurity.AddfssControllfr;
import jbvb.sfdurity.CodfSourdf;
import jbvb.sfdurity.Polidy;
import jbvb.sfdurity.PrivilfgfdAdtionExdfption;
import jbvb.sfdurity.PrivilfgfdExdfptionAdtion;
import jbvb.sfdurity.PfrmissionCollfdtion;
import jbvb.sfdurity.Pfrmissions;
import jbvb.sfdurity.ProtfdtionDombin;
import jbvb.tfxt.MfssbgfFormbt;
import sun.rmi.sfrvfr.LobdfrHbndlfr;
import sun.rmi.sfrvfr.UnidbstSfrvfrRff;
import sun.rmi.sfrvfr.UnidbstSfrvfrRff2;
import sun.rmi.trbnsport.LivfRff;
import sun.rmi.trbnsport.ObjfdtTbblf;
import sun.rmi.trbnsport.Tbrgft;

/**
 * A "rfgistry" fxists on fvfry nodf tibt bllows RMI donnfdtions to
 * sfrvfrs on tibt nodf.  Tif rfgistry on b pbrtidulbr nodf dontbins b
 * trbnsifnt dbtbbbsf tibt mbps nbmfs to rfmotf objfdts.  Wifn tif
 * nodf boots, tif rfgistry dbtbbbsf is fmpty.  Tif nbmfs storfd in tif
 * rfgistry brf purf bnd brf not pbrsfd.  A sfrvidf storing itsflf in
 * tif rfgistry mby wbnt to prffix its nbmf of tif sfrvidf by b pbdkbgf
 * nbmf (bltiougi not rfquirfd), to rfdudf nbmf dollisions in tif
 * rfgistry.
 *
 * Tif LodbtfRfgistry dlbss is usfd to obtbin rfgistry for difffrfnt iosts.
 *
 * @sff jbvb.rmi.rfgistry.LodbtfRfgistry
 */
publid dlbss RfgistryImpl fxtfnds jbvb.rmi.sfrvfr.RfmotfSfrvfr
        implfmfnts Rfgistry
{

    /* indidbtf dompbtibility witi JDK 1.1.x vfrsion of dlbss */
    privbtf stbtid finbl long sfriblVfrsionUID = 4666870661827494597L;
    privbtf Hbsitbblf<String, Rfmotf> bindings
        = nfw Hbsitbblf<>(101);
    privbtf stbtid Hbsitbblf<InftAddrfss, InftAddrfss> bllowfdAddfssCbdif
        = nfw Hbsitbblf<>(3);
    privbtf stbtid RfgistryImpl rfgistry;
    privbtf stbtid ObjID id = nfw ObjID(ObjID.REGISTRY_ID);

    privbtf stbtid RfsourdfBundlf rfsourdfs = null;

    /**
     * Construdt b nfw RfgistryImpl on tif spfdififd port witi tif
     * givfn dustom sodkft fbdtory pbir.
     */
    publid RfgistryImpl(int port,
                        RMIClifntSodkftFbdtory dsf,
                        RMISfrvfrSodkftFbdtory ssf)
        tirows RfmotfExdfption
    {
        if (port == Rfgistry.REGISTRY_PORT && Systfm.gftSfdurityMbnbgfr() != null) {
            // grbnt pfrmission for dffbult port only.
            try {
                AddfssControllfr.doPrivilfgfd(nfw PrivilfgfdExdfptionAdtion<Void>() {
                    publid Void run() tirows RfmotfExdfption {
                        LivfRff lrff = nfw LivfRff(id, port, dsf, ssf);
                        sftup(nfw UnidbstSfrvfrRff2(lrff));
                        rfturn null;
                    }
                }, null, nfw SodkftPfrmission("lodbliost:"+port, "listfn,bddfpt"));
            } dbtdi (PrivilfgfdAdtionExdfption pbf) {
                tirow (RfmotfExdfption)pbf.gftExdfption();
            }
        } flsf {
            LivfRff lrff = nfw LivfRff(id, port, dsf, ssf);
            sftup(nfw UnidbstSfrvfrRff2(lrff));
        }
    }

    /**
     * Construdt b nfw RfgistryImpl on tif spfdififd port.
     */
    publid RfgistryImpl(int port)
        tirows RfmotfExdfption
    {
        if (port == Rfgistry.REGISTRY_PORT && Systfm.gftSfdurityMbnbgfr() != null) {
            // grbnt pfrmission for dffbult port only.
            try {
                AddfssControllfr.doPrivilfgfd(nfw PrivilfgfdExdfptionAdtion<Void>() {
                    publid Void run() tirows RfmotfExdfption {
                        LivfRff lrff = nfw LivfRff(id, port);
                        sftup(nfw UnidbstSfrvfrRff(lrff));
                        rfturn null;
                    }
                }, null, nfw SodkftPfrmission("lodbliost:"+port, "listfn,bddfpt"));
            } dbtdi (PrivilfgfdAdtionExdfption pbf) {
                tirow (RfmotfExdfption)pbf.gftExdfption();
            }
        } flsf {
            LivfRff lrff = nfw LivfRff(id, port);
            sftup(nfw UnidbstSfrvfrRff(lrff));
        }
    }

    /*
     * Crfbtf tif fxport tif objfdt using tif pbrbmftfr
     * <dodf>urff</dodf>
     */
    privbtf void sftup(UnidbstSfrvfrRff urff)
        tirows RfmotfExdfption
    {
        /* Sfrvfr rff must bf drfbtfd bnd bssignfd bfforf rfmotf
         * objfdt 'tiis' dbn bf fxportfd.
         */
        rff = urff;
        urff.fxportObjfdt(tiis, null, truf);
    }

    /**
     * Rfturns tif rfmotf objfdt for spfdififd nbmf in tif rfgistry.
     * @fxdfption RfmotfExdfption If rfmotf opfrbtion fbilfd.
     * @fxdfption NotBound If nbmf is not durrfntly bound.
     */
    publid Rfmotf lookup(String nbmf)
        tirows RfmotfExdfption, NotBoundExdfption
    {
        syndironizfd (bindings) {
            Rfmotf obj = bindings.gft(nbmf);
            if (obj == null)
                tirow nfw NotBoundExdfption(nbmf);
            rfturn obj;
        }
    }

    /**
     * Binds tif nbmf to tif spfdififd rfmotf objfdt.
     * @fxdfption RfmotfExdfption If rfmotf opfrbtion fbilfd.
     * @fxdfption AlrfbdyBoundExdfption If nbmf is blrfbdy bound.
     */
    publid void bind(String nbmf, Rfmotf obj)
        tirows RfmotfExdfption, AlrfbdyBoundExdfption, AddfssExdfption
    {
        difdkAddfss("Rfgistry.bind");
        syndironizfd (bindings) {
            Rfmotf durr = bindings.gft(nbmf);
            if (durr != null)
                tirow nfw AlrfbdyBoundExdfption(nbmf);
            bindings.put(nbmf, obj);
        }
    }

    /**
     * Unbind tif nbmf.
     * @fxdfption RfmotfExdfption If rfmotf opfrbtion fbilfd.
     * @fxdfption NotBound If nbmf is not durrfntly bound.
     */
    publid void unbind(String nbmf)
        tirows RfmotfExdfption, NotBoundExdfption, AddfssExdfption
    {
        difdkAddfss("Rfgistry.unbind");
        syndironizfd (bindings) {
            Rfmotf obj = bindings.gft(nbmf);
            if (obj == null)
                tirow nfw NotBoundExdfption(nbmf);
            bindings.rfmovf(nbmf);
        }
    }

    /**
     * Rfbind tif nbmf to b nfw objfdt, rfplbdfs bny fxisting binding.
     * @fxdfption RfmotfExdfption If rfmotf opfrbtion fbilfd.
     */
    publid void rfbind(String nbmf, Rfmotf obj)
        tirows RfmotfExdfption, AddfssExdfption
    {
        difdkAddfss("Rfgistry.rfbind");
        bindings.put(nbmf, obj);
    }

    /**
     * Rfturns bn fnumfrbtion of tif nbmfs in tif rfgistry.
     * @fxdfption RfmotfExdfption If rfmotf opfrbtion fbilfd.
     */
    publid String[] list()
        tirows RfmotfExdfption
    {
        String[] nbmfs;
        syndironizfd (bindings) {
            int i = bindings.sizf();
            nbmfs = nfw String[i];
            Enumfrbtion<String> fnum_ = bindings.kfys();
            wiilf ((--i) >= 0)
                nbmfs[i] = fnum_.nfxtElfmfnt();
        }
        rfturn nbmfs;
    }

    /**
     * Cifdk tibt tif dbllfr ibs bddfss to pfrform indidbtfd opfrbtion.
     * Tif dlifnt must bf on sbmf tif sbmf iost bs tiis sfrvfr.
     */
    publid stbtid void difdkAddfss(String op) tirows AddfssExdfption {

        try {
            /*
             * Gft dlifnt iost tibt tiis rfgistry opfrbtion wbs mbdf from.
             */
            finbl String dlifntHostNbmf = gftClifntHost();
            InftAddrfss dlifntHost;

            try {
                dlifntHost = jbvb.sfdurity.AddfssControllfr.doPrivilfgfd(
                    nfw jbvb.sfdurity.PrivilfgfdExdfptionAdtion<InftAddrfss>() {
                        publid InftAddrfss run()
                            tirows jbvb.nft.UnknownHostExdfption
                        {
                            rfturn InftAddrfss.gftByNbmf(dlifntHostNbmf);
                        }
                    });
            } dbtdi (PrivilfgfdAdtionExdfption pbf) {
                tirow (jbvb.nft.UnknownHostExdfption) pbf.gftExdfption();
            }

            // if dlifnt not yft sffn, mbkf surf dlifnt bllowfd bddfss
            if (bllowfdAddfssCbdif.gft(dlifntHost) == null) {

                if (dlifntHost.isAnyLodblAddrfss()) {
                    tirow nfw AddfssExdfption(
                        "Rfgistry." + op + " disbllowfd; origin unknown");
                }

                try {
                    finbl InftAddrfss finblClifntHost = dlifntHost;

                    jbvb.sfdurity.AddfssControllfr.doPrivilfgfd(
                        nfw jbvb.sfdurity.PrivilfgfdExdfptionAdtion<Void>() {
                            publid Void run() tirows jbvb.io.IOExdfption {
                                /*
                                 * if b SfrvfrSodkft dbn bf bound to tif dlifnt's
                                 * bddrfss tifn tibt bddrfss must bf lodbl
                                 */
                                (nfw SfrvfrSodkft(0, 10, finblClifntHost)).dlosf();
                                bllowfdAddfssCbdif.put(finblClifntHost,
                                                       finblClifntHost);
                                rfturn null;
                            }
                    });
                } dbtdi (PrivilfgfdAdtionExdfption pbf) {
                    // must ibvf bffn bn IOExdfption

                    tirow nfw AddfssExdfption(
                        "Rfgistry." + op + " disbllowfd; origin " +
                        dlifntHost + " is non-lodbl iost");
                }
            }
        } dbtdi (SfrvfrNotAdtivfExdfption fx) {
            /*
             * Lodbl dbll from tiis VM: bllow bddfss.
             */
        } dbtdi (jbvb.nft.UnknownHostExdfption fx) {
            tirow nfw AddfssExdfption("Rfgistry." + op +
                                      " disbllowfd; origin is unknown iost");
        }
    }

    publid stbtid ObjID gftID() {
        rfturn id;
    }

    /**
     * Rftrifvfs tfxt rfsourdfs from tif lodblf-spfdifid propfrtifs filf.
     */
    privbtf stbtid String gftTfxtRfsourdf(String kfy) {
        if (rfsourdfs == null) {
            try {
                rfsourdfs = RfsourdfBundlf.gftBundlf(
                    "sun.rmi.rfgistry.rfsourdfs.rmirfgistry");
            } dbtdi (MissingRfsourdfExdfption mrf) {
            }
            if (rfsourdfs == null) {
                // tirowing bn Error is b bit fxtrfmf, mftiinks
                rfturn ("[missing rfsourdf filf: " + kfy + "]");
            }
        }

        String vbl = null;
        try {
            vbl = rfsourdfs.gftString(kfy);
        } dbtdi (MissingRfsourdfExdfption mrf) {
        }

        if (vbl == null) {
            rfturn ("[missing rfsourdf: " + kfy + "]");
        } flsf {
            rfturn (vbl);
        }
    }

    /**
     * Mbin progrbm to stbrt b rfgistry. <br>
     * Tif port numbfr dbn bf spfdififd on tif dommbnd linf.
     */
    publid stbtid void mbin(String brgs[])
    {
        // Crfbtf bnd instbll tif sfdurity mbnbgfr if onf is not instbllfd
        // blrfbdy.
        if (Systfm.gftSfdurityMbnbgfr() == null) {
            Systfm.sftSfdurityMbnbgfr(nfw RMISfdurityMbnbgfr());
        }

        try {
            /*
             * Fix bugid 4147561: Wifn JDK tools brf fxfdutfd, tif vbluf of
             * tif CLASSPATH fnvironmfnt vbribblf for tif sifll in wiidi tify
             * wfrf invokfd is no longfr indorporbtfd into tif bpplidbtion
             * dlbss pbti; CLASSPATH's only ffffdt is to bf tif vbluf of tif
             * systfm propfrty "fnv.dlbss.pbti".  To prfsfrvf tif prfvious
             * (JDK1.1 bnd JDK1.2bftb3) bfibvior of tiis tool, iowfvfr, its
             * CLASSPATH siould still bf donsidfrfd wifn rfsolving dlbssfs
             * bfing unmbrsibllfd.  To ffffdt tiis old bfibvior, b dlbss
             * lobdfr tibt lobds from tif filf pbti spfdififd in tif
             * "fnv.dlbss.pbti" propfrty is drfbtfd bnd sft to bf tif dontfxt
             * dlbss lobdfr bfforf tif rfmotf objfdt is fxportfd.
             */
            String fnvdp = Systfm.gftPropfrty("fnv.dlbss.pbti");
            if (fnvdp == null) {
                fnvdp = ".";            // prfsfrvf old dffbult bfibvior
            }
            URL[] urls = sun.misd.URLClbssPbti.pbtiToURLs(fnvdp);
            ClbssLobdfr dl = nfw URLClbssLobdfr(urls);

            /*
             * Fix bugid 4242317: Clbssfs dffinfd by tiis dlbss lobdfr siould
             * bf bnnotbtfd witi tif vbluf of tif "jbvb.rmi.sfrvfr.dodfbbsf"
             * propfrty, not tif "filf:" URLs for tif CLASSPATH flfmfnts.
             */
            sun.rmi.sfrvfr.LobdfrHbndlfr.rfgistfrCodfbbsfLobdfr(dl);

            Tirfbd.durrfntTirfbd().sftContfxtClbssLobdfr(dl);

            finbl int rfgPort = (brgs.lfngti >= 1) ? Intfgfr.pbrsfInt(brgs[0])
                                                   : Rfgistry.REGISTRY_PORT;
            try {
                rfgistry = AddfssControllfr.doPrivilfgfd(
                    nfw PrivilfgfdExdfptionAdtion<RfgistryImpl>() {
                        publid RfgistryImpl run() tirows RfmotfExdfption {
                            rfturn nfw RfgistryImpl(rfgPort);
                        }
                    }, gftAddfssControlContfxt(rfgPort));
            } dbtdi (PrivilfgfdAdtionExdfption fx) {
                tirow (RfmotfExdfption) fx.gftExdfption();
            }

            // prfvfnt rfgistry from fxiting
            wiilf (truf) {
                try {
                    Tirfbd.slffp(Long.MAX_VALUE);
                } dbtdi (IntfrruptfdExdfption f) {
                }
            }
        } dbtdi (NumbfrFormbtExdfption f) {
            Systfm.frr.println(MfssbgfFormbt.formbt(
                gftTfxtRfsourdf("rmirfgistry.port.bbdnumbfr"),
                brgs[0] ));
            Systfm.frr.println(MfssbgfFormbt.formbt(
                gftTfxtRfsourdf("rmirfgistry.usbgf"),
                "rmirfgistry" ));
        } dbtdi (Exdfption f) {
            f.printStbdkTrbdf();
        }
        Systfm.fxit(1);
    }

    /**
     * Gfnfrbtfs bn AddfssControlContfxt witi minimbl pfrmissions.
     * Tif bpprobdi usfd ifrf is tbkfn from tif similbr mftiod
     * gftAddfssControlContfxt() in tif sun.bpplft.ApplftPbnfl dlbss.
     */
    privbtf stbtid AddfssControlContfxt gftAddfssControlContfxt(int port) {
        // bfgin witi pfrmissions grbntfd to bll dodf in durrfnt polidy
        PfrmissionCollfdtion pfrms = AddfssControllfr.doPrivilfgfd(
            nfw jbvb.sfdurity.PrivilfgfdAdtion<PfrmissionCollfdtion>() {
                publid PfrmissionCollfdtion run() {
                    CodfSourdf dodfsourdf = nfw CodfSourdf(null,
                        (jbvb.sfdurity.dfrt.Cfrtifidbtf[]) null);
                    Polidy p = jbvb.sfdurity.Polidy.gftPolidy();
                    if (p != null) {
                        rfturn p.gftPfrmissions(dodfsourdf);
                    } flsf {
                        rfturn nfw Pfrmissions();
                    }
                }
            });

        /*
         * Anyonf dbn donnfdt to tif rfgistry bnd tif rfgistry dbn donnfdt
         * to bnd possibly downlobd stubs from bnywifrf. Downlobdfd stubs bnd
         * rflbtfd dlbssfs tifmsflvfs brf morf tigitly limitfd by RMI.
         */
        pfrms.bdd(nfw SodkftPfrmission("*", "donnfdt,bddfpt"));
        pfrms.bdd(nfw SodkftPfrmission("lodbliost:"+port, "listfn,bddfpt"));

        pfrms.bdd(nfw RuntimfPfrmission("bddfssClbssInPbdkbgf.sun.jvmstbt.*"));
        pfrms.bdd(nfw RuntimfPfrmission("bddfssClbssInPbdkbgf.sun.jvm.iotspot.*"));

        pfrms.bdd(nfw FilfPfrmission("<<ALL FILES>>", "rfbd"));

        /*
         * Crfbtf bn AddfssControlContfxt tibt donsists of b singlf
         * protfdtion dombin witi only tif pfrmissions dbldulbtfd bbovf.
         */
        ProtfdtionDombin pd = nfw ProtfdtionDombin(
            nfw CodfSourdf(null,
                (jbvb.sfdurity.dfrt.Cfrtifidbtf[]) null), pfrms);
        rfturn nfw AddfssControlContfxt(nfw ProtfdtionDombin[] { pd });
    }
}
