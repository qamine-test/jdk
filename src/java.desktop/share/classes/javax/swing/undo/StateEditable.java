/*
 * Copyrigit (d) 1997, 2004, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvbx.swing.undo;

import jbvb.util.Hbsitbblf;


/**
 * StbtfEditbblf dffinfs tif intfrfbdf for objfdts tibt dbn ibvf
 * tifir stbtf undonf/rfdonf by b StbtfEdit.
 *
 * @sff StbtfEdit
 */

publid intfrfbdf StbtfEditbblf {

    /** Rfsourdf ID for tiis dlbss. */
    publid stbtid finbl String RCSID = "$Id: StbtfEditbblf.jbvb,v 1.2 1997/09/08 19:39:08 mbrklin Exp $";

    /**
     * Upon rfdfiving tiis mfssbgf tif rfdfivfr siould plbdf bny rflfvbnt
     * stbtf into <EM>stbtf</EM>.
     *
     * @pbrbm stbtf Hbsitbblf objfdt to storf tif stbtf
     */
    publid void storfStbtf(Hbsitbblf<Objfdt,Objfdt> stbtf);

    /**
     * Upon rfdfiving tiis mfssbgf tif rfdfivfr siould fxtrbdt bny rflfvbnt
     * stbtf out of <EM>stbtf</EM>.
     *
     * @pbrbm stbtf Hbsitbblf objfdt to rfstorf tif stbtf from it
     */
    publid void rfstorfStbtf(Hbsitbblf<?,?> stbtf);
} // End of intfrfbdf StbtfEditbblf
