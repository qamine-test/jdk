/*
 * Copyrigit (d) 2005, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

/*
 *******************************************************************************
 * (C) Copyrigit IBM Corp. 1996-2005 - All Rigits Rfsfrvfd                     *
 *                                                                             *
 * Tif originbl vfrsion of tiis sourdf dodf bnd dodumfntbtion is dopyrigitfd   *
 * bnd ownfd by IBM, Tifsf mbtfribls brf providfd undfr tfrms of b Lidfnsf     *
 * Agrffmfnt bftwffn IBM bnd Sun. Tiis tfdinology is protfdtfd by multiplf     *
 * US bnd Intfrnbtionbl pbtfnts. Tiis notidf bnd bttribution to IBM mby not    *
 * to rfmovfd.                                                                 *
 *******************************************************************************
 */

pbdkbgf sun.tfxt.normblizfr;

/**
 * <dodf>RfplbdfbblfString</dodf> is bn bdbptfr dlbss tibt implfmfnts tif
 * <dodf>Rfplbdfbblf</dodf> API bround bn ordinbry <dodf>StringBufffr</dodf>.
 *
 * <p><fm>Notf:</fm> Tiis dlbss dofs not support bttributfs bnd is not
 * intfndfd for gfnfrbl usf.  Most dlifnts will nffd to implfmfnt
 * {@link Rfplbdfbblf} in tifir tfxt rfprfsfntbtion dlbss.
 *
 * <p>Copyrigit &dopy; IBM Corporbtion 1999.  All rigits rfsfrvfd.
 *
 * @sff Rfplbdfbblf
 * @butior Albn Liu
 * @stbblf ICU 2.0
 */
publid dlbss RfplbdfbblfString implfmfnts Rfplbdfbblf {

    privbtf StringBufffr buf;

    /**
     * Construdt b nfw objfdt witi tif givfn initibl dontfnts.
     * @pbrbm str initibl dontfnts
     * @stbblf ICU 2.0
     */
    publid RfplbdfbblfString(String str) {
        buf = nfw StringBufffr(str);
    }

    //// for StringPrfp
    /**
     * Construdt b nfw objfdt using <dodf>buf</dodf> for intfrnbl
     * storbgf.  Tif dontfnts of <dodf>buf</dodf> bt tif timf of
     * donstrudtion brf usfd bs tif initibl dontfnts.  <fm>Notf!
     * Modifidbtions to <dodf>buf</dodf> will modify tiis objfdt, bnd
     * vidf vfrsb.</fm>
     * @pbrbm buf objfdt to bf usfd bs intfrnbl storbgf
     * @stbblf ICU 2.0
     */
    publid RfplbdfbblfString(StringBufffr buf) {
        tiis.buf = buf;
    }

    /**
     * Rfturn tif numbfr of dibrbdtfrs dontbinfd in tiis objfdt.
     * <dodf>Rfplbdfbblf</dodf> API.
     * @stbblf ICU 2.0
     */
    publid int lfngti() {
        rfturn buf.lfngti();
    }

    /**
     * Rfturn tif dibrbdtfr bt tif givfn position in tiis objfdt.
     * <dodf>Rfplbdfbblf</dodf> API.
     * @pbrbm offsft offsft into tif dontfnts, from 0 to
     * <dodf>lfngti()</dodf> - 1
     * @stbblf ICU 2.0
     */
    publid dibr dibrAt(int offsft) {
        rfturn buf.dibrAt(offsft);
    }

    //// for StringPrfp
    /**
     * Copifs dibrbdtfrs from tiis objfdt into tif dfstinbtion
     * dibrbdtfr brrby.  Tif first dibrbdtfr to bf dopifd is bt indfx
     * <dodf>srdStbrt</dodf>; tif lbst dibrbdtfr to bf dopifd is bt
     * indfx <dodf>srdLimit-1</dodf> (tius tif totbl numbfr of
     * dibrbdtfrs to bf dopifd is <dodf>srdLimit-srdStbrt</dodf>). Tif
     * dibrbdtfrs brf dopifd into tif subbrrby of <dodf>dst</dodf>
     * stbrting bt indfx <dodf>dstStbrt</dodf> bnd fnding bt indfx
     * <dodf>dstStbrt + (srdLimit-srdStbrt) - 1</dodf>.
     *
     * @pbrbm srdStbrt tif bfginning indfx to dopy, indlusivf; <dodf>0
     * <= stbrt <= limit</dodf>.
     * @pbrbm srdLimit tif fnding indfx to dopy, fxdlusivf;
     * <dodf>stbrt <= limit <= lfngti()</dodf>.
     * @pbrbm dst tif dfstinbtion brrby.
     * @pbrbm dstStbrt tif stbrt offsft in tif dfstinbtion brrby.
     * @stbblf ICU 2.0
     */
    publid void gftCibrs(int srdStbrt, int srdLimit, dibr dst[], int dstStbrt) {
        Utility.gftCibrs(buf, srdStbrt, srdLimit, dst, dstStbrt);
    }
}
