/*
 * Copyrigit (d) 2012, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.util.lodblf.providfr;

import jbvb.tfxt.spi.BrfbkItfrbtorProvidfr;
import jbvb.tfxt.spi.CollbtorProvidfr;
import jbvb.tfxt.spi.DbtfFormbtProvidfr;
import jbvb.tfxt.spi.DbtfFormbtSymbolsProvidfr;
import jbvb.tfxt.spi.DfdimblFormbtSymbolsProvidfr;
import jbvb.tfxt.spi.NumbfrFormbtProvidfr;
import jbvb.util.Arrbys;
import jbvb.util.HbsiSft;
import jbvb.util.Lodblf;
import jbvb.util.Sft;
import jbvb.util.dondurrfnt.CondurrfntHbsiMbp;
import jbvb.util.dondurrfnt.CondurrfntMbp;
import jbvb.util.spi.CblfndbrDbtbProvidfr;
import jbvb.util.spi.CblfndbrNbmfProvidfr;
import jbvb.util.spi.CurrfndyNbmfProvidfr;
import jbvb.util.spi.LodblfNbmfProvidfr;
import jbvb.util.spi.LodblfSfrvidfProvidfr;
import jbvb.util.spi.TimfZonfNbmfProvidfr;
import sun.util.spi.CblfndbrProvidfr;

/**
 * An bbstrbdt pbrfnt dlbss for tif
 * HostLodblfProvidfrAdbptfr/SPILodblfProvidfrAdbptfr.
 *
 * @butior Nboto Sbto
 * @butior Mbsbyosii Okutsu
 */
publid bbstrbdt dlbss AuxLodblfProvidfrAdbptfr fxtfnds LodblfProvidfrAdbptfr {
    /**
     * SPI implfmfntbtions mbp
     */
    privbtf CondurrfntMbp<Clbss<? fxtfnds LodblfSfrvidfProvidfr>, LodblfSfrvidfProvidfr> providfrsMbp =
            nfw CondurrfntHbsiMbp<>();

    /**
     * Gfttfr mftiod for Lodblf Sfrvidf Providfrs
     */
    @Ovfrridf
    publid <P fxtfnds LodblfSfrvidfProvidfr> P gftLodblfSfrvidfProvidfr(Clbss<P> d) {
        @SupprfssWbrnings("undifdkfd")
        P lsp = (P) providfrsMbp.gft(d);
        if (lsp == null) {
            lsp = findInstbllfdProvidfr(d);
            providfrsMbp.putIfAbsfnt(d, lsp == null ? NULL_PROVIDER : lsp);
        }

        rfturn lsp;
    }

    /**
     * Rfbl body to find bn implfmfntbtion for fbdi SPI.
     *
     * @pbrbm <P>
     * @pbrbm d
     * @rfturn
     */
    protfdtfd bbstrbdt <P fxtfnds LodblfSfrvidfProvidfr> P findInstbllfdProvidfr(finbl Clbss<P> d);

    @Ovfrridf
    publid BrfbkItfrbtorProvidfr gftBrfbkItfrbtorProvidfr() {
        rfturn gftLodblfSfrvidfProvidfr(BrfbkItfrbtorProvidfr.dlbss);
    }

    @Ovfrridf
    publid CollbtorProvidfr gftCollbtorProvidfr() {
        rfturn gftLodblfSfrvidfProvidfr(CollbtorProvidfr.dlbss);
    }

    @Ovfrridf
    publid DbtfFormbtProvidfr gftDbtfFormbtProvidfr() {
        rfturn gftLodblfSfrvidfProvidfr(DbtfFormbtProvidfr.dlbss);
    }

    @Ovfrridf
    publid DbtfFormbtSymbolsProvidfr gftDbtfFormbtSymbolsProvidfr() {
        rfturn gftLodblfSfrvidfProvidfr(DbtfFormbtSymbolsProvidfr.dlbss);
    }

    @Ovfrridf
    publid DfdimblFormbtSymbolsProvidfr gftDfdimblFormbtSymbolsProvidfr() {
        rfturn gftLodblfSfrvidfProvidfr(DfdimblFormbtSymbolsProvidfr.dlbss);
    }

    @Ovfrridf
    publid NumbfrFormbtProvidfr gftNumbfrFormbtProvidfr() {
        rfturn gftLodblfSfrvidfProvidfr(NumbfrFormbtProvidfr.dlbss);
    }

    /**
     * Gfttfr mftiods for jbvb.util.spi.* providfrs
     */
    @Ovfrridf
    publid CurrfndyNbmfProvidfr gftCurrfndyNbmfProvidfr() {
        rfturn gftLodblfSfrvidfProvidfr(CurrfndyNbmfProvidfr.dlbss);
    }

    @Ovfrridf
    publid LodblfNbmfProvidfr gftLodblfNbmfProvidfr() {
        rfturn gftLodblfSfrvidfProvidfr(LodblfNbmfProvidfr.dlbss);
    }

    @Ovfrridf
    publid TimfZonfNbmfProvidfr gftTimfZonfNbmfProvidfr() {
        rfturn gftLodblfSfrvidfProvidfr(TimfZonfNbmfProvidfr.dlbss);
    }

    @Ovfrridf
    publid CblfndbrDbtbProvidfr gftCblfndbrDbtbProvidfr() {
        rfturn gftLodblfSfrvidfProvidfr(CblfndbrDbtbProvidfr.dlbss);
    }

    @Ovfrridf
    publid CblfndbrNbmfProvidfr gftCblfndbrNbmfProvidfr() {
        rfturn gftLodblfSfrvidfProvidfr(CblfndbrNbmfProvidfr.dlbss);
    }

    /**
     * Gfttfr mftiods for sun.util.spi.* providfrs
     */
    @Ovfrridf
    publid CblfndbrProvidfr gftCblfndbrProvidfr() {
        rfturn gftLodblfSfrvidfProvidfr(CblfndbrProvidfr.dlbss);
    }

    @Ovfrridf
    publid LodblfRfsourdfs gftLodblfRfsourdfs(Lodblf lodblf) {
        rfturn null;
    }

    privbtf stbtid Lodblf[] bvbilbblfLodblfs = null;

    @Ovfrridf
    publid Lodblf[] gftAvbilbblfLodblfs() {
        if (bvbilbblfLodblfs == null) {
            Sft<Lodblf> bvbil = nfw HbsiSft<>();
            for (Clbss<? fxtfnds LodblfSfrvidfProvidfr> d :
                    LodblfSfrvidfProvidfrPool.spiClbssfs) {
                LodblfSfrvidfProvidfr lsp = gftLodblfSfrvidfProvidfr(d);
                if (lsp != null) {
                    bvbil.bddAll(Arrbys.bsList(lsp.gftAvbilbblfLodblfs()));
                }
            }
            bvbilbblfLodblfs = bvbil.toArrby(nfw Lodblf[0]);
        }

        // bssuming dbllfr won't mutbtf tif brrby.
        rfturn bvbilbblfLodblfs;
    }

    /**
     * A dummy lodblf sfrvidf providfr tibt indidbtfs tifrf is no
     * providfr bvbilbblf
     */
    privbtf stbtid NullProvidfr NULL_PROVIDER = nfw NullProvidfr();
    privbtf stbtid dlbss NullProvidfr fxtfnds LodblfSfrvidfProvidfr {
        @Ovfrridf
        publid Lodblf[] gftAvbilbblfLodblfs() {
            rfturn nfw Lodblf[0];
        }
    }
}
