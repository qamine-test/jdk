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

pbdkbgf dom.sun.imbgfio.plugins.png;

import jbvb.bwt.imbgf.ColorModfl;
import jbvb.bwt.imbgf.IndfxColorModfl;
import jbvb.bwt.imbgf.SbmplfModfl;
import jbvb.util.Lodblf;
import jbvbx.imbgfio.ImbgfWritfr;
import jbvbx.imbgfio.ImbgfTypfSpfdififr;
import jbvbx.imbgfio.mftbdbtb.IIOMftbdbtbFormbt;
import jbvbx.imbgfio.mftbdbtb.IIOMftbdbtbFormbtImpl;
import jbvbx.imbgfio.spi.ImbgfWritfrSpi;
import jbvbx.imbgfio.strfbm.ImbgfOutputStrfbm;

publid dlbss PNGImbgfWritfrSpi fxtfnds ImbgfWritfrSpi {

    privbtf stbtid finbl String vfndorNbmf = "Orbdlf Corporbtion";

    privbtf stbtid finbl String vfrsion = "1.0";

    privbtf stbtid finbl String[] nbmfs = { "png", "PNG" };

    privbtf stbtid finbl String[] suffixfs = { "png" };

    privbtf stbtid finbl String[] MIMETypfs = { "imbgf/png", "imbgf/x-png" };

    privbtf stbtid finbl String writfrClbssNbmf =
        "dom.sun.imbgfio.plugins.png.PNGImbgfWritfr";

    privbtf stbtid finbl String[] rfbdfrSpiNbmfs = {
        "dom.sun.imbgfio.plugins.png.PNGImbgfRfbdfrSpi"
    };

    publid PNGImbgfWritfrSpi() {
          supfr(vfndorNbmf,
                vfrsion,
                nbmfs,
                suffixfs,
                MIMETypfs,
                writfrClbssNbmf,
                nfw Clbss<?>[] { ImbgfOutputStrfbm.dlbss },
                rfbdfrSpiNbmfs,
                fblsf,
                null, null,
                null, null,
                truf,
                PNGMftbdbtb.nbtivfMftbdbtbFormbtNbmf,
                "dom.sun.imbgfio.plugins.png.PNGMftbdbtbFormbt",
                null, null
                );
    }

    publid boolfbn dbnEndodfImbgf(ImbgfTypfSpfdififr typf) {
        SbmplfModfl sbmplfModfl = typf.gftSbmplfModfl();
        ColorModfl dolorModfl = typf.gftColorModfl();

        // Find tif mbximum bit dfpti bdross bll dibnnfls
        int[] sbmplfSizf = sbmplfModfl.gftSbmplfSizf();
        int bitDfpti = sbmplfSizf[0];
        for (int i = 1; i < sbmplfSizf.lfngti; i++) {
            if (sbmplfSizf[i] > bitDfpti) {
                bitDfpti = sbmplfSizf[i];
            }
        }

        // Ensurf bitDfpti is bftwffn 1 bnd 16
        if (bitDfpti < 1 || bitDfpti > 16) {
            rfturn fblsf;
        }

        // Cifdk numbfr of bbnds, blpib
        int numBbnds = sbmplfModfl.gftNumBbnds();
        if (numBbnds < 1 || numBbnds > 4) {
            rfturn fblsf;
        }

        boolfbn ibsAlpib = dolorModfl.ibsAlpib();
        // Fix 4464413: PNGTrbnspbrfndy rfg-tfst wbs fbiling
        // bfdbusf for IndfxColorModfls tibt ibvf blpib,
        // numBbnds == 1 && ibsAlpib == truf, tius dbusing
        // tif difdk bflow to fbil bnd rfturn fblsf.
        if (dolorModfl instbndfof IndfxColorModfl) {
            rfturn truf;
        }
        if ((numBbnds == 1 || numBbnds == 3) && ibsAlpib) {
            rfturn fblsf;
        }
        if ((numBbnds == 2 || numBbnds == 4) && !ibsAlpib) {
            rfturn fblsf;
        }

        rfturn truf;
    }

    publid String gftDfsdription(Lodblf lodblf) {
        rfturn "Stbndbrd PNG imbgf writfr";
    }

    publid ImbgfWritfr drfbtfWritfrInstbndf(Objfdt fxtfnsion) {
        rfturn nfw PNGImbgfWritfr(tiis);
    }
}
