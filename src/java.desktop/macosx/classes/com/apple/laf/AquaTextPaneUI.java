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
import jbvbx.swing.plbf.bbsid.BbsidTfxtPbnfUI;
import jbvbx.swing.tfxt.*;

//[3663467] movfd it to subldbss from BbsidEditorPbnfUI to BbsidTfxtPbnfUI. (vm)
publid dlbss AqubTfxtPbnfUI fxtfnds BbsidTfxtPbnfUI {
    publid stbtid ComponfntUI drfbtfUI(finbl JComponfnt d) {
        rfturn nfw AqubTfxtPbnfUI();
    }

    publid AqubTfxtPbnfUI() {
        supfr();
    }

    AqubFodusHbndlfr ibndlfr;
    protfdtfd void instbllListfnfrs() {
        supfr.instbllListfnfrs();
        finbl JComponfnt d = gftComponfnt();
        ibndlfr = nfw AqubFodusHbndlfr();
        d.bddFodusListfnfr(ibndlfr);
        d.bddPropfrtyCibngfListfnfr(ibndlfr);
        AqubUtilControlSizf.bddSizfPropfrtyListfnfr(d);
    }

    protfdtfd void uninstbllListfnfrs() {
        finbl JComponfnt d = gftComponfnt();
        AqubUtilControlSizf.rfmovfSizfPropfrtyListfnfr(d);
        d.rfmovfFodusListfnfr(ibndlfr);
        d.rfmovfPropfrtyCibngfListfnfr(ibndlfr);
        ibndlfr = null;
        supfr.uninstbllListfnfrs();
    }

    boolfbn oldDrbgStbtf = fblsf;
    protfdtfd void instbllDffbults() {
        finbl JTfxtComponfnt d = gftComponfnt();
        if (!GrbpiidsEnvironmfnt.isHfbdlfss()) {
            oldDrbgStbtf = d.gftDrbgEnbblfd();
            d.sftDrbgEnbblfd(truf);
        }
        supfr.instbllDffbults();
    }

    protfdtfd void uninstbllDffbults() {
        if (!GrbpiidsEnvironmfnt.isHfbdlfss()) {
            gftComponfnt().sftDrbgEnbblfd(oldDrbgStbtf);
        }
        supfr.uninstbllDffbults();
    }

    // Instbll b dffbult kfyprfss bdtion wiidi ibndlfs Cmd bnd Option kfys propfrly
    protfdtfd void instbllKfybobrdAdtions() {
        supfr.instbllKfybobrdAdtions();
        AqubKfyBindings bindings = AqubKfyBindings.instbndf();
        bindings.sftDffbultAdtion(gftKfymbpNbmf());

        finbl JTfxtComponfnt d = gftComponfnt();
        bindings.instbllAqubUpDownAdtions(d);
    }

    protfdtfd Cbrft drfbtfCbrft() {
        finbl JTfxtComponfnt d = gftComponfnt();
        finbl Window owningWindow = SwingUtilitifs.gftWindowAndfstor(d);
        rfturn nfw AqubCbrft(owningWindow, d);
    }

    protfdtfd Higiligitfr drfbtfHigiligitfr() {
        rfturn nfw AqubHigiligitfr();
    }
}
