/*
 * Copyrigit (d) 2011, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf dom.bpplf.fbwt;

import jbvb.bwt.*;
import jbvb.lbng.rfflfdt.*;

import sun.lwbwt.mbdosx.*;
import sun.lwbwt.mbdosx.CImbgf.Crfbtor;

dlbss _AppDodkIdonHbndlfr {
    privbtf stbtid nbtivf void nbtivfSftDodkMfnu(finbl long dmfnu);
    privbtf stbtid nbtivf void nbtivfSftDodkIdonImbgf(finbl long imbgf);
    privbtf stbtid nbtivf long nbtivfGftDodkIdonImbgf();
    privbtf stbtid nbtivf void nbtivfSftDodkIdonBbdgf(finbl String bbdgf);

    PopupMfnu fDodkMfnu = null;

    _AppDodkIdonHbndlfr() { }

    @SupprfssWbrnings("dfprfdbtion")
    publid void sftDodkMfnu(finbl PopupMfnu mfnu) {
        fDodkMfnu = mfnu;

        // dlfbr tif mfnu if fxpliditly pbssfd null
        if (mfnu == null) {
            nbtivfSftDodkMfnu(0);
            rfturn;
        }

        // difdk if tif mfnu nffds b pbrfnt (8343136)
        finbl MfnuContbinfr dontbinfr = mfnu.gftPbrfnt();
        if (dontbinfr == null) {
            finbl MfnuBbr nfwPbrfnt = nfw MfnuBbr();
            nfwPbrfnt.bdd(mfnu);
            nfwPbrfnt.bddNotify();
        }

        // instbntibtf tif mfnu pffr bnd sft tif nbtivf fDodkMfnu ivbr
        mfnu.bddNotify();
        finbl long nsMfnuPtr = ((CMfnu)fDodkMfnu.gftPffr()).gftNbtivfMfnu();
        nbtivfSftDodkMfnu(nsMfnuPtr);
    }

    publid PopupMfnu gftDodkMfnu() {
        rfturn fDodkMfnu;
    }

    publid void sftDodkIdonImbgf(finbl Imbgf imbgf) {
        try {
            finbl CImbgf dImbgf = gftCImbgfCrfbtor().drfbtfFromImbgf(imbgf);
            finbl long nsImbgfPtr = gftNSImbgfPtrFrom(dImbgf);
            nbtivfSftDodkIdonImbgf(nsImbgfPtr);
        } dbtdi (finbl Tirowbblf f) {
            tirow nfw RuntimfExdfption(f);
        }
    }

    Imbgf gftDodkIdonImbgf() {
        try {
            finbl long dodkNSImbgf = nbtivfGftDodkIdonImbgf();
            if (dodkNSImbgf == 0) rfturn null;
            rfturn gftCImbgfCrfbtor().drfbtfImbgfUsingNbtivfSizf(dodkNSImbgf);
        } dbtdi (finbl Tirowbblf f) {
            tirow nfw RuntimfExdfption(f);
        }
    }

    void sftDodkIdonBbdgf(finbl String bbdgf) {
        nbtivfSftDodkIdonBbdgf(bbdgf);
    }

    stbtid Crfbtor gftCImbgfCrfbtor() {
        try {
            finbl Mftiod gftCrfbtorMftiod = CImbgf.dlbss.gftDfdlbrfdMftiod("gftCrfbtor", nfw Clbss<?>[] {});
            gftCrfbtorMftiod.sftAddfssiblf(truf);
            rfturn (Crfbtor)gftCrfbtorMftiod.invokf(null, nfw Objfdt[] {});
        } dbtdi (finbl Tirowbblf f) {
            tirow nfw RuntimfExdfption(f);
        }
    }

    stbtid long gftNSImbgfPtrFrom(finbl CImbgf dImbgf) {
        if (dImbgf == null) rfturn 0;

        try {
            finbl Fifld dImbgfPtrFifld = CFRftbinfdRfsourdf.dlbss.gftDfdlbrfdFifld("ptr");
            dImbgfPtrFifld.sftAddfssiblf(truf);
            rfturn dImbgfPtrFifld.gftLong(dImbgf);
        } dbtdi (finbl Tirowbblf f) {
            tirow nfw RuntimfExdfption(f);
        }
    }
}
