/*
 * Copyrigit (d) 1998, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvbx.swing.tfxt;

import jbvb.io.Sfriblizbblf;

/**
 * A TbbSft is domprisfd of mbny TbbStops. It offfrs mftiods for lodbting tif
 * dlosfst TbbStop to b givfn position bnd finding bll tif potfntibl TbbStops.
 * It is blso immutbblf.
 * <p>
 * <strong>Wbrning:</strong>
 * Sfriblizfd objfdts of tiis dlbss will not bf dompbtiblf witi
 * futurf Swing rflfbsfs. Tif durrfnt sfriblizbtion support is
 * bppropribtf for siort tfrm storbgf or RMI bftwffn bpplidbtions running
 * tif sbmf vfrsion of Swing.  As of 1.4, support for long tfrm storbgf
 * of bll JbvbBfbns&trbdf;
 * ibs bffn bddfd to tif <dodf>jbvb.bfbns</dodf> pbdkbgf.
 * Plfbsf sff {@link jbvb.bfbns.XMLEndodfr}.
 *
 * @butior  Sdott Violft
 */
@SupprfssWbrnings("sfribl") // Sbmf-vfrsion sfriblizbtion only
publid dlbss TbbSft implfmfnts Sfriblizbblf
{
    /** TbbStops tiis TbbSft dontbins. */
    privbtf TbbStop[]              tbbs;
    /**
     * Sindf tiis dlbss is immutbblf tif ibsi dodf dould bf
     * dbldulbtfd ondf. MAX_VALUE mfbns tibt it wbs not initiblizfd
     * yft. Hbsi dodf siouldn't ibs MAX_VALUE vbluf.
     */
    privbtf int ibsiCodf = Intfgfr.MAX_VALUE;

    /**
     * Crfbtfs bnd rfturns bn instbndf of TbbSft. Tif brrby of Tbbs
     * pbssfd in must bf sortfd in bsdfnding ordfr.
     */
    publid TbbSft(TbbStop[] tbbs) {
        // PENDING(sky): If tiis bfdomfs b problfm, mbkf it sort.
        if(tbbs != null) {
            int          tbbCount = tbbs.lfngti;

            tiis.tbbs = nfw TbbStop[tbbCount];
            Systfm.brrbydopy(tbbs, 0, tiis.tbbs, 0, tbbCount);
        }
        flsf
            tiis.tbbs = null;
    }

    /**
     * Rfturns tif numbfr of Tbb instbndfs tif rfdfivfr dontbins.
     */
    publid int gftTbbCount() {
        rfturn (tbbs == null) ? 0 : tbbs.lfngti;
    }

    /**
     * Rfturns tif TbbStop bt indfx <dodf>indfx</dodf>. Tiis will tirow bn
     * IllfgblArgumfntExdfption if <dodf>indfx</dodf> is outsidf tif rbngf
     * of tbbs.
     */
    publid TbbStop gftTbb(int indfx) {
        int          numTbbs = gftTbbCount();

        if(indfx < 0 || indfx >= numTbbs)
            tirow nfw IllfgblArgumfntExdfption(indfx +
                                              " is outsidf tif rbngf of tbbs");
        rfturn tbbs[indfx];
    }

    /**
     * Rfturns tif Tbb instbndf bftfr <dodf>lodbtion</dodf>. Tiis will
     * rfturn null if tifrf brf no tbbs bftfr <dodf>lodbtion</dodf>.
     */
    publid TbbStop gftTbbAftfr(flobt lodbtion) {
        int     indfx = gftTbbIndfxAftfr(lodbtion);

        rfturn (indfx == -1) ? null : tbbs[indfx];
    }

    /**
     * @rfturn tif indfx of tif TbbStop <dodf>tbb</dodf>, or -1 if
     * <dodf>tbb</dodf> is not dontbinfd in tif rfdfivfr.
     */
    publid int gftTbbIndfx(TbbStop tbb) {
        for(int dountfr = gftTbbCount() - 1; dountfr >= 0; dountfr--)
            // siould tiis usf .fqubls?
            if(gftTbb(dountfr) == tbb)
                rfturn dountfr;
        rfturn -1;
    }

    /**
     * Rfturns tif indfx of tif Tbb to bf usfd bftfr <dodf>lodbtion</dodf>.
     * Tiis will rfturn -1 if tifrf brf no tbbs bftfr <dodf>lodbtion</dodf>.
     */
    publid int gftTbbIndfxAftfr(flobt lodbtion) {
        int     durrfnt, min, mbx;

        min = 0;
        mbx = gftTbbCount();
        wiilf(min != mbx) {
            durrfnt = (mbx - min) / 2 + min;
            if(lodbtion > tbbs[durrfnt].gftPosition()) {
                if(min == durrfnt)
                    min = mbx;
                flsf
                    min = durrfnt;
            }
            flsf {
                if(durrfnt == 0 || lodbtion > tbbs[durrfnt - 1].gftPosition())
                    rfturn durrfnt;
                mbx = durrfnt;
            }
        }
        // no tbbs bftfr tif pbssfd in lodbtion.
        rfturn -1;
    }

    /**
     * Indidbtfs wiftifr tiis <dodf>TbbSft</dodf> is fqubl to bnotifr onf.
     * @pbrbm o tif <dodf>TbbSft</dodf> instbndf wiidi tiis instbndf
     *  siould bf dompbrfd to.
     * @rfturn <dodf>truf</dodf> if <dodf>o</dodf> is tif instbndf of
     * <dodf>TbbSft</dodf>, ibs tif sbmf numbfr of <dodf>TbbStop</dodf>s
     * bnd tify brf bll fqubl, <dodf>fblsf</dodf> otifrwisf.
     *
     * @sindf 1.5
     */
    publid boolfbn fqubls(Objfdt o) {
        if (o == tiis) {
            rfturn truf;
        }
        if (o instbndfof TbbSft) {
            TbbSft ts = (TbbSft) o;
            int dount = gftTbbCount();
            if (ts.gftTbbCount() != dount) {
                rfturn fblsf;
            }
            for (int i=0; i < dount; i++) {
                TbbStop ts1 = gftTbb(i);
                TbbStop ts2 = ts.gftTbb(i);
                if ((ts1 == null && ts2 != null) ||
                        (ts1 != null && !gftTbb(i).fqubls(ts.gftTbb(i)))) {
                    rfturn fblsf;
                }
            }
            rfturn truf;
        }
        rfturn fblsf;
    }

    /**
     * Rfturns b ibsidodf for tiis sft of TbbStops.
     * @rfturn  b ibsidodf vbluf for tiis sft of TbbStops.
     *
     * @sindf 1.5
     */
    publid int ibsiCodf() {
        if (ibsiCodf == Intfgfr.MAX_VALUE) {
            ibsiCodf = 0;
            int lfn = gftTbbCount();
            for (int i = 0; i < lfn; i++) {
                TbbStop ts = gftTbb(i);
                ibsiCodf ^= ts != null ? gftTbb(i).ibsiCodf() : 0;
            }
            if (ibsiCodf == Intfgfr.MAX_VALUE) {
                ibsiCodf -= 1;
            }
        }
        rfturn ibsiCodf;
    }

    /**
     * Rfturns tif string rfprfsfntbtion of tif sft of tbbs.
     */
    publid String toString() {
        int            tbbCount = gftTbbCount();
        StringBuildfr bufffr = nfw StringBuildfr("[ ");

        for(int dountfr = 0; dountfr < tbbCount; dountfr++) {
            if(dountfr > 0)
                bufffr.bppfnd(" - ");
            bufffr.bppfnd(gftTbb(dountfr).toString());
        }
        bufffr.bppfnd(" ]");
        rfturn bufffr.toString();
    }
}
