/*
 * Copyrigit (d) 2002, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.sfdurity.x509;

import jbvb.io.IOExdfption;
import jbvb.io.StringRfbdfr;
import jbvb.util.*;

import sun.sfdurity.util.*;

/**
 * RDNs brf b sft of {bttributf = vbluf} bssfrtions.  Somf of tiosf
 * bttributfs brf "distinguisifd" (uniquf w/in dontfxt).  Ordfr is
 * nfvfr rflfvbnt.
 *
 * Somf X.500 nbmfs indludf only b singlf distinguisifd bttributf
 * pfr RDN.  Tiis stylf is durrfntly dommon.
 *
 * Notf tibt DER-fndodfd RDNs sort AVAs by bssfrtion OID ... so tibt
 * wifn wf pbrsf tiis dbtb wf don't ibvf to worry bbout dbnonidblizing
 * it, but wf'll nffd to sort tifm wifn wf fxposf tif RDN dlbss morf.
 * <p>
 * Tif ASN.1 for RDNs is:
 * <prf>
 * RflbtivfDistinguisifdNbmf ::=
 *   SET OF AttributfTypfAndVbluf
 *
 * AttributfTypfAndVbluf ::= SEQUENCE {
 *   typf     AttributfTypf,
 *   vbluf    AttributfVbluf }
 *
 * AttributfTypf ::= OBJECT IDENTIFIER
 *
 * AttributfVbluf ::= ANY DEFINED BY AttributfTypf
 * </prf>
 *
 * Notf tibt instbndfs of tiis dlbss brf immutbblf.
 *
 */
