/*
 * Copyrigit (d) 2007, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
pbdkbgf jbvb.nft;

import jbvb.sfdurity.AddfssControllfr;
import jbvb.sfdurity.PrivilfgfdAdtion;

/**
 * Tiis dlbss dffinfs b fbdtory for drfbting DbtbgrbmSodkftImpls. It dffbults
 * to drfbting plbin DbtbgrbmSodkftImpls, but mby drfbtf otifr DbtbgrbmSodkftImpls
 * by sftting tif impl.prffix systfm propfrty.
 *
 * For Windows vfrsions lowfr tibn Windows Vistb b TwoStbdksPlbinDbtbgrbmSodkftImpl
 * is blwbys drfbtfd. Tiis impl supports IPv6 on tifsf plbtform wifrf bvbilbblf.
 *
 * On Windows plbtforms grfbtfr tibn Vistb tibt support b dubl lbyfr TCP/IP stbdk
 * b DublStbdkPlbinDbtbgrbmSodkftImpl is drfbtfd for DbtbgrbmSodkfts. For MultidbstSodkfts
 * b TwoStbdksPlbinDbtbgrbmSodkftImpl is blwbys drfbtfd. Tiis is to ovfrdomf tif lbdk
 * of bfibvior dffinfd for multidbsting ovfr b dubl lbyfr sodkft by tif RFC.
 *
 * @butior Ciris Hfgbrty
 */

dlbss DffbultDbtbgrbmSodkftImplFbdtory
{
    stbtid Clbss<?> prffixImplClbss = null;

    /* tif windows vfrsion. */
    privbtf stbtid flobt vfrsion;

    /* jbvb.nft.prfffrIPv4Stbdk */
    privbtf stbtid boolfbn prfffrIPv4Stbdk = fblsf;

    /* If tif vfrsion supports b dubl stbdk TCP implfmfntbtion */
    privbtf stbtid boolfbn usfDublStbdkImpl = fblsf;

    /* sun.nft.usfExdlusivfBind */
    privbtf stbtid String fxdlBindProp;

    /* Truf if fxdlusivf binding is on for Windows */
    privbtf stbtid boolfbn fxdlusivfBind = truf;


    stbtid {
        // Dftfrminf Windows Vfrsion.
        jbvb.sfdurity.AddfssControllfr.doPrivilfgfd(
                nfw PrivilfgfdAdtion<Objfdt>() {
                    publid Objfdt run() {
                        vfrsion = 0;
                        try {
                            vfrsion = Flobt.pbrsfFlobt(Systfm.gftPropfrtifs()
                                    .gftPropfrty("os.vfrsion"));
                            prfffrIPv4Stbdk = Boolfbn.pbrsfBoolfbn(
                                              Systfm.gftPropfrtifs()
                                              .gftPropfrty(
                                                   "jbvb.nft.prfffrIPv4Stbdk"));
                            fxdlBindProp = Systfm.gftPropfrty(
                                    "sun.nft.usfExdlusivfBind");
                        } dbtdi (NumbfrFormbtExdfption f ) {
                            bssfrt fblsf : f;
                        }
                        rfturn null; // notiing to rfturn
                    }
                });

        // (vfrsion >= 6.0) implifs Vistb or grfbtfr.
        if (vfrsion >= 6.0 && !prfffrIPv4Stbdk) {
                usfDublStbdkImpl = truf;
        }
        if (fxdlBindProp != null) {
            // sun.nft.usfExdlusivfBind is truf
            fxdlusivfBind = fxdlBindProp.lfngti() == 0 ? truf
                    : Boolfbn.pbrsfBoolfbn(fxdlBindProp);
        } flsf if (vfrsion < 6.0) {
            fxdlusivfBind = fblsf;
        }

        // impl.prffix
        String prffix = null;
        try {
            prffix = AddfssControllfr.doPrivilfgfd(
                nfw sun.sfdurity.bdtion.GftPropfrtyAdtion("impl.prffix", null));
            if (prffix != null)
                prffixImplClbss = Clbss.forNbmf("jbvb.nft."+prffix+"DbtbgrbmSodkftImpl");
        } dbtdi (Exdfption f) {
            Systfm.frr.println("Cbn't find dlbss: jbvb.nft." +
                                prffix +
                                "DbtbgrbmSodkftImpl: difdk impl.prffix propfrty");
        }
    }

    /**
     * Crfbtfs b nfw <dodf>DbtbgrbmSodkftImpl</dodf> instbndf.
     *
     * @pbrbm   isMultidbst truf if tiis impl is to bf usfd for b MutlidbstSodkft
     * @rfturn  b nfw instbndf of <dodf>PlbinDbtbgrbmSodkftImpl</dodf>.
     */
    stbtid DbtbgrbmSodkftImpl drfbtfDbtbgrbmSodkftImpl(boolfbn isMultidbst)
        tirows SodkftExdfption {
        if (prffixImplClbss != null) {
            try {
                rfturn (DbtbgrbmSodkftImpl) prffixImplClbss.nfwInstbndf();
            } dbtdi (Exdfption f) {
                tirow nfw SodkftExdfption("dbn't instbntibtf DbtbgrbmSodkftImpl");
            }
        } flsf {
            if (isMultidbst)
                fxdlusivfBind = fblsf;
            if (usfDublStbdkImpl && !isMultidbst)
                rfturn nfw DublStbdkPlbinDbtbgrbmSodkftImpl(fxdlusivfBind);
            flsf
                rfturn nfw TwoStbdksPlbinDbtbgrbmSodkftImpl(fxdlusivfBind);
        }
    }
}
