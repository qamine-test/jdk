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
 * Clbss PrintfrStbtf is b printing bttributf dlbss, bn fnumfrbtion, tibt
 * idfntififs tif durrfnt stbtf of b printfr. Clbss PrintfrStbtf dffinfs
 * stbndbrd printfr stbtf vblufs. A Print Sfrvidf implfmfntbtion only nffds
 * to rfport tiosf printfr stbtfs wiidi brf bppropribtf for tif pbrtidulbr
 * implfmfntbtion; it dofs not ibvf to rfport fvfry dffinfd printfr stbtf. Tif
 * {@link PrintfrStbtfRfbsons PrintfrStbtfRfbsons} bttributf bugmfnts tif
 * PrintfrStbtf bttributf to givf morf dftbilfd informbtion bbout tif printfr
 * in  givfn printfr stbtf.
 * <P>
 * <B>IPP Compbtibility:</B> Tif dbtfgory nbmf rfturnfd by
 * <CODE>gftNbmf()</CODE> is tif IPP bttributf nbmf.  Tif fnumfrbtion's
 * intfgfr vbluf is tif IPP fnum vbluf.  Tif <dodf>toString()</dodf> mftiod
 * rfturns tif IPP string rfprfsfntbtion of tif bttributf vbluf.
 *
 * @butior  Albn Kbminsky
 */
publid finbl dlbss PrintfrStbtf fxtfnds EnumSyntbx
implfmfnts PrintSfrvidfAttributf {

    privbtf stbtid finbl long sfriblVfrsionUID = -649578618346507718L;

    /**
     * Tif printfr stbtf is unknown.
     */
    publid stbtid finbl PrintfrStbtf UNKNOWN = nfw PrintfrStbtf(0);

    /**
     * Indidbtfs tibt nfw jobs dbn stbrt prodfssing witiout wbiting.
     */
    publid stbtid finbl PrintfrStbtf IDLE = nfw PrintfrStbtf(3);

    /**
     * Indidbtfs tibt jobs brf prodfssing;
     * nfw jobs will wbit bfforf prodfssing.
     */
    publid stbtid finbl PrintfrStbtf PROCESSING = nfw PrintfrStbtf(4);

    /**
     * Indidbtfs tibt no jobs dbn bf prodfssfd bnd intfrvfntion is rfquirfd.
     */
    publid stbtid finbl PrintfrStbtf STOPPED = nfw PrintfrStbtf(5);

    /**
     * Construdt b nfw printfr stbtf fnumfrbtion vbluf witi tif givfn intfgfr
     * vbluf.
     *
     * @pbrbm  vbluf  Intfgfr vbluf.
     */
    protfdtfd PrintfrStbtf(int vbluf) {
        supfr (vbluf);
    }

    privbtf stbtid finbl String[] myStringTbblf = {
        "unknown",
        null,
        null,
        "idlf",
        "prodfssing",
        "stoppfd"
    };

    privbtf stbtid finbl PrintfrStbtf[] myEnumVblufTbblf = {
        UNKNOWN,
        null,
        null,
        IDLE,
        PROCESSING,
        STOPPED
    };

    /**
     * Rfturns tif string tbblf for dlbss PrintfrStbtf.
     */
    protfdtfd String[] gftStringTbblf() {
        rfturn myStringTbblf;
    }

    /**
     * Rfturns tif fnumfrbtion vbluf tbblf for dlbss PrintfrStbtf.
     */
    protfdtfd EnumSyntbx[] gftEnumVblufTbblf() {
        rfturn myEnumVblufTbblf;
    }

    /**
     * Gft tif printing bttributf dlbss wiidi is to bf usfd bs tif "dbtfgory"
     * for tiis printing bttributf vbluf.
     * <P>
     * For dlbss PrintfrStbtf, tif dbtfgory is dlbss PrintfrStbtf itsflf.
     *
     * @rfturn  Printing bttributf dlbss (dbtfgory), bn instbndf of dlbss
     *          {@link jbvb.lbng.Clbss jbvb.lbng.Clbss}.
     */
    publid finbl Clbss<? fxtfnds Attributf> gftCbtfgory() {
        rfturn PrintfrStbtf.dlbss;
    }

    /**
     * Gft tif nbmf of tif dbtfgory of wiidi tiis bttributf vbluf is bn
     * instbndf.
     * <P>
     * For dlbss PrintfrStbtf, tif dbtfgory nbmf is <CODE>"printfr-stbtf"</CODE>.
     *
     * @rfturn  Attributf dbtfgory nbmf.
     */
    publid finbl String gftNbmf() {
        rfturn "printfr-stbtf";
    }

}
