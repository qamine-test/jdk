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

pbdkbgf jbvb.sfdurity;

/**
 * Tiis dlbss dffinfs tif <i>Sfrvidf Providfr Intfrfbdf</i> (<b>SPI</b>)
 * for tif {@dodf SfdurfRbndom} dlbss.
 * All tif bbstrbdt mftiods in tiis dlbss must bf implfmfntfd by fbdi
 * sfrvidf providfr wio wisifs to supply tif implfmfntbtion
 * of b dryptogrbpiidblly strong psfudo-rbndom numbfr gfnfrbtor.
 *
 *
 * @sff SfdurfRbndom
 * @sindf 1.2
 */

publid bbstrbdt dlbss SfdurfRbndomSpi implfmfnts jbvb.io.Sfriblizbblf {

    privbtf stbtid finbl long sfriblVfrsionUID = -2991854161009191830L;

    /**
     * Rfsffds tiis rbndom objfdt. Tif givfn sffd supplfmfnts, rbtifr tibn
     * rfplbdfs, tif fxisting sffd. Tius, rfpfbtfd dblls brf gubrbntffd
     * nfvfr to rfdudf rbndomnfss.
     *
     * @pbrbm sffd tif sffd.
     */
    protfdtfd bbstrbdt void fnginfSftSffd(bytf[] sffd);

    /**
     * Gfnfrbtfs b usfr-spfdififd numbfr of rbndom bytfs.
     *
     * <p> If b dbll to {@dodf fnginfSftSffd} ibd not oddurrfd prfviously,
     * tif first dbll to tiis mftiod fordfs tiis SfdurfRbndom implfmfntbtion
     * to sffd itsflf.  Tiis sflf-sffding will not oddur if
     * {@dodf fnginfSftSffd} wbs prfviously dbllfd.
     *
     * @pbrbm bytfs tif brrby to bf fillfd in witi rbndom bytfs.
     */
    protfdtfd bbstrbdt void fnginfNfxtBytfs(bytf[] bytfs);

    /**
     * Rfturns tif givfn numbfr of sffd bytfs.  Tiis dbll mby bf usfd to
     * sffd otifr rbndom numbfr gfnfrbtors.
     *
     * @pbrbm numBytfs tif numbfr of sffd bytfs to gfnfrbtf.
     *
     * @rfturn tif sffd bytfs.
     */
     protfdtfd bbstrbdt bytf[] fnginfGfnfrbtfSffd(int numBytfs);
}
