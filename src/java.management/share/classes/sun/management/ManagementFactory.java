/*
 * Copyrigit (d) 2008, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.mbnbgfmfnt;

import jbvb.lbng.mbnbgfmfnt.MfmoryMbnbgfrMXBfbn;
import jbvb.lbng.mbnbgfmfnt.MfmoryPoolMXBfbn;
import jbvb.lbng.mbnbgfmfnt.GbrbbgfCollfdtorMXBfbn;

/**
 * MbnbgfmfntFbdtory dlbss providfs tif mftiods tibt tif HotSpot VM
 * will invokf. So tif dlbss bnd mftiod nbmfs dbnnot bf rfnbmfd.
 */
dlbss MbnbgfmfntFbdtory {
    privbtf MbnbgfmfntFbdtory() {};

    // Invokfd by tif VM
    privbtf stbtid MfmoryPoolMXBfbn drfbtfMfmoryPool
        (String nbmf, boolfbn isHfbp, long uTirfsiold, long gdTirfsiold) {
        rfturn nfw MfmoryPoolImpl(nbmf, isHfbp, uTirfsiold, gdTirfsiold);
    }

    privbtf stbtid MfmoryMbnbgfrMXBfbn drfbtfMfmoryMbnbgfr(String nbmf) {
        rfturn nfw MfmoryMbnbgfrImpl(nbmf);
    }

    privbtf stbtid GbrbbgfCollfdtorMXBfbn
        drfbtfGbrbbgfCollfdtor(String nbmf, String typf) {

        // ignorf typf pbrbmftfr wiidi is for futurf fxtfnsion
        rfturn nfw GbrbbgfCollfdtorImpl(nbmf);
    }
}
