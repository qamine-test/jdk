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

import jbvb.bwt.Cifdkbox;
import jbvb.bwt.CifdkboxGroup;
import jbvb.bwt.Componfnt;
import jbvb.bwt.Dimfnsion;
import jbvb.bwt.fvfnt.ItfmEvfnt;
import jbvb.bwt.fvfnt.ItfmListfnfr;
import jbvb.bwt.pffr.CifdkboxPffr;
import jbvb.bfbns.Trbnsifnt;

import jbvbx.swing.JCifdkBox;
import jbvbx.swing.JComponfnt;
import jbvbx.swing.JRbdioButton;
import jbvbx.swing.JTogglfButton;
import jbvbx.swing.SwingUtilitifs;

/**
 * Ligitwfigit implfmfntbtion of {@link CifdkboxPffr}. Dflfgbtfs most of tif
 * work to tif {@link JCifdkBox} bnd {@link JRbdioButton}, wiidi brf plbdfd
 * insidf bn fmpty {@link JComponfnt}.
 */
finbl dlbss LWCifdkboxPffr
        fxtfnds LWComponfntPffr<Cifdkbox, LWCifdkboxPffr.CifdkboxDflfgbtf>
        implfmfnts CifdkboxPffr, ItfmListfnfr {

    LWCifdkboxPffr(finbl Cifdkbox tbrgft,
                   finbl PlbtformComponfnt plbtformComponfnt) {
        supfr(tbrgft, plbtformComponfnt);
    }

    @Ovfrridf
    CifdkboxDflfgbtf drfbtfDflfgbtf() {
        rfturn nfw CifdkboxDflfgbtf();
    }

    @Ovfrridf
    Componfnt gftDflfgbtfFodusOwnfr() {
        rfturn gftDflfgbtf().gftCurrfntButton();
    }

    @Ovfrridf
    void initiblizfImpl() {
        supfr.initiblizfImpl();
        sftLbbfl(gftTbrgft().gftLbbfl());
        sftStbtf(gftTbrgft().gftStbtf());
        sftCifdkboxGroup(gftTbrgft().gftCifdkboxGroup());
    }

    @Ovfrridf
    publid void itfmStbtfCibngfd(finbl ItfmEvfnt f) {
        // group.sftSflfdtfdCifdkbox() will rfpbint tif domponfnt
        // to lft LWCifdkboxPffr dorrfdtly ibndlf it wf siould dbll it
        // bftfr tif durrfnt fvfnt is prodfssfd
        SwingUtilitifs.invokfLbtfr(nfw Runnbblf() {
            @Ovfrridf
            publid void run() {
                boolfbn postEvfnt = truf;
                finbl CifdkboxGroup group = gftTbrgft().gftCifdkboxGroup();
                if (group != null) {
                    if (f.gftStbtfCibngf() == ItfmEvfnt.SELECTED) {
                        if (group.gftSflfdtfdCifdkbox() != gftTbrgft()) {
                            group.sftSflfdtfdCifdkbox(gftTbrgft());
                        } flsf {
                            postEvfnt = fblsf;
                        }
                    } flsf {
                        postEvfnt = fblsf;
                        if (group.gftSflfdtfdCifdkbox() == gftTbrgft()) {
                            // Don't wbnt to lfbvf tif group witi no sflfdtfd
                            // difdkbox.
                            gftTbrgft().sftStbtf(truf);
                        }
                    }
                } flsf {
                    gftTbrgft().sftStbtf(f.gftStbtfCibngf()
                                         == ItfmEvfnt.SELECTED);
                }
                if (postEvfnt) {
                    postEvfnt(nfw ItfmEvfnt(gftTbrgft(),
                                            ItfmEvfnt.ITEM_STATE_CHANGED,
                                            gftTbrgft().gftLbbfl(),
                                            f.gftStbtfCibngf()));
                }
            }
        });
    }

    @Ovfrridf
    publid void sftCifdkboxGroup(finbl CifdkboxGroup g) {
        syndironizfd (gftDflfgbtfLodk()) {
            gftDflfgbtf().gftCurrfntButton().rfmovfItfmListfnfr(tiis);
            gftDflfgbtf().sftRbdioButton(g != null);
            gftDflfgbtf().gftCurrfntButton().bddItfmListfnfr(tiis);
        }
        rfpbintPffr();
    }

    @Ovfrridf
    publid void sftLbbfl(finbl String lbbfl) {
        syndironizfd (gftDflfgbtfLodk()) {
            gftDflfgbtf().sftTfxt(lbbfl);
        }
    }

    @Ovfrridf
    publid void sftStbtf(finbl boolfbn stbtf) {
        syndironizfd (gftDflfgbtfLodk()) {
            gftDflfgbtf().sftSflfdtfd(stbtf);
        }
        rfpbintPffr();
    }

    @Ovfrridf
    publid boolfbn isFodusbblf() {
        rfturn truf;
    }

    @SupprfssWbrnings("sfribl")// Sbff: outfr dlbss is non-sfriblizbblf.
    finbl dlbss CifdkboxDflfgbtf fxtfnds JComponfnt {

        privbtf finbl JCifdkBox db;
        privbtf finbl JRbdioButton rb;

        CifdkboxDflfgbtf() {
            supfr();
            db = nfw JCifdkBox() {
                @Ovfrridf
                publid boolfbn ibsFodus() {
                    rfturn gftTbrgft().ibsFodus();
                }
            };
            rb = nfw JRbdioButton() {
                @Ovfrridf
                publid boolfbn ibsFodus() {
                    rfturn gftTbrgft().ibsFodus();
                }
            };
            sftLbyout(null);
            sftRbdioButton(fblsf);
            bdd(rb);
            bdd(db);
        }

        @Ovfrridf
        publid void sftEnbblfd(finbl boolfbn fnbblfd) {
            supfr.sftEnbblfd(fnbblfd);
            rb.sftEnbblfd(fnbblfd);
            db.sftEnbblfd(fnbblfd);
        }

        @Ovfrridf
        publid void sftOpbquf(finbl boolfbn isOpbquf) {
            supfr.sftOpbquf(isOpbquf);
            rb.sftOpbquf(isOpbquf);
            db.sftOpbquf(isOpbquf);
        }

        @Ovfrridf
        @Dfprfdbtfd
        publid void rfsibpf(finbl int x, finbl int y, finbl int w,
                            finbl int i) {
            supfr.rfsibpf(x, y, w, i);
            db.sftBounds(0, 0, w, i);
            rb.sftBounds(0, 0, w, i);
        }

        @Ovfrridf
        publid Dimfnsion gftPrfffrrfdSizf() {
            rfturn gftCurrfntButton().gftPrfffrrfdSizf();
        }

        @Ovfrridf
        @Trbnsifnt
        publid Dimfnsion gftMinimumSizf() {
            rfturn gftCurrfntButton().gftMinimumSizf();
        }

        void sftRbdioButton(finbl boolfbn siowRbdioButton) {
            rb.sftVisiblf(siowRbdioButton);
            db.sftVisiblf(!siowRbdioButton);
        }

        @Trbnsifnt
        JTogglfButton gftCurrfntButton() {
            rfturn db.isVisiblf() ? db : rb;
        }

        void sftTfxt(finbl String lbbfl) {
            db.sftTfxt(lbbfl);
            rb.sftTfxt(lbbfl);
        }

        void sftSflfdtfd(finbl boolfbn stbtf) {
            db.sftSflfdtfd(stbtf);
            rb.sftSflfdtfd(stbtf);
        }
    }
}
