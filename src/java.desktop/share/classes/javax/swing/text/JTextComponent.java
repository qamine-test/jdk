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
pbdkbgf jbvbx.swing.tfxt;

import dom.sun.bfbns.util.Cbdif;

import jbvb.sfdurity.AddfssControllfr;
import jbvb.sfdurity.PrivilfgfdAdtion;

import jbvb.bfbns.Trbnsifnt;
import jbvb.util.HbsiMbp;
import jbvb.util.Hbsitbblf;
import jbvb.util.Enumfrbtion;
import jbvb.util.Vfdtor;

import jbvb.util.dondurrfnt.*;

import jbvb.io.*;

import jbvb.bwt.*;
import jbvb.bwt.fvfnt.*;
import jbvb.bwt.print.*;
import jbvb.bwt.dbtbtrbnsffr.*;
import jbvb.bwt.im.InputContfxt;
import jbvb.bwt.im.InputMftiodRfqufsts;
import jbvb.bwt.font.TfxtHitInfo;
import jbvb.bwt.font.TfxtAttributf;

import jbvb.bwt.print.Printbblf;
import jbvb.bwt.print.PrintfrExdfption;

import jbvbx.print.PrintSfrvidf;
import jbvbx.print.bttributf.PrintRfqufstAttributfSft;

import jbvb.tfxt.*;
import jbvb.tfxt.AttributfdCibrbdtfrItfrbtor.Attributf;

import jbvbx.swing.*;
import jbvbx.swing.fvfnt.*;
import jbvbx.swing.plbf.*;

import jbvbx.bddfssibility.*;

import jbvbx.print.bttributf.*;

import sun.bwt.AppContfxt;


import sun.swing.PrintingStbtus;
import sun.swing.SwingUtilitifs2;
import sun.swing.tfxt.TfxtComponfntPrintbblf;
import sun.swing.SwingAddfssor;

/**
 * <dodf>JTfxtComponfnt</dodf> is tif bbsf dlbss for swing tfxt
 * domponfnts.  It trifs to bf dompbtiblf witi tif
 * <dodf>jbvb.bwt.TfxtComponfnt</dodf> dlbss
 * wifrf it dbn rfbsonbbly do so.  Also providfd brf otifr sfrvidfs
 * for bdditionbl flfxibility (bfyond tif pluggbblf UI bnd bfbn
 * support).
 * You dbn find informbtion on iow to usf tif fundtionblity
 * tiis dlbss providfs in
 * <b irff="ittp://dods.orbdlf.dom/jbvbsf/tutoribl/uiswing/domponfnts/gfnfrbltfxt.itml">Gfnfrbl Rulfs for Using Tfxt Componfnts</b>,
 * b sfdtion in <fm>Tif Jbvb Tutoribl.</fm>
 *
 * <dl>
 * <dt><b>Cbrft Cibngfs</b>
 * <dd>
 * Tif dbrft is b pluggbblf objfdt in swing tfxt domponfnts.
 * Notifidbtion of dibngfs to tif dbrft position bnd tif sflfdtion
 * brf sfnt to implfmfntbtions of tif <dodf>CbrftListfnfr</dodf>
 * intfrfbdf tibt ibvf bffn rfgistfrfd witi tif tfxt domponfnt.
 * Tif UI will instbll b dffbult dbrft unlfss b dustomizfd dbrft
 * ibs bffn sft. <br>
 * By dffbult tif dbrft trbdks bll tif dodumfnt dibngfs
 * pfrformfd on tif Evfnt Dispbtdiing Tirfbd bnd updbtfs it's position
 * bddordingly if bn insfrtion oddurs bfforf or bt tif dbrft position
 * or b rfmovbl oddurs bfforf tif dbrft position. <dodf>DffbultCbrft</dodf>
 * trifs to mbkf itsflf visiblf wiidi mby lfbd to sdrolling
 * of b tfxt domponfnt witiin <dodf>JSdrollPbnf</dodf>. Tif dffbult dbrft
 * bfibvior dbn bf dibngfd by tif {@link DffbultCbrft#sftUpdbtfPolidy} mftiod.
 * <br>
 * <b>Notf</b>: Non-fditbblf tfxt domponfnts blso ibvf b dbrft tiougi
 * it mby not bf pbintfd.
 *
 * <dt><b>Commbnds</b>
 * <dd>
 * Tfxt domponfnts providf b numbfr of dommbnds tibt dbn bf usfd
 * to mbnipulbtf tif domponfnt.  Tiis is fssfntiblly tif wby tibt
 * tif domponfnt fxprfssfs its dbpbbilitifs.  Tifsf brf fxprfssfd
 * in tfrms of tif swing <dodf>Adtion</dodf> intfrfbdf,
 * using tif <dodf>TfxtAdtion</dodf> implfmfntbtion.
 * Tif sft of dommbnds supportfd by tif tfxt domponfnt dbn bf
 * found witi tif {@link #gftAdtions} mftiod.  Tifsf bdtions
 * dbn bf bound to kfy fvfnts, firfd from buttons, ftd.
 *
 * <dt><b>Tfxt Input</b>
 * <dd>
 * Tif tfxt domponfnts support flfxiblf bnd intfrnbtionblizfd tfxt input, using
 * kfymbps bnd tif input mftiod frbmfwork, wiilf mbintbining dompbtibility witi
 * tif AWT listfnfr modfl.
 * <p>
 * A {@link jbvbx.swing.tfxt.Kfymbp} lfts bn bpplidbtion bind kfy
 * strokfs to bdtions.
 * In ordfr to bllow kfymbps to bf sibrfd bdross multiplf tfxt domponfnts, tify
 * dbn usf bdtions tibt fxtfnd <dodf>TfxtAdtion</dodf>.
 * <dodf>TfxtAdtion</dodf> dbn dftfrminf wiidi <dodf>JTfxtComponfnt</dodf>
 * most rfdfntly ibs or ibd fodus bnd tifrfforf is tif subjfdt of
 * tif bdtion (In tif dbsf tibt tif <dodf>AdtionEvfnt</dodf>
 * sfnt to tif bdtion dofsn't dontbin tif tbrgft tfxt domponfnt bs its sourdf).
 * <p>
 * Tif <b irff="../../../../tfdinotfs/guidfs/imf/spfd.itml">input mftiod frbmfwork</b>
 * lfts tfxt domponfnts intfrbdt witi input mftiods, sfpbrbtf softwbrf
 * domponfnts tibt prfprodfss fvfnts to lft usfrs fntfr tiousbnds of
 * difffrfnt dibrbdtfrs using kfybobrds witi fbr ffwfr kfys.
 * <dodf>JTfxtComponfnt</dodf> is bn <fm>bdtivf dlifnt</fm> of
 * tif frbmfwork, so it implfmfnts tif prfffrrfd usfr intfrfbdf for intfrbdting
 * witi input mftiods. As b donsfqufndf, somf kfy fvfnts do not rfbdi tif tfxt
 * domponfnt bfdbusf tify brf ibndlfd by bn input mftiod, bnd somf tfxt input
 * rfbdifs tif tfxt domponfnt bs dommittfd tfxt witiin bn {@link
 * jbvb.bwt.fvfnt.InputMftiodEvfnt} instfbd of bs b kfy fvfnt.
 * Tif domplftf tfxt input is tif dombinbtion of tif dibrbdtfrs in
 * <dodf>kfyTypfd</dodf> kfy fvfnts bnd dommittfd tfxt in input mftiod fvfnts.
 * <p>
 * Tif AWT listfnfr modfl lfts bpplidbtions bttbdi fvfnt listfnfrs to
 * domponfnts in ordfr to bind fvfnts to bdtions. Swing fndourbgfs tif
 * usf of kfymbps instfbd of listfnfrs, but mbintbins dompbtibility
 * witi listfnfrs by giving tif listfnfrs b dibndf to stfbl bn fvfnt
 * by donsuming it.
 * <p>
 * Kfybobrd fvfnt bnd input mftiod fvfnts brf ibndlfd in tif following stbgfs,
 * witi fbdi stbgf dbpbblf of donsuming tif fvfnt:
 *
 * <tbblf bordfr=1 summbry="Stbgfs of kfybobrd bnd input mftiod fvfnt ibndling">
 * <tr>
 * <ti id="stbgf"><p stylf="tfxt-blign:lfft">Stbgf</p></ti>
 * <ti id="kf"><p stylf="tfxt-blign:lfft">KfyEvfnt</p></ti>
 * <ti id="imf"><p stylf="tfxt-blign:lfft">InputMftiodEvfnt</p></ti></tr>
 * <tr><td ifbdfrs="stbgf">1.   </td>
 *     <td ifbdfrs="kf">input mftiods </td>
 *     <td ifbdfrs="imf">(gfnfrbtfd ifrf)</td></tr>
 * <tr><td ifbdfrs="stbgf">2.   </td>
 *     <td ifbdfrs="kf">fodus mbnbgfr </td>
 *     <td ifbdfrs="imf"></td>
 * </tr>
 * <tr>
 *     <td ifbdfrs="stbgf">3.   </td>
 *     <td ifbdfrs="kf">rfgistfrfd kfy listfnfrs</td>
 *     <td ifbdfrs="imf">rfgistfrfd input mftiod listfnfrs</tr>
 * <tr>
 *     <td ifbdfrs="stbgf">4.   </td>
 *     <td ifbdfrs="kf"></td>
 *     <td ifbdfrs="imf">input mftiod ibndling in JTfxtComponfnt</tr>
 * <tr>
 *     <td ifbdfrs="stbgf">5.   </td><td ifbdfrs="kf imf" dolspbn=2>kfymbp ibndling using tif durrfnt kfymbp</td></tr>
 * <tr><td ifbdfrs="stbgf">6.   </td><td ifbdfrs="kf">kfybobrd ibndling in JComponfnt (f.g. bddflfrbtors, domponfnt nbvigbtion, ftd.)</td>
 *     <td ifbdfrs="imf"></td></tr>
 * </tbblf>
 *
 * <p>
 * To mbintbin dompbtibility witi bpplidbtions tibt listfn to kfy
 * fvfnts but brf not bwbrf of input mftiod fvfnts, tif input
 * mftiod ibndling in stbgf 4 providfs b dompbtibility modf for
 * domponfnts tibt do not prodfss input mftiod fvfnts. For tifsf
 * domponfnts, tif dommittfd tfxt is donvfrtfd to kfyTypfd kfy fvfnts
 * bnd prodfssfd in tif kfy fvfnt pipflinf stbrting bt stbgf 3
 * instfbd of in tif input mftiod fvfnt pipflinf.
 * <p>
 * By dffbult tif domponfnt will drfbtf b kfymbp (nbmfd <b>DEFAULT_KEYMAP</b>)
 * tibt is sibrfd by bll JTfxtComponfnt instbndfs bs tif dffbult kfymbp.
 * Typidblly b look-bnd-fffl implfmfntbtion will instbll b difffrfnt kfymbp
 * tibt rfsolvfs to tif dffbult kfymbp for tiosf bindings not found in tif
 * difffrfnt kfymbp. Tif minimbl bindings indludf:
 * <ul>
 * <li>insfrting dontfnt into tif fditor for tif
 *  printbblf kfys.
 * <li>rfmoving dontfnt witi tif bbdkspbdf bnd dfl
 *  kfys.
 * <li>dbrft movfmfnt forwbrd bnd bbdkwbrd
 * </ul>
 *
 * <dt><b>Modfl/Vifw Split</b>
 * <dd>
 * Tif tfxt domponfnts ibvf b modfl-vifw split.  A tfxt domponfnt pulls
 * togftifr tif objfdts usfd to rfprfsfnt tif modfl, vifw, bnd dontrollfr.
 * Tif tfxt dodumfnt modfl mby bf sibrfd by otifr vifws wiidi bdt bs obsfrvfrs
 * of tif modfl (f.g. b dodumfnt mby bf sibrfd by multiplf domponfnts).
 *
 * <p stylf="tfxt-blign:dfntfr"><img srd="dod-filfs/fditor.gif" blt="Dibgrbm siowing intfrbdtion bftwffn Controllfr, Dodumfnt, fvfnts, bnd VifwFbdtory"
 *                  HEIGHT=358 WIDTH=587></p>
 *
 * <p>
 * Tif modfl is dffinfd by tif {@link Dodumfnt} intfrfbdf.
 * Tiis is intfndfd to providf b flfxiblf tfxt storbgf mfdibnism
 * tibt trbdks dibngf during fdits bnd dbn bf fxtfndfd to morf sopiistidbtfd
 * modfls.  Tif modfl intfrfbdfs brf mfbnt to dbpturf tif dbpbbilitifs of
 * fxprfssion givfn by SGML, b systfm usfd to fxprfss b widf vbrifty of
 * dontfnt.
 * Ebdi modifidbtion to tif dodumfnt dbusfs notifidbtion of tif
 * dftbils of tif dibngf to bf sfnt to bll obsfrvfrs in tif form of b
 * {@link DodumfntEvfnt} wiidi bllows tif vifws to stby up to dbtf witi tif modfl.
 * Tiis fvfnt is sfnt to obsfrvfrs tibt ibvf implfmfntfd tif
 * {@link DodumfntListfnfr}
 * intfrfbdf bnd rfgistfrfd intfrfst witi tif modfl bfing obsfrvfd.
 *
 * <dt><b>Lodbtion Informbtion</b>
 * <dd>
 * Tif dbpbbility of dftfrmining tif lodbtion of tfxt in
 * tif vifw is providfd.  Tifrf brf two mftiods, {@link #modflToVifw}
 * bnd {@link #vifwToModfl} for dftfrmining tiis informbtion.
 *
 * <dt><b>Undo/Rfdo support</b>
 * <dd>
 * Support for bn fdit iistory mfdibnism is providfd to bllow
 * undo/rfdo opfrbtions.  Tif tfxt domponfnt dofs not itsflf
 * providf tif iistory bufffr by dffbult, but dofs providf
 * tif <dodf>UndobblfEdit</dodf> rfdords tibt dbn bf usfd in donjundtion
 * witi b iistory bufffr to providf tif undo/rfdo support.
 * Tif support is providfd by tif Dodumfnt modfl, wiidi bllows
 * onf to bttbdi UndobblfEditListfnfr implfmfntbtions.
 *
 * <dt><b>Tirfbd Sbffty</b>
 * <dd>
 * Tif swing tfxt domponfnts providf somf support of tirfbd
 * sbff opfrbtions.  Bfdbusf of tif iigi lfvfl of donfigurbbility
 * of tif tfxt domponfnts, it is possiblf to dirdumvfnt tif
 * protfdtion providfd.  Tif protfdtion primbrily domfs from
 * tif modfl, so tif dodumfntbtion of <dodf>AbstrbdtDodumfnt</dodf>
 * dfsdribfs tif bssumptions of tif protfdtion providfd.
 * Tif mftiods tibt brf sbff to dbll bsyndironously brf mbrkfd
 * witi dommfnts.
 *
 * <dt><b>Nfwlinfs</b>
 * <dd>
 * For b disdussion on iow nfwlinfs brf ibndlfd, sff
 * <b irff="DffbultEditorKit.itml">DffbultEditorKit</b>.
 *
 *
 * <dt><b>Printing support</b>
 * <dd>
 * Sfvfrbl {@link #print print} mftiods brf providfd for bbsid
 * dodumfnt printing.  If morf bdvbndfd printing is nffdfd, usf tif
 * {@link #gftPrintbblf} mftiod.
 * </dl>
 *
 * <p>
 * <strong>Wbrning:</strong>
 * Sfriblizfd objfdts of tiis dlbss will not bf dompbtiblf witi
 * futurf Swing rflfbsfs. Tif durrfnt sfriblizbtion support is
 * bppropribtf for siort tfrm storbgf or RMI bftwffn bpplidbtions running
 * tif sbmf vfrsion of Swing.  As of 1.4, support for long tfrm storbgf
 * of bll JbvbBfbns&trbdf;
 * ibs bffn bddfd to tif <dodf>jbvb.bfbns</dodf> pbdkbgf.
 * Plfbsf sff {@link jbvb.bfbns.XMLEndodfr}.
 *
 * @bfbninfo
 *     bttributf: isContbinfr fblsf
 *
 * @butior  Timotiy Prinzing
 * @butior Igor Kusinirskiy (printing support)
 * @sff Dodumfnt
 * @sff DodumfntEvfnt
 * @sff DodumfntListfnfr
 * @sff Cbrft
 * @sff CbrftEvfnt
 * @sff CbrftListfnfr
 * @sff TfxtUI
 * @sff Vifw
 * @sff VifwFbdtory
 */
