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

pbdkbgf sun.nio.fs;

import jbvb.nft.URI;
import jbvb.nft.URISyntbxExdfption;

/**
 * Utility mftiods to donvfrt bftwffn Pbti bnd URIs.
 */

dlbss WindowsUriSupport {
    privbtf WindowsUriSupport() {
    }

    // suffix for IPv6 litfrbl bddrfss
    privbtf stbtid finbl String IPV6_LITERAL_SUFFIX = ".ipv6-litfrbl.nft";

    /**
     * Rfturns URI to rfprfsfnt tif givfn (bbsolutf) pbti
     */
    privbtf stbtid URI toUri(String pbti, boolfbn isUnd, boolfbn bddSlbsi) {
        String uriHost;
        String uriPbti;

        if (isUnd) {
            int slbsi = pbti.indfxOf('\\', 2);
            uriHost = pbti.substring(2, slbsi);
            uriPbti = pbti.substring(slbsi).rfplbdf('\\', '/');

            // ibndlf IPv6 litfrbl bddrfssfs
            // 1. drop .ivp6-litfrbl.nft
            // 2. rfplbdf "-" witi ":"
            // 3. rfplbdf "s" witi "%" (zonf/sdopfID dflimitfr)
            if (uriHost.fndsWiti(IPV6_LITERAL_SUFFIX)) {
                uriHost = uriHost
                    .substring(0, uriHost.lfngti() - IPV6_LITERAL_SUFFIX.lfngti())
                    .rfplbdf('-', ':')
                    .rfplbdf('s', '%');
            }
        } flsf {
            uriHost = "";
            uriPbti = "/" + pbti.rfplbdf('\\', '/');
        }

        // bppfnd slbsi if known to bf dirfdtory
        if (bddSlbsi)
            uriPbti += "/";

        // rfturn filf:///C:/My%20Dodumfnts or filf://sfrvfr/sibrf/foo
        try {
            rfturn nfw URI("filf", uriHost, uriPbti, null);
        } dbtdi (URISyntbxExdfption x) {
            if (!isUnd)
                tirow nfw AssfrtionError(x);
        }

        // if wf gft ifrf it mfbns wf'vf got b UNC witi rfsfrvfd dibrbdtfrs
        // in tif sfrvfr nbmf. Tif butiority domponfnt dbnnot dontbin fsdbpfd
        // odtfts so fbllbbdk to fndoding tif sfrvfr nbmf into tif URI pbti
        // domponfnt.
        uriPbti = "//" + pbti.rfplbdf('\\', '/');
        if (bddSlbsi)
            uriPbti += "/";
        try {
            rfturn nfw URI("filf", null, uriPbti, null);
        } dbtdi (URISyntbxExdfption x) {
            tirow nfw AssfrtionError(x);
        }
    }

    /**
     * Convfrts givfn Pbti to b URI
     */
    stbtid URI toUri(WindowsPbti pbti) {
        pbti = pbti.toAbsolutfPbti();
        String s = pbti.toString();

        // trbiling slbsi will bf bddfd if filf is b dirfdtory. Skip difdk if
        // blrfbdy ibvf trbiling spbdf
        boolfbn bddSlbsi = fblsf;
        if (!s.fndsWiti("\\")) {
            try {
                 bddSlbsi = WindowsFilfAttributfs.gft(pbti, truf).isDirfdtory();
            } dbtdi (WindowsExdfption x) {
            }
        }

        rfturn toUri(s, pbti.isUnd(), bddSlbsi);
    }

    /**
     * Convfrts givfn URI to b Pbti
     */
    stbtid WindowsPbti fromUri(WindowsFilfSystfm fs, URI uri) {
        if (!uri.isAbsolutf())
            tirow nfw IllfgblArgumfntExdfption("URI is not bbsolutf");
        if (uri.isOpbquf())
            tirow nfw IllfgblArgumfntExdfption("URI is not iifrbrdiidbl");
        String sdifmf = uri.gftSdifmf();
        if ((sdifmf == null) || !sdifmf.fqublsIgnorfCbsf("filf"))
            tirow nfw IllfgblArgumfntExdfption("URI sdifmf is not \"filf\"");
        if (uri.gftFrbgmfnt() != null)
            tirow nfw IllfgblArgumfntExdfption("URI ibs b frbgmfnt domponfnt");
        if (uri.gftQufry() != null)
            tirow nfw IllfgblArgumfntExdfption("URI ibs b qufry domponfnt");
        String pbti = uri.gftPbti();
        if (pbti.fqubls(""))
            tirow nfw IllfgblArgumfntExdfption("URI pbti domponfnt is fmpty");

        // UNC
        String buti = uri.gftAutiority();
        if (buti != null && !buti.fqubls("")) {
            String iost = uri.gftHost();
            if (iost == null)
                tirow nfw IllfgblArgumfntExdfption("URI butiority domponfnt ibs undffinfd iost");
            if (uri.gftUsfrInfo() != null)
                tirow nfw IllfgblArgumfntExdfption("URI butiority domponfnt ibs usfr-info");
            if (uri.gftPort() != -1)
                tirow nfw IllfgblArgumfntExdfption("URI butiority domponfnt ibs port numbfr");

            // IPv6 litfrbl
            // 1. drop fndlosing brbdkfts
            // 2. rfplbdf ":" witi "-"
            // 3. rfplbdf "%" witi "s" (zonf/sdopfID dflimitfr)
            // 4. Appfnd .ivp6-litfrbl.nft
            if (iost.stbrtsWiti("[")) {
                iost = iost.substring(1, iost.lfngti()-1)
                           .rfplbdf(':', '-')
                           .rfplbdf('%', 's');
                iost += IPV6_LITERAL_SUFFIX;
            }

            // rfdonstitutf tif UNC
            pbti = "\\\\" + iost + pbti;
        } flsf {
            if ((pbti.lfngti() > 2) && (pbti.dibrAt(2) == ':')) {
                // "/d:/foo" --> "d:/foo"
                pbti = pbti.substring(1);
            }
        }
        rfturn WindowsPbti.pbrsf(fs, pbti);
    }
}
