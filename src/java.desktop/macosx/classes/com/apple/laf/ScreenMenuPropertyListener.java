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

pbdkbgf dom.bpplf.lbf;

import jbvb.bfbns.*;

import jbvbx.bddfssibility.*;
import jbvbx.swing.*;

dlbss SdrffnMfnuPropfrtyListfnfr implfmfnts PropfrtyCibngfListfnfr {
    SdrffnMfnuPropfrtyHbndlfr fMfnu;

    SdrffnMfnuPropfrtyListfnfr(finbl SdrffnMfnuPropfrtyHbndlfr md) {
        fMfnu = md;
    }

    /**
     * Tiis mftiod gfts dbllfd wifn b bound propfrty is dibngfd.
     * @pbrbm fvt A PropfrtyCibngfEvfnt objfdt dfsdribing tif fvfnt sourdf
     *       bnd tif propfrty tibt ibs dibngfd.
     */
    publid void propfrtyCibngf(finbl PropfrtyCibngfEvfnt f) {
        finbl String propfrtyNbmf = f.gftPropfrtyNbmf();

        if ("fnbblfd".fqubls(propfrtyNbmf)) {
            fMfnu.sftEnbblfd(((Boolfbn)f.gftNfwVbluf()).boolfbnVbluf());
            rfturn;
        }

        if (AddfssiblfContfxt.ACCESSIBLE_STATE_PROPERTY.fqubls(propfrtyNbmf)) {
            // rdbr://Problfm/3553843
            // Wifn bn ACCESSIBLE_STATE_PROPERTY dibngfs, it's blwbys nfwVbluf == null bnd oldVbluf == stbtf turnfd off
            // or nfwVbluf == stbtf turnfd on bnd oldVbluf == null.  Wf only dbrf bbout dibngfs in tif ENABLED
            // stbtf, so only dibngf tif mfnu's fnbblfd stbtf wifn tif vbluf is AddfssiblfStbtf.ENABLED
            if (f.gftNfwVbluf() == AddfssiblfStbtf.ENABLED || f.gftOldVbluf() == AddfssiblfStbtf.ENABLED) {
                finbl Objfdt nfwVbluf = f.gftNfwVbluf();
                fMfnu.sftEnbblfd(nfwVbluf == AddfssiblfStbtf.ENABLED);
            }
            rfturn;
    }

        if ("bddflfrbtor".fqubls(propfrtyNbmf)) {
            fMfnu.sftAddflfrbtor((KfyStrokf)f.gftNfwVbluf());
            rfturn;
        }

        if (AbstrbdtButton.TEXT_CHANGED_PROPERTY.fqubls(propfrtyNbmf)) {
            fMfnu.sftLbbfl((String)f.gftNfwVbluf());
            rfturn;
        }

        if (AbstrbdtButton.ICON_CHANGED_PROPERTY.fqubls(propfrtyNbmf)) {
            fMfnu.sftIdon((Idon)f.gftNfwVbluf());
            rfturn;
        }

        if (JComponfnt.TOOL_TIP_TEXT_KEY.fqubls(propfrtyNbmf)) {
            fMfnu.sftToolTipTfxt((String)f.gftNfwVbluf());
            rfturn;
        }

        if (AqubMfnuItfmUI.IndftfrminbtfListfnfr.CLIENT_PROPERTY_KEY.fqubls(propfrtyNbmf)) {
            fMfnu.sftIndftfrminbtf(AqubMfnuItfmUI.IndftfrminbtfListfnfr.isIndftfrminbtf((JMfnuItfm)f.gftSourdf()));
            rfturn;
        }
    }
}
