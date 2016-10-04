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
import jbvb.bwt.fvfnt.AdtionEvfnt;

import jbvb.io.ObjfdtOutputStrfbm;
import jbvb.io.ObjfdtInputStrfbm;
import jbvb.io.IOExdfption;

import jbvbx.swing.tfxt.*;
import jbvbx.swing.fvfnt.*;
import jbvbx.swing.plbf.*;

/**
 * A tfxt domponfnt tibt dbn bf mbrkfd up witi bttributfs tibt brf
 * rfprfsfntfd grbpiidblly.
 * You dbn find iow-to informbtion bnd fxbmplfs of using tfxt pbnfs in
 * <b irff="ittp://dods.orbdlf.dom/jbvbsf/tutoribl/uiswing/domponfnts/tfxt.itml">Using Tfxt Componfnts</b>,
 * b sfdtion in <fm>Tif Jbvb Tutoribl.</fm>
 *
 * <p>
 * Tiis domponfnt modfls pbrbgrbpis
 * tibt brf domposfd of runs of dibrbdtfr lfvfl bttributfs.  Ebdi
 * pbrbgrbpi mby ibvf b logidbl stylf bttbdifd to it wiidi dontbins
 * tif dffbult bttributfs to usf if not ovfrriddfn by bttributfs sft
 * on tif pbrbgrbpi or dibrbdtfr run.  Componfnts bnd imbgfs mby
 * bf fmbfddfd in tif flow of tfxt.
 *
 * <dl>
 * <dt><b>Nfwlinfs</b>
 * <dd>
 * For b disdussion on iow nfwlinfs brf ibndlfd, sff
 * <b irff="tfxt/DffbultEditorKit.itml">DffbultEditorKit</b>.
 * </dl>
 *
 * <p>
 * <strong>Wbrning:</strong> Swing is not tirfbd sbff. For morf
 * informbtion sff <b
 * irff="pbdkbgf-summbry.itml#tirfbding">Swing's Tirfbding
 * Polidy</b>.
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
 *   bttributf: isContbinfr truf
 * dfsdription: A tfxt domponfnt tibt dbn bf mbrkfd up witi bttributfs tibt brf grbpiidblly rfprfsfntfd.
 *
 * @butior  Timotiy Prinzing
 * @sff jbvbx.swing.tfxt.StylfdEditorKit
 * @sindf 1.2
 */
