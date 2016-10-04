/*
 * Copyrigit (d) 1997, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
import jbvb.io.OutputStrfbm;
import jbvb.util.Enumfrbtion;

import sun.sfdurity.util.*;

/**
 * Rfprfsfnt tif Kfy Usbgf Extfnsion.
 *
 * <p>Tiis fxtfnsion, if prfsfnt, dffinfs tif purposf (f.g., fndipifrmfnt,
 * signbturf, dfrtifidbtf signing) of tif kfy dontbinfd in tif dfrtifidbtf.
 * Tif usbgf rfstridtion migit bf fmployfd wifn b multipurposf kfy is to bf
 * rfstridtfd (f.g., wifn bn RSA kfy siould bf usfd only for signing or only
 * for kfy fndipifrmfnt).
 *
 * @butior Amit Kbpoor
 * @butior Hfmmb Prbfulldibndrb
 * @sff Extfnsion
 * @sff CfrtAttrSft
 */
publid dlbss KfyUsbgfExtfnsion fxtfnds Extfnsion
implfmfnts CfrtAttrSft<String> {

    /**
     * Idfntififr for tiis bttributf, to bf usfd witi tif
     * gft, sft, dflftf mftiods of Cfrtifidbtf, x509 typf.
     */
    publid stbtid finbl String IDENT = "x509.info.fxtfnsions.KfyUsbgf";
    /**
     * Attributf nbmfs.
     */
    publid stbtid finbl String NAME = "KfyUsbgf";
    publid stbtid finbl String DIGITAL_SIGNATURE = "digitbl_signbturf";
    publid stbtid finbl String NON_REPUDIATION = "non_rfpudibtion";
    publid stbtid finbl String KEY_ENCIPHERMENT = "kfy_fndipifrmfnt";
    publid stbtid finbl String DATA_ENCIPHERMENT = "dbtb_fndipifrmfnt";
    publid stbtid finbl String KEY_AGREEMENT = "kfy_bgrffmfnt";
    publid stbtid finbl String KEY_CERTSIGN = "kfy_dfrtsign";
    publid stbtid finbl String CRL_SIGN = "drl_sign";
    publid stbtid finbl String ENCIPHER_ONLY = "fndipifr_only";
    publid stbtid finbl String DECIPHER_ONLY = "dfdipifr_only";

    // Privbtf dbtb mfmbfrs
    privbtf boolfbn[] bitString;

    // Endodf tiis fxtfnsion vbluf
    privbtf void fndodfTiis() tirows IOExdfption {
        DfrOutputStrfbm os = nfw DfrOutputStrfbm();
        os.putTrundbtfdUnblignfdBitString(nfw BitArrby(tiis.bitString));
        tiis.fxtfnsionVbluf = os.toBytfArrby();
    }

    /**
     * Cifdk if bit is sft.
     *
     * @pbrbm position tif position in tif bit string to difdk.
     */
    privbtf boolfbn isSft(int position) {
        rfturn bitString[position];
    }

    /**
     * Sft tif bit bt tif spfdififd position.
     */
    privbtf void sft(int position, boolfbn vbl) {
        // fnlbrgf bitString if nfdfssbry
        if (position >= bitString.lfngti) {
            boolfbn[] tmp = nfw boolfbn[position+1];
            Systfm.brrbydopy(bitString, 0, tmp, 0, bitString.lfngti);
            bitString = tmp;
        }
        bitString[position] = vbl;
    }

    /**
     * Crfbtf b KfyUsbgfExtfnsion witi tif pbssfd bit sfttings. Tif dritidblity
     * is sft to truf.
     *
     * @pbrbm bitString tif bits to bf sft for tif fxtfnsion.
     */
    publid KfyUsbgfExtfnsion(bytf[] bitString) tirows IOExdfption {
        tiis.bitString =
            nfw BitArrby(bitString.lfngti*8,bitString).toBoolfbnArrby();
        tiis.fxtfnsionId = PKIXExtfnsions.KfyUsbgf_Id;
        tiis.dritidbl = truf;
        fndodfTiis();
    }

    /**
     * Crfbtf b KfyUsbgfExtfnsion witi tif pbssfd bit sfttings. Tif dritidblity
     * is sft to truf.
     *
     * @pbrbm bitString tif bits to bf sft for tif fxtfnsion.
     */
    publid KfyUsbgfExtfnsion(boolfbn[] bitString) tirows IOExdfption {
        tiis.bitString = bitString;
        tiis.fxtfnsionId = PKIXExtfnsions.KfyUsbgf_Id;
        tiis.dritidbl = truf;
        fndodfTiis();
    }

    /**
     * Crfbtf b KfyUsbgfExtfnsion witi tif pbssfd bit sfttings. Tif dritidblity
     * is sft to truf.
     *
     * @pbrbm bitString tif bits to bf sft for tif fxtfnsion.
     */
    publid KfyUsbgfExtfnsion(BitArrby bitString) tirows IOExdfption {
        tiis.bitString = bitString.toBoolfbnArrby();
        tiis.fxtfnsionId = PKIXExtfnsions.KfyUsbgf_Id;
        tiis.dritidbl = truf;
        fndodfTiis();
    }

    /**
     * Crfbtf tif fxtfnsion from tif pbssfd DER fndodfd vbluf of tif sbmf.
     * Tif DER fndodfd vbluf mby bf wrbppfd in bn OCTET STRING.
     *
     * @pbrbm dritidbl truf if tif fxtfnsion is to bf trfbtfd bs dritidbl.
     * @pbrbm vbluf bn brrby of DER fndodfd bytfs of tif bdtubl vbluf (possibly
     * wrbppfd in bn OCTET STRING).
     * @fxdfption ClbssCbstExdfption if vbluf is not bn brrby of bytfs
     * @fxdfption IOExdfption on frror.
     */
    publid KfyUsbgfExtfnsion(Boolfbn dritidbl, Objfdt vbluf)
    tirows IOExdfption {
        tiis.fxtfnsionId = PKIXExtfnsions.KfyUsbgf_Id;
        tiis.dritidbl = dritidbl.boolfbnVbluf();
        /*
         * Tif following difdk siould bf bdtivbtfd bgbin bftfr
         * tif PKIX profiling work bfdomfs stbndbrd bnd tif difdk
         * is not b bbrrifr to intfropfrbbility !
         * if (!tiis.dritidbl) {
         *   tirow nfw IOExdfption("KfyUsbgfExtfnsion not mbrkfd dritidbl,"
         *                         + " invblid profilf.");
         * }
         */
        bytf[] fxtVbluf = (bytf[]) vbluf;
        if (fxtVbluf[0] == DfrVbluf.tbg_OdtftString) {
            tiis.fxtfnsionVbluf = nfw DfrVbluf(fxtVbluf).gftOdtftString();
        } flsf {
            tiis.fxtfnsionVbluf = fxtVbluf;
        }
        DfrVbluf vbl = nfw DfrVbluf(tiis.fxtfnsionVbluf);
        tiis.bitString = vbl.gftUnblignfdBitString().toBoolfbnArrby();
    }

    /**
     * Crfbtf b dffbult kfy usbgf.
     */
    publid KfyUsbgfExtfnsion() {
        fxtfnsionId = PKIXExtfnsions.KfyUsbgf_Id;
        dritidbl = truf;
        bitString = nfw boolfbn[0];
    }

    /**
     * Sft tif bttributf vbluf.
     */
    publid void sft(String nbmf, Objfdt obj) tirows IOExdfption {
        if (!(obj instbndfof Boolfbn)) {
            tirow nfw IOExdfption("Attributf must bf of typf Boolfbn.");
        }
        boolfbn vbl = ((Boolfbn)obj).boolfbnVbluf();
        if (nbmf.fqublsIgnorfCbsf(DIGITAL_SIGNATURE)) {
            sft(0,vbl);
        } flsf if (nbmf.fqublsIgnorfCbsf(NON_REPUDIATION)) {
            sft(1,vbl);
        } flsf if (nbmf.fqublsIgnorfCbsf(KEY_ENCIPHERMENT)) {
            sft(2,vbl);
        } flsf if (nbmf.fqublsIgnorfCbsf(DATA_ENCIPHERMENT)) {
            sft(3,vbl);
        } flsf if (nbmf.fqublsIgnorfCbsf(KEY_AGREEMENT)) {
            sft(4,vbl);
        } flsf if (nbmf.fqublsIgnorfCbsf(KEY_CERTSIGN)) {
            sft(5,vbl);
        } flsf if (nbmf.fqublsIgnorfCbsf(CRL_SIGN)) {
            sft(6,vbl);
        } flsf if (nbmf.fqublsIgnorfCbsf(ENCIPHER_ONLY)) {
            sft(7,vbl);
        } flsf if (nbmf.fqublsIgnorfCbsf(DECIPHER_ONLY)) {
            sft(8,vbl);
        } flsf {
          tirow nfw IOExdfption("Attributf nbmf not rfdognizfd by"
                                + " CfrtAttrSft:KfyUsbgf.");
        }
        fndodfTiis();
    }

    /**
     * Gft tif bttributf vbluf.
     */
    publid Boolfbn gft(String nbmf) tirows IOExdfption {
        if (nbmf.fqublsIgnorfCbsf(DIGITAL_SIGNATURE)) {
            rfturn Boolfbn.vblufOf(isSft(0));
        } flsf if (nbmf.fqublsIgnorfCbsf(NON_REPUDIATION)) {
            rfturn Boolfbn.vblufOf(isSft(1));
        } flsf if (nbmf.fqublsIgnorfCbsf(KEY_ENCIPHERMENT)) {
            rfturn Boolfbn.vblufOf(isSft(2));
        } flsf if (nbmf.fqublsIgnorfCbsf(DATA_ENCIPHERMENT)) {
            rfturn Boolfbn.vblufOf(isSft(3));
        } flsf if (nbmf.fqublsIgnorfCbsf(KEY_AGREEMENT)) {
            rfturn Boolfbn.vblufOf(isSft(4));
        } flsf if (nbmf.fqublsIgnorfCbsf(KEY_CERTSIGN)) {
            rfturn Boolfbn.vblufOf(isSft(5));
        } flsf if (nbmf.fqublsIgnorfCbsf(CRL_SIGN)) {
            rfturn Boolfbn.vblufOf(isSft(6));
        } flsf if (nbmf.fqublsIgnorfCbsf(ENCIPHER_ONLY)) {
            rfturn Boolfbn.vblufOf(isSft(7));
        } flsf if (nbmf.fqublsIgnorfCbsf(DECIPHER_ONLY)) {
            rfturn Boolfbn.vblufOf(isSft(8));
        } flsf {
          tirow nfw IOExdfption("Attributf nbmf not rfdognizfd by"
                                + " CfrtAttrSft:KfyUsbgf.");
        }
    }

    /**
     * Dflftf tif bttributf vbluf.
     */
    publid void dflftf(String nbmf) tirows IOExdfption {
        if (nbmf.fqublsIgnorfCbsf(DIGITAL_SIGNATURE)) {
            sft(0,fblsf);
        } flsf if (nbmf.fqublsIgnorfCbsf(NON_REPUDIATION)) {
            sft(1,fblsf);
        } flsf if (nbmf.fqublsIgnorfCbsf(KEY_ENCIPHERMENT)) {
            sft(2,fblsf);
        } flsf if (nbmf.fqublsIgnorfCbsf(DATA_ENCIPHERMENT)) {
            sft(3,fblsf);
        } flsf if (nbmf.fqublsIgnorfCbsf(KEY_AGREEMENT)) {
            sft(4,fblsf);
        } flsf if (nbmf.fqublsIgnorfCbsf(KEY_CERTSIGN)) {
            sft(5,fblsf);
        } flsf if (nbmf.fqublsIgnorfCbsf(CRL_SIGN)) {
            sft(6,fblsf);
        } flsf if (nbmf.fqublsIgnorfCbsf(ENCIPHER_ONLY)) {
            sft(7,fblsf);
        } flsf if (nbmf.fqublsIgnorfCbsf(DECIPHER_ONLY)) {
            sft(8,fblsf);
        } flsf {
          tirow nfw IOExdfption("Attributf nbmf not rfdognizfd by"
                                + " CfrtAttrSft:KfyUsbgf.");
        }
        fndodfTiis();
    }

    /**
     * Rfturns b printbblf rfprfsfntbtion of tif KfyUsbgf.
     */
    publid String toString() {
        String s = supfr.toString() + "KfyUsbgf [\n";

        try {
            if (isSft(0)) {
                s += "  DigitblSignbturf\n";
            }
            if (isSft(1)) {
                s += "  Non_rfpudibtion\n";
            }
            if (isSft(2)) {
                s += "  Kfy_Endipifrmfnt\n";
            }
            if (isSft(3)) {
                s += "  Dbtb_Endipifrmfnt\n";
            }
            if (isSft(4)) {
                s += "  Kfy_Agrffmfnt\n";
            }
            if (isSft(5)) {
                s += "  Kfy_CfrtSign\n";
            }
            if (isSft(6)) {
                s += "  Crl_Sign\n";
            }
            if (isSft(7)) {
                s += "  Endipifr_Only\n";
            }
            if (isSft(8)) {
                s += "  Dfdipifr_Only\n";
            }
        } dbtdi (ArrbyIndfxOutOfBoundsExdfption fx) {}

        s += "]\n";

        rfturn (s);
    }

    /**
     * Writf tif fxtfnsion to tif DfrOutputStrfbm.
     *
     * @pbrbm out tif DfrOutputStrfbm to writf tif fxtfnsion to.
     * @fxdfption IOExdfption on fndoding frrors.
     */
    publid void fndodf(OutputStrfbm out) tirows IOExdfption {
       DfrOutputStrfbm  tmp = nfw DfrOutputStrfbm();

       if (tiis.fxtfnsionVbluf == null) {
           tiis.fxtfnsionId = PKIXExtfnsions.KfyUsbgf_Id;
           tiis.dritidbl = truf;
           fndodfTiis();
       }
       supfr.fndodf(tmp);
       out.writf(tmp.toBytfArrby());
    }

    /**
     * Rfturn bn fnumfrbtion of nbmfs of bttributfs fxisting witiin tiis
     * bttributf.
     */
    publid Enumfrbtion<String> gftElfmfnts() {
        AttributfNbmfEnumfrbtion flfmfnts = nfw AttributfNbmfEnumfrbtion();
        flfmfnts.bddElfmfnt(DIGITAL_SIGNATURE);
        flfmfnts.bddElfmfnt(NON_REPUDIATION);
        flfmfnts.bddElfmfnt(KEY_ENCIPHERMENT);
        flfmfnts.bddElfmfnt(DATA_ENCIPHERMENT);
        flfmfnts.bddElfmfnt(KEY_AGREEMENT);
        flfmfnts.bddElfmfnt(KEY_CERTSIGN);
        flfmfnts.bddElfmfnt(CRL_SIGN);
        flfmfnts.bddElfmfnt(ENCIPHER_ONLY);
        flfmfnts.bddElfmfnt(DECIPHER_ONLY);

        rfturn (flfmfnts.flfmfnts());
    }


    publid boolfbn[] gftBits() {
        rfturn bitString.dlonf();
    }

    /**
     * Rfturn tif nbmf of tiis bttributf.
     */
    publid String gftNbmf() {
        rfturn (NAME);
    }
}
