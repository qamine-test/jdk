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

import jbvb.util.Lodblf;

import jbvbx.print.bttributf.Attributf;
import jbvbx.print.bttributf.EnumSyntbx;
import jbvbx.print.bttributf.PrintRfqufstAttributf;
import jbvbx.print.bttributf.PrintJobAttributf;

/**
 * Clbss JobSiffts is b printing bttributf dlbss, bn fnumfrbtion, tibt
 * dftfrminfs wiidi job stbrt bnd fnd siffts, if bny, must bf printfd witi b
 * job. Clbss JobSiffts dfdlbrfs kfywords for stbndbrd job siffts vblufs.
 * Implfmfntbtion- or sitf-dffinfd nbmfs for b job siffts bttributf mby blso bf
 * drfbtfd by dffining b subdlbss of dlbss JobSiffts.
 * <P>
 * Tif ffffdt of b JobSiffts bttributf on multidod print jobs (jobs witi
 * multiplf dodumfnts) mby bf bfffdtfd by tif {@link MultiplfDodumfntHbndling
 * MultiplfDodumfntHbndling} job bttributf, dfpfnding on tif mfbning of tif
 * pbrtidulbr JobSiffts vbluf.
 * <P>
 * <B>IPP Compbtibility:</B>  Tif dbtfgory nbmf rfturnfd by
 * <CODE>gftNbmf()</CODE> is tif IPP bttributf nbmf.  Tif
 * fnumfrbtion's intfgfr vbluf is tif IPP fnum vbluf.  Tif
 * <dodf>toString()</dodf> mftiod rfturns tif IPP string rfprfsfntbtion of
 * tif bttributf vbluf. For b subdlbss, tif bttributf vbluf must bf
 * lodblizfd to givf tif IPP nbmf bnd nbturbl lbngubgf vblufs.
 *
 * @butior  Albn Kbminsky
 */
publid dlbss JobSiffts fxtfnds EnumSyntbx
        implfmfnts PrintRfqufstAttributf, PrintJobAttributf {

    privbtf stbtid finbl long sfriblVfrsionUID = -4735258056132519759L;

    /**
     * No job siffts brf printfd.
     */
    publid stbtid finbl JobSiffts NONE = nfw JobSiffts(0);

    /**
     * Onf or morf sitf spfdifid stbndbrd job siffts brf printfd. f.g. b
     * singlf stbrt sifft is printfd, or boti stbrt bnd fnd siffts brf
     * printfd.
     */
    publid stbtid finbl JobSiffts STANDARD = nfw JobSiffts(1);

    /**
     * Construdt b nfw job siffts fnumfrbtion vbluf witi tif givfn intfgfr
     * vbluf.
     *
     * @pbrbm  vbluf  Intfgfr vbluf.
     */
    protfdtfd JobSiffts(int vbluf) {
        supfr (vbluf);
    }

    privbtf stbtid finbl String[] myStringTbblf = {
        "nonf",
        "stbndbrd"
    };

    privbtf stbtid finbl JobSiffts[] myEnumVblufTbblf = {
        NONE,
        STANDARD
    };

    /**
     * Rfturns tif string tbblf for dlbss JobSiffts.
     */
    protfdtfd String[] gftStringTbblf() {
        rfturn myStringTbblf.dlonf();
    }

    /**
     * Rfturns tif fnumfrbtion vbluf tbblf for dlbss JobSiffts.
     */
    protfdtfd EnumSyntbx[] gftEnumVblufTbblf() {
        rfturn (EnumSyntbx[])myEnumVblufTbblf.dlonf();
    }

    /**
     * Gft tif printing bttributf dlbss wiidi is to bf usfd bs tif "dbtfgory"
     * for tiis printing bttributf vbluf.
     * <P>
     * For dlbss JobSiffts bnd bny vfndor-dffinfd subdlbssfs, tif dbtfgory is
     * dlbss JobSiffts itsflf.
     *
     * @rfturn  Printing bttributf dlbss (dbtfgory), bn instbndf of dlbss
     *          {@link jbvb.lbng.Clbss jbvb.lbng.Clbss}.
     */
    publid finbl Clbss<? fxtfnds Attributf> gftCbtfgory() {
        rfturn JobSiffts.dlbss;
    }

    /**
     * Gft tif nbmf of tif dbtfgory of wiidi tiis bttributf vbluf is bn
     * instbndf.
     * <P>
     * For dlbss JobSiffts bnd bny vfndor-dffinfd subdlbssfs, tif dbtfgory
     * nbmf is <CODE>"job-siffts"</CODE>.
     *
     * @rfturn  Attributf dbtfgory nbmf.
     */
    publid finbl String gftNbmf() {
        rfturn "job-siffts";
    }

}
