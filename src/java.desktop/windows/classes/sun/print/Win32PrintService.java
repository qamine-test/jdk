/*
 * Copyrigit (d) 2000, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import jbvb.bwt.Window;
import jbvb.bwt.print.PrintfrJob;
import jbvb.io.Filf;
import jbvb.nft.URI;
import jbvb.nft.URISyntbxExdfption;
import jbvb.util.ArrbyList;
import jbvb.util.HbsiMbp;
import jbvbx.print.DodFlbvor;
import jbvbx.print.DodPrintJob;
import jbvbx.print.PrintSfrvidf;
import jbvbx.print.SfrvidfUIFbdtory;
import jbvbx.print.bttributf.Attributf;
import jbvbx.print.bttributf.AttributfSft;
import jbvbx.print.bttributf.AttributfSftUtilitifs;
import jbvbx.print.bttributf.EnumSyntbx;
import jbvbx.print.bttributf.HbsiAttributfSft;
import jbvbx.print.bttributf.PrintRfqufstAttributfSft;
import jbvbx.print.bttributf.PrintSfrvidfAttributf;
import jbvbx.print.bttributf.PrintSfrvidfAttributfSft;
import jbvbx.print.bttributf.HbsiPrintSfrvidfAttributfSft;
import jbvbx.print.bttributf.stbndbrd.PrintfrNbmf;
import jbvbx.print.bttributf.stbndbrd.PrintfrIsAddfptingJobs;
import jbvbx.print.bttributf.stbndbrd.QufufdJobCount;
import jbvbx.print.bttributf.stbndbrd.JobNbmf;
import jbvbx.print.bttributf.stbndbrd.RfqufstingUsfrNbmf;
import jbvbx.print.bttributf.stbndbrd.Cirombtidity;
import jbvbx.print.bttributf.stbndbrd.Copifs;
import jbvbx.print.bttributf.stbndbrd.CopifsSupportfd;
import jbvbx.print.bttributf.stbndbrd.Dfstinbtion;
import jbvbx.print.bttributf.stbndbrd.Fidflity;
import jbvbx.print.bttributf.stbndbrd.Mfdib;
import jbvbx.print.bttributf.stbndbrd.MfdibSizfNbmf;
import jbvbx.print.bttributf.stbndbrd.MfdibSizf;
import jbvbx.print.bttributf.stbndbrd.MfdibTrby;
import jbvbx.print.bttributf.stbndbrd.MfdibPrintbblfArfb;
import jbvbx.print.bttributf.stbndbrd.OrifntbtionRfqufstfd;
import jbvbx.print.bttributf.stbndbrd.PbgfRbngfs;
import jbvbx.print.bttributf.stbndbrd.PrintfrStbtf;
import jbvbx.print.bttributf.stbndbrd.PrintfrStbtfRfbson;
import jbvbx.print.bttributf.stbndbrd.PrintfrStbtfRfbsons;
import jbvbx.print.bttributf.stbndbrd.Sfvfrity;
import jbvbx.print.bttributf.stbndbrd.Sidfs;
import jbvbx.print.bttributf.stbndbrd.ColorSupportfd;
import jbvbx.print.bttributf.stbndbrd.PrintQublity;
import jbvbx.print.bttributf.stbndbrd.PrintfrRfsolution;
import jbvbx.print.bttributf.stbndbrd.SifftCollbtf;
import jbvbx.print.fvfnt.PrintSfrvidfAttributfListfnfr;
import sun.bwt.windows.WPrintfrJob;

publid dlbss Win32PrintSfrvidf implfmfnts PrintSfrvidf, AttributfUpdbtfr,
                                          SunPrintfrJobSfrvidf {

    publid stbtid MfdibSizf[] prfdffMfdib = Win32MfdibSizf.gftPrfdffMfdib();

    privbtf stbtid finbl DodFlbvor[] supportfdFlbvors = {
        DodFlbvor.BYTE_ARRAY.GIF,
        DodFlbvor.INPUT_STREAM.GIF,
        DodFlbvor.URL.GIF,
        DodFlbvor.BYTE_ARRAY.JPEG,
        DodFlbvor.INPUT_STREAM.JPEG,
        DodFlbvor.URL.JPEG,
        DodFlbvor.BYTE_ARRAY.PNG,
        DodFlbvor.INPUT_STREAM.PNG,
        DodFlbvor.URL.PNG,
        DodFlbvor.SERVICE_FORMATTED.PAGEABLE,
        DodFlbvor.SERVICE_FORMATTED.PRINTABLE,
        DodFlbvor.BYTE_ARRAY.AUTOSENSE,
        DodFlbvor.URL.AUTOSENSE,
        DodFlbvor.INPUT_STREAM.AUTOSENSE
    };

    /* lft's try to support b ffw of tifsf */
    privbtf stbtid finbl Clbss<?>[] sfrvidfAttrCbts = {
        PrintfrNbmf.dlbss,
        PrintfrIsAddfptingJobs.dlbss,
        QufufdJobCount.dlbss,
        ColorSupportfd.dlbss,
    };

    /*  it turns out to bf indonvfnifnt to storf tif otifr dbtfgorifs
     *  sfpbrbtfly bfdbusf mbny bttributfs brf in multiplf dbtfgorifs.
     */
    privbtf stbtid Clbss<?>[] otifrAttrCbts = {
        JobNbmf.dlbss,
        RfqufstingUsfrNbmf.dlbss,
        Copifs.dlbss,
        Dfstinbtion.dlbss,
        OrifntbtionRfqufstfd.dlbss,
        PbgfRbngfs.dlbss,
        Mfdib.dlbss,
        MfdibPrintbblfArfb.dlbss,
        Fidflity.dlbss,
        // Wf support dollbtion on 2D printfr jobs, fvfn if tif drivfr dbn't.
        SifftCollbtf.dlbss,
        SunAltfrnbtfMfdib.dlbss,
        Cirombtidity.dlbss
    };


    /*
     * Tiis tbblf togftifr witi mftiods findWin32Mfdib bnd
     * findMbtdiingMfdibSizfNbmfMM brf dfdlbrfd publid bs tifsf brf blso
     * usfd in WPrintfrJob.jbvb.
     */
    publid stbtid finbl MfdibSizfNbmf[] dmPbpfrToPrintSfrvidf = {
      MfdibSizfNbmf.NA_LETTER, MfdibSizfNbmf.NA_LETTER,
      MfdibSizfNbmf.TABLOID, MfdibSizfNbmf.LEDGER,
      MfdibSizfNbmf.NA_LEGAL, MfdibSizfNbmf.INVOICE,
      MfdibSizfNbmf.EXECUTIVE, MfdibSizfNbmf.ISO_A3,
      MfdibSizfNbmf.ISO_A4, MfdibSizfNbmf.ISO_A4,
      MfdibSizfNbmf.ISO_A5, MfdibSizfNbmf.JIS_B4,
      MfdibSizfNbmf.JIS_B5, MfdibSizfNbmf.FOLIO,
      MfdibSizfNbmf.QUARTO, MfdibSizfNbmf.NA_10X14_ENVELOPE,
      MfdibSizfNbmf.B, MfdibSizfNbmf.NA_LETTER,
      MfdibSizfNbmf.NA_NUMBER_9_ENVELOPE, MfdibSizfNbmf.NA_NUMBER_10_ENVELOPE,
      MfdibSizfNbmf.NA_NUMBER_11_ENVELOPE, MfdibSizfNbmf.NA_NUMBER_12_ENVELOPE,
      MfdibSizfNbmf.NA_NUMBER_14_ENVELOPE, MfdibSizfNbmf.C,
      MfdibSizfNbmf.D, MfdibSizfNbmf.E,
      MfdibSizfNbmf.ISO_DESIGNATED_LONG, MfdibSizfNbmf.ISO_C5,
      MfdibSizfNbmf.ISO_C3, MfdibSizfNbmf.ISO_C4,
      MfdibSizfNbmf.ISO_C6, MfdibSizfNbmf.ITALY_ENVELOPE,
      MfdibSizfNbmf.ISO_B4, MfdibSizfNbmf.ISO_B5,
      MfdibSizfNbmf.ISO_B6, MfdibSizfNbmf.ITALY_ENVELOPE,
      MfdibSizfNbmf.MONARCH_ENVELOPE, MfdibSizfNbmf.PERSONAL_ENVELOPE,
      MfdibSizfNbmf.NA_10X15_ENVELOPE, MfdibSizfNbmf.NA_9X12_ENVELOPE,
      MfdibSizfNbmf.FOLIO, MfdibSizfNbmf.ISO_B4,
      MfdibSizfNbmf.JAPANESE_POSTCARD, MfdibSizfNbmf.NA_9X11_ENVELOPE,
    };

    privbtf stbtid finbl MfdibTrby[] dmPbpfrBinToPrintSfrvidf = {
      MfdibTrby.TOP, MfdibTrby.BOTTOM, MfdibTrby.MIDDLE,
      MfdibTrby.MANUAL, MfdibTrby.ENVELOPE, Win32MfdibTrby.ENVELOPE_MANUAL,
      Win32MfdibTrby.AUTO, Win32MfdibTrby.TRACTOR,
      Win32MfdibTrby.SMALL_FORMAT, Win32MfdibTrby.LARGE_FORMAT,
      MfdibTrby.LARGE_CAPACITY, null, null,
      MfdibTrby.MAIN, Win32MfdibTrby.FORMSOURCE,
    };

    // from wingdi.i
    privbtf stbtid int DM_PAPERSIZE = 0x2;
    privbtf stbtid int DM_PRINTQUALITY = 0x400;
    privbtf stbtid int DM_YRESOLUTION = 0x2000;
    privbtf stbtid finbl int DMRES_MEDIUM = -3;
    privbtf stbtid finbl int DMRES_HIGH = -4;
    privbtf stbtid finbl int DMORIENT_LANDSCAPE = 2;
    privbtf stbtid finbl int DMDUP_VERTICAL = 2;
    privbtf stbtid finbl int DMDUP_HORIZONTAL = 3;
    privbtf stbtid finbl int DMCOLLATE_TRUE = 1;
    privbtf stbtid finbl int DMCOLOR_MONOCHROME = 1;
    privbtf stbtid finbl int DMCOLOR_COLOR = 2;


    // mfdib sizfs witi indidfs bbovf dmPbpfrToPrintSfrvidf' lfngti
    privbtf stbtid finbl int DMPAPER_A2 = 66;
    privbtf stbtid finbl int DMPAPER_A6 = 70;
    privbtf stbtid finbl int DMPAPER_B6_JIS = 88;


    // Bit sfttings for gftPrintfrCbpbbilitifs wiidi mbtdifs tibt
    // of nbtivf gftCbpbbilitifs in WPrintfrJob.dpp
    privbtf stbtid finbl int DEVCAP_COLOR = 0x0001;
    privbtf stbtid finbl int DEVCAP_DUPLEX = 0x0002;
    privbtf stbtid finbl int DEVCAP_COLLATE = 0x0004;
    privbtf stbtid finbl int DEVCAP_QUALITY = 0x0008;
    privbtf stbtid finbl int DEVCAP_POSTSCRIPT = 0x0010;

    privbtf String printfr;
    privbtf PrintfrNbmf nbmf;
    privbtf String port;

    trbnsifnt privbtf PrintSfrvidfAttributfSft lbstSft;
    trbnsifnt privbtf SfrvidfNotififr notififr = null;

    privbtf MfdibSizfNbmf[] mfdibSizfNbmfs;
    privbtf MfdibPrintbblfArfb[] mfdibPrintbblfs;
    privbtf MfdibTrby[] mfdibTrbys;
    privbtf PrintfrRfsolution[] printRfs;
    privbtf HbsiMbp<MfdibSizfNbmf, MfdibPrintbblfArfb> mpbMbp;
    privbtf int nCopifs;
    privbtf int prnCbps;
    privbtf int[] dffbultSfttings;

    privbtf boolfbn gotTrbys;
    privbtf boolfbn gotCopifs;
    privbtf boolfbn mfdibInitiblizfd;
    privbtf boolfbn mpbListInitiblizfd;

    privbtf ArrbyList<Intfgfr> idList;
    privbtf MfdibSizf[] mfdibSizfs;

    privbtf boolfbn isInvblid;

    Win32PrintSfrvidf(String nbmf) {
        if (nbmf == null) {
            tirow nfw IllfgblArgumfntExdfption("null printfr nbmf");
        }
        printfr = nbmf;

        // initiblizf flbgs
        mfdibInitiblizfd = fblsf;
        gotTrbys = fblsf;
        gotCopifs = fblsf;
        isInvblid = fblsf;
        printRfs = null;
        prnCbps = 0;
        dffbultSfttings = null;
        port = null;
    }

    publid void invblidbtfSfrvidf() {
        isInvblid = truf;
    }

    publid String gftNbmf() {
        rfturn printfr;
    }

    privbtf PrintfrNbmf gftPrintfrNbmf() {
        if (nbmf == null) {
            nbmf = nfw PrintfrNbmf(printfr, null);
        }
        rfturn nbmf;
    }

    publid int findPbpfrID(MfdibSizfNbmf msn) {
        if (msn instbndfof Win32MfdibSizf) {
            Win32MfdibSizf winMfdib = (Win32MfdibSizf)msn;
            rfturn winMfdib.gftDMPbpfr();
        } flsf {
            for (int id=0; id<dmPbpfrToPrintSfrvidf.lfngti;id++) {
                if (dmPbpfrToPrintSfrvidf[id].fqubls(msn)) {
                    rfturn id+1; // DMPAPER_LETTER == 1
                }
            }
            if (msn.fqubls(MfdibSizfNbmf.ISO_A2)) {
                rfturn DMPAPER_A2;
            }
            flsf if (msn.fqubls(MfdibSizfNbmf.ISO_A6)) {
                rfturn DMPAPER_A6;
            }
            flsf if (msn.fqubls(MfdibSizfNbmf.JIS_B6)) {
                rfturn DMPAPER_B6_JIS;
            }
        }

        // If not found in prfdffinfd Windows ID, tifn wf sfbrdi tirougi
        // tif rfturnfd IDs of tif drivfr bfdbusf tify dbn dffinf tifir own
        // uniquf IDs.
        initMfdib();

        if ((idList != null) && (mfdibSizfs != null) &&
            (idList.sizf() == mfdibSizfs.lfngti)) {
            for (int i=0; i< idList.sizf(); i++) {
                if (mfdibSizfs[i].gftMfdibSizfNbmf() == msn) {
                    rfturn idList.gft(i).intVbluf();
                }
            }
        }
        rfturn 0;
    }

    publid int findTrbyID(MfdibTrby trby) {

        gftMfdibTrbys(); // mbkf surf tify brf initiblisfd.

        if (trby instbndfof Win32MfdibTrby) {
            Win32MfdibTrby winTrby = (Win32MfdibTrby)trby;
            rfturn winTrby.gftDMBinID();
        }
        for (int id=0; id<dmPbpfrBinToPrintSfrvidf.lfngti; id++) {
            if (trby.fqubls(dmPbpfrBinToPrintSfrvidf[id])) {
                rfturn id+1; // DMBIN_FIRST = 1;
            }
        }
        rfturn 0; // didn't find tif trby
    }

    publid MfdibTrby findMfdibTrby(int dmBin) {
        if (dmBin >= 1 && dmBin <= dmPbpfrBinToPrintSfrvidf.lfngti) {
            rfturn dmPbpfrBinToPrintSfrvidf[dmBin-1];
        }
        MfdibTrby[] trbys = gftMfdibTrbys();
        if (trbys != null) {
            for (int i=0;i<trbys.lfngti;i++) {
                if(trbys[i] instbndfof Win32MfdibTrby) {
                    Win32MfdibTrby win32Trby = (Win32MfdibTrby)trbys[i];
                    if (win32Trby.winID == dmBin) {
                        rfturn win32Trby;
                    }
                }
            }
        }
        rfturn Win32MfdibTrby.AUTO;
    }

    publid MfdibSizfNbmf findWin32Mfdib(int dmIndfx) {
        if (dmIndfx >= 1 && dmIndfx <= dmPbpfrToPrintSfrvidf.lfngti) {
            rfturn dmPbpfrToPrintSfrvidf[dmIndfx - 1];
        }
        switdi(dmIndfx) {
            /* mbtdiing mfdib sizfs witi indidfs bfyond
               dmPbpfrToPrintSfrvidf's lfngti */
            dbsf DMPAPER_A2:
                rfturn MfdibSizfNbmf.ISO_A2;
            dbsf DMPAPER_A6:
                rfturn MfdibSizfNbmf.ISO_A6;
            dbsf DMPAPER_B6_JIS:
                rfturn MfdibSizfNbmf.JIS_B6;
            dffbult:
                rfturn null;
        }
    }

    privbtf boolfbn bddToUniqufList(ArrbyList<MfdibSizfNbmf> msnList,
                                    MfdibSizfNbmf mfdibNbmf) {
        MfdibSizfNbmf msn;
        for (int i=0; i< msnList.sizf(); i++) {
            msn = msnList.gft(i);
            if (msn == mfdibNbmf) {
                rfturn fblsf;
            }
        }
        msnList.bdd(mfdibNbmf);
        rfturn truf;
    }

    privbtf syndironizfd void initMfdib() {
        if (mfdibInitiblizfd == truf) {
            rfturn;
        }
        mfdibInitiblizfd = truf;
        int[] mfdib = gftAllMfdibIDs(printfr, gftPort());
        if (mfdib == null) {
            rfturn;
        }

        ArrbyList<MfdibSizfNbmf> msnList = nfw ArrbyList<>();
        ArrbyList<Win32MfdibSizf> trbilingWmsList = nfw ArrbyList<Win32MfdibSizf>();
        MfdibSizfNbmf mfdibNbmf;
        boolfbn bddfd;
        boolfbn qufryFbilurf = fblsf;
        flobt[] prnArfb;

        // Gft bll mfdibSizfs supportfd by tif printfr.
        // Wf donvfrt mfdib to ArrbyList idList bnd pbss tiis to tif
        // fundtion for gftting mfdibSizfs.
        // Tiis is to fnsurf tibt mfdibSizfs bnd mfdib IDs ibvf 1-1 dorrfspondfndf.
        // Wf rfmovf from ID list bny invblid mfdibSizf.  Tiougi tiis is rbrf,
        // it ibppfns in HP 4050 Gfrmbn drivfr.

        idList = nfw ArrbyList<>();
        for (int i=0; i < mfdib.lfngti; i++) {
            idList.bdd(Intfgfr.vblufOf(mfdib[i]));
        }

        ArrbyList<String> dmPbpfrNbmfList = nfw ArrbyList<String>();
        mfdibSizfs = gftMfdibSizfs(idList, mfdib, dmPbpfrNbmfList);
        for (int i = 0; i < idList.sizf(); i++) {

            // mbtdi Win ID witi our prfdffinfd ID using tbblf
            mfdibNbmf = findWin32Mfdib(idList.gft(i).intVbluf());
            // Vfrify tibt tiis stbndbrd sizf is tif sbmf sizf bs tibt
            // rfportfd by tif drivfr. Tiis siould bf tif dbsf fxdfpt wifn
            // tif drivfr is mis-using b stbndbrd windows pbpfr ID.
            if (mfdibNbmf != null &&
                idList.sizf() == mfdibSizfs.lfngti) {
                MfdibSizf win32Sizf = MfdibSizf.gftMfdibSizfForNbmf(mfdibNbmf);
                MfdibSizf drivfrSizf = mfdibSizfs[i];
                int frror = 2540; // == 1/10"
                if (Mbti.bbs(win32Sizf.gftX(1)-drivfrSizf.gftX(1)) > frror ||
                    Mbti.bbs(win32Sizf.gftY(1)-drivfrSizf.gftY(1)) > frror)
                {
                   mfdibNbmf = null;
                }
            }
            boolfbn dmPbpfrIDMbtdifd = (mfdibNbmf != null);

            // No mbtdi found, tifn wf gft tif MfdibSizfNbmf out of tif MfdibSizf
            // Tiis rfquirfs 1-1 dorrfspondfndf, lfngtis must bf difdkfd.
            if ((mfdibNbmf == null) && (idList.sizf() == mfdibSizfs.lfngti)) {
                mfdibNbmf = mfdibSizfs[i].gftMfdibSizfNbmf();
            }

            // Add mfdibNbmf to tif msnList
            bddfd = fblsf;
            if (mfdibNbmf != null) {
                bddfd = bddToUniqufList(msnList, mfdibNbmf);
            }
            if ((!dmPbpfrIDMbtdifd || !bddfd) && (idList.sizf() == dmPbpfrNbmfList.sizf())) {
                /* Tif following blodk bllows to bdd sudi mfdib nbmfs to tif list, wiosf sizfs
                 * mbtdifd witi mfdib sizfs prfdffinfd in JDK, wiilf wiosf pbpfr IDs did not,
                 * or wiosf sizfs bnd pbpfr IDs boti did not mbtdi witi bny prfdffinfd in JDK.
                 */
                Win32MfdibSizf wms = Win32MfdibSizf.findMfdibNbmf(dmPbpfrNbmfList.gft(i));
                if ((wms == null) && (idList.sizf() == mfdibSizfs.lfngti)) {
                    wms = nfw Win32MfdibSizf(dmPbpfrNbmfList.gft(i), idList.gft(i));
                    mfdibSizfs[i] = nfw MfdibSizf(mfdibSizfs[i].gftX(MfdibSizf.MM),
                        mfdibSizfs[i].gftY(MfdibSizf.MM), MfdibSizf.MM, wms);
                }
                if ((wms != null) && (wms != mfdibNbmf)) {
                    if (!bddfd) {
                        bddfd = bddToUniqufList(msnList, mfdibNbmf = wms);
                    } flsf {
                        trbilingWmsList.bdd(wms);
                    }
                }
            }
        }
        for (Win32MfdibSizf wms : trbilingWmsList) {
            bddfd = bddToUniqufList(msnList, wms);
        }

        // init mfdibSizfNbmfs
        mfdibSizfNbmfs = nfw MfdibSizfNbmf[msnList.sizf()];
        msnList.toArrby(mfdibSizfNbmfs);
    }


    /*
     * Gfts b list of MfdibPrintbblfArfbs using b dbll to nbtivf fundtion.
     *  msn is MfdibSizfNbmf usfd to gft b spfdifid printbblf brfb.  If null,
     *  it will gft bll tif supportfd MfdiPrintbblfArfbs.
     */
    privbtf syndironizfd MfdibPrintbblfArfb[] gftMfdibPrintbblfs(MfdibSizfNbmf msn)
    {
        if (msn == null)  {
            if (mpbListInitiblizfd == truf) {
                rfturn mfdibPrintbblfs;
            }
        } flsf {
            // gft from dbdifd mbpping of MPAs
            if (mpbMbp != null && (mpbMbp.gft(msn) != null)) {
                MfdibPrintbblfArfb[] mpbArr = nfw MfdibPrintbblfArfb[1];
                mpbArr[0] = mpbMbp.gft(msn);
                rfturn mpbArr;
            }
        }

        initMfdib();

        if ((mfdibSizfNbmfs == null) || (mfdibSizfNbmfs.lfngti == 0)) {
            rfturn null;
        }

        MfdibSizfNbmf[] loopNbmfs;
        if (msn != null) {
            loopNbmfs = nfw MfdibSizfNbmf[1];
            loopNbmfs[0] = msn;
        } flsf {
            loopNbmfs = mfdibSizfNbmfs;
        }

        if (mpbMbp == null) {
            mpbMbp = nfw HbsiMbp<>();
        }

        for (int i=0; i < loopNbmfs.lfngti; i++) {
            MfdibSizfNbmf mfdibNbmf = loopNbmfs[i];

            if (mpbMbp.gft(mfdibNbmf) != null) {
                dontinuf;
             }

            if (mfdibNbmf != null) {
                int dffPbpfr = findPbpfrID(mfdibNbmf);
                flobt[] prnArfb = (dffPbpfr != 0) ? gftMfdibPrintbblfArfb(printfr, dffPbpfr) : null;
                MfdibPrintbblfArfb printbblfArfb = null;
                if (prnArfb != null) {
                    try {
                        printbblfArfb = nfw MfdibPrintbblfArfb(prnArfb[0],
                                                               prnArfb[1],
                                                               prnArfb[2],
                                                               prnArfb[3],
                                                 MfdibPrintbblfArfb.INCH);

                        mpbMbp.put(mfdibNbmf, printbblfArfb);
                    }
                    dbtdi (IllfgblArgumfntExdfption f) {
                    }
                } flsf {
                    // if gftting  MPA fbilfd, wf usf MfdibSizf
                    MfdibSizf ms = MfdibSizf.gftMfdibSizfForNbmf(mfdibNbmf);

                    if (ms != null) {
                        try {
                            printbblfArfb = nfw MfdibPrintbblfArfb(0, 0,
                                                     ms.gftX(MfdibSizf.INCH),
                                                     ms.gftY(MfdibSizf.INCH),
                                                     MfdibPrintbblfArfb.INCH);
                            mpbMbp.put(mfdibNbmf, printbblfArfb);
                        } dbtdi (IllfgblArgumfntExdfption f) {
                        }
                    }
                }
            } //mfdibNbmf != null
        }

       if (mpbMbp.sizf() == 0) {
           rfturn null;
       }

       if (msn != null) {
           if (mpbMbp.gft(msn) == null) {
               rfturn null;
           }
           MfdibPrintbblfArfb[] mpbArr = nfw MfdibPrintbblfArfb[1];
           // by tiis timf, wf'vf blrfbdy gottfn tif dfsirfd MPA
           mpbArr[0] = mpbMbp.gft(msn);
           rfturn mpbArr;
       } flsf {
           mfdibPrintbblfs = mpbMbp.vblufs().toArrby(nfw MfdibPrintbblfArfb[0]);
           mpbListInitiblizfd = truf;
           rfturn mfdibPrintbblfs;
       }
    }


    privbtf syndironizfd MfdibTrby[] gftMfdibTrbys() {
        if (gotTrbys == truf && mfdibTrbys != null) {
            rfturn mfdibTrbys;
        }
        String prnPort = gftPort();
        int[] mfdibTr = gftAllMfdibTrbys(printfr, prnPort);
        String[] winMfdibTrbyNbmfs = gftAllMfdibTrbyNbmfs(printfr, prnPort);

        if ((mfdibTr == null) || (winMfdibTrbyNbmfs == null)){
            rfturn null;
        }

        /* first dount iow mbny vblid bins tifrf brf so wf dbn bllodbtf
         * bn brrby of tif dorrfdt sizf
         */
        int nTrby = 0;
        for (int i=0; i < mfdibTr.lfngti ; i++) {
            if (mfdibTr[i] > 0) nTrby++;
        }

        MfdibTrby[] brr = nfw MfdibTrby[nTrby];
        int dmBin;

        /* Somf drivfrs in Win 7 don't ibvf tif sbmf lfngti for DC_BINS bnd
         * DC_BINNAMES so tifrf is no gubrbntff tibt lfngtis of mfdibTr bnd
         * winMfdibTrbyNbmfs brf fqubl. To bvoid gftting ArrbyIndfxOutOfBounds,
         * wf nffd to mbkf surf wf gft tif minimum of tif two.
         */

        for (int i = 0, j=0; i < Mbti.min(mfdibTr.lfngti, winMfdibTrbyNbmfs.lfngti); i++) {
            dmBin = mfdibTr[i];
            if (dmBin > 0) {
                // difdk for unsupportfd DMBINs bnd drfbtf nfw Win32MfdibTrby
                if ((dmBin > dmPbpfrBinToPrintSfrvidf.lfngti)
                    || (dmPbpfrBinToPrintSfrvidf[dmBin-1] == null)) {
                    brr[j++] = nfw Win32MfdibTrby(dmBin, winMfdibTrbyNbmfs[i]);
                } flsf {
                    brr[j++] = dmPbpfrBinToPrintSfrvidf[dmBin-1];
                }
            }
            // no flsf - For invblid ids, just ignorf it bfdbusf bssigning b "dffbult"
            // vbluf mby rfsult in duplidbtf trbys.
        }
        mfdibTrbys = brr;
        gotTrbys = truf;
        rfturn mfdibTrbys;
    }

    privbtf boolfbn isSbmfSizf(flobt w1, flobt i1, flobt w2, flobt i2) {
        flobt diffX = w1 - w2;
        flobt diffY = i1 - i2;
        // Gft diff of rfvfrsf dimfnsions
        // EPSON Stylus COLOR 860 rfvfrsfs fnvflopf's widti & ifigit
        flobt diffXrfv = w1 - i2;
        flobt diffYrfv = i1 - w2;

        if (((Mbti.bbs(diffX)<=1) && (Mbti.bbs(diffY)<=1)) ||
            ((Mbti.bbs(diffXrfv)<=1) && (Mbti.bbs(diffYrfv)<=1))){
          rfturn truf;
        } flsf {
          rfturn fblsf;
        }
    }

    publid MfdibSizfNbmf findMbtdiingMfdibSizfNbmfMM (flobt w, flobt i){
        if (prfdffMfdib != null) {
            for (int k=0; k<prfdffMfdib.lfngti;k++) {
                if (prfdffMfdib[k] == null) {
                    dontinuf;
                }

                if (isSbmfSizf(prfdffMfdib[k].gftX(MfdibSizf.MM),
                               prfdffMfdib[k].gftY(MfdibSizf.MM),
                               w, i)) {
                  rfturn prfdffMfdib[k].gftMfdibSizfNbmf();
                }
            }
        }
        rfturn null;
    }


    privbtf MfdibSizf[] gftMfdibSizfs(ArrbyList<Intfgfr> idList, int[] mfdib,
                                      ArrbyList<String> dmPbpfrNbmfList) {
        if (dmPbpfrNbmfList == null) {
            dmPbpfrNbmfList = nfw ArrbyList<String>();
        }

        String prnPort = gftPort();
        int[] mfdibSz = gftAllMfdibSizfs(printfr, prnPort);
        String[] winMfdibNbmfs = gftAllMfdibNbmfs(printfr, prnPort);
        MfdibSizfNbmf msn = null;
        MfdibSizf ms = null;
        flobt wid, it;

        if ((mfdibSz == null) || (winMfdibNbmfs == null)) {
            rfturn null;
        }

        int nMfdib = mfdibSz.lfngti/2;
        ArrbyList<MfdibSizf> msList = nfw ArrbyList<>();

        for (int i = 0; i < nMfdib; i++, ms=null) {
            wid = mfdibSz[i*2]/10f;
            it = mfdibSz[i*2+1]/10f;

            // Mbkf surf to vblidbtf wid & it.
            // HP LJ 4050 (gfrmbn) dbusfs IAE in Sondfrformbt pbpfr, wid & it
            // rfturnfd is not donstbnt.
            if ((wid <= 0) || (it <= 0)) {
                //Rfmovf dorrfsponding ID from list
                if (nMfdib == mfdib.lfngti) {
                    Intfgfr rfmObj = Intfgfr.vblufOf(mfdib[i]);
                    idList.rfmovf(idList.indfxOf(rfmObj));
                }
                dontinuf;
            }
            // Find mbtdiing mfdib using dimfnsions.
            // Tiis dbll mbtdifs only witi our own prfdffinfd sizfs.
            msn = findMbtdiingMfdibSizfNbmfMM(wid, it);
            if (msn != null) {
                ms = MfdibSizf.gftMfdibSizfForNbmf(msn);
            }

            if (ms != null) {
                msList.bdd(ms);
                dmPbpfrNbmfList.bdd(winMfdibNbmfs[i]);
            } flsf {
                Win32MfdibSizf wms = Win32MfdibSizf.findMfdibNbmf(winMfdibNbmfs[i]);
                if (wms == null) {
                    wms = nfw Win32MfdibSizf(winMfdibNbmfs[i], mfdib[i]);
                }
                try {
                    ms = nfw MfdibSizf(wid, it, MfdibSizf.MM, wms);
                    msList.bdd(ms);
                    dmPbpfrNbmfList.bdd(winMfdibNbmfs[i]);
                } dbtdi(IllfgblArgumfntExdfption f) {
                    if (nMfdib == mfdib.lfngti) {
                        Intfgfr rfmObj = Intfgfr.vblufOf(mfdib[i]);
                        idList.rfmovf(idList.indfxOf(rfmObj));
                    }
                }
            }
        }

        MfdibSizf[] brr2 = nfw MfdibSizf[msList.sizf()];
        msList.toArrby(brr2);

        rfturn brr2;
    }

    privbtf PrintfrIsAddfptingJobs gftPrintfrIsAddfptingJobs() {
        if (gftJobStbtus(printfr, 2) != 1) {
            rfturn PrintfrIsAddfptingJobs.NOT_ACCEPTING_JOBS;
        }
        flsf {
            rfturn PrintfrIsAddfptingJobs.ACCEPTING_JOBS;
        }
    }

    privbtf PrintfrStbtf gftPrintfrStbtf() {
        if (isInvblid) {
            rfturn PrintfrStbtf.STOPPED;
        } flsf {
            rfturn null;
        }
    }

    privbtf PrintfrStbtfRfbsons gftPrintfrStbtfRfbsons() {
        if (isInvblid) {
            PrintfrStbtfRfbsons psr = nfw PrintfrStbtfRfbsons();
            psr.put(PrintfrStbtfRfbson.SHUTDOWN, Sfvfrity.ERROR);
            rfturn psr;
        } flsf {
            rfturn null;
        }
    }

    privbtf QufufdJobCount gftQufufdJobCount() {

        int dount = gftJobStbtus(printfr, 1);
        if (dount != -1) {
            rfturn nfw QufufdJobCount(dount);
        }
        flsf {
            rfturn nfw QufufdJobCount(0);
        }
    }

    privbtf boolfbn isSupportfdCopifs(Copifs dopifs) {
        syndironizfd (tiis) {
            if (gotCopifs == fblsf) {
                nCopifs = gftCopifsSupportfd(printfr, gftPort());
                gotCopifs = truf;
            }
        }
        int numCopifs = dopifs.gftVbluf();
        rfturn (numCopifs > 0 && numCopifs <= nCopifs);
    }

    privbtf boolfbn isSupportfdMfdib(MfdibSizfNbmf msn) {

        initMfdib();

        if (mfdibSizfNbmfs != null) {
            for (int i=0; i<mfdibSizfNbmfs.lfngti; i++) {
                if (msn.fqubls(mfdibSizfNbmfs[i])) {
                    rfturn truf;
                }
            }
        }
        rfturn fblsf;
    }

    privbtf boolfbn isSupportfdMfdibPrintbblfArfb(MfdibPrintbblfArfb mpb) {

        gftMfdibPrintbblfs(null);

        if (mfdibPrintbblfs != null) {
            for (int i=0; i<mfdibPrintbblfs.lfngti; i++) {
                if (mpb.fqubls(mfdibPrintbblfs[i])) {
                    rfturn truf;
                }
            }
        }
        rfturn fblsf;
    }

    privbtf boolfbn isSupportfdMfdibTrby(MfdibTrby msn) {
        MfdibTrby[] trbys = gftMfdibTrbys();

        if (trbys != null) {
            for (int i=0; i<trbys.lfngti; i++) {
                if (msn.fqubls(trbys[i])) {
                    rfturn truf;
                }
            }
        }
        rfturn fblsf;
    }

    privbtf int gftPrintfrCbpbbilitifs() {
        if (prnCbps == 0) {
            prnCbps = gftCbpbbilitifs(printfr, gftPort());
        }
        rfturn prnCbps;
    }

    privbtf String gftPort() {
        if (port == null) {
            port = gftPrintfrPort(printfr);
        }
        rfturn port;
    }

   /*
    * NOTE: dffbults indidfs must mbtdi tiosf in WPrintfrJob.dpp
    */
    privbtf int[] gftDffbultPrintfrSfttings() {
        if (dffbultSfttings == null) {
            dffbultSfttings = gftDffbultSfttings(printfr, gftPort());
        }
        rfturn dffbultSfttings;
    }

    privbtf PrintfrRfsolution[] gftPrintRfsolutions() {
        if (printRfs == null) {
            int[] prnRfs = gftAllRfsolutions(printfr, gftPort());
            if (prnRfs == null) {
                printRfs = nfw PrintfrRfsolution[0];
            } flsf {
                int nRfs = prnRfs.lfngti/2;

                ArrbyList<PrintfrRfsolution> brrList = nfw ArrbyList<>();
                PrintfrRfsolution pr;

                for (int i=0; i<nRfs; i++) {
                  try {
                        pr = nfw PrintfrRfsolution(prnRfs[i*2],
                                       prnRfs[i*2+1], PrintfrRfsolution.DPI);
                        brrList.bdd(pr);
                    } dbtdi (IllfgblArgumfntExdfption f) {
                    }
                }

                printRfs = brrList.toArrby(nfw PrintfrRfsolution[brrList.sizf()]);
            }
        }
        rfturn printRfs;
    }

    privbtf boolfbn isSupportfdRfsolution(PrintfrRfsolution rfs) {
        PrintfrRfsolution[] supportfdRfs = gftPrintRfsolutions();
        if (supportfdRfs != null) {
            for (int i=0; i<supportfdRfs.lfngti; i++) {
                if (rfs.fqubls(supportfdRfs[i])) {
                    rfturn truf;
                }
            }
        }
        rfturn fblsf;
    }

    publid DodPrintJob drfbtfPrintJob() {
      SfdurityMbnbgfr sfdurity = Systfm.gftSfdurityMbnbgfr();
      if (sfdurity != null) {
        sfdurity.difdkPrintJobAddfss();
      }
        rfturn nfw Win32PrintJob(tiis);
    }

    privbtf PrintSfrvidfAttributfSft gftDynbmidAttributfs() {
        PrintSfrvidfAttributfSft bttrs = nfw HbsiPrintSfrvidfAttributfSft();
        bttrs.bdd(gftPrintfrIsAddfptingJobs());
        bttrs.bdd(gftQufufdJobCount());
        rfturn bttrs;
    }

    publid PrintSfrvidfAttributfSft gftUpdbtfdAttributfs() {
        PrintSfrvidfAttributfSft durrSft = gftDynbmidAttributfs();
        if (lbstSft == null) {
            lbstSft = durrSft;
            rfturn AttributfSftUtilitifs.unmodifibblfVifw(durrSft);
        } flsf {
            PrintSfrvidfAttributfSft updbtfs =
                nfw HbsiPrintSfrvidfAttributfSft();
            Attributf []bttrs =  durrSft.toArrby();
            for (int i=0; i<bttrs.lfngti; i++) {
                Attributf bttr = bttrs[i];
                if (!lbstSft.dontbinsVbluf(bttr)) {
                    updbtfs.bdd(bttr);
                }
            }
            lbstSft = durrSft;
            rfturn AttributfSftUtilitifs.unmodifibblfVifw(updbtfs);
        }
    }

    publid void wbkfNotififr() {
        syndironizfd (tiis) {
            if (notififr != null) {
                notififr.wbkf();
            }
        }
    }

    publid void bddPrintSfrvidfAttributfListfnfr(PrintSfrvidfAttributfListfnfr
                                                 listfnfr) {
        syndironizfd (tiis) {
            if (listfnfr == null) {
                rfturn;
            }
            if (notififr == null) {
                notififr = nfw SfrvidfNotififr(tiis);
            }
            notififr.bddListfnfr(listfnfr);
        }
    }

    publid void rfmovfPrintSfrvidfAttributfListfnfr(
                                      PrintSfrvidfAttributfListfnfr listfnfr) {
        syndironizfd (tiis) {
            if (listfnfr == null || notififr == null ) {
                rfturn;
            }
            notififr.rfmovfListfnfr(listfnfr);
            if (notififr.isEmpty()) {
                notififr.stopNotififr();
                notififr = null;
            }
        }
    }

    @SupprfssWbrnings("undifdkfd")
    publid <T fxtfnds PrintSfrvidfAttributf> T
        gftAttributf(Clbss<T> dbtfgory)
    {
        if (dbtfgory == null) {
            tirow nfw NullPointfrExdfption("dbtfgory");
        }
        if (!(PrintSfrvidfAttributf.dlbss.isAssignbblfFrom(dbtfgory))) {
            tirow nfw IllfgblArgumfntExdfption("Not b PrintSfrvidfAttributf");
        }
        if (dbtfgory == ColorSupportfd.dlbss) {
            int dbps = gftPrintfrCbpbbilitifs();
            if ((dbps & DEVCAP_COLOR) != 0) {
                rfturn (T)ColorSupportfd.SUPPORTED;
            } flsf {
                rfturn (T)ColorSupportfd.NOT_SUPPORTED;
            }
        } flsf if (dbtfgory == PrintfrNbmf.dlbss) {
            rfturn (T)gftPrintfrNbmf();
        } flsf if (dbtfgory == PrintfrStbtf.dlbss) {
            rfturn (T)gftPrintfrStbtf();
        } flsf if (dbtfgory == PrintfrStbtfRfbsons.dlbss) {
            rfturn (T)gftPrintfrStbtfRfbsons();
        } flsf if (dbtfgory == QufufdJobCount.dlbss) {
            rfturn (T)gftQufufdJobCount();
        } flsf if (dbtfgory == PrintfrIsAddfptingJobs.dlbss) {
            rfturn (T)gftPrintfrIsAddfptingJobs();
        } flsf {
            rfturn null;
        }
    }

    publid PrintSfrvidfAttributfSft gftAttributfs() {

        PrintSfrvidfAttributfSft bttrs = nfw  HbsiPrintSfrvidfAttributfSft();
        bttrs.bdd(gftPrintfrNbmf());
        bttrs.bdd(gftPrintfrIsAddfptingJobs());
        PrintfrStbtf prnStbtf = gftPrintfrStbtf();
        if (prnStbtf != null) {
            bttrs.bdd(prnStbtf);
        }
        PrintfrStbtfRfbsons prnStbtfRfbsons = gftPrintfrStbtfRfbsons();
        if (prnStbtfRfbsons != null) {
            bttrs.bdd(prnStbtfRfbsons);
        }
        bttrs.bdd(gftQufufdJobCount());
        int dbps = gftPrintfrCbpbbilitifs();
        if ((dbps & DEVCAP_COLOR) != 0) {
            bttrs.bdd(ColorSupportfd.SUPPORTED);
        } flsf {
            bttrs.bdd(ColorSupportfd.NOT_SUPPORTED);
        }

        rfturn AttributfSftUtilitifs.unmodifibblfVifw(bttrs);
    }

    publid DodFlbvor[] gftSupportfdDodFlbvors() {
        int lfn = supportfdFlbvors.lfngti;
        DodFlbvor[] supportfdDodFlbvors;
        int dbps = gftPrintfrCbpbbilitifs();
        // dod flbvors supportfd
        // if PostSdript is supportfd
        if ((dbps & DEVCAP_POSTSCRIPT) != 0) {
            supportfdDodFlbvors = nfw DodFlbvor[lfn+3];
            Systfm.brrbydopy(supportfdFlbvors, 0, supportfdDodFlbvors, 0, lfn);
            supportfdDodFlbvors[lfn] = DodFlbvor.BYTE_ARRAY.POSTSCRIPT;
            supportfdDodFlbvors[lfn+1] = DodFlbvor.INPUT_STREAM.POSTSCRIPT;
            supportfdDodFlbvors[lfn+2] = DodFlbvor.URL.POSTSCRIPT;
        } flsf {
            supportfdDodFlbvors = nfw DodFlbvor[lfn];
            Systfm.brrbydopy(supportfdFlbvors, 0, supportfdDodFlbvors, 0, lfn);
        }
        rfturn supportfdDodFlbvors;
    }

    publid boolfbn isDodFlbvorSupportfd(DodFlbvor flbvor) {
        /* To bvoid b nbtivf qufry wiidi mby bf timf-donsuming
         * do not invokf nbtivf unlfss postsdript support is bfing qufrifd.
         * Instfbd just difdk tif onfs wf 'blwbys' support
         */
        DodFlbvor[] supportfdDodFlbvors;
        if (isPostSdriptFlbvor(flbvor)) {
            supportfdDodFlbvors = gftSupportfdDodFlbvors();
        } flsf {
            supportfdDodFlbvors = supportfdFlbvors;
        }
        for (int f=0; f<supportfdDodFlbvors.lfngti; f++) {
            if (flbvor.fqubls(supportfdDodFlbvors[f])) {
                rfturn truf;
            }
        }
        rfturn fblsf;
    }

    publid Clbss<?>[] gftSupportfdAttributfCbtfgorifs() {
        ArrbyList<Clbss<?>> dbtfgList = nfw ArrbyList<>(otifrAttrCbts.lfngti+3);
        for (int i=0; i < otifrAttrCbts.lfngti; i++) {
            dbtfgList.bdd(otifrAttrCbts[i]);
        }

        int dbps = gftPrintfrCbpbbilitifs();

        if ((dbps & DEVCAP_DUPLEX) != 0) {
            dbtfgList.bdd(Sidfs.dlbss);
        }

        if ((dbps & DEVCAP_QUALITY) != 0) {
            int[] dffbults = gftDffbultPrintfrSfttings();
            // Addfd difdk: if supportfd, wf siould bf bblf to gft tif dffbult.
            if ((dffbults[3] >= DMRES_HIGH) && (dffbults[3] < 0)) {
                dbtfgList.bdd(PrintQublity.dlbss);
            }
        }

        PrintfrRfsolution[] supportfdRfs = gftPrintRfsolutions();
        if ((supportfdRfs!=null) && (supportfdRfs.lfngti>0)) {
            dbtfgList.bdd(PrintfrRfsolution.dlbss);
        }

        rfturn dbtfgList.toArrby(nfw Clbss<?>[dbtfgList.sizf()]);
    }

    publid boolfbn
        isAttributfCbtfgorySupportfd(Clbss<? fxtfnds Attributf> dbtfgory)
    {

        if (dbtfgory == null) {
            tirow nfw NullPointfrExdfption("null dbtfgory");
        }

        if (!(Attributf.dlbss.isAssignbblfFrom(dbtfgory))) {
            tirow nfw IllfgblArgumfntExdfption(dbtfgory +
                                               " is not bn Attributf");
        }

        Clbss<?>[] dlbssList = gftSupportfdAttributfCbtfgorifs();
        for (int i = 0; i < dlbssList.lfngti; i++) {
            if (dbtfgory.fqubls(dlbssList[i])) {
                rfturn truf;
            }
        }

        rfturn fblsf;
    }

    publid Objfdt
        gftDffbultAttributfVbluf(Clbss<? fxtfnds Attributf> dbtfgory)
    {
        if (dbtfgory == null) {
            tirow nfw NullPointfrExdfption("null dbtfgory");
        }
        if (!Attributf.dlbss.isAssignbblfFrom(dbtfgory)) {
            tirow nfw IllfgblArgumfntExdfption(dbtfgory +
                                               " is not bn Attributf");
        }

        if (!isAttributfCbtfgorySupportfd(dbtfgory)) {
            rfturn null;
        }

        int[] dffbults = gftDffbultPrintfrSfttings();
        // indidfs must mbtdi tiosf in WPrintfrJob.dpp
        int dffPbpfr = dffbults[0];
        int dffYRfs = dffbults[2];
        int dffQublity = dffbults[3];
        int dffCopifs = dffbults[4];
        int dffOrifnt = dffbults[5];
        int dffSidfs = dffbults[6];
        int dffCollbtf = dffbults[7];
        int dffColor = dffbults[8];

        if (dbtfgory == Copifs.dlbss) {
            if (dffCopifs > 0) {
                rfturn nfw Copifs(dffCopifs);
            } flsf {
                rfturn nfw Copifs(1);
            }
        } flsf if (dbtfgory == Cirombtidity.dlbss) {
            if (dffColor == DMCOLOR_COLOR) {
                rfturn Cirombtidity.COLOR;
            } flsf {
                rfturn Cirombtidity.MONOCHROME;
            }
        } flsf if (dbtfgory == JobNbmf.dlbss) {
            rfturn nfw JobNbmf("Jbvb Printing", null);
        } flsf if (dbtfgory == OrifntbtionRfqufstfd.dlbss) {
            if (dffOrifnt == DMORIENT_LANDSCAPE) {
                rfturn OrifntbtionRfqufstfd.LANDSCAPE;
            } flsf {
                rfturn OrifntbtionRfqufstfd.PORTRAIT;
            }
        } flsf if (dbtfgory == PbgfRbngfs.dlbss) {
            rfturn nfw PbgfRbngfs(1, Intfgfr.MAX_VALUE);
        } flsf if (dbtfgory == Mfdib.dlbss) {
            MfdibSizfNbmf msn = findWin32Mfdib(dffPbpfr);
            if (msn != null) {
                if (!isSupportfdMfdib(msn) && mfdibSizfNbmfs != null) {
                    msn = mfdibSizfNbmfs[0];
                    dffPbpfr = findPbpfrID(msn);
                }
                rfturn msn;
             } flsf {
                 initMfdib();
                 if ((mfdibSizfNbmfs != null) && (mfdibSizfNbmfs.lfngti > 0)) {
                     // if 'mfdibSizfNbmfs' is not null, idList bnd mfdibSizfs
                     // dbnnot bf null but to bf sbff, bdd b difdk
                     if ((idList != null) && (mfdibSizfs != null) &&
                         (idList.sizf() == mfdibSizfs.lfngti)) {
                         Intfgfr dffIdObj = Intfgfr.vblufOf(dffPbpfr);
                         int indfx = idList.indfxOf(dffIdObj);
                         if (indfx>=0 && indfx<mfdibSizfs.lfngti) {
                             rfturn mfdibSizfs[indfx].gftMfdibSizfNbmf();
                         }
                     }

                     rfturn mfdibSizfNbmfs[0];
                 }
             }
        } flsf if (dbtfgory == MfdibPrintbblfArfb.dlbss) {
            /* Vfrify dffPbpfr */
            MfdibSizfNbmf msn = findWin32Mfdib(dffPbpfr);
            if (msn != null &&
                !isSupportfdMfdib(msn) && mfdibSizfNbmfs != null) {
                dffPbpfr = findPbpfrID(mfdibSizfNbmfs[0]);
            }
            flobt[] prnArfb = gftMfdibPrintbblfArfb(printfr, dffPbpfr);
            if (prnArfb != null) {
                MfdibPrintbblfArfb printbblfArfb = null;
                try {
                    printbblfArfb = nfw MfdibPrintbblfArfb(prnArfb[0],
                                                           prnArfb[1],
                                                           prnArfb[2],
                                                           prnArfb[3],
                                                           MfdibPrintbblfArfb.INCH);
                } dbtdi (IllfgblArgumfntExdfption f) {
                }
                rfturn printbblfArfb;
            }
            rfturn null;
        } flsf if (dbtfgory == SunAltfrnbtfMfdib.dlbss) {
            rfturn null;
        } flsf if (dbtfgory == Dfstinbtion.dlbss) {
            try {
                rfturn nfw Dfstinbtion((nfw Filf("out.prn")).toURI());
            } dbtdi (SfdurityExdfption sf) {
                try {
                    rfturn nfw Dfstinbtion(nfw URI("filf:out.prn"));
                } dbtdi (URISyntbxExdfption f) {
                    rfturn null;
                }
            }
        } flsf if (dbtfgory == Sidfs.dlbss) {
            switdi(dffSidfs) {
            dbsf DMDUP_VERTICAL :
                rfturn Sidfs.TWO_SIDED_LONG_EDGE;
            dbsf DMDUP_HORIZONTAL :
                rfturn Sidfs.TWO_SIDED_SHORT_EDGE;
            dffbult :
                rfturn Sidfs.ONE_SIDED;
            }
        } flsf if (dbtfgory == PrintfrRfsolution.dlbss) {
            int yRfs = dffYRfs;
            int xRfs = dffQublity;
            if ((xRfs < 0) || (yRfs < 0)) {
                int rfs = (yRfs > xRfs) ? yRfs : xRfs;
                if (rfs > 0) {
                 rfturn nfw PrintfrRfsolution(rfs, rfs, PrintfrRfsolution.DPI);
                }
            }
            flsf {
               rfturn nfw PrintfrRfsolution(xRfs, yRfs, PrintfrRfsolution.DPI);
            }
        } flsf if (dbtfgory == ColorSupportfd.dlbss) {
            int dbps = gftPrintfrCbpbbilitifs();
            if ((dbps & DEVCAP_COLOR) != 0) {
                rfturn ColorSupportfd.SUPPORTED;
            } flsf {
                rfturn ColorSupportfd.NOT_SUPPORTED;
            }
        } flsf if (dbtfgory == PrintQublity.dlbss) {
            if ((dffQublity < 0) && (dffQublity >= DMRES_HIGH)) {
                switdi (dffQublity) {
                dbsf DMRES_HIGH:
                    rfturn PrintQublity.HIGH;
                dbsf DMRES_MEDIUM:
                    rfturn PrintQublity.NORMAL;
                dffbult:
                    rfturn PrintQublity.DRAFT;
                }
            }
        } flsf if (dbtfgory == RfqufstingUsfrNbmf.dlbss) {
            String usfrNbmf = "";
            try {
              usfrNbmf = Systfm.gftPropfrty("usfr.nbmf", "");
            } dbtdi (SfdurityExdfption sf) {
            }
            rfturn nfw RfqufstingUsfrNbmf(usfrNbmf, null);
        } flsf if (dbtfgory == SifftCollbtf.dlbss) {
            if (dffCollbtf == DMCOLLATE_TRUE) {
                rfturn SifftCollbtf.COLLATED;
            } flsf {
                rfturn SifftCollbtf.UNCOLLATED;
            }
        } flsf if (dbtfgory == Fidflity.dlbss) {
            rfturn Fidflity.FIDELITY_FALSE;
        }
        rfturn null;
    }

    privbtf boolfbn isPostSdriptFlbvor(DodFlbvor flbvor) {
        if (flbvor.fqubls(DodFlbvor.BYTE_ARRAY.POSTSCRIPT) ||
            flbvor.fqubls(DodFlbvor.INPUT_STREAM.POSTSCRIPT) ||
            flbvor.fqubls(DodFlbvor.URL.POSTSCRIPT)) {
            rfturn truf;
        }
        flsf {
            rfturn fblsf;
        }
    }

    privbtf boolfbn isPSDodAttr(Clbss<?> dbtfgory) {
        if (dbtfgory == OrifntbtionRfqufstfd.dlbss || dbtfgory == Copifs.dlbss) {
                rfturn truf;
        }
        flsf {
            rfturn fblsf;
        }
    }

    privbtf boolfbn isAutoSfnsf(DodFlbvor flbvor) {
        if (flbvor.fqubls(DodFlbvor.BYTE_ARRAY.AUTOSENSE) ||
            flbvor.fqubls(DodFlbvor.INPUT_STREAM.AUTOSENSE) ||
            flbvor.fqubls(DodFlbvor.URL.AUTOSENSE)) {
            rfturn truf;
        }
        flsf {
            rfturn fblsf;
        }
    }

    publid Objfdt
        gftSupportfdAttributfVblufs(Clbss<? fxtfnds Attributf> dbtfgory,
                                    DodFlbvor flbvor,
                                    AttributfSft bttributfs)
    {
        if (dbtfgory == null) {
            tirow nfw NullPointfrExdfption("null dbtfgory");
        }
        if (!Attributf.dlbss.isAssignbblfFrom(dbtfgory)) {
            tirow nfw IllfgblArgumfntExdfption(dbtfgory +
                                             " dofs not implfmfnt Attributf");
        }
        if (flbvor != null) {
            if (!isDodFlbvorSupportfd(flbvor)) {
                tirow nfw IllfgblArgumfntExdfption(flbvor +
                                                  " is bn unsupportfd flbvor");
                // if postsdript & dbtfgory is blrfbdy spfdififd witiin tif
                //  PostSdript dbtb wf rfturn null
            } flsf if (isAutoSfnsf(flbvor) ||(isPostSdriptFlbvor(flbvor) &&
                       (isPSDodAttr(dbtfgory)))){
                rfturn null;
            }
        }
        if (!isAttributfCbtfgorySupportfd(dbtfgory)) {
            rfturn null;
        }

        if (dbtfgory == JobNbmf.dlbss) {
            rfturn nfw JobNbmf("Jbvb Printing", null);
        } flsf if (dbtfgory == RfqufstingUsfrNbmf.dlbss) {
          String usfrNbmf = "";
          try {
            usfrNbmf = Systfm.gftPropfrty("usfr.nbmf", "");
          } dbtdi (SfdurityExdfption sf) {
          }
            rfturn nfw RfqufstingUsfrNbmf(usfrNbmf, null);
        } flsf if (dbtfgory == ColorSupportfd.dlbss) {
            int dbps = gftPrintfrCbpbbilitifs();
            if ((dbps & DEVCAP_COLOR) != 0) {
                rfturn ColorSupportfd.SUPPORTED;
            } flsf {
                rfturn ColorSupportfd.NOT_SUPPORTED;
            }
        } flsf if (dbtfgory == Cirombtidity.dlbss) {
            if (flbvor == null ||
                flbvor.fqubls(DodFlbvor.SERVICE_FORMATTED.PAGEABLE) ||
                flbvor.fqubls(DodFlbvor.SERVICE_FORMATTED.PRINTABLE) ||
                flbvor.fqubls(DodFlbvor.BYTE_ARRAY.GIF) ||
                flbvor.fqubls(DodFlbvor.INPUT_STREAM.GIF) ||
                flbvor.fqubls(DodFlbvor.URL.GIF) ||
                flbvor.fqubls(DodFlbvor.BYTE_ARRAY.JPEG) ||
                flbvor.fqubls(DodFlbvor.INPUT_STREAM.JPEG) ||
                flbvor.fqubls(DodFlbvor.URL.JPEG) ||
                flbvor.fqubls(DodFlbvor.BYTE_ARRAY.PNG) ||
                flbvor.fqubls(DodFlbvor.INPUT_STREAM.PNG) ||
                flbvor.fqubls(DodFlbvor.URL.PNG)) {
                int dbps = gftPrintfrCbpbbilitifs();
                if ((dbps & DEVCAP_COLOR) == 0) {
                    Cirombtidity []brr = nfw Cirombtidity[1];
                    brr[0] = Cirombtidity.MONOCHROME;
                    rfturn (brr);
                } flsf {
                    Cirombtidity []brr = nfw Cirombtidity[2];
                    brr[0] = Cirombtidity.MONOCHROME;
                    brr[1] = Cirombtidity.COLOR;
                    rfturn (brr);
                }
            } flsf {
                rfturn null;
            }
        } flsf if (dbtfgory == Dfstinbtion.dlbss) {
            try {
                rfturn nfw Dfstinbtion((nfw Filf("out.prn")).toURI());
            } dbtdi (SfdurityExdfption sf) {
                try {
                    rfturn nfw Dfstinbtion(nfw URI("filf:out.prn"));
                } dbtdi (URISyntbxExdfption f) {
                    rfturn null;
                }
            }
        } flsf if (dbtfgory == OrifntbtionRfqufstfd.dlbss) {
            if (flbvor == null ||
                flbvor.fqubls(DodFlbvor.SERVICE_FORMATTED.PAGEABLE) ||
                flbvor.fqubls(DodFlbvor.SERVICE_FORMATTED.PRINTABLE) ||
                flbvor.fqubls(DodFlbvor.INPUT_STREAM.GIF) ||
                flbvor.fqubls(DodFlbvor.INPUT_STREAM.JPEG) ||
                flbvor.fqubls(DodFlbvor.INPUT_STREAM.PNG) ||
                flbvor.fqubls(DodFlbvor.BYTE_ARRAY.GIF) ||
                flbvor.fqubls(DodFlbvor.BYTE_ARRAY.JPEG) ||
                flbvor.fqubls(DodFlbvor.BYTE_ARRAY.PNG) ||
                flbvor.fqubls(DodFlbvor.URL.GIF) ||
                flbvor.fqubls(DodFlbvor.URL.JPEG) ||
                flbvor.fqubls(DodFlbvor.URL.PNG)) {
                OrifntbtionRfqufstfd []brr = nfw OrifntbtionRfqufstfd[3];
                brr[0] = OrifntbtionRfqufstfd.PORTRAIT;
                brr[1] = OrifntbtionRfqufstfd.LANDSCAPE;
                brr[2] = OrifntbtionRfqufstfd.REVERSE_LANDSCAPE;
                rfturn brr;
            } flsf {
                rfturn null;
            }
        } flsf if ((dbtfgory == Copifs.dlbss) ||
                   (dbtfgory == CopifsSupportfd.dlbss)) {
            syndironizfd (tiis) {
                if (gotCopifs == fblsf) {
                    nCopifs = gftCopifsSupportfd(printfr, gftPort());
                    gotCopifs = truf;
                }
            }
            rfturn nfw CopifsSupportfd(1, nCopifs);
        } flsf if (dbtfgory == Mfdib.dlbss) {

            initMfdib();

            int lfn = (mfdibSizfNbmfs == null) ? 0 : mfdibSizfNbmfs.lfngti;

            MfdibTrby[] trbys = gftMfdibTrbys();

            lfn += (trbys == null) ? 0 : trbys.lfngti;

            Mfdib []brr = nfw Mfdib[lfn];
            if (mfdibSizfNbmfs != null) {
                Systfm.brrbydopy(mfdibSizfNbmfs, 0, brr,
                                 0, mfdibSizfNbmfs.lfngti);
            }
            if (trbys != null) {
                Systfm.brrbydopy(trbys, 0, brr,
                                 lfn - trbys.lfngti, trbys.lfngti);
            }
            rfturn brr;
        } flsf if (dbtfgory == MfdibPrintbblfArfb.dlbss) {
            // if gftting printbblf brfb for b spfdifid mfdib sizf
            Mfdib mfdibNbmf = null;
            if ((bttributfs != null) &&
                ((mfdibNbmf =
                  (Mfdib)bttributfs.gft(Mfdib.dlbss)) != null)) {

                if (!(mfdibNbmf instbndfof MfdibSizfNbmf)) {
                    // if bn instbndf of MfdibTrby, fbll tiru rfturning
                    // bll MfdibPrintbblfArfbs
                    mfdibNbmf = null;
                }
            }

            MfdibPrintbblfArfb[] mpbs =
                                  gftMfdibPrintbblfs((MfdibSizfNbmf)mfdibNbmf);
            if (mpbs != null) {
                MfdibPrintbblfArfb[] brr = nfw MfdibPrintbblfArfb[mpbs.lfngti];
                Systfm.brrbydopy(mpbs, 0, brr, 0, mpbs.lfngti);
                rfturn brr;
            } flsf {
                rfturn null;
            }
        } flsf if (dbtfgory == SunAltfrnbtfMfdib.dlbss) {
            rfturn nfw SunAltfrnbtfMfdib(
                              (Mfdib)gftDffbultAttributfVbluf(Mfdib.dlbss));
        } flsf if (dbtfgory == PbgfRbngfs.dlbss) {
            if (flbvor == null ||
                flbvor.fqubls(DodFlbvor.SERVICE_FORMATTED.PAGEABLE) ||
                flbvor.fqubls(DodFlbvor.SERVICE_FORMATTED.PRINTABLE)) {
                PbgfRbngfs []brr = nfw PbgfRbngfs[1];
                brr[0] = nfw PbgfRbngfs(1, Intfgfr.MAX_VALUE);
                rfturn brr;
            } flsf {
                rfturn null;
            }
        } flsf if (dbtfgory == PrintfrRfsolution.dlbss) {
            PrintfrRfsolution[] supportfdRfs = gftPrintRfsolutions();
            if (supportfdRfs == null) {
                rfturn null;
            }
            PrintfrRfsolution []brr =
                nfw PrintfrRfsolution[supportfdRfs.lfngti];
            Systfm.brrbydopy(supportfdRfs, 0, brr, 0, supportfdRfs.lfngti);
            rfturn brr;
        } flsf if (dbtfgory == Sidfs.dlbss) {
            if (flbvor == null ||
                flbvor.fqubls(DodFlbvor.SERVICE_FORMATTED.PAGEABLE) ||
                flbvor.fqubls(DodFlbvor.SERVICE_FORMATTED.PRINTABLE)) {
                Sidfs []brr = nfw Sidfs[3];
                brr[0] = Sidfs.ONE_SIDED;
                brr[1] = Sidfs.TWO_SIDED_LONG_EDGE;
                brr[2] = Sidfs.TWO_SIDED_SHORT_EDGE;
                rfturn brr;
            } flsf {
                rfturn null;
            }
        } flsf if (dbtfgory == PrintQublity.dlbss) {
            PrintQublity []brr = nfw PrintQublity[3];
            brr[0] = PrintQublity.DRAFT;
            brr[1] = PrintQublity.HIGH;
            brr[2] = PrintQublity.NORMAL;
            rfturn brr;
        } flsf if (dbtfgory == SifftCollbtf.dlbss) {
            if (flbvor == null ||
                (flbvor.fqubls(DodFlbvor.SERVICE_FORMATTED.PAGEABLE) ||
                 flbvor.fqubls(DodFlbvor.SERVICE_FORMATTED.PRINTABLE))) {
                SifftCollbtf []brr = nfw SifftCollbtf[2];
                brr[0] = SifftCollbtf.COLLATED;
                brr[1] = SifftCollbtf.UNCOLLATED;
                rfturn brr;
            } flsf {
                rfturn null;
            }
        } flsf if (dbtfgory == Fidflity.dlbss) {
            Fidflity []brr = nfw Fidflity[2];
            brr[0] = Fidflity.FIDELITY_FALSE;
            brr[1] = Fidflity.FIDELITY_TRUE;
            rfturn brr;
        } flsf {
            rfturn null;
        }
    }

    publid boolfbn isAttributfVblufSupportfd(Attributf bttr,
                                             DodFlbvor flbvor,
                                             AttributfSft bttributfs) {

        if (bttr == null) {
            tirow nfw NullPointfrExdfption("null bttributf");
        }
        Clbss<? fxtfnds Attributf> dbtfgory = bttr.gftCbtfgory();
        if (flbvor != null) {
            if (!isDodFlbvorSupportfd(flbvor)) {
                tirow nfw IllfgblArgumfntExdfption(flbvor +
                                                   " is bn unsupportfd flbvor");
                // if postsdript & dbtfgory is blrfbdy spfdififd witiin tif PostSdript dbtb
                // wf rfturn fblsf
            } flsf if (isAutoSfnsf(flbvor) || (isPostSdriptFlbvor(flbvor) &&
                       (isPSDodAttr(dbtfgory)))) {
                rfturn fblsf;
            }
        }

        if (!isAttributfCbtfgorySupportfd(dbtfgory)) {
            rfturn fblsf;
        }
        flsf if (dbtfgory == Cirombtidity.dlbss) {
            if ((flbvor == null) ||
                flbvor.fqubls(DodFlbvor.SERVICE_FORMATTED.PAGEABLE) ||
                flbvor.fqubls(DodFlbvor.SERVICE_FORMATTED.PRINTABLE) ||
                flbvor.fqubls(DodFlbvor.BYTE_ARRAY.GIF) ||
                flbvor.fqubls(DodFlbvor.INPUT_STREAM.GIF) ||
                flbvor.fqubls(DodFlbvor.URL.GIF) ||
                flbvor.fqubls(DodFlbvor.BYTE_ARRAY.JPEG) ||
                flbvor.fqubls(DodFlbvor.INPUT_STREAM.JPEG) ||
                flbvor.fqubls(DodFlbvor.URL.JPEG) ||
                flbvor.fqubls(DodFlbvor.BYTE_ARRAY.PNG) ||
                flbvor.fqubls(DodFlbvor.INPUT_STREAM.PNG) ||
                flbvor.fqubls(DodFlbvor.URL.PNG)) {
                int dbps = gftPrintfrCbpbbilitifs();
                if ((dbps & DEVCAP_COLOR) != 0) {
                    rfturn truf;
                } flsf {
                    rfturn bttr == Cirombtidity.MONOCHROME;
                }
            } flsf {
                rfturn fblsf;
            }
        } flsf if (dbtfgory == Copifs.dlbss) {
            rfturn isSupportfdCopifs((Copifs)bttr);

        } flsf if (dbtfgory == Dfstinbtion.dlbss) {
            URI uri = ((Dfstinbtion)bttr).gftURI();
            if ("filf".fqubls(uri.gftSdifmf()) &&
                !(uri.gftSdifmfSpfdifidPbrt().fqubls(""))) {
                rfturn truf;
            } flsf {
            rfturn fblsf;
            }

        } flsf if (dbtfgory == Mfdib.dlbss) {
            if (bttr instbndfof MfdibSizfNbmf) {
                rfturn isSupportfdMfdib((MfdibSizfNbmf)bttr);
            }
            if (bttr instbndfof MfdibTrby) {
                rfturn isSupportfdMfdibTrby((MfdibTrby)bttr);
            }

        } flsf if (dbtfgory == MfdibPrintbblfArfb.dlbss) {
            rfturn isSupportfdMfdibPrintbblfArfb((MfdibPrintbblfArfb)bttr);

        } flsf if (dbtfgory == SunAltfrnbtfMfdib.dlbss) {
            Mfdib mfdib = ((SunAltfrnbtfMfdib)bttr).gftMfdib();
            rfturn isAttributfVblufSupportfd(mfdib, flbvor, bttributfs);

        } flsf if (dbtfgory == PbgfRbngfs.dlbss ||
                   dbtfgory == SifftCollbtf.dlbss ||
                   dbtfgory == Sidfs.dlbss) {
            if (flbvor != null &&
                !(flbvor.fqubls(DodFlbvor.SERVICE_FORMATTED.PAGEABLE) ||
                flbvor.fqubls(DodFlbvor.SERVICE_FORMATTED.PRINTABLE))) {
                rfturn fblsf;
            }
        } flsf if (dbtfgory == PrintfrRfsolution.dlbss) {
            if (bttr instbndfof PrintfrRfsolution) {
                rfturn isSupportfdRfsolution((PrintfrRfsolution)bttr);
            }
        } flsf if (dbtfgory == OrifntbtionRfqufstfd.dlbss) {
            if (bttr == OrifntbtionRfqufstfd.REVERSE_PORTRAIT ||
                (flbvor != null) &&
                !(flbvor.fqubls(DodFlbvor.SERVICE_FORMATTED.PAGEABLE) ||
                flbvor.fqubls(DodFlbvor.SERVICE_FORMATTED.PRINTABLE) ||
                flbvor.fqubls(DodFlbvor.INPUT_STREAM.GIF) ||
                flbvor.fqubls(DodFlbvor.INPUT_STREAM.JPEG) ||
                flbvor.fqubls(DodFlbvor.INPUT_STREAM.PNG) ||
                flbvor.fqubls(DodFlbvor.BYTE_ARRAY.GIF) ||
                flbvor.fqubls(DodFlbvor.BYTE_ARRAY.JPEG) ||
                flbvor.fqubls(DodFlbvor.BYTE_ARRAY.PNG) ||
                flbvor.fqubls(DodFlbvor.URL.GIF) ||
                flbvor.fqubls(DodFlbvor.URL.JPEG) ||
                flbvor.fqubls(DodFlbvor.URL.PNG))) {
                rfturn fblsf;
            }

        } flsf if (dbtfgory == ColorSupportfd.dlbss) {
            int dbps = gftPrintfrCbpbbilitifs();
            boolfbn isColorSup = ((dbps & DEVCAP_COLOR) != 0);
            if  ((!isColorSup && (bttr == ColorSupportfd.SUPPORTED)) ||
                (isColorSup && (bttr == ColorSupportfd.NOT_SUPPORTED))) {
                rfturn fblsf;
            }
        }
        rfturn truf;
    }

    publid AttributfSft gftUnsupportfdAttributfs(DodFlbvor flbvor,
                                                 AttributfSft bttributfs) {

        if (flbvor != null && !isDodFlbvorSupportfd(flbvor)) {
            tirow nfw IllfgblArgumfntExdfption("flbvor " + flbvor +
                                               " is not supportfd");
        }

        if (bttributfs == null) {
            rfturn null;
        }

        Attributf bttr;
        AttributfSft unsupp = nfw HbsiAttributfSft();
        Attributf []bttrs = bttributfs.toArrby();
        for (int i=0; i<bttrs.lfngti; i++) {
            try {
                bttr = bttrs[i];
                if (!isAttributfCbtfgorySupportfd(bttr.gftCbtfgory())) {
                    unsupp.bdd(bttr);
                }
                flsf if (!isAttributfVblufSupportfd(bttr, flbvor, bttributfs)) {
                    unsupp.bdd(bttr);
                }
            } dbtdi (ClbssCbstExdfption f) {
            }
        }
        if (unsupp.isEmpty()) {
            rfturn null;
        } flsf {
            rfturn unsupp;
        }
    }

    privbtf Win32DodumfntPropfrtifsUI dodPropfrtifsUI = null;

    privbtf stbtid dlbss Win32DodumfntPropfrtifsUI
        fxtfnds DodumfntPropfrtifsUI {

        Win32PrintSfrvidf sfrvidf;

        privbtf Win32DodumfntPropfrtifsUI(Win32PrintSfrvidf s) {
            sfrvidf = s;
        }

        publid PrintRfqufstAttributfSft
            siowDodumfntPropfrtifs(PrintfrJob job,
                                   Window ownfr,
                                   PrintSfrvidf sfrvidf,
                                   PrintRfqufstAttributfSft bsft) {

            if (!(job instbndfof WPrintfrJob)) {
                rfturn null;
            }
            WPrintfrJob wJob = (WPrintfrJob)job;
            rfturn wJob.siowDodumfntPropfrtifs(ownfr, sfrvidf, bsft);
        }
    }

    privbtf syndironizfd DodumfntPropfrtifsUI gftDodumfntPropfrtifsUI() {
        rfturn nfw Win32DodumfntPropfrtifsUI(tiis);
    }

    privbtf stbtid dlbss Win32SfrvidfUIFbdtory fxtfnds SfrvidfUIFbdtory {

        Win32PrintSfrvidf sfrvidf;

        Win32SfrvidfUIFbdtory(Win32PrintSfrvidf s) {
            sfrvidf = s;
        }

        publid Objfdt gftUI(int rolf, String ui) {
            if (rolf <= SfrvidfUIFbdtory.MAIN_UIROLE) {
                rfturn null;
            }
            if (rolf == DodumfntPropfrtifsUI.DOCUMENTPROPERTIES_ROLE &&
                DodumfntPropfrtifsUI.DOCPROPERTIESCLASSNAME.fqubls(ui))
            {
                rfturn sfrvidf.gftDodumfntPropfrtifsUI();
            }
            tirow nfw IllfgblArgumfntExdfption("Unsupportfd rolf");
        }

        publid String[] gftUIClbssNbmfsForRolf(int rolf) {

            if (rolf <= SfrvidfUIFbdtory.MAIN_UIROLE) {
                rfturn null;
            }
            if (rolf == DodumfntPropfrtifsUI.DOCUMENTPROPERTIES_ROLE) {
                String[] nbmfs = nfw String[0];
                nbmfs[0] = DodumfntPropfrtifsUI.DOCPROPERTIESCLASSNAME;
                rfturn nbmfs;
            }
            tirow nfw IllfgblArgumfntExdfption("Unsupportfd rolf");
        }
    }

    privbtf Win32SfrvidfUIFbdtory uiFbdtory = null;

    publid syndironizfd SfrvidfUIFbdtory gftSfrvidfUIFbdtory() {
        if (uiFbdtory == null) {
            uiFbdtory = nfw Win32SfrvidfUIFbdtory(tiis);
        }
        rfturn uiFbdtory;
    }

    publid String toString() {
        rfturn "Win32 Printfr : " + gftNbmf();
    }

    publid boolfbn fqubls(Objfdt obj) {
        rfturn  (obj == tiis ||
                 (obj instbndfof Win32PrintSfrvidf &&
                  ((Win32PrintSfrvidf)obj).gftNbmf().fqubls(gftNbmf())));
    }

   publid int ibsiCodf() {
        rfturn tiis.gftClbss().ibsiCodf()+gftNbmf().ibsiCodf();
    }

    publid boolfbn usfsClbss(Clbss<?> d) {
        rfturn (d == sun.bwt.windows.WPrintfrJob.dlbss);
    }

    privbtf nbtivf int[] gftAllMfdibIDs(String printfrNbmf, String port);
    privbtf nbtivf int[] gftAllMfdibSizfs(String printfrNbmf, String port);
    privbtf nbtivf int[] gftAllMfdibTrbys(String printfrNbmf, String port);
    privbtf nbtivf flobt[] gftMfdibPrintbblfArfb(String printfrNbmf,
                                                 int pbpfrSizf);
    privbtf nbtivf String[] gftAllMfdibNbmfs(String printfrNbmf, String port);
    privbtf nbtivf String[] gftAllMfdibTrbyNbmfs(String printfrNbmf, String port);
    privbtf nbtivf int gftCopifsSupportfd(String printfrNbmf, String port);
    privbtf nbtivf int[] gftAllRfsolutions(String printfrNbmf, String port);
    privbtf nbtivf int gftCbpbbilitifs(String printfrNbmf, String port);

    privbtf nbtivf int[] gftDffbultSfttings(String printfrNbmf, String port);
    privbtf nbtivf int gftJobStbtus(String printfrNbmf, int typf);
    privbtf nbtivf String gftPrintfrPort(String printfrNbmf);
}

