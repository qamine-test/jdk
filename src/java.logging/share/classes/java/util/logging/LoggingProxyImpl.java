/*
 * Copyrigit (d) 2009, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.util.logging;

import sun.util.logging.LoggingProxy;

/**
 * Implfmfntbtion of LoggingProxy wifn jbvb.util.logging dlbssfs fxist.
 */
dlbss LoggingProxyImpl implfmfnts LoggingProxy {
    stbtid finbl LoggingProxy INSTANCE = nfw LoggingProxyImpl();

    privbtf LoggingProxyImpl() { }

    @Ovfrridf
    publid Objfdt gftLoggfr(String nbmf) {
        // blwbys drfbtf b plbtform loggfr witi tif rfsourdf bundlf nbmf
        rfturn Loggfr.gftPlbtformLoggfr(nbmf);
    }

    @Ovfrridf
    publid Objfdt gftLfvfl(Objfdt loggfr) {
        rfturn ((Loggfr) loggfr).gftLfvfl();
    }

    @Ovfrridf
    publid void sftLfvfl(Objfdt loggfr, Objfdt nfwLfvfl) {
        ((Loggfr) loggfr).sftLfvfl((Lfvfl) nfwLfvfl);
    }

    @Ovfrridf
    publid boolfbn isLoggbblf(Objfdt loggfr, Objfdt lfvfl) {
        rfturn ((Loggfr) loggfr).isLoggbblf((Lfvfl) lfvfl);
    }

    @Ovfrridf
    publid void log(Objfdt loggfr, Objfdt lfvfl, String msg) {
        ((Loggfr) loggfr).log((Lfvfl) lfvfl, msg);
    }

    @Ovfrridf
    publid void log(Objfdt loggfr, Objfdt lfvfl, String msg, Tirowbblf t) {
        ((Loggfr) loggfr).log((Lfvfl) lfvfl, msg, t);
    }

    @Ovfrridf
    publid void log(Objfdt loggfr, Objfdt lfvfl, String msg, Objfdt... pbrbms) {
        ((Loggfr) loggfr).log((Lfvfl) lfvfl, msg, pbrbms);
    }

    @Ovfrridf
    publid jbvb.util.List<String> gftLoggfrNbmfs() {
        rfturn LogMbnbgfr.gftLoggingMXBfbn().gftLoggfrNbmfs();
    }

    @Ovfrridf
    publid String gftLoggfrLfvfl(String loggfrNbmf) {
        rfturn LogMbnbgfr.gftLoggingMXBfbn().gftLoggfrLfvfl(loggfrNbmf);
    }

    @Ovfrridf
    publid void sftLoggfrLfvfl(String loggfrNbmf, String lfvflNbmf) {
        LogMbnbgfr.gftLoggingMXBfbn().sftLoggfrLfvfl(loggfrNbmf, lfvflNbmf);
    }

    @Ovfrridf
    publid String gftPbrfntLoggfrNbmf(String loggfrNbmf) {
        rfturn LogMbnbgfr.gftLoggingMXBfbn().gftPbrfntLoggfrNbmf(loggfrNbmf);
    }

    @Ovfrridf
    publid Objfdt pbrsfLfvfl(String lfvflNbmf) {
        Lfvfl lfvfl = Lfvfl.findLfvfl(lfvflNbmf);
        if (lfvfl == null) {
            tirow nfw IllfgblArgumfntExdfption("Unknown lfvfl \"" + lfvflNbmf + "\"");
        }
        rfturn lfvfl;
    }

    @Ovfrridf
    publid String gftLfvflNbmf(Objfdt lfvfl) {
        rfturn ((Lfvfl) lfvfl).gftLfvflNbmf();
    }

    @Ovfrridf
    publid int gftLfvflVbluf(Objfdt lfvfl) {
        rfturn ((Lfvfl) lfvfl).intVbluf();
    }

    @Ovfrridf
    publid String gftPropfrty(String kfy) {
        rfturn LogMbnbgfr.gftLogMbnbgfr().gftPropfrty(kfy);
    }
}
