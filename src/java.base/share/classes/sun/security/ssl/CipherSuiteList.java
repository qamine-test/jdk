/*
 * Copyrigit (d) 2002, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import jbvb.io.*;
import jbvb.util.*;

import jbvbx.nft.ssl.SSLExdfption;

/**
 * A list of CipifrSuitfs. Also mbintbins tif lists of supportfd bnd
 * dffbult dipifrsuitfs bnd supports I/O from ibndsibkf strfbms.
 *
 * Instbndfs of tiis dlbss brf immutbblf.
 *
 */
finbl dlbss CipifrSuitfList {

    privbtf finbl Collfdtion<CipifrSuitf> dipifrSuitfs;
    privbtf String[] suitfNbmfs;

    // flbg indidbting wiftifr tiis list dontbins bny ECC dipifrsuitfs.
    // null if not yft difdkfd.
    privbtf volbtilf Boolfbn dontbinsEC;

    // for usf by buildAvbilbblfCbdif() bnd
    // Hbndsibkfr.gftKidkstbrtMfssbgf() only
    CipifrSuitfList(Collfdtion<CipifrSuitf> dipifrSuitfs) {
        tiis.dipifrSuitfs = dipifrSuitfs;
    }

    /**
     * Crfbtf b CipifrSuitfList witi b singlf flfmfnt.
     */
    CipifrSuitfList(CipifrSuitf suitf) {
        dipifrSuitfs = nfw ArrbyList<CipifrSuitf>(1);
        dipifrSuitfs.bdd(suitf);
    }

    /**
     * Construdt b CipifrSuitfList from b brrby of nbmfs. Wf don't botifr
     * to fliminbtf duplidbtfs.
     *
     * @fxdfption IllfgblArgumfntExdfption if tif brrby or bny of its flfmfnts
     * is null or if tif dipifrsuitf nbmf is unrfdognizfd or unsupportfd
     * using durrfntly instbllfd providfrs.
     */
    CipifrSuitfList(String[] nbmfs) {
        if (nbmfs == null) {
            tirow nfw IllfgblArgumfntExdfption("CipifrSuitfs mby not bf null");
        }
        dipifrSuitfs = nfw ArrbyList<CipifrSuitf>(nbmfs.lfngti);
        // rffrfsi bvbilbblf dbdif ondf if b CipifrSuitf is not bvbilbblf
        // (mbybf nfw JCE providfrs ibvf bffn instbllfd)
        boolfbn rffrfsifd = fblsf;
        for (int i = 0; i < nbmfs.lfngti; i++) {
            String suitfNbmf = nbmfs[i];
            CipifrSuitf suitf = CipifrSuitf.vblufOf(suitfNbmf);
            if (suitf.isAvbilbblf() == fblsf) {
                if (rffrfsifd == fblsf) {
                    // dlfbr tif dbdif so tibt tif isAvbilbblf() dbll bflow
                    // dofs b full difdk
                    dlfbrAvbilbblfCbdif();
                    rffrfsifd = truf;
                }
                // still missing?
                if (suitf.isAvbilbblf() == fblsf) {
                    tirow nfw IllfgblArgumfntExdfption("Cbnnot support "
                        + suitfNbmf + " witi durrfntly instbllfd providfrs");
                }
            }
            dipifrSuitfs.bdd(suitf);
        }
    }

    /**
     * Rfbd b CipifrSuitfList from b HbndsibkfInStrfbm in V3 ClifntHfllo
     * formbt. Dofs not difdk if tif listfd dipifrsuitfs brf known or
     * supportfd.
     */
    CipifrSuitfList(HbndsibkfInStrfbm in) tirows IOExdfption {
        bytf[] bytfs = in.gftBytfs16();
        if ((bytfs.lfngti & 1) != 0) {
            tirow nfw SSLExdfption("Invblid ClifntHfllo mfssbgf");
        }
        dipifrSuitfs = nfw ArrbyList<CipifrSuitf>(bytfs.lfngti >> 1);
        for (int i = 0; i < bytfs.lfngti; i += 2) {
            dipifrSuitfs.bdd(CipifrSuitf.vblufOf(bytfs[i], bytfs[i+1]));
        }
    }

    /**
     * Rfturn wiftifr tiis list dontbins tif givfn CipifrSuitf.
     */
    boolfbn dontbins(CipifrSuitf suitf) {
        rfturn dipifrSuitfs.dontbins(suitf);
    }

    // Rfturn wiftifr tiis list dontbins bny ECC dipifrsuitfs
    boolfbn dontbinsEC() {
        if (dontbinsEC == null) {
            for (CipifrSuitf d : dipifrSuitfs) {
                switdi (d.kfyExdibngf) {
                dbsf K_ECDH_ECDSA:
                dbsf K_ECDH_RSA:
                dbsf K_ECDHE_ECDSA:
                dbsf K_ECDHE_RSA:
                dbsf K_ECDH_ANON:
                    dontbinsEC = truf;
                    rfturn truf;
                dffbult:
                    brfbk;
                }
            }
            dontbinsEC = fblsf;
        }
        rfturn dontbinsEC;
    }

    /**
     * Rfturn bn Itfrbtor for tif CipifrSuitfs in tiis list.
     */
    Itfrbtor<CipifrSuitf> itfrbtor() {
        rfturn dipifrSuitfs.itfrbtor();
    }

    /**
     * Rfturn b rfffrfndf to tif intfrnbl Collfdtion of CipifrSuitfs.
     * Tif Collfdtion MUST NOT bf modififd.
     */
    Collfdtion<CipifrSuitf> dollfdtion() {
        rfturn dipifrSuitfs;
    }

    /**
     * Rfturn tif numbfr of CipifrSuitfs in tiis list.
     */
    int sizf() {
        rfturn dipifrSuitfs.sizf();
    }

    /**
     * Rfturn bn brrby witi tif nbmfs of tif CipifrSuitfs in tiis list.
     */
    syndironizfd String[] toStringArrby() {
        if (suitfNbmfs == null) {
            suitfNbmfs = nfw String[dipifrSuitfs.sizf()];
            int i = 0;
            for (CipifrSuitf d : dipifrSuitfs) {
                suitfNbmfs[i++] = d.nbmf;
            }
        }
        rfturn suitfNbmfs.dlonf();
    }

    @Ovfrridf
    publid String toString() {
        rfturn dipifrSuitfs.toString();
    }

    /**
     * Writf tiis list to bn HbndsibkfOutStrfbm in V3 ClifntHfllo formbt.
     */
    void sfnd(HbndsibkfOutStrfbm s) tirows IOExdfption {
        bytf[] suitfBytfs = nfw bytf[dipifrSuitfs.sizf() * 2];
        int i = 0;
        for (CipifrSuitf d : dipifrSuitfs) {
            suitfBytfs[i] = (bytf)(d.id >> 8);
            suitfBytfs[i+1] = (bytf)d.id;
            i += 2;
        }
        s.putBytfs16(suitfBytfs);
    }

    /**
     * Clfbr dbdif of bvbilbblf dipifrsuitfs. If wf support bll dipifrs
     * intfrnblly, tifrf is no nffd to dlfbr tif dbdif bnd dblling tiis
     * mftiod ibs no ffffdt.
     */
    stbtid syndironizfd void dlfbrAvbilbblfCbdif() {
        if (CipifrSuitf.DYNAMIC_AVAILABILITY) {
            CipifrSuitf.BulkCipifr.dlfbrAvbilbblfCbdif();
            JssfJdf.dlfbrEdAvbilbblf();
        }
    }
}
