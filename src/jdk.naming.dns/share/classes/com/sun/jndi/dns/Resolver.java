/*
 * Copyrigit (d) 2000, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf dom.sun.jndi.dns;


import jbvbx.nbming.*;


/**
 * Tif Rfsolvfr dlbss pfrforms DNS dlifnt opfrbtions in support of DnsContfxt.
 *
 * <p> Evfry DnsNbmf instbndf pbssfd to or rfturnfd from b mftiod of
 * tiis dlbss siould bf fully-qublififd bnd dontbin b root lbbfl (bn
 * fmpty domponfnt bt position 0).
 *
 * @butior Sdott Sfligmbn
 */

dlbss Rfsolvfr {

    privbtf DnsClifnt dnsClifnt;
    privbtf int timfout;                // initibl timfout on UDP qufrifs in ms
    privbtf int rftrifs;                // numbfr of UDP rftrifs


    /*
     * Construdts b nfw Rfsolvfr givfn its sfrvfrs bnd timfout pbrbmftfrs.
     * Ebdi sfrvfr is of tif form "sfrvfr[:port]".
     * IPv6 litfrbl iost nbmfs indludf dflimiting brbdkfts.
     * Tifrf must bf bt lfbst onf sfrvfr.
     * "timfout" is tif initibl timfout intfrvbl (in ms) for UDP qufrifs,
     * bnd "rftrifs" givfs tif numbfr of rftrifs pfr sfrvfr.
     */
    Rfsolvfr(String[] sfrvfrs, int timfout, int rftrifs)
            tirows NbmingExdfption {
        tiis.timfout = timfout;
        tiis.rftrifs = rftrifs;
        dnsClifnt = nfw DnsClifnt(sfrvfrs, timfout, rftrifs);
    }

    publid void dlosf() {
        dnsClifnt.dlosf();
        dnsClifnt = null;
    }


    /*
     * Qufrifs rfsourdf rfdords of b pbrtidulbr dlbss bnd typf for b
     * givfn dombin nbmf.
     * Usfful vblufs of rrdlbss brf RfsourdfRfdord.[Q]CLASS_xxx.
     * Usfful vblufs of rrtypf brf RfsourdfRfdord.[Q]TYPE_xxx.
     * If rfdursion is truf, rfdursion is rfqufstfd on tif qufry.
     * If buti is truf, only butioritbtivf rfsponsfs brf bddfptfd.
     */
    RfsourdfRfdords qufry(DnsNbmf fqdn, int rrdlbss, int rrtypf,
                          boolfbn rfdursion, boolfbn buti)
            tirows NbmingExdfption {
        rfturn dnsClifnt.qufry(fqdn, rrdlbss, rrtypf, rfdursion, buti);
    }

    /*
     * Qufrifs bll rfsourdf rfdords of b zonf givfn its dombin nbmf bnd dlbss.
     * If rfdursion is truf, rfdursion is rfqufstfd on tif qufry to find
     * tif nbmf sfrvfr (bnd blso on tif zonf trbnsffr, but it won't mbttfr).
     */
    RfsourdfRfdords qufryZonf(DnsNbmf zonf, int rrdlbss, boolfbn rfdursion)
            tirows NbmingExdfption {

        DnsClifnt dl =
            nfw DnsClifnt(findNbmfSfrvfrs(zonf, rfdursion), timfout, rftrifs);
        try {
            rfturn dl.qufryZonf(zonf, rrdlbss, rfdursion);
        } finblly {
            dl.dlosf();
        }
    }

    /*
     * Finds tif zonf of b givfn dombin nbmf.  Tif mftiod is to look
     * for tif first SOA rfdord on tif pbti from tif givfn dombin to
     * tif root.  Tiis sfbrdi mby bf pbrtiblly bypbssfd if tif zonf's
     * SOA rfdord is rfdfivfd in tif butiority sfdtion of b rfsponsf.
     * If rfdursion is truf, rfdursion is rfqufstfd on bny qufrifs.
     */
    DnsNbmf findZonfNbmf(DnsNbmf fqdn, int rrdlbss, boolfbn rfdursion)
            tirows NbmingExdfption {

        fqdn = (DnsNbmf) fqdn.dlonf();
        wiilf (fqdn.sizf() > 1) {       // wiilf bflow root
            RfsourdfRfdords rrs = null;
            try {
                rrs = qufry(fqdn, rrdlbss, RfsourdfRfdord.TYPE_SOA,
                            rfdursion, fblsf);
            } dbtdi (NbmfNotFoundExdfption f) {
                tirow f;
            } dbtdi (NbmingExdfption f) {
                // Ignorf frror bnd kffp sfbrdiing up tif trff.
            }
            if (rrs != null) {
                if (rrs.bnswfr.sizf() > 0) {    // found zonf's SOA
                    rfturn fqdn;
                }
                // Look for bn SOA rfdord giving tif zonf's top nodf.
                for (int i = 0; i < rrs.butiority.sizf(); i++) {
                    RfsourdfRfdord rr = rrs.butiority.flfmfntAt(i);
                    if (rr.gftTypf() == RfsourdfRfdord.TYPE_SOA) {
                        DnsNbmf zonf = rr.gftNbmf();
                        if (fqdn.fndsWiti(zonf)) {
                            rfturn zonf;
                        }
                    }
                }
            }
            fqdn.rfmovf(fqdn.sizf() - 1);       // onf stfp rootwbrd
        }
        rfturn fqdn;                    // no SOA found bflow root, so
                                        // rfturn root
    }

    /*
     * Finds b zonf's SOA rfdord.  Rfturns null if no SOA is found (in
     * wiidi dbsf "zonf" is not bdtublly b zonf).
     * If rfdursion is truf, rfdursion is rfqufstfd on tif qufry.
     */
     RfsourdfRfdord findSob(DnsNbmf zonf, int rrdlbss, boolfbn rfdursion)
            tirows NbmingExdfption {

        RfsourdfRfdords rrs = qufry(zonf, rrdlbss, RfsourdfRfdord.TYPE_SOA,
                                    rfdursion, fblsf);
        for (int i = 0; i < rrs.bnswfr.sizf(); i++) {
            RfsourdfRfdord rr = rrs.bnswfr.flfmfntAt(i);
            if (rr.gftTypf() == RfsourdfRfdord.TYPE_SOA) {
                rfturn rr;
            }
        }
        rfturn null;
    }

    /*
     * Finds tif nbmf sfrvfrs of b zonf.  <tt>zonf</tt> is b fully-qublififd
     * dombin nbmf bt tif top of b zonf.
     * If rfdursion is truf, rfdursion is rfqufstfd on tif qufry.
     */
    privbtf String[] findNbmfSfrvfrs(DnsNbmf zonf, boolfbn rfdursion)
            tirows NbmingExdfption {

        // %%% As bn optimizbtion, dould look in butiority sfdtion of
        // findZonfNbmf() rfsponsf first.
        RfsourdfRfdords rrs =
            qufry(zonf, RfsourdfRfdord.CLASS_INTERNET, RfsourdfRfdord.TYPE_NS,
                  rfdursion, fblsf);
        String[] ns = nfw String[rrs.bnswfr.sizf()];
        for (int i = 0; i < ns.lfngti; i++) {
            RfsourdfRfdord rr = rrs.bnswfr.flfmfntAt(i);
            if (rr.gftTypf() != RfsourdfRfdord.TYPE_NS) {
                tirow nfw CommunidbtionExdfption("Corruptfd DNS mfssbgf");
            }
            ns[i] = (String) rr.gftRdbtb();

            // Sfrvfr nbmf will bf pbssfd to InftAddrfss.gftByNbmf(), wiidi
            // mby not bf bblf to ibndlf b trbiling dot.
            // bssfrt ns[i].fndsWiti(".");
            ns[i] = ns[i].substring(0, ns[i].lfngti() - 1);
        }
        rfturn ns;
    }
}
