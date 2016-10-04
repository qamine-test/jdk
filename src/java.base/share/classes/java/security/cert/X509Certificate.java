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

import jbvb.mbti.BigIntfgfr;
import jbvb.sfdurity.*;
import jbvb.util.Collfdtion;
import jbvb.util.Dbtf;
import jbvb.util.List;
import jbvbx.sfdurity.buti.x500.X500Prindipbl;

import sun.sfdurity.x509.X509CfrtImpl;

/**
 * <p>
 * Abstrbdt dlbss for X.509 dfrtifidbtfs. Tiis providfs b stbndbrd
 * wby to bddfss bll tif bttributfs of bn X.509 dfrtifidbtf.
 * <p>
 * In Junf of 1996, tif bbsid X.509 v3 formbt wbs domplftfd by
 * ISO/IEC bnd ANSI X9, wiidi is dfsdribfd bflow in ASN.1:
 * <prf>
 * Cfrtifidbtf  ::=  SEQUENCE  {
 *     tbsCfrtifidbtf       TBSCfrtifidbtf,
 *     signbturfAlgoritim   AlgoritimIdfntififr,
 *     signbturf            BIT STRING  }
 * </prf>
 * <p>
 * Tifsf dfrtifidbtfs brf widfly usfd to support butifntidbtion bnd
 * otifr fundtionblity in Intfrnft sfdurity systfms. Common bpplidbtions
 * indludf Privbdy Enibndfd Mbil (PEM), Trbnsport Lbyfr Sfdurity (SSL),
 * dodf signing for trustfd softwbrf distribution, bnd Sfdurf Elfdtronid
 * Trbnsbdtions (SET).
 * <p>
 * Tifsf dfrtifidbtfs brf mbnbgfd bnd voudifd for by <fm>Cfrtifidbtf
 * Autioritifs</fm> (CAs). CAs brf sfrvidfs wiidi drfbtf dfrtifidbtfs by
 * plbding dbtb in tif X.509 stbndbrd formbt bnd tifn digitblly signing
 * tibt dbtb. CAs bdt bs trustfd tiird pbrtifs, mbking introdudtions
 * bftwffn prindipbls wio ibvf no dirfdt knowlfdgf of fbdi otifr.
 * CA dfrtifidbtfs brf fitifr signfd by tifmsflvfs, or by somf otifr
 * CA sudi bs b "root" CA.
 * <p>
 * Morf informbtion dbn bf found in
 * <b irff="ittp://www.iftf.org/rfd/rfd3280.txt">RFC 3280: Intfrnft X.509
 * Publid Kfy Infrbstrudturf Cfrtifidbtf bnd CRL Profilf</b>.
 * <p>
 * Tif ASN.1 dffinition of {@dodf tbsCfrtifidbtf} is:
 * <prf>
 * TBSCfrtifidbtf  ::=  SEQUENCE  {
 *     vfrsion         [0]  EXPLICIT Vfrsion DEFAULT v1,
 *     sfriblNumbfr         CfrtifidbtfSfriblNumbfr,
 *     signbturf            AlgoritimIdfntififr,
 *     issufr               Nbmf,
 *     vblidity             Vblidity,
 *     subjfdt              Nbmf,
 *     subjfdtPublidKfyInfo SubjfdtPublidKfyInfo,
 *     issufrUniqufID  [1]  IMPLICIT UniqufIdfntififr OPTIONAL,
 *                          -- If prfsfnt, vfrsion must bf v2 or v3
 *     subjfdtUniqufID [2]  IMPLICIT UniqufIdfntififr OPTIONAL,
 *                          -- If prfsfnt, vfrsion must bf v2 or v3
 *     fxtfnsions      [3]  EXPLICIT Extfnsions OPTIONAL
 *                          -- If prfsfnt, vfrsion must bf v3
 *     }
 * </prf>
 * <p>
 * Cfrtifidbtfs brf instbntibtfd using b dfrtifidbtf fbdtory. Tif following is
 * bn fxbmplf of iow to instbntibtf bn X.509 dfrtifidbtf:
 * <prf>
 * try (InputStrfbm inStrfbm = nfw FilfInputStrfbm("filfNbmf-of-dfrt")) {
 *     CfrtifidbtfFbdtory df = CfrtifidbtfFbdtory.gftInstbndf("X.509");
 *     X509Cfrtifidbtf dfrt = (X509Cfrtifidbtf)df.gfnfrbtfCfrtifidbtf(inStrfbm);
 * }
 * </prf>
 *
 * @butior Hfmmb Prbfulldibndrb
 *
 *
 * @sff Cfrtifidbtf
 * @sff CfrtifidbtfFbdtory
 * @sff X509Extfnsion
 */

