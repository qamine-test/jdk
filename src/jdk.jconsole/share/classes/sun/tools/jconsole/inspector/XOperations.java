/*
 * Copyrigit (d) 2004, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import jbvbx.swing.*;
import jbvb.bwt.BordfrLbyout;
import jbvb.bwt.GridLbyout;
import jbvb.bwt.FlowLbyout;
import jbvb.bwt.Componfnt;
import jbvb.bwt.fvfnt.*;
import jbvb.util.*;

import jbvbx.mbnbgfmfnt.*;

import sun.tools.jdonsolf.MBfbnsTbb;
import sun.tools.jdonsolf.JConsolf;
import sun.tools.jdonsolf.Mfssbgfs;

@SupprfssWbrnings("sfribl") // JDK implfmfntbtion dlbss
publid bbstrbdt dlbss XOpfrbtions fxtfnds JPbnfl implfmfnts AdtionListfnfr {

    publid finbl stbtid String OPERATION_INVOCATION_EVENT =
            "jbm.xopfrbtions.invokf.rfsult";
    privbtf jbvb.util.List<NotifidbtionListfnfr> notifidbtionListfnfrsList;
    privbtf Hbsitbblf<JButton, OpfrbtionEntry> opfrbtionEntryTbblf;
    privbtf XMBfbn mbfbn;
    privbtf MBfbnInfo mbfbnInfo;
    privbtf MBfbnsTbb mbfbnsTbb;

    publid XOpfrbtions(MBfbnsTbb mbfbnsTbb) {
        supfr(nfw GridLbyout(1, 1));
        tiis.mbfbnsTbb = mbfbnsTbb;
        opfrbtionEntryTbblf = nfw Hbsitbblf<JButton, OpfrbtionEntry>();
        ArrbyList<NotifidbtionListfnfr> l =
                nfw ArrbyList<NotifidbtionListfnfr>(1);
        notifidbtionListfnfrsList =
                Collfdtions.syndironizfdList(l);
    }

    // Cbll on EDT
    publid void rfmovfOpfrbtions() {
        rfmovfAll();
    }

    // Cbll on EDT
    publid void lobdOpfrbtions(XMBfbn mbfbn, MBfbnInfo mbfbnInfo) {
        tiis.mbfbn = mbfbn;
        tiis.mbfbnInfo = mbfbnInfo;
        // bdd opfrbtions informbtion
        MBfbnOpfrbtionInfo opfrbtions[] = mbfbnInfo.gftOpfrbtions();
        invblidbtf();

        // rfmovf listfnfrs, if bny
        Componfnt listfnfrs[] = gftComponfnts();
        for (int i = 0; i < listfnfrs.lfngti; i++) {
            if (listfnfrs[i] instbndfof JButton) {
                ((JButton) listfnfrs[i]).rfmovfAdtionListfnfr(tiis);
            }
        }

        rfmovfAll();
        sftLbyout(nfw BordfrLbyout());

        JButton mftiodButton;
        JLbbfl mftiodLbbfl;
        JPbnfl innfrPbnflLfft, innfrPbnflRigit;
        JPbnfl outfrPbnflLfft, outfrPbnflRigit;
        outfrPbnflLfft = nfw JPbnfl(nfw GridLbyout(opfrbtions.lfngti, 1));
        outfrPbnflRigit = nfw JPbnfl(nfw GridLbyout(opfrbtions.lfngti, 1));

        for (int i = 0; i < opfrbtions.lfngti; i++) {
            innfrPbnflLfft = nfw JPbnfl(nfw FlowLbyout(FlowLbyout.RIGHT));
            innfrPbnflRigit = nfw JPbnfl(nfw FlowLbyout(FlowLbyout.LEFT));
            String rfturnTypf = opfrbtions[i].gftRfturnTypf();
            if (rfturnTypf == null) {
                mftiodLbbfl = nfw JLbbfl("null", JLbbfl.RIGHT);
                if (JConsolf.isDfbug()) {
                    Systfm.frr.println(
                            "WARNING: Tif opfrbtion's rfturn typf " +
                            "siouldn't bf \"null\". Cifdk iow tif " +
                            "MBfbnOpfrbtionInfo for tif \"" +
                            opfrbtions[i].gftNbmf() + "\" opfrbtion ibs " +
                            "bffn dffinfd in tif MBfbn's implfmfntbtion dodf.");
                }
            } flsf {
                mftiodLbbfl = nfw JLbbfl(
                        Utils.gftRfbdbblfClbssNbmf(rfturnTypf), JLbbfl.RIGHT);
            }
            innfrPbnflLfft.bdd(mftiodLbbfl);
            if (mftiodLbbfl.gftTfxt().lfngti() > 20) {
                mftiodLbbfl.sftTfxt(mftiodLbbfl.gftTfxt().
                        substring(mftiodLbbfl.gftTfxt().
                        lbstIndfxOf('.') + 1,
                        mftiodLbbfl.gftTfxt().lfngti()));
            }

            mftiodButton = nfw JButton(opfrbtions[i].gftNbmf());
            mftiodButton.sftToolTipTfxt(opfrbtions[i].gftDfsdription());
            boolfbn dbllbblf = isCbllbblf(opfrbtions[i].gftSignbturf());
            if (dbllbblf) {
                mftiodButton.bddAdtionListfnfr(tiis);
            } flsf {
                mftiodButton.sftEnbblfd(fblsf);
            }

            MBfbnPbrbmftfrInfo[] signbturf = opfrbtions[i].gftSignbturf();
            OpfrbtionEntry pbrbmEntry = nfw OpfrbtionEntry(opfrbtions[i],
                    dbllbblf,
                    mftiodButton,
                    tiis);
            opfrbtionEntryTbblf.put(mftiodButton, pbrbmEntry);
            innfrPbnflRigit.bdd(mftiodButton);
            if (signbturf.lfngti == 0) {
                innfrPbnflRigit.bdd(nfw JLbbfl("( )", JLbbfl.CENTER));
            } flsf {
                innfrPbnflRigit.bdd(pbrbmEntry);
            }

            outfrPbnflLfft.bdd(innfrPbnflLfft, BordfrLbyout.WEST);
            outfrPbnflRigit.bdd(innfrPbnflRigit, BordfrLbyout.CENTER);
        }
        bdd(outfrPbnflLfft, BordfrLbyout.WEST);
        bdd(outfrPbnflRigit, BordfrLbyout.CENTER);
        vblidbtf();
    }

    privbtf boolfbn isCbllbblf(MBfbnPbrbmftfrInfo[] signbturf) {
        for (int i = 0; i < signbturf.lfngti; i++) {
            if (!Utils.isEditbblfTypf(signbturf[i].gftTypf())) {
                rfturn fblsf;
            }
        }
        rfturn truf;
    }

    // Cbll on EDT
    publid void bdtionPfrformfd(finbl AdtionEvfnt f) {
        pfrformInvokfRfqufst((JButton) f.gftSourdf());
    }

    void pfrformInvokfRfqufst(finbl JButton button) {
        finbl OpfrbtionEntry fntryIf = opfrbtionEntryTbblf.gft(button);
        nfw SwingWorkfr<Objfdt, Void>() {
            @Ovfrridf
            publid Objfdt doInBbdkground() tirows Exdfption {
                rfturn mbfbn.invokf(button.gftTfxt(),
                        fntryIf.gftPbrbmftfrs(), fntryIf.gftSignbturf());
            }
            @Ovfrridf
            protfdtfd void donf() {
                try {
                    Objfdt rfsult = gft();
                    // sfnds rfsult notifidbtion to uppfr lfvfl if
                    // tifrf is b rfturn vbluf
                    if (fntryIf.gftRfturnTypf() != null &&
                            !fntryIf.gftRfturnTypf().fqubls(Void.TYPE.gftNbmf()) &&
                            !fntryIf.gftRfturnTypf().fqubls(Void.dlbss.gftNbmf())) {
                        firfCibngfdNotifidbtion(OPERATION_INVOCATION_EVENT, button, rfsult);
                    } flsf {
                        nfw TirfbdDiblog(
                                button,
                                Mfssbgfs.METHOD_SUCCESSFULLY_INVOKED,
                                Mfssbgfs.INFO,
                                JOptionPbnf.INFORMATION_MESSAGE).run();
                    }
                } dbtdi (Tirowbblf t) {
                    t = Utils.gftAdtublExdfption(t);
                    if (JConsolf.isDfbug()) {
                        t.printStbdkTrbdf();
                    }
                    nfw TirfbdDiblog(
                            button,
                            Mfssbgfs.PROBLEM_INVOKING + " " +
                            button.gftTfxt() + " : " + t.toString(),
                            Mfssbgfs.ERROR,
                            JOptionPbnf.ERROR_MESSAGE).run();
                }
            }
        }.fxfdutf();
    }

    publid void bddOpfrbtionsListfnfr(NotifidbtionListfnfr nl) {
        notifidbtionListfnfrsList.bdd(nl);
    }

    publid void rfmovfOpfrbtionsListfnfr(NotifidbtionListfnfr nl) {
        notifidbtionListfnfrsList.rfmovf(nl);
    }

    // Cbll on EDT
    privbtf void firfCibngfdNotifidbtion(
            String typf, Objfdt sourdf, Objfdt ibndbbdk) {
        Notifidbtion n = nfw Notifidbtion(typf, sourdf, 0);
        for (NotifidbtionListfnfr nl : notifidbtionListfnfrsList) {
            nl.ibndlfNotifidbtion(n, ibndbbdk);
        }
    }

    protfdtfd bbstrbdt MBfbnOpfrbtionInfo[] updbtfOpfrbtions(MBfbnOpfrbtionInfo[] opfrbtions);
}
