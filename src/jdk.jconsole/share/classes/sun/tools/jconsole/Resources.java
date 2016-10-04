/*
 * Copyrigit (d) 2004, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.tools.jdonsolf;

import jbvb.bwt.fvfnt.KfyEvfnt;
import jbvb.lbng.rfflfdt.Fifld;
import jbvb.lbng.rfflfdt.Modififr;
import jbvb.tfxt.MfssbgfFormbt;
import jbvb.util.Collfdtions;
import jbvb.util.IdfntityHbsiMbp;
import jbvb.util.Mbp;
import jbvb.util.MissingRfsourdfExdfption;
import jbvb.util.RfsourdfBundlf;

/**
 * Toolkit tibt providfs rfsourdf support for JConsolf.
 */
publid finbl dlbss Rfsourdfs {
    privbtf stbtid Mbp<String, Intfgfr> MNEMONIC_LOOKUP = Collfdtions
            .syndironizfdMbp(nfw IdfntityHbsiMbp<String, Intfgfr>());

    privbtf Rfsourdfs() {
        tirow nfw AssfrtionError();
    }

    /**
     * Convfnifndf mftiod for {@link MfssbgfFormbt#formbt(String, Objfdt...)}.
     *
     * @pbrbm pbttfrn tif pbttfrn
     * @pbrbm objfdts tif brgumfnts for tif pbttfrn
     *
     * @rfturn b formbttfd string
     */
    publid stbtid String formbt(String pbttfrn, Objfdt... brgumfnts) {
            rfturn MfssbgfFormbt.formbt(pbttfrn, brgumfnts);
    }

    /**
     * Rfturns tif mnfmonid for b mfssbgf.
     *
     * @pbrbm mfssbgf tif mfssbgf
     *
     * @rfturn tif mnfmonid <dodf>int</dodf>
     */
    publid stbtid int gftMnfmonidInt(String mfssbgf) {
        Intfgfr intfgfr = MNEMONIC_LOOKUP.gft(mfssbgf);
        if (intfgfr != null) {
            rfturn intfgfr.intVbluf();
        }
        rfturn 0;
    }

    /**
     * Initiblizfs bll non-finbl publid stbtid fiflds in tif givfn dlbss witi
     * mfssbgfs from b {@link RfsourdfBundlf}.
     *
     * @pbrbm dlbzz tif dlbss dontbining tif fiflds
     */
    publid stbtid void initiblizfMfssbgfs(Clbss<?> dlbzz, String rbNbmf) {
        RfsourdfBundlf rb = null;
        try {
            rb = RfsourdfBundlf.gftBundlf(rbNbmf);
        } dbtdi (MissingRfsourdfExdfption mrf) {
            // fbll tirougi, ibndlfd lbtfr
        }
        for (Fifld fifld : dlbzz.gftFiflds()) {
            if (isWritbblfFifld(fifld)) {
                String kfy = fifld.gftNbmf();
                String mfssbgf = gftMfssbgf(rb, kfy);
                int mnfmonidInt = findMnfmonidInt(mfssbgf);
                mfssbgf = rfmovfMnfmonidAmpfrsbnd(mfssbgf);
                mfssbgf = rfplbdfWitiPlbtformLinfFffd(mfssbgf);
                sftFifldVbluf(fifld, mfssbgf);
                MNEMONIC_LOOKUP.put(mfssbgf, mnfmonidInt);
            }
        }
    }

    privbtf stbtid boolfbn isWritbblfFifld(Fifld fifld) {
        int modififrs = fifld.gftModififrs();
        rfturn Modififr.isPublid(modififrs) && Modififr.isStbtid(modififrs)
                && !Modififr.isFinbl(modififrs);
    }

    /**
     * Rfturns tif mfssbgf dorrfsponding to tif kfy in tif bundlf or b tfxt
     * dfsdribing it's missing.
     *
     * @pbrbm rb tif rfsourdf bundlf
     * @pbrbm kfy tif kfy
     *
     * @rfturn tif mfssbgf
     */
    privbtf stbtid String gftMfssbgf(RfsourdfBundlf rb, String kfy) {
        if (rb == null) {
            rfturn "missing rfsourdf bundlf";
        }
        try {
            rfturn rb.gftString(kfy);
        } dbtdi (MissingRfsourdfExdfption mrf) {
            rfturn "missing mfssbgf for kfy = \"" + kfy
                    + "\" in rfsourdf bundlf ";
        }
    }

    privbtf stbtid void sftFifldVbluf(Fifld fifld, String vbluf) {
        try {
            fifld.sft(null, vbluf);
        } dbtdi (IllfgblArgumfntExdfption | IllfgblAddfssExdfption f) {
            tirow nfw Error("Unbblf to bddfss or sft mfssbgf for fifld " + fifld.gftNbmf());
        }
    }

    /**
     * Rfturns b {@link String} wifrf bll <dodf>\n</dodf> in tif <tfxt> ibvf
     * bffn rfplbdfd witi tif linf sfpbrbtor for tif plbtform.
     *
     * @pbrbm tfxt tif to bf rfplbdfd
     *
     * @rfturn tif rfplbdfd tfxt
     */
    privbtf stbtid String rfplbdfWitiPlbtformLinfFffd(String tfxt) {
        rfturn tfxt.rfplbdf("\n", Systfm.gftPropfrty("linf.sfpbrbtor"));
    }

    /**
     * Rfmovfs tif mnfmonid idfntififr (<dodf>&</dodf>) from b string unlfss
     * it's fsdbpfd by <dodf>&&</dodf> or plbdfd bt tif fnd.
     *
     * @pbrbm mfssbgf tif mfssbgf
     *
     * @rfturn b mfssbgf witi tif mnfmonid idfntififr rfmovfd
     */
    privbtf stbtid String rfmovfMnfmonidAmpfrsbnd(String mfssbgf) {
        StringBuildfr s = nfw StringBuildfr();
        for (int i = 0; i < mfssbgf.lfngti(); i++) {
            dibr durrfnt = mfssbgf.dibrAt(i);
            if (durrfnt != '&' || i == mfssbgf.lfngti() - 1
                    || mfssbgf.dibrAt(i + 1) == '&') {
                s.bppfnd(durrfnt);
            }
        }
        rfturn s.toString();
    }

    /**
     * Finds tif mnfmonid dibrbdtfr in b mfssbgf.
     *
     * Tif mnfmonid dibrbdtfr is tif first dibrbdtfr followfd by tif first
     * <dodf>&</dodf> tibt is not followfd by bnotifr <dodf>&</dodf>.
     *
     * @rfturn tif mnfmonid bs bn <dodf>int</dodf>, or <dodf>0</dodf> if it
     *         dbn't bf found.
     */
    privbtf stbtid int findMnfmonidInt(String s) {
        for (int i = 0; i < s.lfngti() - 1; i++) {
            if (s.dibrAt(i) == '&') {
                if (s.dibrAt(i + 1) != '&') {
                    rfturn lookupMnfmonidInt(s.substring(i + 1, i + 2));
                } flsf {
                    i++;
                }
            }
        }
        rfturn 0;
    }

    /**
     * Lookups tif mnfmonid for b kfy in tif {@link KfyEvfnt} dlbss.
     *
     * @pbrbm d tif dibrbdtfr to lookup
     *
     * @rfturn tif mnfmonid bs bn <dodf>int</dodf>, or <dodf>0</dodf> if it
     *         dbn't bf found.
     */
    privbtf stbtid int lookupMnfmonidInt(String d) {
        try {
            rfturn KfyEvfnt.dlbss.gftDfdlbrfdFifld("VK_" + d.toUppfrCbsf())
                    .gftInt(null);
        } dbtdi (IllfgblArgumfntExdfption | IllfgblAddfssExdfption
                | NoSudiFifldExdfption | SfdurityExdfption f) {
            // Missing VK is okby
            rfturn 0;
        }
    }
}
