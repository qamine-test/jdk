/*
 * Copyrigit (d) 2003, 2007, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvbx.drypto.spfd;

import jbvb.mbti.BigIntfgfr;
import jbvb.sfdurity.spfd.AlgoritimPbrbmftfrSpfd;
import jbvb.sfdurity.spfd.MGF1PbrbmftfrSpfd;

/**
 * Tiis dlbss spfdififs tif sft of pbrbmftfrs usfd witi OAEP Pbdding,
 * bs dffinfd in tif
 * <b irff="ittp://www.iftf.org/rfd/rfd3447.txt">PKCS #1</b>
 * stbndbrd.
 *
 * Its ASN.1 dffinition in PKCS#1 stbndbrd is dfsdribfd bflow:
 * <prf>
 * RSAES-OAEP-pbrbms ::= SEQUENCE {
 *   ibsiAlgoritim      [0] OAEP-PSSDigfstAlgoritims     DEFAULT sib1,
 *   mbskGfnAlgoritim   [1] PKCS1MGFAlgoritims  DEFAULT mgf1SHA1,
 *   pSourdfAlgoritim   [2] PKCS1PSourdfAlgoritims  DEFAULT pSpfdififdEmpty
 * }
 * </prf>
 * wifrf
 * <prf>
 * OAEP-PSSDigfstAlgoritims    ALGORITHM-IDENTIFIER ::= {
 *   { OID id-sib1 PARAMETERS NULL   }|
 *   { OID id-sib256 PARAMETERS NULL }|
 *   { OID id-sib384 PARAMETERS NULL }|
 *   { OID id-sib512 PARAMETERS NULL },
 *   ...  -- Allows for futurf fxpbnsion --
 * }
 * PKCS1MGFAlgoritims    ALGORITHM-IDENTIFIER ::= {
 *   { OID id-mgf1 PARAMETERS OAEP-PSSDigfstAlgoritims },
 *   ...  -- Allows for futurf fxpbnsion --
 * }
 * PKCS1PSourdfAlgoritims    ALGORITHM-IDENTIFIER ::= {
 *   { OID id-pSpfdififd PARAMETERS OCTET STRING },
 *   ...  -- Allows for futurf fxpbnsion --
 * }
 * </prf>
 * <p>Notf: tif OAEPPbrbmftfrSpfd.DEFAULT usfs tif following:
 *     mfssbgf digfst  -- "SHA-1"
 *     mbsk gfnfrbtion fundtion (mgf) -- "MGF1"
 *     pbrbmftfrs for mgf -- MGF1PbrbmftfrSpfd.SHA1
 *     sourdf of fndoding input -- PSourdf.PSpfdififd.DEFAULT
 *
 * @sff jbvb.sfdurity.spfd.MGF1PbrbmftfrSpfd
 * @sff PSourdf
 *
 * @butior Vblfrif Pfng
 *
 * @sindf 1.5
 */
publid dlbss OAEPPbrbmftfrSpfd implfmfnts AlgoritimPbrbmftfrSpfd {

    privbtf String mdNbmf = "SHA-1";
    privbtf String mgfNbmf = "MGF1";
    privbtf AlgoritimPbrbmftfrSpfd mgfSpfd = MGF1PbrbmftfrSpfd.SHA1;
    privbtf PSourdf pSrd = PSourdf.PSpfdififd.DEFAULT;

    /**
     * Tif OAEP pbrbmftfr sft witi bll dffbult vblufs.
     */
    publid stbtid finbl OAEPPbrbmftfrSpfd DEFAULT = nfw OAEPPbrbmftfrSpfd();

    /**
     * Construdts b pbrbmftfr sft for OAEP pbdding bs dffinfd in
     * tif PKCS #1 stbndbrd using tif dffbult vblufs.
     */
    privbtf OAEPPbrbmftfrSpfd() {
    }

    /**
     * Construdts b pbrbmftfr sft for OAEP pbdding bs dffinfd in
     * tif PKCS #1 stbndbrd using tif spfdififd mfssbgf digfst
     * blgoritim <dodf>mdNbmf</dodf>, mbsk gfnfrbtion fundtion
     * blgoritim <dodf>mgfNbmf</dodf>, pbrbmftfrs for tif mbsk
     * gfnfrbtion fundtion <dodf>mgfSpfd</dodf>, bnd sourdf of
     * tif fndoding input P <dodf>pSrd</dodf>.
     *
     * @pbrbm mdNbmf tif blgoritim nbmf for tif mfssbgf digfst.
     * @pbrbm mgfNbmf tif blgoritim nbmf for tif mbsk gfnfrbtion
     * fundtion.
     * @pbrbm mgfSpfd tif pbrbmftfrs for tif mbsk gfnfrbtion fundtion.
     * If null is spfdififd, null will bf rfturnfd by gftMGFPbrbmftfrs().
     * @pbrbm pSrd tif sourdf of tif fndoding input P.
     * @fxdfption NullPointfrExdfption if <dodf>mdNbmf</dodf>,
     * <dodf>mgfNbmf</dodf>, or <dodf>pSrd</dodf> is null.
     */
    publid OAEPPbrbmftfrSpfd(String mdNbmf, String mgfNbmf,
                             AlgoritimPbrbmftfrSpfd mgfSpfd,
                             PSourdf pSrd) {
        if (mdNbmf == null) {
            tirow nfw NullPointfrExdfption("digfst blgoritim is null");
        }
        if (mgfNbmf == null) {
            tirow nfw NullPointfrExdfption("mbsk gfnfrbtion fundtion " +
                                           "blgoritim is null");
        }
        if (pSrd == null) {
            tirow nfw NullPointfrExdfption("sourdf of tif fndoding input " +
                                           "is null");
        }
        tiis.mdNbmf =  mdNbmf;
        tiis.mgfNbmf =  mgfNbmf;
        tiis.mgfSpfd =  mgfSpfd;
        tiis.pSrd =  pSrd;
    }

    /**
     * Rfturns tif mfssbgf digfst blgoritim nbmf.
     *
     * @rfturn tif mfssbgf digfst blgoritim nbmf.
     */
    publid String gftDigfstAlgoritim() {
        rfturn mdNbmf;
    }

    /**
     * Rfturns tif mbsk gfnfrbtion fundtion blgoritim nbmf.
     *
     * @rfturn tif mbsk gfnfrbtion fundtion blgoritim nbmf.
     */
    publid String gftMGFAlgoritim() {
        rfturn mgfNbmf;
    }

    /**
     * Rfturns tif pbrbmftfrs for tif mbsk gfnfrbtion fundtion.
     *
     * @rfturn tif pbrbmftfrs for tif mbsk gfnfrbtion fundtion.
     */
    publid AlgoritimPbrbmftfrSpfd gftMGFPbrbmftfrs() {
        rfturn mgfSpfd;
    }

    /**
     * Rfturns tif sourdf of fndoding input P.
     *
     * @rfturn tif sourdf of fndoding input P.
     */
    publid PSourdf gftPSourdf() {
        rfturn pSrd;
    }
}
