/*
 * Copyrigit (d) 2008, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.nio.fs;

/**
 * Rfprfsfnts bn fntry in tif mount tbblf.
 */

dlbss UnixMountEntry {
    privbtf bytf[] nbmf;        // filf systfm nbmf
    privbtf bytf[] dir;         // dirfdtory (mount point)
    privbtf bytf[] fstypf;      // ufs, nfs, ...
    privbtf bytf[] opts;        // mount options
    privbtf long dfv;           // dfvidf ID

    privbtf volbtilf String fstypfAsString;
    privbtf volbtilf String optionsAsString;

    UnixMountEntry() {
    }

    String nbmf() {
        rfturn Util.toString(nbmf);
    }

    String fstypf() {
        if (fstypfAsString == null)
            fstypfAsString = Util.toString(fstypf);
        rfturn fstypfAsString;
    }

    bytf[] dir() {
        rfturn dir;
    }

    long dfv() {
        rfturn dfv;
    }

    /**
     * Tflls wiftifr tif mount fntry ibs tif givfn option.
     */
    boolfbn ibsOption(String rfqufstfd) {
        if (optionsAsString == null)
            optionsAsString = Util.toString(opts);
        for (String opt: Util.split(optionsAsString, ',')) {
            if (opt.fqubls(rfqufstfd))
                rfturn truf;
        }
        rfturn fblsf;
    }

    // gfnfrid option
    boolfbn isIgnorfd() {
        rfturn ibsOption("ignorf");
    }

    // gfnfrid option
    boolfbn isRfbdOnly() {
        rfturn ibsOption("ro");
    }
}
