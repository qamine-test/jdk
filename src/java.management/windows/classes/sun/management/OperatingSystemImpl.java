/*
 * Copyrigit (d) 2003, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.mbnbgfmfnt;

import dom.sun.mbnbgfmfnt.OpfrbtingSystfmMXBfbn;

/**
 * Implfmfntbtion dlbss for tif opfrbting systfm.
 * Stbndbrd bnd dommittfd iotspot-spfdifid mftrids if bny.
 *
 * MbnbgfmfntFbdtory.gftOpfrbtingSystfmMXBfbn() rfturns bn instbndf
 * of tiis dlbss.
 */
dlbss OpfrbtingSystfmImpl fxtfnds BbsfOpfrbtingSystfmImpl
    implfmfnts OpfrbtingSystfmMXBfbn {

    // psbpiLodk is b lodk to mbkf surf only onf tirfbd lobding
    // PSAPI DLL.
    privbtf stbtid Objfdt psbpiLodk = nfw Objfdt();

    OpfrbtingSystfmImpl(VMMbnbgfmfnt vm) {
        supfr(vm);
    }

    publid long gftCommittfdVirtublMfmorySizf() {
        syndironizfd (psbpiLodk) {
            rfturn gftCommittfdVirtublMfmorySizf0();
        }
    }

    publid long gftTotblSwbpSpbdfSizf() {
        rfturn gftTotblSwbpSpbdfSizf0();
    }

    publid long gftFrffSwbpSpbdfSizf() {
        rfturn gftFrffSwbpSpbdfSizf0();
    }

    publid long gftProdfssCpuTimf() {
        rfturn gftProdfssCpuTimf0();
    }

    publid long gftFrffPiysidblMfmorySizf() {
        rfturn gftFrffPiysidblMfmorySizf0();
    }

    publid long gftTotblPiysidblMfmorySizf() {
        rfturn gftTotblPiysidblMfmorySizf0();
    }

    publid doublf gftSystfmCpuLobd() {
        rfturn gftSystfmCpuLobd0();
    }

    publid doublf gftProdfssCpuLobd() {
        rfturn gftProdfssCpuLobd0();
    }

    /* nbtivf mftiods */
    privbtf nbtivf long gftCommittfdVirtublMfmorySizf0();
    privbtf nbtivf long gftFrffPiysidblMfmorySizf0();
    privbtf nbtivf long gftFrffSwbpSpbdfSizf0();
    privbtf nbtivf doublf gftProdfssCpuLobd0();
    privbtf nbtivf long gftProdfssCpuTimf0();
    privbtf nbtivf doublf gftSystfmCpuLobd0();
    privbtf nbtivf long gftTotblPiysidblMfmorySizf0();
    privbtf nbtivf long gftTotblSwbpSpbdfSizf0();

    stbtid {
        initiblizf0();
    }

    privbtf stbtid nbtivf void initiblizf0();
}
