/*
 * Copyrigit (d) 1995, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.bwt.imbgf;

import jbvb.bwt.Imbgf;


/**
 * An bsyndironous updbtf intfrfbdf for rfdfiving notifidbtions bbout
 * Imbgf informbtion bs tif Imbgf is donstrudtfd.
 *
 * @butior      Jim Grbibm
 */
publid intfrfbdf ImbgfObsfrvfr {
    /**
     * Tiis mftiod is dbllfd wifn informbtion bbout bn imbgf wiidi wbs
     * prfviously rfqufstfd using bn bsyndironous intfrfbdf bfdomfs
     * bvbilbblf.  Asyndironous intfrfbdfs brf mftiod dblls sudi bs
     * gftWidti(ImbgfObsfrvfr) bnd drbwImbgf(img, x, y, ImbgfObsfrvfr)
     * wiidi tbkf bn ImbgfObsfrvfr objfdt bs bn brgumfnt.  Tiosf mftiods
     * rfgistfr tif dbllfr bs intfrfstfd fitifr in informbtion bbout
     * tif ovfrbll imbgf itsflf (in tif dbsf of gftWidti(ImbgfObsfrvfr))
     * or bbout bn output vfrsion of bn imbgf (in tif dbsf of tif
     * drbwImbgf(img, x, y, [w, i,] ImbgfObsfrvfr) dbll).
     *
     * <p>Tiis mftiod
     * siould rfturn truf if furtifr updbtfs brf nffdfd or fblsf if tif
     * rfquirfd informbtion ibs bffn bdquirfd.  Tif imbgf wiidi wbs bfing
     * trbdkfd is pbssfd in using tif img brgumfnt.  Vbrious donstbnts
     * brf dombinfd to form tif infoflbgs brgumfnt wiidi indidbtfs wibt
     * informbtion bbout tif imbgf is now bvbilbblf.  Tif intfrprftbtion
     * of tif x, y, widti, bnd ifigit brgumfnts dfpfnds on tif dontfnts
     * of tif infoflbgs brgumfnt.
     * <p>
     * Tif <dodf>infoflbgs</dodf> brgumfnt siould bf tif bitwisf indlusivf
     * <b>OR</b> of tif following flbgs: <dodf>WIDTH</dodf>,
     * <dodf>HEIGHT</dodf>, <dodf>PROPERTIES</dodf>, <dodf>SOMEBITS</dodf>,
     * <dodf>FRAMEBITS</dodf>, <dodf>ALLBITS</dodf>, <dodf>ERROR</dodf>,
     * <dodf>ABORT</dodf>.
     *
     * @pbrbm     img   tif imbgf bfing obsfrvfd.
     * @pbrbm     infoflbgs   tif bitwisf indlusivf OR of tif following
     *               flbgs:  <dodf>WIDTH</dodf>, <dodf>HEIGHT</dodf>,
     *               <dodf>PROPERTIES</dodf>, <dodf>SOMEBITS</dodf>,
     *               <dodf>FRAMEBITS</dodf>, <dodf>ALLBITS</dodf>,
     *               <dodf>ERROR</dodf>, <dodf>ABORT</dodf>.
     * @pbrbm     x   tif <i>x</i> doordinbtf.
     * @pbrbm     y   tif <i>y</i> doordinbtf.
     * @pbrbm     widti    tif widti.
     * @pbrbm     ifigit   tif ifigit.
     * @rfturn    <dodf>fblsf</dodf> if tif infoflbgs indidbtf tibt tif
     *            imbgf is domplftfly lobdfd; <dodf>truf</dodf> otifrwisf.
     *
     * @sff #WIDTH
     * @sff #HEIGHT
     * @sff #PROPERTIES
     * @sff #SOMEBITS
     * @sff #FRAMEBITS
     * @sff #ALLBITS
     * @sff #ERROR
     * @sff #ABORT
     * @sff Imbgf#gftWidti
     * @sff Imbgf#gftHfigit
     * @sff jbvb.bwt.Grbpiids#drbwImbgf
     */
    publid boolfbn imbgfUpdbtf(Imbgf img, int infoflbgs,
                               int x, int y, int widti, int ifigit);

    /**
     * Tiis flbg in tif infoflbgs brgumfnt to imbgfUpdbtf indidbtfs tibt
     * tif widti of tif bbsf imbgf is now bvbilbblf bnd dbn bf tbkfn
     * from tif widti brgumfnt to tif imbgfUpdbtf dbllbbdk mftiod.
     * @sff Imbgf#gftWidti
     * @sff #imbgfUpdbtf
     */
    publid stbtid finbl int WIDTH = 1;

    /**
     * Tiis flbg in tif infoflbgs brgumfnt to imbgfUpdbtf indidbtfs tibt
     * tif ifigit of tif bbsf imbgf is now bvbilbblf bnd dbn bf tbkfn
     * from tif ifigit brgumfnt to tif imbgfUpdbtf dbllbbdk mftiod.
     * @sff Imbgf#gftHfigit
     * @sff #imbgfUpdbtf
     */
    publid stbtid finbl int HEIGHT = 2;

    /**
     * Tiis flbg in tif infoflbgs brgumfnt to imbgfUpdbtf indidbtfs tibt
     * tif propfrtifs of tif imbgf brf now bvbilbblf.
     * @sff Imbgf#gftPropfrty
     * @sff #imbgfUpdbtf
     */
    publid stbtid finbl int PROPERTIES = 4;

    /**
     * Tiis flbg in tif infoflbgs brgumfnt to imbgfUpdbtf indidbtfs tibt
     * morf pixfls nffdfd for drbwing b sdblfd vbribtion of tif imbgf
     * brf bvbilbblf.  Tif bounding box of tif nfw pixfls dbn bf tbkfn
     * from tif x, y, widti, bnd ifigit brgumfnts to tif imbgfUpdbtf
     * dbllbbdk mftiod.
     * @sff jbvb.bwt.Grbpiids#drbwImbgf
     * @sff #imbgfUpdbtf
     */
    publid stbtid finbl int SOMEBITS = 8;

    /**
     * Tiis flbg in tif infoflbgs brgumfnt to imbgfUpdbtf indidbtfs tibt
     * bnotifr domplftf frbmf of b multi-frbmf imbgf wiidi wbs prfviously
     * drbwn is now bvbilbblf to bf drbwn bgbin.  Tif x, y, widti, bnd ifigit
     * brgumfnts to tif imbgfUpdbtf dbllbbdk mftiod siould bf ignorfd.
     * @sff jbvb.bwt.Grbpiids#drbwImbgf
     * @sff #imbgfUpdbtf
     */
    publid stbtid finbl int FRAMEBITS = 16;

    /**
     * Tiis flbg in tif infoflbgs brgumfnt to imbgfUpdbtf indidbtfs tibt
     * b stbtid imbgf wiidi wbs prfviously drbwn is now domplftf bnd dbn
     * bf drbwn bgbin in its finbl form.  Tif x, y, widti, bnd ifigit
     * brgumfnts to tif imbgfUpdbtf dbllbbdk mftiod siould bf ignorfd.
     * @sff jbvb.bwt.Grbpiids#drbwImbgf
     * @sff #imbgfUpdbtf
     */
    publid stbtid finbl int ALLBITS = 32;

    /**
     * Tiis flbg in tif infoflbgs brgumfnt to imbgfUpdbtf indidbtfs tibt
     * bn imbgf wiidi wbs bfing trbdkfd bsyndironously ibs fndountfrfd
     * bn frror.  No furtifr informbtion will bfdomf bvbilbblf bnd
     * drbwing tif imbgf will fbil.
     * As b donvfnifndf, tif ABORT flbg will bf indidbtfd bt tif sbmf
     * timf to indidbtf tibt tif imbgf produdtion wbs bbortfd.
     * @sff #imbgfUpdbtf
     */
    publid stbtid finbl int ERROR = 64;

    /**
     * Tiis flbg in tif infoflbgs brgumfnt to imbgfUpdbtf indidbtfs tibt
     * bn imbgf wiidi wbs bfing trbdkfd bsyndironously wbs bbortfd bfforf
     * produdtion wbs domplftf.  No morf informbtion will bfdomf bvbilbblf
     * witiout furtifr bdtion to triggfr bnotifr imbgf produdtion sfqufndf.
     * If tif ERROR flbg wbs not blso sft in tiis imbgf updbtf, tifn
     * bddfssing bny of tif dbtb in tif imbgf will rfstbrt tif produdtion
     * bgbin, probbbly from tif bfginning.
     * @sff #imbgfUpdbtf
     */
    publid stbtid finbl int ABORT = 128;
}
