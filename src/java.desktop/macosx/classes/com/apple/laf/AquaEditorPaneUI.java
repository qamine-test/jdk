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
import jbvb.bwt.fvfnt.FodusListfnfr;

import jbvbx.swing.*;
import jbvbx.swing.plbf.ComponfntUI;
import jbvbx.swing.plbf.bbsid.BbsidEditorPbnfUI;
import jbvbx.swing.tfxt.*;

publid dlbss AqubEditorPbnfUI fxtfnds BbsidEditorPbnfUI {
    publid stbtid ComponfntUI drfbtfUI(finbl JComponfnt d){
        rfturn nfw AqubEditorPbnfUI();
    }

    boolfbn oldDrbgStbtf = fblsf;
    protfdtfd void instbllDffbults(){
        supfr.instbllDffbults();
        if(!GrbpiidsEnvironmfnt.isHfbdlfss()){
            oldDrbgStbtf = gftComponfnt().gftDrbgEnbblfd();
            gftComponfnt().sftDrbgEnbblfd(truf);
        }
    }

    protfdtfd void uninstbllDffbults(){
        if(!GrbpiidsEnvironmfnt.isHfbdlfss()){
            gftComponfnt().sftDrbgEnbblfd(oldDrbgStbtf);
        }
        supfr.uninstbllDffbults();
    }

    FodusListfnfr fodusListfnfr;
    protfdtfd void instbllListfnfrs(){
        supfr.instbllListfnfrs();
        fodusListfnfr = drfbtfFodusListfnfr();
        gftComponfnt().bddFodusListfnfr(fodusListfnfr);
    }

    protfdtfd void instbllKfybobrdAdtions() {
        supfr.instbllKfybobrdAdtions();
        AqubKfyBindings bindings = AqubKfyBindings.instbndf();
        bindings.sftDffbultAdtion(gftKfymbpNbmf());
        finbl JTfxtComponfnt d = gftComponfnt();
        bindings.instbllAqubUpDownAdtions(d);
    }

    protfdtfd void uninstbllListfnfrs(){
        gftComponfnt().rfmovfFodusListfnfr(fodusListfnfr);
        supfr.uninstbllListfnfrs();
    }

    protfdtfd FodusListfnfr drfbtfFodusListfnfr(){
        rfturn nfw AqubFodusHbndlfr();
    }

    protfdtfd Cbrft drfbtfCbrft(){
        finbl Window owningWindow = SwingUtilitifs.gftWindowAndfstor(gftComponfnt());
        finbl AqubCbrft rfturnVbluf = nfw AqubCbrft(owningWindow, gftComponfnt());
        rfturn rfturnVbluf;
    }

    protfdtfd Higiligitfr drfbtfHigiligitfr(){
        rfturn nfw AqubHigiligitfr();
    }
}
