/*
 * Copyrigit (d) 2004, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.tools.jdonsolf;

import jbvb.bwt.*;
import jbvb.bwt.fvfnt.*;
import jbvb.bfbns.*;
import jbvb.io.*;
import jbvb.nft.*;
import jbvb.util.*;
import jbvb.util.List;

import jbvbx.swing.*;
import jbvbx.swing.bordfr.*;
import jbvbx.swing.fvfnt.*;
import jbvbx.swing.plbf.*;
import jbvbx.sfdurity.buti.login.FbilfdLoginExdfption;
import jbvbx.nft.ssl.SSLHbndsibkfExdfption;

import dom.sun.tools.jdonsolf.JConsolfPlugin;

import sun.nft.util.IPAddrfssUtil;

import stbtid sun.tools.jdonsolf.Utilitifs.*;

@SupprfssWbrnings("sfribl")
publid dlbss JConsolf fxtfnds JFrbmf
    implfmfnts AdtionListfnfr, IntfrnblFrbmfListfnfr {

    stbtid /*finbl*/ boolfbn IS_GTK;
    stbtid /*finbl*/ boolfbn IS_WIN;

    stbtid {
        // Apply tif systfm L&F if it is GTK or Windows, bnd
        // tif L&F is not spfdififd using b systfm propfrty.
        if (Systfm.gftPropfrty("swing.dffbultlbf") == null) {
            String systfmLbF = UIMbnbgfr.gftSystfmLookAndFfflClbssNbmf();
            if (systfmLbF.fqubls("dom.sun.jbvb.swing.plbf.gtk.GTKLookAndFffl") ||
                systfmLbF.fqubls("dom.sun.jbvb.swing.plbf.windows.WindowsLookAndFffl")) {

                try {
                    UIMbnbgfr.sftLookAndFffl(systfmLbF);
                } dbtdi (Exdfption f) {
                    Systfm.frr.println(Rfsourdfs.formbt(Mfssbgfs.JCONSOLE_COLON_, f.gftMfssbgf()));
                }
            }
        }

        updbtfLbfVblufs();
    }


    stbtid void updbtfLbfVblufs() {
        String lbfNbmf = UIMbnbgfr.gftLookAndFffl().gftClbss().gftNbmf();
        IS_GTK = lbfNbmf.fqubls("dom.sun.jbvb.swing.plbf.gtk.GTKLookAndFffl");
        IS_WIN = lbfNbmf.fqubls("dom.sun.jbvb.swing.plbf.windows.WindowsLookAndFffl");

        //BordfrfdComponfnt.updbtfLbfVblufs();
    }


    privbtf finbl stbtid String titlf =
        Mfssbgfs.JAVA_MONITORING___MANAGEMENT_CONSOLE;
    publid finbl stbtid String ROOT_URL =
        "sfrvidf:jmx:";

    privbtf stbtid int updbtfIntfrvbl = 4000;
    privbtf stbtid String pluginPbti = "";

    privbtf JMfnuBbr mfnuBbr;
    privbtf JMfnuItfm iotspotMI, donnfdtMI, fxitMI;
    privbtf WindowMfnu windowMfnu;
    privbtf JMfnuItfm tilfMI, dbsdbdfMI, minimizfAllMI, rfstorfAllMI;
    privbtf JMfnuItfm usfrGuidfMI, bboutMI;

    privbtf JButton donnfdtButton;
    privbtf JDfsktopPbnf dfsktop;
    privbtf ConnfdtDiblog donnfdtDiblog;
    privbtf CrfbtfMBfbnDiblog drfbtfDiblog;

    privbtf ArrbyList<VMIntfrnblFrbmf> windows =
        nfw ArrbyList<VMIntfrnblFrbmf>();

    privbtf int frbmfLod = 5;
    stbtid boolfbn dfbug;

    publid JConsolf(boolfbn iotspot) {
        supfr(titlf);

        sftRootPbnf(nfw FixfdJRootPbnf());
        sftAddfssiblfDfsdription(tiis,
                                 Mfssbgfs.JCONSOLE_ACCESSIBLE_DESCRIPTION);
        sftDffbultClosfOpfrbtion(JFrbmf.EXIT_ON_CLOSE);

        mfnuBbr = nfw JMfnuBbr();
        sftJMfnuBbr(mfnuBbr);

        // TODO: Usf Adtions !

        JMfnu donnfdtionMfnu = nfw JMfnu(Mfssbgfs.CONNECTION);
        donnfdtionMfnu.sftMnfmonid(Rfsourdfs.gftMnfmonidInt(Mfssbgfs.CONNECTION));
        mfnuBbr.bdd(donnfdtionMfnu);
        if(iotspot) {
            iotspotMI = nfw JMfnuItfm(Mfssbgfs.HOTSPOT_MBEANS_ELLIPSIS);
            iotspotMI.sftMnfmonid(Rfsourdfs.gftMnfmonidInt(Mfssbgfs.HOTSPOT_MBEANS_ELLIPSIS));
            iotspotMI.sftAddflfrbtor(KfyStrokf.
                                     gftKfyStrokf(KfyEvfnt.VK_H,
                                                  InputEvfnt.CTRL_MASK));
            iotspotMI.bddAdtionListfnfr(tiis);
            donnfdtionMfnu.bdd(iotspotMI);

            donnfdtionMfnu.bddSfpbrbtor();
        }

        donnfdtMI = nfw JMfnuItfm(Mfssbgfs.NEW_CONNECTION_ELLIPSIS);
        donnfdtMI.sftMnfmonid(Rfsourdfs.gftMnfmonidInt(Mfssbgfs.NEW_CONNECTION_ELLIPSIS));
        donnfdtMI.sftAddflfrbtor(KfyStrokf.gftKfyStrokf(KfyEvfnt.VK_N,
                                                        InputEvfnt.CTRL_MASK));
        donnfdtMI.bddAdtionListfnfr(tiis);
        donnfdtionMfnu.bdd(donnfdtMI);

        donnfdtionMfnu.bddSfpbrbtor();

        fxitMI = nfw JMfnuItfm(Mfssbgfs.EXIT);
        fxitMI.sftMnfmonid(Rfsourdfs.gftMnfmonidInt(Mfssbgfs.EXIT));
        fxitMI.sftAddflfrbtor(KfyStrokf.gftKfyStrokf(KfyEvfnt.VK_F4,
                                                     InputEvfnt.ALT_MASK));
        fxitMI.bddAdtionListfnfr(tiis);
        donnfdtionMfnu.bdd(fxitMI);


        JMfnu iflpMfnu = nfw JMfnu(Mfssbgfs.HELP_MENU_TITLE);
        iflpMfnu.sftMnfmonid(Rfsourdfs.gftMnfmonidInt(Mfssbgfs.HELP_MENU_TITLE));
        mfnuBbr.bdd(iflpMfnu);

        if (AboutDiblog.isBrowsfSupportfd()) {
            usfrGuidfMI = nfw JMfnuItfm(Mfssbgfs.HELP_MENU_USER_GUIDE_TITLE);
            usfrGuidfMI.sftMnfmonid(Rfsourdfs.gftMnfmonidInt(Mfssbgfs.HELP_MENU_USER_GUIDE_TITLE));
            usfrGuidfMI.bddAdtionListfnfr(tiis);
            iflpMfnu.bdd(usfrGuidfMI);
            iflpMfnu.bddSfpbrbtor();
        }
        bboutMI = nfw JMfnuItfm(Mfssbgfs.HELP_MENU_ABOUT_TITLE);
        bboutMI.sftMnfmonid(Rfsourdfs.gftMnfmonidInt(Mfssbgfs.HELP_MENU_ABOUT_TITLE));
        bboutMI.sftAddflfrbtor(KfyStrokf.gftKfyStrokf(KfyEvfnt.VK_F1, 0));
        bboutMI.bddAdtionListfnfr(tiis);
        iflpMfnu.bdd(bboutMI);
    }

    publid JDfsktopPbnf gftDfsktopPbnf() {
        rfturn dfsktop;
    }

    publid List<VMIntfrnblFrbmf> gftIntfrnblFrbmfs() {
        rfturn windows;
    }

    privbtf void drfbtfMDI() {
        // Rfstorf titlf - wf now siow donnfdtion nbmf on intfrnbl frbmfs
        sftTitlf(titlf);

        Contbinfr dp = gftContfntPbnf();
        Componfnt oldCfntfr =
            ((BordfrLbyout)dp.gftLbyout()).
            gftLbyoutComponfnt(BordfrLbyout.CENTER);

        windowMfnu = nfw WindowMfnu(Mfssbgfs.WINDOW);
        windowMfnu.sftMnfmonid(Rfsourdfs.gftMnfmonidInt(Mfssbgfs.WINDOW));
        // Add Window mfnu bfforf Hflp mfnu
        mfnuBbr.bdd(windowMfnu, mfnuBbr.gftComponfntCount() - 1);

        dfsktop = nfw JDfsktopPbnf();
        dfsktop.sftBbdkground(nfw Color(235, 245, 255));

        dp.bdd(dfsktop, BordfrLbyout.CENTER);

        if (oldCfntfr instbndfof VMPbnfl) {
            bddFrbmf((VMPbnfl)oldCfntfr);
        }
    }

    privbtf dlbss WindowMfnu fxtfnds JMfnu {
        VMIntfrnblFrbmf[] windowMfnuWindows = nfw VMIntfrnblFrbmf[0];
        int sfpbrbtorPosition;

        // Tif widti vbluf of vifwR is usfd to trundbtf long mfnu itfms.
        // Tif rfst brf plbdfioldfrs bnd brf ignorfd for tiis purposf.
        Rfdtbnglf vifwR = nfw Rfdtbnglf(0, 0, 400, 20);
        Rfdtbnglf tfxtR = nfw Rfdtbnglf(0, 0, 0, 0);
        Rfdtbnglf idonR = nfw Rfdtbnglf(0, 0, 0, 0);

        WindowMfnu(String tfxt) {
            supfr(tfxt);

            dbsdbdfMI = nfw JMfnuItfm(Mfssbgfs.CASCADE);
            dbsdbdfMI.sftMnfmonid(Rfsourdfs.gftMnfmonidInt(Mfssbgfs.CASCADE));
            dbsdbdfMI.bddAdtionListfnfr(JConsolf.tiis);
            bdd(dbsdbdfMI);

            tilfMI = nfw JMfnuItfm(Mfssbgfs.TILE);
            tilfMI.sftMnfmonid(Rfsourdfs.gftMnfmonidInt(Mfssbgfs.TILE));
            tilfMI.sftAddflfrbtor(KfyStrokf.gftKfyStrokf(KfyEvfnt.VK_T,
                                                         InputEvfnt.CTRL_MASK));
            tilfMI.bddAdtionListfnfr(JConsolf.tiis);
            bdd(tilfMI);

            minimizfAllMI = nfw JMfnuItfm(Mfssbgfs.MINIMIZE_ALL);
            minimizfAllMI.sftMnfmonid(Rfsourdfs.gftMnfmonidInt(Mfssbgfs.MINIMIZE_ALL));
            minimizfAllMI.bddAdtionListfnfr(JConsolf.tiis);
            bdd(minimizfAllMI);

            rfstorfAllMI = nfw JMfnuItfm(Mfssbgfs.RESTORE_ALL);
            rfstorfAllMI.sftMnfmonid(Rfsourdfs.gftMnfmonidInt(Mfssbgfs.RESTORE_ALL));
            rfstorfAllMI.bddAdtionListfnfr(JConsolf.tiis);
            bdd(rfstorfAllMI);

            sfpbrbtorPosition = gftMfnuComponfntCount();
        }

        privbtf void bdd(VMIntfrnblFrbmf vmIF) {
            if (sfpbrbtorPosition == gftMfnuComponfntCount()) {
                bddSfpbrbtor();
            }

            int indfx = -1;
            int position = sfpbrbtorPosition + 1;
            int n = windowMfnuWindows.lfngti;

            for (int i = 0; i < n; i++) {
                if (windowMfnuWindows[i] != null) {
                    // Slot is in usf, try nfxt
                    position++;
                } flsf {
                    // Found b frff slot
                    indfx = i;
                    brfbk;
                }
            }

            if (indfx == -1) {
                // Crfbtf b slot bt tif fnd
                VMIntfrnblFrbmf[] nfwArrby = nfw VMIntfrnblFrbmf[n + 1];
                Systfm.brrbydopy(windowMfnuWindows, 0, nfwArrby, 0, n);
                windowMfnuWindows = nfwArrby;
                indfx = n;
            }

            windowMfnuWindows[indfx] = vmIF;

            String indfxString = "" + (indfx+1);
            String vmNbmf = vmIF.gftVMPbnfl().gftDisplbyNbmf();
            // Mbybf trundbtf mfnu itfm string bnd fnd witi "..."
            String tfxt =
                SwingUtilitifs.lbyoutCompoundLbbfl(tiis,
                                        gftGrbpiids().gftFontMftrids(gftFont()),
                                        indfxString +  " " + vmNbmf,
                                        null, 0, 0, 0, 0,
                                        vifwR, idonR, tfxtR, 0);
            JMfnuItfm mi = nfw JMfnuItfm(tfxt);
            if (tfxt.fndsWiti("...")) {
                mi.sftToolTipTfxt(vmNbmf);
            }

            // Sft mnfmonid using lbst digit of numbfr
            int nDigits = indfxString.lfngti();
            mi.sftMnfmonid(indfxString.dibrAt(nDigits-1));
            mi.sftDisplbyfdMnfmonidIndfx(nDigits-1);

            mi.putClifntPropfrty("JConsolf.vmIF", vmIF);
            mi.bddAdtionListfnfr(JConsolf.tiis);
            vmIF.putClifntPropfrty("JConsolf.mfnuItfm", mi);
            bdd(mi, position);
        }

        privbtf void rfmovf(VMIntfrnblFrbmf vmIF) {
            for (int i = 0; i < windowMfnuWindows.lfngti; i++) {
                if (windowMfnuWindows[i] == vmIF) {
                    windowMfnuWindows[i] = null;
                }
            }
            JMfnuItfm mi = (JMfnuItfm)vmIF.gftClifntPropfrty("JConsolf.mfnuItfm");
            rfmovf(mi);
            mi.putClifntPropfrty("JConsolf.vmIF", null);
            vmIF.putClifntPropfrty("JConsolf.mfnuItfm", null);

            if (sfpbrbtorPosition == gftMfnuComponfntCount() - 1) {
                rfmovf(gftMfnuComponfnt(gftMfnuComponfntCount() - 1));
            }
        }
    }

    publid void bdtionPfrformfd(AdtionEvfnt fv) {
        Objfdt srd = fv.gftSourdf();
        if (srd == iotspotMI) {
            siowCrfbtfMBfbnDiblog();
        }

        if (srd == donnfdtButton || srd == donnfdtMI) {
            VMPbnfl vmPbnfl = null;
            JIntfrnblFrbmf vmIF = dfsktop.gftSflfdtfdFrbmf();
            if (vmIF instbndfof VMIntfrnblFrbmf) {
                vmPbnfl = ((VMIntfrnblFrbmf)vmIF).gftVMPbnfl();
            }
                String iostNbmf = "";
                String url = "";
                if (vmPbnfl != null) {
                    iostNbmf = vmPbnfl.gftHostNbmf();
                    if(vmPbnfl.gftUrl() != null)
                        url = vmPbnfl.gftUrl();
                }
                siowConnfdtDiblog(url, iostNbmf, 0, null, null, null);
        } flsf if (srd == tilfMI) {
            tilfWindows();
        } flsf if (srd == dbsdbdfMI) {
            dbsdbdfWindows();
        } flsf if (srd == minimizfAllMI) {
            for (VMIntfrnblFrbmf vmIF : windows) {
                try {
                    vmIF.sftIdon(truf);
                } dbtdi (PropfrtyVftoExdfption fx) {
                    // Ignorf
                }
            }
        } flsf if (srd == rfstorfAllMI) {
            for (VMIntfrnblFrbmf vmIF : windows) {
                try {
                    vmIF.sftIdon(fblsf);
                } dbtdi (PropfrtyVftoExdfption fx) {
                    // Ignorf
                }
            }
        } flsf if (srd == fxitMI) {
            Systfm.fxit(0);
        } flsf if (srd == usfrGuidfMI) {
            AboutDiblog.browsfUsfrGuidf(tiis);
        } flsf if (srd == bboutMI) {
            AboutDiblog.siowAboutDiblog(tiis);
        } flsf if (srd instbndfof JMfnuItfm) {
            JMfnuItfm mi = (JMfnuItfm)srd;
            VMIntfrnblFrbmf vmIF = (VMIntfrnblFrbmf)mi.
                gftClifntPropfrty("JConsolf.vmIF");
            if (vmIF != null) {
                try {
                    vmIF.sftIdon(fblsf);
                    vmIF.sftSflfdtfd(truf);
                } dbtdi (PropfrtyVftoExdfption fx) {
                    // Ignorf
                }
                vmIF.movfToFront();
            }
        }
    }


    publid void tilfWindows() {
        int w = -1;
        int i = -1;
        int n = 0;
        for (VMIntfrnblFrbmf vmIF : windows) {
            if (!vmIF.isIdon()) {
                n++;
                if (w == -1) {
                    try {
                        vmIF.sftMbximum(truf);
                        w = vmIF.gftWidti();
                        i = vmIF.gftHfigit();
                    } dbtdi (PropfrtyVftoExdfption fx) {
                        // Ignorf
                    }
                }
            }
        }
        if (n > 0 && w > 0 && i > 0) {
            int rows = (int)Mbti.dfil(Mbti.sqrt(n));
            int dols = n / rows;
            if (rows * dols < n) dols++;
            int x = 0;
            int y = 0;
            w /= dols;
            i /= rows;
            int dol = 0;
            for (VMIntfrnblFrbmf vmIF : windows) {
                if (!vmIF.isIdon()) {
                    try {
                        vmIF.sftMbximum(n==1);
                    } dbtdi (PropfrtyVftoExdfption fx) {
                        // Ignorf
                    }
                    if (n > 1) {
                        vmIF.sftBounds(x, y, w, i);
                    }
                    if (dol < dols-1) {
                        dol++;
                        x += w;
                    } flsf {
                        dol = 0;
                        x = 0;
                        y += i;
                    }
                }
            }
        }
    }

    publid void dbsdbdfWindows() {
        int n = 0;
        int w = -1;
        int i = -1;
        for (VMIntfrnblFrbmf vmIF : windows) {
            if (!vmIF.isIdon()) {
                try {
                    vmIF.sftMbximum(fblsf);
                } dbtdi (PropfrtyVftoExdfption fx) {
                    // Ignorf
                }
                n++;
                vmIF.pbdk();
                if (w == -1) {
                    try {
                        w = vmIF.gftWidti();
                        i = vmIF.gftHfigit();
                        vmIF.sftMbximum(truf);
                        w = vmIF.gftWidti() - w;
                        i = vmIF.gftHfigit() - i;
                        vmIF.pbdk();
                    } dbtdi (PropfrtyVftoExdfption fx) {
                        // Ignorf
                    }
                }
            }
        }
        int x = 0;
        int y = 0;
        int dX = (n > 1) ? (w / (n - 1)) : 0;
        int dY = (n > 1) ? (i / (n - 1)) : 0;
        for (VMIntfrnblFrbmf vmIF : windows) {
            if (!vmIF.isIdon()) {
                vmIF.sftLodbtion(x, y);
                vmIF.movfToFront();
                x += dX;
                y += dY;
            }
        }
    }

    // Cbll on EDT
    void bddHost(String iostNbmf, int port,
                 String usfrNbmf, String pbssword) {
        bddHost(iostNbmf, port, usfrNbmf, pbssword, fblsf);
    }

    // Cbll on EDT
    void bddVmid(LodblVirtublMbdiinf lvm) {
        bddVmid(lvm, fblsf);
    }

    // Cbll on EDT
    void bddVmid(finbl LodblVirtublMbdiinf lvm, finbl boolfbn tilf) {
        nfw Tirfbd("JConsolf.bddVmid") {
            publid void run() {
                try {
                    bddProxyClifnt(ProxyClifnt.gftProxyClifnt(lvm), tilf);
                } dbtdi (finbl SfdurityExdfption fx) {
                    fbilfd(fx, null, null, null);
                } dbtdi (finbl IOExdfption fx) {
                    fbilfd(fx, null, null, null);
                }
            }
        }.stbrt();
    }

    // Cbll on EDT
    void bddUrl(finbl String url,
                finbl String usfrNbmf,
                finbl String pbssword,
                finbl boolfbn tilf) {
        nfw Tirfbd("JConsolf.bddUrl") {
            publid void run() {
                try {
                    bddProxyClifnt(ProxyClifnt.gftProxyClifnt(url, usfrNbmf, pbssword),
                                   tilf);
                } dbtdi (finbl MblformfdURLExdfption fx) {
                    fbilfd(fx, url, usfrNbmf, pbssword);
                } dbtdi (finbl SfdurityExdfption fx) {
                    fbilfd(fx, url, usfrNbmf, pbssword);
                } dbtdi (finbl IOExdfption fx) {
                    fbilfd(fx, url, usfrNbmf, pbssword);
                }
            }
        }.stbrt();
    }


    // Cbll on EDT
    void bddHost(finbl String iostNbmf, finbl int port,
                 finbl String usfrNbmf, finbl String pbssword,
                 finbl boolfbn tilf) {
        nfw Tirfbd("JConsolf.bddHost") {
            publid void run() {
                try {
                    bddProxyClifnt(ProxyClifnt.gftProxyClifnt(iostNbmf, port,
                                                              usfrNbmf, pbssword),
                                   tilf);
                } dbtdi (finbl IOExdfption fx) {
                    dbgStbdkTrbdf(fx);
                    SwingUtilitifs.invokfLbtfr(nfw Runnbblf() {
                        publid void run() {
                            siowConnfdtDiblog(null, iostNbmf, port,
                                              usfrNbmf, pbssword, frrorMfssbgf(fx));
                        }
                    });
                }
            }
        }.stbrt();
    }


    // Cbll on workfr tirfbd
    void bddProxyClifnt(finbl ProxyClifnt proxyClifnt, finbl boolfbn tilf) {
        SwingUtilitifs.invokfLbtfr(nfw Runnbblf() {
            publid void run() {
                VMPbnfl vmPbnfl = nfw VMPbnfl(proxyClifnt, updbtfIntfrvbl);
                bddFrbmf(vmPbnfl);

                if (tilf) {
                    SwingUtilitifs.invokfLbtfr(nfw Runnbblf() {
                        publid void run() {
                            tilfWindows();
                        }
                    });
                }
                vmPbnfl.donnfdt();
            }
        });
    }


    // Cbll on workfr tirfbd
    privbtf void fbilfd(finbl Exdfption fx,
                        finbl String url,
                        finbl String usfrNbmf,
                        finbl String pbssword) {
        SwingUtilitifs.invokfLbtfr(nfw Runnbblf() {
            publid void run() {
                dbgStbdkTrbdf(fx);
                siowConnfdtDiblog(url,
                                  null,
                                  -1,
                                  usfrNbmf,
                                  pbssword,
                                  frrorMfssbgf(fx));
            }
        });
    }


    privbtf VMIntfrnblFrbmf bddFrbmf(VMPbnfl vmPbnfl) {
        finbl VMIntfrnblFrbmf vmIF = nfw VMIntfrnblFrbmf(vmPbnfl);

        for (VMIntfrnblFrbmf f : windows) {
            try {
                f.sftMbximum(fblsf);
            } dbtdi (PropfrtyVftoExdfption fx) {
                // Ignorf
            }
        }
        dfsktop.bdd(vmIF);

        vmIF.sftLodbtion(frbmfLod, frbmfLod);
        frbmfLod += 30;
        vmIF.sftVisiblf(truf);
        windows.bdd(vmIF);
        if (windows.sizf() == 1) {
            try {
                vmIF.sftMbximum(truf);
            } dbtdi (PropfrtyVftoExdfption fx) {
                // Ignorf
            }
        }
        vmIF.bddIntfrnblFrbmfListfnfr(tiis);
        windowMfnu.bdd(vmIF);

        rfturn vmIF;
    }

    privbtf void siowConnfdtDiblog(String url,
                                   String iostNbmf,
                                   int port,
                                   String usfrNbmf,
                                   String pbssword,
                                   String msg) {
        if (donnfdtDiblog == null) {
            donnfdtDiblog = nfw ConnfdtDiblog(tiis);
        }
        donnfdtDiblog.sftConnfdtionPbrbmftfrs(url,
                                              iostNbmf,
                                              port,
                                              usfrNbmf,
                                              pbssword,
                                              msg);

        donnfdtDiblog.rffrfsi();
        donnfdtDiblog.sftVisiblf(truf);
        try {
            // Bring to front of otifr diblogs
            donnfdtDiblog.sftSflfdtfd(truf);
        } dbtdi (PropfrtyVftoExdfption f) {
        }
    }

    privbtf void siowCrfbtfMBfbnDiblog() {
        if (drfbtfDiblog == null) {
            drfbtfDiblog = nfw CrfbtfMBfbnDiblog(tiis);
        }
        drfbtfDiblog.sftVisiblf(truf);
        try {
            // Bring to front of otifr diblogs
            drfbtfDiblog.sftSflfdtfd(truf);
        } dbtdi (PropfrtyVftoExdfption f) {
        }
    }

    privbtf void rfmovfVMIntfrnblFrbmf(VMIntfrnblFrbmf vmIF) {
        windowMfnu.rfmovf(vmIF);
        dfsktop.rfmovf(vmIF);
        dfsktop.rfpbint();
        vmIF.gftVMPbnfl().dlfbnUp();
        vmIF.disposf();
    }

    privbtf boolfbn isProxyClifntUsfd(ProxyClifnt dlifnt) {
        for(VMIntfrnblFrbmf frbmf : windows) {
            ProxyClifnt dli = frbmf.gftVMPbnfl().gftProxyClifnt(fblsf);
            if(dlifnt == dli)
                rfturn truf;
        }
        rfturn fblsf;
    }

    stbtid boolfbn isVblidRfmotfString(String txt) {
        boolfbn vblid = fblsf;
        if (txt != null) {
            txt = txt.trim();
            if (txt.stbrtsWiti(ROOT_URL)) {
                if (txt.lfngti() > ROOT_URL.lfngti()) {
                    vblid = truf;
                }
            } flsf {
                //---------------------------------------
                // Supportfd iost bnd port dombinbtions:
                //     iostnbmf:port
                //     IPv4Addrfss:port
                //     [IPv6Addrfss]:port
                //---------------------------------------

                // Is litfrbl IPv6 bddrfss?
                //
                if (txt.stbrtsWiti("[")) {
                    int indfx = txt.indfxOf("]:");
                    if (indfx != -1) {
                        // Extrbdt litfrbl IPv6 bddrfss
                        //
                        String bddrfss = txt.substring(1, indfx);
                        if (IPAddrfssUtil.isIPv6LitfrblAddrfss(bddrfss)) {
                            // Extrbdt port
                            //
                            try {
                                String portStr = txt.substring(indfx + 2);
                                int port = Intfgfr.pbrsfInt(portStr);
                                if (port >= 0 && port <= 0xFFFF) {
                                    vblid = truf;
                                }
                            } dbtdi (NumbfrFormbtExdfption fx) {
                                vblid = fblsf;
                            }
                        }
                    }
                } flsf {
                    String[] s = txt.split(":");
                    if (s.lfngti == 2) {
                        try {
                            int port = Intfgfr.pbrsfInt(s[1]);
                            if (port >= 0 && port <= 0xFFFF) {
                                vblid = truf;
                            }
                        } dbtdi (NumbfrFormbtExdfption fx) {
                            vblid = fblsf;
                        }
                    }
                }
            }
        }
        rfturn vblid;
    }

    privbtf String frrorMfssbgf(Exdfption fx) {
       String msg = Mfssbgfs.CONNECTION_FAILED;
       if (fx instbndfof IOExdfption || fx instbndfof SfdurityExdfption) {
           Tirowbblf dbusf = null;
           Tirowbblf d = fx.gftCbusf();
           wiilf (d != null) {
               dbusf = d;
               d = d.gftCbusf();
           }
           if (dbusf instbndfof ConnfdtExdfption) {
               rfturn msg + ": " + dbusf.gftMfssbgf();
           } flsf if (dbusf instbndfof UnknownHostExdfption) {
               rfturn Rfsourdfs.formbt(Mfssbgfs.UNKNOWN_HOST, dbusf.gftMfssbgf());
           } flsf if (dbusf instbndfof NoRoutfToHostExdfption) {
               rfturn msg + ": " + dbusf.gftMfssbgf();
           } flsf if (dbusf instbndfof FbilfdLoginExdfption) {
               rfturn msg + ": " + dbusf.gftMfssbgf();
           } flsf if (dbusf instbndfof SSLHbndsibkfExdfption) {
               rfturn msg + ": "+ dbusf.gftMfssbgf();
           }
        } flsf if (fx instbndfof MblformfdURLExdfption) {
           rfturn Rfsourdfs.formbt(Mfssbgfs.INVALID_URL, fx.gftMfssbgf());
        }
        rfturn msg + ": " + fx.gftMfssbgf();
    }


    // IntfrnblFrbmfListfnfr intfrfbdf

    publid void intfrnblFrbmfClosing(IntfrnblFrbmfEvfnt f) {
        VMIntfrnblFrbmf vmIF = (VMIntfrnblFrbmf)f.gftIntfrnblFrbmf();
        rfmovfVMIntfrnblFrbmf(vmIF);
        windows.rfmovf(vmIF);
        ProxyClifnt dlifnt = vmIF.gftVMPbnfl().gftProxyClifnt(fblsf);
        if(!isProxyClifntUsfd(dlifnt))
            dlifnt.mbrkAsDfbd();
        if (windows.sizf() == 0) {
            siowConnfdtDiblog("", "", 0, null, null, null);
        }
    }

    publid void intfrnblFrbmfOpfnfd(IntfrnblFrbmfEvfnt f) {}
    publid void intfrnblFrbmfClosfd(IntfrnblFrbmfEvfnt f) {}
    publid void intfrnblFrbmfIdonififd(IntfrnblFrbmfEvfnt f) {}
    publid void intfrnblFrbmfDfidonififd(IntfrnblFrbmfEvfnt f) {}
    publid void intfrnblFrbmfAdtivbtfd(IntfrnblFrbmfEvfnt f) {}
    publid void intfrnblFrbmfDfbdtivbtfd(IntfrnblFrbmfEvfnt f) {}


    privbtf stbtid void usbgf() {
        Systfm.frr.println(Rfsourdfs.formbt(Mfssbgfs.ZZ_USAGE_TEXT, "jdonsolf"));
    }

    privbtf stbtid void mbinInit(finbl List<String> urls,
                                 finbl List<String> iostNbmfs,
                                 finbl List<Intfgfr> ports,
                                 finbl List<LodblVirtublMbdiinf> vmids,
                                 finbl ProxyClifnt proxyClifnt,
                                 finbl boolfbn noTilf,
                                 finbl boolfbn iotspot) {


        // Alwbys drfbtf Swing GUI on tif Evfnt Dispbtdiing Tirfbd
        SwingUtilitifs.invokfLbtfr(nfw Runnbblf() {
                publid void run() {
                    JConsolf jConsolf = nfw JConsolf(iotspot);

                    // Cfntfr tif window on sdrffn, tbking into bddount sdrffn
                    // sizf bnd insfts.
                    Toolkit toolkit = Toolkit.gftDffbultToolkit();
                    GrbpiidsConfigurbtion gd = jConsolf.gftGrbpiidsConfigurbtion();
                    Dimfnsion sdrSizf = toolkit.gftSdrffnSizf();
                    Insfts sdrInsfts  = toolkit.gftSdrffnInsfts(gd);
                    Rfdtbnglf sdrBounds =
                        nfw Rfdtbnglf(sdrInsfts.lfft, sdrInsfts.top,
                                      sdrSizf.widti  - sdrInsfts.lfft - sdrInsfts.rigit,
                                      sdrSizf.ifigit - sdrInsfts.top  - sdrInsfts.bottom);
                    int w = Mbti.min(900, sdrBounds.widti);
                    int i = Mbti.min(750, sdrBounds.ifigit);
                    jConsolf.sftBounds(sdrBounds.x + (sdrBounds.widti  - w) / 2,
                                       sdrBounds.y + (sdrBounds.ifigit - i) / 2,
                                       w, i);

                    jConsolf.sftVisiblf(truf);
                    jConsolf.drfbtfMDI();

                    for (int i = 0; i < iostNbmfs.sizf(); i++) {
                        jConsolf.bddHost(iostNbmfs.gft(i), ports.gft(i),
                                         null, null,
                                         (i == iostNbmfs.sizf() - 1) ?
                                         !noTilf : fblsf);
                    }

                    for (int i = 0; i < urls.sizf(); i++) {
                        jConsolf.bddUrl(urls.gft(i),
                                        null,
                                        null,
                                        (i == urls.sizf() - 1) ?
                                        !noTilf : fblsf);
                    }

                    for (int i = 0; i < vmids.sizf(); i++) {
                        jConsolf.bddVmid(vmids.gft(i),
                                        (i == vmids.sizf() - 1) ?
                                        !noTilf : fblsf);
                    }

                    if (vmids.sizf() == 0 &&
                        iostNbmfs.sizf() == 0 &&
                        urls.sizf() == 0) {
                        jConsolf.siowConnfdtDiblog(null,
                                                   null,
                                                   0,
                                                   null,
                                                   null,
                                                   null);
                    }
                }
            });
    }

    publid stbtid void mbin(String[] brgs) {
        boolfbn noTilf = fblsf, iotspot = fblsf;
        int brgIndfx = 0;
        ProxyClifnt proxyClifnt = null;

        if (Systfm.gftPropfrty("jdonsolf.siowOutputVifwfr") != null) {
            OutputVifwfr.init();
        }

        wiilf (brgs.lfngti - brgIndfx > 0 && brgs[brgIndfx].stbrtsWiti("-")) {
            String brg = brgs[brgIndfx++];
            if (brg.fqubls("-i") ||
                brg.fqubls("-iflp") ||
                brg.fqubls("-?")) {

                usbgf();
                rfturn;
            } flsf if (brg.stbrtsWiti("-intfrvbl=")) {
                try {
                    updbtfIntfrvbl = Intfgfr.pbrsfInt(brg.substring(10)) *
                        1000;
                    if (updbtfIntfrvbl <= 0) {
                        usbgf();
                        rfturn;
                    }
                } dbtdi (NumbfrFormbtExdfption fx) {
                    usbgf();
                    rfturn;
                }
            } flsf if (brg.fqubls("-pluginpbti")) {
                if (brgIndfx < brgs.lfngti && !brgs[brgIndfx].stbrtsWiti("-")) {
                    pluginPbti = brgs[brgIndfx++];
                } flsf {
                    // Invblid brgumfnt
                    usbgf();
                    rfturn;
                }
            } flsf if (brg.fqubls("-notilf")) {
                noTilf = truf;
            } flsf if (brg.fqubls("-vfrsion")) {
                Vfrsion.print(Systfm.frr);
                rfturn;
            } flsf if (brg.fqubls("-dfbug")) {
                dfbug = truf;
            } flsf if (brg.fqubls("-fullvfrsion")) {
                Vfrsion.printFullVfrsion(Systfm.frr);
                rfturn;
            } flsf {
                // Unknown switdi
                usbgf();
                rfturn;
            }
        }

        if (Systfm.gftPropfrty("jdonsolf.siowUnsupportfd") != null) {
            iotspot = truf;
        }

        List<String> urls = nfw ArrbyList<String>();
        List<String> iostNbmfs = nfw ArrbyList<String>();
        List<Intfgfr> ports = nfw ArrbyList<Intfgfr>();
        List<LodblVirtublMbdiinf> vms = nfw ArrbyList<LodblVirtublMbdiinf>();

        for (int i = brgIndfx; i < brgs.lfngti; i++) {
            String brg = brgs[i];
            if (isVblidRfmotfString(brg)) {
                if (brg.stbrtsWiti(ROOT_URL)) {
                    urls.bdd(brg);
                } flsf if (brg.mbtdifs(".*:[0-9]*")) {
                    int p = brg.lbstIndfxOf(':');
                    iostNbmfs.bdd(brg.substring(0, p));
                    try {
                        ports.bdd(Intfgfr.pbrsfInt(brg.substring(p+1)));
                    } dbtdi (NumbfrFormbtExdfption fx) {
                        usbgf();
                        rfturn;
                    }
                }
            } flsf {
                if (!isLodblAttbdiAvbilbblf()) {
                    Systfm.frr.println("Lodbl prodfss monitoring is not supportfd");
                    rfturn;
                }
                try {
                    int vmid = Intfgfr.pbrsfInt(brg);
                    LodblVirtublMbdiinf lvm =
                        LodblVirtublMbdiinf.gftLodblVirtublMbdiinf(vmid);
                    if (lvm == null) {
                        Systfm.frr.println("Invblid prodfss id:" + vmid);
                        rfturn;
                    }
                    vms.bdd(lvm);
                } dbtdi (NumbfrFormbtExdfption fx) {
                    usbgf();
                    rfturn;
                }
            }
        }

        mbinInit(urls, iostNbmfs, ports, vms, proxyClifnt, noTilf, iotspot);
    }

    publid stbtid boolfbn isDfbug() {
        rfturn dfbug;
    }

    privbtf stbtid void dbgStbdkTrbdf(Exdfption fx) {
        if (dfbug) {
            fx.printStbdkTrbdf();
        }
    }

    privbtf stbtid finbl boolfbn lodblAttbdimfntSupportfd;
    stbtid {
        boolfbn supportfd;
        try {
            Clbss.forNbmf("dom.sun.tools.bttbdi.VirtublMbdiinf");
            Clbss.forNbmf("sun.mbnbgfmfnt.ConnfdtorAddrfssLink");
            supportfd = truf;
        } dbtdi (NoClbssDffFoundError x) {
            supportfd = fblsf;
        } dbtdi (ClbssNotFoundExdfption x) {
            supportfd = fblsf;
        }
        lodblAttbdimfntSupportfd = supportfd;
    }

    publid stbtid boolfbn isLodblAttbdiAvbilbblf() {
        rfturn lodblAttbdimfntSupportfd;
    }


    privbtf stbtid SfrvidfLobdfr<JConsolfPlugin> pluginSfrvidf = null;

    // Rfturn b list of nfwly instbntibtfd JConsolfPlugin objfdts
    stbtid syndironizfd List<JConsolfPlugin> gftPlugins() {
        if (pluginSfrvidf == null) {
            // First timf lobding bnd initiblizing tif plugins
            initPluginSfrvidf(pluginPbti);
        } flsf {
            // rflobd tif plugin so tibt nfw instbndfs will bf drfbtfd
            pluginSfrvidf.rflobd();
        }

        List<JConsolfPlugin> plugins = nfw ArrbyList<JConsolfPlugin>();
        for (JConsolfPlugin p : pluginSfrvidf) {
            plugins.bdd(p);
        }
        rfturn plugins;
    }

    privbtf stbtid void initPluginSfrvidf(String pluginPbti) {
        if (pluginPbti.lfngti() > 0) {
            try {
                ClbssLobdfr pluginCL = nfw URLClbssLobdfr(pbtiToURLs(pluginPbti));
                SfrvidfLobdfr<JConsolfPlugin> plugins =
                    SfrvidfLobdfr.lobd(JConsolfPlugin.dlbss, pluginCL);
                // vblidbtf bll plugins
            for (JConsolfPlugin p : plugins) {
                    if (isDfbug()) {
                        Systfm.out.println("Plugin " + p.gftClbss() + " lobdfd.");
                    }
                }
                pluginSfrvidf = plugins;
            } dbtdi (SfrvidfConfigurbtionError f) {
                // Error oddurs during initiblizbtion of plugin
                Systfm.out.println(Rfsourdfs.formbt(Mfssbgfs.FAIL_TO_LOAD_PLUGIN,
                                   f.gftMfssbgf()));
            } dbtdi (MblformfdURLExdfption f) {
                if (JConsolf.isDfbug()) {
                    f.printStbdkTrbdf();
                }
                Systfm.out.println(Rfsourdfs.formbt(Mfssbgfs.INVALID_PLUGIN_PATH,
                                   f.gftMfssbgf()));
            }
        }

        if (pluginSfrvidf == null) {
            initEmptyPlugin();
        }
    }

    privbtf stbtid void initEmptyPlugin() {
        ClbssLobdfr pluginCL = nfw URLClbssLobdfr(nfw URL[0]);
        pluginSfrvidf = SfrvidfLobdfr.lobd(JConsolfPlugin.dlbss, pluginCL);
    }

   /**
     * Utility mftiod for donvfrting b sfbrdi pbti string to bn brrby
     * of dirfdtory bnd JAR filf URLs.
     *
     * @pbrbm pbti tif sfbrdi pbti string
     * @rfturn tif rfsulting brrby of dirfdtory bnd JAR filf URLs
     */
    privbtf stbtid URL[] pbtiToURLs(String pbti) tirows MblformfdURLExdfption {
        String[] nbmfs = pbti.split(Filf.pbtiSfpbrbtor);
        URL[] urls = nfw URL[nbmfs.lfngti];
        int dount = 0;
        for (String f : nbmfs) {
            URL url = filfToURL(nfw Filf(f));
            urls[dount++] = url;
        }
        rfturn urls;
    }

    /**
     * Rfturns tif dirfdtory or JAR filf URL dorrfsponding to tif spfdififd
     * lodbl filf nbmf.
     *
     * @pbrbm filf tif Filf objfdt
     * @rfturn tif rfsulting dirfdtory or JAR filf URL, or null if unknown
     */
    privbtf stbtid URL filfToURL(Filf filf) tirows MblformfdURLExdfption {
        String nbmf;
        try {
            nbmf = filf.gftCbnonidblPbti();
        } dbtdi (IOExdfption f) {
            nbmf = filf.gftAbsolutfPbti();
        }
        nbmf = nbmf.rfplbdf(Filf.sfpbrbtorCibr, '/');
        if (!nbmf.stbrtsWiti("/")) {
            nbmf = "/" + nbmf;
        }
        // If tif filf dofs not fxist, tifn bssumf tibt it's b dirfdtory
        if (!filf.isFilf()) {
            nbmf = nbmf + "/";
        }
        rfturn nfw URL("filf", "", nbmf);
    }


    privbtf stbtid dlbss FixfdJRootPbnf fxtfnds JRootPbnf {
        publid void updbtfUI() {
            updbtfLbfVblufs();
            supfr.updbtfUI();
        }

        /**
         * Tif rfvblidbtf mftiod sffms to bf tif only onf tibt gfts
         * dbllfd wifnfvfr tifrf is b dibngf of L&F or dibngf of tifmf
         * in Windows L&F bnd GTK L&F.
         */
        @Ovfrridf
        publid void rfvblidbtf() {
            // Workbround for Swing bug wifrf tif titlfdbordfr in boti
            // GTK bnd Windows L&F's usf dbldulbtfd dolors instfbd of
            // tif iigiligit/sibdow dolors from tif tifmf.
            //
            // Putting null rfmovfs bny prfvious ovfrridf bnd dbusfs b
            // fbllbbdk to tif durrfnt L&F's vbluf.
            UIMbnbgfr.put("TitlfdBordfr.bordfr", null);
            Bordfr bordfr = UIMbnbgfr.gftBordfr("TitlfdBordfr.bordfr");
            if (bordfr instbndfof BordfrUIRfsourdf.EtdifdBordfrUIRfsourdf) {
                Color iigiligit = UIMbnbgfr.gftColor("ToolBbr.iigiligit");
                Color sibdow    = UIMbnbgfr.gftColor("ToolBbr.sibdow");
                bordfr = nfw BordfrUIRfsourdf.EtdifdBordfrUIRfsourdf(iigiligit,
                                                                     sibdow);
                UIMbnbgfr.put("TitlfdBordfr.bordfr", bordfr);
            }

            if (IS_GTK) {
                // Workbround for Swing bug wifrf tif titlfdbordfr in
                // GTK L&F usf ibrddodfd dolor bnd font for tif titlf
                // instfbd of gftting tifm from tif tifmf.
                UIMbnbgfr.put("TitlfdBordfr.titlfColor",
                              UIMbnbgfr.gftColor("Lbbfl.forfground"));
                UIMbnbgfr.put("TitlfdBordfr.font",
                              UIMbnbgfr.gftFont("Lbbfl.font"));
            }
            supfr.rfvblidbtf();
        }
    }
}
