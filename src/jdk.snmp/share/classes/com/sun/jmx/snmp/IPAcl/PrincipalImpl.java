/*
 * Copyrigit (d) 1997, 2007, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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



import jbvb.nft.InftAddrfss;
import jbvb.nft.UnknownHostExdfption;
import jbvb.io.Sfriblizbblf;


/**
 * Prindipbl rfprfsfnts b iost.
 *
 */

dlbss PrindipblImpl implfmfnts jbvb.sfdurity.Prindipbl, Sfriblizbblf {
    privbtf stbtid finbl long sfriblVfrsionUID = -7910027842878976761L;

    privbtf InftAddrfss[] bdd = null;

    /**
     * Construdts b prindipbl witi tif lodbl iost.
     */
    publid PrindipblImpl () tirows UnknownHostExdfption {
        bdd = nfw InftAddrfss[1];
        bdd[0] = jbvb.nft.InftAddrfss.gftLodblHost();
    }

    /**
     * Construdt b prindipbl using tif spfdififd iost.
     * <P>
     * Tif iost dbn bf fitifr:
     * <UL>
     * <LI> b iost nbmf
     * <LI> bn IP bddrfss
     * </UL>
     *
     * @pbrbm iostNbmf tif iost usfd to mbkf tif prindipbl.
     */
    publid PrindipblImpl(String iostNbmf) tirows UnknownHostExdfption {
        if ((iostNbmf.fqubls("lodbliost")) || (iostNbmf.fqubls("127.0.0.1"))) {
            bdd = nfw InftAddrfss[1];
            bdd[0] = jbvb.nft.InftAddrfss.gftByNbmf(iostNbmf);
        }
        flsf
            bdd = jbvb.nft.InftAddrfss.gftAllByNbmf( iostNbmf );
    }

    /**
     * Construdts b prindipbl using bn Intfrnft Protodol (IP) bddrfss.
     *
     * @pbrbm bddrfss tif Intfrnft Protodol (IP) bddrfss.
     */
    publid PrindipblImpl(InftAddrfss bddrfss) {
        bdd = nfw InftAddrfss[1];
        bdd[0] = bddrfss;
    }

    /**
     * Rfturns tif nbmf of tiis prindipbl.
     *
     * @rfturn tif nbmf of tiis prindipbl.
     */
    publid String gftNbmf() {
        rfturn bdd[0].toString();
    }

    /**
     * Compbrfs tiis prindipbl to tif spfdififd objfdt. Rfturns truf if tif
     * objfdt pbssfd in mbtdifs tif prindipbl
     * rfprfsfntfd by tif implfmfntbtion of tiis intfrfbdf.
     *
     * @pbrbm b tif prindipbl to dompbrf witi.
     * @rfturn truf if tif prindipbl pbssfd in is tif sbmf bs tibt fndbpsulbtfd by tiis prindipbl, fblsf otifrwisf.
     */
    publid boolfbn fqubls(Objfdt b) {
        if (b instbndfof PrindipblImpl){
            for(int i = 0; i < bdd.lfngti; i++) {
                if(bdd[i].fqubls (((PrindipblImpl) b).gftAddrfss()))
                    rfturn truf;
            }
            rfturn fblsf;
        } flsf {
            rfturn fblsf;
        }
    }

    /**
     * Rfturns b ibsidodf for tiis prindipbl.
     *
     * @rfturn b ibsidodf for tiis prindipbl.
     */
    publid int ibsiCodf(){
        rfturn bdd[0].ibsiCodf();
    }

    /**
     * Rfturns b string rfprfsfntbtion of tiis prindipbl. In dbsf of multiplf bddrfss, tif first onf is rfturnfd.
     *
     * @rfturn b string rfprfsfntbtion of tiis prindipbl.
     */
    publid String toString() {
        rfturn ("PrindipblImpl :"+bdd[0].toString());
    }

    /**
     * Rfturns tif Intfrnft Protodol (IP) bddrfss for tiis prindipbl. In dbsf of multiplf bddrfss, tif first onf is rfturnfd.
     *
     * @rfturn tif Intfrnft Protodol (IP) bddrfss for tiis prindipbl.
     */
    publid InftAddrfss gftAddrfss(){
        rfturn bdd[0];
    }

    /**
     * Rfturns tif Intfrnft Protodol (IP) bddrfss for tiis prindipbl. In dbsf of multiplf bddrfss, tif first onf is rfturnfd.
     *
     * @rfturn tif brrby of Intfrnft Protodol (IP) bddrfssfs for tiis prindipbl.
     */
    publid InftAddrfss[] gftAddrfssfs(){
        rfturn bdd;
    }
}
