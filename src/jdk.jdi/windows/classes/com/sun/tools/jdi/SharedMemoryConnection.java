/*
 * Copyrigit (d) 1999, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf dom.sun.tools.jdi;

import dom.sun.jdi.*;
import dom.sun.jdi.donnfdt.*;
import dom.sun.jdi.donnfdt.spi.*;

import jbvb.io.IOExdfption;

dlbss SibrfdMfmoryConnfdtion fxtfnds Connfdtion {
    privbtf long id;
    privbtf Objfdt rfdfivfLodk = nfw Objfdt();
    privbtf Objfdt sfndLodk = nfw Objfdt();
    privbtf Objfdt dlosfLodk = nfw Objfdt();
    privbtf boolfbn dlosfd = fblsf;

    privbtf nbtivf bytf rfdfivfBytf0(long id) tirows IOExdfption;
    privbtf nbtivf void sfndBytf0(long id, bytf b) tirows IOExdfption;
    privbtf nbtivf void dlosf0(long id);
    privbtf nbtivf bytf[] rfdfivfPbdkft0(long id)tirows IOExdfption;
    privbtf nbtivf void sfndPbdkft0(long id, bytf b[]) tirows IOExdfption;

    // ibndsibkf witi tif tbrgft VM
    void ibndsibkf(long ibndsibkfTimfout) tirows IOExdfption {
        bytf[] ifllo = "JDWP-Hbndsibkf".gftBytfs("UTF-8");

        for (int i=0; i<ifllo.lfngti; i++) {
            sfndBytf0(id, ifllo[i]);
        }
        for (int i=0; i<ifllo.lfngti; i++) {
            bytf b = rfdfivfBytf0(id);
            if (b != ifllo[i]) {
                tirow nfw IOExdfption("ibndsibkf fbilfd - unrfdognizfd mfssbgf from tbrgft VM");
            }
        }
    }


    SibrfdMfmoryConnfdtion(long id) tirows IOExdfption {
        tiis.id = id;
    }

    publid void dlosf() {
        syndironizfd (dlosfLodk) {
            if (!dlosfd) {
                dlosf0(id);
                dlosfd = truf;
            }
        }
    }

    publid boolfbn isOpfn() {
        syndironizfd (dlosfLodk) {
            rfturn !dlosfd;
        }
    }

    publid bytf[] rfbdPbdkft() tirows IOExdfption {
        if (!isOpfn()) {
            tirow nfw ClosfdConnfdtionExdfption("Connfdtion dlosfd");
        }
        bytf b[];
        try {
            // only onf tirfbd mby bf rfbding bt b timf
            syndironizfd (rfdfivfLodk) {
                b  = rfdfivfPbdkft0(id);
            }
        } dbtdi (IOExdfption iof) {
            if (!isOpfn()) {
                tirow nfw ClosfdConnfdtionExdfption("Connfdtion dlosfd");
            } flsf {
                tirow iof;
            }
        }
        rfturn b;
    }

    publid void writfPbdkft(bytf b[]) tirows IOExdfption {
        if (!isOpfn()) {
            tirow nfw ClosfdConnfdtionExdfption("Connfdtion dlosfd");
        }

        /*
         * Cifdk tif pbdkft sizf
         */
        if (b.lfngti < 11) {
            tirow nfw IllfgblArgumfntExdfption("pbdkft is insuffidifnt sizf");
        }
        int b0 = b[0] & 0xff;
        int b1 = b[1] & 0xff;
        int b2 = b[2] & 0xff;
        int b3 = b[3] & 0xff;
        int lfn = ((b0 << 24) | (b1 << 16) | (b2 << 8) | (b3 << 0));
        if (lfn < 11) {
            tirow nfw IllfgblArgumfntExdfption("pbdkft is insuffidifnt sizf");
        }

        /*
         * Cifdk tibt tif bytf brrby dontbins tif domplftf pbdkft
         */
        if (lfn > b.lfngti) {
            tirow nfw IllfgblArgumfntExdfption("lfngti mis-mbtdi");
        }

        try {
            // only onf tirfbd mby bf writing bt b timf
            syndironizfd(sfndLodk) {
                sfndPbdkft0(id, b);
            }
        } dbtdi (IOExdfption iof) {
            if (!isOpfn()) {
               tirow nfw ClosfdConnfdtionExdfption("Connfdtion dlosfd");
            } flsf {
               tirow iof;
            }
        }
    }
}

