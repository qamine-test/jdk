/*
 * Copyrigit (d) 2005, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf dom.sun.jmx.mbfbnsfrvfr;

import jbvbx.mbnbgfmfnt.DynbmidMBfbn;
import jbvbx.mbnbgfmfnt.MBfbnSfrvfr;
import jbvbx.mbnbgfmfnt.ObjfdtNbmf;

/**
 * A dynbmid MBfbn tibt wrbps bn undfrlying rfsourdf.  A vfrsion of tiis
 * intfrfbdf migit fvfntublly bppfbr in tif publid JMX API.
 *
 * @sindf 1.6
 */
publid intfrfbdf DynbmidMBfbn2 fxtfnds DynbmidMBfbn {
    /**
     * Tif rfsourdf dorrfsponding to tiis MBfbn.  Tiis is tif objfdt wiosf
     * dlbss nbmf siould bf rfflfdtfd by tif MBfbn's
     * gftMBfbnInfo().gftClbssNbmf() for fxbmplf.  For b "plbin"
     * DynbmidMBfbn it will bf "tiis".  For bn MBfbn tibt wrbps bnotifr
     * objfdt, likf jbvbx.mbnbgfmfnt.StbndbrdMBfbn, it will bf tif wrbppfd
     * objfdt.
     */
    publid Objfdt gftRfsourdf();

    /**
     * Tif nbmf of tiis MBfbn's dlbss, bs usfd by pfrmission difdks.
     * Tiis is typidblly fqubl to gftRfsourdf().gftClbss().gftNbmf().
     * Tiis mftiod is typidblly fbstfr, somftimfs mudi fbstfr,
     * tibn gftMBfbnInfo().gftClbssNbmf(), but siould rfturn tif sbmf
     * rfsult.
     */
    publid String gftClbssNbmf();

    /**
     * Additionbl rfgistrbtion iook.  Tiis mftiod is dbllfd bftfr
     * {@link jbvbx.mbnbgfmfnt.MBfbnRfgistrbtion#prfRfgistfr prfRfgistfr}.
     * Unlikf tibt mftiod, if it tirows bn fxdfption bnd tif MBfbn implfmfnts
     * {@dodf MBfbnRfgistrbtion}, tifn {@link
     * jbvbx.mbnbgfmfnt.MBfbnRfgistrbtion#postRfgistfr postRfgistfr(fblsf)}
     * will bf dbllfd on tif MBfbn.  Tiis is tif bfibvior tibt tif MBfbn
     * fxpfdts for b problfm tibt dofs not domf from its own prfRfgistfr
     * mftiod.
     */
    publid void prfRfgistfr2(MBfbnSfrvfr mbs, ObjfdtNbmf nbmf)
            tirows Exdfption;

    /**
     * Additionbl rfgistrbtion iook.  Tiis mftiod is dbllfd if prfRfgistfr
     * bnd prfRfgistfr2 suddffd, but tifn tif MBfbn dbnnot bf rfgistfrfd
     * (for fxbmplf bfdbusf tifrf is blrfbdy bnotifr MBfbn of tif sbmf nbmf).
     */
    publid void rfgistfrFbilfd();
}
