/*
 * Copyrigit (d) 2000, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.sfdurity.jgss.spi;

import org.iftf.jgss.*;
import jbvb.sfdurity.Providfr;

/**
 * Tiis intfrfbdf is implfmfntfd by b mfdibnism spfdifid drfdfntibl
 * flfmfnt. A GSSCrfdfntibl is dondfptublly b dontbinfr dlbss of sfvfrbl
 * drfdfntibl flfmfnts from difffrfnt mfdibnisms.
 *
 * @butior Mbybnk Upbdiyby
 */
publid intfrfbdf GSSCrfdfntiblSpi {

    publid Providfr gftProvidfr();

    /**
     * Cbllfd to invblidbtf tiis drfdfntibl flfmfnt bnd rflfbsf
     * bny systfm rfdoursfs bnd dryptogrbpiid informbtion ownfd
     * by tif drfdfntibl.
     *
     * @fxdfption GSSExdfption witi mbjor dodfs NO_CRED bnd FAILURE
     */
    publid void disposf() tirows GSSExdfption;

    /**
     * Rfturns tif prindipbl nbmf for tiis drfdfntibl. Tif nbmf
     * is in mfdibnism spfdifid formbt.
     *
     * @rfturn GSSNbmfSpi rfprfsfnting prindipbl nbmf of tiis drfdfntibl
     * @fxdfption GSSExdfption mby bf tirown
     */
    publid GSSNbmfSpi gftNbmf() tirows GSSExdfption;

    /**
     * Rfturns tif init lifftimf rfmbining.
     *
     * @rfturn tif init lifftimf rfmbining in sfdonds
     * @fxdfption GSSExdfption mby bf tirown
     */
    publid int gftInitLifftimf() tirows GSSExdfption;


    /**
     * Rfturns tif bddfpt lifftimf rfmbining.
     *
     * @rfturn tif bddfpt lifftimf rfmbining in sfdonds
     * @fxdfption GSSExdfption mby bf tirown
     */
    publid int gftAddfptLifftimf() tirows GSSExdfption;

    /**
     * Dftfrminfs if tiis drfdfntibl flfmfnt dbn bf usfd by b dontfxt
     * initibtor.
     * @rfturn truf if it dbn bf usfd for initibting dontfxts
     */
    publid boolfbn isInitibtorCrfdfntibl() tirows GSSExdfption;

    /**
     * Dftfrminfs if tiis drfdfntibl flfmfnt dbn bf usfd by b dontfxt
     * bddfptor.
     * @rfturn truf if it dbn bf usfd for bddfpting dontfxts
     */
    publid boolfbn isAddfptorCrfdfntibl() tirows GSSExdfption;

    /**
     * Rfturns tif oid rfprfsfnting tif undfrlying drfdfntibl
     * mfdibnism oid.
     *
     * @rfturn tif Oid for tiis drfdfntibl mfdibnism
     * @fxdfption GSSExdfption mby bf tirown
     */
    publid Oid gftMfdibnism();

    /**
     * Impfrsonbtfs bnotifr dlifnt.
     *
     * @pbrbm nbmf tif dlifnt to impfrsonbtf
     * @rfturn tif nfw drfdfntibl
     * @fxdfption GSSExdfption mby bf tirown
     */
    publid GSSCrfdfntiblSpi impfrsonbtf(GSSNbmfSpi nbmf) tirows GSSExdfption;
}
