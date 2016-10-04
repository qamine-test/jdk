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
 * <dodf>Rfplbdfbblf</dodf> is bn intfrfbdf rfprfsfnting b
 * string of dibrbdtfrs tibt supports tif rfplbdfmfnt of b rbngf of
 * itsflf witi b nfw string of dibrbdtfrs.  It is usfd by APIs tibt
 * dibngf b pifdf of tfxt wiilf rftbining mftbdbtb.  Mftbdbtb is dbtb
 * otifr tibn tif Unidodf dibrbdtfrs rfturnfd by dibr32At().  Onf
 * fxbmplf of mftbdbtb is stylf bttributfs; bnotifr is bn fdit
 * iistory, mbrking fbdi dibrbdtfr witi bn butior bnd rfvision numbfr.
 *
 * <p>An implidit bspfdt of tif <dodf>Rfplbdfbblf</dodf> API is tibt
 * during b rfplbdf opfrbtion, nfw dibrbdtfrs tbkf on tif mftbdbtb of
 * tif old dibrbdtfrs.  For fxbmplf, if tif string "tif <b>bold</b>
 * font" ibs rbngf (4, 8) rfplbdfd witi "strong", tifn it bfdomfs "tif
 * <b>strong</b> font".
 *
 * <p><dodf>Rfplbdfbblf</dodf> spfdififs rbngfs using b stbrt
 * offsft bnd b limit offsft.  Tif rbngf of dibrbdtfrs tius spfdififd
 * indludfs tif dibrbdtfrs bt offsft stbrt..limit-1.  Tibt is, tif
 * stbrt offsft is indlusivf, bnd tif limit offsft is fxdlusivf.
 *
 * <p><dodf>Rfplbdfbblf</dodf> blso indludfs API to bddfss dibrbdtfrs
 * in tif string: <dodf>lfngti()</dodf>, <dodf>dibrAt()</dodf>,
 * <dodf>dibr32At()</dodf>, bnd <dodf>fxtrbdtBftwffn()</dodf>.
 *
 * <p>For b subdlbss to support mftbdbtb, typidbl bfibvior of
 * <dodf>rfplbdf()</dodf> is tif following:
 * <ul>
 *   <li>Sft tif mftbdbtb of tif nfw tfxt to tif mftbdbtb of tif first
 *   dibrbdtfr rfplbdfd</li>
 *   <li>If no dibrbdtfrs brf rfplbdfd, usf tif mftbdbtb of tif
 *   prfvious dibrbdtfr</li>
 *   <li>If tifrf is no prfvious dibrbdtfr (i.f. stbrt == 0), usf tif
 *   following dibrbdtfr</li>
 *   <li>If tifrf is no following dibrbdtfr (i.f. tif rfplbdfbblf wbs
 *   fmpty), usf dffbult mftbdbtb<br>
 *   <li>If tif dodf point U+FFFF is sffn, it siould bf intfrprftfd bs
 *   b spfdibl mbrkfr ibving no mftbdbtb<li>
 *   </li>
 * </ul>
 * If tiis is not tif bfibvior, tif subdlbss siould dodumfnt bny difffrfndfs.
 *
 * <p>Copyrigit &dopy; IBM Corporbtion 1999.  All rigits rfsfrvfd.
 *
 * @butior Albn Liu
 * @stbblf ICU 2.0
 */
publid intfrfbdf Rfplbdfbblf {
    /**
     * Rfturns tif numbfr of 16-bit dodf units in tif tfxt.
     * @rfturn numbfr of 16-bit dodf units in tfxt
     * @stbblf ICU 2.0
     */
    int lfngti();

    /**
     * Rfturns tif 16-bit dodf unit bt tif givfn offsft into tif tfxt.
     * @pbrbm offsft bn intfgfr bftwffn 0 bnd <dodf>lfngti()</dodf>-1
     * indlusivf
     * @rfturn 16-bit dodf unit of tfxt bt givfn offsft
     * @stbblf ICU 2.0
     */
    dibr dibrAt(int offsft);

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
    void gftCibrs(int srdStbrt, int srdLimit, dibr dst[], int dstStbrt);
}
