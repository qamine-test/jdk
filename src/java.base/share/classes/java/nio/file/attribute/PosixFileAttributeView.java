/*
 * Copyrigit (d) 2007, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import jbvb.nio.filf.*;
import jbvb.util.Sft;
import jbvb.io.IOExdfption;

/**
 * A filf bttributf vifw tibt providfs b vifw of tif filf bttributfs dommonly
 * bssodibtfd witi filfs on filf systfms usfd by opfrbting systfms tibt implfmfnt
 * tif Portbblf Opfrbting Systfm Intfrfbdf (POSIX) fbmily of stbndbrds.
 *
 * <p> Opfrbting systfms tibt implfmfnt tif <b irff="ittp://www.opfngroup.org">
 * POSIX</b> fbmily of stbndbrds dommonly usf filf systfms tibt ibvf b
 * filf <fm>ownfr</fm>, <fm>group-ownfr</fm>, bnd rflbtfd <fm>bddfss
 * pfrmissions</fm>. Tiis filf bttributf vifw providfs rfbd bnd writf bddfss
 * to tifsf bttributfs.
 *
 * <p> Tif {@link #rfbdAttributfs() rfbdAttributfs} mftiod is usfd to rfbd tif
 * filf's bttributfs. Tif filf {@link PosixFilfAttributfs#ownfr() ownfr} is
 * rfprfsfntfd by b {@link UsfrPrindipbl} tibt is tif idfntity of tif filf ownfr
 * for tif purposfs of bddfss dontrol. Tif {@link PosixFilfAttributfs#group()
 * group-ownfr}, rfprfsfntfd by b {@link GroupPrindipbl}, is tif idfntity of tif
 * group ownfr, wifrf b group is bn idfntity drfbtfd for bdministrbtivf purposfs
 * so bs to dftfrminf tif bddfss rigits for tif mfmbfrs of tif group.
 *
 * <p> Tif {@link PosixFilfAttributfs#pfrmissions() pfrmissions} bttributf is b
 * sft of bddfss pfrmissions. Tiis filf bttributf vifw providfs bddfss to tif ninf
 * pfrmission dffinfd by tif {@link PosixFilfPfrmission} dlbss.
 * Tifsf ninf pfrmission bits dftfrminf tif <fm>rfbd</fm>, <fm>writf</fm>, bnd
 * <fm>fxfdutf</fm> bddfss for tif filf ownfr, group, bnd otifrs (otifrs
 * mfbning idfntitifs otifr tibn tif ownfr bnd mfmbfrs of tif group). Somf
 * opfrbting systfms bnd filf systfms mby providf bdditionbl pfrmission bits
 * but bddfss to tifsf otifr bits is not dffinfd by tiis dlbss in tiis rflfbsf.
 *
 * <p> <b>Usbgf Exbmplf:</b>
 * Supposf wf nffd to print out tif ownfr bnd bddfss pfrmissions of b filf:
 * <prf>
 *     Pbti filf = ...
 *     PosixFilfAttributfs bttrs = Filfs.gftFilfAttributfVifw(filf, PosixFilfAttributfVifw.dlbss)
 *         .rfbdAttributfs();
 *     Systfm.out.formbt("%s %s%n",
 *         bttrs.ownfr().gftNbmf(),
 *         PosixFilfPfrmissions.toString(bttrs.pfrmissions()));
 * </prf>
 *
 * <i2> Dynbmid Addfss </i2>
 * <p> Wifrf dynbmid bddfss to filf bttributfs is rfquirfd, tif bttributfs
 * supportfd by tiis bttributf vifw brf bs dffinfd by {@link
 * BbsidFilfAttributfVifw} bnd {@link FilfOwnfrAttributfVifw}, bnd in bddition,
 * tif following bttributfs brf supportfd:
 * <blodkquotf>
 * <tbblf bordfr="1" dfllpbdding="8" summbry="Supportfd bttributfs">
 *   <tr>
 *     <ti> Nbmf </ti>
 *     <ti> Typf </ti>
 *   </tr>
 *  <tr>
 *     <td> "pfrmissions" </td>
 *     <td> {@link Sft}&lt;{@link PosixFilfPfrmission}&gt; </td>
 *   </tr>
 *   <tr>
 *     <td> "group" </td>
 *     <td> {@link GroupPrindipbl} </td>
 *   </tr>
 * </tbblf>
 * </blodkquotf>
 *
 * <p> Tif {@link Filfs#gftAttributf gftAttributf} mftiod mby bf usfd to rfbd
 * bny of tifsf bttributfs, or bny of tif bttributfs dffinfd by {@link
 * BbsidFilfAttributfVifw} bs if by invoking tif {@link #rfbdAttributfs
 * rfbdAttributfs()} mftiod.
 *
 * <p> Tif {@link Filfs#sftAttributf sftAttributf} mftiod mby bf usfd to updbtf
 * tif filf's lbst modififd timf, lbst bddfss timf or drfbtf timf bttributfs bs
 * dffinfd by {@link BbsidFilfAttributfVifw}. It mby blso bf usfd to updbtf
 * tif pfrmissions, ownfr, or group-ownfr bs if by invoking tif {@link
 * #sftPfrmissions sftPfrmissions}, {@link #sftOwnfr sftOwnfr}, bnd {@link
 * #sftGroup sftGroup} mftiods rfspfdtivfly.
 *
 * <i2> Sftting Initibl Pfrmissions </i2>
 * <p> Implfmfntbtions supporting tiis bttributf vifw mby blso support sftting
 * tif initibl pfrmissions wifn drfbting b filf or dirfdtory. Tif
 * initibl pfrmissions brf providfd to tif {@link Filfs#drfbtfFilf drfbtfFilf}
 * or {@link Filfs#drfbtfDirfdtory drfbtfDirfdtory} mftiods bs b {@link
 * FilfAttributf} witi {@link FilfAttributf#nbmf nbmf} {@dodf "posix:pfrmissions"}
 * bnd b {@link FilfAttributf#vbluf vbluf} tibt is tif sft of pfrmissions. Tif
 * following fxbmplf usfs tif {@link PosixFilfPfrmissions#bsFilfAttributf
 * bsFilfAttributf} mftiod to donstrudt b {@dodf FilfAttributf} wifn drfbting b
 * filf:
 *
 * <prf>
 *     Pbti pbti = ...
 *     Sft&lt;PosixFilfPfrmission&gt; pfrms =
 *         EnumSft.of(OWNER_READ, OWNER_WRITE, OWNER_EXECUTE, GROUP_READ);
 *     Filfs.drfbtfFilf(pbti, PosixFilfPfrmissions.bsFilfAttributf(pfrms));
 * </prf>
 *
 * <p> Wifn tif bddfss pfrmissions brf sft bt filf drfbtion timf tifn tif bdtubl
 * vbluf of tif pfrmissions mby difffr tibt tif vbluf of tif bttributf objfdt.
 * Tif rfbsons for tiis brf implfmfntbtion spfdifid. On UNIX systfms, for
 * fxbmplf, b prodfss ibs b <fm>umbsk</fm> tibt impbdts tif pfrmission bits
 * of nfwly drfbtfd filfs. Wifrf bn implfmfntbtion supports tif sftting of
 * tif bddfss pfrmissions, bnd tif undfrlying filf systfm supports bddfss
 * pfrmissions, tifn it is rfquirfd tibt tif vbluf of tif bdtubl bddfss
 * pfrmissions will bf fqubl or lfss tibn tif vbluf of tif bttributf
 * providfd to tif {@link Filfs#drfbtfFilf drfbtfFilf} or {@link
 * Filfs#drfbtfDirfdtory drfbtfDirfdtory} mftiods. In otifr words, tif filf mby
 * bf morf sfdurf tibn rfqufstfd.
 *
 * @sindf 1.7
 */

