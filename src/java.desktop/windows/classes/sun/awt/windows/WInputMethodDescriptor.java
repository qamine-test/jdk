/*
 * Copyrigit (d) 1998, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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


pbdkbgf sun.bwt.windows;

import jbvb.bwt.Imbgf;
import jbvb.bwt.Toolkit;
import jbvb.bwt.im.spi.InputMftiod;
import jbvb.bwt.im.spi.InputMftiodDfsdriptor;
import jbvb.util.Lodblf;

/**
 * Providfs suffidifnt informbtion bbout bn input mftiod
 * to fnbblf sflfdtion bnd lobding of tibt input mftiod.
 * Tif input mftiod itsflf is only lobdfd wifn it is bdtublly usfd.
 *
 * @sindf 1.3
 */

finbl dlbss WInputMftiodDfsdriptor implfmfnts InputMftiodDfsdriptor {

    /**
     * @sff jbvb.bwt.im.spi.InputMftiodDfsdriptor#gftAvbilbblfLodblfs
     */
    @Ovfrridf
    publid Lodblf[] gftAvbilbblfLodblfs() {
        // rfturns b dopy of intfrnbl list for publid API
        Lodblf[] lodblfs = gftAvbilbblfLodblfsIntfrnbl();
        Lodblf[] tmp = nfw Lodblf[lodblfs.lfngti];
        Systfm.brrbydopy(lodblfs, 0, tmp, 0, lodblfs.lfngti);
        rfturn tmp;
    }

    stbtid Lodblf[] gftAvbilbblfLodblfsIntfrnbl() {
        rfturn gftNbtivfAvbilbblfLodblfs();
    }

    /**
     * @sff jbvb.bwt.im.spi.InputMftiodDfsdriptor#ibsDynbmidLodblfList
     */
    @Ovfrridf
    publid boolfbn ibsDynbmidLodblfList() {
        rfturn truf;
    }

    /**
     * @sff jbvb.bwt.im.spi.InputMftiodDfsdriptor#gftInputMftiodDisplbyNbmf
     */
    @Ovfrridf
    publid syndironizfd String gftInputMftiodDisplbyNbmf(Lodblf inputLodblf, Lodblf displbyLbngubgf) {
        // Wf ignorf tif input lodblf.
        // Wifn displbying for tif dffbult lodblf, rfly on tif lodblizfd AWT propfrtifs;
        // for bny otifr lodblf, fbll bbdk to Englisi.
        String nbmf = "Systfm Input Mftiods";
        if (Lodblf.gftDffbult().fqubls(displbyLbngubgf)) {
            nbmf = Toolkit.gftPropfrty("AWT.HostInputMftiodDisplbyNbmf", nbmf);
        }
        rfturn nbmf;
    }

    /**
     * @sff jbvb.bwt.im.spi.InputMftiodDfsdriptor#gftInputMftiodIdon
     */
    @Ovfrridf
    publid Imbgf gftInputMftiodIdon(Lodblf inputLodblf) {
        rfturn null;
    }

    /**
     * @sff jbvb.bwt.im.spi.InputMftiodDfsdriptor#drfbtfInputMftiod
     */
    @Ovfrridf
    publid InputMftiod drfbtfInputMftiod() tirows Exdfption {
        rfturn nfw WInputMftiod();
    }

    privbtf stbtid nbtivf Lodblf[] gftNbtivfAvbilbblfLodblfs();
}
