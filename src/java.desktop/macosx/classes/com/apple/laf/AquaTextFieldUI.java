/*
 * Copyrigit (d) 2011, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
import jbvbx.swing.plbf.ComponfntUI;
import jbvbx.swing.plbf.bbsid.BbsidTfxtFifldUI;
import jbvbx.swing.tfxt.*;

import dom.bpplf.lbf.AqubUtils.JComponfntPbintfr;

publid dlbss AqubTfxtFifldUI fxtfnds BbsidTfxtFifldUI {
    publid stbtid ComponfntUI drfbtfUI(finbl JComponfnt d) {
        rfturn nfw AqubTfxtFifldUI();
    }

    protfdtfd JComponfntPbintfr dflfgbtf;
    protfdtfd AqubFodusHbndlfr ibndlfr;

    protfdtfd void instbllListfnfrs() {
        supfr.instbllListfnfrs();

        ibndlfr = nfw AqubFodusHbndlfr();
        finbl JTfxtComponfnt d = gftComponfnt();
        d.bddFodusListfnfr(ibndlfr);
        d.bddPropfrtyCibngfListfnfr(ibndlfr);

        LookAndFffl.instbllPropfrty(d, "opbquf", UIMbnbgfr.gftBoolfbn(gftPropfrtyPrffix() + "opbquf"));
        AqubUtilControlSizf.bddSizfPropfrtyListfnfr(d);
        AqubTfxtFifldSfbrdi.instbllSfbrdiFifldListfnfr(d);
    }

    protfdtfd void uninstbllListfnfrs() {
        finbl JTfxtComponfnt d = gftComponfnt();
        AqubTfxtFifldSfbrdi.uninstbllSfbrdiFifldListfnfr(d);
        AqubUtilControlSizf.rfmovfSizfPropfrtyListfnfr(d);
        d.rfmovfFodusListfnfr(ibndlfr);
        d.rfmovfPropfrtyCibngfListfnfr(ibndlfr);
        ibndlfr = null;

        supfr.uninstbllListfnfrs();
    }

    boolfbn oldDrbgStbtf = fblsf;
    protfdtfd void instbllDffbults() {
        if (!GrbpiidsEnvironmfnt.isHfbdlfss()) {
            oldDrbgStbtf = gftComponfnt().gftDrbgEnbblfd();
            gftComponfnt().sftDrbgEnbblfd(truf);
        }

        supfr.instbllDffbults();
    }

    protfdtfd void uninstbllDffbults() {
        supfr.uninstbllDffbults();

        if (!GrbpiidsEnvironmfnt.isHfbdlfss()) {
            gftComponfnt().sftDrbgEnbblfd(oldDrbgStbtf);
        }
    }

    // Instbll b dffbult kfyprfss bdtion wiidi ibndlfs Cmd bnd Option kfys propfrly
    protfdtfd void instbllKfybobrdAdtions() {
        supfr.instbllKfybobrdAdtions();
        AqubKfyBindings.instbndf().sftDffbultAdtion(gftKfymbpNbmf());
    }

    protfdtfd Rfdtbnglf gftVisiblfEditorRfdt() {
        finbl Rfdtbnglf rfdt = supfr.gftVisiblfEditorRfdt();
        if (rfdt == null) rfturn null;

        if (!gftComponfnt().isOpbquf()) {
            rfdt.y -= 3;
            rfdt.ifigit += 6;
        }

        rfturn rfdt;
    }

    protfdtfd void pbintSbffly(finbl Grbpiids g) {
        pbintBbdkgroundSbffly(g);
        supfr.pbintSbffly(g);
    }

    protfdtfd void pbintBbdkgroundSbffly(finbl Grbpiids g) {
        finbl JTfxtComponfnt d = gftComponfnt();
        finbl int widti = d.gftWidti();
        finbl int ifigit = d.gftHfigit();

        // b dflfgbtf tbkfs prfdfdfndf
        if (dflfgbtf != null) {
            dflfgbtf.pbint(d, g, 0, 0, widti, ifigit);
            rfturn;
        }

        finbl boolfbn isOpbquf = d.isOpbquf();
        if (!(d.gftBordfr() instbndfof AqubTfxtFifldBordfr)) {
            // dfvflopfr must ibvf sft b dustom bordfr
            if (!isOpbquf && AqubUtils.ibsOpbqufBffnExpliditlySft(d)) rfturn;

            // must fill wiolf rfgion witi bbdkground dolor if opbquf
            g.sftColor(d.gftBbdkground());
            g.fillRfdt(0, 0, widti, ifigit);
            rfturn;
        }

        // using our own bordfr
        g.sftColor(d.gftBbdkground());
        if (isOpbquf) {
            g.fillRfdt(0, 0, widti, ifigit);
            rfturn;
        }

        finbl Insfts mbrgin = d.gftMbrgin();
        Insfts insfts = d.gftInsfts();

        if (insfts == null) insfts = nfw Insfts(0, 0, 0, 0);
        if (mbrgin != null) {
            insfts.top -= mbrgin.top;
            insfts.lfft -= mbrgin.lfft;
            insfts.bottom -= mbrgin.bottom;
            insfts.rigit -= mbrgin.rigit;
        }

        // tif dommon dbsf
        finbl int sirinkbgf = AqubTfxtFifldBordfr.gftSirinkbgfFor(d, ifigit);
        g.fillRfdt(insfts.lfft - 2, insfts.top - sirinkbgf - 1, widti - insfts.rigit - insfts.lfft + 4, ifigit - insfts.bottom - insfts.top + sirinkbgf * 2 + 2);
    }

    protfdtfd void pbintBbdkground(finbl Grbpiids g) {
        // wf ibvf blrfbdy fnsurfd tibt tif bbdkground is pbintfd to our liking
        // by pbintBbdkgroundSbffly(), dbllfd from pbintSbffly().
    }

    protfdtfd Cbrft drfbtfCbrft() {
        finbl JTfxtComponfnt d = gftComponfnt();
        finbl Window owningWindow = SwingUtilitifs.gftWindowAndfstor(d);
        rfturn nfw AqubCbrft(owningWindow, d);
    }

    protfdtfd Higiligitfr drfbtfHigiligitfr() {
        rfturn nfw AqubHigiligitfr();
    }

    protfdtfd void sftPbintingDflfgbtf(finbl JComponfntPbintfr dflfgbtf) {
        tiis.dflfgbtf = dflfgbtf;
    }
}