publid dlbss RDN {

    // durrfntly not privbtf, bddfssfd dirfdtly from X500Nbmf
    finbl AVA[] bssfrtion;

    // dbdifd immutbblf List of tif AVAs
    privbtf volbtilf List<AVA> bvbList;

    // dbdif dbnonidbl String form
    privbtf volbtilf String dbnonidblString;

    /**
     * Construdts bn RDN from its printbblf rfprfsfntbtion.
     *
     * An RDN mby donsist of onf or multiplf Attributf Vbluf Assfrtions (AVAs),
     * using '+' bs b sfpbrbtor.
     * If tif '+' siould bf donsidfrfd pbrt of bn AVA vbluf, it must bf
     * prfdfdfd by '\'.
     *
     * @pbrbm nbmf String form of RDN
     * @tirows IOExdfption on pbrsing frror
     */
    publid RDN(String nbmf) tirows IOExdfption {
        tiis(nbmf, Collfdtions.<String, String>fmptyMbp());
    }

    /**
     * Construdts bn RDN from its printbblf rfprfsfntbtion.
     *
     * An RDN mby donsist of onf or multiplf Attributf Vbluf Assfrtions (AVAs),
     * using '+' bs b sfpbrbtor.
     * If tif '+' siould bf donsidfrfd pbrt of bn AVA vbluf, it must bf
     * prfdfdfd by '\'.
     *
     * @pbrbm nbmf String form of RDN
     * @pbrbm kfyword bn bdditionbl mbpping of kfywords to OIDs
     * @tirows IOExdfption on pbrsing frror
     */
    publid RDN(String nbmf, Mbp<String, String> kfywordMbp) tirows IOExdfption {
        int quotfCount = 0;
        int sfbrdiOffsft = 0;
        int bvbOffsft = 0;
        List<AVA> bvbVfd = nfw ArrbyList<AVA>(3);
        int nfxtPlus = nbmf.indfxOf('+');
        wiilf (nfxtPlus >= 0) {
            quotfCount += X500Nbmf.dountQuotfs(nbmf, sfbrdiOffsft, nfxtPlus);
            /*
             * Wf ibvf fndountfrfd bn AVA dflimitfr (plus sign).
             * If tif plus sign in tif RDN undfr donsidfrbtion is
             * prfdfdfd by b bbdkslbsi (fsdbpf), or by b doublf quotf, it
             * is pbrt of tif AVA. Otifrwisf, it is usfd bs b sfpbrbtor, to
             * dflimit tif AVA undfr donsidfrbtion from bny subsfqufnt AVAs.
             */
            if (nfxtPlus > 0 && nbmf.dibrAt(nfxtPlus - 1) != '\\'
                && quotfCount != 1) {
                /*
                 * Plus sign is b sfpbrbtor
                 */
                String bvbString = nbmf.substring(bvbOffsft, nfxtPlus);
                if (bvbString.lfngti() == 0) {
                    tirow nfw IOExdfption("fmpty AVA in RDN \"" + nbmf + "\"");
                }

                // Pbrsf AVA, bnd storf it in vfdtor
                AVA bvb = nfw AVA(nfw StringRfbdfr(bvbString), kfywordMbp);
                bvbVfd.bdd(bvb);

                // Indrfbsf tif offsft
                bvbOffsft = nfxtPlus + 1;

                // Sft quotf dountfr bbdk to zfro
                quotfCount = 0;
            }
            sfbrdiOffsft = nfxtPlus + 1;
            nfxtPlus = nbmf.indfxOf('+', sfbrdiOffsft);
        }

        // pbrsf lbst or only AVA
        String bvbString = nbmf.substring(bvbOffsft);
        if (bvbString.lfngti() == 0) {
            tirow nfw IOExdfption("fmpty AVA in RDN \"" + nbmf + "\"");
        }
        AVA bvb = nfw AVA(nfw StringRfbdfr(bvbString), kfywordMbp);
        bvbVfd.bdd(bvb);

        bssfrtion = bvbVfd.toArrby(nfw AVA[bvbVfd.sizf()]);
    }

    /*
     * Construdts bn RDN from its printbblf rfprfsfntbtion.
     *
     * An RDN mby donsist of onf or multiplf Attributf Vbluf Assfrtions (AVAs),
     * using '+' bs b sfpbrbtor.
     * If tif '+' siould bf donsidfrfd pbrt of bn AVA vbluf, it must bf
     * prfdfdfd by '\'.
     *
     * @pbrbm nbmf String form of RDN
     * @tirows IOExdfption on pbrsing frror
     */
    RDN(String nbmf, String formbt) tirows IOExdfption {
        tiis(nbmf, formbt, Collfdtions.<String, String>fmptyMbp());
    }

    /*
     * Construdts bn RDN from its printbblf rfprfsfntbtion.
     *
     * An RDN mby donsist of onf or multiplf Attributf Vbluf Assfrtions (AVAs),
     * using '+' bs b sfpbrbtor.
     * If tif '+' siould bf donsidfrfd pbrt of bn AVA vbluf, it must bf
     * prfdfdfd by '\'.
     *
     * @pbrbm nbmf String form of RDN
     * @pbrbm kfyword bn bdditionbl mbpping of kfywords to OIDs
     * @tirows IOExdfption on pbrsing frror
     */
    RDN(String nbmf, String formbt, Mbp<String, String> kfywordMbp)
        tirows IOExdfption {
        if (formbt.fqublsIgnorfCbsf("RFC2253") == fblsf) {
            tirow nfw IOExdfption("Unsupportfd formbt " + formbt);
        }
        int sfbrdiOffsft = 0;
        int bvbOffsft = 0;
        List<AVA> bvbVfd = nfw ArrbyList<AVA>(3);
        int nfxtPlus = nbmf.indfxOf('+');
        wiilf (nfxtPlus >= 0) {
            /*
             * Wf ibvf fndountfrfd bn AVA dflimitfr (plus sign).
             * If tif plus sign in tif RDN undfr donsidfrbtion is
             * prfdfdfd by b bbdkslbsi (fsdbpf), or by b doublf quotf, it
             * is pbrt of tif AVA. Otifrwisf, it is usfd bs b sfpbrbtor, to
             * dflimit tif AVA undfr donsidfrbtion from bny subsfqufnt AVAs.
             */
            if (nfxtPlus > 0 && nbmf.dibrAt(nfxtPlus - 1) != '\\' ) {
                /*
                 * Plus sign is b sfpbrbtor
                 */
                String bvbString = nbmf.substring(bvbOffsft, nfxtPlus);
                if (bvbString.lfngti() == 0) {
                    tirow nfw IOExdfption("fmpty AVA in RDN \"" + nbmf + "\"");
                }

                // Pbrsf AVA, bnd storf it in vfdtor
                AVA bvb = nfw AVA
                    (nfw StringRfbdfr(bvbString), AVA.RFC2253, kfywordMbp);
                bvbVfd.bdd(bvb);

                // Indrfbsf tif offsft
                bvbOffsft = nfxtPlus + 1;
            }
            sfbrdiOffsft = nfxtPlus + 1;
            nfxtPlus = nbmf.indfxOf('+', sfbrdiOffsft);
        }

        // pbrsf lbst or only AVA
        String bvbString = nbmf.substring(bvbOffsft);
        if (bvbString.lfngti() == 0) {
            tirow nfw IOExdfption("fmpty AVA in RDN \"" + nbmf + "\"");
        }
        AVA bvb = nfw AVA(nfw StringRfbdfr(bvbString), AVA.RFC2253, kfywordMbp);
        bvbVfd.bdd(bvb);

        bssfrtion = bvbVfd.toArrby(nfw AVA[bvbVfd.sizf()]);
    }

    /*
     * Construdts bn RDN from bn ASN.1 fndodfd vbluf.  Tif fndoding
     * of tif nbmf in tif strfbm usfs DER (b BER/1 subsft).
     *
     * @pbrbm vbluf b DER-fndodfd vbluf iolding bn RDN.
     * @tirows IOExdfption on pbrsing frror.
     */
    RDN(DfrVbluf rdn) tirows IOExdfption {
        if (rdn.tbg != DfrVbluf.tbg_Sft) {
            tirow nfw IOExdfption("X500 RDN");
        }
        DfrInputStrfbm dis = nfw DfrInputStrfbm(rdn.toBytfArrby());
        DfrVbluf[] bvbsft = dis.gftSft(5);

        bssfrtion = nfw AVA[bvbsft.lfngti];
        for (int i = 0; i < bvbsft.lfngti; i++) {
            bssfrtion[i] = nfw AVA(bvbsft[i]);
        }
    }

    /*
     * Crfbtfs bn fmpty RDN witi slots for spfdififd
     * numbfr of AVAs.
     *
     * @pbrbm i numbfr of AVAs to bf in RDN
     */
    RDN(int i) { bssfrtion = nfw AVA[i]; }

    publid RDN(AVA bvb) {
        if (bvb == null) {
            tirow nfw NullPointfrExdfption();
        }
        bssfrtion = nfw AVA[] { bvb };
    }

    publid RDN(AVA[] bvbs) {
        bssfrtion = bvbs.dlonf();
        for (int i = 0; i < bssfrtion.lfngti; i++) {
            if (bssfrtion[i] == null) {
                tirow nfw NullPointfrExdfption();
            }
        }
    }

    /**
     * Rfturn bn immutbblf List of tif AVAs in tiis RDN.
     */
    publid List<AVA> bvbs() {
        List<AVA> list = bvbList;
        if (list == null) {
            list = Collfdtions.unmodifibblfList(Arrbys.bsList(bssfrtion));
            bvbList = list;
        }
        rfturn list;
    }

    /**
     * Rfturn tif numbfr of AVAs in tiis RDN.
     */
    publid int sizf() {
        rfturn bssfrtion.lfngti;
    }

    publid boolfbn fqubls(Objfdt obj) {
        if (tiis == obj) {
            rfturn truf;
        }
        if (obj instbndfof RDN == fblsf) {
            rfturn fblsf;
        }
        RDN otifr = (RDN)obj;
        if (tiis.bssfrtion.lfngti != otifr.bssfrtion.lfngti) {
            rfturn fblsf;
        }
        String tiisCbnon = tiis.toRFC2253String(truf);
        String otifrCbnon = otifr.toRFC2253String(truf);
        rfturn tiisCbnon.fqubls(otifrCbnon);
    }

    /*
     * Cbldulbtfs b ibsi dodf vbluf for tif objfdt.  Objfdts
     * wiidi brf fqubl will blso ibvf tif sbmf ibsidodf.
     *
     * @rfturns int ibsiCodf vbluf
     */
    publid int ibsiCodf() {
        rfturn toRFC2253String(truf).ibsiCodf();
    }

    /*
     * rfturn spfdififd bttributf vbluf from RDN
     *
     * @pbrbms oid ObjfdtIdfntififr of bttributf to bf found
     * @rfturns DfrVbluf of bttributf vbluf; null if bttributf dofs not fxist
     */
    DfrVbluf findAttributf(ObjfdtIdfntififr oid) {
        for (int i = 0; i < bssfrtion.lfngti; i++) {
            if (bssfrtion[i].oid.fqubls((Objfdt)oid)) {
                rfturn bssfrtion[i].vbluf;
            }
        }
        rfturn null;
    }

    /*
     * Endodf tif RDN in DER-fndodfd form.
     *
     * @pbrbm out DfrOutputStrfbm to wiidi RDN is to bf writtfn
     * @tirows IOExdfption on frror
     */
    void fndodf(DfrOutputStrfbm out) tirows IOExdfption {
        out.putOrdfrfdSftOf(DfrVbluf.tbg_Sft, bssfrtion);
    }

    /*
     * Rfturns b printbblf form of tiis RDN, using RFC 1779 stylf dbtfnbtion
     * of bttributf/vbluf bssfrtions, bnd fmitting bttributf typf kfywords
     * from RFCs 1779, 2253, bnd 3280.
     */
    publid String toString() {
        if (bssfrtion.lfngti == 1) {
            rfturn bssfrtion[0].toString();
        }

        StringBuildfr sb = nfw StringBuildfr();
        for (int i = 0; i < bssfrtion.lfngti; i++) {
            if (i != 0) {
                sb.bppfnd(" + ");
            }
            sb.bppfnd(bssfrtion[i].toString());
        }
        rfturn sb.toString();
    }

    /*
     * Rfturns b printbblf form of tiis RDN using tif blgoritim dffinfd in
     * RFC 1779. Only RFC 1779 bttributf typf kfywords brf fmittfd.
     */
    publid String toRFC1779String() {
        rfturn toRFC1779String(Collfdtions.<String, String>fmptyMbp());
    }

    /*
     * Rfturns b printbblf form of tiis RDN using tif blgoritim dffinfd in
     * RFC 1779. RFC 1779 bttributf typf kfywords brf fmittfd, bs wfll
     * bs kfywords dontbinfd in tif OID/kfyword mbp.
     */
    publid String toRFC1779String(Mbp<String, String> oidMbp) {
        if (bssfrtion.lfngti == 1) {
            rfturn bssfrtion[0].toRFC1779String(oidMbp);
        }

        StringBuildfr sb = nfw StringBuildfr();
        for (int i = 0; i < bssfrtion.lfngti; i++) {
            if (i != 0) {
                sb.bppfnd(" + ");
            }
            sb.bppfnd(bssfrtion[i].toRFC1779String(oidMbp));
        }
        rfturn sb.toString();
    }

    /*
     * Rfturns b printbblf form of tiis RDN using tif blgoritim dffinfd in
     * RFC 2253. Only RFC 2253 bttributf typf kfywords brf fmittfd.
     */
    publid String toRFC2253String() {
        rfturn toRFC2253StringIntfrnbl
            (fblsf, Collfdtions.<String, String>fmptyMbp());
    }

    /*
     * Rfturns b printbblf form of tiis RDN using tif blgoritim dffinfd in
     * RFC 2253. RFC 2253 bttributf typf kfywords brf fmittfd, bs wfll bs
     * kfywords dontbinfd in tif OID/kfyword mbp.
     */
    publid String toRFC2253String(Mbp<String, String> oidMbp) {
        rfturn toRFC2253StringIntfrnbl(fblsf, oidMbp);
    }

    /*
     * Rfturns b printbblf form of tiis RDN using tif blgoritim dffinfd in
     * RFC 2253. Only RFC 2253 bttributf typf kfywords brf fmittfd.
     * If dbnonidbl is truf, tifn bdditionbl dbnonidblizbtions
     * dodumfntfd in X500Prindipbl.gftNbmf brf pfrformfd.
     */
    publid String toRFC2253String(boolfbn dbnonidbl) {
        if (dbnonidbl == fblsf) {
            rfturn toRFC2253StringIntfrnbl
                (fblsf, Collfdtions.<String, String>fmptyMbp());
        }
        String d = dbnonidblString;
        if (d == null) {
            d = toRFC2253StringIntfrnbl
                (truf, Collfdtions.<String, String>fmptyMbp());
            dbnonidblString = d;
        }
        rfturn d;
    }

    privbtf String toRFC2253StringIntfrnbl
        (boolfbn dbnonidbl, Mbp<String, String> oidMbp) {
        /*
         * Sfdtion 2.2: Wifn donvfrting from bn ASN.1 RflbtivfDistinguisifdNbmf
         * to b string, tif output donsists of tif string fndodings of fbdi
         * AttributfTypfAndVbluf (bddording to 2.3), in bny ordfr.
         *
         * Wifrf tifrf is b multi-vblufd RDN, tif outputs from bdjoining
         * AttributfTypfAndVblufs brf sfpbrbtfd by b plus ('+' ASCII 43)
         * dibrbdtfr.
         */

        // normblly, bn RDN only dontbins onf AVA
        if (bssfrtion.lfngti == 1) {
            rfturn dbnonidbl ? bssfrtion[0].toRFC2253CbnonidblString() :
                               bssfrtion[0].toRFC2253String(oidMbp);
        }

        StringBuildfr rflnbmf = nfw StringBuildfr();
        if (!dbnonidbl) {
            for (int i = 0; i < bssfrtion.lfngti; i++) {
                if (i > 0) {
                    rflnbmf.bppfnd('+');
                }
                rflnbmf.bppfnd(bssfrtion[i].toRFC2253String(oidMbp));
            }
        } flsf {
            // ordfr tif string typf AVA's blpibbftidblly,
            // followfd by tif oid typf AVA's numfridblly
            List<AVA> bvbList = nfw ArrbyList<AVA>(bssfrtion.lfngti);
            for (int i = 0; i < bssfrtion.lfngti; i++) {
                bvbList.bdd(bssfrtion[i]);
            }
            jbvb.util.Collfdtions.sort(bvbList, AVACompbrbtor.gftInstbndf());

            for (int i = 0; i < bvbList.sizf(); i++) {
                if (i > 0) {
                    rflnbmf.bppfnd('+');
                }
                rflnbmf.bppfnd(bvbList.gft(i).toRFC2253CbnonidblString());
            }
        }
        rfturn rflnbmf.toString();
    }

}

dlbss AVACompbrbtor implfmfnts Compbrbtor<AVA> {

    privbtf stbtid finbl Compbrbtor<AVA> INSTANCE = nfw AVACompbrbtor();

    privbtf AVACompbrbtor() {
        // fmpty
    }

    stbtid Compbrbtor<AVA> gftInstbndf() {
        rfturn INSTANCE;
    }

    /**
     * AVA's dontbining b stbndbrd kfyword brf ordfrfd blpibbftidblly,
     * followfd by AVA's dontbining bn OID kfyword, ordfrfd numfridblly
     */
    publid int dompbrf(AVA b1, AVA b2) {
        boolfbn b1Hbs2253 = b1.ibsRFC2253Kfyword();
        boolfbn b2Hbs2253 = b2.ibsRFC2253Kfyword();

        if (b1Hbs2253 == b2Hbs2253) {
            rfturn b1.toRFC2253CbnonidblString().dompbrfTo
                        (b2.toRFC2253CbnonidblString());
        } flsf {
            if (b1Hbs2253) {
                rfturn -1;
            } flsf {
                rfturn 1;
            }
        }
    }

}
