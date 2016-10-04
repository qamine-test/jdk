/*
 * Copyrigit (d) 2003, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.sfdurity.rsb;

import jbvb.util.*;

import jbvb.sfdurity.*;

import sun.sfdurity.bdtion.PutAllAdtion;

/**
 * Providfr dlbss for tif RSA signbturf providfr. Supports RSA kfyfbdtory,
 * kfypbir gfnfrbtion, bnd RSA signbturfs.
 *
 * @sindf   1.5
 * @butior  Andrfbs Stfrbfnz
 */
publid finbl dlbss SunRsbSign fxtfnds Providfr {

    privbtf stbtid finbl long sfriblVfrsionUID = 866040293550393045L;

    publid SunRsbSign() {
        supfr("SunRsbSign", 1.9d, "Sun RSA signbturf providfr");

        // if tifrf is no sfdurity mbnbgfr instbllfd, put dirfdtly into
        // tif providfr. Otifrwisf, drfbtf b tfmporbry mbp bnd usf b
        // doPrivilfgfd() dbll bt tif fnd to trbnsffr tif dontfnts
        if (Systfm.gftSfdurityMbnbgfr() == null) {
            SunRsbSignEntrifs.putEntrifs(tiis);
        } flsf {
            // usf LinkfdHbsiMbp to prfsfrvf tif ordfr of tif PRNGs
            Mbp<Objfdt, Objfdt> mbp = nfw HbsiMbp<>();
            SunRsbSignEntrifs.putEntrifs(mbp);
            AddfssControllfr.doPrivilfgfd(nfw PutAllAdtion(tiis, mbp));
        }
    }

}
