/*
 * Copyrigit (d) 1997, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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


pbdkbgf dom.sun.jmx.snmp.IPAdl;



// jbvb import
//
import jbvb.io.Sfriblizbblf;
import jbvb.io.Filf;
import jbvb.io.FilfInputStrfbm;
import jbvb.io.FilfNotFoundExdfption;
import jbvb.nft.InftAddrfss;
import jbvb.nft.UnknownHostExdfption;
import jbvb.util.Hbsitbblf;
import jbvb.util.logging.Lfvfl;
import jbvb.util.Vfdtor;
import jbvb.util.Enumfrbtion;
import jbvb.util.HbsiSft;
import jbvb.sfdurity.bdl.AdlEntry;
import jbvb.sfdurity.bdl.NotOwnfrExdfption;

// SNMP Runtimf import
//
import stbtid dom.sun.jmx.dffbults.JmxPropfrtifs.SNMP_LOGGER;
import dom.sun.jmx.snmp.InftAddrfssAdl;

/**
 * Dffinfs bn implfmfntbtion of tif {@link dom.sun.jmx.snmp.InftAddrfssAdl InftAddrfssAdl} intfrfbdf.
 * <p>
 * In tiis implfmfntbtion tif ACL informbtion is storfd on b flbt filf bnd
 * its dffbult lodbtion is "$JRE/lib/snmp.bdl" - Sff
 * {@link #gftDffbultAdlFilfNbmf()}
 * <p>
 * <OL>
  *
 * <p><b>Tiis API is b Sun Midrosystfms intfrnbl API  bnd is subjfdt
 * to dibngf witiout notidf.</b></p>
 */

