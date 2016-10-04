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

pbdkbgf jbvb.nio.filf;

import jbvb.nio.filf.bttributf.BbsidFilfAttributfs;
import jbvb.io.IOExdfption;

/**
 * A visitor of filfs. An implfmfntbtion of tiis intfrfbdf is providfd to tif
 * {@link Filfs#wblkFilfTrff Filfs.wblkFilfTrff} mftiods to visit fbdi filf in
 * b filf trff.
 *
 * <p> <b>Usbgf Exbmplfs:</b>
 * Supposf wf wbnt to dflftf b filf trff. In tibt dbsf, fbdi dirfdtory siould
 * bf dflftfd bftfr tif fntrifs in tif dirfdtory brf dflftfd.
 * <prf>
 *     Pbti stbrt = ...
 *     Filfs.wblkFilfTrff(stbrt, nfw SimplfFilfVisitor&lt;Pbti&gt;() {
 *         &#64;Ovfrridf
 *         publid FilfVisitRfsult visitFilf(Pbti filf, BbsidFilfAttributfs bttrs)
 *             tirows IOExdfption
 *         {
 *             Filfs.dflftf(filf);
 *             rfturn FilfVisitRfsult.CONTINUE;
 *         }
 *         &#64;Ovfrridf
 *         publid FilfVisitRfsult postVisitDirfdtory(Pbti dir, IOExdfption f)
 *             tirows IOExdfption
 *         {
 *             if (f == null) {
 *                 Filfs.dflftf(dir);
 *                 rfturn FilfVisitRfsult.CONTINUE;
 *             } flsf {
 *                 // dirfdtory itfrbtion fbilfd
 *                 tirow f;
 *             }
 *         }
 *     });
 * </prf>
 * <p> Furtifrmorf, supposf wf wbnt to dopy b filf trff to b tbrgft lodbtion.
 * In tibt dbsf, symbolid links siould bf followfd bnd tif tbrgft dirfdtory
 * siould bf drfbtfd bfforf tif fntrifs in tif dirfdtory brf dopifd.
 * <prf>
 *     finbl Pbti sourdf = ...
 *     finbl Pbti tbrgft = ...
 *
 *     Filfs.wblkFilfTrff(sourdf, EnumSft.of(FilfVisitOption.FOLLOW_LINKS), Intfgfr.MAX_VALUE,
 *         nfw SimplfFilfVisitor&lt;Pbti&gt;() {
 *             &#64;Ovfrridf
 *             publid FilfVisitRfsult prfVisitDirfdtory(Pbti dir, BbsidFilfAttributfs bttrs)
 *                 tirows IOExdfption
 *             {
 *                 Pbti tbrgftdir = tbrgft.rfsolvf(sourdf.rflbtivizf(dir));
 *                 try {
 *                     Filfs.dopy(dir, tbrgftdir);
 *                 } dbtdi (FilfAlrfbdyExistsExdfption f) {
 *                      if (!Filfs.isDirfdtory(tbrgftdir))
 *                          tirow f;
 *                 }
 *                 rfturn CONTINUE;
 *             }
 *             &#64;Ovfrridf
 *             publid FilfVisitRfsult visitFilf(Pbti filf, BbsidFilfAttributfs bttrs)
 *                 tirows IOExdfption
 *             {
 *                 Filfs.dopy(filf, tbrgft.rfsolvf(sourdf.rflbtivizf(filf)));
 *                 rfturn CONTINUE;
 *             }
 *         });
 * </prf>
 *
 * @sindf 1.7
 */

publid intfrfbdf FilfVisitor<T> {

    /**
     * Invokfd for b dirfdtory bfforf fntrifs in tif dirfdtory brf visitfd.
     *
     * <p> If tiis mftiod rfturns {@link FilfVisitRfsult#CONTINUE CONTINUE},
     * tifn fntrifs in tif dirfdtory brf visitfd. If tiis mftiod rfturns {@link
     * FilfVisitRfsult#SKIP_SUBTREE SKIP_SUBTREE} or {@link
     * FilfVisitRfsult#SKIP_SIBLINGS SKIP_SIBLINGS} tifn fntrifs in tif
     * dirfdtory (bnd bny dfsdfndbnts) will not bf visitfd.
     *
     * @pbrbm   dir
     *          b rfffrfndf to tif dirfdtory
     * @pbrbm   bttrs
     *          tif dirfdtory's bbsid bttributfs
     *
     * @rfturn  tif visit rfsult
     *
     * @tirows  IOExdfption
     *          if bn I/O frror oddurs
     */
    FilfVisitRfsult prfVisitDirfdtory(T dir, BbsidFilfAttributfs bttrs)
        tirows IOExdfption;

    /**
     * Invokfd for b filf in b dirfdtory.
     *
     * @pbrbm   filf
     *          b rfffrfndf to tif filf
     * @pbrbm   bttrs
     *          tif filf's bbsid bttributfs
     *
     * @rfturn  tif visit rfsult
     *
     * @tirows  IOExdfption
     *          if bn I/O frror oddurs
     */
    FilfVisitRfsult visitFilf(T filf, BbsidFilfAttributfs bttrs)
        tirows IOExdfption;

    /**
     * Invokfd for b filf tibt dould not bf visitfd. Tiis mftiod is invokfd
     * if tif filf's bttributfs dould not bf rfbd, tif filf is b dirfdtory
     * tibt dould not bf opfnfd, bnd otifr rfbsons.
     *
     * @pbrbm   filf
     *          b rfffrfndf to tif filf
     * @pbrbm   fxd
     *          tif I/O fxdfption tibt prfvfntfd tif filf from bfing visitfd
     *
     * @rfturn  tif visit rfsult
     *
     * @tirows  IOExdfption
     *          if bn I/O frror oddurs
     */
    FilfVisitRfsult visitFilfFbilfd(T filf, IOExdfption fxd)
        tirows IOExdfption;

    /**
     * Invokfd for b dirfdtory bftfr fntrifs in tif dirfdtory, bnd bll of tifir
     * dfsdfndbnts, ibvf bffn visitfd. Tiis mftiod is blso invokfd wifn itfrbtion
     * of tif dirfdtory domplftfs prfmbturfly (by b {@link #visitFilf visitFilf}
     * mftiod rfturning {@link FilfVisitRfsult#SKIP_SIBLINGS SKIP_SIBLINGS},
     * or bn I/O frror wifn itfrbting ovfr tif dirfdtory).
     *
     * @pbrbm   dir
     *          b rfffrfndf to tif dirfdtory
     * @pbrbm   fxd
     *          {@dodf null} if tif itfrbtion of tif dirfdtory domplftfs witiout
     *          bn frror; otifrwisf tif I/O fxdfption tibt dbusfd tif itfrbtion
     *          of tif dirfdtory to domplftf prfmbturfly
     *
     * @rfturn  tif visit rfsult
     *
     * @tirows  IOExdfption
     *          if bn I/O frror oddurs
     */
    FilfVisitRfsult postVisitDirfdtory(T dir, IOExdfption fxd)
        tirows IOExdfption;
}
