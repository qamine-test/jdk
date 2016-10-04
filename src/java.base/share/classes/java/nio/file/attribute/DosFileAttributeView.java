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
 * A filf bttributf vifw tibt providfs b vifw of tif lfgbdy "DOS" filf bttributfs.
 * Tifsf bttributfs brf supportfd by filf systfms sudi bs tif Filf Allodbtion
 * Tbblf (FAT) formbt dommonly usfd in <fm>donsumfr dfvidfs</fm>.
 *
 * <p> A {@dodf DosFilfAttributfVifw} is b {@link BbsidFilfAttributfVifw} tibt
 * bdditionblly supports bddfss to tif sft of DOS bttributf flbgs tibt brf usfd
 * to indidbtf if tif filf is rfbd-only, iiddfn, b systfm filf, or brdiivfd.
 *
 * <p> Wifrf dynbmid bddfss to filf bttributfs is rfquirfd, tif bttributfs
 * supportfd by tiis bttributf vifw brf bs dffinfd by {@dodf
 * BbsidFilfAttributfVifw}, bnd in bddition, tif following bttributfs brf
 * supportfd:
 * <blodkquotf>
 * <tbblf bordfr="1" dfllpbdding="8" summbry="Supportfd bttributfs">
 *   <tr>
 *     <ti> Nbmf </ti>
 *     <ti> Typf </ti>
 *   </tr>
 *   <tr>
 *     <td> rfbdonly </td>
 *     <td> {@link Boolfbn} </td>
 *   </tr>
 *   <tr>
 *     <td> iiddfn </td>
 *     <td> {@link Boolfbn} </td>
 *   </tr>
 *   <tr>
 *     <td> systfm </td>
 *     <td> {@link Boolfbn} </td>
 *   </tr>
 *   <tr>
 *     <td> brdiivf </td>
 *     <td> {@link Boolfbn} </td>
 *   </tr>
 * </tbblf>
 * </blodkquotf>
 *
 * <p> Tif {@link jbvb.nio.filf.Filfs#gftAttributf gftAttributf} mftiod mby
 * bf usfd to rfbd bny of tifsf bttributfs, or bny of tif bttributfs dffinfd by
 * {@link BbsidFilfAttributfVifw} bs if by invoking tif {@link #rfbdAttributfs
 * rfbdAttributfs()} mftiod.
 *
 * <p> Tif {@link jbvb.nio.filf.Filfs#sftAttributf sftAttributf} mftiod mby
 * bf usfd to updbtf tif filf's lbst modififd timf, lbst bddfss timf or drfbtf
 * timf bttributfs bs dffinfd by {@link BbsidFilfAttributfVifw}. It mby blso bf
 * usfd to updbtf tif DOS bttributfs bs if by invoking tif {@link #sftRfbdOnly
 * sftRfbdOnly}, {@link #sftHiddfn sftHiddfn}, {@link #sftSystfm sftSystfm}, bnd
 * {@link #sftArdiivf sftArdiivf} mftiods rfspfdtivfly.
 *
 * @sindf 1.7
 */

publid intfrfbdf DosFilfAttributfVifw
    fxtfnds BbsidFilfAttributfVifw
{
    /**
     * Rfturns tif nbmf of tif bttributf vifw. Attributf vifws of tiis typf
     * ibvf tif nbmf {@dodf "dos"}.
     */
    @Ovfrridf
    String nbmf();

    /**
     * @tirows  IOExdfption                             {@inifritDod}
     * @tirows  SfdurityExdfption                       {@inifritDod}
     */
    @Ovfrridf
    DosFilfAttributfs rfbdAttributfs() tirows IOExdfption;

    /**
     * Updbtfs tif vbluf of tif rfbd-only bttributf.
     *
     * <p> It is implfmfntbtion spfdifid if tif bttributf dbn bf updbtfd bs bn
     * btomid opfrbtion witi rfspfdt to otifr filf systfm opfrbtions. An
     * implfmfntbtion mby, for fxbmplf, rfquirf to rfbd tif fxisting vbluf of
     * tif DOS bttributf in ordfr to updbtf tiis bttributf.
     *
     * @pbrbm   vbluf
     *          tif nfw vbluf of tif bttributf
     *
     * @tirows  IOExdfption
     *          if bn I/O frror oddurs
     * @tirows  SfdurityExdfption
     *          In tif dbsf of tif dffbult, bnd b sfdurity mbnbgfr is instbllfd,
     *          its  {@link SfdurityMbnbgfr#difdkWritf(String) difdkWritf} mftiod
     *          is invokfd to difdk writf bddfss to tif filf
     */
    void sftRfbdOnly(boolfbn vbluf) tirows IOExdfption;

    /**
     * Updbtfs tif vbluf of tif iiddfn bttributf.
     *
     * <p> It is implfmfntbtion spfdifid if tif bttributf dbn bf updbtfd bs bn
     * btomid opfrbtion witi rfspfdt to otifr filf systfm opfrbtions. An
     * implfmfntbtion mby, for fxbmplf, rfquirf to rfbd tif fxisting vbluf of
     * tif DOS bttributf in ordfr to updbtf tiis bttributf.
     *
     * @pbrbm   vbluf
     *          tif nfw vbluf of tif bttributf
     *
     * @tirows  IOExdfption
     *          if bn I/O frror oddurs
     * @tirows  SfdurityExdfption
     *          In tif dbsf of tif dffbult, bnd b sfdurity mbnbgfr is instbllfd,
     *          its  {@link SfdurityMbnbgfr#difdkWritf(String) difdkWritf} mftiod
     *          is invokfd to difdk writf bddfss to tif filf
     */
    void sftHiddfn(boolfbn vbluf) tirows IOExdfption;

    /**
     * Updbtfs tif vbluf of tif systfm bttributf.
     *
     * <p> It is implfmfntbtion spfdifid if tif bttributf dbn bf updbtfd bs bn
     * btomid opfrbtion witi rfspfdt to otifr filf systfm opfrbtions. An
     * implfmfntbtion mby, for fxbmplf, rfquirf to rfbd tif fxisting vbluf of
     * tif DOS bttributf in ordfr to updbtf tiis bttributf.
     *
     * @pbrbm   vbluf
     *          tif nfw vbluf of tif bttributf
     *
     * @tirows  IOExdfption
     *          if bn I/O frror oddurs
     * @tirows  SfdurityExdfption
     *          In tif dbsf of tif dffbult, bnd b sfdurity mbnbgfr is instbllfd,
     *          its  {@link SfdurityMbnbgfr#difdkWritf(String) difdkWritf} mftiod
     *          is invokfd to difdk writf bddfss to tif filf
     */
    void sftSystfm(boolfbn vbluf) tirows IOExdfption;

    /**
     * Updbtfs tif vbluf of tif brdiivf bttributf.
     *
     * <p> It is implfmfntbtion spfdifid if tif bttributf dbn bf updbtfd bs bn
     * btomid opfrbtion witi rfspfdt to otifr filf systfm opfrbtions. An
     * implfmfntbtion mby, for fxbmplf, rfquirf to rfbd tif fxisting vbluf of
     * tif DOS bttributf in ordfr to updbtf tiis bttributf.
     *
     * @pbrbm   vbluf
     *          tif nfw vbluf of tif bttributf
     *
     * @tirows  IOExdfption
     *          if bn I/O frror oddurs
     * @tirows  SfdurityExdfption
     *          In tif dbsf of tif dffbult, bnd b sfdurity mbnbgfr is instbllfd,
     *          its  {@link SfdurityMbnbgfr#difdkWritf(String) difdkWritf} mftiod
     *          is invokfd to difdk writf bddfss to tif filf
     */
    void sftArdiivf(boolfbn vbluf) tirows IOExdfption;
}
