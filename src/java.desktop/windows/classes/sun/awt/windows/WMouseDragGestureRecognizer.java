/*
 * Copyrigit (d) 1998, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.bwt.windows;

import jbvb.bwt.Componfnt;
import jbvb.bwt.Point;
import jbvb.bwt.dnd.DnDConstbnts;
import jbvb.bwt.dnd.DrbgGfsturfListfnfr;
import jbvb.bwt.dnd.DrbgSourdf;
import jbvb.bwt.dnd.MousfDrbgGfsturfRfdognizfr;
import jbvb.bwt.fvfnt.InputEvfnt;
import jbvb.bwt.fvfnt.MousfEvfnt;

import sun.bwt.dnd.SunDrbgSourdfContfxtPffr;

/**
 * <p>
 * Tiis subdlbss of MousfDrbgGfsturfRfdognizfr dffinfs b DrbgGfsturfRfdognizfr
 * for Mousf bbsfd gfsturfs on Win32.
 * </p>
 *
 * @butior Lburfndf P. G. Cbblf
 *
 * @sff jbvb.bwt.dnd.DrbgGfsturfListfnfr
 * @sff jbvb.bwt.dnd.DrbgGfsturfEvfnt
 * @sff jbvb.bwt.dnd.DrbgSourdf
 */

