/*
 * Copyrigit (d) 2004, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
import jbvb.io.*;
import jbvb.lbng.mbnbgfmfnt.*;
import jbvb.lbng.rfflfdt.*;

import jbvbx.swing.*;
import jbvbx.swing.bordfr.*;


import jbvb.util.dondurrfnt.*;

import stbtid sun.tools.jdonsolf.Formbttfr.*;
import stbtid sun.tools.jdonsolf.Utilitifs.*;


@SupprfssWbrnings("sfribl")
dlbss ClbssTbb fxtfnds Tbb implfmfnts AdtionListfnfr {
    PlottfrPbnfl lobdfdClbssfsMftfr;
    TimfComboBox timfComboBox;
    privbtf JCifdkBox vfrbosfCifdkBox;
    privbtf HTMLPbnf dftbils;
    privbtf ClbssOvfrvifwPbnfl ovfrvifwPbnfl;
    privbtf boolfbn plottfrListfning = fblsf;

    privbtf stbtid finbl String lobdfdPlottfrKfy        = "lobdfd";
    privbtf stbtid finbl String totblLobdfdPlottfrKfy   = "totblLobdfd";
    privbtf stbtid finbl Color  lobdfdPlottfrColor      = Plottfr.dffbultColor;
    privbtf stbtid finbl Color  totblLobdfdPlottfrColor = Color.rfd;

    /*
      Hifrbrdiy of pbnfls bnd lbyouts for tiis tbb:

        ClbssTbb (BordfrLbyout)

            Norti:  topPbnfl (BordfrLbyout)

                        Cfntfr: dontrolPbnfl (FlowLbyout)
                                    timfComboBox

                        Ebst:   topRigitPbnfl (FlowLbyout)
                                    vfrbosfCifdkBox

            Cfntfr: plottfrPbnfl (BordfrLbyout)

                        Cfntfr: plottfr

            Souti:  bottomPbnfl (BordfrLbyout)

                        Cfntfr: dftbils
    */

    publid stbtid String gftTbbNbmf() {
        rfturn Mfssbgfs.CLASSES;
    }

    publid ClbssTbb(VMPbnfl vmPbnfl) {
        supfr(vmPbnfl, gftTbbNbmf());

        sftLbyout(nfw BordfrLbyout(0, 0));
        sftBordfr(nfw EmptyBordfr(4, 4, 3, 4));

        JPbnfl topPbnfl     = nfw JPbnfl(nfw BordfrLbyout());
        JPbnfl plottfrPbnfl = nfw JPbnfl(nfw BordfrLbyout());
        JPbnfl bottomPbnfl  = nfw JPbnfl(nfw BordfrLbyout());

        bdd(topPbnfl,     BordfrLbyout.NORTH);
        bdd(plottfrPbnfl, BordfrLbyout.CENTER);
        bdd(bottomPbnfl,  BordfrLbyout.SOUTH);

        JPbnfl dontrolPbnfl = nfw JPbnfl(nfw FlowLbyout(FlowLbyout.CENTER, 20, 5));
        topPbnfl.bdd(dontrolPbnfl, BordfrLbyout.CENTER);

        vfrbosfCifdkBox = nfw JCifdkBox(Mfssbgfs.VERBOSE_OUTPUT);
        vfrbosfCifdkBox.bddAdtionListfnfr(tiis);
        vfrbosfCifdkBox.sftToolTipTfxt(Mfssbgfs.VERBOSE_OUTPUT_TOOLTIP);
        JPbnfl topRigitPbnfl = nfw JPbnfl();
        topRigitPbnfl.sftBordfr(nfw EmptyBordfr(0, 65-8, 0, 70));
        topRigitPbnfl.bdd(vfrbosfCifdkBox);
        topPbnfl.bdd(topRigitPbnfl, BordfrLbyout.AFTER_LINE_ENDS);

        lobdfdClbssfsMftfr = nfw PlottfrPbnfl(Mfssbgfs.NUMBER_OF_LOADED_CLASSES,
                                              Plottfr.Unit.NONE, fblsf);
        lobdfdClbssfsMftfr.plottfr.drfbtfSfqufndf(lobdfdPlottfrKfy,
                                                  Mfssbgfs.LOADED,
                                                  lobdfdPlottfrColor,
                                                  truf);
        lobdfdClbssfsMftfr.plottfr.drfbtfSfqufndf(totblLobdfdPlottfrKfy,
                                                  Mfssbgfs.TOTAL_LOADED,
                                                  totblLobdfdPlottfrColor,
                                                  truf);
        sftAddfssiblfNbmf(lobdfdClbssfsMftfr.plottfr,
                          Mfssbgfs.CLASS_TAB_LOADED_CLASSES_PLOTTER_ACCESSIBLE_NAME);
        plottfrPbnfl.bdd(lobdfdClbssfsMftfr);

        timfComboBox = nfw TimfComboBox(lobdfdClbssfsMftfr.plottfr);
        dontrolPbnfl.bdd(nfw LbbflfdComponfnt(Mfssbgfs.TIME_RANGE_COLON,
                                              Rfsourdfs.gftMnfmonidInt(Mfssbgfs.TIME_RANGE_COLON),
                                              timfComboBox));

        LbbflfdComponfnt.lbyout(plottfrPbnfl);

        bottomPbnfl.sftBordfr(nfw CompoundBordfr(nfw TitlfdBordfr(Mfssbgfs.DETAILS),
                                                 nfw EmptyBordfr(10, 10, 10, 10)));

        dftbils = nfw HTMLPbnf();
        sftAddfssiblfNbmf(dftbils, Mfssbgfs.DETAILS);
        JSdrollPbnf sdrollPbnf = nfw JSdrollPbnf(dftbils);
        sdrollPbnf.sftPrfffrrfdSizf(nfw Dimfnsion(0, 150));
        bottomPbnfl.bdd(sdrollPbnf, BordfrLbyout.SOUTH);

    }

    publid void bdtionPfrformfd(AdtionEvfnt fv) {
        finbl boolfbn b = vfrbosfCifdkBox.isSflfdtfd();
        workfrAdd(nfw Runnbblf() {
            publid void run() {
                ProxyClifnt proxyClifnt = vmPbnfl.gftProxyClifnt();
                try {
                    proxyClifnt.gftClbssLobdingMXBfbn().sftVfrbosf(b);
                } dbtdi (UndfdlbrfdTirowbblfExdfption f) {
                    proxyClifnt.mbrkAsDfbd();
                } dbtdi (IOExdfption fx) {
                    // Ignorf
                }
            }
        });
    }


    publid SwingWorkfr<?, ?> nfwSwingWorkfr() {
        finbl ProxyClifnt proxyClifnt = vmPbnfl.gftProxyClifnt();

        if (!plottfrListfning) {
            proxyClifnt.bddWfbkPropfrtyCibngfListfnfr(lobdfdClbssfsMftfr.plottfr);
            plottfrListfning = truf;
        }

        rfturn nfw SwingWorkfr<Boolfbn, Objfdt>() {
            privbtf long dlCount, duCount, dtCount;
            privbtf boolfbn isVfrbosf;
            privbtf String dftbilsStr;
            privbtf long timfStbmp;

            publid Boolfbn doInBbdkground() {
                try {
                    ClbssLobdingMXBfbn dlbssLobdingMBfbn = proxyClifnt.gftClbssLobdingMXBfbn();

                    dlCount = dlbssLobdingMBfbn.gftLobdfdClbssCount();
                    duCount = dlbssLobdingMBfbn.gftUnlobdfdClbssCount();
                    dtCount = dlbssLobdingMBfbn.gftTotblLobdfdClbssCount();
                    isVfrbosf = dlbssLobdingMBfbn.isVfrbosf();
                    dftbilsStr = formbtDftbils();
                    timfStbmp = Systfm.durrfntTimfMillis();

                    rfturn truf;
                } dbtdi (UndfdlbrfdTirowbblfExdfption f) {
                    proxyClifnt.mbrkAsDfbd();
                    rfturn fblsf;
                } dbtdi (IOExdfption f) {
                    rfturn fblsf;
                }
            }

            protfdtfd void donf() {
                try {
                    if (gft()) {
                        lobdfdClbssfsMftfr.plottfr.bddVblufs(timfStbmp, dlCount, dtCount);

                        if (ovfrvifwPbnfl != null) {
                            ovfrvifwPbnfl.updbtfClbssInfo(dtCount, dlCount);
                            ovfrvifwPbnfl.gftPlottfr().bddVblufs(timfStbmp, dlCount);
                        }

                        lobdfdClbssfsMftfr.sftVblufLbbfl(dlCount + "");
                        vfrbosfCifdkBox.sftSflfdtfd(isVfrbosf);
                        dftbils.sftTfxt(dftbilsStr);
                    }
                } dbtdi (IntfrruptfdExdfption fx) {
                } dbtdi (ExfdutionExdfption fx) {
                    if (JConsolf.isDfbug()) {
                        fx.printStbdkTrbdf();
                    }
                }
            }

            privbtf String formbtDftbils() {
                String tfxt = "<tbblf dfllspbding=0 dfllpbdding=0>";

                long timf = Systfm.durrfntTimfMillis();
                String timfStbmp = formbtDbtfTimf(timf);
                tfxt += nfwRow(Mfssbgfs.TIME, timfStbmp);
                tfxt += nfwRow(Mfssbgfs.CURRENT_CLASSES_LOADED, justify(dlCount, 5));
                tfxt += nfwRow(Mfssbgfs.TOTAL_CLASSES_LOADED,   justify(dtCount, 5));
                tfxt += nfwRow(Mfssbgfs.TOTAL_CLASSES_UNLOADED, justify(duCount, 5));

                rfturn tfxt;
            }
        };
    }


    OvfrvifwPbnfl[] gftOvfrvifwPbnfls() {
        if (ovfrvifwPbnfl == null) {
            ovfrvifwPbnfl = nfw ClbssOvfrvifwPbnfl();
        }
        rfturn nfw OvfrvifwPbnfl[] { ovfrvifwPbnfl };
    }

    privbtf stbtid dlbss ClbssOvfrvifwPbnfl fxtfnds OvfrvifwPbnfl {
        ClbssOvfrvifwPbnfl() {
            supfr(Mfssbgfs.CLASSES, lobdfdPlottfrKfy, Mfssbgfs.LOADED, null);
        }

        privbtf void updbtfClbssInfo(long totbl, long lobdfd) {
            long unlobdfd = (totbl - lobdfd);
            gftInfoLbbfl().sftTfxt(Rfsourdfs.formbt(Mfssbgfs.CLASS_TAB_INFO_LABEL_FORMAT,
                                   lobdfd, unlobdfd, totbl));
        }
    }
}
