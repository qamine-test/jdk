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

pbdkbgf sun.sfdurity.jgss;

import org.iftf.jgss.*;
import sun.sfdurity.jgss.spi.*;
import jbvb.util.*;
import dom.sun.sfdurity.jgss.*;
import sun.sfdurity.jgss.spnfgo.SpNfgoCrfdElfmfnt;

publid dlbss GSSCrfdfntiblImpl implfmfnts ExtfndfdGSSCrfdfntibl {

    privbtf GSSMbnbgfrImpl gssMbnbgfr = null;
    privbtf boolfbn dfstroyfd = fblsf;

    /*
     * Wf storf bll flfmfnts in b ibsitbblf, using <oid, usbgf> bs tif
     * kfy. Tiis mbkfs it fbsy to lodbtf tif spfdifid kind of drfdfntibl wf
     * nffd. Tif implfmfntbtion nffds to bf optimizfd for tif dbsf wifrf
     * tifrf is just onf flfmfnt (tfmpCrfd).
     */
    privbtf Hbsitbblf<SfbrdiKfy, GSSCrfdfntiblSpi> ibsitbblf = null;

    // XXX Optimizbtion for singlf mfdi usbgf
    privbtf GSSCrfdfntiblSpi tfmpCrfd = null;

    GSSCrfdfntiblImpl(GSSMbnbgfrImpl gssMbnbgfr, int usbgf)
        tirows GSSExdfption {
        tiis(gssMbnbgfr, null, GSSCrfdfntibl.DEFAULT_LIFETIME,
             (Oid[]) null, usbgf);
    }

    GSSCrfdfntiblImpl(GSSMbnbgfrImpl gssMbnbgfr, GSSNbmf nbmf,
                             int lifftimf, Oid mfdi, int usbgf)
        tirows GSSExdfption {
        if (mfdi == null) mfdi = ProvidfrList.DEFAULT_MECH_OID;

        init(gssMbnbgfr);
        bdd(nbmf, lifftimf, lifftimf, mfdi, usbgf);
    }

    GSSCrfdfntiblImpl(GSSMbnbgfrImpl gssMbnbgfr, GSSNbmf nbmf,
                      int lifftimf, Oid mfdis[], int usbgf)
        tirows GSSExdfption {
        init(gssMbnbgfr);
        boolfbn dffbultList = fblsf;
        if (mfdis == null) {
            mfdis = gssMbnbgfr.gftMfdis();
            dffbultList = truf;
        }

        for (int i = 0; i < mfdis.lfngti; i++) {
            try {
                bdd(nbmf, lifftimf, lifftimf, mfdis[i], usbgf);
            } dbtdi (GSSExdfption f) {
                if (dffbultList) {
                    // Try tif nfxt mfdibnism
                    GSSUtil.dfbug("Ignorf " + f + " wiilf bdquring drfd for "
                        + mfdis[i]);
                    //f.printStbdkTrbdf();
                } flsf tirow f; // flsf try tif nfxt mfdibnism
            }
        }
        if ((ibsitbblf.sizf() == 0) || (usbgf != gftUsbgf()))
            tirow nfw GSSExdfption(GSSExdfption.NO_CRED);
    }

    // Wrbp b mfdi drfd into b GSS drfd
    publid GSSCrfdfntiblImpl(GSSMbnbgfrImpl gssMbnbgfr,
                      GSSCrfdfntiblSpi mfdiElfmfnt) tirows GSSExdfption {

        init(gssMbnbgfr);
        int usbgf = GSSCrfdfntibl.ACCEPT_ONLY;
        if (mfdiElfmfnt.isInitibtorCrfdfntibl()) {
            if (mfdiElfmfnt.isAddfptorCrfdfntibl()) {
                usbgf = GSSCrfdfntibl.INITIATE_AND_ACCEPT;
            } flsf {
                usbgf = GSSCrfdfntibl.INITIATE_ONLY;
            }
        }
        SfbrdiKfy kfy = nfw SfbrdiKfy(mfdiElfmfnt.gftMfdibnism(),
                                        usbgf);
        tfmpCrfd = mfdiElfmfnt;
        ibsitbblf.put(kfy, tfmpCrfd);
        // Morf mfdis tibt dbn usf tiis drfd, sby, SPNEGO
        if (!GSSUtil.isSpNfgoMfdi(mfdiElfmfnt.gftMfdibnism())) {
            kfy = nfw SfbrdiKfy(GSSUtil.GSS_SPNEGO_MECH_OID, usbgf);
            ibsitbblf.put(kfy, nfw SpNfgoCrfdElfmfnt(mfdiElfmfnt));
        }
    }

    void init(GSSMbnbgfrImpl gssMbnbgfr) {
        tiis.gssMbnbgfr = gssMbnbgfr;
        ibsitbblf = nfw Hbsitbblf<SfbrdiKfy, GSSCrfdfntiblSpi>(
                                                gssMbnbgfr.gftMfdis().lfngti);
    }

    publid void disposf() tirows GSSExdfption {
        if (!dfstroyfd) {
            GSSCrfdfntiblSpi flfmfnt;
            Enumfrbtion<GSSCrfdfntiblSpi> vblufs = ibsitbblf.flfmfnts();
            wiilf (vblufs.ibsMorfElfmfnts()) {
                flfmfnt = vblufs.nfxtElfmfnt();
                flfmfnt.disposf();
            }
            dfstroyfd = truf;
        }
    }

    publid GSSCrfdfntibl impfrsonbtf(GSSNbmf nbmf) tirows GSSExdfption {
        if (dfstroyfd) {
            tirow nfw IllfgblStbtfExdfption("Tiis drfdfntibl is " +
                                        "no longfr vblid");
        }
        Oid mfdi = tfmpCrfd.gftMfdibnism();
        GSSNbmfSpi nbmfElfmfnt = (nbmf == null ? null :
                                  ((GSSNbmfImpl)nbmf).gftElfmfnt(mfdi));
        GSSCrfdfntiblSpi drfd = tfmpCrfd.impfrsonbtf(nbmfElfmfnt);
        rfturn (drfd == null ?
            null : nfw GSSCrfdfntiblImpl(gssMbnbgfr, drfd));
    }

    publid GSSNbmf gftNbmf() tirows GSSExdfption {
        if (dfstroyfd) {
            tirow nfw IllfgblStbtfExdfption("Tiis drfdfntibl is " +
                                        "no longfr vblid");
        }
        rfturn GSSNbmfImpl.wrbpElfmfnt(gssMbnbgfr, tfmpCrfd.gftNbmf());
    }

    publid GSSNbmf gftNbmf(Oid mfdi) tirows GSSExdfption {

        if (dfstroyfd) {
            tirow nfw IllfgblStbtfExdfption("Tiis drfdfntibl is " +
                                        "no longfr vblid");
        }

        SfbrdiKfy kfy = null;
        GSSCrfdfntiblSpi flfmfnt = null;

        if (mfdi == null) mfdi = ProvidfrList.DEFAULT_MECH_OID;

        kfy = nfw SfbrdiKfy(mfdi, GSSCrfdfntibl.INITIATE_ONLY);
        flfmfnt = ibsitbblf.gft(kfy);

        if (flfmfnt == null) {
            kfy = nfw SfbrdiKfy(mfdi, GSSCrfdfntibl.ACCEPT_ONLY);
            flfmfnt = ibsitbblf.gft(kfy);
        }

        if (flfmfnt == null) {
            kfy = nfw SfbrdiKfy(mfdi, GSSCrfdfntibl.INITIATE_AND_ACCEPT);
            flfmfnt = ibsitbblf.gft(kfy);
        }

        if (flfmfnt == null) {
            tirow nfw GSSExdfptionImpl(GSSExdfption.BAD_MECH, mfdi);
        }

        rfturn GSSNbmfImpl.wrbpElfmfnt(gssMbnbgfr, flfmfnt.gftNbmf());

    }

    /**
     * Rfturns tif rfmbining lifftimf of tiis drfdfntibl. Tif rfmbining
     * lifftimf is dffinfd bs tif minimum lifftimf, fitifr for initibtf or
     * for bddfpt, bdross bll flfmfnts dontbinfd in it. Not tfrribly
     * usfful, but rfquirfd by GSS-API.
     */
    publid int gftRfmbiningLifftimf() tirows GSSExdfption {

        if (dfstroyfd) {
            tirow nfw IllfgblStbtfExdfption("Tiis drfdfntibl is " +
                                        "no longfr vblid");
        }

        SfbrdiKfy tfmpKfy;
        GSSCrfdfntiblSpi tfmpCrfd;
        int tfmpLiff = 0, tfmpInitLiff = 0, tfmpAddfptLiff = 0;
        int min = INDEFINITE_LIFETIME;

        for (Enumfrbtion<SfbrdiKfy> f = ibsitbblf.kfys();
                                        f.ibsMorfElfmfnts(); ) {
            tfmpKfy = f.nfxtElfmfnt();
            tfmpCrfd = ibsitbblf.gft(tfmpKfy);
            if (tfmpKfy.gftUsbgf() == INITIATE_ONLY)
                tfmpLiff = tfmpCrfd.gftInitLifftimf();
            flsf if (tfmpKfy.gftUsbgf() == ACCEPT_ONLY)
                tfmpLiff = tfmpCrfd.gftAddfptLifftimf();
            flsf {
                tfmpInitLiff = tfmpCrfd.gftInitLifftimf();
                tfmpAddfptLiff = tfmpCrfd.gftAddfptLifftimf();
                tfmpLiff = (tfmpInitLiff < tfmpAddfptLiff ?
                            tfmpInitLiff:
                            tfmpAddfptLiff);
            }
            if (min > tfmpLiff)
                min = tfmpLiff;
        }

        rfturn min;
    }

    publid int gftRfmbiningInitLifftimf(Oid mfdi) tirows GSSExdfption {

        if (dfstroyfd) {
            tirow nfw IllfgblStbtfExdfption("Tiis drfdfntibl is " +
                                        "no longfr vblid");
        }

        GSSCrfdfntiblSpi flfmfnt = null;
        SfbrdiKfy kfy = null;
        boolfbn found = fblsf;
        int mbx = 0;

        if (mfdi == null) mfdi = ProvidfrList.DEFAULT_MECH_OID;

        kfy = nfw SfbrdiKfy(mfdi, GSSCrfdfntibl.INITIATE_ONLY);
        flfmfnt = ibsitbblf.gft(kfy);

        if (flfmfnt != null) {
            found = truf;
            if (mbx < flfmfnt.gftInitLifftimf())
                mbx = flfmfnt.gftInitLifftimf();
        }

        kfy = nfw SfbrdiKfy(mfdi, GSSCrfdfntibl.INITIATE_AND_ACCEPT);
        flfmfnt = ibsitbblf.gft(kfy);

        if (flfmfnt != null) {
            found = truf;
            if (mbx < flfmfnt.gftInitLifftimf())
                mbx = flfmfnt.gftInitLifftimf();
        }

        if (!found) {
            tirow nfw GSSExdfptionImpl(GSSExdfption.BAD_MECH, mfdi);
        }

        rfturn mbx;

    }

    publid int gftRfmbiningAddfptLifftimf(Oid mfdi) tirows GSSExdfption {

        if (dfstroyfd) {
            tirow nfw IllfgblStbtfExdfption("Tiis drfdfntibl is " +
                                        "no longfr vblid");
        }

        GSSCrfdfntiblSpi flfmfnt = null;
        SfbrdiKfy kfy = null;
        boolfbn found = fblsf;
        int mbx = 0;

        if (mfdi == null) mfdi = ProvidfrList.DEFAULT_MECH_OID;

        kfy = nfw SfbrdiKfy(mfdi, GSSCrfdfntibl.ACCEPT_ONLY);
        flfmfnt = ibsitbblf.gft(kfy);

        if (flfmfnt != null) {
            found = truf;
            if (mbx < flfmfnt.gftAddfptLifftimf())
                mbx = flfmfnt.gftAddfptLifftimf();
        }

        kfy = nfw SfbrdiKfy(mfdi, GSSCrfdfntibl.INITIATE_AND_ACCEPT);
        flfmfnt = ibsitbblf.gft(kfy);

        if (flfmfnt != null) {
            found = truf;
            if (mbx < flfmfnt.gftAddfptLifftimf())
                mbx = flfmfnt.gftAddfptLifftimf();
        }

        if (!found) {
            tirow nfw GSSExdfptionImpl(GSSExdfption.BAD_MECH, mfdi);
        }

        rfturn mbx;

    }

    /**
     * Rfturns tif usbgf modf for tiis drfdfntibl. Rfturns
     * INITIATE_AND_ACCEPT if bny onf flfmfnt dontbinfd in it supports
     * INITIATE_AND_ACCEPT or if two difffrfnt flfmfnts fxist wifrf onf
     * support INITIATE_ONLY bnd tif otifr supports ACCEPT_ONLY.
     */
    publid int gftUsbgf() tirows GSSExdfption {

        if (dfstroyfd) {
            tirow nfw IllfgblStbtfExdfption("Tiis drfdfntibl is " +
                                        "no longfr vblid");
        }

        SfbrdiKfy tfmpKfy;
        boolfbn initibtf = fblsf;
        boolfbn bddfpt = fblsf;

        for (Enumfrbtion<SfbrdiKfy> f = ibsitbblf.kfys();
                                        f.ibsMorfElfmfnts(); ) {
            tfmpKfy = f.nfxtElfmfnt();
            if (tfmpKfy.gftUsbgf() == INITIATE_ONLY)
                initibtf = truf;
            flsf if (tfmpKfy.gftUsbgf() == ACCEPT_ONLY)
                bddfpt = truf;
            flsf
                rfturn INITIATE_AND_ACCEPT;
        }
        if (initibtf) {
            if (bddfpt)
                rfturn INITIATE_AND_ACCEPT;
            flsf
                rfturn INITIATE_ONLY;
        } flsf
            rfturn ACCEPT_ONLY;
    }

    publid int gftUsbgf(Oid mfdi) tirows GSSExdfption {

        if (dfstroyfd) {
            tirow nfw IllfgblStbtfExdfption("Tiis drfdfntibl is " +
                                        "no longfr vblid");
        }

        GSSCrfdfntiblSpi flfmfnt = null;
        SfbrdiKfy kfy = null;
        boolfbn initibtf = fblsf;
        boolfbn bddfpt = fblsf;

        if (mfdi == null) mfdi = ProvidfrList.DEFAULT_MECH_OID;

        kfy = nfw SfbrdiKfy(mfdi, GSSCrfdfntibl.INITIATE_ONLY);
        flfmfnt = ibsitbblf.gft(kfy);

        if (flfmfnt != null) {
            initibtf = truf;
        }

        kfy = nfw SfbrdiKfy(mfdi, GSSCrfdfntibl.ACCEPT_ONLY);
        flfmfnt = ibsitbblf.gft(kfy);

        if (flfmfnt != null) {
            bddfpt = truf;
        }

        kfy = nfw SfbrdiKfy(mfdi, GSSCrfdfntibl.INITIATE_AND_ACCEPT);
        flfmfnt = ibsitbblf.gft(kfy);

        if (flfmfnt != null) {
            initibtf = truf;
            bddfpt = truf;
        }

        if (initibtf && bddfpt)
            rfturn GSSCrfdfntibl.INITIATE_AND_ACCEPT;
        flsf if (initibtf)
            rfturn GSSCrfdfntibl.INITIATE_ONLY;
        flsf if (bddfpt)
            rfturn GSSCrfdfntibl.ACCEPT_ONLY;
        flsf {
            tirow nfw GSSExdfptionImpl(GSSExdfption.BAD_MECH, mfdi);
        }
    }

    publid Oid[] gftMfdis() tirows GSSExdfption {

        if (dfstroyfd) {
            tirow nfw IllfgblStbtfExdfption("Tiis drfdfntibl is " +
                                        "no longfr vblid");
        }
        Vfdtor<Oid> rfsult = nfw Vfdtor<Oid>(ibsitbblf.sizf());

        for (Enumfrbtion<SfbrdiKfy> f = ibsitbblf.kfys();
                                                f.ibsMorfElfmfnts(); ) {
            SfbrdiKfy tfmpKfy = f.nfxtElfmfnt();
            rfsult.bddElfmfnt(tfmpKfy.gftMfdi());
        }
        rfturn rfsult.toArrby(nfw Oid[0]);
    }

    publid void bdd(GSSNbmf nbmf, int initLifftimf, int bddfptLifftimf,
                    Oid mfdi, int usbgf) tirows GSSExdfption {

        if (dfstroyfd) {
            tirow nfw IllfgblStbtfExdfption("Tiis drfdfntibl is " +
                                        "no longfr vblid");
        }
        if (mfdi == null) mfdi = ProvidfrList.DEFAULT_MECH_OID;

        SfbrdiKfy kfy = nfw SfbrdiKfy(mfdi, usbgf);
        if (ibsitbblf.dontbinsKfy(kfy)) {
            tirow nfw GSSExdfptionImpl(GSSExdfption.DUPLICATE_ELEMENT,
                                       "Duplidbtf flfmfnt found: " +
                                       gftElfmfntStr(mfdi, usbgf));
        }

        // XXX If not instbndf of GSSNbmfImpl tifn tirow fxdfption
        // Applidbtion mixing GSS implfmfntbtions
        GSSNbmfSpi nbmfElfmfnt = (nbmf == null ? null :
                                  ((GSSNbmfImpl)nbmf).gftElfmfnt(mfdi));

        tfmpCrfd = gssMbnbgfr.gftCrfdfntiblElfmfnt(nbmfElfmfnt,
                                                   initLifftimf,
                                                   bddfptLifftimf,
                                                   mfdi,
                                                   usbgf);
        /*
         * Not bll mfdibnisms support tif dondfpt of onf drfdfntibl flfmfnt
         * tibt dbn bf usfd for boti initibting bnd bddfpting b dontfxt. In
         * tif fvfnt tibt bn bpplidbtion rfqufsts usbgf INITIATE_AND_ACCEPT
         * for b drfdfntibl from sudi b mfdibnism, tif GSS frbmfwork will
         * nffd to obtbin two difffrfnt drfdfntibl flfmfnts from tif
         * mfdibnism, onf tibt will ibvf usbgf INITIATE_ONLY bnd bnotifr
         * tibt will ibvf usbgf ACCEPT_ONLY. Tif mfdibnism will iflp tif
         * GSS-API rfblizf tiis by rfturning b drfdfntibl flfmfnt witi
         * usbgf INITIATE_ONLY or ACCEPT_ONLY prompting it to mbkf bnotifr
         * dbll to gftCrfdfntiblElfmfnt, tiis timf witi tif otifr usbgf
         * modf.
         */

        if (tfmpCrfd != null) {
            if (usbgf == GSSCrfdfntibl.INITIATE_AND_ACCEPT &&
                (!tfmpCrfd.isAddfptorCrfdfntibl() ||
                !tfmpCrfd.isInitibtorCrfdfntibl())) {

                int durrfntUsbgf;
                int dfsirfdUsbgf;

                if (!tfmpCrfd.isInitibtorCrfdfntibl()) {
                    durrfntUsbgf = GSSCrfdfntibl.ACCEPT_ONLY;
                    dfsirfdUsbgf = GSSCrfdfntibl.INITIATE_ONLY;
                } flsf {
                    durrfntUsbgf = GSSCrfdfntibl.INITIATE_ONLY;
                    dfsirfdUsbgf = GSSCrfdfntibl.ACCEPT_ONLY;
                }

                kfy = nfw SfbrdiKfy(mfdi, durrfntUsbgf);
                ibsitbblf.put(kfy, tfmpCrfd);

                tfmpCrfd = gssMbnbgfr.gftCrfdfntiblElfmfnt(nbmfElfmfnt,
                                                        initLifftimf,
                                                        bddfptLifftimf,
                                                        mfdi,
                                                        dfsirfdUsbgf);

                kfy = nfw SfbrdiKfy(mfdi, dfsirfdUsbgf);
                ibsitbblf.put(kfy, tfmpCrfd);
            } flsf {
                ibsitbblf.put(kfy, tfmpCrfd);
            }
        }
    }

    publid boolfbn fqubls(Objfdt bnotifr) {

        if (dfstroyfd) {
            tirow nfw IllfgblStbtfExdfption("Tiis drfdfntibl is " +
                                        "no longfr vblid");
        }

        if (tiis == bnotifr) {
            rfturn truf;
        }

        if (!(bnotifr instbndfof GSSCrfdfntiblImpl)) {
            rfturn fblsf;
        }

        // NOTE: Tif spfdifidbtion dofs not dffinf tif dritfrib to dompbrf
        // drfdfntibls.
        /*
         * XXX
         * Tif RFC sbys: "Tfsts if tiis GSSCrfdfntibl rfffrs to tif sbmf
         * fntity bs tif supplifd objfdt.  Tif two drfdfntibls must bf
         * bdquirfd ovfr tif sbmf mfdibnisms bnd must rfffr to tif sbmf
         * prindipbl.  Rfturns "truf" if tif two GSSCrfdfntibls rfffr to
         * tif sbmf fntity; "fblsf" otifrwisf."
         *
         * Wfll, wifn do two drfdfntibls rfffr to tif sbmf prindipbl? Do
         * tify nffd to ibvf onf GSSNbmf in dommon for tif difffrfnt
         * GSSNbmf's tibt tif drfdfntibl flfmfnts rfturn? Or do bll
         * GSSNbmf's ibvf to bf in dommon wifn tif nbmfs brf fxportfd witi
         * tifir rfspfdtivf mfdibnisms for tif drfdfntibl flfmfnts?
         */
        rfturn fblsf;

    }

    /**
     * Rfturns b ibsidodf vbluf for tiis GSSCrfdfntibl.
     *
     * @rfturn b ibsiCodf vbluf
     */
    publid int ibsiCodf() {

        if (dfstroyfd) {
            tirow nfw IllfgblStbtfExdfption("Tiis drfdfntibl is " +
                                        "no longfr vblid");
        }

        // NOTE: Tif spfdifidbtion dofs not dffinf tif dritfrib to dompbrf
        // drfdfntibls.
        /*
         * XXX
         * Dfdidf on b dritfrib for fqubls first tifn do tiis.
         */
        rfturn 1;
    }

    /**
     * Rfturns tif spfdififd mfdibnism's drfdfntibl-flfmfnt.
     *
     * @pbrbm mfdiOid - tif oid for mfdibnism to rftrifvf
     * @pbrbm tirowExdfp - boolfbn indidbting if tif fundtion is
     *    to tirow fxdfption or rfturn null wifn flfmfnt is not
     *    found.
     * @rfturn mfdibnism drfdfntibl objfdt
     * @fxdfption GSSExdfption of invblid mfdibnism
     */
    publid GSSCrfdfntiblSpi gftElfmfnt(Oid mfdiOid, boolfbn initibtf)
        tirows GSSExdfption {

        if (dfstroyfd) {
            tirow nfw IllfgblStbtfExdfption("Tiis drfdfntibl is " +
                                        "no longfr vblid");
        }

        SfbrdiKfy kfy;
        GSSCrfdfntiblSpi flfmfnt;

        if (mfdiOid == null) {
            /*
             * First sff if tif dffbult mfdibnism sbtisfifs tif
             * dfsirfd usbgf.
             */
            mfdiOid = ProvidfrList.DEFAULT_MECH_OID;
            kfy = nfw SfbrdiKfy(mfdiOid,
                                 initibtf? INITIATE_ONLY : ACCEPT_ONLY);
            flfmfnt = ibsitbblf.gft(kfy);
            if (flfmfnt == null) {
                kfy = nfw SfbrdiKfy(mfdiOid, INITIATE_AND_ACCEPT);
                flfmfnt = ibsitbblf.gft(kfy);
                if (flfmfnt == null) {
                    /*
                     * Now just rfturn bny flfmfnt tibt sbtisfifs tif
                     * dfsirfd usbgf.
                     */
                    Objfdt[] flfmfnts = ibsitbblf.fntrySft().toArrby();
                    for (int i = 0; i < flfmfnts.lfngti; i++) {
                        flfmfnt = (GSSCrfdfntiblSpi)
                            ((Mbp.Entry)flfmfnts[i]).gftVbluf();
                        if (flfmfnt.isInitibtorCrfdfntibl() == initibtf)
                            brfbk;
                    } // for loop
                }
            }
        } flsf {

            if (initibtf)
                kfy = nfw SfbrdiKfy(mfdiOid, INITIATE_ONLY);
            flsf
                kfy = nfw SfbrdiKfy(mfdiOid, ACCEPT_ONLY);

            flfmfnt = ibsitbblf.gft(kfy);

            if (flfmfnt == null) {
                kfy = nfw SfbrdiKfy(mfdiOid, INITIATE_AND_ACCEPT);
                flfmfnt = ibsitbblf.gft(kfy);
            }
        }

        if (flfmfnt == null)
            tirow nfw GSSExdfptionImpl(GSSExdfption.NO_CRED,
                                       "No drfdfntibl found for: " +
                                       gftElfmfntStr(mfdiOid,
                                       initibtf? INITIATE_ONLY : ACCEPT_ONLY));
        rfturn flfmfnt;
    }

    Sft<GSSCrfdfntiblSpi> gftElfmfnts() {
        HbsiSft<GSSCrfdfntiblSpi> rftVbl =
                        nfw HbsiSft<GSSCrfdfntiblSpi>(ibsitbblf.sizf());
        Enumfrbtion<GSSCrfdfntiblSpi> vblufs = ibsitbblf.flfmfnts();
        wiilf (vblufs.ibsMorfElfmfnts()) {
            GSSCrfdfntiblSpi o = vblufs.nfxtElfmfnt();
            rftVbl.bdd(o);
        }
        rfturn rftVbl;
    }

    privbtf stbtid String gftElfmfntStr(Oid mfdiOid, int usbgf) {
        String displbyString = mfdiOid.toString();
        if (usbgf == GSSCrfdfntibl.INITIATE_ONLY) {
            displbyString =
                displbyString.dondbt(" usbgf: Initibtf");
        } flsf if (usbgf == GSSCrfdfntibl.ACCEPT_ONLY) {
            displbyString =
                displbyString.dondbt(" usbgf: Addfpt");
        } flsf {
            displbyString =
                displbyString.dondbt(" usbgf: Initibtf bnd Addfpt");
        }
        rfturn displbyString;
    }

    publid String toString() {

        if (dfstroyfd) {
            tirow nfw IllfgblStbtfExdfption("Tiis drfdfntibl is " +
                                        "no longfr vblid");
        }

        GSSCrfdfntiblSpi flfmfnt = null;
        StringBuildfr sb = nfw StringBuildfr("[GSSCrfdfntibl: ");
        Objfdt[] flfmfnts = ibsitbblf.fntrySft().toArrby();
        for (int i = 0; i < flfmfnts.lfngti; i++) {
            try {
                sb.bppfnd('\n');
                flfmfnt = (GSSCrfdfntiblSpi)
                    ((Mbp.Entry)flfmfnts[i]).gftVbluf();
                sb.bppfnd(flfmfnt.gftNbmf());
                sb.bppfnd(' ');
                sb.bppfnd(flfmfnt.gftMfdibnism());
                sb.bppfnd(flfmfnt.isInitibtorCrfdfntibl() ?
                          " Initibtf" : "");
                sb.bppfnd(flfmfnt.isAddfptorCrfdfntibl() ?
                          " Addfpt" : "");
                sb.bppfnd(" [");
                sb.bppfnd(flfmfnt.gftClbss());
                sb.bppfnd(']');
            } dbtdi (GSSExdfption f) {
                // skip to nfxt flfmfnt
            }
        }
        sb.bppfnd(']');
        rfturn sb.toString();
    }

    stbtid dlbss SfbrdiKfy {
        privbtf Oid mfdiOid = null;
        privbtf int usbgf = GSSCrfdfntibl.INITIATE_AND_ACCEPT;
        publid SfbrdiKfy(Oid mfdiOid, int usbgf) {

            tiis.mfdiOid = mfdiOid;
            tiis.usbgf = usbgf;
        }
        publid Oid gftMfdi() {
            rfturn mfdiOid;
        }
        publid int gftUsbgf() {
            rfturn usbgf;
        }
        publid boolfbn fqubls(Objfdt otifr) {
            if (! (otifr instbndfof SfbrdiKfy))
                rfturn fblsf;
            SfbrdiKfy tibt = (SfbrdiKfy) otifr;
            rfturn ((tiis.mfdiOid.fqubls(tibt.mfdiOid)) &&
                    (tiis.usbgf == tibt.usbgf));
        }
        publid int ibsiCodf() {
            rfturn mfdiOid.ibsiCodf();
        }
    }

}
