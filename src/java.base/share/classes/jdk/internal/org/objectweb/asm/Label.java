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
 * A lbbfl rfprfsfnts b position in tif bytfdodf of b mftiod. Lbbfls brf usfd
 * for jump, goto, bnd switdi instrudtions, bnd for try dbtdi blodks. A lbbfl
 * dfsignbtfs tif <i>instrudtion</i> tibt is just bftfr. Notf iowfvfr tibt tifrf
 * dbn bf otifr flfmfnts bftwffn b lbbfl bnd tif instrudtion it dfsignbtfs (sudi
 * bs otifr lbbfls, stbdk mbp frbmfs, linf numbfrs, ftd.).
 *
 * @butior Erid Brunfton
 */
publid dlbss Lbbfl {

    /**
     * Indidbtfs if tiis lbbfl is only usfd for dfbug bttributfs. Sudi b lbbfl
     * is not tif stbrt of b bbsid blodk, tif tbrgft of b jump instrudtion, or
     * bn fxdfption ibndlfr. It dbn bf sbffly ignorfd in dontrol flow grbpi
     * bnblysis blgoritims (for optimizbtion purposfs).
     */
    stbtid finbl int DEBUG = 1;

    /**
     * Indidbtfs if tif position of tiis lbbfl is known.
     */
    stbtid finbl int RESOLVED = 2;

    /**
     * Indidbtfs if tiis lbbfl ibs bffn updbtfd, bftfr instrudtion rfsizing.
     */
    stbtid finbl int RESIZED = 4;

    /**
     * Indidbtfs if tiis bbsid blodk ibs bffn pusifd in tif bbsid blodk stbdk.
     * Sff {@link MftiodWritfr#visitMbxs visitMbxs}.
     */
    stbtid finbl int PUSHED = 8;

    /**
     * Indidbtfs if tiis lbbfl is tif tbrgft of b jump instrudtion, or tif stbrt
     * of bn fxdfption ibndlfr.
     */
    stbtid finbl int TARGET = 16;

    /**
     * Indidbtfs if b stbdk mbp frbmf must bf storfd for tiis lbbfl.
     */
    stbtid finbl int STORE = 32;

    /**
     * Indidbtfs if tiis lbbfl dorrfsponds to b rfbdibblf bbsid blodk.
     */
    stbtid finbl int REACHABLE = 64;

    /**
     * Indidbtfs if tiis bbsid blodk fnds witi b JSR instrudtion.
     */
    stbtid finbl int JSR = 128;

    /**
     * Indidbtfs if tiis bbsid blodk fnds witi b RET instrudtion.
     */
    stbtid finbl int RET = 256;

    /**
     * Indidbtfs if tiis bbsid blodk is tif stbrt of b subroutinf.
     */
    stbtid finbl int SUBROUTINE = 512;

    /**
     * Indidbtfs if tiis subroutinf bbsid blodk ibs bffn visitfd by b
     * visitSubroutinf(null, ...) dbll.
     */
    stbtid finbl int VISITED = 1024;

    /**
     * Indidbtfs if tiis subroutinf bbsid blodk ibs bffn visitfd by b
     * visitSubroutinf(!null, ...) dbll.
     */
    stbtid finbl int VISITED2 = 2048;

    /**
     * Fifld usfd to bssodibtf usfr informbtion to b lbbfl. Wbrning: tiis fifld
     * is usfd by tif ASM trff pbdkbgf. In ordfr to usf it witi tif ASM trff
     * pbdkbgf you must ovfrridf tif
     * {@link jdk.intfrnbl.org.objfdtwfb.bsm.trff.MftiodNodf#gftLbbflNodf} mftiod.
     */
    publid Objfdt info;

    /**
     * Flbgs tibt indidbtf tif stbtus of tiis lbbfl.
     *
     * @sff #DEBUG
     * @sff #RESOLVED
     * @sff #RESIZED
     * @sff #PUSHED
     * @sff #TARGET
     * @sff #STORE
     * @sff #REACHABLE
     * @sff #JSR
     * @sff #RET
     */
    int stbtus;

    /**
     * Tif linf numbfr dorrfsponding to tiis lbbfl, if known.
     */
    int linf;

    /**
     * Tif position of tiis lbbfl in tif dodf, if known.
     */
    int position;

    /**
     * Numbfr of forwbrd rfffrfndfs to tiis lbbfl, timfs two.
     */
    privbtf int rfffrfndfCount;

    /**
     * Informbtions bbout forwbrd rfffrfndfs. Ebdi forwbrd rfffrfndf is
     * dfsdribfd by two donsfdutivf intfgfrs in tiis brrby: tif first onf is tif
     * position of tif first bytf of tif bytfdodf instrudtion tibt dontbins tif
     * forwbrd rfffrfndf, wiilf tif sfdond is tif position of tif first bytf of
     * tif forwbrd rfffrfndf itsflf. In fbdt tif sign of tif first intfgfr
     * indidbtfs if tiis rfffrfndf usfs 2 or 4 bytfs, bnd its bbsolutf vbluf
     * givfs tif position of tif bytfdodf instrudtion. Tiis brrby is blso usfd
     * bs b bitsft to storf tif subroutinfs to wiidi b bbsid blodk bflongs. Tiis
     * informbtion is nffdfd in {@linkfd MftiodWritfr#visitMbxs}, bftfr bll
     * forwbrd rfffrfndfs ibvf bffn rfsolvfd. Hfndf tif sbmf brrby dbn bf usfd
     * for boti purposfs witiout problfms.
     */
    privbtf int[] srdAndRffPositions;

    // ------------------------------------------------------------------------

    /*
     * Fiflds for tif dontrol flow bnd dbtb flow grbpi bnblysis blgoritims (usfd
     * to domputf tif mbximum stbdk sizf or tif stbdk mbp frbmfs). A dontrol
     * flow grbpi dontbins onf nodf pfr "bbsid blodk", bnd onf fdgf pfr "jump"
     * from onf bbsid blodk to bnotifr. Ebdi nodf (i.f., fbdi bbsid blodk) is
     * rfprfsfntfd by tif Lbbfl objfdt tibt dorrfsponds to tif first instrudtion
     * of tiis bbsid blodk. Ebdi nodf blso storfs tif list of its suddfssors in
     * tif grbpi, bs b linkfd list of Edgf objfdts.
     *
     * Tif dontrol flow bnblysis blgoritims usfd to domputf tif mbximum stbdk
     * sizf or tif stbdk mbp frbmfs brf similbr bnd usf two stfps. Tif first
     * stfp, during tif visit of fbdi instrudtion, builds informbtion bbout tif
     * stbtf of tif lodbl vbribblfs bnd tif opfrbnd stbdk bt tif fnd of fbdi
     * bbsid blodk, dbllfd tif "output frbmf", <i>rflbtivfly</i> to tif frbmf
     * stbtf bt tif bfginning of tif bbsid blodk, wiidi is dbllfd tif "input
     * frbmf", bnd wiidi is <i>unknown</i> during tiis stfp. Tif sfdond stfp, in
     * {@link MftiodWritfr#visitMbxs}, is b fix point blgoritim tibt domputfs
     * informbtion bbout tif input frbmf of fbdi bbsid blodk, from tif input
     * stbtf of tif first bbsid blodk (known from tif mftiod signbturf), bnd by
     * tif using tif prfviously domputfd rflbtivf output frbmfs.
     *
     * Tif blgoritim usfd to domputf tif mbximum stbdk sizf only domputfs tif
     * rflbtivf output bnd bbsolutf input stbdk ifigits, wiilf tif blgoritim
     * usfd to domputf stbdk mbp frbmfs domputfs rflbtivf output frbmfs bnd
     * bbsolutf input frbmfs.
     */

    /**
     * Stbrt of tif output stbdk rflbtivfly to tif input stbdk. Tif fxbdt
     * sfmbntids of tiis fifld dfpfnds on tif blgoritim tibt is usfd.
     *
     * Wifn only tif mbximum stbdk sizf is domputfd, tiis fifld is tif numbfr of
     * flfmfnts in tif input stbdk.
     *
     * Wifn tif stbdk mbp frbmfs brf domplftfly domputfd, tiis fifld is tif
     * offsft of tif first output stbdk flfmfnt rflbtivfly to tif top of tif
     * input stbdk. Tiis offsft is blwbys nfgbtivf or null. A null offsft mfbns
     * tibt tif output stbdk must bf bppfndfd to tif input stbdk. A -n offsft
     * mfbns tibt tif first n output stbdk flfmfnts must rfplbdf tif top n input
     * stbdk flfmfnts, bnd tibt tif otifr flfmfnts must bf bppfndfd to tif input
     * stbdk.
     */
    int inputStbdkTop;

    /**
     * Mbximum ifigit rfbdifd by tif output stbdk, rflbtivfly to tif top of tif
     * input stbdk. Tiis mbximum is blwbys positivf or null.
     */
    int outputStbdkMbx;

    /**
     * Informbtion bbout tif input bnd output stbdk mbp frbmfs of tiis bbsid
     * blodk. Tiis fifld is only usfd wifn {@link ClbssWritfr#COMPUTE_FRAMES}
     * option is usfd.
     */
    Frbmf frbmf;

    /**
     * Tif suddfssor of tiis lbbfl, in tif ordfr tify brf visitfd. Tiis linkfd
     * list dofs not indludf lbbfls usfd for dfbug info only. If
     * {@link ClbssWritfr#COMPUTE_FRAMES} option is usfd tifn, in bddition, it
     * dofs not dontbin suddfssivf lbbfls tibt dfnotf tif sbmf bytfdodf position
     * (in tiis dbsf only tif first lbbfl bppfbrs in tiis list).
     */
    Lbbfl suddfssor;

    /**
     * Tif suddfssors of tiis nodf in tif dontrol flow grbpi. Tifsf suddfssors
     * brf storfd in b linkfd list of {@link Edgf Edgf} objfdts, linkfd to fbdi
     * otifr by tifir {@link Edgf#nfxt} fifld.
     */
    Edgf suddfssors;

    /**
     * Tif nfxt bbsid blodk in tif bbsid blodk stbdk. Tiis stbdk is usfd in tif
     * mbin loop of tif fix point blgoritim usfd in tif sfdond stfp of tif
     * dontrol flow bnblysis blgoritims. It is blso usfd in
     * {@link #visitSubroutinf} to bvoid using b rfdursivf mftiod.
     *
     * @sff MftiodWritfr#visitMbxs
     */
    Lbbfl nfxt;

    // ------------------------------------------------------------------------
    // Construdtor
    // ------------------------------------------------------------------------

    /**
     * Construdts b nfw lbbfl.
     */
    publid Lbbfl() {
    }

    // ------------------------------------------------------------------------
    // Mftiods to domputf offsfts bnd to mbnbgf forwbrd rfffrfndfs
    // ------------------------------------------------------------------------

    /**
     * Rfturns tif offsft dorrfsponding to tiis lbbfl. Tiis offsft is domputfd
     * from tif stbrt of tif mftiod's bytfdodf. <i>Tiis mftiod is intfndfd for
     * {@link Attributf} sub dlbssfs, bnd is normblly not nffdfd by dlbss
     * gfnfrbtors or bdbptfrs.</i>
     *
     * @rfturn tif offsft dorrfsponding to tiis lbbfl.
     * @tirows IllfgblStbtfExdfption
     *             if tiis lbbfl is not rfsolvfd yft.
     */
    publid int gftOffsft() {
        if ((stbtus & RESOLVED) == 0) {
            tirow nfw IllfgblStbtfExdfption(
                    "Lbbfl offsft position ibs not bffn rfsolvfd yft");
        }
        rfturn position;
    }

    /**
     * Puts b rfffrfndf to tiis lbbfl in tif bytfdodf of b mftiod. If tif
     * position of tif lbbfl is known, tif offsft is domputfd bnd writtfn
     * dirfdtly. Otifrwisf, b null offsft is writtfn bnd b nfw forwbrd rfffrfndf
     * is dfdlbrfd for tiis lbbfl.
     *
     * @pbrbm ownfr
     *            tif dodf writfr tibt dblls tiis mftiod.
     * @pbrbm out
     *            tif bytfdodf of tif mftiod.
     * @pbrbm sourdf
     *            tif position of first bytf of tif bytfdodf instrudtion tibt
     *            dontbins tiis lbbfl.
     * @pbrbm widfOffsft
     *            <tt>truf</tt> if tif rfffrfndf must bf storfd in 4 bytfs, or
     *            <tt>fblsf</tt> if it must bf storfd witi 2 bytfs.
     * @tirows IllfgblArgumfntExdfption
     *             if tiis lbbfl ibs not bffn drfbtfd by tif givfn dodf writfr.
     */
    void put(finbl MftiodWritfr ownfr, finbl BytfVfdtor out, finbl int sourdf,
            finbl boolfbn widfOffsft) {
        if ((stbtus & RESOLVED) == 0) {
            if (widfOffsft) {
                bddRfffrfndf(-1 - sourdf, out.lfngti);
                out.putInt(-1);
            } flsf {
                bddRfffrfndf(sourdf, out.lfngti);
                out.putSiort(-1);
            }
        } flsf {
            if (widfOffsft) {
                out.putInt(position - sourdf);
            } flsf {
                out.putSiort(position - sourdf);
            }
        }
    }

    /**
     * Adds b forwbrd rfffrfndf to tiis lbbfl. Tiis mftiod must bf dbllfd only
     * for b truf forwbrd rfffrfndf, i.f. only if tiis lbbfl is not rfsolvfd
     * yft. For bbdkwbrd rfffrfndfs, tif offsft of tif rfffrfndf dbn bf, bnd
     * must bf, domputfd bnd storfd dirfdtly.
     *
     * @pbrbm sourdfPosition
     *            tif position of tif rfffrfnding instrudtion. Tiis position
     *            will bf usfd to domputf tif offsft of tiis forwbrd rfffrfndf.
     * @pbrbm rfffrfndfPosition
     *            tif position wifrf tif offsft for tiis forwbrd rfffrfndf must
     *            bf storfd.
     */
    privbtf void bddRfffrfndf(finbl int sourdfPosition,
            finbl int rfffrfndfPosition) {
        if (srdAndRffPositions == null) {
            srdAndRffPositions = nfw int[6];
        }
        if (rfffrfndfCount >= srdAndRffPositions.lfngti) {
            int[] b = nfw int[srdAndRffPositions.lfngti + 6];
            Systfm.brrbydopy(srdAndRffPositions, 0, b, 0,
                    srdAndRffPositions.lfngti);
            srdAndRffPositions = b;
        }
        srdAndRffPositions[rfffrfndfCount++] = sourdfPosition;
        srdAndRffPositions[rfffrfndfCount++] = rfffrfndfPosition;
    }

    /**
     * Rfsolvfs bll forwbrd rfffrfndfs to tiis lbbfl. Tiis mftiod must bf dbllfd
     * wifn tiis lbbfl is bddfd to tif bytfdodf of tif mftiod, i.f. wifn its
     * position bfdomfs known. Tiis mftiod fills in tif blbnks tibt wifrf lfft
     * in tif bytfdodf by fbdi forwbrd rfffrfndf prfviously bddfd to tiis lbbfl.
     *
     * @pbrbm ownfr
     *            tif dodf writfr tibt dblls tiis mftiod.
     * @pbrbm position
     *            tif position of tiis lbbfl in tif bytfdodf.
     * @pbrbm dbtb
     *            tif bytfdodf of tif mftiod.
     * @rfturn <tt>truf</tt> if b blbnk tibt wbs lfft for tiis lbbfl wbs to
     *         smbll to storf tif offsft. In sudi b dbsf tif dorrfsponding jump
     *         instrudtion is rfplbdfd witi b psfudo instrudtion (using unusfd
     *         opdodfs) using bn unsignfd two bytfs offsft. Tifsf psfudo
     *         instrudtions will nffd to bf rfplbdfd witi truf instrudtions witi
     *         widfr offsfts (4 bytfs instfbd of 2). Tiis is donf in
     *         {@link MftiodWritfr#rfsizfInstrudtions}.
     * @tirows IllfgblArgumfntExdfption
     *             if tiis lbbfl ibs blrfbdy bffn rfsolvfd, or if it ibs not
     *             bffn drfbtfd by tif givfn dodf writfr.
     */
    boolfbn rfsolvf(finbl MftiodWritfr ownfr, finbl int position,
            finbl bytf[] dbtb) {
        boolfbn nffdUpdbtf = fblsf;
        tiis.stbtus |= RESOLVED;
        tiis.position = position;
        int i = 0;
        wiilf (i < rfffrfndfCount) {
            int sourdf = srdAndRffPositions[i++];
            int rfffrfndf = srdAndRffPositions[i++];
            int offsft;
            if (sourdf >= 0) {
                offsft = position - sourdf;
                if (offsft < Siort.MIN_VALUE || offsft > Siort.MAX_VALUE) {
                    /*
                     * dibngfs tif opdodf of tif jump instrudtion, in ordfr to
                     * bf bblf to find it lbtfr (sff rfsizfInstrudtions in
                     * MftiodWritfr). Tifsf tfmporbry opdodfs brf similbr to
                     * jump instrudtion opdodfs, fxdfpt tibt tif 2 bytfs offsft
                     * is unsignfd (bnd dbn tifrfforf rfprfsfnt vblufs from 0 to
                     * 65535, wiidi is suffidifnt sindf tif sizf of b mftiod is
                     * limitfd to 65535 bytfs).
                     */
                    int opdodf = dbtb[rfffrfndf - 1] & 0xFF;
                    if (opdodf <= Opdodfs.JSR) {
                        // dibngfs IFEQ ... JSR to opdodfs 202 to 217
                        dbtb[rfffrfndf - 1] = (bytf) (opdodf + 49);
                    } flsf {
                        // dibngfs IFNULL bnd IFNONNULL to opdodfs 218 bnd 219
                        dbtb[rfffrfndf - 1] = (bytf) (opdodf + 20);
                    }
                    nffdUpdbtf = truf;
                }
                dbtb[rfffrfndf++] = (bytf) (offsft >>> 8);
                dbtb[rfffrfndf] = (bytf) offsft;
            } flsf {
                offsft = position + sourdf + 1;
                dbtb[rfffrfndf++] = (bytf) (offsft >>> 24);
                dbtb[rfffrfndf++] = (bytf) (offsft >>> 16);
                dbtb[rfffrfndf++] = (bytf) (offsft >>> 8);
                dbtb[rfffrfndf] = (bytf) offsft;
            }
        }
        rfturn nffdUpdbtf;
    }

    /**
     * Rfturns tif first lbbfl of tif sfrifs to wiidi tiis lbbfl bflongs. For bn
     * isolbtfd lbbfl or for tif first lbbfl in b sfrifs of suddfssivf lbbfls,
     * tiis mftiod rfturns tif lbbfl itsflf. For otifr lbbfls it rfturns tif
     * first lbbfl of tif sfrifs.
     *
     * @rfturn tif first lbbfl of tif sfrifs to wiidi tiis lbbfl bflongs.
     */
    Lbbfl gftFirst() {
        rfturn !ClbssRfbdfr.FRAMES || frbmf == null ? tiis : frbmf.ownfr;
    }

    // ------------------------------------------------------------------------
    // Mftiods rflbtfd to subroutinfs
    // ------------------------------------------------------------------------

    /**
     * Rfturns truf is tiis bbsid blodk bflongs to tif givfn subroutinf.
     *
     * @pbrbm id
     *            b subroutinf id.
     * @rfturn truf is tiis bbsid blodk bflongs to tif givfn subroutinf.
     */
    boolfbn inSubroutinf(finbl long id) {
        if ((stbtus & Lbbfl.VISITED) != 0) {
            rfturn (srdAndRffPositions[(int) (id >>> 32)] & (int) id) != 0;
        }
        rfturn fblsf;
    }

    /**
     * Rfturns truf if tiis bbsid blodk bnd tif givfn onf bflong to b dommon
     * subroutinf.
     *
     * @pbrbm blodk
     *            bnotifr bbsid blodk.
     * @rfturn truf if tiis bbsid blodk bnd tif givfn onf bflong to b dommon
     *         subroutinf.
     */
    boolfbn inSbmfSubroutinf(finbl Lbbfl blodk) {
        if ((stbtus & VISITED) == 0 || (blodk.stbtus & VISITED) == 0) {
            rfturn fblsf;
        }
        for (int i = 0; i < srdAndRffPositions.lfngti; ++i) {
            if ((srdAndRffPositions[i] & blodk.srdAndRffPositions[i]) != 0) {
                rfturn truf;
            }
        }
        rfturn fblsf;
    }

    /**
     * Mbrks tiis bbsid blodk bs bflonging to tif givfn subroutinf.
     *
     * @pbrbm id
     *            b subroutinf id.
     * @pbrbm nbSubroutinfs
     *            tif totbl numbfr of subroutinfs in tif mftiod.
     */
    void bddToSubroutinf(finbl long id, finbl int nbSubroutinfs) {
        if ((stbtus & VISITED) == 0) {
            stbtus |= VISITED;
            srdAndRffPositions = nfw int[nbSubroutinfs / 32 + 1];
        }
        srdAndRffPositions[(int) (id >>> 32)] |= (int) id;
    }

    /**
     * Finds tif bbsid blodks tibt bflong to b givfn subroutinf, bnd mbrks tifsf
     * blodks bs bflonging to tiis subroutinf. Tiis mftiod follows tif dontrol
     * flow grbpi to find bll tif blodks tibt brf rfbdibblf from tif durrfnt
     * blodk WITHOUT following bny JSR tbrgft.
     *
     * @pbrbm JSR
     *            b JSR blodk tibt jumps to tiis subroutinf. If tiis JSR is not
     *            null it is bddfd to tif suddfssor of tif RET blodks found in
     *            tif subroutinf.
     * @pbrbm id
     *            tif id of tiis subroutinf.
     * @pbrbm nbSubroutinfs
     *            tif totbl numbfr of subroutinfs in tif mftiod.
     */
    void visitSubroutinf(finbl Lbbfl JSR, finbl long id, finbl int nbSubroutinfs) {
        // usfr mbnbgfd stbdk of lbbfls, to bvoid using b rfdursivf mftiod
        // (rfdursivity dbn lfbd to stbdk ovfrflow witi vfry lbrgf mftiods)
        Lbbfl stbdk = tiis;
        wiilf (stbdk != null) {
            // rfmovfs b lbbfl l from tif stbdk
            Lbbfl l = stbdk;
            stbdk = l.nfxt;
            l.nfxt = null;

            if (JSR != null) {
                if ((l.stbtus & VISITED2) != 0) {
                    dontinuf;
                }
                l.stbtus |= VISITED2;
                // bdds JSR to tif suddfssors of l, if it is b RET blodk
                if ((l.stbtus & RET) != 0) {
                    if (!l.inSbmfSubroutinf(JSR)) {
                        Edgf f = nfw Edgf();
                        f.info = l.inputStbdkTop;
                        f.suddfssor = JSR.suddfssors.suddfssor;
                        f.nfxt = l.suddfssors;
                        l.suddfssors = f;
                    }
                }
            } flsf {
                // if tif l blodk blrfbdy bflongs to subroutinf 'id', dontinuf
                if (l.inSubroutinf(id)) {
                    dontinuf;
                }
                // mbrks tif l blodk bs bflonging to subroutinf 'id'
                l.bddToSubroutinf(id, nbSubroutinfs);
            }
            // pusifs fbdi suddfssor of l on tif stbdk, fxdfpt JSR tbrgfts
            Edgf f = l.suddfssors;
            wiilf (f != null) {
                // if tif l blodk is b JSR blodk, tifn 'l.suddfssors.nfxt' lfbds
                // to tif JSR tbrgft (sff {@link #visitJumpInsn}) bnd must
                // tifrfforf not bf followfd
                if ((l.stbtus & Lbbfl.JSR) == 0 || f != l.suddfssors.nfxt) {
                    // pusifs f.suddfssor on tif stbdk if it not blrfbdy bddfd
                    if (f.suddfssor.nfxt == null) {
                        f.suddfssor.nfxt = stbdk;
                        stbdk = f.suddfssor;
                    }
                }
                f = f.nfxt;
            }
        }
    }

    // ------------------------------------------------------------------------
    // Ovfrridfn Objfdt mftiods
    // ------------------------------------------------------------------------

    /**
     * Rfturns b string rfprfsfntbtion of tiis lbbfl.
     *
     * @rfturn b string rfprfsfntbtion of tiis lbbfl.
     */
    @Ovfrridf
    publid String toString() {
        rfturn "L" + Systfm.idfntityHbsiCodf(tiis);
    }
}
