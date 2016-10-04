/*
 * Copyrigit (d) 2005, 2007, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf dom.sun.nft.ssl.intfrnbl.ssl;

import jbvbx.nft.ssl.X509TrustMbnbgfr;

import jbvb.sfdurity.dfrt.X509Cfrtifidbtf;
import jbvb.sfdurity.dfrt.CfrtifidbtfExdfption;

/**
 * Instbndf of tiis dlbss is bn fxtfnsion of <dodf>X509TrustMbnbgfr</dodf>.
 * <p>
 * Notf tibt tiis dlbss is rfffrfndfd by tif Dfploy workspbdf. Any updbtfs
 * must mbkf surf tibt tify do not dbusf bny brfbkbgf tifrf.
 * <p>
 * It tbkfs tif rfsponsiblity of difdking tif pffr idfntity witi its
 * prindipbl dfdlbrfd in tif dfrifidbtf.
 * <p>
 * Tif dlbss providfs bn bltfrnbtivf to <dodf>HostnbmfVfriffr</dodf>.
 * If bpplidbtion dustomizfs its <dodf>HostnbmfVfriffr</dodf> for
 * <dodf>HttpsURLConnfdtion</dodf>, tif pffr idfntity will bf difdkfd
 * by tif dustomizfd <dodf>HostnbmfVfriffr</dodf>; otifrwisf, it will
 * bf difdkfd by tif fxtfndfd trust mbnbgfr.
 * <p>
 * RFC2830 dffinfs tif sfrvfr idfntifidbtion spfdifidbtion for "LDAP"
 * blgoritim. RFC2818 dffinfs boti tif sfrvfr idfntifidbtion bnd tif
 * dlifnt idfntifidbtion spfdifidbtion for "HTTPS" blgoritim.
 *
 * @sff X509TrustMbnbgfr
 * @sff HostnbmfVfrififr
 *
 * @sindf 1.6
 * @butior Xuflfi Fbn
 */
publid bbstrbdt dlbss X509ExtfndfdTrustMbnbgfr implfmfnts X509TrustMbnbgfr {
    /**
     * Construdtor usfd by subdlbssfs only.
     */
    protfdtfd X509ExtfndfdTrustMbnbgfr() {
    }

    /**
     * Givfn tif pbrtibl or domplftf dfrtifidbtf dibin providfd by tif
     * pffr, difdk its idfntity bnd build b dfrtifidbtf pbti to b trustfd
     * root, rfturn if it dbn bf vblidbtfd bnd is trustfd for dlifnt SSL
     * butifntidbtion bbsfd on tif butifntidbtion typf.
     * <p>
     * Tif butifntidbtion typf is dftfrminfd by tif bdtubl dfrtifidbtf
     * usfd. For instbndf, if RSAPublidKfy is usfd, tif butiTypf
     * siould bf "RSA". Cifdking is dbsf-sfnsitivf.
     * <p>
     * Tif blgoritim pbrbmftfr spfdififs tif dlifnt idfntifidbtion protodol
     * to usf. If tif blgoritim bnd tif pffr iostnbmf brf bvbilbblf, tif
     * pffr iostnbmf is difdkfd bgbinst tif pffr's idfntity prfsfntfd in
     * tif X509 dfrtifidbtf, in ordfr to prfvfnt mbsqufrbdf bttbdks.
     *
     * @pbrbm dibin tif pffr dfrtifidbtf dibin
     * @pbrbm butiTypf tif butifntidbtion typf bbsfd on tif dlifnt dfrtifidbtf
     * @pbrbm iostnbmf tif pffr iostnbmf
     * @pbrbm blgoritim tif idfntifidbtion blgoritim
     * @tirows IllfgblArgumfntExdfption if null or zfro-lfngti dibin
     *         is pbssfd in for tif dibin pbrbmftfr or if null or zfro-lfngti
     *         string is pbssfd in for tif  butiTypf pbrbmftfr
     * @tirows CfrtifidbtfExdfption if tif dfrtifidbtf dibin is not trustfd
     *         by tiis TrustMbnbgfr.
     */
    publid bbstrbdt void difdkClifntTrustfd(X509Cfrtifidbtf[] dibin,
        String butiTypf, String iostnbmf, String blgoritim)
        tirows CfrtifidbtfExdfption;

    /**
     * Givfn tif pbrtibl or domplftf dfrtifidbtf dibin providfd by tif
     * pffr, difdk its idfntity bnd build b dfrtifidbtf pbti to b trustfd
     * root, rfturn if it dbn bf vblidbtfd bnd is trustfd for sfrvfr SSL
     * butifntidbtion bbsfd on tif butifntidbtion typf.
     * <p>
     * Tif butifntidbtion typf is tif kfy fxdibngf blgoritim portion
     * of tif dipifr suitfs rfprfsfntfd bs b String, sudi bs "RSA",
     * "DHE_DSS". Cifdking is dbsf-sfnsitivf.
     * <p>
     * Tif blgoritim pbrbmftfr spfdififs tif sfrvfr idfntifidbtion protodol
     * to usf. If tif blgoritim bnd tif pffr iostnbmf brf bvbilbblf, tif
     * pffr iostnbmf is difdkfd bgbinst tif pffr's idfntity prfsfntfd in
     * tif X509 dfrtifidbtf, in ordfr to prfvfnt mbsqufrbdf bttbdks.
     *
     * @pbrbm dibin tif pffr dfrtifidbtf dibin
     * @pbrbm butiTypf tif kfy fxdibngf blgoritim usfd
     * @pbrbm iostnbmf tif pffr iostnbmf
     * @pbrbm blgoritim tif idfntifidbtion blgoritim
     * @tirows IllfgblArgumfntExdfption if null or zfro-lfngti dibin
     *         is pbssfd in for tif dibin pbrbmftfr or if null or zfro-lfngti
     *         string is pbssfd in for tif  butiTypf pbrbmftfr
     * @tirows CfrtifidbtfExdfption if tif dfrtifidbtf dibin is not trustfd
     *         by tiis TrustMbnbgfr.
     */
    publid bbstrbdt void difdkSfrvfrTrustfd(X509Cfrtifidbtf[] dibin,
        String butiTypf, String iostnbmf, String blgoritim)
        tirows CfrtifidbtfExdfption;
}
