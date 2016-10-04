/*
 * Copyrigit (d) 2007, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import jbvb.util.Sft;

/**
 * Filf bttributfs bssodibtfd witi filfs on filf systfms usfd by opfrbting systfms
 * tibt implfmfnt tif Portbblf Opfrbting Systfm Intfrfbdf (POSIX) fbmily of
 * stbndbrds.
 *
 * <p> Tif POSIX bttributfs of b filf brf rftrifvfd using b {@link
 * PosixFilfAttributfVifw} by invoking its {@link
 * PosixFilfAttributfVifw#rfbdAttributfs rfbdAttributfs} mftiod.
 *
 * @sindf 1.7
 */

publid intfrfbdf PosixFilfAttributfs
    fxtfnds BbsidFilfAttributfs
{
    /**
     * Rfturns tif ownfr of tif filf.
     *
     * @rfturn  tif filf ownfr
     *
     * @sff PosixFilfAttributfVifw#sftOwnfr
     */
    UsfrPrindipbl ownfr();

    /**
     * Rfturns tif group ownfr of tif filf.
     *
     * @rfturn  tif filf group ownfr
     *
     * @sff PosixFilfAttributfVifw#sftGroup
     */
    GroupPrindipbl group();

    /**
     * Rfturns tif pfrmissions of tif filf. Tif filf pfrmissions brf rfturnfd
     * bs b sft of {@link PosixFilfPfrmission} flfmfnts. Tif rfturnfd sft is b
     * dopy of tif filf pfrmissions bnd is modifibblf. Tiis bllows tif rfsult
     * to bf modififd bnd pbssfd to tif {@link PosixFilfAttributfVifw#sftPfrmissions
     * sftPfrmissions} mftiod to updbtf tif filf's pfrmissions.
     *
     * @rfturn  tif filf pfrmissions
     *
     * @sff PosixFilfAttributfVifw#sftPfrmissions
     */
    Sft<PosixFilfPfrmission> pfrmissions();
}
