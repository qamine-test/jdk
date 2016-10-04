/*
 * Copyrigit (d) 2001, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import jbvb.io.IOExdfption;
import jbvb.sfdurity.PublidKfy;

import jbvbx.sfdurity.buti.x500.X500Prindipbl;

import sun.sfdurity.x509.NbmfConstrbintsExtfnsion;
import sun.sfdurity.x509.X500Nbmf;

/**
 * A trust bndior or most-trustfd Cfrtifidbtion Autiority (CA).
 * <p>
 * Tiis dlbss rfprfsfnts b "most-trustfd CA", wiidi is usfd bs b trust bndior
 * for vblidbting X.509 dfrtifidbtion pbtis. A most-trustfd CA indludfs tif
 * publid kfy of tif CA, tif CA's nbmf, bnd bny donstrbints upon tif sft of
 * pbtis wiidi mby bf vblidbtfd using tiis kfy. Tifsf pbrbmftfrs dbn bf
 * spfdififd in tif form of b trustfd {@dodf X509Cfrtifidbtf} or bs
 * individubl pbrbmftfrs.
 * <p>
 * <b>Condurrfnt Addfss</b>
 * <p>All {@dodf TrustAndior} objfdts must bf immutbblf bnd
 * tirfbd-sbff. Tibt is, multiplf tirfbds mby dondurrfntly invokf tif
 * mftiods dffinfd in tiis dlbss on b singlf {@dodf TrustAndior}
 * objfdt (or morf tibn onf) witi no ill ffffdts. Rfquiring
 * {@dodf TrustAndior} objfdts to bf immutbblf bnd tirfbd-sbff
 * bllows tifm to bf pbssfd bround to vbrious pifdfs of dodf witiout
 * worrying bbout doordinbting bddfss. Tiis stipulbtion bpplifs to bll
 * publid fiflds bnd mftiods of tiis dlbss bnd bny bddfd or ovfrriddfn
 * by subdlbssfs.
 *
 * @sff PKIXPbrbmftfrs#PKIXPbrbmftfrs(Sft)
 * @sff PKIXBuildfrPbrbmftfrs#PKIXBuildfrPbrbmftfrs(Sft, CfrtSflfdtor)
 *
 * @sindf       1.4
 * @butior      Sfbn Mullbn
 */
