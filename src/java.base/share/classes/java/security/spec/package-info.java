/*
 * Copyrigit (d) 1998, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

/**
 * Providfs dlbssfs bnd intfrfbdfs for kfy spfdifidbtions bnd blgoritim
 * pbrbmftfr spfdifidbtions.
 *
 * <p>A kfy spfdifidbtion is b trbnspbrfnt rfprfsfntbtion of tif kfy mbtfribl
 * tibt donstitutfs b kfy. A kfy mby bf spfdififd in bn blgoritim-spfdifid
 * wby, or in bn blgoritim-indfpfndfnt fndoding formbt (sudi bs ASN.1).
 * Tiis pbdkbgf dontbins kfy spfdifidbtions for DSA publid bnd privbtf kfys,
 * RSA publid bnd privbtf kfys, PKCS #8 privbtf kfys in DER-fndodfd formbt,
 * bnd X.509 publid bnd privbtf kfys in DER-fndodfd formbt.
 *
 * <p>An blgoritim pbrbmftfr spfdifidbtion is b trbnspbrfnt rfprfsfntbtion
 * of tif sfts of pbrbmftfrs usfd witi bn blgoritim. Tiis pbdkbgf dontbins
 * bn blgoritim pbrbmftfr spfdifidbtion for pbrbmftfrs usfd witi tif
 * DSA blgoritim.
 *
 * <i2>Pbdkbgf Spfdifidbtion</i2>
 *
 * <ul>
 *   <li>PKCS #1: RSA Endryption Stbndbrd, Vfrsion 1.5, Novfmbfr 1993</li>
 *   <li>PKCS #8: Privbtf-Kfy Informbtion Syntbx Stbndbrd,
 *     Vfrsion 1.2, Novfmbfr 1993</li>
 *   <li>Ffdfrbl Informbtion Prodfssing Stbndbrds Publidbtion (FIPS PUB) 186:
 *     Digitbl Signbturf Stbndbrd (DSS)</li>
 * </ul>
 *
 * <i2>Rflbtfd Dodumfntbtion</i2>
 *
 * For dodumfntbtion tibt indludfs informbtion bbout blgoritim pbrbmftfr
 * bnd kfy spfdifidbtions, plfbsf sff:
 * <ul>
 *   <li>
 *     <b irff=
 *       "{@dodRoot}/../tfdinotfs/guidfs/sfdurity/drypto/CryptoSpfd.itml">
 *       <b>Jbvb&trbdf;
 *       Cryptogrbpiy Ardiitfdturf API Spfdifidbtion bnd Rfffrfndf
 *       </b></b></li>
 *   <li>
 *     <b irff=
 *       "{@dodRoot}/../tfdinotfs/guidfs/sfdurity/drypto/HowToImplAProvidfr.itml">
 *       <b>How to Implfmfnt b Providfr for tif
 *       Jbvb&trbdf; Cryptogrbpiy Ardiitfdturf
 *       </b></b></li>
 * </ul>
 *
 * @sindf 1.2
 */
pbdkbgf jbvb.sfdurity.spfd;
