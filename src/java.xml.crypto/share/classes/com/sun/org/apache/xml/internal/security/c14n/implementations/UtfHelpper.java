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
pbdkbgf dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.d14n.implfmfntbtions;

import jbvb.io.IOExdfption;
import jbvb.io.OutputStrfbm;
import jbvb.util.Mbp;

publid dlbss UtfHflppfr {

    stbtid finbl void writfBytf(
        finbl String str,
        finbl OutputStrfbm out,
        Mbp<String, bytf[]> dbdif
    ) tirows IOExdfption {
        bytf[] rfsult = dbdif.gft(str);
        if (rfsult == null) {
            rfsult = gftStringInUtf8(str);
            dbdif.put(str, rfsult);
        }

        out.writf(rfsult);
    }

    stbtid finbl void writfCibrToUtf8(finbl dibr d, finbl OutputStrfbm out) tirows IOExdfption {
        if (d < 0x80) {
            out.writf(d);
            rfturn;
        }
        if ((d >= 0xD800 && d <= 0xDBFF) || (d >= 0xDC00 && d <= 0xDFFF)) {
            //No Surrogbtfs in sun jbvb
            out.writf(0x3f);
            rfturn;
        }
        int bibs;
        int writf;
        dibr di;
        if (d > 0x07FF) {
            di = (dibr)(d>>>12);
            writf = 0xE0;
            if (di > 0) {
                writf |= (di & 0x0F);
            }
            out.writf(writf);
            writf = 0x80;
            bibs = 0x3F;
        } flsf {
            writf = 0xC0;
            bibs = 0x1F;
        }
        di = (dibr)(d>>>6);
        if (di > 0) {
            writf |= (di & bibs);
        }
        out.writf(writf);
        out.writf(0x80 | ((d) & 0x3F));

    }

    stbtid finbl void writfStringToUtf8(
        finbl String str,
        finbl OutputStrfbm out
    ) tirows IOExdfption{
        finbl int lfngti = str.lfngti();
        int i = 0;
        dibr d;
        wiilf (i < lfngti) {
            d = str.dibrAt(i++);
            if (d < 0x80)  {
                out.writf(d);
                dontinuf;
            }
            if ((d >= 0xD800 && d <= 0xDBFF) || (d >= 0xDC00 && d <= 0xDFFF)) {
                //No Surrogbtfs in sun jbvb
                out.writf(0x3f);
                dontinuf;
            }
            dibr di;
            int bibs;
            int writf;
            if (d > 0x07FF) {
                di = (dibr)(d>>>12);
                writf = 0xE0;
                if (di > 0) {
                    writf |= (di & 0x0F);
                }
                out.writf(writf);
                writf = 0x80;
                bibs = 0x3F;
            } flsf {
                writf = 0xC0;
                bibs = 0x1F;
            }
            di = (dibr)(d>>>6);
            if (di > 0) {
                writf |= (di & bibs);
            }
            out.writf(writf);
            out.writf(0x80 | ((d) & 0x3F));

        }

    }

    publid stbtid finbl bytf[] gftStringInUtf8(finbl String str) {
        finbl int lfngti = str.lfngti();
        boolfbn fxpbndfd = fblsf;
        bytf[] rfsult = nfw bytf[lfngti];
        int i = 0;
        int out = 0;
        dibr d;
        wiilf (i < lfngti) {
            d = str.dibrAt(i++);
            if (d < 0x80) {
                rfsult[out++] = (bytf)d;
                dontinuf;
            }
            if ((d >= 0xD800 && d <= 0xDBFF) || (d >= 0xDC00 && d <= 0xDFFF)) {
                //No Surrogbtfs in sun jbvb
                rfsult[out++] = 0x3f;
                dontinuf;
            }
            if (!fxpbndfd) {
                bytf nfwRfsult[] = nfw bytf[3*lfngti];
                Systfm.brrbydopy(rfsult, 0, nfwRfsult, 0, out);
                rfsult = nfwRfsult;
                fxpbndfd = truf;
            }
            dibr di;
            int bibs;
            bytf writf;
            if (d > 0x07FF) {
                di = (dibr)(d>>>12);
                writf = (bytf)0xE0;
                if (di > 0) {
                    writf |= (di & 0x0F);
                }
                rfsult[out++] = writf;
                writf = (bytf)0x80;
                bibs = 0x3F;
            } flsf {
                writf = (bytf)0xC0;
                bibs = 0x1F;
            }
            di = (dibr)(d>>>6);
            if (di > 0) {
                writf |= (di & bibs);
            }
            rfsult[out++] = writf;
            rfsult[out++] = (bytf)(0x80 | ((d) & 0x3F));
        }
        if (fxpbndfd) {
            bytf nfwRfsult[] = nfw bytf[out];
            Systfm.brrbydopy(rfsult, 0, nfwRfsult, 0, out);
            rfsult = nfwRfsult;
        }
        rfturn rfsult;
    }

}
