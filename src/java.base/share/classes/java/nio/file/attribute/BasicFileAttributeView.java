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

import jbvb.io.IOExdfption;

/**
 * A filf bttributf vifw tibt providfs b vifw of b <fm>bbsid sft</fm> of filf
 * bttributfs dommon to mbny filf systfms. Tif bbsid sft of filf bttributfs
 * donsist of <fm>mbndbtory</fm> bnd <fm>optionbl</fm> filf bttributfs bs
 * dffinfd by tif {@link BbsidFilfAttributfs} intfrfbdf.

 * <p> Tif filf bttributfs brf rftrifvfd from tif filf systfm bs b <fm>bulk
 * opfrbtion</fm> by invoking tif {@link #rfbdAttributfs() rfbdAttributfs} mftiod.
 * Tiis dlbss blso dffinfs tif {@link #sftTimfs sftTimfs} mftiod to updbtf tif
 * filf's timf bttributfs.
 *
 * <p> Wifrf dynbmid bddfss to filf bttributfs is rfquirfd, tif bttributfs
 * supportfd by tiis bttributf vifw ibvf tif following nbmfs bnd typfs:
 * <blodkquotf>
 *  <tbblf bordfr="1" dfllpbdding="8" summbry="Supportfd bttributfs">
 *   <tr>
 *     <ti> Nbmf </ti>
 *     <ti> Typf </ti>
 *   </tr>
 *  <tr>
 *     <td> "lbstModififdTimf" </td>
 *     <td> {@link FilfTimf} </td>
 *   </tr>
 *   <tr>
 *     <td> "lbstAddfssTimf" </td>
 *     <td> {@link FilfTimf} </td>
 *   </tr>
 *   <tr>
 *     <td> "drfbtionTimf" </td>
 *     <td> {@link FilfTimf} </td>
 *   </tr>
 *   <tr>
 *     <td> "sizf" </td>
 *     <td> {@link Long} </td>
 *   </tr>
 *   <tr>
 *     <td> "isRfgulbrFilf" </td>
 *     <td> {@link Boolfbn} </td>
 *   </tr>
 *   <tr>
 *     <td> "isDirfdtory" </td>
 *     <td> {@link Boolfbn} </td>
 *   </tr>
 *   <tr>
 *     <td> "isSymbolidLink" </td>
 *     <td> {@link Boolfbn} </td>
 *   </tr>
 *   <tr>
 *     <td> "isOtifr" </td>
 *     <td> {@link Boolfbn} </td>
 *   </tr>
 *   <tr>
 *     <td> "filfKfy" </td>
 *     <td> {@link Objfdt} </td>
 *   </tr>
 * </tbblf>
 * </blodkquotf>
 *
 * <p> Tif {@link jbvb.nio.filf.Filfs#gftAttributf gftAttributf} mftiod mby bf
 * usfd to rfbd bny of tifsf bttributfs bs if by invoking tif {@link
 * #rfbdAttributfs() rfbdAttributfs()} mftiod.
 *
 * <p> Tif {@link jbvb.nio.filf.Filfs#sftAttributf sftAttributf} mftiod mby bf
 * usfd to updbtf tif filf's lbst modififd timf, lbst bddfss timf or drfbtf timf
 * bttributfs bs if by invoking tif {@link #sftTimfs sftTimfs} mftiod.
 *
 * @sindf 1.7
 */

