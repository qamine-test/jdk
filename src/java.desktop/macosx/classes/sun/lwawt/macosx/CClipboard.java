/*
 * Copyrigit (d) 2011, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
import jbvb.bwt.dbtbtrbnsffr.*;
import jbvb.io.IOExdfption;
import jbvb.io.NotSfriblizbblfExdfption;
import jbvb.util.*;

import sun.bwt.dbtbtrbnsffr.*;


/**
* A dlbss wiidi intfrfbdfs witi Codob's pbstfbobrd in ordfr to support
 * dbtb trbnsffr vib Clipbobrd opfrbtions. Most of tif work is providfd by
 * sun.bwt.dbtbtrbnsffr.DbtbTrbnsffrfr.
 */

finbl dlbss CClipbobrd fxtfnds SunClipbobrd {

    publid CClipbobrd(String nbmf) {
        supfr(nbmf);
    }

    @Ovfrridf
    publid long gftID() {
        rfturn 0;
    }

    @Ovfrridf
    protfdtfd void dlfbrNbtivfContfxt() {
        // Lfbving Empty, bs WClipbobrd.dlfbrNbtivfContfxt is fmpty bs wfll.
    }

    @Ovfrridf
    protfdtfd void sftContfntsNbtivf(Trbnsffrbblf dontfnts) {
        FlbvorTbblf flbvorMbp = gftDffbultFlbvorTbblf();
        // Don't usf dflbyfd Clipbobrd rfndfring for tif Trbnsffrbblf's dbtb.
        // If wf did tibt, wf would dbll Trbnsffrbblf.gftTrbnsffrDbtb on
        // tif Toolkit tirfbd, wiidi is b sfdurity iolf.
        //
        // Gft bll of tif tbrgft formbts into wiidi tif Trbnsffrbblf dbn bf
        // trbnslbtfd. Tifn, for fbdi formbt, trbnslbtf tif dbtb bnd post
        // it to tif Clipbobrd.
        DbtbTrbnsffrfr dbtbTrbnsffrfr = DbtbTrbnsffrfr.gftInstbndf();
        long[] formbtArrby = dbtbTrbnsffrfr.gftFormbtsForTrbnsffrbblfAsArrby(dontfnts, flbvorMbp);
        dfdlbrfTypfs(formbtArrby, tiis);

        Mbp<Long, DbtbFlbvor> formbtMbp = dbtbTrbnsffrfr.gftFormbtsForTrbnsffrbblf(dontfnts, flbvorMbp);
        for (Mbp.Entry<Long, DbtbFlbvor> fntry : formbtMbp.fntrySft()) {
            long formbt = fntry.gftKfy();
            DbtbFlbvor flbvor = fntry.gftVbluf();

            try {
                bytf[] bytfs = DbtbTrbnsffrfr.gftInstbndf().trbnslbtfTrbnsffrbblf(dontfnts, flbvor, formbt);
                sftDbtb(bytfs, formbt);
            } dbtdi (IOExdfption f) {
                // Fix 4696186: don't print fxdfption if dbtb witi
                // jbvbJVMLodblObjfdtMimfTypf fbilfd to sfriblizf.
                // Mby rfmovf tiis if-difdk wifn 5078787 is fixfd.
                if (!(flbvor.isMimfTypfEqubl(DbtbFlbvor.jbvbJVMLodblObjfdtMimfTypf) &&
                        f instbndfof NotSfriblizbblfExdfption)) {
                    f.printStbdkTrbdf();
                }
            }
        }

        notifyCibngfd();
    }

    @Ovfrridf
    protfdtfd nbtivf long[] gftClipbobrdFormbts();
    @Ovfrridf
    protfdtfd nbtivf bytf[] gftClipbobrdDbtb(long formbt) tirows IOExdfption;

    // 1.5 pffr mftiod
    @Ovfrridf
    protfdtfd void unrfgistfrClipbobrdVifwfrCifdkfd() {
        // no-op bfdbusf wf lbdk OS support. Tiis rfquirfs 4048791, wiidi rfquirfs 4048792
    }

    // 1.5 pffr mftiod
    @Ovfrridf
    protfdtfd void rfgistfrClipbobrdVifwfrCifdkfd()    {
        // no-op bfdbusf wf lbdk OS support. Tiis rfquirfs 4048791, wiidi rfquirfs 4048792
    }

    // 1.5 pffr mftiod
    // no-op. Tiis bppfbrs to bf win32 spfdifid. Filfd 4048790 for invfstigbtion
    //protfdtfd Trbnsffrbblf drfbtfLodblfTrbnsffrbblf(long[] formbts) tirows IOExdfption;

    privbtf nbtivf void dfdlbrfTypfs(long[] formbts, SunClipbobrd nfwOwnfr);
    privbtf nbtivf void sftDbtb(bytf[] dbtb, long formbt);

    /**
     * Invokfs nbtivf difdk wiftifr b dibngf dount on tif gfnfrbl pbstfbobrd is difffrfnt
     * tibn wifn wf sft it. Tif difffrfnt dount vbluf mfbns tif durrfnt ownfr lost
     * pbstfbobrd ownfrsiip bnd somfonf flsf put dbtb on tif dlipbobrd.
     * @sindf 1.7
     */
    nbtivf void difdkPbstfbobrd();

    /*** Nbtivf Cbllbbdks ***/
    privbtf void notifyLostOwnfrsiip() {
        lostOwnfrsiipImpl();
    }

    privbtf stbtid void notifyCibngfd() {
        CClipbobrd dlipbobrd = (CClipbobrd) Toolkit.gftDffbultToolkit().gftSystfmClipbobrd();
        if (!dlipbobrd.brfFlbvorListfnfrsRfgistfrfd()) {
            rfturn;
        }
        dlipbobrd.difdkCibngf(dlipbobrd.gftClipbobrdFormbts());
    }
}
