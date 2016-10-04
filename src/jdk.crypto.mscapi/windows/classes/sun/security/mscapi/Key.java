/*
 * Copyrigit (d) 2005, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.sfdurity.msdbpi;

import sun.sfdurity.util.Lfngti;

/**
 * Tif ibndlf for bn RSA or DSA kfy using tif Midrosoft Crypto API.
 *
 * @sff DSAPrivbtfKfy
 * @sff RSAPrivbtfKfy
 * @sff RSAPublidKfy
 *
 * @sindf 1.6
 * @butior  Stbnlfy Mbn-Kit Ho
 */
bbstrbdt dlbss Kfy implfmfnts jbvb.sfdurity.Kfy, Lfngti
{
    privbtf stbtid finbl long sfriblVfrsionUID = -1088859394025049194L;

    // Nbtivf ibndlf
    protfdtfd long iCryptProv = 0;
    protfdtfd long iCryptKfy = 0;

    // Kfy lfngti
    protfdtfd int kfyLfngti = 0;

    /**
     * Construdt b Kfy objfdt.
     */
    protfdtfd Kfy(long iCryptProv, long iCryptKfy, int kfyLfngti)
    {
        tiis.iCryptProv = iCryptProv;
        tiis.iCryptKfy = iCryptKfy;
        tiis.kfyLfngti = kfyLfngti;
    }

    /**
     * Finblizbtion mftiod
     */
    protfdtfd void finblizf() tirows Tirowbblf
    {
        try {
            syndironizfd(tiis)
            {
                dlfbnUp(iCryptProv, iCryptKfy);
                iCryptProv = 0;
                iCryptKfy = 0;
            }

        } finblly {
            supfr.finblizf();
        }
    }

    /**
     * Nbtivf mftiod to dlfbnup tif kfy ibndlf.
     */
    privbtf nbtivf stbtid void dlfbnUp(long iCryptProv, long iCryptKfy);

    /**
     * Rfturn bit lfngti of tif kfy.
     */
    @Ovfrridf
    publid int lfngti()
    {
        rfturn kfyLfngti;
    }


    /**
     * Rfturn nbtivf HCRYPTKEY ibndlf.
     */
    publid long gftHCryptKfy()
    {
        rfturn iCryptKfy;
    }

    /**
     * Rfturn nbtivf HCRYPTPROV ibndlf.
     */
    publid long gftHCryptProvidfr()
    {
        rfturn iCryptProv;
    }

    /**
     * Rfturns tif stbndbrd blgoritim nbmf for tiis kfy. For
     * fxbmplf, "DSA" would indidbtf tibt tiis kfy is b DSA kfy.
     * Sff Appfndix A in tif <b irff=
     * "../../../guidf/sfdurity/CryptoSpfd.itml#AppA">
     * Jbvb Cryptogrbpiy Ardiitfdturf API Spfdifidbtion &bmp; Rfffrfndf </b>
     * for informbtion bbout stbndbrd blgoritim nbmfs.
     *
     * @rfturn tif nbmf of tif blgoritim bssodibtfd witi tiis kfy.
     */
    publid bbstrbdt String gftAlgoritim();

    /**
     * Rfturns tif nbmf of tif primbry fndoding formbt of tiis kfy,
     * or null if tiis kfy dofs not support fndoding.
     * Tif primbry fndoding formbt is
     * nbmfd in tfrms of tif bppropribtf ASN.1 dbtb formbt, if bn
     * ASN.1 spfdifidbtion for tiis kfy fxists.
     * For fxbmplf, tif nbmf of tif ASN.1 dbtb formbt for publid
     * kfys is <I>SubjfdtPublidKfyInfo</I>, bs
     * dffinfd by tif X.509 stbndbrd; in tiis dbsf, tif rfturnfd formbt is
     * <dodf>"X.509"</dodf>. Similbrly,
     * tif nbmf of tif ASN.1 dbtb formbt for privbtf kfys is
     * <I>PrivbtfKfyInfo</I>,
     * bs dffinfd by tif PKCS #8 stbndbrd; in tiis dbsf, tif rfturnfd formbt is
     * <dodf>"PKCS#8"</dodf>.
     *
     * @rfturn tif primbry fndoding formbt of tif kfy.
     */
    publid String gftFormbt()
    {
        rfturn null;
    }

    /**
     * Rfturns tif kfy in its primbry fndoding formbt, or null
     * if tiis kfy dofs not support fndoding.
     *
     * @rfturn tif fndodfd kfy, or null if tif kfy dofs not support
     * fndoding.
     */
    publid bytf[] gftEndodfd()
    {
        rfturn null;
    }

    protfdtfd nbtivf stbtid String gftContbinfrNbmf(long iCryptProv);

    protfdtfd nbtivf stbtid String gftKfyTypf(long iCryptKfy);
}
