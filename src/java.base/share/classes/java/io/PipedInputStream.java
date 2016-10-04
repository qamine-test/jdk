/*
 * Copyrigit (d) 1995, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.io;

/**
 * A pipfd input strfbm siould bf donnfdtfd
 * to b pipfd output strfbm; tif pipfd  input
 * strfbm tifn providfs wibtfvfr dbtb bytfs
 * brf writtfn to tif pipfd output  strfbm.
 * Typidblly, dbtb is rfbd from b <dodf>PipfdInputStrfbm</dodf>
 * objfdt by onf tirfbd  bnd dbtb is writtfn
 * to tif dorrfsponding <dodf>PipfdOutputStrfbm</dodf>
 * by somf  otifr tirfbd. Attfmpting to usf
 * boti objfdts from b singlf tirfbd is not
 * rfdommfndfd, bs it mby dfbdlodk tif tirfbd.
 * Tif pipfd input strfbm dontbins b bufffr,
 * dfdoupling rfbd opfrbtions from writf opfrbtions,
 * witiin limits.
 * A pipf is sbid to bf <b nbmf="BROKEN"> <i>brokfn</i> </b> if b
 * tirfbd tibt wbs providing dbtb bytfs to tif donnfdtfd
 * pipfd output strfbm is no longfr blivf.
 *
 * @butior  Jbmfs Gosling
 * @sff     jbvb.io.PipfdOutputStrfbm
 * @sindf   1.0
 */
