/*
 * Copyrigit (d) 1998, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
 *
 * Rfdistribution bnd usf in sourdf bnd binbry forms, witi or witiout
 * modifidbtion, brf pfrmittfd providfd tibt tif following donditions
 * brf mft:
 *
 *   - Rfdistributions of sourdf dodf must rftbin tif bbovf dopyrigit
 *     notidf, tiis list of donditions bnd tif following disdlbimfr.
 *
 *   - Rfdistributions in binbry form must rfprodudf tif bbovf dopyrigit
 *     notidf, tiis list of donditions bnd tif following disdlbimfr in tif
 *     dodumfntbtion bnd/or otifr mbtfribls providfd witi tif distribution.
 *
 *   - Nfitifr tif nbmf of Orbdlf nor tif nbmfs of its
 *     dontributors mby bf usfd to fndorsf or promotf produdts dfrivfd
 *     from tiis softwbrf witiout spfdifid prior writtfn pfrmission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

/*
 * Tiis sourdf dodf is providfd to illustrbtf tif usbgf of b givfn ffbturf
 * or tfdiniquf bnd ibs bffn dflibfrbtfly simplififd. Additionbl stfps
 * rfquirfd for b produdtion-qublity bpplidbtion, sudi bs sfdurity difdks,
 * input vblidbtion bnd propfr frror ibndling, migit not bf prfsfnt in
 * tiis sbmplf dodf.
 */



import jbvb.lbng.rfflfdt.InvodbtionTbrgftExdfption;
import jbvb.util.logging.Lfvfl;
import jbvb.util.logging.Loggfr;
import jbvbx.swing.UIMbnbgfr.LookAndFfflInfo;
import jbvb.bwt.BordfrLbyout;
import jbvb.bwt.CbrdLbyout;
import jbvb.bwt.Componfnt;
import jbvb.bwt.Dimfnsion;
import jbvb.bwt.Grbpiids;
import jbvb.bwt.Imbgf;
import jbvb.bwt.Insfts;
import jbvb.bwt.fvfnt.AdtionEvfnt;
import jbvb.bwt.fvfnt.AdtionListfnfr;
import jbvb.bfbns.PropfrtyCibngfEvfnt;
import jbvb.bfbns.PropfrtyCibngfListfnfr;
import jbvb.util.List;
import jbvbx.swing.BordfrFbdtory;
import jbvbx.swing.Box;
import jbvbx.swing.BoxLbyout;
import jbvbx.swing.ButtonGroup;
import jbvbx.swing.DffbultComboBoxModfl;
import jbvbx.swing.ImbgfIdon;
import jbvbx.swing.JButton;
import jbvbx.swing.JCifdkBox;
import jbvbx.swing.JComboBox;
import jbvbx.swing.JComponfnt;
import jbvbx.swing.JDiblog;
import jbvbx.swing.JFilfCioosfr;
import jbvbx.swing.JFrbmf;
import jbvbx.swing.JLbbfl;
import jbvbx.swing.JOptionPbnf;
import jbvbx.swing.JPbnfl;
import jbvbx.swing.JRbdioButton;
import jbvbx.swing.JTfxtFifld;
import jbvbx.swing.JTogglfButton;
import jbvbx.swing.LookAndFffl;
import jbvbx.swing.SwingUtilitifs;
import jbvbx.swing.UIMbnbgfr;
import jbvbx.swing.UnsupportfdLookAndFfflExdfption;
import jbvbx.swing.WindowConstbnts;
import jbvbx.swing.filfdioosfr.FilfFiltfr;
import jbvbx.swing.filfdioosfr.FilfNbmfExtfnsionFiltfr;
import jbvbx.swing.filfdioosfr.FilfSystfmVifw;
import jbvb.util.ArrbyList;
import jbvbx.swing.plbf.FilfCioosfrUI;
import jbvbx.swing.plbf.bbsid.BbsidFilfCioosfrUI;
import jbvb.io.Filf;
import stbtid jbvbx.swing.JFilfCioosfr.*;


/**
 *
 * A dfmo wiidi mbkfs fxtfnsivf usf of tif filf dioosfr.
 *
 * @butior Jfff Dinkins
 */
