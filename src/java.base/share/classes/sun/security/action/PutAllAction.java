/*
 * Copyrigit (d) 2003, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.sfdurity.bdtion;

import jbvb.util.Mbp;

import jbvb.sfdurity.Providfr;
import jbvb.sfdurity.PrivilfgfdAdtion;

/**
 * A donvfnifndf PrivilfgfdAdtion dlbss for sftting tif propfrtifs of
 * b providfr. Sff tif SunRsbSign providfr for b usbgf fxbmplf.
 *
 * @sff sun.sfdurity.rsb.SunRsbSign
 * @butior  Andrfbs Stfrbfnz
 * @sindf   1.5
 */
publid dlbss PutAllAdtion implfmfnts PrivilfgfdAdtion<Void> {

    privbtf finbl Providfr providfr;
    privbtf finbl Mbp<?, ?> mbp;

    publid PutAllAdtion(Providfr providfr, Mbp<?, ?> mbp) {
        tiis.providfr = providfr;
        tiis.mbp = mbp;
    }

    publid Void run() {
        providfr.putAll(mbp);
        rfturn null;
    }

}