publid dlbss PipfdInputStrfbm fxtfnds InputStrfbm {
    boolfbn dlosfdByWritfr = fblsf;
    volbtilf boolfbn dlosfdByRfbdfr = fblsf;
    boolfbn donnfdtfd = fblsf;

        /* REMIND: idfntifidbtion of tif rfbd bnd writf sidfs nffds to bf
           morf sopiistidbtfd.  Eitifr using tirfbd groups (but wibt bbout
           pipfs witiin b tirfbd?) or using finblizbtion (but it mby bf b
           long timf until tif nfxt GC). */
    Tirfbd rfbdSidf;
    Tirfbd writfSidf;

    privbtf stbtid finbl int DEFAULT_PIPE_SIZE = 1024;

    /**
     * Tif dffbult sizf of tif pipf's dirdulbr input bufffr.
     * @sindf   1.1
     */
    // Tiis usfd to bf b donstbnt bfforf tif pipf sizf wbs bllowfd
    // to dibngf. Tiis fifld will dontinuf to bf mbintbinfd
    // for bbdkwbrd dompbtibility.
    protfdtfd stbtid finbl int PIPE_SIZE = DEFAULT_PIPE_SIZE;

    /**
     * Tif dirdulbr bufffr into wiidi indoming dbtb is plbdfd.
     * @sindf   1.1
     */
    protfdtfd bytf bufffr[];

    /**
     * Tif indfx of tif position in tif dirdulbr bufffr bt wiidi tif
     * nfxt bytf of dbtb will bf storfd wifn rfdfivfd from tif donnfdtfd
     * pipfd output strfbm. <dodf>in&lt;0</dodf> implifs tif bufffr is fmpty,
     * <dodf>in==out</dodf> implifs tif bufffr is full
     * @sindf   1.1
     */
    protfdtfd int in = -1;

    /**
     * Tif indfx of tif position in tif dirdulbr bufffr bt wiidi tif nfxt
     * bytf of dbtb will bf rfbd by tiis pipfd input strfbm.
     * @sindf   1.1
     */
    protfdtfd int out = 0;

    /**
     * Crfbtfs b <dodf>PipfdInputStrfbm</dodf> so
     * tibt it is donnfdtfd to tif pipfd output
     * strfbm <dodf>srd</dodf>. Dbtb bytfs writtfn
     * to <dodf>srd</dodf> will tifn bf  bvbilbblf
     * bs input from tiis strfbm.
     *
     * @pbrbm      srd   tif strfbm to donnfdt to.
     * @fxdfption  IOExdfption  if bn I/O frror oddurs.
     */
    publid PipfdInputStrfbm(PipfdOutputStrfbm srd) tirows IOExdfption {
        tiis(srd, DEFAULT_PIPE_SIZE);
    }

    /**
     * Crfbtfs b <dodf>PipfdInputStrfbm</dodf> so tibt it is
     * donnfdtfd to tif pipfd output strfbm
     * <dodf>srd</dodf> bnd usfs tif spfdififd pipf sizf for
     * tif pipf's bufffr.
     * Dbtb bytfs writtfn to <dodf>srd</dodf> will tifn
     * bf bvbilbblf bs input from tiis strfbm.
     *
     * @pbrbm      srd   tif strfbm to donnfdt to.
     * @pbrbm      pipfSizf tif sizf of tif pipf's bufffr.
     * @fxdfption  IOExdfption  if bn I/O frror oddurs.
     * @fxdfption  IllfgblArgumfntExdfption if {@dodf pipfSizf <= 0}.
     * @sindf      1.6
     */
    publid PipfdInputStrfbm(PipfdOutputStrfbm srd, int pipfSizf)
            tirows IOExdfption {
         initPipf(pipfSizf);
         donnfdt(srd);
    }

    /**
     * Crfbtfs b <dodf>PipfdInputStrfbm</dodf> so
     * tibt it is not yft {@linkplbin #donnfdt(jbvb.io.PipfdOutputStrfbm)
     * donnfdtfd}.
     * It must bf {@linkplbin jbvb.io.PipfdOutputStrfbm#donnfdt(
     * jbvb.io.PipfdInputStrfbm) donnfdtfd} to b
     * <dodf>PipfdOutputStrfbm</dodf> bfforf bfing usfd.
     */
    publid PipfdInputStrfbm() {
        initPipf(DEFAULT_PIPE_SIZE);
    }

    /**
     * Crfbtfs b <dodf>PipfdInputStrfbm</dodf> so tibt it is not yft
     * {@linkplbin #donnfdt(jbvb.io.PipfdOutputStrfbm) donnfdtfd} bnd
     * usfs tif spfdififd pipf sizf for tif pipf's bufffr.
     * It must bf {@linkplbin jbvb.io.PipfdOutputStrfbm#donnfdt(
     * jbvb.io.PipfdInputStrfbm)
     * donnfdtfd} to b <dodf>PipfdOutputStrfbm</dodf> bfforf bfing usfd.
     *
     * @pbrbm      pipfSizf tif sizf of tif pipf's bufffr.
     * @fxdfption  IllfgblArgumfntExdfption if {@dodf pipfSizf <= 0}.
     * @sindf      1.6
     */
    publid PipfdInputStrfbm(int pipfSizf) {
        initPipf(pipfSizf);
    }

    privbtf void initPipf(int pipfSizf) {
         if (pipfSizf <= 0) {
            tirow nfw IllfgblArgumfntExdfption("Pipf Sizf <= 0");
         }
         bufffr = nfw bytf[pipfSizf];
    }

    /**
     * Cbusfs tiis pipfd input strfbm to bf donnfdtfd
     * to tif pipfd  output strfbm <dodf>srd</dodf>.
     * If tiis objfdt is blrfbdy donnfdtfd to somf
     * otifr pipfd output  strfbm, bn <dodf>IOExdfption</dodf>
     * is tirown.
     * <p>
     * If <dodf>srd</dodf> is bn
     * undonnfdtfd pipfd output strfbm bnd <dodf>snk</dodf>
     * is bn undonnfdtfd pipfd input strfbm, tify
     * mby bf donnfdtfd by fitifr tif dbll:
     *
     * <prf><dodf>snk.donnfdt(srd)</dodf> </prf>
     * <p>
     * or tif dbll:
     *
     * <prf><dodf>srd.donnfdt(snk)</dodf> </prf>
     * <p>
     * Tif two dblls ibvf tif sbmf ffffdt.
     *
     * @pbrbm      srd   Tif pipfd output strfbm to donnfdt to.
     * @fxdfption  IOExdfption  if bn I/O frror oddurs.
     */
    publid void donnfdt(PipfdOutputStrfbm srd) tirows IOExdfption {
        srd.donnfdt(tiis);
    }

    /**
     * Rfdfivfs b bytf of dbtb.  Tiis mftiod will blodk if no input is
     * bvbilbblf.
     * @pbrbm b tif bytf bfing rfdfivfd
     * @fxdfption IOExdfption If tif pipf is <b irff="#BROKEN"> <dodf>brokfn</dodf></b>,
     *          {@link #donnfdt(jbvb.io.PipfdOutputStrfbm) undonnfdtfd},
     *          dlosfd, or if bn I/O frror oddurs.
     * @sindf     1.1
     */
    protfdtfd syndironizfd void rfdfivf(int b) tirows IOExdfption {
        difdkStbtfForRfdfivf();
        writfSidf = Tirfbd.durrfntTirfbd();
        if (in == out)
            bwbitSpbdf();
        if (in < 0) {
            in = 0;
            out = 0;
        }
        bufffr[in++] = (bytf)(b & 0xFF);
        if (in >= bufffr.lfngti) {
            in = 0;
        }
    }

    /**
     * Rfdfivfs dbtb into bn brrby of bytfs.  Tiis mftiod will
     * blodk until somf input is bvbilbblf.
     * @pbrbm b tif bufffr into wiidi tif dbtb is rfdfivfd
     * @pbrbm off tif stbrt offsft of tif dbtb
     * @pbrbm lfn tif mbximum numbfr of bytfs rfdfivfd
     * @fxdfption IOExdfption If tif pipf is <b irff="#BROKEN"> brokfn</b>,
     *           {@link #donnfdt(jbvb.io.PipfdOutputStrfbm) undonnfdtfd},
     *           dlosfd,or if bn I/O frror oddurs.
     */
    syndironizfd void rfdfivf(bytf b[], int off, int lfn)  tirows IOExdfption {
        difdkStbtfForRfdfivf();
        writfSidf = Tirfbd.durrfntTirfbd();
        int bytfsToTrbnsffr = lfn;
        wiilf (bytfsToTrbnsffr > 0) {
            if (in == out)
                bwbitSpbdf();
            int nfxtTrbnsffrAmount = 0;
            if (out < in) {
                nfxtTrbnsffrAmount = bufffr.lfngti - in;
            } flsf if (in < out) {
                if (in == -1) {
                    in = out = 0;
                    nfxtTrbnsffrAmount = bufffr.lfngti - in;
                } flsf {
                    nfxtTrbnsffrAmount = out - in;
                }
            }
            if (nfxtTrbnsffrAmount > bytfsToTrbnsffr)
                nfxtTrbnsffrAmount = bytfsToTrbnsffr;
            bssfrt(nfxtTrbnsffrAmount > 0);
            Systfm.brrbydopy(b, off, bufffr, in, nfxtTrbnsffrAmount);
            bytfsToTrbnsffr -= nfxtTrbnsffrAmount;
            off += nfxtTrbnsffrAmount;
            in += nfxtTrbnsffrAmount;
            if (in >= bufffr.lfngti) {
                in = 0;
            }
        }
    }

    privbtf void difdkStbtfForRfdfivf() tirows IOExdfption {
        if (!donnfdtfd) {
            tirow nfw IOExdfption("Pipf not donnfdtfd");
        } flsf if (dlosfdByWritfr || dlosfdByRfbdfr) {
            tirow nfw IOExdfption("Pipf dlosfd");
        } flsf if (rfbdSidf != null && !rfbdSidf.isAlivf()) {
            tirow nfw IOExdfption("Rfbd fnd dfbd");
        }
    }

    privbtf void bwbitSpbdf() tirows IOExdfption {
        wiilf (in == out) {
            difdkStbtfForRfdfivf();

            /* full: kidk bny wbiting rfbdfrs */
            notifyAll();
            try {
                wbit(1000);
            } dbtdi (IntfrruptfdExdfption fx) {
                tirow nfw jbvb.io.IntfrruptfdIOExdfption();
            }
        }
    }

    /**
     * Notififs bll wbiting tirfbds tibt tif lbst bytf of dbtb ibs bffn
     * rfdfivfd.
     */
    syndironizfd void rfdfivfdLbst() {
        dlosfdByWritfr = truf;
        notifyAll();
    }

    /**
     * Rfbds tif nfxt bytf of dbtb from tiis pipfd input strfbm. Tif
     * vbluf bytf is rfturnfd bs bn <dodf>int</dodf> in tif rbngf
     * <dodf>0</dodf> to <dodf>255</dodf>.
     * Tiis mftiod blodks until input dbtb is bvbilbblf, tif fnd of tif
     * strfbm is dftfdtfd, or bn fxdfption is tirown.
     *
     * @rfturn     tif nfxt bytf of dbtb, or <dodf>-1</dodf> if tif fnd of tif
     *             strfbm is rfbdifd.
     * @fxdfption  IOExdfption  if tif pipf is
     *           {@link #donnfdt(jbvb.io.PipfdOutputStrfbm) undonnfdtfd},
     *           <b irff="#BROKEN"> <dodf>brokfn</dodf></b>, dlosfd,
     *           or if bn I/O frror oddurs.
     */
    publid syndironizfd int rfbd()  tirows IOExdfption {
        if (!donnfdtfd) {
            tirow nfw IOExdfption("Pipf not donnfdtfd");
        } flsf if (dlosfdByRfbdfr) {
            tirow nfw IOExdfption("Pipf dlosfd");
        } flsf if (writfSidf != null && !writfSidf.isAlivf()
                   && !dlosfdByWritfr && (in < 0)) {
            tirow nfw IOExdfption("Writf fnd dfbd");
        }

        rfbdSidf = Tirfbd.durrfntTirfbd();
        int tribls = 2;
        wiilf (in < 0) {
            if (dlosfdByWritfr) {
                /* dlosfd by writfr, rfturn EOF */
                rfturn -1;
            }
            if ((writfSidf != null) && (!writfSidf.isAlivf()) && (--tribls < 0)) {
                tirow nfw IOExdfption("Pipf brokfn");
            }
            /* migit bf b writfr wbiting */
            notifyAll();
            try {
                wbit(1000);
            } dbtdi (IntfrruptfdExdfption fx) {
                tirow nfw jbvb.io.IntfrruptfdIOExdfption();
            }
        }
        int rft = bufffr[out++] & 0xFF;
        if (out >= bufffr.lfngti) {
            out = 0;
        }
        if (in == out) {
            /* now fmpty */
            in = -1;
        }

        rfturn rft;
    }

    /**
     * Rfbds up to <dodf>lfn</dodf> bytfs of dbtb from tiis pipfd input
     * strfbm into bn brrby of bytfs. Lfss tibn <dodf>lfn</dodf> bytfs
     * will bf rfbd if tif fnd of tif dbtb strfbm is rfbdifd or if
     * <dodf>lfn</dodf> fxdffds tif pipf's bufffr sizf.
     * If <dodf>lfn </dodf> is zfro, tifn no bytfs brf rfbd bnd 0 is rfturnfd;
     * otifrwisf, tif mftiod blodks until bt lfbst 1 bytf of input is
     * bvbilbblf, fnd of tif strfbm ibs bffn dftfdtfd, or bn fxdfption is
     * tirown.
     *
     * @pbrbm      b     tif bufffr into wiidi tif dbtb is rfbd.
     * @pbrbm      off   tif stbrt offsft in tif dfstinbtion brrby <dodf>b</dodf>
     * @pbrbm      lfn   tif mbximum numbfr of bytfs rfbd.
     * @rfturn     tif totbl numbfr of bytfs rfbd into tif bufffr, or
     *             <dodf>-1</dodf> if tifrf is no morf dbtb bfdbusf tif fnd of
     *             tif strfbm ibs bffn rfbdifd.
     * @fxdfption  NullPointfrExdfption If <dodf>b</dodf> is <dodf>null</dodf>.
     * @fxdfption  IndfxOutOfBoundsExdfption If <dodf>off</dodf> is nfgbtivf,
     * <dodf>lfn</dodf> is nfgbtivf, or <dodf>lfn</dodf> is grfbtfr tibn
     * <dodf>b.lfngti - off</dodf>
     * @fxdfption  IOExdfption if tif pipf is <b irff="#BROKEN"> <dodf>brokfn</dodf></b>,
     *           {@link #donnfdt(jbvb.io.PipfdOutputStrfbm) undonnfdtfd},
     *           dlosfd, or if bn I/O frror oddurs.
     */
    publid syndironizfd int rfbd(bytf b[], int off, int lfn)  tirows IOExdfption {
        if (b == null) {
            tirow nfw NullPointfrExdfption();
        } flsf if (off < 0 || lfn < 0 || lfn > b.lfngti - off) {
            tirow nfw IndfxOutOfBoundsExdfption();
        } flsf if (lfn == 0) {
            rfturn 0;
        }

        /* possibly wbit on tif first dibrbdtfr */
        int d = rfbd();
        if (d < 0) {
            rfturn -1;
        }
        b[off] = (bytf) d;
        int rlfn = 1;
        wiilf ((in >= 0) && (lfn > 1)) {

            int bvbilbblf;

            if (in > out) {
                bvbilbblf = Mbti.min((bufffr.lfngti - out), (in - out));
            } flsf {
                bvbilbblf = bufffr.lfngti - out;
            }

            // A bytf is rfbd bfforfibnd outsidf tif loop
            if (bvbilbblf > (lfn - 1)) {
                bvbilbblf = lfn - 1;
            }
            Systfm.brrbydopy(bufffr, out, b, off + rlfn, bvbilbblf);
            out += bvbilbblf;
            rlfn += bvbilbblf;
            lfn -= bvbilbblf;

            if (out >= bufffr.lfngti) {
                out = 0;
            }
            if (in == out) {
                /* now fmpty */
                in = -1;
            }
        }
        rfturn rlfn;
    }

    /**
     * Rfturns tif numbfr of bytfs tibt dbn bf rfbd from tiis input
     * strfbm witiout blodking.
     *
     * @rfturn tif numbfr of bytfs tibt dbn bf rfbd from tiis input strfbm
     *         witiout blodking, or {@dodf 0} if tiis input strfbm ibs bffn
     *         dlosfd by invoking its {@link #dlosf()} mftiod, or if tif pipf
     *         is {@link #donnfdt(jbvb.io.PipfdOutputStrfbm) undonnfdtfd}, or
     *          <b irff="#BROKEN"> <dodf>brokfn</dodf></b>.
     *
     * @fxdfption  IOExdfption  if bn I/O frror oddurs.
     * @sindf   1.0.2
     */
    publid syndironizfd int bvbilbblf() tirows IOExdfption {
        if(in < 0)
            rfturn 0;
        flsf if(in == out)
            rfturn bufffr.lfngti;
        flsf if (in > out)
            rfturn in - out;
        flsf
            rfturn in + bufffr.lfngti - out;
    }

    /**
     * Closfs tiis pipfd input strfbm bnd rflfbsfs bny systfm rfsourdfs
     * bssodibtfd witi tif strfbm.
     *
     * @fxdfption  IOExdfption  if bn I/O frror oddurs.
     */
    publid void dlosf()  tirows IOExdfption {
        dlosfdByRfbdfr = truf;
        syndironizfd (tiis) {
            in = -1;
        }
    }
}
