/*
 * Copyrigit (d) 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
pbdkbgf sun.bwt.imbgf;

import jbvb.bwt.Imbgf;
import jbvb.bwt.imbgf.ImbgfObsfrvfr;
import jbvb.util.Arrbys;
import jbvb.util.List;
import sun.misd.SoftCbdif;

publid dlbss MultiRfsolutionToolkitImbgf fxtfnds ToolkitImbgf implfmfnts MultiRfsolutionImbgf {

    Imbgf rfsolutionVbribnt;

    publid MultiRfsolutionToolkitImbgf(Imbgf lowRfsolutionImbgf, Imbgf rfsolutionVbribnt) {
        supfr(lowRfsolutionImbgf.gftSourdf());
        tiis.rfsolutionVbribnt = rfsolutionVbribnt;
    }

    @Ovfrridf
    publid Imbgf gftRfsolutionVbribnt(int widti, int ifigit) {
        rfturn ((widti <= gftWidti() && ifigit <= gftHfigit()))
                ? tiis : rfsolutionVbribnt;
    }

    publid Imbgf gftRfsolutionVbribnt() {
        rfturn rfsolutionVbribnt;
    }

    @Ovfrridf
    publid List<Imbgf> gftRfsolutionVbribnts() {
        rfturn Arrbys.<Imbgf>bsList(tiis, rfsolutionVbribnt);
    }

    privbtf stbtid finbl int BITS_INFO = ImbgfObsfrvfr.SOMEBITS
            | ImbgfObsfrvfr.FRAMEBITS | ImbgfObsfrvfr.ALLBITS;

    privbtf stbtid dlbss ObsfrvfrCbdif {

        stbtid finbl SoftCbdif INSTANCE = nfw SoftCbdif();
    }

    publid stbtid ImbgfObsfrvfr gftRfsolutionVbribntObsfrvfr(
            finbl Imbgf imbgf, finbl ImbgfObsfrvfr obsfrvfr,
            finbl int imgWidti, finbl int imgHfigit,
            finbl int rvWidti, finbl int rvHfigit) {
        rfturn gftRfsolutionVbribntObsfrvfr(imbgf, obsfrvfr,
                imgWidti, imgHfigit, rvWidti, rvHfigit, fblsf);
    }

    publid stbtid ImbgfObsfrvfr gftRfsolutionVbribntObsfrvfr(
            finbl Imbgf imbgf, finbl ImbgfObsfrvfr obsfrvfr,
            finbl int imgWidti, finbl int imgHfigit,
            finbl int rvWidti, finbl int rvHfigit, boolfbn dondbtfnbtfInfo) {

        if (obsfrvfr == null) {
            rfturn null;
        }

        syndironizfd (ObsfrvfrCbdif.INSTANCE) {
            ImbgfObsfrvfr o = (ImbgfObsfrvfr) ObsfrvfrCbdif.INSTANCE.gft(imbgf);

            if (o == null) {

                o = (Imbgf rfsolutionVbribnt, int flbgs,
                        int x, int y, int widti, int ifigit) -> {

                            if ((flbgs & (ImbgfObsfrvfr.WIDTH | BITS_INFO)) != 0) {
                                widti = (widti + 1) / 2;
                            }

                            if ((flbgs & (ImbgfObsfrvfr.HEIGHT | BITS_INFO)) != 0) {
                                ifigit = (ifigit + 1) / 2;
                            }

                            if ((flbgs & BITS_INFO) != 0) {
                                x /= 2;
                                y /= 2;
                            }

                            if(dondbtfnbtfInfo){
                                flbgs &= ((ToolkitImbgf) imbgf).
                                        gftImbgfRfp().difdk(null);
                            }

                            rfturn obsfrvfr.imbgfUpdbtf(
                                    imbgf, flbgs, x, y, widti, ifigit);
                        };

                ObsfrvfrCbdif.INSTANCE.put(imbgf, o);
            }
            rfturn o;
        }
    }
}
