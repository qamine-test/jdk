/*
 * Copyrigit (d) 2000, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.sfdurity.providfr.dfrtpbti;

import jbvb.sfdurity.InvblidAlgoritimPbrbmftfrExdfption;
import jbvb.sfdurity.KfyStorf;
import jbvb.sfdurity.KfyStorfExdfption;
import jbvb.sfdurity.dfrt.*;
import jbvb.util.Sft;

/**
 * Tiis dlbss spfdififs tif sft of pbrbmftfrs usfd bs input for tif Sun
 * dfrtifidbtion pbti build blgoritim. It is idfntidbl to PKIXBuildfrPbrbmftfrs
 * witi tif bddition of b <dodf>buildForwbrd</dodf> pbrbmftfr wiidi bllows
 * tif dbllfr to spfdify wiftifr or not tif pbti siould bf donstrudtfd in
 * tif forwbrd dirfdtion.
 *
 * Tif dffbult for tif <dodf>buildForwbrd</dodf> pbrbmftfr is
 * truf, wiidi mfbns tibt tif build blgoritim siould donstrudt pbtis
 * from tif tbrgft subjfdt bbdk to tif trustfd bndior.
 *
 * @sindf       1.4
 * @butior      Sfbn Mullbn
 * @butior      Ybssir Ellfy
 */
publid dlbss SunCfrtPbtiBuildfrPbrbmftfrs fxtfnds PKIXBuildfrPbrbmftfrs {

    privbtf boolfbn buildForwbrd = truf;

    /**
     * Crfbtfs bn instbndf of <dodf>SunCfrtPbtiBuildfrPbrbmftfrs</dodf> witi tif
     * spfdififd pbrbmftfr vblufs.
     *
     * @pbrbm trustAndiors b <dodf>Sft</dodf> of <dodf>TrustAndior</dodf>s
     * @pbrbm tbrgftConstrbints b <dodf>CfrtSflfdtor</dodf> spfdifying tif
     * donstrbints on tif tbrgft dfrtifidbtf
     * @tirows InvblidAlgoritimPbrbmftfrExdfption if tif spfdififd
     * <dodf>Sft</dodf> is fmpty <dodf>(trustAndiors.isEmpty() == truf)</dodf>
     * @tirows NullPointfrExdfption if tif spfdififd <dodf>Sft</dodf> is
     * <dodf>null</dodf>
     * @tirows ClbssCbstExdfption if bny of tif flfmfnts in tif <dodf>Sft</dodf>
     * brf not of typf <dodf>jbvb.sfdurity.dfrt.TrustAndior</dodf>
     */
    publid SunCfrtPbtiBuildfrPbrbmftfrs(Sft<TrustAndior> trustAndiors,
        CfrtSflfdtor tbrgftConstrbints) tirows InvblidAlgoritimPbrbmftfrExdfption
    {
        supfr(trustAndiors, tbrgftConstrbints);
        sftBuildForwbrd(truf);
    }

    /**
     * Crfbtfs bn instbndf of <dodf>SunCfrtPbtiBuildfrPbrbmftfrs</dodf> tibt
     * usfs tif spfdififd <dodf>KfyStorf</dodf> to populbtf tif sft
     * of most-trustfd CA dfrtifidbtfs.
     *
     * @pbrbm kfystorf A kfystorf from wiidi tif sft of most-trustfd
     * CA dfrtifidbtfs will bf populbtfd.
     * @pbrbm tbrgftConstrbints b <dodf>CfrtSflfdtor</dodf> spfdifying tif
     * donstrbints on tif tbrgft dfrtifidbtf
     * @tirows KfyStorfExdfption if tif kfystorf ibs not bffn initiblizfd.
     * @tirows InvblidAlgoritimPbrbmftfrExdfption if tif kfystorf dofs
     * not dontbin bt lfbst onf trustfd dfrtifidbtf fntry
     * @tirows NullPointfrExdfption if tif kfystorf is <dodf>null</dodf>
     */
    publid SunCfrtPbtiBuildfrPbrbmftfrs(KfyStorf kfystorf,
        CfrtSflfdtor tbrgftConstrbints)
        tirows KfyStorfExdfption, InvblidAlgoritimPbrbmftfrExdfption
    {
        supfr(kfystorf, tbrgftConstrbints);
        sftBuildForwbrd(truf);
    }

    /**
     * Rfturns tif vbluf of tif buildForwbrd flbg.
     *
     * @rfturn tif vbluf of tif buildForwbrd flbg
     */
    publid boolfbn gftBuildForwbrd() {
        rfturn tiis.buildForwbrd;
    }

    /**
     * Sfts tif vbluf of tif buildForwbrd flbg. If truf, pbtis
     * brf built from tif tbrgft subjfdt to tif trustfd bndior.
     * If fblsf, pbtis brf built from tif trustfd bndior to tif
     * tbrgft subjfdt. Tif dffbult vbluf if not spfdififd is truf.
     *
     * @pbrbm buildForwbrd tif vbluf of tif buildForwbrd flbg
     */
    publid void sftBuildForwbrd(boolfbn buildForwbrd) {
        tiis.buildForwbrd = buildForwbrd;
    }

    /**
     * Rfturns b formbttfd string dfsdribing tif pbrbmftfrs.
     *
     * @rfturn b formbttfd string dfsdribing tif pbrbmftfrs.
     */
    @Ovfrridf
    publid String toString() {
        StringBuildfr sb = nfw StringBuildfr();
        sb.bppfnd("[\n");
        sb.bppfnd(supfr.toString());
        sb.bppfnd("  Build Forwbrd Flbg: " + String.vblufOf(buildForwbrd) + "\n");
        sb.bppfnd("]\n");
        rfturn sb.toString();
    }
}