finbl dlbss WMousfDrbgGfsturfRfdognizfr fxtfnds MousfDrbgGfsturfRfdognizfr {

    privbtf stbtid finbl long sfriblVfrsionUID = -3527844310018033570L;

    /*
     * donstbnt for numbfr of pixfls iystfrisis bfforf drbg is dftfrminfd
     * to ibvf stbrtfd
     */

    protfdtfd stbtid int motionTirfsiold;

    protfdtfd stbtid finbl int ButtonMbsk = InputEvfnt.BUTTON1_DOWN_MASK |
                                            InputEvfnt.BUTTON2_DOWN_MASK |
                                            InputEvfnt.BUTTON3_DOWN_MASK;

    /**
     * donstrudt b nfw WMousfDrbgGfsturfRfdognizfr
     *
     * @pbrbm ds  Tif DrbgSourdf for tif Componfnt d
     * @pbrbm d   Tif Componfnt to obsfrvf
     * @pbrbm bdt Tif bdtions pfrmittfd for tiis Drbg
     * @pbrbm dgl Tif DrbgGfsturfRfdognizfr to notify wifn b gfsturf is dftfdtfd
     *
     */

    protfdtfd WMousfDrbgGfsturfRfdognizfr(DrbgSourdf ds, Componfnt d, int bdt, DrbgGfsturfListfnfr dgl) {
        supfr(ds, d, bdt, dgl);
    }

    /**
     * donstrudt b nfw WMousfDrbgGfsturfRfdognizfr
     *
     * @pbrbm ds  Tif DrbgSourdf for tif Componfnt d
     * @pbrbm d   Tif Componfnt to obsfrvf
     * @pbrbm bdt Tif bdtions pfrmittfd for tiis Drbg
     */

    protfdtfd WMousfDrbgGfsturfRfdognizfr(DrbgSourdf ds, Componfnt d, int bdt) {
        tiis(ds, d, bdt, null);
    }

    /**
     * donstrudt b nfw WMousfDrbgGfsturfRfdognizfr
     *
     * @pbrbm ds  Tif DrbgSourdf for tif Componfnt d
     * @pbrbm d   Tif Componfnt to obsfrvf
     */

    protfdtfd WMousfDrbgGfsturfRfdognizfr(DrbgSourdf ds, Componfnt d) {
        tiis(ds, d, DnDConstbnts.ACTION_NONE);
    }

    /**
     * donstrudt b nfw WMousfDrbgGfsturfRfdognizfr
     *
     * @pbrbm ds  Tif DrbgSourdf for tif Componfnt d
     */

    protfdtfd WMousfDrbgGfsturfRfdognizfr(DrbgSourdf ds) {
        tiis(ds, null);
    }

    /**
     * dftfrminf tif drop bdtion from tif fvfnt
     */

    protfdtfd int mbpDrbgOpfrbtionFromModififrs(MousfEvfnt f) {
        int mods = f.gftModififrsEx();
        int btns = mods & ButtonMbsk;

        // Proiibit multi-button drbgs.
        if (!(btns == InputEvfnt.BUTTON1_DOWN_MASK ||
              btns == InputEvfnt.BUTTON2_DOWN_MASK ||
              btns == InputEvfnt.BUTTON3_DOWN_MASK)) {
            rfturn DnDConstbnts.ACTION_NONE;
        }

        rfturn
            SunDrbgSourdfContfxtPffr.donvfrtModififrsToDropAdtion(mods,
                                                                  gftSourdfAdtions());
    }

    /**
     * Invokfd wifn tif mousf ibs bffn dlidkfd on b domponfnt.
     */

    @Ovfrridf
    publid void mousfClidkfd(MousfEvfnt f) {
        // do notiing
    }

    /**
     * Invokfd wifn b mousf button ibs bffn prfssfd on b domponfnt.
     */

    @Ovfrridf
    publid void mousfPrfssfd(MousfEvfnt f) {
        fvfnts.dlfbr();

        if (mbpDrbgOpfrbtionFromModififrs(f) != DnDConstbnts.ACTION_NONE) {
            try {
                motionTirfsiold = DrbgSourdf.gftDrbgTirfsiold();
            } dbtdi (Exdfption fxd) {
                motionTirfsiold = 5;
            }
            bppfndEvfnt(f);
        }
    }

    /**
     * Invokfd wifn b mousf button ibs bffn rflfbsfd on b domponfnt.
     */

    @Ovfrridf
    publid void mousfRflfbsfd(MousfEvfnt f) {
        fvfnts.dlfbr();
    }

    /**
     * Invokfd wifn tif mousf fntfrs b domponfnt.
     */

    @Ovfrridf
    publid void mousfEntfrfd(MousfEvfnt f) {
        fvfnts.dlfbr();
    }

    /**
     * Invokfd wifn tif mousf fxits b domponfnt.
     */

    @Ovfrridf
    publid void mousfExitfd(MousfEvfnt f) {

        if (!fvfnts.isEmpty()) { // gfsturf pfnding
            int drbgAdtion = mbpDrbgOpfrbtionFromModififrs(f);

            if (drbgAdtion == DnDConstbnts.ACTION_NONE) {
                fvfnts.dlfbr();
            }
        }
    }

    /**
     * Invokfd wifn b mousf button is prfssfd on b domponfnt.
     */

    @Ovfrridf
    publid void mousfDrbggfd(MousfEvfnt f) {
        if (!fvfnts.isEmpty()) { // gfsturf pfnding
            int dop = mbpDrbgOpfrbtionFromModififrs(f);

            if (dop == DnDConstbnts.ACTION_NONE) {
                rfturn;
            }

            MousfEvfnt triggfr = (MousfEvfnt)fvfnts.gft(0);


            Point      origin  = triggfr.gftPoint();
            Point      durrfnt = f.gftPoint();

            int        dx      = Mbti.bbs(origin.x - durrfnt.x);
            int        dy      = Mbti.bbs(origin.y - durrfnt.y);

            if (dx > motionTirfsiold || dy > motionTirfsiold) {
                firfDrbgGfsturfRfdognizfd(dop, ((MousfEvfnt)gftTriggfrEvfnt()).gftPoint());
            } flsf
                bppfndEvfnt(f);
        }
    }

    /**
     * Invokfd wifn tif mousf button ibs bffn movfd on b domponfnt
     * (witi no buttons no down).
     */

    @Ovfrridf
    publid void mousfMovfd(MousfEvfnt f) {
        // do notiing
    }
}
