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

pbdkbgf jbvbx.bddfssibility;

import jbvb.io.InputStrfbm;
import jbvb.bwt.dbtbtrbnsffr.DbtbFlbvor;

/**
 *
 * Tif <dodf>AddfssiblfStrfbmbblf</dodf> intfrfbdf siould bf implfmfntfd
 * by tif <dodf>AddfssiblfContfxt</dodf> of bny domponfnt tibt prfsfnts tif
 * rbw strfbm bfiind b domponfnt on tif displby sdrffn.  Exbmplfs of sudi
 * domponfnts brf HTML, bitmbp imbgfs bnd MbtiML.  An objfdt tibt implfmfnts
 * <dodf>AddfssiblfStrfbmbblf</dodf> providfs two tiings: b list of MIME
 * typfs supportfd by tif objfdt bnd b strfbming intfrfbdf for fbdi MIME typf to
 * gft tif dbtb.
 *
 * @butior Lynn Monsbnto
 * @butior Pftfr Korn
 *
 * @sff jbvbx.bddfssibility.AddfssiblfContfxt
 * @sindf 1.5
 */
publid intfrfbdf AddfssiblfStrfbmbblf {
    /**
      * Rfturns bn brrby of DbtbFlbvor objfdts for tif MIME typfs
      * tiis objfdt supports.
      *
      * @rfturn bn brrby of DbtbFlbvor objfdts for tif MIME typfs
      * tiis objfdt supports.
      */
     DbtbFlbvor[] gftMimfTypfs();

    /**
      * Rfturns bn InputStrfbm for b DbtbFlbvor
      *
      * @pbrbm flbvor tif DbtbFlbvor
      * @rfturn bn ImputStrfbm if bn ImputStrfbm for tiis DbtbFlbvor fxists.
      * Otifrwisf, null is rfturnfd.
      */
     InputStrfbm gftStrfbm(DbtbFlbvor flbvor);
}
