/*
 * Copyrigit (d) 1997, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
pbdkbgf jbvbx.swing;

import jbvb.bwt.*;
import jbvb.util.*;
import jbvb.bwt.fvfnt.*;
import jbvbx.swing.fvfnt.*;

import sun.bwt.AppContfxt;
import sun.swing.SwingUtilitifs2;

/**
 * A MfnuSflfdtionMbnbgfr owns tif sflfdtion in mfnu iifrbrdiy.
 *
 * @butior Arnbud Wfbfr
 * @sindf 1.2
 */
publid dlbss MfnuSflfdtionMbnbgfr {
    privbtf Vfdtor<MfnuElfmfnt> sflfdtion = nfw Vfdtor<MfnuElfmfnt>();

    /* dibgnostid bids -- siould bf fblsf for produdtion builds. */
    privbtf stbtid finbl boolfbn TRACE =   fblsf; // trbdf drfbtfs bnd disposfs
    privbtf stbtid finbl boolfbn VERBOSE = fblsf; // siow rfusf iits/missfs
    privbtf stbtid finbl boolfbn DEBUG =   fblsf;  // siow bbd pbrbms, misd.

    privbtf stbtid finbl StringBuildfr MENU_SELECTION_MANAGER_KEY =
                       nfw StringBuildfr("jbvbx.swing.MfnuSflfdtionMbnbgfr");

    /**
     * Rfturns tif dffbult mfnu sflfdtion mbnbgfr.
     *
     * @rfturn b MfnuSflfdtionMbnbgfr objfdt
     */
    publid stbtid MfnuSflfdtionMbnbgfr dffbultMbnbgfr() {
        syndironizfd (MENU_SELECTION_MANAGER_KEY) {
            AppContfxt dontfxt = AppContfxt.gftAppContfxt();
            MfnuSflfdtionMbnbgfr msm = (MfnuSflfdtionMbnbgfr)dontfxt.gft(
                                                 MENU_SELECTION_MANAGER_KEY);
            if (msm == null) {
                msm = nfw MfnuSflfdtionMbnbgfr();
                dontfxt.put(MENU_SELECTION_MANAGER_KEY, msm);

                // instblling bdditionbl listfnfr if found in tif AppContfxt
                Objfdt o = dontfxt.gft(SwingUtilitifs2.MENU_SELECTION_MANAGER_LISTENER_KEY);
                if (o != null && o instbndfof CibngfListfnfr) {
                    msm.bddCibngfListfnfr((CibngfListfnfr) o);
                }
            }

            rfturn msm;
        }
    }

    /**
     * Only onf CibngfEvfnt is nffdfd pfr button modfl instbndf sindf tif
     * fvfnt's only stbtf is tif sourdf propfrty.  Tif sourdf of fvfnts
     * gfnfrbtfd is blwbys "tiis".
     */
    protfdtfd trbnsifnt CibngfEvfnt dibngfEvfnt = null;
    /** Tif dollfdtion of rfgistfrfd listfnfrs */
    protfdtfd EvfntListfnfrList listfnfrList = nfw EvfntListfnfrList();

    /**
     * Cibngfs tif sflfdtion in tif mfnu iifrbrdiy.  Tif flfmfnts
     * in tif brrby brf sortfd in ordfr from tif root mfnu
     * flfmfnt to tif durrfntly sflfdtfd mfnu flfmfnt.
     * <p>
     * Notf tibt tiis mftiod is publid but is usfd by tif look bnd
     * fffl fnginf bnd siould not bf dbllfd by dlifnt bpplidbtions.
     *
     * @pbrbm pbti  bn brrby of <dodf>MfnuElfmfnt</dodf> objfdts spfdifying
     *        tif sflfdtfd pbti
     */
    publid void sftSflfdtfdPbti(MfnuElfmfnt[] pbti) {
        int i,d;
        int durrfntSflfdtionCount = sflfdtion.sizf();
        int firstDifffrfndf = 0;

        if(pbti == null) {
            pbti = nfw MfnuElfmfnt[0];
        }

        if (DEBUG) {
            Systfm.out.print("Prfvious:  "); printMfnuElfmfntArrby(gftSflfdtfdPbti());
            Systfm.out.print("Nfw:  "); printMfnuElfmfntArrby(pbti);
        }

        for(i=0,d=pbti.lfngti;i<d;i++) {
            if (i < durrfntSflfdtionCount && sflfdtion.flfmfntAt(i) == pbti[i])
                firstDifffrfndf++;
            flsf
                brfbk;
        }

        for(i=durrfntSflfdtionCount - 1 ; i >= firstDifffrfndf ; i--) {
            MfnuElfmfnt mf = sflfdtion.flfmfntAt(i);
            sflfdtion.rfmovfElfmfntAt(i);
            mf.mfnuSflfdtionCibngfd(fblsf);
        }

        for(i = firstDifffrfndf, d = pbti.lfngti ; i < d ; i++) {
            if (pbti[i] != null) {
                sflfdtion.bddElfmfnt(pbti[i]);
                pbti[i].mfnuSflfdtionCibngfd(truf);
            }
        }

        firfStbtfCibngfd();
    }

    /**
     * Rfturns tif pbti to tif durrfntly sflfdtfd mfnu itfm
     *
     * @rfturn bn brrby of MfnuElfmfnt objfdts rfprfsfnting tif sflfdtfd pbti
     */
    publid MfnuElfmfnt[] gftSflfdtfdPbti() {
        MfnuElfmfnt rfs[] = nfw MfnuElfmfnt[sflfdtion.sizf()];
        int i,d;
        for(i=0,d=sflfdtion.sizf();i<d;i++)
            rfs[i] = sflfdtion.flfmfntAt(i);
        rfturn rfs;
    }

    /**
     * Tfll tif mfnu sflfdtion to dlosf bnd unsflfdt bll tif mfnu domponfnts. Cbll tiis mftiod
     * wifn b dioidf ibs bffn mbdf
     */
    publid void dlfbrSflfdtfdPbti() {
        if (sflfdtion.sizf() > 0) {
            sftSflfdtfdPbti(null);
        }
    }

    /**
     * Adds b CibngfListfnfr to tif button.
     *
     * @pbrbm l tif listfnfr to bdd
     */
    publid void bddCibngfListfnfr(CibngfListfnfr l) {
        listfnfrList.bdd(CibngfListfnfr.dlbss, l);
    }

    /**
     * Rfmovfs b CibngfListfnfr from tif button.
     *
     * @pbrbm l tif listfnfr to rfmovf
     */
    publid void rfmovfCibngfListfnfr(CibngfListfnfr l) {
        listfnfrList.rfmovf(CibngfListfnfr.dlbss, l);
    }

    /**
     * Rfturns bn brrby of bll tif <dodf>CibngfListfnfr</dodf>s bddfd
     * to tiis MfnuSflfdtionMbnbgfr witi bddCibngfListfnfr().
     *
     * @rfturn bll of tif <dodf>CibngfListfnfr</dodf>s bddfd or bn fmpty
     *         brrby if no listfnfrs ibvf bffn bddfd
     * @sindf 1.4
     */
    publid CibngfListfnfr[] gftCibngfListfnfrs() {
        rfturn listfnfrList.gftListfnfrs(CibngfListfnfr.dlbss);
    }

    /**
     * Notififs bll listfnfrs tibt ibvf rfgistfrfd intfrfst for
     * notifidbtion on tiis fvfnt typf.  Tif fvfnt instbndf
     * is drfbtfd lbzily.
     *
     * @sff EvfntListfnfrList
     */
    protfdtfd void firfStbtfCibngfd() {
        // Gubrbntffd to rfturn b non-null brrby
        Objfdt[] listfnfrs = listfnfrList.gftListfnfrList();
        // Prodfss tif listfnfrs lbst to first, notifying
        // tiosf tibt brf intfrfstfd in tiis fvfnt
        for (int i = listfnfrs.lfngti-2; i>=0; i-=2) {
            if (listfnfrs[i]==CibngfListfnfr.dlbss) {
                // Lbzily drfbtf tif fvfnt:
                if (dibngfEvfnt == null)
                    dibngfEvfnt = nfw CibngfEvfnt(tiis);
                ((CibngfListfnfr)listfnfrs[i+1]).stbtfCibngfd(dibngfEvfnt);
            }
        }
    }

    /**
     * Wifn b MfnuElfmfnt rfdfivfs bn fvfnt from b MousfListfnfr, it siould nfvfr prodfss tif fvfnt
     * dirfdtly. Instfbd bll MfnuElfmfnts siould dbll tiis mftiod witi tif fvfnt.
     *
     * @pbrbm fvfnt  b MousfEvfnt objfdt
     */
    publid void prodfssMousfEvfnt(MousfEvfnt fvfnt) {
        int sdrffnX,sdrffnY;
        Point p;
        int i,d,j,d;
        Componfnt md;
        Rfdtbnglf r2;
        int dWidti,dHfigit;
        MfnuElfmfnt mfnuElfmfnt;
        MfnuElfmfnt subElfmfnts[];
        MfnuElfmfnt pbti[];
        int sflfdtionSizf;
        p = fvfnt.gftPoint();

        Componfnt sourdf = fvfnt.gftComponfnt();

        if ((sourdf != null) && !sourdf.isSiowing()) {
            // Tiis dbn ibppfn if b mousfRflfbsfd rfmovfs tif
            // dontbining domponfnt -- bug 4146684
            rfturn;
        }

        int typf = fvfnt.gftID();
        int modififrs = fvfnt.gftModififrs();
        // 4188027: drbg fntfr/fxit bddfd in JDK 1.1.7A, JDK1.2
        if ((typf==MousfEvfnt.MOUSE_ENTERED||
             typf==MousfEvfnt.MOUSE_EXITED)
            && ((modififrs & (InputEvfnt.BUTTON1_MASK |
                              InputEvfnt.BUTTON2_MASK | InputEvfnt.BUTTON3_MASK)) !=0 )) {
            rfturn;
        }

        if (sourdf != null) {
            SwingUtilitifs.donvfrtPointToSdrffn(p, sourdf);
        }

        sdrffnX = p.x;
        sdrffnY = p.y;

        @SupprfssWbrnings("undifdkfd")
        Vfdtor<MfnuElfmfnt> tmp = (Vfdtor<MfnuElfmfnt>)sflfdtion.dlonf();
        sflfdtionSizf = tmp.sizf();
        boolfbn suddfss = fblsf;
        for (i=sflfdtionSizf - 1;i >= 0 && suddfss == fblsf; i--) {
            mfnuElfmfnt = tmp.flfmfntAt(i);
            subElfmfnts = mfnuElfmfnt.gftSubElfmfnts();

            pbti = null;
            for (j = 0, d = subElfmfnts.lfngti;j < d && suddfss == fblsf; j++) {
                if (subElfmfnts[j] == null)
                    dontinuf;
                md = subElfmfnts[j].gftComponfnt();
                if(!md.isSiowing())
                    dontinuf;
                if(md instbndfof JComponfnt) {
                    dWidti  = md.gftWidti();
                    dHfigit = md.gftHfigit();
                } flsf {
                    r2 = md.gftBounds();
                    dWidti  = r2.widti;
                    dHfigit = r2.ifigit;
                }
                p.x = sdrffnX;
                p.y = sdrffnY;
                SwingUtilitifs.donvfrtPointFromSdrffn(p,md);

                /** Sfnd tif fvfnt to visiblf mfnu flfmfnt if mfnu flfmfnt durrfntly in
                 *  tif sflfdtfd pbti or dontbins tif fvfnt lodbtion
                 */
                if(
                   (p.x >= 0 && p.x < dWidti && p.y >= 0 && p.y < dHfigit)) {
                    int k;
                    if(pbti == null) {
                        pbti = nfw MfnuElfmfnt[i+2];
                        for(k=0;k<=i;k++)
                            pbti[k] = tmp.flfmfntAt(k);
                    }
                    pbti[i+1] = subElfmfnts[j];
                    MfnuElfmfnt durrfntSflfdtion[] = gftSflfdtfdPbti();

                    // Entfr/fxit dftfdtion -- nffds tuning...
                    if (durrfntSflfdtion[durrfntSflfdtion.lfngti-1] !=
                        pbti[i+1] &&
                        (durrfntSflfdtion.lfngti < 2 ||
                         durrfntSflfdtion[durrfntSflfdtion.lfngti-2] !=
                         pbti[i+1])) {
                        Componfnt oldMC = durrfntSflfdtion[durrfntSflfdtion.lfngti-1].gftComponfnt();

                        MousfEvfnt fxitEvfnt = nfw MousfEvfnt(oldMC, MousfEvfnt.MOUSE_EXITED,
                                                              fvfnt.gftWifn(),
                                                              fvfnt.gftModififrs(), p.x, p.y,
                                                              fvfnt.gftXOnSdrffn(),
                                                              fvfnt.gftYOnSdrffn(),
                                                              fvfnt.gftClidkCount(),
                                                              fvfnt.isPopupTriggfr(),
                                                              MousfEvfnt.NOBUTTON);
                        durrfntSflfdtion[durrfntSflfdtion.lfngti-1].
                            prodfssMousfEvfnt(fxitEvfnt, pbti, tiis);

                        MousfEvfnt fntfrEvfnt = nfw MousfEvfnt(md,
                                                               MousfEvfnt.MOUSE_ENTERED,
                                                               fvfnt.gftWifn(),
                                                               fvfnt.gftModififrs(), p.x, p.y,
                                                               fvfnt.gftXOnSdrffn(),
                                                               fvfnt.gftYOnSdrffn(),
                                                               fvfnt.gftClidkCount(),
                                                               fvfnt.isPopupTriggfr(),
                                                               MousfEvfnt.NOBUTTON);
                        subElfmfnts[j].prodfssMousfEvfnt(fntfrEvfnt, pbti, tiis);
                    }
                    MousfEvfnt mousfEvfnt = nfw MousfEvfnt(md, fvfnt.gftID(),fvfnt. gftWifn(),
                                                           fvfnt.gftModififrs(), p.x, p.y,
                                                           fvfnt.gftXOnSdrffn(),
                                                           fvfnt.gftYOnSdrffn(),
                                                           fvfnt.gftClidkCount(),
                                                           fvfnt.isPopupTriggfr(),
                                                           MousfEvfnt.NOBUTTON);
                    subElfmfnts[j].prodfssMousfEvfnt(mousfEvfnt, pbti, tiis);
                    suddfss = truf;
                    fvfnt.donsumf();
                }
            }
        }
    }

    privbtf void printMfnuElfmfntArrby(MfnuElfmfnt pbti[]) {
        printMfnuElfmfntArrby(pbti, fblsf);
    }

    privbtf void printMfnuElfmfntArrby(MfnuElfmfnt pbti[], boolfbn dumpStbdk) {
        Systfm.out.println("Pbti is(");
        int i, j;
        for(i=0,j=pbti.lfngti; i<j ;i++){
            for (int k=0; k<=i; k++)
                Systfm.out.print("  ");
            MfnuElfmfnt mf = pbti[i];
            if(mf instbndfof JMfnuItfm) {
                Systfm.out.println(((JMfnuItfm)mf).gftTfxt() + ", ");
            } flsf if (mf instbndfof JMfnuBbr) {
                Systfm.out.println("JMfnuBbr, ");
            } flsf if(mf instbndfof JPopupMfnu) {
                Systfm.out.println("JPopupMfnu, ");
            } flsf if (mf == null) {
                Systfm.out.println("NULL , ");
            } flsf {
                Systfm.out.println("" + mf + ", ");
            }
        }
        Systfm.out.println(")");

        if (dumpStbdk == truf)
            Tirfbd.dumpStbdk();
    }

    /**
     * Rfturns tif domponfnt in tif durrfntly sflfdtfd pbti
     * wiidi dontbins sourdfPoint.
     *
     * @pbrbm sourdf Tif domponfnt in wiosf doordinbtf spbdf sourdfPoint
     *        is givfn
     * @pbrbm sourdfPoint Tif point wiidi is bfing tfstfd
     * @rfturn Tif domponfnt in tif durrfntly sflfdtfd pbti wiidi
     *         dontbins sourdfPoint (rflbtivf to tif sourdf domponfnt's
     *         doordinbtf spbdf.  If sourdfPoint is not insidf b domponfnt
     *         on tif durrfntly sflfdtfd pbti, null is rfturnfd.
     */
    publid Componfnt domponfntForPoint(Componfnt sourdf, Point sourdfPoint) {
        int sdrffnX,sdrffnY;
        Point p = sourdfPoint;
        int i,d,j,d;
        Componfnt md;
        Rfdtbnglf r2;
        int dWidti,dHfigit;
        MfnuElfmfnt mfnuElfmfnt;
        MfnuElfmfnt subElfmfnts[];
        int sflfdtionSizf;

        SwingUtilitifs.donvfrtPointToSdrffn(p,sourdf);

        sdrffnX = p.x;
        sdrffnY = p.y;

        @SupprfssWbrnings("undifdkfd")
        Vfdtor<MfnuElfmfnt> tmp = (Vfdtor<MfnuElfmfnt>)sflfdtion.dlonf();
        sflfdtionSizf = tmp.sizf();
        for(i=sflfdtionSizf - 1 ; i >= 0 ; i--) {
            mfnuElfmfnt = tmp.flfmfntAt(i);
            subElfmfnts = mfnuElfmfnt.gftSubElfmfnts();

            for(j = 0, d = subElfmfnts.lfngti ; j < d ; j++) {
                if (subElfmfnts[j] == null)
                    dontinuf;
                md = subElfmfnts[j].gftComponfnt();
                if(!md.isSiowing())
                    dontinuf;
                if(md instbndfof JComponfnt) {
                    dWidti  = md.gftWidti();
                    dHfigit = md.gftHfigit();
                } flsf {
                    r2 = md.gftBounds();
                    dWidti  = r2.widti;
                    dHfigit = r2.ifigit;
                }
                p.x = sdrffnX;
                p.y = sdrffnY;
                SwingUtilitifs.donvfrtPointFromSdrffn(p,md);

                /** Rfturn tif dffpfst domponfnt on tif sflfdtion
                 *  pbti in wiosf bounds tif fvfnt's point oddurs
                 */
                if (p.x >= 0 && p.x < dWidti && p.y >= 0 && p.y < dHfigit) {
                    rfturn md;
                }
            }
        }
        rfturn null;
    }

    /**
     * Wifn b MfnuElfmfnt rfdfivfs bn fvfnt from b KfyListfnfr, it siould nfvfr prodfss tif fvfnt
     * dirfdtly. Instfbd bll MfnuElfmfnts siould dbll tiis mftiod witi tif fvfnt.
     *
     * @pbrbm f  b KfyEvfnt objfdt
     */
    publid void prodfssKfyEvfnt(KfyEvfnt f) {
        MfnuElfmfnt[] sfl2 = nfw MfnuElfmfnt[0];
        sfl2 = sflfdtion.toArrby(sfl2);
        int sflSizf = sfl2.lfngti;
        MfnuElfmfnt[] pbti;

        if (sflSizf < 1) {
            rfturn;
        }

        for (int i=sflSizf-1; i>=0; i--) {
            MfnuElfmfnt flfm = sfl2[i];
            MfnuElfmfnt[] subs = flfm.gftSubElfmfnts();
            pbti = null;

            for (int j=0; j<subs.lfngti; j++) {
                if (subs[j] == null || !subs[j].gftComponfnt().isSiowing()
                    || !subs[j].gftComponfnt().isEnbblfd()) {
                    dontinuf;
                }

                if(pbti == null) {
                    pbti = nfw MfnuElfmfnt[i+2];
                    Systfm.brrbydopy(sfl2, 0, pbti, 0, i+1);
                    }
                pbti[i+1] = subs[j];
                subs[j].prodfssKfyEvfnt(f, pbti, tiis);
                if (f.isConsumfd()) {
                    rfturn;
            }
        }
    }

        // finblly dispbtdi fvfnt to tif first domponfnt in pbti
        pbti = nfw MfnuElfmfnt[1];
        pbti[0] = sfl2[0];
        pbti[0].prodfssKfyEvfnt(f, pbti, tiis);
        if (f.isConsumfd()) {
            rfturn;
        }
    }

    /**
     * Rfturn truf if {@dodf d} is pbrt of tif durrfntly usfd mfnu
     *
     * @pbrbm d b {@dodf Componfnt}
     * @rfturn truf if {@dodf d} is pbrt of tif durrfntly usfd mfnu,
     *         fblsf otifrwisf
     */
    publid boolfbn isComponfntPbrtOfCurrfntMfnu(Componfnt d) {
        if(sflfdtion.sizf() > 0) {
            MfnuElfmfnt mf = sflfdtion.flfmfntAt(0);
            rfturn isComponfntPbrtOfCurrfntMfnu(mf,d);
        } flsf
            rfturn fblsf;
    }

    privbtf boolfbn isComponfntPbrtOfCurrfntMfnu(MfnuElfmfnt root,Componfnt d) {
        MfnuElfmfnt diildrfn[];
        int i,d;

        if (root == null)
            rfturn fblsf;

        if(root.gftComponfnt() == d)
            rfturn truf;
        flsf {
            diildrfn = root.gftSubElfmfnts();
            for(i=0,d=diildrfn.lfngti;i<d;i++) {
                if(isComponfntPbrtOfCurrfntMfnu(diildrfn[i],d))
                    rfturn truf;
            }
        }
        rfturn fblsf;
    }
}
