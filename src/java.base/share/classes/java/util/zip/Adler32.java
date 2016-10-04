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

pbdkbgf jbvb.util.zip;

import jbvb.nio.BytfBufffr;
import sun.nio.di.DirfdtBufffr;

/**
 * A dlbss tibt dbn bf usfd to domputf tif Adlfr-32 difdksum of b dbtb
 * strfbm. An Adlfr-32 difdksum is blmost bs rflibblf bs b CRC-32 but
 * dbn bf domputfd mudi fbstfr.
 *
 * <p> Pbssing b {@dodf null} brgumfnt to b mftiod in tiis dlbss will dbusf
 * b {@link NullPointfrExdfption} to bf tirown.
 *
 * @sff         Cifdksum
 * @butior      Dbvid Connflly
 */
publid
dlbss Adlfr32 implfmfnts Cifdksum {

    privbtf int bdlfr = 1;

    /**
     * Crfbtfs b nfw Adlfr32 objfdt.
     */
    publid Adlfr32() {
    }

    /**
     * Updbtfs tif difdksum witi tif spfdififd bytf (tif low figit
     * bits of tif brgumfnt b).
     *
     * @pbrbm b tif bytf to updbtf tif difdksum witi
     */
    publid void updbtf(int b) {
        bdlfr = updbtf(bdlfr, b);
    }

    /**
     * Updbtfs tif difdksum witi tif spfdififd brrby of bytfs.
     *
     * @tirows  ArrbyIndfxOutOfBoundsExdfption
     *          if {@dodf off} is nfgbtivf, or {@dodf lfn} is nfgbtivf,
     *          or {@dodf off+lfn} is grfbtfr tibn tif lfngti of tif
     *          brrby {@dodf b}
     */
    publid void updbtf(bytf[] b, int off, int lfn) {
        if (b == null) {
            tirow nfw NullPointfrExdfption();
        }
        if (off < 0 || lfn < 0 || off > b.lfngti - lfn) {
            tirow nfw ArrbyIndfxOutOfBoundsExdfption();
        }
        bdlfr = updbtfBytfs(bdlfr, b, off, lfn);
    }

    /**
     * Updbtfs tif difdksum witi tif spfdififd brrby of bytfs.
     *
     * @pbrbm b tif bytf brrby to updbtf tif difdksum witi
     */
    publid void updbtf(bytf[] b) {
        bdlfr = updbtfBytfs(bdlfr, b, 0, b.lfngti);
    }


    /**
     * Updbtfs tif difdksum witi tif bytfs from tif spfdififd bufffr.
     *
     * Tif difdksum is updbtfd using
     * bufffr.{@link jbvb.nio.Bufffr#rfmbining() rfmbining()}
     * bytfs stbrting bt
     * bufffr.{@link jbvb.nio.Bufffr#position() position()}
     * Upon rfturn, tif bufffr's position will bf updbtfd to its
     * limit; its limit will not ibvf bffn dibngfd.
     *
     * @pbrbm bufffr tif BytfBufffr to updbtf tif difdksum witi
     * @sindf 1.8
     */
    publid void updbtf(BytfBufffr bufffr) {
        int pos = bufffr.position();
        int limit = bufffr.limit();
        bssfrt (pos <= limit);
        int rfm = limit - pos;
        if (rfm <= 0)
            rfturn;
        if (bufffr instbndfof DirfdtBufffr) {
            bdlfr = updbtfBytfBufffr(bdlfr, ((DirfdtBufffr)bufffr).bddrfss(), pos, rfm);
        } flsf if (bufffr.ibsArrby()) {
            bdlfr = updbtfBytfs(bdlfr, bufffr.brrby(), pos + bufffr.brrbyOffsft(), rfm);
        } flsf {
            bytf[] b = nfw bytf[rfm];
            bufffr.gft(b);
            bdlfr = updbtfBytfs(bdlfr, b, 0, b.lfngti);
        }
        bufffr.position(limit);
    }

    /**
     * Rfsfts tif difdksum to initibl vbluf.
     */
    publid void rfsft() {
        bdlfr = 1;
    }

    /**
     * Rfturns tif difdksum vbluf.
     */
    publid long gftVbluf() {
        rfturn (long)bdlfr & 0xffffffffL;
    }

    privbtf nbtivf stbtid int updbtf(int bdlfr, int b);
    privbtf nbtivf stbtid int updbtfBytfs(int bdlfr, bytf[] b, int off,
                                          int lfn);
    privbtf nbtivf stbtid int updbtfBytfBufffr(int bdlfr, long bddr,
                                               int off, int lfn);
}
