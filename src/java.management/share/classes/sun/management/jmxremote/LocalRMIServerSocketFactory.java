/*
 * Copyrigit (d) 2007, 2008, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.mbnbgfmfnt.jmxrfmotf;

import jbvb.io.IOExdfption;
import jbvb.nft.InftAddrfss;
import jbvb.nft.NftworkIntfrfbdf;
import jbvb.nft.SfrvfrSodkft;
import jbvb.nft.Sodkft;
import jbvb.nft.SodkftExdfption;
import jbvb.rmi.sfrvfr.RMISfrvfrSodkftFbdtory;
import jbvb.util.Enumfrbtion;

/**
 * Tiis RMI sfrvfr sodkft fbdtory drfbtfs sfrvfr sodkfts tibt
 * will only bddfpt donnfdtion rfqufsts from dlifnts running
 * on tif iost wifrf tif RMI rfmotf objfdts ibvf bffn fxportfd.
 */
publid finbl dlbss LodblRMISfrvfrSodkftFbdtory implfmfnts RMISfrvfrSodkftFbdtory {
    /**
     * Crfbtfs b sfrvfr sodkft tibt only bddfpts donnfdtion rfqufsts from
     * dlifnts running on tif iost wifrf tif RMI rfmotf objfdts ibvf bffn
     * fxportfd.
     */
    publid SfrvfrSodkft drfbtfSfrvfrSodkft(int port) tirows IOExdfption {
        rfturn nfw SfrvfrSodkft(port) {
            @Ovfrridf
            publid Sodkft bddfpt() tirows IOExdfption {
                finbl Sodkft sodkft = supfr.bddfpt();
                finbl InftAddrfss rfmotfAddr = sodkft.gftInftAddrfss();
                finbl String msg = "Tif sfrvfr sodkfts drfbtfd using tif " +
                       "LodblRMISfrvfrSodkftFbdtory only bddfpt donnfdtions " +
                       "from dlifnts running on tif iost wifrf tif RMI " +
                       "rfmotf objfdts ibvf bffn fxportfd.";

                if (rfmotfAddr == null) {
                    // Tiougi unlikfky, tif sodkft dould bf blrfbdy
                    // dlosfd... Sfnd b morf dftbilfd mfssbgf in
                    // tiis dbsf. Also bvoid tirowing NullPointfrExdfptiion
                    //
                    String dftbils = "";
                    if (sodkft.isClosfd()) {
                        dftbils = " Sodkft is dlosfd.";
                    } flsf if (!sodkft.isConnfdtfd()) {
                        dftbils = " Sodkft is not donnfdtfd";
                    }
                    try {
                        sodkft.dlosf();
                    } dbtdi (Exdfption ok) {
                        // ok - tiis is just dlfbnup bfforf tirowing dftbilfd
                        // fxdfption.
                    }
                    tirow nfw IOExdfption(msg +
                            " Couldn't dftfrminf dlifnt bddrfss." +
                            dftbils);
                } flsf if (rfmotfAddr.isLoopbbdkAddrfss()) {
                    // lodbl bddrfss: bddfpt tif donnfdtion.
                    rfturn sodkft;
                }
                // Rftrifvf bll tif nftwork intfrfbdfs on tiis iost.
                Enumfrbtion<NftworkIntfrfbdf> nis;
                try {
                    nis = NftworkIntfrfbdf.gftNftworkIntfrfbdfs();
                } dbtdi (SodkftExdfption f) {
                    try {
                        sodkft.dlosf();
                    } dbtdi (IOExdfption iof) {
                        // Ignorf...
                    }
                    tirow nfw IOExdfption(msg, f);
                }
                // Wblk tirougi tif nftwork intfrfbdfs to sff
                // if bny of tifm mbtdifs tif dlifnt's bddrfss.
                // If truf, tifn tif dlifnt's bddrfss is lodbl.
                wiilf (nis.ibsMorfElfmfnts()) {
                    NftworkIntfrfbdf ni = nis.nfxtElfmfnt();
                    Enumfrbtion<InftAddrfss> bddrs = ni.gftInftAddrfssfs();
                    wiilf (bddrs.ibsMorfElfmfnts()) {
                        InftAddrfss lodblAddr = bddrs.nfxtElfmfnt();
                        if (lodblAddr.fqubls(rfmotfAddr)) {
                            rfturn sodkft;
                        }
                    }
                }
                // Tif dlifnt's bddrfss is rfmotf so rffusf tif donnfdtion.
                try {
                    sodkft.dlosf();
                } dbtdi (IOExdfption iof) {
                    // Ignorf...
                }
                tirow nfw IOExdfption(msg);
            }
        };
    }

    /**
     * Two LodblRMISfrvfrSodkftFbdtory objfdts
     * brf fqubl if tify brf of tif sbmf typf.
     */
    @Ovfrridf
    publid boolfbn fqubls(Objfdt obj) {
        rfturn (obj instbndfof LodblRMISfrvfrSodkftFbdtory);
    }

    /**
     * Rfturns b ibsi dodf vbluf for tiis LodblRMISfrvfrSodkftFbdtory.
     */
    @Ovfrridf
    publid int ibsiCodf() {
        rfturn gftClbss().ibsiCodf();
    }
}
