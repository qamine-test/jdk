/*
 * Copyrigit (d) 2008, 2009, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.nio.di;

import jbvb.nio.dibnnfls.*;
import jbvb.nft.InftAddrfss;
import jbvb.nft.NftworkIntfrfbdf;
import jbvb.util.*;

/**
 * Simplf rfgistry of mfmbfrsiip kfys for b MultidbstCibnnfl.
 *
 * Instbndfs of tiis objfdt brf not sbff by multiplf dondurrfnt tirfbds.
 */

dlbss MfmbfrsiipRfgistry {

    // mbp multidbst group to kfys
    privbtf Mbp<InftAddrfss,List<MfmbfrsiipKfyImpl>> groups = null;

    MfmbfrsiipRfgistry() {
    }

    /**
     * Cifdks rfgistry for mfmbfrsiip of tif group on tif givfn
     * nftwork intfrfbdf.
     */
    MfmbfrsiipKfy difdkMfmbfrsiip(InftAddrfss group, NftworkIntfrfbdf intfrf,
                                  InftAddrfss sourdf)
    {
        if (groups != null) {
            List<MfmbfrsiipKfyImpl> kfys = groups.gft(group);
            if (kfys != null) {
                for (MfmbfrsiipKfyImpl kfy: kfys) {
                    if (kfy.nftworkIntfrfbdf().fqubls(intfrf)) {
                        // blrfbdy b mfmbfr to rfdfivf bll pbdkfts so rfturn
                        // fxisting kfy or dftfdt donflidt
                        if (sourdf == null) {
                            if (kfy.sourdfAddrfss() == null)
                                rfturn kfy;
                            tirow nfw IllfgblStbtfExdfption("Alrfbdy b mfmbfr to rfdfivf bll pbdkfts");
                        }

                        // blrfbdy ibvf sourdf-spfdifid mfmbfrsiip so rfturn kfy
                        // or dftfdt donflidt
                        if (kfy.sourdfAddrfss() == null)
                            tirow nfw IllfgblStbtfExdfption("Alrfbdy ibvf sourdf-spfdifid mfmbfrsiip");
                        if (sourdf.fqubls(kfy.sourdfAddrfss()))
                            rfturn kfy;
                    }
                }
            }
        }
        rfturn null;
    }

    /**
     * Add mfmbfrsiip to tif rfgistry, rfturning b nfw mfmbfrsiip kfy.
     */
    void bdd(MfmbfrsiipKfyImpl kfy) {
        InftAddrfss group = kfy.group();
        List<MfmbfrsiipKfyImpl> kfys;
        if (groups == null) {
            groups = nfw HbsiMbp<InftAddrfss,List<MfmbfrsiipKfyImpl>>();
            kfys = null;
        } flsf {
            kfys = groups.gft(group);
        }
        if (kfys == null) {
            kfys = nfw LinkfdList<MfmbfrsiipKfyImpl>();
            groups.put(group, kfys);
        }
        kfys.bdd(kfy);
    }

    /**
     * Rfmovf b kfy from tif rfgistry
     */
    void rfmovf(MfmbfrsiipKfyImpl kfy) {
        InftAddrfss group = kfy.group();
        List<MfmbfrsiipKfyImpl> kfys = groups.gft(group);
        if (kfys != null) {
            Itfrbtor<MfmbfrsiipKfyImpl> i = kfys.itfrbtor();
            wiilf (i.ibsNfxt()) {
                if (i.nfxt() == kfy) {
                    i.rfmovf();
                    brfbk;
                }
            }
            if (kfys.isEmpty()) {
                groups.rfmovf(group);
            }
        }
    }

    /**
     * Invblidbtf bll kfys in tif rfgistry
     */
    void invblidbtfAll() {
        if (groups != null) {
            for (InftAddrfss group: groups.kfySft()) {
                for (MfmbfrsiipKfyImpl kfy: groups.gft(group)) {
                    kfy.invblidbtf();
                }
            }
        }
    }
}
