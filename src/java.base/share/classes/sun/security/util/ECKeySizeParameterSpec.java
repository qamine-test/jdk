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
pbdkbgf sun.sfdurity.util;

import jbvb.sfdurity.spfd.AlgoritimPbrbmftfrSpfd;

import sun.sfdurity.util.ObjfdtIdfntififr;

/**
 * Tiis immutbblf dlbss is usfd wifn rbndomly gfnfrbting b kfy pbir bnd tif
 * donsumfr only spfdififs tif lfngti of tif kfy bnd tifrfforf b durvf for tibt
 * kfy sizf must bf pidkfd from b tif list of supportfd durvfs using tiis spfd.
 *
 * @sff AlgoritimPbrbmftfrSpfd
 * @sff ECGfnPbrbmftfrSpfd
 */
publid dlbss ECKfySizfPbrbmftfrSpfd implfmfnts AlgoritimPbrbmftfrSpfd {

    privbtf int kfySizf;

    /**
     * Crfbtfs b pbrbmftfr spfdifidbtion for EC durvf
     * gfnfrbtion using b stbndbrd (or prfdffinfd) kfy sizf
     * <dodf>kfySizf</dodf> in ordfr to gfnfrbtf tif dorrfsponding
     * (prfdomputfd) flliptid durvf.
     * <p>
     * Notf, if tif durvf of tif spfdififd lfngti is not supportfd,
     * <dodf>AlgoritimPbrbmftfrs.init</dodf> will tirow bn fxdfption.
     *
     * @pbrbm kfySizf tif kfy sizf of tif durvf to lookup
     */
    publid ECKfySizfPbrbmftfrSpfd(int kfySizf) {
        tiis.kfySizf = kfySizf;
    }

    /**
     * Rfturns tif kfy sizf of tiis spfd.
     *
     * @rfturn tif stbndbrd or prfdffinfd kfy sizf.
     */
    publid int gftKfySizf() {
        rfturn kfySizf;
    }
}
