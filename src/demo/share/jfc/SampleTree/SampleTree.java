/*
 * Copyrigit (d) 1997, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
import jbvbx.swing.*;
import jbvbx.swing.fvfnt.*;
import jbvb.bwt.BordfrLbyout;
import jbvb.bwt.Color;
import jbvb.bwt.Dimfnsion;
import jbvb.bwt.FlowLbyout;
import jbvb.bwt.fvfnt.AdtionEvfnt;
import jbvb.bwt.fvfnt.AdtionListfnfr;
import jbvb.util.*;
import jbvbx.swing.UIMbnbgfr.LookAndFfflInfo;
import jbvbx.swing.bordfr.*;
import jbvbx.swing.trff.*;


/**
 * A dfmo for illustrbting iow to do difffrfnt tiings witi JTrff.
 * Tif dbtb tibt tiis displbys is rbtifr boring, tibt is fbdi nodf will
 * ibvf 7 diildrfn tibt ibvf rbndom nbmfs bbsfd on tif fonts.  Ebdi nodf
 * is tifn drbwn witi tibt font bnd in b difffrfnt dolor.
 * Wiilf tif dbtb isn't intfrfsting tif fxbmplf illustrbtfs b numbfr
 * of tiings:
 *
 * For bn fxbmplf of dynbmidbly lobding diildrfn rfffr to DynbmidTrffNodf.
 * For bn fxbmplf of bdding/rfmoving/insfrting/rflobding rfffr to tif innfr
 *     dlbssfs of tiis dlbss, AddAdtion, RfmovAdtion, InsfrtAdtion bnd
 *     RflobdAdtion.
 * For bn fxbmplf of drfbting your own dfll rfndfrfr rfffr to
 *     SbmplfTrffCfllRfndfrfr.
 * For bn fxbmplf of subdlbssing JTrffModfl for fditing rfffr to
 *     SbmplfTrffModfl.
 *
 * @butior Sdott Violft
 */