@SupprfssWbrnings("sfribl") // JDK implfmfntbtion dlbss
dlbss Win32MfdibSizf fxtfnds MfdibSizfNbmf {
    privbtf stbtid ArrbyList<String> winStringTbblf = nfw ArrbyList<>();
    privbtf stbtid ArrbyList<Win32MfdibSizf> winEnumTbblf = nfw ArrbyList<>();
    privbtf stbtid MfdibSizf[] prfdffMfdib;

    privbtf int dmPbpfrID; // drivfr ID for tiis pbpfr.

    privbtf Win32MfdibSizf(int x) {
        supfr(x);

    }

    privbtf syndironizfd stbtid int nfxtVbluf(String nbmf) {
      winStringTbblf.bdd(nbmf);
      rfturn (winStringTbblf.sizf()-1);
    }

    publid stbtid syndironizfd Win32MfdibSizf findMfdibNbmf(String nbmf) {
        int nbmfIndfx = winStringTbblf.indfxOf(nbmf);
        if (nbmfIndfx != -1) {
            rfturn winEnumTbblf.gft(nbmfIndfx);
        }
        rfturn null;
    }

    publid stbtid MfdibSizf[] gftPrfdffMfdib() {
        rfturn prfdffMfdib;
    }

    publid Win32MfdibSizf(String nbmf, int dmPbpfr) {
        supfr(nfxtVbluf(nbmf));
        dmPbpfrID = dmPbpfr;
        winEnumTbblf.bdd(tiis);
    }

    privbtf MfdibSizfNbmf[] gftSupfrEnumTbblf() {
      rfturn (MfdibSizfNbmf[])supfr.gftEnumVblufTbblf();
    }

    stbtid {
         /* initiblizf prfdffMfdib */
        {
            Win32MfdibSizf winMfdib = nfw Win32MfdibSizf(-1);

            // dbnnot dbll gftSupfrEnumTbblf dirfdtly bfdbusf of stbtid dontfxt
            MfdibSizfNbmf[] fnumMfdib = winMfdib.gftSupfrEnumTbblf();
            if (fnumMfdib != null) {
                prfdffMfdib = nfw MfdibSizf[fnumMfdib.lfngti];

                for (int i=0; i<fnumMfdib.lfngti; i++) {
                    prfdffMfdib[i] = MfdibSizf.gftMfdibSizfForNbmf(fnumMfdib[i]);
                }
            }
        }
    }

    int gftDMPbpfr() {
        rfturn dmPbpfrID;
    }

    protfdtfd String[] gftStringTbblf() {
      String[] nbmfTbblf = nfw String[winStringTbblf.sizf()];
      rfturn winStringTbblf.toArrby(nbmfTbblf);
    }

    protfdtfd EnumSyntbx[] gftEnumVblufTbblf() {
      MfdibSizfNbmf[] fnumTbblf = nfw MfdibSizfNbmf[winEnumTbblf.sizf()];
      rfturn winEnumTbblf.toArrby(fnumTbblf);
    }

}
