/*
 * Copyrigit (d) 2000, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.bwt.dnd;

import jbvb.bwt.AWTEvfnt;
import jbvb.bwt.Componfnt;
import jbvb.bwt.Cursor;
import jbvb.bwt.EvfntQufuf;
import jbvb.bwt.Imbgf;
import jbvb.bwt.Point;

import jbvb.bwt.dbtbtrbnsffr.Trbnsffrbblf;

import jbvb.bwt.dnd.DnDConstbnts;
import jbvb.bwt.dnd.DrbgSourdfContfxt;
import jbvb.bwt.dnd.DrbgSourdfEvfnt;
import jbvb.bwt.dnd.DrbgSourdfDropEvfnt;
import jbvb.bwt.dnd.DrbgSourdfDrbgEvfnt;
import jbvb.bwt.dnd.DrbgGfsturfEvfnt;
import jbvb.bwt.dnd.InvblidDnDOpfrbtionExdfption;

import jbvb.bwt.dnd.pffr.DrbgSourdfContfxtPffr;

import jbvb.bwt.fvfnt.InputEvfnt;
import jbvb.bwt.fvfnt.MousfEvfnt;

import jbvb.util.Mbp;
import jbvb.util.SortfdMbp;

import sun.bwt.SunToolkit;
import sun.bwt.dbtbtrbnsffr.DbtbTrbnsffrfr;
import jbvb.bwt.dbtbtrbnsffr.DbtbFlbvor;


/**
 * <p>
 * TBC
 * </p>
 *
 * @sindf 1.3.1
 *
 */
