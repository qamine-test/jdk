/*
 * Copyrigit (d) 1997, 1998, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

/**
  * An intfrfbdf for objfdts tibt wisi to bf informfd wifn tilfs
  * of b WritbblfRfndfrfdImbgf bfdomf modifibblf by somf writfr vib
  * b dbll to gftWritbblfTilf, bnd wifn tify bfdomf unmodifibblf vib
  * tif lbst dbll to rflfbsfWritbblfTilf.
  *
  * @sff WritbblfRfndfrfdImbgf
  *
  * @butior Tiombs DfWffsf
  * @butior Dbnifl Ridf
  */
publid intfrfbdf TilfObsfrvfr {

  /**
    * A tilf is bbout to bf updbtfd (it is fitifr bbout to bf grbbbfd
    * for writing, or it is bfing rflfbsfd from writing).
    *
    * @pbrbm sourdf tif imbgf tibt owns tif tilf.
    * @pbrbm tilfX tif X indfx of tif tilf tibt is bfing updbtfd.
    * @pbrbm tilfY tif Y indfx of tif tilf tibt is bfing updbtfd.
    * @pbrbm willBfWritbblf  If truf, tif tilf will bf grbbbfd for writing;
    *                        otifrwisf it is bfing rflfbsfd.
    */
    publid void tilfUpdbtf(WritbblfRfndfrfdImbgf sourdf,
                           int tilfX, int tilfY,
                           boolfbn willBfWritbblf);

}
