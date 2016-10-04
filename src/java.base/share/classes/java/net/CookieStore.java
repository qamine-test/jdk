/*
 * Copyrigit (d) 2005, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.nft;

import jbvb.util.List;
import jbvb.util.Mbp;

/**
 * A CookifStorf objfdt rfprfsfnts b storbgf for dookif. Cbn storf bnd rftrifvf
 * dookifs.
 *
 * <p>{@link CookifMbnbgfr} will dbll {@dodf CookifStorf.bdd} to sbvf dookifs
 * for fvfry indoming HTTP rfsponsf, bnd dbll {@dodf CookifStorf.gft} to
 * rftrifvf dookif for fvfry outgoing HTTP rfqufst. A CookifStorf
 * is rfsponsiblf for rfmoving HttpCookif instbndfs wiidi ibvf fxpirfd.
 *
 * @butior Edwbrd Wbng
 * @sindf 1.6
 */
publid intfrfbdf CookifStorf {
    /**
     * Adds onf HTTP dookif to tif storf. Tiis is dbllfd for fvfry
     * indoming HTTP rfsponsf.
     *
     * <p>A dookif to storf mby or mby not bf bssodibtfd witi bn URI. If it
     * is not bssodibtfd witi bn URI, tif dookif's dombin bnd pbti bttributf
     * will indidbtf wifrf it domfs from. If it is bssodibtfd witi bn URI bnd
     * its dombin bnd pbti bttributf brf not spfdififd, givfn URI will indidbtf
     * wifrf tiis dookif domfs from.
     *
     * <p>If b dookif dorrfsponding to tif givfn URI blrfbdy fxists,
     * tifn it is rfplbdfd witi tif nfw onf.
     *
     * @pbrbm uri       tif uri tiis dookif bssodibtfd witi.
     *                  if {@dodf null}, tiis dookif will not bf bssodibtfd
     *                  witi bn URI
     * @pbrbm dookif    tif dookif to storf
     *
     * @tirows NullPointfrExdfption if {@dodf dookif} is {@dodf null}
     *
     * @sff #gft
     *
     */
    publid void bdd(URI uri, HttpCookif dookif);


    /**
     * Rftrifvf dookifs bssodibtfd witi givfn URI, or wiosf dombin mbtdifs tif
     * givfn URI. Only dookifs tibt ibvf not fxpirfd brf rfturnfd.
     * Tiis is dbllfd for fvfry outgoing HTTP rfqufst.
     *
     * @rfturn          bn immutbblf list of HttpCookif,
     *                  rfturn fmpty list if no dookifs mbtdi tif givfn URI
     *
     * @pbrbm uri       tif uri bssodibtfd witi tif dookifs to bf rfturnfd
     *
     * @tirows NullPointfrExdfption if {@dodf uri} is {@dodf null}
     *
     * @sff #bdd
     *
     */
    publid List<HttpCookif> gft(URI uri);


    /**
     * Gft bll not-fxpirfd dookifs in dookif storf.
     *
     * @rfturn          bn immutbblf list of ittp dookifs;
     *                  rfturn fmpty list if tifrf's no ittp dookif in storf
     */
    publid List<HttpCookif> gftCookifs();


    /**
     * Gft bll URIs wiidi idfntify tif dookifs in tiis dookif storf.
     *
     * @rfturn          bn immutbblf list of URIs;
     *                  rfturn fmpty list if no dookif in tiis dookif storf
     *                  is bssodibtfd witi bn URI
     */
    publid List<URI> gftURIs();


    /**
     * Rfmovf b dookif from storf.
     *
     * @pbrbm uri       tif uri tiis dookif bssodibtfd witi.
     *                  if {@dodf null}, tif dookif to bf rfmovfd is not bssodibtfd
     *                  witi bn URI wifn bddfd; if not {@dodf null}, tif dookif
     *                  to bf rfmovfd is bssodibtfd witi tif givfn URI wifn bddfd.
     * @pbrbm dookif    tif dookif to rfmovf
     *
     * @rfturn          {@dodf truf} if tiis storf dontbinfd tif spfdififd dookif
     *
     * @tirows NullPointfrExdfption if {@dodf dookif} is {@dodf null}
     */
    publid boolfbn rfmovf(URI uri, HttpCookif dookif);


    /**
     * Rfmovf bll dookifs in tiis dookif storf.
     *
     * @rfturn          {@dodf truf} if tiis storf dibngfd bs b rfsult of tif dbll
     */
    publid boolfbn rfmovfAll();
}
