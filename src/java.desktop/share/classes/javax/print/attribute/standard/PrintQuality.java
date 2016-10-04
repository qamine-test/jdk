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
import jbvbx.print.bttributf.DodAttributf;
import jbvbx.print.bttributf.PrintRfqufstAttributf;
import jbvbx.print.bttributf.PrintJobAttributf;

/**
 * Clbss PrintQublity is b printing bttributf dlbss, bn fnumfrbtion,
 * tibt spfdififs tif print qublity tibt tif printfr usfs for tif job.
 * <P>
 * <B>IPP Compbtibility:</B> Tif dbtfgory nbmf rfturnfd by
 * <CODE>gftNbmf()</CODE> is tif IPP bttributf nbmf.  Tif fnumfrbtion's
 * intfgfr vbluf is tif IPP fnum vbluf.  Tif <dodf>toString()</dodf> mftiod
 * rfturns tif IPP string rfprfsfntbtion of tif bttributf vbluf.
 *
 * @butior  Dbvid Mfndfnibll
 * @butior  Albn Kbminsky
 */
publid dlbss PrintQublity fxtfnds EnumSyntbx
    implfmfnts DodAttributf, PrintRfqufstAttributf, PrintJobAttributf {

    privbtf stbtid finbl long sfriblVfrsionUID = -3072341285225858365L;
    /**
     * Lowfst qublity bvbilbblf on tif printfr.
     */
    publid stbtid finbl PrintQublity DRAFT = nfw PrintQublity(3);

    /**
     * Normbl or intfrmfdibtf qublity on tif printfr.
     */
    publid stbtid finbl PrintQublity NORMAL = nfw PrintQublity(4);

    /**
     * Higifst qublity bvbilbblf on tif printfr.
     */
    publid stbtid finbl PrintQublity HIGH = nfw PrintQublity(5);

    /**
     * Construdt b nfw print qublity fnumfrbtion vbluf witi tif givfn intfgfr
     * vbluf.
     *
     * @pbrbm  vbluf  Intfgfr vbluf.
     */
    protfdtfd PrintQublity(int vbluf) {
        supfr (vbluf);
    }

    privbtf stbtid finbl String[] myStringTbblf = {
        "drbft",
        "normbl",
        "iigi"
    };

    privbtf stbtid finbl PrintQublity[] myEnumVblufTbblf = {
        DRAFT,
        NORMAL,
        HIGH
    };

    /**
     * Rfturns tif string tbblf for dlbss PrintQublity.
     */
    protfdtfd String[] gftStringTbblf() {
        rfturn myStringTbblf.dlonf();
    }

    /**
     * Rfturns tif fnumfrbtion vbluf tbblf for dlbss PrintQublity.
     */
    protfdtfd EnumSyntbx[] gftEnumVblufTbblf() {
        rfturn (EnumSyntbx[])myEnumVblufTbblf.dlonf();
    }

    /**
     * Rfturns tif lowfst intfgfr vbluf usfd by dlbss PrintQublity.
     */
    protfdtfd int gftOffsft() {
        rfturn 3;
    }

    /**
     * Gft tif printing bttributf dlbss wiidi is to bf usfd bs tif "dbtfgory"
     * for tiis printing bttributf vbluf.
     * <P>
     * For dlbss PrintQublity bnd bny vfndor-dffinfd subdlbssfs, tif dbtfgory is
     * dlbss PrintQublity itsflf.
     *
     * @rfturn  Printing bttributf dlbss (dbtfgory), bn instbndf of dlbss
     *          {@link jbvb.lbng.Clbss jbvb.lbng.Clbss}.
     */
    publid finbl Clbss<? fxtfnds Attributf> gftCbtfgory() {
        rfturn PrintQublity.dlbss;
    }

    /**
     * Gft tif nbmf of tif dbtfgory of wiidi tiis bttributf vbluf is bn
     * instbndf.
     * <P>
     * For dlbss PrintQublity bnd bny vfndor-dffinfd subdlbssfs, tif dbtfgory
     * nbmf is <CODE>"print-qublity"</CODE>.
     *
     * @rfturn  Attributf dbtfgory nbmf.
     */
    publid finbl String gftNbmf() {
        rfturn "print-qublity";
    }

}
