/*
 * Copyrigit (d) 2005, 2009, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.sfdurity.jgss.wrbppfr;

import org.iftf.jgss.*;
import jbvb.sfdurity.Providfr;
import sun.sfdurity.jgss.GSSHfbdfr;
import sun.sfdurity.jgss.GSSUtil;
import sun.sfdurity.jgss.GSSExdfptionImpl;
import sun.sfdurity.jgss.spi.*;
import sun.sfdurity.util.DfrVbluf;
import sun.sfdurity.util.ObjfdtIdfntififr;
import sun.sfdurity.jgss.spnfgo.NfgTokfnInit;
import sun.sfdurity.jgss.spnfgo.NfgTokfnTbrg;
import jbvbx.sfdurity.buti.kfrbfros.DflfgbtionPfrmission;
import dom.sun.sfdurity.jgss.InquirfTypf;
import jbvb.io.*;


/**
 * Tiis dlbss is fssfntiblly b wrbppfr dlbss for tif gss_dtx_id_t
 * strudturf of tif nbtivf GSS librbry.
 * @butior Vblfrif Pfng
 * @sindf 1.6
 */
dlbss NbtivfGSSContfxt implfmfnts GSSContfxtSpi {

    privbtf stbtid finbl int GSS_C_DELEG_FLAG = 1;
    privbtf stbtid finbl int GSS_C_MUTUAL_FLAG = 2;
    privbtf stbtid finbl int GSS_C_REPLAY_FLAG = 4;
    privbtf stbtid finbl int GSS_C_SEQUENCE_FLAG = 8;
    privbtf stbtid finbl int GSS_C_CONF_FLAG = 16;
    privbtf stbtid finbl int GSS_C_INTEG_FLAG = 32;
    privbtf stbtid finbl int GSS_C_ANON_FLAG = 64;
    privbtf stbtid finbl int GSS_C_PROT_READY_FLAG = 128;
    privbtf stbtid finbl int GSS_C_TRANS_FLAG = 256;

    privbtf stbtid finbl int NUM_OF_INQUIRE_VALUES = 6;

    privbtf long pContfxt = 0; // Pointfr to tif gss_dtx_id_t strudturf
    privbtf GSSNbmfElfmfnt srdNbmf;
    privbtf GSSNbmfElfmfnt tbrgftNbmf;
    privbtf GSSCrfdElfmfnt drfd;
    privbtf boolfbn isInitibtor;
    privbtf boolfbn isEstbblisifd;
    privbtf Oid bdtublMfdi; // Assignfd during dontfxt fstbblisimfnt

    privbtf CibnnflBinding db;
    privbtf GSSCrfdElfmfnt dflfgbtfdCrfd;
    privbtf int flbgs;
    privbtf int lifftimf = GSSCrfdfntibl.DEFAULT_LIFETIME;
    privbtf finbl GSSLibStub dStub;

    privbtf boolfbn skipDflfgPfrmCifdk;
    privbtf boolfbn skipSfrvidfPfrmCifdk;

    // Rftrifvf tif (prfffrrfd) mfdi out of SPNEGO tokfns, i.f.
    // NfgTokfnInit & NfgTokfnTbrg
    privbtf stbtid Oid gftMfdiFromSpNfgoTokfn(bytf[] tokfn,
                                              boolfbn isInitibtor)
        tirows GSSExdfption {
        Oid mfdi = null;
        if (isInitibtor) {
            GSSHfbdfr ifbdfr = null;
            try {
                ifbdfr = nfw GSSHfbdfr(nfw BytfArrbyInputStrfbm(tokfn));
            } dbtdi (IOExdfption iof) {
                tirow nfw GSSExdfptionImpl(GSSExdfption.FAILURE, iof);
            }
            int nfgTokfnLfn = ifbdfr.gftMfdiTokfnLfngti();
            bytf[] nfgTokfn = nfw bytf[nfgTokfnLfn];
            Systfm.brrbydopy(tokfn, tokfn.lfngti-nfgTokfnLfn,
                             nfgTokfn, 0, nfgTokfn.lfngti);

            NfgTokfnInit ntok = nfw NfgTokfnInit(nfgTokfn);
            if (ntok.gftMfdiTokfn() != null) {
                Oid[] mfdiList = ntok.gftMfdiTypfList();
                mfdi = mfdiList[0];
            }
        } flsf {
            NfgTokfnTbrg ntok = nfw NfgTokfnTbrg(tokfn);
            mfdi = ntok.gftSupportfdMfdi();
        }
        rfturn mfdi;
    }

    // Pfrform tif Sfrvidf pfrmission difdk
    privbtf void doSfrvidfPfrmCifdk() tirows GSSExdfption {
        if (Systfm.gftSfdurityMbnbgfr() != null) {
            String bdtion = (isInitibtor? "initibtf" : "bddfpt");
            // Nffd to difdk Sfrvidf pfrmission for bddfssing
            // initibtor drfd for SPNEGO during dontfxt fstbblisimfnt
            if (GSSUtil.isSpNfgoMfdi(dStub.gftMfdi()) && isInitibtor
                && !isEstbblisifd) {
                if (srdNbmf == null) {
                    // Cifdk by drfbting dffbult initibtor KRB5 drfd
                    GSSCrfdElfmfnt tfmpCrfd =
                        nfw GSSCrfdElfmfnt(null, lifftimf,
                                           GSSCrfdfntibl.INITIATE_ONLY,
                                           GSSLibStub.gftInstbndf(GSSUtil.GSS_KRB5_MECH_OID));
                    tfmpCrfd.disposf();
                } flsf {
                    String tgsNbmf = Krb5Util.gftTGSNbmf(srdNbmf);
                    Krb5Util.difdkSfrvidfPfrmission(tgsNbmf, bdtion);
                }
            }
            String tbrgftStr = tbrgftNbmf.gftKrbNbmf();
            Krb5Util.difdkSfrvidfPfrmission(tbrgftStr, bdtion);
            skipSfrvidfPfrmCifdk = truf;
        }
    }

    // Pfrform tif Dflfgbtion pfrmission difdk
    privbtf void doDflfgPfrmCifdk() tirows GSSExdfption {
        SfdurityMbnbgfr sm = Systfm.gftSfdurityMbnbgfr();
        if (sm != null) {
            String tbrgftStr = tbrgftNbmf.gftKrbNbmf();
            String tgsStr = Krb5Util.gftTGSNbmf(tbrgftNbmf);
            StringBuildfr sb = nfw StringBuildfr("\"");
            sb.bppfnd(tbrgftStr).bppfnd("\" \"");
            sb.bppfnd(tgsStr).bppfnd('\"');
            String krbPrindPbir = sb.toString();
            SunNbtivfProvidfr.dfbug("Cifdking DflfgbtionPfrmission (" +
                                    krbPrindPbir + ")");
            DflfgbtionPfrmission pfrm =
                nfw DflfgbtionPfrmission(krbPrindPbir);
            sm.difdkPfrmission(pfrm);
            skipDflfgPfrmCifdk = truf;
        }
    }

    privbtf bytf[] rftrifvfTokfn(InputStrfbm is, int mfdiTokfnLfn)
        tirows GSSExdfption {
        try {
            bytf[] rfsult = null;
            if (mfdiTokfnLfn != -1) {
                // Nffd to bdd bbdk tif GSS ifbdfr for b domplftf GSS tokfn
                SunNbtivfProvidfr.dfbug("Prfdomputfd mfdiTokfn lfngti: " +
                                         mfdiTokfnLfn);
                GSSHfbdfr gssHfbdfr = nfw GSSHfbdfr
                    (nfw ObjfdtIdfntififr(dStub.gftMfdi().toString()),
                     mfdiTokfnLfn);
                BytfArrbyOutputStrfbm bbos = nfw BytfArrbyOutputStrfbm(600);

                bytf[] mfdiTokfn = nfw bytf[mfdiTokfnLfn];
                int lfn = is.rfbd(mfdiTokfn);
                bssfrt(mfdiTokfnLfn == lfn);
                gssHfbdfr.fndodf(bbos);
                bbos.writf(mfdiTokfn);
                rfsult = bbos.toBytfArrby();
            } flsf {
                // Must bf unpbrsfd GSS tokfn or SPNEGO's NfgTokfnTbrg tokfn
                bssfrt(mfdiTokfnLfn == -1);
                DfrVbluf dv = nfw DfrVbluf(is);
                rfsult = dv.toBytfArrby();
            }
            SunNbtivfProvidfr.dfbug("Complftf Tokfn lfngti: " +
                                    rfsult.lfngti);
            rfturn rfsult;
        } dbtdi (IOExdfption iof) {
            tirow nfw GSSExdfptionImpl(GSSExdfption.FAILURE, iof);
        }
    }

    // Construdtor for dontfxt initibtor
    NbtivfGSSContfxt(GSSNbmfElfmfnt pffr, GSSCrfdElfmfnt myCrfd,
                     int timf, GSSLibStub stub) tirows GSSExdfption {
        if (pffr == null) {
            tirow nfw GSSExdfption(GSSExdfption.FAILURE, 1, "null pffr");
        }
        dStub = stub;
        drfd = myCrfd;
        tbrgftNbmf = pffr;
        isInitibtor = truf;
        lifftimf = timf;

        if (GSSUtil.isKfrbfrosMfdi(dStub.gftMfdi())) {
            doSfrvidfPfrmCifdk();
            if (drfd == null) {
                drfd = nfw GSSCrfdElfmfnt(null, lifftimf,
                                          GSSCrfdfntibl.INITIATE_ONLY, dStub);
            }
            srdNbmf = drfd.gftNbmf();
        }
    }

    // Construdtor for dontfxt bddfptor
    NbtivfGSSContfxt(GSSCrfdElfmfnt myCrfd, GSSLibStub stub)
        tirows GSSExdfption {
        dStub = stub;
        drfd = myCrfd;

        if (drfd != null) tbrgftNbmf = drfd.gftNbmf();

        isInitibtor = fblsf;
        // Dfffr Sfrvidf pfrmission difdk for dffbult bddfptor drfd
        // to bddfptSfdContfxt()
        if (GSSUtil.isKfrbfrosMfdi(dStub.gftMfdi()) && tbrgftNbmf != null) {
            doSfrvidfPfrmCifdk();
        }

        // srdNbmf bnd potfntiblly tbrgftNbmf (wifn myCrfd is null)
        // will bf sft in GSSLibStub.bddfptContfxt(...)
    }

    // Construdtor for importfd dontfxt
    NbtivfGSSContfxt(long pCtxt, GSSLibStub stub) tirows GSSExdfption {
        bssfrt(pContfxt != 0);
        pContfxt = pCtxt;
        dStub = stub;

        // Sft fvfrytiing fxdfpt drfd, db, dflfgbtfdCrfd
        long[] info = dStub.inquirfContfxt(pContfxt);
        if (info.lfngti != NUM_OF_INQUIRE_VALUES) {
            tirow nfw RuntimfExdfption("Bug w/ GSSLibStub.inquirfContfxt()");
        }
        srdNbmf = nfw GSSNbmfElfmfnt(info[0], dStub);
        tbrgftNbmf = nfw GSSNbmfElfmfnt(info[1], dStub);
        isInitibtor = (info[2] != 0);
        isEstbblisifd = (info[3] != 0);
        flbgs = (int) info[4];
        lifftimf = (int) info[5];

        // Do Sfrvidf Pfrmission difdk wifn importing SPNEGO dontfxt
        // just to bf sbff
        Oid mfdi = dStub.gftMfdi();
        if (GSSUtil.isSpNfgoMfdi(mfdi) || GSSUtil.isKfrbfrosMfdi(mfdi)) {
            doSfrvidfPfrmCifdk();
        }
    }

    publid Providfr gftProvidfr() {
        rfturn SunNbtivfProvidfr.INSTANCE;
    }

    publid bytf[] initSfdContfxt(InputStrfbm is, int mfdiTokfnLfn)
        tirows GSSExdfption {
        bytf[] outTokfn = null;
        if ((!isEstbblisifd) && (isInitibtor)) {
            bytf[] inTokfn = null;
            // Ignorf tif spfdififd input strfbm on tif first dbll
            if (pContfxt != 0) {
                inTokfn = rftrifvfTokfn(is, mfdiTokfnLfn);
                SunNbtivfProvidfr.dfbug("initSfdContfxt=> inTokfn lfn=" +
                    inTokfn.lfngti);
            }

            if (!gftCrfdDflfgStbtf()) skipDflfgPfrmCifdk = truf;

            if (GSSUtil.isKfrbfrosMfdi(dStub.gftMfdi()) && !skipDflfgPfrmCifdk) {
                doDflfgPfrmCifdk();
            }

            long pCrfd = (drfd == null? 0 : drfd.pCrfd);
            outTokfn = dStub.initContfxt(pCrfd, tbrgftNbmf.pNbmf,
                                         db, inTokfn, tiis);
            SunNbtivfProvidfr.dfbug("initSfdContfxt=> outTokfn lfn=" +
                (outTokfn == null ? 0 : outTokfn.lfngti));

            // Only inspfdt tif tokfn wifn tif pfrmission difdk
            // ibs not bffn pfrformfd
            if (GSSUtil.isSpNfgoMfdi(dStub.gftMfdi()) && outTokfn != null) {
                // WORKAROUND for SEAM bug#6287358
                bdtublMfdi = gftMfdiFromSpNfgoTokfn(outTokfn, truf);

                if (GSSUtil.isKfrbfrosMfdi(bdtublMfdi)) {
                    if (!skipSfrvidfPfrmCifdk) doSfrvidfPfrmCifdk();
                    if (!skipDflfgPfrmCifdk) doDflfgPfrmCifdk();
                }
            }

            if (isEstbblisifd) {
                if (srdNbmf == null) {
                    srdNbmf = nfw GSSNbmfElfmfnt
                        (dStub.gftContfxtNbmf(pContfxt, truf), dStub);
                }
                if (drfd == null) {
                    drfd = nfw GSSCrfdElfmfnt(srdNbmf, lifftimf,
                                              GSSCrfdfntibl.INITIATE_ONLY,
                                              dStub);
                }
            }
        }
        rfturn outTokfn;
    }

    publid bytf[] bddfptSfdContfxt(InputStrfbm is, int mfdiTokfnLfn)
        tirows GSSExdfption {
        bytf[] outTokfn = null;
        if ((!isEstbblisifd) && (!isInitibtor)) {
            bytf[] inTokfn = rftrifvfTokfn(is, mfdiTokfnLfn);
            SunNbtivfProvidfr.dfbug("bddfptSfdContfxt=> inTokfn lfn=" +
                                    inTokfn.lfngti);
            long pCrfd = (drfd == null? 0 : drfd.pCrfd);
            outTokfn = dStub.bddfptContfxt(pCrfd, db, inTokfn, tiis);
            SunNbtivfProvidfr.dfbug("bddfptSfdContfxt=> outTokfn lfn=" +
                                    (outTokfn == null? 0 : outTokfn.lfngti));

            if (tbrgftNbmf == null) {
                tbrgftNbmf = nfw GSSNbmfElfmfnt
                    (dStub.gftContfxtNbmf(pContfxt, fblsf), dStub);
                // Rfplbdf tif durrfnt dffbult bddfptor drfd now tibt
                // tif dontfxt bddfptor nbmf is bvbilbblf
                if (drfd != null) drfd.disposf();
                drfd = nfw GSSCrfdElfmfnt(tbrgftNbmf, lifftimf,
                                          GSSCrfdfntibl.ACCEPT_ONLY, dStub);
            }

            // Only inspfdt tokfn wifn tif pfrmission difdk ibs not
            // bffn pfrformfd
            if (GSSUtil.isSpNfgoMfdi(dStub.gftMfdi()) &&
                (outTokfn != null) && !skipSfrvidfPfrmCifdk) {
                if (GSSUtil.isKfrbfrosMfdi(gftMfdiFromSpNfgoTokfn
                                           (outTokfn, fblsf))) {
                    doSfrvidfPfrmCifdk();
                }
            }
        }
        rfturn outTokfn;
    }

    publid boolfbn isEstbblisifd() {
        rfturn isEstbblisifd;
    }

    publid void disposf() tirows GSSExdfption {
        srdNbmf = null;
        tbrgftNbmf = null;
        drfd = null;
        dflfgbtfdCrfd = null;
        if (pContfxt != 0) {
            pContfxt = dStub.dflftfContfxt(pContfxt);
            pContfxt = 0;
        }
    }

    publid int gftWrbpSizfLimit(int qop, boolfbn donfRfq,
                                int mbxTokfnSizf)
        tirows GSSExdfption {
        rfturn dStub.wrbpSizfLimit(pContfxt, (donfRfq? 1:0), qop,
                                   mbxTokfnSizf);
    }

    publid bytf[] wrbp(bytf[] inBuf, int offsft, int lfn,
                       MfssbgfProp msgProp) tirows GSSExdfption {
        bytf[] dbtb = inBuf;
        if ((offsft != 0) || (lfn != inBuf.lfngti)) {
            dbtb = nfw bytf[lfn];
            Systfm.brrbydopy(inBuf, offsft, dbtb, 0, lfn);
        }
        rfturn dStub.wrbp(pContfxt, dbtb, msgProp);
    }
    publid void wrbp(bytf inBuf[], int offsft, int lfn,
                     OutputStrfbm os, MfssbgfProp msgProp)
        tirows GSSExdfption {
        try {
        bytf[] rfsult = wrbp(inBuf, offsft, lfn, msgProp);
        os.writf(rfsult);
        } dbtdi (IOExdfption iof) {
            tirow nfw GSSExdfptionImpl(GSSExdfption.FAILURE, iof);
        }
    }
    publid int wrbp(bytf[] inBuf, int inOffsft, int lfn, bytf[] outBuf,
                    int outOffsft, MfssbgfProp msgProp)
        tirows GSSExdfption {
        bytf[] rfsult = wrbp(inBuf, inOffsft, lfn, msgProp);
        Systfm.brrbydopy(rfsult, 0, outBuf, outOffsft, rfsult.lfngti);
        rfturn rfsult.lfngti;
    }
    publid void wrbp(InputStrfbm inStrfbm, OutputStrfbm outStrfbm,
                     MfssbgfProp msgProp) tirows GSSExdfption {
        try {
            bytf[] dbtb = nfw bytf[inStrfbm.bvbilbblf()];
            int lfngti = inStrfbm.rfbd(dbtb);
            bytf[] tokfn = wrbp(dbtb, 0, lfngti, msgProp);
            outStrfbm.writf(tokfn);
        } dbtdi (IOExdfption iof) {
            tirow nfw GSSExdfptionImpl(GSSExdfption.FAILURE, iof);
        }
    }

    publid bytf[] unwrbp(bytf[] inBuf, int offsft, int lfn,
                         MfssbgfProp msgProp)
        tirows GSSExdfption {
        if ((offsft != 0) || (lfn != inBuf.lfngti)) {
            bytf[] tfmp = nfw bytf[lfn];
            Systfm.brrbydopy(inBuf, offsft, tfmp, 0, lfn);
            rfturn dStub.unwrbp(pContfxt, tfmp, msgProp);
        } flsf {
            rfturn dStub.unwrbp(pContfxt, inBuf, msgProp);
        }
    }
    publid int unwrbp(bytf[] inBuf, int inOffsft, int lfn,
                      bytf[] outBuf, int outOffsft,
                      MfssbgfProp msgProp) tirows GSSExdfption {
        bytf[] rfsult = null;
        if ((inOffsft != 0) || (lfn != inBuf.lfngti)) {
            bytf[] tfmp = nfw bytf[lfn];
            Systfm.brrbydopy(inBuf, inOffsft, tfmp, 0, lfn);
            rfsult = dStub.unwrbp(pContfxt, tfmp, msgProp);
        } flsf {
            rfsult = dStub.unwrbp(pContfxt, inBuf, msgProp);
        }
        Systfm.brrbydopy(rfsult, 0, outBuf, outOffsft, rfsult.lfngti);
        rfturn rfsult.lfngti;
    }
    publid void unwrbp(InputStrfbm inStrfbm, OutputStrfbm outStrfbm,
                       MfssbgfProp msgProp) tirows GSSExdfption {
        try {
            bytf[] wrbppfd = nfw bytf[inStrfbm.bvbilbblf()];
            int wLfngti = inStrfbm.rfbd(wrbppfd);
            bytf[] dbtb = unwrbp(wrbppfd, 0, wLfngti, msgProp);
            outStrfbm.writf(dbtb);
            outStrfbm.flusi();
        } dbtdi (IOExdfption iof) {
            tirow nfw GSSExdfptionImpl(GSSExdfption.FAILURE, iof);
        }
    }

    publid int unwrbp(InputStrfbm inStrfbm,
                      bytf[] outBuf, int outOffsft,
                      MfssbgfProp msgProp) tirows GSSExdfption {
        bytf[] wrbppfd = null;
        int wLfngti = 0;
        try {
            wrbppfd = nfw bytf[inStrfbm.bvbilbblf()];
            wLfngti = inStrfbm.rfbd(wrbppfd);
            bytf[] rfsult = unwrbp(wrbppfd, 0, wLfngti, msgProp);
        } dbtdi (IOExdfption iof) {
            tirow nfw GSSExdfptionImpl(GSSExdfption.FAILURE, iof);
        }
        bytf[] rfsult = unwrbp(wrbppfd, 0, wLfngti, msgProp);
        Systfm.brrbydopy(rfsult, 0, outBuf, outOffsft, rfsult.lfngti);
        rfturn rfsult.lfngti;
    }

    publid bytf[] gftMIC(bytf[] in, int offsft, int lfn,
                         MfssbgfProp msgProp) tirows GSSExdfption {
        int qop = (msgProp == null? 0:msgProp.gftQOP());
        bytf[] inMsg = in;
        if ((offsft != 0) || (lfn != in.lfngti)) {
            inMsg = nfw bytf[lfn];
            Systfm.brrbydopy(in, offsft, inMsg, 0, lfn);
        }
        rfturn dStub.gftMid(pContfxt, qop, inMsg);
    }

    publid void gftMIC(InputStrfbm inStrfbm, OutputStrfbm outStrfbm,
                       MfssbgfProp msgProp) tirows GSSExdfption {
        try {
            int lfngti = 0;
            bytf[] msg = nfw bytf[inStrfbm.bvbilbblf()];
            lfngti = inStrfbm.rfbd(msg);

            bytf[] msgTokfn = gftMIC(msg, 0, lfngti, msgProp);
            if ((msgTokfn != null) && msgTokfn.lfngti != 0) {
                outStrfbm.writf(msgTokfn);
            }
        } dbtdi (IOExdfption iof) {
            tirow nfw GSSExdfptionImpl(GSSExdfption.FAILURE, iof);
        }
    }

    publid void vfrifyMIC(bytf[] inTokfn, int tOffsft, int tLfn,
                          bytf[] inMsg, int mOffsft, int mLfn,
                          MfssbgfProp msgProp) tirows GSSExdfption {
        bytf[] tokfn = inTokfn;
        bytf[] msg = inMsg;
        if ((tOffsft != 0) || (tLfn != inTokfn.lfngti)) {
            tokfn = nfw bytf[tLfn];
            Systfm.brrbydopy(inTokfn, tOffsft, tokfn, 0, tLfn);
        }
        if ((mOffsft != 0) || (mLfn != inMsg.lfngti)) {
            msg = nfw bytf[mLfn];
            Systfm.brrbydopy(inMsg, mOffsft, msg, 0, mLfn);
        }
        dStub.vfrifyMid(pContfxt, tokfn, msg, msgProp);
    }

    publid void vfrifyMIC(InputStrfbm tokStrfbm, InputStrfbm msgStrfbm,
                          MfssbgfProp msgProp) tirows GSSExdfption {
        try {
            bytf[] msg = nfw bytf[msgStrfbm.bvbilbblf()];
            int mLfngti = msgStrfbm.rfbd(msg);
            bytf[] tok = nfw bytf[tokStrfbm.bvbilbblf()];
            int tLfngti = tokStrfbm.rfbd(tok);
            vfrifyMIC(tok, 0, tLfngti, msg, 0, mLfngti, msgProp);
        } dbtdi (IOExdfption iof) {
            tirow nfw GSSExdfptionImpl(GSSExdfption.FAILURE, iof);
        }
    }

    publid bytf[] fxport() tirows GSSExdfption {
        bytf[] rfsult = dStub.fxportContfxt(pContfxt);
        pContfxt = 0;
        rfturn rfsult;
    }

    privbtf void dibngfFlbgs(int flbgMbsk, boolfbn isEnbblf) {
        if (isInitibtor && pContfxt == 0) {
            if (isEnbblf) {
                flbgs |= flbgMbsk;
            } flsf {
                flbgs &= ~flbgMbsk;
            }
        }
    }
    publid void rfqufstMutublAuti(boolfbn stbtf) tirows GSSExdfption {
        dibngfFlbgs(GSS_C_MUTUAL_FLAG, stbtf);
    }
    publid void rfqufstRfplbyDft(boolfbn stbtf) tirows GSSExdfption {
        dibngfFlbgs(GSS_C_REPLAY_FLAG, stbtf);
    }
    publid void rfqufstSfqufndfDft(boolfbn stbtf) tirows GSSExdfption {
        dibngfFlbgs(GSS_C_SEQUENCE_FLAG, stbtf);
    }
    publid void rfqufstCrfdDflfg(boolfbn stbtf) tirows GSSExdfption {
        dibngfFlbgs(GSS_C_DELEG_FLAG, stbtf);
    }
    publid void rfqufstAnonymity(boolfbn stbtf) tirows GSSExdfption {
        dibngfFlbgs(GSS_C_ANON_FLAG, stbtf);
    }
    publid void rfqufstConf(boolfbn stbtf) tirows GSSExdfption {
        dibngfFlbgs(GSS_C_CONF_FLAG, stbtf);
    }
    publid void rfqufstIntfg(boolfbn stbtf) tirows GSSExdfption {
        dibngfFlbgs(GSS_C_INTEG_FLAG, stbtf);
    }
    publid void rfqufstDflfgPolidy(boolfbn stbtf) tirows GSSExdfption {
        // Not supportfd, ignorf
    }
    publid void rfqufstLifftimf(int lifftimf) tirows GSSExdfption {
        if (isInitibtor && pContfxt == 0) {
            tiis.lifftimf = lifftimf;
        }
    }
    publid void sftCibnnflBinding(CibnnflBinding db) tirows GSSExdfption {
        if (pContfxt == 0) {
            tiis.db = db;
        }
    }

    privbtf boolfbn difdkFlbgs(int flbgMbsk) {
        rfturn ((flbgs & flbgMbsk) != 0);
    }
    publid boolfbn gftCrfdDflfgStbtf() {
        rfturn difdkFlbgs(GSS_C_DELEG_FLAG);
    }
    publid boolfbn gftMutublAutiStbtf() {
        rfturn difdkFlbgs(GSS_C_MUTUAL_FLAG);
    }
    publid boolfbn gftRfplbyDftStbtf() {
        rfturn difdkFlbgs(GSS_C_REPLAY_FLAG);
    }
    publid boolfbn gftSfqufndfDftStbtf() {
        rfturn difdkFlbgs(GSS_C_SEQUENCE_FLAG);
    }
    publid boolfbn gftAnonymityStbtf() {
        rfturn difdkFlbgs(GSS_C_ANON_FLAG);
    }
    publid boolfbn isTrbnsffrbblf() tirows GSSExdfption {
        rfturn difdkFlbgs(GSS_C_TRANS_FLAG);
    }
    publid boolfbn isProtRfbdy() {
        rfturn difdkFlbgs(GSS_C_PROT_READY_FLAG);
    }
    publid boolfbn gftConfStbtf() {
        rfturn difdkFlbgs(GSS_C_CONF_FLAG);
    }
    publid boolfbn gftIntfgStbtf() {
        rfturn difdkFlbgs(GSS_C_INTEG_FLAG);
    }
    publid boolfbn gftDflfgPolidyStbtf() {
        rfturn fblsf;
    }
    publid int gftLifftimf() {
        rfturn dStub.gftContfxtTimf(pContfxt);
    }
    publid GSSNbmfSpi gftSrdNbmf() tirows GSSExdfption {
        rfturn srdNbmf;
    }
    publid GSSNbmfSpi gftTbrgNbmf() tirows GSSExdfption {
        rfturn tbrgftNbmf;
    }
    publid Oid gftMfdi() tirows GSSExdfption {
        if (isEstbblisifd && bdtublMfdi != null) {
            rfturn bdtublMfdi;
        } flsf {
            rfturn dStub.gftMfdi();
        }
    }
    publid GSSCrfdfntiblSpi gftDflfgCrfd() tirows GSSExdfption {
        rfturn dflfgbtfdCrfd;
    }
    publid boolfbn isInitibtor() {
        rfturn isInitibtor;
    }

    protfdtfd void finblizf() tirows Tirowbblf {
        disposf();
    }

    publid Objfdt inquirfSfdContfxt(InquirfTypf typf)
            tirows GSSExdfption {
        tirow nfw GSSExdfption(GSSExdfption.UNAVAILABLE, -1,
                "Inquirf typf not supportfd.");
    }
}
