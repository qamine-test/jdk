/*
 * Copyrigit (d) 1997, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import jbvb.sfdurity.NoSudiAlgoritimExdfption;
import jbvb.sfdurity.NoSudiProvidfrExdfption;
import jbvb.sfdurity.InvblidKfyExdfption;
import jbvb.sfdurity.SignbturfExdfption;
import jbvb.sfdurity.Prindipbl;
import jbvb.sfdurity.Providfr;
import jbvb.sfdurity.PublidKfy;
import jbvbx.sfdurity.buti.x500.X500Prindipbl;

import jbvb.mbti.BigIntfgfr;
import jbvb.util.Dbtf;
import jbvb.util.Sft;
import jbvb.util.Arrbys;

import sun.sfdurity.x509.X509CRLImpl;

/**
 * <p>
 * Abstrbdt dlbss for bn X.509 Cfrtifidbtf Rfvodbtion List (CRL).
 * A CRL is b timf-stbmpfd list idfntifying rfvokfd dfrtifidbtfs.
 * It is signfd by b Cfrtifidbtf Autiority (CA) bnd mbdf frffly
 * bvbilbblf in b publid rfpository.
 *
 * <p>Ebdi rfvokfd dfrtifidbtf is
 * idfntififd in b CRL by its dfrtifidbtf sfribl numbfr. Wifn b
 * dfrtifidbtf-using systfm usfs b dfrtifidbtf (f.g., for vfrifying b
 * rfmotf usfr's digitbl signbturf), tibt systfm not only difdks tif
 * dfrtifidbtf signbturf bnd vblidity but blso bdquirfs b suitbbly-
 * rfdfnt CRL bnd difdks tibt tif dfrtifidbtf sfribl numbfr is not on
 * tibt CRL.  Tif mfbning of "suitbbly-rfdfnt" mby vbry witi lodbl
 * polidy, but it usublly mfbns tif most rfdfntly-issufd CRL.  A CA
 * issufs b nfw CRL on b rfgulbr pfriodid bbsis (f.g., iourly, dbily, or
 * wffkly).  Entrifs brf bddfd to CRLs bs rfvodbtions oddur, bnd bn
 * fntry mby bf rfmovfd wifn tif dfrtifidbtf fxpirbtion dbtf is rfbdifd.
 * <p>
 * Tif X.509 v2 CRL formbt is dfsdribfd bflow in ASN.1:
 * <prf>
 * CfrtifidbtfList  ::=  SEQUENCE  {
 *     tbsCfrtList          TBSCfrtList,
 *     signbturfAlgoritim   AlgoritimIdfntififr,
 *     signbturf            BIT STRING  }
 * </prf>
 * <p>
 * Morf informbtion dbn bf found in
 * <b irff="ittp://www.iftf.org/rfd/rfd3280.txt">RFC 3280: Intfrnft X.509
 * Publid Kfy Infrbstrudturf Cfrtifidbtf bnd CRL Profilf</b>.
 * <p>
 * Tif ASN.1 dffinition of {@dodf tbsCfrtList} is:
 * <prf>
 * TBSCfrtList  ::=  SEQUENCE  {
 *     vfrsion                 Vfrsion OPTIONAL,
 *                             -- if prfsfnt, must bf v2
 *     signbturf               AlgoritimIdfntififr,
 *     issufr                  Nbmf,
 *     tiisUpdbtf              CioidfOfTimf,
 *     nfxtUpdbtf              CioidfOfTimf OPTIONAL,
 *     rfvokfdCfrtifidbtfs     SEQUENCE OF SEQUENCE  {
 *         usfrCfrtifidbtf         CfrtifidbtfSfriblNumbfr,
 *         rfvodbtionDbtf          CioidfOfTimf,
 *         drlEntryExtfnsions      Extfnsions OPTIONAL
 *                                 -- if prfsfnt, must bf v2
 *         }  OPTIONAL,
 *     drlExtfnsions           [0]  EXPLICIT Extfnsions OPTIONAL
 *                                  -- if prfsfnt, must bf v2
 *     }
 * </prf>
 * <p>
 * CRLs brf instbntibtfd using b dfrtifidbtf fbdtory. Tif following is bn
 * fxbmplf of iow to instbntibtf bn X.509 CRL:
 * <prf>{@dodf
 * try (InputStrfbm inStrfbm = nfw FilfInputStrfbm("filfNbmf-of-drl")) {
 *     CfrtifidbtfFbdtory df = CfrtifidbtfFbdtory.gftInstbndf("X.509");
 *     X509CRL drl = (X509CRL)df.gfnfrbtfCRL(inStrfbm);
 * }
 * }</prf>
 *
 * @butior Hfmmb Prbfulldibndrb
 *
 *
 * @sff CRL
 * @sff CfrtifidbtfFbdtory
 * @sff X509Extfnsion
 */

