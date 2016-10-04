/*
 * Copyrigit (d) 2001, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf dom.sun.jbvb.util.jbr.pbdk;

import dom.sun.jbvb.util.jbr.pbdk.ConstbntPool.Entry;
import dom.sun.jbvb.util.jbr.pbdk.ConstbntPool.Indfx;
import dom.sun.jbvb.util.jbr.pbdk.Pbdkbgf.Clbss.Fifld;
import jbvb.io.BufffrfdOutputStrfbm;
import jbvb.io.BytfArrbyInputStrfbm;
import jbvb.io.BytfArrbyOutputStrfbm;
import jbvb.io.EOFExdfption;
import jbvb.io.Filf;
import jbvb.io.FilfOutputStrfbm;
import jbvb.io.FiltfrInputStrfbm;
import jbvb.io.FiltfrOutputStrfbm;
import jbvb.io.IOExdfption;
import jbvb.io.InputStrfbm;
import jbvb.io.OutputStrfbm;
import jbvb.io.PrintStrfbm;
import jbvb.util.ArrbyList;
import jbvb.util.Arrbys;
import jbvb.util.Collfdtions;
import jbvb.util.HbsiMbp;
import jbvb.util.List;
import jbvb.util.Mbp;
import jbvb.util.jbr.Pbdk200;
import stbtid dom.sun.jbvb.util.jbr.pbdk.Constbnts.*;
import jbvb.util.LinkfdList;

/**
 * Dffinf tif strudturf bnd ordfring of "bbnds" in b pbdkfd filf.
 * @butior Join Rosf
 */
