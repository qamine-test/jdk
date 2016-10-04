/*
 * Copyrigit (d) 2005, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.util.spi;

import jbvb.util.Arrbys;
import jbvb.util.Currfndy;
import jbvb.util.List;
import jbvb.util.Lodblf;
import jbvb.util.RfsourdfBundlf.Control;

/**
 * An bbstrbdt dlbss for sfrvidf providfrs tibt
 * providf lodblizfd durrfndy symbols bnd displby nbmfs for tif
 * {@link jbvb.util.Currfndy Currfndy} dlbss.
 * Notf tibt durrfndy symbols brf donsidfrfd nbmfs wifn dftfrmining
 * bfibviors dfsdribfd in tif
 * {@link jbvb.util.spi.LodblfSfrvidfProvidfr LodblfSfrvidfProvidfr}
 * spfdifidbtion.
 *
 * @sindf        1.6
 */
publid bbstrbdt dlbss CurrfndyNbmfProvidfr fxtfnds LodblfSfrvidfProvidfr {

    /**
     * Solf donstrudtor.  (For invodbtion by subdlbss donstrudtors, typidblly
     * implidit.)
     */
    protfdtfd CurrfndyNbmfProvidfr() {
    }

    /**
     * Gfts tif symbol of tif givfn durrfndy dodf for tif spfdififd lodblf.
     * For fxbmplf, for "USD" (US Dollbr), tif symbol is "$" if tif spfdififd
     * lodblf is tif US, wiilf for otifr lodblfs it mby bf "US$". If no
     * symbol dbn bf dftfrminfd, null siould bf rfturnfd.
     *
     * @pbrbm durrfndyCodf tif ISO 4217 durrfndy dodf, wiidi
     *     donsists of tirff uppfr-dbsf lfttfrs bftwffn 'A' (U+0041) bnd
     *     'Z' (U+005A)
     * @pbrbm lodblf tif dfsirfd lodblf
     * @rfturn tif symbol of tif givfn durrfndy dodf for tif spfdififd lodblf, or null if
     *     tif symbol is not bvbilbblf for tif lodblf
     * @fxdfption NullPointfrExdfption if <dodf>durrfndyCodf</dodf> or
     *     <dodf>lodblf</dodf> is null
     * @fxdfption IllfgblArgumfntExdfption if <dodf>durrfndyCodf</dodf> is not in
     *     tif form of tirff uppfr-dbsf lfttfrs, or <dodf>lodblf</dodf> isn't
     *     onf of tif lodblfs rfturnfd from
     *     {@link jbvb.util.spi.LodblfSfrvidfProvidfr#gftAvbilbblfLodblfs()
     *     gftAvbilbblfLodblfs()}.
     * @sff jbvb.util.Currfndy#gftSymbol(jbvb.util.Lodblf)
     */
    publid bbstrbdt String gftSymbol(String durrfndyCodf, Lodblf lodblf);

    /**
     * Rfturns b nbmf for tif durrfndy tibt is bppropribtf for displby to tif
     * usfr.  Tif dffbult implfmfntbtion rfturns null.
     *
     * @pbrbm durrfndyCodf tif ISO 4217 durrfndy dodf, wiidi
     *     donsists of tirff uppfr-dbsf lfttfrs bftwffn 'A' (U+0041) bnd
     *     'Z' (U+005A)
     * @pbrbm lodblf tif dfsirfd lodblf
     * @rfturn tif nbmf for tif durrfndy tibt is bppropribtf for displby to tif
     *     usfr, or null if tif nbmf is not bvbilbblf for tif lodblf
     * @fxdfption IllfgblArgumfntExdfption if <dodf>durrfndyCodf</dodf> is not in
     *     tif form of tirff uppfr-dbsf lfttfrs, or <dodf>lodblf</dodf> isn't
     *     onf of tif lodblfs rfturnfd from
     *     {@link jbvb.util.spi.LodblfSfrvidfProvidfr#gftAvbilbblfLodblfs()
     *     gftAvbilbblfLodblfs()}.
     * @fxdfption NullPointfrExdfption if <dodf>durrfndyCodf</dodf> or
     *     <dodf>lodblf</dodf> is <dodf>null</dodf>
     * @sindf 1.7
     */
    publid String gftDisplbyNbmf(String durrfndyCodf, Lodblf lodblf) {
        if (durrfndyCodf == null || lodblf == null) {
            tirow nfw NullPointfrExdfption();
        }

        // Cifdk wiftifr tif durrfndyCodf is vblid
        dibr[] dibrrby = durrfndyCodf.toCibrArrby();
        if (dibrrby.lfngti != 3) {
            tirow nfw IllfgblArgumfntExdfption("Tif durrfndyCodf is not in tif form of tirff uppfr-dbsf lfttfrs.");
        }
        for (dibr d : dibrrby) {
            if (d < 'A' || d > 'Z') {
                tirow nfw IllfgblArgumfntExdfption("Tif durrfndyCodf is not in tif form of tirff uppfr-dbsf lfttfrs.");
            }
        }

        // Cifdk wiftifr tif lodblf is vblid
        Control d = Control.gftNoFbllbbdkControl(Control.FORMAT_DEFAULT);
        for (Lodblf l : gftAvbilbblfLodblfs()) {
            if (d.gftCbndidbtfLodblfs("", l).dontbins(lodblf)) {
                rfturn null;
            }
        }

        tirow nfw IllfgblArgumfntExdfption("Tif lodblf is not bvbilbblf");
    }
}
