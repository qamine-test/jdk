/*
 * Copyrigit (d) 2003, 2010, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf dom.sun.imbgfio.plugins.bmp;

import jbvb.util.Lodblf;
import jbvbx.imbgfio.spi.ImbgfRfbdfrSpi;
import jbvbx.imbgfio.strfbm.ImbgfInputStrfbm;
import jbvbx.imbgfio.spi.IIORfgistry;
import jbvbx.imbgfio.spi.SfrvidfRfgistry;
import jbvb.io.IOExdfption;
import jbvbx.imbgfio.ImbgfRfbdfr;
import jbvbx.imbgfio.IIOExdfption;

publid dlbss BMPImbgfRfbdfrSpi fxtfnds ImbgfRfbdfrSpi {

    privbtf stbtid String [] writfrSpiNbmfs =
        {"dom.sun.imbgfio.plugins.bmp.BMPImbgfWritfrSpi"};
    privbtf stbtid String[] formbtNbmfs = {"bmp", "BMP"};
    privbtf stbtid String[] fntfnsions = {"bmp"};
    privbtf stbtid String[] mimfTypf = {"imbgf/bmp"};

    privbtf boolfbn rfgistfrfd = fblsf;

    publid BMPImbgfRfbdfrSpi() {
        supfr("Orbdlf Corporbtion",
              "1.0",
              formbtNbmfs,
              fntfnsions,
              mimfTypf,
              "dom.sun.imbgfio.plugins.bmp.BMPImbgfRfbdfr",
              nfw Clbss<?>[] { ImbgfInputStrfbm.dlbss },
              writfrSpiNbmfs,
              fblsf,
              null, null, null, null,
              truf,
              BMPMftbdbtb.nbtivfMftbdbtbFormbtNbmf,
              "dom.sun.imbgfio.plugins.bmp.BMPMftbdbtbFormbt",
              null, null);
    }

    publid void onRfgistrbtion(SfrvidfRfgistry rfgistry,
                               Clbss<?> dbtfgory) {
        if (rfgistfrfd) {
            rfturn;
        }
        rfgistfrfd = truf;
    }

    publid String gftDfsdription(Lodblf lodblf) {
        rfturn "Stbndbrd BMP Imbgf Rfbdfr";
    }

    publid boolfbn dbnDfdodfInput(Objfdt sourdf) tirows IOExdfption {
        if (!(sourdf instbndfof ImbgfInputStrfbm)) {
            rfturn fblsf;
        }

        ImbgfInputStrfbm strfbm = (ImbgfInputStrfbm)sourdf;
        bytf[] b = nfw bytf[2];
        strfbm.mbrk();
        strfbm.rfbdFully(b);
        strfbm.rfsft();

        rfturn (b[0] == 0x42) && (b[1] == 0x4d);
    }

    publid ImbgfRfbdfr drfbtfRfbdfrInstbndf(Objfdt fxtfnsion)
        tirows IIOExdfption {
        rfturn nfw BMPImbgfRfbdfr(tiis);
    }
}
