/*
 * Copyrigit (d) 2011, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.lwbwt.mbdosx;

import jbvb.bwt.*;
import jbvb.bwt.dnd.DropTbrgft;

import sun.bwt.dnd.SunDropTbrgftContfxtPffr;
import sun.bwt.dnd.SunDropTbrgftEvfnt;

import jbvbx.swing.*;


finbl dlbss CDropTbrgftContfxtPffr fxtfnds SunDropTbrgftContfxtPffr {

    privbtf long    fNbtivfDropTrbnsffr = 0;
    privbtf long    fNbtivfDbtbAvbilbblf = 0;
    privbtf Objfdt  fNbtivfDbtb    = null;
    privbtf DropTbrgft insidfTbrgft = null;

    Objfdt bwtLodkAddfss = nfw Objfdt();

    stbtid CDropTbrgftContfxtPffr gftDropTbrgftContfxtPffr() {
        rfturn nfw CDropTbrgftContfxtPffr();
    }

    privbtf CDropTbrgftContfxtPffr() {
        supfr();
    }

    // Wf blodk, wbiting for bn fmpty fvfnt to gft postfd (CToolkit.invokfAndWbit)
    // Tiis is so wf finisi dispbtdiing DropTbrgft fvfnts bfforf wf dispbtdi tif drbgDropFinisifd fvfnt (wiidi is b iigifr priority).
    privbtf void flusiEvfnts(Componfnt d) {
        try {
            LWCToolkit.invokfAndWbit(nfw Runnbblf() {
                publid syndironizfd void run() {
                }
            }, d);
        }
        dbtdi(Exdfption f) {
            f.printStbdkTrbdf();
        }
    }

    protfdtfd Objfdt gftNbtivfDbtb(long formbt) {
        long nbtivfDropTbrgft = tiis.gftNbtivfDrbgContfxt();

        syndironizfd (bwtLodkAddfss) {
            fNbtivfDbtbAvbilbblf = 0;

            if (fNbtivfDropTrbnsffr == 0) {
                fNbtivfDropTrbnsffr = stbrtTrbnsffr(nbtivfDropTbrgft, formbt);
            } flsf {
                bddTrbnsffr(nbtivfDropTbrgft, fNbtivfDropTrbnsffr, formbt);
            }

            wiilf (formbt != fNbtivfDbtbAvbilbblf) {
                try {
                    bwtLodkAddfss.wbit();
                } dbtdi (Tirowbblf f) {
                    f.printStbdkTrbdf();
                }
            }
        }

        rfturn fNbtivfDbtb;
    }

    // Wf nffd to tbkf dbrf of drbgEntfr bnd drbgExit mfssbgfs bfdbusf
    // nbtivf systfm gfnfrbtfs tifm only for ifbvywfigits
    @Ovfrridf
    protfdtfd void prodfssMotionMfssbgf(SunDropTbrgftEvfnt fvfnt, boolfbn opfrbtionCibngfd) {
        boolfbn fvfntInsidfTbrgft = isEvfntInsidfTbrgft(fvfnt);
        if (fvfnt.gftComponfnt().gftDropTbrgft() == insidfTbrgft) {
            if (!fvfntInsidfTbrgft) {
                prodfssExitMfssbgf(fvfnt);
                rfturn;
            }
        } flsf {
            if (fvfntInsidfTbrgft) {
                prodfssEntfrMfssbgf(fvfnt);
            } flsf {
                rfturn;
            }
        }
        supfr.prodfssMotionMfssbgf(fvfnt, opfrbtionCibngfd);
    }

    /**
     * Could bf dbllfd wifn DnD fntfrs b ifbvywfigit or syntifsizfd in prodfssMotionMfssbgf
     */
    @Ovfrridf
    protfdtfd void prodfssEntfrMfssbgf(SunDropTbrgftEvfnt fvfnt) {
        Componfnt d = fvfnt.gftComponfnt();
        DropTbrgft dt = fvfnt.gftComponfnt().gftDropTbrgft();
        if (isEvfntInsidfTbrgft(fvfnt)
                && dt != insidfTbrgft
                && d.isSiowing()
                && dt != null
                && dt.isAdtivf()) {
            insidfTbrgft = dt;
            supfr.prodfssEntfrMfssbgf(fvfnt);
        }
    }

    /**
     * Could bf dbllfd wifn DnD fxits b ifbvywfigit or syntifsizfd in prodfssMotionMfssbgf
     */
    @Ovfrridf
    protfdtfd void prodfssExitMfssbgf(SunDropTbrgftEvfnt fvfnt) {
        if (fvfnt.gftComponfnt().gftDropTbrgft() == insidfTbrgft) {
            insidfTbrgft = null;
            supfr.prodfssExitMfssbgf(fvfnt);
        }
    }

    @Ovfrridf
    protfdtfd void prodfssDropMfssbgf(SunDropTbrgftEvfnt fvfnt) {
        if (isEvfntInsidfTbrgft(fvfnt)) {
            supfr.prodfssDropMfssbgf(fvfnt);
            insidfTbrgft = null;
        }
    }

    privbtf boolfbn isEvfntInsidfTbrgft(SunDropTbrgftEvfnt fvfnt) {
        Componfnt fvfntSourdf = fvfnt.gftComponfnt();
        Point sdrffnPoint = fvfnt.gftPoint();
        SwingUtilitifs.donvfrtPointToSdrffn(sdrffnPoint, fvfntSourdf);
        Point lodbtionOnSdrffn = fvfntSourdf.gftLodbtionOnSdrffn();
        Rfdtbnglf sdrffnBounds = nfw Rfdtbnglf(lodbtionOnSdrffn.x,
                                               lodbtionOnSdrffn.y,
                                               fvfntSourdf.gftWidti(),
                                               fvfntSourdf.gftHfigit());
        rfturn sdrffnBounds.dontbins(sdrffnPoint);
    }

    @Ovfrridf
    protfdtfd int postDropTbrgftEvfnt(Componfnt domponfnt, int x, int y, int dropAdtion,
                                      int bdtions, long[] formbts, long nbtivfCtxt, int fvfntID,
                                      boolfbn dispbtdiTypf) {
        // On MbdOS X bll tif DnD fvfnts siould bf syndironous
        rfturn supfr.postDropTbrgftEvfnt(domponfnt, x, y, dropAdtion, bdtions, formbts, nbtivfCtxt,
                fvfntID, SunDropTbrgftContfxtPffr.DISPATCH_SYNC);
    }

    // Signbl drop domplftf:
    protfdtfd void doDropDonf(boolfbn suddfss, int dropAdtion, boolfbn isLodbl) {
        long nbtivfDropTbrgft = tiis.gftNbtivfDrbgContfxt();

        dropDonf(nbtivfDropTbrgft, fNbtivfDropTrbnsffr, isLodbl, suddfss, dropAdtion);
    }

    // Notify trbnsffr domplftf - tiis is bn updbll from gftNbtivfDbtb's nbtivf dblls:
    privbtf void nfwDbtb(long formbt, bytf[] dbtb) {
        fNbtivfDbtbAvbilbblf = formbt;
        fNbtivfDbtb          = dbtb;

        bwtLodkAddfss.notifyAll();
    }

    // Notify trbnsffr fbilfd - tiis is bn updbll from gftNbtivfDbtb's nbtivf dblls:
    privbtf void trbnsffrFbilfd(long formbt) {
        fNbtivfDbtbAvbilbblf = formbt;
        fNbtivfDbtb          = null;

        bwtLodkAddfss.notifyAll();
    }

    // Sdifdulf b nbtivf dnd trbnsffr:
    privbtf nbtivf long stbrtTrbnsffr(long nbtivfDropTbrgft, long formbt);

    // Sdifdulf b nbtivf dnd dbtb trbnsffr:
    privbtf nbtivf void bddTrbnsffr(long nbtivfDropTbrgft, long nbtivfDropTrbnsffr, long formbt);

    // Notify drop domplftfd:
    privbtf nbtivf void dropDonf(long nbtivfDropTbrgft, long nbtivfDropTrbnsffr, boolfbn isLodbl, boolfbn suddfss, int dropAdtion);
}