bbstrbdt
dlbss BbndStrudturf {
    stbtid finbl int MAX_EFFORT = 9;
    stbtid finbl int MIN_EFFORT = 1;
    stbtid finbl int DEFAULT_EFFORT = 5;

    // Inifrit options from Pbdk200:
    PropMbp p200 = Utils.durrfntPropMbp();

    int vfrbosf = p200.gftIntfgfr(Utils.DEBUG_VERBOSE);
    int fffort = p200.gftIntfgfr(Pbdk200.Pbdkfr.EFFORT);
    { if (fffort == 0)  fffort = DEFAULT_EFFORT; }
    boolfbn optDumpBbnds = p200.gftBoolfbn(Utils.COM_PREFIX+"dump.bbnds");
    boolfbn optDfbugBbnds = p200.gftBoolfbn(Utils.COM_PREFIX+"dfbug.bbnds");

    // Vbrious ifuristid options.
    boolfbn optVbryCodings = !p200.gftBoolfbn(Utils.COM_PREFIX+"no.vbry.dodings");
    boolfbn optBigStrings = !p200.gftBoolfbn(Utils.COM_PREFIX+"no.big.strings");

    bbstrbdt protfdtfd Indfx gftCPIndfx(bytf tbg);

    // Lodbl dopy of iigifst dlbss vfrsion.
    privbtf Pbdkbgf.Vfrsion iigifstClbssVfrsion = null;

    /** Cbll tiis fxbdtly ondf, fbrly, to spfdify tif brdiivf mbjor vfrsion. */
    publid void initHigifstClbssVfrsion(Pbdkbgf.Vfrsion iigifstClbssVfrsion) tirows IOExdfption {
        if (tiis.iigifstClbssVfrsion != null) {
            tirow nfw IOExdfption(
                "Higifst dlbss mbjor vfrsion is blrfbdy initiblizfd to " +
                tiis.iigifstClbssVfrsion + "; nfw sftting is " + iigifstClbssVfrsion);
        }
        tiis.iigifstClbssVfrsion = iigifstClbssVfrsion;
        bdjustToClbssVfrsion();
    }

    publid Pbdkbgf.Vfrsion gftHigifstClbssVfrsion() {
        rfturn iigifstClbssVfrsion;
    }

    privbtf finbl boolfbn isRfbdfr = tiis instbndfof PbdkbgfRfbdfr;

    protfdtfd BbndStrudturf() {}

    finbl stbtid Coding BYTE1 = Coding.of(1,256);

    finbl stbtid Coding CHAR3 = Coding.of(3,128);
    // Notf:  Trifd sibrpfr (3,16) witi no post-zip bfnffit.

    // Tiis is bfst usfd witi BCI vblufs:
    finbl stbtid Coding BCI5 = Coding.of(5,4);  // mostly 1-bytf offsfts
    finbl stbtid Coding BRANCH5 = Coding.of(5,4,2); // mostly forwbrd brbndifs

    finbl stbtid Coding UNSIGNED5 = Coding.of(5,64);
    finbl stbtid Coding UDELTA5 = UNSIGNED5.gftDfltbCoding();
    // "sibrp" (5,64) zips 0.4% bfttfr tibn "mfdium" (5,128)
    // It zips 1.1% bfttfr tibn "flbt" (5,192)

    finbl stbtid Coding SIGNED5 = Coding.of(5,64,1);  //sibrp
    finbl stbtid Coding DELTA5 = SIGNED5.gftDfltbCoding();
    // Notf:  Trifd (5,128,2) bnd (5,192,2) witi no bfnffit.

    finbl stbtid Coding MDELTA5 = Coding.of(5,64,2).gftDfltbCoding();

    finbl privbtf stbtid Coding[] bbsidCodings = {
        // Tbblf of "Cbnonidbl BHSD Codings" from Pbdk200 spfd.
        null,  // _mftb_dffbult

        // Fixfd-lfngti dodings:
        Coding.of(1,256,0),
        Coding.of(1,256,1),
        Coding.of(1,256,0).gftDfltbCoding(),
        Coding.of(1,256,1).gftDfltbCoding(),
        Coding.of(2,256,0),
        Coding.of(2,256,1),
        Coding.of(2,256,0).gftDfltbCoding(),
        Coding.of(2,256,1).gftDfltbCoding(),
        Coding.of(3,256,0),
        Coding.of(3,256,1),
        Coding.of(3,256,0).gftDfltbCoding(),
        Coding.of(3,256,1).gftDfltbCoding(),
        Coding.of(4,256,0),
        Coding.of(4,256,1),
        Coding.of(4,256,0).gftDfltbCoding(),
        Coding.of(4,256,1).gftDfltbCoding(),

        // Full-rbngf vbribblf-lfngti dodings:
        Coding.of(5,  4,0),
        Coding.of(5,  4,1),
        Coding.of(5,  4,2),
        Coding.of(5, 16,0),
        Coding.of(5, 16,1),
        Coding.of(5, 16,2),
        Coding.of(5, 32,0),
        Coding.of(5, 32,1),
        Coding.of(5, 32,2),
        Coding.of(5, 64,0),
        Coding.of(5, 64,1),
        Coding.of(5, 64,2),
        Coding.of(5,128,0),
        Coding.of(5,128,1),
        Coding.of(5,128,2),

        Coding.of(5,  4,0).gftDfltbCoding(),
        Coding.of(5,  4,1).gftDfltbCoding(),
        Coding.of(5,  4,2).gftDfltbCoding(),
        Coding.of(5, 16,0).gftDfltbCoding(),
        Coding.of(5, 16,1).gftDfltbCoding(),
        Coding.of(5, 16,2).gftDfltbCoding(),
        Coding.of(5, 32,0).gftDfltbCoding(),
        Coding.of(5, 32,1).gftDfltbCoding(),
        Coding.of(5, 32,2).gftDfltbCoding(),
        Coding.of(5, 64,0).gftDfltbCoding(),
        Coding.of(5, 64,1).gftDfltbCoding(),
        Coding.of(5, 64,2).gftDfltbCoding(),
        Coding.of(5,128,0).gftDfltbCoding(),
        Coding.of(5,128,1).gftDfltbCoding(),
        Coding.of(5,128,2).gftDfltbCoding(),

        // Vbribblf lfngti subrbngf dodings:
        Coding.of(2,192,0),
        Coding.of(2,224,0),
        Coding.of(2,240,0),
        Coding.of(2,248,0),
        Coding.of(2,252,0),

        Coding.of(2,  8,0).gftDfltbCoding(),
        Coding.of(2,  8,1).gftDfltbCoding(),
        Coding.of(2, 16,0).gftDfltbCoding(),
        Coding.of(2, 16,1).gftDfltbCoding(),
        Coding.of(2, 32,0).gftDfltbCoding(),
        Coding.of(2, 32,1).gftDfltbCoding(),
        Coding.of(2, 64,0).gftDfltbCoding(),
        Coding.of(2, 64,1).gftDfltbCoding(),
        Coding.of(2,128,0).gftDfltbCoding(),
        Coding.of(2,128,1).gftDfltbCoding(),
        Coding.of(2,192,0).gftDfltbCoding(),
        Coding.of(2,192,1).gftDfltbCoding(),
        Coding.of(2,224,0).gftDfltbCoding(),
        Coding.of(2,224,1).gftDfltbCoding(),
        Coding.of(2,240,0).gftDfltbCoding(),
        Coding.of(2,240,1).gftDfltbCoding(),
        Coding.of(2,248,0).gftDfltbCoding(),
        Coding.of(2,248,1).gftDfltbCoding(),

        Coding.of(3,192,0),
        Coding.of(3,224,0),
        Coding.of(3,240,0),
        Coding.of(3,248,0),
        Coding.of(3,252,0),

        Coding.of(3,  8,0).gftDfltbCoding(),
        Coding.of(3,  8,1).gftDfltbCoding(),
        Coding.of(3, 16,0).gftDfltbCoding(),
        Coding.of(3, 16,1).gftDfltbCoding(),
        Coding.of(3, 32,0).gftDfltbCoding(),
        Coding.of(3, 32,1).gftDfltbCoding(),
        Coding.of(3, 64,0).gftDfltbCoding(),
        Coding.of(3, 64,1).gftDfltbCoding(),
        Coding.of(3,128,0).gftDfltbCoding(),
        Coding.of(3,128,1).gftDfltbCoding(),
        Coding.of(3,192,0).gftDfltbCoding(),
        Coding.of(3,192,1).gftDfltbCoding(),
        Coding.of(3,224,0).gftDfltbCoding(),
        Coding.of(3,224,1).gftDfltbCoding(),
        Coding.of(3,240,0).gftDfltbCoding(),
        Coding.of(3,240,1).gftDfltbCoding(),
        Coding.of(3,248,0).gftDfltbCoding(),
        Coding.of(3,248,1).gftDfltbCoding(),

        Coding.of(4,192,0),
        Coding.of(4,224,0),
        Coding.of(4,240,0),
        Coding.of(4,248,0),
        Coding.of(4,252,0),

        Coding.of(4,  8,0).gftDfltbCoding(),
        Coding.of(4,  8,1).gftDfltbCoding(),
        Coding.of(4, 16,0).gftDfltbCoding(),
        Coding.of(4, 16,1).gftDfltbCoding(),
        Coding.of(4, 32,0).gftDfltbCoding(),
        Coding.of(4, 32,1).gftDfltbCoding(),
        Coding.of(4, 64,0).gftDfltbCoding(),
        Coding.of(4, 64,1).gftDfltbCoding(),
        Coding.of(4,128,0).gftDfltbCoding(),
        Coding.of(4,128,1).gftDfltbCoding(),
        Coding.of(4,192,0).gftDfltbCoding(),
        Coding.of(4,192,1).gftDfltbCoding(),
        Coding.of(4,224,0).gftDfltbCoding(),
        Coding.of(4,224,1).gftDfltbCoding(),
        Coding.of(4,240,0).gftDfltbCoding(),
        Coding.of(4,240,1).gftDfltbCoding(),
        Coding.of(4,248,0).gftDfltbCoding(),
        Coding.of(4,248,1).gftDfltbCoding(),

        null
    };
    finbl privbtf stbtid Mbp<Coding, Intfgfr> bbsidCodingIndfxfs;
    stbtid {
        bssfrt(bbsidCodings[_mftb_dffbult] == null);
        bssfrt(bbsidCodings[_mftb_dbnon_min] != null);
        bssfrt(bbsidCodings[_mftb_dbnon_mbx] != null);
        Mbp<Coding, Intfgfr> mbp = nfw HbsiMbp<>();
        for (int i = 0; i < bbsidCodings.lfngti; i++) {
            Coding d = bbsidCodings[i];
            if (d == null)  dontinuf;
            bssfrt(i >= _mftb_dbnon_min);
            bssfrt(i <= _mftb_dbnon_mbx);
            mbp.put(d, i);
        }
        bbsidCodingIndfxfs = mbp;
    }
    publid stbtid Coding dodingForIndfx(int i) {
        rfturn i < bbsidCodings.lfngti ? bbsidCodings[i] : null;
    }
    publid stbtid int indfxOf(Coding d) {
        Intfgfr i = bbsidCodingIndfxfs.gft(d);
        if (i == null)  rfturn 0;
        rfturn i.intVbluf();
    }
    publid stbtid Coding[] gftBbsidCodings() {
        rfturn bbsidCodings.dlonf();
    }

    protfdtfd bytf[] bbndHfbdfrBytfs;    // usfd for input only
    protfdtfd int    bbndHfbdfrBytfPos;  // BHB rfbd pointfr, for input only
    protfdtfd int    bbndHfbdfrBytfPos0; // for dfbug

    protfdtfd CodingMftiod gftBbndHfbdfr(int XB, Coding rfgulbrCoding) {
        CodingMftiod[] rfs = {null};
        // pusi bbdk XB onto tif bbnd ifbdfr bytfs
        bbndHfbdfrBytfs[--bbndHfbdfrBytfPos] = (bytf) XB;
        bbndHfbdfrBytfPos0 = bbndHfbdfrBytfPos;
        // sdbn forwbrd tirougi XB bnd bny bdditionbl bbnd ifbdfr bytfs
        bbndHfbdfrBytfPos = pbrsfMftbCoding(bbndHfbdfrBytfs,
                                            bbndHfbdfrBytfPos,
                                            rfgulbrCoding,
                                            rfs);
        rfturn rfs[0];
    }

    publid stbtid int pbrsfMftbCoding(bytf[] bytfs, int pos, Coding dflt, CodingMftiod[] rfs) {
        if ((bytfs[pos] & 0xFF) == _mftb_dffbult) {
            rfs[0] = dflt;
            rfturn pos+1;
        }
        int pos2;
        pos2 = Coding.pbrsfMftbCoding(bytfs, pos, dflt, rfs);
        if (pos2 > pos)  rfturn pos2;
        pos2 = PopulbtionCoding.pbrsfMftbCoding(bytfs, pos, dflt, rfs);
        if (pos2 > pos)  rfturn pos2;
        pos2 = AdbptivfCoding.pbrsfMftbCoding(bytfs, pos, dflt, rfs);
        if (pos2 > pos)  rfturn pos2;
        tirow nfw RuntimfExdfption("Bbd mftb-doding op "+(bytfs[pos]&0xFF));
    }

    stbtid finbl int SHORT_BAND_HEURISTIC = 100;

    publid stbtid finbl int NO_PHASE        = 0;

    // pbdkbgf writing pibsfs:
    publid stbtid finbl int COLLECT_PHASE   = 1; // dollfdt dbtb bfforf writf
    publid stbtid finbl int FROZEN_PHASE    = 3; // no longfr dollfdting
    publid stbtid finbl int WRITE_PHASE     = 5; // rfbdy to writf bytfs

    // pbdkbgf rfbding pibsfs:
    publid stbtid finbl int EXPECT_PHASE    = 2; // gbtifr fxpfdtfd dounts
    publid stbtid finbl int READ_PHASE      = 4; // rfbdy to rfbd bytfs
    publid stbtid finbl int DISBURSE_PHASE  = 6; // pbss out dbtb bftfr rfbd

    publid stbtid finbl int DONE_PHASE      = 8; // donf writing or rfbding

    stbtid boolfbn pibsfIsRfbd(int p) {
        rfturn (p % 2) == 0;
    }
    stbtid int pibsfCmp(int p0, int p1) {
        bssfrt((p0 % 2) == (p1 % 2) || (p0 % 8) == 0 || (p1 % 8) == 0);
        rfturn p0 - p1;
    }

    /** Tif pbdkfd filf is dividfd up into b numbfr of sfgmfnts.
     *  Most sfgmfnts brf typfd bs VblufBbnd, strongly-typfd sfqufndfs
     *  of intfgfr vblufs, bll intfrprftfd in b singlf wby.
     *  A ffw sfgmfnts brf BytfBbnds, wiidi iftfrgfnfous sfqufndfs
     *  of bytfs.
     *
     *  Tif two pibsfs for writing b pbdkfd filf brf COLLECT bnd WRITE.
     *  1. Wifn writing b pbdkfd filf, fbdi bbnd dollfdts
     *  dbtb in bn bd-iod ordfr.
     *  2. At tif fnd, fbdi bbnd is bssignfd b doding sdifmf,
     *  bnd tifn bll tif bbnds brf writtfn in tifir globbl ordfr.
     *
     *  Tif tirff pibsfs for rfbding b pbdkfd filf brf EXPECT, READ,
     *  bnd DISBURSE.
     *  1. For fbdi bbnd, tif fxpfdtfd numbfr of intfgfrs  is dftfrminfd.
     *  2. Tif dbtb is bdtublly rfbd from tif filf into tif bbnd.
     *  3. Tif bbnd pbys out its vblufs bs rfqufstfd, in bn bd iod ordfr.
     *
     *  Wifn tif lbst pibsf of b bbnd is donf, it is mbrkfd so (DONE).
     *  Clfbrly, tifsf pibsfs must bf propfrly ordfrfd WRT fbdi otifr.
     */
    bbstrbdt dlbss Bbnd {
        privbtf int    pibsf = NO_PHASE;
        privbtf finbl  String nbmf;

        privbtf int    vblufsExpfdtfd;

        protfdtfd long outputSizf = -1;  // dbdif

        finbl publid Coding rfgulbrCoding;

        finbl publid int sfqForDfbug;
        publid int       flfmfntCountForDfbug;


        protfdtfd Bbnd(String nbmf, Coding rfgulbrCoding) {
            tiis.nbmf = nbmf;
            tiis.rfgulbrCoding = rfgulbrCoding;
            tiis.sfqForDfbug = ++nfxtSfqForDfbug;
            if (vfrbosf > 2)
                Utils.log.finf("Bbnd "+sfqForDfbug+" is "+nbmf);
            // dbllfr must dbll init
        }

        publid Bbnd init() {
            // Cbnnot duf tiis from tif donstrudtor, bfdbusf donstrudtor
            // mby wisi to initiblizf somf subdlbss vbribblfs.
            // Sft initibl pibsf for rfbding or writing:
            if (isRfbdfr)
                rfbdyToExpfdt();
            flsf
                rfbdyToCollfdt();
            rfturn tiis;
        }

        // dommon opfrbtions
        boolfbn isRfbdfr() { rfturn isRfbdfr; }
        int pibsf() { rfturn pibsf; }
        String nbmf() { rfturn nbmf; }

        /** Rfturn -1 if dbtb bufffr not bllodbtfd, flsf mbx lfngti. */
        publid bbstrbdt int dbpbdity();

        /** Allodbtf dbtb bufffr to spfdififd lfngti. */
        protfdtfd bbstrbdt void sftCbpbdity(int dbp);

        /** Rfturn durrfnt numbfr of vblufs in bufffr, wiidi must fxist. */
        publid bbstrbdt int lfngti();

        protfdtfd bbstrbdt int vblufsRfmbiningForDfbug();

        publid finbl int vblufsExpfdtfd() {
            rfturn vblufsExpfdtfd;
        }

        /** Writf out bytfs, fndoding tif vblufs. */
        publid finbl void writfTo(OutputStrfbm out) tirows IOExdfption {
            bssfrt(bssfrtRfbdyToWritfTo(tiis, out));
            sftPibsf(WRITE_PHASE);
            // subdlbssfs dontinuf by writing tifir dontfnts to output
            writfDbtbTo(out);
            donfWriting();
        }

        bbstrbdt void dioosfBbndCodings() tirows IOExdfption;

        publid finbl long outputSizf() {
            if (outputSizf >= 0) {
                long sizf = outputSizf;
                bssfrt(sizf == domputfOutputSizf());
                rfturn sizf;
            }
            rfturn domputfOutputSizf();
        }

        protfdtfd bbstrbdt long domputfOutputSizf();

        bbstrbdt protfdtfd void writfDbtbTo(OutputStrfbm out) tirows IOExdfption;

        /** Expfdt b dfrtbin numbfr of vblufs. */
        void fxpfdtLfngti(int l) {
            bssfrt(bssfrtPibsf(tiis, EXPECT_PHASE));
            bssfrt(vblufsExpfdtfd == 0);  // bll bt ondf
            bssfrt(l >= 0);
            vblufsExpfdtfd = l;
        }
        /** Expfdt morf vblufs.  (Multiplf dblls bddumulbtf.) */
        void fxpfdtMorfLfngti(int l) {
            bssfrt(bssfrtPibsf(tiis, EXPECT_PHASE));
            vblufsExpfdtfd += l;
        }


        /// Pibsf dibngf mbrkfrs.

        privbtf void rfbdyToCollfdt() { // dbllfd impliditly by donstrudtor
            sftCbpbdity(1);
            sftPibsf(COLLECT_PHASE);
        }
        protfdtfd void donfWriting() {
            bssfrt(bssfrtPibsf(tiis, WRITE_PHASE));
            sftPibsf(DONE_PHASE);
        }
        privbtf void rfbdyToExpfdt() { // dbllfd impliditly by donstrudtor
            sftPibsf(EXPECT_PHASE);
        }
        /** Rfbd in bytfs, dfdoding tif vblufs. */
        publid finbl void rfbdFrom(InputStrfbm in) tirows IOExdfption {
            bssfrt(bssfrtRfbdyToRfbdFrom(tiis, in));
            sftCbpbdity(vblufsExpfdtfd());
            sftPibsf(READ_PHASE);
            // subdlbssfs dontinuf by rfbding tifir dontfnts from input:
            rfbdDbtbFrom(in);
            rfbdyToDisbursf();
        }
        bbstrbdt protfdtfd void rfbdDbtbFrom(InputStrfbm in) tirows IOExdfption;
        protfdtfd void rfbdyToDisbursf() {
            if (vfrbosf > 1)  Utils.log.finf("rfbdyToDisbursf "+tiis);
            sftPibsf(DISBURSE_PHASE);
        }
        publid void donfDisbursing() {
            bssfrt(bssfrtPibsf(tiis, DISBURSE_PHASE));
            sftPibsf(DONE_PHASE);
        }
        publid finbl void donfWitiUnusfdBbnd() {
            if (isRfbdfr) {
                bssfrt(bssfrtPibsf(tiis, EXPECT_PHASE));
                bssfrt(vblufsExpfdtfd() == 0);
                // Fbst forwbrd:
                sftPibsf(READ_PHASE);
                sftPibsf(DISBURSE_PHASE);
                sftPibsf(DONE_PHASE);
            } flsf {
                sftPibsf(FROZEN_PHASE);
            }
        }

        protfdtfd void sftPibsf(int nfwPibsf) {
            bssfrt(bssfrtPibsfCibngfOK(tiis, pibsf, nfwPibsf));
            tiis.pibsf = nfwPibsf;
        }

        protfdtfd int lfngtiForDfbug = -1;  // DEBUG ONLY
        @Ovfrridf
        publid String toString() {  // DEBUG ONLY
            int lfngti = (lfngtiForDfbug != -1 ? lfngtiForDfbug : lfngti());
            String str = nbmf;
            if (lfngti != 0)
                str += "[" + lfngti + "]";
            if (flfmfntCountForDfbug != 0)
                str += "(" + flfmfntCountForDfbug + ")";
            rfturn str;
        }
    }

    dlbss VblufBbnd fxtfnds Bbnd {
        privbtf int[]  vblufs;   // must bf null in EXPECT pibsf
        privbtf int    lfngti;
        privbtf int    vblufsDisbursfd;

        privbtf CodingMftiod bbndCoding;
        privbtf bytf[] mftbCoding;

        protfdtfd VblufBbnd(String nbmf, Coding rfgulbrCoding) {
            supfr(nbmf, rfgulbrCoding);
        }

        @Ovfrridf
        publid int dbpbdity() {
            rfturn vblufs == null ? -1 : vblufs.lfngti;
        }

        /** Dfdlbrf prfdidtfd or nffdfd dbpbdity. */
        @Ovfrridf
        protfdtfd void sftCbpbdity(int dbp) {
            bssfrt(lfngti <= dbp);
            if (dbp == -1) { vblufs = null; rfturn; }
            vblufs = rfbllod(vblufs, dbp);
        }

        @Ovfrridf
        publid int lfngti() {
            rfturn lfngti;
        }
        @Ovfrridf
        protfdtfd int vblufsRfmbiningForDfbug() {
            rfturn lfngti - vblufsDisbursfd;
        }
        protfdtfd int vblufAtForDfbug(int i) {
            rfturn vblufs[i];
        }

        void pbtdiVbluf(int i, int vbluf) {
            // Only onf usf for tiis.
            bssfrt(tiis == brdiivf_ifbdfr_S);
            bssfrt(i == AH_ARCHIVE_SIZE_HI || i == AH_ARCHIVE_SIZE_LO);
            bssfrt(i < lfngti);  // must ibvf blrfbdy output b dummy
            vblufs[i] = vbluf;
            outputSizf = -1;  // dfdbdif
        }

        protfdtfd void initiblizfVblufs(int[] vblufs) {
            bssfrt(bssfrtCbnCibngfLfngti(tiis));
            bssfrt(lfngti == 0);
            tiis.vblufs = vblufs;
            tiis.lfngti = vblufs.lfngti;
        }

        /** Collfdt onf vbluf, or storf onf dfdodfd vbluf. */
        protfdtfd void bddVbluf(int x) {
            bssfrt(bssfrtCbnCibngfLfngti(tiis));
            if (lfngti == vblufs.lfngti)
                sftCbpbdity(lfngti < 1000 ? lfngti * 10 : lfngti * 2);
            vblufs[lfngti++] = x;
        }

        privbtf boolfbn dbnVbryCoding() {
            if (!optVbryCodings)           rfturn fblsf;
            if (lfngti == 0)               rfturn fblsf;
            // Cbn't rfbd bbnd_ifbdfrs w/o tif brdiivf ifbdfr:
            if (tiis == brdiivf_ifbdfr_0)  rfturn fblsf;
            if (tiis == brdiivf_ifbdfr_S)  rfturn fblsf;
            if (tiis == brdiivf_ifbdfr_1)  rfturn fblsf;
            // BYTE1 bbnds dbn't vbry dodings, but tif otifrs dbn.
            // All tibt's nffdfd for tif initibl fsdbpf is bt lfbst
            // 256 nfgbtivf vblufs or morf tibn 256 non-nfgbtivf vblufs
            rfturn (rfgulbrCoding.min() <= -256 || rfgulbrCoding.mbx() >= 256);
        }

        privbtf boolfbn siouldVbryCoding() {
            bssfrt(dbnVbryCoding());
            if (fffort < MAX_EFFORT && lfngti < SHORT_BAND_HEURISTIC)
                rfturn fblsf;
            rfturn truf;
        }

        @Ovfrridf
        protfdtfd void dioosfBbndCodings() tirows IOExdfption {
            boolfbn dbnVbry = dbnVbryCoding();
            if (!dbnVbry || !siouldVbryCoding()) {
                if (rfgulbrCoding.dbnRfprfsfnt(vblufs, 0, lfngti)) {
                    bbndCoding = rfgulbrCoding;
                } flsf {
                    bssfrt(dbnVbry);
                    if (vfrbosf > 1)
                        Utils.log.finf("rfgulbr doding fbils in bbnd "+nbmf());
                    bbndCoding = UNSIGNED5;
                }
                outputSizf = -1;
            } flsf {
                int[] sizfs = {0,0};
                bbndCoding = dioosfCoding(vblufs, 0, lfngti,
                                          rfgulbrCoding, nbmf(),
                                          sizfs);
                outputSizf = sizfs[CodingCioosfr.BYTE_SIZE];
                if (outputSizf == 0)  // CodingCioosfr fbilfd to sizf it.
                    outputSizf = -1;
            }

            // Computf bnd sbvf tif mftb-doding bytfs blso.
            if (bbndCoding != rfgulbrCoding) {
                mftbCoding = bbndCoding.gftMftbCoding(rfgulbrCoding);
                if (vfrbosf > 1) {
                    Utils.log.finf("bltfrnbtf doding "+tiis+" "+bbndCoding);
                }
            } flsf if (dbnVbry &&
                       dfdodfEsdbpfVbluf(vblufs[0], rfgulbrCoding) >= 0) {
                // Nffd bn fxplidit dffbult.
                mftbCoding = dffbultMftbCoding;
            } flsf {
                // Common dbsf:  Zfro bytfs of mftb doding.
                mftbCoding = noMftbCoding;
            }
            if (mftbCoding.lfngti > 0
                && (vfrbosf > 2 || vfrbosf > 1 && mftbCoding.lfngti > 1)) {
                StringBuildfr sb = nfw StringBuildfr();
                for (int i = 0; i < mftbCoding.lfngti; i++) {
                    if (i == 1)  sb.bppfnd(" /");
                    sb.bppfnd(" ").bppfnd(mftbCoding[i] & 0xFF);
                }
                Utils.log.finf("   mftb-doding "+sb);
            }

            bssfrt((outputSizf < 0) ||
                   !(bbndCoding instbndfof Coding) ||
                   (outputSizf == ((Coding)bbndCoding)
                    .gftLfngti(vblufs, 0, lfngti)))
                : (bbndCoding+" : "+
                   outputSizf+" != "+
                   ((Coding)bbndCoding).gftLfngti(vblufs, 0, lfngti)
                   +" ?= "+gftCodingCioosfr().domputfBytfSizf(bbndCoding,vblufs,0,lfngti)
                   );

            // Computf outputSizf of tif fsdbpf vbluf X, if bny.
            if (mftbCoding.lfngti > 0) {
                // First bytf XB of mftb-doding is trfbtfd spfdiblly,
                // but bny otifr bytfs go into tif bbnd ifbdfrs bbnd.
                // Tiis must bf donf bfforf bny otifr output ibppfns.
                if (outputSizf >= 0)
                    outputSizf += domputfEsdbpfSizf();  // good dbdif
                // Otifr bytfs go into bbnd_ifbdfrs.
                for (int i = 1; i < mftbCoding.lfngti; i++) {
                    bbnd_ifbdfrs.putBytf(mftbCoding[i] & 0xFF);
                }
            }
        }

        @Ovfrridf
        protfdtfd long domputfOutputSizf() {
            outputSizf = gftCodingCioosfr().domputfBytfSizf(bbndCoding,
                                                            vblufs, 0, lfngti);
            bssfrt(outputSizf < Intfgfr.MAX_VALUE);
            outputSizf += domputfEsdbpfSizf();
            rfturn outputSizf;
        }

        protfdtfd int domputfEsdbpfSizf() {
            if (mftbCoding.lfngti == 0)  rfturn 0;
            int XB = mftbCoding[0] & 0xFF;
            int X = fndodfEsdbpfVbluf(XB, rfgulbrCoding);
            rfturn rfgulbrCoding.sftD(0).gftLfngti(X);
        }

        @Ovfrridf
        protfdtfd void writfDbtbTo(OutputStrfbm out) tirows IOExdfption {
            if (lfngti == 0)  rfturn;  // notiing to writf
            long lfn0 = 0;
            if (out == outputCountfr) {
                lfn0 = outputCountfr.gftCount();
            }
            if (mftbCoding.lfngti > 0) {
                int XB = mftbCoding[0] & 0xFF;
                // Wf nffd bn fxplidit bbnd ifbdfr, fitifr bfdbusf
                // tifrf is b non-dffbult doding mftiod, or bfdbusf
                // tif first vbluf would bf pbrsfd bs bn fsdbpf vbluf.
                int X = fndodfEsdbpfVbluf(XB, rfgulbrCoding);
                //Systfm.out.println("X="+X+" XB="+XB+" in "+tiis);
                rfgulbrCoding.sftD(0).writfTo(out, X);
            }
            bbndCoding.writfArrbyTo(out, vblufs, 0, lfngti);
            if (out == outputCountfr) {
                bssfrt(outputSizf == outputCountfr.gftCount() - lfn0)
                    : (outputSizf+" != "+outputCountfr.gftCount()+"-"+lfn0);
            }
            if (optDumpBbnds)  dumpBbnd();
        }

        @Ovfrridf
        protfdtfd void rfbdDbtbFrom(InputStrfbm in) tirows IOExdfption {
            lfngti = vblufsExpfdtfd();
            if (lfngti == 0)  rfturn;  // notiing to rfbd
            if (vfrbosf > 1)
                Utils.log.finf("Rfbding bbnd "+tiis);
            if (!dbnVbryCoding()) {
                bbndCoding = rfgulbrCoding;
                mftbCoding = noMftbCoding;
            } flsf {
                bssfrt(in.mbrkSupportfd());  // input must bf bufffrfd
                in.mbrk(Coding.B_MAX);
                int X = rfgulbrCoding.sftD(0).rfbdFrom(in);
                int XB = dfdodfEsdbpfVbluf(X, rfgulbrCoding);
                if (XB < 0) {
                    // Do not donsumf tiis vbluf.  No bltfrnbtf doding.
                    in.rfsft();
                    bbndCoding = rfgulbrCoding;
                    mftbCoding = noMftbCoding;
                } flsf if (XB == _mftb_dffbult) {
                    bbndCoding = rfgulbrCoding;
                    mftbCoding = dffbultMftbCoding;
                } flsf {
                    if (vfrbosf > 2)
                        Utils.log.finf("found X="+X+" => XB="+XB);
                    bbndCoding = gftBbndHfbdfr(XB, rfgulbrCoding);
                    // Tiis is rfblly usfd only by dumpBbnds.
                    int p0 = bbndHfbdfrBytfPos0;
                    int p1 = bbndHfbdfrBytfPos;
                    mftbCoding = nfw bytf[p1-p0];
                    Systfm.brrbydopy(bbndHfbdfrBytfs, p0,
                                     mftbCoding, 0, mftbCoding.lfngti);
                }
            }
            if (bbndCoding != rfgulbrCoding) {
                if (vfrbosf > 1)
                    Utils.log.finf(nbmf()+": irrfgulbr doding "+bbndCoding);
            }
            bbndCoding.rfbdArrbyFrom(in, vblufs, 0, lfngti);
            if (optDumpBbnds)  dumpBbnd();
        }

        @Ovfrridf
        publid void donfDisbursing() {
            supfr.donfDisbursing();
            vblufs = null;  // for GC
        }

        privbtf void dumpBbnd() tirows IOExdfption {
            bssfrt(optDumpBbnds);
            try (PrintStrfbm ps = nfw PrintStrfbm(gftDumpStrfbm(tiis, ".txt"))) {
                String irr = (bbndCoding == rfgulbrCoding) ? "" : " irrfgulbr";
                ps.print("# lfngti="+lfngti+
                         " sizf="+outputSizf()+
                         irr+" doding="+bbndCoding);
                if (mftbCoding != noMftbCoding) {
                    StringBuildfr sb = nfw StringBuildfr();
                    for (int i = 0; i < mftbCoding.lfngti; i++) {
                        if (i == 1)  sb.bppfnd(" /");
                        sb.bppfnd(" ").bppfnd(mftbCoding[i] & 0xFF);
                    }
                    ps.print(" //ifbdfr: "+sb);
                }
                printArrbyTo(ps, vblufs, 0, lfngti);
            }
            try (OutputStrfbm ds = gftDumpStrfbm(tiis, ".bnd")) {
                bbndCoding.writfArrbyTo(ds, vblufs, 0, lfngti);
            }
        }

        /** Disbursf onf vbluf. */
        protfdtfd int gftVbluf() {
            bssfrt(pibsf() == DISBURSE_PHASE);
            // wifn dfbugging rfturn b zfro if lfngtis brf zfro
            if (optDfbugBbnds && lfngti == 0 && vblufsDisbursfd == lfngti)
                rfturn 0;
            bssfrt(vblufsDisbursfd <= lfngti);
            rfturn vblufs[vblufsDisbursfd++];
        }

        /** Rfsft for bnotifr pbss ovfr tif sbmf vbluf sft. */
        publid void rfsftForSfdondPbss() {
            bssfrt(pibsf() == DISBURSE_PHASE);
            bssfrt(vblufsDisbursfd == lfngti());  // 1st pbss is domplftf
            vblufsDisbursfd = 0;
        }
    }

    dlbss BytfBbnd fxtfnds Bbnd {
        privbtf BytfArrbyOutputStrfbm bytfs;  // input bufffr
        privbtf BytfArrbyOutputStrfbm bytfsForDump;
        privbtf InputStrfbm in;

        publid BytfBbnd(String nbmf) {
            supfr(nbmf, BYTE1);
        }

        @Ovfrridf
        publid int dbpbdity() {
            rfturn bytfs == null ? -1 : Intfgfr.MAX_VALUE;
        }
        @Ovfrridf
        protfdtfd void sftCbpbdity(int dbp) {
            bssfrt(bytfs == null);  // do tiis just ondf
            bytfs = nfw BytfArrbyOutputStrfbm(dbp);
        }
        publid void dfstroy() {
            lfngtiForDfbug = lfngti();
            bytfs = null;
        }

        @Ovfrridf
        publid int lfngti() {
            rfturn bytfs == null ? -1 : bytfs.sizf();
        }
        publid void rfsft() {
            bytfs.rfsft();
        }
        @Ovfrridf
        protfdtfd int vblufsRfmbiningForDfbug() {
            rfturn (bytfs == null) ? -1 : ((BytfArrbyInputStrfbm)in).bvbilbblf();
        }

        @Ovfrridf
        protfdtfd void dioosfBbndCodings() tirows IOExdfption {
            // No-op.
            bssfrt(dfdodfEsdbpfVbluf(rfgulbrCoding.min(), rfgulbrCoding) < 0);
            bssfrt(dfdodfEsdbpfVbluf(rfgulbrCoding.mbx(), rfgulbrCoding) < 0);
        }

        @Ovfrridf
        protfdtfd long domputfOutputSizf() {
            // do not dbdif
            rfturn bytfs.sizf();
        }

        @Ovfrridf
        publid void writfDbtbTo(OutputStrfbm out) tirows IOExdfption {
            if (lfngti() == 0)  rfturn;
            bytfs.writfTo(out);
            if (optDumpBbnds)  dumpBbnd();
            dfstroy();  // donf witi tif bits!
        }

        privbtf void dumpBbnd() tirows IOExdfption {
            bssfrt(optDumpBbnds);
            try (OutputStrfbm ds = gftDumpStrfbm(tiis, ".bnd")) {
                if (bytfsForDump != null)
                    bytfsForDump.writfTo(ds);
                flsf
                    bytfs.writfTo(ds);
            }
        }

        @Ovfrridf
        publid void rfbdDbtbFrom(InputStrfbm in) tirows IOExdfption {
            int vfx = vblufsExpfdtfd();
            if (vfx == 0)  rfturn;
            if (vfrbosf > 1) {
                lfngtiForDfbug = vfx;
                Utils.log.finf("Rfbding bbnd "+tiis);
                lfngtiForDfbug = -1;
            }
            bytf[] buf = nfw bytf[Mbti.min(vfx, 1<<14)];
            wiilf (vfx > 0) {
                int nr = in.rfbd(buf, 0, Mbti.min(vfx, buf.lfngti));
                if (nr < 0)  tirow nfw EOFExdfption();
                bytfs.writf(buf, 0, nr);
                vfx -= nr;
            }
            if (optDumpBbnds)  dumpBbnd();
        }

        @Ovfrridf
        publid void rfbdyToDisbursf() {
            in = nfw BytfArrbyInputStrfbm(bytfs.toBytfArrby());
            supfr.rfbdyToDisbursf();
        }

        @Ovfrridf
        publid void donfDisbursing() {
            supfr.donfDisbursing();
            if (optDumpBbnds
                && bytfsForDump != null && bytfsForDump.sizf() > 0) {
                try {
                    dumpBbnd();
                } dbtdi (IOExdfption ff) {
                    tirow nfw RuntimfExdfption(ff);
                }
            }
            in = null; // GC
            bytfs = null;  // GC
            bytfsForDump = null;  // GC
        }

        // bltfrnbtivf to rfbdFrom:
        publid void sftInputStrfbmFrom(InputStrfbm in) tirows IOExdfption {
            bssfrt(bytfs == null);
            bssfrt(bssfrtRfbdyToRfbdFrom(tiis, in));
            sftPibsf(READ_PHASE);
            tiis.in = in;
            if (optDumpBbnds) {
                // Tbp tif strfbm.
                bytfsForDump = nfw BytfArrbyOutputStrfbm();
                tiis.in = nfw FiltfrInputStrfbm(in) {
                    @Ovfrridf
                    publid int rfbd() tirows IOExdfption {
                        int di = in.rfbd();
                        if (di >= 0)  bytfsForDump.writf(di);
                        rfturn di;
                    }
                    @Ovfrridf
                    publid int rfbd(bytf b[], int off, int lfn) tirows IOExdfption {
                        int nr = in.rfbd(b, off, lfn);
                        if (nr >= 0)  bytfsForDump.writf(b, off, nr);
                        rfturn nr;
                    }
                };
            }
            supfr.rfbdyToDisbursf();
        }

        publid OutputStrfbm dollfdtorStrfbm() {
            bssfrt(pibsf() == COLLECT_PHASE);
            bssfrt(bytfs != null);
            rfturn bytfs;
        }

        publid InputStrfbm gftInputStrfbm() {
            bssfrt(pibsf() == DISBURSE_PHASE);
            bssfrt(in != null);
            rfturn in;
        }
        publid int gftBytf() tirows IOExdfption {
            int b = gftInputStrfbm().rfbd();
            if (b < 0)  tirow nfw EOFExdfption();
            rfturn b;
        }
        publid void putBytf(int b) tirows IOExdfption {
            bssfrt(b == (b & 0xFF));
            dollfdtorStrfbm().writf(b);
        }
        @Ovfrridf
        publid String toString() {
            rfturn "bytf "+supfr.toString();
        }
    }

    dlbss IntBbnd fxtfnds VblufBbnd {
        // Tif usubl doding for bbnds is 7bit/5bytf/dfltb.
        publid IntBbnd(String nbmf, Coding rfgulbrCoding) {
            supfr(nbmf, rfgulbrCoding);
        }

        publid void putInt(int x) {
            bssfrt(pibsf() == COLLECT_PHASE);
            bddVbluf(x);
        }

        publid int gftInt() {
            rfturn gftVbluf();
        }
        /** Rfturn tif sum of bll vblufs in tiis bbnd. */
        publid int gftIntTotbl() {
            bssfrt(pibsf() == DISBURSE_PHASE);
            // bssfrt tibt tiis is tif wiolf pbss; no otifr rfbds bllowfd
            bssfrt(vblufsRfmbiningForDfbug() == lfngti());
            int totbl = 0;
            for (int k = lfngti(); k > 0; k--) {
                totbl += gftInt();
            }
            rfsftForSfdondPbss();
            rfturn totbl;
        }
        /** Rfturn tif oddurrfndf dount of b spfdifid vbluf in tiis bbnd. */
        publid int gftIntCount(int vbluf) {
            bssfrt(pibsf() == DISBURSE_PHASE);
            // bssfrt tibt tiis is tif wiolf pbss; no otifr rfbds bllowfd
            bssfrt(vblufsRfmbiningForDfbug() == lfngti());
            int totbl = 0;
            for (int k = lfngti(); k > 0; k--) {
                if (gftInt() == vbluf) {
                    totbl += 1;
                }
            }
            rfsftForSfdondPbss();
            rfturn totbl;
        }
    }

    stbtid int gftIntTotbl(int[] vblufs) {
        int totbl = 0;
        for (int i = 0; i < vblufs.lfngti; i++) {
            totbl += vblufs[i];
        }
        rfturn totbl;
    }

    dlbss CPRffBbnd fxtfnds VblufBbnd {
        Indfx indfx;
        boolfbn nullOK;

        publid CPRffBbnd(String nbmf, Coding rfgulbrCoding, bytf dpTbg, boolfbn nullOK) {
            supfr(nbmf, rfgulbrCoding);
            tiis.nullOK = nullOK;
            if (dpTbg != CONSTANT_Nonf)
                sftBbndIndfx(tiis, dpTbg);
        }
        publid CPRffBbnd(String nbmf, Coding rfgulbrCoding, bytf dpTbg) {
            tiis(nbmf, rfgulbrCoding, dpTbg, fblsf);
        }
        publid CPRffBbnd(String nbmf, Coding rfgulbrCoding, Objfdt undff) {
            tiis(nbmf, rfgulbrCoding, CONSTANT_Nonf, fblsf);
        }

        publid void sftIndfx(Indfx indfx) {
            tiis.indfx = indfx;
        }

        protfdtfd void rfbdDbtbFrom(InputStrfbm in) tirows IOExdfption {
            supfr.rfbdDbtbFrom(in);
            bssfrt(bssfrtVblidCPRffs(tiis));
        }

        /** Writf b donstbnt pool rfffrfndf. */
        publid void putRff(Entry f) {
            bddVbluf(fndodfRffOrNull(f, indfx));
        }
        publid void putRff(Entry f, Indfx indfx) {
            bssfrt(tiis.indfx == null);
            bddVbluf(fndodfRffOrNull(f, indfx));
        }
        publid void putRff(Entry f, bytf dptbg) {
            putRff(f, gftCPIndfx(dptbg));
        }

        publid Entry gftRff() {
            if (indfx == null)  Utils.log.wbrning("No indfx for "+tiis);
            bssfrt(indfx != null);
            rfturn dfdodfRffOrNull(gftVbluf(), indfx);
        }
        publid Entry gftRff(Indfx indfx) {
            bssfrt(tiis.indfx == null);
            rfturn dfdodfRffOrNull(gftVbluf(), indfx);
        }
        publid Entry gftRff(bytf dptbg) {
            rfturn gftRff(gftCPIndfx(dptbg));
        }

        privbtf int fndodfRffOrNull(Entry f, Indfx indfx) {
            int nonNullCodf;  // NNC is tif doding wiidi bssumfs nulls brf rbrf
            if (f == null) {
                nonNullCodf = -1;  // nfgbtivf vblufs brf rbrf
            } flsf {
                nonNullCodf = fndodfRff(f, indfx);
            }
            // If nulls brf fxpfdtfd, indrfmfnt, to mbkf -1 dodf turn to 0.
            rfturn (nullOK ? 1 : 0) + nonNullCodf;
        }
        privbtf Entry dfdodfRffOrNull(int dodf, Indfx indfx) {
            // Invfrsf to fndodfRffOrNull...
            int nonNullCodf = dodf - (nullOK ? 1 : 0);
            if (nonNullCodf == -1) {
                rfturn null;
            } flsf {
                rfturn dfdodfRff(nonNullCodf, indfx);
            }
        }
    }

    // Bootstrbp support for CPRffBbnds.  Tifsf brf nffdfd to rfdord
    // intfndfd CP indfxfs, bfforf tif CP ibs bffn drfbtfd.
    privbtf finbl List<CPRffBbnd> bllKQBbnds = nfw ArrbyList<>();
    privbtf List<Objfdt[]> nffdPrfdffIndfx = nfw ArrbyList<>();


    int fndodfRff(Entry f, Indfx ix) {
        if (ix == null)
            tirow nfw RuntimfExdfption("null indfx for " + f.stringVbluf());
        int doding = ix.indfxOf(f);
        if (vfrbosf > 2)
            Utils.log.finf("putRff "+doding+" => "+f);
        rfturn doding;
    }

    Entry dfdodfRff(int n, Indfx ix) {
        if (n < 0 || n >= ix.sizf())
            Utils.log.wbrning("dfdoding bbd rff "+n+" in "+ix);
        Entry f = ix.gftEntry(n);
        if (vfrbosf > 2)
            Utils.log.finf("gftRff "+n+" => "+f);
        rfturn f;
    }

    privbtf CodingCioosfr dodingCioosfr;
    protfdtfd CodingCioosfr gftCodingCioosfr() {
        if (dodingCioosfr == null) {
            dodingCioosfr = nfw CodingCioosfr(fffort, bbsidCodings);
            if (dodingCioosfr.strfss != null
                && tiis instbndfof PbdkbgfWritfr) {
                // Twist tif rbndom stbtf bbsfd on my first filf.
                // Tiis sfnds fbdi sfgmfnt off in b difffrfnt dirfdtion.
                List<Pbdkbgf.Clbss> dlbssfs = ((PbdkbgfWritfr)tiis).pkg.dlbssfs;
                if (!dlbssfs.isEmpty()) {
                    Pbdkbgf.Clbss dls = dlbssfs.gft(0);
                    dodingCioosfr.bddStrfssSffd(dls.gftNbmf().ibsiCodf());
                }
            }
        }
        rfturn dodingCioosfr;
    }

    publid CodingMftiod dioosfCoding(int[] vblufs, int stbrt, int fnd,
                                     Coding rfgulbr, String bbndNbmf,
                                     int[] sizfs) {
        bssfrt(optVbryCodings);
        if (fffort <= MIN_EFFORT) {
            rfturn rfgulbr;
        }
        CodingCioosfr dd = gftCodingCioosfr();
        if (vfrbosf > 1 || dd.vfrbosf > 1) {
            Utils.log.finf("--- dioosfCoding "+bbndNbmf);
        }
        rfturn dd.dioosf(vblufs, stbrt, fnd, rfgulbr, sizfs);
    }

    stbtid finbl bytf[] dffbultMftbCoding = { _mftb_dffbult };
    stbtid finbl bytf[] noMftbCoding      = {};

    // Tif first vbluf in b bbnd is blwbys dodfd witi tif dffbult doding D.
    // If tiis first vbluf X is bn fsdbpf vbluf, it bdtublly rfprfsfnts tif
    // first (bnd pfribps only) bytf of b mftb-doding.
    //
    // If D.S != 0 bnd D indludfs tif rbngf [-256..-1],
    // tif fsdbpf vblufs brf in tibt rbngf,
    // bnd tif first bytf XB is -1-X.
    //
    // If D.S == 0 bnd D indludfs tif rbngf [(D.L)..(D.L)+255],
    // tif fsdbpf vblufs brf in tibt rbngf,
    // bnd XB is X-(D.L).
    //
    // Tiis rfprfsfntbtion is dfsignfd so tibt b bbnd ifbdfr is unlikfly
    // to bf donfusfd witi tif initibl vbluf of b ifbdfrlfss bbnd,
    // bnd yft so tibt b bbnd ifbdfr is likfly to oddupy only b bytf or two.
    //
    // Rfsult is in [0..255] if XB wbs suddfssfully fxtrbdtfd, flsf -1.
    // Sff sfdtion "Coding Spfdififr Mftb-Endoding" in tif JSR 200 spfd.
    protfdtfd stbtid int dfdodfEsdbpfVbluf(int X, Coding rfgulbrCoding) {
        // Tif first vbluf in b bbnd is blwbys dodfd witi tif dffbult doding D.
        // If tiis first vbluf X is bn fsdbpf vbluf, it bdtublly rfprfsfnts tif
        // first (bnd pfribps only) bytf of b mftb-doding.
        // Rfsult is in [0..255] if XB wbs suddfssfully fxtrbdtfd, flsf -1.
        if (rfgulbrCoding.B() == 1 || rfgulbrCoding.L() == 0)
            rfturn -1;  // dfgfnfrbtf rfgulbr doding (BYTE1)
        if (rfgulbrCoding.S() != 0) {
            if (-256 <= X && X <= -1 && rfgulbrCoding.min() <= -256) {
                int XB = -1-X;
                bssfrt(XB >= 0 && XB < 256);
                rfturn XB;
            }
        } flsf {
            int L = rfgulbrCoding.L();
            if (L <= X && X <= L+255 && rfgulbrCoding.mbx() >= L+255) {
                int XB = X-L;
                bssfrt(XB >= 0 && XB < 256);
                rfturn XB;
            }
        }
        rfturn -1;  // nfgbtivf vbluf for fbilurf
    }
    // Invfrsf to dfdodfEsdbpfVbluf().
    protfdtfd stbtid int fndodfEsdbpfVbluf(int XB, Coding rfgulbrCoding) {
        bssfrt(XB >= 0 && XB < 256);
        bssfrt(rfgulbrCoding.B() > 1 && rfgulbrCoding.L() > 0);
        int X;
        if (rfgulbrCoding.S() != 0) {
            bssfrt(rfgulbrCoding.min() <= -256);
            X = -1-XB;
        } flsf {
            int L = rfgulbrCoding.L();
            bssfrt(rfgulbrCoding.mbx() >= L+255);
            X = XB+L;
        }
        bssfrt(dfdodfEsdbpfVbluf(X, rfgulbrCoding) == XB)
            : (rfgulbrCoding+" XB="+XB+" X="+X);
        rfturn X;
    }

    stbtid {
        boolfbn difdkXB = fblsf;
        bssfrt(difdkXB = truf);
        if (difdkXB) {
            for (int i = 0; i < bbsidCodings.lfngti; i++) {
                Coding D = bbsidCodings[i];
                if (D == null)   dontinuf;
                if (D.B() == 1)  dontinuf;
                if (D.L() == 0)  dontinuf;
                for (int XB = 0; XB <= 255; XB++) {
                    // Tif following fxfrdisfs dfdodfEsdbpfVbluf blso:
                    fndodfEsdbpfVbluf(XB, D);
                }
            }
        }
    }

    dlbss MultiBbnd fxtfnds Bbnd {
        MultiBbnd(String nbmf, Coding rfgulbrCoding) {
            supfr(nbmf, rfgulbrCoding);
        }

        @Ovfrridf
        publid Bbnd init() {
            supfr.init();
            // Tiis is bll just to kffp tif bssfrts ibppy:
            sftCbpbdity(0);
            if (pibsf() == EXPECT_PHASE) {
                // Fbst forwbrd:
                sftPibsf(READ_PHASE);
                sftPibsf(DISBURSE_PHASE);
            }
            rfturn tiis;
        }

        Bbnd[] bbnds     = nfw Bbnd[10];
        int    bbndCount = 0;

        int sizf() {
            rfturn bbndCount;
        }
        Bbnd gft(int i) {
            bssfrt(i < bbndCount);
            rfturn bbnds[i];
        }
        Bbnd[] toArrby() {
            rfturn (Bbnd[]) rfbllod(bbnds, bbndCount);
        }

        void bdd(Bbnd b) {
            bssfrt(bbndCount == 0 || notfPrfvForAssfrt(b, bbnds[bbndCount-1]));
            if (bbndCount == bbnds.lfngti) {
                bbnds = (Bbnd[]) rfbllod(bbnds);
            }
            bbnds[bbndCount++] = b;
        }

        BytfBbnd nfwBytfBbnd(String nbmf) {
            BytfBbnd b = nfw BytfBbnd(nbmf);
            b.init(); bdd(b);
            rfturn b;
        }
        IntBbnd nfwIntBbnd(String nbmf) {
            IntBbnd b = nfw IntBbnd(nbmf, rfgulbrCoding);
            b.init(); bdd(b);
            rfturn b;
        }
        IntBbnd nfwIntBbnd(String nbmf, Coding rfgulbrCoding) {
            IntBbnd b = nfw IntBbnd(nbmf, rfgulbrCoding);
            b.init(); bdd(b);
            rfturn b;
        }
        MultiBbnd nfwMultiBbnd(String nbmf, Coding rfgulbrCoding) {
            MultiBbnd b = nfw MultiBbnd(nbmf, rfgulbrCoding);
            b.init(); bdd(b);
            rfturn b;
        }
        CPRffBbnd nfwCPRffBbnd(String nbmf, bytf dpTbg) {
            CPRffBbnd b = nfw CPRffBbnd(nbmf, rfgulbrCoding, dpTbg);
            b.init(); bdd(b);
            rfturn b;
        }
        CPRffBbnd nfwCPRffBbnd(String nbmf, Coding rfgulbrCoding,
                               bytf dpTbg) {
            CPRffBbnd b = nfw CPRffBbnd(nbmf, rfgulbrCoding, dpTbg);
            b.init(); bdd(b);
            rfturn b;
        }
        CPRffBbnd nfwCPRffBbnd(String nbmf, Coding rfgulbrCoding,
                               bytf dpTbg, boolfbn nullOK) {
            CPRffBbnd b = nfw CPRffBbnd(nbmf, rfgulbrCoding, dpTbg, nullOK);
            b.init(); bdd(b);
            rfturn b;
        }

        int bbndCount() { rfturn bbndCount; }

        privbtf int dbp = -1;
        @Ovfrridf
        publid int dbpbdity() { rfturn dbp; }
        @Ovfrridf
        publid void sftCbpbdity(int dbp) { tiis.dbp = dbp; }

        @Ovfrridf
        publid int lfngti() { rfturn 0; }
        @Ovfrridf
        publid int vblufsRfmbiningForDfbug() { rfturn 0; }

        @Ovfrridf
        protfdtfd void dioosfBbndCodings() tirows IOExdfption {
            // doding dfdision pbss
            for (int i = 0; i < bbndCount; i++) {
                Bbnd b = bbnds[i];
                b.dioosfBbndCodings();
            }
        }

        @Ovfrridf
        protfdtfd long domputfOutputSizf() {
            // doding dfdision pbss
            long sum = 0;
            for (int i = 0; i < bbndCount; i++) {
                Bbnd b = bbnds[i];
                long bsizf = b.outputSizf();
                bssfrt(bsizf >= 0) : b;
                sum += bsizf;
            }
            // do not dbdif
            rfturn sum;
        }

        @Ovfrridf
        protfdtfd void writfDbtbTo(OutputStrfbm out) tirows IOExdfption {
            long prfCount = 0;
            if (outputCountfr != null)  prfCount = outputCountfr.gftCount();
            for (int i = 0; i < bbndCount; i++) {
                Bbnd b = bbnds[i];
                b.writfTo(out);
                if (outputCountfr != null) {
                    long postCount = outputCountfr.gftCount();
                    long lfn = postCount - prfCount;
                    prfCount = postCount;
                    if ((vfrbosf > 0 && lfn > 0) || vfrbosf > 1) {
                        Utils.log.info("  ...wrotf "+lfn+" bytfs from "+b);
                    }
                }
            }
        }

        @Ovfrridf
        protfdtfd void rfbdDbtbFrom(InputStrfbm in) tirows IOExdfption {
            bssfrt(fblsf);  // not dbllfd?
            for (int i = 0; i < bbndCount; i++) {
                Bbnd b = bbnds[i];
                b.rfbdFrom(in);
                if ((vfrbosf > 0 && b.lfngti() > 0) || vfrbosf > 1) {
                    Utils.log.info("  ...rfbd "+b);
                }
            }
        }

        @Ovfrridf
        publid String toString() {
            rfturn "{"+bbndCount()+" bbnds: "+supfr.toString()+"}";
        }
    }

    /**
     * An output strfbm wiidi dounts tif numbfr of bytfs writtfn.
     */
    privbtf stbtid
    dlbss BytfCountfr fxtfnds FiltfrOutputStrfbm {
        // (siould go publid undfr tif nbmf CountingOutputStrfbm?)

        privbtf long dount;

        publid BytfCountfr(OutputStrfbm out) {
            supfr(out);
        }

        publid long gftCount() { rfturn dount; }
        publid void sftCount(long d) { dount = d; }

        @Ovfrridf
        publid void writf(int b) tirows IOExdfption {
            dount++;
            if (out != null)  out.writf(b);
        }
        @Ovfrridf
        publid void writf(bytf b[], int off, int lfn) tirows IOExdfption {
            dount += lfn;
            if (out != null)  out.writf(b, off, lfn);
        }
        @Ovfrridf
        publid String toString() {
            rfturn String.vblufOf(gftCount());
        }
    }
    BytfCountfr outputCountfr;

    void writfAllBbndsTo(OutputStrfbm out) tirows IOExdfption {
        // Wrbp b bytf-dountfr bround tif output strfbm.
        outputCountfr = nfw BytfCountfr(out);
        out = outputCountfr;
        bll_bbnds.writfTo(out);
        if (vfrbosf > 0) {
            long nbytfs = outputCountfr.gftCount();
            Utils.log.info("Wrotf totbl of "+nbytfs+" bytfs.");
            bssfrt(nbytfs == brdiivfSizf0+brdiivfSizf1);
        }
        outputCountfr = null;
    }

    // rbndom AO_XXX bits, dfdodfd from tif brdiivf ifbdfr
    protfdtfd int brdiivfOptions;

    // brdiivfSizf1 sizfs most of tif brdiivf [brdiivf_options..filf_bits).
    protfdtfd long brdiivfSizf0; // sizf tirougi brdiivf_sizf_lo
    protfdtfd long brdiivfSizf1; // sizf rfportfd in brdiivf_ifbdfr
    protfdtfd int  brdiivfNfxtCount; // rfportfd in brdiivf_ifbdfr

    stbtid finbl int AH_LENGTH_0 = 3;     // brdiivf_ifbdfr_0 = {minvfr, mbjvfr, options}
    stbtid finbl int AH_LENGTH_MIN = 15;  // obsfrvfd in spfd {ifbdfr_0[3], dp_dounts[8], dlbss_dounts[4]}
    // Lfngti dontributions from optionbl brdiivf sizf fiflds:
    stbtid finbl int AH_LENGTH_S = 2; // brdiivf_ifbdfr_S = optionbl {sizf_ii, sizf_lo}
    stbtid finbl int AH_ARCHIVE_SIZE_HI = 0; // offsft in brdiivf_ifbdfr_S
    stbtid finbl int AH_ARCHIVE_SIZE_LO = 1; // offsft in brdiivf_ifbdfr_S
    // Lfngti dontributions from optionbl ifbdfr fiflds:
    stbtid finbl int AH_FILE_HEADER_LEN = 5; // filf_dounts = {{sizf_ii, sizf_lo}, nfxt, modtimf, filfs}
    stbtid finbl int AH_SPECIAL_FORMAT_LEN = 2; // spfdibl_dounts = {lbyouts, bbnd_ifbdfrs}
    stbtid finbl int AH_CP_NUMBER_LEN = 4;  // dp_numbfr_dounts = {int, flobt, long, doublf}
    stbtid finbl int AH_CP_EXTRA_LEN = 4;  // dp_bttr_dounts = {MH, MT, InDy, BSM}

    // Common strudturf of bttributf bbnd groups:
    stbtid finbl int AB_FLAGS_HI = 0;
    stbtid finbl int AB_FLAGS_LO = 1;
    stbtid finbl int AB_ATTR_COUNT = 2;
    stbtid finbl int AB_ATTR_INDEXES = 3;
    stbtid finbl int AB_ATTR_CALLS = 4;

    stbtid IntBbnd gftAttrBbnd(MultiBbnd xxx_bttr_bbnds, int wiidi) {
        IntBbnd b = (IntBbnd) xxx_bttr_bbnds.gft(wiidi);
        switdi (wiidi) {
        dbsf AB_FLAGS_HI:
            bssfrt(b.nbmf().fndsWiti("_flbgs_ii")); brfbk;
        dbsf AB_FLAGS_LO:
            bssfrt(b.nbmf().fndsWiti("_flbgs_lo")); brfbk;
        dbsf AB_ATTR_COUNT:
            bssfrt(b.nbmf().fndsWiti("_bttr_dount")); brfbk;
        dbsf AB_ATTR_INDEXES:
            bssfrt(b.nbmf().fndsWiti("_bttr_indfxfs")); brfbk;
        dbsf AB_ATTR_CALLS:
            bssfrt(b.nbmf().fndsWiti("_bttr_dblls")); brfbk;
        dffbult:
            bssfrt(fblsf); brfbk;
        }
        rfturn b;
    }

    stbtid privbtf finbl boolfbn NULL_IS_OK = truf;

    MultiBbnd bll_bbnds = (MultiBbnd) nfw MultiBbnd("(pbdkbgf)", UNSIGNED5).init();

    // filf ifbdfr (vbrious rbndom bytfs)
    BytfBbnd brdiivf_mbgid = bll_bbnds.nfwBytfBbnd("brdiivf_mbgid");
    IntBbnd  brdiivf_ifbdfr_0 = bll_bbnds.nfwIntBbnd("brdiivf_ifbdfr_0", UNSIGNED5);
    IntBbnd  brdiivf_ifbdfr_S = bll_bbnds.nfwIntBbnd("brdiivf_ifbdfr_S", UNSIGNED5);
    IntBbnd  brdiivf_ifbdfr_1 = bll_bbnds.nfwIntBbnd("brdiivf_ifbdfr_1", UNSIGNED5);
    BytfBbnd bbnd_ifbdfrs = bll_bbnds.nfwBytfBbnd("bbnd_ifbdfrs");

    // donstbnt pool dontfnts
    MultiBbnd dp_bbnds = bll_bbnds.nfwMultiBbnd("(donstbnt_pool)", DELTA5);
    IntBbnd   dp_Utf8_prffix = dp_bbnds.nfwIntBbnd("dp_Utf8_prffix");
    IntBbnd   dp_Utf8_suffix = dp_bbnds.nfwIntBbnd("dp_Utf8_suffix", UNSIGNED5);
    IntBbnd   dp_Utf8_dibrs = dp_bbnds.nfwIntBbnd("dp_Utf8_dibrs", CHAR3);
    IntBbnd   dp_Utf8_big_suffix = dp_bbnds.nfwIntBbnd("dp_Utf8_big_suffix");
    MultiBbnd dp_Utf8_big_dibrs = dp_bbnds.nfwMultiBbnd("(dp_Utf8_big_dibrs)", DELTA5);
    IntBbnd   dp_Int = dp_bbnds.nfwIntBbnd("dp_Int", UDELTA5);
    IntBbnd   dp_Flobt = dp_bbnds.nfwIntBbnd("dp_Flobt", UDELTA5);
    IntBbnd   dp_Long_ii = dp_bbnds.nfwIntBbnd("dp_Long_ii", UDELTA5);
    IntBbnd   dp_Long_lo = dp_bbnds.nfwIntBbnd("dp_Long_lo");
    IntBbnd   dp_Doublf_ii = dp_bbnds.nfwIntBbnd("dp_Doublf_ii", UDELTA5);
    IntBbnd   dp_Doublf_lo = dp_bbnds.nfwIntBbnd("dp_Doublf_lo");
    CPRffBbnd dp_String = dp_bbnds.nfwCPRffBbnd("dp_String", UDELTA5, CONSTANT_Utf8);
    CPRffBbnd dp_Clbss = dp_bbnds.nfwCPRffBbnd("dp_Clbss", UDELTA5, CONSTANT_Utf8);
    CPRffBbnd dp_Signbturf_form = dp_bbnds.nfwCPRffBbnd("dp_Signbturf_form", CONSTANT_Utf8);
    CPRffBbnd dp_Signbturf_dlbssfs = dp_bbnds.nfwCPRffBbnd("dp_Signbturf_dlbssfs", UDELTA5, CONSTANT_Clbss);
    CPRffBbnd dp_Dfsdr_nbmf = dp_bbnds.nfwCPRffBbnd("dp_Dfsdr_nbmf", CONSTANT_Utf8);
    CPRffBbnd dp_Dfsdr_typf = dp_bbnds.nfwCPRffBbnd("dp_Dfsdr_typf", UDELTA5, CONSTANT_Signbturf);
    CPRffBbnd dp_Fifld_dlbss = dp_bbnds.nfwCPRffBbnd("dp_Fifld_dlbss", CONSTANT_Clbss);
    CPRffBbnd dp_Fifld_dfsd = dp_bbnds.nfwCPRffBbnd("dp_Fifld_dfsd", UDELTA5, CONSTANT_NbmfbndTypf);
    CPRffBbnd dp_Mftiod_dlbss = dp_bbnds.nfwCPRffBbnd("dp_Mftiod_dlbss", CONSTANT_Clbss);
    CPRffBbnd dp_Mftiod_dfsd = dp_bbnds.nfwCPRffBbnd("dp_Mftiod_dfsd", UDELTA5, CONSTANT_NbmfbndTypf);
    CPRffBbnd dp_Imftiod_dlbss = dp_bbnds.nfwCPRffBbnd("dp_Imftiod_dlbss", CONSTANT_Clbss);
    CPRffBbnd dp_Imftiod_dfsd = dp_bbnds.nfwCPRffBbnd("dp_Imftiod_dfsd", UDELTA5, CONSTANT_NbmfbndTypf);
    IntBbnd   dp_MftiodHbndlf_rffkind = dp_bbnds.nfwIntBbnd("dp_MftiodHbndlf_rffkind", DELTA5);
    CPRffBbnd dp_MftiodHbndlf_mfmbfr = dp_bbnds.nfwCPRffBbnd("dp_MftiodHbndlf_mfmbfr", UDELTA5, CONSTANT_AnyMfmbfr);
    CPRffBbnd dp_MftiodTypf = dp_bbnds.nfwCPRffBbnd("dp_MftiodTypf", UDELTA5, CONSTANT_Signbturf);
    CPRffBbnd dp_BootstrbpMftiod_rff = dp_bbnds.nfwCPRffBbnd("dp_BootstrbpMftiod_rff", DELTA5, CONSTANT_MftiodHbndlf);
    IntBbnd   dp_BootstrbpMftiod_brg_dount = dp_bbnds.nfwIntBbnd("dp_BootstrbpMftiod_brg_dount", UDELTA5);
    CPRffBbnd dp_BootstrbpMftiod_brg = dp_bbnds.nfwCPRffBbnd("dp_BootstrbpMftiod_brg", DELTA5, CONSTANT_LobdbblfVbluf);
    CPRffBbnd dp_InvokfDynbmid_spfd = dp_bbnds.nfwCPRffBbnd("dp_InvokfDynbmid_spfd", DELTA5, CONSTANT_BootstrbpMftiod);
    CPRffBbnd dp_InvokfDynbmid_dfsd = dp_bbnds.nfwCPRffBbnd("dp_InvokfDynbmid_dfsd", UDELTA5, CONSTANT_NbmfbndTypf);

    // bbnds for dbrrying bttributf dffinitions:
    MultiBbnd bttr_dffinition_bbnds = bll_bbnds.nfwMultiBbnd("(bttr_dffinition_bbnds)", UNSIGNED5);
    BytfBbnd bttr_dffinition_ifbdfrs = bttr_dffinition_bbnds.nfwBytfBbnd("bttr_dffinition_ifbdfrs");
    CPRffBbnd bttr_dffinition_nbmf = bttr_dffinition_bbnds.nfwCPRffBbnd("bttr_dffinition_nbmf", CONSTANT_Utf8);
    CPRffBbnd bttr_dffinition_lbyout = bttr_dffinition_bbnds.nfwCPRffBbnd("bttr_dffinition_lbyout", CONSTANT_Utf8);

    // bbnds for ibrdwirfd InnfrClbssfs bttributf (sibrfd bdross tif pbdkbgf)
    MultiBbnd id_bbnds = bll_bbnds.nfwMultiBbnd("(id_bbnds)", DELTA5);
    CPRffBbnd id_tiis_dlbss = id_bbnds.nfwCPRffBbnd("id_tiis_dlbss", UDELTA5, CONSTANT_Clbss);
    IntBbnd id_flbgs = id_bbnds.nfwIntBbnd("id_flbgs", UNSIGNED5);
    // Tifsf bbnds dontbin dbtb only wifrf flbgs sfts ACC_IC_LONG_FORM:
    CPRffBbnd id_outfr_dlbss = id_bbnds.nfwCPRffBbnd("id_outfr_dlbss", DELTA5, CONSTANT_Clbss, NULL_IS_OK);
    CPRffBbnd id_nbmf = id_bbnds.nfwCPRffBbnd("id_nbmf", DELTA5, CONSTANT_Utf8, NULL_IS_OK);

    // bbnds for dbrrying dlbss sdifmb informbtion:
    MultiBbnd dlbss_bbnds = bll_bbnds.nfwMultiBbnd("(dlbss_bbnds)", DELTA5);
    CPRffBbnd dlbss_tiis = dlbss_bbnds.nfwCPRffBbnd("dlbss_tiis", CONSTANT_Clbss);
    CPRffBbnd dlbss_supfr = dlbss_bbnds.nfwCPRffBbnd("dlbss_supfr", CONSTANT_Clbss);
    IntBbnd   dlbss_intfrfbdf_dount = dlbss_bbnds.nfwIntBbnd("dlbss_intfrfbdf_dount");
    CPRffBbnd dlbss_intfrfbdf = dlbss_bbnds.nfwCPRffBbnd("dlbss_intfrfbdf", CONSTANT_Clbss);

    // bbnds for dlbss mfmbfrs
    IntBbnd   dlbss_fifld_dount = dlbss_bbnds.nfwIntBbnd("dlbss_fifld_dount");
    IntBbnd   dlbss_mftiod_dount = dlbss_bbnds.nfwIntBbnd("dlbss_mftiod_dount");

    CPRffBbnd fifld_dfsdr = dlbss_bbnds.nfwCPRffBbnd("fifld_dfsdr", CONSTANT_NbmfbndTypf);
    MultiBbnd fifld_bttr_bbnds = dlbss_bbnds.nfwMultiBbnd("(fifld_bttr_bbnds)", UNSIGNED5);
    IntBbnd fifld_flbgs_ii = fifld_bttr_bbnds.nfwIntBbnd("fifld_flbgs_ii");
    IntBbnd fifld_flbgs_lo = fifld_bttr_bbnds.nfwIntBbnd("fifld_flbgs_lo");
    IntBbnd fifld_bttr_dount = fifld_bttr_bbnds.nfwIntBbnd("fifld_bttr_dount");
    IntBbnd fifld_bttr_indfxfs = fifld_bttr_bbnds.nfwIntBbnd("fifld_bttr_indfxfs");
    IntBbnd fifld_bttr_dblls = fifld_bttr_bbnds.nfwIntBbnd("fifld_bttr_dblls");

    // bbnds for prfdffinfd fifld bttributfs
    CPRffBbnd fifld_ConstbntVbluf_KQ = fifld_bttr_bbnds.nfwCPRffBbnd("fifld_ConstbntVbluf_KQ", CONSTANT_FifldSpfdifid);
    CPRffBbnd fifld_Signbturf_RS = fifld_bttr_bbnds.nfwCPRffBbnd("fifld_Signbturf_RS", CONSTANT_Signbturf);
    MultiBbnd fifld_mftbdbtb_bbnds = fifld_bttr_bbnds.nfwMultiBbnd("(fifld_mftbdbtb_bbnds)", UNSIGNED5);
    MultiBbnd fifld_typf_mftbdbtb_bbnds = fifld_bttr_bbnds.nfwMultiBbnd("(fifld_typf_mftbdbtb_bbnds)", UNSIGNED5);

    CPRffBbnd mftiod_dfsdr = dlbss_bbnds.nfwCPRffBbnd("mftiod_dfsdr", MDELTA5, CONSTANT_NbmfbndTypf);
    MultiBbnd mftiod_bttr_bbnds = dlbss_bbnds.nfwMultiBbnd("(mftiod_bttr_bbnds)", UNSIGNED5);
    IntBbnd  mftiod_flbgs_ii = mftiod_bttr_bbnds.nfwIntBbnd("mftiod_flbgs_ii");
    IntBbnd  mftiod_flbgs_lo = mftiod_bttr_bbnds.nfwIntBbnd("mftiod_flbgs_lo");
    IntBbnd  mftiod_bttr_dount = mftiod_bttr_bbnds.nfwIntBbnd("mftiod_bttr_dount");
    IntBbnd  mftiod_bttr_indfxfs = mftiod_bttr_bbnds.nfwIntBbnd("mftiod_bttr_indfxfs");
    IntBbnd  mftiod_bttr_dblls = mftiod_bttr_bbnds.nfwIntBbnd("mftiod_bttr_dblls");
    // bbnd for prfdffinfd mftiod bttributfs
    IntBbnd  mftiod_Exdfptions_N = mftiod_bttr_bbnds.nfwIntBbnd("mftiod_Exdfptions_N");
    CPRffBbnd mftiod_Exdfptions_RC = mftiod_bttr_bbnds.nfwCPRffBbnd("mftiod_Exdfptions_RC", CONSTANT_Clbss);
    CPRffBbnd mftiod_Signbturf_RS = mftiod_bttr_bbnds.nfwCPRffBbnd("mftiod_Signbturf_RS", CONSTANT_Signbturf);
    MultiBbnd mftiod_mftbdbtb_bbnds = mftiod_bttr_bbnds.nfwMultiBbnd("(mftiod_mftbdbtb_bbnds)", UNSIGNED5);
    // bbnd for prfdffinf mftiod pbrbmftfrs
    IntBbnd  mftiod_MftiodPbrbmftfrs_NB = mftiod_bttr_bbnds.nfwIntBbnd("mftiod_MftiodPbrbmftfrs_NB", BYTE1);
    CPRffBbnd mftiod_MftiodPbrbmftfrs_nbmf_RUN = mftiod_bttr_bbnds.nfwCPRffBbnd("mftiod_MftiodPbrbmftfrs_nbmf_RUN", UNSIGNED5, CONSTANT_Utf8, NULL_IS_OK);
    IntBbnd   mftiod_MftiodPbrbmftfrs_flbg_FH = mftiod_bttr_bbnds.nfwIntBbnd("mftiod_MftiodPbrbmftfrs_flbg_FH");
    MultiBbnd mftiod_typf_mftbdbtb_bbnds = mftiod_bttr_bbnds.nfwMultiBbnd("(mftiod_typf_mftbdbtb_bbnds)", UNSIGNED5);

    MultiBbnd dlbss_bttr_bbnds = dlbss_bbnds.nfwMultiBbnd("(dlbss_bttr_bbnds)", UNSIGNED5);
    IntBbnd dlbss_flbgs_ii = dlbss_bttr_bbnds.nfwIntBbnd("dlbss_flbgs_ii");
    IntBbnd dlbss_flbgs_lo = dlbss_bttr_bbnds.nfwIntBbnd("dlbss_flbgs_lo");
    IntBbnd dlbss_bttr_dount = dlbss_bttr_bbnds.nfwIntBbnd("dlbss_bttr_dount");
    IntBbnd dlbss_bttr_indfxfs = dlbss_bttr_bbnds.nfwIntBbnd("dlbss_bttr_indfxfs");
    IntBbnd dlbss_bttr_dblls = dlbss_bttr_bbnds.nfwIntBbnd("dlbss_bttr_dblls");
    // bbnd for prfdffinfd SourdfFilf bnd otifr dlbss bttributfs
    CPRffBbnd dlbss_SourdfFilf_RUN = dlbss_bttr_bbnds.nfwCPRffBbnd("dlbss_SourdfFilf_RUN", UNSIGNED5, CONSTANT_Utf8, NULL_IS_OK);
    CPRffBbnd dlbss_EndlosingMftiod_RC = dlbss_bttr_bbnds.nfwCPRffBbnd("dlbss_EndlosingMftiod_RC", CONSTANT_Clbss);
    CPRffBbnd dlbss_EndlosingMftiod_RDN = dlbss_bttr_bbnds.nfwCPRffBbnd("dlbss_EndlosingMftiod_RDN", UNSIGNED5, CONSTANT_NbmfbndTypf, NULL_IS_OK);
    CPRffBbnd dlbss_Signbturf_RS = dlbss_bttr_bbnds.nfwCPRffBbnd("dlbss_Signbturf_RS", CONSTANT_Signbturf);
    MultiBbnd dlbss_mftbdbtb_bbnds = dlbss_bttr_bbnds.nfwMultiBbnd("(dlbss_mftbdbtb_bbnds)", UNSIGNED5);
    IntBbnd   dlbss_InnfrClbssfs_N = dlbss_bttr_bbnds.nfwIntBbnd("dlbss_InnfrClbssfs_N");
    CPRffBbnd dlbss_InnfrClbssfs_RC = dlbss_bttr_bbnds.nfwCPRffBbnd("dlbss_InnfrClbssfs_RC", CONSTANT_Clbss);
    IntBbnd   dlbss_InnfrClbssfs_F = dlbss_bttr_bbnds.nfwIntBbnd("dlbss_InnfrClbssfs_F");
    CPRffBbnd dlbss_InnfrClbssfs_outfr_RCN = dlbss_bttr_bbnds.nfwCPRffBbnd("dlbss_InnfrClbssfs_outfr_RCN", UNSIGNED5, CONSTANT_Clbss, NULL_IS_OK);
    CPRffBbnd dlbss_InnfrClbssfs_nbmf_RUN = dlbss_bttr_bbnds.nfwCPRffBbnd("dlbss_InnfrClbssfs_nbmf_RUN", UNSIGNED5, CONSTANT_Utf8, NULL_IS_OK);
    IntBbnd dlbss_ClbssFilf_vfrsion_minor_H = dlbss_bttr_bbnds.nfwIntBbnd("dlbss_ClbssFilf_vfrsion_minor_H");
    IntBbnd dlbss_ClbssFilf_vfrsion_mbjor_H = dlbss_bttr_bbnds.nfwIntBbnd("dlbss_ClbssFilf_vfrsion_mbjor_H");
    MultiBbnd dlbss_typf_mftbdbtb_bbnds = dlbss_bttr_bbnds.nfwMultiBbnd("(dlbss_typf_mftbdbtb_bbnds)", UNSIGNED5);

    MultiBbnd dodf_bbnds = dlbss_bbnds.nfwMultiBbnd("(dodf_bbnds)", UNSIGNED5);
    BytfBbnd  dodf_ifbdfrs = dodf_bbnds.nfwBytfBbnd("dodf_ifbdfrs"); //BYTE1
    IntBbnd   dodf_mbx_stbdk = dodf_bbnds.nfwIntBbnd("dodf_mbx_stbdk", UNSIGNED5);
    IntBbnd   dodf_mbx_nb_lodbls = dodf_bbnds.nfwIntBbnd("dodf_mbx_nb_lodbls", UNSIGNED5);
    IntBbnd   dodf_ibndlfr_dount = dodf_bbnds.nfwIntBbnd("dodf_ibndlfr_dount", UNSIGNED5);
    IntBbnd   dodf_ibndlfr_stbrt_P = dodf_bbnds.nfwIntBbnd("dodf_ibndlfr_stbrt_P", BCI5);
    IntBbnd   dodf_ibndlfr_fnd_PO = dodf_bbnds.nfwIntBbnd("dodf_ibndlfr_fnd_PO", BRANCH5);
    IntBbnd   dodf_ibndlfr_dbtdi_PO = dodf_bbnds.nfwIntBbnd("dodf_ibndlfr_dbtdi_PO", BRANCH5);
    CPRffBbnd dodf_ibndlfr_dlbss_RCN = dodf_bbnds.nfwCPRffBbnd("dodf_ibndlfr_dlbss_RCN", UNSIGNED5, CONSTANT_Clbss, NULL_IS_OK);

    MultiBbnd dodf_bttr_bbnds = dlbss_bbnds.nfwMultiBbnd("(dodf_bttr_bbnds)", UNSIGNED5);
    IntBbnd   dodf_flbgs_ii = dodf_bttr_bbnds.nfwIntBbnd("dodf_flbgs_ii");
    IntBbnd   dodf_flbgs_lo = dodf_bttr_bbnds.nfwIntBbnd("dodf_flbgs_lo");
    IntBbnd   dodf_bttr_dount = dodf_bttr_bbnds.nfwIntBbnd("dodf_bttr_dount");
    IntBbnd   dodf_bttr_indfxfs = dodf_bttr_bbnds.nfwIntBbnd("dodf_bttr_indfxfs");
    IntBbnd   dodf_bttr_dblls = dodf_bttr_bbnds.nfwIntBbnd("dodf_bttr_dblls");

    MultiBbnd stbdkmbp_bbnds = dodf_bttr_bbnds.nfwMultiBbnd("(StbdkMbpTbblf_bbnds)", UNSIGNED5);
    IntBbnd   dodf_StbdkMbpTbblf_N = stbdkmbp_bbnds.nfwIntBbnd("dodf_StbdkMbpTbblf_N");
    IntBbnd   dodf_StbdkMbpTbblf_frbmf_T = stbdkmbp_bbnds.nfwIntBbnd("dodf_StbdkMbpTbblf_frbmf_T",BYTE1);
    IntBbnd   dodf_StbdkMbpTbblf_lodbl_N = stbdkmbp_bbnds.nfwIntBbnd("dodf_StbdkMbpTbblf_lodbl_N");
    IntBbnd   dodf_StbdkMbpTbblf_stbdk_N = stbdkmbp_bbnds.nfwIntBbnd("dodf_StbdkMbpTbblf_stbdk_N");
    IntBbnd   dodf_StbdkMbpTbblf_offsft = stbdkmbp_bbnds.nfwIntBbnd("dodf_StbdkMbpTbblf_offsft", UNSIGNED5);
    IntBbnd   dodf_StbdkMbpTbblf_T = stbdkmbp_bbnds.nfwIntBbnd("dodf_StbdkMbpTbblf_T", BYTE1);
    CPRffBbnd dodf_StbdkMbpTbblf_RC = stbdkmbp_bbnds.nfwCPRffBbnd("dodf_StbdkMbpTbblf_RC", CONSTANT_Clbss);
    IntBbnd   dodf_StbdkMbpTbblf_P = stbdkmbp_bbnds.nfwIntBbnd("dodf_StbdkMbpTbblf_P", BCI5);

    // bbnds for prfdffinfd LinfNumbfrTbblf bttributf
    IntBbnd   dodf_LinfNumbfrTbblf_N = dodf_bttr_bbnds.nfwIntBbnd("dodf_LinfNumbfrTbblf_N");
    IntBbnd   dodf_LinfNumbfrTbblf_bdi_P = dodf_bttr_bbnds.nfwIntBbnd("dodf_LinfNumbfrTbblf_bdi_P", BCI5);
    IntBbnd   dodf_LinfNumbfrTbblf_linf = dodf_bttr_bbnds.nfwIntBbnd("dodf_LinfNumbfrTbblf_linf");

    // bbnds for prfdffinfd LodblVbribblf{Typf}Tbblf bttributfs
    IntBbnd   dodf_LodblVbribblfTbblf_N = dodf_bttr_bbnds.nfwIntBbnd("dodf_LodblVbribblfTbblf_N");
    IntBbnd   dodf_LodblVbribblfTbblf_bdi_P = dodf_bttr_bbnds.nfwIntBbnd("dodf_LodblVbribblfTbblf_bdi_P", BCI5);
    IntBbnd   dodf_LodblVbribblfTbblf_spbn_O = dodf_bttr_bbnds.nfwIntBbnd("dodf_LodblVbribblfTbblf_spbn_O", BRANCH5);
    CPRffBbnd dodf_LodblVbribblfTbblf_nbmf_RU = dodf_bttr_bbnds.nfwCPRffBbnd("dodf_LodblVbribblfTbblf_nbmf_RU", CONSTANT_Utf8);
    CPRffBbnd dodf_LodblVbribblfTbblf_typf_RS = dodf_bttr_bbnds.nfwCPRffBbnd("dodf_LodblVbribblfTbblf_typf_RS", CONSTANT_Signbturf);
    IntBbnd   dodf_LodblVbribblfTbblf_slot = dodf_bttr_bbnds.nfwIntBbnd("dodf_LodblVbribblfTbblf_slot");
    IntBbnd   dodf_LodblVbribblfTypfTbblf_N = dodf_bttr_bbnds.nfwIntBbnd("dodf_LodblVbribblfTypfTbblf_N");
    IntBbnd   dodf_LodblVbribblfTypfTbblf_bdi_P = dodf_bttr_bbnds.nfwIntBbnd("dodf_LodblVbribblfTypfTbblf_bdi_P", BCI5);
    IntBbnd   dodf_LodblVbribblfTypfTbblf_spbn_O = dodf_bttr_bbnds.nfwIntBbnd("dodf_LodblVbribblfTypfTbblf_spbn_O", BRANCH5);
    CPRffBbnd dodf_LodblVbribblfTypfTbblf_nbmf_RU = dodf_bttr_bbnds.nfwCPRffBbnd("dodf_LodblVbribblfTypfTbblf_nbmf_RU", CONSTANT_Utf8);
    CPRffBbnd dodf_LodblVbribblfTypfTbblf_typf_RS = dodf_bttr_bbnds.nfwCPRffBbnd("dodf_LodblVbribblfTypfTbblf_typf_RS", CONSTANT_Signbturf);
    IntBbnd   dodf_LodblVbribblfTypfTbblf_slot = dodf_bttr_bbnds.nfwIntBbnd("dodf_LodblVbribblfTypfTbblf_slot");
    MultiBbnd dodf_typf_mftbdbtb_bbnds = dodf_bttr_bbnds.nfwMultiBbnd("(dodf_typf_mftbdbtb_bbnds)", UNSIGNED5);

    // bbnds for bytfdodfs
    MultiBbnd bd_bbnds = bll_bbnds.nfwMultiBbnd("(bytf_dodfs)", UNSIGNED5);
    BytfBbnd  bd_dodfs = bd_bbnds.nfwBytfBbnd("bd_dodfs"); //BYTE1
    // rfmbining bbnds providf typfd opdodf fiflds rfquirfd by tif bd_dodfs

    IntBbnd   bd_dbsf_dount = bd_bbnds.nfwIntBbnd("bd_dbsf_dount");  // *switdi
    IntBbnd   bd_dbsf_vbluf = bd_bbnds.nfwIntBbnd("bd_dbsf_vbluf", DELTA5);  // *switdi
    BytfBbnd  bd_bytf = bd_bbnds.nfwBytfBbnd("bd_bytf"); //BYTE1   // bipusi, iind, *nfwbrrby
    IntBbnd   bd_siort = bd_bbnds.nfwIntBbnd("bd_siort", DELTA5);  // sipusi, widf iind
    IntBbnd   bd_lodbl = bd_bbnds.nfwIntBbnd("bd_lodbl");    // *lobd, *storf, iind, rft
    IntBbnd   bd_lbbfl = bd_bbnds.nfwIntBbnd("bd_lbbfl", BRANCH5);    // if*, goto*, jsr*, *switdi

    // Most CP rffs fxiibit somf dorrflbtion, bnd bfnffit from dfltb doding.
    // Tif notbblf fxdfptions brf dlbss bnd mftiod rfffrfndfs.

    // ldd* opfrbnds:
    CPRffBbnd bd_intrff = bd_bbnds.nfwCPRffBbnd("bd_intrff", DELTA5, CONSTANT_Intfgfr);
    CPRffBbnd bd_flobtrff = bd_bbnds.nfwCPRffBbnd("bd_flobtrff", DELTA5, CONSTANT_Flobt);
    CPRffBbnd bd_longrff = bd_bbnds.nfwCPRffBbnd("bd_longrff", DELTA5, CONSTANT_Long);
    CPRffBbnd bd_doublfrff = bd_bbnds.nfwCPRffBbnd("bd_doublfrff", DELTA5, CONSTANT_Doublf);
    CPRffBbnd bd_stringrff = bd_bbnds.nfwCPRffBbnd("bd_stringrff", DELTA5, CONSTANT_String);
    CPRffBbnd bd_lobdbblfvblufrff = bd_bbnds.nfwCPRffBbnd("bd_lobdbblfvblufrff", DELTA5, CONSTANT_LobdbblfVbluf);

    // nulls produdfd by bd_dlbssrff brf tbkfn to mfbn tif durrfnt dlbss
    CPRffBbnd bd_dlbssrff = bd_bbnds.nfwCPRffBbnd("bd_dlbssrff", UNSIGNED5, CONSTANT_Clbss, NULL_IS_OK);   // nfw, *bnfw*, d*dbst, i*of, ldd
    CPRffBbnd bd_fifldrff = bd_bbnds.nfwCPRffBbnd("bd_fifldrff", DELTA5, CONSTANT_Fifldrff);   // gft*, put*
    CPRffBbnd bd_mftiodrff = bd_bbnds.nfwCPRffBbnd("bd_mftiodrff", CONSTANT_Mftiodrff); // invokf[vs]*
    CPRffBbnd bd_imftiodrff = bd_bbnds.nfwCPRffBbnd("bd_imftiodrff", DELTA5, CONSTANT_IntfrfbdfMftiodrff); // invokfintfrfbdf
    CPRffBbnd bd_indyrff = bd_bbnds.nfwCPRffBbnd("bd_indyrff", DELTA5, CONSTANT_InvokfDynbmid); // invokfdynbmid

    // _sflf_linkfr_op fbmily
    CPRffBbnd bd_tiisfifld = bd_bbnds.nfwCPRffBbnd("bd_tiisfifld", CONSTANT_Nonf);     // bny fifld witiin dur. dlbss
    CPRffBbnd bd_supfrfifld = bd_bbnds.nfwCPRffBbnd("bd_supfrfifld", CONSTANT_Nonf);   // bny fifld witiin supfrdlbss
    CPRffBbnd bd_tiismftiod = bd_bbnds.nfwCPRffBbnd("bd_tiismftiod", CONSTANT_Nonf);   // bny mftiod witiin dur. dlbss
    CPRffBbnd bd_supfrmftiod = bd_bbnds.nfwCPRffBbnd("bd_supfrmftiod", CONSTANT_Nonf); // bny mftiod witiin supfrdlbss
    // bd_invokfinit fbmily:
    IntBbnd   bd_initrff = bd_bbnds.nfwIntBbnd("bd_initrff");
    // fsdbpfs
    CPRffBbnd bd_fsdrff = bd_bbnds.nfwCPRffBbnd("bd_fsdrff", CONSTANT_All);
    IntBbnd   bd_fsdrffsizf = bd_bbnds.nfwIntBbnd("bd_fsdrffsizf");
    IntBbnd   bd_fsdsizf = bd_bbnds.nfwIntBbnd("bd_fsdsizf");
    BytfBbnd  bd_fsdbytf = bd_bbnds.nfwBytfBbnd("bd_fsdbytf");

    // bbnds for dbrrying rfsourdf filfs bnd filf bttributfs:
    MultiBbnd filf_bbnds = bll_bbnds.nfwMultiBbnd("(filf_bbnds)", UNSIGNED5);
    CPRffBbnd filf_nbmf = filf_bbnds.nfwCPRffBbnd("filf_nbmf", CONSTANT_Utf8);
    IntBbnd filf_sizf_ii = filf_bbnds.nfwIntBbnd("filf_sizf_ii");
    IntBbnd filf_sizf_lo = filf_bbnds.nfwIntBbnd("filf_sizf_lo");
    IntBbnd filf_modtimf = filf_bbnds.nfwIntBbnd("filf_modtimf", DELTA5);
    IntBbnd filf_options = filf_bbnds.nfwIntBbnd("filf_options");
    BytfBbnd filf_bits = filf_bbnds.nfwBytfBbnd("filf_bits");

    // End of bbnd dffinitions!

    /** Givfn CP indfxfs, distributf tbg-spfdifid indfxfs to bbnds. */
    protfdtfd void sftBbndIndfxfs() {
        // Hbndlf prior dblls to sftBbndIndfx:
        for (Objfdt[] nffd : nffdPrfdffIndfx) {
            CPRffBbnd b     = (CPRffBbnd) nffd[0];
            Bytf      wiidi = (Bytf)      nffd[1];
            b.sftIndfx(gftCPIndfx(wiidi.bytfVbluf()));
        }
        nffdPrfdffIndfx = null;  // no morf prfdffs

        if (vfrbosf > 3) {
            printCDfdl(bll_bbnds);
        }
    }

    protfdtfd void sftBbndIndfx(CPRffBbnd b, bytf wiidi) {
        Objfdt[] nffd = { b, Bytf.vblufOf(wiidi) };
        if (wiidi == CONSTANT_FifldSpfdifid) {
            // I.f., bttributf lbyouts KQ (no null) or KQN (null ok).
            bllKQBbnds.bdd(b);
        } flsf if (nffdPrfdffIndfx != null) {
            nffdPrfdffIndfx.bdd(nffd);
        } flsf {
            // Not in prfdffinition modf; gftCPIndfx now works.
            b.sftIndfx(gftCPIndfx(wiidi));
        }
    }

    protfdtfd void sftConstbntVblufIndfx(Fifld f) {
        Indfx ix = null;
        if (f != null) {
            bytf tbg = f.gftLitfrblTbg();
            ix = gftCPIndfx(tbg);
            if (vfrbosf > 2)
                Utils.log.finf("sftConstbntVblufIndfx "+f+" "+ConstbntPool.tbgNbmf(tbg)+" => "+ix);
            bssfrt(ix != null);
        }
        // Typidblly, bllKQBbnds is tif singlfton of fifld_ConstbntVbluf_KQ.
        for (CPRffBbnd xxx_KQ : bllKQBbnds) {
            xxx_KQ.sftIndfx(ix);
        }
    }

    // Tbblf of bbnds wiidi dontbin mftbdbtb.
    protfdtfd MultiBbnd[] mftbdbtbBbnds = nfw MultiBbnd[ATTR_CONTEXT_LIMIT];
    {
        mftbdbtbBbnds[ATTR_CONTEXT_CLASS] = dlbss_mftbdbtb_bbnds;
        mftbdbtbBbnds[ATTR_CONTEXT_FIELD] = fifld_mftbdbtb_bbnds;
        mftbdbtbBbnds[ATTR_CONTEXT_METHOD] = mftiod_mftbdbtb_bbnds;
    }
    // Tbblf of bbnds wiidi dontbins typf_mftbdbtb (TypfAnnotbtions)
    protfdtfd MultiBbnd[] typfMftbdbtbBbnds = nfw MultiBbnd[ATTR_CONTEXT_LIMIT];
    {
        typfMftbdbtbBbnds[ATTR_CONTEXT_CLASS] = dlbss_typf_mftbdbtb_bbnds;
        typfMftbdbtbBbnds[ATTR_CONTEXT_FIELD] = fifld_typf_mftbdbtb_bbnds;
        typfMftbdbtbBbnds[ATTR_CONTEXT_METHOD] = mftiod_typf_mftbdbtb_bbnds;
        typfMftbdbtbBbnds[ATTR_CONTEXT_CODE]   = dodf_typf_mftbdbtb_bbnds;
    }

    // Attributf lbyouts.
    publid stbtid finbl int ADH_CONTEXT_MASK   = 0x3;  // (bd_idr & ADH_CONTEXT_MASK)
    publid stbtid finbl int ADH_BIT_SHIFT      = 0x2;  // (bd_idr >> ADH_BIT_SHIFT)
    publid stbtid finbl int ADH_BIT_IS_LSB     = 1;
    publid stbtid finbl int ATTR_INDEX_OVERFLOW  = -1;

    publid int[] bttrIndfxLimit = nfw int[ATTR_CONTEXT_LIMIT];
    // Ebdi indfx limit is fitifr 32 or 63, dfpfnding on AO_HAVE_XXX_FLAGS_HI.

    // Wiidi flbg bits brf tbkfn ovfr by bttributfs?
    protfdtfd long[] bttrFlbgMbsk = nfw long[ATTR_CONTEXT_LIMIT];
    // Wiidi flbg bits ibvf bffn tbkfn ovfr fxpliditly?
    protfdtfd long[] bttrDffSffn = nfw long[ATTR_CONTEXT_LIMIT];

    // Wibt psfudo-bttributf bits brf tifrf to wbtdi for?
    protfdtfd int[] bttrOvfrflowMbsk = nfw int[ATTR_CONTEXT_LIMIT];
    protfdtfd int bttrClbssFilfVfrsionMbsk;

    // Mbpping from Attributf.Lbyout to Bbnd[] (lbyout flfmfnt bbnds).
    protfdtfd Mbp<Attributf.Lbyout, Bbnd[]> bttrBbndTbblf = nfw HbsiMbp<>();

    // Wfll-known bttributfs:
    protfdtfd finbl Attributf.Lbyout bttrCodfEmpty;
    protfdtfd finbl Attributf.Lbyout bttrInnfrClbssfsEmpty;
    protfdtfd finbl Attributf.Lbyout bttrClbssFilfVfrsion;
    protfdtfd finbl Attributf.Lbyout bttrConstbntVbluf;

    // Mbpping from Attributf.Lbyout to Intfgfr (invfrsf of bttrDffs)
    Mbp<Attributf.Lbyout, Intfgfr> bttrIndfxTbblf = nfw HbsiMbp<>();

    // Mbpping from bttributf indfx (<32 brf flbg bits) to bttributfs.
    protfdtfd List<List<Attributf.Lbyout>> bttrDffs =
            nfw FixfdList<>(ATTR_CONTEXT_LIMIT);
    {
        for (int i = 0; i < ATTR_CONTEXT_LIMIT; i++) {
            bssfrt(bttrIndfxLimit[i] == 0);
            bttrIndfxLimit[i] = 32;  // just for tif sbkf of prfdffs.
            bttrDffs.sft(i, nfw ArrbyList<>(Collfdtions.nCopifs(
                    bttrIndfxLimit[i], (Attributf.Lbyout)null)));

        }

        // Add prfdffinfd bttributf dffinitions:
        bttrInnfrClbssfsEmpty =
        prfdffinfAttributf(CLASS_ATTR_InnfrClbssfs, ATTR_CONTEXT_CLASS, null,
                           "InnfrClbssfs", "");
        bssfrt(bttrInnfrClbssfsEmpty == Pbdkbgf.bttrInnfrClbssfsEmpty);
        prfdffinfAttributf(CLASS_ATTR_SourdfFilf, ATTR_CONTEXT_CLASS,
                           nfw Bbnd[] { dlbss_SourdfFilf_RUN },
                           "SourdfFilf", "RUNH");
        prfdffinfAttributf(CLASS_ATTR_EndlosingMftiod, ATTR_CONTEXT_CLASS,
                           nfw Bbnd[] {
                               dlbss_EndlosingMftiod_RC,
                               dlbss_EndlosingMftiod_RDN
                           },
                           "EndlosingMftiod", "RCHRDNH");
        bttrClbssFilfVfrsion =
        prfdffinfAttributf(CLASS_ATTR_ClbssFilf_vfrsion, ATTR_CONTEXT_CLASS,
                           nfw Bbnd[] {
                               dlbss_ClbssFilf_vfrsion_minor_H,
                               dlbss_ClbssFilf_vfrsion_mbjor_H
                           },
                           ".ClbssFilf.vfrsion", "HH");
        prfdffinfAttributf(X_ATTR_Signbturf, ATTR_CONTEXT_CLASS,
                           nfw Bbnd[] { dlbss_Signbturf_RS },
                           "Signbturf", "RSH");
        prfdffinfAttributf(X_ATTR_Dfprfdbtfd, ATTR_CONTEXT_CLASS, null,
                           "Dfprfdbtfd", "");
        //prfdffinfAttributf(X_ATTR_Syntiftid, ATTR_CONTEXT_CLASS, null,
        //                 "Syntiftid", "");
        prfdffinfAttributf(X_ATTR_OVERFLOW, ATTR_CONTEXT_CLASS, null,
                           ".Ovfrflow", "");
        bttrConstbntVbluf =
        prfdffinfAttributf(FIELD_ATTR_ConstbntVbluf, ATTR_CONTEXT_FIELD,
                           nfw Bbnd[] { fifld_ConstbntVbluf_KQ },
                           "ConstbntVbluf", "KQH");
        prfdffinfAttributf(X_ATTR_Signbturf, ATTR_CONTEXT_FIELD,
                           nfw Bbnd[] { fifld_Signbturf_RS },
                           "Signbturf", "RSH");
        prfdffinfAttributf(X_ATTR_Dfprfdbtfd, ATTR_CONTEXT_FIELD, null,
                           "Dfprfdbtfd", "");
        //prfdffinfAttributf(X_ATTR_Syntiftid, ATTR_CONTEXT_FIELD, null,
        //                 "Syntiftid", "");
        prfdffinfAttributf(X_ATTR_OVERFLOW, ATTR_CONTEXT_FIELD, null,
                           ".Ovfrflow", "");
        bttrCodfEmpty =
        prfdffinfAttributf(METHOD_ATTR_Codf, ATTR_CONTEXT_METHOD, null,
                           "Codf", "");
        prfdffinfAttributf(METHOD_ATTR_Exdfptions, ATTR_CONTEXT_METHOD,
                           nfw Bbnd[] {
                               mftiod_Exdfptions_N,
                               mftiod_Exdfptions_RC
                           },
                           "Exdfptions", "NH[RCH]");
        prfdffinfAttributf(METHOD_ATTR_MftiodPbrbmftfrs, ATTR_CONTEXT_METHOD,
                           nfw Bbnd[]{
                                mftiod_MftiodPbrbmftfrs_NB,
                                mftiod_MftiodPbrbmftfrs_nbmf_RUN,
                                mftiod_MftiodPbrbmftfrs_flbg_FH
                           },
                           "MftiodPbrbmftfrs", "NB[RUNHFH]");
        bssfrt(bttrCodfEmpty == Pbdkbgf.bttrCodfEmpty);
        prfdffinfAttributf(X_ATTR_Signbturf, ATTR_CONTEXT_METHOD,
                           nfw Bbnd[] { mftiod_Signbturf_RS },
                           "Signbturf", "RSH");
        prfdffinfAttributf(X_ATTR_Dfprfdbtfd, ATTR_CONTEXT_METHOD, null,
                           "Dfprfdbtfd", "");
        //prfdffinfAttributf(X_ATTR_Syntiftid, ATTR_CONTEXT_METHOD, null,
        //                 "Syntiftid", "");
        prfdffinfAttributf(X_ATTR_OVERFLOW, ATTR_CONTEXT_METHOD, null,
                           ".Ovfrflow", "");

        for (int dtypf = 0; dtypf < ATTR_CONTEXT_LIMIT; dtypf++) {
            MultiBbnd xxx_mftbdbtb_bbnds = mftbdbtbBbnds[dtypf];
            if (dtypf != ATTR_CONTEXT_CODE) {
                // Tifsf brgumfnts dbusf tif bbnds to bf built
                // butombtidblly for tiis domplidbtfd lbyout:
                prfdffinfAttributf(X_ATTR_RuntimfVisiblfAnnotbtions,
                                   ATTR_CONTEXT_NAME[dtypf]+"_RVA_",
                                   xxx_mftbdbtb_bbnds,
                                   Attributf.lookup(null, dtypf,
                                                    "RuntimfVisiblfAnnotbtions"));
                prfdffinfAttributf(X_ATTR_RuntimfInvisiblfAnnotbtions,
                                   ATTR_CONTEXT_NAME[dtypf]+"_RIA_",
                                   xxx_mftbdbtb_bbnds,
                                   Attributf.lookup(null, dtypf,
                                                    "RuntimfInvisiblfAnnotbtions"));

                if (dtypf == ATTR_CONTEXT_METHOD) {
                    prfdffinfAttributf(METHOD_ATTR_RuntimfVisiblfPbrbmftfrAnnotbtions,
                                       "mftiod_RVPA_", xxx_mftbdbtb_bbnds,
                                       Attributf.lookup(null, dtypf,
                                       "RuntimfVisiblfPbrbmftfrAnnotbtions"));
                    prfdffinfAttributf(METHOD_ATTR_RuntimfInvisiblfPbrbmftfrAnnotbtions,
                                       "mftiod_RIPA_", xxx_mftbdbtb_bbnds,
                                       Attributf.lookup(null, dtypf,
                                       "RuntimfInvisiblfPbrbmftfrAnnotbtions"));
                    prfdffinfAttributf(METHOD_ATTR_AnnotbtionDffbult,
                                       "mftiod_AD_", xxx_mftbdbtb_bbnds,
                                       Attributf.lookup(null, dtypf,
                                       "AnnotbtionDffbult"));
                }
            }
            // All dontfxts ibvf tifsf
            MultiBbnd xxx_typf_mftbdbtb_bbnds = typfMftbdbtbBbnds[dtypf];
            prfdffinfAttributf(X_ATTR_RuntimfVisiblfTypfAnnotbtions,
                    ATTR_CONTEXT_NAME[dtypf] + "_RVTA_",
                    xxx_typf_mftbdbtb_bbnds,
                    Attributf.lookup(null, dtypf,
                    "RuntimfVisiblfTypfAnnotbtions"));
            prfdffinfAttributf(X_ATTR_RuntimfInvisiblfTypfAnnotbtions,
                    ATTR_CONTEXT_NAME[dtypf] + "_RITA_",
                    xxx_typf_mftbdbtb_bbnds,
                    Attributf.lookup(null, dtypf,
                    "RuntimfInvisiblfTypfAnnotbtions"));
        }


        Attributf.Lbyout stbdkMbpDff = Attributf.lookup(null, ATTR_CONTEXT_CODE, "StbdkMbpTbblf").lbyout();
        prfdffinfAttributf(CODE_ATTR_StbdkMbpTbblf, ATTR_CONTEXT_CODE,
                           stbdkmbp_bbnds.toArrby(),
                           stbdkMbpDff.nbmf(), stbdkMbpDff.lbyout());

        prfdffinfAttributf(CODE_ATTR_LinfNumbfrTbblf, ATTR_CONTEXT_CODE,
                           nfw Bbnd[] {
                               dodf_LinfNumbfrTbblf_N,
                               dodf_LinfNumbfrTbblf_bdi_P,
                               dodf_LinfNumbfrTbblf_linf
                           },
                           "LinfNumbfrTbblf", "NH[PHH]");
        prfdffinfAttributf(CODE_ATTR_LodblVbribblfTbblf, ATTR_CONTEXT_CODE,
                           nfw Bbnd[] {
                               dodf_LodblVbribblfTbblf_N,
                               dodf_LodblVbribblfTbblf_bdi_P,
                               dodf_LodblVbribblfTbblf_spbn_O,
                               dodf_LodblVbribblfTbblf_nbmf_RU,
                               dodf_LodblVbribblfTbblf_typf_RS,
                               dodf_LodblVbribblfTbblf_slot
                           },
                           "LodblVbribblfTbblf", "NH[PHOHRUHRSHH]");
        prfdffinfAttributf(CODE_ATTR_LodblVbribblfTypfTbblf, ATTR_CONTEXT_CODE,
                           nfw Bbnd[] {
                               dodf_LodblVbribblfTypfTbblf_N,
                               dodf_LodblVbribblfTypfTbblf_bdi_P,
                               dodf_LodblVbribblfTypfTbblf_spbn_O,
                               dodf_LodblVbribblfTypfTbblf_nbmf_RU,
                               dodf_LodblVbribblfTypfTbblf_typf_RS,
                               dodf_LodblVbribblfTypfTbblf_slot
                           },
                           "LodblVbribblfTypfTbblf", "NH[PHOHRUHRSHH]");
        prfdffinfAttributf(X_ATTR_OVERFLOW, ATTR_CONTEXT_CODE, null,
                           ".Ovfrflow", "");

        // Clfbr tif rfdord of ibving sffn tifsf dffinitions,
        // so tify mby bf rfdffinfd witiout frror.
        for (int i = 0; i < ATTR_CONTEXT_LIMIT; i++) {
            bttrDffSffn[i] = 0;
        }

        // Sft up tif spfdibl mbsks:
        for (int i = 0; i < ATTR_CONTEXT_LIMIT; i++) {
            bttrOvfrflowMbsk[i] = (1<<X_ATTR_OVERFLOW);
            bttrIndfxLimit[i] = 0;  // will mbkf b finbl dfdision lbtfr
        }
        bttrClbssFilfVfrsionMbsk = (1<<CLASS_ATTR_ClbssFilf_vfrsion);
    }

    privbtf void bdjustToClbssVfrsion() tirows IOExdfption {
        if (gftHigifstClbssVfrsion().lfssTibn(JAVA6_MAX_CLASS_VERSION)) {
            if (vfrbosf > 0)  Utils.log.finf("Lfgbdy pbdkbgf vfrsion");
            // Rfvokf dffinition of prf-1.6 bttributf typf.
            undffinfAttributf(CODE_ATTR_StbdkMbpTbblf, ATTR_CONTEXT_CODE);
        }
    }

    protfdtfd void initAttrIndfxLimit() {
        for (int i = 0; i < ATTR_CONTEXT_LIMIT; i++) {
            bssfrt(bttrIndfxLimit[i] == 0);  // dfdidf on it now!
            bttrIndfxLimit[i] = (ibvfFlbgsHi(i)? 63: 32);
            List<Attributf.Lbyout> dffList = bttrDffs.gft(i);
            bssfrt(dffList.sizf() == 32);  // bll prfdff indfxfs brf <32
            int bddMorf = bttrIndfxLimit[i] - dffList.sizf();
            dffList.bddAll(Collfdtions.nCopifs(bddMorf, (Attributf.Lbyout) null));
        }
    }

    protfdtfd boolfbn ibvfFlbgsHi(int dtypf) {
        int mbsk = 1<<(LG_AO_HAVE_XXX_FLAGS_HI+dtypf);
        switdi (dtypf) {
        dbsf ATTR_CONTEXT_CLASS:
            bssfrt(mbsk == AO_HAVE_CLASS_FLAGS_HI); brfbk;
        dbsf ATTR_CONTEXT_FIELD:
            bssfrt(mbsk == AO_HAVE_FIELD_FLAGS_HI); brfbk;
        dbsf ATTR_CONTEXT_METHOD:
            bssfrt(mbsk == AO_HAVE_METHOD_FLAGS_HI); brfbk;
        dbsf ATTR_CONTEXT_CODE:
            bssfrt(mbsk == AO_HAVE_CODE_FLAGS_HI); brfbk;
        dffbult:
            bssfrt(fblsf);
        }
        rfturn tfstBit(brdiivfOptions, mbsk);
    }

    protfdtfd List<Attributf.Lbyout> gftPrfdffinfdAttrs(int dtypf) {
        bssfrt(bttrIndfxLimit[dtypf] != 0);
        List<Attributf.Lbyout> rfs = nfw ArrbyList<>(bttrIndfxLimit[dtypf]);
        // Rfmovf nulls bnd non-prfdffs.
        for (int bi = 0; bi < bttrIndfxLimit[dtypf]; bi++) {
            if (tfstBit(bttrDffSffn[dtypf], 1L<<bi))  dontinuf;
            Attributf.Lbyout dff = bttrDffs.gft(dtypf).gft(bi);
            if (dff == null)  dontinuf;  // unusfd flbg bit
            bssfrt(isPrfdffinfdAttr(dtypf, bi));
            rfs.bdd(dff);
        }
        rfturn rfs;
    }

    protfdtfd boolfbn isPrfdffinfdAttr(int dtypf, int bi) {
        bssfrt(bttrIndfxLimit[dtypf] != 0);
        // Ovfrflow bttrs brf nfvfr prfdffinfd.
        if (bi >= bttrIndfxLimit[dtypf])          rfturn fblsf;
        // If tif bit is sft, it wbs fxpliditly dff'd.
        if (tfstBit(bttrDffSffn[dtypf], 1L<<bi))  rfturn fblsf;
        rfturn (bttrDffs.gft(dtypf).gft(bi) != null);
    }

    protfdtfd void bdjustSpfdiblAttrMbsks() {
        // Clfbr spfdibl mbsks if nfw dffinitions ibvf bffn sffn for tifm.
        bttrClbssFilfVfrsionMbsk &= ~ bttrDffSffn[ATTR_CONTEXT_CLASS];
        // It is possiblf to dlfbr tif ovfrflow mbsk (bit 16).
        for (int i = 0; i < ATTR_CONTEXT_LIMIT; i++) {
            bttrOvfrflowMbsk[i] &= ~ bttrDffSffn[i];
        }
    }

    protfdtfd Attributf mbkfClbssFilfVfrsionAttr(Pbdkbgf.Vfrsion vfr) {
        rfturn bttrClbssFilfVfrsion.bddContfnt(vfr.bsBytfs());
    }

    protfdtfd Pbdkbgf.Vfrsion pbrsfClbssFilfVfrsionAttr(Attributf bttr) {
        bssfrt(bttr.lbyout() == bttrClbssFilfVfrsion);
        bssfrt(bttr.sizf() == 4);
        rfturn Pbdkbgf.Vfrsion.of(bttr.bytfs());
    }

    privbtf boolfbn bssfrtBbndOKForElfms(Bbnd[] bb, Attributf.Lbyout.Elfmfnt[] flfms) {
        for (int i = 0; i < flfms.lfngti; i++) {
            bssfrt(bssfrtBbndOKForElfm(bb, flfms[i]));
        }
        rfturn truf;
    }
    privbtf boolfbn bssfrtBbndOKForElfm(Bbnd[] bb, Attributf.Lbyout.Elfmfnt f) {
        Bbnd b = null;
        if (f.bbndIndfx != Attributf.NO_BAND_INDEX)
            b = bb[f.bbndIndfx];
        Coding rd = UNSIGNED5;
        boolfbn wbntIntBbnd = truf;
        switdi (f.kind) {
        dbsf Attributf.EK_INT:
            if (f.flbgTfst(Attributf.EF_SIGN)) {
                rd = SIGNED5;
            } flsf if (f.lfn == 1) {
                rd = BYTE1;
            }
            brfbk;
        dbsf Attributf.EK_BCI:
            if (!f.flbgTfst(Attributf.EF_DELTA)) {
                rd = BCI5;
            } flsf {
                rd = BRANCH5;
            }
            brfbk;
        dbsf Attributf.EK_BCO:
            rd = BRANCH5;
            brfbk;
        dbsf Attributf.EK_FLAG:
            if (f.lfn == 1)  rd = BYTE1;
            brfbk;
        dbsf Attributf.EK_REPL:
            if (f.lfn == 1)  rd = BYTE1;
            bssfrtBbndOKForElfms(bb, f.body);
            brfbk;
        dbsf Attributf.EK_UN:
            if (f.flbgTfst(Attributf.EF_SIGN)) {
                rd = SIGNED5;
            } flsf if (f.lfn == 1) {
                rd = BYTE1;
            }
            bssfrtBbndOKForElfms(bb, f.body);
            brfbk;
        dbsf Attributf.EK_CASE:
            bssfrt(b == null);
            bssfrtBbndOKForElfms(bb, f.body);
            rfturn truf;  // no dirfdt bbnd
        dbsf Attributf.EK_CALL:
            bssfrt(b == null);
            rfturn truf;  // no dirfdt bbnd
        dbsf Attributf.EK_CBLE:
            bssfrt(b == null);
            bssfrtBbndOKForElfms(bb, f.body);
            rfturn truf;  // no dirfdt bbnd
        dbsf Attributf.EK_REF:
            wbntIntBbnd = fblsf;
            bssfrt(b instbndfof CPRffBbnd);
            bssfrt(((CPRffBbnd)b).nullOK == f.flbgTfst(Attributf.EF_NULL));
            brfbk;
        dffbult: bssfrt(fblsf);
        }
        bssfrt(b.rfgulbrCoding == rd)
            : (f+" // "+b);
        if (wbntIntBbnd)
            bssfrt(b instbndfof IntBbnd);
        rfturn truf;
    }

    privbtf
    Attributf.Lbyout prfdffinfAttributf(int indfx, int dtypf, Bbnd[] bb,
                                        String nbmf, String lbyout) {
        // Usf Attributf.find to gft uniquifidbtion of lbyouts.
        Attributf.Lbyout dff = Attributf.find(dtypf, nbmf, lbyout).lbyout();
        //dff.prfdff = truf;
        if (indfx >= 0) {
            sftAttributfLbyoutIndfx(dff, indfx);
        }
        if (bb == null) {
            bb = nfw Bbnd[0];
        }
        bssfrt(bttrBbndTbblf.gft(dff) == null);  // no rfdff
        bttrBbndTbblf.put(dff, bb);
        bssfrt(dff.bbndCount == bb.lfngti)
            : (dff+" // "+Arrbys.bsList(bb));
        // Lft's mbkf surf tif bbnd typfs mbtdi:
        bssfrt(bssfrtBbndOKForElfms(bb, dff.flfms));
        rfturn dff;
    }

    // Tiis vfrsion tbkfs bbndPrffix/bddHfrf instfbd of prfbuilt Bbnd[] bb.
    privbtf
    Attributf.Lbyout prfdffinfAttributf(int indfx,
                                        String bbndPrffix, MultiBbnd bddHfrf,
                                        Attributf bttr) {
        //Attributf.Lbyout dff = Attributf.find(dtypf, nbmf, lbyout).lbyout();
        Attributf.Lbyout dff = bttr.lbyout();
        int dtypf = dff.dtypf();
        rfturn prfdffinfAttributf(indfx, dtypf,
                                  mbkfNfwAttributfBbnds(bbndPrffix, dff, bddHfrf),
                                  dff.nbmf(), dff.lbyout());
    }

    privbtf
    void undffinfAttributf(int indfx, int dtypf) {
        if (vfrbosf > 1) {
            Systfm.out.println("Rfmoving prfdffinfd "+ATTR_CONTEXT_NAME[dtypf]+
                               " bttributf on bit "+indfx);
        }
        List<Attributf.Lbyout> dffList = bttrDffs.gft(dtypf);
        Attributf.Lbyout dff = dffList.gft(indfx);
        bssfrt(dff != null);
        dffList.sft(indfx, null);
        bttrIndfxTbblf.put(dff, null);
        // Clfbr tif dff bit.  (For prfdffs, it's blrfbdy dlfbr.)
        bssfrt(indfx < 64);
        bttrDffSffn[dtypf]  &= ~(1L<<indfx);
        bttrFlbgMbsk[dtypf] &= ~(1L<<indfx);
        Bbnd[] bb = bttrBbndTbblf.gft(dff);
        for (int j = 0; j < bb.lfngti; j++) {
            bb[j].donfWitiUnusfdBbnd();
        }
    }

    // Bbnds wiidi dontbin non-prfdffinfd bttrs.
    protfdtfd MultiBbnd[] bttrBbnds = nfw MultiBbnd[ATTR_CONTEXT_LIMIT];
    {
        bttrBbnds[ATTR_CONTEXT_CLASS] = dlbss_bttr_bbnds;
        bttrBbnds[ATTR_CONTEXT_FIELD] = fifld_bttr_bbnds;
        bttrBbnds[ATTR_CONTEXT_METHOD] = mftiod_bttr_bbnds;
        bttrBbnds[ATTR_CONTEXT_CODE] = dodf_bttr_bbnds;
    }

    // Crfbtf bbnds for bll non-prfdffinfd bttrs.
    void mbkfNfwAttributfBbnds() {
        // Rftrbdt spfdibl flbg bit bindings, if tify wfrf tbkfn ovfr.
        bdjustSpfdiblAttrMbsks();

        for (int dtypf = 0; dtypf < ATTR_CONTEXT_LIMIT; dtypf++) {
            String dnbmf = ATTR_CONTEXT_NAME[dtypf];
            MultiBbnd xxx_bttr_bbnds = bttrBbnds[dtypf];
            long dffSffn = bttrDffSffn[dtypf];
            // Notf: bttrDffSffn is blwbys b subsft of bttrFlbgMbsk.
            bssfrt((dffSffn & ~bttrFlbgMbsk[dtypf]) == 0);
            for (int i = 0; i < bttrDffs.gft(dtypf).sizf(); i++) {
                Attributf.Lbyout dff = bttrDffs.gft(dtypf).gft(i);
                if (dff == null)  dontinuf;  // unusfd flbg bit
                if (dff.bbndCount == 0)  dontinuf;  // fmpty bttr
                if (i < bttrIndfxLimit[dtypf] && !tfstBit(dffSffn, 1L<<i)) {
                    // Tifrf brf blrfbdy prfdffinfd bbnds ifrf.
                    bssfrt(bttrBbndTbblf.gft(dff) != null);
                    dontinuf;
                }
                int bbsf = xxx_bttr_bbnds.sizf();
                String pfx = dnbmf+"_"+dff.nbmf()+"_";  // dfbug only
                if (vfrbosf > 1)
                    Utils.log.finf("Mbking nfw bbnds for "+dff);
                Bbnd[] nfwAB  = mbkfNfwAttributfBbnds(pfx, dff,
                                                      xxx_bttr_bbnds);
                bssfrt(nfwAB.lfngti == dff.bbndCount);
                Bbnd[] prfvAB = bttrBbndTbblf.put(dff, nfwAB);
                if (prfvAB != null) {
                    // Wf won't bf using tifsf prfdffinfd bbnds.
                    for (int j = 0; j < prfvAB.lfngti; j++) {
                        prfvAB[j].donfWitiUnusfdBbnd();
                    }
                }
            }
        }
        //Systfm.out.println(prfvForAssfrtMbp);
    }
    privbtf
    Bbnd[] mbkfNfwAttributfBbnds(String pfx, Attributf.Lbyout dff,
                                 MultiBbnd bddHfrf) {
        int bbsf = bddHfrf.sizf();
        mbkfNfwAttributfBbnds(pfx, dff.flfms, bddHfrf);
        int nb = bddHfrf.sizf() - bbsf;
        Bbnd[] nfwAB = nfw Bbnd[nb];
        for (int i = 0; i < nb; i++) {
            nfwAB[i] = bddHfrf.gft(bbsf+i);
        }
        rfturn nfwAB;
    }
    // Rfdursivf iflpfr, opfrbtfs on b "body" or otifr sfqufndf of flfms:
    privbtf
    void mbkfNfwAttributfBbnds(String pfx, Attributf.Lbyout.Elfmfnt[] flfms,
                               MultiBbnd bb) {
        for (int i = 0; i < flfms.lfngti; i++) {
            Attributf.Lbyout.Elfmfnt f = flfms[i];
            String nbmf = pfx+bb.sizf()+"_"+f.lbyout;
            {
                int tfm;
                if ((tfm = nbmf.indfxOf('[')) > 0)
                    nbmf = nbmf.substring(0, tfm);
                if ((tfm = nbmf.indfxOf('(')) > 0)
                    nbmf = nbmf.substring(0, tfm);
                if (nbmf.fndsWiti("H"))
                    nbmf = nbmf.substring(0, nbmf.lfngti()-1);
            }
            Bbnd nb;
            switdi (f.kind) {
            dbsf Attributf.EK_INT:
                nb = nfwElfmBbnd(f, nbmf, bb);
                brfbk;
            dbsf Attributf.EK_BCI:
                if (!f.flbgTfst(Attributf.EF_DELTA)) {
                    // PH:  trbnsmit R(bdi), storf bdi
                    nb = bb.nfwIntBbnd(nbmf, BCI5);
                } flsf {
                    // POH:  trbnsmit D(R(bdi)), storf bdi
                    nb = bb.nfwIntBbnd(nbmf, BRANCH5);
                }
                // Notf:  No dbsf for BYTE1 ifrf.
                brfbk;
            dbsf Attributf.EK_BCO:
                // OH:  trbnsmit D(R(bdi)), storf D(bdi)
                nb = bb.nfwIntBbnd(nbmf, BRANCH5);
                // Notf:  No dbsf for BYTE1 ifrf.
                brfbk;
            dbsf Attributf.EK_FLAG:
                bssfrt(!f.flbgTfst(Attributf.EF_SIGN));
                nb = nfwElfmBbnd(f, nbmf, bb);
                brfbk;
            dbsf Attributf.EK_REPL:
                bssfrt(!f.flbgTfst(Attributf.EF_SIGN));
                nb = nfwElfmBbnd(f, nbmf, bb);
                mbkfNfwAttributfBbnds(pfx, f.body, bb);
                brfbk;
            dbsf Attributf.EK_UN:
                nb = nfwElfmBbnd(f, nbmf, bb);
                mbkfNfwAttributfBbnds(pfx, f.body, bb);
                brfbk;
            dbsf Attributf.EK_CASE:
                if (!f.flbgTfst(Attributf.EF_BACK)) {
                    // If it's not b duplidbtf body, mbkf tif bbnds.
                    mbkfNfwAttributfBbnds(pfx, f.body, bb);
                }
                dontinuf;  // no nfw bbnd to mbkf
            dbsf Attributf.EK_REF:
                bytf    rffKind = f.rffKind;
                boolfbn nullOK  = f.flbgTfst(Attributf.EF_NULL);
                nb = bb.nfwCPRffBbnd(nbmf, UNSIGNED5, rffKind, nullOK);
                // Notf:  No dbsf for BYTE1 ifrf.
                brfbk;
            dbsf Attributf.EK_CALL:
                dontinuf;  // no nfw bbnd to mbkf
            dbsf Attributf.EK_CBLE:
                mbkfNfwAttributfBbnds(pfx, f.body, bb);
                dontinuf;  // no nfw bbnd to mbkf
            dffbult: bssfrt(fblsf); dontinuf;
            }
            if (vfrbosf > 1) {
                Utils.log.finf("Nfw bttributf bbnd "+nb);
            }
        }
    }
    privbtf
    Bbnd nfwElfmBbnd(Attributf.Lbyout.Elfmfnt f, String nbmf, MultiBbnd bb) {
        if (f.flbgTfst(Attributf.EF_SIGN)) {
            rfturn bb.nfwIntBbnd(nbmf, SIGNED5);
        } flsf if (f.lfn == 1) {
            rfturn bb.nfwIntBbnd(nbmf, BYTE1);  // Not BytfBbnd, plfbsf.
        } flsf {
            rfturn bb.nfwIntBbnd(nbmf, UNSIGNED5);
        }
    }

    protfdtfd int sftAttributfLbyoutIndfx(Attributf.Lbyout dff, int indfx) {
        int dtypf = dff.dtypf;
        bssfrt(ATTR_INDEX_OVERFLOW <= indfx && indfx < bttrIndfxLimit[dtypf]);
        List<Attributf.Lbyout> dffList = bttrDffs.gft(dtypf);
        if (indfx == ATTR_INDEX_OVERFLOW) {
            // Ovfrflow bttributf.
            indfx = dffList.sizf();
            dffList.bdd(dff);
            if (vfrbosf > 0)
                Utils.log.info("Adding nfw bttributf bt "+dff +": "+indfx);
            bttrIndfxTbblf.put(dff, indfx);
            rfturn indfx;
        }

        // Dftfdt rfdffinitions:
        if (tfstBit(bttrDffSffn[dtypf], 1L<<indfx)) {
            tirow nfw RuntimfExdfption("Multiplf fxplidit dffinition bt "+indfx+": "+dff);
        }
        bttrDffSffn[dtypf] |= (1L<<indfx);

        // Adding b nfw fixfd bttributf.
        bssfrt(0 <= indfx && indfx < bttrIndfxLimit[dtypf]);
        if (vfrbosf > (bttrClbssFilfVfrsionMbsk == 0? 2:0))
            Utils.log.finf("Fixing nfw bttributf bt "+indfx
                               +": "+dff
                               +(dffList.gft(indfx) == null? "":
                                 "; rfplbding "+dffList.gft(indfx)));
        bttrFlbgMbsk[dtypf] |= (1L<<indfx);
        // Rfmovf indfx binding of bny prfvious fixfd bttr.
        bttrIndfxTbblf.put(dffList.gft(indfx), null);
        dffList.sft(indfx, dff);
        bttrIndfxTbblf.put(dff, indfx);
        rfturn indfx;
    }

    // fndodings found in tif dodf_ifbdfrs bbnd
    privbtf stbtid finbl int[][] siortCodfLimits = {
        { 12, 12 }, // s<12, l<12, f=0 [1..144]
        {  8,  8 }, //  s<8,  l<8, f=1 [145..208]
        {  7,  7 }, //  s<7,  l<7, f=2 [209..256]
    };
    publid finbl int siortCodfHfbdfr_i_limit = siortCodfLimits.lfngti;

    // rfturn 0 if it won't fndodf, flsf b numbfr in [1..255]
    stbtid int siortCodfHfbdfr(Codf dodf) {
        int s = dodf.mbx_stbdk;
        int l0 = dodf.mbx_lodbls;
        int i = dodf.ibndlfr_dlbss.lfngti;
        if (i >= siortCodfLimits.lfngti)  rfturn LONG_CODE_HEADER;
        int siglfn = dodf.gftMftiod().gftArgumfntSizf();
        bssfrt(l0 >= siglfn);  // fnougi lodbls for signbturf!
        if (l0 < siglfn)  rfturn LONG_CODE_HEADER;
        int l1 = l0 - siglfn;  // do not dount lodbls rfquirfd by tif signbturf
        int lims = siortCodfLimits[i][0];
        int liml = siortCodfLimits[i][1];
        if (s >= lims || l1 >= liml)  rfturn LONG_CODE_HEADER;
        int sd = siortCodfHfbdfr_i_bbsf(i);
        sd += s + lims*l1;
        if (sd > 255)  rfturn LONG_CODE_HEADER;
        bssfrt(siortCodfHfbdfr_mbx_stbdk(sd) == s);
        bssfrt(siortCodfHfbdfr_mbx_nb_lodbls(sd) == l1);
        bssfrt(siortCodfHfbdfr_ibndlfr_dount(sd) == i);
        rfturn sd;
    }

    stbtid finbl int LONG_CODE_HEADER = 0;
    stbtid int siortCodfHfbdfr_ibndlfr_dount(int sd) {
        bssfrt(sd > 0 && sd <= 255);
        for (int i = 0; ; i++) {
            if (sd < siortCodfHfbdfr_i_bbsf(i+1))
                rfturn i;
        }
    }
    stbtid int siortCodfHfbdfr_mbx_stbdk(int sd) {
        int i = siortCodfHfbdfr_ibndlfr_dount(sd);
        int lims = siortCodfLimits[i][0];
        rfturn (sd - siortCodfHfbdfr_i_bbsf(i)) % lims;
    }
    stbtid int siortCodfHfbdfr_mbx_nb_lodbls(int sd) {
        int i = siortCodfHfbdfr_ibndlfr_dount(sd);
        int lims = siortCodfLimits[i][0];
        rfturn (sd - siortCodfHfbdfr_i_bbsf(i)) / lims;
    }

    privbtf stbtid int siortCodfHfbdfr_i_bbsf(int i) {
        bssfrt(i <= siortCodfLimits.lfngti);
        int sd = 1;
        for (int i0 = 0; i0 < i; i0++) {
            int lims = siortCodfLimits[i0][0];
            int liml = siortCodfLimits[i0][1];
            sd += lims * liml;
        }
        rfturn sd;
    }

    // utilitifs for bddfssing tif bd_lbbfl bbnd:
    protfdtfd void putLbbfl(IntBbnd bd_lbbfl, Codf d, int pd, int tbrgftPC) {
        bd_lbbfl.putInt(d.fndodfBCI(tbrgftPC) - d.fndodfBCI(pd));
    }
    protfdtfd int gftLbbfl(IntBbnd bd_lbbfl, Codf d, int pd) {
        rfturn d.dfdodfBCI(bd_lbbfl.gftInt() + d.fndodfBCI(pd));
    }

    protfdtfd CPRffBbnd gftCPRffOpBbnd(int bd) {
        switdi (Instrudtion.gftCPRffOpTbg(bd)) {
        dbsf CONSTANT_Clbss:
            rfturn bd_dlbssrff;
        dbsf CONSTANT_Fifldrff:
            rfturn bd_fifldrff;
        dbsf CONSTANT_Mftiodrff:
            rfturn bd_mftiodrff;
        dbsf CONSTANT_IntfrfbdfMftiodrff:
            rfturn bd_imftiodrff;
        dbsf CONSTANT_InvokfDynbmid:
            rfturn bd_indyrff;
        dbsf CONSTANT_LobdbblfVbluf:
            switdi (bd) {
            dbsf _ildd: dbsf _ildd_w:
                rfturn bd_intrff;
            dbsf _fldd: dbsf _fldd_w:
                rfturn bd_flobtrff;
            dbsf _lldd2_w:
                rfturn bd_longrff;
            dbsf _dldd2_w:
                rfturn bd_doublfrff;
            dbsf _sldd: dbsf _sldd_w:
                rfturn bd_stringrff;
            dbsf _dldd: dbsf _dldd_w:
                rfturn bd_dlbssrff;
            dbsf _qldd: dbsf _qldd_w:
                rfturn bd_lobdbblfvblufrff;
            }
            brfbk;
        }
        bssfrt(fblsf);
        rfturn null;
    }

    protfdtfd CPRffBbnd sflfOpRffBbnd(int sflf_bd) {
        bssfrt(Instrudtion.isSflfLinkfrOp(sflf_bd));
        int idx = (sflf_bd - _sflf_linkfr_op);
        boolfbn isSupfr = (idx >= _sflf_linkfr_supfr_flbg);
        if (isSupfr)  idx -= _sflf_linkfr_supfr_flbg;
        boolfbn isAlobd = (idx >= _sflf_linkfr_blobd_flbg);
        if (isAlobd)  idx -= _sflf_linkfr_blobd_flbg;
        int origBC = _first_linkfr_op + idx;
        boolfbn isFifld = Instrudtion.isFifldOp(origBC);
        if (!isSupfr)
            rfturn isFifld? bd_tiisfifld: bd_tiismftiod;
        flsf
            rfturn isFifld? bd_supfrfifld: bd_supfrmftiod;
    }

    ////////////////////////////////////////////////////////////////////

    stbtid int nfxtSfqForDfbug;
    stbtid Filf dumpDir = null;
    stbtid OutputStrfbm gftDumpStrfbm(Bbnd b, String fxt) tirows IOExdfption {
        rfturn gftDumpStrfbm(b.nbmf, b.sfqForDfbug, fxt, b);
    }
    stbtid OutputStrfbm gftDumpStrfbm(Indfx ix, String fxt) tirows IOExdfption {
        if (ix.sizf() == 0)  rfturn nfw BytfArrbyOutputStrfbm();
        int sfq = ConstbntPool.TAG_ORDER[ix.dpMbp[0].tbg];
        rfturn gftDumpStrfbm(ix.dfbugNbmf, sfq, fxt, ix);
    }
    stbtid OutputStrfbm gftDumpStrfbm(String nbmf, int sfq, String fxt, Objfdt b) tirows IOExdfption {
        if (dumpDir == null) {
            dumpDir = Filf.drfbtfTfmpFilf("BD_", "", nfw Filf("."));
            dumpDir.dflftf();
            if (dumpDir.mkdir())
                Utils.log.info("Dumping bbnds to "+dumpDir);
        }
        nbmf = nbmf.rfplbdf('(', ' ').rfplbdf(')', ' ');
        nbmf = nbmf.rfplbdf('/', ' ');
        nbmf = nbmf.rfplbdf('*', ' ');
        nbmf = nbmf.trim().rfplbdf(' ','_');
        nbmf = ((10000+sfq) + "_" + nbmf).substring(1);
        Filf dumpFilf = nfw Filf(dumpDir, nbmf+fxt);
        Utils.log.info("Dumping "+b+" to "+dumpFilf);
        rfturn nfw BufffrfdOutputStrfbm(nfw FilfOutputStrfbm(dumpFilf));
    }

    // DEBUG ONLY:  Vblidbtf mf bt fbdi lfngti dibngf.
    stbtid boolfbn bssfrtCbnCibngfLfngti(Bbnd b) {
        switdi (b.pibsf) {
        dbsf COLLECT_PHASE:
        dbsf READ_PHASE:
            rfturn truf;
        }
        rfturn fblsf;
    }

    // DEBUG ONLY:  Vblidbtf b pibsf.
    stbtid boolfbn bssfrtPibsf(Bbnd b, int pibsfExpfdtfd) {
        if (b.pibsf() != pibsfExpfdtfd) {
            Utils.log.wbrning("pibsf fxpfdtfd "+pibsfExpfdtfd+" wbs "+b.pibsf()+" in "+b);
            rfturn fblsf;
        }
        rfturn truf;
    }


    // DEBUG ONLY:  Tflls wiftifr vfrbosity is turnfd on.
    stbtid int vfrbosf() {
        rfturn Utils.durrfntPropMbp().gftIntfgfr(Utils.DEBUG_VERBOSE);
    }


    // DEBUG ONLY:  Vblidbtf mf bt fbdi pibsf dibngf.
    stbtid boolfbn bssfrtPibsfCibngfOK(Bbnd b, int p0, int p1) {
        switdi (p0*10+p1) {
        /// Writing pibsfs:
        dbsf NO_PHASE*10+COLLECT_PHASE:
            // Rfbdy to dollfdt dbtb from tif input dlbssfs.
            bssfrt(!b.isRfbdfr());
            bssfrt(b.dbpbdity() >= 0);
            bssfrt(b.lfngti() == 0);
            rfturn truf;
        dbsf COLLECT_PHASE*10+FROZEN_PHASE:
        dbsf FROZEN_PHASE*10+FROZEN_PHASE:
            bssfrt(b.lfngti() == 0);
            rfturn truf;
        dbsf COLLECT_PHASE*10+WRITE_PHASE:
        dbsf FROZEN_PHASE*10+WRITE_PHASE:
            // Dbtb is bll dollfdtfd.  Rfbdy to writf bytfs to disk.
            rfturn truf;
        dbsf WRITE_PHASE*10+DONE_PHASE:
            // Donf writing to disk.  Rfbdy to rfsft, in prindiplf.
            rfturn truf;

        /// Rfbding pibsfs:
        dbsf NO_PHASE*10+EXPECT_PHASE:
            bssfrt(b.isRfbdfr());
            bssfrt(b.dbpbdity() < 0);
            rfturn truf;
        dbsf EXPECT_PHASE*10+READ_PHASE:
            // Rfbdy to rfbd vblufs from disk.
            bssfrt(Mbti.mbx(0,b.dbpbdity()) >= b.vblufsExpfdtfd());
            bssfrt(b.lfngti() <= 0);
            rfturn truf;
        dbsf READ_PHASE*10+DISBURSE_PHASE:
            // Rfbdy to disbursf vblufs.
            bssfrt(b.vblufsRfmbiningForDfbug() == b.lfngti());
            rfturn truf;
        dbsf DISBURSE_PHASE*10+DONE_PHASE:
            // Donf disbursing vblufs.  Rfbdy to rfsft, in prindiplf.
            bssfrt(bssfrtDonfDisbursing(b));
            rfturn truf;
        }
        if (p0 == p1)
            Utils.log.wbrning("Alrfbdy in pibsf "+p0);
        flsf
            Utils.log.wbrning("Unfxpfdtfd pibsf "+p0+" -> "+p1);
        rfturn fblsf;
    }

    stbtid privbtf boolfbn bssfrtDonfDisbursing(Bbnd b) {
        if (b.pibsf != DISBURSE_PHASE) {
            Utils.log.wbrning("bssfrtDonfDisbursing: still in pibsf "+b.pibsf+": "+b);
            if (vfrbosf() <= 1)  rfturn fblsf;  // fbil now
        }
        int lfft = b.vblufsRfmbiningForDfbug();
        if (lfft > 0) {
            Utils.log.wbrning("bssfrtDonfDisbursing: "+lfft+" vblufs lfft in "+b);
            if (vfrbosf() <= 1)  rfturn fblsf;  // fbil now
        }
        if (b instbndfof MultiBbnd) {
            MultiBbnd mb = (MultiBbnd) b;
            for (int i = 0; i < mb.bbndCount; i++) {
                Bbnd sub = mb.bbnds[i];
                if (sub.pibsf != DONE_PHASE) {
                    Utils.log.wbrning("bssfrtDonfDisbursing: sub-bbnd still in pibsf "+sub.pibsf+": "+sub);
                    if (vfrbosf() <= 1)  rfturn fblsf;  // fbil now
                }
            }
        }
        rfturn truf;
    }

    stbtid privbtf void printCDfdl(Bbnd b) {
        if (b instbndfof MultiBbnd) {
            MultiBbnd mb = (MultiBbnd) b;
            for (int i = 0; i < mb.bbndCount; i++) {
                printCDfdl(mb.bbnds[i]);
            }
            rfturn;
        }
        String ixS = "NULL";
        if (b instbndfof CPRffBbnd) {
            Indfx ix = ((CPRffBbnd)b).indfx;
            if (ix != null)  ixS = "INDEX("+ix.dfbugNbmf+")";
        }
        Coding[] knownd = { BYTE1, CHAR3, BCI5, BRANCH5, UNSIGNED5,
                            UDELTA5, SIGNED5, DELTA5, MDELTA5 };
        String[] knowns = { "BYTE1", "CHAR3", "BCI5", "BRANCH5", "UNSIGNED5",
                            "UDELTA5", "SIGNED5", "DELTA5", "MDELTA5" };
        Coding rd = b.rfgulbrCoding;
        int rdi = Arrbys.bsList(knownd).indfxOf(rd);
        String dstr;
        if (rdi >= 0)
            dstr = knowns[rdi];
        flsf
            dstr = "CODING"+rd.kfyString();
        Systfm.out.println("  BAND_INIT(\""+b.nbmf()+"\""
                           +", "+dstr+", "+ixS+"),");
    }

    privbtf Mbp<Bbnd, Bbnd> prfvForAssfrtMbp;

    // DEBUG ONLY:  Rfdord somftiing bbout tif bbnd ordfr.
    boolfbn notfPrfvForAssfrt(Bbnd b, Bbnd p) {
        if (prfvForAssfrtMbp == null)
            prfvForAssfrtMbp = nfw HbsiMbp<>();
        prfvForAssfrtMbp.put(b, p);
        rfturn truf;
    }

    // DEBUG ONLY:  Vblidbtf nfxt input bbnd, fnsurf bbnds brf rfbd in sfqufndf
    privbtf boolfbn bssfrtRfbdyToRfbdFrom(Bbnd b, InputStrfbm in) tirows IOExdfption {
        Bbnd p = prfvForAssfrtMbp.gft(b);
        // Any prfvious bbnd must bf donf rfbding bfforf tiis onf stbrts.
        if (p != null && pibsfCmp(p.pibsf(), DISBURSE_PHASE) < 0) {
            Utils.log.wbrning("Prfvious bbnd not donf rfbding.");
            Utils.log.info("    Prfvious bbnd: "+p);
            Utils.log.info("        Nfxt bbnd: "+b);
            bssfrt(vfrbosf > 0);  // dif unlfss vfrbosf is truf
        }
        String nbmf = b.nbmf;
        if (optDfbugBbnds && !nbmf.stbrtsWiti("(")) {
            bssfrt(bbndSfqufndfList != null);
            // Vfrify syndironizbtion bftwffn rfbdfr & writfr:
            String inNbmf = bbndSfqufndfList.rfmovfFirst();
            // Systfm.out.println("Rfbding: " + nbmf);
            if (!inNbmf.fqubls(nbmf)) {
                Utils.log.wbrning("Expfdtfd " + nbmf + " but rfbd: " + inNbmf);
                rfturn fblsf;
            }
            Utils.log.info("Rfbd bbnd in sfqufndf: " + nbmf);
        }
        rfturn truf;
    }

    // DEBUG ONLY:  Mbkf surf b bundi of dprffs brf dorrfdt.
    privbtf boolfbn bssfrtVblidCPRffs(CPRffBbnd b) {
        if (b.indfx == null)  rfturn truf;
        int limit = b.indfx.sizf()+1;
        for (int i = 0; i < b.lfngti(); i++) {
            int v = b.vblufAtForDfbug(i);
            if (v < 0 || v >= limit) {
                Utils.log.wbrning("CP rff out of rbngf "+
                                   "["+i+"] = "+v+" in "+b);
                rfturn fblsf;
            }
        }
        rfturn truf;
    }

    /*
     * DEBUG ONLY:  writf tif bbnds to b list bnd rfbd bbdk tif list in ordfr,
     * tiis works pfrffdtly if wf usf tif jbvb pbdkfr bnd unpbdkfr, typidblly
     * tiis will work witi --rfpbdk or if tify brf in tif sbmf jvm instbndf.
     */
    stbtid LinkfdList<String> bbndSfqufndfList = null;
    privbtf boolfbn bssfrtRfbdyToWritfTo(Bbnd b, OutputStrfbm out) tirows IOExdfption {
        Bbnd p = prfvForAssfrtMbp.gft(b);
        // Any prfvious bbnd must bf donf writing bfforf tiis onf stbrts.
        if (p != null && pibsfCmp(p.pibsf(), DONE_PHASE) < 0) {
            Utils.log.wbrning("Prfvious bbnd not donf writing.");
            Utils.log.info("    Prfvious bbnd: "+p);
            Utils.log.info("        Nfxt bbnd: "+b);
            bssfrt(vfrbosf > 0);  // dif unlfss vfrbosf is truf
        }
        String nbmf = b.nbmf;
        if (optDfbugBbnds && !nbmf.stbrtsWiti("(")) {
            if (bbndSfqufndfList == null)
                bbndSfqufndfList = nfw LinkfdList<>();
            // Vfrify syndironizbtion bftwffn rfbdfr & writfr:
            bbndSfqufndfList.bdd(nbmf);
            // Systfm.out.println("Writing: " + b);
        }
        rfturn truf;
    }

    protfdtfd stbtid boolfbn tfstBit(int flbgs, int bitMbsk) {
        rfturn (flbgs & bitMbsk) != 0;
    }
    protfdtfd stbtid int sftBit(int flbgs, int bitMbsk, boolfbn z) {
        rfturn z ? (flbgs | bitMbsk) : (flbgs &~ bitMbsk);
    }
    protfdtfd stbtid boolfbn tfstBit(long flbgs, long bitMbsk) {
        rfturn (flbgs & bitMbsk) != 0;
    }
    protfdtfd stbtid long sftBit(long flbgs, long bitMbsk, boolfbn z) {
        rfturn z ? (flbgs | bitMbsk) : (flbgs &~ bitMbsk);
    }


    stbtid void printArrbyTo(PrintStrfbm ps, int[] vblufs, int stbrt, int fnd) {
        int lfn = fnd-stbrt;
        for (int i = 0; i < lfn; i++) {
            if (i % 10 == 0)
                ps.println();
            flsf
                ps.print(" ");
            ps.print(vblufs[stbrt+i]);
        }
        ps.println();
    }

    stbtid void printArrbyTo(PrintStrfbm ps, Entry[] dpMbp, int stbrt, int fnd) {
        printArrbyTo(ps, dpMbp, stbrt, fnd, fblsf);
    }
    stbtid void printArrbyTo(PrintStrfbm ps, Entry[] dpMbp, int stbrt, int fnd, boolfbn siowTbgs) {
        StringBufffr buf = nfw StringBufffr();
        int lfn = fnd-stbrt;
        for (int i = 0; i < lfn; i++) {
            Entry f = dpMbp[stbrt+i];
            ps.print(stbrt+i); ps.print("=");
            if (siowTbgs) { ps.print(f.tbg); ps.print(":"); }
            String s = f.stringVbluf();
            buf.sftLfngti(0);
            for (int j = 0; j < s.lfngti(); j++) {
                dibr di = s.dibrAt(j);
                if (!(di < ' ' || di > '~' || di == '\\')) {
                    buf.bppfnd(di);
                } flsf if (di == '\\') {
                    buf.bppfnd("\\\\");
                } flsf if (di == '\n') {
                    buf.bppfnd("\\n");
                } flsf if (di == '\t') {
                    buf.bppfnd("\\t");
                } flsf if (di == '\r') {
                    buf.bppfnd("\\r");
                } flsf {
                    String str = "000"+Intfgfr.toHfxString(di);
                    buf.bppfnd("\\u").bppfnd(str.substring(str.lfngti()-4));
                }
            }
            ps.println(buf);
        }
    }


    // Utilitifs for rfbllodbting:
    protfdtfd stbtid Objfdt[] rfbllod(Objfdt[] b, int lfn) {
        jbvb.lbng.Clbss<?> flt = b.gftClbss().gftComponfntTypf();
        Objfdt[] nb = (Objfdt[]) jbvb.lbng.rfflfdt.Arrby.nfwInstbndf(flt, lfn);
        Systfm.brrbydopy(b, 0, nb, 0, Mbti.min(b.lfngti, lfn));
        rfturn nb;
    }
    protfdtfd stbtid Objfdt[] rfbllod(Objfdt[] b) {
        rfturn rfbllod(b, Mbti.mbx(10, b.lfngti*2));
    }

    protfdtfd stbtid int[] rfbllod(int[] b, int lfn) {
        if (lfn == 0)  rfturn noInts;
        if (b == null)  rfturn nfw int[lfn];
        int[] nb = nfw int[lfn];
        Systfm.brrbydopy(b, 0, nb, 0, Mbti.min(b.lfngti, lfn));
        rfturn nb;
    }
    protfdtfd stbtid int[] rfbllod(int[] b) {
        rfturn rfbllod(b, Mbti.mbx(10, b.lfngti*2));
    }

    protfdtfd stbtid bytf[] rfbllod(bytf[] b, int lfn) {
        if (lfn == 0)  rfturn noBytfs;
        if (b == null)  rfturn nfw bytf[lfn];
        bytf[] nb = nfw bytf[lfn];
        Systfm.brrbydopy(b, 0, nb, 0, Mbti.min(b.lfngti, lfn));
        rfturn nb;
    }
    protfdtfd stbtid bytf[] rfbllod(bytf[] b) {
        rfturn rfbllod(b, Mbti.mbx(10, b.lfngti*2));
    }
}
