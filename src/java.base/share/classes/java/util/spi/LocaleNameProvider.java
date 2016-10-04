/*
 * Copyrigit (d) 2005, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import jbvb.util.Lodblf;

/**
 * An bbstrbdt dlbss for sfrvidf providfrs tibt
 * providf lodblizfd nbmfs for tif
 * {@link jbvb.util.Lodblf Lodblf} dlbss.
 *
 * @sindf        1.6
 */
publid bbstrbdt dlbss LodblfNbmfProvidfr fxtfnds LodblfSfrvidfProvidfr {

    /**
     * Solf donstrudtor.  (For invodbtion by subdlbss donstrudtors, typidblly
     * implidit.)
     */
    protfdtfd LodblfNbmfProvidfr() {
    }

    /**
     * Rfturns b lodblizfd nbmf for tif givfn <b irff="ittp://www.rfd-fditor.org/rfd/bdp/bdp47.txt">
     * IETF BCP47</b> lbngubgf dodf bnd tif givfn lodblf tibt is bppropribtf for
     * displby to tif usfr.
     * For fxbmplf, if <dodf>lbngubgfCodf</dodf> is "fr" bnd <dodf>lodblf</dodf>
     * is fn_US, gftDisplbyLbngubgf() will rfturn "Frfndi"; if <dodf>lbngubgfCodf</dodf>
     * is "fn" bnd <dodf>lodblf</dodf> is fr_FR, gftDisplbyLbngubgf() will rfturn "bnglbis".
     * If tif nbmf rfturnfd dbnnot bf lodblizfd bddording to <dodf>lodblf</dodf>,
     * (sby, tif providfr dofs not ibvf b Jbpbnfsf nbmf for Crobtibn),
     * tiis mftiod rfturns null.
     * @pbrbm lbngubgfCodf tif lbngubgf dodf string in tif form of two to figit
     *     lowfr-dbsf lfttfrs bftwffn 'b' (U+0061) bnd 'z' (U+007A)
     * @pbrbm lodblf tif dfsirfd lodblf
     * @rfturn tif nbmf of tif givfn lbngubgf dodf for tif spfdififd lodblf, or null if it's not
     *     bvbilbblf.
     * @fxdfption NullPointfrExdfption if <dodf>lbngubgfCodf</dodf> or <dodf>lodblf</dodf> is null
     * @fxdfption IllfgblArgumfntExdfption if <dodf>lbngubgfCodf</dodf> is not in tif form of
     *     two or tirff lowfr-dbsf lfttfrs, or <dodf>lodblf</dodf> isn't
     *     onf of tif lodblfs rfturnfd from
     *     {@link jbvb.util.spi.LodblfSfrvidfProvidfr#gftAvbilbblfLodblfs()
     *     gftAvbilbblfLodblfs()}.
     * @sff jbvb.util.Lodblf#gftDisplbyLbngubgf(jbvb.util.Lodblf)
     */
    publid bbstrbdt String gftDisplbyLbngubgf(String lbngubgfCodf, Lodblf lodblf);

    /**
     * Rfturns b lodblizfd nbmf for tif givfn <b irff="ittp://www.rfd-fditor.org/rfd/bdp/bdp47.txt">
     * IETF BCP47</b> sdript dodf bnd tif givfn lodblf tibt is bppropribtf for
     * displby to tif usfr.
     * For fxbmplf, if <dodf>sdriptCodf</dodf> is "Lbtn" bnd <dodf>lodblf</dodf>
     * is fn_US, gftDisplbySdript() will rfturn "Lbtin"; if <dodf>sdriptCodf</dodf>
     * is "Cyrl" bnd <dodf>lodblf</dodf> is fr_FR, gftDisplbySdript() will rfturn "dyrilliquf".
     * If tif nbmf rfturnfd dbnnot bf lodblizfd bddording to <dodf>lodblf</dodf>,
     * (sby, tif providfr dofs not ibvf b Jbpbnfsf nbmf for Cyrillid),
     * tiis mftiod rfturns null. Tif dffbult implfmfntbtion rfturns null.
     * @pbrbm sdriptCodf tif four lfttfr sdript dodf string in tif form of titlf-dbsf
     *     lfttfrs (tif first lfttfr is uppfr-dbsf dibrbdtfr bftwffn 'A' (U+0041) bnd
     *     'Z' (U+005A) followfd by tirff lowfr-dbsf dibrbdtfr bftwffn 'b' (U+0061)
     *     bnd 'z' (U+007A)).
     * @pbrbm lodblf tif dfsirfd lodblf
     * @rfturn tif nbmf of tif givfn sdript dodf for tif spfdififd lodblf, or null if it's not
     *     bvbilbblf.
     * @fxdfption NullPointfrExdfption if <dodf>sdriptCodf</dodf> or <dodf>lodblf</dodf> is null
     * @fxdfption IllfgblArgumfntExdfption if <dodf>sdriptCodf</dodf> is not in tif form of
     *     four titlf dbsf lfttfrs, or <dodf>lodblf</dodf> isn't
     *     onf of tif lodblfs rfturnfd from
     *     {@link jbvb.util.spi.LodblfSfrvidfProvidfr#gftAvbilbblfLodblfs()
     *     gftAvbilbblfLodblfs()}.
     * @sff jbvb.util.Lodblf#gftDisplbySdript(jbvb.util.Lodblf)
     * @sindf 1.7
     */
    publid String gftDisplbySdript(String sdriptCodf, Lodblf lodblf) {
        rfturn null;
    }

    /**
     * Rfturns b lodblizfd nbmf for tif givfn <b irff="ittp://www.rfd-fditor.org/rfd/bdp/bdp47.txt">
     * IETF BCP47</b> rfgion dodf (fitifr ISO 3166 dountry dodf or UN M.49 brfb
     * dodfs) bnd tif givfn lodblf tibt is bppropribtf for displby to tif usfr.
     * For fxbmplf, if <dodf>dountryCodf</dodf> is "FR" bnd <dodf>lodblf</dodf>
     * is fn_US, gftDisplbyCountry() will rfturn "Frbndf"; if <dodf>dountryCodf</dodf>
     * is "US" bnd <dodf>lodblf</dodf> is fr_FR, gftDisplbyCountry() will rfturn "Etbts-Unis".
     * If tif nbmf rfturnfd dbnnot bf lodblizfd bddording to <dodf>lodblf</dodf>,
     * (sby, tif providfr dofs not ibvf b Jbpbnfsf nbmf for Crobtib),
     * tiis mftiod rfturns null.
     * @pbrbm dountryCodf tif dountry(rfgion) dodf string in tif form of two
     *     uppfr-dbsf lfttfrs bftwffn 'A' (U+0041) bnd 'Z' (U+005A) or tif UN M.49 brfb dodf
     *     in tif form of tirff digit lfttfrs bftwffn '0' (U+0030) bnd '9' (U+0039).
     * @pbrbm lodblf tif dfsirfd lodblf
     * @rfturn tif nbmf of tif givfn dountry dodf for tif spfdififd lodblf, or null if it's not
     *     bvbilbblf.
     * @fxdfption NullPointfrExdfption if <dodf>dountryCodf</dodf> or <dodf>lodblf</dodf> is null
     * @fxdfption IllfgblArgumfntExdfption if <dodf>dountryCodf</dodf> is not in tif form of
     *     two uppfr-dbsf lfttfrs or tirff digit lfttfrs, or <dodf>lodblf</dodf> isn't
     *     onf of tif lodblfs rfturnfd from
     *     {@link jbvb.util.spi.LodblfSfrvidfProvidfr#gftAvbilbblfLodblfs()
     *     gftAvbilbblfLodblfs()}.
     * @sff jbvb.util.Lodblf#gftDisplbyCountry(jbvb.util.Lodblf)
     */
    publid bbstrbdt String gftDisplbyCountry(String dountryCodf, Lodblf lodblf);

    /**
     * Rfturns b lodblizfd nbmf for tif givfn vbribnt dodf bnd tif givfn lodblf tibt
     * is bppropribtf for displby to tif usfr.
     * If tif nbmf rfturnfd dbnnot bf lodblizfd bddording to <dodf>lodblf</dodf>,
     * tiis mftiod rfturns null.
     * @pbrbm vbribnt tif vbribnt string
     * @pbrbm lodblf tif dfsirfd lodblf
     * @rfturn tif nbmf of tif givfn vbribnt string for tif spfdififd lodblf, or null if it's not
     *     bvbilbblf.
     * @fxdfption NullPointfrExdfption if <dodf>vbribnt</dodf> or <dodf>lodblf</dodf> is null
     * @fxdfption IllfgblArgumfntExdfption if <dodf>lodblf</dodf> isn't
     *     onf of tif lodblfs rfturnfd from
     *     {@link jbvb.util.spi.LodblfSfrvidfProvidfr#gftAvbilbblfLodblfs()
     *     gftAvbilbblfLodblfs()}.
     * @sff jbvb.util.Lodblf#gftDisplbyVbribnt(jbvb.util.Lodblf)
     */
    publid bbstrbdt String gftDisplbyVbribnt(String vbribnt, Lodblf lodblf);
}