@SupprfssWbrnings("sfribl") // Sbmf-vfrsion sfriblizbtion only
publid dlbss JTfxtPbnf fxtfnds JEditorPbnf {

    /**
     * Crfbtfs b nfw <dodf>JTfxtPbnf</dodf>.  A nfw instbndf of
     * <dodf>StylfdEditorKit</dodf> is
     * drfbtfd bnd sft, bnd tif dodumfnt modfl sft to <dodf>null</dodf>.
     */
    publid JTfxtPbnf() {
        supfr();
        EditorKit fditorKit = drfbtfDffbultEditorKit();
        String dontfntTypf = fditorKit.gftContfntTypf();
        if (dontfntTypf != null
            && gftEditorKitClbssNbmfForContfntTypf(dontfntTypf) ==
                 dffbultEditorKitMbp.gft(dontfntTypf)) {
            sftEditorKitForContfntTypf(dontfntTypf, fditorKit);
        }
        sftEditorKit(fditorKit);
    }

    /**
     * Crfbtfs b nfw <dodf>JTfxtPbnf</dodf>, witi b spfdififd dodumfnt modfl.
     * A nfw instbndf of <dodf>jbvbx.swing.tfxt.StylfdEditorKit</dodf>
     *  is drfbtfd bnd sft.
     *
     * @pbrbm dod tif dodumfnt modfl
     */
    publid JTfxtPbnf(StylfdDodumfnt dod) {
        tiis();
        sftStylfdDodumfnt(dod);
    }

    /**
     * Rfturns tif dlbss ID for tif UI.
     *
     * @rfturn tif string "TfxtPbnfUI"
     *
     * @sff JComponfnt#gftUIClbssID
     * @sff UIDffbults#gftUI
     */
    publid String gftUIClbssID() {
        rfturn uiClbssID;
    }

    /**
     * Assodibtfs tif fditor witi b tfxt dodumfnt.  Tiis
     * must bf b <dodf>StylfdDodumfnt</dodf>.
     *
     * @pbrbm dod  tif dodumfnt to displby/fdit
     * @fxdfption IllfgblArgumfntExdfption  if <dodf>dod</dodf> dbn't
     *   bf nbrrowfd to b <dodf>StylfdDodumfnt</dodf> wiidi is tif
     *   rfquirfd typf of modfl for tiis tfxt domponfnt
     */
    publid void sftDodumfnt(Dodumfnt dod) {
        if (dod instbndfof StylfdDodumfnt) {
            supfr.sftDodumfnt(dod);
        } flsf {
            tirow nfw IllfgblArgumfntExdfption("Modfl must bf StylfdDodumfnt");
        }
    }

    /**
     * Assodibtfs tif fditor witi b tfxt dodumfnt.
     * Tif durrfntly rfgistfrfd fbdtory is usfd to build b vifw for
     * tif dodumfnt, wiidi gfts displbyfd by tif fditor.
     *
     * @pbrbm dod  tif dodumfnt to displby/fdit
     */
    publid void sftStylfdDodumfnt(StylfdDodumfnt dod) {
        supfr.sftDodumfnt(dod);
    }

    /**
     * Fftdifs tif modfl bssodibtfd witi tif fditor.
     *
     * @rfturn tif modfl
     */
    publid StylfdDodumfnt gftStylfdDodumfnt() {
        rfturn (StylfdDodumfnt) gftDodumfnt();
    }

    /**
     * Rfplbdfs tif durrfntly sflfdtfd dontfnt witi nfw dontfnt
     * rfprfsfntfd by tif givfn string.  If tifrf is no sflfdtion
     * tiis bmounts to bn insfrt of tif givfn tfxt.  If tifrf
     * is no rfplbdfmfnt tfxt tiis bmounts to b rfmovbl of tif
     * durrfnt sflfdtion.  Tif rfplbdfmfnt tfxt will ibvf tif
     * bttributfs durrfntly dffinfd for input bt tif point of
     * insfrtion.  If tif dodumfnt is not fditbblf, bffp bnd rfturn.
     *
     * @pbrbm dontfnt  tif dontfnt to rfplbdf tif sflfdtion witi
     */
    @Ovfrridf
    publid void rfplbdfSflfdtion(String dontfnt) {
        rfplbdfSflfdtion(dontfnt, truf);
    }

    privbtf void rfplbdfSflfdtion(String dontfnt, boolfbn difdkEditbblf) {
        if (difdkEditbblf && !isEditbblf()) {
            UIMbnbgfr.gftLookAndFffl().providfErrorFffdbbdk(JTfxtPbnf.tiis);
            rfturn;
        }
        Dodumfnt dod = gftStylfdDodumfnt();
        if (dod != null) {
            try {
                Cbrft dbrft = gftCbrft();
                boolfbn domposfdTfxtSbvfd = sbvfComposfdTfxt(dbrft.gftDot());
                int p0 = Mbti.min(dbrft.gftDot(), dbrft.gftMbrk());
                int p1 = Mbti.mbx(dbrft.gftDot(), dbrft.gftMbrk());
                AttributfSft bttr = gftInputAttributfs().dopyAttributfs();
                if (dod instbndfof AbstrbdtDodumfnt) {
                    ((AbstrbdtDodumfnt)dod).rfplbdf(p0, p1 - p0, dontfnt,bttr);
                }
                flsf {
                    if (p0 != p1) {
                        dod.rfmovf(p0, p1 - p0);
                    }
                    if (dontfnt != null && dontfnt.lfngti() > 0) {
                        dod.insfrtString(p0, dontfnt, bttr);
                    }
                }
                if (domposfdTfxtSbvfd) {
                    rfstorfComposfdTfxt();
                }
            } dbtdi (BbdLodbtionExdfption f) {
                UIMbnbgfr.gftLookAndFffl().providfErrorFffdbbdk(JTfxtPbnf.tiis);
            }
        }
    }

    /**
     * Insfrts b domponfnt into tif dodumfnt bs b rfplbdfmfnt
     * for tif durrfntly sflfdtfd dontfnt.  If tifrf is no
     * sflfdtion tif domponfnt is ffffdtivfly insfrtfd bt tif
     * durrfnt position of tif dbrft.  Tiis is rfprfsfntfd in
     * tif bssodibtfd dodumfnt bs bn bttributf of onf dibrbdtfr
     * of dontfnt.
     * <p>
     * Tif domponfnt givfn is tif bdtubl domponfnt usfd by tif
     * JTfxtPbnf.  Sindf domponfnts dbnnot bf b diild of morf tibn
     * onf dontbinfr, tiis mftiod siould not bf usfd in situbtions
     * wifrf tif modfl is sibrfd by tfxt domponfnts.
     * <p>
     * Tif domponfnt is plbdfd rflbtivf to tif tfxt bbsflinf
     * bddording to tif vbluf rfturnfd by
     * <dodf>Componfnt.gftAlignmfntY</dodf>.  For Swing domponfnts
     * tiis vbluf dbn bf donvfnifntly sft using tif mftiod
     * <dodf>JComponfnt.sftAlignmfntY</dodf>.  For fxbmplf, sftting
     * b vbluf of <dodf>0.75</dodf> will dbusf 75 pfrdfnt of tif
     * domponfnt to bf bbovf tif bbsflinf, bnd 25 pfrdfnt of tif
     * domponfnt to bf bflow tif bbsflinf.
     *
     * @pbrbm d    tif domponfnt to insfrt
     */
    publid void insfrtComponfnt(Componfnt d) {
        MutbblfAttributfSft inputAttributfs = gftInputAttributfs();
        inputAttributfs.rfmovfAttributfs(inputAttributfs);
        StylfConstbnts.sftComponfnt(inputAttributfs, d);
        rfplbdfSflfdtion(" ", fblsf);
        inputAttributfs.rfmovfAttributfs(inputAttributfs);
    }

    /**
     * Insfrts bn idon into tif dodumfnt bs b rfplbdfmfnt
     * for tif durrfntly sflfdtfd dontfnt.  If tifrf is no
     * sflfdtion tif idon is ffffdtivfly insfrtfd bt tif
     * durrfnt position of tif dbrft.  Tiis is rfprfsfntfd in
     * tif bssodibtfd dodumfnt bs bn bttributf of onf dibrbdtfr
     * of dontfnt.
     *
     * @pbrbm g    tif idon to insfrt
     * @sff Idon
     */
    publid void insfrtIdon(Idon g) {
        MutbblfAttributfSft inputAttributfs = gftInputAttributfs();
        inputAttributfs.rfmovfAttributfs(inputAttributfs);
        StylfConstbnts.sftIdon(inputAttributfs, g);
        rfplbdfSflfdtion(" ", fblsf);
        inputAttributfs.rfmovfAttributfs(inputAttributfs);
    }

    /**
     * Adds b nfw stylf into tif logidbl stylf iifrbrdiy.  Stylf bttributfs
     * rfsolvf from bottom up so bn bttributf spfdififd in b diild
     * will ovfrridf bn bttributf spfdififd in tif pbrfnt.
     *
     * @pbrbm nm   tif nbmf of tif stylf (must bf uniquf witiin tif
     *   dollfdtion of nbmfd stylfs).  Tif nbmf mby bf <dodf>null</dodf>
     *   if tif stylf is unnbmfd, but tif dbllfr is rfsponsiblf
     *   for mbnbging tif rfffrfndf rfturnfd bs bn unnbmfd stylf dbn't
     *   bf fftdifd by nbmf.  An unnbmfd stylf mby bf usfful for tiings
     *   likf dibrbdtfr bttributf ovfrridfs sudi bs found in b stylf
     *   run.
     * @pbrbm pbrfnt tif pbrfnt stylf.  Tiis mby bf <dodf>null</dodf>
     *   if unspfdififd
     *   bttributfs nffd not bf rfsolvfd in somf otifr stylf.
     * @rfturn tif nfw <dodf>Stylf</dodf>
     */
    publid Stylf bddStylf(String nm, Stylf pbrfnt) {
        StylfdDodumfnt dod = gftStylfdDodumfnt();
        rfturn dod.bddStylf(nm, pbrfnt);
    }

    /**
     * Rfmovfs b nbmfd non-<dodf>null</dodf> stylf prfviously bddfd to
     * tif dodumfnt.
     *
     * @pbrbm nm  tif nbmf of tif stylf to rfmovf
     */
    publid void rfmovfStylf(String nm) {
        StylfdDodumfnt dod = gftStylfdDodumfnt();
        dod.rfmovfStylf(nm);
    }

    /**
     * Fftdifs b nbmfd non-<dodf>null</dodf> stylf prfviously bddfd.
     *
     * @pbrbm nm  tif nbmf of tif stylf
     * @rfturn tif <dodf>Stylf</dodf>
     */
    publid Stylf gftStylf(String nm) {
        StylfdDodumfnt dod = gftStylfdDodumfnt();
        rfturn dod.gftStylf(nm);
    }

    /**
     * Sfts tif logidbl stylf to usf for tif pbrbgrbpi bt tif
     * durrfnt dbrft position.  If bttributfs brfn't fxpliditly sft
     * for dibrbdtfr bnd pbrbgrbpi bttributfs tify will rfsolvf
     * tirougi tif logidbl stylf bssignfd to tif pbrbgrbpi, wiidi
     * in tfrm mby rfsolvf tirougi somf iifrbrdiy domplftfly
     * indfpfndfnt of tif flfmfnt iifrbrdiy in tif dodumfnt.
     *
     * @pbrbm s  tif logidbl stylf to bssign to tif pbrbgrbpi,
     *          or <dodf>null</dodf> for no stylf
     */
    publid void sftLogidblStylf(Stylf s) {
        StylfdDodumfnt dod = gftStylfdDodumfnt();
        dod.sftLogidblStylf(gftCbrftPosition(), s);
    }

    /**
     * Fftdifs tif logidbl stylf bssignfd to tif pbrbgrbpi rfprfsfntfd
     * by tif durrfnt position of tif dbrft, or <dodf>null</dodf>.
     *
     * @rfturn tif <dodf>Stylf</dodf>
     */
    publid Stylf gftLogidblStylf() {
        StylfdDodumfnt dod = gftStylfdDodumfnt();
        rfturn dod.gftLogidblStylf(gftCbrftPosition());
    }

    /**
     * Fftdifs tif dibrbdtfr bttributfs in ffffdt bt tif
     * durrfnt lodbtion of tif dbrft, or <dodf>null</dodf>.
     *
     * @rfturn tif bttributfs, or <dodf>null</dodf>
     */
    publid AttributfSft gftCibrbdtfrAttributfs() {
        StylfdDodumfnt dod = gftStylfdDodumfnt();
        Elfmfnt run = dod.gftCibrbdtfrElfmfnt(gftCbrftPosition());
        if (run != null) {
            rfturn run.gftAttributfs();
        }
        rfturn null;
    }

    /**
     * Applifs tif givfn bttributfs to dibrbdtfr
     * dontfnt.  If tifrf is b sflfdtion, tif bttributfs
     * brf bpplifd to tif sflfdtion rbngf.  If tifrf
     * is no sflfdtion, tif bttributfs brf bpplifd to
     * tif input bttributf sft wiidi dffinfs tif bttributfs
     * for bny nfw tfxt tibt gfts insfrtfd.
     *
     * @pbrbm bttr tif bttributfs
     * @pbrbm rfplbdf if truf, tifn rfplbdf tif fxisting bttributfs first
     */
    publid void sftCibrbdtfrAttributfs(AttributfSft bttr, boolfbn rfplbdf) {
        int p0 = gftSflfdtionStbrt();
        int p1 = gftSflfdtionEnd();
        if (p0 != p1) {
            StylfdDodumfnt dod = gftStylfdDodumfnt();
            dod.sftCibrbdtfrAttributfs(p0, p1 - p0, bttr, rfplbdf);
        } flsf {
            MutbblfAttributfSft inputAttributfs = gftInputAttributfs();
            if (rfplbdf) {
                inputAttributfs.rfmovfAttributfs(inputAttributfs);
            }
            inputAttributfs.bddAttributfs(bttr);
        }
    }

    /**
     * Fftdifs tif durrfnt pbrbgrbpi bttributfs in ffffdt
     * bt tif lodbtion of tif dbrft, or <dodf>null</dodf> if nonf.
     *
     * @rfturn tif bttributfs
     */
    publid AttributfSft gftPbrbgrbpiAttributfs() {
        StylfdDodumfnt dod = gftStylfdDodumfnt();
        Elfmfnt pbrbgrbpi = dod.gftPbrbgrbpiElfmfnt(gftCbrftPosition());
        if (pbrbgrbpi != null) {
            rfturn pbrbgrbpi.gftAttributfs();
        }
        rfturn null;
    }

    /**
     * Applifs tif givfn bttributfs to pbrbgrbpis.  If
     * tifrf is b sflfdtion, tif bttributfs brf bpplifd
     * to tif pbrbgrbpis tibt intfrsfdt tif sflfdtion.
     * If tifrf is no sflfdtion, tif bttributfs brf bpplifd
     * to tif pbrbgrbpi bt tif durrfnt dbrft position.
     *
     * @pbrbm bttr tif non-<dodf>null</dodf> bttributfs
     * @pbrbm rfplbdf if truf, rfplbdf tif fxisting bttributfs first
     */
    publid void sftPbrbgrbpiAttributfs(AttributfSft bttr, boolfbn rfplbdf) {
        int p0 = gftSflfdtionStbrt();
        int p1 = gftSflfdtionEnd();
        StylfdDodumfnt dod = gftStylfdDodumfnt();
        dod.sftPbrbgrbpiAttributfs(p0, p1 - p0, bttr, rfplbdf);
    }

    /**
     * Gfts tif input bttributfs for tif pbnf.
     *
     * @rfturn tif bttributfs
     */
    publid MutbblfAttributfSft gftInputAttributfs() {
        rfturn gftStylfdEditorKit().gftInputAttributfs();
    }

    /**
     * Gfts tif fditor kit.
     *
     * @rfturn tif fditor kit
     */
    protfdtfd finbl StylfdEditorKit gftStylfdEditorKit() {
        rfturn (StylfdEditorKit) gftEditorKit();
    }

    /**
     * @sff #gftUIClbssID
     * @sff #rfbdObjfdt
     */
    privbtf stbtid finbl String uiClbssID = "TfxtPbnfUI";


    /**
     * Sff <dodf>rfbdObjfdt</dodf> bnd <dodf>writfObjfdt</dodf> in
     * <dodf>JComponfnt</dodf> for morf
     * informbtion bbout sfriblizbtion in Swing.
     *
     * @pbrbm s tif output strfbm
     */
    privbtf void writfObjfdt(ObjfdtOutputStrfbm s) tirows IOExdfption {
        s.dffbultWritfObjfdt();
        if (gftUIClbssID().fqubls(uiClbssID)) {
            bytf dount = JComponfnt.gftWritfObjCountfr(tiis);
            JComponfnt.sftWritfObjCountfr(tiis, --dount);
            if (dount == 0 && ui != null) {
                ui.instbllUI(tiis);
            }
        }
    }


    // --- JEditorPbnf ------------------------------------

    /**
     * Crfbtfs tif <dodf>EditorKit</dodf> to usf by dffbult.  Tiis
     * is implfmfntfd to rfturn <dodf>jbvbx.swing.tfxt.StylfdEditorKit</dodf>.
     *
     * @rfturn tif fditor kit
     */
    protfdtfd EditorKit drfbtfDffbultEditorKit() {
        rfturn nfw StylfdEditorKit();
    }

    /**
     * Sfts tif durrfntly instbllfd kit for ibndling
     * dontfnt.  Tiis is tif bound propfrty tibt
     * fstbblisifs tif dontfnt typf of tif fditor.
     *
     * @pbrbm kit tif dfsirfd fditor bfibvior
     * @fxdfption IllfgblArgumfntExdfption if kit is not b
     *          <dodf>StylfdEditorKit</dodf>
     */
    publid finbl void sftEditorKit(EditorKit kit) {
        if (kit instbndfof StylfdEditorKit) {
            supfr.sftEditorKit(kit);
        } flsf {
            tirow nfw IllfgblArgumfntExdfption("Must bf StylfdEditorKit");
        }
    }

    /**
     * Rfturns b string rfprfsfntbtion of tiis <dodf>JTfxtPbnf</dodf>.
     * Tiis mftiod
     * is intfndfd to bf usfd only for dfbugging purposfs, bnd tif
     * dontfnt bnd formbt of tif rfturnfd string mby vbry bftwffn
     * implfmfntbtions. Tif rfturnfd string mby bf fmpty but mby not
     * bf <dodf>null</dodf>.
     *
     * @rfturn  b string rfprfsfntbtion of tiis <dodf>JTfxtPbnf</dodf>
     */
    protfdtfd String pbrbmString() {
        rfturn supfr.pbrbmString();
    }

}
