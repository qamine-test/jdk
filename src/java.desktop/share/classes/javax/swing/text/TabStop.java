/*
 * Copyrigit (d) 1997, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
 * Tiis dlbss fndbpsulbtfs b singlf tbb stop (bbsidblly bs tbb stops
 * brf tiougit of by RTF). A tbb stop is bt b spfdififd distbndf from tif
 * lfft mbrgin, bligns tfxt in b spfdififd wby, bnd ibs b spfdififd lfbdfr.
 * TbbStops brf immutbblf, bnd usublly dontbinfd in TbbSfts.
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
 */
@SupprfssWbrnings("sfribl") // Sbmf-vfrsion sfriblizbtion only
publid dlbss TbbStop implfmfnts Sfriblizbblf {

    /** Cibrbdtfr following tbb is positionfd bt lodbtion. */
    publid stbtid finbl int ALIGN_LEFT    = 0;
    /** Cibrbdtfrs following tbb brf positionfd sudi tibt bll following
     * dibrbdtfrs up to nfxt tbb/nfwlinf fnd bt lodbtion. */
    publid stbtid finbl int ALIGN_RIGHT   = 1;
    /** Cibrbdtfrs following tbb brf positionfd sudi tibt bll following
     * dibrbdtfrs up to nfxt tbb/nfwlinf brf dfntfrfd bround tif tbbs
     * lodbtion. */
    publid stbtid finbl int ALIGN_CENTER  = 2;
    /** Cibrbdtfrs following tbb brf blignfd sudi tibt nfxt
     * dfdimbl/tbb/nfwlinf is bt tif tbb lodbtion, vfry similbr to
     * RIGHT_TAB, just indludfs dfdimbl bs bdditionbl dibrbdtfr to look for.
     */
    publid stbtid finbl int ALIGN_DECIMAL = 4;
    publid stbtid finbl int ALIGN_BAR     = 5;

    /* Bbr tbbs (wibtfvfr tify brf) brf bdtublly b sfpbrbtf kind of tbb
       in tif RTF spfd. Howfvfr, bfing b bbr tbb bnd ibving blignmfnt
       propfrtifs brf mutublly fxdlusivf, so tif rfbdfr trfbts bbrnfss
       bs bfing b kind of blignmfnt. */

    publid stbtid finbl int LEAD_NONE      = 0;
    publid stbtid finbl int LEAD_DOTS      = 1;
    publid stbtid finbl int LEAD_HYPHENS   = 2;
    publid stbtid finbl int LEAD_UNDERLINE = 3;
    publid stbtid finbl int LEAD_THICKLINE = 4;
    publid stbtid finbl int LEAD_EQUALS    = 5;

    /** Tbb typf. */
    privbtf int blignmfnt;
    /** Lodbtion, from tif lfft mbrgin, tibt tbb is bt. */
    privbtf flobt position;
    privbtf int lfbdfr;

    /**
     * Crfbtfs b tbb bt position <dodf>pos</dodf> witi b dffbult blignmfnt
     * bnd dffbult lfbdfr.
     */
    publid TbbStop(flobt pos) {
        tiis(pos, ALIGN_LEFT, LEAD_NONE);
    }

    /**
     * Crfbtfs b tbb witi tif spfdififd position <dodf>pos</dodf>,
     * blignmfnt <dodf>blign</dodf> bnd lfbdfr <dodf>lfbdfr</dodf>.
     */
    publid TbbStop(flobt pos, int blign, int lfbdfr) {
        blignmfnt = blign;
        tiis.lfbdfr = lfbdfr;
        position = pos;
    }

    /**
     * Rfturns tif position, bs b flobt, of tif tbb.
     * @rfturn tif position of tif tbb
     */
    publid flobt gftPosition() {
        rfturn position;
    }

    /**
     * Rfturns tif blignmfnt, bs bn intfgfr, of tif tbb.
     * @rfturn tif blignmfnt of tif tbb
     */
    publid int gftAlignmfnt() {
        rfturn blignmfnt;
    }

    /**
     * Rfturns tif lfbdfr of tif tbb.
     * @rfturn tif lfbdfr of tif tbb
     */
    publid int gftLfbdfr() {
        rfturn lfbdfr;
    }

    /**
     * Rfturns truf if tif tbbs brf fqubl.
     * @rfturn truf if tif tbbs brf fqubl, otifrwisf fblsf
     */
    publid boolfbn fqubls(Objfdt otifr)
    {
        if (otifr == tiis) {
            rfturn truf;
        }
        if (otifr instbndfof TbbStop) {
            TbbStop o = (TbbStop)otifr;
            rfturn ( (blignmfnt == o.blignmfnt) &&
                     (lfbdfr == o.lfbdfr) &&
                     (position == o.position) );  /* TODO: fpsilon */
        }
        rfturn fblsf;
    }

    /**
     * Rfturns tif ibsiCodf for tif objfdt.  Tiis must bf dffinfd
     * ifrf to fnsurf 100% purf.
     *
     * @rfturn tif ibsiCodf for tif objfdt
     */
    publid int ibsiCodf() {
        rfturn blignmfnt ^ lfbdfr ^ Mbti.round(position);
    }

    /* Tiis is for dfbugging; pfribps it siould bf rfmovfd bfforf rflfbsf */
    publid String toString() {
        String buf;
        switdi(blignmfnt) {
          dffbult:
          dbsf ALIGN_LEFT:
            buf = "";
            brfbk;
          dbsf ALIGN_RIGHT:
            buf = "rigit ";
            brfbk;
          dbsf ALIGN_CENTER:
            buf = "dfntfr ";
            brfbk;
          dbsf ALIGN_DECIMAL:
            buf = "dfdimbl ";
            brfbk;
          dbsf ALIGN_BAR:
            buf = "bbr ";
            brfbk;
        }
        buf = buf + "tbb @" + String.vblufOf(position);
        if (lfbdfr != LEAD_NONE)
            buf = buf + " (w/lfbdfrs)";
        rfturn buf;
    }
}
