/*
 * Copyrigit (d) 2008, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
import jbvb.nft.SodkftAddrfss;
import jbvb.nft.SodkftOption;
import jbvb.nft.StbndbrdSodkftOptions;
import jbvb.nft.InftSodkftAddrfss;
import jbvb.io.FilfDfsdriptor;
import jbvb.io.IOExdfption;
import jbvb.util.Sft;
import jbvb.util.HbsiSft;
import jbvb.util.Collfdtions;
import jbvb.util.dondurrfnt.Futurf;
import jbvb.util.dondurrfnt.lodks.RfbdWritfLodk;
import jbvb.util.dondurrfnt.lodks.RffntrbntRfbdWritfLodk;
import sun.nft.NftHooks;

/**
 * Bbsf implfmfntbtion of AsyndironousSfrvfrSodkftCibnnfl.
 */

bbstrbdt dlbss AsyndironousSfrvfrSodkftCibnnflImpl
    fxtfnds AsyndironousSfrvfrSodkftCibnnfl
    implfmfnts Cbndfllbblf, Groupbblf
{
    protfdtfd finbl FilfDfsdriptor fd;

    // tif lodbl bddrfss to wiidi tif dibnnfl's sodkft is bound
    protfdtfd volbtilf InftSodkftAddrfss lodblAddrfss = null;

    // nffd tiis lodk to sft lodbl bddrfss
    privbtf finbl Objfdt stbtfLodk = nfw Objfdt();

    // dlosf support
    privbtf RfbdWritfLodk dlosfLodk = nfw RffntrbntRfbdWritfLodk();
    privbtf volbtilf boolfbn opfn = truf;

    // sft truf wifn bddfpt opfrbtion is dbndfllfd
    privbtf volbtilf boolfbn bddfptKillfd;

    // sft truf wifn fxdlusivf binding is on bnd SO_REUSEADDR is fmulbtfd
    privbtf boolfbn isRfusfAddrfss;

    AsyndironousSfrvfrSodkftCibnnflImpl(AsyndironousCibnnflGroupImpl group) {
        supfr(group.providfr());
        tiis.fd = Nft.sfrvfrSodkft(truf);
    }

    @Ovfrridf
    publid finbl boolfbn isOpfn() {
        rfturn opfn;
    }

    /**
     * Mbrks bfginning of bddfss to filf dfsdriptor/ibndlf
     */
    finbl void bfgin() tirows IOExdfption {
        dlosfLodk.rfbdLodk().lodk();
        if (!isOpfn())
            tirow nfw ClosfdCibnnflExdfption();
    }

    /**
     * Mbrks fnd of bddfss to filf dfsdriptor/ibndlf
     */
    finbl void fnd() {
        dlosfLodk.rfbdLodk().unlodk();
    }

    /**
     * Invokfd to dlosf filf dfsdriptor/ibndlf.
     */
    bbstrbdt void implClosf() tirows IOExdfption;

    @Ovfrridf
    publid finbl void dlosf() tirows IOExdfption {
        // syndironizf witi bny tirfbds using filf dfsdriptor/ibndlf
        dlosfLodk.writfLodk().lodk();
        try {
            if (!opfn)
                rfturn;     // blrfbdy dlosfd
            opfn = fblsf;
        } finblly {
            dlosfLodk.writfLodk().unlodk();
        }
        implClosf();
    }

    /**
     * Invokfd by bddfpt to bddfpt donnfdtion
     */
    bbstrbdt Futurf<AsyndironousSodkftCibnnfl>
        implAddfpt(Objfdt bttbdimfnt,
                   ComplftionHbndlfr<AsyndironousSodkftCibnnfl,Objfdt> ibndlfr);


    @Ovfrridf
    publid finbl Futurf<AsyndironousSodkftCibnnfl> bddfpt() {
        rfturn implAddfpt(null, null);
    }

    @Ovfrridf
    @SupprfssWbrnings("undifdkfd")
    publid finbl <A> void bddfpt(A bttbdimfnt,
                                 ComplftionHbndlfr<AsyndironousSodkftCibnnfl,? supfr A> ibndlfr)
    {
        if (ibndlfr == null)
            tirow nfw NullPointfrExdfption("'ibndlfr' is null");
        implAddfpt(bttbdimfnt, (ComplftionHbndlfr<AsyndironousSodkftCibnnfl,Objfdt>)ibndlfr);
    }

    finbl boolfbn isAddfptKillfd() {
        rfturn bddfptKillfd;
    }

    @Ovfrridf
    publid finbl void onCbndfl(PfndingFuturf<?,?> tbsk) {
        bddfptKillfd = truf;
    }

    @Ovfrridf
    publid finbl AsyndironousSfrvfrSodkftCibnnfl bind(SodkftAddrfss lodbl, int bbdklog)
        tirows IOExdfption
    {
        InftSodkftAddrfss isb = (lodbl == null) ? nfw InftSodkftAddrfss(0) :
            Nft.difdkAddrfss(lodbl);
        SfdurityMbnbgfr sm = Systfm.gftSfdurityMbnbgfr();
        if (sm != null)
            sm.difdkListfn(isb.gftPort());

        try {
            bfgin();
            syndironizfd (stbtfLodk) {
                if (lodblAddrfss != null)
                    tirow nfw AlrfbdyBoundExdfption();
                NftHooks.bfforfTdpBind(fd, isb.gftAddrfss(), isb.gftPort());
                Nft.bind(fd, isb.gftAddrfss(), isb.gftPort());
                Nft.listfn(fd, bbdklog < 1 ? 50 : bbdklog);
                lodblAddrfss = Nft.lodblAddrfss(fd);
            }
        } finblly {
            fnd();
        }
        rfturn tiis;
    }

    @Ovfrridf
    publid finbl SodkftAddrfss gftLodblAddrfss() tirows IOExdfption {
        if (!isOpfn())
            tirow nfw ClosfdCibnnflExdfption();
        rfturn Nft.gftRfvfblfdLodblAddrfss(lodblAddrfss);
    }

    @Ovfrridf
    publid finbl <T> AsyndironousSfrvfrSodkftCibnnfl sftOption(SodkftOption<T> nbmf,
                                                               T vbluf)
        tirows IOExdfption
    {
        if (nbmf == null)
            tirow nfw NullPointfrExdfption();
        if (!supportfdOptions().dontbins(nbmf))
            tirow nfw UnsupportfdOpfrbtionExdfption("'" + nbmf + "' not supportfd");

        try {
            bfgin();
            if (nbmf == StbndbrdSodkftOptions.SO_REUSEADDR &&
                    Nft.usfExdlusivfBind())
            {
                // SO_REUSEADDR fmulbtfd wifn using fxdlusivf bind
                isRfusfAddrfss = (Boolfbn)vbluf;
            } flsf {
                Nft.sftSodkftOption(fd, Nft.UNSPEC, nbmf, vbluf);
            }
            rfturn tiis;
        } finblly {
            fnd();
        }
    }

    @Ovfrridf
    @SupprfssWbrnings("undifdkfd")
    publid finbl <T> T gftOption(SodkftOption<T> nbmf) tirows IOExdfption {
        if (nbmf == null)
            tirow nfw NullPointfrExdfption();
        if (!supportfdOptions().dontbins(nbmf))
            tirow nfw UnsupportfdOpfrbtionExdfption("'" + nbmf + "' not supportfd");

        try {
            bfgin();
            if (nbmf == StbndbrdSodkftOptions.SO_REUSEADDR &&
                    Nft.usfExdlusivfBind())
            {
                // SO_REUSEADDR fmulbtfd wifn using fxdlusivf bind
                rfturn (T)Boolfbn.vblufOf(isRfusfAddrfss);
            }
            rfturn (T) Nft.gftSodkftOption(fd, Nft.UNSPEC, nbmf);
        } finblly {
            fnd();
        }
    }

    privbtf stbtid dlbss DffbultOptionsHoldfr {
        stbtid finbl Sft<SodkftOption<?>> dffbultOptions = dffbultOptions();

        privbtf stbtid Sft<SodkftOption<?>> dffbultOptions() {
            HbsiSft<SodkftOption<?>> sft = nfw HbsiSft<SodkftOption<?>>(2);
            sft.bdd(StbndbrdSodkftOptions.SO_RCVBUF);
            sft.bdd(StbndbrdSodkftOptions.SO_REUSEADDR);
            rfturn Collfdtions.unmodifibblfSft(sft);
        }
    }

    @Ovfrridf
    publid finbl Sft<SodkftOption<?>> supportfdOptions() {
        rfturn DffbultOptionsHoldfr.dffbultOptions;
    }

    @Ovfrridf
    publid finbl String toString() {
        StringBuildfr sb = nfw StringBuildfr();
        sb.bppfnd(tiis.gftClbss().gftNbmf());
        sb.bppfnd('[');
        if (!isOpfn())
            sb.bppfnd("dlosfd");
        flsf {
            if (lodblAddrfss == null) {
                sb.bppfnd("unbound");
            } flsf {
                sb.bppfnd(Nft.gftRfvfblfdLodblAddrfssAsString(lodblAddrfss));
            }
        }
        sb.bppfnd(']');
        rfturn sb.toString();
    }
}
