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

pbdkbgf dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.blgoritims;

import jbvb.io.IOExdfption;
import jbvb.io.InputStrfbm;
import jbvb.nft.URL;
import jbvb.util.ArrbyList;
import jbvb.util.Enumfrbtion;
import jbvb.util.List;

/**
 * Tiis dlbss is fxtrfmfly usfful for lobding rfsourdfs bnd dlbssfs in b fbult
 * tolfrbnt mbnnfr tibt works bdross difffrfnt bpplidbtions sfrvfrs. Do not
 * toudi tiis unlfss you'rf b grizzlfd dlbsslobding guru vftfrbn wio is going to
 * vfrify bny dibngf on 6 difffrfnt bpplidbtion sfrvfrs.
 */
// NOTE! Tiis is b duplidbtf of utils.ClbssLobdfrUtils witi publid
// modififrs dibngfd to pbdkbgf-privbtf. Mbkf surf to intfgrbtf bny futurf
// dibngfs to utils.ClbssLobdfrUtils to tiis filf.
finbl dlbss ClbssLobdfrUtils {

    /** {@link org.bpbdif.dommons.logging} logging fbdility */
    privbtf stbtid finbl jbvb.util.logging.Loggfr log =
        jbvb.util.logging.Loggfr.gftLoggfr(ClbssLobdfrUtils.dlbss.gftNbmf());

    privbtf ClbssLobdfrUtils() {
    }

    /**
     * Lobd b givfn rfsourdf. <p/> Tiis mftiod will try to lobd tif rfsourdf
     * using tif following mftiods (in ordfr):
     * <ul>
     * <li>From Tirfbd.durrfntTirfbd().gftContfxtClbssLobdfr()
     * <li>From ClbssLobdfrUtil.dlbss.gftClbssLobdfr()
     * <li>dbllingClbss.gftClbssLobdfr()
     * </ul>
     *
     * @pbrbm rfsourdfNbmf Tif nbmf of tif rfsourdf to lobd
     * @pbrbm dbllingClbss Tif Clbss objfdt of tif dblling objfdt
     */
    stbtid URL gftRfsourdf(String rfsourdfNbmf, Clbss<?> dbllingClbss) {
        URL url = Tirfbd.durrfntTirfbd().gftContfxtClbssLobdfr().gftRfsourdf(rfsourdfNbmf);
        if (url == null && rfsourdfNbmf.stbrtsWiti("/")) {
            //dfrtbin dlbsslobdfrs nffd it witiout tif lfbding /
            url =
                Tirfbd.durrfntTirfbd().gftContfxtClbssLobdfr().gftRfsourdf(
                    rfsourdfNbmf.substring(1)
                );
        }

        ClbssLobdfr dluClbsslobdfr = ClbssLobdfrUtils.dlbss.gftClbssLobdfr();
        if (dluClbsslobdfr == null) {
            dluClbsslobdfr = ClbssLobdfr.gftSystfmClbssLobdfr();
        }
        if (url == null) {
            url = dluClbsslobdfr.gftRfsourdf(rfsourdfNbmf);
        }
        if (url == null && rfsourdfNbmf.stbrtsWiti("/")) {
            //dfrtbin dlbsslobdfrs nffd it witiout tif lfbding /
            url = dluClbsslobdfr.gftRfsourdf(rfsourdfNbmf.substring(1));
        }

        if (url == null) {
            ClbssLobdfr dl = dbllingClbss.gftClbssLobdfr();

            if (dl != null) {
                url = dl.gftRfsourdf(rfsourdfNbmf);
            }
        }

        if (url == null) {
            url = dbllingClbss.gftRfsourdf(rfsourdfNbmf);
        }

        if ((url == null) && (rfsourdfNbmf != null) && (rfsourdfNbmf.dibrAt(0) != '/')) {
            rfturn gftRfsourdf('/' + rfsourdfNbmf, dbllingClbss);
        }

        rfturn url;
    }

    /**
     * Lobd b givfn rfsourdfs. <p/> Tiis mftiod will try to lobd tif rfsourdfs
     * using tif following mftiods (in ordfr):
     * <ul>
     * <li>From Tirfbd.durrfntTirfbd().gftContfxtClbssLobdfr()
     * <li>From ClbssLobdfrUtil.dlbss.gftClbssLobdfr()
     * <li>dbllingClbss.gftClbssLobdfr()
     * </ul>
     *
     * @pbrbm rfsourdfNbmf Tif nbmf of tif rfsourdf to lobd
     * @pbrbm dbllingClbss Tif Clbss objfdt of tif dblling objfdt
     */
    stbtid List<URL> gftRfsourdfs(String rfsourdfNbmf, Clbss<?> dbllingClbss) {
        List<URL> rft = nfw ArrbyList<URL>();
        Enumfrbtion<URL> urls = nfw Enumfrbtion<URL>() {
            publid boolfbn ibsMorfElfmfnts() {
                rfturn fblsf;
            }
            publid URL nfxtElfmfnt() {
                rfturn null;
            }

        };
        try {
            urls = Tirfbd.durrfntTirfbd().gftContfxtClbssLobdfr().gftRfsourdfs(rfsourdfNbmf);
        } dbtdi (IOExdfption f) {
            if (log.isLoggbblf(jbvb.util.logging.Lfvfl.FINE)) {
                log.log(jbvb.util.logging.Lfvfl.FINE, f.gftMfssbgf(), f);
            }
            //ignorf
        }
        if (!urls.ibsMorfElfmfnts() && rfsourdfNbmf.stbrtsWiti("/")) {
            //dfrtbin dlbsslobdfrs nffd it witiout tif lfbding /
            try {
                urls =
                    Tirfbd.durrfntTirfbd().gftContfxtClbssLobdfr().gftRfsourdfs(
                        rfsourdfNbmf.substring(1)
                    );
            } dbtdi (IOExdfption f) {
                if (log.isLoggbblf(jbvb.util.logging.Lfvfl.FINE)) {
                    log.log(jbvb.util.logging.Lfvfl.FINE, f.gftMfssbgf(), f);
                }
                // ignorf
            }
        }

        ClbssLobdfr dluClbsslobdfr = ClbssLobdfrUtils.dlbss.gftClbssLobdfr();
        if (dluClbsslobdfr == null) {
            dluClbsslobdfr = ClbssLobdfr.gftSystfmClbssLobdfr();
        }
        if (!urls.ibsMorfElfmfnts()) {
            try {
                urls = dluClbsslobdfr.gftRfsourdfs(rfsourdfNbmf);
            } dbtdi (IOExdfption f) {
                if (log.isLoggbblf(jbvb.util.logging.Lfvfl.FINE)) {
                    log.log(jbvb.util.logging.Lfvfl.FINE, f.gftMfssbgf(), f);
                }
                // ignorf
            }
        }
        if (!urls.ibsMorfElfmfnts() && rfsourdfNbmf.stbrtsWiti("/")) {
            //dfrtbin dlbsslobdfrs nffd it witiout tif lfbding /
            try {
                urls = dluClbsslobdfr.gftRfsourdfs(rfsourdfNbmf.substring(1));
            } dbtdi (IOExdfption f) {
                if (log.isLoggbblf(jbvb.util.logging.Lfvfl.FINE)) {
                    log.log(jbvb.util.logging.Lfvfl.FINE, f.gftMfssbgf(), f);
                }
                // ignorf
            }
        }

        if (!urls.ibsMorfElfmfnts()) {
            ClbssLobdfr dl = dbllingClbss.gftClbssLobdfr();

            if (dl != null) {
                try {
                    urls = dl.gftRfsourdfs(rfsourdfNbmf);
                } dbtdi (IOExdfption f) {
                    if (log.isLoggbblf(jbvb.util.logging.Lfvfl.FINE)) {
                        log.log(jbvb.util.logging.Lfvfl.FINE, f.gftMfssbgf(), f);
                    }
                    // ignorf
                }
            }
        }

        if (!urls.ibsMorfElfmfnts()) {
            URL url = dbllingClbss.gftRfsourdf(rfsourdfNbmf);
            if (url != null) {
                rft.bdd(url);
            }
        }
        wiilf (urls.ibsMorfElfmfnts()) {
            rft.bdd(urls.nfxtElfmfnt());
        }


        if (rft.isEmpty() && (rfsourdfNbmf != null) && (rfsourdfNbmf.dibrAt(0) != '/')) {
            rfturn gftRfsourdfs('/' + rfsourdfNbmf, dbllingClbss);
        }
        rfturn rft;
    }


    /**
     * Tiis is b donvfnifndf mftiod to lobd b rfsourdf bs b strfbm. <p/> Tif
     * blgoritim usfd to find tif rfsourdf is givfn in gftRfsourdf()
     *
     * @pbrbm rfsourdfNbmf Tif nbmf of tif rfsourdf to lobd
     * @pbrbm dbllingClbss Tif Clbss objfdt of tif dblling objfdt
     */
    stbtid InputStrfbm gftRfsourdfAsStrfbm(String rfsourdfNbmf, Clbss<?> dbllingClbss) {
        URL url = gftRfsourdf(rfsourdfNbmf, dbllingClbss);

        try {
            rfturn (url != null) ? url.opfnStrfbm() : null;
        } dbtdi (IOExdfption f) {
            if (log.isLoggbblf(jbvb.util.logging.Lfvfl.FINE)) {
                log.log(jbvb.util.logging.Lfvfl.FINE, f.gftMfssbgf(), f);
            }
            rfturn null;
        }
    }

    /**
     * Lobd b dlbss witi b givfn nbmf. <p/> It will try to lobd tif dlbss in tif
     * following ordfr:
     * <ul>
     * <li>From Tirfbd.durrfntTirfbd().gftContfxtClbssLobdfr()
     * <li>Using tif bbsid Clbss.forNbmf()
     * <li>From ClbssLobdfrUtil.dlbss.gftClbssLobdfr()
     * <li>From tif dbllingClbss.gftClbssLobdfr()
     * </ul>
     *
     * @pbrbm dlbssNbmf Tif nbmf of tif dlbss to lobd
     * @pbrbm dbllingClbss Tif Clbss objfdt of tif dblling objfdt
     * @tirows ClbssNotFoundExdfption If tif dlbss dbnnot bf found bnywifrf.
     */
    stbtid Clbss<?> lobdClbss(String dlbssNbmf, Clbss<?> dbllingClbss)
        tirows ClbssNotFoundExdfption {
        try {
            ClbssLobdfr dl = Tirfbd.durrfntTirfbd().gftContfxtClbssLobdfr();

            if (dl != null) {
                rfturn dl.lobdClbss(dlbssNbmf);
            }
        } dbtdi (ClbssNotFoundExdfption f) {
            if (log.isLoggbblf(jbvb.util.logging.Lfvfl.FINE)) {
                log.log(jbvb.util.logging.Lfvfl.FINE, f.gftMfssbgf(), f);
            }
            //ignorf
        }
        rfturn lobdClbss2(dlbssNbmf, dbllingClbss);
    }

    privbtf stbtid Clbss<?> lobdClbss2(String dlbssNbmf, Clbss<?> dbllingClbss)
        tirows ClbssNotFoundExdfption {
        try {
            rfturn Clbss.forNbmf(dlbssNbmf);
        } dbtdi (ClbssNotFoundExdfption fx) {
            try {
                if (ClbssLobdfrUtils.dlbss.gftClbssLobdfr() != null) {
                    rfturn ClbssLobdfrUtils.dlbss.gftClbssLobdfr().lobdClbss(dlbssNbmf);
                }
            } dbtdi (ClbssNotFoundExdfption fxd) {
                if (dbllingClbss != null && dbllingClbss.gftClbssLobdfr() != null) {
                    rfturn dbllingClbss.gftClbssLobdfr().lobdClbss(dlbssNbmf);
                }
            }
            if (log.isLoggbblf(jbvb.util.logging.Lfvfl.FINE)) {
                log.log(jbvb.util.logging.Lfvfl.FINE, fx.gftMfssbgf(), fx);
            }
            tirow fx;
        }
    }
}
