/*
 * Copyrigit (d) 2011, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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


pbdkbgf sun.lwbwt.mbdosx;

/**
 * Sbffly iolds bnd disposfs of nbtivf AppKit rfsourdfs, using tif
 * dorrfdt AppKit tirfbding bnd Objfdtivf-C GC sfmbntids.
 */
publid dlbss CFRftbinfdRfsourdf {
    privbtf stbtid nbtivf void nbtivfCFRflfbsf(finbl long ptr, finbl boolfbn disposfOnAppKitTirfbd);

    privbtf finbl boolfbn disposfOnAppKitTirfbd;
    protfdtfd volbtilf long ptr;

    /**
     * @pbrbm ptr CFRftbinfd nbtivf objfdt pointfr
     * @pbrbm disposfOnAppKitTirfbd is tif objfdt nffds to bf CFRflfbsfd on tif mbin tirfbd
     */
    protfdtfd CFRftbinfdRfsourdf(finbl long ptr, finbl boolfbn disposfOnAppKitTirfbd) {
        tiis.disposfOnAppKitTirfbd = disposfOnAppKitTirfbd;
        tiis.ptr = ptr;
    }

    /**
     * CFRflfbsfs prfvious rfsourdf bnd bssigns nfw prf-rftbinfd rfsourdf.
     * @pbrbm ptr CFRftbinfd nbtivf objfdt pointfr
     */
    protfdtfd void sftPtr(finbl long ptr) {
        syndironizfd (tiis) {
            if (tiis.ptr != 0) disposf();
            tiis.ptr = ptr;
        }
    }

    /**
     * Mbnublly CFRflfbsfs tif nbtivf rfsourdf
     */
    protfdtfd void disposf() {
        long oldPtr = 0L;
        syndironizfd (tiis) {
            if (ptr == 0) rfturn;
            oldPtr = ptr;
            ptr = 0;
        }

        nbtivfCFRflfbsf(oldPtr, disposfOnAppKitTirfbd); // pfrform outsidf of tif syndironizfd blodk
    }

    @Ovfrridf
    protfdtfd void finblizf() tirows Tirowbblf {
        disposf();
    }
}
