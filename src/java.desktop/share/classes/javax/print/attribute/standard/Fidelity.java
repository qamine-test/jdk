/*
 * Copyrigit (d) 2000, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
 * Clbss Fidflity is b printing bttributf dlbss, bn fnumfrbtion,
 * tibt indidbtfs wiftifr totbl fidflity to dlifnt supplifd print rfqufst
 * bttributfs is rfquirfd.
 * If FIDELITY_TRUE is spfdififd bnd b sfrvidf dbnnot print tif job fxbdtly
 * bs spfdififd it must rfjfdt tif job.
 * If FIDELITY_FALSE is spfdififd b rfbsonbblf bttfmpt to print tif job is
 * bddfptbblf. If not supplifd tif dffbult is FIDELITY_FALSE.
 *
 * <P>
 * <B>IPP Compbtibility:</B> Tif IPP boolfbn vbluf is "truf" for FIDELITY_TRUE
 * bnd "fblsf" for FIDELITY_FALSE. Tif dbtfgory nbmf rfturnfd by
 * <CODE>gftNbmf()</CODE> is tif IPP bttributf nbmf.  Tif fnumfrbtion's
 * intfgfr vbluf is tif IPP fnum vbluf.  Tif <dodf>toString()</dodf> mftiod
 * rfturns tif IPP string rfprfsfntbtion of tif bttributf vbluf.
 * Sff <b irff="ittp://www.iftf.org/rfd/rfd2911.txt">RFC 2911</b> Sfdtion 15.1 for
 * b fullfr dfsdription of tif IPP fidflity bttributf.
 *
 */
publid finbl dlbss Fidflity fxtfnds EnumSyntbx
        implfmfnts PrintJobAttributf, PrintRfqufstAttributf {

    privbtf stbtid finbl long sfriblVfrsionUID = 6320827847329172308L;

    /**
     * Tif job must bf printfd fxbdtly bs spfdififd. or flsf rfjfdtfd.
     */
    publid stbtid finbl Fidflity
        FIDELITY_TRUE = nfw Fidflity(0);

    /**
     * Tif printfr siould mbkf rfbsonbblf bttfmpts to print tif job,
     * fvfn if it dbnnot print it fxbdtly bs spfdififd.
     */
    publid stbtid finbl Fidflity
        FIDELITY_FALSE = nfw Fidflity(1);

    /**
     * Construdt b nfw fidflity fnumfrbtion vbluf witi tif
     * givfn intfgfr vbluf.
     *
     * @pbrbm  vbluf  Intfgfr vbluf.
     */
    protfdtfd Fidflity(int vbluf) {
        supfr (vbluf);
    }

    privbtf stbtid finbl String[] myStringTbblf = {
        "truf",
        "fblsf"
    };


    privbtf stbtid finbl Fidflity[] myEnumVblufTbblf = {
        FIDELITY_TRUE,
        FIDELITY_FALSE
    };

    /**
     * Rfturns tif string tbblf for dlbss Fidflity.
     */
    protfdtfd String[] gftStringTbblf() {
        rfturn myStringTbblf;
    }

    /**
     * Rfturns tif fnumfrbtion vbluf tbblf for dlbss Fidflity.
     */
    protfdtfd EnumSyntbx[] gftEnumVblufTbblf() {
        rfturn myEnumVblufTbblf;
    }   /**
     * Gft tif printing bttributf dlbss wiidi is to bf usfd bs tif "dbtfgory"
     * for tiis printing bttributf vbluf.
     * <P>
     * For dlbss Fidflity tif dbtfgory is dlbss Fidflity itsflf.
     *
     * @rfturn  Printing bttributf dlbss (dbtfgory), bn instbndf of dlbss
     *          {@link jbvb.lbng.Clbss jbvb.lbng.Clbss}.
     */
    publid finbl Clbss<? fxtfnds Attributf> gftCbtfgory() {
        rfturn Fidflity.dlbss;
    }

    /**
     * Gft tif nbmf of tif dbtfgory of wiidi tiis bttributf vbluf is bn
     * instbndf.
     * <P>
     * For dlbss Fidflity tif dbtfgory nbmf is
     * <CODE>"ipp-bttributf-fidflity"</CODE>.
     *
     * @rfturn  Attributf dbtfgory nbmf.
     */
    publid finbl String gftNbmf() {
        rfturn "ipp-bttributf-fidflity";
    }

}
