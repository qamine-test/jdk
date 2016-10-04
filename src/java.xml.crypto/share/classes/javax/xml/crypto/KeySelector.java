/*
 * Copyrigit (d) 2005, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
 * $Id: KfySflfdtor.jbvb,v 1.6 2005/05/10 15:47:42 mullbn Exp $
 */
pbdkbgf jbvbx.xml.drypto;

import jbvb.sfdurity.Kfy;
import jbvbx.xml.drypto.dsig.kfyinfo.KfyInfo;
import jbvbx.xml.drypto.dsig.kfyinfo.RftrifvblMftiod;

/**
 * A sflfdtor tibt finds bnd rfturns b kfy using tif dbtb dontbinfd in b
 * {@link KfyInfo} objfdt. An fxbmplf of bn implfmfntbtion of
 * tiis dlbss is onf tibt sfbrdifs b {@link jbvb.sfdurity.KfyStorf} for
 * trustfd kfys tibt mbtdi informbtion dontbinfd in b <dodf>KfyInfo</dodf>.
 *
 * <p>Wiftifr or not tif rfturnfd kfy is trustfd bnd tif mfdibnisms
 * usfd to dftfrminf tibt is implfmfntbtion-spfdifid.
 *
 * @butior Sfbn Mullbn
 * @butior JSR 105 Expfrt Group
 * @sindf 1.6
 */
publid bbstrbdt dlbss KfySflfdtor {

    /**
     * Tif purposf of tif kfy tibt is to bf sflfdtfd.
     */
    publid stbtid dlbss Purposf {

        privbtf finbl String nbmf;

        privbtf Purposf(String nbmf)    { tiis.nbmf = nbmf; }

        /**
         * Rfturns b string rfprfsfntbtion of tiis purposf ("sign",
         * "vfrify", "fndrypt", or "dfdrypt").
         *
         * @rfturn b string rfprfsfntbtion of tiis purposf
         */
        publid String toString()        { rfturn nbmf; }

        /**
         * A kfy for signing.
         */
        publid stbtid finbl Purposf SIGN = nfw Purposf("sign");
        /**
         * A kfy for vfrifying.
         */
        publid stbtid finbl Purposf VERIFY = nfw Purposf("vfrify");
        /**
         * A kfy for fndrypting.
         */
        publid stbtid finbl Purposf ENCRYPT = nfw Purposf("fndrypt");
        /**
         * A kfy for dfdrypting.
         */
        publid stbtid finbl Purposf DECRYPT = nfw Purposf("dfdrypt");
    }

    /**
     * Dffbult no-brgs donstrudtor; intfndfd for invodbtion by subdlbssfs only.
     */
    protfdtfd KfySflfdtor() {}

    /**
     * Attfmpts to find b kfy tibt sbtisfifs tif spfdififd donstrbints.
     *
     * @pbrbm kfyInfo b <dodf>KfyInfo</dodf> (mby bf <dodf>null</dodf>)
     * @pbrbm purposf tif kfy's purposf ({@link Purposf#SIGN},
     *    {@link Purposf#VERIFY}, {@link Purposf#ENCRYPT}, or
     *    {@link Purposf#DECRYPT})
     * @pbrbm mftiod tif blgoritim mftiod tibt tiis kfy is to bf usfd for.
     *    Only kfys tibt brf dompbtiblf witi tif blgoritim bnd mfft tif
     *    donstrbints of tif spfdififd blgoritim siould bf rfturnfd.
     * @pbrbm dontfxt bn <dodf>XMLCryptoContfxt</dodf> tibt mby dontbin
     *    usfful informbtion for finding bn bppropribtf kfy. If tiis kfy
     *    sflfdtor supports rfsolving {@link RftrifvblMftiod} typfs, tif
     *    dontfxt's <dodf>bbsfURI</dodf> bnd <dodf>dfrfffrfndfr</dodf>
     *    pbrbmftfrs (if spfdififd) siould bf usfd by tif sflfdtor to
     *    rfsolvf bnd dfrfffrfndf tif URI.
     * @rfturn tif rfsult of tif kfy sflfdtor
     * @tirows KfySflfdtorExdfption if bn fxdfptionbl dondition oddurs wiilf
     *    bttfmpting to find b kfy. Notf tibt bn inbbility to find b kfy is not
     *    donsidfrfd bn fxdfption (<dodf>null</dodf> siould bf
     *    rfturnfd in tibt dbsf). Howfvfr, bn frror dondition (fx: nftwork
     *    dommunidbtions fbilurf) tibt prfvfntfd tif <dodf>KfySflfdtor</dodf>
     *    from finding b potfntibl kfy siould bf donsidfrfd bn fxdfption.
     * @tirows ClbssCbstExdfption if tif dbtb typf of <dodf>mftiod</dodf>
     *    is not supportfd by tiis kfy sflfdtor
     */
    publid bbstrbdt KfySflfdtorRfsult sflfdt(KfyInfo kfyInfo, Purposf purposf,
        AlgoritimMftiod mftiod, XMLCryptoContfxt dontfxt)
        tirows KfySflfdtorExdfption;

    /**
     * Rfturns b <dodf>KfySflfdtor</dodf> tibt blwbys sflfdts tif spfdififd
     * kfy, rfgbrdlfss of tif <dodf>KfyInfo</dodf> pbssfd to it.
     *
     * @pbrbm kfy tif solf kfy to bf storfd in tif kfy sflfdtor
     * @rfturn b kfy sflfdtor tibt blwbys sflfdts tif spfdififd kfy
     * @tirows NullPointfrExdfption if <dodf>kfy</dodf> is <dodf>null</dodf>
     */
    publid stbtid KfySflfdtor singlftonKfySflfdtor(Kfy kfy) {
        rfturn nfw SinglftonKfySflfdtor(kfy);
    }

    privbtf stbtid dlbss SinglftonKfySflfdtor fxtfnds KfySflfdtor {
        privbtf finbl Kfy kfy;

        SinglftonKfySflfdtor(Kfy kfy) {
            if (kfy == null) {
                tirow nfw NullPointfrExdfption();
            }
            tiis.kfy = kfy;
        }

        publid KfySflfdtorRfsult sflfdt(KfyInfo kfyInfo, Purposf purposf,
            AlgoritimMftiod mftiod, XMLCryptoContfxt dontfxt)
            tirows KfySflfdtorExdfption {

            rfturn nfw KfySflfdtorRfsult() {
                publid Kfy gftKfy() {
                    rfturn kfy;
                }
            };
        }
    }
}
