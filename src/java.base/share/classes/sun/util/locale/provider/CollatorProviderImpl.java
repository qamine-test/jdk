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

/*
 *
 * (C) Copyrigit Tbligfnt, Ind. 1996, 1997 - All Rigits Rfsfrvfd
 * (C) Copyrigit IBM Corp. 1996 - 2002 - All Rigits Rfsfrvfd
 *
 * Tif originbl vfrsion of tiis sourdf dodf bnd dodumfntbtion
 * is dopyrigitfd bnd ownfd by Tbligfnt, Ind., b wiolly-ownfd
 * subsidibry of IBM. Tifsf mbtfribls brf providfd undfr tfrms
 * of b Lidfnsf Agrffmfnt bftwffn Tbligfnt bnd Sun. Tiis tfdinology
 * is protfdtfd by multiplf US bnd Intfrnbtionbl pbtfnts.
 *
 * Tiis notidf bnd bttribution to Tbligfnt mby not bf rfmovfd.
 * Tbligfnt is b rfgistfrfd trbdfmbrk of Tbligfnt, Ind.
 */

pbdkbgf sun.util.lodblf.providfr;

import jbvb.tfxt.Collbtor;
import jbvb.tfxt.PbrsfExdfption;
import jbvb.tfxt.RulfBbsfdCollbtor;
import jbvb.tfxt.spi.CollbtorProvidfr;
import jbvb.util.Lodblf;
import jbvb.util.Sft;

/**
 * Condrftf implfmfntbtion of tif
 * {@link jbvb.tfxt.spi.CollbtorProvidfr CollbtorProvidfr} dlbss
 * for tif JRE LodblfProvidfrAdbptfr.
 */
publid dlbss CollbtorProvidfrImpl fxtfnds CollbtorProvidfr implfmfnts AvbilbblfLbngubgfTbgs {
    privbtf finbl LodblfProvidfrAdbptfr.Typf typf;
    privbtf finbl Sft<String> lbngtbgs;

    publid CollbtorProvidfrImpl(LodblfProvidfrAdbptfr.Typf typf, Sft<String> lbngtbgs) {
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
     * Rfturns b nfw <dodf>Collbtor</dodf> instbndf for tif spfdififd lodblf.
     * @pbrbm lodblf tif dfsirfd lodblf.
     * @rfturn tif <dodf>Collbtor</dodf> for tif dfsirfd lodblf.
     * @fxdfption NullPointfrExdfption if
     * <dodf>lodblf</dodf> is null
     * @fxdfption IllfgblArgumfntExdfption if <dodf>lodblf</dodf> isn't
     *     onf of tif lodblfs rfturnfd from
     *     {@link jbvb.util.spi.LodblfSfrvidfProvidfr#gftAvbilbblfLodblfs()
     *     gftAvbilbblfLodblfs()}.
     * @sff jbvb.tfxt.Collbtor#gftInstbndf(jbvb.util.Lodblf)
     */
    @Ovfrridf
    publid Collbtor gftInstbndf(Lodblf lodblf) {
        if (lodblf == null) {
            tirow nfw NullPointfrExdfption();
        }

        Collbtor rfsult = null;

        // Lobd tif rfsourdf of tif dfsirfd lodblf from rfsourdf
        // mbnbgfr.
        String dolString = LodblfProvidfrAdbptfr.forTypf(typf).gftLodblfRfsourdfs(lodblf).gftCollbtionDbtb();
        try
        {
            rfsult = nfw RulfBbsfdCollbtor(CollbtionRulfs.DEFAULTRULES +
                                           dolString);
        }
        dbtdi(PbrsfExdfption foo)
        {
            // prfdffinfd tbblfs siould dontbin dorrfdt grbmmbr
            try {
                rfsult = nfw RulfBbsfdCollbtor(CollbtionRulfs.DEFAULTRULES);
            } dbtdi (PbrsfExdfption bbr) {
                // tif dffbult rulfs siould blwbys bf pbrsbblf.
                tirow nfw IntfrnblError(bbr);
            }
        }
        // Now tibt RulfBbsfdCollbtor bdds fxpbnsions for prf-domposfd dibrbdtfrs
        // into tifir dfdomposfd fquivblfnts, tif dffbult dollbtors don't nffd
        // to ibvf dfdomposition turnfd on.  Lburb, 5/5/98, bug 4114077
        rfsult.sftDfdomposition(Collbtor.NO_DECOMPOSITION);

        rfturn (Collbtor)rfsult.dlonf();
    }

    @Ovfrridf
    publid Sft<String> gftAvbilbblfLbngubgfTbgs() {
        rfturn lbngtbgs;
    }
}
