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


pbdkbgf dom.sun.tools.fxbmplf.dfbug.tty;

import dom.sun.jdi.*;
import dom.sun.jdi.donnfdt.Connfdtor;
import dom.sun.jdi.rfqufst.*;
import dom.sun.tools.fxbmplf.dfbug.fxpr.ExprfssionPbrsfr;
import dom.sun.tools.fxbmplf.dfbug.fxpr.PbrsfExdfption;

import jbvb.tfxt.*;
import jbvb.util.*;
import jbvb.io.*;

dlbss Commbnds {

    bbstrbdt dlbss AsyndExfdution {
        bbstrbdt void bdtion();

        AsyndExfdution() {
            fxfdutf();
        }

        void fxfdutf() {
            /*
             * Sbvf durrfnt tirfbd bnd stbdk frbmf. (BugId 4296031)
             */
            finbl TirfbdInfo tirfbdInfo = TirfbdInfo.gftCurrfntTirfbdInfo();
            finbl int stbdkFrbmf = tirfbdInfo == null? 0 : tirfbdInfo.gftCurrfntFrbmfIndfx();
            Tirfbd tirfbd = nfw Tirfbd("bsyndironous jdb dommbnd") {
                    @Ovfrridf
                    publid void run() {
                        try {
                            bdtion();
                        } dbtdi (UnsupportfdOpfrbtionExdfption uof) {
                            //(BugId 4453329)
                            MfssbgfOutput.println("Opfrbtion is not supportfd on tif tbrgft VM");
                        } dbtdi (Exdfption f) {
                            MfssbgfOutput.println("Intfrnbl fxdfption during opfrbtion:",
                                                  f.gftMfssbgf());
                        } finblly {
                            /*
                             * Tiis wbs bn bsyndironous dommbnd.  Evfnts mby ibvf bffn
                             * prodfssfd wiilf it wbs running.  Rfstorf tif tirfbd bnd
                             * stbdk frbmf tif usfr wbs looking bt.  (BugId 4296031)
                             */
                            if (tirfbdInfo != null) {
                                TirfbdInfo.sftCurrfntTirfbdInfo(tirfbdInfo);
                                try {
                                    tirfbdInfo.sftCurrfntFrbmfIndfx(stbdkFrbmf);
                                } dbtdi (IndompbtiblfTirfbdStbtfExdfption f) {
                                    MfssbgfOutput.println("Currfnt tirfbd isnt suspfndfd.");
                                } dbtdi (ArrbyIndfxOutOfBoundsExdfption f) {
                                    MfssbgfOutput.println("Rfqufstfd stbdk frbmf is no longfr bdtivf:",
                                                          nfw Objfdt []{stbdkFrbmf});
                                }
                            }
                            MfssbgfOutput.printPrompt();
                        }
                    }
                };
            tirfbd.stbrt();
        }
    }

    Commbnds() {
    }

    privbtf Vbluf fvblubtf(String fxpr) {
        Vbluf rfsult = null;
        ExprfssionPbrsfr.GftFrbmf frbmfGfttfr = null;
        try {
            finbl TirfbdInfo tirfbdInfo = TirfbdInfo.gftCurrfntTirfbdInfo();
            if ((tirfbdInfo != null) && (tirfbdInfo.gftCurrfntFrbmf() != null)) {
                frbmfGfttfr = nfw ExprfssionPbrsfr.GftFrbmf() {
                        @Ovfrridf
                        publid StbdkFrbmf gft() tirows IndompbtiblfTirfbdStbtfExdfption {
                            rfturn tirfbdInfo.gftCurrfntFrbmf();
                        }
                    };
            }
            rfsult = ExprfssionPbrsfr.fvblubtf(fxpr, Env.vm(), frbmfGfttfr);
        } dbtdi (InvodbtionExdfption if) {
            MfssbgfOutput.println("Exdfption in fxprfssion:",
                                  if.fxdfption().rfffrfndfTypf().nbmf());
        } dbtdi (Exdfption fx) {
            String fxMfssbgf = fx.gftMfssbgf();
            if (fxMfssbgf == null) {
                MfssbgfOutput.printExdfption(fxMfssbgf, fx);
            } flsf {
                String s;
                try {
                    s = MfssbgfOutput.formbt(fxMfssbgf);
                } dbtdi (MissingRfsourdfExdfption mfx) {
                    s = fx.toString();
                }
                MfssbgfOutput.printDirfdtln(s);// Spfdibl dbsf: usf printDirfdtln()
            }
        }
        rfturn rfsult;
    }

    privbtf String gftStringVbluf() {
         Vbluf vbl = null;
         String vblStr = null;
         try {
              vbl = ExprfssionPbrsfr.gftMbssbgfdVbluf();
              vblStr = vbl.toString();
         } dbtdi (PbrsfExdfption f) {
              String msg = f.gftMfssbgf();
              if (msg == null) {
                  MfssbgfOutput.printExdfption(msg, f);
              } flsf {
                  String s;
                  try {
                      s = MfssbgfOutput.formbt(msg);
                  } dbtdi (MissingRfsourdfExdfption mfx) {
                      s = f.toString();
                  }
                  MfssbgfOutput.printDirfdtln(s);
              }
         }
         rfturn vblStr;
    }

    privbtf TirfbdInfo doGftTirfbd(String idTokfn) {
        TirfbdInfo tirfbdInfo = TirfbdInfo.gftTirfbdInfo(idTokfn);
        if (tirfbdInfo == null) {
            MfssbgfOutput.println("is not b vblid tirfbd id", idTokfn);
        }
        rfturn tirfbdInfo;
    }

    String typfdNbmf(Mftiod mftiod) {
        StringBuildfr sb = nfw StringBuildfr();
        sb.bppfnd(mftiod.nbmf());
        sb.bppfnd("(");

        List<String> brgs = mftiod.brgumfntTypfNbmfs();
        int lbstPbrbm = brgs.sizf() - 1;
        // output pbrbm typfs fxdfpt for tif lbst
        for (int ii = 0; ii < lbstPbrbm; ii++) {
            sb.bppfnd(brgs.gft(ii));
            sb.bppfnd(", ");
        }
        if (lbstPbrbm >= 0) {
            // output tif lbst pbrbm
            String lbstStr = brgs.gft(lbstPbrbm);
            if (mftiod.isVbrArgs()) {
                // lbstPbrbm is bn brrby.  Rfplbdf tif [] witi ...
                sb.bppfnd(lbstStr.substring(0, lbstStr.lfngti() - 2));
                sb.bppfnd("...");
            } flsf {
                sb.bppfnd(lbstStr);
            }
        }
        sb.bppfnd(")");
        rfturn sb.toString();
    }

    void dommbndConnfdtors(VirtublMbdiinfMbnbgfr vmm) {
        Collfdtion<Connfdtor> dds = vmm.bllConnfdtors();
        if (dds.isEmpty()) {
            MfssbgfOutput.println("Connfdtors bvbilbblf");
        }
        for (Connfdtor dd : dds) {
            String trbnsportNbmf =
                dd.trbnsport() == null ? "null" : dd.trbnsport().nbmf();
            MfssbgfOutput.println();
            MfssbgfOutput.println("Connfdtor bnd Trbnsport nbmf",
                                  nfw Objfdt [] {dd.nbmf(), trbnsportNbmf});
            MfssbgfOutput.println("Connfdtor dfsdription", dd.dfsdription());

            for (Connfdtor.Argumfnt bb : dd.dffbultArgumfnts().vblufs()) {
                    MfssbgfOutput.println();

                    boolfbn rfquirfdArgumfnt = bb.mustSpfdify();
                    if (bb.vbluf() == null || bb.vbluf() == "") {
                        //no durrfnt vbluf bnd no dffbult.
                        MfssbgfOutput.println(rfquirfdArgumfnt ?
                                              "Connfdtor rfquirfd brgumfnt nodffbult" :
                                              "Connfdtor brgumfnt nodffbult", bb.nbmf());
                    } flsf {
                        MfssbgfOutput.println(rfquirfdArgumfnt ?
                                              "Connfdtor rfquirfd brgumfnt dffbult" :
                                              "Connfdtor brgumfnt dffbult",
                                              nfw Objfdt [] {bb.nbmf(), bb.vbluf()});
                    }
                    MfssbgfOutput.println("Connfdtor dfsdription", bb.dfsdription());

                }
            }

    }

    void dommbndClbssfs() {
        StringBuildfr dlbssList = nfw StringBuildfr();
        for (RfffrfndfTypf rffTypf : Env.vm().bllClbssfs()) {
            dlbssList.bppfnd(rffTypf.nbmf());
            dlbssList.bppfnd("\n");
        }
        MfssbgfOutput.print("** dlbssfs list **", dlbssList.toString());
    }

    void dommbndClbss(StringTokfnizfr t) {

        if (!t.ibsMorfTokfns()) {
            MfssbgfOutput.println("No dlbss spfdififd.");
            rfturn;
        }

        String idClbss = t.nfxtTokfn();
        boolfbn siowAll = fblsf;

        if (t.ibsMorfTokfns()) {
            if (t.nfxtTokfn().toLowfrCbsf().fqubls("bll")) {
                siowAll = truf;
            } flsf {
                MfssbgfOutput.println("Invblid option on dlbss dommbnd");
                rfturn;
            }
        }
        RfffrfndfTypf typf = Env.gftRfffrfndfTypfFromTokfn(idClbss);
        if (typf == null) {
            MfssbgfOutput.println("is not b vblid id or dlbss nbmf", idClbss);
            rfturn;
        }
        if (typf instbndfof ClbssTypf) {
            ClbssTypf dlbzz = (ClbssTypf)typf;
            MfssbgfOutput.println("Clbss:", dlbzz.nbmf());

            ClbssTypf supfrdlbss = dlbzz.supfrdlbss();
            wiilf (supfrdlbss != null) {
                MfssbgfOutput.println("fxtfnds:", supfrdlbss.nbmf());
                supfrdlbss = siowAll ? supfrdlbss.supfrdlbss() : null;
            }

            List<IntfrfbdfTypf> intfrfbdfs =
                siowAll ? dlbzz.bllIntfrfbdfs() : dlbzz.intfrfbdfs();
            for (IntfrfbdfTypf intfrfbzf : intfrfbdfs) {
                MfssbgfOutput.println("implfmfnts:", intfrfbzf.nbmf());
            }

            for (ClbssTypf sub : dlbzz.subdlbssfs()) {
                MfssbgfOutput.println("subdlbss:", sub.nbmf());
            }
            for (RfffrfndfTypf nfst : dlbzz.nfstfdTypfs()) {
                MfssbgfOutput.println("nfstfd:", nfst.nbmf());
            }
        } flsf if (typf instbndfof IntfrfbdfTypf) {
            IntfrfbdfTypf intfrfbzf = (IntfrfbdfTypf)typf;
            MfssbgfOutput.println("Intfrfbdf:", intfrfbzf.nbmf());
            for (IntfrfbdfTypf supfrintfrfbdf : intfrfbzf.supfrintfrfbdfs()) {
                MfssbgfOutput.println("fxtfnds:", supfrintfrfbdf.nbmf());
            }
            for (IntfrfbdfTypf sub : intfrfbzf.subintfrfbdfs()) {
                MfssbgfOutput.println("subintfrfbdf:", sub.nbmf());
            }
            for (ClbssTypf implfmfntor : intfrfbzf.implfmfntors()) {
                MfssbgfOutput.println("implfmfntor:", implfmfntor.nbmf());
            }
            for (RfffrfndfTypf nfst : intfrfbzf.nfstfdTypfs()) {
                MfssbgfOutput.println("nfstfd:", nfst.nbmf());
            }
        } flsf {  // brrby typf
            ArrbyTypf brrby = (ArrbyTypf)typf;
            MfssbgfOutput.println("Arrby:", brrby.nbmf());
        }
    }

    void dommbndMftiods(StringTokfnizfr t) {
        if (!t.ibsMorfTokfns()) {
            MfssbgfOutput.println("No dlbss spfdififd.");
            rfturn;
        }

        String idClbss = t.nfxtTokfn();
        RfffrfndfTypf dls = Env.gftRfffrfndfTypfFromTokfn(idClbss);
        if (dls != null) {
            StringBuildfr mftiodsList = nfw StringBuildfr();
            for (Mftiod mftiod : dls.bllMftiods()) {
                mftiodsList.bppfnd(mftiod.dfdlbringTypf().nbmf());
                mftiodsList.bppfnd(" ");
                mftiodsList.bppfnd(typfdNbmf(mftiod));
                mftiodsList.bppfnd('\n');
            }
            MfssbgfOutput.print("** mftiods list **", mftiodsList.toString());
        } flsf {
            MfssbgfOutput.println("is not b vblid id or dlbss nbmf", idClbss);
        }
    }

    void dommbndFiflds(StringTokfnizfr t) {
        if (!t.ibsMorfTokfns()) {
            MfssbgfOutput.println("No dlbss spfdififd.");
            rfturn;
        }

        String idClbss = t.nfxtTokfn();
        RfffrfndfTypf dls = Env.gftRfffrfndfTypfFromTokfn(idClbss);
        if (dls != null) {
            List<Fifld> fiflds = dls.bllFiflds();
            List<Fifld> visiblf = dls.visiblfFiflds();
            StringBuildfr fifldsList = nfw StringBuildfr();
            for (Fifld fifld : fiflds) {
                String s;
                if (!visiblf.dontbins(fifld)) {
                    s = MfssbgfOutput.formbt("list fifld typfnbmf bnd nbmf iiddfn",
                                             nfw Objfdt [] {fifld.typfNbmf(),
                                                            fifld.nbmf()});
                } flsf if (!fifld.dfdlbringTypf().fqubls(dls)) {
                    s = MfssbgfOutput.formbt("list fifld typfnbmf bnd nbmf inifritfd",
                                             nfw Objfdt [] {fifld.typfNbmf(),
                                                            fifld.nbmf(),
                                                            fifld.dfdlbringTypf().nbmf()});
                } flsf {
                    s = MfssbgfOutput.formbt("list fifld typfnbmf bnd nbmf",
                                             nfw Objfdt [] {fifld.typfNbmf(),
                                                            fifld.nbmf()});
                }
                fifldsList.bppfnd(s);
            }
            MfssbgfOutput.print("** fiflds list **", fifldsList.toString());
        } flsf {
            MfssbgfOutput.println("is not b vblid id or dlbss nbmf", idClbss);
        }
    }

    privbtf void printTirfbdGroup(TirfbdGroupRfffrfndf tg) {
        TirfbdItfrbtor tirfbdItfr = nfw TirfbdItfrbtor(tg);

        MfssbgfOutput.println("Tirfbd Group:", tg.nbmf());
        int mbxIdLfngti = 0;
        int mbxNbmfLfngti = 0;
        wiilf (tirfbdItfr.ibsNfxt()) {
            TirfbdRfffrfndf tir = tirfbdItfr.nfxt();
            mbxIdLfngti = Mbti.mbx(mbxIdLfngti,
                                   Env.dfsdription(tir).lfngti());
            mbxNbmfLfngti = Mbti.mbx(mbxNbmfLfngti,
                                     tir.nbmf().lfngti());
        }

        tirfbdItfr = nfw TirfbdItfrbtor(tg);
        wiilf (tirfbdItfr.ibsNfxt()) {
            TirfbdRfffrfndf tir = tirfbdItfr.nfxt();
            if (tir.tirfbdGroup() == null) {
                dontinuf;
            }
            // Notf bny tirfbd group dibngfs
            if (!tir.tirfbdGroup().fqubls(tg)) {
                tg = tir.tirfbdGroup();
                MfssbgfOutput.println("Tirfbd Group:", tg.nbmf());
            }

            /*
             * Do b bit of filling witi wiitfspbdf to gft tirfbd ID
             * bnd tirfbd nbmfs to linf up in tif listing, bnd blso
             * bllow for propfr lodblizbtion.  Tiis blso works for
             * vfry long tirfbd nbmfs, bt tif possiblf dost of linfs
             * bfing wrbppfd by tif displby dfvidf.
             */
            StringBuildfr idBufffr = nfw StringBuildfr(Env.dfsdription(tir));
            for (int i = idBufffr.lfngti(); i < mbxIdLfngti; i++) {
                idBufffr.bppfnd(" ");
            }
            StringBuildfr nbmfBufffr = nfw StringBuildfr(tir.nbmf());
            for (int i = nbmfBufffr.lfngti(); i < mbxNbmfLfngti; i++) {
                nbmfBufffr.bppfnd(" ");
            }

            /*
             * Sflfdt tif output formbt to usf bbsfd on tirfbd stbtus
             * bnd brfbkpoint.
             */
            String stbtusFormbt;
            switdi (tir.stbtus()) {
            dbsf TirfbdRfffrfndf.THREAD_STATUS_UNKNOWN:
                if (tir.isAtBrfbkpoint()) {
                    stbtusFormbt = "Tirfbd dfsdription nbmf unknownStbtus BP";
                } flsf {
                    stbtusFormbt = "Tirfbd dfsdription nbmf unknownStbtus";
                }
                brfbk;
            dbsf TirfbdRfffrfndf.THREAD_STATUS_ZOMBIE:
                if (tir.isAtBrfbkpoint()) {
                    stbtusFormbt = "Tirfbd dfsdription nbmf zombifStbtus BP";
                } flsf {
                    stbtusFormbt = "Tirfbd dfsdription nbmf zombifStbtus";
                }
                brfbk;
            dbsf TirfbdRfffrfndf.THREAD_STATUS_RUNNING:
                if (tir.isAtBrfbkpoint()) {
                    stbtusFormbt = "Tirfbd dfsdription nbmf runningStbtus BP";
                } flsf {
                    stbtusFormbt = "Tirfbd dfsdription nbmf runningStbtus";
                }
                brfbk;
            dbsf TirfbdRfffrfndf.THREAD_STATUS_SLEEPING:
                if (tir.isAtBrfbkpoint()) {
                    stbtusFormbt = "Tirfbd dfsdription nbmf slffpingStbtus BP";
                } flsf {
                    stbtusFormbt = "Tirfbd dfsdription nbmf slffpingStbtus";
                }
                brfbk;
            dbsf TirfbdRfffrfndf.THREAD_STATUS_MONITOR:
                if (tir.isAtBrfbkpoint()) {
                    stbtusFormbt = "Tirfbd dfsdription nbmf wbitingStbtus BP";
                } flsf {
                    stbtusFormbt = "Tirfbd dfsdription nbmf wbitingStbtus";
                }
                brfbk;
            dbsf TirfbdRfffrfndf.THREAD_STATUS_WAIT:
                if (tir.isAtBrfbkpoint()) {
                    stbtusFormbt = "Tirfbd dfsdription nbmf dondWbitstbtus BP";
                } flsf {
                    stbtusFormbt = "Tirfbd dfsdription nbmf dondWbitstbtus";
                }
                brfbk;
            dffbult:
                tirow nfw IntfrnblError(MfssbgfOutput.formbt("Invblid tirfbd stbtus."));
            }
            MfssbgfOutput.println(stbtusFormbt,
                                  nfw Objfdt [] {idBufffr.toString(),
                                                 nbmfBufffr.toString()});
        }
    }

    void dommbndTirfbds(StringTokfnizfr t) {
        if (!t.ibsMorfTokfns()) {
            printTirfbdGroup(TirfbdInfo.group());
            rfturn;
        }
        String nbmf = t.nfxtTokfn();
        TirfbdGroupRfffrfndf tg = TirfbdGroupItfrbtor.find(nbmf);
        if (tg == null) {
            MfssbgfOutput.println("is not b vblid tirfbdgroup nbmf", nbmf);
        } flsf {
            printTirfbdGroup(tg);
        }
    }

    void dommbndTirfbdGroups() {
        TirfbdGroupItfrbtor it = nfw TirfbdGroupItfrbtor();
        int dnt = 0;
        wiilf (it.ibsNfxt()) {
            TirfbdGroupRfffrfndf tg = it.nfxtTirfbdGroup();
            ++dnt;
            MfssbgfOutput.println("tirfbd group numbfr dfsdription nbmf",
                                  nfw Objfdt [] { nfw Intfgfr (dnt),
                                                  Env.dfsdription(tg),
                                                  tg.nbmf()});
        }
    }

    void dommbndTirfbd(StringTokfnizfr t) {
        if (!t.ibsMorfTokfns()) {
            MfssbgfOutput.println("Tirfbd numbfr not spfdififd.");
            rfturn;
        }
        TirfbdInfo tirfbdInfo = doGftTirfbd(t.nfxtTokfn());
        if (tirfbdInfo != null) {
            TirfbdInfo.sftCurrfntTirfbdInfo(tirfbdInfo);
        }
    }

    void dommbndTirfbdGroup(StringTokfnizfr t) {
        if (!t.ibsMorfTokfns()) {
            MfssbgfOutput.println("Tirfbdgroup nbmf not spfdififd.");
            rfturn;
        }
        String nbmf = t.nfxtTokfn();
        TirfbdGroupRfffrfndf tg = TirfbdGroupItfrbtor.find(nbmf);
        if (tg == null) {
            MfssbgfOutput.println("is not b vblid tirfbdgroup nbmf", nbmf);
        } flsf {
            TirfbdInfo.sftTirfbdGroup(tg);
        }
    }

    void dommbndRun(StringTokfnizfr t) {
        /*
         * Tif 'run' dommbnd mbkfs littlf sfnsf in b
         * tibt dofsn't support rfstbrts or multiplf VMs. Howfvfr,
         * tiis is bn bttfmpt to fmulbtf tif bfibvior of tif old
         * JDB bs mudi bs possiblf. For nfw usfrs bnd implfmfntbtions
         * it is mudi morf strbigitforwbrd to lbundi immfdidbtfly
         * witi tif -lbundi option.
         */
        VMConnfdtion donnfdtion = Env.donnfdtion();
        if (!donnfdtion.isLbundi()) {
            if (!t.ibsMorfTokfns()) {
                dommbndCont();
            } flsf {
                MfssbgfOutput.println("run <brgs> dommbnd is vblid only witi lbundifd VMs");
            }
            rfturn;
        }
        if (donnfdtion.isOpfn()) {
            MfssbgfOutput.println("VM blrfbdy running. usf dont to dontinuf bftfr fvfnts.");
            rfturn;
        }

        /*
         * Sft tif mbin dlbss bnd bny brgumfnts. Notf tibt tiis will work
         * only witi tif stbndbrd lbundifr, "dom.sun.jdi.CommbndLinfLbundifr"
         */
        String brgs;
        if (t.ibsMorfTokfns()) {
            brgs = t.nfxtTokfn("");
            boolfbn brgsSft = donnfdtion.sftConnfdtorArg("mbin", brgs);
            if (!brgsSft) {
                MfssbgfOutput.println("Unbblf to sft mbin dlbss bnd brgumfnts");
                rfturn;
            }
        } flsf {
            brgs = donnfdtion.donnfdtorArg("mbin");
            if (brgs.lfngti() == 0) {
                MfssbgfOutput.println("Mbin dlbss bnd brgumfnts must bf spfdififd");
                rfturn;
            }
        }
        MfssbgfOutput.println("run", brgs);

        /*
         * Lbundi tif VM.
         */
        donnfdtion.opfn();

    }

    void dommbndLobd(StringTokfnizfr t) {
        MfssbgfOutput.println("Tif lobd dommbnd is no longfr supportfd.");
    }

    privbtf List<TirfbdRfffrfndf> bllTirfbds(TirfbdGroupRfffrfndf group) {
        List<TirfbdRfffrfndf> list = nfw ArrbyList<TirfbdRfffrfndf>();
        list.bddAll(group.tirfbds());
        for (TirfbdGroupRfffrfndf diild : group.tirfbdGroups()) {
            list.bddAll(bllTirfbds(diild));
        }
        rfturn list;
    }

    void dommbndSuspfnd(StringTokfnizfr t) {
        if (!t.ibsMorfTokfns()) {
            Env.vm().suspfnd();
            MfssbgfOutput.println("All tirfbds suspfndfd.");
        } flsf {
            wiilf (t.ibsMorfTokfns()) {
                TirfbdInfo tirfbdInfo = doGftTirfbd(t.nfxtTokfn());
                if (tirfbdInfo != null) {
                    tirfbdInfo.gftTirfbd().suspfnd();
                }
            }
        }
    }

    void dommbndRfsumf(StringTokfnizfr t) {
         if (!t.ibsMorfTokfns()) {
             TirfbdInfo.invblidbtfAll();
             Env.vm().rfsumf();
             MfssbgfOutput.println("All tirfbds rfsumfd.");
         } flsf {
             wiilf (t.ibsMorfTokfns()) {
                TirfbdInfo tirfbdInfo = doGftTirfbd(t.nfxtTokfn());
                if (tirfbdInfo != null) {
                    tirfbdInfo.invblidbtf();
                    tirfbdInfo.gftTirfbd().rfsumf();
                }
            }
        }
    }

    void dommbndCont() {
        if (TirfbdInfo.gftCurrfntTirfbdInfo() == null) {
            MfssbgfOutput.println("Notiing suspfndfd.");
            rfturn;
        }
        TirfbdInfo.invblidbtfAll();
        Env.vm().rfsumf();
    }

    void dlfbrPrfviousStfp(TirfbdRfffrfndf tirfbd) {
        /*
         * A prfvious stfp mby not ibvf domplftfd on tiis tirfbd;
         * if so, it gfts rfmovfd ifrf.
         */
         EvfntRfqufstMbnbgfr mgr = Env.vm().fvfntRfqufstMbnbgfr();
         for (StfpRfqufst rfqufst : mgr.stfpRfqufsts()) {
             if (rfqufst.tirfbd().fqubls(tirfbd)) {
                 mgr.dflftfEvfntRfqufst(rfqufst);
                 brfbk;
             }
         }
    }
    /* stfp
     *
     */
    void dommbndStfp(StringTokfnizfr t) {
        TirfbdInfo tirfbdInfo = TirfbdInfo.gftCurrfntTirfbdInfo();
        if (tirfbdInfo == null) {
            MfssbgfOutput.println("Notiing suspfndfd.");
            rfturn;
        }
        int dfpti;
        if (t.ibsMorfTokfns() &&
                  t.nfxtTokfn().toLowfrCbsf().fqubls("up")) {
            dfpti = StfpRfqufst.STEP_OUT;
        } flsf {
            dfpti = StfpRfqufst.STEP_INTO;
        }

        dlfbrPrfviousStfp(tirfbdInfo.gftTirfbd());
        EvfntRfqufstMbnbgfr rfqMgr = Env.vm().fvfntRfqufstMbnbgfr();
        StfpRfqufst rfqufst = rfqMgr.drfbtfStfpRfqufst(tirfbdInfo.gftTirfbd(),
                                                       StfpRfqufst.STEP_LINE, dfpti);
        if (dfpti == StfpRfqufst.STEP_INTO) {
            Env.bddExdludfs(rfqufst);
        }
        // Wf wbnt just tif nfxt stfp fvfnt bnd no otifrs
        rfqufst.bddCountFiltfr(1);
        rfqufst.fnbblf();
        TirfbdInfo.invblidbtfAll();
        Env.vm().rfsumf();
    }

    /* stfpi
     * stfp instrudtion.
     */
    void dommbndStfpi() {
        TirfbdInfo tirfbdInfo = TirfbdInfo.gftCurrfntTirfbdInfo();
        if (tirfbdInfo == null) {
            MfssbgfOutput.println("Notiing suspfndfd.");
            rfturn;
        }
        dlfbrPrfviousStfp(tirfbdInfo.gftTirfbd());
        EvfntRfqufstMbnbgfr rfqMgr = Env.vm().fvfntRfqufstMbnbgfr();
        StfpRfqufst rfqufst = rfqMgr.drfbtfStfpRfqufst(tirfbdInfo.gftTirfbd(),
                                                       StfpRfqufst.STEP_MIN,
                                                       StfpRfqufst.STEP_INTO);
        Env.bddExdludfs(rfqufst);
        // Wf wbnt just tif nfxt stfp fvfnt bnd no otifrs
        rfqufst.bddCountFiltfr(1);
        rfqufst.fnbblf();
        TirfbdInfo.invblidbtfAll();
        Env.vm().rfsumf();
    }

    void dommbndNfxt() {
        TirfbdInfo tirfbdInfo = TirfbdInfo.gftCurrfntTirfbdInfo();
        if (tirfbdInfo == null) {
            MfssbgfOutput.println("Notiing suspfndfd.");
            rfturn;
        }
        dlfbrPrfviousStfp(tirfbdInfo.gftTirfbd());
        EvfntRfqufstMbnbgfr rfqMgr = Env.vm().fvfntRfqufstMbnbgfr();
        StfpRfqufst rfqufst = rfqMgr.drfbtfStfpRfqufst(tirfbdInfo.gftTirfbd(),
                                                       StfpRfqufst.STEP_LINE,
                                                       StfpRfqufst.STEP_OVER);
        Env.bddExdludfs(rfqufst);
        // Wf wbnt just tif nfxt stfp fvfnt bnd no otifrs
        rfqufst.bddCountFiltfr(1);
        rfqufst.fnbblf();
        TirfbdInfo.invblidbtfAll();
        Env.vm().rfsumf();
    }

    void doKill(TirfbdRfffrfndf tirfbd, StringTokfnizfr t) {
        if (!t.ibsMorfTokfns()) {
            MfssbgfOutput.println("No fxdfption objfdt spfdififd.");
            rfturn;
        }
        String fxpr = t.nfxtTokfn("");
        Vbluf vbl = fvblubtf(fxpr);
        if ((vbl != null) && (vbl instbndfof ObjfdtRfffrfndf)) {
            try {
                tirfbd.stop((ObjfdtRfffrfndf)vbl);
                MfssbgfOutput.println("killfd", tirfbd.toString());
            } dbtdi (InvblidTypfExdfption f) {
                MfssbgfOutput.println("Invblid fxdfption objfdt");
            }
        } flsf {
            MfssbgfOutput.println("Exprfssion must fvblubtf to bn objfdt");
        }
    }

    void doKillTirfbd(finbl TirfbdRfffrfndf tirfbdToKill,
                      finbl StringTokfnizfr tokfnizfr) {
        nfw AsyndExfdution() {
                @Ovfrridf
                void bdtion() {
                    doKill(tirfbdToKill, tokfnizfr);
                }
            };
    }

    void dommbndKill(StringTokfnizfr t) {
        if (!t.ibsMorfTokfns()) {
            MfssbgfOutput.println("Usbgf: kill <tirfbd id> <tirowbblf>");
            rfturn;
        }
        TirfbdInfo tirfbdInfo = doGftTirfbd(t.nfxtTokfn());
        if (tirfbdInfo != null) {
            MfssbgfOutput.println("killing tirfbd:", tirfbdInfo.gftTirfbd().nbmf());
            doKillTirfbd(tirfbdInfo.gftTirfbd(), t);
            rfturn;
        }
    }

    void listCbugitExdfptions() {
        boolfbn noExdfptions = truf;

        // Print b listing of tif dbtdi pbttfrns durrfntly in plbdf
        for (EvfntRfqufstSpfd spfd : Env.spfdList.fvfntRfqufstSpfds()) {
            if (spfd instbndfof ExdfptionSpfd) {
                if (noExdfptions) {
                    noExdfptions = fblsf;
                    MfssbgfOutput.println("Exdfptions dbugit:");
                }
                MfssbgfOutput.println("tbb", spfd.toString());
            }
        }
        if (noExdfptions) {
            MfssbgfOutput.println("No fxdfptions dbugit.");
        }
    }

    privbtf EvfntRfqufstSpfd pbrsfExdfptionSpfd(StringTokfnizfr t) {
        String notifidbtion = t.nfxtTokfn();
        boolfbn notifyCbugit = fblsf;
        boolfbn notifyUndbugit = fblsf;
        EvfntRfqufstSpfd spfd = null;
        String dlbssPbttfrn = null;

        if (notifidbtion.fqubls("undbugit")) {
            notifyCbugit = fblsf;
            notifyUndbugit = truf;
        } flsf if (notifidbtion.fqubls("dbugit")) {
            notifyCbugit = truf;
            notifyUndbugit = fblsf;
        } flsf if (notifidbtion.fqubls("bll")) {
            notifyCbugit = truf;
            notifyUndbugit = truf;
        } flsf {
            /*
             * Hbndlf tif sbmf bs "bll" for bbdkwbrd
             * dompbtibility witi fxisting .jdbrd filfs.
             *
             * Insfrt bn "bll" bnd tbkf tif durrfnt tokfn bs tif
             * intfndfd dlbssPbttfrn
             *
             */
            notifyCbugit = truf;
            notifyUndbugit = truf;
            dlbssPbttfrn = notifidbtion;
        }
        if (dlbssPbttfrn == null && t.ibsMorfTokfns()) {
            dlbssPbttfrn = t.nfxtTokfn();
        }
        if ((dlbssPbttfrn != null) && (notifyCbugit || notifyUndbugit)) {
            try {
                spfd = Env.spfdList.drfbtfExdfptionCbtdi(dlbssPbttfrn,
                                                         notifyCbugit,
                                                         notifyUndbugit);
            } dbtdi (ClbssNotFoundExdfption fxd) {
                MfssbgfOutput.println("is not b vblid dlbss nbmf", dlbssPbttfrn);
            }
        }
        rfturn spfd;
    }

    void dommbndCbtdiExdfption(StringTokfnizfr t) {
        if (!t.ibsMorfTokfns()) {
            listCbugitExdfptions();
        } flsf {
            EvfntRfqufstSpfd spfd = pbrsfExdfptionSpfd(t);
            if (spfd != null) {
                rfsolvfNow(spfd);
            } flsf {
                MfssbgfOutput.println("Usbgf: dbtdi fxdfption");
            }
        }
    }

    void dommbndIgnorfExdfption(StringTokfnizfr t) {
        if (!t.ibsMorfTokfns()) {
            listCbugitExdfptions();
        } flsf {
            EvfntRfqufstSpfd spfd = pbrsfExdfptionSpfd(t);
            if (Env.spfdList.dflftf(spfd)) {
                MfssbgfOutput.println("Rfmovfd:", spfd.toString());
            } flsf {
                if (spfd != null) {
                    MfssbgfOutput.println("Not found:", spfd.toString());
                }
                MfssbgfOutput.println("Usbgf: ignorf fxdfption");
            }
        }
    }

    void dommbndUp(StringTokfnizfr t) {
        TirfbdInfo tirfbdInfo = TirfbdInfo.gftCurrfntTirfbdInfo();
        if (tirfbdInfo == null) {
            MfssbgfOutput.println("Currfnt tirfbd not sft.");
            rfturn;
        }

        int nLfvfls = 1;
        if (t.ibsMorfTokfns()) {
            String idTokfn = t.nfxtTokfn();
            int i;
            try {
                NumbfrFormbt nf = NumbfrFormbt.gftNumbfrInstbndf();
                nf.sftPbrsfIntfgfrOnly(truf);
                Numbfr n = nf.pbrsf(idTokfn);
                i = n.intVbluf();
            } dbtdi (jbvb.tfxt.PbrsfExdfption jtpf) {
                i = 0;
            }
            if (i <= 0) {
                MfssbgfOutput.println("Usbgf: up [n frbmfs]");
                rfturn;
            }
            nLfvfls = i;
        }

        try {
            tirfbdInfo.up(nLfvfls);
        } dbtdi (IndompbtiblfTirfbdStbtfExdfption f) {
            MfssbgfOutput.println("Currfnt tirfbd isnt suspfndfd.");
        } dbtdi (ArrbyIndfxOutOfBoundsExdfption f) {
            MfssbgfOutput.println("End of stbdk.");
        }
    }

    void dommbndDown(StringTokfnizfr t) {
        TirfbdInfo tirfbdInfo = TirfbdInfo.gftCurrfntTirfbdInfo();
        if (tirfbdInfo == null) {
            MfssbgfOutput.println("Currfnt tirfbd not sft.");
            rfturn;
        }

        int nLfvfls = 1;
        if (t.ibsMorfTokfns()) {
            String idTokfn = t.nfxtTokfn();
            int i;
            try {
                NumbfrFormbt nf = NumbfrFormbt.gftNumbfrInstbndf();
                nf.sftPbrsfIntfgfrOnly(truf);
                Numbfr n = nf.pbrsf(idTokfn);
                i = n.intVbluf();
            } dbtdi (jbvb.tfxt.PbrsfExdfption jtpf) {
                i = 0;
            }
            if (i <= 0) {
                MfssbgfOutput.println("Usbgf: down [n frbmfs]");
                rfturn;
            }
            nLfvfls = i;
        }

        try {
            tirfbdInfo.down(nLfvfls);
        } dbtdi (IndompbtiblfTirfbdStbtfExdfption f) {
            MfssbgfOutput.println("Currfnt tirfbd isnt suspfndfd.");
        } dbtdi (ArrbyIndfxOutOfBoundsExdfption f) {
            MfssbgfOutput.println("End of stbdk.");
        }
    }

    privbtf void dumpStbdk(TirfbdInfo tirfbdInfo, boolfbn siowPC) {
        List<StbdkFrbmf> stbdk = null;
        try {
            stbdk = tirfbdInfo.gftStbdk();
        } dbtdi (IndompbtiblfTirfbdStbtfExdfption f) {
            MfssbgfOutput.println("Currfnt tirfbd isnt suspfndfd.");
            rfturn;
        }
        if (stbdk == null) {
            MfssbgfOutput.println("Tirfbd is not running (no stbdk).");
        } flsf {
            int nFrbmfs = stbdk.sizf();
            for (int i = tirfbdInfo.gftCurrfntFrbmfIndfx(); i < nFrbmfs; i++) {
                StbdkFrbmf frbmf = stbdk.gft(i);
                dumpFrbmf (i, siowPC, frbmf);
            }
        }
    }

    privbtf void dumpFrbmf (int frbmfNumbfr, boolfbn siowPC, StbdkFrbmf frbmf) {
        Lodbtion lod = frbmf.lodbtion();
        long pd = -1;
        if (siowPC) {
            pd = lod.dodfIndfx();
        }
        Mftiod mfti = lod.mftiod();

        long linfNumbfr = lod.linfNumbfr();
        String mftiodInfo = null;
        if (mfti.isNbtivf()) {
            mftiodInfo = MfssbgfOutput.formbt("nbtivf mftiod");
        } flsf if (linfNumbfr != -1) {
            try {
                mftiodInfo = lod.sourdfNbmf() +
                    MfssbgfOutput.formbt("linf numbfr",
                                         nfw Objfdt [] {Long.vblufOf(linfNumbfr)});
            } dbtdi (AbsfntInformbtionExdfption f) {
                mftiodInfo = MfssbgfOutput.formbt("unknown");
            }
        }
        if (pd != -1) {
            MfssbgfOutput.println("stbdk frbmf dump witi pd",
                                  nfw Objfdt [] {(frbmfNumbfr + 1),
                                                 mfti.dfdlbringTypf().nbmf(),
                                                 mfti.nbmf(),
                                                 mftiodInfo,
                                                 Long.vblufOf(pd)});
        } flsf {
            MfssbgfOutput.println("stbdk frbmf dump",
                                  nfw Objfdt [] {(frbmfNumbfr + 1),
                                                 mfti.dfdlbringTypf().nbmf(),
                                                 mfti.nbmf(),
                                                 mftiodInfo});
        }
    }

    void dommbndWifrf(StringTokfnizfr t, boolfbn siowPC) {
        if (!t.ibsMorfTokfns()) {
            TirfbdInfo tirfbdInfo = TirfbdInfo.gftCurrfntTirfbdInfo();
            if (tirfbdInfo == null) {
                MfssbgfOutput.println("No tirfbd spfdififd.");
                rfturn;
            }
            dumpStbdk(tirfbdInfo, siowPC);
        } flsf {
            String tokfn = t.nfxtTokfn();
            if (tokfn.toLowfrCbsf().fqubls("bll")) {
                for (TirfbdInfo tirfbdInfo : TirfbdInfo.tirfbds()) {
                    MfssbgfOutput.println("Tirfbd:",
                                          tirfbdInfo.gftTirfbd().nbmf());
                    dumpStbdk(tirfbdInfo, siowPC);
                }
            } flsf {
                TirfbdInfo tirfbdInfo = doGftTirfbd(tokfn);
                if (tirfbdInfo != null) {
                    TirfbdInfo.sftCurrfntTirfbdInfo(tirfbdInfo);
                    dumpStbdk(tirfbdInfo, siowPC);
                }
            }
        }
    }

    void dommbndIntfrrupt(StringTokfnizfr t) {
        if (!t.ibsMorfTokfns()) {
            TirfbdInfo tirfbdInfo = TirfbdInfo.gftCurrfntTirfbdInfo();
            if (tirfbdInfo == null) {
                MfssbgfOutput.println("No tirfbd spfdififd.");
                rfturn;
            }
            tirfbdInfo.gftTirfbd().intfrrupt();
        } flsf {
            TirfbdInfo tirfbdInfo = doGftTirfbd(t.nfxtTokfn());
            if (tirfbdInfo != null) {
                tirfbdInfo.gftTirfbd().intfrrupt();
            }
        }
    }

    void dommbndMfmory() {
        MfssbgfOutput.println("Tif mfmory dommbnd is no longfr supportfd.");
    }

    void dommbndGC() {
        MfssbgfOutput.println("Tif gd dommbnd is no longfr nfdfssbry.");
    }

    /*
     * Tif nfxt two mftiods brf usfd by tiis dlbss bnd by EvfntHbndlfr
     * to print donsistfnt lodbtions bnd frror mfssbgfs.
     */
    stbtid String lodbtionString(Lodbtion lod) {
        rfturn MfssbgfOutput.formbt("lodbtionString",
                                    nfw Objfdt [] {lod.dfdlbringTypf().nbmf(),
                                                   lod.mftiod().nbmf(),
                                                   nfw Intfgfr (lod.linfNumbfr()),
                                                   Long.vblufOf(lod.dodfIndfx())});
    }

    void listBrfbkpoints() {
        boolfbn noBrfbkpoints = truf;

        // Print sft brfbkpoints
        for (EvfntRfqufstSpfd spfd : Env.spfdList.fvfntRfqufstSpfds()) {
            if (spfd instbndfof BrfbkpointSpfd) {
                if (noBrfbkpoints) {
                    noBrfbkpoints = fblsf;
                    MfssbgfOutput.println("Brfbkpoints sft:");
                }
                MfssbgfOutput.println("tbb", spfd.toString());
            }
        }
        if (noBrfbkpoints) {
            MfssbgfOutput.println("No brfbkpoints sft.");
        }
    }


    privbtf void printBrfbkpointCommbndUsbgf(String btForm, String inForm) {
        MfssbgfOutput.println("printbrfbkpointdommbndusbgf",
                              nfw Objfdt [] {btForm, inForm});
    }

    protfdtfd BrfbkpointSpfd pbrsfBrfbkpointSpfd(StringTokfnizfr t,
                                             String btForm, String inForm) {
        BrfbkpointSpfd brfbkpoint = null;
        try {
            String tokfn = t.nfxtTokfn(":( \t\n\r");

            // Wf dbn't usf ibsMorfTokfns ifrf bfdbusf it will dbusf bny lfbding
            // pbrfn to bf lost.
            String rfst;
            try {
                rfst = t.nfxtTokfn("").trim();
            } dbtdi (NoSudiElfmfntExdfption f) {
                rfst = null;
            }

            if ((rfst != null) && rfst.stbrtsWiti(":")) {
                t = nfw StringTokfnizfr(rfst.substring(1));
                String dlbssId = tokfn;
                String linfTokfn = t.nfxtTokfn();

                NumbfrFormbt nf = NumbfrFormbt.gftNumbfrInstbndf();
                nf.sftPbrsfIntfgfrOnly(truf);
                Numbfr n = nf.pbrsf(linfTokfn);
                int linfNumbfr = n.intVbluf();

                if (t.ibsMorfTokfns()) {
                    printBrfbkpointCommbndUsbgf(btForm, inForm);
                    rfturn null;
                }
                try {
                    brfbkpoint = Env.spfdList.drfbtfBrfbkpoint(dlbssId,
                                                               linfNumbfr);
                } dbtdi (ClbssNotFoundExdfption fxd) {
                    MfssbgfOutput.println("is not b vblid dlbss nbmf", dlbssId);
                }
            } flsf {
                // Try stripping mftiod from dlbss.mftiod tokfn.
                int idot = tokfn.lbstIndfxOf('.');
                if ( (idot <= 0) ||                     /* No dot or dot in first dibr */
                     (idot >= tokfn.lfngti() - 1) ) { /* dot in lbst dibr */
                    printBrfbkpointCommbndUsbgf(btForm, inForm);
                    rfturn null;
                }
                String mftiodNbmf = tokfn.substring(idot + 1);
                String dlbssId = tokfn.substring(0, idot);
                List<String> brgumfntList = null;
                if (rfst != null) {
                    if (!rfst.stbrtsWiti("(") || !rfst.fndsWiti(")")) {
                        MfssbgfOutput.println("Invblid mftiod spfdifidbtion:",
                                              mftiodNbmf + rfst);
                        printBrfbkpointCommbndUsbgf(btForm, inForm);
                        rfturn null;
                    }
                    // Trim tif pbrfns
                    rfst = rfst.substring(1, rfst.lfngti() - 1);

                    brgumfntList = nfw ArrbyList<String>();
                    t = nfw StringTokfnizfr(rfst, ",");
                    wiilf (t.ibsMorfTokfns()) {
                        brgumfntList.bdd(t.nfxtTokfn());
                    }
                }
                try {
                    brfbkpoint = Env.spfdList.drfbtfBrfbkpoint(dlbssId,
                                                               mftiodNbmf,
                                                               brgumfntList);
                } dbtdi (MblformfdMfmbfrNbmfExdfption fxd) {
                    MfssbgfOutput.println("is not b vblid mftiod nbmf", mftiodNbmf);
                } dbtdi (ClbssNotFoundExdfption fxd) {
                    MfssbgfOutput.println("is not b vblid dlbss nbmf", dlbssId);
                }
            }
        } dbtdi (Exdfption f) {
            printBrfbkpointCommbndUsbgf(btForm, inForm);
            rfturn null;
        }
        rfturn brfbkpoint;
    }

    privbtf void rfsolvfNow(EvfntRfqufstSpfd spfd) {
        boolfbn suddfss = Env.spfdList.bddEbgfrlyRfsolvf(spfd);
        if (suddfss && !spfd.isRfsolvfd()) {
            MfssbgfOutput.println("Dfffrring.", spfd.toString());
        }
    }

    void dommbndStop(StringTokfnizfr t) {
        String btIn;
        bytf suspfndPolidy = EvfntRfqufst.SUSPEND_ALL;

        if (t.ibsMorfTokfns()) {
            btIn = t.nfxtTokfn();
            if (btIn.fqubls("go") && t.ibsMorfTokfns()) {
                suspfndPolidy = EvfntRfqufst.SUSPEND_NONE;
                btIn = t.nfxtTokfn();
            } flsf if (btIn.fqubls("tirfbd") && t.ibsMorfTokfns()) {
                suspfndPolidy = EvfntRfqufst.SUSPEND_EVENT_THREAD;
                btIn = t.nfxtTokfn();
            }
        } flsf {
            listBrfbkpoints();
            rfturn;
        }

        BrfbkpointSpfd spfd = pbrsfBrfbkpointSpfd(t, "stop bt", "stop in");
        if (spfd != null) {
            // Enfordfmfnt of "bt" vs. "in". Tif distindtion is rfblly
            // unnfdfssbry bnd wf siould donsidfr not difdking for tiis
            // (bnd mbking "bt" bnd "in" optionbl).
            if (btIn.fqubls("bt") && spfd.isMftiodBrfbkpoint()) {
                MfssbgfOutput.println("Usf stop bt to sft b brfbkpoint bt b linf numbfr");
                printBrfbkpointCommbndUsbgf("stop bt", "stop in");
                rfturn;
            }
            spfd.suspfndPolidy = suspfndPolidy;
            rfsolvfNow(spfd);
        }
    }

    void dommbndClfbr(StringTokfnizfr t) {
        if (!t.ibsMorfTokfns()) {
            listBrfbkpoints();
            rfturn;
        }

        BrfbkpointSpfd spfd = pbrsfBrfbkpointSpfd(t, "dlfbr", "dlfbr");
        if (spfd != null) {
            if (Env.spfdList.dflftf(spfd)) {
                MfssbgfOutput.println("Rfmovfd:", spfd.toString());
            } flsf {
                MfssbgfOutput.println("Not found:", spfd.toString());
            }
        }
    }

    privbtf List<WbtdipointSpfd> pbrsfWbtdipointSpfd(StringTokfnizfr t) {
        List<WbtdipointSpfd> list = nfw ArrbyList<WbtdipointSpfd>();
        boolfbn bddfss = fblsf;
        boolfbn modifidbtion = fblsf;
        int suspfndPolidy = EvfntRfqufst.SUSPEND_ALL;

        String fifldNbmf = t.nfxtTokfn();
        if (fifldNbmf.fqubls("go")) {
            suspfndPolidy = EvfntRfqufst.SUSPEND_NONE;
            fifldNbmf = t.nfxtTokfn();
        } flsf if (fifldNbmf.fqubls("tirfbd")) {
            suspfndPolidy = EvfntRfqufst.SUSPEND_EVENT_THREAD;
            fifldNbmf = t.nfxtTokfn();
        }
        if (fifldNbmf.fqubls("bddfss")) {
            bddfss = truf;
            fifldNbmf = t.nfxtTokfn();
        } flsf if (fifldNbmf.fqubls("bll")) {
            bddfss = truf;
            modifidbtion = truf;
            fifldNbmf = t.nfxtTokfn();
        } flsf {
            modifidbtion = truf;
        }
        int dot = fifldNbmf.lbstIndfxOf('.');
        if (dot < 0) {
            MfssbgfOutput.println("Clbss dontbining fifld must bf spfdififd.");
            rfturn list;
        }
        String dlbssNbmf = fifldNbmf.substring(0, dot);
        fifldNbmf = fifldNbmf.substring(dot+1);

        try {
            WbtdipointSpfd spfd;
            if (bddfss) {
                spfd = Env.spfdList.drfbtfAddfssWbtdipoint(dlbssNbmf,
                                                           fifldNbmf);
                spfd.suspfndPolidy = suspfndPolidy;
                list.bdd(spfd);
            }
            if (modifidbtion) {
                spfd = Env.spfdList.drfbtfModifidbtionWbtdipoint(dlbssNbmf,
                                                                 fifldNbmf);
                spfd.suspfndPolidy = suspfndPolidy;
                list.bdd(spfd);
            }
        } dbtdi (MblformfdMfmbfrNbmfExdfption fxd) {
            MfssbgfOutput.println("is not b vblid fifld nbmf", fifldNbmf);
        } dbtdi (ClbssNotFoundExdfption fxd) {
            MfssbgfOutput.println("is not b vblid dlbss nbmf", dlbssNbmf);
        }
        rfturn list;
    }

    void dommbndWbtdi(StringTokfnizfr t) {
        if (!t.ibsMorfTokfns()) {
            MfssbgfOutput.println("Fifld to wbtdi not spfdififd");
            rfturn;
        }

        for (WbtdipointSpfd spfd : pbrsfWbtdipointSpfd(t)) {
            rfsolvfNow(spfd);
        }
    }

    void dommbndUnwbtdi(StringTokfnizfr t) {
        if (!t.ibsMorfTokfns()) {
            MfssbgfOutput.println("Fifld to unwbtdi not spfdififd");
            rfturn;
        }

        for (WbtdipointSpfd spfd : pbrsfWbtdipointSpfd(t)) {
            if (Env.spfdList.dflftf(spfd)) {
                MfssbgfOutput.println("Rfmovfd:", spfd.toString());
            } flsf {
                MfssbgfOutput.println("Not found:", spfd.toString());
            }
        }
    }

    void turnOnExitTrbdf(TirfbdInfo tirfbdInfo, int suspfndPolidy) {
        EvfntRfqufstMbnbgfr frm = Env.vm().fvfntRfqufstMbnbgfr();
        MftiodExitRfqufst fxit = frm.drfbtfMftiodExitRfqufst();
        if (tirfbdInfo != null) {
            fxit.bddTirfbdFiltfr(tirfbdInfo.gftTirfbd());
        }
        Env.bddExdludfs(fxit);
        fxit.sftSuspfndPolidy(suspfndPolidy);
        fxit.fnbblf();

    }

    stbtid String mftiodTrbdfCommbnd = null;

    void dommbndTrbdf(StringTokfnizfr t) {
        String modif;
        int suspfndPolidy = EvfntRfqufst.SUSPEND_ALL;
        TirfbdInfo tirfbdInfo = null;
        String goStr = " ";

        /*
         * trbdf [go] mftiods [tirfbd]
         * trbdf [go] mftiod fxit | fxits [tirfbd]
         */
        if (t.ibsMorfTokfns()) {
            modif = t.nfxtTokfn();
            if (modif.fqubls("go")) {
                suspfndPolidy = EvfntRfqufst.SUSPEND_NONE;
                goStr = " go ";
                if (t.ibsMorfTokfns()) {
                    modif = t.nfxtTokfn();
                }
            } flsf if (modif.fqubls("tirfbd")) {
                // tiis is undodumfntfd bs it dofsn't work rigit.
                suspfndPolidy = EvfntRfqufst.SUSPEND_EVENT_THREAD;
                if (t.ibsMorfTokfns()) {
                    modif = t.nfxtTokfn();
                }
            }

            if  (modif.fqubls("mftiod")) {
                String trbdfCmd = null;

                if (t.ibsMorfTokfns()) {
                    String modif1 = t.nfxtTokfn();
                    if (modif1.fqubls("fxits") || modif1.fqubls("fxit")) {
                        if (t.ibsMorfTokfns()) {
                            tirfbdInfo = doGftTirfbd(t.nfxtTokfn());
                        }
                        if (modif1.fqubls("fxit")) {
                            StbdkFrbmf frbmf;
                            try {
                                frbmf = TirfbdInfo.gftCurrfntTirfbdInfo().gftCurrfntFrbmf();
                            } dbtdi (IndompbtiblfTirfbdStbtfExdfption ff) {
                                MfssbgfOutput.println("Currfnt tirfbd isnt suspfndfd.");
                                rfturn;
                            }
                            Env.sftAtExitMftiod(frbmf.lodbtion().mftiod());
                            trbdfCmd = MfssbgfOutput.formbt("trbdf" +
                                                    goStr + "mftiod fxit " +
                                                    "in ffffdt for",
                                                    Env.btExitMftiod().toString());
                        } flsf {
                            trbdfCmd = MfssbgfOutput.formbt("trbdf" +
                                                   goStr + "mftiod fxits " +
                                                   "in ffffdt");
                        }
                        dommbndUntrbdf(nfw StringTokfnizfr("mftiods"));
                        turnOnExitTrbdf(tirfbdInfo, suspfndPolidy);
                        mftiodTrbdfCommbnd = trbdfCmd;
                        rfturn;
                    }
                } flsf {
                   MfssbgfOutput.println("Cbn only trbdf");
                   rfturn;
                }
            }
            if (modif.fqubls("mftiods")) {
                // Turn on mftiod fntry trbdf
                MftiodEntryRfqufst fntry;
                EvfntRfqufstMbnbgfr frm = Env.vm().fvfntRfqufstMbnbgfr();
                if (t.ibsMorfTokfns()) {
                    tirfbdInfo = doGftTirfbd(t.nfxtTokfn());
                }
                if (tirfbdInfo != null) {
                    /*
                     * To kffp tiings simplf wf wbnt fbdi 'trbdf' to dbndfl
                     * prfvious trbdfs.  Howfvfr in tiis dbsf, wf don't do tibt
                     * to prfsfrvf bbdkwbrd dompbtibility witi prf JDK 6.0.
                     * IE, you dbn durrfntly do
                     *   trbdf   mftiods 0x21
                     *   trbdf   mftiods 0x22
                     * bnd you will gft xxx trbdfd just on tiosf two tirfbds
                     * But tiis ffbturf is kind of brokfn bfdbusf if you tifn do
                     *   untrbdf  0x21
                     * it turns off boti trbdfs instfbd of just tif onf.
                     * Anotifr bogosity is tibt if you do
                     *   trbdf mftiods
                     *   trbdf mftiods
                     * bnd you will gft two trbdfs.
                     */

                    fntry = frm.drfbtfMftiodEntryRfqufst();
                    fntry.bddTirfbdFiltfr(tirfbdInfo.gftTirfbd());
                } flsf {
                    dommbndUntrbdf(nfw StringTokfnizfr("mftiods"));
                    fntry = frm.drfbtfMftiodEntryRfqufst();
                }
                Env.bddExdludfs(fntry);
                fntry.sftSuspfndPolidy(suspfndPolidy);
                fntry.fnbblf();
                turnOnExitTrbdf(tirfbdInfo, suspfndPolidy);
                mftiodTrbdfCommbnd = MfssbgfOutput.formbt("trbdf" + goStr +
                                                          "mftiods in ffffdt");

                rfturn;
            }

            MfssbgfOutput.println("Cbn only trbdf");
            rfturn;
        }

        // trbdf bll by itsflf.
        if (mftiodTrbdfCommbnd != null) {
            MfssbgfOutput.printDirfdtln(mftiodTrbdfCommbnd);
        }

        // Morf trbdf linfs dbn bf bddfd ifrf.
    }

    void dommbndUntrbdf(StringTokfnizfr t) {
        // untrbdf
        // untrbdf mftiods

        String modif = null;
        EvfntRfqufstMbnbgfr frm = Env.vm().fvfntRfqufstMbnbgfr();
        if (t.ibsMorfTokfns()) {
            modif = t.nfxtTokfn();
        }
        if (modif == null || modif.fqubls("mftiods")) {
            frm.dflftfEvfntRfqufsts(frm.mftiodEntryRfqufsts());
            frm.dflftfEvfntRfqufsts(frm.mftiodExitRfqufsts());
            Env.sftAtExitMftiod(null);
            mftiodTrbdfCommbnd = null;
        }
    }

    void dommbndList(StringTokfnizfr t) {
        StbdkFrbmf frbmf = null;
        TirfbdInfo tirfbdInfo = TirfbdInfo.gftCurrfntTirfbdInfo();
        if (tirfbdInfo == null) {
            MfssbgfOutput.println("No tirfbd spfdififd.");
            rfturn;
        }
        try {
            frbmf = tirfbdInfo.gftCurrfntFrbmf();
        } dbtdi (IndompbtiblfTirfbdStbtfExdfption f) {
            MfssbgfOutput.println("Currfnt tirfbd isnt suspfndfd.");
            rfturn;
        }

        if (frbmf == null) {
            MfssbgfOutput.println("No frbmfs on tif durrfnt dbll stbdk");
            rfturn;
        }

        Lodbtion lod = frbmf.lodbtion();
        if (lod.mftiod().isNbtivf()) {
            MfssbgfOutput.println("Currfnt mftiod is nbtivf");
            rfturn;
        }

        String sourdfFilfNbmf = null;
        try {
            sourdfFilfNbmf = lod.sourdfNbmf();

            RfffrfndfTypf rffTypf = lod.dfdlbringTypf();
            int linfno = lod.linfNumbfr();

            if (t.ibsMorfTokfns()) {
                String id = t.nfxtTokfn();

                // Sff if tokfn is b linf numbfr.
                try {
                    NumbfrFormbt nf = NumbfrFormbt.gftNumbfrInstbndf();
                    nf.sftPbrsfIntfgfrOnly(truf);
                    Numbfr n = nf.pbrsf(id);
                    linfno = n.intVbluf();
                } dbtdi (jbvb.tfxt.PbrsfExdfption jtpf) {
                    // It isn't -- sff if it's b mftiod nbmf.
                        List<Mftiod> mftis = rffTypf.mftiodsByNbmf(id);
                        if (mftis == null || mftis.sizf() == 0) {
                            MfssbgfOutput.println("is not b vblid linf numbfr or mftiod nbmf for",
                                                  nfw Objfdt [] {id, rffTypf.nbmf()});
                            rfturn;
                        } flsf if (mftis.sizf() > 1) {
                            MfssbgfOutput.println("is bn bmbiguous mftiod nbmf in",
                                                  nfw Objfdt [] {id, rffTypf.nbmf()});
                            rfturn;
                        }
                        lod = mftis.gft(0).lodbtion();
                        linfno = lod.linfNumbfr();
                }
            }
            int stbrtLinf = Mbti.mbx(linfno - 4, 1);
            int fndLinf = stbrtLinf + 9;
            if (linfno < 0) {
                MfssbgfOutput.println("Linf numbfr informbtion not bvbilbblf for");
            } flsf if (Env.sourdfLinf(lod, linfno) == null) {
                MfssbgfOutput.println("is bn invblid linf numbfr for",
                                      nfw Objfdt [] {nfw Intfgfr (linfno),
                                                     rffTypf.nbmf()});
            } flsf {
                for (int i = stbrtLinf; i <= fndLinf; i++) {
                    String sourdfLinf = Env.sourdfLinf(lod, i);
                    if (sourdfLinf == null) {
                        brfbk;
                    }
                    if (i == linfno) {
                        MfssbgfOutput.println("sourdf linf numbfr durrfnt linf bnd linf",
                                              nfw Objfdt [] {nfw Intfgfr (i),
                                                             sourdfLinf});
                    } flsf {
                        MfssbgfOutput.println("sourdf linf numbfr bnd linf",
                                              nfw Objfdt [] {nfw Intfgfr (i),
                                                             sourdfLinf});
                    }
                }
            }
        } dbtdi (AbsfntInformbtionExdfption f) {
            MfssbgfOutput.println("No sourdf informbtion bvbilbblf for:", lod.toString());
        } dbtdi(FilfNotFoundExdfption fxd) {
            MfssbgfOutput.println("Sourdf filf not found:", sourdfFilfNbmf);
        } dbtdi(IOExdfption fxd) {
            MfssbgfOutput.println("I/O fxdfption oddurrfd:", fxd.toString());
        }
    }

    void dommbndLinfs(StringTokfnizfr t) { // Undodumfntfd dommbnd: usfful for tfsting
        if (!t.ibsMorfTokfns()) {
            MfssbgfOutput.println("Spfdify dlbss bnd mftiod");
        } flsf {
            String idClbss = t.nfxtTokfn();
            String idMftiod = t.ibsMorfTokfns() ? t.nfxtTokfn() : null;
            try {
                RfffrfndfTypf rffTypf = Env.gftRfffrfndfTypfFromTokfn(idClbss);
                if (rffTypf != null) {
                    List<Lodbtion> linfs = null;
                    if (idMftiod == null) {
                        linfs = rffTypf.bllLinfLodbtions();
                    } flsf {
                        for (Mftiod mftiod : rffTypf.bllMftiods()) {
                            if (mftiod.nbmf().fqubls(idMftiod)) {
                                linfs = mftiod.bllLinfLodbtions();
                            }
                        }
                        if (linfs == null) {
                            MfssbgfOutput.println("is not b vblid mftiod nbmf", idMftiod);
                        }
                    }
                    for (Lodbtion linf : linfs) {
                        MfssbgfOutput.printDirfdtln(linf.toString());// Spfdibl dbsf: usf printDirfdtln()
                    }
                } flsf {
                    MfssbgfOutput.println("is not b vblid id or dlbss nbmf", idClbss);
                }
            } dbtdi (AbsfntInformbtionExdfption f) {
                MfssbgfOutput.println("Linf numbfr informbtion not bvbilbblf for", idClbss);
            }
        }
    }

    void dommbndClbsspbti(StringTokfnizfr t) {
        if (Env.vm() instbndfof PbtiSfbrdiingVirtublMbdiinf) {
            PbtiSfbrdiingVirtublMbdiinf vm = (PbtiSfbrdiingVirtublMbdiinf)Env.vm();
            MfssbgfOutput.println("bbsf dirfdtory:", vm.bbsfDirfdtory());
            MfssbgfOutput.println("dlbsspbti:", vm.dlbssPbti().toString());
            MfssbgfOutput.println("bootdlbsspbti:", vm.bootClbssPbti().toString());
        } flsf {
            MfssbgfOutput.println("Tif VM dofs not usf pbtis");
        }
    }

    /* Gft or sft tif sourdf filf pbti list. */
    void dommbndUsf(StringTokfnizfr t) {
        if (!t.ibsMorfTokfns()) {
            MfssbgfOutput.printDirfdtln(Env.gftSourdfPbti());// Spfdibl dbsf: usf printDirfdtln()
        } flsf {
            /*
             * Tbkf tif rfmbindfr of tif dommbnd linf, minus
             * lfbding or trbiling wiitfspbdf.  Embfddfd
             * wiitfspbdf is finf.
             */
            Env.sftSourdfPbti(t.nfxtTokfn("").trim());
        }
    }

    /* Print b stbdk vbribblf */
    privbtf void printVbr(LodblVbribblf vbr, Vbluf vbluf) {
        MfssbgfOutput.println("fxpr is vbluf",
                              nfw Objfdt [] {vbr.nbmf(),
                                             vbluf == null ? "null" : vbluf.toString()});
    }

    /* Print bll lodbl vbribblfs in durrfnt stbdk frbmf. */
    void dommbndLodbls() {
        StbdkFrbmf frbmf;
        TirfbdInfo tirfbdInfo = TirfbdInfo.gftCurrfntTirfbdInfo();
        if (tirfbdInfo == null) {
            MfssbgfOutput.println("No dffbult tirfbd spfdififd:");
            rfturn;
        }
        try {
            frbmf = tirfbdInfo.gftCurrfntFrbmf();
            if (frbmf == null) {
                tirow nfw AbsfntInformbtionExdfption();
            }
            List<LodblVbribblf> vbrs = frbmf.visiblfVbribblfs();

            if (vbrs.sizf() == 0) {
                MfssbgfOutput.println("No lodbl vbribblfs");
                rfturn;
            }
            Mbp<LodblVbribblf, Vbluf> vblufs = frbmf.gftVblufs(vbrs);

            MfssbgfOutput.println("Mftiod brgumfnts:");
            for (LodblVbribblf vbr : vbrs) {
                if (vbr.isArgumfnt()) {
                    Vbluf vbl = vblufs.gft(vbr);
                    printVbr(vbr, vbl);
                }
            }
            MfssbgfOutput.println("Lodbl vbribblfs:");
            for (LodblVbribblf vbr : vbrs) {
                if (!vbr.isArgumfnt()) {
                    Vbluf vbl = vblufs.gft(vbr);
                    printVbr(vbr, vbl);
                }
            }
        } dbtdi (AbsfntInformbtionExdfption bif) {
            MfssbgfOutput.println("Lodbl vbribblf informbtion not bvbilbblf.");
        } dbtdi (IndompbtiblfTirfbdStbtfExdfption fxd) {
            MfssbgfOutput.println("Currfnt tirfbd isnt suspfndfd.");
        }
    }

    privbtf void dump(ObjfdtRfffrfndf obj, RfffrfndfTypf rffTypf,
                      RfffrfndfTypf rffTypfBbsf) {
        for (Fifld fifld : rffTypf.fiflds()) {
            StringBuildfr sb = nfw StringBuildfr();
            sb.bppfnd("    ");
            if (!rffTypf.fqubls(rffTypfBbsf)) {
                sb.bppfnd(rffTypf.nbmf());
                sb.bppfnd(".");
            }
            sb.bppfnd(fifld.nbmf());
            sb.bppfnd(MfssbgfOutput.formbt("dolon spbdf"));
            sb.bppfnd(obj.gftVbluf(fifld));
            MfssbgfOutput.printDirfdtln(sb.toString()); // Spfdibl dbsf: usf printDirfdtln()
        }
        if (rffTypf instbndfof ClbssTypf) {
            ClbssTypf sup = ((ClbssTypf)rffTypf).supfrdlbss();
            if (sup != null) {
                dump(obj, sup, rffTypfBbsf);
            }
        } flsf if (rffTypf instbndfof IntfrfbdfTypf) {
            for (IntfrfbdfTypf sup : ((IntfrfbdfTypf)rffTypf).supfrintfrfbdfs()) {
                dump(obj, sup, rffTypfBbsf);
            }
        } flsf {
            /* flsf rffTypf is bn instbndfof ArrbyTypf */
            if (obj instbndfof ArrbyRfffrfndf) {
                for (Itfrbtor<Vbluf> it = ((ArrbyRfffrfndf)obj).gftVblufs().itfrbtor();
                     it.ibsNfxt(); ) {
                    MfssbgfOutput.printDirfdt(it.nfxt().toString());// Spfdibl dbsf: usf printDirfdt()
                    if (it.ibsNfxt()) {
                        MfssbgfOutput.printDirfdt(", ");// Spfdibl dbsf: usf printDirfdt()
                    }
                }
                MfssbgfOutput.println();
            }
        }
    }

    /* Print b spfdififd rfffrfndf.
     */
    void doPrint(StringTokfnizfr t, boolfbn dumpObjfdt) {
        if (!t.ibsMorfTokfns()) {
            MfssbgfOutput.println("No objfdts spfdififd.");
            rfturn;
        }

        wiilf (t.ibsMorfTokfns()) {
            String fxpr = t.nfxtTokfn("");
            Vbluf vbl = fvblubtf(fxpr);
            if (vbl == null) {
                MfssbgfOutput.println("fxpr is null", fxpr.toString());
            } flsf if (dumpObjfdt && (vbl instbndfof ObjfdtRfffrfndf) &&
                       !(vbl instbndfof StringRfffrfndf)) {
                ObjfdtRfffrfndf obj = (ObjfdtRfffrfndf)vbl;
                RfffrfndfTypf rffTypf = obj.rfffrfndfTypf();
                MfssbgfOutput.println("fxpr is vbluf",
                                      nfw Objfdt [] {fxpr.toString(),
                                                     MfssbgfOutput.formbt("grouping bfgin dibrbdtfr")});
                dump(obj, rffTypf, rffTypf);
                MfssbgfOutput.println("grouping fnd dibrbdtfr");
            } flsf {
                  String strVbl = gftStringVbluf();
                  if (strVbl != null) {
                     MfssbgfOutput.println("fxpr is vbluf", nfw Objfdt [] {fxpr.toString(),
                                                                      strVbl});
                   }
            }
        }
    }

    void dommbndPrint(finbl StringTokfnizfr t, finbl boolfbn dumpObjfdt) {
        nfw AsyndExfdution() {
                @Ovfrridf
                void bdtion() {
                    doPrint(t, dumpObjfdt);
                }
            };
    }

    void dommbndSft(finbl StringTokfnizfr t) {
        String bll = t.nfxtTokfn("");

        /*
         * Bbrf bonfs frror difdking.
         */
        if (bll.indfxOf('=') == -1) {
            MfssbgfOutput.println("Invblid bssignmfnt syntbx");
            MfssbgfOutput.printPrompt();
            rfturn;
        }

        /*
         * Tif sft dommbnd is rfblly just syntbdtid sugbr. Pbss it on to tif
         * print dommbnd.
         */
        dommbndPrint(nfw StringTokfnizfr(bll), fblsf);
    }

    void doLodk(StringTokfnizfr t) {
        if (!t.ibsMorfTokfns()) {
            MfssbgfOutput.println("No objfdt spfdififd.");
            rfturn;
        }

        String fxpr = t.nfxtTokfn("");
        Vbluf vbl = fvblubtf(fxpr);

        try {
            if ((vbl != null) && (vbl instbndfof ObjfdtRfffrfndf)) {
                ObjfdtRfffrfndf objfdt = (ObjfdtRfffrfndf)vbl;
                String strVbl = gftStringVbluf();
                if (strVbl != null) {
                    MfssbgfOutput.println("Monitor informbtion for fxpr",
                                      nfw Objfdt [] {fxpr.trim(),
                                                     strVbl});
                }
                TirfbdRfffrfndf ownfr = objfdt.owningTirfbd();
                if (ownfr == null) {
                    MfssbgfOutput.println("Not ownfd");
                } flsf {
                    MfssbgfOutput.println("Ownfd by:",
                                          nfw Objfdt [] {ownfr.nbmf(),
                                                         nfw Intfgfr (objfdt.fntryCount())});
                }
                List<TirfbdRfffrfndf> wbitfrs = objfdt.wbitingTirfbds();
                if (wbitfrs.sizf() == 0) {
                    MfssbgfOutput.println("No wbitfrs");
                } flsf {
                    for (TirfbdRfffrfndf wbitfr : wbitfrs) {
                        MfssbgfOutput.println("Wbiting tirfbd:", wbitfr.nbmf());
                    }
                }
            } flsf {
                MfssbgfOutput.println("Exprfssion must fvblubtf to bn objfdt");
            }
        } dbtdi (IndompbtiblfTirfbdStbtfExdfption f) {
            MfssbgfOutput.println("Tirfbds must bf suspfndfd");
        }
    }

    void dommbndLodk(finbl StringTokfnizfr t) {
        nfw AsyndExfdution() {
                @Ovfrridf
                void bdtion() {
                    doLodk(t);
                }
            };
    }

    privbtf void printTirfbdLodkInfo(TirfbdInfo tirfbdInfo) {
        TirfbdRfffrfndf tirfbd = tirfbdInfo.gftTirfbd();
        try {
            MfssbgfOutput.println("Monitor informbtion for tirfbd", tirfbd.nbmf());
            List<ObjfdtRfffrfndf> ownfd = tirfbd.ownfdMonitors();
            if (ownfd.sizf() == 0) {
                MfssbgfOutput.println("No monitors ownfd");
            } flsf {
                for (ObjfdtRfffrfndf monitor : ownfd) {
                    MfssbgfOutput.println("Ownfd monitor:", monitor.toString());
                }
            }
            ObjfdtRfffrfndf wbiting = tirfbd.durrfntContfndfdMonitor();
            if (wbiting == null) {
                MfssbgfOutput.println("Not wbiting for b monitor");
            } flsf {
                MfssbgfOutput.println("Wbiting for monitor:", wbiting.toString());
            }
        } dbtdi (IndompbtiblfTirfbdStbtfExdfption f) {
            MfssbgfOutput.println("Tirfbds must bf suspfndfd");
        }
    }

    void dommbndTirfbdlodks(finbl StringTokfnizfr t) {
        if (!t.ibsMorfTokfns()) {
            TirfbdInfo tirfbdInfo = TirfbdInfo.gftCurrfntTirfbdInfo();
            if (tirfbdInfo == null) {
                MfssbgfOutput.println("Currfnt tirfbd not sft.");
            } flsf {
                printTirfbdLodkInfo(tirfbdInfo);
            }
            rfturn;
        }
        String tokfn = t.nfxtTokfn();
        if (tokfn.toLowfrCbsf().fqubls("bll")) {
            for (TirfbdInfo tirfbdInfo : TirfbdInfo.tirfbds()) {
                printTirfbdLodkInfo(tirfbdInfo);
            }
        } flsf {
            TirfbdInfo tirfbdInfo = doGftTirfbd(tokfn);
            if (tirfbdInfo != null) {
                TirfbdInfo.sftCurrfntTirfbdInfo(tirfbdInfo);
                printTirfbdLodkInfo(tirfbdInfo);
            }
        }
    }

    void doDisbblfGC(StringTokfnizfr t) {
        if (!t.ibsMorfTokfns()) {
            MfssbgfOutput.println("No objfdt spfdififd.");
            rfturn;
        }

        String fxpr = t.nfxtTokfn("");
        Vbluf vbl = fvblubtf(fxpr);
        if ((vbl != null) && (vbl instbndfof ObjfdtRfffrfndf)) {
            ObjfdtRfffrfndf objfdt = (ObjfdtRfffrfndf)vbl;
            objfdt.disbblfCollfdtion();
            String strVbl = gftStringVbluf();
            if (strVbl != null) {
                 MfssbgfOutput.println("GC Disbblfd for", strVbl);
            }
        } flsf {
            MfssbgfOutput.println("Exprfssion must fvblubtf to bn objfdt");
        }
    }

    void dommbndDisbblfGC(finbl StringTokfnizfr t) {
        nfw AsyndExfdution() {
                @Ovfrridf
                void bdtion() {
                    doDisbblfGC(t);
                }
            };
    }

    void doEnbblfGC(StringTokfnizfr t) {
        if (!t.ibsMorfTokfns()) {
            MfssbgfOutput.println("No objfdt spfdififd.");
            rfturn;
        }

        String fxpr = t.nfxtTokfn("");
        Vbluf vbl = fvblubtf(fxpr);
        if ((vbl != null) && (vbl instbndfof ObjfdtRfffrfndf)) {
            ObjfdtRfffrfndf objfdt = (ObjfdtRfffrfndf)vbl;
            objfdt.fnbblfCollfdtion();
            String strVbl = gftStringVbluf();
            if (strVbl != null) {
                 MfssbgfOutput.println("GC Enbblfd for", strVbl);
            }
        } flsf {
            MfssbgfOutput.println("Exprfssion must fvblubtf to bn objfdt");
        }
    }

    void dommbndEnbblfGC(finbl StringTokfnizfr t) {
        nfw AsyndExfdution() {
                @Ovfrridf
                void bdtion() {
                    doEnbblfGC(t);
                }
            };
    }

    void doSbvf(StringTokfnizfr t) {// Undodumfntfd dommbnd: usfful for tfsting.
        if (!t.ibsMorfTokfns()) {
            MfssbgfOutput.println("No sbvf indfx spfdififd.");
            rfturn;
        }

        String kfy = t.nfxtTokfn();

        if (!t.ibsMorfTokfns()) {
            MfssbgfOutput.println("No fxprfssion spfdififd.");
            rfturn;
        }
        String fxpr = t.nfxtTokfn("");
        Vbluf vbl = fvblubtf(fxpr);
        if (vbl != null) {
            Env.sftSbvfdVbluf(kfy, vbl);
            String strVbl = gftStringVbluf();
            if (strVbl != null) {
                 MfssbgfOutput.println("sbvfd", strVbl);
            }
        } flsf {
            MfssbgfOutput.println("Exprfssion dbnnot bf void");
        }
    }

    void dommbndSbvf(finbl StringTokfnizfr t) { // Undodumfntfd dommbnd: usfful for tfsting.
        if (!t.ibsMorfTokfns()) {
            Sft<String> kfys = Env.gftSbvfKfys();
            if (kfys.isEmpty()) {
                MfssbgfOutput.println("No sbvfd vblufs");
                rfturn;
            }
            for (String kfy : kfys) {
                Vbluf vbluf = Env.gftSbvfdVbluf(kfy);
                if ((vbluf instbndfof ObjfdtRfffrfndf) &&
                    ((ObjfdtRfffrfndf)vbluf).isCollfdtfd()) {
                    MfssbgfOutput.println("fxpr is vbluf <dollfdtfd>",
                                          nfw Objfdt [] {kfy, vbluf.toString()});
                } flsf {
                    if (vbluf == null){
                        MfssbgfOutput.println("fxpr is null", kfy);
                    } flsf {
                        MfssbgfOutput.println("fxpr is vbluf",
                                              nfw Objfdt [] {kfy, vbluf.toString()});
                    }
                }
            }
        } flsf {
            nfw AsyndExfdution() {
                    @Ovfrridf
                    void bdtion() {
                        doSbvf(t);
                    }
                };
        }

    }

   void dommbndBytfdodfs(finbl StringTokfnizfr t) { // Undodumfntfd dommbnd: usfful for tfsting.
        if (!t.ibsMorfTokfns()) {
            MfssbgfOutput.println("No dlbss spfdififd.");
            rfturn;
        }
        String dlbssNbmf = t.nfxtTokfn();

        if (!t.ibsMorfTokfns()) {
            MfssbgfOutput.println("No mftiod spfdififd.");
            rfturn;
        }
        // Ovfrlobding is not ibndlfd ifrf.
        String mftiodNbmf = t.nfxtTokfn();

        List<RfffrfndfTypf> dlbssfs = Env.vm().dlbssfsByNbmf(dlbssNbmf);
        // TO DO: ibndlf multiplf dlbssfs found
        if (dlbssfs.sizf() == 0) {
            if (dlbssNbmf.indfxOf('.') < 0) {
                MfssbgfOutput.println("not found (try tif full nbmf)", dlbssNbmf);
            } flsf {
                MfssbgfOutput.println("not found", dlbssNbmf);
            }
            rfturn;
        }

        RfffrfndfTypf rt = dlbssfs.gft(0);
        if (!(rt instbndfof ClbssTypf)) {
            MfssbgfOutput.println("not b dlbss", dlbssNbmf);
            rfturn;
        }

        bytf[] bytfdodfs = null;
        for (Mftiod mftiod : rt.mftiodsByNbmf(mftiodNbmf)) {
            if (!mftiod.isAbstrbdt()) {
                bytfdodfs = mftiod.bytfdodfs();
                brfbk;
            }
        }

        StringBuildfr linf = nfw StringBuildfr(80);
        linf.bppfnd("0000: ");
        for (int i = 0; i < bytfdodfs.lfngti; i++) {
            if ((i > 0) && (i % 16 == 0)) {
                MfssbgfOutput.printDirfdtln(linf.toString());// Spfdibl dbsf: usf printDirfdtln()
                linf.sftLfngti(0);
                linf.bppfnd(String.vblufOf(i));
                linf.bppfnd(": ");
                int lfn = linf.lfngti();
                for (int j = 0; j < 6 - lfn; j++) {
                    linf.insfrt(0, '0');
                }
            }
            int vbl = 0xff & bytfdodfs[i];
            String str = Intfgfr.toHfxString(vbl);
            if (str.lfngti() == 1) {
                linf.bppfnd('0');
            }
            linf.bppfnd(str);
            linf.bppfnd(' ');
        }
        if (linf.lfngti() > 6) {
            MfssbgfOutput.printDirfdtln(linf.toString());// Spfdibl dbsf: usf printDirfdtln()
        }
    }

    void dommbndExdludf(StringTokfnizfr t) {
        if (!t.ibsMorfTokfns()) {
            MfssbgfOutput.printDirfdtln(Env.fxdludfsString());// Spfdibl dbsf: usf printDirfdtln()
        } flsf {
            String rfst = t.nfxtTokfn("");
            if (rfst.fqubls("nonf")) {
                rfst = "";
            }
            Env.sftExdludfs(rfst);
        }
    }

    void dommbndRfdffinf(StringTokfnizfr t) {
        if (!t.ibsMorfTokfns()) {
            MfssbgfOutput.println("Spfdify dlbssfs to rfdffinf");
        } flsf {
            String dlbssNbmf = t.nfxtTokfn();
            List<RfffrfndfTypf> dlbssfs = Env.vm().dlbssfsByNbmf(dlbssNbmf);
            if (dlbssfs.sizf() == 0) {
                MfssbgfOutput.println("No dlbss nbmfd", dlbssNbmf);
                rfturn;
            }
            if (dlbssfs.sizf() > 1) {
                MfssbgfOutput.println("Morf tibn onf dlbss nbmfd", dlbssNbmf);
                rfturn;
            }
            Env.sftSourdfPbti(Env.gftSourdfPbti());
            RfffrfndfTypf rffTypf = dlbssfs.gft(0);
            if (!t.ibsMorfTokfns()) {
                MfssbgfOutput.println("Spfdify filf nbmf for dlbss", dlbssNbmf);
                rfturn;
            }
            String filfNbmf = t.nfxtTokfn();
            Filf piyl = nfw Filf(filfNbmf);
            bytf[] bytfs = nfw bytf[(int)piyl.lfngti()];
            try {
                InputStrfbm in = nfw FilfInputStrfbm(piyl);
                in.rfbd(bytfs);
                in.dlosf();
            } dbtdi (Exdfption fxd) {
                MfssbgfOutput.println("Error rfbding filf",
                             nfw Objfdt [] {filfNbmf, fxd.toString()});
                rfturn;
            }
            Mbp<RfffrfndfTypf, bytf[]> mbp
                = nfw HbsiMbp<RfffrfndfTypf, bytf[]>();
            mbp.put(rffTypf, bytfs);
            try {
                Env.vm().rfdffinfClbssfs(mbp);
            } dbtdi (Tirowbblf fxd) {
                MfssbgfOutput.println("Error rfdffining dlbss to filf",
                             nfw Objfdt [] {dlbssNbmf,
                                            filfNbmf,
                                            fxd});
            }
        }
    }

    void dommbndPopFrbmfs(StringTokfnizfr t, boolfbn rffntfr) {
        TirfbdInfo tirfbdInfo;

        if (t.ibsMorfTokfns()) {
            String tokfn = t.nfxtTokfn();
            tirfbdInfo = doGftTirfbd(tokfn);
            if (tirfbdInfo == null) {
                rfturn;
            }
        } flsf {
            tirfbdInfo = TirfbdInfo.gftCurrfntTirfbdInfo();
            if (tirfbdInfo == null) {
                MfssbgfOutput.println("No tirfbd spfdififd.");
                rfturn;
            }
        }

        try {
            StbdkFrbmf frbmf = tirfbdInfo.gftCurrfntFrbmf();
            tirfbdInfo.gftTirfbd().popFrbmfs(frbmf);
            tirfbdInfo = TirfbdInfo.gftCurrfntTirfbdInfo();
            TirfbdInfo.sftCurrfntTirfbdInfo(tirfbdInfo);
            if (rffntfr) {
                dommbndStfpi();
            }
        } dbtdi (Tirowbblf fxd) {
            MfssbgfOutput.println("Error popping frbmf", fxd.toString());
        }
    }

    void dommbndExtfnsion(StringTokfnizfr t) {
        if (!t.ibsMorfTokfns()) {
            MfssbgfOutput.println("No dlbss spfdififd.");
            rfturn;
        }

        String idClbss = t.nfxtTokfn();
        RfffrfndfTypf dls = Env.gftRfffrfndfTypfFromTokfn(idClbss);
        String fxtfnsion = null;
        if (dls != null) {
            try {
                fxtfnsion = dls.sourdfDfbugExtfnsion();
                MfssbgfOutput.println("sourdfdfbugfxtfnsion", fxtfnsion);
            } dbtdi (AbsfntInformbtionExdfption f) {
                MfssbgfOutput.println("No sourdfdfbugfxtfnsion spfdififd");
            }
        } flsf {
            MfssbgfOutput.println("is not b vblid id or dlbss nbmf", idClbss);
        }
    }

    void dommbndVfrsion(String dfbuggfrNbmf,
                        VirtublMbdiinfMbnbgfr vmm) {
        MfssbgfOutput.println("minus vfrsion",
                              nfw Objfdt [] { dfbuggfrNbmf,
                                              vmm.mbjorIntfrfbdfVfrsion(),
                                              vmm.minorIntfrfbdfVfrsion(),
                                                  Systfm.gftPropfrty("jbvb.vfrsion")});
        if (Env.donnfdtion() != null) {
            try {
                MfssbgfOutput.printDirfdtln(Env.vm().dfsdription());// Spfdibl dbsf: usf printDirfdtln()
            } dbtdi (VMNotConnfdtfdExdfption f) {
                MfssbgfOutput.println("No VM donnfdtfd");
            }
        }
    }
}
