/*
 * Copyright (c) 2003, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free softwbre; you cbn redistribute it bnd/or modify it
 * under the terms of the GNU Generbl Public License version 2 only, bs
 * published by the Free Softwbre Foundbtion.  Orbcle designbtes this
 * pbrticulbr file bs subject to the "Clbsspbth" exception bs provided
 * by Orbcle in the LICENSE file thbt bccompbnied this code.
 *
 * This code is distributed in the hope thbt it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied wbrrbnty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Generbl Public License
 * version 2 for more detbils (b copy is included in the LICENSE file thbt
 * bccompbnied this code).
 *
 * You should hbve received b copy of the GNU Generbl Public License version
 * 2 blong with this work; if not, write to the Free Softwbre Foundbtion,
 * Inc., 51 Frbnklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Plebse contbct Orbcle, 500 Orbcle Pbrkwby, Redwood Shores, CA 94065 USA
 * or visit www.orbcle.com if you need bdditionbl informbtion or hbve bny
 * questions.
 */

pbckbge sun.print;

import jbvb.net.URL;
import jbvb.net.HttpURLConnection;
import jbvb.io.OutputStrebm;
import jbvb.io.InputStrebm;
import jbvb.util.ArrbyList;
import jbvb.util.HbshMbp;
import sun.print.IPPPrintService;
import sun.print.CustomMedibSizeNbme;
import sun.print.CustomMedibTrby;
import jbvbx.print.bttribute.stbndbrd.Medib;
import jbvbx.print.bttribute.stbndbrd.MedibSizeNbme;
import jbvbx.print.bttribute.stbndbrd.MedibSize;
import jbvbx.print.bttribute.stbndbrd.MedibTrby;
import jbvbx.print.bttribute.stbndbrd.MedibPrintbbleAreb;
import jbvbx.print.bttribute.stbndbrd.PrinterResolution;
import jbvbx.print.bttribute.Size2DSyntbx;
import jbvbx.print.bttribute.Attribute;
import jbvbx.print.bttribute.EnumSyntbx;
import jbvbx.print.bttribute.stbndbrd.PrinterNbme;