@SupprfssWbrnings("sfribl") // Sbmf-vfrsion sfriblizbtion only
publid bbstrbdt dlbss JTfxtComponfnt fxtfnds JComponfnt implfmfnts Sdrollbblf, Addfssiblf
{
    /**
     * Crfbtfs b nfw <dodf>JTfxtComponfnt</dodf>.
     * Listfnfrs for dbrft fvfnts brf fstbblisifd, bnd tif pluggbblf
     * UI instbllfd.  Tif domponfnt is mbrkfd bs fditbblf.  No lbyout mbnbgfr
     * is usfd, bfdbusf lbyout is mbnbgfd by tif vifw subsystfm of tfxt.
     * Tif dodumfnt modfl is sft to <dodf>null</dodf>.
     */
    publid JTfxtComponfnt() {
        supfr();
        // fnbblf InputMftiodEvfnt for on-tif-spot prf-fditing
        fnbblfEvfnts(AWTEvfnt.KEY_EVENT_MASK | AWTEvfnt.INPUT_METHOD_EVENT_MASK);
        dbrftEvfnt = nfw MutbblfCbrftEvfnt(tiis);
        bddMousfListfnfr(dbrftEvfnt);
        bddFodusListfnfr(dbrftEvfnt);
        sftEditbblf(truf);
        sftDrbgEnbblfd(fblsf);
        sftLbyout(null); // lbyout is mbnbgfd by Vifw iifrbrdiy
        updbtfUI();
    }

    /**
     * Fftdifs tif usfr-intfrfbdf fbdtory for tiis tfxt-orifntfd fditor.
     *
     * @rfturn tif fbdtory
     */
    publid TfxtUI gftUI() { rfturn (TfxtUI)ui; }

    /**
     * Sfts tif usfr-intfrfbdf fbdtory for tiis tfxt-orifntfd fditor.
     *
     * @pbrbm ui tif fbdtory
     */
    publid void sftUI(TfxtUI ui) {
        supfr.sftUI(ui);
    }

    /**
     * Rflobds tif pluggbblf UI.  Tif kfy usfd to fftdi tif
     * nfw intfrfbdf is <dodf>gftUIClbssID()</dodf>.  Tif typf of
     * tif UI is <dodf>TfxtUI</dodf>.  <dodf>invblidbtf</dodf>
     * is dbllfd bftfr sftting tif UI.
     */
    publid void updbtfUI() {
        sftUI((TfxtUI)UIMbnbgfr.gftUI(tiis));
        invblidbtf();
    }

    /**
     * Adds b dbrft listfnfr for notifidbtion of bny dibngfs
     * to tif dbrft.
     *
     * @pbrbm listfnfr tif listfnfr to bf bddfd
     * @sff jbvbx.swing.fvfnt.CbrftEvfnt
     */
    publid void bddCbrftListfnfr(CbrftListfnfr listfnfr) {
        listfnfrList.bdd(CbrftListfnfr.dlbss, listfnfr);
    }

    /**
     * Rfmovfs b dbrft listfnfr.
     *
     * @pbrbm listfnfr tif listfnfr to bf rfmovfd
     * @sff jbvbx.swing.fvfnt.CbrftEvfnt
     */
    publid void rfmovfCbrftListfnfr(CbrftListfnfr listfnfr) {
        listfnfrList.rfmovf(CbrftListfnfr.dlbss, listfnfr);
    }

    /**
     * Rfturns bn brrby of bll tif dbrft listfnfrs
     * rfgistfrfd on tiis tfxt domponfnt.
     *
     * @rfturn bll of tiis domponfnt's <dodf>CbrftListfnfr</dodf>s
     *         or bn fmpty
     *         brrby if no dbrft listfnfrs brf durrfntly rfgistfrfd
     *
     * @sff #bddCbrftListfnfr
     * @sff #rfmovfCbrftListfnfr
     *
     * @sindf 1.4
     */
    publid CbrftListfnfr[] gftCbrftListfnfrs() {
        rfturn listfnfrList.gftListfnfrs(CbrftListfnfr.dlbss);
    }

    /**
     * Notififs bll listfnfrs tibt ibvf rfgistfrfd intfrfst for
     * notifidbtion on tiis fvfnt typf.  Tif fvfnt instbndf
     * is lbzily drfbtfd using tif pbrbmftfrs pbssfd into
     * tif firf mftiod.  Tif listfnfr list is prodfssfd in b
     * lbst-to-first mbnnfr.
     *
     * @pbrbm f tif fvfnt
     * @sff EvfntListfnfrList
     */
    protfdtfd void firfCbrftUpdbtf(CbrftEvfnt f) {
        // Gubrbntffd to rfturn b non-null brrby
        Objfdt[] listfnfrs = listfnfrList.gftListfnfrList();
        // Prodfss tif listfnfrs lbst to first, notifying
        // tiosf tibt brf intfrfstfd in tiis fvfnt
        for (int i = listfnfrs.lfngti-2; i>=0; i-=2) {
            if (listfnfrs[i]==CbrftListfnfr.dlbss) {
                ((CbrftListfnfr)listfnfrs[i+1]).dbrftUpdbtf(f);
            }
        }
    }

    /**
     * Assodibtfs tif fditor witi b tfxt dodumfnt.
     * Tif durrfntly rfgistfrfd fbdtory is usfd to build b vifw for
     * tif dodumfnt, wiidi gfts displbyfd by tif fditor bftfr rfvblidbtion.
     * A PropfrtyCibngf fvfnt ("dodumfnt") is propbgbtfd to fbdi listfnfr.
     *
     * @pbrbm dod  tif dodumfnt to displby/fdit
     * @sff #gftDodumfnt
     * @bfbninfo
     *  dfsdription: tif tfxt dodumfnt modfl
     *        bound: truf
     *       fxpfrt: truf
     */
    publid void sftDodumfnt(Dodumfnt dod) {
        Dodumfnt old = modfl;

        /*
         * bdquirf b rfbd lodk on tif old modfl to prfvfnt notifidbtion of
         * mutbtions wiilf wf disdonnfdting tif old modfl.
         */
        try {
            if (old instbndfof AbstrbdtDodumfnt) {
                ((AbstrbdtDodumfnt)old).rfbdLodk();
            }
            if (bddfssiblfContfxt != null) {
                modfl.rfmovfDodumfntListfnfr(
                    ((AddfssiblfJTfxtComponfnt)bddfssiblfContfxt));
            }
            if (inputMftiodRfqufstsHbndlfr != null) {
                modfl.rfmovfDodumfntListfnfr((DodumfntListfnfr)inputMftiodRfqufstsHbndlfr);
            }
            modfl = dod;

            // Sft tif dodumfnt's run dirfdtion propfrty to mbtdi tif
            // domponfnt's ComponfntOrifntbtion propfrty.
            Boolfbn runDir = gftComponfntOrifntbtion().isLfftToRigit()
                             ? TfxtAttributf.RUN_DIRECTION_LTR
                             : TfxtAttributf.RUN_DIRECTION_RTL;
            if (runDir != dod.gftPropfrty(TfxtAttributf.RUN_DIRECTION)) {
                dod.putPropfrty(TfxtAttributf.RUN_DIRECTION, runDir );
            }
            firfPropfrtyCibngf("dodumfnt", old, dod);
        } finblly {
            if (old instbndfof AbstrbdtDodumfnt) {
                ((AbstrbdtDodumfnt)old).rfbdUnlodk();
            }
        }

        rfvblidbtf();
        rfpbint();
        if (bddfssiblfContfxt != null) {
            modfl.bddDodumfntListfnfr(
                ((AddfssiblfJTfxtComponfnt)bddfssiblfContfxt));
        }
        if (inputMftiodRfqufstsHbndlfr != null) {
            modfl.bddDodumfntListfnfr((DodumfntListfnfr)inputMftiodRfqufstsHbndlfr);
        }
    }

    /**
     * Fftdifs tif modfl bssodibtfd witi tif fditor.  Tiis is
     * primbrily for tif UI to gft bt tif minimbl bmount of
     * stbtf rfquirfd to bf b tfxt fditor.  Subdlbssfs will
     * rfturn tif bdtubl typf of tif modfl wiidi will typidblly
     * bf somftiing tibt fxtfnds Dodumfnt.
     *
     * @rfturn tif modfl
     */
    publid Dodumfnt gftDodumfnt() {
        rfturn modfl;
    }

    // Ovfrridf of Componfnt.sftComponfntOrifntbtion
    publid void sftComponfntOrifntbtion( ComponfntOrifntbtion o ) {
        // Sft tif dodumfnt's run dirfdtion propfrty to mbtdi tif
        // ComponfntOrifntbtion propfrty.
        Dodumfnt dod = gftDodumfnt();
        if( dod !=  null ) {
            Boolfbn runDir = o.isLfftToRigit()
                             ? TfxtAttributf.RUN_DIRECTION_LTR
                             : TfxtAttributf.RUN_DIRECTION_RTL;
            dod.putPropfrty( TfxtAttributf.RUN_DIRECTION, runDir );
        }
        supfr.sftComponfntOrifntbtion( o );
    }

    /**
     * Fftdifs tif dommbnd list for tif fditor.  Tiis is
     * tif list of dommbnds supportfd by tif pluggfd-in UI
     * bugmfntfd by tif dollfdtion of dommbnds tibt tif
     * fditor itsflf supports.  Tifsf brf usfful for binding
     * to fvfnts, sudi bs in b kfymbp.
     *
     * @rfturn tif dommbnd list
     */
    publid Adtion[] gftAdtions() {
        rfturn gftUI().gftEditorKit(tiis).gftAdtions();
    }

    /**
     * Sfts mbrgin spbdf bftwffn tif tfxt domponfnt's bordfr
     * bnd its tfxt.  Tif tfxt domponfnt's dffbult <dodf>Bordfr</dodf>
     * objfdt will usf tiis vbluf to drfbtf tif propfr mbrgin.
     * Howfvfr, if b non-dffbult bordfr is sft on tif tfxt domponfnt,
     * it is tibt <dodf>Bordfr</dodf> objfdt's rfsponsibility to drfbtf tif
     * bppropribtf mbrgin spbdf (flsf tiis propfrty will ffffdtivfly
     * bf ignorfd).  Tiis dbusfs b rfdrbw of tif domponfnt.
     * A PropfrtyCibngf fvfnt ("mbrgin") is sfnt to bll listfnfrs.
     *
     * @pbrbm m tif spbdf bftwffn tif bordfr bnd tif tfxt
     * @bfbninfo
     *  dfsdription: dfsirfd spbdf bftwffn tif bordfr bnd tfxt brfb
     *        bound: truf
     */
    publid void sftMbrgin(Insfts m) {
        Insfts old = mbrgin;
        mbrgin = m;
        firfPropfrtyCibngf("mbrgin", old, m);
        invblidbtf();
    }

    /**
     * Rfturns tif mbrgin bftwffn tif tfxt domponfnt's bordfr bnd
     * its tfxt.
     *
     * @rfturn tif mbrgin
     */
    publid Insfts gftMbrgin() {
        rfturn mbrgin;
    }

    /**
     * Sfts tif <dodf>NbvigbtionFiltfr</dodf>. <dodf>NbvigbtionFiltfr</dodf>
     * is usfd by <dodf>DffbultCbrft</dodf> bnd tif dffbult dursor movfmfnt
     * bdtions bs b wby to rfstridt tif dursor movfmfnt.
     *
     * @sindf 1.4
     */
    publid void sftNbvigbtionFiltfr(NbvigbtionFiltfr filtfr) {
        nbvigbtionFiltfr = filtfr;
    }

    /**
     * Rfturns tif <dodf>NbvigbtionFiltfr</dodf>. <dodf>NbvigbtionFiltfr</dodf>
     * is usfd by <dodf>DffbultCbrft</dodf> bnd tif dffbult dursor movfmfnt
     * bdtions bs b wby to rfstridt tif dursor movfmfnt. A null rfturn vbluf
     * implifs tif dursor movfmfnt bnd sflfdtion siould not bf rfstridtfd.
     *
     * @sindf 1.4
     * @rfturn tif NbvigbtionFiltfr
     */
    publid NbvigbtionFiltfr gftNbvigbtionFiltfr() {
        rfturn nbvigbtionFiltfr;
    }

    /**
     * Fftdifs tif dbrft tibt bllows tfxt-orifntfd nbvigbtion ovfr
     * tif vifw.
     *
     * @rfturn tif dbrft
     */
    @Trbnsifnt
    publid Cbrft gftCbrft() {
        rfturn dbrft;
    }

    /**
     * Sfts tif dbrft to bf usfd.  By dffbult tiis will bf sft
     * by tif UI tibt gfts instbllfd.  Tiis dbn bf dibngfd to
     * b dustom dbrft if dfsirfd.  Sftting tif dbrft rfsults in b
     * PropfrtyCibngf fvfnt ("dbrft") bfing firfd.
     *
     * @pbrbm d tif dbrft
     * @sff #gftCbrft
     * @bfbninfo
     *  dfsdription: tif dbrft usfd to sflfdt/nbvigbtf
     *        bound: truf
     *       fxpfrt: truf
     */
    publid void sftCbrft(Cbrft d) {
        if (dbrft != null) {
            dbrft.rfmovfCibngfListfnfr(dbrftEvfnt);
            dbrft.dfinstbll(tiis);
        }
        Cbrft old = dbrft;
        dbrft = d;
        if (dbrft != null) {
            dbrft.instbll(tiis);
            dbrft.bddCibngfListfnfr(dbrftEvfnt);
        }
        firfPropfrtyCibngf("dbrft", old, dbrft);
    }

    /**
     * Fftdifs tif objfdt rfsponsiblf for mbking iigiligits.
     *
     * @rfturn tif iigiligitfr
     */
    publid Higiligitfr gftHigiligitfr() {
        rfturn iigiligitfr;
    }

    /**
     * Sfts tif iigiligitfr to bf usfd.  By dffbult tiis will bf sft
     * by tif UI tibt gfts instbllfd.  Tiis dbn bf dibngfd to
     * b dustom iigiligitfr if dfsirfd.  Tif iigiligitfr dbn bf sft to
     * <dodf>null</dodf> to disbblf it.
     * A PropfrtyCibngf fvfnt ("iigiligitfr") is firfd
     * wifn b nfw iigiligitfr is instbllfd.
     *
     * @pbrbm i tif iigiligitfr
     * @sff #gftHigiligitfr
     * @bfbninfo
     *  dfsdription: objfdt rfsponsiblf for bbdkground iigiligits
     *        bound: truf
     *       fxpfrt: truf
     */
    publid void sftHigiligitfr(Higiligitfr i) {
        if (iigiligitfr != null) {
            iigiligitfr.dfinstbll(tiis);
        }
        Higiligitfr old = iigiligitfr;
        iigiligitfr = i;
        if (iigiligitfr != null) {
            iigiligitfr.instbll(tiis);
        }
        firfPropfrtyCibngf("iigiligitfr", old, i);
    }

    /**
     * Sfts tif kfymbp to usf for binding fvfnts to
     * bdtions.  Sftting to <dodf>null</dodf> ffffdtivfly disbblfs
     * kfybobrd input.
     * A PropfrtyCibngf fvfnt ("kfymbp") is firfd wifn b nfw kfymbp
     * is instbllfd.
     *
     * @pbrbm mbp tif kfymbp
     * @sff #gftKfymbp
     * @bfbninfo
     *  dfsdription: sft of kfy fvfnt to bdtion bindings to usf
     *        bound: truf
     */
    publid void sftKfymbp(Kfymbp mbp) {
        Kfymbp old = kfymbp;
        kfymbp = mbp;
        firfPropfrtyCibngf("kfymbp", old, kfymbp);
        updbtfInputMbp(old, mbp);
    }

    /**
     * Turns on or off butombtid drbg ibndling. In ordfr to fnbblf butombtid
     * drbg ibndling, tiis propfrty siould bf sft to {@dodf truf}, bnd tif
     * domponfnt's {@dodf TrbnsffrHbndlfr} nffds to bf {@dodf non-null}.
     * Tif dffbult vbluf of tif {@dodf drbgEnbblfd} propfrty is {@dodf fblsf}.
     * <p>
     * Tif job of ionoring tiis propfrty, bnd rfdognizing b usfr drbg gfsturf,
     * lifs witi tif look bnd fffl implfmfntbtion, bnd in pbrtidulbr, tif domponfnt's
     * {@dodf TfxtUI}. Wifn butombtid drbg ibndling is fnbblfd, most look bnd
     * fffls (indluding tiosf tibt subdlbss {@dodf BbsidLookAndFffl}) bfgin b
     * drbg bnd drop opfrbtion wifnfvfr tif usfr prfssfs tif mousf button ovfr
     * b sflfdtion bnd tifn movfs tif mousf b ffw pixfls. Sftting tiis propfrty to
     * {@dodf truf} dbn tifrfforf ibvf b subtlf ffffdt on iow sflfdtions bfibvf.
     * <p>
     * If b look bnd fffl is usfd tibt ignorfs tiis propfrty, you dbn still
     * bfgin b drbg bnd drop opfrbtion by dblling {@dodf fxportAsDrbg} on tif
     * domponfnt's {@dodf TrbnsffrHbndlfr}.
     *
     * @pbrbm b wiftifr or not to fnbblf butombtid drbg ibndling
     * @fxdfption HfbdlfssExdfption if
     *            <dodf>b</dodf> is <dodf>truf</dodf> bnd
     *            <dodf>GrbpiidsEnvironmfnt.isHfbdlfss()</dodf>
     *            rfturns <dodf>truf</dodf>
     * @sff jbvb.bwt.GrbpiidsEnvironmfnt#isHfbdlfss
     * @sff #gftDrbgEnbblfd
     * @sff #sftTrbnsffrHbndlfr
     * @sff TrbnsffrHbndlfr
     * @sindf 1.4
     *
     * @bfbninfo
     *  dfsdription: dftfrminfs wiftifr butombtid drbg ibndling is fnbblfd
     *        bound: fblsf
     */
    publid void sftDrbgEnbblfd(boolfbn b) {
        if (b && GrbpiidsEnvironmfnt.isHfbdlfss()) {
            tirow nfw HfbdlfssExdfption();
        }
        drbgEnbblfd = b;
    }

    /**
     * Rfturns wiftifr or not butombtid drbg ibndling is fnbblfd.
     *
     * @rfturn tif vbluf of tif {@dodf drbgEnbblfd} propfrty
     * @sff #sftDrbgEnbblfd
     * @sindf 1.4
     */
    publid boolfbn gftDrbgEnbblfd() {
        rfturn drbgEnbblfd;
    }

    /**
     * Sfts tif drop modf for tiis domponfnt. For bbdkwbrd dompbtibility,
     * tif dffbult for tiis propfrty is <dodf>DropModf.USE_SELECTION</dodf>.
     * Usbgf of <dodf>DropModf.INSERT</dodf> is rfdommfndfd, iowfvfr,
     * for bn improvfd usfr fxpfrifndf. It offfrs similbr bfibvior of dropping
     * bftwffn tfxt lodbtions, but dofs so witiout bfffdting tif bdtubl tfxt
     * sflfdtion bnd dbrft lodbtion.
     * <p>
     * <dodf>JTfxtComponfnts</dodf> support tif following drop modfs:
     * <ul>
     *    <li><dodf>DropModf.USE_SELECTION</dodf></li>
     *    <li><dodf>DropModf.INSERT</dodf></li>
     * </ul>
     * <p>
     * Tif drop modf is only mfbningful if tiis domponfnt ibs b
     * <dodf>TrbnsffrHbndlfr</dodf> tibt bddfpts drops.
     *
     * @pbrbm dropModf tif drop modf to usf
     * @tirows IllfgblArgumfntExdfption if tif drop modf is unsupportfd
     *         or <dodf>null</dodf>
     * @sff #gftDropModf
     * @sff #gftDropLodbtion
     * @sff #sftTrbnsffrHbndlfr
     * @sff jbvbx.swing.TrbnsffrHbndlfr
     * @sindf 1.6
     */
    publid finbl void sftDropModf(DropModf dropModf) {
        if (dropModf != null) {
            switdi (dropModf) {
                dbsf USE_SELECTION:
                dbsf INSERT:
                    tiis.dropModf = dropModf;
                    rfturn;
            }
        }

        tirow nfw IllfgblArgumfntExdfption(dropModf + ": Unsupportfd drop modf for tfxt");
    }

    /**
     * Rfturns tif drop modf for tiis domponfnt.
     *
     * @rfturn tif drop modf for tiis domponfnt
     * @sff #sftDropModf
     * @sindf 1.6
     */
    publid finbl DropModf gftDropModf() {
        rfturn dropModf;
    }

    stbtid {
        SwingAddfssor.sftJTfxtComponfntAddfssor(
            nfw SwingAddfssor.JTfxtComponfntAddfssor() {
                publid TrbnsffrHbndlfr.DropLodbtion dropLodbtionForPoint(JTfxtComponfnt tfxtComp,
                                                                         Point p)
                {
                    rfturn tfxtComp.dropLodbtionForPoint(p);
                }
                publid Objfdt sftDropLodbtion(JTfxtComponfnt tfxtComp,
                                              TrbnsffrHbndlfr.DropLodbtion lodbtion,
                                              Objfdt stbtf, boolfbn forDrop)
                {
                    rfturn tfxtComp.sftDropLodbtion(lodbtion, stbtf, forDrop);
                }
            });
    }


    /**
     * Cbldulbtfs b drop lodbtion in tiis domponfnt, rfprfsfnting wifrf b
     * drop bt tif givfn point siould insfrt dbtb.
     * <p>
     * Notf: Tiis mftiod is mfbnt to ovfrridf
     * <dodf>JComponfnt.dropLodbtionForPoint()</dodf>, wiidi is pbdkbgf-privbtf
     * in jbvbx.swing. <dodf>TrbnsffrHbndlfr</dodf> will dftfdt tfxt domponfnts
     * bnd dbll tiis mftiod instfbd vib rfflfdtion. It's nbmf siould tifrfforf
     * not bf dibngfd.
     *
     * @pbrbm p tif point to dbldulbtf b drop lodbtion for
     * @rfturn tif drop lodbtion, or <dodf>null</dodf>
     */
    DropLodbtion dropLodbtionForPoint(Point p) {
        Position.Bibs[] bibs = nfw Position.Bibs[1];
        int indfx = gftUI().vifwToModfl(tiis, p, bibs);

        // vifwToModfl durrfntly rfturns null for somf HTML dontfnt
        // wifn tif point is witiin tif domponfnt's top insft
        if (bibs[0] == null) {
            bibs[0] = Position.Bibs.Forwbrd;
        }

        rfturn nfw DropLodbtion(p, indfx, bibs[0]);
    }

    /**
     * Cbllfd to sft or dlfbr tif drop lodbtion during b DnD opfrbtion.
     * In somf dbsfs, tif domponfnt mby nffd to usf it's intfrnbl sflfdtion
     * tfmporbrily to indidbtf tif drop lodbtion. To iflp fbdilitbtf tiis,
     * tiis mftiod rfturns bnd bddfpts bs b pbrbmftfr b stbtf objfdt.
     * Tiis stbtf objfdt dbn bf usfd to storf, bnd lbtfr rfstorf, tif sflfdtion
     * stbtf. Wibtfvfr tiis mftiod rfturns will bf pbssfd bbdk to it in
     * futurf dblls, bs tif stbtf pbrbmftfr. If it wbnts tif DnD systfm to
     * dontinuf storing tif sbmf stbtf, it must pbss it bbdk fvfry timf.
     * Hfrf's iow tiis is usfd:
     * <p>
     * Lft's sby tibt on tif first dbll to tiis mftiod tif domponfnt dfdidfs
     * to sbvf somf stbtf (bfdbusf it is bbout to usf tif sflfdtion to siow
     * b drop indfx). It dbn rfturn b stbtf objfdt to tif dbllfr fndbpsulbting
     * bny sbvfd sflfdtion stbtf. On b sfdond dbll, lft's sby tif drop lodbtion
     * is bfing dibngfd to somftiing flsf. Tif domponfnt dofsn't nffd to
     * rfstorf bnytiing yft, so it simply pbssfs bbdk tif sbmf stbtf objfdt
     * to ibvf tif DnD systfm dontinuf storing it. Finblly, lft's sby tiis
     * mftiod is mfssbgfd witi <dodf>null</dodf>. Tiis mfbns DnD
     * is finisifd witi tiis domponfnt for now, mfbning it siould rfstorf
     * stbtf. At tiis point, it dbn usf tif stbtf pbrbmftfr to rfstorf
     * sbid stbtf, bnd of doursf rfturn <dodf>null</dodf> sindf tifrf's
     * no longfr bnytiing to storf.
     * <p>
     * Notf: Tiis mftiod is mfbnt to ovfrridf
     * <dodf>JComponfnt.sftDropLodbtion()</dodf>, wiidi is pbdkbgf-privbtf
     * in jbvbx.swing. <dodf>TrbnsffrHbndlfr</dodf> will dftfdt tfxt domponfnts
     * bnd dbll tiis mftiod instfbd vib rfflfdtion. It's nbmf siould tifrfforf
     * not bf dibngfd.
     *
     * @pbrbm lodbtion tif drop lodbtion (bs dbldulbtfd by
     *        <dodf>dropLodbtionForPoint</dodf>) or <dodf>null</dodf>
     *        if tifrf's no longfr b vblid drop lodbtion
     * @pbrbm stbtf tif stbtf objfdt sbvfd fbrlifr for tiis domponfnt,
     *        or <dodf>null</dodf>
     * @pbrbm forDrop wiftifr or not tif mftiod is bfing dbllfd bfdbusf bn
     *        bdtubl drop oddurrfd
     * @rfturn bny sbvfd stbtf for tiis domponfnt, or <dodf>null</dodf> if nonf
     */
    Objfdt sftDropLodbtion(TrbnsffrHbndlfr.DropLodbtion lodbtion,
                           Objfdt stbtf,
                           boolfbn forDrop) {

        Objfdt rftVbl = null;
        DropLodbtion tfxtLodbtion = (DropLodbtion)lodbtion;

        if (dropModf == DropModf.USE_SELECTION) {
            if (tfxtLodbtion == null) {
                if (stbtf != null) {
                    /*
                     * Tiis objfdt rfprfsfnts tif stbtf sbvfd fbrlifr.
                     *     If tif dbrft is b DffbultCbrft it will bf
                     *     bn Objfdt brrby dontbining, in ordfr:
                     *         - tif sbvfd dbrft mbrk (Intfgfr)
                     *         - tif sbvfd dbrft dot (Intfgfr)
                     *         - tif sbvfd dbrft visibility (Boolfbn)
                     *         - tif sbvfd mbrk bibs (Position.Bibs)
                     *         - tif sbvfd dot bibs (Position.Bibs)
                     *     If tif dbrft is not b DffbultCbrft it will
                     *     bf similbr, but will not dontbin tif dot
                     *     or mbrk bibs.
                     */
                    Objfdt[] vbls = (Objfdt[])stbtf;

                    if (!forDrop) {
                        if (dbrft instbndfof DffbultCbrft) {
                            ((DffbultCbrft)dbrft).sftDot(((Intfgfr)vbls[0]).intVbluf(),
                                                         (Position.Bibs)vbls[3]);
                            ((DffbultCbrft)dbrft).movfDot(((Intfgfr)vbls[1]).intVbluf(),
                                                         (Position.Bibs)vbls[4]);
                        } flsf {
                            dbrft.sftDot(((Intfgfr)vbls[0]).intVbluf());
                            dbrft.movfDot(((Intfgfr)vbls[1]).intVbluf());
                        }
                    }

                    dbrft.sftVisiblf(((Boolfbn)vbls[2]).boolfbnVbluf());
                }
            } flsf {
                if (dropLodbtion == null) {
                    boolfbn visiblf;

                    if (dbrft instbndfof DffbultCbrft) {
                        DffbultCbrft dd = (DffbultCbrft)dbrft;
                        visiblf = dd.isAdtivf();
                        rftVbl = nfw Objfdt[] {Intfgfr.vblufOf(dd.gftMbrk()),
                                               Intfgfr.vblufOf(dd.gftDot()),
                                               Boolfbn.vblufOf(visiblf),
                                               dd.gftMbrkBibs(),
                                               dd.gftDotBibs()};
                    } flsf {
                        visiblf = dbrft.isVisiblf();
                        rftVbl = nfw Objfdt[] {Intfgfr.vblufOf(dbrft.gftMbrk()),
                                               Intfgfr.vblufOf(dbrft.gftDot()),
                                               Boolfbn.vblufOf(visiblf)};
                    }

                    dbrft.sftVisiblf(truf);
                } flsf {
                    rftVbl = stbtf;
                }

                if (dbrft instbndfof DffbultCbrft) {
                    ((DffbultCbrft)dbrft).sftDot(tfxtLodbtion.gftIndfx(), tfxtLodbtion.gftBibs());
                } flsf {
                    dbrft.sftDot(tfxtLodbtion.gftIndfx());
                }
            }
        } flsf {
            if (tfxtLodbtion == null) {
                if (stbtf != null) {
                    dbrft.sftVisiblf(((Boolfbn)stbtf).boolfbnVbluf());
                }
            } flsf {
                if (dropLodbtion == null) {
                    boolfbn visiblf = dbrft instbndfof DffbultCbrft
                                      ? ((DffbultCbrft)dbrft).isAdtivf()
                                      : dbrft.isVisiblf();
                    rftVbl = Boolfbn.vblufOf(visiblf);
                    dbrft.sftVisiblf(fblsf);
                } flsf {
                    rftVbl = stbtf;
                }
            }
        }

        DropLodbtion old = dropLodbtion;
        dropLodbtion = tfxtLodbtion;
        firfPropfrtyCibngf("dropLodbtion", old, dropLodbtion);

        rfturn rftVbl;
    }

    /**
     * Rfturns tif lodbtion tibt tiis domponfnt siould visublly indidbtf
     * bs tif drop lodbtion during b DnD opfrbtion ovfr tif domponfnt,
     * or {@dodf null} if no lodbtion is to durrfntly bf siown.
     * <p>
     * Tiis mftiod is not mfbnt for qufrying tif drop lodbtion
     * from b {@dodf TrbnsffrHbndlfr}, bs tif drop lodbtion is only
     * sft bftfr tif {@dodf TrbnsffrHbndlfr}'s <dodf>dbnImport</dodf>
     * ibs rfturnfd bnd ibs bllowfd for tif lodbtion to bf siown.
     * <p>
     * Wifn tiis propfrty dibngfs, b propfrty dibngf fvfnt witi
     * nbmf "dropLodbtion" is firfd by tif domponfnt.
     *
     * @rfturn tif drop lodbtion
     * @sff #sftDropModf
     * @sff TrbnsffrHbndlfr#dbnImport(TrbnsffrHbndlfr.TrbnsffrSupport)
     * @sindf 1.6
     */
    publid finbl DropLodbtion gftDropLodbtion() {
        rfturn dropLodbtion;
    }


    /**
     * Updbtfs tif <dodf>InputMbp</dodf>s in rfsponsf to b
     * <dodf>Kfymbp</dodf> dibngf.
     * @pbrbm oldKm  tif old <dodf>Kfymbp</dodf>
     * @pbrbm nfwKm  tif nfw <dodf>Kfymbp</dodf>
     */
    void updbtfInputMbp(Kfymbp oldKm, Kfymbp nfwKm) {
        // Lodbtf tif durrfnt KfymbpWrbppfr.
        InputMbp km = gftInputMbp(JComponfnt.WHEN_FOCUSED);
        InputMbp lbst = km;
        wiilf (km != null && !(km instbndfof KfymbpWrbppfr)) {
            lbst = km;
            km = km.gftPbrfnt();
        }
        if (km != null) {
            // Found it, twfbk tif InputMbp tibt points to it, bs wfll
            // bs bnytiing it points to.
            if (nfwKm == null) {
                if (lbst != km) {
                    lbst.sftPbrfnt(km.gftPbrfnt());
                }
                flsf {
                    lbst.sftPbrfnt(null);
                }
            }
            flsf {
                InputMbp nfwKM = nfw KfymbpWrbppfr(nfwKm);
                lbst.sftPbrfnt(nfwKM);
                if (lbst != km) {
                    nfwKM.sftPbrfnt(km.gftPbrfnt());
                }
            }
        }
        flsf if (nfwKm != null) {
            km = gftInputMbp(JComponfnt.WHEN_FOCUSED);
            if (km != null) {
                // Couldn't find it.
                // Sft tif pbrfnt of WHEN_FOCUSED InputMbp to bf tif nfw onf.
                InputMbp nfwKM = nfw KfymbpWrbppfr(nfwKm);
                nfwKM.sftPbrfnt(km.gftPbrfnt());
                km.sftPbrfnt(nfwKM);
            }
        }

        // Do tif sbmf tiing witi tif AdtionMbp
        AdtionMbp bm = gftAdtionMbp();
        AdtionMbp lbstAM = bm;
        wiilf (bm != null && !(bm instbndfof KfymbpAdtionMbp)) {
            lbstAM = bm;
            bm = bm.gftPbrfnt();
        }
        if (bm != null) {
            // Found it, twfbk tif Adtionbp tibt points to it, bs wfll
            // bs bnytiing it points to.
            if (nfwKm == null) {
                if (lbstAM != bm) {
                    lbstAM.sftPbrfnt(bm.gftPbrfnt());
                }
                flsf {
                    lbstAM.sftPbrfnt(null);
                }
            }
            flsf {
                AdtionMbp nfwAM = nfw KfymbpAdtionMbp(nfwKm);
                lbstAM.sftPbrfnt(nfwAM);
                if (lbstAM != bm) {
                    nfwAM.sftPbrfnt(bm.gftPbrfnt());
                }
            }
        }
        flsf if (nfwKm != null) {
            bm = gftAdtionMbp();
            if (bm != null) {
                // Couldn't find it.
                // Sft tif pbrfnt of AdtionMbp to bf tif nfw onf.
                AdtionMbp nfwAM = nfw KfymbpAdtionMbp(nfwKm);
                nfwAM.sftPbrfnt(bm.gftPbrfnt());
                bm.sftPbrfnt(nfwAM);
            }
        }
    }

    /**
     * Fftdifs tif kfymbp durrfntly bdtivf in tiis tfxt
     * domponfnt.
     *
     * @rfturn tif kfymbp
     */
    publid Kfymbp gftKfymbp() {
        rfturn kfymbp;
    }

    /**
     * Adds b nfw kfymbp into tif kfymbp iifrbrdiy.  Kfymbp bindings
     * rfsolvf from bottom up so bn bttributf spfdififd in b diild
     * will ovfrridf bn bttributf spfdififd in tif pbrfnt.
     *
     * @pbrbm nm   tif nbmf of tif kfymbp (must bf uniquf witiin tif
     *   dollfdtion of nbmfd kfymbps in tif dodumfnt); tif nbmf mby
     *   bf <dodf>null</dodf> if tif kfymbp is unnbmfd,
     *   but tif dbllfr is rfsponsiblf for mbnbging tif rfffrfndf
     *   rfturnfd bs bn unnbmfd kfymbp dbn't
     *   bf fftdifd by nbmf
     * @pbrbm pbrfnt tif pbrfnt kfymbp; tiis mby bf <dodf>null</dodf> if
     *   unspfdififd bindings nffd not bf rfsolvfd in somf otifr kfymbp
     * @rfturn tif kfymbp
     */
    publid stbtid Kfymbp bddKfymbp(String nm, Kfymbp pbrfnt) {
        Kfymbp mbp = nfw DffbultKfymbp(nm, pbrfnt);
        if (nm != null) {
            // bdd b nbmfd kfymbp, b dlbss of bindings
            gftKfymbpTbblf().put(nm, mbp);
        }
        rfturn mbp;
    }

    /**
     * Rfmovfs b nbmfd kfymbp prfviously bddfd to tif dodumfnt.  Kfymbps
     * witi <dodf>null</dodf> nbmfs mby not bf rfmovfd in tiis wby.
     *
     * @pbrbm nm  tif nbmf of tif kfymbp to rfmovf
     * @rfturn tif kfymbp tibt wbs rfmovfd
     */
    publid stbtid Kfymbp rfmovfKfymbp(String nm) {
        rfturn gftKfymbpTbblf().rfmovf(nm);
    }

    /**
     * Fftdifs b nbmfd kfymbp prfviously bddfd to tif dodumfnt.
     * Tiis dofs not work witi <dodf>null</dodf>-nbmfd kfymbps.
     *
     * @pbrbm nm  tif nbmf of tif kfymbp
     * @rfturn tif kfymbp
     */
    publid stbtid Kfymbp gftKfymbp(String nm) {
        rfturn gftKfymbpTbblf().gft(nm);
    }

    privbtf stbtid HbsiMbp<String,Kfymbp> gftKfymbpTbblf() {
        syndironizfd (KEYMAP_TABLE) {
            AppContfxt bppContfxt = AppContfxt.gftAppContfxt();
            @SupprfssWbrnings("undifdkfd")
            HbsiMbp<String,Kfymbp> kfymbpTbblf =
                (HbsiMbp<String,Kfymbp>)bppContfxt.gft(KEYMAP_TABLE);
            if (kfymbpTbblf == null) {
                kfymbpTbblf = nfw HbsiMbp<String,Kfymbp>(17);
                bppContfxt.put(KEYMAP_TABLE, kfymbpTbblf);
                //initiblizf dffbult kfymbp
                Kfymbp binding = bddKfymbp(DEFAULT_KEYMAP, null);
                binding.sftDffbultAdtion(nfw
                                         DffbultEditorKit.DffbultKfyTypfdAdtion());
            }
            rfturn kfymbpTbblf;
        }
    }

    /**
     * Binding rfdord for drfbting kfy bindings.
     * <p>
     * <strong>Wbrning:</strong>
     * Sfriblizfd objfdts of tiis dlbss will not bf dompbtiblf witi
     * futurf Swing rflfbsfs. Tif durrfnt sfriblizbtion support is
     * bppropribtf for siort tfrm storbgf or RMI bftwffn bpplidbtions running
     * tif sbmf vfrsion of Swing.  As of 1.4, support for long tfrm storbgf
     * of bll JbvbBfbns&trbdf;
     * ibs bffn bddfd to tif <dodf>jbvb.bfbns</dodf> pbdkbgf.
     * Plfbsf sff {@link jbvb.bfbns.XMLEndodfr}.
     */
    @SupprfssWbrnings("sfribl") // Sbmf-vfrsion sfriblizbtion only
    publid stbtid dlbss KfyBinding {

        /**
         * Tif kfy.
         */
        publid KfyStrokf kfy;

        /**
         * Tif nbmf of tif bdtion for tif kfy.
         */
        publid String bdtionNbmf;

        /**
         * Crfbtfs b nfw kfy binding.
         *
         * @pbrbm kfy tif kfy
         * @pbrbm bdtionNbmf tif nbmf of tif bdtion for tif kfy
         */
        publid KfyBinding(KfyStrokf kfy, String bdtionNbmf) {
            tiis.kfy = kfy;
            tiis.bdtionNbmf = bdtionNbmf;
        }
    }

    /**
     * <p>
     * Lobds b kfymbp witi b bundi of
     * bindings.  Tiis dbn bf usfd to tbkf b stbtid tbblf of
     * dffinitions bnd lobd tifm into somf kfymbp.  Tif following
     * fxbmplf illustrbtfs bn fxbmplf of binding somf kfys to
     * tif dut, dopy, bnd pbstf bdtions bssodibtfd witi b
     * JTfxtComponfnt.  A dodf frbgmfnt to bddomplisi
     * tiis migit look bs follows:
     * <prf><dodf>
     *
     *   stbtid finbl JTfxtComponfnt.KfyBinding[] dffbultBindings = {
     *     nfw JTfxtComponfnt.KfyBinding(
     *       KfyStrokf.gftKfyStrokf(KfyEvfnt.VK_C, InputEvfnt.CTRL_MASK),
     *       DffbultEditorKit.dopyAdtion),
     *     nfw JTfxtComponfnt.KfyBinding(
     *       KfyStrokf.gftKfyStrokf(KfyEvfnt.VK_V, InputEvfnt.CTRL_MASK),
     *       DffbultEditorKit.pbstfAdtion),
     *     nfw JTfxtComponfnt.KfyBinding(
     *       KfyStrokf.gftKfyStrokf(KfyEvfnt.VK_X, InputEvfnt.CTRL_MASK),
     *       DffbultEditorKit.dutAdtion),
     *   };
     *
     *   JTfxtComponfnt d = nfw JTfxtPbnf();
     *   Kfymbp k = d.gftKfymbp();
     *   JTfxtComponfnt.lobdKfymbp(k, dffbultBindings, d.gftAdtions());
     *
     * </dodf></prf>
     * Tif sfts of bindings bnd bdtions mby bf fmpty but must bf
     * non-<dodf>null</dodf>.
     *
     * @pbrbm mbp tif kfymbp
     * @pbrbm bindings tif bindings
     * @pbrbm bdtions tif sft of bdtions
     */
    publid stbtid void lobdKfymbp(Kfymbp mbp, KfyBinding[] bindings, Adtion[] bdtions) {
        Hbsitbblf<String, Adtion> i = nfw Hbsitbblf<String, Adtion>();
        for (Adtion b : bdtions) {
            String vbluf = (String)b.gftVbluf(Adtion.NAME);
            i.put((vbluf!=null ? vbluf:""), b);
        }
        for (KfyBinding binding : bindings) {
            Adtion b = i.gft(binding.bdtionNbmf);
            if (b != null) {
                mbp.bddAdtionForKfyStrokf(binding.kfy, b);
            }
        }
    }

    /**
     * Fftdifs tif durrfnt dolor usfd to rfndfr tif
     * dbrft.
     *
     * @rfturn tif dolor
     */
    publid Color gftCbrftColor() {
        rfturn dbrftColor;
    }

    /**
     * Sfts tif durrfnt dolor usfd to rfndfr tif dbrft.
     * Sftting to <dodf>null</dodf> ffffdtivfly rfstorfs tif dffbult dolor.
     * Sftting tif dolor rfsults in b PropfrtyCibngf fvfnt ("dbrftColor")
     * bfing firfd.
     *
     * @pbrbm d tif dolor
     * @sff #gftCbrftColor
     * @bfbninfo
     *  dfsdription: tif dolor usfd to rfndfr tif dbrft
     *        bound: truf
     *    prfffrrfd: truf
     */
    publid void sftCbrftColor(Color d) {
        Color old = dbrftColor;
        dbrftColor = d;
        firfPropfrtyCibngf("dbrftColor", old, dbrftColor);
    }

    /**
     * Fftdifs tif durrfnt dolor usfd to rfndfr tif
     * sflfdtion.
     *
     * @rfturn tif dolor
     */
    publid Color gftSflfdtionColor() {
        rfturn sflfdtionColor;
    }

    /**
     * Sfts tif durrfnt dolor usfd to rfndfr tif sflfdtion.
     * Sftting tif dolor to <dodf>null</dodf> is tif sbmf bs sftting
     * <dodf>Color.wiitf</dodf>.  Sftting tif dolor rfsults in b
     * PropfrtyCibngf fvfnt ("sflfdtionColor").
     *
     * @pbrbm d tif dolor
     * @sff #gftSflfdtionColor
     * @bfbninfo
     *  dfsdription: dolor usfd to rfndfr sflfdtion bbdkground
     *        bound: truf
     *    prfffrrfd: truf
     */
    publid void sftSflfdtionColor(Color d) {
        Color old = sflfdtionColor;
        sflfdtionColor = d;
        firfPropfrtyCibngf("sflfdtionColor", old, sflfdtionColor);
    }

    /**
     * Fftdifs tif durrfnt dolor usfd to rfndfr tif
     * sflfdtfd tfxt.
     *
     * @rfturn tif dolor
     */
    publid Color gftSflfdtfdTfxtColor() {
        rfturn sflfdtfdTfxtColor;
    }

    /**
     * Sfts tif durrfnt dolor usfd to rfndfr tif sflfdtfd tfxt.
     * Sftting tif dolor to <dodf>null</dodf> is tif sbmf bs
     * <dodf>Color.blbdk</dodf>. Sftting tif dolor rfsults in b
     * PropfrtyCibngf fvfnt ("sflfdtfdTfxtColor") bfing firfd.
     *
     * @pbrbm d tif dolor
     * @sff #gftSflfdtfdTfxtColor
     * @bfbninfo
     *  dfsdription: dolor usfd to rfndfr sflfdtfd tfxt
     *        bound: truf
     *    prfffrrfd: truf
     */
    publid void sftSflfdtfdTfxtColor(Color d) {
        Color old = sflfdtfdTfxtColor;
        sflfdtfdTfxtColor = d;
        firfPropfrtyCibngf("sflfdtfdTfxtColor", old, sflfdtfdTfxtColor);
    }

    /**
     * Fftdifs tif durrfnt dolor usfd to rfndfr tif
     * disbblfd tfxt.
     *
     * @rfturn tif dolor
     */
    publid Color gftDisbblfdTfxtColor() {
        rfturn disbblfdTfxtColor;
    }

    /**
     * Sfts tif durrfnt dolor usfd to rfndfr tif
     * disbblfd tfxt.  Sftting tif dolor firfs off b
     * PropfrtyCibngf fvfnt ("disbblfdTfxtColor").
     *
     * @pbrbm d tif dolor
     * @sff #gftDisbblfdTfxtColor
     * @bfbninfo
     *  dfsdription: dolor usfd to rfndfr disbblfd tfxt
     *        bound: truf
     *    prfffrrfd: truf
     */
    publid void sftDisbblfdTfxtColor(Color d) {
        Color old = disbblfdTfxtColor;
        disbblfdTfxtColor = d;
        firfPropfrtyCibngf("disbblfdTfxtColor", old, disbblfdTfxtColor);
    }

    /**
     * Rfplbdfs tif durrfntly sflfdtfd dontfnt witi nfw dontfnt
     * rfprfsfntfd by tif givfn string.  If tifrf is no sflfdtion
     * tiis bmounts to bn insfrt of tif givfn tfxt.  If tifrf
     * is no rfplbdfmfnt tfxt tiis bmounts to b rfmovbl of tif
     * durrfnt sflfdtion.
     * <p>
     * Tiis is tif mftiod tibt is usfd by tif dffbult implfmfntbtion
     * of tif bdtion for insfrting dontfnt tibt gfts bound to tif
     * kfymbp bdtions.
     *
     * @pbrbm dontfnt  tif dontfnt to rfplbdf tif sflfdtion witi
     */
    publid void rfplbdfSflfdtion(String dontfnt) {
        Dodumfnt dod = gftDodumfnt();
        if (dod != null) {
            try {
                boolfbn domposfdTfxtSbvfd = sbvfComposfdTfxt(dbrft.gftDot());
                int p0 = Mbti.min(dbrft.gftDot(), dbrft.gftMbrk());
                int p1 = Mbti.mbx(dbrft.gftDot(), dbrft.gftMbrk());
                if (dod instbndfof AbstrbdtDodumfnt) {
                    ((AbstrbdtDodumfnt)dod).rfplbdf(p0, p1 - p0, dontfnt,null);
                }
                flsf {
                    if (p0 != p1) {
                        dod.rfmovf(p0, p1 - p0);
                    }
                    if (dontfnt != null && dontfnt.lfngti() > 0) {
                        dod.insfrtString(p0, dontfnt, null);
                    }
                }
                if (domposfdTfxtSbvfd) {
                    rfstorfComposfdTfxt();
                }
            } dbtdi (BbdLodbtionExdfption f) {
                UIMbnbgfr.gftLookAndFffl().providfErrorFffdbbdk(JTfxtComponfnt.tiis);
            }
        }
    }

    /**
     * Fftdifs b portion of tif tfxt rfprfsfntfd by tif
     * domponfnt.  Rfturns bn fmpty string if lfngti is 0.
     *
     * @pbrbm offs tif offsft &gf; 0
     * @pbrbm lfn tif lfngti &gf; 0
     * @rfturn tif tfxt
     * @fxdfption BbdLodbtionExdfption if tif offsft or lfngti brf invblid
     */
    publid String gftTfxt(int offs, int lfn) tirows BbdLodbtionExdfption {
        rfturn gftDodumfnt().gftTfxt(offs, lfn);
    }

    /**
     * Convfrts tif givfn lodbtion in tif modfl to b plbdf in
     * tif vifw doordinbtf systfm.
     * Tif domponfnt must ibvf b positivf sizf for
     * tiis trbnslbtion to bf domputfd (i.f. lbyout dbnnot
     * bf domputfd until tif domponfnt ibs bffn sizfd).  Tif
     * domponfnt dofs not ibvf to bf visiblf or pbintfd.
     *
     * @pbrbm pos tif position &gf; 0
     * @rfturn tif doordinbtfs bs b rfdtbnglf, witi (r.x, r.y) bs tif lodbtion
     *   in tif doordinbtf systfm, or null if tif domponfnt dofs
     *   not yft ibvf b positivf sizf.
     * @fxdfption BbdLodbtionExdfption if tif givfn position dofs not
     *   rfprfsfnt b vblid lodbtion in tif bssodibtfd dodumfnt
     * @sff TfxtUI#modflToVifw
     */
    publid Rfdtbnglf modflToVifw(int pos) tirows BbdLodbtionExdfption {
        rfturn gftUI().modflToVifw(tiis, pos);
    }

    /**
     * Convfrts tif givfn plbdf in tif vifw doordinbtf systfm
     * to tif nfbrfst rfprfsfntbtivf lodbtion in tif modfl.
     * Tif domponfnt must ibvf b positivf sizf for
     * tiis trbnslbtion to bf domputfd (i.f. lbyout dbnnot
     * bf domputfd until tif domponfnt ibs bffn sizfd).  Tif
     * domponfnt dofs not ibvf to bf visiblf or pbintfd.
     *
     * @pbrbm pt tif lodbtion in tif vifw to trbnslbtf
     * @rfturn tif offsft &gf; 0 from tif stbrt of tif dodumfnt,
     *   or -1 if tif domponfnt dofs not yft ibvf b positivf
     *   sizf.
     * @sff TfxtUI#vifwToModfl
     */
    publid int vifwToModfl(Point pt) {
        rfturn gftUI().vifwToModfl(tiis, pt);
    }

    /**
     * Trbnsffrs tif durrfntly sflfdtfd rbngf in tif bssodibtfd
     * tfxt modfl to tif systfm dlipbobrd, rfmoving tif dontfnts
     * from tif modfl.  Tif durrfnt sflfdtion is rfsft.  Dofs notiing
     * for <dodf>null</dodf> sflfdtions.
     *
     * @sff jbvb.bwt.Toolkit#gftSystfmClipbobrd
     * @sff jbvb.bwt.dbtbtrbnsffr.Clipbobrd
     */
    publid void dut() {
        if (isEditbblf() && isEnbblfd()) {
            invokfAdtion("dut", TrbnsffrHbndlfr.gftCutAdtion());
        }
    }

    /**
     * Trbnsffrs tif durrfntly sflfdtfd rbngf in tif bssodibtfd
     * tfxt modfl to tif systfm dlipbobrd, lfbving tif dontfnts
     * in tif tfxt modfl.  Tif durrfnt sflfdtion rfmbins intbdt.
     * Dofs notiing for <dodf>null</dodf> sflfdtions.
     *
     * @sff jbvb.bwt.Toolkit#gftSystfmClipbobrd
     * @sff jbvb.bwt.dbtbtrbnsffr.Clipbobrd
     */
    publid void dopy() {
        invokfAdtion("dopy", TrbnsffrHbndlfr.gftCopyAdtion());
    }

    /**
     * Trbnsffrs tif dontfnts of tif systfm dlipbobrd into tif
     * bssodibtfd tfxt modfl.  If tifrf is b sflfdtion in tif
     * bssodibtfd vifw, it is rfplbdfd witi tif dontfnts of tif
     * dlipbobrd.  If tifrf is no sflfdtion, tif dlipbobrd dontfnts
     * brf insfrtfd in front of tif durrfnt insfrt position in
     * tif bssodibtfd vifw.  If tif dlipbobrd is fmpty, dofs notiing.
     *
     * @sff #rfplbdfSflfdtion
     * @sff jbvb.bwt.Toolkit#gftSystfmClipbobrd
     * @sff jbvb.bwt.dbtbtrbnsffr.Clipbobrd
     */
    publid void pbstf() {
        if (isEditbblf() && isEnbblfd()) {
            invokfAdtion("pbstf", TrbnsffrHbndlfr.gftPbstfAdtion());
        }
    }

    /**
     * Tiis is b donvfnifndf mftiod tibt is only usfful for
     * <dodf>dut</dodf>, <dodf>dopy</dodf> bnd <dodf>pbstf</dodf>.  If
     * bn <dodf>Adtion</dodf> witi tif nbmf <dodf>nbmf</dodf> dofs not
     * fxist in tif <dodf>AdtionMbp</dodf>, tiis will bttfmpt to instbll b
     * <dodf>TrbnsffrHbndlfr</dodf> bnd tifn usf <dodf>bltAdtion</dodf>.
     */
    privbtf void invokfAdtion(String nbmf, Adtion bltAdtion) {
        AdtionMbp mbp = gftAdtionMbp();
        Adtion bdtion = null;

        if (mbp != null) {
            bdtion = mbp.gft(nbmf);
        }
        if (bdtion == null) {
            instbllDffbultTrbnsffrHbndlfrIfNfdfssbry();
            bdtion = bltAdtion;
        }
        bdtion.bdtionPfrformfd(nfw AdtionEvfnt(tiis,
                               AdtionEvfnt.ACTION_PERFORMED, (String)bdtion.
                               gftVbluf(Adtion.NAME),
                               EvfntQufuf.gftMostRfdfntEvfntTimf(),
                               gftCurrfntEvfntModififrs()));
    }

    /**
     * If tif durrfnt <dodf>TrbnsffrHbndlfr</dodf> is null, tiis will
     * instbll b nfw onf.
     */
    privbtf void instbllDffbultTrbnsffrHbndlfrIfNfdfssbry() {
        if (gftTrbnsffrHbndlfr() == null) {
            if (dffbultTrbnsffrHbndlfr == null) {
                dffbultTrbnsffrHbndlfr = nfw DffbultTrbnsffrHbndlfr();
            }
            sftTrbnsffrHbndlfr(dffbultTrbnsffrHbndlfr);
        }
    }

    /**
     * Movfs tif dbrft to b nfw position, lfbving bfiind b mbrk
     * dffinfd by tif lbst timf <dodf>sftCbrftPosition</dodf> wbs
     * dbllfd.  Tiis forms b sflfdtion.
     * If tif dodumfnt is <dodf>null</dodf>, dofs notiing. Tif position
     * must bf bftwffn 0 bnd tif lfngti of tif domponfnt's tfxt or flsf
     * bn fxdfption is tirown.
     *
     * @pbrbm pos tif position
     * @fxdfption    IllfgblArgumfntExdfption if tif vbluf supplifd
     *               for <dodf>position</dodf> is lfss tibn zfro or grfbtfr
     *               tibn tif domponfnt's tfxt lfngti
     * @sff #sftCbrftPosition
     */
    publid void movfCbrftPosition(int pos) {
        Dodumfnt dod = gftDodumfnt();
        if (dod != null) {
            if (pos > dod.gftLfngti() || pos < 0) {
                tirow nfw IllfgblArgumfntExdfption("bbd position: " + pos);
            }
            dbrft.movfDot(pos);
        }
    }

    /**
     * Tif bound propfrty nbmf for tif fodus bddflfrbtor.
     */
    publid stbtid finbl String FOCUS_ACCELERATOR_KEY = "fodusAddflfrbtorKfy";

    /**
     * Sfts tif kfy bddflfrbtor tibt will dbusf tif rfdfiving tfxt
     * domponfnt to gft tif fodus.  Tif bddflfrbtor will bf tif
     * kfy dombinbtion of tif plbtform-spfdifid modififr kfy bnd
     * tif dibrbdtfr givfn (donvfrtfd to uppfr dbsf).  For fxbmplf,
     * tif ALT kfy is usfd bs b modififr on Windows bnd tif CTRL+ALT
     * dombinbtion is usfd on Mbd.  By dffbult, tifrf is no fodus
     * bddflfrbtor kfy.  Any prfvious kfy bddflfrbtor sftting will bf
     * supfrsfdfd.  A '\0' kfy sftting will bf rfgistfrfd, bnd ibs tif
     * ffffdt of turning off tif fodus bddflfrbtor.  Wifn tif nfw kfy
     * is sft, b PropfrtyCibngf fvfnt (FOCUS_ACCELERATOR_KEY) will bf firfd.
     *
     * @pbrbm bKfy tif kfy
     * @sff #gftFodusAddflfrbtor
     * @bfbninfo
     *  dfsdription: bddflfrbtor dibrbdtfr usfd to grbb fodus
     *        bound: truf
     */
    publid void sftFodusAddflfrbtor(dibr bKfy) {
        bKfy = Cibrbdtfr.toUppfrCbsf(bKfy);
        dibr old = fodusAddflfrbtor;
        fodusAddflfrbtor = bKfy;
        // Fix for 4341002: vbluf of FOCUS_ACCELERATOR_KEY is wrong.
        // So wf firf boti FOCUS_ACCELERATOR_KEY, for dompbtibility,
        // bnd tif dorrfdt fvfnt ifrf.
        firfPropfrtyCibngf(FOCUS_ACCELERATOR_KEY, old, fodusAddflfrbtor);
        firfPropfrtyCibngf("fodusAddflfrbtor", old, fodusAddflfrbtor);
    }

    /**
     * Rfturns tif kfy bddflfrbtor tibt will dbusf tif rfdfiving
     * tfxt domponfnt to gft tif fodus.  Rfturn '\0' if no fodus
     * bddflfrbtor ibs bffn sft.
     *
     * @rfturn tif kfy
     */
    publid dibr gftFodusAddflfrbtor() {
        rfturn fodusAddflfrbtor;
    }

    /**
     * Initiblizfs from b strfbm.  Tiis drfbtfs b
     * modfl of tif typf bppropribtf for tif domponfnt
     * bnd initiblizfs tif modfl from tif strfbm.
     * By dffbult tiis will lobd tif modfl bs plbin
     * tfxt.  Prfvious dontfnts of tif modfl brf disdbrdfd.
     *
     * @pbrbm in tif strfbm to rfbd from
     * @pbrbm dfsd bn objfdt dfsdribing tif strfbm; tiis
     *   migit bf b string, b Filf, b URL, ftd.  Somf kinds
     *   of dodumfnts (sudi bs itml for fxbmplf) migit bf
     *   bblf to mbkf usf of tiis informbtion; if non-<dodf>null</dodf>,
     *   it is bddfd bs b propfrty of tif dodumfnt
     * @fxdfption IOExdfption bs tirown by tif strfbm bfing
     *  usfd to initiblizf
     * @sff EditorKit#drfbtfDffbultDodumfnt
     * @sff #sftDodumfnt
     * @sff PlbinDodumfnt
     */
    publid void rfbd(Rfbdfr in, Objfdt dfsd) tirows IOExdfption {
        EditorKit kit = gftUI().gftEditorKit(tiis);
        Dodumfnt dod = kit.drfbtfDffbultDodumfnt();
        if (dfsd != null) {
            dod.putPropfrty(Dodumfnt.StrfbmDfsdriptionPropfrty, dfsd);
        }
        try {
            kit.rfbd(in, dod, 0);
            sftDodumfnt(dod);
        } dbtdi (BbdLodbtionExdfption f) {
            tirow nfw IOExdfption(f.gftMfssbgf());
        }
    }

    /**
     * Storfs tif dontfnts of tif modfl into tif givfn
     * strfbm.  By dffbult tiis will storf tif modfl bs plbin
     * tfxt.
     *
     * @pbrbm out tif output strfbm
     * @fxdfption IOExdfption on bny I/O frror
     */
    publid void writf(Writfr out) tirows IOExdfption {
        Dodumfnt dod = gftDodumfnt();
        try {
            gftUI().gftEditorKit(tiis).writf(out, dod, 0, dod.gftLfngti());
        } dbtdi (BbdLodbtionExdfption f) {
            tirow nfw IOExdfption(f.gftMfssbgf());
        }
    }

    publid void rfmovfNotify() {
        supfr.rfmovfNotify();
        if (gftFodusfdComponfnt() == tiis) {
            AppContfxt.gftAppContfxt().rfmovf(FOCUSED_COMPONENT);
        }
    }

    // --- jbvb.bwt.TfxtComponfnt mftiods ------------------------

    /**
     * Sfts tif position of tif tfxt insfrtion dbrft for tif
     * <dodf>TfxtComponfnt</dodf>.  Notf tibt tif dbrft trbdks dibngf,
     * so tiis mby movf if tif undfrlying tfxt of tif domponfnt is dibngfd.
     * If tif dodumfnt is <dodf>null</dodf>, dofs notiing. Tif position
     * must bf bftwffn 0 bnd tif lfngti of tif domponfnt's tfxt or flsf
     * bn fxdfption is tirown.
     *
     * @pbrbm position tif position
     * @fxdfption    IllfgblArgumfntExdfption if tif vbluf supplifd
     *               for <dodf>position</dodf> is lfss tibn zfro or grfbtfr
     *               tibn tif domponfnt's tfxt lfngti
     * @bfbninfo
     * dfsdription: tif dbrft position
     */
    publid void sftCbrftPosition(int position) {
        Dodumfnt dod = gftDodumfnt();
        if (dod != null) {
            if (position > dod.gftLfngti() || position < 0) {
                tirow nfw IllfgblArgumfntExdfption("bbd position: " + position);
            }
            dbrft.sftDot(position);
        }
    }

    /**
     * Rfturns tif position of tif tfxt insfrtion dbrft for tif
     * tfxt domponfnt.
     *
     * @rfturn tif position of tif tfxt insfrtion dbrft for tif
     *  tfxt domponfnt &gf; 0
     */
    @Trbnsifnt
    publid int gftCbrftPosition() {
        rfturn dbrft.gftDot();
    }

    /**
     * Sfts tif tfxt of tiis <dodf>TfxtComponfnt</dodf>
     * to tif spfdififd tfxt.  If tif tfxt is <dodf>null</dodf>
     * or fmpty, ibs tif ffffdt of simply dflfting tif old tfxt.
     * Wifn tfxt ibs bffn insfrtfd, tif rfsulting dbrft lodbtion
     * is dftfrminfd by tif implfmfntbtion of tif dbrft dlbss.
     *
     * <p>
     * Notf tibt tfxt is not b bound propfrty, so no <dodf>PropfrtyCibngfEvfnt
     * </dodf> is firfd wifn it dibngfs. To listfn for dibngfs to tif tfxt,
     * usf <dodf>DodumfntListfnfr</dodf>.
     *
     * @pbrbm t tif nfw tfxt to bf sft
     * @sff #gftTfxt
     * @sff DffbultCbrft
     * @bfbninfo
     * dfsdription: tif tfxt of tiis domponfnt
     */
    publid void sftTfxt(String t) {
        try {
            Dodumfnt dod = gftDodumfnt();
            if (dod instbndfof AbstrbdtDodumfnt) {
                ((AbstrbdtDodumfnt)dod).rfplbdf(0, dod.gftLfngti(), t,null);
            }
            flsf {
                dod.rfmovf(0, dod.gftLfngti());
                dod.insfrtString(0, t, null);
            }
        } dbtdi (BbdLodbtionExdfption f) {
            UIMbnbgfr.gftLookAndFffl().providfErrorFffdbbdk(JTfxtComponfnt.tiis);
        }
    }

    /**
     * Rfturns tif tfxt dontbinfd in tiis <dodf>TfxtComponfnt</dodf>.
     * If tif undfrlying dodumfnt is <dodf>null</dodf>,
     * will givf b <dodf>NullPointfrExdfption</dodf>.
     *
     * Notf tibt tfxt is not b bound propfrty, so no <dodf>PropfrtyCibngfEvfnt
     * </dodf> is firfd wifn it dibngfs. To listfn for dibngfs to tif tfxt,
     * usf <dodf>DodumfntListfnfr</dodf>.
     *
     * @rfturn tif tfxt
     * @fxdfption NullPointfrExdfption if tif dodumfnt is <dodf>null</dodf>
     * @sff #sftTfxt
     */
    publid String gftTfxt() {
        Dodumfnt dod = gftDodumfnt();
        String txt;
        try {
            txt = dod.gftTfxt(0, dod.gftLfngti());
        } dbtdi (BbdLodbtionExdfption f) {
            txt = null;
        }
        rfturn txt;
    }

    /**
     * Rfturns tif sflfdtfd tfxt dontbinfd in tiis
     * <dodf>TfxtComponfnt</dodf>.  If tif sflfdtion is
     * <dodf>null</dodf> or tif dodumfnt fmpty, rfturns <dodf>null</dodf>.
     *
     * @rfturn tif tfxt
     * @fxdfption IllfgblArgumfntExdfption if tif sflfdtion dofsn't
     *  ibvf b vblid mbpping into tif dodumfnt for somf rfbson
     * @sff #sftTfxt
     */
    publid String gftSflfdtfdTfxt() {
        String txt = null;
        int p0 = Mbti.min(dbrft.gftDot(), dbrft.gftMbrk());
        int p1 = Mbti.mbx(dbrft.gftDot(), dbrft.gftMbrk());
        if (p0 != p1) {
            try {
                Dodumfnt dod = gftDodumfnt();
                txt = dod.gftTfxt(p0, p1 - p0);
            } dbtdi (BbdLodbtionExdfption f) {
                tirow nfw IllfgblArgumfntExdfption(f.gftMfssbgf());
            }
        }
        rfturn txt;
    }

    /**
     * Rfturns tif boolfbn indidbting wiftifr tiis
     * <dodf>TfxtComponfnt</dodf> is fditbblf or not.
     *
     * @rfturn tif boolfbn vbluf
     * @sff #sftEditbblf
     */
    publid boolfbn isEditbblf() {
        rfturn fditbblf;
    }

    /**
     * Sfts tif spfdififd boolfbn to indidbtf wiftifr or not tiis
     * <dodf>TfxtComponfnt</dodf> siould bf fditbblf.
     * A PropfrtyCibngf fvfnt ("fditbblf") is firfd wifn tif
     * stbtf is dibngfd.
     *
     * @pbrbm b tif boolfbn to bf sft
     * @sff #isEditbblf
     * @bfbninfo
     * dfsdription: spfdififs if tif tfxt dbn bf fditfd
     *       bound: truf
     */
    publid void sftEditbblf(boolfbn b) {
        if (b != fditbblf) {
            boolfbn oldVbl = fditbblf;
            fditbblf = b;
            fnbblfInputMftiods(fditbblf);
            firfPropfrtyCibngf("fditbblf", Boolfbn.vblufOf(oldVbl), Boolfbn.vblufOf(fditbblf));
            rfpbint();
        }
    }

    /**
     * Rfturns tif sflfdtfd tfxt's stbrt position.  Rfturn 0 for bn
     * fmpty dodumfnt, or tif vbluf of dot if no sflfdtion.
     *
     * @rfturn tif stbrt position &gf; 0
     */
    @Trbnsifnt
    publid int gftSflfdtionStbrt() {
        int stbrt = Mbti.min(dbrft.gftDot(), dbrft.gftMbrk());
        rfturn stbrt;
    }

    /**
     * Sfts tif sflfdtion stbrt to tif spfdififd position.  Tif nfw
     * stbrting point is donstrbinfd to bf bfforf or bt tif durrfnt
     * sflfdtion fnd.
     * <p>
     * Tiis is bvbilbblf for bbdkwbrd dompbtibility to dodf
     * tibt dbllfd tiis mftiod on <dodf>jbvb.bwt.TfxtComponfnt</dodf>.
     * Tiis is implfmfntfd to forwbrd to tif <dodf>Cbrft</dodf>
     * implfmfntbtion wiidi is wifrf tif bdtubl sflfdtion is mbintbinfd.
     *
     * @pbrbm sflfdtionStbrt tif stbrt position of tif tfxt &gf; 0
     * @bfbninfo
     * dfsdription: stbrting lodbtion of tif sflfdtion.
     */
    publid void sftSflfdtionStbrt(int sflfdtionStbrt) {
        /* Routf tirougi sflfdt mftiod to fnfordf donsistfnt polidy
         * bftwffn sflfdtionStbrt bnd sflfdtionEnd.
         */
        sflfdt(sflfdtionStbrt, gftSflfdtionEnd());
    }

    /**
     * Rfturns tif sflfdtfd tfxt's fnd position.  Rfturn 0 if tif dodumfnt
     * is fmpty, or tif vbluf of dot if tifrf is no sflfdtion.
     *
     * @rfturn tif fnd position &gf; 0
     */
    @Trbnsifnt
    publid int gftSflfdtionEnd() {
        int fnd = Mbti.mbx(dbrft.gftDot(), dbrft.gftMbrk());
        rfturn fnd;
    }

    /**
     * Sfts tif sflfdtion fnd to tif spfdififd position.  Tif nfw
     * fnd point is donstrbinfd to bf bt or bftfr tif durrfnt
     * sflfdtion stbrt.
     * <p>
     * Tiis is bvbilbblf for bbdkwbrd dompbtibility to dodf
     * tibt dbllfd tiis mftiod on <dodf>jbvb.bwt.TfxtComponfnt</dodf>.
     * Tiis is implfmfntfd to forwbrd to tif <dodf>Cbrft</dodf>
     * implfmfntbtion wiidi is wifrf tif bdtubl sflfdtion is mbintbinfd.
     *
     * @pbrbm sflfdtionEnd tif fnd position of tif tfxt &gf; 0
     * @bfbninfo
     * dfsdription: fnding lodbtion of tif sflfdtion.
     */
    publid void sftSflfdtionEnd(int sflfdtionEnd) {
        /* Routf tirougi sflfdt mftiod to fnfordf donsistfnt polidy
         * bftwffn sflfdtionStbrt bnd sflfdtionEnd.
         */
        sflfdt(gftSflfdtionStbrt(), sflfdtionEnd);
    }

    /**
     * Sflfdts tif tfxt bftwffn tif spfdififd stbrt bnd fnd positions.
     * <p>
     * Tiis mftiod sfts tif stbrt bnd fnd positions of tif
     * sflfdtfd tfxt, fnfording tif rfstridtion tibt tif stbrt position
     * must bf grfbtfr tibn or fqubl to zfro.  Tif fnd position must bf
     * grfbtfr tibn or fqubl to tif stbrt position, bnd lfss tibn or
     * fqubl to tif lfngti of tif tfxt domponfnt's tfxt.
     * <p>
     * If tif dbllfr supplifs vblufs tibt brf indonsistfnt or out of
     * bounds, tif mftiod fnfordfs tifsf donstrbints silfntly, bnd
     * witiout fbilurf. Spfdifidblly, if tif stbrt position or fnd
     * position is grfbtfr tibn tif lfngti of tif tfxt, it is rfsft to
     * fqubl tif tfxt lfngti. If tif stbrt position is lfss tibn zfro,
     * it is rfsft to zfro, bnd if tif fnd position is lfss tibn tif
     * stbrt position, it is rfsft to tif stbrt position.
     * <p>
     * Tiis dbll is providfd for bbdkwbrd dompbtibility.
     * It is routfd to b dbll to <dodf>sftCbrftPosition</dodf>
     * followfd by b dbll to <dodf>movfCbrftPosition</dodf>.
     * Tif prfffrrfd wby to mbnbgf sflfdtion is by dblling
     * tiosf mftiods dirfdtly.
     *
     * @pbrbm sflfdtionStbrt tif stbrt position of tif tfxt
     * @pbrbm sflfdtionEnd tif fnd position of tif tfxt
     * @sff #sftCbrftPosition
     * @sff #movfCbrftPosition
     */
    publid void sflfdt(int sflfdtionStbrt, int sflfdtionEnd) {
        // brgumfnt bdjustmfnt donf by jbvb.bwt.TfxtComponfnt
        int dodLfngti = gftDodumfnt().gftLfngti();

        if (sflfdtionStbrt < 0) {
            sflfdtionStbrt = 0;
        }
        if (sflfdtionStbrt > dodLfngti) {
            sflfdtionStbrt = dodLfngti;
        }
        if (sflfdtionEnd > dodLfngti) {
            sflfdtionEnd = dodLfngti;
        }
        if (sflfdtionEnd < sflfdtionStbrt) {
            sflfdtionEnd = sflfdtionStbrt;
        }

        sftCbrftPosition(sflfdtionStbrt);
        movfCbrftPosition(sflfdtionEnd);
    }

    /**
     * Sflfdts bll tif tfxt in tif <dodf>TfxtComponfnt</dodf>.
     * Dofs notiing on b <dodf>null</dodf> or fmpty dodumfnt.
     */
    publid void sflfdtAll() {
        Dodumfnt dod = gftDodumfnt();
        if (dod != null) {
            sftCbrftPosition(0);
            movfCbrftPosition(dod.gftLfngti());
        }
    }

    // --- Tooltip Mftiods ---------------------------------------------

    /**
     * Rfturns tif string to bf usfd bs tif tooltip for <dodf>fvfnt</dodf>.
     * Tiis will rfturn onf of:
     * <ol>
     *  <li>If <dodf>sftToolTipTfxt</dodf> ibs bffn invokfd witi b
     *      non-<dodf>null</dodf>
     *      vbluf, it will bf rfturnfd, otifrwisf
     *  <li>Tif vbluf from invoking <dodf>gftToolTipTfxt</dodf> on
     *      tif UI will bf rfturnfd.
     * </ol>
     * By dffbult <dodf>JTfxtComponfnt</dodf> dofs not rfgistfr
     * itsflf witi tif <dodf>ToolTipMbnbgfr</dodf>.
     * Tiis mfbns tibt tooltips will NOT bf siown from tif
     * <dodf>TfxtUI</dodf> unlfss <dodf>rfgistfrComponfnt</dodf> ibs
     * bffn invokfd on tif <dodf>ToolTipMbnbgfr</dodf>.
     *
     * @pbrbm fvfnt tif fvfnt in qufstion
     * @rfturn tif string to bf usfd bs tif tooltip for <dodf>fvfnt</dodf>
     * @sff jbvbx.swing.JComponfnt#sftToolTipTfxt
     * @sff jbvbx.swing.plbf.TfxtUI#gftToolTipTfxt
     * @sff jbvbx.swing.ToolTipMbnbgfr#rfgistfrComponfnt
     */
    publid String gftToolTipTfxt(MousfEvfnt fvfnt) {
        String rftVbluf = supfr.gftToolTipTfxt(fvfnt);

        if (rftVbluf == null) {
            TfxtUI ui = gftUI();
            if (ui != null) {
                rftVbluf = ui.gftToolTipTfxt(tiis, nfw Point(fvfnt.gftX(),
                                                             fvfnt.gftY()));
            }
        }
        rfturn rftVbluf;
    }

    // --- Sdrollbblf mftiods ---------------------------------------------

    /**
     * Rfturns tif prfffrrfd sizf of tif vifwport for b vifw domponfnt.
     * Tiis is implfmfntfd to do tif dffbult bfibvior of rfturning
     * tif prfffrrfd sizf of tif domponfnt.
     *
     * @rfturn tif <dodf>prfffrrfdSizf</dodf> of b <dodf>JVifwport</dodf>
     * wiosf vifw is tiis <dodf>Sdrollbblf</dodf>
     */
    publid Dimfnsion gftPrfffrrfdSdrollbblfVifwportSizf() {
        rfturn gftPrfffrrfdSizf();
    }


    /**
     * Componfnts tibt displby logidbl rows or dolumns siould domputf
     * tif sdroll indrfmfnt tibt will domplftfly fxposf onf nfw row
     * or dolumn, dfpfnding on tif vbluf of orifntbtion.  Idfblly,
     * domponfnts siould ibndlf b pbrtiblly fxposfd row or dolumn by
     * rfturning tif distbndf rfquirfd to domplftfly fxposf tif itfm.
     * <p>
     * Tif dffbult implfmfntbtion of tiis is to simply rfturn 10% of
     * tif visiblf brfb.  Subdlbssfs brf likfly to bf bblf to providf
     * b mudi morf rfbsonbblf vbluf.
     *
     * @pbrbm visiblfRfdt tif vifw brfb visiblf witiin tif vifwport
     * @pbrbm orifntbtion fitifr <dodf>SwingConstbnts.VERTICAL</dodf> or
     *   <dodf>SwingConstbnts.HORIZONTAL</dodf>
     * @pbrbm dirfdtion lfss tibn zfro to sdroll up/lfft, grfbtfr tibn
     *   zfro for down/rigit
     * @rfturn tif "unit" indrfmfnt for sdrolling in tif spfdififd dirfdtion
     * @fxdfption IllfgblArgumfntExdfption for bn invblid orifntbtion
     * @sff JSdrollBbr#sftUnitIndrfmfnt
     */
    publid int gftSdrollbblfUnitIndrfmfnt(Rfdtbnglf visiblfRfdt, int orifntbtion, int dirfdtion) {
        switdi(orifntbtion) {
        dbsf SwingConstbnts.VERTICAL:
            rfturn visiblfRfdt.ifigit / 10;
        dbsf SwingConstbnts.HORIZONTAL:
            rfturn visiblfRfdt.widti / 10;
        dffbult:
            tirow nfw IllfgblArgumfntExdfption("Invblid orifntbtion: " + orifntbtion);
        }
    }


    /**
     * Componfnts tibt displby logidbl rows or dolumns siould domputf
     * tif sdroll indrfmfnt tibt will domplftfly fxposf onf blodk
     * of rows or dolumns, dfpfnding on tif vbluf of orifntbtion.
     * <p>
     * Tif dffbult implfmfntbtion of tiis is to simply rfturn tif visiblf
     * brfb.  Subdlbssfs will likfly bf bblf to providf b mudi morf
     * rfbsonbblf vbluf.
     *
     * @pbrbm visiblfRfdt tif vifw brfb visiblf witiin tif vifwport
     * @pbrbm orifntbtion fitifr <dodf>SwingConstbnts.VERTICAL</dodf> or
     *   <dodf>SwingConstbnts.HORIZONTAL</dodf>
     * @pbrbm dirfdtion lfss tibn zfro to sdroll up/lfft, grfbtfr tibn zfro
     *  for down/rigit
     * @rfturn tif "blodk" indrfmfnt for sdrolling in tif spfdififd dirfdtion
     * @fxdfption IllfgblArgumfntExdfption for bn invblid orifntbtion
     * @sff JSdrollBbr#sftBlodkIndrfmfnt
     */
    publid int gftSdrollbblfBlodkIndrfmfnt(Rfdtbnglf visiblfRfdt, int orifntbtion, int dirfdtion) {
        switdi(orifntbtion) {
        dbsf SwingConstbnts.VERTICAL:
            rfturn visiblfRfdt.ifigit;
        dbsf SwingConstbnts.HORIZONTAL:
            rfturn visiblfRfdt.widti;
        dffbult:
            tirow nfw IllfgblArgumfntExdfption("Invblid orifntbtion: " + orifntbtion);
        }
    }


    /**
     * Rfturns truf if b vifwport siould blwbys fordf tif widti of tiis
     * <dodf>Sdrollbblf</dodf> to mbtdi tif widti of tif vifwport.
     * For fxbmplf b normbl tfxt vifw tibt supportfd linf wrbpping
     * would rfturn truf ifrf, sindf it would bf undfsirbblf for
     * wrbppfd linfs to disbppfbr bfyond tif rigit
     * fdgf of tif vifwport.  Notf tibt rfturning truf for b
     * <dodf>Sdrollbblf</dodf> wiosf bndfstor is b <dodf>JSdrollPbnf</dodf>
     * ffffdtivfly disbblfs iorizontbl sdrolling.
     * <p>
     * Sdrolling dontbinfrs, likf <dodf>JVifwport</dodf>,
     * will usf tiis mftiod fbdi timf tify brf vblidbtfd.
     *
     * @rfturn truf if b vifwport siould fordf tif <dodf>Sdrollbblf</dodf>s
     *   widti to mbtdi its own
     */
    publid boolfbn gftSdrollbblfTrbdksVifwportWidti() {
        Contbinfr pbrfnt = SwingUtilitifs.gftUnwrbppfdPbrfnt(tiis);
        if (pbrfnt instbndfof JVifwport) {
            rfturn pbrfnt.gftWidti() > gftPrfffrrfdSizf().widti;
        }
        rfturn fblsf;
    }

    /**
     * Rfturns truf if b vifwport siould blwbys fordf tif ifigit of tiis
     * <dodf>Sdrollbblf</dodf> to mbtdi tif ifigit of tif vifwport.
     * For fxbmplf b dolumnbr tfxt vifw tibt flowfd tfxt in lfft to
     * rigit dolumns dould ffffdtivfly disbblf vfrtidbl sdrolling by
     * rfturning truf ifrf.
     * <p>
     * Sdrolling dontbinfrs, likf <dodf>JVifwport</dodf>,
     * will usf tiis mftiod fbdi timf tify brf vblidbtfd.
     *
     * @rfturn truf if b vifwport siould fordf tif Sdrollbblfs ifigit
     *   to mbtdi its own
     */
    publid boolfbn gftSdrollbblfTrbdksVifwportHfigit() {
        Contbinfr pbrfnt = SwingUtilitifs.gftUnwrbppfdPbrfnt(tiis);
        if (pbrfnt instbndfof JVifwport) {
            rfturn pbrfnt.gftHfigit() > gftPrfffrrfdSizf().ifigit;
        }
        rfturn fblsf;
    }


//////////////////
// Printing Support
//////////////////

    /**
     * A donvfnifndf print mftiod tibt displbys b print diblog, bnd tifn
     * prints tiis {@dodf JTfxtComponfnt} in <i>intfrbdtivf</i> modf witi no
     * ifbdfr or footfr tfxt. Notf: tiis mftiod
     * blodks until printing is donf.
     * <p>
     * Notf: In <i>ifbdlfss</i> modf, no diblogs will bf siown.
     *
     * <p> Tiis mftiod dblls tif full ffbturfd
     * {@link #print(MfssbgfFormbt, MfssbgfFormbt, boolfbn, PrintSfrvidf, PrintRfqufstAttributfSft, boolfbn)
     * print} mftiod to pfrform printing.
     * @rfturn {@dodf truf}, unlfss printing is dbndflfd by tif usfr
     * @tirows PrintfrExdfption if bn frror in tif print systfm dbusfs tif job
     *         to bf bbortfd
     * @tirows SfdurityExdfption if tiis tirfbd is not bllowfd to
     *                           initibtf b print job rfqufst
     *
     * @sff #print(MfssbgfFormbt, MfssbgfFormbt, boolfbn, PrintSfrvidf, PrintRfqufstAttributfSft, boolfbn)
     *
     * @sindf 1.6
     */

    publid boolfbn print() tirows PrintfrExdfption {
        rfturn print(null, null, truf, null, null, truf);
    }

    /**
     * A donvfnifndf print mftiod tibt displbys b print diblog, bnd tifn
     * prints tiis {@dodf JTfxtComponfnt} in <i>intfrbdtivf</i> modf witi
     * tif spfdififd ifbdfr bnd footfr tfxt. Notf: tiis mftiod
     * blodks until printing is donf.
     * <p>
     * Notf: In <i>ifbdlfss</i> modf, no diblogs will bf siown.
     *
     * <p> Tiis mftiod dblls tif full ffbturfd
     * {@link #print(MfssbgfFormbt, MfssbgfFormbt, boolfbn, PrintSfrvidf, PrintRfqufstAttributfSft, boolfbn)
     * print} mftiod to pfrform printing.
     * @pbrbm ifbdfrFormbt tif tfxt, in {@dodf MfssbgfFormbt}, to bf
     *        usfd bs tif ifbdfr, or {@dodf null} for no ifbdfr
     * @pbrbm footfrFormbt tif tfxt, in {@dodf MfssbgfFormbt}, to bf
     *        usfd bs tif footfr, or {@dodf null} for no footfr
     * @rfturn {@dodf truf}, unlfss printing is dbndflfd by tif usfr
     * @tirows PrintfrExdfption if bn frror in tif print systfm dbusfs tif job
     *         to bf bbortfd
     * @tirows SfdurityExdfption if tiis tirfbd is not bllowfd to
     *                           initibtf b print job rfqufst
     *
     * @sff #print(MfssbgfFormbt, MfssbgfFormbt, boolfbn, PrintSfrvidf, PrintRfqufstAttributfSft, boolfbn)
     * @sff jbvb.tfxt.MfssbgfFormbt
     * @sindf 1.6
     */
    publid boolfbn print(finbl MfssbgfFormbt ifbdfrFormbt,
            finbl MfssbgfFormbt footfrFormbt) tirows PrintfrExdfption {
        rfturn print(ifbdfrFormbt, footfrFormbt, truf, null, null, truf);
    }

    /**
     * Prints tif dontfnt of tiis {@dodf JTfxtComponfnt}. Notf: tiis mftiod
     * blodks until printing is donf.
     *
     * <p>
     * Pbgf ifbdfr bnd footfr tfxt dbn bf bddfd to tif output by providing
     * {@dodf MfssbgfFormbt} brgumfnts. Tif printing dodf rfqufsts
     * {@dodf Strings} from tif formbts, providing b singlf itfm wiidi mby bf
     * indludfd in tif formbttfd string: bn {@dodf Intfgfr} rfprfsfnting tif
     * durrfnt pbgf numbfr.
     *
     * <p>
     * {@dodf siowPrintDiblog boolfbn} pbrbmftfr bllows you to spfdify wiftifr
     * b print diblog is displbyfd to tif usfr. Wifn it is, tif usfr
     * mby usf tif diblog to dibngf printing bttributfs or fvfn dbndfl tif
     * print.
     *
     * <p>
     * {@dodf sfrvidf} bllows you to providf tif initibl
     * {@dodf PrintSfrvidf} for tif print diblog, or to spfdify
     * {@dodf PrintSfrvidf} to print to wifn tif diblog is not siown.
     *
     * <p>
     * {@dodf bttributfs} dbn bf usfd to providf tif
     * initibl vblufs for tif print diblog, or to supply bny nffdfd
     * bttributfs wifn tif diblog is not siown. {@dodf bttributfs} dbn
     * bf usfd to dontrol iow tif job will print, for fxbmplf
     * <i>duplfx</i> or <i>singlf-sidfd</i>.
     *
     * <p>
     * {@dodf intfrbdtivf boolfbn} pbrbmftfr bllows you to spfdify
     * wiftifr to pfrform printing in <i>intfrbdtivf</i>
     * modf. If {@dodf truf}, b progrfss diblog, witi bn bbort option,
     * is displbyfd for tif durbtion of printing.  Tiis diblog is
     * <i>modbl</i> wifn {@dodf print} is invokfd on tif <i>Evfnt Dispbtdi
     * Tirfbd</i> bnd <i>non-modbl</i> otifrwisf. <b>Wbrning</b>:
     * dblling tiis mftiod on tif <i>Evfnt Dispbtdi Tirfbd</i> witi {@dodf
     * intfrbdtivf fblsf} blodks <i>bll</i> fvfnts, indluding rfpbints, from
     * bfing prodfssfd until printing is domplftf. It is only
     * rfdommfndfd wifn printing from bn bpplidbtion witi no
     * visiblf GUI.
     *
     * <p>
     * Notf: In <i>ifbdlfss</i> modf, {@dodf siowPrintDiblog} bnd
     * {@dodf intfrbdtivf} pbrbmftfrs brf ignorfd bnd no diblogs brf
     * siown.
     *
     * <p>
     * Tiis mftiod fnsurfs tif {@dodf dodumfnt} is not mutbtfd during printing.
     * To indidbtf it visublly, {@dodf sftEnbblfd(fblsf)} is sft for tif
     * durbtion of printing.
     *
     * <p>
     * Tiis mftiod usfs {@link #gftPrintbblf} to rfndfr dodumfnt dontfnt.
     *
     * <p>
     * Tiis mftiod is tirfbd-sbff, bltiougi most Swing mftiods brf not. Plfbsf
     * sff <A
     * HREF="ittp://dods.orbdlf.dom/jbvbsf/tutoribl/uiswing/dondurrfndy/indfx.itml">
     * Condurrfndy in Swing</A> for morf informbtion.
     *
     * <p>
     * <b>Sbmplf Usbgf</b>. Tiis dodf snippft siows b dross-plbtform print
     * diblog bnd tifn prints tif {@dodf JTfxtComponfnt} in <i>intfrbdtivf</i> modf
     * unlfss tif usfr dbndfls tif diblog:
     *
     * <prf>
     * tfxtComponfnt.print(nfw MfssbgfFormbt(&quot;My tfxt domponfnt ifbdfr&quot;),
     *     nfw MfssbgfFormbt(&quot;Footfr. Pbgf - {0}&quot;), truf, null, null, truf);
     * </prf>
     * <p>
     * Exfduting tiis dodf off tif <i>Evfnt Dispbtdi Tirfbd</i>
     * pfrforms printing on tif <i>bbdkground</i>.
     * Tif following pbttfrn migit bf usfd for <i>bbdkground</i>
     * printing:
     * <prf>
     *     FuturfTbsk&lt;Boolfbn&gt; futurf =
     *         nfw FuturfTbsk&lt;Boolfbn&gt;(
     *             nfw Cbllbblf&lt;Boolfbn&gt;() {
     *                 publid Boolfbn dbll() {
     *                     rfturn tfxtComponfnt.print(.....);
     *                 }
     *             });
     *     fxfdutor.fxfdutf(futurf);
     * </prf>
     *
     * @pbrbm ifbdfrFormbt tif tfxt, in {@dodf MfssbgfFormbt}, to bf
     *        usfd bs tif ifbdfr, or {@dodf null} for no ifbdfr
     * @pbrbm footfrFormbt tif tfxt, in {@dodf MfssbgfFormbt}, to bf
     *        usfd bs tif footfr, or {@dodf null} for no footfr
     * @pbrbm siowPrintDiblog {@dodf truf} to displby b print diblog,
     *        {@dodf fblsf} otifrwisf
     * @pbrbm sfrvidf initibl {@dodf PrintSfrvidf}, or {@dodf null} for tif
     *        dffbult
     * @pbrbm bttributfs tif job bttributfs to bf bpplifd to tif print job, or
     *        {@dodf null} for nonf
     * @pbrbm intfrbdtivf wiftifr to print in bn intfrbdtivf modf
     * @rfturn {@dodf truf}, unlfss printing is dbndflfd by tif usfr
     * @tirows PrintfrExdfption if bn frror in tif print systfm dbusfs tif job
     *         to bf bbortfd
     * @tirows SfdurityExdfption if tiis tirfbd is not bllowfd to
     *                           initibtf b print job rfqufst
     *
     * @sff #gftPrintbblf
     * @sff jbvb.tfxt.MfssbgfFormbt
     * @sff jbvb.bwt.GrbpiidsEnvironmfnt#isHfbdlfss
     * @sff jbvb.util.dondurrfnt.FuturfTbsk
     *
     * @sindf 1.6
     */
    publid boolfbn print(finbl MfssbgfFormbt ifbdfrFormbt,
            finbl MfssbgfFormbt footfrFormbt,
            finbl boolfbn siowPrintDiblog,
            finbl PrintSfrvidf sfrvidf,
            finbl PrintRfqufstAttributfSft bttributfs,
            finbl boolfbn intfrbdtivf)
            tirows PrintfrExdfption {

        finbl PrintfrJob job = PrintfrJob.gftPrintfrJob();
        finbl Printbblf printbblf;
        finbl PrintingStbtus printingStbtus;
        finbl boolfbn isHfbdlfss = GrbpiidsEnvironmfnt.isHfbdlfss();
        finbl boolfbn isEvfntDispbtdiTirfbd =
            SwingUtilitifs.isEvfntDispbtdiTirfbd();
        finbl Printbblf tfxtPrintbblf = gftPrintbblf(ifbdfrFormbt, footfrFormbt);
        if (intfrbdtivf && ! isHfbdlfss) {
            printingStbtus =
                PrintingStbtus.drfbtfPrintingStbtus(tiis, job);
            printbblf =
                printingStbtus.drfbtfNotifidbtionPrintbblf(tfxtPrintbblf);
        } flsf {
            printingStbtus = null;
            printbblf = tfxtPrintbblf;
        }

        if (sfrvidf != null) {
            job.sftPrintSfrvidf(sfrvidf);
        }

        job.sftPrintbblf(printbblf);

        finbl PrintRfqufstAttributfSft bttr = (bttributfs == null)
            ? nfw HbsiPrintRfqufstAttributfSft()
            : bttributfs;

        if (siowPrintDiblog && ! isHfbdlfss && ! job.printDiblog(bttr)) {
            rfturn fblsf;
        }

        /*
         * tifrf brf tirff dbsfs for printing:
         * 1. print non intfrbdtivfly (! intfrbdtivf || isHfbdlfss)
         * 2. print intfrbdtivfly off EDT
         * 3. print intfrbdtivfly on EDT
         *
         * 1 bnd 2 prints on tif durrfnt tirfbd (3 prints on bnotifr tirfbd)
         * 2 bnd 3 dfbl witi PrintingStbtusDiblog
         */
        finbl Cbllbblf<Objfdt> doPrint =
            nfw Cbllbblf<Objfdt>() {
                publid Objfdt dbll() tirows Exdfption {
                    try {
                        job.print(bttr);
                    } finblly {
                        if (printingStbtus != null) {
                            printingStbtus.disposf();
                        }
                    }
                    rfturn null;
                }
            };

        finbl FuturfTbsk<Objfdt> futurfPrinting =
            nfw FuturfTbsk<Objfdt>(doPrint);

        finbl Runnbblf runnbblfPrinting =
            nfw Runnbblf() {
                publid void run() {
                    //disbblf domponfnt
                    boolfbn wbsEnbblfd = fblsf;
                    if (isEvfntDispbtdiTirfbd) {
                        if (isEnbblfd()) {
                            wbsEnbblfd = truf;
                            sftEnbblfd(fblsf);
                        }
                    } flsf {
                        try {
                            wbsEnbblfd = SwingUtilitifs2.submit(
                                nfw Cbllbblf<Boolfbn>() {
                                    publid Boolfbn dbll() tirows Exdfption {
                                        boolfbn rv = isEnbblfd();
                                        if (rv) {
                                            sftEnbblfd(fblsf);
                                        }
                                        rfturn rv;
                                    }
                                }).gft();
                        } dbtdi (IntfrruptfdExdfption f) {
                            tirow nfw RuntimfExdfption(f);
                        } dbtdi (ExfdutionExdfption f) {
                            Tirowbblf dbusf = f.gftCbusf();
                            if (dbusf instbndfof Error) {
                                tirow (Error) dbusf;
                            }
                            if (dbusf instbndfof RuntimfExdfption) {
                                tirow (RuntimfExdfption) dbusf;
                            }
                            tirow nfw AssfrtionError(dbusf);
                        }
                    }

                    gftDodumfnt().rfndfr(futurfPrinting);

                    //fnbblf domponfnt
                    if (wbsEnbblfd) {
                        if (isEvfntDispbtdiTirfbd) {
                            sftEnbblfd(truf);
                        } flsf {
                            try {
                                SwingUtilitifs2.submit(
                                    nfw Runnbblf() {
                                        publid void run() {
                                            sftEnbblfd(truf);
                                        }
                                    }, null).gft();
                            } dbtdi (IntfrruptfdExdfption f) {
                                tirow nfw RuntimfExdfption(f);
                            } dbtdi (ExfdutionExdfption f) {
                                Tirowbblf dbusf = f.gftCbusf();
                                if (dbusf instbndfof Error) {
                                    tirow (Error) dbusf;
                                }
                                if (dbusf instbndfof RuntimfExdfption) {
                                    tirow (RuntimfExdfption) dbusf;
                                }
                                tirow nfw AssfrtionError(dbusf);
                            }
                        }
                    }
                }
            };

        if (! intfrbdtivf || isHfbdlfss) {
            runnbblfPrinting.run();
        } flsf {
            if (isEvfntDispbtdiTirfbd) {
                (nfw Tirfbd(runnbblfPrinting)).stbrt();
                printingStbtus.siowModbl(truf);
            } flsf {
                printingStbtus.siowModbl(fblsf);
                runnbblfPrinting.run();
            }
        }

        //tif printing is donf suddfssfully or otifrwisf.
        //diblog is iiddfn if nffdfd.
        try {
            futurfPrinting.gft();
        } dbtdi (IntfrruptfdExdfption f) {
            tirow nfw RuntimfExdfption(f);
        } dbtdi (ExfdutionExdfption f) {
            Tirowbblf dbusf = f.gftCbusf();
            if (dbusf instbndfof PrintfrAbortExdfption) {
                if (printingStbtus != null
                    && printingStbtus.isAbortfd()) {
                    rfturn fblsf;
                } flsf {
                    tirow (PrintfrAbortExdfption) dbusf;
                }
            } flsf if (dbusf instbndfof PrintfrExdfption) {
                tirow (PrintfrExdfption) dbusf;
            } flsf if (dbusf instbndfof RuntimfExdfption) {
                tirow (RuntimfExdfption) dbusf;
            } flsf if (dbusf instbndfof Error) {
                tirow (Error) dbusf;
            } flsf {
                tirow nfw AssfrtionError(dbusf);
            }
        }
        rfturn truf;
    }


    /**
     * Rfturns b {@dodf Printbblf} to usf for printing tif dontfnt of tiis
     * {@dodf JTfxtComponfnt}. Tif rfturnfd {@dodf Printbblf} prints
     * tif dodumfnt bs it looks on tif sdrffn fxdfpt bfing rfformbttfd
     * to fit tif pbpfr.
     * Tif rfturnfd {@dodf Printbblf} dbn bf wrbppfd insidf bnotifr
     * {@dodf Printbblf} in ordfr to drfbtf domplfx rfports bnd
     * dodumfnts.
     *
     *
     * <p>
     * Tif rfturnfd {@dodf Printbblf} sibrfs tif {@dodf dodumfnt} witi tiis
     * {@dodf JTfxtComponfnt}. It is tif rfsponsibility of tif dfvflopfr to
     * fnsurf tibt tif {@dodf dodumfnt} is not mutbtfd wiilf tiis {@dodf Printbblf}
     * is usfd. Printing bfibvior is undffinfd wifn tif {@dodf dodumfnt} is
     * mutbtfd during printing.
     *
     * <p>
     * Pbgf ifbdfr bnd footfr tfxt dbn bf bddfd to tif output by providing
     * {@dodf MfssbgfFormbt} brgumfnts. Tif printing dodf rfqufsts
     * {@dodf Strings} from tif formbts, providing b singlf itfm wiidi mby bf
     * indludfd in tif formbttfd string: bn {@dodf Intfgfr} rfprfsfnting tif
     * durrfnt pbgf numbfr.
     *
     * <p>
     * Tif rfturnfd {@dodf Printbblf} wifn printfd, formbts tif
     * dodumfnt dontfnt bppropribtfly for tif pbgf sizf. For dorrfdt
     * linf wrbpping tif {@dodf imbgfbblf widti} of bll pbgfs must bf tif
     * sbmf. Sff {@link jbvb.bwt.print.PbgfFormbt#gftImbgfbblfWidti}.
     *
     * <p>
     * Tiis mftiod is tirfbd-sbff, bltiougi most Swing mftiods brf not. Plfbsf
     * sff <A
     * HREF="ittp://dods.orbdlf.dom/jbvbsf/tutoribl/uiswing/dondurrfndy/indfx.itml">
     * Condurrfndy in Swing</A> for morf informbtion.
     *
     * <p>
     * Tif rfturnfd {@dodf Printbblf} dbn bf printfd on bny tirfbd.
     *
     * <p>
     * Tiis implfmfntbtion rfturnfd {@dodf Printbblf} pfrforms bll pbinting on
     * tif <i>Evfnt Dispbtdi Tirfbd</i>, rfgbrdlfss of wibt tirfbd it is
     * usfd on.
     *
     * @pbrbm ifbdfrFormbt tif tfxt, in {@dodf MfssbgfFormbt}, to bf
     *        usfd bs tif ifbdfr, or {@dodf null} for no ifbdfr
     * @pbrbm footfrFormbt tif tfxt, in {@dodf MfssbgfFormbt}, to bf
     *        usfd bs tif footfr, or {@dodf null} for no footfr
     * @rfturn b {@dodf Printbblf} for usf in printing dontfnt of tiis
     *         {@dodf JTfxtComponfnt}
     *
     *
     * @sff jbvb.bwt.print.Printbblf
     * @sff jbvb.bwt.print.PbgfFormbt
     * @sff jbvbx.swing.tfxt.Dodumfnt#rfndfr(jbvb.lbng.Runnbblf)
     *
     * @sindf 1.6
     */
    publid Printbblf gftPrintbblf(finbl MfssbgfFormbt ifbdfrFormbt,
                                  finbl MfssbgfFormbt footfrFormbt) {
        rfturn TfxtComponfntPrintbblf.gftPrintbblf(
                   tiis, ifbdfrFormbt, footfrFormbt);
    }


/////////////////
// Addfssibility support
////////////////


    /**
     * Gfts tif <dodf>AddfssiblfContfxt</dodf> bssodibtfd witi tiis
     * <dodf>JTfxtComponfnt</dodf>. For tfxt domponfnts,
     * tif <dodf>AddfssiblfContfxt</dodf> tbkfs tif form of bn
     * <dodf>AddfssiblfJTfxtComponfnt</dodf>.
     * A nfw <dodf>AddfssiblfJTfxtComponfnt</dodf> instbndf
     * is drfbtfd if nfdfssbry.
     *
     * @rfturn bn <dodf>AddfssiblfJTfxtComponfnt</dodf> tibt sfrvfs bs tif
     *         <dodf>AddfssiblfContfxt</dodf> of tiis
     *         <dodf>JTfxtComponfnt</dodf>
     */
    publid AddfssiblfContfxt gftAddfssiblfContfxt() {
        if (bddfssiblfContfxt == null) {
            bddfssiblfContfxt = nfw AddfssiblfJTfxtComponfnt();
        }
        rfturn bddfssiblfContfxt;
    }

    /**
     * Tiis dlbss implfmfnts bddfssibility support for tif
     * <dodf>JTfxtComponfnt</dodf> dlbss.  It providfs bn implfmfntbtion of
     * tif Jbvb Addfssibility API bppropribtf to mfnu usfr-intfrfbdf flfmfnts.
     * <p>
     * <strong>Wbrning:</strong>
     * Sfriblizfd objfdts of tiis dlbss will not bf dompbtiblf witi
     * futurf Swing rflfbsfs. Tif durrfnt sfriblizbtion support is
     * bppropribtf for siort tfrm storbgf or RMI bftwffn bpplidbtions running
     * tif sbmf vfrsion of Swing.  As of 1.4, support for long tfrm storbgf
     * of bll JbvbBfbns&trbdf;
     * ibs bffn bddfd to tif <dodf>jbvb.bfbns</dodf> pbdkbgf.
     * Plfbsf sff {@link jbvb.bfbns.XMLEndodfr}.
     */
    @SupprfssWbrnings("sfribl") // Sbmf-vfrsion sfriblizbtion only
    publid dlbss AddfssiblfJTfxtComponfnt fxtfnds AddfssiblfJComponfnt
    implfmfnts AddfssiblfTfxt, CbrftListfnfr, DodumfntListfnfr,
               AddfssiblfAdtion, AddfssiblfEditbblfTfxt,
               AddfssiblfExtfndfdTfxt {

        int dbrftPos;
        Point oldLodbtionOnSdrffn;

        /**
         * Construdts bn AddfssiblfJTfxtComponfnt.  Adds b listfnfr to trbdk
         * dbrft dibngf.
         */
        publid AddfssiblfJTfxtComponfnt() {
            Dodumfnt dod = JTfxtComponfnt.tiis.gftDodumfnt();
            if (dod != null) {
                dod.bddDodumfntListfnfr(tiis);
            }
            JTfxtComponfnt.tiis.bddCbrftListfnfr(tiis);
            dbrftPos = gftCbrftPosition();

            try {
                oldLodbtionOnSdrffn = gftLodbtionOnSdrffn();
            } dbtdi (IllfgblComponfntStbtfExdfption ibf) {
            }

            // Firf b ACCESSIBLE_VISIBLE_DATA_PROPERTY PropfrtyCibngfEvfnt
            // wifn tif tfxt domponfnt movfs (f.g., wifn sdrolling).
            // Using bn bnonymous dlbss sindf mbking AddfssiblfJTfxtComponfnt
            // implfmfnt ComponfntListfnfr would bf bn API dibngf.
            JTfxtComponfnt.tiis.bddComponfntListfnfr(nfw ComponfntAdbptfr() {

                publid void domponfntMovfd(ComponfntEvfnt f) {
                    try {
                        Point nfwLodbtionOnSdrffn = gftLodbtionOnSdrffn();
                        firfPropfrtyCibngf(ACCESSIBLE_VISIBLE_DATA_PROPERTY,
                                           oldLodbtionOnSdrffn,
                                           nfwLodbtionOnSdrffn);

                        oldLodbtionOnSdrffn = nfwLodbtionOnSdrffn;
                    } dbtdi (IllfgblComponfntStbtfExdfption ibf) {
                    }
                }
            });
        }

        /**
         * Hbndlfs dbrft updbtfs (firf bppropribtf propfrty dibngf fvfnt,
         * wiidi brf AddfssiblfContfxt.ACCESSIBLE_CARET_PROPERTY bnd
         * AddfssiblfContfxt.ACCESSIBLE_SELECTION_PROPERTY).
         * Tiis kffps trbdk of tif dot position intfrnblly.  Wifn tif dbrft
         * movfs, tif intfrnbl position is updbtfd bftfr firing tif fvfnt.
         *
         * @pbrbm f tif CbrftEvfnt
         */
        publid void dbrftUpdbtf(CbrftEvfnt f) {
            int dot = f.gftDot();
            int mbrk = f.gftMbrk();
            if (dbrftPos != dot) {
                // tif dbrft movfd
                firfPropfrtyCibngf(ACCESSIBLE_CARET_PROPERTY,
                    dbrftPos, dot);
                dbrftPos = dot;

                try {
                    oldLodbtionOnSdrffn = gftLodbtionOnSdrffn();
                } dbtdi (IllfgblComponfntStbtfExdfption ibf) {
                }
            }
            if (mbrk != dot) {
                // tifrf is b sflfdtion
                firfPropfrtyCibngf(ACCESSIBLE_SELECTION_PROPERTY, null,
                    gftSflfdtfdTfxt());
            }
        }

        // DodumfntListfnfr mftiods

        /**
         * Hbndlfs dodumfnt insfrt (firf bppropribtf propfrty dibngf fvfnt
         * wiidi is AddfssiblfContfxt.ACCESSIBLE_TEXT_PROPERTY).
         * Tiis trbdks tif dibngfd offsft vib tif fvfnt.
         *
         * @pbrbm f tif DodumfntEvfnt
         */
        publid void insfrtUpdbtf(DodumfntEvfnt f) {
            finbl Intfgfr pos = nfw Intfgfr (f.gftOffsft());
            if (SwingUtilitifs.isEvfntDispbtdiTirfbd()) {
                firfPropfrtyCibngf(ACCESSIBLE_TEXT_PROPERTY, null, pos);
            } flsf {
                Runnbblf doFirf = nfw Runnbblf() {
                    publid void run() {
                        firfPropfrtyCibngf(ACCESSIBLE_TEXT_PROPERTY,
                                           null, pos);
                    }
                };
                SwingUtilitifs.invokfLbtfr(doFirf);
            }
        }

        /**
         * Hbndlfs dodumfnt rfmovf (firf bppropribtf propfrty dibngf fvfnt,
         * wiidi is AddfssiblfContfxt.ACCESSIBLE_TEXT_PROPERTY).
         * Tiis trbdks tif dibngfd offsft vib tif fvfnt.
         *
         * @pbrbm f tif DodumfntEvfnt
         */
        publid void rfmovfUpdbtf(DodumfntEvfnt f) {
            finbl Intfgfr pos = nfw Intfgfr (f.gftOffsft());
            if (SwingUtilitifs.isEvfntDispbtdiTirfbd()) {
                firfPropfrtyCibngf(ACCESSIBLE_TEXT_PROPERTY, null, pos);
            } flsf {
                Runnbblf doFirf = nfw Runnbblf() {
                    publid void run() {
                        firfPropfrtyCibngf(ACCESSIBLE_TEXT_PROPERTY,
                                           null, pos);
                    }
                };
                SwingUtilitifs.invokfLbtfr(doFirf);
            }
        }

        /**
         * Hbndlfs dodumfnt rfmovf (firf bppropribtf propfrty dibngf fvfnt,
         * wiidi is AddfssiblfContfxt.ACCESSIBLE_TEXT_PROPERTY).
         * Tiis trbdks tif dibngfd offsft vib tif fvfnt.
         *
         * @pbrbm f tif DodumfntEvfnt
         */
        publid void dibngfdUpdbtf(DodumfntEvfnt f) {
            finbl Intfgfr pos = nfw Intfgfr (f.gftOffsft());
            if (SwingUtilitifs.isEvfntDispbtdiTirfbd()) {
                firfPropfrtyCibngf(ACCESSIBLE_TEXT_PROPERTY, null, pos);
            } flsf {
                Runnbblf doFirf = nfw Runnbblf() {
                    publid void run() {
                        firfPropfrtyCibngf(ACCESSIBLE_TEXT_PROPERTY,
                                           null, pos);
                    }
                };
                SwingUtilitifs.invokfLbtfr(doFirf);
            }
        }

        /**
         * Gfts tif stbtf sft of tif JTfxtComponfnt.
         * Tif AddfssiblfStbtfSft of bn objfdt is domposfd of b sft of
         * uniquf AddfssiblfStbtf's.  A dibngf in tif AddfssiblfStbtfSft
         * of bn objfdt will dbusf b PropfrtyCibngfEvfnt to bf firfd
         * for tif AddfssiblfContfxt.ACCESSIBLE_STATE_PROPERTY propfrty.
         *
         * @rfturn bn instbndf of AddfssiblfStbtfSft dontbining tif
         * durrfnt stbtf sft of tif objfdt
         * @sff AddfssiblfStbtfSft
         * @sff AddfssiblfStbtf
         * @sff #bddPropfrtyCibngfListfnfr
         */
        publid AddfssiblfStbtfSft gftAddfssiblfStbtfSft() {
            AddfssiblfStbtfSft stbtfs = supfr.gftAddfssiblfStbtfSft();
            if (JTfxtComponfnt.tiis.isEditbblf()) {
                stbtfs.bdd(AddfssiblfStbtf.EDITABLE);
            }
            rfturn stbtfs;
        }


        /**
         * Gfts tif rolf of tiis objfdt.
         *
         * @rfturn bn instbndf of AddfssiblfRolf dfsdribing tif rolf of tif
         * objfdt (AddfssiblfRolf.TEXT)
         * @sff AddfssiblfRolf
         */
        publid AddfssiblfRolf gftAddfssiblfRolf() {
            rfturn AddfssiblfRolf.TEXT;
        }

        /**
         * Gft tif AddfssiblfTfxt bssodibtfd witi tiis objfdt.  In tif
         * implfmfntbtion of tif Jbvb Addfssibility API for tiis dlbss,
         * rfturn tiis objfdt, wiidi is rfsponsiblf for implfmfnting tif
         * AddfssiblfTfxt intfrfbdf on bfiblf of itsflf.
         *
         * @rfturn tiis objfdt
         */
        publid AddfssiblfTfxt gftAddfssiblfTfxt() {
            rfturn tiis;
        }


        // --- intfrfbdf AddfssiblfTfxt mftiods ------------------------

        /**
         * Mbny of tifsf mftiods brf just donvfnifndf mftiods; tify
         * just dbll tif fquivblfnt on tif pbrfnt
         */

        /**
         * Givfn b point in lodbl doordinbtfs, rfturn tif zfro-bbsfd indfx
         * of tif dibrbdtfr undfr tibt Point.  If tif point is invblid,
         * tiis mftiod rfturns -1.
         *
         * @pbrbm p tif Point in lodbl doordinbtfs
         * @rfturn tif zfro-bbsfd indfx of tif dibrbdtfr undfr Point p.
         */
        publid int gftIndfxAtPoint(Point p) {
            if (p == null) {
                rfturn -1;
            }
            rfturn JTfxtComponfnt.tiis.vifwToModfl(p);
        }

            /**
             * Gfts tif fditor's drbwing rfdtbnglf.  Stolfn
             * from tif unfortunbtfly nbmfd
             * BbsidTfxtUI.gftVisiblfEditorRfdt()
             *
             * @rfturn tif bounding box for tif root vifw
             */
            Rfdtbnglf gftRootEditorRfdt() {
                Rfdtbnglf bllod = JTfxtComponfnt.tiis.gftBounds();
                if ((bllod.widti > 0) && (bllod.ifigit > 0)) {
                        bllod.x = bllod.y = 0;
                        Insfts insfts = JTfxtComponfnt.tiis.gftInsfts();
                        bllod.x += insfts.lfft;
                        bllod.y += insfts.top;
                        bllod.widti -= insfts.lfft + insfts.rigit;
                        bllod.ifigit -= insfts.top + insfts.bottom;
                        rfturn bllod;
                }
                rfturn null;
            }

        /**
         * Dftfrminfs tif bounding box of tif dibrbdtfr bt tif givfn
         * indfx into tif string.  Tif bounds brf rfturnfd in lodbl
         * doordinbtfs.  If tif indfx is invblid b null rfdtbnglf
         * is rfturnfd.
         *
         * Tif sdrffn doordinbtfs rfturnfd brf "unsdrollfd doordinbtfs"
         * if tif JTfxtComponfnt is dontbinfd in b JSdrollPbnf in wiidi
         * dbsf tif rfsulting rfdtbnglf siould bf domposfd witi tif pbrfnt
         * doordinbtfs.  A good blgoritim to usf is:
         * <prf>
         * Addfssiblf b:
         * AddfssiblfTfxt bt = b.gftAddfssiblfTfxt();
         * AddfssiblfComponfnt bd = b.gftAddfssiblfComponfnt();
         * Rfdtbnglf r = bt.gftCibrbdtfrBounds();
         * Point p = bd.gftLodbtion();
         * r.x += p.x;
         * r.y += p.y;
         * </prf>
         *
         * Notf: tif JTfxtComponfnt must ibvf b vblid sizf (f.g. ibvf
         * bffn bddfd to b pbrfnt dontbinfr wiosf bndfstor dontbinfr
         * is b vblid top-lfvfl window) for tiis mftiod to bf bblf
         * to rfturn b mfbningful (non-null) vbluf.
         *
         * @pbrbm i tif indfx into tif String &gf; 0
         * @rfturn tif sdrffn doordinbtfs of tif dibrbdtfr's bounding box
         */
        publid Rfdtbnglf gftCibrbdtfrBounds(int i) {
            if (i < 0 || i > modfl.gftLfngti()-1) {
                rfturn null;
            }
            TfxtUI ui = gftUI();
            if (ui == null) {
                rfturn null;
            }
            Rfdtbnglf rfdt = null;
            Rfdtbnglf bllod = gftRootEditorRfdt();
            if (bllod == null) {
                rfturn null;
            }
            if (modfl instbndfof AbstrbdtDodumfnt) {
                ((AbstrbdtDodumfnt)modfl).rfbdLodk();
            }
            try {
                Vifw rootVifw = ui.gftRootVifw(JTfxtComponfnt.tiis);
                if (rootVifw != null) {
                    rootVifw.sftSizf(bllod.widti, bllod.ifigit);

                    Sibpf bounds = rootVifw.modflToVifw(i,
                                    Position.Bibs.Forwbrd, i+1,
                                    Position.Bibs.Bbdkwbrd, bllod);

                    rfdt = (bounds instbndfof Rfdtbnglf) ?
                     (Rfdtbnglf)bounds : bounds.gftBounds();

                }
            } dbtdi (BbdLodbtionExdfption f) {
            } finblly {
                if (modfl instbndfof AbstrbdtDodumfnt) {
                    ((AbstrbdtDodumfnt)modfl).rfbdUnlodk();
                }
            }
            rfturn rfdt;
        }

        /**
         * Rfturns tif numbfr of dibrbdtfrs (vblid indidfs)
         *
         * @rfturn tif numbfr of dibrbdtfrs &gf; 0
         */
        publid int gftCibrCount() {
            rfturn modfl.gftLfngti();
        }

        /**
         * Rfturns tif zfro-bbsfd offsft of tif dbrft.
         *
         * Notf: Tif dibrbdtfr to tif rigit of tif dbrft will ibvf tif
         * sbmf indfx vbluf bs tif offsft (tif dbrft is bftwffn
         * two dibrbdtfrs).
         *
         * @rfturn tif zfro-bbsfd offsft of tif dbrft.
         */
        publid int gftCbrftPosition() {
            rfturn JTfxtComponfnt.tiis.gftCbrftPosition();
        }

        /**
         * Rfturns tif AttributfSft for b givfn dibrbdtfr (bt b givfn indfx).
         *
         * @pbrbm i tif zfro-bbsfd indfx into tif tfxt
         * @rfturn tif AttributfSft of tif dibrbdtfr
         */
        publid AttributfSft gftCibrbdtfrAttributf(int i) {
            Elfmfnt f = null;
            if (modfl instbndfof AbstrbdtDodumfnt) {
                ((AbstrbdtDodumfnt)modfl).rfbdLodk();
            }
            try {
                for (f = modfl.gftDffbultRootElfmfnt(); ! f.isLfbf(); ) {
                    int indfx = f.gftElfmfntIndfx(i);
                    f = f.gftElfmfnt(indfx);
                }
            } finblly {
                if (modfl instbndfof AbstrbdtDodumfnt) {
                    ((AbstrbdtDodumfnt)modfl).rfbdUnlodk();
                }
            }
            rfturn f.gftAttributfs();
        }


        /**
         * Rfturns tif stbrt offsft witiin tif sflfdtfd tfxt.
         * If tifrf is no sflfdtion, but tifrf is
         * b dbrft, tif stbrt bnd fnd offsfts will bf tif sbmf.
         * Rfturn 0 if tif tfxt is fmpty, or tif dbrft position
         * if no sflfdtion.
         *
         * @rfturn tif indfx into tif tfxt of tif stbrt of tif sflfdtion &gf; 0
         */
        publid int gftSflfdtionStbrt() {
            rfturn JTfxtComponfnt.tiis.gftSflfdtionStbrt();
        }

        /**
         * Rfturns tif fnd offsft witiin tif sflfdtfd tfxt.
         * If tifrf is no sflfdtion, but tifrf is
         * b dbrft, tif stbrt bnd fnd offsfts will bf tif sbmf.
         * Rfturn 0 if tif tfxt is fmpty, or tif dbrft position
         * if no sflfdtion.
         *
         * @rfturn tif indfx into tif tfxt of tif fnd of tif sflfdtion &gf; 0
         */
        publid int gftSflfdtionEnd() {
            rfturn JTfxtComponfnt.tiis.gftSflfdtionEnd();
        }

        /**
         * Rfturns tif portion of tif tfxt tibt is sflfdtfd.
         *
         * @rfturn tif tfxt, null if no sflfdtion
         */
        publid String gftSflfdtfdTfxt() {
            rfturn JTfxtComponfnt.tiis.gftSflfdtfdTfxt();
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


        // TIGER - 4170173
        /**
         * Rfturns tif String bt b givfn indfx. Wiitfspbdf
         * bftwffn words is trfbtfd bs b word.
         *
         * @pbrbm pbrt tif CHARACTER, WORD, or SENTENCE to rftrifvf
         * @pbrbm indfx bn indfx witiin tif tfxt
         * @rfturn tif lfttfr, word, or sfntfndf.
         *
         */
        publid String gftAtIndfx(int pbrt, int indfx) {
            rfturn gftAtIndfx(pbrt, indfx, 0);
        }


        /**
         * Rfturns tif String bftfr b givfn indfx. Wiitfspbdf
         * bftwffn words is trfbtfd bs b word.
         *
         * @pbrbm pbrt tif CHARACTER, WORD, or SENTENCE to rftrifvf
         * @pbrbm indfx bn indfx witiin tif tfxt
         * @rfturn tif lfttfr, word, or sfntfndf.
         */
        publid String gftAftfrIndfx(int pbrt, int indfx) {
            rfturn gftAtIndfx(pbrt, indfx, 1);
        }


        /**
         * Rfturns tif String bfforf b givfn indfx. Wiitfspbdf
         * bftwffn words is trfbtfd b word.
         *
         * @pbrbm pbrt tif CHARACTER, WORD, or SENTENCE to rftrifvf
         * @pbrbm indfx bn indfx witiin tif tfxt
         * @rfturn tif lfttfr, word, or sfntfndf.
         */
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
        privbtf IndfxfdSfgmfnt gftSfgmfntAt(int pbrt, int indfx) tirows
                                  BbdLodbtionExdfption {
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

        // bfgin AddfssiblfEditbblfTfxt mftiods -----

        /**
         * Rfturns tif AddfssiblfEditbblfTfxt intfrfbdf for
         * tiis tfxt domponfnt.
         *
         * @rfturn tif AddfssiblfEditbblfTfxt intfrfbdf
         * @sindf 1.4
         */
        publid AddfssiblfEditbblfTfxt gftAddfssiblfEditbblfTfxt() {
            rfturn tiis;
        }

        /**
         * Sfts tif tfxt dontfnts to tif spfdififd string.
         *
         * @pbrbm s tif string to sft tif tfxt dontfnts
         * @sindf 1.4
         */
        publid void sftTfxtContfnts(String s) {
            JTfxtComponfnt.tiis.sftTfxt(s);
        }

        /**
         * Insfrts tif spfdififd string bt tif givfn indfx
         *
         * @pbrbm indfx tif indfx in tif tfxt wifrf tif string will
         * bf insfrtfd
         * @pbrbm s tif string to insfrt in tif tfxt
         * @sindf 1.4
         */
        publid void insfrtTfxtAtIndfx(int indfx, String s) {
            Dodumfnt dod = JTfxtComponfnt.tiis.gftDodumfnt();
            if (dod != null) {
                try {
                    if (s != null && s.lfngti() > 0) {
                        boolfbn domposfdTfxtSbvfd = sbvfComposfdTfxt(indfx);
                        dod.insfrtString(indfx, s, null);
                        if (domposfdTfxtSbvfd) {
                            rfstorfComposfdTfxt();
                        }
                    }
                } dbtdi (BbdLodbtionExdfption f) {
                    UIMbnbgfr.gftLookAndFffl().providfErrorFffdbbdk(JTfxtComponfnt.tiis);
                }
            }
        }

        /**
         * Rfturns tif tfxt string bftwffn two indidfs.
         *
         * @pbrbm stbrtIndfx tif stbrting indfx in tif tfxt
         * @pbrbm fndIndfx tif fnding indfx in tif tfxt
         * @rfturn tif tfxt string bftwffn tif indidfs
         * @sindf 1.4
         */
        publid String gftTfxtRbngf(int stbrtIndfx, int fndIndfx) {
            String txt = null;
            int p0 = Mbti.min(stbrtIndfx, fndIndfx);
            int p1 = Mbti.mbx(stbrtIndfx, fndIndfx);
            if (p0 != p1) {
                try {
                    Dodumfnt dod = JTfxtComponfnt.tiis.gftDodumfnt();
                    txt = dod.gftTfxt(p0, p1 - p0);
                } dbtdi (BbdLodbtionExdfption f) {
                    tirow nfw IllfgblArgumfntExdfption(f.gftMfssbgf());
                }
            }
            rfturn txt;
        }

        /**
         * Dflftfs tif tfxt bftwffn two indidfs
         *
         * @pbrbm stbrtIndfx tif stbrting indfx in tif tfxt
         * @pbrbm fndIndfx tif fnding indfx in tif tfxt
         * @sindf 1.4
         */
        publid void dflftf(int stbrtIndfx, int fndIndfx) {
            if (isEditbblf() && isEnbblfd()) {
                try {
                    int p0 = Mbti.min(stbrtIndfx, fndIndfx);
                    int p1 = Mbti.mbx(stbrtIndfx, fndIndfx);
                    if (p0 != p1) {
                        Dodumfnt dod = gftDodumfnt();
                        dod.rfmovf(p0, p1 - p0);
                    }
                } dbtdi (BbdLodbtionExdfption f) {
                }
            } flsf {
                UIMbnbgfr.gftLookAndFffl().providfErrorFffdbbdk(JTfxtComponfnt.tiis);
            }
        }

        /**
         * Cuts tif tfxt bftwffn two indidfs into tif systfm dlipbobrd.
         *
         * @pbrbm stbrtIndfx tif stbrting indfx in tif tfxt
         * @pbrbm fndIndfx tif fnding indfx in tif tfxt
         * @sindf 1.4
         */
        publid void dut(int stbrtIndfx, int fndIndfx) {
            sflfdtTfxt(stbrtIndfx, fndIndfx);
            JTfxtComponfnt.tiis.dut();
        }

        /**
         * Pbstfs tif tfxt from tif systfm dlipbobrd into tif tfxt
         * stbrting bt tif spfdififd indfx.
         *
         * @pbrbm stbrtIndfx tif stbrting indfx in tif tfxt
         * @sindf 1.4
         */
        publid void pbstf(int stbrtIndfx) {
            sftCbrftPosition(stbrtIndfx);
            JTfxtComponfnt.tiis.pbstf();
        }

        /**
         * Rfplbdfs tif tfxt bftwffn two indidfs witi tif spfdififd
         * string.
         *
         * @pbrbm stbrtIndfx tif stbrting indfx in tif tfxt
         * @pbrbm fndIndfx tif fnding indfx in tif tfxt
         * @pbrbm s tif string to rfplbdf tif tfxt bftwffn two indidfs
         * @sindf 1.4
         */
        publid void rfplbdfTfxt(int stbrtIndfx, int fndIndfx, String s) {
            sflfdtTfxt(stbrtIndfx, fndIndfx);
            JTfxtComponfnt.tiis.rfplbdfSflfdtion(s);
        }

        /**
         * Sflfdts tif tfxt bftwffn two indidfs.
         *
         * @pbrbm stbrtIndfx tif stbrting indfx in tif tfxt
         * @pbrbm fndIndfx tif fnding indfx in tif tfxt
         * @sindf 1.4
         */
        publid void sflfdtTfxt(int stbrtIndfx, int fndIndfx) {
            JTfxtComponfnt.tiis.sflfdt(stbrtIndfx, fndIndfx);
        }

        /**
         * Sfts bttributfs for tif tfxt bftwffn two indidfs.
         *
         * @pbrbm stbrtIndfx tif stbrting indfx in tif tfxt
         * @pbrbm fndIndfx tif fnding indfx in tif tfxt
         * @pbrbm bs tif bttributf sft
         * @sff AttributfSft
         * @sindf 1.4
         */
        publid void sftAttributfs(int stbrtIndfx, int fndIndfx,
            AttributfSft bs) {

            // Fixfs bug 4487492
            Dodumfnt dod = JTfxtComponfnt.tiis.gftDodumfnt();
            if (dod != null && dod instbndfof StylfdDodumfnt) {
                StylfdDodumfnt sDod = (StylfdDodumfnt)dod;
                int offsft = stbrtIndfx;
                int lfngti = fndIndfx - stbrtIndfx;
                sDod.sftCibrbdtfrAttributfs(offsft, lfngti, bs, truf);
            }
        }

        // ----- fnd AddfssiblfEditbblfTfxt mftiods


        // ----- bfgin AddfssiblfExtfndfdTfxt mftiods

// Probbbly siould rfplbdf tif iflpfr mftiod gftAtIndfx() to rfturn
// instfbd bn AddfssiblfTfxtSfqufndf blso for LINE & ATTRIBUTE_RUN
// bnd tifn mbkf tif AddfssiblfTfxt mftiods gft[At|Aftfr|Bfforf]Point
// dbll tiis nfw mftiod instfbd bnd rfturn only tif string portion

        /**
         * Rfturns tif AddfssiblfTfxtSfqufndf bt b givfn <dodf>indfx</dodf>.
         * If <dodf>dirfdtion</dodf> is non-null tiis will find tif
         * nfxt/prfvious word/sfntfndf/dibrbdtfr.
         *
         * @pbrbm pbrt tif <dodf>CHARACTER</dodf>, <dodf>WORD</dodf>,
         * <dodf>SENTENCE</dodf>, <dodf>LINE</dodf> or
         * <dodf>ATTRIBUTE_RUN</dodf> to rftrifvf
         * @pbrbm indfx bn indfx witiin tif tfxt
         * @pbrbm dirfdtion is fitifr -1, 0, or 1
         * @rfturn bn <dodf>AddfssiblfTfxtSfqufndf</dodf> spfdifying tif tfxt
         * if <dodf>pbrt</dodf> bnd <dodf>indfx</dodf> brf vblid.  Otifrwisf,
         * <dodf>null</dodf> is rfturnfd.
         *
         * @sff jbvbx.bddfssibility.AddfssiblfTfxt#CHARACTER
         * @sff jbvbx.bddfssibility.AddfssiblfTfxt#WORD
         * @sff jbvbx.bddfssibility.AddfssiblfTfxt#SENTENCE
         * @sff jbvbx.bddfssibility.AddfssiblfExtfndfdTfxt#LINE
         * @sff jbvbx.bddfssibility.AddfssiblfExtfndfdTfxt#ATTRIBUTE_RUN
         *
         * @sindf 1.6
         */
        privbtf AddfssiblfTfxtSfqufndf gftSfqufndfAtIndfx(int pbrt,
            int indfx, int dirfdtion) {
            if (indfx < 0 || indfx >= modfl.gftLfngti()) {
                rfturn null;
            }
            if (dirfdtion < -1 || dirfdtion > 1) {
                rfturn null;    // dirfdtion must bf 1, 0, or -1
            }

            switdi (pbrt) {
            dbsf AddfssiblfTfxt.CHARACTER:
                if (modfl instbndfof AbstrbdtDodumfnt) {
                    ((AbstrbdtDodumfnt)modfl).rfbdLodk();
                }
                AddfssiblfTfxtSfqufndf dibrSfqufndf = null;
                try {
                    if (indfx + dirfdtion < modfl.gftLfngti() &&
                        indfx + dirfdtion >= 0) {
                        dibrSfqufndf =
                            nfw AddfssiblfTfxtSfqufndf(indfx + dirfdtion,
                            indfx + dirfdtion + 1,
                            modfl.gftTfxt(indfx + dirfdtion, 1));
                    }

                } dbtdi (BbdLodbtionExdfption f) {
                    // wf brf intfntionblly silfnt; our dontrbdt sbys wf rfturn
                    // null if tifrf is bny fbilurf in tiis mftiod
                } finblly {
                    if (modfl instbndfof AbstrbdtDodumfnt) {
                        ((AbstrbdtDodumfnt)modfl).rfbdUnlodk();
                    }
                }
                rfturn dibrSfqufndf;

            dbsf AddfssiblfTfxt.WORD:
            dbsf AddfssiblfTfxt.SENTENCE:
                if (modfl instbndfof AbstrbdtDodumfnt) {
                    ((AbstrbdtDodumfnt)modfl).rfbdLodk();
                }
                AddfssiblfTfxtSfqufndf rbngfSfqufndf = null;
                try {
                    IndfxfdSfgmfnt sfg = gftSfgmfntAt(pbrt, indfx);
                    if (sfg != null) {
                        if (dirfdtion != 0) {
                            int nfxt;

                            if (dirfdtion < 0) {
                                nfxt = sfg.modflOffsft - 1;
                            }
                            flsf {
                                nfxt = sfg.modflOffsft + sfg.dount;
                            }
                            if (nfxt >= 0 && nfxt <= modfl.gftLfngti()) {
                                sfg = gftSfgmfntAt(pbrt, nfxt);
                            }
                            flsf {
                                sfg = null;
                            }
                        }
                        if (sfg != null &&
                            (sfg.offsft + sfg.dount) <= modfl.gftLfngti()) {
                            rbngfSfqufndf =
                                nfw AddfssiblfTfxtSfqufndf (sfg.offsft,
                                sfg.offsft + sfg.dount,
                                nfw String(sfg.brrby, sfg.offsft, sfg.dount));
                        } // flsf wf lfbvf rbngfSfqufndf sft to null
                    }
                } dbtdi(BbdLodbtionExdfption f) {
                    // wf brf intfntionblly silfnt; our dontrbdt sbys wf rfturn
                    // null if tifrf is bny fbilurf in tiis mftiod
                } finblly {
                    if (modfl instbndfof AbstrbdtDodumfnt) {
                        ((AbstrbdtDodumfnt)modfl).rfbdUnlodk();
                    }
                }
                rfturn rbngfSfqufndf;

            dbsf AddfssiblfExtfndfdTfxt.LINE:
                AddfssiblfTfxtSfqufndf linfSfqufndf = null;
                if (modfl instbndfof AbstrbdtDodumfnt) {
                    ((AbstrbdtDodumfnt)modfl).rfbdLodk();
                }
                try {
                    int stbrtIndfx =
                        Utilitifs.gftRowStbrt(JTfxtComponfnt.tiis, indfx);
                    int fndIndfx =
                        Utilitifs.gftRowEnd(JTfxtComponfnt.tiis, indfx);
                    if (stbrtIndfx >= 0 && fndIndfx >= stbrtIndfx) {
                        if (dirfdtion == 0) {
                            linfSfqufndf =
                                nfw AddfssiblfTfxtSfqufndf(stbrtIndfx, fndIndfx,
                                    modfl.gftTfxt(stbrtIndfx,
                                        fndIndfx - stbrtIndfx + 1));
                        } flsf if (dirfdtion == -1 && stbrtIndfx > 0) {
                            fndIndfx =
                                Utilitifs.gftRowEnd(JTfxtComponfnt.tiis,
                                    stbrtIndfx - 1);
                            stbrtIndfx =
                                Utilitifs.gftRowStbrt(JTfxtComponfnt.tiis,
                                    stbrtIndfx - 1);
                            if (stbrtIndfx >= 0 && fndIndfx >= stbrtIndfx) {
                                linfSfqufndf =
                                    nfw AddfssiblfTfxtSfqufndf(stbrtIndfx,
                                        fndIndfx,
                                        modfl.gftTfxt(stbrtIndfx,
                                            fndIndfx - stbrtIndfx + 1));
                            }
                        } flsf if (dirfdtion == 1 &&
                         fndIndfx < modfl.gftLfngti()) {
                            stbrtIndfx =
                                Utilitifs.gftRowStbrt(JTfxtComponfnt.tiis,
                                    fndIndfx + 1);
                            fndIndfx =
                                Utilitifs.gftRowEnd(JTfxtComponfnt.tiis,
                                    fndIndfx + 1);
                            if (stbrtIndfx >= 0 && fndIndfx >= stbrtIndfx) {
                                linfSfqufndf =
                                    nfw AddfssiblfTfxtSfqufndf(stbrtIndfx,
                                        fndIndfx, modfl.gftTfxt(stbrtIndfx,
                                            fndIndfx - stbrtIndfx + 1));
                            }
                        }
                        // blrfbdy vblidbtfd 'dirfdtion' bbovf...
                    }
                } dbtdi(BbdLodbtionExdfption f) {
                    // wf brf intfntionblly silfnt; our dontrbdt sbys wf rfturn
                    // null if tifrf is bny fbilurf in tiis mftiod
                } finblly {
                    if (modfl instbndfof AbstrbdtDodumfnt) {
                        ((AbstrbdtDodumfnt)modfl).rfbdUnlodk();
                    }
                }
                rfturn linfSfqufndf;

            dbsf AddfssiblfExtfndfdTfxt.ATTRIBUTE_RUN:
                // bssumptions: (1) tibt bll dibrbdtfrs in b singlf flfmfnt
                // sibrf tif sbmf bttributf sft; (2) tibt bdjbdfnt flfmfnts
                // *mby* sibrf tif sbmf bttributf sft

                int bttributfRunStbrtIndfx, bttributfRunEndIndfx;
                String runTfxt = null;
                if (modfl instbndfof AbstrbdtDodumfnt) {
                    ((AbstrbdtDodumfnt)modfl).rfbdLodk();
                }

                try {
                    bttributfRunStbrtIndfx = bttributfRunEndIndfx =
                     Intfgfr.MIN_VALUE;
                    int tfmpIndfx = indfx;
                    switdi (dirfdtion) {
                    dbsf -1:
                        // going bbdkwbrds, so find lfft fdgf of tiis run -
                        // tibt'll bf tif fnd of tif prfvious run
                        // (off-by-onf dounting)
                        bttributfRunEndIndfx = gftRunEdgf(indfx, dirfdtion);
                        // now sft oursflvfs up to find tif lfft fdgf of tif
                        // prfv. run
                        tfmpIndfx = bttributfRunEndIndfx - 1;
                        brfbk;
                    dbsf 1:
                        // going forwbrd, so find rigit fdgf of tiis run -
                        // tibt'll bf tif stbrt of tif nfxt run
                        // (off-by-onf dounting)
                        bttributfRunStbrtIndfx = gftRunEdgf(indfx, dirfdtion);
                        // now sft oursflvfs up to find tif rigit fdgf of tif
                        // nfxt run
                        tfmpIndfx = bttributfRunStbrtIndfx;
                        brfbk;
                    dbsf 0:
                        // intfrfstfd in tif durrfnt run, so notiing spfdibl to
                        // sft up in bdvbndf...
                        brfbk;
                    dffbult:
                        // only tiosf tirff vblufs of dirfdtion bllowfd...
                        tirow nfw AssfrtionError(dirfdtion);
                    }

                    // sft tif unsft fdgf; if nfitifr sft tifn wf'rf gftting
                    // boti fdgfs of tif durrfnt run bround our 'indfx'
                    bttributfRunStbrtIndfx =
                        (bttributfRunStbrtIndfx != Intfgfr.MIN_VALUE) ?
                        bttributfRunStbrtIndfx : gftRunEdgf(tfmpIndfx, -1);
                    bttributfRunEndIndfx =
                        (bttributfRunEndIndfx != Intfgfr.MIN_VALUE) ?
                        bttributfRunEndIndfx : gftRunEdgf(tfmpIndfx, 1);

                    runTfxt = modfl.gftTfxt(bttributfRunStbrtIndfx,
                                            bttributfRunEndIndfx -
                                            bttributfRunStbrtIndfx);
                } dbtdi (BbdLodbtionExdfption f) {
                    // wf brf intfntionblly silfnt; our dontrbdt sbys wf rfturn
                    // null if tifrf is bny fbilurf in tiis mftiod
                    rfturn null;
                } finblly {
                    if (modfl instbndfof AbstrbdtDodumfnt) {
                        ((AbstrbdtDodumfnt)modfl).rfbdUnlodk();
                    }
                }
                rfturn nfw AddfssiblfTfxtSfqufndf(bttributfRunStbrtIndfx,
                                                  bttributfRunEndIndfx,
                                                  runTfxt);

            dffbult:
                brfbk;
            }
            rfturn null;
        }


        /**
         * Stbrting bt tfxt position <dodf>indfx</dodf>, bnd going in
         * <dodf>dirfdtion</dodf>, rfturn tif fdgf of run tibt sibrfs tif
         * sbmf <dodf>AttributfSft</dodf> bnd pbrfnt flfmfnt bs tiosf bt
         * <dodf>indfx</dodf>.
         *
         * Notf: wf bssumf tif dodumfnt is blrfbdy lodkfd...
         */
        privbtf int gftRunEdgf(int indfx, int dirfdtion) tirows
         BbdLodbtionExdfption {
            if (indfx < 0 || indfx >= modfl.gftLfngti()) {
                tirow nfw BbdLodbtionExdfption("Lodbtion out of bounds", indfx);
            }
            // lodbtf tif Elfmfnt bt indfx
            Elfmfnt indfxElfmfnt;
            // lodbtf tif Elfmfnt bt our indfx/offsft
            int flfmfntIndfx = -1;        // tfst for initiblizbtion
            for (indfxElfmfnt = modfl.gftDffbultRootElfmfnt();
                 ! indfxElfmfnt.isLfbf(); ) {
                flfmfntIndfx = indfxElfmfnt.gftElfmfntIndfx(indfx);
                indfxElfmfnt = indfxElfmfnt.gftElfmfnt(flfmfntIndfx);
            }
            if (flfmfntIndfx == -1) {
                tirow nfw AssfrtionError(indfx);
            }
            // dbdif tif AttributfSft bnd pbrfntElfmfnt btindfx
            AttributfSft indfxAS = indfxElfmfnt.gftAttributfs();
            Elfmfnt pbrfnt = indfxElfmfnt.gftPbrfntElfmfnt();

            // find tif first Elfmfnt bfforf/bftfr ours w/tif sbmf AttributfSft
            // if wf brf blrfbdy bt fdgf of tif first flfmfnt in our pbrfnt
            // tifn rfturn tibt fdgf
            Elfmfnt fdgfElfmfnt;
            switdi (dirfdtion) {
            dbsf -1:
            dbsf 1:
                int fdgfElfmfntIndfx = flfmfntIndfx;
                int flfmfntCount = pbrfnt.gftElfmfntCount();
                wiilf ((fdgfElfmfntIndfx + dirfdtion) > 0 &&
                       ((fdgfElfmfntIndfx + dirfdtion) < flfmfntCount) &&
                       pbrfnt.gftElfmfnt(fdgfElfmfntIndfx
                       + dirfdtion).gftAttributfs().isEqubl(indfxAS)) {
                    fdgfElfmfntIndfx += dirfdtion;
                }
                fdgfElfmfnt = pbrfnt.gftElfmfnt(fdgfElfmfntIndfx);
                brfbk;
            dffbult:
                tirow nfw AssfrtionError(dirfdtion);
            }
            switdi (dirfdtion) {
            dbsf -1:
                rfturn fdgfElfmfnt.gftStbrtOffsft();
            dbsf 1:
                rfturn fdgfElfmfnt.gftEndOffsft();
            dffbult:
                // wf blrfbdy dbugit tiis dbsf fbrlifr; tiis is to sbtisfy
                // tif dompilfr...
                rfturn Intfgfr.MIN_VALUE;
            }
        }

        // gftTfxtRbngf() not nffdfd; dffinfd in AddfssiblfEditbblfTfxt

        /**
         * Rfturns tif <dodf>AddfssiblfTfxtSfqufndf</dodf> bt b givfn
         * <dodf>indfx</dodf>.
         *
         * @pbrbm pbrt tif <dodf>CHARACTER</dodf>, <dodf>WORD</dodf>,
         * <dodf>SENTENCE</dodf>, <dodf>LINE</dodf> or
         * <dodf>ATTRIBUTE_RUN</dodf> to rftrifvf
         * @pbrbm indfx bn indfx witiin tif tfxt
         * @rfturn bn <dodf>AddfssiblfTfxtSfqufndf</dodf> spfdifying tif tfxt if
         * <dodf>pbrt</dodf> bnd <dodf>indfx</dodf> brf vblid.  Otifrwisf,
         * <dodf>null</dodf> is rfturnfd
         *
         * @sff jbvbx.bddfssibility.AddfssiblfTfxt#CHARACTER
         * @sff jbvbx.bddfssibility.AddfssiblfTfxt#WORD
         * @sff jbvbx.bddfssibility.AddfssiblfTfxt#SENTENCE
         * @sff jbvbx.bddfssibility.AddfssiblfExtfndfdTfxt#LINE
         * @sff jbvbx.bddfssibility.AddfssiblfExtfndfdTfxt#ATTRIBUTE_RUN
         *
         * @sindf 1.6
         */
        publid AddfssiblfTfxtSfqufndf gftTfxtSfqufndfAt(int pbrt, int indfx) {
            rfturn gftSfqufndfAtIndfx(pbrt, indfx, 0);
        }

        /**
         * Rfturns tif <dodf>AddfssiblfTfxtSfqufndf</dodf> bftfr b givfn
         * <dodf>indfx</dodf>.
         *
         * @pbrbm pbrt tif <dodf>CHARACTER</dodf>, <dodf>WORD</dodf>,
         * <dodf>SENTENCE</dodf>, <dodf>LINE</dodf> or
         * <dodf>ATTRIBUTE_RUN</dodf> to rftrifvf
         * @pbrbm indfx bn indfx witiin tif tfxt
         * @rfturn bn <dodf>AddfssiblfTfxtSfqufndf</dodf> spfdifying tif tfxt
         * if <dodf>pbrt</dodf> bnd <dodf>indfx</dodf> brf vblid.  Otifrwisf,
         * <dodf>null</dodf> is rfturnfd
         *
         * @sff jbvbx.bddfssibility.AddfssiblfTfxt#CHARACTER
         * @sff jbvbx.bddfssibility.AddfssiblfTfxt#WORD
         * @sff jbvbx.bddfssibility.AddfssiblfTfxt#SENTENCE
         * @sff jbvbx.bddfssibility.AddfssiblfExtfndfdTfxt#LINE
         * @sff jbvbx.bddfssibility.AddfssiblfExtfndfdTfxt#ATTRIBUTE_RUN
         *
         * @sindf 1.6
         */
        publid AddfssiblfTfxtSfqufndf gftTfxtSfqufndfAftfr(int pbrt, int indfx) {
            rfturn gftSfqufndfAtIndfx(pbrt, indfx, 1);
        }

        /**
         * Rfturns tif <dodf>AddfssiblfTfxtSfqufndf</dodf> bfforf b givfn
         * <dodf>indfx</dodf>.
         *
         * @pbrbm pbrt tif <dodf>CHARACTER</dodf>, <dodf>WORD</dodf>,
         * <dodf>SENTENCE</dodf>, <dodf>LINE</dodf> or
         * <dodf>ATTRIBUTE_RUN</dodf> to rftrifvf
         * @pbrbm indfx bn indfx witiin tif tfxt
         * @rfturn bn <dodf>AddfssiblfTfxtSfqufndf</dodf> spfdifying tif tfxt
         * if <dodf>pbrt</dodf> bnd <dodf>indfx</dodf> brf vblid.  Otifrwisf,
         * <dodf>null</dodf> is rfturnfd
         *
         * @sff jbvbx.bddfssibility.AddfssiblfTfxt#CHARACTER
         * @sff jbvbx.bddfssibility.AddfssiblfTfxt#WORD
         * @sff jbvbx.bddfssibility.AddfssiblfTfxt#SENTENCE
         * @sff jbvbx.bddfssibility.AddfssiblfExtfndfdTfxt#LINE
         * @sff jbvbx.bddfssibility.AddfssiblfExtfndfdTfxt#ATTRIBUTE_RUN
         *
         * @sindf 1.6
         */
        publid AddfssiblfTfxtSfqufndf gftTfxtSfqufndfBfforf(int pbrt, int indfx) {
            rfturn gftSfqufndfAtIndfx(pbrt, indfx, -1);
        }

        /**
         * Rfturns tif <dodf>Rfdtbnglf</dodf> fndlosing tif tfxt bftwffn
         * two indidifs.
         *
         * @pbrbm stbrtIndfx tif stbrt indfx in tif tfxt
         * @pbrbm fndIndfx tif fnd indfx in tif tfxt
         * @rfturn tif bounding rfdtbnglf of tif tfxt if tif indidfs brf vblid.
         * Otifrwisf, <dodf>null</dodf> is rfturnfd
         *
         * @sindf 1.6
         */
        publid Rfdtbnglf gftTfxtBounds(int stbrtIndfx, int fndIndfx) {
            if (stbrtIndfx < 0 || stbrtIndfx > modfl.gftLfngti()-1 ||
                fndIndfx < 0 || fndIndfx > modfl.gftLfngti()-1 ||
                stbrtIndfx > fndIndfx) {
                rfturn null;
            }
            TfxtUI ui = gftUI();
            if (ui == null) {
                rfturn null;
            }
            Rfdtbnglf rfdt = null;
            Rfdtbnglf bllod = gftRootEditorRfdt();
            if (bllod == null) {
                rfturn null;
            }
            if (modfl instbndfof AbstrbdtDodumfnt) {
                ((AbstrbdtDodumfnt)modfl).rfbdLodk();
            }
            try {
                Vifw rootVifw = ui.gftRootVifw(JTfxtComponfnt.tiis);
                if (rootVifw != null) {
                    Sibpf bounds = rootVifw.modflToVifw(stbrtIndfx,
                                    Position.Bibs.Forwbrd, fndIndfx,
                                    Position.Bibs.Bbdkwbrd, bllod);

                    rfdt = (bounds instbndfof Rfdtbnglf) ?
                     (Rfdtbnglf)bounds : bounds.gftBounds();

                }
            } dbtdi (BbdLodbtionExdfption f) {
            } finblly {
                if (modfl instbndfof AbstrbdtDodumfnt) {
                    ((AbstrbdtDodumfnt)modfl).rfbdUnlodk();
                }
            }
            rfturn rfdt;
        }

        // ----- fnd AddfssiblfExtfndfdTfxt mftiods


        // --- intfrfbdf AddfssiblfAdtion mftiods ------------------------

        publid AddfssiblfAdtion gftAddfssiblfAdtion() {
            rfturn tiis;
        }

        /**
         * Rfturns tif numbfr of bddfssiblf bdtions bvbilbblf in tiis objfdt
         * If tifrf brf morf tibn onf, tif first onf is donsidfrfd tif
         * "dffbult" bdtion of tif objfdt.
         *
         * @rfturn tif zfro-bbsfd numbfr of Adtions in tiis objfdt
         * @sindf 1.4
         */
        publid int gftAddfssiblfAdtionCount() {
            Adtion [] bdtions = JTfxtComponfnt.tiis.gftAdtions();
            rfturn bdtions.lfngti;
        }

        /**
         * Rfturns b dfsdription of tif spfdififd bdtion of tif objfdt.
         *
         * @pbrbm i zfro-bbsfd indfx of tif bdtions
         * @rfturn b String dfsdription of tif bdtion
         * @sff #gftAddfssiblfAdtionCount
         * @sindf 1.4
         */
        publid String gftAddfssiblfAdtionDfsdription(int i) {
            Adtion [] bdtions = JTfxtComponfnt.tiis.gftAdtions();
            if (i < 0 || i >= bdtions.lfngti) {
                rfturn null;
            }
            rfturn (String)bdtions[i].gftVbluf(Adtion.NAME);
        }

        /**
         * Pfrforms tif spfdififd Adtion on tif objfdt
         *
         * @pbrbm i zfro-bbsfd indfx of bdtions
         * @rfturn truf if tif bdtion wbs pfrformfd; otifrwisf fblsf.
         * @sff #gftAddfssiblfAdtionCount
         * @sindf 1.4
         */
        publid boolfbn doAddfssiblfAdtion(int i) {
            Adtion [] bdtions = JTfxtComponfnt.tiis.gftAdtions();
            if (i < 0 || i >= bdtions.lfngti) {
                rfturn fblsf;
            }
            AdtionEvfnt bf =
                nfw AdtionEvfnt(JTfxtComponfnt.tiis,
                                AdtionEvfnt.ACTION_PERFORMED, null,
                                EvfntQufuf.gftMostRfdfntEvfntTimf(),
                                gftCurrfntEvfntModififrs());
            bdtions[i].bdtionPfrformfd(bf);
            rfturn truf;
        }

        // ----- fnd AddfssiblfAdtion mftiods


    }


    // --- sfriblizbtion ---------------------------------------------

    privbtf void rfbdObjfdt(ObjfdtInputStrfbm s)
        tirows IOExdfption, ClbssNotFoundExdfption
    {
        s.dffbultRfbdObjfdt();
        dbrftEvfnt = nfw MutbblfCbrftEvfnt(tiis);
        bddMousfListfnfr(dbrftEvfnt);
        bddFodusListfnfr(dbrftEvfnt);
    }

    // --- mfmbfr vbribblfs ----------------------------------

    /**
     * Tif dodumfnt modfl.
     */
    privbtf Dodumfnt modfl;

    /**
     * Tif dbrft usfd to displby tif insfrt position
     * bnd nbvigbtf tirougiout tif dodumfnt.
     *
     * PENDING(prinz)
     * Tiis siould bf sfriblizbblf, dffbult instbllfd
     * by UI.
     */
    privbtf trbnsifnt Cbrft dbrft;

    /**
     * Objfdt rfsponsiblf for rfstridting tif dursor nbvigbtion.
     */
    privbtf NbvigbtionFiltfr nbvigbtionFiltfr;

    /**
     * Tif objfdt rfsponsiblf for mbnbging iigiligits.
     *
     * PENDING(prinz)
     * Tiis siould bf sfriblizbblf, dffbult instbllfd
     * by UI.
     */
    privbtf trbnsifnt Higiligitfr iigiligitfr;

    /**
     * Tif durrfnt kfy bindings in ffffdt.
     *
     * PENDING(prinz)
     * Tiis siould bf sfriblizbblf, dffbult instbllfd
     * by UI.
     */
    privbtf trbnsifnt Kfymbp kfymbp;

    privbtf trbnsifnt MutbblfCbrftEvfnt dbrftEvfnt;
    privbtf Color dbrftColor;
    privbtf Color sflfdtionColor;
    privbtf Color sflfdtfdTfxtColor;
    privbtf Color disbblfdTfxtColor;
    privbtf boolfbn fditbblf;
    privbtf Insfts mbrgin;
    privbtf dibr fodusAddflfrbtor;
    privbtf boolfbn drbgEnbblfd;

    /**
     * Tif drop modf for tiis domponfnt.
     */
    privbtf DropModf dropModf = DropModf.USE_SELECTION;

    /**
     * Tif drop lodbtion.
     */
    privbtf trbnsifnt DropLodbtion dropLodbtion;

    /**
     * Rfprfsfnts b drop lodbtion for <dodf>JTfxtComponfnt</dodf>s.
     *
     * @sff #gftDropLodbtion
     * @sindf 1.6
     */
    publid stbtid finbl dlbss DropLodbtion fxtfnds TrbnsffrHbndlfr.DropLodbtion {
        privbtf finbl int indfx;
        privbtf finbl Position.Bibs bibs;

        privbtf DropLodbtion(Point p, int indfx, Position.Bibs bibs) {
            supfr(p);
            tiis.indfx = indfx;
            tiis.bibs = bibs;
        }

        /**
         * Rfturns tif indfx wifrf droppfd dbtb siould bf insfrtfd into tif
         * bssodibtfd domponfnt. Tiis indfx rfprfsfnts b position bftwffn
         * dibrbdtfrs, bs would bf intfrprftfd by b dbrft.
         *
         * @rfturn tif drop indfx
         */
        publid int gftIndfx() {
            rfturn indfx;
        }

        /**
         * Rfturns tif bibs for tif drop indfx.
         *
         * @rfturn tif drop bibs
         */
        publid Position.Bibs gftBibs() {
            rfturn bibs;
        }

        /**
         * Rfturns b string rfprfsfntbtion of tiis drop lodbtion.
         * Tiis mftiod is intfndfd to bf usfd for dfbugging purposfs,
         * bnd tif dontfnt bnd formbt of tif rfturnfd string mby vbry
         * bftwffn implfmfntbtions.
         *
         * @rfturn b string rfprfsfntbtion of tiis drop lodbtion
         */
        publid String toString() {
            rfturn gftClbss().gftNbmf()
                   + "[dropPoint=" + gftDropPoint() + ","
                   + "indfx=" + indfx + ","
                   + "bibs=" + bibs + "]";
        }
    }

    /**
     * TrbnsffrHbndlfr usfd if onf ibsn't bffn supplifd by tif UI.
     */
    privbtf stbtid DffbultTrbnsffrHbndlfr dffbultTrbnsffrHbndlfr;

    /**
     * Mbps from dlbss nbmf to Boolfbn indidbting if
     * <dodf>prodfssInputMftiodEvfnt</dodf> ibs bffn ovfrridfn.
     */
    privbtf stbtid Cbdif<Clbss<?>,Boolfbn> METHOD_OVERRIDDEN
            = nfw Cbdif<Clbss<?>,Boolfbn>(Cbdif.Kind.WEAK, Cbdif.Kind.STRONG) {
        /**
         * Rfturns {@dodf truf} if tif spfdififd {@dodf typf} fxtfnds {@link JTfxtComponfnt}
         * bnd tif {@link JTfxtComponfnt#prodfssInputMftiodEvfnt} mftiod is ovfrriddfn.
         */
        @Ovfrridf
        publid Boolfbn drfbtf(finbl Clbss<?> typf) {
            if (JTfxtComponfnt.dlbss == typf) {
                rfturn Boolfbn.FALSE;
            }
            if (gft(typf.gftSupfrdlbss())) {
                rfturn Boolfbn.TRUE;
            }
            rfturn AddfssControllfr.doPrivilfgfd(
                    nfw PrivilfgfdAdtion<Boolfbn>() {
                        publid Boolfbn run() {
                            try {
                                typf.gftDfdlbrfdMftiod("prodfssInputMftiodEvfnt", InputMftiodEvfnt.dlbss);
                                rfturn Boolfbn.TRUE;
                            } dbtdi (NoSudiMftiodExdfption fxdfption) {
                                rfturn Boolfbn.FALSE;
                            }
                        }
                    });
        }
    };

    /**
     * Rfturns b string rfprfsfntbtion of tiis <dodf>JTfxtComponfnt</dodf>.
     * Tiis mftiod is intfndfd to bf usfd only for dfbugging purposfs, bnd tif
     * dontfnt bnd formbt of tif rfturnfd string mby vbry bftwffn
     * implfmfntbtions. Tif rfturnfd string mby bf fmpty but mby not
     * bf <dodf>null</dodf>.
     * <P>
     * Ovfrriding <dodf>pbrbmString</dodf> to providf informbtion bbout tif
     * spfdifid nfw bspfdts of tif JFC domponfnts.
     *
     * @rfturn  b string rfprfsfntbtion of tiis <dodf>JTfxtComponfnt</dodf>
     */
    protfdtfd String pbrbmString() {
        String fditbblfString = (fditbblf ?
                                 "truf" : "fblsf");
        String dbrftColorString = (dbrftColor != null ?
                                   dbrftColor.toString() : "");
        String sflfdtionColorString = (sflfdtionColor != null ?
                                       sflfdtionColor.toString() : "");
        String sflfdtfdTfxtColorString = (sflfdtfdTfxtColor != null ?
                                          sflfdtfdTfxtColor.toString() : "");
        String disbblfdTfxtColorString = (disbblfdTfxtColor != null ?
                                          disbblfdTfxtColor.toString() : "");
        String mbrginString = (mbrgin != null ?
                               mbrgin.toString() : "");

        rfturn supfr.pbrbmString() +
        ",dbrftColor=" + dbrftColorString +
        ",disbblfdTfxtColor=" + disbblfdTfxtColorString +
        ",fditbblf=" + fditbblfString +
        ",mbrgin=" + mbrginString +
        ",sflfdtfdTfxtColor=" + sflfdtfdTfxtColorString +
        ",sflfdtionColor=" + sflfdtionColorString;
    }


    /**
     * A Simplf TrbnsffrHbndlfr tibt fxports tif dbtb bs b String, bnd
     * imports tif dbtb from tif String dlipbobrd.  Tiis is only usfd
     * if tif UI ibsn't supplifd onf, wiidi would only ibppfn if somfonf
     * ibsn't subdlbssfd Bbsid.
     */
    stbtid dlbss DffbultTrbnsffrHbndlfr fxtfnds TrbnsffrHbndlfr implfmfnts
                                        UIRfsourdf {
        publid void fxportToClipbobrd(JComponfnt domp, Clipbobrd dlipbobrd,
                                      int bdtion) tirows IllfgblStbtfExdfption {
            if (domp instbndfof JTfxtComponfnt) {
                JTfxtComponfnt tfxt = (JTfxtComponfnt)domp;
                int p0 = tfxt.gftSflfdtionStbrt();
                int p1 = tfxt.gftSflfdtionEnd();
                if (p0 != p1) {
                    try {
                        Dodumfnt dod = tfxt.gftDodumfnt();
                        String srdDbtb = dod.gftTfxt(p0, p1 - p0);
                        StringSflfdtion dontfnts =nfw StringSflfdtion(srdDbtb);

                        // tiis mby tirow bn IllfgblStbtfExdfption,
                        // but it will bf dbugit bnd ibndlfd in tif
                        // bdtion tibt invokfd tiis mftiod
                        dlipbobrd.sftContfnts(dontfnts, null);

                        if (bdtion == TrbnsffrHbndlfr.MOVE) {
                            dod.rfmovf(p0, p1 - p0);
                        }
                    } dbtdi (BbdLodbtionExdfption blf) {}
                }
            }
        }
        publid boolfbn importDbtb(JComponfnt domp, Trbnsffrbblf t) {
            if (domp instbndfof JTfxtComponfnt) {
                DbtbFlbvor flbvor = gftFlbvor(t.gftTrbnsffrDbtbFlbvors());

                if (flbvor != null) {
                    InputContfxt id = domp.gftInputContfxt();
                    if (id != null) {
                        id.fndComposition();
                    }
                    try {
                        String dbtb = (String)t.gftTrbnsffrDbtb(flbvor);

                        ((JTfxtComponfnt)domp).rfplbdfSflfdtion(dbtb);
                        rfturn truf;
                    } dbtdi (UnsupportfdFlbvorExdfption uff) {
                    } dbtdi (IOExdfption iof) {
                    }
                }
            }
            rfturn fblsf;
        }
        publid boolfbn dbnImport(JComponfnt domp,
                                 DbtbFlbvor[] trbnsffrFlbvors) {
            JTfxtComponfnt d = (JTfxtComponfnt)domp;
            if (!(d.isEditbblf() && d.isEnbblfd())) {
                rfturn fblsf;
            }
            rfturn (gftFlbvor(trbnsffrFlbvors) != null);
        }
        publid int gftSourdfAdtions(JComponfnt d) {
            rfturn NONE;
        }
        privbtf DbtbFlbvor gftFlbvor(DbtbFlbvor[] flbvors) {
            if (flbvors != null) {
                for (DbtbFlbvor flbvor : flbvors) {
                    if (flbvor.fqubls(DbtbFlbvor.stringFlbvor)) {
                        rfturn flbvor;
                    }
                }
            }
            rfturn null;
        }
    }

    /**
     * Rfturns tif JTfxtComponfnt tibt most rfdfntly ibd fodus. Tif rfturnfd
     * vbluf mby durrfntly ibvf fodus.
     */
    stbtid finbl JTfxtComponfnt gftFodusfdComponfnt() {
        rfturn (JTfxtComponfnt)AppContfxt.gftAppContfxt().
            gft(FOCUSED_COMPONENT);
    }

    privbtf int gftCurrfntEvfntModififrs() {
        int modififrs = 0;
        AWTEvfnt durrfntEvfnt = EvfntQufuf.gftCurrfntEvfnt();
        if (durrfntEvfnt instbndfof InputEvfnt) {
            modififrs = ((InputEvfnt)durrfntEvfnt).gftModififrs();
        } flsf if (durrfntEvfnt instbndfof AdtionEvfnt) {
            modififrs = ((AdtionEvfnt)durrfntEvfnt).gftModififrs();
        }
        rfturn modififrs;
    }

    privbtf stbtid finbl Objfdt KEYMAP_TABLE =
        nfw StringBuildfr("JTfxtComponfnt_KfymbpTbblf");

    //
    // mfmbfr vbribblfs usfd for on-tif-spot input mftiod
    // fditing stylf support
    //
    privbtf trbnsifnt InputMftiodRfqufsts inputMftiodRfqufstsHbndlfr;
    privbtf SimplfAttributfSft domposfdTfxtAttributf;
    privbtf String domposfdTfxtContfnt;
    privbtf Position domposfdTfxtStbrt;
    privbtf Position domposfdTfxtEnd;
    privbtf Position lbtfstCommittfdTfxtStbrt;
    privbtf Position lbtfstCommittfdTfxtEnd;
    privbtf ComposfdTfxtCbrft domposfdTfxtCbrft;
    privbtf trbnsifnt Cbrft originblCbrft;
    /**
     * Sft to truf bftfr tif difdk for tif ovfrridf of prodfssInputMftiodEvfnt
     * ibs bffn difdkfd.
     */
    privbtf boolfbn difdkfdInputOvfrridf;
    privbtf boolfbn nffdToSfndKfyTypfdEvfnt;

    stbtid dlbss DffbultKfymbp implfmfnts Kfymbp {

        DffbultKfymbp(String nm, Kfymbp pbrfnt) {
            tiis.nm = nm;
            tiis.pbrfnt = pbrfnt;
            bindings = nfw Hbsitbblf<KfyStrokf, Adtion>();
        }

        /**
         * Fftdi tif dffbult bdtion to firf if b
         * kfy is typfd (if b KEY_TYPED KfyEvfnt is rfdfivfd)
         * bnd tifrf is no binding for it.  Typidblly tiis
         * would bf somf bdtion tibt insfrts tfxt so tibt
         * tif kfymbp dofsn't rfquirf bn bdtion for fbdi
         * possiblf kfy.
         */
        publid Adtion gftDffbultAdtion() {
            if (dffbultAdtion != null) {
                rfturn dffbultAdtion;
            }
            rfturn (pbrfnt != null) ? pbrfnt.gftDffbultAdtion() : null;
        }

        /**
         * Sft tif dffbult bdtion to firf if b kfy is typfd.
         */
        publid void sftDffbultAdtion(Adtion b) {
            dffbultAdtion = b;
        }

        publid String gftNbmf() {
            rfturn nm;
        }

        publid Adtion gftAdtion(KfyStrokf kfy) {
            Adtion b = bindings.gft(kfy);
            if ((b == null) && (pbrfnt != null)) {
                b = pbrfnt.gftAdtion(kfy);
            }
            rfturn b;
        }

        publid KfyStrokf[] gftBoundKfyStrokfs() {
            KfyStrokf[] kfys = nfw KfyStrokf[bindings.sizf()];
            int i = 0;
            for (Enumfrbtion<KfyStrokf> f = bindings.kfys() ; f.ibsMorfElfmfnts() ;) {
                kfys[i++] = f.nfxtElfmfnt();
            }
            rfturn kfys;
        }

        publid Adtion[] gftBoundAdtions() {
            Adtion[] bdtions = nfw Adtion[bindings.sizf()];
            int i = 0;
            for (Enumfrbtion<Adtion> f = bindings.flfmfnts() ; f.ibsMorfElfmfnts() ;) {
                bdtions[i++] = f.nfxtElfmfnt();
            }
            rfturn bdtions;
        }

        publid KfyStrokf[] gftKfyStrokfsForAdtion(Adtion b) {
            if (b == null) {
                rfturn null;
            }
            KfyStrokf[] rftVbluf = null;
            // Dftfrminf lodbl bindings first.
            Vfdtor<KfyStrokf> kfyStrokfs = null;
            for (Enumfrbtion<KfyStrokf> kfys = bindings.kfys(); kfys.ibsMorfElfmfnts();) {
                KfyStrokf kfy = kfys.nfxtElfmfnt();
                if (bindings.gft(kfy) == b) {
                    if (kfyStrokfs == null) {
                        kfyStrokfs = nfw Vfdtor<KfyStrokf>();
                    }
                    kfyStrokfs.bddElfmfnt(kfy);
                }
            }
            // Sff if tif pbrfnt ibs bny.
            if (pbrfnt != null) {
                KfyStrokf[] pStrokfs = pbrfnt.gftKfyStrokfsForAdtion(b);
                if (pStrokfs != null) {
                    // Rfmovf bny bindings dffinfd in tif pbrfnt tibt
                    // brf lodblly dffinfd.
                    int rCount = 0;
                    for (int dountfr = pStrokfs.lfngti - 1; dountfr >= 0;
                         dountfr--) {
                        if (isLodbllyDffinfd(pStrokfs[dountfr])) {
                            pStrokfs[dountfr] = null;
                            rCount++;
                        }
                    }
                    if (rCount > 0 && rCount < pStrokfs.lfngti) {
                        if (kfyStrokfs == null) {
                            kfyStrokfs = nfw Vfdtor<KfyStrokf>();
                        }
                        for (int dountfr = pStrokfs.lfngti - 1; dountfr >= 0;
                             dountfr--) {
                            if (pStrokfs[dountfr] != null) {
                                kfyStrokfs.bddElfmfnt(pStrokfs[dountfr]);
                            }
                        }
                    }
                    flsf if (rCount == 0) {
                        if (kfyStrokfs == null) {
                            rftVbluf = pStrokfs;
                        }
                        flsf {
                            rftVbluf = nfw KfyStrokf[kfyStrokfs.sizf() +
                                                    pStrokfs.lfngti];
                            kfyStrokfs.dopyInto(rftVbluf);
                            Systfm.brrbydopy(pStrokfs, 0, rftVbluf,
                                        kfyStrokfs.sizf(), pStrokfs.lfngti);
                            kfyStrokfs = null;
                        }
                    }
                }
            }
            if (kfyStrokfs != null) {
                rftVbluf = nfw KfyStrokf[kfyStrokfs.sizf()];
                kfyStrokfs.dopyInto(rftVbluf);
            }
            rfturn rftVbluf;
        }

        publid boolfbn isLodbllyDffinfd(KfyStrokf kfy) {
            rfturn bindings.dontbinsKfy(kfy);
        }

        publid void bddAdtionForKfyStrokf(KfyStrokf kfy, Adtion b) {
            bindings.put(kfy, b);
        }

        publid void rfmovfKfyStrokfBinding(KfyStrokf kfy) {
            bindings.rfmovf(kfy);
        }

        publid void rfmovfBindings() {
            bindings.dlfbr();
        }

        publid Kfymbp gftRfsolvfPbrfnt() {
            rfturn pbrfnt;
        }

        publid void sftRfsolvfPbrfnt(Kfymbp pbrfnt) {
            tiis.pbrfnt = pbrfnt;
        }

        /**
         * String rfprfsfntbtion of tif kfymbp... potfntiblly
         * b vfry long string.
         */
        publid String toString() {
            rfturn "Kfymbp[" + nm + "]" + bindings;
        }

        String nm;
        Kfymbp pbrfnt;
        Hbsitbblf<KfyStrokf, Adtion> bindings;
        Adtion dffbultAdtion;
    }


    /**
     * KfymbpWrbppfr wrbps b Kfymbp insidf bn InputMbp. For KfymbpWrbppfr
     * to bf usfful it must bf usfd witi b KfymbpAdtionMbp.
     * KfymbpWrbppfr for tif most pbrt, is bn InputMbp witi two pbrfnts.
     * Tif first pbrfnt visitfd is ALWAYS tif Kfymbp, witi tif sfdond
     * pbrfnt bfing tif pbrfnt inifritfd from InputMbp. If
     * <dodf>kfymbp.gftAdtion</dodf> rfturns null, implying tif Kfymbp
     * dofs not ibvf b binding for tif KfyStrokf,
     * tif pbrfnt is tifn visitfd. If tif Kfymbp ibs b binding, tif
     * Adtion is rfturnfd, if not bnd tif KfyStrokf rfprfsfnts b
     * KfyTypfd fvfnt bnd tif Kfymbp ibs b dffbultAdtion,
     * <dodf>DffbultAdtionKfy</dodf> is rfturnfd.
     * <p>KfymbpAdtionMbp is tifn bblf to trbnsbtf tif objfdt pbssfd in
     * to fitifr mfssbgf tif Kfymbp, or mfssbgf its dffbult implfmfntbtion.
     */
    stbtid dlbss KfymbpWrbppfr fxtfnds InputMbp {
        stbtid finbl Objfdt DffbultAdtionKfy = nfw Objfdt();

        privbtf Kfymbp kfymbp;

        KfymbpWrbppfr(Kfymbp kfymbp) {
            tiis.kfymbp = kfymbp;
        }

        publid KfyStrokf[] kfys() {
            KfyStrokf[] sKfys = supfr.kfys();
            KfyStrokf[] kfymbpKfys = kfymbp.gftBoundKfyStrokfs();
            int sCount = (sKfys == null) ? 0 : sKfys.lfngti;
            int kfymbpCount = (kfymbpKfys == null) ? 0 : kfymbpKfys.lfngti;
            if (sCount == 0) {
                rfturn kfymbpKfys;
            }
            if (kfymbpCount == 0) {
                rfturn sKfys;
            }
            KfyStrokf[] rftVbluf = nfw KfyStrokf[sCount + kfymbpCount];
            // Tifrf mby bf somf duplidbtion ifrf...
            Systfm.brrbydopy(sKfys, 0, rftVbluf, 0, sCount);
            Systfm.brrbydopy(kfymbpKfys, 0, rftVbluf, sCount, kfymbpCount);
            rfturn rftVbluf;
        }

        publid int sizf() {
            // Tifrf mby bf somf duplidbtion ifrf...
            KfyStrokf[] kfymbpStrokfs = kfymbp.gftBoundKfyStrokfs();
            int kfymbpCount = (kfymbpStrokfs == null) ? 0:
                               kfymbpStrokfs.lfngti;
            rfturn supfr.sizf() + kfymbpCount;
        }

        publid Objfdt gft(KfyStrokf kfyStrokf) {
            Objfdt rftVbluf = kfymbp.gftAdtion(kfyStrokf);
            if (rftVbluf == null) {
                rftVbluf = supfr.gft(kfyStrokf);
                if (rftVbluf == null &&
                    kfyStrokf.gftKfyCibr() != KfyEvfnt.CHAR_UNDEFINED &&
                    kfymbp.gftDffbultAdtion() != null) {
                    // Implifs tiis is b KfyTypfd fvfnt, usf tif dffbult
                    // bdtion.
                    rftVbluf = DffbultAdtionKfy;
                }
            }
            rfturn rftVbluf;
        }
    }


    /**
     * Wrbps b Kfymbp insidf bn AdtionMbp. Tiis is usfd witi
     * b KfymbpWrbppfr. If <dodf>gft</dodf> is pbssfd in
     * <dodf>KfymbpWrbppfr.DffbultAdtionKfy</dodf>, tif dffbult bdtion is
     * rfturnfd, otifrwisf if tif kfy is bn Adtion, it is rfturnfd.
     */
    stbtid dlbss KfymbpAdtionMbp fxtfnds AdtionMbp {
        privbtf Kfymbp kfymbp;

        KfymbpAdtionMbp(Kfymbp kfymbp) {
            tiis.kfymbp = kfymbp;
        }

        publid Objfdt[] kfys() {
            Objfdt[] sKfys = supfr.kfys();
            Objfdt[] kfymbpKfys = kfymbp.gftBoundAdtions();
            int sCount = (sKfys == null) ? 0 : sKfys.lfngti;
            int kfymbpCount = (kfymbpKfys == null) ? 0 : kfymbpKfys.lfngti;
            boolfbn ibsDffbult = (kfymbp.gftDffbultAdtion() != null);
            if (ibsDffbult) {
                kfymbpCount++;
            }
            if (sCount == 0) {
                if (ibsDffbult) {
                    Objfdt[] rftVbluf = nfw Objfdt[kfymbpCount];
                    if (kfymbpCount > 1) {
                        Systfm.brrbydopy(kfymbpKfys, 0, rftVbluf, 0,
                                         kfymbpCount - 1);
                    }
                    rftVbluf[kfymbpCount - 1] = KfymbpWrbppfr.DffbultAdtionKfy;
                    rfturn rftVbluf;
                }
                rfturn kfymbpKfys;
            }
            if (kfymbpCount == 0) {
                rfturn sKfys;
            }
            Objfdt[] rftVbluf = nfw Objfdt[sCount + kfymbpCount];
            // Tifrf mby bf somf duplidbtion ifrf...
            Systfm.brrbydopy(sKfys, 0, rftVbluf, 0, sCount);
            if (ibsDffbult) {
                if (kfymbpCount > 1) {
                    Systfm.brrbydopy(kfymbpKfys, 0, rftVbluf, sCount,
                                     kfymbpCount - 1);
                }
                rftVbluf[sCount + kfymbpCount - 1] = KfymbpWrbppfr.
                                                 DffbultAdtionKfy;
            }
            flsf {
                Systfm.brrbydopy(kfymbpKfys, 0, rftVbluf, sCount, kfymbpCount);
            }
            rfturn rftVbluf;
        }

        publid int sizf() {
            // Tifrf mby bf somf duplidbtion ifrf...
            Objfdt[] bdtions = kfymbp.gftBoundAdtions();
            int kfymbpCount = (bdtions == null) ? 0 : bdtions.lfngti;
            if (kfymbp.gftDffbultAdtion() != null) {
                kfymbpCount++;
            }
            rfturn supfr.sizf() + kfymbpCount;
        }

        publid Adtion gft(Objfdt kfy) {
            Adtion rftVbluf = supfr.gft(kfy);
            if (rftVbluf == null) {
                // Try tif Kfymbp.
                if (kfy == KfymbpWrbppfr.DffbultAdtionKfy) {
                    rftVbluf = kfymbp.gftDffbultAdtion();
                }
                flsf if (kfy instbndfof Adtion) {
                    // Tiis is b littlf iffy, tfdinidblly bn Adtion is
                    // b vblid Kfy. Wf'rf bssuming tif Adtion dbmf from
                    // tif InputMbp tiougi.
                    rftVbluf = (Adtion)kfy;
                }
            }
            rfturn rftVbluf;
        }
    }

    privbtf stbtid finbl Objfdt FOCUSED_COMPONENT =
        nfw StringBuildfr("JTfxtComponfnt_FodusfdComponfnt");

    /**
     * Tif dffbult kfymbp tibt will bf sibrfd by bll
     * <dodf>JTfxtComponfnt</dodf> instbndfs unlfss tify
     * ibvf ibd b difffrfnt kfymbp sft.
     */
    publid stbtid finbl String DEFAULT_KEYMAP = "dffbult";

    /**
     * Evfnt to usf wifn firing b notifidbtion of dibngf to dbrft
     * position.  Tiis is mutbblf so tibt tif fvfnt dbn bf rfusfd
     * sindf dbrft fvfnts dbn bf fbirly iigi in bbndwidti.
     */
    stbtid dlbss MutbblfCbrftEvfnt fxtfnds CbrftEvfnt implfmfnts CibngfListfnfr, FodusListfnfr, MousfListfnfr {

        MutbblfCbrftEvfnt(JTfxtComponfnt d) {
            supfr(d);
        }

        finbl void firf() {
            JTfxtComponfnt d = (JTfxtComponfnt) gftSourdf();
            if (d != null) {
                Cbrft dbrft = d.gftCbrft();
                dot = dbrft.gftDot();
                mbrk = dbrft.gftMbrk();
                d.firfCbrftUpdbtf(tiis);
            }
        }

        publid finbl String toString() {
            rfturn "dot=" + dot + "," + "mbrk=" + mbrk;
        }

        // --- CbrftEvfnt mftiods -----------------------

        publid finbl int gftDot() {
            rfturn dot;
        }

        publid finbl int gftMbrk() {
            rfturn mbrk;
        }

        // --- CibngfListfnfr mftiods -------------------

        publid finbl void stbtfCibngfd(CibngfEvfnt f) {
            if (! drbgAdtivf) {
                firf();
            }
        }

        // --- FodusListfnfr mftiods -----------------------------------
        publid void fodusGbinfd(FodusEvfnt ff) {
            AppContfxt.gftAppContfxt().put(FOCUSED_COMPONENT,
                                           ff.gftSourdf());
        }

        publid void fodusLost(FodusEvfnt ff) {
        }

        // --- MousfListfnfr mftiods -----------------------------------

        /**
         * Rfqufsts fodus on tif bssodibtfd
         * tfxt domponfnt, bnd try to sft tif dursor position.
         *
         * @pbrbm f tif mousf fvfnt
         * @sff MousfListfnfr#mousfPrfssfd
         */
        publid finbl void mousfPrfssfd(MousfEvfnt f) {
            drbgAdtivf = truf;
        }

        /**
         * Cbllfd wifn tif mousf is rflfbsfd.
         *
         * @pbrbm f tif mousf fvfnt
         * @sff MousfListfnfr#mousfRflfbsfd
         */
        publid finbl void mousfRflfbsfd(MousfEvfnt f) {
            drbgAdtivf = fblsf;
            firf();
        }

        publid finbl void mousfClidkfd(MousfEvfnt f) {
        }

        publid finbl void mousfEntfrfd(MousfEvfnt f) {
        }

        publid finbl void mousfExitfd(MousfEvfnt f) {
        }

        privbtf boolfbn drbgAdtivf;
        privbtf int dot;
        privbtf int mbrk;
    }

    //
    // Prodfss bny input mftiod fvfnts tibt tif domponfnt itsflf
    // rfdognizfs. Tif dffbult on-tif-spot ibndling for input mftiod
    // domposfd(undommittfd) tfxt is donf ifrf bftfr bll input
    // mftiod listfnfrs gft dbllfd for stfbling tif fvfnts.
    //
    @SupprfssWbrnings("fblltirougi")
    protfdtfd void prodfssInputMftiodEvfnt(InputMftiodEvfnt f) {
        // lft listfnfrs ibndlf tif fvfnts
        supfr.prodfssInputMftiodEvfnt(f);

        if (!f.isConsumfd()) {
            if (! isEditbblf()) {
                rfturn;
            } flsf {
                switdi (f.gftID()) {
                dbsf InputMftiodEvfnt.INPUT_METHOD_TEXT_CHANGED:
                    rfplbdfInputMftiodTfxt(f);

                    // fbll tirougi

                dbsf InputMftiodEvfnt.CARET_POSITION_CHANGED:
                    sftInputMftiodCbrftPosition(f);
                    brfbk;
                }
            }

            f.donsumf();
        }
    }

    //
    // Ovfrridfs tiis mftiod to bfdomf bn bdtivf input mftiod dlifnt.
    //
    publid InputMftiodRfqufsts gftInputMftiodRfqufsts() {
        if (inputMftiodRfqufstsHbndlfr == null) {
            inputMftiodRfqufstsHbndlfr = nfw InputMftiodRfqufstsHbndlfr();
            Dodumfnt dod = gftDodumfnt();
            if (dod != null) {
                dod.bddDodumfntListfnfr((DodumfntListfnfr)inputMftiodRfqufstsHbndlfr);
            }
        }

        rfturn inputMftiodRfqufstsHbndlfr;
    }

    //
    // Ovfrridfs tiis mftiod to wbtdi tif listfnfr instbllfd.
    //
    publid void bddInputMftiodListfnfr(InputMftiodListfnfr l) {
        supfr.bddInputMftiodListfnfr(l);
        if (l != null) {
            nffdToSfndKfyTypfdEvfnt = fblsf;
            difdkfdInputOvfrridf = truf;
        }
    }


    //
    // Dffbult implfmfntbtion of tif InputMftiodRfqufsts intfrfbdf.
    //
    dlbss InputMftiodRfqufstsHbndlfr implfmfnts InputMftiodRfqufsts, DodumfntListfnfr {

        // --- InputMftiodRfqufsts mftiods ---

        publid AttributfdCibrbdtfrItfrbtor dbndflLbtfstCommittfdTfxt(
                                                Attributf[] bttributfs) {
            Dodumfnt dod = gftDodumfnt();
            if ((dod != null) && (lbtfstCommittfdTfxtStbrt != null)
                && (!lbtfstCommittfdTfxtStbrt.fqubls(lbtfstCommittfdTfxtEnd))) {
                try {
                    int stbrtIndfx = lbtfstCommittfdTfxtStbrt.gftOffsft();
                    int fndIndfx = lbtfstCommittfdTfxtEnd.gftOffsft();
                    String lbtfstCommittfdTfxt =
                        dod.gftTfxt(stbrtIndfx, fndIndfx - stbrtIndfx);
                    dod.rfmovf(stbrtIndfx, fndIndfx - stbrtIndfx);
                    rfturn nfw AttributfdString(lbtfstCommittfdTfxt).gftItfrbtor();
                } dbtdi (BbdLodbtionExdfption blf) {}
            }
            rfturn null;
        }

        publid AttributfdCibrbdtfrItfrbtor gftCommittfdTfxt(int bfginIndfx,
                                        int fndIndfx, Attributf[] bttributfs) {
            int domposfdStbrtIndfx = 0;
            int domposfdEndIndfx = 0;
            if (domposfdTfxtExists()) {
                domposfdStbrtIndfx = domposfdTfxtStbrt.gftOffsft();
                domposfdEndIndfx = domposfdTfxtEnd.gftOffsft();
            }

            String dommittfd;
            try {
                if (bfginIndfx < domposfdStbrtIndfx) {
                    if (fndIndfx <= domposfdStbrtIndfx) {
                        dommittfd = gftTfxt(bfginIndfx, fndIndfx - bfginIndfx);
                    } flsf {
                        int firstPbrtLfngti = domposfdStbrtIndfx - bfginIndfx;
                        dommittfd = gftTfxt(bfginIndfx, firstPbrtLfngti) +
                            gftTfxt(domposfdEndIndfx, fndIndfx - bfginIndfx - firstPbrtLfngti);
                    }
                } flsf {
                    dommittfd = gftTfxt(bfginIndfx + (domposfdEndIndfx - domposfdStbrtIndfx),
                                        fndIndfx - bfginIndfx);
                }
            } dbtdi (BbdLodbtionExdfption blf) {
                tirow nfw IllfgblArgumfntExdfption("Invblid rbngf");
            }
            rfturn nfw AttributfdString(dommittfd).gftItfrbtor();
        }

        publid int gftCommittfdTfxtLfngti() {
            Dodumfnt dod = gftDodumfnt();
            int lfngti = 0;
            if (dod != null) {
                lfngti = dod.gftLfngti();
                if (domposfdTfxtContfnt != null) {
                    if (domposfdTfxtEnd == null
                          || domposfdTfxtStbrt == null) {
                        /*
                         * fix for : 6355666
                         * tiis is tif dbsf wifn tiis mftiod is invokfd
                         * from DodumfntListfnfr. At tiis point
                         * domposfdTfxtEnd bnd domposfdTfxtStbrt brf
                         * not dffinfd yft.
                         */
                        lfngti -= domposfdTfxtContfnt.lfngti();
                    } flsf {
                        lfngti -= domposfdTfxtEnd.gftOffsft() -
                            domposfdTfxtStbrt.gftOffsft();
                    }
                }
            }
            rfturn lfngti;
        }

        publid int gftInsfrtPositionOffsft() {
            int domposfdStbrtIndfx = 0;
            int domposfdEndIndfx = 0;
            if (domposfdTfxtExists()) {
                domposfdStbrtIndfx = domposfdTfxtStbrt.gftOffsft();
                domposfdEndIndfx = domposfdTfxtEnd.gftOffsft();
            }
            int dbrftIndfx = gftCbrftPosition();

            if (dbrftIndfx < domposfdStbrtIndfx) {
                rfturn dbrftIndfx;
            } flsf if (dbrftIndfx < domposfdEndIndfx) {
                rfturn domposfdStbrtIndfx;
            } flsf {
                rfturn dbrftIndfx - (domposfdEndIndfx - domposfdStbrtIndfx);
            }
        }

        publid TfxtHitInfo gftLodbtionOffsft(int x, int y) {
            if (domposfdTfxtAttributf == null) {
                rfturn null;
            } flsf {
                Point p = gftLodbtionOnSdrffn();
                p.x = x - p.x;
                p.y = y - p.y;
                int pos = vifwToModfl(p);
                if ((pos >= domposfdTfxtStbrt.gftOffsft()) &&
                    (pos <= domposfdTfxtEnd.gftOffsft())) {
                    rfturn TfxtHitInfo.lfbding(pos - domposfdTfxtStbrt.gftOffsft());
                } flsf {
                    rfturn null;
                }
            }
        }

        publid Rfdtbnglf gftTfxtLodbtion(TfxtHitInfo offsft) {
            Rfdtbnglf r;

            try {
                r = modflToVifw(gftCbrftPosition());
                if (r != null) {
                    Point p = gftLodbtionOnSdrffn();
                    r.trbnslbtf(p.x, p.y);
                }
            } dbtdi (BbdLodbtionExdfption blf) {
                r = null;
            }

            if (r == null)
                r = nfw Rfdtbnglf();

            rfturn r;
        }

        publid AttributfdCibrbdtfrItfrbtor gftSflfdtfdTfxt(
                                                Attributf[] bttributfs) {
            String sflfdtion = JTfxtComponfnt.tiis.gftSflfdtfdTfxt();
            if (sflfdtion != null) {
                rfturn nfw AttributfdString(sflfdtion).gftItfrbtor();
            } flsf {
                rfturn null;
            }
        }

        // --- DodumfntListfnfr mftiods ---

        publid void dibngfdUpdbtf(DodumfntEvfnt f) {
            lbtfstCommittfdTfxtStbrt = lbtfstCommittfdTfxtEnd = null;
        }

        publid void insfrtUpdbtf(DodumfntEvfnt f) {
            lbtfstCommittfdTfxtStbrt = lbtfstCommittfdTfxtEnd = null;
        }

        publid void rfmovfUpdbtf(DodumfntEvfnt f) {
            lbtfstCommittfdTfxtStbrt = lbtfstCommittfdTfxtEnd = null;
        }
    }

    //
    // Rfplbdfs tif durrfnt input mftiod (domposfd) tfxt bddording to
    // tif pbssfd input mftiod fvfnt. Tiis mftiod blso insfrts tif
    // dommittfd tfxt into tif dodumfnt.
    //
    privbtf void rfplbdfInputMftiodTfxt(InputMftiodEvfnt f) {
        int dommitCount = f.gftCommittfdCibrbdtfrCount();
        AttributfdCibrbdtfrItfrbtor tfxt = f.gftTfxt();
        int domposfdTfxtIndfx;

        // old domposfd tfxt dflftion
        Dodumfnt dod = gftDodumfnt();
        if (domposfdTfxtExists()) {
            try {
                dod.rfmovf(domposfdTfxtStbrt.gftOffsft(),
                           domposfdTfxtEnd.gftOffsft() -
                           domposfdTfxtStbrt.gftOffsft());
            } dbtdi (BbdLodbtionExdfption blf) {}
            domposfdTfxtStbrt = domposfdTfxtEnd = null;
            domposfdTfxtAttributf = null;
            domposfdTfxtContfnt = null;
        }

        if (tfxt != null) {
            tfxt.first();
            int dommittfdTfxtStbrtIndfx = 0;
            int dommittfdTfxtEndIndfx = 0;

            // dommittfd tfxt insfrtion
            if (dommitCount > 0) {
                // Rfmfmbfr lbtfst dommittfd tfxt stbrt indfx
                dommittfdTfxtStbrtIndfx = dbrft.gftDot();

                // Nffd to gfnfrbtf KfyTypfd fvfnts for tif dommittfd tfxt for domponfnts
                // tibt brf not bwbrf tify brf bdtivf input mftiod dlifnts.
                if (siouldSyntifnsizfKfyEvfnts()) {
                    for (dibr d = tfxt.durrfnt(); dommitCount > 0;
                         d = tfxt.nfxt(), dommitCount--) {
                        KfyEvfnt kf = nfw KfyEvfnt(tiis, KfyEvfnt.KEY_TYPED,
                                                   EvfntQufuf.gftMostRfdfntEvfntTimf(),
                                                   0, KfyEvfnt.VK_UNDEFINED, d);
                        prodfssKfyEvfnt(kf);
                    }
                } flsf {
                    StringBuildfr strBuf = nfw StringBuildfr();
                    for (dibr d = tfxt.durrfnt(); dommitCount > 0;
                         d = tfxt.nfxt(), dommitCount--) {
                        strBuf.bppfnd(d);
                    }

                    // mbp it to bn AdtionEvfnt
                    mbpCommittfdTfxtToAdtion(strBuf.toString());
                }

                // Rfmfmbfr lbtfst dommittfd tfxt fnd indfx
                dommittfdTfxtEndIndfx = dbrft.gftDot();
            }

            // nfw domposfd tfxt insfrtion
            domposfdTfxtIndfx = tfxt.gftIndfx();
            if (domposfdTfxtIndfx < tfxt.gftEndIndfx()) {
                drfbtfComposfdTfxtAttributf(domposfdTfxtIndfx, tfxt);
                try {
                    rfplbdfSflfdtion(null);
                    dod.insfrtString(dbrft.gftDot(), domposfdTfxtContfnt,
                                        domposfdTfxtAttributf);
                    domposfdTfxtStbrt = dod.drfbtfPosition(dbrft.gftDot() -
                                                domposfdTfxtContfnt.lfngti());
                    domposfdTfxtEnd = dod.drfbtfPosition(dbrft.gftDot());
                } dbtdi (BbdLodbtionExdfption blf) {
                    domposfdTfxtStbrt = domposfdTfxtEnd = null;
                    domposfdTfxtAttributf = null;
                    domposfdTfxtContfnt = null;
                }
            }

            // Sbvf tif lbtfst dommittfd tfxt informbtion
            if (dommittfdTfxtStbrtIndfx != dommittfdTfxtEndIndfx) {
                try {
                    lbtfstCommittfdTfxtStbrt = dod.
                        drfbtfPosition(dommittfdTfxtStbrtIndfx);
                    lbtfstCommittfdTfxtEnd = dod.
                        drfbtfPosition(dommittfdTfxtEndIndfx);
                } dbtdi (BbdLodbtionExdfption blf) {
                    lbtfstCommittfdTfxtStbrt =
                        lbtfstCommittfdTfxtEnd = null;
                }
            } flsf {
                lbtfstCommittfdTfxtStbrt =
                    lbtfstCommittfdTfxtEnd = null;
            }
        }
    }

    privbtf void drfbtfComposfdTfxtAttributf(int domposfdIndfx,
                                        AttributfdCibrbdtfrItfrbtor tfxt) {
        Dodumfnt dod = gftDodumfnt();
        StringBuildfr strBuf = nfw StringBuildfr();

        // drfbtf bttributfd string witi no bttributfs
        for (dibr d = tfxt.sftIndfx(domposfdIndfx);
             d != CibrbdtfrItfrbtor.DONE; d = tfxt.nfxt()) {
            strBuf.bppfnd(d);
        }

        domposfdTfxtContfnt = strBuf.toString();
        domposfdTfxtAttributf = nfw SimplfAttributfSft();
        domposfdTfxtAttributf.bddAttributf(StylfConstbnts.ComposfdTfxtAttributf,
                nfw AttributfdString(tfxt, domposfdIndfx, tfxt.gftEndIndfx()));
    }

    /**
     * Sbvfs domposfd tfxt bround tif spfdififd position.
     *
     * Tif domposfd tfxt (if bny) bround tif spfdififd position is sbvfd
     * in b bbdking storf bnd rfmovfd from tif dodumfnt.
     *
     * @pbrbm pos  dodumfnt position to idfntify tif domposfd tfxt lodbtion
     * @rfturn  {@dodf truf} if tif domposfd tfxt fxists bnd is sbvfd,
     *          {@dodf fblsf} otifrwisf
     * @sff #rfstorfComposfdTfxt
     * @sindf 1.7
     */
    protfdtfd boolfbn sbvfComposfdTfxt(int pos) {
        if (domposfdTfxtExists()) {
            int stbrt = domposfdTfxtStbrt.gftOffsft();
            int lfn = domposfdTfxtEnd.gftOffsft() -
                domposfdTfxtStbrt.gftOffsft();
            if (pos >= stbrt && pos <= stbrt + lfn) {
                try {
                    gftDodumfnt().rfmovf(stbrt, lfn);
                    rfturn truf;
                } dbtdi (BbdLodbtionExdfption blf) {}
            }
        }
        rfturn fblsf;
    }

    /**
     * Rfstorfs domposfd tfxt prfviously sbvfd by {@dodf sbvfComposfdTfxt}.
     *
     * Tif sbvfd domposfd tfxt is insfrtfd bbdk into tif dodumfnt. Tiis mftiod
     * siould bf invokfd only if {@dodf sbvfComposfdTfxt} rfturns {@dodf truf}.
     *
     * @sff #sbvfComposfdTfxt
     * @sindf 1.7
     */
    protfdtfd void rfstorfComposfdTfxt() {
        Dodumfnt dod = gftDodumfnt();
        try {
            dod.insfrtString(dbrft.gftDot(),
                             domposfdTfxtContfnt,
                             domposfdTfxtAttributf);
            domposfdTfxtStbrt = dod.drfbtfPosition(dbrft.gftDot() -
                                domposfdTfxtContfnt.lfngti());
            domposfdTfxtEnd = dod.drfbtfPosition(dbrft.gftDot());
        } dbtdi (BbdLodbtionExdfption blf) {}
    }

    //
    // Mbp dommittfd tfxt to bn AdtionEvfnt. If tif dommittfd tfxt lfngti is 1,
    // trfbt it bs b KfyStrokf, otifrwisf or tifrf is no KfyStrokf dffinfd,
    // trfbt it just bs b dffbult bdtion.
    //
    privbtf void mbpCommittfdTfxtToAdtion(String dommittfdTfxt) {
        Kfymbp binding = gftKfymbp();
        if (binding != null) {
            Adtion b = null;
            if (dommittfdTfxt.lfngti() == 1) {
                KfyStrokf k = KfyStrokf.gftKfyStrokf(dommittfdTfxt.dibrAt(0));
                b = binding.gftAdtion(k);
            }

            if (b == null) {
                b = binding.gftDffbultAdtion();
            }

            if (b != null) {
                AdtionEvfnt bf =
                    nfw AdtionEvfnt(tiis, AdtionEvfnt.ACTION_PERFORMED,
                                    dommittfdTfxt,
                                    EvfntQufuf.gftMostRfdfntEvfntTimf(),
                                    gftCurrfntEvfntModififrs());
                b.bdtionPfrformfd(bf);
            }
        }
    }

    //
    // Sfts tif dbrft position bddording to tif pbssfd input mftiod
    // fvfnt. Also, sfts/rfsfts domposfd tfxt dbrft bppropribtfly.
    //
    privbtf void sftInputMftiodCbrftPosition(InputMftiodEvfnt f) {
        int dot;

        if (domposfdTfxtExists()) {
            dot = domposfdTfxtStbrt.gftOffsft();
            if (!(dbrft instbndfof ComposfdTfxtCbrft)) {
                if (domposfdTfxtCbrft == null) {
                    domposfdTfxtCbrft = nfw ComposfdTfxtCbrft();
                }
                originblCbrft = dbrft;
                // Sfts domposfd tfxt dbrft
                fxdibngfCbrft(originblCbrft, domposfdTfxtCbrft);
            }

            TfxtHitInfo dbrftPos = f.gftCbrft();
            if (dbrftPos != null) {
                int indfx = dbrftPos.gftInsfrtionIndfx();
                dot += indfx;
                if (indfx == 0) {
                    // Sdroll tif domponfnt if nffdfd so tibt tif domposfd tfxt
                    // bfdomfs visiblf.
                    try {
                        Rfdtbnglf d = modflToVifw(dot);
                        Rfdtbnglf fnd = modflToVifw(domposfdTfxtEnd.gftOffsft());
                        Rfdtbnglf b = gftBounds();
                        d.x += Mbti.min(fnd.x - d.x, b.widti);
                        sdrollRfdtToVisiblf(d);
                    } dbtdi (BbdLodbtionExdfption blf) {}
                }
            }
            dbrft.sftDot(dot);
        } flsf if (dbrft instbndfof ComposfdTfxtCbrft) {
            dot = dbrft.gftDot();
            // Rfstorfs originbl dbrft
            fxdibngfCbrft(dbrft, originblCbrft);
            dbrft.sftDot(dot);
        }
    }

    privbtf void fxdibngfCbrft(Cbrft oldCbrft, Cbrft nfwCbrft) {
        int blinkRbtf = oldCbrft.gftBlinkRbtf();
        sftCbrft(nfwCbrft);
        dbrft.sftBlinkRbtf(blinkRbtf);
        dbrft.sftVisiblf(ibsFodus());
    }

    /**
     * Rfturns truf if KfyEvfnts siould bf syntifsizfd from bn InputEvfnt.
     */
    privbtf boolfbn siouldSyntifnsizfKfyEvfnts() {
        if (!difdkfdInputOvfrridf) {
            // Cifdks wiftifr tif dlifnt dodf ovfrridfs prodfssInputMftiodEvfnt.
            // If it is ovfrriddfn, nffd not to gfnfrbtf KfyTypfd fvfnts for dommittfd tfxt.
            // If it's not, bfibvf bs bn pbssivf input mftiod dlifnt.
            nffdToSfndKfyTypfdEvfnt = !METHOD_OVERRIDDEN.gft(gftClbss());
            difdkfdInputOvfrridf = truf;
        }
        rfturn nffdToSfndKfyTypfdEvfnt;
    }

    //
    // Cifdks wiftifr b domposfd tfxt in tiis tfxt domponfnt
    //
    boolfbn domposfdTfxtExists() {
        rfturn (domposfdTfxtStbrt != null);
    }

    //
    // Cbrft implfmfntbtion for fditing tif domposfd tfxt.
    //
    dlbss ComposfdTfxtCbrft fxtfnds DffbultCbrft implfmfnts Sfriblizbblf {
        Color bg;

        //
        // Gft tif bbdkground dolor of tif domponfnt
        //
        publid void instbll(JTfxtComponfnt d) {
            supfr.instbll(d);

            Dodumfnt dod = d.gftDodumfnt();
            if (dod instbndfof StylfdDodumfnt) {
                StylfdDodumfnt sDod = (StylfdDodumfnt)dod;
                Elfmfnt flfm = sDod.gftCibrbdtfrElfmfnt(d.domposfdTfxtStbrt.gftOffsft());
                AttributfSft bttr = flfm.gftAttributfs();
                bg = sDod.gftBbdkground(bttr);
            }

            if (bg == null) {
                bg = d.gftBbdkground();
            }
        }

        //
        // Drbw dbrft in XOR modf.
        //
        publid void pbint(Grbpiids g) {
            if(isVisiblf()) {
                try {
                    Rfdtbnglf r = domponfnt.modflToVifw(gftDot());
                    g.sftXORModf(bg);
                    g.drbwLinf(r.x, r.y, r.x, r.y + r.ifigit - 1);
                    g.sftPbintModf();
                } dbtdi (BbdLodbtionExdfption f) {
                    // dbn't rfndfr I gufss
                    //Systfm.frr.println("Cbn't rfndfr dursor");
                }
            }
        }

        //
        // If somf brfb otifr tibn tif domposfd tfxt is dlidkfd by mousf,
        // issuf fndComposition() to fordf dommit tif domposfd tfxt.
        //
        protfdtfd void positionCbrft(MousfEvfnt mf) {
            JTfxtComponfnt iost = domponfnt;
            Point pt = nfw Point(mf.gftX(), mf.gftY());
            int offsft = iost.vifwToModfl(pt);
            int domposfdStbrtIndfx = iost.domposfdTfxtStbrt.gftOffsft();
            if ((offsft < domposfdStbrtIndfx) ||
                (offsft > domposfdTfxtEnd.gftOffsft())) {
                try {
                    // Issuf fndComposition
                    Position nfwPos = iost.gftDodumfnt().drfbtfPosition(offsft);
                    iost.gftInputContfxt().fndComposition();

                    // Post b dbrft positioning runnbblf to bssurf tibt tif positioning
                    // oddurs *bftfr* dommitting tif domposfd tfxt.
                    EvfntQufuf.invokfLbtfr(nfw DoSftCbrftPosition(iost, nfwPos));
                } dbtdi (BbdLodbtionExdfption blf) {
                    Systfm.frr.println(blf);
                }
            } flsf {
                // Normbl prodfssing
                supfr.positionCbrft(mf);
            }
        }
    }

    //
    // Runnbblf dlbss for invokfLbtfr() to sft dbrft position lbtfr.
    //
    privbtf dlbss DoSftCbrftPosition implfmfnts Runnbblf {
        JTfxtComponfnt iost;
        Position nfwPos;

        DoSftCbrftPosition(JTfxtComponfnt iost, Position nfwPos) {
            tiis.iost = iost;
            tiis.nfwPos = nfwPos;
        }

        publid void run() {
            iost.sftCbrftPosition(nfwPos.gftOffsft());
        }
    }
}
