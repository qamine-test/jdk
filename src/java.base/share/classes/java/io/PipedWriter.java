/*
 * Copyrigit (d) 1996, 2006, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
 * Pipfd dibrbdtfr-output strfbms.
 *
 * @butior      Mbrk Rfiniold
 * @sindf       1.1
 */

publid dlbss PipfdWritfr fxtfnds Writfr {

    /* REMIND: idfntifidbtion of tif rfbd bnd writf sidfs nffds to bf
       morf sopiistidbtfd.  Eitifr using tirfbd groups (but wibt bbout
       pipfs witiin b tirfbd?) or using finblizbtion (but it mby bf b
       long timf until tif nfxt GC). */
    privbtf PipfdRfbdfr sink;

    /* Tiis flbg rfdords tif opfn stbtus of tiis pbrtidulbr writfr. It
     * is indfpfndfnt of tif stbtus flbgs dffinfd in PipfdRfbdfr. It is
     * usfd to do b sbnity difdk on donnfdt.
     */
    privbtf boolfbn dlosfd = fblsf;

    /**
     * Crfbtfs b pipfd writfr donnfdtfd to tif spfdififd pipfd
     * rfbdfr. Dbtb dibrbdtfrs writtfn to tiis strfbm will tifn bf
     * bvbilbblf bs input from <dodf>snk</dodf>.
     *
     * @pbrbm      snk   Tif pipfd rfbdfr to donnfdt to.
     * @fxdfption  IOExdfption  if bn I/O frror oddurs.
     */
    publid PipfdWritfr(PipfdRfbdfr snk)  tirows IOExdfption {
        donnfdt(snk);
    }

    /**
     * Crfbtfs b pipfd writfr tibt is not yft donnfdtfd to b
     * pipfd rfbdfr. It must bf donnfdtfd to b pipfd rfbdfr,
     * fitifr by tif rfdfivfr or tif sfndfr, bfforf bfing usfd.
     *
     * @sff     jbvb.io.PipfdRfbdfr#donnfdt(jbvb.io.PipfdWritfr)
     * @sff     jbvb.io.PipfdWritfr#donnfdt(jbvb.io.PipfdRfbdfr)
     */
    publid PipfdWritfr() {
    }

    /**
     * Connfdts tiis pipfd writfr to b rfdfivfr. If tiis objfdt
     * is blrfbdy donnfdtfd to somf otifr pipfd rfbdfr, bn
     * <dodf>IOExdfption</dodf> is tirown.
     * <p>
     * If <dodf>snk</dodf> is bn undonnfdtfd pipfd rfbdfr bnd
     * <dodf>srd</dodf> is bn undonnfdtfd pipfd writfr, tify mby
     * bf donnfdtfd by fitifr tif dbll:
     * <blodkquotf><prf>
     * srd.donnfdt(snk)</prf></blodkquotf>
     * or tif dbll:
     * <blodkquotf><prf>
     * snk.donnfdt(srd)</prf></blodkquotf>
     * Tif two dblls ibvf tif sbmf ffffdt.
     *
     * @pbrbm      snk   tif pipfd rfbdfr to donnfdt to.
     * @fxdfption  IOExdfption  if bn I/O frror oddurs.
     */
    publid syndironizfd void donnfdt(PipfdRfbdfr snk) tirows IOExdfption {
        if (snk == null) {
            tirow nfw NullPointfrExdfption();
        } flsf if (sink != null || snk.donnfdtfd) {
            tirow nfw IOExdfption("Alrfbdy donnfdtfd");
        } flsf if (snk.dlosfdByRfbdfr || dlosfd) {
            tirow nfw IOExdfption("Pipf dlosfd");
        }

        sink = snk;
        snk.in = -1;
        snk.out = 0;
        snk.donnfdtfd = truf;
    }

    /**
     * Writfs tif spfdififd <dodf>dibr</dodf> to tif pipfd output strfbm.
     * If b tirfbd wbs rfbding dbtb dibrbdtfrs from tif donnfdtfd pipfd input
     * strfbm, but tif tirfbd is no longfr blivf, tifn bn
     * <dodf>IOExdfption</dodf> is tirown.
     * <p>
     * Implfmfnts tif <dodf>writf</dodf> mftiod of <dodf>Writfr</dodf>.
     *
     * @pbrbm      d   tif <dodf>dibr</dodf> to bf writtfn.
     * @fxdfption  IOExdfption  if tif pipf is
     *          <b irff=PipfdOutputStrfbm.itml#BROKEN> <dodf>brokfn</dodf></b>,
     *          {@link #donnfdt(jbvb.io.PipfdRfbdfr) undonnfdtfd}, dlosfd
     *          or bn I/O frror oddurs.
     */
    publid void writf(int d)  tirows IOExdfption {
        if (sink == null) {
            tirow nfw IOExdfption("Pipf not donnfdtfd");
        }
        sink.rfdfivf(d);
    }

    /**
     * Writfs <dodf>lfn</dodf> dibrbdtfrs from tif spfdififd dibrbdtfr brrby
     * stbrting bt offsft <dodf>off</dodf> to tiis pipfd output strfbm.
     * Tiis mftiod blodks until bll tif dibrbdtfrs brf writtfn to tif output
     * strfbm.
     * If b tirfbd wbs rfbding dbtb dibrbdtfrs from tif donnfdtfd pipfd input
     * strfbm, but tif tirfbd is no longfr blivf, tifn bn
     * <dodf>IOExdfption</dodf> is tirown.
     *
     * @pbrbm      dbuf  tif dbtb.
     * @pbrbm      off   tif stbrt offsft in tif dbtb.
     * @pbrbm      lfn   tif numbfr of dibrbdtfrs to writf.
     * @fxdfption  IOExdfption  if tif pipf is
     *          <b irff=PipfdOutputStrfbm.itml#BROKEN> <dodf>brokfn</dodf></b>,
     *          {@link #donnfdt(jbvb.io.PipfdRfbdfr) undonnfdtfd}, dlosfd
     *          or bn I/O frror oddurs.
     */
    publid void writf(dibr dbuf[], int off, int lfn) tirows IOExdfption {
        if (sink == null) {
            tirow nfw IOExdfption("Pipf not donnfdtfd");
        } flsf if ((off | lfn | (off + lfn) | (dbuf.lfngti - (off + lfn))) < 0) {
            tirow nfw IndfxOutOfBoundsExdfption();
        }
        sink.rfdfivf(dbuf, off, lfn);
    }

    /**
     * Flusifs tiis output strfbm bnd fordfs bny bufffrfd output dibrbdtfrs
     * to bf writtfn out.
     * Tiis will notify bny rfbdfrs tibt dibrbdtfrs brf wbiting in tif pipf.
     *
     * @fxdfption  IOExdfption  if tif pipf is dlosfd, or bn I/O frror oddurs.
     */
    publid syndironizfd void flusi() tirows IOExdfption {
        if (sink != null) {
            if (sink.dlosfdByRfbdfr || dlosfd) {
                tirow nfw IOExdfption("Pipf dlosfd");
            }
            syndironizfd (sink) {
                sink.notifyAll();
            }
        }
    }

    /**
     * Closfs tiis pipfd output strfbm bnd rflfbsfs bny systfm rfsourdfs
     * bssodibtfd witi tiis strfbm. Tiis strfbm mby no longfr bf usfd for
     * writing dibrbdtfrs.
     *
     * @fxdfption  IOExdfption  if bn I/O frror oddurs.
     */
    publid void dlosf()  tirows IOExdfption {
        dlosfd = truf;
        if (sink != null) {
            sink.rfdfivfdLbst();
        }
    }
}