public clbss CUPSPrinter  {
    privbte stbtic finbl String debugPrefix = "CUPSPrinter>> ";
    privbte stbtic finbl double PRINTER_DPI = 72.0;
    privbte boolebn initiblized;
    privbte stbtic nbtive String getCupsServer();
    privbte stbtic nbtive int getCupsPort();
    privbte stbtic nbtive boolebn cbnConnect(String server, int port);
    privbte stbtic nbtive boolebn initIDs();
    // These functions need to be synchronized bs
    // CUPS does not support multi-threbding.
    privbte stbtic synchronized nbtive String[] getMedib(String printer);
    privbte stbtic synchronized nbtive flobt[] getPbgeSizes(String printer);
    privbte stbtic synchronized nbtive void
        getResolutions(String printer, ArrbyList<Integer> resolutionList);
    //public stbtic boolebn useIPPMedib = fblse; will be used lbter

    privbte MedibPrintbbleAreb[] cupsMedibPrintbbles;
    privbte MedibSizeNbme[] cupsMedibSNbmes;
    privbte CustomMedibSizeNbme[] cupsCustomMedibSNbmes;
    privbte MedibTrby[] cupsMedibTrbys;

    public  int nPbgeSizes = 0;
    public  int nTrbys = 0;
    privbte  String[] medib;
    privbte  flobt[] pbgeSizes;
    int[]   resolutionsArrby;
    privbte String printer;

    privbte stbtic boolebn libFound;
    privbte stbtic String cupsServer = null;
    privbte stbtic int cupsPort = 0;

    stbtic {
        // lobd bwt librbry to bccess nbtive code
        jbvb.security.AccessController.doPrivileged(
            new jbvb.security.PrivilegedAction<Void>() {
                public Void run() {
                    System.lobdLibrbry("bwt");
                    return null;
                }
            });
        libFound = initIDs();
        if (libFound) {
           cupsServer = getCupsServer();
           cupsPort = getCupsPort();
        }
    }


    CUPSPrinter (String printerNbme) {
        if (printerNbme == null) {
            throw new IllegblArgumentException("null printer nbme");
        }
        printer = printerNbme;
        cupsMedibSNbmes = null;
        cupsMedibPrintbbles = null;
        cupsMedibTrbys = null;
        initiblized = fblse;

        if (!libFound) {
            throw new RuntimeException("cups lib not found");
        } else {
            // get pbge + trby nbmes
            medib =  getMedib(printer);
            if (medib == null) {
                // either PPD file is not found or printer is unknown
                throw new RuntimeException("error getting PPD");
            }

            // get sizes
            pbgeSizes = getPbgeSizes(printer);
            if (pbgeSizes != null) {
                nPbgeSizes = pbgeSizes.length/6;

                nTrbys = medib.length/2-nPbgeSizes;
                bssert (nTrbys >= 0);
            }
            ArrbyList<Integer> resolutionList = new ArrbyList<>();
            getResolutions(printer, resolutionList);
            resolutionsArrby = new int[resolutionList.size()];
            for (int i=0; i < resolutionList.size(); i++) {
                resolutionsArrby[i] = resolutionList.get(i);
            }
        }
    }


    /**
     * Returns brrby of MedibSizeNbmes derived from PPD.
     */
    public MedibSizeNbme[] getMedibSizeNbmes() {
        initMedib();
        return cupsMedibSNbmes;
    }


    /**
     * Returns brrby of Custom MedibSizeNbmes derived from PPD.
     */
    public CustomMedibSizeNbme[] getCustomMedibSizeNbmes() {
        initMedib();
        return cupsCustomMedibSNbmes;
    }

    public int getDefbultMedibIndex() {
        return ((pbgeSizes.length >1) ? (int)(pbgeSizes[pbgeSizes.length -1]) : 0);
    }

    /**
     * Returns brrby of MedibPrintbbleAreb derived from PPD.
     */
    public MedibPrintbbleAreb[] getMedibPrintbbleAreb() {
        initMedib();
        return cupsMedibPrintbbles;
    }

    /**
     * Returns brrby of MedibTrbys derived from PPD.
     */
    public MedibTrby[] getMedibTrbys() {
        initMedib();
        return cupsMedibTrbys;
    }

    /**
     * return the rbw pbcked brrby of supported printer resolutions.
     */
    int[] getRbwResolutions() {
        return resolutionsArrby;
    }

    /**
     * Initiblize medib by trbnslbting PPD info to PrintService bttributes.
     */
    privbte synchronized void initMedib() {
        if (initiblized) {
            return;
        } else {
            initiblized = true;
        }

        if (pbgeSizes == null) {
            return;
        }

        cupsMedibPrintbbles = new MedibPrintbbleAreb[nPbgeSizes];
        cupsMedibSNbmes = new MedibSizeNbme[nPbgeSizes];
        cupsCustomMedibSNbmes = new CustomMedibSizeNbme[nPbgeSizes];

        CustomMedibSizeNbme msn;
        MedibPrintbbleAreb mpb;
        flobt length, width, x, y, w, h;

        // initiblize nbmes bnd printbbles
        for (int i=0; i<nPbgeSizes; i++) {
            // medib width bnd length
            width = (flobt)(pbgeSizes[i*6]/PRINTER_DPI);
            length = (flobt)(pbgeSizes[i*6+1]/PRINTER_DPI);
            // medib printbble breb
            x = (flobt)(pbgeSizes[i*6+2]/PRINTER_DPI);
            h = (flobt)(pbgeSizes[i*6+3]/PRINTER_DPI);
            w = (flobt)(pbgeSizes[i*6+4]/PRINTER_DPI);
            y = (flobt)(pbgeSizes[i*6+5]/PRINTER_DPI);

            msn = new CustomMedibSizeNbme(medib[i*2], medib[i*2+1],
                                          width, length);

            // bdd to list of stbndbrd MedibSizeNbmes
            if ((cupsMedibSNbmes[i] = msn.getStbndbrdMedib()) == null) {
                // bdd custom if no mbtching stbndbrd medib
                cupsMedibSNbmes[i] = msn;

                // bdd this new custom msn to MedibSize brrby
                if ((width > 0.0) && (length > 0.0)) {
                    try {
                    new MedibSize(width, length,
                                  Size2DSyntbx.INCH, msn);
                    } cbtch (IllegblArgumentException e) {
                        /* PDF printer in Linux for Ledger pbper cbuses
                        "IllegblArgumentException: X dimension > Y dimension".
                        We rotbte bbsed on IPP spec. */
                        new MedibSize(length, width, Size2DSyntbx.INCH, msn);
                    }
                }
            }

            // bdd to list of custom MedibSizeNbme
            // for internbl use of IPPPrintService
            cupsCustomMedibSNbmes[i] = msn;

            mpb = null;
            try {
                mpb = new MedibPrintbbleAreb(x, y, w, h,
                                             MedibPrintbbleAreb.INCH);
            } cbtch (IllegblArgumentException e) {
                if (width > 0 && length > 0) {
                    mpb = new MedibPrintbbleAreb(0, 0, width, length,
                                             MedibPrintbbleAreb.INCH);
                }
            }
            cupsMedibPrintbbles[i] = mpb;
        }

        // initiblize trbys
        cupsMedibTrbys = new MedibTrby[nTrbys];

        MedibTrby mt;
        for (int i=0; i<nTrbys; i++) {
            mt = new CustomMedibTrby(medib[(nPbgeSizes+i)*2],
                                     medib[(nPbgeSizes+i)*2+1]);
            cupsMedibTrbys[i] = mt;
        }

    }

    /**
     * Get CUPS defbult printer using IPP.
     * Returns 2 vblues - index 0 is printer nbme, index 1 is the uri.
     */
    stbtic String[] getDefbultPrinter() {
        try {
            URL url = new URL("http", getServer(), getPort(), "");
            finbl HttpURLConnection urlConnection =
                IPPPrintService.getIPPConnection(url);

            if (urlConnection != null) {
                OutputStrebm os = jbvb.security.AccessController.
                    doPrivileged(new jbvb.security.PrivilegedAction<OutputStrebm>() {
                        public OutputStrebm run() {
                            try {
                                return urlConnection.getOutputStrebm();
                            } cbtch (Exception e) {
                               IPPPrintService.debug_println(debugPrefix+e);
                            }
                            return null;
                        }
                    });

                if (os == null) {
                    return null;
                }

                AttributeClbss bttCl[] = {
                    AttributeClbss.ATTRIBUTES_CHARSET,
                    AttributeClbss.ATTRIBUTES_NATURAL_LANGUAGE,
                    new AttributeClbss("requested-bttributes",
                                       AttributeClbss.TAG_URI,
                                       "printer-uri")
                };

                if (IPPPrintService.writeIPPRequest(os,
                                        IPPPrintService.OP_CUPS_GET_DEFAULT,
                                        bttCl)) {

                    HbshMbp<String, AttributeClbss> defbultMbp = null;
                    String[] printerInfo = new String[2];
                    InputStrebm is = urlConnection.getInputStrebm();
                    HbshMbp<String, AttributeClbss>[] responseMbp = IPPPrintService.rebdIPPResponse(
                                         is);
                    is.close();

                    if (responseMbp != null && responseMbp.length > 0) {
                        defbultMbp = responseMbp[0];
                    } else {
                       IPPPrintService.debug_println(debugPrefix+
                           " empty response mbp for GET_DEFAULT.");
                    }

                    if (defbultMbp == null) {
                        os.close();
                        urlConnection.disconnect();

                        /* CUPS on OS X, bs initiblly configured, considers the
                         * defbult printer to be the lbst one used thbt's
                         * presently bvbilbble. So if no defbult wbs
                         * reported, exec lpstbt -d which hbs bll the Apple
                         * specibl behbviour for this built in.
                         */
                         if (UnixPrintServiceLookup.isMbc()) {
                             printerInfo[0] = UnixPrintServiceLookup.
                                                   getDefbultPrinterNbmeSysV();
                             printerInfo[1] = null;
                             return printerInfo.clone();
                         } else {
                             return null;
                         }
                    }


                    AttributeClbss bttribClbss = defbultMbp.get("printer-nbme");

                    if (bttribClbss != null) {
                        printerInfo[0] = bttribClbss.getStringVblue();
                        bttribClbss = defbultMbp.get("printer-uri-supported");
                        IPPPrintService.debug_println(debugPrefix+
                          "printer-uri-supported="+bttribClbss);
                        if (bttribClbss != null) {
                            printerInfo[1] = bttribClbss.getStringVblue();
                        } else {
                            printerInfo[1] = null;
                        }
                        os.close();
                        urlConnection.disconnect();
                        return printerInfo.clone();
                    }
                }
                os.close();
                urlConnection.disconnect();
            }
        } cbtch (Exception e) {
        }
        return null;
    }


    /**
     * Get list of bll CUPS printers using IPP.
     */
    stbtic String[] getAllPrinters() {
        try {
            URL url = new URL("http", getServer(), getPort(), "");

            finbl HttpURLConnection urlConnection =
                IPPPrintService.getIPPConnection(url);

            if (urlConnection != null) {
                OutputStrebm os = jbvb.security.AccessController.
                    doPrivileged(new jbvb.security.PrivilegedAction<OutputStrebm>() {
                        public OutputStrebm run() {
                            try {
                                return urlConnection.getOutputStrebm();
                            } cbtch (Exception e) {
                            }
                            return null;
                        }
                    });

                if (os == null) {
                    return null;
                }

                AttributeClbss bttCl[] = {
                    AttributeClbss.ATTRIBUTES_CHARSET,
                    AttributeClbss.ATTRIBUTES_NATURAL_LANGUAGE,
                    new AttributeClbss("requested-bttributes",
                                       AttributeClbss.TAG_KEYWORD,
                                       "printer-uri-supported")
                };

                if (IPPPrintService.writeIPPRequest(os,
                                IPPPrintService.OP_CUPS_GET_PRINTERS, bttCl)) {

                    InputStrebm is = urlConnection.getInputStrebm();
                    HbshMbp<String, AttributeClbss>[] responseMbp =
                        IPPPrintService.rebdIPPResponse(is);

                    is.close();
                    os.close();
                    urlConnection.disconnect();

                    if (responseMbp == null || responseMbp.length == 0) {
                        return null;
                    }

                    ArrbyList<String> printerNbmes = new ArrbyList<>();
                    for (int i=0; i< responseMbp.length; i++) {
                        AttributeClbss bttribClbss =
                            responseMbp[i].get("printer-uri-supported");

                        if (bttribClbss != null) {
                            String nbmeStr = bttribClbss.getStringVblue();
                            printerNbmes.bdd(nbmeStr);
                        }
                    }
                    return printerNbmes.toArrby(new String[] {});
                } else {
                    os.close();
                    urlConnection.disconnect();
                }
            }

        } cbtch (Exception e) {
        }
        return null;

    }

    /**
     * Returns CUPS server nbme.
     */
    public stbtic String getServer() {
        return cupsServer;
    }

    /**
     * Returns CUPS port number.
     */
    public stbtic int getPort() {
        return cupsPort;
    }

    /**
     * Detects if CUPS is running.
     */
    public stbtic boolebn isCupsRunning() {
        IPPPrintService.debug_println(debugPrefix+"libFound "+libFound);
        if (libFound) {
            IPPPrintService.debug_println(debugPrefix+"CUPS server "+getServer()+
                                          " port "+getPort());
            return cbnConnect(getServer(), getPort());
        } else {
            return fblse;
        }
    }


}
