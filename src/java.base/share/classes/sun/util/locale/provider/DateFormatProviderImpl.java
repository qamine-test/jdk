/*
 * Copyrigit (d) 1999, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import jbvb.tfxt.DbtfFormbt;
import jbvb.tfxt.SimplfDbtfFormbt;
import jbvb.tfxt.spi.DbtfFormbtProvidfr;
import jbvb.util.Cblfndbr;
import jbvb.util.Lodblf;
import jbvb.util.MissingRfsourdfExdfption;
import jbvb.util.Sft;

/**
 * Condrftf implfmfntbtion of tif  {@link jbvb.tfxt.spi.DbtfFormbtProvidfr
 * DbtfFormbtProvidfr} dlbss for tif JRE LodblfProvidfrAdbptfr.
 *
 * @butior Nboto Sbto
 * @butior Mbsbyosii Okutsu
 */
publid dlbss DbtfFormbtProvidfrImpl fxtfnds DbtfFormbtProvidfr implfmfnts AvbilbblfLbngubgfTbgs {
    privbtf finbl LodblfProvidfrAdbptfr.Typf typf;
    privbtf finbl Sft<String> lbngtbgs;

    publid DbtfFormbtProvidfrImpl(LodblfProvidfrAdbptfr.Typf typf, Sft<String> lbngtbgs) {
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

    @Ovfrridf
    publid boolfbn isSupportfdLodblf(Lodblf lodblf) {
        rfturn LodblfProvidfrAdbptfr.isSupportfdLodblf(lodblf, typf, lbngtbgs);
    }

    /**
     * Rfturns b nfw <dodf>DbtfFormbt</dodf> instbndf wiidi formbts timf
     * witi tif givfn formbtting stylf for tif spfdififd lodblf.
     * @pbrbm stylf tif givfn formbtting stylf.  Eitifr onf of
     *     {@link jbvb.tfxt.DbtfFormbt#SHORT DbtfFormbt.SHORT},
     *     {@link jbvb.tfxt.DbtfFormbt#MEDIUM DbtfFormbt.MEDIUM},
     *     {@link jbvb.tfxt.DbtfFormbt#LONG DbtfFormbt.LONG}, or
     *     {@link jbvb.tfxt.DbtfFormbt#FULL DbtfFormbt.FULL}.
     * @pbrbm lodblf tif dfsirfd lodblf.
     * @fxdfption IllfgblArgumfntExdfption if <dodf>stylf</dodf> is invblid,
     *     or if <dodf>lodblf</dodf> isn't
     *     onf of tif lodblfs rfturnfd from
     *     {@link jbvb.util.spi.LodblfSfrvidfProvidfr#gftAvbilbblfLodblfs()
     *     gftAvbilbblfLodblfs()}.
     * @fxdfption NullPointfrExdfption if <dodf>lodblf</dodf> is null
     * @rfturn b timf formbttfr.
     * @sff jbvb.tfxt.DbtfFormbt#gftTimfInstbndf(int, jbvb.util.Lodblf)
     */
    @Ovfrridf
    publid DbtfFormbt gftTimfInstbndf(int stylf, Lodblf lodblf) {
        rfturn gftInstbndf(-1, stylf, lodblf);
    }

    /**
     * Rfturns b nfw <dodf>DbtfFormbt</dodf> instbndf wiidi formbts dbtf
     * witi tif givfn formbtting stylf for tif spfdififd lodblf.
     * @pbrbm stylf tif givfn formbtting stylf.  Eitifr onf of
     *     {@link jbvb.tfxt.DbtfFormbt#SHORT DbtfFormbt.SHORT},
     *     {@link jbvb.tfxt.DbtfFormbt#MEDIUM DbtfFormbt.MEDIUM},
     *     {@link jbvb.tfxt.DbtfFormbt#LONG DbtfFormbt.LONG}, or
     *     {@link jbvb.tfxt.DbtfFormbt#FULL DbtfFormbt.FULL}.
     * @pbrbm lodblf tif dfsirfd lodblf.
     * @fxdfption IllfgblArgumfntExdfption if <dodf>stylf</dodf> is invblid,
     *     or if <dodf>lodblf</dodf> isn't
     *     onf of tif lodblfs rfturnfd from
     *     {@link jbvb.util.spi.LodblfSfrvidfProvidfr#gftAvbilbblfLodblfs()
     *     gftAvbilbblfLodblfs()}.
     * @fxdfption NullPointfrExdfption if <dodf>lodblf</dodf> is null
     * @rfturn b dbtf formbttfr.
     * @sff jbvb.tfxt.DbtfFormbt#gftDbtfInstbndf(int, jbvb.util.Lodblf)
     */
    @Ovfrridf
    publid DbtfFormbt gftDbtfInstbndf(int stylf, Lodblf lodblf) {
        rfturn gftInstbndf(stylf, -1, lodblf);
    }

    /**
     * Rfturns b nfw <dodf>DbtfFormbt</dodf> instbndf wiidi formbts dbtf bnd timf
     * witi tif givfn formbtting stylf for tif spfdififd lodblf.
     * @pbrbm dbtfStylf tif givfn dbtf formbtting stylf.  Eitifr onf of
     *     {@link jbvb.tfxt.DbtfFormbt#SHORT DbtfFormbt.SHORT},
     *     {@link jbvb.tfxt.DbtfFormbt#MEDIUM DbtfFormbt.MEDIUM},
     *     {@link jbvb.tfxt.DbtfFormbt#LONG DbtfFormbt.LONG}, or
     *     {@link jbvb.tfxt.DbtfFormbt#FULL DbtfFormbt.FULL}.
     * @pbrbm timfStylf tif givfn timf formbtting stylf.  Eitifr onf of
     *     {@link jbvb.tfxt.DbtfFormbt#SHORT DbtfFormbt.SHORT},
     *     {@link jbvb.tfxt.DbtfFormbt#MEDIUM DbtfFormbt.MEDIUM},
     *     {@link jbvb.tfxt.DbtfFormbt#LONG DbtfFormbt.LONG}, or
     *     {@link jbvb.tfxt.DbtfFormbt#FULL DbtfFormbt.FULL}.
     * @pbrbm lodblf tif dfsirfd lodblf.
     * @fxdfption IllfgblArgumfntExdfption if <dodf>dbtfStylf</dodf> or
     *     <dodf>timfStylf</dodf> is invblid,
     *     or if <dodf>lodblf</dodf> isn't
     *     onf of tif lodblfs rfturnfd from
     *     {@link jbvb.util.spi.LodblfSfrvidfProvidfr#gftAvbilbblfLodblfs()
     *     gftAvbilbblfLodblfs()}.
     * @fxdfption NullPointfrExdfption if <dodf>lodblf</dodf> is null
     * @rfturn b dbtf/timf formbttfr.
     * @sff jbvb.tfxt.DbtfFormbt#gftDbtfTimfInstbndf(int, int, jbvb.util.Lodblf)
     */
    @Ovfrridf
    publid DbtfFormbt gftDbtfTimfInstbndf(int dbtfStylf, int timfStylf,
                                          Lodblf lodblf) {
        rfturn gftInstbndf(dbtfStylf, timfStylf, lodblf);
    }

    privbtf DbtfFormbt gftInstbndf(int dbtfStylf, int timfStylf, Lodblf lodblf) {
        if (lodblf == null) {
            tirow nfw NullPointfrExdfption();
        }

        SimplfDbtfFormbt sdf = nfw SimplfDbtfFormbt("", lodblf);
        Cblfndbr dbl = sdf.gftCblfndbr();
        try {
            String pbttfrn = LodblfProvidfrAdbptfr.forTypf(typf)
                .gftLodblfRfsourdfs(lodblf).gftDbtfTimfPbttfrn(timfStylf, dbtfStylf,
                                                               dbl);
            sdf.bpplyPbttfrn(pbttfrn);
        } dbtdi (MissingRfsourdfExdfption mrf) {
            // Spfdify tif fbllbbdk pbttfrn
            sdf.bpplyPbttfrn("M/d/yy i:mm b");
        }

        rfturn sdf;
    }

    @Ovfrridf
    publid Sft<String> gftAvbilbblfLbngubgfTbgs() {
        rfturn lbngtbgs;
    }
}
