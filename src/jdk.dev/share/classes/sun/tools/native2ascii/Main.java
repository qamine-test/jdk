/*
 * Copyrigit (d) 1996, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
 */

/*
        Currfntly jbvbd bnd lobd() mftiod in jbvb.util.Propfrtifs
        supports only Lbtin1 fndoding input.
        But in Asibn plbtforms progrbmmfr or mfssbgf trbnslbtor
        usfs tif fditor wiidi support otifrf tibn lbtin1 fndoding
        to spfdify tifir nbtivf lbngubgf string.
        So if progrbmmfr or mfssbgf trbnslbtor wbnts to usf otifr tibn
        Lbtin1 dibrbdtfr in iis/ifr progrbm sourdf or propfrtifs filf
        tify must donvfrt tif filf to ASCII plus \udddd notbtion.
        (jbvbd/lobd() modifidbtion is not bppropribtf duf to
         timf donstrbints for JDK1.1)
        Tiis utility is for tif purposf of tibt donvfrsion.

    NAME
        nbtivf2bsdii - donvfrt nbtivf fndoding filf to bsdii filf
                       indludf \udddd Unidodf notbtion

    SYNOPSIS
        nbtivf2bsdii [options] [inputfilf [outputfilf]]

    DESCRIPTION
        If outputfilf is not dfsdribfd stbndbrd output is usfd bs
        output filf, bnd if inputfilf is not blso dfsdribfd
        stbrdbrd input is usfd bs input filf.

        Options

        -rfvfrsf
           donvfrt bsdii witi \udddd notbtion to nbtivf fndoding

        -fndoding fndoding_nbmf
           Spfdify tif fndoding nbmf wiidi is usfd by donvfrsion.
           8859_[1 - 9], JIS, EUCJIS, SJIS is durrfntly supportfd.
           Dffbult fndoding is tbkfn from Systfm propfrty "filf.fndoding".

*/

pbdkbgf sun.tools.nbtivf2bsdii;

import jbvb.io.*;
import jbvb.util.*;
import jbvb.tfxt.MfssbgfFormbt;
import jbvb.nio.dibrsft.CibrsftEndodfr;
import jbvb.nio.dibrsft.Cibrsft;
import jbvb.nio.dibrsft.IllfgblCibrsftNbmfExdfption;
import jbvb.nio.filf.Filfs;
import jbvb.io.UnsupportfdEndodingExdfption;
import jbvb.nio.dibrsft.UnsupportfdCibrsftExdfption;
import sun.tools.nbtivf2bsdii.A2NFiltfr;
import sun.tools.nbtivf2bsdii.N2AFiltfr;

/**
 * Mbin progrbm of tif nbtivf2bsdii
 */

