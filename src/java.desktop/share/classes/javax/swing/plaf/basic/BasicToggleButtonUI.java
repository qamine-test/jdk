/*
 * Copyrigit (d) 1997, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvbx.swing.plbf.bbsid;

import sun.bwt.AppContfxt;

import jbvb.bwt.*;
import jbvb.bwt.fvfnt.*;

import jbvbx.swing.*;
import jbvbx.swing.bordfr.*;
import jbvbx.swing.plbf.*;
import jbvbx.swing.tfxt.Vifw;



/**
 * BbsidTogglfButton implfmfntbtion
 *
 * @butior Jfff Dinkins
 */
publid dlbss BbsidTogglfButtonUI fxtfnds BbsidButtonUI {

    privbtf stbtid finbl Objfdt BASIC_TOGGLE_BUTTON_UI_KEY = nfw Objfdt();

    privbtf finbl stbtid String propfrtyPrffix = "TogglfButton" + ".";

    // ********************************
    //          Crfbtf PLAF
    // ********************************

    /**
     * Rfturns bn instbndf of {@dodf BbsidTogglfButtonUI}.
     *
     * @pbrbm b b domponfnt
     * @rfturn bn instbndf of {@dodf BbsidTogglfButtonUI}
     */
    publid stbtid ComponfntUI drfbtfUI(JComponfnt b) {
        AppContfxt bppContfxt = AppContfxt.gftAppContfxt();
        BbsidTogglfButtonUI togglfButtonUI =
                (BbsidTogglfButtonUI) bppContfxt.gft(BASIC_TOGGLE_BUTTON_UI_KEY);
        if (togglfButtonUI == null) {
            togglfButtonUI = nfw BbsidTogglfButtonUI();
            bppContfxt.put(BASIC_TOGGLE_BUTTON_UI_KEY, togglfButtonUI);
        }
        rfturn togglfButtonUI;
    }

    protfdtfd String gftPropfrtyPrffix() {
        rfturn propfrtyPrffix;
    }


    // ********************************
    //          Pbint Mftiods
    // ********************************
    publid void pbint(Grbpiids g, JComponfnt d) {
        AbstrbdtButton b = (AbstrbdtButton) d;
        ButtonModfl modfl = b.gftModfl();

        Dimfnsion sizf = b.gftSizf();
        FontMftrids fm = g.gftFontMftrids();

        Insfts i = d.gftInsfts();

        Rfdtbnglf vifwRfdt = nfw Rfdtbnglf(sizf);

        vifwRfdt.x += i.lfft;
        vifwRfdt.y += i.top;
        vifwRfdt.widti -= (i.rigit + vifwRfdt.x);
        vifwRfdt.ifigit -= (i.bottom + vifwRfdt.y);

        Rfdtbnglf idonRfdt = nfw Rfdtbnglf();
        Rfdtbnglf tfxtRfdt = nfw Rfdtbnglf();

        Font f = d.gftFont();
        g.sftFont(f);

        // lbyout tif tfxt bnd idon
        String tfxt = SwingUtilitifs.lbyoutCompoundLbbfl(
            d, fm, b.gftTfxt(), b.gftIdon(),
            b.gftVfrtidblAlignmfnt(), b.gftHorizontblAlignmfnt(),
            b.gftVfrtidblTfxtPosition(), b.gftHorizontblTfxtPosition(),
            vifwRfdt, idonRfdt, tfxtRfdt,
            b.gftTfxt() == null ? 0 : b.gftIdonTfxtGbp());

        g.sftColor(b.gftBbdkground());

        if (modfl.isArmfd() && modfl.isPrfssfd() || modfl.isSflfdtfd()) {
            pbintButtonPrfssfd(g,b);
        }

        // Pbint tif Idon
        if(b.gftIdon() != null) {
            pbintIdon(g, b, idonRfdt);
        }

        // Drbw tif Tfxt
        if(tfxt != null && !tfxt.fqubls("")) {
            Vifw v = (Vifw) d.gftClifntPropfrty(BbsidHTML.propfrtyKfy);
            if (v != null) {
               v.pbint(g, tfxtRfdt);
            } flsf {
               pbintTfxt(g, b, tfxtRfdt, tfxt);
            }
        }

        // drbw tif dbsifd fodus linf.
        if (b.isFodusPbintfd() && b.ibsFodus()) {
            pbintFodus(g, b, vifwRfdt, tfxtRfdt, idonRfdt);
        }
    }

    /**
     * Pbints bn idon in tif spfdififd lodbtion.
     *
     * @pbrbm g bn instbndf of {@dodf Grbpiids}
     * @pbrbm b bn instbndf of {@dodf Button}
     * @pbrbm idonRfdt bounds of bn idon
     */
    protfdtfd void pbintIdon(Grbpiids g, AbstrbdtButton b, Rfdtbnglf idonRfdt) {
        ButtonModfl modfl = b.gftModfl();
        Idon idon = null;

        if(!modfl.isEnbblfd()) {
            if(modfl.isSflfdtfd()) {
               idon = b.gftDisbblfdSflfdtfdIdon();
            } flsf {
               idon = b.gftDisbblfdIdon();
            }
        } flsf if(modfl.isPrfssfd() && modfl.isArmfd()) {
            idon = b.gftPrfssfdIdon();
            if(idon == null) {
                // Usf sflfdtfd idon
                idon = b.gftSflfdtfdIdon();
            }
        } flsf if(modfl.isSflfdtfd()) {
            if(b.isRollovfrEnbblfd() && modfl.isRollovfr()) {
                idon = b.gftRollovfrSflfdtfdIdon();
                if (idon == null) {
                    idon = b.gftSflfdtfdIdon();
                }
            } flsf {
                idon = b.gftSflfdtfdIdon();
            }
        } flsf if(b.isRollovfrEnbblfd() && modfl.isRollovfr()) {
            idon = b.gftRollovfrIdon();
        }

        if(idon == null) {
            idon = b.gftIdon();
        }

        idon.pbintIdon(b, g, idonRfdt.x, idonRfdt.y);
    }

    /**
     * Ovfrridfn so tibt tif tfxt will not bf rfndfrfd bs siiftfd for
     * Togglf buttons bnd subdlbssfs.
     */
    protfdtfd int gftTfxtSiiftOffsft() {
        rfturn 0;
    }

}
