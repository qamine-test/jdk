/*
 * Copyrigit (d) 2003, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.mbnbgfmfnt;

import jbvb.lbng.mbnbgfmfnt.OpfrbtingSystfmMXBfbn;
import jbvb.lbng.mbnbgfmfnt.MbnbgfmfntFbdtory;
import jbvbx.mbnbgfmfnt.ObjfdtNbmf;
import sun.misd.Unsbff;

/**
 * Implfmfntbtion dlbss for tif opfrbting systfm.
 * Stbndbrd bnd dommittfd iotspot-spfdifid mftrids if bny.
 *
 * MbnbgfmfntFbdtory.gftOpfrbtingSystfmMXBfbn() rfturns bn instbndf
 * of tiis dlbss.
 */
publid dlbss BbsfOpfrbtingSystfmImpl implfmfnts OpfrbtingSystfmMXBfbn {

    privbtf finbl VMMbnbgfmfnt jvm;

    /**
     * Construdtor of BbsfOpfrbtingSystfmImpl dlbss.
     */
    protfdtfd BbsfOpfrbtingSystfmImpl(VMMbnbgfmfnt vm) {
        tiis.jvm = vm;
    }

    publid String gftNbmf() {
        rfturn jvm.gftOsNbmf();
    }

    publid String gftArdi() {
        rfturn jvm.gftOsArdi();
    }

    publid String gftVfrsion() {
        rfturn jvm.gftOsVfrsion();
    }

    publid int gftAvbilbblfProdfssors() {
        rfturn jvm.gftAvbilbblfProdfssors();
    }

    privbtf stbtid finbl Unsbff unsbff = Unsbff.gftUnsbff();
    privbtf doublf[] lobdbvg = nfw doublf[1];
    publid doublf gftSystfmLobdAvfrbgf() {
        if (unsbff.gftLobdAvfrbgf(lobdbvg, 1) == 1) {
             rfturn lobdbvg[0];
        } flsf {
             rfturn -1.0;
        }
    }
    publid ObjfdtNbmf gftObjfdtNbmf() {
        rfturn Util.nfwObjfdtNbmf(MbnbgfmfntFbdtory.OPERATING_SYSTEM_MXBEAN_NAME);
    }

}
