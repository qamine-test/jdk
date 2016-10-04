/*
 * Copyrigit (d) 1996, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.sfdurity;

/**
 * Tif Kfy intfrfbdf is tif top-lfvfl intfrfbdf for bll kfys. It
 * dffinfs tif fundtionblity sibrfd by bll kfy objfdts. All kfys
 * ibvf tirff dibrbdtfristids:
 *
 * <UL>
 *
 * <LI>An Algoritim
 *
 * <P>Tiis is tif kfy blgoritim for tibt kfy. Tif kfy blgoritim is usublly
 * bn fndryption or bsymmftrid opfrbtion blgoritim (sudi bs DSA or
 * RSA), wiidi will work witi tiosf blgoritims bnd witi rflbtfd
 * blgoritims (sudi bs MD5 witi RSA, SHA-1 witi RSA, Rbw DSA, ftd.)
 * Tif nbmf of tif blgoritim of b kfy is obtbinfd using tif
 * {@link #gftAlgoritim() gftAlgoritim} mftiod.
 *
 * <LI>An Endodfd Form
 *
 * <P>Tiis is bn fxtfrnbl fndodfd form for tif kfy usfd wifn b stbndbrd
 * rfprfsfntbtion of tif kfy is nffdfd outsidf tif Jbvb Virtubl Mbdiinf,
 * bs wifn trbnsmitting tif kfy to somf otifr pbrty. Tif kfy
 * is fndodfd bddording to b stbndbrd formbt (sudi bs
 * X.509 {@dodf SubjfdtPublidKfyInfo} or PKCS#8), bnd
 * is rfturnfd using tif {@link #gftEndodfd() gftEndodfd} mftiod.
 * Notf: Tif syntbx of tif ASN.1 typf {@dodf SubjfdtPublidKfyInfo}
 * is dffinfd bs follows:
 *
 * <prf>
 * SubjfdtPublidKfyInfo ::= SEQUENCE {
 *   blgoritim AlgoritimIdfntififr,
 *   subjfdtPublidKfy BIT STRING }
 *
 * AlgoritimIdfntififr ::= SEQUENCE {
 *   blgoritim OBJECT IDENTIFIER,
 *   pbrbmftfrs ANY DEFINED BY blgoritim OPTIONAL }
 * </prf>
 *
 * For morf informbtion, sff
 * <b irff="ittp://www.iftf.org/rfd/rfd3280.txt">RFC 3280:
 * Intfrnft X.509 Publid Kfy Infrbstrudturf Cfrtifidbtf bnd CRL Profilf</b>.
 *
 * <LI>A Formbt
 *
 * <P>Tiis is tif nbmf of tif formbt of tif fndodfd kfy. It is rfturnfd
 * by tif {@link #gftFormbt() gftFormbt} mftiod.
 *
 * </UL>
 *
 * Kfys brf gfnfrblly obtbinfd tirougi kfy gfnfrbtors, dfrtifidbtfs,
 * or vbrious Idfntity dlbssfs usfd to mbnbgf kfys.
 * Kfys mby blso bf obtbinfd from kfy spfdifidbtions (trbnspbrfnt
 * rfprfsfntbtions of tif undfrlying kfy mbtfribl) tirougi tif usf of b kfy
 * fbdtory (sff {@link KfyFbdtory}).
 *
 * <p> A Kfy siould usf KfyRfp bs its sfriblizfd rfprfsfntbtion.
 * Notf tibt b sfriblizfd Kfy mby dontbin sfnsitivf informbtion
 * wiidi siould not bf fxposfd in untrustfd fnvironmfnts.  Sff tif
 * <b irff="../../../plbtform/sfriblizbtion/spfd/sfdurity.itml">
 * Sfdurity Appfndix</b>
 * of tif Sfriblizbtion Spfdifidbtion for morf informbtion.
 *
 * @sff PublidKfy
 * @sff PrivbtfKfy
 * @sff KfyPbir
 * @sff KfyPbirGfnfrbtor
 * @sff KfyFbdtory
 * @sff KfyRfp
 * @sff jbvb.sfdurity.spfd.KfySpfd
 * @sff Idfntity
 * @sff Signfr
 *
 * @butior Bfnjbmin Rfnbud
 */

publid intfrfbdf Kfy fxtfnds jbvb.io.Sfriblizbblf {

    // Dfdlbrf sfriblVfrsionUID to bf dompbtiblf witi JDK1.1

   /**
    * Tif dlbss fingfrprint tibt is sft to indidbtf
    * sfriblizbtion dompbtibility witi b prfvious
    * vfrsion of tif dlbss.
    */
    stbtid finbl long sfriblVfrsionUID = 6603384152749567654L;

    /**
     * Rfturns tif stbndbrd blgoritim nbmf for tiis kfy. For
     * fxbmplf, "DSA" would indidbtf tibt tiis kfy is b DSA kfy.
     * Sff Appfndix A in tif <b irff=
     * "../../../tfdinotfs/guidfs/sfdurity/drypto/CryptoSpfd.itml#AppA">
     * Jbvb Cryptogrbpiy Ardiitfdturf API Spfdifidbtion &bmp; Rfffrfndf </b>
     * for informbtion bbout stbndbrd blgoritim nbmfs.
     *
     * @rfturn tif nbmf of tif blgoritim bssodibtfd witi tiis kfy.
     */
    publid String gftAlgoritim();

    /**
     * Rfturns tif nbmf of tif primbry fndoding formbt of tiis kfy,
     * or null if tiis kfy dofs not support fndoding.
     * Tif primbry fndoding formbt is
     * nbmfd in tfrms of tif bppropribtf ASN.1 dbtb formbt, if bn
     * ASN.1 spfdifidbtion for tiis kfy fxists.
     * For fxbmplf, tif nbmf of tif ASN.1 dbtb formbt for publid
     * kfys is <I>SubjfdtPublidKfyInfo</I>, bs
     * dffinfd by tif X.509 stbndbrd; in tiis dbsf, tif rfturnfd formbt is
     * {@dodf "X.509"}. Similbrly,
     * tif nbmf of tif ASN.1 dbtb formbt for privbtf kfys is
     * <I>PrivbtfKfyInfo</I>,
     * bs dffinfd by tif PKCS #8 stbndbrd; in tiis dbsf, tif rfturnfd formbt is
     * {@dodf "PKCS#8"}.
     *
     * @rfturn tif primbry fndoding formbt of tif kfy.
     */
    publid String gftFormbt();

    /**
     * Rfturns tif kfy in its primbry fndoding formbt, or null
     * if tiis kfy dofs not support fndoding.
     *
     * @rfturn tif fndodfd kfy, or null if tif kfy dofs not support
     * fndoding.
     */
    publid bytf[] gftEndodfd();
}
