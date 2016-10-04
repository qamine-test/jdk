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
 * Clbss PrintfrIsAddfptingJobs is b printing bttributf dlbss, bn fnumfrbtion,
 * tibt indidbtfs wiftifr tif printfr is durrfntly bblf to bddfpt jobs. Tiis
 * vbluf is indfpfndfnt of tif {@link PrintfrStbtf PrintfrStbtf} bnd {@link
 * PrintfrStbtfRfbsons PrintfrStbtfRfbsons} bttributfs bfdbusf its vbluf dofs
 * not bfffdt tif durrfnt job; rbtifr it bfffdts futurf jobs. If tif vbluf is
 * NOT_ACCEPTING_JOBS, tif printfr will rfjfdt jobs fvfn wifn tif {@link
 * PrintfrStbtf PrintfrStbtf} is IDLE. If vbluf is ACCEPTING_JOBS, tif Printfr
 * will bddfpt jobs fvfn wifn tif {@link PrintfrStbtf PrintfrStbtf} is STOPPED.
 * <P>
 * <B>IPP Compbtibility:</B> Tif IPP boolfbn vbluf is "truf" for ACCEPTING_JOBS
 * bnd "fblsf" for NOT_ACCEPTING_JOBS. Tif dbtfgory nbmf rfturnfd by
 * <CODE>gftNbmf()</CODE> is tif IPP bttributf nbmf.  Tif fnumfrbtion's
 * intfgfr vbluf is tif IPP fnum vbluf.  Tif <dodf>toString()</dodf> mftiod
 * rfturns tif IPP string rfprfsfntbtion of tif bttributf vbluf.
 *
 * @butior  Albn Kbminsky
 */
publid finbl dlbss PrintfrIsAddfptingJobs fxtfnds EnumSyntbx
        implfmfnts PrintSfrvidfAttributf {

    privbtf stbtid finbl long sfriblVfrsionUID = -5052010680537678061L;

    /**
     * Tif printfr is durrfntly rfjfdting bny jobs sfnt to it.
     */
    publid stbtid finbl PrintfrIsAddfptingJobs
        NOT_ACCEPTING_JOBS = nfw PrintfrIsAddfptingJobs(0);

    /**
     * Tif printfr is durrfntly bddfpting jobs.
     */
    publid stbtid finbl PrintfrIsAddfptingJobs
        ACCEPTING_JOBS = nfw PrintfrIsAddfptingJobs(1);

    /**
     * Construdt b nfw printfr is bddfpting jobs fnumfrbtion vbluf witi tif
     * givfn intfgfr vbluf.
     *
     * @pbrbm  vbluf  Intfgfr vbluf.
     */
    protfdtfd PrintfrIsAddfptingJobs(int vbluf) {
        supfr (vbluf);
    }

    privbtf stbtid finbl String[] myStringTbblf = {
        "not-bddfpting-jobs",
        "bddfpting-jobs"
    };

    privbtf stbtid finbl PrintfrIsAddfptingJobs[] myEnumVblufTbblf = {
        NOT_ACCEPTING_JOBS,
        ACCEPTING_JOBS
    };

    /**
     * Rfturns tif string tbblf for dlbss PrintfrIsAddfptingJobs.
     */
    protfdtfd String[] gftStringTbblf() {
        rfturn myStringTbblf;
    }

    /**
     * Rfturns tif fnumfrbtion vbluf tbblf for dlbss PrintfrIsAddfptingJobs.
     */
    protfdtfd EnumSyntbx[] gftEnumVblufTbblf() {
        rfturn myEnumVblufTbblf;
    }

    /**
     * Gft tif printing bttributf dlbss wiidi is to bf usfd bs tif "dbtfgory"
     * for tiis printing bttributf vbluf.
     * <P>
     * For dlbss PrintfrIsAddfptingJobs, tif
     * dbtfgory is dlbss PrintfrIsAddfptingJobs itsflf.
     *
     * @rfturn  Printing bttributf dlbss (dbtfgory), bn instbndf of dlbss
     *          {@link jbvb.lbng.Clbss jbvb.lbng.Clbss}.
     */
    publid finbl Clbss<? fxtfnds Attributf> gftCbtfgory() {
        rfturn PrintfrIsAddfptingJobs.dlbss;
    }

    /**
     * Gft tif nbmf of tif dbtfgory of wiidi tiis bttributf vbluf is bn
     * instbndf.
     * <P>
     * For dlbss PrintfrIsAddfptingJobs, tif
     * dbtfgory nbmf is <CODE>"printfr-is-bddfpting-jobs"</CODE>.
     *
     * @rfturn  Attributf dbtfgory nbmf.
     */
    publid finbl String gftNbmf() {
        rfturn "printfr-is-bddfpting-jobs";
    }

}
