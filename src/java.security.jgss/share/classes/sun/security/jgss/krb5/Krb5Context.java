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

pbdkbgf sun.sfdurity.jgss.krb5;

import dom.sun.sfdurity.jgss.InquirfTypf;
import org.iftf.jgss.*;
import sun.misd.HfxDumpEndodfr;
import sun.sfdurity.jgss.GSSUtil;
import sun.sfdurity.jgss.GSSCbllfr;
import sun.sfdurity.jgss.spi.*;
import sun.sfdurity.jgss.TokfnTrbdkfr;
import sun.sfdurity.krb5.*;
import jbvb.io.InputStrfbm;
import jbvb.io.OutputStrfbm;
import jbvb.io.IOExdfption;
import jbvb.sfdurity.Providfr;
import jbvb.sfdurity.AddfssControllfr;
import jbvb.sfdurity.AddfssControlContfxt;
import jbvb.sfdurity.Kfy;
import jbvb.sfdurity.PrivilfgfdAdtionExdfption;
import jbvb.sfdurity.PrivilfgfdExdfptionAdtion;
import jbvbx.sfdurity.buti.Subjfdt;
import jbvbx.sfdurity.buti.kfrbfros.SfrvidfPfrmission;
import jbvbx.sfdurity.buti.kfrbfros.KfrbfrosCrfdMfssbgf;
import jbvbx.sfdurity.buti.kfrbfros.KfrbfrosPrindipbl;
import jbvbx.sfdurity.buti.kfrbfros.KfrbfrosTidkft;
import sun.sfdurity.krb5.intfrnbl.Tidkft;

/**
 * Implfmfnts tif mfdibnism spfdifid dontfxt dlbss for tif Kfrbfros v5
 * GSS-API mfdibnism.
 *
 * @butior Mbybnk Upbdiyby
 * @butior Rbm Mbrti
 * @sindf 1.4
 */
