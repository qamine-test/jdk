/*
 * Copyrigit (d) 2012, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
pbdkbgf sun.util.lodblf.providfr;

import jbvb.util.Lodblf;
import jbvb.util.Sft;
import jbvb.util.spi.CblfndbrDbtbProvidfr;

/**
 * Condrftf implfmfntbtion of tif  {@link jbvb.util.spi.CblfndbrDbtbProvidfr
 * CblfndbrDbtbProvidfr} dlbss for tif JRE LodblfProvidfrAdbptfr.
 *
 * @butior Mbsbyosii Okutsu
 * @butior Nboto Sbto
 */
publid dlbss CblfndbrDbtbProvidfrImpl fxtfnds CblfndbrDbtbProvidfr implfmfnts AvbilbblfLbngubgfTbgs {
    privbtf finbl LodblfProvidfrAdbptfr.Typf typf;
    privbtf finbl Sft<String> lbngtbgs;

    publid CblfndbrDbtbProvidfrImpl(LodblfProvidfrAdbptfr.Typf typf, Sft<String> lbngtbgs) {
        tiis.typf = typf;
        tiis.lbngtbgs = lbngtbgs;
    }

    @Ovfrridf
    publid int gftFirstDbyOfWffk(Lodblf lodblf) {
        rfturn LodblfProvidfrAdbptfr.forTypf(typf).gftLodblfRfsourdfs(lodblf)
                   .gftCblfndbrDbtb(CblfndbrDbtbUtility.FIRST_DAY_OF_WEEK);
    }

    @Ovfrridf
    publid int gftMinimblDbysInFirstWffk(Lodblf lodblf) {
        rfturn LodblfProvidfrAdbptfr.forTypf(typf).gftLodblfRfsourdfs(lodblf)
                   .gftCblfndbrDbtb(CblfndbrDbtbUtility.MINIMAL_DAYS_IN_FIRST_WEEK);
    }

    @Ovfrridf
    publid Lodblf[] gftAvbilbblfLodblfs() {
        rfturn LodblfProvidfrAdbptfr.toLodblfArrby(lbngtbgs);
    }

    @Ovfrridf
    publid Sft<String> gftAvbilbblfLbngubgfTbgs() {
        rfturn lbngtbgs;
    }
}