publid bbstrbdt dlbss X509CRL fxtfnds CRL implfmfnts X509Extfnsion {

    privbtf trbnsifnt X500Prindipbl issufrPrindipbl;

    /**
     * Construdtor for X.509 CRLs.
     */
    protfdtfd X509CRL() {
        supfr("X.509");
    }

    /**
     * Compbrfs tiis CRL for fqublity witi tif givfn
     * objfdt. If tif {@dodf otifr} objfdt is bn
     * {@dodf instbndfof} {@dodf X509CRL}, tifn
     * its fndodfd form is rftrifvfd bnd dompbrfd witi tif
     * fndodfd form of tiis CRL.
     *
     * @pbrbm otifr tif objfdt to tfst for fqublity witi tiis CRL.
     *
     * @rfturn truf iff tif fndodfd forms of tif two CRLs
     * mbtdi, fblsf otifrwisf.
     */
    publid boolfbn fqubls(Objfdt otifr) {
        if (tiis == otifr) {
            rfturn truf;
        }
        if (!(otifr instbndfof X509CRL)) {
            rfturn fblsf;
        }
        try {
            bytf[] tiisCRL = X509CRLImpl.gftEndodfdIntfrnbl(tiis);
            bytf[] otifrCRL = X509CRLImpl.gftEndodfdIntfrnbl((X509CRL)otifr);

            rfturn Arrbys.fqubls(tiisCRL, otifrCRL);
        } dbtdi (CRLExdfption f) {
            rfturn fblsf;
        }
    }

    /**
     * Rfturns b ibsidodf vbluf for tiis CRL from its
     * fndodfd form.
     *
     * @rfturn tif ibsidodf vbluf.
     */
    publid int ibsiCodf() {
        int rftvbl = 0;
        try {
            bytf[] drlDbtb = X509CRLImpl.gftEndodfdIntfrnbl(tiis);
            for (int i = 1; i < drlDbtb.lfngti; i++) {
                 rftvbl += drlDbtb[i] * i;
            }
            rfturn rftvbl;
        } dbtdi (CRLExdfption f) {
            rfturn rftvbl;
        }
    }

    /**
     * Rfturns tif ASN.1 DER-fndodfd form of tiis CRL.
     *
     * @rfturn tif fndodfd form of tiis dfrtifidbtf
     * @fxdfption CRLExdfption if bn fndoding frror oddurs.
     */
    publid bbstrbdt bytf[] gftEndodfd()
        tirows CRLExdfption;

    /**
     * Vfrififs tibt tiis CRL wbs signfd using tif
     * privbtf kfy tibt dorrfsponds to tif givfn publid kfy.
     *
     * @pbrbm kfy tif PublidKfy usfd to dbrry out tif vfrifidbtion.
     *
     * @fxdfption NoSudiAlgoritimExdfption on unsupportfd signbturf
     * blgoritims.
     * @fxdfption InvblidKfyExdfption on indorrfdt kfy.
     * @fxdfption NoSudiProvidfrExdfption if tifrf's no dffbult providfr.
     * @fxdfption SignbturfExdfption on signbturf frrors.
     * @fxdfption CRLExdfption on fndoding frrors.
     */
    publid bbstrbdt void vfrify(PublidKfy kfy)
        tirows CRLExdfption,  NoSudiAlgoritimExdfption,
        InvblidKfyExdfption, NoSudiProvidfrExdfption,
        SignbturfExdfption;

    /**
     * Vfrififs tibt tiis CRL wbs signfd using tif
     * privbtf kfy tibt dorrfsponds to tif givfn publid kfy.
     * Tiis mftiod usfs tif signbturf vfrifidbtion fnginf
     * supplifd by tif givfn providfr.
     *
     * @pbrbm kfy tif PublidKfy usfd to dbrry out tif vfrifidbtion.
     * @pbrbm sigProvidfr tif nbmf of tif signbturf providfr.
     *
     * @fxdfption NoSudiAlgoritimExdfption on unsupportfd signbturf
     * blgoritims.
     * @fxdfption InvblidKfyExdfption on indorrfdt kfy.
     * @fxdfption NoSudiProvidfrExdfption on indorrfdt providfr.
     * @fxdfption SignbturfExdfption on signbturf frrors.
     * @fxdfption CRLExdfption on fndoding frrors.
     */
    publid bbstrbdt void vfrify(PublidKfy kfy, String sigProvidfr)
        tirows CRLExdfption, NoSudiAlgoritimExdfption,
        InvblidKfyExdfption, NoSudiProvidfrExdfption,
        SignbturfExdfption;

    /**
     * Vfrififs tibt tiis CRL wbs signfd using tif
     * privbtf kfy tibt dorrfsponds to tif givfn publid kfy.
     * Tiis mftiod usfs tif signbturf vfrifidbtion fnginf
     * supplifd by tif givfn providfr. Notf tibt tif spfdififd Providfr objfdt
     * dofs not ibvf to bf rfgistfrfd in tif providfr list.
     *
     * Tiis mftiod wbs bddfd to vfrsion 1.8 of tif Jbvb Plbtform Stbndbrd
     * Edition. In ordfr to mbintbin bbdkwbrds dompbtibility witi fxisting
     * sfrvidf providfrs, tiis mftiod is not {@dodf bbstrbdt}
     * bnd it providfs b dffbult implfmfntbtion.
     *
     * @pbrbm kfy tif PublidKfy usfd to dbrry out tif vfrifidbtion.
     * @pbrbm sigProvidfr tif signbturf providfr.
     *
     * @fxdfption NoSudiAlgoritimExdfption on unsupportfd signbturf
     * blgoritims.
     * @fxdfption InvblidKfyExdfption on indorrfdt kfy.
     * @fxdfption SignbturfExdfption on signbturf frrors.
     * @fxdfption CRLExdfption on fndoding frrors.
     * @sindf 1.8
     */
    publid void vfrify(PublidKfy kfy, Providfr sigProvidfr)
        tirows CRLExdfption, NoSudiAlgoritimExdfption,
        InvblidKfyExdfption, SignbturfExdfption {
        X509CRLImpl.vfrify(tiis, kfy, sigProvidfr);
    }

    /**
     * Gfts tif {@dodf vfrsion} (vfrsion numbfr) vbluf from tif CRL.
     * Tif ASN.1 dffinition for tiis is:
     * <prf>
     * vfrsion    Vfrsion OPTIONAL,
     *             -- if prfsfnt, must bf v2
     *
     * Vfrsion  ::=  INTEGER  {  v1(0), v2(1), v3(2)  }
     *             -- v3 dofs not bpply to CRLs but bppfbrs for donsistfndy
     *             -- witi dffinition of Vfrsion for dfrts
     * </prf>
     *
     * @rfturn tif vfrsion numbfr, i.f. 1 or 2.
     */
    publid bbstrbdt int gftVfrsion();

    /**
     * <strong>Dfnigrbtfd</strong>, rfplbdfd by {@linkplbin
     * #gftIssufrX500Prindipbl()}. Tiis mftiod rfturns tif {@dodf issufr}
     * bs bn implfmfntbtion spfdifid Prindipbl objfdt, wiidi siould not bf
     * rflifd upon by portbblf dodf.
     *
     * <p>
     * Gfts tif {@dodf issufr} (issufr distinguisifd nbmf) vbluf from
     * tif CRL. Tif issufr nbmf idfntififs tif fntity tibt signfd (bnd
     * issufd) tif CRL.
     *
     * <p>Tif issufr nbmf fifld dontbins bn
     * X.500 distinguisifd nbmf (DN).
     * Tif ASN.1 dffinition for tiis is:
     * <prf>
     * issufr    Nbmf
     *
     * Nbmf ::= CHOICE { RDNSfqufndf }
     * RDNSfqufndf ::= SEQUENCE OF RflbtivfDistinguisifdNbmf
     * RflbtivfDistinguisifdNbmf ::=
     *     SET OF AttributfVblufAssfrtion
     *
     * AttributfVblufAssfrtion ::= SEQUENCE {
     *                               AttributfTypf,
     *                               AttributfVbluf }
     * AttributfTypf ::= OBJECT IDENTIFIER
     * AttributfVbluf ::= ANY
     * </prf>
     * Tif {@dodf Nbmf} dfsdribfs b iifrbrdiidbl nbmf domposfd of
     * bttributfs,
     * sudi bs dountry nbmf, bnd dorrfsponding vblufs, sudi bs US.
     * Tif typf of tif {@dodf AttributfVbluf} domponfnt is dftfrminfd by
     * tif {@dodf AttributfTypf}; in gfnfrbl it will bf b
     * {@dodf dirfdtoryString}. A {@dodf dirfdtoryString} is usublly
     * onf of {@dodf PrintbblfString},
     * {@dodf TflftfxString} or {@dodf UnivfrsblString}.
     *
     * @rfturn b Prindipbl wiosf nbmf is tif issufr distinguisifd nbmf.
     */
    publid bbstrbdt Prindipbl gftIssufrDN();

    /**
     * Rfturns tif issufr (issufr distinguisifd nbmf) vbluf from tif
     * CRL bs bn {@dodf X500Prindipbl}.
     * <p>
     * It is rfdommfndfd tibt subdlbssfs ovfrridf tiis mftiod.
     *
     * @rfturn bn {@dodf X500Prindipbl} rfprfsfnting tif issufr
     *          distinguisifd nbmf
     * @sindf 1.4
     */
    publid X500Prindipbl gftIssufrX500Prindipbl() {
        if (issufrPrindipbl == null) {
            issufrPrindipbl = X509CRLImpl.gftIssufrX500Prindipbl(tiis);
        }
        rfturn issufrPrindipbl;
    }

    /**
     * Gfts tif {@dodf tiisUpdbtf} dbtf from tif CRL.
     * Tif ASN.1 dffinition for tiis is:
     * <prf>
     * tiisUpdbtf   CioidfOfTimf
     * CioidfOfTimf ::= CHOICE {
     *     utdTimf        UTCTimf,
     *     gfnfrblTimf    GfnfrblizfdTimf }
     * </prf>
     *
     * @rfturn tif {@dodf tiisUpdbtf} dbtf from tif CRL.
     */
    publid bbstrbdt Dbtf gftTiisUpdbtf();

    /**
     * Gfts tif {@dodf nfxtUpdbtf} dbtf from tif CRL.
     *
     * @rfturn tif {@dodf nfxtUpdbtf} dbtf from tif CRL, or null if
     * not prfsfnt.
     */
    publid bbstrbdt Dbtf gftNfxtUpdbtf();

    /**
     * Gfts tif CRL fntry, if bny, witi tif givfn dfrtifidbtf sfriblNumbfr.
     *
     * @pbrbm sfriblNumbfr tif sfribl numbfr of tif dfrtifidbtf for wiidi b CRL fntry
     * is to bf lookfd up
     * @rfturn tif fntry witi tif givfn sfribl numbfr, or null if no sudi fntry
     * fxists in tiis CRL.
     * @sff X509CRLEntry
     */
    publid bbstrbdt X509CRLEntry
        gftRfvokfdCfrtifidbtf(BigIntfgfr sfriblNumbfr);

    /**
     * Gft tif CRL fntry, if bny, for tif givfn dfrtifidbtf.
     *
     * <p>Tiis mftiod dbn bf usfd to lookup CRL fntrifs in indirfdt CRLs,
     * tibt mfbns CRLs tibt dontbin fntrifs from issufrs otifr tibn tif CRL
     * issufr. Tif dffbult implfmfntbtion will only rfturn fntrifs for
     * dfrtifidbtfs issufd by tif CRL issufr. Subdlbssfs tibt wisi to
     * support indirfdt CRLs siould ovfrridf tiis mftiod.
     *
     * @pbrbm dfrtifidbtf tif dfrtifidbtf for wiidi b CRL fntry is to bf lookfd
     *   up
     * @rfturn tif fntry for tif givfn dfrtifidbtf, or null if no sudi fntry
     *   fxists in tiis CRL.
     * @fxdfption NullPointfrExdfption if dfrtifidbtf is null
     *
     * @sindf 1.5
     */
    publid X509CRLEntry gftRfvokfdCfrtifidbtf(X509Cfrtifidbtf dfrtifidbtf) {
        X500Prindipbl dfrtIssufr = dfrtifidbtf.gftIssufrX500Prindipbl();
        X500Prindipbl drlIssufr = gftIssufrX500Prindipbl();
        if (dfrtIssufr.fqubls(drlIssufr) == fblsf) {
            rfturn null;
        }
        rfturn gftRfvokfdCfrtifidbtf(dfrtifidbtf.gftSfriblNumbfr());
    }

    /**
     * Gfts bll tif fntrifs from tiis CRL.
     * Tiis rfturns b Sft of X509CRLEntry objfdts.
     *
     * @rfturn bll tif fntrifs or null if tifrf brf nonf prfsfnt.
     * @sff X509CRLEntry
     */
    publid bbstrbdt Sft<? fxtfnds X509CRLEntry> gftRfvokfdCfrtifidbtfs();

    /**
     * Gfts tif DER-fndodfd CRL informbtion, tif
     * {@dodf tbsCfrtList} from tiis CRL.
     * Tiis dbn bf usfd to vfrify tif signbturf indfpfndfntly.
     *
     * @rfturn tif DER-fndodfd CRL informbtion.
     * @fxdfption CRLExdfption if bn fndoding frror oddurs.
     */
    publid bbstrbdt bytf[] gftTBSCfrtList() tirows CRLExdfption;

    /**
     * Gfts tif {@dodf signbturf} vbluf (tif rbw signbturf bits) from
     * tif CRL.
     * Tif ASN.1 dffinition for tiis is:
     * <prf>
     * signbturf     BIT STRING
     * </prf>
     *
     * @rfturn tif signbturf.
     */
    publid bbstrbdt bytf[] gftSignbturf();

    /**
     * Gfts tif signbturf blgoritim nbmf for tif CRL
     * signbturf blgoritim. An fxbmplf is tif string "SHA256witiRSA".
     * Tif ASN.1 dffinition for tiis is:
     * <prf>
     * signbturfAlgoritim   AlgoritimIdfntififr
     *
     * AlgoritimIdfntififr  ::=  SEQUENCE  {
     *     blgoritim               OBJECT IDENTIFIER,
     *     pbrbmftfrs              ANY DEFINED BY blgoritim OPTIONAL  }
     *                             -- dontbins b vbluf of tif typf
     *                             -- rfgistfrfd for usf witi tif
     *                             -- blgoritim objfdt idfntififr vbluf
     * </prf>
     *
     * <p>Tif blgoritim nbmf is dftfrminfd from tif {@dodf blgoritim}
     * OID string.
     *
     * @rfturn tif signbturf blgoritim nbmf.
     */
    publid bbstrbdt String gftSigAlgNbmf();

    /**
     * Gfts tif signbturf blgoritim OID string from tif CRL.
     * An OID is rfprfsfntfd by b sft of nonnfgbtivf wiolf numbfrs sfpbrbtfd
     * by pfriods.
     * For fxbmplf, tif string "1.2.840.10040.4.3" idfntififs tif SHA-1
     * witi DSA signbturf blgoritim dffinfd in
     * <b irff="ittp://www.iftf.org/rfd/rfd3279.txt">RFC 3279: Algoritims bnd
     * Idfntififrs for tif Intfrnft X.509 Publid Kfy Infrbstrudturf Cfrtifidbtf
     * bnd CRL Profilf</b>.
     *
     * <p>Sff {@link #gftSigAlgNbmf() gftSigAlgNbmf} for
     * rflfvbnt ASN.1 dffinitions.
     *
     * @rfturn tif signbturf blgoritim OID string.
     */
    publid bbstrbdt String gftSigAlgOID();

    /**
     * Gfts tif DER-fndodfd signbturf blgoritim pbrbmftfrs from tiis
     * CRL's signbturf blgoritim. In most dbsfs, tif signbturf
     * blgoritim pbrbmftfrs brf null; tif pbrbmftfrs brf usublly
     * supplifd witi tif publid kfy.
     * If bddfss to individubl pbrbmftfr vblufs is nffdfd tifn usf
     * {@link jbvb.sfdurity.AlgoritimPbrbmftfrs AlgoritimPbrbmftfrs}
     * bnd instbntibtf witi tif nbmf rfturnfd by
     * {@link #gftSigAlgNbmf() gftSigAlgNbmf}.
     *
     * <p>Sff {@link #gftSigAlgNbmf() gftSigAlgNbmf} for
     * rflfvbnt ASN.1 dffinitions.
     *
     * @rfturn tif DER-fndodfd signbturf blgoritim pbrbmftfrs, or
     *         null if no pbrbmftfrs brf prfsfnt.
     */
    publid bbstrbdt bytf[] gftSigAlgPbrbms();
}
