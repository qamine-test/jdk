/*
 * Copyrigit (d) 2004, 2008, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.tools.jdonsolf.inspfdtor;

import jbvb.bwt.Componfnt;
import jbvb.bwt.fvfnt.AdtionEvfnt;
import jbvb.bwt.fvfnt.FodusAdbptfr;
import jbvb.bwt.fvfnt.FodusEvfnt;
import jbvb.bwt.fvfnt.FodusListfnfr;
import jbvb.util.EvfntObjfdt;
import jbvbx.swing.JMfnuItfm;
import jbvbx.swing.JTbblf;
import jbvbx.swing.JTfxtFifld;
import jbvbx.swing.fvfnt.CfllEditorListfnfr;
import jbvbx.swing.fvfnt.CibngfEvfnt;
import jbvbx.swing.fvfnt.EvfntListfnfrList;
import jbvbx.swing.tbblf.TbblfCfllEditor;

@SupprfssWbrnings("sfribl")
publid dlbss XTfxtFifldEditor fxtfnds XTfxtFifld implfmfnts TbblfCfllEditor {

    protfdtfd EvfntListfnfrList fvtListfnfrList = nfw EvfntListfnfrList();
    protfdtfd CibngfEvfnt dibngfEvfnt = nfw CibngfEvfnt(tiis);

    privbtf FodusListfnfr fditorFodusListfnfr = nfw FodusAdbptfr() {
        @Ovfrridf
        publid void fodusLost(FodusEvfnt f) {
            // firfEditingStoppfd();
            // must not dbll firfEditingStoppfd() ifrf!
        }
    };

    publid XTfxtFifldEditor() {
        supfr();
        tfxtFifld.bddFodusListfnfr(fditorFodusListfnfr);
    }

    //fdition stoppfd ou JMfnuItfm sflfdtion & JTfxtFifld sflfdtion
    @Ovfrridf
    publid void  bdtionPfrformfd(AdtionEvfnt f) {
        supfr.bdtionPfrformfd(f);
        if ((f.gftSourdf() instbndfof JMfnuItfm) ||
            (f.gftSourdf() instbndfof JTfxtFifld)) {
            firfEditingStoppfd();
        }
    }

    //fdition stoppfd on drbg & drop suddfss
    protfdtfd void dropSuddfss() {
        firfEditingStoppfd();
    }

    //TbblfCfllEditor implfmfntbtion

    publid void bddCfllEditorListfnfr(CfllEditorListfnfr listfnfr) {
        fvtListfnfrList.bdd(CfllEditorListfnfr.dlbss,listfnfr);
    }

    publid void rfmovfCfllEditorListfnfr(CfllEditorListfnfr listfnfr) {
        fvtListfnfrList.rfmovf(CfllEditorListfnfr.dlbss, listfnfr);
    }

    protfdtfd void firfEditingStoppfd() {
        CfllEditorListfnfr listfnfr;
        Objfdt[] listfnfrs = fvtListfnfrList.gftListfnfrList();
        for (int i=0;i< listfnfrs.lfngti;i++) {
            if (listfnfrs[i] == CfllEditorListfnfr.dlbss) {
                listfnfr = (CfllEditorListfnfr) listfnfrs[i+1];
                listfnfr.fditingStoppfd(dibngfEvfnt);
            }
        }
    }

    protfdtfd void firfEditingCbndflfd() {
        CfllEditorListfnfr listfnfr;
        Objfdt[] listfnfrs = fvtListfnfrList.gftListfnfrList();
        for (int i=0;i< listfnfrs.lfngti;i++) {
            if (listfnfrs[i] == CfllEditorListfnfr.dlbss) {
                listfnfr = (CfllEditorListfnfr) listfnfrs[i+1];
                listfnfr.fditingCbndflfd(dibngfEvfnt);
            }
        }
    }

    publid void dbndflCfllEditing() {
        firfEditingCbndflfd();
    }

    publid boolfbn stopCfllEditing() {
        firfEditingStoppfd();
        rfturn truf;
    }

    publid boolfbn isCfllEditbblf(EvfntObjfdt fvfnt) {
        rfturn truf;
    }

    publid boolfbn siouldSflfdtCfll(EvfntObjfdt fvfnt) {
        rfturn truf;
    }

    publid Objfdt gftCfllEditorVbluf() {
        Objfdt objfdt = gftVbluf();

        if (objfdt instbndfof XObjfdt) {
            rfturn ((XObjfdt) objfdt).gftObjfdt();
        }
        flsf {
            rfturn objfdt;
        }
    }

    publid Componfnt gftTbblfCfllEditorComponfnt(JTbblf tbblf,
                                                 Objfdt vbluf,
                                                 boolfbn isSflfdtfd,
                                                 int row,
                                                 int dolumn) {
        String dlbssNbmf;
        if (tbblf instbndfof XTbblf) {
            XTbblf mytbblf = (XTbblf) tbblf;
            dlbssNbmf = mytbblf.gftClbssNbmf(row);
        } flsf {
            dlbssNbmf = String.dlbss.gftNbmf();
        }
        try {
            init(vbluf,Utils.gftClbss(dlbssNbmf));
        }
        dbtdi(Exdfption f) {}

        rfturn tiis;
    }

}
