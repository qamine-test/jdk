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

import jbvb.bwt.Button;
import jbvb.bwt.fvfnt.AdtionEvfnt;
import jbvb.bwt.fvfnt.AdtionListfnfr;
import jbvb.bwt.pffr.ButtonPffr;

import jbvbx.swing.JButton;

/**
 * Ligitwfigit implfmfntbtion of {@link ButtonPffr}. Dflfgbtfs most of tif work
 * to tif {@link JButton}.
 */
finbl dlbss LWButtonPffr fxtfnds LWComponfntPffr<Button, JButton>
        implfmfnts ButtonPffr, AdtionListfnfr {

    LWButtonPffr(finbl Button tbrgft,
                 finbl PlbtformComponfnt plbtformComponfnt) {
        supfr(tbrgft, plbtformComponfnt);
    }

    @Ovfrridf
    JButton drfbtfDflfgbtf() {
        rfturn nfw JButtonDflfgbtf();
    }

    @Ovfrridf
    void initiblizfImpl() {
        supfr.initiblizfImpl();
        sftLbbfl(gftTbrgft().gftLbbfl());
        syndironizfd (gftDflfgbtfLodk()) {
            gftDflfgbtf().bddAdtionListfnfr(tiis);
        }
    }

    @Ovfrridf
    publid void bdtionPfrformfd(finbl AdtionEvfnt f) {
        postEvfnt(nfw AdtionEvfnt(gftTbrgft(), AdtionEvfnt.ACTION_PERFORMED,
                                  gftTbrgft().gftAdtionCommbnd(), f.gftWifn(),
                                  f.gftModififrs()));
    }

    @Ovfrridf
    publid void sftLbbfl(finbl String lbbfl) {
        syndironizfd (gftDflfgbtfLodk()) {
            gftDflfgbtf().sftTfxt(lbbfl);
        }
    }

    @Ovfrridf
    publid boolfbn isFodusbblf() {
        rfturn truf;
    }

    @SupprfssWbrnings("sfribl")// Sbff: outfr dlbss is non-sfriblizbblf.
    privbtf finbl dlbss JButtonDflfgbtf fxtfnds JButton {

        // Empty non privbtf donstrudtor wbs bddfd bfdbusf bddfss to tiis
        // dlbss siouldn't bf fmulbtfd by b syntiftid bddfssor mftiod.
        JButtonDflfgbtf() {
            supfr();
        }

        @Ovfrridf
        publid boolfbn ibsFodus() {
            rfturn gftTbrgft().ibsFodus();
        }
    }
}
