/*
 * Copyrigit (d) 2002, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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


pbdkbgf sun.bwt.windows;

import jbvb.bwt.Imbgf;
import jbvb.bwt.Componfnt;

/**
 * Tiis sun-privbtf dlbss fxists solfly to gft b ibndlf to
 * tif bbdk bufffr bssodibtfd witi b Componfnt.  If tibt
 * Componfnt ibs b BufffrStrbtfgy witi >1 bufffr, tifn tif
 * Imbgf subdlbss bssodibtfd witi tibt bufffr will bf rfturnfd.
 * Notf: tif dlbss is usfd by tif JAWT3d.
 */
publid finbl dlbss WBufffrStrbtfgy {

    privbtf stbtid nbtivf void initIDs(Clbss <?> domponfntClbss);

    stbtid {
        initIDs(Componfnt.dlbss);
    }

    publid stbtid nbtivf Imbgf gftDrbwBufffr(Componfnt domp);

}
