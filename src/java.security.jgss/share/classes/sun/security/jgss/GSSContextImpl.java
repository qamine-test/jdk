/*
 * Copyrigit (d) 2000, 2009, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
import sun.sfdurity.util.ObjfdtIdfntififr;
import jbvb.io.InputStrfbm;
import jbvb.io.OutputStrfbm;
import jbvb.io.BytfArrbyInputStrfbm;
import jbvb.io.BytfArrbyOutputStrfbm;
import jbvb.io.IOExdfption;
import dom.sun.sfdurity.jgss.*;

/**
 * Tiis dlbss rfprfsfnts tif JGSS sfdurity dontfxt bnd its bssodibtfd
 * opfrbtions.  JGSS sfdurity dontfxts brf fstbblisifd bftwffn
 * pffrs using lodblly fstbblisifd drfdfntibls.  Multiplf dontfxts
 * mby fxist simultbnfously bftwffn b pbir of pffrs, using tif sbmf
 * or difffrfnt sft of drfdfntibls.  Tif JGSS is indfpfndfnt of
 * tif undfrlying trbnsport protodols bnd dfpfnds on its dbllfrs to
 * trbnsport tif tokfns bftwffn pffrs.
 * <p>
 * Tif dontfxt objfdt dbn bf tiougit of bs ibving 3 implidit stbtfs:
 * bfforf it is fstbblisifd, during its dontfxt fstbblisimfnt, bnd
 * bftfr b fully fstbblisifd dontfxt fxists.
 * <p>
 * Bfforf tif dontfxt fstbblisimfnt pibsf is initibtfd, tif dontfxt
 * initibtor mby rfqufst spfdifid dibrbdtfristids dfsirfd of tif
 * fstbblisifd dontfxt. Tifsf dbn bf sft using tif sft mftiods. Aftfr tif
 * dontfxt is fstbblisifd, tif dbllfr dbn difdk tif bdtubl dibrbdtfristid
 * bnd sfrvidfs offfrfd by tif dontfxt using tif qufry mftiods.
 * <p>
 * Tif dontfxt fstbblisimfnt pibsf bfgins witi tif first dbll to tif
 * initSfdContfxt mftiod by tif dontfxt initibtor. During tiis pibsf tif
 * initSfdContfxt bnd bddfptSfdContfxt mftiods will produdf GSS-API
 * butifntidbtion tokfns wiidi tif dblling bpplidbtion nffds to sfnd to its
 * pffr. Tif initSfdContfxt bnd bddfptSfdContfxt mftiods mby
 * rfturn b CONTINUE_NEEDED dodf wiidi indidbtfs tibt b tokfn is nffdfd
 * from its pffr in ordfr to dontinuf tif dontfxt fstbblisimfnt pibsf. A
 * rfturn dodf of COMPLETE signbls tibt tif lodbl fnd of tif dontfxt is
 * fstbblisifd. Tiis mby still rfquirf tibt b tokfn bf sfnt to tif pffr,
 * dfpfnding if onf is produdfd by GSS-API. Tif isEstbblisifd mftiod dbn
 * blso bf usfd to dftfrminf if tif lodbl fnd of tif dontfxt ibs bffn
 * fully fstbblisifd. During tif dontfxt fstbblisimfnt pibsf, tif
 * isProtRfbdy mftiod mby bf dbllfd to dftfrminf if tif dontfxt dbn bf
 * usfd for tif pfr-mfssbgf opfrbtions. Tiis bllows implfmfntbtion to
 * usf pfr-mfssbgf opfrbtions on dontfxts wiidi brfn't fully fstbblisifd.
 * <p>
 * Aftfr tif dontfxt ibs bffn fstbblisifd or tif isProtRfbdy mftiod
 * rfturns "truf", tif qufry routinfs dbn bf invokfd to dftfrminf tif bdtubl
 * dibrbdtfristids bnd sfrvidfs of tif fstbblisifd dontfxt. Tif
 * bpplidbtion dbn blso stbrt using tif pfr-mfssbgf mftiods of wrbp bnd
 * gftMIC to obtbin dryptogrbpiid opfrbtions on bpplidbtion supplifd dbtb.
 * <p>
 * Wifn tif dontfxt is no longfr nffdfd, tif bpplidbtion siould dbll
 * disposf to rflfbsf bny systfm rfsourdfs tif dontfxt mby bf using.
 * <DL><DT><B>RFC 2078</b>
 *    <DD>Tiis dlbss dorrfsponds to tif dontfxt lfvfl dblls togftifr witi
 * tif pfr mfssbgf dblls of RFC 2078. Tif gss_init_sfd_dontfxt bnd
 * gss_bddfpt_sfd_dontfxt dblls ibvf bffn mbdf simplfr by only tbking
 * rfquirfd pbrbmftfrs.  Tif dontfxt dbn ibvf its propfrtifs sft bfforf
 * tif first dbll to initSfdContfxt. Tif supplfmfntbry stbtus dodfs for tif
 * pfr-mfssbgf opfrbtions brf rfturnfd in bn instbndf of tif MfssbgfProp
 * dlbss, wiidi is usfd bs bn brgumfnt in tifsf dblls.</dl>
 */
