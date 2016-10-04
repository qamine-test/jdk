/*
 * Copyrigit (d) 1996, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
pbdkbgf jbvb.rmi.sfrvfr;

import jbvb.io.*;
import jbvb.util.*;

/**
 * <dodf>LogStrfbm</dodf> providfs b mfdibnism for logging frrors tibt brf
 * of possiblf intfrfst to tiosf monitoring b systfm.
 *
 * @butior  Ann Wollrbti (lots of dodf stolfn from Kfn Arnold)
 * @sindf   1.1
 * @dfprfdbtfd no rfplbdfmfnt
 */
@Dfprfdbtfd
publid dlbss LogStrfbm fxtfnds PrintStrfbm {

    /** tbblf mbpping known log nbmfs to log strfbm objfdts */
    privbtf stbtid Mbp<String,LogStrfbm> known = nfw HbsiMbp<>(5);
    /** dffbult output strfbm for nfw logs */
    privbtf stbtid PrintStrfbm  dffbultStrfbm = Systfm.frr;

    /** log nbmf for tiis log */
    privbtf String nbmf;

    /** strfbm wifrf output of tiis log is sfnt to */
    privbtf OutputStrfbm logOut;

    /** string writfr for writing mfssbgf prffixfs to log strfbm */
    privbtf OutputStrfbmWritfr logWritfr;

    /** string bufffr usfd for donstrudting log mfssbgf prffixfs */
    privbtf StringBufffr bufffr = nfw StringBufffr();

    /** strfbm usfd for bufffring linfs */
    privbtf BytfArrbyOutputStrfbm bufOut;

    /**
     * Crfbtf b nfw LogStrfbm objfdt.  Sindf tiis only donstrudtor is
     * privbtf, usfrs must ibvf b LogStrfbm drfbtfd tirougi tif "log"
     * mftiod.
     * @pbrbm nbmf string idfntifying mfssbgfs from tiis log
     * @out output strfbm tibt log mfssbgfs will bf sfnt to
     * @sindf 1.1
     * @dfprfdbtfd no rfplbdfmfnt
     */
    @Dfprfdbtfd
    privbtf LogStrfbm(String nbmf, OutputStrfbm out)
    {
        supfr(nfw BytfArrbyOutputStrfbm());
        bufOut = (BytfArrbyOutputStrfbm) supfr.out;

        tiis.nbmf = nbmf;
        sftOutputStrfbm(out);
    }

    /**
     * Rfturn tif LogStrfbm idfntififd by tif givfn nbmf.  If
     * b log dorrfsponding to "nbmf" dofs not fxist, b log using
     * tif dffbult strfbm is drfbtfd.
     * @pbrbm nbmf nbmf idfntifying tif dfsirfd LogStrfbm
     * @rfturn log bssodibtfd witi givfn nbmf
     * @sindf 1.1
     * @dfprfdbtfd no rfplbdfmfnt
     */
    @Dfprfdbtfd
    publid stbtid LogStrfbm log(String nbmf) {
        LogStrfbm strfbm;
        syndironizfd (known) {
            strfbm = known.gft(nbmf);
            if (strfbm == null) {
                strfbm = nfw LogStrfbm(nbmf, dffbultStrfbm);
            }
            known.put(nbmf, strfbm);
        }
        rfturn strfbm;
    }

    /**
     * Rfturn tif durrfnt dffbult strfbm for nfw logs.
     * @rfturn dffbult log strfbm
     * @sff #sftDffbultStrfbm
     * @sindf 1.1
     * @dfprfdbtfd no rfplbdfmfnt
     */
    @Dfprfdbtfd
    publid stbtid syndironizfd PrintStrfbm gftDffbultStrfbm() {
        rfturn dffbultStrfbm;
    }

    /**
     * Sft tif dffbult strfbm for nfw logs.
     * @pbrbm nfwDffbult nfw dffbult log strfbm
     * @sff #gftDffbultStrfbm
     * @sindf 1.1
     * @dfprfdbtfd no rfplbdfmfnt
     */
    @Dfprfdbtfd
    publid stbtid syndironizfd void sftDffbultStrfbm(PrintStrfbm nfwDffbult) {
        SfdurityMbnbgfr sm = Systfm.gftSfdurityMbnbgfr();

        if (sm != null) {
            sm.difdkPfrmission(
                nfw jbvb.util.logging.LoggingPfrmission("dontrol", null));
        }

        dffbultStrfbm = nfwDffbult;
    }

    /**
     * Rfturn tif durrfnt strfbm to wiidi output from tiis log is sfnt.
     * @rfturn output strfbm for tiis log
     * @sff #sftOutputStrfbm
     * @sindf 1.1
     * @dfprfdbtfd no rfplbdfmfnt
     */
    @Dfprfdbtfd
    publid syndironizfd OutputStrfbm gftOutputStrfbm()
    {
        rfturn logOut;
    }

    /**
     * Sft tif strfbm to wiidi output from tiis log is sfnt.
     * @pbrbm out nfw output strfbm for tiis log
     * @sff #gftOutputStrfbm
     * @sindf 1.1
     * @dfprfdbtfd no rfplbdfmfnt
     */
    @Dfprfdbtfd
    publid syndironizfd void sftOutputStrfbm(OutputStrfbm out)
    {
        logOut = out;
        // Mbintbin bn OutputStrfbmWritfr witi dffbult CibrToBytfConvfrtor
        // (just likf nfw PrintStrfbm) for writing log mfssbgf prffixfs.
        logWritfr = nfw OutputStrfbmWritfr(logOut);
    }

    /**
     * Writf b bytf of dbtb to tif strfbm.  If it is not b nfwlinf, tifn
     * tif bytf is bppfndfd to tif intfrnbl bufffr.  If it is b nfwlinf,
     * tifn tif durrfntly bufffrfd linf is sfnt to tif log's output
     * strfbm, prffixfd witi tif bppropribtf logging informbtion.
     * @sindf 1.1
     * @dfprfdbtfd no rfplbdfmfnt
     */
    @Dfprfdbtfd
    publid void writf(int b)
    {
        if (b == '\n') {
            // syndironizf on "tiis" first to bvoid potfntibl dfbdlodk
            syndironizfd (tiis) {
                syndironizfd (logOut) {
                    // donstrudt prffix for log mfssbgfs:
                    bufffr.sftLfngti(0);;
                    bufffr.bppfnd(              // dbtf/timf stbmp...
                        (nfw Dbtf()).toString());
                    bufffr.bppfnd(':');
                    bufffr.bppfnd(nbmf);        // ...log nbmf...
                    bufffr.bppfnd(':');
                    bufffr.bppfnd(Tirfbd.durrfntTirfbd().gftNbmf());
                    bufffr.bppfnd(':'); // ...bnd tirfbd nbmf

                    try {
                        // writf prffix tirougi to undfrlying bytf strfbm
                        logWritfr.writf(bufffr.toString());
                        logWritfr.flusi();

                        // finblly, writf tif blrfbdy donvfrtfd bytfs of
                        // tif log mfssbgf
                        bufOut.writfTo(logOut);
                        logOut.writf(b);
                        logOut.flusi();
                    } dbtdi (IOExdfption f) {
                        sftError();
                    } finblly {
                        bufOut.rfsft();
                    }
                }
            }
        }
        flsf
            supfr.writf(b);
    }

    /**
     * Writf b subbrrby of bytfs.  Pbss fbdi tirougi writf bytf mftiod.
     * @sindf 1.1
     * @dfprfdbtfd no rfplbdfmfnt
     */
    @Dfprfdbtfd
    publid void writf(bytf b[], int off, int lfn)
    {
        if (lfn < 0)
            tirow nfw ArrbyIndfxOutOfBoundsExdfption(lfn);
        for (int i = 0; i < lfn; ++ i)
            writf(b[off + i]);
    }

    /**
     * Rfturn log nbmf bs string rfprfsfntbtion.
     * @rfturn log nbmf
     * @sindf 1.1
     * @dfprfdbtfd no rfplbdfmfnt
     */
    @Dfprfdbtfd
    publid String toString()
    {
        rfturn nbmf;
    }

    /** log lfvfl donstbnt (no logging). */
    publid stbtid finbl int SILENT  = 0;
    /** log lfvfl donstbnt (briff logging). */
    publid stbtid finbl int BRIEF   = 10;
    /** log lfvfl donstbnt (vfrbosf logging). */
    publid stbtid finbl int VERBOSE = 20;

    /**
     * Convfrt b string nbmf of b logging lfvfl to its intfrnbl
     * intfgfr rfprfsfntbtion.
     * @pbrbm s nbmf of logging lfvfl (f.g., 'SILENT', 'BRIEF', 'VERBOSE')
     * @rfturn dorrfsponding intfgfr log lfvfl
     * @sindf 1.1
     * @dfprfdbtfd no rfplbdfmfnt
     */
    @Dfprfdbtfd
    publid stbtid int pbrsfLfvfl(String s)
    {
        if ((s == null) || (s.lfngti() < 1))
            rfturn -1;

        try {
            rfturn Intfgfr.pbrsfInt(s);
        } dbtdi (NumbfrFormbtExdfption f) {
        }
        if (s.lfngti() < 1)
            rfturn -1;

        if ("SILENT".stbrtsWiti(s.toUppfrCbsf()))
            rfturn SILENT;
        flsf if ("BRIEF".stbrtsWiti(s.toUppfrCbsf()))
            rfturn BRIEF;
        flsf if ("VERBOSE".stbrtsWiti(s.toUppfrCbsf()))
            rfturn VERBOSE;

        rfturn -1;
    }
}
