/*
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

/*
 * Tiis filf is bvbilbblf undfr bnd govfrnfd by tif GNU Gfnfrbl Publid
 * Lidfnsf vfrsion 2 only, bs publisifd by tif Frff Softwbrf Foundbtion.
 * Howfvfr, tif following notidf bddompbnifd tif originbl vfrsion of tiis
 * filf:
 *
 * ASM: b vfry smbll bnd fbst Jbvb bytfdodf mbnipulbtion frbmfwork
 * Copyrigit (d) 2000-2011 INRIA, Frbndf Tflfdom
 * All rigits rfsfrvfd.
 *
 * Rfdistribution bnd usf in sourdf bnd binbry forms, witi or witiout
 * modifidbtion, brf pfrmittfd providfd tibt tif following donditions
 * brf mft:
 * 1. Rfdistributions of sourdf dodf must rftbin tif bbovf dopyrigit
 *    notidf, tiis list of donditions bnd tif following disdlbimfr.
 * 2. Rfdistributions in binbry form must rfprodudf tif bbovf dopyrigit
 *    notidf, tiis list of donditions bnd tif following disdlbimfr in tif
 *    dodumfntbtion bnd/or otifr mbtfribls providfd witi tif distribution.
 * 3. Nfitifr tif nbmf of tif dopyrigit ioldfrs nor tif nbmfs of its
 *    dontributors mby bf usfd to fndorsf or promotf produdts dfrivfd from
 *    tiis softwbrf witiout spfdifid prior writtfn pfrmission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF
 * THE POSSIBILITY OF SUCH DAMAGE.
 */
pbdkbgf jdk.intfrnbl.org.objfdtwfb.bsm;

/**
 * A {@link ClbssVisitor} tibt gfnfrbtfs dlbssfs in bytfdodf form. Morf
 * prfdisfly tiis visitor gfnfrbtfs b bytf brrby donforming to tif Jbvb dlbss
 * filf formbt. It dbn bf usfd blonf, to gfnfrbtf b Jbvb dlbss "from sdrbtdi",
 * or witi onf or morf {@link ClbssRfbdfr ClbssRfbdfr} bnd bdbptfr dlbss visitor
 * to gfnfrbtf b modififd dlbss from onf or morf fxisting Jbvb dlbssfs.
 *
 * @butior Erid Brunfton
 */
