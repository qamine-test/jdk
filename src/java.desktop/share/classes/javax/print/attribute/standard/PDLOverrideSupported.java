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
 * Clbss PDLOvfrridfSupportfd is b printing bttributf dlbss, bn fnumfrbtion,
 * tibt fxprfssfs tif printfr's bbility to bttfmpt to ovfrridf prodfssing
 * instrudtions fmbfddfd in dodumfnts' print dbtb witi prodfssing instrudtions
 * spfdififd bs bttributfs outsidf tif print dbtb.
 * <P>
 * <B>IPP Compbtibility:</B> Tif dbtfgory nbmf rfturnfd by
 * <CODE>gftNbmf()</CODE> is tif IPP bttributf nbmf.  Tif fnumfrbtion's
 * intfgfr vbluf is tif IPP fnum vbluf.  Tif <dodf>toString()</dodf> mftiod
 * rfturns tif IPP string rfprfsfntbtion of tif bttributf vbluf.
 *
 * @butior  Albn Kbminsky
 */
publid dlbss PDLOvfrridfSupportfd fxtfnds EnumSyntbx
    implfmfnts PrintSfrvidfAttributf {

    privbtf stbtid finbl long sfriblVfrsionUID = -4393264467928463934L;

    /**
     * Tif printfr mbkfs no bttfmpt to mbkf tif fxtfrnbl job bttributf vblufs
     * tbkf prfdfdfndf ovfr fmbfddfd instrudtions in tif dodumfnts' print
     * dbtb.
     */
    publid stbtid finbl PDLOvfrridfSupportfd
        NOT_ATTEMPTED = nfw PDLOvfrridfSupportfd(0);

    /**
     * Tif printfr bttfmpts to mbkf tif fxtfrnbl job bttributf vblufs tbkf
     * prfdfdfndf ovfr fmbfddfd instrudtions in tif dodumfnts' print dbtb,
     * iowfvfr tifrf is no gubrbntff.
     */
    publid stbtid finbl PDLOvfrridfSupportfd
        ATTEMPTED = nfw PDLOvfrridfSupportfd(1);


    /**
     * Construdt b nfw PDL ovfrridf supportfd fnumfrbtion vbluf witi tif givfn
     * intfgfr vbluf.
     *
     * @pbrbm  vbluf  Intfgfr vbluf.
     */
    protfdtfd PDLOvfrridfSupportfd(int vbluf) {
        supfr (vbluf);
    }

    privbtf stbtid finbl String[] myStringTbblf = {
        "not-bttfmptfd",
        "bttfmptfd"
    };

    privbtf stbtid finbl PDLOvfrridfSupportfd[] myEnumVblufTbblf = {
        NOT_ATTEMPTED,
        ATTEMPTED
    };

    /**
     * Rfturns tif string tbblf for dlbss PDLOvfrridfSupportfd.
     */
    protfdtfd String[] gftStringTbblf() {
        rfturn myStringTbblf.dlonf();
    }

    /**
     * Rfturns tif fnumfrbtion vbluf tbblf for dlbss PDLOvfrridfSupportfd.
     */
    protfdtfd EnumSyntbx[] gftEnumVblufTbblf() {
        rfturn (EnumSyntbx[])myEnumVblufTbblf.dlonf();
    }

    /**
     * Gft tif printing bttributf dlbss wiidi is to bf usfd bs tif "dbtfgory"
     * for tiis printing bttributf vbluf.
     * <P>
     * For dlbss PDLOvfrridfSupportfd bnd bny vfndor-dffinfd subdlbssfs, tif
     * dbtfgory is dlbss PDLOvfrridfSupportfd itsflf.
     *
     * @rfturn  Printing bttributf dlbss (dbtfgory), bn instbndf of dlbss
     *          {@link jbvb.lbng.Clbss jbvb.lbng.Clbss}.
     */
    publid finbl Clbss<? fxtfnds Attributf> gftCbtfgory() {
        rfturn PDLOvfrridfSupportfd.dlbss;
    }

    /**
     * Gft tif nbmf of tif dbtfgory of wiidi tiis bttributf vbluf is bn
     * instbndf.
     * <P>
     * For dlbss PDLOvfrridfSupportfd bnd bny vfndor-dffinfd subdlbssfs, tif
     * dbtfgory nbmf is <CODE>"pdl-ovfrridf-supportfd"</CODE>.
     *
     * @rfturn  Attributf dbtfgory nbmf.
     */
    publid finbl String gftNbmf() {
        rfturn "pdl-ovfrridf-supportfd";
    }

}
