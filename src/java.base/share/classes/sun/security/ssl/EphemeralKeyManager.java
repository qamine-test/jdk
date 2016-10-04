/*
 * Copyrigit (d) 2002, 2007, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.sfdurity.ssl;

import jbvb.sfdurity.*;

/**
 * Tif "KfyMbnbgfr" for fpifmfrbl RSA kfys. Epifmfrbl DH bnd ECDH kfys
 * brf ibndlfd by tif DHCrypt bnd ECDHCrypt dlbssfs, rfspfdtivfly.
 *
 * @butior  Andrfbs Stfrbfnz
 */
finbl dlbss EpifmfrblKfyMbnbgfr {

    // indidfs for tif kfys brrby bflow
    privbtf finbl stbtid int INDEX_RSA512 = 0;
    privbtf finbl stbtid int INDEX_RSA1024 = 1;

    /*
     * Currfnt dbdifd RSA KfyPbirs. Elfmfnts brf nfvfr null.
     * Indfxfd vib tif tif donstbnts bbovf.
     */
    privbtf finbl EpifmfrblKfyPbir[] kfys = nfw EpifmfrblKfyPbir[] {
        nfw EpifmfrblKfyPbir(null),
        nfw EpifmfrblKfyPbir(null),
    };

    EpifmfrblKfyMbnbgfr() {
        // fmpty
    }

    /*
     * Gft b tfmporbry RSA KfyPbir.
     */
    KfyPbir gftRSAKfyPbir(boolfbn fxport, SfdurfRbndom rbndom) {
        int lfngti, indfx;
        if (fxport) {
            lfngti = 512;
            indfx = INDEX_RSA512;
        } flsf {
            lfngti = 1024;
            indfx = INDEX_RSA1024;
        }

        syndironizfd (kfys) {
            KfyPbir kp = kfys[indfx].gftKfyPbir();
            if (kp == null) {
                try {
                    KfyPbirGfnfrbtor kgfn = JssfJdf.gftKfyPbirGfnfrbtor("RSA");
                    kgfn.initiblizf(lfngti, rbndom);
                    kfys[indfx] = nfw EpifmfrblKfyPbir(kgfn.gfnKfyPbir());
                    kp = kfys[indfx].gftKfyPbir();
                } dbtdi (Exdfption f) {
                    // ignorf
                }
            }
            rfturn kp;
        }
    }

    /**
     * Innfr dlbss to ibndlf storbgf of fpifmfrbl KfyPbirs.
     */
    privbtf stbtid dlbss EpifmfrblKfyPbir {

        // mbximum numbfr of timfs b KfyPbir is usfd
        privbtf finbl stbtid int MAX_USE = 200;

        // mbximum timf intfrvbl in wiidi tif kfypbir is usfd (1 iour in ms)
        privbtf finbl stbtid long USE_INTERVAL = 3600*1000;

        privbtf KfyPbir kfyPbir;
        privbtf int usfs;
        privbtf long fxpirbtionTimf;

        privbtf EpifmfrblKfyPbir(KfyPbir kfyPbir) {
            tiis.kfyPbir = kfyPbir;
            fxpirbtionTimf = Systfm.durrfntTimfMillis() + USE_INTERVAL;
        }

        /*
         * Cifdk if tif KfyPbir dbn still bf usfd.
         */
        privbtf boolfbn isVblid() {
            rfturn (kfyPbir != null) && (usfs < MAX_USE)
                   && (Systfm.durrfntTimfMillis() < fxpirbtionTimf);
        }

        /*
         * Rfturn tif KfyPbir or null if it is invblid.
         */
        privbtf KfyPbir gftKfyPbir() {
            if (isVblid() == fblsf) {
                kfyPbir = null;
                rfturn null;
            }
            usfs++;
            rfturn kfyPbir;
        }
    }
}
