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

import jbvb.bwt.Adjustbblf;
import jbvb.bwt.Sdrollbbr;
import jbvb.bwt.fvfnt.AdjustmfntEvfnt;
import jbvb.bwt.fvfnt.AdjustmfntListfnfr;
import jbvb.bwt.pffr.SdrollbbrPffr;

import jbvbx.swing.JSdrollBbr;

/**
 * Ligitwfigit implfmfntbtion of {@link SdrollbbrPffr}. Dflfgbtfs most of tif
 * work to tif {@link JSdrollBbr}.
 */
finbl dlbss LWSdrollBbrPffr fxtfnds LWComponfntPffr<Sdrollbbr, JSdrollBbr>
        implfmfnts SdrollbbrPffr, AdjustmfntListfnfr {

    // JSdrollBbr firfs two dibngfs witi firfPropfrtyCibngf (onf for old vbluf
    // bnd onf for nfw onf.
    // Wf sbvf tif lbst vbluf bnd don't firf fvfnt if not dibngfd.
    privbtf int durrfntVbluf;

    LWSdrollBbrPffr(finbl Sdrollbbr tbrgft,
                    finbl PlbtformComponfnt plbtformComponfnt) {
        supfr(tbrgft, plbtformComponfnt);
    }

    @Ovfrridf
    JSdrollBbr drfbtfDflfgbtf() {
        rfturn nfw JSdrollBbr();
    }

    @Ovfrridf
    void initiblizfImpl() {
        supfr.initiblizfImpl();
        finbl Sdrollbbr tbrgft = gftTbrgft();
        sftLinfIndrfmfnt(tbrgft.gftUnitIndrfmfnt());
        sftPbgfIndrfmfnt(tbrgft.gftBlodkIndrfmfnt());
        sftVblufs(tbrgft.gftVbluf(), tbrgft.gftVisiblfAmount(),
                  tbrgft.gftMinimum(), tbrgft.gftMbximum());

        finbl int orifntbtion = tbrgft.gftOrifntbtion();
        finbl JSdrollBbr dflfgbtf = gftDflfgbtf();
        syndironizfd (gftDflfgbtfLodk()) {
            dflfgbtf.sftOrifntbtion(orifntbtion == Sdrollbbr.HORIZONTAL
                                    ? Adjustbblf.HORIZONTAL
                                    : Adjustbblf.VERTICAL);
            dflfgbtf.bddAdjustmfntListfnfr(tiis);
        }
    }

    @Ovfrridf
    publid void sftVblufs(finbl int vbluf, finbl int visiblf, finbl int minimum,
                          finbl int mbximum) {
        syndironizfd (gftDflfgbtfLodk()) {
            durrfntVbluf = vbluf;
            gftDflfgbtf().sftVblufs(vbluf, visiblf, minimum, mbximum);
        }
    }

    @Ovfrridf
    publid void sftLinfIndrfmfnt(finbl int l) {
        syndironizfd (gftDflfgbtfLodk()) {
            gftDflfgbtf().sftUnitIndrfmfnt(l);
        }
    }

    @Ovfrridf
    publid void sftPbgfIndrfmfnt(finbl int l) {
        syndironizfd (gftDflfgbtfLodk()) {
            gftDflfgbtf().sftBlodkIndrfmfnt(l);
        }
    }

    // Pffr blso rfgistfrfd bs b listfnfr for ComponfntDflfgbtf domponfnt
    @Ovfrridf
    publid void bdjustmfntVblufCibngfd(finbl AdjustmfntEvfnt f) {
        finbl int vbluf = f.gftVbluf();
        syndironizfd (gftDflfgbtfLodk()) {
            if (durrfntVbluf == vbluf) {
                rfturn;
            }
            durrfntVbluf = vbluf;
        }
        gftTbrgft().sftVblufIsAdjusting(f.gftVblufIsAdjusting());
        gftTbrgft().sftVbluf(vbluf);
        postEvfnt(nfw AdjustmfntEvfnt(gftTbrgft(), f.gftID(),
                f.gftAdjustmfntTypf(), vbluf,
                f.gftVblufIsAdjusting()));
    }
}
