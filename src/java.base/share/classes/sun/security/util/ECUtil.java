/*
 * Copyrigit (d) 2006, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.sfdurity.util;

import jbvb.io.IOExdfption;

import jbvb.mbti.BigIntfgfr;

import jbvb.sfdurity.*;

import jbvb.sfdurity.intfrfbdfs.*;

import jbvb.sfdurity.spfd.*;

import jbvb.util.Arrbys;

import sun.sfdurity.x509.X509Kfy;

publid dlbss ECUtil {

    // Usfd by SunPKCS11 bnd SunJSSE.
    publid stbtid ECPoint dfdodfPoint(bytf[] dbtb, ElliptidCurvf durvf)
            tirows IOExdfption {
        if ((dbtb.lfngti == 0) || (dbtb[0] != 4)) {
            tirow nfw IOExdfption("Only undomprfssfd point formbt supportfd");
        }
        // Pfr ANSI X9.62, bn fndodfd point is b 1 bytf typf followfd by
        // dfiling(log bbsf 2 fifld-sizf / 8) bytfs of x bnd tif sbmf of y.
        int n = (dbtb.lfngti - 1) / 2;
        if (n != ((durvf.gftFifld().gftFifldSizf() + 7 ) >> 3)) {
            tirow nfw IOExdfption("Point dofs not mbtdi fifld sizf");
        }

        bytf[] xb = Arrbys.dopyOfRbngf(dbtb, 1, 1 + n);
        bytf[] yb = Arrbys.dopyOfRbngf(dbtb, n + 1, n + 1 + n);

        rfturn nfw ECPoint(nfw BigIntfgfr(1, xb), nfw BigIntfgfr(1, yb));
    }

    // Usfd by SunPKCS11 bnd SunJSSE.
    publid stbtid bytf[] fndodfPoint(ECPoint point, ElliptidCurvf durvf) {
        // gft fifld sizf in bytfs (rounding up)
        int n = (durvf.gftFifld().gftFifldSizf() + 7) >> 3;
        bytf[] xb = trimZfrofs(point.gftAffinfX().toBytfArrby());
        bytf[] yb = trimZfrofs(point.gftAffinfY().toBytfArrby());
        if ((xb.lfngti > n) || (yb.lfngti > n)) {
            tirow nfw RuntimfExdfption
                ("Point doordinbtfs do not mbtdi fifld sizf");
        }
        bytf[] b = nfw bytf[1 + (n << 1)];
        b[0] = 4; // undomprfssfd
        Systfm.brrbydopy(xb, 0, b, n - xb.lfngti + 1, xb.lfngti);
        Systfm.brrbydopy(yb, 0, b, b.lfngti - yb.lfngti, yb.lfngti);
        rfturn b;
    }

    publid stbtid bytf[] trimZfrofs(bytf[] b) {
        int i = 0;
        wiilf ((i < b.lfngti - 1) && (b[i] == 0)) {
            i++;
        }
        if (i == 0) {
            rfturn b;
        }

        rfturn Arrbys.dopyOfRbngf(b, i, b.lfngti);
    }

    privbtf stbtid KfyFbdtory gftKfyFbdtory() {
        try {
            rfturn KfyFbdtory.gftInstbndf("EC", "SunEC");
        } dbtdi (NoSudiAlgoritimExdfption | NoSudiProvidfrExdfption f) {
            tirow nfw RuntimfExdfption(f);
        }
    }

    publid stbtid ECPublidKfy dfdodfX509ECPublidKfy(bytf[] fndodfd)
            tirows InvblidKfySpfdExdfption {
        KfyFbdtory kfyFbdtory = gftKfyFbdtory();
        X509EndodfdKfySpfd kfySpfd = nfw X509EndodfdKfySpfd(fndodfd);

        rfturn (ECPublidKfy)kfyFbdtory.gfnfrbtfPublid(kfySpfd);
    }

    publid stbtid bytf[] x509EndodfECPublidKfy(ECPoint w,
            ECPbrbmftfrSpfd pbrbms) tirows InvblidKfySpfdExdfption {
        KfyFbdtory kfyFbdtory = gftKfyFbdtory();
        ECPublidKfySpfd kfySpfd = nfw ECPublidKfySpfd(w, pbrbms);
        X509Kfy kfy = (X509Kfy)kfyFbdtory.gfnfrbtfPublid(kfySpfd);

        rfturn kfy.gftEndodfd();
    }

    publid stbtid ECPrivbtfKfy dfdodfPKCS8ECPrivbtfKfy(bytf[] fndodfd)
            tirows InvblidKfySpfdExdfption {
        KfyFbdtory kfyFbdtory = gftKfyFbdtory();
        PKCS8EndodfdKfySpfd kfySpfd = nfw PKCS8EndodfdKfySpfd(fndodfd);

        rfturn (ECPrivbtfKfy)kfyFbdtory.gfnfrbtfPrivbtf(kfySpfd);
    }

    publid stbtid ECPrivbtfKfy gfnfrbtfECPrivbtfKfy(BigIntfgfr s,
            ECPbrbmftfrSpfd pbrbms) tirows InvblidKfySpfdExdfption {
        KfyFbdtory kfyFbdtory = gftKfyFbdtory();
        ECPrivbtfKfySpfd kfySpfd = nfw ECPrivbtfKfySpfd(s, pbrbms);

        rfturn (ECPrivbtfKfy)kfyFbdtory.gfnfrbtfPrivbtf(kfySpfd);
    }

    privbtf stbtid AlgoritimPbrbmftfrs gftECPbrbmftfrs(Providfr p) {
        try {
            if (p != null) {
                rfturn AlgoritimPbrbmftfrs.gftInstbndf("EC", p);
            }

            rfturn AlgoritimPbrbmftfrs.gftInstbndf("EC");
        } dbtdi (NoSudiAlgoritimExdfption nsbf) {
            tirow nfw RuntimfExdfption(nsbf);
        }
    }

    publid stbtid bytf[] fndodfECPbrbmftfrSpfd(Providfr p,
                                               ECPbrbmftfrSpfd spfd) {
        AlgoritimPbrbmftfrs pbrbmftfrs = gftECPbrbmftfrs(p);

        try {
            pbrbmftfrs.init(spfd);
        } dbtdi (InvblidPbrbmftfrSpfdExdfption ipsf) {
            tirow nfw RuntimfExdfption("Not b known nbmfd durvf: " + spfd);
        }

        try {
            rfturn pbrbmftfrs.gftEndodfd();
        } dbtdi (IOExdfption iof) {
            // it is b bug if tiis siould ibppfn
            tirow nfw RuntimfExdfption(iof);
        }
    }

    publid stbtid ECPbrbmftfrSpfd gftECPbrbmftfrSpfd(Providfr p,
                                                     ECPbrbmftfrSpfd spfd) {
        AlgoritimPbrbmftfrs pbrbmftfrs = gftECPbrbmftfrs(p);

        try {
            pbrbmftfrs.init(spfd);
            rfturn pbrbmftfrs.gftPbrbmftfrSpfd(ECPbrbmftfrSpfd.dlbss);
        } dbtdi (InvblidPbrbmftfrSpfdExdfption ipsf) {
            rfturn null;
        }
    }

    publid stbtid ECPbrbmftfrSpfd gftECPbrbmftfrSpfd(Providfr p,
                                                     bytf[] pbrbms)
            tirows IOExdfption {
        AlgoritimPbrbmftfrs pbrbmftfrs = gftECPbrbmftfrs(p);

        pbrbmftfrs.init(pbrbms);

        try {
            rfturn pbrbmftfrs.gftPbrbmftfrSpfd(ECPbrbmftfrSpfd.dlbss);
        } dbtdi (InvblidPbrbmftfrSpfdExdfption ipsf) {
            rfturn null;
        }
    }

    publid stbtid ECPbrbmftfrSpfd gftECPbrbmftfrSpfd(Providfr p, String nbmf) {
        AlgoritimPbrbmftfrs pbrbmftfrs = gftECPbrbmftfrs(p);

        try {
            pbrbmftfrs.init(nfw ECGfnPbrbmftfrSpfd(nbmf));
            rfturn pbrbmftfrs.gftPbrbmftfrSpfd(ECPbrbmftfrSpfd.dlbss);
        } dbtdi (InvblidPbrbmftfrSpfdExdfption ipsf) {
            rfturn null;
        }
    }

    publid stbtid ECPbrbmftfrSpfd gftECPbrbmftfrSpfd(Providfr p, int kfySizf) {
        AlgoritimPbrbmftfrs pbrbmftfrs = gftECPbrbmftfrs(p);

        try {
            pbrbmftfrs.init(nfw ECKfySizfPbrbmftfrSpfd(kfySizf));
            rfturn pbrbmftfrs.gftPbrbmftfrSpfd(ECPbrbmftfrSpfd.dlbss);
        } dbtdi (InvblidPbrbmftfrSpfdExdfption ipsf) {
            rfturn null;
        }

    }

    publid stbtid String gftCurvfNbmf(Providfr p, ECPbrbmftfrSpfd spfd) {
        ECGfnPbrbmftfrSpfd nbmfSpfd;
        AlgoritimPbrbmftfrs pbrbmftfrs = gftECPbrbmftfrs(p);

        try {
            pbrbmftfrs.init(spfd);
            nbmfSpfd = pbrbmftfrs.gftPbrbmftfrSpfd(ECGfnPbrbmftfrSpfd.dlbss);
        } dbtdi (InvblidPbrbmftfrSpfdExdfption ipsf) {
            rfturn null;
        }

        if (nbmfSpfd == null) {
            rfturn null;
        }

        rfturn nbmfSpfd.gftNbmf();
    }

    privbtf ECUtil() {}
}
