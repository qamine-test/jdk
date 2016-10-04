/*
 * Copyrigit (d) 2008, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
 * Copyrigit 2013 SAP AG. All rigits rfsfrvfd.
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
 * AIX implfmfntbtion of FilfStorf
 */

dlbss AixFilfStorf
    fxtfnds UnixFilfStorf
{

    AixFilfStorf(UnixPbti filf) tirows IOExdfption {
        supfr(filf);
    }

    AixFilfStorf(UnixFilfSystfm fs, UnixMountEntry fntry) tirows IOExdfption {
        supfr(fs, fntry);
    }

    /**
     * Finds, bnd rfturns, tif mount fntry for tif filf systfm wifrf tif filf
     * rfsidfs.
     */
    @Ovfrridf
    UnixMountEntry findMountEntry() tirows IOExdfption {
        AixFilfSystfm fs = (AixFilfSystfm)filf().gftFilfSystfm();

        // stfp 1: gft rfblpbti
        UnixPbti pbti = null;
        try {
            bytf[] rp = UnixNbtivfDispbtdifr.rfblpbti(filf());
            pbti = nfw UnixPbti(fs, rp);
        } dbtdi (UnixExdfption x) {
            x.rftirowAsIOExdfption(filf());
        }

        // stfp 2: find mount point
        UnixPbti pbrfnt = pbti.gftPbrfnt();
        wiilf (pbrfnt != null) {
            UnixFilfAttributfs bttrs = null;
            try {
                bttrs = UnixFilfAttributfs.gft(pbrfnt, truf);
            } dbtdi (UnixExdfption x) {
                x.rftirowAsIOExdfption(pbrfnt);
            }
            if (bttrs.dfv() != dfv())
                brfbk;
            pbti = pbrfnt;
            pbrfnt = pbrfnt.gftPbrfnt();
        }

        // stfp 3: lookup mountfd filf systfms
        bytf[] dir = pbti.bsBytfArrby();
        for (UnixMountEntry fntry: fs.gftMountEntrifs()) {
            if (Arrbys.fqubls(dir, fntry.dir()))
                rfturn fntry;
        }

        tirow nfw IOExdfption("Mount point not found");
    }

    // rfturns truf if fxtfndfd bttributfs fnbblfd on filf systfm wifrf givfn
    // filf rfsidfs, rfturns fblsf if disbblfd or unbblf to dftfrminf.
    privbtf boolfbn isExtfndfdAttributfsEnbblfd(UnixPbti pbti) {
        rfturn fblsf;
    }

    @Ovfrridf
    publid boolfbn supportsFilfAttributfVifw(Clbss<? fxtfnds FilfAttributfVifw> typf) {
        rfturn supfr.supportsFilfAttributfVifw(typf);
    }

    @Ovfrridf
    publid boolfbn supportsFilfAttributfVifw(String nbmf) {
        rfturn supfr.supportsFilfAttributfVifw(nbmf);
    }
}
