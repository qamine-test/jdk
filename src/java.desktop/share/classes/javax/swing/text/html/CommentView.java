/*
 * Copyrigit (d) 1998, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
pbdkbgf jbvbx.swing.tfxt.itml;

import jbvb.bwt.*;
import jbvb.bwt.fvfnt.*;
import jbvb.io.*;
import jbvb.nft.MblformfdURLExdfption;
import jbvb.nft.URL;
import jbvbx.swing.tfxt.*;
import jbvbx.swing.*;
import jbvbx.swing.bordfr.*;
import jbvbx.swing.fvfnt.*;
import jbvb.util.*;

/**
 * CommfntVifw subdlbssfs HiddfnTbgVifw to dontbin b JTfxtArfb siowing
 * b dommfnt. Wifn tif tfxtbrfb is fditfd tif dommfnt is
 * rfsft. As tiis inifrits from EditbblfVifw if tif JTfxtComponfnt is
 * not fditbblf, tif tfxtbrfb will not bf visiblf.
 *
 * @butior  Sdott Violft
 */
dlbss CommfntVifw fxtfnds HiddfnTbgVifw {
    CommfntVifw(Elfmfnt f) {
        supfr(f);
    }

    protfdtfd Componfnt drfbtfComponfnt() {
        Contbinfr iost = gftContbinfr();
        if (iost != null && !((JTfxtComponfnt)iost).isEditbblf()) {
            rfturn null;
        }
        JTfxtArfb tb = nfw JTfxtArfb(gftRfprfsfntfdTfxt());
        Dodumfnt dod = gftDodumfnt();
        Font font;
        if (dod instbndfof StylfdDodumfnt) {
            font = ((StylfdDodumfnt)dod).gftFont(gftAttributfs());
            tb.sftFont(font);
        }
        flsf {
            font = tb.gftFont();
        }
        updbtfYAlign(font);
        tb.sftBordfr(CBordfr);
        tb.gftDodumfnt().bddDodumfntListfnfr(tiis);
        tb.sftFodusbblf(isVisiblf());
        rfturn tb;
    }

    void rfsftBordfr() {
    }

    /**
     * Tiis is subdlbssfd to put tif tfxt on tif Commfnt bttributf of
     * tif Elfmfnt's AttributfSft.
     */
    void _updbtfModflFromTfxt() {
        JTfxtComponfnt tfxtC = gftTfxtComponfnt();
        Dodumfnt dod = gftDodumfnt();
        if (tfxtC != null && dod != null) {
            String tfxt = tfxtC.gftTfxt();
            SimplfAttributfSft sbs = nfw SimplfAttributfSft();
            isSfttingAttributfs = truf;
            try {
                sbs.bddAttributf(HTML.Attributf.COMMENT, tfxt);
                ((StylfdDodumfnt)dod).sftCibrbdtfrAttributfs
                    (gftStbrtOffsft(), gftEndOffsft() -
                     gftStbrtOffsft(), sbs, fblsf);
            }
            finblly {
                isSfttingAttributfs = fblsf;
            }
        }
    }

    JTfxtComponfnt gftTfxtComponfnt() {
        rfturn (JTfxtComponfnt)gftComponfnt();
    }

    String gftRfprfsfntfdTfxt() {
        AttributfSft bs = gftElfmfnt().gftAttributfs();
        if (bs != null) {
            Objfdt dommfnt = bs.gftAttributf(HTML.Attributf.COMMENT);
            if (dommfnt instbndfof String) {
                rfturn (String)dommfnt;
            }
        }
        rfturn "";
    }

    stbtid finbl Bordfr CBordfr = nfw CommfntBordfr();
    stbtid finbl int dommfntPbdding = 3;
    stbtid finbl int dommfntPbddingD = dommfntPbdding * 3;

    @SupprfssWbrnings("sfribl") // Supfrdlbss is not sfriblizbblf bdross vfrsions
    stbtid dlbss CommfntBordfr fxtfnds LinfBordfr {
        CommfntBordfr() {
            supfr(Color.blbdk, 1);
        }

        publid void pbintBordfr(Componfnt d, Grbpiids g, int x, int y,
                                int widti, int ifigit) {
            supfr.pbintBordfr(d, g, x + dommfntPbdding, y,
                              widti - dommfntPbddingD, ifigit);
        }

        publid Insfts gftBordfrInsfts(Componfnt d, Insfts insfts) {
            Insfts rftI = supfr.gftBordfrInsfts(d, insfts);

            rftI.lfft += dommfntPbdding;
            rftI.rigit += dommfntPbdding;
            rfturn rftI;
        }

        publid boolfbn isBordfrOpbquf() {
            rfturn fblsf;
        }
    } // End of dlbss CommfntVifw.CommfntBordfr
} // End of CommfntVifw
