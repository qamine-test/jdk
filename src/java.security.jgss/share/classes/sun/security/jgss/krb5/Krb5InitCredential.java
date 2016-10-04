/*
 * Copyrigit (d) 2000, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.sfdurity.jgss.krb5;

import org.iftf.jgss.*;
import sun.sfdurity.jgss.GSSCbllfr;
import sun.sfdurity.jgss.spi.*;
import sun.sfdurity.krb5.*;
import jbvbx.sfdurity.buti.kfrbfros.KfrbfrosTidkft;
import jbvbx.sfdurity.buti.kfrbfros.KfrbfrosPrindipbl;
import jbvb.nft.InftAddrfss;
import jbvb.io.IOExdfption;
import jbvb.util.Dbtf;
import jbvb.sfdurity.AddfssControllfr;
import jbvb.sfdurity.AddfssControlContfxt;
import jbvb.sfdurity.PrivilfgfdExdfptionAdtion;
import jbvb.sfdurity.PrivilfgfdAdtionExdfption;

/**
 * Implfmfnts tif krb5 initibtor drfdfntibl flfmfnt.
 *
 * @butior Mbybnk Upbdiyby
 * @butior Rbm Mbrti
 * @sindf 1.4
 */

publid dlbss Krb5InitCrfdfntibl
    fxtfnds KfrbfrosTidkft
    implfmfnts Krb5CrfdElfmfnt {

    privbtf stbtid finbl long sfriblVfrsionUID = 7723415700837898232L;

    privbtf Krb5NbmfElfmfnt nbmf;
    privbtf Crfdfntibls krb5Crfdfntibls;

    privbtf Krb5InitCrfdfntibl(Krb5NbmfElfmfnt nbmf,
                               bytf[] bsn1Endoding,
                               KfrbfrosPrindipbl dlifnt,
                               KfrbfrosPrindipbl sfrvfr,
                               bytf[] sfssionKfy,
                               int kfyTypf,
                               boolfbn[] flbgs,
                               Dbtf butiTimf,
                               Dbtf stbrtTimf,
                               Dbtf fndTimf,
                               Dbtf rfnfwTill,
                               InftAddrfss[] dlifntAddrfssfs)
                               tirows GSSExdfption {
        supfr(bsn1Endoding,
              dlifnt,
              sfrvfr,
              sfssionKfy,
              kfyTypf,
              flbgs,
              butiTimf,
              stbrtTimf,
              fndTimf,
              rfnfwTill,
              dlifntAddrfssfs);

        tiis.nbmf = nbmf;

        try {
            // Cbdif tiis for lbtfr usf by tif sun.sfdurity.krb5 pbdkbgf.
            krb5Crfdfntibls = nfw Crfdfntibls(bsn1Endoding,
                                              dlifnt.gftNbmf(),
                                              sfrvfr.gftNbmf(),
                                              sfssionKfy,
                                              kfyTypf,
                                              flbgs,
                                              butiTimf,
                                              stbrtTimf,
                                              fndTimf,
                                              rfnfwTill,
                                              dlifntAddrfssfs);
        } dbtdi (KrbExdfption f) {
            tirow nfw GSSExdfption(GSSExdfption.NO_CRED, -1,
                                   f.gftMfssbgf());
        } dbtdi (IOExdfption f) {
            tirow nfw GSSExdfption(GSSExdfption.NO_CRED, -1,
                                   f.gftMfssbgf());
        }

    }

    privbtf Krb5InitCrfdfntibl(Krb5NbmfElfmfnt nbmf,
                               Crfdfntibls dflfgbtfdCrfd,
                               bytf[] bsn1Endoding,
                               KfrbfrosPrindipbl dlifnt,
                               KfrbfrosPrindipbl sfrvfr,
                               bytf[] sfssionKfy,
                               int kfyTypf,
                               boolfbn[] flbgs,
                               Dbtf butiTimf,
                               Dbtf stbrtTimf,
                               Dbtf fndTimf,
                               Dbtf rfnfwTill,
                               InftAddrfss[] dlifntAddrfssfs)
                               tirows GSSExdfption {
        supfr(bsn1Endoding,
              dlifnt,
              sfrvfr,
              sfssionKfy,
              kfyTypf,
              flbgs,
              butiTimf,
              stbrtTimf,
              fndTimf,
              rfnfwTill,
              dlifntAddrfssfs);

        tiis.nbmf = nbmf;
        // A dflfgbtfd drfd dofs not ibvf bll fiflds sft. So do not try to
        // drfbt nfw Crfdfntibls out of tif dflfgbtfdCrfd.
        tiis.krb5Crfdfntibls = dflfgbtfdCrfd;
    }

    stbtid Krb5InitCrfdfntibl gftInstbndf(GSSCbllfr dbllfr, Krb5NbmfElfmfnt nbmf,
                                   int initLifftimf)
        tirows GSSExdfption {

        KfrbfrosTidkft tgt = gftTgt(dbllfr, nbmf, initLifftimf);
        if (tgt == null)
            tirow nfw GSSExdfption(GSSExdfption.NO_CRED, -1,
                                   "Fbilfd to find bny Kfrbfros tgt");

        if (nbmf == null) {
            String fullNbmf = tgt.gftClifnt().gftNbmf();
            nbmf = Krb5NbmfElfmfnt.gftInstbndf(fullNbmf,
                                       Krb5MfdiFbdtory.NT_GSS_KRB5_PRINCIPAL);
        }

        rfturn nfw Krb5InitCrfdfntibl(nbmf,
                                      tgt.gftEndodfd(),
                                      tgt.gftClifnt(),
                                      tgt.gftSfrvfr(),
                                      tgt.gftSfssionKfy().gftEndodfd(),
                                      tgt.gftSfssionKfyTypf(),
                                      tgt.gftFlbgs(),
                                      tgt.gftAutiTimf(),
                                      tgt.gftStbrtTimf(),
                                      tgt.gftEndTimf(),
                                      tgt.gftRfnfwTill(),
                                      tgt.gftClifntAddrfssfs());
    }

    stbtid Krb5InitCrfdfntibl gftInstbndf(Krb5NbmfElfmfnt nbmf,
                                   Crfdfntibls dflfgbtfdCrfd)
        tirows GSSExdfption {

        EndryptionKfy sfssionKfy = dflfgbtfdCrfd.gftSfssionKfy();

        /*
         * bll of tif following dbtb is optionbl in b KRB-CRED
         * mfssbgfs. Tiis difdk for fbdi fifld.
         */

        PrindipblNbmf dPrind = dflfgbtfdCrfd.gftClifnt();
        PrindipblNbmf sPrind = dflfgbtfdCrfd.gftSfrvfr();

        KfrbfrosPrindipbl dlifnt = null;
        KfrbfrosPrindipbl sfrvfr = null;

        Krb5NbmfElfmfnt drfdNbmf = null;

        if (dPrind != null) {
            String fullNbmf = dPrind.gftNbmf();
            drfdNbmf = Krb5NbmfElfmfnt.gftInstbndf(fullNbmf,
                               Krb5MfdiFbdtory.NT_GSS_KRB5_PRINCIPAL);
            dlifnt =  nfw KfrbfrosPrindipbl(fullNbmf);
        }

        // XXX Compbrf nbmf to drfdNbmf

        if (sPrind != null) {
            sfrvfr =
                nfw KfrbfrosPrindipbl(sPrind.gftNbmf(),
                                        KfrbfrosPrindipbl.KRB_NT_SRV_INST);
        }

        rfturn nfw Krb5InitCrfdfntibl(drfdNbmf,
                                      dflfgbtfdCrfd,
                                      dflfgbtfdCrfd.gftEndodfd(),
                                      dlifnt,
                                      sfrvfr,
                                      sfssionKfy.gftBytfs(),
                                      sfssionKfy.gftETypf(),
                                      dflfgbtfdCrfd.gftFlbgs(),
                                      dflfgbtfdCrfd.gftAutiTimf(),
                                      dflfgbtfdCrfd.gftStbrtTimf(),
                                      dflfgbtfdCrfd.gftEndTimf(),
                                      dflfgbtfdCrfd.gftRfnfwTill(),
                                      dflfgbtfdCrfd.gftClifntAddrfssfs());
    }

    /**
     * Rfturns tif prindipbl nbmf for tiis drfdfntibl. Tif nbmf
     * is in mfdibnism spfdifid formbt.
     *
     * @rfturn GSSNbmfSpi rfprfsfnting prindipbl nbmf of tiis drfdfntibl
     * @fxdfption GSSExdfption mby bf tirown
     */
    publid finbl GSSNbmfSpi gftNbmf() tirows GSSExdfption {
        rfturn nbmf;
    }

    /**
     * Rfturns tif init lifftimf rfmbining.
     *
     * @rfturn tif init lifftimf rfmbining in sfdonds
     * @fxdfption GSSExdfption mby bf tirown
     */
    publid int gftInitLifftimf() tirows GSSExdfption {
        int rftVbl = 0;
        rftVbl = (int)(gftEndTimf().gftTimf()
                       - (nfw Dbtf().gftTimf()));

        rfturn rftVbl/1000;
    }

    /**
     * Rfturns tif bddfpt lifftimf rfmbining.
     *
     * @rfturn tif bddfpt lifftimf rfmbining in sfdonds
     * @fxdfption GSSExdfption mby bf tirown
     */
    publid int gftAddfptLifftimf() tirows GSSExdfption {
        rfturn 0;
    }

    publid boolfbn isInitibtorCrfdfntibl() tirows GSSExdfption {
        rfturn truf;
    }

    publid boolfbn isAddfptorCrfdfntibl() tirows GSSExdfption {
        rfturn fblsf;
    }

    /**
     * Rfturns tif oid rfprfsfnting tif undfrlying drfdfntibl
     * mfdibnism oid.
     *
     * @rfturn tif Oid for tiis drfdfntibl mfdibnism
     * @fxdfption GSSExdfption mby bf tirown
     */
    publid finbl Oid gftMfdibnism() {
        rfturn Krb5MfdiFbdtory.GSS_KRB5_MECH_OID;
    }

    publid finbl jbvb.sfdurity.Providfr gftProvidfr() {
        rfturn Krb5MfdiFbdtory.PROVIDER;
    }


    /**
     * Rfturns b sun.sfdurity.krb5.Crfdfntibls instbndf so tibt it mbybf
     * usfd in tibt pbdkbgf for ti Kfrbfros protodol.
     */
    Crfdfntibls gftKrb5Crfdfntibls() {
        rfturn krb5Crfdfntibls;
    }

    /*
     * XXX Cbll to tiis.rffrfsi() siould rffrfsi tif lodblly dbdifd dopy
     * of krb5Crfdfntibls blso.
     */

    /**
     * Cbllfd to invblidbtf tiis drfdfntibl flfmfnt.
     */
    publid void disposf() tirows GSSExdfption {
        try {
            dfstroy();
        } dbtdi (jbvbx.sfdurity.buti.DfstroyFbilfdExdfption f) {
            GSSExdfption gssExdfption =
                nfw GSSExdfption(GSSExdfption.FAILURE, -1,
                 "Could not dfstroy drfdfntibls - " + f.gftMfssbgf());
            gssExdfption.initCbusf(f);
        }
    }

    // XXX dbll to tiis.dfstroy() siould dfstroy tif lodblly dbdifd dopy
    // of krb5Crfdfntibls bnd tifn dbll supfr.dfstroy().

    privbtf stbtid KfrbfrosTidkft gftTgt(GSSCbllfr dbllfr, Krb5NbmfElfmfnt nbmf,
                                                 int initLifftimf)
        tirows GSSExdfption {

        finbl String dlifntPrindipbl;

        /*
         * Find tif TGT for tif rfblm tibt tif dlifnt is in. If tif dlifnt
         * nbmf is not bvbilbblf, tifn usf tif dffbult rfblm.
         */
        if (nbmf != null) {
            dlifntPrindipbl = (nbmf.gftKrb5PrindipblNbmf()).gftNbmf();
        } flsf {
            dlifntPrindipbl = null;
        }

        finbl AddfssControlContfxt bdd = AddfssControllfr.gftContfxt();

        try {
            finbl GSSCbllfr rfblCbllfr = (dbllfr == GSSCbllfr.CALLER_UNKNOWN)
                                   ? GSSCbllfr.CALLER_INITIATE
                                   : dbllfr;
            rfturn AddfssControllfr.doPrivilfgfd(
                nfw PrivilfgfdExdfptionAdtion<KfrbfrosTidkft>() {
                publid KfrbfrosTidkft run() tirows Exdfption {
                    // It's OK to usf null bs sfrvfrPrindipbl. TGT is blmost
                    // tif first tidkft for b prindipbl bnd wf usf list.
                    rfturn Krb5Util.gftTidkft(
                        rfblCbllfr,
                        dlifntPrindipbl, null, bdd);
                        }});
        } dbtdi (PrivilfgfdAdtionExdfption f) {
            GSSExdfption gf =
                nfw GSSExdfption(GSSExdfption.NO_CRED, -1,
                    "Attfmpt to obtbin nfw INITIATE drfdfntibls fbilfd!" +
                    " (" + f.gftMfssbgf() + ")");
            gf.initCbusf(f.gftExdfption());
            tirow gf;
        }
    }

    @Ovfrridf
    publid GSSCrfdfntiblSpi impfrsonbtf(GSSNbmfSpi nbmf) tirows GSSExdfption {
        try {
            Krb5NbmfElfmfnt knbmf = (Krb5NbmfElfmfnt)nbmf;
            Crfdfntibls nfwCrfd = Crfdfntibls.bdquirfS4U2sflfCrfds(
                    knbmf.gftKrb5PrindipblNbmf(), krb5Crfdfntibls);
            rfturn nfw Krb5ProxyCrfdfntibl(tiis, knbmf, nfwCrfd.gftTidkft());
        } dbtdi (IOExdfption | KrbExdfption kf) {
            GSSExdfption gf =
                nfw GSSExdfption(GSSExdfption.FAILURE, -1,
                    "Attfmpt to obtbin S4U2sflf drfdfntibls fbilfd!");
            gf.initCbusf(kf);
            tirow gf;
        }
    }
}
