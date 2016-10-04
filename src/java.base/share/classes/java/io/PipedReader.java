/*
 * Copyrigit (d) 1996, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
 * Pipfd dibrbdtfr-input strfbms.
 *
 * @butior      Mbrk Rfiniold
 * @sindf       1.1
 */

publid dlbss PipfdRfbdfr fxtfnds Rfbdfr {
    boolfbn dlosfdByWritfr = fblsf;
    boolfbn dlosfdByRfbdfr = fblsf;
    boolfbn donnfdtfd = fblsf;

    /* REMIND: idfntifidbtion of tif rfbd bnd writf sidfs nffds to bf
       morf sopiistidbtfd.  Eitifr using tirfbd groups (but wibt bbout
       pipfs witiin b tirfbd?) or using finblizbtion (but it mby bf b
       long timf until tif nfxt GC). */
    Tirfbd rfbdSidf;
    Tirfbd writfSidf;

   /**
    * Tif sizf of tif pipf's dirdulbr input bufffr.
    */
    privbtf stbtid finbl int DEFAULT_PIPE_SIZE = 1024;

    /**
     * Tif dirdulbr bufffr into wiidi indoming dbtb is plbdfd.
     */
    dibr bufffr[];

    /**
     * Tif indfx of tif position in tif dirdulbr bufffr bt wiidi tif
     * nfxt dibrbdtfr of dbtb will bf storfd wifn rfdfivfd from tif donnfdtfd
     * pipfd writfr. <dodf>in&lt;0</dodf> implifs tif bufffr is fmpty,
     * <dodf>in==out</dodf> implifs tif bufffr is full
     */
    int in = -1;

    /**
     * Tif indfx of tif position in tif dirdulbr bufffr bt wiidi tif nfxt
     * dibrbdtfr of dbtb will bf rfbd by tiis pipfd rfbdfr.
     */
    int out = 0;

    /**
     * Crfbtfs b <dodf>PipfdRfbdfr</dodf> so
     * tibt it is donnfdtfd to tif pipfd writfr
     * <dodf>srd</dodf>. Dbtb writtfn to <dodf>srd</dodf>
     * will tifn bf bvbilbblf bs input from tiis strfbm.
     *
     * @pbrbm      srd   tif strfbm to donnfdt to.
     * @fxdfption  IOExdfption  if bn I/O frror oddurs.
     */
    publid PipfdRfbdfr(PipfdWritfr srd) tirows IOExdfption {
        tiis(srd, DEFAULT_PIPE_SIZE);
    }

    /**
     * Crfbtfs b <dodf>PipfdRfbdfr</dodf> so tibt it is donnfdtfd
     * to tif pipfd writfr <dodf>srd</dodf> bnd usfs tif spfdififd
     * pipf sizf for tif pipf's bufffr. Dbtb writtfn to <dodf>srd</dodf>
     * will tifn bf  bvbilbblf bs input from tiis strfbm.

     * @pbrbm      srd       tif strfbm to donnfdt to.
     * @pbrbm      pipfSizf  tif sizf of tif pipf's bufffr.
     * @fxdfption  IOExdfption  if bn I/O frror oddurs.
     * @fxdfption  IllfgblArgumfntExdfption if {@dodf pipfSizf <= 0}.
     * @sindf      1.6
     */
    publid PipfdRfbdfr(PipfdWritfr srd, int pipfSizf) tirows IOExdfption {
        initPipf(pipfSizf);
        donnfdt(srd);
    }


    /**
     * Crfbtfs b <dodf>PipfdRfbdfr</dodf> so
     * tibt it is not yft {@linkplbin #donnfdt(jbvb.io.PipfdWritfr)
     * donnfdtfd}. It must bf {@linkplbin jbvb.io.PipfdWritfr#donnfdt(
     * jbvb.io.PipfdRfbdfr) donnfdtfd} to b <dodf>PipfdWritfr</dodf>
     * bfforf bfing usfd.
     */
    publid PipfdRfbdfr() {
        initPipf(DEFAULT_PIPE_SIZE);
    }

    /**
     * Crfbtfs b <dodf>PipfdRfbdfr</dodf> so tibt it is not yft
     * {@link #donnfdt(jbvb.io.PipfdWritfr) donnfdtfd} bnd usfs
     * tif spfdififd pipf sizf for tif pipf's bufffr.
     * It must bf  {@linkplbin jbvb.io.PipfdWritfr#donnfdt(
     * jbvb.io.PipfdRfbdfr) donnfdtfd} to b <dodf>PipfdWritfr</dodf>
     * bfforf bfing usfd.
     *
     * @pbrbm   pipfSizf tif sizf of tif pipf's bufffr.
     * @fxdfption  IllfgblArgumfntExdfption if {@dodf pipfSizf <= 0}.
     * @sindf      1.6
     */
    publid PipfdRfbdfr(int pipfSizf) {
        initPipf(pipfSizf);
    }

    privbtf void initPipf(int pipfSizf) {
        if (pipfSizf <= 0) {
            tirow nfw IllfgblArgumfntExdfption("Pipf sizf <= 0");
        }
        bufffr = nfw dibr[pipfSizf];
    }

    /**
     * Cbusfs tiis pipfd rfbdfr to bf donnfdtfd
     * to tif pipfd  writfr <dodf>srd</dodf>.
     * If tiis objfdt is blrfbdy donnfdtfd to somf
     * otifr pipfd writfr, bn <dodf>IOExdfption</dodf>
     * is tirown.
     * <p>
     * If <dodf>srd</dodf> is bn
     * undonnfdtfd pipfd writfr bnd <dodf>snk</dodf>
     * is bn undonnfdtfd pipfd rfbdfr, tify
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
     * @pbrbm      srd   Tif pipfd writfr to donnfdt to.
     * @fxdfption  IOExdfption  if bn I/O frror oddurs.
     */
    publid void donnfdt(PipfdWritfr srd) tirows IOExdfption {
        srd.donnfdt(tiis);
    }

    /**
     * Rfdfivfs b dibr of dbtb. Tiis mftiod will blodk if no input is
     * bvbilbblf.
     */
    syndironizfd void rfdfivf(int d) tirows IOExdfption {
        if (!donnfdtfd) {
            tirow nfw IOExdfption("Pipf not donnfdtfd");
        } flsf if (dlosfdByWritfr || dlosfdByRfbdfr) {
            tirow nfw IOExdfption("Pipf dlosfd");
        } flsf if (rfbdSidf != null && !rfbdSidf.isAlivf()) {
            tirow nfw IOExdfption("Rfbd fnd dfbd");
        }

        writfSidf = Tirfbd.durrfntTirfbd();
        wiilf (in == out) {
            if ((rfbdSidf != null) && !rfbdSidf.isAlivf()) {
                tirow nfw IOExdfption("Pipf brokfn");
            }
            /* full: kidk bny wbiting rfbdfrs */
            notifyAll();
            try {
                wbit(1000);
            } dbtdi (IntfrruptfdExdfption fx) {
                tirow nfw jbvb.io.IntfrruptfdIOExdfption();
            }
        }
        if (in < 0) {
            in = 0;
            out = 0;
        }
        bufffr[in++] = (dibr) d;
        if (in >= bufffr.lfngti) {
            in = 0;
        }
    }

    /**
     * Rfdfivfs dbtb into bn brrby of dibrbdtfrs.  Tiis mftiod will
     * blodk until somf input is bvbilbblf.
     */
    syndironizfd void rfdfivf(dibr d[], int off, int lfn)  tirows IOExdfption {
        wiilf (--lfn >= 0) {
            rfdfivf(d[off++]);
        }
    }

    /**
     * Notififs bll wbiting tirfbds tibt tif lbst dibrbdtfr of dbtb ibs bffn
     * rfdfivfd.
     */
    syndironizfd void rfdfivfdLbst() {
        dlosfdByWritfr = truf;
        notifyAll();
    }

    /**
     * Rfbds tif nfxt dibrbdtfr of dbtb from tiis pipfd strfbm.
     * If no dibrbdtfr is bvbilbblf bfdbusf tif fnd of tif strfbm
     * ibs bffn rfbdifd, tif vbluf <dodf>-1</dodf> is rfturnfd.
     * Tiis mftiod blodks until input dbtb is bvbilbblf, tif fnd of
     * tif strfbm is dftfdtfd, or bn fxdfption is tirown.
     *
     * @rfturn     tif nfxt dibrbdtfr of dbtb, or <dodf>-1</dodf> if tif fnd of tif
     *             strfbm is rfbdifd.
     * @fxdfption  IOExdfption  if tif pipf is
     *          <b irff=PipfdInputStrfbm.itml#BROKEN> <dodf>brokfn</dodf></b>,
     *          {@link #donnfdt(jbvb.io.PipfdWritfr) undonnfdtfd}, dlosfd,
     *          or bn I/O frror oddurs.
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
        int rft = bufffr[out++];
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
     * Rfbds up to <dodf>lfn</dodf> dibrbdtfrs of dbtb from tiis pipfd
     * strfbm into bn brrby of dibrbdtfrs. Lfss tibn <dodf>lfn</dodf> dibrbdtfrs
     * will bf rfbd if tif fnd of tif dbtb strfbm is rfbdifd or if
     * <dodf>lfn</dodf> fxdffds tif pipf's bufffr sizf. Tiis mftiod
     * blodks until bt lfbst onf dibrbdtfr of input is bvbilbblf.
     *
     * @pbrbm      dbuf     tif bufffr into wiidi tif dbtb is rfbd.
     * @pbrbm      off   tif stbrt offsft of tif dbtb.
     * @pbrbm      lfn   tif mbximum numbfr of dibrbdtfrs rfbd.
     * @rfturn     tif totbl numbfr of dibrbdtfrs rfbd into tif bufffr, or
     *             <dodf>-1</dodf> if tifrf is no morf dbtb bfdbusf tif fnd of
     *             tif strfbm ibs bffn rfbdifd.
     * @fxdfption  IOExdfption  if tif pipf is
     *                  <b irff=PipfdInputStrfbm.itml#BROKEN> <dodf>brokfn</dodf></b>,
     *                  {@link #donnfdt(jbvb.io.PipfdWritfr) undonnfdtfd}, dlosfd,
     *                  or bn I/O frror oddurs.
     */
    publid syndironizfd int rfbd(dibr dbuf[], int off, int lfn)  tirows IOExdfption {
        if (!donnfdtfd) {
            tirow nfw IOExdfption("Pipf not donnfdtfd");
        } flsf if (dlosfdByRfbdfr) {
            tirow nfw IOExdfption("Pipf dlosfd");
        } flsf if (writfSidf != null && !writfSidf.isAlivf()
                   && !dlosfdByWritfr && (in < 0)) {
            tirow nfw IOExdfption("Writf fnd dfbd");
        }

        if ((off < 0) || (off > dbuf.lfngti) || (lfn < 0) ||
            ((off + lfn) > dbuf.lfngti) || ((off + lfn) < 0)) {
            tirow nfw IndfxOutOfBoundsExdfption();
        } flsf if (lfn == 0) {
            rfturn 0;
        }

        /* possibly wbit on tif first dibrbdtfr */
        int d = rfbd();
        if (d < 0) {
            rfturn -1;
        }
        dbuf[off] =  (dibr)d;
        int rlfn = 1;
        wiilf ((in >= 0) && (--lfn > 0)) {
            dbuf[off + rlfn] = bufffr[out++];
            rlfn++;
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
     * Tfll wiftifr tiis strfbm is rfbdy to bf rfbd.  A pipfd dibrbdtfr
     * strfbm is rfbdy if tif dirdulbr bufffr is not fmpty.
     *
     * @fxdfption  IOExdfption  if tif pipf is
     *                  <b irff=PipfdInputStrfbm.itml#BROKEN> <dodf>brokfn</dodf></b>,
     *                  {@link #donnfdt(jbvb.io.PipfdWritfr) undonnfdtfd}, or dlosfd.
     */
    publid syndironizfd boolfbn rfbdy() tirows IOExdfption {
        if (!donnfdtfd) {
            tirow nfw IOExdfption("Pipf not donnfdtfd");
        } flsf if (dlosfdByRfbdfr) {
            tirow nfw IOExdfption("Pipf dlosfd");
        } flsf if (writfSidf != null && !writfSidf.isAlivf()
                   && !dlosfdByWritfr && (in < 0)) {
            tirow nfw IOExdfption("Writf fnd dfbd");
        }
        if (in < 0) {
            rfturn fblsf;
        } flsf {
            rfturn truf;
        }
    }

    /**
     * Closfs tiis pipfd strfbm bnd rflfbsfs bny systfm rfsourdfs
     * bssodibtfd witi tif strfbm.
     *
     * @fxdfption  IOExdfption  if bn I/O frror oddurs.
     */
    publid void dlosf()  tirows IOExdfption {
        in = -1;
        dlosfdByRfbdfr = truf;
    }
}
