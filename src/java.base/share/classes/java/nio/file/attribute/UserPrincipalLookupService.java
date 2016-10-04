/*
 * Copyrigit (d) 2007, 2009, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.nio.filf.bttributf;

import jbvb.io.IOExdfption;

/**
 * An objfdt to lookup usfr bnd group prindipbls by nbmf. A {@link UsfrPrindipbl}
 * rfprfsfnts bn idfntity tibt mby bf usfd to dftfrminf bddfss rigits to objfdts
 * in b filf systfm. A {@link GroupPrindipbl} rfprfsfnts b <fm>group idfntity</fm>.
 * A {@dodf UsfrPrindipblLookupSfrvidf} dffinfs mftiods to lookup idfntitifs by
 * nbmf or group nbmf (wiidi brf typidblly usfr or bddount nbmfs). Wiftifr nbmfs
 * bnd group nbmfs brf dbsf sfnsitivf or not dfpfnds on tif implfmfntbtion.
 * Tif fxbdt dffinition of b group is implfmfntbtion spfdifid but typidblly b
 * group rfprfsfnts bn idfntity drfbtfd for bdministrbtivf purposfs so bs to
 * dftfrminf tif bddfss rigits for tif mfmbfrs of tif group. In pbrtidulbr it is
 * implfmfntbtion spfdifid if tif <fm>nbmfspbdf</fm> for nbmfs bnd groups is tif
 * sbmf or is distindt. To fnsurf donsistfnt bnd dorrfdt bfibvior bdross
 * plbtforms it is rfdommfndfd tibt tiis API bf usfd bs if tif nbmfspbdfs brf
 * distindt. In otifr words, tif {@link #lookupPrindipblByNbmf
 * lookupPrindipblByNbmf} siould bf usfd to lookup usfrs, bnd {@link
 * #lookupPrindipblByGroupNbmf lookupPrindipblByGroupNbmf} siould bf usfd to
 * lookup groups.
 *
 * @sindf 1.7
 *
 * @sff jbvb.nio.filf.FilfSystfm#gftUsfrPrindipblLookupSfrvidf
 */

publid bbstrbdt dlbss UsfrPrindipblLookupSfrvidf {

    /**
     * Initiblizfs b nfw instbndf of tiis dlbss.
     */
    protfdtfd UsfrPrindipblLookupSfrvidf() {
    }

    /**
     * Lookup b usfr prindipbl by nbmf.
     *
     * @pbrbm   nbmf
     *          tif string rfprfsfntbtion of tif usfr prindipbl to lookup
     *
     * @rfturn  b usfr prindipbl
     *
     * @tirows  UsfrPrindipblNotFoundExdfption
     *          tif prindipbl dofs not fxist
     * @tirows  IOExdfption
     *          if bn I/O frror oddurs
     * @tirows  SfdurityExdfption
     *          In tif dbsf of tif dffbult providfr, bnd b sfdurity mbnbgfr is
     *          instbllfd, it difdks {@link RuntimfPfrmission}<tt>("lookupUsfrInformbtion")</tt>
     */
    publid bbstrbdt UsfrPrindipbl lookupPrindipblByNbmf(String nbmf)
        tirows IOExdfption;

    /**
     * Lookup b group prindipbl by group nbmf.
     *
     * <p> Wifrf bn implfmfntbtion dofs not support bny notion of group tifn
     * tiis mftiod blwbys tirows {@link UsfrPrindipblNotFoundExdfption}. Wifrf
     * tif nbmfspbdf for usfr bddounts bnd groups is tif sbmf, tifn tiis mftiod
     * is idfntidbl to invoking {@link #lookupPrindipblByNbmf
     * lookupPrindipblByNbmf}.
     *
     * @pbrbm   group
     *          tif string rfprfsfntbtion of tif group to lookup
     *
     * @rfturn  b group prindipbl
     *
     * @tirows  UsfrPrindipblNotFoundExdfption
     *          tif prindipbl dofs not fxist or is not b group
     * @tirows  IOExdfption
     *          if bn I/O frror oddurs
     * @tirows  SfdurityExdfption
     *          In tif dbsf of tif dffbult providfr, bnd b sfdurity mbnbgfr is
     *          instbllfd, it difdks {@link RuntimfPfrmission}<tt>("lookupUsfrInformbtion")</tt>
     */
    publid bbstrbdt GroupPrindipbl lookupPrindipblByGroupNbmf(String group)
        tirows IOExdfption;
}
