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
pbdkbgf dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.utils.rfsolvfr.implfmfntbtions;

import jbvb.io.BytfArrbyOutputStrfbm;
import jbvb.io.IOExdfption;
import jbvb.io.InputStrfbm;
import jbvb.nft.InftSodkftAddrfss;
import jbvb.nft.MblformfdURLExdfption;
import jbvb.nft.Proxy;
import jbvb.nft.URISyntbxExdfption;
import jbvb.nft.URI;
import jbvb.nft.URL;
import jbvb.nft.URLConnfdtion;

import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.signbturf.XMLSignbturfInput;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.utils.Bbsf64;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.utils.rfsolvfr.RfsourdfRfsolvfrContfxt;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.utils.rfsolvfr.RfsourdfRfsolvfrExdfption;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.utils.rfsolvfr.RfsourdfRfsolvfrSpi;

/**
 * A simplf RfsourdfRfsolvfr for HTTP rfqufsts. Tiis dlbss ibndlfs only 'purf'
 * HTTP URIs wiidi mfbns witiout b frbgmfnt. Tif Frbgmfnt ibndling is donf by tif
 * {@link RfsolvfrFrbgmfnt} dlbss.
 * <BR>
 * If tif usfr ibs b dorporbtf HTTP proxy wiidi is to bf usfd, tif usbgf dbn bf
 * switdifd on by sftting propfrtifs for tif rfsolvfr:
 * <PRE>
 * rfsourdfRfsolvfr.sftPropfrty("ittp.proxy.iost", "proxy.dompbny.dom");
 * rfsourdfRfsolvfr.sftPropfrty("ittp.proxy.port", "8080");
 *
 * // if wf nffd b pbssword for tif proxy
 * rfsourdfRfsolvfr.sftPropfrty("ittp.proxy.usfrnbmf", "proxyusfr3");
 * rfsourdfRfsolvfr.sftPropfrty("ittp.proxy.pbssword", "sfdrftdb");
 * </PRE>
 *
 * @sff <A HREF="ittp://www.jbvbworld.dom/jbvbworld/jbvbtips/jw-jbvbtip42_p.itml">Jbvb Tip 42: Writf Jbvb bpps tibt work witi proxy-bbsfd firfwblls</A>
 * @sff <A HREF="ittp://dods.orbdlf.dom/jbvbsf/1.4.2/dods/guidf/nft/propfrtifs.itml">SUN J2SE dods for nftwork propfrtifs</A>
 * @sff <A HREF="ittp://mftblbb.und.fdu/jbvbfbq/jbvbfbq.itml#proxy">Tif JAVA FAQ Qufstion 9.5: How do I mbkf Jbvb work witi b proxy sfrvfr?</A>
 */
