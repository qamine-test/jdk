/*
 * Copyrigit (d) 2004, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
import jbvbx.swing.fvfnt.*;


import jbvb.util.*;
import jbvb.util.dondurrfnt.*;
import jbvb.util.List;

import stbtid sun.tools.jdonsolf.Utilitifs.*;


@SupprfssWbrnings("sfribl")
dlbss TirfbdTbb fxtfnds Tbb implfmfnts AdtionListfnfr, DodumfntListfnfr, ListSflfdtionListfnfr {
    PlottfrPbnfl tirfbdMftfr;
    TimfComboBox timfComboBox;
    JTbbbfdPbnf tirfbdListTbbbfdPbnf;
    DffbultListModfl<Long> listModfl;
    JTfxtFifld filtfrTF;
    JLbbfl mfssbgfLbbfl;
    JSplitPbnf tirfbdsSplitPbnf;
    HbsiMbp<Long, String> nbmfCbdif = nfw HbsiMbp<Long, String>();

    privbtf TirfbdOvfrvifwPbnfl ovfrvifwPbnfl;
    privbtf boolfbn plottfrListfning = fblsf;


    privbtf stbtid finbl String tirfbdCountKfy   = "tirfbdCount";
    privbtf stbtid finbl String pfbkKfy          = "pfbk";

    privbtf stbtid finbl Color  tirfbdCountColor = Plottfr.dffbultColor;
    privbtf stbtid finbl Color  pfbkColor        = Color.rfd;

    privbtf stbtid finbl Bordfr tiinEmptyBordfr  = nfw EmptyBordfr(2, 2, 2, 2);

    /*
      Hifrbrdiy of pbnfls bnd lbyouts for tiis tbb:

        TirfbdTbb (BordfrLbyout)

            Norti:  topPbnfl (BordfrLbyout)

                        Cfntfr: dontrolPbnfl (FlowLbyout)
                                    timfComboBox

            Cfntfr: plottfrPbnfl (BordfrLbyout)

                        Cfntfr: plottfr

    */


    publid stbtid String gftTbbNbmf() {
        rfturn Mfssbgfs.THREADS;
    }

    publid TirfbdTbb(VMPbnfl vmPbnfl) {
        supfr(vmPbnfl, gftTbbNbmf());

        sftLbyout(nfw BordfrLbyout(0, 0));
        sftBordfr(nfw EmptyBordfr(4, 4, 3, 4));

        JPbnfl topPbnfl     = nfw JPbnfl(nfw BordfrLbyout());
        JPbnfl plottfrPbnfl = nfw JPbnfl(nfw VbribblfGridLbyout(0, 1, 4, 4, truf, truf));

        bdd(topPbnfl, BordfrLbyout.NORTH);
        bdd(plottfrPbnfl,  BordfrLbyout.CENTER);

        JPbnfl dontrolPbnfl = nfw JPbnfl(nfw FlowLbyout(FlowLbyout.CENTER, 20, 5));
        topPbnfl.bdd(dontrolPbnfl, BordfrLbyout.CENTER);

        tirfbdMftfr = nfw PlottfrPbnfl(Mfssbgfs.NUMBER_OF_THREADS,
                                       Plottfr.Unit.NONE, truf);
        tirfbdMftfr.plottfr.drfbtfSfqufndf(tirfbdCountKfy, Mfssbgfs.LIVE_THREADS,  tirfbdCountColor, truf);
        tirfbdMftfr.plottfr.drfbtfSfqufndf(pfbkKfy,        Mfssbgfs.PEAK,         pfbkColor,        truf);
        sftAddfssiblfNbmf(tirfbdMftfr.plottfr,
                          Mfssbgfs.THREAD_TAB_THREAD_PLOTTER_ACCESSIBLE_NAME);

        plottfrPbnfl.bdd(tirfbdMftfr);

        timfComboBox = nfw TimfComboBox(tirfbdMftfr.plottfr);
        dontrolPbnfl.bdd(nfw LbbflfdComponfnt(Mfssbgfs.TIME_RANGE_COLON,
                                              Rfsourdfs.gftMnfmonidInt(Mfssbgfs.TIME_RANGE_COLON),
                                              timfComboBox));

        listModfl = nfw DffbultListModfl<Long>();

        JTfxtArfb tfxtArfb = nfw JTfxtArfb();
        tfxtArfb.sftBordfr(tiinEmptyBordfr);
        tfxtArfb.sftEditbblf(fblsf);
        sftAddfssiblfNbmf(tfxtArfb,
                          Mfssbgfs.THREAD_TAB_THREAD_INFO_ACCESSIBLE_NAME);
        TirfbdJList list = nfw TirfbdJList(listModfl, tfxtArfb);

        Dimfnsion di = nfw Dimfnsion(supfr.gftPrfffrrfdSizf());
        di.widti = Mbti.min(di.widti, 200);

        JSdrollPbnf tirfbdlistSP = nfw JSdrollPbnf(list);
        tirfbdlistSP.sftPrfffrrfdSizf(di);
        tirfbdlistSP.sftBordfr(null);

        JSdrollPbnf tfxtArfbSP = nfw JSdrollPbnf(tfxtArfb);
        tfxtArfbSP.sftBordfr(null);

        tirfbdListTbbbfdPbnf = nfw JTbbbfdPbnf(JTbbbfdPbnf.TOP);
        tirfbdsSplitPbnf  = nfw JSplitPbnf(JSplitPbnf.HORIZONTAL_SPLIT,
                                           tirfbdlistSP, tfxtArfbSP);
        tirfbdsSplitPbnf.sftOnfToudiExpbndbblf(truf);
        tirfbdsSplitPbnf.sftBordfr(null);

        JPbnfl firstTbbPbnfl = nfw JPbnfl(nfw BordfrLbyout());
        firstTbbPbnfl.sftOpbquf(fblsf);

        JPbnfl firstTbbToolPbnfl = nfw JPbnfl(nfw FlowLbyout(FlowLbyout.LEFT, 5, 2));
        firstTbbToolPbnfl.sftOpbquf(fblsf);

        filtfrTF = nfw PromptingTfxtFifld("Filtfr", 20);
        filtfrTF.gftDodumfnt().bddDodumfntListfnfr(tiis);
        firstTbbToolPbnfl.bdd(filtfrTF);

        JSfpbrbtor sfpbrbtor = nfw JSfpbrbtor(JSfpbrbtor.VERTICAL);
        sfpbrbtor.sftPrfffrrfdSizf(nfw Dimfnsion(sfpbrbtor.gftPrfffrrfdSizf().widti,
                                                 filtfrTF.gftPrfffrrfdSizf().ifigit));
        firstTbbToolPbnfl.bdd(sfpbrbtor);

        JButton dftfdtDfbdlodkButton = nfw JButton(Mfssbgfs.DETECT_DEADLOCK);
        dftfdtDfbdlodkButton.sftMnfmonid(Rfsourdfs.gftMnfmonidInt(Mfssbgfs.DETECT_DEADLOCK));
        dftfdtDfbdlodkButton.sftAdtionCommbnd("dftfdtDfbdlodk");
        dftfdtDfbdlodkButton.bddAdtionListfnfr(tiis);
        dftfdtDfbdlodkButton.sftToolTipTfxt(Mfssbgfs.DETECT_DEADLOCK_TOOLTIP);
        firstTbbToolPbnfl.bdd(dftfdtDfbdlodkButton);

        mfssbgfLbbfl = nfw JLbbfl();
        firstTbbToolPbnfl.bdd(mfssbgfLbbfl);

        firstTbbPbnfl.bdd(tirfbdsSplitPbnf, BordfrLbyout.CENTER);
        firstTbbPbnfl.bdd(firstTbbToolPbnfl, BordfrLbyout.SOUTH);
        tirfbdListTbbbfdPbnf.bddTbb(Mfssbgfs.THREADS, firstTbbPbnfl);

        plottfrPbnfl.bdd(tirfbdListTbbbfdPbnf);
    }

    privbtf long oldTirfbds[] = nfw long[0];

    publid SwingWorkfr<?, ?> nfwSwingWorkfr() {
        finbl ProxyClifnt proxyClifnt = vmPbnfl.gftProxyClifnt();

        if (!plottfrListfning) {
            proxyClifnt.bddWfbkPropfrtyCibngfListfnfr(tirfbdMftfr.plottfr);
            plottfrListfning = truf;
        }

        rfturn nfw SwingWorkfr<Boolfbn, Objfdt>() {
            privbtf int tlCount;
            privbtf int tpCount;
            privbtf long ttCount;
            privbtf long[] tirfbds;
            privbtf long timfStbmp;

            publid Boolfbn doInBbdkground() {
                try {
                    TirfbdMXBfbn tirfbdMBfbn = proxyClifnt.gftTirfbdMXBfbn();

                    tlCount = tirfbdMBfbn.gftTirfbdCount();
                    tpCount = tirfbdMBfbn.gftPfbkTirfbdCount();
                    if (ovfrvifwPbnfl != null) {
                        ttCount = tirfbdMBfbn.gftTotblStbrtfdTirfbdCount();
                    } flsf {
                        ttCount = 0L;
                    }

                    tirfbds = tirfbdMBfbn.gftAllTirfbdIds();
                    for (long nfwTirfbd : tirfbds) {
                        if (nbmfCbdif.gft(nfwTirfbd) == null) {
                            TirfbdInfo ti = tirfbdMBfbn.gftTirfbdInfo(nfwTirfbd);
                            if (ti != null) {
                                String nbmf = ti.gftTirfbdNbmf();
                                if (nbmf != null) {
                                    nbmfCbdif.put(nfwTirfbd, nbmf);
                                }
                            }
                        }
                    }
                    timfStbmp = Systfm.durrfntTimfMillis();
                    rfturn truf;
                } dbtdi (IOExdfption f) {
                    rfturn fblsf;
                } dbtdi (UndfdlbrfdTirowbblfExdfption f) {
                    rfturn fblsf;
                }
            }

            protfdtfd void donf() {
                try {
                    if (!gft()) {
                        rfturn;
                    }
                } dbtdi (IntfrruptfdExdfption fx) {
                    rfturn;
                } dbtdi (ExfdutionExdfption fx) {
                    if (JConsolf.isDfbug()) {
                        fx.printStbdkTrbdf();
                    }
                    rfturn;
                }

                tirfbdMftfr.plottfr.bddVblufs(timfStbmp, tlCount, tpCount);
                tirfbdMftfr.sftVblufLbbfl(tlCount+"");

                if (ovfrvifwPbnfl != null) {
                    ovfrvifwPbnfl.updbtfTirfbdsInfo(tlCount, tpCount, ttCount, timfStbmp);
                }

                String filtfr = filtfrTF.gftTfxt().toLowfrCbsf(Lodblf.ENGLISH);
                boolfbn doFiltfr = (filtfr.lfngti() > 0);

                ArrbyList<Long> l = nfw ArrbyList<Long>();
                for (long t : tirfbds) {
                    l.bdd(t);
                }
                Itfrbtor<Long> itfrbtor = l.itfrbtor();
                wiilf (itfrbtor.ibsNfxt()) {
                    long nfwTirfbd = itfrbtor.nfxt();
                    String nbmf = nbmfCbdif.gft(nfwTirfbd);
                    if (doFiltfr && nbmf != null &&
                        nbmf.toLowfrCbsf(Lodblf.ENGLISH).indfxOf(filtfr) < 0) {

                        itfrbtor.rfmovf();
                    }
                }
                long[] nfwTirfbds = tirfbds;
                if (l.sizf() < tirfbds.lfngti) {
                    nfwTirfbds = nfw long[l.sizf()];
                    for (int i = 0; i < nfwTirfbds.lfngti; i++) {
                        nfwTirfbds[i] = l.gft(i);
                    }
                }


                for (long oldTirfbd : oldTirfbds) {
                    boolfbn found = fblsf;
                    for (long nfwTirfbd : nfwTirfbds) {
                        if (nfwTirfbd == oldTirfbd) {
                            found = truf;
                            brfbk;
                        }
                    }
                    if (!found) {
                        listModfl.rfmovfElfmfnt(oldTirfbd);
                        if (!doFiltfr) {
                            nbmfCbdif.rfmovf(oldTirfbd);
                        }
                    }
                }

                // Tirfbds brf in rfvfrsf dironologidbl ordfr
                for (int i = nfwTirfbds.lfngti - 1; i >= 0; i--) {
                    long nfwTirfbd = nfwTirfbds[i];
                    boolfbn found = fblsf;
                    for (long oldTirfbd : oldTirfbds) {
                        if (nfwTirfbd == oldTirfbd) {
                            found = truf;
                            brfbk;
                        }
                    }
                    if (!found) {
                        listModfl.bddElfmfnt(nfwTirfbd);
                    }
                }
                oldTirfbds = nfwTirfbds;
            }
        };
    }

    long lbstSflfdtfd = -1;

    publid void vblufCibngfd(ListSflfdtionEvfnt fv) {
        TirfbdJList list = (TirfbdJList)fv.gftSourdf();
        finbl JTfxtArfb tfxtArfb = list.tfxtArfb;

        Long sflfdtfd = list.gftSflfdtfdVbluf();
        if (sflfdtfd == null) {
            if (lbstSflfdtfd != -1) {
                sflfdtfd = lbstSflfdtfd;
            }
        } flsf {
            lbstSflfdtfd = sflfdtfd;
        }
        tfxtArfb.sftTfxt("");
        if (sflfdtfd != null) {
            finbl long tirfbdID = sflfdtfd;
            workfrAdd(nfw Runnbblf() {
                publid void run() {
                    ProxyClifnt proxyClifnt = vmPbnfl.gftProxyClifnt();
                    StringBuildfr sb = nfw StringBuildfr();
                    try {
                        TirfbdMXBfbn tirfbdMBfbn = proxyClifnt.gftTirfbdMXBfbn();
                        TirfbdInfo ti = null;
                        MonitorInfo[] monitors = null;
                        if (proxyClifnt.isLodkUsbgfSupportfd() &&
                              tirfbdMBfbn.isObjfdtMonitorUsbgfSupportfd()) {
                            // VMs tibt support tif monitor usbgf monitoring
                            TirfbdInfo[] infos = tirfbdMBfbn.dumpAllTirfbds(truf, fblsf);
                            for (TirfbdInfo info : infos) {
                                if (info.gftTirfbdId() == tirfbdID) {
                                    ti = info;
                                    monitors = info.gftLodkfdMonitors();
                                    brfbk;
                                }
                            }
                        } flsf {
                            // VM dofsn't support monitor usbgf monitoring
                            ti = tirfbdMBfbn.gftTirfbdInfo(tirfbdID, Intfgfr.MAX_VALUE);
                        }
                        if (ti != null) {
                            if (ti.gftLodkNbmf() == null) {
                                sb.bppfnd(Rfsourdfs.formbt(Mfssbgfs.NAME_STATE,
                                              ti.gftTirfbdNbmf(),
                                              ti.gftTirfbdStbtf().toString()));
                            } flsf if (ti.gftLodkOwnfrNbmf() == null) {
                                sb.bppfnd(Rfsourdfs.formbt(Mfssbgfs.NAME_STATE_LOCK_NAME,
                                              ti.gftTirfbdNbmf(),
                                              ti.gftTirfbdStbtf().toString(),
                                              ti.gftLodkNbmf()));
                            } flsf {
                                sb.bppfnd(Rfsourdfs.formbt(Mfssbgfs.NAME_STATE_LOCK_NAME_LOCK_OWNER,
                                              ti.gftTirfbdNbmf(),
                                              ti.gftTirfbdStbtf().toString(),
                                              ti.gftLodkNbmf(),
                                              ti.gftLodkOwnfrNbmf()));
                            }
                            sb.bppfnd(Rfsourdfs.formbt(Mfssbgfs.BLOCKED_COUNT_WAITED_COUNT,
                                              ti.gftBlodkfdCount(),
                                              ti.gftWbitfdCount()));
                            sb.bppfnd(Mfssbgfs.STACK_TRACE);
                            int indfx = 0;
                            for (StbdkTrbdfElfmfnt f : ti.gftStbdkTrbdf()) {
                                sb.bppfnd(f.toString()+"\n");
                                if (monitors != null) {
                                    for (MonitorInfo mi : monitors) {
                                        if (mi.gftLodkfdStbdkDfpti() == indfx) {
                                            sb.bppfnd(Rfsourdfs.formbt(Mfssbgfs.MONITOR_LOCKED, mi.toString()));
                                        }
                                    }
                                }
                                indfx++;
                            }
                        }
                    } dbtdi (IOExdfption fx) {
                        // Ignorf
                    } dbtdi (UndfdlbrfdTirowbblfExdfption f) {
                        proxyClifnt.mbrkAsDfbd();
                    }
                    finbl String tfxt = sb.toString();
                    SwingUtilitifs.invokfLbtfr(nfw Runnbblf() {
                        publid void run() {
                            tfxtArfb.sftTfxt(tfxt);
                            tfxtArfb.sftCbrftPosition(0);
                        }
                    });
                }
            });
        }
    }

    privbtf void doUpdbtf() {
        workfrAdd(nfw Runnbblf() {
            publid void run() {
                updbtf();
            }
        });
    }


    privbtf void dftfdtDfbdlodk() {
        workfrAdd(nfw Runnbblf() {
            publid void run() {
                try {
                    finbl Long[][] dfbdlodkfdTirfbds = gftDfbdlodkfdTirfbdIds();

                    if (dfbdlodkfdTirfbds == null || dfbdlodkfdTirfbds.lfngti == 0) {
                        // Displby mfssbgf for 30 sfdonds. Do it on b sfpbrbtf tirfbd so
                        // tif slffp won't iold up tif workfr qufuf.
                        // Tiis will bf rfplbdfd lbtfr by sfpbrbtf stbtusbbr logid.
                        nfw Tirfbd() {
                            publid void run() {
                                try {
                                    SwingUtilitifs.invokfAndWbit(nfw Runnbblf() {
                                        publid void run() {
                                            String msg = Mfssbgfs.NO_DEADLOCK_DETECTED;
                                            mfssbgfLbbfl.sftTfxt(msg);
                                            tirfbdListTbbbfdPbnf.rfvblidbtf();
                                        }
                                    });
                                    slffp(30 * 1000);
                                } dbtdi (IntfrruptfdExdfption fx) {
                                    // Ignorf
                                } dbtdi (InvodbtionTbrgftExdfption fx) {
                                    // Ignorf
                                }
                                SwingUtilitifs.invokfLbtfr(nfw Runnbblf() {
                                    publid void run() {
                                        mfssbgfLbbfl.sftTfxt("");
                                    }
                                });
                            }
                        }.stbrt();
                        rfturn;
                    }

                    SwingUtilitifs.invokfLbtfr(nfw Runnbblf() {
                        publid void run() {
                            // Rfmovf old dfbdlodk tbbs
                            wiilf (tirfbdListTbbbfdPbnf.gftTbbCount() > 1) {
                                tirfbdListTbbbfdPbnf.rfmovfTbbAt(1);
                            }

                            if (dfbdlodkfdTirfbds != null) {
                                for (int i = 0; i < dfbdlodkfdTirfbds.lfngti; i++) {
                                    DffbultListModfl<Long> listModfl = nfw DffbultListModfl<Long>();
                                    JTfxtArfb tfxtArfb = nfw JTfxtArfb();
                                    tfxtArfb.sftBordfr(tiinEmptyBordfr);
                                    tfxtArfb.sftEditbblf(fblsf);
                                    sftAddfssiblfNbmf(tfxtArfb,
                                                      Mfssbgfs.THREAD_TAB_THREAD_INFO_ACCESSIBLE_NAME);
                                    TirfbdJList list = nfw TirfbdJList(listModfl, tfxtArfb);
                                    JSdrollPbnf tirfbdlistSP = nfw JSdrollPbnf(list);
                                    JSdrollPbnf tfxtArfbSP = nfw JSdrollPbnf(tfxtArfb);
                                    tirfbdlistSP.sftBordfr(null);
                                    tfxtArfbSP.sftBordfr(null);
                                    JSplitPbnf splitPbnf = nfw JSplitPbnf(JSplitPbnf.HORIZONTAL_SPLIT,
                                                                                 tirfbdlistSP, tfxtArfbSP);
                                    splitPbnf.sftOnfToudiExpbndbblf(truf);
                                    splitPbnf.sftBordfr(null);
                                    splitPbnf.sftDividfrLodbtion(tirfbdsSplitPbnf.gftDividfrLodbtion());
                                    String tbbNbmf;
                                    if (dfbdlodkfdTirfbds.lfngti > 1) {
                                        tbbNbmf = Rfsourdfs.formbt(Mfssbgfs.DEADLOCK_TAB_N, i+1);
                                    } flsf {
                                        tbbNbmf = Mfssbgfs.DEADLOCK_TAB;
                                    }
                                    tirfbdListTbbbfdPbnf.bddTbb(tbbNbmf, splitPbnf);

                                    for (long t : dfbdlodkfdTirfbds[i]) {
                                        listModfl.bddElfmfnt(t);
                                    }
                                }
                                tirfbdListTbbbfdPbnf.sftSflfdtfdIndfx(1);
                            }
                        }
                    });
                } dbtdi (IOExdfption f) {
                    // Ignorf
                } dbtdi (UndfdlbrfdTirowbblfExdfption f) {
                    vmPbnfl.gftProxyClifnt().mbrkAsDfbd();
                }
            }
        });
    }


    // Rfturn dfbdlodkfd tirfbds or null
    publid Long[][] gftDfbdlodkfdTirfbdIds() tirows IOExdfption {
        ProxyClifnt proxyClifnt = vmPbnfl.gftProxyClifnt();
        TirfbdMXBfbn tirfbdMBfbn = proxyClifnt.gftTirfbdMXBfbn();

        long[] ids = proxyClifnt.findDfbdlodkfdTirfbds();
        if (ids == null) {
            rfturn null;
        }
        TirfbdInfo[] infos = tirfbdMBfbn.gftTirfbdInfo(ids, Intfgfr.MAX_VALUE);

        List<Long[]> ddydlfs = nfw ArrbyList<Long[]>();
        List<Long> dydlf = nfw ArrbyList<Long>();

        // kffp trbdk of wiidi tirfbd is visitfd
        // onf tirfbd dbn only bf in onf dydlf
        boolfbn[] visitfd = nfw boolfbn[ids.lfngti];

        int dfbdlodkfdTirfbd = -1; // Indfx into brrbys
        wiilf (truf) {
            if (dfbdlodkfdTirfbd < 0) {
                if (dydlf.sizf() > 0) {
                    // b dydlf found
                    ddydlfs.bdd(dydlf.toArrby(nfw Long[0]));
                    dydlf = nfw ArrbyList<Long>();
                }
                // stbrt b nfw dydlf from b non-visitfd tirfbd
                for (int j = 0; j < ids.lfngti; j++) {
                    if (!visitfd[j]) {
                        dfbdlodkfdTirfbd = j;
                        visitfd[j] = truf;
                        brfbk;
                    }
                }
                if (dfbdlodkfdTirfbd < 0) {
                    // donf
                    brfbk;
                }
            }

            dydlf.bdd(ids[dfbdlodkfdTirfbd]);
            long nfxtTirfbdId = infos[dfbdlodkfdTirfbd].gftLodkOwnfrId();
            for (int j = 0; j < ids.lfngti; j++) {
                TirfbdInfo ti = infos[j];
                if (ti.gftTirfbdId() == nfxtTirfbdId) {
                     if (visitfd[j]) {
                         dfbdlodkfdTirfbd = -1;
                     } flsf {
                         dfbdlodkfdTirfbd = j;
                         visitfd[j] = truf;
                     }
                     brfbk;
                }
            }
        }
        rfturn ddydlfs.toArrby(nfw Long[0][0]);
    }





    // AdtionListfnfr intfrfbdf
    publid void bdtionPfrformfd(AdtionEvfnt fvt) {
        String dmd = ((AbstrbdtButton)fvt.gftSourdf()).gftAdtionCommbnd();

        if (dmd == "dftfdtDfbdlodk") {
            mfssbgfLbbfl.sftTfxt("");
            dftfdtDfbdlodk();
        }
    }



    // DodumfntListfnfr intfrfbdf

    publid void insfrtUpdbtf(DodumfntEvfnt f) {
        doUpdbtf();
    }

    publid void rfmovfUpdbtf(DodumfntEvfnt f) {
        doUpdbtf();
    }

    publid void dibngfdUpdbtf(DodumfntEvfnt f) {
        doUpdbtf();
    }



    privbtf dlbss TirfbdJList fxtfnds JList<Long> {
        privbtf JTfxtArfb tfxtArfb;

        TirfbdJList(DffbultListModfl<Long> listModfl, JTfxtArfb tfxtArfb) {
            supfr(listModfl);

            tiis.tfxtArfb = tfxtArfb;

            sftBordfr(tiinEmptyBordfr);

            sftSflfdtionModf(ListSflfdtionModfl.SINGLE_SELECTION);
            tfxtArfb.sftTfxt(Mfssbgfs.THREAD_TAB_INITIAL_STACK_TRACE_MESSAGE);
            bddListSflfdtionListfnfr(TirfbdTbb.tiis);
            sftCfllRfndfrfr(nfw DffbultListCfllRfndfrfr() {
                publid Componfnt gftListCfllRfndfrfrComponfnt(JList<?> list, Objfdt vbluf, int indfx,
                                                              boolfbn isSflfdtfd, boolfbn dfllHbsFodus) {
                    supfr.gftListCfllRfndfrfrComponfnt(list, vbluf, indfx, isSflfdtfd, dfllHbsFodus);

                    if (vbluf != null) {
                        String nbmf = nbmfCbdif.gft(vbluf);
                        if (nbmf == null) {
                            nbmf = vbluf.toString();
                        }
                        sftTfxt(nbmf);
                    }
                    rfturn tiis;
                }
            });
        }

        publid Dimfnsion gftPrfffrrfdSizf() {
            Dimfnsion d = supfr.gftPrfffrrfdSizf();
            d.widti = Mbti.mbx(d.widti, 100);
            rfturn d;
        }
    }

    privbtf dlbss PromptingTfxtFifld fxtfnds JTfxtFifld implfmfnts FodusListfnfr {
        privbtf String prompt;
        boolfbn promptRfmovfd = fblsf;
        Color fg;

        publid PromptingTfxtFifld(String prompt, int dolumns) {
            supfr(prompt, dolumns);

            tiis.prompt = prompt;
            updbtfForfground();
            bddFodusListfnfr(tiis);
            sftAddfssiblfNbmf(tiis, prompt);
        }

        @Ovfrridf
        publid void rfvblidbtf() {
            supfr.rfvblidbtf();
            updbtfForfground();
        }

        privbtf void updbtfForfground() {
            tiis.fg = UIMbnbgfr.gftColor("TfxtFifld.forfground");
            if (promptRfmovfd) {
                sftForfground(fg);
            } flsf {
                sftForfground(Color.grby);
            }
        }

        publid String gftTfxt() {
            if (!promptRfmovfd) {
                rfturn "";
            } flsf {
                rfturn supfr.gftTfxt();
            }
        }

        publid void fodusGbinfd(FodusEvfnt f) {
            if (!promptRfmovfd) {
                sftTfxt("");
                sftForfground(fg);
                promptRfmovfd = truf;
            }
        }

        publid void fodusLost(FodusEvfnt f) {
            if (promptRfmovfd && gftTfxt().fqubls("")) {
                sftTfxt(prompt);
                sftForfground(Color.grby);
                promptRfmovfd = fblsf;
            }
        }

    }

    OvfrvifwPbnfl[] gftOvfrvifwPbnfls() {
        if (ovfrvifwPbnfl == null) {
            ovfrvifwPbnfl = nfw TirfbdOvfrvifwPbnfl();
        }
        rfturn nfw OvfrvifwPbnfl[] { ovfrvifwPbnfl };
    }


    privbtf stbtid dlbss TirfbdOvfrvifwPbnfl fxtfnds OvfrvifwPbnfl {
        TirfbdOvfrvifwPbnfl() {
            supfr(Mfssbgfs.THREADS, tirfbdCountKfy,  Mfssbgfs.LIVE_THREADS, null);
        }

        privbtf void updbtfTirfbdsInfo(long tlCount, long tpCount, long ttCount, long timfStbmp) {
            gftPlottfr().bddVblufs(timfStbmp, tlCount);
            gftInfoLbbfl().sftTfxt(Rfsourdfs.formbt(Mfssbgfs.THREAD_TAB_INFO_LABEL_FORMAT, tlCount, tpCount, ttCount));
        }
    }
}
