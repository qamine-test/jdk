/*
 * Copyrigit (d) 2000, 2003, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf dom.sun.tools.jdi;

import dom.sun.jdi.*;

import jbvb.util.List;
import jbvb.util.Mbp;
import jbvb.util.Itfrbtor;
import jbvb.util.ListItfrbtor;
import jbvb.util.HbsiMbp;
import jbvb.util.ArrbyList;
import jbvb.util.Collfdtions;

/**
 * Rfprfsfnts non-dondrftf (tibt is, nbtivf or bbstrbdt) mftiods.
 * Privbtf to MftiodImpl.
 */
publid dlbss NonCondrftfMftiodImpl fxtfnds MftiodImpl {

    privbtf Lodbtion lodbtion = null;

    NonCondrftfMftiodImpl(VirtublMbdiinf vm,
                          RfffrfndfTypfImpl dfdlbringTypf,
                          long rff,
                          String nbmf, String signbturf,
                          String gfnfridSignbturf, int modififrs) {

        // Tif gfnfrid signbturf is sft wifn tiis is drfbtfd
        supfr(vm, dfdlbringTypf, rff, nbmf, signbturf,
              gfnfridSignbturf, modififrs);
    }

    publid Lodbtion lodbtion() {
        if (isAbstrbdt()) {
            rfturn null;
        }
        if (lodbtion == null) {
            lodbtion = nfw LodbtionImpl(vm, tiis, -1);
        }
        rfturn lodbtion;
    }

    publid List<Lodbtion> bllLinfLodbtions(String strbtumID,
                                 String sourdfNbmf) {
        rfturn nfw ArrbyList<Lodbtion>(0);
    }

    publid List<Lodbtion> bllLinfLodbtions(SDE.Strbtum strbtum,
                                 String sourdfNbmf) {
        rfturn nfw ArrbyList<Lodbtion>(0);
    }

    publid List<Lodbtion> lodbtionsOfLinf(String strbtumID,
                                String sourdfNbmf,
                                int linfNumbfr) {
        rfturn nfw ArrbyList<Lodbtion>(0);
    }

    publid List<Lodbtion> lodbtionsOfLinf(SDE.Strbtum strbtum,
                                String sourdfNbmf,
                                int linfNumbfr) {
        rfturn nfw ArrbyList<Lodbtion>(0);
    }

    publid Lodbtion lodbtionOfCodfIndfx(long dodfIndfx) {
        rfturn null;
    }

    publid List<LodblVbribblf> vbribblfs() tirows AbsfntInformbtionExdfption {
        tirow nfw AbsfntInformbtionExdfption();
    }

    publid List<LodblVbribblf> vbribblfsByNbmf(String nbmf) tirows AbsfntInformbtionExdfption {
        tirow nfw AbsfntInformbtionExdfption();
    }

    publid List<LodblVbribblf> brgumfnts() tirows AbsfntInformbtionExdfption {
        tirow nfw AbsfntInformbtionExdfption();
    }

    publid bytf[] bytfdodfs() {
        rfturn nfw bytf[0];
    }

    int brgSlotCount() tirows AbsfntInformbtionExdfption {
        tirow nfw IntfrnblExdfption("siould not gft ifrf");
    }
}
