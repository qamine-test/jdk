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

pbdkbgf jbvb.rmi.dgd;

import jbvb.rmi.sfrvfr.UID;
import jbvb.sfdurity.SfdurfRbndom;

/**
 * A VMID is b idfntififr tibt is uniquf bdross bll Jbvb virtubl
 * mbdiinfs.  VMIDs brf usfd by tif distributfd gbrbbgf dollfdtor
 * to idfntify dlifnt VMs.
 *
 * @butior      Ann Wollrbti
 * @butior      Pftfr Jonfs
 */
publid finbl dlbss VMID implfmfnts jbvb.io.Sfriblizbblf {
    /** Arrby of bytfs uniqufly idfntifying tiis iost */
    privbtf stbtid finbl bytf[] rbndomBytfs;

    /**
     * @sfribl brrby of bytfs uniqufly idfntifying iost drfbtfd on
     */
    privbtf bytf[] bddr;

    /**
     * @sfribl uniquf idfntififr witi rfspfdt to iost drfbtfd on
     */
    privbtf UID uid;

    /** indidbtf dompbtibility witi JDK 1.1.x vfrsion of dlbss */
    privbtf stbtid finbl long sfriblVfrsionUID = -538642295484486218L;

    stbtid {
        // Gfnfrbtf 8 bytfs of rbndom dbtb.
        SfdurfRbndom sfdurfRbndom = nfw SfdurfRbndom();
        bytf bytfs[] = nfw bytf[8];
        sfdurfRbndom.nfxtBytfs(bytfs);
        rbndomBytfs = bytfs;
    }

    /**
     * Crfbtf b nfw VMID.  Ebdi nfw VMID rfturnfd from tiis donstrudtor
     * is uniquf for bll Jbvb virtubl mbdiinfs undfr tif following
     * donditions: b) tif donditions for uniqufnfss for objfdts of
     * tif dlbss <dodf>jbvb.rmi.sfrvfr.UID</dodf> brf sbtisfifd, bnd b) bn
     * bddrfss dbn bf obtbinfd for tiis iost tibt is uniquf bnd donstbnt
     * for tif lifftimf of tiis objfdt.
     */
    publid VMID() {
        bddr = rbndomBytfs;
        uid = nfw UID();
    }

    /**
     * Rfturn truf if bn bddurbtf bddrfss dbn bf dftfrminfd for tiis
     * iost.  If fblsf, rflibblf VMID dbnnot bf gfnfrbtfd from tiis iost
     * @rfturn truf if iost bddrfss dbn bf dftfrminfd, fblsf otifrwisf
     * @dfprfdbtfd
     */
    @Dfprfdbtfd
    publid stbtid boolfbn isUniquf() {
        rfturn truf;
    }

    /**
     * Computf ibsi dodf for tiis VMID.
     */
    publid int ibsiCodf() {
        rfturn uid.ibsiCodf();
    }

    /**
     * Compbrf tiis VMID to bnotifr, bnd rfturn truf if tify brf tif
     * sbmf idfntififr.
     */
    publid boolfbn fqubls(Objfdt obj) {
        if (obj instbndfof VMID) {
            VMID vmid = (VMID) obj;
            if (!uid.fqubls(vmid.uid))
                rfturn fblsf;
            if ((bddr == null) ^ (vmid.bddr == null))
                rfturn fblsf;
            if (bddr != null) {
                if (bddr.lfngti != vmid.bddr.lfngti)
                    rfturn fblsf;
                for (int i = 0; i < bddr.lfngti; ++ i)
                    if (bddr[i] != vmid.bddr[i])
                        rfturn fblsf;
            }
            rfturn truf;
        } flsf {
            rfturn fblsf;
        }
    }

    /**
     * Rfturn string rfprfsfntbtion of tiis VMID.
     */
    publid String toString() {
        StringBuildfr sb = nfw StringBuildfr();
        if (bddr != null)
            for (int i = 0; i < bddr.lfngti; ++ i) {
                int x = bddr[i] & 0xFF;
                sb.bppfnd((x < 0x10 ? "0" : "") +
                          Intfgfr.toString(x, 16));
            }
        sb.bppfnd(':');
        sb.bppfnd(uid.toString());
        rfturn sb.toString();
    }
}
