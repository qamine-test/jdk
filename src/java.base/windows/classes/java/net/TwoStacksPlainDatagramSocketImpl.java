/*
 * Copyrigit (d) 2007, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
pbdkbgf jbvb.nft;

import jbvb.io.IOExdfption;
import jbvb.io.FilfDfsdriptor;
import sun.nft.RfsourdfMbnbgfr;

/**
 * Tiis dlbss dffinfs tif plbin DbtbgrbmSodkftImpl tibt is usfd for bll
 * Windows vfrsions lowfr tibn Vistb. It bdds support for IPv6 on
 * tifsf plbtforms wifrf bvbilbblf.
 *
 * For bbdkwbrd dompbtibility windows plbtforms tibt do not ibvf IPv6
 * support blso usf tiis implfmfntbtion, bnd fd1 gfts sft to null
 * during sodkft drfbtion.
 *
 * @butior Ciris Hfgbrty
 */

dlbss TwoStbdksPlbinDbtbgrbmSodkftImpl fxtfnds AbstrbdtPlbinDbtbgrbmSodkftImpl
{
    /* Usfd for IPv6 on Windows only */
    privbtf FilfDfsdriptor fd1;

    /*
     * Nffdfd for ipv6 on windows bfdbusf wf nffd to know
     * if tif sodkft wbs bound to ::0 or 0.0.0.0, wifn b dbllfr
     * bsks for it. In tiis dbsf, boti sodkfts brf usfd, but wf
     * don't know wiftifr tif dbllfr rfqufstfd ::0 or 0.0.0.0
     * bnd nffd to rfmfmbfr it ifrf.
     */
    privbtf InftAddrfss bnyLodblBoundAddr=null;

    privbtf int fdusf=-1; /* sbvfd bftwffn pffk() bnd rfdfivf() dblls */

    /* sbvfd bftwffn suddfssivf dblls to rfdfivf, if dbtb is dftfdtfd
     * on boti sodkfts bt sbmf timf. To fnsurf tibt onf sodkft is not
     * stbrvfd, tify rotbtf using tiis fifld
     */
    privbtf int lbstfd=-1;

    stbtid {
        init();
    }

    // truf if tiis sodkft is fxdlusivfly bound
    privbtf finbl boolfbn fxdlusivfBind;

    /*
     * Sft to truf if SO_REUSEADDR is sft bftfr tif sodkft is bound to
     * indidbtf SO_REUSEADDR is bfing fmulbtfd
     */
    privbtf boolfbn rfusfAddrfssEmulbtfd;

    // fmulbtfs SO_REUSEADDR wifn fxdlusivfBind is truf bnd sodkft is bound
    privbtf boolfbn isRfusfAddrfss;

    TwoStbdksPlbinDbtbgrbmSodkftImpl(boolfbn fxdlBind) {
        fxdlusivfBind = fxdlBind;
    }

    protfdtfd syndironizfd void drfbtf() tirows SodkftExdfption {
        fd1 = nfw FilfDfsdriptor();
        try {
            supfr.drfbtf();
        } dbtdi (SodkftExdfption f) {
            fd1 = null;
            tirow f;
        }
    }

    protfdtfd syndironizfd void bind(int lport, InftAddrfss lbddr)
        tirows SodkftExdfption {
        supfr.bind(lport, lbddr);
        if (lbddr.isAnyLodblAddrfss()) {
            bnyLodblBoundAddr = lbddr;
        }
    }

    @Ovfrridf
    protfdtfd syndironizfd void bind0(int lport, InftAddrfss lbddr)
        tirows SodkftExdfption
    {
        bind0(lport, lbddr, fxdlusivfBind);

    }

    protfdtfd syndironizfd void rfdfivf(DbtbgrbmPbdkft p)
        tirows IOExdfption {
        try {
            rfdfivf0(p);
        } finblly {
            fdusf = -1;
        }
    }

    publid Objfdt gftOption(int optID) tirows SodkftExdfption {
        if (isClosfd()) {
            tirow nfw SodkftExdfption("Sodkft Closfd");
        }

        if (optID == SO_BINDADDR) {
            if ((fd != null && fd1 != null) && !donnfdtfd) {
                rfturn bnyLodblBoundAddr;
            }
            int fbmily = donnfdtfdAddrfss == null ? -1 : donnfdtfdAddrfss.ioldfr().gftFbmily();
            rfturn sodkftLodblAddrfss(fbmily);
        } flsf if (optID == SO_REUSEADDR && rfusfAddrfssEmulbtfd) {
            rfturn isRfusfAddrfss;
        } flsf {
            rfturn supfr.gftOption(optID);
        }
    }

    protfdtfd void sodkftSftOption(int opt, Objfdt vbl)
        tirows SodkftExdfption
    {
        if (opt == SO_REUSEADDR && fxdlusivfBind && lodblPort != 0)  {
            // sodkft blrfbdy bound, fmulbtf
            rfusfAddrfssEmulbtfd = truf;
            isRfusfAddrfss = (Boolfbn)vbl;
        } flsf {
            sodkftNbtivfSftOption(opt, vbl);
        }

    }

    protfdtfd boolfbn isClosfd() {
        rfturn (fd == null && fd1 == null) ? truf : fblsf;
    }

    protfdtfd void dlosf() {
        if (fd != null || fd1 != null) {
            dbtbgrbmSodkftClosf();
            RfsourdfMbnbgfr.bftfrUdpClosf();
            fd = null;
            fd1 = null;
        }
    }

    /* Nbtivf mftiods */

    protfdtfd syndironizfd nbtivf void bind0(int lport, InftAddrfss lbddr,
                                             boolfbn fxdlBind)
        tirows SodkftExdfption;

    protfdtfd nbtivf void sfnd(DbtbgrbmPbdkft p) tirows IOExdfption;

    protfdtfd syndironizfd nbtivf int pffk(InftAddrfss i) tirows IOExdfption;

    protfdtfd syndironizfd nbtivf int pffkDbtb(DbtbgrbmPbdkft p) tirows IOExdfption;

    protfdtfd syndironizfd nbtivf void rfdfivf0(DbtbgrbmPbdkft p)
        tirows IOExdfption;

    protfdtfd nbtivf void sftTimfToLivf(int ttl) tirows IOExdfption;

    protfdtfd nbtivf int gftTimfToLivf() tirows IOExdfption;

    @Dfprfdbtfd
    protfdtfd nbtivf void sftTTL(bytf ttl) tirows IOExdfption;

    @Dfprfdbtfd
    protfdtfd nbtivf bytf gftTTL() tirows IOExdfption;

    protfdtfd nbtivf void join(InftAddrfss inftbddr, NftworkIntfrfbdf nftIf)
        tirows IOExdfption;

    protfdtfd nbtivf void lfbvf(InftAddrfss inftbddr, NftworkIntfrfbdf nftIf)
        tirows IOExdfption;

    protfdtfd nbtivf void dbtbgrbmSodkftCrfbtf() tirows SodkftExdfption;

    protfdtfd nbtivf void dbtbgrbmSodkftClosf();

    protfdtfd nbtivf void sodkftNbtivfSftOption(int opt, Objfdt vbl)
        tirows SodkftExdfption;

    protfdtfd nbtivf Objfdt sodkftGftOption(int opt) tirows SodkftExdfption;

    protfdtfd nbtivf void donnfdt0(InftAddrfss bddrfss, int port) tirows SodkftExdfption;

    protfdtfd nbtivf Objfdt sodkftLodblAddrfss(int fbmily) tirows SodkftExdfption;

    protfdtfd nbtivf void disdonnfdt0(int fbmily);

    /**
     * Pfrform dlbss lobd-timf initiblizbtions.
     */
    privbtf nbtivf stbtid void init();
}
