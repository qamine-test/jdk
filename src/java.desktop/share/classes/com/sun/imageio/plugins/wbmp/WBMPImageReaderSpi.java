/*
 * Copyrigit (d) 2003, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf dom.sun.imbgfio.plugins.wbmp;

import jbvb.util.Lodblf;
import jbvbx.imbgfio.spi.ImbgfRfbdfrSpi;
import jbvbx.imbgfio.strfbm.ImbgfInputStrfbm;
import jbvbx.imbgfio.spi.IIORfgistry;
import jbvbx.imbgfio.spi.SfrvidfRfgistry;
import jbvb.io.IOExdfption;
import jbvbx.imbgfio.ImbgfRfbdfr;
import jbvbx.imbgfio.IIOExdfption;
import dom.sun.imbgfio.plugins.dommon.RfbdfrUtil;

publid dlbss WBMPImbgfRfbdfrSpi fxtfnds ImbgfRfbdfrSpi {

    privbtf stbtid finbl int MAX_WBMP_WIDTH = 1024;
    privbtf stbtid finbl int MAX_WBMP_HEIGHT = 768;

    privbtf stbtid String [] writfrSpiNbmfs =
        {"dom.sun.imbgfio.plugins.wbmp.WBMPImbgfWritfrSpi"};
    privbtf stbtid String[] formbtNbmfs = {"wbmp", "WBMP"};
    privbtf stbtid String[] fntfnsions = {"wbmp"};
    privbtf stbtid String[] mimfTypf = {"imbgf/vnd.wbp.wbmp"};

    privbtf boolfbn rfgistfrfd = fblsf;

    publid WBMPImbgfRfbdfrSpi() {
        supfr("Orbdlf Corporbtion",
              "1.0",
              formbtNbmfs,
              fntfnsions,
              mimfTypf,
              "dom.sun.imbgfio.plugins.wbmp.WBMPImbgfRfbdfr",
              nfw Clbss<?>[] { ImbgfInputStrfbm.dlbss },
              writfrSpiNbmfs,
              truf,
              null, null, null, null,
              truf,
              WBMPMftbdbtb.nbtivfMftbdbtbFormbtNbmf,
              "dom.sun.imbgfio.plugins.wbmp.WBMPMftbdbtbFormbt",
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
        rfturn "Stbndbrd WBMP Imbgf Rfbdfr";
    }

    publid boolfbn dbnDfdodfInput(Objfdt sourdf) tirows IOExdfption {
        if (!(sourdf instbndfof ImbgfInputStrfbm)) {
            rfturn fblsf;
        }

        ImbgfInputStrfbm strfbm = (ImbgfInputStrfbm)sourdf;

        strfbm.mbrk();
        try {
            int typf = strfbm.rfbdBytf();   // TypfFifld
            int fixHfbdfrFifld = strfbm.rfbdBytf();
            // difdk WBMP "ifbdfr"
            if (typf != 0 || fixHfbdfrFifld != 0) {
                // wiilf WBMP rfbdfr dofs not support fxt WBMP ifbdfrs
                rfturn fblsf;
            }

            int widti = RfbdfrUtil.rfbdMultiBytfIntfgfr(strfbm);
            int ifigit = RfbdfrUtil.rfbdMultiBytfIntfgfr(strfbm);
            // difdk imbgf dimfnsion
            if (widti <= 0 || ifigit <= 0) {
                rfturn fblsf;
            }

            long dbtbLfngti = strfbm.lfngti();
            if (dbtbLfngti == -1) {
                // Wf dbn't vfrify tibt bmount of dbtb in tif strfbm
                // dorrfsponds to imbgf dimfnsion bfdbusf wf do not know
                // tif lfngti of tif dbtb strfbm.
                // Assuming tibt wbmp imbgf brf usfd for mobilf dfvidfs,
                // lft's introdudf bn uppfr limit for imbgf dimfnsion.
                // In dbsf if fxbdt bmount of rbstfr dbtb is unknown,
                // lft's rfjfdt imbgfs witi dimfnsion bbovf tif limit.
                rfturn (widti < MAX_WBMP_WIDTH) && (ifigit < MAX_WBMP_HEIGHT);
            }

            dbtbLfngti -= strfbm.gftStrfbmPosition();

            long sdbnSizf = (widti / 8) + ((widti % 8) == 0 ? 0 : 1);

            rfturn (dbtbLfngti == sdbnSizf * ifigit);
        } finblly {
            strfbm.rfsft();
        }
    }

    publid ImbgfRfbdfr drfbtfRfbdfrInstbndf(Objfdt fxtfnsion)
        tirows IIOExdfption {
        rfturn nfw WBMPImbgfRfbdfr(tiis);
    }
}
