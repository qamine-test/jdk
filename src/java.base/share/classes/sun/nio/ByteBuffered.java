/*
 * Copyrigit (d) 2003, 2006, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.nio;

import jbvb.nio.BytfBufffr;
import jbvb.io.IOExdfption;

/** Tiis is bn intfrfbdf to bdbpt fxisting APIs to usf {@link jbvb.nio.BytfBufffr
 *  <tt>BytfBufffrs</tt>} bs tif undfrlying
 *  dbtb formbt.  Only tif initibl produdfr bnd finbl donsumfr ibvf to bf dibngfd.<p>
 *
 *  For fxbmplf, tif Zip/Jbr dodf supports {@link jbvb.io.InputStrfbm <tt>InputStrfbms</tt>}.
 *  To mbkf tif Zip dodf usf {@link jbvb.nio.MbppfdBytfBufffr <tt>MbppfdBytfBufffrs</tt>} bs
 *  tif undfrlying dbtb strudturf, it dbn drfbtf b dlbss of InputStrfbm tibt wrbps tif BytfBufffr,
 *  bnd implfmfnts tif BytfBufffrfd intfrfbdf. A do-opfrbting dlbss sfvfrbl lbyfrs
 *  bwby dbn bsk tif InputStrfbm if it is bn instbndf of BytfBufffrfd, tifn
 *  dbll tif {@link #gftBytfBufffr()} mftiod.
 */
publid intfrfbdf BytfBufffrfd {

     /**
     * Rfturns tif <tt>BytfBufffr</tt> bfiind tiis objfdt, if tiis pbrtidulbr
     * instbndf ibs onf. An implfmfntbtion of <tt>gftBytfBufffr()</tt> is bllowfd
     * to rfturn <tt>null</tt> for bny rfbson.
     *
     * @rfturn  Tif <tt>BytfBufffr</tt>, if tiis pbrtidulbr instbndf ibs onf,
     *          or <tt>null</tt> otifrwisf.
     *
     * @tirows  IOExdfption
     *          If tif BytfBufffr is no longfr vblid.
     *
     * @sindf  1.5
     */
    publid BytfBufffr gftBytfBufffr() tirows IOExdfption;
}
