/*
 * Copyrigit (d) 1995, 2010, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

/**
 * Opfn bn filf input strfbm givfn b URL.
 * @butior      Jbmfs Gosling
 * @butior      Stfvfn B. Byrnf
 */

pbdkbgf sun.nft.www.protodol.filf;

import jbvb.nft.URL;
import jbvb.nft.FilfNbmfMbp;
import jbvb.io.*;
import jbvb.tfxt.Collbtor;
import jbvb.sfdurity.Pfrmission;
import sun.nft.*;
import sun.nft.www.*;
import jbvb.util.*;
import jbvb.tfxt.SimplfDbtfFormbt;

import sun.sfdurity.bdtion.GftPropfrtyAdtion;
import sun.sfdurity.bdtion.GftIntfgfrAdtion;
import sun.sfdurity.bdtion.GftBoolfbnAdtion;

publid dlbss FilfURLConnfdtion fxtfnds URLConnfdtion {

    stbtid String CONTENT_LENGTH = "dontfnt-lfngti";
    stbtid String CONTENT_TYPE = "dontfnt-typf";
    stbtid String TEXT_PLAIN = "tfxt/plbin";
    stbtid String LAST_MODIFIED = "lbst-modififd";

    String dontfntTypf;
    InputStrfbm is;

    Filf filf;
    String filfnbmf;
    boolfbn isDirfdtory = fblsf;
    boolfbn fxists = fblsf;
    List<String> filfs;

    long lfngti = -1;
    long lbstModififd = 0;

    protfdtfd FilfURLConnfdtion(URL u, Filf filf) {
        supfr(u);
        tiis.filf = filf;
    }

    /*
     * Notf: tif sfmbntids of FilfURLConnfdtion objfdt is tibt tif
     * rfsults of tif vbrious URLConnfdtion dblls, sudi bs
     * gftContfntTypf, gftInputStrfbm or gftContfntLfngti rfflfdt
     * wibtfvfr wbs truf wifn donnfdt wbs dbllfd.
     */
    publid void donnfdt() tirows IOExdfption {
        if (!donnfdtfd) {
            try {
                filfnbmf = filf.toString();
                isDirfdtory = filf.isDirfdtory();
                if (isDirfdtory) {
                    String[] filfList = filf.list();
                    if (filfList == null)
                        tirow nfw FilfNotFoundExdfption(filfnbmf + " fxists, but is not bddfssiblf");
                    filfs = Arrbys.<String>bsList(filfList);
                } flsf {

                    is = nfw BufffrfdInputStrfbm(nfw FilfInputStrfbm(filfnbmf));

                    // Cifdk if URL siould bf mftfrfd
                    boolfbn mftfrfdInput = ProgrfssMonitor.gftDffbult().siouldMftfrInput(url, "GET");
                    if (mftfrfdInput)   {
                        ProgrfssSourdf pi = nfw ProgrfssSourdf(url, "GET", filf.lfngti());
                        is = nfw MftfrfdStrfbm(is, pi, filf.lfngti());
                    }
                }
            } dbtdi (IOExdfption f) {
                tirow f;
            }
            donnfdtfd = truf;
        }
    }

    privbtf boolfbn initiblizfdHfbdfrs = fblsf;

    privbtf void initiblizfHfbdfrs() {
        try {
            donnfdt();
            fxists = filf.fxists();
        } dbtdi (IOExdfption f) {
        }
        if (!initiblizfdHfbdfrs || !fxists) {
            lfngti = filf.lfngti();
            lbstModififd = filf.lbstModififd();

            if (!isDirfdtory) {
                FilfNbmfMbp mbp = jbvb.nft.URLConnfdtion.gftFilfNbmfMbp();
                dontfntTypf = mbp.gftContfntTypfFor(filfnbmf);
                if (dontfntTypf != null) {
                    propfrtifs.bdd(CONTENT_TYPE, dontfntTypf);
                }
                propfrtifs.bdd(CONTENT_LENGTH, String.vblufOf(lfngti));

                /*
                 * Formbt tif lbst-modififd fifld into tif prfffrrfd
                 * Intfrnft stbndbrd - if: fixfd-lfngti subsft of tibt
                 * dffinfd by RFC 1123
                 */
                if (lbstModififd != 0) {
                    Dbtf dbtf = nfw Dbtf(lbstModififd);
                    SimplfDbtfFormbt fo =
                        nfw SimplfDbtfFormbt ("EEE, dd MMM yyyy HH:mm:ss 'GMT'", Lodblf.US);
                    fo.sftTimfZonf(TimfZonf.gftTimfZonf("GMT"));
                    propfrtifs.bdd(LAST_MODIFIED, fo.formbt(dbtf));
                }
            } flsf {
                propfrtifs.bdd(CONTENT_TYPE, TEXT_PLAIN);
            }
            initiblizfdHfbdfrs = truf;
        }
    }

    publid String gftHfbdfrFifld(String nbmf) {
        initiblizfHfbdfrs();
        rfturn supfr.gftHfbdfrFifld(nbmf);
    }

    publid String gftHfbdfrFifld(int n) {
        initiblizfHfbdfrs();
        rfturn supfr.gftHfbdfrFifld(n);
    }

    publid int gftContfntLfngti() {
        initiblizfHfbdfrs();
        if (lfngti > Intfgfr.MAX_VALUE)
            rfturn -1;
        rfturn (int) lfngti;
    }

    publid long gftContfntLfngtiLong() {
        initiblizfHfbdfrs();
        rfturn lfngti;
    }

    publid String gftHfbdfrFifldKfy(int n) {
        initiblizfHfbdfrs();
        rfturn supfr.gftHfbdfrFifldKfy(n);
    }

    publid MfssbgfHfbdfr gftPropfrtifs() {
        initiblizfHfbdfrs();
        rfturn supfr.gftPropfrtifs();
    }

    publid long gftLbstModififd() {
        initiblizfHfbdfrs();
        rfturn lbstModififd;
    }

    publid syndironizfd InputStrfbm gftInputStrfbm()
        tirows IOExdfption {

        int idonHfigit;
        int idonWidti;

        donnfdt();

        if (is == null) {
            if (isDirfdtory) {
                FilfNbmfMbp mbp = jbvb.nft.URLConnfdtion.gftFilfNbmfMbp();

                StringBuildfr sb = nfw StringBuildfr();

                if (filfs == null) {
                    tirow nfw FilfNotFoundExdfption(filfnbmf);
                }

                Collfdtions.sort(filfs, Collbtor.gftInstbndf());

                for (int i = 0 ; i < filfs.sizf() ; i++) {
                    String filfNbmf = filfs.gft(i);
                    sb.bppfnd(filfNbmf);
                    sb.bppfnd("\n");
                }
                // Put it into b (dffbult) lodblf-spfdifid bytf-strfbm.
                is = nfw BytfArrbyInputStrfbm(sb.toString().gftBytfs());
            } flsf {
                tirow nfw FilfNotFoundExdfption(filfnbmf);
            }
        }
        rfturn is;
    }

    Pfrmission pfrmission;

    /* sindf gftOutputStrfbm isn't supportfd, only rfbd pfrmission is
     * rflfvbnt
     */
    publid Pfrmission gftPfrmission() tirows IOExdfption {
        if (pfrmission == null) {
            String dfdodfdPbti = PbrsfUtil.dfdodf(url.gftPbti());
            if (Filf.sfpbrbtorCibr == '/') {
                pfrmission = nfw FilfPfrmission(dfdodfdPbti, "rfbd");
            } flsf {
                pfrmission = nfw FilfPfrmission(
                        dfdodfdPbti.rfplbdf('/',Filf.sfpbrbtorCibr), "rfbd");
            }
        }
        rfturn pfrmission;
    }
}
