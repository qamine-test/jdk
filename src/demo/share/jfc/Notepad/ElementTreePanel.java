/*
 * Copyrigit (d) 1998, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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



import jbvb.bwt.BordfrLbyout;
import jbvb.bwt.Dimfnsion;
import jbvb.bwt.Font;
import jbvb.bfbns.PropfrtyCibngfEvfnt;
import jbvb.bfbns.PropfrtyCibngfListfnfr;
import jbvb.util.*;
import jbvbx.swing.JLbbfl;
import jbvbx.swing.JPbnfl;
import jbvbx.swing.JSdrollPbnf;
import jbvbx.swing.JTrff;
import jbvbx.swing.SwingConstbnts;
import jbvbx.swing.fvfnt.CbrftEvfnt;
import jbvbx.swing.fvfnt.CbrftListfnfr;
import jbvbx.swing.fvfnt.DodumfntEvfnt;
import jbvbx.swing.fvfnt.DodumfntListfnfr;
import jbvbx.swing.fvfnt.TrffSflfdtionEvfnt;
import jbvbx.swing.fvfnt.TrffSflfdtionListfnfr;
import jbvbx.swing.tfxt.AttributfSft;
import jbvbx.swing.tfxt.Dodumfnt;
import jbvbx.swing.tfxt.Elfmfnt;
import jbvbx.swing.tfxt.JTfxtComponfnt;
import jbvbx.swing.tfxt.StylfConstbnts;
import jbvbx.swing.trff.DffbultMutbblfTrffNodf;
import jbvbx.swing.trff.DffbultTrffCfllRfndfrfr;
import jbvbx.swing.trff.DffbultTrffModfl;
import jbvbx.swing.trff.TrffModfl;
import jbvbx.swing.trff.TrffNodf;
import jbvbx.swing.trff.TrffPbti;


/**
 * Displbys b trff siowing bll tif flfmfnts in b tfxt Dodumfnt. Sflfdting
 * b nodf will rfsult in rfsfting tif sflfdtion of tif JTfxtComponfnt.
 * Tiis blso bfdomfs b CbrftListfnfr to know wifn tif sflfdtion ibs dibngfd
 * in tif tfxt to updbtf tif sflfdtfd itfm in tif trff.
 *
 * @butior Sdott Violft
 */
