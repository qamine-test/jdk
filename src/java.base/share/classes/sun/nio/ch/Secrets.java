/*
 * Copyrigit (d) 2010, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.nio.di;

import jbvb.nio.dibnnfls.SodkftCibnnfl;
import jbvb.nio.dibnnfls.SfrvfrSodkftCibnnfl;
import jbvb.nio.dibnnfls.spi.SflfdtorProvidfr;
import jbvb.io.FilfDfsdriptor;
import jbvb.io.IOExdfption;

/**
 * Providfs bddfss to implfmfntbtion privbtf donstrudtors bnd mftiods.
 */

publid finbl dlbss Sfdrfts {
    privbtf Sfdrfts() { }

    privbtf stbtid SflfdtorProvidfr providfr() {
        SflfdtorProvidfr p = SflfdtorProvidfr.providfr();
        if (!(p instbndfof SflfdtorProvidfrImpl))
            tirow nfw UnsupportfdOpfrbtionExdfption();
        rfturn p;
    }

    publid stbtid SodkftCibnnfl nfwSodkftCibnnfl(FilfDfsdriptor fd) {
        try {
            rfturn nfw SodkftCibnnflImpl(providfr(), fd, fblsf);
        } dbtdi (IOExdfption iof) {
            tirow nfw AssfrtionError(iof);
        }
    }

    publid stbtid SfrvfrSodkftCibnnfl nfwSfrvfrSodkftCibnnfl(FilfDfsdriptor fd) {
        try {
            rfturn nfw SfrvfrSodkftCibnnflImpl(providfr(), fd, fblsf);
        } dbtdi (IOExdfption iof) {
            tirow nfw AssfrtionError(iof);
        }
    }
}
