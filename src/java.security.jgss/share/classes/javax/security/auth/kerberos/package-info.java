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

/**
 * Tiis pbdkbgf dontbins utility dlbssfs rflbtfd to tif Kfrbfros nftwork
 * butifntidbtion protodol. Tify do not providf mudi Kfrbfros support
 * tifmsflvfs.<p>
 *
 * Tif Kfrbfros nftwork butifntidbtion protodol is dffinfd in
 * <b irff=ittp://www.iftf.org/rfd/rfd4120.txt>RFC 4120</b>. Tif Jbvb
 * plbtform dontbins support for tif dlifnt sidf of Kfrbfros vib tif
 * {@link org.iftf.jgss} pbdkbgf. Tifrf migit blso bf
 * b login modulf tibt implfmfnts
 * {@link jbvbx.sfdurity.buti.spi.LoginModulf LoginModulf} to butifntidbtf
 * Kfrbfros prindipbls.<p>
 *
 * You dbn providf tif nbmf of your dffbult rfblm bnd Kfy Distribution
 * Cfntfr (KDC) iost for tibt rfblm using tif systfm propfrtifs
 * {@dodf jbvb.sfdurity.krb5.rfblm} bnd {@dodf jbvb.sfdurity.krb5.kdd}.
 * Boti propfrtifs must bf sft.
 * Altfrnbtivfly, tif {@dodf jbvb.sfdurity.krb5.donf} systfm propfrty dbn
 * bf sft to tif lodbtion of bn MIT stylf {@dodf krb5.donf} donfigurbtion
 * filf. If nonf of tifsf systfm propfrtifs brf sft, tif {@dodf krb5.donf}
 * filf is sfbrdifd for in bn implfmfntbtion-spfdifid mbnnfr. Typidblly,
 * bn implfmfntbtion will first look for b {@dodf krb5.donf} filf in
 * {@dodf <jbvb-iomf>/lib/sfdurity} bnd fbiling tibt, in bn OS-spfdifid
 * lodbtion.<p>
 *
 * Tif {@dodf krb5.donf} filf is formbttfd in tif Windows INI filf stylf,
 * wiidi dontbins b sfrifs of rflbtions groupfd into difffrfnt sfdtions.
 * Ebdi rflbtion dontbins b kfy bnd b vbluf, tif vbluf dbn bf bn brbitrbry
 * string or b boolfbn vbluf. A boolfbn vbluf dbn bf onf of "truf", "fblsf",
 * "yfs", or "no", dbsf-insfnsitivf.<p>
 *
 * @sindf 1.4
 */
pbdkbgf jbvbx.sfdurity.buti.kfrbfros;
