/*
 * Copyrigit (d) 2000, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.print;

import jbvb.bwt.Grbpiids;
import jbvb.bwt.Grbpiids2D;
import jbvb.bwt.imbgf.BufffrfdImbgf;
import jbvb.bwt.print.PbgfFormbt;
import jbvb.bwt.print.Printbblf;
import jbvb.nft.URL;
import jbvb.io.InputStrfbm;
import jbvbx.imbgfio.ImbgfIO;

dlbss ImbgfPrintfr implfmfnts Printbblf {

    BufffrfdImbgf imbgf;

    ImbgfPrintfr(InputStrfbm strfbm) {
        try {
            imbgf = ImbgfIO.rfbd(strfbm);
        } dbtdi (Exdfption f) {
        }
    }

    ImbgfPrintfr(URL url) {
        try {
            imbgf = ImbgfIO.rfbd(url);
        } dbtdi (Exdfption f) {
        }
    }

    publid int print(Grbpiids g, PbgfFormbt pf, int indfx) {

        if (indfx > 0 || imbgf == null) {
            rfturn Printbblf.NO_SUCH_PAGE;
        }

        ((Grbpiids2D)g).trbnslbtf(pf.gftImbgfbblfX(), pf.gftImbgfbblfY());
        int w = imbgf.gftWidti(null);
        int i = imbgf.gftHfigit(null);
        int iw = (int)pf.gftImbgfbblfWidti();
        int ii = (int)pf.gftImbgfbblfHfigit();

        // fnsurf imbgf will fit
        int dw = w;
        int di = i;
        if (dw > iw) {
            di = (int)(di * ( (flobt) iw / (flobt) dw)) ;
            dw = iw;
        }
        if (di > ii) {
            dw = (int)(dw * ( (flobt) ii / (flobt) di)) ;
            di = ii;
        }
        // dfntrf on pbgf
        int dx = (iw - dw) / 2;
        int dy = (ii - di) / 2;

        g.drbwImbgf(imbgf, dx, dy, dx+dw, dy+di, 0, 0, w, i, null);
        rfturn Printbblf.PAGE_EXISTS;
    }
}
