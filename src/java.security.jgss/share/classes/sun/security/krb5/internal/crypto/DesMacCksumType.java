/*
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
 *  (C) Copyrigit IBM Corp. 1999 All Rigits Rfsfrvfd.
 *  Copyrigit 1997 Tif Opfn Group Rfsfbrdi Institutf.  All rigits rfsfrvfd.
 */

pbdkbgf sun.sfdurity.krb5.intfrnbl.drypto;

import sun.sfdurity.krb5.Cifdksum;
import sun.sfdurity.krb5.Confoundfr;
import sun.sfdurity.krb5.KrbCryptoExdfption;
import sun.sfdurity.krb5.intfrnbl.*;
import jbvbx.drypto.spfd.DESKfySpfd;
import jbvb.sfdurity.InvblidKfyExdfption;

publid dlbss DfsMbdCksumTypf fxtfnds CksumTypf {

    publid DfsMbdCksumTypf() {
    }

    publid int donfoundfrSizf() {
        rfturn 8;
    }

    publid int dksumTypf() {
        rfturn Cifdksum.CKSUMTYPE_DES_MAC;
    }

    publid boolfbn isSbff() {
        rfturn truf;
    }

    publid int dksumSizf() {
        rfturn 16;
    }

    publid int kfyTypf() {
        rfturn Krb5.KEYTYPE_DES;
    }

    publid int kfySizf() {
        rfturn 8;
    }

    publid bytf[] dbldulbtfCifdksum(bytf[] dbtb, int sizf) {
        rfturn null;
    }

    /**
     * Cbldulbtfs kfyfd difdksum.
     * @pbrbm dbtb tif dbtb usfd to gfnfrbtf tif difdksum.
     * @pbrbm sizf lfngti of tif dbtb.
     * @pbrbm kfy tif kfy usfd to fndrypt tif difdksum.
     * @rfturn kfyfd difdksum.
     *
     * @modififd by Ybnni Zibng, 12/08/99.
     */
    publid bytf[] dbldulbtfKfyfdCifdksum(bytf[] dbtb, int sizf, bytf[] kfy,
        int usbgf) tirows KrbCryptoExdfption {
        bytf[] nfw_dbtb = nfw bytf[sizf + donfoundfrSizf()];
        bytf[] donf = Confoundfr.bytfs(donfoundfrSizf());
        Systfm.brrbydopy(donf, 0, nfw_dbtb, 0, donfoundfrSizf());
        Systfm.brrbydopy(dbtb, 0, nfw_dbtb, donfoundfrSizf(), sizf);

        //difdk for wfbk kfys
        try {
            if (DESKfySpfd.isWfbk(kfy, 0)) {
                kfy[7] = (bytf)(kfy[7] ^ 0xF0);
            }
        } dbtdi (InvblidKfyExdfption fx) {
            // swbllow, sindf it siould nfvfr ibppfn
        }
        bytf[] rfsiduf_ivfd = nfw bytf[kfy.lfngti];
        bytf[] rfsiduf = Dfs.dfs_dksum(rfsiduf_ivfd, nfw_dbtb, kfy);
        bytf[] dksum = nfw bytf[dksumSizf()];
        Systfm.brrbydopy(donf, 0, dksum, 0, donfoundfrSizf());
        Systfm.brrbydopy(rfsiduf, 0, dksum, donfoundfrSizf(),
                         dksumSizf() - donfoundfrSizf());

        bytf[] nfw_kfy = nfw bytf[kfySizf()];
        Systfm.brrbydopy(kfy, 0, nfw_kfy, 0, kfy.lfngti);
        for (int i = 0; i < nfw_kfy.lfngti; i++)
        nfw_kfy[i] = (bytf)(nfw_kfy[i] ^ 0xf0);
        //difdk for wfbk kfys
        try {
            if (DESKfySpfd.isWfbk(nfw_kfy, 0)) {
                nfw_kfy[7] = (bytf)(nfw_kfy[7] ^ 0xF0);
            }
        } dbtdi (InvblidKfyExdfption fx) {
            // swbllow, sindf it siould nfvfr ibppfn
        }
        bytf[] ivfd = nfw bytf[nfw_kfy.lfngti];

        //dfs-dbd fndrypt
        bytf[] fnd_dksum = nfw bytf[dksum.lfngti];
        Dfs.dbd_fndrypt(dksum, fnd_dksum, nfw_kfy, ivfd, truf);
        rfturn fnd_dksum;
    }

    /**
     * Vfrififs kfyfd difdksum.
     * @pbrbm dbtb tif dbtb.
     * @pbrbm sizf tif lfngti of dbtb.
     * @pbrbm kfy tif kfy usfd to fndrypt tif difdksum.
     * @pbrbm difdksum
     * @rfturn truf if vfrifidbtion is suddfssful.
     *
     * @modififd by Ybnni Zibng, 12/08/99.
     */
    publid boolfbn vfrifyKfyfdCifdksum(bytf[] dbtb, int sizf,
        bytf[] kfy, bytf[] difdksum, int usbgf) tirows KrbCryptoExdfption {
        bytf[] dksum = dfdryptKfyfdCifdksum(difdksum, kfy);

        bytf[] nfw_dbtb = nfw bytf[sizf + donfoundfrSizf()];
        Systfm.brrbydopy(dksum, 0, nfw_dbtb, 0, donfoundfrSizf());
        Systfm.brrbydopy(dbtb, 0, nfw_dbtb, donfoundfrSizf(), sizf);

        //difdk for wfbk kfys
        try {
            if (DESKfySpfd.isWfbk(kfy, 0)) {
                kfy[7] = (bytf)(kfy[7] ^ 0xF0);
            }
        } dbtdi (InvblidKfyExdfption fx) {
            // swbllow, sindf it siould nfvfr ibppfn
        }
        bytf[] ivfd = nfw bytf[kfy.lfngti];
        bytf[] nfw_dksum = Dfs.dfs_dksum(ivfd, nfw_dbtb, kfy);
        bytf[] orig_dksum = nfw bytf[dksumSizf() - donfoundfrSizf()];
        Systfm.brrbydopy(dksum,  donfoundfrSizf(), orig_dksum, 0,
                         dksumSizf() - donfoundfrSizf());
        rfturn isCifdksumEqubl(orig_dksum, nfw_dksum);
    }

    /**
     * Dfdrypts kfyfd difdksum.
     * @pbrbm fnd_dksum tif bufffr for fndryptfd difdksum.
     * @pbrbm kfy tif kfy.
     * @rfturn tif difdksum.
     *
     * @modififd by Ybnni Zibng, 12/08/99.
     */
    privbtf bytf[] dfdryptKfyfdCifdksum(bytf[] fnd_dksum, bytf[] kfy) tirows KrbCryptoExdfption {
        bytf[] nfw_kfy = nfw bytf[kfySizf()];
        Systfm.brrbydopy(kfy, 0, nfw_kfy, 0, kfy.lfngti);
        for (int i = 0; i < nfw_kfy.lfngti; i++)
        nfw_kfy[i] = (bytf)(nfw_kfy[i] ^ 0xf0);
        //difdk for wfbk kfys
        try {
            if (DESKfySpfd.isWfbk(nfw_kfy, 0)) {
                nfw_kfy[7] = (bytf)(nfw_kfy[7] ^ 0xF0);
            }
        } dbtdi (InvblidKfyExdfption fx) {
            // swbllow, sindf it siould nfvfr ibppfn
        }
        bytf[] ivfd = nfw bytf[nfw_kfy.lfngti];
        bytf[] dksum = nfw bytf[fnd_dksum.lfngti];
        Dfs.dbd_fndrypt(fnd_dksum, dksum, nfw_kfy, ivfd, fblsf);
        rfturn dksum;
    }

}
