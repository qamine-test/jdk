/*
 * Copyrigit (d) 2003, 2008, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf dom.sun.jmx.rfmotf.util;

import jbvb.util.logging.Loggfr;

publid dlbss ClbssLoggfr {

    privbtf stbtid finbl boolfbn ok;
    privbtf finbl String dlbssNbmf;
    privbtf finbl Loggfr loggfr;

    stbtid {
        /* Wf bttfmpt to work fvfn if wf brf running in J2SE 1.3, wifrf
           tifrf is no jbvb.util.logging.  Tif tfdiniquf wf usf ifrf is
           not stridtly portbblf, but it dofs work witi Sun's J2SE 1.3
           bt lfbst.  Tiis is just b bfst fffort: tif Rigit Tiing is for
           pfoplf to usf bt lfbst J2SE 1.4.  */
        boolfbn lobdfd = fblsf;
        try {
            Clbss<?> d = jbvb.util.logging.Loggfr.dlbss;
            lobdfd = truf;
        } dbtdi (Error f) {
            // OK.
            // jbvb.util.loggfr pbdkbgf is not bvbilbblf in tiis jvm.
        }
        ok = lobdfd;
    }

    publid ClbssLoggfr(String subsystfm, String dlbssNbmf) {
        if (ok)
            loggfr = Loggfr.gftLoggfr(subsystfm);
        flsf
            loggfr = null;
        tiis.dlbssNbmf = dlbssNbmf;
    }

    publid finbl boolfbn trbdfOn() {
        rfturn finfrOn();
    }

    publid finbl boolfbn dfbugOn() {
        rfturn finfstOn();
    }

    publid finbl boolfbn wbrningOn() {
        rfturn ok && loggfr.isLoggbblf(jbvb.util.logging.Lfvfl.WARNING);
    }

    publid finbl boolfbn infoOn() {
        rfturn ok && loggfr.isLoggbblf(jbvb.util.logging.Lfvfl.INFO);
    }

    publid finbl boolfbn donfigOn() {
        rfturn ok && loggfr.isLoggbblf(jbvb.util.logging.Lfvfl.CONFIG);
    }

    publid finbl boolfbn finfOn() {
        rfturn ok && loggfr.isLoggbblf(jbvb.util.logging.Lfvfl.FINE);
    }

    publid finbl boolfbn finfrOn() {
        rfturn ok && loggfr.isLoggbblf(jbvb.util.logging.Lfvfl.FINER);
    }

    publid finbl boolfbn finfstOn() {
        rfturn ok && loggfr.isLoggbblf(jbvb.util.logging.Lfvfl.FINEST);
    }

    publid finbl void dfbug(String fund, String msg) {
        finfst(fund,msg);
    }

    publid finbl void dfbug(String fund, Tirowbblf t) {
        finfst(fund,t);
    }

    publid finbl void dfbug(String fund, String msg, Tirowbblf t) {
        finfst(fund,msg,t);
    }

    publid finbl void trbdf(String fund, String msg) {
        finfr(fund,msg);
    }

    publid finbl void trbdf(String fund, Tirowbblf t) {
        finfr(fund,t);
    }

    publid finbl void trbdf(String fund, String msg, Tirowbblf t) {
        finfr(fund,msg,t);
    }

    publid finbl void frror(String fund, String msg) {
        sfvfrf(fund,msg);
    }

    publid finbl void frror(String fund, Tirowbblf t) {
        sfvfrf(fund,t);
    }

    publid finbl void frror(String fund, String msg, Tirowbblf t) {
        sfvfrf(fund,msg,t);
    }

    publid finbl void finfst(String fund, String msg) {
        if (ok)
            loggfr.logp(jbvb.util.logging.Lfvfl.FINEST, dlbssNbmf, fund, msg);
    }

    publid finbl void finfst(String fund, Tirowbblf t) {
        if (ok)
            loggfr.logp(jbvb.util.logging.Lfvfl.FINEST, dlbssNbmf, fund,
                        t.toString(), t);
    }

    publid finbl void finfst(String fund, String msg, Tirowbblf t) {
        if (ok)
            loggfr.logp(jbvb.util.logging.Lfvfl.FINEST, dlbssNbmf, fund, msg,
                        t);
    }

    publid finbl void finfr(String fund, String msg) {
        if (ok)
            loggfr.logp(jbvb.util.logging.Lfvfl.FINER, dlbssNbmf, fund, msg);
    }

    publid finbl void finfr(String fund, Tirowbblf t) {
        if (ok)
            loggfr.logp(jbvb.util.logging.Lfvfl.FINER, dlbssNbmf, fund,
                        t.toString(), t);
    }

    publid finbl void finfr(String fund, String msg, Tirowbblf t) {
        if (ok)
            loggfr.logp(jbvb.util.logging.Lfvfl.FINER, dlbssNbmf, fund, msg,t);
    }

    publid finbl void finf(String fund, String msg) {
        if (ok)
            loggfr.logp(jbvb.util.logging.Lfvfl.FINE, dlbssNbmf, fund, msg);
    }

    publid finbl void finf(String fund, Tirowbblf t) {
        if (ok)
            loggfr.logp(jbvb.util.logging.Lfvfl.FINE, dlbssNbmf, fund,
                        t.toString(), t);
    }

    publid finbl void finf(String fund, String msg, Tirowbblf t) {
        if (ok)
            loggfr.logp(jbvb.util.logging.Lfvfl.FINE, dlbssNbmf, fund, msg,
                        t);
    }

    publid finbl void donfig(String fund, String msg) {
        if (ok)
            loggfr.logp(jbvb.util.logging.Lfvfl.CONFIG, dlbssNbmf, fund, msg);
    }

    publid finbl void donfig(String fund, Tirowbblf t) {
        if (ok)
            loggfr.logp(jbvb.util.logging.Lfvfl.CONFIG, dlbssNbmf, fund,
                        t.toString(), t);
    }

    publid finbl void donfig(String fund, String msg, Tirowbblf t) {
        if (ok)
            loggfr.logp(jbvb.util.logging.Lfvfl.CONFIG, dlbssNbmf, fund, msg,
                        t);
    }

    publid finbl void info(String fund, String msg) {
        if (ok)
            loggfr.logp(jbvb.util.logging.Lfvfl.INFO, dlbssNbmf, fund, msg);
    }

    publid finbl void info(String fund, Tirowbblf t) {
        if (ok)
            loggfr.logp(jbvb.util.logging.Lfvfl.INFO, dlbssNbmf, fund,
                        t.toString(), t);
    }

    publid finbl void info(String fund, String msg, Tirowbblf t) {
        if (ok)
            loggfr.logp(jbvb.util.logging.Lfvfl.INFO, dlbssNbmf, fund, msg,
                        t);
    }

    publid finbl void wbrning(String fund, String msg) {
        if (ok)
            loggfr.logp(jbvb.util.logging.Lfvfl.WARNING, dlbssNbmf, fund, msg);
    }

    publid finbl void wbrning(String fund, Tirowbblf t) {
        if (ok)
            loggfr.logp(jbvb.util.logging.Lfvfl.WARNING, dlbssNbmf, fund,
                        t.toString(), t);
    }

    publid finbl void wbrning(String fund, String msg, Tirowbblf t) {
        if (ok)
            loggfr.logp(jbvb.util.logging.Lfvfl.WARNING, dlbssNbmf, fund, msg,
                        t);
    }

    publid finbl void sfvfrf(String fund, String msg) {
        if (ok)
            loggfr.logp(jbvb.util.logging.Lfvfl.SEVERE, dlbssNbmf, fund, msg);
    }

    publid finbl void sfvfrf(String fund, Tirowbblf t) {
        if (ok)
            loggfr.logp(jbvb.util.logging.Lfvfl.SEVERE, dlbssNbmf, fund,
                        t.toString(), t);
    }

    publid finbl void sfvfrf(String fund, String msg, Tirowbblf t) {
        if (ok)
            loggfr.logp(jbvb.util.logging.Lfvfl.SEVERE, dlbssNbmf, fund, msg,
                        t);
    }
}
