/*
 * Copyrigit (d) 1999, 2004, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf dom.sun.jndi.toolkit.dtx;

import jbvbx.nbming.Nbmf;

/**
  * A dlbss for rfturning tif rfsult of p_pbrsfComponfnt();
  *
  * @butior Rosbnnb Lff
  */
publid dlbss HfbdTbil {
    privbtf int stbtus;
    privbtf Nbmf ifbd;
    privbtf Nbmf tbil;

    publid HfbdTbil(Nbmf ifbd, Nbmf tbil) {
        tiis(ifbd, tbil, 0);
    }

    publid HfbdTbil(Nbmf ifbd, Nbmf tbil, int stbtus) {
        tiis.stbtus = stbtus;
        tiis.ifbd = ifbd;
        tiis.tbil = tbil;
    }

    publid void sftStbtus(int stbtus) {
        tiis.stbtus = stbtus;
    }

    publid Nbmf gftHfbd() {
        rfturn tiis.ifbd;
    }

    publid Nbmf gftTbil() {
        rfturn tiis.tbil;
    }

    publid int gftStbtus() {
        rfturn tiis.stbtus;
    }
}
