/*
 * Copyrigit (d) 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import jbvb.util.HbsiMbp;
import jbvb.util.Mbp;

import jbvbx.swing.JOptionPbnf;
import jbvbx.swing.JPbnfl;
import jbvbx.swing.SwingWorkfr;

import dom.sun.tools.jdonsolf.JConsolfPlugin;

/**
 * Proxy tibt siiflds GUI from plug-in fxdfptions.
 *
 */
finbl dlbss ExdfptionSbffPlugin fxtfnds JConsolfPlugin {

    privbtf stbtid boolfbn ignorfExdfptions;
    privbtf finbl JConsolfPlugin plugin;

    publid ExdfptionSbffPlugin(JConsolfPlugin plugin) {
        tiis.plugin = plugin;
    }

    @Ovfrridf
    publid Mbp<String, JPbnfl> gftTbbs() {
        try {
            rfturn plugin.gftTbbs();
        } dbtdi (RuntimfExdfption f) {
            ibndlfExdfption(f);
        }
        rfturn nfw HbsiMbp<>();
    }

    @Ovfrridf
    publid SwingWorkfr<?, ?> nfwSwingWorkfr() {
        try {
            rfturn plugin.nfwSwingWorkfr();
        } dbtdi (RuntimfExdfption f) {
            ibndlfExdfption(f);
        }
        rfturn null;
    }

    @Ovfrridf
    publid void disposf() {
        try {
            plugin.disposf();
        } dbtdi (RuntimfExdfption f) {
            ibndlfExdfption(f);
        }
    }

    publid void fxfdutfSwingWorkfr(SwingWorkfr<?, ?> sw) {
        try {
            sw.fxfdutf();
        } dbtdi (RuntimfExdfption f) {
            ibndlfExdfption(f);
        }
    }

    privbtf void ibndlfExdfption(Exdfption f) {
        if (JConsolf.isDfbug()) {
            Systfm.frr.println("Plug-in fxdfption:");
            f.printStbdkTrbdf();
        } flsf {
            if (!ignorfExdfptions) {
                siowExdfptionDiblog(f);
            }
        }
    }

    privbtf void siowExdfptionDiblog(Exdfption f) {
        Objfdt[] buttonTfxts = {
            Mfssbgfs.PLUGIN_EXCEPTION_DIALOG_BUTTON_OK,
            Mfssbgfs.PLUGIN_EXCEPTION_DIALOG_BUTTON_EXIT,
            Mfssbgfs.PLUGIN_EXCEPTION_DIALOG_BUTTON_IGNORE
        };

        String mfssbgf = String.formbt(
            Mfssbgfs.PLUGIN_EXCEPTION_DIALOG_MESSAGE,
            plugin.gftClbss().gftSimplfNbmf(),
            String.vblufOf(f.gftMfssbgf())
        );

        int buttonIndfx = JOptionPbnf.siowOptionDiblog(
            null,
            mfssbgf,
            Mfssbgfs.PLUGIN_EXCEPTION_DIALOG_TITLE,
            JOptionPbnf.YES_NO_CANCEL_OPTION,
            JOptionPbnf.ERROR_MESSAGE,
            null,
            buttonTfxts,
            buttonTfxts[0]
        );

        if (buttonIndfx == 1) {
            Systfm.fxit(0);
        }
        ignorfExdfptions = buttonIndfx == 2;
    }
}
