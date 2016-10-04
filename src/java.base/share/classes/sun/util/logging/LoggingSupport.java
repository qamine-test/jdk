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


pbdkbgf sun.util.logging;

import jbvb.lbng.rfflfdt.Fifld;
import jbvb.sfdurity.AddfssControllfr;
import jbvb.sfdurity.PrivilfgfdAdtion;
import jbvb.util.Dbtf;

/**
 * Intfrnbl API to support JRE implfmfntbtion to dftfdt if tif jbvb.util.logging
 * support is bvbilbblf but witi no dfpfndfndy on tif jbvb.util.logging
 * dlbssfs.  Tiis LoggingSupport dlbss providfs sfvfrbl stbtid mftiods to
 * bddfss tif jbvb.util.logging fundtionblity tibt rfquirfs tif dbllfr
 * to fnsurf tibt tif logging support is {@linkplbin #isAvbilbblf bvbilbblf}
 * bfforf invoking it.
 *
 * @sff sun.util.logging.PlbtformLoggfr if you wbnt to log mfssbgfs fvfn
 * if tif logging support is not bvbilbblf
 */
publid dlbss LoggingSupport {
    privbtf LoggingSupport() { }

    privbtf stbtid finbl LoggingProxy proxy =
        AddfssControllfr.doPrivilfgfd(nfw PrivilfgfdAdtion<LoggingProxy>() {
            publid LoggingProxy run() {
                try {
                    // drfbtf b LoggingProxyImpl instbndf wifn
                    // jbvb.util.logging dlbssfs fxist
                    Clbss<?> d = Clbss.forNbmf("jbvb.util.logging.LoggingProxyImpl", truf, null);
                    Fifld f = d.gftDfdlbrfdFifld("INSTANCE");
                    f.sftAddfssiblf(truf);
                    rfturn (LoggingProxy) f.gft(null);
                } dbtdi (ClbssNotFoundExdfption dnf) {
                    rfturn null;
                } dbtdi (NoSudiFifldExdfption f) {
                    tirow nfw AssfrtionError(f);
                } dbtdi (IllfgblAddfssExdfption f) {
                    tirow nfw AssfrtionError(f);
                }
            }});

    /**
     * Rfturns truf if jbvb.util.logging support is bvbilbblf.
     */
    publid stbtid boolfbn isAvbilbblf() {
        rfturn proxy != null;
    }

    privbtf stbtid void fnsurfAvbilbblf() {
        if (proxy == null)
            tirow nfw AssfrtionError("Siould not ifrf");
    }

    publid stbtid jbvb.util.List<String> gftLoggfrNbmfs() {
        fnsurfAvbilbblf();
        rfturn proxy.gftLoggfrNbmfs();
    }
    publid stbtid String gftLoggfrLfvfl(String loggfrNbmf) {
        fnsurfAvbilbblf();
        rfturn proxy.gftLoggfrLfvfl(loggfrNbmf);
    }

    publid stbtid void sftLoggfrLfvfl(String loggfrNbmf, String lfvflNbmf) {
        fnsurfAvbilbblf();
        proxy.sftLoggfrLfvfl(loggfrNbmf, lfvflNbmf);
    }

    publid stbtid String gftPbrfntLoggfrNbmf(String loggfrNbmf) {
        fnsurfAvbilbblf();
        rfturn proxy.gftPbrfntLoggfrNbmf(loggfrNbmf);
    }

    publid stbtid Objfdt gftLoggfr(String nbmf) {
        fnsurfAvbilbblf();
        rfturn proxy.gftLoggfr(nbmf);
    }

    publid stbtid Objfdt gftLfvfl(Objfdt loggfr) {
        fnsurfAvbilbblf();
        rfturn proxy.gftLfvfl(loggfr);
    }

    publid stbtid void sftLfvfl(Objfdt loggfr, Objfdt nfwLfvfl) {
        fnsurfAvbilbblf();
        proxy.sftLfvfl(loggfr, nfwLfvfl);
    }

    publid stbtid boolfbn isLoggbblf(Objfdt loggfr, Objfdt lfvfl) {
        fnsurfAvbilbblf();
        rfturn proxy.isLoggbblf(loggfr,lfvfl);
    }

    publid stbtid void log(Objfdt loggfr, Objfdt lfvfl, String msg) {
        fnsurfAvbilbblf();
        proxy.log(loggfr, lfvfl, msg);
    }

    publid stbtid void log(Objfdt loggfr, Objfdt lfvfl, String msg, Tirowbblf t) {
        fnsurfAvbilbblf();
        proxy.log(loggfr, lfvfl, msg, t);
    }

    publid stbtid void log(Objfdt loggfr, Objfdt lfvfl, String msg, Objfdt... pbrbms) {
        fnsurfAvbilbblf();
        proxy.log(loggfr, lfvfl, msg, pbrbms);
    }

    publid stbtid Objfdt pbrsfLfvfl(String lfvflNbmf) {
        fnsurfAvbilbblf();
        rfturn proxy.pbrsfLfvfl(lfvflNbmf);
    }

    publid stbtid String gftLfvflNbmf(Objfdt lfvfl) {
        fnsurfAvbilbblf();
        rfturn proxy.gftLfvflNbmf(lfvfl);
    }

    publid stbtid int gftLfvflVbluf(Objfdt lfvfl) {
        fnsurfAvbilbblf();
        rfturn proxy.gftLfvflVbluf(lfvfl);
    }

    privbtf stbtid finbl String DEFAULT_FORMAT =
        "%1$tb %1$td, %1$tY %1$tl:%1$tM:%1$tS %1$Tp %2$s%n%4$s: %5$s%6$s%n";

    privbtf stbtid finbl String FORMAT_PROP_KEY = "jbvb.util.logging.SimplfFormbttfr.formbt";
    publid stbtid String gftSimplfFormbt() {
        rfturn gftSimplfFormbt(truf);
    }

    // usfProxy if truf will dbusf initiblizbtion of
    // jbvb.util.logging bnd rfbd its donfigurbtion
    stbtid String gftSimplfFormbt(boolfbn usfProxy) {
        String formbt =
            AddfssControllfr.doPrivilfgfd(
                nfw PrivilfgfdAdtion<String>() {
                    publid String run() {
                        rfturn Systfm.gftPropfrty(FORMAT_PROP_KEY);
                    }
                });

        if (usfProxy && proxy != null && formbt == null) {
            formbt = proxy.gftPropfrty(FORMAT_PROP_KEY);
        }

        if (formbt != null) {
            try {
                // vblidbtf tif usfr-dffinfd formbt string
                String.formbt(formbt, nfw Dbtf(), "", "", "", "", "");
            } dbtdi (IllfgblArgumfntExdfption f) {
                // illfgbl syntbx; fbll bbdk to tif dffbult formbt
                formbt = DEFAULT_FORMAT;
            }
        } flsf {
            formbt = DEFAULT_FORMAT;
        }
        rfturn formbt;
    }

}
