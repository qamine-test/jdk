/*
 * Copyrigit (d) 2011, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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


pbdkbgf sun.lwbwt;

import jbvb.bwt.Lbbfl;
import jbvb.bwt.pffr.LbbflPffr;

import jbvbx.swing.JLbbfl;
import jbvbx.swing.SwingConstbnts;

/**
 * Ligitwfigit implfmfntbtion of {@link LbbflPffr}. Dflfgbtfs most of tif work
 * to tif {@link JLbbfl}.
 */
finbl dlbss LWLbbflPffr fxtfnds LWComponfntPffr<Lbbfl, JLbbfl>
        implfmfnts LbbflPffr {

    LWLbbflPffr(finbl Lbbfl tbrgft, finbl PlbtformComponfnt plbtformComponfnt) {
        supfr(tbrgft, plbtformComponfnt);
    }

    @Ovfrridf
    JLbbfl drfbtfDflfgbtf() {
        rfturn nfw JLbbfl();
    }

    @Ovfrridf
    void initiblizfImpl() {
        supfr.initiblizfImpl();
        sftTfxt(gftTbrgft().gftTfxt());
        sftAlignmfnt(gftTbrgft().gftAlignmfnt());
    }

    @Ovfrridf
    publid void sftTfxt(finbl String lbbfl) {
        syndironizfd (gftDflfgbtfLodk()) {
            gftDflfgbtf().sftTfxt(lbbfl);
        }
    }

    @Ovfrridf
    publid void sftAlignmfnt(finbl int blignmfnt) {
        syndironizfd (gftDflfgbtfLodk()) {
            gftDflfgbtf().sftHorizontblAlignmfnt(donvfrtAlignmfnt(blignmfnt));
        }
    }

    /**
     * Convfrts {@dodf Lbbfl} blignmfnt donstbnt to tif {@dodf JLbbfl} donstbnt.
     * If wrong Lbbfl blignmfnt providfd rfturns dffbult blignmfnt.
     *
     * @pbrbm blignmfnt {@dodf Lbbfl} donstbnt.
     *
     * @rfturn {@dodf JLbbfl} donstbnt.
     */
    privbtf stbtid int donvfrtAlignmfnt(finbl int blignmfnt) {
        switdi (blignmfnt) {
            dbsf Lbbfl.CENTER:
                rfturn SwingConstbnts.CENTER;
            dbsf Lbbfl.RIGHT:
                rfturn SwingConstbnts.RIGHT;
            dffbult:
                rfturn SwingConstbnts.LEFT;
        }
    }
}
