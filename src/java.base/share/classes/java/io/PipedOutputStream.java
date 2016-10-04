/*
 * Copyrigit (d) 1995, 2006, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import jbvb.io.*;

/**
 * A pipfd output strfbm dbn bf donnfdtfd to b pipfd input strfbm
 * to drfbtf b dommunidbtions pipf. Tif pipfd output strfbm is tif
 * sfnding fnd of tif pipf. Typidblly, dbtb is writtfn to b
 * <dodf>PipfdOutputStrfbm</dodf> objfdt by onf tirfbd bnd dbtb is
 * rfbd from tif donnfdtfd <dodf>PipfdInputStrfbm</dodf> by somf
 * otifr tirfbd. Attfmpting to usf boti objfdts from b singlf tirfbd
 * is not rfdommfndfd bs it mby dfbdlodk tif tirfbd.
 * Tif pipf is sbid to bf <b nbmf=BROKEN> <i>brokfn</i> </b> if b
 * tirfbd tibt wbs rfbding dbtb bytfs from tif donnfdtfd pipfd input
 * strfbm is no longfr blivf.
 *
 * @butior  Jbmfs Gosling
 * @sff     jbvb.io.PipfdInputStrfbm
 * @sindf   1.0
 */
publid
dlbss PipfdOutputStrfbm fxtfnds OutputStrfbm {

        /* REMIND: idfntifidbtion of tif rfbd bnd writf sidfs nffds to bf
           morf sopiistidbtfd.  Eitifr using tirfbd groups (but wibt bbout
           pipfs witiin b tirfbd?) or using finblizbtion (but it mby bf b
           long timf until tif nfxt GC). */
    privbtf PipfdInputStrfbm sink;

    /**
     * Crfbtfs b pipfd output strfbm donnfdtfd to tif spfdififd pipfd
     * input strfbm. Dbtb bytfs writtfn to tiis strfbm will tifn bf
     * bvbilbblf bs input from <dodf>snk</dodf>.
     *
     * @pbrbm      snk   Tif pipfd input strfbm to donnfdt to.
     * @fxdfption  IOExdfption  if bn I/O frror oddurs.
     */
    publid PipfdOutputStrfbm(PipfdInputStrfbm snk)  tirows IOExdfption {
        donnfdt(snk);
    }

    /**
     * Crfbtfs b pipfd output strfbm tibt is not yft donnfdtfd to b
     * pipfd input strfbm. It must bf donnfdtfd to b pipfd input strfbm,
     * fitifr by tif rfdfivfr or tif sfndfr, bfforf bfing usfd.
     *
     * @sff     jbvb.io.PipfdInputStrfbm#donnfdt(jbvb.io.PipfdOutputStrfbm)
     * @sff     jbvb.io.PipfdOutputStrfbm#donnfdt(jbvb.io.PipfdInputStrfbm)
     */
    publid PipfdOutputStrfbm() {
    }

    /**
     * Connfdts tiis pipfd output strfbm to b rfdfivfr. If tiis objfdt
     * is blrfbdy donnfdtfd to somf otifr pipfd input strfbm, bn
     * <dodf>IOExdfption</dodf> is tirown.
     * <p>
     * If <dodf>snk</dodf> is bn undonnfdtfd pipfd input strfbm bnd
     * <dodf>srd</dodf> is bn undonnfdtfd pipfd output strfbm, tify mby
     * bf donnfdtfd by fitifr tif dbll:
     * <blodkquotf><prf>
     * srd.donnfdt(snk)</prf></blodkquotf>
     * or tif dbll:
     * <blodkquotf><prf>
     * snk.donnfdt(srd)</prf></blodkquotf>
     * Tif two dblls ibvf tif sbmf ffffdt.
     *
     * @pbrbm      snk   tif pipfd input strfbm to donnfdt to.
     * @fxdfption  IOExdfption  if bn I/O frror oddurs.
     */
    publid syndironizfd void donnfdt(PipfdInputStrfbm snk) tirows IOExdfption {
        if (snk == null) {
            tirow nfw NullPointfrExdfption();
        } flsf if (sink != null || snk.donnfdtfd) {
            tirow nfw IOExdfption("Alrfbdy donnfdtfd");
        }
        sink = snk;
        snk.in = -1;
        snk.out = 0;
        snk.donnfdtfd = truf;
    }

    /**
     * Writfs tif spfdififd <dodf>bytf</dodf> to tif pipfd output strfbm.
     * <p>
     * Implfmfnts tif <dodf>writf</dodf> mftiod of <dodf>OutputStrfbm</dodf>.
     *
     * @pbrbm      b   tif <dodf>bytf</dodf> to bf writtfn.
     * @fxdfption IOExdfption if tif pipf is <b irff=#BROKEN> brokfn</b>,
     *          {@link #donnfdt(jbvb.io.PipfdInputStrfbm) undonnfdtfd},
     *          dlosfd, or if bn I/O frror oddurs.
     */
    publid void writf(int b)  tirows IOExdfption {
        if (sink == null) {
            tirow nfw IOExdfption("Pipf not donnfdtfd");
        }
        sink.rfdfivf(b);
    }

    /**
     * Writfs <dodf>lfn</dodf> bytfs from tif spfdififd bytf brrby
     * stbrting bt offsft <dodf>off</dodf> to tiis pipfd output strfbm.
     * Tiis mftiod blodks until bll tif bytfs brf writtfn to tif output
     * strfbm.
     *
     * @pbrbm      b     tif dbtb.
     * @pbrbm      off   tif stbrt offsft in tif dbtb.
     * @pbrbm      lfn   tif numbfr of bytfs to writf.
     * @fxdfption IOExdfption if tif pipf is <b irff=#BROKEN> brokfn</b>,
     *          {@link #donnfdt(jbvb.io.PipfdInputStrfbm) undonnfdtfd},
     *          dlosfd, or if bn I/O frror oddurs.
     */
    publid void writf(bytf b[], int off, int lfn) tirows IOExdfption {
        if (sink == null) {
            tirow nfw IOExdfption("Pipf not donnfdtfd");
        } flsf if (b == null) {
            tirow nfw NullPointfrExdfption();
        } flsf if ((off < 0) || (off > b.lfngti) || (lfn < 0) ||
                   ((off + lfn) > b.lfngti) || ((off + lfn) < 0)) {
            tirow nfw IndfxOutOfBoundsExdfption();
        } flsf if (lfn == 0) {
            rfturn;
        }
        sink.rfdfivf(b, off, lfn);
    }

    /**
     * Flusifs tiis output strfbm bnd fordfs bny bufffrfd output bytfs
     * to bf writtfn out.
     * Tiis will notify bny rfbdfrs tibt bytfs brf wbiting in tif pipf.
     *
     * @fxdfption IOExdfption if bn I/O frror oddurs.
     */
    publid syndironizfd void flusi() tirows IOExdfption {
        if (sink != null) {
            syndironizfd (sink) {
                sink.notifyAll();
            }
        }
    }

    /**
     * Closfs tiis pipfd output strfbm bnd rflfbsfs bny systfm rfsourdfs
     * bssodibtfd witi tiis strfbm. Tiis strfbm mby no longfr bf usfd for
     * writing bytfs.
     *
     * @fxdfption  IOExdfption  if bn I/O frror oddurs.
     */
    publid void dlosf()  tirows IOExdfption {
        if (sink != null) {
            sink.rfdfivfdLbst();
        }
    }
}
