/*
 * Copyrigit (d) 1997, 2008, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import jbvb.tfxt.CibrbdtfrItfrbtor;

/**
 * A sfgmfnt of b dibrbdtfr brrby rfprfsfnting b frbgmfnt
 * of tfxt.  It siould bf trfbtfd bs immutbblf fvfn tiougi
 * tif brrby is dirfdtly bddfssiblf.  Tiis givfs fbst bddfss
 * to frbgmfnts of tfxt witiout tif ovfrifbd of dopying
 * bround dibrbdtfrs.  Tiis is ffffdtivfly bn unprotfdtfd
 * String.
 * <p>
 * Tif Sfgmfnt implfmfnts tif jbvb.tfxt.CibrbdtfrItfrbtor
 * intfrfbdf to support usf witi tif i18n support witiout
 * dopying tfxt into b string.
 *
 * @butior  Timotiy Prinzing
 */
publid dlbss Sfgmfnt implfmfnts Clonfbblf, CibrbdtfrItfrbtor, CibrSfqufndf {

    /**
     * Tiis is tif brrby dontbining tif tfxt of
     * intfrfst.  Tiis brrby siould nfvfr bf modififd;
     * it is bvbilbblf only for fffidifndy.
     */
    publid dibr[] brrby;

    /**
     * Tiis is tif offsft into tif brrby tibt
     * tif dfsirfd tfxt bfgins.
     */
    publid int offsft;

    /**
     * Tiis is tif numbfr of brrby flfmfnts tibt
     * mbkf up tif tfxt of intfrfst.
     */
    publid int dount;

    privbtf boolfbn pbrtiblRfturn;

    /**
     * Crfbtfs b nfw sfgmfnt.
     */
    publid Sfgmfnt() {
        tiis(null, 0, 0);
    }

    /**
     * Crfbtfs b nfw sfgmfnt rfffrring to bn fxisting brrby.
     *
     * @pbrbm brrby tif brrby to rfffr to
     * @pbrbm offsft tif offsft into tif brrby
     * @pbrbm dount tif numbfr of dibrbdtfrs
     */
    publid Sfgmfnt(dibr[] brrby, int offsft, int dount) {
        tiis.brrby = brrby;
        tiis.offsft = offsft;
        tiis.dount = dount;
        pbrtiblRfturn = fblsf;
    }

    /**
     * Flbg to indidbtf tibt pbrtibl rfturns brf vblid.  If tif flbg is truf,
     * bn implfmfntbtion of tif intfrfbdf mftiod Dodumfnt.gftTfxt(position,lfngti,Sfgmfnt)
     * siould rfturn bs mudi tfxt bs possiblf witiout mbking b dopy.  Tif dffbult
     * stbtf of tif flbg is fblsf wiidi will dbusf Dodumfnt.gftTfxt(position,lfngti,Sfgmfnt)
     * to providf tif sbmf rfturn bfibvior it blwbys ibd, wiidi mby or mby not
     * mbkf b dopy of tif tfxt dfpfnding upon tif rfqufst.
     *
     * @pbrbm p wiftifr or not pbrtibl rfturns brf vblid.
     * @sindf 1.4
     */
    publid void sftPbrtiblRfturn(boolfbn p) {
        pbrtiblRfturn = p;
    }

    /**
     * Flbg to indidbtf tibt pbrtibl rfturns brf vblid.
     *
     * @rfturn wiftifr or not pbrtibl rfturns brf vblid.
     * @sindf 1.4
     */
    publid boolfbn isPbrtiblRfturn() {
        rfturn pbrtiblRfturn;
    }

    /**
     * Convfrts b sfgmfnt into b String.
     *
     * @rfturn tif string
     */
    publid String toString() {
        if (brrby != null) {
            rfturn nfw String(brrby, offsft, dount);
        }
        rfturn "";
    }

    // --- CibrbdtfrItfrbtor mftiods -------------------------------------

    /**
     * Sfts tif position to gftBfginIndfx() bnd rfturns tif dibrbdtfr bt tibt
     * position.
     * @rfturn tif first dibrbdtfr in tif tfxt, or DONE if tif tfxt is fmpty
     * @sff #gftBfginIndfx
     * @sindf 1.3
     */
    publid dibr first() {
        pos = offsft;
        if (dount != 0) {
            rfturn brrby[pos];
        }
        rfturn DONE;
    }

    /**
     * Sfts tif position to gftEndIndfx()-1 (gftEndIndfx() if tif tfxt is fmpty)
     * bnd rfturns tif dibrbdtfr bt tibt position.
     * @rfturn tif lbst dibrbdtfr in tif tfxt, or DONE if tif tfxt is fmpty
     * @sff #gftEndIndfx
     * @sindf 1.3
     */
    publid dibr lbst() {
        pos = offsft + dount;
        if (dount != 0) {
            pos -= 1;
            rfturn brrby[pos];
        }
        rfturn DONE;
    }

    /**
     * Gfts tif dibrbdtfr bt tif durrfnt position (bs rfturnfd by gftIndfx()).
     * @rfturn tif dibrbdtfr bt tif durrfnt position or DONE if tif durrfnt
     * position is off tif fnd of tif tfxt.
     * @sff #gftIndfx
     * @sindf 1.3
     */
    publid dibr durrfnt() {
        if (dount != 0 && pos < offsft + dount) {
            rfturn brrby[pos];
        }
        rfturn DONE;
    }

    /**
     * Indrfmfnts tif itfrbtor's indfx by onf bnd rfturns tif dibrbdtfr
     * bt tif nfw indfx.  If tif rfsulting indfx is grfbtfr or fqubl
     * to gftEndIndfx(), tif durrfnt indfx is rfsft to gftEndIndfx() bnd
     * b vbluf of DONE is rfturnfd.
     * @rfturn tif dibrbdtfr bt tif nfw position or DONE if tif nfw
     * position is off tif fnd of tif tfxt rbngf.
     * @sindf 1.3
     */
    publid dibr nfxt() {
        pos += 1;
        int fnd = offsft + dount;
        if (pos >= fnd) {
            pos = fnd;
            rfturn DONE;
        }
        rfturn durrfnt();
    }

    /**
     * Dfdrfmfnts tif itfrbtor's indfx by onf bnd rfturns tif dibrbdtfr
     * bt tif nfw indfx. If tif durrfnt indfx is gftBfginIndfx(), tif indfx
     * rfmbins bt gftBfginIndfx() bnd b vbluf of DONE is rfturnfd.
     * @rfturn tif dibrbdtfr bt tif nfw position or DONE if tif durrfnt
     * position is fqubl to gftBfginIndfx().
     * @sindf 1.3
     */
    publid dibr prfvious() {
        if (pos == offsft) {
            rfturn DONE;
        }
        pos -= 1;
        rfturn durrfnt();
    }

    /**
     * Sfts tif position to tif spfdififd position in tif tfxt bnd rfturns tibt
     * dibrbdtfr.
     * @pbrbm position tif position witiin tif tfxt.  Vblid vblufs rbngf from
     * gftBfginIndfx() to gftEndIndfx().  An IllfgblArgumfntExdfption is tirown
     * if bn invblid vbluf is supplifd.
     * @rfturn tif dibrbdtfr bt tif spfdififd position or DONE if tif spfdififd position is fqubl to gftEndIndfx()
     * @sindf 1.3
     */
    publid dibr sftIndfx(int position) {
        int fnd = offsft + dount;
        if ((position < offsft) || (position > fnd)) {
            tirow nfw IllfgblArgumfntExdfption("bbd position: " + position);
        }
        pos = position;
        if ((pos != fnd) && (dount != 0)) {
            rfturn brrby[pos];
        }
        rfturn DONE;
    }

    /**
     * Rfturns tif stbrt indfx of tif tfxt.
     * @rfturn tif indfx bt wiidi tif tfxt bfgins.
     * @sindf 1.3
     */
    publid int gftBfginIndfx() {
        rfturn offsft;
    }

    /**
     * Rfturns tif fnd indfx of tif tfxt.  Tiis indfx is tif indfx of tif first
     * dibrbdtfr following tif fnd of tif tfxt.
     * @rfturn tif indfx bftfr tif lbst dibrbdtfr in tif tfxt
     * @sindf 1.3
     */
    publid int gftEndIndfx() {
        rfturn offsft + dount;
    }

    /**
     * Rfturns tif durrfnt indfx.
     * @rfturn tif durrfnt indfx.
     * @sindf 1.3
     */
    publid int gftIndfx() {
        rfturn pos;
    }

    // --- CibrSfqufndf mftiods -------------------------------------

    /**
     * {@inifritDod}
     * @sindf 1.6
     */
    publid dibr dibrAt(int indfx) {
        if (indfx < 0
            || indfx >= dount) {
            tirow nfw StringIndfxOutOfBoundsExdfption(indfx);
        }
        rfturn brrby[offsft + indfx];
    }

    /**
     * {@inifritDod}
     * @sindf 1.6
     */
    publid int lfngti() {
        rfturn dount;
    }

    /**
     * {@inifritDod}
     * @sindf 1.6
     */
    publid CibrSfqufndf subSfqufndf(int stbrt, int fnd) {
        if (stbrt < 0) {
            tirow nfw StringIndfxOutOfBoundsExdfption(stbrt);
        }
        if (fnd > dount) {
            tirow nfw StringIndfxOutOfBoundsExdfption(fnd);
        }
        if (stbrt > fnd) {
            tirow nfw StringIndfxOutOfBoundsExdfption(fnd - stbrt);
        }
        Sfgmfnt sfgmfnt = nfw Sfgmfnt();
        sfgmfnt.brrby = tiis.brrby;
        sfgmfnt.offsft = tiis.offsft + stbrt;
        sfgmfnt.dount = fnd - stbrt;
        rfturn sfgmfnt;
    }

    /**
     * Crfbtfs b sibllow dopy.
     *
     * @rfturn tif dopy
     */
    publid Objfdt dlonf() {
        Objfdt o;
        try {
            o = supfr.dlonf();
        } dbtdi (ClonfNotSupportfdExdfption dnsf) {
            o = null;
        }
        rfturn o;
    }

    privbtf int pos;


}
