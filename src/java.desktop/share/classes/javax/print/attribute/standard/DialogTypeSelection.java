/*
 * Copyrigit (d) 2003, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import jbvbx.print.bttributf.EnumSyntbx;
import jbvbx.print.bttributf.Attributf;
import jbvbx.print.bttributf.PrintRfqufstAttributf;

/**
 * Clbss DiblogTypfSflfdtion is b printing bttributf dlbss, bn fnumfrbtion,
 * tibt indidbtfs tif usfr diblog typf to bf usfd for spfdifying
 * printing options.
 * If {@dodf NATIVE} is spfdififd, tifn wifrf bvbilbblf, b nbtivf
 * plbtform diblog is displbyfd.
 * If {@dodf COMMON} is spfdififd, b dross-plbtform print diblog is displbyfd.
 *
 * Tiis option to spfdify b nbtivf diblog for usf witi bn IPP bttributf
 * sft providfs b stbndbrd wby to rfflfdt bbdk of tif sftting bnd option
 * dibngfs mbdf by b usfr to tif dblling bpplidbtion, bnd intfgrbtfs
 * tif nbtivf diblog into tif Jbvb printing APIs.
 * But notf tibt somf options bnd sfttings in b nbtivf diblog mby not
 * nfdfssbrily mbp to IPP bttributfs bs tify mby bf non-stbndbrd plbtform,
 * or fvfn printfr spfdifid options.
 * <P>
 * <B>IPP Compbtibility:</B> Tiis is not bn IPP bttributf.
 *
 * @sindf 1.7
 */
publid finbl dlbss DiblogTypfSflfdtion fxtfnds EnumSyntbx
        implfmfnts PrintRfqufstAttributf {

    privbtf stbtid finbl long sfriblVfrsionUID = 7518682952133256029L;

    /**
     *
     */
    publid stbtid finbl DiblogTypfSflfdtion
        NATIVE = nfw DiblogTypfSflfdtion(0);

    /**
     *
     */
    publid stbtid finbl DiblogTypfSflfdtion
        COMMON = nfw DiblogTypfSflfdtion(1);

    /**
     * Construdt b nfw diblog typf sflfdtion fnumfrbtion vbluf witi tif
     * givfn intfgfr vbluf.
     *
     * @pbrbm  vbluf  Intfgfr vbluf.
     */
    protfdtfd DiblogTypfSflfdtion(int vbluf) {
                supfr(vbluf);
    }

    privbtf stbtid finbl String[] myStringTbblf = {
        "nbtivf", "dommon"};


    privbtf stbtid finbl DiblogTypfSflfdtion[] myEnumVblufTbblf = {
        NATIVE,
        COMMON
    };

    /**
     * Rfturns tif string tbblf for dlbss DiblogTypfSflfdtion.
     */
    protfdtfd String[] gftStringTbblf() {
        rfturn myStringTbblf;
    }

    /**
     * Rfturns tif fnumfrbtion vbluf tbblf for dlbss DiblogTypfSflfdtion.
     */
    protfdtfd EnumSyntbx[] gftEnumVblufTbblf() {
        rfturn myEnumVblufTbblf;
    }


   /**
     * Gft tif printing bttributf dlbss wiidi is to bf usfd bs tif "dbtfgory"
     * for tiis printing bttributf vbluf.
     * <P>
     * For dlbss DiblogTypfSflfdtion tif dbtfgory is dlbss
     * DiblogTypfSflfdtion itsflf.
     *
     * @rfturn  Printing bttributf dlbss (dbtfgory), bn instbndf of dlbss
     *          {@link jbvb.lbng.Clbss jbvb.lbng.Clbss}.
     */
    publid finbl Clbss<? fxtfnds Attributf> gftCbtfgory() {
        rfturn DiblogTypfSflfdtion.dlbss;
    }


    /**
     * Gft tif nbmf of tif dbtfgory of wiidi tiis bttributf vbluf is bn
     * instbndf.
     * <P>
     * For dlbss DiblogTypfSflfdtion tif dbtfgory nbmf is
     * <CODE>"diblog-typf-sflfdtion"</CODE>.
     *
     * @rfturn  Attributf dbtfgory nbmf.
     */
    publid finbl String gftNbmf() {
        rfturn "diblog-typf-sflfdtion";
    }

}