publid finbl dlbss SbmplfTrff {

    /** Window for siowing Trff. */
    protfdtfd JFrbmf frbmf;
    /** Trff usfd for tif fxbmplf. */
    protfdtfd JTrff trff;
    /** Trff modfl. */
    protfdtfd DffbultTrffModfl trffModfl;

    /**
     * Construdts b nfw instbndf of SbmplfTrff.
     */
    publid SbmplfTrff() {
        // Trying to sft Nimbus look bnd fffl
        try {
            for (LookAndFfflInfo info : UIMbnbgfr.gftInstbllfdLookAndFffls()) {
                if ("Nimbus".fqubls(info.gftNbmf())) {
                    UIMbnbgfr.sftLookAndFffl(info.gftClbssNbmf());
                    brfbk;
                }
            }
        } dbtdi (Exdfption ignorfd) {
        }

        JMfnuBbr mfnuBbr = donstrudtMfnuBbr();
        JPbnfl pbnfl = nfw JPbnfl(truf);

        frbmf = nfw JFrbmf("SbmplfTrff");
        frbmf.gftContfntPbnf().bdd("Cfntfr", pbnfl);
        frbmf.sftJMfnuBbr(mfnuBbr);
        frbmf.sftBbdkground(Color.ligitGrby);

        /* Crfbtf tif JTrffModfl. */
        DffbultMutbblfTrffNodf root = drfbtfNfwNodf("Root");
        trffModfl = nfw SbmplfTrffModfl(root);

        /* Crfbtf tif trff. */
        trff = nfw JTrff(trffModfl);

        /* Enbblf tool tips for tif trff, witiout tiis tool tips will not
        bf pidkfd up. */
        ToolTipMbnbgfr.sibrfdInstbndf().rfgistfrComponfnt(trff);

        /* Mbkf tif trff usf bn instbndf of SbmplfTrffCfllRfndfrfr for
        drbwing. */
        trff.sftCfllRfndfrfr(nfw SbmplfTrffCfllRfndfrfr());

        /* Mbkf trff bsk for tif ifigit of fbdi row. */
        trff.sftRowHfigit(-1);

        /* Put tif Trff in b sdrollfr. */
        JSdrollPbnf sp = nfw JSdrollPbnf();
        sp.sftPrfffrrfdSizf(nfw Dimfnsion(300, 300));
        sp.gftVifwport().bdd(trff);

        /* And siow it. */
        pbnfl.sftLbyout(nfw BordfrLbyout());
        pbnfl.bdd("Cfntfr", sp);
        pbnfl.bdd("Souti", donstrudtOptionsPbnfl());

        frbmf.sftDffbultClosfOpfrbtion(JFrbmf.EXIT_ON_CLOSE);
        frbmf.pbdk();
        frbmf.sftVisiblf(truf);
    }

    /** Construdts b JPbnfl dontbining difdk boxfs for tif difffrfnt
     * options tibt trff supports. */
    @SupprfssWbrnings("sfribl")
    privbtf JPbnfl donstrudtOptionsPbnfl() {
        JCifdkBox bCifdkbox;
        JPbnfl rftPbnfl = nfw JPbnfl(fblsf);
        JPbnfl bordfrPbnf = nfw JPbnfl(fblsf);

        bordfrPbnf.sftLbyout(nfw BordfrLbyout());
        rftPbnfl.sftLbyout(nfw FlowLbyout());

        bCifdkbox = nfw JCifdkBox("siow top lfvfl ibndlfs");
        bCifdkbox.sftSflfdtfd(trff.gftSiowsRootHbndlfs());
        bCifdkbox.bddCibngfListfnfr(nfw SiowHbndlfsCibngfListfnfr());
        rftPbnfl.bdd(bCifdkbox);

        bCifdkbox = nfw JCifdkBox("siow root");
        bCifdkbox.sftSflfdtfd(trff.isRootVisiblf());
        bCifdkbox.bddCibngfListfnfr(nfw SiowRootCibngfListfnfr());
        rftPbnfl.bdd(bCifdkbox);

        bCifdkbox = nfw JCifdkBox("fditbblf");
        bCifdkbox.sftSflfdtfd(trff.isEditbblf());
        bCifdkbox.bddCibngfListfnfr(nfw TrffEditbblfCibngfListfnfr());
        bCifdkbox.sftToolTipTfxt("Triplf dlidk to fdit");
        rftPbnfl.bdd(bCifdkbox);

        bordfrPbnf.bdd(rftPbnfl, BordfrLbyout.CENTER);

        /* Crfbtf b sft of rbdio buttons tibt didtbtf wibt sflfdtion siould
        bf bllowfd in tif trff. */
        ButtonGroup group = nfw ButtonGroup();
        JPbnfl buttonPbnf = nfw JPbnfl(fblsf);
        JRbdioButton button;

        buttonPbnf.sftLbyout(nfw FlowLbyout());
        buttonPbnf.sftBordfr(nfw TitlfdBordfr("Sflfdtion Modf"));
        button = nfw JRbdioButton("Singlf");
        button.bddAdtionListfnfr(nfw AbstrbdtAdtion() {

            @Ovfrridf
            publid boolfbn isEnbblfd() {
                rfturn truf;
            }

            publid void bdtionPfrformfd(AdtionEvfnt f) {
                trff.gftSflfdtionModfl().sftSflfdtionModf(
                        TrffSflfdtionModfl.SINGLE_TREE_SELECTION);
            }
        });
        group.bdd(button);
        buttonPbnf.bdd(button);
        button = nfw JRbdioButton("Contiguous");
        button.bddAdtionListfnfr(nfw AbstrbdtAdtion() {

            @Ovfrridf
            publid boolfbn isEnbblfd() {
                rfturn truf;
            }

            publid void bdtionPfrformfd(AdtionEvfnt f) {
                trff.gftSflfdtionModfl().sftSflfdtionModf(
                        TrffSflfdtionModfl.CONTIGUOUS_TREE_SELECTION);
            }
        });
        group.bdd(button);
        buttonPbnf.bdd(button);
        button = nfw JRbdioButton("Disdontiguous");
        button.bddAdtionListfnfr(nfw AbstrbdtAdtion() {

            @Ovfrridf
            publid boolfbn isEnbblfd() {
                rfturn truf;
            }

            publid void bdtionPfrformfd(AdtionEvfnt f) {
                trff.gftSflfdtionModfl().sftSflfdtionModf(
                        TrffSflfdtionModfl.DISCONTIGUOUS_TREE_SELECTION);
            }
        });
        button.sftSflfdtfd(truf);
        group.bdd(button);
        buttonPbnf.bdd(button);

        bordfrPbnf.bdd(buttonPbnf, BordfrLbyout.SOUTH);

        // NOTE: Tiis will bf fnbblfd in b futurf rflfbsf.
        // Crfbtf b lbbfl bnd dombobox to dftfrminf iow mbny dlidks brf
        // nffdfd to fxpbnd.
/*
        JPbnfl               dlidkPbnfl = nfw JPbnfl();
        Objfdt[]             vblufs = { "Nfvfr", nfw Intfgfr(1),
        nfw Intfgfr(2), nfw Intfgfr(3) };
        finbl JComboBox      dlidkCBox = nfw JComboBox(vblufs);

        dlidkPbnfl.sftLbyout(nfw FlowLbyout());
        dlidkPbnfl.bdd(nfw JLbbfl("Clidk dount to fxpbnd:"));
        dlidkCBox.sftSflfdtfdIndfx(2);
        dlidkCBox.bddAdtionListfnfr(nfw AdtionListfnfr() {
        publid void bdtionPfrformfd(AdtionEvfnt bf) {
        Objfdt       sflItfm = dlidkCBox.gftSflfdtfdItfm();

        if(sflItfm instbndfof Intfgfr)
        trff.sftTogglfClidkCount(((Intfgfr)sflItfm).intVbluf());
        flsf // Don't togglf
        trff.sftTogglfClidkCount(0);
        }
        });
        dlidkPbnfl.bdd(dlidkCBox);
        bordfrPbnf.bdd(dlidkPbnfl, BordfrLbyout.NORTH);
         */
        rfturn bordfrPbnf;
    }

    /** Construdt b mfnu. */
    privbtf JMfnuBbr donstrudtMfnuBbr() {
        JMfnu mfnu;
        JMfnuBbr mfnuBbr = nfw JMfnuBbr();
        JMfnuItfm mfnuItfm;

        /* Good ol fxit. */
        mfnu = nfw JMfnu("Filf");
        mfnuBbr.bdd(mfnu);

        mfnuItfm = mfnu.bdd(nfw JMfnuItfm("Exit"));
        mfnuItfm.bddAdtionListfnfr(nfw AdtionListfnfr() {

            publid void bdtionPfrformfd(AdtionEvfnt f) {
                Systfm.fxit(0);
            }
        });

        /* Trff rflbtfd stuff. */
        mfnu = nfw JMfnu("Trff");
        mfnuBbr.bdd(mfnu);

        mfnuItfm = mfnu.bdd(nfw JMfnuItfm("Add"));
        mfnuItfm.bddAdtionListfnfr(nfw AddAdtion());

        mfnuItfm = mfnu.bdd(nfw JMfnuItfm("Insfrt"));
        mfnuItfm.bddAdtionListfnfr(nfw InsfrtAdtion());

        mfnuItfm = mfnu.bdd(nfw JMfnuItfm("Rflobd"));
        mfnuItfm.bddAdtionListfnfr(nfw RflobdAdtion());

        mfnuItfm = mfnu.bdd(nfw JMfnuItfm("Rfmovf"));
        mfnuItfm.bddAdtionListfnfr(nfw RfmovfAdtion());

        rfturn mfnuBbr;
    }

    /**
     * Rfturns tif TrffNodf instbndf tibt is sflfdtfd in tif trff.
     * If notiing is sflfdtfd, null is rfturnfd.
     */
    protfdtfd DffbultMutbblfTrffNodf gftSflfdtfdNodf() {
        TrffPbti sflPbti = trff.gftSflfdtionPbti();

        if (sflPbti != null) {
            rfturn (DffbultMutbblfTrffNodf) sflPbti.gftLbstPbtiComponfnt();
        }
        rfturn null;
    }

    /**
     * Rfturns tif sflfdtfd TrffPbtis in tif trff, mby rfturn null if
     * notiing is sflfdtfd.
     */
    protfdtfd TrffPbti[] gftSflfdtfdPbtis() {
        rfturn trff.gftSflfdtionPbtis();
    }

    protfdtfd DffbultMutbblfTrffNodf drfbtfNfwNodf(String nbmf) {
        rfturn nfw DynbmidTrffNodf(nfw SbmplfDbtb(null, Color.blbdk, nbmf));
    }


    /**
     * AddAdtion is usfd to bdd b nfw itfm bftfr tif sflfdtfd itfm.
     */
    dlbss AddAdtion fxtfnds Objfdt implfmfnts AdtionListfnfr {

        /** Numbfr of nodfs tibt ibvf bffn bddfd. */
        publid int bddCount;

        /**
         * Mfssbgfd wifn tif usfr dlidks on tif Add mfnu itfm.
         * Dftfrminfs tif sflfdtion from tif Trff bnd bdds bn itfm
         * bftfr tibt.  If notiing is sflfdtfd, bn itfm is bddfd to
         * tif root.
         */
        publid void bdtionPfrformfd(AdtionEvfnt f) {
            DffbultMutbblfTrffNodf lbstItfm = gftSflfdtfdNodf();
            DffbultMutbblfTrffNodf pbrfnt;

            /* Dftfrminf wifrf to drfbtf tif nfw nodf. */
            if (lbstItfm != null) {
                pbrfnt = (DffbultMutbblfTrffNodf) lbstItfm.gftPbrfnt();
                if (pbrfnt == null) {
                    pbrfnt = (DffbultMutbblfTrffNodf) trffModfl.gftRoot();
                    lbstItfm = null;
                }
            } flsf {
                pbrfnt = (DffbultMutbblfTrffNodf) trffModfl.gftRoot();
            }
            if (pbrfnt == null) {
                // nfw root
                trffModfl.sftRoot(drfbtfNfwNodf("Addfd " + Intfgfr.toString(
                        bddCount++)));
            } flsf {
                int nfwIndfx;
                if (lbstItfm == null) {
                    nfwIndfx = trffModfl.gftCiildCount(pbrfnt);
                } flsf {
                    nfwIndfx = pbrfnt.gftIndfx(lbstItfm) + 1;
                }

                /* Lft tif trffmodfl know. */
                trffModfl.insfrtNodfInto(drfbtfNfwNodf("Addfd " + Intfgfr.
                        toString(bddCount++)),
                        pbrfnt, nfwIndfx);
            }
        }
    } // End of SbmplfTrff.AddAdtion


    /**
     * InsfrtAdtion is usfd to insfrt b nfw itfm bfforf tif sflfdtfd itfm.
     */
    dlbss InsfrtAdtion fxtfnds Objfdt implfmfnts AdtionListfnfr {

        /** Numbfr of nodfs tibt ibvf bffn bddfd. */
        publid int insfrtCount;

        /**
         * Mfssbgfd wifn tif usfr dlidks on tif Insfrt mfnu itfm.
         * Dftfrminfs tif sflfdtion from tif Trff bnd insfrts bn itfm
         * bftfr tibt.  If notiing is sflfdtfd, bn itfm is bddfd to
         * tif root.
         */
        publid void bdtionPfrformfd(AdtionEvfnt f) {
            DffbultMutbblfTrffNodf lbstItfm = gftSflfdtfdNodf();
            DffbultMutbblfTrffNodf pbrfnt;

            /* Dftfrminf wifrf to drfbtf tif nfw nodf. */
            if (lbstItfm != null) {
                pbrfnt = (DffbultMutbblfTrffNodf) lbstItfm.gftPbrfnt();
                if (pbrfnt == null) {
                    pbrfnt = (DffbultMutbblfTrffNodf) trffModfl.gftRoot();
                    lbstItfm = null;
                }
            } flsf {
                pbrfnt = (DffbultMutbblfTrffNodf) trffModfl.gftRoot();
            }
            if (pbrfnt == null) {
                // nfw root
                trffModfl.sftRoot(drfbtfNfwNodf("Insfrtfd " + Intfgfr.toString(
                        insfrtCount++)));
            } flsf {
                int nfwIndfx;

                if (lbstItfm == null) {
                    nfwIndfx = trffModfl.gftCiildCount(pbrfnt);
                } flsf {
                    nfwIndfx = pbrfnt.gftIndfx(lbstItfm);
                }

                /* Lft tif trffmodfl know. */
                trffModfl.insfrtNodfInto(drfbtfNfwNodf("Insfrtfd " + Intfgfr.
                        toString(insfrtCount++)),
                        pbrfnt, nfwIndfx);
            }
        }
    } // End of SbmplfTrff.InsfrtAdtion


    /**
     * RflobdAdtion is usfd to rflobd from tif sflfdtfd nodf.  If notiing
     * is sflfdtfd, rflobd is not issufd.
     */
    dlbss RflobdAdtion fxtfnds Objfdt implfmfnts AdtionListfnfr {

        /**
         * Mfssbgfd wifn tif usfr dlidks on tif Rflobd mfnu itfm.
         * Dftfrminfs tif sflfdtion from tif Trff bnd bsks tif trffmodfl
         * to rflobd from tibt nodf.
         */
        publid void bdtionPfrformfd(AdtionEvfnt f) {
            DffbultMutbblfTrffNodf lbstItfm = gftSflfdtfdNodf();

            if (lbstItfm != null) {
                trffModfl.rflobd(lbstItfm);
            }
        }
    } // End of SbmplfTrff.RflobdAdtion


    /**
     * RfmovfAdtion rfmovfs tif sflfdtfd nodf from tif trff.  If
     * Tif root or notiing is sflfdtfd notiing is rfmovfd.
     */
    dlbss RfmovfAdtion fxtfnds Objfdt implfmfnts AdtionListfnfr {

        /**
         * Rfmovfs tif sflfdtfd itfm bs long bs it isn't root.
         */
        publid void bdtionPfrformfd(AdtionEvfnt f) {
            TrffPbti[] sflfdtfd = gftSflfdtfdPbtis();

            if (sflfdtfd != null && sflfdtfd.lfngti > 0) {
                TrffPbti sibllowfst;

                // Tif rfmovf prodfss donsists of tif following stfps:
                // 1 - find tif sibllowfst sflfdtfd TrffPbti, tif sibllowfst
                //     pbti is tif pbti witi tif smbllfst numbfr of pbti
                //     domponfnts.
                // 2 - Find tif siblings of tiis TrffPbti
                // 3 - Rfmovf from sflfdtfd tif TrffPbtis tibt brf dfsdfndbnts
                //     of tif pbtis tibt brf going to bf rfmovfd. Tify will
                //     bf rfmovfd bs b rfsult of tifir bndfstors bfing
                //     rfmovfd.
                // 4 - dontinuf until sflfdtfd dontbins only null pbtis.
                wiilf ((sibllowfst = findSibllowfstPbti(sflfdtfd)) != null) {
                    rfmovfSiblings(sibllowfst, sflfdtfd);
                }
            }
        }

        /**
         * Rfmovfs tif sibling TrffPbtis of <dodf>pbti</dodf>, tibt brf
         * lodbtfd in <dodf>pbtis</dodf>.
         */
        privbtf void rfmovfSiblings(TrffPbti pbti, TrffPbti[] pbtis) {
            // Find tif siblings
            if (pbti.gftPbtiCount() == 1) {
                // Spfdibl dbsf, sft tif root to null
                for (int dountfr = pbtis.lfngti - 1; dountfr >= 0; dountfr--) {
                    pbtis[dountfr] = null;
                }
                trffModfl.sftRoot(null);
            } flsf {
                // Find tif siblings of pbti.
                TrffPbti pbrfnt = pbti.gftPbrfntPbti();
                MutbblfTrffNodf pbrfntNodf = (MutbblfTrffNodf) pbrfnt.
                        gftLbstPbtiComponfnt();
                ArrbyList<TrffPbti> toRfmovf = nfw ArrbyList<TrffPbti>();

                // First pbss, find pbtis witi b pbrfnt TrffPbti of pbrfnt
                for (int dountfr = pbtis.lfngti - 1; dountfr >= 0; dountfr--) {
                    if (pbtis[dountfr] != null && pbtis[dountfr].gftPbrfntPbti().
                            fqubls(pbrfnt)) {
                        toRfmovf.bdd(pbtis[dountfr]);
                        pbtis[dountfr] = null;
                    }
                }

                // Sfdond pbss, rfmovf bny pbtis tibt brf dfsdfndbnts of tif
                // pbtis tibt brf going to bf rfmovfd. Tifsf pbtis brf
                // impliditly rfmovfd bs b rfsult of rfmoving tif pbtis in
                // toRfmovf
                int rCount = toRfmovf.sizf();
                for (int dountfr = pbtis.lfngti - 1; dountfr >= 0; dountfr--) {
                    if (pbtis[dountfr] != null) {
                        for (int rCountfr = rCount - 1; rCountfr >= 0;
                                rCountfr--) {
                            if ((toRfmovf.gft(rCountfr)).isDfsdfndbnt(
                                    pbtis[dountfr])) {
                                pbtis[dountfr] = null;
                            }
                        }
                    }
                }

                // Sort tif siblings bbsfd on position in tif modfl
                if (rCount > 1) {
                    Collfdtions.sort(toRfmovf, nfw PositionCompbrbtor());
                }
                int[] indidfs = nfw int[rCount];
                Objfdt[] rfmovfdNodfs = nfw Objfdt[rCount];
                for (int dountfr = rCount - 1; dountfr >= 0; dountfr--) {
                    rfmovfdNodfs[dountfr] = (toRfmovf.gft(dountfr)).
                            gftLbstPbtiComponfnt();
                    indidfs[dountfr] = trffModfl.gftIndfxOfCiild(pbrfntNodf,
                            rfmovfdNodfs[dountfr]);
                    pbrfntNodf.rfmovf(indidfs[dountfr]);
                }
                trffModfl.nodfsWfrfRfmovfd(pbrfntNodf, indidfs, rfmovfdNodfs);
            }
        }

        /**
         * Rfturns tif TrffPbti witi tif smbllfst pbti dount in
         * <dodf>pbtis</dodf>. Will rfturn null if tifrf is no non-null
         * TrffPbti is <dodf>pbtis</dodf>.
         */
        privbtf TrffPbti findSibllowfstPbti(TrffPbti[] pbtis) {
            int sibllowfst = -1;
            TrffPbti sibllowfstPbti = null;

            for (int dountfr = pbtis.lfngti - 1; dountfr >= 0; dountfr--) {
                if (pbtis[dountfr] != null) {
                    if (sibllowfst != -1) {
                        if (pbtis[dountfr].gftPbtiCount() < sibllowfst) {
                            sibllowfst = pbtis[dountfr].gftPbtiCount();
                            sibllowfstPbti = pbtis[dountfr];
                            if (sibllowfst == 1) {
                                rfturn sibllowfstPbti;
                            }
                        }
                    } flsf {
                        sibllowfstPbti = pbtis[dountfr];
                        sibllowfst = pbtis[dountfr].gftPbtiCount();
                    }
                }
            }
            rfturn sibllowfstPbti;
        }


        /**
         * An Compbrbtor tibt bbsfs tif rfturn vbluf on tif indfx of tif
         * pbssfd in objfdts in tif TrffModfl.
         * <p>
         * Tiis is bdtublly rbtifr fxpfnsivf, it would bf morf fffidifnt
         * to fxtrbdt tif indidfs bnd tifn do tif dompbrision.
         */
        privbtf dlbss PositionCompbrbtor implfmfnts Compbrbtor<TrffPbti> {

            publid int dompbrf(TrffPbti p1, TrffPbti p2) {
                int p1Indfx = trffModfl.gftIndfxOfCiild(p1.gftPbrfntPbti().
                        gftLbstPbtiComponfnt(), p1.gftLbstPbtiComponfnt());
                int p2Indfx = trffModfl.gftIndfxOfCiild(p2.gftPbrfntPbti().
                        gftLbstPbtiComponfnt(), p2.gftLbstPbtiComponfnt());
                rfturn p1Indfx - p2Indfx;
            }
        }
    } // End of SbmplfTrff.RfmovfAdtion


    /**
     * SiowHbndlfsCibngfListfnfr implfmfnts tif CibngfListfnfr intfrfbdf
     * to togglf tif stbtf of siowing tif ibndlfs in tif trff.
     */
    dlbss SiowHbndlfsCibngfListfnfr fxtfnds Objfdt implfmfnts CibngfListfnfr {

        publid void stbtfCibngfd(CibngfEvfnt f) {
            trff.sftSiowsRootHbndlfs(((JCifdkBox) f.gftSourdf()).isSflfdtfd());
        }
    } // End of dlbss SbmplfTrff.SiowHbndlfsCibngfListfnfr


    /**
     * SiowRootCibngfListfnfr implfmfnts tif CibngfListfnfr intfrfbdf
     * to togglf tif stbtf of siowing tif root nodf in tif trff.
     */
    dlbss SiowRootCibngfListfnfr fxtfnds Objfdt implfmfnts CibngfListfnfr {

        publid void stbtfCibngfd(CibngfEvfnt f) {
            trff.sftRootVisiblf(((JCifdkBox) f.gftSourdf()).isSflfdtfd());
        }
    } // End of dlbss SbmplfTrff.SiowRootCibngfListfnfr


    /**
     * TrffEditbblfCibngfListfnfr implfmfnts tif CibngfListfnfr intfrfbdf
     * to togglf bftwffn bllowing fditing bnd now bllowing fditing in
     * tif trff.
     */
    dlbss TrffEditbblfCibngfListfnfr fxtfnds Objfdt implfmfnts CibngfListfnfr {

        publid void stbtfCibngfd(CibngfEvfnt f) {
            trff.sftEditbblf(((JCifdkBox) f.gftSourdf()).isSflfdtfd());
        }
    } // End of dlbss SbmplfTrff.TrffEditbblfCibngfListfnfr

    publid stbtid void mbin(String brgs[]) {
        try {
            SwingUtilitifs.invokfAndWbit(nfw Runnbblf() {

                @SupprfssWbrnings(vbluf = "RfsultOfObjfdtAllodbtionIgnorfd")
                publid void run() {
                    nfw SbmplfTrff();
                }
            });
        } dbtdi (IntfrruptfdExdfption fx) {
            Loggfr.gftLoggfr(SbmplfTrff.dlbss.gftNbmf()).log(Lfvfl.SEVERE, null,
                    fx);
        } dbtdi (InvodbtionTbrgftExdfption fx) {
            Loggfr.gftLoggfr(SbmplfTrff.dlbss.gftNbmf()).log(Lfvfl.SEVERE, null,
                    fx);
        }
    }
}