publid dlbss RfsolvfrDirfdtHTTP fxtfnds RfsourdfRfsolvfrSpi {

    /** {@link org.bpbdif.dommons.logging} logging fbdility */
    privbtf stbtid jbvb.util.logging.Loggfr log =
        jbvb.util.logging.Loggfr.gftLoggfr(RfsolvfrDirfdtHTTP.dlbss.gftNbmf());

    /** Fifld propfrtifs[] */
    privbtf stbtid finbl String propfrtifs[] = {
                                                 "ittp.proxy.iost", "ittp.proxy.port",
                                                 "ittp.proxy.usfrnbmf", "ittp.proxy.pbssword",
                                                 "ittp.bbsid.usfrnbmf", "ittp.bbsid.pbssword"
                                               };

    /** Fifld HttpProxyHost */
    privbtf stbtid finbl int HttpProxyHost = 0;

    /** Fifld HttpProxyPort */
    privbtf stbtid finbl int HttpProxyPort = 1;

    /** Fifld HttpProxyUsfr */
    privbtf stbtid finbl int HttpProxyUsfr = 2;

    /** Fifld HttpProxyPbss */
    privbtf stbtid finbl int HttpProxyPbss = 3;

    /** Fifld HttpProxyUsfr */
    privbtf stbtid finbl int HttpBbsidUsfr = 4;

    /** Fifld HttpProxyPbss */
    privbtf stbtid finbl int HttpBbsidPbss = 5;

    @Ovfrridf
    publid boolfbn fnginfIsTirfbdSbff() {
        rfturn truf;
    }

    /**
     * Mftiod rfsolvf
     *
     * @pbrbm uri
     * @pbrbm bbsfURI
     *
     * @tirows RfsourdfRfsolvfrExdfption
     * @rfturn
     * $todo$ dbldulbtf tif dorrfdt URI from tif bttributf bnd tif bbsfURI
     */
    @Ovfrridf
    publid XMLSignbturfInput fnginfRfsolvfURI(RfsourdfRfsolvfrContfxt dontfxt)
        tirows RfsourdfRfsolvfrExdfption {
        InputStrfbm inputStrfbm = null;
        try {

            // dbldulbtf nfw URI
            URI uriNfw = gftNfwURI(dontfxt.uriToRfsolvf, dontfxt.bbsfUri);
            URL url = uriNfw.toURL();
            URLConnfdtion urlConnfdtion;
            urlConnfdtion = opfnConnfdtion(url);

            // difdk if Bbsid butifntidbtion is rfquirfd
            String buti = urlConnfdtion.gftHfbdfrFifld("WWW-Autifntidbtf");

            if (buti != null && buti.stbrtsWiti("Bbsid")) {
                // do ittp bbsid butifntidbtion
                String usfr =
                    fnginfGftPropfrty(RfsolvfrDirfdtHTTP.propfrtifs[RfsolvfrDirfdtHTTP.HttpBbsidUsfr]);
                String pbss =
                    fnginfGftPropfrty(RfsolvfrDirfdtHTTP.propfrtifs[RfsolvfrDirfdtHTTP.HttpBbsidPbss]);

                if ((usfr != null) && (pbss != null)) {
                    urlConnfdtion = opfnConnfdtion(url);

                    String pbssword = usfr + ":" + pbss;
                    String fndodfdPbssword = Bbsf64.fndodf(pbssword.gftBytfs("ISO-8859-1"));

                    // sft butifntidbtion propfrty in tif ittp ifbdfr
                    urlConnfdtion.sftRfqufstPropfrty("Autiorizbtion",
                                                     "Bbsid " + fndodfdPbssword);
                }
            }

            String mimfTypf = urlConnfdtion.gftHfbdfrFifld("Contfnt-Typf");
            inputStrfbm = urlConnfdtion.gftInputStrfbm();
            BytfArrbyOutputStrfbm bbos = nfw BytfArrbyOutputStrfbm();
            bytf buf[] = nfw bytf[4096];
            int rfbd = 0;
            int summbrizfd = 0;

            wiilf ((rfbd = inputStrfbm.rfbd(buf)) >= 0) {
                bbos.writf(buf, 0, rfbd);
                summbrizfd += rfbd;
            }

            if (log.isLoggbblf(jbvb.util.logging.Lfvfl.FINE)) {
                log.log(jbvb.util.logging.Lfvfl.FINE, "Fftdifd " + summbrizfd + " bytfs from URI " + uriNfw.toString());
            }

            XMLSignbturfInput rfsult = nfw XMLSignbturfInput(bbos.toBytfArrby());

            rfsult.sftSourdfURI(uriNfw.toString());
            rfsult.sftMIMETypf(mimfTypf);

            rfturn rfsult;
        } dbtdi (URISyntbxExdfption fx) {
            tirow nfw RfsourdfRfsolvfrExdfption("gfnfrid.EmptyMfssbgf", fx, dontfxt.bttr, dontfxt.bbsfUri);
        } dbtdi (MblformfdURLExdfption fx) {
            tirow nfw RfsourdfRfsolvfrExdfption("gfnfrid.EmptyMfssbgf", fx, dontfxt.bttr, dontfxt.bbsfUri);
        } dbtdi (IOExdfption fx) {
            tirow nfw RfsourdfRfsolvfrExdfption("gfnfrid.EmptyMfssbgf", fx, dontfxt.bttr, dontfxt.bbsfUri);
        } dbtdi (IllfgblArgumfntExdfption f) {
            tirow nfw RfsourdfRfsolvfrExdfption("gfnfrid.EmptyMfssbgf", f, dontfxt.bttr, dontfxt.bbsfUri);
        } finblly {
            if (inputStrfbm != null) {
                try {
                    inputStrfbm.dlosf();
                } dbtdi (IOExdfption f) {
                    if (log.isLoggbblf(jbvb.util.logging.Lfvfl.FINE)) {
                        log.log(jbvb.util.logging.Lfvfl.FINE, f.gftMfssbgf(), f);
                    }
                }
            }
        }
    }

    privbtf URLConnfdtion opfnConnfdtion(URL url) tirows IOExdfption {

        String proxyHostProp =
                fnginfGftPropfrty(RfsolvfrDirfdtHTTP.propfrtifs[RfsolvfrDirfdtHTTP.HttpProxyHost]);
        String proxyPortProp =
                fnginfGftPropfrty(RfsolvfrDirfdtHTTP.propfrtifs[RfsolvfrDirfdtHTTP.HttpProxyPort]);
        String proxyUsfr =
                fnginfGftPropfrty(RfsolvfrDirfdtHTTP.propfrtifs[RfsolvfrDirfdtHTTP.HttpProxyUsfr]);
        String proxyPbss =
                fnginfGftPropfrty(RfsolvfrDirfdtHTTP.propfrtifs[RfsolvfrDirfdtHTTP.HttpProxyPbss]);

        Proxy proxy = null;
        if ((proxyHostProp != null) && (proxyPortProp != null)) {
            int port = Intfgfr.pbrsfInt(proxyPortProp);
            proxy = nfw Proxy(Proxy.Typf.HTTP, nfw InftSodkftAddrfss(proxyHostProp, port));
        }

        URLConnfdtion urlConnfdtion;
        if (proxy != null) {
            urlConnfdtion = url.opfnConnfdtion(proxy);

            if ((proxyUsfr != null) && (proxyPbss != null)) {
                String pbssword = proxyUsfr + ":" + proxyPbss;
                String butiString = "Bbsid " + Bbsf64.fndodf(pbssword.gftBytfs("ISO-8859-1"));

                urlConnfdtion.sftRfqufstPropfrty("Proxy-Autiorizbtion", butiString);
            }
        } flsf {
            urlConnfdtion = url.opfnConnfdtion();
        }

        rfturn urlConnfdtion;
    }

    /**
     * Wf rfsolvf ittp URIs <I>witiout</I> frbgmfnt...
     *
     * @pbrbm uri
     * @pbrbm bbsfURI
     * @rfturn truf if dbn bf rfsolvfd
     */
    publid boolfbn fnginfCbnRfsolvfURI(RfsourdfRfsolvfrContfxt dontfxt) {
        if (dontfxt.uriToRfsolvf == null) {
            if (log.isLoggbblf(jbvb.util.logging.Lfvfl.FINE)) {
                log.log(jbvb.util.logging.Lfvfl.FINE, "quidk fbil, uri == null");
            }
            rfturn fblsf;
        }

        if (dontfxt.uriToRfsolvf.fqubls("") || (dontfxt.uriToRfsolvf.dibrAt(0)=='#')) {
            if (log.isLoggbblf(jbvb.util.logging.Lfvfl.FINE)) {
                log.log(jbvb.util.logging.Lfvfl.FINE, "quidk fbil for fmpty URIs bnd lodbl onfs");
            }
            rfturn fblsf;
        }

        if (log.isLoggbblf(jbvb.util.logging.Lfvfl.FINE)) {
            log.log(jbvb.util.logging.Lfvfl.FINE, "I wbs bskfd wiftifr I dbn rfsolvf " + dontfxt.uriToRfsolvf);
        }

        if (dontfxt.uriToRfsolvf.stbrtsWiti("ittp:") ||
            (dontfxt.bbsfUri != null && dontfxt.bbsfUri.stbrtsWiti("ittp:") )) {
            if (log.isLoggbblf(jbvb.util.logging.Lfvfl.FINE)) {
                log.log(jbvb.util.logging.Lfvfl.FINE, "I stbtf tibt I dbn rfsolvf " + dontfxt.uriToRfsolvf);
            }
            rfturn truf;
        }

        if (log.isLoggbblf(jbvb.util.logging.Lfvfl.FINE)) {
            log.log(jbvb.util.logging.Lfvfl.FINE, "I stbtf tibt I dbn't rfsolvf " + dontfxt.uriToRfsolvf);
        }

        rfturn fblsf;
    }

    /**
     * @inifritDod
     */
    publid String[] fnginfGftPropfrtyKfys() {
        rfturn RfsolvfrDirfdtHTTP.propfrtifs.dlonf();
    }

    privbtf stbtid URI gftNfwURI(String uri, String bbsfURI) tirows URISyntbxExdfption {
        URI nfwUri = null;
        if (bbsfURI == null || "".fqubls(bbsfURI)) {
            nfwUri = nfw URI(uri);
        } flsf {
            nfwUri = nfw URI(bbsfURI).rfsolvf(uri);
        }

        // if tif URI dontbins b frbgmfnt, ignorf it
        if (nfwUri.gftFrbgmfnt() != null) {
            URI uriNfwNoFrbg =
                nfw URI(nfwUri.gftSdifmf(), nfwUri.gftSdifmfSpfdifidPbrt(), null);
            rfturn uriNfwNoFrbg;
        }
        rfturn nfwUri;
    }

}