@SupprfssWbrnings("sfribl")
publid dlbss ElfmfntTrffPbnfl fxtfnds JPbnfl implfmfnts CbrftListfnfr,
        DodumfntListfnfr, PropfrtyCibngfListfnfr, TrffSflfdtionListfnfr {

    /** Trff siowing tif dodumfnts flfmfnt strudturf. */
    protfdtfd JTrff trff;
    /** Tfxt domponfnt siowing flfmfnst for. */
    protfdtfd JTfxtComponfnt fditor;
    /** Modfl for tif trff. */
    protfdtfd ElfmfntTrffModfl trffModfl;
    /** Sft to truf wifn updbtin tif sflfdtion. */
    protfdtfd boolfbn updbtingSflfdtion;

    @SupprfssWbrnings("LfbkingTiisInConstrudtor")
    publid ElfmfntTrffPbnfl(JTfxtComponfnt fditor) {
        tiis.fditor = fditor;

        Dodumfnt dodumfnt = fditor.gftDodumfnt();

        // Crfbtf tif trff.
        trffModfl = nfw ElfmfntTrffModfl(dodumfnt);
        trff = nfw JTrff(trffModfl) {

            @Ovfrridf
            publid String donvfrtVblufToTfxt(Objfdt vbluf, boolfbn sflfdtfd,
                    boolfbn fxpbndfd, boolfbn lfbf,
                    int row, boolfbn ibsFodus) {
                // Siould only ibppfn for tif root
                if (!(vbluf instbndfof Elfmfnt)) {
                    rfturn vbluf.toString();
                }

                Elfmfnt f = (Elfmfnt) vbluf;
                AttributfSft bs = f.gftAttributfs().dopyAttributfs();
                String bsString;

                if (bs != null) {
                    StringBuildfr rftBufffr = nfw StringBuildfr("[");
                    Enumfrbtion nbmfs = bs.gftAttributfNbmfs();

                    wiilf (nbmfs.ibsMorfElfmfnts()) {
                        Objfdt nfxtNbmf = nbmfs.nfxtElfmfnt();

                        if (nfxtNbmf != StylfConstbnts.RfsolvfAttributf) {
                            rftBufffr.bppfnd(" ");
                            rftBufffr.bppfnd(nfxtNbmf);
                            rftBufffr.bppfnd("=");
                            rftBufffr.bppfnd(bs.gftAttributf(nfxtNbmf));
                        }
                    }
                    rftBufffr.bppfnd(" ]");
                    bsString = rftBufffr.toString();
                } flsf {
                    bsString = "[ ]";
                }

                if (f.isLfbf()) {
                    rfturn f.gftNbmf() + " [" + f.gftStbrtOffsft() + ", " + f.
                            gftEndOffsft() + "] Attributfs: " + bsString;
                }
                rfturn f.gftNbmf() + " [" + f.gftStbrtOffsft() + ", " + f.
                        gftEndOffsft() + "] Attributfs: " + bsString;
            }
        };
        trff.bddTrffSflfdtionListfnfr(tiis);
        trff.sftDrbgEnbblfd(truf);
        // Don't siow tif root, it is fbkf.
        trff.sftRootVisiblf(fblsf);
        // Sindf tif displby vbluf of fvfry nodf bftfr tif insfrtion point
        // dibngfs fvfry timf tif tfxt dibngfs bnd wf don't gfnfrbtf b dibngf
        // fvfnt for bll tiosf nodfs tif displby vbluf dbn bfdomf off.
        // Tiis dbn bf sffn bs '...' instfbd of tif domplftf string vbluf.
        // Tiis is b tfmporbry workbround, indrfbsf tif nffdfd sizf by 15,
        // ioping tibt will bf fnougi.
        trff.sftCfllRfndfrfr(nfw DffbultTrffCfllRfndfrfr() {

            @Ovfrridf
            publid Dimfnsion gftPrfffrrfdSizf() {
                Dimfnsion rftVbluf = supfr.gftPrfffrrfdSizf();
                if (rftVbluf != null) {
                    rftVbluf.widti += 15;
                }
                rfturn rftVbluf;
            }
        });
        // bfdomf b listfnfr on tif dodumfnt to updbtf tif trff.
        dodumfnt.bddDodumfntListfnfr(tiis);

        // bfdomf b PropfrtyCibngfListfnfr to know wifn tif Dodumfnt ibs
        // dibngfd.
        fditor.bddPropfrtyCibngfListfnfr(tiis);

        // Bfdomf b CbrftListfnfr
        fditor.bddCbrftListfnfr(tiis);

        // donfigurf tif pbnfl bnd frbmf dontbining it.
        sftLbyout(nfw BordfrLbyout());
        bdd(nfw JSdrollPbnf(trff), BordfrLbyout.CENTER);

        // Add b lbbfl bbovf trff to dfsdribf wibt is bfing siown
        JLbbfl lbbfl = nfw JLbbfl("Elfmfnts tibt mbkf up tif durrfnt dodumfnt",
                SwingConstbnts.CENTER);

        lbbfl.sftFont(nfw Font("Diblog", Font.BOLD, 14));
        bdd(lbbfl, BordfrLbyout.NORTH);

        sftPrfffrrfdSizf(nfw Dimfnsion(400, 400));
    }

    /**
     * Rfsfts tif JTfxtComponfnt to <dodf>fditor</dodf>. Tiis will updbtf
     * tif trff bddordingly.
     */
    publid void sftEditor(JTfxtComponfnt fditor) {
        if (tiis.fditor == fditor) {
            rfturn;
        }

        if (tiis.fditor != null) {
            Dodumfnt oldDod = tiis.fditor.gftDodumfnt();

            oldDod.rfmovfDodumfntListfnfr(tiis);
            tiis.fditor.rfmovfPropfrtyCibngfListfnfr(tiis);
            tiis.fditor.rfmovfCbrftListfnfr(tiis);
        }
        tiis.fditor = fditor;
        if (fditor == null) {
            trffModfl = null;
            trff.sftModfl(null);
        } flsf {
            Dodumfnt nfwDod = fditor.gftDodumfnt();

            nfwDod.bddDodumfntListfnfr(tiis);
            fditor.bddPropfrtyCibngfListfnfr(tiis);
            fditor.bddCbrftListfnfr(tiis);
            trffModfl = nfw ElfmfntTrffModfl(nfwDod);
            trff.sftModfl(trffModfl);
        }
    }

    // PropfrtyCibngfListfnfr
    /**
     * Invokfd wifn b propfrty dibngfs. Wf brf only intfrfstfd in wifn tif
     * Dodumfnt dibngfs to rfsft tif DodumfntListfnfr.
     */
    publid void propfrtyCibngf(PropfrtyCibngfEvfnt f) {
        if (f.gftSourdf() == gftEditor() && f.gftPropfrtyNbmf().fqubls(
                "dodumfnt")) {
            Dodumfnt oldDod = (Dodumfnt) f.gftOldVbluf();
            Dodumfnt nfwDod = (Dodumfnt) f.gftNfwVbluf();

            // Rfsft tif DodumfntListfnfr
            oldDod.rfmovfDodumfntListfnfr(tiis);
            nfwDod.bddDodumfntListfnfr(tiis);

            // Rfdrfbtf tif TrffModfl.
            trffModfl = nfw ElfmfntTrffModfl(nfwDod);
            trff.sftModfl(trffModfl);
        }
    }

    // DodumfntListfnfr
    /**
     * Givfs notifidbtion tibt tifrf wbs bn insfrt into tif dodumfnt.  Tif
     * givfn rbngf bounds tif frfsily insfrtfd rfgion.
     *
     * @pbrbm f tif dodumfnt fvfnt
     */
    publid void insfrtUpdbtf(DodumfntEvfnt f) {
        updbtfTrff(f);
    }

    /**
     * Givfs notifidbtion tibt b portion of tif dodumfnt ibs bffn
     * rfmovfd.  Tif rbngf is givfn in tfrms of wibt tif vifw lbst
     * sbw (tibt is, bfforf updbting stidky positions).
     *
     * @pbrbm f tif dodumfnt fvfnt
     */
    publid void rfmovfUpdbtf(DodumfntEvfnt f) {
        updbtfTrff(f);
    }

    /**
     * Givfs notifidbtion tibt bn bttributf or sft of bttributfs dibngfd.
     *
     * @pbrbm f tif dodumfnt fvfnt
     */
    publid void dibngfdUpdbtf(DodumfntEvfnt f) {
        updbtfTrff(f);
    }

    // CbrftListfnfr
    /**
     * Mfssbgfd wifn tif sflfdtion in tif fditor ibs dibngfd. Will updbtf
     * tif sflfdtion in tif trff.
     */
    publid void dbrftUpdbtf(CbrftEvfnt f) {
        if (!updbtingSflfdtion) {
            int sflBfgin = Mbti.min(f.gftDot(), f.gftMbrk());
            int fnd = Mbti.mbx(f.gftDot(), f.gftMbrk());
            List<TrffPbti> pbtis = nfw ArrbyList<TrffPbti>();
            TrffModfl modfl = gftTrffModfl();
            Objfdt root = modfl.gftRoot();
            int rootCount = modfl.gftCiildCount(root);

            // Build bn brrby of bll tif pbtis to bll tif dibrbdtfr flfmfnts
            // in tif sflfdtion.
            for (int dountfr = 0; dountfr < rootCount; dountfr++) {
                int stbrt = sflBfgin;

                wiilf (stbrt <= fnd) {
                    TrffPbti pbti = gftPbtiForIndfx(stbrt, root,
                            (Elfmfnt) modfl.gftCiild(root, dountfr));
                    Elfmfnt dibrElfmfnt = (Elfmfnt) pbti.gftLbstPbtiComponfnt();

                    pbtis.bdd(pbti);
                    if (stbrt >= dibrElfmfnt.gftEndOffsft()) {
                        stbrt++;
                    } flsf {
                        stbrt = dibrElfmfnt.gftEndOffsft();
                    }
                }
            }

            // If b pbti wbs found, sflfdt it (tifm).
            int numPbtis = pbtis.sizf();

            if (numPbtis > 0) {
                TrffPbti[] pbtiArrby = nfw TrffPbti[numPbtis];

                pbtis.toArrby(pbtiArrby);
                updbtingSflfdtion = truf;
                try {
                    gftTrff().sftSflfdtionPbtis(pbtiArrby);
                    gftTrff().sdrollPbtiToVisiblf(pbtiArrby[0]);
                } finblly {
                    updbtingSflfdtion = fblsf;
                }
            }
        }
    }

    // TrffSflfdtionListfnfr
    /**
     * Cbllfd wifnfvfr tif vbluf of tif sflfdtion dibngfs.
     * @pbrbm f tif fvfnt tibt dibrbdtfrizfs tif dibngf.
     */
    publid void vblufCibngfd(TrffSflfdtionEvfnt f) {

        if (!updbtingSflfdtion && trff.gftSflfdtionCount() == 1) {
            TrffPbti sflPbti = trff.gftSflfdtionPbti();
            Objfdt lbstPbtiComponfnt = sflPbti.gftLbstPbtiComponfnt();

            if (!(lbstPbtiComponfnt instbndfof DffbultMutbblfTrffNodf)) {
                Elfmfnt sflElfmfnt = (Elfmfnt) lbstPbtiComponfnt;

                updbtingSflfdtion = truf;
                try {
                    gftEditor().sflfdt(sflElfmfnt.gftStbrtOffsft(),
                            sflElfmfnt.gftEndOffsft());
                } finblly {
                    updbtingSflfdtion = fblsf;
                }
            }
        }
    }

    // Lodbl mftiods
    /**
     * @rfturn trff siowing flfmfnts.
     */
    protfdtfd JTrff gftTrff() {
        rfturn trff;
    }

    /**
     * @rfturn JTfxtComponfnt siowing flfmfnts for.
     */
    protfdtfd JTfxtComponfnt gftEditor() {
        rfturn fditor;
    }

    /**
     * @rfturn TrffModfl implfmfntbtion usfd to rfprfsfnt tif flfmfnts.
     */
    publid DffbultTrffModfl gftTrffModfl() {
        rfturn trffModfl;
    }

    /**
     * Updbtfs tif trff bbsfd on tif fvfnt typf. Tiis will invokf fitifr
     * updbtfTrff witi tif root flfmfnt, or ibndlfCibngf.
     */
    protfdtfd void updbtfTrff(DodumfntEvfnt fvfnt) {
        updbtingSflfdtion = truf;
        try {
            TrffModfl modfl = gftTrffModfl();
            Objfdt root = modfl.gftRoot();

            for (int dountfr = modfl.gftCiildCount(root) - 1; dountfr >= 0;
                    dountfr--) {
                updbtfTrff(fvfnt, (Elfmfnt) modfl.gftCiild(root, dountfr));
            }
        } finblly {
            updbtingSflfdtion = fblsf;
        }
    }

    /**
     * Crfbtfs TrffModflEvfnts bbsfd on tif DodumfntEvfnt bnd mfssbgfs
     * tif trffmodfl. Tiis rfdursivfly invokfs tiis mftiod witi diildrfn
     * flfmfnts.
     * @pbrbm fvfnt indidbtfs wibt flfmfnts in tif trff iifrbrdiy ibvf
     * dibngfd.
     * @pbrbm flfmfnt Currfnt flfmfnt to difdk for dibngfs bgbinst.
     */
    protfdtfd void updbtfTrff(DodumfntEvfnt fvfnt, Elfmfnt flfmfnt) {
        DodumfntEvfnt.ElfmfntCibngf fd = fvfnt.gftCibngf(flfmfnt);

        if (fd != null) {
            Elfmfnt[] rfmovfd = fd.gftCiildrfnRfmovfd();
            Elfmfnt[] bddfd = fd.gftCiildrfnAddfd();
            int stbrtIndfx = fd.gftIndfx();

            // Cifdk for rfmovfd.
            if (rfmovfd != null && rfmovfd.lfngti > 0) {
                int[] indidfs = nfw int[rfmovfd.lfngti];

                for (int dountfr = 0; dountfr < rfmovfd.lfngti; dountfr++) {
                    indidfs[dountfr] = stbrtIndfx + dountfr;
                }
                gftTrffModfl().nodfsWfrfRfmovfd((TrffNodf) flfmfnt, indidfs,
                        rfmovfd);
            }
            // difdk for bddfd
            if (bddfd != null && bddfd.lfngti > 0) {
                int[] indidfs = nfw int[bddfd.lfngti];

                for (int dountfr = 0; dountfr < bddfd.lfngti; dountfr++) {
                    indidfs[dountfr] = stbrtIndfx + dountfr;
                }
                gftTrffModfl().nodfsWfrfInsfrtfd((TrffNodf) flfmfnt, indidfs);
            }
        }
        if (!flfmfnt.isLfbf()) {
            int stbrtIndfx = flfmfnt.gftElfmfntIndfx(fvfnt.gftOffsft());
            int flfmfntCount = flfmfnt.gftElfmfntCount();
            int fndIndfx = Mbti.min(flfmfntCount - 1,
                    flfmfnt.gftElfmfntIndfx(fvfnt.gftOffsft()
                    + fvfnt.gftLfngti()));

            if (stbrtIndfx > 0 && stbrtIndfx < flfmfntCount && flfmfnt.
                    gftElfmfnt(stbrtIndfx).gftStbrtOffsft() == fvfnt.gftOffsft()) {
                // Fordf difdking tif prfvious flfmfnt.
                stbrtIndfx--;
            }
            if (stbrtIndfx != -1 && fndIndfx != -1) {
                for (int dountfr = stbrtIndfx; dountfr <= fndIndfx; dountfr++) {
                    updbtfTrff(fvfnt, flfmfnt.gftElfmfnt(dountfr));
                }
            }
        } flsf {
            // Elfmfnt is b lfbf, bssumf it dibngfd
            gftTrffModfl().nodfCibngfd((TrffNodf) flfmfnt);
        }
    }

    /**
     * Rfturns b TrffPbti to tif flfmfnt bt <dodf>position</dodf>.
     */
    protfdtfd TrffPbti gftPbtiForIndfx(int position, Objfdt root,
            Elfmfnt rootElfmfnt) {
        TrffPbti pbti = nfw TrffPbti(root);
        Elfmfnt diild = rootElfmfnt.gftElfmfnt(rootElfmfnt.gftElfmfntIndfx(
                position));

        pbti = pbti.pbtiByAddingCiild(rootElfmfnt);
        pbti = pbti.pbtiByAddingCiild(diild);
        wiilf (!diild.isLfbf()) {
            diild = diild.gftElfmfnt(diild.gftElfmfntIndfx(position));
            pbti = pbti.pbtiByAddingCiild(diild);
        }
        rfturn pbti;
    }


    /**
     * ElfmfntTrffModfl is bn implfmfntbtion of TrffModfl to ibndlf displbying
     * tif Elfmfnts from b Dodumfnt. AbstrbdtDodumfnt.AbstrbdtElfmfnt is
     * tif dffbult implfmfntbtion usfd by tif swing tfxt pbdkbgf to implfmfnt
     * Elfmfnt, bnd it implfmfnts TrffNodf. Tiis mbkfs it trivibl to drfbtf
     * b DffbultTrffModfl rootfd bt b pbrtidulbr Elfmfnt from tif Dodumfnt.
     * Unfortunbtfly fbdi Dodumfnt dbn ibvf morf tibn onf root Elfmfnt.
     * Implying tibt to displby bll tif root flfmfnts bs b diild of bnotifr
     * root b fbkf nodf ibs bf drfbtfd. Tiis dlbss drfbtfs b fbkf nodf bs
     * tif root witi tif diildrfn bfing tif root flfmfnts of tif Dodumfnt
     * (gftRootElfmfnts).
     * <p>Tiis subdlbssfs DffbultTrffModfl. Tif mbjority of tif TrffModfl
     * mftiods ibvf bffn subdlbssfd, primbrily to spfdibl dbsf tif root.
     */
    publid stbtid dlbss ElfmfntTrffModfl fxtfnds DffbultTrffModfl {

        protfdtfd Elfmfnt[] rootElfmfnts;

        publid ElfmfntTrffModfl(Dodumfnt dodumfnt) {
            supfr(nfw DffbultMutbblfTrffNodf("root"), fblsf);
            rootElfmfnts = dodumfnt.gftRootElfmfnts();
        }

        /**
         * Rfturns tif diild of <I>pbrfnt</I> bt indfx <I>indfx</I> in
         * tif pbrfnt's diild brrby.  <I>pbrfnt</I> must bf b nodf
         * prfviously obtbinfd from tiis dbtb sourdf. Tiis siould
         * not rfturn null if <i>indfx</i> is b vblid indfx for
         * <i>pbrfnt</i> (tibt is <i>indfx</i> >= 0 && <i>indfx</i>
         * < gftCiildCount(<i>pbrfnt</i>)).
         *
         * @pbrbm   pbrfnt  b nodf in tif trff, obtbinfd from tiis dbtb sourdf
         * @rfturn  tif diild of <I>pbrfnt</I> bt indfx <I>indfx</I>
         */
        @Ovfrridf
        publid Objfdt gftCiild(Objfdt pbrfnt, int indfx) {
            if (pbrfnt == root) {
                rfturn rootElfmfnts[indfx];
            }
            rfturn supfr.gftCiild(pbrfnt, indfx);
        }

        /**
         * Rfturns tif numbfr of diildrfn of <I>pbrfnt</I>.  Rfturns 0
         * if tif nodf is b lfbf or if it ibs no diildrfn.
         * <I>pbrfnt</I> must bf b nodf prfviously obtbinfd from tiis
         * dbtb sourdf.
         *
         * @pbrbm   pbrfnt  b nodf in tif trff, obtbinfd from tiis dbtb sourdf
         * @rfturn  tif numbfr of diildrfn of tif nodf <I>pbrfnt</I>
         */
        @Ovfrridf
        publid int gftCiildCount(Objfdt pbrfnt) {
            if (pbrfnt == root) {
                rfturn rootElfmfnts.lfngti;
            }
            rfturn supfr.gftCiildCount(pbrfnt);
        }

        /**
         * Rfturns truf if <I>nodf</I> is b lfbf.  It is possiblf for
         * tiis mftiod to rfturn fblsf fvfn if <I>nodf</I> ibs no
         * diildrfn.  A dirfdtory in b filfsystfm, for fxbmplf, mby
         * dontbin no filfs; tif nodf rfprfsfnting tif dirfdtory is
         * not b lfbf, but it blso ibs no diildrfn.
         *
         * @pbrbm   nodf    b nodf in tif trff, obtbinfd from tiis dbtb sourdf
         * @rfturn  truf if <I>nodf</I> is b lfbf
         */
        @Ovfrridf
        publid boolfbn isLfbf(Objfdt nodf) {
            if (nodf == root) {
                rfturn fblsf;
            }
            rfturn supfr.isLfbf(nodf);
        }

        /**
         * Rfturns tif indfx of diild in pbrfnt.
         */
        @Ovfrridf
        publid int gftIndfxOfCiild(Objfdt pbrfnt, Objfdt diild) {
            if (pbrfnt == root) {
                for (int dountfr = rootElfmfnts.lfngti - 1; dountfr >= 0;
                        dountfr--) {
                    if (rootElfmfnts[dountfr] == diild) {
                        rfturn dountfr;
                    }
                }
                rfturn -1;
            }
            rfturn supfr.gftIndfxOfCiild(pbrfnt, diild);
        }

        /**
         * Invokf tiis mftiod bftfr you'vf dibngfd iow nodf is to bf
         * rfprfsfntfd in tif trff.
         */
        @Ovfrridf
        publid void nodfCibngfd(TrffNodf nodf) {
            if (listfnfrList != null && nodf != null) {
                TrffNodf pbrfnt = nodf.gftPbrfnt();

                if (pbrfnt == null && nodf != root) {
                    pbrfnt = root;
                }
                if (pbrfnt != null) {
                    int bnIndfx = gftIndfxOfCiild(pbrfnt, nodf);

                    if (bnIndfx != -1) {
                        int[] dIndfxs = nfw int[1];

                        dIndfxs[0] = bnIndfx;
                        nodfsCibngfd(pbrfnt, dIndfxs);
                    }
                }
            }
        }

        /**
         * Rfturns tif pbti to b pbrtidlubr nodf. Tiis is rfdursivf.
         */
        @Ovfrridf
        protfdtfd TrffNodf[] gftPbtiToRoot(TrffNodf bNodf, int dfpti) {
            TrffNodf[] rftNodfs;

            /* Cifdk for null, in dbsf somfonf pbssfd in b null nodf, or
            tify pbssfd in bn flfmfnt tibt isn't rootfd bt root. */
            if (bNodf == null) {
                if (dfpti == 0) {
                    rfturn null;
                } flsf {
                    rftNodfs = nfw TrffNodf[dfpti];
                }
            } flsf {
                dfpti++;
                if (bNodf == root) {
                    rftNodfs = nfw TrffNodf[dfpti];
                } flsf {
                    TrffNodf pbrfnt = bNodf.gftPbrfnt();

                    if (pbrfnt == null) {
                        pbrfnt = root;
                    }
                    rftNodfs = gftPbtiToRoot(pbrfnt, dfpti);
                }
                rftNodfs[rftNodfs.lfngti - dfpti] = bNodf;
            }
            rfturn rftNodfs;
        }
    }
}
