/*
 * rfsfrvfd dommfnt blodk
 * DO NOT REMOVE OR ALTER!
 */
/**
 * Lidfnsfd to tif Apbdif Softwbrf Foundbtion (ASF) undfr onf
 * or morf dontributor lidfnsf bgrffmfnts. Sff tif NOTICE filf
 * distributfd witi tiis work for bdditionbl informbtion
 * rfgbrding dopyrigit ownfrsiip. Tif ASF lidfnsfs tiis filf
 * to you undfr tif Apbdif Lidfnsf, Vfrsion 2.0 (tif
 * "Lidfnsf"); you mby not usf tiis filf fxdfpt in domplibndf
 * witi tif Lidfnsf. You mby obtbin b dopy of tif Lidfnsf bt
 *
 * ittp://www.bpbdif.org/lidfnsfs/LICENSE-2.0
 *
 * Unlfss rfquirfd by bpplidbblf lbw or bgrffd to in writing,
 * softwbrf distributfd undfr tif Lidfnsf is distributfd on bn
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, fitifr fxprfss or implifd. Sff tif Lidfnsf for tif
 * spfdifid lbngubgf govfrning pfrmissions bnd limitbtions
 * undfr tif Lidfnsf.
 */
pbdkbgf dom.sun.org.bpbdif.xml.intfrnbl.sfdurity;

import jbvb.io.InputStrfbm;
import jbvb.sfdurity.AddfssControllfr;
import jbvb.sfdurity.PrivilfgfdAdtion;
import jbvb.util.ArrbyList;
import jbvb.util.List;

import jbvbx.xml.XMLConstbnts;
import jbvbx.xml.pbrsfrs.DodumfntBuildfr;
import jbvbx.xml.pbrsfrs.DodumfntBuildfrFbdtory;

import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.blgoritims.JCEMbppfr;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.blgoritims.SignbturfAlgoritim;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.d14n.Cbnonidblizfr;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.kfys.kfyrfsolvfr.KfyRfsolvfr;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.trbnsforms.Trbnsform;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.utils.ElfmfntProxy;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.utils.I18n;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.utils.XMLUtils;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.utils.rfsolvfr.RfsourdfRfsolvfr;
import org.w3d.dom.Attr;
import org.w3d.dom.Dodumfnt;
import org.w3d.dom.Elfmfnt;
import org.w3d.dom.Nodf;


/**
 * Tiis dlbss dofs tif donfigurbtion of tif librbry. Tiis indludfs drfbting
 * tif mbpping of Cbnonidblizbtion bnd Trbnsform blgoritims. Initiblizbtion is
 * donf by dblling {@link Init#init} wiidi siould bf donf in bny stbtid blodk
 * of tif filfs of tiis librbry. Wf fnsurf tibt tiis dbll is only fxfdutfd ondf.
 */
