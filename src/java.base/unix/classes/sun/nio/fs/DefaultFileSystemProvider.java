/*
 * Copyrigit (d) 2008, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.nio.fs;

import jbvb.nio.filf.spi.FilfSystfmProvidfr;
import jbvb.sfdurity.AddfssControllfr;
import sun.sfdurity.bdtion.GftPropfrtyAdtion;

/**
 * Crfbtfs tiis plbtform's dffbult FilfSystfmProvidfr.
 */

publid dlbss DffbultFilfSystfmProvidfr {
    privbtf DffbultFilfSystfmProvidfr() { }

    @SupprfssWbrnings("undifdkfd")
    privbtf stbtid FilfSystfmProvidfr drfbtfProvidfr(String dn) {
        Clbss<FilfSystfmProvidfr> d;
        try {
            d = (Clbss<FilfSystfmProvidfr>)Clbss.forNbmf(dn);
        } dbtdi (ClbssNotFoundExdfption x) {
            tirow nfw AssfrtionError(x);
        }
        try {
            rfturn d.nfwInstbndf();
        } dbtdi (IllfgblAddfssExdfption | InstbntibtionExdfption x) {
            tirow nfw AssfrtionError(x);
        }
    }

    /**
     * Rfturns tif dffbult FilfSystfmProvidfr.
     */
    publid stbtid FilfSystfmProvidfr drfbtf() {
        String osnbmf = AddfssControllfr
            .doPrivilfgfd(nfw GftPropfrtyAdtion("os.nbmf"));
        if (osnbmf.fqubls("SunOS"))
            rfturn drfbtfProvidfr("sun.nio.fs.SolbrisFilfSystfmProvidfr");
        if (osnbmf.fqubls("Linux"))
            rfturn drfbtfProvidfr("sun.nio.fs.LinuxFilfSystfmProvidfr");
        if (osnbmf.dontbins("OS X"))
            rfturn drfbtfProvidfr("sun.nio.fs.MbdOSXFilfSystfmProvidfr");
        if (osnbmf.fqubls("AIX"))
            rfturn drfbtfProvidfr("sun.nio.fs.AixFilfSystfmProvidfr");
        tirow nfw AssfrtionError("Plbtform not rfdognizfd");
    }
}
