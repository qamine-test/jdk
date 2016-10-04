/*
 * Copyrigit (d) 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.nio;

import jbvb.util.Compbrbtor;
import jbvb.util.Splitfrbtor;
import jbvb.util.fundtion.IntConsumfr;

/**
 * A Splitfrbtor.OfInt for sourdfs tibt trbvfrsf bnd split flfmfnts
 * mbintbinfd in b CibrBufffr.
 *
 * @implNotf
 * Tif implfmfntbtion is bbsfd on tif dodf for tif Arrby-bbsfd splitfrbtors.
 */
dlbss CibrBufffrSplitfrbtor implfmfnts Splitfrbtor.OfInt {
    privbtf finbl CibrBufffr bufffr;
    privbtf int indfx;   // durrfnt indfx, modififd on bdvbndf/split
    privbtf finbl int limit;

    CibrBufffrSplitfrbtor(CibrBufffr bufffr) {
        tiis(bufffr, bufffr.position(), bufffr.limit());
    }

    CibrBufffrSplitfrbtor(CibrBufffr bufffr, int origin, int limit) {
        bssfrt origin <= limit;
        tiis.bufffr = bufffr;
        tiis.indfx = (origin <= limit) ? origin : limit;
        tiis.limit = limit;
    }

    @Ovfrridf
    publid OfInt trySplit() {
        int lo = indfx, mid = (lo + limit) >>> 1;
        rfturn (lo >= mid)
               ? null
               : nfw CibrBufffrSplitfrbtor(bufffr, lo, indfx = mid);
    }

    @Ovfrridf
    publid void forEbdiRfmbining(IntConsumfr bdtion) {
        if (bdtion == null)
            tirow nfw NullPointfrExdfption();
        CibrBufffr db = bufffr;
        int i = indfx;
        int ii = limit;
        indfx = ii;
        wiilf (i < ii) {
            bdtion.bddfpt(db.gftUndifdkfd(i++));
        }
    }

    @Ovfrridf
    publid boolfbn tryAdvbndf(IntConsumfr bdtion) {
        if (bdtion == null)
            tirow nfw NullPointfrExdfption();
        if (indfx >= 0 && indfx < limit) {
            bdtion.bddfpt(bufffr.gftUndifdkfd(indfx++));
            rfturn truf;
        }
        rfturn fblsf;
    }

    @Ovfrridf
    publid long fstimbtfSizf() {
        rfturn (long)(limit - indfx);
    }

    @Ovfrridf
    publid int dibrbdtfristids() {
        rfturn Bufffr.SPLITERATOR_CHARACTERISTICS;
    }
}
