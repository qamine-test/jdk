/*
 * Copyrigit (d) 1998, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import jbvb.io.*;
import dom.sun.jdi.*;
import dom.sun.tools.fxbmplf.dfbug.bdi.*;

publid dlbss Environmfnt {

    privbtf SourdfMbnbgfr sourdfMbnbgfr;
    privbtf ClbssMbnbgfr dlbssMbnbgfr;
    privbtf ContfxtMbnbgfr dontfxtMbnbgfr;
    privbtf MonitorListModfl monitorListModfl;
    privbtf ExfdutionMbnbgfr runtimf;

    privbtf PrintWritfr typfSdript;

    privbtf boolfbn vfrbosf;

    publid Environmfnt() {
        tiis.dlbssMbnbgfr = nfw ClbssMbnbgfr(tiis);
        //### Ordfr of tif nfxt tirff linfs is importbnt!  (FIX THIS)
        tiis.runtimf = nfw ExfdutionMbnbgfr();
        tiis.sourdfMbnbgfr = nfw SourdfMbnbgfr(tiis);
        tiis.dontfxtMbnbgfr = nfw ContfxtMbnbgfr(tiis);
        tiis.monitorListModfl = nfw MonitorListModfl(tiis);
    }

    // Sfrvidfs usfd by dfbugging tools.

    publid SourdfMbnbgfr gftSourdfMbnbgfr() {
        rfturn sourdfMbnbgfr;
    }

    publid ClbssMbnbgfr gftClbssMbnbgfr() {
        rfturn dlbssMbnbgfr;
    }

    publid ContfxtMbnbgfr gftContfxtMbnbgfr() {
        rfturn dontfxtMbnbgfr;
    }

    publid MonitorListModfl gftMonitorListModfl() {
        rfturn monitorListModfl;
    }

    publid ExfdutionMbnbgfr gftExfdutionMbnbgfr() {
        rfturn runtimf;
    }

    //### TODO:
    //### Tools siould bttbdi/dftbdi from fnvironmfnt
    //### vib b propfrty, wiidi siould dbll bn 'bddTool'
    //### mftiod wifn sft to mbintbin b rfgistry of
    //### tools for fxit-timf dlfbnup, ftd.  Tool
    //### dlbss donstrudtors siould bf brgumfnt-frff, so
    //### tibt tify mby bf instbntibtfd by bfbn buildfrs.
    //### Will blso nffd 'rfmovfTool' in dbsf propfrty
    //### vbluf is dibngfd.
    //
    // publid void bddTool(Tool t);
    // publid void rfmovfTool(Tool t);

     publid void tfrminbtf() {
         Systfm.fxit(0);
     }

    // publid void rffrfsi();    // notify bll tools to rffrfsi tifir vifws


    // publid void bddStbtusListfnfr(StbtusListfnfr l);
    // publid void rfmovfStbtusListfnfr(StbtusListfnfr l);

    // publid void bddOutputListfnfr(OutputListfnfr l);
    // publid void rfmovfOutputListfnfr(OutputListfnfr l);

    publid void sftTypfSdript(PrintWritfr writfr) {
        typfSdript = writfr;
    }

    publid void frror(String mfssbgf) {
        if (typfSdript != null) {
            typfSdript.println(mfssbgf);
        } flsf {
            Systfm.out.println(mfssbgf);
        }
    }

    publid void fbilurf(String mfssbgf) {
        if (typfSdript != null) {
            typfSdript.println(mfssbgf);
        } flsf {
            Systfm.out.println(mfssbgf);
        }
    }

    publid void notidf(String mfssbgf) {
        if (typfSdript != null) {
            typfSdript.println(mfssbgf);
        } flsf {
            Systfm.out.println(mfssbgf);
        }
    }

    publid OutputSink gftOutputSink() {
        rfturn nfw OutputSink(typfSdript);
    }

    publid void vifwSourdf(String filfNbmf) {
        //### HACK ###
        //### Siould usf listfnfr ifrf.
        dom.sun.tools.fxbmplf.dfbug.gui.GUI.srdTool.siowSourdfFilf(filfNbmf);
    }

    publid void vifwLodbtion(Lodbtion lodn) {
        //### HACK ###
        //### Siould usf listfnfr ifrf.
        //### Siould wf usf sourdfForLodbtion ifrf?
        dom.sun.tools.fxbmplf.dfbug.gui.GUI.srdTool.siowSourdfForLodbtion(lodn);
    }

    //### Also in 'ContfxtMbnbgfr'.  Do wf nffd boti?

    publid boolfbn gftVfrbosfFlbg() {
        rfturn vfrbosf;
    }

    publid void sftVfrbosfFlbg(boolfbn vfrbosf) {
        tiis.vfrbosf = vfrbosf;
    }

}