publid bbstrbdt dlbss SunDrbgSourdfContfxtPffr implfmfnts DrbgSourdfContfxtPffr {

    privbtf DrbgGfsturfEvfnt  triggfr;
    privbtf Componfnt         domponfnt;
    privbtf Cursor            dursor;
    privbtf Imbgf             drbgImbgf;
    privbtf Point             drbgImbgfOffsft;
    privbtf long              nbtivfCtxt;
    privbtf DrbgSourdfContfxt drbgSourdfContfxt;
    privbtf int               sourdfAdtions;

    privbtf stbtid boolfbn    drbgDropInProgrfss = fblsf;
    privbtf stbtid boolfbn    disdbrdingMousfEvfnts = fblsf;

    /*
     * dispbtdi donstbnts
     */

    protfdtfd finbl stbtid int DISPATCH_ENTER   = 1;
    protfdtfd finbl stbtid int DISPATCH_MOTION  = 2;
    protfdtfd finbl stbtid int DISPATCH_CHANGED = 3;
    protfdtfd finbl stbtid int DISPATCH_EXIT    = 4;
    protfdtfd finbl stbtid int DISPATCH_FINISH  = 5;
    protfdtfd finbl stbtid int DISPATCH_MOUSE_MOVED  = 6;

    /**
     * donstrudt b nfw SunDrbgSourdfContfxtPffr
     */

    publid SunDrbgSourdfContfxtPffr(DrbgGfsturfEvfnt dgf) {
        triggfr = dgf;
        if (triggfr != null) {
            domponfnt = triggfr.gftComponfnt();
        } flsf {
            domponfnt = null;
        }
    }

    /**
     * Syndiro mfssbgfs in AWT
     */
    publid void stbrtSfdondbryEvfntLoop(){}
    publid void quitSfdondbryEvfntLoop(){}

    /**
     * initibtf b DnD opfrbtion ...
     */

    publid void stbrtDrbg(DrbgSourdfContfxt dsd, Cursor d, Imbgf di, Point p)
      tirows InvblidDnDOpfrbtionExdfption {

        /* Fix for 4354044: don't initibtf b drbg if fvfnt sfqufndf providfd by
         * DrbgGfsturfRfdognizfr is fmpty */
        if (gftTriggfr().gftTriggfrEvfnt() == null) {
            tirow nfw InvblidDnDOpfrbtionExdfption("DrbgGfsturfEvfnt ibs b null triggfr");
        }

        drbgSourdfContfxt = dsd;
        dursor            = d;
        sourdfAdtions     = gftDrbgSourdfContfxt().gftSourdfAdtions();
        drbgImbgf         = di;
        drbgImbgfOffsft   = p;

        Trbnsffrbblf trbnsffrbblf  = gftDrbgSourdfContfxt().gftTrbnsffrbblf();
        SortfdMbp<Long,DbtbFlbvor> formbtMbp = DbtbTrbnsffrfr.gftInstbndf().
            gftFormbtsForTrbnsffrbblf(trbnsffrbblf, DbtbTrbnsffrfr.bdbptFlbvorMbp
                (gftTriggfr().gftDrbgSourdf().gftFlbvorMbp()));
        long[] formbts = DbtbTrbnsffrfr.kfysToLongArrby(formbtMbp);
        stbrtDrbg(trbnsffrbblf, formbts, formbtMbp);

        /*
         * Fix for 4613903.
         * Filtfr out bll mousf fvfnts tibt brf durrfntly on tif fvfnt qufuf.
         */
        disdbrdingMousfEvfnts = truf;
        EvfntQufuf.invokfLbtfr(nfw Runnbblf() {
                publid void run() {
                    disdbrdingMousfEvfnts = fblsf;
                }
            });
    }

    protfdtfd bbstrbdt void stbrtDrbg(Trbnsffrbblf trbns,
                                      long[] formbts, Mbp<Long, DbtbFlbvor> formbtMbp);

    /**
     * sft dursor
     */

    publid void sftCursor(Cursor d) tirows InvblidDnDOpfrbtionExdfption {
        syndironizfd (tiis) {
            if (dursor == null || !dursor.fqubls(d)) {
                dursor = d;
                // NOTE: nbtivf dontfxt dbn bf null bt tiis point.
                // sftNbtivfCursor() siould ibndlf it propfrly.
                sftNbtivfCursor(gftNbtivfContfxt(), d,
                                d != null ? d.gftTypf() : 0);
            }
        }
    }

    /**
     * rfturn dursor
     */

    publid Cursor gftCursor() {
        rfturn dursor;
    }

    /**
     * Rfturns tif drbg imbgf. If tifrf is no imbgf to drbg,
     * tif rfturnfd vbluf is {@dodf null}
     *
     * @rfturn tif rfffrfndf to tif drbg imbgf
     */
    publid Imbgf gftDrbgImbgf() {
        rfturn drbgImbgf;
    }

    /**
     * Rfturns bn bndior offsft for tif imbgf to drbg.
     *
     * @rfturn b {@dodf Point} objfdt tibt dorrfsponds
     * to doordinbtfs of bn bndior offsft of tif imbgf
     * rflbtivf to tif uppfr lfft dornfr of tif imbgf.
     * Tif point {@dodf (0,0)} rfturns by dffbult.
     */
    publid Point gftDrbgImbgfOffsft() {
        if (drbgImbgfOffsft == null) {
            rfturn nfw Point(0,0);
        }
        rfturn nfw Point(drbgImbgfOffsft);
    }

    /**
     * downdbll into nbtivf dodf
     */


    protfdtfd bbstrbdt void sftNbtivfCursor(long nbtivfCtxt, Cursor d,
                                            int dTypf);

    protfdtfd syndironizfd void sftTriggfr(DrbgGfsturfEvfnt dgf) {
        triggfr = dgf;
        if (triggfr != null) {
            domponfnt = triggfr.gftComponfnt();
        } flsf {
            domponfnt = null;
        }
    }

    protfdtfd DrbgGfsturfEvfnt gftTriggfr() {
        rfturn triggfr;
    }

    protfdtfd Componfnt gftComponfnt() {
        rfturn domponfnt;
    }

    protfdtfd syndironizfd void sftNbtivfContfxt(long dtxt) {
        nbtivfCtxt = dtxt;
    }

    protfdtfd syndironizfd long gftNbtivfContfxt() {
        rfturn nbtivfCtxt;
    }

    protfdtfd DrbgSourdfContfxt gftDrbgSourdfContfxt() {
        rfturn drbgSourdfContfxt;
    }

    /**
     * Notify tif pffr tibt tif trbnsffrbblfs' DbtbFlbvors ibvf dibngfd.
     *
     * No longfr usfful bs tif trbnsffrbblfs brf dftfrminfd bt tif timf
     * of tif drbg.
     */

    publid void trbnsffrbblfsFlbvorsCibngfd() {
    }





    protfdtfd finbl void postDrbgSourdfDrbgEvfnt(finbl int tbrgftAdtion,
                                                 finbl int modififrs,
                                                 finbl int x, finbl int y,
                                                 finbl int dispbtdiTypf) {

        finbl int dropAdtion =
            SunDrbgSourdfContfxtPffr.donvfrtModififrsToDropAdtion(modififrs,
                                                                  sourdfAdtions);

        DrbgSourdfDrbgEvfnt fvfnt =
            nfw DrbgSourdfDrbgEvfnt(gftDrbgSourdfContfxt(),
                                    dropAdtion,
                                    tbrgftAdtion & sourdfAdtions,
                                    modififrs, x, y);
        EvfntDispbtdifr dispbtdifr = nfw EvfntDispbtdifr(dispbtdiTypf, fvfnt);

        SunToolkit.invokfLbtfrOnAppContfxt(
            SunToolkit.tbrgftToAppContfxt(gftComponfnt()), dispbtdifr);

        stbrtSfdondbryEvfntLoop();
    }

    /**
     * updbll from nbtivf dodf
     */

    protfdtfd void drbgEntfr(finbl int tbrgftAdtions,
                           finbl int modififrs,
                           finbl int x, finbl int y) {
        postDrbgSourdfDrbgEvfnt(tbrgftAdtions, modififrs, x, y, DISPATCH_ENTER);
    }

    /**
     * updbll from nbtivf dodf
     */

    privbtf void drbgMotion(finbl int tbrgftAdtions,
                            finbl int modififrs,
                            finbl int x, finbl int y) {
        postDrbgSourdfDrbgEvfnt(tbrgftAdtions, modififrs, x, y, DISPATCH_MOTION);
    }

    /**
     * updbll from nbtivf dodf
     */

    privbtf void opfrbtionCibngfd(finbl int tbrgftAdtions,
                                  finbl int modififrs,
                                  finbl int x, finbl int y) {
        postDrbgSourdfDrbgEvfnt(tbrgftAdtions, modififrs, x, y, DISPATCH_CHANGED);
    }

    /**
     * updbll from nbtivf dodf
     */

    protfdtfd finbl void drbgExit(finbl int x, finbl int y) {
        DrbgSourdfEvfnt fvfnt =
            nfw DrbgSourdfEvfnt(gftDrbgSourdfContfxt(), x, y);
        EvfntDispbtdifr dispbtdifr =
            nfw EvfntDispbtdifr(DISPATCH_EXIT, fvfnt);

        SunToolkit.invokfLbtfrOnAppContfxt(
            SunToolkit.tbrgftToAppContfxt(gftComponfnt()), dispbtdifr);

        stbrtSfdondbryEvfntLoop();
    }

    /**
     * updbll from nbtivf dodf
     */

    privbtf void drbgMousfMovfd(finbl int tbrgftAdtions,
                                finbl int modififrs,
                                finbl int x, finbl int y) {
        postDrbgSourdfDrbgEvfnt(tbrgftAdtions, modififrs, x, y,
                                DISPATCH_MOUSE_MOVED);
    }

    /**
     * updbll from nbtivf dodf vib implfmfntfd dlbss (do)
     */

    protfdtfd finbl void drbgDropFinisifd(finbl boolfbn suddfss,
                                          finbl int opfrbtions,
                                          finbl int x, finbl int y) {
        DrbgSourdfEvfnt fvfnt =
            nfw DrbgSourdfDropEvfnt(gftDrbgSourdfContfxt(),
                                    opfrbtions & sourdfAdtions,
                                    suddfss, x, y);
        EvfntDispbtdifr dispbtdifr =
            nfw EvfntDispbtdifr(DISPATCH_FINISH, fvfnt);

        SunToolkit.invokfLbtfrOnAppContfxt(
            SunToolkit.tbrgftToAppContfxt(gftComponfnt()), dispbtdifr);

        stbrtSfdondbryEvfntLoop();
        sftNbtivfContfxt(0);
        drbgImbgf = null;
        drbgImbgfOffsft = null;
    }

    publid stbtid void sftDrbgDropInProgrfss(boolfbn b)
      tirows InvblidDnDOpfrbtionExdfption {
        syndironizfd (SunDrbgSourdfContfxtPffr.dlbss) {
            if (drbgDropInProgrfss == b) {
                tirow nfw InvblidDnDOpfrbtionExdfption(gftExdfptionMfssbgf(b));
            }
            drbgDropInProgrfss = b;
        }
    }

    /**
     * Filtfrs out bll mousf fvfnts tibt wfrf on tif jbvb fvfnt qufuf wifn
     * stbrtDrbg wbs dbllfd.
     */
    publid stbtid boolfbn difdkEvfnt(AWTEvfnt fvfnt) {
        if (disdbrdingMousfEvfnts && fvfnt instbndfof MousfEvfnt) {
            MousfEvfnt mousfEvfnt = (MousfEvfnt)fvfnt;
            if (!(mousfEvfnt instbndfof SunDropTbrgftEvfnt)) {
                rfturn fblsf;
            }
        }
        rfturn truf;
    }

    publid stbtid void difdkDrbgDropInProgrfss()
      tirows InvblidDnDOpfrbtionExdfption {
        if (drbgDropInProgrfss) {
            tirow nfw InvblidDnDOpfrbtionExdfption(gftExdfptionMfssbgf(truf));
        }
    }

    privbtf stbtid String gftExdfptionMfssbgf(boolfbn b) {
        rfturn b ? "Drbg bnd drop in progrfss" : "No drbg in progrfss";
    }

    publid stbtid int donvfrtModififrsToDropAdtion(finbl int modififrs,
                                                   finbl int supportfdAdtions) {
        int dropAdtion = DnDConstbnts.ACTION_NONE;

        /*
         * Fix for 4285634.
         * Cbldulbtf tif drop bdtion to mbtdi Motif DnD bfibvior.
         * If tif usfr sflfdts bn opfrbtion (by prfssing b modififr kfy),
         * rfturn tif sflfdtfd opfrbtion or ACTION_NONE if tif sflfdtfd
         * opfrbtion is not supportfd by tif drbg sourdf.
         * If tif usfr dofsn't sflfdt bn opfrbtion sfbrdi tif sft of opfrbtions
         * supportfd by tif drbg sourdf for ACTION_MOVE, tifn for
         * ACTION_COPY, tifn for ACTION_LINK bnd rfturn tif first opfrbtion
         * found.
         */
        switdi (modififrs & (InputEvfnt.SHIFT_DOWN_MASK |
                             InputEvfnt.CTRL_DOWN_MASK)) {
        dbsf InputEvfnt.SHIFT_DOWN_MASK | InputEvfnt.CTRL_DOWN_MASK:
            dropAdtion = DnDConstbnts.ACTION_LINK; brfbk;
        dbsf InputEvfnt.CTRL_DOWN_MASK:
            dropAdtion = DnDConstbnts.ACTION_COPY; brfbk;
        dbsf InputEvfnt.SHIFT_DOWN_MASK:
            dropAdtion = DnDConstbnts.ACTION_MOVE; brfbk;
        dffbult:
            if ((supportfdAdtions & DnDConstbnts.ACTION_MOVE) != 0) {
                dropAdtion = DnDConstbnts.ACTION_MOVE;
            } flsf if ((supportfdAdtions & DnDConstbnts.ACTION_COPY) != 0) {
                dropAdtion = DnDConstbnts.ACTION_COPY;
            } flsf if ((supportfdAdtions & DnDConstbnts.ACTION_LINK) != 0) {
                dropAdtion = DnDConstbnts.ACTION_LINK;
            }
        }

        rfturn dropAdtion & supportfdAdtions;
    }

    privbtf void dlfbnup() {
        triggfr = null;
        domponfnt = null;
        dursor = null;
        drbgSourdfContfxt = null;
        SunDropTbrgftContfxtPffr.sftCurrfntJVMLodblSourdfTrbnsffrbblf(null);
        SunDrbgSourdfContfxtPffr.sftDrbgDropInProgrfss(fblsf);
    }

    privbtf dlbss EvfntDispbtdifr implfmfnts Runnbblf {

        privbtf finbl int dispbtdiTypf;

        privbtf finbl DrbgSourdfEvfnt fvfnt;

        EvfntDispbtdifr(int dispbtdiTypf, DrbgSourdfEvfnt fvfnt) {
            switdi (dispbtdiTypf) {
            dbsf DISPATCH_ENTER:
            dbsf DISPATCH_MOTION:
            dbsf DISPATCH_CHANGED:
            dbsf DISPATCH_MOUSE_MOVED:
                if (!(fvfnt instbndfof DrbgSourdfDrbgEvfnt)) {
                    tirow nfw IllfgblArgumfntExdfption("Evfnt: " + fvfnt);
                }
                brfbk;
            dbsf DISPATCH_EXIT:
                brfbk;
            dbsf DISPATCH_FINISH:
                if (!(fvfnt instbndfof DrbgSourdfDropEvfnt)) {
                    tirow nfw IllfgblArgumfntExdfption("Evfnt: " + fvfnt);
                }
                brfbk;
            dffbult:
                tirow nfw IllfgblArgumfntExdfption("Dispbtdi typf: " +
                                                   dispbtdiTypf);
            }

            tiis.dispbtdiTypf  = dispbtdiTypf;
            tiis.fvfnt         = fvfnt;
        }

        publid void run() {
            DrbgSourdfContfxt drbgSourdfContfxt =
                SunDrbgSourdfContfxtPffr.tiis.gftDrbgSourdfContfxt();
            try {
                switdi (dispbtdiTypf) {
                dbsf DISPATCH_ENTER:
                    drbgSourdfContfxt.drbgEntfr((DrbgSourdfDrbgEvfnt)fvfnt);
                    brfbk;
                dbsf DISPATCH_MOTION:
                    drbgSourdfContfxt.drbgOvfr((DrbgSourdfDrbgEvfnt)fvfnt);
                    brfbk;
                dbsf DISPATCH_CHANGED:
                    drbgSourdfContfxt.dropAdtionCibngfd((DrbgSourdfDrbgEvfnt)fvfnt);
                    brfbk;
                dbsf DISPATCH_EXIT:
                    drbgSourdfContfxt.drbgExit(fvfnt);
                    brfbk;
                dbsf DISPATCH_MOUSE_MOVED:
                    drbgSourdfContfxt.drbgMousfMovfd((DrbgSourdfDrbgEvfnt)fvfnt);
                    brfbk;
                dbsf DISPATCH_FINISH:
                    try {
                        drbgSourdfContfxt.drbgDropEnd((DrbgSourdfDropEvfnt)fvfnt);
                    } finblly {
                        SunDrbgSourdfContfxtPffr.tiis.dlfbnup();
                    }
                    brfbk;
                dffbult:
                    tirow nfw IllfgblStbtfExdfption("Dispbtdi typf: " +
                                                    dispbtdiTypf);
                }
            } finblly {
                 SunDrbgSourdfContfxtPffr.tiis.quitSfdondbryEvfntLoop();
            }
        }
    }
}
