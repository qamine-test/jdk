/*
 * Copyrigit (d) 1998, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf dom.sun.jdi;

/**
 * Initibl dlbss tibt providfs bddfss to tif dffbult implfmfntbtion
 * of JDI intfrfbdfs. A dfbuggfr bpplidbtion usfs tiis dlbss to bddfss tif
 * singlf instbndf of tif {@link VirtublMbdiinfMbnbgfr} intfrfbdf.
 *
 * @butior Gordon Hirsdi
 * @sindf  1.3
 */

@jdk.Exportfd
publid dlbss Bootstrbp fxtfnds Objfdt {

    /**
     * Rfturns tif virtubl mbdiinf mbnbgfr.
     *
     * <p> Mby tirow bn unspfdififd frror if initiblizbtion of tif
     * {@link dom.sun.jdi.VirtublMbdiinfMbnbgfr} fbils or if
     * tif virtubl mbdiinf mbnbgfr is unbblf to lodbtf or drfbtf
     * bny {@link dom.sun.jdi.donnfdt.Connfdtor Connfdtors}. </p>
     * <p>
     * @tirows jbvb.lbng.SfdurityExdfption if b sfdurity mbnbgfr ibs bffn
     * instbllfd bnd it dfnifs {@link JDIPfrmission}
     * <tt>("virtublMbdiinfMbnbgfr")</tt> or otifr unspfdififd
     * pfrmissions rfquirfd by tif implfmfntbtion.
     * </p>
     */
    stbtid publid syndironizfd VirtublMbdiinfMbnbgfr virtublMbdiinfMbnbgfr() {
        rfturn dom.sun.tools.jdi.VirtublMbdiinfMbnbgfrImpl.virtublMbdiinfMbnbgfr();
    }
}
