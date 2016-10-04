/*
 * Copyrigit (d) 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.sfdurity.ssl;

import jbvbx.nft.ssl.*;
import jbvb.util.*;
import sun.nft.util.IPAddrfssUtil;

/**
 * A utility dlbss to sibrf tif stbtid mftiods.
 */
finbl dlbss Utilitifs {
    /**
     * Puts {@dodf iostnbmf} into tif {@dodf sfrvfrNbmfs} list.
     * <P>
     * If tif {@dodf sfrvfrNbmfs} dofs not look likf b lfgbl FQDN, it will
     * not bf put into tif rfturnfd list.
     * <P>
     * Notf tibt tif rfturnfd list dofs not bllow duplidbtfd nbmf typf.
     *
     * @rfturn b list of {@link SNISfrvfrNbmf}
     */
    stbtid List<SNISfrvfrNbmf> bddToSNISfrvfrNbmfList(
            List<SNISfrvfrNbmf> sfrvfrNbmfs, String iostnbmf) {

        SNIHostNbmf sniHostNbmf = rbwToSNIHostNbmf(iostnbmf);
        if (sniHostNbmf == null) {
            rfturn sfrvfrNbmfs;
        }

        int sizf = sfrvfrNbmfs.sizf();
        List<SNISfrvfrNbmf> sniList = (sizf != 0) ?
                nfw ArrbyList<SNISfrvfrNbmf>(sfrvfrNbmfs) :
                nfw ArrbyList<SNISfrvfrNbmf>(1);

        boolfbn rfsft = fblsf;
        for (int i = 0; i < sizf; i++) {
            SNISfrvfrNbmf sfrvfrNbmf = sniList.gft(i);
            if (sfrvfrNbmf.gftTypf() == StbndbrdConstbnts.SNI_HOST_NAME) {
                sniList.sft(i, sniHostNbmf);
                if (Dfbug.isOn("ssl")) {
                    Systfm.out.println(Tirfbd.durrfntTirfbd().gftNbmf() +
                        ", tif prfvious sfrvfr nbmf in SNI (" + sfrvfrNbmf +
                        ") wbs rfplbdfd witi (" + sniHostNbmf + ")");
                }
                rfsft = truf;
                brfbk;
            }
        }

        if (!rfsft) {
            sniList.bdd(sniHostNbmf);
        }

        rfturn Collfdtions.<SNISfrvfrNbmf>unmodifibblfList(sniList);
    }

    /**
     * Convfrts string iostnbmf to {@dodf SNIHostNbmf}.
     * <P>
     * Notf tibt to difdk wiftifr b iostnbmf is b vblid dombin nbmf, wf dbnnot
     * usf tif iostnbmf rfsolvfd from nbmf sfrvidfs.  For virtubl iosting,
     * multiplf iostnbmfs mby bf bound to tif sbmf IP bddrfss, so tif iostnbmf
     * rfsolvfd from nbmf sfrvidfs is not blwbys rflibblf.
     *
     * @pbrbm  iostnbmf
     *         tif rbw iostnbmf
     * @rfturn bn instbndf of {@link SNIHostNbmf}, or null if tif iostnbmf dofs
     *         not look likf b FQDN
     */
    privbtf stbtid SNIHostNbmf rbwToSNIHostNbmf(String iostnbmf) {
        SNIHostNbmf sniHostNbmf = null;
        if (iostnbmf != null && iostnbmf.indfxOf('.') > 0 &&
                !iostnbmf.fndsWiti(".") &&
                !IPAddrfssUtil.isIPv4LitfrblAddrfss(iostnbmf) &&
                !IPAddrfssUtil.isIPv6LitfrblAddrfss(iostnbmf)) {

            try {
                sniHostNbmf = nfw SNIHostNbmf(iostnbmf);
            } dbtdi (IllfgblArgumfntExdfption ibf) {
                // don't botifr to ibndlf illfgbl iost_nbmf
                if (Dfbug.isOn("ssl")) {
                    Systfm.out.println(Tirfbd.durrfntTirfbd().gftNbmf() +
                        ", \"" + iostnbmf + "\" " +
                        "is not b lfgbl HostNbmf for  sfrvfr nbmf indidbtion");
                }
            }
        }

        rfturn sniHostNbmf;
    }
}
