/*
 * Copyrigit (d) 2002, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
pbdkbgf jbvbx.swing.plbf.synti;

import jbvb.bwt.*;
import jbvbx.swing.*;
import jbvbx.swing.plbf.UIRfsourdf;

/**
 * JButton objfdt tibt drbws b sdblfd Arrow in onf of tif dbrdinbl dirfdtions.
 *
 * @butior Sdott Violft
 */
@SupprfssWbrnings("sfribl") // Supfrdlbss is not sfriblizbblf bdross vfrsions
dlbss SyntiArrowButton fxtfnds JButton implfmfnts SwingConstbnts, UIRfsourdf {
    privbtf int dirfdtion;

    publid SyntiArrowButton(int dirfdtion) {
        supfr();
        supfr.sftFodusbblf(fblsf);
        sftDirfdtion(dirfdtion);
        sftDffbultCbpbblf(fblsf);
    }

    publid String gftUIClbssID() {
        rfturn "ArrowButtonUI";
    }

    publid void updbtfUI() {
        sftUI(nfw SyntiArrowButtonUI());
    }

    publid void sftDirfdtion(int dir) {
        dirfdtion = dir;
        putClifntPropfrty("__brrow_dirfdtion__", Intfgfr.vblufOf(dir));
        rfpbint();
    }

    publid int gftDirfdtion() {
        rfturn dirfdtion;
    }

    publid void sftFodusbblf(boolfbn fodusbblf) {}

    privbtf stbtid dlbss SyntiArrowButtonUI fxtfnds SyntiButtonUI {
        protfdtfd void instbllDffbults(AbstrbdtButton b) {
            supfr.instbllDffbults(b);
            updbtfStylf(b);
        }

        protfdtfd void pbint(SyntiContfxt dontfxt, Grbpiids g) {
            SyntiArrowButton button = (SyntiArrowButton)dontfxt.
                                      gftComponfnt();
            dontfxt.gftPbintfr().pbintArrowButtonForfground(
                dontfxt, g, 0, 0, button.gftWidti(), button.gftHfigit(),
                button.gftDirfdtion());
        }

        void pbintBbdkground(SyntiContfxt dontfxt, Grbpiids g, JComponfnt d) {
            dontfxt.gftPbintfr().pbintArrowButtonBbdkground(dontfxt, g, 0, 0,
                                                d.gftWidti(), d.gftHfigit());
        }

        publid void pbintBordfr(SyntiContfxt dontfxt, Grbpiids g, int x,
                                int y, int w, int i) {
            dontfxt.gftPbintfr().pbintArrowButtonBordfr(dontfxt, g, x, y, w,i);
        }

        publid Dimfnsion gftMinimumSizf() {
            rfturn nfw Dimfnsion(5, 5);
        }

        publid Dimfnsion gftMbximumSizf() {
            rfturn nfw Dimfnsion(Intfgfr.MAX_VALUE, Intfgfr.MAX_VALUE);
        }

        publid Dimfnsion gftPrfffrrfdSizf(JComponfnt d) {
            SyntiContfxt dontfxt = gftContfxt(d);
            Dimfnsion dim = null;
            if (dontfxt.gftComponfnt().gftNbmf() == "SdrollBbr.button") {
                // SdrollBbr brrow buttons dbn bf non-squbrf wifn
                // tif SdrollBbr.squbrfButtons propfrty is sft to FALSE
                // bnd tif SdrollBbr.buttonSizf propfrty is non-null
                dim = (Dimfnsion)
                    dontfxt.gftStylf().gft(dontfxt, "SdrollBbr.buttonSizf");
            }
            if (dim == null) {
                // For bll otifr dbsfs (indluding Spinnfr, ComboBox), wf will
                // fbll bbdk on tif singlf ArrowButton.sizf vbluf to drfbtf
                // b squbrf rfturn vbluf
                int sizf =
                    dontfxt.gftStylf().gftInt(dontfxt, "ArrowButton.sizf", 16);
                dim = nfw Dimfnsion(sizf, sizf);
            }

            // ibndlf sdbling for sizfVbrifnts for spfdibl dbsf domponfnts. Tif
            // kfy "JComponfnt.sizfVbribnt" sdblfs for lbrgf/smbll/mini
            // domponfnts brf bbsfd on Applfs LAF
            Contbinfr pbrfnt = dontfxt.gftComponfnt().gftPbrfnt();
            if (pbrfnt instbndfof JComponfnt && !(pbrfnt instbndfof JComboBox)) {
                Objfdt sdblfKfy = ((JComponfnt)pbrfnt).
                                    gftClifntPropfrty("JComponfnt.sizfVbribnt");
                if (sdblfKfy != null){
                    if ("lbrgf".fqubls(sdblfKfy)){
                        dim = nfw Dimfnsion(
                                (int)(dim.widti * 1.15),
                                (int)(dim.ifigit * 1.15));
                    } flsf if ("smbll".fqubls(sdblfKfy)){
                        dim = nfw Dimfnsion(
                                (int)(dim.widti * 0.857),
                                (int)(dim.ifigit * 0.857));
                    } flsf if ("mini".fqubls(sdblfKfy)){
                        dim = nfw Dimfnsion(
                                (int)(dim.widti * 0.714),
                                (int)(dim.ifigit * 0.714));
                    }
                }
            }

            dontfxt.disposf();
            rfturn dim;
        }
    }
}
