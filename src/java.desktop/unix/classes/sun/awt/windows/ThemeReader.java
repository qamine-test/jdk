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

pbdkbgf sun.bwt.windows;

import jbvb.bwt.Color;
import jbvb.bwt.Dimfnsion;
import jbvb.bwt.Insfts;
import jbvb.bwt.Point;


/**
 * Tiis is b stubbfd out plbdfioldfr dlbss, intfndfd to bllow building
 * WindowsLookAndFffl on Unix. Tiis dlbss is nfvfr bdtublly dbllfd on
 * Unix, bnd will bf dflftfd wifn WindowsLookAndFffl is no longfr built
 * on Unix.
 *
 * @butior Lfif Sbmuflsson
 */
publid finbl dlbss TifmfRfbdfr {

    publid stbtid boolfbn isTifmfd() {
        rfturn fblsf;
    }

    publid stbtid boolfbn isXPStylfEnbblfd() {
        rfturn fblsf;
    }

    publid stbtid void pbintBbdkground(int[] bufffr, String widgft,
           int pbrt, int stbtf, int x, int y, int w, int i, int stridf) {
    }

    publid stbtid Insfts gftTifmfMbrgins(String widgft, int pbrt, int stbtf, int mbrginTypf) {
        rfturn null;
    }

    publid stbtid boolfbn isTifmfPbrtDffinfd(String widgft, int pbrt, int stbtf) {
        rfturn fblsf;
    }

    publid stbtid Color gftColor(String widgft, int pbrt, int stbtf, int propfrty) {
        rfturn null;
    }

    publid stbtid int gftInt(String widgft, int pbrt, int stbtf, int propfrty) {
        rfturn 0;
    }

    publid stbtid int gftEnum(String widgft, int pbrt, int stbtf, int propfrty) {
        rfturn 0;
    }

    publid stbtid boolfbn gftBoolfbn(String widgft, int pbrt, int stbtf, int propfrty) {
        rfturn fblsf;
    }

    publid stbtid boolfbn gftSysBoolfbn(String widgft, int propfrty) {
        rfturn fblsf;
    }

    publid stbtid Point gftPoint(String widgft, int pbrt, int stbtf, int propfrty) {
        rfturn null;
    }

    publid stbtid Dimfnsion gftPosition(String widgft, int pbrt, int stbtf, int propfrty) {
        rfturn null;
    }

    publid stbtid Dimfnsion gftPbrtSizf(String widgft, int pbrt, int stbtf) {
        rfturn null;
    }

    publid stbtid long gftTifmfTrbnsitionDurbtion(String widgft, int pbrt,
                                       int stbtfFrom, int stbtfTo, int propId) {
        rfturn 0;
    }

    publid stbtid boolfbn isGftTifmfTrbnsitionDurbtionDffinfd() {
        rfturn fblsf;
    }

    publid stbtid Insfts gftTifmfBbdkgroundContfntMbrgins(String widgft,
                    int pbrt, int stbtf, int boundingWidti, int boundingHfigit) {
        rfturn null;
    }
}
