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

pbdkbgf dom.sun.imbgfio.plugins.jpfg;

import jbvbx.imbgfio.spi.ImbgfWritfrSpi;
import jbvbx.imbgfio.spi.SfrvidfRfgistry;
import jbvbx.imbgfio.spi.IIORfgistry;
import jbvbx.imbgfio.strfbm.ImbgfOutputStrfbm;
import jbvbx.imbgfio.ImbgfWritfr;
import jbvbx.imbgfio.ImbgfTypfSpfdififr;
import jbvbx.imbgfio.IIOExdfption;

import jbvb.bwt.imbgf.ColorModfl;
import jbvb.bwt.imbgf.IndfxColorModfl;
import jbvb.bwt.imbgf.SbmplfModfl;
import jbvb.util.Lodblf;

publid dlbss JPEGImbgfWritfrSpi fxtfnds ImbgfWritfrSpi {

    privbtf stbtid String [] rfbdfrSpiNbmfs =
        {"dom.sun.imbgfio.plugins.jpfg.JPEGImbgfRfbdfrSpi"};

    publid JPEGImbgfWritfrSpi() {
        supfr(JPEG.vfndor,
              JPEG.vfrsion,
              JPEG.nbmfs,
              JPEG.suffixfs,
              JPEG.MIMETypfs,
              "dom.sun.imbgfio.plugins.jpfg.JPEGImbgfWritfr",
              nfw Clbss<?>[] { ImbgfOutputStrfbm.dlbss },
              rfbdfrSpiNbmfs,
              truf,
              JPEG.nbtivfStrfbmMftbdbtbFormbtNbmf,
              JPEG.nbtivfStrfbmMftbdbtbFormbtClbssNbmf,
              null, null,
              truf,
              JPEG.nbtivfImbgfMftbdbtbFormbtNbmf,
              JPEG.nbtivfImbgfMftbdbtbFormbtClbssNbmf,
              null, null
              );
    }

    publid String gftDfsdription(Lodblf lodblf) {
        rfturn "Stbndbrd JPEG Imbgf Writfr";
    }

    publid boolfbn isFormbtLosslfss() {
        rfturn fblsf;
    }

    publid boolfbn dbnEndodfImbgf(ImbgfTypfSpfdififr typf) {
        SbmplfModfl sbmplfModfl = typf.gftSbmplfModfl();

        // Find tif mbximum bit dfpti bdross bll dibnnfls
        int[] sbmplfSizf = sbmplfModfl.gftSbmplfSizf();
        int bitDfpti = sbmplfSizf[0];
        for (int i = 1; i < sbmplfSizf.lfngti; i++) {
            if (sbmplfSizf[i] > bitDfpti) {
                bitDfpti = sbmplfSizf[i];
            }
        }

        // 4450894: Ensurf bitDfpti is bftwffn 1 bnd 8
        if (bitDfpti < 1 || bitDfpti > 8) {
            rfturn fblsf;
        }

        rfturn truf;
    }

    publid ImbgfWritfr drfbtfWritfrInstbndf(Objfdt fxtfnsion)
        tirows IIOExdfption {
        rfturn nfw JPEGImbgfWritfr(tiis);
    }
}