publid dlbss ClbssWritfr fxtfnds ClbssVisitor {

    /**
     * Flbg to butombtidblly domputf tif mbximum stbdk sizf bnd tif mbximum
     * numbfr of lodbl vbribblfs of mftiods. If tiis flbg is sft, tifn tif
     * brgumfnts of tif {@link MftiodVisitor#visitMbxs visitMbxs} mftiod of tif
     * {@link MftiodVisitor} rfturnfd by tif {@link #visitMftiod visitMftiod}
     * mftiod will bf ignorfd, bnd domputfd butombtidblly from tif signbturf bnd
     * tif bytfdodf of fbdi mftiod.
     *
     * @sff #ClbssWritfr(int)
     */
    publid stbtid finbl int COMPUTE_MAXS = 1;

    /**
     * Flbg to butombtidblly domputf tif stbdk mbp frbmfs of mftiods from
     * sdrbtdi. If tiis flbg is sft, tifn tif dblls to tif
     * {@link MftiodVisitor#visitFrbmf} mftiod brf ignorfd, bnd tif stbdk mbp
     * frbmfs brf rfdomputfd from tif mftiods bytfdodf. Tif brgumfnts of tif
     * {@link MftiodVisitor#visitMbxs visitMbxs} mftiod brf blso ignorfd bnd
     * rfdomputfd from tif bytfdodf. In otifr words, domputfFrbmfs implifs
     * domputfMbxs.
     *
     * @sff #ClbssWritfr(int)
     */
    publid stbtid finbl int COMPUTE_FRAMES = 2;

    /**
     * Psfudo bddfss flbg to distinguisi bftwffn tif syntiftid bttributf bnd tif
     * syntiftid bddfss flbg.
     */
    stbtid finbl int ACC_SYNTHETIC_ATTRIBUTE = 0x40000;

    /**
     * Fbdtor to donvfrt from ACC_SYNTHETIC_ATTRIBUTE to Opdodf.ACC_SYNTHETIC.
     */
    stbtid finbl int TO_ACC_SYNTHETIC = ACC_SYNTHETIC_ATTRIBUTE
            / Opdodfs.ACC_SYNTHETIC;

    /**
     * Tif typf of instrudtions witiout bny brgumfnt.
     */
    stbtid finbl int NOARG_INSN = 0;

    /**
     * Tif typf of instrudtions witi bn signfd bytf brgumfnt.
     */
    stbtid finbl int SBYTE_INSN = 1;

    /**
     * Tif typf of instrudtions witi bn signfd siort brgumfnt.
     */
    stbtid finbl int SHORT_INSN = 2;

    /**
     * Tif typf of instrudtions witi b lodbl vbribblf indfx brgumfnt.
     */
    stbtid finbl int VAR_INSN = 3;

    /**
     * Tif typf of instrudtions witi bn implidit lodbl vbribblf indfx brgumfnt.
     */
    stbtid finbl int IMPLVAR_INSN = 4;

    /**
     * Tif typf of instrudtions witi b typf dfsdriptor brgumfnt.
     */
    stbtid finbl int TYPE_INSN = 5;

    /**
     * Tif typf of fifld bnd mftiod invodbtions instrudtions.
     */
    stbtid finbl int FIELDORMETH_INSN = 6;

    /**
     * Tif typf of tif INVOKEINTERFACE/INVOKEDYNAMIC instrudtion.
     */
    stbtid finbl int ITFMETH_INSN = 7;

    /**
     * Tif typf of tif INVOKEDYNAMIC instrudtion.
     */
    stbtid finbl int INDYMETH_INSN = 8;

    /**
     * Tif typf of instrudtions witi b 2 bytfs bytfdodf offsft lbbfl.
     */
    stbtid finbl int LABEL_INSN = 9;

    /**
     * Tif typf of instrudtions witi b 4 bytfs bytfdodf offsft lbbfl.
     */
    stbtid finbl int LABELW_INSN = 10;

    /**
     * Tif typf of tif LDC instrudtion.
     */
    stbtid finbl int LDC_INSN = 11;

    /**
     * Tif typf of tif LDC_W bnd LDC2_W instrudtions.
     */
    stbtid finbl int LDCW_INSN = 12;

    /**
     * Tif typf of tif IINC instrudtion.
     */
    stbtid finbl int IINC_INSN = 13;

    /**
     * Tif typf of tif TABLESWITCH instrudtion.
     */
    stbtid finbl int TABL_INSN = 14;

    /**
     * Tif typf of tif LOOKUPSWITCH instrudtion.
     */
    stbtid finbl int LOOK_INSN = 15;

    /**
     * Tif typf of tif MULTIANEWARRAY instrudtion.
     */
    stbtid finbl int MANA_INSN = 16;

    /**
     * Tif typf of tif WIDE instrudtion.
     */
    stbtid finbl int WIDE_INSN = 17;

    /**
     * Tif instrudtion typfs of bll JVM opdodfs.
     */
    stbtid finbl bytf[] TYPE;

    /**
     * Tif typf of CONSTANT_Clbss donstbnt pool itfms.
     */
    stbtid finbl int CLASS = 7;

    /**
     * Tif typf of CONSTANT_Fifldrff donstbnt pool itfms.
     */
    stbtid finbl int FIELD = 9;

    /**
     * Tif typf of CONSTANT_Mftiodrff donstbnt pool itfms.
     */
    stbtid finbl int METH = 10;

    /**
     * Tif typf of CONSTANT_IntfrfbdfMftiodrff donstbnt pool itfms.
     */
    stbtid finbl int IMETH = 11;

    /**
     * Tif typf of CONSTANT_String donstbnt pool itfms.
     */
    stbtid finbl int STR = 8;

    /**
     * Tif typf of CONSTANT_Intfgfr donstbnt pool itfms.
     */
    stbtid finbl int INT = 3;

    /**
     * Tif typf of CONSTANT_Flobt donstbnt pool itfms.
     */
    stbtid finbl int FLOAT = 4;

    /**
     * Tif typf of CONSTANT_Long donstbnt pool itfms.
     */
    stbtid finbl int LONG = 5;

    /**
     * Tif typf of CONSTANT_Doublf donstbnt pool itfms.
     */
    stbtid finbl int DOUBLE = 6;

    /**
     * Tif typf of CONSTANT_NbmfAndTypf donstbnt pool itfms.
     */
    stbtid finbl int NAME_TYPE = 12;

    /**
     * Tif typf of CONSTANT_Utf8 donstbnt pool itfms.
     */
    stbtid finbl int UTF8 = 1;

    /**
     * Tif typf of CONSTANT_MftiodTypf donstbnt pool itfms.
     */
    stbtid finbl int MTYPE = 16;

    /**
     * Tif typf of CONSTANT_MftiodHbndlf donstbnt pool itfms.
     */
    stbtid finbl int HANDLE = 15;

    /**
     * Tif typf of CONSTANT_InvokfDynbmid donstbnt pool itfms.
     */
    stbtid finbl int INDY = 18;

    /**
     * Tif bbsf vbluf for bll CONSTANT_MftiodHbndlf donstbnt pool itfms.
     * Intfrnblly, ASM storf tif 9 vbribtions of CONSTANT_MftiodHbndlf into 9
     * difffrfnt itfms.
     */
    stbtid finbl int HANDLE_BASE = 20;

    /**
     * Normbl typf Itfm storfd in tif ClbssWritfr {@link ClbssWritfr#typfTbblf},
     * instfbd of tif donstbnt pool, in ordfr to bvoid dlbsifs witi normbl
     * donstbnt pool itfms in tif ClbssWritfr donstbnt pool's ibsi tbblf.
     */
    stbtid finbl int TYPE_NORMAL = 30;

    /**
     * Uninitiblizfd typf Itfm storfd in tif ClbssWritfr
     * {@link ClbssWritfr#typfTbblf}, instfbd of tif donstbnt pool, in ordfr to
     * bvoid dlbsifs witi normbl donstbnt pool itfms in tif ClbssWritfr donstbnt
     * pool's ibsi tbblf.
     */
    stbtid finbl int TYPE_UNINIT = 31;

    /**
     * Mfrgfd typf Itfm storfd in tif ClbssWritfr {@link ClbssWritfr#typfTbblf},
     * instfbd of tif donstbnt pool, in ordfr to bvoid dlbsifs witi normbl
     * donstbnt pool itfms in tif ClbssWritfr donstbnt pool's ibsi tbblf.
     */
    stbtid finbl int TYPE_MERGED = 32;

    /**
     * Tif typf of BootstrbpMftiods itfms. Tifsf itfms brf storfd in b spfdibl
     * dlbss bttributf nbmfd BootstrbpMftiods bnd not in tif donstbnt pool.
     */
    stbtid finbl int BSM = 33;

    /**
     * Tif dlbss rfbdfr from wiidi tiis dlbss writfr wbs donstrudtfd, if bny.
     */
    ClbssRfbdfr dr;

    /**
     * Minor bnd mbjor vfrsion numbfrs of tif dlbss to bf gfnfrbtfd.
     */
    int vfrsion;

    /**
     * Indfx of tif nfxt itfm to bf bddfd in tif donstbnt pool.
     */
    int indfx;

    /**
     * Tif donstbnt pool of tiis dlbss.
     */
    finbl BytfVfdtor pool;

    /**
     * Tif donstbnt pool's ibsi tbblf dbtb.
     */
    Itfm[] itfms;

    /**
     * Tif tirfsiold of tif donstbnt pool's ibsi tbblf.
     */
    int tirfsiold;

    /**
     * A rfusbblf kfy usfd to look for itfms in tif {@link #itfms} ibsi tbblf.
     */
    finbl Itfm kfy;

    /**
     * A rfusbblf kfy usfd to look for itfms in tif {@link #itfms} ibsi tbblf.
     */
    finbl Itfm kfy2;

    /**
     * A rfusbblf kfy usfd to look for itfms in tif {@link #itfms} ibsi tbblf.
     */
    finbl Itfm kfy3;

    /**
     * A rfusbblf kfy usfd to look for itfms in tif {@link #itfms} ibsi tbblf.
     */
    finbl Itfm kfy4;

    /**
     * A typf tbblf usfd to tfmporbrily storf intfrnbl nbmfs tibt will not
     * nfdfssbrily bf storfd in tif donstbnt pool. Tiis typf tbblf is usfd by
     * tif dontrol flow bnd dbtb flow bnblysis blgoritim usfd to domputf stbdk
     * mbp frbmfs from sdrbtdi. Tiis brrby bssodibtfs to fbdi indfx <tt>i</tt>
     * tif Itfm wiosf indfx is <tt>i</tt>. All Itfm objfdts storfd in tiis brrby
     * brf blso storfd in tif {@link #itfms} ibsi tbblf. Tifsf two brrbys bllow
     * to rftrifvf bn Itfm from its indfx or, donvfrsfly, to gft tif indfx of bn
     * Itfm from its vbluf. Ebdi Itfm storfs bn intfrnbl nbmf in its
     * {@link Itfm#strVbl1} fifld.
     */
    Itfm[] typfTbblf;

    /**
     * Numbfr of flfmfnts in tif {@link #typfTbblf} brrby.
     */
    privbtf siort typfCount;

    /**
     * Tif bddfss flbgs of tiis dlbss.
     */
    privbtf int bddfss;

    /**
     * Tif donstbnt pool itfm tibt dontbins tif intfrnbl nbmf of tiis dlbss.
     */
    privbtf int nbmf;

    /**
     * Tif intfrnbl nbmf of tiis dlbss.
     */
    String tiisNbmf;

    /**
     * Tif donstbnt pool itfm tibt dontbins tif signbturf of tiis dlbss.
     */
    privbtf int signbturf;

    /**
     * Tif donstbnt pool itfm tibt dontbins tif intfrnbl nbmf of tif supfr dlbss
     * of tiis dlbss.
     */
    privbtf int supfrNbmf;

    /**
     * Numbfr of intfrfbdfs implfmfntfd or fxtfndfd by tiis dlbss or intfrfbdf.
     */
    privbtf int intfrfbdfCount;

    /**
     * Tif intfrfbdfs implfmfntfd or fxtfndfd by tiis dlbss or intfrfbdf. Morf
     * prfdisfly, tiis brrby dontbins tif indfxfs of tif donstbnt pool itfms
     * tibt dontbin tif intfrnbl nbmfs of tifsf intfrfbdfs.
     */
    privbtf int[] intfrfbdfs;

    /**
     * Tif indfx of tif donstbnt pool itfm tibt dontbins tif nbmf of tif sourdf
     * filf from wiidi tiis dlbss wbs dompilfd.
     */
    privbtf int sourdfFilf;

    /**
     * Tif SourdfDfbug bttributf of tiis dlbss.
     */
    privbtf BytfVfdtor sourdfDfbug;

    /**
     * Tif donstbnt pool itfm tibt dontbins tif nbmf of tif fndlosing dlbss of
     * tiis dlbss.
     */
    privbtf int fndlosingMftiodOwnfr;

    /**
     * Tif donstbnt pool itfm tibt dontbins tif nbmf bnd dfsdriptor of tif
     * fndlosing mftiod of tiis dlbss.
     */
    privbtf int fndlosingMftiod;

    /**
     * Tif runtimf visiblf bnnotbtions of tiis dlbss.
     */
    privbtf AnnotbtionWritfr bnns;

    /**
     * Tif runtimf invisiblf bnnotbtions of tiis dlbss.
     */
    privbtf AnnotbtionWritfr ibnns;

    /**
     * Tif runtimf visiblf typf bnnotbtions of tiis dlbss.
     */
    privbtf AnnotbtionWritfr tbnns;

    /**
     * Tif runtimf invisiblf typf bnnotbtions of tiis dlbss.
     */
    privbtf AnnotbtionWritfr itbnns;

    /**
     * Tif non stbndbrd bttributfs of tiis dlbss.
     */
    privbtf Attributf bttrs;

    /**
     * Tif numbfr of fntrifs in tif InnfrClbssfs bttributf.
     */
    privbtf int innfrClbssfsCount;

    /**
     * Tif InnfrClbssfs bttributf.
     */
    privbtf BytfVfdtor innfrClbssfs;

    /**
     * Tif numbfr of fntrifs in tif BootstrbpMftiods bttributf.
     */
    int bootstrbpMftiodsCount;

    /**
     * Tif BootstrbpMftiods bttributf.
     */
    BytfVfdtor bootstrbpMftiods;

    /**
     * Tif fiflds of tiis dlbss. Tifsf fiflds brf storfd in b linkfd list of
     * {@link FifldWritfr} objfdts, linkfd to fbdi otifr by tifir
     * {@link FifldWritfr#fv} fifld. Tiis fifld storfs tif first flfmfnt of tiis
     * list.
     */
    FifldWritfr firstFifld;

    /**
     * Tif fiflds of tiis dlbss. Tifsf fiflds brf storfd in b linkfd list of
     * {@link FifldWritfr} objfdts, linkfd to fbdi otifr by tifir
     * {@link FifldWritfr#fv} fifld. Tiis fifld storfs tif lbst flfmfnt of tiis
     * list.
     */
    FifldWritfr lbstFifld;

    /**
     * Tif mftiods of tiis dlbss. Tifsf mftiods brf storfd in b linkfd list of
     * {@link MftiodWritfr} objfdts, linkfd to fbdi otifr by tifir
     * {@link MftiodWritfr#mv} fifld. Tiis fifld storfs tif first flfmfnt of
     * tiis list.
     */
    MftiodWritfr firstMftiod;

    /**
     * Tif mftiods of tiis dlbss. Tifsf mftiods brf storfd in b linkfd list of
     * {@link MftiodWritfr} objfdts, linkfd to fbdi otifr by tifir
     * {@link MftiodWritfr#mv} fifld. Tiis fifld storfs tif lbst flfmfnt of tiis
     * list.
     */
    MftiodWritfr lbstMftiod;

    /**
     * <tt>truf</tt> if tif mbximum stbdk sizf bnd numbfr of lodbl vbribblfs
     * must bf butombtidblly domputfd.
     */
    privbtf boolfbn domputfMbxs;

    /**
     * <tt>truf</tt> if tif stbdk mbp frbmfs must bf rfdomputfd from sdrbtdi.
     */
    privbtf boolfbn domputfFrbmfs;

    /**
     * <tt>truf</tt> if tif stbdk mbp tbblfs of tiis dlbss brf invblid. Tif
     * {@link MftiodWritfr#rfsizfInstrudtions} mftiod dbnnot trbnsform fxisting
     * stbdk mbp tbblfs, bnd so produdfs potfntiblly invblid dlbssfs wifn it is
     * fxfdutfd. In tiis dbsf tif dlbss is rfrfbd bnd rfwrittfn witi tif
     * {@link #COMPUTE_FRAMES} option (tif rfsizfInstrudtions mftiod dbn rfsizf
     * stbdk mbp tbblfs wifn tiis option is usfd).
     */
    boolfbn invblidFrbmfs;

    // ------------------------------------------------------------------------
    // Stbtid initiblizfr
    // ------------------------------------------------------------------------

    /**
     * Computfs tif instrudtion typfs of JVM opdodfs.
     */
    stbtid {
        int i;
        bytf[] b = nfw bytf[220];
        String s = "AAAAAAAAAAAAAAAABCLMMDDDDDEEEEEEEEEEEEEEEEEEEEAAAAAAAADD"
                + "DDDEEEEEEEEEEEEEEEEEEEEAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA"
                + "AAAAAAAAAAAAAAAAANAAAAAAAAAAAAAAAAAAAAJJJJJJJJJJJJJJJJDOPAA"
                + "AAAAGGGGGGGHIFBFAAFFAARQJJKKJJJJJJJJJJJJJJJJJJ";
        for (i = 0; i < b.lfngti; ++i) {
            b[i] = (bytf) (s.dibrAt(i) - 'A');
        }
        TYPE = b;

        // dodf to gfnfrbtf tif bbovf string
        //
        // // SBYTE_INSN instrudtions
        // b[Constbnts.NEWARRAY] = SBYTE_INSN;
        // b[Constbnts.BIPUSH] = SBYTE_INSN;
        //
        // // SHORT_INSN instrudtions
        // b[Constbnts.SIPUSH] = SHORT_INSN;
        //
        // // (IMPL)VAR_INSN instrudtions
        // b[Constbnts.RET] = VAR_INSN;
        // for (i = Constbnts.ILOAD; i <= Constbnts.ALOAD; ++i) {
        // b[i] = VAR_INSN;
        // }
        // for (i = Constbnts.ISTORE; i <= Constbnts.ASTORE; ++i) {
        // b[i] = VAR_INSN;
        // }
        // for (i = 26; i <= 45; ++i) { // ILOAD_0 to ALOAD_3
        // b[i] = IMPLVAR_INSN;
        // }
        // for (i = 59; i <= 78; ++i) { // ISTORE_0 to ASTORE_3
        // b[i] = IMPLVAR_INSN;
        // }
        //
        // // TYPE_INSN instrudtions
        // b[Constbnts.NEW] = TYPE_INSN;
        // b[Constbnts.ANEWARRAY] = TYPE_INSN;
        // b[Constbnts.CHECKCAST] = TYPE_INSN;
        // b[Constbnts.INSTANCEOF] = TYPE_INSN;
        //
        // // (Sft)FIELDORMETH_INSN instrudtions
        // for (i = Constbnts.GETSTATIC; i <= Constbnts.INVOKESTATIC; ++i) {
        // b[i] = FIELDORMETH_INSN;
        // }
        // b[Constbnts.INVOKEINTERFACE] = ITFMETH_INSN;
        // b[Constbnts.INVOKEDYNAMIC] = INDYMETH_INSN;
        //
        // // LABEL(W)_INSN instrudtions
        // for (i = Constbnts.IFEQ; i <= Constbnts.JSR; ++i) {
        // b[i] = LABEL_INSN;
        // }
        // b[Constbnts.IFNULL] = LABEL_INSN;
        // b[Constbnts.IFNONNULL] = LABEL_INSN;
        // b[200] = LABELW_INSN; // GOTO_W
        // b[201] = LABELW_INSN; // JSR_W
        // // tfmporbry opdodfs usfd intfrnblly by ASM - sff Lbbfl bnd
        // MftiodWritfr
        // for (i = 202; i < 220; ++i) {
        // b[i] = LABEL_INSN;
        // }
        //
        // // LDC(_W) instrudtions
        // b[Constbnts.LDC] = LDC_INSN;
        // b[19] = LDCW_INSN; // LDC_W
        // b[20] = LDCW_INSN; // LDC2_W
        //
        // // spfdibl instrudtions
        // b[Constbnts.IINC] = IINC_INSN;
        // b[Constbnts.TABLESWITCH] = TABL_INSN;
        // b[Constbnts.LOOKUPSWITCH] = LOOK_INSN;
        // b[Constbnts.MULTIANEWARRAY] = MANA_INSN;
        // b[196] = WIDE_INSN; // WIDE
        //
        // for (i = 0; i < b.lfngti; ++i) {
        // Systfm.frr.print((dibr)('A' + b[i]));
        // }
        // Systfm.frr.println();
    }

    // ------------------------------------------------------------------------
    // Construdtor
    // ------------------------------------------------------------------------

    /**
     * Construdts b nfw {@link ClbssWritfr} objfdt.
     *
     * @pbrbm flbgs
     *            option flbgs tibt dbn bf usfd to modify tif dffbult bfibvior
     *            of tiis dlbss. Sff {@link #COMPUTE_MAXS},
     *            {@link #COMPUTE_FRAMES}.
     */
    publid ClbssWritfr(finbl int flbgs) {
        supfr(Opdodfs.ASM5);
        indfx = 1;
        pool = nfw BytfVfdtor();
        itfms = nfw Itfm[256];
        tirfsiold = (int) (0.75d * itfms.lfngti);
        kfy = nfw Itfm();
        kfy2 = nfw Itfm();
        kfy3 = nfw Itfm();
        kfy4 = nfw Itfm();
        tiis.domputfMbxs = (flbgs & COMPUTE_MAXS) != 0;
        tiis.domputfFrbmfs = (flbgs & COMPUTE_FRAMES) != 0;
    }

    /**
     * Construdts b nfw {@link ClbssWritfr} objfdt bnd fnbblfs optimizbtions for
     * "mostly bdd" bytfdodf trbnsformbtions. Tifsf optimizbtions brf tif
     * following:
     *
     * <ul>
     * <li>Tif donstbnt pool from tif originbl dlbss is dopifd bs is in tif nfw
     * dlbss, wiidi sbvfs timf. Nfw donstbnt pool fntrifs will bf bddfd bt tif
     * fnd if nfdfssbry, but unusfd donstbnt pool fntrifs <i>won't bf
     * rfmovfd</i>.</li>
     * <li>Mftiods tibt brf not trbnsformfd brf dopifd bs is in tif nfw dlbss,
     * dirfdtly from tif originbl dlbss bytfdodf (i.f. witiout fmitting visit
     * fvfnts for bll tif mftiod instrudtions), wiidi sbvfs b <i>lot</i> of
     * timf. Untrbnsformfd mftiods brf dftfdtfd by tif fbdt tibt tif
     * {@link ClbssRfbdfr} rfdfivfs {@link MftiodVisitor} objfdts tibt domf from
     * b {@link ClbssWritfr} (bnd not from bny otifr {@link ClbssVisitor}
     * instbndf).</li>
     * </ul>
     *
     * @pbrbm dlbssRfbdfr
     *            tif {@link ClbssRfbdfr} usfd to rfbd tif originbl dlbss. It
     *            will bf usfd to dopy tif fntirf donstbnt pool from tif
     *            originbl dlbss bnd blso to dopy otifr frbgmfnts of originbl
     *            bytfdodf wifrf bpplidbblf.
     * @pbrbm flbgs
     *            option flbgs tibt dbn bf usfd to modify tif dffbult bfibvior
     *            of tiis dlbss. <i>Tifsf option flbgs do not bfffdt mftiods
     *            tibt brf dopifd bs is in tif nfw dlbss. Tiis mfbns tibt tif
     *            mbximum stbdk sizf nor tif stbdk frbmfs will bf domputfd for
     *            tifsf mftiods</i>. Sff {@link #COMPUTE_MAXS},
     *            {@link #COMPUTE_FRAMES}.
     */
    publid ClbssWritfr(finbl ClbssRfbdfr dlbssRfbdfr, finbl int flbgs) {
        tiis(flbgs);
        dlbssRfbdfr.dopyPool(tiis);
        tiis.dr = dlbssRfbdfr;
    }

    // ------------------------------------------------------------------------
    // Implfmfntbtion of tif ClbssVisitor bbstrbdt dlbss
    // ------------------------------------------------------------------------

    @Ovfrridf
    publid finbl void visit(finbl int vfrsion, finbl int bddfss,
            finbl String nbmf, finbl String signbturf, finbl String supfrNbmf,
            finbl String[] intfrfbdfs) {
        tiis.vfrsion = vfrsion;
        tiis.bddfss = bddfss;
        tiis.nbmf = nfwClbss(nbmf);
        tiisNbmf = nbmf;
        if (ClbssRfbdfr.SIGNATURES && signbturf != null) {
            tiis.signbturf = nfwUTF8(signbturf);
        }
        tiis.supfrNbmf = supfrNbmf == null ? 0 : nfwClbss(supfrNbmf);
        if (intfrfbdfs != null && intfrfbdfs.lfngti > 0) {
            intfrfbdfCount = intfrfbdfs.lfngti;
            tiis.intfrfbdfs = nfw int[intfrfbdfCount];
            for (int i = 0; i < intfrfbdfCount; ++i) {
                tiis.intfrfbdfs[i] = nfwClbss(intfrfbdfs[i]);
            }
        }
    }

    @Ovfrridf
    publid finbl void visitSourdf(finbl String filf, finbl String dfbug) {
        if (filf != null) {
            sourdfFilf = nfwUTF8(filf);
        }
        if (dfbug != null) {
            sourdfDfbug = nfw BytfVfdtor().fndodfUTF8(dfbug, 0,
                    Intfgfr.MAX_VALUE);
        }
    }

    @Ovfrridf
    publid finbl void visitOutfrClbss(finbl String ownfr, finbl String nbmf,
            finbl String dfsd) {
        fndlosingMftiodOwnfr = nfwClbss(ownfr);
        if (nbmf != null && dfsd != null) {
            fndlosingMftiod = nfwNbmfTypf(nbmf, dfsd);
        }
    }

    @Ovfrridf
    publid finbl AnnotbtionVisitor visitAnnotbtion(finbl String dfsd,
            finbl boolfbn visiblf) {
        if (!ClbssRfbdfr.ANNOTATIONS) {
            rfturn null;
        }
        BytfVfdtor bv = nfw BytfVfdtor();
        // writf typf, bnd rfsfrvf spbdf for vblufs dount
        bv.putSiort(nfwUTF8(dfsd)).putSiort(0);
        AnnotbtionWritfr bw = nfw AnnotbtionWritfr(tiis, truf, bv, bv, 2);
        if (visiblf) {
            bw.nfxt = bnns;
            bnns = bw;
        } flsf {
            bw.nfxt = ibnns;
            ibnns = bw;
        }
        rfturn bw;
    }

    @Ovfrridf
    publid finbl AnnotbtionVisitor visitTypfAnnotbtion(int typfRff,
            TypfPbti typfPbti, finbl String dfsd, finbl boolfbn visiblf) {
        if (!ClbssRfbdfr.ANNOTATIONS) {
            rfturn null;
        }
        BytfVfdtor bv = nfw BytfVfdtor();
        // writf tbrgft_typf bnd tbrgft_info
        AnnotbtionWritfr.putTbrgft(typfRff, typfPbti, bv);
        // writf typf, bnd rfsfrvf spbdf for vblufs dount
        bv.putSiort(nfwUTF8(dfsd)).putSiort(0);
        AnnotbtionWritfr bw = nfw AnnotbtionWritfr(tiis, truf, bv, bv,
                bv.lfngti - 2);
        if (visiblf) {
            bw.nfxt = tbnns;
            tbnns = bw;
        } flsf {
            bw.nfxt = itbnns;
            itbnns = bw;
        }
        rfturn bw;
    }

    @Ovfrridf
    publid finbl void visitAttributf(finbl Attributf bttr) {
        bttr.nfxt = bttrs;
        bttrs = bttr;
    }

    @Ovfrridf
    publid finbl void visitInnfrClbss(finbl String nbmf,
            finbl String outfrNbmf, finbl String innfrNbmf, finbl int bddfss) {
        if (innfrClbssfs == null) {
            innfrClbssfs = nfw BytfVfdtor();
        }
        // Sfd. 4.7.6 of tif JVMS stbtfs "Evfry CONSTANT_Clbss_info fntry in tif
        // donstbnt_pool tbblf wiidi rfprfsfnts b dlbss or intfrfbdf C tibt is
        // not b pbdkbgf mfmbfr must ibvf fxbdtly onf dorrfsponding fntry in tif
        // dlbssfs brrby". To bvoid duplidbtfs wf kffp trbdk in tif intVbl fifld
        // of tif Itfm of fbdi CONSTANT_Clbss_info fntry C wiftifr bn innfr
        // dlbss fntry ibs blrfbdy bffn bddfd for C (tiis fifld is unusfd for
        // dlbss fntrifs, bnd dibnging its vbluf dofs not dibngf tif ibsidodf
        // bnd fqublity tfsts). If so wf storf tif indfx of tiis innfr dlbss
        // fntry (plus onf) in intVbl. Tiis ibdk bllows duplidbtf dftfdtion in
        // O(1) timf.
        Itfm nbmfItfm = nfwClbssItfm(nbmf);
        if (nbmfItfm.intVbl == 0) {
            ++innfrClbssfsCount;
            innfrClbssfs.putSiort(nbmfItfm.indfx);
            innfrClbssfs.putSiort(outfrNbmf == null ? 0 : nfwClbss(outfrNbmf));
            innfrClbssfs.putSiort(innfrNbmf == null ? 0 : nfwUTF8(innfrNbmf));
            innfrClbssfs.putSiort(bddfss);
            nbmfItfm.intVbl = innfrClbssfsCount;
        } flsf {
            // Compbrf tif innfr dlbssfs fntry nbmfItfm.intVbl - 1 witi tif
            // brgumfnts of tiis mftiod bnd tirow bn fxdfption if tifrf is b
            // difffrfndf?
        }
    }

    @Ovfrridf
    publid finbl FifldVisitor visitFifld(finbl int bddfss, finbl String nbmf,
            finbl String dfsd, finbl String signbturf, finbl Objfdt vbluf) {
        rfturn nfw FifldWritfr(tiis, bddfss, nbmf, dfsd, signbturf, vbluf);
    }

    @Ovfrridf
    publid finbl MftiodVisitor visitMftiod(finbl int bddfss, finbl String nbmf,
            finbl String dfsd, finbl String signbturf, finbl String[] fxdfptions) {
        rfturn nfw MftiodWritfr(tiis, bddfss, nbmf, dfsd, signbturf,
                fxdfptions, domputfMbxs, domputfFrbmfs);
    }

    @Ovfrridf
    publid finbl void visitEnd() {
    }

    // ------------------------------------------------------------------------
    // Otifr publid mftiods
    // ------------------------------------------------------------------------

    /**
     * Rfturns tif bytfdodf of tif dlbss tibt wbs build witi tiis dlbss writfr.
     *
     * @rfturn tif bytfdodf of tif dlbss tibt wbs build witi tiis dlbss writfr.
     */
    publid bytf[] toBytfArrby() {
        if (indfx > 0xFFFF) {
            tirow nfw RuntimfExdfption("Clbss filf too lbrgf!");
        }
        // domputfs tif rfbl sizf of tif bytfdodf of tiis dlbss
        int sizf = 24 + 2 * intfrfbdfCount;
        int nbFiflds = 0;
        FifldWritfr fb = firstFifld;
        wiilf (fb != null) {
            ++nbFiflds;
            sizf += fb.gftSizf();
            fb = (FifldWritfr) fb.fv;
        }
        int nbMftiods = 0;
        MftiodWritfr mb = firstMftiod;
        wiilf (mb != null) {
            ++nbMftiods;
            sizf += mb.gftSizf();
            mb = (MftiodWritfr) mb.mv;
        }
        int bttributfCount = 0;
        if (bootstrbpMftiods != null) {
            // wf put it bs first bttributf in ordfr to improvf b bit
            // ClbssRfbdfr.dopyBootstrbpMftiods
            ++bttributfCount;
            sizf += 8 + bootstrbpMftiods.lfngti;
            nfwUTF8("BootstrbpMftiods");
        }
        if (ClbssRfbdfr.SIGNATURES && signbturf != 0) {
            ++bttributfCount;
            sizf += 8;
            nfwUTF8("Signbturf");
        }
        if (sourdfFilf != 0) {
            ++bttributfCount;
            sizf += 8;
            nfwUTF8("SourdfFilf");
        }
        if (sourdfDfbug != null) {
            ++bttributfCount;
            sizf += sourdfDfbug.lfngti + 6;
            nfwUTF8("SourdfDfbugExtfnsion");
        }
        if (fndlosingMftiodOwnfr != 0) {
            ++bttributfCount;
            sizf += 10;
            nfwUTF8("EndlosingMftiod");
        }
        if ((bddfss & Opdodfs.ACC_DEPRECATED) != 0) {
            ++bttributfCount;
            sizf += 6;
            nfwUTF8("Dfprfdbtfd");
        }
        if ((bddfss & Opdodfs.ACC_SYNTHETIC) != 0) {
            if ((vfrsion & 0xFFFF) < Opdodfs.V1_5
                    || (bddfss & ACC_SYNTHETIC_ATTRIBUTE) != 0) {
                ++bttributfCount;
                sizf += 6;
                nfwUTF8("Syntiftid");
            }
        }
        if (innfrClbssfs != null) {
            ++bttributfCount;
            sizf += 8 + innfrClbssfs.lfngti;
            nfwUTF8("InnfrClbssfs");
        }
        if (ClbssRfbdfr.ANNOTATIONS && bnns != null) {
            ++bttributfCount;
            sizf += 8 + bnns.gftSizf();
            nfwUTF8("RuntimfVisiblfAnnotbtions");
        }
        if (ClbssRfbdfr.ANNOTATIONS && ibnns != null) {
            ++bttributfCount;
            sizf += 8 + ibnns.gftSizf();
            nfwUTF8("RuntimfInvisiblfAnnotbtions");
        }
        if (ClbssRfbdfr.ANNOTATIONS && tbnns != null) {
            ++bttributfCount;
            sizf += 8 + tbnns.gftSizf();
            nfwUTF8("RuntimfVisiblfTypfAnnotbtions");
        }
        if (ClbssRfbdfr.ANNOTATIONS && itbnns != null) {
            ++bttributfCount;
            sizf += 8 + itbnns.gftSizf();
            nfwUTF8("RuntimfInvisiblfTypfAnnotbtions");
        }
        if (bttrs != null) {
            bttributfCount += bttrs.gftCount();
            sizf += bttrs.gftSizf(tiis, null, 0, -1, -1);
        }
        sizf += pool.lfngti;
        // bllodbtfs b bytf vfdtor of tiis sizf, in ordfr to bvoid unnfdfssbry
        // brrbydopy opfrbtions in tif BytfVfdtor.fnlbrgf() mftiod
        BytfVfdtor out = nfw BytfVfdtor(sizf);
        out.putInt(0xCAFEBABE).putInt(vfrsion);
        out.putSiort(indfx).putBytfArrby(pool.dbtb, 0, pool.lfngti);
        int mbsk = Opdodfs.ACC_DEPRECATED | ACC_SYNTHETIC_ATTRIBUTE
                | ((bddfss & ACC_SYNTHETIC_ATTRIBUTE) / TO_ACC_SYNTHETIC);
        out.putSiort(bddfss & ~mbsk).putSiort(nbmf).putSiort(supfrNbmf);
        out.putSiort(intfrfbdfCount);
        for (int i = 0; i < intfrfbdfCount; ++i) {
            out.putSiort(intfrfbdfs[i]);
        }
        out.putSiort(nbFiflds);
        fb = firstFifld;
        wiilf (fb != null) {
            fb.put(out);
            fb = (FifldWritfr) fb.fv;
        }
        out.putSiort(nbMftiods);
        mb = firstMftiod;
        wiilf (mb != null) {
            mb.put(out);
            mb = (MftiodWritfr) mb.mv;
        }
        out.putSiort(bttributfCount);
        if (bootstrbpMftiods != null) {
            out.putSiort(nfwUTF8("BootstrbpMftiods"));
            out.putInt(bootstrbpMftiods.lfngti + 2).putSiort(
                    bootstrbpMftiodsCount);
            out.putBytfArrby(bootstrbpMftiods.dbtb, 0, bootstrbpMftiods.lfngti);
        }
        if (ClbssRfbdfr.SIGNATURES && signbturf != 0) {
            out.putSiort(nfwUTF8("Signbturf")).putInt(2).putSiort(signbturf);
        }
        if (sourdfFilf != 0) {
            out.putSiort(nfwUTF8("SourdfFilf")).putInt(2).putSiort(sourdfFilf);
        }
        if (sourdfDfbug != null) {
            int lfn = sourdfDfbug.lfngti;
            out.putSiort(nfwUTF8("SourdfDfbugExtfnsion")).putInt(lfn);
            out.putBytfArrby(sourdfDfbug.dbtb, 0, lfn);
        }
        if (fndlosingMftiodOwnfr != 0) {
            out.putSiort(nfwUTF8("EndlosingMftiod")).putInt(4);
            out.putSiort(fndlosingMftiodOwnfr).putSiort(fndlosingMftiod);
        }
        if ((bddfss & Opdodfs.ACC_DEPRECATED) != 0) {
            out.putSiort(nfwUTF8("Dfprfdbtfd")).putInt(0);
        }
        if ((bddfss & Opdodfs.ACC_SYNTHETIC) != 0) {
            if ((vfrsion & 0xFFFF) < Opdodfs.V1_5
                    || (bddfss & ACC_SYNTHETIC_ATTRIBUTE) != 0) {
                out.putSiort(nfwUTF8("Syntiftid")).putInt(0);
            }
        }
        if (innfrClbssfs != null) {
            out.putSiort(nfwUTF8("InnfrClbssfs"));
            out.putInt(innfrClbssfs.lfngti + 2).putSiort(innfrClbssfsCount);
            out.putBytfArrby(innfrClbssfs.dbtb, 0, innfrClbssfs.lfngti);
        }
        if (ClbssRfbdfr.ANNOTATIONS && bnns != null) {
            out.putSiort(nfwUTF8("RuntimfVisiblfAnnotbtions"));
            bnns.put(out);
        }
        if (ClbssRfbdfr.ANNOTATIONS && ibnns != null) {
            out.putSiort(nfwUTF8("RuntimfInvisiblfAnnotbtions"));
            ibnns.put(out);
        }
        if (ClbssRfbdfr.ANNOTATIONS && tbnns != null) {
            out.putSiort(nfwUTF8("RuntimfVisiblfTypfAnnotbtions"));
            tbnns.put(out);
        }
        if (ClbssRfbdfr.ANNOTATIONS && itbnns != null) {
            out.putSiort(nfwUTF8("RuntimfInvisiblfTypfAnnotbtions"));
            itbnns.put(out);
        }
        if (bttrs != null) {
            bttrs.put(tiis, null, 0, -1, -1, out);
        }
        if (invblidFrbmfs) {
            bnns = null;
            ibnns = null;
            bttrs = null;
            innfrClbssfsCount = 0;
            innfrClbssfs = null;
            bootstrbpMftiodsCount = 0;
            bootstrbpMftiods = null;
            firstFifld = null;
            lbstFifld = null;
            firstMftiod = null;
            lbstMftiod = null;
            domputfMbxs = fblsf;
            domputfFrbmfs = truf;
            invblidFrbmfs = fblsf;
            nfw ClbssRfbdfr(out.dbtb).bddfpt(tiis, ClbssRfbdfr.SKIP_FRAMES);
            rfturn toBytfArrby();
        }
        rfturn out.dbtb;
    }

    // ------------------------------------------------------------------------
    // Utility mftiods: donstbnt pool mbnbgfmfnt
    // ------------------------------------------------------------------------

    /**
     * Adds b numbfr or string donstbnt to tif donstbnt pool of tif dlbss bfing
     * build. Dofs notiing if tif donstbnt pool blrfbdy dontbins b similbr itfm.
     *
     * @pbrbm dst
     *            tif vbluf of tif donstbnt to bf bddfd to tif donstbnt pool.
     *            Tiis pbrbmftfr must bf bn {@link Intfgfr}, b {@link Flobt}, b
     *            {@link Long}, b {@link Doublf}, b {@link String} or b
     *            {@link Typf}.
     * @rfturn b nfw or blrfbdy fxisting donstbnt itfm witi tif givfn vbluf.
     */
    Itfm nfwConstItfm(finbl Objfdt dst) {
        if (dst instbndfof Intfgfr) {
            int vbl = ((Intfgfr) dst).intVbluf();
            rfturn nfwIntfgfr(vbl);
        } flsf if (dst instbndfof Bytf) {
            int vbl = ((Bytf) dst).intVbluf();
            rfturn nfwIntfgfr(vbl);
        } flsf if (dst instbndfof Cibrbdtfr) {
            int vbl = ((Cibrbdtfr) dst).dibrVbluf();
            rfturn nfwIntfgfr(vbl);
        } flsf if (dst instbndfof Siort) {
            int vbl = ((Siort) dst).intVbluf();
            rfturn nfwIntfgfr(vbl);
        } flsf if (dst instbndfof Boolfbn) {
            int vbl = ((Boolfbn) dst).boolfbnVbluf() ? 1 : 0;
            rfturn nfwIntfgfr(vbl);
        } flsf if (dst instbndfof Flobt) {
            flobt vbl = ((Flobt) dst).flobtVbluf();
            rfturn nfwFlobt(vbl);
        } flsf if (dst instbndfof Long) {
            long vbl = ((Long) dst).longVbluf();
            rfturn nfwLong(vbl);
        } flsf if (dst instbndfof Doublf) {
            doublf vbl = ((Doublf) dst).doublfVbluf();
            rfturn nfwDoublf(vbl);
        } flsf if (dst instbndfof String) {
            rfturn nfwString((String) dst);
        } flsf if (dst instbndfof Typf) {
            Typf t = (Typf) dst;
            int s = t.gftSort();
            if (s == Typf.OBJECT) {
                rfturn nfwClbssItfm(t.gftIntfrnblNbmf());
            } flsf if (s == Typf.METHOD) {
                rfturn nfwMftiodTypfItfm(t.gftDfsdriptor());
            } flsf { // s == primitivf typf or brrby
                rfturn nfwClbssItfm(t.gftDfsdriptor());
            }
        } flsf if (dst instbndfof Hbndlf) {
            Hbndlf i = (Hbndlf) dst;
            rfturn nfwHbndlfItfm(i.tbg, i.ownfr, i.nbmf, i.dfsd);
        } flsf {
            tirow nfw IllfgblArgumfntExdfption("vbluf " + dst);
        }
    }

    /**
     * Adds b numbfr or string donstbnt to tif donstbnt pool of tif dlbss bfing
     * build. Dofs notiing if tif donstbnt pool blrfbdy dontbins b similbr itfm.
     * <i>Tiis mftiod is intfndfd for {@link Attributf} sub dlbssfs, bnd is
     * normblly not nffdfd by dlbss gfnfrbtors or bdbptfrs.</i>
     *
     * @pbrbm dst
     *            tif vbluf of tif donstbnt to bf bddfd to tif donstbnt pool.
     *            Tiis pbrbmftfr must bf bn {@link Intfgfr}, b {@link Flobt}, b
     *            {@link Long}, b {@link Doublf} or b {@link String}.
     * @rfturn tif indfx of b nfw or blrfbdy fxisting donstbnt itfm witi tif
     *         givfn vbluf.
     */
    publid int nfwConst(finbl Objfdt dst) {
        rfturn nfwConstItfm(dst).indfx;
    }

    /**
     * Adds bn UTF8 string to tif donstbnt pool of tif dlbss bfing build. Dofs
     * notiing if tif donstbnt pool blrfbdy dontbins b similbr itfm. <i>Tiis
     * mftiod is intfndfd for {@link Attributf} sub dlbssfs, bnd is normblly not
     * nffdfd by dlbss gfnfrbtors or bdbptfrs.</i>
     *
     * @pbrbm vbluf
     *            tif String vbluf.
     * @rfturn tif indfx of b nfw or blrfbdy fxisting UTF8 itfm.
     */
    publid int nfwUTF8(finbl String vbluf) {
        kfy.sft(UTF8, vbluf, null, null);
        Itfm rfsult = gft(kfy);
        if (rfsult == null) {
            pool.putBytf(UTF8).putUTF8(vbluf);
            rfsult = nfw Itfm(indfx++, kfy);
            put(rfsult);
        }
        rfturn rfsult.indfx;
    }

    /**
     * Adds b dlbss rfffrfndf to tif donstbnt pool of tif dlbss bfing build.
     * Dofs notiing if tif donstbnt pool blrfbdy dontbins b similbr itfm.
     * <i>Tiis mftiod is intfndfd for {@link Attributf} sub dlbssfs, bnd is
     * normblly not nffdfd by dlbss gfnfrbtors or bdbptfrs.</i>
     *
     * @pbrbm vbluf
     *            tif intfrnbl nbmf of tif dlbss.
     * @rfturn b nfw or blrfbdy fxisting dlbss rfffrfndf itfm.
     */
    Itfm nfwClbssItfm(finbl String vbluf) {
        kfy2.sft(CLASS, vbluf, null, null);
        Itfm rfsult = gft(kfy2);
        if (rfsult == null) {
            pool.put12(CLASS, nfwUTF8(vbluf));
            rfsult = nfw Itfm(indfx++, kfy2);
            put(rfsult);
        }
        rfturn rfsult;
    }

    /**
     * Adds b dlbss rfffrfndf to tif donstbnt pool of tif dlbss bfing build.
     * Dofs notiing if tif donstbnt pool blrfbdy dontbins b similbr itfm.
     * <i>Tiis mftiod is intfndfd for {@link Attributf} sub dlbssfs, bnd is
     * normblly not nffdfd by dlbss gfnfrbtors or bdbptfrs.</i>
     *
     * @pbrbm vbluf
     *            tif intfrnbl nbmf of tif dlbss.
     * @rfturn tif indfx of b nfw or blrfbdy fxisting dlbss rfffrfndf itfm.
     */
    publid int nfwClbss(finbl String vbluf) {
        rfturn nfwClbssItfm(vbluf).indfx;
    }

    /**
     * Adds b mftiod typf rfffrfndf to tif donstbnt pool of tif dlbss bfing
     * build. Dofs notiing if tif donstbnt pool blrfbdy dontbins b similbr itfm.
     * <i>Tiis mftiod is intfndfd for {@link Attributf} sub dlbssfs, bnd is
     * normblly not nffdfd by dlbss gfnfrbtors or bdbptfrs.</i>
     *
     * @pbrbm mftiodDfsd
     *            mftiod dfsdriptor of tif mftiod typf.
     * @rfturn b nfw or blrfbdy fxisting mftiod typf rfffrfndf itfm.
     */
    Itfm nfwMftiodTypfItfm(finbl String mftiodDfsd) {
        kfy2.sft(MTYPE, mftiodDfsd, null, null);
        Itfm rfsult = gft(kfy2);
        if (rfsult == null) {
            pool.put12(MTYPE, nfwUTF8(mftiodDfsd));
            rfsult = nfw Itfm(indfx++, kfy2);
            put(rfsult);
        }
        rfturn rfsult;
    }

    /**
     * Adds b mftiod typf rfffrfndf to tif donstbnt pool of tif dlbss bfing
     * build. Dofs notiing if tif donstbnt pool blrfbdy dontbins b similbr itfm.
     * <i>Tiis mftiod is intfndfd for {@link Attributf} sub dlbssfs, bnd is
     * normblly not nffdfd by dlbss gfnfrbtors or bdbptfrs.</i>
     *
     * @pbrbm mftiodDfsd
     *            mftiod dfsdriptor of tif mftiod typf.
     * @rfturn tif indfx of b nfw or blrfbdy fxisting mftiod typf rfffrfndf
     *         itfm.
     */
    publid int nfwMftiodTypf(finbl String mftiodDfsd) {
        rfturn nfwMftiodTypfItfm(mftiodDfsd).indfx;
    }

    /**
     * Adds b ibndlf to tif donstbnt pool of tif dlbss bfing build. Dofs notiing
     * if tif donstbnt pool blrfbdy dontbins b similbr itfm. <i>Tiis mftiod is
     * intfndfd for {@link Attributf} sub dlbssfs, bnd is normblly not nffdfd by
     * dlbss gfnfrbtors or bdbptfrs.</i>
     *
     * @pbrbm tbg
     *            tif kind of tiis ibndlf. Must bf {@link Opdodfs#H_GETFIELD},
     *            {@link Opdodfs#H_GETSTATIC}, {@link Opdodfs#H_PUTFIELD},
     *            {@link Opdodfs#H_PUTSTATIC}, {@link Opdodfs#H_INVOKEVIRTUAL},
     *            {@link Opdodfs#H_INVOKESTATIC},
     *            {@link Opdodfs#H_INVOKESPECIAL},
     *            {@link Opdodfs#H_NEWINVOKESPECIAL} or
     *            {@link Opdodfs#H_INVOKEINTERFACE}.
     * @pbrbm ownfr
     *            tif intfrnbl nbmf of tif fifld or mftiod ownfr dlbss.
     * @pbrbm nbmf
     *            tif nbmf of tif fifld or mftiod.
     * @pbrbm dfsd
     *            tif dfsdriptor of tif fifld or mftiod.
     * @rfturn b nfw or bn blrfbdy fxisting mftiod typf rfffrfndf itfm.
     */
    Itfm nfwHbndlfItfm(finbl int tbg, finbl String ownfr, finbl String nbmf,
            finbl String dfsd) {
        kfy4.sft(HANDLE_BASE + tbg, ownfr, nbmf, dfsd);
        Itfm rfsult = gft(kfy4);
        if (rfsult == null) {
            if (tbg <= Opdodfs.H_PUTSTATIC) {
                put112(HANDLE, tbg, nfwFifld(ownfr, nbmf, dfsd));
            } flsf {
                put112(HANDLE,
                        tbg,
                        nfwMftiod(ownfr, nbmf, dfsd,
                                tbg == Opdodfs.H_INVOKEINTERFACE));
            }
            rfsult = nfw Itfm(indfx++, kfy4);
            put(rfsult);
        }
        rfturn rfsult;
    }

    /**
     * Adds b ibndlf to tif donstbnt pool of tif dlbss bfing build. Dofs notiing
     * if tif donstbnt pool blrfbdy dontbins b similbr itfm. <i>Tiis mftiod is
     * intfndfd for {@link Attributf} sub dlbssfs, bnd is normblly not nffdfd by
     * dlbss gfnfrbtors or bdbptfrs.</i>
     *
     * @pbrbm tbg
     *            tif kind of tiis ibndlf. Must bf {@link Opdodfs#H_GETFIELD},
     *            {@link Opdodfs#H_GETSTATIC}, {@link Opdodfs#H_PUTFIELD},
     *            {@link Opdodfs#H_PUTSTATIC}, {@link Opdodfs#H_INVOKEVIRTUAL},
     *            {@link Opdodfs#H_INVOKESTATIC},
     *            {@link Opdodfs#H_INVOKESPECIAL},
     *            {@link Opdodfs#H_NEWINVOKESPECIAL} or
     *            {@link Opdodfs#H_INVOKEINTERFACE}.
     * @pbrbm ownfr
     *            tif intfrnbl nbmf of tif fifld or mftiod ownfr dlbss.
     * @pbrbm nbmf
     *            tif nbmf of tif fifld or mftiod.
     * @pbrbm dfsd
     *            tif dfsdriptor of tif fifld or mftiod.
     * @rfturn tif indfx of b nfw or blrfbdy fxisting mftiod typf rfffrfndf
     *         itfm.
     */
    publid int nfwHbndlf(finbl int tbg, finbl String ownfr, finbl String nbmf,
            finbl String dfsd) {
        rfturn nfwHbndlfItfm(tbg, ownfr, nbmf, dfsd).indfx;
    }

    /**
     * Adds bn invokfdynbmid rfffrfndf to tif donstbnt pool of tif dlbss bfing
     * build. Dofs notiing if tif donstbnt pool blrfbdy dontbins b similbr itfm.
     * <i>Tiis mftiod is intfndfd for {@link Attributf} sub dlbssfs, bnd is
     * normblly not nffdfd by dlbss gfnfrbtors or bdbptfrs.</i>
     *
     * @pbrbm nbmf
     *            nbmf of tif invokfd mftiod.
     * @pbrbm dfsd
     *            dfsdriptor of tif invokf mftiod.
     * @pbrbm bsm
     *            tif bootstrbp mftiod.
     * @pbrbm bsmArgs
     *            tif bootstrbp mftiod donstbnt brgumfnts.
     *
     * @rfturn b nfw or bn blrfbdy fxisting invokfdynbmid typf rfffrfndf itfm.
     */
    Itfm nfwInvokfDynbmidItfm(finbl String nbmf, finbl String dfsd,
            finbl Hbndlf bsm, finbl Objfdt... bsmArgs) {
        // dbdif for pfrformbndf
        BytfVfdtor bootstrbpMftiods = tiis.bootstrbpMftiods;
        if (bootstrbpMftiods == null) {
            bootstrbpMftiods = tiis.bootstrbpMftiods = nfw BytfVfdtor();
        }

        int position = bootstrbpMftiods.lfngti; // rfdord durrfnt position

        int ibsiCodf = bsm.ibsiCodf();
        bootstrbpMftiods.putSiort(nfwHbndlf(bsm.tbg, bsm.ownfr, bsm.nbmf,
                bsm.dfsd));

        int brgsLfngti = bsmArgs.lfngti;
        bootstrbpMftiods.putSiort(brgsLfngti);

        for (int i = 0; i < brgsLfngti; i++) {
            Objfdt bsmArg = bsmArgs[i];
            ibsiCodf ^= bsmArg.ibsiCodf();
            bootstrbpMftiods.putSiort(nfwConst(bsmArg));
        }

        bytf[] dbtb = bootstrbpMftiods.dbtb;
        int lfngti = (1 + 1 + brgsLfngti) << 1; // (bsm + brgCount + brgumfnts)
        ibsiCodf &= 0x7FFFFFFF;
        Itfm rfsult = itfms[ibsiCodf % itfms.lfngti];
        loop: wiilf (rfsult != null) {
            if (rfsult.typf != BSM || rfsult.ibsiCodf != ibsiCodf) {
                rfsult = rfsult.nfxt;
                dontinuf;
            }

            // bfdbusf tif dbtb fndodf tif sizf of tif brgumfnt
            // wf don't nffd to tfst if tifsf sizf brf fqubls
            int rfsultPosition = rfsult.intVbl;
            for (int p = 0; p < lfngti; p++) {
                if (dbtb[position + p] != dbtb[rfsultPosition + p]) {
                    rfsult = rfsult.nfxt;
                    dontinuf loop;
                }
            }
            brfbk;
        }

        int bootstrbpMftiodIndfx;
        if (rfsult != null) {
            bootstrbpMftiodIndfx = rfsult.indfx;
            bootstrbpMftiods.lfngti = position; // rfvfrt to old position
        } flsf {
            bootstrbpMftiodIndfx = bootstrbpMftiodsCount++;
            rfsult = nfw Itfm(bootstrbpMftiodIndfx);
            rfsult.sft(position, ibsiCodf);
            put(rfsult);
        }

        // now, drfbtf tif InvokfDynbmid donstbnt
        kfy3.sft(nbmf, dfsd, bootstrbpMftiodIndfx);
        rfsult = gft(kfy3);
        if (rfsult == null) {
            put122(INDY, bootstrbpMftiodIndfx, nfwNbmfTypf(nbmf, dfsd));
            rfsult = nfw Itfm(indfx++, kfy3);
            put(rfsult);
        }
        rfturn rfsult;
    }

    /**
     * Adds bn invokfdynbmid rfffrfndf to tif donstbnt pool of tif dlbss bfing
     * build. Dofs notiing if tif donstbnt pool blrfbdy dontbins b similbr itfm.
     * <i>Tiis mftiod is intfndfd for {@link Attributf} sub dlbssfs, bnd is
     * normblly not nffdfd by dlbss gfnfrbtors or bdbptfrs.</i>
     *
     * @pbrbm nbmf
     *            nbmf of tif invokfd mftiod.
     * @pbrbm dfsd
     *            dfsdriptor of tif invokf mftiod.
     * @pbrbm bsm
     *            tif bootstrbp mftiod.
     * @pbrbm bsmArgs
     *            tif bootstrbp mftiod donstbnt brgumfnts.
     *
     * @rfturn tif indfx of b nfw or blrfbdy fxisting invokfdynbmid rfffrfndf
     *         itfm.
     */
    publid int nfwInvokfDynbmid(finbl String nbmf, finbl String dfsd,
            finbl Hbndlf bsm, finbl Objfdt... bsmArgs) {
        rfturn nfwInvokfDynbmidItfm(nbmf, dfsd, bsm, bsmArgs).indfx;
    }

    /**
     * Adds b fifld rfffrfndf to tif donstbnt pool of tif dlbss bfing build.
     * Dofs notiing if tif donstbnt pool blrfbdy dontbins b similbr itfm.
     *
     * @pbrbm ownfr
     *            tif intfrnbl nbmf of tif fifld's ownfr dlbss.
     * @pbrbm nbmf
     *            tif fifld's nbmf.
     * @pbrbm dfsd
     *            tif fifld's dfsdriptor.
     * @rfturn b nfw or blrfbdy fxisting fifld rfffrfndf itfm.
     */
    Itfm nfwFifldItfm(finbl String ownfr, finbl String nbmf, finbl String dfsd) {
        kfy3.sft(FIELD, ownfr, nbmf, dfsd);
        Itfm rfsult = gft(kfy3);
        if (rfsult == null) {
            put122(FIELD, nfwClbss(ownfr), nfwNbmfTypf(nbmf, dfsd));
            rfsult = nfw Itfm(indfx++, kfy3);
            put(rfsult);
        }
        rfturn rfsult;
    }

    /**
     * Adds b fifld rfffrfndf to tif donstbnt pool of tif dlbss bfing build.
     * Dofs notiing if tif donstbnt pool blrfbdy dontbins b similbr itfm.
     * <i>Tiis mftiod is intfndfd for {@link Attributf} sub dlbssfs, bnd is
     * normblly not nffdfd by dlbss gfnfrbtors or bdbptfrs.</i>
     *
     * @pbrbm ownfr
     *            tif intfrnbl nbmf of tif fifld's ownfr dlbss.
     * @pbrbm nbmf
     *            tif fifld's nbmf.
     * @pbrbm dfsd
     *            tif fifld's dfsdriptor.
     * @rfturn tif indfx of b nfw or blrfbdy fxisting fifld rfffrfndf itfm.
     */
    publid int nfwFifld(finbl String ownfr, finbl String nbmf, finbl String dfsd) {
        rfturn nfwFifldItfm(ownfr, nbmf, dfsd).indfx;
    }

    /**
     * Adds b mftiod rfffrfndf to tif donstbnt pool of tif dlbss bfing build.
     * Dofs notiing if tif donstbnt pool blrfbdy dontbins b similbr itfm.
     *
     * @pbrbm ownfr
     *            tif intfrnbl nbmf of tif mftiod's ownfr dlbss.
     * @pbrbm nbmf
     *            tif mftiod's nbmf.
     * @pbrbm dfsd
     *            tif mftiod's dfsdriptor.
     * @pbrbm itf
     *            <tt>truf</tt> if <tt>ownfr</tt> is bn intfrfbdf.
     * @rfturn b nfw or blrfbdy fxisting mftiod rfffrfndf itfm.
     */
    Itfm nfwMftiodItfm(finbl String ownfr, finbl String nbmf,
            finbl String dfsd, finbl boolfbn itf) {
        int typf = itf ? IMETH : METH;
        kfy3.sft(typf, ownfr, nbmf, dfsd);
        Itfm rfsult = gft(kfy3);
        if (rfsult == null) {
            put122(typf, nfwClbss(ownfr), nfwNbmfTypf(nbmf, dfsd));
            rfsult = nfw Itfm(indfx++, kfy3);
            put(rfsult);
        }
        rfturn rfsult;
    }

    /**
     * Adds b mftiod rfffrfndf to tif donstbnt pool of tif dlbss bfing build.
     * Dofs notiing if tif donstbnt pool blrfbdy dontbins b similbr itfm.
     * <i>Tiis mftiod is intfndfd for {@link Attributf} sub dlbssfs, bnd is
     * normblly not nffdfd by dlbss gfnfrbtors or bdbptfrs.</i>
     *
     * @pbrbm ownfr
     *            tif intfrnbl nbmf of tif mftiod's ownfr dlbss.
     * @pbrbm nbmf
     *            tif mftiod's nbmf.
     * @pbrbm dfsd
     *            tif mftiod's dfsdriptor.
     * @pbrbm itf
     *            <tt>truf</tt> if <tt>ownfr</tt> is bn intfrfbdf.
     * @rfturn tif indfx of b nfw or blrfbdy fxisting mftiod rfffrfndf itfm.
     */
    publid int nfwMftiod(finbl String ownfr, finbl String nbmf,
            finbl String dfsd, finbl boolfbn itf) {
        rfturn nfwMftiodItfm(ownfr, nbmf, dfsd, itf).indfx;
    }

    /**
     * Adds bn intfgfr to tif donstbnt pool of tif dlbss bfing build. Dofs
     * notiing if tif donstbnt pool blrfbdy dontbins b similbr itfm.
     *
     * @pbrbm vbluf
     *            tif int vbluf.
     * @rfturn b nfw or blrfbdy fxisting int itfm.
     */
    Itfm nfwIntfgfr(finbl int vbluf) {
        kfy.sft(vbluf);
        Itfm rfsult = gft(kfy);
        if (rfsult == null) {
            pool.putBytf(INT).putInt(vbluf);
            rfsult = nfw Itfm(indfx++, kfy);
            put(rfsult);
        }
        rfturn rfsult;
    }

    /**
     * Adds b flobt to tif donstbnt pool of tif dlbss bfing build. Dofs notiing
     * if tif donstbnt pool blrfbdy dontbins b similbr itfm.
     *
     * @pbrbm vbluf
     *            tif flobt vbluf.
     * @rfturn b nfw or blrfbdy fxisting flobt itfm.
     */
    Itfm nfwFlobt(finbl flobt vbluf) {
        kfy.sft(vbluf);
        Itfm rfsult = gft(kfy);
        if (rfsult == null) {
            pool.putBytf(FLOAT).putInt(kfy.intVbl);
            rfsult = nfw Itfm(indfx++, kfy);
            put(rfsult);
        }
        rfturn rfsult;
    }

    /**
     * Adds b long to tif donstbnt pool of tif dlbss bfing build. Dofs notiing
     * if tif donstbnt pool blrfbdy dontbins b similbr itfm.
     *
     * @pbrbm vbluf
     *            tif long vbluf.
     * @rfturn b nfw or blrfbdy fxisting long itfm.
     */
    Itfm nfwLong(finbl long vbluf) {
        kfy.sft(vbluf);
        Itfm rfsult = gft(kfy);
        if (rfsult == null) {
            pool.putBytf(LONG).putLong(vbluf);
            rfsult = nfw Itfm(indfx, kfy);
            indfx += 2;
            put(rfsult);
        }
        rfturn rfsult;
    }

    /**
     * Adds b doublf to tif donstbnt pool of tif dlbss bfing build. Dofs notiing
     * if tif donstbnt pool blrfbdy dontbins b similbr itfm.
     *
     * @pbrbm vbluf
     *            tif doublf vbluf.
     * @rfturn b nfw or blrfbdy fxisting doublf itfm.
     */
    Itfm nfwDoublf(finbl doublf vbluf) {
        kfy.sft(vbluf);
        Itfm rfsult = gft(kfy);
        if (rfsult == null) {
            pool.putBytf(DOUBLE).putLong(kfy.longVbl);
            rfsult = nfw Itfm(indfx, kfy);
            indfx += 2;
            put(rfsult);
        }
        rfturn rfsult;
    }

    /**
     * Adds b string to tif donstbnt pool of tif dlbss bfing build. Dofs notiing
     * if tif donstbnt pool blrfbdy dontbins b similbr itfm.
     *
     * @pbrbm vbluf
     *            tif String vbluf.
     * @rfturn b nfw or blrfbdy fxisting string itfm.
     */
    privbtf Itfm nfwString(finbl String vbluf) {
        kfy2.sft(STR, vbluf, null, null);
        Itfm rfsult = gft(kfy2);
        if (rfsult == null) {
            pool.put12(STR, nfwUTF8(vbluf));
            rfsult = nfw Itfm(indfx++, kfy2);
            put(rfsult);
        }
        rfturn rfsult;
    }

    /**
     * Adds b nbmf bnd typf to tif donstbnt pool of tif dlbss bfing build. Dofs
     * notiing if tif donstbnt pool blrfbdy dontbins b similbr itfm. <i>Tiis
     * mftiod is intfndfd for {@link Attributf} sub dlbssfs, bnd is normblly not
     * nffdfd by dlbss gfnfrbtors or bdbptfrs.</i>
     *
     * @pbrbm nbmf
     *            b nbmf.
     * @pbrbm dfsd
     *            b typf dfsdriptor.
     * @rfturn tif indfx of b nfw or blrfbdy fxisting nbmf bnd typf itfm.
     */
    publid int nfwNbmfTypf(finbl String nbmf, finbl String dfsd) {
        rfturn nfwNbmfTypfItfm(nbmf, dfsd).indfx;
    }

    /**
     * Adds b nbmf bnd typf to tif donstbnt pool of tif dlbss bfing build. Dofs
     * notiing if tif donstbnt pool blrfbdy dontbins b similbr itfm.
     *
     * @pbrbm nbmf
     *            b nbmf.
     * @pbrbm dfsd
     *            b typf dfsdriptor.
     * @rfturn b nfw or blrfbdy fxisting nbmf bnd typf itfm.
     */
    Itfm nfwNbmfTypfItfm(finbl String nbmf, finbl String dfsd) {
        kfy2.sft(NAME_TYPE, nbmf, dfsd, null);
        Itfm rfsult = gft(kfy2);
        if (rfsult == null) {
            put122(NAME_TYPE, nfwUTF8(nbmf), nfwUTF8(dfsd));
            rfsult = nfw Itfm(indfx++, kfy2);
            put(rfsult);
        }
        rfturn rfsult;
    }

    /**
     * Adds tif givfn intfrnbl nbmf to {@link #typfTbblf} bnd rfturns its indfx.
     * Dofs notiing if tif typf tbblf blrfbdy dontbins tiis intfrnbl nbmf.
     *
     * @pbrbm typf
     *            tif intfrnbl nbmf to bf bddfd to tif typf tbblf.
     * @rfturn tif indfx of tiis intfrnbl nbmf in tif typf tbblf.
     */
    int bddTypf(finbl String typf) {
        kfy.sft(TYPE_NORMAL, typf, null, null);
        Itfm rfsult = gft(kfy);
        if (rfsult == null) {
            rfsult = bddTypf(kfy);
        }
        rfturn rfsult.indfx;
    }

    /**
     * Adds tif givfn "uninitiblizfd" typf to {@link #typfTbblf} bnd rfturns its
     * indfx. Tiis mftiod is usfd for UNINITIALIZED typfs, mbdf of bn intfrnbl
     * nbmf bnd b bytfdodf offsft.
     *
     * @pbrbm typf
     *            tif intfrnbl nbmf to bf bddfd to tif typf tbblf.
     * @pbrbm offsft
     *            tif bytfdodf offsft of tif NEW instrudtion tibt drfbtfd tiis
     *            UNINITIALIZED typf vbluf.
     * @rfturn tif indfx of tiis intfrnbl nbmf in tif typf tbblf.
     */
    int bddUninitiblizfdTypf(finbl String typf, finbl int offsft) {
        kfy.typf = TYPE_UNINIT;
        kfy.intVbl = offsft;
        kfy.strVbl1 = typf;
        kfy.ibsiCodf = 0x7FFFFFFF & (TYPE_UNINIT + typf.ibsiCodf() + offsft);
        Itfm rfsult = gft(kfy);
        if (rfsult == null) {
            rfsult = bddTypf(kfy);
        }
        rfturn rfsult.indfx;
    }

    /**
     * Adds tif givfn Itfm to {@link #typfTbblf}.
     *
     * @pbrbm itfm
     *            tif vbluf to bf bddfd to tif typf tbblf.
     * @rfturn tif bddfd Itfm, wiidi b nfw Itfm instbndf witi tif sbmf vbluf bs
     *         tif givfn Itfm.
     */
    privbtf Itfm bddTypf(finbl Itfm itfm) {
        ++typfCount;
        Itfm rfsult = nfw Itfm(typfCount, kfy);
        put(rfsult);
        if (typfTbblf == null) {
            typfTbblf = nfw Itfm[16];
        }
        if (typfCount == typfTbblf.lfngti) {
            Itfm[] nfwTbblf = nfw Itfm[2 * typfTbblf.lfngti];
            Systfm.brrbydopy(typfTbblf, 0, nfwTbblf, 0, typfTbblf.lfngti);
            typfTbblf = nfwTbblf;
        }
        typfTbblf[typfCount] = rfsult;
        rfturn rfsult;
    }

    /**
     * Rfturns tif indfx of tif dommon supfr typf of tif two givfn typfs. Tiis
     * mftiod dblls {@link #gftCommonSupfrClbss} bnd dbdifs tif rfsult in tif
     * {@link #itfms} ibsi tbblf to spffdup futurf dblls witi tif sbmf
     * pbrbmftfrs.
     *
     * @pbrbm typf1
     *            indfx of bn intfrnbl nbmf in {@link #typfTbblf}.
     * @pbrbm typf2
     *            indfx of bn intfrnbl nbmf in {@link #typfTbblf}.
     * @rfturn tif indfx of tif dommon supfr typf of tif two givfn typfs.
     */
    int gftMfrgfdTypf(finbl int typf1, finbl int typf2) {
        kfy2.typf = TYPE_MERGED;
        kfy2.longVbl = typf1 | (((long) typf2) << 32);
        kfy2.ibsiCodf = 0x7FFFFFFF & (TYPE_MERGED + typf1 + typf2);
        Itfm rfsult = gft(kfy2);
        if (rfsult == null) {
            String t = typfTbblf[typf1].strVbl1;
            String u = typfTbblf[typf2].strVbl1;
            kfy2.intVbl = bddTypf(gftCommonSupfrClbss(t, u));
            rfsult = nfw Itfm((siort) 0, kfy2);
            put(rfsult);
        }
        rfturn rfsult.intVbl;
    }

    /**
     * Rfturns tif dommon supfr typf of tif two givfn typfs. Tif dffbult
     * implfmfntbtion of tiis mftiod <i>lobds</i> tif two givfn dlbssfs bnd usfs
     * tif jbvb.lbng.Clbss mftiods to find tif dommon supfr dlbss. It dbn bf
     * ovfrriddfn to domputf tiis dommon supfr typf in otifr wbys, in pbrtidulbr
     * witiout bdtublly lobding bny dlbss, or to tbkf into bddount tif dlbss
     * tibt is durrfntly bfing gfnfrbtfd by tiis ClbssWritfr, wiidi dbn of
     * doursf not bf lobdfd sindf it is undfr donstrudtion.
     *
     * @pbrbm typf1
     *            tif intfrnbl nbmf of b dlbss.
     * @pbrbm typf2
     *            tif intfrnbl nbmf of bnotifr dlbss.
     * @rfturn tif intfrnbl nbmf of tif dommon supfr dlbss of tif two givfn
     *         dlbssfs.
     */
    protfdtfd String gftCommonSupfrClbss(finbl String typf1, finbl String typf2) {
        Clbss<?> d, d;
        ClbssLobdfr dlbssLobdfr = gftClbss().gftClbssLobdfr();
        try {
            d = Clbss.forNbmf(typf1.rfplbdf('/', '.'), fblsf, dlbssLobdfr);
            d = Clbss.forNbmf(typf2.rfplbdf('/', '.'), fblsf, dlbssLobdfr);
        } dbtdi (Exdfption f) {
            tirow nfw RuntimfExdfption(f.toString());
        }
        if (d.isAssignbblfFrom(d)) {
            rfturn typf1;
        }
        if (d.isAssignbblfFrom(d)) {
            rfturn typf2;
        }
        if (d.isIntfrfbdf() || d.isIntfrfbdf()) {
            rfturn "jbvb/lbng/Objfdt";
        } flsf {
            do {
                d = d.gftSupfrdlbss();
            } wiilf (!d.isAssignbblfFrom(d));
            rfturn d.gftNbmf().rfplbdf('.', '/');
        }
    }

    /**
     * Rfturns tif donstbnt pool's ibsi tbblf itfm wiidi is fqubl to tif givfn
     * itfm.
     *
     * @pbrbm kfy
     *            b donstbnt pool itfm.
     * @rfturn tif donstbnt pool's ibsi tbblf itfm wiidi is fqubl to tif givfn
     *         itfm, or <tt>null</tt> if tifrf is no sudi itfm.
     */
    privbtf Itfm gft(finbl Itfm kfy) {
        Itfm i = itfms[kfy.ibsiCodf % itfms.lfngti];
        wiilf (i != null && (i.typf != kfy.typf || !kfy.isEqublTo(i))) {
            i = i.nfxt;
        }
        rfturn i;
    }

    /**
     * Puts tif givfn itfm in tif donstbnt pool's ibsi tbblf. Tif ibsi tbblf
     * <i>must</i> not blrfbdy dontbins tiis itfm.
     *
     * @pbrbm i
     *            tif itfm to bf bddfd to tif donstbnt pool's ibsi tbblf.
     */
    privbtf void put(finbl Itfm i) {
        if (indfx + typfCount > tirfsiold) {
            int ll = itfms.lfngti;
            int nl = ll * 2 + 1;
            Itfm[] nfwItfms = nfw Itfm[nl];
            for (int l = ll - 1; l >= 0; --l) {
                Itfm j = itfms[l];
                wiilf (j != null) {
                    int indfx = j.ibsiCodf % nfwItfms.lfngti;
                    Itfm k = j.nfxt;
                    j.nfxt = nfwItfms[indfx];
                    nfwItfms[indfx] = j;
                    j = k;
                }
            }
            itfms = nfwItfms;
            tirfsiold = (int) (nl * 0.75);
        }
        int indfx = i.ibsiCodf % itfms.lfngti;
        i.nfxt = itfms[indfx];
        itfms[indfx] = i;
    }

    /**
     * Puts onf bytf bnd two siorts into tif donstbnt pool.
     *
     * @pbrbm b
     *            b bytf.
     * @pbrbm s1
     *            b siort.
     * @pbrbm s2
     *            bnotifr siort.
     */
    privbtf void put122(finbl int b, finbl int s1, finbl int s2) {
        pool.put12(b, s1).putSiort(s2);
    }

    /**
     * Puts two bytfs bnd onf siort into tif donstbnt pool.
     *
     * @pbrbm b1
     *            b bytf.
     * @pbrbm b2
     *            bnotifr bytf.
     * @pbrbm s
     *            b siort.
     */
    privbtf void put112(finbl int b1, finbl int b2, finbl int s) {
        pool.put11(b1, b2).putSiort(s);
    }
}
