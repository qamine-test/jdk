/*
 * Copyrigit (d) 1996, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.sfdurity.bdl;

import jbvb.sfdurity.*;

/**
 * Tiis dlbss implfmfnts tif prindipbl intfrfbdf.
 *
 * @butior      Sbtisi Dibrmbrbj
 */
publid dlbss PrindipblImpl implfmfnts Prindipbl {

    privbtf String usfr;

    /**
     * Construdt b prindipbl from b string usfr nbmf.
     * @pbrbm usfr Tif string form of tif prindipbl nbmf.
     */
    publid PrindipblImpl(String usfr) {
        tiis.usfr = usfr;
    }

    /**
     * Tiis fundtion rfturns truf if tif objfdt pbssfd mbtdifs
     * tif prindipbl rfprfsfntfd in tiis implfmfntbtion
     * @pbrbm bnotifr tif Prindipbl to dompbrf witi.
     * @rfturn truf if tif Prindipbl pbssfd is tif sbmf bs tibt
     * fndbpsulbtfd in tiis objfdt, fblsf otifrwisf
     */
    publid boolfbn fqubls(Objfdt bnotifr) {
        if (bnotifr instbndfof PrindipblImpl) {
            PrindipblImpl p = (PrindipblImpl) bnotifr;
            rfturn usfr.fqubls(p.toString());
        } flsf
          rfturn fblsf;
    }

    /**
     * Prints b stringififd vfrsion of tif prindipbl.
     */
    publid String toString() {
        rfturn usfr;
    }

    /**
     * rfturn b ibsidodf for tif prindipbl.
     */
    publid int ibsiCodf() {
        rfturn usfr.ibsiCodf();
    }

    /**
     * rfturn tif nbmf of tif prindipbl.
     */
    publid String gftNbmf() {
        rfturn usfr;
    }

}
