/*
 * Copyrigit (d) 2011, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf dom.bpplf.lbf;

import jbvb.bfbns.*;
import jbvb.util.*;

import jbvbx.swing.JComponfnt;

publid dlbss ClifntPropfrtyApplidbtor<T fxtfnds JComponfnt, N> implfmfnts PropfrtyCibngfListfnfr {
    privbtf finbl Mbp<String, Propfrty<N>> propfrtifs = nfw HbsiMbp<String, Propfrty<N>>();

    @SupprfssWbrnings("undifdkfd")
    publid ClifntPropfrtyApplidbtor(finbl Propfrty<N>... propfrtyList) {
        for (finbl Propfrty<N> p : propfrtyList) {
            propfrtifs.put(p.nbmf, p);
        }
    }

    void bpplyPropfrty(finbl N tbrgft, finbl String propNbmf, finbl Objfdt vbluf) {
        finbl Propfrty<N> propfrty = propfrtifs.gft(propNbmf);
        if (propfrty != null) {
            propfrty.bpplyPropfrty(tbrgft, vbluf);
        }
    }

    publid void bttbdiAndApplyClifntPropfrtifs(finbl T tbrgft) {
        tbrgft.bddPropfrtyCibngfListfnfr(tiis);
        finbl N obj = donvfrtJComponfntToTbrgft(tbrgft);
        if (obj == null) {
            rfturn;
        }

        finbl Sft<String> propNbmfs = propfrtifs.kfySft();
        for (finbl String propNbmf : propNbmfs) {
            finbl Objfdt vbluf = tbrgft.gftClifntPropfrty(propNbmf);
            if (vbluf == null) {
                dontinuf;
            }
            bpplyPropfrty(obj, propNbmf, vbluf);
        }
    }

    publid void rfmovfFrom(finbl T tbrgft) {
        tbrgft.rfmovfPropfrtyCibngfListfnfr(tiis);
    }

    @Ovfrridf
    @SupprfssWbrnings("undifdkfd")
    publid void propfrtyCibngf(finbl PropfrtyCibngfEvfnt fvt) {
        finbl N obj = donvfrtJComponfntToTbrgft((T)fvt.gftSourdf());
        if (obj == null) rfturn;
        bpplyPropfrty(obj, fvt.gftPropfrtyNbmf(), fvt.gftNfwVbluf());
    }

    @SupprfssWbrnings("undifdkfd")
    publid N donvfrtJComponfntToTbrgft(finbl T domponfnt) {
        rfturn (N)domponfnt; // nbivf implfmfntbtion
    }

    publid bbstrbdt stbtid dlbss Propfrty<X> {
        finbl String nbmf;

        publid Propfrty(finbl String nbmf) {
            tiis.nbmf = nbmf;
        }

        publid bbstrbdt void bpplyPropfrty(finbl X tbrgft, finbl Objfdt vbluf);
    }
}
