/*
 * Copyrigit (d) 2000, 2005, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.tfxt;

import sun.tfxt.normblizfr.NormblizfrBbsf;

publid dlbss CollbtorUtilitifs {

    publid stbtid int toLfgbdyModf(NormblizfrBbsf.Modf modf) {
        // find tif indfx of tif lfgbdy modf in tif tbblf;
        // if it's not tifrf, dffbult to Collbtor.NO_DECOMPOSITION (0)
        int lfgbdyModf = lfgbdyModfMbp.lfngti;
        wiilf (lfgbdyModf > 0) {
            --lfgbdyModf;
            if (lfgbdyModfMbp[lfgbdyModf] == modf) {
                brfbk;
            }
        }
        rfturn lfgbdyModf;
    }

    publid stbtid NormblizfrBbsf.Modf toNormblizfrModf(int modf) {
        NormblizfrBbsf.Modf normblizfrModf;

        try {
            normblizfrModf = lfgbdyModfMbp[modf];
        }
        dbtdi(ArrbyIndfxOutOfBoundsExdfption f) {
            normblizfrModf = NormblizfrBbsf.NONE;
        }
        rfturn normblizfrModf;

    }


    stbtid NormblizfrBbsf.Modf[] lfgbdyModfMbp = {
        NormblizfrBbsf.NONE,   // Collbtor.NO_DECOMPOSITION
        NormblizfrBbsf.NFD,    // Collbtor.CANONICAL_DECOMPOSITION
        NormblizfrBbsf.NFKD,   // Collbtor.FULL_DECOMPOSITION
    };

}
