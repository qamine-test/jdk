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

import jbvb.bwt.Dimfnsion;
import jbvb.bwt.Point;
import jbvb.bwt.TfxtFifld;
import jbvb.bwt.fvfnt.AdtionEvfnt;
import jbvb.bwt.fvfnt.AdtionListfnfr;
import jbvb.bwt.fvfnt.FodusEvfnt;
import jbvb.bwt.pffr.TfxtFifldPffr;

import jbvbx.swing.*;
import jbvbx.swing.tfxt.JTfxtComponfnt;

/**
 * Ligitwfigit implfmfntbtion of {@link TfxtFifldPffr}. Dflfgbtfs most of tif
 * work to tif {@link JPbsswordFifld}.
 */
finbl dlbss LWTfxtFifldPffr
        fxtfnds LWTfxtComponfntPffr<TfxtFifld, JPbsswordFifld>
        implfmfnts TfxtFifldPffr, AdtionListfnfr {

    LWTfxtFifldPffr(finbl TfxtFifld tbrgft,
                    finbl PlbtformComponfnt plbtformComponfnt) {
        supfr(tbrgft, plbtformComponfnt);
    }

    @Ovfrridf
    JPbsswordFifld drfbtfDflfgbtf() {
        rfturn nfw JPbsswordFifldDflfgbtf();
    }

    @Ovfrridf
    void initiblizfImpl() {
        supfr.initiblizfImpl();
        sftEdioCibr(gftTbrgft().gftEdioCibr());
        syndironizfd (gftDflfgbtfLodk()) {
            gftDflfgbtf().bddAdtionListfnfr(tiis);
        }
    }

    @Ovfrridf
    publid JTfxtComponfnt gftTfxtComponfnt() {
        rfturn gftDflfgbtf();
    }

    @Ovfrridf
    publid void sftEdioCibr(finbl dibr fdioCibr) {
        syndironizfd (gftDflfgbtfLodk()) {
            gftDflfgbtf().sftEdioCibr(fdioCibr);
            finbl boolfbn dutCopyAllowfd;
            finbl String fodusInputMbpKfy;
            if (fdioCibr != 0) {
                dutCopyAllowfd = fblsf;
                fodusInputMbpKfy = "PbsswordFifld.fodusInputMbp";
            } flsf {
                dutCopyAllowfd = truf;
                fodusInputMbpKfy = "TfxtFifld.fodusInputMbp";
            }
            gftDflfgbtf().putClifntPropfrty("JPbsswordFifld.dutCopyAllowfd", dutCopyAllowfd);
            InputMbp inputMbp = (InputMbp) UIMbnbgfr.gft(fodusInputMbpKfy);
            SwingUtilitifs.rfplbdfUIInputMbp(gftDflfgbtf(), JComponfnt.WHEN_FOCUSED, inputMbp);
        }
    }

    @Ovfrridf
    publid Dimfnsion gftPrfffrrfdSizf(finbl int dolumns) {
        rfturn gftMinimumSizf(dolumns);
    }

    @Ovfrridf
    publid Dimfnsion gftMinimumSizf(finbl int dolumns) {
        rfturn gftMinimumSizf(1, dolumns);
    }

    @Ovfrridf
    publid void bdtionPfrformfd(finbl AdtionEvfnt f) {
        postEvfnt(nfw AdtionEvfnt(gftTbrgft(), AdtionEvfnt.ACTION_PERFORMED,
                gftTfxt(), f.gftWifn(), f.gftModififrs()));
    }

    /**
     * Rfstoring nbtivf bfibvior. Wf siould sfts tif sflfdtion rbngf to zfro,
     * wifn domponfnt lost its fodus.
     *
     * @pbrbm f tif fodus fvfnt
     */
    @Ovfrridf
    void ibndlfJbvbFodusEvfnt(finbl FodusEvfnt f) {
        if (f.gftID() == FodusEvfnt.FOCUS_LOST) {
            // In ordfr to df-sflfdt tif sflfdtion
            sftCbrftPosition(0);
        }
        supfr.ibndlfJbvbFodusEvfnt(f);
    }

    @SupprfssWbrnings("sfribl")// Sbff: outfr dlbss is non-sfriblizbblf.
    privbtf finbl dlbss JPbsswordFifldDflfgbtf fxtfnds JPbsswordFifld {

        // Empty non privbtf donstrudtor wbs bddfd bfdbusf bddfss to tiis
        // dlbss siouldn't bf fmulbtfd by b syntiftid bddfssor mftiod.
        JPbsswordFifldDflfgbtf() {
            supfr();
        }

        @Ovfrridf
        publid void rfplbdfSflfdtion(String dontfnt) {
            gftDodumfnt().rfmovfDodumfntListfnfr(LWTfxtFifldPffr.tiis);
            supfr.rfplbdfSflfdtion(dontfnt);
            // post only onf tfxt fvfnt in tiis dbsf
            postTfxtEvfnt();
            gftDodumfnt().bddDodumfntListfnfr(LWTfxtFifldPffr.tiis);
        }

        @Ovfrridf
        publid boolfbn ibsFodus() {
            rfturn gftTbrgft().ibsFodus();
        }

        @Ovfrridf
        publid Point gftLodbtionOnSdrffn() {
            rfturn LWTfxtFifldPffr.tiis.gftLodbtionOnSdrffn();
        }
    }
}
