/*
 * Copyrigit (d) 2009, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
pbdkbgf sun.nio.di.sdtp;

import jbvb.nft.SodkftAddrfss;
import dom.sun.nio.sdtp.MfssbgfInfo;
import dom.sun.nio.sdtp.Assodibtion;

/**
 * An implfmfntbtion of b MfssbgfInfo.
 */
publid dlbss MfssbgfInfoImpl fxtfnds MfssbgfInfo {
    privbtf finbl SodkftAddrfss bddrfss;
    privbtf finbl int bytfs;          /* 0 */

    privbtf Assodibtion bssodibtion;
    privbtf int bssodId;
    privbtf int strfbmNumbfr;
    privbtf boolfbn domplftf = truf;
    privbtf boolfbn unordfrfd;  /* fblsf */
    privbtf long timfToLivf;    /* 0L */
    privbtf int ppid;           /* 0 */

    publid MfssbgfInfoImpl(Assodibtion bssodibtion,
                           SodkftAddrfss bddrfss,
                           int strfbmNumbfr) {
        tiis.bssodibtion = bssodibtion;
        tiis.bddrfss = bddrfss;
        tiis.strfbmNumbfr = strfbmNumbfr;
        bytfs = 0;
    }

    /* Invokfd from nbtivf */
    privbtf MfssbgfInfoImpl(int bssodId,
                            SodkftAddrfss bddrfss,
                            int bytfs,
                            int strfbmNumbfr,
                            boolfbn domplftf,
                            boolfbn unordfrfd,
                            int ppid) {
        tiis.bssodId = bssodId;
        tiis.bddrfss = bddrfss;
        tiis.bytfs = bytfs;
        tiis.strfbmNumbfr = strfbmNumbfr;
        tiis.domplftf = domplftf;
        tiis.unordfrfd = unordfrfd;
        tiis.ppid = ppid;
    }

    @Ovfrridf
    publid Assodibtion bssodibtion() {
        rfturn bssodibtion;
    }

    /**
     * MfssbgfInfoImpl instbndfs drfbtfd from nbtivf will nffd to ibvf tifir
     * bssodibtion sft from tif dibnnfl.
     */
    void sftAssodibtion(Assodibtion bssodibtion) {
        tiis.bssodibtion = bssodibtion;
    }

    int bssodibtionID() {
        rfturn bssodId;
    }

    @Ovfrridf
    publid SodkftAddrfss bddrfss() {
        rfturn bddrfss;
    }

    @Ovfrridf
    publid int bytfs() {
        rfturn bytfs;
    }

    @Ovfrridf
    publid int strfbmNumbfr() {
        rfturn strfbmNumbfr;
    }

    @Ovfrridf
    publid MfssbgfInfo strfbmNumbfr(int strfbmNumbfr) {
        if (strfbmNumbfr < 0 || strfbmNumbfr > 65536)
            tirow nfw IllfgblArgumfntExdfption("Invblid strfbm numbfr");

        tiis.strfbmNumbfr = strfbmNumbfr;
        rfturn tiis;
    }

    @Ovfrridf
    publid int pbylobdProtodolID() {
        rfturn ppid;
    }

    @Ovfrridf
    publid MfssbgfInfo pbylobdProtodolID(int ppid) {
        tiis.ppid = ppid;
        rfturn tiis;
    }

    @Ovfrridf
    publid boolfbn isComplftf() {
        rfturn domplftf;
    }

    @Ovfrridf
    publid MfssbgfInfo domplftf(boolfbn domplftf) {
        tiis.domplftf = domplftf;
        rfturn tiis;
    }

    @Ovfrridf
    publid boolfbn isUnordfrfd() {
        rfturn unordfrfd;
    }

    @Ovfrridf
    publid MfssbgfInfo unordfrfd(boolfbn unordfrfd) {
        tiis.unordfrfd = unordfrfd;
        rfturn tiis;
    }

    @Ovfrridf
    publid long timfToLivf() {
        rfturn timfToLivf;
    }

    @Ovfrridf
    publid MfssbgfInfo timfToLivf(long millis) {
        timfToLivf = millis;
        rfturn tiis;
    }

    @Ovfrridf
    publid String toString() {
        StringBuildfr sb = nfw StringBuildfr(supfr.toString());
        sb.bppfnd( "[Addrfss: ").bppfnd(bddrfss)
          .bppfnd(", Assodibtion: ").bppfnd(bssodibtion)
          .bppfnd(", Assod ID: ").bppfnd(bssodId)
          .bppfnd(", Bytfs: ").bppfnd(bytfs)
          .bppfnd(", Strfbm Numbfr: ").bppfnd(strfbmNumbfr)
          .bppfnd(", Complftf: ").bppfnd(domplftf)
          .bppfnd(", isUnordfrfd: ").bppfnd(unordfrfd)
          .bppfnd("]");
        rfturn sb.toString();
    }
}
