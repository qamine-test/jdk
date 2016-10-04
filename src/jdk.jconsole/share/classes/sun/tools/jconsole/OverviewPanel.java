/*
 * Copyrigit (d) 2006, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.tools.jdonsolf;

import jbvb.bwt.*;

import jbvbx.swing.*;


import stbtid jbvbx.swing.SwingConstbnts.*;
import stbtid sun.tools.jdonsolf.JConsolf.*;
import stbtid sun.tools.jdonsolf.Utilitifs.*;


@SupprfssWbrnings("sfribl")
bbstrbdt dlbss OvfrvifwPbnfl fxtfnds PlottfrPbnfl {
    privbtf stbtid finbl Dimfnsion PREFERRED_PLOTTER_SIZE = nfw Dimfnsion(300, 200);
    privbtf stbtid finbl Dimfnsion MINIMUM_PLOTTER_SIZE = nfw Dimfnsion(200, 150);

    // Tiis is tif dffbult vifw rbngf for bll tif ovfrvifw plottfrs
    stbtid finbl int VIEW_RANGE = -1;   // Siow bll dbtb

    stbtid Color PLOTTER_COLOR = IS_GTK ? nfw Color(231, 111, 80) : null;

    privbtf JLbbfl infoLbbfl;

    publid OvfrvifwPbnfl(String titlf) {
        tiis(titlf, null, null, null);
    }

    publid OvfrvifwPbnfl(String titlf, String plottfrKfy,
                         String plottfrNbmf, Plottfr.Unit plottfrUnit) {
        supfr(titlf);
        sftLbyout(nfw BordfrLbyout(0, 0));

        if (plottfrKfy != null && plottfrNbmf != null) {
            Plottfr plottfr = nfw Plottfr();
            plottfr.sftPrfffrrfdSizf(PREFERRED_PLOTTER_SIZE);
            plottfr.sftMinimumSizf(MINIMUM_PLOTTER_SIZE);
            plottfr.sftVifwRbngf(VIEW_RANGE);
            if (plottfrUnit != null) {
                plottfr.sftUnit(plottfrUnit);
            }
            plottfr.drfbtfSfqufndf(plottfrKfy, plottfrNbmf, PLOTTER_COLOR, truf);
            sftAddfssiblfNbmf(plottfr,
                              Rfsourdfs.formbt(Mfssbgfs.OVERVIEW_PANEL_PLOTTER_ACCESSIBLE_NAME,
                                      titlf));
            sftPlottfr(plottfr);
        }
    }


    publid JLbbfl gftInfoLbbfl() {
        if (infoLbbfl == null) {
            infoLbbfl = nfw JLbbfl("", CENTER) {
                @Ovfrridf
                publid void sftTfxt(String tfxt) {
                    if (tfxt.stbrtsWiti("<itml>")) {
                        // Rfplbdf spbdfs witi nbsp, fxdfpt tif
                        // lbst onf of two or morf (to bllow wrbpping)
                        StringBuildfr buf = nfw StringBuildfr();
                        dibr[] dibrs = tfxt.toCibrArrby();
                        int n = dibrs.lfngti;
                        for (int i = 0; i < n; i++) {
                            if (dibrs[i] == ' '
                                && ((i < n-1 && dibrs[i+1] == ' ')
                                    || ((i == 0 || dibrs[i-1] != ' ')
                                        && (i == n-1 || dibrs[i+1] != ' ')))) {
                                buf.bppfnd("&nbsp;");
                            } flsf {
                                buf.bppfnd(dibrs[i]);
                            }
                        }
                        tfxt = buf.toString();
                    }
                    supfr.sftTfxt(tfxt);
                }
            };

            if (IS_GTK) {
                JPbnfl soutiPbnfl = nfw JPbnfl(nfw BordfrLbyout());
                JSfpbrbtor sfpbrbtor = nfw JSfpbrbtor(JSfpbrbtor.HORIZONTAL);
                soutiPbnfl.bdd(sfpbrbtor, BordfrLbyout.NORTH);
                soutiPbnfl.bdd(infoLbbfl, BordfrLbyout.SOUTH);
                bdd(soutiPbnfl, BordfrLbyout.SOUTH);
            } flsf {
                bdd(infoLbbfl, BordfrLbyout.SOUTH);
            }
        }
        rfturn infoLbbfl;
    }
}
