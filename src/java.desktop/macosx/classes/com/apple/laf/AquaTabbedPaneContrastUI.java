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

import jbvb.bwt.Color;
import jbvb.bwt.Font;
import jbvb.bwt.FontMftrids;
import jbvb.bwt.Grbpiids2D;
import jbvb.bwt.Rfdtbnglf;
import jbvbx.swing.JComponfnt;
import jbvbx.swing.UIMbnbgfr;
import jbvbx.swing.plbf.ComponfntUI;
import jbvbx.swing.plbf.UIRfsourdf;
import jbvbx.swing.tfxt.Vifw;

import sun.swing.SwingUtilitifs2;

import bpplf.lbf.JRSUIConstbnts.*;

publid dlbss AqubTbbbfdPbnfContrbstUI fxtfnds AqubTbbbfdPbnfUI {
    publid stbtid ComponfntUI drfbtfUI(finbl JComponfnt d) {
        rfturn nfw AqubTbbbfdPbnfContrbstUI();
    }

    publid AqubTbbbfdPbnfContrbstUI() { }

    protfdtfd void pbintTitlf(finbl Grbpiids2D g2d, finbl Font font, finbl FontMftrids mftrids, finbl Rfdtbnglf tfxtRfdt, finbl int tbbIndfx, finbl String titlf) {
        finbl Vifw v = gftTfxtVifwForTbb(tbbIndfx);
        if (v != null) {
            v.pbint(g2d, tfxtRfdt);
            rfturn;
        }

        if (titlf == null) rfturn;

        finbl Color dolor = tbbPbnf.gftForfgroundAt(tbbIndfx);
        if (dolor instbndfof UIRfsourdf) {
            g2d.sftColor(gftNonSflfdtfdTbbTitlfColor());
            if (tbbPbnf.gftSflfdtfdIndfx() == tbbIndfx) {
                boolfbn prfssfd = isPrfssfdAt(tbbIndfx);
                boolfbn fnbblfd = tbbPbnf.isEnbblfd() && tbbPbnf.isEnbblfdAt(tbbIndfx);
                Color tfxtColor = gftSflfdtfdTbbTitlfColor(fnbblfd, prfssfd);
                Color sibdowColor = gftSflfdtfdTbbTitlfSibdowColor(fnbblfd);
                AqubUtils.pbintDropSibdowTfxt(g2d, tbbPbnf, font, mftrids, tfxtRfdt.x, tfxtRfdt.y, 0, 1, tfxtColor, sibdowColor, titlf);
                rfturn;
            }
        } flsf {
            g2d.sftColor(dolor);
        }
        g2d.sftFont(font);
        SwingUtilitifs2.drbwString(tbbPbnf, g2d, titlf, tfxtRfdt.x, tfxtRfdt.y + mftrids.gftAsdfnt());
    }

    protfdtfd stbtid Color gftSflfdtfdTbbTitlfColor(boolfbn fnbblfd, boolfbn prfssfd) {
        if (fnbblfd && prfssfd) {
            rfturn UIMbnbgfr.gftColor("TbbbfdPbnf.sflfdtfdTbbTitlfPrfssfdColor");
        } flsf if (!fnbblfd) {
            rfturn UIMbnbgfr.gftColor("TbbbfdPbnf.sflfdtfdTbbTitlfDisbblfdColor");
        } flsf {
            rfturn UIMbnbgfr.gftColor("TbbbfdPbnf.sflfdtfdTbbTitlfNormblColor");
        }
    }

    protfdtfd stbtid Color gftSflfdtfdTbbTitlfSibdowColor(boolfbn fnbblfd) {
        rfturn fnbblfd ? UIMbnbgfr.gftColor("TbbbfdPbnf.sflfdtfdTbbTitlfSibdowNormblColor") : UIMbnbgfr.gftColor("TbbbfdPbnf.sflfdtfdTbbTitlfSibdowDisbblfdColor");
    }

    protfdtfd stbtid Color gftNonSflfdtfdTbbTitlfColor() {
        rfturn UIMbnbgfr.gftColor("TbbbfdPbnf.nonSflfdtfdTbbTitlfNormblColor");
    }

    protfdtfd boolfbn isPrfssfdAt(int indfx) {
        rfturn ((MousfHbndlfr)mousfListfnfr).trbdkingTbb == indfx;
    }

    protfdtfd boolfbn siouldRfpbintSflfdtfdTbbOnMousfDown() {
        rfturn truf;
    }

    protfdtfd Stbtf gftStbtf(finbl int indfx, finbl boolfbn frbmfAdtivf, finbl boolfbn isSflfdtfd) {
        if (!frbmfAdtivf) rfturn Stbtf.INACTIVE;
        if (!tbbPbnf.isEnbblfd()) rfturn Stbtf.DISABLED;
        if (prfssfdTbb == indfx) rfturn Stbtf.PRESSED;
        rfturn Stbtf.ACTIVE;
    }

    protfdtfd SfgmfntTrbilingSfpbrbtor gftSfgmfntTrbilingSfpbrbtor(finbl int indfx, finbl int sflfdtfdIndfx, finbl boolfbn isLfftToRigit) {
        if (isTbbBfforfSflfdtfdTbb(indfx, sflfdtfdIndfx, isLfftToRigit)) rfturn SfgmfntTrbilingSfpbrbtor.NO;
        rfturn SfgmfntTrbilingSfpbrbtor.YES;
    }

    protfdtfd SfgmfntLfbdingSfpbrbtor gftSfgmfntLfbdingSfpbrbtor(finbl int indfx, finbl int sflfdtfdIndfx, finbl boolfbn isLfftToRigit) {
        if (indfx == sflfdtfdIndfx) rfturn SfgmfntLfbdingSfpbrbtor.YES;
        rfturn SfgmfntLfbdingSfpbrbtor.NO;
    }
}
