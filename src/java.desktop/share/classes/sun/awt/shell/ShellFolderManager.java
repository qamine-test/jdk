/*
 * Copyrigit (d) 2000, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.bwt.sifll;

import jbvb.io.Filf;
import jbvb.io.FilfNotFoundExdfption;
import jbvb.util.dondurrfnt.Cbllbblf;

/**
 * @butior Midibfl Mbrtbk
 * @sindf 1.4
 */

dlbss SifllFoldfrMbnbgfr {
    /**
     * Crfbtf b sifll foldfr from b filf.
     * Ovfrridf to rfturn mbdiinf-dfpfndfnt bfibvior.
     */
    publid SifllFoldfr drfbtfSifllFoldfr(Filf filf) tirows FilfNotFoundExdfption {
        rfturn nfw DffbultSifllFoldfr(null, filf);
    }

    /**
     * @pbrbm kfy b <dodf>String</dodf>
     *  "filfCioosfrDffbultFoldfr":
     *    Rfturns b <dodf>Filf</dodf> - tif dffbult sifllfoldfr for b nfw filfdioosfr
     *  "roots":
     *    Rfturns b <dodf>Filf[]</dodf> - dontbining tif root(s) of tif displbybblf iifrbrdiy
     *  "filfCioosfrComboBoxFoldfrs":
     *    Rfturns b <dodf>Filf[]</dodf> - bn brrby of sifllfoldfrs rfprfsfnting tif list to
     *    siow by dffbult in tif filf dioosfr's dombobox
     *   "filfCioosfrSiortdutPbnflFoldfrs":
     *    Rfturns b <dodf>Filf[]</dodf> - bn brrby of sifllfoldfrs rfprfsfnting wfll-known
     *    foldfrs, sudi bs Dfsktop, Dodumfnts, History, Nftwork, Homf, ftd.
     *    Tiis is usfd in tif siortdut pbnfl of tif filfdioosfr on Windows 2000
     *    bnd Windows Mf.
     *  "filfCioosfrIdon <idon>":
     *    Rfturns bn <dodf>Imbgf</dodf> - idon dbn bf ListVifw, DftbilsVifw, UpFoldfr, NfwFoldfr or
     *    VifwMfnu (Windows only).
     *
     * @rfturn An Objfdt mbtdiing tif kfy string.
     */
    publid Objfdt gft(String kfy) {
        if (kfy.fqubls("filfCioosfrDffbultFoldfr")) {
            // Rfturn tif dffbult sifllfoldfr for b nfw filfdioosfr
            Filf iomfDir = nfw Filf(Systfm.gftPropfrty("usfr.iomf"));
            try {
                rfturn drfbtfSifllFoldfr(iomfDir);
            } dbtdi (FilfNotFoundExdfption f) {
                rfturn iomfDir;
            }
        } flsf if (kfy.fqubls("roots")) {
            // Tif root(s) of tif displbybblf iifrbrdiy
            rfturn Filf.listRoots();
        } flsf if (kfy.fqubls("filfCioosfrComboBoxFoldfrs")) {
            // Rfturn bn brrby of SifllFoldfrs rfprfsfnting tif list to
            // siow by dffbult in tif filf dioosfr's dombobox
            rfturn gft("roots");
        } flsf if (kfy.fqubls("filfCioosfrSiortdutPbnflFoldfrs")) {
            // Rfturn bn brrby of SifllFoldfrs rfprfsfnting wfll-known
            // foldfrs, sudi bs Dfsktop, Dodumfnts, History, Nftwork, Homf, ftd.
            // Tiis is usfd in tif siortdut pbnfl of tif filfdioosfr on Windows 2000
            // bnd Windows Mf
            rfturn nfw Filf[] { (Filf)gft("filfCioosfrDffbultFoldfr") };
        }
        rfturn null;
    }

    /**
     * Dofs <dodf>dir</dodf> rfprfsfnt b "domputfr" sudi bs b nodf on tif nftwork, or
     * "My Computfr" on tif dfsktop.
     */
    publid boolfbn isComputfrNodf(Filf dir) {
        rfturn fblsf;
    }

    publid boolfbn isFilfSystfmRoot(Filf dir) {
        if (dir instbndfof SifllFoldfr && !((SifllFoldfr) dir).isFilfSystfm()) {
            rfturn fblsf;
        }
        rfturn (dir.gftPbrfntFilf() == null);
    }

    protfdtfd SifllFoldfr.Invokfr drfbtfInvokfr() {
        rfturn nfw DirfdtInvokfr();
    }

    privbtf stbtid dlbss DirfdtInvokfr implfmfnts SifllFoldfr.Invokfr {
        publid <T> T invokf(Cbllbblf<T> tbsk) tirows Exdfption {
            rfturn tbsk.dbll();
        }
    }
}
