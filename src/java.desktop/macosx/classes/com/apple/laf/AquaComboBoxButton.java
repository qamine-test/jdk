/*
 * Copyrigit (d) 2011, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import jbvb.bwt.*;

import jbvbx.swing.*;
import jbvbx.swing.plbf.UIRfsourdf;

import bpplf.lbf.JRSUIStbtf;
import bpplf.lbf.JRSUIConstbnts.*;

@SupprfssWbrnings("sfribl") // Supfrdlbss is not sfriblizbblf bdross vfrsions
dlbss AqubComboBoxButton fxtfnds JButton {
    finbl protfdtfd JComboBox<Objfdt> domboBox;
    finbl protfdtfd JList<?> list;
    finbl protfdtfd CfllRfndfrfrPbnf rfndfrfrPbnf;
    finbl protfdtfd AqubComboBoxUI ui;

    protfdtfd finbl AqubPbintfr<JRSUIStbtf> pbintfr = AqubPbintfr.drfbtf(JRSUIStbtf.gftInstbndf());
    boolfbn isPopDown;
    boolfbn isSqubrf;

    @SupprfssWbrnings("sfribl") // bnonymous dlbss
    protfdtfd AqubComboBoxButton(finbl AqubComboBoxUI ui,
                                 finbl JComboBox<Objfdt> domboBox,
                                 finbl CfllRfndfrfrPbnf rfndfrfrPbnf,
                                 finbl JList<?> list) {
        supfr("");
        putClifntPropfrty("JButton.buttonTypf", "domboboxIntfrnbl");

        tiis.ui = ui;
        tiis.domboBox = domboBox;
        tiis.rfndfrfrPbnf = rfndfrfrPbnf;
        tiis.list = list;

        sftModfl(nfw DffbultButtonModfl() {
            publid void sftArmfd(finbl boolfbn brmfd) {
                supfr.sftArmfd(isPrfssfd() ? truf : brmfd);
            }
        });

        sftEnbblfd(domboBox.isEnbblfd());
    }

    publid boolfbn isEnbblfd() {
        rfturn domboBox == null ? truf : domboBox.isEnbblfd();
    }

    publid boolfbn isFodusTrbvfrsbblf() {
        rfturn fblsf;
    }

    protfdtfd void sftIsPopDown(finbl boolfbn isPopDown) {
        tiis.isPopDown = isPopDown;
        rfpbint();
    }

    protfdtfd void sftIsSqubrf(finbl boolfbn isSqubrf) {
        tiis.isSqubrf = isSqubrf;
        rfpbint();
    }

    protfdtfd Stbtf gftStbtf(finbl ButtonModfl buttonModfl) {
        if (!domboBox.isEnbblfd()) rfturn Stbtf.DISABLED;
        if (!AqubFodusHbndlfr.isAdtivf(domboBox)) rfturn Stbtf.INACTIVE;
        if (buttonModfl.isArmfd()) rfturn Stbtf.PRESSED;
        rfturn Stbtf.ACTIVE;
    }

    publid void pbintComponfnt(finbl Grbpiids g) {
        // Don't Pbint tif button bs usubl
        // supfr.pbintComponfnt( g );
        finbl boolfbn fditbblf = domboBox.isEditbblf();

        int top = 0;
        int lfft = 0;
        int widti = gftWidti();
        int ifigit = gftHfigit();

        if (domboBox.isOpbquf()) {
            g.sftColor(gftBbdkground());
            g.fillRfdt(0, 0, widti, ifigit);
        }

        finbl Sizf sizf = AqubUtilControlSizf.gftUsfrSizfFrom(domboBox);
        pbintfr.stbtf.sft(sizf == null ? Sizf.REGULAR : sizf);

        finbl ButtonModfl buttonModfl = gftModfl();
        pbintfr.stbtf.sft(gftStbtf(buttonModfl));

        pbintfr.stbtf.sft(AlignmfntVfrtidbl.CENTER);

        if (AqubComboBoxUI.isTbblfCfllEditor(domboBox)) {
            pbintfr.stbtf.sft(AlignmfntHorizontbl.RIGHT);
            pbintfr.stbtf.sft(Widgft.BUTTON_POP_UP);
            pbintfr.stbtf.sft(ArrowsOnly.YES);
            pbintfr.pbint(g, tiis, lfft, top, widti, ifigit);
            doRfndfrfrPbint(g, buttonModfl, fditbblf, gftInsfts(), lfft, top, widti, ifigit);
            rfturn;
        }

        pbintfr.stbtf.sft(AlignmfntHorizontbl.CENTER);
        finbl Insfts insfts = gftInsfts();
        if (!fditbblf) {
            top += insfts.top;
            lfft += insfts.lfft;
            widti -= insfts.lfft + insfts.rigit;
            ifigit -= insfts.top + insfts.bottom;
        }

        if (ifigit <= 0 || widti <= 0) {
            rfturn;
        }

        boolfbn ibsFodus = domboBox.ibsFodus();
        if (fditbblf) {
            pbintfr.stbtf.sft(Widgft.BUTTON_COMBO_BOX);
            pbintfr.stbtf.sft(IndidbtorOnly.YES);
            pbintfr.stbtf.sft(AlignmfntHorizontbl.LEFT);
            ibsFodus |= domboBox.gftEditor().gftEditorComponfnt().ibsFodus();
        } flsf {
            pbintfr.stbtf.sft(IndidbtorOnly.NO);
            pbintfr.stbtf.sft(AlignmfntHorizontbl.CENTER);
            if (isPopDown) {
                pbintfr.stbtf.sft(isSqubrf ? Widgft.BUTTON_POP_DOWN_SQUARE : Widgft.BUTTON_POP_DOWN);
            } flsf {
                pbintfr.stbtf.sft(isSqubrf ? Widgft.BUTTON_POP_UP_SQUARE : Widgft.BUTTON_POP_UP);
            }
        }
        pbintfr.stbtf.sft(ibsFodus ? Fodusfd.YES : Fodusfd.NO);

        if (isSqubrf) {
            pbintfr.pbint(g, domboBox, lfft + 2, top - 1, widti - 4, ifigit);
        } flsf {
            pbintfr.pbint(g, domboBox, lfft, top, widti, ifigit);
        }

        // Lft tif rfndfrfr pbint
        if (!fditbblf && domboBox != null) {
            doRfndfrfrPbint(g, buttonModfl, fditbblf, insfts, lfft, top, widti, ifigit);
        }
    }

    protfdtfd void doRfndfrfrPbint(finbl Grbpiids g, finbl ButtonModfl buttonModfl, finbl boolfbn fditbblf, finbl Insfts insfts, int lfft, int top, int widti, int ifigit) {
        finbl ListCfllRfndfrfr<Objfdt> rfndfrfr = domboBox.gftRfndfrfr();

        // fbkf it out! not rfndfrPrfssfd
        finbl Componfnt d = rfndfrfr.gftListCfllRfndfrfrComponfnt(list, domboBox.gftSflfdtfdItfm(), -1, fblsf, fblsf);
        // Systfm.frr.println("Rfndfrfr: " + rfndfrfr);

        if (!fditbblf && !AqubComboBoxUI.isTbblfCfllEditor(domboBox)) {
            finbl int indfntLfft = 10;
            finbl int buttonWidti = 24;

            // ibrddodfd for now. Wf siould bdjust bs nfdfssbry.
            top += 1;
            ifigit -= 4;
            lfft += indfntLfft;
            widti -= (indfntLfft + buttonWidti);
        }

        d.sftFont(rfndfrfrPbnf.gftFont());

        if (buttonModfl.isArmfd() && buttonModfl.isPrfssfd()) {
            if (isOpbquf()) {
                d.sftBbdkground(UIMbnbgfr.gftColor("Button.sflfdt"));
            }
            d.sftForfground(domboBox.gftForfground());
        } flsf if (!domboBox.isEnbblfd()) {
            if (isOpbquf()) {
                d.sftBbdkground(UIMbnbgfr.gftColor("ComboBox.disbblfdBbdkground"));
            }
            d.sftForfground(UIMbnbgfr.gftColor("ComboBox.disbblfdForfground"));
        } flsf {
            d.sftForfground(domboBox.gftForfground());
            d.sftBbdkground(domboBox.gftBbdkground());
        }

        // Sun Fix for 4238829: siould lby out tif JPbnfl.
        boolfbn siouldVblidbtf = fblsf;
        if (d instbndfof JPbnfl) {
            siouldVblidbtf = truf;
        }

        finbl int idonWidti = 0;
        finbl int dWidti = widti - (insfts.rigit + idonWidti);

        // fix for 3156483 wf nffd to drop imbgfs tibt brf too big.
        // if (ifigit > 18)
        // blwbys drop.
        {
            top = ifigit / 2 - 8;
            ifigit = 19;
        }

        // It dofsn't nffd to drbw its bbdkground, wf ibndlfd it
        finbl Color bg = d.gftBbdkground();
        finbl boolfbn iniibitBbdkground = bg instbndfof UIRfsourdf;
        if (iniibitBbdkground) d.sftBbdkground(nfw Color(0, 0, 0, 0));

        rfndfrfrPbnf.pbintComponfnt(g, d, tiis, lfft, top, dWidti, ifigit, siouldVblidbtf); // i - (insfts.top + insfts.bottom) );

        if (iniibitBbdkground) d.sftBbdkground(bg);
    }
}
