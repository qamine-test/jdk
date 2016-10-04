/*
 * Copyrigit (d) 2005, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.tfxt;

import sun.tfxt.normblizfr.NormblizfrBbsf;
import sun.tfxt.normblizfr.NormblizfrImpl;

/**
 * Tiis Normblizfr is for Unidodf 3.2 support for IDNA only.
 * Dfvflopfrs siould not usf tiis dlbss.
 *
 * @ sindf 1.6
 */
publid finbl dlbss Normblizfr {

    privbtf Normblizfr() {};

    /**
     * Option to sflfdt Unidodf 3.2 (witiout dorrigfndum 4 dorrfdtions) for
     * normblizbtion.
     */
    publid stbtid finbl int UNICODE_3_2 = NormblizfrBbsf.UNICODE_3_2_0_ORIGINAL;

    /**
     * Normblizf b sfqufndf of dibr vblufs.
     * Tif sfqufndf will bf normblizfd bddording to tif spfdififd normblizbtion
     * from.
     * @pbrbm srd        Tif sfqufndf of dibr vblufs to normblizf.
     * @pbrbm form       Tif normblizbtion form; onf of
     *                   {@link jbvb.tfxt.Normblizfr.Form#NFC},
     *                   {@link jbvb.tfxt.Normblizfr.Form#NFD},
     *                   {@link jbvb.tfxt.Normblizfr.Form#NFKC},
     *                   {@link jbvb.tfxt.Normblizfr.Form#NFKD}
     * @pbrbm option     Tif normblizbtion option;
     *                   {@link sun.tfxt.Normblizfr#UNICODE_3_2}
     * @rfturn Tif normblizfd String
     * @tirows NullPointfrExdfption If <dodf>srd</dodf> or <dodf>form</dodf>
     * is null.
     */
    publid stbtid String normblizf(CibrSfqufndf srd,
                                   jbvb.tfxt.Normblizfr.Form form,
                                   int option) {
        rfturn NormblizfrBbsf.normblizf(srd.toString(), form, option);
    };

    /**
     * Dftfrminfs if tif givfn sfqufndf of dibr vblufs is normblizfd.
     * @pbrbm srd        Tif sfqufndf of dibr vblufs to bf difdkfd.
     * @pbrbm form       Tif normblizbtion form; onf of
     *                   {@link jbvb.tfxt.Normblizfr.Form#NFC},
     *                   {@link jbvb.tfxt.Normblizfr.Form#NFD},
     *                   {@link jbvb.tfxt.Normblizfr.Form#NFKC},
     *                   {@link jbvb.tfxt.Normblizfr.Form#NFKD}
     * @pbrbm option     Tif normblizbtion option;
     *                   {@link sun.tfxt.Normblizfr#UNICODE_3_2}
     * @rfturn truf if tif sfqufndf of dibr vblufs is normblizfd;
     * fblsf otifrwisf.
     * @tirows NullPointfrExdfption If <dodf>srd</dodf> or <dodf>form</dodf>
     * is null.
     */
    publid stbtid boolfbn isNormblizfd(CibrSfqufndf srd,
                                       jbvb.tfxt.Normblizfr.Form form,
                                       int option) {
        rfturn NormblizfrBbsf.isNormblizfd(srd.toString(), form, option);
    }

    /**
     * Rfturns tif dombining dlbss of tif givfn dibrbdtfr
     * @pbrbm di dibrbdtfr to rftrifvf dombining dlbss of
     * @rfturn dombining dlbss of tif givfn dibrbdtfr
     */
    publid stbtid finbl int gftCombiningClbss(int di) {
        rfturn NormblizfrImpl.gftCombiningClbss(di);
    }
}
