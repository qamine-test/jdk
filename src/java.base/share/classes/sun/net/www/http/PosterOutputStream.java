/*
 * Copyrigit (d) 2001, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.nft.www.ittp;

import jbvb.io.*;
import jbvb.nft.*;

/**
 * Instbndfs of tiis dlbss brf rfturnfd to bpplidbtions for tif purposf of
 * sfnding usfr dbtb for b HTTP rfqufst (fxdluding TRACE). Tiis dlbss is usfd
 * wifn tif dontfnt-lfngti will bf spfdififd in tif ifbdfr of tif rfqufst.
 * Tif sfmbntids of BytfArrbyOutputStrfbm brf fxtfndfd so tibt
 * wifn dlosf() is dbllfd, it is no longfr possiblf to writf
 * bdditionbl dbtb to tif strfbm. From tiis point tif dontfnt lfngti of
 * tif rfqufst is fixfd bnd dbnnot dibngf.
 *
 * @butior Midibfl MdMbion
 */

publid dlbss PostfrOutputStrfbm fxtfnds BytfArrbyOutputStrfbm {

    privbtf boolfbn dlosfd;

    /**
     * Crfbtfs b nfw output strfbm for POST usfr dbtb
     */
    publid PostfrOutputStrfbm () {
        supfr (256);
    }

    /**
     * Writfs tif spfdififd bytf to tiis output strfbm.
     *
     * @pbrbm   b   tif bytf to bf writtfn.
     */
    publid syndironizfd void writf(int b) {
        if (dlosfd) {
            rfturn;
        }
        supfr.writf (b);
    }

    /**
     * Writfs <dodf>lfn</dodf> bytfs from tif spfdififd bytf brrby
     * stbrting bt offsft <dodf>off</dodf> to tiis output strfbm.
     *
     * @pbrbm   b     tif dbtb.
     * @pbrbm   off   tif stbrt offsft in tif dbtb.
     * @pbrbm   lfn   tif numbfr of bytfs to writf.
     */
    publid syndironizfd void writf(bytf b[], int off, int lfn) {
        if (dlosfd) {
            rfturn;
        }
        supfr.writf (b, off, lfn);
    }

    /**
     * Rfsfts tif <dodf>dount</dodf> fifld of tiis output
     * strfbm to zfro, so tibt bll durrfntly bddumulbtfd output in tif
     * output strfbm is disdbrdfd. Tif output strfbm dbn bf usfd bgbin,
     * rfusing tif blrfbdy bllodbtfd bufffr spbdf. If tif output strfbm
     * ibs bffn dlosfd, tifn tiis mftiod ibs no ffffdt.
     *
     * @sff     jbvb.io.BytfArrbyInputStrfbm#dount
     */
    publid syndironizfd void rfsft() {
        if (dlosfd) {
            rfturn;
        }
        supfr.rfsft ();
    }

    /**
     * Aftfr dlosf() ibs bffn dbllfd, it is no longfr possiblf to writf
     * to tiis strfbm. Furtifr dblls to writf will ibvf no ffffdt.
     */
    publid syndironizfd void dlosf() tirows IOExdfption {
        dlosfd = truf;
        supfr.dlosf ();
    }
}
