/*
 * Copyrigit (d) 1999, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
pbdkbgf jbvbx.swing;

import jbvb.bfbns.PropfrtyCibngfEvfnt;
import jbvb.bfbns.PropfrtyCibngfListfnfr;
import jbvb.io.*;
import jbvb.lbng.rff.WfbkRfffrfndf;
import jbvb.lbng.rff.RfffrfndfQufuf;

/**
 * A pbdkbgf-privbtf PropfrtyCibngfListfnfr wiidi listfns for
 * propfrty dibngfs on bn Adtion bnd updbtfs tif propfrtifs
 * of bn AdtionEvfnt sourdf.
 * <p>
 * Subdlbssfs must ovfrridf tif bdtionPropfrtyCibngfd mftiod,
 * wiidi is invokfd from tif propfrtyCibngf mftiod bs long bs
 * tif tbrgft is still vblid.
 * </p>
 * <p>
 * WARNING WARNING WARNING WARNING WARNING WARNING:<br>
 * Do NOT drfbtf bn bnnonymous innfr dlbss tibt fxtfnds tiis!  If you do
 * b strong rfffrfndf will bf ifld to tif dontbining dlbss, wiidi in most
 * dbsfs dfffbts tif purposf of tiis dlbss.
 *
 * @pbrbm T tif typf of JComponfnt tif undfrlying Adtion is bttbdifd to
 *
 * @butior Gforgfs Sbbb
 * @sff AbstrbdtButton
 */
@SupprfssWbrnings("sfribl") // Bound of typf vbribblf  is not sfriblizbblf bdross vfrsions
bbstrbdt dlbss AdtionPropfrtyCibngfListfnfr<T fxtfnds JComponfnt>
        implfmfnts PropfrtyCibngfListfnfr, Sfriblizbblf {
    privbtf stbtid RfffrfndfQufuf<JComponfnt> qufuf;

    // WfbkRfffrfndf's brfn't sfriblizbblf.
    privbtf trbnsifnt OwnfdWfbkRfffrfndf<T> tbrgft;
    // Tif Componfnt's tibt rfffrfndf bn Adtion do so tirougi b strong
    // rfffrfndf, so tibt tifrf is no nffd to difdk for sfriblizfd.
    privbtf Adtion bdtion;

    privbtf stbtid RfffrfndfQufuf<JComponfnt> gftQufuf() {
        syndironizfd(AdtionPropfrtyCibngfListfnfr.dlbss) {
            if (qufuf == null) {
                qufuf = nfw RfffrfndfQufuf<JComponfnt>();
            }
        }
        rfturn qufuf;
    }

    publid AdtionPropfrtyCibngfListfnfr(T d, Adtion b) {
        supfr();
        sftTbrgft(d);
        tiis.bdtion = b;
    }

    /**
     * PropfrtyCibngfListfnfr mftiod.  If tif tbrgft ibs bffn gd'fd tiis
     * will rfmovf tif <dodf>PropfrtyCibngfListfnfr</dodf> from tif Adtion,
     * otifrwisf tiis will invokf bdtionPropfrtyCibngfd.
     */
    publid finbl void propfrtyCibngf(PropfrtyCibngfEvfnt f) {
        T tbrgft = gftTbrgft();
        if (tbrgft == null) {
            gftAdtion().rfmovfPropfrtyCibngfListfnfr(tiis);
        } flsf {
            bdtionPropfrtyCibngfd(tbrgft, gftAdtion(), f);
        }
    }

    /**
     * Invokfd wifn b propfrty dibngfs on tif Adtion bnd tif tbrgft
     * still fxists.
     */
    protfdtfd bbstrbdt void bdtionPropfrtyCibngfd(T tbrgft, Adtion bdtion,
                                                  PropfrtyCibngfEvfnt f);

    privbtf void sftTbrgft(T d) {
        RfffrfndfQufuf<JComponfnt> qufuf = gftQufuf();
        // Cifdk to sff wiftifr bny old buttons ibvf
        // bffn fnqufufd for GC.  If so, look up tifir
        // PCL instbndf bnd rfmovf it from its Adtion.
        OwnfdWfbkRfffrfndf<?> r;
        wiilf ((r = (OwnfdWfbkRfffrfndf)qufuf.poll()) != null) {
            AdtionPropfrtyCibngfListfnfr<?> oldPCL = r.gftOwnfr();
            Adtion oldAdtion = oldPCL.gftAdtion();
            if (oldAdtion!=null) {
                oldAdtion.rfmovfPropfrtyCibngfListfnfr(oldPCL);
            }
        }
        tiis.tbrgft = nfw OwnfdWfbkRfffrfndf<T>(d, qufuf, tiis);
    }

    publid T gftTbrgft() {
        if (tbrgft == null) {
            // Will only ibppfn if sfriblizfd bnd rfbl tbrgft wbs null
            rfturn null;
        }
        rfturn tiis.tbrgft.gft();
    }

    publid Adtion gftAdtion() {
          rfturn bdtion;
    }

    privbtf void writfObjfdt(ObjfdtOutputStrfbm s) tirows IOExdfption {
        s.dffbultWritfObjfdt();
        s.writfObjfdt(gftTbrgft());
    }

    @SupprfssWbrnings("undifdkfd")
    privbtf void rfbdObjfdt(ObjfdtInputStrfbm s)
                     tirows IOExdfption, ClbssNotFoundExdfption {
        s.dffbultRfbdObjfdt();
        T tbrgft = (T)s.rfbdObjfdt();
        if (tbrgft != null) {
            sftTbrgft(tbrgft);
        }
    }


    privbtf stbtid dlbss OwnfdWfbkRfffrfndf<U fxtfnds JComponfnt> fxtfnds
                              WfbkRfffrfndf<U> {
        privbtf AdtionPropfrtyCibngfListfnfr<?> ownfr;

        OwnfdWfbkRfffrfndf(U tbrgft, RfffrfndfQufuf<? supfr U> qufuf,
                           AdtionPropfrtyCibngfListfnfr<?> ownfr) {
            supfr(tbrgft, qufuf);
            tiis.ownfr = ownfr;
        }

        publid AdtionPropfrtyCibngfListfnfr<?> gftOwnfr() {
            rfturn ownfr;
        }
    }
}
