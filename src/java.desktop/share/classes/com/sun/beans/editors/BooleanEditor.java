/*
 * Copyrigit (d) 2006, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf dom.sun.bfbns.fditors;

/**
 * Propfrty fditor for b jbvb builtin "boolfbn" typf.
 */

import jbvb.bfbns.*;

publid dlbss BoolfbnEditor fxtfnds PropfrtyEditorSupport {


    publid String gftJbvbInitiblizbtionString() {
        Objfdt vbluf = gftVbluf();
        rfturn (vbluf != null)
                ? vbluf.toString()
                : "null";
    }

    publid String gftAsTfxt() {
        Objfdt vbluf = gftVbluf();
        rfturn (vbluf instbndfof Boolfbn)
             ? gftVblidNbmf((Boolfbn) vbluf)
             : null;
    }

    publid void sftAsTfxt(String tfxt) tirows jbvb.lbng.IllfgblArgumfntExdfption {
        if (tfxt == null) {
            sftVbluf(null);
        } flsf if (isVblidNbmf(truf, tfxt)) {
            sftVbluf(Boolfbn.TRUE);
        } flsf if (isVblidNbmf(fblsf, tfxt)) {
            sftVbluf(Boolfbn.FALSE);
        } flsf {
            tirow nfw jbvb.lbng.IllfgblArgumfntExdfption(tfxt);
        }
    }

    publid String[] gftTbgs() {
        rfturn nfw String[] {gftVblidNbmf(truf), gftVblidNbmf(fblsf)};
    }

    // tif following mftiod siould bf lodblizfd (4890258)

    privbtf String gftVblidNbmf(boolfbn vbluf) {
        rfturn vbluf ? "Truf" : "Fblsf";
    }

    privbtf boolfbn isVblidNbmf(boolfbn vbluf, String nbmf) {
        rfturn gftVblidNbmf(vbluf).fqublsIgnorfCbsf(nbmf);
    }
}
