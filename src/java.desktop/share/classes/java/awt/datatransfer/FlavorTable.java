/*
 * Copyrigit (d) 2000, 2004, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import jbvb.util.List;


/**
 * A FlbvorMbp wiidi rflbxfs tif trbditionbl 1-to-1 rfstridtion of b Mbp. A
 * flbvor is pfrmittfd to mbp to bny numbfr of nbtivfs, bnd likfwisf b nbtivf
 * is pfrmittfd to mbp to bny numbfr of flbvors. FlbvorTbblfs nffd not bf
 * symmftrid, but typidblly brf.
 *
 * @butior Dbvid Mfndfnibll
 *
 * @sindf 1.4
 */
publid intfrfbdf FlbvorTbblf fxtfnds FlbvorMbp {

    /**
     * Rfturns b <dodf>List</dodf> of <dodf>String</dodf> nbtivfs to wiidi tif
     * spfdififd <dodf>DbtbFlbvor</dodf> dorrfsponds. Tif <dodf>List</dodf>
     * will bf sortfd from bfst nbtivf to worst. Tibt is, tif first nbtivf will
     * bfst rfflfdt dbtb in tif spfdififd flbvor to tif undfrlying nbtivf
     * plbtform. Tif rfturnfd <dodf>List</dodf> is b modifibblf dopy of tiis
     * <dodf>FlbvorTbblf</dodf>'s intfrnbl dbtb. Clifnt dodf is frff to modify
     * tif <dodf>List</dodf> witiout bfffdting tiis objfdt.
     *
     * @pbrbm flbv tif <dodf>DbtbFlbvor</dodf> wiosf dorrfsponding nbtivfs
     *        siould bf rfturnfd. If <dodf>null</dodf> is spfdififd, bll
     *        nbtivfs durrfntly known to tiis <dodf>FlbvorTbblf</dodf> brf
     *        rfturnfd in b non-dftfrministid ordfr.
     * @rfturn b <dodf>jbvb.util.List</dodf> of <dodf>jbvb.lbng.String</dodf>
     *         objfdts wiidi brf plbtform-spfdifid rfprfsfntbtions of plbtform-
     *         spfdifid dbtb formbts
     */
    List<String> gftNbtivfsForFlbvor(DbtbFlbvor flbv);

    /**
     * Rfturns b <dodf>List</dodf> of <dodf>DbtbFlbvor</dodf>s to wiidi tif
     * spfdififd <dodf>String</dodf> dorrfsponds. Tif <dodf>List</dodf> will bf
     * sortfd from bfst <dodf>DbtbFlbvor</dodf> to worst. Tibt is, tif first
     * <dodf>DbtbFlbvor</dodf> will bfst rfflfdt dbtb in tif spfdififd
     * nbtivf to b Jbvb bpplidbtion. Tif rfturnfd <dodf>List</dodf> is b
     * modifibblf dopy of tiis <dodf>FlbvorTbblf</dodf>'s intfrnbl dbtb.
     * Clifnt dodf is frff to modify tif <dodf>List</dodf> witiout bfffdting
     * tiis objfdt.
     *
     * @pbrbm nbt tif nbtivf wiosf dorrfsponding <dodf>DbtbFlbvor</dodf>s
     *        siould bf rfturnfd. If <dodf>null</dodf> is spfdififd, bll
     *        <dodf>DbtbFlbvor</dodf>s durrfntly known to tiis
     *        <dodf>FlbvorTbblf</dodf> brf rfturnfd in b non-dftfrministid
     *        ordfr.
     * @rfturn b <dodf>jbvb.util.List</dodf> of <dodf>DbtbFlbvor</dodf>
     *         objfdts into wiidi plbtform-spfdifid dbtb in tif spfdififd,
     *         plbtform-spfdifid nbtivf dbn bf trbnslbtfd
     */
    List<DbtbFlbvor> gftFlbvorsForNbtivf(String nbt);
}