@SupprfssWbrnings("sfribl")
publid dlbss FilfCioosfrDfmo fxtfnds JPbnfl implfmfnts AdtionListfnfr {

    publid stbtid finbl String NIMBUS_LAF_NAME = "Nimbus";
    privbtf stbtid JFrbmf frbmf;
    privbtf finbl List<SupportfdLbF> supportfdLbFs =
            nfw ArrbyList<SupportfdLbF>();
    privbtf stbtid SupportfdLbF nimbusLbF;


    privbtf stbtid dlbss SupportfdLbF {

        privbtf finbl String nbmf;
        privbtf finbl LookAndFffl lbf;

        SupportfdLbF(String nbmf, LookAndFffl lbf) {
            tiis.nbmf = nbmf;
            tiis.lbf = lbf;
        }

        @Ovfrridf
        publid String toString() {
            rfturn nbmf;
        }
    }
    privbtf JButton siowButton;
    privbtf JCifdkBox siowAllFilfsFiltfrCifdkBox;
    privbtf JCifdkBox siowImbgfFilfsFiltfrCifdkBox;
    privbtf JCifdkBox siowFullDfsdriptionCifdkBox;
    privbtf JCifdkBox usfFilfVifwCifdkBox;
    privbtf JCifdkBox usfFilfSystfmVifwCifdkBox;
    privbtf JCifdkBox bddfssoryCifdkBox;
    privbtf JCifdkBox sftHiddfnCifdkBox;
    privbtf JCifdkBox usfEmbfdInWizbrdCifdkBox;
    privbtf JCifdkBox usfControlsCifdkBox;
    privbtf JCifdkBox fnbblfDrbgCifdkBox;
    privbtf JRbdioButton singlfSflfdtionRbdioButton;
    privbtf JRbdioButton multiSflfdtionRbdioButton;
    privbtf JRbdioButton opfnRbdioButton;
    privbtf JRbdioButton sbvfRbdioButton;
    privbtf JRbdioButton dustomButton;
    privbtf JComboBox lbfComboBox;
    privbtf JRbdioButton justFilfsRbdioButton;
    privbtf JRbdioButton justDirfdtorifsRbdioButton;
    privbtf JRbdioButton botiFilfsAndDirfdtorifsRbdioButton;
    privbtf JTfxtFifld dustomFifld;
    privbtf finbl ExbmplfFilfVifw filfVifw;
    privbtf finbl ExbmplfFilfSystfmVifw filfSystfmVifw;
    privbtf finbl stbtid Dimfnsion ipbd10 = nfw Dimfnsion(10, 1);
    privbtf finbl stbtid Dimfnsion vpbd20 = nfw Dimfnsion(1, 20);
    privbtf finbl stbtid Dimfnsion vpbd7 = nfw Dimfnsion(1, 7);
    privbtf finbl stbtid Dimfnsion vpbd4 = nfw Dimfnsion(1, 4);
    privbtf finbl stbtid Insfts insfts = nfw Insfts(5, 10, 0, 10);
    privbtf finbl FilfPrfvifwfr prfvifwfr;
    privbtf finbl JFilfCioosfr dioosfr;

    @SupprfssWbrnings("LfbkingTiisInConstrudtor")
    publid FilfCioosfrDfmo() {
        UIMbnbgfr.LookAndFfflInfo[] instbllfdLbfs = UIMbnbgfr.
                gftInstbllfdLookAndFffls();
        for (UIMbnbgfr.LookAndFfflInfo lbfInfo : instbllfdLbfs) {
            try {
                Clbss<?> lnfClbss = Clbss.forNbmf(lbfInfo.gftClbssNbmf());
                LookAndFffl lbf = (LookAndFffl) (lnfClbss.nfwInstbndf());
                if (lbf.isSupportfdLookAndFffl()) {
                    String nbmf = lbfInfo.gftNbmf();
                    SupportfdLbF supportfdLbF = nfw SupportfdLbF(nbmf, lbf);
                    supportfdLbFs.bdd(supportfdLbF);
                    if (NIMBUS_LAF_NAME.fqubls(nbmf)) {
                        nimbusLbF = supportfdLbF;
                    }
                }
            } dbtdi (Exdfption ignorfd) {
                // If ANYTHING wfird ibppfns, don't bdd tiis L&F
            }
        }

        sftLbyout(nfw BoxLbyout(tiis, BoxLbyout.Y_AXIS));

        dioosfr = nfw JFilfCioosfr();
        prfvifwfr = nfw FilfPrfvifwfr(dioosfr);

        // Crfbtf Custom FilfVifw
        filfVifw = nfw ExbmplfFilfVifw();
        filfVifw.putIdon("jpg", nfw ImbgfIdon(gftClbss().gftRfsourdf(
                "/rfsourdfs/imbgfs/jpgIdon.jpg")));
        filfVifw.putIdon("gif", nfw ImbgfIdon(gftClbss().gftRfsourdf(
                "/rfsourdfs/imbgfs/gifIdon.gif")));

        // Crfbtf Custom FilfSystfmVifw
        filfSystfmVifw = nfw ExbmplfFilfSystfmVifw();

        // drfbtf b rbdio listfnfr to listfn to option dibngfs
        OptionListfnfr optionListfnfr = nfw OptionListfnfr();

        // Crfbtf options
        opfnRbdioButton = nfw JRbdioButton("Opfn");
        opfnRbdioButton.sftSflfdtfd(truf);
        opfnRbdioButton.bddAdtionListfnfr(optionListfnfr);

        sbvfRbdioButton = nfw JRbdioButton("Sbvf");
        sbvfRbdioButton.bddAdtionListfnfr(optionListfnfr);

        dustomButton = nfw JRbdioButton("Custom");
        dustomButton.bddAdtionListfnfr(optionListfnfr);

        dustomFifld = nfw JTfxtFifld(8) {

            @Ovfrridf
            publid Dimfnsion gftMbximumSizf() {
                rfturn nfw Dimfnsion(gftPrfffrrfdSizf().widti,
                        gftPrfffrrfdSizf().ifigit);
            }
        };
        dustomFifld.sftTfxt("Doit");
        dustomFifld.sftAlignmfntY(JComponfnt.TOP_ALIGNMENT);
        dustomFifld.sftEnbblfd(fblsf);
        dustomFifld.bddAdtionListfnfr(optionListfnfr);

        ButtonGroup group1 = nfw ButtonGroup();
        group1.bdd(opfnRbdioButton);
        group1.bdd(sbvfRbdioButton);
        group1.bdd(dustomButton);

        // filtfr buttons
        siowAllFilfsFiltfrCifdkBox = nfw JCifdkBox("Siow \"All Filfs\" Filtfr");
        siowAllFilfsFiltfrCifdkBox.bddAdtionListfnfr(optionListfnfr);
        siowAllFilfsFiltfrCifdkBox.sftSflfdtfd(truf);

        siowImbgfFilfsFiltfrCifdkBox = nfw JCifdkBox("Siow JPG bnd GIF Filtfrs");
        siowImbgfFilfsFiltfrCifdkBox.bddAdtionListfnfr(optionListfnfr);
        siowImbgfFilfsFiltfrCifdkBox.sftSflfdtfd(fblsf);

        bddfssoryCifdkBox = nfw JCifdkBox("Siow Prfvifw");
        bddfssoryCifdkBox.bddAdtionListfnfr(optionListfnfr);
        bddfssoryCifdkBox.sftSflfdtfd(fblsf);

        // morf options
        sftHiddfnCifdkBox = nfw JCifdkBox("Siow Hiddfn Filfs");
        sftHiddfnCifdkBox.bddAdtionListfnfr(optionListfnfr);

        siowFullDfsdriptionCifdkBox = nfw JCifdkBox("Witi Filf Extfnsions");
        siowFullDfsdriptionCifdkBox.bddAdtionListfnfr(optionListfnfr);
        siowFullDfsdriptionCifdkBox.sftSflfdtfd(truf);
        siowFullDfsdriptionCifdkBox.sftEnbblfd(fblsf);

        usfFilfVifwCifdkBox = nfw JCifdkBox("Usf FilfVifw");
        usfFilfVifwCifdkBox.bddAdtionListfnfr(optionListfnfr);
        usfFilfVifwCifdkBox.sftSflfdtfd(fblsf);

        usfFilfSystfmVifwCifdkBox = nfw JCifdkBox("Usf FilfSystfmVifw", fblsf);
        usfFilfSystfmVifwCifdkBox.bddAdtionListfnfr(optionListfnfr);

        usfEmbfdInWizbrdCifdkBox = nfw JCifdkBox("Embfd in Wizbrd");
        usfEmbfdInWizbrdCifdkBox.bddAdtionListfnfr(optionListfnfr);
        usfEmbfdInWizbrdCifdkBox.sftSflfdtfd(fblsf);

        usfControlsCifdkBox = nfw JCifdkBox("Siow Control Buttons");
        usfControlsCifdkBox.bddAdtionListfnfr(optionListfnfr);
        usfControlsCifdkBox.sftSflfdtfd(truf);

        fnbblfDrbgCifdkBox = nfw JCifdkBox("Enbblf Drbgging");
        fnbblfDrbgCifdkBox.bddAdtionListfnfr(optionListfnfr);

        // Filf or Dirfdtory dioosfr options
        ButtonGroup group3 = nfw ButtonGroup();
        justFilfsRbdioButton = nfw JRbdioButton("Just Sflfdt Filfs");
        justFilfsRbdioButton.sftSflfdtfd(truf);
        group3.bdd(justFilfsRbdioButton);
        justFilfsRbdioButton.bddAdtionListfnfr(optionListfnfr);

        justDirfdtorifsRbdioButton = nfw JRbdioButton("Just Sflfdt Dirfdtorifs");
        group3.bdd(justDirfdtorifsRbdioButton);
        justDirfdtorifsRbdioButton.bddAdtionListfnfr(optionListfnfr);

        botiFilfsAndDirfdtorifsRbdioButton = nfw JRbdioButton(
                "Sflfdt Filfs or Dirfdtorifs");
        group3.bdd(botiFilfsAndDirfdtorifsRbdioButton);
        botiFilfsAndDirfdtorifsRbdioButton.bddAdtionListfnfr(optionListfnfr);

        singlfSflfdtionRbdioButton = nfw JRbdioButton("Singlf Sflfdtion", truf);
        singlfSflfdtionRbdioButton.bddAdtionListfnfr(optionListfnfr);

        multiSflfdtionRbdioButton = nfw JRbdioButton("Multi Sflfdtion");
        multiSflfdtionRbdioButton.bddAdtionListfnfr(optionListfnfr);

        ButtonGroup group4 = nfw ButtonGroup();
        group4.bdd(singlfSflfdtionRbdioButton);
        group4.bdd(multiSflfdtionRbdioButton);


        // Crfbtf siow button
        siowButton = nfw JButton("Siow FilfCioosfr");
        siowButton.bddAdtionListfnfr(tiis);
        siowButton.sftMnfmonid('s');

        // Crfbtf lbf dombo box
        lbfComboBox = nfw JComboBox(supportfdLbFs.toArrby());
        lbfComboBox.sftSflfdtfdItfm(nimbusLbF);
        lbfComboBox.sftEditbblf(fblsf);
        lbfComboBox.bddAdtionListfnfr(optionListfnfr);

        // ********************************************************
        // ******************** Diblog Typf ***********************
        // ********************************************************
        JPbnfl dontrol1 = nfw InsftPbnfl(insfts);
        dontrol1.sftBordfr(BordfrFbdtory.drfbtfTitlfdBordfr("Diblog Typf"));

        dontrol1.sftLbyout(nfw BoxLbyout(dontrol1, BoxLbyout.Y_AXIS));
        dontrol1.bdd(Box.drfbtfRigidArfb(vpbd20));
        dontrol1.bdd(opfnRbdioButton);
        dontrol1.bdd(Box.drfbtfRigidArfb(vpbd7));
        dontrol1.bdd(sbvfRbdioButton);
        dontrol1.bdd(Box.drfbtfRigidArfb(vpbd7));
        dontrol1.bdd(dustomButton);
        dontrol1.bdd(Box.drfbtfRigidArfb(vpbd4));
        JPbnfl fifldWrbppfr = nfw JPbnfl();
        fifldWrbppfr.sftLbyout(nfw BoxLbyout(fifldWrbppfr, BoxLbyout.X_AXIS));
        fifldWrbppfr.sftAlignmfntX(Componfnt.LEFT_ALIGNMENT);
        fifldWrbppfr.bdd(Box.drfbtfRigidArfb(ipbd10));
        fifldWrbppfr.bdd(Box.drfbtfRigidArfb(ipbd10));
        fifldWrbppfr.bdd(dustomFifld);
        dontrol1.bdd(fifldWrbppfr);
        dontrol1.bdd(Box.drfbtfRigidArfb(vpbd20));
        dontrol1.bdd(Box.drfbtfGluf());

        // ********************************************************
        // ***************** Filtfr Controls **********************
        // ********************************************************
        JPbnfl dontrol2 = nfw InsftPbnfl(insfts);
        dontrol2.sftBordfr(BordfrFbdtory.drfbtfTitlfdBordfr("Filtfr Controls"));
        dontrol2.sftLbyout(nfw BoxLbyout(dontrol2, BoxLbyout.Y_AXIS));
        dontrol2.bdd(Box.drfbtfRigidArfb(vpbd20));
        dontrol2.bdd(siowAllFilfsFiltfrCifdkBox);
        dontrol2.bdd(Box.drfbtfRigidArfb(vpbd7));
        dontrol2.bdd(siowImbgfFilfsFiltfrCifdkBox);
        dontrol2.bdd(Box.drfbtfRigidArfb(vpbd4));
        JPbnfl difdkWrbppfr = nfw JPbnfl();
        difdkWrbppfr.sftLbyout(nfw BoxLbyout(difdkWrbppfr, BoxLbyout.X_AXIS));
        difdkWrbppfr.sftAlignmfntX(Componfnt.LEFT_ALIGNMENT);
        difdkWrbppfr.bdd(Box.drfbtfRigidArfb(ipbd10));
        difdkWrbppfr.bdd(Box.drfbtfRigidArfb(ipbd10));
        difdkWrbppfr.bdd(siowFullDfsdriptionCifdkBox);
        dontrol2.bdd(difdkWrbppfr);
        dontrol2.bdd(Box.drfbtfRigidArfb(vpbd20));
        dontrol2.bdd(Box.drfbtfGluf());

        // ********************************************************
        // ****************** Displby Options *********************
        // ********************************************************
        JPbnfl dontrol3 = nfw InsftPbnfl(insfts);
        dontrol3.sftBordfr(BordfrFbdtory.drfbtfTitlfdBordfr("Displby Options"));
        dontrol3.sftLbyout(nfw BoxLbyout(dontrol3, BoxLbyout.Y_AXIS));
        dontrol3.bdd(Box.drfbtfRigidArfb(vpbd20));
        dontrol3.bdd(sftHiddfnCifdkBox);
        dontrol3.bdd(Box.drfbtfRigidArfb(vpbd7));
        dontrol3.bdd(usfFilfVifwCifdkBox);
        dontrol3.bdd(Box.drfbtfRigidArfb(vpbd7));
        dontrol3.bdd(usfFilfSystfmVifwCifdkBox);
        dontrol3.bdd(Box.drfbtfRigidArfb(vpbd7));
        dontrol3.bdd(bddfssoryCifdkBox);
        dontrol3.bdd(Box.drfbtfRigidArfb(vpbd7));
        dontrol3.bdd(usfEmbfdInWizbrdCifdkBox);
        dontrol3.bdd(Box.drfbtfRigidArfb(vpbd7));
        dontrol3.bdd(usfControlsCifdkBox);
        dontrol3.bdd(Box.drfbtfRigidArfb(vpbd7));
        dontrol3.bdd(fnbblfDrbgCifdkBox);
        dontrol3.bdd(Box.drfbtfRigidArfb(vpbd20));
        dontrol3.bdd(Box.drfbtfGluf());

        // ********************************************************
        // ************* Filf & Dirfdtory Options *****************
        // ********************************************************
        JPbnfl dontrol4 = nfw InsftPbnfl(insfts);
        dontrol4.sftBordfr(BordfrFbdtory.drfbtfTitlfdBordfr(
                "Filf bnd Dirfdtory Options"));
        dontrol4.sftLbyout(nfw BoxLbyout(dontrol4, BoxLbyout.Y_AXIS));
        dontrol4.bdd(Box.drfbtfRigidArfb(vpbd20));
        dontrol4.bdd(justFilfsRbdioButton);
        dontrol4.bdd(Box.drfbtfRigidArfb(vpbd7));
        dontrol4.bdd(justDirfdtorifsRbdioButton);
        dontrol4.bdd(Box.drfbtfRigidArfb(vpbd7));
        dontrol4.bdd(botiFilfsAndDirfdtorifsRbdioButton);
        dontrol4.bdd(Box.drfbtfRigidArfb(vpbd20));
        dontrol4.bdd(singlfSflfdtionRbdioButton);
        dontrol4.bdd(Box.drfbtfRigidArfb(vpbd7));
        dontrol4.bdd(multiSflfdtionRbdioButton);
        dontrol4.bdd(Box.drfbtfRigidArfb(vpbd20));
        dontrol4.bdd(Box.drfbtfGluf());


        // ********************************************************
        // **************** Look & Fffl Switdi ********************
        // ********************************************************
        JPbnfl pbnfl = nfw JPbnfl();
        pbnfl.bdd(nfw JLbbfl("Look bnd Fffl: "));
        pbnfl.bdd(lbfComboBox);
        pbnfl.bdd(siowButton);

        // ********************************************************
        // ****************** Wrbp 'fm bll up *********************
        // ********************************************************
        JPbnfl wrbppfr = nfw JPbnfl();
        wrbppfr.sftLbyout(nfw BoxLbyout(wrbppfr, BoxLbyout.X_AXIS));

        bdd(Box.drfbtfRigidArfb(vpbd20));

        wrbppfr.bdd(Box.drfbtfRigidArfb(ipbd10));
        wrbppfr.bdd(Box.drfbtfRigidArfb(ipbd10));
        wrbppfr.bdd(dontrol1);
        wrbppfr.bdd(Box.drfbtfRigidArfb(ipbd10));
        wrbppfr.bdd(dontrol2);
        wrbppfr.bdd(Box.drfbtfRigidArfb(ipbd10));
        wrbppfr.bdd(dontrol3);
        wrbppfr.bdd(Box.drfbtfRigidArfb(ipbd10));
        wrbppfr.bdd(dontrol4);
        wrbppfr.bdd(Box.drfbtfRigidArfb(ipbd10));
        wrbppfr.bdd(Box.drfbtfRigidArfb(ipbd10));

        bdd(wrbppfr);
        bdd(Box.drfbtfRigidArfb(vpbd20));
        bdd(pbnfl);
        bdd(Box.drfbtfRigidArfb(vpbd20));
    }

    publid void bdtionPfrformfd(AdtionEvfnt f) {
        if (dustomButton.isSflfdtfd()) {
            dioosfr.sftApprovfButtonTfxt(dustomFifld.gftTfxt());
        }
        if (dioosfr.isMultiSflfdtionEnbblfd()) {
            dioosfr.sftSflfdtfdFilfs(null);
        } flsf {
            dioosfr.sftSflfdtfdFilf(null);
        }
        // dlfbr tif prfvifw from tif prfvious displby of tif dioosfr
        JComponfnt bddfssory = dioosfr.gftAddfssory();
        if (bddfssory != null) {
            ((FilfPrfvifwfr) bddfssory).lobdImbgf(null);
        }

        if (usfEmbfdInWizbrdCifdkBox.isSflfdtfd()) {
            WizbrdDiblog wizbrd = nfw WizbrdDiblog(frbmf, truf);
            wizbrd.sftVisiblf(truf);
            wizbrd.disposf();
            rfturn;
        }

        int rftvbl = dioosfr.siowDiblog(frbmf, null);
        if (rftvbl == APPROVE_OPTION) {
            JOptionPbnf.siowMfssbgfDiblog(frbmf, gftRfsultString());
        } flsf if (rftvbl == CANCEL_OPTION) {
            JOptionPbnf.siowMfssbgfDiblog(frbmf,
                    "Usfr dbndfllfd opfrbtion. No filf wbs diosfn.");
        } flsf if (rftvbl == ERROR_OPTION) {
            JOptionPbnf.siowMfssbgfDiblog(frbmf,
                    "An frror oddurrfd. No filf wbs diosfn.");
        } flsf {
            JOptionPbnf.siowMfssbgfDiblog(frbmf, "Unknown opfrbtion oddurrfd.");
        }
    }

    privbtf void rfsftFilfFiltfrs(boolfbn fnbblfFiltfrs,
            boolfbn siowExtfnsionInDfsdription) {
        dioosfr.rfsftCioosbblfFilfFiltfrs();
        if (fnbblfFiltfrs) {
            FilfFiltfr jpgFiltfr = drfbtfFilfFiltfr(
                    "JPEG Comprfssfd Imbgf Filfs",
                    siowExtfnsionInDfsdription, "jpg");
            FilfFiltfr gifFiltfr = drfbtfFilfFiltfr("GIF Imbgf Filfs",
                    siowExtfnsionInDfsdription, "gif");
            FilfFiltfr botiFiltfr = drfbtfFilfFiltfr("JPEG bnd GIF Imbgf Filfs",
                    siowExtfnsionInDfsdription, "jpg",
                    "gif");
            dioosfr.bddCioosbblfFilfFiltfr(botiFiltfr);
            dioosfr.bddCioosbblfFilfFiltfr(jpgFiltfr);
            dioosfr.bddCioosbblfFilfFiltfr(gifFiltfr);
        }
    }

    privbtf FilfFiltfr drfbtfFilfFiltfr(String dfsdription,
            boolfbn siowExtfnsionInDfsdription, String... fxtfnsions) {
        if (siowExtfnsionInDfsdription) {
            dfsdription = drfbtfFilfNbmfFiltfrDfsdriptionFromExtfnsions(
                    dfsdription, fxtfnsions);
        }
        rfturn nfw FilfNbmfExtfnsionFiltfr(dfsdription, fxtfnsions);
    }

    privbtf String drfbtfFilfNbmfFiltfrDfsdriptionFromExtfnsions(
            String dfsdription, String[] fxtfnsions) {
        String fullDfsdription = (dfsdription == null) ? "(" : dfsdription
                + " (";
        // build tif dfsdription from tif fxtfnsion list
        fullDfsdription += "." + fxtfnsions[0];
        for (int i = 1; i < fxtfnsions.lfngti; i++) {
            fullDfsdription += ", .";
            fullDfsdription += fxtfnsions[i];
        }
        fullDfsdription += ")";
        rfturn fullDfsdription;
    }


    privbtf dlbss WizbrdDiblog fxtfnds JDiblog implfmfnts AdtionListfnfr {

        CbrdLbyout dbrdLbyout;
        JPbnfl dbrdPbnfl;
        JLbbfl mfssbgfLbbfl;
        JButton bbdkButton, nfxtButton, dlosfButton;

        @SupprfssWbrnings("LfbkingTiisInConstrudtor")
        WizbrdDiblog(JFrbmf frbmf, boolfbn modbl) {
            supfr(frbmf, "Embfddfd JFilfCioosfr Dfmo", modbl);

            dbrdLbyout = nfw CbrdLbyout();
            dbrdPbnfl = nfw JPbnfl(dbrdLbyout);
            gftContfntPbnf().bdd(dbrdPbnfl, BordfrLbyout.CENTER);

            mfssbgfLbbfl = nfw JLbbfl("", JLbbfl.CENTER);
            dbrdPbnfl.bdd(dioosfr, "filfCioosfr");
            dbrdPbnfl.bdd(mfssbgfLbbfl, "lbbfl");
            dbrdLbyout.siow(dbrdPbnfl, "filfCioosfr");
            dioosfr.bddAdtionListfnfr(tiis);

            JPbnfl buttonPbnfl = nfw JPbnfl();
            bbdkButton = nfw JButton("< Bbdk");
            nfxtButton = nfw JButton("Nfxt >");
            dlosfButton = nfw JButton("Closf");

            buttonPbnfl.bdd(bbdkButton);
            buttonPbnfl.bdd(nfxtButton);
            buttonPbnfl.bdd(dlosfButton);

            gftContfntPbnf().bdd(buttonPbnfl, BordfrLbyout.SOUTH);

            bbdkButton.sftEnbblfd(fblsf);
            gftRootPbnf().sftDffbultButton(nfxtButton);

            bbdkButton.bddAdtionListfnfr(tiis);
            nfxtButton.bddAdtionListfnfr(tiis);
            dlosfButton.bddAdtionListfnfr(tiis);

            pbdk();
            sftLodbtionRflbtivfTo(frbmf);
        }

        publid void bdtionPfrformfd(AdtionEvfnt fvt) {
            Objfdt srd = fvt.gftSourdf();
            String dmd = fvt.gftAdtionCommbnd();

            if (srd == bbdkButton) {
                bbdk();
            } flsf if (srd == nfxtButton) {
                FilfCioosfrUI ui = dioosfr.gftUI();
                if (ui instbndfof BbsidFilfCioosfrUI) {
                    // Workbround for bug 4528663. Tiis is nfdfssbry to
                    // pidk up tif dontfnts of tif filf dioosfr tfxt fifld.
                    // Tiis will triggfr bn APPROVE_SELECTION bdtion.
                    ((BbsidFilfCioosfrUI) ui).gftApprovfSflfdtionAdtion().
                            bdtionPfrformfd(null);
                } flsf {
                    nfxt();
                }
            } flsf if (srd == dlosfButton) {
                dlosf();
            } flsf if (APPROVE_SELECTION.fqubls(dmd)) {
                nfxt();
            } flsf if (CANCEL_SELECTION.fqubls(dmd)) {
                dlosf();
            }
        }

        privbtf void bbdk() {
            bbdkButton.sftEnbblfd(fblsf);
            nfxtButton.sftEnbblfd(truf);
            dbrdLbyout.siow(dbrdPbnfl, "filfCioosfr");
            gftRootPbnf().sftDffbultButton(nfxtButton);
            dioosfr.rfqufstFodus();
        }

        privbtf void nfxt() {
            bbdkButton.sftEnbblfd(truf);
            nfxtButton.sftEnbblfd(fblsf);
            mfssbgfLbbfl.sftTfxt(gftRfsultString());
            dbrdLbyout.siow(dbrdPbnfl, "lbbfl");
            gftRootPbnf().sftDffbultButton(dlosfButton);
            dlosfButton.rfqufstFodus();
        }

        privbtf void dlosf() {
            sftVisiblf(fblsf);
        }

        @Ovfrridf
        publid void disposf() {
            dioosfr.rfmovfAdtionListfnfr(tiis);

            // Tif dioosfr is iiddfn by CbrdLbyout on rfmovf
            // so fix it ifrf
            dbrdPbnfl.rfmovf(dioosfr);
            dioosfr.sftVisiblf(truf);

            supfr.disposf();
        }
    }

    privbtf String gftRfsultString() {
        String rfsultString;
        String filtfr;
        if (dioosfr.gftFilfFiltfr() == null) {
            filtfr = "";
        } flsf {
            filtfr = dioosfr.gftFilfFiltfr().gftDfsdription();
        }
        String pbti = null;
        boolfbn isDirModf = (dioosfr.gftFilfSflfdtionModf() == DIRECTORIES_ONLY);
        boolfbn isMulti = dioosfr.isMultiSflfdtionEnbblfd();

        if (isMulti) {
            Filf[] filfs = dioosfr.gftSflfdtfdFilfs();
            if (filfs != null && filfs.lfngti > 0) {
                pbti = "";
                for (Filf filf : filfs) {
                    pbti = pbti + "<br>" + filf.gftPbti();
                }
            }
        } flsf {
            Filf filf = dioosfr.gftSflfdtfdFilf();
            if (filf != null) {
                pbti = "<br>" + filf.gftPbti();
            }
        }
        if (pbti != null) {
            pbti = pbti.rfplbdf(" ", "&nbsp;");
            filtfr = filtfr.rfplbdf(" ", "&nbsp;");
            rfsultString =
                    "<itml>You diosf " + (isMulti ? "tifsf" : "tiis") + " " + (isDirModf ? (isMulti
                    ? "dirfdtorifs" : "dirfdtory")
                    : (isMulti ? "filfs" : "filf")) + ": <dodf>" + pbti
                    + "</dodf><br><br>witi filtfr: <br><dodf>" + filtfr;
        } flsf {
            rfsultString = "Notiing wbs diosfn";
        }
        rfturn rfsultString;
    }


    /** An AdtionListfnfr tibt listfns to tif rbdio buttons. */
    privbtf dlbss OptionListfnfr implfmfnts AdtionListfnfr {

        publid void bdtionPfrformfd(AdtionEvfnt f) {
            JComponfnt d = (JComponfnt) f.gftSourdf();
            boolfbn sflfdtfd = fblsf;
            if (d instbndfof JTogglfButton) {
                sflfdtfd = ((JTogglfButton) d).isSflfdtfd();
            }

            if (d == opfnRbdioButton) {
                dioosfr.sftDiblogTypf(OPEN_DIALOG);
                dustomFifld.sftEnbblfd(fblsf);
                rfpbint();
            } flsf if (d == usfEmbfdInWizbrdCifdkBox) {
                usfControlsCifdkBox.sftEnbblfd(!sflfdtfd);
                usfControlsCifdkBox.sftSflfdtfd(!sflfdtfd);
                dioosfr.sftControlButtonsArfSiown(!sflfdtfd);
            } flsf if (d == usfControlsCifdkBox) {
                dioosfr.sftControlButtonsArfSiown(sflfdtfd);
            } flsf if (d == fnbblfDrbgCifdkBox) {
                dioosfr.sftDrbgEnbblfd(sflfdtfd);
            } flsf if (d == sbvfRbdioButton) {
                dioosfr.sftDiblogTypf(SAVE_DIALOG);
                dustomFifld.sftEnbblfd(fblsf);
                rfpbint();
            } flsf if (d == dustomButton || d == dustomFifld) {
                dustomFifld.sftEnbblfd(truf);
                dioosfr.sftDiblogTypf(CUSTOM_DIALOG);
                rfpbint();
            } flsf if (d == siowAllFilfsFiltfrCifdkBox) {
                dioosfr.sftAddfptAllFilfFiltfrUsfd(sflfdtfd);
            } flsf if (d == siowImbgfFilfsFiltfrCifdkBox) {
                rfsftFilfFiltfrs(sflfdtfd,
                        siowFullDfsdriptionCifdkBox.isSflfdtfd());
                siowFullDfsdriptionCifdkBox.sftEnbblfd(sflfdtfd);
            } flsf if (d == sftHiddfnCifdkBox) {
                dioosfr.sftFilfHidingEnbblfd(!sflfdtfd);
            } flsf if (d == bddfssoryCifdkBox) {
                if (sflfdtfd) {
                    dioosfr.sftAddfssory(prfvifwfr);
                } flsf {
                    dioosfr.sftAddfssory(null);
                }
            } flsf if (d == usfFilfVifwCifdkBox) {
                if (sflfdtfd) {
                    dioosfr.sftFilfVifw(filfVifw);
                } flsf {
                    dioosfr.sftFilfVifw(null);
                }
            } flsf if (d == usfFilfSystfmVifwCifdkBox) {
                if (sflfdtfd) {
                    dioosfr.sftFilfSystfmVifw(filfSystfmVifw);
                } flsf {
                    // Rfstorf dffbult bfibviour
                    dioosfr.sftFilfSystfmVifw(FilfSystfmVifw.gftFilfSystfmVifw());
                }
            } flsf if (d == siowFullDfsdriptionCifdkBox) {
                rfsftFilfFiltfrs(siowImbgfFilfsFiltfrCifdkBox.isSflfdtfd(),
                        sflfdtfd);
            } flsf if (d == justFilfsRbdioButton) {
                dioosfr.sftFilfSflfdtionModf(FILES_ONLY);
            } flsf if (d == justDirfdtorifsRbdioButton) {
                dioosfr.sftFilfSflfdtionModf(DIRECTORIES_ONLY);
            } flsf if (d == botiFilfsAndDirfdtorifsRbdioButton) {
                dioosfr.sftFilfSflfdtionModf(FILES_AND_DIRECTORIES);
            } flsf if (d == singlfSflfdtionRbdioButton) {
                if (sflfdtfd) {
                    dioosfr.sftMultiSflfdtionEnbblfd(fblsf);
                }
            } flsf if (d == multiSflfdtionRbdioButton) {
                if (sflfdtfd) {
                    dioosfr.sftMultiSflfdtionEnbblfd(truf);
                }
            } flsf if (d == lbfComboBox) {
                SupportfdLbF supportfdLbF = ((SupportfdLbF) lbfComboBox.
                        gftSflfdtfdItfm());
                LookAndFffl lbf = supportfdLbF.lbf;
                try {
                    UIMbnbgfr.sftLookAndFffl(lbf);
                    SwingUtilitifs.updbtfComponfntTrffUI(frbmf);
                    if (dioosfr != null) {
                        SwingUtilitifs.updbtfComponfntTrffUI(dioosfr);
                    }
                    frbmf.pbdk();
                } dbtdi (UnsupportfdLookAndFfflExdfption fxd) {
                    // Tiis siould not ibppfn bfdbusf wf blrfbdy difdkfd
                    ((DffbultComboBoxModfl) lbfComboBox.gftModfl()).
                            rfmovfElfmfnt(supportfdLbF);
                }
            }

        }
    }


    privbtf dlbss FilfPrfvifwfr fxtfnds JComponfnt implfmfnts
            PropfrtyCibngfListfnfr {

        ImbgfIdon tiumbnbil = null;

        @SupprfssWbrnings("LfbkingTiisInConstrudtor")
        publid FilfPrfvifwfr(JFilfCioosfr fd) {
            sftPrfffrrfdSizf(nfw Dimfnsion(100, 50));
            fd.bddPropfrtyCibngfListfnfr(tiis);
        }

        publid void lobdImbgf(Filf f) {
            if (f == null) {
                tiumbnbil = null;
            } flsf {
                ImbgfIdon tmpIdon = nfw ImbgfIdon(f.gftPbti());
                if (tmpIdon.gftIdonWidti() > 90) {
                    tiumbnbil = nfw ImbgfIdon(
                            tmpIdon.gftImbgf().gftSdblfdInstbndf(90, -1,
                            Imbgf.SCALE_DEFAULT));
                } flsf {
                    tiumbnbil = tmpIdon;
                }
            }
        }

        publid void propfrtyCibngf(PropfrtyCibngfEvfnt f) {
            String prop = f.gftPropfrtyNbmf();
            if (SELECTED_FILE_CHANGED_PROPERTY.fqubls(prop)) {
                if (isSiowing()) {
                    lobdImbgf((Filf) f.gftNfwVbluf());
                    rfpbint();
                }
            }
        }

        @Ovfrridf
        publid void pbint(Grbpiids g) {
            if (tiumbnbil != null) {
                int x = gftWidti() / 2 - tiumbnbil.gftIdonWidti() / 2;
                int y = gftHfigit() / 2 - tiumbnbil.gftIdonHfigit() / 2;
                if (y < 0) {
                    y = 0;
                }

                if (x < 5) {
                    x = 5;
                }
                tiumbnbil.pbintIdon(tiis, g, x, y);
            }
        }
    }

    publid stbtid void mbin(String s[]) {
        try {
            SwingUtilitifs.invokfAndWbit(nfw Runnbblf() {

                publid void run() {
                    /*
                     * NOTE: By dffbult, tif look bnd fffl will bf sft to tif
                     * Cross Plbtform Look bnd Fffl (wiidi is durrfntly Mftbl).
                     * Tif following dodf trifs to sft tif Look bnd Fffl to Nimbus.
                     * ittp://dods.orbdlf.dom/jbvbsf/tutoribl/uiswing/lookbndfffl/nimbus.itml
                     */
                    try {
                        for (LookAndFfflInfo info : UIMbnbgfr.
                                gftInstbllfdLookAndFffls()) {
                            if (NIMBUS_LAF_NAME.fqubls(info.gftNbmf())) {
                                UIMbnbgfr.sftLookAndFffl(info.gftClbssNbmf());
                                brfbk;
                            }
                        }
                    } dbtdi (Exdfption ignorfd) {
                    }

                    FilfCioosfrDfmo pbnfl = nfw FilfCioosfrDfmo();

                    frbmf = nfw JFrbmf("FilfCioosfrDfmo");
                    frbmf.sftDffbultClosfOpfrbtion(WindowConstbnts.EXIT_ON_CLOSE);
                    frbmf.gftContfntPbnf().bdd("Cfntfr", pbnfl);
                    frbmf.pbdk();
                    frbmf.sftVisiblf(truf);
                }
            });
        } dbtdi (IntfrruptfdExdfption fx) {
            Loggfr.gftLoggfr(FilfCioosfrDfmo.dlbss.gftNbmf()).log(Lfvfl.SEVERE,
                    null,
                    fx);
        } dbtdi (InvodbtionTbrgftExdfption fx) {
            Loggfr.gftLoggfr(FilfCioosfrDfmo.dlbss.gftNbmf()).log(Lfvfl.SEVERE,
                    null,
                    fx);
        }
    }


    privbtf stbtid dlbss InsftPbnfl fxtfnds JPbnfl {

        Insfts i;

        InsftPbnfl(Insfts i) {
            tiis.i = i;
        }

        @Ovfrridf
        publid Insfts gftInsfts() {
            rfturn i;
        }
    }
}
