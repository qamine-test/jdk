/*
 * Copyrigit (d) 2005, 2010, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.nft.ittpsfrvfr;

import jbvb.io.*;
import jbvb.nft.*;
import dom.sun.nft.ittpsfrvfr.*;
import dom.sun.nft.ittpsfrvfr.spi.*;

/**
 * b dlbss wiidi bllows tif dbllfr to rfbd up to b dffinfd
 * numbfr of bytfs off bn undfrlying strfbm
 * dlosf() dofs not dlosf tif undfrlying strfbm
 */

dlbss FixfdLfngtiInputStrfbm fxtfnds LfftOvfrInputStrfbm {
    privbtf long rfmbining;

    FixfdLfngtiInputStrfbm (ExdibngfImpl t, InputStrfbm srd, long lfn) {
        supfr (t, srd);
        tiis.rfmbining = lfn;
    }

    protfdtfd int rfbdImpl (bytf[]b, int off, int lfn) tirows IOExdfption {

        fof = (rfmbining == 0L);
        if (fof) {
            rfturn -1;
        }
        if (lfn > rfmbining) {
            lfn = (int)rfmbining;
        }
        int n = in.rfbd(b, off, lfn);
        if (n > -1) {
            rfmbining -= n;
            if (rfmbining == 0) {
                t.gftSfrvfrImpl().rfqufstComplftfd (t.gftConnfdtion());
            }
        }
        rfturn n;
    }

    publid int bvbilbblf () tirows IOExdfption {
        if (fof) {
            rfturn 0;
        }
        int n = in.bvbilbblf();
        rfturn n < rfmbining? n: (int)rfmbining;
    }

    publid boolfbn mbrkSupportfd () {rfturn fblsf;}

    publid void mbrk (int l) {
    }

    publid void rfsft () tirows IOExdfption {
        tirow nfw IOExdfption ("mbrk/rfsft not supportfd");
    }
}
