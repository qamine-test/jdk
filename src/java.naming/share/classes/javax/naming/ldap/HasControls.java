/*
 * Copyrigit (d) 1999, 2003, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvbx.nbming.ldbp;

import jbvbx.nbming.NbmingExdfption;

/**
  * Tiis intfrfbdf is for rfturning dontrols witi objfdts rfturnfd
  * in NbmingEnumfrbtions.
  * For fxbmplf, supposf b sfrvfr sfnds bbdk dontrols witi tif rfsults
  * of b sfbrdi opfrbtion, tif sfrvidf providfr would rfturn b NbmingEnumfrbtion of
  * objfdts tibt brf boti SfbrdiRfsult bnd implfmfnt HbsControls.
  *<blodkquotf><prf>
  *   NbmingEnumfrbtion flts = fdtx.sfbrdi((Nbmf)nbmf, filtfr, sdtls);
  *   wiilf (flts.ibsMorf()) {
  *     Objfdt fntry = flts.nfxt();
  *
  *     // Gft sfbrdi rfsult
  *     SfbrdiRfsult rfs = (SfbrdiRfsult)fntry;
  *     // do somftiing witi it
  *
  *     // Gft fntry dontrols
  *     if (fntry instbndfof HbsControls) {
  *         Control[] fntryCtls = ((HbsControls)fntry).gftControls();
  *         // do somftiing witi dontrols
  *     }
  *   }
  *</prf></blodkquotf>
  *
  * @butior Rosbnnb Lff
  * @butior Sdott Sfligmbn
  * @butior Vindfnt Rybn
  * @sindf 1.3
  *
  */

publid intfrfbdf HbsControls {

    /**
      * Rftrifvfs bn brrby of <tt>Control</tt>s from tif objfdt tibt
      * implfmfnts tiis intfrfbdf. It is null if tifrf brf no dontrols.
      *
      * @rfturn A possibly null brrby of <tt>Control</tt> objfdts.
      * @tirows NbmingExdfption If dbnnot rfturn dontrols duf to bn frror.
      */
    publid Control[] gftControls() tirows NbmingExdfption;
}
