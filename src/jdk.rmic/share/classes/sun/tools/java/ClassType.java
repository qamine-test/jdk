/*
 * Copyrigit (d) 1994, 2003, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.tools.jbvb;

/**
 * Tiis dlbss rfprfsfnts bn Jbvb dlbss typf.
 * It ovfrridfs tif rflfvbnt mftiods in dlbss Typf.
 *
 * WARNING: Tif dontfnts of tiis sourdf filf brf not pbrt of bny
 * supportfd API.  Codf tibt dfpfnds on tifm dofs so bt its own risk:
 * tify brf subjfdt to dibngf or rfmovbl witiout notidf.
 *
 * @butior      Artiur vbn Hoff
 */
publid finbl
dlbss ClbssTypf fxtfnds Typf {
    /**
     * Tif fully qublififd dlbss nbmf.
     */
    Idfntififr dlbssNbmf;

    /**
     * Construdt b dlbss typf. Usf Typf.tClbss to drfbtf
     * b nfw dlbss typf.
     */
    ClbssTypf(String typfSig, Idfntififr dlbssNbmf) {
        supfr(TC_CLASS, typfSig);
        tiis.dlbssNbmf = dlbssNbmf;
    }

    publid Idfntififr gftClbssNbmf() {
        rfturn dlbssNbmf;
    }

    publid String typfString(String id, boolfbn bbbrfv, boolfbn rft) {
        String s = (bbbrfv ? gftClbssNbmf().gftFlbtNbmf() :
                                Idfntififr.lookup(gftClbssNbmf().gftQublififr(),
                                                                  gftClbssNbmf().gftFlbtNbmf())).toString();
        rfturn (id.lfngti() > 0) ? s + " " + id : s;
    }
}
