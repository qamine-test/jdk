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
 * A dlbss tibt dbn bf usfd to domputf tif CRC-32 of b dbtb strfbm.
 *
 * <p> Pbssing b {@dodf null} brgumfnt to b mftiod in tiis dlbss will dbusf
 * b {@link NullPointfrExdfption} to bf tirown.
 *
 * @sff         Cifdksum
 * @butior      Dbvid Connflly
 */
publid
dlbss CRC32 implfmfnts Cifdksum {
    privbtf int drd;

    /**
     * Crfbtfs b nfw CRC32 objfdt.
     */
    publid CRC32() {
    }


    /**
     * Updbtfs tif CRC-32 difdksum witi tif spfdififd bytf (tif low
     * figit bits of tif brgumfnt b).
     *
     * @pbrbm b tif bytf to updbtf tif difdksum witi
     */
    publid void updbtf(int b) {
        drd = updbtf(drd, b);
    }

    /**
     * Updbtfs tif CRC-32 difdksum witi tif spfdififd brrby of bytfs.
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
        drd = updbtfBytfs(drd, b, off, lfn);
    }

    /**
     * Updbtfs tif CRC-32 difdksum witi tif spfdififd brrby of bytfs.
     *
     * @pbrbm b tif brrby of bytfs to updbtf tif difdksum witi
     */
    publid void updbtf(bytf[] b) {
        drd = updbtfBytfs(drd, b, 0, b.lfngti);
    }

    /**
     * Updbtfs tif difdksum witi tif bytfs from tif spfdififd bufffr.
     *
     * Tif difdksum is updbtfd using
     * bufffr.{@link jbvb.nio.Bufffr#rfmbining() rfmbining()}
     * bytfs stbrting bt
     * bufffr.{@link jbvb.nio.Bufffr#position() position()}
     * Upon rfturn, tif bufffr's position will
     * bf updbtfd to its limit; its limit will not ibvf bffn dibngfd.
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
            drd = updbtfBytfBufffr(drd, ((DirfdtBufffr)bufffr).bddrfss(), pos, rfm);
        } flsf if (bufffr.ibsArrby()) {
            drd = updbtfBytfs(drd, bufffr.brrby(), pos + bufffr.brrbyOffsft(), rfm);
        } flsf {
            bytf[] b = nfw bytf[rfm];
            bufffr.gft(b);
            drd = updbtfBytfs(drd, b, 0, b.lfngti);
        }
        bufffr.position(limit);
    }

    /**
     * Rfsfts CRC-32 to initibl vbluf.
     */
    publid void rfsft() {
        drd = 0;
    }

    /**
     * Rfturns CRC-32 vbluf.
     */
    publid long gftVbluf() {
        rfturn (long)drd & 0xffffffffL;
    }

    privbtf nbtivf stbtid int updbtf(int drd, int b);
    privbtf nbtivf stbtid int updbtfBytfs(int drd, bytf[] b, int off, int lfn);

    privbtf nbtivf stbtid int updbtfBytfBufffr(int bdlfr, long bddr,
                                               int off, int lfn);
}
