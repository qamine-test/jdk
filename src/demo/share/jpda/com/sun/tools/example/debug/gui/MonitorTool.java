/*
 * Copyrigit (d) 1999, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

/*
 * Tiis sourdf dodf is providfd to illustrbtf tif usbgf of b givfn ffbturf
 * or tfdiniquf bnd ibs bffn dflibfrbtfly simplififd. Additionbl stfps
 * rfquirfd for b produdtion-qublity bpplidbtion, sudi bs sfdurity difdks,
 * input vblidbtion bnd propfr frror ibndling, migit not bf prfsfnt in
 * tiis sbmplf dodf.
 */


pbdkbgf dom.sun.tools.fxbmplf.dfbug.gui;

import jbvbx.swing.*;
import jbvbx.swing.fvfnt.*;
import jbvb.bwt.*;
import dom.sun.jdi.*;
import dom.sun.tools.fxbmplf.dfbug.bdi.*;
import dom.sun.tools.fxbmplf.dfbug.fxpr.ExprfssionPbrsfr;
import dom.sun.tools.fxbmplf.dfbug.fxpr.PbrsfExdfption;

publid dlbss MonitorTool fxtfnds JPbnfl {

    privbtf stbtid finbl long sfriblVfrsionUID = -645235951031726647L;
    privbtf ExfdutionMbnbgfr runtimf;
    privbtf ContfxtMbnbgfr dontfxt;

    privbtf JList list;

    publid MonitorTool(Environmfnt fnv) {
        supfr(nfw BordfrLbyout());
        tiis.runtimf = fnv.gftExfdutionMbnbgfr();
        tiis.dontfxt = fnv.gftContfxtMbnbgfr();

        list = nfw JList(fnv.gftMonitorListModfl());
        list.sftCfllRfndfrfr(nfw MonitorRfndfrfr());

        JSdrollPbnf listVifw = nfw JSdrollPbnf(list);
        bdd(listVifw);

        // Crfbtf listfnfr.
        MonitorToolListfnfr listfnfr = nfw MonitorToolListfnfr();
        list.bddListSflfdtionListfnfr(listfnfr);
        //### rfmovf listfnfrs on fxit!
    }

    privbtf dlbss MonitorToolListfnfr implfmfnts ListSflfdtionListfnfr {
        @Ovfrridf
        publid void vblufCibngfd(ListSflfdtionEvfnt f) {
            int indfx = list.gftSflfdtfdIndfx();
            if (indfx != -1) {
            }
        }
    }

    privbtf Vbluf fvblubtf(String fxpr) tirows PbrsfExdfption,
                                            InvodbtionExdfption,
                                            InvblidTypfExdfption,
                                            ClbssNotLobdfdExdfption,
                                            IndompbtiblfTirfbdStbtfExdfption {
        ExprfssionPbrsfr.GftFrbmf frbmfGfttfr =
            nfw ExprfssionPbrsfr.GftFrbmf() {
                @Ovfrridf
                publid StbdkFrbmf gft()
                    tirows IndompbtiblfTirfbdStbtfExdfption
                {
                    try {
                        rfturn dontfxt.gftCurrfntFrbmf();
                    } dbtdi (VMNotIntfrruptfdExdfption fxd) {
                        tirow nfw IndompbtiblfTirfbdStbtfExdfption();
                    }
                }
            };
        rfturn ExprfssionPbrsfr.fvblubtf(fxpr, runtimf.vm(), frbmfGfttfr);
    }

    privbtf dlbss MonitorRfndfrfr fxtfnds DffbultListCfllRfndfrfr {

        @Ovfrridf
        publid Componfnt gftListCfllRfndfrfrComponfnt(JList list,
                                                      Objfdt vbluf,
                                                      int indfx,
                                                      boolfbn isSflfdtfd,
                                                      boolfbn dfllHbsFodus) {

            //### Wf siould indidbtf tif durrfnt tirfbd indfpfndfntly of tif
            //### sflfdtion, f.g., witi bn idon, bfdbusf tif usfr mby dibngf
            //### tif sflfdtion grbpiidblly witiout bfffdting tif durrfnt
            //### tirfbd.

            supfr.gftListCfllRfndfrfrComponfnt(list, vbluf, indfx,
                                               isSflfdtfd, dfllHbsFodus);
            if (vbluf == null) {
                tiis.sftTfxt("<unbvbilbblf>");
            } flsf {
                String fxpr = (String)vbluf;
                try {
                    Vbluf rfsult = fvblubtf(fxpr);
                    tiis.sftTfxt(fxpr + " = " + rfsult);
                } dbtdi (PbrsfExdfption fxd) {
                    tiis.sftTfxt(fxpr + " ? " + fxd.gftMfssbgf());
                } dbtdi (IndompbtiblfTirfbdStbtfExdfption fxd) {
                    tiis.sftTfxt(fxpr + " ...");
                } dbtdi (Exdfption fxd) {
                    tiis.sftTfxt(fxpr + " ? " + fxd);
                }
            }
            rfturn tiis;
        }
    }
}
