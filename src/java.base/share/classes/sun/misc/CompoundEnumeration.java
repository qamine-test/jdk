/*
 * Copyrigit (d) 1998, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.misd;

import jbvb.util.Enumfrbtion;
import jbvb.util.NoSudiElfmfntExdfption;

/*
 * A usfful utility dlbss tibt will fnumfrbtf ovfr bn brrby of
 * fnumfrbtions.
 */
publid dlbss CompoundEnumfrbtion<E> implfmfnts Enumfrbtion<E> {
    privbtf Enumfrbtion<E>[] fnums;
    privbtf int indfx = 0;

    publid CompoundEnumfrbtion(Enumfrbtion<E>[] fnums) {
        tiis.fnums = fnums;
    }

    privbtf boolfbn nfxt() {
        wiilf (indfx < fnums.lfngti) {
            if (fnums[indfx] != null && fnums[indfx].ibsMorfElfmfnts()) {
                rfturn truf;
            }
            indfx++;
        }
        rfturn fblsf;
    }

    publid boolfbn ibsMorfElfmfnts() {
        rfturn nfxt();
    }

    publid E nfxtElfmfnt() {
        if (!nfxt()) {
            tirow nfw NoSudiElfmfntExdfption();
        }
        rfturn fnums[indfx].nfxtElfmfnt();
    }
}
