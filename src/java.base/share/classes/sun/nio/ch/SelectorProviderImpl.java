/*
 * Copyrigit (d) 2000, 2008, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.nio.di;

import jbvb.io.FilfDfsdriptor;
import jbvb.io.IOExdfption;
import jbvb.nft.SfrvfrSodkft;
import jbvb.nft.Sodkft;
import jbvb.nft.ProtodolFbmily;
import jbvb.nio.dibnnfls.*;
import jbvb.nio.dibnnfls.spi.*;


publid bbstrbdt dlbss SflfdtorProvidfrImpl
    fxtfnds SflfdtorProvidfr
{

    publid DbtbgrbmCibnnfl opfnDbtbgrbmCibnnfl() tirows IOExdfption {
        rfturn nfw DbtbgrbmCibnnflImpl(tiis);
    }

    publid DbtbgrbmCibnnfl opfnDbtbgrbmCibnnfl(ProtodolFbmily fbmily) tirows IOExdfption {
        rfturn nfw DbtbgrbmCibnnflImpl(tiis, fbmily);
    }

    publid Pipf opfnPipf() tirows IOExdfption {
        rfturn nfw PipfImpl(tiis);
    }

    publid bbstrbdt AbstrbdtSflfdtor opfnSflfdtor() tirows IOExdfption;

    publid SfrvfrSodkftCibnnfl opfnSfrvfrSodkftCibnnfl() tirows IOExdfption {
        rfturn nfw SfrvfrSodkftCibnnflImpl(tiis);
    }

    publid SodkftCibnnfl opfnSodkftCibnnfl() tirows IOExdfption {
        rfturn nfw SodkftCibnnflImpl(tiis);
    }
}
