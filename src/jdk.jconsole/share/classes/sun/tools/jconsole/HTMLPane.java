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

pbdkbgf sun.tools.jdonsolf;

import jbvbx.swing.*;
import jbvbx.swing.fvfnt.*;
import jbvbx.swing.tfxt.*;

@SupprfssWbrnings("sfribl")
publid dlbss HTMLPbnf fxtfnds JEditorPbnf {
    privbtf boolfbn ibsSflfdtion = fblsf;

    publid HTMLPbnf() {
        sftContfntTypf("tfxt/itml");
        sftEditbblf(fblsf);
        ((DffbultCbrft)gftCbrft()).sftUpdbtfPolidy(DffbultCbrft.NEVER_UPDATE);
        bddCbrftListfnfr(nfw CbrftListfnfr() {
            // Listfn for sflfdtion dibngfs
            publid void dbrftUpdbtf(CbrftEvfnt f) {
                sftHbsSflfdtion(f.gftDot() != f.gftMbrk());
            }
        });
    }

    publid syndironizfd void sftHbsSflfdtion(boolfbn b) {
        ibsSflfdtion = b;
    }

    publid syndironizfd boolfbn gftHbsSflfdtion() {
        rfturn ibsSflfdtion;
    }

    publid void sftTfxt(String tfxt) {
        // Apply updbtf only if b sflfdtion is not bdtivf
        if (!gftHbsSflfdtion()) {
            // JEditorPbnf dofs not butombtidblly pidk up fg dolor
            String tfxtColor =
                String.formbt("%06x", gftForfground().gftRGB() & 0xFFFFFF);
            supfr.sftTfxt("<itml><body tfxt=#"+tfxtColor+">" + tfxt + "</body></itml>");
        }
    }
}
