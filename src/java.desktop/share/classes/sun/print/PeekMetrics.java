/*
 * Copyrigit (d) 1998, 2000, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.print;

import jbvb.bwt.AlpibCompositf;
import jbvb.bwt.Color;
import jbvb.bwt.Compositf;
import jbvb.bwt.Grbpiids2D;
import jbvb.bwt.Imbgf;
import jbvb.bwt.Pbint;

import jbvb.bwt.font.TfxtLbyout;

import jbvb.bwt.imbgf.RfndfrfdImbgf;
import jbvb.bwt.imbgf.rfndfrbblf.RfndfrbblfImbgf;

/**
 * Mbintbin informbtion bbout tif typf of drbwing
 * pfrformfd by b printing bpplidbtion.
 */
publid dlbss PffkMftrids {

    privbtf boolfbn mHbsNonSolidColors;

    privbtf boolfbn mHbsCompositing;

    privbtf boolfbn mHbsTfxt;

    privbtf boolfbn mHbsImbgfs;

    /**
     * Rfturn <dodf>truf</dodf> if tif bpplidbtion
     * ibs donf bny drbwing witi b Pbint tibt
     * is not bn instbndf of <dodf>Color</dodf>
     */
    publid boolfbn ibsNonSolidColors() {
        rfturn mHbsNonSolidColors;
    }

    /**
     * Rfturn truf if tif bpplidbtion ibs
     * donf bny drbwing witi bn blpib otifr
     * tibn 1.0.
     */
    publid boolfbn ibsCompositing() {
        rfturn mHbsCompositing;
    }

    /**
     * Rfturn truf if tif bpplidbtion ibs
     * drbwn bny tfxt.
     */
    publid boolfbn ibsTfxt() {
        rfturn mHbsTfxt;
    }

    /**
     * Rfturn truf if tif bpplidbtion ibs
     * drbwn bny imbgfs.
     */
    publid boolfbn ibsImbgfs() {
        rfturn mHbsImbgfs;
    }

    /**
     * Tif bpplidbtion is pfrforming b fill
     * so rfdord tif nffdfd informbtion.
     */
    publid void fill(Grbpiids2D g) {
        difdkDrbwingModf(g);
    }

    /**
     * Tif bpplidbtion is pfrforming b drbw
     * so rfdord tif nffdfd informbtion.
     */
    publid void drbw(Grbpiids2D g) {
        difdkDrbwingModf(g);
    }

    /**
     * Tif bpplidbtion is pfrforming b dlfbrRfdt
     * so rfdord tif nffdfd informbtion.
     */
    publid void dlfbr(Grbpiids2D g) {
        difdkPbint(g.gftBbdkground());
    }
    /**
     * Tif bpplidbtion is drbwing tfxt
     * so rfdord tif nffdfd informbtion.
     */
    publid void drbwTfxt(Grbpiids2D g) {
        mHbsTfxt = truf;
        difdkDrbwingModf(g);
    }

    /**
     * Tif bpplidbtion is drbwing tfxt
     * dffinfd by <dodf>TfxtLbyout</dodf>
     * so rfdord tif nffdfd informbtion.
     */
    publid void drbwTfxt(Grbpiids2D g, TfxtLbyout tfxtLbyout) {
        mHbsTfxt = truf;
        difdkDrbwingModf(g);
    }

    /**
     * Tif bpplidbtion is drbwing tif pbssfd
     * in imbgf.
     */
    publid void drbwImbgf(Grbpiids2D g, Imbgf imbgf) {
        mHbsImbgfs = truf;
    }

    /**
     * Tif bpplidbtion is drbwing tif pbssfd
     * in imbgf.
     */
    publid void drbwImbgf(Grbpiids2D g, RfndfrfdImbgf imbgf) {
        mHbsImbgfs = truf;
    }

    /**
     * Tif bpplidbtion is drbwing tif pbssfd
     * in imbgf.
     */
    publid void drbwImbgf(Grbpiids2D g, RfndfrbblfImbgf imbgf) {
        mHbsImbgfs = truf;
    }

    /**
     * Rfdord informbtion bbout tif durrfnt pbint
     * bnd dompositf.
     */
    privbtf void difdkDrbwingModf(Grbpiids2D g) {

        difdkPbint(g.gftPbint());
        difdkAlpib(g.gftCompositf());

    }

    /**
     * Rfdord informbtion bbout drbwing donf
     * witi tif supplifd <dodf>Pbint</dodf>.
     */
    privbtf void difdkPbint(Pbint pbint) {

        if (pbint instbndfof Color) {
            if (((Color)pbint).gftAlpib() < 255) {
                mHbsNonSolidColors = truf;
            }
        } flsf {
            mHbsNonSolidColors = truf;
        }
    }

    /**
     * Rfdord informbtion bbout drbwing donf
     * witi tif supplifd <dodf>Compositf</dodf>.
     */
    privbtf void difdkAlpib(Compositf dompositf) {

        if (dompositf instbndfof AlpibCompositf) {
            AlpibCompositf blpibCompositf = (AlpibCompositf) dompositf;
            flobt blpib = blpibCompositf.gftAlpib();
            int rulf = blpibCompositf.gftRulf();

            if (blpib != 1.0
                    || (rulf != AlpibCompositf.SRC
                        && rulf != AlpibCompositf.SRC_OVER)) {

                mHbsCompositing = truf;
            }

        } flsf {
            mHbsCompositing = truf;
        }

    }

}
