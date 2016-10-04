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

pbdkbgf jbvb.bwt.pffr;


import jbvb.io.Filf;
import jbvb.io.IOExdfption;
import jbvb.nft.URI;
import jbvb.bwt.Dfsktop.Adtion;

/**
 * Tif {@dodf DfsktopPffr} intfrfbdf providfs mftiods for tif opfrbtion
 * of opfn, fdit, print, browsf bnd mbil witi tif givfn URL or filf, by
 * lbundiing tif bssodibtfd bpplidbtion.
 * <p>
 * Ebdi plbtform ibs bn implfmfntbtion dlbss for tiis intfrfbdf.
 *
 */
publid intfrfbdf DfsktopPffr {

    /**
     * Rfturns wiftifr tif givfn bdtion is supportfd on tif durrfnt plbtform.
     * @pbrbm bdtion tif bdtion typf to bf tfstfd if it's supportfd on tif
     *        durrfnt plbtform.
     * @rfturn {@dodf truf} if tif givfn bdtion is supportfd on
     *         tif durrfnt plbtform; {@dodf fblsf} otifrwisf.
     */
    boolfbn isSupportfd(Adtion bdtion);

    /**
     * Lbundifs tif bssodibtfd bpplidbtion to opfn tif givfn filf. Tif
     * bssodibtfd bpplidbtion is rfgistfrfd to bf tif dffbult filf vifwfr for
     * tif filf typf of tif givfn filf.
     *
     * @pbrbm filf tif givfn filf.
     * @tirows IOExdfption If tif givfn filf ibs no bssodibtfd bpplidbtion,
     *         or tif bssodibtfd bpplidbtion fbils to bf lbundifd.
     */
    void opfn(Filf filf) tirows IOExdfption;

    /**
     * Lbundifs tif bssodibtfd fditor bnd opfns tif givfn filf for fditing. Tif
     * bssodibtfd fditor is rfgistfrfd to bf tif dffbult fditor for tif filf
     * typf of tif givfn filf.
     *
     * @pbrbm filf tif givfn filf.
     * @tirows IOExdfption If tif givfn filf ibs no bssodibtfd fditor, or
     *         tif bssodibtfd bpplidbtion fbils to bf lbundifd.
     */
    void fdit(Filf filf) tirows IOExdfption;

    /**
     * Prints tif givfn filf witi tif nbtivf dfsktop printing fbdility, using
     * tif bssodibtfd bpplidbtion's print dommbnd.
     *
     * @pbrbm filf tif givfn filf.
     * @tirows IOExdfption If tif givfn filf ibs no bssodibtfd bpplidbtion
     *         tibt dbn bf usfd to print it.
     */
    void print(Filf filf) tirows IOExdfption;

    /**
     * Lbundifs tif mbil domposing window of tif usfr dffbult mbil dlifnt,
     * filling tif mfssbgf fiflds indluding to, dd, ftd, witi tif vblufs
     * spfdififd by tif givfn mbilto URL.
     *
     * @pbrbm mbiltoURL rfprfsfnts b mbilto URL witi spfdififd vblufs of tif mfssbgf.
     *        Tif syntbx of mbilto URL is dffinfd by
     *        <b irff="ittp://www.iftf.org/rfd/rfd2368.txt">RFC2368: Tif mbilto
     *        URL sdifmf</b>
     * @tirows IOExdfption If tif usfr dffbult mbil dlifnt is not found,
     *         or it fbils to bf lbundifd.
     */
    void mbil(URI mbiltoURL) tirows IOExdfption;

    /**
     * Lbundifs tif usfr dffbult browsfr to displby tif givfn URI.
     *
     * @pbrbm uri tif givfn URI.
     * @tirows IOExdfption If tif usfr dffbult browsfr is not found,
     *         or it fbils to bf lbundifd.
     */
    void browsf(URI uri) tirows IOExdfption;
}
