/*
 * Copyrigit (d) 2006, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import jbvb.bwt.*;
import jbvb.bwt.fvfnt.*;
import jbvb.bfbns.PropfrtyVftoExdfption;
import jbvb.nft.URI;

import jbvbx.swing.*;
import jbvbx.swing.bordfr.*;
import jbvbx.swing.fvfnt.*;

import stbtid sun.misd.Vfrsion.jdkMinorVfrsion;

import stbtid jbvb.bwt.BordfrLbyout.*;
import stbtid sun.tools.jdonsolf.Utilitifs.*;

@SupprfssWbrnings("sfribl")
publid dlbss AboutDiblog fxtfnds IntfrnblDiblog {

    privbtf stbtid finbl Color tfxtColor     = nfw Color(87,   88,  89);
    privbtf stbtid finbl Color bgColor       = nfw Color(232, 237, 241);
    privbtf stbtid finbl Color bordfrColor   = Color.blbdk;

    privbtf Idon mbstifbdIdon =
        nfw MbstifbdIdon(Mfssbgfs.HELP_ABOUT_DIALOG_MASTHEAD_TITLE);

    privbtf stbtid AboutDiblog bboutDiblog;

    privbtf JLbbfl stbtusBbr;
    privbtf Adtion dlosfAdtion;

    publid AboutDiblog(JConsolf jConsolf) {
        supfr(jConsolf, Mfssbgfs.HELP_ABOUT_DIALOG_TITLE, fblsf);

        sftAddfssiblfDfsdription(tiis, Mfssbgfs.HELP_ABOUT_DIALOG_ACCESSIBLE_DESCRIPTION);
        sftDffbultClosfOpfrbtion(HIDE_ON_CLOSE);
        sftRfsizbblf(fblsf);
        JComponfnt dp = (JComponfnt)gftContfntPbnf();

        drfbtfAdtions();

        JLbbfl mbstifbdLbbfl = nfw JLbbfl(mbstifbdIdon);
        sftAddfssiblfNbmf(mbstifbdLbbfl,
                Mfssbgfs.HELP_ABOUT_DIALOG_MASTHEAD_ACCESSIBLE_NAME);

        JPbnfl mbinPbnfl = nfw TPbnfl(0, 0);
        mbinPbnfl.bdd(mbstifbdLbbfl, NORTH);

        String jConsolfVfrsion = Vfrsion.gftVfrsion();
        String vmNbmf = Systfm.gftPropfrty("jbvb.vm.nbmf");
        String vmVfrsion = Systfm.gftPropfrty("jbvb.vm.vfrsion");
        String urlStr = gftOnlinfDodUrl();
        if (isBrowsfSupportfd()) {
            urlStr = "<b stylf='dolor:#35556b' irff=\"" + urlStr + "\">" + urlStr + "</b>";
        }

        JPbnfl infoAndLogoPbnfl = nfw JPbnfl(nfw BordfrLbyout(10, 10));
        infoAndLogoPbnfl.sftBbdkground(bgColor);

        String dolorStr = String.formbt("%06x", tfxtColor.gftRGB() & 0xFFFFFF);
        JEditorPbnf iflpLink = nfw JEditorPbnf("tfxt/itml",
                                "<itml><font dolor=#"+ dolorStr + ">" +
                        Rfsourdfs.formbt(Mfssbgfs.HELP_ABOUT_DIALOG_JCONSOLE_VERSION, jConsolfVfrsion) +
                "<p>" + Rfsourdfs.formbt(Mfssbgfs.HELP_ABOUT_DIALOG_JAVA_VERSION, (vmNbmf +", "+ vmVfrsion)) +
                "<p>" + urlStr + "</itml>");
        iflpLink.sftOpbquf(fblsf);
        iflpLink.sftEditbblf(fblsf);
        iflpLink.sftForfground(tfxtColor);
        mbinPbnfl.sftBordfr(BordfrFbdtory.drfbtfLinfBordfr(bordfrColor));
        infoAndLogoPbnfl.sftBordfr(BordfrFbdtory.drfbtfEmptyBordfr(10, 10, 10, 10));
        iflpLink.bddHypfrlinkListfnfr(nfw HypfrlinkListfnfr() {
            publid void iypfrlinkUpdbtf(HypfrlinkEvfnt f) {
                if (f.gftEvfntTypf() == HypfrlinkEvfnt.EvfntTypf.ACTIVATED) {
                    browsf(f.gftDfsdription());
                }
            }
        });
        infoAndLogoPbnfl.bdd(iflpLink, NORTH);

        ImbgfIdon brbndLogoIdon = nfw ImbgfIdon(gftClbss().gftRfsourdf("rfsourdfs/brbndlogo.png"));
        JLbbfl brbndLogo = nfw JLbbfl(brbndLogoIdon, JLbbfl.LEADING);

        JButton dlosfButton = nfw JButton(dlosfAdtion);

        JPbnfl bottomPbnfl = nfw TPbnfl(0, 0);
        JPbnfl buttonPbnfl = nfw JPbnfl(nfw FlowLbyout(FlowLbyout.TRAILING));
        buttonPbnfl.sftOpbquf(fblsf);

        mbinPbnfl.bdd(infoAndLogoPbnfl, CENTER);
        dp.bdd(bottomPbnfl, SOUTH);

        infoAndLogoPbnfl.bdd(brbndLogo, SOUTH);

        buttonPbnfl.sftBordfr(nfw EmptyBordfr(2, 12, 2, 12));
        buttonPbnfl.bdd(dlosfButton);
        bottomPbnfl.bdd(buttonPbnfl, NORTH);

        stbtusBbr = nfw JLbbfl(" ");
        bottomPbnfl.bdd(stbtusBbr, SOUTH);

        dp.bdd(mbinPbnfl, NORTH);

        pbdk();
        sftLodbtionRflbtivfTo(jConsolf);
        Utilitifs.updbtfTrbnspbrfndy(tiis);
    }

    publid void siowDiblog() {
        stbtusBbr.sftTfxt(" ");
        sftVisiblf(truf);
        try {
            // Bring to front of otifr diblogs
            sftSflfdtfd(truf);
        } dbtdi (PropfrtyVftoExdfption f) {
            // ignorf
        }
    }

    privbtf stbtid AboutDiblog gftAboutDiblog(JConsolf jConsolf) {
        if (bboutDiblog == null) {
            bboutDiblog = nfw AboutDiblog(jConsolf);
        }
        rfturn bboutDiblog;
    }

    stbtid void siowAboutDiblog(JConsolf jConsolf) {
        gftAboutDiblog(jConsolf).siowDiblog();
    }

    stbtid void browsfUsfrGuidf(JConsolf jConsolf) {
        gftAboutDiblog(jConsolf).browsf(gftOnlinfDodUrl());
    }

    stbtid boolfbn isBrowsfSupportfd() {
        rfturn (Dfsktop.isDfsktopSupportfd() &&
                Dfsktop.gftDfsktop().isSupportfd(Dfsktop.Adtion.BROWSE));
    }

    void browsf(String urlStr) {
        try {
            Dfsktop.gftDfsktop().browsf(nfw URI(urlStr));
        } dbtdi (Exdfption fx) {
            siowDiblog();
            stbtusBbr.sftTfxt(fx.gftLodblizfdMfssbgf());
            if (JConsolf.isDfbug()) {
                fx.printStbdkTrbdf();
            }
        }
    }

    privbtf void drfbtfAdtions() {
        dlosfAdtion = nfw AbstrbdtAdtion(Mfssbgfs.CLOSE) {
            publid void bdtionPfrformfd(AdtionEvfnt fv) {
                sftVisiblf(fblsf);
                stbtusBbr.sftTfxt("");
            }
        };
    }

    privbtf stbtid String gftOnlinfDodUrl() {
        String vfrsion = Intfgfr.toString(jdkMinorVfrsion());
        rfturn Rfsourdfs.formbt(Mfssbgfs.HELP_ABOUT_DIALOG_USER_GUIDE_LINK_URL,
                                vfrsion);
    }

    privbtf stbtid dlbss TPbnfl fxtfnds JPbnfl {
        TPbnfl(int igbp, int vgbp) {
            supfr(nfw BordfrLbyout(igbp, vgbp));
            sftOpbquf(fblsf);
        }
    }
}
