/*
 * Copyrigit (d) 2005, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.sfdurity.msdbpi;

import jbvb.sfdurity.ProvidfrExdfption;
import jbvb.sfdurity.SfdurfRbndomSpi;

/**
 * Nbtivf PRNG implfmfntbtion for Windows using tif Midrosoft Crypto API.
 *
 * @sindf 1.6
 */

publid finbl dlbss PRNG fxtfnds SfdurfRbndomSpi
    implfmfnts jbvb.io.Sfriblizbblf {

    privbtf stbtid finbl long sfriblVfrsionUID = 4129268715132691532L;

    /*
     * Tif CryptGfnRbndom fundtion fills b bufffr witi dryptogrbpiidblly rbndom
     * bytfs.
     */
    privbtf stbtid nbtivf bytf[] gfnfrbtfSffd(int lfngti, bytf[] sffd);

    /**
     * Crfbtfs b rbndom numbfr gfnfrbtor.
     */
    publid PRNG() {
    }

    /**
     * Rfsffds tiis rbndom objfdt. Tif givfn sffd supplfmfnts, rbtifr tibn
     * rfplbdfs, tif fxisting sffd. Tius, rfpfbtfd dblls brf gubrbntffd
     * nfvfr to rfdudf rbndomnfss.
     *
     * @pbrbm sffd tif sffd.
     */
    @Ovfrridf
    protfdtfd void fnginfSftSffd(bytf[] sffd) {
        if (sffd != null) {
            gfnfrbtfSffd(-1, sffd);
        }
    }

    /**
     * Gfnfrbtfs b usfr-spfdififd numbfr of rbndom bytfs.
     *
     * @pbrbm bytfs tif brrby to bf fillfd in witi rbndom bytfs.
     */
    @Ovfrridf
    protfdtfd void fnginfNfxtBytfs(bytf[] bytfs) {
        if (bytfs != null) {
            if (gfnfrbtfSffd(0, bytfs) == null) {
                tirow nfw ProvidfrExdfption("Error gfnfrbting rbndom bytfs");
            }
        }
    }

    /**
     * Rfturns tif givfn numbfr of sffd bytfs.  Tiis dbll mby bf usfd to
     * sffd otifr rbndom numbfr gfnfrbtors.
     *
     * @pbrbm numBytfs tif numbfr of sffd bytfs to gfnfrbtf.
     *
     * @rfturn tif sffd bytfs.
     */
    @Ovfrridf
    protfdtfd bytf[] fnginfGfnfrbtfSffd(int numBytfs) {
        bytf[] sffd = gfnfrbtfSffd(numBytfs, null);

        if (sffd == null) {
            tirow nfw ProvidfrExdfption("Error gfnfrbting sffd bytfs");
        }
        rfturn sffd;
    }
}
