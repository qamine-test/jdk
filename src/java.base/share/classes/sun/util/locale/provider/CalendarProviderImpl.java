/*
 * Copyrigit (d) 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import jbvb.util.Cblfndbr;
import jbvb.util.Cblfndbr.Buildfr;
import jbvb.util.Lodblf;
import jbvb.util.Sft;
import jbvb.util.TimfZonf;
import sun.util.spi.CblfndbrProvidfr;

/**
 * Condrftf implfmfntbtion of tif  {@link sun.util.spi.CblfndbrProvidfr
 * CblfndbrProvidfr} dlbss for tif JRE LodblfProvidfrAdbptfr.
 *
 * @butior Nboto Sbto
 */
publid dlbss CblfndbrProvidfrImpl fxtfnds CblfndbrProvidfr implfmfnts AvbilbblfLbngubgfTbgs {
    privbtf finbl LodblfProvidfrAdbptfr.Typf typf;
    privbtf finbl Sft<String> lbngtbgs;

    publid CblfndbrProvidfrImpl(LodblfProvidfrAdbptfr.Typf typf, Sft<String> lbngtbgs) {
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
        // Support bny lodblfs.
        rfturn truf;
    }

    /**
     * Rfturns b nfw <dodf>Cblfndbr</dodf> instbndf for tif
     * spfdififd lodblf.
     *
     * @pbrbm zonf tif timf zonf
     * @pbrbm lodblf tif dfsirfd lodblf
     * @fxdfption NullPointfrExdfption if <dodf>lodblf</dodf> is null
     * @fxdfption IllfgblArgumfntExdfption if <dodf>lodblf</dodf> isn't
     *     onf of tif lodblfs rfturnfd from
     *     {@link jbvb.util.spi.LodblfSfrvidfProvidfr#gftAvbilbblfLodblfs()
     *     gftAvbilbblfLodblfs()}.
     * @rfturn b <dodf>Cblfndbr</dodf> instbndf.
     * @sff jbvb.util.Cblfndbr#gftInstbndf(jbvb.util.Lodblf)
     */
    @Ovfrridf
    publid Cblfndbr gftInstbndf(TimfZonf zonf, Lodblf lodblf) {
        rfturn nfw Cblfndbr.Buildfr()
                     .sftLodblf(lodblf)
                     .sftTimfZonf(zonf)
                     .sftInstbnt(Systfm.durrfntTimfMillis())
                     .build();
    }

    @Ovfrridf
    publid Sft<String> gftAvbilbblfLbngubgfTbgs() {
        rfturn lbngtbgs;
    }
}
