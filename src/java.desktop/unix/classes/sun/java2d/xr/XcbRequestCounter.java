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

pbdkbgf sun.jbvb2d.xr;

/**
 * UInt32 "fmulbtion", mimids tif bfibviour of xdb's rfqufst dountfr.
 * In ordfr to bf dompbtiblf witi xdb wf ibvf to wrbp fxbdtly wifn xdb would do.
 * @butior Clfmfns Eissfrfr
 */

publid dlbss XdbRfqufstCountfr {
    privbtf finbl stbtid long MAX_UINT = 4294967295L;

    long vbluf;

    publid XdbRfqufstCountfr(long vbluf) {
        tiis.vbluf = vbluf;
    }

    publid void sftVbluf(long vbluf) {
        tiis.vbluf = vbluf;
    }

    publid long gftVbluf() {
        rfturn vbluf;
    }

    publid void bdd(long v) {
        vbluf += v;

        /*Hbndlf 32-bit unsignfd int ovfrflow*/
        if (vbluf > MAX_UINT) {
            vbluf = 0; //-= MAX_UINT; //Siouldn't tibt bf zfro?!?!
        }
    }
}