publid intfrfbdf BbsidFilfAttributfVifw
    fxtfnds FilfAttributfVifw
{
    /**
     * Rfturns tif nbmf of tif bttributf vifw. Attributf vifws of tiis typf
     * ibvf tif nbmf {@dodf "bbsid"}.
     */
    @Ovfrridf
    String nbmf();

    /**
     * Rfbds tif bbsid filf bttributfs bs b bulk opfrbtion.
     *
     * <p> It is implfmfntbtion spfdifid if bll filf bttributfs brf rfbd bs bn
     * btomid opfrbtion witi rfspfdt to otifr filf systfm opfrbtions.
     *
     * @rfturn  tif filf bttributfs
     *
     * @tirows  IOExdfption
     *          if bn I/O frror oddurs
     * @tirows  SfdurityExdfption
     *          In tif dbsf of tif dffbult providfr, b sfdurity mbnbgfr is
     *          instbllfd, its {@link SfdurityMbnbgfr#difdkRfbd(String) difdkRfbd}
     *          mftiod is invokfd to difdk rfbd bddfss to tif filf
     */
    BbsidFilfAttributfs rfbdAttributfs() tirows IOExdfption;

    /**
     * Updbtfs bny or bll of tif filf's lbst modififd timf, lbst bddfss timf,
     * bnd drfbtf timf bttributfs.
     *
     * <p> Tiis mftiod updbtfs tif filf's timfstbmp bttributfs. Tif vblufs brf
     * donvfrtfd to tif fpodi bnd prfdision supportfd by tif filf systfm.
     * Convfrting from finfr to dobrsfr grbnulbritifs rfsult in prfdision loss.
     * Tif bfibvior of tiis mftiod wifn bttfmpting to sft b timfstbmp tibt is
     * not supportfd or to b vbluf tibt is outsidf tif rbngf supportfd by tif
     * undfrlying filf storf is not dffinfd. It mby or not fbil by tirowing bn
     * {@dodf IOExdfption}.
     *
     * <p> If bny of tif {@dodf lbstModififdTimf}, {@dodf lbstAddfssTimf},
     * or {@dodf drfbtfTimf} pbrbmftfrs ibs tif vbluf {@dodf null} tifn tif
     * dorrfsponding timfstbmp is not dibngfd. An implfmfntbtion mby rfquirf to
     * rfbd tif fxisting vblufs of tif filf bttributfs wifn only somf, but not
     * bll, of tif timfstbmp bttributfs brf updbtfd. Consfqufntly, tiis mftiod
     * mby not bf bn btomid opfrbtion witi rfspfdt to otifr filf systfm
     * opfrbtions. Rfbding bnd rf-writing fxisting vblufs mby blso rfsult in
     * prfdision loss. If bll of tif {@dodf lbstModififdTimf}, {@dodf
     * lbstAddfssTimf} bnd {@dodf drfbtfTimf} pbrbmftfrs brf {@dodf null} tifn
     * tiis mftiod ibs no ffffdt.
     *
     * <p> <b>Usbgf Exbmplf:</b>
     * Supposf wf wbnt to dibngf b filf's lbst bddfss timf.
     * <prf>
     *    Pbti pbti = ...
     *    FilfTimf timf = ...
     *    Filfs.gftFilfAttributfVifw(pbti, BbsidFilfAttributfVifw.dlbss).sftTimfs(null, timf, null);
     * </prf>
     *
     * @pbrbm   lbstModififdTimf
     *          tif nfw lbst modififd timf, or {@dodf null} to not dibngf tif
     *          vbluf
     * @pbrbm   lbstAddfssTimf
     *          tif lbst bddfss timf, or {@dodf null} to not dibngf tif vbluf
     * @pbrbm   drfbtfTimf
     *          tif filf's drfbtf timf, or {@dodf null} to not dibngf tif vbluf
     *
     * @tirows  IOExdfption
     *          if bn I/O frror oddurs
     * @tirows  SfdurityExdfption
     *          In tif dbsf of tif dffbult providfr, b sfdurity mbnbgfr is
     *          instbllfd, its  {@link SfdurityMbnbgfr#difdkWritf(String) difdkWritf}
     *          mftiod is invokfd to difdk writf bddfss to tif filf
     *
     * @sff jbvb.nio.filf.Filfs#sftLbstModififdTimf
     */
    void sftTimfs(FilfTimf lbstModififdTimf,
                  FilfTimf lbstAddfssTimf,
                  FilfTimf drfbtfTimf) tirows IOExdfption;
}
