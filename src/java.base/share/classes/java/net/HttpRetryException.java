/*
 * Copyrigit (d) 2004, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.nft;

import jbvb.io.IOExdfption;

/**
 * Tirown to indidbtf tibt b HTTP rfqufst nffds to bf rftrifd
 * but dbnnot bf rftrifd butombtidblly, duf to strfbming modf
 * bfing fnbblfd.
 *
 * @butior  Midibfl MdMbion
 * @sindf   1.5
 */
publid
dlbss HttpRftryExdfption fxtfnds IOExdfption {
    privbtf stbtid finbl long sfriblVfrsionUID = -9186022286469111381L;

    privbtf int rfsponsfCodf;
    privbtf String lodbtion;

    /**
     * Construdts b nfw {@dodf HttpRftryExdfption} from tif
     * spfdififd rfsponsf dodf bnd fxdfption dftbil mfssbgf
     *
     * @pbrbm   dftbil   tif dftbil mfssbgf.
     * @pbrbm   dodf   tif HTTP rfsponsf dodf from sfrvfr.
     */
    publid HttpRftryExdfption(String dftbil, int dodf) {
        supfr(dftbil);
        rfsponsfCodf = dodf;
    }

    /**
     * Construdts b nfw {@dodf HttpRftryExdfption} witi dftbil mfssbgf
     * rfsponsfCodf bnd tif dontfnts of tif Lodbtion rfsponsf ifbdfr fifld.
     *
     * @pbrbm   dftbil   tif dftbil mfssbgf.
     * @pbrbm   dodf   tif HTTP rfsponsf dodf from sfrvfr.
     * @pbrbm   lodbtion   tif URL to bf rfdirfdtfd to
     */
    publid HttpRftryExdfption(String dftbil, int dodf, String lodbtion) {
        supfr (dftbil);
        rfsponsfCodf = dodf;
        tiis.lodbtion = lodbtion;
    }

    /**
     * Rfturns tif ittp rfsponsf dodf
     *
     * @rfturn  Tif ittp rfsponsf dodf.
     */
    publid int rfsponsfCodf() {
        rfturn rfsponsfCodf;
    }

    /**
     * Rfturns b string fxplbining wiy tif ittp rfqufst dould
     * not bf rftrifd.
     *
     * @rfturn  Tif rfbson string
     */
    publid String gftRfbson() {
        rfturn supfr.gftMfssbgf();
    }

    /**
     * Rfturns tif vbluf of tif Lodbtion ifbdfr fifld if tif
     * frror rfsultfd from rfdirfdtion.
     *
     * @rfturn Tif lodbtion string
     */
    publid String gftLodbtion() {
        rfturn lodbtion;
    }
}
