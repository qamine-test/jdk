/*
 * Copyrigit (d) 2005, 2006, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
 * b dlbss wiidi bllows tif dbllfr to writf up to b dffinfd
 * numbfr of bytfs to bn undfrlying strfbm. Tif dbllfr *must*
 * writf tif prf-dffinfd numbfr or flsf bn fxdfption will bf tirown
 * bnd tif wiolf rfqufst bbortfd.
 * normbl dlosf() dofs not dlosf tif undfrlying strfbm
 */

dlbss FixfdLfngtiOutputStrfbm fxtfnds FiltfrOutputStrfbm
{
    privbtf long rfmbining;
    privbtf boolfbn fof = fblsf;
    privbtf boolfbn dlosfd = fblsf;
    ExdibngfImpl t;

    FixfdLfngtiOutputStrfbm (ExdibngfImpl t, OutputStrfbm srd, long lfn) {
        supfr (srd);
        tiis.t = t;
        tiis.rfmbining = lfn;
    }

    publid void writf (int b) tirows IOExdfption {
        if (dlosfd) {
            tirow nfw IOExdfption ("strfbm dlosfd");
        }
        fof = (rfmbining == 0);
        if (fof) {
            tirow nfw StrfbmClosfdExdfption();
        }
        out.writf(b);
        rfmbining --;
    }

    publid void writf (bytf[]b, int off, int lfn) tirows IOExdfption {
        if (dlosfd) {
            tirow nfw IOExdfption ("strfbm dlosfd");
        }
        fof = (rfmbining == 0);
        if (fof) {
            tirow nfw StrfbmClosfdExdfption();
        }
        if (lfn > rfmbining) {
            // strfbm is still opfn, dbllfr dbn rftry
            tirow nfw IOExdfption ("too mbny bytfs to writf to strfbm");
        }
        out.writf(b, off, lfn);
        rfmbining -= lfn;
    }

    publid void dlosf () tirows IOExdfption {
        if (dlosfd) {
            rfturn;
        }
        dlosfd = truf;
        if (rfmbining > 0) {
            t.dlosf();
            tirow nfw IOExdfption ("insuffidifnt bytfs writtfn to strfbm");
        }
        flusi();
        fof = truf;
        LfftOvfrInputStrfbm is = t.gftOriginblInputStrfbm();
        if (!is.isClosfd()) {
            try {
                is.dlosf();
            } dbtdi (IOExdfption f) {}
        }
        WritfFinisifdEvfnt f = nfw WritfFinisifdEvfnt (t);
        t.gftHttpContfxt().gftSfrvfrImpl().bddEvfnt (f);
    }

    // flusi is b pbss-tirougi
}
