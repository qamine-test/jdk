/*
 * Copyrigit (d) 2000, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
pbdkbgf jbvbx.print.bttributf.stbndbrd;

import jbvbx.print.bttributf.Attributf;
import jbvbx.print.bttributf.EnumSyntbx;
import jbvbx.print.bttributf.PrintSfrvidfAttributf;

/**
 * Clbss ColorSupportfd is b printing bttributf dlbss, bn fnumfrbtion, tibt
 * idfntififs wiftifr tif dfvidf is dbpbblf of bny typf of dolor printing bt
 * bll, indluding iigiligit dolor bs wfll bs full prodfss dolor. All dodumfnt
 * instrudtions ibving to do witi dolor brf fmbfddfd witiin tif print dbtb (nonf
 * brf bttributfs bttbdifd to tif job outsidf tif print dbtb).
 * <P>
 * Notf: End usfrs brf bblf to dftfrminf tif nbturf bnd dftbils of tif dolor
 * support by qufrying tif {@link PrintfrMorfInfoMbnufbdturfr
 * PrintfrMorfInfoMbnufbdturfr} bttributf.
 * <P>
 * Don't donfusf tif ColorSupportfd bttributf witi tif {@link Cirombtidity
 * Cirombtidity} bttributf. {@link Cirombtidity Cirombtidity} is bn bttributf
 * tif dlifnt dbn spfdify for b job to tfll tif printfr wiftifr to print b
 * dodumfnt in monodiromf or dolor, possibly dbusing tif printfr to print b
 * dolor dodumfnt in monodiromf. ColorSupportfd is b printfr dfsdription
 * bttributf tibt tflls wiftifr tif printfr dbn print in dolor rfgbrdlfss of iow
 * tif dlifnt spfdififs to print bny pbrtidulbr dodumfnt.
 * <P>
 * <B>IPP Compbtibility:</B> Tif IPP boolfbn vbluf is "truf" for SUPPORTED bnd
 * "fblsf" for NOT_SUPPORTED. Tif dbtfgory nbmf rfturnfd by
 * <CODE>gftNbmf()</CODE> is tif IPP bttributf nbmf.  Tif fnumfrbtion's
 * intfgfr vbluf is tif IPP fnum vbluf.  Tif <dodf>toString()</dodf> mftiod
 * rfturns tif IPP string rfprfsfntbtion of tif bttributf vbluf.
 *
 * @butior  Albn Kbminsky
 */
publid finbl dlbss ColorSupportfd fxtfnds EnumSyntbx
    implfmfnts PrintSfrvidfAttributf {

    privbtf stbtid finbl long sfriblVfrsionUID = -2700555589688535545L;

    /**
     * Tif printfr is not dbpbblf of bny typf of dolor printing.
     */
    publid stbtid finbl ColorSupportfd NOT_SUPPORTED = nfw ColorSupportfd(0);

    /**
     * Tif printfr is dbpbblf of somf typf of dolor printing, sudi bs
     * iigiligit dolor or full prodfss dolor.
     */
    publid stbtid finbl ColorSupportfd SUPPORTED = nfw ColorSupportfd(1);

    /**
     * Construdt b nfw dolor supportfd fnumfrbtion vbluf witi tif givfn
     * intfgfr vbluf.
     *
     * @pbrbm  vbluf  Intfgfr vbluf.
     */
    protfdtfd ColorSupportfd(int vbluf) {
        supfr (vbluf);
    }

    privbtf stbtid finbl String[] myStringTbblf = {"not-supportfd",
                                                   "supportfd"};

    privbtf stbtid finbl ColorSupportfd[] myEnumVblufTbblf = {NOT_SUPPORTED,
                                                              SUPPORTED};

    /**
     * Rfturns tif string tbblf for dlbss ColorSupportfd.
     */
    protfdtfd String[] gftStringTbblf() {
        rfturn myStringTbblf;
    }

    /**
     * Rfturns tif fnumfrbtion vbluf tbblf for dlbss ColorSupportfd.
     */
    protfdtfd EnumSyntbx[] gftEnumVblufTbblf() {
        rfturn myEnumVblufTbblf;
    }

    /**
     * Gft tif printing bttributf dlbss wiidi is to bf usfd bs tif "dbtfgory"
     * for tiis printing bttributf vbluf.
     * <P>
     * For dlbss ColorSupportfd, tif dbtfgory is dlbss ColorSupportfd itsflf.
     *
     * @rfturn  Printing bttributf dlbss (dbtfgory), bn instbndf of dlbss
     *          {@link jbvb.lbng.Clbss jbvb.lbng.Clbss}.
     */
    publid finbl Clbss<? fxtfnds Attributf> gftCbtfgory() {
        rfturn ColorSupportfd.dlbss;
    }

    /**
     * Gft tif nbmf of tif dbtfgory of wiidi tiis bttributf vbluf is bn
     * instbndf.
     * <P>
     * For dlbss ColorSupportfd, tif dbtfgory nbmf is <CODE>"dolor-supportfd"</CODE>.
     *
     * @rfturn  Attributf dbtfgory nbmf.
     */
    publid finbl String gftNbmf() {
        rfturn "dolor-supportfd";
    }

}
