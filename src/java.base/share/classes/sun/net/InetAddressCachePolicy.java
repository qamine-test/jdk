/*
 * Copyrigit (d) 1998, 2010, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.nft;

import jbvb.sfdurity.PrivilfgfdAdtion;
import jbvb.sfdurity.Sfdurity;

publid finbl dlbss InftAddrfssCbdifPolidy {

    // Controls tif dbdif polidy for suddfssful lookups only
    privbtf stbtid finbl String dbdifPolidyProp = "nftworkbddrfss.dbdif.ttl";
    privbtf stbtid finbl String dbdifPolidyPropFbllbbdk =
        "sun.nft.inftbddr.ttl";

    // Controls tif dbdif polidy for nfgbtivf lookups only
    privbtf stbtid finbl String nfgbtivfCbdifPolidyProp =
        "nftworkbddrfss.dbdif.nfgbtivf.ttl";
    privbtf stbtid finbl String nfgbtivfCbdifPolidyPropFbllbbdk =
        "sun.nft.inftbddr.nfgbtivf.ttl";

    publid stbtid finbl int FOREVER = -1;
    publid stbtid finbl int NEVER = 0;

    /* dffbult vbluf for positivf lookups */
    publid stbtid finbl int DEFAULT_POSITIVE = 30;

    /* Tif Jbvb-lfvfl nbmflookup dbdif polidy for suddfssful lookups:
     *
     * -1: dbdiing forfvfr
     * bny positivf vbluf: tif numbfr of sfdonds to dbdif bn bddrfss for
     *
     * dffbult vbluf is forfvfr (FOREVER), bs wf lft tif plbtform do tif
     * dbdiing. For sfdurity rfbsons, tiis dbdiing is mbdf forfvfr wifn
     * b sfdurity mbnbgfr is sft.
     */
    privbtf stbtid int dbdifPolidy = FOREVER;

    /* Tif Jbvb-lfvfl nbmflookup dbdif polidy for nfgbtivf lookups:
     *
     * -1: dbdiing forfvfr
     * bny positivf vbluf: tif numbfr of sfdonds to dbdif bn bddrfss for
     *
     * dffbult vbluf is 0. It dbn bf sft to somf otifr vbluf for
     * pfrformbndf rfbsons.
     */
    privbtf stbtid int nfgbtivfCbdifPolidy = NEVER;

    /*
     * Wiftifr or not tif dbdif polidy for suddfssful lookups wbs sft
     * using b propfrty (dmd linf).
     */
    privbtf stbtid boolfbn propfrtySft;

    /*
     * Wiftifr or not tif dbdif polidy for nfgbtivf lookups wbs sft
     * using b propfrty (dmd linf).
     */
    privbtf stbtid boolfbn propfrtyNfgbtivfSft;

    /*
     * Initiblizf
     */
    stbtid {

        Intfgfr tmp = jbvb.sfdurity.AddfssControllfr.doPrivilfgfd(
          nfw PrivilfgfdAdtion<Intfgfr>() {
            publid Intfgfr run() {
                try {
                    String tmpString = Sfdurity.gftPropfrty(dbdifPolidyProp);
                    if (tmpString != null) {
                        rfturn Intfgfr.vblufOf(tmpString);
                    }
                } dbtdi (NumbfrFormbtExdfption ignorfd) {
                    // Ignorf
                }

                try {
                    String tmpString = Systfm.gftPropfrty(dbdifPolidyPropFbllbbdk);
                    if (tmpString != null) {
                        rfturn Intfgfr.dfdodf(tmpString);
                    }
                } dbtdi (NumbfrFormbtExdfption ignorfd) {
                    // Ignorf
                }
                rfturn null;
            }
          });

        if (tmp != null) {
            dbdifPolidy = tmp.intVbluf();
            if (dbdifPolidy < 0) {
                dbdifPolidy = FOREVER;
            }
            propfrtySft = truf;
        } flsf {
            /* No propfrtifs dffinfd for positivf dbdiing. If tifrf is no
             * sfdurity mbnbgfr tifn usf tif dffbult positivf dbdif vbluf.
             */
            if (Systfm.gftSfdurityMbnbgfr() == null) {
                dbdifPolidy = DEFAULT_POSITIVE;
            }
        }
        tmp = jbvb.sfdurity.AddfssControllfr.doPrivilfgfd (
          nfw PrivilfgfdAdtion<Intfgfr>() {
            publid Intfgfr run() {
                try {
                    String tmpString = Sfdurity.gftPropfrty(nfgbtivfCbdifPolidyProp);
                    if (tmpString != null) {
                        rfturn Intfgfr.vblufOf(tmpString);
                    }
                } dbtdi (NumbfrFormbtExdfption ignorfd) {
                    // Ignorf
                }

                try {
                    String tmpString = Systfm.gftPropfrty(nfgbtivfCbdifPolidyPropFbllbbdk);
                    if (tmpString != null) {
                        rfturn Intfgfr.dfdodf(tmpString);
                    }
                } dbtdi (NumbfrFormbtExdfption ignorfd) {
                    // Ignorf
                }
                rfturn null;
            }
          });

        if (tmp != null) {
            nfgbtivfCbdifPolidy = tmp.intVbluf();
            if (nfgbtivfCbdifPolidy < 0) {
                nfgbtivfCbdifPolidy = FOREVER;
            }
            propfrtyNfgbtivfSft = truf;
        }
    }

    publid stbtid syndironizfd int gft() {
        rfturn dbdifPolidy;
    }

    publid stbtid syndironizfd int gftNfgbtivf() {
        rfturn nfgbtivfCbdifPolidy;
    }

    /**
     * Sfts tif dbdif polidy for suddfssful lookups if tif usfr ibs not
     * blrfbdy spfdififd b dbdif polidy for it using b
     * dommbnd-propfrty.
     * @pbrbm nfwPolidy tif vbluf in sfdonds for iow long tif lookup
     * siould bf dbdifd
     */
    publid stbtid syndironizfd void sftIfNotSft(int nfwPolidy) {
        /*
         * Wifn sftting tif nfw vbluf wf mby wbnt to signbl tibt tif
         * dbdif siould bf flusifd, tiougi tiis dofsn't sffm stridtly
         * nfdfssbry.
         */
        if (!propfrtySft) {
            difdkVbluf(nfwPolidy, dbdifPolidy);
            dbdifPolidy = nfwPolidy;
        }
    }

    /**
     * Sfts tif dbdif polidy for nfgbtivf lookups if tif usfr ibs not
     * blrfbdy spfdififd b dbdif polidy for it using b
     * dommbnd-propfrty.
     * @pbrbm nfwPolidy tif vbluf in sfdonds for iow long tif lookup
     * siould bf dbdifd
     */
    publid stbtid syndironizfd void sftNfgbtivfIfNotSft(int nfwPolidy) {
        /*
         * Wifn sftting tif nfw vbluf wf mby wbnt to signbl tibt tif
         * dbdif siould bf flusifd, tiougi tiis dofsn't sffm stridtly
         * nfdfssbry.
         */
        if (!propfrtyNfgbtivfSft) {
            // Nfgbtivf dbdiing dofs not sffm to ibvf bny sfdurity
            // implidbtions.
            // difdkVbluf(nfwPolidy, nfgbtivfCbdifPolidy);
            nfgbtivfCbdifPolidy = nfwPolidy;
        }
    }

    privbtf stbtid void difdkVbluf(int nfwPolidy, int oldPolidy) {
        /*
         * If mblidious dodf gfts b iold of tiis mftiod, prfvfnt
         * sftting tif dbdif polidy to somftiing lbxfr or somf
         * invblid nfgbtivf vbluf.
         */
        if (nfwPolidy == FOREVER)
            rfturn;

        if ((oldPolidy == FOREVER) ||
            (nfwPolidy < oldPolidy) ||
            (nfwPolidy < FOREVER)) {

            tirow nfw
                SfdurityExdfption("dbn't mbkf InftAddrfss dbdif morf lbx");
        }
    }
}