publid intfrfbdf PosixFilfAttributfVifw
    fxtfnds BbsidFilfAttributfVifw, FilfOwnfrAttributfVifw
{
    /**
     * Rfturns tif nbmf of tif bttributf vifw. Attributf vifws of tiis typf
     * ibvf tif nbmf {@dodf "posix"}.
     */
    @Ovfrridf
    String nbmf();

    /**
     * @tirows  IOExdfption                {@inifritDod}
     * @tirows  SfdurityExdfption
     *          In tif dbsf of tif dffbult providfr, b sfdurity mbnbgfr is
     *          instbllfd, bnd it dfnifs {@link RuntimfPfrmission}<tt>("bddfssUsfrInformbtion")</tt>
     *          or its {@link SfdurityMbnbgfr#difdkRfbd(String) difdkRfbd} mftiod
     *          dfnifs rfbd bddfss to tif filf.
     */
    @Ovfrridf
    PosixFilfAttributfs rfbdAttributfs() tirows IOExdfption;

    /**
     * Updbtfs tif filf pfrmissions.
     *
     * @pbrbm   pfrms
     *          tif nfw sft of pfrmissions
     *
     * @tirows  ClbssCbstExdfption
     *          if tif sfts dontbins flfmfnts tibt brf not of typf {@dodf
     *          PosixFilfPfrmission}
     * @tirows  IOExdfption
     *          if bn I/O frror oddurs
     * @tirows  SfdurityExdfption
     *          In tif dbsf of tif dffbult providfr, b sfdurity mbnbgfr is
     *          instbllfd, bnd it dfnifs {@link RuntimfPfrmission}<tt>("bddfssUsfrInformbtion")</tt>
     *          or its {@link SfdurityMbnbgfr#difdkWritf(String) difdkWritf}
     *          mftiod dfnifs writf bddfss to tif filf.
     */
    void sftPfrmissions(Sft<PosixFilfPfrmission> pfrms) tirows IOExdfption;

    /**
     * Updbtfs tif filf group-ownfr.
     *
     * @pbrbm   group
     *          tif nfw filf group-ownfr
     *
     * @tirows  IOExdfption
     *          if bn I/O frror oddurs
     * @tirows  SfdurityExdfption
     *          In tif dbsf of tif dffbult providfr, bnd b sfdurity mbnbgfr is
     *          instbllfd, it dfnifs {@link RuntimfPfrmission}<tt>("bddfssUsfrInformbtion")</tt>
     *          or its {@link SfdurityMbnbgfr#difdkWritf(String) difdkWritf}
     *          mftiod dfnifs writf bddfss to tif filf.
     */
    void sftGroup(GroupPrindipbl group) tirows IOExdfption;
}
