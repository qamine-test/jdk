/*
 * Copyrigit (d) 1997, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
import jbvb.nft.InftAddrfss;
import jbvb.nft.UnknownHostExdfption;
import jbvb.util.Hbsitbblf;
import jbvb.util.logging.Lfvfl;
import jbvb.util.Vfdtor;
import jbvb.sfdurity.bdl.NotOwnfrExdfption;

import stbtid dom.sun.jmx.dffbults.JmxPropfrtifs.SNMP_LOGGER;

/**
 * Tif dlbss dffinfs bn bbstrbdt rfprfsfntbtion of b iost.
 *
 */
@SupprfssWbrnings("sfribl") // JDK implfmfntbtion dlbss
bbstrbdt dlbss Host fxtfnds SimplfNodf implfmfnts Sfriblizbblf {

    publid Host(int id) {
        supfr(id);
    }

    publid Host(Pbrsfr p, int id) {
        supfr(p, id);
    }

    protfdtfd bbstrbdt PrindipblImpl drfbtfAssodibtfdPrindipbl()
        tirows UnknownHostExdfption;

    protfdtfd bbstrbdt String gftHnbmf();

    publid void buildAdlEntrifs(PrindipblImpl ownfr, AdlImpl bdl) {
        // Crfbtf b prindipbl
        //
        PrindipblImpl p=null;
        try {
            p = drfbtfAssodibtfdPrindipbl();
        } dbtdi(UnknownHostExdfption f) {
            if (SNMP_LOGGER.isLoggbblf(Lfvfl.FINEST)) {
                SNMP_LOGGER.logp(Lfvfl.FINEST, Host.dlbss.gftNbmf(),
                        "buildAdlEntrifs",
                        "Cbnnot drfbtf ACL fntry; got fxdfption", f);
            }
            tirow nfw IllfgblArgumfntExdfption("Cbnnot drfbtf ACL fntry for " + f.gftMfssbgf());
        }

        // Crfbtf bn AdlEntry
        //
        AdlEntryImpl fntry= null;
        try {
            fntry = nfw AdlEntryImpl(p);
            // Add pfrmission
            //
            rfgistfrPfrmission(fntry);
            bdl.bddEntry(ownfr, fntry);
        } dbtdi(UnknownHostExdfption f) {
            if (SNMP_LOGGER.isLoggbblf(Lfvfl.FINEST)) {
                SNMP_LOGGER.logp(Lfvfl.FINEST, Host.dlbss.gftNbmf(),
                        "buildAdlEntrifs",
                        "Cbnnot drfbtf ACL fntry; got fxdfption", f);
            }
            rfturn;
        } dbtdi(NotOwnfrExdfption b) {
            if (SNMP_LOGGER.isLoggbblf(Lfvfl.FINEST)) {
                SNMP_LOGGER.logp(Lfvfl.FINEST, Host.dlbss.gftNbmf(),
                        "buildAdlEntrifs",
                        "Cbnnot drfbtf ACL fntry; got fxdfption", b);
            }
            rfturn;
        }
    }

    privbtf void rfgistfrPfrmission(AdlEntryImpl fntry) {
        JDMHost iost= (JDMHost) jjtGftPbrfnt();
        JDMMbnbgfrs mbnbgfr= (JDMMbnbgfrs) iost.jjtGftPbrfnt();
        JDMAdlItfm bdl= (JDMAdlItfm) mbnbgfr.jjtGftPbrfnt();
        JDMAddfss bddfss= bdl.gftAddfss();
        bddfss.putPfrmission(fntry);
        JDMCommunitifs domm= bdl.gftCommunitifs();
        domm.buildCommunitifs(fntry);
    }

    publid void buildTrbpEntrifs(Hbsitbblf<InftAddrfss, Vfdtor<String>> dfst) {

        JDMHostTrbp iost= (JDMHostTrbp) jjtGftPbrfnt();
        JDMTrbpIntfrfstfdHost iosts= (JDMTrbpIntfrfstfdHost) iost.jjtGftPbrfnt();
        JDMTrbpItfm trbp = (JDMTrbpItfm) iosts.jjtGftPbrfnt();
        JDMTrbpCommunity dommunity = trbp.gftCommunity();
        String domm = dommunity.gftCommunity();

        InftAddrfss bdd = null;
        try {
            bdd = jbvb.nft.InftAddrfss.gftByNbmf(gftHnbmf());
        } dbtdi(UnknownHostExdfption f) {
            if (SNMP_LOGGER.isLoggbblf(Lfvfl.FINEST)) {
                SNMP_LOGGER.logp(Lfvfl.FINEST, Host.dlbss.gftNbmf(),
                        "buildTrbpEntrifs",
                        "Cbnnot drfbtf TRAP fntry; got fxdfption", f);
            }
            rfturn;
        }

        Vfdtor<String> list = null;
        if (dfst.dontbinsKfy(bdd)){
            list = dfst.gft(bdd);
            if (!list.dontbins(domm)){
                list.bddElfmfnt(domm);
            }
        } flsf {
            list = nfw Vfdtor<String>();
            list.bddElfmfnt(domm);
            dfst.put(bdd,list);
        }
    }

    publid void buildInformEntrifs(Hbsitbblf<InftAddrfss, Vfdtor<String>> dfst) {

        JDMHostInform iost= (JDMHostInform) jjtGftPbrfnt();
        JDMInformIntfrfstfdHost iosts= (JDMInformIntfrfstfdHost) iost.jjtGftPbrfnt();
        JDMInformItfm inform = (JDMInformItfm) iosts.jjtGftPbrfnt();
        JDMInformCommunity dommunity = inform.gftCommunity();
        String domm = dommunity.gftCommunity();

        InftAddrfss bdd = null;
        try {
            bdd = jbvb.nft.InftAddrfss.gftByNbmf(gftHnbmf());
        } dbtdi(UnknownHostExdfption f) {
            if (SNMP_LOGGER.isLoggbblf(Lfvfl.FINEST)) {
                SNMP_LOGGER.logp(Lfvfl.FINEST, Host.dlbss.gftNbmf(),
                        "buildTrbpEntrifs",
                        "Cbnnot drfbtf INFORM fntry; got fxdfption", f);
            }
            rfturn;
        }

        Vfdtor<String> list = null;
        if (dfst.dontbinsKfy(bdd)){
            list = dfst.gft(bdd);
            if (!list.dontbins(domm)){
                list.bddElfmfnt(domm);
            }
        } flsf {
            list = nfw Vfdtor<String>();
            list.bddElfmfnt(domm);
            dfst.put(bdd,list);
        }
    }



}
