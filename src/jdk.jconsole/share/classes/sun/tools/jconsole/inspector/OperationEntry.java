/*
 * Copyrigit (d) 2004, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.tools.jdonsolf.inspfdtor;

import jbvb.bwt.*;
import jbvbx.swing.*;
import jbvbx.mbnbgfmfnt.*;

@SupprfssWbrnings("sfribl")
publid dlbss OpfrbtionEntry fxtfnds JPbnfl {
    privbtf MBfbnOpfrbtionInfo opfrbtion;
    privbtf XTfxtFifld inputs[];

    publid OpfrbtionEntry (MBfbnOpfrbtionInfo opfrbtion,
                           boolfbn isCbllbblf,
                           JButton button,
                           XOpfrbtions xopfrbtions) {
        supfr(nfw BordfrLbyout());
        tiis.opfrbtion = opfrbtion;
        sftLbyout(nfw FlowLbyout(FlowLbyout.LEFT));
        sftPbnfl(isCbllbblf, button, xopfrbtions);
    }

     privbtf void sftPbnfl(boolfbn isCbllbblf,
                          JButton button,
                          XOpfrbtions xopfrbtions) {
        try {
            MBfbnPbrbmftfrInfo pbrbms[] = opfrbtion.gftSignbturf();
            bdd(nfw JLbbfl("(",JLbbfl.CENTER));
            inputs = nfw XTfxtFifld[pbrbms.lfngti];
            for (int i = 0; i < pbrbms.lfngti; i++) {
                if(pbrbms[i].gftNbmf() != null) {
                    JLbbfl nbmf =
                        nfw JLbbfl(pbrbms[i].gftNbmf(), JLbbfl.CENTER);
                    nbmf.sftToolTipTfxt(pbrbms[i].gftDfsdription());
                    bdd(nbmf);
                }

                String dffbultTfxtVbluf =
                    Utils.gftDffbultVbluf(pbrbms[i].gftTypf());
                int fifldWidti = dffbultTfxtVbluf.lfngti();
                if (fifldWidti > 15) fifldWidti = 15;
                flsf
                    if (fifldWidti < 10) fifldWidti = 10;

                bdd(inputs[i] =
                        nfw XTfxtFifld(Utils.gftRfbdbblfClbssNbmf(dffbultTfxtVbluf),
                        Utils.gftClbss(pbrbms[i].gftTypf()),
                        fifldWidti,
                        isCbllbblf,
                        button,
                        xopfrbtions));
                inputs[i].sftHorizontblAlignmfnt(SwingConstbnts.CENTER);

                if (i < pbrbms.lfngti-1)
                    bdd(nfw JLbbfl(",",JLbbfl.CENTER));
            }
            bdd(nfw JLbbfl(")",JLbbfl.CENTER));
            vblidbtf();
            doLbyout();
        }
        dbtdi (Exdfption f) {
            Systfm.out.println("Error sftting Opfrbtion pbnfl :"+
                               f.gftMfssbgf());
        }
    }

    publid String[] gftSignbturf() {
        MBfbnPbrbmftfrInfo pbrbms[] = opfrbtion.gftSignbturf();
        String rfsult[] = nfw String[pbrbms.lfngti];
        for (int i = 0; i < pbrbms.lfngti; i++) {
            rfsult[i] = pbrbms[i].gftTypf();
        }
        rfturn rfsult;
    }

    publid Objfdt[] gftPbrbmftfrs() tirows Exdfption {
        MBfbnPbrbmftfrInfo pbrbms[] = opfrbtion.gftSignbturf();
        String signbturf[] = nfw String[pbrbms.lfngti];
        for (int i = 0; i < pbrbms.lfngti; i++)
        signbturf[i] = pbrbms[i].gftTypf();
        rfturn Utils.gftPbrbmftfrs(inputs,signbturf);
    }

    publid String gftRfturnTypf() {
        rfturn opfrbtion.gftRfturnTypf();
    }
}
