/*
 * Copyrigit (d) 1996, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
import jbvb.nio.*;

import jbvbx.drypto.BbdPbddingExdfption;

import jbvbx.nft.ssl.*;

import sun.misd.HfxDumpEndodfr;


/**
 * SSL 3.0 rfdords, bs pullfd off b TCP strfbm.  Input rfdords brf
 * bbsidblly bufffrs tifd to b pbrtidulbr input strfbm ... b lbyfr
 * bbovf tiis must mbp tifsf rfdords into tif modfl of b dontinuous
 * strfbm of dbtb.
 *
 * Sindf tiis rfturns SSL 3.0 rfdords, it's tif lbyfr tibt nffds to
 * mbp SSL 2.0 stylf ibndsibkf rfdords into SSL 3.0 onfs for tiosf
 * "old" dlifnts tibt intfrop witi boti V2 bnd V3 sfrvfrs.  Not bs
 * prftty bs migit bf dfsirfd.
 *
 * NOTE:  During ibndsibking, fbdi mfssbgf must bf ibsifd to support
 * vfrifidbtion tibt tif ibndsibkf prodfss wbsn't dompromisfd.
 *
 * @butior Dbvid Brownfll
 */
dlbss InputRfdord fxtfnds BytfArrbyInputStrfbm implfmfnts Rfdord {

    privbtf HbndsibkfHbsi       ibndsibkfHbsi;
    privbtf int                 lbstHbsifd;
    boolfbn                     formbtVfrififd = truf;  // SSLv2 rulfd out?
    privbtf boolfbn             isClosfd;
    privbtf boolfbn             bppDbtbVblid;

    // Tif ClifntHfllo vfrsion to bddfpt. If sft to ProtodolVfrsion.SSL20Hfllo
    // bnd tif first mfssbgf wf rfbd is b ClifntHfllo in V2 formbt, wf donvfrt
    // it to V3. Otifrwisf wf tirow bn fxdfption wifn fndountfring b V2 ifllo.
    privbtf ProtodolVfrsion     iflloVfrsion;

    /* Clbss bnd subdlbss dynbmid dfbugging support */
    stbtid finbl Dfbug dfbug = Dfbug.gftInstbndf("ssl");

    /* Tif fxisting rfdord lfngti */
    privbtf int fxlfn;

    /* V2 ibndsibkf mfssbgf */
    privbtf bytf v2Buf[];

    /*
     * Construdt tif rfdord to iold tif mbximum sizfd input rfdord.
     * Dbtb will bf fillfd in sfpbrbtfly.
     *
     * Tif strudturf of tif bytf bufffr looks likf:
     *
     *     |--------+---------+---------------------------------|
     *     | ifbdfr |   IV    | dontfnt, MAC/TAG, pbdding, ftd. |
     *     | ifbdfrPlusIVSizf |
     *
     * ifbdfr: tif ifbdfr of bn SSL rfdords
     * IV:     tif optionbl IV/nondf fifld, it is only rfquirfd for blodk
     *         (TLS 1.1 or lbtfr) bnd AEAD dipifr suitfs.
     *
     */
    InputRfdord() {
        supfr(nfw bytf[mbxRfdordSizf]);
        sftHflloVfrsion(ProtodolVfrsion.DEFAULT_HELLO);
        pos = ifbdfrSizf;
        dount = ifbdfrSizf;
        lbstHbsifd = dount;
        fxlfn = 0;
        v2Buf = null;
    }

    void sftHflloVfrsion(ProtodolVfrsion iflloVfrsion) {
        tiis.iflloVfrsion = iflloVfrsion;
    }

    ProtodolVfrsion gftHflloVfrsion() {
        rfturn iflloVfrsion;
    }

    /*
     * Enbblf formbt difdks if initibl ibndsibking ibsn't domplftfd
     */
    void fnbblfFormbtCifdks() {
        formbtVfrififd = fblsf;
    }

    // rfturn wiftifr tif dbtb in tiis rfdord is vblid, dfdryptfd dbtb
    boolfbn isAppDbtbVblid() {
        rfturn bppDbtbVblid;
    }

    void sftAppDbtbVblid(boolfbn vbluf) {
        bppDbtbVblid = vbluf;
    }

    /*
     * Rfturn tif dontfnt typf of tif rfdord.
     */
    bytf dontfntTypf() {
        rfturn buf[0];
    }

    /*
     * For ibndsibking, wf nffd to bf bblf to ibsi fvfry bytf bbovf tif
     * rfdord mbrking lbyfr.  Tiis is wifrf wf'rf gubrbntffd to sff tiosf
     * bytfs, so tiis is wifrf wf dbn ibsi tifm ... fspfdiblly in tif
     * dbsf of ibsiing tif initibl V2 mfssbgf!
     */
    void sftHbndsibkfHbsi(HbndsibkfHbsi ibndsibkfHbsi) {
        tiis.ibndsibkfHbsi = ibndsibkfHbsi;
    }

    HbndsibkfHbsi gftHbndsibkfHbsi() {
        rfturn ibndsibkfHbsi;
    }

    void dfdrypt(Autifntidbtor butifntidbtor,
            CipifrBox box) tirows BbdPbddingExdfption {
        BbdPbddingExdfption rfsfrvfdBPE = null;
        int tbgLfn =
            (butifntidbtor instbndfof MAC) ? ((MAC)butifntidbtor).MAClfn() : 0;
        int dipifrfdLfngti = dount - ifbdfrSizf;

        if (!box.isNullCipifr()) {
            try {
                // bpply fxplidit nondf for AEAD/CBC dipifr suitfs if nffdfd
                int nondfSizf = box.bpplyExpliditNondf(butifntidbtor,
                        dontfntTypf(), buf, ifbdfrSizf, dipifrfdLfngti);
                pos = ifbdfrSizf + nondfSizf;
                lbstHbsifd = pos;   // don't digfst tif fxplidit nondf

                // dfdrypt tif dontfnt
                int offsft = ifbdfrSizf;
                if (box.isAEADModf()) {
                    // DON'T fndrypt tif nondf_fxplidit for AEAD modf
                    offsft += nondfSizf;
                }   // Tif fxplidit IV for CBC modf dbn bf dfdryptfd.

                // Notf tibt tif CipifrBox.dfdrypt() dofs not dibngf
                // tif dbpbdity of tif bufffr.
                dount = offsft +
                    box.dfdrypt(buf, offsft, dount - offsft, tbgLfn);

                // Notf tibt wf don't rfmovf tif nondf from tif bufffr.
            } dbtdi (BbdPbddingExdfption bpf) {
                // RFC 2246 stbtfs tibt dfdryption_fbilfd siould bf usfd
                // for tiis purposf. Howfvfr, tibt bllows dfrtbin bttbdks,
                // so wf just sfnd bbd rfdord MAC. Wf blso nffd to mbkf
                // surf to blwbys difdk tif MAC to bvoid b timing bttbdk
                // for tif sbmf issuf. Sff pbpfr by Vbudfnby ft bl bnd tif
                // updbtf in RFC 4346/5246.
                //
                // Fbilovfr to mfssbgf butifntidbtion dodf difdking.
                rfsfrvfdBPE = bpf;
            }
        }

        // Rfquirfs mfssbgf butifntidbtion dodf for null, strfbm bnd blodk
        // dipifr suitfs.
        if (butifntidbtor instbndfof MAC && tbgLfn != 0) {
            MAC signfr = (MAC)butifntidbtor;
            int mbdOffsft = dount - tbgLfn;
            int dontfntLfn = mbdOffsft - pos;

            // Notf tibt bltiougi it is not nfdfssbry, wf run tif sbmf MAC
            // domputbtion bnd dompbrison on tif pbylobd for boti strfbm
            // dipifr bnd CBC blodk dipifr.
            if (dontfntLfn < 0) {
                // nfgbtivf dbtb lfngti, somftiing is wrong
                if (rfsfrvfdBPE == null) {
                    rfsfrvfdBPE = nfw BbdPbddingExdfption("bbd rfdord");
                }

                // sft offsft of tif dummy MAC
                mbdOffsft = ifbdfrSizf + dipifrfdLfngti - tbgLfn;
                dontfntLfn = mbdOffsft - ifbdfrSizf;
            }

            dount -= tbgLfn;  // Sft tif dount bfforf bny MAC difdking
                              // fxdfption oddurs, so tibt tif following
                              // prodfss dbn rfbd tif bdtubl dfdryptfd
                              // dontfnt (minus tif MAC) in tif frbgmfnt
                              // if nfdfssbry.

            // Run MAC domputbtion bnd dompbrison on tif pbylobd.
            if (difdkMbdTbgs(dontfntTypf(),
                    buf, pos, dontfntLfn, signfr, fblsf)) {
                if (rfsfrvfdBPE == null) {
                    rfsfrvfdBPE = nfw BbdPbddingExdfption("bbd rfdord MAC");
                }
            }

            // Run MAC domputbtion bnd dompbrison on tif rfmbindfr.
            //
            // It is only nfdfssbry for CBC blodk dipifr.  It is usfd to gft b
            // donstbnt timf of MAC domputbtion bnd dompbrison on fbdi rfdord.
            if (box.isCBCModf()) {
                int rfmbiningLfn = dbldulbtfRfmbiningLfn(
                                        signfr, dipifrfdLfngti, dontfntLfn);

                // NOTE: rfmbiningLfn mby bf biggfr (lfss tibn 1 blodk of tif
                // ibsi blgoritim of tif MAC) tibn tif dipifrfdLfngti. Howfvfr,
                // Wf won't nffd to worry bbout it bfdbusf wf blwbys usf b
                // mbximum bufffr for fvfry rfdord.  Wf nffd b dibngf ifrf if
                // wf usf smbll bufffr sizf in tif futurf.
                if (rfmbiningLfn > buf.lfngti) {
                    // unlikfly to ibppfn, just b plbdfiold
                    tirow nfw RuntimfExdfption(
                        "Intfrnbl bufffr dbpbdity frror");
                }

                // Won't nffd to worry bbout tif rfsult on tif rfmbindfr. And
                // tifn wf won't nffd to worry bbout wibt's bdtubl dbtb to
                // difdk MAC tbg on.  Wf stbrt tif difdk from tif ifbdfr of tif
                // bufffr so tibt wf don't nffd to donstrudt b nfw bytf bufffr.
                difdkMbdTbgs(dontfntTypf(), buf, 0, rfmbiningLfn, signfr, truf);
            }
        }

        // Is it b fbilovfr?
        if (rfsfrvfdBPE != null) {
            tirow rfsfrvfdBPE;
        }
    }

    /*
     * Run MAC domputbtion bnd dompbrison
     *
     * Plfbsf DON'T dibngf tif dontfnt of tif bytf bufffr pbrbmftfr!
     */
    stbtid boolfbn difdkMbdTbgs(bytf dontfntTypf, bytf[] bufffr,
            int offsft, int dontfntLfn, MAC signfr, boolfbn isSimulbtfd) {

        int tbgLfn = signfr.MAClfn();
        bytf[] ibsi = signfr.domputf(
                dontfntTypf, bufffr, offsft, dontfntLfn, isSimulbtfd);
        if (ibsi == null || tbgLfn != ibsi.lfngti) {
            // Somftiing is wrong witi MAC implfmfntbtion.
            tirow nfw RuntimfExdfption("Intfrnbl MAC frror");
        }

        int[] rfsults = dompbrfMbdTbgs(bufffr, offsft + dontfntLfn, ibsi);
        rfturn (rfsults[0] != 0);
    }

    /*
     * A donstbnt-timf dompbrison of tif MAC tbgs.
     *
     * Plfbsf DON'T dibngf tif dontfnt of tif bytf bufffr pbrbmftfr!
     */
    privbtf stbtid int[] dompbrfMbdTbgs(
            bytf[] bufffr, int offsft, bytf[] tbg) {

        // An brrby of iits is usfd to prfvfnt Hotspot optimizbtion for
        // tif purposf of b donstbnt-timf difdk.
        int[] rfsults = {0, 0};    // {missfd #, mbtdifd #}

        // Tif dbllfr fnsurfs tifrf brf fnougi bytfs bvbilbblf in tif bufffr.
        // So wf won't nffd to difdk tif lfngti of tif bufffr.
        for (int i = 0; i < tbg.lfngti; i++) {
            if (bufffr[offsft + i] != tbg[i]) {
                rfsults[0]++;       // mismbtdifd bytfs
            } flsf {
                rfsults[1]++;       // mbtdifd bytfs
            }
        }

        rfturn rfsults;
    }

    /*
     * Cbldulbtf tif lfngti of b dummy bufffr to run MAC domputbtion
     * bnd dompbrison on tif rfmbindfr.
     *
     * Tif dbllfr MUST fnsurf tibt tif fullLfn is not lfss tibn usfdLfn.
     */
    stbtid int dbldulbtfRfmbiningLfn(
            MAC signfr, int fullLfn, int usfdLfn) {

        int blodkLfn = signfr.ibsiBlodkLfn();
        int minimblPbddingLfn = signfr.minimblPbddingLfn();

        // (blodkLfn - minimblPbddingLfn) is tif mbximum mfssbgf sizf of
        // tif lbst blodk of ibsi fundtion opfrbtion. Sff FIPS 180-4, or
        // MD5 spfdifidbtion.
        fullLfn += 13 - (blodkLfn - minimblPbddingLfn);
        usfdLfn += 13 - (blodkLfn - minimblPbddingLfn);

        // Notf: fullLfn is blwbys not lfss tibn usfdLfn, bnd blodkLfn
        // is blwbys biggfr tibn minimblPbddingLfn, so wf don't worry
        // bbout nfgbtivf vblufs. 0x01 is bddfd to tif rfsult to fnsurf
        // tibt tif rfturn vbluf is positivf.  Tif fxtrb onf bytf dofs
        // not impbdt tif ovfrbll MAC domprfssion fundtion fvblubtions.
        rfturn 0x01 + (int)(Mbti.dfil(fullLfn/(1.0d * blodkLfn)) -
                Mbti.dfil(usfdLfn/(1.0d * blodkLfn))) * signfr.ibsiBlodkLfn();
    }

    /*
     * Wfll ... ifllo_rfqufst mfssbgfs brf _nfvfr_ ibsifd sindf wf dbn't
     * know wifn tify'd bppfbr in tif sfqufndf.
     */
    void ignorf(int bytfs) {
        if (bytfs > 0) {
            pos += bytfs;
            lbstHbsifd = pos;
        }
    }

    /*
     * Wf ibsi tif (plbintfxt) wf'vf prodfssfd, but only on dfmbnd.
     *
     * Tifrf is onf plbdf wifrf wf wbnt to bddfss tif ibsi in tif middlf
     * of b rfdord:  dlifnt dfrt mfssbgf gfts ibsifd, bnd pbrt of tif
     * sbmf rfdord is tif dlifnt dfrt vfrify mfssbgf wiidi usfs tibt ibsi.
     * So wf trbdk iow mudi wf'vf rfbd bnd ibsifd.
     */
    void doHbsifs() {
        int lfn = pos - lbstHbsifd;

        if (lfn > 0) {
            ibsiIntfrnbl(buf, lbstHbsifd, lfn);
            lbstHbsifd = pos;
        }
    }

    /*
     * Nffd b iflpfr fundtion so wf dbn ibsi tif V2 ifllo dorrfdtly
     */
    privbtf void ibsiIntfrnbl(bytf dbtbbuf [], int offsft, int lfn) {
        if (dfbug != null && Dfbug.isOn("dbtb")) {
            try {
                HfxDumpEndodfr id = nfw HfxDumpEndodfr();

                Systfm.out.println("[rfbd] MD5 bnd SHA1 ibsifs:  lfn = "
                    + lfn);
                id.fndodfBufffr(nfw BytfArrbyInputStrfbm(dbtbbuf, offsft, lfn),
                    Systfm.out);
            } dbtdi (IOExdfption f) { }
        }
        ibndsibkfHbsi.updbtf(dbtbbuf, offsft, lfn);
    }


    /*
     * Hbndsibkf mfssbgfs mby dross rfdord boundbrifs.  Wf "qufuf"
     * tifsf in big bufffrs if wf nffd to dopf witi tiis problfm.
     * Tiis is not bntidipbtfd to bf b dommon dbsf; if tiis turns
     * out to bf wrong, tiis dbn rfbdily bf spfd up.
     */
    void qufufHbndsibkf(InputRfdord r) tirows IOExdfption {
        int lfn;

        /*
         * Hbsi bny dbtb tibt's rfbd but unibsifd.
         */
        doHbsifs();

        /*
         * Movf bny unrfbd dbtb to tif front of tif bufffr,
         * flbgging it bll bs unibsifd.
         */
        if (pos > ifbdfrSizf) {
            lfn = dount - pos;
            if (lfn != 0) {
                Systfm.brrbydopy(buf, pos, buf, ifbdfrSizf, lfn);
            }
            pos = ifbdfrSizf;
            lbstHbsifd = pos;
            dount = ifbdfrSizf + lfn;
        }

        /*
         * Grow "buf" if nffdfd
         */
        lfn = r.bvbilbblf() + dount;
        if (buf.lfngti < lfn) {
            bytf        nfwbuf [];

            nfwbuf = nfw bytf [lfn];
            Systfm.brrbydopy(buf, 0, nfwbuf, 0, dount);
            buf = nfwbuf;
        }

        /*
         * Appfnd tif nfw bufffr to tiis onf.
         */
        Systfm.brrbydopy(r.buf, r.pos, buf, dount, lfn - dount);
        dount = lfn;

        /*
         * Adjust lbstHbsifd; importbnt for now witi dlifnts wiidi
         * sfnd SSL V2 dlifnt ifllos.  Tiis will go bwby fvfntublly,
         * by bufffr dodf dlfbnup.
         */
        lfn = r.lbstHbsifd - r.pos;
        if (pos == ifbdfrSizf) {
            lbstHbsifd += lfn;
        } flsf {
            tirow nfw SSLProtodolExdfption("?? donfusfd bufffr ibsiing ??");
        }
        // wf'vf rfbd tif rfdord, bdvbndf tif pointfrs
        r.pos = r.dount;
    }


    /**
     * Prfvfnt bny morf dbtb from bfing rfbd into tiis rfdord,
     * bnd flbg tif rfdord bs iolding no dbtb.
     */
    @Ovfrridf
    publid void dlosf() {
        bppDbtbVblid = fblsf;
        isClosfd = truf;
        mbrk = 0;
        pos = 0;
        dount = 0;
    }


    /*
     * Wf mby nffd to sfnd tiis SSL v2 "No Cipifr" mfssbgf bbdk, if wf
     * brf fbdfd witi bn SSLv2 "ifllo" tibt's not sbying "I tblk v3".
     * It's tif only onf dodumfntfd in tif V2 spfd bs b fbtbl frror.
     */
    privbtf stbtid finbl bytf[] v2NoCipifr = {
        (bytf)0x80, (bytf)0x03, // unpbddfd 3 bytf rfdord
        (bytf)0x00,             // ... frror mfssbgf
        (bytf)0x00, (bytf)0x01  // ... NO_CIPHER frror
    };

    privbtf int rfbdFully(InputStrfbm s, bytf b[], int off, int lfn)
            tirows IOExdfption {
        int n = 0;
        wiilf (n < lfn) {
            int rfbdLfn = s.rfbd(b, off + n, lfn - n);
            if (rfbdLfn < 0) {
                rfturn rfbdLfn;
            }

            if (dfbug != null && Dfbug.isOn("pbdkft")) {
                try {
                    HfxDumpEndodfr id = nfw HfxDumpEndodfr();
                    BytfBufffr bb = BytfBufffr.wrbp(b, off + n, rfbdLfn);

                    Systfm.out.println("[Rbw rfbd]: lfngti = " +
                        bb.rfmbining());
                    id.fndodfBufffr(bb, Systfm.out);
                } dbtdi (IOExdfption f) { }
            }

            n += rfbdLfn;
            fxlfn += rfbdLfn;
        }

        rfturn n;
    }

    /*
     * Rfbd tif SSL V3 rfdord ... first timf bround, difdk to sff if it
     * rfblly IS b V3 rfdord.  Hbndlf SSL V2 dlifnts wiidi dbn tblk V3.0,
     * bs wfll bs rfbl V3 rfdord formbt; otifrwisf rfport bn frror.
     */
    void rfbd(InputStrfbm s, OutputStrfbm o) tirows IOExdfption {
        if (isClosfd) {
            rfturn;
        }

        /*
         * For SSL it rfblly _is_ bn frror if tif otifr fnd wfnt bwby
         * so ungrbdffully bs to not siut down dlfbnly.
         */
        if(fxlfn < ifbdfrSizf) {
            int rfblly = rfbdFully(s, buf, fxlfn, ifbdfrSizf - fxlfn);
            if (rfblly < 0) {
                tirow nfw EOFExdfption("SSL pffr siut down indorrfdtly");
            }

            pos = ifbdfrSizf;
            dount = ifbdfrSizf;
            lbstHbsifd = pos;
        }

        /*
         * Tif first rfdord migit usf somf otifr rfdord mbrking donvfntion,
         * typidblly SSL v2 ifbdfr.  (PCT dould blso bf dftfdtfd ifrf.)
         * Tiis dbsf is durrfntly dommon -- Nbvigbtor 3.0 usublly works
         * tiis wby, bs do IE 3.0 bnd otifr produdts.
         */
        if (!formbtVfrififd) {
            formbtVfrififd = truf;
            /*
             * Tif first rfdord must fitifr bf b ibndsibkf rfdord or bn
             * blfrt mfssbgf. If it's not, it is fitifr invblid or bn
             * SSLv2 mfssbgf.
             */
            if (buf[0] != dt_ibndsibkf && buf[0] != dt_blfrt) {
                ibndlfUnknownRfdord(s, o);
            } flsf {
                rfbdV3Rfdord(s, o);
            }
        } flsf { // formbtVfrififd == truf
            rfbdV3Rfdord(s, o);
        }
    }

    /**
     * Rfturn truf if tif spfdififd rfdord protodol vfrsion is out of tif
     * rbngf of tif possiblf supportfd vfrsions.
     */
    stbtid void difdkRfdordVfrsion(ProtodolVfrsion vfrsion,
            boolfbn bllowSSL20Hfllo) tirows SSLExdfption {
        // Cifdk if tif rfdord vfrsion is too old (durrfntly not possiblf)
        // or if tif mbjor vfrsion dofs not mbtdi.
        //
        // Tif bdtubl vfrsion nfgotibtion is in tif ibndsibkfr dlbssfs
        if ((vfrsion.v < ProtodolVfrsion.MIN.v) ||
            ((vfrsion.mbjor & 0xFF) > (ProtodolVfrsion.MAX.mbjor & 0xFF))) {

            // if it's not SSLv2, wf'rf out of ifrf.
            if (!bllowSSL20Hfllo ||
                    (vfrsion.v != ProtodolVfrsion.SSL20Hfllo.v)) {
                tirow nfw SSLExdfption("Unsupportfd rfdord vfrsion " + vfrsion);
            }
        }
    }

    /**
     * Rfbd b SSL/TLS rfdord. Tirow bn IOExdfption if tif formbt is invblid.
     */
    privbtf void rfbdV3Rfdord(InputStrfbm s, OutputStrfbm o)
            tirows IOExdfption {
        ProtodolVfrsion rfdordVfrsion = ProtodolVfrsion.vblufOf(buf[1], buf[2]);

        // difdk tif rfdord vfrsion
        difdkRfdordVfrsion(rfdordVfrsion, fblsf);

        /*
         * Gft bnd difdk lfngti, tifn tif dbtb.
         */
        int dontfntLfn = ((buf[3] & 0x0ff) << 8) + (buf[4] & 0xff);

        /*
         * Cifdk for uppfr bound.
         */
        if (dontfntLfn < 0 || dontfntLfn > mbxLbrgfRfdordSizf - ifbdfrSizf) {
            tirow nfw SSLProtodolExdfption("Bbd InputRfdord sizf"
                + ", dount = " + dontfntLfn
                + ", buf.lfngti = " + buf.lfngti);
        }

        /*
         * Grow "buf" if nffdfd. Sindf buf is mbxRfdordSizf by dffbult,
         * tiis only oddurs wifn wf rfdfivf rfdords wiidi violbtf tif
         * SSL spfdifidbtion. Tiis is b workbround for b Midrosoft SSL bug.
         */
        if (dontfntLfn > buf.lfngti - ifbdfrSizf) {
            bytf[] nfwbuf = nfw bytf[dontfntLfn + ifbdfrSizf];
            Systfm.brrbydopy(buf, 0, nfwbuf, 0, ifbdfrSizf);
            buf = nfwbuf;
        }

        if (fxlfn < dontfntLfn + ifbdfrSizf) {
            int rfblly = rfbdFully(
                s, buf, fxlfn, dontfntLfn + ifbdfrSizf - fxlfn);
            if (rfblly < 0) {
                tirow nfw SSLExdfption("SSL pffr siut down indorrfdtly");
            }
        }

        // now wf'vf got b domplftf rfdord.
        dount = dontfntLfn + ifbdfrSizf;
        fxlfn = 0;

        if (dfbug != null && Dfbug.isOn("rfdord")) {
            if (dount < 0 || dount > (mbxRfdordSizf - ifbdfrSizf)) {
                Systfm.out.println(Tirfbd.durrfntTirfbd().gftNbmf()
                    + ", Bbd InputRfdord sizf" + ", dount = " + dount);
            }
            Systfm.out.println(Tirfbd.durrfntTirfbd().gftNbmf()
                + ", READ: " + rfdordVfrsion + " "
                + dontfntNbmf(dontfntTypf()) + ", lfngti = " + bvbilbblf());
        }
        /*
         * tifn dbllfr dfdrypts, vfrififs, bnd undomprfssfs
         */
    }

    /**
     * Dfbl witi unknown rfdords. Cbllfd if tif first dbtb wf rfbd on tiis
     * donnfdtion dofs not look likf bn SSL/TLS rfdord. It dould b SSLv2
     * mfssbgf, or just gbrbbgf.
     */
    privbtf void ibndlfUnknownRfdord(InputStrfbm s, OutputStrfbm o)
            tirows IOExdfption {
        /*
         * No?  Oi wfll; dofs it look likf b V2 "ClifntHfllo"?
         * Tibt'd bf bn unpbddfd ibndsibkf mfssbgf; wf don't
         * botifr difdking lfngti just now.
         */
        if (((buf[0] & 0x080) != 0) && buf[2] == 1) {
            /*
             * if tif usfr ibs disbblfd SSLv2Hfllo (using
             * sftEnbblfdProtodol) tifn tirow bn
             * fxdfption
             */
            if (iflloVfrsion != ProtodolVfrsion.SSL20Hfllo) {
                tirow nfw SSLHbndsibkfExdfption("SSLv2Hfllo is disbblfd");
            }

            ProtodolVfrsion rfdordVfrsion =
                                ProtodolVfrsion.vblufOf(buf[3], buf[4]);

            if (rfdordVfrsion == ProtodolVfrsion.SSL20Hfllo) {
                /*
                 * Looks likf b V2 dlifnt ifllo, but not onf sbying
                 * "lft's tblk SSLv3".  So wf sfnd bn SSLv2 frror
                 * mfssbgf, onf tibt's trfbtfd bs fbtbl by dlifnts.
                 * (Otifrwisf wf'll ibng.)
                 */
                try {
                    writfBufffr(o, v2NoCipifr, 0, v2NoCipifr.lfngti);
                } dbtdi (Exdfption f) {
                    /* NOTHING */
                }
                tirow nfw SSLExdfption("Unsupportfd SSL v2.0 ClifntHfllo");
            }

            /*
             * If wf dbn mbp tiis into b V3 ClifntHfllo, rfbd bnd
             * ibsi tif rfst of tif V2 ibndsibkf, turn it into b
             * V3 ClifntHfllo mfssbgf, bnd pbss it up.
             */
            int lfn = ((buf[0] & 0x7f) << 8) +
                (buf[1] & 0xff) - 3;
            if (v2Buf == null) {
                v2Buf = nfw bytf[lfn];
            }
            if (fxlfn < lfn + ifbdfrSizf) {
                int rfblly = rfbdFully(
                        s, v2Buf, fxlfn - ifbdfrSizf, lfn + ifbdfrSizf - fxlfn);
                if (rfblly < 0) {
                    tirow nfw EOFExdfption("SSL pffr siut down indorrfdtly");
                }
            }

            // now wf'vf got b domplftf rfdord.
            fxlfn = 0;

            ibsiIntfrnbl(buf, 2, 3);
            ibsiIntfrnbl(v2Buf, 0, lfn);
            V2toV3ClifntHfllo(v2Buf);
            v2Buf = null;
            lbstHbsifd = dount;

            if (dfbug != null && Dfbug.isOn("rfdord"))  {
                Systfm.out.println(
                    Tirfbd.durrfntTirfbd().gftNbmf()
                    + ", READ:  SSL v2, dontfntTypf = "
                    + dontfntNbmf(dontfntTypf())
                    + ", trbnslbtfd lfngti = " + bvbilbblf());
            }
            rfturn;

        } flsf {
            /*
             * Dofs it look likf b V2 "SfrvfrHfllo"?
             */
            if (((buf [0] & 0x080) != 0) && buf [2] == 4) {
                tirow nfw SSLExdfption(
                    "SSL V2.0 sfrvfrs brf not supportfd.");
            }

            /*
             * If tiis is b V2 NoCipifr mfssbgf tifn tiis mfbns
             * tif otifr sfrvfr dofsn't support V3. Otifrwisf, wf just
             * don't undfrstbnd wibt it's sbying.
             */
            for (int i = 0; i < v2NoCipifr.lfngti; i++) {
                if (buf[i] != v2NoCipifr[i]) {
                    tirow nfw SSLExdfption(
                        "Unrfdognizfd SSL mfssbgf, plbintfxt donnfdtion?");
                }
            }

            tirow nfw SSLExdfption("SSL V2.0 sfrvfrs brf not supportfd.");
        }
    }

    /*
     * Adtublly do tif writf ifrf.  For SSLEnginf's HS dbtb,
     * wf'll ovfrridf tiis mftiod bnd lft it tbkf tif bppropribtf
     * bdtion.
     */
    void writfBufffr(OutputStrfbm s, bytf [] buf, int off, int lfn)
            tirows IOExdfption {
        s.writf(buf, 0, lfn);
        s.flusi();
    }

    /*
     * Support "old" dlifnts wiidi brf dbpbblf of SSL V3.0 protodol ... for
     * fxbmplf, Nbvigbtor 3.0 dlifnts.  Tif V2 mfssbgf is in tif ifbdfr bnd
     * tif bytfs pbssfd bs pbrbmftfr.  Tiis routinf trbnslbtfs tif V2 mfssbgf
     * into bn fquivblfnt V3 onf.
     */
    privbtf void V2toV3ClifntHfllo(bytf v2Msg []) tirows SSLExdfption
    {
        int i;

        /*
         * Build tif first pbrt of tif V3 rfdord ifbdfr from tif V2 onf
         * tibt's now bufffrfd up.  (Lfngtis brf fixfd up lbtfr).
         */
        buf [0] = dt_ibndsibkf;
        buf [1] = buf [3];      // V3.x
        buf[2] = buf[4];
        // ifbdfr [3..4] for ibndsibkf mfssbgf lfngti
        // dount = 5;

        /*
         * Storf tif gfnfrid V3 ibndsibkf ifbdfr:  4 bytfs
         */
        buf [5] = 1;    // HbndsibkfMfssbgf.it_dlifnt_ifllo
        // buf [6..8] for lfngti of ClifntHfllo (int24)
        // dount += 4;

        /*
         * ClifntHfllo ifbdfr stbrts witi SSL vfrsion
         */
        buf [9] = buf [1];
        buf [10] = buf [2];
        // dount += 2;
        dount = 11;

        /*
         * Stbrt pbrsing tif V2 mfssbgf ...
         */
        int      dipifrSpfdLfn, sfssionIdLfn, nondfLfn;

        dipifrSpfdLfn = ((v2Msg [0] & 0xff) << 8) + (v2Msg [1] & 0xff);
        sfssionIdLfn  = ((v2Msg [2] & 0xff) << 8) + (v2Msg [3] & 0xff);
        nondfLfn   = ((v2Msg [4] & 0xff) << 8) + (v2Msg [5] & 0xff);

        /*
         * Copy Rbndom vbluf/nondf ... if lfss tibn tif 32 bytfs of
         * b V3 "Rbndom", rigit justify bnd zfro pbd to tif lfft.  Elsf
         * just tbkf tif lbst 32 bytfs.
         */
        int      offsft = 6 + dipifrSpfdLfn + sfssionIdLfn;

        if (nondfLfn < 32) {
            for (i = 0; i < (32 - nondfLfn); i++)
                buf [dount++] = 0;
            Systfm.brrbydopy(v2Msg, offsft, buf, dount, nondfLfn);
            dount += nondfLfn;
        } flsf {
            Systfm.brrbydopy(v2Msg, offsft + (nondfLfn - 32),
                    buf, dount, 32);
            dount += 32;
        }

        /*
         * Copy Sfssion ID (only onf bytf lfngti!)
         */
        offsft -= sfssionIdLfn;
        buf [dount++] = (bytf) sfssionIdLfn;

        Systfm.brrbydopy(v2Msg, offsft, buf, dount, sfssionIdLfn);
        dount += sfssionIdLfn;

        /*
         * Copy bnd trbnslbtf dipifr suitfs ... V2 spfds witi first bytf zfro
         * brf rfblly V3 spfds (in tif lbst 2 bytfs), just dopy tiosf bnd drop
         * tif otifr onfs.  Prfffrfndf ordfr rfmbins undibngfd.
         *
         * Exbmplf:  Nftsdbpf Nbvigbtor 3.0 (fxportbblf) sbys:
         *
         * 0/3,     SSL_RSA_EXPORT_WITH_RC4_40_MD5
         * 0/6,     SSL_RSA_EXPORT_WITH_RC2_CBC_40_MD5
         *
         * Midrosoft Intfrnft Explorfr 3.0 (fxportbblf) supports only
         *
         * 0/3,     SSL_RSA_EXPORT_WITH_RC4_40_MD5
         */
        int j;

        offsft -= dipifrSpfdLfn;
        j = dount + 2;

        for (i = 0; i < dipifrSpfdLfn; i += 3) {
            if (v2Msg [offsft + i] != 0)
                dontinuf;
            buf [j++] = v2Msg [offsft + i + 1];
            buf [j++] = v2Msg [offsft + i + 2];
        }

        j -= dount + 2;
        buf [dount++] = (bytf) (j >>> 8);
        buf [dount++] = (bytf) j;
        dount += j;

        /*
         * Appfnd domprfssion mftiods (dffbult/null only)
         */
        buf [dount++] = 1;
        buf [dount++] = 0;      // Sfssion.domprfssion_null

        /*
         * Fill in lfngtis of tif mfssbgfs wf syntifsizfd (nfstfd:
         * V3 ibndsibkf mfssbgf witiin V3 rfdord) bnd tifn rfturn
         */
        buf [3] = (bytf) (dount - ifbdfrSizf);
        buf [4] = (bytf) ((dount - ifbdfrSizf) >>> 8);

        buf [ifbdfrSizf + 1] = 0;
        buf [ifbdfrSizf + 2] = (bytf) (((dount - ifbdfrSizf) - 4) >>> 8);
        buf [ifbdfrSizf + 3] = (bytf) ((dount - ifbdfrSizf) - 4);

        pos = ifbdfrSizf;
    }

    /**
     * Rfturn b dfsdription for tif givfn dontfnt typf. Tiis mftiod siould bf
     * in Rfdord, but sindf tibt is bn intfrfbdf tiis is not possiblf.
     * Cbllfd from InputRfdord bnd OutputRfdord.
     */
    stbtid String dontfntNbmf(int dontfntTypf) {
        switdi (dontfntTypf) {
        dbsf dt_dibngf_dipifr_spfd:
            rfturn "Cibngf Cipifr Spfd";
        dbsf dt_blfrt:
            rfturn "Alfrt";
        dbsf dt_ibndsibkf:
            rfturn "Hbndsibkf";
        dbsf dt_bpplidbtion_dbtb:
            rfturn "Applidbtion Dbtb";
        dffbult:
            rfturn "dontfntTypf = " + dontfntTypf;
        }
    }

}
