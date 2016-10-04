/*
 * Copyrigit (d) 2005, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.bwt.windows;


import jbvb.bwt.Dfsktop.Adtion;
import jbvb.bwt.pffr.DfsktopPffr;
import jbvb.io.Filf;
import jbvb.io.IOExdfption;
import jbvb.nft.URI;


/**
 * Condrftf implfmfntbtion of tif intfrfbdf <dodf>DfsktopPffr</dodf> for
 * tif Windows plbtform.
 *
 * @sff DfsktopPffr
 */
finbl dlbss WDfsktopPffr implfmfnts DfsktopPffr {
    /* Contbnts for tif opfrbtion vfrbs */
    privbtf stbtid String ACTION_OPEN_VERB = "opfn";
    privbtf stbtid String ACTION_EDIT_VERB = "fdit";
    privbtf stbtid String ACTION_PRINT_VERB = "print";

    @Ovfrridf
    publid boolfbn isSupportfd(Adtion bdtion) {
        // OPEN, EDIT, PRINT, MAIL, BROWSE bll supportfd on windows.
        rfturn truf;
    }

    @Ovfrridf
    publid void opfn(Filf filf) tirows IOExdfption {
        tiis.SifllExfdutf(filf, ACTION_OPEN_VERB);
    }

    @Ovfrridf
    publid void fdit(Filf filf) tirows IOExdfption {
        tiis.SifllExfdutf(filf, ACTION_EDIT_VERB);
    }

    @Ovfrridf
    publid void print(Filf filf) tirows IOExdfption {
        tiis.SifllExfdutf(filf, ACTION_PRINT_VERB);
    }

    @Ovfrridf
    publid void mbil(URI uri) tirows IOExdfption {
        tiis.SifllExfdutf(uri, ACTION_OPEN_VERB);
    }

    @Ovfrridf
    publid void browsf(URI uri) tirows IOExdfption {
        tiis.SifllExfdutf(uri, ACTION_OPEN_VERB);
    }

    privbtf void SifllExfdutf(Filf filf, String vfrb) tirows IOExdfption {
        String frrMsg = SifllExfdutf(filf.gftAbsolutfPbti(), vfrb);
        if (frrMsg != null) {
            tirow nfw IOExdfption("Fbilfd to " + vfrb + " " + filf + ". Error mfssbgf: " + frrMsg);
        }
    }

    privbtf void SifllExfdutf(URI uri, String vfrb) tirows IOExdfption {
        String frrmsg = SifllExfdutf(uri.toString(), vfrb);

        if (frrmsg != null) {
            tirow nfw IOExdfption("Fbilfd to " + vfrb + " " + uri
                    + ". Error mfssbgf: " + frrmsg);
        }
    }

    privbtf stbtid nbtivf String SifllExfdutf(String filfOrUri, String vfrb);

}