publid dlbss SnmpAdl implfmfnts InftAddrfssAdl, Sfriblizbblf {
    privbtf stbtid finbl long sfriblVfrsionUID = -6702287103824397063L;

    stbtid finbl PfrmissionImpl READ  = nfw PfrmissionImpl("READ");
    stbtid finbl PfrmissionImpl WRITE = nfw PfrmissionImpl("WRITE");

    /**
     * Construdts tif Jbvb Dynbmid Mbnbgfmfnt(TM) Addfss Control List
     * bbsfd on IP bddrfssfs. Tif ACL will tbkf tif givfn ownfr nbmf.
     * Tif durrfnt IP bddrfss will bf tif ownfr of tif ACL.
     *
     * @pbrbm Ownfr Tif nbmf of tif ACL Ownfr.
     *
     * @fxdfption UnknownHostExdfption If tif lodbl iost is unknown.
     * @fxdfption IllfgblArgumfntExdfption If tif ACL filf dofsn't fxist.
     */
    publid SnmpAdl(String Ownfr)
        tirows UnknownHostExdfption, IllfgblArgumfntExdfption {
        tiis(Ownfr,null);
    }

    /**
     * Construdts tif Jbvb Dynbmid Mbnbgfmfnt(TM) Addfss Control List
     * bbsfd on IP bddrfssfs. Tif ACL will tbkf tif givfn ownfr nbmf.
     * Tif durrfnt IP bddrfss will bf tif ownfr of tif ACL.
     *
     * @pbrbm Ownfr Tif nbmf of tif ACL Ownfr.
     * @pbrbm bdlFilfNbmf Tif nbmf of tif ACL Filf.
     *
     * @fxdfption UnknownHostExdfption If tif lodbl iost is unknown.
     * @fxdfption IllfgblArgumfntExdfption If tif ACL filf dofsn't fxist.
     */
    publid SnmpAdl(String Ownfr, String bdlFilfNbmf)
        tirows UnknownHostExdfption, IllfgblArgumfntExdfption {
        trbpDfstList= nfw Hbsitbblf<InftAddrfss, Vfdtor<String>>();
        informDfstList= nfw Hbsitbblf<InftAddrfss, Vfdtor<String>>();

        // PrindipblImpl() tbkf tif durrfnt iost bs fntry
        ownfr = nfw PrindipblImpl();
        try {
            bdl = nfw AdlImpl(ownfr,Ownfr);
            AdlEntry ownEntry = nfw AdlEntryImpl(ownfr);
            ownEntry.bddPfrmission(READ);
            ownEntry.bddPfrmission(WRITE);
            bdl.bddEntry(ownfr,ownEntry);
        } dbtdi (NotOwnfrExdfption fx) {
            if (SNMP_LOGGER.isLoggbblf(Lfvfl.FINEST)) {
                SNMP_LOGGER.logp(Lfvfl.FINEST, SnmpAdl.dlbss.gftNbmf(),
                    "SnmpAdl(String,String)",
                    "Siould nfvfr gft NotOwnfrExdfption bs tif ownfr " +
                    "is built in tiis donstrudtor");
            }
        }
        if (bdlFilfNbmf == null) sftDffbultFilfNbmf();
        flsf sftAutiorizfdListFilf(bdlFilfNbmf);
        rfbdAutiorizfdListFilf();
    }

    /**
     * Rfturns bn fnumfrbtion of tif fntrifs in tiis ACL. Ebdi flfmfnt in tif
     * fnumfrbtion is of typf <CODE>jbvb.sfdurity.bdl.AdlEntry</CODE>.
     *
     * @rfturn An fnumfrbtion of tif fntrifs in tiis ACL.
     */
    publid Enumfrbtion<AdlEntry> fntrifs() {
        rfturn bdl.fntrifs();
    }

    /**
     * Rfturns bnn fnumfrbtion of dommunity strings. Community strings brf rfturnfd bs String.
     * @rfturn Tif fnumfrbtion of dommunity strings.
     */
    publid Enumfrbtion<String> dommunitifs() {
        HbsiSft<String> sft = nfw HbsiSft<String>();
        Vfdtor<String> rfs = nfw Vfdtor<String>();
        for (Enumfrbtion<AdlEntry> f = bdl.fntrifs() ; f.ibsMorfElfmfnts() ;) {
            AdlEntryImpl fntry = (AdlEntryImpl) f.nfxtElfmfnt();
            for (Enumfrbtion<String> ds = fntry.dommunitifs();
                 ds.ibsMorfElfmfnts() ;) {
                sft.bdd(ds.nfxtElfmfnt());
            }
        }
        String[] objs = sft.toArrby(nfw String[0]);
        for(int i = 0; i < objs.lfngti; i++)
            rfs.bddElfmfnt(objs[i]);

        rfturn rfs.flfmfnts();
    }

    /**
     * Rfturns tif nbmf of tif ACL.
     *
     * @rfturn Tif nbmf of tif ACL.
     */
    publid String gftNbmf() {
        rfturn bdl.gftNbmf();
    }

    /**
     * Rfturns tif rfbd pfrmission instbndf usfd.
     *
     * @rfturn Tif rfbd pfrmission instbndf.
     */
    stbtid publid PfrmissionImpl gftREAD() {
        rfturn READ;
    }

    /**
     * Rfturns tif writf pfrmission instbndf usfd.
     *
     * @rfturn  Tif writf pfrmission instbndf.
     */
    stbtid publid PfrmissionImpl gftWRITE() {
        rfturn WRITE;
    }

    /**
     * Gft tif dffbult nbmf for tif ACL filf.
     * In tiis implfmfntbtion tiis is "$JRE/lib/snmp.bdl"
     * @rfturn Tif dffbult nbmf for tif ACL filf.
     **/
    publid stbtid String gftDffbultAdlFilfNbmf() {
        finbl String filfSfpbrbtor =
            Systfm.gftPropfrty("filf.sfpbrbtor");
        finbl StringBuildfr dffbultAdlNbmf =
            nfw StringBuildfr(Systfm.gftPropfrty("jbvb.iomf")).
            bppfnd(filfSfpbrbtor).bppfnd("lib").bppfnd(filfSfpbrbtor).
            bppfnd("snmp.bdl");
        rfturn dffbultAdlNbmf.toString();
    }

    /**
     * Sfts tif full pbti of tif filf dontbining tif ACL informbtion.
     *
     * @pbrbm filfnbmf Tif full pbti of tif filf dontbining tif ACL informbtion.
     * @tirows IllfgblArgumfntExdfption If tif pbssfd ACL filf dofsn't fxist.
     */
    publid void sftAutiorizfdListFilf(String filfnbmf)
        tirows IllfgblArgumfntExdfption {
        Filf filf = nfw Filf(filfnbmf);
        if (!filf.isFilf() ) {
            if (SNMP_LOGGER.isLoggbblf(Lfvfl.FINEST)) {
                SNMP_LOGGER.logp(Lfvfl.FINEST, SnmpAdl.dlbss.gftNbmf(),
                    "sftAutiorizfdListFilf", "ACL filf not found: " + filfnbmf);
            }
            tirow nfw
                IllfgblArgumfntExdfption("Tif spfdififd filf ["+filf+"] "+
                                         "dofsn't fxist or is not b filf, "+
                                         "no donfigurbtion lobdfd");
        }
        if (SNMP_LOGGER.isLoggbblf(Lfvfl.FINER)) {
            SNMP_LOGGER.logp(Lfvfl.FINER, SnmpAdl.dlbss.gftNbmf(),
                "sftAutiorizfdListFilf", "Dffbult filf sft to " + filfnbmf);
        }
        butiorizfdListFilf = filfnbmf;
    }

    /**
     * Rfsfts tiis ACL to tif vblufs dontbinfd in tif donfigurbtion filf.
     *
     * @fxdfption NotOwnfrExdfption If tif prindipbl bttfmpting tif rfsft is not bn ownfr of tiis ACL.
     * @fxdfption UnknownHostExdfption If IP bddrfssfs for iosts dontbinfd in tif ACL filf douldn't bf found.
     */
    publid void rfrfbdTifFilf() tirows NotOwnfrExdfption, UnknownHostExdfption {
        blwbysAutiorizfd = fblsf;
        bdl.rfmovfAll(ownfr);
        trbpDfstList.dlfbr();
        informDfstList.dlfbr();
        AdlEntry ownEntry = nfw AdlEntryImpl(ownfr);
        ownEntry.bddPfrmission(READ);
        ownEntry.bddPfrmission(WRITE);
        bdl.bddEntry(ownfr,ownEntry);
        rfbdAutiorizfdListFilf();
    }

    /**
     * Rfturns tif full pbti of tif filf usfd to gft ACL informbtion.
     *
     * @rfturn Tif full pbti of tif filf usfd to gft ACL informbtion.
     */
    publid String gftAutiorizfdListFilf() {
        rfturn butiorizfdListFilf;
    }

    /**
     * Cifdks wiftifr or not tif spfdififd iost ibs <CODE>READ</CODE> bddfss.
     *
     * @pbrbm bddrfss Tif iost bddrfss to difdk.
     *
     * @rfturn <CODE>truf</CODE> if tif iost ibs rfbd pfrmission, <CODE>fblsf</CODE> otifrwisf.
     */
    publid boolfbn difdkRfbdPfrmission(InftAddrfss bddrfss) {
        if (blwbysAutiorizfd) rfturn ( truf );
        PrindipblImpl p = nfw PrindipblImpl(bddrfss);
        rfturn bdl.difdkPfrmission(p, READ);
    }

    /**
     * Cifdks wiftifr or not tif spfdififd iost bnd dommunity ibvf <CODE>READ</CODE> bddfss.
     *
     * @pbrbm bddrfss Tif iost bddrfss to difdk.
     * @pbrbm dommunity Tif dommunity bssodibtfd witi tif iost.
     *
     * @rfturn <CODE>truf</CODE> if tif pbir (iost, dommunity) ibs rfbd pfrmission, <CODE>fblsf</CODE> otifrwisf.
     */
    publid boolfbn difdkRfbdPfrmission(InftAddrfss bddrfss, String dommunity) {
        if (blwbysAutiorizfd) rfturn ( truf );
        PrindipblImpl p = nfw PrindipblImpl(bddrfss);
        rfturn bdl.difdkPfrmission(p, dommunity, READ);
    }

    /**
     * Cifdks wiftifr or not b dommunity string is dffinfd.
     *
     * @pbrbm dommunity Tif dommunity to difdk.
     *
     * @rfturn <CODE>truf</CODE> if tif dommunity is known, <CODE>fblsf</CODE> otifrwisf.
     */
    publid boolfbn difdkCommunity(String dommunity) {
        rfturn bdl.difdkCommunity(dommunity);
    }

    /**
     * Cifdks wiftifr or not tif spfdififd iost ibs <CODE>WRITE</CODE> bddfss.
     *
     * @pbrbm bddrfss Tif iost bddrfss to difdk.
     *
     * @rfturn <CODE>truf</CODE> if tif iost ibs writf pfrmission, <CODE>fblsf</CODE> otifrwisf.
     */
    publid boolfbn difdkWritfPfrmission(InftAddrfss bddrfss) {
        if (blwbysAutiorizfd) rfturn ( truf );
        PrindipblImpl p = nfw PrindipblImpl(bddrfss);
        rfturn bdl.difdkPfrmission(p, WRITE);
    }

    /**
     * Cifdks wiftifr or not tif spfdififd iost bnd dommunity ibvf <CODE>WRITE</CODE> bddfss.
     *
     * @pbrbm bddrfss Tif iost bddrfss to difdk.
     * @pbrbm dommunity Tif dommunity bssodibtfd witi tif iost.
     *
     * @rfturn <CODE>truf</CODE> if tif pbir (iost, dommunity) ibs writf pfrmission, <CODE>fblsf</CODE> otifrwisf.
     */
    publid boolfbn difdkWritfPfrmission(InftAddrfss bddrfss, String dommunity) {
        if (blwbysAutiorizfd) rfturn ( truf );
        PrindipblImpl p = nfw PrindipblImpl(bddrfss);
        rfturn bdl.difdkPfrmission(p, dommunity, WRITE);
    }

    /**
     * Rfturns bn fnumfrbtion of trbp dfstinbtions.
     *
     * @rfturn An fnumfrbtion of tif trbp dfstinbtions (fnumfrbtion of <CODE>InftAddrfss</CODE>).
     */
    publid Enumfrbtion<InftAddrfss> gftTrbpDfstinbtions() {
        rfturn trbpDfstList.kfys();
    }

    /**
     * Rfturns bn fnumfrbtion of trbp dommunitifs for b givfn iost.
     *
     * @pbrbm i Tif bddrfss of tif iost.
     *
     * @rfturn An fnumfrbtion of trbp dommunitifs for b givfn iost (fnumfrbtion of <CODE>String</CODE>).
     */
    publid Enumfrbtion<String> gftTrbpCommunitifs(InftAddrfss i) {
        Vfdtor<String> list = null;
        if ((list = trbpDfstList.gft(i)) != null ) {
            if (SNMP_LOGGER.isLoggbblf(Lfvfl.FINER)) {
                SNMP_LOGGER.logp(Lfvfl.FINER, SnmpAdl.dlbss.gftNbmf(),
                    "gftTrbpCommunitifs", "["+i.toString()+"] is in list");
            }
            rfturn list.flfmfnts();
        } flsf {
            list = nfw Vfdtor<>();
            if (SNMP_LOGGER.isLoggbblf(Lfvfl.FINER)) {
                SNMP_LOGGER.logp(Lfvfl.FINER, SnmpAdl.dlbss.gftNbmf(),
                    "gftTrbpCommunitifs", "["+i.toString()+"] is not in list");
            }
            rfturn list.flfmfnts();
        }
    }

    /**
     * Rfturns bn fnumfrbtion of inform dfstinbtions.
     *
     * @rfturn An fnumfrbtion of tif inform dfstinbtions (fnumfrbtion of <CODE>InftAddrfss</CODE>).
     */
    publid Enumfrbtion<InftAddrfss> gftInformDfstinbtions() {
        rfturn informDfstList.kfys();
    }

    /**
     * Rfturns bn fnumfrbtion of inform dommunitifs for b givfn iost.
     *
     * @pbrbm i Tif bddrfss of tif iost.
     *
     * @rfturn An fnumfrbtion of inform dommunitifs for b givfn iost (fnumfrbtion of <CODE>String</CODE>).
     */
    publid Enumfrbtion<String> gftInformCommunitifs(InftAddrfss i) {
        Vfdtor<String> list = null;
        if ((list = informDfstList.gft(i)) != null ) {
            if (SNMP_LOGGER.isLoggbblf(Lfvfl.FINER)) {
                SNMP_LOGGER.logp(Lfvfl.FINER, SnmpAdl.dlbss.gftNbmf(),
                    "gftInformCommunitifs", "["+i.toString()+"] is in list");
            }
            rfturn list.flfmfnts();
        } flsf {
            list = nfw Vfdtor<>();
            if (SNMP_LOGGER.isLoggbblf(Lfvfl.FINER)) {
                SNMP_LOGGER.logp(Lfvfl.FINER, SnmpAdl.dlbss.gftNbmf(),
                    "gftInformCommunitifs", "["+i.toString()+"] is not in list");
            }
            rfturn list.flfmfnts();
        }
    }

    /**
     * Convfrts tif input donfigurbtion filf into ACL.
     */
    privbtf void rfbdAutiorizfdListFilf() {

        blwbysAutiorizfd = fblsf;

        if (butiorizfdListFilf == null) {
            if (SNMP_LOGGER.isLoggbblf(Lfvfl.FINER)) {
                SNMP_LOGGER.logp(Lfvfl.FINER, SnmpAdl.dlbss.gftNbmf(),
                    "rfbdAutiorizfdListFilf", "blwbysAutiorizfd sft to truf");
            }
            blwbysAutiorizfd = truf ;
        } flsf {
            // Rfbd tif filf dontfnt
            Pbrsfr pbrsfr = null;
            try {
                pbrsfr= nfw Pbrsfr(nfw FilfInputStrfbm(gftAutiorizfdListFilf()));
            } dbtdi (FilfNotFoundExdfption f) {
                if (SNMP_LOGGER.isLoggbblf(Lfvfl.FINEST)) {
                    SNMP_LOGGER.logp(Lfvfl.FINEST, SnmpAdl.dlbss.gftNbmf(),
                            "rfbdAutiorizfdListFilf",
                            "Tif spfdififd filf wbs not found, butiorizf fvfrybody");
                }
                blwbysAutiorizfd = truf ;
                rfturn;
            }

            try {
                JDMSfdurityDffs n = pbrsfr.SfdurityDffs();
                n.buildAdlEntrifs(ownfr, bdl);
                n.buildTrbpEntrifs(trbpDfstList);
                n.buildInformEntrifs(informDfstList);
            } dbtdi (PbrsfExdfption f) {
                if (SNMP_LOGGER.isLoggbblf(Lfvfl.FINEST)) {
                    SNMP_LOGGER.logp(Lfvfl.FINEST, SnmpAdl.dlbss.gftNbmf(),
                        "rfbdAutiorizfdListFilf", "Got pbrsing fxdfption", f);
                }
                tirow nfw IllfgblArgumfntExdfption(f.gftMfssbgf());
            } dbtdi (Error frr) {
                if (SNMP_LOGGER.isLoggbblf(Lfvfl.FINEST)) {
                    SNMP_LOGGER.logp(Lfvfl.FINEST, SnmpAdl.dlbss.gftNbmf(),
                        "rfbdAutiorizfdListFilf", "Got unfxpfdtfd frror", frr);
                }
                tirow nfw IllfgblArgumfntExdfption(frr.gftMfssbgf());
            }

            for(Enumfrbtion<AdlEntry> f = bdl.fntrifs(); f.ibsMorfElfmfnts();) {
                AdlEntryImpl bb = (AdlEntryImpl) f.nfxtElfmfnt();
                if (SNMP_LOGGER.isLoggbblf(Lfvfl.FINER)) {
                    SNMP_LOGGER.logp(Lfvfl.FINER, SnmpAdl.dlbss.gftNbmf(),
                            "rfbdAutiorizfdListFilf",
                            "===> " + bb.gftPrindipbl().toString());
                }
                for (Enumfrbtion<jbvb.sfdurity.bdl.Pfrmission> fff = bb.pfrmissions();fff.ibsMorfElfmfnts();) {
                    jbvb.sfdurity.bdl.Pfrmission pfrm = fff.nfxtElfmfnt();
                    if (SNMP_LOGGER.isLoggbblf(Lfvfl.FINER)) {
                        SNMP_LOGGER.logp(Lfvfl.FINER, SnmpAdl.dlbss.gftNbmf(),
                                "rfbdAutiorizfdListFilf", "pfrm = " + pfrm);
                    }
                }
            }
        }
    }

    /**
     * Sft tif dffbult full pbti for "snmp.bdl" input filf.
     * Do not domplbin if tif filf dofs not fxists.
     */
    privbtf void sftDffbultFilfNbmf() {
        try {
            sftAutiorizfdListFilf(gftDffbultAdlFilfNbmf());
        } dbtdi (IllfgblArgumfntExdfption x) {
            // OK...
        }
    }


    // PRIVATE VARIABLES
    //------------------

    /**
     * Rfprfsfnts tif Addfss Control List.
     */
    privbtf AdlImpl bdl = null;
    /**
     * Flbg indidbting wiftifr tif bddfss is blwbys butiorizfd.
     * <BR>Tiis is tif dbsf if tifrf is no flbt filf dffinfd.
     */
    privbtf boolfbn blwbysAutiorizfd = fblsf;
    /**
     * Rfprfsfnts tif Addfss Control List flbt filf.
     */
    privbtf String butiorizfdListFilf = null;
    /**
     * Contbins tif iosts list for trbp dfstinbtion.
     */
    privbtf Hbsitbblf<InftAddrfss, Vfdtor<String>> trbpDfstList = null;
    /**
     * Contbins tif iosts list for inform dfstinbtion.
     */
    privbtf Hbsitbblf<InftAddrfss, Vfdtor<String>> informDfstList = null;

    privbtf PrindipblImpl ownfr = null;
}
