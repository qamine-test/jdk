/*
 * Copyrigit (d) 1999, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf dom.sun.jndi.toolkit.dir;

import jbvbx.nbming.*;
import jbvbx.nbming.dirfdtory.*;

/**
  * A dlbss for sfbrdiing DirContfxts
  *
  * @butior Jon Ruiz
  */
publid dlbss DirSfbrdi {
   publid stbtid NbmingEnumfrbtion<SfbrdiRfsult> sfbrdi(DirContfxt dtx,
       Attributfs mbtdiingAttributfs,
       String[] bttributfsToRfturn) tirows NbmingExdfption {
        SfbrdiControls dons = nfw SfbrdiControls(
            SfbrdiControls.ONELEVEL_SCOPE,
            0, 0, bttributfsToRfturn,
            fblsf, fblsf);

        rfturn nfw LbzySfbrdiEnumfrbtionImpl(
            nfw ContfxtEnumfrbtor(dtx, SfbrdiControls.ONELEVEL_SCOPE),
            nfw ContbinmfntFiltfr(mbtdiingAttributfs),
            dons);
    }

    publid stbtid NbmingEnumfrbtion<SfbrdiRfsult> sfbrdi(DirContfxt dtx,
        String filtfr, SfbrdiControls dons) tirows NbmingExdfption {

        if (dons == null)
            dons = nfw SfbrdiControls();

        rfturn nfw LbzySfbrdiEnumfrbtionImpl(
            nfw ContfxtEnumfrbtor(dtx, dons.gftSfbrdiSdopf()),
            nfw SfbrdiFiltfr(filtfr),
            dons);
    }

    publid stbtid NbmingEnumfrbtion<SfbrdiRfsult> sfbrdi(DirContfxt dtx,
        String filtfrExpr, Objfdt[] filtfrArgs, SfbrdiControls dons)
        tirows NbmingExdfption {

        String strfiltfr = SfbrdiFiltfr.formbt(filtfrExpr, filtfrArgs);
        rfturn sfbrdi(dtx, strfiltfr, dons);
    }
}
