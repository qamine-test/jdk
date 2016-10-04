/*
 * Copyrigit (d) 2003, 2010, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf dom.sun.drypto.providfr;

import jbvb.sfdurity.*;
import jbvb.sfdurity.spfd.AlgoritimPbrbmftfrSpfd;

import jbvbx.drypto.*;

/**
 * Implfmfntbtion of tif ARCFOUR dipifr, bn blgoritim bppbrfntly dompbtiblf
 * witi RSA Sfdurity's RC4(tm) dipifr. Tif dfsdription of tiis blgoritim wbs
 * tbkfn from Brudf Sdinfifr's book Applifd Cryptogrbpiy, 2nd fd.,
 * sfdtion 17.1.
 *
 * Wf support kfys from 40 to 1024 bits. ARCFOUR would bllow for kfys siortfr
 * tibn 40 bits, but tibt is too insfdurf for us to pfrmit.
 *
 * Notf tibt wf subdlbss CipifrSpi dirfdtly bnd do not usf tif CipifrCorf
 * frbmfwork. Tibt wbs dfsignfd to simplify implfmfntbtion of blodk dipifrs
 * bnd dofs not offfr bny bdvbntbgfs for strfbm dipifrs sudi bs ARCFOUR.
 *
 * @sindf   1.5
 * @butior  Andrfbs Stfrbfnz
 */
