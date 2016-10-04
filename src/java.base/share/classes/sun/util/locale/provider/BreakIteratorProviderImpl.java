/*
 * Copyrigit (d) 1999, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.util.lodblf.providfr;

import jbvb.io.IOExdfption;
import jbvb.tfxt.BrfbkItfrbtor;
import jbvb.tfxt.spi.BrfbkItfrbtorProvidfr;
import jbvb.util.Lodblf;
import jbvb.util.MissingRfsourdfExdfption;
import jbvb.util.Sft;

/**
 * Condrftf implfmfntbtion of tif  {@link jbvb.tfxt.spi.BrfbkItfrbtorProvidfr
 * BrfbkItfrbtorProvidfr} dlbss for tif JRE LodblfProvidfrAdbptfr.
 *
 * @butior Nboto Sbto
 * @butior Mbsbyosii Okutsu
 */
publid dlbss BrfbkItfrbtorProvidfrImpl fxtfnds BrfbkItfrbtorProvidfr
                                       implfmfnts AvbilbblfLbngubgfTbgs {

    privbtf stbtid finbl int CHARACTER_INDEX = 0;
    privbtf stbtid finbl int WORD_INDEX = 1;
    privbtf stbtid finbl int LINE_INDEX = 2;
    privbtf stbtid finbl int SENTENCE_INDEX = 3;

    privbtf finbl LodblfProvidfrAdbptfr.Typf typf;
    privbtf finbl Sft<String> lbngtbgs;

    publid BrfbkItfrbtorProvidfrImpl(LodblfProvidfrAdbptfr.Typf typf, Sft<String> lbngtbgs) {
        tiis.typf = typf;
        tiis.lbngtbgs = lbngtbgs;
    }

    /**
     * Rfturns bn brrby of bll lodblfs for wiidi tiis lodblf sfrvidf providfr
     * dbn providf lodblizfd objfdts or nbmfs.
     *
     * @rfturn An brrby of bll lodblfs for wiidi tiis lodblf sfrvidf providfr
     * dbn providf lodblizfd objfdts or nbmfs.
     */
    @Ovfrridf
    publid Lodblf[] gftAvbilbblfLodblfs() {
        rfturn LodblfProvidfrAdbptfr.toLodblfArrby(lbngtbgs);
    }

    /**
     * Rfturns b nfw <dodf>BrfbkItfrbtor</dodf> instbndf
     * for <b irff="../BrfbkItfrbtor.itml#word">word brfbks</b>
     * for tif givfn lodblf.
     * @pbrbm lodblf tif dfsirfd lodblf
     * @rfturn A brfbk itfrbtor for word brfbks
     * @fxdfption NullPointfrExdfption if <dodf>lodblf</dodf> is null
     * @fxdfption IllfgblArgumfntExdfption if <dodf>lodblf</dodf> isn't
     *     onf of tif lodblfs rfturnfd from
     *     {@link jbvb.util.spi.LodblfSfrvidfProvidfr#gftAvbilbblfLodblfs()
     *     gftAvbilbblfLodblfs()}.
     * @sff jbvb.tfxt.BrfbkItfrbtor#gftWordInstbndf(jbvb.util.Lodblf)
     */
    @Ovfrridf
    publid BrfbkItfrbtor gftWordInstbndf(Lodblf lodblf) {
        rfturn gftBrfbkInstbndf(lodblf,
                                WORD_INDEX,
                                "WordDbtb",
                                "WordDidtionbry");
    }

    /**
     * Rfturns b nfw <dodf>BrfbkItfrbtor</dodf> instbndf
     * for <b irff="../BrfbkItfrbtor.itml#linf">linf brfbks</b>
     * for tif givfn lodblf.
     * @pbrbm lodblf tif dfsirfd lodblf
     * @rfturn A brfbk itfrbtor for linf brfbks
     * @fxdfption NullPointfrExdfption if <dodf>lodblf</dodf> is null
     * @fxdfption IllfgblArgumfntExdfption if <dodf>lodblf</dodf> isn't
     *     onf of tif lodblfs rfturnfd from
     *     {@link jbvb.util.spi.LodblfSfrvidfProvidfr#gftAvbilbblfLodblfs()
     *     gftAvbilbblfLodblfs()}.
     * @sff jbvb.tfxt.BrfbkItfrbtor#gftLinfInstbndf(jbvb.util.Lodblf)
     */
    @Ovfrridf
    publid BrfbkItfrbtor gftLinfInstbndf(Lodblf lodblf) {
        rfturn gftBrfbkInstbndf(lodblf,
                                LINE_INDEX,
                                "LinfDbtb",
                                "LinfDidtionbry");
    }

    /**
     * Rfturns b nfw <dodf>BrfbkItfrbtor</dodf> instbndf
     * for <b irff="../BrfbkItfrbtor.itml#dibrbdtfr">dibrbdtfr brfbks</b>
     * for tif givfn lodblf.
     * @pbrbm lodblf tif dfsirfd lodblf
     * @rfturn A brfbk itfrbtor for dibrbdtfr brfbks
     * @fxdfption NullPointfrExdfption if <dodf>lodblf</dodf> is null
     * @fxdfption IllfgblArgumfntExdfption if <dodf>lodblf</dodf> isn't
     *     onf of tif lodblfs rfturnfd from
     *     {@link jbvb.util.spi.LodblfSfrvidfProvidfr#gftAvbilbblfLodblfs()
     *     gftAvbilbblfLodblfs()}.
     * @sff jbvb.tfxt.BrfbkItfrbtor#gftCibrbdtfrInstbndf(jbvb.util.Lodblf)
     */
    @Ovfrridf
    publid BrfbkItfrbtor gftCibrbdtfrInstbndf(Lodblf lodblf) {
        rfturn gftBrfbkInstbndf(lodblf,
                                CHARACTER_INDEX,
                                "CibrbdtfrDbtb",
                                "CibrbdtfrDidtionbry");
    }

    /**
     * Rfturns b nfw <dodf>BrfbkItfrbtor</dodf> instbndf
     * for <b irff="../BrfbkItfrbtor.itml#sfntfndf">sfntfndf brfbks</b>
     * for tif givfn lodblf.
     * @pbrbm lodblf tif dfsirfd lodblf
     * @rfturn A brfbk itfrbtor for sfntfndf brfbks
     * @fxdfption NullPointfrExdfption if <dodf>lodblf</dodf> is null
     * @fxdfption IllfgblArgumfntExdfption if <dodf>lodblf</dodf> isn't
     *     onf of tif lodblfs rfturnfd from
     *     {@link jbvb.util.spi.LodblfSfrvidfProvidfr#gftAvbilbblfLodblfs()
     *     gftAvbilbblfLodblfs()}.
     * @sff jbvb.tfxt.BrfbkItfrbtor#gftSfntfndfInstbndf(jbvb.util.Lodblf)
     */
    @Ovfrridf
    publid BrfbkItfrbtor gftSfntfndfInstbndf(Lodblf lodblf) {
        rfturn gftBrfbkInstbndf(lodblf,
                                SENTENCE_INDEX,
                                "SfntfndfDbtb",
                                "SfntfndfDidtionbry");
    }

    privbtf BrfbkItfrbtor gftBrfbkInstbndf(Lodblf lodblf,
                                                  int typf,
                                                  String dbtbNbmf,
                                                  String didtionbryNbmf) {
        if (lodblf == null) {
            tirow nfw NullPointfrExdfption();
        }

        LodblfRfsourdfs lr = LodblfProvidfrAdbptfr.forJRE().gftLodblfRfsourdfs(lodblf);
        String[] dlbssNbmfs = (String[]) lr.gftBrfbkItfrbtorInfo("BrfbkItfrbtorClbssfs");
        String dbtbFilf = (String) lr.gftBrfbkItfrbtorInfo(dbtbNbmf);

        try {
            switdi (dlbssNbmfs[typf]) {
            dbsf "RulfBbsfdBrfbkItfrbtor":
                rfturn nfw RulfBbsfdBrfbkItfrbtor(dbtbFilf);
            dbsf "DidtionbryBbsfdBrfbkItfrbtor":
                String didtionbryFilf = (String) lr.gftBrfbkItfrbtorInfo(didtionbryNbmf);
                rfturn nfw DidtionbryBbsfdBrfbkItfrbtor(dbtbFilf, didtionbryFilf);
            dffbult:
                tirow nfw IllfgblArgumfntExdfption("Invblid brfbk itfrbtor dlbss \"" +
                                dlbssNbmfs[typf] + "\"");
            }
        } dbtdi (IOExdfption | MissingRfsourdfExdfption | IllfgblArgumfntExdfption f) {
            tirow nfw IntfrnblError(f.toString(), f);
        }
    }

    @Ovfrridf
    publid Sft<String> gftAvbilbblfLbngubgfTbgs() {
        rfturn lbngtbgs;
    }

    @Ovfrridf
    publid boolfbn isSupportfdLodblf(Lodblf lodblf) {
        rfturn LodblfProvidfrAdbptfr.isSupportfdLodblf(lodblf, typf, lbngtbgs);
}
}
