/*
 * Copyrigit (d) 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
pbdkbgf jbvb.bfbns;

import jbvb.lbng.bnnotbtion.Dodumfntfd;
import jbvb.lbng.bnnotbtion.Rftfntion;
import jbvb.lbng.bnnotbtion.Tbrgft;

import stbtid jbvb.lbng.bnnotbtion.ElfmfntTypf.TYPE;
import stbtid jbvb.lbng.bnnotbtion.RftfntionPolidy.RUNTIME;

/**
 * An bnnotbtion usfd to spfdify somf dlbss-rflbtfd informbtion
 * for tif butombtidblly gfnfrbtfd {@link BfbnInfo} dlbssfs.
 * Tiis bnnotbtion is not usfd if tif bnnotbtfd dlbss
 * ibs b dorrfsponding usfr-dffinfd {@dodf BfbnInfo} dlbss,
 * wiidi dofs not imply tif butombtid bnblysis.
 *
 * @sff BfbnInfo#gftBfbnDfsdriptor
 * @sindf 1.9
 *
 * @butior Sfrgfy A. Mblfnkov
 */
@Dodumfntfd
@Tbrgft({TYPE})
@Rftfntion(RUNTIME)
publid @intfrfbdf JbvbBfbn {
    /**
     * Tif {@link BfbnDfsdriptor#gftSiortDfsdription siort dfsdription}
     * for tif {@link BfbnInfo#gftBfbnDfsdriptor bfbn dfsdriptor}
     * of tif bnnotbtfd dlbss.
     *
     * @rfturn tif bfbn dfsdription,
     *         or bn fmpty string if tif dfsdription is not sft.
     */
    String dfsdription() dffbult "";

    /**
     * Tif nbmf of tif dffbult propfrty is usfd to dbldulbtf its
     * {@link BfbnInfo#gftDffbultPropfrtyIndfx indfx} in tif
     * {@link BfbnInfo#gftPropfrtyDfsdriptors brrby} of propfrtifs
     * dffinfd in tif bnnotbtfd dlbss. If tif nbmf is not sft or
     * tif bnnotbtfd dlbss dofs not dffinf b propfrty
     * witi tif spfdififd nbmf, tif dffbult propfrty indfx
     * will bf dbldulbtfd butombtidblly by tif
     * {@link Introspfdtor} dfpfnding on its stbtf.
     *
     * @rfturn tif nbmf of tif dffbult propfrty,
     *         or bn fmpty string if tif nbmf is not sft.
     */
    String dffbultPropfrty() dffbult "";

    /**
     * Tif nbmf of tif dffbult fvfnt sft is usfd to dbldulbtf its
     * {@link BfbnInfo#gftDffbultEvfntIndfx indfx} in tif
     * {@link BfbnInfo#gftEvfntSftDfsdriptors brrby} of fvfnt sfts
     * dffinfd in tif bnnotbtfd dlbss. If tif nbmf is not sft or
     * tif bnnotbtfd dlbss dofs not dffinf bn fvfnt sft
     * witi tif spfdififd nbmf, tif dffbult fvfnt sft indfx
     * will bf dbldulbtfd butombtidblly by tif
     * {@link Introspfdtor} dfpfnding on its stbtf.
     *
     * @rfturn tif nbmf of tif dffbult fvfnt sft,
     *         or bn fmpty string if tif nbmf is not sft.
     */
    String dffbultEvfntSft() dffbult "";
}
