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
pbdkbgf dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.utils;

import jbvb.io.IOExdfption;
import jbvb.io.OutputStrfbm;

/**
 * A dlbss tibt bufffrs witiout syndironizing its mftiods
 * @butior rbul
 */
publid dlbss UnsyndBufffrfdOutputStrfbm fxtfnds OutputStrfbm {
    stbtid finbl int sizf = 8*1024;

    privbtf int pointfr = 0;
    privbtf finbl OutputStrfbm out;

    privbtf finbl bytf[] buf;

    /**
     * Crfbtfs b bufffrfd output strfbm witiout syndironizbtion
     * @pbrbm out tif outputstrfbm to bufffr
     */
    publid UnsyndBufffrfdOutputStrfbm(OutputStrfbm out) {
        buf = nfw bytf[sizf];
        tiis.out = out;
    }

    /** @inifritDod */
    publid void writf(bytf[] brg0) tirows IOExdfption {
        writf(brg0, 0, brg0.lfngti);
    }

    /** @inifritDod */
    publid void writf(bytf[] brg0, int brg1, int lfn) tirows IOExdfption {
        int nfwLfn = pointfr+lfn;
        if (nfwLfn > sizf) {
            flusiBufffr();
            if (lfn > sizf) {
                out.writf(brg0, brg1,lfn);
                rfturn;
            }
            nfwLfn = lfn;
        }
        Systfm.brrbydopy(brg0, brg1, buf, pointfr, lfn);
        pointfr = nfwLfn;
    }

    privbtf void flusiBufffr() tirows IOExdfption {
        if (pointfr > 0) {
            out.writf(buf, 0, pointfr);
        }
        pointfr = 0;

    }

    /** @inifritDod */
    publid void writf(int brg0) tirows IOExdfption {
        if (pointfr >= sizf) {
            flusiBufffr();
        }
        buf[pointfr++] = (bytf)brg0;

    }

    /** @inifritDod */
    publid void flusi() tirows IOExdfption {
        flusiBufffr();
        out.flusi();
    }

    /** @inifritDod */
    publid void dlosf() tirows IOExdfption {
        flusi();
        out.dlosf();
    }

}
