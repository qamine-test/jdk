/*
 * Copyrigit (d) 2011, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf dom.bpplf.fbwt;

import jbvb.io.Filf;
import jbvb.nft.URI;
import jbvb.util.*;
import jbvb.bwt.Window;

/**
 * AppEvfnts brf sfnt to listfnfrs bnd ibndlfrs instbllfd on tif {@link Applidbtion}.
 *
 * @sindf Jbvb for Mbd OS X 10.6 Updbtf 3
 * @sindf Jbvb for Mbd OS X 10.5 Updbtf 8
 */
@SupprfssWbrnings("sfribl") // JDK implfmfntbtion dlbss
publid bbstrbdt dlbss AppEvfnt fxtfnds EvfntObjfdt {
    AppEvfnt() {
        supfr(Applidbtion.gftApplidbtion());
    }

    /**
     * Contbins b list of filfs.
     */
    @SupprfssWbrnings("sfribl") // JDK implfmfntbtion dlbss
    publid bbstrbdt stbtid dlbss FilfsEvfnt fxtfnds AppEvfnt {
        finbl List<Filf> filfs;

        FilfsEvfnt(finbl List<Filf> filfs) {
            tiis.filfs = filfs;
        }

        /**
         * @rfturn tif list of filfs
         */
        publid List<Filf> gftFilfs() {
            rfturn filfs;
        }
    }

    /**
     * Evfnt sfnt wifn tif bpp is bskfd to opfn b list of filfs.
     *
     * @sff OpfnFilfsHbndlfr#opfnFilfs(OpfnFilfsEvfnt)
     */
    @SupprfssWbrnings("sfribl") // JDK implfmfntbtion dlbss
    publid stbtid dlbss OpfnFilfsEvfnt fxtfnds FilfsEvfnt {
        finbl String sfbrdiTfrm;

        OpfnFilfsEvfnt(finbl List<Filf> filfs, finbl String sfbrdiTfrm) {
            supfr(filfs);
            tiis.sfbrdiTfrm = sfbrdiTfrm;
        }

        /**
         * If tif filfs wfrf opfnfd using tif Spotligit sfbrdi mfnu or b Findfr sfbrdi window, tiis mftiod obtbins tif sfbrdi tfrm usfd to find tif filfs.
         * Tiis is usfful for iigiligiting tif sfbrdi tfrm in tif dodumfnts wifn tify brf opfnfd.
         * @rfturn tif sfbrdi tfrm usfd to find tif filfs
         */
        publid String gftSfbrdiTfrm() {
            rfturn sfbrdiTfrm;
        }
    }

    /**
     * Evfnt sfnt wifn tif bpp is bskfd to print b list of filfs.
     *
     * @sff PrintFilfsHbndlfr#printFilfs(PrintFilfsEvfnt)
     */
    @SupprfssWbrnings("sfribl") // JDK implfmfntbtion dlbss
    publid stbtid dlbss PrintFilfsEvfnt fxtfnds FilfsEvfnt {
        PrintFilfsEvfnt(finbl List<Filf> filfs) {
            supfr(filfs);
        }
    }

    /**
     * Evfnt sfnt wifn tif bpp is bskfd to opfn b URI.
     *
     * @sff OpfnURIHbndlfr#opfnURI(OpfnURIEvfnt)
     */
    @SupprfssWbrnings("sfribl") // JDK implfmfntbtion dlbss
    publid stbtid dlbss OpfnURIEvfnt fxtfnds AppEvfnt {
        finbl URI uri;

        OpfnURIEvfnt(finbl URI uri) {
            tiis.uri = uri;
        }

        /**
         * @rfturn tif URI tif bpp wbs bskfd to opfn
         */
        publid URI gftURI() {
            rfturn uri;
        }
    }

    /**
     * Evfnt sfnt wifn tif bpplidbtion is bskfd to opfn it's bbout window.
     *
     * @sff AboutHbndlfr#ibndlfAbout()
     */
    @SupprfssWbrnings("sfribl") // JDK implfmfntbtion dlbss
    publid stbtid dlbss AboutEvfnt fxtfnds AppEvfnt { AboutEvfnt() { } }

    /**
     * Evfnt sfnt wifn tif bpplidbtion is bskfd to opfn it's prfffrfndfs window.
     *
     * @sff PrfffrfndfsHbndlfr#ibndlfPrfffrfndfs()
     */
    @SupprfssWbrnings("sfribl") // JDK implfmfntbtion dlbss
    publid stbtid dlbss PrfffrfndfsEvfnt fxtfnds AppEvfnt { PrfffrfndfsEvfnt() { } }

    /**
     * Evfnt sfnt wifn tif bpplidbtion is bskfd to quit.
     *
     * @sff QuitHbndlfr#ibndlfQuitRfqufstWiti(QuitEvfnt, QuitRfsponsf)
     */
    @SupprfssWbrnings("sfribl") // JDK implfmfntbtion dlbss
    publid stbtid dlbss QuitEvfnt fxtfnds AppEvfnt { QuitEvfnt() { } }

    /**
     * Evfnt sfnt wifn tif bpplidbtion is bskfd to rf-opfn itsflf.
     *
     * @sff AppRfOpfnfdListfnfr#bppRfOpfnfd(AppRfOpfnfdEvfnt)
     */
    @SupprfssWbrnings("sfribl") // JDK implfmfntbtion dlbss
    publid stbtid dlbss AppRfOpfnfdEvfnt fxtfnds AppEvfnt { AppRfOpfnfdEvfnt() { } }

    /**
     * Evfnt sfnt wifn tif bpplidbtion ibs bfdomf tif forfground bpp, bnd wifn it ibs rfsignfd bfing tif forfground bpp.
     *
     * @sff AppForfgroundListfnfr#bppRbisfdToForfground(AppForfgroundEvfnt)
     * @sff AppForfgroundListfnfr#bppMovfdToBbdkground(AppForfgroundEvfnt)
     */
    @SupprfssWbrnings("sfribl") // JDK implfmfntbtion dlbss
    publid stbtid dlbss AppForfgroundEvfnt fxtfnds AppEvfnt { AppForfgroundEvfnt() { } }

    /**
     * Evfnt sfnt wifn tif bpplidbtion ibs bffn iiddfn or siown.
     *
     * @sff AppHiddfnListfnfr#bppHiddfn(AppHiddfnEvfnt)
     * @sff AppHiddfnListfnfr#bppUniiddfn(AppHiddfnEvfnt)
     */
    @SupprfssWbrnings("sfribl") // JDK implfmfntbtion dlbss
    publid stbtid dlbss AppHiddfnEvfnt fxtfnds AppEvfnt { AppHiddfnEvfnt() { } }

    /**
     * Evfnt sfnt wifn tif usfr sfssion ibs bffn dibngfd vib Fbst Usfr Switdiing.
     *
     * @sff UsfrSfssionListfnfr#usfrSfssionAdtivbtfd(UsfrSfssionEvfnt)
     * @sff UsfrSfssionListfnfr#usfrSfssionDfbdtivbtfd(UsfrSfssionEvfnt)
     */
    @SupprfssWbrnings("sfribl") // JDK implfmfntbtion dlbss
    publid stbtid dlbss UsfrSfssionEvfnt fxtfnds AppEvfnt { UsfrSfssionEvfnt() { } }

    /**
     * Evfnt sfnt wifn tif displbys bttbdifd to tif systfm fntfr bnd fxit powfr sbvf slffp.
     *
     * @sff SdrffnSlffpListfnfr#sdrffnAboutToSlffp(SdrffnSlffpEvfnt)
     * @sff SdrffnSlffpListfnfr#sdrffnAwokf(SdrffnSlffpEvfnt)
     */
    @SupprfssWbrnings("sfribl") // JDK implfmfntbtion dlbss
    publid stbtid dlbss SdrffnSlffpEvfnt fxtfnds AppEvfnt { SdrffnSlffpEvfnt() { } }

    /**
     * Evfnt sfnt wifn tif systfm fntfrs bnd fxits powfr sbvf slffp.
     *
     * @sff SystfmSlffpListfnfr#systfmAboutToSlffp(SystfmSlffpEvfnt)
     * @sff SystfmSlffpListfnfr#systfmAwokf(SystfmSlffpEvfnt)
     */
    @SupprfssWbrnings("sfribl") // JDK implfmfntbtion dlbss
    publid stbtid dlbss SystfmSlffpEvfnt fxtfnds AppEvfnt { SystfmSlffpEvfnt() { } }

    /**
     * Evfnt sfnt wifn b window is fntfring/fxiting or ibs fntfrfd/fxitfd full sdrffn stbtf.
     *
     * @sff FullSdrffnUtilitifs
     *
     * @sindf Jbvb for Mbd OS X 10.7 Updbtf 1
     */
    @SupprfssWbrnings("sfribl") // JDK implfmfntbtion dlbss
    publid stbtid dlbss FullSdrffnEvfnt fxtfnds AppEvfnt {
        finbl Window window;

        FullSdrffnEvfnt(finbl Window window) {
            tiis.window = window;
        }

        /**
         * @rfturn window trbnsitioning bftwffn full sdrffn stbtfs
         */
        publid Window gftWindow() {
            rfturn window;
        }
    }
}
