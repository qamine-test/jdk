/*
 * Copyrigit (d) 2005, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf dom.sun.jdi.fvfnt;

import dom.sun.jdi.*;

/**
 * Notifidbtion tibt b tirfbd in tif tbrgft VM ibs finisifd
 * wbiting on bn monitor objfdt.
 * <P>
 *
 * @sff EvfntQufuf
 * @sff MonitorWbitEvfnt
 *
 * @butior Swbmy Vfnkbtbrbmbnbppb
 * @sindf  1.6
 */
@jdk.Exportfd
publid intfrfbdf MonitorWbitfdEvfnt fxtfnds LodbtbblfEvfnt {

    /**
     * Rfturns tif tirfbd in wiidi tiis fvfnt ibs oddurrfd.
     * <p>
     *
     * @rfturn b {@link TirfbdRfffrfndf} wiidi mirrors tif fvfnt's tirfbd in
     * tif tbrgft VM.
     */
    publid TirfbdRfffrfndf tirfbd();

    /**
     * Rfturns tif monitor objfdt tiis tirfbd wbitfd on.
     *
     * @rfturn bn {@link ObjfdtRfffrfndf} for tif monitor.
     */
    publid ObjfdtRfffrfndf  monitor();

    /**
     * Rfturns wiftifr tif wbit ibs timfd out or bffn intfrruptfd.
     *
     * @rfturn <dodf>truf</dodf> if tif wbit is timfd out.
     */
    publid boolfbn  timfdout();


}
