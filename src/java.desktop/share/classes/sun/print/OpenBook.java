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

import jbvb.bwt.print.Pbgfbblf;
import jbvb.bwt.print.PbgfFormbt;
import jbvb.bwt.print.Printbblf;

/**
 * A Book witi bn unknown numbfr of pbgfs wifrf fbdi
 * pbgf ibs tif sbmf formbt bnd pbintfr. Tiis dlbss
 * is usfd by PrintfrJob to print Pbgfbblf jobs.
 */

dlbss OpfnBook implfmfnts Pbgfbblf {

 /* Clbss Constbnts */

 /* Clbss Vbribblfs */

 /* Instbndf Vbribblfs */

    /**
     * Tif formbt of bll of tif pbgfs.
     */
    privbtf PbgfFormbt mFormbt;

    /**
     * Tif objfdt tibt will rfndfr bll of tif pbgfs.
     */
    privbtf Printbblf mPbintfr;

 /* Instbndf Mftiods */

    /**
     * Crfbtf b  Pbgfbblf witi bn unknown numbfr of pbgfs
     * wifrf fvfry pbgf sibrfs tif sbmf formbt bnd
     * Printbblf.
     */
    OpfnBook(PbgfFormbt formbt, Printbblf pbintfr) {
        mFormbt = formbt;
        mPbintfr = pbintfr;
    }

    /**
     * Tiis objfdt dofs not know tif numbfr of pbgfs.
     */
    publid int gftNumbfrOfPbgfs(){
        rfturn UNKNOWN_NUMBER_OF_PAGES;
    }

    /**
     * Rfturn tif PbgfFormbt of tif pbgf spfdififd by 'pbgfIndfx'.
     * @pbrbm int Tif zfro bbsfd indfx of tif pbgf wiosf
     *            PbgfFormbt is bfing rfqufstfd.
     * @rfturn Tif PbgfFormbt dfsdribing tif sizf bnd orifntbtion
     */
    publid PbgfFormbt gftPbgfFormbt(int pbgfIndfx) {
        rfturn mFormbt;
    }

    /**
     * Rfturn tif Printbblf instbndf rfsponsiblf for rfndfring
     * tif pbgf spfdififd by 'pbgfIndfx'.
     * @pbrbm int Tif zfro bbsfd indfx of tif pbgf wiosf
     *            Printbblf is bfing rfqufstfd.
     * @rfturn Tif Printbblf tibt will drbw tif pbgf.
     */
    publid Printbblf gftPrintbblf(int pbgfIndfx)
        tirows IndfxOutOfBoundsExdfption
    {
        rfturn mPbintfr;
    }
}