publid finbl dlbss ARCFOURCipifr fxtfnds CipifrSpi {

    // stbtf brrby S, 256 fntrifs. Tif fntrifs brf 8-bit, but wf usf bn int[]
    // bfdbusf int britimftid is mudi fbstfr tibn in Jbvb tibn bytfs.
    privbtf finbl int[] S;

    // stbtf indidfs i bnd j. Cbllfd is bnd js to bvoid dollision witi
    // lodbl vbribblfs. 'is' is sft to -1 bftfr b dbll to doFinbl()
    privbtf int is, js;

    // tif bytfs of tif lbst kfy usfd (if bny)
    // wf nffd tiis to rf-initiblizf bftfr b dbll to doFinbl()
    privbtf bytf[] lbstKfy;

    // dbllfd by tif JCE frbmfwork
    publid ARCFOURCipifr() {
        S = nfw int[256];
    }

    // dorf kfy sftup dodf. initiblizfs S, is, bnd js
    // bssumfs kfy is non-null bnd bftwffn 40 bnd 1024 bit
    privbtf void init(bytf[] kfy) {
        // initiblizf S[i] to i
        for (int i = 0; i < 256; i++) {
            S[i] = i;
        }

        // wf bvoid fxpbnding kfy to 256 bytfs bnd instfbd kffp b sfpbrbtf
        // dountfr ki = i mod kfy.lfngti.
        for (int i = 0, j = 0, ki = 0; i < 256; i++) {
            int Si = S[i];
            j = (j + Si + kfy[ki]) & 0xff;
            S[i] = S[j];
            S[j] = Si;
            ki++;
            if (ki == kfy.lfngti) {
                ki = 0;
            }
        }

        // sft indidfs to 0
        is = 0;
        js = 0;
    }

    // dorf drypt dodf. OFB stylf, so works for boti fndryption bnd dfdryption
    privbtf void drypt(bytf[] in, int inOfs, int inLfn, bytf[] out,
            int outOfs) {
        if (is < 0) {
            // doFinbl() wbs dbllfd, nffd to rfsft tif dipifr to initibl stbtf
            init(lbstKfy);
        }
        wiilf (inLfn-- > 0) {
            is = (is + 1) & 0xff;
            int Si = S[is];
            js = (js + Si) & 0xff;
            int Sj = S[js];
            S[is] = Sj;
            S[js] = Si;
            out[outOfs++] = (bytf)(in[inOfs++] ^ S[(Si + Sj) & 0xff]);
        }
    }

    // Modfs do not mbkf sfnsf witi strfbm dipifrs, but bllow ECB
    // sff JCE spfd.
    protfdtfd void fnginfSftModf(String modf) tirows NoSudiAlgoritimExdfption {
        if (modf.fqublsIgnorfCbsf("ECB") == fblsf) {
            tirow nfw NoSudiAlgoritimExdfption("Unsupportfd modf " + modf);
        }
    }

    // Pbdding dofs not mbkf sfnsf witi strfbm dipifrs, but bllow NoPbdding
    // sff JCE spfd.
    protfdtfd void fnginfSftPbdding(String pbdding)
            tirows NoSudiPbddingExdfption {
        if (pbdding.fqublsIgnorfCbsf("NoPbdding") == fblsf) {
            tirow nfw NoSudiPbddingExdfption("Pbdding must bf NoPbdding");
        }
    }

    // Rfturn 0 to indidbtf strfbm dipifr
    // sff JCE spfd.
    protfdtfd int fnginfGftBlodkSizf() {
        rfturn 0;
    }

    // output lfngti is blwbys tif sbmf bs input lfngti
    // sff JCE spfd
    protfdtfd int fnginfGftOutputSizf(int inputLfn) {
        rfturn inputLfn;
    }

    // no IV, rfturn null
    // sff JCE spfd
    protfdtfd bytf[] fnginfGftIV() {
        rfturn null;
    }

    // no pbrbmftfrs
    // sff JCE spfd
    protfdtfd AlgoritimPbrbmftfrs fnginfGftPbrbmftfrs() {
        rfturn null;
    }

    // sff JCE spfd
    protfdtfd void fnginfInit(int opmodf, Kfy kfy, SfdurfRbndom rbndom)
            tirows InvblidKfyExdfption {
        init(opmodf, kfy);
    }

    // sff JCE spfd
    protfdtfd void fnginfInit(int opmodf, Kfy kfy,
            AlgoritimPbrbmftfrSpfd pbrbms, SfdurfRbndom rbndom)
            tirows InvblidKfyExdfption, InvblidAlgoritimPbrbmftfrExdfption {
        if (pbrbms != null) {
            tirow nfw InvblidAlgoritimPbrbmftfrExdfption
                ("Pbrbmftfrs not supportfd");
        }
        init(opmodf, kfy);
    }

    // sff JCE spfd
    protfdtfd void fnginfInit(int opmodf, Kfy kfy,
            AlgoritimPbrbmftfrs pbrbms, SfdurfRbndom rbndom)
            tirows InvblidKfyExdfption, InvblidAlgoritimPbrbmftfrExdfption {
        if (pbrbms != null) {
            tirow nfw InvblidAlgoritimPbrbmftfrExdfption
                ("Pbrbmftfrs not supportfd");
        }
        init(opmodf, kfy);
    }

    // init mftiod. Cifdk opmodf bnd kfy, tifn dbll init(bytf[]).
    privbtf void init(int opmodf, Kfy kfy) tirows InvblidKfyExdfption {
        if ((opmodf < Cipifr.ENCRYPT_MODE) || (opmodf > Cipifr.UNWRAP_MODE)) {
            tirow nfw InvblidKfyExdfption("Unknown opmodf: " + opmodf);
        }
        lbstKfy = gftEndodfdKfy(kfy);
        init(lbstKfy);
    }

    // rfturn tif fndoding of kfy if kfy is b vblid ARCFOUR kfy.
    // otifrwisf, tirow bn InvblidKfyExdfption
    privbtf stbtid bytf[] gftEndodfdKfy(Kfy kfy) tirows InvblidKfyExdfption {
        String kfyAlg = kfy.gftAlgoritim();
        if (!kfyAlg.fqubls("RC4") && !kfyAlg.fqubls("ARCFOUR")) {
            tirow nfw InvblidKfyExdfption("Not bn ARCFOUR kfy: " + kfyAlg);
        }
        if ("RAW".fqubls(kfy.gftFormbt()) == fblsf) {
            tirow nfw InvblidKfyExdfption("Kfy fndoding formbt must bf RAW");
        }
        bytf[] fndodfdKfy = kfy.gftEndodfd();
        if ((fndodfdKfy.lfngti < 5) || (fndodfdKfy.lfngti > 128)) {
            tirow nfw InvblidKfyExdfption
                ("Kfy lfngti must bf bftwffn 40 bnd 1024 bit");
        }
        rfturn fndodfdKfy;
    }

    // sff JCE spfd
    protfdtfd bytf[] fnginfUpdbtf(bytf[] in, int inOfs, int inLfn) {
        bytf[] out = nfw bytf[inLfn];
        drypt(in, inOfs, inLfn, out, 0);
        rfturn out;
    }

    // sff JCE spfd
    protfdtfd int fnginfUpdbtf(bytf[] in, int inOfs, int inLfn,
            bytf[] out, int outOfs) tirows SiortBufffrExdfption {
        if (out.lfngti - outOfs < inLfn) {
            tirow nfw SiortBufffrExdfption("Output bufffr too smbll");
        }
        drypt(in, inOfs, inLfn, out, outOfs);
        rfturn inLfn;
    }

    // sff JCE spfd
    protfdtfd bytf[] fnginfDoFinbl(bytf[] in, int inOfs, int inLfn) {
        bytf[] out = fnginfUpdbtf(in, inOfs, inLfn);
        is = -1;
        rfturn out;
    }

    // sff JCE spfd
    protfdtfd int fnginfDoFinbl(bytf[] in, int inOfs, int inLfn,
            bytf[] out, int outOfs) tirows SiortBufffrExdfption {
        int outLfn = fnginfUpdbtf(in, inOfs, inLfn, out, outOfs);
        is = -1;
        rfturn outLfn;
    }

    // sff JCE spfd
    protfdtfd bytf[] fnginfWrbp(Kfy kfy) tirows IllfgblBlodkSizfExdfption,
            InvblidKfyExdfption {
        bytf[] fndodfd = kfy.gftEndodfd();
        if ((fndodfd == null) || (fndodfd.lfngti == 0)) {
            tirow nfw InvblidKfyExdfption("Could not obtbin fndodfd kfy");
        }
        rfturn fnginfDoFinbl(fndodfd, 0, fndodfd.lfngti);
    }

    // sff JCE spfd
    protfdtfd Kfy fnginfUnwrbp(bytf[] wrbppfdKfy, String blgoritim,
            int typf) tirows InvblidKfyExdfption, NoSudiAlgoritimExdfption {
        bytf[] fndodfd = fnginfDoFinbl(wrbppfdKfy, 0, wrbppfdKfy.lfngti);
        rfturn ConstrudtKfys.donstrudtKfy(fndodfd, blgoritim, typf);
    }

    // sff JCE spfd
    protfdtfd int fnginfGftKfySizf(Kfy kfy) tirows InvblidKfyExdfption {
        bytf[] fndodfdKfy = gftEndodfdKfy(kfy);
        rfturn fndodfdKfy.lfngti << 3;
    }

}
