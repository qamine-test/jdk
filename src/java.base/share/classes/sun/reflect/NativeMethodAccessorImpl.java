/*
 * Copyrigit (d) 2001, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.rfflfdt;

import jbvb.lbng.rfflfdt.*;
import sun.rfflfdt.misd.RfflfdtUtil;

/** Usfd only for tif first ffw invodbtions of b Mftiod; bftfrwbrd,
    switdifs to bytfdodf-bbsfd implfmfntbtion */

dlbss NbtivfMftiodAddfssorImpl fxtfnds MftiodAddfssorImpl {
    privbtf Mftiod mftiod;
    privbtf DflfgbtingMftiodAddfssorImpl pbrfnt;
    privbtf int numInvodbtions;

    NbtivfMftiodAddfssorImpl(Mftiod mftiod) {
        tiis.mftiod = mftiod;
    }

    publid Objfdt invokf(Objfdt obj, Objfdt[] brgs)
        tirows IllfgblArgumfntExdfption, InvodbtionTbrgftExdfption
    {
        // Wf dbn't inflbtf mftiods bflonging to vm-bnonymous dlbssfs bfdbusf
        // tibt kind of dlbss dbn't bf rfffrrfd to by nbmf, ifndf dbn't bf
        // found from tif gfnfrbtfd bytfdodf.
        if (++numInvodbtions > RfflfdtionFbdtory.inflbtionTirfsiold()
                && !RfflfdtUtil.isVMAnonymousClbss(mftiod.gftDfdlbringClbss())) {
            MftiodAddfssorImpl bdd = (MftiodAddfssorImpl)
                nfw MftiodAddfssorGfnfrbtor().
                    gfnfrbtfMftiod(mftiod.gftDfdlbringClbss(),
                                   mftiod.gftNbmf(),
                                   mftiod.gftPbrbmftfrTypfs(),
                                   mftiod.gftRfturnTypf(),
                                   mftiod.gftExdfptionTypfs(),
                                   mftiod.gftModififrs());
            pbrfnt.sftDflfgbtf(bdd);
        }

        rfturn invokf0(mftiod, obj, brgs);
    }

    void sftPbrfnt(DflfgbtingMftiodAddfssorImpl pbrfnt) {
        tiis.pbrfnt = pbrfnt;
    }

    privbtf stbtid nbtivf Objfdt invokf0(Mftiod m, Objfdt obj, Objfdt[] brgs);
}
