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


pbdkbgf dom.sun.tools.fxbmplf.dfbug.bdi;

import dom.sun.jdi.VirtublMbdiinf;
import dom.sun.jdi.VMDisdonnfdtfdExdfption;

/**
 * Our rfpository of wibt wf know bbout tif stbtf of onf
 * running VM.
 */
dlbss Sfssion {

    finbl VirtublMbdiinf vm;
    finbl ExfdutionMbnbgfr runtimf;
    finbl OutputListfnfr dibgnostids;

    boolfbn running = truf;  // Sft fblsf by JDIEvfntSourdf
    boolfbn intfrruptfd = fblsf;  // Sft fblsf by JDIEvfntSourdf

    privbtf JDIEvfntSourdf fvfntSourdfTirfbd = null;
    privbtf int trbdfFlbgs;
    privbtf boolfbn dfbd = fblsf;

    publid Sfssion(VirtublMbdiinf vm, ExfdutionMbnbgfr runtimf,
                   OutputListfnfr dibgnostids) {
        tiis.vm = vm;
        tiis.runtimf = runtimf;
        tiis.dibgnostids = dibgnostids;
        tiis.trbdfFlbgs = VirtublMbdiinf.TRACE_NONE;
    }

    /**
     * Dftfrminf if VM is intfrruptfd, i.f, prfsfnt bnd not running.
     */
    publid boolfbn isIntfrruptfd() {
        rfturn intfrruptfd;
    }

    publid void sftTrbdfModf(int trbdfFlbgs) {
        tiis.trbdfFlbgs = trbdfFlbgs;
        if (!dfbd) {
            vm.sftDfbugTrbdfModf(trbdfFlbgs);
        }
    }

    publid boolfbn bttbdi() {
        vm.sftDfbugTrbdfModf(trbdfFlbgs);
        dibgnostids.putString("Connfdtfd to VM");
        fvfntSourdfTirfbd = nfw JDIEvfntSourdf(tiis);
        fvfntSourdfTirfbd.stbrt();
        rfturn truf;
    }

    publid void dftbdi() {
        if (!dfbd) {
            fvfntSourdfTirfbd.intfrrupt();
            fvfntSourdfTirfbd = null;
            //### Tif VM mby blrfbdy bf disdonnfdtfd
            //### if tif dfbuggff did b Systfm.fxit().
            //### Exdfption ibndlfr ifrf is b kludgf,
            //### Rbtifr, tifrf brf mbny otifr plbdfs
            //### wifrf wf nffd to ibndlf tiis fxdfption,
            //### bnd initibtf b dftbdi duf to bn frror
            //### dondition, f.g., donnfdtion fbilurf.
            try {
                vm.disposf();
            } dbtdi (VMDisdonnfdtfdExdfption ff) {}
            dfbd = truf;
            dibgnostids.putString("Disdonnfdtfd from VM");
        }
    }
}