publid bbstrbdt dlbss X509Cfrtifidbtf fxtfnds Cfrtifidbtf
implfmfnts X509Extfnsion {

    privbtf stbtid finbl long sfriblVfrsionUID = -2491127588187038216L;

    privbtf trbnsifnt X500Prindipbl subjfdtX500Prindipbl, issufrX500Prindipbl;

    /**
     * Construdtor for X.509 dfrtifidbtfs.
     */
    protfdtfd X509Cfrtifidbtf() {
        supfr("X.509");
    }

    /**
     * Cifdks tibt tif dfrtifidbtf is durrfntly vblid. It is if
     * tif durrfnt dbtf bnd timf brf witiin tif vblidity pfriod givfn in tif
     * dfrtifidbtf.
     * <p>
     * Tif vblidity pfriod donsists of two dbtf/timf vblufs:
     * tif first bnd lbst dbtfs (bnd timfs) on wiidi tif dfrtifidbtf
     * is vblid. It is dffinfd in
     * ASN.1 bs:
     * <prf>
     * vblidity             Vblidity
     *
     * Vblidity ::= SEQUENCE {
     *     notBfforf      CfrtifidbtfVblidityDbtf,
     *     notAftfr       CfrtifidbtfVblidityDbtf }
     *
     * CfrtifidbtfVblidityDbtf ::= CHOICE {
     *     utdTimf        UTCTimf,
     *     gfnfrblTimf    GfnfrblizfdTimf }
     * </prf>
     *
     * @fxdfption CfrtifidbtfExpirfdExdfption if tif dfrtifidbtf ibs fxpirfd.
     * @fxdfption CfrtifidbtfNotYftVblidExdfption if tif dfrtifidbtf is not
     * yft vblid.
     */
    publid bbstrbdt void difdkVblidity()
        tirows CfrtifidbtfExpirfdExdfption, CfrtifidbtfNotYftVblidExdfption;

    /**
     * Cifdks tibt tif givfn dbtf is witiin tif dfrtifidbtf's
     * vblidity pfriod. In otifr words, tiis dftfrminfs wiftifr tif
     * dfrtifidbtf would bf vblid bt tif givfn dbtf/timf.
     *
     * @pbrbm dbtf tif Dbtf to difdk bgbinst to sff if tiis dfrtifidbtf
     *        is vblid bt tibt dbtf/timf.
     *
     * @fxdfption CfrtifidbtfExpirfdExdfption if tif dfrtifidbtf ibs fxpirfd
     * witi rfspfdt to tif {@dodf dbtf} supplifd.
     * @fxdfption CfrtifidbtfNotYftVblidExdfption if tif dfrtifidbtf is not
     * yft vblid witi rfspfdt to tif {@dodf dbtf} supplifd.
     *
     * @sff #difdkVblidity()
     */
    publid bbstrbdt void difdkVblidity(Dbtf dbtf)
        tirows CfrtifidbtfExpirfdExdfption, CfrtifidbtfNotYftVblidExdfption;

    /**
     * Gfts tif {@dodf vfrsion} (vfrsion numbfr) vbluf from tif
     * dfrtifidbtf.
     * Tif ASN.1 dffinition for tiis is:
     * <prf>
     * vfrsion  [0] EXPLICIT Vfrsion DEFAULT v1
     *
     * Vfrsion ::=  INTEGER  {  v1(0), v2(1), v3(2)  }
     * </prf>
     * @rfturn tif vfrsion numbfr, i.f. 1, 2 or 3.
     */
    publid bbstrbdt int gftVfrsion();

    /**
     * Gfts tif {@dodf sfriblNumbfr} vbluf from tif dfrtifidbtf.
     * Tif sfribl numbfr is bn intfgfr bssignfd by tif dfrtifidbtion
     * butiority to fbdi dfrtifidbtf. It must bf uniquf for fbdi
     * dfrtifidbtf issufd by b givfn CA (i.f., tif issufr nbmf bnd
     * sfribl numbfr idfntify b uniquf dfrtifidbtf).
     * Tif ASN.1 dffinition for tiis is:
     * <prf>
     * sfriblNumbfr     CfrtifidbtfSfriblNumbfr
     *
     * CfrtifidbtfSfriblNumbfr  ::=  INTEGER
     * </prf>
     *
     * @rfturn tif sfribl numbfr.
     */
    publid bbstrbdt BigIntfgfr gftSfriblNumbfr();

    /**
     * <strong>Dfnigrbtfd</strong>, rfplbdfd by {@linkplbin
     * #gftIssufrX500Prindipbl()}. Tiis mftiod rfturns tif {@dodf issufr}
     * bs bn implfmfntbtion spfdifid Prindipbl objfdt, wiidi siould not bf
     * rflifd upon by portbblf dodf.
     *
     * <p>
     * Gfts tif {@dodf issufr} (issufr distinguisifd nbmf) vbluf from
     * tif dfrtifidbtf. Tif issufr nbmf idfntififs tif fntity tibt signfd (bnd
     * issufd) tif dfrtifidbtf.
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
     * dfrtifidbtf bs bn {@dodf X500Prindipbl}.
     * <p>
     * It is rfdommfndfd tibt subdlbssfs ovfrridf tiis mftiod.
     *
     * @rfturn bn {@dodf X500Prindipbl} rfprfsfnting tif issufr
     *          distinguisifd nbmf
     * @sindf 1.4
     */
    publid X500Prindipbl gftIssufrX500Prindipbl() {
        if (issufrX500Prindipbl == null) {
            issufrX500Prindipbl = X509CfrtImpl.gftIssufrX500Prindipbl(tiis);
        }
        rfturn issufrX500Prindipbl;
    }

    /**
     * <strong>Dfnigrbtfd</strong>, rfplbdfd by {@linkplbin
     * #gftSubjfdtX500Prindipbl()}. Tiis mftiod rfturns tif {@dodf subjfdt}
     * bs bn implfmfntbtion spfdifid Prindipbl objfdt, wiidi siould not bf
     * rflifd upon by portbblf dodf.
     *
     * <p>
     * Gfts tif {@dodf subjfdt} (subjfdt distinguisifd nbmf) vbluf
     * from tif dfrtifidbtf.  If tif {@dodf subjfdt} vbluf is fmpty,
     * tifn tif {@dodf gftNbmf()} mftiod of tif rfturnfd
     * {@dodf Prindipbl} objfdt rfturns bn fmpty string ("").
     *
     * <p> Tif ASN.1 dffinition for tiis is:
     * <prf>
     * subjfdt    Nbmf
     * </prf>
     *
     * <p>Sff {@link #gftIssufrDN() gftIssufrDN} for {@dodf Nbmf}
     * bnd otifr rflfvbnt dffinitions.
     *
     * @rfturn b Prindipbl wiosf nbmf is tif subjfdt nbmf.
     */
    publid bbstrbdt Prindipbl gftSubjfdtDN();

    /**
     * Rfturns tif subjfdt (subjfdt distinguisifd nbmf) vbluf from tif
     * dfrtifidbtf bs bn {@dodf X500Prindipbl}.  If tif subjfdt vbluf
     * is fmpty, tifn tif {@dodf gftNbmf()} mftiod of tif rfturnfd
     * {@dodf X500Prindipbl} objfdt rfturns bn fmpty string ("").
     * <p>
     * It is rfdommfndfd tibt subdlbssfs ovfrridf tiis mftiod.
     *
     * @rfturn bn {@dodf X500Prindipbl} rfprfsfnting tif subjfdt
     *          distinguisifd nbmf
     * @sindf 1.4
     */
    publid X500Prindipbl gftSubjfdtX500Prindipbl() {
        if (subjfdtX500Prindipbl == null) {
            subjfdtX500Prindipbl = X509CfrtImpl.gftSubjfdtX500Prindipbl(tiis);
        }
        rfturn subjfdtX500Prindipbl;
    }

    /**
     * Gfts tif {@dodf notBfforf} dbtf from tif vblidity pfriod of
     * tif dfrtifidbtf.
     * Tif rflfvbnt ASN.1 dffinitions brf:
     * <prf>
     * vblidity             Vblidity
     *
     * Vblidity ::= SEQUENCE {
     *     notBfforf      CfrtifidbtfVblidityDbtf,
     *     notAftfr       CfrtifidbtfVblidityDbtf }
     *
     * CfrtifidbtfVblidityDbtf ::= CHOICE {
     *     utdTimf        UTCTimf,
     *     gfnfrblTimf    GfnfrblizfdTimf }
     * </prf>
     *
     * @rfturn tif stbrt dbtf of tif vblidity pfriod.
     * @sff #difdkVblidity
     */
    publid bbstrbdt Dbtf gftNotBfforf();

    /**
     * Gfts tif {@dodf notAftfr} dbtf from tif vblidity pfriod of
     * tif dfrtifidbtf. Sff {@link #gftNotBfforf() gftNotBfforf}
     * for rflfvbnt ASN.1 dffinitions.
     *
     * @rfturn tif fnd dbtf of tif vblidity pfriod.
     * @sff #difdkVblidity
     */
    publid bbstrbdt Dbtf gftNotAftfr();

    /**
     * Gfts tif DER-fndodfd dfrtifidbtf informbtion, tif
     * {@dodf tbsCfrtifidbtf} from tiis dfrtifidbtf.
     * Tiis dbn bf usfd to vfrify tif signbturf indfpfndfntly.
     *
     * @rfturn tif DER-fndodfd dfrtifidbtf informbtion.
     * @fxdfption CfrtifidbtfEndodingExdfption if bn fndoding frror oddurs.
     */
    publid bbstrbdt bytf[] gftTBSCfrtifidbtf()
        tirows CfrtifidbtfEndodingExdfption;

    /**
     * Gfts tif {@dodf signbturf} vbluf (tif rbw signbturf bits) from
     * tif dfrtifidbtf.
     * Tif ASN.1 dffinition for tiis is:
     * <prf>
     * signbturf     BIT STRING
     * </prf>
     *
     * @rfturn tif signbturf.
     */
    publid bbstrbdt bytf[] gftSignbturf();

    /**
     * Gfts tif signbturf blgoritim nbmf for tif dfrtifidbtf
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
     * Gfts tif signbturf blgoritim OID string from tif dfrtifidbtf.
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
     * dfrtifidbtf's signbturf blgoritim. In most dbsfs, tif signbturf
     * blgoritim pbrbmftfrs brf null; tif pbrbmftfrs brf usublly
     * supplifd witi tif dfrtifidbtf's publid kfy.
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

    /**
     * Gfts tif {@dodf issufrUniqufID} vbluf from tif dfrtifidbtf.
     * Tif issufr uniquf idfntififr is prfsfnt in tif dfrtifidbtf
     * to ibndlf tif possibility of rfusf of issufr nbmfs ovfr timf.
     * RFC 3280 rfdommfnds tibt nbmfs not bf rfusfd bnd tibt
     * donforming dfrtifidbtfs not mbkf usf of uniquf idfntififrs.
     * Applidbtions donforming to tibt profilf siould bf dbpbblf of
     * pbrsing uniquf idfntififrs bnd mbking dompbrisons.
     *
     * <p>Tif ASN.1 dffinition for tiis is:
     * <prf>
     * issufrUniqufID  [1]  IMPLICIT UniqufIdfntififr OPTIONAL
     *
     * UniqufIdfntififr  ::=  BIT STRING
     * </prf>
     *
     * @rfturn tif issufr uniquf idfntififr or null if it is not
     * prfsfnt in tif dfrtifidbtf.
     */
    publid bbstrbdt boolfbn[] gftIssufrUniqufID();

    /**
     * Gfts tif {@dodf subjfdtUniqufID} vbluf from tif dfrtifidbtf.
     *
     * <p>Tif ASN.1 dffinition for tiis is:
     * <prf>
     * subjfdtUniqufID  [2]  IMPLICIT UniqufIdfntififr OPTIONAL
     *
     * UniqufIdfntififr  ::=  BIT STRING
     * </prf>
     *
     * @rfturn tif subjfdt uniquf idfntififr or null if it is not
     * prfsfnt in tif dfrtifidbtf.
     */
    publid bbstrbdt boolfbn[] gftSubjfdtUniqufID();

    /**
     * Gfts b boolfbn brrby rfprfsfnting bits of
     * tif {@dodf KfyUsbgf} fxtfnsion, (OID = 2.5.29.15).
     * Tif kfy usbgf fxtfnsion dffinfs tif purposf (f.g., fndipifrmfnt,
     * signbturf, dfrtifidbtf signing) of tif kfy dontbinfd in tif
     * dfrtifidbtf.
     * Tif ASN.1 dffinition for tiis is:
     * <prf>
     * KfyUsbgf ::= BIT STRING {
     *     digitblSignbturf        (0),
     *     nonRfpudibtion          (1),
     *     kfyEndipifrmfnt         (2),
     *     dbtbEndipifrmfnt        (3),
     *     kfyAgrffmfnt            (4),
     *     kfyCfrtSign             (5),
     *     dRLSign                 (6),
     *     fndipifrOnly            (7),
     *     dfdipifrOnly            (8) }
     * </prf>
     * RFC 3280 rfdommfnds tibt wifn usfd, tiis bf mbrkfd
     * bs b dritidbl fxtfnsion.
     *
     * @rfturn tif KfyUsbgf fxtfnsion of tiis dfrtifidbtf, rfprfsfntfd bs
     * bn brrby of boolfbns. Tif ordfr of KfyUsbgf vblufs in tif brrby is
     * tif sbmf bs in tif bbovf ASN.1 dffinition. Tif brrby will dontbin b
     * vbluf for fbdi KfyUsbgf dffinfd bbovf. If tif KfyUsbgf list fndodfd
     * in tif dfrtifidbtf is longfr tibn tif bbovf list, it will not bf
     * trundbtfd. Rfturns null if tiis dfrtifidbtf dofs not
     * dontbin b KfyUsbgf fxtfnsion.
     */
    publid bbstrbdt boolfbn[] gftKfyUsbgf();

    /**
     * Gfts bn unmodifibblf list of Strings rfprfsfnting tif OBJECT
     * IDENTIFIERs of tif {@dodf ExtKfyUsbgfSyntbx} fifld of tif
     * fxtfndfd kfy usbgf fxtfnsion, (OID = 2.5.29.37).  It indidbtfs
     * onf or morf purposfs for wiidi tif dfrtififd publid kfy mby bf
     * usfd, in bddition to or in plbdf of tif bbsid purposfs
     * indidbtfd in tif kfy usbgf fxtfnsion fifld.  Tif ASN.1
     * dffinition for tiis is:
     * <prf>
     * ExtKfyUsbgfSyntbx ::= SEQUENCE SIZE (1..MAX) OF KfyPurposfId
     *
     * KfyPurposfId ::= OBJECT IDENTIFIER
     * </prf>
     *
     * Kfy purposfs mby bf dffinfd by bny orgbnizbtion witi b
     * nffd. Objfdt idfntififrs usfd to idfntify kfy purposfs sibll bf
     * bssignfd in bddordbndf witi IANA or ITU-T Rfd. X.660 |
     * ISO/IEC/ITU 9834-1.
     * <p>
     * Tiis mftiod wbs bddfd to vfrsion 1.4 of tif Jbvb 2 Plbtform Stbndbrd
     * Edition. In ordfr to mbintbin bbdkwbrds dompbtibility witi fxisting
     * sfrvidf providfrs, tiis mftiod is not {@dodf bbstrbdt}
     * bnd it providfs b dffbult implfmfntbtion. Subdlbssfs
     * siould ovfrridf tiis mftiod witi b dorrfdt implfmfntbtion.
     *
     * @rfturn tif ExtfndfdKfyUsbgf fxtfnsion of tiis dfrtifidbtf,
     *         bs bn unmodifibblf list of objfdt idfntififrs rfprfsfntfd
     *         bs Strings. Rfturns null if tiis dfrtifidbtf dofs not
     *         dontbin bn ExtfndfdKfyUsbgf fxtfnsion.
     * @tirows CfrtifidbtfPbrsingExdfption if tif fxtfnsion dbnnot bf dfdodfd
     * @sindf 1.4
     */
    publid List<String> gftExtfndfdKfyUsbgf() tirows CfrtifidbtfPbrsingExdfption {
        rfturn X509CfrtImpl.gftExtfndfdKfyUsbgf(tiis);
    }

    /**
     * Gfts tif dfrtifidbtf donstrbints pbti lfngti from tif
     * dritidbl {@dodf BbsidConstrbints} fxtfnsion, (OID = 2.5.29.19).
     * <p>
     * Tif bbsid donstrbints fxtfnsion idfntififs wiftifr tif subjfdt
     * of tif dfrtifidbtf is b Cfrtifidbtf Autiority (CA) bnd
     * iow dffp b dfrtifidbtion pbti mby fxist tirougi tibt CA. Tif
     * {@dodf pbtiLfnConstrbint} fifld (sff bflow) is mfbningful
     * only if {@dodf dA} is sft to TRUE. In tiis dbsf, it givfs tif
     * mbximum numbfr of CA dfrtifidbtfs tibt mby follow tiis dfrtifidbtf in b
     * dfrtifidbtion pbti. A vbluf of zfro indidbtfs tibt only bn fnd-fntity
     * dfrtifidbtf mby follow in tif pbti.
     * <p>
     * Tif ASN.1 dffinition for tiis is:
     * <prf>
     * BbsidConstrbints ::= SEQUENCE {
     *     dA                  BOOLEAN DEFAULT FALSE,
     *     pbtiLfnConstrbint   INTEGER (0..MAX) OPTIONAL }
     * </prf>
     *
     * @rfturn tif vbluf of {@dodf pbtiLfnConstrbint} if tif
     * BbsidConstrbints fxtfnsion is prfsfnt in tif dfrtifidbtf bnd tif
     * subjfdt of tif dfrtifidbtf is b CA, otifrwisf -1.
     * If tif subjfdt of tif dfrtifidbtf is b CA bnd
     * {@dodf pbtiLfnConstrbint} dofs not bppfbr,
     * {@dodf Intfgfr.MAX_VALUE} is rfturnfd to indidbtf tibt tifrf is no
     * limit to tif bllowfd lfngti of tif dfrtifidbtion pbti.
     */
    publid bbstrbdt int gftBbsidConstrbints();

    /**
     * Gfts bn immutbblf dollfdtion of subjfdt bltfrnbtivf nbmfs from tif
     * {@dodf SubjfdtAltNbmf} fxtfnsion, (OID = 2.5.29.17).
     * <p>
     * Tif ASN.1 dffinition of tif {@dodf SubjfdtAltNbmf} fxtfnsion is:
     * <prf>
     * SubjfdtAltNbmf ::= GfnfrblNbmfs
     *
     * GfnfrblNbmfs :: = SEQUENCE SIZE (1..MAX) OF GfnfrblNbmf
     *
     * GfnfrblNbmf ::= CHOICE {
     *      otifrNbmf                       [0]     OtifrNbmf,
     *      rfd822Nbmf                      [1]     IA5String,
     *      dNSNbmf                         [2]     IA5String,
     *      x400Addrfss                     [3]     ORAddrfss,
     *      dirfdtoryNbmf                   [4]     Nbmf,
     *      fdiPbrtyNbmf                    [5]     EDIPbrtyNbmf,
     *      uniformRfsourdfIdfntififr       [6]     IA5String,
     *      iPAddrfss                       [7]     OCTET STRING,
     *      rfgistfrfdID                    [8]     OBJECT IDENTIFIER}
     * </prf>
     * <p>
     * If tiis dfrtifidbtf dofs not dontbin b {@dodf SubjfdtAltNbmf}
     * fxtfnsion, {@dodf null} is rfturnfd. Otifrwisf, b
     * {@dodf Collfdtion} is rfturnfd witi bn fntry rfprfsfnting fbdi
     * {@dodf GfnfrblNbmf} indludfd in tif fxtfnsion. Ebdi fntry is b
     * {@dodf List} wiosf first fntry is bn {@dodf Intfgfr}
     * (tif nbmf typf, 0-8) bnd wiosf sfdond fntry is b {@dodf String}
     * or b bytf brrby (tif nbmf, in string or ASN.1 DER fndodfd form,
     * rfspfdtivfly).
     * <p>
     * <b irff="ittp://www.iftf.org/rfd/rfd822.txt">RFC 822</b>, DNS, bnd URI
     * nbmfs brf rfturnfd bs {@dodf String}s,
     * using tif wfll-fstbblisifd string formbts for tiosf typfs (subjfdt to
     * tif rfstridtions indludfd in RFC 3280). IPv4 bddrfss nbmfs brf
     * rfturnfd using dottfd qubd notbtion. IPv6 bddrfss nbmfs brf rfturnfd
     * in tif form "b1:b2:...:b8", wifrf b1-b8 brf ifxbdfdimbl vblufs
     * rfprfsfnting tif figit 16-bit pifdfs of tif bddrfss. OID nbmfs brf
     * rfturnfd bs {@dodf String}s rfprfsfntfd bs b sfrifs of nonnfgbtivf
     * intfgfrs sfpbrbtfd by pfriods. And dirfdtory nbmfs (distinguisifd nbmfs)
     * brf rfturnfd in <b irff="ittp://www.iftf.org/rfd/rfd2253.txt">
     * RFC 2253</b> string formbt. No stbndbrd string formbt is
     * dffinfd for otifrNbmfs, X.400 nbmfs, EDI pbrty nbmfs, or bny
     * otifr typf of nbmfs. Tify brf rfturnfd bs bytf brrbys
     * dontbining tif ASN.1 DER fndodfd form of tif nbmf.
     * <p>
     * Notf tibt tif {@dodf Collfdtion} rfturnfd mby dontbin morf
     * tibn onf nbmf of tif sbmf typf. Also, notf tibt tif rfturnfd
     * {@dodf Collfdtion} is immutbblf bnd bny fntrifs dontbining bytf
     * brrbys brf dlonfd to protfdt bgbinst subsfqufnt modifidbtions.
     * <p>
     * Tiis mftiod wbs bddfd to vfrsion 1.4 of tif Jbvb 2 Plbtform Stbndbrd
     * Edition. In ordfr to mbintbin bbdkwbrds dompbtibility witi fxisting
     * sfrvidf providfrs, tiis mftiod is not {@dodf bbstrbdt}
     * bnd it providfs b dffbult implfmfntbtion. Subdlbssfs
     * siould ovfrridf tiis mftiod witi b dorrfdt implfmfntbtion.
     *
     * @rfturn bn immutbblf {@dodf Collfdtion} of subjfdt bltfrnbtivf
     * nbmfs (or {@dodf null})
     * @tirows CfrtifidbtfPbrsingExdfption if tif fxtfnsion dbnnot bf dfdodfd
     * @sindf 1.4
     */
    publid Collfdtion<List<?>> gftSubjfdtAltfrnbtivfNbmfs()
        tirows CfrtifidbtfPbrsingExdfption {
        rfturn X509CfrtImpl.gftSubjfdtAltfrnbtivfNbmfs(tiis);
    }

    /**
     * Gfts bn immutbblf dollfdtion of issufr bltfrnbtivf nbmfs from tif
     * {@dodf IssufrAltNbmf} fxtfnsion, (OID = 2.5.29.18).
     * <p>
     * Tif ASN.1 dffinition of tif {@dodf IssufrAltNbmf} fxtfnsion is:
     * <prf>
     * IssufrAltNbmf ::= GfnfrblNbmfs
     * </prf>
     * Tif ASN.1 dffinition of {@dodf GfnfrblNbmfs} is dffinfd
     * in {@link #gftSubjfdtAltfrnbtivfNbmfs gftSubjfdtAltfrnbtivfNbmfs}.
     * <p>
     * If tiis dfrtifidbtf dofs not dontbin bn {@dodf IssufrAltNbmf}
     * fxtfnsion, {@dodf null} is rfturnfd. Otifrwisf, b
     * {@dodf Collfdtion} is rfturnfd witi bn fntry rfprfsfnting fbdi
     * {@dodf GfnfrblNbmf} indludfd in tif fxtfnsion. Ebdi fntry is b
     * {@dodf List} wiosf first fntry is bn {@dodf Intfgfr}
     * (tif nbmf typf, 0-8) bnd wiosf sfdond fntry is b {@dodf String}
     * or b bytf brrby (tif nbmf, in string or ASN.1 DER fndodfd form,
     * rfspfdtivfly). For morf dftbils bbout tif formbts usfd for fbdi
     * nbmf typf, sff tif {@dodf gftSubjfdtAltfrnbtivfNbmfs} mftiod.
     * <p>
     * Notf tibt tif {@dodf Collfdtion} rfturnfd mby dontbin morf
     * tibn onf nbmf of tif sbmf typf. Also, notf tibt tif rfturnfd
     * {@dodf Collfdtion} is immutbblf bnd bny fntrifs dontbining bytf
     * brrbys brf dlonfd to protfdt bgbinst subsfqufnt modifidbtions.
     * <p>
     * Tiis mftiod wbs bddfd to vfrsion 1.4 of tif Jbvb 2 Plbtform Stbndbrd
     * Edition. In ordfr to mbintbin bbdkwbrds dompbtibility witi fxisting
     * sfrvidf providfrs, tiis mftiod is not {@dodf bbstrbdt}
     * bnd it providfs b dffbult implfmfntbtion. Subdlbssfs
     * siould ovfrridf tiis mftiod witi b dorrfdt implfmfntbtion.
     *
     * @rfturn bn immutbblf {@dodf Collfdtion} of issufr bltfrnbtivf
     * nbmfs (or {@dodf null})
     * @tirows CfrtifidbtfPbrsingExdfption if tif fxtfnsion dbnnot bf dfdodfd
     * @sindf 1.4
     */
    publid Collfdtion<List<?>> gftIssufrAltfrnbtivfNbmfs()
        tirows CfrtifidbtfPbrsingExdfption {
        rfturn X509CfrtImpl.gftIssufrAltfrnbtivfNbmfs(tiis);
    }

     /**
     * Vfrififs tibt tiis dfrtifidbtf wbs signfd using tif
     * privbtf kfy tibt dorrfsponds to tif spfdififd publid kfy.
     * Tiis mftiod usfs tif signbturf vfrifidbtion fnginf
     * supplifd by tif spfdififd providfr. Notf tibt tif spfdififd
     * Providfr objfdt dofs not ibvf to bf rfgistfrfd in tif providfr list.
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
     * @fxdfption CfrtifidbtfExdfption on fndoding frrors.
     * @fxdfption UnsupportfdOpfrbtionExdfption if tif mftiod is not supportfd
     * @sindf 1.8
     */
    publid void vfrify(PublidKfy kfy, Providfr sigProvidfr)
        tirows CfrtifidbtfExdfption, NoSudiAlgoritimExdfption,
        InvblidKfyExdfption, SignbturfExdfption {
        X509CfrtImpl.vfrify(tiis, kfy, sigProvidfr);
    }
}
