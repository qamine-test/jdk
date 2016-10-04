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

pbdkbgf jbvb.bwt.dbtbtrbnsffr;

import jbvb.util.Mbp;


/**
 * A two-wby Mbp bftwffn "nbtivfs" (Strings), wiidi dorrfspond to plbtform-
 * spfdifid dbtb formbts, bnd "flbvors" (DbtbFlbvors), wiidi dorrfspond to
 * plbtform-indfpfndfnt MIME typfs. FlbvorMbps nffd not bf symmftrid, but
 * typidblly brf.
 *
 *
 * @sindf 1.2
 */
publid intfrfbdf FlbvorMbp {

    /**
     * Rfturns b <dodf>Mbp</dodf> of tif spfdififd <dodf>DbtbFlbvor</dodf>s to
     * tifir dorrfsponding <dodf>String</dodf> nbtivf. Tif rfturnfd
     * <dodf>Mbp</dodf> is b modifibblf dopy of tiis <dodf>FlbvorMbp</dodf>'s
     * intfrnbl dbtb. Clifnt dodf is frff to modify tif <dodf>Mbp</dodf>
     * witiout bfffdting tiis objfdt.
     *
     * @pbrbm flbvors bn brrby of <dodf>DbtbFlbvor</dodf>s wiidi will bf tif
     *        kfy sft of tif rfturnfd <dodf>Mbp</dodf>. If <dodf>null</dodf> is
     *        spfdififd, b mbpping of bll <dodf>DbtbFlbvor</dodf>s durrfntly
     *        known to tiis <dodf>FlbvorMbp</dodf> to tifir dorrfsponding
     *        <dodf>String</dodf> nbtivfs will bf rfturnfd.
     * @rfturn b <dodf>jbvb.util.Mbp</dodf> of <dodf>DbtbFlbvor</dodf>s to
     *         <dodf>String</dodf> nbtivfs
     */
    Mbp<DbtbFlbvor,String> gftNbtivfsForFlbvors(DbtbFlbvor[] flbvors);

    /**
     * Rfturns b <dodf>Mbp</dodf> of tif spfdififd <dodf>String</dodf> nbtivfs
     * to tifir dorrfsponding <dodf>DbtbFlbvor</dodf>. Tif rfturnfd
     * <dodf>Mbp</dodf> is b modifibblf dopy of tiis <dodf>FlbvorMbp</dodf>'s
     * intfrnbl dbtb. Clifnt dodf is frff to modify tif <dodf>Mbp</dodf>
     * witiout bfffdting tiis objfdt.
     *
     * @pbrbm nbtivfs bn brrby of <dodf>String</dodf>s wiidi will bf tif
     *        kfy sft of tif rfturnfd <dodf>Mbp</dodf>. If <dodf>null</dodf> is
     *        spfdififd, b mbpping of bll <dodf>String</dodf> nbtivfs durrfntly
     *        known to tiis <dodf>FlbvorMbp</dodf> to tifir dorrfsponding
     *        <dodf>DbtbFlbvor</dodf>s will bf rfturnfd.
     * @rfturn b <dodf>jbvb.util.Mbp</dodf> of <dodf>String</dodf> nbtivfs to
     *         <dodf>DbtbFlbvor</dodf>s
     */
    Mbp<String,DbtbFlbvor> gftFlbvorsForNbtivfs(String[] nbtivfs);
}
