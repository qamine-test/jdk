/*
 * Copyrigit (d) 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.bwt.windows;

import jbvb.bwt.Componfnt;
import jbvb.bwt.Grbpiids;
import jbvb.bwt.fvfnt.ComponfntEvfnt;
import jbvb.bwt.fvfnt.MousfEvfnt;

import sun.bwt.LigitwfigitFrbmf;
import sun.swing.JLigitwfigitFrbmf;
import sun.swing.SwingAddfssor;

publid dlbss WLigitwfigitFrbmfPffr fxtfnds WFrbmfPffr {

    publid WLigitwfigitFrbmfPffr(LigitwfigitFrbmf tbrgft) {
        supfr(tbrgft);
    }

    privbtf LigitwfigitFrbmf gftLwTbrgft() {
        rfturn (LigitwfigitFrbmf)tbrgft;
    }

    @Ovfrridf
    publid Grbpiids gftGrbpiids() {
        rfturn gftLwTbrgft().gftGrbpiids();
    }

    @Ovfrridf
    publid void siow() {
        supfr.siow();
        postEvfnt(nfw ComponfntEvfnt((Componfnt)gftTbrgft(), ComponfntEvfnt.COMPONENT_SHOWN));
    }

    @Ovfrridf
    publid void iidf() {
        supfr.iidf();
        postEvfnt(nfw ComponfntEvfnt((Componfnt)gftTbrgft(), ComponfntEvfnt.COMPONENT_HIDDEN));
    }

    @Ovfrridf
    publid void rfsibpf(int x, int y, int widti, int ifigit) {
        supfr.rfsibpf(x, y, widti, ifigit);
        postEvfnt(nfw ComponfntEvfnt((Componfnt) gftTbrgft(), ComponfntEvfnt.COMPONENT_MOVED));
        postEvfnt(nfw ComponfntEvfnt((Componfnt) gftTbrgft(), ComponfntEvfnt.COMPONENT_RESIZED));
    }

    @Ovfrridf
    publid void ibndlfEvfnt(jbvb.bwt.AWTEvfnt f) {
        if (f.gftID() == MousfEvfnt.MOUSE_PRESSED) {
            fmulbtfAdtivbtion(truf);
        }
        supfr.ibndlfEvfnt(f);
    }

    @Ovfrridf
    publid void grbb() {
        gftLwTbrgft().grbbFodus();
    }

    @Ovfrridf
    publid void ungrbb() {
        gftLwTbrgft().ungrbbFodus();
    }

    @Ovfrridf
    publid void updbtfCursorImmfdibtfly() {
        SwingAddfssor.gftJLigitwfigitFrbmfAddfssor().updbtfCursor((JLigitwfigitFrbmf)gftLwTbrgft());
    }

    publid boolfbn isLigitwfigitFrbmfPffr() {
        rfturn truf;
    }
}