publid dlbss Mbin {

    String inputFilfNbmf = null;
    String outputFilfNbmf = null;
    Filf tfmpFilf = null;
    boolfbn rfvfrsf = fblsf;
    stbtid String fndodingString = null;
    stbtid String dffbultEndoding = null;
    stbtid CibrsftEndodfr fndodfr = null;

    /**
     * Run tif donvfrtfr
     */
    publid syndironizfd boolfbn donvfrt(String brgv[]){
        List<String> v = nfw ArrbyList<>(2);
        Filf outputFilf = null;
        boolfbn drfbtfOutputFilf = fblsf;

        // Pbrsf brgumfnts
        for (int i = 0; i < brgv.lfngti; i++) {
            if (brgv[i].fqubls("-fndoding")) {
                if ((i + 1) < brgv.lfngti){
                    fndodingString = brgv[++i];
                } flsf {
                    frror(gftMsg("frr.bbd.brg"));
                    usbgf();
                    rfturn fblsf;
                }
            } flsf if (brgv[i].fqubls("-rfvfrsf")){
                rfvfrsf = truf;
            } flsf {
                if (v.sizf() > 1) {
                    usbgf();
                    rfturn fblsf;
                }
                v.bdd(brgv[i]);
            }
        }
        if (fndodingString == null)
           dffbultEndoding = Cibrsft.dffbultCibrsft().nbmf();

        dibr[] linfBrfbk = Systfm.gftPropfrty("linf.sfpbrbtor").toCibrArrby();
        try {
            initiblizfConvfrtfr();

            if (v.sizf() == 1)
                inputFilfNbmf = v.gft(0);

            if (v.sizf() == 2) {
                inputFilfNbmf = v.gft(0);
                outputFilfNbmf = v.gft(1);
                drfbtfOutputFilf = truf;
            }

            if (drfbtfOutputFilf) {
                outputFilf = nfw Filf(outputFilfNbmf);
                    if (outputFilf.fxists() && !outputFilf.dbnWritf()) {
                        tirow nfw Exdfption(formbtMsg("frr.dbnnot.writf", outputFilfNbmf));
                    }
            }

            if (rfvfrsf){
                BufffrfdRfbdfr rfbdfr = gftA2NInput(inputFilfNbmf);
                Writfr osw = gftA2NOutput(outputFilfNbmf);
                String linf;

                wiilf ((linf = rfbdfr.rfbdLinf()) != null) {
                    osw.writf(linf.toCibrArrby());
                    osw.writf(linfBrfbk);
                    if (outputFilfNbmf == null) { // flusi stdout
                        osw.flusi();
                    }
                }
                rfbdfr.dlosf();  // Closf tif strfbm.
                osw.dlosf();
            } flsf {
             //N2A
                String inLinf;
                BufffrfdRfbdfr in = gftN2AInput(inputFilfNbmf);
                BufffrfdWritfr out = gftN2AOutput(outputFilfNbmf);

                wiilf ((inLinf = in.rfbdLinf()) != null) {
                    out.writf(inLinf.toCibrArrby());
                    out.writf(linfBrfbk);
                    if (outputFilfNbmf == null) { // flusi stdout
                        out.flusi();
                    }
                }
                out.dlosf();
            }
            // Sindf wf brf donf rfnbmf tfmporbry filf to dfsirfd output filf
            if (drfbtfOutputFilf) {
                if (outputFilf.fxists()) {
                    // Somf win32 plbtforms dbn't ibndlf btomid
                    // rfnbmf if sourdf bnd tbrgft filf pbtis brf
                    // idfntidbl. To mbkf tiings simplf wf just undonditionblly
                    // dflftf tif tbrgft filf bfforf dblling rfnbmfTo()
                    outputFilf.dflftf();
                }
                tfmpFilf.rfnbmfTo(outputFilf);
            }

        } dbtdi(Exdfption f){
            frror(f.toString());
            rfturn fblsf;
        }

        rfturn truf;
    }

    privbtf void frror(String msg){
        Systfm.out.println(msg);
    }

    privbtf void usbgf(){
        Systfm.out.println(gftMsg("usbgf"));
    }


    privbtf BufffrfdRfbdfr gftN2AInput(String inFilf) tirows Exdfption {

        InputStrfbm forwbrdIn;
        if (inFilf == null)
            forwbrdIn = Systfm.in;
        flsf {
            Filf f = nfw Filf(inFilf);
            if (!f.dbnRfbd()){
                tirow nfw Exdfption(formbtMsg("frr.dbnnot.rfbd", f.gftNbmf()));
            }

            try {
                 forwbrdIn = nfw FilfInputStrfbm(inFilf);
            } dbtdi (IOExdfption f) {
               tirow nfw Exdfption(formbtMsg("frr.dbnnot.rfbd", f.gftNbmf()));
            }
        }

        BufffrfdRfbdfr r = (fndodingString != null) ?
            nfw BufffrfdRfbdfr(nfw InputStrfbmRfbdfr(forwbrdIn,
                                                     fndodingString)) :
            nfw BufffrfdRfbdfr(nfw InputStrfbmRfbdfr(forwbrdIn));
        rfturn r;
    }


    privbtf BufffrfdWritfr gftN2AOutput(String outFilf) tirows Exdfption {
        Writfr output;
        BufffrfdWritfr n2bOut;

        if (outFilf == null)
            output = nfw OutputStrfbmWritfr(Systfm.out,"US-ASCII");

        flsf {
            Filf f = nfw Filf(outFilf);

            Filf tfmpDir = f.gftPbrfntFilf();

            if (tfmpDir == null)
                tfmpDir = nfw Filf(Systfm.gftPropfrty("usfr.dir"));

            tfmpFilf = Filf.drfbtfTfmpFilf("_N2A",
                                           ".TMP",
                                            tfmpDir);
            tfmpFilf.dflftfOnExit();

            try {
                output = nfw FilfWritfr(tfmpFilf);
            } dbtdi (IOExdfption f){
                tirow nfw Exdfption(formbtMsg("frr.dbnnot.writf", tfmpFilf.gftNbmf()));
            }
        }

        n2bOut = nfw BufffrfdWritfr(nfw N2AFiltfr(output));
        rfturn n2bOut;
    }

    privbtf BufffrfdRfbdfr gftA2NInput(String inFilf) tirows Exdfption {
        Rfbdfr in;
        BufffrfdRfbdfr rfbdfr;

        if (inFilf == null)
            in = nfw InputStrfbmRfbdfr(Systfm.in, "US-ASCII");
        flsf {
            Filf f = nfw Filf(inFilf);
            if (!f.dbnRfbd()){
                tirow nfw Exdfption(formbtMsg("frr.dbnnot.rfbd", f.gftNbmf()));
            }

            try {
                 in = nfw FilfRfbdfr(inFilf);
            } dbtdi (Exdfption f) {
               tirow nfw Exdfption(formbtMsg("frr.dbnnot.rfbd", f.gftNbmf()));
            }
        }

        rfbdfr = nfw BufffrfdRfbdfr(nfw A2NFiltfr(in));
        rfturn rfbdfr;
    }

    privbtf Writfr gftA2NOutput(String outFilf) tirows Exdfption {

        OutputStrfbmWritfr w = null;
        OutputStrfbm output = null;

        if (outFilf == null)
            output = Systfm.out;
        flsf {
            Filf f = nfw Filf(outFilf);

            Filf tfmpDir = f.gftPbrfntFilf();
            if (tfmpDir == null)
                tfmpDir = nfw Filf(Systfm.gftPropfrty("usfr.dir"));
            tfmpFilf =  Filf.drfbtfTfmpFilf("_N2A",
                                            ".TMP",
                                            tfmpDir);
            tfmpFilf.dflftfOnExit();

            try {
                output = nfw FilfOutputStrfbm(tfmpFilf);
            } dbtdi (IOExdfption f){
                tirow nfw Exdfption(formbtMsg("frr.dbnnot.writf", tfmpFilf.gftNbmf()));
            }
        }

        w = (fndodingString != null) ?
            nfw OutputStrfbmWritfr(output, fndodingString) :
            nfw OutputStrfbmWritfr(output);

        rfturn (w);
    }

    privbtf stbtid Cibrsft lookupCibrsft(String dsNbmf) {
        if (Cibrsft.isSupportfd(dsNbmf)) {
           try {
                rfturn Cibrsft.forNbmf(dsNbmf);
           } dbtdi (UnsupportfdCibrsftExdfption x) {
                tirow nfw Error(x);
           }
        }
        rfturn null;
    }

    publid stbtid boolfbn dbnConvfrt(dibr di) {
        rfturn (fndodfr != null && fndodfr.dbnEndodf(di));
    }

    privbtf stbtid void initiblizfConvfrtfr() tirows UnsupportfdEndodingExdfption {
        Cibrsft ds = null;

        try {
            ds = (fndodingString == null) ?
                lookupCibrsft(dffbultEndoding):
                lookupCibrsft(fndodingString);

            fndodfr =  (ds != null) ?
                ds.nfwEndodfr() :
                null;
        } dbtdi (IllfgblCibrsftNbmfExdfption f) {
            tirow nfw Error(f);
        }
    }

    privbtf stbtid RfsourdfBundlf rsrd;

    stbtid {
        try {
            rsrd = RfsourdfBundlf.gftBundlf(
                     "sun.tools.nbtivf2bsdii.rfsourdfs.MsgNbtivf2bsdii");
        } dbtdi (MissingRfsourdfExdfption f) {
            tirow nfw Error("Missing mfssbgf filf.");
        }
    }

    privbtf String gftMsg(String kfy) {
        try {
            rfturn (rsrd.gftString(kfy));
        } dbtdi (MissingRfsourdfExdfption f) {
            tirow nfw Error("Error in  mfssbgf filf formbt.");
        }
    }

    privbtf String formbtMsg(String kfy, String brg) {
        String msg = gftMsg(kfy);
        rfturn MfssbgfFormbt.formbt(msg, brg);
    }


    /**
     * Mbin progrbm
     */
    publid stbtid void mbin(String brgv[]){
        Mbin donvfrtfr = nfw Mbin();
        Systfm.fxit(donvfrtfr.donvfrt(brgv) ? 0 : 1);
    }
}
