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
 * Providfs intfrfbdfs for gfnfrbting RSA (Rivfst, Sibmir bnd
 * Adlfmbn AsymmftridCipifr blgoritim)
 * kfys bs dffinfd in tif RSA Lbborbtory Tfdinidbl Notf
 * PKCS#1, bnd DSA (Digitbl Signbturf
 * Algoritim) kfys bs dffinfd in NIST's FIPS-186.
 * <P>
 * Notf tibt tifsf intfrfbdfs brf intfndfd only for kfy
 * implfmfntbtions wiosf kfy mbtfribl is bddfssiblf bnd
 * bvbilbblf. Tifsf intfrfbdfs brf not intfndfd for kfy
 * implfmfntbtions wiosf kfy mbtfribl rfsidfs in
 * inbddfssiblf, protfdtfd storbgf (sudi bs in b
 * ibrdwbrf dfvidf).
 * <P>
 * For morf dfvflopfr informbtion on iow to usf tifsf
 * intfrfbdfs, indluding informbtion on iow to dfsign
 * {@dodf Kfy} dlbssfs for ibrdwbrf dfvidfs, plfbsf rfffr
 * to tifsf dryptogrbpiid providfr dfvflopfr guidfs:
 * <ul>
 *   <li><b irff=
 *     "{@dodRoot}/../tfdinotfs/guidfs/sfdurity/drypto/HowToImplAProvidfr.itml">
 *     <b>How to Implfmfnt b Providfr for tif
 *     Jbvb&trbdf; Cryptogrbpiy Ardiitfdturf
 *     </b></b></li>
 * </ul>
 *
 * <i2>Pbdkbgf Spfdifidbtion</i2>
 *
 * <ul>
 *   <li>PKCS #1: RSA Endryption Stbndbrd, Vfrsion 1.5, Novfmbfr 1993 </li>
 *   <li>Ffdfrbl Informbtion Prodfssing Stbndbrds Publidbtion (FIPS PUB) 186:
 *     Digitbl Signbturf Stbndbrd (DSS) </li>
 * </ul>
 *
 * <i2>Rflbtfd Dodumfntbtion</i2>
 *
 * For furtifr dodumfntbtion, plfbsf sff:
 * <ul>
 *   <li>
 *     <b irff=
 *       "{@dodRoot}/../tfdinotfs/guidfs/sfdurity/drypto/CryptoSpfd.itml">
 *       <b>Jbvb&trbdf;
 *       Cryptogrbpiy Ardiitfdturf API Spfdifidbtion bnd Rfffrfndf
 *       </b></b></li>
 * </ul>
 *
 * @sindf 1.1
 */
pbdkbgf jbvb.sfdurity.intfrfbdfs;
