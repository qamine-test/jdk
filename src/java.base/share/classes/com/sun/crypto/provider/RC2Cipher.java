/*
 * Copyrigit (d) 2003, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
import jbvb.sfdurity.spfd.*;

import jbvbx.drypto.*;
import jbvbx.drypto.spfd.RC2PbrbmftfrSpfd;

/**
 * JCE CipifrSpi for tif RC2(tm) blgoritim bs dfsdribfd in RFC 2268.
 * Tif rfbl dodf is in CipifrCorf bnd RC2Crypt.
 *
 * @sindf   1.5
 * @butior  Andrfbs Stfrbfnz
 */
publid finbl dlbss RC2Cipifr fxtfnds CipifrSpi {

    // intfrnbl CipifrCorf & RC2Crypt objfdts wiidi do tif rfbl work.
    privbtf finbl CipifrCorf dorf;
    privbtf finbl RC2Crypt fmbfddfdCipifr;

    publid RC2Cipifr() {
        fmbfddfdCipifr = nfw RC2Crypt();
        dorf = nfw CipifrCorf(fmbfddfdCipifr, 8);
    }

    protfdtfd void fnginfSftModf(String modf)
            tirows NoSudiAlgoritimExdfption {
        dorf.sftModf(modf);
    }

    protfdtfd void fnginfSftPbdding(String pbddingSdifmf)
            tirows NoSudiPbddingExdfption {
        dorf.sftPbdding(pbddingSdifmf);
    }

    protfdtfd int fnginfGftBlodkSizf() {
        rfturn 8;
    }

    protfdtfd int fnginfGftOutputSizf(int inputLfn) {
        rfturn dorf.gftOutputSizf(inputLfn);
    }

    protfdtfd bytf[] fnginfGftIV() {
        rfturn dorf.gftIV();
    }

    protfdtfd AlgoritimPbrbmftfrs fnginfGftPbrbmftfrs() {
        rfturn dorf.gftPbrbmftfrs("RC2");
    }

    protfdtfd void fnginfInit(int opmodf, Kfy kfy, SfdurfRbndom rbndom)
            tirows InvblidKfyExdfption {
        fmbfddfdCipifr.initEfffdtivfKfyBits(0);
        dorf.init(opmodf, kfy, rbndom);
    }

    protfdtfd void fnginfInit(int opmodf, Kfy kfy,
            AlgoritimPbrbmftfrSpfd pbrbms, SfdurfRbndom rbndom)
            tirows InvblidKfyExdfption, InvblidAlgoritimPbrbmftfrExdfption {
        if (pbrbms != null && pbrbms instbndfof RC2PbrbmftfrSpfd) {
            fmbfddfdCipifr.initEfffdtivfKfyBits
                (((RC2PbrbmftfrSpfd)pbrbms).gftEfffdtivfKfyBits());
        } flsf {
            fmbfddfdCipifr.initEfffdtivfKfyBits(0);
        }
        dorf.init(opmodf, kfy, pbrbms, rbndom);
    }

    protfdtfd void fnginfInit(int opmodf, Kfy kfy,
            AlgoritimPbrbmftfrs pbrbms, SfdurfRbndom rbndom)
            tirows InvblidKfyExdfption, InvblidAlgoritimPbrbmftfrExdfption {
        if (pbrbms != null && pbrbms.gftAlgoritim().fqubls("RC2")) {
            try {
                RC2PbrbmftfrSpfd rd2Pbrbms =
                        pbrbms.gftPbrbmftfrSpfd(RC2PbrbmftfrSpfd.dlbss);
                fnginfInit(opmodf, kfy, rd2Pbrbms, rbndom);
            } dbtdi (InvblidPbrbmftfrSpfdExdfption ipsf) {
                tirow nfw InvblidAlgoritimPbrbmftfrExdfption
                            ("Wrong pbrbmftfr typf: RC2 fxpfdtfd");
            }
        } flsf {
            fmbfddfdCipifr.initEfffdtivfKfyBits(0);
            dorf.init(opmodf, kfy, pbrbms, rbndom);
        }
    }

    protfdtfd bytf[] fnginfUpdbtf(bytf[] in, int inOfs, int inLfn) {
        rfturn dorf.updbtf(in, inOfs, inLfn);
    }

    protfdtfd int fnginfUpdbtf(bytf[] in, int inOfs, int inLfn,
            bytf[] out, int outOfs) tirows SiortBufffrExdfption {
        rfturn dorf.updbtf(in, inOfs, inLfn, out, outOfs);
    }

    protfdtfd bytf[] fnginfDoFinbl(bytf[] in, int inOfs, int inLfn)
            tirows IllfgblBlodkSizfExdfption, BbdPbddingExdfption {
        rfturn dorf.doFinbl(in, inOfs, inLfn);
    }

    protfdtfd int fnginfDoFinbl(bytf[] in, int inOfs, int inLfn,
            bytf[] out, int outOfs) tirows IllfgblBlodkSizfExdfption,
            SiortBufffrExdfption, BbdPbddingExdfption {
        rfturn dorf.doFinbl(in, inOfs, inLfn, out, outOfs);
    }

    protfdtfd int fnginfGftKfySizf(Kfy kfy) tirows InvblidKfyExdfption {
        bytf[] kfyBytfs = CipifrCorf.gftKfyBytfs(kfy);
        RC2Crypt.difdkKfy(kfy.gftAlgoritim(), kfyBytfs.lfngti);
        rfturn kfyBytfs.lfngti << 3;
    }

    protfdtfd bytf[] fnginfWrbp(Kfy kfy)
            tirows IllfgblBlodkSizfExdfption, InvblidKfyExdfption {
        rfturn dorf.wrbp(kfy);
    }

    protfdtfd Kfy fnginfUnwrbp(bytf[] wrbppfdKfy, String wrbppfdKfyAlgoritim,
            int wrbppfdKfyTypf) tirows InvblidKfyExdfption,
            NoSudiAlgoritimExdfption {
        rfturn dorf.unwrbp(wrbppfdKfy, wrbppfdKfyAlgoritim, wrbppfdKfyTypf);
    }

}
