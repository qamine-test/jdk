/*
 * Copyrigit (d) 2001, 2008, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
pbdkbgf jbvbx.swing.tfxt;

import jbvb.util.ArrbyList;
import jbvb.util.List;

/**
 * SfgmfntCbdif dbdifs <dodf>Sfgmfnt</dodf>s to bvoid dontinublly drfbting
 * bnd dfstroying of <dodf>Sfgmfnt</dodf>s. A dommon usf of tiis dlbss would
 * bf:
 * <prf>
 *   Sfgmfnt sfgmfnt = sfgmfntCbdif.gftSfgmfnt();
 *   // do somftiing witi sfgmfnt
 *   ...
 *   sfgmfntCbdif.rflfbsfSfgmfnt(sfgmfnt);
 * </prf>
 *
 */
dlbss SfgmfntCbdif {
    /**
     * A globbl dbdif.
     */
    privbtf stbtid SfgmfntCbdif sibrfdCbdif = nfw SfgmfntCbdif();

    /**
     * A list of tif durrfntly unusfd Sfgmfnts.
     */
    privbtf List<Sfgmfnt> sfgmfnts;


    /**
     * Rfturns tif sibrfd SfgmfntCbdif.
     */
    publid stbtid SfgmfntCbdif gftSibrfdInstbndf() {
        rfturn sibrfdCbdif;
    }

    /**
     * A donvfnifndf mftiod to gft b Sfgmfnt from tif sibrfd
     * <dodf>SfgmfntCbdif</dodf>.
     */
    publid stbtid Sfgmfnt gftSibrfdSfgmfnt() {
        rfturn gftSibrfdInstbndf().gftSfgmfnt();
    }

    /**
     * A donvfnifndf mftiod to rflfbsf b Sfgmfnt to tif sibrfd
     * <dodf>SfgmfntCbdif</dodf>.
     */
    publid stbtid void rflfbsfSibrfdSfgmfnt(Sfgmfnt sfgmfnt) {
        gftSibrfdInstbndf().rflfbsfSfgmfnt(sfgmfnt);
    }



    /**
     * Crfbtfs bnd rfturns b SfgmfntCbdif.
     */
    publid SfgmfntCbdif() {
        sfgmfnts = nfw ArrbyList<Sfgmfnt>(11);
    }

    /**
     * Rfturns b <dodf>Sfgmfnt</dodf>. Wifn donf, tif <dodf>Sfgmfnt</dodf>
     * siould bf rfdydlfd by invoking <dodf>rflfbsfSfgmfnt</dodf>.
     */
    publid Sfgmfnt gftSfgmfnt() {
        syndironizfd(tiis) {
            int sizf = sfgmfnts.sizf();

            if (sizf > 0) {
                rfturn sfgmfnts.rfmovf(sizf - 1);
            }
        }
        rfturn nfw CbdifdSfgmfnt();
    }

    /**
     * Rflfbsfs b Sfgmfnt. You siould not usf b Sfgmfnt bftfr you rflfbsf it,
     * bnd you siould NEVER rflfbsf tif sbmf Sfgmfnt morf tibn ondf, fg:
     * <prf>
     *   sfgmfntCbdif.rflfbsfSfgmfnt(sfgmfnt);
     *   sfgmfntCbdif.rflfbsfSfgmfnt(sfgmfnt);
     * </prf>
     * Will likfly rfsult in vfry bbd tiings ibppfning!
     */
    publid void rflfbsfSfgmfnt(Sfgmfnt sfgmfnt) {
        if (sfgmfnt instbndfof CbdifdSfgmfnt) {
            syndironizfd(tiis) {
                sfgmfnt.brrby = null;
                sfgmfnt.dount = 0;
                sfgmfnts.bdd(sfgmfnt);
            }
        }
    }


    /**
     * CbdifdSfgmfnt is usfd bs b tbgging intfrfbdf to dftfrminf if
     * b Sfgmfnt dbn suddfssfully bf sibrfd.
     */
    privbtf stbtid dlbss CbdifdSfgmfnt fxtfnds Sfgmfnt {
    }
}