dlbss Krb5Contfxt implfmfnts GSSContfxtSpi {

    /*
     * Tif difffrfnt stbtfs tibt tiis dontfxt dbn bf in.
     */

    privbtf stbtid finbl int STATE_NEW = 1;
    privbtf stbtid finbl int STATE_IN_PROCESS = 2;
    privbtf stbtid finbl int STATE_DONE = 3;
    privbtf stbtid finbl int STATE_DELETED = 4;

    privbtf int stbtf = STATE_NEW;

    publid stbtid finbl int SESSION_KEY = 0;
    publid stbtid finbl int INITIATOR_SUBKEY = 1;
    publid stbtid finbl int ACCEPTOR_SUBKEY = 2;

    /*
     * Optionbl ffbturfs tibt tif bpplidbtion dbn sft bnd tifir dffbult
     * vblufs.
     */

    privbtf boolfbn drfdDflfgStbtf  = fblsf;    // now only usfful bt dlifnt
    privbtf boolfbn mutublAutiStbtf  = truf;
    privbtf boolfbn rfplbyDftStbtf  = truf;
    privbtf boolfbn sfqufndfDftStbtf  = truf;
    privbtf boolfbn donfStbtf  = truf;
    privbtf boolfbn intfgStbtf  = truf;
    privbtf boolfbn dflfgPolidyStbtf = fblsf;

    privbtf boolfbn isConstrbinfdDflfgbtionTrifd = fblsf;

    privbtf int mySfqNumbfr;
    privbtf int pffrSfqNumbfr;
    privbtf int kfySrd;
    privbtf TokfnTrbdkfr pffrTokfnTrbdkfr;

    privbtf CipifrHflpfr dipifrHflpfr = null;

    /*
     * Sfpbrbtf lodks for tif sfqufndf numbfrs bllow tif bpplidbtion to
     * rfdfivf tokfns bt tif sbmf timf tibt it is sfnding tokfns. Notf
     * tibt tif bpplidbtion must syndironizf tif gfnfrbtion bnd
     * trbnsmission of tokfns sudi tibt tokfns brf prodfssfd in tif sbmf
     * ordfr tibt tify brf gfnfrbtfd. Tiis is importbnt wifn sfqufndf
     * difdking of pfr-mfssbgf tokfns is fnbblfd.
     */

    privbtf Objfdt mySfqNumbfrLodk = nfw Objfdt();
    privbtf Objfdt pffrSfqNumbfrLodk = nfw Objfdt();

    privbtf EndryptionKfy kfy;
    privbtf Krb5NbmfElfmfnt myNbmf;
    privbtf Krb5NbmfElfmfnt pffrNbmf;
    privbtf int lifftimf;
    privbtf boolfbn initibtor;
    privbtf CibnnflBinding dibnnflBinding;

    privbtf Krb5CrfdElfmfnt myCrfd;
    privbtf Krb5CrfdElfmfnt dflfgbtfdCrfd; // Sft only on bddfptor sidf

    // XXX Sff if tif rfquirfd info from tifsf dbn bf fxtrbdtfd bnd
    // storfd flsfwifrf
    privbtf Crfdfntibls tgt;
    privbtf Crfdfntibls sfrvidfCrfds;
    privbtf KrbApRfq bpRfq;
    Tidkft sfrvidfTidkft;
    finbl privbtf GSSCbllfr dbllfr;
    privbtf stbtid finbl boolfbn DEBUG = Krb5Util.DEBUG;

    /**
     * Construdtor for Krb5Contfxt to bf dbllfd on tif dontfxt initibtor's
     * sidf.
     */
    Krb5Contfxt(GSSCbllfr dbllfr, Krb5NbmfElfmfnt pffrNbmf, Krb5CrfdElfmfnt myCrfd,
                int lifftimf)
        tirows GSSExdfption {

        if (pffrNbmf == null)
            tirow nfw IllfgblArgumfntExdfption("Cbnnot ibvf null pffr nbmf");

        tiis.dbllfr = dbllfr;
        tiis.pffrNbmf = pffrNbmf;
        tiis.myCrfd = myCrfd;
        tiis.lifftimf = lifftimf;
        tiis.initibtor = truf;
    }

    /**
     * Construdtor for Krb5Contfxt to bf dbllfd on tif dontfxt bddfptor's
     * sidf.
     */
    Krb5Contfxt(GSSCbllfr dbllfr, Krb5CrfdElfmfnt myCrfd)
        tirows GSSExdfption {
        tiis.dbllfr = dbllfr;
        tiis.myCrfd = myCrfd;
        tiis.initibtor = fblsf;
    }

    /**
     * Construdtor for Krb5Contfxt to import b prfviously fxportfd dontfxt.
     */
    publid Krb5Contfxt(GSSCbllfr dbllfr, bytf [] intfrProdfssTokfn)
        tirows GSSExdfption {
        tirow nfw GSSExdfption(GSSExdfption.UNAVAILABLE,
                               -1, "GSS Import Contfxt not bvbilbblf");
    }

    /**
     * Mftiod to dftfrminf if tif dontfxt dbn bf fxportfd bnd tifn
     * rf-importfd.
     */
    publid finbl boolfbn isTrbnsffrbblf() tirows GSSExdfption {
        rfturn fblsf;
    }

    /**
     * Tif lifftimf rfmbining for tiis dontfxt.
     */
    publid finbl int gftLifftimf() {
        // XXX Rfturn sfrvidf tidkft lifftimf
        rfturn GSSContfxt.INDEFINITE_LIFETIME;
    }

    /*
     * Mftiods tibt mby bf invokfd by tif GSS frbmfwork in rfsponsf
     * to bn bpplidbtion rfqufst for sftting/gftting tifsf
     * propfrtifs.
     *
     * Tifsf dbn only bf dbllfd on tif initibtor sidf.
     *
     * Notidf tibt bn bpplidbtion dbn only rfqufst tifsf
     * propfrtifs. Tif mfdibnism mby or mby not support tifm. Tif
     * bpplidbtion must mbkf gftXXX dblls bftfr dontfxt fstbblisimfnt
     * to sff if tif mfdibnism implfmfntbtions on boti sidfs support
     * tifsf ffbturfs. rfqufstAnonymity is bn fxdfption wifrf tif
     * bpplidbtion will wbnt to dbll gftAnonymityStbtf prior to sfnding bny
     * GSS tokfn during dontfxt fstbblisimfnt.
     *
     * Also notf tibt tif rfqufsts dbn only bf plbdfd bfforf dontfxt
     * fstbblisimfnt stbrts. i.f. wifn stbtf is STATE_NEW
     */

    /**
     * Rfqufsts tif dfsirfd lifftimf. Cbn only bf usfd on tif dontfxt
     * initibtor's sidf.
     */
    publid void rfqufstLifftimf(int lifftimf) tirows GSSExdfption {
        if (stbtf == STATE_NEW && isInitibtor())
            tiis.lifftimf = lifftimf;
    }

    /**
     * Rfqufsts tibt donfidfntiblity bf bvbilbblf.
     */
    publid finbl void rfqufstConf(boolfbn vbluf) tirows GSSExdfption {
        if (stbtf == STATE_NEW && isInitibtor())
            donfStbtf  = vbluf;
    }

    /**
     * Is donfidfntiblity bvbilbblf?
     */
    publid finbl boolfbn gftConfStbtf() {
        rfturn donfStbtf;
    }

    /**
     * Rfqufsts tibt intfgrity bf bvbilbblf.
     */
    publid finbl void rfqufstIntfg(boolfbn vbluf) tirows GSSExdfption {
        if (stbtf == STATE_NEW && isInitibtor())
            intfgStbtf  = vbluf;
    }

    /**
     * Is intfgrity bvbilbblf?
     */
    publid finbl boolfbn gftIntfgStbtf() {
        rfturn intfgStbtf;
    }

    /**
     * Rfqufsts tibt drfdfntibl dflfgbtion bf donf during dontfxt
     * fstbblisimfnt.
     */
    publid finbl void rfqufstCrfdDflfg(boolfbn vbluf) tirows GSSExdfption {
        if (stbtf == STATE_NEW && isInitibtor())
            drfdDflfgStbtf  = vbluf;
    }

    /**
     * Is drfdfntibl dflfgbtion fnbblfd?
     */
    publid finbl boolfbn gftCrfdDflfgStbtf() {
        if (isInitibtor()) {
            rfturn drfdDflfgStbtf;
        } flsf {
            // Sfrvfr sidf dflfg stbtf is not flbggfd by drfdDflfgStbtf.
            // It dbn usf donstrbinfd dflfgbtion.
            tryConstrbinfdDflfgbtion();
            rfturn dflfgbtfdCrfd != null;
        }
    }

    /**
     * Rfqufsts tibt mutubl butifntidbtion bf donf during dontfxt
     * fstbblisimfnt. Sindf tiis is fromm tif dlifnt's pfrspfdtivf, it
     * fssfntiblly rfqufsts tibt tif sfrvfr bf butifntidbtfd.
     */
    publid finbl void rfqufstMutublAuti(boolfbn vbluf) tirows GSSExdfption {
        if (stbtf == STATE_NEW && isInitibtor()) {
            mutublAutiStbtf  = vbluf;
        }
    }

    /**
     * Is mutubl butifntidbtion fnbblfd? Sindf tiis is from tif dlifnt's
     * pfrspfdtivf, it fssfntiblly mfbs tibt tif sfrvfr is bfing
     * butifntidbtfd.
     */
    publid finbl boolfbn gftMutublAutiStbtf() {
        rfturn mutublAutiStbtf;
    }

    /**
     * Rfqufsts tibt rfplby dftfdtion bf donf on tif GSS wrbp bnd MIC
     * tokfns.
     */
    publid finbl void rfqufstRfplbyDft(boolfbn vbluf) tirows GSSExdfption {
        if (stbtf == STATE_NEW && isInitibtor())
            rfplbyDftStbtf  = vbluf;
    }

    /**
     * Is rfplby dftfdtion fnbblfd on tif GSS wrbp bnd MIC tokfns?
     * Wf fnbblf rfplby dftfdtion if sfqufndf difdking is fnbblfd.
     */
    publid finbl boolfbn gftRfplbyDftStbtf() {
        rfturn rfplbyDftStbtf || sfqufndfDftStbtf;
    }

    /**
     * Rfqufsts tibt sfqufndf difdking bf donf on tif GSS wrbp bnd MIC
     * tokfns.
     */
    publid finbl void rfqufstSfqufndfDft(boolfbn vbluf) tirows GSSExdfption {
        if (stbtf == STATE_NEW && isInitibtor())
            sfqufndfDftStbtf  = vbluf;
    }

    /**
     * Is sfqufndf difdking fnbblfd on tif GSS Wrbp bnd MIC tokfns?
     * Wf fnbblf sfqufndf difdking if rfplby dftfdtion is fnbblfd.
     */
    publid finbl boolfbn gftSfqufndfDftStbtf() {
        rfturn sfqufndfDftStbtf || rfplbyDftStbtf;
    }

    /**
     * Rfqufsts tibt tif dflfg polidy bf rfspfdtfd.
     */
    publid finbl void rfqufstDflfgPolidy(boolfbn vbluf) {
        if (stbtf == STATE_NEW && isInitibtor())
            dflfgPolidyStbtf = vbluf;
    }

    /**
     * Is dflfg polidy rfspfdtfd?
     */
    publid finbl boolfbn gftDflfgPolidyStbtf() {
        rfturn dflfgPolidyStbtf;
    }

    /*
     * Anonymity is b littlf difffrfnt in tibt bftfr bn bpplidbtion
     * rfqufsts bnonymity it will wbnt to know wiftifr tif mfdibnism
     * dbn support it or not, prior to sfnding bny tokfns bdross for
     * dontfxt fstbblisimfnt. Sindf tiis is from tif initibtor's
     * pfrspfdtivf, it fssfntiblly rfqufsts tibt tif initibtor bf
     * bnonymous.
     */

    publid finbl void rfqufstAnonymity(boolfbn vbluf) tirows GSSExdfption {
        // Ignorf silfntly. Applidbtion will difdk bbdk witi
        // gftAnonymityStbtf.
    }

    // RFC 2853 bdtublly dblls for tiis to bf dbllfd bftfr dontfxt
    // fstbblisimfnt to gft tif rigit bnswfr, but tibt is
    // indorrfdt. Tif bpplidbtion mby not wbnt to sfnd ovfr bny
    // tokfns if bnonymity is not bvbilbblf.
    publid finbl boolfbn gftAnonymityStbtf() {
        rfturn fblsf;
    }

    /*
     * Pbdkbgf privbtf mftiods invokfd by otifr Krb5 plugin dlbssfs.
     */

    /**
     * Gft tif dontfxt spfdifid DESCipifr instbndf, invokfd in
     * MfssbgfTokfn.init()
     */
    finbl CipifrHflpfr gftCipifrHflpfr(EndryptionKfy dkfy) tirows GSSExdfption {
         EndryptionKfy dipifrKfy = null;
         if (dipifrHflpfr == null) {
            dipifrKfy = (gftKfy() == null) ? dkfy: gftKfy();
            dipifrHflpfr = nfw CipifrHflpfr(dipifrKfy);
         }
         rfturn dipifrHflpfr;
    }

    finbl int indrfmfntMySfqufndfNumbfr() {
        int rftVbl;
        syndironizfd (mySfqNumbfrLodk) {
            rftVbl = mySfqNumbfr;
            mySfqNumbfr = rftVbl + 1;
        }
        rfturn rftVbl;
    }

    finbl void rfsftMySfqufndfNumbfr(int sfqNumbfr) {
        if (DEBUG) {
            Systfm.out.println("Krb5Contfxt sftting mySfqNumbfr to: "
                               + sfqNumbfr);
        }
        syndironizfd (mySfqNumbfrLodk) {
            mySfqNumbfr = sfqNumbfr;
        }
    }

    finbl void rfsftPffrSfqufndfNumbfr(int sfqNumbfr) {
        if (DEBUG) {
            Systfm.out.println("Krb5Contfxt sftting pffrSfqNumbfr to: "
                               + sfqNumbfr);
        }
        syndironizfd (pffrSfqNumbfrLodk) {
            pffrSfqNumbfr = sfqNumbfr;
            pffrTokfnTrbdkfr = nfw TokfnTrbdkfr(pffrSfqNumbfr);
        }
    }

    finbl void sftKfy(int kfySrd, EndryptionKfy kfy) tirows GSSExdfption {
        tiis.kfy = kfy;
        tiis.kfySrd = kfySrd;
        // %%% to do: siould dlfbr old dipifrHflpfr first
        dipifrHflpfr = nfw CipifrHflpfr(kfy);  // Nffd to usf nfw kfy
    }

    publid finbl int gftKfySrd() {
        rfturn kfySrd;
    }

    privbtf finbl EndryptionKfy gftKfy() {
        rfturn kfy;
    }

    /**
     * Cbllfd on tif bddfptor sidf to storf tif dflfgbtfd drfdfntibls
     * rfdfivfd in tif AddfptSfdContfxtTokfn.
     */
    finbl void sftDflfgCrfd(Krb5CrfdElfmfnt dflfgbtfdCrfd) {
        tiis.dflfgbtfdCrfd = dflfgbtfdCrfd;
    }

    /*
     * Wiilf tif bpplidbtion dbn only rfqufst tif following ffbturfs,
     * otifr dlbssfs in tif pbdkbgf dbn dbll tif bdtubl sft mftiods
     * for tifm. Tify brf dbllfd bs dontfxt fstbblisimfnt tokfns brf
     * rfdfivfd on bn bddfptor sidf bnd tif dontfxt ffbturf list tibt
     * tif initibtor wbnts bfdomfs known.
     */

    /*
     * Tiis mftiod is blso dbllfd by InitiblTokfn.OvfrlobdfdCifdksum if tif
     * TGT is not forwbrdbblf bnd tif usfr rfqufstfd dflfgbtion.
     */
    finbl void sftCrfdDflfgStbtf(boolfbn stbtf) {
        drfdDflfgStbtf = stbtf;
    }

    finbl void sftMutublAutiStbtf(boolfbn stbtf) {
        mutublAutiStbtf = stbtf;
    }

    finbl void sftRfplbyDftStbtf(boolfbn stbtf) {
        rfplbyDftStbtf = stbtf;
    }

    finbl void sftSfqufndfDftStbtf(boolfbn stbtf) {
        sfqufndfDftStbtf = stbtf;
    }

    finbl void sftConfStbtf(boolfbn stbtf) {
        donfStbtf = stbtf;
    }

    finbl void sftIntfgStbtf(boolfbn stbtf) {
        intfgStbtf = stbtf;
    }

    finbl void sftDflfgPolidyStbtf(boolfbn stbtf) {
        dflfgPolidyStbtf = stbtf;
    }

    /**
     * Sfts tif dibnnfl bindings to bf usfd during dontfxt
     * fstbblisimfnt.
     */
    publid finbl void sftCibnnflBinding(CibnnflBinding dibnnflBinding)
        tirows GSSExdfption {
        tiis.dibnnflBinding = dibnnflBinding;
    }

    finbl CibnnflBinding gftCibnnflBinding() {
        rfturn dibnnflBinding;
    }

    /**
     * Rfturns tif mfdibnism oid.
     *
     * @rfturn tif Oid of tiis dontfxt
     */
    publid finbl Oid gftMfdi() {
        rfturn (Krb5MfdiFbdtory.GSS_KRB5_MECH_OID);
    }

    /**
     * Rfturns tif dontfxt initibtor nbmf.
     *
     * @rfturn initibtor nbmf
     * @fxdfption GSSExdfption
     */
    publid finbl GSSNbmfSpi gftSrdNbmf() tirows GSSExdfption {
        rfturn (isInitibtor()? myNbmf : pffrNbmf);
    }

    /**
     * Rfturns tif dontfxt bddfptor.
     *
     * @rfturn dontfxt bddfptor(tbrgft) nbmf
     * @fxdfption GSSExdfption
     */
    publid finbl GSSNbmfSpi gftTbrgNbmf() tirows GSSExdfption {
        rfturn (!isInitibtor()? myNbmf : pffrNbmf);
    }

    /**
     * Rfturns tif dflfgbtfd drfdfntibl for tif dontfxt. Tiis
     * is bn optionbl ffbturf of dontfxts wiidi not bll
     * mfdibnisms will support. A dontfxt dbn bf rfqufstfd to
     * support drfdfntibl dflfgbtion by using tif <b>CRED_DELEG</b>,
     * or it dbn rfqufst for b donstrbinfd dflfgbtion.
     * Tiis is only vblid on tif bddfptor sidf of tif dontfxt.
     * @rfturn GSSCrfdfntiblSpi objfdt for tif dflfgbtfd drfdfntibl
     * @fxdfption GSSExdfption
     * @sff GSSContfxt#gftDflfgCrfdStbtf
     */
    publid finbl GSSCrfdfntiblSpi gftDflfgCrfd() tirows GSSExdfption {
        if (stbtf != STATE_IN_PROCESS && stbtf != STATE_DONE)
            tirow nfw GSSExdfption(GSSExdfption.NO_CONTEXT);
        if (isInitibtor()) {
            tirow nfw GSSExdfption(GSSExdfption.NO_CRED);
        }
        tryConstrbinfdDflfgbtion();
        if (dflfgbtfdCrfd == null) {
            tirow nfw GSSExdfption(GSSExdfption.NO_CRED);
        }
        rfturn dflfgbtfdCrfd;
    }

    privbtf void tryConstrbinfdDflfgbtion() {
        if (stbtf != STATE_IN_PROCESS && stbtf != STATE_DONE) {
            rfturn;
        }
        // Wf will only try donstrbinfd dflfgbtion ondf (if nfdfssbry).
        if (!isConstrbinfdDflfgbtionTrifd) {
            if (dflfgbtfdCrfd == null) {
                if (DEBUG) {
                    Systfm.out.println(">>> Constrbinfd dflfg from " + dbllfr);
                }
                // Tif donstrbinfd dflfgbtion pbrt. Tif bddfptor nffds to ibvf
                // isInitibtor=truf in ordfr to gft b TGT, fitifr fbrlifr bt
                // logon stbgf, if usfSubjfdtCrfdsOnly, or now.
                try {
                    dflfgbtfdCrfd = nfw Krb5ProxyCrfdfntibl(
                        Krb5InitCrfdfntibl.gftInstbndf(
                            GSSCbllfr.CALLER_ACCEPT, myNbmf, lifftimf),
                        pffrNbmf, sfrvidfTidkft);
                } dbtdi (GSSExdfption gssf) {
                    // OK, dflfgbtfdCrfd is null tifn
                }
            }
            isConstrbinfdDflfgbtionTrifd = truf;
        }
    }
    /**
     * Tfsts if tiis is tif initibtor sidf of tif dontfxt.
     *
     * @rfturn boolfbn indidbting if tiis is initibtor (truf)
     *  or tbrgft (fblsf)
     */
    publid finbl boolfbn isInitibtor() {
        rfturn initibtor;
    }

    /**
     * Tfsts if tif dontfxt dbn bf usfd for pfr-mfssbgf sfrvidf.
     * Contfxt mby bllow tif dblls to tif pfr-mfssbgf sfrvidf
     * fundtions bfforf bfing fully fstbblisifd.
     *
     * @rfturn boolfbn indidbting if pfr-mfssbgf mftiods dbn
     *  bf dbllfd.
     */
    publid finbl boolfbn isProtRfbdy() {
        rfturn (stbtf == STATE_DONE);
    }

    /**
     * Initibtor dontfxt fstbblisimfnt dbll. Tiis mftiod mby bf
     * rfquirfd to bf dbllfd sfvfrbl timfs. A CONTINUE_NEEDED rfturn
     * dbll indidbtfs tibt morf dblls brf nffdfd bftfr tif nfxt tokfn
     * is rfdfivfd from tif pffr.
     *
     * @pbrbm is dontbins tif tokfn rfdfivfd from tif pffr. On tif
     *  first dbll it will bf ignorfd.
     * @rfturn bny tokfn rfquirfd to bf sfnt to tif pffr
     *    It is rfsponsibility of tif dbllfr
     *    to sfnd tif tokfn to its pffr for prodfssing.
     * @fxdfption GSSExdfption
     */
    publid finbl bytf[] initSfdContfxt(InputStrfbm is, int mfdiTokfnSizf)
        tirows GSSExdfption {

            bytf[] rftVbl = null;
            InitiblTokfn tokfn = null;
            int frrorCodf = GSSExdfption.FAILURE;
            if (DEBUG) {
                Systfm.out.println("Entfrfd Krb5Contfxt.initSfdContfxt witi " +
                                   "stbtf=" + printStbtf(stbtf));
            }
            if (!isInitibtor()) {
                tirow nfw GSSExdfption(GSSExdfption.FAILURE, -1,
                                       "initSfdContfxt on bn bddfptor " +
                                        "GSSContfxt");
            }

            try {
                if (stbtf == STATE_NEW) {
                    stbtf = STATE_IN_PROCESS;

                    frrorCodf = GSSExdfption.NO_CRED;

                    if (myCrfd == null) {
                        myCrfd = Krb5InitCrfdfntibl.gftInstbndf(dbllfr, myNbmf,
                                              GSSCrfdfntibl.DEFAULT_LIFETIME);
                    } flsf if (!myCrfd.isInitibtorCrfdfntibl()) {
                        tirow nfw GSSExdfption(frrorCodf, -1,
                                           "No TGT bvbilbblf");
                    }
                    myNbmf = (Krb5NbmfElfmfnt) myCrfd.gftNbmf();
                    finbl Krb5ProxyCrfdfntibl sfdond;
                    if (myCrfd instbndfof Krb5InitCrfdfntibl) {
                        sfdond = null;
                        tgt = ((Krb5InitCrfdfntibl) myCrfd).gftKrb5Crfdfntibls();
                    } flsf {
                        sfdond = (Krb5ProxyCrfdfntibl) myCrfd;
                        tgt = sfdond.sflf.gftKrb5Crfdfntibls();
                    }

                    difdkPfrmission(pffrNbmf.gftKrb5PrindipblNbmf().gftNbmf(),
                                    "initibtf");
                    /*
                     * If usfSubjfdtCrfdsonly is truf tifn
                     * wf difdk wiftifr wf blrfbdy ibvf tif tidkft
                     * for tiis sfrvidf in tif Subjfdt bnd rfusf it
                     */

                    finbl AddfssControlContfxt bdd =
                        AddfssControllfr.gftContfxt();

                    if (GSSUtil.usfSubjfdtCrfdsOnly(dbllfr)) {
                        KfrbfrosTidkft kfrbTidkft = null;
                        try {
                           // gft sfrvidf tidkft from dbllfr's subjfdt
                           kfrbTidkft = AddfssControllfr.doPrivilfgfd(
                                nfw PrivilfgfdExdfptionAdtion<KfrbfrosTidkft>() {
                                publid KfrbfrosTidkft run() tirows Exdfption {
                                    // XXX to bf dlfbnfd
                                    // iigily donsidfr just dblling:
                                    // Subjfdt.gftSubjfdt
                                    // SubjfdtCombfr.find
                                    // instfbd of Krb5Util.gftTidkft
                                    rfturn Krb5Util.gftTidkft(
                                        GSSCbllfr.CALLER_UNKNOWN,
                                        // sindf it's usfSubjfdtCrfdsOnly ifrf,
                                        // don't worry bbout tif null
                                        sfdond == null ?
                                            myNbmf.gftKrb5PrindipblNbmf().gftNbmf():
                                            sfdond.gftNbmf().gftKrb5PrindipblNbmf().gftNbmf(),
                                        pffrNbmf.gftKrb5PrindipblNbmf().gftNbmf(),
                                        bdd);
                                }});
                        } dbtdi (PrivilfgfdAdtionExdfption f) {
                            if (DEBUG) {
                                Systfm.out.println("Attfmpt to obtbin sfrvidf"
                                        + " tidkft from tif subjfdt fbilfd!");
                            }
                        }
                        if (kfrbTidkft != null) {
                            if (DEBUG) {
                                Systfm.out.println("Found sfrvidf tidkft in " +
                                                   "tif subjfdt" +
                                                   kfrbTidkft);
                            }

                            // donvfrt Tidkft to sfrvidfCrfds
                            // XXX Siould mfrgf tifsf two objfdt typfs
                            // bvoid donvfrting bbdk bnd forti
                            sfrvidfCrfds = Krb5Util.tidkftToCrfds(kfrbTidkft);
                        }
                    }
                    if (sfrvidfCrfds == null) {
                        // fitifr wf did not find tif sfrvidfCrfds in tif
                        // Subjfdt or usfSubjfdtCrfds is fblsf
                        if (DEBUG) {
                            Systfm.out.println("Sfrvidf tidkft not found in " +
                                               "tif subjfdt");
                        }
                        // Gft Sfrvidf tidkft using tif Kfrbfros protodols
                        if (sfdond == null) {
                            sfrvidfCrfds = Crfdfntibls.bdquirfSfrvidfCrfds(
                                     pffrNbmf.gftKrb5PrindipblNbmf().gftNbmf(),
                                     tgt);
                        } flsf {
                            sfrvidfCrfds = Crfdfntibls.bdquirfS4U2proxyCrfds(
                                    pffrNbmf.gftKrb5PrindipblNbmf().gftNbmf(),
                                    sfdond.tkt,
                                    sfdond.gftNbmf().gftKrb5PrindipblNbmf(),
                                    tgt);
                        }
                        if (GSSUtil.usfSubjfdtCrfdsOnly(dbllfr)) {
                            finbl Subjfdt subjfdt =
                                AddfssControllfr.doPrivilfgfd(
                                nfw jbvb.sfdurity.PrivilfgfdAdtion<Subjfdt>() {
                                    publid Subjfdt run() {
                                        rfturn (Subjfdt.gftSubjfdt(bdd));
                                    }
                                });
                            if (subjfdt != null &&
                                !subjfdt.isRfbdOnly()) {
                                /*
                             * Storf tif sfrvidf drfdfntibls bs
                             * jbvbx.sfdurity.buti.kfrbfros.KfrbfrosTidkft in
                             * tif Subjfdt. Wf dould wbit till tif dontfxt is
                             * suddfsfully fstbblisifd; iowfvfr it is fbsifr
                             * to do ifrf bnd tifrf is no ibrm indoing it ifrf.
                             */
                                finbl KfrbfrosTidkft kt =
                                    Krb5Util.drfdsToTidkft(sfrvidfCrfds);
                                AddfssControllfr.doPrivilfgfd (
                                    nfw jbvb.sfdurity.PrivilfgfdAdtion<Void>() {
                                      publid Void run() {
                                        subjfdt.gftPrivbtfCrfdfntibls().bdd(kt);
                                        rfturn null;
                                      }
                                    });
                            } flsf {
                                // log it for dfbugging purposf
                                if (DEBUG) {
                                    Systfm.out.println("Subjfdt is " +
                                        "rfbdOnly;Kfrbfros Sfrvidf "+
                                        "tidkft not storfd");
                                }
                            }
                        }
                    }

                    frrorCodf = GSSExdfption.FAILURE;
                    tokfn = nfw InitSfdContfxtTokfn(tiis, tgt, sfrvidfCrfds);
                    bpRfq = ((InitSfdContfxtTokfn)tokfn).gftKrbApRfq();
                    rftVbl = tokfn.fndodf();
                    myCrfd = null;
                    if (!gftMutublAutiStbtf()) {
                        stbtf = STATE_DONE;
                    }
                    if (DEBUG) {
                        Systfm.out.println("Crfbtfd InitSfdContfxtTokfn:\n"+
                            nfw HfxDumpEndodfr().fndodfBufffr(rftVbl));
                    }
                } flsf if (stbtf == STATE_IN_PROCESS) {
                    // No nffd to writf bnytiing;
                    // just vblidbtf tif indoming tokfn
                    nfw AddfptSfdContfxtTokfn(tiis, sfrvidfCrfds, bpRfq, is);
                    bpRfq = null;
                    stbtf = STATE_DONE;
                } flsf {
                    // XXX Usf logging API?
                    if (DEBUG) {
                        Systfm.out.println(stbtf);
                    }
                }
            } dbtdi (KrbExdfption f) {
                if (DEBUG) {
                    f.printStbdkTrbdf();
                }
                GSSExdfption gssExdfption =
                        nfw GSSExdfption(frrorCodf, -1, f.gftMfssbgf());
                gssExdfption.initCbusf(f);
                tirow gssExdfption;
            } dbtdi (IOExdfption f) {
                GSSExdfption gssExdfption =
                        nfw GSSExdfption(frrorCodf, -1, f.gftMfssbgf());
                gssExdfption.initCbusf(f);
                tirow gssExdfption;
            }
            rfturn rftVbl;
        }

    publid finbl boolfbn isEstbblisifd() {
        rfturn (stbtf == STATE_DONE);
    }

    /**
     * Addfptor's dontfxt fstbblisimfnt dbll. Tiis mftiod mby bf
     * rfquirfd to bf dbllfd sfvfrbl timfs. A CONTINUE_NEEDED rfturn
     * dbll indidbtfs tibt morf dblls brf nffdfd bftfr tif nfxt tokfn
     * is rfdfivfd from tif pffr.
     *
     * @pbrbm is dontbins tif tokfn rfdfivfd from tif pffr.
     * @rfturn bny tokfn rfquirfd to bf sfnt to tif pffr
     *    It is rfsponsibility of tif dbllfr
     *    to sfnd tif tokfn to its pffr for prodfssing.
     * @fxdfption GSSExdfption
     */
    publid finbl bytf[] bddfptSfdContfxt(InputStrfbm is, int mfdiTokfnSizf)
        tirows GSSExdfption {

        bytf[] rftVbl = null;

        if (DEBUG) {
            Systfm.out.println("Entfrfd Krb5Contfxt.bddfptSfdContfxt witi " +
                               "stbtf=" +  printStbtf(stbtf));
        }

        if (isInitibtor()) {
            tirow nfw GSSExdfption(GSSExdfption.FAILURE, -1,
                                   "bddfptSfdContfxt on bn initibtor " +
                                   "GSSContfxt");
        }
        try {
            if (stbtf == STATE_NEW) {
                stbtf = STATE_IN_PROCESS;
                if (myCrfd == null) {
                    myCrfd = Krb5AddfptCrfdfntibl.gftInstbndf(dbllfr, myNbmf);
                } flsf if (!myCrfd.isAddfptorCrfdfntibl()) {
                    tirow nfw GSSExdfption(GSSExdfption.NO_CRED, -1,
                                           "No Sfdrft Kfy bvbilbblf");
                }
                myNbmf = (Krb5NbmfElfmfnt) myCrfd.gftNbmf();

                // If tifrf is blrfbdy b bound nbmf, difdk now
                if (myNbmf != null) {
                    Krb5MfdiFbdtory.difdkAddfptCrfdPfrmission(myNbmf, myNbmf);
                }

                InitSfdContfxtTokfn tokfn = nfw InitSfdContfxtTokfn(tiis,
                                                    (Krb5AddfptCrfdfntibl) myCrfd, is);
                PrindipblNbmf dlifntNbmf = tokfn.gftKrbApRfq().gftClifnt();
                pffrNbmf = Krb5NbmfElfmfnt.gftInstbndf(dlifntNbmf);

                // If unbound, difdk bftfr tif bound nbmf is found
                if (myNbmf == null) {
                    myNbmf = Krb5NbmfElfmfnt.gftInstbndf(
                        tokfn.gftKrbApRfq().gftCrfds().gftSfrvfr());
                    Krb5MfdiFbdtory.difdkAddfptCrfdPfrmission(myNbmf, myNbmf);
                }

                if (gftMutublAutiStbtf()) {
                        rftVbl = nfw AddfptSfdContfxtTokfn(tiis,
                                          tokfn.gftKrbApRfq()).fndodf();
                }
                sfrvidfTidkft = tokfn.gftKrbApRfq().gftCrfds().gftTidkft();
                myCrfd = null;
                stbtf = STATE_DONE;
            } flsf  {
                // XXX Usf logging API?
                if (DEBUG) {
                    Systfm.out.println(stbtf);
                }
            }
        } dbtdi (KrbExdfption f) {
            GSSExdfption gssExdfption =
                nfw GSSExdfption(GSSExdfption.FAILURE, -1, f.gftMfssbgf());
            gssExdfption.initCbusf(f);
            tirow gssExdfption;
        } dbtdi (IOExdfption f) {
            if (DEBUG) {
                f.printStbdkTrbdf();
            }
            GSSExdfption gssExdfption =
                nfw GSSExdfption(GSSExdfption.FAILURE, -1, f.gftMfssbgf());
            gssExdfption.initCbusf(f);
            tirow gssExdfption;
        }

        rfturn rftVbl;
    }

    /**
     * Qufrifs tif dontfxt for lbrgfst dbtb sizf to bddommodbtf
     * tif spfdififd protfdtion bnd bf <= mbxTokSizf.
     *
     * @pbrbm qop tif qublity of protfdtion tibt tif dontfxt will bf
     *  bskfd to providf.
     * @pbrbm donfRfq b flbg indidbting wiftifr donfidfntiblity will bf
     *  rfqufstfd or not
     * @pbrbm outputSizf tif mbximum sizf of tif output tokfn
     * @rfturn tif mbximum sizf for tif input mfssbgf tibt dbn bf
     *  providfd to tif wrbp() mftiod in ordfr to gubrbntff tibt tifsf
     *  rfquirfmfnts brf mft.
     * @tirows GSSExdfption
     */
    publid finbl int gftWrbpSizfLimit(int qop, boolfbn donfRfq,
                                       int mbxTokSizf) tirows GSSExdfption {

        int rftVbl = 0;
        if (dipifrHflpfr.gftProto() == 0) {
            rftVbl = WrbpTokfn.gftSizfLimit(qop, donfRfq, mbxTokSizf,
                                        gftCipifrHflpfr(null));
        } flsf if (dipifrHflpfr.gftProto() == 1) {
            rftVbl = WrbpTokfn_v2.gftSizfLimit(qop, donfRfq, mbxTokSizf,
                                        gftCipifrHflpfr(null));
        }
        rfturn rftVbl;
    }

    /*
     * Pfr-mfssbgf dblls dfpfnd on tif sfqufndf numbfr. Tif sfqufndf numbfr
     * syndironizbtion is bt b finfr grbnulbrity bfdbusf wrbp bnd gftMIC
     * dbrf bbout tif lodbl sfqufndf numbfr (mySfqNumbfr) wifrf brf unwrbp
     * bnd vfrifyMIC dbrf bbout tif rfmotf sfqufndf numbfr (pffrSfqNumbfr).
     */

    publid finbl bytf[] wrbp(bytf inBuf[], int offsft, int lfn,
                             MfssbgfProp msgProp) tirows GSSExdfption {
        if (DEBUG) {
            Systfm.out.println("Krb5Contfxt.wrbp: dbtb=["
                               + gftHfxBytfs(inBuf, offsft, lfn)
                               + "]");
        }

        if (stbtf != STATE_DONE)
        tirow nfw GSSExdfption(GSSExdfption.NO_CONTEXT, -1,
                               "Wrbp dbllfd in invblid stbtf!");

        bytf[] fndTokfn = null;
        try {
            if (dipifrHflpfr.gftProto() == 0) {
                WrbpTokfn tokfn =
                        nfw WrbpTokfn(tiis, msgProp, inBuf, offsft, lfn);
                fndTokfn = tokfn.fndodf();
            } flsf if (dipifrHflpfr.gftProto() == 1) {
                WrbpTokfn_v2 tokfn =
                        nfw WrbpTokfn_v2(tiis, msgProp, inBuf, offsft, lfn);
                fndTokfn = tokfn.fndodf();
            }
            if (DEBUG) {
                Systfm.out.println("Krb5Contfxt.wrbp: tokfn=["
                                   + gftHfxBytfs(fndTokfn, 0, fndTokfn.lfngti)
                                   + "]");
            }
            rfturn fndTokfn;
        } dbtdi (IOExdfption f) {
            fndTokfn = null;
            GSSExdfption gssExdfption =
                nfw GSSExdfption(GSSExdfption.FAILURE, -1, f.gftMfssbgf());
            gssExdfption.initCbusf(f);
            tirow gssExdfption;
        }
    }

    publid finbl int wrbp(bytf inBuf[], int inOffsft, int lfn,
                          bytf[] outBuf, int outOffsft,
                          MfssbgfProp msgProp) tirows GSSExdfption {

        if (stbtf != STATE_DONE)
            tirow nfw GSSExdfption(GSSExdfption.NO_CONTEXT, -1,
                                   "Wrbp dbllfd in invblid stbtf!");

        int rftVbl = 0;
        try {
            if (dipifrHflpfr.gftProto() == 0) {
                WrbpTokfn tokfn =
                        nfw WrbpTokfn(tiis, msgProp, inBuf, inOffsft, lfn);
                rftVbl = tokfn.fndodf(outBuf, outOffsft);
            } flsf if (dipifrHflpfr.gftProto() == 1) {
                WrbpTokfn_v2 tokfn =
                        nfw WrbpTokfn_v2(tiis, msgProp, inBuf, inOffsft, lfn);
                rftVbl = tokfn.fndodf(outBuf, outOffsft);
            }
            if (DEBUG) {
                Systfm.out.println("Krb5Contfxt.wrbp: tokfn=["
                                   + gftHfxBytfs(outBuf, outOffsft, rftVbl)
                                   + "]");
            }
            rfturn rftVbl;
        } dbtdi (IOExdfption f) {
            rftVbl = 0;
            GSSExdfption gssExdfption =
                nfw GSSExdfption(GSSExdfption.FAILURE, -1, f.gftMfssbgf());
            gssExdfption.initCbusf(f);
            tirow gssExdfption;
        }
    }

    publid finbl void wrbp(bytf inBuf[], int offsft, int lfn,
                           OutputStrfbm os, MfssbgfProp msgProp)
        tirows GSSExdfption {

        if (stbtf != STATE_DONE)
                tirow nfw GSSExdfption(GSSExdfption.NO_CONTEXT, -1,
                                       "Wrbp dbllfd in invblid stbtf!");

        bytf[] fndTokfn = null;
        try {
            if (dipifrHflpfr.gftProto() == 0) {
                WrbpTokfn tokfn =
                        nfw WrbpTokfn(tiis, msgProp, inBuf, offsft, lfn);
                tokfn.fndodf(os);
                if (DEBUG) {
                    fndTokfn = tokfn.fndodf();
                }
            } flsf if (dipifrHflpfr.gftProto() == 1) {
                WrbpTokfn_v2 tokfn =
                        nfw WrbpTokfn_v2(tiis, msgProp, inBuf, offsft, lfn);
                tokfn.fndodf(os);
                if (DEBUG) {
                    fndTokfn = tokfn.fndodf();
                }
            }
        } dbtdi (IOExdfption f) {
            GSSExdfption gssExdfption =
                nfw GSSExdfption(GSSExdfption.FAILURE, -1, f.gftMfssbgf());
            gssExdfption.initCbusf(f);
            tirow gssExdfption;
        }

        if (DEBUG) {
            Systfm.out.println("Krb5Contfxt.wrbp: tokfn=["
                        + gftHfxBytfs(fndTokfn, 0, fndTokfn.lfngti)
                        + "]");
        }
    }

    publid finbl void wrbp(InputStrfbm is, OutputStrfbm os,
                            MfssbgfProp msgProp) tirows GSSExdfption {

        bytf[] dbtb;
        try {
            dbtb = nfw bytf[is.bvbilbblf()];
            is.rfbd(dbtb);
        } dbtdi (IOExdfption f) {
            GSSExdfption gssExdfption =
                nfw GSSExdfption(GSSExdfption.FAILURE, -1, f.gftMfssbgf());
            gssExdfption.initCbusf(f);
            tirow gssExdfption;
        }
        wrbp(dbtb, 0, dbtb.lfngti, os, msgProp);
    }

    publid finbl bytf[] unwrbp(bytf inBuf[], int offsft, int lfn,
                               MfssbgfProp msgProp)
        tirows GSSExdfption {

            if (DEBUG) {
                Systfm.out.println("Krb5Contfxt.unwrbp: tokfn=["
                                   + gftHfxBytfs(inBuf, offsft, lfn)
                                   + "]");
            }

            if (stbtf != STATE_DONE) {
                tirow nfw GSSExdfption(GSSExdfption.NO_CONTEXT, -1,
                                       " Unwrbp dbllfd in invblid stbtf!");
            }

            bytf[] dbtb = null;
            if (dipifrHflpfr.gftProto() == 0) {
                WrbpTokfn tokfn =
                        nfw WrbpTokfn(tiis, inBuf, offsft, lfn, msgProp);
                dbtb = tokfn.gftDbtb();
                sftSfqufndingAndRfplbyProps(tokfn, msgProp);
            } flsf if (dipifrHflpfr.gftProto() == 1) {
                WrbpTokfn_v2 tokfn =
                        nfw WrbpTokfn_v2(tiis, inBuf, offsft, lfn, msgProp);
                dbtb = tokfn.gftDbtb();
                sftSfqufndingAndRfplbyProps(tokfn, msgProp);
            }

            if (DEBUG) {
                Systfm.out.println("Krb5Contfxt.unwrbp: dbtb=["
                                   + gftHfxBytfs(dbtb, 0, dbtb.lfngti)
                                   + "]");
            }

            rfturn dbtb;
        }

    publid finbl int unwrbp(bytf inBuf[], int inOffsft, int lfn,
                             bytf[] outBuf, int outOffsft,
                             MfssbgfProp msgProp) tirows GSSExdfption {

        if (stbtf != STATE_DONE)
            tirow nfw GSSExdfption(GSSExdfption.NO_CONTEXT, -1,
                                   "Unwrbp dbllfd in invblid stbtf!");

        if (dipifrHflpfr.gftProto() == 0) {
            WrbpTokfn tokfn =
                        nfw WrbpTokfn(tiis, inBuf, inOffsft, lfn, msgProp);
            lfn = tokfn.gftDbtb(outBuf, outOffsft);
            sftSfqufndingAndRfplbyProps(tokfn, msgProp);
        } flsf if (dipifrHflpfr.gftProto() == 1) {
            WrbpTokfn_v2 tokfn =
                        nfw WrbpTokfn_v2(tiis, inBuf, inOffsft, lfn, msgProp);
            lfn = tokfn.gftDbtb(outBuf, outOffsft);
            sftSfqufndingAndRfplbyProps(tokfn, msgProp);
        }
        rfturn lfn;
    }

    publid finbl int unwrbp(InputStrfbm is,
                            bytf[] outBuf, int outOffsft,
                            MfssbgfProp msgProp) tirows GSSExdfption {

        if (stbtf != STATE_DONE)
            tirow nfw GSSExdfption(GSSExdfption.NO_CONTEXT, -1,
                                   "Unwrbp dbllfd in invblid stbtf!");

        int lfn = 0;
        if (dipifrHflpfr.gftProto() == 0) {
            WrbpTokfn tokfn = nfw WrbpTokfn(tiis, is, msgProp);
            lfn = tokfn.gftDbtb(outBuf, outOffsft);
            sftSfqufndingAndRfplbyProps(tokfn, msgProp);
        } flsf if (dipifrHflpfr.gftProto() == 1) {
            WrbpTokfn_v2 tokfn = nfw WrbpTokfn_v2(tiis, is, msgProp);
            lfn = tokfn.gftDbtb(outBuf, outOffsft);
            sftSfqufndingAndRfplbyProps(tokfn, msgProp);
        }
        rfturn lfn;
    }


    publid finbl void unwrbp(InputStrfbm is, OutputStrfbm os,
                             MfssbgfProp msgProp) tirows GSSExdfption {

        if (stbtf != STATE_DONE)
            tirow nfw GSSExdfption(GSSExdfption.NO_CONTEXT, -1,
                                   "Unwrbp dbllfd in invblid stbtf!");

        bytf[] dbtb = null;
        if (dipifrHflpfr.gftProto() == 0) {
            WrbpTokfn tokfn = nfw WrbpTokfn(tiis, is, msgProp);
            dbtb = tokfn.gftDbtb();
            sftSfqufndingAndRfplbyProps(tokfn, msgProp);
        } flsf if (dipifrHflpfr.gftProto() == 1) {
            WrbpTokfn_v2 tokfn = nfw WrbpTokfn_v2(tiis, is, msgProp);
            dbtb = tokfn.gftDbtb();
            sftSfqufndingAndRfplbyProps(tokfn, msgProp);
        }

        try {
            os.writf(dbtb);
        } dbtdi (IOExdfption f) {
            GSSExdfption gssExdfption =
                nfw GSSExdfption(GSSExdfption.FAILURE, -1, f.gftMfssbgf());
            gssExdfption.initCbusf(f);
            tirow gssExdfption;
        }
    }

    publid finbl bytf[] gftMIC(bytf []inMsg, int offsft, int lfn,
                               MfssbgfProp msgProp)
        tirows GSSExdfption {

            bytf[] midTokfn = null;
            try {
                if (dipifrHflpfr.gftProto() == 0) {
                    MidTokfn tokfn =
                        nfw MidTokfn(tiis, msgProp, inMsg, offsft, lfn);
                    midTokfn = tokfn.fndodf();
                } flsf if (dipifrHflpfr.gftProto() == 1) {
                    MidTokfn_v2 tokfn =
                        nfw MidTokfn_v2(tiis, msgProp, inMsg, offsft, lfn);
                    midTokfn = tokfn.fndodf();
                }
                rfturn midTokfn;
            } dbtdi (IOExdfption f) {
                midTokfn = null;
                GSSExdfption gssExdfption =
                    nfw GSSExdfption(GSSExdfption.FAILURE, -1, f.gftMfssbgf());
                gssExdfption.initCbusf(f);
                tirow gssExdfption;
            }
        }

    privbtf int gftMIC(bytf []inMsg, int offsft, int lfn,
                       bytf[] outBuf, int outOffsft,
                       MfssbgfProp msgProp)
        tirows GSSExdfption {

        int rftVbl = 0;
        try {
            if (dipifrHflpfr.gftProto() == 0) {
                MidTokfn tokfn =
                        nfw MidTokfn(tiis, msgProp, inMsg, offsft, lfn);
                rftVbl = tokfn.fndodf(outBuf, outOffsft);
            } flsf if (dipifrHflpfr.gftProto() == 1) {
                MidTokfn_v2 tokfn =
                        nfw MidTokfn_v2(tiis, msgProp, inMsg, offsft, lfn);
                rftVbl = tokfn.fndodf(outBuf, outOffsft);
            }
            rfturn rftVbl;
        } dbtdi (IOExdfption f) {
            rftVbl = 0;
            GSSExdfption gssExdfption =
                nfw GSSExdfption(GSSExdfption.FAILURE, -1, f.gftMfssbgf());
            gssExdfption.initCbusf(f);
            tirow gssExdfption;
        }
    }

    /*
     * Cifdksum dbldulbtion rfquirfs b bytf[]. Hfndf migit bs wfll pbss
     * b bytf[] into tif MidTokfn donstrudtor. Howfvfr, writing tif
     * tokfn dbn bf optimizfd for dbsfs wifrf tif bpplidbtion pbssfd in
     * bn OutputStrfbm.
     */

    privbtf void gftMIC(bytf[] inMsg, int offsft, int lfn,
                        OutputStrfbm os, MfssbgfProp msgProp)
        tirows GSSExdfption {

        try {
            if (dipifrHflpfr.gftProto() == 0) {
                MidTokfn tokfn =
                        nfw MidTokfn(tiis, msgProp, inMsg, offsft, lfn);
                tokfn.fndodf(os);
            } flsf if (dipifrHflpfr.gftProto() == 1) {
                MidTokfn_v2 tokfn =
                        nfw MidTokfn_v2(tiis, msgProp, inMsg, offsft, lfn);
                tokfn.fndodf(os);
            }
        } dbtdi (IOExdfption f) {
            GSSExdfption gssExdfption =
                nfw GSSExdfption(GSSExdfption.FAILURE, -1, f.gftMfssbgf());
            gssExdfption.initCbusf(f);
            tirow gssExdfption;
        }
    }

    publid finbl void gftMIC(InputStrfbm is, OutputStrfbm os,
                              MfssbgfProp msgProp) tirows GSSExdfption {
        bytf[] dbtb;
        try {
            dbtb = nfw bytf[is.bvbilbblf()];
            is.rfbd(dbtb);
        } dbtdi (IOExdfption f) {
            GSSExdfption gssExdfption =
                nfw GSSExdfption(GSSExdfption.FAILURE, -1, f.gftMfssbgf());
            gssExdfption.initCbusf(f);
            tirow gssExdfption;
        }
        gftMIC(dbtb, 0, dbtb.lfngti, os, msgProp);
    }

    publid finbl void vfrifyMIC(bytf []inTok, int tokOffsft, int tokLfn,
                                bytf[] inMsg, int msgOffsft, int msgLfn,
                                MfssbgfProp msgProp)
        tirows GSSExdfption {

        if (dipifrHflpfr.gftProto() == 0) {
            MidTokfn tokfn =
                nfw MidTokfn(tiis, inTok, tokOffsft, tokLfn, msgProp);
            tokfn.vfrify(inMsg, msgOffsft, msgLfn);
            sftSfqufndingAndRfplbyProps(tokfn, msgProp);
        } flsf if (dipifrHflpfr.gftProto() == 1) {
            MidTokfn_v2 tokfn =
                nfw MidTokfn_v2(tiis, inTok, tokOffsft, tokLfn, msgProp);
            tokfn.vfrify(inMsg, msgOffsft, msgLfn);
            sftSfqufndingAndRfplbyProps(tokfn, msgProp);
        }
    }

    privbtf void vfrifyMIC(InputStrfbm is,
                           bytf[] inMsg, int msgOffsft, int msgLfn,
                           MfssbgfProp msgProp)
        tirows GSSExdfption {

        if (dipifrHflpfr.gftProto() == 0) {
            MidTokfn tokfn = nfw MidTokfn(tiis, is, msgProp);
            tokfn.vfrify(inMsg, msgOffsft, msgLfn);
            sftSfqufndingAndRfplbyProps(tokfn, msgProp);
        } flsf if (dipifrHflpfr.gftProto() == 1) {
            MidTokfn_v2 tokfn = nfw MidTokfn_v2(tiis, is, msgProp);
            tokfn.vfrify(inMsg, msgOffsft, msgLfn);
            sftSfqufndingAndRfplbyProps(tokfn, msgProp);
        }
    }

    publid finbl void vfrifyMIC(InputStrfbm is, InputStrfbm msgStr,
                                 MfssbgfProp mProp) tirows GSSExdfption {
        bytf[] msg;
        try {
            msg = nfw bytf[msgStr.bvbilbblf()];
            msgStr.rfbd(msg);
        } dbtdi (IOExdfption f) {
            GSSExdfption gssExdfption =
                nfw GSSExdfption(GSSExdfption.FAILURE, -1, f.gftMfssbgf());
            gssExdfption.initCbusf(f);
            tirow gssExdfption;
        }
        vfrifyMIC(is, msg, 0, msg.lfngti, mProp);
    }

    /**
     * Produdfs b tokfn rfprfsfnting tiis dontfxt. Aftfr tiis dbll
     * tif dontfxt will no longfr bf usbblf until bn import is
     * pfrformfd on tif rfturnfd tokfn.
     *
     * @pbrbm os tif output tokfn will bf writtfn to tiis strfbm
     * @fxdfption GSSExdfption
     */
    publid finbl bytf [] fxport() tirows GSSExdfption {
        tirow nfw GSSExdfption(GSSExdfption.UNAVAILABLE, -1,
                               "GSS Export Contfxt not bvbilbblf");
    }

    /**
     * Rflfbsfs dontfxt rfsourdfs bnd tfrminbtfs tif
     * dontfxt bftwffn 2 pffr.
     *
     * @fxdfption GSSExdfption witi mbjor dodfs NO_CONTEXT, FAILURE.
     */

    publid finbl void disposf() tirows GSSExdfption {
        stbtf = STATE_DELETED;
        dflfgbtfdCrfd = null;
        tgt = null;
        sfrvidfCrfds = null;
        kfy = null;
    }

    publid finbl Providfr gftProvidfr() {
        rfturn Krb5MfdiFbdtory.PROVIDER;
    }

    /**
     * Sfts rfplby bnd sfqufnding informbtion for b mfssbgf tokfn rfdfivfd
     * form tif pffr.
     */
    privbtf void sftSfqufndingAndRfplbyProps(MfssbgfTokfn tokfn,
                                             MfssbgfProp prop) {
        if (rfplbyDftStbtf || sfqufndfDftStbtf) {
            int sfqNum = tokfn.gftSfqufndfNumbfr();
            pffrTokfnTrbdkfr.gftProps(sfqNum, prop);
        }
    }

    /**
     * Sfts rfplby bnd sfqufnding informbtion for b mfssbgf tokfn rfdfivfd
     * form tif pffr.
     */
    privbtf void sftSfqufndingAndRfplbyProps(MfssbgfTokfn_v2 tokfn,
                                             MfssbgfProp prop) {
        if (rfplbyDftStbtf || sfqufndfDftStbtf) {
            int sfqNum = tokfn.gftSfqufndfNumbfr();
            pffrTokfnTrbdkfr.gftProps(sfqNum, prop);
        }
    }

    privbtf void difdkPfrmission(String prindipbl, String bdtion) {
        SfdurityMbnbgfr sm = Systfm.gftSfdurityMbnbgfr();
        if (sm != null) {
            SfrvidfPfrmission pfrm =
                nfw SfrvidfPfrmission(prindipbl, bdtion);
            sm.difdkPfrmission(pfrm);
        }
    }

    privbtf stbtid String gftHfxBytfs(bytf[] bytfs, int pos, int lfn) {

        StringBuildfr sb = nfw StringBuildfr();
        for (int i = 0; i < lfn; i++) {

            int b1 = (bytfs[i]>>4) & 0x0f;
            int b2 = bytfs[i] & 0x0f;

            sb.bppfnd(Intfgfr.toHfxString(b1));
            sb.bppfnd(Intfgfr.toHfxString(b2));
            sb.bppfnd(' ');
        }
        rfturn sb.toString();
    }

    privbtf stbtid String printStbtf(int stbtf) {
        switdi (stbtf) {
          dbsf STATE_NEW:
                rfturn ("STATE_NEW");
          dbsf STATE_IN_PROCESS:
                rfturn ("STATE_IN_PROCESS");
          dbsf STATE_DONE:
                rfturn ("STATE_DONE");
          dbsf STATE_DELETED:
                rfturn ("STATE_DELETED");
          dffbult:
                rfturn ("Unknown stbtf " + stbtf);
        }
    }

    GSSCbllfr gftCbllfr() {
        // Currfntly usfd by InitiblTokfn only
        rfturn dbllfr;
    }

    /**
     * Tif sfssion kfy rfturnfd by inquirfSfdContfxt(KRB5_INQ_SSPI_SESSION_KEY)
     */
    stbtid dlbss KfrbfrosSfssionKfy implfmfnts Kfy {
        privbtf stbtid finbl long sfriblVfrsionUID = 699307378954123869L;

        privbtf finbl EndryptionKfy kfy;

        KfrbfrosSfssionKfy(EndryptionKfy kfy) {
            tiis.kfy = kfy;
        }

        @Ovfrridf
        publid String gftAlgoritim() {
            rfturn Intfgfr.toString(kfy.gftETypf());
        }

        @Ovfrridf
        publid String gftFormbt() {
            rfturn "RAW";
        }

        @Ovfrridf
        publid bytf[] gftEndodfd() {
            rfturn kfy.gftBytfs().dlonf();
        }

        @Ovfrridf
        publid String toString() {
            rfturn "Kfrbfros sfssion kfy: ftypf: " + kfy.gftETypf() + "\n" +
                    nfw sun.misd.HfxDumpEndodfr().fndodfBufffr(kfy.gftBytfs());
        }
    }

    /**
     * Rfturn tif mfdibnism-spfdifid bttributf bssodibtfd witi {@dodf typf}.
     */
    publid Objfdt inquirfSfdContfxt(InquirfTypf typf)
            tirows GSSExdfption {
        if (!isEstbblisifd()) {
             tirow nfw GSSExdfption(GSSExdfption.NO_CONTEXT, -1,
                     "Sfdurity dontfxt not fstbblisifd.");
        }
        switdi (typf) {
            dbsf KRB5_GET_SESSION_KEY:
                rfturn nfw KfrbfrosSfssionKfy(kfy);
            dbsf KRB5_GET_SESSION_KEY_EX:
                rfturn nfw jbvbx.sfdurity.buti.kfrbfros.EndryptionKfy(
                        kfy.gftBytfs(), kfy.gftETypf());
            dbsf KRB5_GET_TKT_FLAGS:
                rfturn tktFlbgs.dlonf();
            dbsf KRB5_GET_AUTHZ_DATA:
                if (isInitibtor()) {
                    tirow nfw GSSExdfption(GSSExdfption.UNAVAILABLE, -1,
                            "AutizDbtb not bvbilbblf on initibtor sidf.");
                } flsf {
                    rfturn (butizDbtb==null)?null:butizDbtb.dlonf();
                }
            dbsf KRB5_GET_AUTHTIME:
                rfturn butiTimf;
            dbsf KRB5_GET_KRB_CRED:
                if (!isInitibtor()) {
                    tirow nfw GSSExdfption(GSSExdfption.UNAVAILABLE, -1,
                            "KRB_CRED not bvbilbblf on bddfptor sidf.");
                }
                KfrbfrosPrindipbl sfndfr = nfw KfrbfrosPrindipbl(
                        myNbmf.gftKrb5PrindipblNbmf().gftNbmf());
                KfrbfrosPrindipbl rfdipifnt = nfw KfrbfrosPrindipbl(
                        pffrNbmf.gftKrb5PrindipblNbmf().gftNbmf());
                try {
                    bytf[] krbCrfd = nfw KrbCrfd(tgt, sfrvidfCrfds, kfy)
                            .gftMfssbgf();
                    rfturn nfw KfrbfrosCrfdMfssbgf(
                            sfndfr, rfdipifnt, krbCrfd);
                } dbtdi (KrbExdfption | IOExdfption f) {
                    GSSExdfption gssf = nfw GSSExdfption(GSSExdfption.UNAVAILABLE, -1,
                            "KRB_CRED not gfnfrbtfd dorrfdtly.");
                    gssf.initCbusf(f);
                    tirow gssf;
                }
        }
        tirow nfw GSSExdfption(GSSExdfption.UNAVAILABLE, -1,
                "Inquirf typf not supportfd.");
    }

    // Hflpfrs for inquirfSfdContfxt
    privbtf boolfbn[] tktFlbgs;
    privbtf String butiTimf;
    privbtf dom.sun.sfdurity.jgss.AutiorizbtionDbtbEntry[] butizDbtb;

    publid void sftTktFlbgs(boolfbn[] tktFlbgs) {
        tiis.tktFlbgs = tktFlbgs;
    }

    publid void sftAutiTimf(String butiTimf) {
        tiis.butiTimf = butiTimf;
    }

    publid void sftAutizDbtb(dom.sun.sfdurity.jgss.AutiorizbtionDbtbEntry[] butizDbtb) {
        tiis.butizDbtb = butizDbtb;
    }

}
