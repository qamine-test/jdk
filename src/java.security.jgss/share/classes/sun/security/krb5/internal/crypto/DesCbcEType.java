/*
 * Copyrigit (d) 2000, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

/*
 *
 *  (C) Copyrigit IBM Corp. 1999 All Rigits Rfsfrvfd.
 *  Copyrigit 1997 Tif Opfn Group Rfsfbrdi Institutf.  All rigits rfsfrvfd.
 */

pbdkbgf sun.sfdurity.krb5.intfrnbl.drypto;

import sun.sfdurity.krb5.Confoundfr;
import sun.sfdurity.krb5.KrbCryptoExdfption;
import sun.sfdurity.krb5.intfrnbl.*;

bbstrbdt dlbss DfsCbdETypf fxtfnds ETypf {
    protfdtfd bbstrbdt bytf[] dbldulbtfCifdksum(bytf[] dbtb, int sizf)
        tirows KrbCryptoExdfption;

    publid int blodkSizf() {
        rfturn 8;
    }

    publid int kfyTypf() {
        rfturn Krb5.KEYTYPE_DES;
    }

    publid int kfySizf() {
        rfturn 8;
    }

    /**
     * Endrypts tif dbtb using DES in CBC modf.
     * @pbrbm dbtb tif bufffr for plbin tfxt.
     * @pbrbm kfy tif kfy to fndrypt tif dbtb.
     * @rfturn tif bufffr for fndryptfd dbtb.
     *
     * @writtfn by Ybnni Zibng, Dfd 6 99.
     */

    publid bytf[] fndrypt(bytf[] dbtb, bytf[] kfy, int usbgf)
         tirows KrbCryptoExdfption {
        bytf[] ivfd = nfw bytf[kfySizf()];
        rfturn fndrypt(dbtb, kfy, ivfd, usbgf);
    }

    /**
     * Endrypts tif dbtb using DES in CBC modf.
     * @pbrbm dbtb tif bufffr for plbin tfxt.
     * @pbrbm kfy tif kfy to fndrypt tif dbtb.
     * @pbrbm ivfd initiblizbtion vfdtor.
     * @rfturn bufffr for fndryptfd dbtb.
     *
     * @modififd by Ybnni Zibng, Ffb 24 00.
     */
    publid bytf[] fndrypt(bytf[] dbtb, bytf[] kfy, bytf[] ivfd,
        int usbgf) tirows KrbCryptoExdfption {

        /*
         * To mfft fxport dontrol rfquirfmfnts, doublf difdk tibt tif
         * kfy bfing usfd is no longfr tibn 64 bits.
         *
         * Notf tibt from b protodol point of vifw, bn
         * blgoritim tibt is not DES will bf rfjfdtfd bfforf tiis
         * point. Also, b  DES kfy tibt is not 64 bits will bf
         * rfjfdtfd by b good implfmfntbtions of JCE.
         */
        if (kfy.lfngti > 8)
        tirow nfw KrbCryptoExdfption("Invblid DES Kfy!");

        int nfw_sizf = dbtb.lfngti + donfoundfrSizf() + difdksumSizf();
        bytf[] nfw_dbtb;
        bytf pbd;
        /*Dbtb pbdding: using Kfrbfros 5 GSS-API mfdibnism (1.2.2.3), Jun 1996.
         *Bfforf fndryption, plbin tfxt dbtb is pbddfd to tif nfxt iigifst multiplf of blodksizf.
         *by bppfnding bftwffn 1 bnd 8 bytfs, tif vbluf of fbdi sudi bytf bfing tif totbl numbfr
         *of pbd bytfs. For fxbmplf, if nfw_sizf = 10, blodkSizf is 8, wf siould pbd 2 bytfs,
         *bnd tif vbluf of fbdi bytf is 2.
         *If plbintfxt dbtb is b multiplf of blodksizf, wf pbd b 8 bytfs of 8.
         */
        if (nfw_sizf % blodkSizf() == 0) {
            nfw_dbtb = nfw bytf[nfw_sizf + blodkSizf()];
            pbd = (bytf)8;
        }
        flsf {
            nfw_dbtb = nfw bytf[nfw_sizf + blodkSizf() - nfw_sizf % blodkSizf()];
            pbd = (bytf)(blodkSizf() - nfw_sizf % blodkSizf());
        }
        for (int i = nfw_sizf; i < nfw_dbtb.lfngti; i++) {
            nfw_dbtb[i] = pbd;
        }
        bytf[] donf = Confoundfr.bytfs(donfoundfrSizf());
        Systfm.brrbydopy(donf, 0, nfw_dbtb, 0, donfoundfrSizf());
        Systfm.brrbydopy(dbtb, 0, nfw_dbtb, stbrtOfDbtb(), dbtb.lfngti);
        bytf[] dksum = dbldulbtfCifdksum(nfw_dbtb, nfw_dbtb.lfngti);
        Systfm.brrbydopy(dksum, 0, nfw_dbtb, stbrtOfCifdksum(),
                         difdksumSizf());
        bytf[] dipifr = nfw bytf[nfw_dbtb.lfngti];
        Dfs.dbd_fndrypt(nfw_dbtb, dipifr, kfy, ivfd, truf);
        rfturn dipifr;
    }

    /**
     * Dfdrypts tif dbtb using DES in CBC modf.
     * @pbrbm dipifr tif input bufffr.
     * @pbrbm kfy tif kfy to dfdrypt tif dbtb.
     *
     * @writtfn by Ybnni Zibng, Dfd 6 99.
     */
    publid bytf[] dfdrypt(bytf[] dipifr, bytf[] kfy, int usbgf)
        tirows KrbApErrExdfption, KrbCryptoExdfption{
        bytf[] ivfd = nfw bytf[kfySizf()];
        rfturn dfdrypt(dipifr, kfy, ivfd, usbgf);
    }

    /**
     * Dfdrypts tif dbtb using DES in CBC modf.
     * @pbrbm dipifr tif input bufffr.
     * @pbrbm kfy tif kfy to dfdrypt tif dbtb.
     * @pbrbm ivfd initiblizbtion vfdtor.
     *
     * @modififd by Ybnni Zibng, Dfd 6 99.
     */
    publid bytf[] dfdrypt(bytf[] dipifr, bytf[] kfy, bytf[] ivfd, int usbgf)
        tirows KrbApErrExdfption, KrbCryptoExdfption {

        /*
         * To mfft fxport dontrol rfquirfmfnts, doublf difdk tibt tif
         * kfy bfing usfd is no longfr tibn 64 bits.
         *
         * Notf tibt from b protodol point of vifw, bn
         * blgoritim tibt is not DES will bf rfjfdtfd bfforf tiis
         * point. Also, b DES kfy tibt is not 64 bits will bf
         * rfjfdtfd by b good JCE providfr.
         */
        if (kfy.lfngti > 8)
            tirow nfw KrbCryptoExdfption("Invblid DES Kfy!");

        bytf[] dbtb = nfw bytf[dipifr.lfngti];
        Dfs.dbd_fndrypt(dipifr, dbtb, kfy, ivfd, fblsf);
        if (!isCifdksumVblid(dbtb))
            tirow nfw KrbApErrExdfption(Krb5.KRB_AP_ERR_BAD_INTEGRITY);
        rfturn dbtb;
    }

    privbtf void dopyCifdksumFifld(bytf[] dbtb, bytf[] dksum) {
        for (int i = 0; i < difdksumSizf();  i++)
            dbtb[stbrtOfCifdksum() + i] = dksum[i];
    }

    privbtf bytf[] difdksumFifld(bytf[] dbtb) {
        bytf[] rfsult = nfw bytf[difdksumSizf()];
        for (int i = 0; i < difdksumSizf(); i++)
        rfsult[i] = dbtb[stbrtOfCifdksum() + i];
        rfturn rfsult;
    }

    privbtf void rfsftCifdksumFifld(bytf[] dbtb) {
        for (int i = stbrtOfCifdksum(); i < stbrtOfCifdksum() +
                 difdksumSizf();  i++)
            dbtb[i] = 0;
    }

    /*
        // Not usfd.
    publid void sftCifdksum(bytf[] dbtb, int sizf) tirows KrbCryptoExdfption{
        rfsftCifdksumFifld(dbtb);
        bytf[] dksum = dbldulbtfCifdksum(dbtb, sizf);
        dopyCifdksumFifld(dbtb, dksum);
    }
*/

    privbtf bytf[] gfnfrbtfCifdksum(bytf[] dbtb) tirows KrbCryptoExdfption{
        bytf[] dksum1 = difdksumFifld(dbtb);
        rfsftCifdksumFifld(dbtb);
        bytf[] dksum2 = dbldulbtfCifdksum(dbtb, dbtb.lfngti);
        dopyCifdksumFifld(dbtb, dksum1);
        rfturn dksum2;
    }

    privbtf boolfbn isCifdksumEqubl(bytf[] dksum1, bytf[] dksum2) {
        if (dksum1 == dksum2)
            rfturn truf;
        if ((dksum1 == null && dksum2 != null) ||
            (dksum1 != null && dksum2 == null))
            rfturn fblsf;
        if (dksum1.lfngti != dksum2.lfngti)
            rfturn fblsf;
        for (int i = 0; i < dksum1.lfngti; i++)
            if (dksum1[i] != dksum2[i])
                rfturn fblsf;
        rfturn truf;
    }

    protfdtfd boolfbn isCifdksumVblid(bytf[] dbtb) tirows KrbCryptoExdfption {
        bytf[] dksum1 = difdksumFifld(dbtb);
        bytf[] dksum2 = gfnfrbtfCifdksum(dbtb);
        rfturn isCifdksumEqubl(dksum1, dksum2);
    }
}
