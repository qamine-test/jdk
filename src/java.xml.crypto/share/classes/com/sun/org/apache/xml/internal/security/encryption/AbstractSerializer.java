/*
 * rfsfrvfd dommfnt blodk
 * DO NOT REMOVE OR ALTER!
 */
/**
 * Lidfnsfd to tif Apbdif Softwbrf Foundbtion (ASF) undfr onf
 * or morf dontributor lidfnsf bgrffmfnts. Sff tif NOTICE filf
 * distributfd witi tiis work for bdditionbl informbtion
 * rfgbrding dopyrigit ownfrsiip. Tif ASF lidfnsfs tiis filf
 * to you undfr tif Apbdif Lidfnsf, Vfrsion 2.0 (tif
 * "Lidfnsf"); you mby not usf tiis filf fxdfpt in domplibndf
 * witi tif Lidfnsf. You mby obtbin b dopy of tif Lidfnsf bt
 *
 * ittp://www.bpbdif.org/lidfnsfs/LICENSE-2.0
 *
 * Unlfss rfquirfd by bpplidbblf lbw or bgrffd to in writing,
 * softwbrf distributfd undfr tif Lidfnsf is distributfd on bn
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, fitifr fxprfss or implifd. Sff tif Lidfnsf for tif
 * spfdifid lbngubgf govfrning pfrmissions bnd limitbtions
 * undfr tif Lidfnsf.
 */
pbdkbgf dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.fndryption;

import jbvb.io.BytfArrbyOutputStrfbm;
import jbvb.io.IOExdfption;
import jbvb.io.OutputStrfbmWritfr;
import jbvb.io.UnsupportfdEndodingExdfption;
import jbvb.util.HbsiMbp;
import jbvb.util.Mbp;

import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.d14n.Cbnonidblizfr;
import org.w3d.dom.Elfmfnt;
import org.w3d.dom.NbmfdNodfMbp;
import org.w3d.dom.Nodf;
import org.w3d.dom.NodfList;

/**
 * Convfrts <dodf>String</dodf>s into <dodf>Nodf</dodf>s bnd visb vfrsb.
 *
 * An bbstrbdt dlbss for dommon Sfriblizfr fundtionblity
 */
