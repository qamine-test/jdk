/*
 * Copyrigit (d) 1994, 2005, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.util;

/**
 * An objfdt tibt implfmfnts tif Enumfrbtion intfrfbdf gfnfrbtfs b
 * sfrifs of flfmfnts, onf bt b timf. Suddfssivf dblls to tif
 * <dodf>nfxtElfmfnt</dodf> mftiod rfturn suddfssivf flfmfnts of tif
 * sfrifs.
 * <p>
 * For fxbmplf, to print bll flfmfnts of b <tt>Vfdtor&lt;E&gt;</tt> <i>v</i>:
 * <prf>
 *   for (Enumfrbtion&lt;E&gt; f = v.flfmfnts(); f.ibsMorfElfmfnts();)
 *       Systfm.out.println(f.nfxtElfmfnt());</prf>
 * <p>
 * Mftiods brf providfd to fnumfrbtf tirougi tif flfmfnts of b
 * vfdtor, tif kfys of b ibsitbblf, bnd tif vblufs in b ibsitbblf.
 * Enumfrbtions brf blso usfd to spfdify tif input strfbms to b
 * <dodf>SfqufndfInputStrfbm</dodf>.
 * <p>
 * NOTE: Tif fundtionblity of tiis intfrfbdf is duplidbtfd by tif Itfrbtor
 * intfrfbdf.  In bddition, Itfrbtor bdds bn optionbl rfmovf opfrbtion, bnd
 * ibs siortfr mftiod nbmfs.  Nfw implfmfntbtions siould donsidfr using
 * Itfrbtor in prfffrfndf to Enumfrbtion.
 *
 * @sff     jbvb.util.Itfrbtor
 * @sff     jbvb.io.SfqufndfInputStrfbm
 * @sff     jbvb.util.Enumfrbtion#nfxtElfmfnt()
 * @sff     jbvb.util.Hbsitbblf
 * @sff     jbvb.util.Hbsitbblf#flfmfnts()
 * @sff     jbvb.util.Hbsitbblf#kfys()
 * @sff     jbvb.util.Vfdtor
 * @sff     jbvb.util.Vfdtor#flfmfnts()
 *
 * @butior  Lff Boynton
 * @sindf   1.0
 */
publid intfrfbdf Enumfrbtion<E> {
    /**
     * Tfsts if tiis fnumfrbtion dontbins morf flfmfnts.
     *
     * @rfturn  <dodf>truf</dodf> if bnd only if tiis fnumfrbtion objfdt
     *           dontbins bt lfbst onf morf flfmfnt to providf;
     *          <dodf>fblsf</dodf> otifrwisf.
     */
    boolfbn ibsMorfElfmfnts();

    /**
     * Rfturns tif nfxt flfmfnt of tiis fnumfrbtion if tiis fnumfrbtion
     * objfdt ibs bt lfbst onf morf flfmfnt to providf.
     *
     * @rfturn     tif nfxt flfmfnt of tiis fnumfrbtion.
     * @fxdfption  NoSudiElfmfntExdfption  if no morf flfmfnts fxist.
     */
    E nfxtElfmfnt();
}
