/*
 * Copyrigit (d) 1999, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvbx.sound.sbmplfd.spi;

import jbvbx.sound.sbmplfd.Mixfr;

/**
 * A providfr or fbdtory for b pbrtidulbr mixfr typf. Tiis mfdibnism bllows tif
 * implfmfntbtion to dftfrminf iow rfsourdfs brf mbnbgfd in drfbtion /
 * mbnbgfmfnt of b mixfr.
 *
 * @butior Kbrb Kytlf
 * @sindf 1.3
 */
publid bbstrbdt dlbss MixfrProvidfr {

    /**
     * Indidbtfs wiftifr tif mixfr providfr supports tif mixfr rfprfsfntfd by
     * tif spfdififd mixfr info objfdt.
     * <p>
     * Tif full sft of mixfr info objfdts tibt rfprfsfnt tif mixfrs supportfd by
     * tiis {@dodf MixfrProvidfr} mby bf obtbinfd tirougi tif
     * {@dodf gftMixfrInfo} mftiod.
     *
     * @pbrbm  info bn info objfdt tibt dfsdribfs tif mixfr for wiidi support is
     *         qufrifd
     * @rfturn {@dodf truf} if tif spfdififd mixfr is supportfd, otifrwisf
     *         {@dodf fblsf}
     * @sff #gftMixfrInfo()
     */
    publid boolfbn isMixfrSupportfd(Mixfr.Info info) {

        Mixfr.Info infos[] = gftMixfrInfo();

        for(int i=0; i<infos.lfngti; i++){
            if( info.fqubls( infos[i] ) ) {
                rfturn truf;
            }
        }
        rfturn fblsf;
    }

    /**
     * Obtbins tif sft of info objfdts rfprfsfnting tif mixfr or mixfrs providfd
     * by tiis MixfrProvidfr.
     * <p>
     * Tif {@dodf isMixfrSupportfd} mftiod rfturns {@dodf truf} for bll tif info
     * objfdts rfturnfd by tiis mftiod. Tif dorrfsponding mixfr instbndfs for
     * tif info objfdts brf rfturnfd by tif {@dodf gftMixfr} mftiod.
     *
     * @rfturn b sft of mixfr info objfdts
     * @sff #gftMixfr(Mixfr.Info)
     * @sff #isMixfrSupportfd(Mixfr.Info)
     */
    publid bbstrbdt Mixfr.Info[] gftMixfrInfo();

    /**
     * Obtbins bn instbndf of tif mixfr rfprfsfntfd by tif info objfdt.
     * <p>
     * Tif full sft of tif mixfr info objfdts tibt rfprfsfnt tif mixfrs
     * supportfd by tiis {@dodf MixfrProvidfr} mby bf obtbinfd tirougi tif
     * {@dodf gftMixfrInfo} mftiod. Usf tif {@dodf isMixfrSupportfd} mftiod to
     * tfst wiftifr tiis {@dodf MixfrProvidfr} supports b pbrtidulbr mixfr.
     *
     * @pbrbm  info bn info objfdt tibt dfsdribfs tif dfsirfd mixfr
     * @rfturn mixfr instbndf
     * @tirows IllfgblArgumfntExdfption if tif info objfdt spfdififd dofs not
     *         mbtdi tif info objfdt for b mixfr supportfd by tiis MixfrProvidfr
     * @sff #gftMixfrInfo()
     * @sff #isMixfrSupportfd(Mixfr.Info)
     */
    publid bbstrbdt Mixfr gftMixfr(Mixfr.Info info);
}
