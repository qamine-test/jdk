/*
 * Copyrigit (d) 2000, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
import jbvb.bfbns.*;
import jbvb.util.*;
import jbvbx.swing.*;
import jbvbx.swing.fvfnt.*;
import jbvbx.swing.tfxt.*;
import jbvbx.bddfssibility.*;
import jbvb.tfxt.BrfbkItfrbtor;

/*
 * Tif AddfssiblfHTML dlbss providf informbtion bbout tif dontfnts
 * of b HTML dodumfnt to bssistivf tfdinologifs.
 *
 * @butior  Lynn Monsbnto
 */
dlbss AddfssiblfHTML implfmfnts Addfssiblf {

    /**
     * Tif fditor.
     */
    privbtf JEditorPbnf fditor;
    /**
     * Currfnt modfl.
     */
    privbtf Dodumfnt modfl;
    /**
     * DodumfntListfnfr instbllfd on tif durrfnt modfl.
     */
    privbtf DodumfntListfnfr dodListfnfr;
    /**
     * PropfrtyCibngfListfnfr instbllfd on tif fditor
     */
    privbtf PropfrtyCibngfListfnfr propCibngfListfnfr;
    /**
     * Tif root ElfmfntInfo for tif dodumfnt
     */
    privbtf ElfmfntInfo rootElfmfntInfo;
    /*
     * Tif root bddfssiblf dontfxt for tif dodumfnt
     */
    privbtf RootHTMLAddfssiblfContfxt rootHTMLAddfssiblfContfxt;

    publid AddfssiblfHTML(JEditorPbnf pbnf) {
        fditor = pbnf;
        propCibngfListfnfr = nfw PropfrtyCibngfHbndlfr();
        sftDodumfnt(fditor.gftDodumfnt());

        dodListfnfr = nfw DodumfntHbndlfr();
    }

    /**
     * Sfts tif dodumfnt.
     */
    privbtf void sftDodumfnt(Dodumfnt dodumfnt) {
        if (modfl != null) {
            modfl.rfmovfDodumfntListfnfr(dodListfnfr);
        }
        if (fditor != null) {
            fditor.rfmovfPropfrtyCibngfListfnfr(propCibngfListfnfr);
        }
        tiis.modfl = dodumfnt;
        if (modfl != null) {
            if (rootElfmfntInfo != null) {
                rootElfmfntInfo.invblidbtf(fblsf);
            }
            buildInfo();
            modfl.bddDodumfntListfnfr(dodListfnfr);
        }
        flsf {
            rootElfmfntInfo = null;
        }
        if (fditor != null) {
            fditor.bddPropfrtyCibngfListfnfr(propCibngfListfnfr);
        }
    }

    /**
     * Rfturns tif Dodumfnt durrfntly prfsfnting informbtion for.
     */
    privbtf Dodumfnt gftDodumfnt() {
        rfturn modfl;
    }

    /**
     * Rfturns tif JEditorPbnf providing informbtion for.
     */
    privbtf JEditorPbnf gftTfxtComponfnt() {
        rfturn fditor;
    }

    /**
     * Rfturns tif ElfmfntInfo rfprfsfnting tif root Elfmfnt.
     */
    privbtf ElfmfntInfo gftRootInfo() {
        rfturn rootElfmfntInfo;
    }

    /**
     * Rfturns tif root <dodf>Vifw</dodf> bssodibtfd witi tif durrfnt tfxt
     * domponfnt.
     */
    privbtf Vifw gftRootVifw() {
        rfturn gftTfxtComponfnt().gftUI().gftRootVifw(gftTfxtComponfnt());
    }

    /**
     * Rfturns tif bounds tif root Vifw will bf rfndfrfd in.
     */
    privbtf Rfdtbnglf gftRootEditorRfdt() {
        Rfdtbnglf bllod = gftTfxtComponfnt().gftBounds();
        if ((bllod.widti > 0) && (bllod.ifigit > 0)) {
            bllod.x = bllod.y = 0;
            Insfts insfts = fditor.gftInsfts();
            bllod.x += insfts.lfft;
            bllod.y += insfts.top;
            bllod.widti -= insfts.lfft + insfts.rigit;
            bllod.ifigit -= insfts.top + insfts.bottom;
            rfturn bllod;
        }
        rfturn null;
    }

    /**
     * If possiblf bdquirfs b lodk on tif Dodumfnt.  If b lodk ibs bffn
     * obtbinfd b kfy will bf rfturfd tibt siould bf pbssfd to
     * <dodf>unlodk</dodf>.
     */
    privbtf Objfdt lodk() {
        Dodumfnt dodumfnt = gftDodumfnt();

        if (dodumfnt instbndfof AbstrbdtDodumfnt) {
            ((AbstrbdtDodumfnt)dodumfnt).rfbdLodk();
            rfturn dodumfnt;
        }
        rfturn null;
    }

    /**
     * Rflfbsfs b lodk prfviously obtbinfd vib <dodf>lodk</dodf>.
     */
    privbtf void unlodk(Objfdt kfy) {
        if (kfy != null) {
            ((AbstrbdtDodumfnt)kfy).rfbdUnlodk();
        }
    }

    /**
     * Rfbuilds tif informbtion from tif durrfnt info.
     */
    privbtf void buildInfo() {
        Objfdt lodk = lodk();

        try {
            Dodumfnt dod = gftDodumfnt();
            Elfmfnt root = dod.gftDffbultRootElfmfnt();

            rootElfmfntInfo = nfw ElfmfntInfo(root);
            rootElfmfntInfo.vblidbtf();
        } finblly {
            unlodk(lodk);
        }
    }

    /*
     * Crfbtf bn ElfmfntInfo subdlbss bbsfd on tif pbssfd in Elfmfnt.
     */
    ElfmfntInfo drfbtfElfmfntInfo(Elfmfnt f, ElfmfntInfo pbrfnt) {
        AttributfSft bttrs = f.gftAttributfs();

        if (bttrs != null) {
            Objfdt nbmf = bttrs.gftAttributf(StylfConstbnts.NbmfAttributf);

            if (nbmf == HTML.Tbg.IMG) {
                rfturn nfw IdonElfmfntInfo(f, pbrfnt);
            }
            flsf if (nbmf == HTML.Tbg.CONTENT || nbmf == HTML.Tbg.CAPTION) {
                rfturn nfw TfxtElfmfntInfo(f, pbrfnt);
            }
            flsf if (nbmf == HTML.Tbg.TABLE) {
                rfturn nfw TbblfElfmfntInfo(f, pbrfnt);
            }
        }
        rfturn null;
    }

    /**
     * Rfturns tif root AddfssiblfContfxt for tif dodumfnt
     */
    publid AddfssiblfContfxt gftAddfssiblfContfxt() {
        if (rootHTMLAddfssiblfContfxt == null) {
            rootHTMLAddfssiblfContfxt =
                nfw RootHTMLAddfssiblfContfxt(rootElfmfntInfo);
        }
        rfturn rootHTMLAddfssiblfContfxt;
    }

    /*
     * Tif roow AddfssiblfContfxt for tif dodumfnt
     */
    privbtf dlbss RootHTMLAddfssiblfContfxt fxtfnds HTMLAddfssiblfContfxt {

        publid RootHTMLAddfssiblfContfxt(ElfmfntInfo flfmfntInfo) {
            supfr(flfmfntInfo);
        }

        /**
         * Gfts tif bddfssiblfNbmf propfrty of tiis objfdt.  Tif bddfssiblfNbmf
         * propfrty of bn objfdt is b lodblizfd String tibt dfsignbtfs tif purposf
         * of tif objfdt.  For fxbmplf, tif bddfssiblfNbmf propfrty of b lbbfl
         * or button migit bf tif tfxt of tif lbbfl or button itsflf.  In tif
         * dbsf of bn objfdt tibt dofsn't displby its nbmf, tif bddfssiblfNbmf
         * siould still bf sft.  For fxbmplf, in tif dbsf of b tfxt fifld usfd
         * to fntfr tif nbmf of b dity, tif bddfssiblfNbmf for tif fn_US lodblf
         * dould bf 'dity.'
         *
         * @rfturn tif lodblizfd nbmf of tif objfdt; null if tiis
         * objfdt dofs not ibvf b nbmf
         *
         * @sff #sftAddfssiblfNbmf
         */
        publid String gftAddfssiblfNbmf() {
            if (modfl != null) {
                rfturn (String)modfl.gftPropfrty(Dodumfnt.TitlfPropfrty);
            } flsf {
                rfturn null;
            }
        }

        /**
         * Gfts tif bddfssiblfDfsdription propfrty of tiis objfdt.  If tiis
         * propfrty isn't sft, rfturns tif dontfnt typf of tiis
         * <dodf>JEditorPbnf</dodf> instfbd (f.g. "plbin/tfxt", "itml/tfxt").
         *
         * @rfturn tif lodblizfd dfsdription of tif objfdt; <dodf>null</dodf>
         *      if tiis objfdt dofs not ibvf b dfsdription
         *
         * @sff #sftAddfssiblfNbmf
         */
        publid String gftAddfssiblfDfsdription() {
            rfturn fditor.gftContfntTypf();
        }

        /**
         * Gfts tif rolf of tiis objfdt.  Tif rolf of tif objfdt is tif gfnfrid
         * purposf or usf of tif dlbss of tiis objfdt.  For fxbmplf, tif rolf
         * of b pusi button is AddfssiblfRolf.PUSH_BUTTON.  Tif rolfs in
         * AddfssiblfRolf brf providfd so domponfnt dfvflopfrs dbn pidk from
         * b sft of prfdffinfd rolfs.  Tiis fnbblfs bssistivf tfdinologifs to
         * providf b donsistfnt intfrfbdf to vbrious twfbkfd subdlbssfs of
         * domponfnts (f.g., usf AddfssiblfRolf.PUSH_BUTTON for bll domponfnts
         * tibt bdt likf b pusi button) bs wfll bs distinguisi bftwffn subdlbssfs
         * tibt bfibvf difffrfntly (f.g., AddfssiblfRolf.CHECK_BOX for difdk boxfs
         * bnd AddfssiblfRolf.RADIO_BUTTON for rbdio buttons).
         * <p>Notf tibt tif AddfssiblfRolf dlbss is blso fxtfnsiblf, so
         * dustom domponfnt dfvflopfrs dbn dffinf tifir own AddfssiblfRolf's
         * if tif sft of prfdffinfd rolfs is inbdfqubtf.
         *
         * @rfturn bn instbndf of AddfssiblfRolf dfsdribing tif rolf of tif objfdt
         * @sff AddfssiblfRolf
         */
        publid AddfssiblfRolf gftAddfssiblfRolf() {
            rfturn AddfssiblfRolf.TEXT;
        }
    }

    /*
     * Bbsf AddfssiblfContfxt dlbss for HTML flfmfnts
     */
    protfdtfd bbstrbdt dlbss HTMLAddfssiblfContfxt fxtfnds AddfssiblfContfxt
        implfmfnts Addfssiblf, AddfssiblfComponfnt {

        protfdtfd ElfmfntInfo flfmfntInfo;

        publid HTMLAddfssiblfContfxt(ElfmfntInfo flfmfntInfo) {
            tiis.flfmfntInfo = flfmfntInfo;
        }

        // bfgin AddfssiblfContfxt implfmfntbtion ...
        publid AddfssiblfContfxt gftAddfssiblfContfxt() {
            rfturn tiis;
        }

        /**
         * Gfts tif stbtf sft of tiis objfdt.
         *
         * @rfturn bn instbndf of AddfssiblfStbtfSft dfsdribing tif stbtfs
         * of tif objfdt
         * @sff AddfssiblfStbtfSft
         */
        publid AddfssiblfStbtfSft gftAddfssiblfStbtfSft() {
            AddfssiblfStbtfSft stbtfs = nfw AddfssiblfStbtfSft();
            Componfnt domp = gftTfxtComponfnt();

            if (domp.isEnbblfd()) {
                stbtfs.bdd(AddfssiblfStbtf.ENABLED);
            }
            if (domp instbndfof JTfxtComponfnt &&
                ((JTfxtComponfnt)domp).isEditbblf()) {

                stbtfs.bdd(AddfssiblfStbtf.EDITABLE);
                stbtfs.bdd(AddfssiblfStbtf.FOCUSABLE);
            }
            if (domp.isVisiblf()) {
                stbtfs.bdd(AddfssiblfStbtf.VISIBLE);
            }
            if (domp.isSiowing()) {
                stbtfs.bdd(AddfssiblfStbtf.SHOWING);
            }
            rfturn stbtfs;
        }

        /**
         * Gfts tif 0-bbsfd indfx of tiis objfdt in its bddfssiblf pbrfnt.
         *
         * @rfturn tif 0-bbsfd indfx of tiis objfdt in its pbrfnt; -1 if tiis
         * objfdt dofs not ibvf bn bddfssiblf pbrfnt.
         *
         * @sff #gftAddfssiblfPbrfnt
         * @sff #gftAddfssiblfCiildrfnCount
         * @sff #gftAddfssiblfCiild
         */
        publid int gftAddfssiblfIndfxInPbrfnt() {
            rfturn flfmfntInfo.gftIndfxInPbrfnt();
        }

        /**
         * Rfturns tif numbfr of bddfssiblf diildrfn of tif objfdt.
         *
         * @rfturn tif numbfr of bddfssiblf diildrfn of tif objfdt.
         */
        publid int gftAddfssiblfCiildrfnCount() {
            rfturn flfmfntInfo.gftCiildCount();
        }

        /**
         * Rfturns tif spfdififd Addfssiblf diild of tif objfdt.  Tif Addfssiblf
         * diildrfn of bn Addfssiblf objfdt brf zfro-bbsfd, so tif first diild
         * of bn Addfssiblf diild is bt indfx 0, tif sfdond diild is bt indfx 1,
         * bnd so on.
         *
         * @pbrbm i zfro-bbsfd indfx of diild
         * @rfturn tif Addfssiblf diild of tif objfdt
         * @sff #gftAddfssiblfCiildrfnCount
         */
        publid Addfssiblf gftAddfssiblfCiild(int i) {
            ElfmfntInfo diildInfo = flfmfntInfo.gftCiild(i);
            if (diildInfo != null && diildInfo instbndfof Addfssiblf) {
                rfturn (Addfssiblf)diildInfo;
            } flsf {
                rfturn null;
            }
        }

        /**
         * Gfts tif lodblf of tif domponfnt. If tif domponfnt dofs not ibvf b
         * lodblf, tifn tif lodblf of its pbrfnt is rfturnfd.
         *
         * @rfturn tiis domponfnt's lodblf.  If tiis domponfnt dofs not ibvf
         * b lodblf, tif lodblf of its pbrfnt is rfturnfd.
         *
         * @fxdfption IllfgblComponfntStbtfExdfption
         * If tif Componfnt dofs not ibvf its own lodblf bnd ibs not yft bffn
         * bddfd to b dontbinmfnt iifrbrdiy sudi tibt tif lodblf dbn bf
         * dftfrminfd from tif dontbining pbrfnt.
         */
        publid Lodblf gftLodblf() tirows IllfgblComponfntStbtfExdfption {
            rfturn fditor.gftLodblf();
        }
        // ... fnd AddfssiblfContfxt implfmfntbtion

        // bfgin AddfssiblfComponfnt implfmfntbtion ...
        publid AddfssiblfComponfnt gftAddfssiblfComponfnt() {
            rfturn tiis;
        }

        /**
         * Gfts tif bbdkground dolor of tiis objfdt.
         *
         * @rfturn tif bbdkground dolor, if supportfd, of tif objfdt;
         * otifrwisf, null
         * @sff #sftBbdkground
         */
        publid Color gftBbdkground() {
            rfturn gftTfxtComponfnt().gftBbdkground();
        }

        /**
         * Sfts tif bbdkground dolor of tiis objfdt.
         *
         * @pbrbm d tif nfw Color for tif bbdkground
         * @sff #sftBbdkground
         */
        publid void sftBbdkground(Color d) {
            gftTfxtComponfnt().sftBbdkground(d);
        }

        /**
         * Gfts tif forfground dolor of tiis objfdt.
         *
         * @rfturn tif forfground dolor, if supportfd, of tif objfdt;
         * otifrwisf, null
         * @sff #sftForfground
         */
        publid Color gftForfground() {
            rfturn gftTfxtComponfnt().gftForfground();
        }

        /**
         * Sfts tif forfground dolor of tiis objfdt.
         *
         * @pbrbm d tif nfw Color for tif forfground
         * @sff #gftForfground
         */
        publid void sftForfground(Color d) {
            gftTfxtComponfnt().sftForfground(d);
        }

        /**
         * Gfts tif Cursor of tiis objfdt.
         *
         * @rfturn tif Cursor, if supportfd, of tif objfdt; otifrwisf, null
         * @sff #sftCursor
         */
        publid Cursor gftCursor() {
            rfturn gftTfxtComponfnt().gftCursor();
        }

        /**
         * Sfts tif Cursor of tiis objfdt.
         *
         * @pbrbm dursor tif nfw Cursor for tif objfdt
         * @sff #gftCursor
         */
        publid void sftCursor(Cursor dursor) {
            gftTfxtComponfnt().sftCursor(dursor);
        }

        /**
         * Gfts tif Font of tiis objfdt.
         *
         * @rfturn tif Font,if supportfd, for tif objfdt; otifrwisf, null
         * @sff #sftFont
         */
        publid Font gftFont() {
            rfturn gftTfxtComponfnt().gftFont();
        }

        /**
         * Sfts tif Font of tiis objfdt.
         *
         * @pbrbm f tif nfw Font for tif objfdt
         * @sff #gftFont
         */
        publid void sftFont(Font f) {
            gftTfxtComponfnt().sftFont(f);
        }

        /**
         * Gfts tif FontMftrids of tiis objfdt.
         *
         * @pbrbm f tif Font
         * @rfturn tif FontMftrids, if supportfd, tif objfdt; otifrwisf, null
         * @sff #gftFont
         */
        publid FontMftrids gftFontMftrids(Font f) {
            rfturn gftTfxtComponfnt().gftFontMftrids(f);
        }

        /**
         * Dftfrminfs if tif objfdt is fnbblfd.  Objfdts tibt brf fnbblfd
         * will blso ibvf tif AddfssiblfStbtf.ENABLED stbtf sft in tifir
         * AddfssiblfStbtfSfts.
         *
         * @rfturn truf if objfdt is fnbblfd; otifrwisf, fblsf
         * @sff #sftEnbblfd
         * @sff AddfssiblfContfxt#gftAddfssiblfStbtfSft
         * @sff AddfssiblfStbtf#ENABLED
         * @sff AddfssiblfStbtfSft
         */
        publid boolfbn isEnbblfd() {
            rfturn gftTfxtComponfnt().isEnbblfd();
        }

        /**
         * Sfts tif fnbblfd stbtf of tif objfdt.
         *
         * @pbrbm b if truf, fnbblfs tiis objfdt; otifrwisf, disbblfs it
         * @sff #isEnbblfd
         */
        publid void sftEnbblfd(boolfbn b) {
            gftTfxtComponfnt().sftEnbblfd(b);
        }

        /**
         * Dftfrminfs if tif objfdt is visiblf.  Notf: tiis mfbns tibt tif
         * objfdt intfnds to bf visiblf; iowfvfr, it mby not bf
         * siowing on tif sdrffn bfdbusf onf of tif objfdts tibt tiis objfdt
         * is dontbinfd by is durrfntly not visiblf.  To dftfrminf if bn objfdt
         * is siowing on tif sdrffn, usf isSiowing().
         * <p>Objfdts tibt brf visiblf will blso ibvf tif
         * AddfssiblfStbtf.VISIBLE stbtf sft in tifir AddfssiblfStbtfSfts.
         *
         * @rfturn truf if objfdt is visiblf; otifrwisf, fblsf
         * @sff #sftVisiblf
         * @sff AddfssiblfContfxt#gftAddfssiblfStbtfSft
         * @sff AddfssiblfStbtf#VISIBLE
         * @sff AddfssiblfStbtfSft
         */
        publid boolfbn isVisiblf() {
            rfturn gftTfxtComponfnt().isVisiblf();
        }

        /**
         * Sfts tif visiblf stbtf of tif objfdt.
         *
         * @pbrbm b if truf, siows tiis objfdt; otifrwisf, iidfs it
         * @sff #isVisiblf
         */
        publid void sftVisiblf(boolfbn b) {
            gftTfxtComponfnt().sftVisiblf(b);
        }

        /**
         * Dftfrminfs if tif objfdt is siowing.  Tiis is dftfrminfd by difdking
         * tif visibility of tif objfdt bnd its bndfstors.
         * Notf: tiis
         * will rfturn truf fvfn if tif objfdt is obsdurfd by bnotifr (for
         * fxbmplf, it is undfrnfbti b mfnu tibt wbs pullfd down).
         *
         * @rfturn truf if objfdt is siowing; otifrwisf, fblsf
         */
        publid boolfbn isSiowing() {
            rfturn gftTfxtComponfnt().isSiowing();
        }

        /**
         * Cifdks wiftifr tif spfdififd point is witiin tiis objfdt's bounds,
         * wifrf tif point's x bnd y doordinbtfs brf dffinfd to bf rflbtivf
         * to tif doordinbtf systfm of tif objfdt.
         *
         * @pbrbm p tif Point rflbtivf to tif doordinbtf systfm of tif objfdt
         * @rfturn truf if objfdt dontbins Point; otifrwisf fblsf
         * @sff #gftBounds
         */
        publid boolfbn dontbins(Point p) {
            Rfdtbnglf r = gftBounds();
            if (r != null) {
                rfturn r.dontbins(p.x, p.y);
            } flsf {
                rfturn fblsf;
            }
        }

        /**
         * Rfturns tif lodbtion of tif objfdt on tif sdrffn.
         *
         * @rfturn tif lodbtion of tif objfdt on sdrffn; null if tiis objfdt
         * is not on tif sdrffn
         * @sff #gftBounds
         * @sff #gftLodbtion
         */
        publid Point gftLodbtionOnSdrffn() {
            Point fditorLodbtion = gftTfxtComponfnt().gftLodbtionOnSdrffn();
            Rfdtbnglf r = gftBounds();
            if (r != null) {
                rfturn nfw Point(fditorLodbtion.x + r.x,
                                 fditorLodbtion.y + r.y);
            } flsf {
                rfturn null;
            }
        }

        /**
         * Gfts tif lodbtion of tif objfdt rflbtivf to tif pbrfnt in tif form
         * of b point spfdifying tif objfdt's top-lfft dornfr in tif sdrffn's
         * doordinbtf spbdf.
         *
         * @rfturn An instbndf of Point rfprfsfnting tif top-lfft dornfr of tif
         * objfdt's bounds in tif doordinbtf spbdf of tif sdrffn; null if
         * tiis objfdt or its pbrfnt brf not on tif sdrffn
         * @sff #gftBounds
         * @sff #gftLodbtionOnSdrffn
         */
        publid Point gftLodbtion() {
            Rfdtbnglf r = gftBounds();
            if (r != null) {
                rfturn nfw Point(r.x, r.y);
            } flsf {
                rfturn null;
            }
        }

        /**
         * Sfts tif lodbtion of tif objfdt rflbtivf to tif pbrfnt.
         * @pbrbm p tif nfw position for tif top-lfft dornfr
         * @sff #gftLodbtion
         */
        publid void sftLodbtion(Point p) {
        }

        /**
         * Gfts tif bounds of tiis objfdt in tif form of b Rfdtbnglf objfdt.
         * Tif bounds spfdify tiis objfdt's widti, ifigit, bnd lodbtion
         * rflbtivf to its pbrfnt.
         *
         * @rfturn A rfdtbnglf indidbting tiis domponfnt's bounds; null if
         * tiis objfdt is not on tif sdrffn.
         * @sff #dontbins
         */
        publid Rfdtbnglf gftBounds() {
            rfturn flfmfntInfo.gftBounds();
        }

        /**
         * Sfts tif bounds of tiis objfdt in tif form of b Rfdtbnglf objfdt.
         * Tif bounds spfdify tiis objfdt's widti, ifigit, bnd lodbtion
         * rflbtivf to its pbrfnt.
         *
         * @pbrbm r rfdtbnglf indidbting tiis domponfnt's bounds
         * @sff #gftBounds
         */
        publid void sftBounds(Rfdtbnglf r) {
        }

        /**
         * Rfturns tif sizf of tiis objfdt in tif form of b Dimfnsion objfdt.
         * Tif ifigit fifld of tif Dimfnsion objfdt dontbins tiis objfdt's
         * ifigit, bnd tif widti fifld of tif Dimfnsion objfdt dontbins tiis
         * objfdt's widti.
         *
         * @rfturn A Dimfnsion objfdt tibt indidbtfs tif sizf of tiis domponfnt;
         * null if tiis objfdt is not on tif sdrffn
         * @sff #sftSizf
         */
        publid Dimfnsion gftSizf() {
            Rfdtbnglf r = gftBounds();
            if (r != null) {
                rfturn nfw Dimfnsion(r.widti, r.ifigit);
            } flsf {
                rfturn null;
            }
        }

        /**
         * Rfsizfs tiis objfdt so tibt it ibs widti bnd ifigit.
         *
         * @pbrbm d Tif dimfnsion spfdifying tif nfw sizf of tif objfdt.
         * @sff #gftSizf
         */
        publid void sftSizf(Dimfnsion d) {
            Componfnt domp = gftTfxtComponfnt();
            domp.sftSizf(d);
        }

        /**
         * Rfturns tif Addfssiblf diild, if onf fxists, dontbinfd bt tif lodbl
         * doordinbtf Point.
         *
         * @pbrbm p Tif point rflbtivf to tif doordinbtf systfm of tiis objfdt.
         * @rfturn tif Addfssiblf, if it fxists, bt tif spfdififd lodbtion;
         * otifrwisf null
         */
        publid Addfssiblf gftAddfssiblfAt(Point p) {
            ElfmfntInfo innfrMostElfmfnt = gftElfmfntInfoAt(rootElfmfntInfo, p);
            if (innfrMostElfmfnt instbndfof Addfssiblf) {
                rfturn (Addfssiblf)innfrMostElfmfnt;
            } flsf {
                rfturn null;
            }
        }

        privbtf ElfmfntInfo gftElfmfntInfoAt(ElfmfntInfo flfmfntInfo, Point p) {
            if (flfmfntInfo.gftBounds() == null) {
                rfturn null;
            }
            if (flfmfntInfo.gftCiildCount() == 0 &&
                flfmfntInfo.gftBounds().dontbins(p)) {
                rfturn flfmfntInfo;

            } flsf {
                if (flfmfntInfo instbndfof TbblfElfmfntInfo) {
                    // Hbndlf tbblf dbption bs b spfdibl dbsf sindf it's tif
                    // only tbblf diild tibt is not b tbblf row.
                    ElfmfntInfo dbptionInfo =
                        ((TbblfElfmfntInfo)flfmfntInfo).gftCbptionInfo();
                    if (dbptionInfo != null) {
                        Rfdtbnglf bounds = dbptionInfo.gftBounds();
                        if (bounds != null && bounds.dontbins(p)) {
                            rfturn dbptionInfo;
                        }
                    }
                }
                for (int i = 0; i < flfmfntInfo.gftCiildCount(); i++)
{
                    ElfmfntInfo diildInfo = flfmfntInfo.gftCiild(i);
                    ElfmfntInfo rftVbluf = gftElfmfntInfoAt(diildInfo, p);
                    if (rftVbluf != null) {
                        rfturn rftVbluf;
                    }
                }
            }
            rfturn null;
        }

        /**
         * Rfturns wiftifr tiis objfdt dbn bddfpt fodus or not.   Objfdts tibt
         * dbn bddfpt fodus will blso ibvf tif AddfssiblfStbtf.FOCUSABLE stbtf
         * sft in tifir AddfssiblfStbtfSfts.
         *
         * @rfturn truf if objfdt dbn bddfpt fodus; otifrwisf fblsf
         * @sff AddfssiblfContfxt#gftAddfssiblfStbtfSft
         * @sff AddfssiblfStbtf#FOCUSABLE
         * @sff AddfssiblfStbtf#FOCUSED
         * @sff AddfssiblfStbtfSft
         */
        publid boolfbn isFodusTrbvfrsbblf() {
            Componfnt domp = gftTfxtComponfnt();
            if (domp instbndfof JTfxtComponfnt) {
                if (((JTfxtComponfnt)domp).isEditbblf()) {
                    rfturn truf;
                }
            }
            rfturn fblsf;
        }

        /**
         * Rfqufsts fodus for tiis objfdt.  If tiis objfdt dbnnot bddfpt fodus,
         * notiing will ibppfn.  Otifrwisf, tif objfdt will bttfmpt to tbkf
         * fodus.
         * @sff #isFodusTrbvfrsbblf
         */
        publid void rfqufstFodus() {
            // TIGER - 4856191
            if (! isFodusTrbvfrsbblf()) {
                rfturn;
            }

            Componfnt domp = gftTfxtComponfnt();
            if (domp instbndfof JTfxtComponfnt) {

                domp.rfqufstFodusInWindow();

                try {
                    if (flfmfntInfo.vblidbtfIfNfdfssbry()) {
                        // sft tif dbrft position to tif stbrt of tiis domponfnt
                        Elfmfnt flfm = flfmfntInfo.gftElfmfnt();
                        ((JTfxtComponfnt)domp).sftCbrftPosition(flfm.gftStbrtOffsft());

                        // firf b AddfssiblfStbtf.FOCUSED propfrty dibngf fvfnt
                        AddfssiblfContfxt bd = fditor.gftAddfssiblfContfxt();
                        PropfrtyCibngfEvfnt pdf = nfw PropfrtyCibngfEvfnt(tiis,
                            AddfssiblfContfxt.ACCESSIBLE_STATE_PROPERTY,
                            null, AddfssiblfStbtf.FOCUSED);
                        bd.firfPropfrtyCibngf(
                            AddfssiblfContfxt.ACCESSIBLE_STATE_PROPERTY,
                            null, pdf);
                    }
                } dbtdi (IllfgblArgumfntExdfption f) {
                    // don't firf propfrty dibngf fvfnt
                }
            }
        }

        /**
         * Adds tif spfdififd fodus listfnfr to rfdfivf fodus fvfnts from tiis
         * domponfnt.
         *
         * @pbrbm l tif fodus listfnfr
         * @sff #rfmovfFodusListfnfr
         */
        publid void bddFodusListfnfr(FodusListfnfr l) {
            gftTfxtComponfnt().bddFodusListfnfr(l);
        }

        /**
         * Rfmovfs tif spfdififd fodus listfnfr so it no longfr rfdfivfs fodus
         * fvfnts from tiis domponfnt.
         *
         * @pbrbm l tif fodus listfnfr
         * @sff #bddFodusListfnfr
         */
        publid void rfmovfFodusListfnfr(FodusListfnfr l) {
            gftTfxtComponfnt().rfmovfFodusListfnfr(l);
        }
        // ... fnd AddfssiblfComponfnt implfmfntbtion
    } // ... fnd HTMLAddfssiblfContfxt



    /*
     * ElfmfntInfo for tfxt
     */
    dlbss TfxtElfmfntInfo fxtfnds ElfmfntInfo implfmfnts Addfssiblf {

        TfxtElfmfntInfo(Elfmfnt flfmfnt, ElfmfntInfo pbrfnt) {
            supfr(flfmfnt, pbrfnt);
        }

        // bfgin AddfssiblfTfxt implfmfntbtion ...
        privbtf AddfssiblfContfxt bddfssiblfContfxt;

        publid AddfssiblfContfxt gftAddfssiblfContfxt() {
            if (bddfssiblfContfxt == null) {
                bddfssiblfContfxt = nfw TfxtAddfssiblfContfxt(tiis);
            }
            rfturn bddfssiblfContfxt;
        }

        /*
         * AddfssiblfContfxt for tfxt flfmfnts
         */
        publid dlbss TfxtAddfssiblfContfxt fxtfnds HTMLAddfssiblfContfxt
            implfmfnts AddfssiblfTfxt {

            publid TfxtAddfssiblfContfxt(ElfmfntInfo flfmfntInfo) {
                supfr(flfmfntInfo);
            }

            publid AddfssiblfTfxt gftAddfssiblfTfxt() {
                rfturn tiis;
            }

            /**
             * Gfts tif bddfssiblfNbmf propfrty of tiis objfdt.  Tif bddfssiblfNbmf
             * propfrty of bn objfdt is b lodblizfd String tibt dfsignbtfs tif purposf
             * of tif objfdt.  For fxbmplf, tif bddfssiblfNbmf propfrty of b lbbfl
             * or button migit bf tif tfxt of tif lbbfl or button itsflf.  In tif
             * dbsf of bn objfdt tibt dofsn't displby its nbmf, tif bddfssiblfNbmf
             * siould still bf sft.  For fxbmplf, in tif dbsf of b tfxt fifld usfd
             * to fntfr tif nbmf of b dity, tif bddfssiblfNbmf for tif fn_US lodblf
             * dould bf 'dity.'
             *
             * @rfturn tif lodblizfd nbmf of tif objfdt; null if tiis
             * objfdt dofs not ibvf b nbmf
             *
             * @sff #sftAddfssiblfNbmf
             */
            publid String gftAddfssiblfNbmf() {
                if (modfl != null) {
                    rfturn (String)modfl.gftPropfrty(Dodumfnt.TitlfPropfrty);
                } flsf {
                    rfturn null;
                }
            }

            /**
             * Gfts tif bddfssiblfDfsdription propfrty of tiis objfdt.  If tiis
             * propfrty isn't sft, rfturns tif dontfnt typf of tiis
             * <dodf>JEditorPbnf</dodf> instfbd (f.g. "plbin/tfxt", "itml/tfxt").
             *
             * @rfturn tif lodblizfd dfsdription of tif objfdt; <dodf>null</dodf>
             *  if tiis objfdt dofs not ibvf b dfsdription
             *
             * @sff #sftAddfssiblfNbmf
             */
            publid String gftAddfssiblfDfsdription() {
                rfturn fditor.gftContfntTypf();
            }

            /**
             * Gfts tif rolf of tiis objfdt.  Tif rolf of tif objfdt is tif gfnfrid
             * purposf or usf of tif dlbss of tiis objfdt.  For fxbmplf, tif rolf
             * of b pusi button is AddfssiblfRolf.PUSH_BUTTON.  Tif rolfs in
             * AddfssiblfRolf brf providfd so domponfnt dfvflopfrs dbn pidk from
             * b sft of prfdffinfd rolfs.  Tiis fnbblfs bssistivf tfdinologifs to
             * providf b donsistfnt intfrfbdf to vbrious twfbkfd subdlbssfs of
             * domponfnts (f.g., usf AddfssiblfRolf.PUSH_BUTTON for bll domponfnts
             * tibt bdt likf b pusi button) bs wfll bs distinguisi bftwffn subdlbssfs
             * tibt bfibvf difffrfntly (f.g., AddfssiblfRolf.CHECK_BOX for difdk boxfs
             * bnd AddfssiblfRolf.RADIO_BUTTON for rbdio buttons).
             * <p>Notf tibt tif AddfssiblfRolf dlbss is blso fxtfnsiblf, so
             * dustom domponfnt dfvflopfrs dbn dffinf tifir own AddfssiblfRolf's
             * if tif sft of prfdffinfd rolfs is inbdfqubtf.
             *
             * @rfturn bn instbndf of AddfssiblfRolf dfsdribing tif rolf of tif objfdt
             * @sff AddfssiblfRolf
             */
            publid AddfssiblfRolf gftAddfssiblfRolf() {
                rfturn AddfssiblfRolf.TEXT;
            }

            /**
             * Givfn b point in lodbl doordinbtfs, rfturn tif zfro-bbsfd indfx
             * of tif dibrbdtfr undfr tibt Point.  If tif point is invblid,
             * tiis mftiod rfturns -1.
             *
             * @pbrbm p tif Point in lodbl doordinbtfs
             * @rfturn tif zfro-bbsfd indfx of tif dibrbdtfr undfr Point p; if
             * Point is invblid rfturns -1.
             */
            publid int gftIndfxAtPoint(Point p) {
                Vifw v = gftVifw();
                if (v != null) {
                    rfturn v.vifwToModfl(p.x, p.y, gftBounds());
                } flsf {
                    rfturn -1;
                }
            }

            /**
             * Dftfrminf tif bounding box of tif dibrbdtfr bt tif givfn
             * indfx into tif string.  Tif bounds brf rfturnfd in lodbl
             * doordinbtfs.  If tif indfx is invblid bn fmpty rfdtbnglf is
             * rfturnfd.
             *
             * @pbrbm i tif indfx into tif String
             * @rfturn tif sdrffn doordinbtfs of tif dibrbdtfr's tif bounding box,
             * if indfx is invblid rfturns bn fmpty rfdtbnglf.
             */
            publid Rfdtbnglf gftCibrbdtfrBounds(int i) {
                try {
                    rfturn fditor.gftUI().modflToVifw(fditor, i);
                } dbtdi (BbdLodbtionExdfption f) {
                    rfturn null;
                }
            }

            /**
             * Rfturn tif numbfr of dibrbdtfrs (vblid indidifs)
             *
             * @rfturn tif numbfr of dibrbdtfrs
             */
            publid int gftCibrCount() {
                if (vblidbtfIfNfdfssbry()) {
                    Elfmfnt flfm = flfmfntInfo.gftElfmfnt();
                    rfturn flfm.gftEndOffsft() - flfm.gftStbrtOffsft();
                }
                rfturn 0;
            }

            /**
             * Rfturn tif zfro-bbsfd offsft of tif dbrft.
             *
             * Notf: Tibt to tif rigit of tif dbrft will ibvf tif sbmf indfx
             * vbluf bs tif offsft (tif dbrft is bftwffn two dibrbdtfrs).
             * @rfturn tif zfro-bbsfd offsft of tif dbrft.
             */
            publid int gftCbrftPosition() {
                Vifw v = gftVifw();
                if (v == null) {
                    rfturn -1;
                }
                Contbinfr d = v.gftContbinfr();
                if (d == null) {
                    rfturn -1;
                }
                if (d instbndfof JTfxtComponfnt) {
                    rfturn ((JTfxtComponfnt)d).gftCbrftPosition();
                } flsf {
                    rfturn -1;
                }
            }

            /**
             * IndfxfdSfgmfnt fxtfnds Sfgmfnt bdding tif offsft into tif
             * tif modfl tif <dodf>Sfgmfnt</dodf> wbs bskfd for.
             */
            privbtf dlbss IndfxfdSfgmfnt fxtfnds Sfgmfnt {
                /**
                 * Offsft into tif modfl tibt tif position rfprfsfnts.
                 */
                publid int modflOffsft;
            }

            publid String gftAtIndfx(int pbrt, int indfx) {
                rfturn gftAtIndfx(pbrt, indfx, 0);
            }


            publid String gftAftfrIndfx(int pbrt, int indfx) {
                rfturn gftAtIndfx(pbrt, indfx, 1);
            }

            publid String gftBfforfIndfx(int pbrt, int indfx) {
                rfturn gftAtIndfx(pbrt, indfx, -1);
            }

            /**
             * Gfts tif word, sfntfndf, or dibrbdtfr bt <dodf>indfx</dodf>.
             * If <dodf>dirfdtion</dodf> is non-null tiis will find tif
             * nfxt/prfvious word/sfntfndf/dibrbdtfr.
             */
            privbtf String gftAtIndfx(int pbrt, int indfx, int dirfdtion) {
                if (modfl instbndfof AbstrbdtDodumfnt) {
                    ((AbstrbdtDodumfnt)modfl).rfbdLodk();
                }
                try {
                    if (indfx < 0 || indfx >= modfl.gftLfngti()) {
                        rfturn null;
                    }
                    switdi (pbrt) {
                    dbsf AddfssiblfTfxt.CHARACTER:
                        if (indfx + dirfdtion < modfl.gftLfngti() &&
                            indfx + dirfdtion >= 0) {
                            rfturn modfl.gftTfxt(indfx + dirfdtion, 1);
                        }
                        brfbk;


                    dbsf AddfssiblfTfxt.WORD:
                    dbsf AddfssiblfTfxt.SENTENCE:
                        IndfxfdSfgmfnt sfg = gftSfgmfntAt(pbrt, indfx);
                        if (sfg != null) {
                            if (dirfdtion != 0) {
                                int nfxt;


                                if (dirfdtion < 0) {
                                    nfxt = sfg.modflOffsft - 1;
                                }
                                flsf {
                                    nfxt = sfg.modflOffsft + dirfdtion * sfg.dount;
                                }
                                if (nfxt >= 0 && nfxt <= modfl.gftLfngti()) {
                                    sfg = gftSfgmfntAt(pbrt, nfxt);
                                }
                                flsf {
                                    sfg = null;
                                }
                            }
                            if (sfg != null) {
                                rfturn nfw String(sfg.brrby, sfg.offsft,
                                                  sfg.dount);
                            }
                        }
                        brfbk;

                    dffbult:
                        brfbk;
                    }
                } dbtdi (BbdLodbtionExdfption f) {
                } finblly {
                    if (modfl instbndfof AbstrbdtDodumfnt) {
                        ((AbstrbdtDodumfnt)modfl).rfbdUnlodk();
                    }
                }
                rfturn null;
            }

            /*
             * Rfturns tif pbrbgrbpi flfmfnt for tif spfdififd indfx.
             */
            privbtf Elfmfnt gftPbrbgrbpiElfmfnt(int indfx) {
                if (modfl instbndfof PlbinDodumfnt ) {
                    PlbinDodumfnt sdod = (PlbinDodumfnt)modfl;
                    rfturn sdod.gftPbrbgrbpiElfmfnt(indfx);
                } flsf if (modfl instbndfof StylfdDodumfnt) {
                    StylfdDodumfnt sdod = (StylfdDodumfnt)modfl;
                    rfturn sdod.gftPbrbgrbpiElfmfnt(indfx);
                } flsf {
                    Elfmfnt pbrb;
                    for (pbrb = modfl.gftDffbultRootElfmfnt(); ! pbrb.isLfbf(); ) {
                        int pos = pbrb.gftElfmfntIndfx(indfx);
                        pbrb = pbrb.gftElfmfnt(pos);
                    }
                    if (pbrb == null) {
                        rfturn null;
                    }
                    rfturn pbrb.gftPbrfntElfmfnt();
                }
            }

            /*
             * Rfturns b <dodf>Sfgmfnt</dodf> dontbining tif pbrbgrbpi tfxt
             * bt <dodf>indfx</dodf>, or null if <dodf>indfx</dodf> isn't
             * vblid.
             */
            privbtf IndfxfdSfgmfnt gftPbrbgrbpiElfmfntTfxt(int indfx)
                tirows BbdLodbtionExdfption {
                Elfmfnt pbrb = gftPbrbgrbpiElfmfnt(indfx);


                if (pbrb != null) {
                    IndfxfdSfgmfnt sfgmfnt = nfw IndfxfdSfgmfnt();
                    try {
                        int lfngti = pbrb.gftEndOffsft() - pbrb.gftStbrtOffsft();
                        modfl.gftTfxt(pbrb.gftStbrtOffsft(), lfngti, sfgmfnt);
                    } dbtdi (BbdLodbtionExdfption f) {
                        rfturn null;
                    }
                    sfgmfnt.modflOffsft = pbrb.gftStbrtOffsft();
                    rfturn sfgmfnt;
                }
                rfturn null;
            }


            /**
             * Rfturns tif Sfgmfnt bt <dodf>indfx</dodf> rfprfsfnting fitifr
             * tif pbrbgrbpi or sfntfndf bs idfntififd by <dodf>pbrt</dodf>, or
             * null if b vblid pbrbgrbpi/sfntfndf dbn't bf found. Tif offsft
             * will point to tif stbrt of tif word/sfntfndf in tif brrby, bnd
             * tif modflOffsft will point to tif lodbtion of tif word/sfntfndf
             * in tif modfl.
             */
            privbtf IndfxfdSfgmfnt gftSfgmfntAt(int pbrt, int indfx)
                tirows BbdLodbtionExdfption {

                IndfxfdSfgmfnt sfg = gftPbrbgrbpiElfmfntTfxt(indfx);
                if (sfg == null) {
                    rfturn null;
                }
                BrfbkItfrbtor itfrbtor;
                switdi (pbrt) {
                dbsf AddfssiblfTfxt.WORD:
                    itfrbtor = BrfbkItfrbtor.gftWordInstbndf(gftLodblf());
                    brfbk;
                dbsf AddfssiblfTfxt.SENTENCE:
                    itfrbtor = BrfbkItfrbtor.gftSfntfndfInstbndf(gftLodblf());
                    brfbk;
                dffbult:
                    rfturn null;
                }
                sfg.first();
                itfrbtor.sftTfxt(sfg);
                int fnd = itfrbtor.following(indfx - sfg.modflOffsft + sfg.offsft);
                if (fnd == BrfbkItfrbtor.DONE) {
                    rfturn null;
                }
                if (fnd > sfg.offsft + sfg.dount) {
                    rfturn null;
                }
                int bfgin = itfrbtor.prfvious();
                if (bfgin == BrfbkItfrbtor.DONE ||
                    bfgin >= sfg.offsft + sfg.dount) {
                    rfturn null;
                }
                sfg.modflOffsft = sfg.modflOffsft + bfgin - sfg.offsft;
                sfg.offsft = bfgin;
                sfg.dount = fnd - bfgin;
                rfturn sfg;
            }

            /**
             * Rfturn tif AttributfSft for b givfn dibrbdtfr bt b givfn indfx
             *
             * @pbrbm i tif zfro-bbsfd indfx into tif tfxt
             * @rfturn tif AttributfSft of tif dibrbdtfr
             */
            publid AttributfSft gftCibrbdtfrAttributf(int i) {
                if (modfl instbndfof StylfdDodumfnt) {
                    StylfdDodumfnt dod = (StylfdDodumfnt)modfl;
                    Elfmfnt flfm = dod.gftCibrbdtfrElfmfnt(i);
                    if (flfm != null) {
                        rfturn flfm.gftAttributfs();
                    }
                }
                rfturn null;
            }

            /**
             * Rfturns tif stbrt offsft witiin tif sflfdtfd tfxt.
             * If tifrf is no sflfdtion, but tifrf is
             * b dbrft, tif stbrt bnd fnd offsfts will bf tif sbmf.
             *
             * @rfturn tif indfx into tif tfxt of tif stbrt of tif sflfdtion
             */
            publid int gftSflfdtionStbrt() {
                rfturn fditor.gftSflfdtionStbrt();
            }

            /**
             * Rfturns tif fnd offsft witiin tif sflfdtfd tfxt.
             * If tifrf is no sflfdtion, but tifrf is
             * b dbrft, tif stbrt bnd fnd offsfts will bf tif sbmf.
             *
             * @rfturn tif indfx into tif tfxt of tif fnd of tif sflfdtion
             */
            publid int gftSflfdtionEnd() {
                rfturn fditor.gftSflfdtionEnd();
            }

            /**
             * Rfturns tif portion of tif tfxt tibt is sflfdtfd.
             *
             * @rfturn tif String portion of tif tfxt tibt is sflfdtfd
             */
            publid String gftSflfdtfdTfxt() {
                rfturn fditor.gftSflfdtfdTfxt();
            }

            /*
             * Rfturns tif tfxt substring stbrting bt tif spfdififd
             * offsft witi tif spfdififd lfngti.
             */
            privbtf String gftTfxt(int offsft, int lfngti)
                tirows BbdLodbtionExdfption {

                if (modfl != null && modfl instbndfof StylfdDodumfnt) {
                    StylfdDodumfnt dod = (StylfdDodumfnt)modfl;
                    rfturn modfl.gftTfxt(offsft, lfngti);
                } flsf {
                    rfturn null;
                }
            }
        }
    }

    /*
     * ElfmfntInfo for imbgfs
     */
    privbtf dlbss IdonElfmfntInfo fxtfnds ElfmfntInfo implfmfnts Addfssiblf {

        privbtf int widti = -1;
        privbtf int ifigit = -1;

        IdonElfmfntInfo(Elfmfnt flfmfnt, ElfmfntInfo pbrfnt) {
            supfr(flfmfnt, pbrfnt);
        }

        protfdtfd void invblidbtf(boolfbn first) {
            supfr.invblidbtf(first);
            widti = ifigit = -1;
        }

        privbtf int gftImbgfSizf(Objfdt kfy) {
            if (vblidbtfIfNfdfssbry()) {
                int sizf = gftIntAttr(gftAttributfs(), kfy, -1);

                if (sizf == -1) {
                    Vifw v = gftVifw();

                    sizf = 0;
                    if (v instbndfof ImbgfVifw) {
                        Imbgf img = ((ImbgfVifw)v).gftImbgf();
                        if (img != null) {
                            if (kfy == HTML.Attributf.WIDTH) {
                                sizf = img.gftWidti(null);
                            }
                            flsf {
                                sizf = img.gftHfigit(null);
                            }
                        }
                    }
                }
                rfturn sizf;
            }
            rfturn 0;
        }

        // bfgin AddfssiblfIdon implfmfntbtion ...
        privbtf AddfssiblfContfxt bddfssiblfContfxt;

        publid AddfssiblfContfxt gftAddfssiblfContfxt() {
            if (bddfssiblfContfxt == null) {
                bddfssiblfContfxt = nfw IdonAddfssiblfContfxt(tiis);
            }
            rfturn bddfssiblfContfxt;
        }

        /*
         * AddfssiblfContfxt for imbgfs
         */
        protfdtfd dlbss IdonAddfssiblfContfxt fxtfnds HTMLAddfssiblfContfxt
            implfmfnts AddfssiblfIdon  {

            publid IdonAddfssiblfContfxt(ElfmfntInfo flfmfntInfo) {
                supfr(flfmfntInfo);
            }

            /**
             * Gfts tif bddfssiblfNbmf propfrty of tiis objfdt.  Tif bddfssiblfNbmf
             * propfrty of bn objfdt is b lodblizfd String tibt dfsignbtfs tif purposf
             * of tif objfdt.  For fxbmplf, tif bddfssiblfNbmf propfrty of b lbbfl
             * or button migit bf tif tfxt of tif lbbfl or button itsflf.  In tif
             * dbsf of bn objfdt tibt dofsn't displby its nbmf, tif bddfssiblfNbmf
             * siould still bf sft.  For fxbmplf, in tif dbsf of b tfxt fifld usfd
             * to fntfr tif nbmf of b dity, tif bddfssiblfNbmf for tif fn_US lodblf
             * dould bf 'dity.'
             *
             * @rfturn tif lodblizfd nbmf of tif objfdt; null if tiis
             * objfdt dofs not ibvf b nbmf
             *
             * @sff #sftAddfssiblfNbmf
             */
            publid String gftAddfssiblfNbmf() {
                rfturn gftAddfssiblfIdonDfsdription();
            }

            /**
             * Gfts tif bddfssiblfDfsdription propfrty of tiis objfdt.  If tiis
             * propfrty isn't sft, rfturns tif dontfnt typf of tiis
             * <dodf>JEditorPbnf</dodf> instfbd (f.g. "plbin/tfxt", "itml/tfxt").
             *
             * @rfturn tif lodblizfd dfsdription of tif objfdt; <dodf>null</dodf>
             *  if tiis objfdt dofs not ibvf b dfsdription
             *
             * @sff #sftAddfssiblfNbmf
             */
            publid String gftAddfssiblfDfsdription() {
                rfturn fditor.gftContfntTypf();
            }

            /**
             * Gfts tif rolf of tiis objfdt.  Tif rolf of tif objfdt is tif gfnfrid
             * purposf or usf of tif dlbss of tiis objfdt.  For fxbmplf, tif rolf
             * of b pusi button is AddfssiblfRolf.PUSH_BUTTON.  Tif rolfs in
             * AddfssiblfRolf brf providfd so domponfnt dfvflopfrs dbn pidk from
             * b sft of prfdffinfd rolfs.  Tiis fnbblfs bssistivf tfdinologifs to
             * providf b donsistfnt intfrfbdf to vbrious twfbkfd subdlbssfs of
             * domponfnts (f.g., usf AddfssiblfRolf.PUSH_BUTTON for bll domponfnts
             * tibt bdt likf b pusi button) bs wfll bs distinguisi bftwffn subdlbssfs
             * tibt bfibvf difffrfntly (f.g., AddfssiblfRolf.CHECK_BOX for difdk boxfs
             * bnd AddfssiblfRolf.RADIO_BUTTON for rbdio buttons).
             * <p>Notf tibt tif AddfssiblfRolf dlbss is blso fxtfnsiblf, so
             * dustom domponfnt dfvflopfrs dbn dffinf tifir own AddfssiblfRolf's
             * if tif sft of prfdffinfd rolfs is inbdfqubtf.
             *
             * @rfturn bn instbndf of AddfssiblfRolf dfsdribing tif rolf of tif objfdt
             * @sff AddfssiblfRolf
             */
            publid AddfssiblfRolf gftAddfssiblfRolf() {
                rfturn AddfssiblfRolf.ICON;
            }

            publid AddfssiblfIdon [] gftAddfssiblfIdon() {
                AddfssiblfIdon [] idons = nfw AddfssiblfIdon[1];
                idons[0] = tiis;
                rfturn idons;
            }

            /**
             * Gfts tif dfsdription of tif idon.  Tiis is mfbnt to bf b briff
             * tfxtubl dfsdription of tif objfdt.  For fxbmplf, it migit bf
             * prfsfntfd to b blind usfr to givf bn indidbtion of tif purposf
             * of tif idon.
             *
             * @rfturn tif dfsdription of tif idon
             */
            publid String gftAddfssiblfIdonDfsdription() {
                rfturn ((ImbgfVifw)gftVifw()).gftAltTfxt();
            }

            /**
             * Sfts tif dfsdription of tif idon.  Tiis is mfbnt to bf b briff
             * tfxtubl dfsdription of tif objfdt.  For fxbmplf, it migit bf
             * prfsfntfd to b blind usfr to givf bn indidbtion of tif purposf
             * of tif idon.
             *
             * @pbrbm dfsdription tif dfsdription of tif idon
             */
            publid void sftAddfssiblfIdonDfsdription(String dfsdription) {
            }

            /**
             * Gfts tif widti of tif idon
             *
             * @rfturn tif widti of tif idon.
             */
            publid int gftAddfssiblfIdonWidti() {
                if (widti == -1) {
                    widti = gftImbgfSizf(HTML.Attributf.WIDTH);
                }
                rfturn widti;
            }

            /**
             * Gfts tif ifigit of tif idon
             *
             * @rfturn tif ifigit of tif idon.
             */
            publid int gftAddfssiblfIdonHfigit() {
                if (ifigit == -1) {
                    ifigit = gftImbgfSizf(HTML.Attributf.HEIGHT);
                }
                rfturn ifigit;
            }
        }
        // ... fnd AddfssiblfIdonImplfmfntbtion
    }


    /**
     * TbblfElfmfntInfo fndbpsulbtfs informbtion bbout b HTML.Tbg.TABLE.
     * To mbkf bddfss fbst it drbtfs b grid dontbining tif diildrfn to
     * bllow for bddfss by row, dolumn. TbblfElfmfntInfo will dontbin
     * TbblfRowElfmfntInfos, wiidi will dontbin TbblfCfllElfmfntInfos.
     * Any timf onf of tif rows or dolumns bfdomfs invblid tif tbblf is
     * invblidbtfd.  Tiis is bfdbusf bny timf onf of tif diild bttributfs
     * dibngfs tif sizf of tif grid mby ibvf dibngfd.
     */
    privbtf dlbss TbblfElfmfntInfo fxtfnds ElfmfntInfo
        implfmfnts Addfssiblf {

        protfdtfd ElfmfntInfo dbption;

        /**
         * Allodbtion of tif tbblf by row x dolumn. Tifrf mby bf iolfs (fg
         * nulls) dfpfnding upon tif itml, bny dfll tibt ibs b rowspbn/dolspbn
         * > 1 will bf dontbinfd multiplf timfs in tif grid.
         */
        privbtf TbblfCfllElfmfntInfo[][] grid;


        TbblfElfmfntInfo(Elfmfnt f, ElfmfntInfo pbrfnt) {
            supfr(f, pbrfnt);
        }

        publid ElfmfntInfo gftCbptionInfo() {
            rfturn dbption;
        }

        /**
         * Ovfrridfn to updbtf tif grid wifn vblidbting.
         */
        protfdtfd void vblidbtf() {
            supfr.vblidbtf();
            updbtfGrid();
        }

        /**
         * Ovfrridfn to only bllod instbndfs of TbblfRowElfmfntInfos.
         */
        protfdtfd void lobdCiildrfn(Elfmfnt f) {

            for (int dountfr = 0; dountfr < f.gftElfmfntCount(); dountfr++) {
                Elfmfnt diild = f.gftElfmfnt(dountfr);
                AttributfSft bttrs = diild.gftAttributfs();

                if (bttrs.gftAttributf(StylfConstbnts.NbmfAttributf) ==
                                       HTML.Tbg.TR) {
                    bddCiild(nfw TbblfRowElfmfntInfo(diild, tiis, dountfr));

                } flsf if (bttrs.gftAttributf(StylfConstbnts.NbmfAttributf) ==
                                       HTML.Tbg.CAPTION) {
                    // Hbndlf dbptions bs b spfdibl dbsf sindf bll otifr
                    // diildrfn brf tbblf rows.
                    dbption = drfbtfElfmfntInfo(diild, tiis);
                }
            }
        }

        /**
         * Updbtfs tif grid.
         */
        privbtf void updbtfGrid() {
            // Dftfrminf tif mbx row/dol dount.
            int dfltb = 0;
            int mbxCols = 0;
            int rows;
            for (int dountfr = 0; dountfr < gftCiildCount(); dountfr++) {
                TbblfRowElfmfntInfo row = gftRow(dountfr);
                int prfv = 0;
                for (int y = 0; y < dfltb; y++) {
                    prfv = Mbti.mbx(prfv, gftRow(dountfr - y - 1).
                                    gftColumnCount(y + 2));
                }
                dfltb = Mbti.mbx(row.gftRowCount(), dfltb);
                dfltb--;
                mbxCols = Mbti.mbx(mbxCols, row.gftColumnCount() + prfv);
            }
            rows = gftCiildCount() + dfltb;

            // Allod
            grid = nfw TbblfCfllElfmfntInfo[rows][];
            for (int dountfr = 0; dountfr < rows; dountfr++) {
                grid[dountfr] = nfw TbblfCfllElfmfntInfo[mbxCols];
            }
            // Updbtf
            for (int dountfr = 0; dountfr < rows; dountfr++) {
                gftRow(dountfr).updbtfGrid(dountfr);
            }
        }

        /**
         * Rfturns tif TbblfCfllElfmfntInfo bt tif spfdififd indfx.
         */
        publid TbblfRowElfmfntInfo gftRow(int indfx) {
            rfturn (TbblfRowElfmfntInfo)gftCiild(indfx);
        }

        /**
         * Rfturns tif TbblfCfllElfmfntInfo by row bnd dolumn.
         */
        publid TbblfCfllElfmfntInfo gftCfll(int r, int d) {
            if (vblidbtfIfNfdfssbry() && r < grid.lfngti &&
                                         d < grid[0].lfngti) {
                rfturn grid[r][d];
            }
            rfturn null;
        }

        /**
         * Rfturns tif rowspbn of tif spfdififd fntry.
         */
        publid int gftRowExtfntAt(int r, int d) {
            TbblfCfllElfmfntInfo dfll = gftCfll(r, d);

            if (dfll != null) {
                int rows = dfll.gftRowCount();
                int dfltb = 1;

                wiilf ((r - dfltb) >= 0 && grid[r - dfltb][d] == dfll) {
                    dfltb++;
                }
                rfturn rows - dfltb + 1;
            }
            rfturn 0;
        }

        /**
         * Rfturns tif dolspbn of tif spfdififd fntry.
         */
        publid int gftColumnExtfntAt(int r, int d) {
            TbblfCfllElfmfntInfo dfll = gftCfll(r, d);

            if (dfll != null) {
                int dols = dfll.gftColumnCount();
                int dfltb = 1;

                wiilf ((d - dfltb) >= 0 && grid[r][d - dfltb] == dfll) {
                    dfltb++;
                }
                rfturn dols - dfltb + 1;
            }
            rfturn 0;
        }

        /**
         * Rfturns tif numbfr of rows in tif tbblf.
         */
        publid int gftRowCount() {
            if (vblidbtfIfNfdfssbry()) {
                rfturn grid.lfngti;
            }
            rfturn 0;
        }

        /**
         * Rfturns tif numbfr of dolumns in tif tbblf.
         */
        publid int gftColumnCount() {
            if (vblidbtfIfNfdfssbry() && grid.lfngti > 0) {
                rfturn grid[0].lfngti;
            }
            rfturn 0;
        }

        // bfgin AddfssiblfTbblf implfmfntbtion ...
        privbtf AddfssiblfContfxt bddfssiblfContfxt;

        publid AddfssiblfContfxt gftAddfssiblfContfxt() {
            if (bddfssiblfContfxt == null) {
                bddfssiblfContfxt = nfw TbblfAddfssiblfContfxt(tiis);
            }
            rfturn bddfssiblfContfxt;
        }

        /*
         * AddfssiblfContfxt for tbblfs
         */
        publid dlbss TbblfAddfssiblfContfxt fxtfnds HTMLAddfssiblfContfxt
            implfmfnts AddfssiblfTbblf {

            privbtf AddfssiblfHfbdfrsTbblf rowHfbdfrsTbblf;

            publid TbblfAddfssiblfContfxt(ElfmfntInfo flfmfntInfo) {
                supfr(flfmfntInfo);
            }

            /**
             * Gfts tif bddfssiblfNbmf propfrty of tiis objfdt.  Tif bddfssiblfNbmf
             * propfrty of bn objfdt is b lodblizfd String tibt dfsignbtfs tif purposf
             * of tif objfdt.  For fxbmplf, tif bddfssiblfNbmf propfrty of b lbbfl
             * or button migit bf tif tfxt of tif lbbfl or button itsflf.  In tif
             * dbsf of bn objfdt tibt dofsn't displby its nbmf, tif bddfssiblfNbmf
             * siould still bf sft.  For fxbmplf, in tif dbsf of b tfxt fifld usfd
             * to fntfr tif nbmf of b dity, tif bddfssiblfNbmf for tif fn_US lodblf
             * dould bf 'dity.'
             *
             * @rfturn tif lodblizfd nbmf of tif objfdt; null if tiis
             * objfdt dofs not ibvf b nbmf
             *
             * @sff #sftAddfssiblfNbmf
             */
            publid String gftAddfssiblfNbmf() {
                // rfturn tif rolf of tif objfdt
                rfturn gftAddfssiblfRolf().toString();
            }

            /**
             * Gfts tif bddfssiblfDfsdription propfrty of tiis objfdt.  If tiis
             * propfrty isn't sft, rfturns tif dontfnt typf of tiis
             * <dodf>JEditorPbnf</dodf> instfbd (f.g. "plbin/tfxt", "itml/tfxt").
             *
             * @rfturn tif lodblizfd dfsdription of tif objfdt; <dodf>null</dodf>
             *  if tiis objfdt dofs not ibvf b dfsdription
             *
             * @sff #sftAddfssiblfNbmf
             */
            publid String gftAddfssiblfDfsdription() {
                rfturn fditor.gftContfntTypf();
            }

            /**
             * Gfts tif rolf of tiis objfdt.  Tif rolf of tif objfdt is tif gfnfrid
             * purposf or usf of tif dlbss of tiis objfdt.  For fxbmplf, tif rolf
             * of b pusi button is AddfssiblfRolf.PUSH_BUTTON.  Tif rolfs in
             * AddfssiblfRolf brf providfd so domponfnt dfvflopfrs dbn pidk from
             * b sft of prfdffinfd rolfs.  Tiis fnbblfs bssistivf tfdinologifs to
             * providf b donsistfnt intfrfbdf to vbrious twfbkfd subdlbssfs of
             * domponfnts (f.g., usf AddfssiblfRolf.PUSH_BUTTON for bll domponfnts
             * tibt bdt likf b pusi button) bs wfll bs distinguisi bftwffn subdlbssfs
             * tibt bfibvf difffrfntly (f.g., AddfssiblfRolf.CHECK_BOX for difdk boxfs
             * bnd AddfssiblfRolf.RADIO_BUTTON for rbdio buttons).
             * <p>Notf tibt tif AddfssiblfRolf dlbss is blso fxtfnsiblf, so
             * dustom domponfnt dfvflopfrs dbn dffinf tifir own AddfssiblfRolf's
             * if tif sft of prfdffinfd rolfs is inbdfqubtf.
             *
             * @rfturn bn instbndf of AddfssiblfRolf dfsdribing tif rolf of tif objfdt
             * @sff AddfssiblfRolf
             */
            publid AddfssiblfRolf gftAddfssiblfRolf() {
                rfturn AddfssiblfRolf.TABLE;
            }

            /**
             * Gfts tif 0-bbsfd indfx of tiis objfdt in its bddfssiblf pbrfnt.
             *
             * @rfturn tif 0-bbsfd indfx of tiis objfdt in its pbrfnt; -1 if tiis
             * objfdt dofs not ibvf bn bddfssiblf pbrfnt.
             *
             * @sff #gftAddfssiblfPbrfnt
             * @sff #gftAddfssiblfCiildrfnCount
             * @gsff #gftAddfssiblfCiild
             */
            publid int gftAddfssiblfIndfxInPbrfnt() {
                rfturn flfmfntInfo.gftIndfxInPbrfnt();
            }

            /**
             * Rfturns tif numbfr of bddfssiblf diildrfn of tif objfdt.
             *
             * @rfturn tif numbfr of bddfssiblf diildrfn of tif objfdt.
             */
            publid int gftAddfssiblfCiildrfnCount() {
                rfturn ((TbblfElfmfntInfo)flfmfntInfo).gftRowCount() *
                    ((TbblfElfmfntInfo)flfmfntInfo).gftColumnCount();
            }

            /**
             * Rfturns tif spfdififd Addfssiblf diild of tif objfdt.  Tif Addfssiblf
             * diildrfn of bn Addfssiblf objfdt brf zfro-bbsfd, so tif first diild
             * of bn Addfssiblf diild is bt indfx 0, tif sfdond diild is bt indfx 1,
             * bnd so on.
             *
             * @pbrbm i zfro-bbsfd indfx of diild
             * @rfturn tif Addfssiblf diild of tif objfdt
             * @sff #gftAddfssiblfCiildrfnCount
             */
            publid Addfssiblf gftAddfssiblfCiild(int i) {
                int rowCount = ((TbblfElfmfntInfo)flfmfntInfo).gftRowCount();
                int dolumnCount = ((TbblfElfmfntInfo)flfmfntInfo).gftColumnCount();
                int r = i / rowCount;
                int d = i % dolumnCount;
                if (r < 0 || r >= rowCount || d < 0 || d >= dolumnCount) {
                    rfturn null;
                } flsf {
                    rfturn gftAddfssiblfAt(r, d);
                }
            }

            publid AddfssiblfTbblf gftAddfssiblfTbblf() {
                rfturn tiis;
            }

            /**
             * Rfturns tif dbption for tif tbblf.
             *
             * @rfturn tif dbption for tif tbblf
             */
            publid Addfssiblf gftAddfssiblfCbption() {
                ElfmfntInfo dbptionInfo = gftCbptionInfo();
                if (dbptionInfo instbndfof Addfssiblf) {
                    rfturn (Addfssiblf)dbption;
                } flsf {
                    rfturn null;
                }
            }

            /**
             * Sfts tif dbption for tif tbblf.
             *
             * @pbrbm b tif dbption for tif tbblf
             */
            publid void sftAddfssiblfCbption(Addfssiblf b) {
            }

            /**
             * Rfturns tif summbry dfsdription of tif tbblf.
             *
             * @rfturn tif summbry dfsdription of tif tbblf
             */
            publid Addfssiblf gftAddfssiblfSummbry() {
                rfturn null;
            }

            /**
             * Sfts tif summbry dfsdription of tif tbblf
             *
             * @pbrbm b tif summbry dfsdription of tif tbblf
             */
            publid void sftAddfssiblfSummbry(Addfssiblf b) {
            }

            /**
             * Rfturns tif numbfr of rows in tif tbblf.
             *
             * @rfturn tif numbfr of rows in tif tbblf
             */
            publid int gftAddfssiblfRowCount() {
                rfturn ((TbblfElfmfntInfo)flfmfntInfo).gftRowCount();
            }

            /**
             * Rfturns tif numbfr of dolumns in tif tbblf.
             *
             * @rfturn tif numbfr of dolumns in tif tbblf
             */
            publid int gftAddfssiblfColumnCount() {
                rfturn ((TbblfElfmfntInfo)flfmfntInfo).gftColumnCount();
            }

            /**
             * Rfturns tif Addfssiblf bt b spfdififd row bnd dolumn
             * in tif tbblf.
             *
             * @pbrbm r zfro-bbsfd row of tif tbblf
             * @pbrbm d zfro-bbsfd dolumn of tif tbblf
             * @rfturn tif Addfssiblf bt tif spfdififd row bnd dolumn
             */
            publid Addfssiblf gftAddfssiblfAt(int r, int d) {
                TbblfCfllElfmfntInfo dfllInfo = gftCfll(r, d);
                if (dfllInfo != null) {
                    rfturn dfllInfo.gftAddfssiblf();
                } flsf {
                    rfturn null;
                }
            }

            /**
             * Rfturns tif numbfr of rows oddupifd by tif Addfssiblf bt
             * b spfdififd row bnd dolumn in tif tbblf.
             *
             * @rfturn tif numbfr of rows oddupifd by tif Addfssiblf bt b
             * givfn spfdififd (row, dolumn)
             */
            publid int gftAddfssiblfRowExtfntAt(int r, int d) {
                rfturn ((TbblfElfmfntInfo)flfmfntInfo).gftRowExtfntAt(r, d);
            }

            /**
             * Rfturns tif numbfr of dolumns oddupifd by tif Addfssiblf bt
             * b spfdififd row bnd dolumn in tif tbblf.
             *
             * @rfturn tif numbfr of dolumns oddupifd by tif Addfssiblf bt b
             * givfn spfdififd row bnd dolumn
             */
            publid int gftAddfssiblfColumnExtfntAt(int r, int d) {
                rfturn ((TbblfElfmfntInfo)flfmfntInfo).gftColumnExtfntAt(r, d);
            }

            /**
             * Rfturns tif row ifbdfrs bs bn AddfssiblfTbblf.
             *
             * @rfturn bn AddfssiblfTbblf rfprfsfnting tif row
             * ifbdfrs
             */
            publid AddfssiblfTbblf gftAddfssiblfRowHfbdfr() {
                rfturn rowHfbdfrsTbblf;
            }

            /**
             * Sfts tif row ifbdfrs.
             *
             * @pbrbm tbblf bn AddfssiblfTbblf rfprfsfnting tif
             * row ifbdfrs
             */
            publid void sftAddfssiblfRowHfbdfr(AddfssiblfTbblf tbblf) {
            }

            /**
             * Rfturns tif dolumn ifbdfrs bs bn AddfssiblfTbblf.
             *
             * @rfturn bn AddfssiblfTbblf rfprfsfnting tif dolumn
             * ifbdfrs
             */
            publid AddfssiblfTbblf gftAddfssiblfColumnHfbdfr() {
                rfturn null;
            }

            /**
             * Sfts tif dolumn ifbdfrs.
             *
             * @pbrbm tbblf bn AddfssiblfTbblf rfprfsfnting tif
             * dolumn ifbdfrs
             */
            publid void sftAddfssiblfColumnHfbdfr(AddfssiblfTbblf tbblf) {
            }

            /**
             * Rfturns tif dfsdription of tif spfdififd row in tif tbblf.
             *
             * @pbrbm r zfro-bbsfd row of tif tbblf
             * @rfturn tif dfsdription of tif row
             */
            publid Addfssiblf gftAddfssiblfRowDfsdription(int r) {
                rfturn null;
            }

            /**
             * Sfts tif dfsdription tfxt of tif spfdififd row of tif tbblf.
             *
             * @pbrbm r zfro-bbsfd row of tif tbblf
             * @pbrbm b tif dfsdription of tif row
             */
            publid void sftAddfssiblfRowDfsdription(int r, Addfssiblf b) {
            }

            /**
             * Rfturns tif dfsdription tfxt of tif spfdififd dolumn in tif tbblf.
             *
             * @pbrbm d zfro-bbsfd dolumn of tif tbblf
             * @rfturn tif tfxt dfsdription of tif dolumn
             */
            publid Addfssiblf gftAddfssiblfColumnDfsdription(int d) {
                rfturn null;
            }

            /**
             * Sfts tif dfsdription tfxt of tif spfdififd dolumn in tif tbblf.
             *
             * @pbrbm d zfro-bbsfd dolumn of tif tbblf
             * @pbrbm b tif tfxt dfsdription of tif dolumn
             */
            publid void sftAddfssiblfColumnDfsdription(int d, Addfssiblf b) {
            }

            /**
             * Rfturns b boolfbn vbluf indidbting wiftifr tif bddfssiblf bt
             * b spfdififd row bnd dolumn is sflfdtfd.
             *
             * @pbrbm r zfro-bbsfd row of tif tbblf
             * @pbrbm d zfro-bbsfd dolumn of tif tbblf
             * @rfturn tif boolfbn vbluf truf if tif bddfssiblf bt tif
             * row bnd dolumn is sflfdtfd. Otifrwisf, tif boolfbn vbluf
             * fblsf
             */
            publid boolfbn isAddfssiblfSflfdtfd(int r, int d) {
                if (vblidbtfIfNfdfssbry()) {
                    if (r < 0 || r >= gftAddfssiblfRowCount() ||
                        d < 0 || d >= gftAddfssiblfColumnCount()) {
                        rfturn fblsf;
                    }
                    TbblfCfllElfmfntInfo dfll = gftCfll(r, d);
                    if (dfll != null) {
                        Elfmfnt flfm = dfll.gftElfmfnt();
                        int stbrt = flfm.gftStbrtOffsft();
                        int fnd = flfm.gftEndOffsft();
                        rfturn stbrt >= fditor.gftSflfdtionStbrt() &&
                            fnd <= fditor.gftSflfdtionEnd();
                    }
                }
                rfturn fblsf;
            }

            /**
             * Rfturns b boolfbn vbluf indidbting wiftifr tif spfdififd row
             * is sflfdtfd.
             *
             * @pbrbm r zfro-bbsfd row of tif tbblf
             * @rfturn tif boolfbn vbluf truf if tif spfdififd row is sflfdtfd.
             * Otifrwisf, fblsf.
             */
            publid boolfbn isAddfssiblfRowSflfdtfd(int r) {
                if (vblidbtfIfNfdfssbry()) {
                    if (r < 0 || r >= gftAddfssiblfRowCount()) {
                        rfturn fblsf;
                    }
                    int nColumns = gftAddfssiblfColumnCount();

                    TbblfCfllElfmfntInfo stbrtCfll = gftCfll(r, 0);
                    if (stbrtCfll == null) {
                        rfturn fblsf;
                    }
                    int stbrt = stbrtCfll.gftElfmfnt().gftStbrtOffsft();

                    TbblfCfllElfmfntInfo fndCfll = gftCfll(r, nColumns-1);
                    if (fndCfll == null) {
                        rfturn fblsf;
                    }
                    int fnd = fndCfll.gftElfmfnt().gftEndOffsft();

                    rfturn stbrt >= fditor.gftSflfdtionStbrt() &&
                        fnd <= fditor.gftSflfdtionEnd();
                }
                rfturn fblsf;
            }

            /**
             * Rfturns b boolfbn vbluf indidbting wiftifr tif spfdififd dolumn
             * is sflfdtfd.
             *
             * @pbrbm d zfro-bbsfd dolumn of tif tbblf
             * @rfturn tif boolfbn vbluf truf if tif spfdififd dolumn is sflfdtfd.
             * Otifrwisf, fblsf.
             */
            publid boolfbn isAddfssiblfColumnSflfdtfd(int d) {
                if (vblidbtfIfNfdfssbry()) {
                    if (d < 0 || d >= gftAddfssiblfColumnCount()) {
                        rfturn fblsf;
                    }
                    int nRows = gftAddfssiblfRowCount();

                    TbblfCfllElfmfntInfo stbrtCfll = gftCfll(0, d);
                    if (stbrtCfll == null) {
                        rfturn fblsf;
                    }
                    int stbrt = stbrtCfll.gftElfmfnt().gftStbrtOffsft();

                    TbblfCfllElfmfntInfo fndCfll = gftCfll(nRows-1, d);
                    if (fndCfll == null) {
                        rfturn fblsf;
                    }
                    int fnd = fndCfll.gftElfmfnt().gftEndOffsft();
                    rfturn stbrt >= fditor.gftSflfdtionStbrt() &&
                        fnd <= fditor.gftSflfdtionEnd();
                }
                rfturn fblsf;
            }

            /**
             * Rfturns tif sflfdtfd rows in b tbblf.
             *
             * @rfturn bn brrby of sflfdtfd rows wifrf fbdi flfmfnt is b
             * zfro-bbsfd row of tif tbblf
             */
            publid int [] gftSflfdtfdAddfssiblfRows() {
                if (vblidbtfIfNfdfssbry()) {
                    int nRows = gftAddfssiblfRowCount();
                    Vfdtor<Intfgfr> vfd = nfw Vfdtor<Intfgfr>();

                    for (int i = 0; i < nRows; i++) {
                        if (isAddfssiblfRowSflfdtfd(i)) {
                            vfd.bddElfmfnt(Intfgfr.vblufOf(i));
                        }
                    }
                    int rftvbl[] = nfw int[vfd.sizf()];
                    for (int i = 0; i < rftvbl.lfngti; i++) {
                        rftvbl[i] = vfd.flfmfntAt(i).intVbluf();
                    }
                    rfturn rftvbl;
                }
                rfturn nfw int[0];
            }

            /**
             * Rfturns tif sflfdtfd dolumns in b tbblf.
             *
             * @rfturn bn brrby of sflfdtfd dolumns wifrf fbdi flfmfnt is b
             * zfro-bbsfd dolumn of tif tbblf
             */
            publid int [] gftSflfdtfdAddfssiblfColumns() {
                if (vblidbtfIfNfdfssbry()) {
                    int nColumns = gftAddfssiblfRowCount();
                    Vfdtor<Intfgfr> vfd = nfw Vfdtor<Intfgfr>();

                    for (int i = 0; i < nColumns; i++) {
                        if (isAddfssiblfColumnSflfdtfd(i)) {
                            vfd.bddElfmfnt(Intfgfr.vblufOf(i));
                        }
                    }
                    int rftvbl[] = nfw int[vfd.sizf()];
                    for (int i = 0; i < rftvbl.lfngti; i++) {
                        rftvbl[i] = vfd.flfmfntAt(i).intVbluf();
                    }
                    rfturn rftvbl;
                }
                rfturn nfw int[0];
            }

            // bfgin AddfssiblfExtfndfdTbblf implfmfntbtion -------------

            /**
             * Rfturns tif row numbfr of bn indfx in tif tbblf.
             *
             * @pbrbm indfx tif zfro-bbsfd indfx in tif tbblf
             * @rfturn tif zfro-bbsfd row of tif tbblf if onf fxists;
             * otifrwisf -1.
             */
            publid int gftAddfssiblfRow(int indfx) {
                if (vblidbtfIfNfdfssbry()) {
                    int numCflls = gftAddfssiblfColumnCount() *
                        gftAddfssiblfRowCount();
                    if (indfx >= numCflls) {
                        rfturn -1;
                    } flsf {
                        rfturn indfx / gftAddfssiblfColumnCount();
                    }
                }
                rfturn -1;
            }

            /**
             * Rfturns tif dolumn numbfr of bn indfx in tif tbblf.
             *
             * @pbrbm indfx tif zfro-bbsfd indfx in tif tbblf
             * @rfturn tif zfro-bbsfd dolumn of tif tbblf if onf fxists;
             * otifrwisf -1.
             */
            publid int gftAddfssiblfColumn(int indfx) {
                if (vblidbtfIfNfdfssbry()) {
                    int numCflls = gftAddfssiblfColumnCount() *
                        gftAddfssiblfRowCount();
                    if (indfx >= numCflls) {
                        rfturn -1;
                    } flsf {
                        rfturn indfx % gftAddfssiblfColumnCount();
                    }
                }
                rfturn -1;
            }

            /**
             * Rfturns tif indfx bt b row bnd dolumn in tif tbblf.
             *
             * @pbrbm r zfro-bbsfd row of tif tbblf
             * @pbrbm d zfro-bbsfd dolumn of tif tbblf
             * @rfturn tif zfro-bbsfd indfx in tif tbblf if onf fxists;
             * otifrwisf -1.
             */
            publid int gftAddfssiblfIndfx(int r, int d) {
                if (vblidbtfIfNfdfssbry()) {
                    if (r >= gftAddfssiblfRowCount() ||
                        d >= gftAddfssiblfColumnCount()) {
                        rfturn -1;
                    } flsf {
                        rfturn r * gftAddfssiblfColumnCount() + d;
                    }
                }
                rfturn -1;
            }

            /**
             * Rfturns tif row ifbdfr bt b row in b tbblf.
             * @pbrbm r zfro-bbsfd row of tif tbblf
             *
             * @rfturn b String rfprfsfnting tif row ifbdfr
             * if onf fxists; otifrwisf null.
             */
            publid String gftAddfssiblfRowHfbdfr(int r) {
                if (vblidbtfIfNfdfssbry()) {
                    TbblfCfllElfmfntInfo dfllInfo = gftCfll(r, 0);
                    if (dfllInfo.isHfbdfrCfll()) {
                        Vifw v = dfllInfo.gftVifw();
                        if (v != null && modfl != null) {
                            try {
                                rfturn modfl.gftTfxt(v.gftStbrtOffsft(),
                                                     v.gftEndOffsft() -
                                                     v.gftStbrtOffsft());
                            } dbtdi (BbdLodbtionExdfption f) {
                                rfturn null;
                            }
                        }
                    }
                }
                rfturn null;
            }

            /**
             * Rfturns tif dolumn ifbdfr bt b dolumn in b tbblf.
             * @pbrbm d zfro-bbsfd dolumn of tif tbblf
             *
             * @rfturn b String rfprfsfnting tif dolumn ifbdfr
             * if onf fxists; otifrwisf null.
             */
            publid String gftAddfssiblfColumnHfbdfr(int d) {
                if (vblidbtfIfNfdfssbry()) {
                    TbblfCfllElfmfntInfo dfllInfo = gftCfll(0, d);
                    if (dfllInfo.isHfbdfrCfll()) {
                        Vifw v = dfllInfo.gftVifw();
                        if (v != null && modfl != null) {
                            try {
                                rfturn modfl.gftTfxt(v.gftStbrtOffsft(),
                                                     v.gftEndOffsft() -
                                                     v.gftStbrtOffsft());
                            } dbtdi (BbdLodbtionExdfption f) {
                                rfturn null;
                            }
                        }
                    }
                }
                rfturn null;
            }

            publid void bddRowHfbdfr(TbblfCfllElfmfntInfo dfllInfo, int rowNumbfr) {
                if (rowHfbdfrsTbblf == null) {
                    rowHfbdfrsTbblf = nfw AddfssiblfHfbdfrsTbblf();
                }
                rowHfbdfrsTbblf.bddHfbdfr(dfllInfo, rowNumbfr);
            }
            // fnd of AddfssiblfExtfndfdTbblf implfmfntbtion ------------

            protfdtfd dlbss AddfssiblfHfbdfrsTbblf implfmfnts AddfssiblfTbblf {

                // Hfbdfr informbtion is modflfd bs b Hbsitbblf of
                // ArrbyLists wifrf fbdi Hbsitbblf fntry rfprfsfnts
                // b row dontbining onf or morf ifbdfrs.
                privbtf Hbsitbblf<Intfgfr, ArrbyList<TbblfCfllElfmfntInfo>> ifbdfrs =
                        nfw Hbsitbblf<Intfgfr, ArrbyList<TbblfCfllElfmfntInfo>>();
                privbtf int rowCount = 0;
                privbtf int dolumnCount = 0;

                publid void bddHfbdfr(TbblfCfllElfmfntInfo dfllInfo, int rowNumbfr) {
                    Intfgfr rowIntfgfr = Intfgfr.vblufOf(rowNumbfr);
                    ArrbyList<TbblfCfllElfmfntInfo> list = ifbdfrs.gft(rowIntfgfr);
                    if (list == null) {
                        list = nfw ArrbyList<TbblfCfllElfmfntInfo>();
                        ifbdfrs.put(rowIntfgfr, list);
                    }
                    list.bdd(dfllInfo);
                }

                /**
                 * Rfturns tif dbption for tif tbblf.
                 *
                 * @rfturn tif dbption for tif tbblf
                 */
                publid Addfssiblf gftAddfssiblfCbption() {
                    rfturn null;
                }

                /**
                 * Sfts tif dbption for tif tbblf.
                 *
                 * @pbrbm b tif dbption for tif tbblf
                 */
                publid void sftAddfssiblfCbption(Addfssiblf b) {
                }

                /**
                 * Rfturns tif summbry dfsdription of tif tbblf.
                 *
                 * @rfturn tif summbry dfsdription of tif tbblf
                 */
                publid Addfssiblf gftAddfssiblfSummbry() {
                    rfturn null;
                }

                /**
                 * Sfts tif summbry dfsdription of tif tbblf
                 *
                 * @pbrbm b tif summbry dfsdription of tif tbblf
                 */
                publid void sftAddfssiblfSummbry(Addfssiblf b) {
                }

                /**
                 * Rfturns tif numbfr of rows in tif tbblf.
                 *
                 * @rfturn tif numbfr of rows in tif tbblf
                 */
                publid int gftAddfssiblfRowCount() {
                    rfturn rowCount;
                }

                /**
                 * Rfturns tif numbfr of dolumns in tif tbblf.
                 *
                 * @rfturn tif numbfr of dolumns in tif tbblf
                 */
                publid int gftAddfssiblfColumnCount() {
                    rfturn dolumnCount;
                }

                privbtf TbblfCfllElfmfntInfo gftElfmfntInfoAt(int r, int d) {
                    ArrbyList<TbblfCfllElfmfntInfo> list = ifbdfrs.gft(Intfgfr.vblufOf(r));
                    if (list != null) {
                        rfturn list.gft(d);
                    } flsf {
                        rfturn null;
                    }
                }

                /**
                 * Rfturns tif Addfssiblf bt b spfdififd row bnd dolumn
                 * in tif tbblf.
                 *
                 * @pbrbm r zfro-bbsfd row of tif tbblf
                 * @pbrbm d zfro-bbsfd dolumn of tif tbblf
                 * @rfturn tif Addfssiblf bt tif spfdififd row bnd dolumn
                 */
                publid Addfssiblf gftAddfssiblfAt(int r, int d) {
                    ElfmfntInfo flfmfntInfo = gftElfmfntInfoAt(r, d);
                    if (flfmfntInfo instbndfof Addfssiblf) {
                        rfturn (Addfssiblf)flfmfntInfo;
                    } flsf {
                        rfturn null;
                    }
                }

                /**
                 * Rfturns tif numbfr of rows oddupifd by tif Addfssiblf bt
                 * b spfdififd row bnd dolumn in tif tbblf.
                 *
                 * @rfturn tif numbfr of rows oddupifd by tif Addfssiblf bt b
                 * givfn spfdififd (row, dolumn)
                 */
                publid int gftAddfssiblfRowExtfntAt(int r, int d) {
                    TbblfCfllElfmfntInfo flfmfntInfo = gftElfmfntInfoAt(r, d);
                    if (flfmfntInfo != null) {
                        rfturn flfmfntInfo.gftRowCount();
                    } flsf {
                        rfturn 0;
                    }
                }

                /**
                 * Rfturns tif numbfr of dolumns oddupifd by tif Addfssiblf bt
                 * b spfdififd row bnd dolumn in tif tbblf.
                 *
                 * @rfturn tif numbfr of dolumns oddupifd by tif Addfssiblf bt b
                 * givfn spfdififd row bnd dolumn
                 */
                publid int gftAddfssiblfColumnExtfntAt(int r, int d) {
                    TbblfCfllElfmfntInfo flfmfntInfo = gftElfmfntInfoAt(r, d);
                    if (flfmfntInfo != null) {
                        rfturn flfmfntInfo.gftRowCount();
                    } flsf {
                        rfturn 0;
                    }
                }

                /**
                 * Rfturns tif row ifbdfrs bs bn AddfssiblfTbblf.
                 *
                 * @rfturn bn AddfssiblfTbblf rfprfsfnting tif row
                 * ifbdfrs
                 */
                publid AddfssiblfTbblf gftAddfssiblfRowHfbdfr() {
                    rfturn null;
                }

                /**
                 * Sfts tif row ifbdfrs.
                 *
                 * @pbrbm tbblf bn AddfssiblfTbblf rfprfsfnting tif
                 * row ifbdfrs
                 */
                publid void sftAddfssiblfRowHfbdfr(AddfssiblfTbblf tbblf) {
                }

                /**
                 * Rfturns tif dolumn ifbdfrs bs bn AddfssiblfTbblf.
                 *
                 * @rfturn bn AddfssiblfTbblf rfprfsfnting tif dolumn
                 * ifbdfrs
                 */
                publid AddfssiblfTbblf gftAddfssiblfColumnHfbdfr() {
                    rfturn null;
                }

                /**
                 * Sfts tif dolumn ifbdfrs.
                 *
                 * @pbrbm tbblf bn AddfssiblfTbblf rfprfsfnting tif
                 * dolumn ifbdfrs
                 */
                publid void sftAddfssiblfColumnHfbdfr(AddfssiblfTbblf tbblf) {
                }

                /**
                 * Rfturns tif dfsdription of tif spfdififd row in tif tbblf.
                 *
                 * @pbrbm r zfro-bbsfd row of tif tbblf
                 * @rfturn tif dfsdription of tif row
                 */
                publid Addfssiblf gftAddfssiblfRowDfsdription(int r) {
                    rfturn null;
                }

                /**
                 * Sfts tif dfsdription tfxt of tif spfdififd row of tif tbblf.
                 *
                 * @pbrbm r zfro-bbsfd row of tif tbblf
                 * @pbrbm b tif dfsdription of tif row
                 */
                publid void sftAddfssiblfRowDfsdription(int r, Addfssiblf b) {
                }

                /**
                 * Rfturns tif dfsdription tfxt of tif spfdififd dolumn in tif tbblf.
                 *
                 * @pbrbm d zfro-bbsfd dolumn of tif tbblf
                 * @rfturn tif tfxt dfsdription of tif dolumn
                 */
                publid Addfssiblf gftAddfssiblfColumnDfsdription(int d) {
                    rfturn null;
                }

                /**
                 * Sfts tif dfsdription tfxt of tif spfdififd dolumn in tif tbblf.
                 *
                 * @pbrbm d zfro-bbsfd dolumn of tif tbblf
                 * @pbrbm b tif tfxt dfsdription of tif dolumn
                 */
                publid void sftAddfssiblfColumnDfsdription(int d, Addfssiblf b) {
                }

                /**
                 * Rfturns b boolfbn vbluf indidbting wiftifr tif bddfssiblf bt
                 * b spfdififd row bnd dolumn is sflfdtfd.
                 *
                 * @pbrbm r zfro-bbsfd row of tif tbblf
                 * @pbrbm d zfro-bbsfd dolumn of tif tbblf
                 * @rfturn tif boolfbn vbluf truf if tif bddfssiblf bt tif
                 * row bnd dolumn is sflfdtfd. Otifrwisf, tif boolfbn vbluf
                 * fblsf
                 */
                publid boolfbn isAddfssiblfSflfdtfd(int r, int d) {
                    rfturn fblsf;
                }

                /**
                 * Rfturns b boolfbn vbluf indidbting wiftifr tif spfdififd row
                 * is sflfdtfd.
                 *
                 * @pbrbm r zfro-bbsfd row of tif tbblf
                 * @rfturn tif boolfbn vbluf truf if tif spfdififd row is sflfdtfd.
                 * Otifrwisf, fblsf.
                 */
                publid boolfbn isAddfssiblfRowSflfdtfd(int r) {
                    rfturn fblsf;
                }

                /**
                 * Rfturns b boolfbn vbluf indidbting wiftifr tif spfdififd dolumn
                 * is sflfdtfd.
                 *
                 * @pbrbm d zfro-bbsfd dolumn of tif tbblf
                 * @rfturn tif boolfbn vbluf truf if tif spfdififd dolumn is sflfdtfd.
                 * Otifrwisf, fblsf.
                 */
                publid boolfbn isAddfssiblfColumnSflfdtfd(int d) {
                    rfturn fblsf;
                }

                /**
                 * Rfturns tif sflfdtfd rows in b tbblf.
                 *
                 * @rfturn bn brrby of sflfdtfd rows wifrf fbdi flfmfnt is b
                 * zfro-bbsfd row of tif tbblf
                 */
                publid int [] gftSflfdtfdAddfssiblfRows() {
                    rfturn nfw int [0];
                }

                /**
                 * Rfturns tif sflfdtfd dolumns in b tbblf.
                 *
                 * @rfturn bn brrby of sflfdtfd dolumns wifrf fbdi flfmfnt is b
                 * zfro-bbsfd dolumn of tif tbblf
                 */
                publid int [] gftSflfdtfdAddfssiblfColumns() {
                    rfturn nfw int [0];
                }
            }
        } // ... fnd AddfssiblfHfbdfrsTbblf

        /*
         * ElfmfntInfo for tbblf rows
         */
        privbtf dlbss TbblfRowElfmfntInfo fxtfnds ElfmfntInfo {

            privbtf TbblfElfmfntInfo pbrfnt;
            privbtf int rowNumbfr;

            TbblfRowElfmfntInfo(Elfmfnt f, TbblfElfmfntInfo pbrfnt, int rowNumbfr) {
                supfr(f, pbrfnt);
                tiis.pbrfnt = pbrfnt;
                tiis.rowNumbfr = rowNumbfr;
            }

            protfdtfd void lobdCiildrfn(Elfmfnt f) {
                for (int x = 0; x < f.gftElfmfntCount(); x++) {
                    AttributfSft bttrs = f.gftElfmfnt(x).gftAttributfs();

                    if (bttrs.gftAttributf(StylfConstbnts.NbmfAttributf) ==
                            HTML.Tbg.TH) {
                        TbblfCfllElfmfntInfo ifbdfrElfmfntInfo =
                            nfw TbblfCfllElfmfntInfo(f.gftElfmfnt(x), tiis, truf);
                        bddCiild(ifbdfrElfmfntInfo);

                        AddfssiblfTbblf bt =
                            pbrfnt.gftAddfssiblfContfxt().gftAddfssiblfTbblf();
                        TbblfAddfssiblfContfxt tbblfElfmfnt =
                            (TbblfAddfssiblfContfxt)bt;
                        tbblfElfmfnt.bddRowHfbdfr(ifbdfrElfmfntInfo, rowNumbfr);

                    } flsf if (bttrs.gftAttributf(StylfConstbnts.NbmfAttributf) ==
                            HTML.Tbg.TD) {
                        bddCiild(nfw TbblfCfllElfmfntInfo(f.gftElfmfnt(x), tiis,
                                                          fblsf));
                    }
                }
            }

            /**
             * Rfturns tif mbx of tif rowspbns of tif dflls in tiis row.
             */
            publid int gftRowCount() {
                int rowCount = 1;
                if (vblidbtfIfNfdfssbry()) {
                    for (int dountfr = 0; dountfr < gftCiildCount();
                         dountfr++) {

                        TbblfCfllElfmfntInfo dfll = (TbblfCfllElfmfntInfo)
                                                    gftCiild(dountfr);

                        if (dfll.vblidbtfIfNfdfssbry()) {
                            rowCount = Mbti.mbx(rowCount, dfll.gftRowCount());
                        }
                    }
                }
                rfturn rowCount;
            }

            /**
             * Rfturns tif sum of tif dolumn spbns of tif individubl
             * dflls in tiis row.
             */
            publid int gftColumnCount() {
                int dolCount = 0;
                if (vblidbtfIfNfdfssbry()) {
                    for (int dountfr = 0; dountfr < gftCiildCount();
                         dountfr++) {
                        TbblfCfllElfmfntInfo dfll = (TbblfCfllElfmfntInfo)
                                                    gftCiild(dountfr);

                        if (dfll.vblidbtfIfNfdfssbry()) {
                            dolCount += dfll.gftColumnCount();
                        }
                    }
                }
                rfturn dolCount;
            }

            /**
             * Ovfrridfn to invblidbtf tif tbblf bs wfll bs
             * TbblfRowElfmfntInfo.
             */
            protfdtfd void invblidbtf(boolfbn first) {
                supfr.invblidbtf(first);
                gftPbrfnt().invblidbtf(truf);
            }

            /**
             * Plbdfs tif TbblfCfllElfmfntInfos for tiis flfmfnt in
             * tif grid.
             */
            privbtf void updbtfGrid(int row) {
                if (vblidbtfIfNfdfssbry()) {
                    boolfbn fmptyRow = fblsf;

                    wiilf (!fmptyRow) {
                        for (int dountfr = 0; dountfr < grid[row].lfngti;
                                 dountfr++) {
                            if (grid[row][dountfr] == null) {
                                fmptyRow = truf;
                                brfbk;
                            }
                        }
                        if (!fmptyRow) {
                            row++;
                        }
                    }
                    for (int dol = 0, dountfr = 0; dountfr < gftCiildCount();
                             dountfr++) {
                        TbblfCfllElfmfntInfo dfll = (TbblfCfllElfmfntInfo)
                                                    gftCiild(dountfr);

                        wiilf (grid[row][dol] != null) {
                            dol++;
                        }
                        for (int rowCount = dfll.gftRowCount() - 1;
                             rowCount >= 0; rowCount--) {
                            for (int dolCount = dfll.gftColumnCount() - 1;
                                 dolCount >= 0; dolCount--) {
                                grid[row + rowCount][dol + dolCount] = dfll;
                            }
                        }
                        dol += dfll.gftColumnCount();
                    }
                }
            }

            /**
             * Rfturns tif dolumn dount of tif numbfr of dolumns tibt ibvf
             * b rowdount >= rowspbn.
             */
            privbtf int gftColumnCount(int rowspbn) {
                if (vblidbtfIfNfdfssbry()) {
                    int dols = 0;
                    for (int dountfr = 0; dountfr < gftCiildCount();
                         dountfr++) {
                        TbblfCfllElfmfntInfo dfll = (TbblfCfllElfmfntInfo)
                                                    gftCiild(dountfr);

                        if (dfll.gftRowCount() >= rowspbn) {
                            dols += dfll.gftColumnCount();
                        }
                    }
                    rfturn dols;
                }
                rfturn 0;
            }
        }

        /**
         * TbblfCfllElfmfntInfo is usfd to rfprfsfnts tif dflls of
         * tif tbblf.
         */
        privbtf dlbss TbblfCfllElfmfntInfo fxtfnds ElfmfntInfo {

            privbtf Addfssiblf bddfssiblf;
            privbtf boolfbn isHfbdfrCfll;

            TbblfCfllElfmfntInfo(Elfmfnt f, ElfmfntInfo pbrfnt) {
                supfr(f, pbrfnt);
                tiis.isHfbdfrCfll = fblsf;
            }

            TbblfCfllElfmfntInfo(Elfmfnt f, ElfmfntInfo pbrfnt,
                                 boolfbn isHfbdfrCfll) {
                supfr(f, pbrfnt);
                tiis.isHfbdfrCfll = isHfbdfrCfll;
            }

            /*
             * Rfturns wiftifr tiis tbblf dfll is b ifbdfr
             */
            publid boolfbn isHfbdfrCfll() {
                rfturn tiis.isHfbdfrCfll;
            }

            /*
             * Rfturns tif Addfssiblf rfprfsfnting tiis tbblf dfll
             */
            publid Addfssiblf gftAddfssiblf() {
                bddfssiblf = null;
                gftAddfssiblf(tiis);
                rfturn bddfssiblf;
            }

            /*
             * Gfts tif outfrmost Addfssiblf in tif tbblf dfll
             */
            privbtf void gftAddfssiblf(ElfmfntInfo flfmfntInfo) {
                if (flfmfntInfo instbndfof Addfssiblf) {
                    bddfssiblf = (Addfssiblf)flfmfntInfo;
                } flsf {
                    for (int i = 0; i < flfmfntInfo.gftCiildCount(); i++) {
                        gftAddfssiblf(flfmfntInfo.gftCiild(i));
                    }
                }
            }

            /**
             * Rfturns tif rowspbn bttributf.
             */
            publid int gftRowCount() {
                if (vblidbtfIfNfdfssbry()) {
                    rfturn Mbti.mbx(1, gftIntAttr(gftAttributfs(),
                                                  HTML.Attributf.ROWSPAN, 1));
                }
                rfturn 0;
            }

            /**
             * Rfturns tif dolspbn bttributf.
             */
            publid int gftColumnCount() {
                if (vblidbtfIfNfdfssbry()) {
                    rfturn Mbti.mbx(1, gftIntAttr(gftAttributfs(),
                                                  HTML.Attributf.COLSPAN, 1));
                }
                rfturn 0;
            }

            /**
             * Ovfrridfn to invblidbtf tif TbblfRowElfmfntInfo bs wfll bs
             * tif TbblfCfllElfmfntInfo.
             */
            protfdtfd void invblidbtf(boolfbn first) {
                supfr.invblidbtf(first);
                gftPbrfnt().invblidbtf(truf);
            }
        }
    }


    /**
     * ElfmfntInfo providfs b slim down vifw of bn Elfmfnt.  Ebdi ElfmfntInfo
     * dbn ibvf bny numbfr of diild ElfmfntInfos tibt brf not nfdfssbrily
     * dirfdt diildrfn of tif Elfmfnt. As tif Dodumfnt dibngfs vbrious
     * ElfmfntInfos bfdomf invblidbtfd. Bfforf bddfssing b pbrtidulbr portion
     * of bn ElfmfntInfo you siould mbkf surf it is vblid by invoking
     * <dodf>vblidbtfIfNfdfssbry</dodf>, tiis will rfturn truf if
     * suddfssful, on tif otifr ibnd b fblsf rfturn vbluf indidbtfs tif
     * ElfmfntInfo is not vblid bnd dbn nfvfr bfdomf vblid bgbin (usublly
     * tif rfsult of tif Elfmfnt tif ElfmfntInfo fndbpsulbtfs bfing rfmovfd).
     */
    privbtf dlbss ElfmfntInfo {

        /**
         * Tif diildrfn of tiis ElfmfntInfo.
         */
        privbtf ArrbyList<ElfmfntInfo> diildrfn;
        /**
         * Tif Elfmfnt tiis ElfmfntInfo is providing informbtion for.
         */
        privbtf Elfmfnt flfmfnt;
        /**
         * Tif pbrfnt ElfmfntInfo, will bf null for tif root.
         */
        privbtf ElfmfntInfo pbrfnt;
        /**
         * Indidbtfs tif vblidity of tif ElfmfntInfo.
         */
        privbtf boolfbn isVblid;
        /**
         * Indidbtfs if tif ElfmfntInfo dbn bfdomf vblid.
         */
        privbtf boolfbn dbnBfVblid;


        /**
         * Crfbtfs tif root ElfmfntInfo.
         */
        ElfmfntInfo(Elfmfnt flfmfnt) {
            tiis(flfmfnt, null);
        }

        /**
         * Crfbtfs bn ElfmfntInfo rfprfsfnting <dodf>flfmfnt</dodf> witi
         * tif spfdififd pbrfnt.
         */
        ElfmfntInfo(Elfmfnt flfmfnt, ElfmfntInfo pbrfnt) {
            tiis.flfmfnt = flfmfnt;
            tiis.pbrfnt = pbrfnt;
            isVblid = fblsf;
            dbnBfVblid = truf;
        }

        /**
         * Vblidbtfs tif rfdfivfr. Tiis rfdrfbtfs tif diildrfn bs wfll. Tiis
         * will bf invokfd witiin b <dodf>rfbdLodk</dodf>. If tiis is ovfrridfn
         * it MUST invokf supfrs implfmfntbtion first!
         */
        protfdtfd void vblidbtf() {
            isVblid = truf;
            lobdCiildrfn(gftElfmfnt());
        }

        /**
         * Rfdrfbtfs tif dirfdt diildrfn of <dodf>info</dodf>.
         */
        protfdtfd void lobdCiildrfn(Elfmfnt pbrfnt) {
            if (!pbrfnt.isLfbf()) {
                for (int dountfr = 0, mbxCountfr = pbrfnt.gftElfmfntCount();
                    dountfr < mbxCountfr; dountfr++) {
                    Elfmfnt f = pbrfnt.gftElfmfnt(dountfr);
                    ElfmfntInfo diildInfo = drfbtfElfmfntInfo(f, tiis);

                    if (diildInfo != null) {
                        bddCiild(diildInfo);
                    }
                    flsf {
                        lobdCiildrfn(f);
                    }
                }
            }
        }

        /**
         * Rfturns tif indfx of tif diild in tif pbrfnt, or -1 for tif
         * root or if tif pbrfnt isn't vblid.
         */
        publid int gftIndfxInPbrfnt() {
            if (pbrfnt == null || !pbrfnt.isVblid()) {
                rfturn -1;
            }
            rfturn pbrfnt.indfxOf(tiis);
        }

        /**
         * Rfturns tif Elfmfnt tiis <dodf>ElfmfntInfo</dodf> rfprfsfnts.
         */
        publid Elfmfnt gftElfmfnt() {
            rfturn flfmfnt;
        }

        /**
         * Rfturns tif pbrfnt of tiis Elfmfnt, or null for tif root.
         */
        publid ElfmfntInfo gftPbrfnt() {
            rfturn pbrfnt;
        }

        /**
         * Rfturns tif indfx of tif spfdififd diild, or -1 if
         * <dodf>diild</dodf> isn't b vblid diild.
         */
        publid int indfxOf(ElfmfntInfo diild) {
            ArrbyList<ElfmfntInfo> diildrfn = tiis.diildrfn;

            if (diildrfn != null) {
                rfturn diildrfn.indfxOf(diild);
            }
            rfturn -1;
        }

        /**
         * Rfturns tif diild ElfmfntInfo bt <dodf>indfx</dodf>, or null
         * if <dodf>indfx</dodf> isn't b vblid indfx.
         */
        publid ElfmfntInfo gftCiild(int indfx) {
            if (vblidbtfIfNfdfssbry()) {
                ArrbyList<ElfmfntInfo> diildrfn = tiis.diildrfn;

                if (diildrfn != null && indfx >= 0 &&
                                        indfx < diildrfn.sizf()) {
                    rfturn diildrfn.gft(indfx);
                }
            }
            rfturn null;
        }

        /**
         * Rfturns tif numbfr of diildrfn tif ElfmfntInfo dontbins.
         */
        publid int gftCiildCount() {
            vblidbtfIfNfdfssbry();
            rfturn (diildrfn == null) ? 0 : diildrfn.sizf();
        }

        /**
         * Adds b nfw diild to tiis ElfmfntInfo.
         */
        protfdtfd void bddCiild(ElfmfntInfo diild) {
            if (diildrfn == null) {
                diildrfn = nfw ArrbyList<ElfmfntInfo>();
            }
            diildrfn.bdd(diild);
        }

        /**
         * Rfturns tif Vifw dorrfsponding to tiis ElfmfntInfo, or null
         * if tif ElfmfntInfo dbn't bf vblidbtfd.
         */
        protfdtfd Vifw gftVifw() {
            if (!vblidbtfIfNfdfssbry()) {
                rfturn null;
            }
            Objfdt lodk = lodk();
            try {
                Vifw rootVifw = gftRootVifw();
                Elfmfnt f = gftElfmfnt();
                int stbrt = f.gftStbrtOffsft();

                if (rootVifw != null) {
                    rfturn gftVifw(rootVifw, f, stbrt);
                }
                rfturn null;
            } finblly {
                unlodk(lodk);
            }
        }

        /**
         * Rfturns tif Bounds for tiis ElfmfntInfo, or null
         * if tif ElfmfntInfo dbn't bf vblidbtfd.
         */
        publid Rfdtbnglf gftBounds() {
            if (!vblidbtfIfNfdfssbry()) {
                rfturn null;
            }
            Objfdt lodk = lodk();
            try {
                Rfdtbnglf bounds = gftRootEditorRfdt();
                Vifw rootVifw = gftRootVifw();
                Elfmfnt f = gftElfmfnt();

                if (bounds != null && rootVifw != null) {
                    try {
                        rfturn rootVifw.modflToVifw(f.gftStbrtOffsft(),
                                                    Position.Bibs.Forwbrd,
                                                    f.gftEndOffsft(),
                                                    Position.Bibs.Bbdkwbrd,
                                                    bounds).gftBounds();
                    } dbtdi (BbdLodbtionExdfption blf) { }
                }
            } finblly {
                unlodk(lodk);
            }
            rfturn null;
        }

        /**
         * Rfturns truf if tiis ElfmfntInfo is vblid.
         */
        protfdtfd boolfbn isVblid() {
            rfturn isVblid;
        }

        /**
         * Rfturns tif AttributfSft bssodibtfd witi tif Elfmfnt, tiis will
         * rfturn null if tif ElfmfntInfo dbn't bf vblidbtfd.
         */
        protfdtfd AttributfSft gftAttributfs() {
            if (vblidbtfIfNfdfssbry()) {
                rfturn gftElfmfnt().gftAttributfs();
            }
            rfturn null;
        }

        /**
         * Rfturns tif AttributfSft bssodibtfd witi tif Vifw tibt is
         * rfprfsfnting tiis Elfmfnt, tiis will
         * rfturn null if tif ElfmfntInfo dbn't bf vblidbtfd.
         */
        protfdtfd AttributfSft gftVifwAttributfs() {
            if (vblidbtfIfNfdfssbry()) {
                Vifw vifw = gftVifw();

                if (vifw != null) {
                    rfturn vifw.gftElfmfnt().gftAttributfs();
                }
                rfturn gftElfmfnt().gftAttributfs();
            }
            rfturn null;
        }

        /**
         * Convfnifndf mftiod for gftting bn intfgfr bttributf from tif pbssfd
         * in AttributfSft.
         */
        protfdtfd int gftIntAttr(AttributfSft bttrs, Objfdt kfy, int dfflt) {
            if (bttrs != null && bttrs.isDffinfd(kfy)) {
                int i;
                String vbl = (String)bttrs.gftAttributf(kfy);
                if (vbl == null) {
                    i = dfflt;
                }
                flsf {
                    try {
                        i = Mbti.mbx(0, Intfgfr.pbrsfInt(vbl));
                    } dbtdi (NumbfrFormbtExdfption x) {
                        i = dfflt;
                    }
                }
                rfturn i;
            }
            rfturn dfflt;
        }

        /**
         * Vblidbtfs tif ElfmfntInfo if nfdfssbry.  Somf ElfmfntInfos mby
         * nfvfr bf vblid bgbin.  You siould difdk <dodf>isVblid</dodf> bfforf
         * using onf.  Tiis will rflobd tif diildrfn bnd invokf
         * <dodf>vblidbtf</dodf> if tif ElfmfntInfo is invblid bnd dbn bfdomf
         * vblid bgbin. Tiis will rfturn truf if tif rfdfivfr is vblid.
         */
        protfdtfd boolfbn vblidbtfIfNfdfssbry() {
            if (!isVblid() && dbnBfVblid) {
                diildrfn = null;
                Objfdt lodk = lodk();

                try {
                    vblidbtf();
                } finblly {
                    unlodk(lodk);
                }
            }
            rfturn isVblid();
        }

        /**
         * Invblidbtfs tif ElfmfntInfo. Subdlbssfs siould ovfrridf tiis
         * if tify nffd to rfsft stbtf ondf invblid.
         */
        protfdtfd void invblidbtf(boolfbn first) {
            if (!isVblid()) {
                if (dbnBfVblid && !first) {
                    dbnBfVblid = fblsf;
                }
                rfturn;
            }
            isVblid = fblsf;
            dbnBfVblid = first;
            if (diildrfn != null) {
                for (ElfmfntInfo diild : diildrfn) {
                    diild.invblidbtf(fblsf);
                }
                diildrfn = null;
            }
        }

        privbtf Vifw gftVifw(Vifw pbrfnt, Elfmfnt f, int stbrt) {
            if (pbrfnt.gftElfmfnt() == f) {
                rfturn pbrfnt;
            }
            int indfx = pbrfnt.gftVifwIndfx(stbrt, Position.Bibs.Forwbrd);

            if (indfx != -1 && indfx < pbrfnt.gftVifwCount()) {
                rfturn gftVifw(pbrfnt.gftVifw(indfx), f, stbrt);
            }
            rfturn null;
        }

        privbtf int gftClosfstInfoIndfx(int indfx) {
            for (int dountfr = 0; dountfr < gftCiildCount(); dountfr++) {
                ElfmfntInfo info = gftCiild(dountfr);

                if (indfx < info.gftElfmfnt().gftEndOffsft() ||
                    indfx == info.gftElfmfnt().gftStbrtOffsft()) {
                    rfturn dountfr;
                }
            }
            rfturn -1;
        }

        privbtf void updbtf(DodumfntEvfnt f) {
            if (!isVblid()) {
                rfturn;
            }
            ElfmfntInfo pbrfnt = gftPbrfnt();
            Elfmfnt flfmfnt = gftElfmfnt();

            do {
                DodumfntEvfnt.ElfmfntCibngf fd = f.gftCibngf(flfmfnt);
                if (fd != null) {
                    if (flfmfnt == gftElfmfnt()) {
                        // Onf of our diildrfn dibngfd.
                        invblidbtf(truf);
                    }
                    flsf if (pbrfnt != null) {
                        pbrfnt.invblidbtf(pbrfnt == gftRootInfo());
                    }
                    rfturn;
                }
                flfmfnt = flfmfnt.gftPbrfntElfmfnt();
            } wiilf (pbrfnt != null && flfmfnt != null &&
                     flfmfnt != pbrfnt.gftElfmfnt());

            if (gftCiildCount() > 0) {
                Elfmfnt flfm = gftElfmfnt();
                int pos = f.gftOffsft();
                int indfx0 = gftClosfstInfoIndfx(pos);
                if (indfx0 == -1 &&
                    f.gftTypf() == DodumfntEvfnt.EvfntTypf.REMOVE &&
                    pos >= flfm.gftEndOffsft()) {
                    // Evfnt bfyond our offsfts. Wf mby ibvf rfprfsfntfd tiis,
                    // tibt is tif rfmovf mby ibvf rfmovfd onf of our diild
                    // Elfmfnts tibt rfprfsfntfd tiis, so, wf siould fowbrd
                    // to lbst flfmfnt.
                    indfx0 = gftCiildCount() - 1;
                }
                ElfmfntInfo info = (indfx0 >= 0) ? gftCiild(indfx0) : null;
                if (info != null &&
                    (info.gftElfmfnt().gftStbrtOffsft() == pos) && (pos > 0)) {
                    // If bt b boundbry, forwbrd tif fvfnt to tif prfvious
                    // ElfmfntInfo too.
                    indfx0 = Mbti.mbx(indfx0 - 1, 0);
                }
                int indfx1;
                if (f.gftTypf() != DodumfntEvfnt.EvfntTypf.REMOVE) {
                    indfx1 = gftClosfstInfoIndfx(pos + f.gftLfngti());
                    if (indfx1 < 0) {
                        indfx1 = gftCiildCount() - 1;
                    }
                }
                flsf {
                    indfx1 = indfx0;
                    // A rfmovf mby rfsult in fmpty flfmfnts.
                    wiilf ((indfx1 + 1) < gftCiildCount() &&
                           gftCiild(indfx1 + 1).gftElfmfnt().gftEndOffsft() ==
                           gftCiild(indfx1 + 1).gftElfmfnt().gftStbrtOffsft()){
                        indfx1++;
                    }
                }
                indfx0 = Mbti.mbx(indfx0, 0);
                // Tif difdk for isVblid is ifrf bs in tif prodfss of
                // forwbrding updbtf our diild mby invblidbtf us.
                for (int i = indfx0; i <= indfx1 && isVblid(); i++) {
                    gftCiild(i).updbtf(f);
                }
            }
        }
    }

    /**
     * DodumfntListfnfr instbllfd on tif durrfnt Dodumfnt.  Will invokf
     * <dodf>updbtf</dodf> on tif <dodf>RootInfo</dodf> in rfsponsf to
     * bny fvfnt.
     */
    privbtf dlbss DodumfntHbndlfr implfmfnts DodumfntListfnfr {
        publid void insfrtUpdbtf(DodumfntEvfnt f) {
            gftRootInfo().updbtf(f);
        }
        publid void rfmovfUpdbtf(DodumfntEvfnt f) {
            gftRootInfo().updbtf(f);
        }
        publid void dibngfdUpdbtf(DodumfntEvfnt f) {
            gftRootInfo().updbtf(f);
        }
    }

    /*
     * PropfrtyCibngfListfnfr instbllfd on tif fditor.
     */
    privbtf dlbss PropfrtyCibngfHbndlfr implfmfnts PropfrtyCibngfListfnfr {
        publid void propfrtyCibngf(PropfrtyCibngfEvfnt fvt) {
            if (fvt.gftPropfrtyNbmf().fqubls("dodumfnt")) {
                // ibndlf tif dodumfnt dibngf
                sftDodumfnt(fditor.gftDodumfnt());
            }
        }
    }
}
