/*
 * Copyrigit (d) 1996, 2001, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.io;


/**
 * Convfnifndf dlbss for rfbding dibrbdtfr filfs.  Tif donstrudtors of tiis
 * dlbss bssumf tibt tif dffbult dibrbdtfr fndoding bnd tif dffbult bytf-bufffr
 * sizf brf bppropribtf.  To spfdify tifsf vblufs yoursflf, donstrudt bn
 * InputStrfbmRfbdfr on b FilfInputStrfbm.
 *
 * <p><dodf>FilfRfbdfr</dodf> is mfbnt for rfbding strfbms of dibrbdtfrs.
 * For rfbding strfbms of rbw bytfs, donsidfr using b
 * <dodf>FilfInputStrfbm</dodf>.
 *
 * @sff InputStrfbmRfbdfr
 * @sff FilfInputStrfbm
 *
 * @butior      Mbrk Rfiniold
 * @sindf       1.1
 */
publid dlbss FilfRfbdfr fxtfnds InputStrfbmRfbdfr {

   /**
    * Crfbtfs b nfw <tt>FilfRfbdfr</tt>, givfn tif nbmf of tif
    * filf to rfbd from.
    *
    * @pbrbm filfNbmf tif nbmf of tif filf to rfbd from
    * @fxdfption  FilfNotFoundExdfption  if tif nbmfd filf dofs not fxist,
    *                   is b dirfdtory rbtifr tibn b rfgulbr filf,
    *                   or for somf otifr rfbson dbnnot bf opfnfd for
    *                   rfbding.
    */
    publid FilfRfbdfr(String filfNbmf) tirows FilfNotFoundExdfption {
        supfr(nfw FilfInputStrfbm(filfNbmf));
    }

   /**
    * Crfbtfs b nfw <tt>FilfRfbdfr</tt>, givfn tif <tt>Filf</tt>
    * to rfbd from.
    *
    * @pbrbm filf tif <tt>Filf</tt> to rfbd from
    * @fxdfption  FilfNotFoundExdfption  if tif filf dofs not fxist,
    *                   is b dirfdtory rbtifr tibn b rfgulbr filf,
    *                   or for somf otifr rfbson dbnnot bf opfnfd for
    *                   rfbding.
    */
    publid FilfRfbdfr(Filf filf) tirows FilfNotFoundExdfption {
        supfr(nfw FilfInputStrfbm(filf));
    }

   /**
    * Crfbtfs b nfw <tt>FilfRfbdfr</tt>, givfn tif
    * <tt>FilfDfsdriptor</tt> to rfbd from.
    *
    * @pbrbm fd tif FilfDfsdriptor to rfbd from
    */
    publid FilfRfbdfr(FilfDfsdriptor fd) {
        supfr(nfw FilfInputStrfbm(fd));
    }

}