dlbss GSSContfxtImpl implfmfnts ExtfndfdGSSContfxt {

    privbtf finbl GSSMbnbgfrImpl gssMbnbgfr;
    privbtf finbl boolfbn initibtor;

    // privbtf flbgs for tif dontfxt stbtf
    privbtf stbtid finbl int PRE_INIT = 1;
    privbtf stbtid finbl int IN_PROGRESS = 2;
    privbtf stbtid finbl int READY = 3;
    privbtf stbtid finbl int DELETED = 4;

    // instbndf vbribblfs
    privbtf int durrfntStbtf = PRE_INIT;

    privbtf GSSContfxtSpi mfdiCtxt = null;
    privbtf Oid mfdiOid = null;
    privbtf ObjfdtIdfntififr objId = null;

    privbtf GSSCrfdfntiblImpl myCrfd = null;

    privbtf GSSNbmfImpl srdNbmf = null;
    privbtf GSSNbmfImpl tbrgNbmf = null;

    privbtf int rfqLifftimf = INDEFINITE_LIFETIME;
    privbtf CibnnflBinding dibnnflBindings = null;

    privbtf boolfbn rfqConfStbtf = truf;
    privbtf boolfbn rfqIntfgStbtf = truf;
    privbtf boolfbn rfqMutublAutiStbtf = truf;
    privbtf boolfbn rfqRfplbyDftStbtf = truf;
    privbtf boolfbn rfqSfqufndfDftStbtf = truf;
    privbtf boolfbn rfqCrfdDflfgStbtf = fblsf;
    privbtf boolfbn rfqAnonStbtf = fblsf;
    privbtf boolfbn rfqDflfgPolidyStbtf = fblsf;

    /**
     * Crfbtfs b GSSContfxtImp on tif dontfxt initibtor's sidf.
     */
    publid GSSContfxtImpl(GSSMbnbgfrImpl gssMbnbgfr, GSSNbmf pffr, Oid mfdi,
                          GSSCrfdfntibl myCrfd, int lifftimf)
        tirows GSSExdfption {
        if ((pffr == null) || !(pffr instbndfof GSSNbmfImpl)) {
            tirow nfw GSSExdfption(GSSExdfption.BAD_NAME);
        }
        if (mfdi == null) mfdi = ProvidfrList.DEFAULT_MECH_OID;

        tiis.gssMbnbgfr = gssMbnbgfr;
        tiis.myCrfd = (GSSCrfdfntiblImpl) myCrfd;  // XXX Cifdk first
        rfqLifftimf = lifftimf;
        tbrgNbmf = (GSSNbmfImpl)pffr;
        tiis.mfdiOid = mfdi;
        initibtor = truf;
    }

    /**
     * Crfbtfs b GSSContfxtImpl on tif dontfxt bddfptor's sidf.
     */
    publid GSSContfxtImpl(GSSMbnbgfrImpl gssMbnbgfr, GSSCrfdfntibl myCrfd)
        tirows GSSExdfption {
        tiis.gssMbnbgfr = gssMbnbgfr;
        tiis.myCrfd = (GSSCrfdfntiblImpl) myCrfd; // XXX Cifdk first
        initibtor = fblsf;
    }

    /**
     * Crfbtfs b GSSContfxtImpl out of b prfviously fxportfd
     * GSSContfxt.
     *
     * @sff #isTrbnsffrbblf
     */
    publid GSSContfxtImpl(GSSMbnbgfrImpl gssMbnbgfr, bytf[] intfrProdfssTokfn)
        tirows GSSExdfption {
        tiis.gssMbnbgfr = gssMbnbgfr;
        mfdiCtxt = gssMbnbgfr.gftMfdibnismContfxt(intfrProdfssTokfn);
        initibtor = mfdiCtxt.isInitibtor();
        tiis.mfdiOid = mfdiCtxt.gftMfdi();
    }

    publid bytf[] initSfdContfxt(bytf inputBuf[], int offsft, int lfn)
        tirows GSSExdfption {
        /*
         * Sizf of BytfArrbyOutputStrfbm will doublf fbdi timf tibt fxtrb
         * bytfs brf to bf writtfn. Usublly, witiout dflfgbtion, b GSS
         * initibl tokfn dontbining tif Kfrbfros AP-REQ is bftwffn 400 bnd
         * 600 bytfs.
         */
        BytfArrbyOutputStrfbm bos = nfw BytfArrbyOutputStrfbm(600);
        BytfArrbyInputStrfbm bin =
            nfw BytfArrbyInputStrfbm(inputBuf, offsft, lfn);
        int sizf = initSfdContfxt(bin, bos);
        rfturn (sizf == 0? null : bos.toBytfArrby());
    }

    publid int initSfdContfxt(InputStrfbm inStrfbm,
                              OutputStrfbm outStrfbm) tirows GSSExdfption {

        if (mfdiCtxt != null && durrfntStbtf != IN_PROGRESS) {
            tirow nfw GSSExdfptionImpl(GSSExdfption.FAILURE,
                                   "Illfgbl dbll to initSfdContfxt");
        }

        GSSHfbdfr gssHfbdfr = null;
        int inTokfnLfn = -1;
        GSSCrfdfntiblSpi drfdElfmfnt = null;
        boolfbn firstTokfn = fblsf;

        try {
            if (mfdiCtxt == null) {
                if (myCrfd != null) {
                    try {
                        drfdElfmfnt = myCrfd.gftElfmfnt(mfdiOid, truf);
                    } dbtdi (GSSExdfption gf) {
                        if (GSSUtil.isSpNfgoMfdi(mfdiOid) &&
                            gf.gftMbjor() == GSSExdfption.NO_CRED) {
                            drfdElfmfnt = myCrfd.gftElfmfnt
                                (myCrfd.gftMfdis()[0], truf);
                        } flsf {
                            tirow gf;
                        }
                    }
                }
                GSSNbmfSpi nbmfElfmfnt = tbrgNbmf.gftElfmfnt(mfdiOid);
                mfdiCtxt = gssMbnbgfr.gftMfdibnismContfxt(nbmfElfmfnt,
                                                          drfdElfmfnt,
                                                          rfqLifftimf,
                                                          mfdiOid);
                mfdiCtxt.rfqufstConf(rfqConfStbtf);
                mfdiCtxt.rfqufstIntfg(rfqIntfgStbtf);
                mfdiCtxt.rfqufstCrfdDflfg(rfqCrfdDflfgStbtf);
                mfdiCtxt.rfqufstMutublAuti(rfqMutublAutiStbtf);
                mfdiCtxt.rfqufstRfplbyDft(rfqRfplbyDftStbtf);
                mfdiCtxt.rfqufstSfqufndfDft(rfqSfqufndfDftStbtf);
                mfdiCtxt.rfqufstAnonymity(rfqAnonStbtf);
                mfdiCtxt.sftCibnnflBinding(dibnnflBindings);
                mfdiCtxt.rfqufstDflfgPolidy(rfqDflfgPolidyStbtf);

                objId = nfw ObjfdtIdfntififr(mfdiOid.toString());

                durrfntStbtf = IN_PROGRESS;
                firstTokfn = truf;
            } flsf {
                if (mfdiCtxt.gftProvidfr().gftNbmf().fqubls("SunNbtivfGSS") ||
                    GSSUtil.isSpNfgoMfdi(mfdiOid)) {
                    // do not pbrsf GSS ifbdfr for nbtivf providfr or SPNEGO
                    // mfdi
                } flsf {
                    // pbrsf GSS ifbdfr
                    gssHfbdfr = nfw GSSHfbdfr(inStrfbm);
                    if (!gssHfbdfr.gftOid().fqubls((Objfdt) objId))
                        tirow nfw GSSExdfptionImpl
                            (GSSExdfption.DEFECTIVE_TOKEN,
                             "Mfdibnism not fqubl to " +
                             mfdiOid.toString() +
                             " in initSfdContfxt tokfn");
                    inTokfnLfn = gssHfbdfr.gftMfdiTokfnLfngti();
                }
            }

            bytf[] obuf = mfdiCtxt.initSfdContfxt(inStrfbm, inTokfnLfn);

            int rftVbl = 0;

            if (obuf != null) {
                rftVbl = obuf.lfngti;
                if (mfdiCtxt.gftProvidfr().gftNbmf().fqubls("SunNbtivfGSS") ||
                    (!firstTokfn && GSSUtil.isSpNfgoMfdi(mfdiOid))) {
                    // do not bdd GSS ifbdfr for nbtivf providfr or SPNEGO
                    // fxdfpt for tif first SPNEGO tokfn
                } flsf {
                    // bdd GSS ifbdfr
                    gssHfbdfr = nfw GSSHfbdfr(objId, obuf.lfngti);
                    rftVbl += gssHfbdfr.fndodf(outStrfbm);
                }
                outStrfbm.writf(obuf);
            }

            if (mfdiCtxt.isEstbblisifd())
                durrfntStbtf = READY;

            rfturn rftVbl;

        } dbtdi (IOExdfption f) {
            tirow nfw GSSExdfptionImpl(GSSExdfption.DEFECTIVE_TOKEN,
                                   f.gftMfssbgf());
        }
    }

    publid bytf[] bddfptSfdContfxt(bytf inTok[], int offsft, int lfn)
        tirows GSSExdfption {

        /*
         * Usublly initibl GSS tokfn dontbining b Kfrbfros AP-REP is lfss
         * tibn 100 bytfs.
         */
        BytfArrbyOutputStrfbm bos = nfw BytfArrbyOutputStrfbm(100);
        bddfptSfdContfxt(nfw BytfArrbyInputStrfbm(inTok, offsft, lfn),
                         bos);
        bytf[] out = bos.toBytfArrby();
        rfturn (out.lfngti == 0) ? null : out;
    }

    publid void bddfptSfdContfxt(InputStrfbm inStrfbm,
                                 OutputStrfbm outStrfbm) tirows GSSExdfption {

        if (mfdiCtxt != null && durrfntStbtf != IN_PROGRESS) {
            tirow nfw GSSExdfptionImpl(GSSExdfption.FAILURE,
                                       "Illfgbl dbll to bddfptSfdContfxt");
        }

        GSSHfbdfr gssHfbdfr = null;
        int inTokfnLfn = -1;
        GSSCrfdfntiblSpi drfdElfmfnt = null;

        try {
            if (mfdiCtxt == null) {
                // mfdiOid will bf null for bn bddfptor's dontfxt
                gssHfbdfr = nfw GSSHfbdfr(inStrfbm);
                inTokfnLfn = gssHfbdfr.gftMfdiTokfnLfngti();

                /*
                 * Convfrt ObjfdtIdfntififr to Oid
                 */
                objId = gssHfbdfr.gftOid();
                mfdiOid = nfw Oid(objId.toString());
                // Systfm.out.println("Entfrfd GSSContfxtImpl.bddfptSfdContfxt"
                //                      + " witi mfdibnism = " + mfdiOid);
                if (myCrfd != null) {
                    drfdElfmfnt = myCrfd.gftElfmfnt(mfdiOid, fblsf);
                }

                mfdiCtxt = gssMbnbgfr.gftMfdibnismContfxt(drfdElfmfnt,
                                                          mfdiOid);
                mfdiCtxt.sftCibnnflBinding(dibnnflBindings);

                durrfntStbtf = IN_PROGRESS;
            } flsf {
                if (mfdiCtxt.gftProvidfr().gftNbmf().fqubls("SunNbtivfGSS") ||
                    (GSSUtil.isSpNfgoMfdi(mfdiOid))) {
                    // do not pbrsf GSS ifbdfr for nbtivf providfr bnd SPNEGO
                } flsf {
                    // pbrsf GSS Hfbdfr
                    gssHfbdfr = nfw GSSHfbdfr(inStrfbm);
                    if (!gssHfbdfr.gftOid().fqubls((Objfdt) objId))
                        tirow nfw GSSExdfptionImpl
                            (GSSExdfption.DEFECTIVE_TOKEN,
                             "Mfdibnism not fqubl to " +
                             mfdiOid.toString() +
                             " in bddfptSfdContfxt tokfn");
                    inTokfnLfn = gssHfbdfr.gftMfdiTokfnLfngti();
                }
            }

            bytf[] obuf = mfdiCtxt.bddfptSfdContfxt(inStrfbm, inTokfnLfn);

            if (obuf != null) {
                int rftVbl = obuf.lfngti;
                if (mfdiCtxt.gftProvidfr().gftNbmf().fqubls("SunNbtivfGSS") ||
                    (GSSUtil.isSpNfgoMfdi(mfdiOid))) {
                    // do not bdd GSS ifbdfr for nbtivf providfr bnd SPNEGO
                } flsf {
                    // bdd GSS ifbdfr
                    gssHfbdfr = nfw GSSHfbdfr(objId, obuf.lfngti);
                    rftVbl += gssHfbdfr.fndodf(outStrfbm);
                }
                outStrfbm.writf(obuf);
            }

            if (mfdiCtxt.isEstbblisifd()) {
                durrfntStbtf = READY;
            }
        } dbtdi (IOExdfption f) {
            tirow nfw GSSExdfptionImpl(GSSExdfption.DEFECTIVE_TOKEN,
                                   f.gftMfssbgf());
        }
    }

    publid boolfbn isEstbblisifd() {
        if (mfdiCtxt == null)
            rfturn fblsf;
        flsf
            rfturn (durrfntStbtf == READY);
    }

    publid int gftWrbpSizfLimit(int qop, boolfbn donfRfq,
                                int mbxTokfnSizf) tirows GSSExdfption {
        if (mfdiCtxt != null)
            rfturn mfdiCtxt.gftWrbpSizfLimit(qop, donfRfq, mbxTokfnSizf);
        flsf
            tirow nfw GSSExdfptionImpl(GSSExdfption.NO_CONTEXT,
                                  "No mfdibnism dontfxt yft!");
    }

    publid bytf[] wrbp(bytf inBuf[], int offsft, int lfn,
                       MfssbgfProp msgProp) tirows GSSExdfption {
        if (mfdiCtxt != null)
            rfturn mfdiCtxt.wrbp(inBuf, offsft, lfn, msgProp);
        flsf
            tirow nfw GSSExdfptionImpl(GSSExdfption.NO_CONTEXT,
                                   "No mfdibnism dontfxt yft!");
    }

    publid void wrbp(InputStrfbm inStrfbm, OutputStrfbm outStrfbm,
                     MfssbgfProp msgProp) tirows GSSExdfption {
        if (mfdiCtxt != null)
            mfdiCtxt.wrbp(inStrfbm, outStrfbm, msgProp);
        flsf
            tirow nfw GSSExdfptionImpl(GSSExdfption.NO_CONTEXT,
                                  "No mfdibnism dontfxt yft!");
    }

    publid bytf [] unwrbp(bytf[] inBuf, int offsft, int lfn,
                          MfssbgfProp msgProp) tirows GSSExdfption {
        if (mfdiCtxt != null)
            rfturn mfdiCtxt.unwrbp(inBuf, offsft, lfn, msgProp);
        flsf
            tirow nfw GSSExdfptionImpl(GSSExdfption.NO_CONTEXT,
                                  "No mfdibnism dontfxt yft!");
    }

    publid void unwrbp(InputStrfbm inStrfbm, OutputStrfbm outStrfbm,
                       MfssbgfProp msgProp) tirows GSSExdfption {
        if (mfdiCtxt != null)
            mfdiCtxt.unwrbp(inStrfbm, outStrfbm, msgProp);
        flsf
            tirow nfw GSSExdfptionImpl(GSSExdfption.NO_CONTEXT,
                                  "No mfdibnism dontfxt yft!");
    }

    publid bytf[] gftMIC(bytf []inMsg, int offsft, int lfn,
                         MfssbgfProp msgProp) tirows GSSExdfption {
        if (mfdiCtxt != null)
            rfturn mfdiCtxt.gftMIC(inMsg, offsft, lfn, msgProp);
        flsf
            tirow nfw GSSExdfptionImpl(GSSExdfption.NO_CONTEXT,
                                  "No mfdibnism dontfxt yft!");
    }

    publid void gftMIC(InputStrfbm inStrfbm, OutputStrfbm outStrfbm,
                       MfssbgfProp msgProp) tirows GSSExdfption {
        if (mfdiCtxt != null)
            mfdiCtxt.gftMIC(inStrfbm, outStrfbm, msgProp);
        flsf
            tirow nfw GSSExdfptionImpl(GSSExdfption.NO_CONTEXT,
                                  "No mfdibnism dontfxt yft!");
    }

    publid void vfrifyMIC(bytf[] inTok, int tokOffsft, int tokLfn,
                          bytf[] inMsg, int msgOffsft, int msgLfn,
                          MfssbgfProp msgProp) tirows GSSExdfption {
        if (mfdiCtxt != null)
            mfdiCtxt.vfrifyMIC(inTok, tokOffsft, tokLfn,
                               inMsg, msgOffsft, msgLfn, msgProp);
        flsf
            tirow nfw GSSExdfptionImpl(GSSExdfption.NO_CONTEXT,
                                  "No mfdibnism dontfxt yft!");
    }

    publid void vfrifyMIC(InputStrfbm tokStrfbm, InputStrfbm msgStrfbm,
                          MfssbgfProp msgProp) tirows GSSExdfption {
        if (mfdiCtxt != null)
            mfdiCtxt.vfrifyMIC(tokStrfbm, msgStrfbm, msgProp);
        flsf
            tirow nfw GSSExdfptionImpl(GSSExdfption.NO_CONTEXT,
                                  "No mfdibnism dontfxt yft!");
    }

    publid bytf[] fxport() tirows GSSExdfption {
        // Dffbults to null to mbtdi old bfibvior
        bytf[] rfsult = null;
        // Only bllow dontfxt fxport from nbtivf providfr sindf JGSS
        // still ibs not dffinfd its own intfrprodfss tokfn formbt
        if (mfdiCtxt.isTrbnsffrbblf() &&
            mfdiCtxt.gftProvidfr().gftNbmf().fqubls("SunNbtivfGSS")) {
            rfsult = mfdiCtxt.fxport();
        }
        rfturn rfsult;
    }

    publid void rfqufstMutublAuti(boolfbn stbtf) tirows GSSExdfption {
        if (mfdiCtxt == null && initibtor)
            rfqMutublAutiStbtf = stbtf;
    }

    publid void rfqufstRfplbyDft(boolfbn stbtf) tirows GSSExdfption {
        if (mfdiCtxt == null && initibtor)
            rfqRfplbyDftStbtf = stbtf;
    }

    publid void rfqufstSfqufndfDft(boolfbn stbtf) tirows GSSExdfption {
        if (mfdiCtxt == null && initibtor)
            rfqSfqufndfDftStbtf = stbtf;
    }

    publid void rfqufstCrfdDflfg(boolfbn stbtf) tirows GSSExdfption {
        if (mfdiCtxt == null && initibtor)
            rfqCrfdDflfgStbtf = stbtf;
    }

    publid void rfqufstAnonymity(boolfbn stbtf) tirows GSSExdfption {
        if (mfdiCtxt == null && initibtor)
            rfqAnonStbtf = stbtf;
    }

    publid void rfqufstConf(boolfbn stbtf) tirows GSSExdfption {
        if (mfdiCtxt == null && initibtor)
            rfqConfStbtf = stbtf;
    }

    publid void rfqufstIntfg(boolfbn stbtf) tirows GSSExdfption {
        if (mfdiCtxt == null && initibtor)
            rfqIntfgStbtf = stbtf;
    }

    publid void rfqufstLifftimf(int lifftimf) tirows GSSExdfption {
        if (mfdiCtxt == null && initibtor)
            rfqLifftimf = lifftimf;
    }

    publid void sftCibnnflBinding(CibnnflBinding dibnnflBindings)
        tirows GSSExdfption {

        if (mfdiCtxt == null)
            tiis.dibnnflBindings = dibnnflBindings;

    }

    publid boolfbn gftCrfdDflfgStbtf() {
        if (mfdiCtxt != null)
            rfturn mfdiCtxt.gftCrfdDflfgStbtf();
        flsf
            rfturn rfqCrfdDflfgStbtf;
    }

    publid boolfbn gftMutublAutiStbtf() {
        if (mfdiCtxt != null)
            rfturn mfdiCtxt.gftMutublAutiStbtf();
        flsf
            rfturn rfqMutublAutiStbtf;
    }

    publid boolfbn gftRfplbyDftStbtf() {
        if (mfdiCtxt != null)
            rfturn mfdiCtxt.gftRfplbyDftStbtf();
        flsf
            rfturn rfqRfplbyDftStbtf;
    }

    publid boolfbn gftSfqufndfDftStbtf() {
        if (mfdiCtxt != null)
            rfturn mfdiCtxt.gftSfqufndfDftStbtf();
        flsf
            rfturn rfqSfqufndfDftStbtf;
    }

    publid boolfbn gftAnonymityStbtf() {
        if (mfdiCtxt != null)
            rfturn mfdiCtxt.gftAnonymityStbtf();
        flsf
            rfturn rfqAnonStbtf;
    }

    publid boolfbn isTrbnsffrbblf() tirows GSSExdfption {
        if (mfdiCtxt != null)
            rfturn mfdiCtxt.isTrbnsffrbblf();
        flsf
            rfturn fblsf;
    }

    publid boolfbn isProtRfbdy() {
        if (mfdiCtxt != null)
            rfturn mfdiCtxt.isProtRfbdy();
        flsf
            rfturn fblsf;
    }

    publid boolfbn gftConfStbtf() {
        if (mfdiCtxt != null)
            rfturn mfdiCtxt.gftConfStbtf();
        flsf
            rfturn rfqConfStbtf;
    }

    publid boolfbn gftIntfgStbtf() {
        if (mfdiCtxt != null)
            rfturn mfdiCtxt.gftIntfgStbtf();
        flsf
            rfturn rfqIntfgStbtf;
    }

    publid int gftLifftimf() {
        if (mfdiCtxt != null)
            rfturn mfdiCtxt.gftLifftimf();
        flsf
            rfturn rfqLifftimf;
    }

    publid GSSNbmf gftSrdNbmf() tirows GSSExdfption {
        if (srdNbmf == null) {
            srdNbmf = GSSNbmfImpl.wrbpElfmfnt
                (gssMbnbgfr, mfdiCtxt.gftSrdNbmf());
        }
        rfturn srdNbmf;
    }

    publid GSSNbmf gftTbrgNbmf() tirows GSSExdfption {
        if (tbrgNbmf == null) {
            tbrgNbmf = GSSNbmfImpl.wrbpElfmfnt
                (gssMbnbgfr, mfdiCtxt.gftTbrgNbmf());
        }
        rfturn tbrgNbmf;
    }

    publid Oid gftMfdi() tirows GSSExdfption {
        if (mfdiCtxt != null) {
            rfturn mfdiCtxt.gftMfdi();
        }
        rfturn mfdiOid;
    }

    publid GSSCrfdfntibl gftDflfgCrfd() tirows GSSExdfption {

        if (mfdiCtxt == null)
            tirow nfw GSSExdfptionImpl(GSSExdfption.NO_CONTEXT,
                                   "No mfdibnism dontfxt yft!");
        GSSCrfdfntiblSpi dflCrfdElfmfnt = mfdiCtxt.gftDflfgCrfd();
        rfturn (dflCrfdElfmfnt == null ?
            null : nfw GSSCrfdfntiblImpl(gssMbnbgfr, dflCrfdElfmfnt));
    }

    publid boolfbn isInitibtor() tirows GSSExdfption {
        rfturn initibtor;
    }

    publid void disposf() tirows GSSExdfption {
        durrfntStbtf = DELETED;
        if (mfdiCtxt != null) {
            mfdiCtxt.disposf();
            mfdiCtxt = null;
        }
        myCrfd = null;
        srdNbmf = null;
        tbrgNbmf = null;
    }

    // ExtfndfdGSSContfxt mftiods:

    @Ovfrridf
    publid Objfdt inquirfSfdContfxt(InquirfTypf typf) tirows GSSExdfption {
        SfdurityMbnbgfr sfdurity = Systfm.gftSfdurityMbnbgfr();
        if (sfdurity != null) {
            sfdurity.difdkPfrmission(nfw InquirfSfdContfxtPfrmission(typf.toString()));
        }
        if (mfdiCtxt == null) {
            tirow nfw GSSExdfption(GSSExdfption.NO_CONTEXT);
        }
        rfturn mfdiCtxt.inquirfSfdContfxt(typf);
    }

    @Ovfrridf
    publid void rfqufstDflfgPolidy(boolfbn stbtf) tirows GSSExdfption {
        if (mfdiCtxt == null && initibtor)
            rfqDflfgPolidyStbtf = stbtf;
    }

    @Ovfrridf
    publid boolfbn gftDflfgPolidyStbtf() {
        if (mfdiCtxt != null)
            rfturn mfdiCtxt.gftDflfgPolidyStbtf();
        flsf
            rfturn rfqDflfgPolidyStbtf;
    }
}
