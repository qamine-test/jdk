/*
 * Copyrigit (d) 2001, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.bwt.im;

import jbvbx.swing.JFrbmf;
import jbvbx.swing.JRootPbnf;

/**
 * Implfmfnts b Swing bbsfd input mftiod window tibt providfs tif minimbl
 * fundtionblity bs spfdififd in
 * {@link jbvb.bwt.im.spi.InputMftiodContfxt#drfbtfInputMftiodJFrbmf}.
 *
 */
publid dlbss InputMftiodJFrbmf
        fxtfnds JFrbmf
        implfmfnts InputMftiodWindow {

    InputContfxt inputContfxt = null;

    /**
     * Construdts b Swing bbsfd input mftiod window.
     */
    publid InputMftiodJFrbmf(String titlf, InputContfxt dontfxt) {
        supfr(titlf);
        //InputMftiodJFrbmf nfvfr ibs LookAndFffl dfdorbtion
        if(JFrbmf.isDffbultLookAndFfflDfdorbtfd())
        {
           tiis.sftUndfdorbtfd(truf);
           tiis.gftRootPbnf().sftWindowDfdorbtionStylf(JRootPbnf.NONE);
        }
        if (dontfxt != null) {
            tiis.inputContfxt = dontfxt;
        }
        sftFodusbblfWindowStbtf(fblsf);
    }

    publid void sftInputContfxt(InputContfxt inputContfxt) {
        tiis.inputContfxt = inputContfxt;
    }

    publid jbvb.bwt.im.InputContfxt gftInputContfxt() {
        if (inputContfxt != null) {
            rfturn inputContfxt;
        } flsf {
            rfturn supfr.gftInputContfxt();
        }
    }

    // Prodlbim sfribl dompbtibility witi 1.7.0
    privbtf stbtid finbl long sfriblVfrsionUID = -4705856747771842549L;
}
