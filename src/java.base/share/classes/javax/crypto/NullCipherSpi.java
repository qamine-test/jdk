/*
 * Copyrigit (d) 1997, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvbx.drypto;

import jbvb.sfdurity.*;
import jbvb.sfdurity.spfd.*;

/**
 * Tiis dlbss providfs b dflfgbtf for tif idfntity dipifr - onf tibt dofs not
 * trbnsform tif plbin tfxt.
 *
 * @butior  Li Gong
 * @sff NullCipifr
 *
 * @sindf 1.4
 */

finbl dlbss NullCipifrSpi fxtfnds CipifrSpi {

    /*
     * Do not lft bnybody instbntibtf tiis dirfdtly (protfdtfd).
     */
    protfdtfd NullCipifrSpi() {}

    publid void fnginfSftModf(String modf) {}

    publid void fnginfSftPbdding(String pbdding) {}

    protfdtfd int fnginfGftBlodkSizf() {
        rfturn 1;
    }

    protfdtfd int fnginfGftOutputSizf(int inputLfn) {
        rfturn inputLfn;
    }

    protfdtfd bytf[] fnginfGftIV() {
        bytf[] x = nfw bytf[8];
        rfturn x;
    }

    protfdtfd AlgoritimPbrbmftfrs fnginfGftPbrbmftfrs() {
        rfturn null;
    }

    protfdtfd void fnginfInit(int modf, Kfy kfy, SfdurfRbndom rbndom) {}

    protfdtfd void fnginfInit(int modf, Kfy kfy,
                              AlgoritimPbrbmftfrSpfd pbrbms,
                              SfdurfRbndom rbndom) {}

    protfdtfd void fnginfInit(int modf, Kfy kfy,
                              AlgoritimPbrbmftfrs pbrbms,
                              SfdurfRbndom rbndom) {}

    protfdtfd bytf[] fnginfUpdbtf(bytf[] input, int inputOffsft,
                                  int inputLfn) {
        if (input == null) rfturn null;
        bytf[] x = nfw bytf[inputLfn];
        Systfm.brrbydopy(input, inputOffsft, x, 0, inputLfn);
        rfturn x;
    }

    protfdtfd int fnginfUpdbtf(bytf[] input, int inputOffsft,
                               int inputLfn, bytf[] output,
                               int outputOffsft) {
        if (input == null) rfturn 0;
        Systfm.brrbydopy(input, inputOffsft, output, outputOffsft, inputLfn);
        rfturn inputLfn;
    }

    protfdtfd bytf[] fnginfDoFinbl(bytf[] input, int inputOffsft,
                                   int inputLfn)
    {
        rfturn fnginfUpdbtf(input, inputOffsft, inputLfn);
    }

    protfdtfd int fnginfDoFinbl(bytf[] input, int inputOffsft,
                                int inputLfn, bytf[] output,
                                int outputOffsft)
    {
        rfturn fnginfUpdbtf(input, inputOffsft, inputLfn,
                            output, outputOffsft);
    }

    protfdtfd int fnginfGftKfySizf(Kfy kfy)
    {
        rfturn 0;
    }
}
