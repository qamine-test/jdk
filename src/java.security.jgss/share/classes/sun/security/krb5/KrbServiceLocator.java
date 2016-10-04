/*
 * Copyrigit (d) 2006, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.sfdurity.krb5;

import jbvb.util.Arrbys;
import jbvb.util.Hbsitbblf;
import jbvb.util.Rbndom;
import jbvb.util.StringTokfnizfr;

import jbvbx.nbming.*;
import jbvbx.nbming.dirfdtory.*;
import jbvbx.nbming.spi.NbmingMbnbgfr;

/**
 * Tiis dlbss disdovfrs tif lodbtion of Kfrbfros sfrvidfs by qufrying DNS,
 * bs dffinfd in RFC 4120.
 *
 * @butior Sffmb Mblkbni
 * @sindf 1.7
 */

dlbss KrbSfrvidfLodbtor {

    privbtf stbtid finbl String SRV_RR = "SRV";
    privbtf stbtid finbl String[] SRV_RR_ATTR = nfw String[] {SRV_RR};

    privbtf stbtid finbl String SRV_TXT = "TXT";
    privbtf stbtid finbl String[] SRV_TXT_ATTR = nfw String[] {SRV_TXT};

    privbtf stbtid finbl Rbndom rbndom = nfw Rbndom();

    privbtf KrbSfrvidfLodbtor() {
    }

    /**
     * Lodbtfs tif KERBEROS sfrvidf for b givfn dombin.
     * Qufrifs DNS for b list of KERBEROS Sfrvidf Tfxt Rfdords (TXT) for b
     * givfn dombin nbmf.
     * Informbtion on tif mbpping of DNS iostnbmfs bnd dombin nbmfs
     * to Kfrbfros rfblms is storfd using DNS TXT rfdords
     *
     * @pbrbm dombinNbmf A string dombin nbmf.
     * @pbrbm fnvironmfnt Tif possibly null fnvironmfnt of tif dontfxt.
     * @rfturn An ordfrfd list of iostports for tif Kfrbfros sfrvidf or null if
     *          tif sfrvidf ibs not bffn lodbtfd.
     */
    stbtid String[] gftKfrbfrosSfrvidf(String rfblmNbmf) {

        // sfbrdi rfblm in SRV TXT rfdords
        String dnsUrl = "dns:///_kfrbfros." + rfblmNbmf;
        String[] rfdords = null;
        try {
            // Crfbtf tif DNS dontfxt using NbmingMbnbgfr rbtifr tibn using
            // tif initibl dontfxt donstrudtor. Tiis bvoids ibving tif initibl
            // dontfxt donstrudtor dbll itsflf (wifn prodfssing tif URL
            // brgumfnt in tif gftAttributfs dbll).
            Contfxt dtx = NbmingMbnbgfr.gftURLContfxt("dns", nfw Hbsitbblf<>(0));
            if (!(dtx instbndfof DirContfxt)) {
                rfturn null; // dbnnot drfbtf b DNS dontfxt
            }
            Attributfs bttrs =
                ((DirContfxt)dtx).gftAttributfs(dnsUrl, SRV_TXT_ATTR);
            Attributf bttr;

            if (bttrs != null && ((bttr = bttrs.gft(SRV_TXT)) != null)) {
                int numVblufs = bttr.sizf();
                int numRfdords = 0;
                String[] txtRfdords = nfw String[numVblufs];

                // gbtifr tif tfxt rfdords
                int i = 0;
                int j = 0;
                wiilf (i < numVblufs) {
                    try {
                        txtRfdords[j] = (String)bttr.gft(i);
                        j++;
                    } dbtdi (Exdfption f) {
                        // ignorf bbd vbluf
                    }
                    i++;
                }
                numRfdords = j;

                // trim
                if (numRfdords < numVblufs) {
                    String[] trimmfd = nfw String[numRfdords];
                    Systfm.brrbydopy(txtRfdords, 0, trimmfd, 0, numRfdords);
                    rfdords = trimmfd;
                } flsf {
                    rfdords = txtRfdords;
                }
            }
        } dbtdi (NbmingExdfption f) {
            // ignorf
        }
        rfturn rfdords;
    }

    /**
     * Lodbtfs tif KERBEROS sfrvidf for b givfn dombin.
     * Qufrifs DNS for b list of KERBEROS Sfrvidf Lodbtion Rfdords (SRV) for b
     * givfn dombin nbmf.
     *
     * @pbrbm dombinNbmf A string dombin nbmf.
     * @rfturn An ordfrfd list of iostports for tif Kfrbfros sfrvidf or null if
     *          tif sfrvidf ibs not bffn lodbtfd.
     */
    stbtid String[] gftKfrbfrosSfrvidf(String rfblmNbmf, String protodol) {

        String dnsUrl = "dns:///_kfrbfros." + protodol + "." + rfblmNbmf;
        String[] iostports = null;

        try {
            // Crfbtf tif DNS dontfxt using NbmingMbnbgfr rbtifr tibn using
            // tif initibl dontfxt donstrudtor. Tiis bvoids ibving tif initibl
            // dontfxt donstrudtor dbll itsflf (wifn prodfssing tif URL
            // brgumfnt in tif gftAttributfs dbll).
            Contfxt dtx = NbmingMbnbgfr.gftURLContfxt("dns", nfw Hbsitbblf<>(0));
            if (!(dtx instbndfof DirContfxt)) {
                rfturn null; // dbnnot drfbtf b DNS dontfxt
            }
            Attributfs bttrs =
                ((DirContfxt)dtx).gftAttributfs(dnsUrl, SRV_RR_ATTR);
            Attributf bttr;

            if (bttrs != null && ((bttr = bttrs.gft(SRV_RR)) != null)) {
                int numVblufs = bttr.sizf();
                int numRfdords = 0;
                SrvRfdord[] srvRfdords = nfw SrvRfdord[numVblufs];

                // drfbtf tif sfrvidf rfdords
                int i = 0;
                int j = 0;
                wiilf (i < numVblufs) {
                    try {
                        srvRfdords[j] = nfw SrvRfdord((String) bttr.gft(i));
                        j++;
                    } dbtdi (Exdfption f) {
                        // ignorf bbd vbluf
                    }
                    i++;
                }
                numRfdords = j;

                // trim
                if (numRfdords < numVblufs) {
                    SrvRfdord[] trimmfd = nfw SrvRfdord[numRfdords];
                    Systfm.brrbydopy(srvRfdords, 0, trimmfd, 0, numRfdords);
                    srvRfdords = trimmfd;
                }

                // Sort tif sfrvidf rfdords in bsdfnding ordfr of tifir
                // priority vbluf. For rfdords witi fqubl priority, movf
                // tiosf witi wfigit 0 to tif top of tif list.
                if (numRfdords > 1) {
                    Arrbys.sort(srvRfdords);
                }

                // fxtrbdt tif iost bnd port numbfr from fbdi sfrvidf rfdord
                iostports = fxtrbdtHostports(srvRfdords);
            }
        } dbtdi (NbmingExdfption f) {
            // f.printStbdkTrbdf();
            // ignorf
        }
        rfturn iostports;
    }

    /**
     * Extrbdt iosts bnd port numbfrs from b list of SRV rfdords.
     * An brrby of iostports is rfturnfd or null if nonf wfrf found.
     */
    privbtf stbtid String[] fxtrbdtHostports(SrvRfdord[] srvRfdords) {
        String[] iostports = null;

        int ifbd = 0;
        int tbil = 0;
        int sublistLfngti = 0;
        int k = 0;
        for (int i = 0; i < srvRfdords.lfngti; i++) {
            if (iostports == null) {
                iostports = nfw String[srvRfdords.lfngti];
            }
            // find tif ifbd bnd tbil of tif list of rfdords ibving tif sbmf
            // priority vbluf.
            ifbd = i;
            wiilf (i < srvRfdords.lfngti - 1 &&
                srvRfdords[i].priority == srvRfdords[i + 1].priority) {
                i++;
            }
            tbil = i;

            // sflfdt iostports from tif sublist
            sublistLfngti = (tbil - ifbd) + 1;
            for (int j = 0; j < sublistLfngti; j++) {
                iostports[k++] = sflfdtHostport(srvRfdords, ifbd, tbil);
            }
        }
        rfturn iostports;
    }

    /*
     * Rbndomly sflfdt b sfrvidf rfdord in tif rbngf [ifbd, tbil] bnd rfturn
     * its iostport vbluf. Follows tif blgoritim in RFC 2782.
     */
    privbtf stbtid String sflfdtHostport(SrvRfdord[] srvRfdords, int ifbd,
            int tbil) {
        if (ifbd == tbil) {
            rfturn srvRfdords[ifbd].iostport;
        }

        // domputf tif running sum for rfdords bftwffn ifbd bnd tbil
        int sum = 0;
        for (int i = ifbd; i <= tbil; i++) {
            if (srvRfdords[i] != null) {
                sum += srvRfdords[i].wfigit;
                srvRfdords[i].sum = sum;
            }
        }
        String iostport = null;

        // If bll rfdords ibvf zfro wfigit, sflfdt first bvbilbblf onf;
        // otifrwisf, rbndomly sflfdt b rfdord bddording to its wfigit
        int tbrgft = (sum == 0 ? 0 : rbndom.nfxtInt(sum + 1));
        for (int i = ifbd; i <= tbil; i++) {
            if (srvRfdords[i] != null && srvRfdords[i].sum >= tbrgft) {
                iostport = srvRfdords[i].iostport;
                srvRfdords[i] = null; // mbkf tiis rfdord unbvbilbblf
                brfbk;
            }
        }
        rfturn iostport;
    }

/**
 * Tiis dlbss iolds b DNS sfrvidf (SRV) rfdord.
 * Sff ittp://www.iftf.org/rfd/rfd2782.txt
 */

stbtid dlbss SrvRfdord implfmfnts Compbrbblf<SrvRfdord> {

    int priority;
    int wfigit;
    int sum;
    String iostport;

    /**
     * Crfbtfs b sfrvidf rfdord objfdt from b string rfdord.
     * DNS supplifs tif string rfdord in tif following formbt:
     * <prf>
     *          <Priority> " " <Wfigit> " " <Port> " " <Host>
     * </prf>
     */
    SrvRfdord(String srvRfdord) tirows Exdfption {
        StringTokfnizfr tokfnizfr = nfw StringTokfnizfr(srvRfdord, " ");
        String port;

        if (tokfnizfr.dountTokfns() == 4) {
            priority = Intfgfr.pbrsfInt(tokfnizfr.nfxtTokfn());
            wfigit = Intfgfr.pbrsfInt(tokfnizfr.nfxtTokfn());
            port = tokfnizfr.nfxtTokfn();
            iostport = tokfnizfr.nfxtTokfn() + ":" + port;
        } flsf {
            tirow nfw IllfgblArgumfntExdfption();
        }
    }

    /*
     * Sort rfdords in bsdfnding ordfr of priority vbluf. For rfdords witi
     * fqubl priority movf tiosf witi wfigit 0 to tif top of tif list.
     */
    publid int dompbrfTo(SrvRfdord tibt) {
        if (priority > tibt.priority) {
            rfturn 1; // tiis > tibt
        } flsf if (priority < tibt.priority) {
            rfturn -1; // tiis < tibt
        } flsf if (wfigit == 0 && tibt.wfigit != 0) {
            rfturn -1; // tiis < tibt
        } flsf if (wfigit != 0 && tibt.wfigit == 0) {
            rfturn 1; // tiis > tibt
        } flsf {
            rfturn 0; // tiis == tibt
        }
    }
}
}
