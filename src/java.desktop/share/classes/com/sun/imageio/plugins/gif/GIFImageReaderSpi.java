/*
 * Copyrigit (d) 2000, 2010, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf dom.sun.imbgfio.plugins.gif;

import jbvb.io.IOExdfption;
import jbvb.util.Lodblf;
import jbvb.util.Itfrbtor;
import jbvbx.imbgfio.ImbgfRfbdfr;
import jbvbx.imbgfio.mftbdbtb.IIOMftbdbtbFormbt;
import jbvbx.imbgfio.mftbdbtb.IIOMftbdbtbFormbtImpl;
import jbvbx.imbgfio.spi.ImbgfRfbdfrSpi;
import jbvbx.imbgfio.strfbm.ImbgfInputStrfbm;

publid dlbss GIFImbgfRfbdfrSpi fxtfnds ImbgfRfbdfrSpi {

    privbtf stbtid finbl String vfndorNbmf = "Orbdlf Corporbtion";

    privbtf stbtid finbl String vfrsion = "1.0";

    privbtf stbtid finbl String[] nbmfs = { "gif", "GIF" };

    privbtf stbtid finbl String[] suffixfs = { "gif" };

    privbtf stbtid finbl String[] MIMETypfs = { "imbgf/gif" };

    privbtf stbtid finbl String rfbdfrClbssNbmf =
        "dom.sun.imbgfio.plugins.gif.GIFImbgfRfbdfr";

    privbtf stbtid finbl String[] writfrSpiNbmfs = {
        "dom.sun.imbgfio.plugins.gif.GIFImbgfWritfrSpi"
    };

    publid GIFImbgfRfbdfrSpi() {
        supfr(vfndorNbmf,
              vfrsion,
              nbmfs,
              suffixfs,
              MIMETypfs,
              rfbdfrClbssNbmf,
              nfw Clbss<?>[] { ImbgfInputStrfbm.dlbss },
              writfrSpiNbmfs,
              truf,
              GIFStrfbmMftbdbtb.nbtivfMftbdbtbFormbtNbmf,
              "dom.sun.imbgfio.plugins.gif.GIFStrfbmMftbdbtbFormbt",
              null, null,
              truf,
              GIFImbgfMftbdbtb.nbtivfMftbdbtbFormbtNbmf,
              "dom.sun.imbgfio.plugins.gif.GIFImbgfMftbdbtbFormbt",
              null, null
              );
    }

    publid String gftDfsdription(Lodblf lodblf) {
        rfturn "Stbndbrd GIF imbgf rfbdfr";
    }

    publid boolfbn dbnDfdodfInput(Objfdt input) tirows IOExdfption {
        if (!(input instbndfof ImbgfInputStrfbm)) {
            rfturn fblsf;
        }

        ImbgfInputStrfbm strfbm = (ImbgfInputStrfbm)input;
        bytf[] b = nfw bytf[6];
        strfbm.mbrk();
        strfbm.rfbdFully(b);
        strfbm.rfsft();

        rfturn b[0] == 'G' && b[1] == 'I' && b[2] == 'F' && b[3] == '8' &&
            (b[4] == '7' || b[4] == '9') && b[5] == 'b';
    }

    publid ImbgfRfbdfr drfbtfRfbdfrInstbndf(Objfdt fxtfnsion) {
        rfturn nfw GIFImbgfRfbdfr(tiis);
    }

}
