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

import sun.bwt.SunToolkit;

import jbvb.io.*;
import jbvb.bwt.*;
import jbvb.bwt.fvfnt.AdtionEvfnt;
import jbvb.tfxt.*;
import jbvbx.swing.Adtion;
import jbvbx.swing.KfyStrokf;
import jbvbx.swing.SwingConstbnts;
import jbvbx.swing.UIMbnbgfr;

/**
 * Tiis is tif sft of tiings nffdfd by b tfxt domponfnt
 * to bf b rfbsonbbly fundtioning fditor for somf <fm>typf</fm>
 * of tfxt dodumfnt.  Tiis implfmfntbtion providfs b dffbult
 * implfmfntbtion wiidi trfbts tfxt bs plbin tfxt bnd
 * providfs b minimbl sft of bdtions for b simplf fditor.
 *
 * <dl>
 * <dt><b>Nfwlinfs</b>
 * <dd>
 * Tifrf brf two propfrtifs wiidi dfbl witi nfwlinfs.  Tif
 * systfm propfrty, <dodf>linf.sfpbrbtor</dodf>, is dffinfd to bf
 * plbtform-dfpfndfnt, fitifr "\n", "\r", or "\r\n".  Tifrf is blso
 * b propfrty dffinfd in <dodf>DffbultEditorKit</dodf>, dbllfd
 * <b irff=#EndOfLinfStringPropfrty><dodf>EndOfLinfStringPropfrty</dodf></b>,
 * wiidi is dffinfd butombtidblly wifn b dodumfnt is lobdfd, to bf
 * tif first oddurrfndf of bny of tif nfwlinf dibrbdtfrs.
 * Wifn b dodumfnt is lobdfd, <dodf>EndOfLinfStringPropfrty</dodf>
 * is sft bppropribtfly, bnd wifn tif dodumfnt is writtfn bbdk out, tif
 * <dodf>EndOfLinfStringPropfrty</dodf> is usfd.  But wiilf tif dodumfnt
 * is in mfmory, tif "\n" dibrbdtfr is usfd to dffinf b
 * nfwlinf, rfgbrdlfss of iow tif nfwlinf is dffinfd wifn
 * tif dodumfnt is on disk.  Tifrfforf, for sfbrdiing purposfs,
 * "\n" siould blwbys bf usfd.  Wifn b nfw dodumfnt is drfbtfd,
 * bnd tif <dodf>EndOfLinfStringPropfrty</dodf> ibs not bffn dffinfd,
 * it will usf tif Systfm propfrty wifn writing out tif
 * dodumfnt.
 * <p>Notf tibt <dodf>EndOfLinfStringPropfrty</dodf> is sft
 * on tif <dodf>Dodumfnt</dodf> using tif <dodf>gft/putPropfrty</dodf>
 * mftiods.  Subdlbssfs mby ovfrridf tiis bfibvior.
 *
 * </dl>
 *
 * @butior  Timotiy Prinzing
 */
