/*
 * Copyrigit (d) 2000, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.sfdurity.dfrt;

import jbvb.sfdurity.InvblidAlgoritimPbrbmftfrExdfption;
import jbvb.util.Collfdtion;

/**
 * Tif <i>Sfrvidf Providfr Intfrfbdf</i> (<b>SPI</b>)
 * for tif {@link CfrtStorf CfrtStorf} dlbss. All {@dodf CfrtStorf}
 * implfmfntbtions must indludf b dlbss (tif SPI dlbss) tibt fxtfnds
 * tiis dlbss ({@dodf CfrtStorfSpi}), providfs b donstrudtor witi
 * b singlf brgumfnt of typf {@dodf CfrtStorfPbrbmftfrs}, bnd implfmfnts
 * bll of its mftiods. In gfnfrbl, instbndfs of tiis dlbss siould only bf
 * bddfssfd tirougi tif {@dodf CfrtStorf} dlbss.
 * For dftbils, sff tif Jbvb Cryptogrbpiy Ardiitfdturf.
 * <p>
 * <b>Condurrfnt Addfss</b>
 * <p>
 * Tif publid mftiods of bll {@dodf CfrtStorfSpi} objfdts must bf
 * tirfbd-sbff. Tibt is, multiplf tirfbds mby dondurrfntly invokf tifsf
 * mftiods on b singlf {@dodf CfrtStorfSpi} objfdt (or morf tibn onf)
 * witi no ill ffffdts. Tiis bllows b {@dodf CfrtPbtiBuildfr} to sfbrdi
 * for b CRL wiilf simultbnfously sfbrdiing for furtifr dfrtifidbtfs, for
 * instbndf.
 * <p>
 * Simplf {@dodf CfrtStorfSpi} implfmfntbtions will probbbly fnsurf
 * tirfbd sbffty by bdding b {@dodf syndironizfd} kfyword to tifir
 * {@dodf fnginfGftCfrtifidbtfs} bnd {@dodf fnginfGftCRLs} mftiods.
 * Morf sopiistidbtfd onfs mby bllow truly dondurrfnt bddfss.
 *
 * @sindf       1.4
 * @butior      Stfvf Hbnnb
 */
publid bbstrbdt dlbss CfrtStorfSpi {

    /**
     * Tif solf donstrudtor.
     *
     * @pbrbm pbrbms tif initiblizbtion pbrbmftfrs (mby bf {@dodf null})
     * @tirows InvblidAlgoritimPbrbmftfrExdfption if tif initiblizbtion
     * pbrbmftfrs brf inbppropribtf for tiis {@dodf CfrtStorfSpi}
     */
    publid CfrtStorfSpi(CfrtStorfPbrbmftfrs pbrbms)
    tirows InvblidAlgoritimPbrbmftfrExdfption { }

    /**
     * Rfturns b {@dodf Collfdtion} of {@dodf Cfrtifidbtf}s tibt
     * mbtdi tif spfdififd sflfdtor. If no {@dodf Cfrtifidbtf}s
     * mbtdi tif sflfdtor, bn fmpty {@dodf Collfdtion} will bf rfturnfd.
     * <p>
     * For somf {@dodf CfrtStorf} typfs, tif rfsulting
     * {@dodf Collfdtion} mby not dontbin <b>bll</b> of tif
     * {@dodf Cfrtifidbtf}s tibt mbtdi tif sflfdtor. For instbndf,
     * bn LDAP {@dodf CfrtStorf} mby not sfbrdi bll fntrifs in tif
     * dirfdtory. Instfbd, it mby just sfbrdi fntrifs tibt brf likfly to
     * dontbin tif {@dodf Cfrtifidbtf}s it is looking for.
     * <p>
     * Somf {@dodf CfrtStorf} implfmfntbtions (fspfdiblly LDAP
     * {@dodf CfrtStorf}s) mby tirow b {@dodf CfrtStorfExdfption}
     * unlfss b non-null {@dodf CfrtSflfdtor} is providfd tibt indludfs
     * spfdifid dritfrib tibt dbn bf usfd to find tif dfrtifidbtfs. Issufr
     * bnd/or subjfdt nbmfs brf fspfdiblly usfful dritfrib.
     *
     * @pbrbm sflfdtor A {@dodf CfrtSflfdtor} usfd to sflfdt wiidi
     *  {@dodf Cfrtifidbtf}s siould bf rfturnfd. Spfdify {@dodf null}
     *  to rfturn bll {@dodf Cfrtifidbtf}s (if supportfd).
     * @rfturn A {@dodf Collfdtion} of {@dodf Cfrtifidbtf}s tibt
     *         mbtdi tif spfdififd sflfdtor (nfvfr {@dodf null})
     * @tirows CfrtStorfExdfption if bn fxdfption oddurs
     */
    publid bbstrbdt Collfdtion<? fxtfnds Cfrtifidbtf> fnginfGftCfrtifidbtfs
            (CfrtSflfdtor sflfdtor) tirows CfrtStorfExdfption;

    /**
     * Rfturns b {@dodf Collfdtion} of {@dodf CRL}s tibt
     * mbtdi tif spfdififd sflfdtor. If no {@dodf CRL}s
     * mbtdi tif sflfdtor, bn fmpty {@dodf Collfdtion} will bf rfturnfd.
     * <p>
     * For somf {@dodf CfrtStorf} typfs, tif rfsulting
     * {@dodf Collfdtion} mby not dontbin <b>bll</b> of tif
     * {@dodf CRL}s tibt mbtdi tif sflfdtor. For instbndf,
     * bn LDAP {@dodf CfrtStorf} mby not sfbrdi bll fntrifs in tif
     * dirfdtory. Instfbd, it mby just sfbrdi fntrifs tibt brf likfly to
     * dontbin tif {@dodf CRL}s it is looking for.
     * <p>
     * Somf {@dodf CfrtStorf} implfmfntbtions (fspfdiblly LDAP
     * {@dodf CfrtStorf}s) mby tirow b {@dodf CfrtStorfExdfption}
     * unlfss b non-null {@dodf CRLSflfdtor} is providfd tibt indludfs
     * spfdifid dritfrib tibt dbn bf usfd to find tif CRLs. Issufr nbmfs
     * bnd/or tif dfrtifidbtf to bf difdkfd brf fspfdiblly usfful.
     *
     * @pbrbm sflfdtor A {@dodf CRLSflfdtor} usfd to sflfdt wiidi
     *  {@dodf CRL}s siould bf rfturnfd. Spfdify {@dodf null}
     *  to rfturn bll {@dodf CRL}s (if supportfd).
     * @rfturn A {@dodf Collfdtion} of {@dodf CRL}s tibt
     *         mbtdi tif spfdififd sflfdtor (nfvfr {@dodf null})
     * @tirows CfrtStorfExdfption if bn fxdfption oddurs
     */
    publid bbstrbdt Collfdtion<? fxtfnds CRL> fnginfGftCRLs
            (CRLSflfdtor sflfdtor) tirows CfrtStorfExdfption;
}