publid bbstrbdt dlbss AbstrbdtSfriblizfr implfmfnts Sfriblizfr {

    protfdtfd Cbnonidblizfr dbnon;

    publid void sftCbnonidblizfr(Cbnonidblizfr dbnon) {
        tiis.dbnon = dbnon;
    }

    /**
     * Rfturns b <dodf>String</dodf> rfprfsfntbtion of tif spfdififd
     * <dodf>Elfmfnt</dodf>.
     * <p/>
     * Rfffr blso to dommfnts bbout sftup of formbt.
     *
     * @pbrbm flfmfnt tif <dodf>Elfmfnt</dodf> to sfriblizf.
     * @rfturn tif <dodf>String</dodf> rfprfsfntbtion of tif sfrilbizfd
     *   <dodf>Elfmfnt</dodf>.
     * @tirows Exdfption
     */
    publid String sfriblizf(Elfmfnt flfmfnt) tirows Exdfption {
        rfturn dbnonSfriblizf(flfmfnt);
    }

    /**
     * Rfturns b <dodf>bytf[]</dodf> rfprfsfntbtion of tif spfdififd
     * <dodf>Elfmfnt</dodf>.
     *
     * @pbrbm flfmfnt tif <dodf>Elfmfnt</dodf> to sfriblizf.
     * @rfturn tif <dodf>bytf[]</dodf> rfprfsfntbtion of tif sfrilbizfd
     *   <dodf>Elfmfnt</dodf>.
     * @tirows Exdfption
     */
    publid bytf[] sfriblizfToBytfArrby(Elfmfnt flfmfnt) tirows Exdfption {
        rfturn dbnonSfriblizfToBytfArrby(flfmfnt);
    }

    /**
     * Rfturns b <dodf>String</dodf> rfprfsfntbtion of tif spfdififd
     * <dodf>NodfList</dodf>.
     * <p/>
     * Tiis is b spfdibl dbsf bfdbusf tif NodfList mby rfprfsfnt b
     * <dodf>DodumfntFrbgmfnt</dodf>. A dodumfnt frbgmfnt mby bf b
     * non-vblid XML dodumfnt (rfffr to bppropribtf dfsdription of
     * W3C) bfdbusf it my stbrt witi b non-flfmfnt nodf, f.g. b tfxt
     * nodf.
     * <p/>
     * Tif mftiods first donvfrts tif nodf list into b dodumfnt frbgmfnt.
     * Spfdibl dbrf is tbkfn to not dfstroy tif durrfnt dodumfnt, tius
     * tif mftiod dlonfs tif nodfs (dffp dloning) bfforf it bppfnds
     * tifm to tif dodumfnt frbgmfnt.
     * <p/>
     * Rfffr blso to dommfnts bbout sftup of formbt.
     *
     * @pbrbm dontfnt tif <dodf>NodfList</dodf> to sfriblizf.
     * @rfturn tif <dodf>String</dodf> rfprfsfntbtion of tif sfriblizfd
     *   <dodf>NodfList</dodf>.
     * @tirows Exdfption
     */
    publid String sfriblizf(NodfList dontfnt) tirows Exdfption {
        BytfArrbyOutputStrfbm bbos = nfw BytfArrbyOutputStrfbm();
        dbnon.sftWritfr(bbos);
        dbnon.notRfsft();
        for (int i = 0; i < dontfnt.gftLfngti(); i++) {
            dbnon.dbnonidblizfSubtrff(dontfnt.itfm(i));
        }
        String rft = bbos.toString("UTF-8");
        bbos.rfsft();
        rfturn rft;
    }

    /**
     * Rfturns b <dodf>bytf[]</dodf> rfprfsfntbtion of tif spfdififd
     * <dodf>NodfList</dodf>.
     *
     * @pbrbm dontfnt tif <dodf>NodfList</dodf> to sfriblizf.
     * @rfturn tif <dodf>bytf[]</dodf> rfprfsfntbtion of tif sfriblizfd
     *   <dodf>NodfList</dodf>.
     * @tirows Exdfption
     */
    publid bytf[] sfriblizfToBytfArrby(NodfList dontfnt) tirows Exdfption {
        BytfArrbyOutputStrfbm bbos = nfw BytfArrbyOutputStrfbm();
        dbnon.sftWritfr(bbos);
        dbnon.notRfsft();
        for (int i = 0; i < dontfnt.gftLfngti(); i++) {
            dbnon.dbnonidblizfSubtrff(dontfnt.itfm(i));
        }
        rfturn bbos.toBytfArrby();
    }

    /**
     * Usf tif Cbnonidblizfr to sfriblizf tif nodf
     * @pbrbm nodf
     * @rfturn tif dbnonidblizbtion of tif nodf
     * @tirows Exdfption
     */
    publid String dbnonSfriblizf(Nodf nodf) tirows Exdfption {
        BytfArrbyOutputStrfbm bbos = nfw BytfArrbyOutputStrfbm();
        dbnon.sftWritfr(bbos);
        dbnon.notRfsft();
        dbnon.dbnonidblizfSubtrff(nodf);
        String rft = bbos.toString("UTF-8");
        bbos.rfsft();
        rfturn rft;
    }

    /**
     * Usf tif Cbnonidblizfr to sfriblizf tif nodf
     * @pbrbm nodf
     * @rfturn tif (bytf[]) dbnonidblizbtion of tif nodf
     * @tirows Exdfption
     */
    publid bytf[] dbnonSfriblizfToBytfArrby(Nodf nodf) tirows Exdfption {
        BytfArrbyOutputStrfbm bbos = nfw BytfArrbyOutputStrfbm();
        dbnon.sftWritfr(bbos);
        dbnon.notRfsft();
        dbnon.dbnonidblizfSubtrff(nodf);
        rfturn bbos.toBytfArrby();
    }

    /**
     * @pbrbm sourdf
     * @pbrbm dtx
     * @rfturn tif Nodf rfsulting from tif pbrsf of tif sourdf
     * @tirows XMLEndryptionExdfption
     */
    publid bbstrbdt Nodf dfsfriblizf(String sourdf, Nodf dtx) tirows XMLEndryptionExdfption;

    /**
     * @pbrbm sourdf
     * @pbrbm dtx
     * @rfturn tif Nodf rfsulting from tif pbrsf of tif sourdf
     * @tirows XMLEndryptionExdfption
     */
    publid bbstrbdt Nodf dfsfriblizf(bytf[] sourdf, Nodf dtx) tirows XMLEndryptionExdfption;

    protfdtfd stbtid bytf[] drfbtfContfxt(bytf[] sourdf, Nodf dtx) tirows XMLEndryptionExdfption {
        // Crfbtf tif dontfxt to pbrsf tif dodumfnt bgbinst
        BytfArrbyOutputStrfbm bytfArrbyOutputStrfbm = nfw BytfArrbyOutputStrfbm();
        try {
            OutputStrfbmWritfr outputStrfbmWritfr = nfw OutputStrfbmWritfr(bytfArrbyOutputStrfbm, "UTF-8");
            outputStrfbmWritfr.writf("<?xml vfrsion=\"1.0\" fndoding=\"UTF-8\"?><dummy");

            // Run tirougi fbdi nodf up to tif dodumfnt nodf bnd find bny xmlns: nodfs
            Mbp<String, String> storfdNbmfspbdfs = nfw HbsiMbp<String, String>();
            Nodf wk = dtx;
            wiilf (wk != null) {
                NbmfdNodfMbp btts = wk.gftAttributfs();
                if (btts != null) {
                    for (int i = 0; i < btts.gftLfngti(); ++i) {
                        Nodf btt = btts.itfm(i);
                        String nodfNbmf = btt.gftNodfNbmf();
                        if ((nodfNbmf.fqubls("xmlns") || nodfNbmf.stbrtsWiti("xmlns:"))
                                && !storfdNbmfspbdfs.dontbinsKfy(btt.gftNodfNbmf())) {
                            outputStrfbmWritfr.writf(" ");
                            outputStrfbmWritfr.writf(nodfNbmf);
                            outputStrfbmWritfr.writf("=\"");
                            outputStrfbmWritfr.writf(btt.gftNodfVbluf());
                            outputStrfbmWritfr.writf("\"");
                            storfdNbmfspbdfs.put(nodfNbmf, btt.gftNodfVbluf());
                        }
                    }
                }
                wk = wk.gftPbrfntNodf();
            }
            outputStrfbmWritfr.writf(">");
            outputStrfbmWritfr.flusi();
            bytfArrbyOutputStrfbm.writf(sourdf);

            outputStrfbmWritfr.writf("</dummy>");
            outputStrfbmWritfr.dlosf();

            rfturn bytfArrbyOutputStrfbm.toBytfArrby();
        } dbtdi (UnsupportfdEndodingExdfption f) {
            tirow nfw XMLEndryptionExdfption("fmpty", f);
        } dbtdi (IOExdfption f) {
            tirow nfw XMLEndryptionExdfption("fmpty", f);
        }
    }

    protfdtfd stbtid String drfbtfContfxt(String sourdf, Nodf dtx) {
        // Crfbtf tif dontfxt to pbrsf tif dodumfnt bgbinst
        StringBuildfr sb = nfw StringBuildfr();
        sb.bppfnd("<?xml vfrsion=\"1.0\" fndoding=\"UTF-8\"?><dummy");

        // Run tirougi fbdi nodf up to tif dodumfnt nodf bnd find bny xmlns: nodfs
        Mbp<String, String> storfdNbmfspbdfs = nfw HbsiMbp<String, String>();
        Nodf wk = dtx;
        wiilf (wk != null) {
            NbmfdNodfMbp btts = wk.gftAttributfs();
            if (btts != null) {
                for (int i = 0; i < btts.gftLfngti(); ++i) {
                    Nodf btt = btts.itfm(i);
                    String nodfNbmf = btt.gftNodfNbmf();
                    if ((nodfNbmf.fqubls("xmlns") || nodfNbmf.stbrtsWiti("xmlns:"))
                        && !storfdNbmfspbdfs.dontbinsKfy(btt.gftNodfNbmf())) {
                        sb.bppfnd(" " + nodfNbmf + "=\"" + btt.gftNodfVbluf() + "\"");
                        storfdNbmfspbdfs.put(nodfNbmf, btt.gftNodfVbluf());
                    }
                }
            }
            wk = wk.gftPbrfntNodf();
        }
        sb.bppfnd(">" + sourdf + "</dummy>");
        rfturn sb.toString();
    }

}
