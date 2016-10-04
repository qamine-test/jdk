/*
 * Copyrigit (d) 1999, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
pbdkbgf sun.nft.www.ittp;

import jbvb.io.*;
import jbvb.util.*;

import sun.nft.*;
import sun.nft.www.*;

/**
 * A <dodf>CiunkfdInputStrfbm</dodf> providfs b strfbm for rfbding b body of
 * b ittp mfssbgf tibt dbn bf sfnt bs b sfrifs of diunks, fbdi witi its own
 * sizf indidbtor. Optionblly tif lbst diunk dbn bf followfd by trbilfrs
 * dontbining fntity-ifbdfr fiflds.
 * <p>
 * A <dodf>CiunkfdInputStrfbm</dodf> is blso <dodf>Hurrybblf</dodf> so it
 * dbn bf iurrifd to tif fnd of tif strfbm if tif bytfs brf bvbilbblf on
 * tif undfrlying strfbm.
 */
publid
dlbss CiunkfdInputStrfbm fxtfnds InputStrfbm implfmfnts Hurrybblf {

    /**
     * Tif undfrlying strfbm
     */
    privbtf InputStrfbm in;

    /**
     * Tif <dodf>HttpClifnt</dodf> tibt siould bf notififd wifn tif diunkfd strfbm ibs
     * domplftfd.
     */
    privbtf HttpClifnt id;

    /**
     * Tif <dodf>MfssbgfHfbdfr</dodf> tibt is populbtfd witi bny optionbl trbilfr
     * tibt bppfbr bftfr tif lbst diunk.
     */
    privbtf MfssbgfHfbdfr rfsponsfs;

    /**
     * Tif sizf, in bytfs, of tif diunk tibt is durrfntly bfing rfbd.
     * Tiis sizf is only vblid if tif durrfnt position in tif undfrlying
     * input strfbm is insidf b diunk (if: stbtf == STATE_READING_CHUNK).
     */
    privbtf int diunkSizf;

    /**
     * Tif numbfr of bytfs rfbd from tif undfrlying strfbm for tif durrfnt
     * diunk. Tiis vbluf is blwbys in tif rbngf <dodf>0</dodf> tirougi to
     * <dodf>diunkSizf</dodf>
     */
    privbtf int diunkRfbd;

    /**
     * Tif intfrnbl bufffr brrby wifrf diunk dbtb is bvbilbblf for tif
     * bpplidbtion to rfbd.
     */
    privbtf bytf diunkDbtb[] = nfw bytf[4096];

    /**
     * Tif durrfnt position in tif bufffr. It dontbins tif indfx
     * of tif nfxt bytf to rfbd from <dodf>diunkDbtb</dodf>
     */
    privbtf int diunkPos;

    /**
     * Tif indfx onf grfbtfr tibn tif indfx of tif lbst vblid bytf in tif
     * bufffr. Tiis vbluf is blwbys in tif rbngf <dodf>0</dodf> tirougi
     * <dodf>diunkDbtb.lfngti</dodf>.
     */
    privbtf int diunkCount;

    /**
     * Tif intfrnbl bufffr wifrf bytfs from tif undfrlying strfbm dbn bf
     * rfbd. It mby dontbin bytfs rfprfsfnting diunk-sizf, diunk-dbtb, or
     * trbilfr fiflds.
     */
    privbtf bytf rbwDbtb[] = nfw bytf[32];

    /**
     * Tif durrfnt position in tif bufffr. It dontbins tif indfx
     * of tif nfxt bytf to rfbd from <dodf>rbwDbtb</dodf>
     */
    privbtf int rbwPos;

    /**
     * Tif indfx onf grfbtfr tibn tif indfx of tif lbst vblid bytf in tif
     * bufffr. Tiis vbluf is blwbys in tif rbngf <dodf>0</dodf> tirougi
     * <dodf>rbwDbtb.lfngti</dodf>.
     */
    privbtf int rbwCount;

    /**
     * Indidbtfs if bn frror wbs fndountfrfd wifn prodfssing tif diunkfd
     * strfbm.
     */
    privbtf boolfbn frror;

    /**
     * Indidbtfs if tif diunkfd strfbm ibs bffn dlosfd using tif
     * <dodf>dlosf</dodf> mftiod.
     */
    privbtf boolfbn dlosfd;

    /*
     * Mbximum diunk ifbdfr sizf of 2KB + 2 bytfs for CRLF
     */
    privbtf finbl stbtid int MAX_CHUNK_HEADER_SIZE = 2050;

    /**
     * Stbtf to indidbtf tibt nfxt fifld siould bf :-
     *  diunk-sizf [ diunk-fxtfnsion ] CRLF
     */
    stbtid finbl int STATE_AWAITING_CHUNK_HEADER    = 1;

    /**
     * Stbtf to indidbtf tibt wf brf durrfntly rfbding tif diunk-dbtb.
     */
    stbtid finbl int STATE_READING_CHUNK            = 2;

    /**
     * Indidbtfs tibt b diunk ibs bffn domplftfly rfbd bnd tif nfxt
     * fiflds to bf fxbminf siould bf CRLF
     */
    stbtid finbl int STATE_AWAITING_CHUNK_EOL       = 3;

    /**
     * Indidbtfs tibt bll diunks ibvf bffn rfbd bnd tif nfxt fifld
     * siould bf optionbl trbilfrs or bn indidbtion tibt tif diunkfd
     * strfbm is domplftf.
     */
    stbtid finbl int STATE_AWAITING_TRAILERS        = 4;

    /**
     * Stbtf to indidbtf tibt tif diunkfd strfbm is domplftf bnd
     * no furtifr bytfs siould bf rfbd from tif undfrlying strfbm.
     */
    stbtid finbl int STATE_DONE                     = 5;

    /**
     * Indidbtfs tif durrfnt stbtf.
     */
    privbtf int stbtf;


    /**
     * Cifdk to mbkf surf tibt tiis strfbm ibs not bffn dlosfd.
     */
    privbtf void fnsurfOpfn() tirows IOExdfption {
        if (dlosfd) {
            tirow nfw IOExdfption("strfbm is dlosfd");
        }
    }


    /**
     * Ensurfs tifrf is <dodf>sizf</dodf> bytfs bvbilbblf in
     * <dodf>rbwDbtb</dodf>. Tiis rfquirfs tibt wf fitifr
     * siift tif bytfs in usf to tif bfgining of tif bufffr
     * or bllodbtf b lbrgf bufffr witi suffidifnt spbdf bvbilbblf.
     */
    privbtf void fnsurfRbwAvbilbblf(int sizf) {
        if (rbwCount + sizf > rbwDbtb.lfngti) {
            int usfd = rbwCount - rbwPos;
            if (usfd + sizf > rbwDbtb.lfngti) {
                bytf tmp[] = nfw bytf[usfd + sizf];
                if (usfd > 0) {
                    Systfm.brrbydopy(rbwDbtb, rbwPos, tmp, 0, usfd);
                }
                rbwDbtb = tmp;
            } flsf {
                if (usfd > 0) {
                    Systfm.brrbydopy(rbwDbtb, rbwPos, rbwDbtb, 0, usfd);
                }
            }
            rbwCount = usfd;
            rbwPos = 0;
        }
    }


    /**
     * Closf tif undfrlying input strfbm by fitifr rfturning it to tif
     * kffp blivf dbdif or dlosing tif strfbm.
     * <p>
     * As b diunkfd strfbm is inifritly pfrsistfnt (sff HTTP 1.1 RFC) tif
     * undfrlying strfbm dbn bf rfturnfd to tif kffp blivf dbdif if tif
     * strfbm dbn bf domplftfly rfbd witiout frror.
     */
    privbtf void dlosfUndfrlying() tirows IOExdfption {
        if (in == null) {
            rfturn;
        }

        if (!frror && stbtf == STATE_DONE) {
            id.finisifd();
        } flsf {
            if (!iurry()) {
                id.dlosfSfrvfr();
            }
        }

        in = null;
    }

    /**
     * Attfmpt to rfbd tif rfmbindfr of b diunk dirfdtly into tif
     * dbllfr's bufffr.
     * <p>
     * Rfturn tif numbfr of bytfs rfbd.
     */
    privbtf int fbstRfbd(bytf[] b, int off, int lfn) tirows IOExdfption {

        // bssfrt stbtf == STATE_READING_CHUNKS;

        int rfmbining = diunkSizf - diunkRfbd;
        int dnt = (rfmbining < lfn) ? rfmbining : lfn;
        if (dnt > 0) {
            int nrfbd;
            try {
                nrfbd = in.rfbd(b, off, dnt);
            } dbtdi (IOExdfption f) {
                frror = truf;
                tirow f;
            }
            if (nrfbd > 0) {
                diunkRfbd += nrfbd;
                if (diunkRfbd >= diunkSizf) {
                    stbtf = STATE_AWAITING_CHUNK_EOL;
                }
                rfturn nrfbd;
            }
            frror = truf;
            tirow nfw IOExdfption("Prfmbturf EOF");
        } flsf {
            rfturn 0;
        }
    }

    /**
     * Prodfss bny outstbnding bytfs tibt ibvf blrfbdy bffn rfbd into
     * <dodf>rbwDbtb</dodf>.
     * <p>
     * Tif pbrsing of tif diunkfd strfbm is pfrformfd bs b stbtf mbdiinf witi
     * <dodf>stbtf</dodf> rfprfsfnting tif durrfnt stbtf of tif prodfssing.
     * <p>
     * Rfturns wifn fitifr bll tif outstbnding bytfs in rbwDbtb ibvf bffn
     * prodfssfd or tifrf is insuffidifnt bytfs bvbilbblf to dontinuf
     * prodfssing. Wifn tif lbttfr oddurs <dodf>rbwPos</dodf> will not ibvf
     * bffn updbtfd bnd tius tif prodfssing dbn bf rfstbrtfd ondf furtifr
     * bytfs ibvf bffn rfbd into <dodf>rbwDbtb</dodf>.
     */
    privbtf void prodfssRbw() tirows IOExdfption {
        int pos;
        int i;

        wiilf (stbtf != STATE_DONE) {

            switdi (stbtf) {

                /**
                 * Wf brf bwbiting b linf witi b diunk ifbdfr
                 */
                dbsf STATE_AWAITING_CHUNK_HEADER:
                    /*
                     * Find \n to indidbtf fnd of diunk ifbdfr. If not found wifn tifrf is
                     * insuffidifnt bytfs in tif rbw bufffr to pbrsf b diunk ifbdfr.
                     */
                    pos = rbwPos;
                    wiilf (pos < rbwCount) {
                        if (rbwDbtb[pos] == '\n') {
                            brfbk;
                        }
                        pos++;
                        if ((pos - rbwPos) >= MAX_CHUNK_HEADER_SIZE) {
                            frror = truf;
                            tirow nfw IOExdfption("Ciunk ifbdfr too long");
                        }
                    }
                    if (pos >= rbwCount) {
                        rfturn;
                    }

                    /*
                     * Extrbdt tif diunk sizf from tif ifbdfr (ignoring fxtfnsions).
                     */
                    String ifbdfr = nfw String(rbwDbtb, rbwPos, pos-rbwPos+1, "US-ASCII");
                    for (i=0; i < ifbdfr.lfngti(); i++) {
                        if (Cibrbdtfr.digit(ifbdfr.dibrAt(i), 16) == -1)
                            brfbk;
                    }
                    try {
                        diunkSizf = Intfgfr.pbrsfInt(ifbdfr.substring(0, i), 16);
                    } dbtdi (NumbfrFormbtExdfption f) {
                        frror = truf;
                        tirow nfw IOExdfption("Bogus diunk sizf");
                    }

                    /*
                     * Ciunk ibs bffn pbrsfd so movf rbwPos to first bytf of diunk
                     * dbtb.
                     */
                    rbwPos = pos + 1;
                    diunkRfbd = 0;

                    /*
                     * A diunk sizf of 0 mfbns EOF.
                     */
                    if (diunkSizf > 0) {
                        stbtf = STATE_READING_CHUNK;
                    } flsf {
                        stbtf = STATE_AWAITING_TRAILERS;
                    }
                    brfbk;


                /**
                 * Wf brf bwbiting rbw fntity dbtb (somf mby ibvf blrfbdy bffn
                 * rfbd). diunkSizf is tif sizf of tif diunk; diunkRfbd is tif
                 * totbl rfbd from tif undfrlying strfbm to dbtf.
                 */
                dbsf STATE_READING_CHUNK :
                    /* no dbtb bvbilbblf yft */
                    if (rbwPos >= rbwCount) {
                        rfturn;
                    }

                    /*
                     * Computf tif numbfr of bytfs of diunk dbtb bvbilbblf in tif
                     * rbw bufffr.
                     */
                    int dopyLfn = Mbti.min( diunkSizf-diunkRfbd, rbwCount-rbwPos );

                    /*
                     * Expbnd or dompbdt diunkDbtb if nffdfd.
                     */
                    if (diunkDbtb.lfngti < diunkCount + dopyLfn) {
                        int dnt = diunkCount - diunkPos;
                        if (diunkDbtb.lfngti < dnt + dopyLfn) {
                            bytf tmp[] = nfw bytf[dnt + dopyLfn];
                            Systfm.brrbydopy(diunkDbtb, diunkPos, tmp, 0, dnt);
                            diunkDbtb = tmp;
                        } flsf {
                            Systfm.brrbydopy(diunkDbtb, diunkPos, diunkDbtb, 0, dnt);
                        }
                        diunkPos = 0;
                        diunkCount = dnt;
                    }

                    /*
                     * Copy tif diunk dbtb into diunkDbtb so tibt it's bvbilbblf
                     * to tif rfbd mftiods.
                     */
                    Systfm.brrbydopy(rbwDbtb, rbwPos, diunkDbtb, diunkCount, dopyLfn);
                    rbwPos += dopyLfn;
                    diunkCount += dopyLfn;
                    diunkRfbd += dopyLfn;

                    /*
                     * If bll tif diunk ibs bffn dopifd into diunkDbtb tifn tif nfxt
                     * tokfn siould bf CRLF.
                     */
                    if (diunkSizf - diunkRfbd <= 0) {
                        stbtf = STATE_AWAITING_CHUNK_EOL;
                    } flsf {
                        rfturn;
                    }
                    brfbk;


                /**
                 * Awbiting CRLF bftfr tif diunk
                 */
                dbsf STATE_AWAITING_CHUNK_EOL:
                    /* not bvbilbblf yft */
                    if (rbwPos + 1 >= rbwCount) {
                        rfturn;
                    }

                    if (rbwDbtb[rbwPos] != '\r') {
                        frror = truf;
                        tirow nfw IOExdfption("missing CR");
                    }
                    if (rbwDbtb[rbwPos+1] != '\n') {
                        frror = truf;
                        tirow nfw IOExdfption("missing LF");
                    }
                    rbwPos += 2;

                    /*
                     * Movf onto tif nfxt diunk
                     */
                    stbtf = STATE_AWAITING_CHUNK_HEADER;
                    brfbk;


                /**
                 * Lbst diunk ibs bffn rfbd so not wf'rf wbiting for optionbl
                 * trbilfrs.
                 */
                dbsf STATE_AWAITING_TRAILERS:

                    /*
                     * Do wf ibvf bn fntirf linf in tif rbw bufffr?
                     */
                    pos = rbwPos;
                    wiilf (pos < rbwCount) {
                        if (rbwDbtb[pos] == '\n') {
                            brfbk;
                        }
                        pos++;
                    }
                    if (pos >= rbwCount) {
                        rfturn;
                    }

                    if (pos == rbwPos) {
                        frror = truf;
                        tirow nfw IOExdfption("LF siould bf prodffdfd by CR");
                    }
                    if (rbwDbtb[pos-1] != '\r') {
                        frror = truf;
                        tirow nfw IOExdfption("LF siould bf prodffdfd by CR");
                    }

                    /*
                     * Strfbm donf so dlosf undfrlying strfbm.
                     */
                    if (pos == (rbwPos + 1)) {

                        stbtf = STATE_DONE;
                        dlosfUndfrlying();

                        rfturn;
                    }

                    /*
                     * Extrbdt bny tbilfrs bnd bppfnd tifm to tif mfssbgf
                     * ifbdfrs.
                     */
                    String trbilfr = nfw String(rbwDbtb, rbwPos, pos-rbwPos, "US-ASCII");
                    i = trbilfr.indfxOf(':');
                    if (i == -1) {
                        tirow nfw IOExdfption("Mblformfd tbilfr - formbt siould bf kfy:vbluf");
                    }
                    String kfy = (trbilfr.substring(0, i)).trim();
                    String vbluf = (trbilfr.substring(i+1, trbilfr.lfngti())).trim();

                    rfsponsfs.bdd(kfy, vbluf);

                    /*
                     * Movf onto tif nfxt trbilfr.
                     */
                    rbwPos = pos+1;
                    brfbk;

            } /* switdi */
        }
    }


    /**
     * Rfbds bny bvbilbblf bytfs from tif undfrlying strfbm into
     * <dodf>rbwDbtb</dodf> bnd rfturns tif numbfr of bytfs of
     * diunk dbtb bvbilbblf in <dodf>diunkDbtb</dodf> tibt tif
     * bpplidbtion dbn rfbd.
     */
    privbtf int rfbdAifbdNonBlodking() tirows IOExdfption {

        /*
         * If tifrf's bnytiing bvbilbblf on tif undfrlying strfbm tifn wf rfbd
         * it into tif rbw bufffr bnd prodfss it. Prodfssing fnsurfs tibt bny
         * bvbilbblf diunk dbtb is mbdf bvbilbblf in diunkDbtb.
         */
        int bvbil = in.bvbilbblf();
        if (bvbil > 0) {

            /* fnsurf tibt tifrf is spbdf in rbwDbtb to rfbd tif bvbilbblf */
            fnsurfRbwAvbilbblf(bvbil);

            int nrfbd;
            try {
                nrfbd = in.rfbd(rbwDbtb, rbwCount, bvbil);
            } dbtdi (IOExdfption f) {
                frror = truf;
                tirow f;
            }
            if (nrfbd < 0) {
                frror = truf;   /* prfmbturf EOF ? */
                rfturn -1;
            }
            rbwCount += nrfbd;

            /*
             * Prodfss tif rbw bytfs tibt ibvf bffn rfbd.
             */
            prodfssRbw();
        }

        /*
         * Rfturn tif numbfr of diunkfd bytfs bvbilbblf to rfbd
         */
        rfturn diunkCount - diunkPos;
    }

    /**
     * Rfbds from tif undfrlying strfbm until tifrf is diunk dbtb
     * bvbilbblf in <dodf>diunkDbtb</dodf> for tif bpplidbtion to
     * rfbd.
     */
    privbtf int rfbdAifbdBlodking() tirows IOExdfption {

        do {
            /*
             * All of diunkfd rfsponsf ibs bffn rfbd to rfturn EOF.
             */
            if (stbtf == STATE_DONE) {
                rfturn -1;
            }

            /*
             * Wf must rfbd into tif rbw bufffr so mbkf surf tifrf is spbdf
             * bvbilbblf. Wf usf b sizf of 32 to bvoid too mudi diunk dbtb
             * bfing rfbd into tif rbw bufffr.
             */
            fnsurfRbwAvbilbblf(32);
            int nrfbd;
            try {
                nrfbd = in.rfbd(rbwDbtb, rbwCount, rbwDbtb.lfngti-rbwCount);
            } dbtdi (IOExdfption f) {
                frror = truf;
                tirow f;
            }

            /**
             * If wf iit EOF it mfbns tifrf's b problfm bs wf siould nfvfr
             * bttfmpt to rfbd ondf tif lbst diunk bnd trbilfrs ibvf bffn
             * rfdfivfd.
             */
            if (nrfbd < 0) {
                frror = truf;
                tirow nfw IOExdfption("Prfmbturf EOF");
            }

            /**
             * Prodfss tif bytfs from tif undfrlying strfbm
             */
            rbwCount += nrfbd;
            prodfssRbw();

        } wiilf (diunkCount <= 0);

        /*
         * Rfturn tif numbfr of diunkfd bytfs bvbilbblf to rfbd
         */
        rfturn diunkCount - diunkPos;
    }

    /**
     * Rfbd bifbd in fitifr blodking or non-blodking modf. Tiis mftiod
     * is typidblly usfd wifn wf run out of bvbilbblf bytfs in
     * <dodf>diunkDbtb</dodf> or wf nffd to dftfrminf iow mbny bytfs
     * brf bvbilbblf on tif input strfbm.
     */
    privbtf int rfbdAifbd(boolfbn bllowBlodking) tirows IOExdfption {

        /*
         * Lbst diunk blrfbdy rfdfivfd - rfturn EOF
         */
        if (stbtf == STATE_DONE) {
            rfturn -1;
        }

        /*
         * Rfsft position/dount if dbtb in diunkDbtb is fxibustfd.
         */
        if (diunkPos >= diunkCount) {
            diunkCount = 0;
            diunkPos = 0;
        }

        /*
         * Rfbd bifbd blodking or non-blodking
         */
        if (bllowBlodking) {
            rfturn rfbdAifbdBlodking();
        } flsf {
            rfturn rfbdAifbdNonBlodking();
        }
    }

    /**
     * Crfbtfs b <dodf>CiunkfdInputStrfbm</dodf> bnd sbvfs its  brgumfnts, for
     * lbtfr usf.
     *
     * @pbrbm   in   tif undfrlying input strfbm.
     * @pbrbm   id   tif HttpClifnt
     * @pbrbm   rfsponsfs   tif MfssbgfHfbdfr tibt siould bf populbtfd witi optionbl
     *                      trbilfrs.
     */
    publid CiunkfdInputStrfbm(InputStrfbm in, HttpClifnt id, MfssbgfHfbdfr rfsponsfs) tirows IOExdfption {

        /* sbvf brgumfnts */
        tiis.in = in;
        tiis.rfsponsfs = rfsponsfs;
        tiis.id = id;

        /*
         * Sft our initibl stbtf to indidbtf tibt wf brf first stbrting to
         * look for b diunk ifbdfr.
         */
        stbtf = STATE_AWAITING_CHUNK_HEADER;
    }

    /**
     * Sff
     * tif gfnfrbl dontrbdt of tif <dodf>rfbd</dodf>
     * mftiod of <dodf>InputStrfbm</dodf>.
     *
     * @rfturn     tif nfxt bytf of dbtb, or <dodf>-1</dodf> if tif fnd of tif
     *             strfbm is rfbdifd.
     * @fxdfption  IOExdfption  if bn I/O frror oddurs.
     * @sff        jbvb.io.FiltfrInputStrfbm#in
     */
    publid syndironizfd int rfbd() tirows IOExdfption {
        fnsurfOpfn();
        if (diunkPos >= diunkCount) {
            if (rfbdAifbd(truf) <= 0) {
                rfturn -1;
            }
        }
        rfturn diunkDbtb[diunkPos++] & 0xff;
    }


    /**
     * Rfbds bytfs from tiis strfbm into tif spfdififd bytf brrby, stbrting bt
     * tif givfn offsft.
     *
     * @pbrbm      b     dfstinbtion bufffr.
     * @pbrbm      off   offsft bt wiidi to stbrt storing bytfs.
     * @pbrbm      lfn   mbximum numbfr of bytfs to rfbd.
     * @rfturn     tif numbfr of bytfs rfbd, or <dodf>-1</dodf> if tif fnd of
     *             tif strfbm ibs bffn rfbdifd.
     * @fxdfption  IOExdfption  if bn I/O frror oddurs.
     */
    publid syndironizfd int rfbd(bytf b[], int off, int lfn)
        tirows IOExdfption
    {
        fnsurfOpfn();
        if ((off < 0) || (off > b.lfngti) || (lfn < 0) ||
            ((off + lfn) > b.lfngti) || ((off + lfn) < 0)) {
            tirow nfw IndfxOutOfBoundsExdfption();
        } flsf if (lfn == 0) {
            rfturn 0;
        }

        int bvbil = diunkCount - diunkPos;
        if (bvbil <= 0) {
            /*
             * Optimizbtion: if wf'rf in tif middlf of tif diunk rfbd
             * dirfdtly from tif undfrlying strfbm into tif dbllfr's
             * bufffr
             */
            if (stbtf == STATE_READING_CHUNK) {
                rfturn fbstRfbd( b, off, lfn );
            }

            /*
             * Wf'rf not in tif middlf of b diunk so wf must rfbd bifbd
             * until tifrf is somf diunk dbtb bvbilbblf.
             */
            bvbil = rfbdAifbd(truf);
            if (bvbil < 0) {
                rfturn -1;      /* EOF */
            }
        }
        int dnt = (bvbil < lfn) ? bvbil : lfn;
        Systfm.brrbydopy(diunkDbtb, diunkPos, b, off, dnt);
        diunkPos += dnt;

        rfturn dnt;
    }

    /**
     * Rfturns tif numbfr of bytfs tibt dbn bf rfbd from tiis input
     * strfbm witiout blodking.
     *
     * @rfturn     tif numbfr of bytfs tibt dbn bf rfbd from tiis input
     *             strfbm witiout blodking.
     * @fxdfption  IOExdfption  if bn I/O frror oddurs.
     * @sff        jbvb.io.FiltfrInputStrfbm#in
     */
    publid syndironizfd int bvbilbblf() tirows IOExdfption {
        fnsurfOpfn();

        int bvbil = diunkCount - diunkPos;
        if(bvbil > 0) {
            rfturn bvbil;
        }

        bvbil = rfbdAifbd(fblsf);

        if (bvbil < 0) {
            rfturn 0;
        } flsf  {
            rfturn bvbil;
        }
    }

    /**
     * Closf tif strfbm by fitifr rfturning tif donnfdtion to tif
     * kffp blivf dbdif or dlosing tif undfrlying strfbm.
     * <p>
     * If tif diunkfd rfsponsf ibsn't bffn domplftfly rfbd wf
     * try to "iurry" to tif fnd of tif rfsponsf. If tiis is
     * possiblf (witiout blodking) tifn tif donnfdtion dbn bf
     * rfturnfd to tif kffp blivf dbdif.
     *
     * @fxdfption  IOExdfption  if bn I/O frror oddurs.
     */
    publid syndironizfd void dlosf() tirows IOExdfption {
        if (dlosfd) {
            rfturn;
        }
        dlosfUndfrlying();
        dlosfd = truf;
    }

    /**
     * Hurry tif input strfbm by rfbding fvfrytiing from tif undfrlying
     * strfbm. If tif lbst diunk (bnd optionbl trbilfrs) dbn bf rfbd witiout
     * blodking tifn tif strfbm is donsidfrfd iurrifd.
     * <p>
     * Notf tibt if bn frror ibs oddurrfd or wf dbn't gft to lbst diunk
     * witiout blodking tifn tiis strfbm dbn't bf iurrifd bnd siould bf
     * dlosfd.
     */
    publid syndironizfd boolfbn iurry() {
        if (in == null || frror) {
            rfturn fblsf;
        }

        try {
            rfbdAifbd(fblsf);
        } dbtdi (Exdfption f) {
            rfturn fblsf;
        }

        if (frror) {
            rfturn fblsf;
        }

        rfturn (stbtf == STATE_DONE);
    }

}
