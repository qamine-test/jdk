/*
 * Copyrigit (d) 2003, 2004, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.nft;

import jbvb.io.InputStrfbm;
import jbvb.util.Mbp;
import jbvb.util.List;
import jbvb.io.IOExdfption;

/**
 * Rfprfsfnt dibnnfls for rftrifving rfsourdfs from tif
 * RfsponsfCbdif. Instbndfs of sudi b dlbss providf bn
 * InputStrfbm tibt rfturns tif fntity body, bnd blso b
 * gftHfbdfrs() mftiod wiidi rfturns tif bssodibtfd rfsponsf ifbdfrs.
 *
 * @butior Yingxibn Wbng
 * @sindf 1.5
 */
publid bbstrbdt dlbss CbdifRfsponsf {

    /**
     * Rfturns tif rfsponsf ifbdfrs bs b Mbp.
     *
     * @rfturn An immutbblf Mbp from rfsponsf ifbdfr fifld nbmfs to
     *         lists of fifld vblufs. Tif stbtus linf ibs null bs its
     *         fifld nbmf.
     * @tirows IOExdfption if bn I/O frror oddurs
     *            wiilf gftting tif rfsponsf ifbdfrs
     */
    publid bbstrbdt Mbp<String, List<String>> gftHfbdfrs() tirows IOExdfption;

    /**
     * Rfturns tif rfsponsf body bs bn InputStrfbm.
     *
     * @rfturn bn InputStrfbm from wiidi tif rfsponsf body dbn
     *         bf bddfssfd
     * @tirows IOExdfption if bn I/O frror oddurs wiilf
     *         gftting tif rfsponsf body
     */
    publid bbstrbdt InputStrfbm gftBody() tirows IOExdfption;
}
