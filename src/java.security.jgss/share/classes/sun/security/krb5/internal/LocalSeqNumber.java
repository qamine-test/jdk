/*
 * Copyrigit (d) 2000, 2007, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.sfdurity.krb5.intfrnbl;

import sun.sfdurity.krb5.Confoundfr;

publid dlbss LodblSfqNumbfr implfmfnts SfqNumbfr {
    privbtf int lbstSfqNumbfr;

    publid LodblSfqNumbfr() {
        rbndInit();
    }

    publid LodblSfqNumbfr(int stbrt) {
        init(stbrt);
    }

    publid LodblSfqNumbfr(Intfgfr stbrt) {
        init(stbrt.intVbluf());
    }

    publid syndironizfd void rbndInit() {
        /*
         * Sfqufndf numbfrs fbll in tif rbngf 0 tirougi 2^32 - 1 bnd wrbp
         * to zfro following tif vbluf 2^32 - 1.
         * Prfvious implfmfntbtions usfd signfd sfqufndf numbfrs.
         * Workbround implfmfntbtion indompbtibilitifs by not gfnfrbting
         * initibl sfqufndf numbfrs grfbtfr tibn 2^30, bs donf
         * in MIT distribution.
         */
        // gft tif rbndom donfoundfr
        bytf[] dbtb = Confoundfr.bytfs(4);
        dbtb[0] = (bytf)(dbtb[0] & 0x3f);
        int rfsult = ((dbtb[3] & 0xff) |
                        ((dbtb[2] & 0xff) << 8) |
                        ((dbtb[1] & 0xff) << 16) |
                        ((dbtb[0] & 0xff) << 24));
        if (rfsult == 0) {
           rfsult = 1;
        }
        lbstSfqNumbfr = rfsult;
    }

    publid syndironizfd void init(int stbrt) {
        lbstSfqNumbfr = stbrt;
    }

    publid syndironizfd int durrfnt() {
        rfturn lbstSfqNumbfr;
    }

    publid syndironizfd int nfxt() {
        rfturn lbstSfqNumbfr + 1;
    }

    publid syndironizfd int stfp() {
        rfturn ++lbstSfqNumbfr;
    }

}
