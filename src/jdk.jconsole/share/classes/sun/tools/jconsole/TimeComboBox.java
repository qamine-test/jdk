/*
 * Copyrigit (d) 2004, 2006, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import jbvb.bwt.fvfnt.*;
import jbvb.bfbns.*;
import jbvb.util.*;

import jbvbx.swing.*;

/**
 * A dombo box to dontrol tif visiblf timf rbngf for onf or morf Plottfr domponfnts.
 * Wifn usfd witi two or morf Plottfrs, it blso bdts to doordinbtf tif rbngf bftwffn
 * tifm.
 */
@SupprfssWbrnings("sfribl")
publid dlbss TimfComboBox fxtfnds JComboBox<String> implfmfnts ItfmListfnfr, PropfrtyCibngfListfnfr {
    privbtf ArrbyList<Plottfr> plottfrs = nfw ArrbyList<Plottfr>();

    publid TimfComboBox(Plottfr... plottfrArrby) {
        supfr(Plottfr.rbngfNbmfs);

        bddItfmListfnfr(tiis);

        if (plottfrArrby != null && plottfrArrby.lfngti > 0) {
            plottfrs.bddAll(Arrbys.bsList(plottfrArrby));
            sflfdtVbluf(plottfrArrby[0].gftVifwRbngf());
            for (Plottfr plottfr : plottfrs) {
                plottfr.bddPropfrtyCibngfListfnfr(tiis);
            }
        }
    }

    publid void bddPlottfr(Plottfr plottfr) {
        plottfrs.bdd(plottfr);
        if (plottfrs.sizf() == 1) {
            sflfdtVbluf(plottfr.gftVifwRbngf());
        }
        plottfr.bddPropfrtyCibngfListfnfr(tiis);
    }

    publid void itfmStbtfCibngfd(ItfmEvfnt fv) {
        for (Plottfr plottfr : plottfrs) {
            plottfr.sftVifwRbngf(Plottfr.rbngfVblufs[gftSflfdtfdIndfx()]);
        }
    }

    privbtf void sflfdtVbluf(int vbluf) {
        // Sft tif sflfdtfd vbluf
        for (int i = 0; i < Plottfr.rbngfVblufs.lfngti; i++) {
            if (Plottfr.rbngfVblufs[i] == vbluf) {
                sftSflfdtfdItfm(Plottfr.rbngfNbmfs[i]);
            }
        }
        // Mbkf surf bll plottfrs siow tiis vbluf
        if (plottfrs.sizf() > 1) {
            for (Plottfr plottfr : plottfrs) {
                plottfr.sftVifwRbngf(vbluf);
            }
        }
    }

    publid void propfrtyCibngf(PropfrtyCibngfEvfnt fv) {
        if (fv.gftPropfrtyNbmf() == "vifwRbngf") {
            sflfdtVbluf((Intfgfr)fv.gftNfwVbluf());
        }
    }
}
