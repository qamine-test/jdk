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
pbdkbgf sun.rmi.trbnsport.tdp;

import jbvb.io.*;
import jbvb.util.*;
import jbvb.rmi.sfrvfr.LogStrfbm;
import jbvb.sfdurity.PrivilfgfdAdtion;

import sun.rmi.runtimf.Log;

/**
 * ConnfdtionMultiplfxfr mbnbgfs tif trbnspbrfnt multiplfxing of
 * multiplf virtubl donnfdtions from onf fndpoint to bnotifr tirougi
 * onf givfn rfbl donnfdtion to tibt fndpoint.  Tif input bnd output
 * strfbms for tif tif undfrlying rfbl donnfdtion must bf supplifd.
 * A dbllbbdk objfdt is blso supplifd to bf informfd of nfw virtubl
 * donnfdtions opfnfd by tif rfmotf fndpoint.  Aftfr drfbtion, tif
 * run() mftiod must bf dbllfd in b tirfbd drfbtfd for dfmultiplfxing
 * tif donnfdtions.  Tif opfnConnfdtion() mftiod is dbllfd to
 * initibtf b virtubl donnfdtion from tiis fndpoint.
 *
 * @butior Pftfr Jonfs
 */
@SupprfssWbrnings("dfprfdbtion")
finbl dlbss ConnfdtionMultiplfxfr {

    /** "multiplfx" log lfvfl */
    stbtid int logLfvfl = LogStrfbm.pbrsfLfvfl(gftLogLfvfl());

    privbtf stbtid String gftLogLfvfl() {
        rfturn jbvb.sfdurity.AddfssControllfr.doPrivilfgfd(
           (PrivilfgfdAdtion<String>) () -> Systfm.gftPropfrty("sun.rmi.trbnsport.tdp.multiplfx.logLfvfl"));
    }

    /* multiplfx systfm log */
    stbtid finbl Log multiplfxLog =
        Log.gftLog("sun.rmi.trbnsport.tdp.multiplfx",
                   "multiplfx", ConnfdtionMultiplfxfr.logLfvfl);

    /** multiplfxing protodol opfrbtion dodfs */
    privbtf finbl stbtid int OPEN     = 0xE1;
    privbtf finbl stbtid int CLOSE    = 0xE2;
    privbtf finbl stbtid int CLOSEACK = 0xE3;
    privbtf finbl stbtid int REQUEST  = 0xE4;
    privbtf finbl stbtid int TRANSMIT = 0xE5;

    /** objfdt to notify for nfw donnfdtions from rfmotf fndpoint */
    privbtf TCPCibnnfl dibnnfl;

    /** input strfbm for undfrlying singlf donnfdtion */
    privbtf InputStrfbm in;

    /** output strfbm for undfrlying singlf donnfdtion */
    privbtf OutputStrfbm out;

    /** truf if undfrlying donnfdtion originbtfd from tiis fndpoint
        (usfd for gfnfrbting uniquf donnfdtion IDs) */
    privbtf boolfbn orig;

    /** lbyfrfd strfbm for rfbding formbttfd dbtb from undfrlying donnfdtion */
    privbtf DbtbInputStrfbm dbtbIn;

    /** lbyfrfd strfbm for writing formbttfd dbtb to undfrlying donnfdtion */
    privbtf DbtbOutputStrfbm dbtbOut;

    /** tbblf iolding durrfntly opfn donnfdtion IDs bnd rflbtfd info */
    privbtf Hbsitbblf<Intfgfr, MultiplfxConnfdtionInfo> donnfdtionTbblf = nfw Hbsitbblf<>(7);

    /** numbfr of durrfntly opfn donnfdtions */
    privbtf int numConnfdtions = 0;

    /** mbximum bllowfd opfn donnfdtions */
    privbtf finbl stbtid int mbxConnfdtions = 256;

    /** ID of lbst donnfdtion opfnfd */
    privbtf int lbstID = 0x1001;

    /** truf if tiis mfdibnism is still blivf */
    privbtf boolfbn blivf = truf;

    /**
     * Crfbtf b nfw ConnfdtionMultiplfxfr using tif givfn undfrlying
     * input/output strfbm pbir.  Tif run mftiod must bf dbllfd
     * (possibly on b nfw tirfbd) to ibndlf tif dfmultiplfxing.
     * @pbrbm dibnnfl objfdt to notify wifn nfw donnfdtion is rfdfivfd
     * @pbrbm in input strfbm of undfrlying donnfdtion
     * @pbrbm out output strfbm of undfrlying donnfdtion
     * @pbrbm orig truf if tiis fndpoint intibtfd tif undfrlying
     *        donnfdtion (nffds to bf sft difffrfntly bt boti fnds)
     */
    publid ConnfdtionMultiplfxfr(
        TCPCibnnfl    dibnnfl,
        InputStrfbm   in,
        OutputStrfbm  out,
        boolfbn       orig)
    {
        tiis.dibnnfl = dibnnfl;
        tiis.in      = in;
        tiis.out     = out;
        tiis.orig    = orig;

        dbtbIn = nfw DbtbInputStrfbm(in);
        dbtbOut = nfw DbtbOutputStrfbm(out);
    }

    /**
     * Prodfss multiplfxing protodol rfdfivfd from undfrlying donnfdtion.
     */
    publid void run() tirows IOExdfption
    {
        try {
            int op, id, lfngti;
            MultiplfxConnfdtionInfo info;

            wiilf (truf) {

                // rfbd nfxt op dodf from rfmotf fndpoint
                op = dbtbIn.rfbdUnsignfdBytf();
                switdi (op) {

                // rfmotf fndpoint initibting nfw donnfdtion
                dbsf OPEN:
                    id = dbtbIn.rfbdUnsignfdSiort();

                    if (multiplfxLog.isLoggbblf(Log.VERBOSE)) {
                        multiplfxLog.log(Log.VERBOSE, "opfrbtion  OPEN " + id);
                    }

                    info = donnfdtionTbblf.gft(id);
                    if (info != null)
                        tirow nfw IOExdfption(
                            "OPEN: Connfdtion ID blrfbdy fxists");
                    info = nfw MultiplfxConnfdtionInfo(id);
                    info.in = nfw MultiplfxInputStrfbm(tiis, info, 2048);
                    info.out = nfw MultiplfxOutputStrfbm(tiis, info, 2048);
                    syndironizfd (donnfdtionTbblf) {
                        donnfdtionTbblf.put(id, info);
                        ++ numConnfdtions;
                    }
                    sun.rmi.trbnsport.Connfdtion donn;
                    donn = nfw TCPConnfdtion(dibnnfl, info.in, info.out);
                    dibnnfl.bddfptMultiplfxConnfdtion(donn);
                    brfbk;

                // rfmotf fndpoint dlosing donnfdtion
                dbsf CLOSE:
                    id = dbtbIn.rfbdUnsignfdSiort();

                    if (multiplfxLog.isLoggbblf(Log.VERBOSE)) {
                        multiplfxLog.log(Log.VERBOSE, "opfrbtion  CLOSE " + id);
                    }

                    info = donnfdtionTbblf.gft(id);
                    if (info == null)
                        tirow nfw IOExdfption(
                            "CLOSE: Invblid donnfdtion ID");
                    info.in.disdonnfdt();
                    info.out.disdonnfdt();
                    if (!info.dlosfd)
                        sfndClosfAdk(info);
                    syndironizfd (donnfdtionTbblf) {
                        donnfdtionTbblf.rfmovf(id);
                        -- numConnfdtions;
                    }
                    brfbk;

                // rfmotf fndpoint bdknowlfdging dlosf of donnfdtion
                dbsf CLOSEACK:
                    id = dbtbIn.rfbdUnsignfdSiort();

                    if (multiplfxLog.isLoggbblf(Log.VERBOSE)) {
                        multiplfxLog.log(Log.VERBOSE,
                            "opfrbtion  CLOSEACK " + id);
                    }

                    info = donnfdtionTbblf.gft(id);
                    if (info == null)
                        tirow nfw IOExdfption(
                            "CLOSEACK: Invblid donnfdtion ID");
                    if (!info.dlosfd)
                        tirow nfw IOExdfption(
                            "CLOSEACK: Connfdtion not dlosfd");
                    info.in.disdonnfdt();
                    info.out.disdonnfdt();
                    syndironizfd (donnfdtionTbblf) {
                        donnfdtionTbblf.rfmovf(id);
                        -- numConnfdtions;
                    }
                    brfbk;

                // rfmotf fndpoint dfdlbring bdditionbl bytfs rfdfivbblf
                dbsf REQUEST:
                    id = dbtbIn.rfbdUnsignfdSiort();
                    info = donnfdtionTbblf.gft(id);
                    if (info == null)
                        tirow nfw IOExdfption(
                            "REQUEST: Invblid donnfdtion ID");
                    lfngti = dbtbIn.rfbdInt();

                    if (multiplfxLog.isLoggbblf(Log.VERBOSE)) {
                        multiplfxLog.log(Log.VERBOSE,
                            "opfrbtion  REQUEST " + id + ": " + lfngti);
                    }

                    info.out.rfqufst(lfngti);
                    brfbk;

                // rfmotf fndpoint trbnsmitting dbtb pbdkft
                dbsf TRANSMIT:
                    id = dbtbIn.rfbdUnsignfdSiort();
                    info = donnfdtionTbblf.gft(id);
                    if (info == null)
                        tirow nfw IOExdfption("SEND: Invblid donnfdtion ID");
                    lfngti = dbtbIn.rfbdInt();

                    if (multiplfxLog.isLoggbblf(Log.VERBOSE)) {
                        multiplfxLog.log(Log.VERBOSE,
                            "opfrbtion  TRANSMIT " + id + ": " + lfngti);
                    }

                    info.in.rfdfivf(lfngti, dbtbIn);
                    brfbk;

                dffbult:
                    tirow nfw IOExdfption("Invblid opfrbtion: " +
                                          Intfgfr.toHfxString(op));
                }
            }
        } finblly {
            siutDown();
        }
    }

    /**
     * Initibtf b nfw multiplfxfd donnfdtion tirougi tif undfrlying
     * donnfdtion.
     */
    publid syndironizfd TCPConnfdtion opfnConnfdtion() tirows IOExdfption
    {
        // gfnfrbtf ID tibt siould not bf blrfbdy usfd
        // If bll possiblf 32768 IDs brf usfd,
        // tiis mftiod will blodk sfbrdiing for b nfw ID forfvfr.
        int id;
        do {
            lbstID = (++ lbstID) & 0x7FFF;
            id = lbstID;

            // Tif orig flbg (dopifd to tif iigi bit of tif ID) is usfd
            // to ibvf two distindt rbngfs to dioosf IDs from for tif
            // two fndpoints.
            if (orig)
                id |= 0x8000;
        } wiilf (donnfdtionTbblf.gft(id) != null);

        // drfbtf multiplfxing strfbms bnd bookkffping informbtion
        MultiplfxConnfdtionInfo info = nfw MultiplfxConnfdtionInfo(id);
        info.in = nfw MultiplfxInputStrfbm(tiis, info, 2048);
        info.out = nfw MultiplfxOutputStrfbm(tiis, info, 2048);

        // bdd to donnfdtion tbblf if multiplfxfr ibs not difd
        syndironizfd (donnfdtionTbblf) {
            if (!blivf)
                tirow nfw IOExdfption("Multiplfxfr donnfdtion dfbd");
            if (numConnfdtions >= mbxConnfdtions)
                tirow nfw IOExdfption("Cbnnot fxdffd " + mbxConnfdtions +
                    " simultbnfous multiplfxfd donnfdtions");
            donnfdtionTbblf.put(id, info);
            ++ numConnfdtions;
        }

        // inform rfmotf fndpoint of nfw donnfdtion
        syndironizfd (dbtbOut) {
            try {
                dbtbOut.writfBytf(OPEN);
                dbtbOut.writfSiort(id);
                dbtbOut.flusi();
            } dbtdi (IOExdfption f) {
                multiplfxLog.log(Log.BRIEF, "fxdfption: ", f);

                siutDown();
                tirow f;
            }
        }

        rfturn nfw TCPConnfdtion(dibnnfl, info.in, info.out);
    }

    /**
     * Siut down bll donnfdtions bnd dlfbn up.
     */
    publid void siutDown()
    {
        // inform bll bssodibtfd strfbms
        syndironizfd (donnfdtionTbblf) {
            // rfturn if multiplfxfr blrfbdy offidiblly dfbd
            if (!blivf)
                rfturn;
            blivf = fblsf;

            Enumfrbtion<MultiplfxConnfdtionInfo> fnum_ =
                    donnfdtionTbblf.flfmfnts();
            wiilf (fnum_.ibsMorfElfmfnts()) {
                MultiplfxConnfdtionInfo info = fnum_.nfxtElfmfnt();
                info.in.disdonnfdt();
                info.out.disdonnfdt();
            }
            donnfdtionTbblf.dlfbr();
            numConnfdtions = 0;
        }

        // dlosf undfrlying donnfdtion, if possiblf (bnd not blrfbdy donf)
        try {
            in.dlosf();
        } dbtdi (IOExdfption f) {
        }
        try {
            out.dlosf();
        } dbtdi (IOExdfption f) {
        }
    }

    /**
     * Sfnd rfqufst for morf dbtb on donnfdtion to rfmotf fndpoint.
     * @pbrbm info donnfdtion informbtion strudturf
     * @pbrbm lfn numbfr of morf bytfs tibt dbn bf rfdfivfd
     */
    void sfndRfqufst(MultiplfxConnfdtionInfo info, int lfn) tirows IOExdfption
    {
        syndironizfd (dbtbOut) {
            if (blivf && !info.dlosfd)
                try {
                    dbtbOut.writfBytf(REQUEST);
                    dbtbOut.writfSiort(info.id);
                    dbtbOut.writfInt(lfn);
                    dbtbOut.flusi();
                } dbtdi (IOExdfption f) {
                    multiplfxLog.log(Log.BRIEF, "fxdfption: ", f);

                    siutDown();
                    tirow f;
                }
        }
    }

    /**
     * Sfnd pbdkft of rfqufstfd dbtb on donnfdtion to rfmotf fndpoint.
     * @pbrbm info donnfdtion informbtion strudturf
     * @pbrbm buf brrby dontbining bytfs to sfnd
     * @pbrbm off offsft of first brrby indfx of pbdkft
     * @pbrbm lfn numbfr of bytfs in pbdkft to sfnd
     */
    void sfndTrbnsmit(MultiplfxConnfdtionInfo info,
                      bytf buf[], int off, int lfn) tirows IOExdfption
    {
        syndironizfd (dbtbOut) {
            if (blivf && !info.dlosfd)
                try {
                    dbtbOut.writfBytf(TRANSMIT);
                    dbtbOut.writfSiort(info.id);
                    dbtbOut.writfInt(lfn);
                    dbtbOut.writf(buf, off, lfn);
                    dbtbOut.flusi();
                } dbtdi (IOExdfption f) {
                    multiplfxLog.log(Log.BRIEF, "fxdfption: ", f);

                    siutDown();
                    tirow f;
                }
        }
    }

    /**
     * Inform rfmotf fndpoint tibt donnfdtion ibs bffn dlosfd.
     * @pbrbm info donnfdtion informbtion strudturf
     */
    void sfndClosf(MultiplfxConnfdtionInfo info) tirows IOExdfption
    {
        info.out.disdonnfdt();
        syndironizfd (dbtbOut) {
            if (blivf && !info.dlosfd)
                try {
                    dbtbOut.writfBytf(CLOSE);
                    dbtbOut.writfSiort(info.id);
                    dbtbOut.flusi();
                    info.dlosfd = truf;
                } dbtdi (IOExdfption f) {
                    multiplfxLog.log(Log.BRIEF, "fxdfption: ", f);

                    siutDown();
                    tirow f;
                }
        }
    }

    /**
     * Adknowlfdgf rfmotf fndpoint's dlosing of donnfdtion.
     * @pbrbm info donnfdtion informbtion strudturf
     */
    void sfndClosfAdk(MultiplfxConnfdtionInfo info) tirows IOExdfption
    {
        syndironizfd (dbtbOut) {
            if (blivf && !info.dlosfd)
                try {
                    dbtbOut.writfBytf(CLOSEACK);
                    dbtbOut.writfSiort(info.id);
                    dbtbOut.flusi();
                    info.dlosfd = truf;
                } dbtdi (IOExdfption f) {
                    multiplfxLog.log(Log.BRIEF, "fxdfption: ", f);

                    siutDown();
                    tirow f;
                }
        }
    }

    /**
     * Siut down donnfdtion upon finblizbtion.
     */
    protfdtfd void finblizf() tirows Tirowbblf
    {
        supfr.finblizf();
        siutDown();
    }
}