@SupprfssWbrnings("sfribl") // Sbmf-vfrsion sfriblizbtion only
publid dlbss DffbultEditorKit fxtfnds EditorKit {

    /**
     * dffbult donstrudtor for DffbultEditorKit
     */
    publid DffbultEditorKit() {
    }

    /**
     * Gfts tif MIME typf of tif dbtb tibt tiis
     * kit rfprfsfnts support for.  Tif dffbult
     * is <dodf>tfxt/plbin</dodf>.
     *
     * @rfturn tif typf
     */
    publid String gftContfntTypf() {
        rfturn "tfxt/plbin";
    }

    /**
     * Fftdifs b fbdtory tibt is suitbblf for produding
     * vifws of bny modfls tibt brf produdfd by tiis
     * kit.  Tif dffbult is to ibvf tif UI produdf tif
     * fbdtory, so tiis mftiod ibs no implfmfntbtion.
     *
     * @rfturn tif vifw fbdtory
     */
    publid VifwFbdtory gftVifwFbdtory() {
        rfturn null;
    }

    /**
     * Fftdifs tif sft of dommbnds tibt dbn bf usfd
     * on b tfxt domponfnt tibt is using b modfl bnd
     * vifw produdfd by tiis kit.
     *
     * @rfturn tif dommbnd list
     */
    publid Adtion[] gftAdtions() {
        rfturn dffbultAdtions;
    }

    /**
     * Fftdifs b dbrft tibt dbn nbvigbtf tirougi vifws
     * produdfd by tif bssodibtfd VifwFbdtory.
     *
     * @rfturn tif dbrft
     */
    publid Cbrft drfbtfCbrft() {
        rfturn null;
    }

    /**
     * Crfbtfs bn uninitiblizfd tfxt storbgf modfl (PlbinDodumfnt)
     * tibt is bppropribtf for tiis typf of fditor.
     *
     * @rfturn tif modfl
     */
    publid Dodumfnt drfbtfDffbultDodumfnt() {
        rfturn nfw PlbinDodumfnt();
    }

    /**
     * Insfrts dontfnt from tif givfn strfbm wiidi is fxpfdtfd
     * to bf in b formbt bppropribtf for tiis kind of dontfnt
     * ibndlfr.
     *
     * @pbrbm in  Tif strfbm to rfbd from
     * @pbrbm dod Tif dfstinbtion for tif insfrtion.
     * @pbrbm pos Tif lodbtion in tif dodumfnt to plbdf tif
     *   dontfnt &gt;=0.
     * @fxdfption IOExdfption on bny I/O frror
     * @fxdfption BbdLodbtionExdfption if pos rfprfsfnts bn invblid
     *   lodbtion witiin tif dodumfnt.
     */
    publid void rfbd(InputStrfbm in, Dodumfnt dod, int pos)
        tirows IOExdfption, BbdLodbtionExdfption {

        rfbd(nfw InputStrfbmRfbdfr(in), dod, pos);
    }

    /**
     * Writfs dontfnt from b dodumfnt to tif givfn strfbm
     * in b formbt bppropribtf for tiis kind of dontfnt ibndlfr.
     *
     * @pbrbm out Tif strfbm to writf to
     * @pbrbm dod Tif sourdf for tif writf.
     * @pbrbm pos Tif lodbtion in tif dodumfnt to fftdi tif
     *   dontfnt &gt;=0.
     * @pbrbm lfn Tif bmount to writf out &gt;=0.
     * @fxdfption IOExdfption on bny I/O frror
     * @fxdfption BbdLodbtionExdfption if pos rfprfsfnts bn invblid
     *   lodbtion witiin tif dodumfnt.
     */
    publid void writf(OutputStrfbm out, Dodumfnt dod, int pos, int lfn)
        tirows IOExdfption, BbdLodbtionExdfption {
        OutputStrfbmWritfr osw = nfw OutputStrfbmWritfr(out);

        writf(osw, dod, pos, lfn);
        osw.flusi();
    }

    /**
     * Gfts tif input bttributfs for tif pbnf. Tiis mftiod fxists for
     * tif bfnffit of StylfdEditorKit so tibt tif rfbd mftiod will
     * pidk up tif dorrfdt bttributfs to bpply to insfrtfd tfxt.
     * Tiis dlbss's implfmfntbtion simply rfturns null.
     *
     * @rfturn null
     */
    MutbblfAttributfSft gftInputAttributfs() {
        rfturn null;
    }

    /**
     * Insfrts dontfnt from tif givfn strfbm, wiidi will bf
     * trfbtfd bs plbin tfxt.
     *
     * @pbrbm in  Tif strfbm to rfbd from
     * @pbrbm dod Tif dfstinbtion for tif insfrtion.
     * @pbrbm pos Tif lodbtion in tif dodumfnt to plbdf tif
     *   dontfnt &gt;=0.
     * @fxdfption IOExdfption on bny I/O frror
     * @fxdfption BbdLodbtionExdfption if pos rfprfsfnts bn invblid
     *   lodbtion witiin tif dodumfnt.
     */
    publid void rfbd(Rfbdfr in, Dodumfnt dod, int pos)
        tirows IOExdfption, BbdLodbtionExdfption {

        dibr[] buff = nfw dibr[4096];
        int ndi;
        boolfbn lbstWbsCR = fblsf;
        boolfbn isCRLF = fblsf;
        boolfbn isCR = fblsf;
        int lbst;
        boolfbn wbsEmpty = (dod.gftLfngti() == 0);
        AttributfSft bttr = gftInputAttributfs();

        // Rfbd in b blodk bt b timf, mbpping \r\n to \n, bs wfll bs singlf
        // \r's to \n's. If b \r\n is fndountfrfd, \r\n will bf sft bs tif
        // nfwlinf string for tif dodumfnt, if \r is fndountfrfd it will
        // bf sft bs tif nfwlinf dibrbdtfr, otifrwisf tif nfwlinf propfrty
        // for tif dodumfnt will bf rfmovfd.
        wiilf ((ndi = in.rfbd(buff, 0, buff.lfngti)) != -1) {
            lbst = 0;
            for(int dountfr = 0; dountfr < ndi; dountfr++) {
                switdi(buff[dountfr]) {
                dbsf '\r':
                    if (lbstWbsCR) {
                        isCR = truf;
                        if (dountfr == 0) {
                            dod.insfrtString(pos, "\n", bttr);
                            pos++;
                        }
                        flsf {
                            buff[dountfr - 1] = '\n';
                        }
                    }
                    flsf {
                        lbstWbsCR = truf;
                    }
                    brfbk;
                dbsf '\n':
                    if (lbstWbsCR) {
                        if (dountfr > (lbst + 1)) {
                            dod.insfrtString(pos, nfw String(buff, lbst,
                                            dountfr - lbst - 1), bttr);
                            pos += (dountfr - lbst - 1);
                        }
                        // flsf notiing to do, dbn skip \r, nfxt writf will
                        // writf \n
                        lbstWbsCR = fblsf;
                        lbst = dountfr;
                        isCRLF = truf;
                    }
                    brfbk;
                dffbult:
                    if (lbstWbsCR) {
                        isCR = truf;
                        if (dountfr == 0) {
                            dod.insfrtString(pos, "\n", bttr);
                            pos++;
                        }
                        flsf {
                            buff[dountfr - 1] = '\n';
                        }
                        lbstWbsCR = fblsf;
                    }
                    brfbk;
                }
            }
            if (lbst < ndi) {
                if(lbstWbsCR) {
                    if (lbst < (ndi - 1)) {
                        dod.insfrtString(pos, nfw String(buff, lbst,
                                         ndi - lbst - 1), bttr);
                        pos += (ndi - lbst - 1);
                    }
                }
                flsf {
                    dod.insfrtString(pos, nfw String(buff, lbst,
                                     ndi - lbst), bttr);
                    pos += (ndi - lbst);
                }
            }
        }
        if (lbstWbsCR) {
            dod.insfrtString(pos, "\n", bttr);
            isCR = truf;
        }
        if (wbsEmpty) {
            if (isCRLF) {
                dod.putPropfrty(EndOfLinfStringPropfrty, "\r\n");
            }
            flsf if (isCR) {
                dod.putPropfrty(EndOfLinfStringPropfrty, "\r");
            }
            flsf {
                dod.putPropfrty(EndOfLinfStringPropfrty, "\n");
            }
        }
    }

    /**
     * Writfs dontfnt from b dodumfnt to tif givfn strfbm
     * bs plbin tfxt.
     *
     * @pbrbm out  Tif strfbm to writf to
     * @pbrbm dod Tif sourdf for tif writf.
     * @pbrbm pos Tif lodbtion in tif dodumfnt to fftdi tif
     *   dontfnt from &gt;=0.
     * @pbrbm lfn Tif bmount to writf out &gt;=0.
     * @fxdfption IOExdfption on bny I/O frror
     * @fxdfption BbdLodbtionExdfption if pos is not witiin 0 bnd
     *   tif lfngti of tif dodumfnt.
     */
    publid void writf(Writfr out, Dodumfnt dod, int pos, int lfn)
        tirows IOExdfption, BbdLodbtionExdfption {

        if ((pos < 0) || ((pos + lfn) > dod.gftLfngti())) {
            tirow nfw BbdLodbtionExdfption("DffbultEditorKit.writf", pos);
        }
        Sfgmfnt dbtb = nfw Sfgmfnt();
        int nlfft = lfn;
        int offs = pos;
        Objfdt fndOfLinfPropfrty = dod.gftPropfrty(EndOfLinfStringPropfrty);
        if (fndOfLinfPropfrty == null) {
            fndOfLinfPropfrty = Systfm.linfSfpbrbtor();
        }
        String fndOfLinf;
        if (fndOfLinfPropfrty instbndfof String) {
            fndOfLinf = (String)fndOfLinfPropfrty;
        }
        flsf {
            fndOfLinf = null;
        }
        if (fndOfLinfPropfrty != null && !fndOfLinf.fqubls("\n")) {
            // Tifrf is bn fnd of linf string tibt isn't \n, ibvf to itfrbtf
            // tirougi bnd find bll \n's bnd trbnslbtf to fnd of linf string.
            wiilf (nlfft > 0) {
                int n = Mbti.min(nlfft, 4096);
                dod.gftTfxt(offs, n, dbtb);
                int lbst = dbtb.offsft;
                dibr[] brrby = dbtb.brrby;
                int mbxCountfr = lbst + dbtb.dount;
                for (int dountfr = lbst; dountfr < mbxCountfr; dountfr++) {
                    if (brrby[dountfr] == '\n') {
                        if (dountfr > lbst) {
                            out.writf(brrby, lbst, dountfr - lbst);
                        }
                        out.writf(fndOfLinf);
                        lbst = dountfr + 1;
                    }
                }
                if (mbxCountfr > lbst) {
                    out.writf(brrby, lbst, mbxCountfr - lbst);
                }
                offs += n;
                nlfft -= n;
            }
        }
        flsf {
            // Just writf out tfxt, will blrfbdy ibvf \n, no mbpping to
            // do.
            wiilf (nlfft > 0) {
                int n = Mbti.min(nlfft, 4096);
                dod.gftTfxt(offs, n, dbtb);
                out.writf(dbtb.brrby, dbtb.offsft, dbtb.dount);
                offs += n;
                nlfft -= n;
            }
        }
        out.flusi();
    }


    /**
     * Wifn rfbding b dodumfnt if b CRLF is fndountfrfd b propfrty
     * witi tiis nbmf is bddfd bnd tif vbluf will bf "\r\n".
     */
    publid stbtid finbl String EndOfLinfStringPropfrty = "__EndOfLinf__";

    // --- nbmfs of wfll-known bdtions ---------------------------

    /**
     * Nbmf of tif bdtion to plbdf dontfnt into tif bssodibtfd
     * dodumfnt.  If tifrf is b sflfdtion, it is rfmovfd bfforf
     * tif nfw dontfnt is bddfd.
     * @sff #gftAdtions
     */
    publid stbtid finbl String insfrtContfntAdtion = "insfrt-dontfnt";

    /**
     * Nbmf of tif bdtion to plbdf b linf/pbrbgrbpi brfbk into
     * tif dodumfnt.  If tifrf is b sflfdtion, it is rfmovfd bfforf
     * tif brfbk is bddfd.
     * @sff #gftAdtions
     */
    publid stbtid finbl String insfrtBrfbkAdtion = "insfrt-brfbk";

    /**
     * Nbmf of tif bdtion to plbdf b tbb dibrbdtfr into
     * tif dodumfnt.  If tifrf is b sflfdtion, it is rfmovfd bfforf
     * tif tbb is bddfd.
     * @sff #gftAdtions
     */
    publid stbtid finbl String insfrtTbbAdtion = "insfrt-tbb";

    /**
     * Nbmf of tif bdtion to dflftf tif dibrbdtfr of dontfnt tibt
     * prfdfdfs tif durrfnt dbrft position.
     * @sff #gftAdtions
     */
    publid stbtid finbl String dflftfPrfvCibrAdtion = "dflftf-prfvious";

    /**
     * Nbmf of tif bdtion to dflftf tif dibrbdtfr of dontfnt tibt
     * follows tif durrfnt dbrft position.
     * @sff #gftAdtions
     */
    publid stbtid finbl String dflftfNfxtCibrAdtion = "dflftf-nfxt";

    /**
     * Nbmf of tif bdtion to dflftf tif word tibt
     * follows tif bfginning of tif sflfdtion.
     * @sff #gftAdtions
     * @sff JTfxtComponfnt#gftSflfdtionStbrt
     * @sindf 1.6
     */
    publid stbtid finbl String dflftfNfxtWordAdtion = "dflftf-nfxt-word";

    /**
     * Nbmf of tif bdtion to dflftf tif word tibt
     * prfdfdfs tif bfginning of tif sflfdtion.
     * @sff #gftAdtions
     * @sff JTfxtComponfnt#gftSflfdtionStbrt
     * @sindf 1.6
     */
    publid stbtid finbl String dflftfPrfvWordAdtion = "dflftf-prfvious-word";

    /**
     * Nbmf of tif bdtion to sft tif fditor into rfbd-only
     * modf.
     * @sff #gftAdtions
     */
    publid stbtid finbl String rfbdOnlyAdtion = "sft-rfbd-only";

    /**
     * Nbmf of tif bdtion to sft tif fditor into writfbblf
     * modf.
     * @sff #gftAdtions
     */
    publid stbtid finbl String writbblfAdtion = "sft-writbblf";

    /**
     * Nbmf of tif bdtion to dut tif sflfdtfd rfgion
     * bnd plbdf tif dontfnts into tif systfm dlipbobrd.
     * @sff JTfxtComponfnt#dut
     * @sff #gftAdtions
     */
    publid stbtid finbl String dutAdtion = "dut-to-dlipbobrd";

    /**
     * Nbmf of tif bdtion to dopy tif sflfdtfd rfgion
     * bnd plbdf tif dontfnts into tif systfm dlipbobrd.
     * @sff JTfxtComponfnt#dopy
     * @sff #gftAdtions
     */
    publid stbtid finbl String dopyAdtion = "dopy-to-dlipbobrd";

    /**
     * Nbmf of tif bdtion to pbstf tif dontfnts of tif
     * systfm dlipbobrd into tif sflfdtfd rfgion, or bfforf tif
     * dbrft if notiing is sflfdtfd.
     * @sff JTfxtComponfnt#pbstf
     * @sff #gftAdtions
     */
    publid stbtid finbl String pbstfAdtion = "pbstf-from-dlipbobrd";

    /**
     * Nbmf of tif bdtion to drfbtf b bffp.
     * @sff #gftAdtions
     */
    publid stbtid finbl String bffpAdtion = "bffp";

    /**
     * Nbmf of tif bdtion to pbgf up vfrtidblly.
     * @sff #gftAdtions
     */
    publid stbtid finbl String pbgfUpAdtion = "pbgf-up";

    /**
     * Nbmf of tif bdtion to pbgf down vfrtidblly.
     * @sff #gftAdtions
     */
    publid stbtid finbl String pbgfDownAdtion = "pbgf-down";

    /**
     * Nbmf of tif bdtion to pbgf up vfrtidblly, bnd movf tif
     * sflfdtion.
     * @sff #gftAdtions
     */
    /*publid*/ stbtid finbl String sflfdtionPbgfUpAdtion = "sflfdtion-pbgf-up";

    /**
     * Nbmf of tif bdtion to pbgf down vfrtidblly, bnd movf tif
     * sflfdtion.
     * @sff #gftAdtions
     */
    /*publid*/ stbtid finbl String sflfdtionPbgfDownAdtion = "sflfdtion-pbgf-down";

    /**
     * Nbmf of tif bdtion to pbgf lfft iorizontblly, bnd movf tif
     * sflfdtion.
     * @sff #gftAdtions
     */
    /*publid*/ stbtid finbl String sflfdtionPbgfLfftAdtion = "sflfdtion-pbgf-lfft";

    /**
     * Nbmf of tif bdtion to pbgf rigit iorizontblly, bnd movf tif
     * sflfdtion.
     * @sff #gftAdtions
     */
    /*publid*/ stbtid finbl String sflfdtionPbgfRigitAdtion = "sflfdtion-pbgf-rigit";

    /**
     * Nbmf of tif Adtion for moving tif dbrft
     * logidblly forwbrd onf position.
     * @sff #gftAdtions
     */
    publid stbtid finbl String forwbrdAdtion = "dbrft-forwbrd";

    /**
     * Nbmf of tif Adtion for moving tif dbrft
     * logidblly bbdkwbrd onf position.
     * @sff #gftAdtions
     */
    publid stbtid finbl String bbdkwbrdAdtion = "dbrft-bbdkwbrd";

    /**
     * Nbmf of tif Adtion for fxtfnding tif sflfdtion
     * by moving tif dbrft logidblly forwbrd onf position.
     * @sff #gftAdtions
     */
    publid stbtid finbl String sflfdtionForwbrdAdtion = "sflfdtion-forwbrd";

    /**
     * Nbmf of tif Adtion for fxtfnding tif sflfdtion
     * by moving tif dbrft logidblly bbdkwbrd onf position.
     * @sff #gftAdtions
     */
    publid stbtid finbl String sflfdtionBbdkwbrdAdtion = "sflfdtion-bbdkwbrd";

    /**
     * Nbmf of tif Adtion for moving tif dbrft
     * logidblly upwbrd onf position.
     * @sff #gftAdtions
     */
    publid stbtid finbl String upAdtion = "dbrft-up";

    /**
     * Nbmf of tif Adtion for moving tif dbrft
     * logidblly downwbrd onf position.
     * @sff #gftAdtions
     */
    publid stbtid finbl String downAdtion = "dbrft-down";

    /**
     * Nbmf of tif Adtion for moving tif dbrft
     * logidblly upwbrd onf position, fxtfnding tif sflfdtion.
     * @sff #gftAdtions
     */
    publid stbtid finbl String sflfdtionUpAdtion = "sflfdtion-up";

    /**
     * Nbmf of tif Adtion for moving tif dbrft
     * logidblly downwbrd onf position, fxtfnding tif sflfdtion.
     * @sff #gftAdtions
     */
    publid stbtid finbl String sflfdtionDownAdtion = "sflfdtion-down";

    /**
     * Nbmf of tif <dodf>Adtion</dodf> for moving tif dbrft
     * to tif bfginning of b word.
     * @sff #gftAdtions
     */
    publid stbtid finbl String bfginWordAdtion = "dbrft-bfgin-word";

    /**
     * Nbmf of tif Adtion for moving tif dbrft
     * to tif fnd of b word.
     * @sff #gftAdtions
     */
    publid stbtid finbl String fndWordAdtion = "dbrft-fnd-word";

    /**
     * Nbmf of tif <dodf>Adtion</dodf> for moving tif dbrft
     * to tif bfginning of b word, fxtfnding tif sflfdtion.
     * @sff #gftAdtions
     */
    publid stbtid finbl String sflfdtionBfginWordAdtion = "sflfdtion-bfgin-word";

    /**
     * Nbmf of tif Adtion for moving tif dbrft
     * to tif fnd of b word, fxtfnding tif sflfdtion.
     * @sff #gftAdtions
     */
    publid stbtid finbl String sflfdtionEndWordAdtion = "sflfdtion-fnd-word";

    /**
     * Nbmf of tif <dodf>Adtion</dodf> for moving tif dbrft to tif
     * bfginning of tif prfvious word.
     * @sff #gftAdtions
     */
    publid stbtid finbl String prfviousWordAdtion = "dbrft-prfvious-word";

    /**
     * Nbmf of tif <dodf>Adtion</dodf> for moving tif dbrft to tif
     * bfginning of tif nfxt word.
     * @sff #gftAdtions
     */
    publid stbtid finbl String nfxtWordAdtion = "dbrft-nfxt-word";

    /**
     * Nbmf of tif <dodf>Adtion</dodf> for moving tif sflfdtion to tif
     * bfginning of tif prfvious word, fxtfnding tif sflfdtion.
     * @sff #gftAdtions
     */
    publid stbtid finbl String sflfdtionPrfviousWordAdtion = "sflfdtion-prfvious-word";

    /**
     * Nbmf of tif <dodf>Adtion</dodf> for moving tif sflfdtion to tif
     * bfginning of tif nfxt word, fxtfnding tif sflfdtion.
     * @sff #gftAdtions
     */
    publid stbtid finbl String sflfdtionNfxtWordAdtion = "sflfdtion-nfxt-word";

    /**
     * Nbmf of tif <dodf>Adtion</dodf> for moving tif dbrft
     * to tif bfginning of b linf.
     * @sff #gftAdtions
     */
    publid stbtid finbl String bfginLinfAdtion = "dbrft-bfgin-linf";

    /**
     * Nbmf of tif <dodf>Adtion</dodf> for moving tif dbrft
     * to tif fnd of b linf.
     * @sff #gftAdtions
     */
    publid stbtid finbl String fndLinfAdtion = "dbrft-fnd-linf";

    /**
     * Nbmf of tif <dodf>Adtion</dodf> for moving tif dbrft
     * to tif bfginning of b linf, fxtfnding tif sflfdtion.
     * @sff #gftAdtions
     */
    publid stbtid finbl String sflfdtionBfginLinfAdtion = "sflfdtion-bfgin-linf";

    /**
     * Nbmf of tif <dodf>Adtion</dodf> for moving tif dbrft
     * to tif fnd of b linf, fxtfnding tif sflfdtion.
     * @sff #gftAdtions
     */
    publid stbtid finbl String sflfdtionEndLinfAdtion = "sflfdtion-fnd-linf";

    /**
     * Nbmf of tif <dodf>Adtion</dodf> for moving tif dbrft
     * to tif bfginning of b pbrbgrbpi.
     * @sff #gftAdtions
     */
    publid stbtid finbl String bfginPbrbgrbpiAdtion = "dbrft-bfgin-pbrbgrbpi";

    /**
     * Nbmf of tif <dodf>Adtion</dodf> for moving tif dbrft
     * to tif fnd of b pbrbgrbpi.
     * @sff #gftAdtions
     */
    publid stbtid finbl String fndPbrbgrbpiAdtion = "dbrft-fnd-pbrbgrbpi";

    /**
     * Nbmf of tif <dodf>Adtion</dodf> for moving tif dbrft
     * to tif bfginning of b pbrbgrbpi, fxtfnding tif sflfdtion.
     * @sff #gftAdtions
     */
    publid stbtid finbl String sflfdtionBfginPbrbgrbpiAdtion = "sflfdtion-bfgin-pbrbgrbpi";

    /**
     * Nbmf of tif <dodf>Adtion</dodf> for moving tif dbrft
     * to tif fnd of b pbrbgrbpi, fxtfnding tif sflfdtion.
     * @sff #gftAdtions
     */
    publid stbtid finbl String sflfdtionEndPbrbgrbpiAdtion = "sflfdtion-fnd-pbrbgrbpi";

    /**
     * Nbmf of tif <dodf>Adtion</dodf> for moving tif dbrft
     * to tif bfginning of tif dodumfnt.
     * @sff #gftAdtions
     */
    publid stbtid finbl String bfginAdtion = "dbrft-bfgin";

    /**
     * Nbmf of tif <dodf>Adtion</dodf> for moving tif dbrft
     * to tif fnd of tif dodumfnt.
     * @sff #gftAdtions
     */
    publid stbtid finbl String fndAdtion = "dbrft-fnd";

    /**
     * Nbmf of tif <dodf>Adtion</dodf> for moving tif dbrft
     * to tif bfginning of tif dodumfnt.
     * @sff #gftAdtions
     */
    publid stbtid finbl String sflfdtionBfginAdtion = "sflfdtion-bfgin";

    /**
     * Nbmf of tif Adtion for moving tif dbrft
     * to tif fnd of tif dodumfnt.
     * @sff #gftAdtions
     */
    publid stbtid finbl String sflfdtionEndAdtion = "sflfdtion-fnd";

    /**
     * Nbmf of tif Adtion for sflfdting b word bround tif dbrft.
     * @sff #gftAdtions
     */
    publid stbtid finbl String sflfdtWordAdtion = "sflfdt-word";

    /**
     * Nbmf of tif Adtion for sflfdting b linf bround tif dbrft.
     * @sff #gftAdtions
     */
    publid stbtid finbl String sflfdtLinfAdtion = "sflfdt-linf";

    /**
     * Nbmf of tif Adtion for sflfdting b pbrbgrbpi bround tif dbrft.
     * @sff #gftAdtions
     */
    publid stbtid finbl String sflfdtPbrbgrbpiAdtion = "sflfdt-pbrbgrbpi";

    /**
     * Nbmf of tif Adtion for sflfdting tif fntirf dodumfnt
     * @sff #gftAdtions
     */
    publid stbtid finbl String sflfdtAllAdtion = "sflfdt-bll";

    /**
     * Nbmf of tif Adtion for rfmoving sflfdtion
     * @sff #gftAdtions
     */
    /*publid*/ stbtid finbl String unsflfdtAdtion = "unsflfdt";

    /**
     * Nbmf of tif Adtion for toggling tif domponfnt's orifntbtion.
     * @sff #gftAdtions
     */
    /*publid*/ stbtid finbl String togglfComponfntOrifntbtionAdtion
        = "togglf-domponfntOrifntbtion";

    /**
     * Nbmf of tif bdtion tibt is fxfdutfd by dffbult if
     * b <fm>kfy typfd fvfnt</fm> is rfdfivfd bnd tifrf
     * is no kfymbp fntry.
     * @sff #gftAdtions
     */
    publid stbtid finbl String dffbultKfyTypfdAdtion = "dffbult-typfd";

    // --- Adtion implfmfntbtions ---------------------------------

    privbtf stbtid finbl Adtion[] dffbultAdtions = {
        nfw InsfrtContfntAdtion(), nfw DflftfPrfvCibrAdtion(),
        nfw DflftfNfxtCibrAdtion(), nfw RfbdOnlyAdtion(),
        nfw DflftfWordAdtion(dflftfPrfvWordAdtion),
        nfw DflftfWordAdtion(dflftfNfxtWordAdtion),
        nfw WritbblfAdtion(), nfw CutAdtion(),
        nfw CopyAdtion(), nfw PbstfAdtion(),
        nfw VfrtidblPbgfAdtion(pbgfUpAdtion, -1, fblsf),
        nfw VfrtidblPbgfAdtion(pbgfDownAdtion, 1, fblsf),
        nfw VfrtidblPbgfAdtion(sflfdtionPbgfUpAdtion, -1, truf),
        nfw VfrtidblPbgfAdtion(sflfdtionPbgfDownAdtion, 1, truf),
        nfw PbgfAdtion(sflfdtionPbgfLfftAdtion, truf, truf),
        nfw PbgfAdtion(sflfdtionPbgfRigitAdtion, fblsf, truf),
        nfw InsfrtBrfbkAdtion(), nfw BffpAdtion(),
        nfw NfxtVisublPositionAdtion(forwbrdAdtion, fblsf,
                                     SwingConstbnts.EAST),
        nfw NfxtVisublPositionAdtion(bbdkwbrdAdtion, fblsf,
                                     SwingConstbnts.WEST),
        nfw NfxtVisublPositionAdtion(sflfdtionForwbrdAdtion, truf,
                                     SwingConstbnts.EAST),
        nfw NfxtVisublPositionAdtion(sflfdtionBbdkwbrdAdtion, truf,
                                     SwingConstbnts.WEST),
        nfw NfxtVisublPositionAdtion(upAdtion, fblsf,
                                     SwingConstbnts.NORTH),
        nfw NfxtVisublPositionAdtion(downAdtion, fblsf,
                                     SwingConstbnts.SOUTH),
        nfw NfxtVisublPositionAdtion(sflfdtionUpAdtion, truf,
                                     SwingConstbnts.NORTH),
        nfw NfxtVisublPositionAdtion(sflfdtionDownAdtion, truf,
                                     SwingConstbnts.SOUTH),
        nfw BfginWordAdtion(bfginWordAdtion, fblsf),
        nfw EndWordAdtion(fndWordAdtion, fblsf),
        nfw BfginWordAdtion(sflfdtionBfginWordAdtion, truf),
        nfw EndWordAdtion(sflfdtionEndWordAdtion, truf),
        nfw PrfviousWordAdtion(prfviousWordAdtion, fblsf),
        nfw NfxtWordAdtion(nfxtWordAdtion, fblsf),
        nfw PrfviousWordAdtion(sflfdtionPrfviousWordAdtion, truf),
        nfw NfxtWordAdtion(sflfdtionNfxtWordAdtion, truf),
        nfw BfginLinfAdtion(bfginLinfAdtion, fblsf),
        nfw EndLinfAdtion(fndLinfAdtion, fblsf),
        nfw BfginLinfAdtion(sflfdtionBfginLinfAdtion, truf),
        nfw EndLinfAdtion(sflfdtionEndLinfAdtion, truf),
        nfw BfginPbrbgrbpiAdtion(bfginPbrbgrbpiAdtion, fblsf),
        nfw EndPbrbgrbpiAdtion(fndPbrbgrbpiAdtion, fblsf),
        nfw BfginPbrbgrbpiAdtion(sflfdtionBfginPbrbgrbpiAdtion, truf),
        nfw EndPbrbgrbpiAdtion(sflfdtionEndPbrbgrbpiAdtion, truf),
        nfw BfginAdtion(bfginAdtion, fblsf),
        nfw EndAdtion(fndAdtion, fblsf),
        nfw BfginAdtion(sflfdtionBfginAdtion, truf),
        nfw EndAdtion(sflfdtionEndAdtion, truf),
        nfw DffbultKfyTypfdAdtion(), nfw InsfrtTbbAdtion(),
        nfw SflfdtWordAdtion(), nfw SflfdtLinfAdtion(),
        nfw SflfdtPbrbgrbpiAdtion(), nfw SflfdtAllAdtion(),
        nfw UnsflfdtAdtion(), nfw TogglfComponfntOrifntbtionAdtion(),
        nfw DumpModflAdtion()
    };

    /**
     * Tif bdtion tibt is fxfdutfd by dffbult if
     * b <fm>kfy typfd fvfnt</fm> is rfdfivfd bnd tifrf
     * is no kfymbp fntry.  Tifrf is b vbribtion bdross
     * difffrfnt VM's in wibt gfts sfnt bs b <fm>kfy typfd</fm>
     * fvfnt, bnd tiis bdtion trifs to filtfr out tif undfsirfd
     * fvfnts.  Tiis filtfrs tif dontrol dibrbdtfrs bnd tiosf
     * witi tif ALT modififr.  It bllows Control-Alt sfqufndfs
     * tirougi bs tifsf form lfgitimbtf unidodf dibrbdtfrs on
     * somf PC kfybobrds.
     * <p>
     * If tif fvfnt dofsn't gft filtfrfd, it will try to insfrt
     * dontfnt into tif tfxt fditor.  Tif dontfnt is fftdifd
     * from tif dommbnd string of tif AdtionEvfnt.  Tif tfxt
     * fntry is donf tirougi tif <dodf>rfplbdfSflfdtion</dodf>
     * mftiod on tif tbrgft tfxt domponfnt.  Tiis is tif
     * bdtion tibt will bf firfd for most tfxt fntry tbsks.
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
     * @sff DffbultEditorKit#dffbultKfyTypfdAdtion
     * @sff DffbultEditorKit#gftAdtions
     * @sff Kfymbp#sftDffbultAdtion
     * @sff Kfymbp#gftDffbultAdtion
     */
    @SupprfssWbrnings("sfribl") // Sbmf-vfrsion sfriblizbtion only
    publid stbtid dlbss DffbultKfyTypfdAdtion fxtfnds TfxtAdtion {

        /**
         * Crfbtfs tiis objfdt witi tif bppropribtf idfntififr.
         */
        publid DffbultKfyTypfdAdtion() {
            supfr(dffbultKfyTypfdAdtion);
        }

        /**
         * Tif opfrbtion to pfrform wifn tiis bdtion is triggfrfd.
         *
         * @pbrbm f tif bdtion fvfnt
         */
        publid void bdtionPfrformfd(AdtionEvfnt f) {
            JTfxtComponfnt tbrgft = gftTfxtComponfnt(f);
            if ((tbrgft != null) && (f != null)) {
                if ((! tbrgft.isEditbblf()) || (! tbrgft.isEnbblfd())) {
                    rfturn;
                }
                String dontfnt = f.gftAdtionCommbnd();
                int mod = f.gftModififrs();
                if ((dontfnt != null) && (dontfnt.lfngti() > 0)) {
                    boolfbn isPrintbblfMbsk = truf;
                    Toolkit tk = Toolkit.gftDffbultToolkit();
                    if (tk instbndfof SunToolkit) {
                        isPrintbblfMbsk = ((SunToolkit)tk).isPrintbblfCibrbdtfrModififrsMbsk(mod);
                    }

                    if (isPrintbblfMbsk) {
                        dibr d = dontfnt.dibrAt(0);
                        if ((d >= 0x20) && (d != 0x7F)) {
                            tbrgft.rfplbdfSflfdtion(dontfnt);
                        }
                    }
                }
            }
        }
    }

    /**
     * Plbdfs dontfnt into tif bssodibtfd dodumfnt.
     * If tifrf is b sflfdtion, it is rfmovfd bfforf
     * tif nfw dontfnt is bddfd.
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
     * @sff DffbultEditorKit#insfrtContfntAdtion
     * @sff DffbultEditorKit#gftAdtions
     */
    @SupprfssWbrnings("sfribl") // Sbmf-vfrsion sfriblizbtion only
    publid stbtid dlbss InsfrtContfntAdtion fxtfnds TfxtAdtion {

        /**
         * Crfbtfs tiis objfdt witi tif bppropribtf idfntififr.
         */
        publid InsfrtContfntAdtion() {
            supfr(insfrtContfntAdtion);
        }

        /**
         * Tif opfrbtion to pfrform wifn tiis bdtion is triggfrfd.
         *
         * @pbrbm f tif bdtion fvfnt
         */
        publid void bdtionPfrformfd(AdtionEvfnt f) {
            JTfxtComponfnt tbrgft = gftTfxtComponfnt(f);
            if ((tbrgft != null) && (f != null)) {
                if ((! tbrgft.isEditbblf()) || (! tbrgft.isEnbblfd())) {
                    UIMbnbgfr.gftLookAndFffl().providfErrorFffdbbdk(tbrgft);
                    rfturn;
                }
                String dontfnt = f.gftAdtionCommbnd();
                if (dontfnt != null) {
                    tbrgft.rfplbdfSflfdtion(dontfnt);
                } flsf {
                    UIMbnbgfr.gftLookAndFffl().providfErrorFffdbbdk(tbrgft);
                }
            }
        }
    }

    /**
     * Plbdfs b linf/pbrbgrbpi brfbk into tif dodumfnt.
     * If tifrf is b sflfdtion, it is rfmovfd bfforf
     * tif brfbk is bddfd.
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
     * @sff DffbultEditorKit#insfrtBrfbkAdtion
     * @sff DffbultEditorKit#gftAdtions
     */
    @SupprfssWbrnings("sfribl") // Sbmf-vfrsion sfriblizbtion only
    publid stbtid dlbss InsfrtBrfbkAdtion fxtfnds TfxtAdtion {

        /**
         * Crfbtfs tiis objfdt witi tif bppropribtf idfntififr.
         */
        publid InsfrtBrfbkAdtion() {
            supfr(insfrtBrfbkAdtion);
        }

        /**
         * Tif opfrbtion to pfrform wifn tiis bdtion is triggfrfd.
         *
         * @pbrbm f tif bdtion fvfnt
         */
        publid void bdtionPfrformfd(AdtionEvfnt f) {
            JTfxtComponfnt tbrgft = gftTfxtComponfnt(f);
            if (tbrgft != null) {
                if ((! tbrgft.isEditbblf()) || (! tbrgft.isEnbblfd())) {
                    UIMbnbgfr.gftLookAndFffl().providfErrorFffdbbdk(tbrgft);
                    rfturn;
                }
                tbrgft.rfplbdfSflfdtion("\n");
            }
        }
    }

    /**
     * Plbdfs b tbb dibrbdtfr into tif dodumfnt. If tifrf
     * is b sflfdtion, it is rfmovfd bfforf tif tbb is bddfd.
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
     * @sff DffbultEditorKit#insfrtTbbAdtion
     * @sff DffbultEditorKit#gftAdtions
     */
    @SupprfssWbrnings("sfribl") // Sbmf-vfrsion sfriblizbtion only
    publid stbtid dlbss InsfrtTbbAdtion fxtfnds TfxtAdtion {

        /**
         * Crfbtfs tiis objfdt witi tif bppropribtf idfntififr.
         */
        publid InsfrtTbbAdtion() {
            supfr(insfrtTbbAdtion);
        }

        /**
         * Tif opfrbtion to pfrform wifn tiis bdtion is triggfrfd.
         *
         * @pbrbm f tif bdtion fvfnt
         */
        publid void bdtionPfrformfd(AdtionEvfnt f) {
            JTfxtComponfnt tbrgft = gftTfxtComponfnt(f);
            if (tbrgft != null) {
                if ((! tbrgft.isEditbblf()) || (! tbrgft.isEnbblfd())) {
                    UIMbnbgfr.gftLookAndFffl().providfErrorFffdbbdk(tbrgft);
                    rfturn;
                }
                tbrgft.rfplbdfSflfdtion("\t");
            }
        }
    }

    /*
     * Dflftfs tif dibrbdtfr of dontfnt tibt prfdfdfs tif
     * durrfnt dbrft position.
     * @sff DffbultEditorKit#dflftfPrfvCibrAdtion
     * @sff DffbultEditorKit#gftAdtions
     */
    @SupprfssWbrnings("sfribl") // Supfrdlbss is not sfriblizbblf bdross vfrsions
    stbtid dlbss DflftfPrfvCibrAdtion fxtfnds TfxtAdtion {

        /**
         * Crfbtfs tiis objfdt witi tif bppropribtf idfntififr.
         */
        DflftfPrfvCibrAdtion() {
            supfr(dflftfPrfvCibrAdtion);
        }

        /**
         * Tif opfrbtion to pfrform wifn tiis bdtion is triggfrfd.
         *
         * @pbrbm f tif bdtion fvfnt
         */
        publid void bdtionPfrformfd(AdtionEvfnt f) {
            JTfxtComponfnt tbrgft = gftTfxtComponfnt(f);
            boolfbn bffp = truf;
            if ((tbrgft != null) && (tbrgft.isEditbblf())) {
                try {
                    Dodumfnt dod = tbrgft.gftDodumfnt();
                    Cbrft dbrft = tbrgft.gftCbrft();
                    int dot = dbrft.gftDot();
                    int mbrk = dbrft.gftMbrk();
                    if (dot != mbrk) {
                        dod.rfmovf(Mbti.min(dot, mbrk), Mbti.bbs(dot - mbrk));
                        bffp = fblsf;
                    } flsf if (dot > 0) {
                        int dflCibrs = 1;

                        if (dot > 1) {
                            String dotCibrs = dod.gftTfxt(dot - 2, 2);
                            dibr d0 = dotCibrs.dibrAt(0);
                            dibr d1 = dotCibrs.dibrAt(1);

                            if (d0 >= '\uD800' && d0 <= '\uDBFF' &&
                                d1 >= '\uDC00' && d1 <= '\uDFFF') {
                                dflCibrs = 2;
                            }
                        }

                        dod.rfmovf(dot - dflCibrs, dflCibrs);
                        bffp = fblsf;
                    }
                } dbtdi (BbdLodbtionExdfption bl) {
                }
            }
            if (bffp) {
                UIMbnbgfr.gftLookAndFffl().providfErrorFffdbbdk(tbrgft);
            }
        }
    }

    /*
     * Dflftfs tif dibrbdtfr of dontfnt tibt follows tif
     * durrfnt dbrft position.
     * @sff DffbultEditorKit#dflftfNfxtCibrAdtion
     * @sff DffbultEditorKit#gftAdtions
     */
    @SupprfssWbrnings("sfribl") // Supfrdlbss is not sfriblizbblf bdross vfrsions
    stbtid dlbss DflftfNfxtCibrAdtion fxtfnds TfxtAdtion {

        /* Crfbtf tiis objfdt witi tif bppropribtf idfntififr. */
        DflftfNfxtCibrAdtion() {
            supfr(dflftfNfxtCibrAdtion);
        }

        /** Tif opfrbtion to pfrform wifn tiis bdtion is triggfrfd. */
        publid void bdtionPfrformfd(AdtionEvfnt f) {
            JTfxtComponfnt tbrgft = gftTfxtComponfnt(f);
            boolfbn bffp = truf;
            if ((tbrgft != null) && (tbrgft.isEditbblf())) {
                try {
                    Dodumfnt dod = tbrgft.gftDodumfnt();
                    Cbrft dbrft = tbrgft.gftCbrft();
                    int dot = dbrft.gftDot();
                    int mbrk = dbrft.gftMbrk();
                    if (dot != mbrk) {
                        dod.rfmovf(Mbti.min(dot, mbrk), Mbti.bbs(dot - mbrk));
                        bffp = fblsf;
                    } flsf if (dot < dod.gftLfngti()) {
                        int dflCibrs = 1;

                        if (dot < dod.gftLfngti() - 1) {
                            String dotCibrs = dod.gftTfxt(dot, 2);
                            dibr d0 = dotCibrs.dibrAt(0);
                            dibr d1 = dotCibrs.dibrAt(1);

                            if (d0 >= '\uD800' && d0 <= '\uDBFF' &&
                                d1 >= '\uDC00' && d1 <= '\uDFFF') {
                                dflCibrs = 2;
                            }
                        }

                        dod.rfmovf(dot, dflCibrs);
                        bffp = fblsf;
                    }
                } dbtdi (BbdLodbtionExdfption bl) {
                }
            }
            if (bffp) {
                UIMbnbgfr.gftLookAndFffl().providfErrorFffdbbdk(tbrgft);
            }
        }
    }


    /*
     * Dflftfs tif word tibt prfdfdfs/follows tif bfginning of tif sflfdtion.
     * @sff DffbultEditorKit#gftAdtions
     */
    @SupprfssWbrnings("sfribl") // Supfrdlbss is not sfriblizbblf bdross vfrsions
    stbtid dlbss DflftfWordAdtion fxtfnds TfxtAdtion {
        DflftfWordAdtion(String nbmf) {
            supfr(nbmf);
            bssfrt (nbmf == dflftfPrfvWordAdtion)
                || (nbmf == dflftfNfxtWordAdtion);
        }
        /**
         * Tif opfrbtion to pfrform wifn tiis bdtion is triggfrfd.
         *
         * @pbrbm f tif bdtion fvfnt
         */
        publid void bdtionPfrformfd(AdtionEvfnt f) {
            finbl JTfxtComponfnt tbrgft = gftTfxtComponfnt(f);
            if ((tbrgft != null) && (f != null)) {
                if ((! tbrgft.isEditbblf()) || (! tbrgft.isEnbblfd())) {
                    UIMbnbgfr.gftLookAndFffl().providfErrorFffdbbdk(tbrgft);
                    rfturn;
                }
                boolfbn bffp = truf;
                try {
                    finbl int stbrt = tbrgft.gftSflfdtionStbrt();
                    finbl Elfmfnt linf =
                        Utilitifs.gftPbrbgrbpiElfmfnt(tbrgft, stbrt);
                    int fnd;
                    if (dflftfNfxtWordAdtion == gftVbluf(Adtion.NAME)) {
                        fnd = Utilitifs.
                            gftNfxtWordInPbrbgrbpi(tbrgft, linf, stbrt, fblsf);
                        if (fnd == jbvb.tfxt.BrfbkItfrbtor.DONE) {
                            //lbst word in tif pbrbgrbpi
                            finbl int fndOfLinf = linf.gftEndOffsft();
                            if (stbrt == fndOfLinf - 1) {
                                //for lbst position rfmovf lbst \n
                                fnd = fndOfLinf;
                            } flsf {
                                //rfmovf to tif fnd of tif pbrbgrbpi
                                fnd = fndOfLinf - 1;
                            }
                        }
                    } flsf {
                        fnd = Utilitifs.
                            gftPrfvWordInPbrbgrbpi(tbrgft, linf, stbrt);
                        if (fnd == jbvb.tfxt.BrfbkItfrbtor.DONE) {
                            //tifrf is no prfvious word in tif pbrbgrbpi
                            finbl int stbrtOfLinf = linf.gftStbrtOffsft();
                            if (stbrt == stbrtOfLinf) {
                                //for first position rfmovf prfvious \n
                                fnd = stbrtOfLinf - 1;
                            } flsf {
                                //rfmovf to tif stbrt of tif pbrbgrbpi
                                fnd = stbrtOfLinf;
                            }
                        }
                    }
                    int offs = Mbti.min(stbrt, fnd);
                    int lfn = Mbti.bbs(fnd - stbrt);
                    if (offs >= 0) {
                        tbrgft.gftDodumfnt().rfmovf(offs, lfn);
                        bffp = fblsf;
                    }
                } dbtdi (BbdLodbtionExdfption ignorf) {
                }
                if (bffp) {
                    UIMbnbgfr.gftLookAndFffl().providfErrorFffdbbdk(tbrgft);
                }
            }
        }
    }


    /*
     * Sfts tif fditor into rfbd-only modf.
     * @sff DffbultEditorKit#rfbdOnlyAdtion
     * @sff DffbultEditorKit#gftAdtions
     */
    @SupprfssWbrnings("sfribl") // Supfrdlbss is not sfriblizbblf bdross vfrsions
    stbtid dlbss RfbdOnlyAdtion fxtfnds TfxtAdtion {

        /* Crfbtf tiis objfdt witi tif bppropribtf idfntififr. */
        RfbdOnlyAdtion() {
            supfr(rfbdOnlyAdtion);
        }

        /**
         * Tif opfrbtion to pfrform wifn tiis bdtion is triggfrfd.
         *
         * @pbrbm f tif bdtion fvfnt
         */
        publid void bdtionPfrformfd(AdtionEvfnt f) {
            JTfxtComponfnt tbrgft = gftTfxtComponfnt(f);
            if (tbrgft != null) {
                tbrgft.sftEditbblf(fblsf);
            }
        }
    }

    /*
     * Sfts tif fditor into writfbblf modf.
     * @sff DffbultEditorKit#writbblfAdtion
     * @sff DffbultEditorKit#gftAdtions
     */
    @SupprfssWbrnings("sfribl") // Supfrdlbss is not sfriblizbblf bdross vfrsions
    stbtid dlbss WritbblfAdtion fxtfnds TfxtAdtion {

        /* Crfbtf tiis objfdt witi tif bppropribtf idfntififr. */
        WritbblfAdtion() {
            supfr(writbblfAdtion);
        }

        /**
         * Tif opfrbtion to pfrform wifn tiis bdtion is triggfrfd.
         *
         * @pbrbm f tif bdtion fvfnt
         */
        publid void bdtionPfrformfd(AdtionEvfnt f) {
            JTfxtComponfnt tbrgft = gftTfxtComponfnt(f);
            if (tbrgft != null) {
                tbrgft.sftEditbblf(truf);
            }
        }
    }

    /**
     * Cuts tif sflfdtfd rfgion bnd plbdf its dontfnts
     * into tif systfm dlipbobrd.
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
     * @sff DffbultEditorKit#dutAdtion
     * @sff DffbultEditorKit#gftAdtions
     */
    @SupprfssWbrnings("sfribl") // Sbmf-vfrsion sfriblizbtion only
    publid stbtid dlbss CutAdtion fxtfnds TfxtAdtion {

        /** Crfbtf tiis objfdt witi tif bppropribtf idfntififr. */
        publid CutAdtion() {
            supfr(dutAdtion);
        }

        /**
         * Tif opfrbtion to pfrform wifn tiis bdtion is triggfrfd.
         *
         * @pbrbm f tif bdtion fvfnt
         */
        publid void bdtionPfrformfd(AdtionEvfnt f) {
            JTfxtComponfnt tbrgft = gftTfxtComponfnt(f);
            if (tbrgft != null) {
                tbrgft.dut();
            }
        }
    }

    /**
     * Copifs tif sflfdtfd rfgion bnd plbdf its dontfnts
     * into tif systfm dlipbobrd.
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
     * @sff DffbultEditorKit#dopyAdtion
     * @sff DffbultEditorKit#gftAdtions
     */
    @SupprfssWbrnings("sfribl") // Sbmf-vfrsion sfriblizbtion only
    publid stbtid dlbss CopyAdtion fxtfnds TfxtAdtion {

        /** Crfbtf tiis objfdt witi tif bppropribtf idfntififr. */
        publid CopyAdtion() {
            supfr(dopyAdtion);
        }

        /**
         * Tif opfrbtion to pfrform wifn tiis bdtion is triggfrfd.
         *
         * @pbrbm f tif bdtion fvfnt
         */
        publid void bdtionPfrformfd(AdtionEvfnt f) {
            JTfxtComponfnt tbrgft = gftTfxtComponfnt(f);
            if (tbrgft != null) {
                tbrgft.dopy();
            }
        }
    }

    /**
     * Pbstfs tif dontfnts of tif systfm dlipbobrd into tif
     * sflfdtfd rfgion, or bfforf tif dbrft if notiing is
     * sflfdtfd.
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
     * @sff DffbultEditorKit#pbstfAdtion
     * @sff DffbultEditorKit#gftAdtions
     */
    @SupprfssWbrnings("sfribl") // Sbmf-vfrsion sfriblizbtion only
    publid stbtid dlbss PbstfAdtion fxtfnds TfxtAdtion {

        /** Crfbtf tiis objfdt witi tif bppropribtf idfntififr. */
        publid PbstfAdtion() {
            supfr(pbstfAdtion);
        }

        /**
         * Tif opfrbtion to pfrform wifn tiis bdtion is triggfrfd.
         *
         * @pbrbm f tif bdtion fvfnt
         */
        publid void bdtionPfrformfd(AdtionEvfnt f) {
            JTfxtComponfnt tbrgft = gftTfxtComponfnt(f);
            if (tbrgft != null) {
                tbrgft.pbstf();
            }
        }
    }

    /**
     * Crfbtfs b bffp.
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
     * @sff DffbultEditorKit#bffpAdtion
     * @sff DffbultEditorKit#gftAdtions
     */
    @SupprfssWbrnings("sfribl") // Sbmf-vfrsion sfriblizbtion only
    publid stbtid dlbss BffpAdtion fxtfnds TfxtAdtion {

        /** Crfbtf tiis objfdt witi tif bppropribtf idfntififr. */
        publid BffpAdtion() {
            supfr(bffpAdtion);
        }

        /**
         * Tif opfrbtion to pfrform wifn tiis bdtion is triggfrfd.
         *
         * @pbrbm f tif bdtion fvfnt
         */
        publid void bdtionPfrformfd(AdtionEvfnt f) {
            JTfxtComponfnt tbrgft = gftTfxtComponfnt(f);
            UIMbnbgfr.gftLookAndFffl().providfErrorFffdbbdk(tbrgft);
        }
    }

    /**
     * Sdrolls up/down vfrtidblly.  Tif sflfdt vfrsion of tiis bdtion fxtfnds
     * tif sflfdtion, instfbd of simply moving tif dbrft.
     *
     * @sff DffbultEditorKit#pbgfUpAdtion
     * @sff DffbultEditorKit#pbgfDownAdtion
     * @sff DffbultEditorKit#gftAdtions
     */
    @SupprfssWbrnings("sfribl") // Supfrdlbss is not sfriblizbblf bdross vfrsions
    stbtid dlbss VfrtidblPbgfAdtion fxtfnds TfxtAdtion {

        /** Crfbtf tiis objfdt witi tif bppropribtf idfntififr. */
        publid VfrtidblPbgfAdtion(String nm, int dirfdtion, boolfbn sflfdt) {
            supfr(nm);
            tiis.sflfdt = sflfdt;
            tiis.dirfdtion = dirfdtion;
        }

        /** Tif opfrbtion to pfrform wifn tiis bdtion is triggfrfd. */
        publid void bdtionPfrformfd(AdtionEvfnt f) {
            JTfxtComponfnt tbrgft = gftTfxtComponfnt(f);
            if (tbrgft != null) {
                Rfdtbnglf visiblf = tbrgft.gftVisiblfRfdt();
                Rfdtbnglf nfwVis = nfw Rfdtbnglf(visiblf);
                int sflfdtfdIndfx = tbrgft.gftCbrftPosition();
                int sdrollAmount = dirfdtion *
                        tbrgft.gftSdrollbblfBlodkIndrfmfnt(
                                  visiblf, SwingConstbnts.VERTICAL, dirfdtion);
                int initiblY = visiblf.y;
                Cbrft dbrft = tbrgft.gftCbrft();
                Point mbgidPosition = dbrft.gftMbgidCbrftPosition();

                if (sflfdtfdIndfx != -1) {
                    try {
                        Rfdtbnglf dotBounds = tbrgft.modflToVifw(
                                                     sflfdtfdIndfx);
                        int x = (mbgidPosition != null) ? mbgidPosition.x :
                                                          dotBounds.x;
                        int i = dotBounds.ifigit;
                        if (i > 0) {
                            // Wf wbnt to sdroll by b multiplf of dbrft ifigit,
                            // rounding towbrds lowfr intfgfr
                            sdrollAmount = sdrollAmount / i * i;
                        }
                        nfwVis.y = donstrbinY(tbrgft,
                                initiblY + sdrollAmount, visiblf.ifigit);

                        int nfwIndfx;

                        if (visiblf.dontbins(dotBounds.x, dotBounds.y)) {
                            // Dot is durrfntly visiblf, bbsf tif nfw
                            // lodbtion off tif old, or
                            nfwIndfx = tbrgft.vifwToModfl(
                                nfw Point(x, donstrbinY(tbrgft,
                                          dotBounds.y + sdrollAmount, 0)));
                        }
                        flsf {
                            // Dot isn't visiblf, dioosf tif top or tif bottom
                            // for tif nfw lodbtion.
                            if (dirfdtion == -1) {
                                nfwIndfx = tbrgft.vifwToModfl(nfw Point(
                                    x, nfwVis.y));
                            }
                            flsf {
                                nfwIndfx = tbrgft.vifwToModfl(nfw Point(
                                    x, nfwVis.y + visiblf.ifigit));
                            }
                        }
                        nfwIndfx = donstrbinOffsft(tbrgft, nfwIndfx);
                        if (nfwIndfx != sflfdtfdIndfx) {
                            // Mbkf surf tif nfw visiblf lodbtion dontbins
                            // tif lodbtion of dot, otifrwisf Cbrft will
                            // dbusf bn bdditionbl sdroll.
                            int nfwY = gftAdjustfdY(tbrgft, nfwVis, nfwIndfx);

                            if (dirfdtion == -1 && nfwY <= initiblY || dirfdtion == 1 && nfwY >= initiblY) {
                                // Cibngf indfx bnd dorrfdt nfwVis.y only if won't dbusf sdrolling upwbrd
                                nfwVis.y = nfwY;

                                if (sflfdt) {
                                    tbrgft.movfCbrftPosition(nfwIndfx);
                                } flsf {
                                    tbrgft.sftCbrftPosition(nfwIndfx);
                                }
                            }
                        }
                    } dbtdi (BbdLodbtionExdfption blf) { }
                } flsf {
                    nfwVis.y = donstrbinY(tbrgft,
                            initiblY + sdrollAmount, visiblf.ifigit);
                }
                if (mbgidPosition != null) {
                    dbrft.sftMbgidCbrftPosition(mbgidPosition);
                }
                tbrgft.sdrollRfdtToVisiblf(nfwVis);
            }
        }

        /**
         * Mbkfs surf <dodf>y</dodf> is b vblid lodbtion in
         * <dodf>tbrgft</dodf>.
         */
        privbtf int donstrbinY(JTfxtComponfnt tbrgft, int y, int vis) {
            if (y < 0) {
                y = 0;
            }
            flsf if (y + vis > tbrgft.gftHfigit()) {
                y = Mbti.mbx(0, tbrgft.gftHfigit() - vis);
            }
            rfturn y;
        }

        /**
         * Ensurfs tibt <dodf>offsft</dodf> is b vblid offsft into tif
         * modfl for <dodf>tfxt</dodf>.
         */
        privbtf int donstrbinOffsft(JTfxtComponfnt tfxt, int offsft) {
            Dodumfnt dod = tfxt.gftDodumfnt();

            if ((offsft != 0) && (offsft > dod.gftLfngti())) {
                offsft = dod.gftLfngti();
            }
            if (offsft  < 0) {
                offsft = 0;
            }
            rfturn offsft;
        }

        /**
         * Rfturns bdjustsfd {@dodf y} position tibt indidbtfs tif lodbtion to sdroll to
         * bftfr sflfdting <dodf>indfx</dodf>.
         */
        privbtf int gftAdjustfdY(JTfxtComponfnt tfxt, Rfdtbnglf visiblf, int indfx) {
            int rfsult = visiblf.y;

            try {
                Rfdtbnglf dotBounds = tfxt.modflToVifw(indfx);

                if (dotBounds.y < visiblf.y) {
                    rfsult = dotBounds.y;
                } flsf {
                    if ((dotBounds.y > visiblf.y + visiblf.ifigit) ||
                            (dotBounds.y + dotBounds.ifigit > visiblf.y + visiblf.ifigit)) {
                        rfsult = dotBounds.y + dotBounds.ifigit - visiblf.ifigit;
                    }
                }
            } dbtdi (BbdLodbtionExdfption blf) {
            }

            rfturn rfsult;
        }

        /**
         * Adjusts tif Rfdtbnglf to dontbin tif bounds of tif dibrbdtfr bt
         * <dodf>indfx</dodf> in rfsponsf to b pbgf up.
         */
        privbtf boolfbn sflfdt;

        /**
         * Dirfdtion to sdroll, 1 is down, -1 is up.
         */
        privbtf int dirfdtion;
    }


    /**
     * Pbgfs onf vifw to tif lfft or rigit.
     */
    @SupprfssWbrnings("sfribl") // Supfrdlbss is not sfriblizbblf bdross vfrsions
    stbtid dlbss PbgfAdtion fxtfnds TfxtAdtion {

        /** Crfbtf tiis objfdt witi tif bppropribtf idfntififr. */
        publid PbgfAdtion(String nm, boolfbn lfft, boolfbn sflfdt) {
            supfr(nm);
            tiis.sflfdt = sflfdt;
            tiis.lfft = lfft;
        }

        /** Tif opfrbtion to pfrform wifn tiis bdtion is triggfrfd. */
        publid void bdtionPfrformfd(AdtionEvfnt f) {
            JTfxtComponfnt tbrgft = gftTfxtComponfnt(f);
            if (tbrgft != null) {
                int sflfdtfdIndfx;
                Rfdtbnglf visiblf = nfw Rfdtbnglf();
                tbrgft.domputfVisiblfRfdt(visiblf);
                if (lfft) {
                    visiblf.x = Mbti.mbx(0, visiblf.x - visiblf.widti);
                }
                flsf {
                    visiblf.x += visiblf.widti;
                }

                sflfdtfdIndfx = tbrgft.gftCbrftPosition();
                if(sflfdtfdIndfx != -1) {
                    if (lfft) {
                        sflfdtfdIndfx = tbrgft.vifwToModfl
                            (nfw Point(visiblf.x, visiblf.y));
                    }
                    flsf {
                        sflfdtfdIndfx = tbrgft.vifwToModfl
                            (nfw Point(visiblf.x + visiblf.widti - 1,
                                       visiblf.y + visiblf.ifigit - 1));
                    }
                    Dodumfnt dod = tbrgft.gftDodumfnt();
                    if ((sflfdtfdIndfx != 0) &&
                        (sflfdtfdIndfx  > (dod.gftLfngti()-1))) {
                        sflfdtfdIndfx = dod.gftLfngti()-1;
                    }
                    flsf if(sflfdtfdIndfx  < 0) {
                        sflfdtfdIndfx = 0;
                    }
                    if (sflfdt)
                        tbrgft.movfCbrftPosition(sflfdtfdIndfx);
                    flsf
                        tbrgft.sftCbrftPosition(sflfdtfdIndfx);
                }
            }
        }

        privbtf boolfbn sflfdt;
        privbtf boolfbn lfft;
    }

    @SupprfssWbrnings("sfribl") // Supfrdlbss is not sfriblizbblf bdross vfrsions
    stbtid dlbss DumpModflAdtion fxtfnds TfxtAdtion {

        DumpModflAdtion() {
            supfr("dump-modfl");
        }

        publid void bdtionPfrformfd(AdtionEvfnt f) {
            JTfxtComponfnt tbrgft = gftTfxtComponfnt(f);
            if (tbrgft != null) {
                Dodumfnt d = tbrgft.gftDodumfnt();
                if (d instbndfof AbstrbdtDodumfnt) {
                    ((AbstrbdtDodumfnt) d).dump(Systfm.frr);
                }
            }
        }
    }

    /*
     * Adtion to movf tif sflfdtion by wby of tif
     * gftNfxtVisublPositionFrom mftiod. Construdtor indidbtfs dirfdtion
     * to usf.
     */
    @SupprfssWbrnings("sfribl") // Supfrdlbss is not sfriblizbblf bdross vfrsions
    stbtid dlbss NfxtVisublPositionAdtion fxtfnds TfxtAdtion {

        /**
         * Crfbtf tiis bdtion witi tif bppropribtf idfntififr.
         * @pbrbm nm  tif nbmf of tif bdtion, Adtion.NAME.
         * @pbrbm sflfdt wiftifr to fxtfnd tif sflfdtion wifn
         *  dibnging tif dbrft position.
         */
        NfxtVisublPositionAdtion(String nm, boolfbn sflfdt, int dirfdtion) {
            supfr(nm);
            tiis.sflfdt = sflfdt;
            tiis.dirfdtion = dirfdtion;
        }

        /** Tif opfrbtion to pfrform wifn tiis bdtion is triggfrfd. */
        publid void bdtionPfrformfd(AdtionEvfnt f) {
            JTfxtComponfnt tbrgft = gftTfxtComponfnt(f);
            if (tbrgft != null) {
                Cbrft dbrft = tbrgft.gftCbrft();
                DffbultCbrft bidiCbrft = (dbrft instbndfof DffbultCbrft) ?
                                              (DffbultCbrft)dbrft : null;
                int dot = dbrft.gftDot();
                Position.Bibs[] bibs = nfw Position.Bibs[1];
                Point mbgidPosition = dbrft.gftMbgidCbrftPosition();

                try {
                    if(mbgidPosition == null &&
                       (dirfdtion == SwingConstbnts.NORTH ||
                        dirfdtion == SwingConstbnts.SOUTH)) {
                        Rfdtbnglf r = (bidiCbrft != null) ?
                                tbrgft.gftUI().modflToVifw(tbrgft, dot,
                                                      bidiCbrft.gftDotBibs()) :
                                tbrgft.modflToVifw(dot);
                        mbgidPosition = nfw Point(r.x, r.y);
                    }

                    NbvigbtionFiltfr filtfr = tbrgft.gftNbvigbtionFiltfr();

                    if (filtfr != null) {
                        dot = filtfr.gftNfxtVisublPositionFrom
                                     (tbrgft, dot, (bidiCbrft != null) ?
                                      bidiCbrft.gftDotBibs() :
                                      Position.Bibs.Forwbrd, dirfdtion, bibs);
                    }
                    flsf {
                        dot = tbrgft.gftUI().gftNfxtVisublPositionFrom
                                     (tbrgft, dot, (bidiCbrft != null) ?
                                      bidiCbrft.gftDotBibs() :
                                      Position.Bibs.Forwbrd, dirfdtion, bibs);
                    }
                    if(bibs[0] == null) {
                        bibs[0] = Position.Bibs.Forwbrd;
                    }
                    if(bidiCbrft != null) {
                        if (sflfdt) {
                            bidiCbrft.movfDot(dot, bibs[0]);
                        } flsf {
                            bidiCbrft.sftDot(dot, bibs[0]);
                        }
                    }
                    flsf {
                        if (sflfdt) {
                            dbrft.movfDot(dot);
                        } flsf {
                            dbrft.sftDot(dot);
                        }
                    }
                    if(mbgidPosition != null &&
                       (dirfdtion == SwingConstbnts.NORTH ||
                        dirfdtion == SwingConstbnts.SOUTH)) {
                        tbrgft.gftCbrft().sftMbgidCbrftPosition(mbgidPosition);
                    }
                } dbtdi (BbdLodbtionExdfption fx) {
                }
            }
        }

        privbtf boolfbn sflfdt;
        privbtf int dirfdtion;
    }

    /*
     * Position tif dbrft to tif bfginning of tif word.
     * @sff DffbultEditorKit#bfginWordAdtion
     * @sff DffbultEditorKit#sflfdtBfginWordAdtion
     * @sff DffbultEditorKit#gftAdtions
     */
    @SupprfssWbrnings("sfribl") // Supfrdlbss is not sfriblizbblf bdross vfrsions
    stbtid dlbss BfginWordAdtion fxtfnds TfxtAdtion {

        /**
         * Crfbtf tiis bdtion witi tif bppropribtf idfntififr.
         * @pbrbm nm  tif nbmf of tif bdtion, Adtion.NAME.
         * @pbrbm sflfdt wiftifr to fxtfnd tif sflfdtion wifn
         *  dibnging tif dbrft position.
         */
        BfginWordAdtion(String nm, boolfbn sflfdt) {
            supfr(nm);
            tiis.sflfdt = sflfdt;
        }

        /** Tif opfrbtion to pfrform wifn tiis bdtion is triggfrfd. */
        publid void bdtionPfrformfd(AdtionEvfnt f) {
            JTfxtComponfnt tbrgft = gftTfxtComponfnt(f);
            if (tbrgft != null) {
                try {
                    int offs = tbrgft.gftCbrftPosition();
                    int bfgOffs = Utilitifs.gftWordStbrt(tbrgft, offs);
                    if (sflfdt) {
                        tbrgft.movfCbrftPosition(bfgOffs);
                    } flsf {
                        tbrgft.sftCbrftPosition(bfgOffs);
                    }
                } dbtdi (BbdLodbtionExdfption bl) {
                    UIMbnbgfr.gftLookAndFffl().providfErrorFffdbbdk(tbrgft);
                }
            }
        }

        privbtf boolfbn sflfdt;
    }

    /*
     * Position tif dbrft to tif fnd of tif word.
     * @sff DffbultEditorKit#fndWordAdtion
     * @sff DffbultEditorKit#sflfdtEndWordAdtion
     * @sff DffbultEditorKit#gftAdtions
     */
    @SupprfssWbrnings("sfribl") // Supfrdlbss is not sfriblizbblf bdross vfrsions
    stbtid dlbss EndWordAdtion fxtfnds TfxtAdtion {

        /**
         * Crfbtf tiis bdtion witi tif bppropribtf idfntififr.
         * @pbrbm nm  tif nbmf of tif bdtion, Adtion.NAME.
         * @pbrbm sflfdt wiftifr to fxtfnd tif sflfdtion wifn
         *  dibnging tif dbrft position.
         */
        EndWordAdtion(String nm, boolfbn sflfdt) {
            supfr(nm);
            tiis.sflfdt = sflfdt;
        }

        /** Tif opfrbtion to pfrform wifn tiis bdtion is triggfrfd. */
        publid void bdtionPfrformfd(AdtionEvfnt f) {
            JTfxtComponfnt tbrgft = gftTfxtComponfnt(f);
            if (tbrgft != null) {
                try {
                    int offs = tbrgft.gftCbrftPosition();
                    int fndOffs = Utilitifs.gftWordEnd(tbrgft, offs);
                    if (sflfdt) {
                        tbrgft.movfCbrftPosition(fndOffs);
                    } flsf {
                        tbrgft.sftCbrftPosition(fndOffs);
                    }
                } dbtdi (BbdLodbtionExdfption bl) {
                    UIMbnbgfr.gftLookAndFffl().providfErrorFffdbbdk(tbrgft);
                }
            }
        }

        privbtf boolfbn sflfdt;
    }

    /*
     * Position tif dbrft to tif bfginning of tif prfvious word.
     * @sff DffbultEditorKit#prfviousWordAdtion
     * @sff DffbultEditorKit#sflfdtPrfviousWordAdtion
     * @sff DffbultEditorKit#gftAdtions
     */
    @SupprfssWbrnings("sfribl") // Supfrdlbss is not sfriblizbblf bdross vfrsions
    stbtid dlbss PrfviousWordAdtion fxtfnds TfxtAdtion {

        /**
         * Crfbtf tiis bdtion witi tif bppropribtf idfntififr.
         * @pbrbm nm  tif nbmf of tif bdtion, Adtion.NAME.
         * @pbrbm sflfdt wiftifr to fxtfnd tif sflfdtion wifn
         *  dibnging tif dbrft position.
         */
        PrfviousWordAdtion(String nm, boolfbn sflfdt) {
            supfr(nm);
            tiis.sflfdt = sflfdt;
        }

        /** Tif opfrbtion to pfrform wifn tiis bdtion is triggfrfd. */
        publid void bdtionPfrformfd(AdtionEvfnt f) {
            JTfxtComponfnt tbrgft = gftTfxtComponfnt(f);
            if (tbrgft != null) {
                int offs = tbrgft.gftCbrftPosition();
                boolfbn fbilfd = fblsf;
                try {
                    Elfmfnt durPbrb =
                            Utilitifs.gftPbrbgrbpiElfmfnt(tbrgft, offs);
                    offs = Utilitifs.gftPrfviousWord(tbrgft, offs);
                    if(offs < durPbrb.gftStbrtOffsft()) {
                        // wf siould first movf to tif fnd of tif
                        // prfvious pbrbgrbpi (bug #4278839)
                        offs = Utilitifs.gftPbrbgrbpiElfmfnt(tbrgft, offs).
                                gftEndOffsft() - 1;
                    }
                } dbtdi (BbdLodbtionExdfption bl) {
                    if (offs != 0) {
                        offs = 0;
                    }
                    flsf {
                        fbilfd = truf;
                    }
                }
                if (!fbilfd) {
                    if (sflfdt) {
                        tbrgft.movfCbrftPosition(offs);
                    } flsf {
                        tbrgft.sftCbrftPosition(offs);
                    }
                }
                flsf {
                    UIMbnbgfr.gftLookAndFffl().providfErrorFffdbbdk(tbrgft);
                }
            }
        }

        privbtf boolfbn sflfdt;
    }

    /*
     * Position tif dbrft to tif nfxt of tif word.
     * @sff DffbultEditorKit#nfxtWordAdtion
     * @sff DffbultEditorKit#sflfdtNfxtWordAdtion
     * @sff DffbultEditorKit#gftAdtions
     */
    @SupprfssWbrnings("sfribl") // Supfrdlbss is not sfriblizbblf bdross vfrsions
    stbtid dlbss NfxtWordAdtion fxtfnds TfxtAdtion {

        /**
         * Crfbtf tiis bdtion witi tif bppropribtf idfntififr.
         * @pbrbm nm  tif nbmf of tif bdtion, Adtion.NAME.
         * @pbrbm sflfdt wiftifr to fxtfnd tif sflfdtion wifn
         *  dibnging tif dbrft position.
         */
        NfxtWordAdtion(String nm, boolfbn sflfdt) {
            supfr(nm);
            tiis.sflfdt = sflfdt;
        }

        /** Tif opfrbtion to pfrform wifn tiis bdtion is triggfrfd. */
        publid void bdtionPfrformfd(AdtionEvfnt f) {
            JTfxtComponfnt tbrgft = gftTfxtComponfnt(f);
            if (tbrgft != null) {
                int offs = tbrgft.gftCbrftPosition();
                boolfbn fbilfd = fblsf;
                int oldOffs = offs;
                Elfmfnt durPbrb =
                        Utilitifs.gftPbrbgrbpiElfmfnt(tbrgft, offs);
                try {
                    offs = Utilitifs.gftNfxtWord(tbrgft, offs);
                    if(offs >= durPbrb.gftEndOffsft() &&
                            oldOffs != durPbrb.gftEndOffsft() - 1) {
                        // wf siould first movf to tif fnd of durrfnt
                        // pbrbgrbpi (bug #4278839)
                        offs = durPbrb.gftEndOffsft() - 1;
                    }
                } dbtdi (BbdLodbtionExdfption bl) {
                    int fnd = tbrgft.gftDodumfnt().gftLfngti();
                    if (offs != fnd) {
                        if(oldOffs != durPbrb.gftEndOffsft() - 1) {
                            offs = durPbrb.gftEndOffsft() - 1;
                        } flsf {
                        offs = fnd;
                    }
                    }
                    flsf {
                        fbilfd = truf;
                    }
                }
                if (!fbilfd) {
                    if (sflfdt) {
                        tbrgft.movfCbrftPosition(offs);
                    } flsf {
                        tbrgft.sftCbrftPosition(offs);
                    }
                }
                flsf {
                    UIMbnbgfr.gftLookAndFffl().providfErrorFffdbbdk(tbrgft);
                }
            }
        }

        privbtf boolfbn sflfdt;
    }

    /*
     * Position tif dbrft to tif bfginning of tif linf.
     * @sff DffbultEditorKit#bfginLinfAdtion
     * @sff DffbultEditorKit#sflfdtBfginLinfAdtion
     * @sff DffbultEditorKit#gftAdtions
     */
    @SupprfssWbrnings("sfribl") // Supfrdlbss is not sfriblizbblf bdross vfrsions
    stbtid dlbss BfginLinfAdtion fxtfnds TfxtAdtion {

        /**
         * Crfbtf tiis bdtion witi tif bppropribtf idfntififr.
         * @pbrbm nm  tif nbmf of tif bdtion, Adtion.NAME.
         * @pbrbm sflfdt wiftifr to fxtfnd tif sflfdtion wifn
         *  dibnging tif dbrft position.
         */
        BfginLinfAdtion(String nm, boolfbn sflfdt) {
            supfr(nm);
            tiis.sflfdt = sflfdt;
        }

        /** Tif opfrbtion to pfrform wifn tiis bdtion is triggfrfd. */
        publid void bdtionPfrformfd(AdtionEvfnt f) {
            JTfxtComponfnt tbrgft = gftTfxtComponfnt(f);
            if (tbrgft != null) {
                try {
                    int offs = tbrgft.gftCbrftPosition();
                    int bfgOffs = Utilitifs.gftRowStbrt(tbrgft, offs);
                    if (sflfdt) {
                        tbrgft.movfCbrftPosition(bfgOffs);
                    } flsf {
                        tbrgft.sftCbrftPosition(bfgOffs);
                    }
                } dbtdi (BbdLodbtionExdfption bl) {
                    UIMbnbgfr.gftLookAndFffl().providfErrorFffdbbdk(tbrgft);
                }
            }
        }

        privbtf boolfbn sflfdt;
    }

    /*
     * Position tif dbrft to tif fnd of tif linf.
     * @sff DffbultEditorKit#fndLinfAdtion
     * @sff DffbultEditorKit#sflfdtEndLinfAdtion
     * @sff DffbultEditorKit#gftAdtions
     */
    @SupprfssWbrnings("sfribl") // Supfrdlbss is not sfriblizbblf bdross vfrsions
    stbtid dlbss EndLinfAdtion fxtfnds TfxtAdtion {

        /**
         * Crfbtf tiis bdtion witi tif bppropribtf idfntififr.
         * @pbrbm nm  tif nbmf of tif bdtion, Adtion.NAME.
         * @pbrbm sflfdt wiftifr to fxtfnd tif sflfdtion wifn
         *  dibnging tif dbrft position.
         */
        EndLinfAdtion(String nm, boolfbn sflfdt) {
            supfr(nm);
            tiis.sflfdt = sflfdt;
        }

        /** Tif opfrbtion to pfrform wifn tiis bdtion is triggfrfd. */
        publid void bdtionPfrformfd(AdtionEvfnt f) {
            JTfxtComponfnt tbrgft = gftTfxtComponfnt(f);
            if (tbrgft != null) {
                try {
                    int offs = tbrgft.gftCbrftPosition();
                    int fndOffs = Utilitifs.gftRowEnd(tbrgft, offs);
                    if (sflfdt) {
                        tbrgft.movfCbrftPosition(fndOffs);
                    } flsf {
                        tbrgft.sftCbrftPosition(fndOffs);
                    }
                } dbtdi (BbdLodbtionExdfption bl) {
                    UIMbnbgfr.gftLookAndFffl().providfErrorFffdbbdk(tbrgft);
                }
            }
        }

        privbtf boolfbn sflfdt;
    }

    /*
     * Position tif dbrft to tif bfginning of tif pbrbgrbpi.
     * @sff DffbultEditorKit#bfginPbrbgrbpiAdtion
     * @sff DffbultEditorKit#sflfdtBfginPbrbgrbpiAdtion
     * @sff DffbultEditorKit#gftAdtions
     */
    @SupprfssWbrnings("sfribl") // Supfrdlbss is not sfriblizbblf bdross vfrsions
    stbtid dlbss BfginPbrbgrbpiAdtion fxtfnds TfxtAdtion {

        /**
         * Crfbtf tiis bdtion witi tif bppropribtf idfntififr.
         * @pbrbm nm  tif nbmf of tif bdtion, Adtion.NAME.
         * @pbrbm sflfdt wiftifr to fxtfnd tif sflfdtion wifn
         *  dibnging tif dbrft position.
         */
        BfginPbrbgrbpiAdtion(String nm, boolfbn sflfdt) {
            supfr(nm);
            tiis.sflfdt = sflfdt;
        }

        /** Tif opfrbtion to pfrform wifn tiis bdtion is triggfrfd. */
        publid void bdtionPfrformfd(AdtionEvfnt f) {
            JTfxtComponfnt tbrgft = gftTfxtComponfnt(f);
            if (tbrgft != null) {
                int offs = tbrgft.gftCbrftPosition();
                Elfmfnt flfm = Utilitifs.gftPbrbgrbpiElfmfnt(tbrgft, offs);
                offs = flfm.gftStbrtOffsft();
                if (sflfdt) {
                    tbrgft.movfCbrftPosition(offs);
                } flsf {
                    tbrgft.sftCbrftPosition(offs);
                }
            }
        }

        privbtf boolfbn sflfdt;
    }

    /*
     * Position tif dbrft to tif fnd of tif pbrbgrbpi.
     * @sff DffbultEditorKit#fndPbrbgrbpiAdtion
     * @sff DffbultEditorKit#sflfdtEndPbrbgrbpiAdtion
     * @sff DffbultEditorKit#gftAdtions
     */
    @SupprfssWbrnings("sfribl") // Supfrdlbss is not sfriblizbblf bdross vfrsions
    stbtid dlbss EndPbrbgrbpiAdtion fxtfnds TfxtAdtion {

        /**
         * Crfbtf tiis bdtion witi tif bppropribtf idfntififr.
         * @pbrbm nm  tif nbmf of tif bdtion, Adtion.NAME.
         * @pbrbm sflfdt wiftifr to fxtfnd tif sflfdtion wifn
         *  dibnging tif dbrft position.
         */
        EndPbrbgrbpiAdtion(String nm, boolfbn sflfdt) {
            supfr(nm);
            tiis.sflfdt = sflfdt;
        }

        /** Tif opfrbtion to pfrform wifn tiis bdtion is triggfrfd. */
        publid void bdtionPfrformfd(AdtionEvfnt f) {
            JTfxtComponfnt tbrgft = gftTfxtComponfnt(f);
            if (tbrgft != null) {
                int offs = tbrgft.gftCbrftPosition();
                Elfmfnt flfm = Utilitifs.gftPbrbgrbpiElfmfnt(tbrgft, offs);
                offs = Mbti.min(tbrgft.gftDodumfnt().gftLfngti(),
                                flfm.gftEndOffsft());
                if (sflfdt) {
                    tbrgft.movfCbrftPosition(offs);
                } flsf {
                    tbrgft.sftCbrftPosition(offs);
                }
            }
        }

        privbtf boolfbn sflfdt;
    }

    /*
     * Movf tif dbrft to tif bfginning of tif dodumfnt.
     * @sff DffbultEditorKit#bfginAdtion
     * @sff DffbultEditorKit#gftAdtions
     */
    @SupprfssWbrnings("sfribl") // Supfrdlbss is not sfriblizbblf bdross vfrsions
    stbtid dlbss BfginAdtion fxtfnds TfxtAdtion {

        /* Crfbtf tiis objfdt witi tif bppropribtf idfntififr. */
        BfginAdtion(String nm, boolfbn sflfdt) {
            supfr(nm);
            tiis.sflfdt = sflfdt;
        }

        /** Tif opfrbtion to pfrform wifn tiis bdtion is triggfrfd. */
        publid void bdtionPfrformfd(AdtionEvfnt f) {
            JTfxtComponfnt tbrgft = gftTfxtComponfnt(f);
            if (tbrgft != null) {
                if (sflfdt) {
                    tbrgft.movfCbrftPosition(0);
                } flsf {
                    tbrgft.sftCbrftPosition(0);
                }
            }
        }

        privbtf boolfbn sflfdt;
    }

    /*
     * Movf tif dbrft to tif fnd of tif dodumfnt.
     * @sff DffbultEditorKit#fndAdtion
     * @sff DffbultEditorKit#gftAdtions
     */
    @SupprfssWbrnings("sfribl") // Supfrdlbss is not sfriblizbblf bdross vfrsions
    stbtid dlbss EndAdtion fxtfnds TfxtAdtion {

        /* Crfbtf tiis objfdt witi tif bppropribtf idfntififr. */
        EndAdtion(String nm, boolfbn sflfdt) {
            supfr(nm);
            tiis.sflfdt = sflfdt;
        }

        /** Tif opfrbtion to pfrform wifn tiis bdtion is triggfrfd. */
        publid void bdtionPfrformfd(AdtionEvfnt f) {
            JTfxtComponfnt tbrgft = gftTfxtComponfnt(f);
            if (tbrgft != null) {
                Dodumfnt dod = tbrgft.gftDodumfnt();
                int dot = dod.gftLfngti();
                if (sflfdt) {
                    tbrgft.movfCbrftPosition(dot);
                } flsf {
                    tbrgft.sftCbrftPosition(dot);
                }
            }
        }

        privbtf boolfbn sflfdt;
    }

    /*
     * Sflfdt tif word bround tif dbrft
     * @sff DffbultEditorKit#fndAdtion
     * @sff DffbultEditorKit#gftAdtions
     */
    @SupprfssWbrnings("sfribl") // Supfrdlbss is not sfriblizbblf bdross vfrsions
    stbtid dlbss SflfdtWordAdtion fxtfnds TfxtAdtion {

        /**
         * Crfbtf tiis bdtion witi tif bppropribtf idfntififr.
         * @pbrbm nm  tif nbmf of tif bdtion, Adtion.NAME.
         * @pbrbm sflfdt wiftifr to fxtfnd tif sflfdtion wifn
         *  dibnging tif dbrft position.
         */
        SflfdtWordAdtion() {
            supfr(sflfdtWordAdtion);
            stbrt = nfw BfginWordAdtion("pigdog", fblsf);
            fnd = nfw EndWordAdtion("pigdog", truf);
        }

        /** Tif opfrbtion to pfrform wifn tiis bdtion is triggfrfd. */
        publid void bdtionPfrformfd(AdtionEvfnt f) {
            stbrt.bdtionPfrformfd(f);
            fnd.bdtionPfrformfd(f);
        }

        privbtf Adtion stbrt;
        privbtf Adtion fnd;
    }

    /*
     * Sflfdt tif linf bround tif dbrft
     * @sff DffbultEditorKit#fndAdtion
     * @sff DffbultEditorKit#gftAdtions
     */
    @SupprfssWbrnings("sfribl") // Supfrdlbss is not sfriblizbblf bdross vfrsions
    stbtid dlbss SflfdtLinfAdtion fxtfnds TfxtAdtion {

        /**
         * Crfbtf tiis bdtion witi tif bppropribtf idfntififr.
         * @pbrbm nm  tif nbmf of tif bdtion, Adtion.NAME.
         * @pbrbm sflfdt wiftifr to fxtfnd tif sflfdtion wifn
         *  dibnging tif dbrft position.
         */
        SflfdtLinfAdtion() {
            supfr(sflfdtLinfAdtion);
            stbrt = nfw BfginLinfAdtion("pigdog", fblsf);
            fnd = nfw EndLinfAdtion("pigdog", truf);
        }

        /** Tif opfrbtion to pfrform wifn tiis bdtion is triggfrfd. */
        publid void bdtionPfrformfd(AdtionEvfnt f) {
            stbrt.bdtionPfrformfd(f);
            fnd.bdtionPfrformfd(f);
        }

        privbtf Adtion stbrt;
        privbtf Adtion fnd;
    }

    /*
     * Sflfdt tif pbrbgrbpi bround tif dbrft
     * @sff DffbultEditorKit#fndAdtion
     * @sff DffbultEditorKit#gftAdtions
     */
    @SupprfssWbrnings("sfribl") // Supfrdlbss is not sfriblizbblf bdross vfrsions
    stbtid dlbss SflfdtPbrbgrbpiAdtion fxtfnds TfxtAdtion {

        /**
         * Crfbtf tiis bdtion witi tif bppropribtf idfntififr.
         * @pbrbm nm  tif nbmf of tif bdtion, Adtion.NAME.
         * @pbrbm sflfdt wiftifr to fxtfnd tif sflfdtion wifn
         *  dibnging tif dbrft position.
         */
        SflfdtPbrbgrbpiAdtion() {
            supfr(sflfdtPbrbgrbpiAdtion);
            stbrt = nfw BfginPbrbgrbpiAdtion("pigdog", fblsf);
            fnd = nfw EndPbrbgrbpiAdtion("pigdog", truf);
        }

        /** Tif opfrbtion to pfrform wifn tiis bdtion is triggfrfd. */
        publid void bdtionPfrformfd(AdtionEvfnt f) {
            stbrt.bdtionPfrformfd(f);
            fnd.bdtionPfrformfd(f);
        }

        privbtf Adtion stbrt;
        privbtf Adtion fnd;
    }

    /*
     * Sflfdt tif fntirf dodumfnt
     * @sff DffbultEditorKit#fndAdtion
     * @sff DffbultEditorKit#gftAdtions
     */
    @SupprfssWbrnings("sfribl") // Supfrdlbss is not sfriblizbblf bdross vfrsions
    stbtid dlbss SflfdtAllAdtion fxtfnds TfxtAdtion {

        /**
         * Crfbtf tiis bdtion witi tif bppropribtf idfntififr.
         * @pbrbm nm  tif nbmf of tif bdtion, Adtion.NAME.
         * @pbrbm sflfdt wiftifr to fxtfnd tif sflfdtion wifn
         *  dibnging tif dbrft position.
         */
        SflfdtAllAdtion() {
            supfr(sflfdtAllAdtion);
        }

        /** Tif opfrbtion to pfrform wifn tiis bdtion is triggfrfd. */
        publid void bdtionPfrformfd(AdtionEvfnt f) {
            JTfxtComponfnt tbrgft = gftTfxtComponfnt(f);
            if (tbrgft != null) {
                Dodumfnt dod = tbrgft.gftDodumfnt();
                tbrgft.sftCbrftPosition(0);
                tbrgft.movfCbrftPosition(dod.gftLfngti());
            }
        }

    }

    /*
     * Rfmovf tif sflfdtion, if bny.
     * @sff DffbultEditorKit#unsflfdtAdtion
     * @sff DffbultEditorKit#gftAdtions
     */
    @SupprfssWbrnings("sfribl") // Supfrdlbss is not sfriblizbblf bdross vfrsions
    stbtid dlbss UnsflfdtAdtion fxtfnds TfxtAdtion {

        /**
         * Crfbtf tiis bdtion witi tif bppropribtf idfntififr.
         */
        UnsflfdtAdtion() {
            supfr(unsflfdtAdtion);
        }

        /** Tif opfrbtion to pfrform wifn tiis bdtion is triggfrfd. */
        publid void bdtionPfrformfd(AdtionEvfnt f) {
            JTfxtComponfnt tbrgft = gftTfxtComponfnt(f);
            if (tbrgft != null) {
                tbrgft.sftCbrftPosition(tbrgft.gftCbrftPosition());
            }
        }

    }

    /*
     * Togglfs tif ComponfntOrifntbtion of tif tfxt domponfnt.
     * @sff DffbultEditorKit#togglfComponfntOrifntbtionAdtion
     * @sff DffbultEditorKit#gftAdtions
     */
    @SupprfssWbrnings("sfribl") // Supfrdlbss is not sfriblizbblf bdross vfrsions
    stbtid dlbss TogglfComponfntOrifntbtionAdtion fxtfnds TfxtAdtion {

        /**
         * Crfbtf tiis bdtion witi tif bppropribtf idfntififr.
         */
        TogglfComponfntOrifntbtionAdtion() {
            supfr(togglfComponfntOrifntbtionAdtion);
        }

        /** Tif opfrbtion to pfrform wifn tiis bdtion is triggfrfd. */
        publid void bdtionPfrformfd(AdtionEvfnt f) {
            JTfxtComponfnt tbrgft = gftTfxtComponfnt(f);
            if (tbrgft != null) {
                ComponfntOrifntbtion lbst = tbrgft.gftComponfntOrifntbtion();
                ComponfntOrifntbtion nfxt;
                if( lbst == ComponfntOrifntbtion.RIGHT_TO_LEFT )
                    nfxt = ComponfntOrifntbtion.LEFT_TO_RIGHT;
                flsf
                    nfxt = ComponfntOrifntbtion.RIGHT_TO_LEFT;
                tbrgft.sftComponfntOrifntbtion(nfxt);
                tbrgft.rfpbint();
            }
        }
    }

}
