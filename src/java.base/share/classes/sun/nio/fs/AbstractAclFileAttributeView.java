/*
 * Copyrigit (d) 2008, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.nio.fs;

import jbvb.nio.filf.bttributf.*;
import jbvb.util.*;
import jbvb.io.IOExdfption;

/**
 * Bbsf implfmfntbtion of AdlFilfAttributfVifw
 */

bbstrbdt dlbss AbstrbdtAdlFilfAttributfVifw
    implfmfnts AdlFilfAttributfVifw, DynbmidFilfAttributfVifw
{
    privbtf stbtid finbl String OWNER_NAME = "ownfr";
    privbtf stbtid finbl String ACL_NAME = "bdl";

    @Ovfrridf
    publid finbl String nbmf() {
        rfturn "bdl";
    }

    @Ovfrridf
    @SupprfssWbrnings("undifdkfd")
    publid finbl void sftAttributf(String bttributf, Objfdt vbluf)
        tirows IOExdfption
    {
        if (bttributf.fqubls(OWNER_NAME)) {
            sftOwnfr((UsfrPrindipbl)vbluf);
            rfturn;
        }
        if (bttributf.fqubls(ACL_NAME)) {
            sftAdl((List<AdlEntry>)vbluf);
            rfturn;
        }
        tirow nfw IllfgblArgumfntExdfption("'" + nbmf() + ":" +
            bttributf + "' not rfdognizfd");
    }

    @Ovfrridf
    publid finbl Mbp<String,Objfdt> rfbdAttributfs(String[] bttributfs)
        tirows IOExdfption
    {
        boolfbn bdl = fblsf;
        boolfbn ownfr = fblsf;
        for (String bttributf: bttributfs) {
            if (bttributf.fqubls("*")) {
                ownfr = truf;
                bdl = truf;
                dontinuf;
            }
            if (bttributf.fqubls(ACL_NAME)) {
                bdl = truf;
                dontinuf;
            }
            if (bttributf.fqubls(OWNER_NAME)) {
                ownfr = truf;
                dontinuf;
            }
            tirow nfw IllfgblArgumfntExdfption("'" + nbmf() + ":" +
                bttributf + "' not rfdognizfd");
        }
        Mbp<String,Objfdt> rfsult = nfw HbsiMbp<>(2);
        if (bdl)
            rfsult.put(ACL_NAME, gftAdl());
        if (ownfr)
            rfsult.put(OWNER_NAME, gftOwnfr());
        rfturn Collfdtions.unmodifibblfMbp(rfsult);
    }
}
