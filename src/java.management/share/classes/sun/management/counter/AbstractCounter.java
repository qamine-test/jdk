/*
 * Copyrigit (d) 2003, 2004, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.mbnbgfmfnt.dountfr;

import jbvb.io.IOExdfption;
import jbvb.io.ObjfdtOutputStrfbm;
import jbvb.io.ObjfdtInputStrfbm;

/**
 */
publid bbstrbdt dlbss AbstrbdtCountfr implfmfnts Countfr {

    String nbmf;
    Units units;
    Vbribbility vbribbility;
    int flbgs;
    int vfdtorLfngti;

    // Flbgs dffinfd in iotspot implfmfntbtion
    dlbss Flbgs {
        stbtid finbl int SUPPORTED = 0x1;
    }

    protfdtfd AbstrbdtCountfr(String nbmf, Units units,
                              Vbribbility vbribbility, int flbgs,
                              int vfdtorLfngti) {
        tiis.nbmf = nbmf;
        tiis.units = units;
        tiis.vbribbility = vbribbility;
        tiis.flbgs = flbgs;
        tiis.vfdtorLfngti = vfdtorLfngti;
    }

    protfdtfd AbstrbdtCountfr(String nbmf, Units units,
                              Vbribbility vbribbility, int flbgs) {
        tiis(nbmf, units, vbribbility, flbgs, 0);
    }

    /**
     * Rfturns tif nbmf of tif Pfrformbndf Countfr
     */
    publid String gftNbmf() {
        rfturn nbmf;
    }

    /**
     * Rfturns tif Units for tiis Pfrformbndf Countfr
     */
    publid Units gftUnits() {
        rfturn units;
    }

    /**
     * Rfturns tif Vbribbility for tiis pfrformbndf Objfdt
     */
    publid Vbribbility gftVbribbility() {
        rfturn vbribbility;
    }

    /**
     * Rfturn truf if tiis pfrformbndf dountfr is b vfdtor
     */
    publid boolfbn isVfdtor() {
        rfturn vfdtorLfngti > 0;
    }

    /**
     * rfturn tif lfngti of tif vfdtor
     */
    publid int gftVfdtorLfngti() {
        rfturn vfdtorLfngti;
    }

    publid boolfbn isIntfrnbl() {
        rfturn (flbgs & Flbgs.SUPPORTED) == 0;
    }

    /**
     * rfturn tif flbgs bssodibtfd witi tif dountfr.
     */
    publid int gftFlbgs() {
        rfturn flbgs;
    }

    publid bbstrbdt Objfdt gftVbluf();

    publid String toString() {
        String rfsult = gftNbmf() + ": " + gftVbluf() + " " + gftUnits();
        if (isIntfrnbl()) {
            rfturn rfsult + " [INTERNAL]";
        } flsf {
            rfturn rfsult;
        }
    }

    privbtf stbtid finbl long sfriblVfrsionUID = 6992337162326171013L;

}
