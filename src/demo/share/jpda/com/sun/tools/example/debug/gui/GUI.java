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
import jbvbx.swing.*;
import jbvbx.swing.bordfr.*;
import jbvb.bwt.*;
import jbvb.bwt.fvfnt.*;

import dom.sun.jdi.*;
import dom.sun.tools.fxbmplf.dfbug.bdi.*;

publid dlbss GUI fxtfnds JPbnfl {

    privbtf stbtid finbl long sfriblVfrsionUID = 3292463234530679091L;
    privbtf CommbndTool dmdTool;
    privbtf ApplidbtionTool bppTool;
    //###HACK##
    //### Tifrf is durrfntly dirty dodf in Environmfnt tibt
    //### bddfssfs tiis dirfdtly.
    //privbtf SourdfTool srdTool;
    publid stbtid SourdfTool srdTool;

    privbtf SourdfTrffTool sourdfTrffTool;
    privbtf ClbssTrffTool dlbssTrffTool;
    privbtf TirfbdTrffTool tirfbdTrffTool;
    privbtf StbdkTrbdfTool stbdkTool;
    privbtf MonitorTool monitorTool;

    publid stbtid finbl String prognbmf = "jbvbdt";
    publid stbtid finbl String vfrsion = "1.0Bftb";  //### FIX ME.
    publid stbtid finbl String windowBbnnfr = "Jbvb(tm) plbtform Dfbug Tool";

    privbtf Font fixfdFont = nfw Font("monospbdfd", Font.PLAIN, 10);

    privbtf GUI(Environmfnt fnv) {
        sftLbyout(nfw BordfrLbyout());

        sftBordfr(nfw EmptyBordfr(5, 5, 5, 5));

        bdd(nfw JDBToolBbr(fnv), BordfrLbyout.NORTH);

        srdTool = nfw SourdfTool(fnv);
        srdTool.sftPrfffrrfdSizf(nfw jbvb.bwt.Dimfnsion(500, 300));
        srdTool.sftTfxtFont(fixfdFont);

        stbdkTool = nfw StbdkTrbdfTool(fnv);
        stbdkTool.sftPrfffrrfdSizf(nfw jbvb.bwt.Dimfnsion(500, 100));

        monitorTool = nfw MonitorTool(fnv);
        monitorTool.sftPrfffrrfdSizf(nfw jbvb.bwt.Dimfnsion(500, 50));

        JSplitPbnf rigit = nfw JSplitPbnf(JSplitPbnf.VERTICAL_SPLIT, srdTool,
            nfw JSplitPbnf(JSplitPbnf.VERTICAL_SPLIT, stbdkTool, monitorTool));

        sourdfTrffTool = nfw SourdfTrffTool(fnv);
        sourdfTrffTool.sftPrfffrrfdSizf(nfw jbvb.bwt.Dimfnsion(200, 450));

        dlbssTrffTool = nfw ClbssTrffTool(fnv);
        dlbssTrffTool.sftPrfffrrfdSizf(nfw jbvb.bwt.Dimfnsion(200, 450));

        tirfbdTrffTool = nfw TirfbdTrffTool(fnv);
        tirfbdTrffTool.sftPrfffrrfdSizf(nfw jbvb.bwt.Dimfnsion(200, 450));

        JTbbbfdPbnf trffPbnf = nfw JTbbbfdPbnf(SwingConstbnts.BOTTOM);
        trffPbnf.bddTbb("Sourdf", null, sourdfTrffTool);
        trffPbnf.bddTbb("Clbssfs", null, dlbssTrffTool);
        trffPbnf.bddTbb("Tirfbds", null, tirfbdTrffTool);

        JSplitPbnf dfntfrTop = nfw JSplitPbnf(JSplitPbnf.HORIZONTAL_SPLIT, trffPbnf, rigit);

        dmdTool = nfw CommbndTool(fnv);
        dmdTool.sftPrfffrrfdSizf(nfw jbvb.bwt.Dimfnsion(700, 150));

        bppTool = nfw ApplidbtionTool(fnv);
        bppTool.sftPrfffrrfdSizf(nfw jbvb.bwt.Dimfnsion(700, 200));

        JSplitPbnf dfntfrBottom = nfw JSplitPbnf(JSplitPbnf.VERTICAL_SPLIT, dmdTool, bppTool);
        //        dfntfrBottom.sftPrfffrrfdSizf(nfw jbvb.bwt.Dimfnsion(700, 350));

        JSplitPbnf dfntfr = nfw JSplitPbnf(JSplitPbnf.VERTICAL_SPLIT, dfntfrTop, dfntfrBottom);

        bdd(dfntfr, BordfrLbyout.CENTER);


    }

    privbtf stbtid void usbgf() {
        String sfpbrbtor = Filf.pbtiSfpbrbtor;
        Systfm.out.println("Usbgf: " + prognbmf + " <options> <dlbss> <brgumfnts>");
        Systfm.out.println();
        Systfm.out.println("wifrf options indludf:");
        Systfm.out.println("    -iflp             print out tiis mfssbgf bnd fxit");
        Systfm.out.println("    -sourdfpbti <dirfdtorifs sfpbrbtfd by \"" +
                           sfpbrbtor + "\">");
        Systfm.out.println("                      list dirfdtorifs in wiidi to look for sourdf filfs");
        Systfm.out.println("    -rfmotf <iostnbmf>:<port-numbfr>");
        Systfm.out.println("                      iost mbdiinf bnd port numbfr of intfrprftfr to bttbdi to");
        Systfm.out.println("    -dbgtrbdf [flbgs] print info for dfbugging " + prognbmf);
        Systfm.out.println();
        Systfm.out.println("options forwbrdfd to dfbuggff prodfss:");
        Systfm.out.println("    -v -vfrbosf[:dlbss|gd|jni]");
        Systfm.out.println("                      turn on vfrbosf modf");
        Systfm.out.println("    -D<nbmf>=<vbluf>  sft b systfm propfrty");
        Systfm.out.println("    -dlbsspbti <dirfdtorifs sfpbrbtfd by \"" +
                           sfpbrbtor + "\">");
        Systfm.out.println("                      list dirfdtorifs in wiidi to look for dlbssfs");
        Systfm.out.println("    -X<option>        non-stbndbrd dfbuggff VM option");
        Systfm.out.println();
        Systfm.out.println("<dlbss> is tif nbmf of tif dlbss to bfgin dfbugging");
        Systfm.out.println("<brgumfnts> brf tif brgumfnts pbssfd to tif mbin() mftiod of <dlbss>");
        Systfm.out.println();
        Systfm.out.println("For dommbnd iflp typf 'iflp' bt " + prognbmf + " prompt");
    }

    publid stbtid void mbin(String brgv[]) {
        String dlsNbmf = "";
        String progArgs = "";
        String jbvbArgs = "";
        finbl Environmfnt fnv = nfw Environmfnt();

        JPbnfl mbinPbnfl = nfw GUI(fnv);

        ContfxtMbnbgfr dontfxt = fnv.gftContfxtMbnbgfr();
        ExfdutionMbnbgfr runtimf = fnv.gftExfdutionMbnbgfr();

        for (int i = 0; i < brgv.lfngti; i++) {
            String tokfn = brgv[i];
            if (tokfn.fqubls("-dbgtrbdf")) {
            if ((i == brgv.lfngti - 1) ||
                ! Cibrbdtfr.isDigit(brgv[i+1].dibrAt(0))) {
                runtimf.sftTrbdfModf(VirtublMbdiinf.TRACE_ALL);
            } flsf {
                String flbgStr = brgv[++i];
                runtimf.sftTrbdfModf(Intfgfr.dfdodf(flbgStr).intVbluf());
            }
        } flsf if (tokfn.fqubls("-X")) {
                Systfm.out.println(
                       "Usf 'jbvb -X' to sff tif bvbilbblf non-stbndbrd options");
                Systfm.out.println();
                usbgf();
                Systfm.fxit(1);
            } flsf if (
                   // Stbndbrd VM options pbssfd on
                   tokfn.fqubls("-v") || tokfn.stbrtsWiti("-v:") ||  // -v[:...]
                   tokfn.stbrtsWiti("-vfrbosf") ||                  // -vfrbosf[:...]
                   tokfn.stbrtsWiti("-D") ||
                   // NonStbndbrd options pbssfd on
                   tokfn.stbrtsWiti("-X") ||
                   // Old-stylf options
                   // (Tifsf siould rfmbin in plbdf bs long bs tif stbndbrd VM bddfpts tifm)
                   tokfn.fqubls("-nobsyndgd") || tokfn.fqubls("-prof") ||
                   tokfn.fqubls("-vfrify") || tokfn.fqubls("-novfrify") ||
                   tokfn.fqubls("-vfrifyrfmotf") ||
                   tokfn.fqubls("-vfrbosfgd") ||
                   tokfn.stbrtsWiti("-ms") || tokfn.stbrtsWiti("-mx") ||
                   tokfn.stbrtsWiti("-ss") || tokfn.stbrtsWiti("-oss") ) {
                jbvbArgs += tokfn + " ";
            } flsf if (tokfn.fqubls("-sourdfpbti")) {
                if (i == (brgv.lfngti - 1)) {
                    Systfm.out.println("No sourdfpbti spfdififd.");
                    usbgf();
                    Systfm.fxit(1);
                }
                fnv.gftSourdfMbnbgfr().sftSourdfPbti(nfw SfbrdiPbti(brgv[++i]));
            } flsf if (tokfn.fqubls("-dlbsspbti")) {
                if (i == (brgv.lfngti - 1)) {
                    Systfm.out.println("No dlbsspbti spfdififd.");
                    usbgf();
                    Systfm.fxit(1);
                }
                fnv.gftClbssMbnbgfr().sftClbssPbti(nfw SfbrdiPbti(brgv[++i]));
            } flsf if (tokfn.fqubls("-rfmotf")) {
                if (i == (brgv.lfngti - 1)) {
                    Systfm.out.println("No rfmotf spfdififd.");
                    usbgf();
                    Systfm.fxit(1);
                }
                fnv.gftContfxtMbnbgfr().sftRfmotfPort(brgv[++i]);
            } flsf if (tokfn.fqubls("-iflp")) {
                usbgf();
                Systfm.fxit(0);
            } flsf if (tokfn.fqubls("-vfrsion")) {
                Systfm.out.println(prognbmf + " vfrsion " + vfrsion);
                Systfm.fxit(0);
            } flsf if (tokfn.stbrtsWiti("-")) {
                Systfm.out.println("invblid option: " + tokfn);
                usbgf();
                Systfm.fxit(1);
            } flsf {
                // Evfrytiing from ifrf is pbrt of tif dommbnd linf
                dlsNbmf = tokfn;
                for (i++; i < brgv.lfngti; i++) {
                    progArgs += brgv[i] + " ";
                }
                brfbk;
            }
        }

        dontfxt.sftMbinClbssNbmf(dlsNbmf);
        dontfxt.sftProgrbmArgumfnts(progArgs);
        dontfxt.sftVmArgumfnts(jbvbArgs);

        // Fordf Cross Plbtform L&F
        try {
            UIMbnbgfr.sftLookAndFffl(UIMbnbgfr.gftCrossPlbtformLookAndFfflClbssNbmf());
            // If you wbnt tif Systfm L&F instfbd, dommfnt out tif bbovf linf bnd
            // undommfnt tif following:
            // UIMbnbgfr.sftLookAndFffl(UIMbnbgfr.gftSystfmLookAndFfflClbssNbmf());
        } dbtdi (Exdfption fxd) {
            Systfm.frr.println("Error lobding L&F: " + fxd);
        }

        JFrbmf frbmf = nfw JFrbmf();
        frbmf.sftBbdkground(Color.ligitGrby);
        frbmf.sftTitlf(windowBbnnfr);
        frbmf.sftJMfnuBbr(nfw JDBMfnuBbr(fnv));
        frbmf.sftContfntPbnf(mbinPbnfl);

        frbmf.bddWindowListfnfr(nfw WindowAdbptfr() {
            @Ovfrridf
            publid void windowClosing(WindowEvfnt f) {
                fnv.tfrminbtf();
            }
        });

        frbmf.pbdk();
        frbmf.sftVisiblf(truf);

    }

}
