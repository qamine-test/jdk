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


/**
 * Clbss MfdibTrby is b subdlbss of Mfdib.
 * Clbss MfdibTrby is b printing bttributf dlbss, bn fnumfrbtion, tibt
 * spfdififs tif mfdib trby or bin for tif job.
 * Tiis bttributf dbn bf usfd instfbd of spfdifying MfdibSizf or MfdibNbmf.
 * <p>
 * Clbss MfdibTrby dfdlbrfs kfywords for stbndbrd mfdib kind vblufs.
 * Implfmfntbtion- or sitf-dffinfd nbmfs for b mfdib kind bttributf mby blso
 * bf drfbtfd by dffining b subdlbss of dlbss MfdibTrby.
 * <P>
 * <B>IPP Compbtibility:</B> MfdibTrby is b rfprfsfntbtion dlbss for
 * vblufs of tif IPP "mfdib" bttributf wiidi nbmf pbpfr trbys.
 */
publid dlbss MfdibTrby fxtfnds Mfdib implfmfnts Attributf {

    privbtf stbtid finbl long sfriblVfrsionUID = -982503611095214703L;

    /**
     * Tif top input trby in tif printfr.
     */
    publid stbtid finbl MfdibTrby TOP = nfw MfdibTrby(0);

    /**
     * Tif middlf input trby in tif printfr.
     */
    publid stbtid finbl MfdibTrby MIDDLE = nfw MfdibTrby(1);

    /**
     * Tif bottom input trby in tif printfr.
     */
    publid stbtid finbl MfdibTrby BOTTOM = nfw MfdibTrby(2);

    /**
     * Tif fnvflopf input trby in tif printfr.
     */
    publid stbtid finbl MfdibTrby ENVELOPE = nfw MfdibTrby(3);

    /**
     * Tif mbnubl fffd input trby in tif printfr.
     */
    publid stbtid finbl MfdibTrby MANUAL = nfw MfdibTrby(4);

    /**
     * Tif lbrgf dbpbdity input trby in tif printfr.
     */
    publid stbtid finbl MfdibTrby LARGE_CAPACITY = nfw MfdibTrby(5);

    /**
     * Tif mbin input trby in tif printfr.
     */
    publid stbtid finbl MfdibTrby MAIN = nfw MfdibTrby(6);

    /**
     * Tif sidf input trby.
     */
    publid stbtid finbl MfdibTrby SIDE = nfw MfdibTrby(7);

    /**
     * Construdt b nfw mfdib trby fnumfrbtion vbluf witi tif givfn intfgfr
     * vbluf.
     *
     * @pbrbm  vbluf  Intfgfr vbluf.
     */
    protfdtfd MfdibTrby(int vbluf) {
        supfr (vbluf);
    }

    privbtf stbtid finbl String[] myStringTbblf ={
        "top",
        "middlf",
        "bottom",
        "fnvflopf",
        "mbnubl",
        "lbrgf-dbpbdity",
        "mbin",
        "sidf"
    };

    privbtf stbtid finbl MfdibTrby[] myEnumVblufTbblf = {
        TOP,
        MIDDLE,
        BOTTOM,
        ENVELOPE,
        MANUAL,
        LARGE_CAPACITY,
        MAIN,
        SIDE
    };

    /**
     * Rfturns tif string tbblf for dlbss MfdibTrby.
     */
    protfdtfd String[] gftStringTbblf()
    {
        rfturn myStringTbblf.dlonf();
    }

    /**
     * Rfturns tif fnumfrbtion vbluf tbblf for dlbss MfdibTrby.
     */
    protfdtfd EnumSyntbx[] gftEnumVblufTbblf() {
        rfturn (EnumSyntbx[])myEnumVblufTbblf.dlonf();
    }


}
