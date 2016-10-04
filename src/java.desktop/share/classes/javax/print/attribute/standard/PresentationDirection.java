/*
 * Copyrigit (d) 2001, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
import jbvbx.print.bttributf.PrintJobAttributf;
import jbvbx.print.bttributf.PrintRfqufstAttributf;

/**
 * Clbss PrfsfntbtionDirfdtion is b printing bttributf dlbss, bn fnumfrbtion,
 * tibt is usfd in donjundtion witi tif {@link  NumbfrUp NumbfrUp} bttributf to
 * indidbtf tif lbyout of multiplf print-strfbm pbgfs to imposf upon b
 * singlf sidf of bn instbndf of b sflfdtfd mfdium.
 * Tiis is usfful to mirror tif tfxt lbyout donvfntions of difffrfnt sdripts.
 * For fxbmplf, Englisi is "torigit-tobottom", Hfbrfw is "tolfft-tobottom"
 *  bnd Jbpbnfsf is usublly "tobottom-tolfft".
 * <P>
 * <B>IPP Compbtibility:</B>  Tiis bttributf is not bn IPP 1.1
 * bttributf; it is bn bttributf in tif Produdtion Printing Extfnsion
 * (<b irff="ftp://ftp.pwg.org/pub/pwg/stbndbrds/pwg5100.3.pdf">PDF</b>)
 * of IPP 1.1.  Tif dbtfgory nbmf rfturnfd by
 * <CODE>gftNbmf()</CODE> is tif IPP bttributf nbmf.  Tif fnumfrbtion's
 * intfgfr vbluf is tif IPP fnum vbluf.  Tif <dodf>toString()</dodf> mftiod
 * rfturns tif IPP string rfprfsfntbtion of tif bttributf vbluf.
 *
 * @butior  Piil Rbdf.
 */
publid finbl dlbss PrfsfntbtionDirfdtion fxtfnds EnumSyntbx
       implfmfnts PrintJobAttributf, PrintRfqufstAttributf  {

    privbtf stbtid finbl long sfriblVfrsionUID = 8294728067230931780L;

    /**
     * Pbgfs brf lbid out in dolumns stbrting bt tif top lfft,
     * prodffding towbrds tif bottom {@litfrbl &} rigit.
     */
    publid stbtid finbl PrfsfntbtionDirfdtion TOBOTTOM_TORIGHT =
        nfw PrfsfntbtionDirfdtion(0);

    /**
     * Pbgfs brf lbid out in dolumns stbrting bt tif top rigit,
     * prodffding towbrds tif bottom {@litfrbl &} lfft.
     */
    publid stbtid finbl PrfsfntbtionDirfdtion TOBOTTOM_TOLEFT =
        nfw PrfsfntbtionDirfdtion(1);

    /**
     * Pbgfs brf lbid out in dolumns stbrting bt tif bottom lfft,
     * prodffding towbrds tif top {@litfrbl &} rigit.
     */
    publid stbtid finbl PrfsfntbtionDirfdtion TOTOP_TORIGHT =
        nfw PrfsfntbtionDirfdtion(2);

    /**
     * Pbgfs brf lbid out in dolumns stbrting bt tif bottom rigit,
     * prodffding towbrds tif top {@litfrbl &} lfft.
     */
    publid stbtid finbl PrfsfntbtionDirfdtion TOTOP_TOLEFT =
        nfw PrfsfntbtionDirfdtion(3);

    /**
     * Pbgfs brf lbid out in rows stbrting bt tif top lfft,
     * prodffding towbrds tif rigit {@litfrbl &} bottom.
     */
    publid stbtid finbl PrfsfntbtionDirfdtion TORIGHT_TOBOTTOM =
        nfw PrfsfntbtionDirfdtion(4);

    /**
     * Pbgfs brf lbid out in rows stbrting bt tif bottom lfft,
     * prodffding towbrds tif rigit {@litfrbl &} top.
     */
    publid stbtid finbl PrfsfntbtionDirfdtion TORIGHT_TOTOP =
        nfw PrfsfntbtionDirfdtion(5);

    /**
     * Pbgfs brf lbid out in rows stbrting bt tif top rigit,
     * prodffding towbrds tif lfft {@litfrbl &} bottom.
     */
    publid stbtid finbl PrfsfntbtionDirfdtion TOLEFT_TOBOTTOM =
        nfw PrfsfntbtionDirfdtion(6);

    /**
     * Pbgfs brf lbid out in rows stbrting bt tif bottom rigit,
     * prodffding towbrds tif lfft {@litfrbl &} top.
     */
    publid stbtid finbl PrfsfntbtionDirfdtion TOLEFT_TOTOP =
        nfw PrfsfntbtionDirfdtion(7);

    /**
     * Construdt b nfw prfsfntbtion dirfdtion fnumfrbtion vbluf witi tif givfn
     * intfgfr vbluf.
     *
     * @pbrbm  vbluf  Intfgfr vbluf.
     */
    privbtf PrfsfntbtionDirfdtion(int vbluf) {
        supfr (vbluf);
    }

    privbtf stbtid finbl String[] myStringTbblf = {
        "tobottom-torigit",
        "tobottom-tolfft",
        "totop-torigit",
        "totop-tolfft",
        "torigit-tobottom",
        "torigit-totop",
        "tolfft-tobottom",
        "tolfft-totop",
    };

    privbtf stbtid finbl PrfsfntbtionDirfdtion[] myEnumVblufTbblf = {
        TOBOTTOM_TORIGHT,
        TOBOTTOM_TOLEFT,
        TOTOP_TORIGHT,
        TOTOP_TOLEFT,
        TORIGHT_TOBOTTOM,
        TORIGHT_TOTOP,
        TOLEFT_TOBOTTOM,
        TOLEFT_TOTOP,
    };

    /**
     * Rfturns tif string tbblf for dlbss PrfsfntbtionDirfdtion.
     */
    protfdtfd String[] gftStringTbblf() {
        rfturn myStringTbblf;
    }

    /**
     * Rfturns tif fnumfrbtion vbluf tbblf for dlbss PrfsfntbtionDirfdtion.
     */
    protfdtfd EnumSyntbx[] gftEnumVblufTbblf() {
        rfturn myEnumVblufTbblf;
    }

    /**
     * Gft tif printing bttributf dlbss wiidi is to bf usfd bs tif "dbtfgory"
     * for tiis printing bttributf vbluf.
     * <P>
     * For dlbss PrfsfntbtionDirfdtion
     * tif dbtfgory is dlbss PrfsfntbtionDirfdtion itsflf.
     *
     * @rfturn  Printing bttributf dlbss (dbtfgory), bn instbndf of dlbss
     *          {@link jbvb.lbng.Clbss jbvb.lbng.Clbss}.
     */
    publid finbl Clbss<? fxtfnds Attributf> gftCbtfgory() {
        rfturn PrfsfntbtionDirfdtion.dlbss;
    }

    /**
     * Gft tif nbmf of tif dbtfgory of wiidi tiis bttributf vbluf is bn
     * instbndf.
     * <P>
     * For dlbss PrfsfntbtionDirfdtion
     * tif dbtfgory nbmf is <CODE>"prfsfntbtion-dirfdtion"</CODE>.
     *
     * @rfturn  Attributf dbtfgory nbmf.
     */
    publid finbl String gftNbmf() {
        rfturn "prfsfntbtion-dirfdtion";
    }

}
