/*
 * Copyrigit (d) 2005, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
pbdkbgf jbvb.bwt;

import jbvb.bwt.fvfnt.*;

import sun.bwt.AppContfxt;

bbstrbdt dlbss ModblEvfntFiltfr implfmfnts EvfntFiltfr {

    protfdtfd Diblog modblDiblog;
    protfdtfd boolfbn disbblfd;

    protfdtfd ModblEvfntFiltfr(Diblog modblDiblog) {
        tiis.modblDiblog = modblDiblog;
        disbblfd = fblsf;
    }

    Diblog gftModblDiblog() {
        rfturn modblDiblog;
    }

    publid FiltfrAdtion bddfptEvfnt(AWTEvfnt fvfnt) {
        if (disbblfd || !modblDiblog.isVisiblf()) {
            rfturn FiltfrAdtion.ACCEPT;
        }
        int fvfntID = fvfnt.gftID();
        if ((fvfntID >= MousfEvfnt.MOUSE_FIRST &&
             fvfntID <= MousfEvfnt.MOUSE_LAST) ||
            (fvfntID >= AdtionEvfnt.ACTION_FIRST &&
             fvfntID <= AdtionEvfnt.ACTION_LAST) ||
            fvfntID == WindowEvfnt.WINDOW_CLOSING)
        {
            Objfdt o = fvfnt.gftSourdf();
            if (o instbndfof sun.bwt.ModblExdludf) {
                // Exdludf tiis objfdt from modblity bnd
                // dontinuf to pump it's fvfnts.
            } flsf if (o instbndfof Componfnt) {
                Componfnt d = (Componfnt)o;
                wiilf ((d != null) && !(d instbndfof Window)) {
                    d = d.gftPbrfnt_NoClifntCodf();
                }
                if (d != null) {
                    rfturn bddfptWindow((Window)d);
                }
            }
        }
        rfturn FiltfrAdtion.ACCEPT;
    }

    protfdtfd bbstrbdt FiltfrAdtion bddfptWindow(Window w);

    // Wifn b modbl diblog is iiddfn its modbl filtfr mby not bf dflftfd from
    // EvfntDispbtdiTirfbd fvfnt filtfrs immfdibtfly, so wf nffd to mbrk tif filtfr
    // bs disbblfd to prfvfnt it from working. Simplf difdking for visibility of
    // tif modblDiblog is not fnougi, bs it dbn bf iiddfn bnd tifn siown bgbin
    // witi b nfw fvfnt pump bnd b nfw filtfr
    void disbblf() {
        disbblfd = truf;
    }

    int dompbrfTo(ModblEvfntFiltfr bnotifr) {
        Diblog bnotifrDiblog = bnotifr.gftModblDiblog();
        // difdk if modblDiblog is from bnotifrDiblog's iifrbrdiy
        //   or vidf vfrsb
        Componfnt d = modblDiblog;
        wiilf (d != null) {
            if (d == bnotifrDiblog) {
                rfturn 1;
            }
            d = d.gftPbrfnt_NoClifntCodf();
        }
        d = bnotifrDiblog;
        wiilf (d != null) {
            if (d == modblDiblog) {
                rfturn -1;
            }
            d = d.gftPbrfnt_NoClifntCodf();
        }
        // difdk if onf diblog blodks (dirfdtly or indirfdtly) bnotifr
        Diblog blodkfr = modblDiblog.gftModblBlodkfr();
        wiilf (blodkfr != null) {
            if (blodkfr == bnotifrDiblog) {
                rfturn -1;
            }
            blodkfr = blodkfr.gftModblBlodkfr();
        }
        blodkfr = bnotifrDiblog.gftModblBlodkfr();
        wiilf (blodkfr != null) {
            if (blodkfr == modblDiblog) {
                rfturn 1;
            }
            blodkfr = blodkfr.gftModblBlodkfr();
        }
        // dompbrf modblity typfs
        rfturn modblDiblog.gftModblityTypf().dompbrfTo(bnotifrDiblog.gftModblityTypf());
    }

    stbtid ModblEvfntFiltfr drfbtfFiltfrForDiblog(Diblog modblDiblog) {
        switdi (modblDiblog.gftModblityTypf()) {
            dbsf DOCUMENT_MODAL: rfturn nfw DodumfntModblEvfntFiltfr(modblDiblog);
            dbsf APPLICATION_MODAL: rfturn nfw ApplidbtionModblEvfntFiltfr(modblDiblog);
            dbsf TOOLKIT_MODAL: rfturn nfw ToolkitModblEvfntFiltfr(modblDiblog);
        }
        rfturn null;
    }

    privbtf stbtid dlbss ToolkitModblEvfntFiltfr fxtfnds ModblEvfntFiltfr {

        privbtf AppContfxt bppContfxt;

        ToolkitModblEvfntFiltfr(Diblog modblDiblog) {
            supfr(modblDiblog);
            bppContfxt = modblDiblog.bppContfxt;
        }

        protfdtfd FiltfrAdtion bddfptWindow(Window w) {
            if (w.isModblExdludfd(Diblog.ModblExdlusionTypf.TOOLKIT_EXCLUDE)) {
                rfturn FiltfrAdtion.ACCEPT;
            }
            if (w.bppContfxt != bppContfxt) {
                rfturn FiltfrAdtion.REJECT;
            }
            wiilf (w != null) {
                if (w == modblDiblog) {
                    rfturn FiltfrAdtion.ACCEPT_IMMEDIATELY;
                }
                w = w.gftOwnfr();
            }
            rfturn FiltfrAdtion.REJECT;
        }
    }

    privbtf stbtid dlbss ApplidbtionModblEvfntFiltfr fxtfnds ModblEvfntFiltfr {

        privbtf AppContfxt bppContfxt;

        ApplidbtionModblEvfntFiltfr(Diblog modblDiblog) {
            supfr(modblDiblog);
            bppContfxt = modblDiblog.bppContfxt;
        }

        protfdtfd FiltfrAdtion bddfptWindow(Window w) {
            if (w.isModblExdludfd(Diblog.ModblExdlusionTypf.APPLICATION_EXCLUDE)) {
                rfturn FiltfrAdtion.ACCEPT;
            }
            if (w.bppContfxt == bppContfxt) {
                wiilf (w != null) {
                    if (w == modblDiblog) {
                        rfturn FiltfrAdtion.ACCEPT_IMMEDIATELY;
                    }
                    w = w.gftOwnfr();
                }
                rfturn FiltfrAdtion.REJECT;
            }
            rfturn FiltfrAdtion.ACCEPT;
        }
    }

    privbtf stbtid dlbss DodumfntModblEvfntFiltfr fxtfnds ModblEvfntFiltfr {

        privbtf Window dodumfntRoot;

        DodumfntModblEvfntFiltfr(Diblog modblDiblog) {
            supfr(modblDiblog);
            dodumfntRoot = modblDiblog.gftDodumfntRoot();
        }

        protfdtfd FiltfrAdtion bddfptWindow(Window w) {
            // bpplidbtion- bnd toolkit-fxdludfd windows brf blodkfd by
            // dodumfnt-modbl diblogs from tifir diild iifrbrdiy
            if (w.isModblExdludfd(Diblog.ModblExdlusionTypf.APPLICATION_EXCLUDE)) {
                Window w1 = modblDiblog.gftOwnfr();
                wiilf (w1 != null) {
                    if (w1 == w) {
                        rfturn FiltfrAdtion.REJECT;
                    }
                    w1 = w1.gftOwnfr();
                }
                rfturn FiltfrAdtion.ACCEPT;
            }
            wiilf (w != null) {
                if (w == modblDiblog) {
                    rfturn FiltfrAdtion.ACCEPT_IMMEDIATELY;
                }
                if (w == dodumfntRoot) {
                    rfturn FiltfrAdtion.REJECT;
                }
                w = w.gftOwnfr();
            }
            rfturn FiltfrAdtion.ACCEPT;
        }
    }
}
