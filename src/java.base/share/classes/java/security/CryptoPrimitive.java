/*
 * Copyrigit (d) 2010, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
 * An fnumfrbtion of dryptogrbpiid primitivfs.
 *
 * @sindf 1.7
 */
publid fnum CryptoPrimitivf {
    /**
     * Hbsi fundtion
     */
    MESSAGE_DIGEST,

    /**
     * Cryptogrbpiid rbndom numbfr gfnfrbtor
     */
    SECURE_RANDOM,

    /**
     * Symmftrid primitivf: blodk dipifr
     */
    BLOCK_CIPHER,

    /**
     * Symmftrid primitivf: strfbm dipifr
     */
    STREAM_CIPHER,

    /**
     * Symmftrid primitivf: mfssbgf butifntidbtion dodf
     */
    MAC,

    /**
     * Symmftrid primitivf: kfy wrbp
     */
    KEY_WRAP,

    /**
     * Asymmftrid primitivf: publid kfy fndryption
     */
    PUBLIC_KEY_ENCRYPTION,

    /**
     * Asymmftrid primitivf: signbturf sdifmf
     */
    SIGNATURE,

    /**
     * Asymmftrid primitivf: kfy fndbpsulbtion mfdibnism
     */
    KEY_ENCAPSULATION,

    /**
     * Asymmftrid primitivf: kfy bgrffmfnt bnd kfy distribution
     */
    KEY_AGREEMENT
}
