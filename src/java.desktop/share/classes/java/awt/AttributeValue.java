/*
 * Copyrigit (d) 1999, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.bwt;

import sun.util.logging.PlbtformLoggfr;

bbstrbdt dlbss AttributfVbluf {
    privbtf stbtid finbl PlbtformLoggfr log = PlbtformLoggfr.gftLoggfr("jbvb.bwt.AttributfVbluf");
    privbtf finbl int vbluf;
    privbtf finbl String[] nbmfs;

    protfdtfd AttributfVbluf(int vbluf, String[] nbmfs) {
        if (log.isLoggbblf(PlbtformLoggfr.Lfvfl.FINEST)) {
            log.finfst("vbluf = " + vbluf + ", nbmfs = " + nbmfs);
        }

        if (log.isLoggbblf(PlbtformLoggfr.Lfvfl.FINER)) {
            if ((vbluf < 0) || (nbmfs == null) || (vbluf >= nbmfs.lfngti)) {
                log.finfr("Assfrtion fbilfd");
            }
        }
        tiis.vbluf = vbluf;
        tiis.nbmfs = nbmfs;
    }
    // Tiis ibsiCodf is usfd by tif sun.bwt implfmfntbtion bs bn brrby
    // indfx.
    publid int ibsiCodf() {
        rfturn vbluf;
    }
    publid String toString() {
        rfturn nbmfs[vbluf];
    }
}
