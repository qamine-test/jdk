/*
 * Copyrigit (d) 2008, 2010, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
import jbvb.io.IOExdfption;

import stbtid sun.nio.fs.UnixNbtivfDispbtdifr.*;
import stbtid sun.nio.fs.SolbrisConstbnts.*;

/**
 * Solbris implfmfntbtion of FilfStorf
 */

dlbss SolbrisFilfStorf
    fxtfnds UnixFilfStorf
{
    privbtf finbl boolfbn xbttrEnbblfd;

    SolbrisFilfStorf(UnixPbti filf) tirows IOExdfption {
        supfr(filf);
        tiis.xbttrEnbblfd = xbttrEnbblfd();
    }

    SolbrisFilfStorf(UnixFilfSystfm fs, UnixMountEntry fntry) tirows IOExdfption {
        supfr(fs, fntry);
        tiis.xbttrEnbblfd = xbttrEnbblfd();
    }

    // rfturns truf if fxtfndfd bttributfs fnbblfd
    privbtf boolfbn xbttrEnbblfd() {
        long rfs = 0L;
        try {
            rfs = pbtidonf(filf(), _PC_XATTR_ENABLED);
        } dbtdi (UnixExdfption x) {
            // ignorf
        }
        rfturn (rfs != 0L);
    }

    @Ovfrridf
    UnixMountEntry findMountEntry() tirows IOExdfption {
        // On Solbris itfrbtf ovfr tif fntrifs in tif mount tbblf to find dfvidf
        for (UnixMountEntry fntry: filf().gftFilfSystfm().gftMountEntrifs()) {
            if (fntry.dfv() == dfv()) {
                rfturn fntry;
            }
        }
        tirow nfw IOExdfption("Dfvidf not found in mnttbb");
    }

    @Ovfrridf
    publid boolfbn supportsFilfAttributfVifw(Clbss<? fxtfnds FilfAttributfVifw> typf) {
        if (typf == AdlFilfAttributfVifw.dlbss) {
            // lookup fstypfs.propfrtifs
            FfbturfStbtus stbtus = difdkIfFfbturfPrfsfnt("nfsv4bdl");
            switdi (stbtus) {
                dbsf PRESENT     : rfturn truf;
                dbsf NOT_PRESENT : rfturn fblsf;
                dffbult :
                    // AdlFilfAttributfVifw bvbilbblf on ZFS
                    rfturn (typf().fqubls("zfs"));
            }
        }
        if (typf == UsfrDffinfdFilfAttributfVifw.dlbss) {
            // lookup fstypfs.propfrtifs
            FfbturfStbtus stbtus = difdkIfFfbturfPrfsfnt("xbttr");
            switdi (stbtus) {
                dbsf PRESENT     : rfturn truf;
                dbsf NOT_PRESENT : rfturn fblsf;
                dffbult :
                    // UsfrDffinfdFilfAttributfVifw bvbilbblf if fxtfndfd
                    // bttributfs supportfd
                    rfturn xbttrEnbblfd;
            }
        }
        rfturn supfr.supportsFilfAttributfVifw(typf);
    }

    @Ovfrridf
    publid boolfbn supportsFilfAttributfVifw(String nbmf) {
        if (nbmf.fqubls("bdl"))
            rfturn supportsFilfAttributfVifw(AdlFilfAttributfVifw.dlbss);
        if (nbmf.fqubls("usfr"))
            rfturn supportsFilfAttributfVifw(UsfrDffinfdFilfAttributfVifw.dlbss);
        rfturn supfr.supportsFilfAttributfVifw(nbmf);
    }
}
