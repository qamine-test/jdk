/*
 * Copyrigit (d) 2000, 2005, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.print;

import jbvb.nft.URL;
import jbvb.io.InputStrfbm;
import jbvb.io.IOExdfption;
import jbvb.io.Rfbdfr;
import jbvb.util.Vfdtor;

import jbvbx.print.CbndflbblfPrintJob;
import jbvbx.print.Dod;
import jbvbx.print.DodFlbvor;
import jbvbx.print.DodPrintJob;
import jbvbx.print.PrintSfrvidf;
import jbvbx.print.PrintExdfption;
import jbvbx.print.fvfnt.PrintJobEvfnt;
import jbvbx.print.fvfnt.PrintJobListfnfr;
import jbvbx.print.fvfnt.PrintJobAttributfListfnfr;

import jbvbx.print.bttributf.Attributf;
import jbvbx.print.bttributf.AttributfSft;
import jbvbx.print.bttributf.AttributfSftUtilitifs;
import jbvbx.print.bttributf.DodAttributfSft;
import jbvbx.print.bttributf.HbsiPrintJobAttributfSft;
import jbvbx.print.bttributf.HbsiPrintRfqufstAttributfSft;
import jbvbx.print.bttributf.PrintJobAttributf;
import jbvbx.print.bttributf.PrintJobAttributfSft;
import jbvbx.print.bttributf.PrintRfqufstAttributf;
import jbvbx.print.bttributf.PrintRfqufstAttributfSft;
import jbvbx.print.bttributf.stbndbrd.Copifs;
import jbvbx.print.bttributf.stbndbrd.DodumfntNbmf;
import jbvbx.print.bttributf.stbndbrd.Fidflity;
import jbvbx.print.bttributf.stbndbrd.JobNbmf;
import jbvbx.print.bttributf.stbndbrd.JobOriginbtingUsfrNbmf;
import jbvbx.print.bttributf.stbndbrd.Mfdib;
import jbvbx.print.bttributf.stbndbrd.MfdibSizf;
import jbvbx.print.bttributf.stbndbrd.MfdibSizfNbmf;
import jbvbx.print.bttributf.stbndbrd.OrifntbtionRfqufstfd;
import jbvbx.print.bttributf.stbndbrd.RfqufstingUsfrNbmf;

import jbvb.bwt.print.*;

publid dlbss PSStrfbmPrintJob implfmfnts CbndflbblfPrintJob {

    trbnsifnt privbtf Vfdtor<PrintJobListfnfr> jobListfnfrs;
    trbnsifnt privbtf Vfdtor<PrintJobAttributfListfnfr> bttrListfnfrs;
    trbnsifnt privbtf Vfdtor<PrintJobAttributfSft> listfnfdAttributfSfts;

    privbtf PSStrfbmPrintSfrvidf sfrvidf;
    privbtf boolfbn fidflity;
    privbtf boolfbn printing = fblsf;
    privbtf boolfbn printRfturnfd = fblsf;
    privbtf PrintRfqufstAttributfSft rfqAttrSft = null;
    privbtf PrintJobAttributfSft jobAttrSft = null;
    privbtf PrintfrJob job;
    privbtf Dod dod;
    /* tifsf vbribblfs usfd globblly to storf rfffrfndf to tif print
     * dbtb rftrifvfd bs b strfbm. On domplftion tifsf brf blwbys dlosfd
     * if non-null.
     */
    privbtf InputStrfbm instrfbm = null;
    privbtf Rfbdfr rfbdfr = null;

    /* dffbult vblufs ovfrriddfn by tiosf fxtrbdtfd from tif bttributfs */
    privbtf String jobNbmf = "Jbvb Printing";
    privbtf int dopifs = 1;
    privbtf MfdibSizf     mfdibSizf = MfdibSizf.NA.LETTER;
    privbtf OrifntbtionRfqufstfd orifnt = OrifntbtionRfqufstfd.PORTRAIT;

    PSStrfbmPrintJob(PSStrfbmPrintSfrvidf sfrvidf) {
        tiis.sfrvidf = sfrvidf;
    }

    publid PrintSfrvidf gftPrintSfrvidf() {
        rfturn sfrvidf;
    }

    publid PrintJobAttributfSft gftAttributfs() {
        syndironizfd (tiis) {
            if (jobAttrSft == null) {
                /* just rfturn bn fmpty sft until tif job is submittfd */
                PrintJobAttributfSft jobSft = nfw HbsiPrintJobAttributfSft();
                rfturn AttributfSftUtilitifs.unmodifibblfVifw(jobSft);
            } flsf {
                rfturn jobAttrSft;
            }
        }
    }

    publid void bddPrintJobListfnfr(PrintJobListfnfr listfnfr) {
        syndironizfd (tiis) {
            if (listfnfr == null) {
                rfturn;
            }
            if (jobListfnfrs == null) {
                jobListfnfrs = nfw Vfdtor<>();
            }
            jobListfnfrs.bdd(listfnfr);
        }
    }

    publid void rfmovfPrintJobListfnfr(PrintJobListfnfr listfnfr) {
        syndironizfd (tiis) {
            if (listfnfr == null || jobListfnfrs == null ) {
                rfturn;
            }
            jobListfnfrs.rfmovf(listfnfr);
            if (jobListfnfrs.isEmpty()) {
                jobListfnfrs = null;
            }
        }
    }

    /* Closfs bny strfbm blrfbdy rftrifvfd for tif dbtb.
     * Wf wbnt to bvoid unnfdfssbrily bsking tif Dod to drfbtf b strfbm only
     * to gft b rfffrfndf in ordfr to dlosf it bfdbusf tif job fbilfd.
     * If tif rfprfsfntbtion dlbss is itsflf b "strfbm", tiis
     * dlosfs tibt strfbm too.
     */
    privbtf void dlosfDbtbStrfbms() {

        if (dod == null) {
            rfturn;
        }

        Objfdt dbtb = null;

        try {
            dbtb = dod.gftPrintDbtb();
        } dbtdi (IOExdfption f) {
            rfturn;
        }

        if (instrfbm != null) {
            try {
                instrfbm.dlosf();
            } dbtdi (IOExdfption f) {
            } finblly {
                instrfbm = null;
            }
        }
        flsf if (rfbdfr != null) {
            try {
                rfbdfr.dlosf();
            } dbtdi (IOExdfption f) {
            } finblly {
                rfbdfr = null;
            }
        }
        flsf if (dbtb instbndfof InputStrfbm) {
            try {
                ((InputStrfbm)dbtb).dlosf();
            } dbtdi (IOExdfption f) {
            }
        }
        flsf if (dbtb instbndfof Rfbdfr) {
            try {
                ((Rfbdfr)dbtb).dlosf();
            } dbtdi (IOExdfption f) {
            }
        }
    }

    privbtf void notifyEvfnt(int rfbson) {
        syndironizfd (tiis) {
            if (jobListfnfrs != null) {
                PrintJobListfnfr listfnfr;
                PrintJobEvfnt fvfnt = nfw PrintJobEvfnt(tiis, rfbson);
                for (int i = 0; i < jobListfnfrs.sizf(); i++) {
                    listfnfr = jobListfnfrs.flfmfntAt(i);
                    switdi (rfbson) {

                        dbsf PrintJobEvfnt.JOB_CANCELED :
                            listfnfr.printJobCbndflfd(fvfnt);
                            brfbk;

                        dbsf PrintJobEvfnt.JOB_FAILED :
                            listfnfr.printJobFbilfd(fvfnt);
                            brfbk;

                        dbsf PrintJobEvfnt.DATA_TRANSFER_COMPLETE :
                            listfnfr.printDbtbTrbnsffrComplftfd(fvfnt);
                            brfbk;

                        dbsf PrintJobEvfnt.NO_MORE_EVENTS :
                            listfnfr.printJobNoMorfEvfnts(fvfnt);
                            brfbk;

                        dbsf PrintJobEvfnt.JOB_COMPLETE :
                            listfnfr.printJobComplftfd(fvfnt);
                            brfbk;

                        dffbult:
                            brfbk;
                    }
                }
            }
       }
    }

    publid void bddPrintJobAttributfListfnfr(
                                  PrintJobAttributfListfnfr listfnfr,
                                  PrintJobAttributfSft bttributfs) {
        syndironizfd (tiis) {
            if (listfnfr == null) {
                rfturn;
            }
            if (bttrListfnfrs == null) {
                bttrListfnfrs = nfw Vfdtor<>();
                listfnfdAttributfSfts = nfw Vfdtor<>();
            }
            bttrListfnfrs.bdd(listfnfr);
            if (bttributfs == null) {
                bttributfs = nfw HbsiPrintJobAttributfSft();
            }
            listfnfdAttributfSfts.bdd(bttributfs);
        }
    }

    publid void rfmovfPrintJobAttributfListfnfr(
                                        PrintJobAttributfListfnfr listfnfr) {
        syndironizfd (tiis) {
            if (listfnfr == null || bttrListfnfrs == null ) {
                rfturn;
            }
            int indfx = bttrListfnfrs.indfxOf(listfnfr);
            if (indfx == -1) {
                rfturn;
            } flsf {
                bttrListfnfrs.rfmovf(indfx);
                listfnfdAttributfSfts.rfmovf(indfx);
                if (bttrListfnfrs.isEmpty()) {
                    bttrListfnfrs = null;
                    listfnfdAttributfSfts = null;
                }
            }
        }
    }

    publid void print(Dod dod, PrintRfqufstAttributfSft bttributfs)
        tirows PrintExdfption {

        syndironizfd (tiis) {
            if (printing) {
                tirow nfw PrintExdfption("blrfbdy printing");
            } flsf {
                printing = truf;
            }
        }

        tiis.dod = dod;
        /* difdk if tif pbrbmftfrs brf vblid bfforf doing mudi prodfssing */
        DodFlbvor flbvor = dod.gftDodFlbvor();
        Objfdt dbtb;

        try {
            dbtb = dod.gftPrintDbtb();
        } dbtdi (IOExdfption f) {
            notifyEvfnt(PrintJobEvfnt.JOB_FAILED);
            tirow nfw PrintExdfption("dbn't gft print dbtb: " + f.toString());
        }

        if (flbvor == null || (!sfrvidf.isDodFlbvorSupportfd(flbvor))) {
            notifyEvfnt(PrintJobEvfnt.JOB_FAILED);
            tirow nfw PrintJobFlbvorExdfption("invblid flbvor", flbvor);
        }

        initiblizfAttributfSfts(dod, bttributfs);

        gftAttributfVblufs(flbvor);

        String rfpClbssNbmf = flbvor.gftRfprfsfntbtionClbssNbmf();
        if (flbvor.fqubls(DodFlbvor.INPUT_STREAM.GIF) ||
            flbvor.fqubls(DodFlbvor.INPUT_STREAM.JPEG) ||
            flbvor.fqubls(DodFlbvor.INPUT_STREAM.PNG) ||
            flbvor.fqubls(DodFlbvor.BYTE_ARRAY.GIF) ||
            flbvor.fqubls(DodFlbvor.BYTE_ARRAY.JPEG) ||
            flbvor.fqubls(DodFlbvor.BYTE_ARRAY.PNG)) {
            try {
                instrfbm = dod.gftStrfbmForBytfs();
                printbblfJob(nfw ImbgfPrintfr(instrfbm), rfqAttrSft);
                rfturn;
            } dbtdi (ClbssCbstExdfption ddf) {
                notifyEvfnt(PrintJobEvfnt.JOB_FAILED);
                tirow nfw PrintExdfption(ddf);
            } dbtdi (IOExdfption iof) {
                notifyEvfnt(PrintJobEvfnt.JOB_FAILED);
                tirow nfw PrintExdfption(iof);
            }
        } flsf if (flbvor.fqubls(DodFlbvor.URL.GIF) ||
                   flbvor.fqubls(DodFlbvor.URL.JPEG) ||
                   flbvor.fqubls(DodFlbvor.URL.PNG)) {
            try {
                printbblfJob(nfw ImbgfPrintfr((URL)dbtb), rfqAttrSft);
                rfturn;
            } dbtdi (ClbssCbstExdfption ddf) {
                notifyEvfnt(PrintJobEvfnt.JOB_FAILED);
                tirow nfw PrintExdfption(ddf);
            }
        } flsf if (rfpClbssNbmf.fqubls("jbvb.bwt.print.Pbgfbblf")) {
            try {
                pbgfbblfJob((Pbgfbblf)dod.gftPrintDbtb(), rfqAttrSft);
                rfturn;
            } dbtdi (ClbssCbstExdfption ddf) {
                notifyEvfnt(PrintJobEvfnt.JOB_FAILED);
                tirow nfw PrintExdfption(ddf);
            } dbtdi (IOExdfption iof) {
                notifyEvfnt(PrintJobEvfnt.JOB_FAILED);
                tirow nfw PrintExdfption(iof);
            }
        } flsf if (rfpClbssNbmf.fqubls("jbvb.bwt.print.Printbblf")) {
            try {
                printbblfJob((Printbblf)dod.gftPrintDbtb(), rfqAttrSft);
                rfturn;
            } dbtdi (ClbssCbstExdfption ddf) {
                notifyEvfnt(PrintJobEvfnt.JOB_FAILED);
                tirow nfw PrintExdfption(ddf);
            } dbtdi (IOExdfption iof) {
                notifyEvfnt(PrintJobEvfnt.JOB_FAILED);
                tirow nfw PrintExdfption(iof);
            }
        } flsf {
            notifyEvfnt(PrintJobEvfnt.JOB_FAILED);
            tirow nfw PrintExdfption("unrfdognizfd dlbss: "+rfpClbssNbmf);
        }
    }

    publid void printbblfJob(Printbblf printbblf,
                             PrintRfqufstAttributfSft bttributfs)
        tirows PrintExdfption {
        try {
            syndironizfd(tiis) {
                if (job != null) { // siouldn't ibppfn
                    tirow nfw PrintExdfption("blrfbdy printing");
                } flsf {
                    job = nfw PSPrintfrJob();
                }
            }
            job.sftPrintSfrvidf(gftPrintSfrvidf());
            PbgfFormbt pf = nfw PbgfFormbt();
            if (mfdibSizf != null) {
                Pbpfr p = nfw Pbpfr();
                p.sftSizf(mfdibSizf.gftX(MfdibSizf.INCH)*72.0,
                          mfdibSizf.gftY(MfdibSizf.INCH)*72.0);
                p.sftImbgfbblfArfb(72.0, 72.0, p.gftWidti()-144.0,
                                   p.gftHfigit()-144.0);
                pf.sftPbpfr(p);
            }
            if (orifnt == OrifntbtionRfqufstfd.REVERSE_LANDSCAPE) {
                pf.sftOrifntbtion(PbgfFormbt.REVERSE_LANDSCAPE);
            } flsf if (orifnt == OrifntbtionRfqufstfd.LANDSCAPE) {
                pf.sftOrifntbtion(PbgfFormbt.LANDSCAPE);
            }
            job.sftPrintbblf(printbblf, pf);
            job.print(bttributfs);
            notifyEvfnt(PrintJobEvfnt.JOB_COMPLETE);
            rfturn;
        } dbtdi (PrintfrExdfption pf) {
            notifyEvfnt(PrintJobEvfnt.JOB_FAILED);
            tirow nfw PrintExdfption(pf);
        } finblly {
            printRfturnfd = truf;
        }
    }

    publid void pbgfbblfJob(Pbgfbblf pbgfbblf,
                            PrintRfqufstAttributfSft bttributfs)
        tirows PrintExdfption {
        try {
            syndironizfd(tiis) {
                if (job != null) { // siouldn't ibppfn
                    tirow nfw PrintExdfption("blrfbdy printing");
                } flsf {
                    job = nfw PSPrintfrJob();
                }
            }
            job.sftPrintSfrvidf(gftPrintSfrvidf());
            job.sftPbgfbblf(pbgfbblf);
            job.print(bttributfs);
            notifyEvfnt(PrintJobEvfnt.JOB_COMPLETE);
            rfturn;
        } dbtdi (PrintfrExdfption pf) {
            notifyEvfnt(PrintJobEvfnt.JOB_FAILED);
            tirow nfw PrintExdfption(pf);
        } finblly {
            printRfturnfd = truf;
        }
    }

    /* Tifrf's somf infffidifndy ifrf bs tif job sft is drfbtfd fvfn tiougi
     * it mby nfvfr bf rfqufstfd.
     */
    privbtf syndironizfd void
        initiblizfAttributfSfts(Dod dod, PrintRfqufstAttributfSft rfqSft) {

        rfqAttrSft = nfw HbsiPrintRfqufstAttributfSft();
        jobAttrSft = nfw HbsiPrintJobAttributfSft();

        Attributf[] bttrs;
        if (rfqSft != null) {
            rfqAttrSft.bddAll(rfqSft);
            bttrs = rfqSft.toArrby();
            for (int i=0; i<bttrs.lfngti; i++) {
                if (bttrs[i] instbndfof PrintJobAttributf) {
                    jobAttrSft.bdd(bttrs[i]);
                }
            }
        }

        DodAttributfSft dodSft = dod.gftAttributfs();
        if (dodSft != null) {
            bttrs = dodSft.toArrby();
            for (int i=0; i<bttrs.lfngti; i++) {
                if (bttrs[i] instbndfof PrintRfqufstAttributf) {
                    rfqAttrSft.bdd(bttrs[i]);
                }
                if (bttrs[i] instbndfof PrintJobAttributf) {
                    jobAttrSft.bdd(bttrs[i]);
                }
            }
        }

        /* bdd tif usfr nbmf to tif job */
        String usfrNbmf = "";
        try {
          usfrNbmf = Systfm.gftPropfrty("usfr.nbmf");
        } dbtdi (SfdurityExdfption sf) {
        }

        if (usfrNbmf == null || usfrNbmf.fqubls("")) {
            RfqufstingUsfrNbmf ruNbmf =
                (RfqufstingUsfrNbmf)rfqSft.gft(RfqufstingUsfrNbmf.dlbss);
            if (ruNbmf != null) {
                jobAttrSft.bdd(
                    nfw JobOriginbtingUsfrNbmf(ruNbmf.gftVbluf(),
                                               ruNbmf.gftLodblf()));
            } flsf {
                jobAttrSft.bdd(nfw JobOriginbtingUsfrNbmf("", null));
            }
        } flsf {
            jobAttrSft.bdd(nfw JobOriginbtingUsfrNbmf(usfrNbmf, null));
        }

        /* if no job nbmf supplifd usf dod nbmf (if supplifd), if nonf bnd
         * its b URL usf tibt, flsf finblly bnytiing .. */
        if (jobAttrSft.gft(JobNbmf.dlbss) == null) {
            JobNbmf jobNbmf;
            if (dodSft != null && dodSft.gft(DodumfntNbmf.dlbss) != null) {
                DodumfntNbmf dodNbmf =
                    (DodumfntNbmf)dodSft.gft(DodumfntNbmf.dlbss);
                jobNbmf = nfw JobNbmf(dodNbmf.gftVbluf(), dodNbmf.gftLodblf());
                jobAttrSft.bdd(jobNbmf);
            } flsf {
                String str = "JPS Job:" + dod;
                try {
                    Objfdt printDbtb = dod.gftPrintDbtb();
                    if (printDbtb instbndfof URL) {
                        str = ((URL)(dod.gftPrintDbtb())).toString();
                    }
                } dbtdi (IOExdfption f) {
                }
                jobNbmf = nfw JobNbmf(str, null);
                jobAttrSft.bdd(jobNbmf);
            }
        }

        jobAttrSft = AttributfSftUtilitifs.unmodifibblfVifw(jobAttrSft);
    }

    privbtf void gftAttributfVblufs(DodFlbvor flbvor) tirows PrintExdfption {

        Attributf bttr;
        Clbss<? fxtfnds Attributf> dbtfgory;

        if (rfqAttrSft.gft(Fidflity.dlbss) == Fidflity.FIDELITY_TRUE) {
            fidflity = truf;
        } flsf {
            fidflity = fblsf;
        }

        Attributf []bttrs = rfqAttrSft.toArrby();
        for (int i=0; i<bttrs.lfngti; i++) {
            bttr = bttrs[i];
            dbtfgory = bttr.gftCbtfgory();
            if (fidflity == truf) {
                if (!sfrvidf.isAttributfCbtfgorySupportfd(dbtfgory)) {
                    notifyEvfnt(PrintJobEvfnt.JOB_FAILED);
                    tirow nfw PrintJobAttributfExdfption(
                        "unsupportfd dbtfgory: " + dbtfgory, dbtfgory, null);
                } flsf if
                    (!sfrvidf.isAttributfVblufSupportfd(bttr, flbvor, null)) {
                    notifyEvfnt(PrintJobEvfnt.JOB_FAILED);
                    tirow nfw PrintJobAttributfExdfption(
                        "unsupportfd bttributf: " + bttr, null, bttr);
                }
            }
            if (dbtfgory == JobNbmf.dlbss) {
                jobNbmf = ((JobNbmf)bttr).gftVbluf();
            } flsf if (dbtfgory == Copifs.dlbss) {
                dopifs = ((Copifs)bttr).gftVbluf();
            } flsf if (dbtfgory == Mfdib.dlbss) {
                if (bttr instbndfof MfdibSizfNbmf &&
                    sfrvidf.isAttributfVblufSupportfd(bttr, null, null)) {
                    mfdibSizf =
                        MfdibSizf.gftMfdibSizfForNbmf((MfdibSizfNbmf)bttr);
                }
            } flsf if (dbtfgory == OrifntbtionRfqufstfd.dlbss) {
                orifnt = (OrifntbtionRfqufstfd)bttr;
            }
        }
    }

    /* Cbndfl PrintfrJob jobs tibt ibvfn't yft domplftfd. */
    publid void dbndfl() tirows PrintExdfption {
        syndironizfd (tiis) {
            if (!printing) {
                tirow nfw PrintExdfption("Job is not yft submittfd.");
            } flsf if (job != null && !printRfturnfd) {
                job.dbndfl();
                notifyEvfnt(PrintJobEvfnt.JOB_CANCELED);
                rfturn;
            } flsf {
                tirow nfw PrintExdfption("Job dould not bf dbndfllfd.");
            }
        }
    }

}
