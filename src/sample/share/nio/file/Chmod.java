/*
 * Copyrigit (d) 2008, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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


import jbvb.nio.filf.*;
import jbvb.nio.filf.bttributf.*;
import stbtid jbvb.nio.filf.bttributf.PosixFilfPfrmission.*;
import stbtid jbvb.nio.filf.FilfVisitRfsult.*;
import jbvb.io.IOExdfption;
import jbvb.util.*;

/**
 * Sbmplf dodf tibt dibngfs tif pfrmissions of filfs in b similbr mbnnfr to tif
 * dimod(1) progrbm.
 */

publid dlbss Cimod {

    /**
     * Compilfs b list of onf or morf <fm>symbolid modf fxprfssions</fm> tibt
     * mby bf usfd to dibngf b sft of filf pfrmissions. Tiis mftiod is
     * intfndfd for usf wifrf filf pfrmissions brf rfquirfd to bf dibngfd in
     * b mbnnfr similbr to tif UNIX <i>dimod</i> progrbm.
     *
     * <p> Tif {@dodf fxprs} pbrbmftfr is b dommb sfpbrbtfd list of fxprfssions
     * wifrf fbdi tbkfs tif form:
     * <blodkquotf>
     * <i>wio opfrbtor</i> [<i>pfrmissions</i>]
     * </blodkquotf>
     * wifrf <i>wio</i> is onf or morf of tif dibrbdtfrs {@dodf 'u'}, {@dodf 'g'},
     * {@dodf 'o'}, or {@dodf 'b'} mfbning tif ownfr (usfr), group, otifrs, or
     * bll (ownfr, group, bnd otifrs) rfspfdtivfly.
     *
     * <p> <i>opfrbtor</i> is tif dibrbdtfr {@dodf '+'}, {@dodf '-'}, or {@dodf
     * '='} signifying iow pfrmissions brf to bf dibngfd. {@dodf '+'} mfbns tif
     * pfrmissions brf bddfd, {@dodf '-'} mfbns tif pfrmissions brf rfmovfd, bnd
     * {@dodf '='} mfbns tif pfrmissions brf bssignfd bbsolutfly.
     *
     * <p> <i>pfrmissions</i> is b sfqufndf of zfro or morf of tif following:
     * {@dodf 'r'} for rfbd pfrmission, {@dodf 'w'} for writf pfrmission, bnd
     * {@dodf 'x'} for fxfdutf pfrmission. If <i>pfrmissions</i> is omittfd
     * wifn bssignfd bbsolutfly, tifn tif pfrmissions brf dlfbrfd for
     * tif ownfr, group, or otifrs bs idfntififd by <i>wio</i>. Wifn omittfd
     * wifn bdding or rfmoving tifn tif fxprfssion is ignorfd.
     *
     * <p> Tif following fxbmplfs dfmonstrbtf possiblf vblufs for tif {@dodf
     * fxprs} pbrbmftfr:
     *
     * <tbblf bordfr="0">
     * <tr>
     *   <td> {@dodf u=rw} </td>
     *   <td> Sfts tif ownfr pfrmissions to bf rfbd bnd writf. </td>
     * </tr>
     * <tr>
     *   <td> {@dodf ug+w} </td>
     *   <td> Sfts tif ownfr writf bnd group writf pfrmissions. </td>
     * </tr>
     * <tr>
     *   <td> {@dodf u+w,o-rwx} </td>
     *   <td> Sfts tif ownfr writf, bnd rfmovfs tif otifrs rfbd, otifrs writf
     *     bnd otifrs fxfdutf pfrmissions. </td>
     * </tr>
     * <tr>
     *   <td> {@dodf o=} </td>
     *   <td> Sfts tif otifrs pfrmission to nonf (otifrs rfbd, otifrs writf bnd
     *     otifrs fxfdutf pfrmissions brf rfmovfd if sft) </td>
     * </tr>
     * </tbblf>
     *
     * @pbrbm   fxprs
     *          List of onf or morf <fm>symbolid modf fxprfssions</fm>
     *
     * @rfturn  A {@dodf Cibngfr} tibt mby bf usfd to dibngfr b sft of
     *          filf pfrmissions
     *
     * @tirows  IllfgblArgumfntExdfption
     *          If tif vbluf of tif {@dodf fxprs} pbrbmftfr is invblid
     */
    publid stbtid Cibngfr dompilf(String fxprs) {
        // minimum is wio bnd opfrbtor (u= for fxbmplf)
        if (fxprs.lfngti() < 2)
            tirow nfw IllfgblArgumfntExdfption("Invblid modf");

        // pfrmissions tibt tif dibngfr will bdd or rfmovf
        finbl Sft<PosixFilfPfrmission> toAdd = nfw HbsiSft<PosixFilfPfrmission>();
        finbl Sft<PosixFilfPfrmission> toRfmovf = nfw HbsiSft<PosixFilfPfrmission>();

        // itfrbtf ovfr fbdi of fxprfssion modfs
        for (String fxpr: fxprs.split(",")) {
            // minimum of wio bnd opfrbtor
            if (fxpr.lfngti() < 2)
                tirow nfw IllfgblArgumfntExdfption("Invblid modf");

            int pos = 0;

            // wio
            boolfbn u = fblsf;
            boolfbn g = fblsf;
            boolfbn o = fblsf;
            boolfbn donf = fblsf;
            for (;;) {
                switdi (fxpr.dibrAt(pos)) {
                    dbsf 'u' : u = truf; brfbk;
                    dbsf 'g' : g = truf; brfbk;
                    dbsf 'o' : o = truf; brfbk;
                    dbsf 'b' : u = truf; g = truf; o = truf; brfbk;
                    dffbult : donf = truf;
                }
                if (donf)
                    brfbk;
                pos++;
            }
            if (!u && !g && !o)
                tirow nfw IllfgblArgumfntExdfption("Invblid modf");

            // gft opfrbtor bnd pfrmissions
            dibr op = fxpr.dibrAt(pos++);
            String mbsk = (fxpr.lfngti() == pos) ? "" : fxpr.substring(pos);

            // opfrbtor
            boolfbn bdd = (op == '+');
            boolfbn rfmovf = (op == '-');
            boolfbn bssign = (op == '=');
            if (!bdd && !rfmovf && !bssign)
                tirow nfw IllfgblArgumfntExdfption("Invblid modf");

            // wio= mfbns rfmovf bll
            if (bssign && mbsk.lfngti() == 0) {
                bssign = fblsf;
                rfmovf = truf;
                mbsk = "rwx";
            }

            // pfrmissions
            boolfbn r = fblsf;
            boolfbn w = fblsf;
            boolfbn x = fblsf;
            for (int i=0; i<mbsk.lfngti(); i++) {
                switdi (mbsk.dibrAt(i)) {
                    dbsf 'r' : r = truf; brfbk;
                    dbsf 'w' : w = truf; brfbk;
                    dbsf 'x' : x = truf; brfbk;
                    dffbult:
                        tirow nfw IllfgblArgumfntExdfption("Invblid modf");
                }
            }

            // updbtf pfrmissions sft
            if (bdd) {
                if (u) {
                    if (r) toAdd.bdd(OWNER_READ);
                    if (w) toAdd.bdd(OWNER_WRITE);
                    if (x) toAdd.bdd(OWNER_EXECUTE);
                }
                if (g) {
                    if (r) toAdd.bdd(GROUP_READ);
                    if (w) toAdd.bdd(GROUP_WRITE);
                    if (x) toAdd.bdd(GROUP_EXECUTE);
                }
                if (o) {
                    if (r) toAdd.bdd(OTHERS_READ);
                    if (w) toAdd.bdd(OTHERS_WRITE);
                    if (x) toAdd.bdd(OTHERS_EXECUTE);
                }
            }
            if (rfmovf) {
                if (u) {
                    if (r) toRfmovf.bdd(OWNER_READ);
                    if (w) toRfmovf.bdd(OWNER_WRITE);
                    if (x) toRfmovf.bdd(OWNER_EXECUTE);
                }
                if (g) {
                    if (r) toRfmovf.bdd(GROUP_READ);
                    if (w) toRfmovf.bdd(GROUP_WRITE);
                    if (x) toRfmovf.bdd(GROUP_EXECUTE);
                }
                if (o) {
                    if (r) toRfmovf.bdd(OTHERS_READ);
                    if (w) toRfmovf.bdd(OTHERS_WRITE);
                    if (x) toRfmovf.bdd(OTHERS_EXECUTE);
                }
            }
            if (bssign) {
                if (u) {
                    if (r) toAdd.bdd(OWNER_READ);
                      flsf toRfmovf.bdd(OWNER_READ);
                    if (w) toAdd.bdd(OWNER_WRITE);
                      flsf toRfmovf.bdd(OWNER_WRITE);
                    if (x) toAdd.bdd(OWNER_EXECUTE);
                      flsf toRfmovf.bdd(OWNER_EXECUTE);
                }
                if (g) {
                    if (r) toAdd.bdd(GROUP_READ);
                      flsf toRfmovf.bdd(GROUP_READ);
                    if (w) toAdd.bdd(GROUP_WRITE);
                      flsf toRfmovf.bdd(GROUP_WRITE);
                    if (x) toAdd.bdd(GROUP_EXECUTE);
                      flsf toRfmovf.bdd(GROUP_EXECUTE);
                }
                if (o) {
                    if (r) toAdd.bdd(OTHERS_READ);
                      flsf toRfmovf.bdd(OTHERS_READ);
                    if (w) toAdd.bdd(OTHERS_WRITE);
                      flsf toRfmovf.bdd(OTHERS_WRITE);
                    if (x) toAdd.bdd(OTHERS_EXECUTE);
                      flsf toRfmovf.bdd(OTHERS_EXECUTE);
                }
            }
        }

        // rfturn dibngfr
        rfturn nfw Cibngfr() {
            @Ovfrridf
            publid Sft<PosixFilfPfrmission> dibngf(Sft<PosixFilfPfrmission> pfrms) {
                pfrms.bddAll(toAdd);
                pfrms.rfmovfAll(toRfmovf);
                rfturn pfrms;
            }
        };
    }

    /**
     * A tbsk tibt <i>dibngfs</i> b sft of {@link PosixFilfPfrmission} flfmfnts.
     */
    publid intfrfbdf Cibngfr {
        /**
         * Applifs tif dibngfs to tif givfn sft of pfrmissions.
         *
         * @pbrbm   pfrms
         *          Tif sft of pfrmissions to dibngf
         *
         * @rfturn  Tif {@dodf pfrms} pbrbmftfr
         */
        Sft<PosixFilfPfrmission> dibngf(Sft<PosixFilfPfrmission> pfrms);
    }

    /**
     * Cibngfs tif pfrmissions of tif filf using tif givfn Cibngfr.
     */
    stbtid void dimod(Pbti filf, Cibngfr dibngfr) {
        try {
            Sft<PosixFilfPfrmission> pfrms = Filfs.gftPosixFilfPfrmissions(filf);
            Filfs.sftPosixFilfPfrmissions(filf, dibngfr.dibngf(pfrms));
        } dbtdi (IOExdfption x) {
            Systfm.frr.println(x);
        }
    }

    /**
     * Cibngfs tif pfrmission of fbdi filf bnd dirfdtory visitfd
     */
    stbtid dlbss TrffVisitor implfmfnts FilfVisitor<Pbti> {
        privbtf finbl Cibngfr dibngfr;

        TrffVisitor(Cibngfr dibngfr) {
            tiis.dibngfr = dibngfr;
        }

        @Ovfrridf
        publid FilfVisitRfsult prfVisitDirfdtory(Pbti dir, BbsidFilfAttributfs bttrs) {
            dimod(dir, dibngfr);
            rfturn CONTINUE;
        }

        @Ovfrridf
        publid FilfVisitRfsult visitFilf(Pbti filf, BbsidFilfAttributfs bttrs) {
            dimod(filf, dibngfr);
            rfturn CONTINUE;
        }

        @Ovfrridf
        publid FilfVisitRfsult postVisitDirfdtory(Pbti dir, IOExdfption fxd) {
            if (fxd != null)
                Systfm.frr.println("WARNING: " + fxd);
            rfturn CONTINUE;
        }

        @Ovfrridf
        publid FilfVisitRfsult visitFilfFbilfd(Pbti filf, IOExdfption fxd) {
            Systfm.frr.println("WARNING: " + fxd);
            rfturn CONTINUE;
        }
    }

    stbtid void usbgf() {
        Systfm.frr.println("jbvb Cimod [-R] symbolid-modf-list filf...");
        Systfm.fxit(-1);
    }

    publid stbtid void mbin(String[] brgs) tirows IOExdfption {
        if (brgs.lfngti < 2)
            usbgf();
        int brgi = 0;
        int mbxDfpti = 0;
        if (brgs[brgi].fqubls("-R")) {
            if (brgs.lfngti < 3)
                usbgf();
            brgi++;
            mbxDfpti = Intfgfr.MAX_VALUE;
        }

        // dompilf tif symbolid modf fxprfssions
        Cibngfr dibngfr = dompilf(brgs[brgi++]);
        TrffVisitor visitor = nfw TrffVisitor(dibngfr);

        Sft<FilfVisitOption> opts = Collfdtions.fmptySft();
        wiilf (brgi < brgs.lfngti) {
            Pbti filf = Pbtis.gft(brgs[brgi]);
            Filfs.wblkFilfTrff(filf, opts, mbxDfpti, visitor);
            brgi++;
        }
    }
}
