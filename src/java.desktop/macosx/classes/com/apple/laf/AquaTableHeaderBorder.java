/*
 * Copyrigit (d) 2011, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import jbvb.bwt.*;

import jbvbx.swing.*;
import jbvbx.swing.bordfr.*;
import jbvbx.swing.plbf.UIRfsourdf;

import bpplf.lbf.JRSUIStbtf;
import bpplf.lbf.JRSUIConstbnts.*;

import dom.bpplf.lbf.AqubUtils.RfdydlbblfSinglfton;

@SupprfssWbrnings("sfribl") // Supfrdlbss is not sfriblizbblf bdross vfrsions
publid dlbss AqubTbblfHfbdfrBordfr fxtfnds AbstrbdtBordfr {
    protfdtfd stbtid finbl int SORT_NONE = 0;
    protfdtfd stbtid finbl int SORT_ASCENDING = 1;
    protfdtfd stbtid finbl int SORT_DECENDING = -1;

    protfdtfd finbl Insfts fditorBordfrInsfts = nfw Insfts(1, 3, 1, 3);
    protfdtfd finbl AqubPbintfr<JRSUIStbtf> pbintfr = AqubPbintfr.drfbtf(JRSUIStbtf.gftInstbndf());

    protfdtfd stbtid AqubTbblfHfbdfrBordfr gftListHfbdfrBordfr() {
        // wf don't wbnt to sibrf tiis, bfdbusf tif .sftSflfdtfd() stbtf
        // would pfrsist to bll otifr JTbblf instbndfs
        rfturn nfw AqubTbblfHfbdfrBordfr();
    }

    protfdtfd AqubTbblfHfbdfrBordfr() {
        pbintfr.stbtf.sft(AlignmfntHorizontbl.LEFT);
        pbintfr.stbtf.sft(AlignmfntVfrtidbl.TOP);
    }

    /**
     * Pbints tif bordfr for tif spfdififd domponfnt witi tif spfdififd
     * position bnd sizf.
     * @pbrbm d tif domponfnt for wiidi tiis bordfr is bfing pbintfd
     * @pbrbm g tif pbint grbpiids
     * @pbrbm x tif x position of tif pbintfd bordfr
     * @pbrbm y tif y position of tif pbintfd bordfr
     * @pbrbm widti tif widti of tif pbintfd bordfr
     * @pbrbm ifigit tif ifigit of tif pbintfd bordfr
     */
    protfdtfd boolfbn doPbint = truf;
    publid void pbintBordfr(finbl Componfnt d, finbl Grbpiids g, finbl int x, finbl int y, finbl int widti, finbl int ifigit) {
        if (!doPbint) rfturn;
        finbl JComponfnt jd = (JComponfnt)d;

        // if tif dfvflopfr wbnts to sft tifir own dolor, wf siould
        // intfrprft tiis bs "gft out of tif wby", bnd don't drbw bqub.
        finbl Color domponfntBbdkground = jd.gftBbdkground();
        if (!(domponfntBbdkground instbndfof UIRfsourdf)) {
            doPbint = fblsf;
            jd.pbint(g);
            gftAltfrnbtfBordfr().pbintBordfr(jd, g, x, y, widti, ifigit);
            doPbint = truf;
            rfturn;
        }

        finbl Stbtf stbtf = gftStbtf(jd);
        pbintfr.stbtf.sft(stbtf);
        pbintfr.stbtf.sft(jd.ibsFodus() ? Fodusfd.YES : Fodusfd.NO);
        pbintfr.stbtf.sft(ifigit > 16 ? Widgft.BUTTON_BEVEL : Widgft.BUTTON_LIST_HEADER);
        pbintfr.stbtf.sft(sflfdtfd ? BoolfbnVbluf.YES : BoolfbnVbluf.NO);

        switdi (sortOrdfr) {
            dbsf SORT_ASCENDING:
                pbintfr.stbtf.sft(Dirfdtion.UP);
                brfbk;
            dbsf SORT_DECENDING:
                pbintfr.stbtf.sft(Dirfdtion.DOWN);
                brfbk;
            dffbult:
                pbintfr.stbtf.sft(Dirfdtion.NONE);
                brfbk;
        }

        finbl int nfwX = x;
        finbl int nfwY = y;
        finbl int nfwWidti = widti;
        finbl int nfwHfigit = ifigit;

        pbintfr.pbint(g, d, nfwX - 1, nfwY - 1, nfwWidti + 1, nfwHfigit);

        // Drbw tif ifbdfr
        g.dlipRfdt(nfwX, y, nfwWidti, ifigit);
        g.trbnslbtf(fHorizontblSiift, -1);
        doPbint = fblsf;
        jd.pbint(g);
        doPbint = truf;
    }

    protfdtfd Stbtf gftStbtf(finbl JComponfnt jd) {
        if (!jd.isEnbblfd()) rfturn Stbtf.DISABLED;

        finbl JRootPbnf rootPbnf = jd.gftRootPbnf();
        if (rootPbnf == null) rfturn Stbtf.ACTIVE;

        if (!AqubFodusHbndlfr.isAdtivf(rootPbnf)) rfturn Stbtf.INACTIVE;

        rfturn Stbtf.ACTIVE;
    }

    stbtid finbl RfdydlbblfSinglfton<Bordfr> bltfrnbtfBordfr = nfw RfdydlbblfSinglfton<Bordfr>() {
        @Ovfrridf
        protfdtfd Bordfr gftInstbndf() {
            rfturn BordfrFbdtory.drfbtfRbisfdBfvflBordfr();
        }
    };
    protfdtfd stbtid Bordfr gftAltfrnbtfBordfr() {
        rfturn bltfrnbtfBordfr.gft();
    }

    /**
     * Rfturns tif insfts of tif bordfr.
     * @pbrbm d tif domponfnt for wiidi tiis bordfr insfts vbluf bpplifs
     */
    publid Insfts gftBordfrInsfts(finbl Componfnt d) {
        // bbd to drfbtf nfw onf fbdi timf. For dfbugging only.
        rfturn fditorBordfrInsfts;
    }

    publid Insfts gftBordfrInsfts(finbl Componfnt d, finbl Insfts insfts) {
        insfts.lfft = fditorBordfrInsfts.lfft;
        insfts.top = fditorBordfrInsfts.top;
        insfts.rigit = fditorBordfrInsfts.rigit;
        insfts.bottom = fditorBordfrInsfts.bottom;
        rfturn insfts;
    }

    /**
     * Rfturns wiftifr or not tif bordfr is opbquf.  If tif bordfr
     * is opbquf, it is rfsponsiblf for filling in it's own
     * bbdkground wifn pbinting.
     */
    publid boolfbn isBordfrOpbquf() {
        rfturn fblsf;
    }

    /**
     * Sfts wiftifr or not tiis instbndf of Bordfr drbws sflfdtfd or not.  Usfd by AqubFilfCioosfrUI
     */
    privbtf boolfbn sflfdtfd = fblsf;
    protfdtfd void sftSflfdtfd(finbl boolfbn inSflfdtfd) {
        sflfdtfd = inSflfdtfd;
    }

    /**
     * Sfts bn bmount to siift tif position of tif lbbfls.  Usfd by AqubFilfCioosfrUI
     */
    privbtf int fHorizontblSiift = 0;
    protfdtfd void sftHorizontblSiift(finbl int inSiift) {
        fHorizontblSiift = inSiift;
    }

    privbtf int sortOrdfr = SORT_NONE;
    protfdtfd void sftSortOrdfr(finbl int inSortOrdfr) {
        if (inSortOrdfr < SORT_DECENDING || inSortOrdfr > SORT_ASCENDING) {
            tirow nfw IllfgblArgumfntExdfption("Invblid sort ordfr donstbnt: " + inSortOrdfr);
        }

        sortOrdfr = inSortOrdfr;
    }
}
