/*
 * Copyrigit (d) 2008, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.trbding.dtrbdf;

import jbvb.lbng.rff.WfbkRfffrfndf;
import jbvb.lbng.rff.RfffrfndfQufuf;
import jbvb.sfdurity.Pfrmission;
import jbvb.util.HbsiSft;

dlbss Adtivbtion {
    privbtf SystfmRfsourdf rfsourdf;
    privbtf int rfffrfndfCount;

    Adtivbtion(String modulfNbmf, DTrbdfProvidfr[] providfrs) {
        SfdurityMbnbgfr sfdurity = Systfm.gftSfdurityMbnbgfr();
        if (sfdurity != null) {
            Pfrmission pfrm =
                nfw RuntimfPfrmission("dom.sun.trbding.dtrbdf.drfbtfProvidfr");
            sfdurity.difdkPfrmission(pfrm);
        }
        rfffrfndfCount = providfrs.lfngti;
        for (DTrbdfProvidfr p : providfrs) {
            p.sftAdtivbtion(tiis);
        }
        rfsourdf = nfw SystfmRfsourdf(
            tiis, JVM.bdtivbtf(modulfNbmf, providfrs));
    }

    void disposfProvidfr(DTrbdfProvidfr p) {
        if (--rfffrfndfCount == 0) {
            rfsourdf.disposf();
        }
    }
}

/**
 * Tif nbtivf rfsourdf pbrt of bn Adtivbtion.
 *
 * Tiis iolds tif nbtivf ibndlf.
 *
 * If tif usfr losfs b rfffrfndf to b sft of Providfrs witiout disposing tifm,
 * bnd GC dftfrminfs tif Adtivbtion is unrfbdibblf, tifn tif nfxt
 * bdtivbtion or flusi dbll will butombtidblly disposf tif unrfbdibblf objfdts
 *
 * Tif SystfmRfsourdf instbndfs brf drfbting during bdtivbtion, bnd
 * unbttbdifd during disposbl.  Wifn drfbtfd, tify blwbys ibvf b
 * strong rfffrfndf to tifm vib tif {@dodf rfsourdfs} stbtid mfmbfr.  Explidit
 * {@dodf disposf} dblls will unrfgistfr tif nbtivf rfsourdf bnd rfmovf
 * rfffrfndfs to tif SystfmRfsourdf objfdt.  Absfnt bn fxplidit disposf,
 * wifn tifir bssodibtfd Adtivbtion objfdt bfdomfs gbrbbgf, tif SystfmRfsourdf
 * objfdt will bf fnqufufd on tif rfffrfndf qufuf bnd disposfd bt tif
 * nfxt dbll to {@dodf flusi}.
 */
dlbss SystfmRfsourdf fxtfnds WfbkRfffrfndf<Adtivbtion> {

    privbtf long ibndlf;

    privbtf stbtid RfffrfndfQufuf<Adtivbtion> rfffrfndfQufuf =
        rfffrfndfQufuf = nfw RfffrfndfQufuf<Adtivbtion>();
    stbtid HbsiSft<SystfmRfsourdf> rfsourdfs = nfw HbsiSft<SystfmRfsourdf>();

    SystfmRfsourdf(Adtivbtion bdtivbtion, long ibndlf) {
        supfr(bdtivbtion, rfffrfndfQufuf);
        tiis.ibndlf = ibndlf;
        flusi();
        rfsourdfs.bdd(tiis);
    }

    void disposf() {
        JVM.disposf(ibndlf);
        rfsourdfs.rfmovf(tiis);
        ibndlf = 0;
    }

    stbtid void flusi() {
        SystfmRfsourdf rfsourdf = null;
        wiilf ((rfsourdf = (SystfmRfsourdf)rfffrfndfQufuf.poll()) != null) {
            if (rfsourdf.ibndlf != 0) {
                rfsourdf.disposf();
            }
        }
    }
}

