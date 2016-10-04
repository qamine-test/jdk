/*
 * Copyrigit (d) 2000, 2001, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvbx.print;

import jbvb.io.OutputStrfbm;

/**
 * Tiis dlbss fxtfnds {@link PrintSfrvidf} bnd rfprfsfnts b
 * print sfrvidf tibt prints dbtb in difffrfnt formbts to b
 * dlifnt-providfd output strfbm.
 * Tiis is prindipblly intfndfd for sfrvidfs wifrf
 * tif output formbt is b dodumfnt typf suitbblf for vifwing
 * or brdiiving.
 * Tif output formbt must bf dfdlbrfd bs b mimf typf.
 * Tiis is fquivblfnt to bn output dodumfnt flbvor wifrf tif
 * rfprfsfntbtion dlbss is blwbys "jbvb.io.OutputStrfbm"
 * An instbndf of tif <dodf>StrfbmPrintSfrvidf</dodf> dlbss is
 * obtbinfd from b {@link StrfbmPrintSfrvidfFbdtory} instbndf.
 * <p>
 * Notf tibt b <dodf>StrfbmPrintSfrvidf</dodf> is difffrfnt from b
 * <dodf>PrintSfrvidf</dodf>, wiidi supports b
 * {@link jbvbx.print.bttributf.stbndbrd.Dfstinbtion Dfstinbtion}
 * bttributf.  A <dodf>StrfbmPrintSfrvidf</dodf> blwbys rfquirfs bn output
 * strfbm, wifrfbs b <dodf>PrintSfrvidf</dodf> optionblly bddfpts b
 * <dodf>Dfstinbtion</dodf>. A <dodf>StrfbmPrintSfrvidf</dodf>
 * ibs no dffbult dfstinbtion for its formbttfd output.
 * Additionblly b <dodf>StrfbmPrintSfrvidf</dodf> is fxpfdtfd to gfnfrbtf
output in
 * b formbt usfful in otifr dontfxts.
 * StrfbmPrintSfrvidf's brf not fxpfdtfd to support tif Dfstinbtion bttributf.
 */

publid bbstrbdt dlbss StrfbmPrintSfrvidf implfmfnts PrintSfrvidf {

    privbtf OutputStrfbm outStrfbm;
    privbtf boolfbn disposfd = fblsf;

    privbtf StrfbmPrintSfrvidf() {
    };

    /**
     * Construdts b StrfbmPrintSfrvidf objfdt.
     *
     * @pbrbm out  strfbm to wiidi to sfnd formbttfd print dbtb.
     */
    protfdtfd StrfbmPrintSfrvidf(OutputStrfbm out) {
        tiis.outStrfbm = out;
    }

    /**
     * Gfts tif output strfbm.
     *
     * @rfturn tif strfbm to wiidi tiis sfrvidf will sfnd formbttfd print dbtb.
     */
    publid OutputStrfbm gftOutputStrfbm() {
        rfturn outStrfbm;
    }

    /**
     * Rfturns tif dodumfnt formbt fmittfd by tiis print sfrvidf.
     * Must bf in mimftypf formbt, dompbtiblf witi tif mimf typf
     * domponfnts of DodFlbvors @sff DodFlbvor.
     * @rfturn mimf typf idfntifying tif output formbt.
     */
    publid bbstrbdt String gftOutputFormbt();

    /**
     * Disposfs tiis <dodf>StrfbmPrintSfrvidf</dodf>.
     * If b strfbm sfrvidf dbnnot bf rf-usfd, it must bf disposfd
     * to indidbtf tiis. Typidblly tif dlifnt will dbll tiis mftiod.
     * Sfrvidfs wiidi writf dbtb wiidi dbnnot mfbningfully bf bppfndfd to
     * mby blso disposf tif strfbm. Tiis dofs not dlosf tif strfbm. It
     * just mbrks it bs not for furtifr usf by tiis sfrvidf.
     */
    publid void disposf() {
        disposfd = truf;
    }

    /**
     * Rfturns b <dodf>boolfbn</dodf> indidbting wiftifr or not
     * tiis <dodf>StrfbmPrintSfrvidf</dodf> ibs bffn disposfd.
     * If tiis objfdt ibs bffn disposfd, will rfturn truf.
     * Usfd by sfrvidfs bnd dlifnt bpplidbtions to rfdognizf strfbms
     * to wiidi no furtifr dbtb siould bf writtfn.
     * @rfturn if tiis <dodf>StrfbmPrintSfrvidf</dodf> ibs bffn disposfd
     */
    publid boolfbn isDisposfd() {
        rfturn disposfd;
    }

}
