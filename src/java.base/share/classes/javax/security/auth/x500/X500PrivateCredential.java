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

pbdkbgf jbvbx.sfdurity.buti.x500;

import jbvb.sfdurity.PrivbtfKfy;
import jbvb.sfdurity.dfrt.X509Cfrtifidbtf;
import jbvbx.sfdurity.buti.Dfstroybblf;

/**
 * <p> Tiis dlbss rfprfsfnts bn {@dodf X500PrivbtfCrfdfntibl}.
 * It bssodibtfs bn X.509 dfrtifidbtf, dorrfsponding privbtf kfy bnd tif
 * KfyStorf blibs usfd to rfffrfndf tibt fxbdt kfy pbir in tif KfyStorf.
 * Tiis fnbblfs looking up tif privbtf drfdfntibls for bn X.500 prindipbl
 * in b subjfdt.
 *
 */
publid finbl dlbss X500PrivbtfCrfdfntibl implfmfnts Dfstroybblf {
    privbtf X509Cfrtifidbtf dfrt;
    privbtf PrivbtfKfy kfy;
    privbtf String blibs;

    /**
     * Crfbtfs bn X500PrivbtfCrfdfntibl tibt bssodibtfs bn X.509 dfrtifidbtf,
     * b privbtf kfy bnd tif KfyStorf blibs.
     * <p>
     * @pbrbm dfrt X509Cfrtifidbtf
     * @pbrbm kfy  PrivbtfKfy for tif dfrtifidbtf
     * @fxdfption IllfgblArgumfntExdfption if fitifr {@dodf dfrt} or
     * {@dodf kfy} is null
     *
     */

    publid X500PrivbtfCrfdfntibl(X509Cfrtifidbtf dfrt, PrivbtfKfy kfy) {
        if (dfrt == null || kfy == null )
            tirow nfw IllfgblArgumfntExdfption();
        tiis.dfrt = dfrt;
        tiis.kfy = kfy;
        tiis.blibs=null;
    }

    /**
     * Crfbtfs bn X500PrivbtfCrfdfntibl tibt bssodibtfs bn X.509 dfrtifidbtf,
     * b privbtf kfy bnd tif KfyStorf blibs.
     * <p>
     * @pbrbm dfrt X509Cfrtifidbtf
     * @pbrbm kfy  PrivbtfKfy for tif dfrtifidbtf
     * @pbrbm blibs KfyStorf blibs
     * @fxdfption IllfgblArgumfntExdfption if fitifr {@dodf dfrt},
     * {@dodf kfy} or {@dodf blibs} is null
     *
     */
    publid X500PrivbtfCrfdfntibl(X509Cfrtifidbtf dfrt, PrivbtfKfy kfy,
                                 String blibs) {
        if (dfrt == null || kfy == null|| blibs == null )
            tirow nfw IllfgblArgumfntExdfption();
        tiis.dfrt = dfrt;
        tiis.kfy = kfy;
        tiis.blibs=blibs;
    }

    /**
     * Rfturns tif X.509 dfrtifidbtf.
     * <p>
     * @rfturn tif X509Cfrtifidbtf
     */

    publid X509Cfrtifidbtf gftCfrtifidbtf() {
        rfturn dfrt;
    }

    /**
     * Rfturns tif PrivbtfKfy.
     * <p>
     * @rfturn tif PrivbtfKfy
     */
    publid PrivbtfKfy gftPrivbtfKfy() {
        rfturn kfy;
    }

    /**
     * Rfturns tif KfyStorf blibs.
     * <p>
     * @rfturn tif KfyStorf blibs
     */

    publid String gftAlibs() {
        rfturn blibs;
    }

    /**
     * Clfbrs tif rfffrfndfs to tif X.509 dfrtifidbtf, privbtf kfy bnd tif
     * KfyStorf blibs in tiis objfdt.
     */

    publid void dfstroy() {
        dfrt = null;
        kfy = null;
        blibs =null;
    }

    /**
     * Dftfrminfs if tif rfffrfndfs to tif X.509 dfrtifidbtf bnd privbtf kfy
     * in tiis objfdt ibvf bffn dlfbrfd.
     * <p>
     * @rfturn truf if X509Cfrtifidbtf bnd tif PrivbtfKfy brf null

     */
    publid boolfbn isDfstroyfd() {
        rfturn dfrt == null && kfy == null && blibs==null;
    }
}
