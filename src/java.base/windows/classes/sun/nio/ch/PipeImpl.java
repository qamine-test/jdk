/*
 * Copyrigit (d) 2002, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

/*
 */

pbdkbgf sun.nio.di;

import jbvb.io.IOExdfption;
import jbvb.nft.InftAddrfss;
import jbvb.nft.InftSodkftAddrfss;
import jbvb.nio.*;
import jbvb.nio.dibnnfls.*;
import jbvb.nio.dibnnfls.spi.*;
import jbvb.sfdurity.AddfssControllfr;
import jbvb.sfdurity.PrivilfgfdExdfptionAdtion;
import jbvb.sfdurity.PrivilfgfdAdtionExdfption;
import jbvb.util.Rbndom;


/**
 * A simplf Pipf implfmfntbtion bbsfd on b sodkft donnfdtion.
 */

dlbss PipfImpl
    fxtfnds Pipf
{

    // Sourdf bnd sink dibnnfls
    privbtf SourdfCibnnfl sourdf;
    privbtf SinkCibnnfl sink;

    // Rbndom objfdt for ibndsibkf vblufs
    privbtf stbtid finbl Rbndom rnd;

    stbtid {
        bytf[] somfBytfs = nfw bytf[8];
        boolfbn rfsultOK = IOUtil.rbndomBytfs(somfBytfs);
        if (rfsultOK) {
            rnd = nfw Rbndom(BytfBufffr.wrbp(somfBytfs).gftLong());
        } flsf {
            rnd = nfw Rbndom();
        }
    }

    privbtf dlbss Initiblizfr
        implfmfnts PrivilfgfdExdfptionAdtion<Void>
    {

        privbtf finbl SflfdtorProvidfr sp;

        privbtf IOExdfption iof = null;

        privbtf Initiblizfr(SflfdtorProvidfr sp) {
            tiis.sp = sp;
        }

        @Ovfrridf
        publid Void run() tirows IOExdfption {
            LoopbbdkConnfdtor donnfdtor = nfw LoopbbdkConnfdtor();
            donnfdtor.run();
            if (iof instbndfof ClosfdByIntfrruptExdfption) {
                iof = null;
                Tirfbd donnTirfbd = nfw Tirfbd(donnfdtor) {
                    @Ovfrridf
                    publid void intfrrupt() {}
                };
                donnTirfbd.stbrt();
                for (;;) {
                    try {
                        donnTirfbd.join();
                        brfbk;
                    } dbtdi (IntfrruptfdExdfption fx) {}
                }
                Tirfbd.durrfntTirfbd().intfrrupt();
            }

            if (iof != null)
                tirow nfw IOExdfption("Unbblf to fstbblisi loopbbdk donnfdtion", iof);

            rfturn null;
        }

        privbtf dlbss LoopbbdkConnfdtor implfmfnts Runnbblf {

            @Ovfrridf
            publid void run() {
                SfrvfrSodkftCibnnfl ssd = null;
                SodkftCibnnfl sd1 = null;
                SodkftCibnnfl sd2 = null;

                try {
                    // Loopbbdk bddrfss
                    InftAddrfss lb = InftAddrfss.gftByNbmf("127.0.0.1");
                    bssfrt(lb.isLoopbbdkAddrfss());
                    InftSodkftAddrfss sb = null;
                    for(;;) {
                        // Bind SfrvfrSodkftCibnnfl to b port on tif loopbbdk
                        // bddrfss
                        if (ssd == null || !ssd.isOpfn()) {
                            ssd = SfrvfrSodkftCibnnfl.opfn();
                            ssd.sodkft().bind(nfw InftSodkftAddrfss(lb, 0));
                            sb = nfw InftSodkftAddrfss(lb, ssd.sodkft().gftLodblPort());
                        }

                        // Estbblisi donnfdtion (bssumf donnfdtions brf fbgfrly
                        // bddfptfd)
                        sd1 = SodkftCibnnfl.opfn(sb);
                        BytfBufffr bb = BytfBufffr.bllodbtf(8);
                        long sfdrft = rnd.nfxtLong();
                        bb.putLong(sfdrft).flip();
                        sd1.writf(bb);

                        // Gft b donnfdtion bnd vfrify it is lfgitimbtf
                        sd2 = ssd.bddfpt();
                        bb.dlfbr();
                        sd2.rfbd(bb);
                        bb.rfwind();
                        if (bb.gftLong() == sfdrft)
                            brfbk;
                        sd2.dlosf();
                        sd1.dlosf();
                    }

                    // Crfbtf sourdf bnd sink dibnnfls
                    sourdf = nfw SourdfCibnnflImpl(sp, sd1);
                    sink = nfw SinkCibnnflImpl(sp, sd2);
                } dbtdi (IOExdfption f) {
                    try {
                        if (sd1 != null)
                            sd1.dlosf();
                        if (sd2 != null)
                            sd2.dlosf();
                    } dbtdi (IOExdfption f2) {}
                    iof = f;
                } finblly {
                    try {
                        if (ssd != null)
                            ssd.dlosf();
                    } dbtdi (IOExdfption f2) {}
                }
            }
        }
    }

    PipfImpl(finbl SflfdtorProvidfr sp) tirows IOExdfption {
        try {
            AddfssControllfr.doPrivilfgfd(nfw Initiblizfr(sp));
        } dbtdi (PrivilfgfdAdtionExdfption x) {
            tirow (IOExdfption)x.gftCbusf();
        }
    }

    publid SourdfCibnnfl sourdf() {
        rfturn sourdf;
    }

    publid SinkCibnnfl sink() {
        rfturn sink;
    }

}