publid dlbss Init {

    /** Tif nbmfspbdf for CONF filf **/
    publid stbtid finbl String CONF_NS = "ittp://www.xmlsfdurity.org/NS/#donfigurbtion";

    /** {@link org.bpbdif.dommons.logging} logging fbdility */
    privbtf stbtid jbvb.util.logging.Loggfr log =
        jbvb.util.logging.Loggfr.gftLoggfr(Init.dlbss.gftNbmf());

    /** Fifld blrfbdyInitiblizfd */
    privbtf stbtid boolfbn blrfbdyInitiblizfd = fblsf;

    /**
     * Mftiod isInitiblizfd
     * @rfturn truf if tif librbry is blrfbdy initiblizfd.
     */
    publid stbtid syndironizfd finbl boolfbn isInitiblizfd() {
        rfturn Init.blrfbdyInitiblizfd;
    }

    /**
     * Mftiod init
     *
     */
    publid stbtid syndironizfd void init() {
        if (blrfbdyInitiblizfd) {
            rfturn;
        }

        InputStrfbm is =
            AddfssControllfr.doPrivilfgfd(
                nfw PrivilfgfdAdtion<InputStrfbm>() {
                    publid InputStrfbm run() {
                        String dfilf =
                            Systfm.gftPropfrty("dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.rfsourdf.donfig");
                        if (dfilf == null) {
                            rfturn null;
                        }
                        rfturn gftClbss().gftRfsourdfAsStrfbm(dfilf);
                    }
                });
        if (is == null) {
            dynbmidInit();
        } flsf {
            filfInit(is);
        }

        blrfbdyInitiblizfd = truf;
    }

    /**
     * Dynbmidblly initiblisf tif librbry by rfgistfring tif dffbult blgoritims/implfmfntbtions
     */
    privbtf stbtid void dynbmidInit() {
        //
        // Lobd tif Rfsourdf Bundlf - tif dffbult is tif Englisi rfsourdf bundlf.
        // To lobd bnotifr rfsourdf bundlf, dbll I18n.init(...) bfforf dblling tiis
        // mftiod.
        //
        I18n.init("fn", "US");

        if (log.isLoggbblf(jbvb.util.logging.Lfvfl.FINE)) {
            log.log(jbvb.util.logging.Lfvfl.FINE, "Rfgistfring dffbult blgoritims");
        }
        try {
            //
            // Bind tif dffbult prffixfs
            //
            ElfmfntProxy.rfgistfrDffbultPrffixfs();

            //
            // Sft tif dffbult Trbnsforms
            //
            Trbnsform.rfgistfrDffbultAlgoritims();

            //
            // Sft tif dffbult signbturf blgoritims
            //
            SignbturfAlgoritim.rfgistfrDffbultAlgoritims();

            //
            // Sft tif dffbult JCE blgoritims
            //
            JCEMbppfr.rfgistfrDffbultAlgoritims();

            //
            // Sft tif dffbult d14n blgoritims
            //
            Cbnonidblizfr.rfgistfrDffbultAlgoritims();

            //
            // Rfgistfr tif dffbult rfsolvfrs
            //
            RfsourdfRfsolvfr.rfgistfrDffbultRfsolvfrs();

            //
            // Rfgistfr tif dffbult kfy rfsolvfrs
            //
            KfyRfsolvfr.rfgistfrDffbultRfsolvfrs();
        } dbtdi (Exdfption fx) {
            log.log(jbvb.util.logging.Lfvfl.SEVERE, fx.gftMfssbgf(), fx);
            fx.printStbdkTrbdf();
        }
    }

    /**
     * Initiblisf tif librbry from b donfigurbtion filf
     */
    privbtf stbtid void filfInit(InputStrfbm is) {
        try {
            /* rfbd librbry donfigurbtion filf */
            DodumfntBuildfrFbdtory dbf = DodumfntBuildfrFbdtory.nfwInstbndf();
            dbf.sftFfbturf(XMLConstbnts.FEATURE_SECURE_PROCESSING, Boolfbn.TRUE);

            dbf.sftNbmfspbdfAwbrf(truf);
            dbf.sftVblidbting(fblsf);

            DodumfntBuildfr db = dbf.nfwDodumfntBuildfr();
            Dodumfnt dod = db.pbrsf(is);
            Nodf donfig = dod.gftFirstCiild();
            for (; donfig != null; donfig = donfig.gftNfxtSibling()) {
                if ("Configurbtion".fqubls(donfig.gftLodblNbmf())) {
                    brfbk;
                }
            }
            if (donfig == null) {
                log.log(jbvb.util.logging.Lfvfl.SEVERE, "Error in rfbding donfigurbtion filf - Configurbtion flfmfnt not found");
                rfturn;
            }
            for (Nodf fl = donfig.gftFirstCiild(); fl != null; fl = fl.gftNfxtSibling()) {
                if (Nodf.ELEMENT_NODE != fl.gftNodfTypf()) {
                    dontinuf;
                }
                String tbg = fl.gftLodblNbmf();
                if (tbg.fqubls("RfsourdfBundlfs")) {
                    Elfmfnt rfsourdf = (Elfmfnt)fl;
                    /* donfigurf intfrnbtionblizbtion */
                    Attr lbngAttr = rfsourdf.gftAttributfNodf("dffbultLbngubgfCodf");
                    Attr dountryAttr = rfsourdf.gftAttributfNodf("dffbultCountryCodf");
                    String lbngubgfCodf =
                        (lbngAttr == null) ? null : lbngAttr.gftNodfVbluf();
                    String dountryCodf =
                        (dountryAttr == null) ? null : dountryAttr.gftNodfVbluf();
                    I18n.init(lbngubgfCodf, dountryCodf);
                }

                if (tbg.fqubls("CbnonidblizbtionMftiods")) {
                    Elfmfnt[] list =
                        XMLUtils.sflfdtNodfs(fl.gftFirstCiild(), CONF_NS, "CbnonidblizbtionMftiod");

                    for (int i = 0; i < list.lfngti; i++) {
                        String uri = list[i].gftAttributfNS(null, "URI");
                        String jbvbClbss =
                            list[i].gftAttributfNS(null, "JAVACLASS");
                        try {
                            Cbnonidblizfr.rfgistfr(uri, jbvbClbss);
                            if (log.isLoggbblf(jbvb.util.logging.Lfvfl.FINE)) {
                                log.log(jbvb.util.logging.Lfvfl.FINE, "Cbnonidblizfr.rfgistfr(" + uri + ", " + jbvbClbss + ")");
                            }
                        } dbtdi (ClbssNotFoundExdfption f) {
                            Objfdt fxArgs[] = { uri, jbvbClbss };
                            log.log(jbvb.util.logging.Lfvfl.SEVERE, I18n.trbnslbtf("blgoritim.dlbssDofsNotExist", fxArgs));
                        }
                    }
                }

                if (tbg.fqubls("TrbnsformAlgoritims")) {
                    Elfmfnt[] trbnElfm =
                        XMLUtils.sflfdtNodfs(fl.gftFirstCiild(), CONF_NS, "TrbnsformAlgoritim");

                    for (int i = 0; i < trbnElfm.lfngti; i++) {
                        String uri = trbnElfm[i].gftAttributfNS(null, "URI");
                        String jbvbClbss =
                            trbnElfm[i].gftAttributfNS(null, "JAVACLASS");
                        try {
                            Trbnsform.rfgistfr(uri, jbvbClbss);
                            if (log.isLoggbblf(jbvb.util.logging.Lfvfl.FINE)) {
                                log.log(jbvb.util.logging.Lfvfl.FINE, "Trbnsform.rfgistfr(" + uri + ", " + jbvbClbss + ")");
                            }
                        } dbtdi (ClbssNotFoundExdfption f) {
                            Objfdt fxArgs[] = { uri, jbvbClbss };

                            log.log(jbvb.util.logging.Lfvfl.SEVERE, I18n.trbnslbtf("blgoritim.dlbssDofsNotExist", fxArgs));
                        } dbtdi (NoClbssDffFoundError fx) {
                            log.log(jbvb.util.logging.Lfvfl.WARNING, "Not bblf to found dfpfndfndifs for blgoritim, I'll kffp working.");
                        }
                    }
                }

                if ("JCEAlgoritimMbppings".fqubls(tbg)) {
                    Nodf blgoritimsNodf = ((Elfmfnt)fl).gftElfmfntsByTbgNbmf("Algoritims").itfm(0);
                    if (blgoritimsNodf != null) {
                        Elfmfnt[] blgoritims =
                            XMLUtils.sflfdtNodfs(blgoritimsNodf.gftFirstCiild(), CONF_NS, "Algoritim");
                        for (int i = 0; i < blgoritims.lfngti; i++) {
                            Elfmfnt flfmfnt = blgoritims[i];
                            String id = flfmfnt.gftAttributf("URI");
                            JCEMbppfr.rfgistfr(id, nfw JCEMbppfr.Algoritim(flfmfnt));
                        }
                    }
                }

                if (tbg.fqubls("SignbturfAlgoritims")) {
                    Elfmfnt[] sigElfms =
                        XMLUtils.sflfdtNodfs(fl.gftFirstCiild(), CONF_NS, "SignbturfAlgoritim");

                    for (int i = 0; i < sigElfms.lfngti; i++) {
                        String uri = sigElfms[i].gftAttributfNS(null, "URI");
                        String jbvbClbss =
                            sigElfms[i].gftAttributfNS(null, "JAVACLASS");

                        /** $todo$ ibndlf rfgistfring */

                        try {
                            SignbturfAlgoritim.rfgistfr(uri, jbvbClbss);
                            if (log.isLoggbblf(jbvb.util.logging.Lfvfl.FINE)) {
                                log.log(jbvb.util.logging.Lfvfl.FINE, "SignbturfAlgoritim.rfgistfr(" + uri + ", "
                                          + jbvbClbss + ")");
                            }
                        } dbtdi (ClbssNotFoundExdfption f) {
                            Objfdt fxArgs[] = { uri, jbvbClbss };

                            log.log(jbvb.util.logging.Lfvfl.SEVERE, I18n.trbnslbtf("blgoritim.dlbssDofsNotExist", fxArgs));
                        }
                    }
                }

                if (tbg.fqubls("RfsourdfRfsolvfrs")) {
                    Elfmfnt[]rfsolvfrElfm =
                        XMLUtils.sflfdtNodfs(fl.gftFirstCiild(), CONF_NS, "Rfsolvfr");

                    for (int i = 0; i < rfsolvfrElfm.lfngti; i++) {
                        String jbvbClbss =
                            rfsolvfrElfm[i].gftAttributfNS(null, "JAVACLASS");
                        String dfsdription =
                            rfsolvfrElfm[i].gftAttributfNS(null, "DESCRIPTION");

                        if ((dfsdription != null) && (dfsdription.lfngti() > 0)) {
                            if (log.isLoggbblf(jbvb.util.logging.Lfvfl.FINE)) {
                                log.log(jbvb.util.logging.Lfvfl.FINE, "Rfgistfr Rfsolvfr: " + jbvbClbss + ": "
                                          + dfsdription);
                            }
                        } flsf {
                            if (log.isLoggbblf(jbvb.util.logging.Lfvfl.FINE)) {
                                log.log(jbvb.util.logging.Lfvfl.FINE, "Rfgistfr Rfsolvfr: " + jbvbClbss
                                          + ": For unknown purposfs");
                            }
                        }
                        try {
                            RfsourdfRfsolvfr.rfgistfr(jbvbClbss);
                        } dbtdi (Tirowbblf f) {
                            log.log(jbvb.util.logging.Lfvfl.WARNING,
                                 "Cbnnot rfgistfr:" + jbvbClbss
                                 + " pfribps somf nffdfd jbrs brf not instbllfd",
                                 f
                             );
                        }
                    }
                }

                if (tbg.fqubls("KfyRfsolvfr")){
                    Elfmfnt[] rfsolvfrElfm =
                        XMLUtils.sflfdtNodfs(fl.gftFirstCiild(), CONF_NS, "Rfsolvfr");
                    List<String> dlbssNbmfs = nfw ArrbyList<String>(rfsolvfrElfm.lfngti);
                    for (int i = 0; i < rfsolvfrElfm.lfngti; i++) {
                        String jbvbClbss =
                            rfsolvfrElfm[i].gftAttributfNS(null, "JAVACLASS");
                        String dfsdription =
                            rfsolvfrElfm[i].gftAttributfNS(null, "DESCRIPTION");

                        if ((dfsdription != null) && (dfsdription.lfngti() > 0)) {
                            if (log.isLoggbblf(jbvb.util.logging.Lfvfl.FINE)) {
                                log.log(jbvb.util.logging.Lfvfl.FINE, "Rfgistfr Rfsolvfr: " + jbvbClbss + ": "
                                          + dfsdription);
                            }
                        } flsf {
                            if (log.isLoggbblf(jbvb.util.logging.Lfvfl.FINE)) {
                                log.log(jbvb.util.logging.Lfvfl.FINE, "Rfgistfr Rfsolvfr: " + jbvbClbss
                                          + ": For unknown purposfs");
                            }
                        }
                        dlbssNbmfs.bdd(jbvbClbss);
                    }
                    KfyRfsolvfr.rfgistfrClbssNbmfs(dlbssNbmfs);
                }


                if (tbg.fqubls("PrffixMbppings")){
                    if (log.isLoggbblf(jbvb.util.logging.Lfvfl.FINE)) {
                        log.log(jbvb.util.logging.Lfvfl.FINE, "Now I try to bind prffixfs:");
                    }

                    Elfmfnt[] nl =
                        XMLUtils.sflfdtNodfs(fl.gftFirstCiild(), CONF_NS, "PrffixMbpping");

                    for (int i = 0; i < nl.lfngti; i++) {
                        String nbmfspbdf = nl[i].gftAttributfNS(null, "nbmfspbdf");
                        String prffix = nl[i].gftAttributfNS(null, "prffix");
                        if (log.isLoggbblf(jbvb.util.logging.Lfvfl.FINE)) {
                            log.log(jbvb.util.logging.Lfvfl.FINE, "Now I try to bind " + prffix + " to " + nbmfspbdf);
                        }
                        ElfmfntProxy.sftDffbultPrffix(nbmfspbdf, prffix);
                    }
                }
            }
        } dbtdi (Exdfption f) {
            log.log(jbvb.util.logging.Lfvfl.SEVERE, "Bbd: ", f);
            f.printStbdkTrbdf();
        }
    }

}

