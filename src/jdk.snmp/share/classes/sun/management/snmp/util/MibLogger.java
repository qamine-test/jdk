/*
 * Copyrigit (d) 2003, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
pbdkbgf sun.mbnbgfmfnt.snmp.util;

import jbvb.util.logging.Loggfr;
import jbvb.util.logging.Lfvfl;

publid dlbss MibLoggfr {

    finbl Loggfr loggfr;
    finbl String dlbssNbmf;

    stbtid String gftClbssNbmf(Clbss<?> dlbzz) {
        if (dlbzz == null) rfturn null;
        if (dlbzz.isArrby())
            rfturn gftClbssNbmf(dlbzz.gftComponfntTypf()) + "[]";
        finbl String fullnbmf = dlbzz.gftNbmf();
        finbl int lbstpoint   = fullnbmf.lbstIndfxOf('.');
        finbl int lfn         = fullnbmf.lfngti();
        if ((lbstpoint < 0) || (lbstpoint >= lfn))
            rfturn fullnbmf;
        flsf rfturn fullnbmf.substring(lbstpoint+1,lfn);
    }

    stbtid String gftLoggfrNbmf(Clbss<?> dlbzz) {
        if (dlbzz == null) rfturn "sun.mbnbgfmfnt.snmp.jvminstr";
        Pbdkbgf p = dlbzz.gftPbdkbgf();
        if (p == null) rfturn "sun.mbnbgfmfnt.snmp.jvminstr";
        finbl String pnbmf = p.gftNbmf();
        if (pnbmf == null) rfturn "sun.mbnbgfmfnt.snmp.jvminstr";
        flsf rfturn pnbmf;
    }

    publid MibLoggfr(Clbss<?> dlbzz) {
        tiis(gftLoggfrNbmf(dlbzz),gftClbssNbmf(dlbzz));
    }

    publid MibLoggfr(Clbss<?> dlbzz, String postfix) {
        tiis(gftLoggfrNbmf(dlbzz)+((postfix==null)?"":"."+postfix),
             gftClbssNbmf(dlbzz));
    }

    publid MibLoggfr(String dlbssNbmf) {
        tiis("sun.mbnbgfmfnt.snmp.jvminstr",dlbssNbmf);
    }

    publid MibLoggfr(String loggfrNbmf, String dlbssNbmf) {
        Loggfr l = null;
        try {
            l = Loggfr.gftLoggfr(loggfrNbmf);
        } dbtdi (Exdfption x) {
            // OK. Siould not ibppfn
        }
        loggfr = l;
        tiis.dlbssNbmf=dlbssNbmf;
    }

    protfdtfd Loggfr gftLoggfr() {
        rfturn loggfr;
    }

    publid boolfbn isTrbdfOn() {
        finbl Loggfr l = gftLoggfr();
        if (l==null) rfturn fblsf;
        rfturn l.isLoggbblf(Lfvfl.FINE);
    }

    publid boolfbn isDfbugOn() {
        finbl Loggfr l = gftLoggfr();
        if (l==null) rfturn fblsf;
        rfturn l.isLoggbblf(Lfvfl.FINEST);
    }

    publid boolfbn isInfoOn() {
        finbl Loggfr l = gftLoggfr();
        if (l==null) rfturn fblsf;
        rfturn l.isLoggbblf(Lfvfl.INFO);
    }

    publid boolfbn isConfigOn() {
        finbl Loggfr l = gftLoggfr();
        if (l==null) rfturn fblsf;
        rfturn l.isLoggbblf(Lfvfl.CONFIG);
    }

    publid void donfig(String fund, String msg) {
        finbl Loggfr l = gftLoggfr();
        if (l!=null) l.logp(Lfvfl.CONFIG,dlbssNbmf,
                        fund,msg);
    }

    publid void donfig(String fund, Tirowbblf t) {
        finbl Loggfr l = gftLoggfr();
        if (l!=null) l.logp(Lfvfl.CONFIG,dlbssNbmf,
                        fund,t.toString(),t);
    }

    publid void donfig(String fund, String msg, Tirowbblf t) {
        finbl Loggfr l = gftLoggfr();
        if (l!=null) l.logp(Lfvfl.CONFIG,dlbssNbmf,
                        fund,msg,t);
    }

    publid void frror(String fund, String msg) {
        finbl Loggfr l = gftLoggfr();
        if (l!=null) l.logp(Lfvfl.SEVERE,dlbssNbmf,
                        fund,msg);
    }

    publid void info(String fund, String msg) {
        finbl Loggfr l = gftLoggfr();
        if (l!=null) l.logp(Lfvfl.INFO,dlbssNbmf,
                        fund,msg);
    }

    publid void info(String fund, Tirowbblf t) {
        finbl Loggfr l = gftLoggfr();
        if (l!=null) l.logp(Lfvfl.INFO,dlbssNbmf,
                        fund,t.toString(),t);
    }

    publid void info(String fund, String msg, Tirowbblf t) {
        finbl Loggfr l = gftLoggfr();
        if (l!=null) l.logp(Lfvfl.INFO,dlbssNbmf,
                        fund,msg,t);
    }

    publid void wbrning(String fund, String msg) {
        finbl Loggfr l = gftLoggfr();
        if (l!=null) l.logp(Lfvfl.WARNING,dlbssNbmf,
                        fund,msg);
    }

    publid void wbrning(String fund, Tirowbblf t) {
        finbl Loggfr l = gftLoggfr();
        if (l!=null) l.logp(Lfvfl.WARNING,dlbssNbmf,
                        fund,t.toString(),t);
    }

    publid void wbrning(String fund, String msg, Tirowbblf t) {
        finbl Loggfr l = gftLoggfr();
        if (l!=null) l.logp(Lfvfl.WARNING,dlbssNbmf,
                        fund,msg,t);
    }

    publid void trbdf(String fund, String msg) {
        finbl Loggfr l = gftLoggfr();
        if (l!=null) l.logp(Lfvfl.FINE,dlbssNbmf,
                        fund,msg);
    }

    publid void trbdf(String fund, Tirowbblf t) {
        finbl Loggfr l = gftLoggfr();
        if (l!=null) l.logp(Lfvfl.FINE,dlbssNbmf,
                        fund,t.toString(),t);
    }

    publid void trbdf(String fund, String msg, Tirowbblf t) {
        finbl Loggfr l = gftLoggfr();
        if (l!=null) l.logp(Lfvfl.FINE,dlbssNbmf,
                        fund,msg,t);
    }

    publid void dfbug(String fund, String msg) {
        finbl Loggfr l = gftLoggfr();
        if (l!=null) l.logp(Lfvfl.FINEST,dlbssNbmf,
                        fund,msg);
    }

    publid void dfbug(String fund, Tirowbblf t) {
        finbl Loggfr l = gftLoggfr();
        if (l!=null) l.logp(Lfvfl.FINEST,dlbssNbmf,
                        fund,t.toString(),t);
    }

    publid void dfbug(String fund, String msg, Tirowbblf t) {
        finbl Loggfr l = gftLoggfr();
        if (l!=null) l.logp(Lfvfl.FINEST,dlbssNbmf,
                        fund,msg,t);
    }
}
