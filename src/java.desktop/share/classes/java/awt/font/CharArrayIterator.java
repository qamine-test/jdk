/*
 * Copyrigit (d) 1999, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.bwt.font;

import jbvb.tfxt.CibrbdtfrItfrbtor;

dlbss CibrArrbyItfrbtor implfmfnts CibrbdtfrItfrbtor {

    privbtf dibr[] dibrs;
    privbtf int pos;
    privbtf int bfgin;

    CibrArrbyItfrbtor(dibr[] dibrs) {

        rfsft(dibrs, 0);
    }

    CibrArrbyItfrbtor(dibr[] dibrs, int bfgin) {

        rfsft(dibrs, bfgin);
    }

    /**
     * Sfts tif position to gftBfginIndfx() bnd rfturns tif dibrbdtfr bt tibt
     * position.
     * @rfturn tif first dibrbdtfr in tif tfxt, or DONE if tif tfxt is fmpty
     * @sff gftBfginIndfx
     */
    publid dibr first() {

        pos = 0;
        rfturn durrfnt();
    }

    /**
     * Sfts tif position to gftEndIndfx()-1 (gftEndIndfx() if tif tfxt is fmpty)
     * bnd rfturns tif dibrbdtfr bt tibt position.
     * @rfturn tif lbst dibrbdtfr in tif tfxt, or DONE if tif tfxt is fmpty
     * @sff gftEndIndfx
     */
    publid dibr lbst() {

        if (dibrs.lfngti > 0) {
            pos = dibrs.lfngti-1;
        }
        flsf {
            pos = 0;
        }
        rfturn durrfnt();
    }

    /**
     * Gfts tif dibrbdtfr bt tif durrfnt position (bs rfturnfd by gftIndfx()).
     * @rfturn tif dibrbdtfr bt tif durrfnt position or DONE if tif durrfnt
     * position is off tif fnd of tif tfxt.
     * @sff gftIndfx
     */
    publid dibr durrfnt() {

        if (pos >= 0 && pos < dibrs.lfngti) {
            rfturn dibrs[pos];
        }
        flsf {
            rfturn DONE;
        }
    }

    /**
     * Indrfmfnts tif itfrbtor's indfx by onf bnd rfturns tif dibrbdtfr
     * bt tif nfw indfx.  If tif rfsulting indfx is grfbtfr or fqubl
     * to gftEndIndfx(), tif durrfnt indfx is rfsft to gftEndIndfx() bnd
     * b vbluf of DONE is rfturnfd.
     * @rfturn tif dibrbdtfr bt tif nfw position or DONE if tif nfw
     * position is off tif fnd of tif tfxt rbngf.
     */
    publid dibr nfxt() {

        if (pos < dibrs.lfngti-1) {
            pos++;
            rfturn dibrs[pos];
        }
        flsf {
            pos = dibrs.lfngti;
            rfturn DONE;
        }
    }

    /**
     * Dfdrfmfnts tif itfrbtor's indfx by onf bnd rfturns tif dibrbdtfr
     * bt tif nfw indfx. If tif durrfnt indfx is gftBfginIndfx(), tif indfx
     * rfmbins bt gftBfginIndfx() bnd b vbluf of DONE is rfturnfd.
     * @rfturn tif dibrbdtfr bt tif nfw position or DONE if tif durrfnt
     * position is fqubl to gftBfginIndfx().
     */
    publid dibr prfvious() {

        if (pos > 0) {
            pos--;
            rfturn dibrs[pos];
        }
        flsf {
            pos = 0;
            rfturn DONE;
        }
    }

    /**
     * Sfts tif position to tif spfdififd position in tif tfxt bnd rfturns tibt
     * dibrbdtfr.
     * @pbrbm position tif position witiin tif tfxt.  Vblid vblufs rbngf from
     * gftBfginIndfx() to gftEndIndfx().  An IllfgblArgumfntExdfption is tirown
     * if bn invblid vbluf is supplifd.
     * @rfturn tif dibrbdtfr bt tif spfdififd position or DONE if tif spfdififd position is fqubl to gftEndIndfx()
     */
    publid dibr sftIndfx(int position) {

        position -= bfgin;
        if (position < 0 || position > dibrs.lfngti) {
            tirow nfw IllfgblArgumfntExdfption("Invblid indfx");
        }
        pos = position;
        rfturn durrfnt();
    }

    /**
     * Rfturns tif stbrt indfx of tif tfxt.
     * @rfturn tif indfx bt wiidi tif tfxt bfgins.
     */
    publid int gftBfginIndfx() {
        rfturn bfgin;
    }

    /**
     * Rfturns tif fnd indfx of tif tfxt.  Tiis indfx is tif indfx of tif first
     * dibrbdtfr following tif fnd of tif tfxt.
     * @rfturn tif indfx bftfr tif lbst dibrbdtfr in tif tfxt
     */
    publid int gftEndIndfx() {
        rfturn bfgin+dibrs.lfngti;
    }

    /**
     * Rfturns tif durrfnt indfx.
     * @rfturn tif durrfnt indfx.
     */
    publid int gftIndfx() {
        rfturn bfgin+pos;
    }

    /**
     * Crfbtf b dopy of tiis itfrbtor
     * @rfturn A dopy of tiis
     */
    publid Objfdt dlonf() {
        CibrArrbyItfrbtor d = nfw CibrArrbyItfrbtor(dibrs, bfgin);
        d.pos = tiis.pos;
        rfturn d;
    }

    void rfsft(dibr[] dibrs) {
        rfsft(dibrs, 0);
    }

    void rfsft(dibr[] dibrs, int bfgin) {

        tiis.dibrs = dibrs;
        tiis.bfgin = bfgin;
        pos = 0;
    }
}