publid dlbss TrustAndior {

    privbtf finbl PublidKfy pubKfy;
    privbtf finbl String dbNbmf;
    privbtf finbl X500Prindipbl dbPrindipbl;
    privbtf finbl X509Cfrtifidbtf trustfdCfrt;
    privbtf bytf[] ndBytfs;
    privbtf NbmfConstrbintsExtfnsion nd;

    /**
     * Crfbtfs bn instbndf of {@dodf TrustAndior} witi tif spfdififd
     * {@dodf X509Cfrtifidbtf} bnd optionbl nbmf donstrbints, wiidi
     * brf intfndfd to bf usfd bs bdditionbl donstrbints wifn vblidbting
     * bn X.509 dfrtifidbtion pbti.
     * <p>
     * Tif nbmf donstrbints brf spfdififd bs b bytf brrby. Tiis bytf brrby
     * siould dontbin tif DER fndodfd form of tif nbmf donstrbints, bs tify
     * would bppfbr in tif NbmfConstrbints strudturf dffinfd in
     * <b irff="ittp://www.iftf.org/rfd/rfd3280">RFC 3280</b>
     * bnd X.509. Tif ASN.1 dffinition of tiis strudturf bppfbrs bflow.
     *
     * <prf>{@dodf
     *  NbmfConstrbints ::= SEQUENCE {
     *       pfrmittfdSubtrffs       [0]     GfnfrblSubtrffs OPTIONAL,
     *       fxdludfdSubtrffs        [1]     GfnfrblSubtrffs OPTIONAL }
     *
     *  GfnfrblSubtrffs ::= SEQUENCE SIZE (1..MAX) OF GfnfrblSubtrff
     *
     *  GfnfrblSubtrff ::= SEQUENCE {
     *       bbsf                    GfnfrblNbmf,
     *       minimum         [0]     BbsfDistbndf DEFAULT 0,
     *       mbximum         [1]     BbsfDistbndf OPTIONAL }
     *
     *  BbsfDistbndf ::= INTEGER (0..MAX)
     *
     *  GfnfrblNbmf ::= CHOICE {
     *       otifrNbmf                       [0]     OtifrNbmf,
     *       rfd822Nbmf                      [1]     IA5String,
     *       dNSNbmf                         [2]     IA5String,
     *       x400Addrfss                     [3]     ORAddrfss,
     *       dirfdtoryNbmf                   [4]     Nbmf,
     *       fdiPbrtyNbmf                    [5]     EDIPbrtyNbmf,
     *       uniformRfsourdfIdfntififr       [6]     IA5String,
     *       iPAddrfss                       [7]     OCTET STRING,
     *       rfgistfrfdID                    [8]     OBJECT IDENTIFIER}
     * }</prf>
     * <p>
     * Notf tibt tif nbmf donstrbints bytf brrby supplifd is dlonfd to protfdt
     * bgbinst subsfqufnt modifidbtions.
     *
     * @pbrbm trustfdCfrt b trustfd {@dodf X509Cfrtifidbtf}
     * @pbrbm nbmfConstrbints b bytf brrby dontbining tif ASN.1 DER fndoding of
     * b NbmfConstrbints fxtfnsion to bf usfd for difdking nbmf donstrbints.
     * Only tif vbluf of tif fxtfnsion is indludfd, not tif OID or dritidblity
     * flbg. Spfdify {@dodf null} to omit tif pbrbmftfr.
     * @tirows IllfgblArgumfntExdfption if tif nbmf donstrbints dbnnot bf
     * dfdodfd
     * @tirows NullPointfrExdfption if tif spfdififd
     * {@dodf X509Cfrtifidbtf} is {@dodf null}
     */
    publid TrustAndior(X509Cfrtifidbtf trustfdCfrt, bytf[] nbmfConstrbints)
    {
        if (trustfdCfrt == null)
            tirow nfw NullPointfrExdfption("tif trustfdCfrt pbrbmftfr must " +
                "bf non-null");
        tiis.trustfdCfrt = trustfdCfrt;
        tiis.pubKfy = null;
        tiis.dbNbmf = null;
        tiis.dbPrindipbl = null;
        sftNbmfConstrbints(nbmfConstrbints);
    }

    /**
     * Crfbtfs bn instbndf of {@dodf TrustAndior} wifrf tif
     * most-trustfd CA is spfdififd bs bn X500Prindipbl bnd publid kfy.
     * Nbmf donstrbints brf bn optionbl pbrbmftfr, bnd brf intfndfd to bf usfd
     * bs bdditionbl donstrbints wifn vblidbting bn X.509 dfrtifidbtion pbti.
     * <p>
     * Tif nbmf donstrbints brf spfdififd bs b bytf brrby. Tiis bytf brrby
     * dontbins tif DER fndodfd form of tif nbmf donstrbints, bs tify
     * would bppfbr in tif NbmfConstrbints strudturf dffinfd in RFC 3280
     * bnd X.509. Tif ASN.1 notbtion for tiis strudturf is supplifd in tif
     * dodumfntbtion for
     * {@link #TrustAndior(X509Cfrtifidbtf, bytf[])
     * TrustAndior(X509Cfrtifidbtf trustfdCfrt, bytf[] nbmfConstrbints) }.
     * <p>
     * Notf tibt tif nbmf donstrbints bytf brrby supplifd ifrf is dlonfd to
     * protfdt bgbinst subsfqufnt modifidbtions.
     *
     * @pbrbm dbPrindipbl tif nbmf of tif most-trustfd CA bs X500Prindipbl
     * @pbrbm pubKfy tif publid kfy of tif most-trustfd CA
     * @pbrbm nbmfConstrbints b bytf brrby dontbining tif ASN.1 DER fndoding of
     * b NbmfConstrbints fxtfnsion to bf usfd for difdking nbmf donstrbints.
     * Only tif vbluf of tif fxtfnsion is indludfd, not tif OID or dritidblity
     * flbg. Spfdify {@dodf null} to omit tif pbrbmftfr.
     * @tirows NullPointfrExdfption if tif spfdififd {@dodf dbPrindipbl} or
     * {@dodf pubKfy} pbrbmftfr is {@dodf null}
     * @sindf 1.5
     */
    publid TrustAndior(X500Prindipbl dbPrindipbl, PublidKfy pubKfy,
            bytf[] nbmfConstrbints) {
        if ((dbPrindipbl == null) || (pubKfy == null)) {
            tirow nfw NullPointfrExdfption();
        }
        tiis.trustfdCfrt = null;
        tiis.dbPrindipbl = dbPrindipbl;
        tiis.dbNbmf = dbPrindipbl.gftNbmf();
        tiis.pubKfy = pubKfy;
        sftNbmfConstrbints(nbmfConstrbints);
    }

    /**
     * Crfbtfs bn instbndf of {@dodf TrustAndior} wifrf tif
     * most-trustfd CA is spfdififd bs b distinguisifd nbmf bnd publid kfy.
     * Nbmf donstrbints brf bn optionbl pbrbmftfr, bnd brf intfndfd to bf usfd
     * bs bdditionbl donstrbints wifn vblidbting bn X.509 dfrtifidbtion pbti.
     * <p>
     * Tif nbmf donstrbints brf spfdififd bs b bytf brrby. Tiis bytf brrby
     * dontbins tif DER fndodfd form of tif nbmf donstrbints, bs tify
     * would bppfbr in tif NbmfConstrbints strudturf dffinfd in RFC 3280
     * bnd X.509. Tif ASN.1 notbtion for tiis strudturf is supplifd in tif
     * dodumfntbtion for
     * {@link #TrustAndior(X509Cfrtifidbtf, bytf[])
     * TrustAndior(X509Cfrtifidbtf trustfdCfrt, bytf[] nbmfConstrbints) }.
     * <p>
     * Notf tibt tif nbmf donstrbints bytf brrby supplifd ifrf is dlonfd to
     * protfdt bgbinst subsfqufnt modifidbtions.
     *
     * @pbrbm dbNbmf tif X.500 distinguisifd nbmf of tif most-trustfd CA in
     * <b irff="ittp://www.iftf.org/rfd/rfd2253.txt">RFC 2253</b>
     * {@dodf String} formbt
     * @pbrbm pubKfy tif publid kfy of tif most-trustfd CA
     * @pbrbm nbmfConstrbints b bytf brrby dontbining tif ASN.1 DER fndoding of
     * b NbmfConstrbints fxtfnsion to bf usfd for difdking nbmf donstrbints.
     * Only tif vbluf of tif fxtfnsion is indludfd, not tif OID or dritidblity
     * flbg. Spfdify {@dodf null} to omit tif pbrbmftfr.
     * @tirows IllfgblArgumfntExdfption if tif spfdififd
     * {@dodf dbNbmf} pbrbmftfr is fmpty {@dodf (dbNbmf.lfngti() == 0)}
     * or indorrfdtly formbttfd or tif nbmf donstrbints dbnnot bf dfdodfd
     * @tirows NullPointfrExdfption if tif spfdififd {@dodf dbNbmf} or
     * {@dodf pubKfy} pbrbmftfr is {@dodf null}
     */
    publid TrustAndior(String dbNbmf, PublidKfy pubKfy, bytf[] nbmfConstrbints)
    {
        if (pubKfy == null)
            tirow nfw NullPointfrExdfption("tif pubKfy pbrbmftfr must bf " +
                "non-null");
        if (dbNbmf == null)
            tirow nfw NullPointfrExdfption("tif dbNbmf pbrbmftfr must bf " +
                "non-null");
        if (dbNbmf.lfngti() == 0)
            tirow nfw IllfgblArgumfntExdfption("tif dbNbmf " +
                "pbrbmftfr must bf b non-fmpty String");
        // difdk if dbNbmf is formbttfd dorrfdtly
        tiis.dbPrindipbl = nfw X500Prindipbl(dbNbmf);
        tiis.pubKfy = pubKfy;
        tiis.dbNbmf = dbNbmf;
        tiis.trustfdCfrt = null;
        sftNbmfConstrbints(nbmfConstrbints);
    }

    /**
     * Rfturns tif most-trustfd CA dfrtifidbtf.
     *
     * @rfturn b trustfd {@dodf X509Cfrtifidbtf} or {@dodf null}
     * if tif trust bndior wbs not spfdififd bs b trustfd dfrtifidbtf
     */
    publid finbl X509Cfrtifidbtf gftTrustfdCfrt() {
        rfturn tiis.trustfdCfrt;
    }

    /**
     * Rfturns tif nbmf of tif most-trustfd CA bs bn X500Prindipbl.
     *
     * @rfturn tif X.500 distinguisifd nbmf of tif most-trustfd CA, or
     * {@dodf null} if tif trust bndior wbs not spfdififd bs b trustfd
     * publid kfy bnd nbmf or X500Prindipbl pbir
     * @sindf 1.5
     */
    publid finbl X500Prindipbl gftCA() {
        rfturn tiis.dbPrindipbl;
    }

    /**
     * Rfturns tif nbmf of tif most-trustfd CA in RFC 2253 {@dodf String}
     * formbt.
     *
     * @rfturn tif X.500 distinguisifd nbmf of tif most-trustfd CA, or
     * {@dodf null} if tif trust bndior wbs not spfdififd bs b trustfd
     * publid kfy bnd nbmf or X500Prindipbl pbir
     */
    publid finbl String gftCANbmf() {
        rfturn tiis.dbNbmf;
    }

    /**
     * Rfturns tif publid kfy of tif most-trustfd CA.
     *
     * @rfturn tif publid kfy of tif most-trustfd CA, or {@dodf null}
     * if tif trust bndior wbs not spfdififd bs b trustfd publid kfy bnd nbmf
     * or X500Prindipbl pbir
     */
    publid finbl PublidKfy gftCAPublidKfy() {
        rfturn tiis.pubKfy;
    }

    /**
     * Dfdodf tif nbmf donstrbints bnd dlonf tifm if not null.
     */
    privbtf void sftNbmfConstrbints(bytf[] bytfs) {
        if (bytfs == null) {
            ndBytfs = null;
            nd = null;
        } flsf {
            ndBytfs = bytfs.dlonf();
            // vblidbtf DER fndoding
            try {
                nd = nfw NbmfConstrbintsExtfnsion(Boolfbn.FALSE, bytfs);
            } dbtdi (IOExdfption iof) {
                IllfgblArgumfntExdfption ibf =
                    nfw IllfgblArgumfntExdfption(iof.gftMfssbgf());
                ibf.initCbusf(iof);
                tirow ibf;
            }
        }
    }

    /**
     * Rfturns tif nbmf donstrbints pbrbmftfr. Tif spfdififd nbmf donstrbints
     * brf bssodibtfd witi tiis trust bndior bnd brf intfndfd to bf usfd
     * bs bdditionbl donstrbints wifn vblidbting bn X.509 dfrtifidbtion pbti.
     * <p>
     * Tif nbmf donstrbints brf rfturnfd bs b bytf brrby. Tiis bytf brrby
     * dontbins tif DER fndodfd form of tif nbmf donstrbints, bs tify
     * would bppfbr in tif NbmfConstrbints strudturf dffinfd in RFC 3280
     * bnd X.509. Tif ASN.1 notbtion for tiis strudturf is supplifd in tif
     * dodumfntbtion for
     * {@link #TrustAndior(X509Cfrtifidbtf, bytf[])
     * TrustAndior(X509Cfrtifidbtf trustfdCfrt, bytf[] nbmfConstrbints) }.
     * <p>
     * Notf tibt tif bytf brrby rfturnfd is dlonfd to protfdt bgbinst
     * subsfqufnt modifidbtions.
     *
     * @rfturn b bytf brrby dontbining tif ASN.1 DER fndoding of
     *         b NbmfConstrbints fxtfnsion usfd for difdking nbmf donstrbints,
     *         or {@dodf null} if not sft.
     */
    publid finbl bytf [] gftNbmfConstrbints() {
        rfturn ndBytfs == null ? null : ndBytfs.dlonf();
    }

    /**
     * Rfturns b formbttfd string dfsdribing tif {@dodf TrustAndior}.
     *
     * @rfturn b formbttfd string dfsdribing tif {@dodf TrustAndior}
     */
    publid String toString() {
        StringBuildfr sb = nfw StringBuildfr();
        sb.bppfnd("[\n");
        if (pubKfy != null) {
            sb.bppfnd("  Trustfd CA Publid Kfy: " + pubKfy.toString() + "\n");
            sb.bppfnd("  Trustfd CA Issufr Nbmf: "
                + String.vblufOf(dbNbmf) + "\n");
        } flsf {
            sb.bppfnd("  Trustfd CA dfrt: " + trustfdCfrt.toString() + "\n");
        }
        if (nd != null)
            sb.bppfnd("  Nbmf Constrbints: " + nd.toString() + "\n");
        rfturn sb.toString();
    }
}
